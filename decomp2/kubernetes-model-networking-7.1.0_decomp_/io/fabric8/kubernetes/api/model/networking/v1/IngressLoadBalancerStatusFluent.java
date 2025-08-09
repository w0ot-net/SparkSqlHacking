package io.fabric8.kubernetes.api.model.networking.v1;

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

public class IngressLoadBalancerStatusFluent extends BaseFluent {
   private ArrayList ingress = new ArrayList();
   private Map additionalProperties;

   public IngressLoadBalancerStatusFluent() {
   }

   public IngressLoadBalancerStatusFluent(IngressLoadBalancerStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(IngressLoadBalancerStatus instance) {
      instance = instance != null ? instance : new IngressLoadBalancerStatus();
      if (instance != null) {
         this.withIngress(instance.getIngress());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public IngressLoadBalancerStatusFluent addToIngress(int index, IngressLoadBalancerIngress item) {
      if (this.ingress == null) {
         this.ingress = new ArrayList();
      }

      IngressLoadBalancerIngressBuilder builder = new IngressLoadBalancerIngressBuilder(item);
      if (index >= 0 && index < this.ingress.size()) {
         this._visitables.get("ingress").add(index, builder);
         this.ingress.add(index, builder);
      } else {
         this._visitables.get("ingress").add(builder);
         this.ingress.add(builder);
      }

      return this;
   }

   public IngressLoadBalancerStatusFluent setToIngress(int index, IngressLoadBalancerIngress item) {
      if (this.ingress == null) {
         this.ingress = new ArrayList();
      }

      IngressLoadBalancerIngressBuilder builder = new IngressLoadBalancerIngressBuilder(item);
      if (index >= 0 && index < this.ingress.size()) {
         this._visitables.get("ingress").set(index, builder);
         this.ingress.set(index, builder);
      } else {
         this._visitables.get("ingress").add(builder);
         this.ingress.add(builder);
      }

      return this;
   }

   public IngressLoadBalancerStatusFluent addToIngress(IngressLoadBalancerIngress... items) {
      if (this.ingress == null) {
         this.ingress = new ArrayList();
      }

      for(IngressLoadBalancerIngress item : items) {
         IngressLoadBalancerIngressBuilder builder = new IngressLoadBalancerIngressBuilder(item);
         this._visitables.get("ingress").add(builder);
         this.ingress.add(builder);
      }

      return this;
   }

   public IngressLoadBalancerStatusFluent addAllToIngress(Collection items) {
      if (this.ingress == null) {
         this.ingress = new ArrayList();
      }

      for(IngressLoadBalancerIngress item : items) {
         IngressLoadBalancerIngressBuilder builder = new IngressLoadBalancerIngressBuilder(item);
         this._visitables.get("ingress").add(builder);
         this.ingress.add(builder);
      }

      return this;
   }

   public IngressLoadBalancerStatusFluent removeFromIngress(IngressLoadBalancerIngress... items) {
      if (this.ingress == null) {
         return this;
      } else {
         for(IngressLoadBalancerIngress item : items) {
            IngressLoadBalancerIngressBuilder builder = new IngressLoadBalancerIngressBuilder(item);
            this._visitables.get("ingress").remove(builder);
            this.ingress.remove(builder);
         }

         return this;
      }
   }

   public IngressLoadBalancerStatusFluent removeAllFromIngress(Collection items) {
      if (this.ingress == null) {
         return this;
      } else {
         for(IngressLoadBalancerIngress item : items) {
            IngressLoadBalancerIngressBuilder builder = new IngressLoadBalancerIngressBuilder(item);
            this._visitables.get("ingress").remove(builder);
            this.ingress.remove(builder);
         }

         return this;
      }
   }

   public IngressLoadBalancerStatusFluent removeMatchingFromIngress(Predicate predicate) {
      if (this.ingress == null) {
         return this;
      } else {
         Iterator<IngressLoadBalancerIngressBuilder> each = this.ingress.iterator();
         List visitables = this._visitables.get("ingress");

         while(each.hasNext()) {
            IngressLoadBalancerIngressBuilder builder = (IngressLoadBalancerIngressBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildIngress() {
      return this.ingress != null ? build(this.ingress) : null;
   }

   public IngressLoadBalancerIngress buildIngress(int index) {
      return ((IngressLoadBalancerIngressBuilder)this.ingress.get(index)).build();
   }

   public IngressLoadBalancerIngress buildFirstIngress() {
      return ((IngressLoadBalancerIngressBuilder)this.ingress.get(0)).build();
   }

   public IngressLoadBalancerIngress buildLastIngress() {
      return ((IngressLoadBalancerIngressBuilder)this.ingress.get(this.ingress.size() - 1)).build();
   }

   public IngressLoadBalancerIngress buildMatchingIngress(Predicate predicate) {
      for(IngressLoadBalancerIngressBuilder item : this.ingress) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingIngress(Predicate predicate) {
      for(IngressLoadBalancerIngressBuilder item : this.ingress) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public IngressLoadBalancerStatusFluent withIngress(List ingress) {
      if (this.ingress != null) {
         this._visitables.get("ingress").clear();
      }

      if (ingress != null) {
         this.ingress = new ArrayList();

         for(IngressLoadBalancerIngress item : ingress) {
            this.addToIngress(item);
         }
      } else {
         this.ingress = null;
      }

      return this;
   }

   public IngressLoadBalancerStatusFluent withIngress(IngressLoadBalancerIngress... ingress) {
      if (this.ingress != null) {
         this.ingress.clear();
         this._visitables.remove("ingress");
      }

      if (ingress != null) {
         for(IngressLoadBalancerIngress item : ingress) {
            this.addToIngress(item);
         }
      }

      return this;
   }

   public boolean hasIngress() {
      return this.ingress != null && !this.ingress.isEmpty();
   }

   public IngressNested addNewIngress() {
      return new IngressNested(-1, (IngressLoadBalancerIngress)null);
   }

   public IngressNested addNewIngressLike(IngressLoadBalancerIngress item) {
      return new IngressNested(-1, item);
   }

   public IngressNested setNewIngressLike(int index, IngressLoadBalancerIngress item) {
      return new IngressNested(index, item);
   }

   public IngressNested editIngress(int index) {
      if (this.ingress.size() <= index) {
         throw new RuntimeException("Can't edit ingress. Index exceeds size.");
      } else {
         return this.setNewIngressLike(index, this.buildIngress(index));
      }
   }

   public IngressNested editFirstIngress() {
      if (this.ingress.size() == 0) {
         throw new RuntimeException("Can't edit first ingress. The list is empty.");
      } else {
         return this.setNewIngressLike(0, this.buildIngress(0));
      }
   }

   public IngressNested editLastIngress() {
      int index = this.ingress.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last ingress. The list is empty.");
      } else {
         return this.setNewIngressLike(index, this.buildIngress(index));
      }
   }

   public IngressNested editMatchingIngress(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.ingress.size(); ++i) {
         if (predicate.test((IngressLoadBalancerIngressBuilder)this.ingress.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching ingress. No match found.");
      } else {
         return this.setNewIngressLike(index, this.buildIngress(index));
      }
   }

   public IngressLoadBalancerStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public IngressLoadBalancerStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public IngressLoadBalancerStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public IngressLoadBalancerStatusFluent removeFromAdditionalProperties(Map map) {
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

   public IngressLoadBalancerStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            IngressLoadBalancerStatusFluent that = (IngressLoadBalancerStatusFluent)o;
            if (!Objects.equals(this.ingress, that.ingress)) {
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
      return Objects.hash(new Object[]{this.ingress, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.ingress != null && !this.ingress.isEmpty()) {
         sb.append("ingress:");
         sb.append(this.ingress + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class IngressNested extends IngressLoadBalancerIngressFluent implements Nested {
      IngressLoadBalancerIngressBuilder builder;
      int index;

      IngressNested(int index, IngressLoadBalancerIngress item) {
         this.index = index;
         this.builder = new IngressLoadBalancerIngressBuilder(this, item);
      }

      public Object and() {
         return IngressLoadBalancerStatusFluent.this.setToIngress(this.index, this.builder.build());
      }

      public Object endIngress() {
         return this.and();
      }
   }
}
