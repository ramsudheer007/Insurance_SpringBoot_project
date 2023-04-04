package org.example.insuranceManagement.repository;

import java.util.List;

import org.example.insuranceManagement.entity.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository){
        return args-> {
           Product prod1=new Product( "Car Insurance", "Car Insurance", "Car", 1000.00);
				Product prod2=new Product("Home Insurance", "Home Insurance", "Home", 2000.00);
				Product prod3=new Product("Life Insurance", "Life Insurance", "Life", 3000.00);
           
           repository.saveAll(List.of(prod1,prod2,prod3));
        };
    }
}
