package io.fabric8.kubernetes.client.dsl.internal.apps.v1;

import io.fabric8.kubernetes.api.model.OwnerReference;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import io.fabric8.kubernetes.api.model.apps.ReplicaSet;
import io.fabric8.kubernetes.api.model.apps.ReplicaSetList;
import io.fabric8.kubernetes.api.model.extensions.DeploymentRollback;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.BytesLimitTerminateTimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.dsl.Loggable;
import io.fabric8.kubernetes.client.dsl.PrettyLoggable;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;
import io.fabric8.kubernetes.client.dsl.TailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeoutImageEditReplacePatchable;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationContext;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeploymentOperationsImpl extends RollableScalableResourceOperation implements TimeoutImageEditReplacePatchable {
   static final transient Logger LOG = LoggerFactory.getLogger(DeploymentOperationsImpl.class);
   public static final String DEPLOYMENT_KUBERNETES_IO_REVISION = "deployment.kubernetes.io/revision";

   public DeploymentOperationsImpl(Client client) {
      this(new PodOperationContext(), HasMetadataOperationsImpl.defaultContext(client));
   }

   public DeploymentOperationsImpl(PodOperationContext context, OperationContext superContext) {
      super(context, superContext.withApiGroupName("apps").withApiGroupVersion("v1").withPlural("deployments"), Deployment.class, DeploymentList.class);
   }

   public DeploymentOperationsImpl newInstance(OperationContext context) {
      return new DeploymentOperationsImpl(this.rollingOperationContext, context);
   }

   public DeploymentOperationsImpl newInstance(PodOperationContext context, OperationContext superContext) {
      return new DeploymentOperationsImpl(context, superContext);
   }

   public Status rollback(DeploymentRollback rollback) {
      return this.handleDeploymentRollback(rollback);
   }

   public RollingUpdater getRollingUpdater(long rollingTimeout, TimeUnit rollingTimeUnit) {
      return null;
   }

   public Deployment resume() {
      return (Deployment)RollingUpdater.resume(this);
   }

   public Deployment pause() {
      return (Deployment)RollingUpdater.pause(this);
   }

   public Deployment restart() {
      return (Deployment)RollingUpdater.restart(this);
   }

   public Deployment undo() {
      List<ReplicaSet> replicaSets = this.getReplicaSetListForDeployment((Deployment)this.get()).getItems();
      replicaSets.sort((o1, o2) -> {
         String revisionO1 = (String)o1.getMetadata().getAnnotations().get("deployment.kubernetes.io/revision");
         String revisionO2 = (String)o2.getMetadata().getAnnotations().get("deployment.kubernetes.io/revision");
         return Integer.parseInt(revisionO2) - Integer.parseInt(revisionO1);
      });
      ReplicaSet latestReplicaSet = (ReplicaSet)replicaSets.get(0);
      ReplicaSet previousRevisionReplicaSet = (ReplicaSet)replicaSets.get(1);
      Deployment deployment = (Deployment)this.get();
      deployment.getMetadata().getAnnotations().put("deployment.kubernetes.io/revision", (String)latestReplicaSet.getMetadata().getAnnotations().get("deployment.kubernetes.io/revision"));
      deployment.getSpec().setTemplate(previousRevisionReplicaSet.getSpec().getTemplate());
      return (Deployment)this.sendPatchedObject((Deployment)this.get(), deployment);
   }

   public String getLog(boolean isPretty) {
      StringBuilder stringBuilder = new StringBuilder();

      for(RollableScalableResource rcOperation : this.doGetLog()) {
         stringBuilder.append(rcOperation.getLog(isPretty));
      }

      return stringBuilder.toString();
   }

   private List doGetLog() {
      List<RollableScalableResource<ReplicaSet>> rcs = new ArrayList();
      Deployment deployment = (Deployment)this.requireFromServer();
      String rcUid = deployment.getMetadata().getUid();
      ReplicaSetOperationsImpl rsOperations = new ReplicaSetOperationsImpl(this.rollingOperationContext, this.context.withName((String)null));
      ReplicaSetList rcList = (ReplicaSetList)rsOperations.withLabels(getDeploymentSelectorLabels(deployment)).list();

      for(ReplicaSet rs : rcList.getItems()) {
         OwnerReference ownerReference = KubernetesResourceUtil.getControllerUid(rs);
         if (ownerReference != null && ownerReference.getUid().equals(rcUid)) {
            rcs.add((RollableScalableResource)rsOperations.withName(rs.getMetadata().getName()));
         }
      }

      return rcs;
   }

   public Reader getLogReader() {
      return (Reader)this.findFirstPodResource().map(Loggable::getLogReader).orElse((Object)null);
   }

   public InputStream getLogInputStream() {
      return (InputStream)this.findFirstPodResource().map(Loggable::getLogInputStream).orElse((Object)null);
   }

   public LogWatch watchLog(OutputStream out) {
      return (LogWatch)this.findFirstPodResource().map((it) -> it.watchLog(out)).orElse((Object)null);
   }

   private Optional findFirstPodResource() {
      List<RollableScalableResource<ReplicaSet>> podResources = this.doGetLog();
      if (!podResources.isEmpty()) {
         if (podResources.size() > 1) {
            LOG.debug("Found {} pods, Using first one to get log", podResources.size());
         }

         return Optional.of((RollableScalableResource)podResources.get(0));
      } else {
         return Optional.empty();
      }
   }

   private ReplicaSetList getReplicaSetListForDeployment(Deployment deployment) {
      return (ReplicaSetList)(new ReplicaSetOperationsImpl(this.context.getClient())).inNamespace(this.getNamespace()).withLabels(deployment.getSpec().getSelector().getMatchLabels()).list();
   }

   static Map getDeploymentSelectorLabels(Deployment deployment) {
      Map<String, String> labels = new HashMap();
      if (deployment != null && deployment.getSpec() != null && deployment.getSpec().getTemplate() != null && deployment.getSpec().getTemplate().getMetadata() != null) {
         labels.putAll(deployment.getSpec().getTemplate().getMetadata().getLabels());
      }

      return labels;
   }

   protected List getContainers(Deployment value) {
      return value.getSpec().getTemplate().getSpec().getContainers();
   }

   public TimeTailPrettyLoggable limitBytes(int limitBytes) {
      return new DeploymentOperationsImpl(this.rollingOperationContext.withLimitBytes(limitBytes), this.context);
   }

   public TimeTailPrettyLoggable terminated() {
      return new DeploymentOperationsImpl(this.rollingOperationContext.withTerminatedStatus(true), this.context);
   }

   public Loggable withPrettyOutput() {
      return new DeploymentOperationsImpl(this.rollingOperationContext.withPrettyOutput(true), this.context);
   }

   public PrettyLoggable tailingLines(int lines) {
      return new DeploymentOperationsImpl(this.rollingOperationContext.withTailingLines(lines), this.context);
   }

   public TailPrettyLoggable sinceTime(String timestamp) {
      return new DeploymentOperationsImpl(this.rollingOperationContext.withSinceTimestamp(timestamp), this.context);
   }

   public TailPrettyLoggable sinceSeconds(int seconds) {
      return new DeploymentOperationsImpl(this.rollingOperationContext.withSinceSeconds(seconds), this.context);
   }

   public BytesLimitTerminateTimeTailPrettyLoggable usingTimestamps() {
      return new DeploymentOperationsImpl(this.rollingOperationContext.withTimestamps(true), this.context);
   }
}
