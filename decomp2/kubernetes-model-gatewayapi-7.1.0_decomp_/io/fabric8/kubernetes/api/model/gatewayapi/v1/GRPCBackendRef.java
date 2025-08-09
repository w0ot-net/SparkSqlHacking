package io.fabric8.kubernetes.api.model.gatewayapi.v1;

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
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"kind", "filters", "group", "name", "namespace", "port", "weight"})
public class GRPCBackendRef implements Editable, KubernetesResource {
   @JsonProperty("filters")
   @JsonInclude(Include.NON_EMPTY)
   private List filters = new ArrayList();
   @JsonProperty("group")
   private String group;
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("name")
   private String name;
   @JsonProperty("namespace")
   private String namespace;
   @JsonProperty("port")
   private Integer port;
   @JsonProperty("weight")
   private Integer weight;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public GRPCBackendRef() {
   }

   public GRPCBackendRef(List filters, String group, String kind, String name, String namespace, Integer port, Integer weight) {
      this.filters = filters;
      this.group = group;
      this.kind = kind;
      this.name = name;
      this.namespace = namespace;
      this.port = port;
      this.weight = weight;
   }

   @JsonProperty("filters")
   @JsonInclude(Include.NON_EMPTY)
   public List getFilters() {
      return this.filters;
   }

   @JsonProperty("filters")
   public void setFilters(List filters) {
      this.filters = filters;
   }

   @JsonProperty("group")
   public String getGroup() {
      return this.group;
   }

   @JsonProperty("group")
   public void setGroup(String group) {
      this.group = group;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("namespace")
   public String getNamespace() {
      return this.namespace;
   }

   @JsonProperty("namespace")
   public void setNamespace(String namespace) {
      this.namespace = namespace;
   }

   @JsonProperty("port")
   public Integer getPort() {
      return this.port;
   }

   @JsonProperty("port")
   public void setPort(Integer port) {
      this.port = port;
   }

   @JsonProperty("weight")
   public Integer getWeight() {
      return this.weight;
   }

   @JsonProperty("weight")
   public void setWeight(Integer weight) {
      this.weight = weight;
   }

   @JsonIgnore
   public GRPCBackendRefBuilder edit() {
      return new GRPCBackendRefBuilder(this);
   }

   @JsonIgnore
   public GRPCBackendRefBuilder toBuilder() {
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
      List var10000 = this.getFilters();
      return "GRPCBackendRef(filters=" + var10000 + ", group=" + this.getGroup() + ", kind=" + this.getKind() + ", name=" + this.getName() + ", namespace=" + this.getNamespace() + ", port=" + this.getPort() + ", weight=" + this.getWeight() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GRPCBackendRef)) {
         return false;
      } else {
         GRPCBackendRef other = (GRPCBackendRef)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$port = this.getPort();
            Object other$port = other.getPort();
            if (this$port == null) {
               if (other$port != null) {
                  return false;
               }
            } else if (!this$port.equals(other$port)) {
               return false;
            }

            Object this$weight = this.getWeight();
            Object other$weight = other.getWeight();
            if (this$weight == null) {
               if (other$weight != null) {
                  return false;
               }
            } else if (!this$weight.equals(other$weight)) {
               return false;
            }

            Object this$filters = this.getFilters();
            Object other$filters = other.getFilters();
            if (this$filters == null) {
               if (other$filters != null) {
                  return false;
               }
            } else if (!this$filters.equals(other$filters)) {
               return false;
            }

            Object this$group = this.getGroup();
            Object other$group = other.getGroup();
            if (this$group == null) {
               if (other$group != null) {
                  return false;
               }
            } else if (!this$group.equals(other$group)) {
               return false;
            }

            Object this$kind = this.getKind();
            Object other$kind = other.getKind();
            if (this$kind == null) {
               if (other$kind != null) {
                  return false;
               }
            } else if (!this$kind.equals(other$kind)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$namespace = this.getNamespace();
            Object other$namespace = other.getNamespace();
            if (this$namespace == null) {
               if (other$namespace != null) {
                  return false;
               }
            } else if (!this$namespace.equals(other$namespace)) {
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
      return other instanceof GRPCBackendRef;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $port = this.getPort();
      result = result * 59 + ($port == null ? 43 : $port.hashCode());
      Object $weight = this.getWeight();
      result = result * 59 + ($weight == null ? 43 : $weight.hashCode());
      Object $filters = this.getFilters();
      result = result * 59 + ($filters == null ? 43 : $filters.hashCode());
      Object $group = this.getGroup();
      result = result * 59 + ($group == null ? 43 : $group.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $namespace = this.getNamespace();
      result = result * 59 + ($namespace == null ? 43 : $namespace.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
