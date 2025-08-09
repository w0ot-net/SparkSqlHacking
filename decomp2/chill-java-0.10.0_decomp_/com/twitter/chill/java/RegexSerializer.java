package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.util.regex.Pattern;

public class RegexSerializer extends Serializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Pattern.class, new RegexSerializer());
   }

   public void write(Kryo var1, Output var2, Pattern var3) {
      var2.writeString(var3.pattern());
   }

   public Pattern read(Kryo var1, Input var2, Class var3) {
      return Pattern.compile(var2.readString());
   }
}
