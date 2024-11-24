package com.minhaz.productmanagement.param;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ThirdPartyApiResponseDto<S> implements Serializable {
    private int statusCode;
    private String message;
    private int contentCount;
    private Map<String, Object> contents;
}
