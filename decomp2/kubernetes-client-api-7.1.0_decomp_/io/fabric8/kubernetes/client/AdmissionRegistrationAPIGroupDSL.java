package io.fabric8.kubernetes.client;

public interface AdmissionRegistrationAPIGroupDSL extends Client {
   V1AdmissionRegistrationAPIGroupDSL v1();

   V1beta1AdmissionRegistrationAPIGroupDSL v1beta1();
}
