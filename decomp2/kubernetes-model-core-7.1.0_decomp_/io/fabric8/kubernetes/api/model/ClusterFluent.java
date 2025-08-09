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
import java.util.function.Predicate;

public class ClusterFluent extends BaseFluent {
   private String certificateAuthority;
   private String certificateAuthorityData;
   private Boolean disableCompression;
   private ArrayList extensions = new ArrayList();
   private Boolean insecureSkipTlsVerify;
   private String proxyUrl;
   private String server;
   private String tlsServerName;
   private Map additionalProperties;

   public ClusterFluent() {
   }

   public ClusterFluent(Cluster instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Cluster instance) {
      instance = instance != null ? instance : new Cluster();
      if (instance != null) {
         this.withCertificateAuthority(instance.getCertificateAuthority());
         this.withCertificateAuthorityData(instance.getCertificateAuthorityData());
         this.withDisableCompression(instance.getDisableCompression());
         this.withExtensions(instance.getExtensions());
         this.withInsecureSkipTlsVerify(instance.getInsecureSkipTlsVerify());
         this.withProxyUrl(instance.getProxyUrl());
         this.withServer(instance.getServer());
         this.withTlsServerName(instance.getTlsServerName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getCertificateAuthority() {
      return this.certificateAuthority;
   }

   public ClusterFluent withCertificateAuthority(String certificateAuthority) {
      this.certificateAuthority = certificateAuthority;
      return this;
   }

   public boolean hasCertificateAuthority() {
      return this.certificateAuthority != null;
   }

   public String getCertificateAuthorityData() {
      return this.certificateAuthorityData;
   }

   public ClusterFluent withCertificateAuthorityData(String certificateAuthorityData) {
      this.certificateAuthorityData = certificateAuthorityData;
      return this;
   }

   public boolean hasCertificateAuthorityData() {
      return this.certificateAuthorityData != null;
   }

   public Boolean getDisableCompression() {
      return this.disableCompression;
   }

   public ClusterFluent withDisableCompression(Boolean disableCompression) {
      this.disableCompression = disableCompression;
      return this;
   }

   public boolean hasDisableCompression() {
      return this.disableCompression != null;
   }

   public ClusterFluent addToExtensions(int index, NamedExtension item) {
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

   public ClusterFluent setToExtensions(int index, NamedExtension item) {
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

   public ClusterFluent addToExtensions(NamedExtension... items) {
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

   public ClusterFluent addAllToExtensions(Collection items) {
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

   public ClusterFluent removeFromExtensions(NamedExtension... items) {
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

   public ClusterFluent removeAllFromExtensions(Collection items) {
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

   public ClusterFluent removeMatchingFromExtensions(Predicate predicate) {
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

   public ClusterFluent withExtensions(List extensions) {
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

   public ClusterFluent withExtensions(NamedExtension... extensions) {
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

   public ClusterFluent addNewExtension(Object extension, String name) {
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

   public Boolean getInsecureSkipTlsVerify() {
      return this.insecureSkipTlsVerify;
   }

   public ClusterFluent withInsecureSkipTlsVerify(Boolean insecureSkipTlsVerify) {
      this.insecureSkipTlsVerify = insecureSkipTlsVerify;
      return this;
   }

   public boolean hasInsecureSkipTlsVerify() {
      return this.insecureSkipTlsVerify != null;
   }

   public String getProxyUrl() {
      return this.proxyUrl;
   }

   public ClusterFluent withProxyUrl(String proxyUrl) {
      this.proxyUrl = proxyUrl;
      return this;
   }

   public boolean hasProxyUrl() {
      return this.proxyUrl != null;
   }

   public String getServer() {
      return this.server;
   }

   public ClusterFluent withServer(String server) {
      this.server = server;
      return this;
   }

   public boolean hasServer() {
      return this.server != null;
   }

   public String getTlsServerName() {
      return this.tlsServerName;
   }

   public ClusterFluent withTlsServerName(String tlsServerName) {
      this.tlsServerName = tlsServerName;
      return this;
   }

   public boolean hasTlsServerName() {
      return this.tlsServerName != null;
   }

   public ClusterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ClusterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ClusterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ClusterFluent removeFromAdditionalProperties(Map map) {
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

   public ClusterFluent withAdditionalProperties(Map additionalProperties) {
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
            ClusterFluent that = (ClusterFluent)o;
            if (!Objects.equals(this.certificateAuthority, that.certificateAuthority)) {
               return false;
            } else if (!Objects.equals(this.certificateAuthorityData, that.certificateAuthorityData)) {
               return false;
            } else if (!Objects.equals(this.disableCompression, that.disableCompression)) {
               return false;
            } else if (!Objects.equals(this.extensions, that.extensions)) {
               return false;
            } else if (!Objects.equals(this.insecureSkipTlsVerify, that.insecureSkipTlsVerify)) {
               return false;
            } else if (!Objects.equals(this.proxyUrl, that.proxyUrl)) {
               return false;
            } else if (!Objects.equals(this.server, that.server)) {
               return false;
            } else if (!Objects.equals(this.tlsServerName, that.tlsServerName)) {
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
      return Objects.hash(new Object[]{this.certificateAuthority, this.certificateAuthorityData, this.disableCompression, this.extensions, this.insecureSkipTlsVerify, this.proxyUrl, this.server, this.tlsServerName, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.certificateAuthority != null) {
         sb.append("certificateAuthority:");
         sb.append(this.certificateAuthority + ",");
      }

      if (this.certificateAuthorityData != null) {
         sb.append("certificateAuthorityData:");
         sb.append(this.certificateAuthorityData + ",");
      }

      if (this.disableCompression != null) {
         sb.append("disableCompression:");
         sb.append(this.disableCompression + ",");
      }

      if (this.extensions != null && !this.extensions.isEmpty()) {
         sb.append("extensions:");
         sb.append(this.extensions + ",");
      }

      if (this.insecureSkipTlsVerify != null) {
         sb.append("insecureSkipTlsVerify:");
         sb.append(this.insecureSkipTlsVerify + ",");
      }

      if (this.proxyUrl != null) {
         sb.append("proxyUrl:");
         sb.append(this.proxyUrl + ",");
      }

      if (this.server != null) {
         sb.append("server:");
         sb.append(this.server + ",");
      }

      if (this.tlsServerName != null) {
         sb.append("tlsServerName:");
         sb.append(this.tlsServerName + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ClusterFluent withDisableCompression() {
      return this.withDisableCompression(true);
   }

   public ClusterFluent withInsecureSkipTlsVerify() {
      return this.withInsecureSkipTlsVerify(true);
   }

   public class ExtensionsNested extends NamedExtensionFluent implements Nested {
      NamedExtensionBuilder builder;
      int index;

      ExtensionsNested(int index, NamedExtension item) {
         this.index = index;
         this.builder = new NamedExtensionBuilder(this, item);
      }

      public Object and() {
         return ClusterFluent.this.setToExtensions(this.index, this.builder.build());
      }

      public Object endExtension() {
         return this.and();
      }
   }
}
