package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import java.util.Objects;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class Platform {
   private static final ThreadLocal DEST_TL = new ThreadLocal() {
      protected char[] initialValue() {
         return new char[1024];
      }
   };

   private Platform() {
   }

   static char[] charBufferFromThreadLocal() {
      return (char[])Objects.requireNonNull((char[])DEST_TL.get());
   }
}
