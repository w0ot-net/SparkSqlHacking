package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface VectorLike$mcJ$sp extends VectorLike, Tensor$mcIJ$sp {
   // $FF: synthetic method
   static Object map$(final VectorLike$mcJ$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcJ$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcJ$sp$(final VectorLike$mcJ$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcJ$sp(fn, canMapValues);
   }

   default Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike$mcJ$sp $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.foreach$mcJ$sp(fn);
   }

   // $FF: synthetic method
   static void foreach$mcJ$sp$(final VectorLike$mcJ$sp $this, final Function1 fn) {
      $this.foreach$mcJ$sp(fn);
   }

   default void foreach$mcJ$sp(final Function1 fn) {
      this.values().foreach(fn);
   }
}
