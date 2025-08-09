package spire.std;

import algebra.ring.EuclideanRing;
import scala.package.;
import scala.reflect.ScalaSignature;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u000593q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0004\u0017\u0001\t\u0007IqA\f\t\u000f\u0019\u0003!\u0019!C\u0004\u000f\ny!)[4J]RLen\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005\u00191\u000f\u001e3\u000b\u0003!\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u0017A\u0011AbD\u0007\u0002\u001b)\ta\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0011\u001b\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\n\u0011\u00051!\u0012BA\u000b\u000e\u0005\u0011)f.\u001b;\u0002\u001b\tKw-\u00138u\u00032<WM\u0019:b+\u0005A\"\u0003C\r\u001cg]RT\bQ\"\u0007\ti\u0001\u0001\u0001\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u00049!ZcBA\u000f&\u001d\tq2E\u0004\u0002 E5\t\u0001E\u0003\u0002\"\u0013\u00051AH]8pizJ\u0011\u0001C\u0005\u0003I\u001d\tq!\u00197hK\n\u0014\u0018-\u0003\u0002'O\u00059\u0001/Y2lC\u001e,'B\u0001\u0013\b\u0013\tI#FA\u0007Fk\u000ed\u0017\u000eZ3b]JKgn\u001a\u0006\u0003M\u001d\u0002\"\u0001\f\u0019\u000f\u00055zcBA\u0010/\u0013\u0005q\u0011B\u0001\u0014\u000e\u0013\t\t$G\u0001\u0004CS\u001eLe\u000e\u001e\u0006\u0003M5\u00012\u0001N\u001b,\u001b\u00059\u0013B\u0001\u001c(\u0005\u0015q%k\\8u!\u0011!\u0004hK\u0016\n\u0005e:#aC'fiJL7m\u00159bG\u0016\u00042\u0001N\u001e,\u0013\tatE\u0001\u0006Jg&sG/Z4sC2\u00042\u0001\b ,\u0013\ty$F\u0001\fUeVt7-\u0019;fI\u0012Kg/[:j_:\u001c%+\u001b8h!\ra\u0012iK\u0005\u0003\u0005*\u0012aaU5h]\u0016$\u0007c\u0001\u000fEW%\u0011QI\u000b\u0002\u0006\u001fJ$WM]\u0001\n\u0005&<\u0017J\u001c;UC\u001e,\u0012\u0001\u0013\t\u0004\u00132[S\"\u0001&\u000b\u0005-;\u0011\u0001B7bi\"L!!\u0014&\u0003\u00139+XNY3s)\u0006<\u0007"
)
public interface BigIntInstances {
   void spire$std$BigIntInstances$_setter_$BigIntAlgebra_$eq(final EuclideanRing x$1);

   void spire$std$BigIntInstances$_setter_$BigIntTag_$eq(final NumberTag x$1);

   EuclideanRing BigIntAlgebra();

   NumberTag BigIntTag();

   static void $init$(final BigIntInstances $this) {
      $this.spire$std$BigIntInstances$_setter_$BigIntAlgebra_$eq(new BigIntAlgebra());
      $this.spire$std$BigIntInstances$_setter_$BigIntTag_$eq(new NumberTag.LargeTag(NumberTag.Integral$.MODULE$, .MODULE$.BigInt().apply(0)));
   }
}
