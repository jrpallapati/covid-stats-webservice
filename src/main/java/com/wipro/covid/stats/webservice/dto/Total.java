package com.wipro.covid.stats.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Total {
  private String location;
  private ZonedDateTime lastChecked;
  private ZonedDateTime lastReported;
  private Integer confirmed;
  private Integer deaths;
  private Integer recovered;
}
