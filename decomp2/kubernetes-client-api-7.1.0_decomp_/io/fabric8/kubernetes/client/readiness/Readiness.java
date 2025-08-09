package io.fabric8.kubernetes.client.readiness;

import io.fabric8.kubernetes.api.model.EndpointSubset;
import io.fabric8.kubernetes.api.model.Endpoints;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeCondition;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodCondition;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ReplicationControllerSpec;
import io.fabric8.kubernetes.api.model.ReplicationControllerStatus;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentSpec;
import io.fabric8.kubernetes.api.model.apps.DeploymentStatus;
import io.fabric8.kubernetes.api.model.apps.ReplicaSet;
import io.fabric8.kubernetes.api.model.apps.ReplicaSetSpec;
import io.fabric8.kubernetes.api.model.apps.ReplicaSetStatus;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.apps.StatefulSetSpec;
import io.fabric8.kubernetes.api.model.apps.StatefulSetStatus;
import io.fabric8.kubernetes.client.utils.Utils;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Readiness {
   private static final String POD_READY = "Ready";
   private static final String NODE_READY = "Ready";
   private static final String TRUE = "True";
   private static final Logger logger = LoggerFactory.getLogger(Readiness.class);
   protected static final String READINESS_APPLICABLE_RESOURCES = "Node, Deployment, ReplicaSet, StatefulSet, Pod, ReplicationController";

   public static Readiness getInstance() {
      return Readiness.ReadinessHolder.INSTANCE;
   }

   public boolean isReady(HasMetadata item) {
      return this.isReadinessApplicable(item) ? this.isResourceReady(item) : this.handleNonReadinessApplicableResource(item);
   }

   protected boolean isReadinessApplicable(HasMetadata item) {
      return item instanceof Deployment || item instanceof io.fabric8.kubernetes.api.model.extensions.Deployment || item instanceof ReplicaSet || item instanceof Pod || item instanceof ReplicationController || item instanceof Endpoints || item instanceof Node || item instanceof StatefulSet;
   }

   protected boolean isResourceReady(HasMetadata item) {
      if (item instanceof Deployment) {
         return isDeploymentReady((Deployment)item);
      } else if (item instanceof io.fabric8.kubernetes.api.model.extensions.Deployment) {
         return isExtensionsDeploymentReady((io.fabric8.kubernetes.api.model.extensions.Deployment)item);
      } else if (item instanceof ReplicaSet) {
         return isReplicaSetReady((ReplicaSet)item);
      } else if (item instanceof Pod) {
         return isPodReady((Pod)item);
      } else if (item instanceof ReplicationController) {
         return isReplicationControllerReady((ReplicationController)item);
      } else if (item instanceof Endpoints) {
         return isEndpointsReady((Endpoints)item);
      } else if (item instanceof Node) {
         return isNodeReady((Node)item);
      } else {
         return item instanceof StatefulSet ? isStatefulSetReady((StatefulSet)item) : false;
      }
   }

   protected String getReadinessResourcesList() {
      return "Node, Deployment, ReplicaSet, StatefulSet, Pod, ReplicationController";
   }

   final boolean handleNonReadinessApplicableResource(HasMetadata item) {
      boolean doesItemExist = Objects.nonNull(item);
      logger.warn("{} is not a Readiable resource. It needs to be one of [{}]", doesItemExist ? item.getKind() : "Unknown", this.getReadinessResourcesList());
      return doesItemExist;
   }

   public static boolean isStatefulSetReady(StatefulSet ss) {
      Utils.checkNotNull(ss, "StatefulSet can't be null.");
      StatefulSetSpec spec = ss.getSpec();
      StatefulSetStatus status = ss.getStatus();
      if (spec != null && spec.getReplicas() != null) {
         if (spec.getReplicas() == 0) {
            return true;
         } else if (status != null && status.getReplicas() != null && status.getReadyReplicas() != null) {
            return spec.getReplicas() == status.getReplicas() && spec.getReplicas() == status.getReadyReplicas();
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean isDeploymentReady(Deployment d) {
      Utils.checkNotNull(d, "Deployment can't be null.");
      DeploymentSpec spec = d.getSpec();
      DeploymentStatus status = d.getStatus();
      if (spec != null && spec.getReplicas() != null) {
         if (spec.getReplicas() == 0) {
            return true;
         } else if (status != null && status.getReplicas() != null && status.getAvailableReplicas() != null) {
            int replicas = (Integer)Utils.getNonNullOrElse(spec.getReplicas(), 0);
            return replicas == status.getReplicas() && replicas <= status.getAvailableReplicas();
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean isExtensionsDeploymentReady(io.fabric8.kubernetes.api.model.extensions.Deployment d) {
      Utils.checkNotNull(d, "Deployment can't be null.");
      io.fabric8.kubernetes.api.model.extensions.DeploymentSpec spec = d.getSpec();
      io.fabric8.kubernetes.api.model.extensions.DeploymentStatus status = d.getStatus();
      if (spec != null && spec.getReplicas() != null) {
         if (spec.getReplicas() == 0) {
            return true;
         } else if (status != null && status.getReplicas() != null && status.getAvailableReplicas() != null) {
            return spec.getReplicas() == status.getReplicas() && spec.getReplicas() <= status.getAvailableReplicas();
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean isReplicaSetReady(ReplicaSet r) {
      Utils.checkNotNull(r, "ReplicationController can't be null.");
      ReplicaSetSpec spec = r.getSpec();
      ReplicaSetStatus status = r.getStatus();
      if (spec != null && spec.getReplicas() != null) {
         if (spec.getReplicas() == 0) {
            return true;
         } else if (status != null && status.getReadyReplicas() != null) {
            return spec.getReplicas() == status.getReadyReplicas();
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean isReplicationControllerReady(ReplicationController r) {
      Utils.checkNotNull(r, "ReplicationController can't be null.");
      ReplicationControllerSpec spec = r.getSpec();
      ReplicationControllerStatus status = r.getStatus();
      if (spec != null && spec.getReplicas() != null) {
         if (spec.getReplicas() == 0) {
            return true;
         } else if (status != null && status.getReadyReplicas() != null) {
            return spec.getReplicas() == status.getReadyReplicas();
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean isEndpointsReady(Endpoints e) {
      Utils.checkNotNull(e, "Endpoints can't be null.");
      String name = e.getMetadata().getName();
      Utils.checkNotNull(name, "Endpoints name can't be null.");
      if (e.getSubsets() == null) {
         return false;
      } else {
         for(EndpointSubset subset : e.getSubsets()) {
            if (!subset.getAddresses().isEmpty() && !subset.getPorts().isEmpty()) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean isPodReady(Pod pod) {
      Utils.checkNotNull(pod, "Pod can't be null.");
      PodCondition condition = getPodReadyCondition(pod);
      return condition != null && condition.getStatus() != null ? condition.getStatus().equalsIgnoreCase("True") : false;
   }

   public static boolean isPodSucceeded(Pod pod) {
      Utils.checkNotNull(pod, "Pod can't be null.");
      return pod.getStatus() != null && "Succeeded".equals(pod.getStatus().getPhase());
   }

   private static PodCondition getPodReadyCondition(Pod pod) {
      Utils.checkNotNull(pod, "Pod can't be null.");
      if (pod.getStatus() != null && pod.getStatus().getConditions() != null) {
         for(PodCondition condition : pod.getStatus().getConditions()) {
            if ("Ready".equals(condition.getType())) {
               return condition;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static boolean isNodeReady(Node node) {
      Utils.checkNotNull(node, "Node can't be null.");
      NodeCondition condition = getNodeReadyCondition(node);
      return condition != null && condition.getStatus() != null ? condition.getStatus().equalsIgnoreCase("True") : false;
   }

   private static NodeCondition getNodeReadyCondition(Node node) {
      Utils.checkNotNull(node, "Node can't be null.");
      if (node.getStatus() != null && node.getStatus().getConditions() != null) {
         for(NodeCondition condition : node.getStatus().getConditions()) {
            if ("Ready".equals(condition.getType())) {
               return condition;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private static class ReadinessHolder {
      public static final Readiness INSTANCE = new Readiness();
   }
}
