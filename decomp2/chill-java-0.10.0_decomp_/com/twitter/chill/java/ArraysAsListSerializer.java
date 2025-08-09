package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraysAsListSerializer extends Serializer {
   private static final Map primitives = new HashMap(8, 1.0F);
   private Field _arrayField;

   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Arrays.asList("").getClass(), new ArraysAsListSerializer());
   }

   public ArraysAsListSerializer() {
      try {
         this._arrayField = Class.forName("java.util.Arrays$ArrayList").getDeclaredField("a");
         this._arrayField.setAccessible(true);
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   public List read(Kryo var1, Input var2, Class var3) {
      int var4 = var2.readInt(true);
      Class var5 = var1.readClass(var2).getType();

      try {
         Object var6 = Array.newInstance(getBoxedClass(var5), var4);

         for(int var7 = 0; var7 < var4; ++var7) {
            Array.set(var6, var7, var1.readClassAndObject(var2));
         }

         return Arrays.asList(var6);
      } catch (Exception var8) {
         throw new RuntimeException(var8);
      }
   }

   public void write(Kryo var1, Output var2, List var3) {
      try {
         Object[] var4 = this._arrayField.get(var3);
         var2.writeInt(var4.length, true);
         Class var5 = var4.getClass().getComponentType();
         var1.writeClass(var2, var5);

         for(Object var9 : var4) {
            var1.writeClassAndObject(var2, var9);
         }

      } catch (RuntimeException var10) {
         throw var10;
      } catch (Exception var11) {
         throw new RuntimeException(var11);
      }
   }

   private static Class getBoxedClass(Class var0) {
      if (var0.isPrimitive()) {
         Class var1;
         return (var1 = (Class)primitives.get(var0)) != null ? var1 : var0;
      } else {
         return var0;
      }
   }

   static {
      primitives.put(Byte.TYPE, Byte.class);
      primitives.put(Short.TYPE, Short.class);
      primitives.put(Integer.TYPE, Integer.class);
      primitives.put(Long.TYPE, Long.class);
      primitives.put(Character.TYPE, Character.class);
      primitives.put(Float.TYPE, Float.class);
      primitives.put(Double.TYPE, Double.class);
      primitives.put(Boolean.TYPE, Boolean.class);
   }
}
