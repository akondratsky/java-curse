package io.github.akondratsky.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Currency {
    private int id;
    private String fullName;
    private String isoName;
}
