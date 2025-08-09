package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha3;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2.LocalPolicyTargetReferenceWithSectionName;
import io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2.LocalPolicyTargetReferenceWithSectionNameBuilder;
import io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2.LocalPolicyTargetReferenceWithSectionNameFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class BackendTLSPolicySpecFluent extends BaseFluent {
   private Map options;
   private ArrayList targetRefs = new ArrayList();
   private BackendTLSPolicyValidationBuilder validation;
   private Map additionalProperties;

   public BackendTLSPolicySpecFluent() {
   }

   public BackendTLSPolicySpecFluent(BackendTLSPolicySpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(BackendTLSPolicySpec instance) {
      instance = instance != null ? instance : new BackendTLSPolicySpec();
      if (instance != null) {
         this.withOptions(instance.getOptions());
         this.withTargetRefs(instance.getTargetRefs());
         this.withValidation(instance.getValidation());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public BackendTLSPolicySpecFluent addToOptions(String key, String value) {
      if (this.options == null && key != null && value != null) {
         this.options = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.options.put(key, value);
      }

      return this;
   }

   public BackendTLSPolicySpecFluent addToOptions(Map map) {
      if (this.options == null && map != null) {
         this.options = new LinkedHashMap();
      }

      if (map != null) {
         this.options.putAll(map);
      }

      return this;
   }

   public BackendTLSPolicySpecFluent removeFromOptions(String key) {
      if (this.options == null) {
         return this;
      } else {
         if (key != null && this.options != null) {
            this.options.remove(key);
         }

         return this;
      }
   }

   public BackendTLSPolicySpecFluent removeFromOptions(Map map) {
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

   public BackendTLSPolicySpecFluent withOptions(Map options) {
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

   public BackendTLSPolicySpecFluent addToTargetRefs(int index, LocalPolicyTargetReferenceWithSectionName item) {
      if (this.targetRefs == null) {
         this.targetRefs = new ArrayList();
      }

      LocalPolicyTargetReferenceWithSectionNameBuilder builder = new LocalPolicyTargetReferenceWithSectionNameBuilder(item);
      if (index >= 0 && index < this.targetRefs.size()) {
         this._visitables.get("targetRefs").add(index, builder);
         this.targetRefs.add(index, builder);
      } else {
         this._visitables.get("targetRefs").add(builder);
         this.targetRefs.add(builder);
      }

      return this;
   }

   public BackendTLSPolicySpecFluent setToTargetRefs(int index, LocalPolicyTargetReferenceWithSectionName item) {
      if (this.targetRefs == null) {
         this.targetRefs = new ArrayList();
      }

      LocalPolicyTargetReferenceWithSectionNameBuilder builder = new LocalPolicyTargetReferenceWithSectionNameBuilder(item);
      if (index >= 0 && index < this.targetRefs.size()) {
         this._visitables.get("targetRefs").set(index, builder);
         this.targetRefs.set(index, builder);
      } else {
         this._visitables.get("targetRefs").add(builder);
         this.targetRefs.add(builder);
      }

      return this;
   }

   public BackendTLSPolicySpecFluent addToTargetRefs(LocalPolicyTargetReferenceWithSectionName... items) {
      if (this.targetRefs == null) {
         this.targetRefs = new ArrayList();
      }

      for(LocalPolicyTargetReferenceWithSectionName item : items) {
         LocalPolicyTargetReferenceWithSectionNameBuilder builder = new LocalPolicyTargetReferenceWithSectionNameBuilder(item);
         this._visitables.get("targetRefs").add(builder);
         this.targetRefs.add(builder);
      }

      return this;
   }

   public BackendTLSPolicySpecFluent addAllToTargetRefs(Collection items) {
      if (this.targetRefs == null) {
         this.targetRefs = new ArrayList();
      }

      for(LocalPolicyTargetReferenceWithSectionName item : items) {
         LocalPolicyTargetReferenceWithSectionNameBuilder builder = new LocalPolicyTargetReferenceWithSectionNameBuilder(item);
         this._visitables.get("targetRefs").add(builder);
         this.targetRefs.add(builder);
      }

      return this;
   }

   public BackendTLSPolicySpecFluent removeFromTargetRefs(LocalPolicyTargetReferenceWithSectionName... items) {
      if (this.targetRefs == null) {
         return this;
      } else {
         for(LocalPolicyTargetReferenceWithSectionName item : items) {
            LocalPolicyTargetReferenceWithSectionNameBuilder builder = new LocalPolicyTargetReferenceWithSectionNameBuilder(item);
            this._visitables.get("targetRefs").remove(builder);
            this.targetRefs.remove(builder);
         }

         return this;
      }
   }

   public BackendTLSPolicySpecFluent removeAllFromTargetRefs(Collection items) {
      if (this.targetRefs == null) {
         return this;
      } else {
         for(LocalPolicyTargetReferenceWithSectionName item : items) {
            LocalPolicyTargetReferenceWithSectionNameBuilder builder = new LocalPolicyTargetReferenceWithSectionNameBuilder(item);
            this._visitables.get("targetRefs").remove(builder);
            this.targetRefs.remove(builder);
         }

         return this;
      }
   }

   public BackendTLSPolicySpecFluent removeMatchingFromTargetRefs(Predicate predicate) {
      if (this.targetRefs == null) {
         return this;
      } else {
         Iterator<LocalPolicyTargetReferenceWithSectionNameBuilder> each = this.targetRefs.iterator();
         List visitables = this._visitables.get("targetRefs");

         while(each.hasNext()) {
            LocalPolicyTargetReferenceWithSectionNameBuilder builder = (LocalPolicyTargetReferenceWithSectionNameBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildTargetRefs() {
      return this.targetRefs != null ? build(this.targetRefs) : null;
   }

   public LocalPolicyTargetReferenceWithSectionName buildTargetRef(int index) {
      return ((LocalPolicyTargetReferenceWithSectionNameBuilder)this.targetRefs.get(index)).build();
   }

   public LocalPolicyTargetReferenceWithSectionName buildFirstTargetRef() {
      return ((LocalPolicyTargetReferenceWithSectionNameBuilder)this.targetRefs.get(0)).build();
   }

   public LocalPolicyTargetReferenceWithSectionName buildLastTargetRef() {
      return ((LocalPolicyTargetReferenceWithSectionNameBuilder)this.targetRefs.get(this.targetRefs.size() - 1)).build();
   }

   public LocalPolicyTargetReferenceWithSectionName buildMatchingTargetRef(Predicate predicate) {
      for(LocalPolicyTargetReferenceWithSectionNameBuilder item : this.targetRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingTargetRef(Predicate predicate) {
      for(LocalPolicyTargetReferenceWithSectionNameBuilder item : this.targetRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public BackendTLSPolicySpecFluent withTargetRefs(List targetRefs) {
      if (this.targetRefs != null) {
         this._visitables.get("targetRefs").clear();
      }

      if (targetRefs != null) {
         this.targetRefs = new ArrayList();

         for(LocalPolicyTargetReferenceWithSectionName item : targetRefs) {
            this.addToTargetRefs(item);
         }
      } else {
         this.targetRefs = null;
      }

      return this;
   }

   public BackendTLSPolicySpecFluent withTargetRefs(LocalPolicyTargetReferenceWithSectionName... targetRefs) {
      if (this.targetRefs != null) {
         this.targetRefs.clear();
         this._visitables.remove("targetRefs");
      }

      if (targetRefs != null) {
         for(LocalPolicyTargetReferenceWithSectionName item : targetRefs) {
            this.addToTargetRefs(item);
         }
      }

      return this;
   }

   public boolean hasTargetRefs() {
      return this.targetRefs != null && !this.targetRefs.isEmpty();
   }

   public BackendTLSPolicySpecFluent addNewTargetRef(String group, String kind, String name, String sectionName) {
      return this.addToTargetRefs(new LocalPolicyTargetReferenceWithSectionName(group, kind, name, sectionName));
   }

   public TargetRefsNested addNewTargetRef() {
      return new TargetRefsNested(-1, (LocalPolicyTargetReferenceWithSectionName)null);
   }

   public TargetRefsNested addNewTargetRefLike(LocalPolicyTargetReferenceWithSectionName item) {
      return new TargetRefsNested(-1, item);
   }

   public TargetRefsNested setNewTargetRefLike(int index, LocalPolicyTargetReferenceWithSectionName item) {
      return new TargetRefsNested(index, item);
   }

   public TargetRefsNested editTargetRef(int index) {
      if (this.targetRefs.size() <= index) {
         throw new RuntimeException("Can't edit targetRefs. Index exceeds size.");
      } else {
         return this.setNewTargetRefLike(index, this.buildTargetRef(index));
      }
   }

   public TargetRefsNested editFirstTargetRef() {
      if (this.targetRefs.size() == 0) {
         throw new RuntimeException("Can't edit first targetRefs. The list is empty.");
      } else {
         return this.setNewTargetRefLike(0, this.buildTargetRef(0));
      }
   }

   public TargetRefsNested editLastTargetRef() {
      int index = this.targetRefs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last targetRefs. The list is empty.");
      } else {
         return this.setNewTargetRefLike(index, this.buildTargetRef(index));
      }
   }

   public TargetRefsNested editMatchingTargetRef(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.targetRefs.size(); ++i) {
         if (predicate.test((LocalPolicyTargetReferenceWithSectionNameBuilder)this.targetRefs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching targetRefs. No match found.");
      } else {
         return this.setNewTargetRefLike(index, this.buildTargetRef(index));
      }
   }

   public BackendTLSPolicyValidation buildValidation() {
      return this.validation != null ? this.validation.build() : null;
   }

   public BackendTLSPolicySpecFluent withValidation(BackendTLSPolicyValidation validation) {
      this._visitables.remove("validation");
      if (validation != null) {
         this.validation = new BackendTLSPolicyValidationBuilder(validation);
         this._visitables.get("validation").add(this.validation);
      } else {
         this.validation = null;
         this._visitables.get("validation").remove(this.validation);
      }

      return this;
   }

   public boolean hasValidation() {
      return this.validation != null;
   }

   public ValidationNested withNewValidation() {
      return new ValidationNested((BackendTLSPolicyValidation)null);
   }

   public ValidationNested withNewValidationLike(BackendTLSPolicyValidation item) {
      return new ValidationNested(item);
   }

   public ValidationNested editValidation() {
      return this.withNewValidationLike((BackendTLSPolicyValidation)Optional.ofNullable(this.buildValidation()).orElse((Object)null));
   }

   public ValidationNested editOrNewValidation() {
      return this.withNewValidationLike((BackendTLSPolicyValidation)Optional.ofNullable(this.buildValidation()).orElse((new BackendTLSPolicyValidationBuilder()).build()));
   }

   public ValidationNested editOrNewValidationLike(BackendTLSPolicyValidation item) {
      return this.withNewValidationLike((BackendTLSPolicyValidation)Optional.ofNullable(this.buildValidation()).orElse(item));
   }

   public BackendTLSPolicySpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public BackendTLSPolicySpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public BackendTLSPolicySpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public BackendTLSPolicySpecFluent removeFromAdditionalProperties(Map map) {
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

   public BackendTLSPolicySpecFluent withAdditionalProperties(Map additionalProperties) {
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
            BackendTLSPolicySpecFluent that = (BackendTLSPolicySpecFluent)o;
            if (!Objects.equals(this.options, that.options)) {
               return false;
            } else if (!Objects.equals(this.targetRefs, that.targetRefs)) {
               return false;
            } else if (!Objects.equals(this.validation, that.validation)) {
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
      return Objects.hash(new Object[]{this.options, this.targetRefs, this.validation, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.options != null && !this.options.isEmpty()) {
         sb.append("options:");
         sb.append(this.options + ",");
      }

      if (this.targetRefs != null && !this.targetRefs.isEmpty()) {
         sb.append("targetRefs:");
         sb.append(this.targetRefs + ",");
      }

      if (this.validation != null) {
         sb.append("validation:");
         sb.append(this.validation + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class TargetRefsNested extends LocalPolicyTargetReferenceWithSectionNameFluent implements Nested {
      LocalPolicyTargetReferenceWithSectionNameBuilder builder;
      int index;

      TargetRefsNested(int index, LocalPolicyTargetReferenceWithSectionName item) {
         this.index = index;
         this.builder = new LocalPolicyTargetReferenceWithSectionNameBuilder(this, item);
      }

      public Object and() {
         return BackendTLSPolicySpecFluent.this.setToTargetRefs(this.index, this.builder.build());
      }

      public Object endTargetRef() {
         return this.and();
      }
   }

   public class ValidationNested extends BackendTLSPolicyValidationFluent implements Nested {
      BackendTLSPolicyValidationBuilder builder;

      ValidationNested(BackendTLSPolicyValidation item) {
         this.builder = new BackendTLSPolicyValidationBuilder(this, item);
      }

      public Object and() {
         return BackendTLSPolicySpecFluent.this.withValidation(this.builder.build());
      }

      public Object endValidation() {
         return this.and();
      }
   }
}
