package org.sparkproject.spark_core.protobuf;

public final class ProtobufToStringOutput {
   private static final ThreadLocal outputMode = new ThreadLocal() {
      protected OutputMode initialValue() {
         return ProtobufToStringOutput.OutputMode.TEXT_FORMAT;
      }
   };

   private ProtobufToStringOutput() {
   }

   @CanIgnoreReturnValue
   private static OutputMode setOutputMode(OutputMode newMode) {
      OutputMode oldMode = (OutputMode)outputMode.get();
      outputMode.set(newMode);
      return oldMode;
   }

   private static void callWithSpecificFormat(Runnable impl, OutputMode mode) {
      OutputMode oldMode = setOutputMode(mode);

      try {
         impl.run();
      } finally {
         OutputMode var5 = setOutputMode(oldMode);
      }

   }

   public static void callWithDebugFormat(Runnable impl) {
      callWithSpecificFormat(impl, ProtobufToStringOutput.OutputMode.DEBUG_FORMAT);
   }

   public static void callWithTextFormat(Runnable impl) {
      callWithSpecificFormat(impl, ProtobufToStringOutput.OutputMode.TEXT_FORMAT);
   }

   public static boolean shouldOutputDebugFormat() {
      return outputMode.get() == ProtobufToStringOutput.OutputMode.DEBUG_FORMAT;
   }

   private static enum OutputMode {
      DEBUG_FORMAT,
      TEXT_FORMAT;

      // $FF: synthetic method
      private static OutputMode[] $values() {
         return new OutputMode[]{DEBUG_FORMAT, TEXT_FORMAT};
      }
   }
}
