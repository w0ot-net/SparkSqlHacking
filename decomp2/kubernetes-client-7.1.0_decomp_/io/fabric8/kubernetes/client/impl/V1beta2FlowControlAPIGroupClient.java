package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.flowcontrol.v1beta2.FlowSchema;
import io.fabric8.kubernetes.api.model.flowcontrol.v1beta2.FlowSchemaList;
import io.fabric8.kubernetes.api.model.flowcontrol.v1beta2.PriorityLevelConfiguration;
import io.fabric8.kubernetes.api.model.flowcontrol.v1beta2.PriorityLevelConfigurationList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1beta2FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta2FlowControlAPIGroupClient extends ClientAdapter implements V1beta2FlowControlAPIGroupDSL {
   public NonNamespaceOperation flowSchema() {
      return this.resources(FlowSchema.class, FlowSchemaList.class);
   }

   public NonNamespaceOperation priorityLevelConfigurations() {
      return this.resources(PriorityLevelConfiguration.class, PriorityLevelConfigurationList.class);
   }

   public V1beta2FlowControlAPIGroupClient newInstance() {
      return new V1beta2FlowControlAPIGroupClient();
   }
}
