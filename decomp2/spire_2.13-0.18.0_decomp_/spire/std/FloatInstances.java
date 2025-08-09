package spire.std;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u0005\r3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0004\u0017\u0001\t\u0007IqA\f\t\u000fm\u0002!\u0019!C\u0004y\tqa\t\\8bi&s7\u000f^1oG\u0016\u001c(B\u0001\u0004\b\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0011\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\f!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012a\u0005\t\u0003\u0019QI!!F\u0007\u0003\tUs\u0017\u000e^\u0001\r\r2|\u0017\r^!mO\u0016\u0014'/Y\u000b\u00021I1\u0011d\u0007\u00183ka2AA\u0007\u0001\u00011\taAH]3gS:,W.\u001a8u}A\u0019A\u0004K\u0016\u000f\u0005u)cB\u0001\u0010$\u001d\ty\"%D\u0001!\u0015\t\t\u0013\"\u0001\u0004=e>|GOP\u0005\u0002\u0011%\u0011AeB\u0001\bC2<WM\u0019:b\u0013\t1s%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u0011:\u0011BA\u0015+\u0005\u00151\u0015.\u001a7e\u0015\t1s\u0005\u0005\u0002\rY%\u0011Q&\u0004\u0002\u0006\r2|\u0017\r\u001e\t\u0004_AZS\"A\u0014\n\u0005E:#!\u0002(S_>$\bcA\u00184W%\u0011Ag\n\u0002\u0005)JLw\rE\u00020m-J!aN\u0014\u0003\u0015%\u001b(+\u0019;j_:\fG\u000eE\u0002\u001ds-J!A\u000f\u0016\u0003\u000b=\u0013H-\u001a:\u0002\u0011\u0019cw.\u0019;UC\u001e,\u0012!\u0010\t\u0004}\u0005[S\"A \u000b\u0005\u0001;\u0011\u0001B7bi\"L!AQ \u0003\u00139+XNY3s)\u0006<\u0007"
)
public interface FloatInstances {
   void spire$std$FloatInstances$_setter_$FloatAlgebra_$eq(final Field x$1);

   void spire$std$FloatInstances$_setter_$FloatTag_$eq(final NumberTag x$1);

   Field FloatAlgebra();

   NumberTag FloatTag();

   static void $init$(final FloatInstances $this) {
      $this.spire$std$FloatInstances$_setter_$FloatAlgebra_$eq(new FloatAlgebra());
      $this.spire$std$FloatInstances$_setter_$FloatTag_$eq(new NumberTag.BuiltinFloatTag() {
         public boolean isInfinite(final float a) {
            return Float.isInfinite(a);
         }

         public boolean isNaN(final float a) {
            return Float.isNaN(a);
         }
      });
   }
}
