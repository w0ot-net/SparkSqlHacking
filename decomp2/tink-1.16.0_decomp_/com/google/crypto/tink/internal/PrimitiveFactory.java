package com.google.crypto.tink.internal;

import com.google.protobuf.MessageLite;
import java.security.GeneralSecurityException;

public abstract class PrimitiveFactory {
   private final Class clazz;

   public PrimitiveFactory(Class clazz) {
      this.clazz = clazz;
   }

   final Class getPrimitiveClass() {
      return this.clazz;
   }

   public abstract Object getPrimitive(MessageLite key) throws GeneralSecurityException;
}
