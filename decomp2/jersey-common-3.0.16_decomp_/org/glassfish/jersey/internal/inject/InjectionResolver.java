package org.glassfish.jersey.internal.inject;

public interface InjectionResolver {
   Object resolve(Injectee var1);

   boolean isConstructorParameterIndicator();

   boolean isMethodParameterIndicator();

   Class getAnnotation();
}
