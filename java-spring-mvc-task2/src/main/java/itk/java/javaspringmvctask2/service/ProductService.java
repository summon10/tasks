package itk.java.javaspringmvctask2.service;

import itk.java.javaspringmvctask2.entity.Order;
import itk.java.javaspringmvctask2.entity.Product;
import itk.java.javaspringmvctask2.exception.ResourceNotFoundException;
import itk.java.javaspringmvctask2.repository.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepo productRepo;

    public List<Product> findAllProducts(){
        return productRepo.findAll();
    }

    public Product findProductById(@Valid Long productId) {
       return productRepo.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product doesn't exist"));
    }

    public Product saveOrUpdateNewProduct(Product product){
       return productRepo.save(product);
    }

    public void deleteById(@Valid Long productId) {
        productRepo.deleteById(productId);
    }

}
