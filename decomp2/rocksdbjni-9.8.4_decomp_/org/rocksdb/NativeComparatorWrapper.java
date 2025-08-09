package org.rocksdb;

import java.nio.ByteBuffer;

public abstract class NativeComparatorWrapper extends AbstractComparator {
   static final String NATIVE_CODE_IMPLEMENTATION_SHOULD_NOT_BE_CALLED = "This should not be called. Implementation is in Native code";

   final ComparatorType getComparatorType() {
      return ComparatorType.JAVA_NATIVE_COMPARATOR_WRAPPER;
   }

   public final String name() {
      throw new IllegalStateException("This should not be called. Implementation is in Native code");
   }

   public final int compare(ByteBuffer var1, ByteBuffer var2) {
      throw new IllegalStateException("This should not be called. Implementation is in Native code");
   }

   public final void findShortestSeparator(ByteBuffer var1, ByteBuffer var2) {
      throw new IllegalStateException("This should not be called. Implementation is in Native code");
   }

   public final void findShortSuccessor(ByteBuffer var1) {
      throw new IllegalStateException("This should not be called. Implementation is in Native code");
   }

   protected void disposeInternal() {
      disposeInternal(this.nativeHandle_);
   }

   private static native void disposeInternal(long var0);
}
