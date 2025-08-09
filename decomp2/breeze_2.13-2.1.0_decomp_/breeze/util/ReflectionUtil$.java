package breeze.util;

import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.reflect.ClassTag;

public final class ReflectionUtil$ {
   public static final ReflectionUtil$ MODULE$ = new ReflectionUtil$();

   public Class boxedFromPrimitive(final Class c) {
      Class var10000;
      label150: {
         .MODULE$.require(c.isPrimitive(), () -> "can't get boxed representation of non-primitive type");
         Class var2 = Float.TYPE;
         if (c == null) {
            if (var2 == null) {
               break label150;
            }
         } else if (c.equals(var2)) {
            break label150;
         }

         label151: {
            Class var3 = Long.TYPE;
            if (c == null) {
               if (var3 == null) {
                  break label151;
               }
            } else if (c.equals(var3)) {
               break label151;
            }

            label152: {
               Class var4 = Double.TYPE;
               if (c == null) {
                  if (var4 == null) {
                     break label152;
                  }
               } else if (c.equals(var4)) {
                  break label152;
               }

               label153: {
                  Class var5 = Integer.TYPE;
                  if (c == null) {
                     if (var5 == null) {
                        break label153;
                     }
                  } else if (c.equals(var5)) {
                     break label153;
                  }

                  label154: {
                     Class var6 = Byte.TYPE;
                     if (c == null) {
                        if (var6 == null) {
                           break label154;
                        }
                     } else if (c.equals(var6)) {
                        break label154;
                     }

                     label155: {
                        Class var7 = Short.TYPE;
                        if (c == null) {
                           if (var7 == null) {
                              break label155;
                           }
                        } else if (c.equals(var7)) {
                           break label155;
                        }

                        label156: {
                           Class var8 = Character.TYPE;
                           if (c == null) {
                              if (var8 == null) {
                                 break label156;
                              }
                           } else if (c.equals(var8)) {
                              break label156;
                           }

                           label157: {
                              Class var9 = Boolean.TYPE;
                              if (c == null) {
                                 if (var9 == null) {
                                    break label157;
                                 }
                              } else if (c.equals(var9)) {
                                 break label157;
                              }

                              Class var10 = Void.TYPE;
                              if (c == null) {
                                 if (var10 != null) {
                                    throw scala.sys.package..MODULE$.error("Shouldn't be here...");
                                 }
                              } else if (!c.equals(var10)) {
                                 throw scala.sys.package..MODULE$.error("Shouldn't be here...");
                              }

                              var10000 = Void.class;
                              return var10000;
                           }

                           var10000 = Boolean.class;
                           return var10000;
                        }

                        var10000 = Character.class;
                        return var10000;
                     }

                     var10000 = Short.class;
                     return var10000;
                  }

                  var10000 = Byte.class;
                  return var10000;
               }

               var10000 = Integer.class;
               return var10000;
            }

            var10000 = Double.class;
            return var10000;
         }

         var10000 = Long.class;
         return var10000;
      }

      var10000 = Float.class;
      return var10000;
   }

   public ClassTag elemClassTagFromArray(final Object array) {
      return scala.reflect.ClassTag..MODULE$.apply(array.getClass().getComponentType());
   }

   private ReflectionUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
