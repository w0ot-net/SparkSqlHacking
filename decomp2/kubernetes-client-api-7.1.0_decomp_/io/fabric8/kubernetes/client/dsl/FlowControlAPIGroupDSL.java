package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface FlowControlAPIGroupDSL extends Client {
   V1FlowControlAPIGroupDSL v1();

   V1beta1FlowControlAPIGroupDSL v1beta1();

   V1beta2FlowControlAPIGroupDSL v1beta2();

   V1beta3FlowControlAPIGroupDSL v1beta3();
}
