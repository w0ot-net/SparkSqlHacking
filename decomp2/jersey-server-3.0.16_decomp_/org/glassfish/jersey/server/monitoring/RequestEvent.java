package org.glassfish.jersey.server.monitoring;

import jakarta.ws.rs.ext.ExceptionMapper;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.ExtendedUriInfo;

public interface RequestEvent {
   Type getType();

   ContainerRequest getContainerRequest();

   ContainerResponse getContainerResponse();

   Throwable getException();

   ExtendedUriInfo getUriInfo();

   ExceptionMapper getExceptionMapper();

   Iterable getContainerRequestFilters();

   Iterable getContainerResponseFilters();

   boolean isSuccess();

   boolean isResponseSuccessfullyMapped();

   ExceptionCause getExceptionCause();

   boolean isResponseWritten();

   public static enum Type {
      START,
      MATCHING_START,
      LOCATOR_MATCHED,
      SUBRESOURCE_LOCATED,
      REQUEST_MATCHED,
      REQUEST_FILTERED,
      RESOURCE_METHOD_START,
      RESOURCE_METHOD_FINISHED,
      RESP_FILTERS_START,
      RESP_FILTERS_FINISHED,
      ON_EXCEPTION,
      EXCEPTION_MAPPER_FOUND,
      EXCEPTION_MAPPING_FINISHED,
      FINISHED;
   }

   public static enum ExceptionCause {
      ORIGINAL,
      MAPPED_RESPONSE;
   }
}
