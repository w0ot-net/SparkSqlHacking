package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface VectorLike$mcC$sp extends VectorLike {
   // $FF: synthetic method
   static Object map$(final VectorLike$mcC$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcC$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcC$sp$(final VectorLike$mcC$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcC$sp(fn, canMapValues);
   }

   default Object map$mcC$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }

   // $FF: synthetic method
   static void foreach$(final VectorLike$mcC$sp $this, final Function1 fn) {
      $this.foreach(fn);
   }

   default void foreach(final Function1 fn) {
      this.foreach$mcC$sp(fn);
   }

   // $FF: synthetic method
   static void foreach$mcC$sp$(final VectorLike$mcC$sp $this, final Function1 fn) {
      $this.foreach$mcC$sp(fn);
   }

   default void foreach$mcC$sp(final Function1 fn) {
      this.values().foreach(fn);
   }
}
