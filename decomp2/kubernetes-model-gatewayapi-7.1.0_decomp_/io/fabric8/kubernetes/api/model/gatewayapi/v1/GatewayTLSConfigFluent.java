package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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

public class GatewayTLSConfigFluent extends BaseFluent {
   private ArrayList certificateRefs = new ArrayList();
   private FrontendTLSValidationBuilder frontendValidation;
   private String mode;
   private Map options;
   private Map additionalProperties;

   public GatewayTLSConfigFluent() {
   }

   public GatewayTLSConfigFluent(GatewayTLSConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GatewayTLSConfig instance) {
      instance = instance != null ? instance : new GatewayTLSConfig();
      if (instance != null) {
         this.withCertificateRefs(instance.getCertificateRefs());
         this.withFrontendValidation(instance.getFrontendValidation());
         this.withMode(instance.getMode());
         this.withOptions(instance.getOptions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public GatewayTLSConfigFluent addToCertificateRefs(int index, SecretObjectReference item) {
      if (this.certificateRefs == null) {
         this.certificateRefs = new ArrayList();
      }

      SecretObjectReferenceBuilder builder = new SecretObjectReferenceBuilder(item);
      if (index >= 0 && index < this.certificateRefs.size()) {
         this._visitables.get("certificateRefs").add(index, builder);
         this.certificateRefs.add(index, builder);
      } else {
         this._visitables.get("certificateRefs").add(builder);
         this.certificateRefs.add(builder);
      }

      return this;
   }

   public GatewayTLSConfigFluent setToCertificateRefs(int index, SecretObjectReference item) {
      if (this.certificateRefs == null) {
         this.certificateRefs = new ArrayList();
      }

      SecretObjectReferenceBuilder builder = new SecretObjectReferenceBuilder(item);
      if (index >= 0 && index < this.certificateRefs.size()) {
         this._visitables.get("certificateRefs").set(index, builder);
         this.certificateRefs.set(index, builder);
      } else {
         this._visitables.get("certificateRefs").add(builder);
         this.certificateRefs.add(builder);
      }

      return this;
   }

   public GatewayTLSConfigFluent addToCertificateRefs(SecretObjectReference... items) {
      if (this.certificateRefs == null) {
         this.certificateRefs = new ArrayList();
      }

      for(SecretObjectReference item : items) {
         SecretObjectReferenceBuilder builder = new SecretObjectReferenceBuilder(item);
         this._visitables.get("certificateRefs").add(builder);
         this.certificateRefs.add(builder);
      }

      return this;
   }

   public GatewayTLSConfigFluent addAllToCertificateRefs(Collection items) {
      if (this.certificateRefs == null) {
         this.certificateRefs = new ArrayList();
      }

      for(SecretObjectReference item : items) {
         SecretObjectReferenceBuilder builder = new SecretObjectReferenceBuilder(item);
         this._visitables.get("certificateRefs").add(builder);
         this.certificateRefs.add(builder);
      }

      return this;
   }

   public GatewayTLSConfigFluent removeFromCertificateRefs(SecretObjectReference... items) {
      if (this.certificateRefs == null) {
         return this;
      } else {
         for(SecretObjectReference item : items) {
            SecretObjectReferenceBuilder builder = new SecretObjectReferenceBuilder(item);
            this._visitables.get("certificateRefs").remove(builder);
            this.certificateRefs.remove(builder);
         }

         return this;
      }
   }

   public GatewayTLSConfigFluent removeAllFromCertificateRefs(Collection items) {
      if (this.certificateRefs == null) {
         return this;
      } else {
         for(SecretObjectReference item : items) {
            SecretObjectReferenceBuilder builder = new SecretObjectReferenceBuilder(item);
            this._visitables.get("certificateRefs").remove(builder);
            this.certificateRefs.remove(builder);
         }

         return this;
      }
   }

   public GatewayTLSConfigFluent removeMatchingFromCertificateRefs(Predicate predicate) {
      if (this.certificateRefs == null) {
         return this;
      } else {
         Iterator<SecretObjectReferenceBuilder> each = this.certificateRefs.iterator();
         List visitables = this._visitables.get("certificateRefs");

         while(each.hasNext()) {
            SecretObjectReferenceBuilder builder = (SecretObjectReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildCertificateRefs() {
      return this.certificateRefs != null ? build(this.certificateRefs) : null;
   }

   public SecretObjectReference buildCertificateRef(int index) {
      return ((SecretObjectReferenceBuilder)this.certificateRefs.get(index)).build();
   }

   public SecretObjectReference buildFirstCertificateRef() {
      return ((SecretObjectReferenceBuilder)this.certificateRefs.get(0)).build();
   }

   public SecretObjectReference buildLastCertificateRef() {
      return ((SecretObjectReferenceBuilder)this.certificateRefs.get(this.certificateRefs.size() - 1)).build();
   }

   public SecretObjectReference buildMatchingCertificateRef(Predicate predicate) {
      for(SecretObjectReferenceBuilder item : this.certificateRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCertificateRef(Predicate predicate) {
      for(SecretObjectReferenceBuilder item : this.certificateRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GatewayTLSConfigFluent withCertificateRefs(List certificateRefs) {
      if (this.certificateRefs != null) {
         this._visitables.get("certificateRefs").clear();
      }

      if (certificateRefs != null) {
         this.certificateRefs = new ArrayList();

         for(SecretObjectReference item : certificateRefs) {
            this.addToCertificateRefs(item);
         }
      } else {
         this.certificateRefs = null;
      }

      return this;
   }

   public GatewayTLSConfigFluent withCertificateRefs(SecretObjectReference... certificateRefs) {
      if (this.certificateRefs != null) {
         this.certificateRefs.clear();
         this._visitables.remove("certificateRefs");
      }

      if (certificateRefs != null) {
         for(SecretObjectReference item : certificateRefs) {
            this.addToCertificateRefs(item);
         }
      }

      return this;
   }

   public boolean hasCertificateRefs() {
      return this.certificateRefs != null && !this.certificateRefs.isEmpty();
   }

   public GatewayTLSConfigFluent addNewCertificateRef(String group, String kind, String name, String namespace) {
      return this.addToCertificateRefs(new SecretObjectReference(group, kind, name, namespace));
   }

   public CertificateRefsNested addNewCertificateRef() {
      return new CertificateRefsNested(-1, (SecretObjectReference)null);
   }

   public CertificateRefsNested addNewCertificateRefLike(SecretObjectReference item) {
      return new CertificateRefsNested(-1, item);
   }

   public CertificateRefsNested setNewCertificateRefLike(int index, SecretObjectReference item) {
      return new CertificateRefsNested(index, item);
   }

   public CertificateRefsNested editCertificateRef(int index) {
      if (this.certificateRefs.size() <= index) {
         throw new RuntimeException("Can't edit certificateRefs. Index exceeds size.");
      } else {
         return this.setNewCertificateRefLike(index, this.buildCertificateRef(index));
      }
   }

   public CertificateRefsNested editFirstCertificateRef() {
      if (this.certificateRefs.size() == 0) {
         throw new RuntimeException("Can't edit first certificateRefs. The list is empty.");
      } else {
         return this.setNewCertificateRefLike(0, this.buildCertificateRef(0));
      }
   }

   public CertificateRefsNested editLastCertificateRef() {
      int index = this.certificateRefs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last certificateRefs. The list is empty.");
      } else {
         return this.setNewCertificateRefLike(index, this.buildCertificateRef(index));
      }
   }

   public CertificateRefsNested editMatchingCertificateRef(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.certificateRefs.size(); ++i) {
         if (predicate.test((SecretObjectReferenceBuilder)this.certificateRefs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching certificateRefs. No match found.");
      } else {
         return this.setNewCertificateRefLike(index, this.buildCertificateRef(index));
      }
   }

   public FrontendTLSValidation buildFrontendValidation() {
      return this.frontendValidation != null ? this.frontendValidation.build() : null;
   }

   public GatewayTLSConfigFluent withFrontendValidation(FrontendTLSValidation frontendValidation) {
      this._visitables.remove("frontendValidation");
      if (frontendValidation != null) {
         this.frontendValidation = new FrontendTLSValidationBuilder(frontendValidation);
         this._visitables.get("frontendValidation").add(this.frontendValidation);
      } else {
         this.frontendValidation = null;
         this._visitables.get("frontendValidation").remove(this.frontendValidation);
      }

      return this;
   }

   public boolean hasFrontendValidation() {
      return this.frontendValidation != null;
   }

   public FrontendValidationNested withNewFrontendValidation() {
      return new FrontendValidationNested((FrontendTLSValidation)null);
   }

   public FrontendValidationNested withNewFrontendValidationLike(FrontendTLSValidation item) {
      return new FrontendValidationNested(item);
   }

   public FrontendValidationNested editFrontendValidation() {
      return this.withNewFrontendValidationLike((FrontendTLSValidation)Optional.ofNullable(this.buildFrontendValidation()).orElse((Object)null));
   }

   public FrontendValidationNested editOrNewFrontendValidation() {
      return this.withNewFrontendValidationLike((FrontendTLSValidation)Optional.ofNullable(this.buildFrontendValidation()).orElse((new FrontendTLSValidationBuilder()).build()));
   }

   public FrontendValidationNested editOrNewFrontendValidationLike(FrontendTLSValidation item) {
      return this.withNewFrontendValidationLike((FrontendTLSValidation)Optional.ofNullable(this.buildFrontendValidation()).orElse(item));
   }

   public String getMode() {
      return this.mode;
   }

   public GatewayTLSConfigFluent withMode(String mode) {
      this.mode = mode;
      return this;
   }

   public boolean hasMode() {
      return this.mode != null;
   }

   public GatewayTLSConfigFluent addToOptions(String key, String value) {
      if (this.options == null && key != null && value != null) {
         this.options = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.options.put(key, value);
      }

      return this;
   }

   public GatewayTLSConfigFluent addToOptions(Map map) {
      if (this.options == null && map != null) {
         this.options = new LinkedHashMap();
      }

      if (map != null) {
         this.options.putAll(map);
      }

      return this;
   }

   public GatewayTLSConfigFluent removeFromOptions(String key) {
      if (this.options == null) {
         return this;
      } else {
         if (key != null && this.options != null) {
            this.options.remove(key);
         }

         return this;
      }
   }

   public GatewayTLSConfigFluent removeFromOptions(Map map) {
      if (this.options == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.options != null) {
                  this.options.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getOptions() {
      return this.options;
   }

   public GatewayTLSConfigFluent withOptions(Map options) {
      if (options == null) {
         this.options = null;
      } else {
         this.options = new LinkedHashMap(options);
      }

      return this;
   }

   public boolean hasOptions() {
      return this.options != null;
   }

   public GatewayTLSConfigFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GatewayTLSConfigFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GatewayTLSConfigFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GatewayTLSConfigFluent removeFromAdditionalProperties(Map map) {
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

   public GatewayTLSConfigFluent withAdditionalProperties(Map additionalProperties) {
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
            GatewayTLSConfigFluent that = (GatewayTLSConfigFluent)o;
            if (!Objects.equals(this.certificateRefs, that.certificateRefs)) {
               return false;
            } else if (!Objects.equals(this.frontendValidation, that.frontendValidation)) {
               return false;
            } else if (!Objects.equals(this.mode, that.mode)) {
               return false;
            } else if (!Objects.equals(this.options, that.options)) {
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
      return Objects.hash(new Object[]{this.certificateRefs, this.frontendValidation, this.mode, this.options, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.certificateRefs != null && !this.certificateRefs.isEmpty()) {
         sb.append("certificateRefs:");
         sb.append(this.certificateRefs + ",");
      }

      if (this.frontendValidation != null) {
         sb.append("frontendValidation:");
         sb.append(this.frontendValidation + ",");
      }

      if (this.mode != null) {
         sb.append("mode:");
         sb.append(this.mode + ",");
      }

      if (this.options != null && !this.options.isEmpty()) {
         sb.append("options:");
         sb.append(this.options + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class CertificateRefsNested extends SecretObjectReferenceFluent implements Nested {
      SecretObjectReferenceBuilder builder;
      int index;

      CertificateRefsNested(int index, SecretObjectReference item) {
         this.index = index;
         this.builder = new SecretObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return GatewayTLSConfigFluent.this.setToCertificateRefs(this.index, this.builder.build());
      }

      public Object endCertificateRef() {
         return this.and();
      }
   }

   public class FrontendValidationNested extends FrontendTLSValidationFluent implements Nested {
      FrontendTLSValidationBuilder builder;

      FrontendValidationNested(FrontendTLSValidation item) {
         this.builder = new FrontendTLSValidationBuilder(this, item);
      }

      public Object and() {
         return GatewayTLSConfigFluent.this.withFrontendValidation(this.builder.build());
      }

      public Object endFrontendValidation() {
         return this.and();
      }
   }
}
