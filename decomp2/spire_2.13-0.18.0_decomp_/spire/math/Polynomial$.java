package spire.math;

import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import cats.kernel.Eq;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ClassTag;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Statics;
import scala.util.matching.Regex;
import spire.math.poly.PolyDense;
import spire.math.poly.PolyDense$mcD$sp;
import spire.math.poly.PolySparse;
import spire.math.poly.PolySparse$;
import spire.math.poly.Term$;

public final class Polynomial$ implements PolynomialInstances {
   public static final Polynomial$ MODULE$ = new Polynomial$();
   private static final Regex termRe;
   private static final Regex operRe;

   static {
      PolynomialInstances0.$init$(MODULE$);
      PolynomialInstances1.$init$(MODULE$);
      PolynomialInstances2.$init$(MODULE$);
      PolynomialInstances3.$init$(MODULE$);
      PolynomialInstances4.$init$(MODULE$);
      termRe = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("([0-9]+\\.[0-9]+|[0-9]+/[0-9]+|[0-9]+)?(?:([a-z])(?:\\^([0-9]+))?)?"));
      operRe = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString(" *([+-]) *"));
   }

   public PolynomialOverField overField(final ClassTag evidence$83, final Field evidence$84, final Eq evidence$85) {
      return PolynomialInstances4.overField$(this, evidence$83, evidence$84, evidence$85);
   }

   public PolynomialOverField overField$mDc$sp(final ClassTag evidence$83, final Field evidence$84, final Eq evidence$85) {
      return PolynomialInstances4.overField$mDc$sp$(this, evidence$83, evidence$84, evidence$85);
   }

   public PolynomialOverCRing overCRing(final ClassTag evidence$80, final CommutativeRing evidence$81, final Eq evidence$82) {
      return PolynomialInstances3.overCRing$(this, evidence$80, evidence$81, evidence$82);
   }

   public PolynomialOverCRing overCRing$mDc$sp(final ClassTag evidence$80, final CommutativeRing evidence$81, final Eq evidence$82) {
      return PolynomialInstances3.overCRing$mDc$sp$(this, evidence$80, evidence$81, evidence$82);
   }

   public PolynomialOverRing overRing(final ClassTag evidence$77, final Ring evidence$78, final Eq evidence$79) {
      return PolynomialInstances2.overRing$(this, evidence$77, evidence$78, evidence$79);
   }

   public PolynomialOverRing overRing$mDc$sp(final ClassTag evidence$77, final Ring evidence$78, final Eq evidence$79) {
      return PolynomialInstances2.overRing$mDc$sp$(this, evidence$77, evidence$78, evidence$79);
   }

   public PolynomialOverRig overRig(final ClassTag evidence$71, final Rig evidence$72, final Eq evidence$73) {
      return PolynomialInstances1.overRig$(this, evidence$71, evidence$72, evidence$73);
   }

   public PolynomialOverRig overRig$mDc$sp(final ClassTag evidence$71, final Rig evidence$72, final Eq evidence$73) {
      return PolynomialInstances1.overRig$mDc$sp$(this, evidence$71, evidence$72, evidence$73);
   }

   public PolynomialOverRng overRng(final ClassTag evidence$74, final Rng evidence$75, final Eq evidence$76) {
      return PolynomialInstances1.overRng$(this, evidence$74, evidence$75, evidence$76);
   }

   public PolynomialOverRng overRng$mDc$sp(final ClassTag evidence$74, final Rng evidence$75, final Eq evidence$76) {
      return PolynomialInstances1.overRng$mDc$sp$(this, evidence$74, evidence$75, evidence$76);
   }

   public PolynomialOverSemiring overSemiring(final ClassTag evidence$65, final Semiring evidence$66, final Eq evidence$67) {
      return PolynomialInstances0.overSemiring$(this, evidence$65, evidence$66, evidence$67);
   }

   public PolynomialOverSemiring overSemiring$mDc$sp(final ClassTag evidence$65, final Semiring evidence$66, final Eq evidence$67) {
      return PolynomialInstances0.overSemiring$mDc$sp$(this, evidence$65, evidence$66, evidence$67);
   }

   public PolynomialEq eq(final ClassTag evidence$68, final Semiring evidence$69, final Eq evidence$70) {
      return PolynomialInstances0.eq$(this, evidence$68, evidence$69, evidence$70);
   }

   public PolynomialEq eq$mDc$sp(final ClassTag evidence$68, final Semiring evidence$69, final Eq evidence$70) {
      return PolynomialInstances0.eq$mDc$sp$(this, evidence$68, evidence$69, evidence$70);
   }

   public PolyDense dense(final Object coeffs, final Semiring evidence$1, final Eq evidence$2, final ClassTag evidence$3) {
      int i;
      for(i = scala.runtime.ScalaRunTime..MODULE$.array_length(coeffs); i > 0 && evidence$2.eqv(scala.runtime.ScalaRunTime..MODULE$.array_apply(coeffs, i - 1), spire.algebra.package$.MODULE$.Semiring().apply(evidence$1).zero()); --i) {
      }

      PolyDense var10000;
      if (i == scala.runtime.ScalaRunTime..MODULE$.array_length(coeffs)) {
         var10000 = new PolyDense(coeffs, evidence$3);
      } else {
         Object cs = evidence$3.newArray(i);
         System.arraycopy(coeffs, 0, cs, 0, i);
         var10000 = new PolyDense(cs, evidence$3);
      }

      return var10000;
   }

   public PolySparse sparse(final Map data, final Semiring evidence$4, final Eq evidence$5, final ClassTag evidence$6) {
      return PolySparse$.MODULE$.apply(data, evidence$4, evidence$5, evidence$6);
   }

   public PolySparse apply(final Map data, final Semiring evidence$7, final Eq evidence$8, final ClassTag evidence$9) {
      return this.sparse(data, evidence$7, evidence$8, evidence$9);
   }

   public PolySparse apply(final IterableOnce terms, final Semiring evidence$10, final Eq evidence$11, final ClassTag evidence$12) {
      return PolySparse$.MODULE$.apply(terms, evidence$10, evidence$11, evidence$12);
   }

   public PolySparse apply(final Object c, final int e, final Semiring evidence$13, final Eq evidence$14, final ClassTag evidence$15) {
      PolySparse$ var10000 = PolySparse$.MODULE$;
      int[] var10001 = new int[]{e};
      Object var6 = evidence$15.newArray(1);
      scala.runtime.ScalaRunTime..MODULE$.array_update(var6, 0, c);
      return var10000.safe(var10001, var6, evidence$13, evidence$14, evidence$15);
   }

   public Polynomial apply(final String s) {
      return this.parse(s);
   }

   public Polynomial zero(final Eq evidence$16, final Semiring evidence$17, final ClassTag evidence$18) {
      return PolySparse$.MODULE$.zero(evidence$17, evidence$16, evidence$18);
   }

   public Polynomial constant(final Object c, final Eq evidence$19, final Semiring evidence$20, final ClassTag evidence$21) {
      return (Polynomial)(evidence$19.eqv(c, spire.algebra.package$.MODULE$.Semiring().apply(evidence$20).zero()) ? this.zero(evidence$19, evidence$20, evidence$21) : this.apply((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(0), c)}))), evidence$20, evidence$19, evidence$21));
   }

   public Polynomial linear(final Object c, final Eq evidence$22, final Semiring evidence$23, final ClassTag evidence$24) {
      return (Polynomial)(evidence$22.eqv(c, spire.algebra.package$.MODULE$.Semiring().apply(evidence$23).zero()) ? this.zero(evidence$22, evidence$23, evidence$24) : this.apply((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(1), c)}))), evidence$23, evidence$22, evidence$24));
   }

   public Polynomial linear(final Object c1, final Object c0, final Eq evidence$25, final Semiring evidence$26, final ClassTag evidence$27) {
      return this.apply((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(1), c1), new Tuple2(BoxesRunTime.boxToInteger(0), c0)}))), evidence$26, evidence$25, evidence$27);
   }

   public Polynomial quadratic(final Object c1, final Object c0, final Eq evidence$28, final Semiring evidence$29, final ClassTag evidence$30) {
      return this.apply((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(1), c1), new Tuple2(BoxesRunTime.boxToInteger(0), c0)}))), evidence$29, evidence$28, evidence$30);
   }

   public Polynomial quadratic(final Object c, final Eq evidence$31, final Semiring evidence$32, final ClassTag evidence$33) {
      return (Polynomial)(evidence$31.eqv(c, spire.algebra.package$.MODULE$.Semiring().apply(evidence$32).zero()) ? this.zero(evidence$31, evidence$32, evidence$33) : this.apply((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(2), c)}))), evidence$32, evidence$31, evidence$33));
   }

   public Polynomial quadratic(final Object c2, final Object c1, final Object c0, final Eq evidence$34, final Semiring evidence$35, final ClassTag evidence$36) {
      return this.apply((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(2), c2), new Tuple2(BoxesRunTime.boxToInteger(1), c1), new Tuple2(BoxesRunTime.boxToInteger(0), c0)}))), evidence$35, evidence$34, evidence$36);
   }

   public Polynomial cubic(final Object c, final Eq evidence$37, final Semiring evidence$38, final ClassTag evidence$39) {
      return (Polynomial)(evidence$37.eqv(c, spire.algebra.package$.MODULE$.Semiring().apply(evidence$38).zero()) ? this.zero(evidence$37, evidence$38, evidence$39) : this.apply((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(3), c)}))), evidence$38, evidence$37, evidence$39));
   }

   public Polynomial cubic(final Object c3, final Object c2, final Object c1, final Object c0, final Eq evidence$40, final Semiring evidence$41, final ClassTag evidence$42) {
      return this.apply((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(3), c3), new Tuple2(BoxesRunTime.boxToInteger(2), c2), new Tuple2(BoxesRunTime.boxToInteger(1), c1), new Tuple2(BoxesRunTime.boxToInteger(0), c0)}))), evidence$41, evidence$40, evidence$42);
   }

   public Polynomial one(final Eq evidence$43, final Rig evidence$44, final ClassTag evidence$45) {
      return this.constant(spire.algebra.package$.MODULE$.Rig().apply(evidence$44).one(), evidence$43, evidence$44, evidence$45);
   }

   public Polynomial x(final Eq evidence$46, final Rig evidence$47, final ClassTag evidence$48) {
      return this.linear(spire.algebra.package$.MODULE$.Rig().apply(evidence$47).one(), evidence$46, evidence$47, evidence$48);
   }

   public Polynomial twox(final Eq evidence$49, final Rig evidence$50, final ClassTag evidence$51) {
      return this.linear(evidence$50.plus(spire.algebra.package$.MODULE$.Rig().apply(evidence$50).one(), spire.algebra.package$.MODULE$.Rig().apply(evidence$50).one()), evidence$49, evidence$50, evidence$51);
   }

   public Polynomial parse(final String s) {
      LazyRef T$module = new LazyRef();
      String t = s.trim();
      String u = t.startsWith("(") && t.endsWith(")") ? t.substring(1, t.length() - 1) : t;
      String v = Term$.MODULE$.removeSuperscript(u);
      List ts = this.parse$1(v, scala.package..MODULE$.Nil(), T$module);
      Set vs = (Set)ts.view().map((x$2) -> x$2.v()).toSet().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$parse$6(x$3)));

      class T$1 implements Product, Serializable {
         private final Rational c;
         private final String v;
         private final int e;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Rational c() {
            return this.c;
         }

         public String v() {
            return this.v;
         }

         public int e() {
            return this.e;
         }

         public T$1 copy(final Rational c, final String v, final int e) {
            return new T$1(c, v, e);
         }

         public Rational copy$default$1() {
            return this.c();
         }

         public String copy$default$2() {
            return this.v();
         }

         public int copy$default$3() {
            return this.e();
         }

         public String productPrefix() {
            return "T";
         }

         public int productArity() {
            return 3;
         }

         public Object productElement(final int x$1) {
            Object var10000;
            switch (x$1) {
               case 0:
                  var10000 = this.c();
                  break;
               case 1:
                  var10000 = this.v();
                  break;
               case 2:
                  var10000 = BoxesRunTime.boxToInteger(this.e());
                  break;
               default:
                  var10000 = Statics.ioobe(x$1);
            }

            return var10000;
         }

         public Iterator productIterator() {
            return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
         }

         public boolean canEqual(final Object x$1) {
            return x$1 instanceof T$1;
         }

         public String productElementName(final int x$1) {
            String var10000;
            switch (x$1) {
               case 0:
                  var10000 = "c";
                  break;
               case 1:
                  var10000 = "v";
                  break;
               case 2:
                  var10000 = "e";
                  break;
               default:
                  var10000 = (String)Statics.ioobe(x$1);
            }

            return var10000;
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, Statics.anyHash(this.c()));
            var1 = Statics.mix(var1, Statics.anyHash(this.v()));
            var1 = Statics.mix(var1, this.e());
            return Statics.finalizeHash(var1, 3);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var7;
            if (this != x$1) {
               label57: {
                  boolean var2;
                  if (x$1 instanceof T$1) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  if (var2) {
                     label40: {
                        T$1 var4 = (T$1)x$1;
                        if (this.e() == var4.e() && BoxesRunTime.equalsNumNum(this.c(), var4.c())) {
                           label37: {
                              String var10000 = this.v();
                              String var5 = var4.v();
                              if (var10000 == null) {
                                 if (var5 != null) {
                                    break label37;
                                 }
                              } else if (!var10000.equals(var5)) {
                                 break label37;
                              }

                              if (var4.canEqual(this)) {
                                 var7 = true;
                                 break label40;
                              }
                           }
                        }

                        var7 = false;
                     }

                     if (var7) {
                        break label57;
                     }
                  }

                  var7 = false;
                  return var7;
               }
            }

            var7 = true;
            return var7;
         }

         public T$1(final Rational c, final String v, final int e) {
            this.c = c;
            this.v = v;
            this.e = e;
            Product.$init$(this);
         }
      }

      if (vs.size() > 1) {
         throw new IllegalArgumentException("only univariate polynomials supported");
      } else {
         return (Polynomial)ts.foldLeft(this.zero((Eq)Rational$.MODULE$.RationalAlgebra(), Rational$.MODULE$.RationalAlgebra(), scala.reflect.ClassTag..MODULE$.apply(Rational.class)), (a, tx) -> a.$plus(MODULE$.apply(tx.c(), tx.e(), Rational$.MODULE$.RationalAlgebra(), (Eq)Rational$.MODULE$.RationalAlgebra(), scala.reflect.ClassTag..MODULE$.apply(Rational.class)), Rational$.MODULE$.RationalAlgebra(), (Eq)Rational$.MODULE$.RationalAlgebra()));
      }
   }

   public final Tuple2 spire$math$Polynomial$$split(final Polynomial poly, final ClassTag evidence$52) {
      ArrayBuilder es = spire.scalacompat.package$.MODULE$.arrayBuilderMake(scala.reflect.ClassTag..MODULE$.Int());
      ArrayBuilder cs = spire.scalacompat.package$.MODULE$.arrayBuilderMake(evidence$52);
      poly.foreach((e, c) -> $anonfun$split$1(es, cs, BoxesRunTime.unboxToInt(e), c));
      return new Tuple2(es.result(), cs.result());
   }

   public Polynomial interpolate(final Seq points, final Field evidence$53, final Eq evidence$54, final ClassTag evidence$55) {
      return this.loop$1(this.zero(evidence$54, evidence$53, evidence$55), scala.package..MODULE$.Nil(), points.toList(), evidence$53, evidence$54, evidence$55);
   }

   public PolyDense dense$mDc$sp(final double[] coeffs, final Semiring evidence$1, final Eq evidence$2, final ClassTag evidence$3) {
      int i;
      for(i = coeffs.length; i > 0 && evidence$2.eqv$mcD$sp(coeffs[i - 1], spire.algebra.package$.MODULE$.Semiring().apply(evidence$1).zero$mcD$sp()); --i) {
      }

      PolyDense$mcD$sp var10000;
      if (i == coeffs.length) {
         var10000 = new PolyDense$mcD$sp(coeffs, evidence$3);
      } else {
         double[] cs = (double[])evidence$3.newArray(i);
         System.arraycopy(coeffs, 0, cs, 0, i);
         var10000 = new PolyDense$mcD$sp(cs, evidence$3);
      }

      return var10000;
   }

   public PolySparse sparse$mDc$sp(final Map data, final Semiring evidence$4, final Eq evidence$5, final ClassTag evidence$6) {
      return PolySparse$.MODULE$.apply$mDc$sp(data, evidence$4, evidence$5, evidence$6);
   }

   public PolySparse apply$mDc$sp(final Map data, final Semiring evidence$7, final Eq evidence$8, final ClassTag evidence$9) {
      return this.sparse$mDc$sp(data, evidence$7, evidence$8, evidence$9);
   }

   public PolySparse apply$mDc$sp(final IterableOnce terms, final Semiring evidence$10, final Eq evidence$11, final ClassTag evidence$12) {
      return PolySparse$.MODULE$.apply$mDc$sp(terms, evidence$10, evidence$11, evidence$12);
   }

   public PolySparse apply$mDc$sp(final double c, final int e, final Semiring evidence$13, final Eq evidence$14, final ClassTag evidence$15) {
      return PolySparse$.MODULE$.safe$mDc$sp(new int[]{e}, (double[])scala.Array..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new double[]{c}), evidence$15), evidence$13, evidence$14, evidence$15);
   }

   public Polynomial zero$mDc$sp(final Eq evidence$16, final Semiring evidence$17, final ClassTag evidence$18) {
      return PolySparse$.MODULE$.zero$mDc$sp(evidence$17, evidence$16, evidence$18);
   }

   public Polynomial constant$mDc$sp(final double c, final Eq evidence$19, final Semiring evidence$20, final ClassTag evidence$21) {
      return (Polynomial)(evidence$19.eqv$mcD$sp(c, spire.algebra.package$.MODULE$.Semiring().apply(evidence$20).zero$mcD$sp()) ? this.zero$mDc$sp(evidence$19, evidence$20, evidence$21) : this.apply$mDc$sp((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcID.sp(0, c)}))), evidence$20, evidence$19, evidence$21));
   }

   public Polynomial linear$mDc$sp(final double c, final Eq evidence$22, final Semiring evidence$23, final ClassTag evidence$24) {
      return (Polynomial)(evidence$22.eqv$mcD$sp(c, spire.algebra.package$.MODULE$.Semiring().apply(evidence$23).zero$mcD$sp()) ? this.zero$mDc$sp(evidence$22, evidence$23, evidence$24) : this.apply$mDc$sp((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcID.sp(1, c)}))), evidence$23, evidence$22, evidence$24));
   }

   public Polynomial linear$mDc$sp(final double c1, final double c0, final Eq evidence$25, final Semiring evidence$26, final ClassTag evidence$27) {
      return this.apply$mDc$sp((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcID.sp(1, c1), new Tuple2.mcID.sp(0, c0)}))), evidence$26, evidence$25, evidence$27);
   }

   public Polynomial quadratic$mDc$sp(final double c1, final double c0, final Eq evidence$28, final Semiring evidence$29, final ClassTag evidence$30) {
      return this.apply$mDc$sp((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcID.sp(1, c1), new Tuple2.mcID.sp(0, c0)}))), evidence$29, evidence$28, evidence$30);
   }

   public Polynomial quadratic$mDc$sp(final double c, final Eq evidence$31, final Semiring evidence$32, final ClassTag evidence$33) {
      return (Polynomial)(evidence$31.eqv$mcD$sp(c, spire.algebra.package$.MODULE$.Semiring().apply(evidence$32).zero$mcD$sp()) ? this.zero$mDc$sp(evidence$31, evidence$32, evidence$33) : this.apply$mDc$sp((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcID.sp(2, c)}))), evidence$32, evidence$31, evidence$33));
   }

   public Polynomial quadratic$mDc$sp(final double c2, final double c1, final double c0, final Eq evidence$34, final Semiring evidence$35, final ClassTag evidence$36) {
      return this.apply$mDc$sp((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcID.sp(2, c2), new Tuple2.mcID.sp(1, c1), new Tuple2.mcID.sp(0, c0)}))), evidence$35, evidence$34, evidence$36);
   }

   public Polynomial cubic$mDc$sp(final double c, final Eq evidence$37, final Semiring evidence$38, final ClassTag evidence$39) {
      return (Polynomial)(evidence$37.eqv$mcD$sp(c, spire.algebra.package$.MODULE$.Semiring().apply(evidence$38).zero$mcD$sp()) ? this.zero$mDc$sp(evidence$37, evidence$38, evidence$39) : this.apply$mDc$sp((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcID.sp(3, c)}))), evidence$38, evidence$37, evidence$39));
   }

   public Polynomial cubic$mDc$sp(final double c3, final double c2, final double c1, final double c0, final Eq evidence$40, final Semiring evidence$41, final ClassTag evidence$42) {
      return this.apply$mDc$sp((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcID.sp(3, c3), new Tuple2.mcID.sp(2, c2), new Tuple2.mcID.sp(1, c1), new Tuple2.mcID.sp(0, c0)}))), evidence$41, evidence$40, evidence$42);
   }

   public Polynomial one$mDc$sp(final Eq evidence$43, final Rig evidence$44, final ClassTag evidence$45) {
      return this.constant$mDc$sp(spire.algebra.package$.MODULE$.Rig().apply(evidence$44).one$mcD$sp(), evidence$43, evidence$44, evidence$45);
   }

   public Polynomial x$mDc$sp(final Eq evidence$46, final Rig evidence$47, final ClassTag evidence$48) {
      return this.linear$mDc$sp(spire.algebra.package$.MODULE$.Rig().apply(evidence$47).one$mcD$sp(), evidence$46, evidence$47, evidence$48);
   }

   public Polynomial twox$mDc$sp(final Eq evidence$49, final Rig evidence$50, final ClassTag evidence$51) {
      return this.linear$mDc$sp(evidence$50.plus$mcD$sp(spire.algebra.package$.MODULE$.Rig().apply(evidence$50).one$mcD$sp(), spire.algebra.package$.MODULE$.Rig().apply(evidence$50).one$mcD$sp()), evidence$49, evidence$50, evidence$51);
   }

   private final Tuple2 split$mDc$sp(final Polynomial poly, final ClassTag evidence$52) {
      ArrayBuilder es = spire.scalacompat.package$.MODULE$.arrayBuilderMake(scala.reflect.ClassTag..MODULE$.Int());
      ArrayBuilder cs = spire.scalacompat.package$.MODULE$.arrayBuilderMake(evidence$52);
      poly.foreach$mcD$sp((e, c) -> $anonfun$split$2(es, cs, BoxesRunTime.unboxToInt(e), BoxesRunTime.unboxToDouble(c)));
      return new Tuple2(es.result(), cs.result());
   }

   // $FF: synthetic method
   private static final T$2$ T$lzycompute$1(final LazyRef T$module$1) {
      synchronized(T$module$1){}

      T$2$ var2;
      try {
         class T$2$ extends AbstractFunction3 implements Serializable {
            public final String toString() {
               return "T";
            }

            public T$1 apply(final Rational c, final String v, final int e) {
               return new T$1(c, v, e);
            }

            public Option unapply(final T$1 x$0) {
               return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.c(), x$0.v(), BoxesRunTime.boxToInteger(x$0.e()))));
            }

            public T$2$() {
            }
         }

         var2 = T$module$1.initialized() ? (T$2$)T$module$1.value() : (T$2$)T$module$1.initialize(new T$2$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final T$2$ T$3(final LazyRef T$module$1) {
      return T$module$1.initialized() ? (T$2$)T$module$1.value() : T$lzycompute$1(T$module$1);
   }

   private final List parse$1(final String s, final List ts, final LazyRef T$module$1) {
      while(!s.isEmpty()) {
         Option var9 = operRe.findPrefixMatchOf(s);
         Tuple2 var6;
         if (var9 instanceof Some) {
            Some var10 = (Some)var9;
            Regex.Match m = (Regex.Match)var10.value();
            var6 = new Tuple2(m.group(1), s.substring(m.end()));
         } else {
            if (!scala.None..MODULE$.equals(var9)) {
               throw new MatchError(var9);
            }

            if (!ts.isEmpty()) {
               throw new IllegalArgumentException(s);
            }

            var6 = new Tuple2("+", s);
         }

         if (var6 == null) {
            throw new MatchError(var6);
         }

         String s2;
         Regex.Match m2;
         String var10000;
         label81: {
            String c0;
            label80: {
               String op = (String)var6._1();
               String s2 = (String)var6._2();
               Tuple2 var5 = new Tuple2(op, s2);
               String op = (String)var5._1();
               s2 = (String)var5._2();
               m2 = (Regex.Match)termRe.findPrefixMatchOf(s2).getOrElse(() -> {
                  throw new IllegalArgumentException(s2);
               });
               c0 = (String)scala.Option..MODULE$.apply(m2.group(1)).getOrElse(() -> "1");
               String var19 = "-";
               if (op == null) {
                  if (var19 == null) {
                     break label80;
                  }
               } else if (op.equals(var19)) {
                  break label80;
               }

               var10000 = c0;
               break label81;
            }

            var10000 = (new StringBuilder(1)).append("-").append(c0).toString();
         }

         String c;
         String v;
         label73: {
            String e0;
            label89: {
               c = var10000;
               v = (String)scala.Option..MODULE$.apply(m2.group(2)).getOrElse(() -> "");
               e0 = (String)scala.Option..MODULE$.apply(m2.group(3)).getOrElse(() -> "");
               String var23 = "";
               if (e0 == null) {
                  if (var23 != null) {
                     break label89;
                  }
               } else if (!e0.equals(var23)) {
                  break label89;
               }

               label65: {
                  String var24 = "";
                  if (v == null) {
                     if (var24 == null) {
                        break label65;
                     }
                  } else if (v.equals(var24)) {
                     break label65;
                  }

                  var10000 = "1";
                  break label73;
               }

               var10000 = "0";
               break label73;
            }

            var10000 = e0;
         }

         String e = var10000;

         try {
            var29 = this.T$3(T$module$1).apply(Rational$.MODULE$.apply(c), v, .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(e)));
         } catch (Exception var27) {
            throw new IllegalArgumentException((new StringBuilder(17)).append("illegal term: ").append(c).append("*x^").append(e).toString());
         }

         T$1 t = var29;
         var10000 = s2.substring(m2.end());
         ts = BoxesRunTime.equalsNumObject(t.c(), BoxesRunTime.boxToInteger(0)) ? ts : ts.$colon$colon(t);
         s = var10000;
      }

      return ts;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$6(final String x$3) {
      boolean var10000;
      label23: {
         String var1 = "";
         if (x$3 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!x$3.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final ArrayBuilder $anonfun$split$1(final ArrayBuilder es$1, final ArrayBuilder cs$1, final int e, final Object c) {
      es$1.$plus$eq(BoxesRunTime.boxToInteger(e));
      return (ArrayBuilder)cs$1.$plus$eq(c);
   }

   private final Polynomial loop$1(final Polynomial p, final List xs, final List pts, final Field evidence$53$1, final Eq evidence$54$1, final ClassTag evidence$55$1) {
      while(true) {
         Nil var10000 = scala.package..MODULE$.Nil();
         if (var10000 == null) {
            if (pts == null) {
               break;
            }
         } else if (var10000.equals(pts)) {
            break;
         }

         if (pts instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var11 = (scala.collection.immutable..colon.colon)pts;
            Tuple2 var12 = (Tuple2)var11.head();
            List tail = var11.next$access$1();
            if (var12 != null) {
               Object x = var12._1();
               Object y = var12._2();
               Polynomial c = this.constant(evidence$53$1.div(evidence$53$1.minus(y, p.apply(x, evidence$53$1)), spire.syntax.std.package.seq$.MODULE$.seqOps(xs.map((x$4) -> evidence$53$1.minus(x, x$4))).qproduct(evidence$53$1)), evidence$54$1, evidence$53$1, evidence$55$1);
               Polynomial prod = (Polynomial)xs.foldLeft(this.one(evidence$54$1, evidence$53$1, evidence$55$1), (prodx, xn) -> prodx.$times(MODULE$.x(evidence$54$1, evidence$53$1, evidence$55$1).$minus(MODULE$.constant(xn, evidence$54$1, evidence$53$1, evidence$55$1), evidence$53$1, evidence$54$1), evidence$53$1, evidence$54$1));
               Polynomial var19 = p.$plus(c.$times(prod, evidence$53$1, evidence$54$1), evidence$53$1, evidence$54$1);
               List var10001 = xs.$colon$colon(x);
               pts = tail;
               xs = var10001;
               p = var19;
               continue;
            }
         }

         throw new MatchError(pts);
      }

      return p;
   }

   // $FF: synthetic method
   public static final ArrayBuilder $anonfun$split$2(final ArrayBuilder es$2, final ArrayBuilder cs$2, final int e, final double c) {
      es$2.$plus$eq(BoxesRunTime.boxToInteger(e));
      return (ArrayBuilder)cs$2.$plus$eq(BoxesRunTime.boxToDouble(c));
   }

   private Polynomial$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
