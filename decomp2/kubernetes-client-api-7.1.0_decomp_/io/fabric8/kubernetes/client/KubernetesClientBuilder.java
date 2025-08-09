package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.utils.HttpClientUtils;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class KubernetesClientBuilder {
   private static final String DEFAULT_IMPLEMENTATION = "io.fabric8.kubernetes.client.impl.KubernetesClientImpl";
   private Config config;
   private HttpClient.Factory factory;
   private Class clazz;
   private ExecutorSupplier executorSupplier;
   private Consumer builderConsumer;
   private KubernetesSerialization kubernetesSerialization = new KubernetesSerialization();

   public KubernetesClientBuilder() {
      try {
         this.clazz = Thread.currentThread().getContextClassLoader().loadClass("io.fabric8.kubernetes.client.impl.KubernetesClientImpl");
      } catch (ClassCastException | NullPointerException | ClassNotFoundException var4) {
         try {
            this.clazz = KubernetesClient.class.getClassLoader().loadClass("io.fabric8.kubernetes.client.impl.KubernetesClientImpl");
         } catch (Exception ex) {
            throw KubernetesClientException.launderThrowable(ex);
         }
      }

   }

   KubernetesClientBuilder(Class clazz) {
      this.clazz = clazz;
   }

   public KubernetesClient build() {
      if (this.config == null) {
         this.config = (new ConfigBuilder()).build();
      }

      try {
         if (this.factory == null) {
            this.factory = HttpClientUtils.getHttpClientFactory();
         }

         HttpClient client = this.getHttpClient();
         return (KubernetesClient)this.clazz.getConstructor(HttpClient.class, Config.class, ExecutorSupplier.class, KubernetesSerialization.class).newInstance(client, this.config, this.executorSupplier, this.kubernetesSerialization);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   HttpClient getHttpClient() {
      HttpClient.Builder builder = this.factory.newBuilder(this.config);
      if (this.builderConsumer != null) {
         this.builderConsumer.accept(builder);
      }

      return builder.build();
   }

   public KubernetesClientBuilder withKubernetesSerialization(KubernetesSerialization kubernetesSerialization) {
      this.kubernetesSerialization = (KubernetesSerialization)Utils.checkNotNull(kubernetesSerialization, "kubernetesSerialization must not be null");
      return this;
   }

   public KubernetesClientBuilder withConfig(Config config) {
      this.config = config;
      return this;
   }

   public KubernetesClientBuilder withConfig(String config) {
      return this.withConfig((Config)this.kubernetesSerialization.unmarshal(config, Config.class));
   }

   public KubernetesClientBuilder withConfig(InputStream config) {
      return this.withConfig((Config)this.kubernetesSerialization.unmarshal(config, Config.class));
   }

   public KubernetesClientBuilder withHttpClientFactory(HttpClient.Factory factory) {
      this.factory = factory;
      return this;
   }

   public KubernetesClientBuilder withTaskExecutor(Executor executor) {
      this.executorSupplier = () -> executor;
      return this;
   }

   public KubernetesClientBuilder withTaskExecutorSupplier(ExecutorSupplier executorSupplier) {
      this.executorSupplier = executorSupplier;
      return this;
   }

   public KubernetesClientBuilder withHttpClientBuilderConsumer(Consumer consumer) {
      this.builderConsumer = consumer;
      return this;
   }

   public ConfigNested editOrNewConfig() {
      return new ConfigNested();
   }

   @FunctionalInterface
   public interface ExecutorSupplier extends Supplier {
      default void onClose(Executor executor) {
      }
   }

   public class ConfigNested extends ConfigFluent {
      private ConfigBuilder builder;

      private ConfigNested() {
         this.builder = new ConfigBuilder(this, KubernetesClientBuilder.this.config);
      }

      public KubernetesClientBuilder endConfig() {
         KubernetesClientBuilder.this.config = this.builder.build();
         return KubernetesClientBuilder.this;
      }
   }
}
