package org.apache.spark.api.python;

public final class BarrierTaskContextMessageProtocol$ {
   public static final BarrierTaskContextMessageProtocol$ MODULE$ = new BarrierTaskContextMessageProtocol$();
   private static final int BARRIER_FUNCTION = 1;
   private static final int ALL_GATHER_FUNCTION = 2;
   private static final String BARRIER_RESULT_SUCCESS = "success";
   private static final String ERROR_UNRECOGNIZED_FUNCTION = "Not recognized function call from python side.";

   public int BARRIER_FUNCTION() {
      return BARRIER_FUNCTION;
   }

   public int ALL_GATHER_FUNCTION() {
      return ALL_GATHER_FUNCTION;
   }

   public String BARRIER_RESULT_SUCCESS() {
      return BARRIER_RESULT_SUCCESS;
   }

   public String ERROR_UNRECOGNIZED_FUNCTION() {
      return ERROR_UNRECOGNIZED_FUNCTION;
   }

   private BarrierTaskContextMessageProtocol$() {
   }
}
