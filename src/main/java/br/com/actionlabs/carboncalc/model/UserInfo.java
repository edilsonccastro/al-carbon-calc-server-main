package br.com.actionlabs.carboncalc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("userInfo")
public class UserInfo {
  @Id
  private String id;
  private String name;
  private String email;
  private String uf;
  private String phoneNumber;
}
