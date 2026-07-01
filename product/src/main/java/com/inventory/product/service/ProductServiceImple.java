package com.inventory.product.service;

import com.inventory.product.dto.ProductRequest;
import com.inventory.sharedfiles.ProductResponse;
import com.inventory.sharedfiles.ProductResponsePage;
import com.inventory.product.exception.ResourceNotFoundException;
import com.inventory.product.model.Category;
import com.inventory.product.model.Product;
import com.inventory.product.repository.CategoryRepo;
import com.inventory.product.repository.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImple implements ProductService{
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public  ProductResponsePage  getAll(Long pageNumber, Long pageSize, String sortBy, String sortDir) {
        Sort sorting = sortDir.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageRequest = PageRequest.of(pageNumber.intValue(),pageSize.intValue(),sorting);
        Page<Product> page = productRepo.findAll(pageRequest);
        List<ProductResponse> productResponses = page.getContent()
                .stream()
                .map(product ->{
                    ProductResponse p  = modelMapper.map(product,ProductResponse.class);
                    Category category = categoryRepo.findById(product.getCategory().getId())
                            .orElseThrow(()->new ResourceNotFoundException("Category",product.getCategory().getId()));
                    p.setCategoryName(category.getName());
                    return p;
                })
                .toList();

        ProductResponsePage res = new ProductResponsePage();
        res.setProductResponse(productResponses);
        res.setPageNumber(page.getNumber());
        res.setPageSize(page.getSize());
        res.setTotalPages(page.getTotalPages());
        res.setTotalElements(page.getTotalElements());
        res.setLastPage(page.isLast());

        return res;
    }

    @Override
    public ProductResponse getById(Long id) {
         Product p = productRepo.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("Product",id));
         ProductResponse pr = modelMapper.map(p, ProductResponse.class);
         pr.setCategoryName(p.getCategory().getName());
         return pr;
    }

    @Override
    public ProductResponse add(ProductRequest request) {
        Product p = modelMapper.map(request,Product.class);
        Category c = categoryRepo.findByName(request.getCategory())
                .orElseThrow(()-> new ResourceNotFoundException("Category", request.getCategory()));
        p.setCategory(c);
        p.setCreatedAt(LocalDate.now());
        Product ps = productRepo.save(p);
        ProductResponse pr = modelMapper.map(ps,ProductResponse.class);
        pr.setCategoryName(c.getName());
        return pr;
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product p = productRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product",id));
        Category c = categoryRepo.findByName(request.getCategory())
                .orElseThrow(()-> new ResourceNotFoundException("Product", request.getCategory()));
        p.setCategory(c);
        p.setName(request.getName());
        p.setDescription(request.getDescription());
        p.setQuantity(request.getQuantity());
        p.setPrice(request.getPrice());

        Product ps = productRepo.save(p);
        ProductResponse pr = modelMapper.map(ps,ProductResponse.class);
        pr.setCategoryName(c.getName());
        return pr;
    }

    @Override
    public ProductResponse delete(Long id) {
        Product p = productRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product",id));
        ProductResponse pr = modelMapper.map(p,ProductResponse.class);
        pr.setCategoryName(p.getCategory().getName());

        productRepo.delete(p);
        return pr;
    }

    @Override
    public void addall(List<ProductRequest> request) {
        List<Product> l = request.stream()
                .map(p ->{
                    Product ps = modelMapper.map(p,Product.class);
                    Category c = categoryRepo.findByName(p.getCategory())
                            .orElseThrow(()-> new ResourceNotFoundException("Category",p.getCategory()));
                    ps.setCategory(c);
                    ps.setCreatedAt(LocalDate.now());
                    return ps;
                }).toList();
        productRepo.saveAll(l);
    }

    @Override
    public void updateProductByPurchase(Long productId, Long quantity) {
        Product p = productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product",productId));
        p.setQuantity(p.getQuantity()+quantity);
        Product ps = productRepo.save(p);
    }

    @Override
    public boolean updateProductBySale(Long productId, Long quantity) {
        Product p = productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product",productId));
        if(p.getQuantity()-quantity<0)
            return false;
        p.setQuantity(p.getQuantity()-quantity);
        Product ps = productRepo.save(p);
        return true;
    }

    @Override
    public Double productPrice(Long productId) {

        Product p = productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product",productId));
        return p.getPrice();
    }

    @Override
    public ProductResponsePage getAvailablestocks(Long pageNumber, Long pageSize, String sortBy, String sortDir) {
        Sort sorting = sortDir.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageRequest = PageRequest.of(pageNumber.intValue(),pageSize.intValue(),sorting);
        Page<Product> page = productRepo.findAll(pageRequest);
        List<ProductResponse> productResponses = page.getContent()
                .stream()
                .map(product ->{
                    ProductResponse p  = modelMapper.map(product,ProductResponse.class);
                    Category category = categoryRepo.findById(product.getCategory().getId())
                            .orElseThrow(()->new ResourceNotFoundException("Category",product.getCategory().getId()));
                    p.setCategoryName(category.getName());
                    return p;
                })
                .filter(pro->pro.getQuantity()>0L)
                .toList();

        ProductResponsePage res = new ProductResponsePage();
        res.setProductResponse(productResponses);
        res.setPageNumber(page.getNumber());
        res.setPageSize(page.getSize());
        res.setTotalPages(page.getTotalPages());
        res.setTotalElements(page.getTotalElements());
        res.setLastPage(page.isLast());

        return res;
    }

    @Override
    public boolean updateQuantityOfProduct(Long id,Long quantity) {
        Product p = productRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product",id));
        p.setQuantity(quantity);
        productRepo.save(p);
       return true;
    }


}
