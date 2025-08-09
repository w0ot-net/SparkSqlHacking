package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ConversionRequestFluent extends BaseFluent {
   private String desiredAPIVersion;
   private List objects = new ArrayList();
   private String uid;
   private Map additionalProperties;

   public ConversionRequestFluent() {
   }

   public ConversionRequestFluent(ConversionRequest instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ConversionRequest instance) {
      instance = instance != null ? instance : new ConversionRequest();
      if (instance != null) {
         this.withDesiredAPIVersion(instance.getDesiredAPIVersion());
         this.withObjects(instance.getObjects());
         this.withUid(instance.getUid());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getDesiredAPIVersion() {
      return this.desiredAPIVersion;
   }

   public ConversionRequestFluent withDesiredAPIVersion(String desiredAPIVersion) {
      this.desiredAPIVersion = desiredAPIVersion;
      return this;
   }

   public boolean hasDesiredAPIVersion() {
      return this.desiredAPIVersion != null;
   }

   public ConversionRequestFluent addToObjects(int index, Object item) {
      if (this.objects == null) {
         this.objects = new ArrayList();
      }

      this.objects.add(index, item);
      return this;
   }

   public ConversionRequestFluent setToObjects(int index, Object item) {
      if (this.objects == null) {
         this.objects = new ArrayList();
      }

      this.objects.set(index, item);
      return this;
   }

   public ConversionRequestFluent addToObjects(Object... items) {
      if (this.objects == null) {
         this.objects = new ArrayList();
      }

      for(Object item : items) {
         this.objects.add(item);
      }

      return this;
   }

   public ConversionRequestFluent addAllToObjects(Collection items) {
      if (this.objects == null) {
         this.objects = new ArrayList();
      }

      for(Object item : items) {
         this.objects.add(item);
      }

      return this;
   }

   public ConversionRequestFluent removeFromObjects(Object... items) {
      if (this.objects == null) {
         return this;
      } else {
         for(Object item : items) {
            this.objects.remove(item);
         }

         return this;
      }
   }

   public ConversionRequestFluent removeAllFromObjects(Collection items) {
      if (this.objects == null) {
         return this;
      } else {
         for(Object item : items) {
            this.objects.remove(item);
         }

         return this;
      }
   }

   public List getObjects() {
      return this.objects;
   }

   public Object getObject(int index) {
      return this.objects.get(index);
   }

   public Object getFirstObject() {
      return this.objects.get(0);
   }

   public Object getLastObject() {
      return this.objects.get(this.objects.size() - 1);
   }

   public Object getMatchingObject(Predicate predicate) {
      for(Object item : this.objects) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingObject(Predicate predicate) {
      for(Object item : this.objects) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ConversionRequestFluent withObjects(List objects) {
      if (objects != null) {
         this.objects = new ArrayList();

         for(Object item : objects) {
            this.addToObjects(item);
         }
      } else {
         this.objects = null;
      }

      return this;
   }

   public ConversionRequestFluent withObjects(Object... objects) {
      if (this.objects != null) {
         this.objects.clear();
         this._visitables.remove("objects");
      }

      if (objects != null) {
         for(Object item : objects) {
            this.addToObjects(item);
         }
      }

      return this;
   }

   public boolean hasObjects() {
      return this.objects != null && !this.objects.isEmpty();
   }

   public String getUid() {
      return this.uid;
   }

   public ConversionRequestFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public ConversionRequestFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ConversionRequestFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ConversionRequestFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ConversionRequestFluent removeFromAdditionalProperties(Map map) {
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

   public ConversionRequestFluent withAdditionalProperties(Map additionalProperties) {
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
            ConversionRequestFluent that = (ConversionRequestFluent)o;
            if (!Objects.equals(this.desiredAPIVersion, that.desiredAPIVersion)) {
               return false;
            } else if (!Objects.equals(this.objects, that.objects)) {
               return false;
            } else if (!Objects.equals(this.uid, that.uid)) {
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
      return Objects.hash(new Object[]{this.desiredAPIVersion, this.objects, this.uid, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.desiredAPIVersion != null) {
         sb.append("desiredAPIVersion:");
         sb.append(this.desiredAPIVersion + ",");
      }

      if (this.objects != null && !this.objects.isEmpty()) {
         sb.append("objects:");
         sb.append(this.objects + ",");
      }

      if (this.uid != null) {
         sb.append("uid:");
         sb.append(this.uid + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }
}
