package org.json4s.reflect;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import scala.MatchError;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.Manifest;

public final class ManifestFactory$ {
   public static final ManifestFactory$ MODULE$ = new ManifestFactory$();

   public Manifest manifestOf(final Type t) {
      while(true) {
         Manifest var3;
         if (t instanceof ParameterizedType) {
            ParameterizedType var5 = (ParameterizedType)t;
            Class clazz = this.manifestOf(var5.getRawType()).runtimeClass();
            Manifest[] typeArgs = (Manifest[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])var5.getActualTypeArguments()), (tx) -> MODULE$.manifestOf(tx), scala.reflect.ClassTag..MODULE$.apply(Manifest.class));
            var3 = var5.getOwnerType() == null ? this.manifestOf(clazz, scala.Predef..MODULE$.copyArrayToImmutableIndexedSeq(typeArgs)) : scala.reflect.Manifest..MODULE$.classType(this.manifestOf(var5.getOwnerType()), clazz, scala.Predef..MODULE$.copyArrayToImmutableIndexedSeq(typeArgs));
         } else if (t instanceof GenericArrayType) {
            GenericArrayType var8 = (GenericArrayType)t;
            Manifest componentManifest = this.manifestOf(var8.getGenericComponentType());
            Manifest arrayManifest = componentManifest.arrayManifest();
            var3 = scala.reflect.Manifest..MODULE$.classType(arrayManifest.runtimeClass(), componentManifest, scala.collection.immutable.Nil..MODULE$);
         } else {
            if (t instanceof WildcardType) {
               WildcardType var11 = (WildcardType)t;
               Type[] upper = var11.getUpperBounds();
               if (upper != null && upper.length > 0) {
                  t = upper[0];
                  continue;
               }

               t = Object.class;
               continue;
            }

            if (t instanceof TypeVariable) {
               TypeVariable var13 = (TypeVariable)t;
               Type[] upper = var13.getBounds();
               if (upper != null && upper.length > 0) {
                  t = upper[0];
                  continue;
               }

               t = Object.class;
               continue;
            }

            if (!(t instanceof Class)) {
               throw new MatchError(t);
            }

            Class var15 = (Class)t;
            var3 = this.fromClass(var15);
         }

         return var3;
      }
   }

   public Manifest manifestOf(final Class erasure, final Seq typeArgs) {
      Manifest var10000;
      if (typeArgs.isEmpty()) {
         var10000 = this.fromClass(erasure);
      } else {
         label20: {
            label19: {
               String var5 = erasure.getName();
               String var4 = "scala.Array";
               if (var5 == null) {
                  if (var4 == null) {
                     break label19;
                  }
               } else if (var5.equals(var4)) {
                  break label19;
               }

               var6 = erasure;
               break label20;
            }

            var6 = ((Manifest)typeArgs.apply(0)).arrayManifest().runtimeClass();
         }

         Class normalizedErasure = var6;
         var10000 = scala.reflect.Manifest..MODULE$.classType(normalizedErasure, (Manifest)typeArgs.head(), (Seq)typeArgs.tail());
      }

      return var10000;
   }

   public Manifest manifestOf(final ScalaType st) {
      Seq typeArgs = (Seq)st.typeArgs().map((stx) -> MODULE$.manifestOf(stx));
      return this.manifestOf(st.erasure(), typeArgs);
   }

   private Manifest fromClass(final Class clazz) {
      Object var2;
      label160: {
         Class var10000 = Byte.TYPE;
         if (var10000 == null) {
            if (clazz == null) {
               break label160;
            }
         } else if (var10000.equals(clazz)) {
            break label160;
         }

         label161: {
            var10000 = Short.TYPE;
            if (var10000 == null) {
               if (clazz == null) {
                  break label161;
               }
            } else if (var10000.equals(clazz)) {
               break label161;
            }

            label162: {
               var10000 = Character.TYPE;
               if (var10000 == null) {
                  if (clazz == null) {
                     break label162;
                  }
               } else if (var10000.equals(clazz)) {
                  break label162;
               }

               label163: {
                  var10000 = Integer.TYPE;
                  if (var10000 == null) {
                     if (clazz == null) {
                        break label163;
                     }
                  } else if (var10000.equals(clazz)) {
                     break label163;
                  }

                  label164: {
                     var10000 = Long.TYPE;
                     if (var10000 == null) {
                        if (clazz == null) {
                           break label164;
                        }
                     } else if (var10000.equals(clazz)) {
                        break label164;
                     }

                     label165: {
                        var10000 = Float.TYPE;
                        if (var10000 == null) {
                           if (clazz == null) {
                              break label165;
                           }
                        } else if (var10000.equals(clazz)) {
                           break label165;
                        }

                        label166: {
                           var10000 = Double.TYPE;
                           if (var10000 == null) {
                              if (clazz == null) {
                                 break label166;
                              }
                           } else if (var10000.equals(clazz)) {
                              break label166;
                           }

                           label167: {
                              var10000 = Boolean.TYPE;
                              if (var10000 == null) {
                                 if (clazz == null) {
                                    break label167;
                                 }
                              } else if (var10000.equals(clazz)) {
                                 break label167;
                              }

                              label168: {
                                 var10000 = Void.TYPE;
                                 if (var10000 == null) {
                                    if (clazz == null) {
                                       break label168;
                                    }
                                 } else if (var10000.equals(clazz)) {
                                    break label168;
                                 }

                                 var2 = scala.reflect.Manifest..MODULE$.classType(clazz);
                                 return (Manifest)var2;
                              }

                              var2 = scala.reflect.Manifest..MODULE$.Unit();
                              return (Manifest)var2;
                           }

                           var2 = scala.reflect.Manifest..MODULE$.Boolean();
                           return (Manifest)var2;
                        }

                        var2 = scala.reflect.Manifest..MODULE$.Double();
                        return (Manifest)var2;
                     }

                     var2 = scala.reflect.Manifest..MODULE$.Float();
                     return (Manifest)var2;
                  }

                  var2 = scala.reflect.Manifest..MODULE$.Long();
                  return (Manifest)var2;
               }

               var2 = scala.reflect.Manifest..MODULE$.Int();
               return (Manifest)var2;
            }

            var2 = scala.reflect.Manifest..MODULE$.Char();
            return (Manifest)var2;
         }

         var2 = scala.reflect.Manifest..MODULE$.Short();
         return (Manifest)var2;
      }

      var2 = scala.reflect.Manifest..MODULE$.Byte();
      return (Manifest)var2;
   }

   private ManifestFactory$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
