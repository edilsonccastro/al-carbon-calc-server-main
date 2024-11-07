package br.com.actionlabs.carboncalc.model;

import lombok.Data;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("calcInfo")
public class CalcInfo {
  @Id
  private String id;
  private int energyConsumption;
  private List<Transportation> transportation;
  private int solidWasteTotal;
  private double recyclePercentage;
}
