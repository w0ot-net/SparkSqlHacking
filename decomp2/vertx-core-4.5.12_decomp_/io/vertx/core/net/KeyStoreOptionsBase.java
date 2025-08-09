package io.vertx.core.net;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.impl.KeyStoreHelper;
import java.security.KeyStore;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

public abstract class KeyStoreOptionsBase implements KeyCertOptions, TrustOptions {
   private KeyStoreHelper helper;
   private String provider;
   private String type;
   private String password;
   private String path;
   private Buffer value;
   private String alias;
   private String aliasPassword;

   protected KeyStoreOptionsBase() {
   }

   protected KeyStoreOptionsBase(KeyStoreOptionsBase other) {
      this.type = other.type;
      this.password = other.password;
      this.path = other.path;
      this.value = other.value;
      this.alias = other.alias;
      this.aliasPassword = other.aliasPassword;
   }

   protected String getType() {
      return this.type;
   }

   protected KeyStoreOptionsBase setType(String type) {
      this.type = type;
      return this;
   }

   protected String getProvider() {
      return this.provider;
   }

   protected KeyStoreOptionsBase setProvider(String provider) {
      this.provider = provider;
      return this;
   }

   public String getPassword() {
      return this.password;
   }

   public KeyStoreOptionsBase setPassword(String password) {
      this.password = password;
      return this;
   }

   public String getPath() {
      return this.path;
   }

   public KeyStoreOptionsBase setPath(String path) {
      this.path = path;
      return this;
   }

   public Buffer getValue() {
      return this.value;
   }

   public KeyStoreOptionsBase setValue(Buffer value) {
      this.value = value;
      return this;
   }

   public String getAlias() {
      return this.alias;
   }

   public KeyStoreOptionsBase setAlias(String alias) {
      this.alias = alias;
      return this;
   }

   public String getAliasPassword() {
      return this.aliasPassword;
   }

   public KeyStoreOptionsBase setAliasPassword(String aliasPassword) {
      this.aliasPassword = aliasPassword;
      return this;
   }

   KeyStoreHelper getHelper(Vertx vertx) throws Exception {
      if (this.helper == null) {
         Supplier<Buffer> value;
         if (this.path != null) {
            value = () -> vertx.fileSystem().readFileBlocking(this.path);
         } else if (this.value != null) {
            value = this::getValue;
         } else {
            value = () -> null;
         }

         this.helper = new KeyStoreHelper(KeyStoreHelper.loadKeyStore(this.type, this.provider, this.password, value, this.getAlias()), this.password, this.getAliasPassword());
      }

      return this.helper;
   }

   public KeyStore loadKeyStore(Vertx vertx) throws Exception {
      KeyStoreHelper helper = this.getHelper(vertx);
      return helper != null ? helper.store() : null;
   }

   public KeyManagerFactory getKeyManagerFactory(Vertx vertx) throws Exception {
      KeyStoreHelper helper = this.getHelper(vertx);
      return helper != null ? helper.getKeyMgrFactory() : null;
   }

   public Function keyManagerMapper(Vertx vertx) throws Exception {
      KeyStoreHelper helper = this.getHelper(vertx);
      return helper != null ? helper::getKeyMgr : null;
   }

   public Function keyManagerFactoryMapper(Vertx vertx) throws Exception {
      KeyStoreHelper helper = this.getHelper(vertx);
      return helper != null ? helper::getKeyMgrFactory : null;
   }

   public TrustManagerFactory getTrustManagerFactory(Vertx vertx) throws Exception {
      KeyStoreHelper helper = this.getHelper(vertx);
      return helper != null ? helper.getTrustMgrFactory((VertxInternal)vertx) : null;
   }

   public Function trustManagerMapper(Vertx vertx) throws Exception {
      KeyStoreHelper helper = this.getHelper(vertx);
      return helper != null ? helper::getTrustMgr : null;
   }

   public abstract KeyStoreOptionsBase copy();

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj != null && obj.getClass() == this.getClass()) {
         KeyStoreOptionsBase that = (KeyStoreOptionsBase)obj;
         return Objects.equals(this.provider, that.provider) && Objects.equals(this.type, that.type) && Objects.equals(this.password, that.password) && Objects.equals(this.path, that.path) && Objects.equals(this.value, that.value) && Objects.equals(this.alias, that.alias) && Objects.equals(this.aliasPassword, that.aliasPassword);
      } else {
         return false;
      }
   }
}
