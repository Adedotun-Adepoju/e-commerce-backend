package com.e_commerce.backend.utils;

public class ResponseMessages {
    // Category
    public static final String CREATE_CATEGORY = "Category created successfully";
    public static final String CREATE_MULTIPLE_CATEGORIES = "Categories have been created";
    public static final String FETCH_CATEGORIES = "Categories fetched successfully";

    // Product
    public static final String CREATE_PRODUCT = "Product has been created";
    public static final String CREATE_MULTIPLE_PRODUCTS = "Products have been created";
    public static final String FETCH_ALL_PRODUCTS = "Products have been fetched";
    public static final String FETCH_PRODUCT = "Product has been fetched";

    // Cart
    public static final String CREATE_CART = "Cart created successfully";
    public static final String GET_CART_DETAILS = "Cart details have been fetched";
    public static final String ADD_PRODUCT_TO_CART = "Product has been added to cart";
    public static final String FETCH_CART_PRODUCTS = "Carts products have been fetched";
    public static final String REMOVE_CART_PRODUCT = "Product has been removed from cart";
    public static final String UPDATE_CART_ITEM_QUANTITY = "Product Quantity has been updated";

    private ResponseMessages() {

    }
}
