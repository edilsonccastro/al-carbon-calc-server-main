package br.com.actionlabs.carboncalc.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateCalcInfoResponseDTO {
  @NonNull
  private Boolean success;
}
