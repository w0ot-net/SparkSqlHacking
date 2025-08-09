package spire.math.poly;

import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple5;
import scala.collection.Iterator;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Vector;
import scala.math.BigInt;
import scala.package.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Statics;
import spire.algebra.package$;
import spire.math.Interval;
import spire.math.Interval$;
import spire.math.Polynomial;
import spire.math.Rational;
import spire.math.Rational$;
import spire.optional.intervalGeometricPartialOrder$;
import spire.std.package;

public final class RootIsolator$ {
   public static final RootIsolator$ MODULE$ = new RootIsolator$();
   private static final RootIsolator RationalRootIsolator = new RootIsolator() {
      public Vector isolateRoots(final Polynomial poly) {
         return RootIsolator$.MODULE$.spire$math$poly$RootIsolator$$VAS(Roots$.MODULE$.removeFractions(poly));
      }
   };
   private static final RootIsolator BigDecimalRootIsolator = new RootIsolator() {
      public Vector isolateRoots(final Polynomial poly) {
         return RootIsolator$.MODULE$.spire$math$poly$RootIsolator$$VAS(Roots$.MODULE$.removeDecimal(poly));
      }
   };
   private static final RootIsolator BigIntRootIsolator = new RootIsolator() {
      public Vector isolateRoots(final Polynomial poly) {
         return RootIsolator$.MODULE$.spire$math$poly$RootIsolator$$VAS(poly);
      }
   };

   public RootIsolator RationalRootIsolator() {
      return RationalRootIsolator;
   }

   public RootIsolator BigDecimalRootIsolator() {
      return BigDecimalRootIsolator;
   }

   public RootIsolator BigIntRootIsolator() {
      return BigIntRootIsolator;
   }

   public final Vector spire$math$poly$RootIsolator$$VAS(final Polynomial poly) {
      LazyRef TransformedPoly$module = new LazyRef();
      Vector var10000;
      if (poly.isConstant()) {
         var10000 = .MODULE$.Vector().empty();
      } else {
         Interval zeroInterval = Interval$.MODULE$.point(Rational$.MODULE$.zero(), (Order)Rational$.MODULE$.RationalAlgebra());

         class TransformedPoly$1 implements Product, Serializable {
            private final Polynomial p;
            private final BigInt a;
            private final BigInt b;
            private final BigInt c;
            private final BigInt d;

            public Iterator productElementNames() {
               return Product.productElementNames$(this);
            }

            public Polynomial p() {
               return this.p;
            }

            public BigInt a() {
               return this.a;
            }

            public BigInt b() {
               return this.b;
            }

            public BigInt c() {
               return this.c;
            }

            public BigInt d() {
               return this.d;
            }

            public TransformedPoly$1 copy(final Polynomial p, final BigInt a, final BigInt b, final BigInt c, final BigInt d) {
               return new TransformedPoly$1(p, a, b, c, d);
            }

            public Polynomial copy$default$1() {
               return this.p();
            }

            public BigInt copy$default$2() {
               return this.a();
            }

            public BigInt copy$default$3() {
               return this.b();
            }

            public BigInt copy$default$4() {
               return this.c();
            }

            public BigInt copy$default$5() {
               return this.d();
            }

            public String productPrefix() {
               return "TransformedPoly";
            }

            public int productArity() {
               return 5;
            }

            public Object productElement(final int x$1) {
               Object var10000;
               switch (x$1) {
                  case 0:
                     var10000 = this.p();
                     break;
                  case 1:
                     var10000 = this.a();
                     break;
                  case 2:
                     var10000 = this.b();
                     break;
                  case 3:
                     var10000 = this.c();
                     break;
                  case 4:
                     var10000 = this.d();
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
               return x$1 instanceof TransformedPoly$1;
            }

            public String productElementName(final int x$1) {
               String var10000;
               switch (x$1) {
                  case 0:
                     var10000 = "p";
                     break;
                  case 1:
                     var10000 = "a";
                     break;
                  case 2:
                     var10000 = "b";
                     break;
                  case 3:
                     var10000 = "c";
                     break;
                  case 4:
                     var10000 = "d";
                     break;
                  default:
                     var10000 = (String)Statics.ioobe(x$1);
               }

               return var10000;
            }

            public int hashCode() {
               return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
            }

            public String toString() {
               return scala.runtime.ScalaRunTime..MODULE$._toString(this);
            }

            public boolean equals(final Object x$1) {
               boolean var15;
               if (this != x$1) {
                  label90: {
                     boolean var2;
                     if (x$1 instanceof TransformedPoly$1) {
                        var2 = true;
                     } else {
                        var2 = false;
                     }

                     if (var2) {
                        label72: {
                           label81: {
                              TransformedPoly$1 var4 = (TransformedPoly$1)x$1;
                              Polynomial var10000 = this.p();
                              Polynomial var5 = var4.p();
                              if (var10000 == null) {
                                 if (var5 != null) {
                                    break label81;
                                 }
                              } else if (!var10000.equals(var5)) {
                                 break label81;
                              }

                              BigInt var10 = this.a();
                              BigInt var6 = var4.a();
                              if (var10 == null) {
                                 if (var6 != null) {
                                    break label81;
                                 }
                              } else if (!var10.equals(var6)) {
                                 break label81;
                              }

                              var10 = this.b();
                              BigInt var7 = var4.b();
                              if (var10 == null) {
                                 if (var7 != null) {
                                    break label81;
                                 }
                              } else if (!var10.equals(var7)) {
                                 break label81;
                              }

                              var10 = this.c();
                              BigInt var8 = var4.c();
                              if (var10 == null) {
                                 if (var8 != null) {
                                    break label81;
                                 }
                              } else if (!var10.equals(var8)) {
                                 break label81;
                              }

                              var10 = this.d();
                              BigInt var9 = var4.d();
                              if (var10 == null) {
                                 if (var9 != null) {
                                    break label81;
                                 }
                              } else if (!var10.equals(var9)) {
                                 break label81;
                              }

                              if (var4.canEqual(this)) {
                                 var15 = true;
                                 break label72;
                              }
                           }

                           var15 = false;
                        }

                        if (var15) {
                           break label90;
                        }
                     }

                     var15 = false;
                     return var15;
                  }
               }

               var15 = true;
               return var15;
            }

            public TransformedPoly$1(final Polynomial p, final BigInt a, final BigInt b, final BigInt c, final BigInt d) {
               this.p = p;
               this.a = a;
               this.b = b;
               this.c = c;
               this.d = d;
               Product.$init$(this);
            }
         }

         TransformedPoly$1 var5 = this.TransformedPoly$3(TransformedPoly$module).apply(poly, scala.math.BigInt..MODULE$.int2bigInt(1), scala.math.BigInt..MODULE$.int2bigInt(0), scala.math.BigInt..MODULE$.int2bigInt(0), scala.math.BigInt..MODULE$.int2bigInt(1));
         Vector posRoots = this.rec$1(.MODULE$.Nil().$colon$colon(var5), rec$default$2$1(), TransformedPoly$module);
         TransformedPoly$1 var7 = this.TransformedPoly$3(TransformedPoly$module).apply(poly.flip(package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra()), scala.math.BigInt..MODULE$.int2bigInt(1), scala.math.BigInt..MODULE$.int2bigInt(0), scala.math.BigInt..MODULE$.int2bigInt(0), scala.math.BigInt..MODULE$.int2bigInt(1));
         Vector negRoots = (Vector)((StrictOptimizedIterableOps)this.rec$1(.MODULE$.Nil().$colon$colon(var7), rec$default$2$1(), TransformedPoly$module).map((x$2) -> x$2.unary_$minus(Rational$.MODULE$.RationalAlgebra()))).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$VAS$7(zeroInterval, x$3)));
         Vector roots = (Vector)negRoots.$plus$plus(posRoots);
         PartialOrder partialOrder = intervalGeometricPartialOrder$.MODULE$.intervalGeometricPartialOrder((Order)Rational$.MODULE$.RationalAlgebra());
         Order order = package$.MODULE$.Order().from((x, y) -> BoxesRunTime.boxToInteger($anonfun$VAS$8(partialOrder, x, y)));
         var10000 = (Vector)spire.syntax.std.package.seq$.MODULE$.seqOps(roots).qsorted(order, scala.reflect.ClassTag..MODULE$.apply(Interval.class), scala.collection.immutable.Vector..MODULE$.iterableFactory());
      }

      return var10000;
   }

   // $FF: synthetic method
   private static final TransformedPoly$2$ TransformedPoly$lzycompute$1(final LazyRef TransformedPoly$module$1) {
      synchronized(TransformedPoly$module$1){}

      TransformedPoly$2$ var2;
      try {
         class TransformedPoly$2$ extends AbstractFunction5 implements Serializable {
            public final String toString() {
               return "TransformedPoly";
            }

            public TransformedPoly$1 apply(final Polynomial p, final BigInt a, final BigInt b, final BigInt c, final BigInt d) {
               return new TransformedPoly$1(p, a, b, c, d);
            }

            public Option unapply(final TransformedPoly$1 x$0) {
               return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple5(x$0.p(), x$0.a(), x$0.b(), x$0.c(), x$0.d())));
            }

            public TransformedPoly$2$() {
            }
         }

         var2 = TransformedPoly$module$1.initialized() ? (TransformedPoly$2$)TransformedPoly$module$1.value() : (TransformedPoly$2$)TransformedPoly$module$1.initialize(new TransformedPoly$2$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final TransformedPoly$2$ TransformedPoly$3(final LazyRef TransformedPoly$module$1) {
      return TransformedPoly$module$1.initialized() ? (TransformedPoly$2$)TransformedPoly$module$1.value() : TransformedPoly$lzycompute$1(TransformedPoly$module$1);
   }

   private final List split1$1(final Polynomial p, final BigInt a, final BigInt b, final BigInt c, final BigInt d, final LazyRef TransformedPoly$module$1) {
      Polynomial r = p.shift(.MODULE$.BigInt().apply(1), package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra());
      TransformedPoly$1 rRoots = this.TransformedPoly$3(TransformedPoly$module$1).apply(r, a, b.$plus(a), c, d.$plus(c));
      List var10000;
      if (r.signVariations(package.bigInt$.MODULE$.BigIntAlgebra(), (Order)package.bigInt$.MODULE$.BigIntAlgebra(), (Signed)package.bigInt$.MODULE$.BigIntAlgebra()) < p.signVariations(package.bigInt$.MODULE$.BigIntAlgebra(), (Order)package.bigInt$.MODULE$.BigIntAlgebra(), (Signed)package.bigInt$.MODULE$.BigIntAlgebra())) {
         Polynomial l = p.reciprocal(package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra()).shift(.MODULE$.BigInt().apply(1), package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra()).removeZeroRoots(package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra());
         TransformedPoly$1 lRoots = this.TransformedPoly$3(TransformedPoly$module$1).apply(l, b, a.$plus(b), d, c.$plus(d));
         var10000 = .MODULE$.Nil().$colon$colon(rRoots).$colon$colon(lRoots);
      } else {
         var10000 = .MODULE$.Nil().$colon$colon(rRoots);
      }

      return var10000;
   }

   private static final Rational ub$1(final Polynomial p$1, final BigInt a$1, final BigInt b$1, final BigInt c$1, final BigInt d$1) {
      int exp = Roots$.MODULE$.upperBound(p$1);
      Rational ub0 = exp >= 0 ? Rational$.MODULE$.apply(.MODULE$.BigInt().apply(1).$less$less(exp)) : Rational$.MODULE$.apply(scala.math.BigInt..MODULE$.int2bigInt(1), .MODULE$.BigInt().apply(1).$less$less(-exp));
      return Rational$.MODULE$.apply(a$1).$times(ub0).$plus(Rational$.MODULE$.apply(b$1)).$div(Rational$.MODULE$.apply(c$1).$times(ub0).$plus(Rational$.MODULE$.apply(d$1)));
   }

   private final List findFloor$1(final Polynomial q, final Option floor, final BigInt a$1, final BigInt b$1, final BigInt c$1, final BigInt d$1, final LazyRef TransformedPoly$module$1) {
      while(true) {
         int lb = Roots$.MODULE$.lowerBound(q);
         Object var10000;
         if (lb < 0) {
            var10000 = (List)floor.fold(() -> this.split1$1(q, a$1, b$1, c$1, d$1, TransformedPoly$module$1), (hx) -> this.split1$1(q, a$1, b$1.$plus(a$1.$times(hx)), c$1, d$1.$plus(c$1.$times(hx)), TransformedPoly$module$1));
         } else {
            BigInt h = .MODULE$.BigInt().apply(1).$less$less(lb);
            Polynomial q0 = q.shift(h, package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra());
            if (q0.signVariations(package.bigInt$.MODULE$.BigIntAlgebra(), (Order)package.bigInt$.MODULE$.BigIntAlgebra(), (Signed)package.bigInt$.MODULE$.BigIntAlgebra()) != 0) {
               floor = new Some(floor.fold(() -> h, (x$1) -> x$1.$plus(h)));
               q = q0;
               continue;
            }

            var10000 = .MODULE$.Nil();
         }

         return (List)var10000;
      }
   }

   private final Vector rec$1(final List polys, final Vector acc, final LazyRef TransformedPoly$module$1) {
      while(true) {
         if (polys instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)polys;
            TransformedPoly$1 var8 = (TransformedPoly$1)var7.head();
            List rest = var7.next$access$1();
            if (var8 != null) {
               Polynomial p = var8.p();
               BigInt a = var8.a();
               BigInt b = var8.b();
               BigInt c = var8.c();
               BigInt d = var8.d();
               if (((BigInt)p.nth(0, package.bigInt$.MODULE$.BigIntAlgebra())).signum() == 0) {
                  Polynomial p0 = p.mapTerms((x0$1) -> {
                     if (x0$1 != null) {
                        BigInt coeff = (BigInt)x0$1.coeff();
                        int exp = x0$1.exp();
                        Term var1 = new Term(coeff, exp - 1);
                        return var1;
                     } else {
                        throw new MatchError(x0$1);
                     }
                  }, package.bigInt$.MODULE$.BigIntAlgebra(), (Eq)package.bigInt$.MODULE$.BigIntAlgebra(), scala.reflect.ClassTag..MODULE$.apply(BigInt.class));
                  TransformedPoly$1 var16 = this.TransformedPoly$3(TransformedPoly$module$1).apply(p0, a, b, c, d);
                  List var22 = rest.$colon$colon(var16);
                  acc = (Vector)acc.$colon$plus(Interval$.MODULE$.point(Rational$.MODULE$.apply(b, d), (Order)Rational$.MODULE$.RationalAlgebra()));
                  polys = var22;
                  continue;
               }

               int var17 = p.signVariations(package.bigInt$.MODULE$.BigIntAlgebra(), (Order)package.bigInt$.MODULE$.BigIntAlgebra(), (Signed)package.bigInt$.MODULE$.BigIntAlgebra());
               switch (var17) {
                  case 0:
                     acc = acc;
                     polys = rest;
                     continue;
                  case 1:
                     Rational i0 = BoxesRunTime.equalsNumObject(c, BoxesRunTime.boxToInteger(0)) ? ub$1(p, a, b, c, d) : Rational$.MODULE$.apply(a, c);
                     Rational i1 = BoxesRunTime.equalsNumObject(d, BoxesRunTime.boxToInteger(0)) ? ub$1(p, a, b, c, d) : Rational$.MODULE$.apply(b, d);
                     if (i0.$less(i1)) {
                        acc = (Vector)acc.$colon$plus(Interval$.MODULE$.open(i0, i1, (Order)Rational$.MODULE$.RationalAlgebra()));
                        polys = rest;
                        continue;
                     }

                     acc = (Vector)acc.$colon$plus(Interval$.MODULE$.open(i1, i0, (Order)Rational$.MODULE$.RationalAlgebra()));
                     polys = rest;
                     continue;
                  default:
                     List var21 = this.findFloor$1(p, scala.None..MODULE$, a, b, c, d, TransformedPoly$module$1).reverse_$colon$colon$colon(rest);
                     acc = acc;
                     polys = var21;
                     continue;
               }
            }
         }

         Nil var10000 = .MODULE$.Nil();
         if (var10000 == null) {
            if (polys == null) {
               return acc;
            }
         } else if (var10000.equals(polys)) {
            return acc;
         }

         throw new MatchError(polys);
      }
   }

   private static final Vector rec$default$2$1() {
      return .MODULE$.Vector().empty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$VAS$7(final Interval zeroInterval$1, final Interval x$3) {
      boolean var10000;
      label23: {
         if (x$3 == null) {
            if (zeroInterval$1 != null) {
               break label23;
            }
         } else if (!x$3.equals(zeroInterval$1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final int $anonfun$VAS$8(final PartialOrder partialOrder$1, final Interval x, final Interval y) {
      return BoxesRunTime.unboxToInt(partialOrder$1.tryCompare(x, y).getOrElse(() -> {
         throw new IllegalStateException("unexpected overlapping isolated roots");
      }));
   }

   private RootIsolator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
