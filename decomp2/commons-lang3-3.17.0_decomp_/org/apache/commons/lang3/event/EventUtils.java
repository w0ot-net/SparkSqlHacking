package org.apache.commons.lang3.event;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.reflect.MethodUtils;

public class EventUtils {
   public static void addEventListener(Object eventSource, Class listenerType, Object listener) {
      try {
         MethodUtils.invokeMethod(eventSource, "add" + listenerType.getSimpleName(), listener);
      } catch (ReflectiveOperationException var4) {
         throw new IllegalArgumentException("Unable to add listener for class " + eventSource.getClass().getName() + " and public add" + listenerType.getSimpleName() + " method which takes a parameter of type " + listenerType.getName() + ".");
      }
   }

   public static void bindEventsToMethod(Object target, String methodName, Object eventSource, Class listenerType, String... eventTypes) {
      L listener = (L)listenerType.cast(Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{listenerType}, new EventBindingInvocationHandler(target, methodName, eventTypes)));
      addEventListener(eventSource, listenerType, listener);
   }

   private static final class EventBindingInvocationHandler implements InvocationHandler {
      private final Object target;
      private final String methodName;
      private final Set eventTypes;

      EventBindingInvocationHandler(Object target, String methodName, String[] eventTypes) {
         this.target = target;
         this.methodName = methodName;
         this.eventTypes = new HashSet(Arrays.asList(eventTypes));
      }

      private boolean hasMatchingParametersMethod(Method method) {
         return MethodUtils.getAccessibleMethod(this.target.getClass(), this.methodName, method.getParameterTypes()) != null;
      }

      public Object invoke(Object proxy, Method method, Object[] parameters) throws Throwable {
         if (!this.eventTypes.isEmpty() && !this.eventTypes.contains(method.getName())) {
            return null;
         } else {
            return this.hasMatchingParametersMethod(method) ? MethodUtils.invokeMethod(this.target, this.methodName, parameters) : MethodUtils.invokeMethod(this.target, this.methodName);
         }
      }
   }
}
