package com.fasterxml.jackson.module.scala;

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
   bytes = "\u0006\u0005a<QAC\u0006\t\u0002Y1Q\u0001G\u0006\t\u0002eAQAI\u0001\u0005\u0002\rBq\u0001J\u0001C\u0002\u0013%Q\u0005\u0003\u00049\u0003\u0001\u0006IA\n\u0005\b\u0001\u0006\u0011\r\u0011\"\u0003B\u0011\u0019I\u0015\u0001)A\u0005\u0005\")!*\u0001C\u0005\u0017\")q,\u0001C!A\"9\u0001.AA\u0001\n\u0013I\u0017!\n#fM\u0006,H\u000e\u001e*fcVL'/\u001a3B]:|G/\u0019;j_:Le\u000e\u001e:pgB,7\r^8s\u0015\taQ\"A\u0003tG\u0006d\u0017M\u0003\u0002\u000f\u001f\u00051Qn\u001c3vY\u0016T!\u0001E\t\u0002\u000f)\f7m[:p]*\u0011!cE\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011\u0001F\u0001\u0004G>l7\u0001\u0001\t\u0003/\u0005i\u0011a\u0003\u0002&\t\u00164\u0017-\u001e7u%\u0016\fX/\u001b:fI\u0006sgn\u001c;bi&|g.\u00138ue>\u001c\b/Z2u_J\u001c\"!\u0001\u000e\u0011\u0005m\u0001S\"\u0001\u000f\u000b\u0005uq\u0012AC5oiJ|7\u000f]3di*\u0011qdD\u0001\tI\u0006$\u0018MY5oI&\u0011\u0011\u0005\b\u0002\u001a\u001d>\u0004\u0018I\u001c8pi\u0006$\u0018n\u001c8J]R\u0014xn\u001d9fGR|'/\u0001\u0004=S:LGO\u0010\u000b\u0002-\u00051q\n\u0015+J\u001f:+\u0012A\n\t\u0004O1rS\"\u0001\u0015\u000b\u0005%R\u0013\u0001\u00027b]\u001eT\u0011aK\u0001\u0005U\u00064\u0018-\u0003\u0002.Q\t)1\t\\1tgB\u0012qF\u000e\t\u0004aI\"T\"A\u0019\u000b\u00031I!aM\u0019\u0003\r=\u0003H/[8o!\t)d\u0007\u0004\u0001\u0005\u0013]\"\u0011\u0011!A\u0001\u0006\u0003I$aA0%c\u00059q\n\u0015+J\u001f:\u0003\u0013C\u0001\u001e>!\t\u00014(\u0003\u0002=c\t9aj\u001c;iS:<\u0007C\u0001\u0019?\u0013\ty\u0014GA\u0002B]f\fQBS*P\u001d~\u0003&k\u0014)F%RKV#\u0001\"\u0011\u0007\u001db3\t\u0005\u0002E\u000f6\tQI\u0003\u0002G\u001f\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005!+%\u0001\u0004&t_:\u0004&o\u001c9feRL\u0018A\u0004&T\u001f:{\u0006KU(Q\u000bJ#\u0016\fI\u0001\rSN|\u0005\u000f^5p]RK\b/\u001a\u000b\u0003\u0019>\u0003\"\u0001M'\n\u00059\u000b$a\u0002\"p_2,\u0017M\u001c\u0005\u0006!\u001e\u0001\r!U\u0001\u0004G2\u001c\bG\u0001*^!\r\u0019&\f\u0018\b\u0003)b\u0003\"!V\u0019\u000e\u0003YS!aV\u000b\u0002\rq\u0012xn\u001c;?\u0013\tI\u0016'\u0001\u0004Qe\u0016$WMZ\u0005\u0003[mS!!W\u0019\u0011\u0005UjF!\u00030P\u0003\u0003\u0005\tQ!\u0001:\u0005\ryFEM\u0001\u0012Q\u0006\u001c(+Z9vSJ,G-T1sW\u0016\u0014HCA1d!\t9#-\u0003\u0002OQ!)A\r\u0003a\u0001K\u0006\tQ\u000e\u0005\u0002\u001cM&\u0011q\r\b\u0002\u0010\u0003:tw\u000e^1uK\u0012lU-\u001c2fe\u0006aqO]5uKJ+\u0007\u000f\\1dKR\t!\u000e\u0005\u0002(W&\u0011A\u000e\u000b\u0002\u0007\u001f\nTWm\u0019;)\r\u0005q\u0017O\u001d;v!\t\u0001t.\u0003\u0002qc\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\n1/A*xS2d\u0007EY3!e\u0016lwN^3eA%t\u0007e\r\u00181]A\u0002#/\u001a7fCN,\u0007%Y:!U\u0006\u001c7n]8o[5|G-\u001e7f[)\u001cxN\\*dQ\u0016l\u0017\rI5tA\t,\u0017N\\4!I&\u001c8m\u001c8uS:,X\rZ\u0001\u0006g&t7-Z\u0011\u0002m\u00061!GL\u00194]ABc\u0001\u00018reR,\b"
)
public final class DefaultRequiredAnnotationIntrospector {
   public static Boolean hasRequiredMarker(final AnnotatedMember m) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasRequiredMarker(m);
   }

   public static Version version() {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.version();
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasAnySetterAnnotation(final AnnotatedMethod x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasAnySetterAnnotation(x$1);
   }

   public static Boolean findMergeInfo(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findMergeInfo(x$1);
   }

   public static JsonSetter.Value findSetterInfo(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findSetterInfo(x$1);
   }

   public static Boolean hasAnySetter(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasAnySetter(x$1);
   }

   public static PropertyName findNameForDeserialization(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findNameForDeserialization(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static JsonCreator.Mode findCreatorBinding(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findCreatorBinding(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasCreatorAnnotation(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasCreatorAnnotation(x$1);
   }

   public static PotentialCreator findDefaultCreator(final MapperConfig x$1, final AnnotatedClass x$2, final List x$3, final List x$4) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findDefaultCreator(x$1, x$2, x$3, x$4);
   }

   public static JsonCreator.Mode findCreatorAnnotation(final MapperConfig x$1, final Annotated x$2) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findCreatorAnnotation(x$1, x$2);
   }

   public static JsonPOJOBuilder.Value findPOJOBuilderConfig(final AnnotatedClass x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPOJOBuilderConfig(x$1);
   }

   public static Class findPOJOBuilder(final AnnotatedClass x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPOJOBuilder(x$1);
   }

   public static Object findValueInstantiator(final AnnotatedClass x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findValueInstantiator(x$1);
   }

   public static JavaType refineDeserializationType(final MapperConfig x$1, final Annotated x$2, final JavaType x$3) throws JsonMappingException {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.refineDeserializationType(x$1, x$2, x$3);
   }

   public static Object findDeserializationContentConverter(final AnnotatedMember x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findDeserializationContentConverter(x$1);
   }

   public static Object findDeserializationConverter(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findDeserializationConverter(x$1);
   }

   public static Object findContentDeserializer(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findContentDeserializer(x$1);
   }

   public static Object findKeyDeserializer(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findKeyDeserializer(x$1);
   }

   public static Object findDeserializer(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findDeserializer(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasAnyGetterAnnotation(final AnnotatedMethod x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasAnyGetterAnnotation(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasAsValueAnnotation(final AnnotatedMethod x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasAsValueAnnotation(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static String findEnumValue(final Enum x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findEnumValue(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static Enum findDefaultEnumValue(final Class x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findDefaultEnumValue(x$1);
   }

   public static Enum findDefaultEnumValue(final AnnotatedClass x$1, final Enum[] x$2) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findDefaultEnumValue(x$1, x$2);
   }

   public static void findEnumAliases(final MapperConfig x$1, final AnnotatedClass x$2, final Enum[] x$3, final String[][] x$4) {
      DefaultRequiredAnnotationIntrospector$.MODULE$.findEnumAliases(x$1, x$2, x$3, x$4);
   }

   /** @deprecated */
   @Deprecated
   public static void findEnumAliases(final Class x$1, final Enum[] x$2, final String[][] x$3) {
      DefaultRequiredAnnotationIntrospector$.MODULE$.findEnumAliases(x$1, x$2, x$3);
   }

   public static String[] findEnumValues(final MapperConfig x$1, final AnnotatedClass x$2, final Enum[] x$3, final String[] x$4) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findEnumValues(x$1, x$2, x$3, x$4);
   }

   /** @deprecated */
   @Deprecated
   public static String[] findEnumValues(final Class x$1, final Enum[] x$2, final String[] x$3) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findEnumValues(x$1, x$2, x$3);
   }

   public static Boolean hasAnyGetter(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasAnyGetter(x$1);
   }

   public static Boolean hasAsValue(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasAsValue(x$1);
   }

   public static Boolean hasAsKey(final MapperConfig x$1, final Annotated x$2) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasAsKey(x$1, x$2);
   }

   public static PropertyName findNameForSerialization(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findNameForSerialization(x$1);
   }

   public static void findAndAddVirtualProperties(final MapperConfig x$1, final AnnotatedClass x$2, final List x$3) {
      DefaultRequiredAnnotationIntrospector$.MODULE$.findAndAddVirtualProperties(x$1, x$2, x$3);
   }

   public static Boolean findSerializationSortAlphabetically(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findSerializationSortAlphabetically(x$1);
   }

   public static String[] findSerializationPropertyOrder(final AnnotatedClass x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findSerializationPropertyOrder(x$1);
   }

   public static JavaType refineSerializationType(final MapperConfig x$1, final Annotated x$2, final JavaType x$3) throws JsonMappingException {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.refineSerializationType(x$1, x$2, x$3);
   }

   public static JsonInclude.Value findPropertyInclusion(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyInclusion(x$1);
   }

   public static Object findSerializationContentConverter(final AnnotatedMember x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findSerializationContentConverter(x$1);
   }

   public static Object findSerializationConverter(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findSerializationConverter(x$1);
   }

   public static JsonSerialize.Typing findSerializationTyping(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findSerializationTyping(x$1);
   }

   public static Object findNullSerializer(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findNullSerializer(x$1);
   }

   public static Object findContentSerializer(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findContentSerializer(x$1);
   }

   public static Object findKeySerializer(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findKeySerializer(x$1);
   }

   public static Object findSerializer(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findSerializer(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static Object findInjectableValueId(final AnnotatedMember x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findInjectableValueId(x$1);
   }

   public static PropertyName findRenameByField(final MapperConfig x$1, final AnnotatedField x$2, final PropertyName x$3) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findRenameByField(x$1, x$2, x$3);
   }

   public static AnnotatedMethod resolveSetterConflict(final MapperConfig x$1, final AnnotatedMethod x$2, final AnnotatedMethod x$3) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.resolveSetterConflict(x$1, x$2, x$3);
   }

   public static JsonProperty.Access findPropertyAccess(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyAccess(x$1);
   }

   public static List findPropertyAliases(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyAliases(x$1);
   }

   public static String findImplicitPropertyName(final AnnotatedMember x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findImplicitPropertyName(x$1);
   }

   public static Integer findPropertyIndex(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyIndex(x$1);
   }

   public static String findPropertyDescription(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyDescription(x$1);
   }

   public static String findPropertyDefaultValue(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyDefaultValue(x$1);
   }

   public static PropertyName findWrapperName(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findWrapperName(x$1);
   }

   public static JsonFormat.Value findFormat(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findFormat(x$1);
   }

   public static Class[] findViews(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findViews(x$1);
   }

   public static JacksonInject.Value findInjectableValue(final AnnotatedMember x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findInjectableValue(x$1);
   }

   public static boolean hasIgnoreMarker(final AnnotatedMember x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.hasIgnoreMarker(x$1);
   }

   public static NameTransformer findUnwrappingNameTransformer(final AnnotatedMember x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findUnwrappingNameTransformer(x$1);
   }

   public static AnnotationIntrospector.ReferenceProperty findReferenceType(final AnnotatedMember x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findReferenceType(x$1);
   }

   public static Boolean isTypeId(final AnnotatedMember x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.isTypeId(x$1);
   }

   public static String findTypeName(final AnnotatedClass x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findTypeName(x$1);
   }

   public static List findSubtypes(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findSubtypes(x$1);
   }

   public static TypeResolverBuilder findPropertyContentTypeResolver(final MapperConfig x$1, final AnnotatedMember x$2, final JavaType x$3) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyContentTypeResolver(x$1, x$2, x$3);
   }

   public static TypeResolverBuilder findPropertyTypeResolver(final MapperConfig x$1, final AnnotatedMember x$2, final JavaType x$3) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyTypeResolver(x$1, x$2, x$3);
   }

   public static TypeResolverBuilder findTypeResolver(final MapperConfig x$1, final AnnotatedClass x$2, final JavaType x$3) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findTypeResolver(x$1, x$2, x$3);
   }

   public static JsonTypeInfo.Value findPolymorphicTypeInfo(final MapperConfig x$1, final Annotated x$2) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPolymorphicTypeInfo(x$1, x$2);
   }

   public static VisibilityChecker findAutoDetectVisibility(final AnnotatedClass x$1, final VisibilityChecker x$2) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findAutoDetectVisibility(x$1, x$2);
   }

   /** @deprecated */
   @Deprecated
   public static JsonIgnoreProperties.Value findPropertyIgnorals(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyIgnorals(x$1);
   }

   public static String findClassDescription(final AnnotatedClass x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findClassDescription(x$1);
   }

   public static Object findEnumNamingStrategy(final MapperConfig x$1, final AnnotatedClass x$2) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findEnumNamingStrategy(x$1, x$2);
   }

   public static Object findNamingStrategy(final AnnotatedClass x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findNamingStrategy(x$1);
   }

   public static Object findFilterId(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findFilterId(x$1);
   }

   public static JsonIncludeProperties.Value findPropertyInclusionByName(final MapperConfig x$1, final Annotated x$2) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyInclusionByName(x$1, x$2);
   }

   public static JsonIgnoreProperties.Value findPropertyIgnoralByName(final MapperConfig x$1, final Annotated x$2) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findPropertyIgnoralByName(x$1, x$2);
   }

   public static Boolean isIgnorableType(final AnnotatedClass x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.isIgnorableType(x$1);
   }

   public static PropertyName findRootName(final AnnotatedClass x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findRootName(x$1);
   }

   public static ObjectIdInfo findObjectReferenceInfo(final Annotated x$1, final ObjectIdInfo x$2) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findObjectReferenceInfo(x$1, x$2);
   }

   public static ObjectIdInfo findObjectIdInfo(final Annotated x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.findObjectIdInfo(x$1);
   }

   public static boolean isAnnotationBundle(final Annotation x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.isAnnotationBundle(x$1);
   }

   public static Collection allIntrospectors(final Collection x$1) {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.allIntrospectors(x$1);
   }

   public static Collection allIntrospectors() {
      return DefaultRequiredAnnotationIntrospector$.MODULE$.allIntrospectors();
   }
}
