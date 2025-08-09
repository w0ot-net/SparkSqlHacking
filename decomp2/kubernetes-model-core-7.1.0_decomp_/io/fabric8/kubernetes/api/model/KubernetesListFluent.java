package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.builder.VisitableBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class KubernetesListFluent extends BaseFluent {
   private String apiVersion;
   private ArrayList items = new ArrayList();
   private String kind;
   private ListMetaBuilder metadata;

   public KubernetesListFluent() {
   }

   public KubernetesListFluent(KubernetesList instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(KubernetesList instance) {
      instance = instance != null ? instance : new KubernetesList();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withItems(instance.getItems());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public KubernetesListFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public KubernetesListFluent addToItems(VisitableBuilder builder) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      this._visitables.get("items").add(builder);
      this.items.add(builder);
      return this;
   }

   public KubernetesListFluent addToItems(int index, VisitableBuilder builder) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").add(index, builder);
         this.items.add(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public KubernetesListFluent addToItems(int index, HasMetadata item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      VisitableBuilder<? extends HasMetadata, ?> builder = builder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").add(index, builder);
         this.items.add(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public KubernetesListFluent setToItems(int index, HasMetadata item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      VisitableBuilder<? extends HasMetadata, ?> builder = builder(item);
      if (index >= 0 && index < this.items.size()) {
         this._visitables.get("items").set(index, builder);
         this.items.set(index, builder);
      } else {
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public KubernetesListFluent addToItems(HasMetadata... items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(HasMetadata item : items) {
         VisitableBuilder<? extends HasMetadata, ?> builder = builder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public KubernetesListFluent addAllToItems(Collection items) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      for(HasMetadata item : items) {
         VisitableBuilder<? extends HasMetadata, ?> builder = builder(item);
         this._visitables.get("items").add(builder);
         this.items.add(builder);
      }

      return this;
   }

   public KubernetesListFluent removeFromItems(VisitableBuilder builder) {
      if (this.items == null) {
         return this;
      } else {
         this._visitables.get("items").remove(builder);
         this.items.remove(builder);
         return this;
      }
   }

   public KubernetesListFluent removeFromItems(HasMetadata... items) {
      if (this.items == null) {
         return this;
      } else {
         for(HasMetadata item : items) {
            VisitableBuilder<? extends HasMetadata, ?> builder = builder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public KubernetesListFluent removeAllFromItems(Collection items) {
      if (this.items == null) {
         return this;
      } else {
         for(HasMetadata item : items) {
            VisitableBuilder<? extends HasMetadata, ?> builder = builder(item);
            this._visitables.get("items").remove(builder);
            this.items.remove(builder);
         }

         return this;
      }
   }

   public KubernetesListFluent removeMatchingFromItems(Predicate predicate) {
      if (this.items == null) {
         return this;
      } else {
         Iterator<VisitableBuilder<? extends HasMetadata, ?>> each = this.items.iterator();
         List visitables = this._visitables.get("items");

         while(each.hasNext()) {
            VisitableBuilder<? extends HasMetadata, ?> builder = (VisitableBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildItems() {
      return build(this.items);
   }

   public HasMetadata buildItem(int index) {
      return (HasMetadata)((VisitableBuilder)this.items.get(index)).build();
   }

   public HasMetadata buildFirstItem() {
      return (HasMetadata)((VisitableBuilder)this.items.get(0)).build();
   }

   public HasMetadata buildLastItem() {
      return (HasMetadata)((VisitableBuilder)this.items.get(this.items.size() - 1)).build();
   }

   public HasMetadata buildMatchingItem(Predicate predicate) {
      for(VisitableBuilder item : this.items) {
         if (predicate.test(item)) {
            return (HasMetadata)item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingItem(Predicate predicate) {
      for(VisitableBuilder item : this.items) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public KubernetesListFluent withItems(List items) {
      if (items != null) {
         this.items = new ArrayList();

         for(HasMetadata item : items) {
            this.addToItems(item);
         }
      } else {
         this.items = null;
      }

      return this;
   }

   public KubernetesListFluent withItems(HasMetadata... items) {
      if (this.items != null) {
         this.items.clear();
         this._visitables.remove("items");
      }

      if (items != null) {
         for(HasMetadata item : items) {
            this.addToItems(item);
         }
      }

      return this;
   }

   public boolean hasItems() {
      return this.items != null && !this.items.isEmpty();
   }

   public LimitRangeItemsNested addNewLimitRangeItem() {
      return new LimitRangeItemsNested(-1, (LimitRange)null);
   }

   public LimitRangeItemsNested addNewLimitRangeItemLike(LimitRange item) {
      return new LimitRangeItemsNested(-1, item);
   }

   public LimitRangeItemsNested setNewLimitRangeItemLike(int index, LimitRange item) {
      return new LimitRangeItemsNested(index, item);
   }

   public APIServiceItemsNested addNewAPIServiceItem() {
      return new APIServiceItemsNested(-1, (APIService)null);
   }

   public APIServiceItemsNested addNewAPIServiceItemLike(APIService item) {
      return new APIServiceItemsNested(-1, item);
   }

   public APIServiceItemsNested setNewAPIServiceItemLike(int index, APIService item) {
      return new APIServiceItemsNested(index, item);
   }

   public NamespaceItemsNested addNewNamespaceItem() {
      return new NamespaceItemsNested(-1, (Namespace)null);
   }

   public NamespaceItemsNested addNewNamespaceItemLike(Namespace item) {
      return new NamespaceItemsNested(-1, item);
   }

   public NamespaceItemsNested setNewNamespaceItemLike(int index, Namespace item) {
      return new NamespaceItemsNested(index, item);
   }

   public ServiceItemsNested addNewServiceItem() {
      return new ServiceItemsNested(-1, (Service)null);
   }

   public ServiceItemsNested addNewServiceItemLike(Service item) {
      return new ServiceItemsNested(-1, item);
   }

   public ServiceItemsNested setNewServiceItemLike(int index, Service item) {
      return new ServiceItemsNested(index, item);
   }

   public ReplicationControllerItemsNested addNewReplicationControllerItem() {
      return new ReplicationControllerItemsNested(-1, (ReplicationController)null);
   }

   public ReplicationControllerItemsNested addNewReplicationControllerItemLike(ReplicationController item) {
      return new ReplicationControllerItemsNested(-1, item);
   }

   public ReplicationControllerItemsNested setNewReplicationControllerItemLike(int index, ReplicationController item) {
      return new ReplicationControllerItemsNested(index, item);
   }

   public PodTemplateItemsNested addNewPodTemplateItem() {
      return new PodTemplateItemsNested(-1, (PodTemplate)null);
   }

   public PodTemplateItemsNested addNewPodTemplateItemLike(PodTemplate item) {
      return new PodTemplateItemsNested(-1, item);
   }

   public PodTemplateItemsNested setNewPodTemplateItemLike(int index, PodTemplate item) {
      return new PodTemplateItemsNested(index, item);
   }

   public EventItemsNested addNewEventItem() {
      return new EventItemsNested(-1, (Event)null);
   }

   public EventItemsNested addNewEventItemLike(Event item) {
      return new EventItemsNested(-1, item);
   }

   public EventItemsNested setNewEventItemLike(int index, Event item) {
      return new EventItemsNested(index, item);
   }

   public PersistentVolumeItemsNested addNewPersistentVolumeItem() {
      return new PersistentVolumeItemsNested(-1, (PersistentVolume)null);
   }

   public PersistentVolumeItemsNested addNewPersistentVolumeItemLike(PersistentVolume item) {
      return new PersistentVolumeItemsNested(-1, item);
   }

   public PersistentVolumeItemsNested setNewPersistentVolumeItemLike(int index, PersistentVolume item) {
      return new PersistentVolumeItemsNested(index, item);
   }

   public PersistentVolumeClaimItemsNested addNewPersistentVolumeClaimItem() {
      return new PersistentVolumeClaimItemsNested(-1, (PersistentVolumeClaim)null);
   }

   public PersistentVolumeClaimItemsNested addNewPersistentVolumeClaimItemLike(PersistentVolumeClaim item) {
      return new PersistentVolumeClaimItemsNested(-1, item);
   }

   public PersistentVolumeClaimItemsNested setNewPersistentVolumeClaimItemLike(int index, PersistentVolumeClaim item) {
      return new PersistentVolumeClaimItemsNested(index, item);
   }

   public GenericKubernetesResourceItemsNested addNewGenericKubernetesResourceItem() {
      return new GenericKubernetesResourceItemsNested(-1, (GenericKubernetesResource)null);
   }

   public GenericKubernetesResourceItemsNested addNewGenericKubernetesResourceItemLike(GenericKubernetesResource item) {
      return new GenericKubernetesResourceItemsNested(-1, item);
   }

   public GenericKubernetesResourceItemsNested setNewGenericKubernetesResourceItemLike(int index, GenericKubernetesResource item) {
      return new GenericKubernetesResourceItemsNested(index, item);
   }

   public EndpointsItemsNested addNewEndpointsItem() {
      return new EndpointsItemsNested(-1, (Endpoints)null);
   }

   public EndpointsItemsNested addNewEndpointsItemLike(Endpoints item) {
      return new EndpointsItemsNested(-1, item);
   }

   public EndpointsItemsNested setNewEndpointsItemLike(int index, Endpoints item) {
      return new EndpointsItemsNested(index, item);
   }

   public PodItemsNested addNewPodItem() {
      return new PodItemsNested(-1, (Pod)null);
   }

   public PodItemsNested addNewPodItemLike(Pod item) {
      return new PodItemsNested(-1, item);
   }

   public PodItemsNested setNewPodItemLike(int index, Pod item) {
      return new PodItemsNested(index, item);
   }

   public ConfigMapItemsNested addNewConfigMapItem() {
      return new ConfigMapItemsNested(-1, (ConfigMap)null);
   }

   public ConfigMapItemsNested addNewConfigMapItemLike(ConfigMap item) {
      return new ConfigMapItemsNested(-1, item);
   }

   public ConfigMapItemsNested setNewConfigMapItemLike(int index, ConfigMap item) {
      return new ConfigMapItemsNested(index, item);
   }

   public ComponentStatusItemsNested addNewComponentStatusItem() {
      return new ComponentStatusItemsNested(-1, (ComponentStatus)null);
   }

   public ComponentStatusItemsNested addNewComponentStatusItemLike(ComponentStatus item) {
      return new ComponentStatusItemsNested(-1, item);
   }

   public ComponentStatusItemsNested setNewComponentStatusItemLike(int index, ComponentStatus item) {
      return new ComponentStatusItemsNested(index, item);
   }

   public BindingItemsNested addNewBindingItem() {
      return new BindingItemsNested(-1, (Binding)null);
   }

   public BindingItemsNested addNewBindingItemLike(Binding item) {
      return new BindingItemsNested(-1, item);
   }

   public BindingItemsNested setNewBindingItemLike(int index, Binding item) {
      return new BindingItemsNested(index, item);
   }

   public ResourceQuotaItemsNested addNewResourceQuotaItem() {
      return new ResourceQuotaItemsNested(-1, (ResourceQuota)null);
   }

   public ResourceQuotaItemsNested addNewResourceQuotaItemLike(ResourceQuota item) {
      return new ResourceQuotaItemsNested(-1, item);
   }

   public ResourceQuotaItemsNested setNewResourceQuotaItemLike(int index, ResourceQuota item) {
      return new ResourceQuotaItemsNested(index, item);
   }

   public SecretItemsNested addNewSecretItem() {
      return new SecretItemsNested(-1, (Secret)null);
   }

   public SecretItemsNested addNewSecretItemLike(Secret item) {
      return new SecretItemsNested(-1, item);
   }

   public SecretItemsNested setNewSecretItemLike(int index, Secret item) {
      return new SecretItemsNested(index, item);
   }

   public NodeItemsNested addNewNodeItem() {
      return new NodeItemsNested(-1, (Node)null);
   }

   public NodeItemsNested addNewNodeItemLike(Node item) {
      return new NodeItemsNested(-1, item);
   }

   public NodeItemsNested setNewNodeItemLike(int index, Node item) {
      return new NodeItemsNested(index, item);
   }

   public ServiceAccountItemsNested addNewServiceAccountItem() {
      return new ServiceAccountItemsNested(-1, (ServiceAccount)null);
   }

   public ServiceAccountItemsNested addNewServiceAccountItemLike(ServiceAccount item) {
      return new ServiceAccountItemsNested(-1, item);
   }

   public ServiceAccountItemsNested setNewServiceAccountItemLike(int index, ServiceAccount item) {
      return new ServiceAccountItemsNested(index, item);
   }

   public String getKind() {
      return this.kind;
   }

   public KubernetesListFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ListMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public KubernetesListFluent withMetadata(ListMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ListMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public KubernetesListFluent withNewMetadata(String _continue, Long remainingItemCount, String resourceVersion, String selfLink) {
      return this.withMetadata(new ListMeta(_continue, remainingItemCount, resourceVersion, selfLink));
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ListMeta)null);
   }

   public MetadataNested withNewMetadataLike(ListMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ListMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ListMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ListMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ListMeta item) {
      return this.withNewMetadataLike((ListMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            KubernetesListFluent that = (KubernetesListFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.items, that.items)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else {
               return Objects.equals(this.metadata, that.metadata);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.apiVersion, this.items, this.kind, this.metadata, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.items != null && !this.items.isEmpty()) {
         sb.append("items:");
         sb.append(this.items + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata);
      }

      sb.append("}");
      return sb.toString();
   }

   protected static VisitableBuilder builder(Object item) {
      switch (item.getClass().getName()) {
         case "io.fabric8.kubernetes.api.model.LimitRange":
            return new LimitRangeBuilder((LimitRange)item);
         case "io.fabric8.kubernetes.api.model.APIService":
            return new APIServiceBuilder((APIService)item);
         case "io.fabric8.kubernetes.api.model.Namespace":
            return new NamespaceBuilder((Namespace)item);
         case "io.fabric8.kubernetes.api.model.Service":
            return new ServiceBuilder((Service)item);
         case "io.fabric8.kubernetes.api.model.ReplicationController":
            return new ReplicationControllerBuilder((ReplicationController)item);
         case "io.fabric8.kubernetes.api.model.PodTemplate":
            return new PodTemplateBuilder((PodTemplate)item);
         case "io.fabric8.kubernetes.api.model.Event":
            return new EventBuilder((Event)item);
         case "io.fabric8.kubernetes.api.model.PersistentVolume":
            return new PersistentVolumeBuilder((PersistentVolume)item);
         case "io.fabric8.kubernetes.api.model.PersistentVolumeClaim":
            return new PersistentVolumeClaimBuilder((PersistentVolumeClaim)item);
         case "io.fabric8.kubernetes.api.model.GenericKubernetesResource":
            return new GenericKubernetesResourceBuilder((GenericKubernetesResource)item);
         case "io.fabric8.kubernetes.api.model.Endpoints":
            return new EndpointsBuilder((Endpoints)item);
         case "io.fabric8.kubernetes.api.model.Pod":
            return new PodBuilder((Pod)item);
         case "io.fabric8.kubernetes.api.model.ConfigMap":
            return new ConfigMapBuilder((ConfigMap)item);
         case "io.fabric8.kubernetes.api.model.ComponentStatus":
            return new ComponentStatusBuilder((ComponentStatus)item);
         case "io.fabric8.kubernetes.api.model.Binding":
            return new BindingBuilder((Binding)item);
         case "io.fabric8.kubernetes.api.model.ResourceQuota":
            return new ResourceQuotaBuilder((ResourceQuota)item);
         case "io.fabric8.kubernetes.api.model.Secret":
            return new SecretBuilder((Secret)item);
         case "io.fabric8.kubernetes.api.model.Node":
            return new NodeBuilder((Node)item);
         case "io.fabric8.kubernetes.api.model.ServiceAccount":
            return new ServiceAccountBuilder((ServiceAccount)item);
         default:
            return builderOf(item);
      }
   }

   public class LimitRangeItemsNested extends LimitRangeFluent implements Nested {
      LimitRangeBuilder builder;
      int index;

      LimitRangeItemsNested(int index, LimitRange item) {
         this.index = index;
         this.builder = new LimitRangeBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endLimitRangeItem() {
         return this.and();
      }
   }

   public class APIServiceItemsNested extends APIServiceFluent implements Nested {
      APIServiceBuilder builder;
      int index;

      APIServiceItemsNested(int index, APIService item) {
         this.index = index;
         this.builder = new APIServiceBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endAPIServiceItem() {
         return this.and();
      }
   }

   public class NamespaceItemsNested extends NamespaceFluent implements Nested {
      NamespaceBuilder builder;
      int index;

      NamespaceItemsNested(int index, Namespace item) {
         this.index = index;
         this.builder = new NamespaceBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endNamespaceItem() {
         return this.and();
      }
   }

   public class ServiceItemsNested extends ServiceFluent implements Nested {
      ServiceBuilder builder;
      int index;

      ServiceItemsNested(int index, Service item) {
         this.index = index;
         this.builder = new ServiceBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endServiceItem() {
         return this.and();
      }
   }

   public class ReplicationControllerItemsNested extends ReplicationControllerFluent implements Nested {
      ReplicationControllerBuilder builder;
      int index;

      ReplicationControllerItemsNested(int index, ReplicationController item) {
         this.index = index;
         this.builder = new ReplicationControllerBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endReplicationControllerItem() {
         return this.and();
      }
   }

   public class PodTemplateItemsNested extends PodTemplateFluent implements Nested {
      PodTemplateBuilder builder;
      int index;

      PodTemplateItemsNested(int index, PodTemplate item) {
         this.index = index;
         this.builder = new PodTemplateBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endPodTemplateItem() {
         return this.and();
      }
   }

   public class EventItemsNested extends EventFluent implements Nested {
      EventBuilder builder;
      int index;

      EventItemsNested(int index, Event item) {
         this.index = index;
         this.builder = new EventBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endEventItem() {
         return this.and();
      }
   }

   public class PersistentVolumeItemsNested extends PersistentVolumeFluent implements Nested {
      PersistentVolumeBuilder builder;
      int index;

      PersistentVolumeItemsNested(int index, PersistentVolume item) {
         this.index = index;
         this.builder = new PersistentVolumeBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endPersistentVolumeItem() {
         return this.and();
      }
   }

   public class PersistentVolumeClaimItemsNested extends PersistentVolumeClaimFluent implements Nested {
      PersistentVolumeClaimBuilder builder;
      int index;

      PersistentVolumeClaimItemsNested(int index, PersistentVolumeClaim item) {
         this.index = index;
         this.builder = new PersistentVolumeClaimBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endPersistentVolumeClaimItem() {
         return this.and();
      }
   }

   public class GenericKubernetesResourceItemsNested extends GenericKubernetesResourceFluent implements Nested {
      GenericKubernetesResourceBuilder builder;
      int index;

      GenericKubernetesResourceItemsNested(int index, GenericKubernetesResource item) {
         this.index = index;
         this.builder = new GenericKubernetesResourceBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endGenericKubernetesResourceItem() {
         return this.and();
      }
   }

   public class EndpointsItemsNested extends EndpointsFluent implements Nested {
      EndpointsBuilder builder;
      int index;

      EndpointsItemsNested(int index, Endpoints item) {
         this.index = index;
         this.builder = new EndpointsBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endEndpointsItem() {
         return this.and();
      }
   }

   public class PodItemsNested extends PodFluent implements Nested {
      PodBuilder builder;
      int index;

      PodItemsNested(int index, Pod item) {
         this.index = index;
         this.builder = new PodBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endPodItem() {
         return this.and();
      }
   }

   public class ConfigMapItemsNested extends ConfigMapFluent implements Nested {
      ConfigMapBuilder builder;
      int index;

      ConfigMapItemsNested(int index, ConfigMap item) {
         this.index = index;
         this.builder = new ConfigMapBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endConfigMapItem() {
         return this.and();
      }
   }

   public class ComponentStatusItemsNested extends ComponentStatusFluent implements Nested {
      ComponentStatusBuilder builder;
      int index;

      ComponentStatusItemsNested(int index, ComponentStatus item) {
         this.index = index;
         this.builder = new ComponentStatusBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endComponentStatusItem() {
         return this.and();
      }
   }

   public class BindingItemsNested extends BindingFluent implements Nested {
      BindingBuilder builder;
      int index;

      BindingItemsNested(int index, Binding item) {
         this.index = index;
         this.builder = new BindingBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endBindingItem() {
         return this.and();
      }
   }

   public class ResourceQuotaItemsNested extends ResourceQuotaFluent implements Nested {
      ResourceQuotaBuilder builder;
      int index;

      ResourceQuotaItemsNested(int index, ResourceQuota item) {
         this.index = index;
         this.builder = new ResourceQuotaBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endResourceQuotaItem() {
         return this.and();
      }
   }

   public class SecretItemsNested extends SecretFluent implements Nested {
      SecretBuilder builder;
      int index;

      SecretItemsNested(int index, Secret item) {
         this.index = index;
         this.builder = new SecretBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endSecretItem() {
         return this.and();
      }
   }

   public class NodeItemsNested extends NodeFluent implements Nested {
      NodeBuilder builder;
      int index;

      NodeItemsNested(int index, Node item) {
         this.index = index;
         this.builder = new NodeBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endNodeItem() {
         return this.and();
      }
   }

   public class ServiceAccountItemsNested extends ServiceAccountFluent implements Nested {
      ServiceAccountBuilder builder;
      int index;

      ServiceAccountItemsNested(int index, ServiceAccount item) {
         this.index = index;
         this.builder = new ServiceAccountBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.setToItems(this.index, this.builder.build());
      }

      public Object endServiceAccountItem() {
         return this.and();
      }
   }

   public class MetadataNested extends ListMetaFluent implements Nested {
      ListMetaBuilder builder;

      MetadataNested(ListMeta item) {
         this.builder = new ListMetaBuilder(this, item);
      }

      public Object and() {
         return KubernetesListFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
