package com.ecom.backend.product.service;

import com.ecom.backend.category.model.Category;
import com.ecom.backend.category.dto.CategoryDto;
import com.ecom.backend.payload.ProductResponse;
import com.ecom.backend.category.repository.CategoryRepository;
import com.ecom.backend.product.exception.ResourceNotFoundException;
import com.ecom.backend.product.model.Product;
import com.ecom.backend.product.dto.ProductDto;
import com.ecom.backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository catRepo;


    public ProductDto createProduct(ProductDto productDto,String catid){
        //Fetch Category is available or not
        Category cat = this.catRepo.findById(catid).orElseThrow(() ->new ResourceNotFoundException("This Category id not found Category"));
        // productDto to product
        System.out.println(productDto.getProduct_name());
        Product product = toEntity(productDto);
        product.setCategory(cat);
         Product save = productRepo.save(product);
         //Product save = this.productRepo.save(entity );
         // product to productDto
        ProductDto dto   = toDto(save);
        return dto;
    }
    public ProductResponse viewAll(int pageNumber,int pageSize,String sortBY,String sortDir){
         Sort sort = null;
        if(sortDir.trim().toLowerCase().equals("asc")) {
            sort = Sort.by(sortBY).ascending();
            System.out.print(sort);
        }else {
            sort=Sort.by(sortBY).descending();
            System.out.print(sort);
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = this.productRepo.findAll(pageable);
        List<Product> pageProduct = page.getContent();

        List<ProductDto> productDto = pageProduct.stream().map(p->this.toDto(p)).collect(Collectors.toList());

        ProductResponse response=new ProductResponse();
        response.setContent(productDto);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        //ProductDto to Product
        // List<Product> findAll = productRepo.findAll();
        //Product to ProductDto
        // List<ProductDto> findAllDto = findAll.stream().map(product ->this.toDto(product)).collect(Collectors.toList());
        return response;
    }
    public ProductDto viewProductById(String pid){

        Product  findById = productRepo.findById(pid).orElseThrow(() ->new ResourceNotFoundException(pid+ " from this product id product not found"));
        ProductDto dto = this.toDto(findById);
        return dto;
    }
    public  void deleteProduct(String pid){
        Product byId = productRepo.findById(pid).orElseThrow(() ->new ResourceNotFoundException(pid+ " from this product id product not found"));
        productRepo.deleteById(pid);

    }
    public ProductDto  updateProduct(String pid,ProductDto newproduct){
        Product  oldproduct = productRepo.findById(pid).orElseThrow(() ->new ResourceNotFoundException(pid+ " product not found"));

        oldproduct.setProduct_name(newproduct.getProduct_name());
        oldproduct.setProduct_desc(newproduct.getProduct_desc());
        oldproduct.setProduct_price(newproduct.getProduct_price());
        oldproduct.setProduct_imageName(newproduct.getProduct_imageName());
        oldproduct.setProduct_quantity(newproduct.getProduct_quantity());
        oldproduct.setLive(newproduct.isLive());
        oldproduct.setStock(newproduct.isStock());
        Product save = productRepo.save(oldproduct);

        ProductDto dto = toDto(save);
        return dto ;
    }

    //find product by category

    public List<ProductDto> findProductByCategory( String catid){
       Category Cat = this.catRepo.findById(catid).orElseThrow(() -> new ResourceNotFoundException("This id Category not found"));
      List<Product> findByCategory = this.productRepo.findByCategory(Cat);
      List<ProductDto> collect = findByCategory.stream().map(product -> toDto(product)).collect(Collectors.toList());
       return collect;
    }


    // productDto to product
    public Product toEntity(ProductDto pDto){
        Product p=new Product();

        p.setProduct_price(pDto.getProduct_price());
        p.setProduct_name(pDto.getProduct_name());
        p.setProduct_imageName(pDto.getProduct_imageName());
        p.setProduct_desc(pDto.getProduct_desc());
        p.setLive(pDto.isLive());
        p.setStock(pDto.isStock());
        p.setProduct_quantity(pDto.getProduct_quantity() );
        return p;
    }
    //product to productDto
     public  ProductDto toDto(Product product){
        ProductDto pDto = new ProductDto();
        pDto.setId(product.getId());
        pDto.setProduct_name(product.getProduct_name());
        pDto.setProduct_price(product.getProduct_price());
        pDto.setProduct_quantity(product.getProduct_quantity());
        pDto.setProduct_imageName(product.getProduct_imageName());
        pDto.setProduct_desc(product.getProduct_desc());
        pDto.setLive(pDto.isLive());
        pDto.setStock(product.isStock());


        // Change Category to categoryDto
        CategoryDto catDto = new CategoryDto();
        catDto.setId(product.getCategory().getId());
        catDto.setCategory_title(product.getCategory().getCategory_title());

//         //Set Category to category Dto
          pDto.setCategory(catDto);
          return  pDto;

     }
}
