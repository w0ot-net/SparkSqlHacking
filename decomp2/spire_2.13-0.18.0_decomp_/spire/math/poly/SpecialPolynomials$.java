package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Ring;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.Function3;
import scala.Tuple2;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.Map;
import scala.collection.immutable.LazyList.Deferrer.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import spire.math.Polynomial;
import spire.math.Polynomial$;

public final class SpecialPolynomials$ {
   public static final SpecialPolynomials$ MODULE$ = new SpecialPolynomials$();

   public LazyList hornerScheme(final Polynomial zero, final Polynomial one, final Function3 fn, final Ring evidence$1, final Eq evidence$2, final ClassTag evidence$3) {
      return .MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> loop$1(zero, one, loop$default$3$1(), fn)), () -> zero);
   }

   private Function3 legendreFn(final Eq evidence$4, final ClassTag evidence$5, final Field f) {
      return (pn, pnm1, n) -> $anonfun$legendreFn$1(f, evidence$4, evidence$5, pn, pnm1, BoxesRunTime.unboxToInt(n));
   }

   private Function3 laguerreFn(final Eq evidence$6, final ClassTag evidence$7, final Field f) {
      return (pn, pnm1, n) -> $anonfun$laguerreFn$1(f, evidence$6, evidence$7, pn, pnm1, BoxesRunTime.unboxToInt(n));
   }

   private Function3 chebyshevFn(final Ring evidence$8, final Eq evidence$9, final ClassTag evidence$10) {
      return (pn, pnm1, n) -> $anonfun$chebyshevFn$1(evidence$9, evidence$8, evidence$10, pn, pnm1, BoxesRunTime.unboxToInt(n));
   }

   private Function3 hermiteFnProb(final Ring evidence$11, final Eq evidence$12, final ClassTag evidence$13) {
      return (pn, pnm1, n) -> $anonfun$hermiteFnProb$1(evidence$12, evidence$11, evidence$13, pn, pnm1, BoxesRunTime.unboxToInt(n));
   }

   private Function3 hermiteFnPhys(final Ring evidence$14, final Eq evidence$15, final ClassTag evidence$16) {
      return (pn, pnm1, n) -> $anonfun$hermiteFnPhys$1(evidence$15, evidence$14, evidence$16, pn, pnm1, BoxesRunTime.unboxToInt(n));
   }

   public LazyList legendres(final int num, final Field evidence$17, final Eq evidence$18, final ClassTag evidence$19) {
      return this.hornerScheme(Polynomial$.MODULE$.one(evidence$18, evidence$17, evidence$19), Polynomial$.MODULE$.x(evidence$18, evidence$17, evidence$19), this.legendreFn(evidence$18, evidence$19, evidence$17), evidence$17, evidence$18, evidence$19).take(num);
   }

   public LazyList laguerres(final int num, final Eq evidence$20, final ClassTag evidence$21, final Field f) {
      return this.hornerScheme(Polynomial$.MODULE$.one(evidence$20, f, evidence$21), Polynomial$.MODULE$.apply((Map)((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(0), f.one()), new Tuple2(BoxesRunTime.boxToInteger(1), f.negate(f.one()))})))), f, evidence$20, evidence$21), this.laguerreFn(evidence$20, evidence$21, f), f, evidence$20, evidence$21).take(num);
   }

   public LazyList chebyshevsFirstKind(final int num, final Ring evidence$22, final Eq evidence$23, final ClassTag evidence$24) {
      return this.hornerScheme(Polynomial$.MODULE$.one(evidence$23, evidence$22, evidence$24), Polynomial$.MODULE$.x(evidence$23, evidence$22, evidence$24), this.chebyshevFn(evidence$22, evidence$23, evidence$24), evidence$22, evidence$23, evidence$24).take(num);
   }

   public LazyList chebyshevsSecondKind(final int num, final Ring evidence$25, final Eq evidence$26, final ClassTag evidence$27) {
      return this.hornerScheme(Polynomial$.MODULE$.one(evidence$26, evidence$25, evidence$27), Polynomial$.MODULE$.twox(evidence$26, evidence$25, evidence$27), this.chebyshevFn(evidence$25, evidence$26, evidence$27), evidence$25, evidence$26, evidence$27).take(num);
   }

   public LazyList probHermites(final int num, final Ring evidence$28, final Eq evidence$29, final ClassTag evidence$30) {
      return this.hornerScheme(Polynomial$.MODULE$.one(evidence$29, evidence$28, evidence$30), Polynomial$.MODULE$.x(evidence$29, evidence$28, evidence$30), this.hermiteFnProb(evidence$28, evidence$29, evidence$30), evidence$28, evidence$29, evidence$30).take(num);
   }

   public LazyList physHermites(final int num, final Ring evidence$31, final Eq evidence$32, final ClassTag evidence$33) {
      return this.hornerScheme(Polynomial$.MODULE$.one(evidence$32, evidence$31, evidence$33), Polynomial$.MODULE$.twox(evidence$32, evidence$31, evidence$33), this.hermiteFnPhys(evidence$31, evidence$32, evidence$33), evidence$31, evidence$32, evidence$33).take(num);
   }

   private static final LazyList loop$1(final Polynomial pnm1, final Polynomial pn, final int n, final Function3 fn$1) {
      return .MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> loop$1(pn, (Polynomial)fn$1.apply(pn, pnm1, BoxesRunTime.boxToInteger(n)), n + 1, fn$1)), () -> pn);
   }

   private static final int loop$default$3$1() {
      return 1;
   }

   // $FF: synthetic method
   public static final Polynomial $anonfun$legendreFn$1(final Field f$1, final Eq evidence$4$1, final ClassTag evidence$5$1, final Polynomial pn, final Polynomial pnm1, final int n) {
      PolySparse a = Polynomial$.MODULE$.apply((Map)((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(0), f$1.div(f$1.fromInt(1), f$1.fromInt(n + 1)))})))), f$1, evidence$4$1, evidence$5$1);
      PolySparse b = Polynomial$.MODULE$.apply((Map)((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(1), f$1.fromInt(2 * n + 1))})))), f$1, evidence$4$1, evidence$5$1);
      PolySparse c = Polynomial$.MODULE$.apply((Map)((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(0), f$1.negate(f$1.fromInt(n)))})))), f$1, evidence$4$1, evidence$5$1);
      return a.$times(b.$times(pn, f$1, evidence$4$1).$plus(c.$times(pnm1, f$1, evidence$4$1), f$1, evidence$4$1), f$1, evidence$4$1);
   }

   // $FF: synthetic method
   public static final Polynomial $anonfun$laguerreFn$1(final Field f$2, final Eq evidence$6$1, final ClassTag evidence$7$1, final Polynomial pn, final Polynomial pnm1, final int n) {
      return Polynomial$.MODULE$.apply((Map)((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(0), f$2.div(f$2.one(), f$2.fromInt(n + 1)))})))), f$2, evidence$6$1, evidence$7$1).$times(Polynomial$.MODULE$.apply((Map)((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(0), f$2.fromInt(2 * n + 1)), new Tuple2(BoxesRunTime.boxToInteger(1), f$2.negate(f$2.one()))})))), f$2, evidence$6$1, evidence$7$1).$times(pn, f$2, evidence$6$1).$minus(pnm1.$times(Polynomial$.MODULE$.apply((Map)((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(0), f$2.fromInt(n))})))), f$2, evidence$6$1, evidence$7$1), f$2, evidence$6$1), f$2, evidence$6$1), f$2, evidence$6$1);
   }

   // $FF: synthetic method
   public static final Polynomial $anonfun$chebyshevFn$1(final Eq evidence$9$1, final Ring evidence$8$1, final ClassTag evidence$10$1, final Polynomial pn, final Polynomial pnm1, final int n) {
      return Polynomial$.MODULE$.twox(evidence$9$1, evidence$8$1, evidence$10$1).$times(pn, evidence$8$1, evidence$9$1).$minus(pnm1, evidence$8$1, evidence$9$1);
   }

   // $FF: synthetic method
   public static final Polynomial $anonfun$hermiteFnProb$1(final Eq evidence$12$1, final Ring evidence$11$1, final ClassTag evidence$13$1, final Polynomial pn, final Polynomial pnm1, final int n) {
      return Polynomial$.MODULE$.x(evidence$12$1, evidence$11$1, evidence$13$1).$times(pn, evidence$11$1, evidence$12$1).$minus(pn.derivative(evidence$11$1, evidence$12$1), evidence$11$1, evidence$12$1);
   }

   // $FF: synthetic method
   public static final Polynomial $anonfun$hermiteFnPhys$1(final Eq evidence$15$1, final Ring evidence$14$1, final ClassTag evidence$16$1, final Polynomial pn, final Polynomial pnm1, final int n) {
      return Polynomial$.MODULE$.twox(evidence$15$1, evidence$14$1, evidence$16$1).$times(pn, evidence$14$1, evidence$15$1).$minus(pn.derivative(evidence$14$1, evidence$15$1), evidence$14$1, evidence$15$1);
   }

   private SpecialPolynomials$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
