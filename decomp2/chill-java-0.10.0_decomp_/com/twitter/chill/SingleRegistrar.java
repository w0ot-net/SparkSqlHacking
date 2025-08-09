package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;

public class SingleRegistrar implements IKryoRegistrar {
   final Class klass;
   final Serializer serializer;

   public SingleRegistrar(Class var1, Serializer var2) {
      this.klass = var1;
      this.serializer = var2;
   }

   public void apply(Kryo var1) {
      var1.register(this.klass, this.serializer);
   }
}
