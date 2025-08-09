package breeze.stats.distributions;

import breeze.linalg.DenseVector$;
import breeze.linalg.DenseVector$mcD$sp;
import breeze.util.ArrayUtil$;
import java.io.Serializable;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Polya$ implements Serializable {
   public static final Polya$ MODULE$ = new Polya$();

   public Polya sym(final double alpha, final int k, final RandBasis rand) {
      return this.apply((double[])ArrayUtil$.MODULE$.fillNewArray(k, BoxesRunTime.boxToDouble(alpha), .MODULE$.Double()), rand);
   }

   public Polya apply(final double[] arr, final RandBasis rand) {
      return new Polya$mcI$sp(new DenseVector$mcD$sp(arr), DenseVector$.MODULE$.space_Double(), rand);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Polya$.class);
   }

   private Polya$() {
   }
}
