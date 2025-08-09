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
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "dryRun", "gracePeriodSeconds", "ignoreStoreReadErrorWithClusterBreakingPotential", "orphanDependents", "preconditions", "propagationPolicy"})
@Version("v1")
@Group("")
public class DeleteOptions implements Editable, KubernetesResource {
   @JsonProperty("apiVersion")
   private String apiVersion = "v1";
   @JsonProperty("dryRun")
   @JsonInclude(Include.NON_EMPTY)
   private List dryRun = new ArrayList();
   @JsonProperty("gracePeriodSeconds")
   private Long gracePeriodSeconds;
   @JsonProperty("ignoreStoreReadErrorWithClusterBreakingPotential")
   private Boolean ignoreStoreReadErrorWithClusterBreakingPotential;
   @JsonProperty("kind")
   private String kind = "DeleteOptions";
   @JsonProperty("orphanDependents")
   private Boolean orphanDependents;
   @JsonProperty("preconditions")
   private Preconditions preconditions;
   @JsonProperty("propagationPolicy")
   private String propagationPolicy;
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public DeleteOptions() {
   }

   public DeleteOptions(String apiVersion, List dryRun, Long gracePeriodSeconds, Boolean ignoreStoreReadErrorWithClusterBreakingPotential, String kind, Boolean orphanDependents, Preconditions preconditions, String propagationPolicy) {
      this.apiVersion = apiVersion;
      this.dryRun = dryRun;
      this.gracePeriodSeconds = gracePeriodSeconds;
      this.ignoreStoreReadErrorWithClusterBreakingPotential = ignoreStoreReadErrorWithClusterBreakingPotential;
      this.kind = kind;
      this.orphanDependents = orphanDependents;
      this.preconditions = preconditions;
      this.propagationPolicy = propagationPolicy;
   }

   @JsonProperty("apiVersion")
   public String getApiVersion() {
      return this.apiVersion;
   }

   @JsonProperty("apiVersion")
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("dryRun")
   @JsonInclude(Include.NON_EMPTY)
   public List getDryRun() {
      return this.dryRun;
   }

   @JsonProperty("dryRun")
   public void setDryRun(List dryRun) {
      this.dryRun = dryRun;
   }

   @JsonProperty("gracePeriodSeconds")
   public Long getGracePeriodSeconds() {
      return this.gracePeriodSeconds;
   }

   @JsonProperty("gracePeriodSeconds")
   public void setGracePeriodSeconds(Long gracePeriodSeconds) {
      this.gracePeriodSeconds = gracePeriodSeconds;
   }

   @JsonProperty("ignoreStoreReadErrorWithClusterBreakingPotential")
   public Boolean getIgnoreStoreReadErrorWithClusterBreakingPotential() {
      return this.ignoreStoreReadErrorWithClusterBreakingPotential;
   }

   @JsonProperty("ignoreStoreReadErrorWithClusterBreakingPotential")
   public void setIgnoreStoreReadErrorWithClusterBreakingPotential(Boolean ignoreStoreReadErrorWithClusterBreakingPotential) {
      this.ignoreStoreReadErrorWithClusterBreakingPotential = ignoreStoreReadErrorWithClusterBreakingPotential;
   }

   @JsonProperty("kind")
   public String getKind() {
      return this.kind;
   }

   @JsonProperty("kind")
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("orphanDependents")
   public Boolean getOrphanDependents() {
      return this.orphanDependents;
   }

   @JsonProperty("orphanDependents")
   public void setOrphanDependents(Boolean orphanDependents) {
      this.orphanDependents = orphanDependents;
   }

   @JsonProperty("preconditions")
   public Preconditions getPreconditions() {
      return this.preconditions;
   }

   @JsonProperty("preconditions")
   public void setPreconditions(Preconditions preconditions) {
      this.preconditions = preconditions;
   }

   @JsonProperty("propagationPolicy")
   public String getPropagationPolicy() {
      return this.propagationPolicy;
   }

   @JsonProperty("propagationPolicy")
   public void setPropagationPolicy(String propagationPolicy) {
      this.propagationPolicy = propagationPolicy;
   }

   @JsonIgnore
   public DeleteOptionsBuilder edit() {
      return new DeleteOptionsBuilder(this);
   }

   @JsonIgnore
   public DeleteOptionsBuilder toBuilder() {
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
      String var10000 = this.getApiVersion();
      return "DeleteOptions(apiVersion=" + var10000 + ", dryRun=" + this.getDryRun() + ", gracePeriodSeconds=" + this.getGracePeriodSeconds() + ", ignoreStoreReadErrorWithClusterBreakingPotential=" + this.getIgnoreStoreReadErrorWithClusterBreakingPotential() + ", kind=" + this.getKind() + ", orphanDependents=" + this.getOrphanDependents() + ", preconditions=" + this.getPreconditions() + ", propagationPolicy=" + this.getPropagationPolicy() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeleteOptions)) {
         return false;
      } else {
         DeleteOptions other = (DeleteOptions)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$gracePeriodSeconds = this.getGracePeriodSeconds();
            Object other$gracePeriodSeconds = other.getGracePeriodSeconds();
            if (this$gracePeriodSeconds == null) {
               if (other$gracePeriodSeconds != null) {
                  return false;
               }
            } else if (!this$gracePeriodSeconds.equals(other$gracePeriodSeconds)) {
               return false;
            }

            Object this$ignoreStoreReadErrorWithClusterBreakingPotential = this.getIgnoreStoreReadErrorWithClusterBreakingPotential();
            Object other$ignoreStoreReadErrorWithClusterBreakingPotential = other.getIgnoreStoreReadErrorWithClusterBreakingPotential();
            if (this$ignoreStoreReadErrorWithClusterBreakingPotential == null) {
               if (other$ignoreStoreReadErrorWithClusterBreakingPotential != null) {
                  return false;
               }
            } else if (!this$ignoreStoreReadErrorWithClusterBreakingPotential.equals(other$ignoreStoreReadErrorWithClusterBreakingPotential)) {
               return false;
            }

            Object this$orphanDependents = this.getOrphanDependents();
            Object other$orphanDependents = other.getOrphanDependents();
            if (this$orphanDependents == null) {
               if (other$orphanDependents != null) {
                  return false;
               }
            } else if (!this$orphanDependents.equals(other$orphanDependents)) {
               return false;
            }

            Object this$apiVersion = this.getApiVersion();
            Object other$apiVersion = other.getApiVersion();
            if (this$apiVersion == null) {
               if (other$apiVersion != null) {
                  return false;
               }
            } else if (!this$apiVersion.equals(other$apiVersion)) {
               return false;
            }

            Object this$dryRun = this.getDryRun();
            Object other$dryRun = other.getDryRun();
            if (this$dryRun == null) {
               if (other$dryRun != null) {
                  return false;
               }
            } else if (!this$dryRun.equals(other$dryRun)) {
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

            Object this$preconditions = this.getPreconditions();
            Object other$preconditions = other.getPreconditions();
            if (this$preconditions == null) {
               if (other$preconditions != null) {
                  return false;
               }
            } else if (!this$preconditions.equals(other$preconditions)) {
               return false;
            }

            Object this$propagationPolicy = this.getPropagationPolicy();
            Object other$propagationPolicy = other.getPropagationPolicy();
            if (this$propagationPolicy == null) {
               if (other$propagationPolicy != null) {
                  return false;
               }
            } else if (!this$propagationPolicy.equals(other$propagationPolicy)) {
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
      return other instanceof DeleteOptions;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $gracePeriodSeconds = this.getGracePeriodSeconds();
      result = result * 59 + ($gracePeriodSeconds == null ? 43 : $gracePeriodSeconds.hashCode());
      Object $ignoreStoreReadErrorWithClusterBreakingPotential = this.getIgnoreStoreReadErrorWithClusterBreakingPotential();
      result = result * 59 + ($ignoreStoreReadErrorWithClusterBreakingPotential == null ? 43 : $ignoreStoreReadErrorWithClusterBreakingPotential.hashCode());
      Object $orphanDependents = this.getOrphanDependents();
      result = result * 59 + ($orphanDependents == null ? 43 : $orphanDependents.hashCode());
      Object $apiVersion = this.getApiVersion();
      result = result * 59 + ($apiVersion == null ? 43 : $apiVersion.hashCode());
      Object $dryRun = this.getDryRun();
      result = result * 59 + ($dryRun == null ? 43 : $dryRun.hashCode());
      Object $kind = this.getKind();
      result = result * 59 + ($kind == null ? 43 : $kind.hashCode());
      Object $preconditions = this.getPreconditions();
      result = result * 59 + ($preconditions == null ? 43 : $preconditions.hashCode());
      Object $propagationPolicy = this.getPropagationPolicy();
      result = result * 59 + ($propagationPolicy == null ? 43 : $propagationPolicy.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
