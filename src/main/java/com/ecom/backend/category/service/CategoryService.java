package com.ecom.backend.category.service;

import com.ecom.backend.category.model.Category;
import com.ecom.backend.category.dto.CategoryDto;
import com.ecom.backend.category.repository.CategoryRepository;
import com.ecom.backend.product.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository catRepo;
    @Autowired
    private ModelMapper mapper;
    public CategoryDto create(CategoryDto dto){
        //CategoryDto to Category
        Category cat = this.mapper.map(dto, Category.class);
        Category save = this.catRepo.save(cat);
        //Category to CategoryDto
        return  this.mapper.map(save, CategoryDto.class);
    }
    public CategoryDto getById(String catId){
        Category  getById = this.catRepo.findById(catId).orElseThrow(() ->new ResourceNotFoundException("This category Id not found"));
        return this.mapper.map(getById, CategoryDto.class);
    }

    public List<CategoryDto> getAll(){
       List<Category> findAll = this.catRepo.findAll();
       List<CategoryDto> allDto = findAll.stream().map(cat -> this.mapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return  allDto ;
    }
    public CategoryDto update( CategoryDto newcat, String catId){
        Category  oldCat = this.catRepo.findById(catId).orElseThrow(() ->new ResourceNotFoundException("This category Id not found"));
        oldCat.setCategory_title(newcat.getCategory_title());
        Category save = this.catRepo.save(oldCat);
        return this.mapper.map(oldCat, CategoryDto.class);
    }

    public void delete(String catId){
        Category  Cat = this.catRepo.findById(catId).orElseThrow(() ->new ResourceNotFoundException("This category Id not found")) ;
        this.catRepo.delete(Cat);
    }
}
