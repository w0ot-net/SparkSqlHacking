package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.sql.Date;

public class SqlDateSerializer extends Serializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Date.class, new SqlDateSerializer());
   }

   public void write(Kryo var1, Output var2, Date var3) {
      var2.writeLong(var3.getTime(), true);
   }

   public Date read(Kryo var1, Input var2, Class var3) {
      return new Date(var2.readLong(true));
   }
}
