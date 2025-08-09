package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

public final class MonadicJValue$ {
   public static final MonadicJValue$ MODULE$ = new MonadicJValue$();

   public JValue jvalueToMonadic(final JValue jv) {
      return jv;
   }

   public final JValue $bslash$extension(final JValue $this, final String nameToFind) {
      Object var3;
      if ($this instanceof JArray) {
         Object var5;
         label61: {
            label65: {
               JArray var7 = (JArray)$this;
               List xs = var7.arr();
               JArray var9 = new JArray(this.findDirectByName$extension($this, xs, nameToFind));
               if (var9 != null) {
                  List var10 = var9.arr();
                  Nil var10000 = .MODULE$.Nil();
                  if (var10000 == null) {
                     if (var10 == null) {
                        break label65;
                     }
                  } else if (var10000.equals(var10)) {
                     break label65;
                  }
               }

               if (var9 == null) {
                  throw new MatchError(var9);
               }

               List x = var9.arr();
               var5 = new JArray(x);
               break label61;
            }

            var5 = JNothing$.MODULE$;
         }

         var3 = var5;
      } else {
         Object var4;
         label52: {
            label66: {
               List var13 = this.findDirectByName$extension($this, .MODULE$.Nil().$colon$colon($this), nameToFind);
               Nil var20 = .MODULE$.Nil();
               if (var20 == null) {
                  if (var13 == null) {
                     break label66;
                  }
               } else if (var20.equals(var13)) {
                  break label66;
               }

               JValue x;
               label45: {
                  if (var13 instanceof scala.collection.immutable..colon.colon) {
                     scala.collection.immutable..colon.colon var16 = (scala.collection.immutable..colon.colon)var13;
                     x = (JValue)var16.head();
                     List var18 = var16.next$access$1();
                     var20 = .MODULE$.Nil();
                     if (var20 == null) {
                        if (var18 == null) {
                           break label45;
                        }
                     } else if (var20.equals(var18)) {
                        break label45;
                     }
                  }

                  var4 = new JArray(var13);
                  break label52;
               }

               var4 = x;
               break label52;
            }

            var4 = JNothing$.MODULE$;
         }

         var3 = var4;
      }

      return (JValue)var3;
   }

   public final List findDirectByName$extension(final JValue $this, final List xs, final String name) {
      return xs.flatMap((x0$1) -> {
         Object var3;
         if (x0$1 instanceof JObject) {
            JObject var5 = (JObject)x0$1;
            List l = var5.obj();
            var3 = l.collect(new Serializable(name) {
               private static final long serialVersionUID = 0L;
               private final String name$1;

               public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                  Object var3;
                  JValue v;
                  label27: {
                     if (x1 != null) {
                        String n = (String)x1._1();
                        v = (JValue)x1._2();
                        String var7 = this.name$1;
                        if (n == null) {
                           if (var7 == null) {
                              break label27;
                           }
                        } else if (n.equals(var7)) {
                           break label27;
                        }
                     }

                     var3 = default.apply(x1);
                     return var3;
                  }

                  var3 = v;
                  return var3;
               }

               public final boolean isDefinedAt(final Tuple2 x1) {
                  boolean var2;
                  label27: {
                     if (x1 != null) {
                        String n = (String)x1._1();
                        String var5 = this.name$1;
                        if (n == null) {
                           if (var5 == null) {
                              break label27;
                           }
                        } else if (n.equals(var5)) {
                           break label27;
                        }
                     }

                     var2 = false;
                     return var2;
                  }

                  var2 = true;
                  return var2;
               }

               public {
                  this.name$1 = name$1;
               }
            });
         } else if (x0$1 instanceof JArray) {
            JArray var7 = (JArray)x0$1;
            List l = var7.arr();
            var3 = MODULE$.findDirectByName$extension($this, l, name);
         } else {
            var3 = .MODULE$.Nil();
         }

         return (IterableOnce)var3;
      });
   }

   public final List findDirect$extension(final JValue $this, final List xs, final Function1 p) {
      return xs.flatMap((x0$1) -> {
         Object var3;
         if (x0$1 instanceof JObject) {
            JObject var5 = (JObject)x0$1;
            List l = var5.obj();
            var3 = l.collect(new Serializable(p) {
               private static final long serialVersionUID = 0L;
               private final Function1 p$1;

               public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                  Object var3;
                  if (x1 != null) {
                     JValue x = (JValue)x1._2();
                     if (BoxesRunTime.unboxToBoolean(this.p$1.apply(x))) {
                        var3 = x;
                        return var3;
                     }
                  }

                  var3 = default.apply(x1);
                  return var3;
               }

               public final boolean isDefinedAt(final Tuple2 x1) {
                  boolean var2;
                  if (x1 != null) {
                     JValue x = (JValue)x1._2();
                     if (BoxesRunTime.unboxToBoolean(this.p$1.apply(x))) {
                        var2 = true;
                        return var2;
                     }
                  }

                  var2 = false;
                  return var2;
               }

               public {
                  this.p$1 = p$1;
               }
            });
         } else if (x0$1 instanceof JArray) {
            JArray var7 = (JArray)x0$1;
            List l = var7.arr();
            var3 = MODULE$.findDirect$extension($this, l, p);
         } else if (BoxesRunTime.unboxToBoolean(p.apply(x0$1))) {
            var3 = .MODULE$.Nil().$colon$colon(x0$1);
         } else {
            var3 = .MODULE$.Nil();
         }

         return (IterableOnce)var3;
      });
   }

   public final JValue $bslash$bslash$extension(final JValue $this, final String nameToFind) {
      Object var3;
      JValue x;
      label30: {
         List var4 = find$1($this, nameToFind);
         if (var4 instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var5 = (scala.collection.immutable..colon.colon)var4;
            Tuple2 var6 = (Tuple2)var5.head();
            List var7 = var5.next$access$1();
            if (var6 != null) {
               x = (JValue)var6._2();
               Nil var10000 = .MODULE$.Nil();
               if (var10000 == null) {
                  if (var7 == null) {
                     break label30;
                  }
               } else if (var10000.equals(var7)) {
                  break label30;
               }
            }
         }

         var3 = new JObject(var4);
         return (JValue)var3;
      }

      var3 = x;
      return (JValue)var3;
   }

   public final List $bslash$extension(final JValue $this, final Class clazz) {
      return this.findDirect$extension($this, $this.children(), (json) -> BoxesRunTime.boxToBoolean($anonfun$$bslash$1($this, clazz, json))).map((x$1) -> x$1.values());
   }

   public final List $bslash$bslash$extension(final JValue $this, final Class clazz) {
      return this.filter$extension(this.jvalueToMonadic($this), (json) -> BoxesRunTime.boxToBoolean($anonfun$$bslash$bslash$3($this, clazz, json))).map((x$2) -> x$2.values());
   }

   public final boolean typePredicate$extension(final JValue $this, final Class clazz, final JValue json) {
      boolean var4;
      label24: {
         Class var10000 = json.getClass();
         if (var10000 == null) {
            if (clazz == null) {
               break label24;
            }
         } else if (var10000.equals(clazz)) {
            break label24;
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   public final Object fold$extension(final JValue $this, final Object z, final Function2 f) {
      return rec$1(z, $this, f);
   }

   public final Object foldField$extension(final JValue $this, final Object z, final Function2 f) {
      return rec$2(z, $this, f);
   }

   public final JValue map$extension(final JValue $this, final Function1 f) {
      return rec$3($this, f);
   }

   public final JValue mapField$extension(final JValue $this, final Function1 f) {
      return rec$4($this, f);
   }

   public final JValue transformField$extension(final JValue $this, final PartialFunction f) {
      return this.mapField$extension($this, (x) -> (Tuple2)f.applyOrElse(x, (x$3) -> x));
   }

   public final JValue transform$extension(final JValue $this, final PartialFunction f) {
      return this.map$extension($this, (x) -> (JValue)f.applyOrElse(x, (x$4) -> x));
   }

   public final JValue replace$extension(final JValue $this, final List l, final JValue replacement) {
      return rep$1(l, $this, replacement);
   }

   public final Option findField$extension(final JValue $this, final Function1 p) {
      return this.find$2($this, p);
   }

   public final Option find$extension(final JValue $this, final Function1 p) {
      return this.find$3($this, p);
   }

   public final List filterField$extension(final JValue $this, final Function1 p) {
      return ((List)this.foldField$extension($this, .MODULE$.List().apply(scala.collection.immutable.Nil..MODULE$), (acc, e) -> BoxesRunTime.unboxToBoolean(p.apply(e)) ? acc.$colon$colon(e) : acc)).reverse();
   }

   public final List filter$extension(final JValue $this, final Function1 p) {
      return ((List)this.fold$extension($this, .MODULE$.List().apply(scala.collection.immutable.Nil..MODULE$), (acc, e) -> BoxesRunTime.unboxToBoolean(p.apply(e)) ? acc.$colon$colon(e) : acc)).reverse();
   }

   public final MonadicJValue.JValueWithFilter withFilter$extension(final JValue $this, final Function1 p) {
      return new MonadicJValue.JValueWithFilter($this, p);
   }

   public final JValue removeField$extension(final JValue $this, final Function1 p) {
      return this.transform$extension(this.jvalueToMonadic($this), new Serializable(p) {
         private static final long serialVersionUID = 0L;
         private final Function1 p$6;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JObject) {
               JObject var5 = (JObject)x1;
               List l = var5.obj();
               var3 = new JObject(l.filterNot(this.p$6));
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JObject) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         public {
            this.p$6 = p$6;
         }
      });
   }

   public final JValue remove$extension(final JValue $this, final Function1 p) {
      return (JValue)(BoxesRunTime.unboxToBoolean(p.apply($this)) ? JNothing$.MODULE$ : this.transform$extension(this.jvalueToMonadic($this), new Serializable(p) {
         private static final long serialVersionUID = 0L;
         private final Function1 p$7;

         public final Object applyOrElse(final JValue x1, final Function1 default) {
            Object var3;
            if (x1 instanceof JObject) {
               JObject var5 = (JObject)x1;
               List l = var5.obj();
               var3 = new JObject(l.filterNot((f) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$1(this, f))));
            } else if (x1 instanceof JArray) {
               JArray var7 = (JArray)x1;
               List l = var7.arr();
               var3 = new JArray(l.filterNot(this.p$7));
            } else {
               var3 = default.apply(x1);
            }

            return var3;
         }

         public final boolean isDefinedAt(final JValue x1) {
            boolean var2;
            if (x1 instanceof JObject) {
               var2 = true;
            } else if (x1 instanceof JArray) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$1(final Object $this, final Tuple2 f) {
            return BoxesRunTime.unboxToBoolean($this.p$7.apply(f._2()));
         }

         public {
            this.p$7 = p$7;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      }));
   }

   public final String camelize$extension(final JValue $this, final String word) {
      String var10000;
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(word))) {
         String w = this.pascalize$extension($this, word);
         var10000 = (new StringBuilder(0)).append(w.substring(0, 1).toLowerCase(Locale.ENGLISH)).append(w.substring(1)).toString();
      } else {
         var10000 = word;
      }

      return var10000;
   }

   public final String pascalize$extension(final JValue $this, final String word) {
      List lst = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filterNot$extension(scala.Predef..MODULE$.refArrayOps((Object[])word.split("_")), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$pascalize$1(x$5)))).toList();
      String var4 = (String)lst.headOption().map((s) -> (new StringBuilder(0)).append(s.substring(0, 1).toUpperCase(Locale.ENGLISH)).append(s.substring(1)).toString()).get();
      return ((List)lst.tail()).map((s) -> (new StringBuilder(0)).append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).toString()).$colon$colon(var4).mkString("");
   }

   public final String underscoreCamelCasesOnly$extension(final JValue $this, final String word) {
      Regex firstPattern = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("([A-Z]+)([A-Z][a-z])"));
      Regex secondPattern = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("([a-z\\d])([A-Z])"));
      String replacementPattern = "$1_$2";
      return secondPattern.replaceAllIn(firstPattern.replaceAllIn(word, replacementPattern), replacementPattern).toLowerCase();
   }

   public final String underscore$extension(final JValue $this, final String word) {
      Regex spacesPattern = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("[-\\s]"));
      return spacesPattern.replaceAllIn(this.underscoreCamelCasesOnly$extension($this, word), "_");
   }

   public final JValue camelizeKeys$extension(final JValue $this) {
      return this.rewriteJsonAST$extension($this, (word) -> MODULE$.camelize$extension($this, word));
   }

   public final JValue pascalizeKeys$extension(final JValue $this) {
      return this.rewriteJsonAST$extension($this, (word) -> MODULE$.pascalize$extension($this, word));
   }

   public final JValue snakizeKeys$extension(final JValue $this) {
      return this.rewriteJsonAST$extension($this, (word) -> MODULE$.underscore$extension($this, word));
   }

   public final JValue underscoreCamelCaseKeysOnly$extension(final JValue $this) {
      return this.rewriteJsonAST$extension($this, (word) -> MODULE$.underscoreCamelCasesOnly$extension($this, word));
   }

   public final JValue underscoreKeys$extension(final JValue $this) {
      return this.snakizeKeys$extension($this);
   }

   public final JValue rewriteJsonAST$extension(final JValue $this, final Function1 keyCaseTransform) {
      return this.transformField$extension($this, new Serializable(keyCaseTransform) {
         private static final long serialVersionUID = 0L;
         private final Function1 keyCaseTransform$1;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            Object var3;
            if (x1 != null) {
               Tuple2 var5 = JsonAST$.MODULE$.JField().unapply(x1);
               if (!SomeValue$.MODULE$.isEmpty$extension(var5)) {
                  String nm = (String)var5._1();
                  JValue x = (JValue)var5._2();
                  if (!nm.startsWith("_")) {
                     var3 = JsonAST$.MODULE$.JField().apply((String)this.keyCaseTransform$1.apply(nm), x);
                     return var3;
                  }
               }
            }

            var3 = default.apply(x1);
            return var3;
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            boolean var2;
            if (x1 != null) {
               Tuple2 var4 = JsonAST$.MODULE$.JField().unapply(x1);
               if (!SomeValue$.MODULE$.isEmpty$extension(var4)) {
                  String nm = (String)var4._1();
                  if (!nm.startsWith("_")) {
                     var2 = true;
                     return var2;
                  }
               }
            }

            var2 = false;
            return var2;
         }

         public {
            this.keyCaseTransform$1 = keyCaseTransform$1;
         }
      });
   }

   public final JValue noNulls$extension(final JValue $this) {
      return this.remove$extension($this, (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$noNulls$1(x0$1)));
   }

   public final int hashCode$extension(final JValue $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final JValue $this, final Object x$1) {
      boolean var3;
      if (x$1 instanceof MonadicJValue) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var7;
      if (var3) {
         label32: {
            label31: {
               JValue var5 = x$1 == null ? null : ((MonadicJValue)x$1).org$json4s$MonadicJValue$$jv();
               if ($this == null) {
                  if (var5 == null) {
                     break label31;
                  }
               } else if ($this.equals(var5)) {
                  break label31;
               }

               var7 = false;
               break label32;
            }

            var7 = true;
         }

         if (var7) {
            var7 = true;
            return var7;
         }
      }

      var7 = false;
      return var7;
   }

   private static final List find$1(final JValue json, final String nameToFind$1) {
      Object var2;
      if (json instanceof JObject) {
         JObject var4 = (JObject)json;
         List l = var4.obj();
         var2 = (List)l.foldLeft(.MODULE$.List().apply(scala.collection.immutable.Nil..MODULE$), (x0$1, x1$1) -> {
            Tuple2 var4 = new Tuple2(x0$1, x1$1);
            if (var4 != null) {
               List a = (List)var4._1();
               Tuple2 var6 = (Tuple2)var4._2();
               if (var6 != null) {
                  List var10000;
                  label22: {
                     String name;
                     JValue value;
                     label21: {
                        name = (String)var6._1();
                        value = (JValue)var6._2();
                        if (name == null) {
                           if (nameToFind$1 == null) {
                              break label21;
                           }
                        } else if (name.equals(nameToFind$1)) {
                           break label21;
                        }

                        var10000 = find$1(value, nameToFind$1).$colon$colon$colon(a);
                        break label22;
                     }

                     List var11 = (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(name, value)})));
                     var10000 = find$1(value, nameToFind$1).$colon$colon$colon(var11).$colon$colon$colon(a);
                  }

                  List var3 = var10000;
                  return var3;
               }
            }

            throw new MatchError(var4);
         });
      } else if (json instanceof JArray) {
         JArray var6 = (JArray)json;
         List l = var6.arr();
         var2 = (List)l.foldLeft(.MODULE$.List().apply(scala.collection.immutable.Nil..MODULE$), (a, jsonx) -> find$1(jsonx, nameToFind$1).$colon$colon$colon(a));
      } else {
         var2 = .MODULE$.Nil();
      }

      return (List)var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$bslash$1(final JValue $this$3, final Class clazz$1, final JValue json) {
      return MODULE$.typePredicate$extension($this$3, clazz$1, json);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$bslash$bslash$3(final JValue $this$4, final Class clazz$2, final JValue json) {
      return MODULE$.typePredicate$extension($this$4, clazz$2, json);
   }

   private static final Object rec$1(final Object acc, final JValue v, final Function2 f$1) {
      Object newAcc = f$1.apply(acc, v);
      Object var3;
      if (v instanceof JObject) {
         JObject var6 = (JObject)v;
         List l = var6.obj();
         var3 = l.foldLeft(newAcc, (x0$1, x1$1) -> {
            Tuple2 var4 = new Tuple2(x0$1, x1$1);
            if (var4 != null) {
               Object a = var4._1();
               Tuple2 var6 = (Tuple2)var4._2();
               if (var6 != null) {
                  JValue value = (JValue)var6._2();
                  Object var3 = MODULE$.fold$extension(MODULE$.jvalueToMonadic(value), a, f$1);
                  return var3;
               }
            }

            throw new MatchError(var4);
         });
      } else if (v instanceof JArray) {
         JArray var8 = (JArray)v;
         List l = var8.arr();
         var3 = l.foldLeft(newAcc, (a, e) -> MODULE$.fold$extension(MODULE$.jvalueToMonadic(e), a, f$1));
      } else {
         var3 = newAcc;
      }

      return var3;
   }

   private static final Object rec$2(final Object acc, final JValue v, final Function2 f$2) {
      Object var3;
      if (v instanceof JObject) {
         JObject var5 = (JObject)v;
         List l = var5.obj();
         var3 = l.foldLeft(acc, (x0$1, x1$1) -> {
            Tuple2 var4 = new Tuple2(x0$1, x1$1);
            if (var4 != null) {
               Object a = var4._1();
               Tuple2 field = (Tuple2)var4._2();
               if (field != null) {
                  JValue value = (JValue)field._2();
                  Object var3 = MODULE$.foldField$extension(MODULE$.jvalueToMonadic(value), f$2.apply(a, field), f$2);
                  return var3;
               }
            }

            throw new MatchError(var4);
         });
      } else if (v instanceof JArray) {
         JArray var7 = (JArray)v;
         List l = var7.arr();
         var3 = l.foldLeft(acc, (a, e) -> MODULE$.foldField$extension(MODULE$.jvalueToMonadic(e), a, f$2));
      } else {
         var3 = acc;
      }

      return var3;
   }

   private static final JValue rec$3(final JValue v, final Function1 f$3) {
      JValue var2;
      if (v instanceof JObject) {
         JObject var4 = (JObject)v;
         List l = var4.obj();
         var2 = (JValue)f$3.apply(new JObject(l.map((x0$1) -> {
            if (x0$1 != null) {
               String n = (String)x0$1._1();
               JValue va = (JValue)x0$1._2();
               Tuple2 var2 = new Tuple2(n, rec$3(va, f$3));
               return var2;
            } else {
               throw new MatchError(x0$1);
            }
         })));
      } else if (v instanceof JArray) {
         JArray var6 = (JArray)v;
         List l = var6.arr();
         var2 = (JValue)f$3.apply(new JArray(l.map((vx) -> rec$3(vx, f$3))));
      } else {
         var2 = (JValue)f$3.apply(v);
      }

      return var2;
   }

   private static final JValue rec$4(final JValue v, final Function1 f$4) {
      Object var2;
      if (v instanceof JObject) {
         JObject var4 = (JObject)v;
         List l = var4.obj();
         var2 = new JObject(l.map((x0$1) -> {
            if (x0$1 != null) {
               String n = (String)x0$1._1();
               JValue va = (JValue)x0$1._2();
               Tuple2 var2 = (Tuple2)f$4.apply(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(n), rec$4(va, f$4)));
               return var2;
            } else {
               throw new MatchError(x0$1);
            }
         }));
      } else if (v instanceof JArray) {
         JArray var6 = (JArray)v;
         List l = var6.arr();
         var2 = new JArray(l.map((vx) -> rec$4(vx, f$4)));
      } else {
         var2 = v;
      }

      return (JValue)var2;
   }

   private static final JValue rep$1(final List l, final JValue in, final JValue replacement$1) {
      Tuple2 var4 = new Tuple2(l, in);
      Object var3;
      if (var4 != null) {
         List var5 = (List)var4._1();
         JValue var6 = (JValue)var4._2();
         if (var5 instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)var5;
            String var8 = (String)var7.head();
            List xs = var7.next$access$1();
            if (var8 != null) {
               Option var10 = MonadicJValue.ArrayIndex$.MODULE$.unapply(var8);
               if (!var10.isEmpty()) {
                  String name = (String)((Tuple2)var10.get())._1();
                  int index = ((Tuple2)var10.get())._2$mcI$sp();
                  if (var6 instanceof JObject) {
                     JObject var13 = (JObject)var6;
                     List fields = var13.obj();
                     var3 = new JObject(fields.map((x0$1) -> {
                        Tuple2 var5;
                        if (x0$1 != null) {
                           Tuple2 var7 = JsonAST$.MODULE$.JField().unapply(x0$1);
                           if (!SomeValue$.MODULE$.isEmpty$extension(var7)) {
                              label40: {
                                 String var8 = (String)var7._1();
                                 JValue var9 = (JValue)var7._2();
                                 if (name == null) {
                                    if (var8 != null) {
                                       break label40;
                                    }
                                 } else if (!name.equals(var8)) {
                                    break label40;
                                 }

                                 if (var9 instanceof JArray) {
                                    JArray var11 = (JArray)var9;
                                    List array = var11.arr();
                                    if (array.length() > index) {
                                       JField$ var10000;
                                       JArray var10002;
                                       JValue var10006;
                                       label31: {
                                          label30: {
                                             var10000 = JsonAST$.MODULE$.JField();
                                             var10002 = new JArray;
                                             Nil var13 = .MODULE$.Nil();
                                             if (xs == null) {
                                                if (var13 == null) {
                                                   break label30;
                                                }
                                             } else if (xs.equals(var13)) {
                                                break label30;
                                             }

                                             var10006 = rep$1(xs, (JValue)array.apply(index), replacement$1);
                                             break label31;
                                          }

                                          var10006 = replacement$1;
                                       }

                                       var10002.<init>(array.updated(index, var10006));
                                       var5 = var10000.apply(name, var10002);
                                       return var5;
                                    }
                                 }
                              }
                           }
                        }

                        var5 = x0$1;
                        return var5;
                     }));
                     return (JValue)var3;
                  }
               }
            }
         }
      }

      if (var4 != null) {
         List var15 = (List)var4._1();
         JValue var16 = (JValue)var4._2();
         if (var15 instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var17 = (scala.collection.immutable..colon.colon)var15;
            String var18 = (String)var17.head();
            List xs = var17.next$access$1();
            if (var18 != null) {
               Option var20 = MonadicJValue.ArrayEach$.MODULE$.unapply(var18);
               if (!var20.isEmpty()) {
                  String name = (String)var20.get();
                  if (var16 instanceof JObject) {
                     JObject var22 = (JObject)var16;
                     List fields = var22.obj();
                     var3 = new JObject(fields.map((x0$2) -> {
                        Tuple2 var4;
                        if (x0$2 != null) {
                           Tuple2 var6 = JsonAST$.MODULE$.JField().unapply(x0$2);
                           if (!SomeValue$.MODULE$.isEmpty$extension(var6)) {
                              label23: {
                                 String var7 = (String)var6._1();
                                 JValue var8 = (JValue)var6._2();
                                 if (name == null) {
                                    if (var7 != null) {
                                       break label23;
                                    }
                                 } else if (!name.equals(var7)) {
                                    break label23;
                                 }

                                 if (var8 instanceof JArray) {
                                    JArray var10 = (JArray)var8;
                                    List array = var10.arr();
                                    var4 = JsonAST$.MODULE$.JField().apply(name, new JArray(array.map((elem) -> {
                                       JValue var10000;
                                       label23: {
                                          Nil var3 = .MODULE$.Nil();
                                          if (xs == null) {
                                             if (var3 == null) {
                                                break label23;
                                             }
                                          } else if (xs.equals(var3)) {
                                             break label23;
                                          }

                                          var10000 = rep$1(xs, elem, replacement$1);
                                          return var10000;
                                       }

                                       var10000 = replacement$1;
                                       return var10000;
                                    })));
                                    return var4;
                                 }
                              }
                           }
                        }

                        var4 = x0$2;
                        return var4;
                     }));
                     return (JValue)var3;
                  }
               }
            }
         }
      }

      if (var4 != null) {
         List var24 = (List)var4._1();
         JValue var25 = (JValue)var4._2();
         if (var24 instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var26 = (scala.collection.immutable..colon.colon)var24;
            String x = (String)var26.head();
            List xs = var26.next$access$1();
            if (var25 instanceof JObject) {
               JObject var29 = (JObject)var25;
               List fields = var29.obj();
               var3 = new JObject(fields.map((x0$3) -> {
                  Tuple2 var4;
                  if (x0$3 != null) {
                     Tuple2 var6 = JsonAST$.MODULE$.JField().unapply(x0$3);
                     if (!SomeValue$.MODULE$.isEmpty$extension(var6)) {
                        label50: {
                           String var7 = (String)var6._1();
                           JValue value = (JValue)var6._2();
                           if (x == null) {
                              if (var7 != null) {
                                 break label50;
                              }
                           } else if (!x.equals(var7)) {
                              break label50;
                           }

                           JField$ var10000;
                           JValue var10002;
                           label28: {
                              label27: {
                                 var10000 = JsonAST$.MODULE$.JField();
                                 Nil var10 = .MODULE$.Nil();
                                 if (xs == null) {
                                    if (var10 == null) {
                                       break label27;
                                    }
                                 } else if (xs.equals(var10)) {
                                    break label27;
                                 }

                                 var10002 = rep$1(xs, value, replacement$1);
                                 break label28;
                              }

                              var10002 = replacement$1;
                           }

                           var4 = var10000.apply(x, var10002);
                           return var4;
                        }
                     }
                  }

                  var4 = x0$3;
                  return var4;
               }));
               return (JValue)var3;
            }
         }
      }

      var3 = in;
      return (JValue)var3;
   }

   private final Option loop$1(final List list, final Function1 p$2) {
      while(true) {
         Object var4;
         if (list instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)list;
            JValue x = (JValue)var7.head();
            List xs = var7.next$access$1();
            Option var10 = this.find$2(x, p$2);
            if (!(var10 instanceof Some)) {
               list = xs;
               continue;
            }

            Some var11 = (Some)var10;
            var4 = var11;
         } else {
            var4 = scala.None..MODULE$;
         }

         return (Option)var4;
      }
   }

   private final Option find$2(final JValue json, final Function1 p$2) {
      Object var3;
      if (json instanceof JObject) {
         JObject var5 = (JObject)json;
         List fs = var5.obj();
         var3 = fs.find(p$2).orElse(() -> fs.flatMap((x0$1) -> {
               if (x0$1 != null) {
                  JValue v = (JValue)x0$1._2();
                  Option var3 = this.find$2(v, p$2);
                  return var3;
               } else {
                  throw new MatchError(x0$1);
               }
            }).headOption());
      } else if (json instanceof JArray) {
         JArray var7 = (JArray)json;
         List l = var7.arr();
         var3 = this.loop$1(l, p$2);
      } else {
         var3 = scala.None..MODULE$;
      }

      return (Option)var3;
   }

   private final Option loop$2(final List list, final Function1 p$3) {
      while(true) {
         Object var4;
         if (list instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)list;
            JValue x = (JValue)var7.head();
            List xs = var7.next$access$1();
            Option var10 = this.find$3(x, p$3);
            if (!(var10 instanceof Some)) {
               list = xs;
               continue;
            }

            Some var11 = (Some)var10;
            var4 = var11;
         } else {
            var4 = scala.None..MODULE$;
         }

         return (Option)var4;
      }
   }

   private final Option find$3(final JValue json, final Function1 p$3) {
      if (BoxesRunTime.unboxToBoolean(p$3.apply(json))) {
         return new Some(json);
      } else {
         Object var3;
         if (json instanceof JObject) {
            JObject var5 = (JObject)json;
            List fs = var5.obj();
            var3 = fs.flatMap((x0$1) -> {
               if (x0$1 != null) {
                  JValue v = (JValue)x0$1._2();
                  Option var3 = this.find$3(v, p$3);
                  return var3;
               } else {
                  throw new MatchError(x0$1);
               }
            }).headOption();
         } else if (json instanceof JArray) {
            JArray var7 = (JArray)json;
            List l = var7.arr();
            var3 = this.loop$2(l, p$3);
         } else {
            var3 = scala.None..MODULE$;
         }

         return (Option)var3;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$pascalize$1(final String x$5) {
      return x$5.isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$noNulls$1(final JValue x0$1) {
      boolean var2;
      if (JNull$.MODULE$.equals(x0$1)) {
         var2 = true;
      } else if (JNothing$.MODULE$.equals(x0$1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      boolean var1;
      if (var2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private MonadicJValue$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
