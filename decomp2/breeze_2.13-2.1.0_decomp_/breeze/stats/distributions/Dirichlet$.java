package breeze.stats.distributions;

import breeze.linalg.Counter;
import breeze.linalg.Counter$;
import breeze.linalg.DenseVector$;
import breeze.linalg.DenseVector$mcD$sp;
import breeze.math.EnumeratedCoordinateField;
import breeze.math.Field;
import breeze.util.ArrayUtil$;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Dirichlet$ implements Serializable {
   public static final Dirichlet$ MODULE$ = new Dirichlet$();

   public Dirichlet apply(final Counter c, final RandBasis rand) {
      return new Dirichlet(c, Counter$.MODULE$.space(Field.fieldDouble$.MODULE$), rand);
   }

   public Dirichlet sym(final double alpha, final int k, final RandBasis rand) {
      return this.apply((double[])ArrayUtil$.MODULE$.fillNewArray(k, BoxesRunTime.boxToDouble(alpha), .MODULE$.Double()), rand);
   }

   public Dirichlet apply(final double[] arr, final RandBasis rand) {
      return new Dirichlet$mcI$sp(new DenseVector$mcD$sp(arr), DenseVector$.MODULE$.space_Double(), rand);
   }

   public Dirichlet apply(final Object params, final EnumeratedCoordinateField space, final RandBasis rand) {
      return new Dirichlet(params, space, rand);
   }

   public Option unapply(final Dirichlet x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.params()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Dirichlet$.class);
   }

   public Dirichlet apply$mIc$sp(final Object params, final EnumeratedCoordinateField space, final RandBasis rand) {
      return new Dirichlet$mcI$sp(params, space, rand);
   }

   public Option unapply$mIc$sp(final Dirichlet x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.params()));
   }

   private Dirichlet$() {
   }
}
