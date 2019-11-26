package com.eurail.test.app.systems;

/**
 * Created  2019-07-17 16:06
 * Copyright (c) 2019 Eurail.com B.V.
 *
 * @author Radion, Rodion.Kyryliak@eurail.com
 */

import com.eurail.test.app.entities.Order;
import com.eurail.test.app.exceptions.NotPerformedException;
import com.eurail.test.app.exceptions.NotValidContentException;
import com.eurail.test.app.exceptions.OrderNotFoundException;
import com.eurail.test.app.exceptions.OrderNotInValidStateException;
import com.eurail.test.app.utils.Converter;
import com.eurail.test.app.utils.HttpExecutor;
import org.apache.http.HttpResponse;

/**
 * System-A
 */
public class OrderService {
    private static final String BASE_URL = System.getenv("BASE_URL");
    private static final HttpExecutor HTTP_EXECUTOR = new HttpExecutor();
    private static final int OK = 200;
    private static final int CREATED = 201;
    private static final int NO_CONTENT = 204;
    private static final int BAD_REQUEST = 400;
    private static final int NOT_FOUND = 404;
    private static final int CONFLICT = 409;

    /**
     * Creates order.
     *
     * @param title title
     *
     * @return order
     *
     * @exception NotPerformedException if order not created.
     */
    public Order createOrder(String title) {
        HttpResponse response = HTTP_EXECUTOR.execPost(BASE_URL + "\\" + "orders", Converter.OrderToString(new Order(title)));
        int statusCode = HTTP_EXECUTOR.getHttpStatusCode(response);
        switch (statusCode) {
            case CREATED:
                return Converter.StringToOrder(HTTP_EXECUTOR.getBodyAsString(response));
            default:
                throw new NotPerformedException();
        }
    }

    /**
     * Gets order.
     *
     * @param orderUuid
     *
     * @return order
     *
     * @exception OrderNotFoundException if order not found.
     */
    public Order getOrder(String orderUuid) {
        HttpResponse response = HTTP_EXECUTOR.execGet(BASE_URL + "\\" + "orders" + "\\" + orderUuid);
        int statusCode = HTTP_EXECUTOR.getHttpStatusCode(response);
        switch (statusCode) {
            case OK:
                return Converter.StringToOrder(HTTP_EXECUTOR.getBodyAsString(response));
            case NOT_FOUND:
                throw new OrderNotFoundException();
            default:
                throw new NotPerformedException();
        }
    }

    /**
     * Delete orders
     *
     * @param orderUuid order uuid
     *
     * @exception NotPerformedException if order not deleted.
     * @exception OrderNotFoundException if order not found.
     */
    public void deleteOrder(String orderUuid) {
        HttpResponse response = HTTP_EXECUTOR.execDelete(BASE_URL + "\\" + "orders" + "\\" + orderUuid);
        int statusCode = HTTP_EXECUTOR.getHttpStatusCode(response);
        if (statusCode != NO_CONTENT) {
            processException(statusCode);
        }
    }

    /**
     * Activates order.
     * Only order in NEW state can be activated
     *
     * @param orderUuid order uuid
     *
     * @exception NotPerformedException if order not deleted.
     * @exception OrderNotInValidStateException if order not if valid state for activation.
     * @exception OrderNotFoundException if order not found.
     */
    public void activateOrder(String orderUuid) {
        HttpResponse response = HTTP_EXECUTOR.execPost(BASE_URL + "\\" + "orders" + "\\" + orderUuid + "\\" + "activate");
        int statusCode = HTTP_EXECUTOR.getHttpStatusCode(response);
        if (statusCode != OK) {
            processException(statusCode);
        }
    }

    /**
     * Cancels order.
     * Only order in ACTIVATED state can be cancelled
     *
     * @param orderUuid order uuid
     *
     * @exception NotPerformedException if order not deleted.
     * @exception OrderNotInValidStateException if order not if valid state for cancellation.
     * @exception OrderNotFoundException if order not found.
     */
    public void cancelOrder(String orderUuid) {
        HttpResponse response = HTTP_EXECUTOR.execPost(BASE_URL + "\\" + "orders" + "\\" + orderUuid + "\\" + "cancel");
        int statusCode = HTTP_EXECUTOR.getHttpStatusCode(response);
        if (statusCode != OK) {
            processException(statusCode);
        }
    }

    private void processException(int httpStatusCode) {
        switch (httpStatusCode) {
            case BAD_REQUEST:
                throw new NotValidContentException();
            case NOT_FOUND:
                throw new OrderNotFoundException();
            case CONFLICT:
                throw new OrderNotInValidStateException();
            default:
                throw new NotPerformedException();
        }
    }

}
