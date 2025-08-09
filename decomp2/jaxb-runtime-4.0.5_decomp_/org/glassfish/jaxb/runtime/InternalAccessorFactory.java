package org.glassfish.jaxb.runtime;

import jakarta.xml.bind.JAXBException;
import java.lang.reflect.Field;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public interface InternalAccessorFactory extends AccessorFactory {
   Accessor createFieldAccessor(Class var1, Field var2, boolean var3, boolean var4) throws JAXBException;
}
