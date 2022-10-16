package com.costume.model.custom;

import com.costume.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountClient {
    private Long total;
    private Client client;
}
