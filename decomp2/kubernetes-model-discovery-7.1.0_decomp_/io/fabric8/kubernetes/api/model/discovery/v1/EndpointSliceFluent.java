package io.fabric8.kubernetes.api.model.discovery.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class EndpointSliceFluent extends BaseFluent {
   private String addressType;
   private String apiVersion;
   private ArrayList endpoints = new ArrayList();
   private String kind;
   private ObjectMetaBuilder metadata;
   private ArrayList ports = new ArrayList();
   private Map additionalProperties;

   public EndpointSliceFluent() {
   }

   public EndpointSliceFluent(EndpointSlice instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EndpointSlice instance) {
      instance = instance != null ? instance : new EndpointSlice();
      if (instance != null) {
         this.withAddressType(instance.getAddressType());
         this.withApiVersion(instance.getApiVersion());
         this.withEndpoints(instance.getEndpoints());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withPorts(instance.getPorts());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getAddressType() {
      return this.addressType;
   }

   public EndpointSliceFluent withAddressType(String addressType) {
      this.addressType = addressType;
      return this;
   }

   public boolean hasAddressType() {
      return this.addressType != null;
   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public EndpointSliceFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public EndpointSliceFluent addToEndpoints(int index, Endpoint item) {
      if (this.endpoints == null) {
         this.endpoints = new ArrayList();
      }

      EndpointBuilder builder = new EndpointBuilder(item);
      if (index >= 0 && index < this.endpoints.size()) {
         this._visitables.get("endpoints").add(index, builder);
         this.endpoints.add(index, builder);
      } else {
         this._visitables.get("endpoints").add(builder);
         this.endpoints.add(builder);
      }

      return this;
   }

   public EndpointSliceFluent setToEndpoints(int index, Endpoint item) {
      if (this.endpoints == null) {
         this.endpoints = new ArrayList();
      }

      EndpointBuilder builder = new EndpointBuilder(item);
      if (index >= 0 && index < this.endpoints.size()) {
         this._visitables.get("endpoints").set(index, builder);
         this.endpoints.set(index, builder);
      } else {
         this._visitables.get("endpoints").add(builder);
         this.endpoints.add(builder);
      }

      return this;
   }

   public EndpointSliceFluent addToEndpoints(Endpoint... items) {
      if (this.endpoints == null) {
         this.endpoints = new ArrayList();
      }

      for(Endpoint item : items) {
         EndpointBuilder builder = new EndpointBuilder(item);
         this._visitables.get("endpoints").add(builder);
         this.endpoints.add(builder);
      }

      return this;
   }

   public EndpointSliceFluent addAllToEndpoints(Collection items) {
      if (this.endpoints == null) {
         this.endpoints = new ArrayList();
      }

      for(Endpoint item : items) {
         EndpointBuilder builder = new EndpointBuilder(item);
         this._visitables.get("endpoints").add(builder);
         this.endpoints.add(builder);
      }

      return this;
   }

   public EndpointSliceFluent removeFromEndpoints(Endpoint... items) {
      if (this.endpoints == null) {
         return this;
      } else {
         for(Endpoint item : items) {
            EndpointBuilder builder = new EndpointBuilder(item);
            this._visitables.get("endpoints").remove(builder);
            this.endpoints.remove(builder);
         }

         return this;
      }
   }

   public EndpointSliceFluent removeAllFromEndpoints(Collection items) {
      if (this.endpoints == null) {
         return this;
      } else {
         for(Endpoint item : items) {
            EndpointBuilder builder = new EndpointBuilder(item);
            this._visitables.get("endpoints").remove(builder);
            this.endpoints.remove(builder);
         }

         return this;
      }
   }

   public EndpointSliceFluent removeMatchingFromEndpoints(Predicate predicate) {
      if (this.endpoints == null) {
         return this;
      } else {
         Iterator<EndpointBuilder> each = this.endpoints.iterator();
         List visitables = this._visitables.get("endpoints");

         while(each.hasNext()) {
            EndpointBuilder builder = (EndpointBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildEndpoints() {
      return this.endpoints != null ? build(this.endpoints) : null;
   }

   public Endpoint buildEndpoint(int index) {
      return ((EndpointBuilder)this.endpoints.get(index)).build();
   }

   public Endpoint buildFirstEndpoint() {
      return ((EndpointBuilder)this.endpoints.get(0)).build();
   }

   public Endpoint buildLastEndpoint() {
      return ((EndpointBuilder)this.endpoints.get(this.endpoints.size() - 1)).build();
   }

   public Endpoint buildMatchingEndpoint(Predicate predicate) {
      for(EndpointBuilder item : this.endpoints) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingEndpoint(Predicate predicate) {
      for(EndpointBuilder item : this.endpoints) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EndpointSliceFluent withEndpoints(List endpoints) {
      if (this.endpoints != null) {
         this._visitables.get("endpoints").clear();
      }

      if (endpoints != null) {
         this.endpoints = new ArrayList();

         for(Endpoint item : endpoints) {
            this.addToEndpoints(item);
         }
      } else {
         this.endpoints = null;
      }

      return this;
   }

   public EndpointSliceFluent withEndpoints(Endpoint... endpoints) {
      if (this.endpoints != null) {
         this.endpoints.clear();
         this._visitables.remove("endpoints");
      }

      if (endpoints != null) {
         for(Endpoint item : endpoints) {
            this.addToEndpoints(item);
         }
      }

      return this;
   }

   public boolean hasEndpoints() {
      return this.endpoints != null && !this.endpoints.isEmpty();
   }

   public EndpointsNested addNewEndpoint() {
      return new EndpointsNested(-1, (Endpoint)null);
   }

   public EndpointsNested addNewEndpointLike(Endpoint item) {
      return new EndpointsNested(-1, item);
   }

   public EndpointsNested setNewEndpointLike(int index, Endpoint item) {
      return new EndpointsNested(index, item);
   }

   public EndpointsNested editEndpoint(int index) {
      if (this.endpoints.size() <= index) {
         throw new RuntimeException("Can't edit endpoints. Index exceeds size.");
      } else {
         return this.setNewEndpointLike(index, this.buildEndpoint(index));
      }
   }

   public EndpointsNested editFirstEndpoint() {
      if (this.endpoints.size() == 0) {
         throw new RuntimeException("Can't edit first endpoints. The list is empty.");
      } else {
         return this.setNewEndpointLike(0, this.buildEndpoint(0));
      }
   }

   public EndpointsNested editLastEndpoint() {
      int index = this.endpoints.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last endpoints. The list is empty.");
      } else {
         return this.setNewEndpointLike(index, this.buildEndpoint(index));
      }
   }

   public EndpointsNested editMatchingEndpoint(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.endpoints.size(); ++i) {
         if (predicate.test((EndpointBuilder)this.endpoints.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching endpoints. No match found.");
      } else {
         return this.setNewEndpointLike(index, this.buildEndpoint(index));
      }
   }

   public String getKind() {
      return this.kind;
   }

   public EndpointSliceFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public EndpointSliceFluent withMetadata(ObjectMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ObjectMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ObjectMeta)null);
   }

   public MetadataNested withNewMetadataLike(ObjectMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ObjectMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ObjectMeta item) {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public EndpointSliceFluent addToPorts(int index, EndpointPort item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      EndpointPortBuilder builder = new EndpointPortBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").add(index, builder);
         this.ports.add(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public EndpointSliceFluent setToPorts(int index, EndpointPort item) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      EndpointPortBuilder builder = new EndpointPortBuilder(item);
      if (index >= 0 && index < this.ports.size()) {
         this._visitables.get("ports").set(index, builder);
         this.ports.set(index, builder);
      } else {
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public EndpointSliceFluent addToPorts(EndpointPort... items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(EndpointPort item : items) {
         EndpointPortBuilder builder = new EndpointPortBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public EndpointSliceFluent addAllToPorts(Collection items) {
      if (this.ports == null) {
         this.ports = new ArrayList();
      }

      for(EndpointPort item : items) {
         EndpointPortBuilder builder = new EndpointPortBuilder(item);
         this._visitables.get("ports").add(builder);
         this.ports.add(builder);
      }

      return this;
   }

   public EndpointSliceFluent removeFromPorts(EndpointPort... items) {
      if (this.ports == null) {
         return this;
      } else {
         for(EndpointPort item : items) {
            EndpointPortBuilder builder = new EndpointPortBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public EndpointSliceFluent removeAllFromPorts(Collection items) {
      if (this.ports == null) {
         return this;
      } else {
         for(EndpointPort item : items) {
            EndpointPortBuilder builder = new EndpointPortBuilder(item);
            this._visitables.get("ports").remove(builder);
            this.ports.remove(builder);
         }

         return this;
      }
   }

   public EndpointSliceFluent removeMatchingFromPorts(Predicate predicate) {
      if (this.ports == null) {
         return this;
      } else {
         Iterator<EndpointPortBuilder> each = this.ports.iterator();
         List visitables = this._visitables.get("ports");

         while(each.hasNext()) {
            EndpointPortBuilder builder = (EndpointPortBuilder)each.next();
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

   public EndpointPort buildPort(int index) {
      return ((EndpointPortBuilder)this.ports.get(index)).build();
   }

   public EndpointPort buildFirstPort() {
      return ((EndpointPortBuilder)this.ports.get(0)).build();
   }

   public EndpointPort buildLastPort() {
      return ((EndpointPortBuilder)this.ports.get(this.ports.size() - 1)).build();
   }

   public EndpointPort buildMatchingPort(Predicate predicate) {
      for(EndpointPortBuilder item : this.ports) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPort(Predicate predicate) {
      for(EndpointPortBuilder item : this.ports) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EndpointSliceFluent withPorts(List ports) {
      if (this.ports != null) {
         this._visitables.get("ports").clear();
      }

      if (ports != null) {
         this.ports = new ArrayList();

         for(EndpointPort item : ports) {
            this.addToPorts(item);
         }
      } else {
         this.ports = null;
      }

      return this;
   }

   public EndpointSliceFluent withPorts(EndpointPort... ports) {
      if (this.ports != null) {
         this.ports.clear();
         this._visitables.remove("ports");
      }

      if (ports != null) {
         for(EndpointPort item : ports) {
            this.addToPorts(item);
         }
      }

      return this;
   }

   public boolean hasPorts() {
      return this.ports != null && !this.ports.isEmpty();
   }

   public EndpointSliceFluent addNewPort(String appProtocol, String name, Integer port, String protocol) {
      return this.addToPorts(new EndpointPort(appProtocol, name, port, protocol));
   }

   public PortsNested addNewPort() {
      return new PortsNested(-1, (EndpointPort)null);
   }

   public PortsNested addNewPortLike(EndpointPort item) {
      return new PortsNested(-1, item);
   }

   public PortsNested setNewPortLike(int index, EndpointPort item) {
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
         if (predicate.test((EndpointPortBuilder)this.ports.get(i))) {
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

   public EndpointSliceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EndpointSliceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EndpointSliceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EndpointSliceFluent removeFromAdditionalProperties(Map map) {
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

   public EndpointSliceFluent withAdditionalProperties(Map additionalProperties) {
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
            EndpointSliceFluent that = (EndpointSliceFluent)o;
            if (!Objects.equals(this.addressType, that.addressType)) {
               return false;
            } else if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.endpoints, that.endpoints)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
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
      return Objects.hash(new Object[]{this.addressType, this.apiVersion, this.endpoints, this.kind, this.metadata, this.ports, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.addressType != null) {
         sb.append("addressType:");
         sb.append(this.addressType + ",");
      }

      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.endpoints != null && !this.endpoints.isEmpty()) {
         sb.append("endpoints:");
         sb.append(this.endpoints + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
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

   public class EndpointsNested extends EndpointFluent implements Nested {
      EndpointBuilder builder;
      int index;

      EndpointsNested(int index, Endpoint item) {
         this.index = index;
         this.builder = new EndpointBuilder(this, item);
      }

      public Object and() {
         return EndpointSliceFluent.this.setToEndpoints(this.index, this.builder.build());
      }

      public Object endEndpoint() {
         return this.and();
      }
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return EndpointSliceFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class PortsNested extends EndpointPortFluent implements Nested {
      EndpointPortBuilder builder;
      int index;

      PortsNested(int index, EndpointPort item) {
         this.index = index;
         this.builder = new EndpointPortBuilder(this, item);
      }

      public Object and() {
         return EndpointSliceFluent.this.setToPorts(this.index, this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }
}
