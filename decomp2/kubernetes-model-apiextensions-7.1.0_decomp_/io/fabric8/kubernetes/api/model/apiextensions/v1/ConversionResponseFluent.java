package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.model.Status;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class ConversionResponseFluent extends BaseFluent {
   private List convertedObjects = new ArrayList();
   private Status result;
   private String uid;
   private Map additionalProperties;

   public ConversionResponseFluent() {
   }

   public ConversionResponseFluent(ConversionResponse instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ConversionResponse instance) {
      instance = instance != null ? instance : new ConversionResponse();
      if (instance != null) {
         this.withConvertedObjects(instance.getConvertedObjects());
         this.withResult(instance.getResult());
         this.withUid(instance.getUid());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ConversionResponseFluent addToConvertedObjects(int index, Object item) {
      if (this.convertedObjects == null) {
         this.convertedObjects = new ArrayList();
      }

      this.convertedObjects.add(index, item);
      return this;
   }

   public ConversionResponseFluent setToConvertedObjects(int index, Object item) {
      if (this.convertedObjects == null) {
         this.convertedObjects = new ArrayList();
      }

      this.convertedObjects.set(index, item);
      return this;
   }

   public ConversionResponseFluent addToConvertedObjects(Object... items) {
      if (this.convertedObjects == null) {
         this.convertedObjects = new ArrayList();
      }

      for(Object item : items) {
         this.convertedObjects.add(item);
      }

      return this;
   }

   public ConversionResponseFluent addAllToConvertedObjects(Collection items) {
      if (this.convertedObjects == null) {
         this.convertedObjects = new ArrayList();
      }

      for(Object item : items) {
         this.convertedObjects.add(item);
      }

      return this;
   }

   public ConversionResponseFluent removeFromConvertedObjects(Object... items) {
      if (this.convertedObjects == null) {
         return this;
      } else {
         for(Object item : items) {
            this.convertedObjects.remove(item);
         }

         return this;
      }
   }

   public ConversionResponseFluent removeAllFromConvertedObjects(Collection items) {
      if (this.convertedObjects == null) {
         return this;
      } else {
         for(Object item : items) {
            this.convertedObjects.remove(item);
         }

         return this;
      }
   }

   public List getConvertedObjects() {
      return this.convertedObjects;
   }

   public Object getConvertedObject(int index) {
      return this.convertedObjects.get(index);
   }

   public Object getFirstConvertedObject() {
      return this.convertedObjects.get(0);
   }

   public Object getLastConvertedObject() {
      return this.convertedObjects.get(this.convertedObjects.size() - 1);
   }

   public Object getMatchingConvertedObject(Predicate predicate) {
      for(Object item : this.convertedObjects) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingConvertedObject(Predicate predicate) {
      for(Object item : this.convertedObjects) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ConversionResponseFluent withConvertedObjects(List convertedObjects) {
      if (convertedObjects != null) {
         this.convertedObjects = new ArrayList();

         for(Object item : convertedObjects) {
            this.addToConvertedObjects(item);
         }
      } else {
         this.convertedObjects = null;
      }

      return this;
   }

   public ConversionResponseFluent withConvertedObjects(Object... convertedObjects) {
      if (this.convertedObjects != null) {
         this.convertedObjects.clear();
         this._visitables.remove("convertedObjects");
      }

      if (convertedObjects != null) {
         for(Object item : convertedObjects) {
            this.addToConvertedObjects(item);
         }
      }

      return this;
   }

   public boolean hasConvertedObjects() {
      return this.convertedObjects != null && !this.convertedObjects.isEmpty();
   }

   public Status getResult() {
      return this.result;
   }

   public ConversionResponseFluent withResult(Status result) {
      this.result = result;
      return this;
   }

   public boolean hasResult() {
      return this.result != null;
   }

   public String getUid() {
      return this.uid;
   }

   public ConversionResponseFluent withUid(String uid) {
      this.uid = uid;
      return this;
   }

   public boolean hasUid() {
      return this.uid != null;
   }

   public ConversionResponseFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ConversionResponseFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ConversionResponseFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ConversionResponseFluent removeFromAdditionalProperties(Map map) {
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

   public ConversionResponseFluent withAdditionalProperties(Map additionalProperties) {
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
            ConversionResponseFluent that = (ConversionResponseFluent)o;
            if (!Objects.equals(this.convertedObjects, that.convertedObjects)) {
               return false;
            } else if (!Objects.equals(this.result, that.result)) {
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
      return Objects.hash(new Object[]{this.convertedObjects, this.result, this.uid, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.convertedObjects != null && !this.convertedObjects.isEmpty()) {
         sb.append("convertedObjects:");
         sb.append(this.convertedObjects + ",");
      }

      if (this.result != null) {
         sb.append("result:");
         sb.append(this.result + ",");
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
