package breeze.linalg;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=<QAB\u0004\t\u000211QAD\u0004\t\u0002=AQ\u0001H\u0001\u0005\u0002uAQAH\u0001\u0005\u0004}AQAS\u0001\u0005\u0004-CQ\u0001W\u0001\u0005\u0004e\u000bQa\u001d9mSRT!\u0001C\u0005\u0002\r1Lg.\u00197h\u0015\u0005Q\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u00055\tQ\"A\u0004\u0003\u000bM\u0004H.\u001b;\u0014\u0007\u0005\u0001b\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0003/ii\u0011\u0001\u0007\u0006\u00033%\tqaZ3oKJL7-\u0003\u0002\u001c1\t)QKR;oG\u00061A(\u001b8jiz\"\u0012\u0001D\u0001\u000bS6\u0004H.\u00138u-\u0016\u001cWC\u0001\u0011+)\t\t#\tE\u0003#G\u0015\u001ad'D\u0001\u0002\u0013\t!#DA\u0003J[Bd'\u0007E\u0002\u000eM!J!aJ\u0004\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003S)b\u0001\u0001B\u0003,\u0007\t\u0007AFA\u0001U#\ti\u0003\u0007\u0005\u0002\u0012]%\u0011qF\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t\u0012'\u0003\u00023%\t\u0019\u0011I\\=\u0011\u0005E!\u0014BA\u001b\u0013\u0005\rIe\u000e\u001e\t\u0004o}*cB\u0001\u001d>\u001d\tID(D\u0001;\u0015\tY4\"\u0001\u0004=e>|GOP\u0005\u0002'%\u0011aHE\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0015I\u0001\u0006J]\u0012,\u00070\u001a3TKFT!A\u0010\n\t\u000f\r\u001b\u0011\u0011!a\u0002\t\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007\u0015C\u0005&D\u0001G\u0015\t9%#A\u0004sK\u001adWm\u0019;\n\u0005%3%\u0001C\"mCN\u001cH+Y4\u0002\u0015%l\u0007\u000f\\*fcZ+7-\u0006\u0002M!R\u0011Q*\u0016\t\u0006E\rr\u0015\u000b\u0016\t\u0004\u001b\u0019z\u0005CA\u0015Q\t\u0015YCA1\u0001-!\r9$kM\u0005\u0003'\u0006\u00131aU3r!\r9tH\u0014\u0005\b-\u0012\t\t\u0011q\u0001X\u0003))g/\u001b3f]\u000e,GE\r\t\u0004\u000b\"{\u0015!D5na2Le\u000e^'biJL\u00070\u0006\u0002[ER\u00191\fZ4\u0011\r\tbflM\u001ad\u0013\ti&DA\u0003J[Bd7\u0007E\u0002\u000e?\u0006L!\u0001Y\u0004\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0003S\t$QaK\u0003C\u00021\u00022aN _\u0011\u001d)W!!AA\u0004\u0019\f!\"\u001a<jI\u0016t7-\u001a\u00134!\r)\u0005*\u0019\u0005\u0006Q\u0016\u0001\u001d![\u0001\u0005u\u0016\u0014x\u000eE\u0002k[\u0006l\u0011a\u001b\u0006\u0003Y&\tqa\u001d;pe\u0006<W-\u0003\u0002oW\n!!,\u001a:p\u0001"
)
public final class split {
   public static UFunc.UImpl3 implIntMatrix(final ClassTag evidence$3, final Zero zero) {
      return split$.MODULE$.implIntMatrix(evidence$3, zero);
   }

   public static UFunc.UImpl2 implSeqVec(final ClassTag evidence$2) {
      return split$.MODULE$.implSeqVec(evidence$2);
   }

   public static UFunc.UImpl2 implIntVec(final ClassTag evidence$1) {
      return split$.MODULE$.implIntVec(evidence$1);
   }

   public static Object withSink(final Object s) {
      return split$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return split$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return split$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return split$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return split$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return split$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return split$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return split$.MODULE$.apply(v, impl);
   }
}
