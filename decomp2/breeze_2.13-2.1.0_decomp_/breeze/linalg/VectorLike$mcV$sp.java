package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface VectorLike$mcV$sp extends VectorLike {
   // $FF: synthetic method
   static Object map$(final VectorLike$mcV$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcV$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcV$sp$(final VectorLike$mcV$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcV$sp(fn, canMapValues);
   }

   default Object map$mcV$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike$mcV$sp $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.foreach$mcV$sp(fn);
   }

   // $FF: synthetic method
   static void foreach$mcV$sp$(final VectorLike$mcV$sp $this, final Function1 fn) {
      $this.foreach$mcV$sp(fn);
   }

   default void foreach$mcV$sp(final Function1 fn) {
      this.values().foreach(fn);
   }
}
