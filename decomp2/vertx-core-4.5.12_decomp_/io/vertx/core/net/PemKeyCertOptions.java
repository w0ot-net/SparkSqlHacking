package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.impl.KeyStoreHelper;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import javax.net.ssl.KeyManagerFactory;

@DataObject
@JsonGen(
   publicConverter = false
)
public class PemKeyCertOptions implements KeyCertOptions {
   private KeyStoreHelper helper;
   private List keyPaths;
   private List keyValues;
   private List certPaths;
   private List certValues;

   public PemKeyCertOptions() {
      this.init();
   }

   private void init() {
      this.keyPaths = new ArrayList();
      this.keyValues = new ArrayList();
      this.certPaths = new ArrayList();
      this.certValues = new ArrayList();
   }

   public PemKeyCertOptions(PemKeyCertOptions other) {
      this.keyPaths = other.keyPaths != null ? new ArrayList(other.keyPaths) : new ArrayList();
      this.keyValues = other.keyValues != null ? new ArrayList(other.keyValues) : new ArrayList();
      this.certPaths = other.certPaths != null ? new ArrayList(other.certPaths) : new ArrayList();
      this.certValues = other.certValues != null ? new ArrayList(other.certValues) : new ArrayList();
   }

   public PemKeyCertOptions(JsonObject json) {
      this.init();
      PemKeyCertOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      PemKeyCertOptionsConverter.toJson(this, json);
      return json;
   }

   @GenIgnore
   public String getKeyPath() {
      return this.keyPaths.isEmpty() ? null : (String)this.keyPaths.get(0);
   }

   public PemKeyCertOptions setKeyPath(String keyPath) {
      this.keyPaths.clear();
      if (keyPath != null) {
         this.keyPaths.add(keyPath);
      }

      return this;
   }

   public List getKeyPaths() {
      return this.keyPaths;
   }

   public PemKeyCertOptions setKeyPaths(List keyPaths) {
      this.keyPaths.clear();
      this.keyPaths.addAll(keyPaths);
      return this;
   }

   @GenIgnore
   public PemKeyCertOptions addKeyPath(String keyPath) {
      Arguments.require(keyPath != null, "Null keyPath");
      this.keyPaths.add(keyPath);
      return this;
   }

   @GenIgnore
   public Buffer getKeyValue() {
      return this.keyValues.isEmpty() ? null : (Buffer)this.keyValues.get(0);
   }

   public PemKeyCertOptions setKeyValue(Buffer keyValue) {
      this.keyValues.clear();
      if (keyValue != null) {
         this.keyValues.add(keyValue);
      }

      return this;
   }

   public List getKeyValues() {
      return this.keyValues;
   }

   public PemKeyCertOptions setKeyValues(List keyValues) {
      this.keyValues.clear();
      this.keyValues.addAll(keyValues);
      return this;
   }

   @GenIgnore
   public PemKeyCertOptions addKeyValue(Buffer keyValue) {
      Arguments.require(keyValue != null, "Null keyValue");
      this.keyValues.add(keyValue);
      return this;
   }

   @GenIgnore
   public String getCertPath() {
      return this.certPaths.isEmpty() ? null : (String)this.certPaths.get(0);
   }

   public PemKeyCertOptions setCertPath(String certPath) {
      this.certPaths.clear();
      if (certPath != null) {
         this.certPaths.add(certPath);
      }

      return this;
   }

   public List getCertPaths() {
      return this.certPaths;
   }

   public PemKeyCertOptions setCertPaths(List certPaths) {
      this.certPaths.clear();
      this.certPaths.addAll(certPaths);
      return this;
   }

   @GenIgnore
   public PemKeyCertOptions addCertPath(String certPath) {
      Arguments.require(certPath != null, "Null certPath");
      this.certPaths.add(certPath);
      return this;
   }

   @GenIgnore
   public Buffer getCertValue() {
      return this.certValues.isEmpty() ? null : (Buffer)this.certValues.get(0);
   }

   public PemKeyCertOptions setCertValue(Buffer certValue) {
      this.certValues.clear();
      if (certValue != null) {
         this.certValues.add(certValue);
      }

      return this;
   }

   public List getCertValues() {
      return this.certValues;
   }

   public PemKeyCertOptions setCertValues(List certValues) {
      this.certValues.clear();
      this.certValues.addAll(certValues);
      return this;
   }

   @GenIgnore
   public PemKeyCertOptions addCertValue(Buffer certValue) {
      Arguments.require(certValue != null, "Null certValue");
      this.certValues.add(certValue);
      return this;
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj != null && obj.getClass() == this.getClass()) {
         PemKeyCertOptions that = (PemKeyCertOptions)obj;
         return Objects.equals(this.keyPaths, that.keyPaths) && Objects.equals(this.keyValues, that.keyValues) && Objects.equals(this.certPaths, that.certPaths) && Objects.equals(this.certValues, that.certValues);
      } else {
         return false;
      }
   }

   public PemKeyCertOptions copy() {
      return new PemKeyCertOptions(this);
   }

   KeyStoreHelper getHelper(Vertx vertx) throws Exception {
      if (this.helper == null) {
         List<Buffer> keys = new ArrayList();

         for(String keyPath : this.keyPaths) {
            keys.add(vertx.fileSystem().readFileBlocking(((VertxInternal)vertx).resolveFile(keyPath).getAbsolutePath()));
         }

         keys.addAll(this.keyValues);
         List<Buffer> certs = new ArrayList();

         for(String certPath : this.certPaths) {
            certs.add(vertx.fileSystem().readFileBlocking(((VertxInternal)vertx).resolveFile(certPath).getAbsolutePath()));
         }

         certs.addAll(this.certValues);
         this.helper = new KeyStoreHelper(KeyStoreHelper.loadKeyCert(keys, certs), "dummdummydummydummydummydummydummy", (String)null);
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
}
