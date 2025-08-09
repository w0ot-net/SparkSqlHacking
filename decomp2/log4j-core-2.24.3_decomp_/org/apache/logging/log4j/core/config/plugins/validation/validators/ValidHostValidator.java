package org.apache.logging.log4j.core.config.plugins.validation.validators;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.validation.ConstraintValidator;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.ValidHost;
import org.apache.logging.log4j.status.StatusLogger;

public class ValidHostValidator implements ConstraintValidator {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private ValidHost annotation;

   public void initialize(final ValidHost annotation) {
      this.annotation = annotation;
   }

   public boolean isValid(final String name, final Object value) {
      if (value == null) {
         LOGGER.error(this.annotation.message());
         return false;
      } else if (value instanceof InetAddress) {
         return true;
      } else {
         try {
            InetAddress.getByName(value.toString());
            return true;
         } catch (UnknownHostException e) {
            LOGGER.error(this.annotation.message(), e);
            return false;
         }
      }
   }
}
