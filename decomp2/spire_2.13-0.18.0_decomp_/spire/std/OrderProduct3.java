package spire.std;

import cats.kernel.Order;
import scala.Tuple3;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005!\u0002\u0004\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\u0005\u00021\u0019a\u0011\u0005\u0006\u000b\u00021\u0019A\u0012\u0005\u0006\u0011\u00021\u0019!\u0013\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006)\u0002!\t%\u0016\u0002\u000e\u001fJ$WM\u001d)s_\u0012,8\r^\u001a\u000b\u0005%Q\u0011aA:uI*\t1\"A\u0003ta&\u0014X-\u0006\u0003\u000eUQ:4\u0003\u0002\u0001\u000f)e\u0002\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007cA\u000b#K9\u0011ac\b\b\u0003/uq!\u0001\u0007\u000f\u000e\u0003eQ!AG\u000e\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aC\u0005\u0003=)\tq!\u00197hK\n\u0014\u0018-\u0003\u0002!C\u00059\u0001/Y2lC\u001e,'B\u0001\u0010\u000b\u0013\t\u0019CEA\u0003Pe\u0012,'O\u0003\u0002!CA)qB\n\u00154m%\u0011q\u0005\u0005\u0002\u0007)V\u0004H.Z\u001a\u0011\u0005%RC\u0002\u0001\u0003\u0006W\u0001\u0011\r\u0001\f\u0002\u0002\u0003F\u0011Q\u0006\r\t\u0003\u001f9J!a\f\t\u0003\u000f9{G\u000f[5oOB\u0011q\"M\u0005\u0003eA\u00111!\u00118z!\tIC\u0007B\u00036\u0001\t\u0007AFA\u0001C!\tIs\u0007B\u00039\u0001\t\u0007AFA\u0001D!\u0015Q4\bK\u001a7\u001b\u0005A\u0011B\u0001\u001f\t\u0005))\u0015\u000f\u0015:pIV\u001cGoM\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0002\"a\u0004!\n\u0005\u0005\u0003\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005!\u0005cA\u000b#Q\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003\u001d\u00032!\u0006\u00124\u0003)\u0019HO];diV\u0014XmM\u000b\u0002\u0015B\u0019QC\t\u001c\u0002\u000f\r|W\u000e]1sKR\u0019Q\n\u0015*\u0011\u0005=q\u0015BA(\u0011\u0005\rIe\u000e\u001e\u0005\u0006#\u0016\u0001\r!J\u0001\u0003qBBQaU\u0003A\u0002\u0015\n!\u0001_\u0019\u0002\u0007\u0015\fh\u000fF\u0002W3j\u0003\"aD,\n\u0005a\u0003\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006#\u001a\u0001\r!\n\u0005\u0006'\u001a\u0001\r!\n"
)
public interface OrderProduct3 extends Order, EqProduct3 {
   Order structure1();

   Order structure2();

   Order structure3();

   // $FF: synthetic method
   static int compare$(final OrderProduct3 $this, final Tuple3 x0, final Tuple3 x1) {
      return $this.compare(x0, x1);
   }

   default int compare(final Tuple3 x0, final Tuple3 x1) {
      int cmp = 0;
      cmp = this.structure1().compare(x0._1(), x1._1());
      int var10000;
      if (cmp != 0) {
         var10000 = cmp;
      } else {
         cmp = this.structure2().compare(x0._2(), x1._2());
         if (cmp != 0) {
            var10000 = cmp;
         } else {
            cmp = this.structure3().compare(x0._3(), x1._3());
            var10000 = cmp != 0 ? cmp : 0;
         }
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean eqv$(final OrderProduct3 $this, final Tuple3 x0, final Tuple3 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple3 x0, final Tuple3 x1) {
      return this.compare(x0, x1) == 0;
   }

   static void $init$(final OrderProduct3 $this) {
   }
}
