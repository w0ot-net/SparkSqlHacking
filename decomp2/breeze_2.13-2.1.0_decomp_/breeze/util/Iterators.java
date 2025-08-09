package breeze.util;

import scala.Function0;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00059;Q!\u0002\u0004\t\u0002-1Q!\u0004\u0004\t\u00029AQ!F\u0001\u0005\u0002YAQaF\u0001\u0005\u0002aAQ!O\u0001\u0005\u0002i\n\u0011\"\u0013;fe\u0006$xN]:\u000b\u0005\u001dA\u0011\u0001B;uS2T\u0011!C\u0001\u0007EJ,WM_3\u0004\u0001A\u0011A\"A\u0007\u0002\r\tI\u0011\n^3sCR|'o]\n\u0003\u0003=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\f\u000311'o\\7Qe>$WoY3s+\tI\u0002\u0006\u0006\u0002\u001bcA\u00191d\t\u0014\u000f\u0005q\tcBA\u000f!\u001b\u0005q\"BA\u0010\u000b\u0003\u0019a$o\\8u}%\t!#\u0003\u0002##\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0013&\u0005!IE/\u001a:bi>\u0014(B\u0001\u0012\u0012!\t9\u0003\u0006\u0004\u0001\u0005\u000b%\u001a!\u0019\u0001\u0016\u0003\u0003\u0015\u000b\"a\u000b\u0018\u0011\u0005Aa\u0013BA\u0017\u0012\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001E\u0018\n\u0005A\n\"aA!os\"1!g\u0001CA\u0002M\nA\u0001\u001d:pIB\u0019\u0001\u0003\u000e\u001c\n\u0005U\n\"\u0001\u0003\u001fcs:\fW.\u001a \u0011\u0007A9d%\u0003\u00029#\t1q\n\u001d;j_:\fQ!\\3sO\u0016,\"aO \u0015\u0005qJECA\u001fB!\rY2E\u0010\t\u0003O}\"Q\u0001\u0011\u0003C\u0002)\u0012\u0011\u0001\u0016\u0005\u0006\u0005\u0012\u0001\raQ\u0001\bG>l\u0007/\u0019:f!\u0015\u0001BI\u0010 G\u0013\t)\u0015CA\u0005Gk:\u001cG/[8oeA\u0011\u0001cR\u0005\u0003\u0011F\u00111!\u00138u\u0011\u0015QE\u00011\u0001L\u0003\u0015IG/\u001a:t!\r\u0001B*P\u0005\u0003\u001bF\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?\u0001"
)
public final class Iterators {
   public static Iterator merge(final Seq iters, final Function2 compare) {
      return Iterators$.MODULE$.merge(iters, compare);
   }

   public static Iterator fromProducer(final Function0 prod) {
      return Iterators$.MODULE$.fromProducer(prod);
   }
}
