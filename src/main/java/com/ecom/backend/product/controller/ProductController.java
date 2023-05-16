package com.ecom.backend.product.controller;

import com.ecom.backend.payload.AppConstants;
import com.ecom.backend.payload.ProductResponse;
import com.ecom.backend.product.dto.ProductDto;
import com.ecom.backend.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //Add product
    @PostMapping("/{catid}")
    @ResponseBody
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product,@PathVariable String catid){
        ProductDto createProduct = productService.createProduct(product,catid);
        return new ResponseEntity<ProductDto>(createProduct, HttpStatus.CREATED);
    }

    //view product
    @GetMapping
    public ProductResponse  viewAllProduct(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER_STRING,required = false) int pageNumber,
     @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE_STRING,required = false) int pageSize,
     @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY_STRING,required = false) String sortBy,
     @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR_STRING,required = false) String sortDir ) {
        ProductResponse response = productService.viewAll(pageNumber,pageSize,sortBy,sortDir);
      return response ;
    }

    // view by id
    @ResponseBody
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> viewProductById(@PathVariable String productId){
        ProductDto viewById = productService.viewProductById(productId);
        return new ResponseEntity<ProductDto>(viewById,HttpStatus.OK);
    }

    // delete product
    @DeleteMapping("/{productId}")
    public  ResponseEntity<String> deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted",HttpStatus.NO_CONTENT);
    }

    //update product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId,@RequestBody ProductDto newproduct){
        ProductDto updateProduct = productService.updateProduct(productId, newproduct);
      return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.RESET_CONTENT);
    }
    // find product by category wise

    @GetMapping("categories/{catid}")
    public ResponseEntity<List<ProductDto>>getProductByCategory(@PathVariable String catid){
        List<ProductDto> findProductByCategory = this.productService.findProductByCategory(catid);
        return new ResponseEntity<List<ProductDto>>(findProductByCategory,HttpStatus.OK);
    }
}
