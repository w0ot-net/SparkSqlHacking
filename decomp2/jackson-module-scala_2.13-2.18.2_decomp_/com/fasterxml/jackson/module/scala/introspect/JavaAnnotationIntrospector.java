package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.PotentialCreator;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I;Qa\u0002\u0005\t\u0002U1Qa\u0006\u0005\t\u0002aAQ\u0001I\u0001\u0005\u0002\u0005BQAI\u0001\u0005B\rBQ!L\u0001\u0005B9BQ\u0001Q\u0001\u0005B\u0005Cq\u0001S\u0001\u0002\u0002\u0013%\u0011*\u0001\u000eKCZ\f\u0017I\u001c8pi\u0006$\u0018n\u001c8J]R\u0014xn\u001d9fGR|'O\u0003\u0002\n\u0015\u0005Q\u0011N\u001c;s_N\u0004Xm\u0019;\u000b\u0005-a\u0011!B:dC2\f'BA\u0007\u000f\u0003\u0019iw\u000eZ;mK*\u0011q\u0002E\u0001\bU\u0006\u001c7n]8o\u0015\t\t\"#A\u0005gCN$XM\u001d=nY*\t1#A\u0002d_6\u001c\u0001\u0001\u0005\u0002\u0017\u00035\t\u0001B\u0001\u000eKCZ\f\u0017I\u001c8pi\u0006$\u0018n\u001c8J]R\u0014xn\u001d9fGR|'o\u0005\u0002\u00023A\u0011!DH\u0007\u00027)\u0011\u0011\u0002\b\u0006\u0003;9\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003?m\u0011\u0011DT8q\u0003:tw\u000e^1uS>t\u0017J\u001c;s_N\u0004Xm\u0019;pe\u00061A(\u001b8jiz\"\u0012!F\u0001\u001bM&tGMT1nK\u001a{'\u000fR3tKJL\u0017\r\\5{CRLwN\u001c\u000b\u0003I!\u0002\"!\n\u0014\u000e\u0003qI!a\n\u000f\u0003\u0019A\u0013x\u000e]3sift\u0015-\\3\t\u000b%\u001a\u0001\u0019\u0001\u0016\u0002\u0003\u0005\u0004\"AG\u0016\n\u00051Z\"!C!o]>$\u0018\r^3e\u0003a1\u0017N\u001c3J[Bd\u0017nY5u!J|\u0007/\u001a:us:\u000bW.\u001a\u000b\u0003_m\u0002\"\u0001\r\u001d\u000f\u0005E2\u0004C\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b\u0015\u0003\u0019a$o\\8u})\t1\"\u0003\u00028k\u00051\u0001K]3eK\u001aL!!\u000f\u001e\u0003\rM#(/\u001b8h\u0015\t9T\u0007C\u0003=\t\u0001\u0007Q(A\u0003qCJ\fW\u000e\u0005\u0002\u001b}%\u0011qh\u0007\u0002\u0010\u0003:tw\u000e^1uK\u0012lU-\u001c2fe\u00069a/\u001a:tS>tG#\u0001\"\u0011\u0005\r3U\"\u0001#\u000b\u0005\u0015s\u0011\u0001B2pe\u0016L!a\u0012#\u0003\u000fY+'o]5p]\u0006aqO]5uKJ+\u0007\u000f\\1dKR\t!\n\u0005\u0002L!6\tAJ\u0003\u0002N\u001d\u0006!A.\u00198h\u0015\u0005y\u0015\u0001\u00026bm\u0006L!!\u0015'\u0003\r=\u0013'.Z2u\u0001"
)
public final class JavaAnnotationIntrospector {
   public static Version version() {
      return JavaAnnotationIntrospector$.MODULE$.version();
   }

   public static String findImplicitPropertyName(final AnnotatedMember param) {
      return JavaAnnotationIntrospector$.MODULE$.findImplicitPropertyName(param);
   }

   public static PropertyName findNameForDeserialization(final Annotated a) {
      return JavaAnnotationIntrospector$.MODULE$.findNameForDeserialization(a);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasAnySetterAnnotation(final AnnotatedMethod x$1) {
      return JavaAnnotationIntrospector$.MODULE$.hasAnySetterAnnotation(x$1);
   }

   public static Boolean findMergeInfo(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findMergeInfo(x$1);
   }

   public static JsonSetter.Value findSetterInfo(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findSetterInfo(x$1);
   }

   public static Boolean hasAnySetter(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.hasAnySetter(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static JsonCreator.Mode findCreatorBinding(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findCreatorBinding(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasCreatorAnnotation(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.hasCreatorAnnotation(x$1);
   }

   public static PotentialCreator findDefaultCreator(final MapperConfig x$1, final AnnotatedClass x$2, final List x$3, final List x$4) {
      return JavaAnnotationIntrospector$.MODULE$.findDefaultCreator(x$1, x$2, x$3, x$4);
   }

   public static JsonCreator.Mode findCreatorAnnotation(final MapperConfig x$1, final Annotated x$2) {
      return JavaAnnotationIntrospector$.MODULE$.findCreatorAnnotation(x$1, x$2);
   }

   public static JsonPOJOBuilder.Value findPOJOBuilderConfig(final AnnotatedClass x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findPOJOBuilderConfig(x$1);
   }

   public static Class findPOJOBuilder(final AnnotatedClass x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findPOJOBuilder(x$1);
   }

   public static Object findValueInstantiator(final AnnotatedClass x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findValueInstantiator(x$1);
   }

   public static JavaType refineDeserializationType(final MapperConfig x$1, final Annotated x$2, final JavaType x$3) throws JsonMappingException {
      return JavaAnnotationIntrospector$.MODULE$.refineDeserializationType(x$1, x$2, x$3);
   }

   public static Object findDeserializationContentConverter(final AnnotatedMember x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findDeserializationContentConverter(x$1);
   }

   public static Object findDeserializationConverter(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findDeserializationConverter(x$1);
   }

   public static Object findContentDeserializer(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findContentDeserializer(x$1);
   }

   public static Object findKeyDeserializer(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findKeyDeserializer(x$1);
   }

   public static Object findDeserializer(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findDeserializer(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasAnyGetterAnnotation(final AnnotatedMethod x$1) {
      return JavaAnnotationIntrospector$.MODULE$.hasAnyGetterAnnotation(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasAsValueAnnotation(final AnnotatedMethod x$1) {
      return JavaAnnotationIntrospector$.MODULE$.hasAsValueAnnotation(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static String findEnumValue(final Enum x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findEnumValue(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static Enum findDefaultEnumValue(final Class x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findDefaultEnumValue(x$1);
   }

   public static Enum findDefaultEnumValue(final AnnotatedClass x$1, final Enum[] x$2) {
      return JavaAnnotationIntrospector$.MODULE$.findDefaultEnumValue(x$1, x$2);
   }

   public static void findEnumAliases(final MapperConfig x$1, final AnnotatedClass x$2, final Enum[] x$3, final String[][] x$4) {
      JavaAnnotationIntrospector$.MODULE$.findEnumAliases(x$1, x$2, x$3, x$4);
   }

   /** @deprecated */
   @Deprecated
   public static void findEnumAliases(final Class x$1, final Enum[] x$2, final String[][] x$3) {
      JavaAnnotationIntrospector$.MODULE$.findEnumAliases(x$1, x$2, x$3);
   }

   public static String[] findEnumValues(final MapperConfig x$1, final AnnotatedClass x$2, final Enum[] x$3, final String[] x$4) {
      return JavaAnnotationIntrospector$.MODULE$.findEnumValues(x$1, x$2, x$3, x$4);
   }

   /** @deprecated */
   @Deprecated
   public static String[] findEnumValues(final Class x$1, final Enum[] x$2, final String[] x$3) {
      return JavaAnnotationIntrospector$.MODULE$.findEnumValues(x$1, x$2, x$3);
   }

   public static Boolean hasAnyGetter(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.hasAnyGetter(x$1);
   }

   public static Boolean hasAsValue(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.hasAsValue(x$1);
   }

   public static Boolean hasAsKey(final MapperConfig x$1, final Annotated x$2) {
      return JavaAnnotationIntrospector$.MODULE$.hasAsKey(x$1, x$2);
   }

   public static PropertyName findNameForSerialization(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findNameForSerialization(x$1);
   }

   public static void findAndAddVirtualProperties(final MapperConfig x$1, final AnnotatedClass x$2, final List x$3) {
      JavaAnnotationIntrospector$.MODULE$.findAndAddVirtualProperties(x$1, x$2, x$3);
   }

   public static Boolean findSerializationSortAlphabetically(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findSerializationSortAlphabetically(x$1);
   }

   public static String[] findSerializationPropertyOrder(final AnnotatedClass x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findSerializationPropertyOrder(x$1);
   }

   public static JavaType refineSerializationType(final MapperConfig x$1, final Annotated x$2, final JavaType x$3) throws JsonMappingException {
      return JavaAnnotationIntrospector$.MODULE$.refineSerializationType(x$1, x$2, x$3);
   }

   public static JsonInclude.Value findPropertyInclusion(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyInclusion(x$1);
   }

   public static Object findSerializationContentConverter(final AnnotatedMember x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findSerializationContentConverter(x$1);
   }

   public static Object findSerializationConverter(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findSerializationConverter(x$1);
   }

   public static JsonSerialize.Typing findSerializationTyping(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findSerializationTyping(x$1);
   }

   public static Object findNullSerializer(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findNullSerializer(x$1);
   }

   public static Object findContentSerializer(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findContentSerializer(x$1);
   }

   public static Object findKeySerializer(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findKeySerializer(x$1);
   }

   public static Object findSerializer(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findSerializer(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static Object findInjectableValueId(final AnnotatedMember x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findInjectableValueId(x$1);
   }

   public static PropertyName findRenameByField(final MapperConfig x$1, final AnnotatedField x$2, final PropertyName x$3) {
      return JavaAnnotationIntrospector$.MODULE$.findRenameByField(x$1, x$2, x$3);
   }

   public static AnnotatedMethod resolveSetterConflict(final MapperConfig x$1, final AnnotatedMethod x$2, final AnnotatedMethod x$3) {
      return JavaAnnotationIntrospector$.MODULE$.resolveSetterConflict(x$1, x$2, x$3);
   }

   public static JsonProperty.Access findPropertyAccess(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyAccess(x$1);
   }

   public static List findPropertyAliases(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyAliases(x$1);
   }

   public static Integer findPropertyIndex(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyIndex(x$1);
   }

   public static String findPropertyDescription(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyDescription(x$1);
   }

   public static String findPropertyDefaultValue(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyDefaultValue(x$1);
   }

   public static PropertyName findWrapperName(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findWrapperName(x$1);
   }

   public static JsonFormat.Value findFormat(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findFormat(x$1);
   }

   public static Class[] findViews(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findViews(x$1);
   }

   public static Boolean hasRequiredMarker(final AnnotatedMember x$1) {
      return JavaAnnotationIntrospector$.MODULE$.hasRequiredMarker(x$1);
   }

   public static JacksonInject.Value findInjectableValue(final AnnotatedMember x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findInjectableValue(x$1);
   }

   public static boolean hasIgnoreMarker(final AnnotatedMember x$1) {
      return JavaAnnotationIntrospector$.MODULE$.hasIgnoreMarker(x$1);
   }

   public static NameTransformer findUnwrappingNameTransformer(final AnnotatedMember x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findUnwrappingNameTransformer(x$1);
   }

   public static AnnotationIntrospector.ReferenceProperty findReferenceType(final AnnotatedMember x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findReferenceType(x$1);
   }

   public static Boolean isTypeId(final AnnotatedMember x$1) {
      return JavaAnnotationIntrospector$.MODULE$.isTypeId(x$1);
   }

   public static String findTypeName(final AnnotatedClass x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findTypeName(x$1);
   }

   public static List findSubtypes(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findSubtypes(x$1);
   }

   public static TypeResolverBuilder findPropertyContentTypeResolver(final MapperConfig x$1, final AnnotatedMember x$2, final JavaType x$3) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyContentTypeResolver(x$1, x$2, x$3);
   }

   public static TypeResolverBuilder findPropertyTypeResolver(final MapperConfig x$1, final AnnotatedMember x$2, final JavaType x$3) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyTypeResolver(x$1, x$2, x$3);
   }

   public static TypeResolverBuilder findTypeResolver(final MapperConfig x$1, final AnnotatedClass x$2, final JavaType x$3) {
      return JavaAnnotationIntrospector$.MODULE$.findTypeResolver(x$1, x$2, x$3);
   }

   public static JsonTypeInfo.Value findPolymorphicTypeInfo(final MapperConfig x$1, final Annotated x$2) {
      return JavaAnnotationIntrospector$.MODULE$.findPolymorphicTypeInfo(x$1, x$2);
   }

   public static VisibilityChecker findAutoDetectVisibility(final AnnotatedClass x$1, final VisibilityChecker x$2) {
      return JavaAnnotationIntrospector$.MODULE$.findAutoDetectVisibility(x$1, x$2);
   }

   /** @deprecated */
   @Deprecated
   public static JsonIgnoreProperties.Value findPropertyIgnorals(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyIgnorals(x$1);
   }

   public static String findClassDescription(final AnnotatedClass x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findClassDescription(x$1);
   }

   public static Object findEnumNamingStrategy(final MapperConfig x$1, final AnnotatedClass x$2) {
      return JavaAnnotationIntrospector$.MODULE$.findEnumNamingStrategy(x$1, x$2);
   }

   public static Object findNamingStrategy(final AnnotatedClass x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findNamingStrategy(x$1);
   }

   public static Object findFilterId(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findFilterId(x$1);
   }

   public static JsonIncludeProperties.Value findPropertyInclusionByName(final MapperConfig x$1, final Annotated x$2) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyInclusionByName(x$1, x$2);
   }

   public static JsonIgnoreProperties.Value findPropertyIgnoralByName(final MapperConfig x$1, final Annotated x$2) {
      return JavaAnnotationIntrospector$.MODULE$.findPropertyIgnoralByName(x$1, x$2);
   }

   public static Boolean isIgnorableType(final AnnotatedClass x$1) {
      return JavaAnnotationIntrospector$.MODULE$.isIgnorableType(x$1);
   }

   public static PropertyName findRootName(final AnnotatedClass x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findRootName(x$1);
   }

   public static ObjectIdInfo findObjectReferenceInfo(final Annotated x$1, final ObjectIdInfo x$2) {
      return JavaAnnotationIntrospector$.MODULE$.findObjectReferenceInfo(x$1, x$2);
   }

   public static ObjectIdInfo findObjectIdInfo(final Annotated x$1) {
      return JavaAnnotationIntrospector$.MODULE$.findObjectIdInfo(x$1);
   }

   public static boolean isAnnotationBundle(final Annotation x$1) {
      return JavaAnnotationIntrospector$.MODULE$.isAnnotationBundle(x$1);
   }

   public static Collection allIntrospectors(final Collection x$1) {
      return JavaAnnotationIntrospector$.MODULE$.allIntrospectors(x$1);
   }

   public static Collection allIntrospectors() {
      return JavaAnnotationIntrospector$.MODULE$.allIntrospectors();
   }
}
