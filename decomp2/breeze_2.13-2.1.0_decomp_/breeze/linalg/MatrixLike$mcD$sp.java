package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface MatrixLike$mcD$sp extends MatrixLike {
   // $FF: synthetic method
   static Object map$(final MatrixLike$mcD$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcD$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcD$sp$(final MatrixLike$mcD$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcD$sp(fn, canMapValues);
   }

   default Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }
}
