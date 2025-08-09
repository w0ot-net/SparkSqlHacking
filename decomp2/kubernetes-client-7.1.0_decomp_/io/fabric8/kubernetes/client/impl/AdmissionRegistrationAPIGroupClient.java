package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.AdmissionRegistrationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1AdmissionRegistrationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1AdmissionRegistrationAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class AdmissionRegistrationAPIGroupClient extends ClientAdapter implements AdmissionRegistrationAPIGroupDSL {
   public V1AdmissionRegistrationAPIGroupDSL v1() {
      return (V1AdmissionRegistrationAPIGroupDSL)this.adapt(V1AdmissionRegistrationAPIGroupClient.class);
   }

   public V1beta1AdmissionRegistrationAPIGroupDSL v1beta1() {
      return (V1beta1AdmissionRegistrationAPIGroupDSL)this.adapt(V1beta1AdmissionRegistrationAPIGroupClient.class);
   }

   public AdmissionRegistrationAPIGroupClient newInstance() {
      return new AdmissionRegistrationAPIGroupClient();
   }
}
