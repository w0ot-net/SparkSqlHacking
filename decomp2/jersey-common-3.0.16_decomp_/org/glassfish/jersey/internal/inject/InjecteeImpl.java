package org.glassfish.jersey.internal.inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.glassfish.jersey.internal.util.Pretty;

public class InjecteeImpl implements Injectee {
   private Type requiredType;
   private Set qualifiers;
   private Class parentClassScope;
   private int position = -1;
   private Class injecteeClass;
   private AnnotatedElement parent;
   private boolean isOptional = false;
   private boolean isFactory = false;
   private boolean isProvider = false;
   private ForeignDescriptor injecteeDescriptor;

   public Type getRequiredType() {
      return this.requiredType;
   }

   public void setRequiredType(Type requiredType) {
      this.requiredType = requiredType;
   }

   public Set getRequiredQualifiers() {
      return this.qualifiers == null ? Collections.emptySet() : this.qualifiers;
   }

   public void setRequiredQualifiers(Set requiredQualifiers) {
      this.qualifiers = Collections.unmodifiableSet(requiredQualifiers);
   }

   public Class getParentClassScope() {
      return this.parentClassScope;
   }

   public void setParentClassScope(Class parentClassScope) {
      this.parentClassScope = parentClassScope;
   }

   public boolean isFactory() {
      return this.isFactory;
   }

   public void setFactory(boolean factory) {
      this.isFactory = factory;
   }

   public boolean isProvider() {
      return this.isProvider;
   }

   public void setProvider(boolean provider) {
      this.isProvider = provider;
   }

   public int getPosition() {
      return this.position;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public Class getInjecteeClass() {
      return this.injecteeClass;
   }

   public void setInjecteeClass(Class injecteeClass) {
      this.injecteeClass = injecteeClass;
   }

   public AnnotatedElement getParent() {
      return this.parent;
   }

   public void setParent(AnnotatedElement parent) {
      this.parent = parent;
      if (parent instanceof Field) {
         this.injecteeClass = ((Field)parent).getDeclaringClass();
      } else if (parent instanceof Constructor) {
         this.injecteeClass = ((Constructor)parent).getDeclaringClass();
      } else if (parent instanceof Method) {
         this.injecteeClass = ((Method)parent).getDeclaringClass();
      }

   }

   public boolean isOptional() {
      return this.isOptional;
   }

   public void setOptional(boolean optional) {
      this.isOptional = optional;
   }

   public ForeignDescriptor getInjecteeDescriptor() {
      return this.injecteeDescriptor;
   }

   public void setInjecteeDescriptor(ForeignDescriptor injecteeDescriptor) {
      this.injecteeDescriptor = injecteeDescriptor;
   }

   public String toString() {
      return "InjecteeImpl(requiredType=" + Pretty.type(this.requiredType) + ",parent=" + Pretty.clazz(this.parent.getClass()) + ",qualifiers=" + Pretty.collection(this.qualifiers) + ",position=" + this.position + ",factory=" + this.isFactory + ",provider=" + this.isProvider + ",optional=" + this.isOptional + "," + System.identityHashCode(this) + ")";
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof InjecteeImpl)) {
         return false;
      } else {
         InjecteeImpl injectee = (InjecteeImpl)o;
         return this.position == injectee.position && this.isOptional == injectee.isOptional && this.isFactory == injectee.isFactory && this.isProvider == injectee.isProvider && Objects.equals(this.requiredType, injectee.requiredType) && Objects.equals(this.qualifiers, injectee.qualifiers) && Objects.equals(this.injecteeClass, injectee.injecteeClass) && Objects.equals(this.parent, injectee.parent);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.requiredType, this.qualifiers, this.position, this.injecteeClass, this.parent, this.isOptional, this.isFactory});
   }
}
