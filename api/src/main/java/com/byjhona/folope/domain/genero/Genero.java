package com.byjhona.folope.domain.genero;

import jakarta.persistence.Entity;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genero {
    Long id;
    String name;
}
