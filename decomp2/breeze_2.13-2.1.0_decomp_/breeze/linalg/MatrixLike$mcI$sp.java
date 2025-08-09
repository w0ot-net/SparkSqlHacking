package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface MatrixLike$mcI$sp extends MatrixLike {
   // $FF: synthetic method
   static Object map$(final MatrixLike$mcI$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcI$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcI$sp$(final MatrixLike$mcI$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcI$sp(fn, canMapValues);
   }

   default Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }
}
