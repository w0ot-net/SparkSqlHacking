package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.sql.Time;

public class SqlTimeSerializer extends Serializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Time.class, new SqlTimeSerializer());
   }

   public void write(Kryo var1, Output var2, Time var3) {
      var2.writeLong(var3.getTime(), true);
   }

   public Time read(Kryo var1, Input var2, Class var3) {
      return new Time(var2.readLong(true));
   }
}
