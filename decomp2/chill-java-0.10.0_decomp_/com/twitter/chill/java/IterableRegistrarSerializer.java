package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import java.util.ArrayList;

public class IterableRegistrarSerializer extends Serializer {
   public void write(Kryo var1, Output var2, IterableRegistrar var3) {
      for(IKryoRegistrar var5 : var3.getRegistrars()) {
         var1.writeClassAndObject(var2, var5);
         var2.flush();
      }

      var1.writeClassAndObject(var2, (Object)null);
   }

   public IterableRegistrar read(Kryo var1, Input var2, Class var3) {
      ArrayList var4 = new ArrayList();

      for(IKryoRegistrar var5 = (IKryoRegistrar)var1.readClassAndObject(var2); var5 != null; var5 = (IKryoRegistrar)var1.readClassAndObject(var2)) {
         var4.add(var5);
      }

      return new IterableRegistrar(var4);
   }
}
