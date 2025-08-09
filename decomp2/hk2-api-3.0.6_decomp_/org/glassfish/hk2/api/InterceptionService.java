package org.glassfish.hk2.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface InterceptionService {
   Filter getDescriptorFilter();

   List getMethodInterceptors(Method var1);

   List getConstructorInterceptors(Constructor var1);
}
