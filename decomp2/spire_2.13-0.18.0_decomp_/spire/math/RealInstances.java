package spire.math;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0004\u0017\u0001\t\u0007IqA\f\t\u000fm\u0002!\u0019!C\u0004y\ti!+Z1m\u0013:\u001cH/\u00198dKNT!AB\u0004\u0002\t5\fG\u000f\u001b\u0006\u0002\u0011\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\f!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012a\u0005\t\u0003\u0019QI!!F\u0007\u0003\tUs\u0017\u000e^\u0001\bC2<WM\u0019:b+\u0005A\"CB\r\u001cEE*\u0004H\u0002\u0003\u001b\u0001\u0001A\"\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004c\u0001\u000f\u001e?5\tQ!\u0003\u0002\u001f\u000b\tQaI]1di&|g.\u00197\u0011\u0005q\u0001\u0013BA\u0011\u0006\u0005\u0011\u0011V-\u00197\u0011\u0007\rrsD\u0004\u0002%W9\u0011QE\u000b\b\u0003M%j\u0011a\n\u0006\u0003Q%\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0005\n\u0005Y9\u0011B\u0001\u0017.\u0003\u001d\u0001\u0018mY6bO\u0016T!AF\u0004\n\u0005=\u0002$A\u0006+sk:\u001c\u0017\r^3e\t&4\u0018n]5p]\u000e\u0013\u0016N\\4\u000b\u00051j\u0003c\u0001\u001a4?5\tQ&\u0003\u00025[\t!AK]5h!\r\u0019cgH\u0005\u0003oA\u0012QAR5fY\u0012\u00042aI\u001d \u0013\tQ\u0004GA\u0003Pe\u0012,'/A\u0004SK\u0006dG+Y4\u0016\u0003u\u00022\u0001\b  \u0013\tyTAA\u0005Ok6\u0014WM\u001d+bO\u0002"
)
public interface RealInstances {
   void spire$math$RealInstances$_setter_$algebra_$eq(final Fractional x$1);

   void spire$math$RealInstances$_setter_$RealTag_$eq(final NumberTag x$1);

   Fractional algebra();

   NumberTag RealTag();

   static void $init$(final RealInstances $this) {
      $this.spire$math$RealInstances$_setter_$algebra_$eq(new RealAlgebra());
      $this.spire$math$RealInstances$_setter_$RealTag_$eq(new NumberTag.LargeTag(NumberTag.Exact$.MODULE$, Real$.MODULE$.zero()));
   }
}
