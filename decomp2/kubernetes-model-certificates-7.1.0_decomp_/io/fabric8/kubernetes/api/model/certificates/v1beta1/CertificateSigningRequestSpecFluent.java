package io.fabric8.kubernetes.api.model.certificates.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class CertificateSigningRequestSpecFluent extends BaseFluent {
   private Map extra;
   private List groups = new ArrayList();
   private String request;
   private String signerName;
   private String uid;
   private List usages = new ArrayList();
   private String username;
   private Map additionalProperties;

   public CertificateSigningRequestSpecFluent() {
   }

   public CertificateSigningRequestSpecFluent(CertificateSigningRequestSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CertificateSigningRequestSpec instance) {
      instance = instance != null ? instance : new CertificateSigningRequestSpec();
      if (instance != null) {
         this.withExtra(instance.getExtra());
         this.withGroups(instance.getGroups());
         this.withRequest(instance.getRequest());
         this.withSignerName(instance.getSignerName());
         this.withUid(instance.getUid());
         this.withUsages(instance.getUsages());
         this.withUsername(instance.getUsername());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public CertificateSigningRequestSpecFluent addToExtra(String key, List value) {
      if (this.extra == null && key != null && value != null) {
         this.extra = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.extra.put(key, value);
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent addToExtra(Map map) {
      if (this.extra == null && map != null) {
         this.extra = new LinkedHashMap();
      }

      if (map != null) {
         this.extra.putAll(map);
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent removeFromExtra(String key) {
      if (this.extra == null) {
         return this;
      } else {
         if (key != null && this.extra != null) {
            this.extra.remove(key);
         }

         return this;
      }
   }

   public CertificateSigningRequestSpecFluent removeFromExtra(Map map) {
      if (this.extra == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.extra != null) {
                  this.extra.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getExtra() {
      return this.extra;
   }

   public CertificateSigningRequestSpecFluent withExtra(Map extra) {
      if (extra == null) {
         this.extra = null;
      } else {
         this.extra = new LinkedHashMap(extra);
      }

      return this;
   }

   public boolean hasExtra() {
      return this.extra != null;
   }

   public CertificateSigningRequestSpecFluent addToGroups(int index, String item) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      this.groups.add(index, item);
      return this;
   }

   public CertificateSigningRequestSpecFluent setToGroups(int index, String item) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      this.groups.set(index, item);
      return this;
   }

   public CertificateSigningRequestSpecFluent addToGroups(String... items) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      for(String item : items) {
         this.groups.add(item);
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent addAllToGroups(Collection items) {
      if (this.groups == null) {
         this.groups = new ArrayList();
      }

      for(String item : items) {
         this.groups.add(item);
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent removeFromGroups(String... items) {
      if (this.groups == null) {
         return this;
      } else {
         for(String item : items) {
            this.groups.remove(item);
         }

         return this;
      }
   }

   public CertificateSigningRequestSpecFluent removeAllFromGroups(Collection items) {
      if (this.groups == null) {
         return this;
      } else {
         for(String item : items) {
            this.groups.remove(item);
         }

         return this;
      }
   }

   public List getGroups() {
      return this.groups;
   }

   public String getGroup(int index) {
      return (String)this.groups.get(index);
   }

   public String getFirstGroup() {
      return (String)this.groups.get(0);
   }

   public String getLastGroup() {
      return (String)this.groups.get(this.groups.size() - 1);
   }

   public String getMatchingGroup(Predicate predicate) {
      for(String item : this.groups) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingGroup(Predicate predicate) {
      for(String item : this.groups) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CertificateSigningRequestSpecFluent withGroups(List groups) {
      if (groups != null) {
         this.groups = new ArrayList();

         for(String item : groups) {
            this.addToGroups(item);
         }
      } else {
         this.groups = null;
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent withGroups(String... groups) {
      if (this.groups != null) {
         this.groups.clear();
         this._visitables.remove("groups");
      }

      if (groups != null) {
         for(String item : groups) {
            this.addToGroups(item);
         }
      }

      return this;
   }

   public boolean hasGroups() {
      return this.groups != null && !this.groups.isEmpty();
   }

   public String getRequest() {
      return this.request;
   }

   public CertificateSigningRequestSpecFluent withRequest(String request) {
      this.request = request;
      return this;
   }

   public boolean hasRequest() {
      return this.request != null;
   }

   public String getSignerName() {
      return this.signerName;
   }

   public CertificateSigningRequestSpecFluent withSignerName(String signerName) {
      this.signerName = signerName;
      return this;
   }

   public boolean hasSignerName() {
      return this.signerName != null;
   }

   public String getUid() {
      return this.uid;
   }

   public CertificateSigningRequestSpecFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public CertificateSigningRequestSpecFluent addToUsages(int index, String item) {
      if (this.usages == null) {
         this.usages = new ArrayList();
      }

      this.usages.add(index, item);
      return this;
   }

   public CertificateSigningRequestSpecFluent setToUsages(int index, String item) {
      if (this.usages == null) {
         this.usages = new ArrayList();
      }

      this.usages.set(index, item);
      return this;
   }

   public CertificateSigningRequestSpecFluent addToUsages(String... items) {
      if (this.usages == null) {
         this.usages = new ArrayList();
      }

      for(String item : items) {
         this.usages.add(item);
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent addAllToUsages(Collection items) {
      if (this.usages == null) {
         this.usages = new ArrayList();
      }

      for(String item : items) {
         this.usages.add(item);
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent removeFromUsages(String... items) {
      if (this.usages == null) {
         return this;
      } else {
         for(String item : items) {
            this.usages.remove(item);
         }

         return this;
      }
   }

   public CertificateSigningRequestSpecFluent removeAllFromUsages(Collection items) {
      if (this.usages == null) {
         return this;
      } else {
         for(String item : items) {
            this.usages.remove(item);
         }

         return this;
      }
   }

   public List getUsages() {
      return this.usages;
   }

   public String getUsage(int index) {
      return (String)this.usages.get(index);
   }

   public String getFirstUsage() {
      return (String)this.usages.get(0);
   }

   public String getLastUsage() {
      return (String)this.usages.get(this.usages.size() - 1);
   }

   public String getMatchingUsage(Predicate predicate) {
      for(String item : this.usages) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingUsage(Predicate predicate) {
      for(String item : this.usages) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CertificateSigningRequestSpecFluent withUsages(List usages) {
      if (usages != null) {
         this.usages = new ArrayList();

         for(String item : usages) {
            this.addToUsages(item);
         }
      } else {
         this.usages = null;
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent withUsages(String... usages) {
      if (this.usages != null) {
         this.usages.clear();
         this._visitables.remove("usages");
      }

      if (usages != null) {
         for(String item : usages) {
            this.addToUsages(item);
         }
      }

      return this;
   }

   public boolean hasUsages() {
      return this.usages != null && !this.usages.isEmpty();
   }

   public String getUsername() {
      return this.username;
   }

   public CertificateSigningRequestSpecFluent withUsername(String username) {
      this.username = username;
      return this;
   }

   public boolean hasUsername() {
      return this.username != null;
   }

   public CertificateSigningRequestSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CertificateSigningRequestSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CertificateSigningRequestSpecFluent removeFromAdditionalProperties(Map map) {
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

   public CertificateSigningRequestSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            CertificateSigningRequestSpecFluent that = (CertificateSigningRequestSpecFluent)o;
            if (!Objects.equals(this.extra, that.extra)) {
               return false;
            } else if (!Objects.equals(this.groups, that.groups)) {
               return false;
            } else if (!Objects.equals(this.request, that.request)) {
               return false;
            } else if (!Objects.equals(this.signerName, that.signerName)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
               return false;
            } else if (!Objects.equals(this.usages, that.usages)) {
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
      return Objects.hash(new Object[]{this.extra, this.groups, this.request, this.signerName, this.uid, this.usages, this.username, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.extra != null && !this.extra.isEmpty()) {
         sb.append("extra:");
         sb.append(this.extra + ",");
      }

      if (this.groups != null && !this.groups.isEmpty()) {
         sb.append("groups:");
         sb.append(this.groups + ",");
      }

      if (this.request != null) {
         sb.append("request:");
         sb.append(this.request + ",");
      }

      if (this.signerName != null) {
         sb.append("signerName:");
         sb.append(this.signerName + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.usages != null && !this.usages.isEmpty()) {
         sb.append("usages:");
         sb.append(this.usages + ",");
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
}
