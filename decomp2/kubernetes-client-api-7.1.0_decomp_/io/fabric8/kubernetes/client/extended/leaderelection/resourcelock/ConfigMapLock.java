package io.fabric8.kubernetes.client.extended.leaderelection.resourcelock;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.utils.Serialization;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigMapLock extends ResourceLock {
   private static final Logger LOGGER = LoggerFactory.getLogger(ConfigMapLock.class);

   public ConfigMapLock(String configMapNamespace, String configMapName, String identity) {
      super(configMapNamespace, configMapName, identity);
   }

   public ConfigMapLock(ObjectMeta meta, String identity) {
      super(meta, identity);
   }

   protected Class getKind() {
      return ConfigMap.class;
   }

   protected LeaderElectionRecord toRecord(ConfigMap resource) {
      return (LeaderElectionRecord)Optional.ofNullable(resource.getMetadata().getAnnotations()).map((annotations) -> (String)annotations.get("control-plane.alpha.kubernetes.io/leader")).map((annotation) -> {
         try {
            return (LeaderElectionRecord)Serialization.unmarshal(annotation, LeaderElectionRecord.class);
         } catch (KubernetesClientException ex) {
            LOGGER.error("Error deserializing LeaderElectionRecord from ConfigMap", ex);
            return null;
         }
      }).orElse((Object)null);
   }

   protected ConfigMap toResource(LeaderElectionRecord leaderElectionRecord, ObjectMetaBuilder meta) {
      return ((ConfigMapBuilder)(new ConfigMapBuilder()).withMetadata(((ObjectMetaBuilder)meta.addToAnnotations("control-plane.alpha.kubernetes.io/leader", Serialization.asJson(leaderElectionRecord))).build())).build();
   }
}
