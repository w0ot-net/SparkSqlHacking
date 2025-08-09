package com.twitter.chill.java;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.ClosureSerializer;
import com.twitter.chill.IKryoRegistrar;

public class Java8ClosureRegistrar implements IKryoRegistrar {
   public void apply(Kryo var1) {
      try {
         Class.forName("java.lang.invoke.SerializedLambda");
      } catch (ClassNotFoundException var3) {
         return;
      }

      var1.register(ClosureSerializer.Closure.class, new ClosureSerializer());
   }
}
