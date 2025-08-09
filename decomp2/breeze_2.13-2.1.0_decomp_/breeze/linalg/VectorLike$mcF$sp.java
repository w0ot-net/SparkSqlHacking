package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface VectorLike$mcF$sp extends VectorLike, Tensor$mcIF$sp {
   // $FF: synthetic method
   static Object map$(final VectorLike$mcF$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcF$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcF$sp$(final VectorLike$mcF$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcF$sp(fn, canMapValues);
   }

   default Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike$mcF$sp $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.foreach$mcF$sp(fn);
   }

   // $FF: synthetic method
   static void foreach$mcF$sp$(final VectorLike$mcF$sp $this, final Function1 fn) {
      $this.foreach$mcF$sp(fn);
   }

   default void foreach$mcF$sp(final Function1 fn) {
      this.values().foreach(fn);
   }
}
