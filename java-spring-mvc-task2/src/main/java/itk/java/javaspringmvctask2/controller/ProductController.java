package itk.java.javaspringmvctask2.controller;

import itk.java.javaspringmvctask2.dto.ProductDTO;
import itk.java.javaspringmvctask2.entity.Product;
import itk.java.javaspringmvctask2.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @GetMapping("/products")
    public ResponseEntity<String> getAllProducts(){


        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(productService.findAllProducts()));
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<String> getProductInfo(
            @Valid @PathVariable Long productId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(productService.findProductById(productId)));
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createNewProduct(
            @Valid @RequestBody String product){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.saveOrUpdateNewProduct(objectMapper.readValue(product, Product.class)));

    }

    @PatchMapping("/products/{productId}")
    public ResponseEntity<String> updateExistingProduct(
            @PathVariable Long productId,
            @Valid @RequestBody String json
            ){
        Product existingProduct = productService.findProductById(productId);

        ProductDTO productDTO = objectMapper.readValue(json, ProductDTO.class);
        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setPrice(productDTO.getPrice());
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(productService.saveOrUpdateNewProduct(existingProduct)));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteUser(
            @Valid @PathVariable Long productId){
        productService.deleteById(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body (objectMapper.writeValueAsString("Deleted successfully"));


    }




}
