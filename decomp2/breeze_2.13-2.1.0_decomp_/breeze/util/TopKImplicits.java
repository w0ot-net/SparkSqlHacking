package breeze.util;

import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQ!F\u0001\u0005\u0002YAQaF\u0001\u0005\u0004aAQAN\u0001\u0005\u0004]\nQ\u0002V8q\u0017&k\u0007\u000f\\5dSR\u001c(BA\u0004\t\u0003\u0011)H/\u001b7\u000b\u0003%\taA\u0019:fKj,7\u0001\u0001\t\u0003\u0019\u0005i\u0011A\u0002\u0002\u000e)>\u00048*S7qY&\u001c\u0017\u000e^:\u0014\u0005\u0005y\u0001C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0017\u0005i\u0011\u000eV8q\u0017&#XM]1cY\u0016,\"!G\u0010\u0015\u0005iA\u0003c\u0001\u0007\u001c;%\u0011AD\u0002\u0002\r)>\u00048*\u0013;fe\u0006\u0014G.\u001a\t\u0003=}a\u0001\u0001B\u0003!\u0007\t\u0007\u0011EA\u0001U#\t\u0011S\u0005\u0005\u0002\u0011G%\u0011A%\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0001b%\u0003\u0002(#\t\u0019\u0011I\\=\t\u000b%\u001a\u0001\u0019\u0001\u0016\u0002\u0011%$XM]1cY\u0016\u00042aK\u001a\u001e\u001d\ta\u0013G\u0004\u0002.a5\taF\u0003\u00020\u0015\u00051AH]8pizJ\u0011AE\u0005\u0003eE\tq\u0001]1dW\u0006<W-\u0003\u00025k\tA\u0011\n^3sC\ndWM\u0003\u00023#\u0005i\u0011\u000eV8q\u0017&#XM]1u_J,\"\u0001O\u001f\u0015\u0005er\u0004c\u0001\u0007;y%\u00111H\u0002\u0002\r)>\u00048*\u0013;fe\u0006$xN\u001d\t\u0003=u\"Q\u0001\t\u0003C\u0002\u0005BQa\u0010\u0003A\u0002\u0001\u000b\u0001\"\u001b;fe\u0006$xN\u001d\t\u0004W\u0005c\u0014B\u0001\"6\u0005!IE/\u001a:bi>\u0014\b"
)
public final class TopKImplicits {
   public static TopKIterator iTopKIterator(final Iterator iterator) {
      return TopKImplicits$.MODULE$.iTopKIterator(iterator);
   }

   public static TopKIterable iTopKIterable(final Iterable iterable) {
      return TopKImplicits$.MODULE$.iTopKIterable(iterable);
   }
}
