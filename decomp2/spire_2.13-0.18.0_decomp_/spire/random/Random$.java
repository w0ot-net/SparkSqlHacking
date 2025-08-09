package spire.random;

import scala.Function1;
import spire.random.rng.Cmwc5;
import spire.random.rng.Cmwc5$;

public final class Random$ implements RandomCompanion {
   public static final Random$ MODULE$ = new Random$();

   static {
      RandomCompanion.$init$(MODULE$);
   }

   public Generator generatorFromSeed(final Seed seed) {
      return RandomCompanion.generatorFromSeed$(this, seed);
   }

   public Random next(final Function1 f) {
      return RandomCompanion.next$(this, f);
   }

   public Random fromDist(final Dist dist) {
      return RandomCompanion.fromDist$(this, dist);
   }

   public Random constant(final Object b) {
      return RandomCompanion.constant$(this, b);
   }

   public Random unit() {
      return RandomCompanion.unit$(this);
   }

   public Random boolean() {
      return RandomCompanion.boolean$(this);
   }

   public Random byte() {
      return RandomCompanion.byte$(this);
   }

   public Random short() {
      return RandomCompanion.short$(this);
   }

   public Random char() {
      return RandomCompanion.char$(this);
   }

   public Random int() {
      return RandomCompanion.int$(this);
   }

   public Random int(final int n) {
      return RandomCompanion.int$(this, n);
   }

   public Random int(final int n1, final int n2) {
      return RandomCompanion.int$(this, n1, n2);
   }

   public Random float() {
      return RandomCompanion.float$(this);
   }

   public Random long() {
      return RandomCompanion.long$(this);
   }

   public Random double() {
      return RandomCompanion.double$(this);
   }

   public Random string(final Size size) {
      return RandomCompanion.string$(this, size);
   }

   public Random stringOfSize(final int n) {
      return RandomCompanion.stringOfSize$(this, n);
   }

   public RandomCompanion.RandomOps RandomOps(final Random lhs) {
      return RandomCompanion.RandomOps$(this, lhs);
   }

   public Random tuple2(final Random r1, final Random r2) {
      return RandomCompanion.tuple2$(this, r1, r2);
   }

   public Random tuple3(final Random r1, final Random r2, final Random r3) {
      return RandomCompanion.tuple3$(this, r1, r2, r3);
   }

   public Random tuple4(final Random r1, final Random r2, final Random r3, final Random r4) {
      return RandomCompanion.tuple4$(this, r1, r2, r3, r4);
   }

   public Cmwc5 initGenerator() {
      return Cmwc5$.MODULE$.fromTime(Cmwc5$.MODULE$.fromTime$default$1());
   }

   public RandomCmwc5 spawn(final Op op) {
      return new RandomCmwc5(op);
   }

   private Random$() {
   }
}
