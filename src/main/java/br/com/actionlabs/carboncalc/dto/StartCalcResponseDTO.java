package br.com.actionlabs.carboncalc.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class StartCalcResponseDTO {
  @NonNull
  private String id;
}
