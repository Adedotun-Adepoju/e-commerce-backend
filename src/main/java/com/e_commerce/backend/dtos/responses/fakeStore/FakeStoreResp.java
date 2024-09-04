package com.e_commerce.backend.dtos.responses.fakeStore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class FakeStoreResp {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private FakeStoreRating rating;
}
