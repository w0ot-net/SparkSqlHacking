package io.fabric8.kubernetes.api.model.extensions;

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

public class NetworkPolicyEgressRuleFluent extends BaseFluent {
   private ArrayList ports = new ArrayList();
   private ArrayList to = new ArrayList();
   private Map additionalProperties;

   public NetworkPolicyEgressRuleFluent() {
   }

   public NetworkPolicyEgressRuleFluent(NetworkPolicyEgressRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NetworkPolicyEgressRule instance) {
      instance = instance != null ? instance : new NetworkPolicyEgressRule();
      if (instance != null) {
         this.withPorts(instance.getPorts());
         this.withTo(instance.getTo());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NetworkPolicyEgressRuleFluent addToPorts(int index, NetworkPolicyPort item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      NetworkPolicyPortBuilder builder = new NetworkPolicyPortBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").add(index, builder);
         this.ports.add(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent setToPorts(int index, NetworkPolicyPort item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      NetworkPolicyPortBuilder builder = new NetworkPolicyPortBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").set(index, builder);
         this.ports.set(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent addToPorts(NetworkPolicyPort... items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(NetworkPolicyPort item : items) {
         NetworkPolicyPortBuilder builder = new NetworkPolicyPortBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent addAllToPorts(Collection items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(NetworkPolicyPort item : items) {
         NetworkPolicyPortBuilder builder = new NetworkPolicyPortBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent removeFromPorts(NetworkPolicyPort... items) {
      if (this.ports == null) {
         return this;
      } else {
         for(NetworkPolicyPort item : items) {
            NetworkPolicyPortBuilder builder = new NetworkPolicyPortBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicyEgressRuleFluent removeAllFromPorts(Collection items) {
      if (this.ports == null) {
         return this;
      } else {
         for(NetworkPolicyPort item : items) {
            NetworkPolicyPortBuilder builder = new NetworkPolicyPortBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicyEgressRuleFluent removeMatchingFromPorts(Predicate predicate) {
      if (this.ports == null) {
         return this;
      } else {
         Iterator<NetworkPolicyPortBuilder> each = this.ports.iterator();
         List visitables = this._visitables.get("ports");

         while(each.hasNext()) {
            NetworkPolicyPortBuilder builder = (NetworkPolicyPortBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildPorts() {
      return this.ports != null ? build(this.ports) : null;
   }

   public NetworkPolicyPort buildPort(int index) {
      return ((NetworkPolicyPortBuilder)this.ports.get(index)).build();
   }

   public NetworkPolicyPort buildFirstPort() {
      return ((NetworkPolicyPortBuilder)this.ports.get(0)).build();
   }

   public NetworkPolicyPort buildLastPort() {
      return ((NetworkPolicyPortBuilder)this.ports.get(this.ports.size() - 1)).build();
   }

   public NetworkPolicyPort buildMatchingPort(Predicate predicate) {
      for(NetworkPolicyPortBuilder item : this.ports) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPort(Predicate predicate) {
      for(NetworkPolicyPortBuilder item : this.ports) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NetworkPolicyEgressRuleFluent withPorts(List ports) {
      if (this.ports != null) {
         this._visitables.get("ports").clear();
      }

      if (ports != null) {
         this.ports = new ArrayList();

         for(NetworkPolicyPort item : ports) {
            this.addToPorts(item);
         }
      } else {
         this.ports = null;
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent withPorts(NetworkPolicyPort... ports) {
      if (this.ports != null) {
         this.ports.clear();
         this._visitables.remove("ports");
      }

      if (ports != null) {
         for(NetworkPolicyPort item : ports) {
            this.addToPorts(item);
         }
      }

      return this;
   }

   public boolean hasPorts() {
      return this.ports != null && !this.ports.isEmpty();
   }

   public PortsNested addNewPort() {
      return new PortsNested(-1, (NetworkPolicyPort)null);
   }

   public PortsNested addNewPortLike(NetworkPolicyPort item) {
      return new PortsNested(-1, item);
   }

   public PortsNested setNewPortLike(int index, NetworkPolicyPort item) {
      return new PortsNested(index, item);
   }

   public PortsNested editPort(int index) {
      if (this.ports.size() <= index) {
         throw new RuntimeException("Can't edit ports. Index exceeds size.");
      } else {
         return this.setNewPortLike(index, this.buildPort(index));
      }
   }

   public PortsNested editFirstPort() {
      if (this.ports.size() == 0) {
         throw new RuntimeException("Can't edit first ports. The list is empty.");
      } else {
         return this.setNewPortLike(0, this.buildPort(0));
      }
   }

   public PortsNested editLastPort() {
      int index = this.ports.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last ports. The list is empty.");
      } else {
         return this.setNewPortLike(index, this.buildPort(index));
      }
   }

   public PortsNested editMatchingPort(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.ports.size(); ++i) {
         if (predicate.test((NetworkPolicyPortBuilder)this.ports.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching ports. No match found.");
      } else {
         return this.setNewPortLike(index, this.buildPort(index));
      }
   }

   public NetworkPolicyEgressRuleFluent addToTo(int index, NetworkPolicyPeer item) {
      if (this.to == null) {
         this.to = new ArrayList();
      }

      NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
      if (index >= 0 && index < this.to.size()) {
         this._visitables.get("to").add(index, builder);
         this.to.add(index, builder);
      } else {
         this._visitables.get("to").add(builder);
         this.to.add(builder);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent setToTo(int index, NetworkPolicyPeer item) {
      if (this.to == null) {
         this.to = new ArrayList();
      }

      NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
      if (index >= 0 && index < this.to.size()) {
         this._visitables.get("to").set(index, builder);
         this.to.set(index, builder);
      } else {
         this._visitables.get("to").add(builder);
         this.to.add(builder);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent addToTo(NetworkPolicyPeer... items) {
      if (this.to == null) {
         this.to = new ArrayList();
      }

      for(NetworkPolicyPeer item : items) {
         NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
         this._visitables.get("to").add(builder);
         this.to.add(builder);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent addAllToTo(Collection items) {
      if (this.to == null) {
         this.to = new ArrayList();
      }

      for(NetworkPolicyPeer item : items) {
         NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
         this._visitables.get("to").add(builder);
         this.to.add(builder);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent removeFromTo(NetworkPolicyPeer... items) {
      if (this.to == null) {
         return this;
      } else {
         for(NetworkPolicyPeer item : items) {
            NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
            this._visitables.get("to").remove(builder);
            this.to.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicyEgressRuleFluent removeAllFromTo(Collection items) {
      if (this.to == null) {
         return this;
      } else {
         for(NetworkPolicyPeer item : items) {
            NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
            this._visitables.get("to").remove(builder);
            this.to.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicyEgressRuleFluent removeMatchingFromTo(Predicate predicate) {
      if (this.to == null) {
         return this;
      } else {
         Iterator<NetworkPolicyPeerBuilder> each = this.to.iterator();
         List visitables = this._visitables.get("to");

         while(each.hasNext()) {
            NetworkPolicyPeerBuilder builder = (NetworkPolicyPeerBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildTo() {
      return this.to != null ? build(this.to) : null;
   }

   public NetworkPolicyPeer buildTo(int index) {
      return ((NetworkPolicyPeerBuilder)this.to.get(index)).build();
   }

   public NetworkPolicyPeer buildFirstTo() {
      return ((NetworkPolicyPeerBuilder)this.to.get(0)).build();
   }

   public NetworkPolicyPeer buildLastTo() {
      return ((NetworkPolicyPeerBuilder)this.to.get(this.to.size() - 1)).build();
   }

   public NetworkPolicyPeer buildMatchingTo(Predicate predicate) {
      for(NetworkPolicyPeerBuilder item : this.to) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingTo(Predicate predicate) {
      for(NetworkPolicyPeerBuilder item : this.to) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NetworkPolicyEgressRuleFluent withTo(List to) {
      if (this.to != null) {
         this._visitables.get("to").clear();
      }

      if (to != null) {
         this.to = new ArrayList();

         for(NetworkPolicyPeer item : to) {
            this.addToTo(item);
         }
      } else {
         this.to = null;
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent withTo(NetworkPolicyPeer... to) {
      if (this.to != null) {
         this.to.clear();
         this._visitables.remove("to");
      }

      if (to != null) {
         for(NetworkPolicyPeer item : to) {
            this.addToTo(item);
         }
      }

      return this;
   }

   public boolean hasTo() {
      return this.to != null && !this.to.isEmpty();
   }

   public ToNested addNewTo() {
      return new ToNested(-1, (NetworkPolicyPeer)null);
   }

   public ToNested addNewToLike(NetworkPolicyPeer item) {
      return new ToNested(-1, item);
   }

   public ToNested setNewToLike(int index, NetworkPolicyPeer item) {
      return new ToNested(index, item);
   }

   public ToNested editTo(int index) {
      if (this.to.size() <= index) {
         throw new RuntimeException("Can't edit to. Index exceeds size.");
      } else {
         return this.setNewToLike(index, this.buildTo(index));
      }
   }

   public ToNested editFirstTo() {
      if (this.to.size() == 0) {
         throw new RuntimeException("Can't edit first to. The list is empty.");
      } else {
         return this.setNewToLike(0, this.buildTo(0));
      }
   }

   public ToNested editLastTo() {
      int index = this.to.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last to. The list is empty.");
      } else {
         return this.setNewToLike(index, this.buildTo(index));
      }
   }

   public ToNested editMatchingTo(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.to.size(); ++i) {
         if (predicate.test((NetworkPolicyPeerBuilder)this.to.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching to. No match found.");
      } else {
         return this.setNewToLike(index, this.buildTo(index));
      }
   }

   public NetworkPolicyEgressRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NetworkPolicyEgressRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NetworkPolicyEgressRuleFluent removeFromAdditionalProperties(Map map) {
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

   public NetworkPolicyEgressRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            NetworkPolicyEgressRuleFluent that = (NetworkPolicyEgressRuleFluent)o;
            if (!Objects.equals(this.ports, that.ports)) {
               return false;
            } else if (!Objects.equals(this.to, that.to)) {
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
      return Objects.hash(new Object[]{this.ports, this.to, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.ports != null && !this.ports.isEmpty()) {
         sb.append("ports:");
         sb.append(this.ports + ",");
      }

      if (this.to != null && !this.to.isEmpty()) {
         sb.append("to:");
         sb.append(this.to + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class PortsNested extends NetworkPolicyPortFluent implements Nested {
      NetworkPolicyPortBuilder builder;
      int index;

      PortsNested(int index, NetworkPolicyPort item) {
         this.index = index;
         this.builder = new NetworkPolicyPortBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicyEgressRuleFluent.this.setToPorts(this.index, this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }

   public class ToNested extends NetworkPolicyPeerFluent implements Nested {
      NetworkPolicyPeerBuilder builder;
      int index;

      ToNested(int index, NetworkPolicyPeer item) {
         this.index = index;
         this.builder = new NetworkPolicyPeerBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicyEgressRuleFluent.this.setToTo(this.index, this.builder.build());
      }

      public Object endTo() {
         return this.and();
      }
   }
}
