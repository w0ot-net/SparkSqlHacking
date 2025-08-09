package org.jvnet.hk2.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import javassist.util.proxy.MethodHandler;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.HK2Invocation;
import org.glassfish.hk2.utilities.reflection.Logger;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

public class MethodInterceptorHandler implements MethodHandler {
   private static final boolean DEBUG_INTERCEPTION = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
      public Boolean run() {
         return Boolean.parseBoolean(System.getProperty("org.jvnet.hk2.properties.tracing.interceptors", "false"));
      }
   });
   private final ServiceLocatorImpl locator;
   private final Map interceptorLists;
   private final ActiveDescriptor underlyingDescriptor;

   MethodInterceptorHandler(ServiceLocatorImpl locator, ActiveDescriptor underlyingDescriptor, Map interceptorLists) {
      this.locator = locator;
      this.interceptorLists = interceptorLists;
      this.underlyingDescriptor = underlyingDescriptor;
   }

   public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
      if (thisMethod.getName().equals("__getUnderlyingDescriptor")) {
         return this.underlyingDescriptor;
      } else {
         List<MethodInterceptor> interceptors = (List)this.interceptorLists.get(thisMethod);
         if (interceptors != null && !interceptors.isEmpty()) {
            if (!(interceptors instanceof RandomAccess)) {
               interceptors = new ArrayList(interceptors);
            }

            MethodInterceptor nextInterceptor = (MethodInterceptor)interceptors.get(0);
            long aggregateInterceptionTime = 0L;
            if (DEBUG_INTERCEPTION) {
               aggregateInterceptionTime = System.currentTimeMillis();
               Logger var10000 = Logger.getLogger();
               String var10001 = nextInterceptor.getClass().getName();
               var10000.debug("Invoking interceptor " + var10001 + " index 0 in stack of " + interceptors.size() + " of method " + thisMethod);
            }

            Object var9;
            try {
               var9 = nextInterceptor.invoke(new MethodInvocationImpl(args, thisMethod, self, interceptors, 0, proceed, (HashMap)null));
            } finally {
               if (DEBUG_INTERCEPTION) {
                  aggregateInterceptionTime = System.currentTimeMillis() - aggregateInterceptionTime;
                  Logger var14 = Logger.getLogger();
                  String var15 = nextInterceptor.getClass().getName();
                  var14.debug("Interceptor " + var15 + " index 0 took an aggregate of " + aggregateInterceptionTime + " milliseconds");
               }

            }

            return var9;
         } else {
            return ReflectionHelper.invoke(self, proceed, args, this.locator.getNeutralContextClassLoader());
         }
      }
   }

   private class MethodInvocationImpl implements MethodInvocation, HK2Invocation {
      private final Object[] arguments;
      private final Method method;
      private final Object myself;
      private final List interceptors;
      private final int index;
      private final Method proceed;
      private HashMap userData;

      private MethodInvocationImpl(Object[] arguments, Method method, Object myself, List interceptors, int index, Method proceed, HashMap userData) {
         this.arguments = arguments;
         this.method = method;
         this.myself = myself;
         this.interceptors = interceptors;
         this.index = index;
         this.proceed = proceed;
         this.userData = userData;
      }

      public Object[] getArguments() {
         return this.arguments;
      }

      public AccessibleObject getStaticPart() {
         return this.method;
      }

      public Object getThis() {
         return this.myself;
      }

      public Method getMethod() {
         return this.method;
      }

      public Object proceed() throws Throwable {
         int newIndex = this.index + 1;
         if (newIndex < this.interceptors.size()) {
            MethodInterceptor nextInterceptor = (MethodInterceptor)this.interceptors.get(newIndex);
            long aggregateInterceptionTime = 0L;
            if (MethodInterceptorHandler.DEBUG_INTERCEPTION) {
               aggregateInterceptionTime = System.currentTimeMillis();
               Logger var10000 = Logger.getLogger();
               String var10001 = nextInterceptor.getClass().getName();
               var10000.debug("Invoking interceptor " + var10001 + " index " + newIndex + " in stack of " + this.interceptors.size() + " of method " + this.method);
            }

            Object var5;
            try {
               var5 = nextInterceptor.invoke(MethodInterceptorHandler.this.new MethodInvocationImpl(this.arguments, this.method, this.myself, this.interceptors, newIndex, this.proceed, this.userData));
            } finally {
               if (MethodInterceptorHandler.DEBUG_INTERCEPTION) {
                  aggregateInterceptionTime = System.currentTimeMillis() - aggregateInterceptionTime;
                  Logger.getLogger().debug("Interceptor " + nextInterceptor.getClass().getName() + " index " + newIndex + " took an aggregate of " + aggregateInterceptionTime + " milliseconds");
               }

            }

            return var5;
         } else {
            long methodTime = 0L;
            if (MethodInterceptorHandler.DEBUG_INTERCEPTION) {
               methodTime = System.currentTimeMillis();
            }

            Object var4;
            try {
               var4 = ReflectionHelper.invoke(this.myself, this.proceed, this.arguments, MethodInterceptorHandler.this.locator.getNeutralContextClassLoader());
            } finally {
               if (MethodInterceptorHandler.DEBUG_INTERCEPTION) {
                  methodTime = System.currentTimeMillis() - methodTime;
                  Logger.getLogger().debug("Time to call actual intercepted method " + this.method + " is " + methodTime + " milliseconds");
               }

            }

            return var4;
         }
      }

      public void setUserData(String key, Object data) {
         if (key == null) {
            throw new IllegalArgumentException();
         } else {
            if (this.userData == null) {
               this.userData = new HashMap();
            }

            if (data == null) {
               this.userData.remove(key);
            } else {
               this.userData.put(key, data);
            }

         }
      }

      public Object getUserData(String key) {
         if (key == null) {
            throw new IllegalArgumentException();
         } else {
            return this.userData == null ? null : this.userData.get(key);
         }
      }
   }
}
