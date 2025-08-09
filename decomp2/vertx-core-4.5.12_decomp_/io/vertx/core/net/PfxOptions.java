package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class PfxOptions extends KeyStoreOptionsBase {
   public PfxOptions() {
      this.setType("PKCS12");
   }

   public PfxOptions(PfxOptions other) {
      super(other);
   }

   public PfxOptions(JsonObject json) {
      this();
      PfxOptionsConverter.fromJson(json, this);
   }

   public PfxOptions setPassword(String password) {
      return (PfxOptions)super.setPassword(password);
   }

   public PfxOptions setPath(String path) {
      return (PfxOptions)super.setPath(path);
   }

   public PfxOptions setValue(Buffer value) {
      return (PfxOptions)super.setValue(value);
   }

   public PfxOptions setAlias(String alias) {
      return (PfxOptions)super.setAlias(alias);
   }

   public PfxOptions setAliasPassword(String aliasPassword) {
      return (PfxOptions)super.setAliasPassword(aliasPassword);
   }

   public PfxOptions copy() {
      return new PfxOptions(this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      PfxOptionsConverter.toJson(this, json);
      return json;
   }
}
