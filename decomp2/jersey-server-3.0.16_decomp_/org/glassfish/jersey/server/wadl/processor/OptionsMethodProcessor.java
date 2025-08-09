package org.glassfish.jersey.server.wadl.processor;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.model.ModelProcessor;
import org.glassfish.jersey.server.model.ResourceModel;
import org.glassfish.jersey.server.model.RuntimeResource;
import org.glassfish.jersey.server.model.internal.ModelProcessorUtil;

@Priority(Integer.MAX_VALUE)
public class OptionsMethodProcessor implements ModelProcessor {
   private final List methodList = new ArrayList();

   public OptionsMethodProcessor() {
      this.methodList.add(new ModelProcessorUtil.Method("OPTIONS", MediaType.WILDCARD_TYPE, MediaType.TEXT_PLAIN_TYPE, PlainTextOptionsInflector.class));
      this.methodList.add(new ModelProcessorUtil.Method("OPTIONS", MediaType.WILDCARD_TYPE, MediaType.WILDCARD_TYPE, GenericOptionsInflector.class));
   }

   public ResourceModel processResourceModel(ResourceModel resourceModel, Configuration configuration) {
      return ModelProcessorUtil.enhanceResourceModel(resourceModel, false, this.methodList, true).build();
   }

   public ResourceModel processSubResource(ResourceModel subResourceModel, Configuration configuration) {
      return ModelProcessorUtil.enhanceResourceModel(subResourceModel, true, this.methodList, true).build();
   }

   private static class PlainTextOptionsInflector implements Inflector {
      @Inject
      private Provider extendedUriInfo;

      public Response apply(ContainerRequestContext containerRequestContext) {
         Set<String> allowedMethods = ModelProcessorUtil.getAllowedMethods((RuntimeResource)((ExtendedUriInfo)this.extendedUriInfo.get()).getMatchedRuntimeResources().get(0));
         String allowedList = allowedMethods.toString();
         String optionsBody = allowedList.substring(1, allowedList.length() - 1);
         return Response.ok(optionsBody, MediaType.TEXT_PLAIN_TYPE).allow(allowedMethods).build();
      }
   }

   private static class GenericOptionsInflector implements Inflector {
      @Inject
      private Provider extendedUriInfo;

      public Response apply(ContainerRequestContext containerRequestContext) {
         Set<String> allowedMethods = ModelProcessorUtil.getAllowedMethods((RuntimeResource)((ExtendedUriInfo)this.extendedUriInfo.get()).getMatchedRuntimeResources().get(0));
         return Response.ok().allow(allowedMethods).header("Content-Length", "0").type((MediaType)containerRequestContext.getAcceptableMediaTypes().get(0)).build();
      }
   }
}
