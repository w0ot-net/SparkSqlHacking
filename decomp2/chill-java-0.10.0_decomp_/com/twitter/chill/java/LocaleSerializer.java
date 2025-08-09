package com.twitter.chill.java;

import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.twitter.chill.IKryoRegistrar;
import com.twitter.chill.SingleRegistrar;
import java.util.Locale;

public class LocaleSerializer extends JavaSerializer {
   public static IKryoRegistrar registrar() {
      return new SingleRegistrar(Locale.class, new LocaleSerializer());
   }
}
