package org.glassfish.jersey.internal.inject;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.Set;

public interface Injectee {
   Type getRequiredType();

   Set getRequiredQualifiers();

   int getPosition();

   Class getInjecteeClass();

   AnnotatedElement getParent();

   boolean isOptional();

   ForeignDescriptor getInjecteeDescriptor();

   Class getParentClassScope();

   boolean isFactory();

   boolean isProvider();
}
