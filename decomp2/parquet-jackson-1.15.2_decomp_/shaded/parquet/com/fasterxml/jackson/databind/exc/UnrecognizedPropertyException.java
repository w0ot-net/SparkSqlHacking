package shaded.parquet.com.fasterxml.jackson.databind.exc;

import java.util.Collection;
import shaded.parquet.com.fasterxml.jackson.core.JsonLocation;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;

public class UnrecognizedPropertyException extends PropertyBindingException {
   private static final long serialVersionUID = 1L;

   public UnrecognizedPropertyException(JsonParser p, String msg, JsonLocation loc, Class referringClass, String propName, Collection propertyIds) {
      super(p, msg, loc, referringClass, propName, propertyIds);
   }

   /** @deprecated */
   @Deprecated
   public UnrecognizedPropertyException(String msg, JsonLocation loc, Class referringClass, String propName, Collection propertyIds) {
      super(msg, loc, referringClass, propName, propertyIds);
   }

   public static UnrecognizedPropertyException from(JsonParser p, Object fromObjectOrClass, String propertyName, Collection propertyIds) {
      Class<?> ref;
      if (fromObjectOrClass instanceof Class) {
         ref = (Class)fromObjectOrClass;
      } else {
         ref = fromObjectOrClass.getClass();
      }

      String msg = String.format("Unrecognized field \"%s\" (class %s), not marked as ignorable", propertyName, ref.getName());
      UnrecognizedPropertyException e = new UnrecognizedPropertyException(p, msg, p.currentLocation(), ref, propertyName, propertyIds);
      e.prependPath(fromObjectOrClass, propertyName);
      return e;
   }
}
