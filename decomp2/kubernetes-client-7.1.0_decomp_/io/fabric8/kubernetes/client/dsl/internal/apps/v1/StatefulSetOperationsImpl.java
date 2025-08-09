package io.fabric8.kubernetes.client.dsl.internal.apps.v1;

import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.apps.ControllerRevision;
import io.fabric8.kubernetes.api.model.apps.ControllerRevisionList;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.apps.StatefulSetList;
import io.fabric8.kubernetes.api.model.extensions.DeploymentRollback;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.BytesLimitTerminateTimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.dsl.Loggable;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.PrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeoutImageEditReplacePatchable;
import io.fabric8.kubernetes.client.dsl.base.PatchContext;
import io.fabric8.kubernetes.client.dsl.base.PatchType;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationContext;
import io.fabric8.kubernetes.client.utils.internal.PodOperationUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class StatefulSetOperationsImpl extends RollableScalableResourceOperation implements TimeoutImageEditReplacePatchable {
   public StatefulSetOperationsImpl(Client client) {
      this(new PodOperationContext(), HasMetadataOperationsImpl.defaultContext(client));
   }

   public StatefulSetOperationsImpl(PodOperationContext context, OperationContext superContext) {
      super(context, superContext.withApiGroupName("apps").withApiGroupVersion("v1").withPlural("statefulsets"), StatefulSet.class, StatefulSetList.class);
   }

   public StatefulSetOperationsImpl newInstance(OperationContext context) {
      return new StatefulSetOperationsImpl(this.rollingOperationContext, context);
   }

   public StatefulSetOperationsImpl newInstance(PodOperationContext context, OperationContext superContext) {
      return new StatefulSetOperationsImpl(context, superContext);
   }

   public RollingUpdater getRollingUpdater(long rollingTimeout, TimeUnit rollingTimeUnit) {
      return null;
   }

   public Status rollback(DeploymentRollback deploymentRollback) {
      throw new KubernetesClientException("rollback not supported in case of StatefulSets");
   }

   public String getLog(boolean isPretty) {
      return PodOperationUtil.getLog((new StatefulSetOperationsImpl(this.rollingOperationContext.withPrettyOutput(isPretty), this.context)).doGetLog(), isPretty);
   }

   private List doGetLog() {
      StatefulSet statefulSet = (StatefulSet)this.requireFromServer();
      return PodOperationUtil.getPodOperationsForController(this.context, this.rollingOperationContext, statefulSet.getMetadata().getUid(), getStatefulSetSelectorLabels(statefulSet));
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

   public StatefulSet restart() {
      return (StatefulSet)RollingUpdater.restart(this);
   }

   public StatefulSet undo() {
      List<ControllerRevision> controllerRevisions = this.getControllerRevisionListForStatefulSet((StatefulSet)this.get()).getItems();
      if (controllerRevisions.size() < 2) {
         throw new IllegalStateException("No revision to rollback to!");
      } else {
         controllerRevisions.sort((o1, o2) -> {
            Long revision2 = o2.getRevision();
            Long revision1 = o1.getRevision();
            if (revision1 != null && revision2 != null) {
               return revision2.intValue() - revision1.intValue();
            } else if (revision1 != null) {
               return revision1.intValue();
            } else {
               return revision2 != null ? revision2.intValue() : 0;
            }
         });
         ControllerRevision previousControllerRevision = (ControllerRevision)controllerRevisions.get(1);
         return (StatefulSet)this.patch(PatchContext.of(PatchType.STRATEGIC_MERGE), this.getKubernetesSerialization().asJson(previousControllerRevision.getData()));
      }
   }

   private ControllerRevisionList getControllerRevisionListForStatefulSet(StatefulSet statefulSet) {
      return (ControllerRevisionList)((FilterWatchListDeletable)((NonNamespaceOperation)this.context.getClient().resources(ControllerRevision.class, ControllerRevisionList.class).inNamespace(this.namespace)).withLabels(statefulSet.getSpec().getSelector().getMatchLabels())).list();
   }

   static Map getStatefulSetSelectorLabels(StatefulSet statefulSet) {
      Map<String, String> labels = new HashMap();
      if (statefulSet != null && statefulSet.getSpec() != null && statefulSet.getSpec().getTemplate() != null && statefulSet.getSpec().getTemplate().getMetadata() != null) {
         labels.putAll(statefulSet.getSpec().getTemplate().getMetadata().getLabels());
      }

      return labels;
   }

   protected List getContainers(StatefulSet value) {
      return value.getSpec().getTemplate().getSpec().getContainers();
   }

   public TimeTailPrettyLoggable limitBytes(int limitBytes) {
      return new StatefulSetOperationsImpl(this.rollingOperationContext.withLimitBytes(limitBytes), this.context);
   }

   public TimeTailPrettyLoggable terminated() {
      return new StatefulSetOperationsImpl(this.rollingOperationContext.withTerminatedStatus(true), this.context);
   }

   public Loggable withPrettyOutput() {
      return new StatefulSetOperationsImpl(this.rollingOperationContext.withPrettyOutput(true), this.context);
   }

   public PrettyLoggable tailingLines(int lines) {
      return new StatefulSetOperationsImpl(this.rollingOperationContext.withTailingLines(lines), this.context);
   }

   public TailPrettyLoggable sinceTime(String timestamp) {
      return new StatefulSetOperationsImpl(this.rollingOperationContext.withSinceTimestamp(timestamp), this.context);
   }

   public TailPrettyLoggable sinceSeconds(int seconds) {
      return new StatefulSetOperationsImpl(this.rollingOperationContext.withSinceSeconds(seconds), this.context);
   }

   public BytesLimitTerminateTimeTailPrettyLoggable usingTimestamps() {
      return new StatefulSetOperationsImpl(this.rollingOperationContext.withTimestamps(true), this.context);
   }
}
