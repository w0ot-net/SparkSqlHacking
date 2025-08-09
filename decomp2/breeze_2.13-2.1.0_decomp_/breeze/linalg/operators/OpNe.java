package breeze.linalg.operators;

import breeze.generic.UFunc;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019;Q\u0001B\u0003\t\u000211QAD\u0003\t\u0002=AQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0004\t\nAa\u00149OK*\u0011aaB\u0001\n_B,'/\u0019;peNT!\u0001C\u0005\u0002\r1Lg.\u00197h\u0015\u0005Q\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u00055\tQ\"A\u0003\u0003\t=\u0003h*Z\n\u0005\u0003A1\u0012\u0004\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001b]I!\u0001G\u0003\u0003\r=\u0003H+\u001f9f!\tQR$D\u0001\u001c\u0015\ta\u0012\"A\u0004hK:,'/[2\n\u0005yY\"\u0001E#mK6,g\u000e^<jg\u0016,f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\tA\"A\tj[Bd'G\u0012:p[>\u0013H-\u001a:j]\u001e,\"a\t\u0017\u0015\u0005\u0011B\u0004#B\u0013'U)*T\"A\u0001\n\u0005\u001dB#!B%na2\u0014\u0014BA\u0015\u001c\u0005\u0015)f)\u001e8d!\tYC\u0006\u0004\u0001\u0005\u000b5\u001a!\u0019\u0001\u0018\u0003\u0003Q\u000b\"a\f\u001a\u0011\u0005E\u0001\u0014BA\u0019\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!E\u001a\n\u0005Q\u0012\"aA!osB\u0011\u0011CN\u0005\u0003oI\u0011qAQ8pY\u0016\fg\u000eC\u0004:\u0007\u0005\u0005\t9\u0001\u001e\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\r\t\u0004w\rScB\u0001\u001fB\u001d\ti\u0004)D\u0001?\u0015\ty4\"\u0001\u0004=e>|GOP\u0005\u0002'%\u0011!IE\u0001\ba\u0006\u001c7.Y4f\u0013\t!UI\u0001\u0005Pe\u0012,'/\u001b8h\u0015\t\u0011%\u0003"
)
public final class OpNe {
   public static UFunc.UImpl2 impl2FromOrdering(final Ordering evidence$10) {
      return OpNe$.MODULE$.impl2FromOrdering(evidence$10);
   }

   public static Object withSink(final Object s) {
      return OpNe$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpNe$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpNe$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpNe$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpNe$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpNe$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpNe$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpNe$.MODULE$.apply(v, impl);
   }
}
