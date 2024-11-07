package br.com.actionlabs.carboncalc.model;

import br.com.actionlabs.carboncalc.enums.TransportationType;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Transportation {
  private TransportationType type;
  private int monthlyDistance;
}
