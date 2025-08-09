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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"nameservers", "options", "searches"})
public class PodDNSConfig implements Editable, KubernetesResource {
   @JsonProperty("nameservers")
   @JsonInclude(Include.NON_EMPTY)
   private List nameservers = new ArrayList();
   @JsonProperty("options")
   @JsonInclude(Include.NON_EMPTY)
   private List options = new ArrayList();
   @JsonProperty("searches")
   @JsonInclude(Include.NON_EMPTY)
   private List searches = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public PodDNSConfig() {
   }

   public PodDNSConfig(List nameservers, List options, List searches) {
      this.nameservers = nameservers;
      this.options = options;
      this.searches = searches;
   }

   @JsonProperty("nameservers")
   @JsonInclude(Include.NON_EMPTY)
   public List getNameservers() {
      return this.nameservers;
   }

   @JsonProperty("nameservers")
   public void setNameservers(List nameservers) {
      this.nameservers = nameservers;
   }

   @JsonProperty("options")
   @JsonInclude(Include.NON_EMPTY)
   public List getOptions() {
      return this.options;
   }

   @JsonProperty("options")
   public void setOptions(List options) {
      this.options = options;
   }

   @JsonProperty("searches")
   @JsonInclude(Include.NON_EMPTY)
   public List getSearches() {
      return this.searches;
   }

   @JsonProperty("searches")
   public void setSearches(List searches) {
      this.searches = searches;
   }

   @JsonIgnore
   public PodDNSConfigBuilder edit() {
      return new PodDNSConfigBuilder(this);
   }

   @JsonIgnore
   public PodDNSConfigBuilder toBuilder() {
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
      List var10000 = this.getNameservers();
      return "PodDNSConfig(nameservers=" + var10000 + ", options=" + this.getOptions() + ", searches=" + this.getSearches() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PodDNSConfig)) {
         return false;
      } else {
         PodDNSConfig other = (PodDNSConfig)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$nameservers = this.getNameservers();
            Object other$nameservers = other.getNameservers();
            if (this$nameservers == null) {
               if (other$nameservers != null) {
                  return false;
               }
            } else if (!this$nameservers.equals(other$nameservers)) {
               return false;
            }

            Object this$options = this.getOptions();
            Object other$options = other.getOptions();
            if (this$options == null) {
               if (other$options != null) {
                  return false;
               }
            } else if (!this$options.equals(other$options)) {
               return false;
            }

            Object this$searches = this.getSearches();
            Object other$searches = other.getSearches();
            if (this$searches == null) {
               if (other$searches != null) {
                  return false;
               }
            } else if (!this$searches.equals(other$searches)) {
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
      return other instanceof PodDNSConfig;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $nameservers = this.getNameservers();
      result = result * 59 + ($nameservers == null ? 43 : $nameservers.hashCode());
      Object $options = this.getOptions();
      result = result * 59 + ($options == null ? 43 : $options.hashCode());
      Object $searches = this.getSearches();
      result = result * 59 + ($searches == null ? 43 : $searches.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
