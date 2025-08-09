package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.AeadResult;
import io.jsonwebtoken.security.DigestSupplier;
import io.jsonwebtoken.security.IvSupplier;
import java.io.OutputStream;

public class DefaultAeadResult implements AeadResult, DigestSupplier, IvSupplier {
   private final OutputStream out;
   private byte[] tag;
   private byte[] iv;

   public DefaultAeadResult(OutputStream out) {
      this.out = (OutputStream)Assert.notNull(out, "OutputStream cannot be null.");
   }

   public OutputStream getOutputStream() {
      return this.out;
   }

   public byte[] getDigest() {
      return this.tag;
   }

   public AeadResult setTag(byte[] tag) {
      this.tag = Assert.notEmpty(tag, "Authentication Tag cannot be null or empty.");
      return this;
   }

   public AeadResult setIv(byte[] iv) {
      this.iv = Assert.notEmpty(iv, "Initialization Vector cannot be null or empty.");
      return this;
   }

   public byte[] getIv() {
      return this.iv;
   }
}
