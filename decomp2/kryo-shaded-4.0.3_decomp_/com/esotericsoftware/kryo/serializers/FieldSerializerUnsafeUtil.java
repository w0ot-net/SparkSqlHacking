package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.util.IntArray;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

interface FieldSerializerUnsafeUtil {
   void createUnsafeCacheFieldsAndRegions(List var1, List var2, int var3, IntArray var4);

   long getObjectFieldOffset(Field var1);

   public static class Factory {
      static Constructor fieldSerializerUnsafeUtilConstructor;

      static FieldSerializerUnsafeUtil getInstance(FieldSerializer serializer) {
         if (fieldSerializerUnsafeUtilConstructor != null) {
            try {
               return (FieldSerializerUnsafeUtil)fieldSerializerUnsafeUtilConstructor.newInstance(serializer);
            } catch (Exception var2) {
            }
         }

         return null;
      }

      static {
         try {
            fieldSerializerUnsafeUtilConstructor = FieldSerializer.class.getClassLoader().loadClass("com.esotericsoftware.kryo.serializers.FieldSerializerUnsafeUtilImpl").getConstructor(FieldSerializer.class);
         } catch (Throwable var1) {
         }

      }
   }
}
