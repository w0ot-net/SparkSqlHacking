package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class JksOptions extends KeyStoreOptionsBase {
   public JksOptions() {
      this.setType("JKS");
   }

   public JksOptions(JksOptions other) {
      super(other);
   }

   public JksOptions(JsonObject json) {
      this();
      JksOptionsConverter.fromJson(json, this);
   }

   public JksOptions setPassword(String password) {
      return (JksOptions)super.setPassword(password);
   }

   public JksOptions setPath(String path) {
      return (JksOptions)super.setPath(path);
   }

   public JksOptions setValue(Buffer value) {
      return (JksOptions)super.setValue(value);
   }

   public JksOptions setAlias(String alias) {
      return (JksOptions)super.setAlias(alias);
   }

   public JksOptions setAliasPassword(String aliasPassword) {
      return (JksOptions)super.setAliasPassword(aliasPassword);
   }

   public JksOptions copy() {
      return new JksOptions(this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      JksOptionsConverter.toJson(this, json);
      return json;
   }
}
