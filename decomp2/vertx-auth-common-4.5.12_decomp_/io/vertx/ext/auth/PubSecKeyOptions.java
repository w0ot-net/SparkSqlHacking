package io.vertx.ext.auth;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class PubSecKeyOptions {
   private String algorithm;
   private Buffer buffer;
   private String id;
   private boolean certificate;
   private Boolean symmetric;
   private String publicKey;
   private String secretKey;

   public PubSecKeyOptions() {
   }

   public PubSecKeyOptions(PubSecKeyOptions other) {
      this.algorithm = other.algorithm;
      this.buffer = other.buffer == null ? null : other.buffer.copy();
      this.id = other.getId();
      this.publicKey = other.getPublicKey();
      this.secretKey = other.getSecretKey();
      this.symmetric = other.isSymmetric();
      this.certificate = other.isCertificate();
   }

   public PubSecKeyOptions(JsonObject json) {
      PubSecKeyOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      PubSecKeyOptionsConverter.toJson(this, json);
      return json;
   }

   public String getAlgorithm() {
      return this.algorithm;
   }

   public PubSecKeyOptions setAlgorithm(String algorithm) {
      this.algorithm = algorithm;
      return this;
   }

   public Buffer getBuffer() {
      return this.buffer;
   }

   @GenIgnore({"permitted-type"})
   public PubSecKeyOptions setBuffer(String buffer) {
      this.buffer = Buffer.buffer(buffer, "UTF-8");
      return this;
   }

   public PubSecKeyOptions setBuffer(Buffer buffer) {
      this.buffer = buffer;
      return this;
   }

   public String getId() {
      return this.id;
   }

   public PubSecKeyOptions setId(String id) {
      this.id = id;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public String getPublicKey() {
      return this.publicKey;
   }

   /** @deprecated */
   @Deprecated
   public PubSecKeyOptions setPublicKey(String publicKey) {
      this.publicKey = publicKey;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public String getSecretKey() {
      return this.secretKey;
   }

   /** @deprecated */
   @Deprecated
   public PubSecKeyOptions setSecretKey(String secretKey) {
      this.secretKey = secretKey;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public boolean isSymmetric() {
      if (this.symmetric != null) {
         return this.symmetric;
      } else {
         return this.algorithm.startsWith("HS") && this.publicKey == null && this.secretKey != null;
      }
   }

   /** @deprecated */
   @Deprecated
   public PubSecKeyOptions setSymmetric(boolean symmetric) {
      this.symmetric = symmetric;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public boolean isCertificate() {
      return this.certificate;
   }

   /** @deprecated */
   @Deprecated
   public PubSecKeyOptions setCertificate(boolean certificate) {
      this.certificate = certificate;
      return this;
   }
}
