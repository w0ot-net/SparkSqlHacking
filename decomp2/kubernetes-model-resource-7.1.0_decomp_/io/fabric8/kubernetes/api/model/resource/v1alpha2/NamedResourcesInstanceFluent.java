package io.fabric8.kubernetes.api.model.resource.v1alpha2;

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

public class NamedResourcesInstanceFluent extends BaseFluent {
   private ArrayList attributes = new ArrayList();
   private String name;
   private Map additionalProperties;

   public NamedResourcesInstanceFluent() {
   }

   public NamedResourcesInstanceFluent(NamedResourcesInstance instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NamedResourcesInstance instance) {
      instance = instance != null ? instance : new NamedResourcesInstance();
      if (instance != null) {
         this.withAttributes(instance.getAttributes());
         this.withName(instance.getName());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NamedResourcesInstanceFluent addToAttributes(int index, NamedResourcesAttribute item) {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      NamedResourcesAttributeBuilder builder = new NamedResourcesAttributeBuilder(item);
      if (index >= 0 && index < this.attributes.size()) {
         this._visitables.get("attributes").add(index, builder);
         this.attributes.add(index, builder);
      } else {
         this._visitables.get("attributes").add(builder);
         this.attributes.add(builder);
      }

      return this;
   }

   public NamedResourcesInstanceFluent setToAttributes(int index, NamedResourcesAttribute item) {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      NamedResourcesAttributeBuilder builder = new NamedResourcesAttributeBuilder(item);
      if (index >= 0 && index < this.attributes.size()) {
         this._visitables.get("attributes").set(index, builder);
         this.attributes.set(index, builder);
      } else {
         this._visitables.get("attributes").add(builder);
         this.attributes.add(builder);
      }

      return this;
   }

   public NamedResourcesInstanceFluent addToAttributes(NamedResourcesAttribute... items) {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      for(NamedResourcesAttribute item : items) {
         NamedResourcesAttributeBuilder builder = new NamedResourcesAttributeBuilder(item);
         this._visitables.get("attributes").add(builder);
         this.attributes.add(builder);
      }

      return this;
   }

   public NamedResourcesInstanceFluent addAllToAttributes(Collection items) {
      if (this.attributes == null) {
         this.attributes = new ArrayList();
      }

      for(NamedResourcesAttribute item : items) {
         NamedResourcesAttributeBuilder builder = new NamedResourcesAttributeBuilder(item);
         this._visitables.get("attributes").add(builder);
         this.attributes.add(builder);
      }

      return this;
   }

   public NamedResourcesInstanceFluent removeFromAttributes(NamedResourcesAttribute... items) {
      if (this.attributes == null) {
         return this;
      } else {
         for(NamedResourcesAttribute item : items) {
            NamedResourcesAttributeBuilder builder = new NamedResourcesAttributeBuilder(item);
            this._visitables.get("attributes").remove(builder);
            this.attributes.remove(builder);
         }

         return this;
      }
   }

   public NamedResourcesInstanceFluent removeAllFromAttributes(Collection items) {
      if (this.attributes == null) {
         return this;
      } else {
         for(NamedResourcesAttribute item : items) {
            NamedResourcesAttributeBuilder builder = new NamedResourcesAttributeBuilder(item);
            this._visitables.get("attributes").remove(builder);
            this.attributes.remove(builder);
         }

         return this;
      }
   }

   public NamedResourcesInstanceFluent removeMatchingFromAttributes(Predicate predicate) {
      if (this.attributes == null) {
         return this;
      } else {
         Iterator<NamedResourcesAttributeBuilder> each = this.attributes.iterator();
         List visitables = this._visitables.get("attributes");

         while(each.hasNext()) {
            NamedResourcesAttributeBuilder builder = (NamedResourcesAttributeBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAttributes() {
      return this.attributes != null ? build(this.attributes) : null;
   }

   public NamedResourcesAttribute buildAttribute(int index) {
      return ((NamedResourcesAttributeBuilder)this.attributes.get(index)).build();
   }

   public NamedResourcesAttribute buildFirstAttribute() {
      return ((NamedResourcesAttributeBuilder)this.attributes.get(0)).build();
   }

   public NamedResourcesAttribute buildLastAttribute() {
      return ((NamedResourcesAttributeBuilder)this.attributes.get(this.attributes.size() - 1)).build();
   }

   public NamedResourcesAttribute buildMatchingAttribute(Predicate predicate) {
      for(NamedResourcesAttributeBuilder item : this.attributes) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAttribute(Predicate predicate) {
      for(NamedResourcesAttributeBuilder item : this.attributes) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NamedResourcesInstanceFluent withAttributes(List attributes) {
      if (this.attributes != null) {
         this._visitables.get("attributes").clear();
      }

      if (attributes != null) {
         this.attributes = new ArrayList();

         for(NamedResourcesAttribute item : attributes) {
            this.addToAttributes(item);
         }
      } else {
         this.attributes = null;
      }

      return this;
   }

   public NamedResourcesInstanceFluent withAttributes(NamedResourcesAttribute... attributes) {
      if (this.attributes != null) {
         this.attributes.clear();
         this._visitables.remove("attributes");
      }

      if (attributes != null) {
         for(NamedResourcesAttribute item : attributes) {
            this.addToAttributes(item);
         }
      }

      return this;
   }

   public boolean hasAttributes() {
      return this.attributes != null && !this.attributes.isEmpty();
   }

   public AttributesNested addNewAttribute() {
      return new AttributesNested(-1, (NamedResourcesAttribute)null);
   }

   public AttributesNested addNewAttributeLike(NamedResourcesAttribute item) {
      return new AttributesNested(-1, item);
   }

   public AttributesNested setNewAttributeLike(int index, NamedResourcesAttribute item) {
      return new AttributesNested(index, item);
   }

   public AttributesNested editAttribute(int index) {
      if (this.attributes.size() <= index) {
         throw new RuntimeException("Can't edit attributes. Index exceeds size.");
      } else {
         return this.setNewAttributeLike(index, this.buildAttribute(index));
      }
   }

   public AttributesNested editFirstAttribute() {
      if (this.attributes.size() == 0) {
         throw new RuntimeException("Can't edit first attributes. The list is empty.");
      } else {
         return this.setNewAttributeLike(0, this.buildAttribute(0));
      }
   }

   public AttributesNested editLastAttribute() {
      int index = this.attributes.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last attributes. The list is empty.");
      } else {
         return this.setNewAttributeLike(index, this.buildAttribute(index));
      }
   }

   public AttributesNested editMatchingAttribute(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.attributes.size(); ++i) {
         if (predicate.test((NamedResourcesAttributeBuilder)this.attributes.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching attributes. No match found.");
      } else {
         return this.setNewAttributeLike(index, this.buildAttribute(index));
      }
   }

   public String getName() {
      return this.name;
   }

   public NamedResourcesInstanceFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public NamedResourcesInstanceFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NamedResourcesInstanceFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NamedResourcesInstanceFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NamedResourcesInstanceFluent removeFromAdditionalProperties(Map map) {
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

   public NamedResourcesInstanceFluent withAdditionalProperties(Map additionalProperties) {
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
            NamedResourcesInstanceFluent that = (NamedResourcesInstanceFluent)o;
            if (!Objects.equals(this.attributes, that.attributes)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
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
      return Objects.hash(new Object[]{this.attributes, this.name, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.attributes != null && !this.attributes.isEmpty()) {
         sb.append("attributes:");
         sb.append(this.attributes + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class AttributesNested extends NamedResourcesAttributeFluent implements Nested {
      NamedResourcesAttributeBuilder builder;
      int index;

      AttributesNested(int index, NamedResourcesAttribute item) {
         this.index = index;
         this.builder = new NamedResourcesAttributeBuilder(this, item);
      }

      public Object and() {
         return NamedResourcesInstanceFluent.this.setToAttributes(this.index, this.builder.build());
      }

      public Object endAttribute() {
         return this.and();
      }
   }
}
