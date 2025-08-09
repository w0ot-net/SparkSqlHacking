package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.Condition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class GatewayStatusFluent extends BaseFluent {
   private ArrayList addresses = new ArrayList();
   private List conditions = new ArrayList();
   private ArrayList listeners = new ArrayList();
   private Map additionalProperties;

   public GatewayStatusFluent() {
   }

   public GatewayStatusFluent(GatewayStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GatewayStatus instance) {
      instance = instance != null ? instance : new GatewayStatus();
      if (instance != null) {
         this.withAddresses(instance.getAddresses());
         this.withConditions(instance.getConditions());
         this.withListeners(instance.getListeners());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public GatewayStatusFluent addToAddresses(int index, GatewayStatusAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      GatewayStatusAddressBuilder builder = new GatewayStatusAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").add(index, builder);
         this.addresses.add(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public GatewayStatusFluent setToAddresses(int index, GatewayStatusAddress item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      GatewayStatusAddressBuilder builder = new GatewayStatusAddressBuilder(item);
      if (index >= 0 && index < this.addresses.size()) {
         this._visitables.get("addresses").set(index, builder);
         this.addresses.set(index, builder);
      } else {
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public GatewayStatusFluent addToAddresses(GatewayStatusAddress... items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(GatewayStatusAddress item : items) {
         GatewayStatusAddressBuilder builder = new GatewayStatusAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public GatewayStatusFluent addAllToAddresses(Collection items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(GatewayStatusAddress item : items) {
         GatewayStatusAddressBuilder builder = new GatewayStatusAddressBuilder(item);
         this._visitables.get("addresses").add(builder);
         this.addresses.add(builder);
      }

      return this;
   }

   public GatewayStatusFluent removeFromAddresses(GatewayStatusAddress... items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(GatewayStatusAddress item : items) {
            GatewayStatusAddressBuilder builder = new GatewayStatusAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public GatewayStatusFluent removeAllFromAddresses(Collection items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(GatewayStatusAddress item : items) {
            GatewayStatusAddressBuilder builder = new GatewayStatusAddressBuilder(item);
            this._visitables.get("addresses").remove(builder);
            this.addresses.remove(builder);
         }

         return this;
      }
   }

   public GatewayStatusFluent removeMatchingFromAddresses(Predicate predicate) {
      if (this.addresses == null) {
         return this;
      } else {
         Iterator<GatewayStatusAddressBuilder> each = this.addresses.iterator();
         List visitables = this._visitables.get("addresses");

         while(each.hasNext()) {
            GatewayStatusAddressBuilder builder = (GatewayStatusAddressBuilder)each.next();
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

   public GatewayStatusAddress buildAddress(int index) {
      return ((GatewayStatusAddressBuilder)this.addresses.get(index)).build();
   }

   public GatewayStatusAddress buildFirstAddress() {
      return ((GatewayStatusAddressBuilder)this.addresses.get(0)).build();
   }

   public GatewayStatusAddress buildLastAddress() {
      return ((GatewayStatusAddressBuilder)this.addresses.get(this.addresses.size() - 1)).build();
   }

   public GatewayStatusAddress buildMatchingAddress(Predicate predicate) {
      for(GatewayStatusAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAddress(Predicate predicate) {
      for(GatewayStatusAddressBuilder item : this.addresses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GatewayStatusFluent withAddresses(List addresses) {
      if (this.addresses != null) {
         this._visitables.get("addresses").clear();
      }

      if (addresses != null) {
         this.addresses = new ArrayList();

         for(GatewayStatusAddress item : addresses) {
            this.addToAddresses(item);
         }
      } else {
         this.addresses = null;
      }

      return this;
   }

   public GatewayStatusFluent withAddresses(GatewayStatusAddress... addresses) {
      if (this.addresses != null) {
         this.addresses.clear();
         this._visitables.remove("addresses");
      }

      if (addresses != null) {
         for(GatewayStatusAddress item : addresses) {
            this.addToAddresses(item);
         }
      }

      return this;
   }

   public boolean hasAddresses() {
      return this.addresses != null && !this.addresses.isEmpty();
   }

   public GatewayStatusFluent addNewAddress(String type, String value) {
      return this.addToAddresses(new GatewayStatusAddress(type, value));
   }

   public AddressesNested addNewAddress() {
      return new AddressesNested(-1, (GatewayStatusAddress)null);
   }

   public AddressesNested addNewAddressLike(GatewayStatusAddress item) {
      return new AddressesNested(-1, item);
   }

   public AddressesNested setNewAddressLike(int index, GatewayStatusAddress item) {
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
         if (predicate.test((GatewayStatusAddressBuilder)this.addresses.get(i))) {
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

   public GatewayStatusFluent addToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.add(index, item);
      return this;
   }

   public GatewayStatusFluent setToConditions(int index, Condition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      this.conditions.set(index, item);
      return this;
   }

   public GatewayStatusFluent addToConditions(Condition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public GatewayStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(Condition item : items) {
         this.conditions.add(item);
      }

      return this;
   }

   public GatewayStatusFluent removeFromConditions(Condition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public GatewayStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(Condition item : items) {
            this.conditions.remove(item);
         }

         return this;
      }
   }

   public List getConditions() {
      return this.conditions;
   }

   public Condition getCondition(int index) {
      return (Condition)this.conditions.get(index);
   }

   public Condition getFirstCondition() {
      return (Condition)this.conditions.get(0);
   }

   public Condition getLastCondition() {
      return (Condition)this.conditions.get(this.conditions.size() - 1);
   }

   public Condition getMatchingCondition(Predicate predicate) {
      for(Condition item : this.conditions) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(Condition item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GatewayStatusFluent withConditions(List conditions) {
      if (conditions != null) {
         this.conditions = new ArrayList();

         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public GatewayStatusFluent withConditions(Condition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(Condition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public GatewayStatusFluent addToListeners(int index, ListenerStatus item) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      ListenerStatusBuilder builder = new ListenerStatusBuilder(item);
      if (index >= 0 && index < this.listeners.size()) {
         this._visitables.get("listeners").add(index, builder);
         this.listeners.add(index, builder);
      } else {
         this._visitables.get("listeners").add(builder);
         this.listeners.add(builder);
      }

      return this;
   }

   public GatewayStatusFluent setToListeners(int index, ListenerStatus item) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      ListenerStatusBuilder builder = new ListenerStatusBuilder(item);
      if (index >= 0 && index < this.listeners.size()) {
         this._visitables.get("listeners").set(index, builder);
         this.listeners.set(index, builder);
      } else {
         this._visitables.get("listeners").add(builder);
         this.listeners.add(builder);
      }

      return this;
   }

   public GatewayStatusFluent addToListeners(ListenerStatus... items) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      for(ListenerStatus item : items) {
         ListenerStatusBuilder builder = new ListenerStatusBuilder(item);
         this._visitables.get("listeners").add(builder);
         this.listeners.add(builder);
      }

      return this;
   }

   public GatewayStatusFluent addAllToListeners(Collection items) {
      if (this.listeners == null) {
         this.listeners = new ArrayList();
      }

      for(ListenerStatus item : items) {
         ListenerStatusBuilder builder = new ListenerStatusBuilder(item);
         this._visitables.get("listeners").add(builder);
         this.listeners.add(builder);
      }

      return this;
   }

   public GatewayStatusFluent removeFromListeners(ListenerStatus... items) {
      if (this.listeners == null) {
         return this;
      } else {
         for(ListenerStatus item : items) {
            ListenerStatusBuilder builder = new ListenerStatusBuilder(item);
            this._visitables.get("listeners").remove(builder);
            this.listeners.remove(builder);
         }

         return this;
      }
   }

   public GatewayStatusFluent removeAllFromListeners(Collection items) {
      if (this.listeners == null) {
         return this;
      } else {
         for(ListenerStatus item : items) {
            ListenerStatusBuilder builder = new ListenerStatusBuilder(item);
            this._visitables.get("listeners").remove(builder);
            this.listeners.remove(builder);
         }

         return this;
      }
   }

   public GatewayStatusFluent removeMatchingFromListeners(Predicate predicate) {
      if (this.listeners == null) {
         return this;
      } else {
         Iterator<ListenerStatusBuilder> each = this.listeners.iterator();
         List visitables = this._visitables.get("listeners");

         while(each.hasNext()) {
            ListenerStatusBuilder builder = (ListenerStatusBuilder)each.next();
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

   public ListenerStatus buildListener(int index) {
      return ((ListenerStatusBuilder)this.listeners.get(index)).build();
   }

   public ListenerStatus buildFirstListener() {
      return ((ListenerStatusBuilder)this.listeners.get(0)).build();
   }

   public ListenerStatus buildLastListener() {
      return ((ListenerStatusBuilder)this.listeners.get(this.listeners.size() - 1)).build();
   }

   public ListenerStatus buildMatchingListener(Predicate predicate) {
      for(ListenerStatusBuilder item : this.listeners) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingListener(Predicate predicate) {
      for(ListenerStatusBuilder item : this.listeners) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public GatewayStatusFluent withListeners(List listeners) {
      if (this.listeners != null) {
         this._visitables.get("listeners").clear();
      }

      if (listeners != null) {
         this.listeners = new ArrayList();

         for(ListenerStatus item : listeners) {
            this.addToListeners(item);
         }
      } else {
         this.listeners = null;
      }

      return this;
   }

   public GatewayStatusFluent withListeners(ListenerStatus... listeners) {
      if (this.listeners != null) {
         this.listeners.clear();
         this._visitables.remove("listeners");
      }

      if (listeners != null) {
         for(ListenerStatus item : listeners) {
            this.addToListeners(item);
         }
      }

      return this;
   }

   public boolean hasListeners() {
      return this.listeners != null && !this.listeners.isEmpty();
   }

   public ListenersNested addNewListener() {
      return new ListenersNested(-1, (ListenerStatus)null);
   }

   public ListenersNested addNewListenerLike(ListenerStatus item) {
      return new ListenersNested(-1, item);
   }

   public ListenersNested setNewListenerLike(int index, ListenerStatus item) {
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
         if (predicate.test((ListenerStatusBuilder)this.listeners.get(i))) {
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

   public GatewayStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GatewayStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GatewayStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GatewayStatusFluent removeFromAdditionalProperties(Map map) {
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

   public GatewayStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            GatewayStatusFluent that = (GatewayStatusFluent)o;
            if (!Objects.equals(this.addresses, that.addresses)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
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
      return Objects.hash(new Object[]{this.addresses, this.conditions, this.listeners, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.addresses != null && !this.addresses.isEmpty()) {
         sb.append("addresses:");
         sb.append(this.addresses + ",");
      }

      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
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

   public class AddressesNested extends GatewayStatusAddressFluent implements Nested {
      GatewayStatusAddressBuilder builder;
      int index;

      AddressesNested(int index, GatewayStatusAddress item) {
         this.index = index;
         this.builder = new GatewayStatusAddressBuilder(this, item);
      }

      public Object and() {
         return GatewayStatusFluent.this.setToAddresses(this.index, this.builder.build());
      }

      public Object endAddress() {
         return this.and();
      }
   }

   public class ListenersNested extends ListenerStatusFluent implements Nested {
      ListenerStatusBuilder builder;
      int index;

      ListenersNested(int index, ListenerStatus item) {
         this.index = index;
         this.builder = new ListenerStatusBuilder(this, item);
      }

      public Object and() {
         return GatewayStatusFluent.this.setToListeners(this.index, this.builder.build());
      }

      public Object endListener() {
         return this.and();
      }
   }
}
