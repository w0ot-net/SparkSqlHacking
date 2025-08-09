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

public class NetworkPolicyIngressRuleFluent extends BaseFluent {
   private ArrayList from = new ArrayList();
   private ArrayList ports = new ArrayList();
   private Map additionalProperties;

   public NetworkPolicyIngressRuleFluent() {
   }

   public NetworkPolicyIngressRuleFluent(NetworkPolicyIngressRule instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NetworkPolicyIngressRule instance) {
      instance = instance != null ? instance : new NetworkPolicyIngressRule();
      if (instance != null) {
         this.withFrom(instance.getFrom());
         this.withPorts(instance.getPorts());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NetworkPolicyIngressRuleFluent addToFrom(int index, NetworkPolicyPeer item) {
      if (this.from == null) {
         this.from = new ArrayList();
      }

      NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
      if (index >= 0 && index < this.from.size()) {
         this._visitables.get("from").add(index, builder);
         this.from.add(index, builder);
      } else {
         this._visitables.get("from").add(builder);
         this.from.add(builder);
      }

      return this;
   }

   public NetworkPolicyIngressRuleFluent setToFrom(int index, NetworkPolicyPeer item) {
      if (this.from == null) {
         this.from = new ArrayList();
      }

      NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
      if (index >= 0 && index < this.from.size()) {
         this._visitables.get("from").set(index, builder);
         this.from.set(index, builder);
      } else {
         this._visitables.get("from").add(builder);
         this.from.add(builder);
      }

      return this;
   }

   public NetworkPolicyIngressRuleFluent addToFrom(NetworkPolicyPeer... items) {
      if (this.from == null) {
         this.from = new ArrayList();
      }

      for(NetworkPolicyPeer item : items) {
         NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
         this._visitables.get("from").add(builder);
         this.from.add(builder);
      }

      return this;
   }

   public NetworkPolicyIngressRuleFluent addAllToFrom(Collection items) {
      if (this.from == null) {
         this.from = new ArrayList();
      }

      for(NetworkPolicyPeer item : items) {
         NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
         this._visitables.get("from").add(builder);
         this.from.add(builder);
      }

      return this;
   }

   public NetworkPolicyIngressRuleFluent removeFromFrom(NetworkPolicyPeer... items) {
      if (this.from == null) {
         return this;
      } else {
         for(NetworkPolicyPeer item : items) {
            NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
            this._visitables.get("from").remove(builder);
            this.from.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicyIngressRuleFluent removeAllFromFrom(Collection items) {
      if (this.from == null) {
         return this;
      } else {
         for(NetworkPolicyPeer item : items) {
            NetworkPolicyPeerBuilder builder = new NetworkPolicyPeerBuilder(item);
            this._visitables.get("from").remove(builder);
            this.from.remove(builder);
         }

         return this;
      }
   }

   public NetworkPolicyIngressRuleFluent removeMatchingFromFrom(Predicate predicate) {
      if (this.from == null) {
         return this;
      } else {
         Iterator<NetworkPolicyPeerBuilder> each = this.from.iterator();
         List visitables = this._visitables.get("from");

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

   public List buildFrom() {
      return this.from != null ? build(this.from) : null;
   }

   public NetworkPolicyPeer buildFrom(int index) {
      return ((NetworkPolicyPeerBuilder)this.from.get(index)).build();
   }

   public NetworkPolicyPeer buildFirstFrom() {
      return ((NetworkPolicyPeerBuilder)this.from.get(0)).build();
   }

   public NetworkPolicyPeer buildLastFrom() {
      return ((NetworkPolicyPeerBuilder)this.from.get(this.from.size() - 1)).build();
   }

   public NetworkPolicyPeer buildMatchingFrom(Predicate predicate) {
      for(NetworkPolicyPeerBuilder item : this.from) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingFrom(Predicate predicate) {
      for(NetworkPolicyPeerBuilder item : this.from) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NetworkPolicyIngressRuleFluent withFrom(List from) {
      if (this.from != null) {
         this._visitables.get("from").clear();
      }

      if (from != null) {
         this.from = new ArrayList();

         for(NetworkPolicyPeer item : from) {
            this.addToFrom(item);
         }
      } else {
         this.from = null;
      }

      return this;
   }

   public NetworkPolicyIngressRuleFluent withFrom(NetworkPolicyPeer... from) {
      if (this.from != null) {
         this.from.clear();
         this._visitables.remove("from");
      }

      if (from != null) {
         for(NetworkPolicyPeer item : from) {
            this.addToFrom(item);
         }
      }

      return this;
   }

   public boolean hasFrom() {
      return this.from != null && !this.from.isEmpty();
   }

   public FromNested addNewFrom() {
      return new FromNested(-1, (NetworkPolicyPeer)null);
   }

   public FromNested addNewFromLike(NetworkPolicyPeer item) {
      return new FromNested(-1, item);
   }

   public FromNested setNewFromLike(int index, NetworkPolicyPeer item) {
      return new FromNested(index, item);
   }

   public FromNested editFrom(int index) {
      if (this.from.size() <= index) {
         throw new RuntimeException("Can't edit from. Index exceeds size.");
      } else {
         return this.setNewFromLike(index, this.buildFrom(index));
      }
   }

   public FromNested editFirstFrom() {
      if (this.from.size() == 0) {
         throw new RuntimeException("Can't edit first from. The list is empty.");
      } else {
         return this.setNewFromLike(0, this.buildFrom(0));
      }
   }

   public FromNested editLastFrom() {
      int index = this.from.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last from. The list is empty.");
      } else {
         return this.setNewFromLike(index, this.buildFrom(index));
      }
   }

   public FromNested editMatchingFrom(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.from.size(); ++i) {
         if (predicate.test((NetworkPolicyPeerBuilder)this.from.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching from. No match found.");
      } else {
         return this.setNewFromLike(index, this.buildFrom(index));
      }
   }

   public NetworkPolicyIngressRuleFluent addToPorts(int index, NetworkPolicyPort item) {
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

   public NetworkPolicyIngressRuleFluent setToPorts(int index, NetworkPolicyPort item) {
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

   public NetworkPolicyIngressRuleFluent addToPorts(NetworkPolicyPort... items) {
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

   public NetworkPolicyIngressRuleFluent addAllToPorts(Collection items) {
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

   public NetworkPolicyIngressRuleFluent removeFromPorts(NetworkPolicyPort... items) {
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

   public NetworkPolicyIngressRuleFluent removeAllFromPorts(Collection items) {
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

   public NetworkPolicyIngressRuleFluent removeMatchingFromPorts(Predicate predicate) {
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

   public NetworkPolicyIngressRuleFluent withPorts(List ports) {
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

   public NetworkPolicyIngressRuleFluent withPorts(NetworkPolicyPort... ports) {
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

   public NetworkPolicyIngressRuleFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NetworkPolicyIngressRuleFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NetworkPolicyIngressRuleFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NetworkPolicyIngressRuleFluent removeFromAdditionalProperties(Map map) {
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

   public NetworkPolicyIngressRuleFluent withAdditionalProperties(Map additionalProperties) {
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
            NetworkPolicyIngressRuleFluent that = (NetworkPolicyIngressRuleFluent)o;
            if (!Objects.equals(this.from, that.from)) {
               return false;
            } else if (!Objects.equals(this.ports, that.ports)) {
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
      return Objects.hash(new Object[]{this.from, this.ports, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.from != null && !this.from.isEmpty()) {
         sb.append("from:");
         sb.append(this.from + ",");
      }

      if (this.ports != null && !this.ports.isEmpty()) {
         sb.append("ports:");
         sb.append(this.ports + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class FromNested extends NetworkPolicyPeerFluent implements Nested {
      NetworkPolicyPeerBuilder builder;
      int index;

      FromNested(int index, NetworkPolicyPeer item) {
         this.index = index;
         this.builder = new NetworkPolicyPeerBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicyIngressRuleFluent.this.setToFrom(this.index, this.builder.build());
      }

      public Object endFrom() {
         return this.and();
      }
   }

   public class PortsNested extends NetworkPolicyPortFluent implements Nested {
      NetworkPolicyPortBuilder builder;
      int index;

      PortsNested(int index, NetworkPolicyPort item) {
         this.index = index;
         this.builder = new NetworkPolicyPortBuilder(this, item);
      }

      public Object and() {
         return NetworkPolicyIngressRuleFluent.this.setToPorts(this.index, this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }
}
