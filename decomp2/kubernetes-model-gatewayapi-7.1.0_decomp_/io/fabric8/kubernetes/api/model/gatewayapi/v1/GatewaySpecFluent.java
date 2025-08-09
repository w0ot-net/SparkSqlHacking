package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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

public class GatewaySpecFluent extends BaseFluent {
   private ArrayList addresses = new ArrayList();
   private GatewayBackendTLSBuilder backendTLS;
   private String gatewayClassName;
   private GatewayInfrastructureBuilder infrastructure;
   private ArrayList listeners = new ArrayList();
   private Map additionalProperties;

   public GatewaySpecFluent() {
   }

   public GatewaySpecFluent(GatewaySpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GatewaySpec instance) {
      instance = instance != null ? instance : new GatewaySpec();
      if (instance != null) {
         this.withAddresses(instance.getAddresses());
         this.withBackendTLS(instance.getBackendTLS());
         this.withGatewayClassName(instance.getGatewayClassName());
         this.withInfrastructure(instance.getInfrastructure());
         this.withListeners(instance.getListeners());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public GatewaySpecFluent addToAddresses(int index, GatewayAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      GatewayAddressBuilder builder = new GatewayAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").add(index, builder);
         this.addresses.add(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public GatewaySpecFluent setToAddresses(int index, GatewayAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      GatewayAddressBuilder builder = new GatewayAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").set(index, builder);
         this.addresses.set(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public GatewaySpecFluent addToAddresses(GatewayAddress... items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(GatewayAddress item : items) {
         GatewayAddressBuilder builder = new GatewayAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public GatewaySpecFluent addAllToAddresses(Collection items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(GatewayAddress item : items) {
         GatewayAddressBuilder builder = new GatewayAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public GatewaySpecFluent removeFromAddresses(GatewayAddress... items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(GatewayAddress item : items) {
            GatewayAddressBuilder builder = new GatewayAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public GatewaySpecFluent removeAllFromAddresses(Collection items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(GatewayAddress item : items) {
            GatewayAddressBuilder builder = new GatewayAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public GatewaySpecFluent removeMatchingFromAddresses(Predicate predicate) {
      if (this.addresses == null) {
         return this;
      } else {
         Iterator<GatewayAddressBuilder> each = this.addresses.iterator();
         List visitables = this._visitables.get("addresses");

         while(each.hasNext()) {
            GatewayAddressBuilder builder = (GatewayAddressBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAddresses() {
      return this.addresses != null ? build(this.addresses) : null;
   }

   public GatewayAddress buildAddress(int index) {
      return ((GatewayAddressBuilder)this.addresses.get(index)).build();
   }

   public GatewayAddress buildFirstAddress() {
      return ((GatewayAddressBuilder)this.addresses.get(0)).build();
   }

   public GatewayAddress buildLastAddress() {
      return ((GatewayAddressBuilder)this.addresses.get(this.addresses.size() - 1)).build();
   }

   public GatewayAddress buildMatchingAddress(Predicate predicate) {
      for(GatewayAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAddress(Predicate predicate) {
      for(GatewayAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GatewaySpecFluent withAddresses(List addresses) {
      if (this.addresses != null) {
         this._visitables.get("addresses").clear();
      }

      if (addresses != null) {
         this.addresses = new ArrayList();

         for(GatewayAddress item : addresses) {
            this.addToAddresses(item);
         }
      } else {
         this.addresses = null;
      }

      return this;
   }

   public GatewaySpecFluent withAddresses(GatewayAddress... addresses) {
      if (this.addresses != null) {
         this.addresses.clear();
         this._visitables.remove("addresses");
      }

      if (addresses != null) {
         for(GatewayAddress item : addresses) {
            this.addToAddresses(item);
         }
      }

      return this;
   }

   public boolean hasAddresses() {
      return this.addresses != null && !this.addresses.isEmpty();
   }

   public GatewaySpecFluent addNewAddress(String type, String value) {
      return this.addToAddresses(new GatewayAddress(type, value));
   }

   public AddressesNested addNewAddress() {
      return new AddressesNested(-1, (GatewayAddress)null);
   }

   public AddressesNested addNewAddressLike(GatewayAddress item) {
      return new AddressesNested(-1, item);
   }

   public AddressesNested setNewAddressLike(int index, GatewayAddress item) {
      return new AddressesNested(index, item);
   }

   public AddressesNested editAddress(int index) {
      if (this.addresses.size() <= index) {
         throw new RuntimeException("Can't edit addresses. Index exceeds size.");
      } else {
         return this.setNewAddressLike(index, this.buildAddress(index));
      }
   }

   public AddressesNested editFirstAddress() {
      if (this.addresses.size() == 0) {
         throw new RuntimeException("Can't edit first addresses. The list is empty.");
      } else {
         return this.setNewAddressLike(0, this.buildAddress(0));
      }
   }

   public AddressesNested editLastAddress() {
      int index = this.addresses.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last addresses. The list is empty.");
      } else {
         return this.setNewAddressLike(index, this.buildAddress(index));
      }
   }

   public AddressesNested editMatchingAddress(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.addresses.size(); ++i) {
         if (predicate.test((GatewayAddressBuilder)this.addresses.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching addresses. No match found.");
      } else {
         return this.setNewAddressLike(index, this.buildAddress(index));
      }
   }

   public GatewayBackendTLS buildBackendTLS() {
      return this.backendTLS != null ? this.backendTLS.build() : null;
   }

   public GatewaySpecFluent withBackendTLS(GatewayBackendTLS backendTLS) {
      this._visitables.remove("backendTLS");
      if (backendTLS != null) {
         this.backendTLS = new GatewayBackendTLSBuilder(backendTLS);
         this._visitables.get("backendTLS").add(this.backendTLS);
      } else {
         this.backendTLS = null;
         this._visitables.get("backendTLS").remove(this.backendTLS);
      }

      return this;
   }

   public boolean hasBackendTLS() {
      return this.backendTLS != null;
   }

   public BackendTLSNested withNewBackendTLS() {
      return new BackendTLSNested((GatewayBackendTLS)null);
   }

   public BackendTLSNested withNewBackendTLSLike(GatewayBackendTLS item) {
      return new BackendTLSNested(item);
   }

   public BackendTLSNested editBackendTLS() {
      return this.withNewBackendTLSLike((GatewayBackendTLS)Optional.ofNullable(this.buildBackendTLS()).orElse((Object)null));
   }

   public BackendTLSNested editOrNewBackendTLS() {
      return this.withNewBackendTLSLike((GatewayBackendTLS)Optional.ofNullable(this.buildBackendTLS()).orElse((new GatewayBackendTLSBuilder()).build()));
   }

   public BackendTLSNested editOrNewBackendTLSLike(GatewayBackendTLS item) {
      return this.withNewBackendTLSLike((GatewayBackendTLS)Optional.ofNullable(this.buildBackendTLS()).orElse(item));
   }

   public String getGatewayClassName() {
      return this.gatewayClassName;
   }

   public GatewaySpecFluent withGatewayClassName(String gatewayClassName) {
      this.gatewayClassName = gatewayClassName;
      return this;
   }

   public boolean hasGatewayClassName() {
      return this.gatewayClassName != null;
   }

   public GatewayInfrastructure buildInfrastructure() {
      return this.infrastructure != null ? this.infrastructure.build() : null;
   }

   public GatewaySpecFluent withInfrastructure(GatewayInfrastructure infrastructure) {
      this._visitables.remove("infrastructure");
      if (infrastructure != null) {
         this.infrastructure = new GatewayInfrastructureBuilder(infrastructure);
         this._visitables.get("infrastructure").add(this.infrastructure);
      } else {
         this.infrastructure = null;
         this._visitables.get("infrastructure").remove(this.infrastructure);
      }

      return this;
   }

   public boolean hasInfrastructure() {
      return this.infrastructure != null;
   }

   public InfrastructureNested withNewInfrastructure() {
      return new InfrastructureNested((GatewayInfrastructure)null);
   }

   public InfrastructureNested withNewInfrastructureLike(GatewayInfrastructure item) {
      return new InfrastructureNested(item);
   }

   public InfrastructureNested editInfrastructure() {
      return this.withNewInfrastructureLike((GatewayInfrastructure)Optional.ofNullable(this.buildInfrastructure()).orElse((Object)null));
   }

   public InfrastructureNested editOrNewInfrastructure() {
      return this.withNewInfrastructureLike((GatewayInfrastructure)Optional.ofNullable(this.buildInfrastructure()).orElse((new GatewayInfrastructureBuilder()).build()));
   }

   public InfrastructureNested editOrNewInfrastructureLike(GatewayInfrastructure item) {
      return this.withNewInfrastructureLike((GatewayInfrastructure)Optional.ofNullable(this.buildInfrastructure()).orElse(item));
   }

   public GatewaySpecFluent addToListeners(int index, Listener item) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      ListenerBuilder builder = new ListenerBuilder(item);
      if (index >= 0 && index < this.listeners.size()) {
         this._visitables.get("listeners").add(index, builder);
         this.listeners.add(index, builder);
      } else {
         this._visitables.get("listeners").add(builder);
         this.listeners.add(builder);
      }

      return this;
   }

   public GatewaySpecFluent setToListeners(int index, Listener item) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      ListenerBuilder builder = new ListenerBuilder(item);
      if (index >= 0 && index < this.listeners.size()) {
         this._visitables.get("listeners").set(index, builder);
         this.listeners.set(index, builder);
      } else {
         this._visitables.get("listeners").add(builder);
         this.listeners.add(builder);
      }

      return this;
   }

   public GatewaySpecFluent addToListeners(Listener... items) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      for(Listener item : items) {
         ListenerBuilder builder = new ListenerBuilder(item);
         this._visitables.get("listeners").add(builder);
         this.listeners.add(builder);
      }

      return this;
   }

   public GatewaySpecFluent addAllToListeners(Collection items) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      for(Listener item : items) {
         ListenerBuilder builder = new ListenerBuilder(item);
         this._visitables.get("listeners").add(builder);
         this.listeners.add(builder);
      }

      return this;
   }

   public GatewaySpecFluent removeFromListeners(Listener... items) {
      if (this.listeners == null) {
         return this;
      } else {
         for(Listener item : items) {
            ListenerBuilder builder = new ListenerBuilder(item);
            this._visitables.get("listeners").remove(builder);
            this.listeners.remove(builder);
         }

         return this;
      }
   }

   public GatewaySpecFluent removeAllFromListeners(Collection items) {
      if (this.listeners == null) {
         return this;
      } else {
         for(Listener item : items) {
            ListenerBuilder builder = new ListenerBuilder(item);
            this._visitables.get("listeners").remove(builder);
            this.listeners.remove(builder);
         }

         return this;
      }
   }

   public GatewaySpecFluent removeMatchingFromListeners(Predicate predicate) {
      if (this.listeners == null) {
         return this;
      } else {
         Iterator<ListenerBuilder> each = this.listeners.iterator();
         List visitables = this._visitables.get("listeners");

         while(each.hasNext()) {
            ListenerBuilder builder = (ListenerBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildListeners() {
      return this.listeners != null ? build(this.listeners) : null;
   }

   public Listener buildListener(int index) {
      return ((ListenerBuilder)this.listeners.get(index)).build();
   }

   public Listener buildFirstListener() {
      return ((ListenerBuilder)this.listeners.get(0)).build();
   }

   public Listener buildLastListener() {
      return ((ListenerBuilder)this.listeners.get(this.listeners.size() - 1)).build();
   }

   public Listener buildMatchingListener(Predicate predicate) {
      for(ListenerBuilder item : this.listeners) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingListener(Predicate predicate) {
      for(ListenerBuilder item : this.listeners) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GatewaySpecFluent withListeners(List listeners) {
      if (this.listeners != null) {
         this._visitables.get("listeners").clear();
      }

      if (listeners != null) {
         this.listeners = new ArrayList();

         for(Listener item : listeners) {
            this.addToListeners(item);
         }
      } else {
         this.listeners = null;
      }

      return this;
   }

   public GatewaySpecFluent withListeners(Listener... listeners) {
      if (this.listeners != null) {
         this.listeners.clear();
         this._visitables.remove("listeners");
      }

      if (listeners != null) {
         for(Listener item : listeners) {
            this.addToListeners(item);
         }
      }

      return this;
   }

   public boolean hasListeners() {
      return this.listeners != null && !this.listeners.isEmpty();
   }

   public ListenersNested addNewListener() {
      return new ListenersNested(-1, (Listener)null);
   }

   public ListenersNested addNewListenerLike(Listener item) {
      return new ListenersNested(-1, item);
   }

   public ListenersNested setNewListenerLike(int index, Listener item) {
      return new ListenersNested(index, item);
   }

   public ListenersNested editListener(int index) {
      if (this.listeners.size() <= index) {
         throw new RuntimeException("Can't edit listeners. Index exceeds size.");
      } else {
         return this.setNewListenerLike(index, this.buildListener(index));
      }
   }

   public ListenersNested editFirstListener() {
      if (this.listeners.size() == 0) {
         throw new RuntimeException("Can't edit first listeners. The list is empty.");
      } else {
         return this.setNewListenerLike(0, this.buildListener(0));
      }
   }

   public ListenersNested editLastListener() {
      int index = this.listeners.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last listeners. The list is empty.");
      } else {
         return this.setNewListenerLike(index, this.buildListener(index));
      }
   }

   public ListenersNested editMatchingListener(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.listeners.size(); ++i) {
         if (predicate.test((ListenerBuilder)this.listeners.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching listeners. No match found.");
      } else {
         return this.setNewListenerLike(index, this.buildListener(index));
      }
   }

   public GatewaySpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GatewaySpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GatewaySpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GatewaySpecFluent removeFromAdditionalProperties(Map map) {
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

   public GatewaySpecFluent withAdditionalProperties(Map additionalProperties) {
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
            GatewaySpecFluent that = (GatewaySpecFluent)o;
            if (!Objects.equals(this.addresses, that.addresses)) {
               return false;
            } else if (!Objects.equals(this.backendTLS, that.backendTLS)) {
               return false;
            } else if (!Objects.equals(this.gatewayClassName, that.gatewayClassName)) {
               return false;
            } else if (!Objects.equals(this.infrastructure, that.infrastructure)) {
               return false;
            } else if (!Objects.equals(this.listeners, that.listeners)) {
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
      return Objects.hash(new Object[]{this.addresses, this.backendTLS, this.gatewayClassName, this.infrastructure, this.listeners, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.addresses != null && !this.addresses.isEmpty()) {
         sb.append("addresses:");
         sb.append(this.addresses + ",");
      }

      if (this.backendTLS != null) {
         sb.append("backendTLS:");
         sb.append(this.backendTLS + ",");
      }

      if (this.gatewayClassName != null) {
         sb.append("gatewayClassName:");
         sb.append(this.gatewayClassName + ",");
      }

      if (this.infrastructure != null) {
         sb.append("infrastructure:");
         sb.append(this.infrastructure + ",");
      }

      if (this.listeners != null && !this.listeners.isEmpty()) {
         sb.append("listeners:");
         sb.append(this.listeners + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AddressesNested extends GatewayAddressFluent implements Nested {
      GatewayAddressBuilder builder;
      int index;

      AddressesNested(int index, GatewayAddress item) {
         this.index = index;
         this.builder = new GatewayAddressBuilder(this, item);
      }

      public Object and() {
         return GatewaySpecFluent.this.setToAddresses(this.index, this.builder.build());
      }

      public Object endAddress() {
         return this.and();
      }
   }

   public class BackendTLSNested extends GatewayBackendTLSFluent implements Nested {
      GatewayBackendTLSBuilder builder;

      BackendTLSNested(GatewayBackendTLS item) {
         this.builder = new GatewayBackendTLSBuilder(this, item);
      }

      public Object and() {
         return GatewaySpecFluent.this.withBackendTLS(this.builder.build());
      }

      public Object endBackendTLS() {
         return this.and();
      }
   }

   public class InfrastructureNested extends GatewayInfrastructureFluent implements Nested {
      GatewayInfrastructureBuilder builder;

      InfrastructureNested(GatewayInfrastructure item) {
         this.builder = new GatewayInfrastructureBuilder(this, item);
      }

      public Object and() {
         return GatewaySpecFluent.this.withInfrastructure(this.builder.build());
      }

      public Object endInfrastructure() {
         return this.and();
      }
   }

   public class ListenersNested extends ListenerFluent implements Nested {
      ListenerBuilder builder;
      int index;

      ListenersNested(int index, Listener item) {
         this.index = index;
         this.builder = new ListenerBuilder(this, item);
      }

      public Object and() {
         return GatewaySpecFluent.this.setToListeners(this.index, this.builder.build());
      }

      public Object endListener() {
         return this.and();
      }
   }
}
