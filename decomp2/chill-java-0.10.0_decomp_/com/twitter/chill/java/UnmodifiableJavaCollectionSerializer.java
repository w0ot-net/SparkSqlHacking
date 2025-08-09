package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.reflect.Field;

abstract class UnmodifiableJavaCollectionSerializer extends Serializer {
   private final Field innerField;

   protected abstract Object newInstance(Object var1);

   protected abstract Field getInnerField() throws Exception;

   UnmodifiableJavaCollectionSerializer() {
      try {
         this.innerField = this.getInnerField();
         this.innerField.setAccessible(true);
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   public Object read(Kryo var1, Input var2, Class var3) {
      try {
         Object var4 = var1.readClassAndObject(var2);
         return this.newInstance(var4);
      } catch (Exception var5) {
         throw new RuntimeException(var5);
      }
   }

   public void write(Kryo var1, Output var2, Object var3) {
      try {
         Object var4 = this.innerField.get(var3);
         var1.writeClassAndObject(var2, var4);
      } catch (RuntimeException var5) {
         throw var5;
      } catch (Exception var6) {
         throw new RuntimeException(var6);
      }
   }
}
