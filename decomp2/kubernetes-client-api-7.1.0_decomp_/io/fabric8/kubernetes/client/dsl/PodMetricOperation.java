package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.metrics.v1beta1.PodMetrics;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.PodMetricsList;
import java.util.Map;

public interface PodMetricOperation extends MetricOperation, Nameable, Namespaceable {
   PodMetricOperation withLabels(Map var1);

   PodMetricsList metrics(String var1);

   PodMetrics metrics(String var1, String var2);
}
