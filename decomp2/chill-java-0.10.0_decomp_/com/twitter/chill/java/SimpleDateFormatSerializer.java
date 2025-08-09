package com.twitter.chill.java;

import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.text.SimpleDateFormat;

public class SimpleDateFormatSerializer extends JavaSerializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(SimpleDateFormat.class, new SimpleDateFormatSerializer());
   }
}
