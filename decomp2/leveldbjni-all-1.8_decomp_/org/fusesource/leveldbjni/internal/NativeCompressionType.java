package org.fusesource.leveldbjni.internal;

public enum NativeCompressionType {
   kNoCompression(0),
   kSnappyCompression(1);

   static final int t = kNoCompression.value;
   final int value;

   private NativeCompressionType(int value) {
      this.value = value;
   }
}
