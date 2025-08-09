package io.fabric8.kubernetes.client.dsl.internal.apps.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodCondition;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.PodResource;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;
import io.fabric8.kubernetes.client.dsl.base.PatchContext;
import io.fabric8.kubernetes.client.dsl.base.PatchType;
import io.fabric8.kubernetes.client.dsl.internal.PatchUtils;
import io.fabric8.kubernetes.client.dsl.internal.core.v1.PodOperationsImpl;
import io.fabric8.kubernetes.client.impl.BaseClient;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import io.fabric8.kubernetes.client.utils.Utils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RollingUpdater {
   public static final String DEPLOYMENT_KEY = "deployment";
   private static final Long DEFAULT_SERVER_GC_WAIT_TIMEOUT = 60000L;
   private static final transient Logger LOG = LoggerFactory.getLogger(RollingUpdater.class);
   protected final Client client;
   protected final String namespace;
   private final long rollingTimeoutMillis;
   private final long loggingIntervalMillis;

   protected RollingUpdater(Client client, String namespace, long rollingTimeoutMillis, long loggingIntervalMillis) {
      this.client = client;
      this.namespace = namespace;
      this.rollingTimeoutMillis = rollingTimeoutMillis;
      this.loggingIntervalMillis = loggingIntervalMillis;
   }

   protected abstract HasMetadata createClone(HasMetadata var1, String var2, String var3);

   protected abstract FilterWatchListDeletable selectedPodLister(HasMetadata var1);

   protected abstract HasMetadata updateDeploymentKey(String var1, String var2);

   protected abstract HasMetadata removeDeploymentKey(String var1);

   protected abstract int getReplicas(HasMetadata var1);

   protected abstract HasMetadata setReplicas(HasMetadata var1, int var2);

   public HasMetadata rollUpdate(HasMetadata oldObj, HasMetadata newObj) {
      try {
         String namespace = oldObj.getMetadata().getNamespace();
         String oldName = oldObj.getMetadata().getName();
         String oldDeploymentHash = this.md5sum(oldObj);
         PodList oldPods = (PodList)this.selectedPodLister(oldObj).list();

         for(Pod pod : oldPods.getItems()) {
            try {
               Pod old = (Pod)((PodResource)((NonNamespaceOperation)this.pods().inNamespace(namespace)).withName(pod.getMetadata().getName())).get();
               Pod updated = ((PodBuilder)((PodFluent.MetadataNested)(new PodBuilder(old)).editMetadata().addToLabels("deployment", oldDeploymentHash)).endMetadata()).build();
               ((PodResource)((NonNamespaceOperation)this.pods().inNamespace(namespace)).withName(pod.getMetadata().getName())).replace(updated);
            } catch (KubernetesClientException e) {
               LOG.warn("Unable to add deployment key to pod: {}", e.getMessage());
            }
         }

         oldObj = (T)this.updateDeploymentKey(oldName, oldDeploymentHash);
         String newDeploymentHash = this.md5sum(newObj);
         String newName = newObj.getMetadata().getName();
         if (newName == null || newName.equals(oldName)) {
            newName = newName + "-" + newDeploymentHash;
         }

         T clonedObj = (T)this.createClone(newObj, newName, newDeploymentHash);
         T createdObj = (T)((HasMetadata)((NonNamespaceOperation)this.resources().inNamespace(namespace)).create(clonedObj));
         int oldReplicas = this.getReplicas(oldObj);

         while(this.getReplicas(createdObj) < this.getReplicas(newObj)) {
            int newReplicas = this.getReplicas(createdObj) + 1;
            ((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(namespace)).withName(createdObj.getMetadata().getName())).scale(newReplicas, true);
            this.waitUntilPodsAreReady(createdObj, namespace, newReplicas);
            createdObj = (T)this.setReplicas(createdObj, newReplicas);
            if (oldReplicas > 0) {
               RollableScalableResource var10000 = (RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(namespace)).withName(oldName);
               --oldReplicas;
               var10000.scale(oldReplicas, true);
               this.waitUntilPodsAreReady(oldObj, namespace, oldReplicas);
            }
         }

         ((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(namespace)).withName(oldName)).delete();
         if (Objects.equals(oldName, newObj.getMetadata().getName())) {
            ((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(namespace)).withName(newName)).cascading(false).delete();
            this.waitUntilDeleted(namespace, newName);
            createdObj.getMetadata().setResourceVersion((String)null);
            createdObj.getMetadata().setName(oldName);
            createdObj = (T)((HasMetadata)((NonNamespaceOperation)this.resources().inNamespace(namespace)).create(createdObj));
            createdObj = (T)this.removeDeploymentKey(createdObj.getMetadata().getName());
         }

         return createdObj;
      } catch (JsonProcessingException | NoSuchAlgorithmException e) {
         throw new KubernetesClientException("Could not calculate MD5 of RC", e);
      }
   }

   private static Object applyPatch(Resource resource, List list, KubernetesSerialization serialization) {
      return resource.patch(PatchContext.of(PatchType.JSON), serialization.asJson(list));
   }

   public static HasMetadata resume(RollableScalableResourceOperation resource) {
      return (HasMetadata)applyPatch(resource, requestPayLoadForRolloutResume(), resource.getKubernetesSerialization());
   }

   public static HasMetadata pause(RollableScalableResourceOperation resource) {
      return (HasMetadata)applyPatch(resource, requestPayLoadForRolloutPause(), resource.getKubernetesSerialization());
   }

   public static HasMetadata restart(RollableScalableResourceOperation resource) {
      return (HasMetadata)applyPatch(resource, requestPayLoadForRolloutRestart(), resource.getKubernetesSerialization());
   }

   public static List requestPayLoadForRolloutPause() {
      HashMap<String, Object> patch = new HashMap();
      patch.put("op", "add");
      patch.put("path", "/spec/paused");
      patch.put("value", true);
      return Collections.singletonList(patch);
   }

   public static List requestPayLoadForRolloutResume() {
      HashMap<String, Object> patch = new HashMap();
      patch.put("op", "remove");
      patch.put("path", "/spec/paused");
      return Collections.singletonList(patch);
   }

   public static List requestPayLoadForRolloutRestart() {
      HashMap<String, Object> patch = new HashMap();
      HashMap<String, Object> value = new HashMap();
      patch.put("op", "replace");
      patch.put("path", "/spec/template/metadata/annotations");
      value.put("kubectl.kubernetes.io/restartedAt", (new Date()).toInstant().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
      patch.put("value", value);
      return Collections.singletonList(patch);
   }

   private void waitUntilPodsAreReady(HasMetadata obj, String namespace, int requiredPodCount) {
      AtomicInteger podCount = new AtomicInteger(0);
      CompletableFuture<List<Pod>> future = this.selectedPodLister(obj).informOnCondition((items) -> {
         int count = 0;

         for(Pod item : items) {
            for(PodCondition c : item.getStatus().getConditions()) {
               if (c.getType().equals("Ready") && c.getStatus().equals("True")) {
                  ++count;
               }
            }
         }

         podCount.set(count);
         return count == requiredPodCount;
      });
      Future<?> logger = Utils.scheduleAtFixedRate(Runnable::run, () -> LOG.debug("Only {}/{} pod(s) ready for {}: {} in namespace: {} seconds so waiting...", new Object[]{podCount.get(), requiredPodCount, obj.getKind(), obj.getMetadata().getName(), namespace}), 0L, this.loggingIntervalMillis, TimeUnit.MILLISECONDS);

      try {
         if (!Utils.waitUntilReady(future, this.rollingTimeoutMillis, TimeUnit.MILLISECONDS)) {
            LOG.warn("Only {}/{} pod(s) ready for {}: {} in namespace: {}  after waiting for {} seconds so giving up", new Object[]{podCount.get(), requiredPodCount, obj.getKind(), obj.getMetadata().getName(), namespace, TimeUnit.MILLISECONDS.toSeconds(this.rollingTimeoutMillis)});
         }
      } finally {
         future.cancel(true);
         logger.cancel(true);
      }

   }

   private void waitUntilDeleted(String namespace, String name) {
      Future<?> logger = Utils.scheduleAtFixedRate(Runnable::run, () -> LOG.debug("Found resource {}/{} not yet deleted on server, so waiting...", namespace, name), 0L, this.loggingIntervalMillis, TimeUnit.MILLISECONDS);

      try {
         ((RollableScalableResource)((NonNamespaceOperation)this.resources().inNamespace(namespace)).withName(name)).waitUntilCondition(Objects::isNull, DEFAULT_SERVER_GC_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
      } finally {
         logger.cancel(true);
      }

   }

   private String md5sum(HasMetadata obj) throws NoSuchAlgorithmException, JsonProcessingException {
      byte[] digest = MessageDigest.getInstance("MD5").digest(PatchUtils.withoutRuntimeState(obj, PatchUtils.Format.YAML, true, ((BaseClient)this.client.adapt(BaseClient.class)).getKubernetesSerialization()).getBytes());
      BigInteger i = new BigInteger(1, digest);
      return String.format("%1$032x", i);
   }

   protected abstract MixedOperation resources();

   protected MixedOperation pods() {
      return new PodOperationsImpl(this.client);
   }

   protected FilterWatchListDeletable selectedPodLister(LabelSelector selector) {
      return (FilterWatchListDeletable)((NonNamespaceOperation)this.pods().inNamespace(this.namespace)).withLabelSelector(selector);
   }
}
