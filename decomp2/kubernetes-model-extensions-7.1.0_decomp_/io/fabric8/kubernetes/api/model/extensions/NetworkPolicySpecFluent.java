package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class NetworkPolicySpecFluent extends BaseFluent {
   private ArrayList egress = new ArrayList();
   private ArrayList ingress = new ArrayList();
   private LabelSelectorBuilder podSelector;
   private List policyTypes = new ArrayList();
   private Map additionalProperties;

   public NetworkPolicySpecFluent() {
   }

   public NetworkPolicySpecFluent(NetworkPolicySpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NetworkPolicySpec instance) {
      instance = instance != null ? instance : new NetworkPolicySpec();
      if (instance != null) {
         this.withEgress(instance.getEgress());
         this.withIngress(instance.getIngress());
         this.withPodSelector(instance.getPodSelector());
         this.withPolicyTypes(instance.getPolicyTypes());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NetworkPolicySpecFluent addToEgress(int index, NetworkPolicyEgressRule item) {
      if (this.egress == null) {
         this.egress = new ArrayList();
      }

      NetworkPolicyEgressRuleBuilder builder = new NetworkPolicyEgressRuleBuilder(item);
      if (index >= 0 && index < this.egress.size()) {
         this._visitables.get("egress").add(index, builder);
         this.egress.add(index, builder);
      } else {
         this._visitables.get("egress").add(builder);
         this.egress.add(builder);
      }

      return this;
   }

   public NetworkPolicySpecFluent setToEgress(int index, NetworkPolicyEgressRule item) {
      if (this.egress == null) {
         this.egress = new ArrayList();
      }

      NetworkPolicyEgressRuleBuilder builder = new NetworkPolicyEgressRuleBuilder(item);
      if (index >= 0 && index < this.egress.size()) {
         this._visitables.get("egress").set(index, builder);
         this.egress.set(index, builder);
      } else {
         this._visitables.get("egress").add(builder);
         this.egress.add(builder);
      }

      return this;
   }

   public NetworkPolicySpecFluent addToEgress(NetworkPolicyEgressRule... items) {
      if (this.egress == null) {
         this.egress = new ArrayList();
      }

      for(NetworkPolicyEgressRule item : items) {
         NetworkPolicyEgressRuleBuilder builder = new NetworkPolicyEgressRuleBuilder(item);
         this._visitables.get("egress").add(builder);
         this.egress.add(builder);
      }

      return this;
   }

   public NetworkPolicySpecFluent addAllToEgress(Collection items) {
      if (this.egress == null) {
         this.egress = new ArrayList();
      }

      for(NetworkPolicyEgressRule item : items) {
         NetworkPolicyEgressRuleBuilder builder = new NetworkPolicyEgressRuleBuilder(item);
         this._visitables.get("egress").add(builder);
         this.egress.add(builder);
      }

      return this;
   }

   public NetworkPolicySpecFluent removeFromEgress(NetworkPolicyEgressRule... items) {
      if (this.egress == null) {
         return this;
      } else {
         for(NetworkPolicyEgressRule item : items) {
            NetworkPolicyEgressRuleBuilder builder = new NetworkPolicyEgressRuleBuilder(item);
            this._visitables.get("egress").remove(builder);
            this.egress.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicySpecFluent removeAllFromEgress(Collection items) {
      if (this.egress == null) {
         return this;
      } else {
         for(NetworkPolicyEgressRule item : items) {
            NetworkPolicyEgressRuleBuilder builder = new NetworkPolicyEgressRuleBuilder(item);
            this._visitables.get("egress").remove(builder);
            this.egress.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicySpecFluent removeMatchingFromEgress(Predicate predicate) {
      if (this.egress == null) {
         return this;
      } else {
         Iterator<NetworkPolicyEgressRuleBuilder> each = this.egress.iterator();
         List visitables = this._visitables.get("egress");

         while(each.hasNext()) {
            NetworkPolicyEgressRuleBuilder builder = (NetworkPolicyEgressRuleBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildEgress() {
      return this.egress != null ? build(this.egress) : null;
   }

   public NetworkPolicyEgressRule buildEgress(int index) {
      return ((NetworkPolicyEgressRuleBuilder)this.egress.get(index)).build();
   }

   public NetworkPolicyEgressRule buildFirstEgress() {
      return ((NetworkPolicyEgressRuleBuilder)this.egress.get(0)).build();
   }

   public NetworkPolicyEgressRule buildLastEgress() {
      return ((NetworkPolicyEgressRuleBuilder)this.egress.get(this.egress.size() - 1)).build();
   }

   public NetworkPolicyEgressRule buildMatchingEgress(Predicate predicate) {
      for(NetworkPolicyEgressRuleBuilder item : this.egress) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingEgress(Predicate predicate) {
      for(NetworkPolicyEgressRuleBuilder item : this.egress) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NetworkPolicySpecFluent withEgress(List egress) {
      if (this.egress != null) {
         this._visitables.get("egress").clear();
      }

      if (egress != null) {
         this.egress = new ArrayList();

         for(NetworkPolicyEgressRule item : egress) {
            this.addToEgress(item);
         }
      } else {
         this.egress = null;
      }

      return this;
   }

   public NetworkPolicySpecFluent withEgress(NetworkPolicyEgressRule... egress) {
      if (this.egress != null) {
         this.egress.clear();
         this._visitables.remove("egress");
      }

      if (egress != null) {
         for(NetworkPolicyEgressRule item : egress) {
            this.addToEgress(item);
         }
      }

      return this;
   }

   public boolean hasEgress() {
      return this.egress != null && !this.egress.isEmpty();
   }

   public EgressNested addNewEgress() {
      return new EgressNested(-1, (NetworkPolicyEgressRule)null);
   }

   public EgressNested addNewEgressLike(NetworkPolicyEgressRule item) {
      return new EgressNested(-1, item);
   }

   public EgressNested setNewEgressLike(int index, NetworkPolicyEgressRule item) {
      return new EgressNested(index, item);
   }

   public EgressNested editEgress(int index) {
      if (this.egress.size() <= index) {
         throw new RuntimeException("Can't edit egress. Index exceeds size.");
      } else {
         return this.setNewEgressLike(index, this.buildEgress(index));
      }
   }

   public EgressNested editFirstEgress() {
      if (this.egress.size() == 0) {
         throw new RuntimeException("Can't edit first egress. The list is empty.");
      } else {
         return this.setNewEgressLike(0, this.buildEgress(0));
      }
   }

   public EgressNested editLastEgress() {
      int index = this.egress.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last egress. The list is empty.");
      } else {
         return this.setNewEgressLike(index, this.buildEgress(index));
      }
   }

   public EgressNested editMatchingEgress(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.egress.size(); ++i) {
         if (predicate.test((NetworkPolicyEgressRuleBuilder)this.egress.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching egress. No match found.");
      } else {
         return this.setNewEgressLike(index, this.buildEgress(index));
      }
   }

   public NetworkPolicySpecFluent addToIngress(int index, NetworkPolicyIngressRule item) {
      if (this.ingress == null) {
         this.ingress = new ArrayList();
      }

      NetworkPolicyIngressRuleBuilder builder = new NetworkPolicyIngressRuleBuilder(item);
      if (index >= 0 && index < this.ingress.size()) {
         this._visitables.get("ingress").add(index, builder);
         this.ingress.add(index, builder);
      } else {
         this._visitables.get("ingress").add(builder);
         this.ingress.add(builder);
      }

      return this;
   }

   public NetworkPolicySpecFluent setToIngress(int index, NetworkPolicyIngressRule item) {
      if (this.ingress == null) {
         this.ingress = new ArrayList();
      }

      NetworkPolicyIngressRuleBuilder builder = new NetworkPolicyIngressRuleBuilder(item);
      if (index >= 0 && index < this.ingress.size()) {
         this._visitables.get("ingress").set(index, builder);
         this.ingress.set(index, builder);
      } else {
         this._visitables.get("ingress").add(builder);
         this.ingress.add(builder);
      }

      return this;
   }

   public NetworkPolicySpecFluent addToIngress(NetworkPolicyIngressRule... items) {
      if (this.ingress == null) {
         this.ingress = new ArrayList();
      }

      for(NetworkPolicyIngressRule item : items) {
         NetworkPolicyIngressRuleBuilder builder = new NetworkPolicyIngressRuleBuilder(item);
         this._visitables.get("ingress").add(builder);
         this.ingress.add(builder);
      }

      return this;
   }

   public NetworkPolicySpecFluent addAllToIngress(Collection items) {
      if (this.ingress == null) {
         this.ingress = new ArrayList();
      }

      for(NetworkPolicyIngressRule item : items) {
         NetworkPolicyIngressRuleBuilder builder = new NetworkPolicyIngressRuleBuilder(item);
         this._visitables.get("ingress").add(builder);
         this.ingress.add(builder);
      }

      return this;
   }

   public NetworkPolicySpecFluent removeFromIngress(NetworkPolicyIngressRule... items) {
      if (this.ingress == null) {
         return this;
      } else {
         for(NetworkPolicyIngressRule item : items) {
            NetworkPolicyIngressRuleBuilder builder = new NetworkPolicyIngressRuleBuilder(item);
            this._visitables.get("ingress").remove(builder);
            this.ingress.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicySpecFluent removeAllFromIngress(Collection items) {
      if (this.ingress == null) {
         return this;
      } else {
         for(NetworkPolicyIngressRule item : items) {
            NetworkPolicyIngressRuleBuilder builder = new NetworkPolicyIngressRuleBuilder(item);
            this._visitables.get("ingress").remove(builder);
            this.ingress.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicySpecFluent removeMatchingFromIngress(Predicate predicate) {
      if (this.ingress == null) {
         return this;
      } else {
         Iterator<NetworkPolicyIngressRuleBuilder> each = this.ingress.iterator();
         List visitables = this._visitables.get("ingress");

         while(each.hasNext()) {
            NetworkPolicyIngressRuleBuilder builder = (NetworkPolicyIngressRuleBuilder)each.next();
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

   public NetworkPolicyIngressRule buildIngress(int index) {
      return ((NetworkPolicyIngressRuleBuilder)this.ingress.get(index)).build();
   }

   public NetworkPolicyIngressRule buildFirstIngress() {
      return ((NetworkPolicyIngressRuleBuilder)this.ingress.get(0)).build();
   }

   public NetworkPolicyIngressRule buildLastIngress() {
      return ((NetworkPolicyIngressRuleBuilder)this.ingress.get(this.ingress.size() - 1)).build();
   }

   public NetworkPolicyIngressRule buildMatchingIngress(Predicate predicate) {
      for(NetworkPolicyIngressRuleBuilder item : this.ingress) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingIngress(Predicate predicate) {
      for(NetworkPolicyIngressRuleBuilder item : this.ingress) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NetworkPolicySpecFluent withIngress(List ingress) {
      if (this.ingress != null) {
         this._visitables.get("ingress").clear();
      }

      if (ingress != null) {
         this.ingress = new ArrayList();

         for(NetworkPolicyIngressRule item : ingress) {
            this.addToIngress(item);
         }
      } else {
         this.ingress = null;
      }

      return this;
   }

   public NetworkPolicySpecFluent withIngress(NetworkPolicyIngressRule... ingress) {
      if (this.ingress != null) {
         this.ingress.clear();
         this._visitables.remove("ingress");
      }

      if (ingress != null) {
         for(NetworkPolicyIngressRule item : ingress) {
            this.addToIngress(item);
         }
      }

      return this;
   }

   public boolean hasIngress() {
      return this.ingress != null && !this.ingress.isEmpty();
   }

   public IngressNested addNewIngress() {
      return new IngressNested(-1, (NetworkPolicyIngressRule)null);
   }

   public IngressNested addNewIngressLike(NetworkPolicyIngressRule item) {
      return new IngressNested(-1, item);
   }

   public IngressNested setNewIngressLike(int index, NetworkPolicyIngressRule item) {
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
         if (predicate.test((NetworkPolicyIngressRuleBuilder)this.ingress.get(i))) {
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

   public LabelSelector buildPodSelector() {
      return this.podSelector != null ? this.podSelector.build() : null;
   }

   public NetworkPolicySpecFluent withPodSelector(LabelSelector podSelector) {
      this._visitables.remove("podSelector");
      if (podSelector != null) {
         this.podSelector = new LabelSelectorBuilder(podSelector);
         this._visitables.get("podSelector").add(this.podSelector);
      } else {
         this.podSelector = null;
         this._visitables.get("podSelector").remove(this.podSelector);
      }

      return this;
   }

   public boolean hasPodSelector() {
      return this.podSelector != null;
   }

   public PodSelectorNested withNewPodSelector() {
      return new PodSelectorNested((LabelSelector)null);
   }

   public PodSelectorNested withNewPodSelectorLike(LabelSelector item) {
      return new PodSelectorNested(item);
   }

   public PodSelectorNested editPodSelector() {
      return this.withNewPodSelectorLike((LabelSelector)Optional.ofNullable(this.buildPodSelector()).orElse((Object)null));
   }

   public PodSelectorNested editOrNewPodSelector() {
      return this.withNewPodSelectorLike((LabelSelector)Optional.ofNullable(this.buildPodSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public PodSelectorNested editOrNewPodSelectorLike(LabelSelector item) {
      return this.withNewPodSelectorLike((LabelSelector)Optional.ofNullable(this.buildPodSelector()).orElse(item));
   }

   public NetworkPolicySpecFluent addToPolicyTypes(int index, String item) {
      if (this.policyTypes == null) {
         this.policyTypes = new ArrayList();
      }

      this.policyTypes.add(index, item);
      return this;
   }

   public NetworkPolicySpecFluent setToPolicyTypes(int index, String item) {
      if (this.policyTypes == null) {
         this.policyTypes = new ArrayList();
      }

      this.policyTypes.set(index, item);
      return this;
   }

   public NetworkPolicySpecFluent addToPolicyTypes(String... items) {
      if (this.policyTypes == null) {
         this.policyTypes = new ArrayList();
      }

      for(String item : items) {
         this.policyTypes.add(item);
      }

      return this;
   }

   public NetworkPolicySpecFluent addAllToPolicyTypes(Collection items) {
      if (this.policyTypes == null) {
         this.policyTypes = new ArrayList();
      }

      for(String item : items) {
         this.policyTypes.add(item);
      }

      return this;
   }

   public NetworkPolicySpecFluent removeFromPolicyTypes(String... items) {
      if (this.policyTypes == null) {
         return this;
      } else {
         for(String item : items) {
            this.policyTypes.remove(item);
         }

         return this;
      }
   }

   public NetworkPolicySpecFluent removeAllFromPolicyTypes(Collection items) {
      if (this.policyTypes == null) {
         return this;
      } else {
         for(String item : items) {
            this.policyTypes.remove(item);
         }

         return this;
      }
   }

   public List getPolicyTypes() {
      return this.policyTypes;
   }

   public String getPolicyType(int index) {
      return (String)this.policyTypes.get(index);
   }

   public String getFirstPolicyType() {
      return (String)this.policyTypes.get(0);
   }

   public String getLastPolicyType() {
      return (String)this.policyTypes.get(this.policyTypes.size() - 1);
   }

   public String getMatchingPolicyType(Predicate predicate) {
      for(String item : this.policyTypes) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingPolicyType(Predicate predicate) {
      for(String item : this.policyTypes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NetworkPolicySpecFluent withPolicyTypes(List policyTypes) {
      if (policyTypes != null) {
         this.policyTypes = new ArrayList();

         for(String item : policyTypes) {
            this.addToPolicyTypes(item);
         }
      } else {
         this.policyTypes = null;
      }

      return this;
   }

   public NetworkPolicySpecFluent withPolicyTypes(String... policyTypes) {
      if (this.policyTypes != null) {
         this.policyTypes.clear();
         this._visitables.remove("policyTypes");
      }

      if (policyTypes != null) {
         for(String item : policyTypes) {
            this.addToPolicyTypes(item);
         }
      }

      return this;
   }

   public boolean hasPolicyTypes() {
      return this.policyTypes != null && !this.policyTypes.isEmpty();
   }

   public NetworkPolicySpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NetworkPolicySpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NetworkPolicySpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NetworkPolicySpecFluent removeFromAdditionalProperties(Map map) {
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

   public NetworkPolicySpecFluent withAdditionalProperties(Map additionalProperties) {
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
            NetworkPolicySpecFluent that = (NetworkPolicySpecFluent)o;
            if (!Objects.equals(this.egress, that.egress)) {
               return false;
            } else if (!Objects.equals(this.ingress, that.ingress)) {
               return false;
            } else if (!Objects.equals(this.podSelector, that.podSelector)) {
               return false;
            } else if (!Objects.equals(this.policyTypes, that.policyTypes)) {
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
      return Objects.hash(new Object[]{this.egress, this.ingress, this.podSelector, this.policyTypes, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.egress != null && !this.egress.isEmpty()) {
         sb.append("egress:");
         sb.append(this.egress + ",");
      }

      if (this.ingress != null && !this.ingress.isEmpty()) {
         sb.append("ingress:");
         sb.append(this.ingress + ",");
      }

      if (this.podSelector != null) {
         sb.append("podSelector:");
         sb.append(this.podSelector + ",");
      }

      if (this.policyTypes != null && !this.policyTypes.isEmpty()) {
         sb.append("policyTypes:");
         sb.append(this.policyTypes + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class EgressNested extends NetworkPolicyEgressRuleFluent implements Nested {
      NetworkPolicyEgressRuleBuilder builder;
      int index;

      EgressNested(int index, NetworkPolicyEgressRule item) {
         this.index = index;
         this.builder = new NetworkPolicyEgressRuleBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicySpecFluent.this.setToEgress(this.index, this.builder.build());
      }

      public Object endEgress() {
         return this.and();
      }
   }

   public class IngressNested extends NetworkPolicyIngressRuleFluent implements Nested {
      NetworkPolicyIngressRuleBuilder builder;
      int index;

      IngressNested(int index, NetworkPolicyIngressRule item) {
         this.index = index;
         this.builder = new NetworkPolicyIngressRuleBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicySpecFluent.this.setToIngress(this.index, this.builder.build());
      }

      public Object endIngress() {
         return this.and();
      }
   }

   public class PodSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      PodSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicySpecFluent.this.withPodSelector(this.builder.build());
      }

      public Object endPodSelector() {
         return this.and();
      }
   }
}
