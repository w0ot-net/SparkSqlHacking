package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.net.InetSocketAddress;

public class InetSocketAddressSerializer extends Serializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(InetSocketAddress.class, new InetSocketAddressSerializer());
   }

   public void write(Kryo var1, Output var2, InetSocketAddress var3) {
      var2.writeString(var3.getHostName());
      var2.writeInt(var3.getPort(), true);
   }

   public InetSocketAddress read(Kryo var1, Input var2, Class var3) {
      String var4 = var2.readString();
      int var5 = var2.readInt(true);
      return new InetSocketAddress(var4, var5);
   }
}
