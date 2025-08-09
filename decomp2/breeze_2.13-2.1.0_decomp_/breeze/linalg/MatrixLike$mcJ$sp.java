package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface MatrixLike$mcJ$sp extends MatrixLike {
   // $FF: synthetic method
   static Object map$(final MatrixLike$mcJ$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcJ$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcJ$sp$(final MatrixLike$mcJ$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcJ$sp(fn, canMapValues);
   }

   default Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }
}
