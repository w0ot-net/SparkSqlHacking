package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.util.UUID;

public class UUIDSerializer extends Serializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(UUID.class, new UUIDSerializer());
   }

   public void write(Kryo var1, Output var2, UUID var3) {
      var2.writeLong(var3.getMostSignificantBits(), false);
      var2.writeLong(var3.getLeastSignificantBits(), false);
   }

   public UUID read(Kryo var1, Input var2, Class var3) {
      return new UUID(var2.readLong(false), var2.readLong(false));
   }
}
