package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.sql.Timestamp;

public class TimestampSerializer extends Serializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Timestamp.class, new TimestampSerializer());
   }

   public void write(Kryo var1, Output var2, Timestamp var3) {
      var2.writeLong(var3.getTime(), true);
      var2.writeInt(var3.getNanos(), true);
   }

   public Timestamp read(Kryo var1, Input var2, Class var3) {
      Timestamp var4 = new Timestamp(var2.readLong(true));
      var4.setNanos(var2.readInt(true));
      return var4;
   }
}
