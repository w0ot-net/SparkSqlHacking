package org.glassfish.jersey.server.model;

import jakarta.ws.rs.Encoded;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Injections;

public abstract class MethodHandler implements ResourceModelComponent {
   private final Collection handlerParameters;

   protected MethodHandler() {
      this.handlerParameters = Collections.emptyList();
   }

   protected MethodHandler(Collection parameters) {
      if (parameters != null) {
         this.handlerParameters = Collections.unmodifiableCollection(new ArrayList(parameters));
      } else {
         this.handlerParameters = Collections.emptyList();
      }

   }

   public static MethodHandler create(Class handlerClass) {
      return new ClassBasedMethodHandler(handlerClass, (Collection)null);
   }

   public static MethodHandler create(Class handlerClass, boolean keepConstructorParamsEncoded) {
      return new ClassBasedMethodHandler(handlerClass, keepConstructorParamsEncoded, (Collection)null);
   }

   public static MethodHandler create(Object handlerInstance) {
      return new InstanceBasedMethodHandler(handlerInstance, (Collection)null);
   }

   public static MethodHandler create(Object handlerInstance, Class handlerClass) {
      return new InstanceBasedMethodHandler(handlerInstance, handlerClass, (Collection)null);
   }

   public static MethodHandler create(Class handlerClass, Collection handlerParameters) {
      return new ClassBasedMethodHandler(handlerClass, handlerParameters);
   }

   public static MethodHandler create(Class handlerClass, boolean keepConstructorParamsEncoded, Collection handlerParameters) {
      return new ClassBasedMethodHandler(handlerClass, keepConstructorParamsEncoded, handlerParameters);
   }

   public static MethodHandler create(Object handlerInstance, Collection handlerParameters) {
      return new InstanceBasedMethodHandler(handlerInstance, handlerParameters);
   }

   public static MethodHandler create(Object handlerInstance, Class handlerClass, Collection handlerParameters) {
      return new InstanceBasedMethodHandler(handlerInstance, handlerClass, handlerParameters);
   }

   public abstract Class getHandlerClass();

   public List getConstructors() {
      return Collections.emptyList();
   }

   public abstract Object getInstance(InjectionManager var1);

   public abstract boolean isClassBased();

   public Collection getParameters() {
      return this.handlerParameters;
   }

   public List getComponents() {
      return null;
   }

   public void accept(ResourceModelVisitor visitor) {
      visitor.visitMethodHandler(this);
   }

   protected abstract Object getHandlerInstance();

   private static class ClassBasedMethodHandler extends MethodHandler {
      private final Class handlerClass;
      private final List handlerConstructors;

      public ClassBasedMethodHandler(Class handlerClass, Collection handlerParameters) {
         this(handlerClass, handlerClass.isAnnotationPresent(Encoded.class), handlerParameters);
      }

      public ClassBasedMethodHandler(Class handlerClass, boolean disableParamDecoding, Collection handlerParameters) {
         super(handlerParameters);
         this.handlerClass = handlerClass;
         List<HandlerConstructor> constructors = new LinkedList();

         for(Constructor constructor : handlerClass.getConstructors()) {
            constructors.add(new HandlerConstructor(constructor, Parameter.create(handlerClass, handlerClass, constructor, disableParamDecoding)));
         }

         this.handlerConstructors = Collections.unmodifiableList(constructors);
      }

      public Class getHandlerClass() {
         return this.handlerClass;
      }

      public List getConstructors() {
         return this.handlerConstructors;
      }

      public Object getInstance(InjectionManager injectionManager) {
         return Injections.getOrCreate(injectionManager, this.handlerClass);
      }

      public boolean isClassBased() {
         return true;
      }

      protected Object getHandlerInstance() {
         return null;
      }

      public List getComponents() {
         return this.handlerConstructors;
      }

      public String toString() {
         return "ClassBasedMethodHandler{handlerClass=" + this.handlerClass + ", handlerConstructors=" + this.handlerConstructors + '}';
      }
   }

   private static class InstanceBasedMethodHandler extends MethodHandler {
      private final Object handler;
      private final Class handlerClass;

      public InstanceBasedMethodHandler(Object handler, Collection handlerParameters) {
         super(handlerParameters);
         this.handler = handler;
         this.handlerClass = handler.getClass();
      }

      public InstanceBasedMethodHandler(Object handler, Class handlerClass, Collection handlerParameters) {
         super(handlerParameters);
         this.handler = handler;
         this.handlerClass = handlerClass;
      }

      public Class getHandlerClass() {
         return this.handlerClass;
      }

      protected Object getHandlerInstance() {
         return this.handler;
      }

      public Object getInstance(InjectionManager injectionManager) {
         return this.handler;
      }

      public boolean isClassBased() {
         return false;
      }

      public String toString() {
         return "InstanceBasedMethodHandler{handler=" + this.handler + ", handlerClass=" + this.handlerClass + '}';
      }
   }
}
