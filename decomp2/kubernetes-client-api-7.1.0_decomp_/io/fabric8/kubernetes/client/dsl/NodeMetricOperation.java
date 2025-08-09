package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetrics;
import java.util.Map;

public interface NodeMetricOperation extends MetricOperation, Nameable {
   NodeMetricOperation withLabels(Map var1);

   NodeMetrics metrics(String var1);
}
