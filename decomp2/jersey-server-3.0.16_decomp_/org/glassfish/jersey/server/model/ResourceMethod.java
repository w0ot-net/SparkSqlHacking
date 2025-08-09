package org.glassfish.jersey.server.model;

import jakarta.ws.rs.NameBinding;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.model.NameBound;
import org.glassfish.jersey.process.Inflector;
import org.glassfish.jersey.uri.PathPattern;
import org.glassfish.jersey.uri.PathPattern.RightHandPath;

public final class ResourceMethod implements ResourceModelComponent, Producing, Consuming, Suspendable, NameBound {
   private final Data data;
   private final Resource parent;

   static List transform(Resource parent, List list) {
      return (List)list.stream().map((data1) -> data1 == null ? null : new ResourceMethod(parent, data1)).collect(Collectors.toList());
   }

   ResourceMethod(Resource parent, Data data) {
      this.parent = parent;
      this.data = data;
   }

   Data getData() {
      return this.data;
   }

   public Resource getParent() {
      return this.parent;
   }

   public JaxrsType getType() {
      return this.data.getType();
   }

   public String getHttpMethod() {
      return this.data.getHttpMethod();
   }

   public Invocable getInvocable() {
      return this.data.getInvocable();
   }

   public boolean isExtended() {
      return this.data.extended;
   }

   public List getConsumedTypes() {
      return this.data.getConsumedTypes();
   }

   public List getProducedTypes() {
      return this.data.getProducedTypes();
   }

   public long getSuspendTimeout() {
      return this.data.getSuspendTimeout();
   }

   public TimeUnit getSuspendTimeoutUnit() {
      return this.data.getSuspendTimeoutUnit();
   }

   public boolean isSuspendDeclared() {
      return this.data.isSuspended();
   }

   public boolean isSse() {
      return this.data.isSse();
   }

   public boolean isManagedAsyncDeclared() {
      return this.data.isManagedAsync();
   }

   public List getComponents() {
      return Arrays.asList(this.data.getInvocable());
   }

   public void accept(ResourceModelVisitor visitor) {
      visitor.visitResourceMethod(this);
   }

   public boolean isNameBound() {
      return !this.data.getNameBindings().isEmpty();
   }

   public Collection getNameBindings() {
      return this.data.getNameBindings();
   }

   public String toString() {
      return "ResourceMethod{" + this.data.toString() + '}';
   }

   public static enum JaxrsType {
      RESOURCE_METHOD {
         PathPattern createPatternFor(String pathTemplate) {
            return PathPattern.END_OF_PATH_PATTERN;
         }
      },
      SUB_RESOURCE_METHOD {
         PathPattern createPatternFor(String pathTemplate) {
            return new PathPattern(pathTemplate, RightHandPath.capturingZeroSegments);
         }
      },
      SUB_RESOURCE_LOCATOR {
         PathPattern createPatternFor(String pathTemplate) {
            return new PathPattern(pathTemplate, RightHandPath.capturingZeroOrMoreSegments);
         }
      };

      private JaxrsType() {
      }

      abstract PathPattern createPatternFor(String var1);

      private static JaxrsType classify(String httpMethod) {
         return httpMethod != null && !httpMethod.isEmpty() ? RESOURCE_METHOD : SUB_RESOURCE_LOCATOR;
      }
   }

   public static final class Builder {
      private final Resource.Builder parent;
      private String httpMethod;
      private final Set consumedTypes;
      private final Set producedTypes;
      private boolean managedAsync;
      private boolean sse;
      private boolean suspended;
      private long suspendTimeout;
      private TimeUnit suspendTimeoutUnit;
      private Class handlerClass;
      private Object handlerInstance;
      private final Collection handlerParameters;
      private Method definitionMethod;
      private Method handlingMethod;
      private boolean encodedParams;
      private Type routingResponseType;
      private final Collection nameBindings;
      private boolean extended;

      Builder(Resource.Builder parent) {
         this.parent = parent;
         this.httpMethod = null;
         this.consumedTypes = new LinkedHashSet();
         this.producedTypes = new LinkedHashSet();
         this.suspended = false;
         this.suspendTimeout = 0L;
         this.suspendTimeoutUnit = TimeUnit.MILLISECONDS;
         this.handlerParameters = new LinkedList();
         this.encodedParams = false;
         this.nameBindings = new LinkedHashSet();
      }

      Builder(Resource.Builder parent, ResourceMethod originalMethod) {
         this.parent = parent;
         this.consumedTypes = new LinkedHashSet(originalMethod.getConsumedTypes());
         this.producedTypes = new LinkedHashSet(originalMethod.getProducedTypes());
         this.suspended = originalMethod.isSuspendDeclared();
         this.suspendTimeout = originalMethod.getSuspendTimeout();
         this.suspendTimeoutUnit = originalMethod.getSuspendTimeoutUnit();
         this.handlerParameters = new LinkedHashSet(originalMethod.getInvocable().getHandler().getParameters());
         this.nameBindings = originalMethod.getNameBindings();
         this.httpMethod = originalMethod.getHttpMethod();
         this.managedAsync = originalMethod.isManagedAsyncDeclared();
         Invocable invocable = originalMethod.getInvocable();
         this.handlingMethod = invocable.getHandlingMethod();
         this.encodedParams = false;
         this.routingResponseType = invocable.getRoutingResponseType();
         this.extended = originalMethod.isExtended();
         Method handlerMethod = invocable.getDefinitionMethod();
         MethodHandler handler = invocable.getHandler();
         if (handler.isClassBased()) {
            this.handledBy(handler.getHandlerClass(), handlerMethod);
         } else {
            this.handledBy(handler.getHandlerInstance(), handlerMethod);
         }

      }

      public Builder httpMethod(String name) {
         this.httpMethod = name;
         return this;
      }

      public Builder produces(String... types) {
         return this.produces((Collection)MediaTypes.createFrom(types));
      }

      public Builder produces(MediaType... types) {
         return this.produces((Collection)Arrays.asList(types));
      }

      public Builder produces(Collection types) {
         this.producedTypes.addAll(types);
         return this;
      }

      public Builder consumes(String... types) {
         return this.consumes((Collection)MediaTypes.createFrom(types));
      }

      public Builder consumes(MediaType... types) {
         return this.consumes((Collection)Arrays.asList(types));
      }

      public Builder consumes(Collection types) {
         this.consumedTypes.addAll(types);
         return this;
      }

      public Builder nameBindings(Collection nameBindings) {
         for(Class nameBinding : nameBindings) {
            if (nameBinding.getAnnotation(NameBinding.class) != null) {
               this.nameBindings.add(nameBinding);
            }
         }

         return this;
      }

      @SafeVarargs
      public final Builder nameBindings(Class... nameBindings) {
         return this.nameBindings((Collection)Arrays.asList(nameBindings));
      }

      public Builder nameBindings(Annotation... nameBindings) {
         return this.nameBindings((Collection)Arrays.stream(nameBindings).map(Annotation::annotationType).collect(Collectors.toList()));
      }

      public Builder suspended(long timeout, TimeUnit unit) {
         this.suspended = true;
         this.suspendTimeout = timeout;
         this.suspendTimeoutUnit = unit;
         return this;
      }

      public Builder sse() {
         this.sse = true;
         return this;
      }

      public Builder managedAsync() {
         this.managedAsync = true;
         return this;
      }

      public Builder encodedParameters(boolean value) {
         this.encodedParams = value;
         return this;
      }

      public Builder handledBy(Class handlerClass, Method method) {
         this.handlerInstance = null;
         this.handlerClass = handlerClass;
         this.definitionMethod = method;
         return this;
      }

      public Builder handledBy(Object handlerInstance, Method method) {
         this.handlerClass = null;
         this.handlerInstance = handlerInstance;
         this.definitionMethod = method;
         return this;
      }

      public Builder handledBy(Inflector inflector) {
         return this.handledBy((Object)inflector, Invocable.APPLY_INFLECTOR_METHOD);
      }

      public Builder handledBy(Class inflectorClass) {
         return this.handledBy(inflectorClass, Invocable.APPLY_INFLECTOR_METHOD);
      }

      public Builder handlerParameters(Collection parameters) {
         this.handlerParameters.addAll(parameters);
         return this;
      }

      public Builder handlingMethod(Method handlingMethod) {
         this.handlingMethod = handlingMethod;
         return this;
      }

      public Builder routingResponseType(Type routingResponseType) {
         this.routingResponseType = routingResponseType;
         return this;
      }

      public Builder extended(boolean extended) {
         this.extended = extended;
         return this;
      }

      public ResourceMethod build() {
         Data methodData = new Data(this.httpMethod, this.consumedTypes, this.producedTypes, this.managedAsync, this.suspended, this.sse, this.suspendTimeout, this.suspendTimeoutUnit, this.createInvocable(), this.nameBindings, this.parent.isExtended() || this.extended);
         this.parent.onBuildMethod(this, methodData);
         return new ResourceMethod((Resource)null, methodData);
      }

      private Invocable createInvocable() {
         assert this.handlerClass != null || this.handlerInstance != null;

         MethodHandler handler;
         if (this.handlerClass != null) {
            handler = MethodHandler.create(this.handlerClass, this.encodedParams, this.handlerParameters);
         } else {
            handler = MethodHandler.create(this.handlerInstance, this.handlerParameters);
         }

         return Invocable.create(handler, this.definitionMethod, this.handlingMethod, this.encodedParams, this.routingResponseType);
      }
   }

   static class Data {
      private final JaxrsType type;
      private final String httpMethod;
      private final List consumedTypes;
      private final List producedTypes;
      private final boolean managedAsync;
      private final boolean suspended;
      private final boolean sse;
      private final long suspendTimeout;
      private final TimeUnit suspendTimeoutUnit;
      private final Invocable invocable;
      private final Collection nameBindings;
      private final boolean extended;

      private Data(String httpMethod, Collection consumedTypes, Collection producedTypes, boolean managedAsync, boolean suspended, boolean sse, long suspendTimeout, TimeUnit suspendTimeoutUnit, Invocable invocable, Collection nameBindings, boolean extended) {
         this.managedAsync = managedAsync;
         this.type = ResourceMethod.JaxrsType.classify(httpMethod);
         this.httpMethod = httpMethod == null ? httpMethod : httpMethod.toUpperCase(Locale.ROOT);
         this.consumedTypes = Collections.unmodifiableList(new ArrayList(consumedTypes));
         this.producedTypes = Collections.unmodifiableList(new ArrayList(producedTypes));
         this.invocable = invocable;
         this.suspended = suspended;
         this.sse = sse;
         this.suspendTimeout = suspendTimeout;
         this.suspendTimeoutUnit = suspendTimeoutUnit;
         this.nameBindings = Collections.unmodifiableCollection(new ArrayList(nameBindings));
         this.extended = extended;
      }

      JaxrsType getType() {
         return this.type;
      }

      String getHttpMethod() {
         return this.httpMethod;
      }

      List getConsumedTypes() {
         return this.consumedTypes;
      }

      List getProducedTypes() {
         return this.producedTypes;
      }

      boolean isManagedAsync() {
         return this.managedAsync;
      }

      boolean isSuspended() {
         return this.suspended;
      }

      boolean isSse() {
         return this.sse;
      }

      long getSuspendTimeout() {
         return this.suspendTimeout;
      }

      TimeUnit getSuspendTimeoutUnit() {
         return this.suspendTimeoutUnit;
      }

      Invocable getInvocable() {
         return this.invocable;
      }

      boolean isExtended() {
         return this.extended;
      }

      Collection getNameBindings() {
         return this.nameBindings;
      }

      public String toString() {
         return "httpMethod=" + this.httpMethod + ", consumedTypes=" + this.consumedTypes + ", producedTypes=" + this.producedTypes + ", suspended=" + this.suspended + ", suspendTimeout=" + this.suspendTimeout + ", suspendTimeoutUnit=" + this.suspendTimeoutUnit + ", invocable=" + this.invocable + ", nameBindings=" + this.nameBindings;
      }
   }
}
