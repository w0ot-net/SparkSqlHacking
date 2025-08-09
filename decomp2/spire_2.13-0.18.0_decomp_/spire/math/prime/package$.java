package spire.math.prime;

import algebra.ring.Signed;
import java.lang.invoke.SerializedLambda;
import java.math.BigInteger;
import java.util.Random;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Map;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import spire.math.SafeLong;
import spire.math.SafeLong$;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final Random srand = new Random();
   private static final int SieveSize = 9600000;

   public boolean isPrime(final SafeLong n) {
      return n.isProbablePrime(40);
   }

   public Factors factor(final SafeLong n) {
      return this.factorPollardRho(n);
   }

   public Factors factorTrialDivision(final SafeLong n0) {
      if (BoxesRunTime.equalsNumObject(n0, BoxesRunTime.boxToInteger(0))) {
         return Factors$.MODULE$.zero();
      } else {
         SafeLong n = n0.abs();
         Signed.Sign sign = spire.algebra.package$.MODULE$.Sign().apply(n0.signum());
         if (BoxesRunTime.equalsNumNum(n, SafeLong$.MODULE$.one())) {
            return new Factors(.MODULE$.Map().empty(), sign);
         } else {
            Map facts = (Map)scala.collection.mutable.Map..MODULE$.empty();
            SafeLong x = n;
            Tuple2 var9 = this.findPowers(n, SafeLong$.MODULE$.apply(2));
            if (var9 == null) {
               throw new MatchError(var9);
            } else {
               SafeLong x1 = (SafeLong)var9._1();
               int e1 = var9._2$mcI$sp();
               Tuple2 var3 = new Tuple2(x1, BoxesRunTime.boxToInteger(e1));
               SafeLong x1 = (SafeLong)var3._1();
               int e1 = var3._2$mcI$sp();
               if (e1 > 0) {
                  facts.update(SafeLong$.MODULE$.apply(2), BoxesRunTime.boxToInteger(e1));
                  x = x1;
               }

               SafeLong limit = (SafeLong)SafeLong$.MODULE$.SafeLongAlgebra().sqrt(x);

               for(SafeLong index$macro$1 = SafeLong$.MODULE$.apply(3); index$macro$1.$less$eq(limit) && x.$greater(SafeLong$.MODULE$.apply(1)); index$macro$1 = index$macro$1.$plus(2L)) {
                  Tuple2 var17 = this.findPowers(x, index$macro$1);
                  if (var17 == null) {
                     throw new MatchError(var17);
                  }

                  SafeLong x2 = (SafeLong)var17._1();
                  int e2 = var17._2$mcI$sp();
                  Tuple2 var2 = new Tuple2(x2, BoxesRunTime.boxToInteger(e2));
                  SafeLong x2 = (SafeLong)var2._1();
                  int e2 = var2._2$mcI$sp();
                  if (e2 > 0) {
                     facts.update(index$macro$1, BoxesRunTime.boxToInteger(e2));
                     x = x2;
                     limit = (SafeLong)SafeLong$.MODULE$.SafeLongAlgebra().sqrt(x2);
                  }
               }

               if (x.$greater(SafeLong$.MODULE$.apply(1))) {
                  facts.update(x, BoxesRunTime.boxToInteger(1));
               }

               return new Factors(facts.toMap(scala..less.colon.less..MODULE$.refl()), sign);
            }
         }
      }
   }

   public Factors factorWheelDivision(final SafeLong n0) {
      if (BoxesRunTime.equalsNumObject(n0, BoxesRunTime.boxToInteger(0))) {
         return Factors$.MODULE$.zero();
      } else {
         SafeLong n = n0.abs();
         Signed.Sign sign = spire.algebra.package$.MODULE$.Sign().apply(n0.signum());
         if (BoxesRunTime.equalsNumObject(n, BoxesRunTime.boxToInteger(1))) {
            return new Factors(.MODULE$.Map().empty(), sign);
         } else {
            Map facts = (Map)scala.collection.mutable.Map..MODULE$.empty();
            SafeLong x = n;
            Tuple2 var10 = this.findPowers(n, SafeLong$.MODULE$.apply(2));
            if (var10 == null) {
               throw new MatchError(var10);
            } else {
               SafeLong x1 = (SafeLong)var10._1();
               int e1 = var10._2$mcI$sp();
               Tuple2 var4 = new Tuple2(x1, BoxesRunTime.boxToInteger(e1));
               SafeLong x1 = (SafeLong)var4._1();
               int e1 = var4._2$mcI$sp();
               if (e1 > 0) {
                  facts.update(SafeLong$.MODULE$.apply(2), BoxesRunTime.boxToInteger(e1));
                  x = x1;
               }

               for(SafeLong index$macro$1 = SafeLong$.MODULE$.apply(3); index$macro$1.$less(SafeLong$.MODULE$.apply(30)) && x.$greater(SafeLong$.MODULE$.apply(1)); index$macro$1 = index$macro$1.$plus(2L)) {
                  Tuple2 var17 = this.findPowers(x, index$macro$1);
                  if (var17 == null) {
                     throw new MatchError(var17);
                  }

                  SafeLong x2 = (SafeLong)var17._1();
                  int e2 = var17._2$mcI$sp();
                  Tuple2 var3 = new Tuple2(x2, BoxesRunTime.boxToInteger(e2));
                  SafeLong x2 = (SafeLong)var3._1();
                  int e2 = var3._2$mcI$sp();
                  if (e2 > 0) {
                     facts.update(index$macro$1, BoxesRunTime.boxToInteger(e2));
                     x = x2;
                  }
               }

               SafeLong limit = (SafeLong)SafeLong$.MODULE$.SafeLongAlgebra().sqrt(x);
               SafeLong b = SafeLong$.MODULE$.apply(31);
               int i = 0;

               for(int[] offsets = new int[]{2, 2, 2, 4, 2, 4, 2, 4, 6, 2}; b.$less$eq(limit) && x.$greater(SafeLong$.MODULE$.apply(1)); i = (i + 1) % 10) {
                  Tuple2 var27 = this.findPowers(x, b);
                  if (var27 == null) {
                     throw new MatchError(var27);
                  }

                  SafeLong x2 = (SafeLong)var27._1();
                  int e2 = var27._2$mcI$sp();
                  Tuple2 var2 = new Tuple2(x2, BoxesRunTime.boxToInteger(e2));
                  SafeLong x2 = (SafeLong)var2._1();
                  int e2 = var2._2$mcI$sp();
                  if (e2 > 0) {
                     facts.update(b, BoxesRunTime.boxToInteger(e2));
                     x = x2;
                     limit = (SafeLong)SafeLong$.MODULE$.SafeLongAlgebra().sqrt(x2);
                  }

                  b = b.$plus((long)offsets[i]);
               }

               if (x.$greater(SafeLong$.MODULE$.apply(1))) {
                  facts.update(x, BoxesRunTime.boxToInteger(1));
               }

               return new Factors(facts.toMap(scala..less.colon.less..MODULE$.refl()), sign);
            }
         }
      }
   }

   public Factors factorPollardRho(final SafeLong n0) {
      if (BoxesRunTime.equalsNumObject(n0, BoxesRunTime.boxToInteger(0))) {
         return Factors$.MODULE$.zero();
      } else {
         SafeLong n = n0.abs();
         if (BoxesRunTime.equalsNumObject(n, BoxesRunTime.boxToInteger(1))) {
            return new Factors(.MODULE$.Map().empty(), spire.algebra.package$.MODULE$.Sign().apply(n0.signum()));
         } else {
            return n0.$less(SafeLong$.MODULE$.apply(0)) ? this.factor$1(n).unary_$minus() : this.factor$1(n);
         }
      }
   }

   private Random srand() {
      return srand;
   }

   private SafeLong rand(final SafeLong n) {
      int bits = n.bitLength();

      BigInteger x;
      for(x = new BigInteger(bits, this.srand()); x.signum() == 0; x = new BigInteger(bits, this.srand())) {
      }

      return SafeLong$.MODULE$.apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x));
   }

   private Tuple2 findPowers(final SafeLong x0, final SafeLong b) {
      SafeLong x = x0;

      int e;
      for(e = 0; x.$greater(SafeLong$.MODULE$.apply(1)) && BoxesRunTime.equalsNumObject(x.$percent(b), BoxesRunTime.boxToInteger(0)); x = x.$div(b)) {
         ++e;
      }

      return new Tuple2(x, BoxesRunTime.boxToInteger(e));
   }

   private int SieveSize() {
      return SieveSize;
   }

   public Siever sieverUpToNth(final long n) {
      double upper = (double)n * spire.math.package$.MODULE$.log((double)n) + (double)n * spire.math.package$.MODULE$.log(spire.math.package$.MODULE$.log((double)n - 0.9385));
      long cutoff = spire.math.package$.MODULE$.max(1000L, (long)(spire.math.package$.MODULE$.sqrt(upper) + (double)512L));
      return new Siever(this.SieveSize(), SafeLong$.MODULE$.apply(cutoff));
   }

   public SafeLong nth(final long n) {
      return this.sieverUpToNth(n).nth(n);
   }

   public SafeLong[] fill(final int n) {
      if (n <= 0) {
         throw new IllegalArgumentException(Integer.toString(n));
      } else {
         SafeLong[] var10000;
         if (n == 1) {
            var10000 = (SafeLong[])((Object[])(new SafeLong[]{SafeLong$.MODULE$.two()}));
         } else {
            Siever siever = this.sieverUpToNth((long)n);
            SafeLong[] arr = new SafeLong[n];
            arr[0] = SafeLong$.MODULE$.two();
            arr[1] = SafeLong$.MODULE$.three();
            this.loop$1(2, SafeLong$.MODULE$.three(), arr, siever);
            var10000 = arr;
         }

         return var10000;
      }
   }

   public SafeLong[] fill(final int start, final int limit) {
      SafeLong[] var10000;
      if (start == 0) {
         var10000 = this.fill(limit);
      } else {
         Siever siever = this.sieverUpToNth((long)(start + limit));
         var10000 = this.loop$2(1, SafeLong$.MODULE$.three(), start, siever, limit);
      }

      return var10000;
   }

   public LazyList lazyList() {
      return this.lazyList(this.SieveSize(), SafeLong$.MODULE$.apply(1000000));
   }

   public LazyList lazyList(final int chunkSize, final SafeLong cutoff) {
      return scala.collection.immutable.LazyList.Deferrer..MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> scala.collection.immutable.LazyList.Deferrer..MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> (new Siever(chunkSize, cutoff)).lazyListAfter(SafeLong$.MODULE$.three())), () -> SafeLong$.MODULE$.three())), () -> SafeLong$.MODULE$.two());
   }

   /** @deprecated */
   public Stream stream() {
      return this.stream(this.SieveSize(), SafeLong$.MODULE$.apply(1000000));
   }

   /** @deprecated */
   public Stream stream(final int chunkSize, final SafeLong cutoff) {
      SafeLong var3 = SafeLong$.MODULE$.two();
      return scala.collection.immutable.Stream.Deferrer..MODULE$.$hash$colon$colon$extension(scala.collection.immutable.Stream..MODULE$.toDeferrer(() -> {
         SafeLong var2 = SafeLong$.MODULE$.three();
         return scala.collection.immutable.Stream.Deferrer..MODULE$.$hash$colon$colon$extension(scala.collection.immutable.Stream..MODULE$.toDeferrer(() -> (new Siever(chunkSize, cutoff)).streamAfter(SafeLong$.MODULE$.three())), var2);
      }), var3);
   }

   private static final SafeLong f$1(final SafeLong x, final SafeLong n$1, final SafeLong c$1) {
      return x.$times(x).$percent(n$1).$plus(c$1).$percent(n$1);
   }

   private final SafeLong fastRho$1(final SafeLong x, final SafeLong q0, final SafeLong r, final SafeLong m, final SafeLong n$1, final SafeLong c$1) {
      while(true) {
         SafeLong y = x;
         SafeLong q = q0;

         for(int index$macro$1 = 0; r.$greater(SafeLong$.MODULE$.apply(index$macro$1)); ++index$macro$1) {
            y = f$1(y, n$1, c$1);
         }

         SafeLong g = SafeLong$.MODULE$.one();
         SafeLong k = SafeLong$.MODULE$.zero();

         SafeLong ys;
         for(ys = y; r.$greater(k) && BoxesRunTime.equalsNumObject(g, BoxesRunTime.boxToInteger(1)); k = k.$plus(m)) {
            ys = y;
            SafeLong limit = m.min(r.$minus(k));

            for(int index$macro$2 = 0; limit.$greater(SafeLong$.MODULE$.apply(index$macro$2)); ++index$macro$2) {
               y = f$1(y, n$1, c$1);
               q = q.$times(x.$minus(y).abs()).$percent(n$1);
            }

            if (BoxesRunTime.equalsNumObject(q, BoxesRunTime.boxToInteger(0))) {
               g = n$1;
            } else {
               g = n$1.gcd(q);
            }
         }

         if (!BoxesRunTime.equalsNumObject(g, BoxesRunTime.boxToInteger(1))) {
            return BoxesRunTime.equalsNumNum(g, n$1) ? this.slowRho$1(x, ys, n$1, c$1) : g;
         }

         SafeLong var10002 = r.$times(2L);
         m = m;
         r = var10002;
         q0 = q;
         x = y;
      }
   }

   private final SafeLong slowRho$1(final SafeLong x, final SafeLong ys, final SafeLong n$1, final SafeLong c$1) {
      while(true) {
         SafeLong yys = f$1(ys, n$1, c$1);
         SafeLong g = n$1.gcd(x.$minus(yys).abs());
         if (!BoxesRunTime.equalsNumObject(g, BoxesRunTime.boxToInteger(1))) {
            return g;
         }

         ys = yys;
         x = x;
      }
   }

   private final SafeLong rho$1(final SafeLong n, final SafeLong c) {
      return this.fastRho$1(this.rand(n), SafeLong$.MODULE$.one(), SafeLong$.MODULE$.one(), this.rand(n), n, c);
   }

   private final Factors factor$1(final SafeLong n) {
      Factors var10000;
      if (BoxesRunTime.equalsNumObject(n, BoxesRunTime.boxToInteger(1))) {
         var10000 = Factors$.MODULE$.one();
      } else if (this.isPrime(n)) {
         var10000 = new Factors((scala.collection.immutable.Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(n, BoxesRunTime.boxToInteger(1))}))), algebra.ring.Signed.Positive..MODULE$);
      } else if (n.isEven()) {
         SafeLong x = n.$div(2L);

         int e;
         for(e = 1; x.isEven(); ++e) {
            x = x.$div(2L);
         }

         var10000 = (new Factors((scala.collection.immutable.Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(SafeLong$.MODULE$.apply(2), BoxesRunTime.boxToInteger(e))}))), algebra.ring.Signed.Positive..MODULE$)).$times(this.factor$1(x));
      } else {
         SafeLong divisor;
         for(divisor = this.rho$1(n, this.rand(n)); BoxesRunTime.equalsNumNum(divisor, n); divisor = this.rho$1(n, this.rand(n))) {
         }

         var10000 = this.factor$1(divisor).$times(this.factor$1(n.$div(divisor)));
      }

      return var10000;
   }

   private final void loop$1(final int i, final SafeLong last, final SafeLong[] arr$1, final Siever siever$1) {
      while(i < arr$1.length) {
         SafeLong p = siever$1.nextAfter(last);
         arr$1[i] = p;
         int var10000 = i + 1;
         last = p;
         i = var10000;
      }

      BoxedUnit var7 = BoxedUnit.UNIT;
   }

   private final SafeLong[] loop$2(final int i, final SafeLong p, final int start$1, final Siever siever$2, final int limit$1) {
      while(i < start$1) {
         int var10000 = i + 1;
         p = siever$2.nextAfter(p);
         i = var10000;
      }

      return siever$2.arrayAt(p, limit$1);
   }

   private package$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
