package org.apache.curator.shaded.com.google.common.cache;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public enum RemovalCause {
   EXPLICIT {
      boolean wasEvicted() {
         return false;
      }
   },
   REPLACED {
      boolean wasEvicted() {
         return false;
      }
   },
   COLLECTED {
      boolean wasEvicted() {
         return true;
      }
   },
   EXPIRED {
      boolean wasEvicted() {
         return true;
      }
   },
   SIZE {
      boolean wasEvicted() {
         return true;
      }
   };

   private RemovalCause() {
   }

   abstract boolean wasEvicted();

   // $FF: synthetic method
   private static RemovalCause[] $values() {
      return new RemovalCause[]{EXPLICIT, REPLACED, COLLECTED, EXPIRED, SIZE};
   }
}
