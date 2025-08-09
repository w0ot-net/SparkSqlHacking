package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import scala.Function1;
import scala.reflect.ClassTag;

public final class SinglePredicateRand$mcD$sp extends SinglePredicateRand implements PredicateRandDraws$mcD$sp {
   public final Rand rand$mcD$sp;
   public final Function1 pred$mcD$sp;

   public double draw() {
      return PredicateRandDraws$mcD$sp.draw$(this);
   }

   public double draw$mcD$sp() {
      return PredicateRandDraws$mcD$sp.draw$mcD$sp$(this);
   }

   public double get() {
      return Rand$mcD$sp.get$(this);
   }

   public double get$mcD$sp() {
      return Rand$mcD$sp.get$mcD$sp$(this);
   }

   public double sample() {
      return Rand$mcD$sp.sample$(this);
   }

   public double sample$mcD$sp() {
      return Rand$mcD$sp.sample$mcD$sp$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand$mcD$sp.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return Rand$mcD$sp.samplesVector$mcD$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand$mcD$sp.flatMap$(this, f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return Rand$mcD$sp.flatMap$mcD$sp$(this, f);
   }

   public Rand map(final Function1 f) {
      return Rand$mcD$sp.map$(this, f);
   }

   public Rand map$mcD$sp(final Function1 f) {
      return Rand$mcD$sp.map$mcD$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand$mcD$sp.foreach$(this, f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      Rand$mcD$sp.foreach$mcD$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand$mcD$sp.filter$(this, p);
   }

   public Rand filter$mcD$sp(final Function1 p) {
      return Rand$mcD$sp.filter$mcD$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand$mcD$sp.withFilter$(this, p);
   }

   public Rand withFilter$mcD$sp(final Function1 p) {
      return Rand$mcD$sp.withFilter$mcD$sp$(this, p);
   }

   public Rand rand$mcD$sp() {
      return this.rand$mcD$sp;
   }

   public Rand rand() {
      return this.rand$mcD$sp();
   }

   public Function1 pred$mcD$sp() {
      return this.pred$mcD$sp;
   }

   public Function1 pred() {
      return this.pred$mcD$sp();
   }

   public final boolean predicate(final double x) {
      return this.predicate$mcD$sp(x);
   }

   public final boolean predicate$mcD$sp(final double x) {
      return this.pred().apply$mcZD$sp(x);
   }

   public Rand condition(final Function1 p) {
      return this.condition$mcD$sp(p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      Function1[] newPredicates = new Function1[2];
      newPredicates[0] = this.pred();
      newPredicates[1] = p;
      return new MultiplePredicatesRand$mcD$sp(this.rand(), newPredicates);
   }

   public Rand copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public Rand copy$default$1$mcD$sp() {
      return this.rand();
   }

   public Function1 copy$default$2() {
      return this.copy$default$2$mcD$sp();
   }

   public Function1 copy$default$2$mcD$sp() {
      return this.pred();
   }

   public boolean specInstance$() {
      return true;
   }

   public SinglePredicateRand$mcD$sp(final Rand rand$mcD$sp, final Function1 pred$mcD$sp) {
      super((Rand)null, (Function1)null);
      this.rand$mcD$sp = rand$mcD$sp;
      this.pred$mcD$sp = pred$mcD$sp;
   }
}
