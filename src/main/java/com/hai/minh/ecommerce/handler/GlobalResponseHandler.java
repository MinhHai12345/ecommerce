package com.hai.minh.ecommerce.handler;

import com.hai.minh.ecommerce.common.model.response.Pageable;
import com.hai.minh.ecommerce.common.model.response.Response;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull final MethodParameter returnType,
                            @NonNull final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull final MethodParameter returnType,
                                  @NonNull final MediaType selectedContentType,
                                  @NonNull final Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull final ServerHttpRequest request, @NonNull final ServerHttpResponse response) {
        if (body instanceof Response || body instanceof ByteArrayResource) {
            return body;
        }
        if (body instanceof Page<?> page) {
            return Response.builder()
                    .data(page.getContent())
                    .page(new Pageable(page.getTotalElements(), page.getNumber(), page.getSize(),
                            page.getTotalPages()))
                    .build();
        }

        return Response.builder()
                .data(body)
                .build();
    }
}
