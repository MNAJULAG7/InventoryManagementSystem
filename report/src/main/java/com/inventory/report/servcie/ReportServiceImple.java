package com.inventory.report.servcie;

import com.inventory.report.dto.ReportProductList;
import com.inventory.report.dto.ReportProductPage;
import com.inventory.sharedfiles.ProductResponsePage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImple implements  ReportServcie{

    @Autowired
    FromProductService fromProductService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ReportProductPage getStocks(Long pageNumber, Long pageSize, String sortBy, String sortDir) {
        ProductResponsePage p = fromProductService.getAvailablestocks(pageNumber,pageSize,sortBy,sortDir).getBody();
        List<ReportProductList> reportProductListList = p.getProductResponse()
                .stream()
                .map(product->{
                    ReportProductList r = modelMapper.map(product,ReportProductList.class);
                    r.setTotalStockPrice(product.getQuantity()*product.getPrice());
                    return  r;
                })
                .toList();
        ReportProductPage res = new ReportProductPage();
        res.setReportProductListList(reportProductListList);
        res.setPageNumber(p.getPageNumber());
        res.setPageSize(p.getPageSize());
        res.setTotalPages(p.getTotalPages());
        res.setTotalElements(p.getTotalElements());
        res.setLastPage(p.isLastPage());

        return res;
    }
}
