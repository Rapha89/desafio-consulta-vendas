package com.devsuperior.dsmeta.dto;

public class SalesSumDTO {

    private String sellerName;
    private Double total;

    public SalesSumDTO() {
    }

    public SalesSumDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getSum() {
        return total;
    }

    public void setSum(Double total) {
        this.total = total;
    }
}
