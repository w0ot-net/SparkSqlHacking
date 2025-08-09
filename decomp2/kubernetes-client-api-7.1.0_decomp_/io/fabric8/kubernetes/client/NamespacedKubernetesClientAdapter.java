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
import io.fabric8.kubernetes.client.dsl.FunctionCallable;
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
import io.fabric8.kubernetes.client.extension.ClientAdapter;
import io.fabric8.kubernetes.client.informers.SharedInformerFactory;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import java.io.InputStream;
import java.util.Collection;

public class NamespacedKubernetesClientAdapter extends ClientAdapter implements NamespacedKubernetesClient {
   private Class type;

   public NamespacedKubernetesClientAdapter(Class type) {
      this.type = type;
   }

   public NamespacedKubernetesClientAdapter newInstance() {
      return new NamespacedKubernetesClientAdapter(this.type);
   }

   public NamespacedKubernetesClient getClient() {
      return (NamespacedKubernetesClient)super.getClient().adapt(this.type);
   }

   public FunctionCallable withRequestConfig(RequestConfig requestConfig) {
      return this.getClient().withRequestConfig(requestConfig);
   }

   public MixedOperation resources(Class resourceType, Class listClass) {
      return this.getClient().resources(resourceType, listClass);
   }

   public ApiextensionsAPIGroupDSL apiextensions() {
      return this.getClient().apiextensions();
   }

   public NonNamespaceOperation certificateSigningRequests() {
      return this.getClient().certificateSigningRequests();
   }

   public Client newClient(RequestConfig requestConfig) {
      return this.getClient().newClient(requestConfig);
   }

   public CertificatesAPIGroupDSL certificates() {
      return this.getClient().certificates();
   }

   public MixedOperation resources(Class resourceType) {
      return this.getClient().resources(resourceType);
   }

   public MixedOperation genericKubernetesResources(ResourceDefinitionContext context) {
      return this.getClient().genericKubernetesResources(context);
   }

   public MixedOperation genericKubernetesResources(String apiVersion, String kind) {
      return this.getClient().genericKubernetesResources(apiVersion, kind);
   }

   public DiscoveryAPIGroupDSL discovery() {
      return this.getClient().discovery();
   }

   public DynamicResourceAllocationAPIGroupDSL dynamicResourceAllocation() {
      return this.getClient().dynamicResourceAllocation();
   }

   public EventingAPIGroupDSL events() {
      return this.getClient().events();
   }

   public ExtensionsAPIGroupDSL extensions() {
      return this.getClient().extensions();
   }

   public FlowControlAPIGroupDSL flowControl() {
      return this.getClient().flowControl();
   }

   public VersionInfo getVersion() {
      return this.getClient().getVersion();
   }

   public VersionInfo getKubernetesVersion() {
      return this.getClient().getKubernetesVersion();
   }

   public AdmissionRegistrationAPIGroupDSL admissionRegistration() {
      return this.getClient().admissionRegistration();
   }

   public AppsAPIGroupDSL apps() {
      return this.getClient().apps();
   }

   public AutoscalingAPIGroupDSL autoscaling() {
      return this.getClient().autoscaling();
   }

   public NetworkAPIGroupDSL network() {
      return this.getClient().network();
   }

   public StorageAPIGroupDSL storage() {
      return this.getClient().storage();
   }

   public BatchAPIGroupDSL batch() {
      return this.getClient().batch();
   }

   public MetricAPIGroupDSL top() {
      return this.getClient().top();
   }

   public PolicyAPIGroupDSL policy() {
      return this.getClient().policy();
   }

   public RbacAPIGroupDSL rbac() {
      return this.getClient().rbac();
   }

   public SchedulingAPIGroupDSL scheduling() {
      return this.getClient().scheduling();
   }

   public NonNamespaceOperation componentstatuses() {
      return this.getClient().componentstatuses();
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable load(InputStream is) {
      return this.getClient().load(is);
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(String s) {
      return this.getClient().resourceList(s);
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(KubernetesResourceList list) {
      return this.getClient().resourceList(list);
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(HasMetadata... items) {
      return this.getClient().resourceList(items);
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(Collection items) {
      return this.getClient().resourceList(items);
   }

   public NamespaceableResource resource(HasMetadata is) {
      return this.getClient().resource(is);
   }

   public NamespaceableResource resource(String s) {
      return this.getClient().resource(s);
   }

   public NamespaceableResource resource(InputStream is) {
      return this.getClient().resource(is);
   }

   public MixedOperation bindings() {
      return this.getClient().bindings();
   }

   public MixedOperation endpoints() {
      return this.getClient().endpoints();
   }

   public NonNamespaceOperation namespaces() {
      return this.getClient().namespaces();
   }

   public NonNamespaceOperation nodes() {
      return this.getClient().nodes();
   }

   public NonNamespaceOperation persistentVolumes() {
      return this.getClient().persistentVolumes();
   }

   public MixedOperation persistentVolumeClaims() {
      return this.getClient().persistentVolumeClaims();
   }

   public MixedOperation pods() {
      return this.getClient().pods();
   }

   public MixedOperation replicationControllers() {
      return this.getClient().replicationControllers();
   }

   public MixedOperation resourceQuotas() {
      return this.getClient().resourceQuotas();
   }

   public MixedOperation secrets() {
      return this.getClient().secrets();
   }

   public MixedOperation services() {
      return this.getClient().services();
   }

   public MixedOperation serviceAccounts() {
      return this.getClient().serviceAccounts();
   }

   public NonNamespaceOperation apiServices() {
      return this.getClient().apiServices();
   }

   public MixedOperation configMaps() {
      return this.getClient().configMaps();
   }

   public MixedOperation limitRanges() {
      return this.getClient().limitRanges();
   }

   public AuthorizationAPIGroupDSL authorization() {
      return this.getClient().authorization();
   }

   public AuthenticationAPIGroupDSL authentication() {
      return this.getClient().authentication();
   }

   public InOutCreateable tokenReviews() {
      return this.getClient().tokenReviews();
   }

   public SharedInformerFactory informers() {
      return this.getClient().informers();
   }

   public LeaderElectorBuilder leaderElector() {
      return this.getClient().leaderElector();
   }

   public MixedOperation leases() {
      return this.getClient().leases();
   }

   public V1APIGroupDSL v1() {
      return this.getClient().v1();
   }

   public RunOperations run() {
      return this.getClient().run();
   }

   public NonNamespaceOperation runtimeClasses() {
      return this.getClient().runtimeClasses();
   }

   public void visitResources(ApiVisitor visitor) {
      this.getClient().visitResources(visitor);
   }

   public String raw(String uri, String method, Object payload) {
      return this.getClient().raw(uri, method, payload);
   }

   public KubernetesSerialization getKubernetesSerialization() {
      return this.getClient().getKubernetesSerialization();
   }
}
