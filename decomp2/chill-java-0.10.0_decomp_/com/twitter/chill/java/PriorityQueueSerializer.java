package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.PriorityQueue;

class PriorityQueueSerializer extends Serializer {
   private Field compField;

   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(PriorityQueue.class, new PriorityQueueSerializer());
   }

   public PriorityQueueSerializer() {
      try {
         this.compField = PriorityQueue.class.getDeclaredField("comparator");
         this.compField.setAccessible(true);
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   public Comparator getComparator(PriorityQueue var1) {
      try {
         return (Comparator)this.compField.get(var1);
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }

   public void write(Kryo var1, Output var2, PriorityQueue var3) {
      var1.writeClassAndObject(var2, this.getComparator(var3));
      var2.writeInt(var3.size(), true);

      for(Object var5 : var3) {
         var1.writeClassAndObject(var2, var5);
         var2.flush();
      }

   }

   public PriorityQueue read(Kryo var1, Input var2, Class var3) {
      Comparator var4 = (Comparator)var1.readClassAndObject(var2);
      int var5 = var2.readInt(true);
      PriorityQueue var6;
      if (var5 == 0) {
         var6 = new PriorityQueue(1, var4);
      } else {
         var6 = new PriorityQueue(var5, var4);
      }

      for(int var7 = 0; var7 < var5; ++var7) {
         var6.add(var1.readClassAndObject(var2));
      }

      return var6;
   }
}
