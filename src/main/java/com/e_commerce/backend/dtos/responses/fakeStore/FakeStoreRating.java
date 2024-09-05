package com.e_commerce.backend.dtos.responses.fakeStore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FakeStoreRating {

    private double rate;
    private int count;
}
