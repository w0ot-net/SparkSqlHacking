package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.util.Util;

public class ReflectingRegistrar implements IKryoRegistrar {
   final Class klass;
   final Class serializerKlass;

   public Class getRegisteredClass() {
      return this.klass;
   }

   public Class getSerializerClass() {
      return this.serializerKlass;
   }

   public ReflectingRegistrar(Class var1, Class var2) {
      this.klass = var1;
      this.serializerKlass = var2;
   }

   public void apply(Kryo var1) {
      try {
         var1.register(this.klass, (Serializer)this.serializerKlass.newInstance());
      } catch (Exception var3) {
         throw new IllegalArgumentException("Unable to create serializer \"" + this.serializerKlass.getName() + "\" for class: " + Util.className(this.klass), var3);
      }
   }

   public int hashCode() {
      return this.klass.hashCode() ^ this.serializerKlass.hashCode();
   }

   public boolean equals(Object var1) {
      if (null == var1) {
         return false;
      } else if (!(var1 instanceof ReflectingRegistrar)) {
         return false;
      } else {
         return this.klass.equals(((ReflectingRegistrar)var1).klass) && this.serializerKlass.equals(((ReflectingRegistrar)var1).serializerKlass);
      }
   }
}
