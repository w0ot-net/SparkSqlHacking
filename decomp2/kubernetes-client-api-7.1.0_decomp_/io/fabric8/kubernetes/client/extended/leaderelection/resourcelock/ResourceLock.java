package io.fabric8.kubernetes.client.extended.leaderelection.resourcelock;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.PatchContext;
import io.fabric8.kubernetes.client.dsl.base.PatchType;
import java.util.Objects;

public abstract class ResourceLock implements Lock {
   private final ObjectMeta meta;
   private final String identity;
   private HasMetadata resource;

   public ResourceLock(String namespace, String name, String identity) {
      this(((ObjectMetaBuilder)((ObjectMetaBuilder)(new ObjectMetaBuilder()).withNamespace(namespace)).withName(name)).build(), identity);
   }

   public ResourceLock(ObjectMeta meta, String identity) {
      this.meta = meta;
      Objects.requireNonNull(meta.getNamespace(), "namespace is required");
      Objects.requireNonNull(meta.getName(), "name is required");
      this.identity = (String)Objects.requireNonNull(identity, "identity is required");
   }

   protected abstract Class getKind();

   public synchronized LeaderElectionRecord get(KubernetesClient client) {
      this.resource = (HasMetadata)((Resource)((NonNamespaceOperation)client.resources(this.getKind()).inNamespace(this.meta.getNamespace())).withName(this.meta.getName())).get();
      return this.resource != null ? this.toRecord(this.resource) : null;
   }

   public synchronized void create(KubernetesClient client, LeaderElectionRecord leaderElectionRecord) {
      this.resource = (HasMetadata)client.resource(this.toResource(leaderElectionRecord, this.getObjectMeta((String)null))).create();
   }

   public synchronized void update(KubernetesClient client, LeaderElectionRecord leaderElectionRecord) {
      Objects.requireNonNull(this.resource, "get or create must be called first");
      client.resource(this.toResource(leaderElectionRecord, this.getObjectMeta(this.resource.getMetadata().getResourceVersion()))).patch(PatchContext.of(PatchType.JSON_MERGE));
   }

   protected abstract HasMetadata toResource(LeaderElectionRecord var1, ObjectMetaBuilder var2);

   protected abstract LeaderElectionRecord toRecord(HasMetadata var1);

   protected ObjectMetaBuilder getObjectMeta(String version) {
      return (ObjectMetaBuilder)(new ObjectMetaBuilder(this.meta)).withResourceVersion(version);
   }

   public String identity() {
      return this.identity;
   }

   public String describe() {
      return String.format("%sLock: %s - %s (%s)", this.getKind().getSimpleName(), this.meta.getNamespace(), this.meta.getName(), this.identity);
   }

   void setResource(HasMetadata resource) {
      this.resource = resource;
   }
}
