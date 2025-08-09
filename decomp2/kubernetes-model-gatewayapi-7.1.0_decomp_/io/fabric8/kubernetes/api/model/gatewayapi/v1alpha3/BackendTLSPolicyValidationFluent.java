package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.LocalObjectReference;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.LocalObjectReferenceBuilder;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.LocalObjectReferenceFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class BackendTLSPolicyValidationFluent extends BaseFluent {
   private ArrayList caCertificateRefs = new ArrayList();
   private String hostname;
   private ArrayList subjectAltNames = new ArrayList();
   private String wellKnownCACertificates;
   private Map additionalProperties;

   public BackendTLSPolicyValidationFluent() {
   }

   public BackendTLSPolicyValidationFluent(BackendTLSPolicyValidation instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(BackendTLSPolicyValidation instance) {
      instance = instance != null ? instance : new BackendTLSPolicyValidation();
      if (instance != null) {
         this.withCaCertificateRefs(instance.getCaCertificateRefs());
         this.withHostname(instance.getHostname());
         this.withSubjectAltNames(instance.getSubjectAltNames());
         this.withWellKnownCACertificates(instance.getWellKnownCACertificates());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public BackendTLSPolicyValidationFluent addToCaCertificateRefs(int index, LocalObjectReference item) {
      if (this.caCertificateRefs == null) {
         this.caCertificateRefs = new ArrayList();
      }

      LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
      if (index >= 0 && index < this.caCertificateRefs.size()) {
         this._visitables.get("caCertificateRefs").add(index, builder);
         this.caCertificateRefs.add(index, builder);
      } else {
         this._visitables.get("caCertificateRefs").add(builder);
         this.caCertificateRefs.add(builder);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent setToCaCertificateRefs(int index, LocalObjectReference item) {
      if (this.caCertificateRefs == null) {
         this.caCertificateRefs = new ArrayList();
      }

      LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
      if (index >= 0 && index < this.caCertificateRefs.size()) {
         this._visitables.get("caCertificateRefs").set(index, builder);
         this.caCertificateRefs.set(index, builder);
      } else {
         this._visitables.get("caCertificateRefs").add(builder);
         this.caCertificateRefs.add(builder);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent addToCaCertificateRefs(LocalObjectReference... items) {
      if (this.caCertificateRefs == null) {
         this.caCertificateRefs = new ArrayList();
      }

      for(LocalObjectReference item : items) {
         LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
         this._visitables.get("caCertificateRefs").add(builder);
         this.caCertificateRefs.add(builder);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent addAllToCaCertificateRefs(Collection items) {
      if (this.caCertificateRefs == null) {
         this.caCertificateRefs = new ArrayList();
      }

      for(LocalObjectReference item : items) {
         LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
         this._visitables.get("caCertificateRefs").add(builder);
         this.caCertificateRefs.add(builder);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent removeFromCaCertificateRefs(LocalObjectReference... items) {
      if (this.caCertificateRefs == null) {
         return this;
      } else {
         for(LocalObjectReference item : items) {
            LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
            this._visitables.get("caCertificateRefs").remove(builder);
            this.caCertificateRefs.remove(builder);
         }

         return this;
      }
   }

   public BackendTLSPolicyValidationFluent removeAllFromCaCertificateRefs(Collection items) {
      if (this.caCertificateRefs == null) {
         return this;
      } else {
         for(LocalObjectReference item : items) {
            LocalObjectReferenceBuilder builder = new LocalObjectReferenceBuilder(item);
            this._visitables.get("caCertificateRefs").remove(builder);
            this.caCertificateRefs.remove(builder);
         }

         return this;
      }
   }

   public BackendTLSPolicyValidationFluent removeMatchingFromCaCertificateRefs(Predicate predicate) {
      if (this.caCertificateRefs == null) {
         return this;
      } else {
         Iterator<LocalObjectReferenceBuilder> each = this.caCertificateRefs.iterator();
         List visitables = this._visitables.get("caCertificateRefs");

         while(each.hasNext()) {
            LocalObjectReferenceBuilder builder = (LocalObjectReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildCaCertificateRefs() {
      return this.caCertificateRefs != null ? build(this.caCertificateRefs) : null;
   }

   public LocalObjectReference buildCaCertificateRef(int index) {
      return ((LocalObjectReferenceBuilder)this.caCertificateRefs.get(index)).build();
   }

   public LocalObjectReference buildFirstCaCertificateRef() {
      return ((LocalObjectReferenceBuilder)this.caCertificateRefs.get(0)).build();
   }

   public LocalObjectReference buildLastCaCertificateRef() {
      return ((LocalObjectReferenceBuilder)this.caCertificateRefs.get(this.caCertificateRefs.size() - 1)).build();
   }

   public LocalObjectReference buildMatchingCaCertificateRef(Predicate predicate) {
      for(LocalObjectReferenceBuilder item : this.caCertificateRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCaCertificateRef(Predicate predicate) {
      for(LocalObjectReferenceBuilder item : this.caCertificateRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public BackendTLSPolicyValidationFluent withCaCertificateRefs(List caCertificateRefs) {
      if (this.caCertificateRefs != null) {
         this._visitables.get("caCertificateRefs").clear();
      }

      if (caCertificateRefs != null) {
         this.caCertificateRefs = new ArrayList();

         for(LocalObjectReference item : caCertificateRefs) {
            this.addToCaCertificateRefs(item);
         }
      } else {
         this.caCertificateRefs = null;
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent withCaCertificateRefs(LocalObjectReference... caCertificateRefs) {
      if (this.caCertificateRefs != null) {
         this.caCertificateRefs.clear();
         this._visitables.remove("caCertificateRefs");
      }

      if (caCertificateRefs != null) {
         for(LocalObjectReference item : caCertificateRefs) {
            this.addToCaCertificateRefs(item);
         }
      }

      return this;
   }

   public boolean hasCaCertificateRefs() {
      return this.caCertificateRefs != null && !this.caCertificateRefs.isEmpty();
   }

   public BackendTLSPolicyValidationFluent addNewCaCertificateRef(String group, String kind, String name) {
      return this.addToCaCertificateRefs(new LocalObjectReference(group, kind, name));
   }

   public CaCertificateRefsNested addNewCaCertificateRef() {
      return new CaCertificateRefsNested(-1, (LocalObjectReference)null);
   }

   public CaCertificateRefsNested addNewCaCertificateRefLike(LocalObjectReference item) {
      return new CaCertificateRefsNested(-1, item);
   }

   public CaCertificateRefsNested setNewCaCertificateRefLike(int index, LocalObjectReference item) {
      return new CaCertificateRefsNested(index, item);
   }

   public CaCertificateRefsNested editCaCertificateRef(int index) {
      if (this.caCertificateRefs.size() <= index) {
         throw new RuntimeException("Can't edit caCertificateRefs. Index exceeds size.");
      } else {
         return this.setNewCaCertificateRefLike(index, this.buildCaCertificateRef(index));
      }
   }

   public CaCertificateRefsNested editFirstCaCertificateRef() {
      if (this.caCertificateRefs.size() == 0) {
         throw new RuntimeException("Can't edit first caCertificateRefs. The list is empty.");
      } else {
         return this.setNewCaCertificateRefLike(0, this.buildCaCertificateRef(0));
      }
   }

   public CaCertificateRefsNested editLastCaCertificateRef() {
      int index = this.caCertificateRefs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last caCertificateRefs. The list is empty.");
      } else {
         return this.setNewCaCertificateRefLike(index, this.buildCaCertificateRef(index));
      }
   }

   public CaCertificateRefsNested editMatchingCaCertificateRef(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.caCertificateRefs.size(); ++i) {
         if (predicate.test((LocalObjectReferenceBuilder)this.caCertificateRefs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching caCertificateRefs. No match found.");
      } else {
         return this.setNewCaCertificateRefLike(index, this.buildCaCertificateRef(index));
      }
   }

   public String getHostname() {
      return this.hostname;
   }

   public BackendTLSPolicyValidationFluent withHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public boolean hasHostname() {
      return this.hostname != null;
   }

   public BackendTLSPolicyValidationFluent addToSubjectAltNames(int index, SubjectAltName item) {
      if (this.subjectAltNames == null) {
         this.subjectAltNames = new ArrayList();
      }

      SubjectAltNameBuilder builder = new SubjectAltNameBuilder(item);
      if (index >= 0 && index < this.subjectAltNames.size()) {
         this._visitables.get("subjectAltNames").add(index, builder);
         this.subjectAltNames.add(index, builder);
      } else {
         this._visitables.get("subjectAltNames").add(builder);
         this.subjectAltNames.add(builder);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent setToSubjectAltNames(int index, SubjectAltName item) {
      if (this.subjectAltNames == null) {
         this.subjectAltNames = new ArrayList();
      }

      SubjectAltNameBuilder builder = new SubjectAltNameBuilder(item);
      if (index >= 0 && index < this.subjectAltNames.size()) {
         this._visitables.get("subjectAltNames").set(index, builder);
         this.subjectAltNames.set(index, builder);
      } else {
         this._visitables.get("subjectAltNames").add(builder);
         this.subjectAltNames.add(builder);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent addToSubjectAltNames(SubjectAltName... items) {
      if (this.subjectAltNames == null) {
         this.subjectAltNames = new ArrayList();
      }

      for(SubjectAltName item : items) {
         SubjectAltNameBuilder builder = new SubjectAltNameBuilder(item);
         this._visitables.get("subjectAltNames").add(builder);
         this.subjectAltNames.add(builder);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent addAllToSubjectAltNames(Collection items) {
      if (this.subjectAltNames == null) {
         this.subjectAltNames = new ArrayList();
      }

      for(SubjectAltName item : items) {
         SubjectAltNameBuilder builder = new SubjectAltNameBuilder(item);
         this._visitables.get("subjectAltNames").add(builder);
         this.subjectAltNames.add(builder);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent removeFromSubjectAltNames(SubjectAltName... items) {
      if (this.subjectAltNames == null) {
         return this;
      } else {
         for(SubjectAltName item : items) {
            SubjectAltNameBuilder builder = new SubjectAltNameBuilder(item);
            this._visitables.get("subjectAltNames").remove(builder);
            this.subjectAltNames.remove(builder);
         }

         return this;
      }
   }

   public BackendTLSPolicyValidationFluent removeAllFromSubjectAltNames(Collection items) {
      if (this.subjectAltNames == null) {
         return this;
      } else {
         for(SubjectAltName item : items) {
            SubjectAltNameBuilder builder = new SubjectAltNameBuilder(item);
            this._visitables.get("subjectAltNames").remove(builder);
            this.subjectAltNames.remove(builder);
         }

         return this;
      }
   }

   public BackendTLSPolicyValidationFluent removeMatchingFromSubjectAltNames(Predicate predicate) {
      if (this.subjectAltNames == null) {
         return this;
      } else {
         Iterator<SubjectAltNameBuilder> each = this.subjectAltNames.iterator();
         List visitables = this._visitables.get("subjectAltNames");

         while(each.hasNext()) {
            SubjectAltNameBuilder builder = (SubjectAltNameBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSubjectAltNames() {
      return this.subjectAltNames != null ? build(this.subjectAltNames) : null;
   }

   public SubjectAltName buildSubjectAltName(int index) {
      return ((SubjectAltNameBuilder)this.subjectAltNames.get(index)).build();
   }

   public SubjectAltName buildFirstSubjectAltName() {
      return ((SubjectAltNameBuilder)this.subjectAltNames.get(0)).build();
   }

   public SubjectAltName buildLastSubjectAltName() {
      return ((SubjectAltNameBuilder)this.subjectAltNames.get(this.subjectAltNames.size() - 1)).build();
   }

   public SubjectAltName buildMatchingSubjectAltName(Predicate predicate) {
      for(SubjectAltNameBuilder item : this.subjectAltNames) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSubjectAltName(Predicate predicate) {
      for(SubjectAltNameBuilder item : this.subjectAltNames) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public BackendTLSPolicyValidationFluent withSubjectAltNames(List subjectAltNames) {
      if (this.subjectAltNames != null) {
         this._visitables.get("subjectAltNames").clear();
      }

      if (subjectAltNames != null) {
         this.subjectAltNames = new ArrayList();

         for(SubjectAltName item : subjectAltNames) {
            this.addToSubjectAltNames(item);
         }
      } else {
         this.subjectAltNames = null;
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent withSubjectAltNames(SubjectAltName... subjectAltNames) {
      if (this.subjectAltNames != null) {
         this.subjectAltNames.clear();
         this._visitables.remove("subjectAltNames");
      }

      if (subjectAltNames != null) {
         for(SubjectAltName item : subjectAltNames) {
            this.addToSubjectAltNames(item);
         }
      }

      return this;
   }

   public boolean hasSubjectAltNames() {
      return this.subjectAltNames != null && !this.subjectAltNames.isEmpty();
   }

   public BackendTLSPolicyValidationFluent addNewSubjectAltName(String hostname, String type, String uri) {
      return this.addToSubjectAltNames(new SubjectAltName(hostname, type, uri));
   }

   public SubjectAltNamesNested addNewSubjectAltName() {
      return new SubjectAltNamesNested(-1, (SubjectAltName)null);
   }

   public SubjectAltNamesNested addNewSubjectAltNameLike(SubjectAltName item) {
      return new SubjectAltNamesNested(-1, item);
   }

   public SubjectAltNamesNested setNewSubjectAltNameLike(int index, SubjectAltName item) {
      return new SubjectAltNamesNested(index, item);
   }

   public SubjectAltNamesNested editSubjectAltName(int index) {
      if (this.subjectAltNames.size() <= index) {
         throw new RuntimeException("Can't edit subjectAltNames. Index exceeds size.");
      } else {
         return this.setNewSubjectAltNameLike(index, this.buildSubjectAltName(index));
      }
   }

   public SubjectAltNamesNested editFirstSubjectAltName() {
      if (this.subjectAltNames.size() == 0) {
         throw new RuntimeException("Can't edit first subjectAltNames. The list is empty.");
      } else {
         return this.setNewSubjectAltNameLike(0, this.buildSubjectAltName(0));
      }
   }

   public SubjectAltNamesNested editLastSubjectAltName() {
      int index = this.subjectAltNames.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last subjectAltNames. The list is empty.");
      } else {
         return this.setNewSubjectAltNameLike(index, this.buildSubjectAltName(index));
      }
   }

   public SubjectAltNamesNested editMatchingSubjectAltName(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.subjectAltNames.size(); ++i) {
         if (predicate.test((SubjectAltNameBuilder)this.subjectAltNames.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching subjectAltNames. No match found.");
      } else {
         return this.setNewSubjectAltNameLike(index, this.buildSubjectAltName(index));
      }
   }

   public String getWellKnownCACertificates() {
      return this.wellKnownCACertificates;
   }

   public BackendTLSPolicyValidationFluent withWellKnownCACertificates(String wellKnownCACertificates) {
      this.wellKnownCACertificates = wellKnownCACertificates;
      return this;
   }

   public boolean hasWellKnownCACertificates() {
      return this.wellKnownCACertificates != null;
   }

   public BackendTLSPolicyValidationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public BackendTLSPolicyValidationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public BackendTLSPolicyValidationFluent removeFromAdditionalProperties(Map map) {
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

   public BackendTLSPolicyValidationFluent withAdditionalProperties(Map additionalProperties) {
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
            BackendTLSPolicyValidationFluent that = (BackendTLSPolicyValidationFluent)o;
            if (!Objects.equals(this.caCertificateRefs, that.caCertificateRefs)) {
               return false;
            } else if (!Objects.equals(this.hostname, that.hostname)) {
               return false;
            } else if (!Objects.equals(this.subjectAltNames, that.subjectAltNames)) {
               return false;
            } else if (!Objects.equals(this.wellKnownCACertificates, that.wellKnownCACertificates)) {
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
      return Objects.hash(new Object[]{this.caCertificateRefs, this.hostname, this.subjectAltNames, this.wellKnownCACertificates, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.caCertificateRefs != null && !this.caCertificateRefs.isEmpty()) {
         sb.append("caCertificateRefs:");
         sb.append(this.caCertificateRefs + ",");
      }

      if (this.hostname != null) {
         sb.append("hostname:");
         sb.append(this.hostname + ",");
      }

      if (this.subjectAltNames != null && !this.subjectAltNames.isEmpty()) {
         sb.append("subjectAltNames:");
         sb.append(this.subjectAltNames + ",");
      }

      if (this.wellKnownCACertificates != null) {
         sb.append("wellKnownCACertificates:");
         sb.append(this.wellKnownCACertificates + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class CaCertificateRefsNested extends LocalObjectReferenceFluent implements Nested {
      LocalObjectReferenceBuilder builder;
      int index;

      CaCertificateRefsNested(int index, LocalObjectReference item) {
         this.index = index;
         this.builder = new LocalObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return BackendTLSPolicyValidationFluent.this.setToCaCertificateRefs(this.index, this.builder.build());
      }

      public Object endCaCertificateRef() {
         return this.and();
      }
   }

   public class SubjectAltNamesNested extends SubjectAltNameFluent implements Nested {
      SubjectAltNameBuilder builder;
      int index;

      SubjectAltNamesNested(int index, SubjectAltName item) {
         this.index = index;
         this.builder = new SubjectAltNameBuilder(this, item);
      }

      public Object and() {
         return BackendTLSPolicyValidationFluent.this.setToSubjectAltNames(this.index, this.builder.build());
      }

      public Object endSubjectAltName() {
         return this.and();
      }
   }
}
