package org.apache.curator.shaded.com.google.common.escape;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

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
      return (char[])DEST_TL.get();
   }
}
