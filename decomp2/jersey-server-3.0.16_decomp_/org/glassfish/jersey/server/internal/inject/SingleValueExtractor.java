package org.glassfish.jersey.server.internal.inject;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.ParamConverter;
import org.glassfish.jersey.internal.inject.ExtractorException;

final class SingleValueExtractor extends AbstractParamValueExtractor implements MultivaluedParameterExtractor {
   public SingleValueExtractor(ParamConverter converter, String parameterName, String defaultStringValue) {
      super(converter, parameterName, defaultStringValue);
   }

   public Object extract(MultivaluedMap parameters) {
      String value = (String)parameters.getFirst(this.getName());

      try {
         return this.fromString(value == null && this.isDefaultValueRegistered() ? this.getDefaultValueString() : value);
      } catch (ProcessingException | WebApplicationException ex) {
         throw ex;
      } catch (IllegalArgumentException ex) {
         if (value == null) {
            return this.defaultValue();
         } else {
            throw new ExtractorException(ex);
         }
      } catch (Exception ex) {
         throw new ExtractorException(ex);
      }
   }
}
