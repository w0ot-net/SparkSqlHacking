package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.impl.Arguments;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.impl.KeyStoreHelper;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.net.ssl.TrustManagerFactory;

@DataObject
@JsonGen(
   publicConverter = false
)
public class PemTrustOptions implements TrustOptions, Cloneable {
   private KeyStoreHelper helper;
   private ArrayList certPaths;
   private ArrayList certValues;

   public PemTrustOptions() {
      this.certPaths = new ArrayList();
      this.certValues = new ArrayList();
   }

   public PemTrustOptions(PemTrustOptions other) {
      this.certPaths = new ArrayList(other.getCertPaths());
      this.certValues = new ArrayList(other.getCertValues());
   }

   public PemTrustOptions(JsonObject json) {
      this();
      PemTrustOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      PemTrustOptionsConverter.toJson(this, json);
      return json;
   }

   public List getCertPaths() {
      return this.certPaths;
   }

   public PemTrustOptions addCertPath(String certPath) throws NullPointerException {
      Objects.requireNonNull(certPath, "No null certificate accepted");
      Arguments.require(!certPath.isEmpty(), "No empty certificate path accepted");
      this.certPaths.add(certPath);
      return this;
   }

   public List getCertValues() {
      return this.certValues;
   }

   public PemTrustOptions addCertValue(Buffer certValue) throws NullPointerException {
      Objects.requireNonNull(certValue, "No null certificate accepted");
      this.certValues.add(certValue);
      return this;
   }

   public KeyStore loadKeyStore(Vertx vertx) throws Exception {
      KeyStoreHelper helper = this.getHelper(vertx);
      return helper != null ? helper.store() : null;
   }

   public TrustManagerFactory getTrustManagerFactory(Vertx vertx) throws Exception {
      KeyStoreHelper helper = this.getHelper(vertx);
      return helper != null ? helper.getTrustMgrFactory((VertxInternal)vertx) : null;
   }

   public Function trustManagerMapper(Vertx vertx) throws Exception {
      KeyStoreHelper helper = this.getHelper(vertx);
      return helper != null ? helper::getTrustMgr : null;
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj != null && obj.getClass() == this.getClass()) {
         PemTrustOptions that = (PemTrustOptions)obj;
         return Objects.equals(this.certPaths, that.certPaths) && Objects.equals(this.certValues, that.certValues);
      } else {
         return false;
      }
   }

   public PemTrustOptions copy() {
      return new PemTrustOptions(this);
   }

   KeyStoreHelper getHelper(Vertx vertx) throws Exception {
      if (this.helper == null) {
         Stream var10000 = this.certPaths.stream().map((path) -> ((VertxInternal)vertx).resolveFile(path).getAbsolutePath());
         FileSystem var10001 = vertx.fileSystem();
         var10001.getClass();
         Stream<Buffer> certValues = var10000.map(var10001::readFileBlocking);
         certValues = Stream.concat(certValues, this.certValues.stream());
         this.helper = new KeyStoreHelper(KeyStoreHelper.loadCA(certValues), (String)null, (String)null);
      }

      return this.helper;
   }
}
