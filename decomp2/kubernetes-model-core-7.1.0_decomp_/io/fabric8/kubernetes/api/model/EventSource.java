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
@JsonPropertyOrder({"component", "host"})
public class EventSource implements Editable, KubernetesResource {
   @JsonProperty("component")
   private String component;
   @JsonProperty("host")
   private String host;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public EventSource() {
   }

   public EventSource(String component, String host) {
      this.component = component;
      this.host = host;
   }

   @JsonProperty("component")
   public String getComponent() {
      return this.component;
   }

   @JsonProperty("component")
   public void setComponent(String component) {
      this.component = component;
   }

   @JsonProperty("host")
   public String getHost() {
      return this.host;
   }

   @JsonProperty("host")
   public void setHost(String host) {
      this.host = host;
   }

   @JsonIgnore
   public EventSourceBuilder edit() {
      return new EventSourceBuilder(this);
   }

   @JsonIgnore
   public EventSourceBuilder toBuilder() {
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
      String var10000 = this.getComponent();
      return "EventSource(component=" + var10000 + ", host=" + this.getHost() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventSource)) {
         return false;
      } else {
         EventSource other = (EventSource)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$component = this.getComponent();
            Object other$component = other.getComponent();
            if (this$component == null) {
               if (other$component != null) {
                  return false;
               }
            } else if (!this$component.equals(other$component)) {
               return false;
            }

            Object this$host = this.getHost();
            Object other$host = other.getHost();
            if (this$host == null) {
               if (other$host != null) {
                  return false;
               }
            } else if (!this$host.equals(other$host)) {
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
      return other instanceof EventSource;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $component = this.getComponent();
      result = result * 59 + ($component == null ? 43 : $component.hashCode());
      Object $host = this.getHost();
      result = result * 59 + ($host == null ? 43 : $host.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
