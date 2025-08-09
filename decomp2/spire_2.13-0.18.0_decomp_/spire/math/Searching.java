package spire.math;

import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.collection.Iterable;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eq!\u0002\u0005\n\u0011\u0003qa!\u0002\t\n\u0011\u0003\t\u0002\"\u0002\r\u0002\t\u0003I\u0002\"\u0002\u000e\u0002\t\u000bY\u0002\"\u0002\u000e\u0002\t\u000bI\u0005\"\u0002\u000e\u0002\t\u000bI\u0006\"\u0002\u000e\u0002\t\u000ba\u0007\"\u0002>\u0002\t\u000bY\u0018!C*fCJ\u001c\u0007.\u001b8h\u0015\tQ1\"\u0001\u0003nCRD'\"\u0001\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011q\"A\u0007\u0002\u0013\tI1+Z1sG\"LgnZ\n\u0003\u0003I\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u000f\u0003\u0019\u0019X-\u0019:dQV\u0011A$\u000e\u000b\u0004;\t;EC\u0001\u0010\"!\t\u0019r$\u0003\u0002!)\t\u0019\u0011J\u001c;\t\u000f\t\u001a\u0011\u0011!a\u0002G\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007\u0011\u00024G\u0004\u0002&[9\u0011ae\u000b\b\u0003O)j\u0011\u0001\u000b\u0006\u0003S5\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0007\n\u00051Z\u0011aB1mO\u0016\u0014'/Y\u0005\u0003]=\nq\u0001]1dW\u0006<WM\u0003\u0002-\u0017%\u0011\u0011G\r\u0002\u0006\u001fJ$WM\u001d\u0006\u0003]=\u0002\"\u0001N\u001b\r\u0001\u0011Iag\u0001Q\u0001\u0002\u0003\u0015\ra\u000e\u0002\u0002\u0003F\u0011\u0001h\u000f\t\u0003'eJ!A\u000f\u000b\u0003\u000f9{G\u000f[5oOB\u00111\u0003P\u0005\u0003{Q\u00111!\u00118zQ\t)t\b\u0005\u0002\u0014\u0001&\u0011\u0011\t\u0006\u0002\fgB,7-[1mSj,G\rC\u0003D\u0007\u0001\u0007A)\u0001\u0002bgB\u00191#R\u001a\n\u0005\u0019#\"!B!se\u0006L\b\"\u0002%\u0004\u0001\u0004\u0019\u0014\u0001B5uK6,\"A\u0013)\u0015\u000b-\u0013F+V,\u0015\u0005ya\u0005bB'\u0005\u0003\u0003\u0005\u001dAT\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u0001\u00131\u001fB\u0011A\u0007\u0015\u0003\nm\u0011\u0001\u000b\u0011!AC\u0002]B#\u0001U \t\u000b\r#\u0001\u0019A*\u0011\u0007M)u\nC\u0003I\t\u0001\u0007q\nC\u0003W\t\u0001\u0007a$A\u0003m_^,'\u000fC\u0003Y\t\u0001\u0007a$A\u0003vaB,'/\u0006\u0002[AR\u00191LY6\u0015\u0005ya\u0006bB/\u0006\u0003\u0003\u0005\u001dAX\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004c\u0001\u00131?B\u0011A\u0007\u0019\u0003\nm\u0015\u0001\u000b\u0011!AC\u0002]B#\u0001Y \t\u000b\r+\u0001\u0019A2\u0011\u0007\u0011DwL\u0004\u0002fO:\u0011qEZ\u0005\u0002+%\u0011a\u0006F\u0005\u0003S*\u0014!\"\u00138eKb,GmU3r\u0015\tqC\u0003C\u0003I\u000b\u0001\u0007q,\u0006\u0002ngR)a.^<ysR\u0011ad\u001c\u0005\ba\u001a\t\t\u0011q\u0001r\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0004IA\u0012\bC\u0001\u001bt\t%1d\u0001)A\u0001\u0002\u000b\u0007q\u0007\u000b\u0002t\u007f!)1I\u0002a\u0001mB\u0019A\r\u001b:\t\u000b!3\u0001\u0019\u0001:\t\u000bY3\u0001\u0019\u0001\u0010\t\u000ba3\u0001\u0019\u0001\u0010\u0002\u001f5Lg.[7bY\u0016cW-\\3oiN,2\u0001`A\u0003)\ri\u0018\u0011\u0003\u000b\u0004}\u0006\u001d\u0001\u0003\u00023\u0000\u0003\u0007I1!!\u0001k\u0005\r\u0019V-\u001d\t\u0004i\u0005\u0015A!\u0002\u001c\b\u0005\u00049\u0004bBA\u0005\u000f\u0001\u000f\u00111B\u0001\u0003KZ\u0004R\u0001JA\u0007\u0003\u0007I1!a\u00043\u00051\u0001\u0016M\u001d;jC2|%\u000fZ3s\u0011\u0019\u0019u\u00011\u0001\u0002\u0014A)A-!\u0006\u0002\u0004%\u0019\u0011q\u00036\u0003\u0011%#XM]1cY\u0016\u0004"
)
public final class Searching {
   public static Seq minimalElements(final Iterable as, final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(as, ev);
   }

   public static int search(final IndexedSeq as, final Object item, final int lower, final int upper, final Order evidence$4) {
      return Searching$.MODULE$.search(as, item, lower, upper, evidence$4);
   }

   public static int search(final IndexedSeq as, final Object item, final Order evidence$3) {
      return Searching$.MODULE$.search(as, item, evidence$3);
   }

   public static int search(final Object as, final Object item, final int lower, final int upper, final Order evidence$2) {
      return Searching$.MODULE$.search(as, item, lower, upper, evidence$2);
   }

   public static int search(final Object as, final Object item, final Order evidence$1) {
      return Searching$.MODULE$.search(as, item, evidence$1);
   }
}
