package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import scala.Function1;
import scala.reflect.ClassTag;

public final class SinglePredicateRand$mcI$sp extends SinglePredicateRand implements PredicateRandDraws$mcI$sp {
   public final Rand rand$mcI$sp;
   public final Function1 pred$mcI$sp;

   public int draw() {
      return PredicateRandDraws$mcI$sp.draw$(this);
   }

   public int draw$mcI$sp() {
      return PredicateRandDraws$mcI$sp.draw$mcI$sp$(this);
   }

   public int get() {
      return Rand$mcI$sp.get$(this);
   }

   public int get$mcI$sp() {
      return Rand$mcI$sp.get$mcI$sp$(this);
   }

   public int sample() {
      return Rand$mcI$sp.sample$(this);
   }

   public int sample$mcI$sp() {
      return Rand$mcI$sp.sample$mcI$sp$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand$mcI$sp.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return Rand$mcI$sp.samplesVector$mcI$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand$mcI$sp.flatMap$(this, f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return Rand$mcI$sp.flatMap$mcI$sp$(this, f);
   }

   public Rand map(final Function1 f) {
      return Rand$mcI$sp.map$(this, f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return Rand$mcI$sp.map$mcI$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand$mcI$sp.foreach$(this, f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      Rand$mcI$sp.foreach$mcI$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand$mcI$sp.filter$(this, p);
   }

   public Rand filter$mcI$sp(final Function1 p) {
      return Rand$mcI$sp.filter$mcI$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand$mcI$sp.withFilter$(this, p);
   }

   public Rand withFilter$mcI$sp(final Function1 p) {
      return Rand$mcI$sp.withFilter$mcI$sp$(this, p);
   }

   public Rand rand$mcI$sp() {
      return this.rand$mcI$sp;
   }

   public Rand rand() {
      return this.rand$mcI$sp();
   }

   public Function1 pred$mcI$sp() {
      return this.pred$mcI$sp;
   }

   public Function1 pred() {
      return this.pred$mcI$sp();
   }

   public final boolean predicate(final int x) {
      return this.predicate$mcI$sp(x);
   }

   public final boolean predicate$mcI$sp(final int x) {
      return this.pred().apply$mcZI$sp(x);
   }

   public Rand condition(final Function1 p) {
      return this.condition$mcI$sp(p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      Function1[] newPredicates = new Function1[2];
      newPredicates[0] = this.pred();
      newPredicates[1] = p;
      return new MultiplePredicatesRand$mcI$sp(this.rand(), newPredicates);
   }

   public Rand copy$default$1() {
      return this.copy$default$1$mcI$sp();
   }

   public Rand copy$default$1$mcI$sp() {
      return this.rand();
   }

   public Function1 copy$default$2() {
      return this.copy$default$2$mcI$sp();
   }

   public Function1 copy$default$2$mcI$sp() {
      return this.pred();
   }

   public boolean specInstance$() {
      return true;
   }

   public SinglePredicateRand$mcI$sp(final Rand rand$mcI$sp, final Function1 pred$mcI$sp) {
      super((Rand)null, (Function1)null);
      this.rand$mcI$sp = rand$mcI$sp;
      this.pred$mcI$sp = pred$mcI$sp;
   }
}
