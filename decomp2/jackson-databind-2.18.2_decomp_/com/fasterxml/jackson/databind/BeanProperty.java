package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Named;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

public interface BeanProperty extends Named {
   JsonFormat.Value EMPTY_FORMAT = new JsonFormat.Value();
   JsonInclude.Value EMPTY_INCLUDE = Value.empty();

   String getName();

   PropertyName getFullName();

   JavaType getType();

   PropertyName getWrapperName();

   PropertyMetadata getMetadata();

   boolean isRequired();

   boolean isVirtual();

   Annotation getAnnotation(Class var1);

   Annotation getContextAnnotation(Class var1);

   AnnotatedMember getMember();

   /** @deprecated */
   @Deprecated
   JsonFormat.Value findFormatOverrides(AnnotationIntrospector var1);

   JsonFormat.Value findPropertyFormat(MapperConfig var1, Class var2);

   JsonInclude.Value findPropertyInclusion(MapperConfig var1, Class var2);

   List findAliases(MapperConfig var1);

   void depositSchemaProperty(JsonObjectFormatVisitor var1, SerializerProvider var2) throws JsonMappingException;

   public static class Std implements BeanProperty, Serializable {
      private static final long serialVersionUID = 1L;
      protected final PropertyName _name;
      protected final JavaType _type;
      protected final PropertyName _wrapperName;
      protected final PropertyMetadata _metadata;
      protected final AnnotatedMember _member;

      public Std(PropertyName name, JavaType type, PropertyName wrapperName, AnnotatedMember member, PropertyMetadata metadata) {
         this._name = name;
         this._type = type;
         this._wrapperName = wrapperName;
         this._metadata = metadata;
         this._member = member;
      }

      /** @deprecated */
      @Deprecated
      public Std(PropertyName name, JavaType type, PropertyName wrapperName, Annotations contextAnnotations, AnnotatedMember member, PropertyMetadata metadata) {
         this(name, type, wrapperName, member, metadata);
      }

      public Std(Std base, JavaType newType) {
         this(base._name, newType, base._wrapperName, base._member, base._metadata);
      }

      public Std withType(JavaType type) {
         return new Std(this, type);
      }

      public Annotation getAnnotation(Class acls) {
         return this._member == null ? null : this._member.getAnnotation(acls);
      }

      public Annotation getContextAnnotation(Class acls) {
         return null;
      }

      /** @deprecated */
      @Deprecated
      public JsonFormat.Value findFormatOverrides(AnnotationIntrospector intr) {
         if (this._member != null && intr != null) {
            JsonFormat.Value v = intr.findFormat(this._member);
            if (v != null) {
               return v;
            }
         }

         return EMPTY_FORMAT;
      }

      public JsonFormat.Value findPropertyFormat(MapperConfig config, Class baseType) {
         JsonFormat.Value v0 = config.getDefaultPropertyFormat(baseType);
         AnnotationIntrospector intr = config.getAnnotationIntrospector();
         if (intr != null && this._member != null) {
            JsonFormat.Value v = intr.findFormat(this._member);
            return v == null ? v0 : v0.withOverrides(v);
         } else {
            return v0;
         }
      }

      public JsonInclude.Value findPropertyInclusion(MapperConfig config, Class baseType) {
         JsonInclude.Value v0 = config.getDefaultInclusion(baseType, this._type.getRawClass());
         AnnotationIntrospector intr = config.getAnnotationIntrospector();
         if (intr != null && this._member != null) {
            JsonInclude.Value v = intr.findPropertyInclusion(this._member);
            return v == null ? v0 : v0.withOverrides(v);
         } else {
            return v0;
         }
      }

      public List findAliases(MapperConfig config) {
         return Collections.emptyList();
      }

      public String getName() {
         return this._name.getSimpleName();
      }

      public PropertyName getFullName() {
         return this._name;
      }

      public JavaType getType() {
         return this._type;
      }

      public PropertyName getWrapperName() {
         return this._wrapperName;
      }

      public boolean isRequired() {
         return this._metadata.isRequired();
      }

      public PropertyMetadata getMetadata() {
         return this._metadata;
      }

      public AnnotatedMember getMember() {
         return this._member;
      }

      public boolean isVirtual() {
         return false;
      }

      public void depositSchemaProperty(JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) {
         throw new UnsupportedOperationException("Instances of " + this.getClass().getName() + " should not get visited");
      }
   }

   public static class Bogus implements BeanProperty {
      public String getName() {
         return "";
      }

      public PropertyName getFullName() {
         return PropertyName.NO_NAME;
      }

      public JavaType getType() {
         return TypeFactory.unknownType();
      }

      public PropertyName getWrapperName() {
         return null;
      }

      public PropertyMetadata getMetadata() {
         return PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
      }

      public boolean isRequired() {
         return false;
      }

      public boolean isVirtual() {
         return false;
      }

      public Annotation getAnnotation(Class acls) {
         return null;
      }

      public Annotation getContextAnnotation(Class acls) {
         return null;
      }

      public AnnotatedMember getMember() {
         return null;
      }

      /** @deprecated */
      @Deprecated
      public JsonFormat.Value findFormatOverrides(AnnotationIntrospector intr) {
         return com.fasterxml.jackson.annotation.JsonFormat.Value.empty();
      }

      public JsonFormat.Value findPropertyFormat(MapperConfig config, Class baseType) {
         return com.fasterxml.jackson.annotation.JsonFormat.Value.empty();
      }

      public JsonInclude.Value findPropertyInclusion(MapperConfig config, Class baseType) {
         return null;
      }

      public List findAliases(MapperConfig config) {
         return Collections.emptyList();
      }

      public void depositSchemaProperty(JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
      }
   }
}
