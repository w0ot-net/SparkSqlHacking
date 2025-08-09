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
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.PotentialCreator;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.lang.annotation.Annotation;
import java.lang.invoke.SerializedLambda;
import java.util.Collection;
import java.util.List;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala..less.colon.less.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t]r!B\u000f\u001f\u0011\u0003Yc!B\u0017\u001f\u0011\u0003q\u0003\"\u0002\u001f\u0002\t\u0003i\u0004\"\u0002 \u0002\t\u0003y\u0004\"B'\u0002\t\u0003r\u0005\"B0\u0002\t\u0003\u0002\u0007\"\u00024\u0002\t\u0003:\u0007\"B5\u0002\t\u0003R\u0007bBA\u000b\u0003\u0011\u0005\u0013q\u0003\u0005\b\u00037\tA\u0011IA\u000f\r\u0019\tY#\u0001\u0003\u0002.!Q\u00111\b\u0006\u0003\u0002\u0003\u0006I!!\u0010\t\u0015\u0005\r#B!A!\u0002\u0013\ty\u0003C\u0005w\u0015\t\u0005\t\u0015!\u0003\u0002F!Q\u0011Q\n\u0006\u0003\u0002\u0003\u0006I!a\u0014\t\rqRA\u0011AA+\u0011%\t\u0019G\u0003b\u0001\n\u0013\t)\u0007\u0003\u0005\u0002t)\u0001\u000b\u0011BA4\u0011\u001d\t)H\u0003C!\u0003oBq!a\u001f\u0002\t\u0013\ti\bC\u0004\u0002\u001e\u0006!\t%a(\t\u000f\u0005]\u0016\u0001\"\u0003\u0002:\"9\u0011qZ\u0001\u0005\n\u0005E\u0007bBAp\u0003\u0011%\u0011\u0011\u001d\u0005\b\u0003[\fA\u0011BAx\u0011\u001d\tY0\u0001C\u0005\u0003{D\u0001B!\u0006\u0002\t\u0003q\"q\u0003\u0005\b\u0005O\tA\u0011\u0002B\u0015\u0011%\u0011i#AA\u0001\n\u0013\u0011y#A\u000eTG\u0006d\u0017-\u00118o_R\fG/[8o\u0013:$(o\\:qK\u000e$xN\u001d\u0006\u0003?\u0001\n!\"\u001b8ue>\u001c\b/Z2u\u0015\t\t#%A\u0003tG\u0006d\u0017M\u0003\u0002$I\u00051Qn\u001c3vY\u0016T!!\n\u0014\u0002\u000f)\f7m[:p]*\u0011q\u0005K\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011!K\u0001\u0004G>l7\u0001\u0001\t\u0003Y\u0005i\u0011A\b\u0002\u001c'\u000e\fG.Y!o]>$\u0018\r^5p]&sGO]8ta\u0016\u001cGo\u001c:\u0014\u0007\u0005yc\u0007\u0005\u00021i5\t\u0011G\u0003\u0002 e)\u00111\u0007J\u0001\tI\u0006$\u0018MY5oI&\u0011Q'\r\u0002\u001a\u001d>\u0004\u0018I\u001c8pi\u0006$\u0018n\u001c8J]R\u0014xn\u001d9fGR|'\u000f\u0005\u00028u5\t\u0001H\u0003\u0002:e\u0005)A-Z:fe&\u00111\b\u000f\u0002\u0013-\u0006dW/Z%ogR\fg\u000e^5bi>\u00148/\u0001\u0004=S:LGO\u0010\u000b\u0002W\u0005Y\u0001O]8qKJ$\u0018PR8s)\t\u0001\u0005\nE\u0002B\u0007\u0016k\u0011A\u0011\u0006\u0002C%\u0011AI\u0011\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000512\u0015BA$\u001f\u0005I\u0001&o\u001c9feRLH)Z:de&\u0004Ho\u001c:\t\u000b%\u001b\u0001\u0019\u0001&\u0002\u0003\u0005\u0004\"\u0001M&\n\u00051\u000b$!C!o]>$\u0018\r^3e\u0003a1\u0017N\u001c3J[Bd\u0017nY5u!J|\u0007/\u001a:us:\u000bW.\u001a\u000b\u0003\u001fj\u0003\"\u0001U,\u000f\u0005E+\u0006C\u0001*C\u001b\u0005\u0019&B\u0001++\u0003\u0019a$o\\8u}%\u0011aKQ\u0001\u0007!J,G-\u001a4\n\u0005aK&AB*ue&twM\u0003\u0002W\u0005\")1\f\u0002a\u00019\u00061Q.Z7cKJ\u0004\"\u0001M/\n\u0005y\u000b$aD!o]>$\u0018\r^3e\u001b\u0016l'-\u001a:\u0002\u001f!\f7/S4o_J,W*\u0019:lKJ$\"!\u00193\u0011\u0005\u0005\u0013\u0017BA2C\u0005\u001d\u0011un\u001c7fC:DQ!Z\u0003A\u0002q\u000b\u0011!\\\u0001\u0015Q\u0006\u001c8I]3bi>\u0014\u0018I\u001c8pi\u0006$\u0018n\u001c8\u0015\u0005\u0005D\u0007\"B%\u0007\u0001\u0004Q\u0015!\u00064j]\u0012\u001c%/Z1u_J\feN\\8uCRLwN\u001c\u000b\u0005WV\f\u0019\u0002\u0005\u0002me:\u0011Q\u000e]\u0007\u0002]*\u0011q\u000eJ\u0001\u000bC:tw\u000e^1uS>t\u0017BA9o\u0003-Q5o\u001c8De\u0016\fGo\u001c:\n\u0005M$(\u0001B'pI\u0016T!!\u001d8\t\u000bY<\u0001\u0019A<\u0002\r\r|gNZ5ha\rA\u0018\u0011\u0001\t\u0004srtX\"\u0001>\u000b\u0005m\u0014\u0014aA2gO&\u0011QP\u001f\u0002\r\u001b\u0006\u0004\b/\u001a:D_:4\u0017n\u001a\t\u0004\u007f\u0006\u0005A\u0002\u0001\u0003\f\u0003\u0007)\u0018\u0011!A\u0001\u0006\u0003\t)AA\u0002`IE\nB!a\u0002\u0002\u000eA\u0019\u0011)!\u0003\n\u0007\u0005-!IA\u0004O_RD\u0017N\\4\u0011\u0007\u0005\u000by!C\u0002\u0002\u0012\t\u00131!\u00118z\u0011\u0015Iu\u00011\u0001K\u0003I1\u0017N\u001c3De\u0016\fGo\u001c:CS:$\u0017N\\4\u0015\u0007-\fI\u0002C\u0003J\u0011\u0001\u0007!*A\u0004wKJ\u001c\u0018n\u001c8\u0015\u0005\u0005}\u0001\u0003BA\u0011\u0003Oi!!a\t\u000b\u0007\u0005\u0015B%\u0001\u0003d_J,\u0017\u0002BA\u0015\u0003G\u0011qAV3sg&|gN\u0001\fTG\u0006d\u0017MV1mk\u0016Len\u001d;b]RL\u0017\r^8s'\rQ\u0011q\u0006\t\u0005\u0003c\t9$\u0004\u0002\u00024)\u0019\u0011Q\u0007\u001d\u0002\u0007M$H-\u0003\u0003\u0002:\u0005M\"\u0001F*uIZ\u000bG.^3J]N$\u0018M\u001c;jCR|'/A\u0011tG\u0006d\u0017-\u00118o_R\fG/[8o\u0013:$(o\\:qK\u000e$xN]'pIVdW\rE\u0002-\u0003\u007fI1!!\u0011\u001f\u0005\u0005\u001a6-\u00197b\u0003:tw\u000e^1uS>t\u0017J\u001c;s_N\u0004Xm\u0019;pe6{G-\u001e7f\u0003!!W\r\\3hCR,\u0007\u0003BA$\u0003\u0013j\u0011AM\u0005\u0004\u0003\u0017\u0012$!\u0006#fg\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_:4\u0017nZ\u0001\u000bI\u0016\u001c8M]5qi>\u0014\bc\u0001\u0017\u0002R%\u0019\u00111\u000b\u0010\u0003\u001d\t+\u0017M\u001c#fg\u000e\u0014\u0018\u000e\u001d;peRQ\u0011qKA.\u0003;\ny&!\u0019\u0011\u0007\u0005e#\"D\u0001\u0002\u0011\u001d\tYd\u0004a\u0001\u0003{Aq!a\u0011\u0010\u0001\u0004\ty\u0003\u0003\u0004w\u001f\u0001\u0007\u0011Q\t\u0005\b\u0003\u001bz\u0001\u0019AA(\u0003yyg/\u001a:sS\u0012$WM\\\"p]N$(/^2u_J\f%oZ;nK:$8/\u0006\u0002\u0002hA)\u0011)!\u001b\u0002n%\u0019\u00111\u000e\"\u0003\u000b\u0005\u0013(/Y=\u0011\u0007]\ny'C\u0002\u0002ra\u0012AcU3ui\u0006\u0014G.\u001a\"fC:\u0004&o\u001c9feRL\u0018aH8wKJ\u0014\u0018\u000e\u001a3f]\u000e{gn\u001d;sk\u000e$xN]!sOVlWM\u001c;tA\u00051r-\u001a;Ge>lwJ\u00196fGR\f%oZ;nK:$8\u000f\u0006\u0003\u0002h\u0005e\u0004B\u0002<\u0013\u0001\u0004\t)%\u0001\bbaBd\u0017p\u0014<feJLG-Z:\u0015\u0011\u0005}\u0014QQAE\u0003\u001b\u00032aNAA\u0013\r\t\u0019\t\u000f\u0002\u0010\u0007J,\u0017\r^8s!J|\u0007/\u001a:us\"9\u0011qQ\nA\u0002\u0005}\u0014aB2sK\u0006$xN\u001d\u0005\u0007\u0003\u0017\u001b\u0002\u0019A(\u0002\u0019A\u0014x\u000e]3sift\u0015-\\3\t\u000f\u0005=5\u00031\u0001\u0002\u0012\u0006IqN^3se&$Wm\u001d\t\u0007!\u0006Mu*a&\n\u0007\u0005U\u0015LA\u0002NCB\u00042\u0001LAM\u0013\r\tYJ\b\u0002\f\u00072\f7o\u001d%pY\u0012,'/A\u000bgS:$g+\u00197vK&s7\u000f^1oi&\fGo\u001c:\u0015\u0011\u0005\u0005\u0016qUAU\u0003g\u00032aNAR\u0013\r\t)\u000b\u000f\u0002\u0012-\u0006dW/Z%ogR\fg\u000e^5bi>\u0014\bB\u0002<\u0015\u0001\u0004\t)\u0005C\u0004\u0002,R\u0001\r!!,\u0002\u0011\t,\u0017M\u001c#fg\u000e\u0004B!a\u0012\u00020&\u0019\u0011\u0011\u0017\u001a\u0003\u001f\t+\u0017M\u001c#fg\u000e\u0014\u0018\u000e\u001d;j_:Dq!!.\u0015\u0001\u0004\t\t+A\neK\u001a\fW\u000f\u001c;J]N$\u0018M\u001c;jCR|'/\u0001\b`I\u0016\u001c8M]5qi>\u0014hi\u001c:\u0015\t\u0005m\u0016Q\u0018\t\u0005\u0003\u000e\u000by\u0005C\u0004\u0002@V\u0001\r!!1\u0002\u0007\rd'\u0010\r\u0003\u0002D\u0006-\u0007#\u0002)\u0002F\u0006%\u0017bAAd3\n)1\t\\1tgB\u0019q0a3\u0005\u0019\u00055\u0017QXA\u0001\u0002\u0003\u0015\t!!\u0002\u0003\u0007}##'A\u0005gS\u0016dGMT1nKR!\u00111[Ak!\r\t5i\u0014\u0005\b\u0003/4\u0002\u0019AAm\u0003\t\tg\rE\u00021\u00037L1!!82\u00059\teN\\8uCR,GMR5fY\u0012\f!\"\\3uQ>$g*Y7f)\u0011\t\u0019.a9\t\u000f\u0005\u0015x\u00031\u0001\u0002h\u0006\u0011\u0011-\u001c\t\u0004a\u0005%\u0018bAAvc\ty\u0011I\u001c8pi\u0006$X\rZ'fi\"|G-A\u0005qCJ\fWNT1nKR!\u00111[Ay\u0011\u001d\t\u0019\u0010\u0007a\u0001\u0003k\f!!\u00199\u0011\u0007A\n90C\u0002\u0002zF\u0012!#\u00118o_R\fG/\u001a3QCJ\fW.\u001a;fe\u0006q\u0011n]*dC2\f\u0007+Y2lC\u001e,GcA1\u0002\u0000\"9!\u0011A\rA\u0002\t\r\u0011a\u00019lOB!\u0011i\u0011B\u0003!\u0011\u00119A!\u0005\u000e\u0005\t%!\u0002\u0002B\u0006\u0005\u001b\tA\u0001\\1oO*\u0011!qB\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003\u0014\t%!a\u0002)bG.\fw-Z\u0001\u0015SNl\u0015-\u001f2f'\u000e\fG.\u0019\"fC:$\u0016\u0010]3\u0015\u0007\u0005\u0014I\u0002C\u0004\u0003\u001ci\u0001\rA!\b\u0002\u0007\rd7\u000f\r\u0003\u0003 \t\r\u0002#\u0002)\u0002F\n\u0005\u0002cA@\u0003$\u0011a!Q\u0005B\r\u0003\u0003\u0005\tQ!\u0001\u0002\u0006\t\u0019q\fJ\u001a\u0002\u000f%\u001c8kY1mCR\u0019\u0011Ma\u000b\t\u000b%[\u0002\u0019\u0001&\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tE\u0002\u0003\u0002B\u0004\u0005gIAA!\u000e\u0003\n\t1qJ\u00196fGR\u0004"
)
public final class ScalaAnnotationIntrospector {
   public static ValueInstantiator findValueInstantiator(final DeserializationConfig config, final BeanDescription beanDesc, final ValueInstantiator defaultInstantiator) {
      return ScalaAnnotationIntrospector$.MODULE$.findValueInstantiator(config, beanDesc, defaultInstantiator);
   }

   public static Version version() {
      return ScalaAnnotationIntrospector$.MODULE$.version();
   }

   public static JsonCreator.Mode findCreatorBinding(final Annotated a) {
      return ScalaAnnotationIntrospector$.MODULE$.findCreatorBinding(a);
   }

   public static JsonCreator.Mode findCreatorAnnotation(final MapperConfig config, final Annotated a) {
      return ScalaAnnotationIntrospector$.MODULE$.findCreatorAnnotation(config, a);
   }

   public static boolean hasCreatorAnnotation(final Annotated a) {
      return ScalaAnnotationIntrospector$.MODULE$.hasCreatorAnnotation(a);
   }

   public static boolean hasIgnoreMarker(final AnnotatedMember m) {
      return ScalaAnnotationIntrospector$.MODULE$.hasIgnoreMarker(m);
   }

   public static String findImplicitPropertyName(final AnnotatedMember member) {
      return ScalaAnnotationIntrospector$.MODULE$.findImplicitPropertyName(member);
   }

   public static Option propertyFor(final Annotated a) {
      return ScalaAnnotationIntrospector$.MODULE$.propertyFor(a);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasAnySetterAnnotation(final AnnotatedMethod x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.hasAnySetterAnnotation(x$1);
   }

   public static Boolean findMergeInfo(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findMergeInfo(x$1);
   }

   public static JsonSetter.Value findSetterInfo(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findSetterInfo(x$1);
   }

   public static Boolean hasAnySetter(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.hasAnySetter(x$1);
   }

   public static PropertyName findNameForDeserialization(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findNameForDeserialization(x$1);
   }

   public static PotentialCreator findDefaultCreator(final MapperConfig x$1, final AnnotatedClass x$2, final List x$3, final List x$4) {
      return ScalaAnnotationIntrospector$.MODULE$.findDefaultCreator(x$1, x$2, x$3, x$4);
   }

   public static JsonPOJOBuilder.Value findPOJOBuilderConfig(final AnnotatedClass x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findPOJOBuilderConfig(x$1);
   }

   public static Class findPOJOBuilder(final AnnotatedClass x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findPOJOBuilder(x$1);
   }

   public static Object findValueInstantiator(final AnnotatedClass x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findValueInstantiator(x$1);
   }

   public static JavaType refineDeserializationType(final MapperConfig x$1, final Annotated x$2, final JavaType x$3) throws JsonMappingException {
      return ScalaAnnotationIntrospector$.MODULE$.refineDeserializationType(x$1, x$2, x$3);
   }

   public static Object findDeserializationContentConverter(final AnnotatedMember x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findDeserializationContentConverter(x$1);
   }

   public static Object findDeserializationConverter(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findDeserializationConverter(x$1);
   }

   public static Object findContentDeserializer(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findContentDeserializer(x$1);
   }

   public static Object findKeyDeserializer(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findKeyDeserializer(x$1);
   }

   public static Object findDeserializer(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findDeserializer(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasAnyGetterAnnotation(final AnnotatedMethod x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.hasAnyGetterAnnotation(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static boolean hasAsValueAnnotation(final AnnotatedMethod x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.hasAsValueAnnotation(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static String findEnumValue(final Enum x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findEnumValue(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static Enum findDefaultEnumValue(final Class x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findDefaultEnumValue(x$1);
   }

   public static Enum findDefaultEnumValue(final AnnotatedClass x$1, final Enum[] x$2) {
      return ScalaAnnotationIntrospector$.MODULE$.findDefaultEnumValue(x$1, x$2);
   }

   public static void findEnumAliases(final MapperConfig x$1, final AnnotatedClass x$2, final Enum[] x$3, final String[][] x$4) {
      ScalaAnnotationIntrospector$.MODULE$.findEnumAliases(x$1, x$2, x$3, x$4);
   }

   /** @deprecated */
   @Deprecated
   public static void findEnumAliases(final Class x$1, final Enum[] x$2, final String[][] x$3) {
      ScalaAnnotationIntrospector$.MODULE$.findEnumAliases(x$1, x$2, x$3);
   }

   public static String[] findEnumValues(final MapperConfig x$1, final AnnotatedClass x$2, final Enum[] x$3, final String[] x$4) {
      return ScalaAnnotationIntrospector$.MODULE$.findEnumValues(x$1, x$2, x$3, x$4);
   }

   /** @deprecated */
   @Deprecated
   public static String[] findEnumValues(final Class x$1, final Enum[] x$2, final String[] x$3) {
      return ScalaAnnotationIntrospector$.MODULE$.findEnumValues(x$1, x$2, x$3);
   }

   public static Boolean hasAnyGetter(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.hasAnyGetter(x$1);
   }

   public static Boolean hasAsValue(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.hasAsValue(x$1);
   }

   public static Boolean hasAsKey(final MapperConfig x$1, final Annotated x$2) {
      return ScalaAnnotationIntrospector$.MODULE$.hasAsKey(x$1, x$2);
   }

   public static PropertyName findNameForSerialization(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findNameForSerialization(x$1);
   }

   public static void findAndAddVirtualProperties(final MapperConfig x$1, final AnnotatedClass x$2, final List x$3) {
      ScalaAnnotationIntrospector$.MODULE$.findAndAddVirtualProperties(x$1, x$2, x$3);
   }

   public static Boolean findSerializationSortAlphabetically(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findSerializationSortAlphabetically(x$1);
   }

   public static String[] findSerializationPropertyOrder(final AnnotatedClass x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findSerializationPropertyOrder(x$1);
   }

   public static JavaType refineSerializationType(final MapperConfig x$1, final Annotated x$2, final JavaType x$3) throws JsonMappingException {
      return ScalaAnnotationIntrospector$.MODULE$.refineSerializationType(x$1, x$2, x$3);
   }

   public static JsonInclude.Value findPropertyInclusion(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyInclusion(x$1);
   }

   public static Object findSerializationContentConverter(final AnnotatedMember x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findSerializationContentConverter(x$1);
   }

   public static Object findSerializationConverter(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findSerializationConverter(x$1);
   }

   public static JsonSerialize.Typing findSerializationTyping(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findSerializationTyping(x$1);
   }

   public static Object findNullSerializer(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findNullSerializer(x$1);
   }

   public static Object findContentSerializer(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findContentSerializer(x$1);
   }

   public static Object findKeySerializer(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findKeySerializer(x$1);
   }

   public static Object findSerializer(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findSerializer(x$1);
   }

   /** @deprecated */
   @Deprecated
   public static Object findInjectableValueId(final AnnotatedMember x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findInjectableValueId(x$1);
   }

   public static PropertyName findRenameByField(final MapperConfig x$1, final AnnotatedField x$2, final PropertyName x$3) {
      return ScalaAnnotationIntrospector$.MODULE$.findRenameByField(x$1, x$2, x$3);
   }

   public static AnnotatedMethod resolveSetterConflict(final MapperConfig x$1, final AnnotatedMethod x$2, final AnnotatedMethod x$3) {
      return ScalaAnnotationIntrospector$.MODULE$.resolveSetterConflict(x$1, x$2, x$3);
   }

   public static JsonProperty.Access findPropertyAccess(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyAccess(x$1);
   }

   public static List findPropertyAliases(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyAliases(x$1);
   }

   public static Integer findPropertyIndex(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyIndex(x$1);
   }

   public static String findPropertyDescription(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyDescription(x$1);
   }

   public static String findPropertyDefaultValue(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyDefaultValue(x$1);
   }

   public static PropertyName findWrapperName(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findWrapperName(x$1);
   }

   public static JsonFormat.Value findFormat(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findFormat(x$1);
   }

   public static Class[] findViews(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findViews(x$1);
   }

   public static Boolean hasRequiredMarker(final AnnotatedMember x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.hasRequiredMarker(x$1);
   }

   public static JacksonInject.Value findInjectableValue(final AnnotatedMember x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findInjectableValue(x$1);
   }

   public static NameTransformer findUnwrappingNameTransformer(final AnnotatedMember x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findUnwrappingNameTransformer(x$1);
   }

   public static AnnotationIntrospector.ReferenceProperty findReferenceType(final AnnotatedMember x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findReferenceType(x$1);
   }

   public static Boolean isTypeId(final AnnotatedMember x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.isTypeId(x$1);
   }

   public static String findTypeName(final AnnotatedClass x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findTypeName(x$1);
   }

   public static List findSubtypes(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findSubtypes(x$1);
   }

   public static TypeResolverBuilder findPropertyContentTypeResolver(final MapperConfig x$1, final AnnotatedMember x$2, final JavaType x$3) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyContentTypeResolver(x$1, x$2, x$3);
   }

   public static TypeResolverBuilder findPropertyTypeResolver(final MapperConfig x$1, final AnnotatedMember x$2, final JavaType x$3) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyTypeResolver(x$1, x$2, x$3);
   }

   public static TypeResolverBuilder findTypeResolver(final MapperConfig x$1, final AnnotatedClass x$2, final JavaType x$3) {
      return ScalaAnnotationIntrospector$.MODULE$.findTypeResolver(x$1, x$2, x$3);
   }

   public static JsonTypeInfo.Value findPolymorphicTypeInfo(final MapperConfig x$1, final Annotated x$2) {
      return ScalaAnnotationIntrospector$.MODULE$.findPolymorphicTypeInfo(x$1, x$2);
   }

   public static VisibilityChecker findAutoDetectVisibility(final AnnotatedClass x$1, final VisibilityChecker x$2) {
      return ScalaAnnotationIntrospector$.MODULE$.findAutoDetectVisibility(x$1, x$2);
   }

   /** @deprecated */
   @Deprecated
   public static JsonIgnoreProperties.Value findPropertyIgnorals(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyIgnorals(x$1);
   }

   public static String findClassDescription(final AnnotatedClass x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findClassDescription(x$1);
   }

   public static Object findEnumNamingStrategy(final MapperConfig x$1, final AnnotatedClass x$2) {
      return ScalaAnnotationIntrospector$.MODULE$.findEnumNamingStrategy(x$1, x$2);
   }

   public static Object findNamingStrategy(final AnnotatedClass x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findNamingStrategy(x$1);
   }

   public static Object findFilterId(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findFilterId(x$1);
   }

   public static JsonIncludeProperties.Value findPropertyInclusionByName(final MapperConfig x$1, final Annotated x$2) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyInclusionByName(x$1, x$2);
   }

   public static JsonIgnoreProperties.Value findPropertyIgnoralByName(final MapperConfig x$1, final Annotated x$2) {
      return ScalaAnnotationIntrospector$.MODULE$.findPropertyIgnoralByName(x$1, x$2);
   }

   public static Boolean isIgnorableType(final AnnotatedClass x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.isIgnorableType(x$1);
   }

   public static PropertyName findRootName(final AnnotatedClass x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findRootName(x$1);
   }

   public static ObjectIdInfo findObjectReferenceInfo(final Annotated x$1, final ObjectIdInfo x$2) {
      return ScalaAnnotationIntrospector$.MODULE$.findObjectReferenceInfo(x$1, x$2);
   }

   public static ObjectIdInfo findObjectIdInfo(final Annotated x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.findObjectIdInfo(x$1);
   }

   public static boolean isAnnotationBundle(final Annotation x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.isAnnotationBundle(x$1);
   }

   public static Collection allIntrospectors(final Collection x$1) {
      return ScalaAnnotationIntrospector$.MODULE$.allIntrospectors(x$1);
   }

   public static Collection allIntrospectors() {
      return ScalaAnnotationIntrospector$.MODULE$.allIntrospectors();
   }

   private static class ScalaValueInstantiator extends StdValueInstantiator {
      private final BeanDescriptor descriptor;
      private final SettableBeanProperty[] overriddenConstructorArguments;

      private SettableBeanProperty[] overriddenConstructorArguments() {
         return this.overriddenConstructorArguments;
      }

      public SettableBeanProperty[] getFromObjectArguments(final DeserializationConfig config) {
         return this.overriddenConstructorArguments();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$overriddenConstructorArguments$5(final CreatorProperty x2$3, final ConstructorParameter x$8) {
         return x$8.index() == x2$3.getCreatorIndex();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$overriddenConstructorArguments$4(final CreatorProperty x2$3, final PropertyDescriptor x$7) {
         return x$7.param().exists((x$8) -> BoxesRunTime.boxToBoolean($anonfun$overriddenConstructorArguments$5(x2$3, x$8)));
      }

      public ScalaValueInstantiator(final ScalaAnnotationIntrospectorModule scalaAnnotationIntrospectorModule, final StdValueInstantiator delegate, final DeserializationConfig config, final BeanDescriptor descriptor) {
         SettableBeanProperty[] var10001;
         label22: {
            this.descriptor = descriptor;
            super(delegate);
            Map overrides = (Map)scalaAnnotationIntrospectorModule.overrideMap().get(descriptor.beanType().getName()).map((x$6) -> x$6.overrides().toMap(.MODULE$.refl())).getOrElse(() -> scala.Predef..MODULE$.Map().empty());
            boolean applyDefaultValues = config.isEnabled(MapperFeature.APPLY_DEFAULT_VALUES);
            SettableBeanProperty[] args = delegate.getFromObjectArguments(config);
            boolean var9 = false;
            Some var10 = null;
            Option var11 = scala.Option..MODULE$.apply(args);
            if (var11 instanceof Some) {
               var9 = true;
               var10 = (Some)var11;
               SettableBeanProperty[] array = (SettableBeanProperty[])var10.value();
               if (applyDefaultValues || overrides.nonEmpty()) {
                  var10001 = (SettableBeanProperty[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])array), (x0$1) -> {
                     if (x0$1 instanceof CreatorProperty) {
                        CreatorProperty var9 = (CreatorProperty)x0$1;
                        Option var10 = this.descriptor.properties().find((x$7) -> BoxesRunTime.boxToBoolean($anonfun$overriddenConstructorArguments$4(var9, x$7)));
                        if (var10 instanceof Some) {
                           Some var11 = (Some)var10;
                           PropertyDescriptor pd = (PropertyDescriptor)var11.value();
                           if (applyDefaultValues) {
                              if (pd != null) {
                                 Option var14 = pd.param();
                                 if (var14 instanceof Some) {
                                    Some var15 = (Some)var14;
                                    ConstructorParameter var16 = (ConstructorParameter)var15.value();
                                    if (var16 != null) {
                                       Option var17 = var16.defaultValue();
                                       if (var17 instanceof Some) {
                                          Some var18 = (Some)var17;
                                          Function0 defaultValue = (Function0)var18.value();
                                          SettableBeanProperty updatedCreator = var9.withNullProvider(new NullValueProvider(defaultValue) {
                                             private final Function0 defaultValue$1;

                                             public Object getAbsentValue(final DeserializationContext x$1) throws JsonMappingException {
                                                return super.getAbsentValue(x$1);
                                             }

                                             public Object getNullValue(final DeserializationContext ctxt) {
                                                return this.defaultValue$1.apply();
                                             }

                                             public AccessPattern getNullAccessPattern() {
                                                return AccessPattern.DYNAMIC;
                                             }

                                             public {
                                                this.defaultValue$1 = defaultValue$1;
                                             }
                                          });
                                          if (updatedCreator instanceof CreatorProperty) {
                                             CreatorProperty var22 = (CreatorProperty)updatedCreator;
                                             return ScalaAnnotationIntrospector$.MODULE$.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospector$$applyOverrides(var22, pd.name(), overrides);
                                          }

                                          return updatedCreator;
                                       }
                                    }
                                 }
                              }

                              return ScalaAnnotationIntrospector$.MODULE$.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospector$$applyOverrides(var9, pd.name(), overrides);
                           } else {
                              return ScalaAnnotationIntrospector$.MODULE$.com$fasterxml$jackson$module$scala$introspect$ScalaAnnotationIntrospector$$applyOverrides(var9, pd.name(), overrides);
                           }
                        } else {
                           return var9;
                        }
                     } else {
                        throw new MatchError(x0$1);
                     }
                  }, scala.reflect.ClassTag..MODULE$.apply(SettableBeanProperty.class));
                  break label22;
               }
            }

            if (var9) {
               SettableBeanProperty[] array = (SettableBeanProperty[])var10.value();
               var10001 = array;
            } else {
               var10001 = (SettableBeanProperty[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(SettableBeanProperty.class));
            }
         }

         this.overriddenConstructorArguments = var10001;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
