package com.twitter.chill;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Externalizer$ implements Serializable {
   public static final Externalizer$ MODULE$ = new Externalizer$();
   private static final int com$twitter$chill$Externalizer$$KRYO = 0;
   private static final int com$twitter$chill$Externalizer$$JAVA = 1;

   public int com$twitter$chill$Externalizer$$KRYO() {
      return com$twitter$chill$Externalizer$$KRYO;
   }

   public int com$twitter$chill$Externalizer$$JAVA() {
      return com$twitter$chill$Externalizer$$JAVA;
   }

   public Externalizer apply(final Object t) {
      Externalizer x = new Externalizer();
      x.set(t);
      return x;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Externalizer$.class);
   }

   private Externalizer$() {
   }
}
