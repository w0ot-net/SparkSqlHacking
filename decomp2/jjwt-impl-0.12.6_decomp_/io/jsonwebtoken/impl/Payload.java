package io.jsonwebtoken.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.Jwts.ZIP;
import io.jsonwebtoken.impl.io.Streams;
import io.jsonwebtoken.impl.lang.Bytes;
import io.jsonwebtoken.io.CompressionAlgorithm;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import java.io.InputStream;
import java.io.OutputStream;

class Payload {
   static final Payload EMPTY;
   private final CharSequence string;
   private final byte[] bytes;
   private final Claims claims;
   private final InputStream inputStream;
   private final boolean inputStreamEmpty;
   private final String contentType;
   private CompressionAlgorithm zip;
   private boolean claimsExpected;

   Payload(Claims claims) {
      this(claims, (CharSequence)null, (byte[])null, (InputStream)null, (String)null);
   }

   Payload(CharSequence content, String contentType) {
      this((Claims)null, content, (byte[])null, (InputStream)null, contentType);
   }

   Payload(byte[] content, String contentType) {
      this((Claims)null, (CharSequence)null, content, (InputStream)null, contentType);
   }

   Payload(InputStream inputStream, String contentType) {
      this((Claims)null, (CharSequence)null, (byte[])null, inputStream, contentType);
   }

   private Payload(Claims claims, CharSequence string, byte[] bytes, InputStream inputStream, String contentType) {
      this.claims = claims;
      this.string = Strings.clean(string);
      this.contentType = Strings.clean(contentType);
      InputStream in = inputStream;
      byte[] data = Bytes.nullSafe(bytes);
      if (Strings.hasText(this.string)) {
         data = Strings.utf8(this.string);
      }

      this.bytes = data;
      if (inputStream == null && !Bytes.isEmpty(this.bytes)) {
         in = Streams.of(data);
      }

      this.inputStreamEmpty = in == null;
      this.inputStream = this.inputStreamEmpty ? Streams.of(Bytes.EMPTY) : in;
   }

   boolean isClaims() {
      return !Collections.isEmpty(this.claims);
   }

   Claims getRequiredClaims() {
      return (Claims)Assert.notEmpty(this.claims, "Claims cannot be null or empty when calling this method.");
   }

   boolean isString() {
      return Strings.hasText(this.string);
   }

   String getContentType() {
      return this.contentType;
   }

   public void setZip(CompressionAlgorithm zip) {
      this.zip = zip;
   }

   boolean isCompressed() {
      return this.zip != null;
   }

   public void setClaimsExpected(boolean claimsExpected) {
      this.claimsExpected = claimsExpected;
   }

   boolean isConsumable() {
      return !this.isClaims() && (this.isString() || !Bytes.isEmpty(this.bytes) || this.inputStream != null && this.claimsExpected);
   }

   boolean isEmpty() {
      return !this.isClaims() && !this.isString() && Bytes.isEmpty(this.bytes) && this.inputStreamEmpty;
   }

   public OutputStream compress(OutputStream out) {
      return this.zip != null ? this.zip.compress(out) : out;
   }

   public Payload decompress(CompressionAlgorithm alg) {
      Assert.notNull(alg, "CompressionAlgorithm cannot be null.");
      Payload payload = this;
      if (!this.isString() && this.isConsumable()) {
         if (alg.equals(ZIP.DEF) && !Bytes.isEmpty(this.bytes)) {
            byte[] data = ((CompressionCodec)alg).decompress(this.bytes);
            payload = new Payload(this.claims, this.string, data, (InputStream)null, this.getContentType());
         } else {
            InputStream in = this.toInputStream();
            in = alg.decompress(in);
            payload = new Payload(this.claims, this.string, this.bytes, in, this.getContentType());
         }

         payload.setClaimsExpected(this.claimsExpected);
      }

      return payload;
   }

   public byte[] getBytes() {
      return this.bytes;
   }

   InputStream toInputStream() {
      Assert.state(!this.isClaims(), "Claims exist, cannot convert to InputStream directly.");
      return this.inputStream;
   }

   static {
      EMPTY = new Payload(Bytes.EMPTY, (String)null);
   }
}
