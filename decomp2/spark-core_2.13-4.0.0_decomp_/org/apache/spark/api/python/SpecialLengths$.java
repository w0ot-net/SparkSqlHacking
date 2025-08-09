package org.apache.spark.api.python;

public final class SpecialLengths$ {
   public static final SpecialLengths$ MODULE$ = new SpecialLengths$();
   private static final int END_OF_DATA_SECTION = -1;
   private static final int PYTHON_EXCEPTION_THROWN = -2;
   private static final int TIMING_DATA = -3;
   private static final int END_OF_STREAM = -4;
   private static final int NULL = -5;
   private static final int START_ARROW_STREAM = -6;
   private static final int END_OF_MICRO_BATCH = -7;

   public int END_OF_DATA_SECTION() {
      return END_OF_DATA_SECTION;
   }

   public int PYTHON_EXCEPTION_THROWN() {
      return PYTHON_EXCEPTION_THROWN;
   }

   public int TIMING_DATA() {
      return TIMING_DATA;
   }

   public int END_OF_STREAM() {
      return END_OF_STREAM;
   }

   public int NULL() {
      return NULL;
   }

   public int START_ARROW_STREAM() {
      return START_ARROW_STREAM;
   }

   public int END_OF_MICRO_BATCH() {
      return END_OF_MICRO_BATCH;
   }

   private SpecialLengths$() {
   }
}
