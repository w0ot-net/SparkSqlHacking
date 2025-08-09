package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Predef.;

public final class InputFileBlockHolder$ {
   public static final InputFileBlockHolder$ MODULE$ = new InputFileBlockHolder$();
   private static final InheritableThreadLocal inputBlock = new InheritableThreadLocal() {
      public AtomicReference initialValue() {
         return new AtomicReference(new InputFileBlockHolder.FileBlock());
      }
   };

   public void setThreadLocalValue(final Object ref) {
      inputBlock.set((AtomicReference)ref);
   }

   public Object getThreadLocalValue() {
      return inputBlock.get();
   }

   public UTF8String getInputFilePath() {
      return ((InputFileBlockHolder.FileBlock)((AtomicReference)inputBlock.get()).get()).filePath();
   }

   public long getStartOffset() {
      return ((InputFileBlockHolder.FileBlock)((AtomicReference)inputBlock.get()).get()).startOffset();
   }

   public long getLength() {
      return ((InputFileBlockHolder.FileBlock)((AtomicReference)inputBlock.get()).get()).length();
   }

   public void set(final String filePath, final long startOffset, final long length) {
      .MODULE$.require(filePath != null, () -> "filePath cannot be null");
      .MODULE$.require(startOffset >= 0L, () -> "startOffset (" + startOffset + ") cannot be negative");
      .MODULE$.require(length >= -1L, () -> "length (" + length + ") cannot be smaller than -1");
      ((AtomicReference)inputBlock.get()).set(new InputFileBlockHolder.FileBlock(UTF8String.fromString(filePath), startOffset, length));
   }

   public void unset() {
      inputBlock.remove();
   }

   public void initialize() {
      inputBlock.get();
   }

   private InputFileBlockHolder$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
