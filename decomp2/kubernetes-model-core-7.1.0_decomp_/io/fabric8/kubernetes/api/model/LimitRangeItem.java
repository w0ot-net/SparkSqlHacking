package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.builder.Editable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"default", "defaultRequest", "max", "maxLimitRequestRatio", "min", "type"})
public class LimitRangeItem implements Editable, KubernetesResource {
   @JsonProperty("default")
   @JsonInclude(Include.NON_EMPTY)
   private Map _default = new LinkedHashMap();
   @JsonProperty("defaultRequest")
   @JsonInclude(Include.NON_EMPTY)
   private Map defaultRequest = new LinkedHashMap();
   @JsonProperty("max")
   @JsonInclude(Include.NON_EMPTY)
   private Map max = new LinkedHashMap();
   @JsonProperty("maxLimitRequestRatio")
   @JsonInclude(Include.NON_EMPTY)
   private Map maxLimitRequestRatio = new LinkedHashMap();
   @JsonProperty("min")
   @JsonInclude(Include.NON_EMPTY)
   private Map min = new LinkedHashMap();
   @JsonProperty("type")
   private String type;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public LimitRangeItem() {
   }

   public LimitRangeItem(Map _default, Map defaultRequest, Map max, Map maxLimitRequestRatio, Map min, String type) {
      this._default = _default;
      this.defaultRequest = defaultRequest;
      this.max = max;
      this.maxLimitRequestRatio = maxLimitRequestRatio;
      this.min = min;
      this.type = type;
   }

   @JsonProperty("default")
   @JsonInclude(Include.NON_EMPTY)
   public Map getDefault() {
      return this._default;
   }

   @JsonProperty("default")
   public void setDefault(Map _default) {
      this._default = _default;
   }

   @JsonProperty("defaultRequest")
   @JsonInclude(Include.NON_EMPTY)
   public Map getDefaultRequest() {
      return this.defaultRequest;
   }

   @JsonProperty("defaultRequest")
   public void setDefaultRequest(Map defaultRequest) {
      this.defaultRequest = defaultRequest;
   }

   @JsonProperty("max")
   @JsonInclude(Include.NON_EMPTY)
   public Map getMax() {
      return this.max;
   }

   @JsonProperty("max")
   public void setMax(Map max) {
      this.max = max;
   }

   @JsonProperty("maxLimitRequestRatio")
   @JsonInclude(Include.NON_EMPTY)
   public Map getMaxLimitRequestRatio() {
      return this.maxLimitRequestRatio;
   }

   @JsonProperty("maxLimitRequestRatio")
   public void setMaxLimitRequestRatio(Map maxLimitRequestRatio) {
      this.maxLimitRequestRatio = maxLimitRequestRatio;
   }

   @JsonProperty("min")
   @JsonInclude(Include.NON_EMPTY)
   public Map getMin() {
      return this.min;
   }

   @JsonProperty("min")
   public void setMin(Map min) {
      this.min = min;
   }

   @JsonProperty("type")
   public String getType() {
      return this.type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonIgnore
   public LimitRangeItemBuilder edit() {
      return new LimitRangeItemBuilder(this);
   }

   @JsonIgnore
   public LimitRangeItemBuilder toBuilder() {
      return this.edit();
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   @Generated
   public String toString() {
      Map var10000 = this.getDefault();
      return "LimitRangeItem(_default=" + var10000 + ", defaultRequest=" + this.getDefaultRequest() + ", max=" + this.getMax() + ", maxLimitRequestRatio=" + this.getMaxLimitRequestRatio() + ", min=" + this.getMin() + ", type=" + this.getType() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LimitRangeItem)) {
         return false;
      } else {
         LimitRangeItem other = (LimitRangeItem)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$_default = this.getDefault();
            Object other$_default = other.getDefault();
            if (this$_default == null) {
               if (other$_default != null) {
                  return false;
               }
            } else if (!this$_default.equals(other$_default)) {
               return false;
            }

            Object this$defaultRequest = this.getDefaultRequest();
            Object other$defaultRequest = other.getDefaultRequest();
            if (this$defaultRequest == null) {
               if (other$defaultRequest != null) {
                  return false;
               }
            } else if (!this$defaultRequest.equals(other$defaultRequest)) {
               return false;
            }

            Object this$max = this.getMax();
            Object other$max = other.getMax();
            if (this$max == null) {
               if (other$max != null) {
                  return false;
               }
            } else if (!this$max.equals(other$max)) {
               return false;
            }

            Object this$maxLimitRequestRatio = this.getMaxLimitRequestRatio();
            Object other$maxLimitRequestRatio = other.getMaxLimitRequestRatio();
            if (this$maxLimitRequestRatio == null) {
               if (other$maxLimitRequestRatio != null) {
                  return false;
               }
            } else if (!this$maxLimitRequestRatio.equals(other$maxLimitRequestRatio)) {
               return false;
            }

            Object this$min = this.getMin();
            Object other$min = other.getMin();
            if (this$min == null) {
               if (other$min != null) {
                  return false;
               }
            } else if (!this$min.equals(other$min)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$additionalProperties = this.getAdditionalProperties();
            Object other$additionalProperties = other.getAdditionalProperties();
            if (this$additionalProperties == null) {
               if (other$additionalProperties != null) {
                  return false;
               }
            } else if (!this$additionalProperties.equals(other$additionalProperties)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object other) {
      return other instanceof LimitRangeItem;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $_default = this.getDefault();
      result = result * 59 + ($_default == null ? 43 : $_default.hashCode());
      Object $defaultRequest = this.getDefaultRequest();
      result = result * 59 + ($defaultRequest == null ? 43 : $defaultRequest.hashCode());
      Object $max = this.getMax();
      result = result * 59 + ($max == null ? 43 : $max.hashCode());
      Object $maxLimitRequestRatio = this.getMaxLimitRequestRatio();
      result = result * 59 + ($maxLimitRequestRatio == null ? 43 : $maxLimitRequestRatio.hashCode());
      Object $min = this.getMin();
      result = result * 59 + ($min == null ? 43 : $min.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
