package org.glassfish.jersey.client.internal.inject;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.ParamConverter;
import org.glassfish.jersey.client.inject.ParameterUpdater;
import org.glassfish.jersey.internal.inject.UpdaterException;

final class SingleValueUpdater extends AbstractParamValueUpdater implements ParameterUpdater {
   public SingleValueUpdater(ParamConverter converter, String parameterName, String defaultValue) {
      super(converter, parameterName, defaultValue);
   }

   public String update(Object value) {
      try {
         return value == null && this.isDefaultValueRegistered() ? this.getDefaultValueString() : this.toString(value);
      } catch (ProcessingException | WebApplicationException ex) {
         throw ex;
      } catch (IllegalArgumentException var4) {
         return this.defaultValue();
      } catch (Exception ex) {
         throw new UpdaterException(ex);
      }
   }
}
