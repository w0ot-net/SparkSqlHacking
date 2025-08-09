package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class OAuth2WebClientOptions {
   public static final boolean DEFAULT_RENEW_TOKEN_ON_FORBIDDEN = false;
   public static final int DEFAULT_LEEWAY = 0;
   private boolean renewTokenOnForbidden = false;
   private int leeway = 0;

   public OAuth2WebClientOptions() {
   }

   public OAuth2WebClientOptions(OAuth2WebClientOptions other) {
      this.renewTokenOnForbidden = other.renewTokenOnForbidden;
      this.leeway = other.leeway;
   }

   public OAuth2WebClientOptions(JsonObject json) {
      OAuth2WebClientOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      OAuth2WebClientOptionsConverter.toJson(this, json);
      return json;
   }

   public boolean isRenewTokenOnForbidden() {
      return this.renewTokenOnForbidden;
   }

   public OAuth2WebClientOptions setRenewTokenOnForbidden(boolean renewTokenOnForbidden) {
      this.renewTokenOnForbidden = renewTokenOnForbidden;
      return this;
   }

   public int getLeeway() {
      return this.leeway;
   }

   public OAuth2WebClientOptions setLeeway(int leeway) {
      this.leeway = leeway;
      return this;
   }
}
