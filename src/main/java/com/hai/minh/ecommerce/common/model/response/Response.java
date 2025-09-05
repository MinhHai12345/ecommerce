package com.hai.minh.ecommerce.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4006066927468782730L;

    private T data;
    private List<Error> errors;
    private Pageable page;

    @Builder.Default
    @Setter(AccessLevel.NONE)
    private ZonedDateTime timestamp = ZonedDateTime.now();
}


