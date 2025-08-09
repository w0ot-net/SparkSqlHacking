package org.glassfish.jersey.server;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.GenericType;
import java.util.Collection;
import org.glassfish.jersey.internal.BootstrapBag;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.server.internal.JerseyResourceContext;
import org.glassfish.jersey.server.internal.ProcessingProviders;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.model.ModelProcessor;
import org.glassfish.jersey.server.model.ResourceMethodInvoker;
import org.glassfish.jersey.server.model.ResourceModel;
import org.glassfish.jersey.server.spi.ComponentProvider;
import org.glassfish.jersey.server.spi.internal.ValueParamProvider;

public class ServerBootstrapBag extends BootstrapBag {
   private Application application;
   private ApplicationHandler applicationHandler;
   private Collection valueParamProviders;
   private MultivaluedParameterExtractorProvider multivaluedParameterExtractorProvider;
   private ProcessingProviders processingProviders;
   private JerseyResourceContext resourceContext;
   private LazyValue componentProviders;
   private ResourceMethodInvoker.Builder resourceMethodInvokerBuilder;
   private ResourceBag resourceBag;
   private ResourceModel resourceModel;
   private Collection modelProcessors;

   public Collection getModelProcessors() {
      return this.modelProcessors;
   }

   public void setModelProcessors(Collection modelProcessors) {
      this.modelProcessors = modelProcessors;
   }

   public ResourceBag getResourceBag() {
      requireNonNull(this.resourceBag, ResourceBag.class);
      return this.resourceBag;
   }

   public void setResourceBag(ResourceBag resourceBag) {
      this.resourceBag = resourceBag;
   }

   public ResourceConfig getRuntimeConfig() {
      return (ResourceConfig)this.getConfiguration();
   }

   public Application getApplication() {
      requireNonNull(this.application, Application.class);
      return this.application;
   }

   public void setApplication(Application application) {
      this.application = application;
   }

   public ApplicationHandler getApplicationHandler() {
      requireNonNull(this.applicationHandler, ApplicationHandler.class);
      return this.applicationHandler;
   }

   public void setApplicationHandler(ApplicationHandler applicationHandler) {
      this.applicationHandler = applicationHandler;
   }

   public ProcessingProviders getProcessingProviders() {
      requireNonNull(this.processingProviders, ProcessingProviders.class);
      return this.processingProviders;
   }

   public void setProcessingProviders(ProcessingProviders processingProviders) {
      this.processingProviders = processingProviders;
   }

   public MultivaluedParameterExtractorProvider getMultivaluedParameterExtractorProvider() {
      requireNonNull(this.multivaluedParameterExtractorProvider, MultivaluedParameterExtractorProvider.class);
      return this.multivaluedParameterExtractorProvider;
   }

   public void setMultivaluedParameterExtractorProvider(MultivaluedParameterExtractorProvider provider) {
      this.multivaluedParameterExtractorProvider = provider;
   }

   public Collection getValueParamProviders() {
      requireNonNull(this.valueParamProviders, (new GenericType() {
      }).getType());
      return this.valueParamProviders;
   }

   public void setValueParamProviders(Collection valueParamProviders) {
      this.valueParamProviders = valueParamProviders;
   }

   public JerseyResourceContext getResourceContext() {
      requireNonNull(this.resourceContext, JerseyResourceContext.class);
      return this.resourceContext;
   }

   public void setResourceContext(JerseyResourceContext resourceContext) {
      this.resourceContext = resourceContext;
   }

   public LazyValue getComponentProviders() {
      requireNonNull(this.componentProviders, (new GenericType() {
      }).getType());
      return this.componentProviders;
   }

   public void setComponentProviders(LazyValue componentProviders) {
      this.componentProviders = componentProviders;
   }

   public ResourceMethodInvoker.Builder getResourceMethodInvokerBuilder() {
      requireNonNull(this.resourceMethodInvokerBuilder, ResourceMethodInvoker.Builder.class);
      return this.resourceMethodInvokerBuilder;
   }

   public void setResourceMethodInvokerBuilder(ResourceMethodInvoker.Builder resourceMethodInvokerBuilder) {
      this.resourceMethodInvokerBuilder = resourceMethodInvokerBuilder;
   }

   public ResourceModel getResourceModel() {
      requireNonNull(this.resourceModel, ResourceModel.class);
      return this.resourceModel;
   }

   public void setResourceModel(ResourceModel resourceModel) {
      this.resourceModel = resourceModel;
   }
}
