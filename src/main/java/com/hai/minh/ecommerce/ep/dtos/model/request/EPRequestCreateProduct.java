package com.hai.minh.ecommerce.ep.dtos.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class EPRequestCreateProduct implements Serializable {

    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("type")
        private String type;
        @JsonProperty("name")
        private String name;
        @JsonProperty("slug")
        private String slug;
        @JsonProperty("sku")
        private String sku;
        @JsonProperty("description")
        private String description;
        @JsonProperty("manage_stock")
        private Boolean manageStock;
        @JsonProperty("price")
        private List<PriceDTO> price;
        @JsonProperty("status")
        private String status;
        @JsonProperty("commodity_type")
        private String commodityType;

        @NoArgsConstructor
        @Data
        public static class PriceDTO {
            @JsonProperty("amount")
            private Integer amount;
            @JsonProperty("currency")
            private String currency;
            @JsonProperty("includes_tax")
            private Boolean includesTax;
        }
    }
}