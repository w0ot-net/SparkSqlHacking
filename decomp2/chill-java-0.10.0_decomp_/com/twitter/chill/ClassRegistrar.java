package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;

public class ClassRegistrar implements IKryoRegistrar {
   final Class klass;

   public ClassRegistrar(Class var1) {
      this.klass = var1;
   }

   public Class getRegisteredClass() {
      return this.klass;
   }

   public void apply(Kryo var1) {
      var1.register(this.klass);
   }

   public int hashCode() {
      return this.klass.hashCode();
   }

   public boolean equals(Object var1) {
      if (null == var1) {
         return false;
      } else {
         return var1 instanceof ClassRegistrar ? this.klass.equals(((ClassRegistrar)var1).klass) : false;
      }
   }
}
