package com.inventory.supplier.service;

import com.inventory.supplier.dto.SupplierRequest;
import com.inventory.supplier.dto.SupplierResponse;
import com.inventory.supplier.dto.SupplierResponsePage;
import com.inventory.supplier.exception.ResourceNotFoundException;
import com.inventory.supplier.model.Address;
import com.inventory.supplier.model.Supplier;
import com.inventory.supplier.repositoy.AddressRepo;
import com.inventory.supplier.repositoy.SupplierRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImple implements SupplierService{
    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public SupplierResponse getById(Long id) {
        Supplier s = supplierRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Supplier",id));
        SupplierResponse sr = modelMapper.map(s,SupplierResponse.class);
        return sr;
    }

    @Override
    public SupplierResponse add(SupplierRequest request) {
        Supplier s = modelMapper.map(request,Supplier.class);
        Address a = modelMapper.map(request,Address.class);
        s.setAddress(a);

        Supplier ss = supplierRepo.save(s);
        SupplierResponse sr = modelMapper.map(ss,SupplierResponse.class);
        return sr;
    }

    @Override
    public SupplierResponse update(Long id, SupplierRequest request) {
        Supplier s = supplierRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Supplier",id));
        s.setName(request.getName());
        s.setEmail(request.getEmail());
        Address a = addressRepo.findById(s.getAddress().getId()).orElseThrow(()->new ResourceNotFoundException("supplier","address"));;
        a.setDistrict(request.getDistrict());
        a.setCity(request.getCity());
        a.setHouseNo(request.getHouseNo());
        s.setAddress(a);

        Supplier ss = supplierRepo.save(s);
        SupplierResponse sr = modelMapper.map(ss,SupplierResponse.class);
        return sr;
    }

    @Override
    public SupplierResponse delete(Long id) {
        Supplier s = supplierRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Supplier",id));
        supplierRepo.delete(s);
        SupplierResponse sr = modelMapper.map(s,SupplierResponse.class);
        return sr;
    }

    @Override
    public void addall(List<SupplierRequest> request) {
     List<Supplier> list=request.stream()
             .map(sr ->
             {
                 Supplier s = modelMapper.map(sr,Supplier.class);
                 Address a = modelMapper.map(sr,Address.class);

                 s.setAddress(a);
                 return  s;
             })
             .toList();
     supplierRepo.saveAll(list);
    }

    @Override
    public SupplierResponsePage getAll(Long pageNumber, Long pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber.intValue(),pageSize.intValue(),sort);
        Page<Supplier> page = supplierRepo.findAll(pageable);
        List<SupplierResponse> list=page.getContent().stream()
                .map(supplier -> modelMapper.map(supplier,SupplierResponse.class))
                .toList();
        SupplierResponsePage s = new SupplierResponsePage();
        s.setSupplierResponses(list);
        s.setPageNumber(page.getNumber());
        s.setLastPage(page.isLast());
        s.setTotalPages(page.getTotalPages());
        s.setPageSize(page.getSize());
        s.setTotalElements(page.getTotalElements());

        return s;

    }
}
