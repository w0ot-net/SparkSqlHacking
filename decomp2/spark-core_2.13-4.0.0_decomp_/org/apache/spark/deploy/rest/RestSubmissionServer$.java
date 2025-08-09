package org.apache.spark.deploy.rest;

public final class RestSubmissionServer$ {
   public static final RestSubmissionServer$ MODULE$ = new RestSubmissionServer$();
   private static final String PROTOCOL_VERSION;
   private static final int SC_UNKNOWN_PROTOCOL_VERSION;

   static {
      PROTOCOL_VERSION = RestSubmissionClient$.MODULE$.PROTOCOL_VERSION();
      SC_UNKNOWN_PROTOCOL_VERSION = 468;
   }

   public String PROTOCOL_VERSION() {
      return PROTOCOL_VERSION;
   }

   public int SC_UNKNOWN_PROTOCOL_VERSION() {
      return SC_UNKNOWN_PROTOCOL_VERSION;
   }

   private RestSubmissionServer$() {
   }
}
