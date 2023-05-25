package com.ecom.backend.product.controller;

import com.ecom.backend.fileUpload.service.FileUpload;
import com.ecom.backend.payload.AppConstants;
import com.ecom.backend.payload.ProductResponse;
import com.ecom.backend.product.dto.ProductDto;
import com.ecom.backend.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileUpload fileUpload;
    @Value("${product.path.images}")
     private String imagePath;

    @PostMapping("/images/{productId}")
    public ResponseEntity<?> uploadImageOfProduct(@PathVariable String productId,
                                                 @RequestParam("product_image")MultipartFile file){
       ProductDto product = this.productService.getProduct(productId);
       String imageName = null;

       try {
          String uploadImage = this.fileUpload.uploadImage(imagePath,file);
           product.setProduct_imageName(uploadImage);
           System.out.println(product.getProduct_imageName());
           ProductDto updateProduct = this.productService.updateProduct(productId,product);

           return new ResponseEntity<>(updateProduct,HttpStatus.ACCEPTED);
       }catch (Exception e){
           e.printStackTrace();
           return new ResponseEntity<>(Map.of("Message","File not upload in server"),HttpStatus.INTERNAL_SERVER_ERROR);
       }


    }

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
        ProductResponse response = productService.getAllProduct(pageNumber,pageSize,sortBy,sortDir);
      return response ;
    }

    // view by id
    @ResponseBody
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> viewProductById(@PathVariable String productId){
        ProductDto viewById = productService.getProduct(productId);
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
