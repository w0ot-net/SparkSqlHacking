package io.vertx.ext.web.client.impl;

public enum ClientPhase {
   PREPARE_REQUEST,
   CREATE_REQUEST,
   SEND_REQUEST,
   FOLLOW_REDIRECT,
   RECEIVE_RESPONSE,
   DISPATCH_RESPONSE,
   FAILURE;
}
