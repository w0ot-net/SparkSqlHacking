package breeze.linalg.support;

import breeze.util.ArrayUtil$;
import scala.runtime.BoxedUnit;

public class CanCopy$OpArray$mcV$sp extends CanCopy.OpArray {
   public BoxedUnit[] apply(final BoxedUnit[] from) {
      return this.apply$mcV$sp(from);
   }

   public BoxedUnit[] apply$mcV$sp(final BoxedUnit[] from) {
      return (BoxedUnit[])ArrayUtil$.MODULE$.copyOf(from, from.length);
   }
}
