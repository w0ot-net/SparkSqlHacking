package spire.algebra;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;
import spire.std.SeqLpNormedVectorSpace;
import spire.std.SeqMaxNormedVectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005I4\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006!\u0001!\tA\u0005\u0005\u0006-\u0001!\ta\u0006\u0005\u0006#\u0002!\tA\u0015\u0002\u001b\u001d>\u0014X.\u001a3WK\u000e$xN]*qC\u000e,g)\u001e8di&|gn\u001d\u0006\u0003\r\u001d\tq!\u00197hK\n\u0014\u0018MC\u0001\t\u0003\u0015\u0019\b/\u001b:f'\t\u0001!\u0002\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t1\u0003\u0005\u0002\f)%\u0011Q\u0003\u0004\u0002\u0005+:LG/A\u0002nCb,2\u0001\u0007\u001d )\u0015I\u0012HQ$M!\u0011Q2$H\u001c\u000e\u0003\u0015I!\u0001H\u0003\u0003#9{'/\\3e-\u0016\u001cGo\u001c:Ta\u0006\u001cW\rE\u0002\u001f?]b\u0001\u0001B\u0003!\u0005\t\u0007\u0011E\u0001\u0002D\u0007V\u0011!%L\t\u0003G\u0019\u0002\"a\u0003\u0013\n\u0005\u0015b!a\u0002(pi\"Lgn\u001a\t\u0006O)b3GN\u0007\u0002Q)\u0011\u0011\u0006D\u0001\u000bG>dG.Z2uS>t\u0017BA\u0016)\u0005\u0019\u0019V-](qgB\u0011a$\f\u0003\u0006]}\u0011\ra\f\u0002\u0002\u0003F\u00111\u0005\r\t\u0003\u0017EJ!A\r\u0007\u0003\u0007\u0005s\u0017\u0010\u0005\u0002(i%\u0011Q\u0007\u000b\u0002\u0004'\u0016\f\bc\u0001\u0010 YA\u0011a\u0004\u000f\u0003\u0006]\t\u0011\ra\f\u0005\bu\t\t\t\u0011q\u0001<\u0003))g/\u001b3f]\u000e,G%\r\t\u0004y}:dB\u0001\u000e>\u0013\tqT!A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0001\u000b%!B(sI\u0016\u0014(B\u0001 \u0006\u0011\u001d\u0019%!!AA\u0004\u0011\u000b!\"\u001a<jI\u0016t7-\u001a\u00133!\raTiN\u0005\u0003\r\u0006\u0013QAR5fY\u0012Dq\u0001\u0013\u0002\u0002\u0002\u0003\u000f\u0011*\u0001\u0006fm&$WM\\2fIM\u00022\u0001\u0010&8\u0013\tY\u0015I\u0001\u0004TS\u001etW\r\u001a\u0005\u0006\u001b\n\u0001\u001dAT\u0001\u0005G\n4\u0007\u0007\u0005\u0003(\u001f^j\u0012B\u0001))\u0005\u001d1\u0015m\u0019;pef\f!\u0001\u00149\u0016\u0007M{v\u000b\u0006\u0002U[R)Q\u000bY2iWB!!d\u0007,_!\rqrK\u0018\u0003\u0006A\r\u0011\r\u0001W\u000b\u00033r\u000b\"a\t.\u0011\u000b\u001dR3lM/\u0011\u0005yaF!\u0002\u0018X\u0005\u0004y\u0003c\u0001\u0010X7B\u0011ad\u0018\u0003\u0006]\r\u0011\ra\f\u0005\bC\u000e\t\t\u0011q\u0001c\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0004y\u0015s\u0006b\u00023\u0004\u0003\u0003\u0005\u001d!Z\u0001\u000bKZLG-\u001a8dK\u0012*\u0004c\u0001\u000eg=&\u0011q-\u0002\u0002\u0006\u001dJ{w\u000e\u001e\u0005\bS\u000e\t\t\u0011q\u0001k\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0004y)s\u0006\"B'\u0004\u0001\ba\u0007\u0003B\u0014P=ZCQA\\\u0002A\u0002=\f\u0011\u0001\u001d\t\u0003\u0017AL!!\u001d\u0007\u0003\u0007%sG\u000f"
)
public interface NormedVectorSpaceFunctions {
   // $FF: synthetic method
   static NormedVectorSpace max$(final NormedVectorSpaceFunctions $this, final Order evidence$1, final Field evidence$2, final Signed evidence$3, final Factory cbf0) {
      return $this.max(evidence$1, evidence$2, evidence$3, cbf0);
   }

   default NormedVectorSpace max(final Order evidence$1, final Field evidence$2, final Signed evidence$3, final Factory cbf0) {
      return new SeqMaxNormedVectorSpace(evidence$2, evidence$1, evidence$3, cbf0);
   }

   // $FF: synthetic method
   static NormedVectorSpace Lp$(final NormedVectorSpaceFunctions $this, final int p, final Field evidence$4, final NRoot evidence$5, final Signed evidence$6, final Factory cbf0) {
      return $this.Lp(p, evidence$4, evidence$5, evidence$6, cbf0);
   }

   default NormedVectorSpace Lp(final int p, final Field evidence$4, final NRoot evidence$5, final Signed evidence$6, final Factory cbf0) {
      return new SeqLpNormedVectorSpace(p, evidence$4, evidence$5, evidence$6, cbf0);
   }

   static void $init$(final NormedVectorSpaceFunctions $this) {
   }
}
