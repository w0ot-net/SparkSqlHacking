package io.fabric8.kubernetes.client.dsl.internal.core.v1;

import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ReplicationControllerList;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.extensions.DeploymentRollback;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.BytesLimitTerminateTimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.dsl.Loggable;
import io.fabric8.kubernetes.client.dsl.PrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeoutImageEditReplacePatchable;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationContext;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.RollableScalableResourceOperation;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.RollingUpdater;
import io.fabric8.kubernetes.client.utils.internal.PodOperationUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ReplicationControllerOperationsImpl extends RollableScalableResourceOperation implements TimeoutImageEditReplacePatchable {
   public ReplicationControllerOperationsImpl(Client client) {
      this(new PodOperationContext(), HasMetadataOperationsImpl.defaultContext(client));
   }

   public ReplicationControllerOperationsImpl(PodOperationContext context, OperationContext superContext) {
      super(context, superContext.withPlural("replicationcontrollers"), ReplicationController.class, ReplicationControllerList.class);
   }

   public ReplicationControllerOperationsImpl newInstance(OperationContext context) {
      return new ReplicationControllerOperationsImpl(this.rollingOperationContext, context);
   }

   public ReplicationControllerOperationsImpl newInstance(PodOperationContext context, OperationContext superContext) {
      return new ReplicationControllerOperationsImpl(context, superContext);
   }

   public RollingUpdater getRollingUpdater(long rollingTimeout, TimeUnit rollingTimeUnit) {
      return new ReplicationControllerRollingUpdater(this.context.getClient(), this.namespace, rollingTimeUnit.toMillis(rollingTimeout), (long)this.getRequestConfig().getLoggingInterval());
   }

   public Status rollback(DeploymentRollback deploymentRollback) {
      throw new KubernetesClientException("rollback not supported in case of ReplicationControllers");
   }

   public String getLog(boolean isPretty) {
      return PodOperationUtil.getLog((new ReplicationControllerOperationsImpl(this.rollingOperationContext.withPrettyOutput(isPretty), this.context)).doGetLog(), isPretty);
   }

   private List doGetLog() {
      ReplicationController rc = (ReplicationController)this.requireFromServer();
      return PodOperationUtil.getPodOperationsForController(this.context, this.rollingOperationContext, rc.getMetadata().getUid(), getReplicationControllerPodLabels(rc));
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

   static Map getReplicationControllerPodLabels(ReplicationController replicationController) {
      Map<String, String> labels = new HashMap();
      if (replicationController != null && replicationController.getSpec() != null && replicationController.getSpec().getSelector() != null) {
         labels.putAll(replicationController.getSpec().getSelector());
      }

      return labels;
   }

   protected List getContainers(ReplicationController value) {
      return value.getSpec().getTemplate().getSpec().getContainers();
   }

   public TimeTailPrettyLoggable limitBytes(int limitBytes) {
      return new ReplicationControllerOperationsImpl(this.rollingOperationContext.withLimitBytes(limitBytes), this.context);
   }

   public TimeTailPrettyLoggable terminated() {
      return new ReplicationControllerOperationsImpl(this.rollingOperationContext.withTerminatedStatus(true), this.context);
   }

   public Loggable withPrettyOutput() {
      return new ReplicationControllerOperationsImpl(this.rollingOperationContext.withPrettyOutput(true), this.context);
   }

   public PrettyLoggable tailingLines(int lines) {
      return new ReplicationControllerOperationsImpl(this.rollingOperationContext.withTailingLines(lines), this.context);
   }

   public TailPrettyLoggable sinceTime(String timestamp) {
      return new ReplicationControllerOperationsImpl(this.rollingOperationContext.withSinceTimestamp(timestamp), this.context);
   }

   public TailPrettyLoggable sinceSeconds(int seconds) {
      return new ReplicationControllerOperationsImpl(this.rollingOperationContext.withSinceSeconds(seconds), this.context);
   }

   public BytesLimitTerminateTimeTailPrettyLoggable usingTimestamps() {
      return new ReplicationControllerOperationsImpl(this.rollingOperationContext.withTimestamps(true), this.context);
   }
}
