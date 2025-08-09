package com.univocity.parsers.common;

public final class NoopProcessorErrorHandler implements ProcessorErrorHandler {
   public static final ProcessorErrorHandler instance = new NoopProcessorErrorHandler();

   private NoopProcessorErrorHandler() {
   }

   public void handleError(DataProcessingException error, Object[] inputRow, Context context) {
      throw error;
   }
}
