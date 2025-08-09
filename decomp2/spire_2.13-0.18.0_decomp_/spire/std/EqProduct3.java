package spire.std;

import cats.kernel.Eq;
import scala.Tuple3;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0003\u0005\u0006q\u0001!\t!\u000f\u0005\u0006{\u00011\u0019A\u0010\u0005\u0006\u0001\u00021\u0019!\u0011\u0005\u0006\u0007\u00021\u0019\u0001\u0012\u0005\u0006\r\u0002!\ta\u0012\u0002\u000b\u000bF\u0004&o\u001c3vGR\u001c$B\u0001\u0005\n\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0015\u0005)1\u000f]5sKV!A\"K\u001a7'\r\u0001Qb\u0005\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Q\tCE\u0004\u0002\u0016=9\u0011a\u0003\b\b\u0003/mi\u0011\u0001\u0007\u0006\u00033i\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u0015%\u0011Q$C\u0001\bC2<WM\u0019:b\u0013\ty\u0002%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005uI\u0011B\u0001\u0012$\u0005\t)\u0015O\u0003\u0002 AA)a\"J\u00143k%\u0011ae\u0004\u0002\u0007)V\u0004H.Z\u001a\u0011\u0005!JC\u0002\u0001\u0003\u0006U\u0001\u0011\ra\u000b\u0002\u0002\u0003F\u0011Af\f\t\u0003\u001d5J!AL\b\u0003\u000f9{G\u000f[5oOB\u0011a\u0002M\u0005\u0003c=\u00111!\u00118z!\tA3\u0007B\u00035\u0001\t\u00071FA\u0001C!\tAc\u0007B\u00038\u0001\t\u00071FA\u0001D\u0003\u0019!\u0013N\\5uIQ\t!\b\u0005\u0002\u000fw%\u0011Ah\u0004\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012a\u0010\t\u0004)\u0005:\u0013AC:ueV\u001cG/\u001e:feU\t!\tE\u0002\u0015CI\n!b\u001d;sk\u000e$XO]34+\u0005)\u0005c\u0001\u000b\"k\u0005\u0019Q-\u001d<\u0015\u0007![U\n\u0005\u0002\u000f\u0013&\u0011!j\u0004\u0002\b\u0005>|G.Z1o\u0011\u0015aU\u00011\u0001%\u0003\tA\b\u0007C\u0003O\u000b\u0001\u0007A%\u0001\u0002yc\u0001"
)
public interface EqProduct3 extends Eq {
   Eq structure1();

   Eq structure2();

   Eq structure3();

   // $FF: synthetic method
   static boolean eqv$(final EqProduct3 $this, final Tuple3 x0, final Tuple3 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple3 x0, final Tuple3 x1) {
      return this.structure1().eqv(x0._1(), x1._1()) && this.structure2().eqv(x0._2(), x1._2()) && this.structure3().eqv(x0._3(), x1._3());
   }

   static void $init$(final EqProduct3 $this) {
   }
}
