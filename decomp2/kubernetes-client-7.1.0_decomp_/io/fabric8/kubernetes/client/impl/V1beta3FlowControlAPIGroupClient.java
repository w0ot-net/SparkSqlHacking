package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.flowcontrol.v1beta3.FlowSchema;
import io.fabric8.kubernetes.api.model.flowcontrol.v1beta3.FlowSchemaList;
import io.fabric8.kubernetes.api.model.flowcontrol.v1beta3.PriorityLevelConfiguration;
import io.fabric8.kubernetes.api.model.flowcontrol.v1beta3.PriorityLevelConfigurationList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1beta3FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta3FlowControlAPIGroupClient extends ClientAdapter implements V1beta3FlowControlAPIGroupDSL {
   public NonNamespaceOperation flowSchema() {
      return this.resources(FlowSchema.class, FlowSchemaList.class);
   }

   public NonNamespaceOperation priorityLevelConfigurations() {
      return this.resources(PriorityLevelConfiguration.class, PriorityLevelConfigurationList.class);
   }

   public V1beta3FlowControlAPIGroupClient newInstance() {
      return new V1beta3FlowControlAPIGroupClient();
   }
}
