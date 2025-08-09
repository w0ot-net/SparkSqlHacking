package org.glassfish.jersey.server.model;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.glassfish.jersey.Severity;
import org.glassfish.jersey.internal.Errors;
import org.glassfish.jersey.internal.guava.Preconditions;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.model.internal.ModelHelper;
import org.glassfish.jersey.uri.PathPattern;
import org.glassfish.jersey.uri.PathPattern.RightHandPath;

public final class Resource implements Routed, ResourceModelComponent {
   private final Resource parent;
   private final Data data;
   private final Value name;
   private final List resourceMethods;
   private final ResourceMethod locator;
   private final List childResources;

   public static Builder builder() {
      return new Builder();
   }

   public static Builder builder(String path) {
      return new Builder(path);
   }

   public static Builder builder(List resources) {
      if (resources != null && !resources.isEmpty()) {
         Iterator<Resource> it = resources.iterator();
         Data resourceData = ((Resource)it.next()).data;
         Builder builder = builder(resourceData);
         String path = resourceData.path;

         while(it.hasNext()) {
            resourceData = ((Resource)it.next()).data;
            if ((resourceData.path != null || path != null) && (path == null || !path.equals(resourceData.path))) {
               throw new IllegalArgumentException(LocalizationMessages.ERROR_RESOURCES_CANNOT_MERGE());
            }

            builder.mergeWith(resourceData);
         }

         return builder;
      } else {
         return builder();
      }
   }

   public static Builder builder(Class resourceClass) {
      return builder(resourceClass, false);
   }

   public static Builder builder(Class resourceClass, boolean disableValidation) {
      Builder builder = (new IntrospectionModeller(resourceClass, disableValidation)).createResourceBuilder();
      return builder.isEmpty() ? null : builder;
   }

   public static Resource from(Class resourceClass) {
      return from(resourceClass, false);
   }

   public static Resource from(Class resourceClass, boolean disableValidation) {
      Builder builder = (new IntrospectionModeller(resourceClass, disableValidation)).createResourceBuilder();
      return builder.isEmpty() ? null : builder.build();
   }

   public static boolean isAcceptable(Class c) {
      return (c.getModifiers() & 1024) == 0 && !c.isPrimitive() && !c.isAnnotation() && !c.isInterface() && !c.isLocalClass() && (!c.isMemberClass() || (c.getModifiers() & 8) != 0);
   }

   public static Path getPath(Class resourceClass) {
      return (Path)ModelHelper.getAnnotatedResourceClass(resourceClass).getAnnotation(Path.class);
   }

   public static Builder builder(Resource resource) {
      return builder(resource.data);
   }

   private static Builder builder(Data resourceData) {
      Builder b;
      if (resourceData.path == null) {
         b = new Builder();
      } else {
         b = new Builder(resourceData.path);
      }

      b.resourceMethods.addAll(resourceData.resourceMethods);
      b.childResources.addAll(resourceData.childResources);
      b.subResourceLocator = resourceData.subResourceLocator;
      b.handlerClasses.addAll(resourceData.handlerClasses);
      b.handlerInstances.addAll(resourceData.handlerInstances);
      b.names.addAll(resourceData.names);
      return b;
   }

   private static List transform(Resource parent, List list) {
      return (List)list.stream().map((data) -> new Resource(parent, data)).collect(Collectors.toList());
   }

   private static List immutableCopy(List list) {
      return list.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(list);
   }

   private static Set immutableCopy(Set set) {
      if (set.isEmpty()) {
         return Collections.emptySet();
      } else {
         Set<T> result = Collections.newSetFromMap(new IdentityHashMap());
         result.addAll(set);
         return set;
      }
   }

   private Resource(Resource parent, final Data data) {
      this.parent = parent;
      this.data = data;
      this.name = Values.lazy(new Value() {
         public String get() {
            return data.names.size() == 1 ? (String)data.names.get(0) : "Merge of " + data.names.toString();
         }
      });
      this.resourceMethods = immutableCopy(ResourceMethod.transform(this, data.resourceMethods));
      this.locator = data.subResourceLocator == null ? null : new ResourceMethod(this, data.subResourceLocator);
      this.childResources = immutableCopy(transform(this, data.childResources));
   }

   public String getPath() {
      return this.data.path;
   }

   public PathPattern getPathPattern() {
      return this.data.pathPattern;
   }

   public Resource getParent() {
      return this.parent;
   }

   public String getName() {
      return (String)this.name.get();
   }

   public List getNames() {
      return this.data.names;
   }

   public List getResourceMethods() {
      return this.resourceMethods;
   }

   public ResourceMethod getResourceLocator() {
      return this.locator;
   }

   public List getAllMethods() {
      LinkedList<ResourceMethod> methodsAndLocators = new LinkedList(this.getResourceMethods());
      ResourceMethod loc = this.getResourceLocator();
      if (loc != null) {
         methodsAndLocators.add(loc);
      }

      return methodsAndLocators;
   }

   public List getChildResources() {
      return this.childResources;
   }

   public Set getHandlerClasses() {
      return this.data.handlerClasses;
   }

   public Set getHandlerInstances() {
      return this.data.handlerInstances;
   }

   public void accept(ResourceModelVisitor visitor) {
      if (this.getParent() == null) {
         visitor.visitResource(this);
      } else {
         visitor.visitChildResource(this);
      }

   }

   public boolean isExtended() {
      return this.data.extended;
   }

   public String toString() {
      return this.data.toString();
   }

   public List getComponents() {
      List<ResourceModelComponent> components = new LinkedList();
      components.addAll(this.getChildResources());
      components.addAll(this.getResourceMethods());
      ResourceMethod resourceLocator = this.getResourceLocator();
      if (resourceLocator != null) {
         components.add(resourceLocator);
      }

      return components;
   }

   private static class Data {
      private final List names;
      private final String path;
      private final PathPattern pathPattern;
      private final List resourceMethods;
      private final ResourceMethod.Data subResourceLocator;
      private final List childResources;
      private final Set handlerClasses;
      private final Set handlerInstances;
      private final boolean extended;

      private Data(List names, String path, List resourceMethods, ResourceMethod.Data subResourceLocator, List childResources, Set handlerClasses, Set handlerInstances, boolean extended) {
         this.extended = extended;
         this.names = Resource.immutableCopy(names);
         this.path = path;
         this.pathPattern = path != null && !path.isEmpty() ? new PathPattern(path, RightHandPath.capturingZeroOrMoreSegments) : PathPattern.OPEN_ROOT_PATH_PATTERN;
         this.resourceMethods = Resource.immutableCopy(resourceMethods);
         this.subResourceLocator = subResourceLocator;
         this.childResources = Collections.unmodifiableList(childResources);
         this.handlerClasses = Resource.immutableCopy(handlerClasses);
         this.handlerInstances = Resource.immutableCopy(handlerInstances);
      }

      public String toString() {
         return "Resource{" + (this.path == null ? "[unbound], " : "\"" + this.path + "\", ") + this.childResources.size() + " child resources, " + this.resourceMethods.size() + " resource methods, " + (this.subResourceLocator == null ? "0" : "1") + " sub-resource locator, " + this.handlerClasses.size() + " method handler classes, " + this.handlerInstances.size() + " method handler instances" + '}';
      }
   }

   public static final class Builder {
      private List names;
      private String path;
      private final Set methodBuilders;
      private final Set childResourceBuilders;
      private final List childResources;
      private final List resourceMethods;
      private ResourceMethod.Data subResourceLocator;
      private final Set handlerClasses;
      private final Set handlerInstances;
      private final Builder parentResource;
      private boolean extended;

      private Builder(Builder parentResource) {
         this.methodBuilders = new LinkedHashSet();
         this.childResourceBuilders = new LinkedHashSet();
         this.childResources = new LinkedList();
         this.resourceMethods = new LinkedList();
         this.handlerClasses = Collections.newSetFromMap(new IdentityHashMap());
         this.handlerInstances = Collections.newSetFromMap(new IdentityHashMap());
         this.parentResource = parentResource;
         this.name("[unnamed]");
      }

      private Builder(String path) {
         this((Builder)null);
         this.path(path);
      }

      private Builder(String path, Builder parentResource) {
         this(parentResource);
         this.path = path;
      }

      private Builder() {
         this((Builder)null);
      }

      private boolean isEmpty() {
         return this.path == null && this.methodBuilders.isEmpty() && this.childResourceBuilders.isEmpty() && this.resourceMethods.isEmpty() && this.childResources.isEmpty() && this.subResourceLocator == null;
      }

      public Builder name(String name) {
         this.names = new ArrayList();
         this.names.add(name);
         return this;
      }

      public Builder path(String path) {
         this.path = path;
         return this;
      }

      public ResourceMethod.Builder addMethod(String httpMethod) {
         ResourceMethod.Builder builder = new ResourceMethod.Builder(this);
         this.methodBuilders.add(builder);
         return builder.httpMethod(httpMethod);
      }

      public ResourceMethod.Builder addMethod() {
         ResourceMethod.Builder builder = new ResourceMethod.Builder(this);
         this.methodBuilders.add(builder);
         return builder;
      }

      public ResourceMethod.Builder addMethod(ResourceMethod resourceMethod) {
         ResourceMethod.Builder builder = new ResourceMethod.Builder(this, resourceMethod);
         this.methodBuilders.add(builder);
         return builder;
      }

      public ResourceMethod.Builder updateMethod(ResourceMethod resourceMethod) {
         boolean removed = this.resourceMethods.remove(resourceMethod.getData());
         if (!removed) {
            throw new IllegalArgumentException(LocalizationMessages.RESOURCE_UPDATED_METHOD_DOES_NOT_EXIST(resourceMethod.toString()));
         } else {
            ResourceMethod.Builder builder = new ResourceMethod.Builder(this, resourceMethod);
            this.methodBuilders.add(builder);
            return builder;
         }
      }

      public Builder addChildResource(String relativePath) {
         if (this.parentResource != null) {
            throw new IllegalStateException(LocalizationMessages.RESOURCE_ADD_CHILD_ALREADY_CHILD());
         } else {
            Builder resourceBuilder = new Builder(relativePath, this);
            this.childResourceBuilders.add(resourceBuilder);
            return resourceBuilder;
         }
      }

      public void addChildResource(Resource resource) {
         this.childResources.add(resource.data);
      }

      public void replaceChildResource(Resource replacedResource, Resource newResource) {
         boolean removed = this.childResources.remove(replacedResource.data);
         if (!removed) {
            throw new IllegalArgumentException(LocalizationMessages.RESOURCE_REPLACED_CHILD_DOES_NOT_EXIST(replacedResource.toString()));
         } else {
            this.addChildResource(newResource);
         }
      }

      public Builder mergeWith(Resource resource) {
         this.mergeWith(resource.data);
         return this;
      }

      public Builder extended(boolean extended) {
         this.extended = extended;
         return this;
      }

      boolean isExtended() {
         return this.extended;
      }

      private Builder mergeWith(final Data resourceData) {
         this.resourceMethods.addAll(resourceData.resourceMethods);
         this.childResources.addAll(resourceData.childResources);
         if (this.subResourceLocator != null && resourceData.subResourceLocator != null) {
            Errors.processWithException(new Runnable() {
               public void run() {
                  Errors.error(this, LocalizationMessages.RESOURCE_MERGE_CONFLICT_LOCATORS(Builder.this, resourceData, Builder.this.path), Severity.FATAL);
               }
            });
         } else if (resourceData.subResourceLocator != null) {
            this.subResourceLocator = resourceData.subResourceLocator;
         }

         this.handlerClasses.addAll(resourceData.handlerClasses);
         this.handlerInstances.addAll(resourceData.handlerInstances);
         this.names.addAll(resourceData.names);
         return this;
      }

      public Builder mergeWith(final Builder resourceBuilder) {
         resourceBuilder.processMethodBuilders();
         this.resourceMethods.addAll(resourceBuilder.resourceMethods);
         this.childResources.addAll(resourceBuilder.childResources);
         if (this.subResourceLocator != null && resourceBuilder.subResourceLocator != null) {
            Errors.processWithException(new Runnable() {
               public void run() {
                  Errors.warning(this, LocalizationMessages.RESOURCE_MERGE_CONFLICT_LOCATORS(Builder.this, resourceBuilder, Builder.this.path));
               }
            });
         } else if (resourceBuilder.subResourceLocator != null) {
            this.subResourceLocator = resourceBuilder.subResourceLocator;
         }

         this.handlerClasses.addAll(resourceBuilder.handlerClasses);
         this.handlerInstances.addAll(resourceBuilder.handlerInstances);
         this.names.addAll(resourceBuilder.names);
         return this;
      }

      void onBuildMethod(ResourceMethod.Builder builder, ResourceMethod.Data methodData) {
         Preconditions.checkState(this.methodBuilders.remove(builder), "Resource.Builder.onBuildMethod() invoked from a resource method builder that is not registered in the resource builder instance.");
         switch (methodData.getType()) {
            case RESOURCE_METHOD:
               this.resourceMethods.add(methodData);
               break;
            case SUB_RESOURCE_LOCATOR:
               if (this.subResourceLocator != null) {
                  Errors.processWithException(new Runnable() {
                     public void run() {
                        Errors.error(this, LocalizationMessages.AMBIGUOUS_SRLS(this, Builder.this.path), Severity.FATAL);
                     }
                  });
               }

               this.subResourceLocator = methodData;
         }

         MethodHandler methodHandler = methodData.getInvocable().getHandler();
         if (methodHandler.isClassBased()) {
            this.handlerClasses.add(methodHandler.getHandlerClass());
         } else {
            this.handlerInstances.add(methodHandler.getHandlerInstance());
         }

      }

      private void onBuildChildResource(Builder childResourceBuilder, Data childResourceData) {
         Preconditions.checkState(this.childResourceBuilders.remove(childResourceBuilder), "Resource.Builder.onBuildChildResource() invoked from a resource builder that is not registered in the resource builder instance as a child resource builder.");
         this.childResources.add(childResourceData);
      }

      private static List mergeResources(List resources) {
         MultivaluedMap<String, Data> resourceData = new MultivaluedHashMap();
         List<Data> mergedResources = new ArrayList();

         Data data;
         for(Iterator<Data> resourcesIterator = resources.iterator(); resourcesIterator.hasNext(); resourceData.add(data.path, data)) {
            data = (Data)resourcesIterator.next();
            if (resourceData.containsKey(data.path)) {
               resourcesIterator.remove();
            }
         }

         for(Map.Entry entry : resourceData.entrySet()) {
            if (((List)entry.getValue()).size() == 1) {
               mergedResources.add(((List)entry.getValue()).get(0));
            } else {
               Builder builder = null;

               for(Data data : (List)entry.getValue()) {
                  if (builder != null) {
                     builder.mergeWith(data);
                  } else {
                     builder = Resource.builder(data);
                  }
               }

               mergedResources.add(builder.buildResourceData());
            }
         }

         return mergedResources;
      }

      private Data buildResourceData() {
         if (this.parentResource != null && this.parentResource.isExtended()) {
            this.extended = true;
         }

         this.processMethodBuilders();
         this.processChildResourceBuilders();
         List<Data> mergedChildResources = mergeResources(this.childResources);
         Set<Class<?>> classes = new HashSet(this.handlerClasses);
         Set<Object> instances = new HashSet(this.handlerInstances);

         for(Data childResource : mergedChildResources) {
            classes.addAll(childResource.handlerClasses);
            instances.addAll(childResource.handlerInstances);
         }

         if (this.areAllMembersExtended(mergedChildResources)) {
            this.extended = true;
         }

         Data resourceData = new Data(this.names, this.path, this.resourceMethods, this.subResourceLocator, mergedChildResources, classes, instances, this.extended);
         if (this.parentResource != null) {
            this.parentResource.onBuildChildResource(this, resourceData);
         }

         return resourceData;
      }

      private boolean areAllMembersExtended(List mergedChildResources) {
         boolean allExtended = true;

         for(ResourceMethod.Data resourceMethod : this.resourceMethods) {
            if (!resourceMethod.isExtended()) {
               allExtended = false;
            }
         }

         if (this.subResourceLocator != null && !this.subResourceLocator.isExtended()) {
            allExtended = false;
         }

         for(Data childResource : mergedChildResources) {
            if (!childResource.extended) {
               allExtended = false;
            }
         }

         return allExtended;
      }

      public Resource build() {
         Data resourceData = this.buildResourceData();
         return new Resource((Resource)null, resourceData);
      }

      private void processMethodBuilders() {
         while(!this.methodBuilders.isEmpty()) {
            ((ResourceMethod.Builder)this.methodBuilders.iterator().next()).build();
         }

      }

      private void processChildResourceBuilders() {
         while(!this.childResourceBuilders.isEmpty()) {
            ((Builder)this.childResourceBuilders.iterator().next()).build();
         }

      }

      public String toString() {
         return "Builder{names=" + this.names + ", path='" + this.path + '\'' + ", methodBuilders=" + this.methodBuilders + ", childResourceBuilders=" + this.childResourceBuilders + ", childResources=" + this.childResources + ", resourceMethods=" + this.resourceMethods + ", subResourceLocator=" + this.subResourceLocator + ", handlerClasses=" + this.handlerClasses + ", handlerInstances=" + this.handlerInstances + ", parentResource=" + (this.parentResource == null ? "<no parent>" : this.parentResource.shortToString()) + ", extended=" + this.extended + '}';
      }

      private String shortToString() {
         return "Builder{names=" + this.names + ", path='" + this.path + "'}";
      }
   }
}
