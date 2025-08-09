package io.fabric8.kubernetes.api.model.apps;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder;
import io.fabric8.kubernetes.api.model.LabelSelectorFluent;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaim;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimBuilder;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimFluent;
import io.fabric8.kubernetes.api.model.PodTemplateSpec;
import io.fabric8.kubernetes.api.model.PodTemplateSpecBuilder;
import io.fabric8.kubernetes.api.model.PodTemplateSpecFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class StatefulSetSpecFluent extends BaseFluent {
   private Integer minReadySeconds;
   private StatefulSetOrdinalsBuilder ordinals;
   private StatefulSetPersistentVolumeClaimRetentionPolicyBuilder persistentVolumeClaimRetentionPolicy;
   private String podManagementPolicy;
   private Integer replicas;
   private Integer revisionHistoryLimit;
   private LabelSelectorBuilder selector;
   private String serviceName;
   private PodTemplateSpecBuilder template;
   private StatefulSetUpdateStrategyBuilder updateStrategy;
   private ArrayList volumeClaimTemplates = new ArrayList();
   private Map additionalProperties;

   public StatefulSetSpecFluent() {
   }

   public StatefulSetSpecFluent(StatefulSetSpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(StatefulSetSpec instance) {
      instance = instance != null ? instance : new StatefulSetSpec();
      if (instance != null) {
         this.withMinReadySeconds(instance.getMinReadySeconds());
         this.withOrdinals(instance.getOrdinals());
         this.withPersistentVolumeClaimRetentionPolicy(instance.getPersistentVolumeClaimRetentionPolicy());
         this.withPodManagementPolicy(instance.getPodManagementPolicy());
         this.withReplicas(instance.getReplicas());
         this.withRevisionHistoryLimit(instance.getRevisionHistoryLimit());
         this.withSelector(instance.getSelector());
         this.withServiceName(instance.getServiceName());
         this.withTemplate(instance.getTemplate());
         this.withUpdateStrategy(instance.getUpdateStrategy());
         this.withVolumeClaimTemplates(instance.getVolumeClaimTemplates());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Integer getMinReadySeconds() {
      return this.minReadySeconds;
   }

   public StatefulSetSpecFluent withMinReadySeconds(Integer minReadySeconds) {
      this.minReadySeconds = minReadySeconds;
      return this;
   }

   public boolean hasMinReadySeconds() {
      return this.minReadySeconds != null;
   }

   public StatefulSetOrdinals buildOrdinals() {
      return this.ordinals != null ? this.ordinals.build() : null;
   }

   public StatefulSetSpecFluent withOrdinals(StatefulSetOrdinals ordinals) {
      this._visitables.remove("ordinals");
      if (ordinals != null) {
         this.ordinals = new StatefulSetOrdinalsBuilder(ordinals);
         this._visitables.get("ordinals").add(this.ordinals);
      } else {
         this.ordinals = null;
         this._visitables.get("ordinals").remove(this.ordinals);
      }

      return this;
   }

   public boolean hasOrdinals() {
      return this.ordinals != null;
   }

   public StatefulSetSpecFluent withNewOrdinals(Integer start) {
      return this.withOrdinals(new StatefulSetOrdinals(start));
   }

   public OrdinalsNested withNewOrdinals() {
      return new OrdinalsNested((StatefulSetOrdinals)null);
   }

   public OrdinalsNested withNewOrdinalsLike(StatefulSetOrdinals item) {
      return new OrdinalsNested(item);
   }

   public OrdinalsNested editOrdinals() {
      return this.withNewOrdinalsLike((StatefulSetOrdinals)Optional.ofNullable(this.buildOrdinals()).orElse((Object)null));
   }

   public OrdinalsNested editOrNewOrdinals() {
      return this.withNewOrdinalsLike((StatefulSetOrdinals)Optional.ofNullable(this.buildOrdinals()).orElse((new StatefulSetOrdinalsBuilder()).build()));
   }

   public OrdinalsNested editOrNewOrdinalsLike(StatefulSetOrdinals item) {
      return this.withNewOrdinalsLike((StatefulSetOrdinals)Optional.ofNullable(this.buildOrdinals()).orElse(item));
   }

   public StatefulSetPersistentVolumeClaimRetentionPolicy buildPersistentVolumeClaimRetentionPolicy() {
      return this.persistentVolumeClaimRetentionPolicy != null ? this.persistentVolumeClaimRetentionPolicy.build() : null;
   }

   public StatefulSetSpecFluent withPersistentVolumeClaimRetentionPolicy(StatefulSetPersistentVolumeClaimRetentionPolicy persistentVolumeClaimRetentionPolicy) {
      this._visitables.remove("persistentVolumeClaimRetentionPolicy");
      if (persistentVolumeClaimRetentionPolicy != null) {
         this.persistentVolumeClaimRetentionPolicy = new StatefulSetPersistentVolumeClaimRetentionPolicyBuilder(persistentVolumeClaimRetentionPolicy);
         this._visitables.get("persistentVolumeClaimRetentionPolicy").add(this.persistentVolumeClaimRetentionPolicy);
      } else {
         this.persistentVolumeClaimRetentionPolicy = null;
         this._visitables.get("persistentVolumeClaimRetentionPolicy").remove(this.persistentVolumeClaimRetentionPolicy);
      }

      return this;
   }

   public boolean hasPersistentVolumeClaimRetentionPolicy() {
      return this.persistentVolumeClaimRetentionPolicy != null;
   }

   public StatefulSetSpecFluent withNewPersistentVolumeClaimRetentionPolicy(String whenDeleted, String whenScaled) {
      return this.withPersistentVolumeClaimRetentionPolicy(new StatefulSetPersistentVolumeClaimRetentionPolicy(whenDeleted, whenScaled));
   }

   public PersistentVolumeClaimRetentionPolicyNested withNewPersistentVolumeClaimRetentionPolicy() {
      return new PersistentVolumeClaimRetentionPolicyNested((StatefulSetPersistentVolumeClaimRetentionPolicy)null);
   }

   public PersistentVolumeClaimRetentionPolicyNested withNewPersistentVolumeClaimRetentionPolicyLike(StatefulSetPersistentVolumeClaimRetentionPolicy item) {
      return new PersistentVolumeClaimRetentionPolicyNested(item);
   }

   public PersistentVolumeClaimRetentionPolicyNested editPersistentVolumeClaimRetentionPolicy() {
      return this.withNewPersistentVolumeClaimRetentionPolicyLike((StatefulSetPersistentVolumeClaimRetentionPolicy)Optional.ofNullable(this.buildPersistentVolumeClaimRetentionPolicy()).orElse((Object)null));
   }

   public PersistentVolumeClaimRetentionPolicyNested editOrNewPersistentVolumeClaimRetentionPolicy() {
      return this.withNewPersistentVolumeClaimRetentionPolicyLike((StatefulSetPersistentVolumeClaimRetentionPolicy)Optional.ofNullable(this.buildPersistentVolumeClaimRetentionPolicy()).orElse((new StatefulSetPersistentVolumeClaimRetentionPolicyBuilder()).build()));
   }

   public PersistentVolumeClaimRetentionPolicyNested editOrNewPersistentVolumeClaimRetentionPolicyLike(StatefulSetPersistentVolumeClaimRetentionPolicy item) {
      return this.withNewPersistentVolumeClaimRetentionPolicyLike((StatefulSetPersistentVolumeClaimRetentionPolicy)Optional.ofNullable(this.buildPersistentVolumeClaimRetentionPolicy()).orElse(item));
   }

   public String getPodManagementPolicy() {
      return this.podManagementPolicy;
   }

   public StatefulSetSpecFluent withPodManagementPolicy(String podManagementPolicy) {
      this.podManagementPolicy = podManagementPolicy;
      return this;
   }

   public boolean hasPodManagementPolicy() {
      return this.podManagementPolicy != null;
   }

   public Integer getReplicas() {
      return this.replicas;
   }

   public StatefulSetSpecFluent withReplicas(Integer replicas) {
      this.replicas = replicas;
      return this;
   }

   public boolean hasReplicas() {
      return this.replicas != null;
   }

   public Integer getRevisionHistoryLimit() {
      return this.revisionHistoryLimit;
   }

   public StatefulSetSpecFluent withRevisionHistoryLimit(Integer revisionHistoryLimit) {
      this.revisionHistoryLimit = revisionHistoryLimit;
      return this;
   }

   public boolean hasRevisionHistoryLimit() {
      return this.revisionHistoryLimit != null;
   }

   public LabelSelector buildSelector() {
      return this.selector != null ? this.selector.build() : null;
   }

   public StatefulSetSpecFluent withSelector(LabelSelector selector) {
      this._visitables.remove("selector");
      if (selector != null) {
         this.selector = new LabelSelectorBuilder(selector);
         this._visitables.get("selector").add(this.selector);
      } else {
         this.selector = null;
         this._visitables.get("selector").remove(this.selector);
      }

      return this;
   }

   public boolean hasSelector() {
      return this.selector != null;
   }

   public SelectorNested withNewSelector() {
      return new SelectorNested((LabelSelector)null);
   }

   public SelectorNested withNewSelectorLike(LabelSelector item) {
      return new SelectorNested(item);
   }

   public SelectorNested editSelector() {
      return this.withNewSelectorLike((LabelSelector)Optional.ofNullable(this.buildSelector()).orElse((Object)null));
   }

   public SelectorNested editOrNewSelector() {
      return this.withNewSelectorLike((LabelSelector)Optional.ofNullable(this.buildSelector()).orElse((new LabelSelectorBuilder()).build()));
   }

   public SelectorNested editOrNewSelectorLike(LabelSelector item) {
      return this.withNewSelectorLike((LabelSelector)Optional.ofNullable(this.buildSelector()).orElse(item));
   }

   public String getServiceName() {
      return this.serviceName;
   }

   public StatefulSetSpecFluent withServiceName(String serviceName) {
      this.serviceName = serviceName;
      return this;
   }

   public boolean hasServiceName() {
      return this.serviceName != null;
   }

   public PodTemplateSpec buildTemplate() {
      return this.template != null ? this.template.build() : null;
   }

   public StatefulSetSpecFluent withTemplate(PodTemplateSpec template) {
      this._visitables.remove("template");
      if (template != null) {
         this.template = new PodTemplateSpecBuilder(template);
         this._visitables.get("template").add(this.template);
      } else {
         this.template = null;
         this._visitables.get("template").remove(this.template);
      }

      return this;
   }

   public boolean hasTemplate() {
      return this.template != null;
   }

   public TemplateNested withNewTemplate() {
      return new TemplateNested((PodTemplateSpec)null);
   }

   public TemplateNested withNewTemplateLike(PodTemplateSpec item) {
      return new TemplateNested(item);
   }

   public TemplateNested editTemplate() {
      return this.withNewTemplateLike((PodTemplateSpec)Optional.ofNullable(this.buildTemplate()).orElse((Object)null));
   }

   public TemplateNested editOrNewTemplate() {
      return this.withNewTemplateLike((PodTemplateSpec)Optional.ofNullable(this.buildTemplate()).orElse((new PodTemplateSpecBuilder()).build()));
   }

   public TemplateNested editOrNewTemplateLike(PodTemplateSpec item) {
      return this.withNewTemplateLike((PodTemplateSpec)Optional.ofNullable(this.buildTemplate()).orElse(item));
   }

   public StatefulSetUpdateStrategy buildUpdateStrategy() {
      return this.updateStrategy != null ? this.updateStrategy.build() : null;
   }

   public StatefulSetSpecFluent withUpdateStrategy(StatefulSetUpdateStrategy updateStrategy) {
      this._visitables.remove("updateStrategy");
      if (updateStrategy != null) {
         this.updateStrategy = new StatefulSetUpdateStrategyBuilder(updateStrategy);
         this._visitables.get("updateStrategy").add(this.updateStrategy);
      } else {
         this.updateStrategy = null;
         this._visitables.get("updateStrategy").remove(this.updateStrategy);
      }

      return this;
   }

   public boolean hasUpdateStrategy() {
      return this.updateStrategy != null;
   }

   public UpdateStrategyNested withNewUpdateStrategy() {
      return new UpdateStrategyNested((StatefulSetUpdateStrategy)null);
   }

   public UpdateStrategyNested withNewUpdateStrategyLike(StatefulSetUpdateStrategy item) {
      return new UpdateStrategyNested(item);
   }

   public UpdateStrategyNested editUpdateStrategy() {
      return this.withNewUpdateStrategyLike((StatefulSetUpdateStrategy)Optional.ofNullable(this.buildUpdateStrategy()).orElse((Object)null));
   }

   public UpdateStrategyNested editOrNewUpdateStrategy() {
      return this.withNewUpdateStrategyLike((StatefulSetUpdateStrategy)Optional.ofNullable(this.buildUpdateStrategy()).orElse((new StatefulSetUpdateStrategyBuilder()).build()));
   }

   public UpdateStrategyNested editOrNewUpdateStrategyLike(StatefulSetUpdateStrategy item) {
      return this.withNewUpdateStrategyLike((StatefulSetUpdateStrategy)Optional.ofNullable(this.buildUpdateStrategy()).orElse(item));
   }

   public StatefulSetSpecFluent addToVolumeClaimTemplates(int index, PersistentVolumeClaim item) {
      if (this.volumeClaimTemplates == null) {
         this.volumeClaimTemplates = new ArrayList();
      }

      PersistentVolumeClaimBuilder builder = new PersistentVolumeClaimBuilder(item);
      if (index >= 0 && index < this.volumeClaimTemplates.size()) {
         this._visitables.get("volumeClaimTemplates").add(index, builder);
         this.volumeClaimTemplates.add(index, builder);
      } else {
         this._visitables.get("volumeClaimTemplates").add(builder);
         this.volumeClaimTemplates.add(builder);
      }

      return this;
   }

   public StatefulSetSpecFluent setToVolumeClaimTemplates(int index, PersistentVolumeClaim item) {
      if (this.volumeClaimTemplates == null) {
         this.volumeClaimTemplates = new ArrayList();
      }

      PersistentVolumeClaimBuilder builder = new PersistentVolumeClaimBuilder(item);
      if (index >= 0 && index < this.volumeClaimTemplates.size()) {
         this._visitables.get("volumeClaimTemplates").set(index, builder);
         this.volumeClaimTemplates.set(index, builder);
      } else {
         this._visitables.get("volumeClaimTemplates").add(builder);
         this.volumeClaimTemplates.add(builder);
      }

      return this;
   }

   public StatefulSetSpecFluent addToVolumeClaimTemplates(PersistentVolumeClaim... items) {
      if (this.volumeClaimTemplates == null) {
         this.volumeClaimTemplates = new ArrayList();
      }

      for(PersistentVolumeClaim item : items) {
         PersistentVolumeClaimBuilder builder = new PersistentVolumeClaimBuilder(item);
         this._visitables.get("volumeClaimTemplates").add(builder);
         this.volumeClaimTemplates.add(builder);
      }

      return this;
   }

   public StatefulSetSpecFluent addAllToVolumeClaimTemplates(Collection items) {
      if (this.volumeClaimTemplates == null) {
         this.volumeClaimTemplates = new ArrayList();
      }

      for(PersistentVolumeClaim item : items) {
         PersistentVolumeClaimBuilder builder = new PersistentVolumeClaimBuilder(item);
         this._visitables.get("volumeClaimTemplates").add(builder);
         this.volumeClaimTemplates.add(builder);
      }

      return this;
   }

   public StatefulSetSpecFluent removeFromVolumeClaimTemplates(PersistentVolumeClaim... items) {
      if (this.volumeClaimTemplates == null) {
         return this;
      } else {
         for(PersistentVolumeClaim item : items) {
            PersistentVolumeClaimBuilder builder = new PersistentVolumeClaimBuilder(item);
            this._visitables.get("volumeClaimTemplates").remove(builder);
            this.volumeClaimTemplates.remove(builder);
         }

         return this;
      }
   }

   public StatefulSetSpecFluent removeAllFromVolumeClaimTemplates(Collection items) {
      if (this.volumeClaimTemplates == null) {
         return this;
      } else {
         for(PersistentVolumeClaim item : items) {
            PersistentVolumeClaimBuilder builder = new PersistentVolumeClaimBuilder(item);
            this._visitables.get("volumeClaimTemplates").remove(builder);
            this.volumeClaimTemplates.remove(builder);
         }

         return this;
      }
   }

   public StatefulSetSpecFluent removeMatchingFromVolumeClaimTemplates(Predicate predicate) {
      if (this.volumeClaimTemplates == null) {
         return this;
      } else {
         Iterator<PersistentVolumeClaimBuilder> each = this.volumeClaimTemplates.iterator();
         List visitables = this._visitables.get("volumeClaimTemplates");

         while(each.hasNext()) {
            PersistentVolumeClaimBuilder builder = (PersistentVolumeClaimBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildVolumeClaimTemplates() {
      return this.volumeClaimTemplates != null ? build(this.volumeClaimTemplates) : null;
   }

   public PersistentVolumeClaim buildVolumeClaimTemplate(int index) {
      return ((PersistentVolumeClaimBuilder)this.volumeClaimTemplates.get(index)).build();
   }

   public PersistentVolumeClaim buildFirstVolumeClaimTemplate() {
      return ((PersistentVolumeClaimBuilder)this.volumeClaimTemplates.get(0)).build();
   }

   public PersistentVolumeClaim buildLastVolumeClaimTemplate() {
      return ((PersistentVolumeClaimBuilder)this.volumeClaimTemplates.get(this.volumeClaimTemplates.size() - 1)).build();
   }

   public PersistentVolumeClaim buildMatchingVolumeClaimTemplate(Predicate predicate) {
      for(PersistentVolumeClaimBuilder item : this.volumeClaimTemplates) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingVolumeClaimTemplate(Predicate predicate) {
      for(PersistentVolumeClaimBuilder item : this.volumeClaimTemplates) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public StatefulSetSpecFluent withVolumeClaimTemplates(List volumeClaimTemplates) {
      if (this.volumeClaimTemplates != null) {
         this._visitables.get("volumeClaimTemplates").clear();
      }

      if (volumeClaimTemplates != null) {
         this.volumeClaimTemplates = new ArrayList();

         for(PersistentVolumeClaim item : volumeClaimTemplates) {
            this.addToVolumeClaimTemplates(item);
         }
      } else {
         this.volumeClaimTemplates = null;
      }

      return this;
   }

   public StatefulSetSpecFluent withVolumeClaimTemplates(PersistentVolumeClaim... volumeClaimTemplates) {
      if (this.volumeClaimTemplates != null) {
         this.volumeClaimTemplates.clear();
         this._visitables.remove("volumeClaimTemplates");
      }

      if (volumeClaimTemplates != null) {
         for(PersistentVolumeClaim item : volumeClaimTemplates) {
            this.addToVolumeClaimTemplates(item);
         }
      }

      return this;
   }

   public boolean hasVolumeClaimTemplates() {
      return this.volumeClaimTemplates != null && !this.volumeClaimTemplates.isEmpty();
   }

   public VolumeClaimTemplatesNested addNewVolumeClaimTemplate() {
      return new VolumeClaimTemplatesNested(-1, (PersistentVolumeClaim)null);
   }

   public VolumeClaimTemplatesNested addNewVolumeClaimTemplateLike(PersistentVolumeClaim item) {
      return new VolumeClaimTemplatesNested(-1, item);
   }

   public VolumeClaimTemplatesNested setNewVolumeClaimTemplateLike(int index, PersistentVolumeClaim item) {
      return new VolumeClaimTemplatesNested(index, item);
   }

   public VolumeClaimTemplatesNested editVolumeClaimTemplate(int index) {
      if (this.volumeClaimTemplates.size() <= index) {
         throw new RuntimeException("Can't edit volumeClaimTemplates. Index exceeds size.");
      } else {
         return this.setNewVolumeClaimTemplateLike(index, this.buildVolumeClaimTemplate(index));
      }
   }

   public VolumeClaimTemplatesNested editFirstVolumeClaimTemplate() {
      if (this.volumeClaimTemplates.size() == 0) {
         throw new RuntimeException("Can't edit first volumeClaimTemplates. The list is empty.");
      } else {
         return this.setNewVolumeClaimTemplateLike(0, this.buildVolumeClaimTemplate(0));
      }
   }

   public VolumeClaimTemplatesNested editLastVolumeClaimTemplate() {
      int index = this.volumeClaimTemplates.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last volumeClaimTemplates. The list is empty.");
      } else {
         return this.setNewVolumeClaimTemplateLike(index, this.buildVolumeClaimTemplate(index));
      }
   }

   public VolumeClaimTemplatesNested editMatchingVolumeClaimTemplate(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.volumeClaimTemplates.size(); ++i) {
         if (predicate.test((PersistentVolumeClaimBuilder)this.volumeClaimTemplates.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching volumeClaimTemplates. No match found.");
      } else {
         return this.setNewVolumeClaimTemplateLike(index, this.buildVolumeClaimTemplate(index));
      }
   }

   public StatefulSetSpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StatefulSetSpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StatefulSetSpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StatefulSetSpecFluent removeFromAdditionalProperties(Map map) {
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

   public StatefulSetSpecFluent withAdditionalProperties(Map additionalProperties) {
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
            StatefulSetSpecFluent that = (StatefulSetSpecFluent)o;
            if (!Objects.equals(this.minReadySeconds, that.minReadySeconds)) {
               return false;
            } else if (!Objects.equals(this.ordinals, that.ordinals)) {
               return false;
            } else if (!Objects.equals(this.persistentVolumeClaimRetentionPolicy, that.persistentVolumeClaimRetentionPolicy)) {
               return false;
            } else if (!Objects.equals(this.podManagementPolicy, that.podManagementPolicy)) {
               return false;
            } else if (!Objects.equals(this.replicas, that.replicas)) {
               return false;
            } else if (!Objects.equals(this.revisionHistoryLimit, that.revisionHistoryLimit)) {
               return false;
            } else if (!Objects.equals(this.selector, that.selector)) {
               return false;
            } else if (!Objects.equals(this.serviceName, that.serviceName)) {
               return false;
            } else if (!Objects.equals(this.template, that.template)) {
               return false;
            } else if (!Objects.equals(this.updateStrategy, that.updateStrategy)) {
               return false;
            } else if (!Objects.equals(this.volumeClaimTemplates, that.volumeClaimTemplates)) {
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
      return Objects.hash(new Object[]{this.minReadySeconds, this.ordinals, this.persistentVolumeClaimRetentionPolicy, this.podManagementPolicy, this.replicas, this.revisionHistoryLimit, this.selector, this.serviceName, this.template, this.updateStrategy, this.volumeClaimTemplates, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.minReadySeconds != null) {
         sb.append("minReadySeconds:");
         sb.append(this.minReadySeconds + ",");
      }

      if (this.ordinals != null) {
         sb.append("ordinals:");
         sb.append(this.ordinals + ",");
      }

      if (this.persistentVolumeClaimRetentionPolicy != null) {
         sb.append("persistentVolumeClaimRetentionPolicy:");
         sb.append(this.persistentVolumeClaimRetentionPolicy + ",");
      }

      if (this.podManagementPolicy != null) {
         sb.append("podManagementPolicy:");
         sb.append(this.podManagementPolicy + ",");
      }

      if (this.replicas != null) {
         sb.append("replicas:");
         sb.append(this.replicas + ",");
      }

      if (this.revisionHistoryLimit != null) {
         sb.append("revisionHistoryLimit:");
         sb.append(this.revisionHistoryLimit + ",");
      }

      if (this.selector != null) {
         sb.append("selector:");
         sb.append(this.selector + ",");
      }

      if (this.serviceName != null) {
         sb.append("serviceName:");
         sb.append(this.serviceName + ",");
      }

      if (this.template != null) {
         sb.append("template:");
         sb.append(this.template + ",");
      }

      if (this.updateStrategy != null) {
         sb.append("updateStrategy:");
         sb.append(this.updateStrategy + ",");
      }

      if (this.volumeClaimTemplates != null && !this.volumeClaimTemplates.isEmpty()) {
         sb.append("volumeClaimTemplates:");
         sb.append(this.volumeClaimTemplates + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class OrdinalsNested extends StatefulSetOrdinalsFluent implements Nested {
      StatefulSetOrdinalsBuilder builder;

      OrdinalsNested(StatefulSetOrdinals item) {
         this.builder = new StatefulSetOrdinalsBuilder(this, item);
      }

      public Object and() {
         return StatefulSetSpecFluent.this.withOrdinals(this.builder.build());
      }

      public Object endOrdinals() {
         return this.and();
      }
   }

   public class PersistentVolumeClaimRetentionPolicyNested extends StatefulSetPersistentVolumeClaimRetentionPolicyFluent implements Nested {
      StatefulSetPersistentVolumeClaimRetentionPolicyBuilder builder;

      PersistentVolumeClaimRetentionPolicyNested(StatefulSetPersistentVolumeClaimRetentionPolicy item) {
         this.builder = new StatefulSetPersistentVolumeClaimRetentionPolicyBuilder(this, item);
      }

      public Object and() {
         return StatefulSetSpecFluent.this.withPersistentVolumeClaimRetentionPolicy(this.builder.build());
      }

      public Object endPersistentVolumeClaimRetentionPolicy() {
         return this.and();
      }
   }

   public class SelectorNested extends LabelSelectorFluent implements Nested {
      LabelSelectorBuilder builder;

      SelectorNested(LabelSelector item) {
         this.builder = new LabelSelectorBuilder(this, item);
      }

      public Object and() {
         return StatefulSetSpecFluent.this.withSelector(this.builder.build());
      }

      public Object endSelector() {
         return this.and();
      }
   }

   public class TemplateNested extends PodTemplateSpecFluent implements Nested {
      PodTemplateSpecBuilder builder;

      TemplateNested(PodTemplateSpec item) {
         this.builder = new PodTemplateSpecBuilder(this, item);
      }

      public Object and() {
         return StatefulSetSpecFluent.this.withTemplate(this.builder.build());
      }

      public Object endTemplate() {
         return this.and();
      }
   }

   public class UpdateStrategyNested extends StatefulSetUpdateStrategyFluent implements Nested {
      StatefulSetUpdateStrategyBuilder builder;

      UpdateStrategyNested(StatefulSetUpdateStrategy item) {
         this.builder = new StatefulSetUpdateStrategyBuilder(this, item);
      }

      public Object and() {
         return StatefulSetSpecFluent.this.withUpdateStrategy(this.builder.build());
      }

      public Object endUpdateStrategy() {
         return this.and();
      }
   }

   public class VolumeClaimTemplatesNested extends PersistentVolumeClaimFluent implements Nested {
      PersistentVolumeClaimBuilder builder;
      int index;

      VolumeClaimTemplatesNested(int index, PersistentVolumeClaim item) {
         this.index = index;
         this.builder = new PersistentVolumeClaimBuilder(this, item);
      }

      public Object and() {
         return StatefulSetSpecFluent.this.setToVolumeClaimTemplates(this.index, this.builder.build());
      }

      public Object endVolumeClaimTemplate() {
         return this.and();
      }
   }
}
