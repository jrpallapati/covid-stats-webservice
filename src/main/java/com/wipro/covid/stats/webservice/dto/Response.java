package com.wipro.covid.stats.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Response<T> {
  private Integer statusCode;
  private String error;
  private String message;
  private T data;
}
