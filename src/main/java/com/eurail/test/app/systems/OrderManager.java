package com.eurail.test.app.systems;

/**
 * Created  2019-07-17 16:22
 * Copyright (c) 2019 Eurail.com B.V.
 *
 * @author Radion, Rodion.Kyryliak@eurail.com
 */


/**
 * System-B
 */
public class OrderManager {

    /*
    Create order

    Request
    POST: /orders
    Body:
    {
      "title" : "First Order"
    }

    Response
    Http status code: 201
    Body:
    {
      "title" : "First Order",
      "uuid" : "b1435f4b-b73f-437e-bc11-d3ea292c871d",
      "state" : "NEW"
    }
    or
    Http status code: 400 if title not provided
     */



    /*
    Read order

    Request
    GET: /orders/b1435f4b-b73f-437e-bc11-d3ea292c871d

    Response
    Http status code: 200
    Body:
    {
      "title" : "First Order",
      "uuid" : "b1435f4b-b73f-437e-bc11-d3ea292c871d",
      "state" : "NEW"
    }
    or
    Http status code: 404 if order with provided orderUuid does not exist
     */



    /*
    Delete order

    Request
    DELETE: /orders/{orderUuid}

    Response
    Http status code: 204
    or
    Http status code: 404 if order with provided orderUuid does not exist
     */



    /*
    Activate order

    Request
    POST: /orders/{orderUuid}/activate

    Response
    Http status code: 204
    or
    Http status code: 404 if order with provided orderUuid does not exist
    or
    Http status code: 409 if order not in NEW state
     */



    /*
    Cancel order

    Request
    POST: /orders/{orderUuid}/cancel

    Response
    Http status code: 204
    or
    Http status code: 404 if order with provided orderUuid does not exist
    or
    Http status code: 409 if order not in ACTIVATED state
     */

}
