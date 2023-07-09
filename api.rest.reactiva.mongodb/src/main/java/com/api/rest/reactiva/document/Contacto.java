package com.api.rest.reactiva.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "contacto")
public class Contacto {

  @Id
  private String id;
  private String nombre;
  private String email;
  private String telefono;
}
