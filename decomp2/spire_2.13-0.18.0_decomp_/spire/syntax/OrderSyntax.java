package spire.syntax;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003C\u0001\u0011\r1\tC\u0003M\u0001\u0011\rQ\nC\u0003V\u0001\u0011\raKA\u0006Pe\u0012,'oU=oi\u0006D(B\u0001\u0005\n\u0003\u0019\u0019\u0018P\u001c;bq*\t!\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0007\u0001i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0003)Ui\u0011aB\u0005\u0003-\u001d\u0011!\u0003U1si&\fGn\u0014:eKJ\u001c\u0016P\u001c;bq\u00061A%\u001b8ji\u0012\"\u0012!\u0007\t\u0003\u001diI!aG\b\u0003\tUs\u0017\u000e^\u0001\t_J$WM](qgV\u0011a$\n\u000b\u0003?\u0001#\"\u0001\t\u0018\u0011\u0007Q\t3%\u0003\u0002#\u000f\tAqJ\u001d3fe>\u00038\u000f\u0005\u0002%K1\u0001A!\u0002\u0014\u0003\u0005\u00049#!A!\u0012\u0005!Z\u0003C\u0001\b*\u0013\tQsBA\u0004O_RD\u0017N\\4\u0011\u00059a\u0013BA\u0017\u0010\u0005\r\te.\u001f\u0005\b_\t\t\t\u0011q\u00011\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004cu\u001acB\u0001\u001a;\u001d\t\u0019\u0004H\u0004\u00025o5\tQG\u0003\u00027\u0017\u00051AH]8pizJ\u0011AC\u0005\u0003s%\tq!\u00197hK\n\u0014\u0018-\u0003\u0002<y\u00059\u0001/Y2lC\u001e,'BA\u001d\n\u0013\tqtHA\u0003Pe\u0012,'O\u0003\u0002<y!)\u0011I\u0001a\u0001G\u0005\t\u0011-\u0001\nmSR,'/\u00197J]R|%\u000fZ3s\u001fB\u001cHC\u0001#H!\t!R)\u0003\u0002G\u000f\t\u0011B*\u001b;fe\u0006d\u0017J\u001c;Pe\u0012,'o\u00149t\u0011\u0015A5\u00011\u0001J\u0003\ra\u0007n\u001d\t\u0003\u001d)K!aS\b\u0003\u0007%sG/A\nmSR,'/\u00197M_:<wJ\u001d3fe>\u00038\u000f\u0006\u0002O#B\u0011AcT\u0005\u0003!\u001e\u00111\u0003T5uKJ\fG\u000eT8oO>\u0013H-\u001a:PaNDQ\u0001\u0013\u0003A\u0002I\u0003\"AD*\n\u0005Q{!\u0001\u0002'p]\u001e\fQ\u0003\\5uKJ\fG\u000eR8vE2,wJ\u001d3fe>\u00038\u000f\u0006\u0002X5B\u0011A\u0003W\u0005\u00033\u001e\u0011Q\u0003T5uKJ\fG\u000eR8vE2,wJ\u001d3fe>\u00038\u000fC\u0003I\u000b\u0001\u00071\f\u0005\u0002\u000f9&\u0011Ql\u0004\u0002\u0007\t>,(\r\\3"
)
public interface OrderSyntax extends PartialOrderSyntax {
   // $FF: synthetic method
   static OrderOps orderOps$(final OrderSyntax $this, final Object a, final Order evidence$3) {
      return $this.orderOps(a, evidence$3);
   }

   default OrderOps orderOps(final Object a, final Order evidence$3) {
      return new OrderOps(a, evidence$3);
   }

   // $FF: synthetic method
   static int literalIntOrderOps$(final OrderSyntax $this, final int lhs) {
      return $this.literalIntOrderOps(lhs);
   }

   default int literalIntOrderOps(final int lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static long literalLongOrderOps$(final OrderSyntax $this, final long lhs) {
      return $this.literalLongOrderOps(lhs);
   }

   default long literalLongOrderOps(final long lhs) {
      return lhs;
   }

   // $FF: synthetic method
   static double literalDoubleOrderOps$(final OrderSyntax $this, final double lhs) {
      return $this.literalDoubleOrderOps(lhs);
   }

   default double literalDoubleOrderOps(final double lhs) {
      return lhs;
   }

   static void $init$(final OrderSyntax $this) {
   }
}
