package org.example.insuranceManagement.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.example.insuranceManagement.entity.Product;
import org.example.insuranceManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<Product> getProducts(){
       return productRepository.findAll();
    }

    public Product getInsuranceProduct(Long productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        
        if(productOptional.isEmpty()){
            throw new IllegalStateException("Product with id " + productId + " does not exist");
        }
        return productOptional.get();
    }

    public Product addInsuranceProduct(Product product) {
        Optional<Product> productOptional = productRepository.findByProductName(product.getProductName());

        if(product.getProductName() == null || product.getProductName().isEmpty()){
            throw new IllegalStateException("Product name is required");
        }

        if(product.getProductType() == null || product.getProductType().isEmpty()){
            throw new IllegalStateException("Product type is required");
        }

        if(productOptional.isPresent()){
            throw new IllegalStateException("Product already exists");
        }
        Product addedProduct= productRepository.save(product);

        return addedProduct;

    }

    

    public void deleteInsuranceProduct(Long productId){
        boolean exists = productRepository.existsById(productId);
        if(!exists){
            throw new IllegalStateException("Product with id " + productId + " does not exist");
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public Product updateInsuranceProduct(Long productId, String productName, String productType,double price, String description){
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product with id " + productId + " does not exist"));

        if(productName != null && productName.length() > 0 && !Objects.equals(product.getProductName(), productName)){

            Optional<Product> productOptional = productRepository.findByProductName(productName);
            if(productOptional.isPresent()){
                throw new IllegalStateException("Product name already exists");
            }
            System.out.println("Product name is " + productName);
            product.setProductName(productName);
        }

        if(productType != null && productType.length() > 0 && !Objects.equals(product.getProductType(), productType)){
            product.setProductType(productType);
        }

        if(price != 0 && !Objects.equals(product.getPrice(), price)){
            product.setPrice(price);
        }

        if(description != null && description.length() > 0 && !Objects.equals(product.getDescription(), description)){
            product.setDescription(description);
        }



        return product;
    }


 
}
