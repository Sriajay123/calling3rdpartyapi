package com.project.ecommerce.services;

import com.project.ecommerce.dtos.FakeProductServiceDto;
import com.project.ecommerce.models.Category;
import com.project.ecommerce.models.Product;
import com.project.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductServiceImpl implements ProductService{

  private RestTemplate restTemplate;

  private ProductRepository productRepository;


     @Autowired
    public FakeProductServiceImpl(RestTemplate restTemplate,ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository=productRepository;
    }



    private Product convertDtoToProduct(FakeProductServiceDto dto){
       Product product=new Product();
       product.setId(dto.getId());
       product.setTitle(dto.getTitle());
       product.setPrice(dto.getPrice());
       product.setImage(dto.getImage());
       product.setDescription(dto.getDescription());
        Category category =new Category();
        category.setName(dto.getCategory());
        product.setCategory(category);
        return product;

    }

    @Override
    public Product getProductById(long id) {
        FakeProductServiceDto dto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProductServiceDto.class);
        return convertDtoToProduct(dto);

    }

 @Override
    public List<Product> getProducts() {
        FakeProductServiceDto[] arr = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeProductServiceDto[].class);
        List<Product> products=new ArrayList<>();
        for(FakeProductServiceDto dto:arr){
            products.add(convertDtoToProduct(dto));
        }
        return productRepository.saveAll(products);
    }
}
