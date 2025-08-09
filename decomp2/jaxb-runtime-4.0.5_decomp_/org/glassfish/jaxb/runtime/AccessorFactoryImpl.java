package org.glassfish.jaxb.runtime;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public class AccessorFactoryImpl implements InternalAccessorFactory {
   private static AccessorFactoryImpl instance = new AccessorFactoryImpl();

   private AccessorFactoryImpl() {
   }

   public static AccessorFactoryImpl getInstance() {
      return instance;
   }

   public Accessor createFieldAccessor(Class bean, Field field, boolean readOnly) {
      return (Accessor)(readOnly ? new Accessor.ReadOnlyFieldReflection(field) : new Accessor.FieldReflection(field));
   }

   public Accessor createFieldAccessor(Class bean, Field field, boolean readOnly, boolean supressWarning) {
      return (Accessor)(readOnly ? new Accessor.ReadOnlyFieldReflection(field, supressWarning) : new Accessor.FieldReflection(field, supressWarning));
   }

   public Accessor createPropertyAccessor(Class bean, Method getter, Method setter) {
      if (getter == null) {
         return new Accessor.SetterOnlyReflection(setter);
      } else {
         return (Accessor)(setter == null ? new Accessor.GetterOnlyReflection(getter) : new Accessor.GetterSetterReflection(getter, setter));
      }
   }
}
