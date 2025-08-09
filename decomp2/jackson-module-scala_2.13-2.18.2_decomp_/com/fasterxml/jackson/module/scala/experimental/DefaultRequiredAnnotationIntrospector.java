package com.fasterxml.jackson.module.scala.experimental;

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
   bytes = "\u0006\u0005\t;Q!\u0002\u0004\t\u0002M1Q!\u0006\u0004\t\u0002YAQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005B\tBq\u0001M\u0001\u0002\u0002\u0013%\u0011'A\u0013EK\u001a\fW\u000f\u001c;SKF,\u0018N]3e\u0003:tw\u000e^1uS>t\u0017J\u001c;s_N\u0004Xm\u0019;pe*\u0011q\u0001C\u0001\rKb\u0004XM]5nK:$\u0018\r\u001c\u0006\u0003\u0013)\tQa]2bY\u0006T!a\u0003\u0007\u0002\r5|G-\u001e7f\u0015\tia\"A\u0004kC\u000e\\7o\u001c8\u000b\u0005=\u0001\u0012!\u00034bgR,'\u000f_7m\u0015\u0005\t\u0012aA2p[\u000e\u0001\u0001C\u0001\u000b\u0002\u001b\u00051!!\n#fM\u0006,H\u000e\u001e*fcVL'/\u001a3B]:|G/\u0019;j_:Le\u000e\u001e:pgB,7\r^8s'\t\tq\u0003\u0005\u0002\u0019;5\t\u0011D\u0003\u0002\u001b7\u0005Q\u0011N\u001c;s_N\u0004Xm\u0019;\u000b\u0005qa\u0011\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005yI\"!\u0007(pa\u0006sgn\u001c;bi&|g.\u00138ue>\u001c\b/Z2u_J\fa\u0001P5oSRtD#A\n\u0002#!\f7OU3rk&\u0014X\rZ'be.,'\u000f\u0006\u0002$WA\u0011A%K\u0007\u0002K)\u0011aeJ\u0001\u0005Y\u0006twMC\u0001)\u0003\u0011Q\u0017M^1\n\u0005)*#a\u0002\"p_2,\u0017M\u001c\u0005\u0006Y\r\u0001\r!L\u0001\u0002[B\u0011\u0001DL\u0005\u0003_e\u0011q\"\u00118o_R\fG/\u001a3NK6\u0014WM]\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002eA\u0011AeM\u0005\u0003i\u0015\u0012aa\u00142kK\u000e$\bFB\u00017wqrt\b\u0005\u00028s5\t\u0001HC\u0001\n\u0013\tQ\u0004H\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-I\u0001>\u00031+8/\u001a\u0011d_6tc-Y:uKJDX\u000e\u001c\u0018kC\u000e\\7o\u001c8/[>$W\u000f\\3/g\u000e\fG.\u0019\u0018EK\u001a\fW\u000f\u001c;SKF,\u0018N]3e\u0003:tw\u000e^1uS>t\u0017J\u001c;s_N\u0004Xm\u0019;pe\u0006)1/\u001b8dK\u0006\n\u0001)\u0001\u00043]E\u0012d&\r\u0015\u0007\u0001YZDHP "
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
