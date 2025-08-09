package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;
import java.util.Objects;

@DataObject
@JsonGen(
   publicConverter = false
)
public class ProxyOptions {
   public static final ProxyType DEFAULT_TYPE;
   public static final int DEFAULT_PORT = 3128;
   public static final String DEFAULT_HOST = "localhost";
   private String host;
   private int port;
   private String username;
   private String password;
   private ProxyType type;

   public ProxyOptions() {
      this.host = "localhost";
      this.port = 3128;
      this.type = DEFAULT_TYPE;
   }

   public ProxyOptions(ProxyOptions other) {
      this.host = other.getHost();
      this.port = other.getPort();
      this.username = other.getUsername();
      this.password = other.getPassword();
      this.type = other.getType();
   }

   public ProxyOptions(JsonObject json) {
      this();
      ProxyOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      ProxyOptionsConverter.toJson(this, json);
      return json;
   }

   public String getHost() {
      return this.host;
   }

   public ProxyOptions setHost(String host) {
      Objects.requireNonNull(host, "Proxy host may not be null");
      this.host = host;
      return this;
   }

   public int getPort() {
      return this.port;
   }

   public ProxyOptions setPort(int port) {
      if (port >= 0 && port <= 65535) {
         this.port = port;
         return this;
      } else {
         throw new IllegalArgumentException("Invalid proxy port " + port);
      }
   }

   public String getUsername() {
      return this.username;
   }

   public ProxyOptions setUsername(String username) {
      this.username = username;
      return this;
   }

   public String getPassword() {
      return this.password;
   }

   public ProxyOptions setPassword(String password) {
      this.password = password;
      return this;
   }

   public ProxyType getType() {
      return this.type;
   }

   public ProxyOptions setType(ProxyType type) {
      Objects.requireNonNull(type, "Proxy type may not be null");
      this.type = type;
      return this;
   }

   static {
      DEFAULT_TYPE = ProxyType.HTTP;
   }
}
