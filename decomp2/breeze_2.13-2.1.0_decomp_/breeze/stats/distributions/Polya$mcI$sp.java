package breeze.stats.distributions;

import breeze.linalg.QuasiTensor;
import breeze.linalg.sum$;
import breeze.math.MutableEnumeratedCoordinateField;
import breeze.numerics.package;
import breeze.numerics.package$lbeta$impl2Double$;
import scala.math.package.;
import scala.runtime.BoxesRunTime;

public class Polya$mcI$sp extends Polya {
   public final Dirichlet innerDirichlet$mcI$sp;
   private final Object params;
   private final MutableEnumeratedCoordinateField space;
   private final RandBasis rand;

   public Dirichlet innerDirichlet$mcI$sp() {
      return this.innerDirichlet$mcI$sp;
   }

   public Dirichlet innerDirichlet() {
      return this.innerDirichlet$mcI$sp();
   }

   public int draw() {
      return this.draw$mcI$sp();
   }

   public int draw$mcI$sp() {
      return BoxesRunTime.unboxToInt(Multinomial$.MODULE$.apply(this.innerDirichlet().draw(), this.breeze$stats$distributions$Polya$$space.hasOps(), sum$.MODULE$.reduce_Double(this.breeze$stats$distributions$Polya$$space.iterateValues()), this.breeze$stats$distributions$Polya$$rand).draw());
   }

   public double probabilityOf(final int x) {
      return this.probabilityOf$mcI$sp(x);
   }

   public double probabilityOf$mcI$sp(final int x) {
      return .MODULE$.exp(package.lbeta$.MODULE$.apply$mDDDc$sp(BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(this.breeze$stats$distributions$Polya$$params, sum$.MODULE$.reduce_Double(this.breeze$stats$distributions$Polya$$space.iterateValues()))), (double)1.0F, package$lbeta$impl2Double$.MODULE$) - package.lbeta$.MODULE$.apply$mDDDc$sp(((QuasiTensor)this.breeze$stats$distributions$Polya$$space.hasOps().apply(this.breeze$stats$distributions$Polya$$params)).apply$mcID$sp(x), (double)1.0F, package$lbeta$impl2Double$.MODULE$));
   }

   public boolean specInstance$() {
      return true;
   }

   public Polya$mcI$sp(final Object params, final MutableEnumeratedCoordinateField space, final RandBasis rand) {
      super(params, space, rand);
      this.params = params;
      this.space = space;
      this.rand = rand;
      this.innerDirichlet$mcI$sp = new Dirichlet$mcI$sp(params, space, rand);
   }
}
