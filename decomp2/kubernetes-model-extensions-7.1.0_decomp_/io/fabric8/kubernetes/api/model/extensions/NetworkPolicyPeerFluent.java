package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NetworkPolicyPeerFluent extends BaseFluent {
   private IPBlockBuilder ipBlock;
   private LabelSelectorBuilder namespaceSelector;
   private LabelSelectorBuilder podSelector;
   private Map additionalProperties;

   public NetworkPolicyPeerFluent() {
   }

   public NetworkPolicyPeerFluent(NetworkPolicyPeer instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NetworkPolicyPeer instance) {
      instance = instance != null ? instance : new NetworkPolicyPeer();
      if (instance != null) {
         this.withIpBlock(instance.getIpBlock());
         this.withNamespaceSelector(instance.getNamespaceSelector());
         this.withPodSelector(instance.getPodSelector());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public IPBlock buildIpBlock() {
      return this.ipBlock != null ? this.ipBlock.build() : null;
   }

   public NetworkPolicyPeerFluent withIpBlock(IPBlock ipBlock) {
      this._visitables.remove("ipBlock");
      if (ipBlock != null) {
         this.ipBlock = new IPBlockBuilder(ipBlock);
         this._visitables.get("ipBlock").add(this.ipBlock);
      } else {
         this.ipBlock = null;
         this._visitables.get("ipBlock").remove(this.ipBlock);
      }

      return this;
   }

   public boolean hasIpBlock() {
      return this.ipBlock != null;
   }

   public IpBlockNested withNewIpBlock() {
      return new IpBlockNested((IPBlock)null);
   }

   public IpBlockNested withNewIpBlockLike(IPBlock item) {
      return new IpBlockNested(item);
   }

   public IpBlockNested editIpBlock() {
      return this.withNewIpBlockLike((IPBlock)Optional.ofNullable(this.buildIpBlock()).orElse((Object)null));
   }

   public IpBlockNested editOrNewIpBlock() {
      return this.withNewIpBlockLike((IPBlock)Optional.ofNullable(this.buildIpBlock()).orElse((new IPBlockBuilder()).build()));
   }

   public IpBlockNested editOrNewIpBlockLike(IPBlock item) {
      return this.withNewIpBlockLike((IPBlock)Optional.ofNullable(this.buildIpBlock()).orElse(item));
   }

   public LabelSelector buildNamespaceSelector() {
      return this.namespaceSelector != null ? this.namespaceSelector.build() : null;
   }

   public NetworkPolicyPeerFluent withNamespaceSelector(LabelSelector namespaceSelector) {
      this._visitables.remove("namespaceSelector");
      if (namespaceSelector != null) {
         this.namespaceSelector = new LabelSelectorBuilder(namespaceSelector);
         this._visitables.get("namespaceSelector").add(this.namespaceSelector);
      } else {
         this.namespaceSelector = null;
         this._visitables.get("namespaceSelector").remove(this.namespaceSelector);
      }

      return this;
   }

   public boolean hasNamespaceSelector() {
      return this.namespaceSelector != null;
   }

   public NamespaceSelectorNested withNewNamespaceSelector() {
      return new NamespaceSelectorNested((LabelSelector)null);
   }

   public NamespaceSelectorNested withNewNamespaceSelectorLike(LabelSelector item) {
      return new NamespaceSelectorNested(item);
   }

   public NamespaceSelectorNested editNamespaceSelector() {
      return this.withNewNamespaceSelectorLike((LabelSelector)Optional.ofNullable(this.buildNamespaceSelector()).orElse((Object)null));
   }

   public NamespaceSelectorNested editOrNewNamespaceSelector() {
      return this.withNewNamespaceSelectorLike((LabelSelector)Optional.ofNullable(this.buildNamespaceSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public NamespaceSelectorNested editOrNewNamespaceSelectorLike(LabelSelector item) {
      return this.withNewNamespaceSelectorLike((LabelSelector)Optional.ofNullable(this.buildNamespaceSelector()).orElse(item));
   }

   public LabelSelector buildPodSelector() {
      return this.podSelector != null ? this.podSelector.build() : null;
   }

   public NetworkPolicyPeerFluent withPodSelector(LabelSelector podSelector) {
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

   public NetworkPolicyPeerFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NetworkPolicyPeerFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NetworkPolicyPeerFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NetworkPolicyPeerFluent removeFromAdditionalProperties(Map map) {
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

   public NetworkPolicyPeerFluent withAdditionalProperties(Map additionalProperties) {
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
            NetworkPolicyPeerFluent that = (NetworkPolicyPeerFluent)o;
            if (!Objects.equals(this.ipBlock, that.ipBlock)) {
               return false;
            } else if (!Objects.equals(this.namespaceSelector, that.namespaceSelector)) {
               return false;
            } else if (!Objects.equals(this.podSelector, that.podSelector)) {
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
      return Objects.hash(new Object[]{this.ipBlock, this.namespaceSelector, this.podSelector, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.ipBlock != null) {
         sb.append("ipBlock:");
         sb.append(this.ipBlock + ",");
      }

      if (this.namespaceSelector != null) {
         sb.append("namespaceSelector:");
         sb.append(this.namespaceSelector + ",");
      }

      if (this.podSelector != null) {
         sb.append("podSelector:");
         sb.append(this.podSelector + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class IpBlockNested extends IPBlockFluent implements Nested {
      IPBlockBuilder builder;

      IpBlockNested(IPBlock item) {
         this.builder = new IPBlockBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicyPeerFluent.this.withIpBlock(this.builder.build());
      }

      public Object endIpBlock() {
         return this.and();
      }
   }

   public class NamespaceSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      NamespaceSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicyPeerFluent.this.withNamespaceSelector(this.builder.build());
      }

      public Object endNamespaceSelector() {
         return this.and();
      }
   }

   public class PodSelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      PodSelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicyPeerFluent.this.withPodSelector(this.builder.build());
      }

      public Object endPodSelector() {
         return this.and();
      }
   }
}
