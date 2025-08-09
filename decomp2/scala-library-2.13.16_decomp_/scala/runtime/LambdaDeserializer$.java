package scala.runtime;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import scala.Predef$;

public final class LambdaDeserializer$ {
   public static final LambdaDeserializer$ MODULE$ = new LambdaDeserializer$();

   public Object deserializeLambda(final MethodHandles.Lookup lookup, final Map cache, final Map targetMethodMap, final SerializedLambda serialized) {
      Object result = this.deserializeLambdaOrNull(lookup, cache, targetMethodMap, serialized);
      if (result == null) {
         throw new IllegalArgumentException("Illegal lambda deserialization");
      } else {
         return result;
      }
   }

   public Object deserializeLambdaOrNull(final MethodHandles.Lookup lookup, final Map cache, final Map targetMethodMap, final SerializedLambda serialized) {
      Predef$.MODULE$.assert(targetMethodMap != null);
      ClassLoader loader = lookup.lookupClass().getClassLoader();
      Class implClass = loader.loadClass(serialized.getImplClass().replaceAll("/", "."));
      String key = LambdaDeserialize$.MODULE$.nameAndDescriptorKey(serialized.getImplMethodName(), serialized.getImplMethodSignature());
      MethodHandle var10000;
      if (cache == null) {
         CallSite callSite = makeCallSite$1(loader, serialized, implClass, targetMethodMap, key, lookup);
         if (callSite == null) {
            return null;
         }

         var10000 = callSite.getTarget();
      } else {
         synchronized(cache){}

         MethodHandle var10;
         try {
            MethodHandle var11 = (MethodHandle)cache.get(key);
            if (var11 == null) {
               CallSite callSite = makeCallSite$1(loader, serialized, implClass, targetMethodMap, key, lookup);
               if (callSite == null) {
                  return null;
               }

               MethodHandle temp = callSite.getTarget();
               cache.put(key, temp);
               var10000 = temp;
            } else {
               var10000 = var11;
            }

            var10 = var10000;
         } catch (Throwable var20) {
            throw var20;
         }

         var10000 = var10;
      }

      MethodHandle factory = var10000;
      int tabulate_n = serialized.getCapturedArgCount();
      Object var24;
      if (tabulate_n <= 0) {
         var24 = new Object[0];
      } else {
         Object tabulate_array = new Object[tabulate_n];

         for(int tabulate_i = 0; tabulate_i < tabulate_n; ++tabulate_i) {
            Object array_update_value = serialized.getCapturedArg(tabulate_i);
            ((Object[])tabulate_array)[tabulate_i] = array_update_value;
            array_update_value = null;
         }

         var24 = tabulate_array;
      }

      Object var21 = null;
      Object[] captures = (Object[])var24;
      return factory.invokeWithArguments(captures);
   }

   private static final String slashDot$1(final String name) {
      return name.replaceAll("/", ".");
   }

   private static final MethodType parseDescriptor$1(final String s, final ClassLoader loader$1) {
      return MethodType.fromMethodDescriptorString(s, loader$1);
   }

   private static final CallSite makeCallSite$1(final ClassLoader loader$1, final SerializedLambda serialized$1, final Class implClass$1, final Map targetMethodMap$1, final String key$1, final MethodHandles.Lookup lookup$1) {
      String parseDescriptor$1_s = serialized$1.getFunctionalInterfaceMethodSignature();
      MethodType var10000 = MethodType.fromMethodDescriptorString(parseDescriptor$1_s, loader$1);
      Object var20 = null;
      MethodType funcInterfaceSignature = var10000;
      String parseDescriptor$1_s = serialized$1.getInstantiatedMethodType();
      var10000 = MethodType.fromMethodDescriptorString(parseDescriptor$1_s, loader$1);
      Object var21 = null;
      MethodType instantiated = var10000;
      Class functionalInterfaceClass = loader$1.loadClass(serialized$1.getFunctionalInterfaceClass().replaceAll("/", "."));
      String parseDescriptor$1_s = serialized$1.getImplMethodSignature();
      var10000 = MethodType.fromMethodDescriptorString(parseDescriptor$1_s, loader$1);
      Object var22 = null;
      MethodType implMethodSig = var10000;
      switch (serialized$1.getImplMethodKind()) {
         case 6:
         case 8:
            var10000 = implMethodSig;
            break;
         default:
            var10000 = implMethodSig.insertParameterTypes(0, new Class[]{implClass$1});
      }

      MethodType withReceiver = var10000;
      int lambdaArity = funcInterfaceSignature.parameterCount();
      int from = withReceiver.parameterCount() - lambdaArity;
      int to = withReceiver.parameterCount();
      MethodType invokedType = withReceiver.dropParameterTypes(from, to).changeReturnType(functionalInterfaceClass);
      if (targetMethodMap$1.containsKey(key$1)) {
         MethodHandle implMethod = (MethodHandle)targetMethodMap$1.get(key$1);
         byte flags = 1;
         return LambdaMetafactory.altMetafactory(lookup$1, serialized$1.getFunctionalInterfaceMethodName(), invokedType, funcInterfaceSignature, implMethod, instantiated, Integer.valueOf(flags));
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$deserializeLambdaOrNull$1(final SerializedLambda serialized$1, final int n) {
      return serialized$1.getCapturedArg(n);
   }

   private LambdaDeserializer$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$deserializeLambdaOrNull$1$adapted(final SerializedLambda serialized$1, final Object n) {
      return $anonfun$deserializeLambdaOrNull$1(serialized$1, BoxesRunTime.unboxToInt(n));
   }
}
