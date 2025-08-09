package org.json4s;

import java.lang.invoke.SerializedLambda;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import org.json4s.prefs.EmptyValueStrategy;
import org.json4s.prefs.ExtractionNullStrategy;
import org.json4s.reflect.ParameterNameReader;
import scala.Option;
import scala.PartialFunction;
import scala.collection.Iterable;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.ModuleSerializationProxy;

public final class DefaultFormats$ implements DefaultFormats {
   public static final DefaultFormats$ MODULE$ = new DefaultFormats$();
   private static final TimeZone UTC;
   private static final ThreadLocal org$json4s$DefaultFormats$$losslessDate;
   private static ThreadLocal org$json4s$DefaultFormats$$df;
   private static ParameterNameReader parameterNameReader;
   private static TypeHints typeHints;
   private static List customSerializers;
   private static List customKeySerializers;
   private static List fieldSerializers;
   private static boolean wantsBigInt;
   private static boolean wantsBigDecimal;
   private static Set primitives;
   private static List companions;
   private static boolean strictOptionParsing;
   private static EmptyValueStrategy emptyValueStrategy;
   private static ExtractionNullStrategy extractionNullStrategy;
   private static DateFormat dateFormat;

   static {
      Formats.$init$(MODULE$);
      DefaultFormats.$init$(MODULE$);
      UTC = TimeZone.getTimeZone("UTC");
      org$json4s$DefaultFormats$$losslessDate = new ThreadLocal(() -> $this.createSdf$1());
   }

   public boolean strictFieldDeserialization() {
      return DefaultFormats.strictFieldDeserialization$(this);
   }

   public SimpleDateFormat dateFormatter() {
      return DefaultFormats.dateFormatter$(this);
   }

   public Formats lossless() {
      return DefaultFormats.lossless$(this);
   }

   public Formats withHints(final TypeHints hints) {
      return DefaultFormats.withHints$(this, hints);
   }

   public List richSerializers() {
      return Formats.richSerializers$(this);
   }

   public boolean strictArrayExtraction() {
      return Formats.strictArrayExtraction$(this);
   }

   public boolean strictMapExtraction() {
      return Formats.strictMapExtraction$(this);
   }

   public boolean alwaysEscapeUnicode() {
      return Formats.alwaysEscapeUnicode$(this);
   }

   public boolean considerCompanionConstructors() {
      return Formats.considerCompanionConstructors$(this);
   }

   public Formats withBigInt() {
      return Formats.withBigInt$(this);
   }

   public Formats withLong() {
      return Formats.withLong$(this);
   }

   public Formats withBigDecimal() {
      return Formats.withBigDecimal$(this);
   }

   public Formats withDouble() {
      return Formats.withDouble$(this);
   }

   public Formats withCompanions(final Seq comps) {
      return Formats.withCompanions$(this, comps);
   }

   public Formats preservingEmptyValues() {
      return Formats.preservingEmptyValues$(this);
   }

   public Formats skippingEmptyValues() {
      return Formats.skippingEmptyValues$(this);
   }

   public Formats withEmptyValueStrategy(final EmptyValueStrategy strategy) {
      return Formats.withEmptyValueStrategy$(this, strategy);
   }

   public Formats withEscapeUnicode() {
      return Formats.withEscapeUnicode$(this);
   }

   public Formats withStrictOptionParsing() {
      return Formats.withStrictOptionParsing$(this);
   }

   public Formats withStrictArrayExtraction() {
      return Formats.withStrictArrayExtraction$(this);
   }

   public Formats withStrictMapExtraction() {
      return Formats.withStrictMapExtraction$(this);
   }

   public Formats withPre36DeserializationBehavior() {
      return Formats.withPre36DeserializationBehavior$(this);
   }

   public Formats strict() {
      return Formats.strict$(this);
   }

   public Formats nonStrict() {
      return Formats.nonStrict$(this);
   }

   /** @deprecated */
   public Formats disallowNull() {
      return Formats.disallowNull$(this);
   }

   public Formats withExtractionNullStrategy(final ExtractionNullStrategy strategy) {
      return Formats.withExtractionNullStrategy$(this, strategy);
   }

   public Formats withStrictFieldDeserialization() {
      return Formats.withStrictFieldDeserialization$(this);
   }

   public Formats $plus(final TypeHints extraHints) {
      return Formats.$plus$(this, (TypeHints)extraHints);
   }

   public Formats $plus(final RichSerializer newSerializer) {
      return Formats.$plus$(this, (RichSerializer)newSerializer);
   }

   public Formats $plus(final Serializer newSerializer) {
      return Formats.$plus$(this, (Serializer)newSerializer);
   }

   public Formats $plus(final KeySerializer newSerializer) {
      return Formats.$plus$(this, (KeySerializer)newSerializer);
   }

   public Formats $plus$plus(final Iterable newSerializers) {
      return Formats.$plus$plus$(this, newSerializers);
   }

   public Formats $minus(final Serializer serializer) {
      return Formats.$minus$(this, serializer);
   }

   public Formats addKeySerializers(final Iterable newKeySerializers) {
      return Formats.addKeySerializers$(this, newKeySerializers);
   }

   public Formats $plus(final FieldSerializer newSerializer) {
      return Formats.$plus$(this, (FieldSerializer)newSerializer);
   }

   public Option fieldSerializer(final Class clazz) {
      return Formats.fieldSerializer$(this, clazz);
   }

   /** @deprecated */
   public PartialFunction customSerializer(final Formats format) {
      return Formats.customSerializer$(this, format);
   }

   /** @deprecated */
   public PartialFunction customDeserializer(final Formats format) {
      return Formats.customDeserializer$(this, format);
   }

   /** @deprecated */
   public PartialFunction customKeySerializer(final Formats format) {
      return Formats.customKeySerializer$(this, format);
   }

   /** @deprecated */
   public PartialFunction customKeyDeserializer(final Formats format) {
      return Formats.customKeyDeserializer$(this, format);
   }

   public ThreadLocal org$json4s$DefaultFormats$$df() {
      return org$json4s$DefaultFormats$$df;
   }

   public ParameterNameReader parameterNameReader() {
      return parameterNameReader;
   }

   public TypeHints typeHints() {
      return typeHints;
   }

   public List customSerializers() {
      return customSerializers;
   }

   public List customKeySerializers() {
      return customKeySerializers;
   }

   public List fieldSerializers() {
      return fieldSerializers;
   }

   public boolean wantsBigInt() {
      return wantsBigInt;
   }

   public boolean wantsBigDecimal() {
      return wantsBigDecimal;
   }

   public Set primitives() {
      return primitives;
   }

   public List companions() {
      return companions;
   }

   public boolean strictOptionParsing() {
      return strictOptionParsing;
   }

   public EmptyValueStrategy emptyValueStrategy() {
      return emptyValueStrategy;
   }

   public ExtractionNullStrategy extractionNullStrategy() {
      return extractionNullStrategy;
   }

   public DateFormat dateFormat() {
      return dateFormat;
   }

   public final void org$json4s$DefaultFormats$_setter_$org$json4s$DefaultFormats$$df_$eq(final ThreadLocal x$1) {
      org$json4s$DefaultFormats$$df = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$parameterNameReader_$eq(final ParameterNameReader x$1) {
      parameterNameReader = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$typeHints_$eq(final TypeHints x$1) {
      typeHints = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$customSerializers_$eq(final List x$1) {
      customSerializers = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$customKeySerializers_$eq(final List x$1) {
      customKeySerializers = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$fieldSerializers_$eq(final List x$1) {
      fieldSerializers = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$wantsBigInt_$eq(final boolean x$1) {
      wantsBigInt = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$wantsBigDecimal_$eq(final boolean x$1) {
      wantsBigDecimal = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$primitives_$eq(final Set x$1) {
      primitives = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$companions_$eq(final List x$1) {
      companions = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$strictOptionParsing_$eq(final boolean x$1) {
      strictOptionParsing = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$emptyValueStrategy_$eq(final EmptyValueStrategy x$1) {
      emptyValueStrategy = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$extractionNullStrategy_$eq(final ExtractionNullStrategy x$1) {
      extractionNullStrategy = x$1;
   }

   public void org$json4s$DefaultFormats$_setter_$dateFormat_$eq(final DateFormat x$1) {
      dateFormat = x$1;
   }

   public TimeZone UTC() {
      return UTC;
   }

   public ThreadLocal org$json4s$DefaultFormats$$losslessDate() {
      return org$json4s$DefaultFormats$$losslessDate;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DefaultFormats$.class);
   }

   private final SimpleDateFormat createSdf$1() {
      SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      f.setTimeZone(this.UTC());
      return f;
   }

   private DefaultFormats$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
