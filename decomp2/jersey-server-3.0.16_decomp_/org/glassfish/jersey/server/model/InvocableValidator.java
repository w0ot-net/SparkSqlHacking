package org.glassfish.jersey.server.model;

import jakarta.inject.Singleton;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.inject.PerLookup;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.server.internal.LocalizationMessages;

class InvocableValidator extends AbstractResourceModelVisitor {
   private static final Set SCOPE_ANNOTATIONS = getScopeAnnotations();
   protected final Set checkedClasses = new HashSet();

   private static Set getScopeAnnotations() {
      Set<Class<?>> scopeAnnotations = new HashSet();
      scopeAnnotations.add(Singleton.class);
      scopeAnnotations.add(PerLookup.class);
      return scopeAnnotations;
   }

   public void visitInvocable(Invocable invocable) {
      Class resClass = invocable.getHandler().getHandlerClass();
      if (resClass != null && !this.checkedClasses.contains(resClass)) {
         this.checkedClasses.add(resClass);
         boolean provider = Providers.isProvider(resClass);
         int counter = 0;

         for(Annotation annotation : resClass.getAnnotations()) {
            if (SCOPE_ANNOTATIONS.contains(annotation.annotationType())) {
               ++counter;
            }
         }

         if (counter == 0 && provider) {
            Errors.warning(resClass, LocalizationMessages.RESOURCE_IMPLEMENTS_PROVIDER(resClass, Providers.getProviderContracts(resClass)));
         } else if (counter > 1) {
            Errors.fatal(resClass, LocalizationMessages.RESOURCE_MULTIPLE_SCOPE_ANNOTATIONS(resClass));
         }
      }

   }

   public static boolean isSingleton(Class resourceClass) {
      return resourceClass.isAnnotationPresent(Singleton.class) || Providers.isProvider(resourceClass) && !resourceClass.isAnnotationPresent(PerLookup.class);
   }

   public void visitResourceHandlerConstructor(HandlerConstructor constructor) {
      Class<?> resClass = constructor.getConstructor().getDeclaringClass();
      boolean isSingleton = isSingleton(resClass);
      int paramCount = 0;

      for(Parameter p : constructor.getParameters()) {
         Constructor var10001 = constructor.getConstructor();
         String var10002 = constructor.getConstructor().toGenericString();
         ++paramCount;
         ResourceMethodValidator.validateParameter(p, var10001, var10002, Integer.toString(paramCount), isSingleton);
      }

   }
}
