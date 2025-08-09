package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.net.URI;

public class URISerializer extends Serializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(URI.class, new URISerializer());
   }

   public void write(Kryo var1, Output var2, URI var3) {
      var2.writeString(var3.toString());
   }

   public URI read(Kryo var1, Input var2, Class var3) {
      return URI.create(var2.readString());
   }
}
