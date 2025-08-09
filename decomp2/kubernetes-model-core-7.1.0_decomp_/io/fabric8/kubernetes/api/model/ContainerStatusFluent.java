package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class ContainerStatusFluent extends BaseFluent {
   private Map allocatedResources;
   private ArrayList allocatedResourcesStatus = new ArrayList();
   private String containerID;
   private String image;
   private String imageID;
   private ContainerStateBuilder lastState;
   private String name;
   private Boolean ready;
   private ResourceRequirementsBuilder resources;
   private Integer restartCount;
   private Boolean started;
   private ContainerStateBuilder state;
   private ContainerUserBuilder user;
   private ArrayList volumeMounts = new ArrayList();
   private Map additionalProperties;

   public ContainerStatusFluent() {
   }

   public ContainerStatusFluent(ContainerStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerStatus instance) {
      instance = instance != null ? instance : new ContainerStatus();
      if (instance != null) {
         this.withAllocatedResources(instance.getAllocatedResources());
         this.withAllocatedResourcesStatus(instance.getAllocatedResourcesStatus());
         this.withContainerID(instance.getContainerID());
         this.withImage(instance.getImage());
         this.withImageID(instance.getImageID());
         this.withLastState(instance.getLastState());
         this.withName(instance.getName());
         this.withReady(instance.getReady());
         this.withResources(instance.getResources());
         this.withRestartCount(instance.getRestartCount());
         this.withStarted(instance.getStarted());
         this.withState(instance.getState());
         this.withUser(instance.getUser());
         this.withVolumeMounts(instance.getVolumeMounts());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public ContainerStatusFluent addToAllocatedResources(String key, Quantity value) {
      if (this.allocatedResources == null && key != null && value != null) {
         this.allocatedResources = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.allocatedResources.put(key, value);
      }

      return this;
   }

   public ContainerStatusFluent addToAllocatedResources(Map map) {
      if (this.allocatedResources == null && map != null) {
         this.allocatedResources = new LinkedHashMap();
      }

      if (map != null) {
         this.allocatedResources.putAll(map);
      }

      return this;
   }

   public ContainerStatusFluent removeFromAllocatedResources(String key) {
      if (this.allocatedResources == null) {
         return this;
      } else {
         if (key != null && this.allocatedResources != null) {
            this.allocatedResources.remove(key);
         }

         return this;
      }
   }

   public ContainerStatusFluent removeFromAllocatedResources(Map map) {
      if (this.allocatedResources == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.allocatedResources != null) {
                  this.allocatedResources.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAllocatedResources() {
      return this.allocatedResources;
   }

   public ContainerStatusFluent withAllocatedResources(Map allocatedResources) {
      if (allocatedResources == null) {
         this.allocatedResources = null;
      } else {
         this.allocatedResources = new LinkedHashMap(allocatedResources);
      }

      return this;
   }

   public boolean hasAllocatedResources() {
      return this.allocatedResources != null;
   }

   public ContainerStatusFluent addToAllocatedResourcesStatus(int index, ResourceStatus item) {
      if (this.allocatedResourcesStatus == null) {
         this.allocatedResourcesStatus = new ArrayList();
      }

      ResourceStatusBuilder builder = new ResourceStatusBuilder(item);
      if (index >= 0 && index < this.allocatedResourcesStatus.size()) {
         this._visitables.get("allocatedResourcesStatus").add(index, builder);
         this.allocatedResourcesStatus.add(index, builder);
      } else {
         this._visitables.get("allocatedResourcesStatus").add(builder);
         this.allocatedResourcesStatus.add(builder);
      }

      return this;
   }

   public ContainerStatusFluent setToAllocatedResourcesStatus(int index, ResourceStatus item) {
      if (this.allocatedResourcesStatus == null) {
         this.allocatedResourcesStatus = new ArrayList();
      }

      ResourceStatusBuilder builder = new ResourceStatusBuilder(item);
      if (index >= 0 && index < this.allocatedResourcesStatus.size()) {
         this._visitables.get("allocatedResourcesStatus").set(index, builder);
         this.allocatedResourcesStatus.set(index, builder);
      } else {
         this._visitables.get("allocatedResourcesStatus").add(builder);
         this.allocatedResourcesStatus.add(builder);
      }

      return this;
   }

   public ContainerStatusFluent addToAllocatedResourcesStatus(ResourceStatus... items) {
      if (this.allocatedResourcesStatus == null) {
         this.allocatedResourcesStatus = new ArrayList();
      }

      for(ResourceStatus item : items) {
         ResourceStatusBuilder builder = new ResourceStatusBuilder(item);
         this._visitables.get("allocatedResourcesStatus").add(builder);
         this.allocatedResourcesStatus.add(builder);
      }

      return this;
   }

   public ContainerStatusFluent addAllToAllocatedResourcesStatus(Collection items) {
      if (this.allocatedResourcesStatus == null) {
         this.allocatedResourcesStatus = new ArrayList();
      }

      for(ResourceStatus item : items) {
         ResourceStatusBuilder builder = new ResourceStatusBuilder(item);
         this._visitables.get("allocatedResourcesStatus").add(builder);
         this.allocatedResourcesStatus.add(builder);
      }

      return this;
   }

   public ContainerStatusFluent removeFromAllocatedResourcesStatus(ResourceStatus... items) {
      if (this.allocatedResourcesStatus == null) {
         return this;
      } else {
         for(ResourceStatus item : items) {
            ResourceStatusBuilder builder = new ResourceStatusBuilder(item);
            this._visitables.get("allocatedResourcesStatus").remove(builder);
            this.allocatedResourcesStatus.remove(builder);
         }

         return this;
      }
   }

   public ContainerStatusFluent removeAllFromAllocatedResourcesStatus(Collection items) {
      if (this.allocatedResourcesStatus == null) {
         return this;
      } else {
         for(ResourceStatus item : items) {
            ResourceStatusBuilder builder = new ResourceStatusBuilder(item);
            this._visitables.get("allocatedResourcesStatus").remove(builder);
            this.allocatedResourcesStatus.remove(builder);
         }

         return this;
      }
   }

   public ContainerStatusFluent removeMatchingFromAllocatedResourcesStatus(Predicate predicate) {
      if (this.allocatedResourcesStatus == null) {
         return this;
      } else {
         Iterator<ResourceStatusBuilder> each = this.allocatedResourcesStatus.iterator();
         List visitables = this._visitables.get("allocatedResourcesStatus");

         while(each.hasNext()) {
            ResourceStatusBuilder builder = (ResourceStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildAllocatedResourcesStatus() {
      return this.allocatedResourcesStatus != null ? build(this.allocatedResourcesStatus) : null;
   }

   public ResourceStatus buildAllocatedResourcesStatus(int index) {
      return ((ResourceStatusBuilder)this.allocatedResourcesStatus.get(index)).build();
   }

   public ResourceStatus buildFirstAllocatedResourcesStatus() {
      return ((ResourceStatusBuilder)this.allocatedResourcesStatus.get(0)).build();
   }

   public ResourceStatus buildLastAllocatedResourcesStatus() {
      return ((ResourceStatusBuilder)this.allocatedResourcesStatus.get(this.allocatedResourcesStatus.size() - 1)).build();
   }

   public ResourceStatus buildMatchingAllocatedResourcesStatus(Predicate predicate) {
      for(ResourceStatusBuilder item : this.allocatedResourcesStatus) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingAllocatedResourcesStatus(Predicate predicate) {
      for(ResourceStatusBuilder item : this.allocatedResourcesStatus) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ContainerStatusFluent withAllocatedResourcesStatus(List allocatedResourcesStatus) {
      if (this.allocatedResourcesStatus != null) {
         this._visitables.get("allocatedResourcesStatus").clear();
      }

      if (allocatedResourcesStatus != null) {
         this.allocatedResourcesStatus = new ArrayList();

         for(ResourceStatus item : allocatedResourcesStatus) {
            this.addToAllocatedResourcesStatus(item);
         }
      } else {
         this.allocatedResourcesStatus = null;
      }

      return this;
   }

   public ContainerStatusFluent withAllocatedResourcesStatus(ResourceStatus... allocatedResourcesStatus) {
      if (this.allocatedResourcesStatus != null) {
         this.allocatedResourcesStatus.clear();
         this._visitables.remove("allocatedResourcesStatus");
      }

      if (allocatedResourcesStatus != null) {
         for(ResourceStatus item : allocatedResourcesStatus) {
            this.addToAllocatedResourcesStatus(item);
         }
      }

      return this;
   }

   public boolean hasAllocatedResourcesStatus() {
      return this.allocatedResourcesStatus != null && !this.allocatedResourcesStatus.isEmpty();
   }

   public AllocatedResourcesStatusNested addNewAllocatedResourcesStatus() {
      return new AllocatedResourcesStatusNested(-1, (ResourceStatus)null);
   }

   public AllocatedResourcesStatusNested addNewAllocatedResourcesStatusLike(ResourceStatus item) {
      return new AllocatedResourcesStatusNested(-1, item);
   }

   public AllocatedResourcesStatusNested setNewAllocatedResourcesStatusLike(int index, ResourceStatus item) {
      return new AllocatedResourcesStatusNested(index, item);
   }

   public AllocatedResourcesStatusNested editAllocatedResourcesStatus(int index) {
      if (this.allocatedResourcesStatus.size() <= index) {
         throw new RuntimeException("Can't edit allocatedResourcesStatus. Index exceeds size.");
      } else {
         return this.setNewAllocatedResourcesStatusLike(index, this.buildAllocatedResourcesStatus(index));
      }
   }

   public AllocatedResourcesStatusNested editFirstAllocatedResourcesStatus() {
      if (this.allocatedResourcesStatus.size() == 0) {
         throw new RuntimeException("Can't edit first allocatedResourcesStatus. The list is empty.");
      } else {
         return this.setNewAllocatedResourcesStatusLike(0, this.buildAllocatedResourcesStatus(0));
      }
   }

   public AllocatedResourcesStatusNested editLastAllocatedResourcesStatus() {
      int index = this.allocatedResourcesStatus.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last allocatedResourcesStatus. The list is empty.");
      } else {
         return this.setNewAllocatedResourcesStatusLike(index, this.buildAllocatedResourcesStatus(index));
      }
   }

   public AllocatedResourcesStatusNested editMatchingAllocatedResourcesStatus(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.allocatedResourcesStatus.size(); ++i) {
         if (predicate.test((ResourceStatusBuilder)this.allocatedResourcesStatus.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching allocatedResourcesStatus. No match found.");
      } else {
         return this.setNewAllocatedResourcesStatusLike(index, this.buildAllocatedResourcesStatus(index));
      }
   }

   public String getContainerID() {
      return this.containerID;
   }

   public ContainerStatusFluent withContainerID(String containerID) {
      this.containerID = containerID;
      return this;
   }

   public boolean hasContainerID() {
      return this.containerID != null;
   }

   public String getImage() {
      return this.image;
   }

   public ContainerStatusFluent withImage(String image) {
      this.image = image;
      return this;
   }

   public boolean hasImage() {
      return this.image != null;
   }

   public String getImageID() {
      return this.imageID;
   }

   public ContainerStatusFluent withImageID(String imageID) {
      this.imageID = imageID;
      return this;
   }

   public boolean hasImageID() {
      return this.imageID != null;
   }

   public ContainerState buildLastState() {
      return this.lastState != null ? this.lastState.build() : null;
   }

   public ContainerStatusFluent withLastState(ContainerState lastState) {
      this._visitables.remove("lastState");
      if (lastState != null) {
         this.lastState = new ContainerStateBuilder(lastState);
         this._visitables.get("lastState").add(this.lastState);
      } else {
         this.lastState = null;
         this._visitables.get("lastState").remove(this.lastState);
      }

      return this;
   }

   public boolean hasLastState() {
      return this.lastState != null;
   }

   public LastStateNested withNewLastState() {
      return new LastStateNested((ContainerState)null);
   }

   public LastStateNested withNewLastStateLike(ContainerState item) {
      return new LastStateNested(item);
   }

   public LastStateNested editLastState() {
      return this.withNewLastStateLike((ContainerState)Optional.ofNullable(this.buildLastState()).orElse((Object)null));
   }

   public LastStateNested editOrNewLastState() {
      return this.withNewLastStateLike((ContainerState)Optional.ofNullable(this.buildLastState()).orElse((new ContainerStateBuilder()).build()));
   }

   public LastStateNested editOrNewLastStateLike(ContainerState item) {
      return this.withNewLastStateLike((ContainerState)Optional.ofNullable(this.buildLastState()).orElse(item));
   }

   public String getName() {
      return this.name;
   }

   public ContainerStatusFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public Boolean getReady() {
      return this.ready;
   }

   public ContainerStatusFluent withReady(Boolean ready) {
      this.ready = ready;
      return this;
   }

   public boolean hasReady() {
      return this.ready != null;
   }

   public ResourceRequirements buildResources() {
      return this.resources != null ? this.resources.build() : null;
   }

   public ContainerStatusFluent withResources(ResourceRequirements resources) {
      this._visitables.remove("resources");
      if (resources != null) {
         this.resources = new ResourceRequirementsBuilder(resources);
         this._visitables.get("resources").add(this.resources);
      } else {
         this.resources = null;
         this._visitables.get("resources").remove(this.resources);
      }

      return this;
   }

   public boolean hasResources() {
      return this.resources != null;
   }

   public ResourcesNested withNewResources() {
      return new ResourcesNested((ResourceRequirements)null);
   }

   public ResourcesNested withNewResourcesLike(ResourceRequirements item) {
      return new ResourcesNested(item);
   }

   public ResourcesNested editResources() {
      return this.withNewResourcesLike((ResourceRequirements)Optional.ofNullable(this.buildResources()).orElse((Object)null));
   }

   public ResourcesNested editOrNewResources() {
      return this.withNewResourcesLike((ResourceRequirements)Optional.ofNullable(this.buildResources()).orElse((new ResourceRequirementsBuilder()).build()));
   }

   public ResourcesNested editOrNewResourcesLike(ResourceRequirements item) {
      return this.withNewResourcesLike((ResourceRequirements)Optional.ofNullable(this.buildResources()).orElse(item));
   }

   public Integer getRestartCount() {
      return this.restartCount;
   }

   public ContainerStatusFluent withRestartCount(Integer restartCount) {
      this.restartCount = restartCount;
      return this;
   }

   public boolean hasRestartCount() {
      return this.restartCount != null;
   }

   public Boolean getStarted() {
      return this.started;
   }

   public ContainerStatusFluent withStarted(Boolean started) {
      this.started = started;
      return this;
   }

   public boolean hasStarted() {
      return this.started != null;
   }

   public ContainerState buildState() {
      return this.state != null ? this.state.build() : null;
   }

   public ContainerStatusFluent withState(ContainerState state) {
      this._visitables.remove("state");
      if (state != null) {
         this.state = new ContainerStateBuilder(state);
         this._visitables.get("state").add(this.state);
      } else {
         this.state = null;
         this._visitables.get("state").remove(this.state);
      }

      return this;
   }

   public boolean hasState() {
      return this.state != null;
   }

   public StateNested withNewState() {
      return new StateNested((ContainerState)null);
   }

   public StateNested withNewStateLike(ContainerState item) {
      return new StateNested(item);
   }

   public StateNested editState() {
      return this.withNewStateLike((ContainerState)Optional.ofNullable(this.buildState()).orElse((Object)null));
   }

   public StateNested editOrNewState() {
      return this.withNewStateLike((ContainerState)Optional.ofNullable(this.buildState()).orElse((new ContainerStateBuilder()).build()));
   }

   public StateNested editOrNewStateLike(ContainerState item) {
      return this.withNewStateLike((ContainerState)Optional.ofNullable(this.buildState()).orElse(item));
   }

   public ContainerUser buildUser() {
      return this.user != null ? this.user.build() : null;
   }

   public ContainerStatusFluent withUser(ContainerUser user) {
      this._visitables.remove("user");
      if (user != null) {
         this.user = new ContainerUserBuilder(user);
         this._visitables.get("user").add(this.user);
      } else {
         this.user = null;
         this._visitables.get("user").remove(this.user);
      }

      return this;
   }

   public boolean hasUser() {
      return this.user != null;
   }

   public UserNested withNewUser() {
      return new UserNested((ContainerUser)null);
   }

   public UserNested withNewUserLike(ContainerUser item) {
      return new UserNested(item);
   }

   public UserNested editUser() {
      return this.withNewUserLike((ContainerUser)Optional.ofNullable(this.buildUser()).orElse((Object)null));
   }

   public UserNested editOrNewUser() {
      return this.withNewUserLike((ContainerUser)Optional.ofNullable(this.buildUser()).orElse((new ContainerUserBuilder()).build()));
   }

   public UserNested editOrNewUserLike(ContainerUser item) {
      return this.withNewUserLike((ContainerUser)Optional.ofNullable(this.buildUser()).orElse(item));
   }

   public ContainerStatusFluent addToVolumeMounts(int index, VolumeMountStatus item) {
      if (this.volumeMounts == null) {
         this.volumeMounts = new ArrayList();
      }

      VolumeMountStatusBuilder builder = new VolumeMountStatusBuilder(item);
      if (index >= 0 && index < this.volumeMounts.size()) {
         this._visitables.get("volumeMounts").add(index, builder);
         this.volumeMounts.add(index, builder);
      } else {
         this._visitables.get("volumeMounts").add(builder);
         this.volumeMounts.add(builder);
      }

      return this;
   }

   public ContainerStatusFluent setToVolumeMounts(int index, VolumeMountStatus item) {
      if (this.volumeMounts == null) {
         this.volumeMounts = new ArrayList();
      }

      VolumeMountStatusBuilder builder = new VolumeMountStatusBuilder(item);
      if (index >= 0 && index < this.volumeMounts.size()) {
         this._visitables.get("volumeMounts").set(index, builder);
         this.volumeMounts.set(index, builder);
      } else {
         this._visitables.get("volumeMounts").add(builder);
         this.volumeMounts.add(builder);
      }

      return this;
   }

   public ContainerStatusFluent addToVolumeMounts(VolumeMountStatus... items) {
      if (this.volumeMounts == null) {
         this.volumeMounts = new ArrayList();
      }

      for(VolumeMountStatus item : items) {
         VolumeMountStatusBuilder builder = new VolumeMountStatusBuilder(item);
         this._visitables.get("volumeMounts").add(builder);
         this.volumeMounts.add(builder);
      }

      return this;
   }

   public ContainerStatusFluent addAllToVolumeMounts(Collection items) {
      if (this.volumeMounts == null) {
         this.volumeMounts = new ArrayList();
      }

      for(VolumeMountStatus item : items) {
         VolumeMountStatusBuilder builder = new VolumeMountStatusBuilder(item);
         this._visitables.get("volumeMounts").add(builder);
         this.volumeMounts.add(builder);
      }

      return this;
   }

   public ContainerStatusFluent removeFromVolumeMounts(VolumeMountStatus... items) {
      if (this.volumeMounts == null) {
         return this;
      } else {
         for(VolumeMountStatus item : items) {
            VolumeMountStatusBuilder builder = new VolumeMountStatusBuilder(item);
            this._visitables.get("volumeMounts").remove(builder);
            this.volumeMounts.remove(builder);
         }

         return this;
      }
   }

   public ContainerStatusFluent removeAllFromVolumeMounts(Collection items) {
      if (this.volumeMounts == null) {
         return this;
      } else {
         for(VolumeMountStatus item : items) {
            VolumeMountStatusBuilder builder = new VolumeMountStatusBuilder(item);
            this._visitables.get("volumeMounts").remove(builder);
            this.volumeMounts.remove(builder);
         }

         return this;
      }
   }

   public ContainerStatusFluent removeMatchingFromVolumeMounts(Predicate predicate) {
      if (this.volumeMounts == null) {
         return this;
      } else {
         Iterator<VolumeMountStatusBuilder> each = this.volumeMounts.iterator();
         List visitables = this._visitables.get("volumeMounts");

         while(each.hasNext()) {
            VolumeMountStatusBuilder builder = (VolumeMountStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVolumeMounts() {
      return this.volumeMounts != null ? build(this.volumeMounts) : null;
   }

   public VolumeMountStatus buildVolumeMount(int index) {
      return ((VolumeMountStatusBuilder)this.volumeMounts.get(index)).build();
   }

   public VolumeMountStatus buildFirstVolumeMount() {
      return ((VolumeMountStatusBuilder)this.volumeMounts.get(0)).build();
   }

   public VolumeMountStatus buildLastVolumeMount() {
      return ((VolumeMountStatusBuilder)this.volumeMounts.get(this.volumeMounts.size() - 1)).build();
   }

   public VolumeMountStatus buildMatchingVolumeMount(Predicate predicate) {
      for(VolumeMountStatusBuilder item : this.volumeMounts) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVolumeMount(Predicate predicate) {
      for(VolumeMountStatusBuilder item : this.volumeMounts) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public ContainerStatusFluent withVolumeMounts(List volumeMounts) {
      if (this.volumeMounts != null) {
         this._visitables.get("volumeMounts").clear();
      }

      if (volumeMounts != null) {
         this.volumeMounts = new ArrayList();

         for(VolumeMountStatus item : volumeMounts) {
            this.addToVolumeMounts(item);
         }
      } else {
         this.volumeMounts = null;
      }

      return this;
   }

   public ContainerStatusFluent withVolumeMounts(VolumeMountStatus... volumeMounts) {
      if (this.volumeMounts != null) {
         this.volumeMounts.clear();
         this._visitables.remove("volumeMounts");
      }

      if (volumeMounts != null) {
         for(VolumeMountStatus item : volumeMounts) {
            this.addToVolumeMounts(item);
         }
      }

      return this;
   }

   public boolean hasVolumeMounts() {
      return this.volumeMounts != null && !this.volumeMounts.isEmpty();
   }

   public ContainerStatusFluent addNewVolumeMount(String mountPath, String name, Boolean readOnly, String recursiveReadOnly) {
      return this.addToVolumeMounts(new VolumeMountStatus(mountPath, name, readOnly, recursiveReadOnly));
   }

   public VolumeMountsNested addNewVolumeMount() {
      return new VolumeMountsNested(-1, (VolumeMountStatus)null);
   }

   public VolumeMountsNested addNewVolumeMountLike(VolumeMountStatus item) {
      return new VolumeMountsNested(-1, item);
   }

   public VolumeMountsNested setNewVolumeMountLike(int index, VolumeMountStatus item) {
      return new VolumeMountsNested(index, item);
   }

   public VolumeMountsNested editVolumeMount(int index) {
      if (this.volumeMounts.size() <= index) {
         throw new RuntimeException("Can't edit volumeMounts. Index exceeds size.");
      } else {
         return this.setNewVolumeMountLike(index, this.buildVolumeMount(index));
      }
   }

   public VolumeMountsNested editFirstVolumeMount() {
      if (this.volumeMounts.size() == 0) {
         throw new RuntimeException("Can't edit first volumeMounts. The list is empty.");
      } else {
         return this.setNewVolumeMountLike(0, this.buildVolumeMount(0));
      }
   }

   public VolumeMountsNested editLastVolumeMount() {
      int index = this.volumeMounts.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last volumeMounts. The list is empty.");
      } else {
         return this.setNewVolumeMountLike(index, this.buildVolumeMount(index));
      }
   }

   public VolumeMountsNested editMatchingVolumeMount(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.volumeMounts.size(); ++i) {
         if (predicate.test((VolumeMountStatusBuilder)this.volumeMounts.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching volumeMounts. No match found.");
      } else {
         return this.setNewVolumeMountLike(index, this.buildVolumeMount(index));
      }
   }

   public ContainerStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerStatusFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerStatusFluent that = (ContainerStatusFluent)o;
            if (!Objects.equals(this.allocatedResources, that.allocatedResources)) {
               return false;
            } else if (!Objects.equals(this.allocatedResourcesStatus, that.allocatedResourcesStatus)) {
               return false;
            } else if (!Objects.equals(this.containerID, that.containerID)) {
               return false;
            } else if (!Objects.equals(this.image, that.image)) {
               return false;
            } else if (!Objects.equals(this.imageID, that.imageID)) {
               return false;
            } else if (!Objects.equals(this.lastState, that.lastState)) {
               return false;
            } else if (!Objects.equals(this.name, that.name)) {
               return false;
            } else if (!Objects.equals(this.ready, that.ready)) {
               return false;
            } else if (!Objects.equals(this.resources, that.resources)) {
               return false;
            } else if (!Objects.equals(this.restartCount, that.restartCount)) {
               return false;
            } else if (!Objects.equals(this.started, that.started)) {
               return false;
            } else if (!Objects.equals(this.state, that.state)) {
               return false;
            } else if (!Objects.equals(this.user, that.user)) {
               return false;
            } else if (!Objects.equals(this.volumeMounts, that.volumeMounts)) {
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
      return Objects.hash(new Object[]{this.allocatedResources, this.allocatedResourcesStatus, this.containerID, this.image, this.imageID, this.lastState, this.name, this.ready, this.resources, this.restartCount, this.started, this.state, this.user, this.volumeMounts, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.allocatedResources != null && !this.allocatedResources.isEmpty()) {
         sb.append("allocatedResources:");
         sb.append(this.allocatedResources + ",");
      }

      if (this.allocatedResourcesStatus != null && !this.allocatedResourcesStatus.isEmpty()) {
         sb.append("allocatedResourcesStatus:");
         sb.append(this.allocatedResourcesStatus + ",");
      }

      if (this.containerID != null) {
         sb.append("containerID:");
         sb.append(this.containerID + ",");
      }

      if (this.image != null) {
         sb.append("image:");
         sb.append(this.image + ",");
      }

      if (this.imageID != null) {
         sb.append("imageID:");
         sb.append(this.imageID + ",");
      }

      if (this.lastState != null) {
         sb.append("lastState:");
         sb.append(this.lastState + ",");
      }

      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name + ",");
      }

      if (this.ready != null) {
         sb.append("ready:");
         sb.append(this.ready + ",");
      }

      if (this.resources != null) {
         sb.append("resources:");
         sb.append(this.resources + ",");
      }

      if (this.restartCount != null) {
         sb.append("restartCount:");
         sb.append(this.restartCount + ",");
      }

      if (this.started != null) {
         sb.append("started:");
         sb.append(this.started + ",");
      }

      if (this.state != null) {
         sb.append("state:");
         sb.append(this.state + ",");
      }

      if (this.user != null) {
         sb.append("user:");
         sb.append(this.user + ",");
      }

      if (this.volumeMounts != null && !this.volumeMounts.isEmpty()) {
         sb.append("volumeMounts:");
         sb.append(this.volumeMounts + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public ContainerStatusFluent withReady() {
      return this.withReady(true);
   }

   public ContainerStatusFluent withStarted() {
      return this.withStarted(true);
   }

   public class AllocatedResourcesStatusNested extends ResourceStatusFluent implements Nested {
      ResourceStatusBuilder builder;
      int index;

      AllocatedResourcesStatusNested(int index, ResourceStatus item) {
         this.index = index;
         this.builder = new ResourceStatusBuilder(this, item);
      }

      public Object and() {
         return ContainerStatusFluent.this.setToAllocatedResourcesStatus(this.index, this.builder.build());
      }

      public Object endAllocatedResourcesStatus() {
         return this.and();
      }
   }

   public class LastStateNested extends ContainerStateFluent implements Nested {
      ContainerStateBuilder builder;

      LastStateNested(ContainerState item) {
         this.builder = new ContainerStateBuilder(this, item);
      }

      public Object and() {
         return ContainerStatusFluent.this.withLastState(this.builder.build());
      }

      public Object endLastState() {
         return this.and();
      }
   }

   public class ResourcesNested extends ResourceRequirementsFluent implements Nested {
      ResourceRequirementsBuilder builder;

      ResourcesNested(ResourceRequirements item) {
         this.builder = new ResourceRequirementsBuilder(this, item);
      }

      public Object and() {
         return ContainerStatusFluent.this.withResources(this.builder.build());
      }

      public Object endResources() {
         return this.and();
      }
   }

   public class StateNested extends ContainerStateFluent implements Nested {
      ContainerStateBuilder builder;

      StateNested(ContainerState item) {
         this.builder = new ContainerStateBuilder(this, item);
      }

      public Object and() {
         return ContainerStatusFluent.this.withState(this.builder.build());
      }

      public Object endState() {
         return this.and();
      }
   }

   public class UserNested extends ContainerUserFluent implements Nested {
      ContainerUserBuilder builder;

      UserNested(ContainerUser item) {
         this.builder = new ContainerUserBuilder(this, item);
      }

      public Object and() {
         return ContainerStatusFluent.this.withUser(this.builder.build());
      }

      public Object endUser() {
         return this.and();
      }
   }

   public class VolumeMountsNested extends VolumeMountStatusFluent implements Nested {
      VolumeMountStatusBuilder builder;
      int index;

      VolumeMountsNested(int index, VolumeMountStatus item) {
         this.index = index;
         this.builder = new VolumeMountStatusBuilder(this, item);
      }

      public Object and() {
         return ContainerStatusFluent.this.setToVolumeMounts(this.index, this.builder.build());
      }

      public Object endVolumeMount() {
         return this.and();
      }
   }
}
