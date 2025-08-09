package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Collection;

public class IgnoredPropertyException extends PropertyBindingException {
   private static final long serialVersionUID = 1L;

   public IgnoredPropertyException(JsonParser p, String msg, JsonLocation loc, Class referringClass, String propName, Collection propertyIds) {
      super(p, msg, loc, referringClass, propName, propertyIds);
   }

   /** @deprecated */
   @Deprecated
   public IgnoredPropertyException(String msg, JsonLocation loc, Class referringClass, String propName, Collection propertyIds) {
      super(msg, loc, referringClass, propName, propertyIds);
   }

   public static IgnoredPropertyException from(JsonParser p, Object fromObjectOrClass, String propertyName, Collection propertyIds) {
      Class<?> ref;
      if (fromObjectOrClass instanceof Class) {
         ref = (Class)fromObjectOrClass;
      } else {
         ref = fromObjectOrClass.getClass();
      }

      String msg = String.format("Ignored field \"%s\" (class %s) encountered; mapper configured not to allow this", propertyName, ref.getName());
      IgnoredPropertyException e = new IgnoredPropertyException(p, msg, p.currentLocation(), ref, propertyName, propertyIds);
      e.prependPath(fromObjectOrClass, propertyName);
      return e;
   }
}
