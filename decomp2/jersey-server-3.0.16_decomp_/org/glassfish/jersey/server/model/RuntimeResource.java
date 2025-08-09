package org.glassfish.jersey.server.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.glassfish.jersey.uri.PathPattern;

public class RuntimeResource implements ResourceModelComponent {
   public static final Comparator COMPARATOR = new Comparator() {
      public int compare(RuntimeResource o1, RuntimeResource o2) {
         int cmp = PathPattern.COMPARATOR.compare(o1.getPathPattern(), o2.getPathPattern());
         if (cmp == 0) {
            int locatorCmp = o1.resourceLocators.size() - o2.resourceLocators.size();
            return locatorCmp == 0 ? o2.regex.compareTo(o1.regex) : locatorCmp;
         } else {
            return cmp;
         }
      }
   };
   private final String regex;
   private final List resourceMethods;
   private final List resourceLocators;
   private final List childRuntimeResources;
   private final List resources;
   private final RuntimeResource parent;
   private final PathPattern pathPattern;

   private RuntimeResource(List resources, List childRuntimeResourceBuilders, RuntimeResource parent, String regex) {
      this.parent = parent;
      this.pathPattern = ((Resource)resources.get(0)).getPathPattern();
      this.resources = new ArrayList(resources);
      this.regex = regex;
      this.resourceMethods = new ArrayList();
      this.resourceLocators = new ArrayList();
      this.childRuntimeResources = new ArrayList();

      for(Builder childRuntimeResourceBuilder : childRuntimeResourceBuilders) {
         this.childRuntimeResources.add(childRuntimeResourceBuilder.build(this));
      }

      Collections.sort(this.childRuntimeResources, COMPARATOR);

      for(Resource res : this.resources) {
         this.resourceMethods.addAll(res.getResourceMethods());
         ResourceMethod resourceLocator = res.getResourceLocator();
         if (resourceLocator != null) {
            this.resourceLocators.add(resourceLocator);
         }
      }

   }

   public List getChildRuntimeResources() {
      return this.childRuntimeResources;
   }

   public String getRegex() {
      return this.regex;
   }

   public List getResourceMethods() {
      return this.resourceMethods;
   }

   public List getResourceLocators() {
      return this.resourceLocators;
   }

   public ResourceMethod getResourceLocator() {
      return this.resourceLocators.size() >= 1 ? (ResourceMethod)this.resourceLocators.get(0) : null;
   }

   public RuntimeResource getParent() {
      return this.parent;
   }

   public PathPattern getPathPattern() {
      return this.pathPattern;
   }

   public String getFullPathRegex() {
      return this.parent == null ? this.regex : this.parent.getRegex() + this.regex;
   }

   public List getParentResources() {
      return (List)this.resources.stream().map((child) -> child == null ? null : child.getParent()).collect(Collectors.toList());
   }

   public List getResources() {
      return this.resources;
   }

   public void accept(ResourceModelVisitor visitor) {
      visitor.visitRuntimeResource(this);
   }

   public List getComponents() {
      return this.getChildRuntimeResources();
   }

   static class Builder {
      private final List resources;
      private final String regex;
      private final List childRuntimeResourceBuilders;

      public Builder(List resources, List childRuntimeResourceBuilders, String regex) {
         this.childRuntimeResourceBuilders = childRuntimeResourceBuilders;
         this.resources = resources;
         this.regex = regex;
      }

      public RuntimeResource build(RuntimeResource parent) {
         return new RuntimeResource(this.resources, this.childRuntimeResourceBuilders, parent, this.regex);
      }
   }
}
