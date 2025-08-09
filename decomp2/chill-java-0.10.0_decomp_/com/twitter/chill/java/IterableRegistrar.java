package com.twitter.chill.java;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.Kryo;
import com.twitter.chill.IKryoRegistrar;
import java.util.Arrays;

@DefaultSerializer(IterableRegistrarSerializer.class)
public class IterableRegistrar implements IKryoRegistrar {
   private final Iterable registrarList;

   public IterableRegistrar(IKryoRegistrar... var1) {
      this((Iterable)Arrays.asList(var1));
   }

   public IterableRegistrar(Iterable var1) {
      this.registrarList = var1;

      for(IKryoRegistrar var3 : this.registrarList) {
         if (null == var3) {
            throw new IllegalArgumentException("null Registrars not allowed");
         }
      }

   }

   public void apply(Kryo var1) {
      for(IKryoRegistrar var3 : this.registrarList) {
         var3.apply(var1);
      }

   }

   public Iterable getRegistrars() {
      return this.registrarList;
   }
}
