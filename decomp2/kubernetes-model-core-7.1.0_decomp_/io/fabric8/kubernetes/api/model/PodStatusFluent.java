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
import java.util.function.Predicate;

public class PodStatusFluent extends BaseFluent {
   private ArrayList conditions = new ArrayList();
   private ArrayList containerStatuses = new ArrayList();
   private ArrayList ephemeralContainerStatuses = new ArrayList();
   private String hostIP;
   private ArrayList hostIPs = new ArrayList();
   private ArrayList initContainerStatuses = new ArrayList();
   private String message;
   private String nominatedNodeName;
   private String phase;
   private String podIP;
   private ArrayList podIPs = new ArrayList();
   private String qosClass;
   private String reason;
   private String resize;
   private ArrayList resourceClaimStatuses = new ArrayList();
   private String startTime;
   private Map additionalProperties;

   public PodStatusFluent() {
   }

   public PodStatusFluent(PodStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(PodStatus instance) {
      instance = instance != null ? instance : new PodStatus();
      if (instance != null) {
         this.withConditions(instance.getConditions());
         this.withContainerStatuses(instance.getContainerStatuses());
         this.withEphemeralContainerStatuses(instance.getEphemeralContainerStatuses());
         this.withHostIP(instance.getHostIP());
         this.withHostIPs(instance.getHostIPs());
         this.withInitContainerStatuses(instance.getInitContainerStatuses());
         this.withMessage(instance.getMessage());
         this.withNominatedNodeName(instance.getNominatedNodeName());
         this.withPhase(instance.getPhase());
         this.withPodIP(instance.getPodIP());
         this.withPodIPs(instance.getPodIPs());
         this.withQosClass(instance.getQosClass());
         this.withReason(instance.getReason());
         this.withResize(instance.getResize());
         this.withResourceClaimStatuses(instance.getResourceClaimStatuses());
         this.withStartTime(instance.getStartTime());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public PodStatusFluent addToConditions(int index, PodCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      PodConditionBuilder builder = new PodConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").add(index, builder);
         this.conditions.add(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PodStatusFluent setToConditions(int index, PodCondition item) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      PodConditionBuilder builder = new PodConditionBuilder(item);
      if (index >= 0 && index < this.conditions.size()) {
         this._visitables.get("conditions").set(index, builder);
         this.conditions.set(index, builder);
      } else {
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PodStatusFluent addToConditions(PodCondition... items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(PodCondition item : items) {
         PodConditionBuilder builder = new PodConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PodStatusFluent addAllToConditions(Collection items) {
      if (this.conditions == null) {
         this.conditions = new ArrayList();
      }

      for(PodCondition item : items) {
         PodConditionBuilder builder = new PodConditionBuilder(item);
         this._visitables.get("conditions").add(builder);
         this.conditions.add(builder);
      }

      return this;
   }

   public PodStatusFluent removeFromConditions(PodCondition... items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(PodCondition item : items) {
            PodConditionBuilder builder = new PodConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeAllFromConditions(Collection items) {
      if (this.conditions == null) {
         return this;
      } else {
         for(PodCondition item : items) {
            PodConditionBuilder builder = new PodConditionBuilder(item);
            this._visitables.get("conditions").remove(builder);
            this.conditions.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeMatchingFromConditions(Predicate predicate) {
      if (this.conditions == null) {
         return this;
      } else {
         Iterator<PodConditionBuilder> each = this.conditions.iterator();
         List visitables = this._visitables.get("conditions");

         while(each.hasNext()) {
            PodConditionBuilder builder = (PodConditionBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildConditions() {
      return this.conditions != null ? build(this.conditions) : null;
   }

   public PodCondition buildCondition(int index) {
      return ((PodConditionBuilder)this.conditions.get(index)).build();
   }

   public PodCondition buildFirstCondition() {
      return ((PodConditionBuilder)this.conditions.get(0)).build();
   }

   public PodCondition buildLastCondition() {
      return ((PodConditionBuilder)this.conditions.get(this.conditions.size() - 1)).build();
   }

   public PodCondition buildMatchingCondition(Predicate predicate) {
      for(PodConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingCondition(Predicate predicate) {
      for(PodConditionBuilder item : this.conditions) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodStatusFluent withConditions(List conditions) {
      if (this.conditions != null) {
         this._visitables.get("conditions").clear();
      }

      if (conditions != null) {
         this.conditions = new ArrayList();

         for(PodCondition item : conditions) {
            this.addToConditions(item);
         }
      } else {
         this.conditions = null;
      }

      return this;
   }

   public PodStatusFluent withConditions(PodCondition... conditions) {
      if (this.conditions != null) {
         this.conditions.clear();
         this._visitables.remove("conditions");
      }

      if (conditions != null) {
         for(PodCondition item : conditions) {
            this.addToConditions(item);
         }
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null && !this.conditions.isEmpty();
   }

   public ConditionsNested addNewCondition() {
      return new ConditionsNested(-1, (PodCondition)null);
   }

   public ConditionsNested addNewConditionLike(PodCondition item) {
      return new ConditionsNested(-1, item);
   }

   public ConditionsNested setNewConditionLike(int index, PodCondition item) {
      return new ConditionsNested(index, item);
   }

   public ConditionsNested editCondition(int index) {
      if (this.conditions.size() <= index) {
         throw new RuntimeException("Can't edit conditions. Index exceeds size.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editFirstCondition() {
      if (this.conditions.size() == 0) {
         throw new RuntimeException("Can't edit first conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(0, this.buildCondition(0));
      }
   }

   public ConditionsNested editLastCondition() {
      int index = this.conditions.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last conditions. The list is empty.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public ConditionsNested editMatchingCondition(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.conditions.size(); ++i) {
         if (predicate.test((PodConditionBuilder)this.conditions.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching conditions. No match found.");
      } else {
         return this.setNewConditionLike(index, this.buildCondition(index));
      }
   }

   public PodStatusFluent addToContainerStatuses(int index, ContainerStatus item) {
      if (this.containerStatuses == null) {
         this.containerStatuses = new ArrayList();
      }

      ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
      if (index >= 0 && index < this.containerStatuses.size()) {
         this._visitables.get("containerStatuses").add(index, builder);
         this.containerStatuses.add(index, builder);
      } else {
         this._visitables.get("containerStatuses").add(builder);
         this.containerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent setToContainerStatuses(int index, ContainerStatus item) {
      if (this.containerStatuses == null) {
         this.containerStatuses = new ArrayList();
      }

      ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
      if (index >= 0 && index < this.containerStatuses.size()) {
         this._visitables.get("containerStatuses").set(index, builder);
         this.containerStatuses.set(index, builder);
      } else {
         this._visitables.get("containerStatuses").add(builder);
         this.containerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent addToContainerStatuses(ContainerStatus... items) {
      if (this.containerStatuses == null) {
         this.containerStatuses = new ArrayList();
      }

      for(ContainerStatus item : items) {
         ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
         this._visitables.get("containerStatuses").add(builder);
         this.containerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent addAllToContainerStatuses(Collection items) {
      if (this.containerStatuses == null) {
         this.containerStatuses = new ArrayList();
      }

      for(ContainerStatus item : items) {
         ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
         this._visitables.get("containerStatuses").add(builder);
         this.containerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent removeFromContainerStatuses(ContainerStatus... items) {
      if (this.containerStatuses == null) {
         return this;
      } else {
         for(ContainerStatus item : items) {
            ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
            this._visitables.get("containerStatuses").remove(builder);
            this.containerStatuses.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeAllFromContainerStatuses(Collection items) {
      if (this.containerStatuses == null) {
         return this;
      } else {
         for(ContainerStatus item : items) {
            ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
            this._visitables.get("containerStatuses").remove(builder);
            this.containerStatuses.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeMatchingFromContainerStatuses(Predicate predicate) {
      if (this.containerStatuses == null) {
         return this;
      } else {
         Iterator<ContainerStatusBuilder> each = this.containerStatuses.iterator();
         List visitables = this._visitables.get("containerStatuses");

         while(each.hasNext()) {
            ContainerStatusBuilder builder = (ContainerStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildContainerStatuses() {
      return this.containerStatuses != null ? build(this.containerStatuses) : null;
   }

   public ContainerStatus buildContainerStatus(int index) {
      return ((ContainerStatusBuilder)this.containerStatuses.get(index)).build();
   }

   public ContainerStatus buildFirstContainerStatus() {
      return ((ContainerStatusBuilder)this.containerStatuses.get(0)).build();
   }

   public ContainerStatus buildLastContainerStatus() {
      return ((ContainerStatusBuilder)this.containerStatuses.get(this.containerStatuses.size() - 1)).build();
   }

   public ContainerStatus buildMatchingContainerStatus(Predicate predicate) {
      for(ContainerStatusBuilder item : this.containerStatuses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingContainerStatus(Predicate predicate) {
      for(ContainerStatusBuilder item : this.containerStatuses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodStatusFluent withContainerStatuses(List containerStatuses) {
      if (this.containerStatuses != null) {
         this._visitables.get("containerStatuses").clear();
      }

      if (containerStatuses != null) {
         this.containerStatuses = new ArrayList();

         for(ContainerStatus item : containerStatuses) {
            this.addToContainerStatuses(item);
         }
      } else {
         this.containerStatuses = null;
      }

      return this;
   }

   public PodStatusFluent withContainerStatuses(ContainerStatus... containerStatuses) {
      if (this.containerStatuses != null) {
         this.containerStatuses.clear();
         this._visitables.remove("containerStatuses");
      }

      if (containerStatuses != null) {
         for(ContainerStatus item : containerStatuses) {
            this.addToContainerStatuses(item);
         }
      }

      return this;
   }

   public boolean hasContainerStatuses() {
      return this.containerStatuses != null && !this.containerStatuses.isEmpty();
   }

   public ContainerStatusesNested addNewContainerStatus() {
      return new ContainerStatusesNested(-1, (ContainerStatus)null);
   }

   public ContainerStatusesNested addNewContainerStatusLike(ContainerStatus item) {
      return new ContainerStatusesNested(-1, item);
   }

   public ContainerStatusesNested setNewContainerStatusLike(int index, ContainerStatus item) {
      return new ContainerStatusesNested(index, item);
   }

   public ContainerStatusesNested editContainerStatus(int index) {
      if (this.containerStatuses.size() <= index) {
         throw new RuntimeException("Can't edit containerStatuses. Index exceeds size.");
      } else {
         return this.setNewContainerStatusLike(index, this.buildContainerStatus(index));
      }
   }

   public ContainerStatusesNested editFirstContainerStatus() {
      if (this.containerStatuses.size() == 0) {
         throw new RuntimeException("Can't edit first containerStatuses. The list is empty.");
      } else {
         return this.setNewContainerStatusLike(0, this.buildContainerStatus(0));
      }
   }

   public ContainerStatusesNested editLastContainerStatus() {
      int index = this.containerStatuses.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last containerStatuses. The list is empty.");
      } else {
         return this.setNewContainerStatusLike(index, this.buildContainerStatus(index));
      }
   }

   public ContainerStatusesNested editMatchingContainerStatus(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.containerStatuses.size(); ++i) {
         if (predicate.test((ContainerStatusBuilder)this.containerStatuses.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching containerStatuses. No match found.");
      } else {
         return this.setNewContainerStatusLike(index, this.buildContainerStatus(index));
      }
   }

   public PodStatusFluent addToEphemeralContainerStatuses(int index, ContainerStatus item) {
      if (this.ephemeralContainerStatuses == null) {
         this.ephemeralContainerStatuses = new ArrayList();
      }

      ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
      if (index >= 0 && index < this.ephemeralContainerStatuses.size()) {
         this._visitables.get("ephemeralContainerStatuses").add(index, builder);
         this.ephemeralContainerStatuses.add(index, builder);
      } else {
         this._visitables.get("ephemeralContainerStatuses").add(builder);
         this.ephemeralContainerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent setToEphemeralContainerStatuses(int index, ContainerStatus item) {
      if (this.ephemeralContainerStatuses == null) {
         this.ephemeralContainerStatuses = new ArrayList();
      }

      ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
      if (index >= 0 && index < this.ephemeralContainerStatuses.size()) {
         this._visitables.get("ephemeralContainerStatuses").set(index, builder);
         this.ephemeralContainerStatuses.set(index, builder);
      } else {
         this._visitables.get("ephemeralContainerStatuses").add(builder);
         this.ephemeralContainerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent addToEphemeralContainerStatuses(ContainerStatus... items) {
      if (this.ephemeralContainerStatuses == null) {
         this.ephemeralContainerStatuses = new ArrayList();
      }

      for(ContainerStatus item : items) {
         ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
         this._visitables.get("ephemeralContainerStatuses").add(builder);
         this.ephemeralContainerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent addAllToEphemeralContainerStatuses(Collection items) {
      if (this.ephemeralContainerStatuses == null) {
         this.ephemeralContainerStatuses = new ArrayList();
      }

      for(ContainerStatus item : items) {
         ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
         this._visitables.get("ephemeralContainerStatuses").add(builder);
         this.ephemeralContainerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent removeFromEphemeralContainerStatuses(ContainerStatus... items) {
      if (this.ephemeralContainerStatuses == null) {
         return this;
      } else {
         for(ContainerStatus item : items) {
            ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
            this._visitables.get("ephemeralContainerStatuses").remove(builder);
            this.ephemeralContainerStatuses.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeAllFromEphemeralContainerStatuses(Collection items) {
      if (this.ephemeralContainerStatuses == null) {
         return this;
      } else {
         for(ContainerStatus item : items) {
            ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
            this._visitables.get("ephemeralContainerStatuses").remove(builder);
            this.ephemeralContainerStatuses.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeMatchingFromEphemeralContainerStatuses(Predicate predicate) {
      if (this.ephemeralContainerStatuses == null) {
         return this;
      } else {
         Iterator<ContainerStatusBuilder> each = this.ephemeralContainerStatuses.iterator();
         List visitables = this._visitables.get("ephemeralContainerStatuses");

         while(each.hasNext()) {
            ContainerStatusBuilder builder = (ContainerStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildEphemeralContainerStatuses() {
      return this.ephemeralContainerStatuses != null ? build(this.ephemeralContainerStatuses) : null;
   }

   public ContainerStatus buildEphemeralContainerStatus(int index) {
      return ((ContainerStatusBuilder)this.ephemeralContainerStatuses.get(index)).build();
   }

   public ContainerStatus buildFirstEphemeralContainerStatus() {
      return ((ContainerStatusBuilder)this.ephemeralContainerStatuses.get(0)).build();
   }

   public ContainerStatus buildLastEphemeralContainerStatus() {
      return ((ContainerStatusBuilder)this.ephemeralContainerStatuses.get(this.ephemeralContainerStatuses.size() - 1)).build();
   }

   public ContainerStatus buildMatchingEphemeralContainerStatus(Predicate predicate) {
      for(ContainerStatusBuilder item : this.ephemeralContainerStatuses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingEphemeralContainerStatus(Predicate predicate) {
      for(ContainerStatusBuilder item : this.ephemeralContainerStatuses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodStatusFluent withEphemeralContainerStatuses(List ephemeralContainerStatuses) {
      if (this.ephemeralContainerStatuses != null) {
         this._visitables.get("ephemeralContainerStatuses").clear();
      }

      if (ephemeralContainerStatuses != null) {
         this.ephemeralContainerStatuses = new ArrayList();

         for(ContainerStatus item : ephemeralContainerStatuses) {
            this.addToEphemeralContainerStatuses(item);
         }
      } else {
         this.ephemeralContainerStatuses = null;
      }

      return this;
   }

   public PodStatusFluent withEphemeralContainerStatuses(ContainerStatus... ephemeralContainerStatuses) {
      if (this.ephemeralContainerStatuses != null) {
         this.ephemeralContainerStatuses.clear();
         this._visitables.remove("ephemeralContainerStatuses");
      }

      if (ephemeralContainerStatuses != null) {
         for(ContainerStatus item : ephemeralContainerStatuses) {
            this.addToEphemeralContainerStatuses(item);
         }
      }

      return this;
   }

   public boolean hasEphemeralContainerStatuses() {
      return this.ephemeralContainerStatuses != null && !this.ephemeralContainerStatuses.isEmpty();
   }

   public EphemeralContainerStatusesNested addNewEphemeralContainerStatus() {
      return new EphemeralContainerStatusesNested(-1, (ContainerStatus)null);
   }

   public EphemeralContainerStatusesNested addNewEphemeralContainerStatusLike(ContainerStatus item) {
      return new EphemeralContainerStatusesNested(-1, item);
   }

   public EphemeralContainerStatusesNested setNewEphemeralContainerStatusLike(int index, ContainerStatus item) {
      return new EphemeralContainerStatusesNested(index, item);
   }

   public EphemeralContainerStatusesNested editEphemeralContainerStatus(int index) {
      if (this.ephemeralContainerStatuses.size() <= index) {
         throw new RuntimeException("Can't edit ephemeralContainerStatuses. Index exceeds size.");
      } else {
         return this.setNewEphemeralContainerStatusLike(index, this.buildEphemeralContainerStatus(index));
      }
   }

   public EphemeralContainerStatusesNested editFirstEphemeralContainerStatus() {
      if (this.ephemeralContainerStatuses.size() == 0) {
         throw new RuntimeException("Can't edit first ephemeralContainerStatuses. The list is empty.");
      } else {
         return this.setNewEphemeralContainerStatusLike(0, this.buildEphemeralContainerStatus(0));
      }
   }

   public EphemeralContainerStatusesNested editLastEphemeralContainerStatus() {
      int index = this.ephemeralContainerStatuses.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last ephemeralContainerStatuses. The list is empty.");
      } else {
         return this.setNewEphemeralContainerStatusLike(index, this.buildEphemeralContainerStatus(index));
      }
   }

   public EphemeralContainerStatusesNested editMatchingEphemeralContainerStatus(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.ephemeralContainerStatuses.size(); ++i) {
         if (predicate.test((ContainerStatusBuilder)this.ephemeralContainerStatuses.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching ephemeralContainerStatuses. No match found.");
      } else {
         return this.setNewEphemeralContainerStatusLike(index, this.buildEphemeralContainerStatus(index));
      }
   }

   public String getHostIP() {
      return this.hostIP;
   }

   public PodStatusFluent withHostIP(String hostIP) {
      this.hostIP = hostIP;
      return this;
   }

   public boolean hasHostIP() {
      return this.hostIP != null;
   }

   public PodStatusFluent addToHostIPs(int index, HostIP item) {
      if (this.hostIPs == null) {
         this.hostIPs = new ArrayList();
      }

      HostIPBuilder builder = new HostIPBuilder(item);
      if (index >= 0 && index < this.hostIPs.size()) {
         this._visitables.get("hostIPs").add(index, builder);
         this.hostIPs.add(index, builder);
      } else {
         this._visitables.get("hostIPs").add(builder);
         this.hostIPs.add(builder);
      }

      return this;
   }

   public PodStatusFluent setToHostIPs(int index, HostIP item) {
      if (this.hostIPs == null) {
         this.hostIPs = new ArrayList();
      }

      HostIPBuilder builder = new HostIPBuilder(item);
      if (index >= 0 && index < this.hostIPs.size()) {
         this._visitables.get("hostIPs").set(index, builder);
         this.hostIPs.set(index, builder);
      } else {
         this._visitables.get("hostIPs").add(builder);
         this.hostIPs.add(builder);
      }

      return this;
   }

   public PodStatusFluent addToHostIPs(HostIP... items) {
      if (this.hostIPs == null) {
         this.hostIPs = new ArrayList();
      }

      for(HostIP item : items) {
         HostIPBuilder builder = new HostIPBuilder(item);
         this._visitables.get("hostIPs").add(builder);
         this.hostIPs.add(builder);
      }

      return this;
   }

   public PodStatusFluent addAllToHostIPs(Collection items) {
      if (this.hostIPs == null) {
         this.hostIPs = new ArrayList();
      }

      for(HostIP item : items) {
         HostIPBuilder builder = new HostIPBuilder(item);
         this._visitables.get("hostIPs").add(builder);
         this.hostIPs.add(builder);
      }

      return this;
   }

   public PodStatusFluent removeFromHostIPs(HostIP... items) {
      if (this.hostIPs == null) {
         return this;
      } else {
         for(HostIP item : items) {
            HostIPBuilder builder = new HostIPBuilder(item);
            this._visitables.get("hostIPs").remove(builder);
            this.hostIPs.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeAllFromHostIPs(Collection items) {
      if (this.hostIPs == null) {
         return this;
      } else {
         for(HostIP item : items) {
            HostIPBuilder builder = new HostIPBuilder(item);
            this._visitables.get("hostIPs").remove(builder);
            this.hostIPs.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeMatchingFromHostIPs(Predicate predicate) {
      if (this.hostIPs == null) {
         return this;
      } else {
         Iterator<HostIPBuilder> each = this.hostIPs.iterator();
         List visitables = this._visitables.get("hostIPs");

         while(each.hasNext()) {
            HostIPBuilder builder = (HostIPBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildHostIPs() {
      return this.hostIPs != null ? build(this.hostIPs) : null;
   }

   public HostIP buildHostIP(int index) {
      return ((HostIPBuilder)this.hostIPs.get(index)).build();
   }

   public HostIP buildFirstHostIP() {
      return ((HostIPBuilder)this.hostIPs.get(0)).build();
   }

   public HostIP buildLastHostIP() {
      return ((HostIPBuilder)this.hostIPs.get(this.hostIPs.size() - 1)).build();
   }

   public HostIP buildMatchingHostIP(Predicate predicate) {
      for(HostIPBuilder item : this.hostIPs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingHostIP(Predicate predicate) {
      for(HostIPBuilder item : this.hostIPs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodStatusFluent withHostIPs(List hostIPs) {
      if (this.hostIPs != null) {
         this._visitables.get("hostIPs").clear();
      }

      if (hostIPs != null) {
         this.hostIPs = new ArrayList();

         for(HostIP item : hostIPs) {
            this.addToHostIPs(item);
         }
      } else {
         this.hostIPs = null;
      }

      return this;
   }

   public PodStatusFluent withHostIPs(HostIP... hostIPs) {
      if (this.hostIPs != null) {
         this.hostIPs.clear();
         this._visitables.remove("hostIPs");
      }

      if (hostIPs != null) {
         for(HostIP item : hostIPs) {
            this.addToHostIPs(item);
         }
      }

      return this;
   }

   public boolean hasHostIPs() {
      return this.hostIPs != null && !this.hostIPs.isEmpty();
   }

   public PodStatusFluent addNewHostIP(String ip) {
      return this.addToHostIPs(new HostIP(ip));
   }

   public HostIPsNested addNewHostIP() {
      return new HostIPsNested(-1, (HostIP)null);
   }

   public HostIPsNested addNewHostIPLike(HostIP item) {
      return new HostIPsNested(-1, item);
   }

   public HostIPsNested setNewHostIPLike(int index, HostIP item) {
      return new HostIPsNested(index, item);
   }

   public HostIPsNested editHostIP(int index) {
      if (this.hostIPs.size() <= index) {
         throw new RuntimeException("Can't edit hostIPs. Index exceeds size.");
      } else {
         return this.setNewHostIPLike(index, this.buildHostIP(index));
      }
   }

   public HostIPsNested editFirstHostIP() {
      if (this.hostIPs.size() == 0) {
         throw new RuntimeException("Can't edit first hostIPs. The list is empty.");
      } else {
         return this.setNewHostIPLike(0, this.buildHostIP(0));
      }
   }

   public HostIPsNested editLastHostIP() {
      int index = this.hostIPs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last hostIPs. The list is empty.");
      } else {
         return this.setNewHostIPLike(index, this.buildHostIP(index));
      }
   }

   public HostIPsNested editMatchingHostIP(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.hostIPs.size(); ++i) {
         if (predicate.test((HostIPBuilder)this.hostIPs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching hostIPs. No match found.");
      } else {
         return this.setNewHostIPLike(index, this.buildHostIP(index));
      }
   }

   public PodStatusFluent addToInitContainerStatuses(int index, ContainerStatus item) {
      if (this.initContainerStatuses == null) {
         this.initContainerStatuses = new ArrayList();
      }

      ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
      if (index >= 0 && index < this.initContainerStatuses.size()) {
         this._visitables.get("initContainerStatuses").add(index, builder);
         this.initContainerStatuses.add(index, builder);
      } else {
         this._visitables.get("initContainerStatuses").add(builder);
         this.initContainerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent setToInitContainerStatuses(int index, ContainerStatus item) {
      if (this.initContainerStatuses == null) {
         this.initContainerStatuses = new ArrayList();
      }

      ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
      if (index >= 0 && index < this.initContainerStatuses.size()) {
         this._visitables.get("initContainerStatuses").set(index, builder);
         this.initContainerStatuses.set(index, builder);
      } else {
         this._visitables.get("initContainerStatuses").add(builder);
         this.initContainerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent addToInitContainerStatuses(ContainerStatus... items) {
      if (this.initContainerStatuses == null) {
         this.initContainerStatuses = new ArrayList();
      }

      for(ContainerStatus item : items) {
         ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
         this._visitables.get("initContainerStatuses").add(builder);
         this.initContainerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent addAllToInitContainerStatuses(Collection items) {
      if (this.initContainerStatuses == null) {
         this.initContainerStatuses = new ArrayList();
      }

      for(ContainerStatus item : items) {
         ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
         this._visitables.get("initContainerStatuses").add(builder);
         this.initContainerStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent removeFromInitContainerStatuses(ContainerStatus... items) {
      if (this.initContainerStatuses == null) {
         return this;
      } else {
         for(ContainerStatus item : items) {
            ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
            this._visitables.get("initContainerStatuses").remove(builder);
            this.initContainerStatuses.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeAllFromInitContainerStatuses(Collection items) {
      if (this.initContainerStatuses == null) {
         return this;
      } else {
         for(ContainerStatus item : items) {
            ContainerStatusBuilder builder = new ContainerStatusBuilder(item);
            this._visitables.get("initContainerStatuses").remove(builder);
            this.initContainerStatuses.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeMatchingFromInitContainerStatuses(Predicate predicate) {
      if (this.initContainerStatuses == null) {
         return this;
      } else {
         Iterator<ContainerStatusBuilder> each = this.initContainerStatuses.iterator();
         List visitables = this._visitables.get("initContainerStatuses");

         while(each.hasNext()) {
            ContainerStatusBuilder builder = (ContainerStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildInitContainerStatuses() {
      return this.initContainerStatuses != null ? build(this.initContainerStatuses) : null;
   }

   public ContainerStatus buildInitContainerStatus(int index) {
      return ((ContainerStatusBuilder)this.initContainerStatuses.get(index)).build();
   }

   public ContainerStatus buildFirstInitContainerStatus() {
      return ((ContainerStatusBuilder)this.initContainerStatuses.get(0)).build();
   }

   public ContainerStatus buildLastInitContainerStatus() {
      return ((ContainerStatusBuilder)this.initContainerStatuses.get(this.initContainerStatuses.size() - 1)).build();
   }

   public ContainerStatus buildMatchingInitContainerStatus(Predicate predicate) {
      for(ContainerStatusBuilder item : this.initContainerStatuses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingInitContainerStatus(Predicate predicate) {
      for(ContainerStatusBuilder item : this.initContainerStatuses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodStatusFluent withInitContainerStatuses(List initContainerStatuses) {
      if (this.initContainerStatuses != null) {
         this._visitables.get("initContainerStatuses").clear();
      }

      if (initContainerStatuses != null) {
         this.initContainerStatuses = new ArrayList();

         for(ContainerStatus item : initContainerStatuses) {
            this.addToInitContainerStatuses(item);
         }
      } else {
         this.initContainerStatuses = null;
      }

      return this;
   }

   public PodStatusFluent withInitContainerStatuses(ContainerStatus... initContainerStatuses) {
      if (this.initContainerStatuses != null) {
         this.initContainerStatuses.clear();
         this._visitables.remove("initContainerStatuses");
      }

      if (initContainerStatuses != null) {
         for(ContainerStatus item : initContainerStatuses) {
            this.addToInitContainerStatuses(item);
         }
      }

      return this;
   }

   public boolean hasInitContainerStatuses() {
      return this.initContainerStatuses != null && !this.initContainerStatuses.isEmpty();
   }

   public InitContainerStatusesNested addNewInitContainerStatus() {
      return new InitContainerStatusesNested(-1, (ContainerStatus)null);
   }

   public InitContainerStatusesNested addNewInitContainerStatusLike(ContainerStatus item) {
      return new InitContainerStatusesNested(-1, item);
   }

   public InitContainerStatusesNested setNewInitContainerStatusLike(int index, ContainerStatus item) {
      return new InitContainerStatusesNested(index, item);
   }

   public InitContainerStatusesNested editInitContainerStatus(int index) {
      if (this.initContainerStatuses.size() <= index) {
         throw new RuntimeException("Can't edit initContainerStatuses. Index exceeds size.");
      } else {
         return this.setNewInitContainerStatusLike(index, this.buildInitContainerStatus(index));
      }
   }

   public InitContainerStatusesNested editFirstInitContainerStatus() {
      if (this.initContainerStatuses.size() == 0) {
         throw new RuntimeException("Can't edit first initContainerStatuses. The list is empty.");
      } else {
         return this.setNewInitContainerStatusLike(0, this.buildInitContainerStatus(0));
      }
   }

   public InitContainerStatusesNested editLastInitContainerStatus() {
      int index = this.initContainerStatuses.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last initContainerStatuses. The list is empty.");
      } else {
         return this.setNewInitContainerStatusLike(index, this.buildInitContainerStatus(index));
      }
   }

   public InitContainerStatusesNested editMatchingInitContainerStatus(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.initContainerStatuses.size(); ++i) {
         if (predicate.test((ContainerStatusBuilder)this.initContainerStatuses.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching initContainerStatuses. No match found.");
      } else {
         return this.setNewInitContainerStatusLike(index, this.buildInitContainerStatus(index));
      }
   }

   public String getMessage() {
      return this.message;
   }

   public PodStatusFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public String getNominatedNodeName() {
      return this.nominatedNodeName;
   }

   public PodStatusFluent withNominatedNodeName(String nominatedNodeName) {
      this.nominatedNodeName = nominatedNodeName;
      return this;
   }

   public boolean hasNominatedNodeName() {
      return this.nominatedNodeName != null;
   }

   public String getPhase() {
      return this.phase;
   }

   public PodStatusFluent withPhase(String phase) {
      this.phase = phase;
      return this;
   }

   public boolean hasPhase() {
      return this.phase != null;
   }

   public String getPodIP() {
      return this.podIP;
   }

   public PodStatusFluent withPodIP(String podIP) {
      this.podIP = podIP;
      return this;
   }

   public boolean hasPodIP() {
      return this.podIP != null;
   }

   public PodStatusFluent addToPodIPs(int index, PodIP item) {
      if (this.podIPs == null) {
         this.podIPs = new ArrayList();
      }

      PodIPBuilder builder = new PodIPBuilder(item);
      if (index >= 0 && index < this.podIPs.size()) {
         this._visitables.get("podIPs").add(index, builder);
         this.podIPs.add(index, builder);
      } else {
         this._visitables.get("podIPs").add(builder);
         this.podIPs.add(builder);
      }

      return this;
   }

   public PodStatusFluent setToPodIPs(int index, PodIP item) {
      if (this.podIPs == null) {
         this.podIPs = new ArrayList();
      }

      PodIPBuilder builder = new PodIPBuilder(item);
      if (index >= 0 && index < this.podIPs.size()) {
         this._visitables.get("podIPs").set(index, builder);
         this.podIPs.set(index, builder);
      } else {
         this._visitables.get("podIPs").add(builder);
         this.podIPs.add(builder);
      }

      return this;
   }

   public PodStatusFluent addToPodIPs(PodIP... items) {
      if (this.podIPs == null) {
         this.podIPs = new ArrayList();
      }

      for(PodIP item : items) {
         PodIPBuilder builder = new PodIPBuilder(item);
         this._visitables.get("podIPs").add(builder);
         this.podIPs.add(builder);
      }

      return this;
   }

   public PodStatusFluent addAllToPodIPs(Collection items) {
      if (this.podIPs == null) {
         this.podIPs = new ArrayList();
      }

      for(PodIP item : items) {
         PodIPBuilder builder = new PodIPBuilder(item);
         this._visitables.get("podIPs").add(builder);
         this.podIPs.add(builder);
      }

      return this;
   }

   public PodStatusFluent removeFromPodIPs(PodIP... items) {
      if (this.podIPs == null) {
         return this;
      } else {
         for(PodIP item : items) {
            PodIPBuilder builder = new PodIPBuilder(item);
            this._visitables.get("podIPs").remove(builder);
            this.podIPs.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeAllFromPodIPs(Collection items) {
      if (this.podIPs == null) {
         return this;
      } else {
         for(PodIP item : items) {
            PodIPBuilder builder = new PodIPBuilder(item);
            this._visitables.get("podIPs").remove(builder);
            this.podIPs.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeMatchingFromPodIPs(Predicate predicate) {
      if (this.podIPs == null) {
         return this;
      } else {
         Iterator<PodIPBuilder> each = this.podIPs.iterator();
         List visitables = this._visitables.get("podIPs");

         while(each.hasNext()) {
            PodIPBuilder builder = (PodIPBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildPodIPs() {
      return this.podIPs != null ? build(this.podIPs) : null;
   }

   public PodIP buildPodIP(int index) {
      return ((PodIPBuilder)this.podIPs.get(index)).build();
   }

   public PodIP buildFirstPodIP() {
      return ((PodIPBuilder)this.podIPs.get(0)).build();
   }

   public PodIP buildLastPodIP() {
      return ((PodIPBuilder)this.podIPs.get(this.podIPs.size() - 1)).build();
   }

   public PodIP buildMatchingPodIP(Predicate predicate) {
      for(PodIPBuilder item : this.podIPs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingPodIP(Predicate predicate) {
      for(PodIPBuilder item : this.podIPs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodStatusFluent withPodIPs(List podIPs) {
      if (this.podIPs != null) {
         this._visitables.get("podIPs").clear();
      }

      if (podIPs != null) {
         this.podIPs = new ArrayList();

         for(PodIP item : podIPs) {
            this.addToPodIPs(item);
         }
      } else {
         this.podIPs = null;
      }

      return this;
   }

   public PodStatusFluent withPodIPs(PodIP... podIPs) {
      if (this.podIPs != null) {
         this.podIPs.clear();
         this._visitables.remove("podIPs");
      }

      if (podIPs != null) {
         for(PodIP item : podIPs) {
            this.addToPodIPs(item);
         }
      }

      return this;
   }

   public boolean hasPodIPs() {
      return this.podIPs != null && !this.podIPs.isEmpty();
   }

   public PodStatusFluent addNewPodIP(String ip) {
      return this.addToPodIPs(new PodIP(ip));
   }

   public PodIPsNested addNewPodIP() {
      return new PodIPsNested(-1, (PodIP)null);
   }

   public PodIPsNested addNewPodIPLike(PodIP item) {
      return new PodIPsNested(-1, item);
   }

   public PodIPsNested setNewPodIPLike(int index, PodIP item) {
      return new PodIPsNested(index, item);
   }

   public PodIPsNested editPodIP(int index) {
      if (this.podIPs.size() <= index) {
         throw new RuntimeException("Can't edit podIPs. Index exceeds size.");
      } else {
         return this.setNewPodIPLike(index, this.buildPodIP(index));
      }
   }

   public PodIPsNested editFirstPodIP() {
      if (this.podIPs.size() == 0) {
         throw new RuntimeException("Can't edit first podIPs. The list is empty.");
      } else {
         return this.setNewPodIPLike(0, this.buildPodIP(0));
      }
   }

   public PodIPsNested editLastPodIP() {
      int index = this.podIPs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last podIPs. The list is empty.");
      } else {
         return this.setNewPodIPLike(index, this.buildPodIP(index));
      }
   }

   public PodIPsNested editMatchingPodIP(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.podIPs.size(); ++i) {
         if (predicate.test((PodIPBuilder)this.podIPs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching podIPs. No match found.");
      } else {
         return this.setNewPodIPLike(index, this.buildPodIP(index));
      }
   }

   public String getQosClass() {
      return this.qosClass;
   }

   public PodStatusFluent withQosClass(String qosClass) {
      this.qosClass = qosClass;
      return this;
   }

   public boolean hasQosClass() {
      return this.qosClass != null;
   }

   public String getReason() {
      return this.reason;
   }

   public PodStatusFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public String getResize() {
      return this.resize;
   }

   public PodStatusFluent withResize(String resize) {
      this.resize = resize;
      return this;
   }

   public boolean hasResize() {
      return this.resize != null;
   }

   public PodStatusFluent addToResourceClaimStatuses(int index, PodResourceClaimStatus item) {
      if (this.resourceClaimStatuses == null) {
         this.resourceClaimStatuses = new ArrayList();
      }

      PodResourceClaimStatusBuilder builder = new PodResourceClaimStatusBuilder(item);
      if (index >= 0 && index < this.resourceClaimStatuses.size()) {
         this._visitables.get("resourceClaimStatuses").add(index, builder);
         this.resourceClaimStatuses.add(index, builder);
      } else {
         this._visitables.get("resourceClaimStatuses").add(builder);
         this.resourceClaimStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent setToResourceClaimStatuses(int index, PodResourceClaimStatus item) {
      if (this.resourceClaimStatuses == null) {
         this.resourceClaimStatuses = new ArrayList();
      }

      PodResourceClaimStatusBuilder builder = new PodResourceClaimStatusBuilder(item);
      if (index >= 0 && index < this.resourceClaimStatuses.size()) {
         this._visitables.get("resourceClaimStatuses").set(index, builder);
         this.resourceClaimStatuses.set(index, builder);
      } else {
         this._visitables.get("resourceClaimStatuses").add(builder);
         this.resourceClaimStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent addToResourceClaimStatuses(PodResourceClaimStatus... items) {
      if (this.resourceClaimStatuses == null) {
         this.resourceClaimStatuses = new ArrayList();
      }

      for(PodResourceClaimStatus item : items) {
         PodResourceClaimStatusBuilder builder = new PodResourceClaimStatusBuilder(item);
         this._visitables.get("resourceClaimStatuses").add(builder);
         this.resourceClaimStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent addAllToResourceClaimStatuses(Collection items) {
      if (this.resourceClaimStatuses == null) {
         this.resourceClaimStatuses = new ArrayList();
      }

      for(PodResourceClaimStatus item : items) {
         PodResourceClaimStatusBuilder builder = new PodResourceClaimStatusBuilder(item);
         this._visitables.get("resourceClaimStatuses").add(builder);
         this.resourceClaimStatuses.add(builder);
      }

      return this;
   }

   public PodStatusFluent removeFromResourceClaimStatuses(PodResourceClaimStatus... items) {
      if (this.resourceClaimStatuses == null) {
         return this;
      } else {
         for(PodResourceClaimStatus item : items) {
            PodResourceClaimStatusBuilder builder = new PodResourceClaimStatusBuilder(item);
            this._visitables.get("resourceClaimStatuses").remove(builder);
            this.resourceClaimStatuses.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeAllFromResourceClaimStatuses(Collection items) {
      if (this.resourceClaimStatuses == null) {
         return this;
      } else {
         for(PodResourceClaimStatus item : items) {
            PodResourceClaimStatusBuilder builder = new PodResourceClaimStatusBuilder(item);
            this._visitables.get("resourceClaimStatuses").remove(builder);
            this.resourceClaimStatuses.remove(builder);
         }

         return this;
      }
   }

   public PodStatusFluent removeMatchingFromResourceClaimStatuses(Predicate predicate) {
      if (this.resourceClaimStatuses == null) {
         return this;
      } else {
         Iterator<PodResourceClaimStatusBuilder> each = this.resourceClaimStatuses.iterator();
         List visitables = this._visitables.get("resourceClaimStatuses");

         while(each.hasNext()) {
            PodResourceClaimStatusBuilder builder = (PodResourceClaimStatusBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildResourceClaimStatuses() {
      return this.resourceClaimStatuses != null ? build(this.resourceClaimStatuses) : null;
   }

   public PodResourceClaimStatus buildResourceClaimStatus(int index) {
      return ((PodResourceClaimStatusBuilder)this.resourceClaimStatuses.get(index)).build();
   }

   public PodResourceClaimStatus buildFirstResourceClaimStatus() {
      return ((PodResourceClaimStatusBuilder)this.resourceClaimStatuses.get(0)).build();
   }

   public PodResourceClaimStatus buildLastResourceClaimStatus() {
      return ((PodResourceClaimStatusBuilder)this.resourceClaimStatuses.get(this.resourceClaimStatuses.size() - 1)).build();
   }

   public PodResourceClaimStatus buildMatchingResourceClaimStatus(Predicate predicate) {
      for(PodResourceClaimStatusBuilder item : this.resourceClaimStatuses) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingResourceClaimStatus(Predicate predicate) {
      for(PodResourceClaimStatusBuilder item : this.resourceClaimStatuses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public PodStatusFluent withResourceClaimStatuses(List resourceClaimStatuses) {
      if (this.resourceClaimStatuses != null) {
         this._visitables.get("resourceClaimStatuses").clear();
      }

      if (resourceClaimStatuses != null) {
         this.resourceClaimStatuses = new ArrayList();

         for(PodResourceClaimStatus item : resourceClaimStatuses) {
            this.addToResourceClaimStatuses(item);
         }
      } else {
         this.resourceClaimStatuses = null;
      }

      return this;
   }

   public PodStatusFluent withResourceClaimStatuses(PodResourceClaimStatus... resourceClaimStatuses) {
      if (this.resourceClaimStatuses != null) {
         this.resourceClaimStatuses.clear();
         this._visitables.remove("resourceClaimStatuses");
      }

      if (resourceClaimStatuses != null) {
         for(PodResourceClaimStatus item : resourceClaimStatuses) {
            this.addToResourceClaimStatuses(item);
         }
      }

      return this;
   }

   public boolean hasResourceClaimStatuses() {
      return this.resourceClaimStatuses != null && !this.resourceClaimStatuses.isEmpty();
   }

   public PodStatusFluent addNewResourceClaimStatus(String name, String resourceClaimName) {
      return this.addToResourceClaimStatuses(new PodResourceClaimStatus(name, resourceClaimName));
   }

   public ResourceClaimStatusesNested addNewResourceClaimStatus() {
      return new ResourceClaimStatusesNested(-1, (PodResourceClaimStatus)null);
   }

   public ResourceClaimStatusesNested addNewResourceClaimStatusLike(PodResourceClaimStatus item) {
      return new ResourceClaimStatusesNested(-1, item);
   }

   public ResourceClaimStatusesNested setNewResourceClaimStatusLike(int index, PodResourceClaimStatus item) {
      return new ResourceClaimStatusesNested(index, item);
   }

   public ResourceClaimStatusesNested editResourceClaimStatus(int index) {
      if (this.resourceClaimStatuses.size() <= index) {
         throw new RuntimeException("Can't edit resourceClaimStatuses. Index exceeds size.");
      } else {
         return this.setNewResourceClaimStatusLike(index, this.buildResourceClaimStatus(index));
      }
   }

   public ResourceClaimStatusesNested editFirstResourceClaimStatus() {
      if (this.resourceClaimStatuses.size() == 0) {
         throw new RuntimeException("Can't edit first resourceClaimStatuses. The list is empty.");
      } else {
         return this.setNewResourceClaimStatusLike(0, this.buildResourceClaimStatus(0));
      }
   }

   public ResourceClaimStatusesNested editLastResourceClaimStatus() {
      int index = this.resourceClaimStatuses.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last resourceClaimStatuses. The list is empty.");
      } else {
         return this.setNewResourceClaimStatusLike(index, this.buildResourceClaimStatus(index));
      }
   }

   public ResourceClaimStatusesNested editMatchingResourceClaimStatus(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.resourceClaimStatuses.size(); ++i) {
         if (predicate.test((PodResourceClaimStatusBuilder)this.resourceClaimStatuses.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching resourceClaimStatuses. No match found.");
      } else {
         return this.setNewResourceClaimStatusLike(index, this.buildResourceClaimStatus(index));
      }
   }

   public String getStartTime() {
      return this.startTime;
   }

   public PodStatusFluent withStartTime(String startTime) {
      this.startTime = startTime;
      return this;
   }

   public boolean hasStartTime() {
      return this.startTime != null;
   }

   public PodStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public PodStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public PodStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public PodStatusFluent removeFromAdditionalProperties(Map map) {
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

   public PodStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            PodStatusFluent that = (PodStatusFluent)o;
            if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.containerStatuses, that.containerStatuses)) {
               return false;
            } else if (!Objects.equals(this.ephemeralContainerStatuses, that.ephemeralContainerStatuses)) {
               return false;
            } else if (!Objects.equals(this.hostIP, that.hostIP)) {
               return false;
            } else if (!Objects.equals(this.hostIPs, that.hostIPs)) {
               return false;
            } else if (!Objects.equals(this.initContainerStatuses, that.initContainerStatuses)) {
               return false;
            } else if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.nominatedNodeName, that.nominatedNodeName)) {
               return false;
            } else if (!Objects.equals(this.phase, that.phase)) {
               return false;
            } else if (!Objects.equals(this.podIP, that.podIP)) {
               return false;
            } else if (!Objects.equals(this.podIPs, that.podIPs)) {
               return false;
            } else if (!Objects.equals(this.qosClass, that.qosClass)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
               return false;
            } else if (!Objects.equals(this.resize, that.resize)) {
               return false;
            } else if (!Objects.equals(this.resourceClaimStatuses, that.resourceClaimStatuses)) {
               return false;
            } else if (!Objects.equals(this.startTime, that.startTime)) {
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
      return Objects.hash(new Object[]{this.conditions, this.containerStatuses, this.ephemeralContainerStatuses, this.hostIP, this.hostIPs, this.initContainerStatuses, this.message, this.nominatedNodeName, this.phase, this.podIP, this.podIPs, this.qosClass, this.reason, this.resize, this.resourceClaimStatuses, this.startTime, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.conditions != null && !this.conditions.isEmpty()) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.containerStatuses != null && !this.containerStatuses.isEmpty()) {
         sb.append("containerStatuses:");
         sb.append(this.containerStatuses + ",");
      }

      if (this.ephemeralContainerStatuses != null && !this.ephemeralContainerStatuses.isEmpty()) {
         sb.append("ephemeralContainerStatuses:");
         sb.append(this.ephemeralContainerStatuses + ",");
      }

      if (this.hostIP != null) {
         sb.append("hostIP:");
         sb.append(this.hostIP + ",");
      }

      if (this.hostIPs != null && !this.hostIPs.isEmpty()) {
         sb.append("hostIPs:");
         sb.append(this.hostIPs + ",");
      }

      if (this.initContainerStatuses != null && !this.initContainerStatuses.isEmpty()) {
         sb.append("initContainerStatuses:");
         sb.append(this.initContainerStatuses + ",");
      }

      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.nominatedNodeName != null) {
         sb.append("nominatedNodeName:");
         sb.append(this.nominatedNodeName + ",");
      }

      if (this.phase != null) {
         sb.append("phase:");
         sb.append(this.phase + ",");
      }

      if (this.podIP != null) {
         sb.append("podIP:");
         sb.append(this.podIP + ",");
      }

      if (this.podIPs != null && !this.podIPs.isEmpty()) {
         sb.append("podIPs:");
         sb.append(this.podIPs + ",");
      }

      if (this.qosClass != null) {
         sb.append("qosClass:");
         sb.append(this.qosClass + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.resize != null) {
         sb.append("resize:");
         sb.append(this.resize + ",");
      }

      if (this.resourceClaimStatuses != null && !this.resourceClaimStatuses.isEmpty()) {
         sb.append("resourceClaimStatuses:");
         sb.append(this.resourceClaimStatuses + ",");
      }

      if (this.startTime != null) {
         sb.append("startTime:");
         sb.append(this.startTime + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends PodConditionFluent implements Nested {
      PodConditionBuilder builder;
      int index;

      ConditionsNested(int index, PodCondition item) {
         this.index = index;
         this.builder = new PodConditionBuilder(this, item);
      }

      public Object and() {
         return PodStatusFluent.this.setToConditions(this.index, this.builder.build());
      }

      public Object endCondition() {
         return this.and();
      }
   }

   public class ContainerStatusesNested extends ContainerStatusFluent implements Nested {
      ContainerStatusBuilder builder;
      int index;

      ContainerStatusesNested(int index, ContainerStatus item) {
         this.index = index;
         this.builder = new ContainerStatusBuilder(this, item);
      }

      public Object and() {
         return PodStatusFluent.this.setToContainerStatuses(this.index, this.builder.build());
      }

      public Object endContainerStatus() {
         return this.and();
      }
   }

   public class EphemeralContainerStatusesNested extends ContainerStatusFluent implements Nested {
      ContainerStatusBuilder builder;
      int index;

      EphemeralContainerStatusesNested(int index, ContainerStatus item) {
         this.index = index;
         this.builder = new ContainerStatusBuilder(this, item);
      }

      public Object and() {
         return PodStatusFluent.this.setToEphemeralContainerStatuses(this.index, this.builder.build());
      }

      public Object endEphemeralContainerStatus() {
         return this.and();
      }
   }

   public class HostIPsNested extends HostIPFluent implements Nested {
      HostIPBuilder builder;
      int index;

      HostIPsNested(int index, HostIP item) {
         this.index = index;
         this.builder = new HostIPBuilder(this, item);
      }

      public Object and() {
         return PodStatusFluent.this.setToHostIPs(this.index, this.builder.build());
      }

      public Object endHostIP() {
         return this.and();
      }
   }

   public class InitContainerStatusesNested extends ContainerStatusFluent implements Nested {
      ContainerStatusBuilder builder;
      int index;

      InitContainerStatusesNested(int index, ContainerStatus item) {
         this.index = index;
         this.builder = new ContainerStatusBuilder(this, item);
      }

      public Object and() {
         return PodStatusFluent.this.setToInitContainerStatuses(this.index, this.builder.build());
      }

      public Object endInitContainerStatus() {
         return this.and();
      }
   }

   public class PodIPsNested extends PodIPFluent implements Nested {
      PodIPBuilder builder;
      int index;

      PodIPsNested(int index, PodIP item) {
         this.index = index;
         this.builder = new PodIPBuilder(this, item);
      }

      public Object and() {
         return PodStatusFluent.this.setToPodIPs(this.index, this.builder.build());
      }

      public Object endPodIP() {
         return this.and();
      }
   }

   public class ResourceClaimStatusesNested extends PodResourceClaimStatusFluent implements Nested {
      PodResourceClaimStatusBuilder builder;
      int index;

      ResourceClaimStatusesNested(int index, PodResourceClaimStatus item) {
         this.index = index;
         this.builder = new PodResourceClaimStatusBuilder(this, item);
      }

      public Object and() {
         return PodStatusFluent.this.setToResourceClaimStatuses(this.index, this.builder.build());
      }

      public Object endResourceClaimStatus() {
         return this.and();
      }
   }
}
