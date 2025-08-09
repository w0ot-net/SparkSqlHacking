package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LimitRangeItemFluent extends BaseFluent {
   private Map _default;
   private Map defaultRequest;
   private Map max;
   private Map maxLimitRequestRatio;
   private Map min;
   private String type;
   private Map additionalProperties;

   public LimitRangeItemFluent() {
   }

   public LimitRangeItemFluent(LimitRangeItem instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LimitRangeItem instance) {
      instance = instance != null ? instance : new LimitRangeItem();
      if (instance != null) {
         this.withDefault(instance.getDefault());
         this.withDefaultRequest(instance.getDefaultRequest());
         this.withMax(instance.getMax());
         this.withMaxLimitRequestRatio(instance.getMaxLimitRequestRatio());
         this.withMin(instance.getMin());
         this.withType(instance.getType());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public LimitRangeItemFluent addToDefault(String key, Quantity value) {
      if (this._default == null && key != null && value != null) {
         this._default = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this._default.put(key, value);
      }

      return this;
   }

   public LimitRangeItemFluent addToDefault(Map map) {
      if (this._default == null && map != null) {
         this._default = new LinkedHashMap();
      }

      if (map != null) {
         this._default.putAll(map);
      }

      return this;
   }

   public LimitRangeItemFluent removeFromDefault(String key) {
      if (this._default == null) {
         return this;
      } else {
         if (key != null && this._default != null) {
            this._default.remove(key);
         }

         return this;
      }
   }

   public LimitRangeItemFluent removeFromDefault(Map map) {
      if (this._default == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this._default != null) {
                  this._default.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getDefault() {
      return this._default;
   }

   public LimitRangeItemFluent withDefault(Map _default) {
      if (_default == null) {
         this._default = null;
      } else {
         this._default = new LinkedHashMap(_default);
      }

      return this;
   }

   public boolean hasDefault() {
      return this._default != null;
   }

   public LimitRangeItemFluent addToDefaultRequest(String key, Quantity value) {
      if (this.defaultRequest == null && key != null && value != null) {
         this.defaultRequest = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.defaultRequest.put(key, value);
      }

      return this;
   }

   public LimitRangeItemFluent addToDefaultRequest(Map map) {
      if (this.defaultRequest == null && map != null) {
         this.defaultRequest = new LinkedHashMap();
      }

      if (map != null) {
         this.defaultRequest.putAll(map);
      }

      return this;
   }

   public LimitRangeItemFluent removeFromDefaultRequest(String key) {
      if (this.defaultRequest == null) {
         return this;
      } else {
         if (key != null && this.defaultRequest != null) {
            this.defaultRequest.remove(key);
         }

         return this;
      }
   }

   public LimitRangeItemFluent removeFromDefaultRequest(Map map) {
      if (this.defaultRequest == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.defaultRequest != null) {
                  this.defaultRequest.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getDefaultRequest() {
      return this.defaultRequest;
   }

   public LimitRangeItemFluent withDefaultRequest(Map defaultRequest) {
      if (defaultRequest == null) {
         this.defaultRequest = null;
      } else {
         this.defaultRequest = new LinkedHashMap(defaultRequest);
      }

      return this;
   }

   public boolean hasDefaultRequest() {
      return this.defaultRequest != null;
   }

   public LimitRangeItemFluent addToMax(String key, Quantity value) {
      if (this.max == null && key != null && value != null) {
         this.max = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.max.put(key, value);
      }

      return this;
   }

   public LimitRangeItemFluent addToMax(Map map) {
      if (this.max == null && map != null) {
         this.max = new LinkedHashMap();
      }

      if (map != null) {
         this.max.putAll(map);
      }

      return this;
   }

   public LimitRangeItemFluent removeFromMax(String key) {
      if (this.max == null) {
         return this;
      } else {
         if (key != null && this.max != null) {
            this.max.remove(key);
         }

         return this;
      }
   }

   public LimitRangeItemFluent removeFromMax(Map map) {
      if (this.max == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.max != null) {
                  this.max.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getMax() {
      return this.max;
   }

   public LimitRangeItemFluent withMax(Map max) {
      if (max == null) {
         this.max = null;
      } else {
         this.max = new LinkedHashMap(max);
      }

      return this;
   }

   public boolean hasMax() {
      return this.max != null;
   }

   public LimitRangeItemFluent addToMaxLimitRequestRatio(String key, Quantity value) {
      if (this.maxLimitRequestRatio == null && key != null && value != null) {
         this.maxLimitRequestRatio = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.maxLimitRequestRatio.put(key, value);
      }

      return this;
   }

   public LimitRangeItemFluent addToMaxLimitRequestRatio(Map map) {
      if (this.maxLimitRequestRatio == null && map != null) {
         this.maxLimitRequestRatio = new LinkedHashMap();
      }

      if (map != null) {
         this.maxLimitRequestRatio.putAll(map);
      }

      return this;
   }

   public LimitRangeItemFluent removeFromMaxLimitRequestRatio(String key) {
      if (this.maxLimitRequestRatio == null) {
         return this;
      } else {
         if (key != null && this.maxLimitRequestRatio != null) {
            this.maxLimitRequestRatio.remove(key);
         }

         return this;
      }
   }

   public LimitRangeItemFluent removeFromMaxLimitRequestRatio(Map map) {
      if (this.maxLimitRequestRatio == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.maxLimitRequestRatio != null) {
                  this.maxLimitRequestRatio.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getMaxLimitRequestRatio() {
      return this.maxLimitRequestRatio;
   }

   public LimitRangeItemFluent withMaxLimitRequestRatio(Map maxLimitRequestRatio) {
      if (maxLimitRequestRatio == null) {
         this.maxLimitRequestRatio = null;
      } else {
         this.maxLimitRequestRatio = new LinkedHashMap(maxLimitRequestRatio);
      }

      return this;
   }

   public boolean hasMaxLimitRequestRatio() {
      return this.maxLimitRequestRatio != null;
   }

   public LimitRangeItemFluent addToMin(String key, Quantity value) {
      if (this.min == null && key != null && value != null) {
         this.min = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.min.put(key, value);
      }

      return this;
   }

   public LimitRangeItemFluent addToMin(Map map) {
      if (this.min == null && map != null) {
         this.min = new LinkedHashMap();
      }

      if (map != null) {
         this.min.putAll(map);
      }

      return this;
   }

   public LimitRangeItemFluent removeFromMin(String key) {
      if (this.min == null) {
         return this;
      } else {
         if (key != null && this.min != null) {
            this.min.remove(key);
         }

         return this;
      }
   }

   public LimitRangeItemFluent removeFromMin(Map map) {
      if (this.min == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.min != null) {
                  this.min.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getMin() {
      return this.min;
   }

   public LimitRangeItemFluent withMin(Map min) {
      if (min == null) {
         this.min = null;
      } else {
         this.min = new LinkedHashMap(min);
      }

      return this;
   }

   public boolean hasMin() {
      return this.min != null;
   }

   public String getType() {
      return this.type;
   }

   public LimitRangeItemFluent withType(String type) {
      this.type = type;
      return this;
   }

   public boolean hasType() {
      return this.type != null;
   }

   public LimitRangeItemFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public LimitRangeItemFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public LimitRangeItemFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public LimitRangeItemFluent removeFromAdditionalProperties(Map map) {
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

   public LimitRangeItemFluent withAdditionalProperties(Map additionalProperties) {
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
            LimitRangeItemFluent that = (LimitRangeItemFluent)o;
            if (!Objects.equals(this._default, that._default)) {
               return false;
            } else if (!Objects.equals(this.defaultRequest, that.defaultRequest)) {
               return false;
            } else if (!Objects.equals(this.max, that.max)) {
               return false;
            } else if (!Objects.equals(this.maxLimitRequestRatio, that.maxLimitRequestRatio)) {
               return false;
            } else if (!Objects.equals(this.min, that.min)) {
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
      return Objects.hash(new Object[]{this._default, this.defaultRequest, this.max, this.maxLimitRequestRatio, this.min, this.type, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this._default != null && !this._default.isEmpty()) {
         sb.append("_default:");
         sb.append(this._default + ",");
      }

      if (this.defaultRequest != null && !this.defaultRequest.isEmpty()) {
         sb.append("defaultRequest:");
         sb.append(this.defaultRequest + ",");
      }

      if (this.max != null && !this.max.isEmpty()) {
         sb.append("max:");
         sb.append(this.max + ",");
      }

      if (this.maxLimitRequestRatio != null && !this.maxLimitRequestRatio.isEmpty()) {
         sb.append("maxLimitRequestRatio:");
         sb.append(this.maxLimitRequestRatio + ",");
      }

      if (this.min != null && !this.min.isEmpty()) {
         sb.append("min:");
         sb.append(this.min + ",");
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
}
