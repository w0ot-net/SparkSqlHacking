package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface VectorLike$mcS$sp extends VectorLike {
   // $FF: synthetic method
   static Object map$(final VectorLike$mcS$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcS$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcS$sp$(final VectorLike$mcS$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcS$sp(fn, canMapValues);
   }

   default Object map$mcS$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike$mcS$sp $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.foreach$mcS$sp(fn);
   }

   // $FF: synthetic method
   static void foreach$mcS$sp$(final VectorLike$mcS$sp $this, final Function1 fn) {
      $this.foreach$mcS$sp(fn);
   }

   default void foreach$mcS$sp(final Function1 fn) {
      this.values().foreach(fn);
   }
}
