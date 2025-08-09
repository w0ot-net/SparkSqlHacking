package org.json4s;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import org.json4s.prefs.ExtractionNullStrategy;
import org.json4s.reflect.ClassDescriptor;
import org.json4s.reflect.ObjectDescriptor;
import org.json4s.reflect.PrimitiveDescriptor;
import org.json4s.reflect.PropertyDescriptor;
import org.json4s.reflect.Reflector$;
import org.json4s.reflect.ScalaType;
import org.json4s.reflect.ScalaType$;
import org.json4s.reflect.TypeInfo;
import scala.Function1;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Symbol;
import scala.Tuple2;
import scala.Option.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.LinearSeqOps;
import scala.collection.Map;
import scala.collection.MapOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.Manifest;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.util.Either;
import scala.util.matching.Regex;

public final class Extraction$ {
   public static final Extraction$ MODULE$ = new Extraction$();
   private static Set typesHaveNaN;
   private static volatile boolean bitmap$0;

   public Object extract(final JValue json, final Formats formats, final Manifest mf) {
      try {
         return this.extract(json, Reflector$.MODULE$.scalaTypeOf(mf), formats);
      } catch (MappingException var6) {
         throw var6;
      } catch (Exception var7) {
         throw new MappingException("unknown error", var7);
      }
   }

   public Option extractOpt(final JValue json, final Formats formats, final Manifest mf) {
      Object var10000;
      try {
         var10000 = .MODULE$.apply(this.extract(json, formats, mf));
      } catch (Throwable var7) {
         if (!(var7 instanceof MappingException) || formats.strictOptionParsing()) {
            throw var7;
         }

         None var4 = scala.None..MODULE$;
         var10000 = var4;
      }

      return (Option)var10000;
   }

   public Object extract(final JValue json, final TypeInfo target, final Formats formats) {
      return this.extract(json, ScalaType$.MODULE$.apply(target), formats);
   }

   public Object decomposeWithBuilder(final Object a, final JsonWriter builder, final Formats formats) {
      this.internalDecomposeWithBuilder(a, builder, formats);
      return builder.result();
   }

   public Object loadLazyValValue(final Object a, final String name, final Object defaultValue) {
      Object var10000;
      try {
         Method method = a.getClass().getDeclaredMethod((new StringBuilder(11)).append(name).append("$lzycompute").toString());
         method.setAccessible(true);
         var10000 = method.invoke(a);
      } catch (Exception var5) {
         var10000 = defaultValue;
      }

      return var10000;
   }

   private Set typesHaveNaN$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            typesHaveNaN = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Double.TYPE, Float.TYPE, Double.class, Float.class})));
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return typesHaveNaN;
   }

   private Set typesHaveNaN() {
      return !bitmap$0 ? this.typesHaveNaN$lzycompute() : typesHaveNaN;
   }

   public void internalDecomposeWithBuilder(final Object a, final JsonWriter builder, final Formats formats) {
      while(true) {
         LazyRef richCustom$lzy = new LazyRef();
         PartialFunction serializer = formats.typeHints().serialize();
         PartialFunction custom = Formats$.MODULE$.customSerializer(a, formats);
         if (custom.isDefinedAt(a)) {
            builder.addJValue((JValue)custom.apply(a));
            BoxedUnit var95 = BoxedUnit.UNIT;
            break;
         }

         if (richCustom$1(richCustom$lzy, a, formats).isDefinedAt(a)) {
            builder.addJValue((JValue)richCustom$1(richCustom$lzy, a, formats).apply(a));
            BoxedUnit var94 = BoxedUnit.UNIT;
            break;
         }

         if (!serializer.isDefinedAt(a)) {
            Class k = a != null ? a.getClass() : null;
            if (a == null) {
               builder.addJValue(org.json4s.JNull..MODULE$);
               BoxedUnit var93 = BoxedUnit.UNIT;
               break;
            }

            if (JValue.class.isAssignableFrom(k)) {
               builder.addJValue((JValue)a);
               BoxedUnit var92 = BoxedUnit.UNIT;
               break;
            }

            label247: {
               if (this.typesHaveNaN().contains(a.getClass())) {
                  String var80 = a.toString();
                  String var13 = "NaN";
                  if (var80 == null) {
                     if (var13 == null) {
                        break label247;
                     }
                  } else if (var80.equals(var13)) {
                     break label247;
                  }
               }

               if (Reflector$.MODULE$.isPrimitive(a.getClass(), Reflector$.MODULE$.isPrimitive$default$2())) {
                  this.writePrimitive(a, builder, formats);
                  BoxedUnit var90 = BoxedUnit.UNIT;
                  break;
               }

               if (!Map.class.isAssignableFrom(k)) {
                  if (Iterable.class.isAssignableFrom(k)) {
                     JsonWriter arr = builder.startArray();
                     Iterator iter = ((Iterable)a).iterator();

                     while(iter.hasNext()) {
                        this.internalDecomposeWithBuilder(iter.next(), arr, formats);
                     }

                     arr.endArray();
                     BoxedUnit var82 = BoxedUnit.UNIT;
                  } else if (Collection.class.isAssignableFrom(k)) {
                     JsonWriter arr = builder.startArray();
                     java.util.Iterator iter = ((Collection)a).iterator();

                     while(iter.hasNext()) {
                        this.internalDecomposeWithBuilder(iter.next(), arr, formats);
                     }

                     arr.endArray();
                     BoxedUnit var83 = BoxedUnit.UNIT;
                  } else if (k.isArray()) {
                     JsonWriter arr = builder.startArray();
                     Iterator iter = scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(a));

                     while(iter.hasNext()) {
                        this.internalDecomposeWithBuilder(iter.next(), arr, formats);
                     }

                     arr.endArray();
                     BoxedUnit var84 = BoxedUnit.UNIT;
                  } else if (Option.class.isAssignableFrom(k)) {
                     ((Option)a).foreach((x$4) -> {
                        $anonfun$internalDecomposeWithBuilder$9(builder, formats, x$4);
                        return BoxedUnit.UNIT;
                     });
                     BoxedUnit var85 = BoxedUnit.UNIT;
                  } else {
                     if (Either.class.isAssignableFrom(k)) {
                        Either v = (Either)a;
                        if (v.isLeft()) {
                           Object var89 = v.left().get();
                           formats = formats;
                           builder = builder;
                           a = var89;
                           continue;
                        }

                        Object var88 = v.right().get();
                        formats = formats;
                        builder = builder;
                        a = var88;
                        continue;
                     }

                     if (Tuple2.class.isAssignableFrom(k)) {
                        label242: {
                           Tuple2 var59 = (Tuple2)a;
                           if (var59 != null) {
                              Object k = var59._1();
                              Object v = var59._2();
                              if (k instanceof String) {
                                 String var62 = (String)k;
                                 JsonWriter obj = builder.startObject();
                                 this.addField$1(var62, v, obj, formats);
                                 obj.endObject();
                                 BoxedUnit var69 = BoxedUnit.UNIT;
                                 break label242;
                              }
                           }

                           if (var59 != null) {
                              Object k = var59._1();
                              Object v = var59._2();
                              if (k instanceof Symbol) {
                                 Symbol var66 = (Symbol)k;
                                 JsonWriter obj = builder.startObject();
                                 this.addField$1(var66.name(), v, obj, formats);
                                 obj.endObject();
                                 BoxedUnit var68 = BoxedUnit.UNIT;
                                 break label242;
                              }
                           }

                           if (!(var59 instanceof Tuple2)) {
                              throw new MatchError(var59);
                           }

                           this.decomposeObject$1(k, formats, builder, a, a);
                           BoxedUnit var5 = BoxedUnit.UNIT;
                        }

                        BoxedUnit var86 = BoxedUnit.UNIT;
                     } else {
                        this.decomposeObject$1(k, formats, builder, a, a);
                        BoxedUnit var87 = BoxedUnit.UNIT;
                     }
                  }
                  break;
               }

               JsonWriter obj = builder.startObject();
               Iterator iter = ((Map)a).iterator();

               while(iter.hasNext()) {
                  Tuple2 var16 = (Tuple2)iter.next();
                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof String) {
                        String var19 = (String)k;
                        this.addField$1(var19, v, obj, formats);
                        BoxedUnit var79 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof Symbol) {
                        Symbol var22 = (Symbol)k;
                        this.addField$1(var22.name(), v, obj, formats);
                        BoxedUnit var78 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof Integer) {
                        int var25 = BoxesRunTime.unboxToInt(k);
                        this.addField$1(Integer.toString(var25), v, obj, formats);
                        BoxedUnit var77 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof Long) {
                        long var28 = BoxesRunTime.unboxToLong(k);
                        this.addField$1(Long.toString(var28), v, obj, formats);
                        BoxedUnit var76 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof Date) {
                        Date var32 = (Date)k;
                        this.addField$1(formats.dateFormat().format(var32), v, obj, formats);
                        BoxedUnit var75 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof Integer) {
                        Integer var35 = (Integer)k;
                        this.addField$1(var35.toString(), v, obj, formats);
                        BoxedUnit var74 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof BigInt) {
                        BigInt var38 = (BigInt)k;
                        this.addField$1(var38.toString(), v, obj, formats);
                        BoxedUnit var73 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof Long) {
                        Long var41 = (Long)k;
                        this.addField$1(var41.toString(), v, obj, formats);
                        BoxedUnit var72 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof Short) {
                        short var44 = BoxesRunTime.unboxToShort(k);
                        this.addField$1(Short.toString(var44), v, obj, formats);
                        BoxedUnit var71 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 != null) {
                     Object k = var16._1();
                     Object v = var16._2();
                     if (k instanceof Short) {
                        Short var47 = (Short)k;
                        this.addField$1(var47.toString(), v, obj, formats);
                        BoxedUnit var70 = BoxedUnit.UNIT;
                        continue;
                     }
                  }

                  if (var16 == null) {
                     throw new MatchError(var16);
                  }

                  Object k = var16._1();
                  Object v = var16._2();
                  if (!(k instanceof Object)) {
                     throw new MatchError(var16);
                  }

                  PartialFunction customKeySerializer = Formats$.MODULE$.customKeySerializer(k, formats);
                  if (!customKeySerializer.isDefinedAt(k)) {
                     throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(87)).append("Do not know how to serialize key of type ").append(k.getClass()).append(". ").append("Consider implementing a CustomKeySerializer.").toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
                  }

                  this.addField$1((String)customKeySerializer.apply(k), v, obj, formats);
                  BoxedUnit var6 = BoxedUnit.UNIT;
               }

               obj.endObject();
               BoxedUnit var81 = BoxedUnit.UNIT;
               break;
            }

            builder.addJValue(org.json4s.JNull..MODULE$);
            BoxedUnit var91 = BoxedUnit.UNIT;
            break;
         }

         prependTypeHint$1(a.getClass(), (JObject)serializer.apply(a), formats).foreach((jv) -> builder.addJValue(jv));
         BoxedUnit var10000 = BoxedUnit.UNIT;
         break;
      }

   }

   public JValue decompose(final Object a, final Formats formats) {
      return (JValue)this.decomposeWithBuilder(a, formats.wantsBigDecimal() ? org.json4s.JsonWriter..MODULE$.bigDecimalAst() : org.json4s.JsonWriter..MODULE$.ast(), formats);
   }

   private JsonWriter writePrimitive(final Object a, final JsonWriter builder, final Formats formats) {
      JsonWriter var4;
      if (a instanceof String) {
         String var6 = (String)a;
         var4 = builder.string(var6);
      } else if (a instanceof Integer) {
         int var7 = BoxesRunTime.unboxToInt(a);
         var4 = builder.int(var7);
      } else if (a instanceof Long) {
         long var8 = BoxesRunTime.unboxToLong(a);
         var4 = builder.long(var8);
      } else if (a instanceof Double) {
         double var10 = BoxesRunTime.unboxToDouble(a);
         var4 = builder.double(var10);
      } else if (a instanceof Float) {
         float var12 = BoxesRunTime.unboxToFloat(a);
         var4 = builder.float(var12);
      } else if (a instanceof Byte) {
         byte var13 = BoxesRunTime.unboxToByte(a);
         var4 = builder.byte(var13);
      } else if (a instanceof BigInt) {
         BigInt var14 = (BigInt)a;
         var4 = builder.bigInt(var14);
      } else if (a instanceof BigDecimal) {
         BigDecimal var15 = (BigDecimal)a;
         var4 = builder.bigDecimal(var15);
      } else if (a instanceof Boolean) {
         boolean var16 = BoxesRunTime.unboxToBoolean(a);
         var4 = builder.boolean(var16);
      } else if (a instanceof Short) {
         short var17 = BoxesRunTime.unboxToShort(a);
         var4 = builder.short(var17);
      } else if (a instanceof Integer) {
         Integer var18 = (Integer)a;
         var4 = builder.int(var18);
      } else if (a instanceof Long) {
         Long var19 = (Long)a;
         var4 = builder.long(var19);
      } else if (a instanceof Double) {
         Double var20 = (Double)a;
         var4 = builder.double(var20);
      } else if (a instanceof Float) {
         Float var21 = (Float)a;
         var4 = builder.float(var21);
      } else if (a instanceof Byte) {
         Byte var22 = (Byte)a;
         var4 = builder.byte(var22);
      } else if (a instanceof Boolean) {
         Boolean var23 = (Boolean)a;
         var4 = builder.boolean(var23);
      } else if (a instanceof Short) {
         Short var24 = (Short)a;
         var4 = builder.short(var24);
      } else if (a instanceof java.math.BigDecimal) {
         java.math.BigDecimal var25 = (java.math.BigDecimal)a;
         var4 = builder.bigDecimal(scala.math.BigDecimal..MODULE$.javaBigDecimal2bigDecimal(var25));
      } else if (a instanceof Date) {
         Date var26 = (Date)a;
         var4 = builder.string(formats.dateFormat().format(var26));
      } else {
         if (!(a instanceof Symbol)) {
            throw scala.sys.package..MODULE$.error((new StringBuilder(16)).append("not a primitive ").append(a.getClass()).toString());
         }

         Symbol var27 = (Symbol)a;
         var4 = builder.string(var27.name());
      }

      return var4;
   }

   public scala.collection.immutable.Map flatten(final JValue json, final Formats formats) {
      return flatten0$1("", json, formats);
   }

   public Formats flatten$default$2(final JValue json) {
      return DefaultFormats$.MODULE$;
   }

   public JValue unflatten(final scala.collection.immutable.Map map, final boolean useBigDecimalForDouble, final boolean useBigIntForLong) {
      Regex ArrayProp = new Regex("^(\\.([^\\.\\[]+))\\[(\\d+)\\].*$", scala.collection.immutable.Nil..MODULE$);
      Regex ArrayElem = new Regex("^(\\[(\\d+)\\]).*$", scala.collection.immutable.Nil..MODULE$);
      Regex OtherProp = new Regex("^(\\.([^\\.\\[]+)).*$", scala.collection.immutable.Nil..MODULE$);
      List uniquePaths = (List)((IterableOnceOps)map.keys().foldLeft(scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$), (set, key) -> {
         Set var5;
         if (key != null) {
            Option var7 = ArrayProp.unapplySeq(key);
            if (!var7.isEmpty() && var7.get() != null && ((List)var7.get()).lengthCompare(3) == 0) {
               String p = (String)((LinearSeqOps)var7.get()).apply(0);
               var5 = (Set)set.$plus(p);
               return var5;
            }
         }

         if (key != null) {
            Option var9 = OtherProp.unapplySeq(key);
            if (!var9.isEmpty() && var9.get() != null && ((List)var9.get()).lengthCompare(2) == 0) {
               String p = (String)((LinearSeqOps)var9.get()).apply(0);
               var5 = (Set)set.$plus(p);
               return var5;
            }
         }

         if (key != null) {
            Option var11 = ArrayElem.unapplySeq(key);
            if (!var11.isEmpty() && var11.get() != null && ((List)var11.get()).lengthCompare(2) == 0) {
               String p = (String)((LinearSeqOps)var11.get()).apply(0);
               var5 = (Set)set.$plus(p);
               return var5;
            }
         }

         var5 = (Set)set.$plus(key);
         return var5;
      })).toList().sortWith((x$5, x$6) -> BoxesRunTime.boxToBoolean($anonfun$unflatten$4(x$5, x$6)));
      return (JValue)uniquePaths.foldLeft(org.json4s.JNothing..MODULE$, (jvalue, key) -> {
         Merge.MergeSyntax var10000 = org.json4s.Merge.MergeSyntax..MODULE$;
         JValue var10001 = org.json4s.JValue..MODULE$.j2m(jvalue);
         Object var8;
         if (key != null) {
            Option var10 = ArrayProp.unapplySeq(key);
            if (!var10.isEmpty() && var10.get() != null && ((List)var10.get()).lengthCompare(3) == 0) {
               String f = (String)((LinearSeqOps)var10.get()).apply(1);
               var8 = new JObject((List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{org.json4s.JField..MODULE$.apply(f, MODULE$.unflatten(submap$1(key, map, ArrayProp, ArrayElem, OtherProp), MODULE$.unflatten$default$2(), MODULE$.unflatten$default$3()))}))));
               return var10000.merge$extension(var10001, (JValue)var8, org.json4s.JValue..MODULE$.jjj());
            }
         }

         if (key != null) {
            Option var12 = ArrayElem.unapplySeq(key);
            if (!var12.isEmpty() && var12.get() != null && ((List)var12.get()).lengthCompare(2) == 0) {
               var8 = new JArray((List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new JValue[]{MODULE$.unflatten(submap$1(key, map, ArrayProp, ArrayElem, OtherProp), MODULE$.unflatten$default$2(), MODULE$.unflatten$default$3())}))));
               return var10000.merge$extension(var10001, (JValue)var8, org.json4s.JValue..MODULE$.jjj());
            }
         }

         if (key != null) {
            Option var13 = OtherProp.unapplySeq(key);
            if (!var13.isEmpty() && var13.get() != null && ((List)var13.get()).lengthCompare(2) == 0) {
               String f = (String)((LinearSeqOps)var13.get()).apply(1);
               var8 = new JObject((List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{org.json4s.JField..MODULE$.apply(f, MODULE$.unflatten(submap$1(key, map, ArrayProp, ArrayElem, OtherProp), MODULE$.unflatten$default$2(), MODULE$.unflatten$default$3()))}))));
               return var10000.merge$extension(var10001, (JValue)var8, org.json4s.JValue..MODULE$.jjj());
            }
         }

         if (!"".equals(key)) {
            throw new MatchError(key);
         } else {
            var8 = extractValue$1((String)map.apply(key), useBigIntForLong, useBigDecimalForDouble);
            return var10000.merge$extension(var10001, (JValue)var8, org.json4s.JValue..MODULE$.jjj());
         }
      });
   }

   public boolean unflatten$default$2() {
      return false;
   }

   public boolean unflatten$default$3() {
      return true;
   }

   public Object extract(final JValue json, final ScalaType scalaType, final Formats formats) {
      Object var10000;
      if (scalaType.isEither()) {
         var10000 = scala.util.control.Exception..MODULE$.allCatch().opt(() -> scala.package..MODULE$.Left().apply(MODULE$.extract(json, (ScalaType)scalaType.typeArgs().apply(0), formats))).orElse(() -> scala.util.control.Exception..MODULE$.allCatch().opt(() -> scala.package..MODULE$.Right().apply(MODULE$.extract(json, (ScalaType)scalaType.typeArgs().apply(1), formats)))).getOrElse(() -> org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(23)).append("Expected value but got ").append(json).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2()));
      } else if (scalaType.isOption()) {
         var10000 = this.customOrElse(scalaType, json, (v) -> (formats.strictOptionParsing() ? v.toSome() : v.toOption()).flatMap((j) -> .MODULE$.apply(MODULE$.extract(j, (ScalaType)scalaType.typeArgs().head(), formats))), formats);
      } else if (scalaType.isMap()) {
         var10000 = this.customOrElse(scalaType, json, (x0$1) -> {
            Object var3;
            if (x0$1 instanceof JObject) {
               JObject var6 = (JObject)x0$1;
               List xs = var6.obj();
               ScalaType kta = (ScalaType)scalaType.typeArgs().apply(0);
               ScalaType ta = (ScalaType)scalaType.typeArgs().apply(1);
               List values = xs.map((x0$2) -> {
                  if (x0$2 != null) {
                     String key = (String)x0$2._1();
                     JValue value = (JValue)x0$2._2();
                     Object convertedKey = MODULE$.convert(key, kta, formats);
                     Object extractedValue = MODULE$.org$json4s$Extraction$$extractDetectingNonTerminal(value, ta, formats);
                     Tuple2 var4 = scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(convertedKey), extractedValue);
                     return var4;
                  } else {
                     throw new MatchError(x0$2);
                  }
               });
               var3 = scalaType.isMutableMap() ? scala.collection.mutable.Map..MODULE$.apply(values) : values.toMap(scala..less.colon.less..MODULE$.refl());
            } else {
               boolean var4;
               if (org.json4s.JNothing..MODULE$.equals(x0$1)) {
                  var4 = true;
               } else if (org.json4s.JNull..MODULE$.equals(x0$1)) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               if (!var4 || formats.strictMapExtraction()) {
                  throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(24)).append("Expected object but got ").append(x0$1).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
               }

               var3 = scalaType.isMutableMap() ? scala.collection.mutable.Map..MODULE$.empty() : scala.Predef..MODULE$.Map().empty();
            }

            return var3;
         }, formats);
      } else if (scalaType.isCollection()) {
         var10000 = this.customOrElse(scalaType, json, (x$7) -> (new Extraction.CollectionBuilder(x$7, scalaType, formats)).result(), formats);
      } else if (Tuple2.class.isAssignableFrom(scalaType.erasure()) && (String.class.isAssignableFrom(((ScalaType)scalaType.typeArgs().head()).erasure()) || Symbol.class.isAssignableFrom(((ScalaType)scalaType.typeArgs().head()).erasure()))) {
         ScalaType ta = (ScalaType)scalaType.typeArgs().apply(1);
         if (!(json instanceof JObject)) {
            throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(39)).append("Expected object with 1 element but got ").append(json).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
         }

         JObject var7 = (JObject)json;
         List var8 = var7.obj();
         if (!(var8 instanceof scala.collection.immutable..colon.colon)) {
            throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(39)).append("Expected object with 1 element but got ").append(json).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
         }

         scala.collection.immutable..colon.colon var9 = (scala.collection.immutable..colon.colon)var8;
         Tuple2 xs = (Tuple2)var9.head();
         List var11 = var9.next$access$1();
         Nil var13 = scala.package..MODULE$.Nil();
         if (var13 == null) {
            if (var11 != null) {
               throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(39)).append("Expected object with 1 element but got ").append(json).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
            }
         } else if (!var13.equals(var11)) {
            throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(39)).append("Expected object with 1 element but got ").append(json).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
         }

         Tuple2 var4 = Symbol.class.isAssignableFrom(((ScalaType)scalaType.typeArgs().head()).erasure()) ? new Tuple2(scala.Symbol..MODULE$.apply((String)xs._1()), this.extract((JValue)xs._2(), ta, formats)) : new Tuple2(xs._1(), this.extract((JValue)xs._2(), ta, formats));
         var10000 = var4;
      } else {
         var10000 = this.customOrElse(scalaType, json, (x$8) -> {
            boolean var5 = false;
            ClassDescriptor var6 = null;
            ObjectDescriptor var7 = Reflector$.MODULE$.describeWithFormats(org.json4s.reflect.package$.MODULE$.scalaTypeDescribable(scalaType, formats), formats);
            Object var4;
            if (var7 instanceof PrimitiveDescriptor) {
               PrimitiveDescriptor var8 = (PrimitiveDescriptor)var7;
               ScalaType tpe = var8.erasure();
               Option var10 = var8.default();
               var4 = MODULE$.convert(json, tpe, formats, var10);
            } else {
               if (var7 instanceof ClassDescriptor) {
                  var5 = true;
                  var6 = (ClassDescriptor)var7;
                  if (var6.erasure().isSingleton()) {
                     JObject var11 = new JObject(scala.package..MODULE$.List().empty());
                     if (json == null) {
                        if (var11 != null) {
                           throw scala.sys.package..MODULE$.error((new StringBuilder(66)).append("Expected empty parameter list for singleton instance, got ").append(json).append(" instead").toString());
                        }
                     } else if (!json.equals(var11)) {
                        throw scala.sys.package..MODULE$.error((new StringBuilder(66)).append("Expected empty parameter list for singleton instance, got ").append(json).append(" instead").toString());
                     }

                     var4 = var6.erasure().singletonInstance().getOrElse(() -> scala.sys.package..MODULE$.error((new StringBuilder(19)).append("Not a case object: ").append(var6.erasure()).toString()));
                     return var4;
                  }
               }

               if (!var5) {
                  throw new MatchError(var7);
               }

               var4 = (new Extraction.ClassInstanceBuilder(json, var6, formats)).result();
            }

            return var4;
         }, formats);
      }

      return var10000;
   }

   public Object org$json4s$Extraction$$extractDetectingNonTerminal(final JValue jvalue, final ScalaType typeArg, final Formats formats) {
      Object var4;
      JArray var8;
      label67: {
         boolean var5 = false;
         JObject var6 = null;
         if (jvalue instanceof JArray) {
            var8 = (JArray)jvalue;
            Class var10000 = typeArg.erasure();
            Class var9 = scala.reflect.Manifest..MODULE$.Object().runtimeClass();
            if (var10000 == null) {
               if (var9 == null) {
                  break label67;
               }
            } else if (var10000.equals(var9)) {
               break label67;
            }
         }

         if (jvalue instanceof JObject) {
            label44: {
               var5 = true;
               var6 = (JObject)jvalue;
               Class var12 = typeArg.erasure();
               Class var10 = scala.reflect.Manifest..MODULE$.Object().runtimeClass();
               if (var12 == null) {
                  if (var10 != null) {
                     break label44;
                  }
               } else if (!var12.equals(var10)) {
                  break label44;
               }

               if (var6.obj().exists((x$9) -> BoxesRunTime.boxToBoolean($anonfun$extractDetectingNonTerminal$1(formats, x$9)))) {
                  var4 = this.extract(var6, (ScalaType)ScalaType$.MODULE$.Object(), (Formats)formats);
                  return var4;
               }
            }
         }

         label68: {
            if (var5) {
               Class var13 = typeArg.erasure();
               Class var11 = scala.reflect.Manifest..MODULE$.Object().runtimeClass();
               if (var13 == null) {
                  if (var11 == null) {
                     break label68;
                  }
               } else if (var13.equals(var11)) {
                  break label68;
               }
            }

            var4 = this.extract(jvalue, typeArg, formats);
            return var4;
         }

         var4 = this.extract(var6, (ScalaType)ScalaType$.MODULE$.MapStringObject(), (Formats)formats);
         return var4;
      }

      var4 = this.extract(var8, (ScalaType)ScalaType$.MODULE$.ListObject(), (Formats)formats);
      return var4;
   }

   private Object customOrElse(final ScalaType target, final JValue json, final Function1 thunk, final Formats formats) {
      TypeInfo targetType = target.typeInfo();
      PartialFunction custom = Formats$.MODULE$.customDeserializer(new Tuple2(targetType, json), formats);
      PartialFunction customRich = Formats$.MODULE$.customRichDeserializer(new Tuple2(target, json), formats);
      return customRich.isDefinedAt(new Tuple2(target, json)) ? customRich.apply(new Tuple2(target, json)) : custom.applyOrElse(new Tuple2(targetType, json), (t) -> thunk.apply(t._2()));
   }

   private Object convert(final String key, final ScalaType target, final Formats formats) {
      Object var4;
      label200: {
         Class targetType = target.erasure();
         Class var7 = String.class;
         if (targetType == null) {
            if (var7 == null) {
               break label200;
            }
         } else if (targetType.equals(var7)) {
            break label200;
         }

         label201: {
            Class var8 = Symbol.class;
            if (targetType == null) {
               if (var8 == null) {
                  break label201;
               }
            } else if (targetType.equals(var8)) {
               break label201;
            }

            label202: {
               Class var9 = Integer.TYPE;
               if (targetType == null) {
                  if (var9 == null) {
                     break label202;
                  }
               } else if (targetType.equals(var9)) {
                  break label202;
               }

               label203: {
                  Class var10 = Integer.class;
                  if (targetType == null) {
                     if (var10 == null) {
                        break label203;
                     }
                  } else if (targetType.equals(var10)) {
                     break label203;
                  }

                  label204: {
                     Class var11 = BigInt.class;
                     if (targetType == null) {
                        if (var11 == null) {
                           break label204;
                        }
                     } else if (targetType.equals(var11)) {
                        break label204;
                     }

                     label205: {
                        Class var12 = Long.TYPE;
                        if (targetType == null) {
                           if (var12 == null) {
                              break label205;
                           }
                        } else if (targetType.equals(var12)) {
                           break label205;
                        }

                        label206: {
                           Class var13 = Long.class;
                           if (targetType == null) {
                              if (var13 == null) {
                                 break label206;
                              }
                           } else if (targetType.equals(var13)) {
                              break label206;
                           }

                           label207: {
                              Class var14 = Short.TYPE;
                              if (targetType == null) {
                                 if (var14 == null) {
                                    break label207;
                                 }
                              } else if (targetType.equals(var14)) {
                                 break label207;
                              }

                              label208: {
                                 Class var15 = Short.class;
                                 if (targetType == null) {
                                    if (var15 == null) {
                                       break label208;
                                    }
                                 } else if (targetType.equals(var15)) {
                                    break label208;
                                 }

                                 label209: {
                                    Class var16 = Date.class;
                                    if (targetType == null) {
                                       if (var16 == null) {
                                          break label209;
                                       }
                                    } else if (targetType.equals(var16)) {
                                       break label209;
                                    }

                                    label210: {
                                       Class var17 = Timestamp.class;
                                       if (targetType == null) {
                                          if (var17 == null) {
                                             break label210;
                                          }
                                       } else if (targetType.equals(var17)) {
                                          break label210;
                                       }

                                       TypeInfo typeInfo = new TypeInfo(targetType, scala.None..MODULE$);
                                       PartialFunction deserializer = Formats$.MODULE$.customKeyDeserializer(new Tuple2(typeInfo, key), formats);
                                       if (!deserializer.isDefinedAt(new Tuple2(typeInfo, key))) {
                                          throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(91)).append("Do not know how to deserialize key of type ").append(targetType).append(". Consider implementing a CustomKeyDeserializer.").toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
                                       }

                                       var4 = deserializer.apply(new Tuple2(typeInfo, key));
                                       return var4;
                                    }

                                    var4 = this.formatTimestamp(key, formats);
                                    return var4;
                                 }

                                 var4 = this.formatDate(key, formats);
                                 return var4;
                              }

                              var4 = scala.collection.StringOps..MODULE$.toShort$extension(scala.Predef..MODULE$.augmentString(key));
                              return var4;
                           }

                           var4 = BoxesRunTime.boxToShort(scala.collection.StringOps..MODULE$.toShort$extension(scala.Predef..MODULE$.augmentString(key)));
                           return var4;
                        }

                        var4 = scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(key));
                        return var4;
                     }

                     var4 = BoxesRunTime.boxToLong(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(key)));
                     return var4;
                  }

                  var4 = BoxesRunTime.boxToInteger(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(key)));
                  return var4;
               }

               var4 = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(key));
               return var4;
            }

            var4 = BoxesRunTime.boxToInteger(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(key)));
            return var4;
         }

         var4 = scala.Symbol..MODULE$.apply(key);
         return var4;
      }

      var4 = key;
      return var4;
   }

   private Object convert(final JValue json, final ScalaType target, final Formats formats, final Option default) {
      Object var5;
      BigInt x;
      label1306: {
         Class targetType = target.erasure();
         boolean var7 = false;
         JInt var8 = null;
         boolean var9 = false;
         JLong var10 = null;
         boolean var11 = false;
         JDouble var12 = null;
         boolean var13 = false;
         JDecimal var14 = null;
         boolean var15 = false;
         JString var16 = null;
         boolean var17 = false;
         JBool var18 = null;
         boolean var19 = false;
         Object var20 = null;
         if (json instanceof JInt) {
            var7 = true;
            var8 = (JInt)json;
            x = var8.num();
            Class var23 = Integer.TYPE;
            if (targetType == null) {
               if (var23 == null) {
                  break label1306;
               }
            } else if (targetType.equals(var23)) {
               break label1306;
            }
         }

         BigInt x;
         label1307: {
            if (var7) {
               x = var8.num();
               Class var25 = Integer.class;
               if (targetType == null) {
                  if (var25 == null) {
                     break label1307;
                  }
               } else if (targetType.equals(var25)) {
                  break label1307;
               }
            }

            BigInt x;
            label1308: {
               if (var7) {
                  x = var8.num();
                  Class var27 = BigInt.class;
                  if (targetType == null) {
                     if (var27 == null) {
                        break label1308;
                     }
                  } else if (targetType.equals(var27)) {
                     break label1308;
                  }
               }

               BigInt x;
               label1309: {
                  if (var7) {
                     x = var8.num();
                     Class var29 = Long.TYPE;
                     if (targetType == null) {
                        if (var29 == null) {
                           break label1309;
                        }
                     } else if (targetType.equals(var29)) {
                        break label1309;
                     }
                  }

                  BigInt x;
                  label1310: {
                     if (var7) {
                        x = var8.num();
                        Class var31 = Long.class;
                        if (targetType == null) {
                           if (var31 == null) {
                              break label1310;
                           }
                        } else if (targetType.equals(var31)) {
                           break label1310;
                        }
                     }

                     BigInt x;
                     label1311: {
                        if (var7) {
                           x = var8.num();
                           Class var33 = Double.TYPE;
                           if (targetType == null) {
                              if (var33 == null) {
                                 break label1311;
                              }
                           } else if (targetType.equals(var33)) {
                              break label1311;
                           }
                        }

                        BigInt x;
                        label1312: {
                           if (var7) {
                              x = var8.num();
                              Class var35 = Double.class;
                              if (targetType == null) {
                                 if (var35 == null) {
                                    break label1312;
                                 }
                              } else if (targetType.equals(var35)) {
                                 break label1312;
                              }
                           }

                           BigInt x;
                           label1313: {
                              if (var7) {
                                 x = var8.num();
                                 Class var37 = Float.TYPE;
                                 if (targetType == null) {
                                    if (var37 == null) {
                                       break label1313;
                                    }
                                 } else if (targetType.equals(var37)) {
                                    break label1313;
                                 }
                              }

                              BigInt x;
                              label1314: {
                                 if (var7) {
                                    x = var8.num();
                                    Class var39 = Float.class;
                                    if (targetType == null) {
                                       if (var39 == null) {
                                          break label1314;
                                       }
                                    } else if (targetType.equals(var39)) {
                                       break label1314;
                                    }
                                 }

                                 BigInt x;
                                 label1315: {
                                    if (var7) {
                                       x = var8.num();
                                       Class var41 = Short.TYPE;
                                       if (targetType == null) {
                                          if (var41 == null) {
                                             break label1315;
                                          }
                                       } else if (targetType.equals(var41)) {
                                          break label1315;
                                       }
                                    }

                                    BigInt x;
                                    label1316: {
                                       if (var7) {
                                          x = var8.num();
                                          Class var43 = Short.class;
                                          if (targetType == null) {
                                             if (var43 == null) {
                                                break label1316;
                                             }
                                          } else if (targetType.equals(var43)) {
                                             break label1316;
                                          }
                                       }

                                       BigInt x;
                                       label1317: {
                                          if (var7) {
                                             x = var8.num();
                                             Class var45 = Byte.TYPE;
                                             if (targetType == null) {
                                                if (var45 == null) {
                                                   break label1317;
                                                }
                                             } else if (targetType.equals(var45)) {
                                                break label1317;
                                             }
                                          }

                                          BigInt x;
                                          label1318: {
                                             if (var7) {
                                                x = var8.num();
                                                Class var47 = Byte.class;
                                                if (targetType == null) {
                                                   if (var47 == null) {
                                                      break label1318;
                                                   }
                                                } else if (targetType.equals(var47)) {
                                                   break label1318;
                                                }
                                             }

                                             BigInt x;
                                             label1319: {
                                                if (var7) {
                                                   x = var8.num();
                                                   Class var49 = String.class;
                                                   if (targetType == null) {
                                                      if (var49 == null) {
                                                         break label1319;
                                                      }
                                                   } else if (targetType.equals(var49)) {
                                                      break label1319;
                                                   }
                                                }

                                                BigInt x;
                                                label1320: {
                                                   if (var7) {
                                                      x = var8.num();
                                                      Class var51 = Number.class;
                                                      if (targetType == null) {
                                                         if (var51 == null) {
                                                            break label1320;
                                                         }
                                                      } else if (targetType.equals(var51)) {
                                                         break label1320;
                                                      }
                                                   }

                                                   BigInt x;
                                                   label1321: {
                                                      if (var7) {
                                                         x = var8.num();
                                                         Class var53 = BigDecimal.class;
                                                         if (targetType == null) {
                                                            if (var53 == null) {
                                                               break label1321;
                                                            }
                                                         } else if (targetType.equals(var53)) {
                                                            break label1321;
                                                         }
                                                      }

                                                      BigInt x;
                                                      label1322: {
                                                         if (var7) {
                                                            x = var8.num();
                                                            Class var55 = java.math.BigDecimal.class;
                                                            if (targetType == null) {
                                                               if (var55 == null) {
                                                                  break label1322;
                                                               }
                                                            } else if (targetType.equals(var55)) {
                                                               break label1322;
                                                            }
                                                         }

                                                         long x;
                                                         label1323: {
                                                            if (json instanceof JLong) {
                                                               var9 = true;
                                                               var10 = (JLong)json;
                                                               x = var10.num();
                                                               Class var58 = Integer.TYPE;
                                                               if (targetType == null) {
                                                                  if (var58 == null) {
                                                                     break label1323;
                                                                  }
                                                               } else if (targetType.equals(var58)) {
                                                                  break label1323;
                                                               }
                                                            }

                                                            long x;
                                                            label1324: {
                                                               if (var9) {
                                                                  x = var10.num();
                                                                  Class var61 = Integer.class;
                                                                  if (targetType == null) {
                                                                     if (var61 == null) {
                                                                        break label1324;
                                                                     }
                                                                  } else if (targetType.equals(var61)) {
                                                                     break label1324;
                                                                  }
                                                               }

                                                               long x;
                                                               label1325: {
                                                                  if (var9) {
                                                                     x = var10.num();
                                                                     Class var64 = BigInt.class;
                                                                     if (targetType == null) {
                                                                        if (var64 == null) {
                                                                           break label1325;
                                                                        }
                                                                     } else if (targetType.equals(var64)) {
                                                                        break label1325;
                                                                     }
                                                                  }

                                                                  long x;
                                                                  label1326: {
                                                                     if (var9) {
                                                                        x = var10.num();
                                                                        Class var67 = Long.TYPE;
                                                                        if (targetType == null) {
                                                                           if (var67 == null) {
                                                                              break label1326;
                                                                           }
                                                                        } else if (targetType.equals(var67)) {
                                                                           break label1326;
                                                                        }
                                                                     }

                                                                     long x;
                                                                     label1327: {
                                                                        if (var9) {
                                                                           x = var10.num();
                                                                           Class var70 = Long.class;
                                                                           if (targetType == null) {
                                                                              if (var70 == null) {
                                                                                 break label1327;
                                                                              }
                                                                           } else if (targetType.equals(var70)) {
                                                                              break label1327;
                                                                           }
                                                                        }

                                                                        long x;
                                                                        label1328: {
                                                                           if (var9) {
                                                                              x = var10.num();
                                                                              Class var73 = Double.TYPE;
                                                                              if (targetType == null) {
                                                                                 if (var73 == null) {
                                                                                    break label1328;
                                                                                 }
                                                                              } else if (targetType.equals(var73)) {
                                                                                 break label1328;
                                                                              }
                                                                           }

                                                                           long x;
                                                                           label1329: {
                                                                              if (var9) {
                                                                                 x = var10.num();
                                                                                 Class var76 = Double.class;
                                                                                 if (targetType == null) {
                                                                                    if (var76 == null) {
                                                                                       break label1329;
                                                                                    }
                                                                                 } else if (targetType.equals(var76)) {
                                                                                    break label1329;
                                                                                 }
                                                                              }

                                                                              long x;
                                                                              label1330: {
                                                                                 if (var9) {
                                                                                    x = var10.num();
                                                                                    Class var79 = Float.TYPE;
                                                                                    if (targetType == null) {
                                                                                       if (var79 == null) {
                                                                                          break label1330;
                                                                                       }
                                                                                    } else if (targetType.equals(var79)) {
                                                                                       break label1330;
                                                                                    }
                                                                                 }

                                                                                 long x;
                                                                                 label1331: {
                                                                                    if (var9) {
                                                                                       x = var10.num();
                                                                                       Class var82 = Float.class;
                                                                                       if (targetType == null) {
                                                                                          if (var82 == null) {
                                                                                             break label1331;
                                                                                          }
                                                                                       } else if (targetType.equals(var82)) {
                                                                                          break label1331;
                                                                                       }
                                                                                    }

                                                                                    long x;
                                                                                    label1332: {
                                                                                       if (var9) {
                                                                                          x = var10.num();
                                                                                          Class var85 = Short.TYPE;
                                                                                          if (targetType == null) {
                                                                                             if (var85 == null) {
                                                                                                break label1332;
                                                                                             }
                                                                                          } else if (targetType.equals(var85)) {
                                                                                             break label1332;
                                                                                          }
                                                                                       }

                                                                                       long x;
                                                                                       label1333: {
                                                                                          if (var9) {
                                                                                             x = var10.num();
                                                                                             Class var88 = Short.class;
                                                                                             if (targetType == null) {
                                                                                                if (var88 == null) {
                                                                                                   break label1333;
                                                                                                }
                                                                                             } else if (targetType.equals(var88)) {
                                                                                                break label1333;
                                                                                             }
                                                                                          }

                                                                                          long x;
                                                                                          label1334: {
                                                                                             if (var9) {
                                                                                                x = var10.num();
                                                                                                Class var91 = Byte.TYPE;
                                                                                                if (targetType == null) {
                                                                                                   if (var91 == null) {
                                                                                                      break label1334;
                                                                                                   }
                                                                                                } else if (targetType.equals(var91)) {
                                                                                                   break label1334;
                                                                                                }
                                                                                             }

                                                                                             long x;
                                                                                             label1335: {
                                                                                                if (var9) {
                                                                                                   x = var10.num();
                                                                                                   Class var94 = Byte.class;
                                                                                                   if (targetType == null) {
                                                                                                      if (var94 == null) {
                                                                                                         break label1335;
                                                                                                      }
                                                                                                   } else if (targetType.equals(var94)) {
                                                                                                      break label1335;
                                                                                                   }
                                                                                                }

                                                                                                long x;
                                                                                                label1336: {
                                                                                                   if (var9) {
                                                                                                      x = var10.num();
                                                                                                      Class var97 = String.class;
                                                                                                      if (targetType == null) {
                                                                                                         if (var97 == null) {
                                                                                                            break label1336;
                                                                                                         }
                                                                                                      } else if (targetType.equals(var97)) {
                                                                                                         break label1336;
                                                                                                      }
                                                                                                   }

                                                                                                   long x;
                                                                                                   label1337: {
                                                                                                      if (var9) {
                                                                                                         x = var10.num();
                                                                                                         Class var100 = Number.class;
                                                                                                         if (targetType == null) {
                                                                                                            if (var100 == null) {
                                                                                                               break label1337;
                                                                                                            }
                                                                                                         } else if (targetType.equals(var100)) {
                                                                                                            break label1337;
                                                                                                         }
                                                                                                      }

                                                                                                      long x;
                                                                                                      label1338: {
                                                                                                         if (var9) {
                                                                                                            x = var10.num();
                                                                                                            Class var103 = BigDecimal.class;
                                                                                                            if (targetType == null) {
                                                                                                               if (var103 == null) {
                                                                                                                  break label1338;
                                                                                                               }
                                                                                                            } else if (targetType.equals(var103)) {
                                                                                                               break label1338;
                                                                                                            }
                                                                                                         }

                                                                                                         long x;
                                                                                                         label1339: {
                                                                                                            if (var9) {
                                                                                                               x = var10.num();
                                                                                                               Class var106 = java.math.BigDecimal.class;
                                                                                                               if (targetType == null) {
                                                                                                                  if (var106 == null) {
                                                                                                                     break label1339;
                                                                                                                  }
                                                                                                               } else if (targetType.equals(var106)) {
                                                                                                                  break label1339;
                                                                                                               }
                                                                                                            }

                                                                                                            double x;
                                                                                                            label1340: {
                                                                                                               if (json instanceof JDouble) {
                                                                                                                  var11 = true;
                                                                                                                  var12 = (JDouble)json;
                                                                                                                  x = var12.num();
                                                                                                                  Class var109 = Double.TYPE;
                                                                                                                  if (targetType == null) {
                                                                                                                     if (var109 == null) {
                                                                                                                        break label1340;
                                                                                                                     }
                                                                                                                  } else if (targetType.equals(var109)) {
                                                                                                                     break label1340;
                                                                                                                  }
                                                                                                               }

                                                                                                               double x;
                                                                                                               label1341: {
                                                                                                                  if (var11) {
                                                                                                                     x = var12.num();
                                                                                                                     Class var112 = Double.class;
                                                                                                                     if (targetType == null) {
                                                                                                                        if (var112 == null) {
                                                                                                                           break label1341;
                                                                                                                        }
                                                                                                                     } else if (targetType.equals(var112)) {
                                                                                                                        break label1341;
                                                                                                                     }
                                                                                                                  }

                                                                                                                  double x;
                                                                                                                  label1342: {
                                                                                                                     if (var11) {
                                                                                                                        x = var12.num();
                                                                                                                        Class var115 = Float.TYPE;
                                                                                                                        if (targetType == null) {
                                                                                                                           if (var115 == null) {
                                                                                                                              break label1342;
                                                                                                                           }
                                                                                                                        } else if (targetType.equals(var115)) {
                                                                                                                           break label1342;
                                                                                                                        }
                                                                                                                     }

                                                                                                                     double x;
                                                                                                                     label1343: {
                                                                                                                        if (var11) {
                                                                                                                           x = var12.num();
                                                                                                                           Class var118 = Float.class;
                                                                                                                           if (targetType == null) {
                                                                                                                              if (var118 == null) {
                                                                                                                                 break label1343;
                                                                                                                              }
                                                                                                                           } else if (targetType.equals(var118)) {
                                                                                                                              break label1343;
                                                                                                                           }
                                                                                                                        }

                                                                                                                        double x;
                                                                                                                        label1344: {
                                                                                                                           if (var11) {
                                                                                                                              x = var12.num();
                                                                                                                              Class var121 = String.class;
                                                                                                                              if (targetType == null) {
                                                                                                                                 if (var121 == null) {
                                                                                                                                    break label1344;
                                                                                                                                 }
                                                                                                                              } else if (targetType.equals(var121)) {
                                                                                                                                 break label1344;
                                                                                                                              }
                                                                                                                           }

                                                                                                                           double x;
                                                                                                                           label1345: {
                                                                                                                              if (var11) {
                                                                                                                                 x = var12.num();
                                                                                                                                 Class var124 = Integer.TYPE;
                                                                                                                                 if (targetType == null) {
                                                                                                                                    if (var124 == null) {
                                                                                                                                       break label1345;
                                                                                                                                    }
                                                                                                                                 } else if (targetType.equals(var124)) {
                                                                                                                                    break label1345;
                                                                                                                                 }
                                                                                                                              }

                                                                                                                              double x;
                                                                                                                              label1346: {
                                                                                                                                 if (var11) {
                                                                                                                                    x = var12.num();
                                                                                                                                    Class var127 = Long.TYPE;
                                                                                                                                    if (targetType == null) {
                                                                                                                                       if (var127 == null) {
                                                                                                                                          break label1346;
                                                                                                                                       }
                                                                                                                                    } else if (targetType.equals(var127)) {
                                                                                                                                       break label1346;
                                                                                                                                    }
                                                                                                                                 }

                                                                                                                                 double x;
                                                                                                                                 label1347: {
                                                                                                                                    if (var11) {
                                                                                                                                       x = var12.num();
                                                                                                                                       Class var130 = Number.class;
                                                                                                                                       if (targetType == null) {
                                                                                                                                          if (var130 == null) {
                                                                                                                                             break label1347;
                                                                                                                                          }
                                                                                                                                       } else if (targetType.equals(var130)) {
                                                                                                                                          break label1347;
                                                                                                                                       }
                                                                                                                                    }

                                                                                                                                    double x;
                                                                                                                                    label1348: {
                                                                                                                                       if (var11) {
                                                                                                                                          x = var12.num();
                                                                                                                                          Class var133 = BigDecimal.class;
                                                                                                                                          if (targetType == null) {
                                                                                                                                             if (var133 == null) {
                                                                                                                                                break label1348;
                                                                                                                                             }
                                                                                                                                          } else if (targetType.equals(var133)) {
                                                                                                                                             break label1348;
                                                                                                                                          }
                                                                                                                                       }

                                                                                                                                       double x;
                                                                                                                                       label1349: {
                                                                                                                                          if (var11) {
                                                                                                                                             x = var12.num();
                                                                                                                                             Class var136 = java.math.BigDecimal.class;
                                                                                                                                             if (targetType == null) {
                                                                                                                                                if (var136 == null) {
                                                                                                                                                   break label1349;
                                                                                                                                                }
                                                                                                                                             } else if (targetType.equals(var136)) {
                                                                                                                                                break label1349;
                                                                                                                                             }
                                                                                                                                          }

                                                                                                                                          BigDecimal x;
                                                                                                                                          label1350: {
                                                                                                                                             if (json instanceof JDecimal) {
                                                                                                                                                var13 = true;
                                                                                                                                                var14 = (JDecimal)json;
                                                                                                                                                x = var14.num();
                                                                                                                                                Class var138 = Double.TYPE;
                                                                                                                                                if (targetType == null) {
                                                                                                                                                   if (var138 == null) {
                                                                                                                                                      break label1350;
                                                                                                                                                   }
                                                                                                                                                } else if (targetType.equals(var138)) {
                                                                                                                                                   break label1350;
                                                                                                                                                }
                                                                                                                                             }

                                                                                                                                             BigDecimal x;
                                                                                                                                             label1351: {
                                                                                                                                                if (var13) {
                                                                                                                                                   x = var14.num();
                                                                                                                                                   Class var140 = Double.class;
                                                                                                                                                   if (targetType == null) {
                                                                                                                                                      if (var140 == null) {
                                                                                                                                                         break label1351;
                                                                                                                                                      }
                                                                                                                                                   } else if (targetType.equals(var140)) {
                                                                                                                                                      break label1351;
                                                                                                                                                   }
                                                                                                                                                }

                                                                                                                                                BigDecimal x;
                                                                                                                                                label1352: {
                                                                                                                                                   if (var13) {
                                                                                                                                                      x = var14.num();
                                                                                                                                                      Class var142 = BigDecimal.class;
                                                                                                                                                      if (targetType == null) {
                                                                                                                                                         if (var142 == null) {
                                                                                                                                                            break label1352;
                                                                                                                                                         }
                                                                                                                                                      } else if (targetType.equals(var142)) {
                                                                                                                                                         break label1352;
                                                                                                                                                      }
                                                                                                                                                   }

                                                                                                                                                   BigDecimal x;
                                                                                                                                                   label1353: {
                                                                                                                                                      if (var13) {
                                                                                                                                                         x = var14.num();
                                                                                                                                                         Class var144 = java.math.BigDecimal.class;
                                                                                                                                                         if (targetType == null) {
                                                                                                                                                            if (var144 == null) {
                                                                                                                                                               break label1353;
                                                                                                                                                            }
                                                                                                                                                         } else if (targetType.equals(var144)) {
                                                                                                                                                            break label1353;
                                                                                                                                                         }
                                                                                                                                                      }

                                                                                                                                                      BigDecimal x;
                                                                                                                                                      label1354: {
                                                                                                                                                         if (var13) {
                                                                                                                                                            x = var14.num();
                                                                                                                                                            Class var146 = Float.TYPE;
                                                                                                                                                            if (targetType == null) {
                                                                                                                                                               if (var146 == null) {
                                                                                                                                                                  break label1354;
                                                                                                                                                               }
                                                                                                                                                            } else if (targetType.equals(var146)) {
                                                                                                                                                               break label1354;
                                                                                                                                                            }
                                                                                                                                                         }

                                                                                                                                                         BigDecimal x;
                                                                                                                                                         label1355: {
                                                                                                                                                            if (var13) {
                                                                                                                                                               x = var14.num();
                                                                                                                                                               Class var148 = Float.class;
                                                                                                                                                               if (targetType == null) {
                                                                                                                                                                  if (var148 == null) {
                                                                                                                                                                     break label1355;
                                                                                                                                                                  }
                                                                                                                                                               } else if (targetType.equals(var148)) {
                                                                                                                                                                  break label1355;
                                                                                                                                                               }
                                                                                                                                                            }

                                                                                                                                                            BigDecimal x;
                                                                                                                                                            label1356: {
                                                                                                                                                               if (var13) {
                                                                                                                                                                  x = var14.num();
                                                                                                                                                                  Class var150 = String.class;
                                                                                                                                                                  if (targetType == null) {
                                                                                                                                                                     if (var150 == null) {
                                                                                                                                                                        break label1356;
                                                                                                                                                                     }
                                                                                                                                                                  } else if (targetType.equals(var150)) {
                                                                                                                                                                     break label1356;
                                                                                                                                                                  }
                                                                                                                                                               }

                                                                                                                                                               BigDecimal x;
                                                                                                                                                               label1357: {
                                                                                                                                                                  if (var13) {
                                                                                                                                                                     x = var14.num();
                                                                                                                                                                     Class var152 = Integer.TYPE;
                                                                                                                                                                     if (targetType == null) {
                                                                                                                                                                        if (var152 == null) {
                                                                                                                                                                           break label1357;
                                                                                                                                                                        }
                                                                                                                                                                     } else if (targetType.equals(var152)) {
                                                                                                                                                                        break label1357;
                                                                                                                                                                     }
                                                                                                                                                                  }

                                                                                                                                                                  BigDecimal x;
                                                                                                                                                                  label1358: {
                                                                                                                                                                     if (var13) {
                                                                                                                                                                        x = var14.num();
                                                                                                                                                                        Class var154 = Long.TYPE;
                                                                                                                                                                        if (targetType == null) {
                                                                                                                                                                           if (var154 == null) {
                                                                                                                                                                              break label1358;
                                                                                                                                                                           }
                                                                                                                                                                        } else if (targetType.equals(var154)) {
                                                                                                                                                                           break label1358;
                                                                                                                                                                        }
                                                                                                                                                                     }

                                                                                                                                                                     BigDecimal x;
                                                                                                                                                                     label1359: {
                                                                                                                                                                        if (var13) {
                                                                                                                                                                           x = var14.num();
                                                                                                                                                                           Class var156 = Number.class;
                                                                                                                                                                           if (targetType == null) {
                                                                                                                                                                              if (var156 == null) {
                                                                                                                                                                                 break label1359;
                                                                                                                                                                              }
                                                                                                                                                                           } else if (targetType.equals(var156)) {
                                                                                                                                                                              break label1359;
                                                                                                                                                                           }
                                                                                                                                                                        }

                                                                                                                                                                        String s;
                                                                                                                                                                        label1360: {
                                                                                                                                                                           if (json instanceof JString) {
                                                                                                                                                                              var15 = true;
                                                                                                                                                                              var16 = (JString)json;
                                                                                                                                                                              s = var16.s();
                                                                                                                                                                              Class var158 = String.class;
                                                                                                                                                                              if (targetType == null) {
                                                                                                                                                                                 if (var158 == null) {
                                                                                                                                                                                    break label1360;
                                                                                                                                                                                 }
                                                                                                                                                                              } else if (targetType.equals(var158)) {
                                                                                                                                                                                 break label1360;
                                                                                                                                                                              }
                                                                                                                                                                           }

                                                                                                                                                                           String s;
                                                                                                                                                                           label1361: {
                                                                                                                                                                              if (var15) {
                                                                                                                                                                                 s = var16.s();
                                                                                                                                                                                 Class var160 = Symbol.class;
                                                                                                                                                                                 if (targetType == null) {
                                                                                                                                                                                    if (var160 == null) {
                                                                                                                                                                                       break label1361;
                                                                                                                                                                                    }
                                                                                                                                                                                 } else if (targetType.equals(var160)) {
                                                                                                                                                                                    break label1361;
                                                                                                                                                                                 }
                                                                                                                                                                              }

                                                                                                                                                                              String s;
                                                                                                                                                                              label1362: {
                                                                                                                                                                                 if (var15) {
                                                                                                                                                                                    s = var16.s();
                                                                                                                                                                                    Class var162 = Date.class;
                                                                                                                                                                                    if (targetType == null) {
                                                                                                                                                                                       if (var162 == null) {
                                                                                                                                                                                          break label1362;
                                                                                                                                                                                       }
                                                                                                                                                                                    } else if (targetType.equals(var162)) {
                                                                                                                                                                                       break label1362;
                                                                                                                                                                                    }
                                                                                                                                                                                 }

                                                                                                                                                                                 String s;
                                                                                                                                                                                 label1363: {
                                                                                                                                                                                    if (var15) {
                                                                                                                                                                                       s = var16.s();
                                                                                                                                                                                       Class var164 = Timestamp.class;
                                                                                                                                                                                       if (targetType == null) {
                                                                                                                                                                                          if (var164 == null) {
                                                                                                                                                                                             break label1363;
                                                                                                                                                                                          }
                                                                                                                                                                                       } else if (targetType.equals(var164)) {
                                                                                                                                                                                          break label1363;
                                                                                                                                                                                       }
                                                                                                                                                                                    }

                                                                                                                                                                                    boolean x;
                                                                                                                                                                                    label1364: {
                                                                                                                                                                                       if (json instanceof JBool) {
                                                                                                                                                                                          var17 = true;
                                                                                                                                                                                          var18 = (JBool)json;
                                                                                                                                                                                          x = var18.value();
                                                                                                                                                                                          Class var166 = Boolean.TYPE;
                                                                                                                                                                                          if (targetType == null) {
                                                                                                                                                                                             if (var166 == null) {
                                                                                                                                                                                                break label1364;
                                                                                                                                                                                             }
                                                                                                                                                                                          } else if (targetType.equals(var166)) {
                                                                                                                                                                                             break label1364;
                                                                                                                                                                                          }
                                                                                                                                                                                       }

                                                                                                                                                                                       boolean x;
                                                                                                                                                                                       label1365: {
                                                                                                                                                                                          if (var17) {
                                                                                                                                                                                             x = var18.value();
                                                                                                                                                                                             Class var168 = Boolean.class;
                                                                                                                                                                                             if (targetType == null) {
                                                                                                                                                                                                if (var168 == null) {
                                                                                                                                                                                                   break label1365;
                                                                                                                                                                                                }
                                                                                                                                                                                             } else if (targetType.equals(var168)) {
                                                                                                                                                                                                break label1365;
                                                                                                                                                                                             }
                                                                                                                                                                                          }

                                                                                                                                                                                          label1366: {
                                                                                                                                                                                             if (json != null) {
                                                                                                                                                                                                Class var170 = JValue.class;
                                                                                                                                                                                                if (targetType == null) {
                                                                                                                                                                                                   if (var170 == null) {
                                                                                                                                                                                                      break label1366;
                                                                                                                                                                                                   }
                                                                                                                                                                                                } else if (targetType.equals(var170)) {
                                                                                                                                                                                                   break label1366;
                                                                                                                                                                                                }
                                                                                                                                                                                             }

                                                                                                                                                                                             JObject var171;
                                                                                                                                                                                             label1367: {
                                                                                                                                                                                                if (json instanceof JObject) {
                                                                                                                                                                                                   var171 = (JObject)json;
                                                                                                                                                                                                   Class var172 = JObject.class;
                                                                                                                                                                                                   if (targetType == null) {
                                                                                                                                                                                                      if (var172 == null) {
                                                                                                                                                                                                         break label1367;
                                                                                                                                                                                                      }
                                                                                                                                                                                                   } else if (targetType.equals(var172)) {
                                                                                                                                                                                                      break label1367;
                                                                                                                                                                                                   }
                                                                                                                                                                                                }

                                                                                                                                                                                                JArray var173;
                                                                                                                                                                                                label1368: {
                                                                                                                                                                                                   if (json instanceof JArray) {
                                                                                                                                                                                                      var173 = (JArray)json;
                                                                                                                                                                                                      Class var174 = JArray.class;
                                                                                                                                                                                                      if (targetType == null) {
                                                                                                                                                                                                         if (var174 == null) {
                                                                                                                                                                                                            break label1368;
                                                                                                                                                                                                         }
                                                                                                                                                                                                      } else if (targetType.equals(var174)) {
                                                                                                                                                                                                         break label1368;
                                                                                                                                                                                                      }
                                                                                                                                                                                                   }

                                                                                                                                                                                                   label1369: {
                                                                                                                                                                                                      if (org.json4s.JNull..MODULE$.equals(json)) {
                                                                                                                                                                                                         var19 = true;
                                                                                                                                                                                                         ExtractionNullStrategy var10000 = formats.extractionNullStrategy();
                                                                                                                                                                                                         ExtractionNullStrategy.Keep$ var175 = ExtractionNullStrategy.Keep$.MODULE$;
                                                                                                                                                                                                         if (var10000 == null) {
                                                                                                                                                                                                            if (var175 == null) {
                                                                                                                                                                                                               break label1369;
                                                                                                                                                                                                            }
                                                                                                                                                                                                         } else if (var10000.equals(var175)) {
                                                                                                                                                                                                            break label1369;
                                                                                                                                                                                                         }
                                                                                                                                                                                                      }

                                                                                                                                                                                                      if (var19) {
                                                                                                                                                                                                         throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(47)).append("Did not find value which can be converted into ").append(targetType.getName()).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
                                                                                                                                                                                                      }

                                                                                                                                                                                                      if (org.json4s.JNothing..MODULE$.equals(json)) {
                                                                                                                                                                                                         var5 = default.map((x$25) -> x$25.apply()).getOrElse(() -> org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(47)).append("Did not find value which can be converted into ").append(targetType.getName()).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2()));
                                                                                                                                                                                                      } else {
                                                                                                                                                                                                         LazyRef richCustom$lzy = new LazyRef();
                                                                                                                                                                                                         TypeInfo typeInfo = target.typeInfo();
                                                                                                                                                                                                         PartialFunction custom = Formats$.MODULE$.customDeserializer(new Tuple2(typeInfo, json), formats);
                                                                                                                                                                                                         Object var179;
                                                                                                                                                                                                         if (custom.isDefinedAt(new Tuple2(typeInfo, json))) {
                                                                                                                                                                                                            var179 = custom.apply(new Tuple2(typeInfo, json));
                                                                                                                                                                                                         } else {
                                                                                                                                                                                                            if (!richCustom$2(richCustom$lzy, target, json, formats).isDefinedAt(new Tuple2(target, json))) {
                                                                                                                                                                                                               throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(33)).append("Do not know how to convert ").append(json).append(" into ").append(targetType).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
                                                                                                                                                                                                            }

                                                                                                                                                                                                            var179 = richCustom$2(richCustom$lzy, target, json, formats).apply(new Tuple2(target, json));
                                                                                                                                                                                                         }

                                                                                                                                                                                                         var5 = var179;
                                                                                                                                                                                                      }

                                                                                                                                                                                                      return var5;
                                                                                                                                                                                                   }

                                                                                                                                                                                                   var5 = null;
                                                                                                                                                                                                   return var5;
                                                                                                                                                                                                }

                                                                                                                                                                                                var5 = var173;
                                                                                                                                                                                                return var5;
                                                                                                                                                                                             }

                                                                                                                                                                                             var5 = var171;
                                                                                                                                                                                             return var5;
                                                                                                                                                                                          }

                                                                                                                                                                                          var5 = json;
                                                                                                                                                                                          return var5;
                                                                                                                                                                                       }

                                                                                                                                                                                       var5 = x;
                                                                                                                                                                                       return var5;
                                                                                                                                                                                    }

                                                                                                                                                                                    var5 = BoxesRunTime.boxToBoolean(x);
                                                                                                                                                                                    return var5;
                                                                                                                                                                                 }

                                                                                                                                                                                 var5 = this.formatTimestamp(s, formats);
                                                                                                                                                                                 return var5;
                                                                                                                                                                              }

                                                                                                                                                                              var5 = this.formatDate(s, formats);
                                                                                                                                                                              return var5;
                                                                                                                                                                           }

                                                                                                                                                                           var5 = scala.Symbol..MODULE$.apply(s);
                                                                                                                                                                           return var5;
                                                                                                                                                                        }

                                                                                                                                                                        var5 = s;
                                                                                                                                                                        return var5;
                                                                                                                                                                     }

                                                                                                                                                                     var5 = x;
                                                                                                                                                                     return var5;
                                                                                                                                                                  }

                                                                                                                                                                  var5 = BoxesRunTime.boxToLong(x.longValue());
                                                                                                                                                                  return var5;
                                                                                                                                                               }

                                                                                                                                                               var5 = BoxesRunTime.boxToInteger(x.intValue());
                                                                                                                                                               return var5;
                                                                                                                                                            }

                                                                                                                                                            var5 = x.toString();
                                                                                                                                                            return var5;
                                                                                                                                                         }

                                                                                                                                                         var5 = x.floatValue();
                                                                                                                                                         return var5;
                                                                                                                                                      }

                                                                                                                                                      var5 = BoxesRunTime.boxToFloat(x.floatValue());
                                                                                                                                                      return var5;
                                                                                                                                                   }

                                                                                                                                                   var5 = x.bigDecimal();
                                                                                                                                                   return var5;
                                                                                                                                                }

                                                                                                                                                var5 = x;
                                                                                                                                                return var5;
                                                                                                                                             }

                                                                                                                                             var5 = x.doubleValue();
                                                                                                                                             return var5;
                                                                                                                                          }

                                                                                                                                          var5 = BoxesRunTime.boxToDouble(x.doubleValue());
                                                                                                                                          return var5;
                                                                                                                                       }

                                                                                                                                       var5 = scala.package..MODULE$.BigDecimal().apply(x).bigDecimal();
                                                                                                                                       return var5;
                                                                                                                                    }

                                                                                                                                    var5 = scala.package..MODULE$.BigDecimal().apply(x);
                                                                                                                                    return var5;
                                                                                                                                 }

                                                                                                                                 var5 = BoxesRunTime.boxToDouble(x);
                                                                                                                                 return var5;
                                                                                                                              }

                                                                                                                              var5 = BoxesRunTime.boxToLong((long)x);
                                                                                                                              return var5;
                                                                                                                           }

                                                                                                                           var5 = BoxesRunTime.boxToInteger((int)x);
                                                                                                                           return var5;
                                                                                                                        }

                                                                                                                        var5 = Double.toString(x);
                                                                                                                        return var5;
                                                                                                                     }

                                                                                                                     var5 = (float)x;
                                                                                                                     return var5;
                                                                                                                  }

                                                                                                                  var5 = BoxesRunTime.boxToFloat((float)x);
                                                                                                                  return var5;
                                                                                                               }

                                                                                                               var5 = x;
                                                                                                               return var5;
                                                                                                            }

                                                                                                            var5 = BoxesRunTime.boxToDouble(x);
                                                                                                            return var5;
                                                                                                         }

                                                                                                         var5 = scala.package..MODULE$.BigDecimal().apply(x).bigDecimal();
                                                                                                         return var5;
                                                                                                      }

                                                                                                      var5 = scala.package..MODULE$.BigDecimal().apply(x);
                                                                                                      return var5;
                                                                                                   }

                                                                                                   var5 = BoxesRunTime.boxToLong(x);
                                                                                                   return var5;
                                                                                                }

                                                                                                var5 = Long.toString(x);
                                                                                                return var5;
                                                                                             }

                                                                                             var5 = (byte)((int)x);
                                                                                             return var5;
                                                                                          }

                                                                                          var5 = BoxesRunTime.boxToByte((byte)((int)x));
                                                                                          return var5;
                                                                                       }

                                                                                       var5 = (short)((int)x);
                                                                                       return var5;
                                                                                    }

                                                                                    var5 = BoxesRunTime.boxToShort((short)((int)x));
                                                                                    return var5;
                                                                                 }

                                                                                 var5 = (float)x;
                                                                                 return var5;
                                                                              }

                                                                              var5 = BoxesRunTime.boxToFloat((float)x);
                                                                              return var5;
                                                                           }

                                                                           var5 = (double)x;
                                                                           return var5;
                                                                        }

                                                                        var5 = BoxesRunTime.boxToDouble((double)x);
                                                                        return var5;
                                                                     }

                                                                     var5 = x;
                                                                     return var5;
                                                                  }

                                                                  var5 = BoxesRunTime.boxToLong(x);
                                                                  return var5;
                                                               }

                                                               var5 = BoxesRunTime.boxToLong(x);
                                                               return var5;
                                                            }

                                                            var5 = (int)x;
                                                            return var5;
                                                         }

                                                         var5 = BoxesRunTime.boxToInteger((int)x);
                                                         return var5;
                                                      }

                                                      var5 = scala.package..MODULE$.BigDecimal().apply(x).bigDecimal();
                                                      return var5;
                                                   }

                                                   var5 = scala.package..MODULE$.BigDecimal().apply(x);
                                                   return var5;
                                                }

                                                var5 = BoxesRunTime.boxToLong(x.longValue());
                                                return var5;
                                             }

                                             var5 = x.toString();
                                             return var5;
                                          }

                                          var5 = x.byteValue();
                                          return var5;
                                       }

                                       var5 = BoxesRunTime.boxToByte(x.byteValue());
                                       return var5;
                                    }

                                    var5 = x.shortValue();
                                    return var5;
                                 }

                                 var5 = BoxesRunTime.boxToShort(x.shortValue());
                                 return var5;
                              }

                              var5 = x.floatValue();
                              return var5;
                           }

                           var5 = BoxesRunTime.boxToFloat(x.floatValue());
                           return var5;
                        }

                        var5 = x.doubleValue();
                        return var5;
                     }

                     var5 = BoxesRunTime.boxToDouble(x.doubleValue());
                     return var5;
                  }

                  var5 = x.longValue();
                  return var5;
               }

               var5 = BoxesRunTime.boxToLong(x.longValue());
               return var5;
            }

            var5 = x;
            return var5;
         }

         var5 = x.intValue();
         return var5;
      }

      var5 = BoxesRunTime.boxToInteger(x.intValue());
      return var5;
   }

   private Timestamp formatTimestamp(final String s, final Formats formats) {
      return new Timestamp(((Date)formats.dateFormat().parse(s).getOrElse(() -> org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(15)).append("Invalid date '").append(s).append("'").toString(), org.json4s.reflect.package$.MODULE$.fail$default$2()))).getTime());
   }

   private Date formatDate(final String s, final Formats formats) {
      return (Date)formats.dateFormat().parse(s).getOrElse(() -> org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(15)).append("Invalid date '").append(s).append("'").toString(), org.json4s.reflect.package$.MODULE$.fail$default$2()));
   }

   private static final Option prependTypeHint$1(final Class clazz, final JObject o, final Formats formats$1) {
      return formats$1.typeHints().hintFor(clazz).flatMap((hint) -> formats$1.typeHints().typeHintFieldNameForHint(hint, clazz).map((typeHintFieldName) -> {
            Tuple2 var3 = org.json4s.JField..MODULE$.apply(typeHintFieldName, new JString(hint));
            return new JObject(o.obj().$colon$colon(var3));
         }));
   }

   // $FF: synthetic method
   public static final void $anonfun$internalDecomposeWithBuilder$3(final JsonWriter obj$1, final String name$1, final Formats formats$1, final Object x$1) {
      MODULE$.internalDecomposeWithBuilder(x$1, obj$1.startField(name$1), formats$1);
   }

   private final void addField$1(final String name, final Object v, final JsonWriter obj, final Formats formats$1) {
      if (scala.None..MODULE$.equals(v)) {
         formats$1.emptyValueStrategy().noneValReplacement().foreach((x$1) -> {
            $anonfun$internalDecomposeWithBuilder$3(obj, name, formats$1, x$1);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var5 = BoxedUnit.UNIT;
      } else {
         this.internalDecomposeWithBuilder(v, obj.startField(name), formats$1);
         BoxedUnit var7 = BoxedUnit.UNIT;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$internalDecomposeWithBuilder$6(final Formats formats$1, final Class k$1, final JsonWriter obj$2, final String hintName) {
      formats$1.typeHints().hintFor(k$1).foreach((hintValue) -> obj$2.startField(hintName).string(hintValue));
   }

   // $FF: synthetic method
   public static final void $anonfun$internalDecomposeWithBuilder$8(final Extraction$ $this, final FieldSerializer fieldSerializer$1, final Object a$1, final JsonWriter obj$2, final Formats formats$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String nn = (String)x0$1._1();
         Object vv = x0$1._2();
         Object vvv = fieldSerializer$1.includeLazyVal() ? MODULE$.loadLazyValValue(a$1, nn, vv) : vv;
         $this.addField$1(nn, vvv, obj$2, formats$1);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private final JsonWriter decomposeObject$1(final Class k, final Formats formats$1, final JsonWriter current$1, final Object any$1, final Object a$1) {
      ScalaType klass = Reflector$.MODULE$.scalaTypeOf(k);
      ClassDescriptor descriptor = (ClassDescriptor)Reflector$.MODULE$.describeWithFormats(org.json4s.reflect.package$.MODULE$.scalaTypeDescribable(klass, formats$1), formats$1);
      Seq ctorParams = (Seq)descriptor.mostComprehensive().map((x$2) -> x$2.name());
      Seq methods = (Seq)scala.collection.ArrayOps..MODULE$.toSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])k.getMethods())).map((x$3) -> x$3.getName());
      Iterator iter = descriptor.properties().iterator();
      JsonWriter obj = current$1.startObject();
      formats$1.typeHints().typeHintFieldNameForClass(k).foreach((hintName) -> {
         $anonfun$internalDecomposeWithBuilder$6(formats$1, k, obj, hintName);
         return BoxedUnit.UNIT;
      });
      Option fs = formats$1.fieldSerializer(k);

      while(iter.hasNext()) {
         PropertyDescriptor prop = (PropertyDescriptor)iter.next();
         Object fieldVal = prop.get(any$1);
         String n = prop.name();
         if (fs instanceof Some) {
            Some var18 = (Some)fs;
            FieldSerializer fieldSerializer = (FieldSerializer)var18.value();
            Option ff = (Option)fieldSerializer.serializer().orElse((PartialFunction)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(new Tuple2(n, fieldVal)), new Some(new Tuple2(n, fieldVal)))})))).apply(new Tuple2(n, fieldVal));
            ff.foreach((x0$1) -> {
               $anonfun$internalDecomposeWithBuilder$8(this, fieldSerializer, a$1, obj, formats$1, x0$1);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var22 = BoxedUnit.UNIT;
         } else if (scala.None..MODULE$.equals(fs) && ctorParams.contains(prop.name()) && methods.contains(scala.reflect.NameTransformer..MODULE$.encode(prop.name()))) {
            this.addField$1(n, fieldVal, obj, formats$1);
            BoxedUnit var21 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var6 = BoxedUnit.UNIT;
         }
      }

      return obj.endObject();
   }

   // $FF: synthetic method
   private static final PartialFunction richCustom$lzycompute$1(final LazyRef richCustom$lzy$1, final Object a$1, final Formats formats$1) {
      synchronized(richCustom$lzy$1){}

      PartialFunction var4;
      try {
         var4 = richCustom$lzy$1.initialized() ? (PartialFunction)richCustom$lzy$1.value() : (PartialFunction)richCustom$lzy$1.initialize(Formats$.MODULE$.customRichSerializer(a$1, formats$1));
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   private static final PartialFunction richCustom$1(final LazyRef richCustom$lzy$1, final Object a$1, final Formats formats$1) {
      return richCustom$lzy$1.initialized() ? (PartialFunction)richCustom$lzy$1.value() : richCustom$lzycompute$1(richCustom$lzy$1, a$1, formats$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$internalDecomposeWithBuilder$9(final JsonWriter current$1, final Formats formats$1, final Object x$4) {
      MODULE$.internalDecomposeWithBuilder(x$4, current$1, formats$1);
   }

   private static final String escapePath$1(final String str) {
      return str;
   }

   private static final scala.collection.immutable.Map array$1(final Iterable arr, final String path$1, final Formats formats$2) {
      int var3 = arr.size();
      scala.collection.immutable.Map var10000;
      switch (var3) {
         case 0:
            var10000 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(path$1), "[]")})));
            break;
         default:
            var10000 = (scala.collection.immutable.Map)((Tuple2)arr.foldLeft(new Tuple2(scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), BoxesRunTime.boxToInteger(0)), (tuple, value) -> new Tuple2(((MapOps)tuple._1()).$plus$plus(flatten0$1((new StringBuilder(2)).append(path$1).append("[").append(tuple._2$mcI$sp()).append("]").toString(), value, formats$2)), BoxesRunTime.boxToInteger(tuple._2$mcI$sp() + 1))))._1();
      }

      return var10000;
   }

   private static final scala.collection.immutable.Map flatten0$1(final String path, final JValue json, final Formats formats$2) {
      boolean var4;
      if (org.json4s.JNothing..MODULE$.equals(json)) {
         var4 = true;
      } else if (org.json4s.JNull..MODULE$.equals(json)) {
         var4 = true;
      } else {
         var4 = false;
      }

      scala.collection.immutable.Map var3;
      if (var4) {
         var3 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      } else if (json instanceof JString) {
         JString var6 = (JString)json;
         String s = var6.s();
         var3 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(path), (new StringBuilder(2)).append("\"").append(org.json4s.ParserUtil..MODULE$.quote(s, formats$2.alwaysEscapeUnicode())).append("\"").toString())})));
      } else if (json instanceof JDouble) {
         JDouble var8 = (JDouble)json;
         double num = var8.num();
         var3 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(path), Double.toString(num))})));
      } else if (json instanceof JDecimal) {
         JDecimal var11 = (JDecimal)json;
         BigDecimal num = var11.num();
         var3 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(path), num.toString())})));
      } else if (json instanceof JLong) {
         JLong var13 = (JLong)json;
         long num = var13.num();
         var3 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(path), Long.toString(num))})));
      } else if (json instanceof JInt) {
         JInt var16 = (JInt)json;
         BigInt num = var16.num();
         var3 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(path), num.toString())})));
      } else if (json instanceof JBool) {
         JBool var18 = (JBool)json;
         boolean value = var18.value();
         var3 = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(path), Boolean.toString(value))})));
      } else if (json instanceof JObject) {
         JObject var20 = (JObject)json;
         List obj = var20.obj();
         var3 = (scala.collection.immutable.Map)obj.foldLeft(scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), (x0$1, x1$1) -> {
            Tuple2 var5 = new Tuple2(x0$1, x1$1);
            if (var5 != null) {
               scala.collection.immutable.Map map = (scala.collection.immutable.Map)var5._1();
               Tuple2 var7 = (Tuple2)var5._2();
               if (var7 != null) {
                  String name = (String)var7._1();
                  JValue value = (JValue)var7._2();
                  scala.collection.immutable.Map var4 = (scala.collection.immutable.Map)map.$plus$plus(flatten0$1((new StringBuilder(1)).append(path).append(".").append(escapePath$1(name)).toString(), value, formats$2));
                  return var4;
               }
            }

            throw new MatchError(var5);
         });
      } else if (json instanceof JArray) {
         JArray var22 = (JArray)json;
         List arr = var22.arr();
         var3 = array$1(arr, path, formats$2);
      } else {
         if (!(json instanceof JSet)) {
            throw new MatchError(json);
         }

         JSet var24 = (JSet)json;
         Set s = var24.set();
         var3 = array$1(s, path, formats$2);
      }

      return var3;
   }

   private static final JValue extractValue$1(final String value, final boolean useBigIntForLong$1, final boolean useBigDecimalForDouble$1) {
      String var4 = value.toLowerCase();
      Object var3;
      switch (var4 == null ? 0 : var4.hashCode()) {
         case 0:
            if ("".equals(var4)) {
               var3 = org.json4s.JNothing..MODULE$;
               return (JValue)var3;
            }
            break;
         case 2914:
            if ("[]".equals(var4)) {
               var3 = new JArray(scala.package..MODULE$.Nil());
               return (JValue)var3;
            }
            break;
         case 3392903:
            if ("null".equals(var4)) {
               var3 = org.json4s.JNull..MODULE$;
               return (JValue)var3;
            }
            break;
         case 3569038:
            if ("true".equals(var4)) {
               var3 = org.json4s.JBool..MODULE$.True();
               return (JValue)var3;
            }
            break;
         case 97196323:
            if ("false".equals(var4)) {
               var3 = org.json4s.JBool..MODULE$.False();
               return (JValue)var3;
            }
      }

      var3 = scala.runtime.RichChar..MODULE$.isDigit$extension(scala.Predef..MODULE$.charWrapper(value.charAt(0))) ? (value.indexOf(46) == -1 ? (useBigIntForLong$1 ? new JInt(scala.package..MODULE$.BigInt().apply(value)) : new JLong(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(value)))) : (!useBigDecimalForDouble$1 ? new JDouble(org.json4s.ParserUtil..MODULE$.parseDouble(value)) : new JDecimal(scala.package..MODULE$.BigDecimal().apply(value)))) : new JString(org.json4s.ParserUtil..MODULE$.unquote(value.substring(1)));
      return (JValue)var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$unflatten$1(final Regex ArrayProp$1, final String prefix$1, final Regex ArrayElem$1, final Regex OtherProp$1, final Tuple2 t) {
      boolean var5;
      label88: {
         String var6 = (String)t._1();
         if (var6 != null) {
            Option var7 = ArrayProp$1.unapplySeq(var6);
            if (!var7.isEmpty() && var7.get() != null && ((List)var7.get()).lengthCompare(3) == 0) {
               String p = (String)((LinearSeqOps)var7.get()).apply(0);
               if (p == null) {
                  if (prefix$1 == null) {
                     break label88;
                  }
               } else if (p.equals(prefix$1)) {
                  break label88;
               }
            }
         }

         label89: {
            if (var6 != null) {
               Option var10 = ArrayElem$1.unapplySeq(var6);
               if (!var10.isEmpty() && var10.get() != null && ((List)var10.get()).lengthCompare(2) == 0) {
                  String p = (String)((LinearSeqOps)var10.get()).apply(0);
                  if (p == null) {
                     if (prefix$1 == null) {
                        break label89;
                     }
                  } else if (p.equals(prefix$1)) {
                     break label89;
                  }
               }
            }

            label90: {
               if (var6 != null) {
                  Option var13 = OtherProp$1.unapplySeq(var6);
                  if (!var13.isEmpty() && var13.get() != null && ((List)var13.get()).lengthCompare(2) == 0) {
                     String p = (String)((LinearSeqOps)var13.get()).apply(0);
                     if (p == null) {
                        if (prefix$1 == null) {
                           break label90;
                        }
                     } else if (p.equals(prefix$1)) {
                        break label90;
                     }
                  }
               }

               var5 = false;
               return var5;
            }

            var5 = true;
            return var5;
         }

         var5 = true;
         return var5;
      }

      var5 = true;
      return var5;
   }

   private static final scala.collection.immutable.Map submap$1(final String prefix, final scala.collection.immutable.Map map$1, final Regex ArrayProp$1, final Regex ArrayElem$1, final Regex OtherProp$1) {
      return (scala.collection.immutable.Map)map$1.withFilter((t) -> BoxesRunTime.boxToBoolean($anonfun$unflatten$1(ArrayProp$1, prefix, ArrayElem$1, OtherProp$1, t))).map((t) -> new Tuple2(((String)t._1()).substring(prefix.length()), t._2()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$unflatten$4(final String x$5, final String x$6) {
      return scala.collection.StringOps..MODULE$.$less$extension(scala.Predef..MODULE$.augmentString(x$5), x$6);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$extractDetectingNonTerminal$1(final Formats formats$4, final Tuple2 x$9) {
      boolean var3;
      label23: {
         Object var10000 = x$9._1();
         String var2 = formats$4.typeHints().typeHintFieldName();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   private static final PartialFunction richCustom$lzycompute$2(final LazyRef richCustom$lzy$2, final ScalaType target$1, final JValue json$2, final Formats formats$5) {
      synchronized(richCustom$lzy$2){}

      PartialFunction var5;
      try {
         var5 = richCustom$lzy$2.initialized() ? (PartialFunction)richCustom$lzy$2.value() : (PartialFunction)richCustom$lzy$2.initialize(Formats$.MODULE$.customRichDeserializer(new Tuple2(target$1, json$2), formats$5));
      } catch (Throwable var7) {
         throw var7;
      }

      return var5;
   }

   private static final PartialFunction richCustom$2(final LazyRef richCustom$lzy$2, final ScalaType target$1, final JValue json$2, final Formats formats$5) {
      return richCustom$lzy$2.initialized() ? (PartialFunction)richCustom$lzy$2.value() : richCustom$lzycompute$2(richCustom$lzy$2, target$1, json$2, formats$5);
   }

   private Extraction$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
