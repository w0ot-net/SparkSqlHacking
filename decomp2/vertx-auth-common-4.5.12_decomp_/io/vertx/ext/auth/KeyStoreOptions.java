package io.vertx.ext.auth;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

@DataObject
@JsonGen(
   publicConverter = false
)
public class KeyStoreOptions {
   private static final String DEFAULT_TYPE = KeyStore.getDefaultType();
   private String type;
   private String provider;
   private String password;
   private String path;
   /** @deprecated */
   @Deprecated
   private Buffer value;
   private Map passwordProtection;

   public KeyStoreOptions() {
      this.type = DEFAULT_TYPE;
   }

   public KeyStoreOptions(KeyStoreOptions other) {
      this.type = other.getType();
      if (this.type == null) {
         this.type = DEFAULT_TYPE;
      }

      this.password = other.getPassword();
      this.path = other.getPath();
      this.value = other.getValue();
      this.passwordProtection = other.getPasswordProtection();
      this.provider = other.getProvider();
   }

   public KeyStoreOptions(JsonObject json) {
      KeyStoreOptionsConverter.fromJson(json, this);
   }

   @Fluent
   public KeyStoreOptions setType(String type) {
      this.type = type;
      return this;
   }

   @Fluent
   public KeyStoreOptions setProvider(String provider) {
      this.provider = provider;
      return this;
   }

   @Fluent
   public KeyStoreOptions setPassword(String password) {
      this.password = password;
      return this;
   }

   @Fluent
   public KeyStoreOptions setPath(String path) {
      this.path = path;
      return this;
   }

   /** @deprecated */
   @Fluent
   @Deprecated
   public KeyStoreOptions setValue(Buffer value) {
      this.value = value;
      return this;
   }

   @Fluent
   public KeyStoreOptions setPasswordProtection(Map passwordProtection) {
      this.passwordProtection = passwordProtection;
      return this;
   }

   public String getType() {
      return this.type;
   }

   public String getProvider() {
      return this.provider;
   }

   public String getPassword() {
      return this.password;
   }

   public String getPath() {
      return this.path;
   }

   /** @deprecated */
   @Deprecated
   public Buffer getValue() {
      return this.value;
   }

   public Map getPasswordProtection() {
      return this.passwordProtection;
   }

   public KeyStoreOptions putPasswordProtection(String alias, String password) {
      if (this.passwordProtection == null) {
         this.passwordProtection = new HashMap();
      }

      this.passwordProtection.put(alias, password);
      return this;
   }
}
