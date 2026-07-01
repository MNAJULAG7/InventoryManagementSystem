package com.inventory.report.servcie;

import com.inventory.report.dto.ReportProductList;
import com.inventory.report.dto.ReportProductPage;
import com.inventory.report.dto.ReportPurchaseResponse;
import com.inventory.report.dto.ReportSaleDateResponse;
import com.inventory.sharedfiles.ProductResponsePage;
import com.inventory.sharedfiles.PurchaseResponse;
import com.inventory.sharedfiles.SaleReportResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImple implements  ReportServcie{

    @Autowired
    FromProductService fromProductService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FromSalesService fromSalesService;

    @Autowired
    FromPurchaseService fromPurchaseService;

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

    @Override
    public List<SaleReportResponse> getSales() {
        List<SaleReportResponse> s = fromSalesService.getSaleForReport().getBody();
        return s;
    }

    @Override
    public ReportSaleDateResponse getSaleDate(LocalDate start, LocalDate end) {
        List<SaleReportResponse> s = fromSalesService.getSaleBetweendatesForReport(start,end).getBody();
        ReportSaleDateResponse r = new ReportSaleDateResponse();
        r.setStartDate(start);
        r.setEndDate(end);
        r.setSaleReportResponseList(s);

        Double total = 0.;

        for(SaleReportResponse sale: s)
        {
            total+=sale.getTotalPrice();
        }
        r.setTotalSalesAmount(total);
        return r;
    }

    @Override
    public ReportPurchaseResponse getPurchases() {
        List<PurchaseResponse> p =  fromPurchaseService.getAll().getBody();
        Double total = 0.;
        for(PurchaseResponse pr : p)
        {
            total+= pr.getTotalPrice() == null
                    ? 0
                    : pr.getTotalPrice();
        }
        ReportPurchaseResponse rpr = new ReportPurchaseResponse();
        rpr.setPurchaseResponse(p);
        rpr.setTotalPurchases(total);
        return rpr;
    }

}
