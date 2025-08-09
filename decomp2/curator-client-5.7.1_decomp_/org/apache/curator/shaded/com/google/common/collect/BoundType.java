package org.apache.curator.shaded.com.google.common.collect;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public enum BoundType {
   OPEN(false),
   CLOSED(true);

   final boolean inclusive;

   private BoundType(boolean inclusive) {
      this.inclusive = inclusive;
   }

   static BoundType forBoolean(boolean inclusive) {
      return inclusive ? CLOSED : OPEN;
   }

   // $FF: synthetic method
   private static BoundType[] $values() {
      return new BoundType[]{OPEN, CLOSED};
   }
}
