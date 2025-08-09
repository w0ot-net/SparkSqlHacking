package org.glassfish.jersey.server.internal.monitoring;

import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.monitoring.RequestEvent;

public class RequestEventImpl implements RequestEvent {
   private final RequestEvent.Type type;
   private final ContainerRequest containerRequest;
   private final ContainerResponse containerResponse;
   private final Throwable throwable;
   private final ExtendedUriInfo extendedUriInfo;
   private final Iterable containerResponseFilters;
   private final Iterable containerRequestFilters;
   private final ExceptionMapper exceptionMapper;
   private final boolean success;
   private final boolean responseSuccessfullyMapped;
   private final RequestEvent.ExceptionCause exceptionCause;
   private final boolean responseWritten;

   private RequestEventImpl(RequestEvent.Type type, ContainerRequest containerRequest, ContainerResponse containerResponse, Throwable throwable, ExtendedUriInfo extendedUriInfo, Iterable containerResponseFilters, Iterable containerRequestFilters, ExceptionMapper exceptionMapper, boolean success, boolean responseSuccessfullyMapped, RequestEvent.ExceptionCause exceptionCause, boolean responseWritten) {
      this.type = type;
      this.containerRequest = containerRequest;
      this.containerResponse = containerResponse;
      this.throwable = throwable;
      this.extendedUriInfo = extendedUriInfo;
      this.containerResponseFilters = containerResponseFilters;
      this.containerRequestFilters = containerRequestFilters;
      this.exceptionMapper = exceptionMapper;
      this.success = success;
      this.responseSuccessfullyMapped = responseSuccessfullyMapped;
      this.exceptionCause = exceptionCause;
      this.responseWritten = responseWritten;
   }

   public ContainerRequest getContainerRequest() {
      return this.containerRequest;
   }

   public ContainerResponse getContainerResponse() {
      return this.containerResponse;
   }

   public Throwable getException() {
      return this.throwable;
   }

   public RequestEvent.Type getType() {
      return this.type;
   }

   public ExtendedUriInfo getUriInfo() {
      return this.extendedUriInfo;
   }

   public ExceptionMapper getExceptionMapper() {
      return this.exceptionMapper;
   }

   public Iterable getContainerRequestFilters() {
      return this.containerRequestFilters;
   }

   public Iterable getContainerResponseFilters() {
      return this.containerResponseFilters;
   }

   public boolean isSuccess() {
      return this.success;
   }

   public boolean isResponseSuccessfullyMapped() {
      return this.responseSuccessfullyMapped;
   }

   public RequestEvent.ExceptionCause getExceptionCause() {
      return this.exceptionCause;
   }

   public boolean isResponseWritten() {
      return this.responseWritten;
   }

   public static class Builder implements RequestEventBuilder {
      private ContainerRequest containerRequest;
      private ContainerResponse containerResponse;
      private Throwable throwable;
      private ExtendedUriInfo extendedUriInfo;
      private Iterable containerResponseFilters;
      private Iterable containerRequestFilters;
      private ExceptionMapper exceptionMapper;
      private boolean success;
      private boolean responseWritten;
      private boolean responseSuccessfullyMapped;
      private RequestEvent.ExceptionCause exceptionCause;

      public Builder setExceptionMapper(ExceptionMapper exceptionMapper) {
         this.exceptionMapper = exceptionMapper;
         return this;
      }

      public Builder setContainerRequest(ContainerRequest containerRequest) {
         this.containerRequest = containerRequest;
         return this;
      }

      public Builder setContainerResponse(ContainerResponse containerResponse) {
         this.containerResponse = containerResponse;
         return this;
      }

      public Builder setResponseWritten(boolean responseWritten) {
         this.responseWritten = responseWritten;
         return this;
      }

      public Builder setSuccess(boolean success) {
         this.success = success;
         return this;
      }

      public Builder setException(Throwable throwable, RequestEvent.ExceptionCause exceptionCause) {
         this.throwable = throwable;
         this.exceptionCause = exceptionCause;
         return this;
      }

      public Builder setExtendedUriInfo(ExtendedUriInfo extendedUriInfo) {
         this.extendedUriInfo = extendedUriInfo;
         return this;
      }

      public Builder setContainerResponseFilters(Iterable containerResponseFilters) {
         this.containerResponseFilters = containerResponseFilters;
         return this;
      }

      public Builder setContainerRequestFilters(Iterable containerRequestFilters) {
         this.containerRequestFilters = containerRequestFilters;
         return this;
      }

      public Builder setResponseSuccessfullyMapped(boolean responseSuccessfullyMapped) {
         this.responseSuccessfullyMapped = responseSuccessfullyMapped;
         return this;
      }

      public RequestEventImpl build(RequestEvent.Type type) {
         return new RequestEventImpl(type, this.containerRequest, this.containerResponse, this.throwable, this.extendedUriInfo, this.containerResponseFilters, this.containerRequestFilters, this.exceptionMapper, this.success, this.responseSuccessfullyMapped, this.exceptionCause, this.responseWritten);
      }
   }
}
