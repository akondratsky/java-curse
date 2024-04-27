package io.github.akondratsky.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Currency {
    @JsonProperty
    private int id;

    @JsonProperty
    private String fullName;

    @JsonProperty
    private String isoName;
}
