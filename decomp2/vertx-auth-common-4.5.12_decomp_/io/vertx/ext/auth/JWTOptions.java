package io.vertx.ext.auth;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

@DataObject
@JsonGen(
   publicConverter = false
)
public class JWTOptions {
   private int leeway = 0;
   private boolean ignoreExpiration;
   private String algorithm = "HS256";
   private JsonObject header;
   private boolean noTimestamp;
   private int expires;
   private List audience;
   private String issuer;
   private String subject;
   private List permissions;
   private String nonceAlgorithm;

   public JWTOptions() {
      this.header = new JsonObject();
   }

   public JWTOptions(JWTOptions other) {
      this.leeway = other.leeway;
      this.ignoreExpiration = other.ignoreExpiration;
      this.algorithm = other.algorithm;
      this.header = other.header == null ? new JsonObject() : other.header.copy();
      this.noTimestamp = other.noTimestamp;
      this.expires = other.expires;
      this.audience = other.audience == null ? null : new ArrayList(other.audience);
      this.issuer = other.issuer;
      this.subject = other.subject;
      this.permissions = other.permissions == null ? null : new ArrayList(other.permissions);
      this.nonceAlgorithm = other.nonceAlgorithm;
   }

   public JWTOptions(JsonObject json) {
      this.header = new JsonObject();
      JWTOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      JWTOptionsConverter.toJson(this, json);
      return json;
   }

   public int getLeeway() {
      return this.leeway;
   }

   public JWTOptions setLeeway(int leeway) {
      this.leeway = leeway;
      return this;
   }

   public boolean isIgnoreExpiration() {
      return this.ignoreExpiration;
   }

   public JWTOptions setIgnoreExpiration(boolean ignoreExpiration) {
      this.ignoreExpiration = ignoreExpiration;
      return this;
   }

   public String getAlgorithm() {
      return this.algorithm;
   }

   public JWTOptions setAlgorithm(String algorithm) {
      this.algorithm = algorithm;
      return this;
   }

   public JsonObject getHeader() {
      return this.header;
   }

   public JWTOptions setHeader(JsonObject header) {
      this.header = header;
      return this;
   }

   public boolean isNoTimestamp() {
      return this.noTimestamp;
   }

   public JWTOptions setNoTimestamp(boolean noTimestamp) {
      this.noTimestamp = noTimestamp;
      return this;
   }

   public int getExpiresInSeconds() {
      return this.expires;
   }

   public JWTOptions setExpiresInSeconds(int expires) {
      this.expires = expires;
      return this;
   }

   public JWTOptions setExpiresInMinutes(int expiresInMinutes) {
      this.expires = expiresInMinutes * 60;
      return this;
   }

   public List getAudience() {
      return this.audience;
   }

   public JWTOptions setAudience(List audience) {
      this.audience = audience;
      return this;
   }

   public JWTOptions addAudience(String audience) {
      if (this.audience == null) {
         this.audience = new ArrayList();
      }

      this.audience.add(audience);
      return this;
   }

   public String getIssuer() {
      return this.issuer;
   }

   public JWTOptions setIssuer(String issuer) {
      this.issuer = issuer;
      return this;
   }

   public String getSubject() {
      return this.subject;
   }

   public JWTOptions setSubject(String subject) {
      this.subject = subject;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public JWTOptions setPermissions(List permissions) {
      this.permissions = permissions;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public JWTOptions addPermission(String permission) {
      if (this.permissions == null) {
         this.permissions = new ArrayList();
      }

      this.permissions.add(permission);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public List getPermissions() {
      return this.permissions;
   }

   public String getNonceAlgorithm() {
      return this.nonceAlgorithm;
   }

   public JWTOptions setNonceAlgorithm(String nonceAlgorithm) {
      this.nonceAlgorithm = nonceAlgorithm;
      return this;
   }
}
