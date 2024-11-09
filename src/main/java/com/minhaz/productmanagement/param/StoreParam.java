package com.minhaz.productmanagement.param;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreParam {
    private Long storeId;
    @NotNull
    private String name;
    private String location;
}
