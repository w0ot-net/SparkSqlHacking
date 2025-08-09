package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class KeyStoreOptions extends KeyStoreOptionsBase {
   public KeyStoreOptions() {
   }

   public KeyStoreOptions(KeyStoreOptions other) {
      super(other);
   }

   public KeyStoreOptions(JsonObject json) {
      this();
      KeyStoreOptionsConverter.fromJson(json, this);
   }

   public String getProvider() {
      return super.getProvider();
   }

   public KeyStoreOptions setProvider(String provider) {
      return (KeyStoreOptions)super.setProvider(provider);
   }

   public String getType() {
      return super.getType();
   }

   public KeyStoreOptions setType(String type) {
      return (KeyStoreOptions)super.setType(type);
   }

   public KeyStoreOptions setPassword(String password) {
      return (KeyStoreOptions)super.setPassword(password);
   }

   public KeyStoreOptions setPath(String path) {
      return (KeyStoreOptions)super.setPath(path);
   }

   public KeyStoreOptions setValue(Buffer value) {
      return (KeyStoreOptions)super.setValue(value);
   }

   public KeyStoreOptions setAlias(String alias) {
      return (KeyStoreOptions)super.setAlias(alias);
   }

   public KeyStoreOptions setAliasPassword(String aliasPassword) {
      return (KeyStoreOptions)super.setAliasPassword(aliasPassword);
   }

   public KeyStoreOptions copy() {
      return new KeyStoreOptions(this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      KeyStoreOptionsConverter.toJson(this, json);
      return json;
   }
}
