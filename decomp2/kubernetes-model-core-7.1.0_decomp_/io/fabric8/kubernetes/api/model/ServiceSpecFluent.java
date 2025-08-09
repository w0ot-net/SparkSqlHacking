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

public class ServiceSpecFluent extends BaseFluent {
   private Boolean allocateLoadBalancerNodePorts;
   private String clusterIP;
   private List clusterIPs = new ArrayList();
   private List externalIPs = new ArrayList();
   private String externalName;
   private String externalTrafficPolicy;
   private Integer healthCheckNodePort;
   private String internalTrafficPolicy;
   private List ipFamilies = new ArrayList();
   private String ipFamilyPolicy;
   private String loadBalancerClass;
   private String loadBalancerIP;
   private List loadBalancerSourceRanges = new ArrayList();
   private ArrayList ports = new ArrayList();
   private Boolean publishNotReadyAddresses;
   private Map selector;
   private String sessionAffinity;
   private SessionAffinityConfigBuilder sessionAffinityConfig;
   private String trafficDistribution;
   private String type;
   private Map additionalProperties;

   public ServiceSpecFluent() {
   }

   public ServiceSpecFluent(ServiceSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ServiceSpec instance) {
      instance = instance != null ? instance : new ServiceSpec();
      if (instance != null) {
         this.withAllocateLoadBalancerNodePorts(instance.getAllocateLoadBalancerNodePorts());
         this.withClusterIP(instance.getClusterIP());
         this.withClusterIPs(instance.getClusterIPs());
         this.withExternalIPs(instance.getExternalIPs());
         this.withExternalName(instance.getExternalName());
         this.withExternalTrafficPolicy(instance.getExternalTrafficPolicy());
         this.withHealthCheckNodePort(instance.getHealthCheckNodePort());
         this.withInternalTrafficPolicy(instance.getInternalTrafficPolicy());
         this.withIpFamilies(instance.getIpFamilies());
         this.withIpFamilyPolicy(instance.getIpFamilyPolicy());
         this.withLoadBalancerClass(instance.getLoadBalancerClass());
         this.withLoadBalancerIP(instance.getLoadBalancerIP());
         this.withLoadBalancerSourceRanges(instance.getLoadBalancerSourceRanges());
         this.withPorts(instance.getPorts());
         this.withPublishNotReadyAddresses(instance.getPublishNotReadyAddresses());
         this.withSelector(instance.getSelector());
         this.withSessionAffinity(instance.getSessionAffinity());
         this.withSessionAffinityConfig(instance.getSessionAffinityConfig());
         this.withTrafficDistribution(instance.getTrafficDistribution());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getAllocateLoadBalancerNodePorts() {
      return this.allocateLoadBalancerNodePorts;
   }

   public ServiceSpecFluent withAllocateLoadBalancerNodePorts(Boolean allocateLoadBalancerNodePorts) {
      this.allocateLoadBalancerNodePorts = allocateLoadBalancerNodePorts;
      return this;
   }

   public boolean hasAllocateLoadBalancerNodePorts() {
      return this.allocateLoadBalancerNodePorts != null;
   }

   public String getClusterIP() {
      return this.clusterIP;
   }

   public ServiceSpecFluent withClusterIP(String clusterIP) {
      this.clusterIP = clusterIP;
      return this;
   }

   public boolean hasClusterIP() {
      return this.clusterIP != null;
   }

   public ServiceSpecFluent addToClusterIPs(int index, String item) {
      if (this.clusterIPs == null) {
         this.clusterIPs = new ArrayList();
      }

      this.clusterIPs.add(index, item);
      return this;
   }

   public ServiceSpecFluent setToClusterIPs(int index, String item) {
      if (this.clusterIPs == null) {
         this.clusterIPs = new ArrayList();
      }

      this.clusterIPs.set(index, item);
      return this;
   }

   public ServiceSpecFluent addToClusterIPs(String... items) {
      if (this.clusterIPs == null) {
         this.clusterIPs = new ArrayList();
      }

      for(String item : items) {
         this.clusterIPs.add(item);
      }

      return this;
   }

   public ServiceSpecFluent addAllToClusterIPs(Collection items) {
      if (this.clusterIPs == null) {
         this.clusterIPs = new ArrayList();
      }

      for(String item : items) {
         this.clusterIPs.add(item);
      }

      return this;
   }

   public ServiceSpecFluent removeFromClusterIPs(String... items) {
      if (this.clusterIPs == null) {
         return this;
      } else {
         for(String item : items) {
            this.clusterIPs.remove(item);
         }

         return this;
      }
   }

   public ServiceSpecFluent removeAllFromClusterIPs(Collection items) {
      if (this.clusterIPs == null) {
         return this;
      } else {
         for(String item : items) {
            this.clusterIPs.remove(item);
         }

         return this;
      }
   }

   public List getClusterIPs() {
      return this.clusterIPs;
   }

   public String getClusterIP(int index) {
      return (String)this.clusterIPs.get(index);
   }

   public String getFirstClusterIP() {
      return (String)this.clusterIPs.get(0);
   }

   public String getLastClusterIP() {
      return (String)this.clusterIPs.get(this.clusterIPs.size() - 1);
   }

   public String getMatchingClusterIP(Predicate predicate) {
      for(String item : this.clusterIPs) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingClusterIP(Predicate predicate) {
      for(String item : this.clusterIPs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ServiceSpecFluent withClusterIPs(List clusterIPs) {
      if (clusterIPs != null) {
         this.clusterIPs = new ArrayList();

         for(String item : clusterIPs) {
            this.addToClusterIPs(item);
         }
      } else {
         this.clusterIPs = null;
      }

      return this;
   }

   public ServiceSpecFluent withClusterIPs(String... clusterIPs) {
      if (this.clusterIPs != null) {
         this.clusterIPs.clear();
         this._visitables.remove("clusterIPs");
      }

      if (clusterIPs != null) {
         for(String item : clusterIPs) {
            this.addToClusterIPs(item);
         }
      }

      return this;
   }

   public boolean hasClusterIPs() {
      return this.clusterIPs != null && !this.clusterIPs.isEmpty();
   }

   public ServiceSpecFluent addToExternalIPs(int index, String item) {
      if (this.externalIPs == null) {
         this.externalIPs = new ArrayList();
      }

      this.externalIPs.add(index, item);
      return this;
   }

   public ServiceSpecFluent setToExternalIPs(int index, String item) {
      if (this.externalIPs == null) {
         this.externalIPs = new ArrayList();
      }

      this.externalIPs.set(index, item);
      return this;
   }

   public ServiceSpecFluent addToExternalIPs(String... items) {
      if (this.externalIPs == null) {
         this.externalIPs = new ArrayList();
      }

      for(String item : items) {
         this.externalIPs.add(item);
      }

      return this;
   }

   public ServiceSpecFluent addAllToExternalIPs(Collection items) {
      if (this.externalIPs == null) {
         this.externalIPs = new ArrayList();
      }

      for(String item : items) {
         this.externalIPs.add(item);
      }

      return this;
   }

   public ServiceSpecFluent removeFromExternalIPs(String... items) {
      if (this.externalIPs == null) {
         return this;
      } else {
         for(String item : items) {
            this.externalIPs.remove(item);
         }

         return this;
      }
   }

   public ServiceSpecFluent removeAllFromExternalIPs(Collection items) {
      if (this.externalIPs == null) {
         return this;
      } else {
         for(String item : items) {
            this.externalIPs.remove(item);
         }

         return this;
      }
   }

   public List getExternalIPs() {
      return this.externalIPs;
   }

   public String getExternalIP(int index) {
      return (String)this.externalIPs.get(index);
   }

   public String getFirstExternalIP() {
      return (String)this.externalIPs.get(0);
   }

   public String getLastExternalIP() {
      return (String)this.externalIPs.get(this.externalIPs.size() - 1);
   }

   public String getMatchingExternalIP(Predicate predicate) {
      for(String item : this.externalIPs) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingExternalIP(Predicate predicate) {
      for(String item : this.externalIPs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ServiceSpecFluent withExternalIPs(List externalIPs) {
      if (externalIPs != null) {
         this.externalIPs = new ArrayList();

         for(String item : externalIPs) {
            this.addToExternalIPs(item);
         }
      } else {
         this.externalIPs = null;
      }

      return this;
   }

   public ServiceSpecFluent withExternalIPs(String... externalIPs) {
      if (this.externalIPs != null) {
         this.externalIPs.clear();
         this._visitables.remove("externalIPs");
      }

      if (externalIPs != null) {
         for(String item : externalIPs) {
            this.addToExternalIPs(item);
         }
      }

      return this;
   }

   public boolean hasExternalIPs() {
      return this.externalIPs != null && !this.externalIPs.isEmpty();
   }

   public String getExternalName() {
      return this.externalName;
   }

   public ServiceSpecFluent withExternalName(String externalName) {
      this.externalName = externalName;
      return this;
   }

   public boolean hasExternalName() {
      return this.externalName != null;
   }

   public String getExternalTrafficPolicy() {
      return this.externalTrafficPolicy;
   }

   public ServiceSpecFluent withExternalTrafficPolicy(String externalTrafficPolicy) {
      this.externalTrafficPolicy = externalTrafficPolicy;
      return this;
   }

   public boolean hasExternalTrafficPolicy() {
      return this.externalTrafficPolicy != null;
   }

   public Integer getHealthCheckNodePort() {
      return this.healthCheckNodePort;
   }

   public ServiceSpecFluent withHealthCheckNodePort(Integer healthCheckNodePort) {
      this.healthCheckNodePort = healthCheckNodePort;
      return this;
   }

   public boolean hasHealthCheckNodePort() {
      return this.healthCheckNodePort != null;
   }

   public String getInternalTrafficPolicy() {
      return this.internalTrafficPolicy;
   }

   public ServiceSpecFluent withInternalTrafficPolicy(String internalTrafficPolicy) {
      this.internalTrafficPolicy = internalTrafficPolicy;
      return this;
   }

   public boolean hasInternalTrafficPolicy() {
      return this.internalTrafficPolicy != null;
   }

   public ServiceSpecFluent addToIpFamilies(int index, String item) {
      if (this.ipFamilies == null) {
         this.ipFamilies = new ArrayList();
      }

      this.ipFamilies.add(index, item);
      return this;
   }

   public ServiceSpecFluent setToIpFamilies(int index, String item) {
      if (this.ipFamilies == null) {
         this.ipFamilies = new ArrayList();
      }

      this.ipFamilies.set(index, item);
      return this;
   }

   public ServiceSpecFluent addToIpFamilies(String... items) {
      if (this.ipFamilies == null) {
         this.ipFamilies = new ArrayList();
      }

      for(String item : items) {
         this.ipFamilies.add(item);
      }

      return this;
   }

   public ServiceSpecFluent addAllToIpFamilies(Collection items) {
      if (this.ipFamilies == null) {
         this.ipFamilies = new ArrayList();
      }

      for(String item : items) {
         this.ipFamilies.add(item);
      }

      return this;
   }

   public ServiceSpecFluent removeFromIpFamilies(String... items) {
      if (this.ipFamilies == null) {
         return this;
      } else {
         for(String item : items) {
            this.ipFamilies.remove(item);
         }

         return this;
      }
   }

   public ServiceSpecFluent removeAllFromIpFamilies(Collection items) {
      if (this.ipFamilies == null) {
         return this;
      } else {
         for(String item : items) {
            this.ipFamilies.remove(item);
         }

         return this;
      }
   }

   public List getIpFamilies() {
      return this.ipFamilies;
   }

   public String getIpFamily(int index) {
      return (String)this.ipFamilies.get(index);
   }

   public String getFirstIpFamily() {
      return (String)this.ipFamilies.get(0);
   }

   public String getLastIpFamily() {
      return (String)this.ipFamilies.get(this.ipFamilies.size() - 1);
   }

   public String getMatchingIpFamily(Predicate predicate) {
      for(String item : this.ipFamilies) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingIpFamily(Predicate predicate) {
      for(String item : this.ipFamilies) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ServiceSpecFluent withIpFamilies(List ipFamilies) {
      if (ipFamilies != null) {
         this.ipFamilies = new ArrayList();

         for(String item : ipFamilies) {
            this.addToIpFamilies(item);
         }
      } else {
         this.ipFamilies = null;
      }

      return this;
   }

   public ServiceSpecFluent withIpFamilies(String... ipFamilies) {
      if (this.ipFamilies != null) {
         this.ipFamilies.clear();
         this._visitables.remove("ipFamilies");
      }

      if (ipFamilies != null) {
         for(String item : ipFamilies) {
            this.addToIpFamilies(item);
         }
      }

      return this;
   }

   public boolean hasIpFamilies() {
      return this.ipFamilies != null && !this.ipFamilies.isEmpty();
   }

   public String getIpFamilyPolicy() {
      return this.ipFamilyPolicy;
   }

   public ServiceSpecFluent withIpFamilyPolicy(String ipFamilyPolicy) {
      this.ipFamilyPolicy = ipFamilyPolicy;
      return this;
   }

   public boolean hasIpFamilyPolicy() {
      return this.ipFamilyPolicy != null;
   }

   public String getLoadBalancerClass() {
      return this.loadBalancerClass;
   }

   public ServiceSpecFluent withLoadBalancerClass(String loadBalancerClass) {
      this.loadBalancerClass = loadBalancerClass;
      return this;
   }

   public boolean hasLoadBalancerClass() {
      return this.loadBalancerClass != null;
   }

   public String getLoadBalancerIP() {
      return this.loadBalancerIP;
   }

   public ServiceSpecFluent withLoadBalancerIP(String loadBalancerIP) {
      this.loadBalancerIP = loadBalancerIP;
      return this;
   }

   public boolean hasLoadBalancerIP() {
      return this.loadBalancerIP != null;
   }

   public ServiceSpecFluent addToLoadBalancerSourceRanges(int index, String item) {
      if (this.loadBalancerSourceRanges == null) {
         this.loadBalancerSourceRanges = new ArrayList();
      }

      this.loadBalancerSourceRanges.add(index, item);
      return this;
   }

   public ServiceSpecFluent setToLoadBalancerSourceRanges(int index, String item) {
      if (this.loadBalancerSourceRanges == null) {
         this.loadBalancerSourceRanges = new ArrayList();
      }

      this.loadBalancerSourceRanges.set(index, item);
      return this;
   }

   public ServiceSpecFluent addToLoadBalancerSourceRanges(String... items) {
      if (this.loadBalancerSourceRanges == null) {
         this.loadBalancerSourceRanges = new ArrayList();
      }

      for(String item : items) {
         this.loadBalancerSourceRanges.add(item);
      }

      return this;
   }

   public ServiceSpecFluent addAllToLoadBalancerSourceRanges(Collection items) {
      if (this.loadBalancerSourceRanges == null) {
         this.loadBalancerSourceRanges = new ArrayList();
      }

      for(String item : items) {
         this.loadBalancerSourceRanges.add(item);
      }

      return this;
   }

   public ServiceSpecFluent removeFromLoadBalancerSourceRanges(String... items) {
      if (this.loadBalancerSourceRanges == null) {
         return this;
      } else {
         for(String item : items) {
            this.loadBalancerSourceRanges.remove(item);
         }

         return this;
      }
   }

   public ServiceSpecFluent removeAllFromLoadBalancerSourceRanges(Collection items) {
      if (this.loadBalancerSourceRanges == null) {
         return this;
      } else {
         for(String item : items) {
            this.loadBalancerSourceRanges.remove(item);
         }

         return this;
      }
   }

   public List getLoadBalancerSourceRanges() {
      return this.loadBalancerSourceRanges;
   }

   public String getLoadBalancerSourceRange(int index) {
      return (String)this.loadBalancerSourceRanges.get(index);
   }

   public String getFirstLoadBalancerSourceRange() {
      return (String)this.loadBalancerSourceRanges.get(0);
   }

   public String getLastLoadBalancerSourceRange() {
      return (String)this.loadBalancerSourceRanges.get(this.loadBalancerSourceRanges.size() - 1);
   }

   public String getMatchingLoadBalancerSourceRange(Predicate predicate) {
      for(String item : this.loadBalancerSourceRanges) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingLoadBalancerSourceRange(Predicate predicate) {
      for(String item : this.loadBalancerSourceRanges) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ServiceSpecFluent withLoadBalancerSourceRanges(List loadBalancerSourceRanges) {
      if (loadBalancerSourceRanges != null) {
         this.loadBalancerSourceRanges = new ArrayList();

         for(String item : loadBalancerSourceRanges) {
            this.addToLoadBalancerSourceRanges(item);
         }
      } else {
         this.loadBalancerSourceRanges = null;
      }

      return this;
   }

   public ServiceSpecFluent withLoadBalancerSourceRanges(String... loadBalancerSourceRanges) {
      if (this.loadBalancerSourceRanges != null) {
         this.loadBalancerSourceRanges.clear();
         this._visitables.remove("loadBalancerSourceRanges");
      }

      if (loadBalancerSourceRanges != null) {
         for(String item : loadBalancerSourceRanges) {
            this.addToLoadBalancerSourceRanges(item);
         }
      }

      return this;
   }

   public boolean hasLoadBalancerSourceRanges() {
      return this.loadBalancerSourceRanges != null && !this.loadBalancerSourceRanges.isEmpty();
   }

   public ServiceSpecFluent addToPorts(int index, ServicePort item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      ServicePortBuilder builder = new ServicePortBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").add(index, builder);
         this.ports.add(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public ServiceSpecFluent setToPorts(int index, ServicePort item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      ServicePortBuilder builder = new ServicePortBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").set(index, builder);
         this.ports.set(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public ServiceSpecFluent addToPorts(ServicePort... items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(ServicePort item : items) {
         ServicePortBuilder builder = new ServicePortBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public ServiceSpecFluent addAllToPorts(Collection items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(ServicePort item : items) {
         ServicePortBuilder builder = new ServicePortBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public ServiceSpecFluent removeFromPorts(ServicePort... items) {
      if (this.ports == null) {
         return this;
      } else {
         for(ServicePort item : items) {
            ServicePortBuilder builder = new ServicePortBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public ServiceSpecFluent removeAllFromPorts(Collection items) {
      if (this.ports == null) {
         return this;
      } else {
         for(ServicePort item : items) {
            ServicePortBuilder builder = new ServicePortBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public ServiceSpecFluent removeMatchingFromPorts(Predicate predicate) {
      if (this.ports == null) {
         return this;
      } else {
         Iterator<ServicePortBuilder> each = this.ports.iterator();
         List visitables = this._visitables.get("ports");

         while(each.hasNext()) {
            ServicePortBuilder builder = (ServicePortBuilder)each.next();
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

   public ServicePort buildPort(int index) {
      return ((ServicePortBuilder)this.ports.get(index)).build();
   }

   public ServicePort buildFirstPort() {
      return ((ServicePortBuilder)this.ports.get(0)).build();
   }

   public ServicePort buildLastPort() {
      return ((ServicePortBuilder)this.ports.get(this.ports.size() - 1)).build();
   }

   public ServicePort buildMatchingPort(Predicate predicate) {
      for(ServicePortBuilder item : this.ports) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPort(Predicate predicate) {
      for(ServicePortBuilder item : this.ports) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ServiceSpecFluent withPorts(List ports) {
      if (this.ports != null) {
         this._visitables.get("ports").clear();
      }

      if (ports != null) {
         this.ports = new ArrayList();

         for(ServicePort item : ports) {
            this.addToPorts(item);
         }
      } else {
         this.ports = null;
      }

      return this;
   }

   public ServiceSpecFluent withPorts(ServicePort... ports) {
      if (this.ports != null) {
         this.ports.clear();
         this._visitables.remove("ports");
      }

      if (ports != null) {
         for(ServicePort item : ports) {
            this.addToPorts(item);
         }
      }

      return this;
   }

   public boolean hasPorts() {
      return this.ports != null && !this.ports.isEmpty();
   }

   public PortsNested addNewPort() {
      return new PortsNested(-1, (ServicePort)null);
   }

   public PortsNested addNewPortLike(ServicePort item) {
      return new PortsNested(-1, item);
   }

   public PortsNested setNewPortLike(int index, ServicePort item) {
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
         if (predicate.test((ServicePortBuilder)this.ports.get(i))) {
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

   public Boolean getPublishNotReadyAddresses() {
      return this.publishNotReadyAddresses;
   }

   public ServiceSpecFluent withPublishNotReadyAddresses(Boolean publishNotReadyAddresses) {
      this.publishNotReadyAddresses = publishNotReadyAddresses;
      return this;
   }

   public boolean hasPublishNotReadyAddresses() {
      return this.publishNotReadyAddresses != null;
   }

   public ServiceSpecFluent addToSelector(String key, String value) {
      if (this.selector == null && key != null && value != null) {
         this.selector = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.selector.put(key, value);
      }

      return this;
   }

   public ServiceSpecFluent addToSelector(Map map) {
      if (this.selector == null && map != null) {
         this.selector = new LinkedHashMap();
      }

      if (map != null) {
         this.selector.putAll(map);
      }

      return this;
   }

   public ServiceSpecFluent removeFromSelector(String key) {
      if (this.selector == null) {
         return this;
      } else {
         if (key != null && this.selector != null) {
            this.selector.remove(key);
         }

         return this;
      }
   }

   public ServiceSpecFluent removeFromSelector(Map map) {
      if (this.selector == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.selector != null) {
                  this.selector.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getSelector() {
      return this.selector;
   }

   public ServiceSpecFluent withSelector(Map selector) {
      if (selector == null) {
         this.selector = null;
      } else {
         this.selector = new LinkedHashMap(selector);
      }

      return this;
   }

   public boolean hasSelector() {
      return this.selector != null;
   }

   public String getSessionAffinity() {
      return this.sessionAffinity;
   }

   public ServiceSpecFluent withSessionAffinity(String sessionAffinity) {
      this.sessionAffinity = sessionAffinity;
      return this;
   }

   public boolean hasSessionAffinity() {
      return this.sessionAffinity != null;
   }

   public SessionAffinityConfig buildSessionAffinityConfig() {
      return this.sessionAffinityConfig != null ? this.sessionAffinityConfig.build() : null;
   }

   public ServiceSpecFluent withSessionAffinityConfig(SessionAffinityConfig sessionAffinityConfig) {
      this._visitables.remove("sessionAffinityConfig");
      if (sessionAffinityConfig != null) {
         this.sessionAffinityConfig = new SessionAffinityConfigBuilder(sessionAffinityConfig);
         this._visitables.get("sessionAffinityConfig").add(this.sessionAffinityConfig);
      } else {
         this.sessionAffinityConfig = null;
         this._visitables.get("sessionAffinityConfig").remove(this.sessionAffinityConfig);
      }

      return this;
   }

   public boolean hasSessionAffinityConfig() {
      return this.sessionAffinityConfig != null;
   }

   public SessionAffinityConfigNested withNewSessionAffinityConfig() {
      return new SessionAffinityConfigNested((SessionAffinityConfig)null);
   }

   public SessionAffinityConfigNested withNewSessionAffinityConfigLike(SessionAffinityConfig item) {
      return new SessionAffinityConfigNested(item);
   }

   public SessionAffinityConfigNested editSessionAffinityConfig() {
      return this.withNewSessionAffinityConfigLike((SessionAffinityConfig)Optional.ofNullable(this.buildSessionAffinityConfig()).orElse((Object)null));
   }

   public SessionAffinityConfigNested editOrNewSessionAffinityConfig() {
      return this.withNewSessionAffinityConfigLike((SessionAffinityConfig)Optional.ofNullable(this.buildSessionAffinityConfig()).orElse((new SessionAffinityConfigBuilder()).build()));
   }

   public SessionAffinityConfigNested editOrNewSessionAffinityConfigLike(SessionAffinityConfig item) {
      return this.withNewSessionAffinityConfigLike((SessionAffinityConfig)Optional.ofNullable(this.buildSessionAffinityConfig()).orElse(item));
   }

   public String getTrafficDistribution() {
      return this.trafficDistribution;
   }

   public ServiceSpecFluent withTrafficDistribution(String trafficDistribution) {
      this.trafficDistribution = trafficDistribution;
      return this;
   }

   public boolean hasTrafficDistribution() {
      return this.trafficDistribution != null;
   }

   public String getType() {
      return this.type;
   }

   public ServiceSpecFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public ServiceSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ServiceSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ServiceSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ServiceSpecFluent removeFromAdditionalProperties(Map map) {
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

   public ServiceSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            ServiceSpecFluent that = (ServiceSpecFluent)o;
            if (!Objects.equals(this.allocateLoadBalancerNodePorts, that.allocateLoadBalancerNodePorts)) {
               return false;
            } else if (!Objects.equals(this.clusterIP, that.clusterIP)) {
               return false;
            } else if (!Objects.equals(this.clusterIPs, that.clusterIPs)) {
               return false;
            } else if (!Objects.equals(this.externalIPs, that.externalIPs)) {
               return false;
            } else if (!Objects.equals(this.externalName, that.externalName)) {
               return false;
            } else if (!Objects.equals(this.externalTrafficPolicy, that.externalTrafficPolicy)) {
               return false;
            } else if (!Objects.equals(this.healthCheckNodePort, that.healthCheckNodePort)) {
               return false;
            } else if (!Objects.equals(this.internalTrafficPolicy, that.internalTrafficPolicy)) {
               return false;
            } else if (!Objects.equals(this.ipFamilies, that.ipFamilies)) {
               return false;
            } else if (!Objects.equals(this.ipFamilyPolicy, that.ipFamilyPolicy)) {
               return false;
            } else if (!Objects.equals(this.loadBalancerClass, that.loadBalancerClass)) {
               return false;
            } else if (!Objects.equals(this.loadBalancerIP, that.loadBalancerIP)) {
               return false;
            } else if (!Objects.equals(this.loadBalancerSourceRanges, that.loadBalancerSourceRanges)) {
               return false;
            } else if (!Objects.equals(this.ports, that.ports)) {
               return false;
            } else if (!Objects.equals(this.publishNotReadyAddresses, that.publishNotReadyAddresses)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
               return false;
            } else if (!Objects.equals(this.sessionAffinity, that.sessionAffinity)) {
               return false;
            } else if (!Objects.equals(this.sessionAffinityConfig, that.sessionAffinityConfig)) {
               return false;
            } else if (!Objects.equals(this.trafficDistribution, that.trafficDistribution)) {
               return false;
            } else if (!Objects.equals(this.type, that.type)) {
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
      return Objects.hash(new Object[]{this.allocateLoadBalancerNodePorts, this.clusterIP, this.clusterIPs, this.externalIPs, this.externalName, this.externalTrafficPolicy, this.healthCheckNodePort, this.internalTrafficPolicy, this.ipFamilies, this.ipFamilyPolicy, this.loadBalancerClass, this.loadBalancerIP, this.loadBalancerSourceRanges, this.ports, this.publishNotReadyAddresses, this.selector, this.sessionAffinity, this.sessionAffinityConfig, this.trafficDistribution, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allocateLoadBalancerNodePorts != null) {
         sb.append("allocateLoadBalancerNodePorts:");
         sb.append(this.allocateLoadBalancerNodePorts + ",");
      }

      if (this.clusterIP != null) {
         sb.append("clusterIP:");
         sb.append(this.clusterIP + ",");
      }

      if (this.clusterIPs != null && !this.clusterIPs.isEmpty()) {
         sb.append("clusterIPs:");
         sb.append(this.clusterIPs + ",");
      }

      if (this.externalIPs != null && !this.externalIPs.isEmpty()) {
         sb.append("externalIPs:");
         sb.append(this.externalIPs + ",");
      }

      if (this.externalName != null) {
         sb.append("externalName:");
         sb.append(this.externalName + ",");
      }

      if (this.externalTrafficPolicy != null) {
         sb.append("externalTrafficPolicy:");
         sb.append(this.externalTrafficPolicy + ",");
      }

      if (this.healthCheckNodePort != null) {
         sb.append("healthCheckNodePort:");
         sb.append(this.healthCheckNodePort + ",");
      }

      if (this.internalTrafficPolicy != null) {
         sb.append("internalTrafficPolicy:");
         sb.append(this.internalTrafficPolicy + ",");
      }

      if (this.ipFamilies != null && !this.ipFamilies.isEmpty()) {
         sb.append("ipFamilies:");
         sb.append(this.ipFamilies + ",");
      }

      if (this.ipFamilyPolicy != null) {
         sb.append("ipFamilyPolicy:");
         sb.append(this.ipFamilyPolicy + ",");
      }

      if (this.loadBalancerClass != null) {
         sb.append("loadBalancerClass:");
         sb.append(this.loadBalancerClass + ",");
      }

      if (this.loadBalancerIP != null) {
         sb.append("loadBalancerIP:");
         sb.append(this.loadBalancerIP + ",");
      }

      if (this.loadBalancerSourceRanges != null && !this.loadBalancerSourceRanges.isEmpty()) {
         sb.append("loadBalancerSourceRanges:");
         sb.append(this.loadBalancerSourceRanges + ",");
      }

      if (this.ports != null && !this.ports.isEmpty()) {
         sb.append("ports:");
         sb.append(this.ports + ",");
      }

      if (this.publishNotReadyAddresses != null) {
         sb.append("publishNotReadyAddresses:");
         sb.append(this.publishNotReadyAddresses + ",");
      }

      if (this.selector != null && !this.selector.isEmpty()) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.sessionAffinity != null) {
         sb.append("sessionAffinity:");
         sb.append(this.sessionAffinity + ",");
      }

      if (this.sessionAffinityConfig != null) {
         sb.append("sessionAffinityConfig:");
         sb.append(this.sessionAffinityConfig + ",");
      }

      if (this.trafficDistribution != null) {
         sb.append("trafficDistribution:");
         sb.append(this.trafficDistribution + ",");
      }

      if (this.type != null) {
         sb.append("type:");
         sb.append(this.type + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ServiceSpecFluent withAllocateLoadBalancerNodePorts() {
      return this.withAllocateLoadBalancerNodePorts(true);
   }

   public ServiceSpecFluent withPublishNotReadyAddresses() {
      return this.withPublishNotReadyAddresses(true);
   }

   public class PortsNested extends ServicePortFluent implements Nested {
      ServicePortBuilder builder;
      int index;

      PortsNested(int index, ServicePort item) {
         this.index = index;
         this.builder = new ServicePortBuilder(this, item);
      }

      public Object and() {
         return ServiceSpecFluent.this.setToPorts(this.index, this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }

   public class SessionAffinityConfigNested extends SessionAffinityConfigFluent implements Nested {
      SessionAffinityConfigBuilder builder;

      SessionAffinityConfigNested(SessionAffinityConfig item) {
         this.builder = new SessionAffinityConfigBuilder(this, item);
      }

      public Object and() {
         return ServiceSpecFluent.this.withSessionAffinityConfig(this.builder.build());
      }

      public Object endSessionAffinityConfig() {
         return this.and();
      }
   }
}
