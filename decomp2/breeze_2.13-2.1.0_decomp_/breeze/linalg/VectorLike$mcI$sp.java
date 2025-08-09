package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface VectorLike$mcI$sp extends VectorLike, Tensor$mcII$sp {
   // $FF: synthetic method
   static Object map$(final VectorLike$mcI$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcI$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcI$sp$(final VectorLike$mcI$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcI$sp(fn, canMapValues);
   }

   default Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike$mcI$sp $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.foreach$mcI$sp(fn);
   }

   // $FF: synthetic method
   static void foreach$mcI$sp$(final VectorLike$mcI$sp $this, final Function1 fn) {
      $this.foreach$mcI$sp(fn);
   }

   default void foreach$mcI$sp(final Function1 fn) {
      this.values().foreach(fn);
   }
}
