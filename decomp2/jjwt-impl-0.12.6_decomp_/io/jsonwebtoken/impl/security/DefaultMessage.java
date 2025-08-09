package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.Message;

class DefaultMessage implements Message {
   private final Object payload;

   DefaultMessage(Object payload) {
      this.payload = Assert.notNull(payload, "payload cannot be null.");
      if (payload instanceof byte[]) {
         this.assertBytePayload((byte[])payload);
      }

   }

   protected void assertBytePayload(byte[] payload) {
      Assert.notEmpty(payload, "payload byte array cannot be null or empty.");
   }

   public Object getPayload() {
      return this.payload;
   }
}
