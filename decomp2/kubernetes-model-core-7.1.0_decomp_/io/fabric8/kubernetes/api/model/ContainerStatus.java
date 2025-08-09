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
@JsonPropertyOrder({"allocatedResources", "allocatedResourcesStatus", "containerID", "image", "imageID", "lastState", "name", "ready", "resources", "restartCount", "started", "state", "user", "volumeMounts"})
public class ContainerStatus implements Editable, KubernetesResource {
   @JsonProperty("allocatedResources")
   @JsonInclude(Include.NON_EMPTY)
   private Map allocatedResources = new LinkedHashMap();
   @JsonProperty("allocatedResourcesStatus")
   @JsonInclude(Include.NON_EMPTY)
   private List allocatedResourcesStatus = new ArrayList();
   @JsonProperty("containerID")
   private String containerID;
   @JsonProperty("image")
   private String image;
   @JsonProperty("imageID")
   private String imageID;
   @JsonProperty("lastState")
   private ContainerState lastState;
   @JsonProperty("name")
   private String name;
   @JsonProperty("ready")
   private Boolean ready;
   @JsonProperty("resources")
   private ResourceRequirements resources;
   @JsonProperty("restartCount")
   private Integer restartCount;
   @JsonProperty("started")
   private Boolean started;
   @JsonProperty("state")
   private ContainerState state;
   @JsonProperty("user")
   private ContainerUser user;
   @JsonProperty("volumeMounts")
   @JsonInclude(Include.NON_EMPTY)
   private List volumeMounts = new ArrayList();
   @JsonIgnore
   private Map additionalProperties = new LinkedHashMap();

   public ContainerStatus() {
   }

   public ContainerStatus(Map allocatedResources, List allocatedResourcesStatus, String containerID, String image, String imageID, ContainerState lastState, String name, Boolean ready, ResourceRequirements resources, Integer restartCount, Boolean started, ContainerState state, ContainerUser user, List volumeMounts) {
      this.allocatedResources = allocatedResources;
      this.allocatedResourcesStatus = allocatedResourcesStatus;
      this.containerID = containerID;
      this.image = image;
      this.imageID = imageID;
      this.lastState = lastState;
      this.name = name;
      this.ready = ready;
      this.resources = resources;
      this.restartCount = restartCount;
      this.started = started;
      this.state = state;
      this.user = user;
      this.volumeMounts = volumeMounts;
   }

   @JsonProperty("allocatedResources")
   @JsonInclude(Include.NON_EMPTY)
   public Map getAllocatedResources() {
      return this.allocatedResources;
   }

   @JsonProperty("allocatedResources")
   public void setAllocatedResources(Map allocatedResources) {
      this.allocatedResources = allocatedResources;
   }

   @JsonProperty("allocatedResourcesStatus")
   @JsonInclude(Include.NON_EMPTY)
   public List getAllocatedResourcesStatus() {
      return this.allocatedResourcesStatus;
   }

   @JsonProperty("allocatedResourcesStatus")
   public void setAllocatedResourcesStatus(List allocatedResourcesStatus) {
      this.allocatedResourcesStatus = allocatedResourcesStatus;
   }

   @JsonProperty("containerID")
   public String getContainerID() {
      return this.containerID;
   }

   @JsonProperty("containerID")
   public void setContainerID(String containerID) {
      this.containerID = containerID;
   }

   @JsonProperty("image")
   public String getImage() {
      return this.image;
   }

   @JsonProperty("image")
   public void setImage(String image) {
      this.image = image;
   }

   @JsonProperty("imageID")
   public String getImageID() {
      return this.imageID;
   }

   @JsonProperty("imageID")
   public void setImageID(String imageID) {
      this.imageID = imageID;
   }

   @JsonProperty("lastState")
   public ContainerState getLastState() {
      return this.lastState;
   }

   @JsonProperty("lastState")
   public void setLastState(ContainerState lastState) {
      this.lastState = lastState;
   }

   @JsonProperty("name")
   public String getName() {
      return this.name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("ready")
   public Boolean getReady() {
      return this.ready;
   }

   @JsonProperty("ready")
   public void setReady(Boolean ready) {
      this.ready = ready;
   }

   @JsonProperty("resources")
   public ResourceRequirements getResources() {
      return this.resources;
   }

   @JsonProperty("resources")
   public void setResources(ResourceRequirements resources) {
      this.resources = resources;
   }

   @JsonProperty("restartCount")
   public Integer getRestartCount() {
      return this.restartCount;
   }

   @JsonProperty("restartCount")
   public void setRestartCount(Integer restartCount) {
      this.restartCount = restartCount;
   }

   @JsonProperty("started")
   public Boolean getStarted() {
      return this.started;
   }

   @JsonProperty("started")
   public void setStarted(Boolean started) {
      this.started = started;
   }

   @JsonProperty("state")
   public ContainerState getState() {
      return this.state;
   }

   @JsonProperty("state")
   public void setState(ContainerState state) {
      this.state = state;
   }

   @JsonProperty("user")
   public ContainerUser getUser() {
      return this.user;
   }

   @JsonProperty("user")
   public void setUser(ContainerUser user) {
      this.user = user;
   }

   @JsonProperty("volumeMounts")
   @JsonInclude(Include.NON_EMPTY)
   public List getVolumeMounts() {
      return this.volumeMounts;
   }

   @JsonProperty("volumeMounts")
   public void setVolumeMounts(List volumeMounts) {
      this.volumeMounts = volumeMounts;
   }

   @JsonIgnore
   public ContainerStatusBuilder edit() {
      return new ContainerStatusBuilder(this);
   }

   @JsonIgnore
   public ContainerStatusBuilder toBuilder() {
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
      Map var10000 = this.getAllocatedResources();
      return "ContainerStatus(allocatedResources=" + var10000 + ", allocatedResourcesStatus=" + this.getAllocatedResourcesStatus() + ", containerID=" + this.getContainerID() + ", image=" + this.getImage() + ", imageID=" + this.getImageID() + ", lastState=" + this.getLastState() + ", name=" + this.getName() + ", ready=" + this.getReady() + ", resources=" + this.getResources() + ", restartCount=" + this.getRestartCount() + ", started=" + this.getStarted() + ", state=" + this.getState() + ", user=" + this.getUser() + ", volumeMounts=" + this.getVolumeMounts() + ", additionalProperties=" + this.getAdditionalProperties() + ")";
   }

   @Generated
   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerStatus)) {
         return false;
      } else {
         ContainerStatus other = (ContainerStatus)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ready = this.getReady();
            Object other$ready = other.getReady();
            if (this$ready == null) {
               if (other$ready != null) {
                  return false;
               }
            } else if (!this$ready.equals(other$ready)) {
               return false;
            }

            Object this$restartCount = this.getRestartCount();
            Object other$restartCount = other.getRestartCount();
            if (this$restartCount == null) {
               if (other$restartCount != null) {
                  return false;
               }
            } else if (!this$restartCount.equals(other$restartCount)) {
               return false;
            }

            Object this$started = this.getStarted();
            Object other$started = other.getStarted();
            if (this$started == null) {
               if (other$started != null) {
                  return false;
               }
            } else if (!this$started.equals(other$started)) {
               return false;
            }

            Object this$allocatedResources = this.getAllocatedResources();
            Object other$allocatedResources = other.getAllocatedResources();
            if (this$allocatedResources == null) {
               if (other$allocatedResources != null) {
                  return false;
               }
            } else if (!this$allocatedResources.equals(other$allocatedResources)) {
               return false;
            }

            Object this$allocatedResourcesStatus = this.getAllocatedResourcesStatus();
            Object other$allocatedResourcesStatus = other.getAllocatedResourcesStatus();
            if (this$allocatedResourcesStatus == null) {
               if (other$allocatedResourcesStatus != null) {
                  return false;
               }
            } else if (!this$allocatedResourcesStatus.equals(other$allocatedResourcesStatus)) {
               return false;
            }

            Object this$containerID = this.getContainerID();
            Object other$containerID = other.getContainerID();
            if (this$containerID == null) {
               if (other$containerID != null) {
                  return false;
               }
            } else if (!this$containerID.equals(other$containerID)) {
               return false;
            }

            Object this$image = this.getImage();
            Object other$image = other.getImage();
            if (this$image == null) {
               if (other$image != null) {
                  return false;
               }
            } else if (!this$image.equals(other$image)) {
               return false;
            }

            Object this$imageID = this.getImageID();
            Object other$imageID = other.getImageID();
            if (this$imageID == null) {
               if (other$imageID != null) {
                  return false;
               }
            } else if (!this$imageID.equals(other$imageID)) {
               return false;
            }

            Object this$lastState = this.getLastState();
            Object other$lastState = other.getLastState();
            if (this$lastState == null) {
               if (other$lastState != null) {
                  return false;
               }
            } else if (!this$lastState.equals(other$lastState)) {
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

            Object this$resources = this.getResources();
            Object other$resources = other.getResources();
            if (this$resources == null) {
               if (other$resources != null) {
                  return false;
               }
            } else if (!this$resources.equals(other$resources)) {
               return false;
            }

            Object this$state = this.getState();
            Object other$state = other.getState();
            if (this$state == null) {
               if (other$state != null) {
                  return false;
               }
            } else if (!this$state.equals(other$state)) {
               return false;
            }

            Object this$user = this.getUser();
            Object other$user = other.getUser();
            if (this$user == null) {
               if (other$user != null) {
                  return false;
               }
            } else if (!this$user.equals(other$user)) {
               return false;
            }

            Object this$volumeMounts = this.getVolumeMounts();
            Object other$volumeMounts = other.getVolumeMounts();
            if (this$volumeMounts == null) {
               if (other$volumeMounts != null) {
                  return false;
               }
            } else if (!this$volumeMounts.equals(other$volumeMounts)) {
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
      return other instanceof ContainerStatus;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ready = this.getReady();
      result = result * 59 + ($ready == null ? 43 : $ready.hashCode());
      Object $restartCount = this.getRestartCount();
      result = result * 59 + ($restartCount == null ? 43 : $restartCount.hashCode());
      Object $started = this.getStarted();
      result = result * 59 + ($started == null ? 43 : $started.hashCode());
      Object $allocatedResources = this.getAllocatedResources();
      result = result * 59 + ($allocatedResources == null ? 43 : $allocatedResources.hashCode());
      Object $allocatedResourcesStatus = this.getAllocatedResourcesStatus();
      result = result * 59 + ($allocatedResourcesStatus == null ? 43 : $allocatedResourcesStatus.hashCode());
      Object $containerID = this.getContainerID();
      result = result * 59 + ($containerID == null ? 43 : $containerID.hashCode());
      Object $image = this.getImage();
      result = result * 59 + ($image == null ? 43 : $image.hashCode());
      Object $imageID = this.getImageID();
      result = result * 59 + ($imageID == null ? 43 : $imageID.hashCode());
      Object $lastState = this.getLastState();
      result = result * 59 + ($lastState == null ? 43 : $lastState.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $resources = this.getResources();
      result = result * 59 + ($resources == null ? 43 : $resources.hashCode());
      Object $state = this.getState();
      result = result * 59 + ($state == null ? 43 : $state.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $volumeMounts = this.getVolumeMounts();
      result = result * 59 + ($volumeMounts == null ? 43 : $volumeMounts.hashCode());
      Object $additionalProperties = this.getAdditionalProperties();
      result = result * 59 + ($additionalProperties == null ? 43 : $additionalProperties.hashCode());
      return result;
   }
}
