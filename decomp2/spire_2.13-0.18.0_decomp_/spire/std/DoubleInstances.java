package spire.std;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u0005%3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0004\u0017\u0001\t\u0007IqA\f\t\u000f\u0005\u0003!\u0019!C\u0004\u0005\nyAi\\;cY\u0016Len\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005\u00191\u000f\u001e3\u000b\u0003!\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u0017A\u0011AbD\u0007\u0002\u001b)\ta\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0011\u001b\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\n\u0011\u00051!\u0012BA\u000b\u000e\u0005\u0011)f.\u001b;\u0002\u001b\u0011{WO\u00197f\u00032<WM\u0019:b+\u0005A\"\u0003C\r\u001c]I*\u0004h\u000f \u0007\ti\u0001\u0001\u0001\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u00049!ZcBA\u000f&\u001d\tq2E\u0004\u0002 E5\t\u0001E\u0003\u0002\"\u0013\u00051AH]8pizJ\u0011\u0001C\u0005\u0003I\u001d\tq!\u00197hK\n\u0014\u0018-\u0003\u0002'O\u00059\u0001/Y2lC\u001e,'B\u0001\u0013\b\u0013\tI#FA\u0003GS\u0016dGM\u0003\u0002'OA\u0011A\u0002L\u0005\u0003[5\u0011a\u0001R8vE2,\u0007cA\u00181W5\tq%\u0003\u00022O\t)aJU8piB\u0019qfM\u0016\n\u0005Q:#\u0001\u0002+sS\u001e\u00042a\f\u001c,\u0013\t9tE\u0001\u0006JgJ\u000bG/[8oC2\u00042\u0001H\u001d,\u0013\tQ$F\u0001\fUeVt7-\u0019;fI\u0012Kg/[:j_:\u001c%+\u001b8h!\raBhK\u0005\u0003{)\u0012aaU5h]\u0016$\u0007c\u0001\u000f@W%\u0011\u0001I\u000b\u0002\u0006\u001fJ$WM]\u0001\n\t>,(\r\\3UC\u001e,\u0012a\u0011\t\u0004\t\u001e[S\"A#\u000b\u0005\u0019;\u0011\u0001B7bi\"L!\u0001S#\u0003\u00139+XNY3s)\u0006<\u0007"
)
public interface DoubleInstances {
   void spire$std$DoubleInstances$_setter_$DoubleAlgebra_$eq(final Field x$1);

   void spire$std$DoubleInstances$_setter_$DoubleTag_$eq(final NumberTag x$1);

   Field DoubleAlgebra();

   NumberTag DoubleTag();

   static void $init$(final DoubleInstances $this) {
      $this.spire$std$DoubleInstances$_setter_$DoubleAlgebra_$eq(new DoubleAlgebra());
      $this.spire$std$DoubleInstances$_setter_$DoubleTag_$eq(new NumberTag.BuiltinFloatTag() {
         public boolean isInfinite(final double a) {
            return Double.isInfinite(a);
         }

         public boolean isNaN(final double a) {
            return Double.isNaN(a);
         }
      });
   }
}
