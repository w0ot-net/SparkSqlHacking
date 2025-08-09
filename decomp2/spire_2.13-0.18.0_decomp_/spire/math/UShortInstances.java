package spire.math;

import algebra.ring.CommutativeRig;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007Iq\u0001\r\t\u000fu\u0002!\u0019!C\u0004}!9!\t\u0001b\u0001\n\u000f\u0019%aD+TQ>\u0014H/\u00138ti\u0006t7-Z:\u000b\u0005\u001dA\u0011\u0001B7bi\"T\u0011!C\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Q\u0001\"!D\u000b\n\u0005Yq!\u0001B+oSR\fQ\"V*i_J$\u0018\t\\4fEJ\fW#A\r\u0013\ria\u0002\u0007N\u001c;\r\u0011Y\u0002\u0001A\r\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0007uICF\u0004\u0002\u001fM9\u0011q\u0004\n\b\u0003A\rj\u0011!\t\u0006\u0003E)\ta\u0001\u0010:p_Rt\u0014\"A\u0005\n\u0005\u0015B\u0011aB1mO\u0016\u0014'/Y\u0005\u0003O!\nq\u0001]1dW\u0006<WM\u0003\u0002&\u0011%\u0011!f\u000b\u0002\u0005\u0007JKwM\u0003\u0002(QA\u0011QFL\u0007\u0002\r%\u0011qF\u0002\u0002\u0007+NCwN\u001d;\u0011\u0007E\u0012D&D\u0001)\u0013\t\u0019\u0004F\u0001\u0006Jg&sG/Z4sC2\u00042!H\u001b-\u0013\t14FA\tUeVt7-\u0019;fI\u0012Kg/[:j_:\u00042!\b\u001d-\u0013\tI4FA\u000bTS\u001etW\rZ!eI&$\u0018N^3D\u001b>tw.\u001b3\u0011\u0007uYD&\u0003\u0002=W\t)qJ\u001d3fe\u0006yQk\u00155peR\u0014\u0015\u000e^*ue&tw-F\u0001@!\ri\u0003\tL\u0005\u0003\u0003\u001a\u0011\u0011BQ5u'R\u0014\u0018N\\4\u0002\u0013U\u001b\u0006n\u001c:u)\u0006<W#\u0001#\u0011\u00075*E&\u0003\u0002G\r\tIa*^7cKJ$\u0016m\u001a"
)
public interface UShortInstances {
   void spire$math$UShortInstances$_setter_$UShortAlgebra_$eq(final CommutativeRig x$1);

   void spire$math$UShortInstances$_setter_$UShortBitString_$eq(final BitString x$1);

   void spire$math$UShortInstances$_setter_$UShortTag_$eq(final NumberTag x$1);

   CommutativeRig UShortAlgebra();

   BitString UShortBitString();

   NumberTag UShortTag();

   static void $init$(final UShortInstances $this) {
      $this.spire$math$UShortInstances$_setter_$UShortAlgebra_$eq(new UShortAlgebra());
      $this.spire$math$UShortInstances$_setter_$UShortBitString_$eq(new UShortBitString());
      $this.spire$math$UShortInstances$_setter_$UShortTag_$eq(new NumberTag.UnsignedIntTag(new UShort(UShort$.MODULE$.MinValue()), new UShort(UShort$.MODULE$.MaxValue())));
   }
}
