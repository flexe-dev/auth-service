package com.flexe.authservice.entity.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public record ErrorResponse(HttpStatus statusCode, String message) {

}