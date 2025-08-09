package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.client.dsl.ApiextensionsAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.AppsAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.AuthenticationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.AuthorizationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.BatchAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.DiscoveryAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.DynamicResourceAllocationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.EventingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.ExtensionsAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.InOutCreateable;
import io.fabric8.kubernetes.client.dsl.MetricAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable;
import io.fabric8.kubernetes.client.dsl.NamespaceableResource;
import io.fabric8.kubernetes.client.dsl.NetworkAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.PolicyAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.RbacAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.SchedulingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.StorageAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1APIGroupDSL;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.fabric8.kubernetes.client.extended.leaderelection.LeaderElectorBuilder;
import io.fabric8.kubernetes.client.extended.run.RunOperations;
import io.fabric8.kubernetes.client.informers.SharedInformerFactory;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import java.io.InputStream;
import java.util.Collection;

public interface KubernetesClient extends Client {
   ApiextensionsAPIGroupDSL apiextensions();

   /** @deprecated */
   @Deprecated
   NonNamespaceOperation certificateSigningRequests();

   CertificatesAPIGroupDSL certificates();

   default MixedOperation resources(Class resourceType) {
      return this.resources(resourceType, (Class)null);
   }

   MixedOperation genericKubernetesResources(ResourceDefinitionContext var1);

   MixedOperation genericKubernetesResources(String var1, String var2);

   DiscoveryAPIGroupDSL discovery();

   DynamicResourceAllocationAPIGroupDSL dynamicResourceAllocation();

   EventingAPIGroupDSL events();

   ExtensionsAPIGroupDSL extensions();

   FlowControlAPIGroupDSL flowControl();

   /** @deprecated */
   @Deprecated
   VersionInfo getVersion();

   VersionInfo getKubernetesVersion();

   AdmissionRegistrationAPIGroupDSL admissionRegistration();

   AppsAPIGroupDSL apps();

   AutoscalingAPIGroupDSL autoscaling();

   NetworkAPIGroupDSL network();

   StorageAPIGroupDSL storage();

   BatchAPIGroupDSL batch();

   MetricAPIGroupDSL top();

   PolicyAPIGroupDSL policy();

   RbacAPIGroupDSL rbac();

   SchedulingAPIGroupDSL scheduling();

   NonNamespaceOperation componentstatuses();

   NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable load(InputStream var1);

   NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(String var1);

   NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(KubernetesResourceList var1);

   NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(HasMetadata... var1);

   NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(Collection var1);

   NamespaceableResource resource(HasMetadata var1);

   NamespaceableResource resource(String var1);

   NamespaceableResource resource(InputStream var1);

   MixedOperation bindings();

   MixedOperation endpoints();

   NonNamespaceOperation namespaces();

   NonNamespaceOperation nodes();

   NonNamespaceOperation persistentVolumes();

   MixedOperation persistentVolumeClaims();

   MixedOperation pods();

   MixedOperation replicationControllers();

   MixedOperation resourceQuotas();

   MixedOperation secrets();

   MixedOperation services();

   MixedOperation serviceAccounts();

   NonNamespaceOperation apiServices();

   MixedOperation configMaps();

   MixedOperation limitRanges();

   AuthorizationAPIGroupDSL authorization();

   AuthenticationAPIGroupDSL authentication();

   InOutCreateable tokenReviews();

   SharedInformerFactory informers();

   LeaderElectorBuilder leaderElector();

   MixedOperation leases();

   V1APIGroupDSL v1();

   RunOperations run();

   NonNamespaceOperation runtimeClasses();

   void visitResources(ApiVisitor var1);

   KubernetesSerialization getKubernetesSerialization();
}
