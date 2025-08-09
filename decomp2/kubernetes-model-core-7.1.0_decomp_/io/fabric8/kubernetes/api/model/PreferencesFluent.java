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

public class PreferencesFluent extends BaseFluent {
   private Boolean colors;
   private ArrayList extensions = new ArrayList();
   private Map additionalProperties;

   public PreferencesFluent() {
   }

   public PreferencesFluent(Preferences instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Preferences instance) {
      instance = instance != null ? instance : new Preferences();
      if (instance != null) {
         this.withColors(instance.getColors());
         this.withExtensions(instance.getExtensions());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getColors() {
      return this.colors;
   }

   public PreferencesFluent withColors(Boolean colors) {
      this.colors = colors;
      return this;
   }

   public boolean hasColors() {
      return this.colors != null;
   }

   public PreferencesFluent addToExtensions(int index, NamedExtension item) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
      if (index >= 0 && index < this.extensions.size()) {
         this._visitables.get("extensions").add(index, builder);
         this.extensions.add(index, builder);
      } else {
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public PreferencesFluent setToExtensions(int index, NamedExtension item) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
      if (index >= 0 && index < this.extensions.size()) {
         this._visitables.get("extensions").set(index, builder);
         this.extensions.set(index, builder);
      } else {
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public PreferencesFluent addToExtensions(NamedExtension... items) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      for(NamedExtension item : items) {
         NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public PreferencesFluent addAllToExtensions(Collection items) {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      for(NamedExtension item : items) {
         NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
         this._visitables.get("extensions").add(builder);
         this.extensions.add(builder);
      }

      return this;
   }

   public PreferencesFluent removeFromExtensions(NamedExtension... items) {
      if (this.extensions == null) {
         return this;
      } else {
         for(NamedExtension item : items) {
            NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
            this._visitables.get("extensions").remove(builder);
            this.extensions.remove(builder);
         }

         return this;
      }
   }

   public PreferencesFluent removeAllFromExtensions(Collection items) {
      if (this.extensions == null) {
         return this;
      } else {
         for(NamedExtension item : items) {
            NamedExtensionBuilder builder = new NamedExtensionBuilder(item);
            this._visitables.get("extensions").remove(builder);
            this.extensions.remove(builder);
         }

         return this;
      }
   }

   public PreferencesFluent removeMatchingFromExtensions(Predicate predicate) {
      if (this.extensions == null) {
         return this;
      } else {
         Iterator<NamedExtensionBuilder> each = this.extensions.iterator();
         List visitables = this._visitables.get("extensions");

         while(each.hasNext()) {
            NamedExtensionBuilder builder = (NamedExtensionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildExtensions() {
      return this.extensions != null ? build(this.extensions) : null;
   }

   public NamedExtension buildExtension(int index) {
      return ((NamedExtensionBuilder)this.extensions.get(index)).build();
   }

   public NamedExtension buildFirstExtension() {
      return ((NamedExtensionBuilder)this.extensions.get(0)).build();
   }

   public NamedExtension buildLastExtension() {
      return ((NamedExtensionBuilder)this.extensions.get(this.extensions.size() - 1)).build();
   }

   public NamedExtension buildMatchingExtension(Predicate predicate) {
      for(NamedExtensionBuilder item : this.extensions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingExtension(Predicate predicate) {
      for(NamedExtensionBuilder item : this.extensions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PreferencesFluent withExtensions(List extensions) {
      if (this.extensions != null) {
         this._visitables.get("extensions").clear();
      }

      if (extensions != null) {
         this.extensions = new ArrayList();

         for(NamedExtension item : extensions) {
            this.addToExtensions(item);
         }
      } else {
         this.extensions = null;
      }

      return this;
   }

   public PreferencesFluent withExtensions(NamedExtension... extensions) {
      if (this.extensions != null) {
         this.extensions.clear();
         this._visitables.remove("extensions");
      }

      if (extensions != null) {
         for(NamedExtension item : extensions) {
            this.addToExtensions(item);
         }
      }

      return this;
   }

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public PreferencesFluent addNewExtension(Object extension, String name) {
      return this.addToExtensions(new NamedExtension(extension, name));
   }

   public ExtensionsNested addNewExtension() {
      return new ExtensionsNested(-1, (NamedExtension)null);
   }

   public ExtensionsNested addNewExtensionLike(NamedExtension item) {
      return new ExtensionsNested(-1, item);
   }

   public ExtensionsNested setNewExtensionLike(int index, NamedExtension item) {
      return new ExtensionsNested(index, item);
   }

   public ExtensionsNested editExtension(int index) {
      if (this.extensions.size() <= index) {
         throw new RuntimeException("Can't edit extensions. Index exceeds size.");
      } else {
         return this.setNewExtensionLike(index, this.buildExtension(index));
      }
   }

   public ExtensionsNested editFirstExtension() {
      if (this.extensions.size() == 0) {
         throw new RuntimeException("Can't edit first extensions. The list is empty.");
      } else {
         return this.setNewExtensionLike(0, this.buildExtension(0));
      }
   }

   public ExtensionsNested editLastExtension() {
      int index = this.extensions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last extensions. The list is empty.");
      } else {
         return this.setNewExtensionLike(index, this.buildExtension(index));
      }
   }

   public ExtensionsNested editMatchingExtension(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.extensions.size(); ++i) {
         if (predicate.test((NamedExtensionBuilder)this.extensions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching extensions. No match found.");
      } else {
         return this.setNewExtensionLike(index, this.buildExtension(index));
      }
   }

   public PreferencesFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PreferencesFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PreferencesFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PreferencesFluent removeFromAdditionalProperties(Map map) {
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

   public PreferencesFluent withAdditionalProperties(Map additionalProperties) {
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
            PreferencesFluent that = (PreferencesFluent)o;
            if (!Objects.equals(this.colors, that.colors)) {
               return false;
            } else if (!Objects.equals(this.extensions, that.extensions)) {
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
      return Objects.hash(new Object[]{this.colors, this.extensions, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.colors != null) {
         sb.append("colors:");
         sb.append(this.colors + ",");
      }

      if (this.extensions != null && !this.extensions.isEmpty()) {
         sb.append("extensions:");
         sb.append(this.extensions + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public PreferencesFluent withColors() {
      return this.withColors(true);
   }

   public class ExtensionsNested extends NamedExtensionFluent implements Nested {
      NamedExtensionBuilder builder;
      int index;

      ExtensionsNested(int index, NamedExtension item) {
         this.index = index;
         this.builder = new NamedExtensionBuilder(this, item);
      }

      public Object and() {
         return PreferencesFluent.this.setToExtensions(this.index, this.builder.build());
      }

      public Object endExtension() {
         return this.and();
      }
   }
}
