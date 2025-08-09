package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.util.Collection;

public abstract class SubtypeResolver {
   public SubtypeResolver copy() {
      return this;
   }

   public abstract void registerSubtypes(NamedType... var1);

   public abstract void registerSubtypes(Class... var1);

   public abstract void registerSubtypes(Collection var1);

   public Collection collectAndResolveSubtypesByClass(MapperConfig config, AnnotatedMember property, JavaType baseType) {
      return this.collectAndResolveSubtypes(property, config, config.getAnnotationIntrospector(), baseType);
   }

   public Collection collectAndResolveSubtypesByClass(MapperConfig config, AnnotatedClass baseType) {
      return this.collectAndResolveSubtypes(baseType, config, config.getAnnotationIntrospector());
   }

   public Collection collectAndResolveSubtypesByTypeId(MapperConfig config, AnnotatedMember property, JavaType baseType) {
      return this.collectAndResolveSubtypes(property, config, config.getAnnotationIntrospector(), baseType);
   }

   public Collection collectAndResolveSubtypesByTypeId(MapperConfig config, AnnotatedClass baseType) {
      return this.collectAndResolveSubtypes(baseType, config, config.getAnnotationIntrospector());
   }

   /** @deprecated */
   @Deprecated
   public Collection collectAndResolveSubtypes(AnnotatedMember property, MapperConfig config, AnnotationIntrospector ai, JavaType baseType) {
      return this.collectAndResolveSubtypesByClass(config, property, baseType);
   }

   /** @deprecated */
   @Deprecated
   public Collection collectAndResolveSubtypes(AnnotatedClass baseType, MapperConfig config, AnnotationIntrospector ai) {
      return this.collectAndResolveSubtypesByClass(config, baseType);
   }
}
