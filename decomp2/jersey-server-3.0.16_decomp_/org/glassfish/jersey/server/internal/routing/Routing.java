package org.glassfish.jersey.server.internal.routing;

import jakarta.ws.rs.core.Configuration;
import java.util.Collection;
import java.util.function.Function;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.process.internal.ChainableStage;
import org.glassfish.jersey.process.internal.Stage;
import org.glassfish.jersey.server.internal.JerseyResourceContext;
import org.glassfish.jersey.server.internal.ProcessingProviders;
import org.glassfish.jersey.server.model.ModelProcessor;
import org.glassfish.jersey.server.model.ResourceMethodInvoker;
import org.glassfish.jersey.server.model.RuntimeResourceModel;
import org.glassfish.jersey.server.spi.internal.ValueParamProvider;

public final class Routing {
   private Routing() {
      throw new AssertionError("No instances allowed.");
   }

   public static Stage matchedEndpointExtractor() {
      return new MatchedEndpointExtractorStage();
   }

   public static Builder forModel(RuntimeResourceModel resourceModel) {
      return new Builder(resourceModel);
   }

   public static final class Builder {
      private final RuntimeResourceModel resourceModel;
      private JerseyResourceContext resourceContext;
      private Configuration config;
      private MessageBodyWorkers entityProviders;
      private Collection valueSuppliers;
      private Iterable modelProcessors;
      private Function createServiceFunction;
      private ProcessingProviders processingProviders;
      private ResourceMethodInvoker.Builder resourceMethodInvokerBuilder;

      private Builder(RuntimeResourceModel resourceModel) {
         if (resourceModel == null) {
            throw new NullPointerException("Resource model must not be null.");
         } else {
            this.resourceModel = resourceModel;
         }
      }

      public Builder resourceContext(JerseyResourceContext resourceContext) {
         this.resourceContext = resourceContext;
         return this;
      }

      public Builder configuration(Configuration config) {
         this.config = config;
         return this;
      }

      public Builder entityProviders(MessageBodyWorkers workers) {
         this.entityProviders = workers;
         return this;
      }

      public Builder valueSupplierProviders(Collection valueSuppliers) {
         this.valueSuppliers = valueSuppliers;
         return this;
      }

      public Builder processingProviders(ProcessingProviders processingProviders) {
         this.processingProviders = processingProviders;
         return this;
      }

      public Builder modelProcessors(Iterable modelProcessors) {
         this.modelProcessors = modelProcessors;
         return this;
      }

      public Builder createService(Function createServiceFunction) {
         this.createServiceFunction = createServiceFunction;
         return this;
      }

      public Builder resourceMethodInvokerBuilder(ResourceMethodInvoker.Builder resourceMethodInvokerBuilder) {
         this.resourceMethodInvokerBuilder = resourceMethodInvokerBuilder;
         return this;
      }

      public ChainableStage buildStage() {
         if (this.resourceContext == null) {
            throw new NullPointerException("Resource context is not set.");
         } else if (this.config == null) {
            throw new NullPointerException("Runtime configuration is not set.");
         } else if (this.entityProviders == null) {
            throw new NullPointerException("Entity providers are not set.");
         } else if (this.valueSuppliers == null) {
            throw new NullPointerException("Value supplier providers are not set.");
         } else if (this.modelProcessors == null) {
            throw new NullPointerException("Model processors are not set.");
         } else if (this.createServiceFunction == null) {
            throw new NullPointerException("Create function is not set.");
         } else if (this.processingProviders == null) {
            throw new NullPointerException("Processing providers are not set.");
         } else if (this.resourceMethodInvokerBuilder == null) {
            throw new NullPointerException("ResourceMethodInvokerBuilder is not set.");
         } else {
            RuntimeModelBuilder runtimeModelBuilder = new RuntimeModelBuilder(this.resourceContext, this.config, this.entityProviders, this.valueSuppliers, this.processingProviders, this.resourceMethodInvokerBuilder, this.modelProcessors, this.createServiceFunction);
            return new RoutingStage(runtimeModelBuilder.buildModel(this.resourceModel, false));
         }
      }
   }
}
