package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;

public class ReflectingDefaultRegistrar implements IKryoRegistrar {
   final Class klass;
   final Class serializerKlass;

   public ReflectingDefaultRegistrar(Class var1, Class var2) {
      this.klass = var1;
      this.serializerKlass = var2;
   }

   public Class getRegisteredClass() {
      return this.klass;
   }

   public Class getSerializerClass() {
      return this.serializerKlass;
   }

   public void apply(Kryo var1) {
      var1.addDefaultSerializer(this.klass, this.serializerKlass);
   }

   public int hashCode() {
      return this.klass.hashCode() ^ this.serializerKlass.hashCode();
   }

   public boolean equals(Object var1) {
      if (null == var1) {
         return false;
      } else if (!(var1 instanceof ReflectingDefaultRegistrar)) {
         return false;
      } else {
         return this.klass.equals(((ReflectingDefaultRegistrar)var1).klass) && this.serializerKlass.equals(((ReflectingDefaultRegistrar)var1).serializerKlass);
      }
   }
}
