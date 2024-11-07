package br.com.actionlabs.carboncalc.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class StartCalcRequestDTO {
  @NonNull
  private String name;
  @NonNull
  private String email;
  @NonNull
  private String uf;
  @NonNull
  private String phoneNumber;
}
