package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Set;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Diff$ implements Serializable {
   public static final Diff$ MODULE$ = new Diff$();

   public Diff diff(final JValue val1, final JValue val2) {
      Diff var3;
      label243: {
         Tuple2 var4 = new Tuple2(val1, val2);
         if (var4 != null) {
            JValue x = (JValue)var4._1();
            JValue y = (JValue)var4._2();
            if (x == null) {
               if (y == null) {
                  break label243;
               }
            } else if (x.equals(y)) {
               break label243;
            }
         }

         if (var4 != null) {
            JValue var8 = (JValue)var4._1();
            JValue var9 = (JValue)var4._2();
            if (var8 instanceof JObject) {
               JObject var10 = (JObject)var8;
               List xs = var10.obj();
               if (var9 instanceof JObject) {
                  JObject var12 = (JObject)var9;
                  List ys = var12.obj();
                  var3 = this.diffFields(xs, ys);
                  return var3;
               }
            }
         }

         if (var4 != null) {
            JValue var14 = (JValue)var4._1();
            JValue var15 = (JValue)var4._2();
            if (var14 instanceof JArray) {
               JArray var16 = (JArray)var14;
               List xs = var16.arr();
               if (var15 instanceof JArray) {
                  JArray var18 = (JArray)var15;
                  List ys = var18.arr();
                  var3 = this.diffVals(xs, ys);
                  return var3;
               }
            }
         }

         JSet var22;
         JSet var24;
         label244: {
            if (var4 != null) {
               JValue xx = (JValue)var4._1();
               JValue yy = (JValue)var4._2();
               if (xx instanceof JSet) {
                  var22 = (JSet)xx;
                  Set x = var22.set();
                  if (yy instanceof JSet) {
                     var24 = (JSet)yy;
                     Set y = var24.set();
                     if (x == null) {
                        if (y != null) {
                           break label244;
                        }
                     } else if (!x.equals(y)) {
                        break label244;
                     }
                  }
               }
            }

            BigInt y;
            label245: {
               if (var4 != null) {
                  JValue var27 = (JValue)var4._1();
                  JValue var28 = (JValue)var4._2();
                  if (var27 instanceof JInt) {
                     JInt var29 = (JInt)var27;
                     BigInt x = var29.num();
                     if (var28 instanceof JInt) {
                        JInt var31 = (JInt)var28;
                        y = var31.num();
                        if (x == null) {
                           if (y != null) {
                              break label245;
                           }
                        } else if (!x.equals(y)) {
                           break label245;
                        }
                     }
                  }
               }

               if (var4 != null) {
                  JValue var34 = (JValue)var4._1();
                  JValue var35 = (JValue)var4._2();
                  if (var34 instanceof JDouble) {
                     JDouble var36 = (JDouble)var34;
                     double x = var36.num();
                     if (var35 instanceof JDouble) {
                        JDouble var39 = (JDouble)var35;
                        double y = var39.num();
                        if (x != y) {
                           var3 = new Diff(JsonAST$.MODULE$.JDouble().apply(y), JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing());
                           return var3;
                        }
                     }
                  }
               }

               BigDecimal y;
               label246: {
                  if (var4 != null) {
                     JValue var42 = (JValue)var4._1();
                     JValue var43 = (JValue)var4._2();
                     if (var42 instanceof JDecimal) {
                        JDecimal var44 = (JDecimal)var42;
                        BigDecimal x = var44.num();
                        if (var43 instanceof JDecimal) {
                           JDecimal var46 = (JDecimal)var43;
                           y = var46.num();
                           if (x == null) {
                              if (y != null) {
                                 break label246;
                              }
                           } else if (!x.equals(y)) {
                              break label246;
                           }
                        }
                     }
                  }

                  String y;
                  label247: {
                     if (var4 != null) {
                        JValue var49 = (JValue)var4._1();
                        JValue var50 = (JValue)var4._2();
                        if (var49 instanceof JString) {
                           JString var51 = (JString)var49;
                           String x = var51.s();
                           if (var50 instanceof JString) {
                              JString var53 = (JString)var50;
                              y = var53.s();
                              if (x == null) {
                                 if (y != null) {
                                    break label247;
                                 }
                              } else if (!x.equals(y)) {
                                 break label247;
                              }
                           }
                        }
                     }

                     if (var4 != null) {
                        JValue var56 = (JValue)var4._1();
                        JValue var57 = (JValue)var4._2();
                        if (var56 instanceof JBool) {
                           JBool var58 = (JBool)var56;
                           boolean x = var58.value();
                           if (var57 instanceof JBool) {
                              JBool var60 = (JBool)var57;
                              boolean y = var60.value();
                              if (x != y) {
                                 var3 = new Diff(JsonAST$.MODULE$.JBool().apply(y), JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing());
                                 return var3;
                              }
                           }
                        }
                     }

                     JValue x;
                     label248: {
                        if (var4 != null) {
                           JValue var62 = (JValue)var4._1();
                           x = (JValue)var4._2();
                           JNothing$ var10000 = JsonAST$.MODULE$.JNothing();
                           if (var10000 == null) {
                              if (var62 == null) {
                                 break label248;
                              }
                           } else if (var10000.equals(var62)) {
                              break label248;
                           }
                        }

                        JValue x;
                        label249: {
                           if (var4 != null) {
                              x = (JValue)var4._1();
                              JValue var66 = (JValue)var4._2();
                              JNothing$ var69 = JsonAST$.MODULE$.JNothing();
                              if (var69 == null) {
                                 if (var66 == null) {
                                    break label249;
                                 }
                              } else if (var69.equals(var66)) {
                                 break label249;
                              }
                           }

                           if (var4 == null) {
                              throw new MatchError(var4);
                           }

                           JValue y = (JValue)var4._2();
                           var3 = new Diff(y, JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing());
                           return var3;
                        }

                        var3 = new Diff(JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing(), x);
                        return var3;
                     }

                     var3 = new Diff(JsonAST$.MODULE$.JNothing(), x, JsonAST$.MODULE$.JNothing());
                     return var3;
                  }

                  var3 = new Diff(JsonAST$.MODULE$.JString().apply(y), JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing());
                  return var3;
               }

               var3 = new Diff(JsonAST$.MODULE$.JDecimal().apply(y), JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing());
               return var3;
            }

            var3 = new Diff(JsonAST$.MODULE$.JInt().apply(y), JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing());
            return var3;
         }

         var3 = new Diff(JsonAST$.MODULE$.JNothing(), var24.difference(var22), var22.difference(var24));
         return var3;
      }

      var3 = new Diff(JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing());
      return var3;
   }

   private Diff diffFields(final List vs1, final List vs2) {
      return this.diffRec$1(vs1, vs2);
   }

   private Diff diffVals(final List vs1, final List vs2) {
      return this.diffRec$2(vs1, vs2);
   }

   public Diff apply(final JValue changed, final JValue added, final JValue deleted) {
      return new Diff(changed, added, deleted);
   }

   public Option unapply(final Diff x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.changed(), x$0.added(), x$0.deleted())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Diff$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$diffFields$1(final Tuple2 x$8, final Tuple2 x$1) {
      return BoxesRunTime.equals(x$1._1(), x$8._1());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$diffFields$2(final Tuple2 y$1, final Tuple2 x$3) {
      boolean var10000;
      label23: {
         if (x$3 == null) {
            if (y$1 == null) {
               break label23;
            }
         } else if (x$3.equals(y$1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   private final Diff diffRec$1(final List xleft, final List yleft) {
      Diff var3;
      label69: {
         Nil var10000 = scala.package..MODULE$.Nil();
         if (var10000 == null) {
            if (xleft == null) {
               break label69;
            }
         } else if (var10000.equals(xleft)) {
            break label69;
         }

         if (!(xleft instanceof scala.collection.immutable..colon.colon)) {
            throw new MatchError(xleft);
         }

         scala.collection.immutable..colon.colon var10 = (scala.collection.immutable..colon.colon)xleft;
         Tuple2 x = (Tuple2)var10.head();
         List xs = var10.next$access$1();
         Option var13 = yleft.find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$diffFields$1(x, x$1)));
         Diff var4;
         if (var13 instanceof Some) {
            Some var14 = (Some)var13;
            Tuple2 y = (Tuple2)var14.value();
            Diff var17 = this.diff((JValue)x._2(), (JValue)y._2()).toField((String)y._1());
            if (var17 == null) {
               throw new MatchError(var17);
            }

            JValue c1 = var17.changed();
            JValue a1 = var17.added();
            JValue d1 = var17.deleted();
            Tuple3 var7 = new Tuple3(c1, a1, d1);
            JValue c1 = (JValue)var7._1();
            JValue a1 = (JValue)var7._2();
            JValue d1 = (JValue)var7._3();
            Diff var25 = this.diffRec$1(xs, yleft.filterNot((x$3) -> BoxesRunTime.boxToBoolean($anonfun$diffFields$2(y, x$3))));
            if (var25 == null) {
               throw new MatchError(var25);
            }

            JValue c2 = var25.changed();
            JValue a2 = var25.added();
            JValue d2 = var25.deleted();
            Tuple3 var6 = new Tuple3(c2, a2, d2);
            JValue c2 = (JValue)var6._1();
            JValue a2 = (JValue)var6._2();
            JValue d2 = (JValue)var6._3();
            var4 = new Diff(Merge.MergeSyntax$.MODULE$.merge$extension(JValue$.MODULE$.j2m(c1), c2, JValue$.MODULE$.jjj()), Merge.MergeSyntax$.MODULE$.merge$extension(JValue$.MODULE$.j2m(a1), a2, JValue$.MODULE$.jjj()), Merge.MergeSyntax$.MODULE$.merge$extension(JValue$.MODULE$.j2m(d1), d2, JValue$.MODULE$.jjj()));
         } else {
            if (!.MODULE$.equals(var13)) {
               throw new MatchError(var13);
            }

            Diff var33 = this.diffRec$1(xs, yleft);
            if (var33 == null) {
               throw new MatchError(var33);
            }

            JValue c = var33.changed();
            JValue a = var33.added();
            JValue d = var33.deleted();
            Tuple3 var5 = new Tuple3(c, a, d);
            JValue c = (JValue)var5._1();
            JValue a = (JValue)var5._2();
            JValue d = (JValue)var5._3();
            var4 = new Diff(c, a, Merge.MergeSyntax$.MODULE$.merge$extension(JValue$.MODULE$.j2m(JsonAST$.MODULE$.JObject().apply(scala.package..MODULE$.Nil().$colon$colon(x))), d, JValue$.MODULE$.jjj()));
         }

         var3 = var4;
         return var3;
      }

      var3 = new Diff(JsonAST$.MODULE$.JNothing(), (JValue)(yleft.isEmpty() ? JsonAST$.MODULE$.JNothing() : JsonAST$.MODULE$.JObject().apply(yleft)), JsonAST$.MODULE$.JNothing());
      return var3;
   }

   private final Diff diffRec$2(final List xleft, final List yleft) {
      Diff var3;
      List xs;
      label95: {
         Tuple2 var6 = new Tuple2(xleft, yleft);
         if (var6 != null) {
            xs = (List)var6._1();
            List var8 = (List)var6._2();
            Nil var10000 = scala.package..MODULE$.Nil();
            if (var10000 == null) {
               if (var8 == null) {
                  break label95;
               }
            } else if (var10000.equals(var8)) {
               break label95;
            }
         }

         List ys;
         label96: {
            if (var6 != null) {
               List var10 = (List)var6._1();
               ys = (List)var6._2();
               Nil var37 = scala.package..MODULE$.Nil();
               if (var37 == null) {
                  if (var10 == null) {
                     break label96;
                  }
               } else if (var37.equals(var10)) {
                  break label96;
               }
            }

            if (var6 == null) {
               throw new MatchError(var6);
            }

            List var13 = (List)var6._1();
            List var14 = (List)var6._2();
            if (!(var13 instanceof scala.collection.immutable..colon.colon)) {
               throw new MatchError(var6);
            }

            scala.collection.immutable..colon.colon var15 = (scala.collection.immutable..colon.colon)var13;
            JValue x = (JValue)var15.head();
            List xs = var15.next$access$1();
            if (!(var14 instanceof scala.collection.immutable..colon.colon)) {
               throw new MatchError(var6);
            }

            scala.collection.immutable..colon.colon var18 = (scala.collection.immutable..colon.colon)var14;
            JValue y = (JValue)var18.head();
            List ys = var18.next$access$1();
            Diff var22 = this.diff(x, y);
            if (var22 == null) {
               throw new MatchError(var22);
            }

            JValue c1 = var22.changed();
            JValue a1 = var22.added();
            JValue d1 = var22.deleted();
            Tuple3 var5 = new Tuple3(c1, a1, d1);
            JValue c1 = (JValue)var5._1();
            JValue a1 = (JValue)var5._2();
            JValue d1 = (JValue)var5._3();
            Diff var30 = this.diffRec$2(xs, ys);
            if (var30 == null) {
               throw new MatchError(var30);
            }

            JValue c2 = var30.changed();
            JValue a2 = var30.added();
            JValue d2 = var30.deleted();
            Tuple3 var4 = new Tuple3(c2, a2, d2);
            JValue c2 = (JValue)var4._1();
            JValue a2 = (JValue)var4._2();
            JValue d2 = (JValue)var4._3();
            var3 = new Diff(c1.$plus$plus(c2), a1.$plus$plus(a2), d1.$plus$plus(d2));
            return var3;
         }

         var3 = new Diff(JsonAST$.MODULE$.JNothing(), (JValue)(ys.isEmpty() ? JsonAST$.MODULE$.JNothing() : JsonAST$.MODULE$.JArray().apply(ys)), JsonAST$.MODULE$.JNothing());
         return var3;
      }

      var3 = new Diff(JsonAST$.MODULE$.JNothing(), JsonAST$.MODULE$.JNothing(), (JValue)(xs.isEmpty() ? JsonAST$.MODULE$.JNothing() : JsonAST$.MODULE$.JArray().apply(xs)));
      return var3;
   }

   private Diff$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
