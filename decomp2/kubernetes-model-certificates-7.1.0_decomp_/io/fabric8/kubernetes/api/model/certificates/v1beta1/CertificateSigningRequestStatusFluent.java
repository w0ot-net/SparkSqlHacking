package io.fabric8.kubernetes.api.model.certificates.v1beta1;

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

public class CertificateSigningRequestStatusFluent extends BaseFluent {
   private String certificate;
   private ArrayList conditions = new ArrayList();
   private Map additionalProperties;

   public CertificateSigningRequestStatusFluent() {
   }

   public CertificateSigningRequestStatusFluent(CertificateSigningRequestStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(CertificateSigningRequestStatus instance) {
      instance = instance != null ? instance : new CertificateSigningRequestStatus();
      if (instance != null) {
         this.withCertificate(instance.getCertificate());
         this.withConditions(instance.getConditions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getCertificate() {
      return this.certificate;
   }

   public CertificateSigningRequestStatusFluent withCertificate(String certificate) {
      this.certificate = certificate;
      return this;
   }

   public boolean hasCertificate() {
      return this.certificate != null;
   }

   public CertificateSigningRequestStatusFluent addToConditions(int index, CertificateSigningRequestCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      CertificateSigningRequestConditionBuilder builder = new CertificateSigningRequestConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public CertificateSigningRequestStatusFluent setToConditions(int index, CertificateSigningRequestCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      CertificateSigningRequestConditionBuilder builder = new CertificateSigningRequestConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public CertificateSigningRequestStatusFluent addToConditions(CertificateSigningRequestCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(CertificateSigningRequestCondition item : items) {
         CertificateSigningRequestConditionBuilder builder = new CertificateSigningRequestConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public CertificateSigningRequestStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(CertificateSigningRequestCondition item : items) {
         CertificateSigningRequestConditionBuilder builder = new CertificateSigningRequestConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public CertificateSigningRequestStatusFluent removeFromConditions(CertificateSigningRequestCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(CertificateSigningRequestCondition item : items) {
            CertificateSigningRequestConditionBuilder builder = new CertificateSigningRequestConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public CertificateSigningRequestStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(CertificateSigningRequestCondition item : items) {
            CertificateSigningRequestConditionBuilder builder = new CertificateSigningRequestConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public CertificateSigningRequestStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<CertificateSigningRequestConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            CertificateSigningRequestConditionBuilder builder = (CertificateSigningRequestConditionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildConditions() {
      return this.conditions != null ? build(this.conditions) : null;
   }

   public CertificateSigningRequestCondition buildCondition(int index) {
      return ((CertificateSigningRequestConditionBuilder)this.conditions.get(index)).build();
   }

   public CertificateSigningRequestCondition buildFirstCondition() {
      return ((CertificateSigningRequestConditionBuilder)this.conditions.get(0)).build();
   }

   public CertificateSigningRequestCondition buildLastCondition() {
      return ((CertificateSigningRequestConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public CertificateSigningRequestCondition buildMatchingCondition(Predicate predicate) {
      for(CertificateSigningRequestConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(CertificateSigningRequestConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public CertificateSigningRequestStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(CertificateSigningRequestCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public CertificateSigningRequestStatusFluent withConditions(CertificateSigningRequestCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(CertificateSigningRequestCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (CertificateSigningRequestCondition)null);
   }

   public ConditionsNested addNewConditionLike(CertificateSigningRequestCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, CertificateSigningRequestCondition item) {
      return new ConditionsNested(index, item);
   }

   public ConditionsNested editCondition(int index) {
      if (this.conditions.size() <= index) {
         throw new RuntimeException("Can't edit conditions. Index exceeds size.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editFirstCondition() {
      if (this.conditions.size() == 0) {
         throw new RuntimeException("Can't edit first conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(0, this.buildCondition(0));
      }
   }

   public ConditionsNested editLastCondition() {
      int index = this.conditions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editMatchingCondition(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.conditions.size(); ++i) {
         if (predicate.test((CertificateSigningRequestConditionBuilder)this.conditions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching conditions. No match found.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public CertificateSigningRequestStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public CertificateSigningRequestStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public CertificateSigningRequestStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public CertificateSigningRequestStatusFluent removeFromAdditionalProperties(Map map) {
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

   public CertificateSigningRequestStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            CertificateSigningRequestStatusFluent that = (CertificateSigningRequestStatusFluent)o;
            if (!Objects.equals(this.certificate, that.certificate)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
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
      return Objects.hash(new Object[]{this.certificate, this.conditions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.certificate != null) {
         sb.append("certificate:");
         sb.append(this.certificate + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends CertificateSigningRequestConditionFluent implements Nested {
      CertificateSigningRequestConditionBuilder builder;
      int index;

      ConditionsNested(int index, CertificateSigningRequestCondition item) {
         this.index = index;
         this.builder = new CertificateSigningRequestConditionBuilder(this, item);
      }

      public Object and() {
         return CertificateSigningRequestStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }
}
