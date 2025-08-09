package spire.std;

import algebra.ring.EuclideanRing;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.math.BitString;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u000513q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007Iq\u0001\r\t\u000f\t\u0002!\u0019!C\u0004G!9q\t\u0001b\u0001\n\u000fA%\u0001D%oi&s7\u000f^1oG\u0016\u001c(BA\u0004\t\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0013\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\r!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012\u0001\u0006\t\u0003\u001bUI!A\u0006\b\u0003\tUs\u0017\u000e^\u0001\r\u0013:$()\u001b;TiJLgnZ\u000b\u00023A\u0019!$H\u0010\u000e\u0003mQ!\u0001\b\u0005\u0002\t5\fG\u000f[\u0005\u0003=m\u0011\u0011BQ5u'R\u0014\u0018N\\4\u0011\u00055\u0001\u0013BA\u0011\u000f\u0005\rIe\u000e^\u0001\u000b\u0013:$\u0018\t\\4fEJ\fW#\u0001\u0013\u0013\u000f\u0015:sg\u000f B\t\u001a!a\u0005\u0001\u0001%\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rACg\b\b\u0003SEr!AK\u0018\u000f\u0005-rS\"\u0001\u0017\u000b\u00055R\u0011A\u0002\u001fs_>$h(C\u0001\n\u0013\t\u0001\u0004\"A\u0004bY\u001e,'M]1\n\u0005I\u001a\u0014a\u00029bG.\fw-\u001a\u0006\u0003a!I!!\u000e\u001c\u0003\u001b\u0015+8\r\\5eK\u0006t'+\u001b8h\u0015\t\u00114\u0007E\u00029s}i\u0011aM\u0005\u0003uM\u0012QA\u0014*p_R\u00042\u0001\u000f\u001f \u0013\ti4G\u0001\u0006Jg&sG/Z4sC2\u00042\u0001K  \u0013\t\u0001eG\u0001\fUeVt7-\u0019;fI\u0012Kg/[:j_:\u001c%+\u001b8h!\rA#iH\u0005\u0003\u0007Z\u0012aaU5h]\u0016$\u0007c\u0001\u0015F?%\u0011aI\u000e\u0002\u0006\u001fJ$WM]\u0001\u0007\u0013:$H+Y4\u0016\u0003%\u00032A\u0007& \u0013\tY5DA\u0005Ok6\u0014WM\u001d+bO\u0002"
)
public interface IntInstances {
   void spire$std$IntInstances$_setter_$IntBitString_$eq(final BitString x$1);

   void spire$std$IntInstances$_setter_$IntAlgebra_$eq(final EuclideanRing x$1);

   void spire$std$IntInstances$_setter_$IntTag_$eq(final NumberTag x$1);

   BitString IntBitString();

   EuclideanRing IntAlgebra();

   NumberTag IntTag();

   static void $init$(final IntInstances $this) {
      $this.spire$std$IntInstances$_setter_$IntBitString_$eq(new IntIsBitString());
      $this.spire$std$IntInstances$_setter_$IntAlgebra_$eq(new IntAlgebra());
      $this.spire$std$IntInstances$_setter_$IntTag_$eq(new NumberTag.BuiltinIntTag(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(Integer.MIN_VALUE), BoxesRunTime.boxToInteger(Integer.MAX_VALUE)));
   }
}
