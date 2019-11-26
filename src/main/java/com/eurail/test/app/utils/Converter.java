package com.eurail.test.app.utils;

import com.eurail.test.app.entities.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created  2019-07-17 17:12
 * Copyright (c) 2019 Eurail.com B.V.
 *
 * @author Radion, Rodion.Kyryliak@eurail.com
 */

public class Converter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static String OrderToString(Order order) {
        try {
            return OBJECT_MAPPER.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Order StringToOrder(String body) {
        try {
            return OBJECT_MAPPER.readValue(body, Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
