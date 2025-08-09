package io.fabric8.kubernetes.client.dsl.internal.extensions.v1beta1;

import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.extensions.DeploymentRollback;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSet;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSetList;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.BytesLimitTerminateTimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.dsl.Loggable;
import io.fabric8.kubernetes.client.dsl.PodResource;
import io.fabric8.kubernetes.client.dsl.PrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeoutImageEditReplacePatchable;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationContext;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.RollingUpdater;
import io.fabric8.kubernetes.client.utils.internal.PodOperationUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ReplicaSetOperationsImpl extends LegacyRollableScalableResourceOperation implements TimeoutImageEditReplacePatchable {
   public ReplicaSetOperationsImpl(Client client) {
      this(new PodOperationContext(), HasMetadataOperationsImpl.defaultContext(client));
   }

   ReplicaSetOperationsImpl(PodOperationContext context, OperationContext superContext) {
      super(context, superContext.withApiGroupName("extensions").withApiGroupVersion("v1beta1").withPlural("replicasets"), ReplicaSet.class, ReplicaSetList.class);
   }

   public ReplicaSetOperationsImpl newInstance(OperationContext context) {
      return new ReplicaSetOperationsImpl(this.rollingOperationContext, context);
   }

   public ReplicaSetOperationsImpl newInstance(PodOperationContext context, OperationContext superContext) {
      return new ReplicaSetOperationsImpl(context, superContext);
   }

   public RollingUpdater getRollingUpdater(long rollingTimeout, TimeUnit rollingTimeUnit) {
      return new ReplicaSetRollingUpdater(this.context.getClient(), this.getNamespace(), rollingTimeUnit.toMillis(rollingTimeout), (long)this.getRequestConfig().getLoggingInterval());
   }

   public Status rollback(DeploymentRollback deploymentRollback) {
      throw new KubernetesClientException("rollback not supported in case of ReplicaSets");
   }

   public String getLog(boolean isPretty) {
      StringBuilder stringBuilder = new StringBuilder();

      for(PodResource podOperation : (new ReplicaSetOperationsImpl(this.rollingOperationContext.withPrettyOutput(isPretty), this.context)).doGetLog()) {
         stringBuilder.append(podOperation.getLog(isPretty));
      }

      return stringBuilder.toString();
   }

   private List doGetLog() {
      ReplicaSet replicaSet = (ReplicaSet)this.requireFromServer();
      return PodOperationUtil.getPodOperationsForController(this.context, this.rollingOperationContext, replicaSet.getMetadata().getUid(), getReplicaSetSelectorLabels(replicaSet));
   }

   public Reader getLogReader() {
      return PodOperationUtil.getLogReader(this.doGetLog());
   }

   public InputStream getLogInputStream() {
      return PodOperationUtil.getLogInputStream(this.doGetLog());
   }

   public LogWatch watchLog(OutputStream out) {
      return PodOperationUtil.watchLog(this.doGetLog(), out);
   }

   static Map getReplicaSetSelectorLabels(ReplicaSet replicaSet) {
      Map<String, String> labels = new HashMap();
      if (replicaSet != null && replicaSet.getSpec() != null && replicaSet.getSpec().getSelector() != null) {
         labels.putAll(replicaSet.getSpec().getSelector().getMatchLabels());
      }

      return labels;
   }

   protected List getContainers(ReplicaSet value) {
      return value.getSpec().getTemplate().getSpec().getContainers();
   }

   public TimeTailPrettyLoggable limitBytes(int limitBytes) {
      return new ReplicaSetOperationsImpl(this.rollingOperationContext.withLimitBytes(limitBytes), this.context);
   }

   public TimeTailPrettyLoggable terminated() {
      return new ReplicaSetOperationsImpl(this.rollingOperationContext.withTerminatedStatus(true), this.context);
   }

   public Loggable withPrettyOutput() {
      return new ReplicaSetOperationsImpl(this.rollingOperationContext.withPrettyOutput(true), this.context);
   }

   public PrettyLoggable tailingLines(int lines) {
      return new ReplicaSetOperationsImpl(this.rollingOperationContext.withTailingLines(lines), this.context);
   }

   public TailPrettyLoggable sinceTime(String timestamp) {
      return new ReplicaSetOperationsImpl(this.rollingOperationContext.withSinceTimestamp(timestamp), this.context);
   }

   public TailPrettyLoggable sinceSeconds(int seconds) {
      return new ReplicaSetOperationsImpl(this.rollingOperationContext.withSinceSeconds(seconds), this.context);
   }

   public BytesLimitTerminateTimeTailPrettyLoggable usingTimestamps() {
      return new ReplicaSetOperationsImpl(this.rollingOperationContext.withTimestamps(true), this.context);
   }
}
