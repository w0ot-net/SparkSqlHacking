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

public class NodeSpecFluent extends BaseFluent {
   private NodeConfigSourceBuilder configSource;
   private String externalID;
   private String podCIDR;
   private List podCIDRs = new ArrayList();
   private String providerID;
   private ArrayList taints = new ArrayList();
   private Boolean unschedulable;
   private Map additionalProperties;

   public NodeSpecFluent() {
   }

   public NodeSpecFluent(NodeSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeSpec instance) {
      instance = instance != null ? instance : new NodeSpec();
      if (instance != null) {
         this.withConfigSource(instance.getConfigSource());
         this.withExternalID(instance.getExternalID());
         this.withPodCIDR(instance.getPodCIDR());
         this.withPodCIDRs(instance.getPodCIDRs());
         this.withProviderID(instance.getProviderID());
         this.withTaints(instance.getTaints());
         this.withUnschedulable(instance.getUnschedulable());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeConfigSource buildConfigSource() {
      return this.configSource != null ? this.configSource.build() : null;
   }

   public NodeSpecFluent withConfigSource(NodeConfigSource configSource) {
      this._visitables.remove("configSource");
      if (configSource != null) {
         this.configSource = new NodeConfigSourceBuilder(configSource);
         this._visitables.get("configSource").add(this.configSource);
      } else {
         this.configSource = null;
         this._visitables.get("configSource").remove(this.configSource);
      }

      return this;
   }

   public boolean hasConfigSource() {
      return this.configSource != null;
   }

   public ConfigSourceNested withNewConfigSource() {
      return new ConfigSourceNested((NodeConfigSource)null);
   }

   public ConfigSourceNested withNewConfigSourceLike(NodeConfigSource item) {
      return new ConfigSourceNested(item);
   }

   public ConfigSourceNested editConfigSource() {
      return this.withNewConfigSourceLike((NodeConfigSource)Optional.ofNullable(this.buildConfigSource()).orElse((Object)null));
   }

   public ConfigSourceNested editOrNewConfigSource() {
      return this.withNewConfigSourceLike((NodeConfigSource)Optional.ofNullable(this.buildConfigSource()).orElse((new NodeConfigSourceBuilder()).build()));
   }

   public ConfigSourceNested editOrNewConfigSourceLike(NodeConfigSource item) {
      return this.withNewConfigSourceLike((NodeConfigSource)Optional.ofNullable(this.buildConfigSource()).orElse(item));
   }

   public String getExternalID() {
      return this.externalID;
   }

   public NodeSpecFluent withExternalID(String externalID) {
      this.externalID = externalID;
      return this;
   }

   public boolean hasExternalID() {
      return this.externalID != null;
   }

   public String getPodCIDR() {
      return this.podCIDR;
   }

   public NodeSpecFluent withPodCIDR(String podCIDR) {
      this.podCIDR = podCIDR;
      return this;
   }

   public boolean hasPodCIDR() {
      return this.podCIDR != null;
   }

   public NodeSpecFluent addToPodCIDRs(int index, String item) {
      if (this.podCIDRs == null) {
         this.podCIDRs = new ArrayList();
      }

      this.podCIDRs.add(index, item);
      return this;
   }

   public NodeSpecFluent setToPodCIDRs(int index, String item) {
      if (this.podCIDRs == null) {
         this.podCIDRs = new ArrayList();
      }

      this.podCIDRs.set(index, item);
      return this;
   }

   public NodeSpecFluent addToPodCIDRs(String... items) {
      if (this.podCIDRs == null) {
         this.podCIDRs = new ArrayList();
      }

      for(String item : items) {
         this.podCIDRs.add(item);
      }

      return this;
   }

   public NodeSpecFluent addAllToPodCIDRs(Collection items) {
      if (this.podCIDRs == null) {
         this.podCIDRs = new ArrayList();
      }

      for(String item : items) {
         this.podCIDRs.add(item);
      }

      return this;
   }

   public NodeSpecFluent removeFromPodCIDRs(String... items) {
      if (this.podCIDRs == null) {
         return this;
      } else {
         for(String item : items) {
            this.podCIDRs.remove(item);
         }

         return this;
      }
   }

   public NodeSpecFluent removeAllFromPodCIDRs(Collection items) {
      if (this.podCIDRs == null) {
         return this;
      } else {
         for(String item : items) {
            this.podCIDRs.remove(item);
         }

         return this;
      }
   }

   public List getPodCIDRs() {
      return this.podCIDRs;
   }

   public String getPodCIDR(int index) {
      return (String)this.podCIDRs.get(index);
   }

   public String getFirstPodCIDR() {
      return (String)this.podCIDRs.get(0);
   }

   public String getLastPodCIDR() {
      return (String)this.podCIDRs.get(this.podCIDRs.size() - 1);
   }

   public String getMatchingPodCIDR(Predicate predicate) {
      for(String item : this.podCIDRs) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingPodCIDR(Predicate predicate) {
      for(String item : this.podCIDRs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeSpecFluent withPodCIDRs(List podCIDRs) {
      if (podCIDRs != null) {
         this.podCIDRs = new ArrayList();

         for(String item : podCIDRs) {
            this.addToPodCIDRs(item);
         }
      } else {
         this.podCIDRs = null;
      }

      return this;
   }

   public NodeSpecFluent withPodCIDRs(String... podCIDRs) {
      if (this.podCIDRs != null) {
         this.podCIDRs.clear();
         this._visitables.remove("podCIDRs");
      }

      if (podCIDRs != null) {
         for(String item : podCIDRs) {
            this.addToPodCIDRs(item);
         }
      }

      return this;
   }

   public boolean hasPodCIDRs() {
      return this.podCIDRs != null && !this.podCIDRs.isEmpty();
   }

   public String getProviderID() {
      return this.providerID;
   }

   public NodeSpecFluent withProviderID(String providerID) {
      this.providerID = providerID;
      return this;
   }

   public boolean hasProviderID() {
      return this.providerID != null;
   }

   public NodeSpecFluent addToTaints(int index, Taint item) {
      if (this.taints == null) {
         this.taints = new ArrayList();
      }

      TaintBuilder builder = new TaintBuilder(item);
      if (index >= 0 && index < this.taints.size()) {
         this._visitables.get("taints").add(index, builder);
         this.taints.add(index, builder);
      } else {
         this._visitables.get("taints").add(builder);
         this.taints.add(builder);
      }

      return this;
   }

   public NodeSpecFluent setToTaints(int index, Taint item) {
      if (this.taints == null) {
         this.taints = new ArrayList();
      }

      TaintBuilder builder = new TaintBuilder(item);
      if (index >= 0 && index < this.taints.size()) {
         this._visitables.get("taints").set(index, builder);
         this.taints.set(index, builder);
      } else {
         this._visitables.get("taints").add(builder);
         this.taints.add(builder);
      }

      return this;
   }

   public NodeSpecFluent addToTaints(Taint... items) {
      if (this.taints == null) {
         this.taints = new ArrayList();
      }

      for(Taint item : items) {
         TaintBuilder builder = new TaintBuilder(item);
         this._visitables.get("taints").add(builder);
         this.taints.add(builder);
      }

      return this;
   }

   public NodeSpecFluent addAllToTaints(Collection items) {
      if (this.taints == null) {
         this.taints = new ArrayList();
      }

      for(Taint item : items) {
         TaintBuilder builder = new TaintBuilder(item);
         this._visitables.get("taints").add(builder);
         this.taints.add(builder);
      }

      return this;
   }

   public NodeSpecFluent removeFromTaints(Taint... items) {
      if (this.taints == null) {
         return this;
      } else {
         for(Taint item : items) {
            TaintBuilder builder = new TaintBuilder(item);
            this._visitables.get("taints").remove(builder);
            this.taints.remove(builder);
         }

         return this;
      }
   }

   public NodeSpecFluent removeAllFromTaints(Collection items) {
      if (this.taints == null) {
         return this;
      } else {
         for(Taint item : items) {
            TaintBuilder builder = new TaintBuilder(item);
            this._visitables.get("taints").remove(builder);
            this.taints.remove(builder);
         }

         return this;
      }
   }

   public NodeSpecFluent removeMatchingFromTaints(Predicate predicate) {
      if (this.taints == null) {
         return this;
      } else {
         Iterator<TaintBuilder> each = this.taints.iterator();
         List visitables = this._visitables.get("taints");

         while(each.hasNext()) {
            TaintBuilder builder = (TaintBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildTaints() {
      return this.taints != null ? build(this.taints) : null;
   }

   public Taint buildTaint(int index) {
      return ((TaintBuilder)this.taints.get(index)).build();
   }

   public Taint buildFirstTaint() {
      return ((TaintBuilder)this.taints.get(0)).build();
   }

   public Taint buildLastTaint() {
      return ((TaintBuilder)this.taints.get(this.taints.size() - 1)).build();
   }

   public Taint buildMatchingTaint(Predicate predicate) {
      for(TaintBuilder item : this.taints) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingTaint(Predicate predicate) {
      for(TaintBuilder item : this.taints) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeSpecFluent withTaints(List taints) {
      if (this.taints != null) {
         this._visitables.get("taints").clear();
      }

      if (taints != null) {
         this.taints = new ArrayList();

         for(Taint item : taints) {
            this.addToTaints(item);
         }
      } else {
         this.taints = null;
      }

      return this;
   }

   public NodeSpecFluent withTaints(Taint... taints) {
      if (this.taints != null) {
         this.taints.clear();
         this._visitables.remove("taints");
      }

      if (taints != null) {
         for(Taint item : taints) {
            this.addToTaints(item);
         }
      }

      return this;
   }

   public boolean hasTaints() {
      return this.taints != null && !this.taints.isEmpty();
   }

   public NodeSpecFluent addNewTaint(String effect, String key, String timeAdded, String value) {
      return this.addToTaints(new Taint(effect, key, timeAdded, value));
   }

   public TaintsNested addNewTaint() {
      return new TaintsNested(-1, (Taint)null);
   }

   public TaintsNested addNewTaintLike(Taint item) {
      return new TaintsNested(-1, item);
   }

   public TaintsNested setNewTaintLike(int index, Taint item) {
      return new TaintsNested(index, item);
   }

   public TaintsNested editTaint(int index) {
      if (this.taints.size() <= index) {
         throw new RuntimeException("Can't edit taints. Index exceeds size.");
      } else {
         return this.setNewTaintLike(index, this.buildTaint(index));
      }
   }

   public TaintsNested editFirstTaint() {
      if (this.taints.size() == 0) {
         throw new RuntimeException("Can't edit first taints. The list is empty.");
      } else {
         return this.setNewTaintLike(0, this.buildTaint(0));
      }
   }

   public TaintsNested editLastTaint() {
      int index = this.taints.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last taints. The list is empty.");
      } else {
         return this.setNewTaintLike(index, this.buildTaint(index));
      }
   }

   public TaintsNested editMatchingTaint(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.taints.size(); ++i) {
         if (predicate.test((TaintBuilder)this.taints.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching taints. No match found.");
      } else {
         return this.setNewTaintLike(index, this.buildTaint(index));
      }
   }

   public Boolean getUnschedulable() {
      return this.unschedulable;
   }

   public NodeSpecFluent withUnschedulable(Boolean unschedulable) {
      this.unschedulable = unschedulable;
      return this;
   }

   public boolean hasUnschedulable() {
      return this.unschedulable != null;
   }

   public NodeSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeSpecFluent removeFromAdditionalProperties(Map map) {
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

   public NodeSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeSpecFluent that = (NodeSpecFluent)o;
            if (!Objects.equals(this.configSource, that.configSource)) {
               return false;
            } else if (!Objects.equals(this.externalID, that.externalID)) {
               return false;
            } else if (!Objects.equals(this.podCIDR, that.podCIDR)) {
               return false;
            } else if (!Objects.equals(this.podCIDRs, that.podCIDRs)) {
               return false;
            } else if (!Objects.equals(this.providerID, that.providerID)) {
               return false;
            } else if (!Objects.equals(this.taints, that.taints)) {
               return false;
            } else if (!Objects.equals(this.unschedulable, that.unschedulable)) {
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
      return Objects.hash(new Object[]{this.configSource, this.externalID, this.podCIDR, this.podCIDRs, this.providerID, this.taints, this.unschedulable, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.configSource != null) {
         sb.append("configSource:");
         sb.append(this.configSource + ",");
      }

      if (this.externalID != null) {
         sb.append("externalID:");
         sb.append(this.externalID + ",");
      }

      if (this.podCIDR != null) {
         sb.append("podCIDR:");
         sb.append(this.podCIDR + ",");
      }

      if (this.podCIDRs != null && !this.podCIDRs.isEmpty()) {
         sb.append("podCIDRs:");
         sb.append(this.podCIDRs + ",");
      }

      if (this.providerID != null) {
         sb.append("providerID:");
         sb.append(this.providerID + ",");
      }

      if (this.taints != null && !this.taints.isEmpty()) {
         sb.append("taints:");
         sb.append(this.taints + ",");
      }

      if (this.unschedulable != null) {
         sb.append("unschedulable:");
         sb.append(this.unschedulable + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public NodeSpecFluent withUnschedulable() {
      return this.withUnschedulable(true);
   }

   public class ConfigSourceNested extends NodeConfigSourceFluent implements Nested {
      NodeConfigSourceBuilder builder;

      ConfigSourceNested(NodeConfigSource item) {
         this.builder = new NodeConfigSourceBuilder(this, item);
      }

      public Object and() {
         return NodeSpecFluent.this.withConfigSource(this.builder.build());
      }

      public Object endConfigSource() {
         return this.and();
      }
   }

   public class TaintsNested extends TaintFluent implements Nested {
      TaintBuilder builder;
      int index;

      TaintsNested(int index, Taint item) {
         this.index = index;
         this.builder = new TaintBuilder(this, item);
      }

      public Object and() {
         return NodeSpecFluent.this.setToTaints(this.index, this.builder.build());
      }

      public Object endTaint() {
         return this.and();
      }
   }
}
