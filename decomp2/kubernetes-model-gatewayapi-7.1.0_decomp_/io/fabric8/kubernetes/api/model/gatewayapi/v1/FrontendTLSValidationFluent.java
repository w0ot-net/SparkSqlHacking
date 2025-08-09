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
import java.util.function.Predicate;

public class FrontendTLSValidationFluent extends BaseFluent {
   private ArrayList caCertificateRefs = new ArrayList();
   private Map additionalProperties;

   public FrontendTLSValidationFluent() {
   }

   public FrontendTLSValidationFluent(FrontendTLSValidation instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(FrontendTLSValidation instance) {
      instance = instance != null ? instance : new FrontendTLSValidation();
      if (instance != null) {
         this.withCaCertificateRefs(instance.getCaCertificateRefs());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public FrontendTLSValidationFluent addToCaCertificateRefs(int index, io.fabric8.kubernetes.api.model.ObjectReference item) {
      if (this.caCertificateRefs == null) {
         this.caCertificateRefs = new ArrayList();
      }

      io.fabric8.kubernetes.api.model.ObjectReferenceBuilder builder = new io.fabric8.kubernetes.api.model.ObjectReferenceBuilder(item);
      if (index >= 0 && index < this.caCertificateRefs.size()) {
         this._visitables.get("caCertificateRefs").add(index, builder);
         this.caCertificateRefs.add(index, builder);
      } else {
         this._visitables.get("caCertificateRefs").add(builder);
         this.caCertificateRefs.add(builder);
      }

      return this;
   }

   public FrontendTLSValidationFluent setToCaCertificateRefs(int index, io.fabric8.kubernetes.api.model.ObjectReference item) {
      if (this.caCertificateRefs == null) {
         this.caCertificateRefs = new ArrayList();
      }

      io.fabric8.kubernetes.api.model.ObjectReferenceBuilder builder = new io.fabric8.kubernetes.api.model.ObjectReferenceBuilder(item);
      if (index >= 0 && index < this.caCertificateRefs.size()) {
         this._visitables.get("caCertificateRefs").set(index, builder);
         this.caCertificateRefs.set(index, builder);
      } else {
         this._visitables.get("caCertificateRefs").add(builder);
         this.caCertificateRefs.add(builder);
      }

      return this;
   }

   public FrontendTLSValidationFluent addToCaCertificateRefs(io.fabric8.kubernetes.api.model.ObjectReference... items) {
      if (this.caCertificateRefs == null) {
         this.caCertificateRefs = new ArrayList();
      }

      for(io.fabric8.kubernetes.api.model.ObjectReference item : items) {
         io.fabric8.kubernetes.api.model.ObjectReferenceBuilder builder = new io.fabric8.kubernetes.api.model.ObjectReferenceBuilder(item);
         this._visitables.get("caCertificateRefs").add(builder);
         this.caCertificateRefs.add(builder);
      }

      return this;
   }

   public FrontendTLSValidationFluent addAllToCaCertificateRefs(Collection items) {
      if (this.caCertificateRefs == null) {
         this.caCertificateRefs = new ArrayList();
      }

      for(io.fabric8.kubernetes.api.model.ObjectReference item : items) {
         io.fabric8.kubernetes.api.model.ObjectReferenceBuilder builder = new io.fabric8.kubernetes.api.model.ObjectReferenceBuilder(item);
         this._visitables.get("caCertificateRefs").add(builder);
         this.caCertificateRefs.add(builder);
      }

      return this;
   }

   public FrontendTLSValidationFluent removeFromCaCertificateRefs(io.fabric8.kubernetes.api.model.ObjectReference... items) {
      if (this.caCertificateRefs == null) {
         return this;
      } else {
         for(io.fabric8.kubernetes.api.model.ObjectReference item : items) {
            io.fabric8.kubernetes.api.model.ObjectReferenceBuilder builder = new io.fabric8.kubernetes.api.model.ObjectReferenceBuilder(item);
            this._visitables.get("caCertificateRefs").remove(builder);
            this.caCertificateRefs.remove(builder);
         }

         return this;
      }
   }

   public FrontendTLSValidationFluent removeAllFromCaCertificateRefs(Collection items) {
      if (this.caCertificateRefs == null) {
         return this;
      } else {
         for(io.fabric8.kubernetes.api.model.ObjectReference item : items) {
            io.fabric8.kubernetes.api.model.ObjectReferenceBuilder builder = new io.fabric8.kubernetes.api.model.ObjectReferenceBuilder(item);
            this._visitables.get("caCertificateRefs").remove(builder);
            this.caCertificateRefs.remove(builder);
         }

         return this;
      }
   }

   public FrontendTLSValidationFluent removeMatchingFromCaCertificateRefs(Predicate predicate) {
      if (this.caCertificateRefs == null) {
         return this;
      } else {
         Iterator<io.fabric8.kubernetes.api.model.ObjectReferenceBuilder> each = this.caCertificateRefs.iterator();
         List visitables = this._visitables.get("caCertificateRefs");

         while(each.hasNext()) {
            io.fabric8.kubernetes.api.model.ObjectReferenceBuilder builder = (io.fabric8.kubernetes.api.model.ObjectReferenceBuilder)each.next();
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

   public io.fabric8.kubernetes.api.model.ObjectReference buildCaCertificateRef(int index) {
      return ((io.fabric8.kubernetes.api.model.ObjectReferenceBuilder)this.caCertificateRefs.get(index)).build();
   }

   public io.fabric8.kubernetes.api.model.ObjectReference buildFirstCaCertificateRef() {
      return ((io.fabric8.kubernetes.api.model.ObjectReferenceBuilder)this.caCertificateRefs.get(0)).build();
   }

   public io.fabric8.kubernetes.api.model.ObjectReference buildLastCaCertificateRef() {
      return ((io.fabric8.kubernetes.api.model.ObjectReferenceBuilder)this.caCertificateRefs.get(this.caCertificateRefs.size() - 1)).build();
   }

   public io.fabric8.kubernetes.api.model.ObjectReference buildMatchingCaCertificateRef(Predicate predicate) {
      for(io.fabric8.kubernetes.api.model.ObjectReferenceBuilder item : this.caCertificateRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCaCertificateRef(Predicate predicate) {
      for(io.fabric8.kubernetes.api.model.ObjectReferenceBuilder item : this.caCertificateRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public FrontendTLSValidationFluent withCaCertificateRefs(List caCertificateRefs) {
      if (this.caCertificateRefs != null) {
         this._visitables.get("caCertificateRefs").clear();
      }

      if (caCertificateRefs != null) {
         this.caCertificateRefs = new ArrayList();

         for(io.fabric8.kubernetes.api.model.ObjectReference item : caCertificateRefs) {
            this.addToCaCertificateRefs(item);
         }
      } else {
         this.caCertificateRefs = null;
      }

      return this;
   }

   public FrontendTLSValidationFluent withCaCertificateRefs(io.fabric8.kubernetes.api.model.ObjectReference... caCertificateRefs) {
      if (this.caCertificateRefs != null) {
         this.caCertificateRefs.clear();
         this._visitables.remove("caCertificateRefs");
      }

      if (caCertificateRefs != null) {
         for(io.fabric8.kubernetes.api.model.ObjectReference item : caCertificateRefs) {
            this.addToCaCertificateRefs(item);
         }
      }

      return this;
   }

   public boolean hasCaCertificateRefs() {
      return this.caCertificateRefs != null && !this.caCertificateRefs.isEmpty();
   }

   public CaCertificateRefsNested addNewCaCertificateRef() {
      return new CaCertificateRefsNested(-1, (io.fabric8.kubernetes.api.model.ObjectReference)null);
   }

   public CaCertificateRefsNested addNewCaCertificateRefLike(io.fabric8.kubernetes.api.model.ObjectReference item) {
      return new CaCertificateRefsNested(-1, item);
   }

   public CaCertificateRefsNested setNewCaCertificateRefLike(int index, io.fabric8.kubernetes.api.model.ObjectReference item) {
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
         if (predicate.test((io.fabric8.kubernetes.api.model.ObjectReferenceBuilder)this.caCertificateRefs.get(i))) {
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

   public FrontendTLSValidationFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public FrontendTLSValidationFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public FrontendTLSValidationFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public FrontendTLSValidationFluent removeFromAdditionalProperties(Map map) {
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

   public FrontendTLSValidationFluent withAdditionalProperties(Map additionalProperties) {
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
            FrontendTLSValidationFluent that = (FrontendTLSValidationFluent)o;
            if (!Objects.equals(this.caCertificateRefs, that.caCertificateRefs)) {
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
      return Objects.hash(new Object[]{this.caCertificateRefs, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.caCertificateRefs != null && !this.caCertificateRefs.isEmpty()) {
         sb.append("caCertificateRefs:");
         sb.append(this.caCertificateRefs + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class CaCertificateRefsNested extends io.fabric8.kubernetes.api.model.ObjectReferenceFluent implements Nested {
      io.fabric8.kubernetes.api.model.ObjectReferenceBuilder builder;
      int index;

      CaCertificateRefsNested(int index, io.fabric8.kubernetes.api.model.ObjectReference item) {
         this.index = index;
         this.builder = new io.fabric8.kubernetes.api.model.ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return FrontendTLSValidationFluent.this.setToCaCertificateRefs(this.index, this.builder.build());
      }

      public Object endCaCertificateRef() {
         return this.and();
      }
   }
}
