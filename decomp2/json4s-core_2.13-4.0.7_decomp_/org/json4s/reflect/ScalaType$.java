package org.json4s.reflect;

import java.lang.invoke.SerializedLambda;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import org.json4s.JArray;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Symbol;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.Manifest;

public final class ScalaType$ {
   public static final ScalaType$ MODULE$ = new ScalaType$();
   private static final Memo org$json4s$reflect$ScalaType$$types = new Memo();
   private static final String org$json4s$reflect$ScalaType$$singletonFieldName = "MODULE$";
   private static final ScalaType org$json4s$reflect$ScalaType$$IntType;
   private static final ScalaType org$json4s$reflect$ScalaType$$NumberType;
   private static final ScalaType org$json4s$reflect$ScalaType$$LongType;
   private static final ScalaType org$json4s$reflect$ScalaType$$ByteType;
   private static final ScalaType org$json4s$reflect$ScalaType$$ShortType;
   private static final ScalaType org$json4s$reflect$ScalaType$$BooleanType;
   private static final ScalaType org$json4s$reflect$ScalaType$$FloatType;
   private static final ScalaType org$json4s$reflect$ScalaType$$DoubleType;
   private static final ScalaType org$json4s$reflect$ScalaType$$StringType;
   private static final ScalaType org$json4s$reflect$ScalaType$$SymbolType;
   private static final ScalaType org$json4s$reflect$ScalaType$$BigDecimalType;
   private static final ScalaType org$json4s$reflect$ScalaType$$BigIntType;
   private static final ScalaType org$json4s$reflect$ScalaType$$JValueType;
   private static final ScalaType org$json4s$reflect$ScalaType$$JObjectType;
   private static final ScalaType org$json4s$reflect$ScalaType$$JArrayType;
   private static final ScalaType org$json4s$reflect$ScalaType$$DateType;
   private static final ScalaType org$json4s$reflect$ScalaType$$TimestampType;
   private static final ScalaType ListObject;
   private static final ScalaType Object;
   private static final ScalaType MapStringObject;

   static {
      org$json4s$reflect$ScalaType$$IntType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().Int());
      org$json4s$reflect$ScalaType$$NumberType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(Number.class));
      org$json4s$reflect$ScalaType$$LongType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().Long());
      org$json4s$reflect$ScalaType$$ByteType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().Byte());
      org$json4s$reflect$ScalaType$$ShortType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().Short());
      org$json4s$reflect$ScalaType$$BooleanType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().Boolean());
      org$json4s$reflect$ScalaType$$FloatType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().Float());
      org$json4s$reflect$ScalaType$$DoubleType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().Double());
      org$json4s$reflect$ScalaType$$StringType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(String.class));
      org$json4s$reflect$ScalaType$$SymbolType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(Symbol.class));
      org$json4s$reflect$ScalaType$$BigDecimalType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(BigDecimal.class));
      org$json4s$reflect$ScalaType$$BigIntType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(BigInt.class));
      org$json4s$reflect$ScalaType$$JValueType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(JValue.class));
      org$json4s$reflect$ScalaType$$JObjectType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(JObject.class));
      org$json4s$reflect$ScalaType$$JArrayType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(JArray.class));
      org$json4s$reflect$ScalaType$$DateType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(Date.class));
      org$json4s$reflect$ScalaType$$TimestampType = new ScalaType.PrimitiveScalaType(.MODULE$.Manifest().classType(Timestamp.class));
      ListObject = new ScalaType(.MODULE$.Manifest().classType(List.class, .MODULE$.Manifest().Object(), scala.collection.immutable.Nil..MODULE$));
      Object = new ScalaType(.MODULE$.Manifest().Object());
      MapStringObject = new ScalaType(.MODULE$.Manifest().classType(Map.class, .MODULE$.Manifest().classType(String.class), scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Manifest[]{.MODULE$.Manifest().Object()}))));
   }

   public Memo org$json4s$reflect$ScalaType$$types() {
      return org$json4s$reflect$ScalaType$$types;
   }

   public String org$json4s$reflect$ScalaType$$singletonFieldName() {
      return org$json4s$reflect$ScalaType$$singletonFieldName;
   }

   public ScalaType apply(final Manifest mf) {
      ScalaType var55;
      label364: {
         Class var10000 = mf.runtimeClass();
         Class var2 = Integer.TYPE;
         if (var10000 == null) {
            if (var2 == null) {
               break label364;
            }
         } else if (var10000.equals(var2)) {
            break label364;
         }

         var10000 = mf.runtimeClass();
         Class var3 = Integer.class;
         if (var10000 == null) {
            if (var3 == null) {
               break label364;
            }
         } else if (var10000.equals(var3)) {
            break label364;
         }

         label365: {
            var10000 = mf.runtimeClass();
            Class var4 = Long.TYPE;
            if (var10000 == null) {
               if (var4 == null) {
                  break label365;
               }
            } else if (var10000.equals(var4)) {
               break label365;
            }

            var10000 = mf.runtimeClass();
            Class var5 = Long.class;
            if (var10000 == null) {
               if (var5 == null) {
                  break label365;
               }
            } else if (var10000.equals(var5)) {
               break label365;
            }

            label366: {
               var10000 = mf.runtimeClass();
               Class var6 = Byte.TYPE;
               if (var10000 == null) {
                  if (var6 == null) {
                     break label366;
                  }
               } else if (var10000.equals(var6)) {
                  break label366;
               }

               var10000 = mf.runtimeClass();
               Class var7 = Byte.class;
               if (var10000 == null) {
                  if (var7 == null) {
                     break label366;
                  }
               } else if (var10000.equals(var7)) {
                  break label366;
               }

               label367: {
                  var10000 = mf.runtimeClass();
                  Class var8 = Short.TYPE;
                  if (var10000 == null) {
                     if (var8 == null) {
                        break label367;
                     }
                  } else if (var10000.equals(var8)) {
                     break label367;
                  }

                  var10000 = mf.runtimeClass();
                  Class var9 = Short.class;
                  if (var10000 == null) {
                     if (var9 == null) {
                        break label367;
                     }
                  } else if (var10000.equals(var9)) {
                     break label367;
                  }

                  label368: {
                     var10000 = mf.runtimeClass();
                     Class var10 = Float.TYPE;
                     if (var10000 == null) {
                        if (var10 == null) {
                           break label368;
                        }
                     } else if (var10000.equals(var10)) {
                        break label368;
                     }

                     var10000 = mf.runtimeClass();
                     Class var11 = Float.class;
                     if (var10000 == null) {
                        if (var11 == null) {
                           break label368;
                        }
                     } else if (var10000.equals(var11)) {
                        break label368;
                     }

                     label369: {
                        var10000 = mf.runtimeClass();
                        Class var12 = Double.TYPE;
                        if (var10000 == null) {
                           if (var12 == null) {
                              break label369;
                           }
                        } else if (var10000.equals(var12)) {
                           break label369;
                        }

                        var10000 = mf.runtimeClass();
                        Class var13 = Double.class;
                        if (var10000 == null) {
                           if (var13 == null) {
                              break label369;
                           }
                        } else if (var10000.equals(var13)) {
                           break label369;
                        }

                        label370: {
                           var10000 = mf.runtimeClass();
                           Class var14 = BigInt.class;
                           if (var10000 == null) {
                              if (var14 == null) {
                                 break label370;
                              }
                           } else if (var10000.equals(var14)) {
                              break label370;
                           }

                           var10000 = mf.runtimeClass();
                           Class var15 = BigInteger.class;
                           if (var10000 == null) {
                              if (var15 == null) {
                                 break label370;
                              }
                           } else if (var10000.equals(var15)) {
                              break label370;
                           }

                           label371: {
                              var10000 = mf.runtimeClass();
                              Class var16 = BigDecimal.class;
                              if (var10000 == null) {
                                 if (var16 == null) {
                                    break label371;
                                 }
                              } else if (var10000.equals(var16)) {
                                 break label371;
                              }

                              var10000 = mf.runtimeClass();
                              Class var17 = java.math.BigDecimal.class;
                              if (var10000 == null) {
                                 if (var17 == null) {
                                    break label371;
                                 }
                              } else if (var10000.equals(var17)) {
                                 break label371;
                              }

                              label372: {
                                 var10000 = mf.runtimeClass();
                                 Class var18 = Boolean.TYPE;
                                 if (var10000 == null) {
                                    if (var18 == null) {
                                       break label372;
                                    }
                                 } else if (var10000.equals(var18)) {
                                    break label372;
                                 }

                                 var10000 = mf.runtimeClass();
                                 Class var19 = Boolean.class;
                                 if (var10000 == null) {
                                    if (var19 == null) {
                                       break label372;
                                    }
                                 } else if (var10000.equals(var19)) {
                                    break label372;
                                 }

                                 label373: {
                                    var10000 = mf.runtimeClass();
                                    Class var20 = String.class;
                                    if (var10000 == null) {
                                       if (var20 == null) {
                                          break label373;
                                       }
                                    } else if (var10000.equals(var20)) {
                                       break label373;
                                    }

                                    var10000 = mf.runtimeClass();
                                    Class var21 = String.class;
                                    if (var10000 == null) {
                                       if (var21 == null) {
                                          break label373;
                                       }
                                    } else if (var10000.equals(var21)) {
                                       break label373;
                                    }

                                    label374: {
                                       var10000 = mf.runtimeClass();
                                       Class var22 = Date.class;
                                       if (var10000 == null) {
                                          if (var22 == null) {
                                             break label374;
                                          }
                                       } else if (var10000.equals(var22)) {
                                          break label374;
                                       }

                                       label375: {
                                          var10000 = mf.runtimeClass();
                                          Class var23 = Timestamp.class;
                                          if (var10000 == null) {
                                             if (var23 == null) {
                                                break label375;
                                             }
                                          } else if (var10000.equals(var23)) {
                                             break label375;
                                          }

                                          label376: {
                                             var10000 = mf.runtimeClass();
                                             Class var24 = Symbol.class;
                                             if (var10000 == null) {
                                                if (var24 == null) {
                                                   break label376;
                                                }
                                             } else if (var10000.equals(var24)) {
                                                break label376;
                                             }

                                             label377: {
                                                var10000 = mf.runtimeClass();
                                                Class var25 = Number.class;
                                                if (var10000 == null) {
                                                   if (var25 == null) {
                                                      break label377;
                                                   }
                                                } else if (var10000.equals(var25)) {
                                                   break label377;
                                                }

                                                label378: {
                                                   var10000 = mf.runtimeClass();
                                                   Class var26 = JObject.class;
                                                   if (var10000 == null) {
                                                      if (var26 == null) {
                                                         break label378;
                                                      }
                                                   } else if (var10000.equals(var26)) {
                                                      break label378;
                                                   }

                                                   label379: {
                                                      var10000 = mf.runtimeClass();
                                                      Class var27 = JArray.class;
                                                      if (var10000 == null) {
                                                         if (var27 == null) {
                                                            break label379;
                                                         }
                                                      } else if (var10000.equals(var27)) {
                                                         break label379;
                                                      }

                                                      label380: {
                                                         var10000 = mf.runtimeClass();
                                                         Class var28 = JValue.class;
                                                         if (var10000 == null) {
                                                            if (var28 == null) {
                                                               break label380;
                                                            }
                                                         } else if (var10000.equals(var28)) {
                                                            break label380;
                                                         }

                                                         var55 = mf.typeArguments().isEmpty() ? (ScalaType)this.org$json4s$reflect$ScalaType$$types().apply(mf, (x$1) -> new ScalaType(x$1)) : new ScalaType(mf);
                                                         return var55;
                                                      }

                                                      var55 = this.org$json4s$reflect$ScalaType$$JValueType();
                                                      return var55;
                                                   }

                                                   var55 = this.org$json4s$reflect$ScalaType$$JArrayType();
                                                   return var55;
                                                }

                                                var55 = this.org$json4s$reflect$ScalaType$$JObjectType();
                                                return var55;
                                             }

                                             var55 = this.org$json4s$reflect$ScalaType$$NumberType();
                                             return var55;
                                          }

                                          var55 = this.org$json4s$reflect$ScalaType$$SymbolType();
                                          return var55;
                                       }

                                       var55 = this.org$json4s$reflect$ScalaType$$TimestampType();
                                       return var55;
                                    }

                                    var55 = this.org$json4s$reflect$ScalaType$$DateType();
                                    return var55;
                                 }

                                 var55 = this.org$json4s$reflect$ScalaType$$StringType();
                                 return var55;
                              }

                              var55 = this.org$json4s$reflect$ScalaType$$BooleanType();
                              return var55;
                           }

                           var55 = this.org$json4s$reflect$ScalaType$$BigDecimalType();
                           return var55;
                        }

                        var55 = this.org$json4s$reflect$ScalaType$$BigIntType();
                        return var55;
                     }

                     var55 = this.org$json4s$reflect$ScalaType$$DoubleType();
                     return var55;
                  }

                  var55 = this.org$json4s$reflect$ScalaType$$FloatType();
                  return var55;
               }

               var55 = this.org$json4s$reflect$ScalaType$$ShortType();
               return var55;
            }

            var55 = this.org$json4s$reflect$ScalaType$$ByteType();
            return var55;
         }

         var55 = this.org$json4s$reflect$ScalaType$$LongType();
         return var55;
      }

      var55 = this.org$json4s$reflect$ScalaType$$IntType();
      return var55;
   }

   public ScalaType apply(final Class erasure, final Seq typeArgs) {
      Manifest mf = ManifestFactory$.MODULE$.manifestOf(erasure, (Seq)typeArgs.map((x$2) -> x$2.manifest()));
      return this.apply(mf);
   }

   public ScalaType apply(final TypeInfo target) {
      ScalaType var2;
      if (target instanceof SourceType) {
         var2 = ((SourceType)target).scalaType();
      } else {
         List tArgs = (List)target.parameterizedType().map((x$3) -> .MODULE$.wrapRefArray((Object[])x$3.getActualTypeArguments()).toList().map((x$4) -> Reflector$.MODULE$.scalaTypeOf(x$4))).getOrElse(() -> scala.package..MODULE$.Nil());
         var2 = this.apply(target.clazz(), tArgs);
      }

      return var2;
   }

   public Seq apply$default$2() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public ScalaType org$json4s$reflect$ScalaType$$IntType() {
      return org$json4s$reflect$ScalaType$$IntType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$NumberType() {
      return org$json4s$reflect$ScalaType$$NumberType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$LongType() {
      return org$json4s$reflect$ScalaType$$LongType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$ByteType() {
      return org$json4s$reflect$ScalaType$$ByteType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$ShortType() {
      return org$json4s$reflect$ScalaType$$ShortType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$BooleanType() {
      return org$json4s$reflect$ScalaType$$BooleanType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$FloatType() {
      return org$json4s$reflect$ScalaType$$FloatType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$DoubleType() {
      return org$json4s$reflect$ScalaType$$DoubleType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$StringType() {
      return org$json4s$reflect$ScalaType$$StringType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$SymbolType() {
      return org$json4s$reflect$ScalaType$$SymbolType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$BigDecimalType() {
      return org$json4s$reflect$ScalaType$$BigDecimalType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$BigIntType() {
      return org$json4s$reflect$ScalaType$$BigIntType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$JValueType() {
      return org$json4s$reflect$ScalaType$$JValueType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$JObjectType() {
      return org$json4s$reflect$ScalaType$$JObjectType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$JArrayType() {
      return org$json4s$reflect$ScalaType$$JArrayType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$DateType() {
      return org$json4s$reflect$ScalaType$$DateType;
   }

   public ScalaType org$json4s$reflect$ScalaType$$TimestampType() {
      return org$json4s$reflect$ScalaType$$TimestampType;
   }

   public ScalaType ListObject() {
      return ListObject;
   }

   public ScalaType Object() {
      return Object;
   }

   public ScalaType MapStringObject() {
      return MapStringObject;
   }

   private ScalaType$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
