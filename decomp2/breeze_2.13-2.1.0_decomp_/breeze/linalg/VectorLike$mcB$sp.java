package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface VectorLike$mcB$sp extends VectorLike {
   // $FF: synthetic method
   static Object map$(final VectorLike$mcB$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcB$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcB$sp$(final VectorLike$mcB$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcB$sp(fn, canMapValues);
   }

   default Object map$mcB$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike$mcB$sp $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.foreach$mcB$sp(fn);
   }

   // $FF: synthetic method
   static void foreach$mcB$sp$(final VectorLike$mcB$sp $this, final Function1 fn) {
      $this.foreach$mcB$sp(fn);
   }

   default void foreach$mcB$sp(final Function1 fn) {
      this.values().foreach(fn);
   }
}
