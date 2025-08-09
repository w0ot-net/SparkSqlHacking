package io.fabric8.kubernetes.api.model.discovery.v1;

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
import io.fabric8.kubernetes.api.model.ObjectReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"addresses", "conditions", "deprecatedTopology", "hints", "hostname", "nodeName", "targetRef", "zone"})
public class Endpoint implements Editable, KubernetesResource {
   @JsonProperty("addresses")
   @JsonInclude(Include.NON_EMPTY)
   private List addresses = new ArrayList();
   @JsonProperty("conditions")
   private EndpointConditions conditions;
   @JsonProperty("deprecatedTopology")
   @JsonInclude(Include.NON_EMPTY)
   private Map deprecatedTopology = new LinkedHashMap();
   @JsonProperty("hints")
   private EndpointHints hints;
   @JsonProperty("hostname")
   private String hostname;
   @JsonProperty("nodeName")
   private String nodeName;
   @JsonProperty("targetRef")
   private ObjectReference targetRef;
   @JsonProperty("zone")
   private String zone;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public Endpoint() {
   }

   public Endpoint(List addresses, EndpointConditions conditions, Map deprecatedTopology, EndpointHints hints, String hostname, String nodeName, ObjectReference targetRef, String zone) {
      this.addresses = addresses;
      this.conditions = conditions;
      this.deprecatedTopology = deprecatedTopology;
      this.hints = hints;
      this.hostname = hostname;
      this.nodeName = nodeName;
      this.targetRef = targetRef;
      this.zone = zone;
   }

   @JsonProperty("addresses")
   @JsonInclude(Include.NON_EMPTY)
   public List getAddresses() {
      return this.addresses;
   }

   @JsonProperty("addresses")
   public void setAddresses(List addresses) {
      this.addresses = addresses;
   }

   @JsonProperty("conditions")
   public EndpointConditions getConditions() {
      return this.conditions;
   }

   @JsonProperty("conditions")
   public void setConditions(EndpointConditions conditions) {
      this.conditions = conditions;
   }

   @JsonProperty("deprecatedTopology")
   @JsonInclude(Include.NON_EMPTY)
   public Map getDeprecatedTopology() {
      return this.deprecatedTopology;
   }

   @JsonProperty("deprecatedTopology")
   public void setDeprecatedTopology(Map deprecatedTopology) {
      this.deprecatedTopology = deprecatedTopology;
   }

   @JsonProperty("hints")
   public EndpointHints getHints() {
      return this.hints;
   }

   @JsonProperty("hints")
   public void setHints(EndpointHints hints) {
      this.hints = hints;
   }

   @JsonProperty("hostname")
   public String getHostname() {
      return this.hostname;
   }

   @JsonProperty("hostname")
   public void setHostname(String hostname) {
      this.hostname = hostname;
   }

   @JsonProperty("nodeName")
   public String getNodeName() {
      return this.nodeName;
   }

   @JsonProperty("nodeName")
   public void setNodeName(String nodeName) {
      this.nodeName = nodeName;
   }

   @JsonProperty("targetRef")
   public ObjectReference getTargetRef() {
      return this.targetRef;
   }

   @JsonProperty("targetRef")
   public void setTargetRef(ObjectReference targetRef) {
      this.targetRef = targetRef;
   }

   @JsonProperty("zone")
   public String getZone() {
      return this.zone;
   }

   @JsonProperty("zone")
   public void setZone(String zone) {
      this.zone = zone;
   }

   @JsonIgnore
   public EndpointBuilder edit() {
      return new EndpointBuilder(this);
   }

   @JsonIgnore
   public EndpointBuilder toBuilder() {
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
      List var10000 = this.getAddresses();
      return "Endpoint(addresses=" + var10000 + ", conditions=" + this.getConditions() + ", deprecatedTopology=" + this.getDeprecatedTopology() + ", hints=" + this.getHints() + ", hostname=" + this.getHostname() + ", nodeName=" + this.getNodeName() + ", targetRef=" + this.getTargetRef() + ", zone=" + this.getZone() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Endpoint)) {
         return false;
      } else {
         Endpoint other = (Endpoint)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$addresses = this.getAddresses();
            Object other$addresses = other.getAddresses();
            if (this$addresses == null) {
               if (other$addresses != null) {
                  return false;
               }
            } else if (!this$addresses.equals(other$addresses)) {
               return false;
            }

            Object this$conditions = this.getConditions();
            Object other$conditions = other.getConditions();
            if (this$conditions == null) {
               if (other$conditions != null) {
                  return false;
               }
            } else if (!this$conditions.equals(other$conditions)) {
               return false;
            }

            Object this$deprecatedTopology = this.getDeprecatedTopology();
            Object other$deprecatedTopology = other.getDeprecatedTopology();
            if (this$deprecatedTopology == null) {
               if (other$deprecatedTopology != null) {
                  return false;
               }
            } else if (!this$deprecatedTopology.equals(other$deprecatedTopology)) {
               return false;
            }

            Object this$hints = this.getHints();
            Object other$hints = other.getHints();
            if (this$hints == null) {
               if (other$hints != null) {
                  return false;
               }
            } else if (!this$hints.equals(other$hints)) {
               return false;
            }

            Object this$hostname = this.getHostname();
            Object other$hostname = other.getHostname();
            if (this$hostname == null) {
               if (other$hostname != null) {
                  return false;
               }
            } else if (!this$hostname.equals(other$hostname)) {
               return false;
            }

            Object this$nodeName = this.getNodeName();
            Object other$nodeName = other.getNodeName();
            if (this$nodeName == null) {
               if (other$nodeName != null) {
                  return false;
               }
            } else if (!this$nodeName.equals(other$nodeName)) {
               return false;
            }

            Object this$targetRef = this.getTargetRef();
            Object other$targetRef = other.getTargetRef();
            if (this$targetRef == null) {
               if (other$targetRef != null) {
                  return false;
               }
            } else if (!this$targetRef.equals(other$targetRef)) {
               return false;
            }

            Object this$zone = this.getZone();
            Object other$zone = other.getZone();
            if (this$zone == null) {
               if (other$zone != null) {
                  return false;
               }
            } else if (!this$zone.equals(other$zone)) {
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
      return other instanceof Endpoint;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $addresses = this.getAddresses();
      result = result * 59 + ($addresses == null ? 43 : $addresses.hashCode());
      Object $conditions = this.getConditions();
      result = result * 59 + ($conditions == null ? 43 : $conditions.hashCode());
      Object $deprecatedTopology = this.getDeprecatedTopology();
      result = result * 59 + ($deprecatedTopology == null ? 43 : $deprecatedTopology.hashCode());
      Object $hints = this.getHints();
      result = result * 59 + ($hints == null ? 43 : $hints.hashCode());
      Object $hostname = this.getHostname();
      result = result * 59 + ($hostname == null ? 43 : $hostname.hashCode());
      Object $nodeName = this.getNodeName();
      result = result * 59 + ($nodeName == null ? 43 : $nodeName.hashCode());
      Object $targetRef = this.getTargetRef();
      result = result * 59 + ($targetRef == null ? 43 : $targetRef.hashCode());
      Object $zone = this.getZone();
      result = result * 59 + ($zone == null ? 43 : $zone.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
