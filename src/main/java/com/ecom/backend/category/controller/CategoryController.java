package com.ecom.backend.category.controller;


import com.ecom.backend.payload.ApiResponse;
import com.ecom.backend.category.dto.CategoryDto;
import com.ecom.backend.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService catService;

    //create category
    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto catDto){
       CategoryDto create = this.catService.create(catDto);
        return new ResponseEntity<CategoryDto>(create, HttpStatus.CREATED);
    }

    //getCategoryById

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String catId){
        CategoryDto getById = this.catService.getById(catId );
        return new ResponseEntity<CategoryDto>((getById),HttpStatus.OK);
    }

    //getAllById

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> all = this.catService.getAll();
        return new ResponseEntity<List<CategoryDto>>(all,HttpStatus.OK);
    }
    //Update category

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto catDto,@PathVariable String catId){
        CategoryDto update = this.catService.update(catDto,catId);
        return new ResponseEntity<CategoryDto>(update, HttpStatus.RESET_CONTENT);
    }

    // Delete category

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String catId){
        this.catService.delete(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category delete successfully",true), HttpStatus.NO_CONTENT);
    }

}
