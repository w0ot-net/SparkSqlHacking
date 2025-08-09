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
import java.util.function.Predicate;

public class LoadBalancerIngressFluent extends BaseFluent {
   private String hostname;
   private String ip;
   private String ipMode;
   private ArrayList ports = new ArrayList();
   private Map additionalProperties;

   public LoadBalancerIngressFluent() {
   }

   public LoadBalancerIngressFluent(LoadBalancerIngress instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LoadBalancerIngress instance) {
      instance = instance != null ? instance : new LoadBalancerIngress();
      if (instance != null) {
         this.withHostname(instance.getHostname());
         this.withIp(instance.getIp());
         this.withIpMode(instance.getIpMode());
         this.withPorts(instance.getPorts());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHostname() {
      return this.hostname;
   }

   public LoadBalancerIngressFluent withHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public boolean hasHostname() {
      return this.hostname != null;
   }

   public String getIp() {
      return this.ip;
   }

   public LoadBalancerIngressFluent withIp(String ip) {
      this.ip = ip;
      return this;
   }

   public boolean hasIp() {
      return this.ip != null;
   }

   public String getIpMode() {
      return this.ipMode;
   }

   public LoadBalancerIngressFluent withIpMode(String ipMode) {
      this.ipMode = ipMode;
      return this;
   }

   public boolean hasIpMode() {
      return this.ipMode != null;
   }

   public LoadBalancerIngressFluent addToPorts(int index, PortStatus item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      PortStatusBuilder builder = new PortStatusBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").add(index, builder);
         this.ports.add(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public LoadBalancerIngressFluent setToPorts(int index, PortStatus item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      PortStatusBuilder builder = new PortStatusBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").set(index, builder);
         this.ports.set(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public LoadBalancerIngressFluent addToPorts(PortStatus... items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(PortStatus item : items) {
         PortStatusBuilder builder = new PortStatusBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public LoadBalancerIngressFluent addAllToPorts(Collection items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(PortStatus item : items) {
         PortStatusBuilder builder = new PortStatusBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public LoadBalancerIngressFluent removeFromPorts(PortStatus... items) {
      if (this.ports == null) {
         return this;
      } else {
         for(PortStatus item : items) {
            PortStatusBuilder builder = new PortStatusBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public LoadBalancerIngressFluent removeAllFromPorts(Collection items) {
      if (this.ports == null) {
         return this;
      } else {
         for(PortStatus item : items) {
            PortStatusBuilder builder = new PortStatusBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public LoadBalancerIngressFluent removeMatchingFromPorts(Predicate predicate) {
      if (this.ports == null) {
         return this;
      } else {
         Iterator<PortStatusBuilder> each = this.ports.iterator();
         List visitables = this._visitables.get("ports");

         while(each.hasNext()) {
            PortStatusBuilder builder = (PortStatusBuilder)each.next();
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

   public PortStatus buildPort(int index) {
      return ((PortStatusBuilder)this.ports.get(index)).build();
   }

   public PortStatus buildFirstPort() {
      return ((PortStatusBuilder)this.ports.get(0)).build();
   }

   public PortStatus buildLastPort() {
      return ((PortStatusBuilder)this.ports.get(this.ports.size() - 1)).build();
   }

   public PortStatus buildMatchingPort(Predicate predicate) {
      for(PortStatusBuilder item : this.ports) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPort(Predicate predicate) {
      for(PortStatusBuilder item : this.ports) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public LoadBalancerIngressFluent withPorts(List ports) {
      if (this.ports != null) {
         this._visitables.get("ports").clear();
      }

      if (ports != null) {
         this.ports = new ArrayList();

         for(PortStatus item : ports) {
            this.addToPorts(item);
         }
      } else {
         this.ports = null;
      }

      return this;
   }

   public LoadBalancerIngressFluent withPorts(PortStatus... ports) {
      if (this.ports != null) {
         this.ports.clear();
         this._visitables.remove("ports");
      }

      if (ports != null) {
         for(PortStatus item : ports) {
            this.addToPorts(item);
         }
      }

      return this;
   }

   public boolean hasPorts() {
      return this.ports != null && !this.ports.isEmpty();
   }

   public LoadBalancerIngressFluent addNewPort(String error, Integer port, String protocol) {
      return this.addToPorts(new PortStatus(error, port, protocol));
   }

   public PortsNested addNewPort() {
      return new PortsNested(-1, (PortStatus)null);
   }

   public PortsNested addNewPortLike(PortStatus item) {
      return new PortsNested(-1, item);
   }

   public PortsNested setNewPortLike(int index, PortStatus item) {
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
         if (predicate.test((PortStatusBuilder)this.ports.get(i))) {
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

   public LoadBalancerIngressFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LoadBalancerIngressFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LoadBalancerIngressFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LoadBalancerIngressFluent removeFromAdditionalProperties(Map map) {
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

   public LoadBalancerIngressFluent withAdditionalProperties(Map additionalProperties) {
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
            LoadBalancerIngressFluent that = (LoadBalancerIngressFluent)o;
            if (!Objects.equals(this.hostname, that.hostname)) {
               return false;
            } else if (!Objects.equals(this.ip, that.ip)) {
               return false;
            } else if (!Objects.equals(this.ipMode, that.ipMode)) {
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
      return Objects.hash(new Object[]{this.hostname, this.ip, this.ipMode, this.ports, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.hostname != null) {
         sb.append("hostname:");
         sb.append(this.hostname + ",");
      }

      if (this.ip != null) {
         sb.append("ip:");
         sb.append(this.ip + ",");
      }

      if (this.ipMode != null) {
         sb.append("ipMode:");
         sb.append(this.ipMode + ",");
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

   public class PortsNested extends PortStatusFluent implements Nested {
      PortStatusBuilder builder;
      int index;

      PortsNested(int index, PortStatus item) {
         this.index = index;
         this.builder = new PortStatusBuilder(this, item);
      }

      public Object and() {
         return LoadBalancerIngressFluent.this.setToPorts(this.index, this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }
}
