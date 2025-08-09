package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class AuthInfoFluent extends BaseFluent {
   private String as;
   private List asGroups = new ArrayList();
   private String asUid;
   private Map asUserExtra;
   private AuthProviderConfigBuilder authProvider;
   private String clientCertificate;
   private String clientCertificateData;
   private String clientKey;
   private String clientKeyData;
   private ExecConfigBuilder exec;
   private ArrayList extensions = new ArrayList();
   private String password;
   private String token;
   private String tokenFile;
   private String username;
   private Map additionalProperties;

   public AuthInfoFluent() {
   }

   public AuthInfoFluent(AuthInfo instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(AuthInfo instance) {
      instance = instance != null ? instance : new AuthInfo();
      if (instance != null) {
         this.withAs(instance.getAs());
         this.withAsGroups(instance.getAsGroups());
         this.withAsUid(instance.getAsUid());
         this.withAsUserExtra(instance.getAsUserExtra());
         this.withAuthProvider(instance.getAuthProvider());
         this.withClientCertificate(instance.getClientCertificate());
         this.withClientCertificateData(instance.getClientCertificateData());
         this.withClientKey(instance.getClientKey());
         this.withClientKeyData(instance.getClientKeyData());
         this.withExec(instance.getExec());
         this.withExtensions(instance.getExtensions());
         this.withPassword(instance.getPassword());
         this.withToken(instance.getToken());
         this.withTokenFile(instance.getTokenFile());
         this.withUsername(instance.getUsername());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAs() {
      return this.as;
   }

   public AuthInfoFluent withAs(String as) {
      this.as = as;
      return this;
   }

   public boolean hasAs() {
      return this.as != null;
   }

   public AuthInfoFluent addToAsGroups(int index, String item) {
      if (this.asGroups == null) {
         this.asGroups = new ArrayList();
      }

      this.asGroups.add(index, item);
      return this;
   }

   public AuthInfoFluent setToAsGroups(int index, String item) {
      if (this.asGroups == null) {
         this.asGroups = new ArrayList();
      }

      this.asGroups.set(index, item);
      return this;
   }

   public AuthInfoFluent addToAsGroups(String... items) {
      if (this.asGroups == null) {
         this.asGroups = new ArrayList();
      }

      for(String item : items) {
         this.asGroups.add(item);
      }

      return this;
   }

   public AuthInfoFluent addAllToAsGroups(Collection items) {
      if (this.asGroups == null) {
         this.asGroups = new ArrayList();
      }

      for(String item : items) {
         this.asGroups.add(item);
      }

      return this;
   }

   public AuthInfoFluent removeFromAsGroups(String... items) {
      if (this.asGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.asGroups.remove(item);
         }

         return this;
      }
   }

   public AuthInfoFluent removeAllFromAsGroups(Collection items) {
      if (this.asGroups == null) {
         return this;
      } else {
         for(String item : items) {
            this.asGroups.remove(item);
         }

         return this;
      }
   }

   public List getAsGroups() {
      return this.asGroups;
   }

   public String getAsGroup(int index) {
      return (String)this.asGroups.get(index);
   }

   public String getFirstAsGroup() {
      return (String)this.asGroups.get(0);
   }

   public String getLastAsGroup() {
      return (String)this.asGroups.get(this.asGroups.size() - 1);
   }

   public String getMatchingAsGroup(Predicate predicate) {
      for(String item : this.asGroups) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAsGroup(Predicate predicate) {
      for(String item : this.asGroups) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public AuthInfoFluent withAsGroups(List asGroups) {
      if (asGroups != null) {
         this.asGroups = new ArrayList();

         for(String item : asGroups) {
            this.addToAsGroups(item);
         }
      } else {
         this.asGroups = null;
      }

      return this;
   }

   public AuthInfoFluent withAsGroups(String... asGroups) {
      if (this.asGroups != null) {
         this.asGroups.clear();
         this._visitables.remove("asGroups");
      }

      if (asGroups != null) {
         for(String item : asGroups) {
            this.addToAsGroups(item);
         }
      }

      return this;
   }

   public boolean hasAsGroups() {
      return this.asGroups != null && !this.asGroups.isEmpty();
   }

   public String getAsUid() {
      return this.asUid;
   }

   public AuthInfoFluent withAsUid(String asUid) {
      this.asUid = asUid;
      return this;
   }

   public boolean hasAsUid() {
      return this.asUid != null;
   }

   public AuthInfoFluent addToAsUserExtra(String key, List value) {
      if (this.asUserExtra == null && key != null && value != null) {
         this.asUserExtra = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.asUserExtra.put(key, value);
      }

      return this;
   }

   public AuthInfoFluent addToAsUserExtra(Map map) {
      if (this.asUserExtra == null && map != null) {
         this.asUserExtra = new LinkedHashMap();
      }

      if (map != null) {
         this.asUserExtra.putAll(map);
      }

      return this;
   }

   public AuthInfoFluent removeFromAsUserExtra(String key) {
      if (this.asUserExtra == null) {
         return this;
      } else {
         if (key != null && this.asUserExtra != null) {
            this.asUserExtra.remove(key);
         }

         return this;
      }
   }

   public AuthInfoFluent removeFromAsUserExtra(Map map) {
      if (this.asUserExtra == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.asUserExtra != null) {
                  this.asUserExtra.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAsUserExtra() {
      return this.asUserExtra;
   }

   public AuthInfoFluent withAsUserExtra(Map asUserExtra) {
      if (asUserExtra == null) {
         this.asUserExtra = null;
      } else {
         this.asUserExtra = new LinkedHashMap(asUserExtra);
      }

      return this;
   }

   public boolean hasAsUserExtra() {
      return this.asUserExtra != null;
   }

   public AuthProviderConfig buildAuthProvider() {
      return this.authProvider != null ? this.authProvider.build() : null;
   }

   public AuthInfoFluent withAuthProvider(AuthProviderConfig authProvider) {
      this._visitables.remove("authProvider");
      if (authProvider != null) {
         this.authProvider = new AuthProviderConfigBuilder(authProvider);
         this._visitables.get("authProvider").add(this.authProvider);
      } else {
         this.authProvider = null;
         this._visitables.get("authProvider").remove(this.authProvider);
      }

      return this;
   }

   public boolean hasAuthProvider() {
      return this.authProvider != null;
   }

   public AuthProviderNested withNewAuthProvider() {
      return new AuthProviderNested((AuthProviderConfig)null);
   }

   public AuthProviderNested withNewAuthProviderLike(AuthProviderConfig item) {
      return new AuthProviderNested(item);
   }

   public AuthProviderNested editAuthProvider() {
      return this.withNewAuthProviderLike((AuthProviderConfig)Optional.ofNullable(this.buildAuthProvider()).orElse((Object)null));
   }

   public AuthProviderNested editOrNewAuthProvider() {
      return this.withNewAuthProviderLike((AuthProviderConfig)Optional.ofNullable(this.buildAuthProvider()).orElse((new AuthProviderConfigBuilder()).build()));
   }

   public AuthProviderNested editOrNewAuthProviderLike(AuthProviderConfig item) {
      return this.withNewAuthProviderLike((AuthProviderConfig)Optional.ofNullable(this.buildAuthProvider()).orElse(item));
   }

   public String getClientCertificate() {
      return this.clientCertificate;
   }

   public AuthInfoFluent withClientCertificate(String clientCertificate) {
      this.clientCertificate = clientCertificate;
      return this;
   }

   public boolean hasClientCertificate() {
      return this.clientCertificate != null;
   }

   public String getClientCertificateData() {
      return this.clientCertificateData;
   }

   public AuthInfoFluent withClientCertificateData(String clientCertificateData) {
      this.clientCertificateData = clientCertificateData;
      return this;
   }

   public boolean hasClientCertificateData() {
      return this.clientCertificateData != null;
   }

   public String getClientKey() {
      return this.clientKey;
   }

   public AuthInfoFluent withClientKey(String clientKey) {
      this.clientKey = clientKey;
      return this;
   }

   public boolean hasClientKey() {
      return this.clientKey != null;
   }

   public String getClientKeyData() {
      return this.clientKeyData;
   }

   public AuthInfoFluent withClientKeyData(String clientKeyData) {
      this.clientKeyData = clientKeyData;
      return this;
   }

   public boolean hasClientKeyData() {
      return this.clientKeyData != null;
   }

   public ExecConfig buildExec() {
      return this.exec != null ? this.exec.build() : null;
   }

   public AuthInfoFluent withExec(ExecConfig exec) {
      this._visitables.remove("exec");
      if (exec != null) {
         this.exec = new ExecConfigBuilder(exec);
         this._visitables.get("exec").add(this.exec);
      } else {
         this.exec = null;
         this._visitables.get("exec").remove(this.exec);
      }

      return this;
   }

   public boolean hasExec() {
      return this.exec != null;
   }

   public ExecNested withNewExec() {
      return new ExecNested((ExecConfig)null);
   }

   public ExecNested withNewExecLike(ExecConfig item) {
      return new ExecNested(item);
   }

   public ExecNested editExec() {
      return this.withNewExecLike((ExecConfig)Optional.ofNullable(this.buildExec()).orElse((Object)null));
   }

   public ExecNested editOrNewExec() {
      return this.withNewExecLike((ExecConfig)Optional.ofNullable(this.buildExec()).orElse((new ExecConfigBuilder()).build()));
   }

   public ExecNested editOrNewExecLike(ExecConfig item) {
      return this.withNewExecLike((ExecConfig)Optional.ofNullable(this.buildExec()).orElse(item));
   }

   public AuthInfoFluent addToExtensions(int index, NamedExtension item) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
      if (index >= 0 && index < this.extensions.size()) {
         this._visitables.get("extensions").add(index, builder);
         this.extensions.add(index, builder);
      } else {
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public AuthInfoFluent setToExtensions(int index, NamedExtension item) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
      if (index >= 0 && index < this.extensions.size()) {
         this._visitables.get("extensions").set(index, builder);
         this.extensions.set(index, builder);
      } else {
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public AuthInfoFluent addToExtensions(NamedExtension... items) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      for(NamedExtension item : items) {
         NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public AuthInfoFluent addAllToExtensions(Collection items) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      for(NamedExtension item : items) {
         NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public AuthInfoFluent removeFromExtensions(NamedExtension... items) {
      if (this.extensions == null) {
         return this;
      } else {
         for(NamedExtension item : items) {
            NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
            this._visitables.get("extensions").remove(builder);
            this.extensions.remove(builder);
         }

         return this;
      }
   }

   public AuthInfoFluent removeAllFromExtensions(Collection items) {
      if (this.extensions == null) {
         return this;
      } else {
         for(NamedExtension item : items) {
            NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
            this._visitables.get("extensions").remove(builder);
            this.extensions.remove(builder);
         }

         return this;
      }
   }

   public AuthInfoFluent removeMatchingFromExtensions(Predicate predicate) {
      if (this.extensions == null) {
         return this;
      } else {
         Iterator<NamedExtensionBuilder> each = this.extensions.iterator();
         List visitables = this._visitables.get("extensions");

         while(each.hasNext()) {
            NamedExtensionBuilder builder = (NamedExtensionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildExtensions() {
      return this.extensions != null ? build(this.extensions) : null;
   }

   public NamedExtension buildExtension(int index) {
      return ((NamedExtensionBuilder)this.extensions.get(index)).build();
   }

   public NamedExtension buildFirstExtension() {
      return ((NamedExtensionBuilder)this.extensions.get(0)).build();
   }

   public NamedExtension buildLastExtension() {
      return ((NamedExtensionBuilder)this.extensions.get(this.extensions.size() - 1)).build();
   }

   public NamedExtension buildMatchingExtension(Predicate predicate) {
      for(NamedExtensionBuilder item : this.extensions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingExtension(Predicate predicate) {
      for(NamedExtensionBuilder item : this.extensions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public AuthInfoFluent withExtensions(List extensions) {
      if (this.extensions != null) {
         this._visitables.get("extensions").clear();
      }

      if (extensions != null) {
         this.extensions = new ArrayList();

         for(NamedExtension item : extensions) {
            this.addToExtensions(item);
         }
      } else {
         this.extensions = null;
      }

      return this;
   }

   public AuthInfoFluent withExtensions(NamedExtension... extensions) {
      if (this.extensions != null) {
         this.extensions.clear();
         this._visitables.remove("extensions");
      }

      if (extensions != null) {
         for(NamedExtension item : extensions) {
            this.addToExtensions(item);
         }
      }

      return this;
   }

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public AuthInfoFluent addNewExtension(Object extension, String name) {
      return this.addToExtensions(new NamedExtension(extension, name));
   }

   public ExtensionsNested addNewExtension() {
      return new ExtensionsNested(-1, (NamedExtension)null);
   }

   public ExtensionsNested addNewExtensionLike(NamedExtension item) {
      return new ExtensionsNested(-1, item);
   }

   public ExtensionsNested setNewExtensionLike(int index, NamedExtension item) {
      return new ExtensionsNested(index, item);
   }

   public ExtensionsNested editExtension(int index) {
      if (this.extensions.size() <= index) {
         throw new RuntimeException("Can't edit extensions. Index exceeds size.");
      } else {
         return this.setNewExtensionLike(index, this.buildExtension(index));
      }
   }

   public ExtensionsNested editFirstExtension() {
      if (this.extensions.size() == 0) {
         throw new RuntimeException("Can't edit first extensions. The list is empty.");
      } else {
         return this.setNewExtensionLike(0, this.buildExtension(0));
      }
   }

   public ExtensionsNested editLastExtension() {
      int index = this.extensions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last extensions. The list is empty.");
      } else {
         return this.setNewExtensionLike(index, this.buildExtension(index));
      }
   }

   public ExtensionsNested editMatchingExtension(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.extensions.size(); ++i) {
         if (predicate.test((NamedExtensionBuilder)this.extensions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching extensions. No match found.");
      } else {
         return this.setNewExtensionLike(index, this.buildExtension(index));
      }
   }

   public String getPassword() {
      return this.password;
   }

   public AuthInfoFluent withPassword(String password) {
      this.password = password;
      return this;
   }

   public boolean hasPassword() {
      return this.password != null;
   }

   public String getToken() {
      return this.token;
   }

   public AuthInfoFluent withToken(String token) {
      this.token = token;
      return this;
   }

   public boolean hasToken() {
      return this.token != null;
   }

   public String getTokenFile() {
      return this.tokenFile;
   }

   public AuthInfoFluent withTokenFile(String tokenFile) {
      this.tokenFile = tokenFile;
      return this;
   }

   public boolean hasTokenFile() {
      return this.tokenFile != null;
   }

   public String getUsername() {
      return this.username;
   }

   public AuthInfoFluent withUsername(String username) {
      this.username = username;
      return this;
   }

   public boolean hasUsername() {
      return this.username != null;
   }

   public AuthInfoFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public AuthInfoFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public AuthInfoFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public AuthInfoFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public AuthInfoFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            AuthInfoFluent that = (AuthInfoFluent)o;
            if (!Objects.equals(this.as, that.as)) {
               return false;
            } else if (!Objects.equals(this.asGroups, that.asGroups)) {
               return false;
            } else if (!Objects.equals(this.asUid, that.asUid)) {
               return false;
            } else if (!Objects.equals(this.asUserExtra, that.asUserExtra)) {
               return false;
            } else if (!Objects.equals(this.authProvider, that.authProvider)) {
               return false;
            } else if (!Objects.equals(this.clientCertificate, that.clientCertificate)) {
               return false;
            } else if (!Objects.equals(this.clientCertificateData, that.clientCertificateData)) {
               return false;
            } else if (!Objects.equals(this.clientKey, that.clientKey)) {
               return false;
            } else if (!Objects.equals(this.clientKeyData, that.clientKeyData)) {
               return false;
            } else if (!Objects.equals(this.exec, that.exec)) {
               return false;
            } else if (!Objects.equals(this.extensions, that.extensions)) {
               return false;
            } else if (!Objects.equals(this.password, that.password)) {
               return false;
            } else if (!Objects.equals(this.token, that.token)) {
               return false;
            } else if (!Objects.equals(this.tokenFile, that.tokenFile)) {
               return false;
            } else if (!Objects.equals(this.username, that.username)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.as, this.asGroups, this.asUid, this.asUserExtra, this.authProvider, this.clientCertificate, this.clientCertificateData, this.clientKey, this.clientKeyData, this.exec, this.extensions, this.password, this.token, this.tokenFile, this.username, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.as != null) {
         sb.append("as:");
         sb.append(this.as + ",");
      }

      if (this.asGroups != null && !this.asGroups.isEmpty()) {
         sb.append("asGroups:");
         sb.append(this.asGroups + ",");
      }

      if (this.asUid != null) {
         sb.append("asUid:");
         sb.append(this.asUid + ",");
      }

      if (this.asUserExtra != null && !this.asUserExtra.isEmpty()) {
         sb.append("asUserExtra:");
         sb.append(this.asUserExtra + ",");
      }

      if (this.authProvider != null) {
         sb.append("authProvider:");
         sb.append(this.authProvider + ",");
      }

      if (this.clientCertificate != null) {
         sb.append("clientCertificate:");
         sb.append(this.clientCertificate + ",");
      }

      if (this.clientCertificateData != null) {
         sb.append("clientCertificateData:");
         sb.append(this.clientCertificateData + ",");
      }

      if (this.clientKey != null) {
         sb.append("clientKey:");
         sb.append(this.clientKey + ",");
      }

      if (this.clientKeyData != null) {
         sb.append("clientKeyData:");
         sb.append(this.clientKeyData + ",");
      }

      if (this.exec != null) {
         sb.append("exec:");
         sb.append(this.exec + ",");
      }

      if (this.extensions != null && !this.extensions.isEmpty()) {
         sb.append("extensions:");
         sb.append(this.extensions + ",");
      }

      if (this.password != null) {
         sb.append("password:");
         sb.append(this.password + ",");
      }

      if (this.token != null) {
         sb.append("token:");
         sb.append(this.token + ",");
      }

      if (this.tokenFile != null) {
         sb.append("tokenFile:");
         sb.append(this.tokenFile + ",");
      }

      if (this.username != null) {
         sb.append("username:");
         sb.append(this.username + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AuthProviderNested extends AuthProviderConfigFluent implements Nested {
      AuthProviderConfigBuilder builder;

      AuthProviderNested(AuthProviderConfig item) {
         this.builder = new AuthProviderConfigBuilder(this, item);
      }

      public Object and() {
         return AuthInfoFluent.this.withAuthProvider(this.builder.build());
      }

      public Object endAuthProvider() {
         return this.and();
      }
   }

   public class ExecNested extends ExecConfigFluent implements Nested {
      ExecConfigBuilder builder;

      ExecNested(ExecConfig item) {
         this.builder = new ExecConfigBuilder(this, item);
      }

      public Object and() {
         return AuthInfoFluent.this.withExec(this.builder.build());
      }

      public Object endExec() {
         return this.and();
      }
   }

   public class ExtensionsNested extends NamedExtensionFluent implements Nested {
      NamedExtensionBuilder builder;
      int index;

      ExtensionsNested(int index, NamedExtension item) {
         this.index = index;
         this.builder = new NamedExtensionBuilder(this, item);
      }

      public Object and() {
         return AuthInfoFluent.this.setToExtensions(this.index, this.builder.build());
      }

      public Object endExtension() {
         return this.and();
      }
   }
}
