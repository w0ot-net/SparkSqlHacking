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

public class EndpointSubsetFluent extends BaseFluent {
   private ArrayList addresses = new ArrayList();
   private ArrayList notReadyAddresses = new ArrayList();
   private ArrayList ports = new ArrayList();
   private Map additionalProperties;

   public EndpointSubsetFluent() {
   }

   public EndpointSubsetFluent(EndpointSubset instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EndpointSubset instance) {
      instance = instance != null ? instance : new EndpointSubset();
      if (instance != null) {
         this.withAddresses(instance.getAddresses());
         this.withNotReadyAddresses(instance.getNotReadyAddresses());
         this.withPorts(instance.getPorts());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public EndpointSubsetFluent addToAddresses(int index, EndpointAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").add(index, builder);
         this.addresses.add(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public EndpointSubsetFluent setToAddresses(int index, EndpointAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").set(index, builder);
         this.addresses.set(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public EndpointSubsetFluent addToAddresses(EndpointAddress... items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(EndpointAddress item : items) {
         EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public EndpointSubsetFluent addAllToAddresses(Collection items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(EndpointAddress item : items) {
         EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public EndpointSubsetFluent removeFromAddresses(EndpointAddress... items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(EndpointAddress item : items) {
            EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public EndpointSubsetFluent removeAllFromAddresses(Collection items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(EndpointAddress item : items) {
            EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public EndpointSubsetFluent removeMatchingFromAddresses(Predicate predicate) {
      if (this.addresses == null) {
         return this;
      } else {
         Iterator<EndpointAddressBuilder> each = this.addresses.iterator();
         List visitables = this._visitables.get("addresses");

         while(each.hasNext()) {
            EndpointAddressBuilder builder = (EndpointAddressBuilder)each.next();
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

   public EndpointAddress buildAddress(int index) {
      return ((EndpointAddressBuilder)this.addresses.get(index)).build();
   }

   public EndpointAddress buildFirstAddress() {
      return ((EndpointAddressBuilder)this.addresses.get(0)).build();
   }

   public EndpointAddress buildLastAddress() {
      return ((EndpointAddressBuilder)this.addresses.get(this.addresses.size() - 1)).build();
   }

   public EndpointAddress buildMatchingAddress(Predicate predicate) {
      for(EndpointAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAddress(Predicate predicate) {
      for(EndpointAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EndpointSubsetFluent withAddresses(List addresses) {
      if (this.addresses != null) {
         this._visitables.get("addresses").clear();
      }

      if (addresses != null) {
         this.addresses = new ArrayList();

         for(EndpointAddress item : addresses) {
            this.addToAddresses(item);
         }
      } else {
         this.addresses = null;
      }

      return this;
   }

   public EndpointSubsetFluent withAddresses(EndpointAddress... addresses) {
      if (this.addresses != null) {
         this.addresses.clear();
         this._visitables.remove("addresses");
      }

      if (addresses != null) {
         for(EndpointAddress item : addresses) {
            this.addToAddresses(item);
         }
      }

      return this;
   }

   public boolean hasAddresses() {
      return this.addresses != null && !this.addresses.isEmpty();
   }

   public AddressesNested addNewAddress() {
      return new AddressesNested(-1, (EndpointAddress)null);
   }

   public AddressesNested addNewAddressLike(EndpointAddress item) {
      return new AddressesNested(-1, item);
   }

   public AddressesNested setNewAddressLike(int index, EndpointAddress item) {
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
         if (predicate.test((EndpointAddressBuilder)this.addresses.get(i))) {
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

   public EndpointSubsetFluent addToNotReadyAddresses(int index, EndpointAddress item) {
      if (this.notReadyAddresses == null) {
         this.notReadyAddresses = new ArrayList();
      }

      EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
      if (index >= 0 && index < this.notReadyAddresses.size()) {
         this._visitables.get("notReadyAddresses").add(index, builder);
         this.notReadyAddresses.add(index, builder);
      } else {
         this._visitables.get("notReadyAddresses").add(builder);
         this.notReadyAddresses.add(builder);
      }

      return this;
   }

   public EndpointSubsetFluent setToNotReadyAddresses(int index, EndpointAddress item) {
      if (this.notReadyAddresses == null) {
         this.notReadyAddresses = new ArrayList();
      }

      EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
      if (index >= 0 && index < this.notReadyAddresses.size()) {
         this._visitables.get("notReadyAddresses").set(index, builder);
         this.notReadyAddresses.set(index, builder);
      } else {
         this._visitables.get("notReadyAddresses").add(builder);
         this.notReadyAddresses.add(builder);
      }

      return this;
   }

   public EndpointSubsetFluent addToNotReadyAddresses(EndpointAddress... items) {
      if (this.notReadyAddresses == null) {
         this.notReadyAddresses = new ArrayList();
      }

      for(EndpointAddress item : items) {
         EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
         this._visitables.get("notReadyAddresses").add(builder);
         this.notReadyAddresses.add(builder);
      }

      return this;
   }

   public EndpointSubsetFluent addAllToNotReadyAddresses(Collection items) {
      if (this.notReadyAddresses == null) {
         this.notReadyAddresses = new ArrayList();
      }

      for(EndpointAddress item : items) {
         EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
         this._visitables.get("notReadyAddresses").add(builder);
         this.notReadyAddresses.add(builder);
      }

      return this;
   }

   public EndpointSubsetFluent removeFromNotReadyAddresses(EndpointAddress... items) {
      if (this.notReadyAddresses == null) {
         return this;
      } else {
         for(EndpointAddress item : items) {
            EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
            this._visitables.get("notReadyAddresses").remove(builder);
            this.notReadyAddresses.remove(builder);
         }

         return this;
      }
   }

   public EndpointSubsetFluent removeAllFromNotReadyAddresses(Collection items) {
      if (this.notReadyAddresses == null) {
         return this;
      } else {
         for(EndpointAddress item : items) {
            EndpointAddressBuilder builder = new EndpointAddressBuilder(item);
            this._visitables.get("notReadyAddresses").remove(builder);
            this.notReadyAddresses.remove(builder);
         }

         return this;
      }
   }

   public EndpointSubsetFluent removeMatchingFromNotReadyAddresses(Predicate predicate) {
      if (this.notReadyAddresses == null) {
         return this;
      } else {
         Iterator<EndpointAddressBuilder> each = this.notReadyAddresses.iterator();
         List visitables = this._visitables.get("notReadyAddresses");

         while(each.hasNext()) {
            EndpointAddressBuilder builder = (EndpointAddressBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildNotReadyAddresses() {
      return this.notReadyAddresses != null ? build(this.notReadyAddresses) : null;
   }

   public EndpointAddress buildNotReadyAddress(int index) {
      return ((EndpointAddressBuilder)this.notReadyAddresses.get(index)).build();
   }

   public EndpointAddress buildFirstNotReadyAddress() {
      return ((EndpointAddressBuilder)this.notReadyAddresses.get(0)).build();
   }

   public EndpointAddress buildLastNotReadyAddress() {
      return ((EndpointAddressBuilder)this.notReadyAddresses.get(this.notReadyAddresses.size() - 1)).build();
   }

   public EndpointAddress buildMatchingNotReadyAddress(Predicate predicate) {
      for(EndpointAddressBuilder item : this.notReadyAddresses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingNotReadyAddress(Predicate predicate) {
      for(EndpointAddressBuilder item : this.notReadyAddresses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EndpointSubsetFluent withNotReadyAddresses(List notReadyAddresses) {
      if (this.notReadyAddresses != null) {
         this._visitables.get("notReadyAddresses").clear();
      }

      if (notReadyAddresses != null) {
         this.notReadyAddresses = new ArrayList();

         for(EndpointAddress item : notReadyAddresses) {
            this.addToNotReadyAddresses(item);
         }
      } else {
         this.notReadyAddresses = null;
      }

      return this;
   }

   public EndpointSubsetFluent withNotReadyAddresses(EndpointAddress... notReadyAddresses) {
      if (this.notReadyAddresses != null) {
         this.notReadyAddresses.clear();
         this._visitables.remove("notReadyAddresses");
      }

      if (notReadyAddresses != null) {
         for(EndpointAddress item : notReadyAddresses) {
            this.addToNotReadyAddresses(item);
         }
      }

      return this;
   }

   public boolean hasNotReadyAddresses() {
      return this.notReadyAddresses != null && !this.notReadyAddresses.isEmpty();
   }

   public NotReadyAddressesNested addNewNotReadyAddress() {
      return new NotReadyAddressesNested(-1, (EndpointAddress)null);
   }

   public NotReadyAddressesNested addNewNotReadyAddressLike(EndpointAddress item) {
      return new NotReadyAddressesNested(-1, item);
   }

   public NotReadyAddressesNested setNewNotReadyAddressLike(int index, EndpointAddress item) {
      return new NotReadyAddressesNested(index, item);
   }

   public NotReadyAddressesNested editNotReadyAddress(int index) {
      if (this.notReadyAddresses.size() <= index) {
         throw new RuntimeException("Can't edit notReadyAddresses. Index exceeds size.");
      } else {
         return this.setNewNotReadyAddressLike(index, this.buildNotReadyAddress(index));
      }
   }

   public NotReadyAddressesNested editFirstNotReadyAddress() {
      if (this.notReadyAddresses.size() == 0) {
         throw new RuntimeException("Can't edit first notReadyAddresses. The list is empty.");
      } else {
         return this.setNewNotReadyAddressLike(0, this.buildNotReadyAddress(0));
      }
   }

   public NotReadyAddressesNested editLastNotReadyAddress() {
      int index = this.notReadyAddresses.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last notReadyAddresses. The list is empty.");
      } else {
         return this.setNewNotReadyAddressLike(index, this.buildNotReadyAddress(index));
      }
   }

   public NotReadyAddressesNested editMatchingNotReadyAddress(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.notReadyAddresses.size(); ++i) {
         if (predicate.test((EndpointAddressBuilder)this.notReadyAddresses.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching notReadyAddresses. No match found.");
      } else {
         return this.setNewNotReadyAddressLike(index, this.buildNotReadyAddress(index));
      }
   }

   public EndpointSubsetFluent addToPorts(int index, EndpointPort item) {
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

   public EndpointSubsetFluent setToPorts(int index, EndpointPort item) {
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

   public EndpointSubsetFluent addToPorts(EndpointPort... items) {
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

   public EndpointSubsetFluent addAllToPorts(Collection items) {
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

   public EndpointSubsetFluent removeFromPorts(EndpointPort... items) {
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

   public EndpointSubsetFluent removeAllFromPorts(Collection items) {
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

   public EndpointSubsetFluent removeMatchingFromPorts(Predicate predicate) {
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

   public EndpointSubsetFluent withPorts(List ports) {
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

   public EndpointSubsetFluent withPorts(EndpointPort... ports) {
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

   public EndpointSubsetFluent addNewPort(String appProtocol, String name, Integer port, String protocol) {
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

   public EndpointSubsetFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EndpointSubsetFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EndpointSubsetFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EndpointSubsetFluent removeFromAdditionalProperties(Map map) {
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

   public EndpointSubsetFluent withAdditionalProperties(Map additionalProperties) {
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
            EndpointSubsetFluent that = (EndpointSubsetFluent)o;
            if (!Objects.equals(this.addresses, that.addresses)) {
               return false;
            } else if (!Objects.equals(this.notReadyAddresses, that.notReadyAddresses)) {
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
      return Objects.hash(new Object[]{this.addresses, this.notReadyAddresses, this.ports, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.addresses != null && !this.addresses.isEmpty()) {
         sb.append("addresses:");
         sb.append(this.addresses + ",");
      }

      if (this.notReadyAddresses != null && !this.notReadyAddresses.isEmpty()) {
         sb.append("notReadyAddresses:");
         sb.append(this.notReadyAddresses + ",");
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

   public class AddressesNested extends EndpointAddressFluent implements Nested {
      EndpointAddressBuilder builder;
      int index;

      AddressesNested(int index, EndpointAddress item) {
         this.index = index;
         this.builder = new EndpointAddressBuilder(this, item);
      }

      public Object and() {
         return EndpointSubsetFluent.this.setToAddresses(this.index, this.builder.build());
      }

      public Object endAddress() {
         return this.and();
      }
   }

   public class NotReadyAddressesNested extends EndpointAddressFluent implements Nested {
      EndpointAddressBuilder builder;
      int index;

      NotReadyAddressesNested(int index, EndpointAddress item) {
         this.index = index;
         this.builder = new EndpointAddressBuilder(this, item);
      }

      public Object and() {
         return EndpointSubsetFluent.this.setToNotReadyAddresses(this.index, this.builder.build());
      }

      public Object endNotReadyAddress() {
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
         return EndpointSubsetFluent.this.setToPorts(this.index, this.builder.build());
      }

      public Object endPort() {
         return this.and();
      }
   }
}
