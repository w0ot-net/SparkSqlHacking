package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import scala.Function1;
import scala.collection.ArrayOps.;
import scala.reflect.ClassTag;

public final class MultiplePredicatesRand$mcD$sp extends MultiplePredicatesRand implements PredicateRandDraws$mcD$sp {
   public final Rand rand$mcD$sp;
   public final Function1[] predicates$mcD$sp;

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

   public Function1[] predicates$mcD$sp() {
      return this.predicates$mcD$sp;
   }

   public Function1[] predicates() {
      return this.predicates$mcD$sp();
   }

   public Rand condition(final Function1 p) {
      return this.condition$mcD$sp(p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      Function1[] newPredicates = new Function1[.MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.predicates())) + 1];
      int index$macro$2 = 0;

      for(int limit$macro$4 = .MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.predicates())); index$macro$2 < limit$macro$4; ++index$macro$2) {
         newPredicates[index$macro$2] = this.predicates()[index$macro$2];
      }

      newPredicates[.MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.predicates()))] = p;
      return new MultiplePredicatesRand$mcD$sp(this.rand(), newPredicates);
   }

   public final boolean predicate(final double x) {
      return this.predicate$mcD$sp(x);
   }

   public final boolean predicate$mcD$sp(final double x) {
      boolean result = true;

      for(int i = 0; i < .MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.predicates())) && result; ++i) {
         result = result && this.predicates()[i].apply$mcZD$sp(x);
      }

      return result;
   }

   public Rand copy$default$1() {
      return this.copy$default$1$mcD$sp();
   }

   public Rand copy$default$1$mcD$sp() {
      return this.rand();
   }

   public Function1[] copy$default$2() {
      return this.copy$default$2$mcD$sp();
   }

   public Function1[] copy$default$2$mcD$sp() {
      return this.predicates();
   }

   public Function1[] predicates$access$1() {
      return this.predicates$access$1$mcD$sp();
   }

   public Function1[] predicates$access$1$mcD$sp() {
      return this.predicates$mcD$sp;
   }

   public boolean specInstance$() {
      return true;
   }

   public MultiplePredicatesRand$mcD$sp(final Rand rand$mcD$sp, final Function1[] predicates$mcD$sp) {
      super((Rand)null, predicates$mcD$sp);
      this.rand$mcD$sp = rand$mcD$sp;
      this.predicates$mcD$sp = predicates$mcD$sp;
   }
}
