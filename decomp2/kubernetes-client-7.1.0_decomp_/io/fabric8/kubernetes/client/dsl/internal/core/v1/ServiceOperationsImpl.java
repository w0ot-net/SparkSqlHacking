package io.fabric8.kubernetes.client.dsl.internal.core.v1;

import io.fabric8.kubernetes.api.model.Endpoints;
import io.fabric8.kubernetes.api.model.EndpointsList;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.api.model.ServiceFluent;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.LocalPortForward;
import io.fabric8.kubernetes.client.PortForward;
import io.fabric8.kubernetes.client.ServiceToURLProvider;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.PodResource;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.ServiceResource;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.utils.URLUtils;
import java.net.InetAddress;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ServiceOperationsImpl extends HasMetadataOperation implements ServiceResource {
   public static final String EXTERNAL_NAME = "ExternalName";

   public ServiceOperationsImpl(Client client) {
      this(HasMetadataOperationsImpl.defaultContext(client));
   }

   private ServiceOperationsImpl(OperationContext context) {
      super(context.withPlural("services"), Service.class, ServiceList.class);
   }

   public ServiceOperationsImpl newInstance(OperationContext context) {
      return new ServiceOperationsImpl(context);
   }

   public Service waitUntilReady(long amount, TimeUnit timeUnit) {
      long started = System.nanoTime();
      super.waitUntilReady(amount, timeUnit);
      long alreadySpent = System.nanoTime() - started;
      long remaining = Math.max(10000L, timeUnit.toNanos(amount) - alreadySpent);
      Resource<Endpoints> endpointsOperation = (Resource)((NonNamespaceOperation)this.context.getClient().resources(Endpoints.class, EndpointsList.class).inNamespace(this.context.getNamespace())).withName(this.context.getName());
      endpointsOperation.waitUntilReady(remaining, TimeUnit.MILLISECONDS);
      return (Service)this.get();
   }

   public String getURL(String portName) {
      String clusterIP = ((Service)this.getItemOrRequireFromServer()).getSpec().getClusterIP();
      if ("None".equals(clusterIP)) {
         String var10002 = ((Service)this.getItemOrRequireFromServer()).getMetadata().getName();
         throw new IllegalStateException("Service: " + var10002 + " in namespace " + this.namespace + " is head-less. Search for endpoints instead");
      } else {
         return this.getUrlHelper(portName);
      }
   }

   private String getUrlHelper(String portName) {
      List<ServiceToURLProvider> servicesList = getServiceToURLProviders(Thread.currentThread().getContextClassLoader());
      if (servicesList.isEmpty()) {
         servicesList = getServiceToURLProviders(this.getClass().getClassLoader());
      }

      Collections.sort(servicesList, new ServiceToUrlSortComparator());

      for(ServiceToURLProvider serviceToURLProvider : servicesList) {
         String url = serviceToURLProvider.getURL((Service)this.getItemOrRequireFromServer(), portName, this.namespace, (KubernetesClient)this.context.getClient().adapt(KubernetesClient.class));
         if (url != null && URLUtils.isValidURL(url)) {
            return url;
         }
      }

      return null;
   }

   private static List getServiceToURLProviders(ClassLoader loader) {
      Iterator<ServiceToURLProvider> iterator = ServiceLoader.load(ServiceToURLProvider.class, loader).iterator();
      List<ServiceToURLProvider> servicesList = new ArrayList();

      while(iterator.hasNext()) {
         servicesList.add((ServiceToURLProvider)iterator.next());
      }

      return servicesList;
   }

   private Pod matchingPod() {
      Service item = (Service)this.requireFromServer();
      Map<String, String> labels = item.getSpec().getSelector();
      PodList list = (PodList)(new PodOperationsImpl(this.context.getClient())).inNamespace(item.getMetadata().getNamespace()).withLabels(labels).list();
      return (Pod)list.getItems().stream().findFirst().orElseThrow(() -> new IllegalStateException("Could not find matching pod for service:" + item + "."));
   }

   public PortForward portForward(int port, ReadableByteChannel in, WritableByteChannel out) {
      Pod m = this.matchingPod();
      return ((PodResource)(new PodOperationsImpl(this.context.getClient())).inNamespace(m.getMetadata().getNamespace()).withName(m.getMetadata().getName())).portForward(port, in, out);
   }

   public LocalPortForward portForward(int port, int localPort) {
      Pod m = this.matchingPod();
      return ((PodResource)(new PodOperationsImpl(this.context.getClient())).inNamespace(m.getMetadata().getNamespace()).withName(m.getMetadata().getName())).portForward(port, localPort);
   }

   public LocalPortForward portForward(int port, InetAddress localInetAddress, int localPort) {
      Pod m = this.matchingPod();
      return ((PodResource)(new PodOperationsImpl(this.context.getClient())).inNamespace(m.getMetadata().getNamespace()).withName(m.getMetadata().getName())).portForward(port, localInetAddress, localPort);
   }

   public LocalPortForward portForward(int port) {
      Pod m = this.matchingPod();
      return ((PodResource)(new PodOperationsImpl(this.context.getClient())).inNamespace(m.getMetadata().getNamespace()).withName(m.getMetadata().getName())).portForward(port);
   }

   protected Service modifyItemForReplaceOrPatch(Supplier currentSupplier, Service item) {
      if (!this.isExternalNameService(item)) {
         Service old = (Service)currentSupplier.get();
         return ((ServiceBuilder)((ServiceFluent.SpecNested)(new ServiceBuilder(item)).editSpec().withClusterIP(old.getSpec().getClusterIP())).endSpec()).build();
      } else {
         return item;
      }
   }

   private boolean isExternalNameService(Service item) {
      return item.getSpec() != null && item.getSpec().getType() != null ? item.getSpec().getType().equals("ExternalName") : false;
   }

   public class ServiceToUrlSortComparator implements Comparator {
      public int compare(ServiceToURLProvider first, ServiceToURLProvider second) {
         return first.getPriority() - second.getPriority();
      }
   }
}
