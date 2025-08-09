package spire.std;

import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0016\u0001\u0011\u0005a\u0003C\u0004\u001b\u0001\t\u0007IqA\u000e\t\u000fQ\u0002!\u0019!C\u0004k\ty1\u000b\u001e:j]\u001eLen\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005\u00191\u000f\u001e3\u000b\u0003!\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001\u0017E\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0007C\u0001\n\u0014\u001b\u0005)\u0011B\u0001\u000b\u0006\u0005A\u0019FO]5oO&s7\u000f^1oG\u0016\u001c\b'\u0001\u0004%S:LG\u000f\n\u000b\u0002/A\u0011A\u0002G\u0005\u000335\u0011A!\u00168ji\u0006i1\u000b\u001e:j]\u001e\fEnZ3ce\u0006,\u0012\u0001\b\t\u0004;%bcB\u0001\u0010'\u001d\tyBE\u0004\u0002!G5\t\u0011E\u0003\u0002#\u0013\u00051AH]8pizJ\u0011\u0001C\u0005\u0003K\u001d\tq!\u00197hK\n\u0014\u0018-\u0003\u0002(Q\u00059\u0001/Y2lC\u001e,'BA\u0013\b\u0013\tQ3F\u0001\u0004N_:|\u0017\u000e\u001a\u0006\u0003O!\u0002\"!L\u0019\u000f\u00059z\u0003C\u0001\u0011\u000e\u0013\t\u0001T\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003eM\u0012aa\u0015;sS:<'B\u0001\u0019\u000e\u0003-\u0019FO]5oO>\u0013H-\u001a:\u0016\u0003Y\u00022!H\u001c-\u0013\tA4FA\u0003Pe\u0012,'\u000f"
)
public interface StringInstances extends StringInstances0 {
   void spire$std$StringInstances$_setter_$StringAlgebra_$eq(final Monoid x$1);

   void spire$std$StringInstances$_setter_$StringOrder_$eq(final Order x$1);

   Monoid StringAlgebra();

   Order StringOrder();

   static void $init$(final StringInstances $this) {
      $this.spire$std$StringInstances$_setter_$StringAlgebra_$eq(new StringMonoid());
      $this.spire$std$StringInstances$_setter_$StringOrder_$eq(new StringOrder());
   }
}
