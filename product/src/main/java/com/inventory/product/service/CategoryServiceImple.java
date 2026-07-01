package com.inventory.product.service;

import com.inventory.product.dto.CategoryRequest;
import com.inventory.sharedfiles.CategoryResponse;
import com.inventory.product.dto.CategoryResponsePage;
import com.inventory.product.exception.ResourceNotFoundException;
import com.inventory.product.model.Category;
import com.inventory.product.repository.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImple implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryResponse add(CategoryRequest request) {
        Category category = modelMapper.map(request,Category.class);
        Category cs = categoryRepo.save(category);
        CategoryResponse cr = modelMapper.map(cs,CategoryResponse.class);
        return cr;
    }

    @Override
    public CategoryResponsePage getAll(Long pageNumber, Long pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")?
                  Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber.intValue(),pageSize.intValue(),sort);
        Page<Category>  categoryPage = categoryRepo.findAll(pageable);
        List<CategoryResponse> c = categoryPage.getContent()
                .stream()
                .map(category -> modelMapper.map(category,CategoryResponse.class))
                .toList();
        CategoryResponsePage crp = new CategoryResponsePage();
        crp.setCategoryResponse(c);
        crp.setLastPage(categoryPage.isLast());
        crp.setPageNumber(categoryPage.getNumber());
        crp.setPageSize(categoryPage.getSize());
        crp.setTotalElements(categoryPage.getTotalElements());
        crp.setTotalPages(categoryPage.getTotalPages());

        return crp;


    }

    @Override
    public CategoryResponse getById(Long id) {
        Category c = categoryRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category",id));
        CategoryResponse cr = modelMapper.map(c,CategoryResponse.class);
        return cr;
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category c = categoryRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category",id));
        c.setName(request.getName());
        c.setDescription(request.getDescription());
        Category cs = categoryRepo.save(c);
        CategoryResponse cr = modelMapper.map(cs,CategoryResponse.class);
        return cr;
    }

    @Override
    public CategoryResponse delete(Long id) {
        Category c = categoryRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category",id));

        categoryRepo.delete(c);
        CategoryResponse cd = modelMapper.map(c,CategoryResponse.class);
        return cd;
    }

    @Override
    public void addall(List<CategoryRequest> request) {
        List<Category> l = request.stream()
                .map(c -> modelMapper.map(c,Category.class))
                .toList();
        categoryRepo.saveAll(l);
    }
}
