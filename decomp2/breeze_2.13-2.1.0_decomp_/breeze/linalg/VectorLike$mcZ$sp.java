package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface VectorLike$mcZ$sp extends VectorLike {
   // $FF: synthetic method
   static Object map$(final VectorLike$mcZ$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcZ$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcZ$sp$(final VectorLike$mcZ$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcZ$sp(fn, canMapValues);
   }

   default Object map$mcZ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike$mcZ$sp $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.foreach$mcZ$sp(fn);
   }

   // $FF: synthetic method
   static void foreach$mcZ$sp$(final VectorLike$mcZ$sp $this, final Function1 fn) {
      $this.foreach$mcZ$sp(fn);
   }

   default void foreach$mcZ$sp(final Function1 fn) {
      this.values().foreach(fn);
   }
}
