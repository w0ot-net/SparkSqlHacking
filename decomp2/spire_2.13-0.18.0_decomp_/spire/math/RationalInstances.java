package spire.math;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0004\u0017\u0001\t\u0007IqA\f\t\u000fe\u0002!\u0019!C\u0004u\t\t\"+\u0019;j_:\fG.\u00138ti\u0006t7-Z:\u000b\u0005\u00199\u0011\u0001B7bi\"T\u0011\u0001C\u0001\u0006gBL'/Z\u0002\u0001'\t\u00011\u0002\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003M\u0001\"\u0001\u0004\u000b\n\u0005Ui!\u0001B+oSR\fqBU1uS>t\u0017\r\\!mO\u0016\u0014'/Y\u000b\u00021I)\u0011dG\u00184m\u0019!!\u0004\u0001\u0001\u0019\u00051a$/\u001a4j]\u0016lWM\u001c;?!\ra\u0002f\u000b\b\u0003;\u0015r!AH\u0012\u000f\u0005}\u0011S\"\u0001\u0011\u000b\u0005\u0005J\u0011A\u0002\u001fs_>$h(C\u0001\t\u0013\t!s!A\u0004bY\u001e,'M]1\n\u0005\u0019:\u0013a\u00029bG.\fw-\u001a\u0006\u0003I\u001dI!!\u000b\u0016\u0003\u000b\u0019KW\r\u001c3\u000b\u0005\u0019:\u0003C\u0001\u0017.\u001b\u0005)\u0011B\u0001\u0018\u0006\u0005!\u0011\u0016\r^5p]\u0006d\u0007c\u0001\u00192W5\tq%\u0003\u00023O\tQ\u0011j\u001d*bi&|g.\u00197\u0011\u0007q!4&\u0003\u00026U\t1BK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8D%&tw\rE\u0002\u001do-J!\u0001\u000f\u0016\u0003\u000b=\u0013H-\u001a:\u0002\u0017I\u000bG/[8oC2$\u0016mZ\u000b\u0002wA\u0019A\u0006P\u0016\n\u0005u*!!\u0003(v[\n,'\u000fV1h\u0001"
)
public interface RationalInstances {
   void spire$math$RationalInstances$_setter_$RationalAlgebra_$eq(final Field x$1);

   void spire$math$RationalInstances$_setter_$RationalTag_$eq(final NumberTag x$1);

   Field RationalAlgebra();

   NumberTag RationalTag();

   static void $init$(final RationalInstances $this) {
      $this.spire$math$RationalInstances$_setter_$RationalAlgebra_$eq(new RationalAlgebra());
      $this.spire$math$RationalInstances$_setter_$RationalTag_$eq(new NumberTag.LargeTag(NumberTag.Exact$.MODULE$, Rational$.MODULE$.zero()));
   }
}
