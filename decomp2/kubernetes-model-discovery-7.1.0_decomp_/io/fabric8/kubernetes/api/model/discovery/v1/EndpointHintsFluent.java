package io.fabric8.kubernetes.api.model.discovery.v1;

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

public class EndpointHintsFluent extends BaseFluent {
   private ArrayList forZones = new ArrayList();
   private Map additionalProperties;

   public EndpointHintsFluent() {
   }

   public EndpointHintsFluent(EndpointHints instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EndpointHints instance) {
      instance = instance != null ? instance : new EndpointHints();
      if (instance != null) {
         this.withForZones(instance.getForZones());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public EndpointHintsFluent addToForZones(int index, ForZone item) {
      if (this.forZones == null) {
         this.forZones = new ArrayList();
      }

      ForZoneBuilder builder = new ForZoneBuilder(item);
      if (index >= 0 && index < this.forZones.size()) {
         this._visitables.get("forZones").add(index, builder);
         this.forZones.add(index, builder);
      } else {
         this._visitables.get("forZones").add(builder);
         this.forZones.add(builder);
      }

      return this;
   }

   public EndpointHintsFluent setToForZones(int index, ForZone item) {
      if (this.forZones == null) {
         this.forZones = new ArrayList();
      }

      ForZoneBuilder builder = new ForZoneBuilder(item);
      if (index >= 0 && index < this.forZones.size()) {
         this._visitables.get("forZones").set(index, builder);
         this.forZones.set(index, builder);
      } else {
         this._visitables.get("forZones").add(builder);
         this.forZones.add(builder);
      }

      return this;
   }

   public EndpointHintsFluent addToForZones(ForZone... items) {
      if (this.forZones == null) {
         this.forZones = new ArrayList();
      }

      for(ForZone item : items) {
         ForZoneBuilder builder = new ForZoneBuilder(item);
         this._visitables.get("forZones").add(builder);
         this.forZones.add(builder);
      }

      return this;
   }

   public EndpointHintsFluent addAllToForZones(Collection items) {
      if (this.forZones == null) {
         this.forZones = new ArrayList();
      }

      for(ForZone item : items) {
         ForZoneBuilder builder = new ForZoneBuilder(item);
         this._visitables.get("forZones").add(builder);
         this.forZones.add(builder);
      }

      return this;
   }

   public EndpointHintsFluent removeFromForZones(ForZone... items) {
      if (this.forZones == null) {
         return this;
      } else {
         for(ForZone item : items) {
            ForZoneBuilder builder = new ForZoneBuilder(item);
            this._visitables.get("forZones").remove(builder);
            this.forZones.remove(builder);
         }

         return this;
      }
   }

   public EndpointHintsFluent removeAllFromForZones(Collection items) {
      if (this.forZones == null) {
         return this;
      } else {
         for(ForZone item : items) {
            ForZoneBuilder builder = new ForZoneBuilder(item);
            this._visitables.get("forZones").remove(builder);
            this.forZones.remove(builder);
         }

         return this;
      }
   }

   public EndpointHintsFluent removeMatchingFromForZones(Predicate predicate) {
      if (this.forZones == null) {
         return this;
      } else {
         Iterator<ForZoneBuilder> each = this.forZones.iterator();
         List visitables = this._visitables.get("forZones");

         while(each.hasNext()) {
            ForZoneBuilder builder = (ForZoneBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildForZones() {
      return this.forZones != null ? build(this.forZones) : null;
   }

   public ForZone buildForZone(int index) {
      return ((ForZoneBuilder)this.forZones.get(index)).build();
   }

   public ForZone buildFirstForZone() {
      return ((ForZoneBuilder)this.forZones.get(0)).build();
   }

   public ForZone buildLastForZone() {
      return ((ForZoneBuilder)this.forZones.get(this.forZones.size() - 1)).build();
   }

   public ForZone buildMatchingForZone(Predicate predicate) {
      for(ForZoneBuilder item : this.forZones) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingForZone(Predicate predicate) {
      for(ForZoneBuilder item : this.forZones) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EndpointHintsFluent withForZones(List forZones) {
      if (this.forZones != null) {
         this._visitables.get("forZones").clear();
      }

      if (forZones != null) {
         this.forZones = new ArrayList();

         for(ForZone item : forZones) {
            this.addToForZones(item);
         }
      } else {
         this.forZones = null;
      }

      return this;
   }

   public EndpointHintsFluent withForZones(ForZone... forZones) {
      if (this.forZones != null) {
         this.forZones.clear();
         this._visitables.remove("forZones");
      }

      if (forZones != null) {
         for(ForZone item : forZones) {
            this.addToForZones(item);
         }
      }

      return this;
   }

   public boolean hasForZones() {
      return this.forZones != null && !this.forZones.isEmpty();
   }

   public EndpointHintsFluent addNewForZone(String name) {
      return this.addToForZones(new ForZone(name));
   }

   public ForZonesNested addNewForZone() {
      return new ForZonesNested(-1, (ForZone)null);
   }

   public ForZonesNested addNewForZoneLike(ForZone item) {
      return new ForZonesNested(-1, item);
   }

   public ForZonesNested setNewForZoneLike(int index, ForZone item) {
      return new ForZonesNested(index, item);
   }

   public ForZonesNested editForZone(int index) {
      if (this.forZones.size() <= index) {
         throw new RuntimeException("Can't edit forZones. Index exceeds size.");
      } else {
         return this.setNewForZoneLike(index, this.buildForZone(index));
      }
   }

   public ForZonesNested editFirstForZone() {
      if (this.forZones.size() == 0) {
         throw new RuntimeException("Can't edit first forZones. The list is empty.");
      } else {
         return this.setNewForZoneLike(0, this.buildForZone(0));
      }
   }

   public ForZonesNested editLastForZone() {
      int index = this.forZones.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last forZones. The list is empty.");
      } else {
         return this.setNewForZoneLike(index, this.buildForZone(index));
      }
   }

   public ForZonesNested editMatchingForZone(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.forZones.size(); ++i) {
         if (predicate.test((ForZoneBuilder)this.forZones.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching forZones. No match found.");
      } else {
         return this.setNewForZoneLike(index, this.buildForZone(index));
      }
   }

   public EndpointHintsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EndpointHintsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EndpointHintsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EndpointHintsFluent removeFromAdditionalProperties(Map map) {
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

   public EndpointHintsFluent withAdditionalProperties(Map additionalProperties) {
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
            EndpointHintsFluent that = (EndpointHintsFluent)o;
            if (!Objects.equals(this.forZones, that.forZones)) {
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
      return Objects.hash(new Object[]{this.forZones, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.forZones != null && !this.forZones.isEmpty()) {
         sb.append("forZones:");
         sb.append(this.forZones + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ForZonesNested extends ForZoneFluent implements Nested {
      ForZoneBuilder builder;
      int index;

      ForZonesNested(int index, ForZone item) {
         this.index = index;
         this.builder = new ForZoneBuilder(this, item);
      }

      public Object and() {
         return EndpointHintsFluent.this.setToForZones(this.index, this.builder.build());
      }

      public Object endForZone() {
         return this.and();
      }
   }
}
