package breeze.util;

import java.io.Serializable;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class ArrayBuilder$ implements Serializable {
   public static final ArrayBuilder$ MODULE$ = new ArrayBuilder$();

   public ArrayBuilder make(final ClassTag evidence$1) {
      Object var2;
      label160: {
         ClassTag tag = (ClassTag).MODULE$.implicitly(evidence$1);
         Class var4 = tag.runtimeClass();
         Class var10000 = Byte.TYPE;
         if (var10000 == null) {
            if (var4 == null) {
               break label160;
            }
         } else if (var10000.equals(var4)) {
            break label160;
         }

         label161: {
            var10000 = Short.TYPE;
            if (var10000 == null) {
               if (var4 == null) {
                  break label161;
               }
            } else if (var10000.equals(var4)) {
               break label161;
            }

            label162: {
               var10000 = Character.TYPE;
               if (var10000 == null) {
                  if (var4 == null) {
                     break label162;
                  }
               } else if (var10000.equals(var4)) {
                  break label162;
               }

               label163: {
                  var10000 = Integer.TYPE;
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label163;
                     }
                  } else if (var10000.equals(var4)) {
                     break label163;
                  }

                  label164: {
                     var10000 = Long.TYPE;
                     if (var10000 == null) {
                        if (var4 == null) {
                           break label164;
                        }
                     } else if (var10000.equals(var4)) {
                        break label164;
                     }

                     label165: {
                        var10000 = Float.TYPE;
                        if (var10000 == null) {
                           if (var4 == null) {
                              break label165;
                           }
                        } else if (var10000.equals(var4)) {
                           break label165;
                        }

                        label166: {
                           var10000 = Double.TYPE;
                           if (var10000 == null) {
                              if (var4 == null) {
                                 break label166;
                              }
                           } else if (var10000.equals(var4)) {
                              break label166;
                           }

                           label167: {
                              var10000 = Boolean.TYPE;
                              if (var10000 == null) {
                                 if (var4 == null) {
                                    break label167;
                                 }
                              } else if (var10000.equals(var4)) {
                                 break label167;
                              }

                              label168: {
                                 var10000 = Void.TYPE;
                                 if (var10000 == null) {
                                    if (var4 == null) {
                                       break label168;
                                    }
                                 } else if (var10000.equals(var4)) {
                                    break label168;
                                 }

                                 var2 = new ArrayBuilder.ofRef(tag);
                                 return (ArrayBuilder)var2;
                              }

                              var2 = new ArrayBuilder.ofUnit();
                              return (ArrayBuilder)var2;
                           }

                           var2 = new ArrayBuilder.ofBoolean();
                           return (ArrayBuilder)var2;
                        }

                        var2 = new ArrayBuilder.ofDouble();
                        return (ArrayBuilder)var2;
                     }

                     var2 = new ArrayBuilder.ofFloat();
                     return (ArrayBuilder)var2;
                  }

                  var2 = new ArrayBuilder.ofLong();
                  return (ArrayBuilder)var2;
               }

               var2 = new ArrayBuilder.ofInt();
               return (ArrayBuilder)var2;
            }

            var2 = new ArrayBuilder.ofChar();
            return (ArrayBuilder)var2;
         }

         var2 = new ArrayBuilder.ofShort();
         return (ArrayBuilder)var2;
      }

      var2 = new ArrayBuilder.ofByte();
      return (ArrayBuilder)var2;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ArrayBuilder$.class);
   }

   private ArrayBuilder$() {
   }
}
