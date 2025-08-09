package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.flowcontrol.v1beta1.FlowSchema;
import io.fabric8.kubernetes.api.model.flowcontrol.v1beta1.FlowSchemaList;
import io.fabric8.kubernetes.api.model.flowcontrol.v1beta1.PriorityLevelConfiguration;
import io.fabric8.kubernetes.api.model.flowcontrol.v1beta1.PriorityLevelConfigurationList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1beta1FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1FlowControlAPIGroupClient extends ClientAdapter implements V1beta1FlowControlAPIGroupDSL {
   public NonNamespaceOperation flowSchema() {
      return this.resources(FlowSchema.class, FlowSchemaList.class);
   }

   public NonNamespaceOperation priorityLevelConfigurations() {
      return this.resources(PriorityLevelConfiguration.class, PriorityLevelConfigurationList.class);
   }

   public V1beta1FlowControlAPIGroupClient newInstance() {
      return new V1beta1FlowControlAPIGroupClient();
   }
}
