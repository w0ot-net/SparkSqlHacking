package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface VectorLike$mcD$sp extends VectorLike, Tensor$mcID$sp {
   // $FF: synthetic method
   static Object map$(final VectorLike$mcD$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcD$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcD$sp$(final VectorLike$mcD$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcD$sp(fn, canMapValues);
   }

   default Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike$mcD$sp $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.foreach$mcD$sp(fn);
   }

   // $FF: synthetic method
   static void foreach$mcD$sp$(final VectorLike$mcD$sp $this, final Function1 fn) {
      $this.foreach$mcD$sp(fn);
   }

   default void foreach$mcD$sp(final Function1 fn) {
      this.values().foreach(fn);
   }
}
