package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public class ClosureSerializer extends Serializer {
   private static Method readResolve;
   private static Class serializedLambda = SerializedLambda.class;

   public void write(Kryo kryo, Output output, Object object) {
      try {
         Class type = object.getClass();
         Method writeReplace = type.getDeclaredMethod("writeReplace");
         writeReplace.setAccessible(true);
         Object replacement = writeReplace.invoke(object);
         if (serializedLambda.isInstance(replacement)) {
            kryo.writeObject(output, replacement);
         } else {
            throw new RuntimeException("Could not serialize lambda");
         }
      } catch (Exception e) {
         throw new RuntimeException("Could not serialize lambda", e);
      }
   }

   public Object read(Kryo kryo, Input input, Class type) {
      try {
         Object object = kryo.readObject(input, serializedLambda);
         return readResolve.invoke(object);
      } catch (Exception e) {
         throw new RuntimeException("Could not serialize lambda", e);
      }
   }

   public Object copy(Kryo kryo, Object original) {
      try {
         Class type = original.getClass();
         Method writeReplace = type.getDeclaredMethod("writeReplace");
         writeReplace.setAccessible(true);
         Object replacement = writeReplace.invoke(original);
         if (serializedLambda.isInstance(replacement)) {
            return readResolve.invoke(replacement);
         } else {
            throw new RuntimeException("Could not serialize lambda");
         }
      } catch (Exception e) {
         throw new RuntimeException("Could not serialize lambda", e);
      }
   }

   static {
      try {
         readResolve = serializedLambda.getDeclaredMethod("readResolve");
         readResolve.setAccessible(true);
      } catch (Exception e) {
         throw new RuntimeException("Could not obtain SerializedLambda or its methods via reflection", e);
      }
   }

   public static class Closure {
   }
}
