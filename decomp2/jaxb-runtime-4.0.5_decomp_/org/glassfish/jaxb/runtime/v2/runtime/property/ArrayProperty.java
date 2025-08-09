package org.glassfish.jaxb.runtime.v2.runtime.property;

import java.lang.reflect.Type;
import org.glassfish.jaxb.runtime.api.AccessorException;
import org.glassfish.jaxb.runtime.v2.model.runtime.RuntimePropertyInfo;
import org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Lister;

abstract class ArrayProperty extends PropertyImpl {
   protected final Accessor acc;
   protected final Lister lister;

   protected ArrayProperty(JAXBContextImpl context, RuntimePropertyInfo prop) {
      super(context, prop);

      assert prop.isCollection();

      this.lister = Lister.create((Type)Utils.REFLECTION_NAVIGATOR.erasure(prop.getRawType()), prop.id(), prop.getAdapter());

      assert this.lister != null;

      this.acc = prop.getAccessor().optimize(context);

      assert this.acc != null;

   }

   public void reset(Object o) throws AccessorException {
      this.lister.reset(o, this.acc);
   }

   public final String getIdValue(Object bean) {
      return null;
   }
}
