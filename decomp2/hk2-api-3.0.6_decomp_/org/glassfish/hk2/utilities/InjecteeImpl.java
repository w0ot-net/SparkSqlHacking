package org.glassfish.hk2.utilities;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.Unqualified;
import org.glassfish.hk2.utilities.reflection.Pretty;

public class InjecteeImpl implements Injectee {
   private Type requiredType;
   private Set qualifiers;
   private int position = -1;
   private Class pClass;
   private AnnotatedElement parent;
   private boolean isOptional = false;
   private boolean isSelf = false;
   private Unqualified unqualified = null;
   private ActiveDescriptor injecteeDescriptor;

   public InjecteeImpl() {
   }

   public InjecteeImpl(Type requiredType) {
      this.requiredType = requiredType;
   }

   public InjecteeImpl(Injectee copyMe) {
      this.requiredType = copyMe.getRequiredType();
      this.position = copyMe.getPosition();
      this.parent = copyMe.getParent();
      this.qualifiers = Collections.unmodifiableSet(copyMe.getRequiredQualifiers());
      this.isOptional = copyMe.isOptional();
      this.isSelf = copyMe.isSelf();
      this.injecteeDescriptor = copyMe.getInjecteeDescriptor();
      if (this.parent == null) {
         this.pClass = null;
      } else if (this.parent instanceof Field) {
         this.pClass = ((Field)this.parent).getDeclaringClass();
      } else if (this.parent instanceof Constructor) {
         this.pClass = ((Constructor)this.parent).getDeclaringClass();
      } else if (this.parent instanceof Method) {
         this.pClass = ((Method)this.parent).getDeclaringClass();
      }

   }

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

   public int getPosition() {
      return this.position;
   }

   public void setPosition(int position) {
      this.position = position;
   }

   public Class getInjecteeClass() {
      return this.pClass;
   }

   public AnnotatedElement getParent() {
      return this.parent;
   }

   public void setParent(AnnotatedElement parent) {
      this.parent = parent;
      if (parent instanceof Field) {
         this.pClass = ((Field)parent).getDeclaringClass();
      } else if (parent instanceof Constructor) {
         this.pClass = ((Constructor)parent).getDeclaringClass();
      } else if (parent instanceof Method) {
         this.pClass = ((Method)parent).getDeclaringClass();
      }

   }

   public boolean isOptional() {
      return this.isOptional;
   }

   public void setOptional(boolean optional) {
      this.isOptional = optional;
   }

   public boolean isSelf() {
      return this.isSelf;
   }

   public void setSelf(boolean self) {
      this.isSelf = self;
   }

   public Unqualified getUnqualified() {
      return this.unqualified;
   }

   public void setUnqualified(Unqualified unqualified) {
      this.unqualified = unqualified;
   }

   public ActiveDescriptor getInjecteeDescriptor() {
      return this.injecteeDescriptor;
   }

   public void setInjecteeDescriptor(ActiveDescriptor injecteeDescriptor) {
      this.injecteeDescriptor = injecteeDescriptor;
   }

   public String toString() {
      String var10000 = Pretty.type(this.requiredType);
      return "InjecteeImpl(requiredType=" + var10000 + ",parent=" + Pretty.clazz(this.pClass) + ",qualifiers=" + Pretty.collection(this.qualifiers) + ",position=" + this.position + ",optional=" + this.isOptional + ",self=" + this.isSelf + ",unqualified=" + this.unqualified + "," + System.identityHashCode(this) + ")";
   }
}
