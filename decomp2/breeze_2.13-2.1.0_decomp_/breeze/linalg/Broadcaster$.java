package breeze.linalg;

import breeze.linalg.support.CanCollapseAxis;
import breeze.linalg.support.CanSlice2;
import scala.package.;

public final class Broadcaster$ {
   public static final Broadcaster$ MODULE$ = new Broadcaster$();

   public CanSlice2 canBroadcastSliceColumns(final CanSlice2 cs2_$colon$colon, final CanCollapseAxis.HandHold handhold) {
      return new CanSlice2(cs2_$colon$colon) {
         private final CanSlice2 cs2_$colon$colon$1;

         public BroadcastedColumns apply(final Object from, final Object slice, final $times$ slice2) {
            return new BroadcastedColumns(this.cs2_$colon$colon$1.apply(from, slice, .MODULE$.$colon$colon()));
         }

         public {
            this.cs2_$colon$colon$1 = cs2_$colon$colon$1;
         }
      };
   }

   public CanSlice2 canBroadcastColumns(final CanCollapseAxis.HandHold handhold) {
      return new CanSlice2() {
         public BroadcastedColumns apply(final Object from, final scala.collection.immutable..colon.colon slice, final $times$ slice2) {
            return new BroadcastedColumns(from);
         }
      };
   }

   public CanSlice2 canBroadcastSliceRows(final CanSlice2 cs2_$colon$colon, final CanCollapseAxis.HandHold handhold) {
      return new CanSlice2(cs2_$colon$colon) {
         private final CanSlice2 cs2_$colon$colon$2;

         public BroadcastedRows apply(final Object from, final $times$ slice2, final Object slice) {
            return new BroadcastedRows(this.cs2_$colon$colon$2.apply(from, .MODULE$.$colon$colon(), slice));
         }

         public {
            this.cs2_$colon$colon$2 = cs2_$colon$colon$2;
         }
      };
   }

   public CanSlice2 canBroadcastRows(final CanCollapseAxis.HandHold handhold) {
      return new CanSlice2() {
         public BroadcastedRows apply(final Object from, final $times$ slice2, final scala.collection.immutable..colon.colon slice) {
            return new BroadcastedRows(from);
         }
      };
   }

   private Broadcaster$() {
   }
}
