package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.lang.Converter;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.security.KeyOperation;
import io.jsonwebtoken.security.Jwks.OP;

final class KeyOperationConverter implements Converter {
   static final Converter DEFAULT = new KeyOperationConverter(OP.get());
   private final Registry registry;

   KeyOperationConverter(Registry registry) {
      this.registry = (Registry)Assert.notEmpty(registry, "KeyOperation registry cannot be null or empty.");
   }

   public String applyTo(KeyOperation operation) {
      Assert.notNull(operation, "KeyOperation cannot be null.");
      return operation.getId();
   }

   public KeyOperation applyFrom(Object o) {
      if (o instanceof KeyOperation) {
         return (KeyOperation)o;
      } else {
         String id = (String)Assert.isInstanceOf(String.class, o, "Argument must be a KeyOperation or String.");
         Assert.hasText(id, "KeyOperation string value cannot be null or empty.");
         KeyOperation keyOp = (KeyOperation)this.registry.get(id);
         return keyOp != null ? keyOp : (KeyOperation)OP.builder().id(id).build();
      }
   }
}
