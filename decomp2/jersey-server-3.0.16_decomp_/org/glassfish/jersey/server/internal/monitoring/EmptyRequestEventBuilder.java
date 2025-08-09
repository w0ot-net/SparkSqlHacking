package org.glassfish.jersey.server.internal.monitoring;

import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.monitoring.RequestEvent;

public class EmptyRequestEventBuilder implements RequestEventBuilder {
   public static final EmptyRequestEventBuilder INSTANCE = new EmptyRequestEventBuilder();

   public RequestEventBuilder setExceptionMapper(ExceptionMapper exceptionMapper) {
      return this;
   }

   public RequestEventBuilder setContainerRequest(ContainerRequest containerRequest) {
      return this;
   }

   public RequestEventBuilder setContainerResponse(ContainerResponse containerResponse) {
      return this;
   }

   public RequestEventBuilder setSuccess(boolean success) {
      return this;
   }

   public RequestEventBuilder setResponseWritten(boolean responseWritten) {
      return this;
   }

   public RequestEventBuilder setException(Throwable throwable, RequestEvent.ExceptionCause exceptionCause) {
      return this;
   }

   public RequestEventBuilder setExtendedUriInfo(ExtendedUriInfo extendedUriInfo) {
      return this;
   }

   public RequestEventBuilder setContainerResponseFilters(Iterable containerResponseFilters) {
      return this;
   }

   public RequestEventBuilder setContainerRequestFilters(Iterable containerRequestFilters) {
      return this;
   }

   public RequestEventBuilder setResponseSuccessfullyMapped(boolean responseSuccessfullyMapped) {
      return this;
   }

   public RequestEvent build(RequestEvent.Type eventType) {
      return null;
   }
}
