package com.ibm.icu.text;

import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.Norm2AllModes;
import com.ibm.icu.util.ICUUncheckedIOException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public abstract class Normalizer2 {
   public static Normalizer2 getNFCInstance() {
      return Norm2AllModes.getNFCInstance().comp;
   }

   public static Normalizer2 getNFDInstance() {
      return Norm2AllModes.getNFCInstance().decomp;
   }

   public static Normalizer2 getNFKCInstance() {
      return Norm2AllModes.getNFKCInstance().comp;
   }

   public static Normalizer2 getNFKDInstance() {
      return Norm2AllModes.getNFKCInstance().decomp;
   }

   public static Normalizer2 getNFKCCasefoldInstance() {
      return Norm2AllModes.getNFKC_CFInstance().comp;
   }

   public static Normalizer2 getNFKCSimpleCasefoldInstance() {
      return Norm2AllModes.getNFKC_SCFInstance().comp;
   }

   public static Normalizer2 getInstance(InputStream data, String name, Mode mode) {
      ByteBuffer bytes = null;
      if (data != null) {
         try {
            bytes = ICUBinary.getByteBufferFromInputStreamAndCloseStream(data);
         } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
         }
      }

      Norm2AllModes all2Modes = Norm2AllModes.getInstance(bytes, name);
      switch (mode) {
         case COMPOSE:
            return all2Modes.comp;
         case DECOMPOSE:
            return all2Modes.decomp;
         case FCD:
            return all2Modes.fcd;
         case COMPOSE_CONTIGUOUS:
            return all2Modes.fcc;
         default:
            return null;
      }
   }

   public String normalize(CharSequence src) {
      if (src instanceof String) {
         int spanLength = this.spanQuickCheckYes(src);
         if (spanLength == src.length()) {
            return (String)src;
         }

         if (spanLength != 0) {
            StringBuilder sb = (new StringBuilder(src.length())).append(src, 0, spanLength);
            return this.normalizeSecondAndAppend(sb, src.subSequence(spanLength, src.length())).toString();
         }
      }

      return this.normalize(src, new StringBuilder(src.length())).toString();
   }

   public abstract StringBuilder normalize(CharSequence var1, StringBuilder var2);

   public abstract Appendable normalize(CharSequence var1, Appendable var2);

   public abstract StringBuilder normalizeSecondAndAppend(StringBuilder var1, CharSequence var2);

   public abstract StringBuilder append(StringBuilder var1, CharSequence var2);

   public abstract String getDecomposition(int var1);

   public String getRawDecomposition(int c) {
      return null;
   }

   public int composePair(int a, int b) {
      return -1;
   }

   public int getCombiningClass(int c) {
      return 0;
   }

   public abstract boolean isNormalized(CharSequence var1);

   public abstract Normalizer.QuickCheckResult quickCheck(CharSequence var1);

   public abstract int spanQuickCheckYes(CharSequence var1);

   public abstract boolean hasBoundaryBefore(int var1);

   public abstract boolean hasBoundaryAfter(int var1);

   public abstract boolean isInert(int var1);

   /** @deprecated */
   @Deprecated
   protected Normalizer2() {
   }

   public static enum Mode {
      COMPOSE,
      DECOMPOSE,
      FCD,
      COMPOSE_CONTIGUOUS;
   }
}
