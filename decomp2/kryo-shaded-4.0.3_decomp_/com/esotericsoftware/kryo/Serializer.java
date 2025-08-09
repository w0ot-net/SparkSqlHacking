package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public abstract class Serializer {
   private boolean acceptsNull;
   private boolean immutable;

   public Serializer() {
   }

   public Serializer(boolean acceptsNull) {
      this.acceptsNull = acceptsNull;
   }

   public Serializer(boolean acceptsNull, boolean immutable) {
      this.acceptsNull = acceptsNull;
      this.immutable = immutable;
   }

   public abstract void write(Kryo var1, Output var2, Object var3);

   public abstract Object read(Kryo var1, Input var2, Class var3);

   public boolean getAcceptsNull() {
      return this.acceptsNull;
   }

   public void setAcceptsNull(boolean acceptsNull) {
      this.acceptsNull = acceptsNull;
   }

   public boolean isImmutable() {
      return this.immutable;
   }

   public void setImmutable(boolean immutable) {
      this.immutable = immutable;
   }

   public void setGenerics(Kryo kryo, Class[] generics) {
   }

   public Object copy(Kryo kryo, Object original) {
      if (this.isImmutable()) {
         return original;
      } else {
         throw new KryoException("Serializer does not support copy: " + this.getClass().getName());
      }
   }
}
