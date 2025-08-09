package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ClassTag;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;
import spire.algebra.package$;
import spire.math.Polynomial;
import spire.syntax.std.package;

public final class PolySparse$ implements Serializable {
   public static final PolySparse$ MODULE$ = new PolySparse$();

   public final PolySparse dense2sparse(final PolyDense poly, final Semiring evidence$1, final Eq evidence$2, final ClassTag evidence$3) {
      Object cs = poly.coeffs();
      int[] es = new int[.MODULE$.array_length(cs)];

      for(int index$macro$1 = 0; index$macro$1 < es.length; es[index$macro$1] = index$macro$1++) {
      }

      return this.safe(es, cs, evidence$1, evidence$2, evidence$3);
   }

   public final PolySparse safe(final int[] exp, final Object coeff, final Semiring evidence$4, final Eq evidence$5, final ClassTag evidence$6) {
      int len = 0;

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(coeff); ++index$macro$1) {
         if (evidence$5.neqv(.MODULE$.array_apply(coeff, index$macro$1), package$.MODULE$.Semiring().apply(evidence$4).zero())) {
            ++len;
         }
      }

      PolySparse var10000;
      if (len == .MODULE$.array_length(coeff)) {
         var10000 = new PolySparse(exp, coeff, evidence$6);
      } else {
         int[] es = new int[len];
         Object cs = evidence$6.newArray(len);
         var10000 = this.loop$2(0, 0, coeff, evidence$5, evidence$4, es, exp, cs, evidence$6);
      }

      return var10000;
   }

   public final PolySparse apply(final IterableOnce data, final Semiring evidence$7, final Eq evidence$8, final ClassTag evidence$9) {
      ObjectRef expBldr = ObjectRef.create(spire.scalacompat.package$.MODULE$.arrayBuilderMake(scala.reflect.ClassTag..MODULE$.Int()));
      ObjectRef coeffBldr = ObjectRef.create(spire.scalacompat.package$.MODULE$.arrayBuilderMake(evidence$9));
      Object zero = package$.MODULE$.Semiring().apply(evidence$7).zero();
      BooleanRef inReverseOrder = BooleanRef.create(true);
      BooleanRef inOrder = BooleanRef.create(true);
      IntRef lastDeg = IntRef.create(-1);
      data.iterator().foreach((x0$1) -> {
         $anonfun$apply$1(evidence$8, zero, expBldr, coeffBldr, inOrder, lastDeg, inReverseOrder, x0$1);
         return BoxedUnit.UNIT;
      });
      int[] exp = (int[])((ArrayBuilder)expBldr.elem).result();
      Object coeff = ((ArrayBuilder)coeffBldr.elem).result();
      PolySparse var10000;
      if (inOrder.elem) {
         var10000 = this.apply(exp, coeff, evidence$9);
      } else if (inReverseOrder.elem) {
         this.spire$math$poly$PolySparse$$reverse(exp);
         this.spire$math$poly$PolySparse$$reverse(coeff);
         var10000 = this.apply(exp, coeff, evidence$9);
      } else {
         int[] indices = scala.Array..MODULE$.range(0, exp.length);
         package.array$.MODULE$.arrayOps$mIc$sp(indices).qsortBy$mIcI$sp((JFunction1.mcII.sp)(x$21) -> exp[x$21], (Order)spire.std.package.int$.MODULE$.IntAlgebra(), scala.reflect.ClassTag..MODULE$.Int());
         expBldr.elem = spire.scalacompat.package$.MODULE$.arrayBuilderMake(scala.reflect.ClassTag..MODULE$.Int());
         coeffBldr.elem = spire.scalacompat.package$.MODULE$.arrayBuilderMake(evidence$9);
         int i = 1;
         int j = indices[0];
         int e = exp[j];

         Object c;
         for(c = .MODULE$.array_apply(coeff, j); i < indices.length; ++i) {
            int j0 = indices[i];
            int e0 = exp[j0];
            Object c0 = .MODULE$.array_apply(coeff, j0);
            if (e != e0) {
               if (!evidence$7.isZero(c, evidence$8)) {
                  ((ArrayBuilder)expBldr.elem).$plus$eq(BoxesRunTime.boxToInteger(e));
                  ((ArrayBuilder)coeffBldr.elem).$plus$eq(c);
               } else {
                  BoxedUnit var22 = BoxedUnit.UNIT;
               }

               c = c0;
            } else {
               c = evidence$7.plus(c, c0);
            }

            e = e0;
         }

         if (!evidence$7.isZero(c, evidence$8)) {
            ((ArrayBuilder)expBldr.elem).$plus$eq(BoxesRunTime.boxToInteger(e));
            ((ArrayBuilder)coeffBldr.elem).$plus$eq(c);
         } else {
            BoxedUnit var23 = BoxedUnit.UNIT;
         }

         PolySparse poly = this.apply((int[])((ArrayBuilder)expBldr.elem).result(), ((ArrayBuilder)coeffBldr.elem).result(), evidence$9);
         var10000 = poly;
      }

      return var10000;
   }

   public void spire$math$poly$PolySparse$$reverse(final Object arr) {
      int i = 0;

      for(int j = .MODULE$.array_length(arr) - 1; i < j; --j) {
         Object tmp = .MODULE$.array_apply(arr, i);
         .MODULE$.array_update(arr, i, .MODULE$.array_apply(arr, j));
         .MODULE$.array_update(arr, j, tmp);
         ++i;
      }

   }

   public final PolySparse apply(final Map data, final Semiring evidence$10, final Eq evidence$11, final ClassTag evidence$12) {
      Tuple2[] data0 = (Tuple2[])data.toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      package.array$.MODULE$.arrayOps(data0).qsortBy$mIc$sp((x$22) -> BoxesRunTime.boxToInteger($anonfun$apply$3(x$22)), (Order)spire.std.package.int$.MODULE$.IntAlgebra(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      int[] es = new int[data0.length];
      Object cs = evidence$12.newArray(data0.length);

      for(int index$macro$1 = 0; index$macro$1 < data0.length; ++index$macro$1) {
         Tuple2 var11 = data0[index$macro$1];
         if (var11 == null) {
            throw new MatchError(var11);
         }

         int e = var11._1$mcI$sp();
         Object c = var11._2();
         Tuple2 var5 = new Tuple2(BoxesRunTime.boxToInteger(e), c);
         int e = var5._1$mcI$sp();
         Object c = var5._2();
         es[index$macro$1] = e;
         .MODULE$.array_update(cs, index$macro$1, c);
      }

      return this.safe(es, cs, evidence$10, evidence$11, evidence$12);
   }

   public final PolySparse apply(final Polynomial poly, final Semiring evidence$13, final Eq evidence$14, final ClassTag evidence$15) {
      PolySparse var5;
      if (poly instanceof PolySparse) {
         PolySparse var7 = (PolySparse)poly;
         var5 = var7;
      } else if (poly instanceof PolyDense) {
         var5 = this.dense2sparse((PolyDense)poly, evidence$13, evidence$14, evidence$15);
      } else {
         IntRef len = IntRef.create(0);
         poly.foreachNonZero((x$26, x$27) -> {
            $anonfun$apply$4(len, BoxesRunTime.unboxToInt(x$26), x$27);
            return BoxedUnit.UNIT;
         }, evidence$13, evidence$14);
         int[] es = new int[len.elem];
         Object cs = evidence$15.newArray(len.elem);
         IntRef i = IntRef.create(0);
         poly.foreachNonZero((e, c) -> {
            $anonfun$apply$5(es, i, cs, BoxesRunTime.unboxToInt(e), c);
            return BoxedUnit.UNIT;
         }, evidence$13, evidence$14);
         var5 = this.safe(es, cs, evidence$13, evidence$14, evidence$15);
      }

      return var5;
   }

   public final PolySparse zero(final Semiring evidence$16, final Eq evidence$17, final ClassTag evidence$18) {
      return new PolySparse(new int[0], evidence$18.newArray(0), evidence$18);
   }

   public final PolySparse spire$math$poly$PolySparse$$multiplyTerm(final PolySparse poly, final Object c, final int e, final Semiring evidence$19, final Eq evidence$20, final ClassTag evidence$21) {
      int[] exp = poly.exp();
      Object coeff = poly.coeff();
      Object cs = evidence$21.newArray(.MODULE$.array_length(coeff));
      int[] es = new int[exp.length];

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(coeff); ++index$macro$1) {
         .MODULE$.array_update(cs, index$macro$1, evidence$19.times(c, .MODULE$.array_apply(coeff, index$macro$1)));
         es[index$macro$1] = exp[index$macro$1] + e;
      }

      return new PolySparse(es, cs, evidence$21);
   }

   public final PolySparse spire$math$poly$PolySparse$$multiplySparse(final PolySparse lhs, final PolySparse rhs, final Semiring evidence$22, final Eq evidence$23, final ClassTag evidence$24) {
      int[] lexp = lhs.exp();
      Object lcoeff = lhs.coeff();
      PolySparse sum = new PolySparse(new int[0], evidence$24.newArray(0), evidence$24);

      for(int index$macro$1 = 0; index$macro$1 < lexp.length; ++index$macro$1) {
         sum = this.spire$math$poly$PolySparse$$addSparse(sum, this.spire$math$poly$PolySparse$$multiplyTerm(rhs, .MODULE$.array_apply(lcoeff, index$macro$1), lexp[index$macro$1], evidence$22, evidence$23, evidence$24), evidence$23, evidence$22, evidence$24);
      }

      return sum;
   }

   private final int countSumTerms(final PolySparse lhs, final PolySparse rhs, final int lOffset, final int rOffset) {
      if (lhs != null) {
         int[] lexp = lhs.exp();
         Object lcoeff = lhs.coeff();
         Tuple2 var6 = new Tuple2(lexp, lcoeff);
         int[] lexp = (int[])var6._1();
         Object var12 = var6._2();
         if (rhs != null) {
            int[] rexp = rhs.exp();
            Object rcoeff = rhs.coeff();
            Tuple2 var5 = new Tuple2(rexp, rcoeff);
            int[] rexp = (int[])var5._1();
            Object var18 = var5._2();
            return this.loop$3(0, 0, 0, lexp, rexp, lOffset, rOffset);
         } else {
            throw new MatchError(rhs);
         }
      } else {
         throw new MatchError(lhs);
      }
   }

   private final int countSumTerms$default$3() {
      return 0;
   }

   private final int countSumTerms$default$4() {
      return 0;
   }

   public final PolySparse spire$math$poly$PolySparse$$addSparse(final PolySparse lhs, final PolySparse rhs, final Eq evidence$25, final Semiring evidence$26, final ClassTag evidence$27) {
      if (lhs != null) {
         int[] lexp = lhs.exp();
         Object lcoeff = lhs.coeff();
         Tuple2 var7 = new Tuple2(lexp, lcoeff);
         int[] lexp = (int[])var7._1();
         Object lcoeff = var7._2();
         if (rhs != null) {
            int[] rexp = rhs.exp();
            Object rcoeff = rhs.coeff();
            Tuple2 var6 = new Tuple2(rexp, rcoeff);
            int[] rexp = (int[])var6._1();
            Object rcoeff = var6._2();
            int len = this.countSumTerms(lhs, rhs, this.countSumTerms$default$3(), this.countSumTerms$default$4());
            int[] es = new int[len];
            Object cs = evidence$27.newArray(len);
            return this.sum$1(0, 0, 0, lexp, rexp, es, cs, evidence$26, lcoeff, rcoeff, evidence$25, evidence$27);
         } else {
            throw new MatchError(rhs);
         }
      } else {
         throw new MatchError(lhs);
      }
   }

   public final PolySparse spire$math$poly$PolySparse$$subtractScaled(final PolySparse lhs, final Object c, final int e, final PolySparse rhs, final Eq evidence$28, final Rng evidence$29, final ClassTag evidence$30) {
      if (lhs != null) {
         int[] lexp = lhs.exp();
         Object lcoeff = lhs.coeff();
         Tuple2 var9 = new Tuple2(lexp, lcoeff);
         int[] lexp = (int[])var9._1();
         Object lcoeff = var9._2();
         if (rhs != null) {
            int[] rexp = rhs.exp();
            Object rcoeff = rhs.coeff();
            Tuple2 var8 = new Tuple2(rexp, rcoeff);
            int[] rexp = (int[])var8._1();
            Object rcoeff = var8._2();
            int len = this.countSumTerms(lhs, rhs, 0, e);
            int[] es = new int[len];
            Object cs = evidence$30.newArray(len);
            return this.loop$4(0, 0, 0, lexp, rexp, e, es, cs, evidence$29, lcoeff, c, rcoeff, evidence$28, evidence$30);
         } else {
            throw new MatchError(rhs);
         }
      } else {
         throw new MatchError(lhs);
      }
   }

   public final Tuple2 quotmodSparse(final PolySparse lhs, final PolySparse rhs, final Field evidence$31, final Eq evidence$32, final ClassTag evidence$33) {
      int rdegree = rhs.degree();
      Object rmaxCoeff = rhs.maxOrderTermCoeff(evidence$31);
      return this.loop$5(scala.package..MODULE$.Nil(), lhs, rdegree, evidence$31, rmaxCoeff, rhs, evidence$32, evidence$33);
   }

   public PolySparse apply(final int[] exp, final Object coeff, final ClassTag ct) {
      return new PolySparse(exp, coeff, ct);
   }

   public Option unapply(final PolySparse x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.exp(), x$0.coeff())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PolySparse$.class);
   }

   public final PolySparse dense2sparse$mDc$sp(final PolyDense poly, final Semiring evidence$1, final Eq evidence$2, final ClassTag evidence$3) {
      double[] cs = poly.coeffs$mcD$sp();
      int[] es = new int[cs.length];

      for(int index$macro$1 = 0; index$macro$1 < es.length; es[index$macro$1] = index$macro$1++) {
      }

      return this.safe$mDc$sp(es, cs, evidence$1, evidence$2, evidence$3);
   }

   public final PolySparse safe$mDc$sp(final int[] exp, final double[] coeff, final Semiring evidence$4, final Eq evidence$5, final ClassTag evidence$6) {
      int len = 0;

      for(int index$macro$1 = 0; index$macro$1 < coeff.length; ++index$macro$1) {
         if (evidence$5.neqv$mcD$sp(coeff[index$macro$1], package$.MODULE$.Semiring().apply(evidence$4).zero$mcD$sp())) {
            ++len;
         }
      }

      Object var10000;
      if (len == coeff.length) {
         var10000 = new PolySparse$mcD$sp(exp, coeff, evidence$6);
      } else {
         int[] es = new int[len];
         double[] cs = (double[])evidence$6.newArray(len);
         var10000 = this.loop$6(0, 0, coeff, evidence$5, evidence$4, es, exp, cs, evidence$6);
      }

      return (PolySparse)var10000;
   }

   public final PolySparse apply$mDc$sp(final IterableOnce data, final Semiring evidence$7, final Eq evidence$8, final ClassTag evidence$9) {
      ObjectRef expBldr = ObjectRef.create(spire.scalacompat.package$.MODULE$.arrayBuilderMake(scala.reflect.ClassTag..MODULE$.Int()));
      ObjectRef coeffBldr = ObjectRef.create(spire.scalacompat.package$.MODULE$.arrayBuilderMake(evidence$9));
      double zero = package$.MODULE$.Semiring().apply(evidence$7).zero$mcD$sp();
      BooleanRef inReverseOrder = BooleanRef.create(true);
      BooleanRef inOrder = BooleanRef.create(true);
      IntRef lastDeg = IntRef.create(-1);
      data.iterator().foreach((x0$1) -> {
         $anonfun$apply$6(evidence$8, zero, expBldr, coeffBldr, inOrder, lastDeg, inReverseOrder, x0$1);
         return BoxedUnit.UNIT;
      });
      int[] exp = (int[])((ArrayBuilder)expBldr.elem).result();
      double[] coeff = (double[])((ArrayBuilder)coeffBldr.elem).result();
      PolySparse var10000;
      if (inOrder.elem) {
         var10000 = this.apply$mDc$sp(exp, coeff, evidence$9);
      } else if (inReverseOrder.elem) {
         this.spire$math$poly$PolySparse$$reverse(exp);
         this.spire$math$poly$PolySparse$$reverse(coeff);
         var10000 = this.apply$mDc$sp(exp, coeff, evidence$9);
      } else {
         int[] indices = scala.Array..MODULE$.range(0, exp.length);
         package.array$.MODULE$.arrayOps$mIc$sp(indices).qsortBy$mIcI$sp((JFunction1.mcII.sp)(x$21) -> exp[x$21], (Order)spire.std.package.int$.MODULE$.IntAlgebra(), scala.reflect.ClassTag..MODULE$.Int());
         expBldr.elem = spire.scalacompat.package$.MODULE$.arrayBuilderMake(scala.reflect.ClassTag..MODULE$.Int());
         coeffBldr.elem = spire.scalacompat.package$.MODULE$.arrayBuilderMake(evidence$9);
         int i = 1;
         int j = indices[0];
         int e = exp[j];

         double c;
         for(c = coeff[j]; i < indices.length; ++i) {
            int j0 = indices[i];
            int e0 = exp[j0];
            double c0 = coeff[j0];
            if (e != e0) {
               if (!evidence$7.isZero$mcD$sp(c, evidence$8)) {
                  ((ArrayBuilder)expBldr.elem).$plus$eq(BoxesRunTime.boxToInteger(e));
                  ((ArrayBuilder)coeffBldr.elem).$plus$eq(BoxesRunTime.boxToDouble(c));
               } else {
                  BoxedUnit var25 = BoxedUnit.UNIT;
               }

               c = c0;
            } else {
               c = evidence$7.plus$mcD$sp(c, c0);
            }

            e = e0;
         }

         if (!evidence$7.isZero$mcD$sp(c, evidence$8)) {
            ((ArrayBuilder)expBldr.elem).$plus$eq(BoxesRunTime.boxToInteger(e));
            ((ArrayBuilder)coeffBldr.elem).$plus$eq(BoxesRunTime.boxToDouble(c));
         } else {
            BoxedUnit var26 = BoxedUnit.UNIT;
         }

         PolySparse poly = this.apply$mDc$sp((int[])((ArrayBuilder)expBldr.elem).result(), (double[])((ArrayBuilder)coeffBldr.elem).result(), evidence$9);
         var10000 = poly;
      }

      return var10000;
   }

   private void reverse$mDc$sp(final double[] arr) {
      int i = 0;

      for(int j = arr.length - 1; i < j; --j) {
         double tmp = arr[i];
         arr[i] = arr[j];
         arr[j] = tmp;
         ++i;
      }

   }

   public final PolySparse apply$mDc$sp(final Map data, final Semiring evidence$10, final Eq evidence$11, final ClassTag evidence$12) {
      Tuple2[] data0 = (Tuple2[])data.toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      package.array$.MODULE$.arrayOps(data0).qsortBy$mIc$sp((x$22) -> BoxesRunTime.boxToInteger($anonfun$apply$8(x$22)), (Order)spire.std.package.int$.MODULE$.IntAlgebra(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      int[] es = new int[data0.length];
      double[] cs = (double[])evidence$12.newArray(data0.length);

      for(int index$macro$1 = 0; index$macro$1 < data0.length; ++index$macro$1) {
         Tuple2 var11 = data0[index$macro$1];
         if (var11 == null) {
            throw new MatchError(var11);
         }

         int e = var11._1$mcI$sp();
         double c = var11._2$mcD$sp();
         Tuple2.mcID.sp var5 = new Tuple2.mcID.sp(e, c);
         int e = ((Tuple2)var5)._1$mcI$sp();
         double c = ((Tuple2)var5)._2$mcD$sp();
         es[index$macro$1] = e;
         cs[index$macro$1] = c;
      }

      return this.safe$mDc$sp(es, cs, evidence$10, evidence$11, evidence$12);
   }

   public final PolySparse apply$mDc$sp(final Polynomial poly, final Semiring evidence$13, final Eq evidence$14, final ClassTag evidence$15) {
      PolySparse var5;
      if (poly instanceof PolySparse) {
         PolySparse var7 = (PolySparse)poly;
         var5 = var7;
      } else if (poly instanceof PolyDense) {
         var5 = this.dense2sparse$mDc$sp((PolyDense)poly, evidence$13, evidence$14, evidence$15);
      } else {
         IntRef len = IntRef.create(0);
         poly.foreachNonZero$mcD$sp((JFunction2.mcVID.sp)(x$26, x$27) -> ++len.elem, evidence$13, evidence$14);
         int[] es = new int[len.elem];
         double[] cs = (double[])evidence$15.newArray(len.elem);
         IntRef i = IntRef.create(0);
         poly.foreachNonZero$mcD$sp((JFunction2.mcVID.sp)(e, c) -> {
            es[i.elem] = e;
            cs[i.elem] = c;
            ++i.elem;
         }, evidence$13, evidence$14);
         var5 = this.safe$mDc$sp(es, cs, evidence$13, evidence$14, evidence$15);
      }

      return var5;
   }

   public final PolySparse zero$mDc$sp(final Semiring evidence$16, final Eq evidence$17, final ClassTag evidence$18) {
      return new PolySparse$mcD$sp(new int[0], (double[])evidence$18.newArray(0), evidence$18);
   }

   private final PolySparse multiplyTerm$mDc$sp(final PolySparse poly, final double c, final int e, final Semiring evidence$19, final Eq evidence$20, final ClassTag evidence$21) {
      int[] exp = poly.exp();
      double[] coeff = poly.coeff$mcD$sp();
      double[] cs = (double[])evidence$21.newArray(coeff.length);
      int[] es = new int[exp.length];

      for(int index$macro$1 = 0; index$macro$1 < coeff.length; ++index$macro$1) {
         cs[index$macro$1] = evidence$19.times$mcD$sp(c, coeff[index$macro$1]);
         es[index$macro$1] = exp[index$macro$1] + e;
      }

      return new PolySparse$mcD$sp(es, cs, evidence$21);
   }

   private final PolySparse multiplySparse$mDc$sp(final PolySparse lhs, final PolySparse rhs, final Semiring evidence$22, final Eq evidence$23, final ClassTag evidence$24) {
      int[] lexp = lhs.exp();
      double[] lcoeff = lhs.coeff$mcD$sp();
      PolySparse sum = new PolySparse$mcD$sp(new int[0], (double[])evidence$24.newArray(0), evidence$24);

      for(int index$macro$1 = 0; index$macro$1 < lexp.length; ++index$macro$1) {
         sum = this.spire$math$poly$PolySparse$$addSparse(sum, this.spire$math$poly$PolySparse$$multiplyTerm(rhs, BoxesRunTime.boxToDouble(lcoeff[index$macro$1]), lexp[index$macro$1], evidence$22, evidence$23, evidence$24), evidence$23, evidence$22, evidence$24);
      }

      return sum;
   }

   private final int countSumTerms$mDc$sp(final PolySparse lhs, final PolySparse rhs, final int lOffset, final int rOffset) {
      if (lhs != null) {
         int[] lexp = lhs.exp();
         double[] lcoeff = lhs.coeff$mcD$sp();
         Tuple2 var6 = new Tuple2(lexp, lcoeff);
         int[] lexp = (int[])var6._1();
         double[] var12 = (double[])var6._2();
         if (rhs != null) {
            int[] rexp = rhs.exp();
            double[] rcoeff = rhs.coeff$mcD$sp();
            Tuple2 var5 = new Tuple2(rexp, rcoeff);
            int[] rexp = (int[])var5._1();
            double[] var18 = (double[])var5._2();
            return this.loop$7(0, 0, 0, lexp, rexp, lOffset, rOffset);
         } else {
            throw new MatchError(rhs);
         }
      } else {
         throw new MatchError(lhs);
      }
   }

   public final Tuple2 quotmodSparse$mDc$sp(final PolySparse lhs, final PolySparse rhs, final Field evidence$31, final Eq evidence$32, final ClassTag evidence$33) {
      int rdegree = rhs.degree();
      double rmaxCoeff = rhs.maxOrderTermCoeff$mcD$sp(evidence$31);
      return this.loop$8(scala.package..MODULE$.Nil(), lhs, rdegree, evidence$31, rmaxCoeff, rhs, evidence$32, evidence$33);
   }

   public PolySparse apply$mDc$sp(final int[] exp, final double[] coeff, final ClassTag ct) {
      return new PolySparse$mcD$sp(exp, coeff, ct);
   }

   public Option unapply$mDc$sp(final PolySparse x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.exp(), x$0.coeff$mcD$sp())));
   }

   private final PolySparse loop$2(final int i, final int j, final Object coeff$1, final Eq evidence$5$1, final Semiring evidence$4$1, final int[] es$2, final int[] exp$1, final Object cs$2, final ClassTag evidence$6$1) {
      while(i < .MODULE$.array_length(coeff$1)) {
         Object c = .MODULE$.array_apply(coeff$1, i);
         if (evidence$5$1.neqv(c, package$.MODULE$.Semiring().apply(evidence$4$1).zero())) {
            es$2[j] = exp$1[i];
            .MODULE$.array_update(cs$2, j, c);
            int var12 = i + 1;
            ++j;
            i = var12;
         } else {
            int var10000 = i + 1;
            j = j;
            i = var10000;
         }
      }

      return new PolySparse(es$2, cs$2, evidence$6$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$1(final Eq evidence$8$1, final Object zero$1, final ObjectRef expBldr$1, final ObjectRef coeffBldr$1, final BooleanRef inOrder$1, final IntRef lastDeg$1, final BooleanRef inReverseOrder$1, final Term x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         Object c = x0$1.coeff();
         int i = x0$1.exp();
         if (evidence$8$1.neqv(c, zero$1)) {
            ((ArrayBuilder)expBldr$1.elem).$plus$eq(BoxesRunTime.boxToInteger(i));
            ((ArrayBuilder)coeffBldr$1.elem).$plus$eq(c);
            inOrder$1.elem = inOrder$1.elem && lastDeg$1.elem < i;
            inReverseOrder$1.elem = inReverseOrder$1.elem && (lastDeg$1.elem < 0 || lastDeg$1.elem > i);
            lastDeg$1.elem = i;
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var12 = BoxedUnit.UNIT;
         }

      }
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$3(final Tuple2 x$22) {
      return x$22._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$4(final IntRef len$1, final int x$26, final Object x$27) {
      ++len$1.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$5(final int[] es$3, final IntRef i$1, final Object cs$3, final int e, final Object c) {
      es$3[i$1.elem] = e;
      .MODULE$.array_update(cs$3, i$1.elem, c);
      ++i$1.elem;
   }

   private final int loop$3(final int i, final int j, final int count, final int[] lexp$1, final int[] rexp$1, final int lOffset$1, final int rOffset$1) {
      while(i < lexp$1.length && j < rexp$1.length) {
         int cmp = lexp$1[i] + lOffset$1 - rexp$1[j] - rOffset$1;
         if (cmp == 0) {
            int var10 = i + 1;
            int var11 = j + 1;
            ++count;
            j = var11;
            i = var10;
         } else if (cmp < 0) {
            int var10000 = i + 1;
            ++count;
            j = j;
            i = var10000;
         } else {
            int var10001 = j + 1;
            ++count;
            j = var10001;
            i = i;
         }
      }

      return count + (lexp$1.length - i) + (rexp$1.length - j);
   }

   private final PolySparse sum$1(final int i, final int j, final int k, final int[] lexp$2, final int[] rexp$2, final int[] es$4, final Object cs$4, final Semiring evidence$26$1, final Object lcoeff$1, final Object rcoeff$1, final Eq evidence$25$1, final ClassTag evidence$27$1) {
      while(i < lexp$2.length && j < rexp$2.length) {
         int ei = lexp$2[i];
         int ej = rexp$2[j];
         if (ei == ej) {
            es$4[k] = ei;
            .MODULE$.array_update(cs$4, k, evidence$26$1.plus(.MODULE$.array_apply(lcoeff$1, i), .MODULE$.array_apply(rcoeff$1, j)));
            int var19 = i + 1;
            int var20 = j + 1;
            ++k;
            j = var20;
            i = var19;
         } else if (ei < ej) {
            es$4[k] = ei;
            .MODULE$.array_update(cs$4, k, .MODULE$.array_apply(lcoeff$1, i));
            int var10000 = i + 1;
            ++k;
            j = j;
            i = var10000;
         } else {
            es$4[k] = ej;
            .MODULE$.array_update(cs$4, k, .MODULE$.array_apply(rcoeff$1, j));
            int var10001 = j + 1;
            ++k;
            j = var10001;
            i = i;
         }
      }

      int k0 = k;

      for(int index$macro$1 = i; index$macro$1 < lexp$2.length; ++index$macro$1) {
         es$4[k0] = lexp$2[index$macro$1];
         .MODULE$.array_update(cs$4, k0, .MODULE$.array_apply(lcoeff$1, index$macro$1));
         ++k0;
      }

      for(int index$macro$2 = j; index$macro$2 < rexp$2.length; ++index$macro$2) {
         es$4[k0] = rexp$2[index$macro$2];
         .MODULE$.array_update(cs$4, k0, .MODULE$.array_apply(rcoeff$1, index$macro$2));
         ++k0;
      }

      return this.safe(es$4, cs$4, evidence$26$1, evidence$25$1, evidence$27$1);
   }

   private final PolySparse loop$4(final int i, final int j, final int k, final int[] lexp$3, final int[] rexp$3, final int e$1, final int[] es$5, final Object cs$5, final Rng evidence$29$1, final Object lcoeff$2, final Object c$1, final Object rcoeff$2, final Eq evidence$28$1, final ClassTag evidence$30$1) {
      while(i < lexp$3.length && j < rexp$3.length) {
         int ei = lexp$3[i];
         int ej = rexp$3[j] + e$1;
         if (ei == ej) {
            es$5[k] = ei;
            .MODULE$.array_update(cs$5, k, evidence$29$1.minus(.MODULE$.array_apply(lcoeff$2, i), evidence$29$1.times(c$1, .MODULE$.array_apply(rcoeff$2, j))));
            int var21 = i + 1;
            int var22 = j + 1;
            ++k;
            j = var22;
            i = var21;
         } else if (ei < ej) {
            es$5[k] = ei;
            .MODULE$.array_update(cs$5, k, .MODULE$.array_apply(lcoeff$2, i));
            int var10000 = i + 1;
            ++k;
            j = j;
            i = var10000;
         } else {
            es$5[k] = ej;
            .MODULE$.array_update(cs$5, k, evidence$29$1.times(evidence$29$1.negate(c$1), .MODULE$.array_apply(rcoeff$2, j)));
            int var10001 = j + 1;
            ++k;
            j = var10001;
            i = i;
         }
      }

      int k0 = k;

      for(int index$macro$1 = i; index$macro$1 < lexp$3.length; ++index$macro$1) {
         es$5[k0] = lexp$3[index$macro$1];
         .MODULE$.array_update(cs$5, k0, .MODULE$.array_apply(lcoeff$2, index$macro$1));
         ++k0;
      }

      for(int index$macro$2 = j; index$macro$2 < rexp$3.length; ++index$macro$2) {
         es$5[k0] = rexp$3[index$macro$2] + e$1;
         .MODULE$.array_update(cs$5, k0, evidence$29$1.times(evidence$29$1.negate(c$1), .MODULE$.array_apply(rcoeff$2, index$macro$2)));
         ++k0;
      }

      return this.safe(es$5, cs$5, evidence$29$1, evidence$28$1, evidence$30$1);
   }

   private final PolySparse inflate$1(final List ts, final int i, final int[] es, final Object cs, final ClassTag evidence$33$1) {
      while(true) {
         if (ts instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var9 = (scala.collection.immutable..colon.colon)ts;
            Term var10 = (Term)var9.head();
            List ts0 = var9.next$access$1();
            if (var10 != null) {
               Object c = var10.coeff();
               int e = var10.exp();
               es[i] = e;
               .MODULE$.array_update(cs, i, c);
               int var10001 = i + 1;
               cs = cs;
               es = es;
               i = var10001;
               ts = ts0;
               continue;
            }
         }

         label23: {
            Nil var10000 = scala.package..MODULE$.Nil();
            if (var10000 == null) {
               if (ts == null) {
                  break label23;
               }
            } else if (var10000.equals(ts)) {
               break label23;
            }

            throw new MatchError(ts);
         }

         PolySparse var7 = new PolySparse(es, cs, evidence$33$1);
         return var7;
      }
   }

   private final Tuple2 loop$5(final List quot, final PolySparse rem, final int rdegree$1, final Field evidence$31$1, final Object rmaxCoeff$1, final PolySparse rhs$1, final Eq evidence$32$1, final ClassTag evidence$33$1) {
      while(!rem.isZero() && rem.degree() >= rdegree$1) {
         Object c0 = evidence$31$1.div(rem.maxOrderTermCoeff(evidence$31$1), rmaxCoeff$1);
         int e0 = rem.degree() - rdegree$1;
         Term var12 = new Term(c0, e0);
         List var10000 = quot.$colon$colon(var12);
         rem = this.spire$math$poly$PolySparse$$subtractScaled(rem, c0, e0, rhs$1, evidence$32$1, evidence$31$1, evidence$33$1);
         quot = var10000;
      }

      int len = quot.size();
      return new Tuple2(this.inflate$1(quot, 0, new int[len], evidence$33$1.newArray(len), evidence$33$1), rem);
   }

   private final PolySparse loop$6(final int i, final int j, final double[] coeff$2, final Eq evidence$5$2, final Semiring evidence$4$2, final int[] es$6, final int[] exp$3, final double[] cs$6, final ClassTag evidence$6$2) {
      while(i < coeff$2.length) {
         double c = coeff$2[i];
         if (evidence$5$2.neqv$mcD$sp(c, package$.MODULE$.Semiring().apply(evidence$4$2).zero$mcD$sp())) {
            es$6[j] = exp$3[i];
            cs$6[j] = c;
            int var13 = i + 1;
            ++j;
            i = var13;
         } else {
            int var10000 = i + 1;
            j = j;
            i = var10000;
         }
      }

      return new PolySparse$mcD$sp(es$6, cs$6, evidence$6$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$apply$6(final Eq evidence$8$2, final double zero$2, final ObjectRef expBldr$2, final ObjectRef coeffBldr$2, final BooleanRef inOrder$2, final IntRef lastDeg$2, final BooleanRef inReverseOrder$2, final Term x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         double c = x0$1.coeff$mcD$sp();
         int i = x0$1.exp();
         if (evidence$8$2.neqv$mcD$sp(c, zero$2)) {
            ((ArrayBuilder)expBldr$2.elem).$plus$eq(BoxesRunTime.boxToInteger(i));
            ((ArrayBuilder)coeffBldr$2.elem).$plus$eq(BoxesRunTime.boxToDouble(c));
            inOrder$2.elem = inOrder$2.elem && lastDeg$2.elem < i;
            inReverseOrder$2.elem = inReverseOrder$2.elem && (lastDeg$2.elem < 0 || lastDeg$2.elem > i);
            lastDeg$2.elem = i;
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var14 = BoxedUnit.UNIT;
         }

      }
   }

   // $FF: synthetic method
   public static final int $anonfun$apply$8(final Tuple2 x$22) {
      return x$22._1$mcI$sp();
   }

   private final int loop$7(final int i, final int j, final int count, final int[] lexp$4, final int[] rexp$4, final int lOffset$2, final int rOffset$2) {
      while(i < lexp$4.length && j < rexp$4.length) {
         int cmp = lexp$4[i] + lOffset$2 - rexp$4[j] - rOffset$2;
         if (cmp == 0) {
            int var10 = i + 1;
            int var11 = j + 1;
            ++count;
            j = var11;
            i = var10;
         } else if (cmp < 0) {
            int var10000 = i + 1;
            ++count;
            j = j;
            i = var10000;
         } else {
            int var10001 = j + 1;
            ++count;
            j = var10001;
            i = i;
         }
      }

      return count + (lexp$4.length - i) + (rexp$4.length - j);
   }

   private final PolySparse inflate$2(final List ts, final int i, final int[] es, final double[] cs, final ClassTag evidence$33$2) {
      while(true) {
         if (ts instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var9 = (scala.collection.immutable..colon.colon)ts;
            Term var10 = (Term)var9.head();
            List ts0 = var9.next$access$1();
            if (var10 != null) {
               double c = var10.coeff$mcD$sp();
               int e = var10.exp();
               es[i] = e;
               cs[i] = c;
               int var10001 = i + 1;
               cs = cs;
               es = es;
               i = var10001;
               ts = ts0;
               continue;
            }
         }

         label23: {
            Nil var10000 = scala.package..MODULE$.Nil();
            if (var10000 == null) {
               if (ts == null) {
                  break label23;
               }
            } else if (var10000.equals(ts)) {
               break label23;
            }

            throw new MatchError(ts);
         }

         PolySparse$mcD$sp var7 = new PolySparse$mcD$sp(es, cs, evidence$33$2);
         return var7;
      }
   }

   private final Tuple2 loop$8(final List quot, final PolySparse rem, final int rdegree$2, final Field evidence$31$2, final double rmaxCoeff$2, final PolySparse rhs$2, final Eq evidence$32$2, final ClassTag evidence$33$2) {
      while(!rem.isZero() && rem.degree() >= rdegree$2) {
         double c0 = evidence$31$2.div$mcD$sp(rem.maxOrderTermCoeff$mcD$sp(evidence$31$2), rmaxCoeff$2);
         int e0 = rem.degree() - rdegree$2;
         Term$mcD$sp var14 = new Term$mcD$sp(c0, e0);
         List var10000 = quot.$colon$colon(var14);
         rem = this.spire$math$poly$PolySparse$$subtractScaled(rem, BoxesRunTime.boxToDouble(c0), e0, rhs$2, evidence$32$2, evidence$31$2, evidence$33$2);
         quot = var10000;
      }

      int len = quot.size();
      return new Tuple2(this.inflate$2(quot, 0, new int[len], (double[])evidence$33$2.newArray(len), evidence$33$2), rem);
   }

   private PolySparse$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
