package com.univocity.parsers.common;

final class NoopRowProcessorErrorHandler implements RowProcessorErrorHandler {
   public static final RowProcessorErrorHandler instance = new NoopRowProcessorErrorHandler();

   private NoopRowProcessorErrorHandler() {
   }

   public void handleError(DataProcessingException error, Object[] inputRow, ParsingContext context) {
      throw error;
   }
}
