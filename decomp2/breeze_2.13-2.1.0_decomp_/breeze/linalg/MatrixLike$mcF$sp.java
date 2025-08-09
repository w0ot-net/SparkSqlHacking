package breeze.linalg;

import breeze.linalg.support.CanMapValues;
import scala.Function1;

public interface MatrixLike$mcF$sp extends MatrixLike {
   // $FF: synthetic method
   static Object map$(final MatrixLike$mcF$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map(fn, canMapValues);
   }

   default Object map(final Function1 fn, final CanMapValues canMapValues) {
      return this.map$mcF$sp(fn, canMapValues);
   }

   // $FF: synthetic method
   static Object map$mcF$sp$(final MatrixLike$mcF$sp $this, final Function1 fn, final CanMapValues canMapValues) {
      return $this.map$mcF$sp(fn, canMapValues);
   }

   default Object map$mcF$sp(final Function1 fn, final CanMapValues canMapValues) {
      return this.values().map(fn, canMapValues);
   }
}
