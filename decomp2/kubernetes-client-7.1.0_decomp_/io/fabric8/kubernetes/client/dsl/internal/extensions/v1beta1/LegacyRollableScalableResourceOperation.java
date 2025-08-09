package io.fabric8.kubernetes.client.dsl.internal.extensions.v1beta1;

import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.autoscaling.v1.Scale;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationContext;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.RollableScalableResourceOperation;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class LegacyRollableScalableResourceOperation extends RollableScalableResourceOperation {
   protected LegacyRollableScalableResourceOperation(PodOperationContext context, OperationContext superContext, Class type, Class listType) {
      super(context, superContext, type, listType);
   }

   public Scale scale(Scale scaleParam) {
      return scale(scaleParam, this);
   }

   public static Scale scale(Scale scaleParam, HasMetadataOperation operation) {
      GenericKubernetesResource scale = (GenericKubernetesResource)operation.handleScale((GenericKubernetesResource)Optional.ofNullable(scaleParam).map((s) -> (GenericKubernetesResource)operation.getKubernetesSerialization().unmarshal(operation.getKubernetesSerialization().asYaml(s), GenericKubernetesResource.class)).map((g) -> {
         g.getAdditionalProperties().put("status", (Object)null);
         return g;
      }).orElse((Object)null), GenericKubernetesResource.class);
      return (Scale)Optional.ofNullable(scale).map((s) -> {
         Optional.ofNullable((Map)s.getAdditionalProperties().get("status")).ifPresent((status) -> Optional.ofNullable((Map)status.get("selector")).ifPresent((selector) -> status.put("selector", selector.entrySet().stream().map((e) -> {
                  String var10000 = (String)e.getKey();
                  return var10000 + "=" + e.getValue();
               }).collect(Collectors.joining(";")))));
         return s;
      }).map((s) -> (Scale)operation.getKubernetesSerialization().unmarshal(operation.getKubernetesSerialization().asYaml(s), Scale.class)).orElse((Object)null);
   }
}
