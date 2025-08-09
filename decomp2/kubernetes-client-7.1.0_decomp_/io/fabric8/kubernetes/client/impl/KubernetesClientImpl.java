package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.APIGroup;
import io.fabric8.kubernetes.api.model.APIGroupBuilder;
import io.fabric8.kubernetes.api.model.APIGroupList;
import io.fabric8.kubernetes.api.model.APIResource;
import io.fabric8.kubernetes.api.model.APIResourceList;
import io.fabric8.kubernetes.api.model.APIService;
import io.fabric8.kubernetes.api.model.APIServiceList;
import io.fabric8.kubernetes.api.model.Binding;
import io.fabric8.kubernetes.api.model.ComponentStatus;
import io.fabric8.kubernetes.api.model.ComponentStatusList;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapList;
import io.fabric8.kubernetes.api.model.Endpoints;
import io.fabric8.kubernetes.api.model.EndpointsList;
import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.GenericKubernetesResourceList;
import io.fabric8.kubernetes.api.model.GroupVersionForDiscovery;
import io.fabric8.kubernetes.api.model.GroupVersionForDiscoveryBuilder;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesListBuilder;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.api.model.LimitRange;
import io.fabric8.kubernetes.api.model.LimitRangeList;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.PersistentVolume;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaim;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimList;
import io.fabric8.kubernetes.api.model.PersistentVolumeList;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ResourceQuota;
import io.fabric8.kubernetes.api.model.ResourceQuotaList;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.SecretList;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceAccount;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.ReplicaSet;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.authentication.TokenReview;
import io.fabric8.kubernetes.api.model.batch.v1.Job;
import io.fabric8.kubernetes.api.model.certificates.v1.CertificateSigningRequest;
import io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequestList;
import io.fabric8.kubernetes.api.model.coordination.v1.Lease;
import io.fabric8.kubernetes.api.model.coordination.v1.LeaseList;
import io.fabric8.kubernetes.api.model.node.v1beta1.RuntimeClass;
import io.fabric8.kubernetes.api.model.node.v1beta1.RuntimeClassList;
import io.fabric8.kubernetes.client.AdmissionRegistrationAPIGroupDSL;
import io.fabric8.kubernetes.client.ApiVisitor;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.NamespacedKubernetesClient;
import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.V1AdmissionRegistrationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1Alpha2DynamicResourceAllocationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1ApiextensionAPIGroupDSL;
import io.fabric8.kubernetes.client.V1AuthenticationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1AuthorizationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.V1NetworkAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1AdmissionRegistrationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1ApiextensionAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1AuthorizationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1NetworkAPIGroupDSL;
import io.fabric8.kubernetes.client.V2AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.V2beta1AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.V2beta2AutoscalingAPIGroupDSL;
import io.fabric8.kubernetes.client.VersionInfo;
import io.fabric8.kubernetes.client.WithRequestCallable;
import io.fabric8.kubernetes.client.ApiVisitor.ApiVisitResult;
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
import io.fabric8.kubernetes.client.dsl.V1Alpha1CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1BatchAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1Beta1AuthenticationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1DiscoveryAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1EventingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1PolicyAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1SchedulingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1BatchAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1CertificatesAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1DiscoveryAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1EventingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1PolicyAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1SchedulingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta2FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta3FlowControlAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.NamespaceVisitFromServerGetWatchDeleteRecreateWaitApplicableListImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.DeploymentOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.ReplicaSetOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.StatefulSetOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.batch.v1.JobOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.certificates.v1.CertificateSigningRequestOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.core.v1.PodOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.core.v1.ReplicationControllerOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.core.v1.ServiceAccountOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.core.v1.ServiceOperationsImpl;
import io.fabric8.kubernetes.client.extended.leaderelection.LeaderElectorBuilder;
import io.fabric8.kubernetes.client.extended.run.RunConfigBuilder;
import io.fabric8.kubernetes.client.extended.run.RunOperations;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.informers.SharedInformerFactory;
import io.fabric8.kubernetes.client.informers.impl.SharedInformerFactoryImpl;
import io.fabric8.kubernetes.client.utils.ApiVersionUtil;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KubernetesClientImpl extends BaseClient implements NamespacedKubernetesClient {
   public static final Logger logger = LoggerFactory.getLogger(KubernetesClientImpl.class);
   public static final String KUBERNETES_VERSION_ENDPOINT = "version";

   public KubernetesClientImpl(HttpClient httpClient, Config config) {
      this(httpClient, config, () -> Runnable::run, new KubernetesSerialization());
   }

   public KubernetesClientImpl(HttpClient httpClient, Config config, KubernetesClientBuilder.ExecutorSupplier executorSupplier, KubernetesSerialization kubernetesSerialization) {
      super(httpClient, config, executorSupplier, kubernetesSerialization);
      this.registerDefaultAdapters();
      this.registerDefaultHandlers();
   }

   protected KubernetesClientImpl(BaseClient client) {
      super(client);
   }

   protected void registerDefaultAdapters() {
      Adapters adapters = this.getAdapters();
      adapters.registerClient(AppsAPIGroupDSL.class, new AppsAPIGroupClient());
      adapters.registerClient(AdmissionRegistrationAPIGroupDSL.class, new AdmissionRegistrationAPIGroupClient());
      adapters.registerClient(V1AdmissionRegistrationAPIGroupDSL.class, new V1AdmissionRegistrationAPIGroupClient());
      adapters.registerClient(V1beta1AdmissionRegistrationAPIGroupDSL.class, new V1beta1AdmissionRegistrationAPIGroupClient());
      adapters.registerClient(AutoscalingAPIGroupDSL.class, new AutoscalingAPIGroupClient());
      adapters.registerClient(ApiextensionsAPIGroupDSL.class, new ApiextensionsAPIGroupClient());
      adapters.registerClient(AuthorizationAPIGroupDSL.class, new AuthorizationAPIGroupClient());
      adapters.registerClient(AuthenticationAPIGroupDSL.class, new AuthenticationAPIGroupClient());
      adapters.registerClient(V1AutoscalingAPIGroupDSL.class, new V1AutoscalingAPIGroupClient());
      adapters.registerClient(V2AutoscalingAPIGroupDSL.class, new V2AutoscalingAPIGroupClient());
      adapters.registerClient(V2beta1AutoscalingAPIGroupDSL.class, new V2beta1AutoscalingAPIGroupClient());
      adapters.registerClient(V2beta2AutoscalingAPIGroupDSL.class, new V2beta2AutoscalingAPIGroupClient());
      adapters.registerClient(BatchAPIGroupDSL.class, new BatchAPIGroupClient());
      adapters.registerClient(V1BatchAPIGroupDSL.class, new V1BatchAPIGroupClient());
      adapters.registerClient(V1beta1BatchAPIGroupDSL.class, new V1beta1BatchAPIGroupClient());
      adapters.registerClient(ExtensionsAPIGroupDSL.class, new ExtensionsAPIGroupClient());
      adapters.registerClient(EventingAPIGroupDSL.class, new EventingAPIGroupClient());
      adapters.registerClient(V1EventingAPIGroupDSL.class, new V1EventingAPIGroupClient());
      adapters.registerClient(V1beta1EventingAPIGroupDSL.class, new V1beta1EventingAPIGroupClient());
      adapters.registerClient(FlowControlAPIGroupDSL.class, new FlowControlAPIGroupClient());
      adapters.registerClient(V1FlowControlAPIGroupDSL.class, new V1FlowControlAPIGroupClient());
      adapters.registerClient(V1beta1FlowControlAPIGroupDSL.class, new V1beta1FlowControlAPIGroupClient());
      adapters.registerClient(V1beta2FlowControlAPIGroupDSL.class, new V1beta2FlowControlAPIGroupClient());
      adapters.registerClient(V1beta3FlowControlAPIGroupDSL.class, new V1beta3FlowControlAPIGroupClient());
      adapters.registerClient(MetricAPIGroupDSL.class, new MetricAPIGroupClient());
      adapters.registerClient(NetworkAPIGroupDSL.class, new NetworkAPIGroupClient());
      adapters.registerClient(PolicyAPIGroupDSL.class, new PolicyAPIGroupClient());
      adapters.registerClient(V1PolicyAPIGroupDSL.class, new V1PolicyAPIGroupClient());
      adapters.registerClient(V1beta1PolicyAPIGroupDSL.class, new V1beta1PolicyAPIGroupClient());
      adapters.registerClient(RbacAPIGroupDSL.class, new RbacAPIGroupClient());
      adapters.registerClient(SchedulingAPIGroupDSL.class, new SchedulingAPIGroupClient());
      adapters.registerClient(V1SchedulingAPIGroupDSL.class, new V1SchedulingAPIGroupClient());
      adapters.registerClient(V1beta1SchedulingAPIGroupDSL.class, new V1beta1SchedulingAPIGroupClient());
      adapters.registerClient(StorageAPIGroupDSL.class, new StorageAPIGroupClient());
      adapters.registerClient(V1StorageAPIGroupClient.class, new V1StorageAPIGroupClient());
      adapters.registerClient(V1beta1StorageAPIGroupClient.class, new V1beta1StorageAPIGroupClient());
      adapters.registerClient(V1APIGroupDSL.class, new V1APIGroupClient());
      adapters.registerClient(V1ApiextensionAPIGroupDSL.class, new V1ApiextensionsAPIGroupClient());
      adapters.registerClient(V1beta1ApiextensionAPIGroupDSL.class, new V1beta1ApiextensionsAPIGroupClient());
      adapters.registerClient(V1AuthorizationAPIGroupDSL.class, new V1AuthorizationAPIGroupClient());
      adapters.registerClient(V1beta1AuthorizationAPIGroupDSL.class, new V1beta1AuthorizationAPIGroupClient());
      adapters.registerClient(V1AuthenticationAPIGroupDSL.class, new V1AuthenticationAPIGroupClient());
      adapters.registerClient(V1Beta1AuthenticationAPIGroupDSL.class, new V1Beta1AuthenticationAPIGroupClient());
      adapters.registerClient(V1NetworkAPIGroupDSL.class, new V1NetworkAPIGroupClient());
      adapters.registerClient(V1beta1NetworkAPIGroupDSL.class, new V1beta1NetworkAPIGroupClient());
      adapters.registerClient(DiscoveryAPIGroupDSL.class, new DiscoveryAPIGroupClient());
      adapters.registerClient(V1beta1DiscoveryAPIGroupDSL.class, new V1beta1DiscoveryAPIGroupClient());
      adapters.registerClient(V1DiscoveryAPIGroupDSL.class, new V1DiscoveryAPIGroupClient());
      adapters.registerClient(DynamicResourceAllocationAPIGroupDSL.class, new DynamicResourceAllocationAPIGroupClient());
      adapters.registerClient(V1Alpha2DynamicResourceAllocationAPIGroupDSL.class, new V1Alpha2DynamicResourceAllocationAPIGroupClient());
      adapters.registerClient(CertificatesAPIGroupDSL.class, new CertificatesAPIGroupClient());
      adapters.registerClient(V1CertificatesAPIGroupDSL.class, new V1CertificatesAPIGroupClient());
      adapters.registerClient(V1beta1CertificatesAPIGroupDSL.class, new V1beta1CertificatesAPIGroupClient());
      adapters.registerClient(V1Alpha1CertificatesAPIGroupDSL.class, new V1Alpha1CertificatesAPIGroupClient());
   }

   protected void registerDefaultHandlers() {
      Handlers handlers = this.getHandlers();
      handlers.register(Pod.class, PodOperationsImpl::new);
      handlers.register(ServiceAccount.class, ServiceAccountOperationsImpl::new);
      handlers.register(Job.class, JobOperationsImpl::new);
      handlers.register(Service.class, ServiceOperationsImpl::new);
      handlers.register(Deployment.class, DeploymentOperationsImpl::new);
      handlers.register(io.fabric8.kubernetes.api.model.extensions.Deployment.class, io.fabric8.kubernetes.client.dsl.internal.extensions.v1beta1.DeploymentOperationsImpl::new);
      handlers.register(ReplicaSet.class, ReplicaSetOperationsImpl::new);
      handlers.register(io.fabric8.kubernetes.api.model.extensions.ReplicaSet.class, io.fabric8.kubernetes.client.dsl.internal.extensions.v1beta1.ReplicaSetOperationsImpl::new);
      handlers.register(ReplicationController.class, ReplicationControllerOperationsImpl::new);
      handlers.register(StatefulSet.class, StatefulSetOperationsImpl::new);
      handlers.register(CertificateSigningRequest.class, CertificateSigningRequestOperationsImpl::new);
      handlers.register(io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequest.class, io.fabric8.kubernetes.client.dsl.internal.certificates.v1beta1.CertificateSigningRequestOperationsImpl::new);
   }

   public NamespacedKubernetesClient inNamespace(String name) {
      return (NamespacedKubernetesClient)this.newClient(this.createInNamespaceContext(name, false), NamespacedKubernetesClient.class);
   }

   protected OperationContext createInNamespaceContext(String name, boolean any) {
      if (!any && name == null) {
         throw new KubernetesClientException("namespace cannot be null");
      } else {
         return HasMetadataOperationsImpl.defaultContext(this).withNamespace(name);
      }
   }

   public LeaderElectorBuilder leaderElector() {
      return new LeaderElectorBuilder(this, this.getExecutor());
   }

   public FunctionCallable withRequestConfig(RequestConfig requestConfig) {
      return new WithRequestCallable(this, requestConfig);
   }

   public NonNamespaceOperation componentstatuses() {
      return this.resources(ComponentStatus.class, ComponentStatusList.class);
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable load(InputStream is) {
      return this.resourceListFor(this.kubernetesSerialization.unmarshal(is));
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(KubernetesResourceList item) {
      return this.resourceListFor(item);
   }

   public NamespaceVisitFromServerGetWatchDeleteRecreateWaitApplicableListImpl resourceListFor(Object item) {
      return new NamespaceVisitFromServerGetWatchDeleteRecreateWaitApplicableListImpl(this, item);
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(HasMetadata... items) {
      return this.resourceList((KubernetesResourceList)((KubernetesListBuilder)(new KubernetesListBuilder()).withItems(items)).build());
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(Collection items) {
      return this.resourceList((KubernetesResourceList)((KubernetesListBuilder)(new KubernetesListBuilder()).withItems(new ArrayList(items))).build());
   }

   public NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable resourceList(String s) {
      return this.resourceListFor(this.kubernetesSerialization.unmarshal(s));
   }

   public NamespaceableResource resource(HasMetadata item) {
      ResourceHandler<T, ?> resourceHandler = this.getHandlers().get(item, this);
      HasMetadataOperation<T, ?, ?> op = resourceHandler.operation(this, (Class)null);
      return new NamespaceableResourceAdapter(item, op);
   }

   private NamespaceableResource resource(Object resource) {
      if (resource instanceof HasMetadata) {
         return this.resource((HasMetadata)resource);
      } else {
         throw new KubernetesClientException("Unable to create a valid resource from the provided object (" + resource.getClass().getName() + ")");
      }
   }

   public NamespaceableResource resource(String s) {
      return this.resource(this.kubernetesSerialization.unmarshal(s));
   }

   public NamespaceableResource resource(InputStream is) {
      return this.resource(this.kubernetesSerialization.unmarshal(is));
   }

   public MixedOperation bindings() {
      return this.resources(Binding.class);
   }

   public MixedOperation endpoints() {
      return this.resources(Endpoints.class, EndpointsList.class);
   }

   public NonNamespaceOperation namespaces() {
      return this.resources(Namespace.class, NamespaceList.class);
   }

   public NonNamespaceOperation nodes() {
      return this.resources(Node.class, NodeList.class);
   }

   public NonNamespaceOperation persistentVolumes() {
      return this.resources(PersistentVolume.class, PersistentVolumeList.class);
   }

   public MixedOperation persistentVolumeClaims() {
      return this.resources(PersistentVolumeClaim.class, PersistentVolumeClaimList.class);
   }

   public MixedOperation pods() {
      return new PodOperationsImpl(this);
   }

   public MixedOperation replicationControllers() {
      return new ReplicationControllerOperationsImpl(this);
   }

   public MixedOperation resourceQuotas() {
      return this.resources(ResourceQuota.class, ResourceQuotaList.class);
   }

   public SchedulingAPIGroupDSL scheduling() {
      return (SchedulingAPIGroupDSL)this.adapt(SchedulingAPIGroupClient.class);
   }

   public MixedOperation secrets() {
      return this.resources(Secret.class, SecretList.class);
   }

   public MixedOperation services() {
      return new ServiceOperationsImpl(this);
   }

   public MixedOperation serviceAccounts() {
      return new ServiceAccountOperationsImpl(this);
   }

   public NonNamespaceOperation apiServices() {
      return this.resources(APIService.class, APIServiceList.class);
   }

   public MixedOperation configMaps() {
      return this.resources(ConfigMap.class, ConfigMapList.class);
   }

   public MixedOperation limitRanges() {
      return this.resources(LimitRange.class, LimitRangeList.class);
   }

   public ApiextensionsAPIGroupDSL apiextensions() {
      return (ApiextensionsAPIGroupDSL)this.adapt(ApiextensionsAPIGroupClient.class);
   }

   public NonNamespaceOperation certificateSigningRequests() {
      return this.resources(io.fabric8.kubernetes.api.model.certificates.v1beta1.CertificateSigningRequest.class, CertificateSigningRequestList.class);
   }

   public CertificatesAPIGroupDSL certificates() {
      return (CertificatesAPIGroupDSL)this.adapt(CertificatesAPIGroupClient.class);
   }

   public AuthorizationAPIGroupDSL authorization() {
      return (AuthorizationAPIGroupDSL)this.adapt(AuthorizationAPIGroupClient.class);
   }

   public AuthenticationAPIGroupDSL authentication() {
      return (AuthenticationAPIGroupDSL)this.adapt(AuthenticationAPIGroupClient.class);
   }

   public InOutCreateable tokenReviews() {
      return this.getHandlers().getNonListingOperation(TokenReview.class, this);
   }

   public MixedOperation genericKubernetesResources(String apiVersion, String kind) {
      ResourceDefinitionContext context = this.getHandlers().getResourceDefinitionContext(apiVersion, kind, this);
      if (context == null) {
         throw new KubernetesClientException("Could not find the metadata for the given apiVersion and kind, please pass a ResourceDefinitionContext instead");
      } else {
         return this.genericKubernetesResources(context);
      }
   }

   public MixedOperation genericKubernetesResources(ResourceDefinitionContext context) {
      return this.newHasMetadataOperation(context, GenericKubernetesResource.class, GenericKubernetesResourceList.class);
   }

   public DiscoveryAPIGroupDSL discovery() {
      return (DiscoveryAPIGroupDSL)this.adapt(DiscoveryAPIGroupClient.class);
   }

   public DynamicResourceAllocationAPIGroupDSL dynamicResourceAllocation() {
      return (DynamicResourceAllocationAPIGroupDSL)this.adapt(DynamicResourceAllocationAPIGroupClient.class);
   }

   public EventingAPIGroupDSL events() {
      return (EventingAPIGroupDSL)this.adapt(EventingAPIGroupClient.class);
   }

   public NamespacedKubernetesClient inAnyNamespace() {
      return (NamespacedKubernetesClient)this.newClient(this.createInNamespaceContext((String)null, true), NamespacedKubernetesClient.class);
   }

   protected KubernetesClientImpl copy() {
      return new KubernetesClientImpl(this);
   }

   public ExtensionsAPIGroupDSL extensions() {
      return (ExtensionsAPIGroupDSL)this.adapt(ExtensionsAPIGroupClient.class);
   }

   public FlowControlAPIGroupDSL flowControl() {
      return (FlowControlAPIGroupDSL)this.adapt(FlowControlAPIGroupClient.class);
   }

   public VersionInfo getVersion() {
      return this.getVersionInfo("version");
   }

   public VersionInfo getKubernetesVersion() {
      return this.getVersionInfo("version");
   }

   public V1APIGroupDSL v1() {
      return (V1APIGroupDSL)this.adapt(V1APIGroupClient.class);
   }

   public AdmissionRegistrationAPIGroupDSL admissionRegistration() {
      return (AdmissionRegistrationAPIGroupDSL)this.adapt(AdmissionRegistrationAPIGroupClient.class);
   }

   public AppsAPIGroupDSL apps() {
      return (AppsAPIGroupDSL)this.adapt(AppsAPIGroupClient.class);
   }

   public AutoscalingAPIGroupDSL autoscaling() {
      return (AutoscalingAPIGroupDSL)this.adapt(AutoscalingAPIGroupClient.class);
   }

   public NetworkAPIGroupDSL network() {
      return (NetworkAPIGroupDSL)this.adapt(NetworkAPIGroupClient.class);
   }

   public StorageAPIGroupDSL storage() {
      return (StorageAPIGroupDSL)this.adapt(StorageAPIGroupClient.class);
   }

   public BatchAPIGroupDSL batch() {
      return (BatchAPIGroupDSL)this.adapt(BatchAPIGroupClient.class);
   }

   public MetricAPIGroupDSL top() {
      return (MetricAPIGroupDSL)this.adapt(MetricAPIGroupClient.class);
   }

   public PolicyAPIGroupDSL policy() {
      return (PolicyAPIGroupDSL)this.adapt(PolicyAPIGroupClient.class);
   }

   public RbacAPIGroupDSL rbac() {
      return (RbacAPIGroupDSL)this.adapt(RbacAPIGroupClient.class);
   }

   public SharedInformerFactory informers() {
      return new SharedInformerFactoryImpl(this);
   }

   public MixedOperation leases() {
      return this.resources(Lease.class, LeaseList.class);
   }

   public RunOperations run() {
      return new RunOperations(this, (new RunConfigBuilder()).build());
   }

   public NonNamespaceOperation runtimeClasses() {
      return this.resources(RuntimeClass.class, RuntimeClassList.class);
   }

   public Client newClient(RequestConfig requestConfig) {
      return this.newClient(HasMetadataOperationsImpl.defaultContext(this).withRequestConfig(requestConfig), Client.class);
   }

   public void visitResources(ApiVisitor visitor) {
      if (!this.visitGroups(visitor, Arrays.asList(((APIGroupBuilder)((APIGroupBuilder)(new APIGroupBuilder()).withName("")).withVersions(new GroupVersionForDiscovery[]{((GroupVersionForDiscoveryBuilder)(new GroupVersionForDiscoveryBuilder()).withGroupVersion("v1")).build()})).build()))) {
         APIGroupList apiGroups = this.getApiGroups();
         if (apiGroups != null) {
            this.visitGroups(visitor, apiGroups.getGroups());
         }

      }
   }

   private boolean visitGroups(ApiVisitor visitor, List groups) {
      for(APIGroup group : groups) {
         switch (visitor.visitApiGroup(group.getName())) {
            case TERMINATE:
               return true;
            case SKIP:
            default:
               continue;
            case CONTINUE:
         }

         for(GroupVersionForDiscovery groupForDiscovery : group.getVersions()) {
            String groupVersion = groupForDiscovery.getGroupVersion();
            String groupName = (String)Utils.getNonNullOrElse(ApiVersionUtil.trimGroupOrNull(groupVersion), "");
            String version = ApiVersionUtil.trimVersion(groupVersion);
            ApiVisitor.ApiVisitResult versionResult = visitor.visitApiGroupVersion(groupName, version);
            switch (versionResult) {
               case TERMINATE:
                  return true;
               case SKIP:
               default:
                  break;
               case CONTINUE:
                  APIResourceList apiResources = this.getApiResources(groupVersion);
                  if (apiResources == null) {
                     if (logger.isDebugEnabled()) {
                        logger.debug("{} is discoverable, but is not yet populating an APIResource list", groupVersion);
                     }
                  } else {
                     for(APIResource resource : apiResources.getResources()) {
                        if (!resource.getName().contains("/")) {
                           ApiVisitor.ApiVisitResult resourceResult = visitor.visitResource(groupName, version, resource, this.genericKubernetesResources(ResourceDefinitionContext.fromApiResource(groupVersion, resource)));
                           if (resourceResult == ApiVisitResult.TERMINATE) {
                              return true;
                           }
                        }
                     }
                  }
            }
         }
      }

      return false;
   }
}
