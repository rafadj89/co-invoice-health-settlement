package com.reven.rips.application.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TaxDTO implements Serializable {

  private int id;
  private String taxId;
  private String name;
  private Double percent;
}
