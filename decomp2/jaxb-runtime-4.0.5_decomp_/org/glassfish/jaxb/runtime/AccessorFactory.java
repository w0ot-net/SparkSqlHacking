package org.glassfish.jaxb.runtime;

import jakarta.xml.bind.JAXBException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

public interface AccessorFactory {
   Accessor createFieldAccessor(Class var1, Field var2, boolean var3) throws JAXBException;

   Accessor createPropertyAccessor(Class var1, Method var2, Method var3) throws JAXBException;
}
