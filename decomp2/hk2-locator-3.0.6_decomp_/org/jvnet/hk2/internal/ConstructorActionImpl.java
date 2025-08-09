package org.jvnet.hk2.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.Map;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.ProxyFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.glassfish.hk2.api.AOPProxyCtl;
import org.glassfish.hk2.utilities.reflection.Logger;

final class ConstructorActionImpl implements ConstructorAction {
   private static final Class[] ADDED_INTERFACES = new Class[]{AOPProxyCtl.class};
   private static final MethodFilter METHOD_FILTER = new MethodFilter() {
      public boolean isHandled(Method method) {
         return !method.getName().equals("finalize");
      }
   };
   private final ClazzCreator clazzCreator;
   private final Map methodInterceptors;

   ConstructorActionImpl(ClazzCreator clazzCreator, Map methodInterceptors) {
      this.clazzCreator = clazzCreator;
      this.methodInterceptors = methodInterceptors;
   }

   public Object makeMe(final Constructor c, final Object[] args, final boolean neutralCCL) throws Throwable {
      final MethodInterceptorHandler methodInterceptor = new MethodInterceptorHandler(this.clazzCreator.getServiceLocator(), this.clazzCreator.getUnderlyingDescriptor(), this.methodInterceptors);
      final ProxyFactory proxyFactory = new ProxyFactory();
      proxyFactory.setSuperclass(this.clazzCreator.getImplClass());
      proxyFactory.setFilter(METHOD_FILTER);
      proxyFactory.setInterfaces(ADDED_INTERFACES);
      return AccessController.doPrivileged(new PrivilegedExceptionAction() {
         public Object run() throws Exception {
            ClassLoader currentCCL = null;
            if (neutralCCL) {
               currentCCL = Thread.currentThread().getContextClassLoader();
            }

            Object var2;
            try {
               var2 = proxyFactory.create(c.getParameterTypes(), args, methodInterceptor);
            } catch (InvocationTargetException ite) {
               Throwable targetException = ite.getTargetException();
               Logger.getLogger().debug(c.getDeclaringClass().getName(), c.getName(), targetException);
               if (targetException instanceof Exception) {
                  throw (Exception)targetException;
               }

               throw new RuntimeException(targetException);
            } finally {
               if (neutralCCL) {
                  Thread.currentThread().setContextClassLoader(currentCCL);
               }

            }

            return var2;
         }
      });
   }
}
