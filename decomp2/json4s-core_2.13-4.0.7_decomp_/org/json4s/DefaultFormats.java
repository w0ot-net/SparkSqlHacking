package org.json4s;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.json4s.prefs.EmptyValueStrategy;
import org.json4s.prefs.ExtractionNullStrategy;
import org.json4s.reflect.ParameterNameReader;
import org.json4s.reflect.ParanamerReader$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.collection.Iterable;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uv!\u0002\u000f\u001e\u0011\u0003\u0011c!\u0002\u0013\u001e\u0011\u0003)\u0003bBAH\u0003\u0011\u0005\u0011\u0011\u0013\u0005\n\u0003'\u000b!\u0019!C\u0001\u0003+C\u0001\"a)\u0002A\u0003%\u0011q\u0013\u0005\t\u0003K\u000b!\u0019!C\u0005o!9\u0011qU\u0001!\u0002\u0013A\u0004\"CAU\u0003\u0005\u0005I\u0011BAV\r\u001d!S\u0004%A\u0002\u00025BQ!\r\u0005\u0005\u0002IBqA\u000e\u0005CB\u0013%q\u0007C\u0004D\u0011\t\u0007I\u0011\t#\t\u000f-C!\u0019!C!\u0019\"9\u0001\u000b\u0003b\u0001\n\u0003\n\u0006bB7\t\u0005\u0004%\tE\u001c\u0005\bo\"\u0011\r\u0011\"\u0011y\u0011%\t\t\u0003\u0003b\u0001\n\u0003\n\u0019\u0003C\u0005\u0002,!\u0011\r\u0011\"\u0011\u0002$!I\u0011Q\u0006\u0005C\u0002\u0013\u0005\u0013q\u0006\u0005\n\u0003\u000bB!\u0019!C!\u0003\u000fB\u0011\"a\u0016\t\u0005\u0004%\t%a\t\t\u0013\u0005e\u0003B1A\u0005B\u0005m\u0003\"CA5\u0011\t\u0007I\u0011IA6\u0011\u001d\t\u0019\b\u0003C!\u0003GA\u0011\"!\u001e\t\u0005\u0004%\t!a\u001e\t\u000f\u0005}\u0004\u0002\"\u0005\u0002\u0002\"9\u00111\u0011\u0005\u0005\u0002\u0005\u0015\u0005bBAD\u0011\u0011\u0005\u0011\u0011R\u0001\u000f\t\u00164\u0017-\u001e7u\r>\u0014X.\u0019;t\u0015\tqr$\u0001\u0004kg>tGg\u001d\u0006\u0002A\u0005\u0019qN]4\u0004\u0001A\u00111%A\u0007\u0002;\tqA)\u001a4bk2$hi\u001c:nCR\u001c8cA\u0001'YA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t1\u0011I\\=SK\u001a\u0004\"a\t\u0005\u0014\u0007!1c\u0006\u0005\u0002$_%\u0011\u0001'\b\u0002\b\r>\u0014X.\u0019;t\u0003\u0019!\u0013N\\5uIQ\t1\u0007\u0005\u0002(i%\u0011Q\u0007\u000b\u0002\u0005+:LG/\u0001\u0002eMV\t\u0001\bE\u0002$smJ!AO\u000f\u0003\u0017QC'/Z1e\u0019>\u001c\u0017\r\u001c\t\u0003y\u0005k\u0011!\u0010\u0006\u0003}}\nA\u0001^3yi*\t\u0001)\u0001\u0003kCZ\f\u0017B\u0001\">\u0005A\u0019\u0016.\u001c9mK\u0012\u000bG/\u001a$pe6\fG/A\nqCJ\fW.\u001a;fe:\u000bW.\u001a*fC\u0012,'/F\u0001F!\t1\u0015*D\u0001H\u0015\tAU$A\u0004sK\u001adWm\u0019;\n\u0005);%a\u0005)be\u0006lW\r^3s\u001d\u0006lWMU3bI\u0016\u0014\u0018!\u0003;za\u0016D\u0015N\u001c;t+\u0005i\u0005CA\u0012O\u0013\tyUDA\u0005UsB,\u0007*\u001b8ug\u0006\t2-^:u_6\u001cVM]5bY&TXM]:\u0016\u0003I\u00032aU._\u001d\t!\u0016L\u0004\u0002V16\taK\u0003\u0002XC\u00051AH]8pizJ\u0011!K\u0005\u00035\"\nq\u0001]1dW\u0006<W-\u0003\u0002];\n!A*[:u\u0015\tQ\u0006\u0006\r\u0002`IB\u00191\u0005\u00192\n\u0005\u0005l\"AC*fe&\fG.\u001b>feB\u00111\r\u001a\u0007\u0001\t%)W\"!A\u0001\u0002\u000b\u0005aMA\u0002`IE\n\"a\u001a6\u0011\u0005\u001dB\u0017BA5)\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aJ6\n\u00051D#aA!os\u0006!2-^:u_6\\U-_*fe&\fG.\u001b>feN,\u0012a\u001c\t\u0004'n\u0003\bGA9v!\r\u0019#\u000f^\u0005\u0003gv\u0011QbS3z'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bCA2v\t%1h\"!A\u0001\u0002\u000b\u0005aMA\u0002`II\n\u0001CZ5fY\u0012\u001cVM]5bY&TXM]:\u0016\u0003e\u00042aU.{!\u0015930`A\n\u0013\ta\bF\u0001\u0004UkBdWM\r\u0019\u0004}\u0006=\u0001#B@\u0002\b\u00055a\u0002BA\u0001\u0003\u0007\u0001\"!\u0016\u0015\n\u0007\u0005\u0015\u0001&\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u0013\tYAA\u0003DY\u0006\u001c8OC\u0002\u0002\u0006!\u00022aYA\b\t)\t\tbDA\u0001\u0002\u0003\u0015\tA\u001a\u0002\u0004?\u0012\u001a\u0004\u0007BA\u000b\u0003;\u0001RaIA\f\u00037I1!!\u0007\u001e\u0005=1\u0015.\u001a7e'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bcA2\u0002\u001e\u0011Q\u0011qD\b\u0002\u0002\u0003\u0005)\u0011\u00014\u0003\u0007}#C'A\u0006xC:$8OQ5h\u0013:$XCAA\u0013!\r9\u0013qE\u0005\u0004\u0003SA#a\u0002\"p_2,\u0017M\\\u0001\u0010o\u0006tGo\u001d\"jO\u0012+7-[7bY\u0006Q\u0001O]5nSRLg/Z:\u0016\u0005\u0005E\u0002#B@\u00024\u0005]\u0012\u0002BA\u001b\u0003\u0017\u00111aU3u!\u0011\tI$!\u0011\u000e\u0005\u0005m\"b\u0001%\u0002>)\u0019\u0011qH \u0002\t1\fgnZ\u0005\u0005\u0003\u0007\nYD\u0001\u0003UsB,\u0017AC2p[B\fg.[8ogV\u0011\u0011\u0011\n\t\u0005'n\u000bY\u0005E\u0003(w\u00065c\u0005\r\u0003\u0002P\u0005M\u0003#B@\u0002\b\u0005E\u0003cA2\u0002T\u0011Q\u0011QK\n\u0002\u0002\u0003\u0005)\u0011\u00014\u0003\u0007}#S'A\ntiJL7\r^(qi&|g\u000eU1sg&tw-\u0001\nf[B$\u0018PV1mk\u0016\u001cFO]1uK\u001eLXCAA/!\u0011\ty&!\u001a\u000e\u0005\u0005\u0005$bAA2;\u0005)\u0001O]3gg&!\u0011qMA1\u0005I)U\u000e\u001d;z-\u0006dW/Z*ue\u0006$XmZ=\u0002-\u0015DHO]1di&|gNT;mYN#(/\u0019;fOf,\"!!\u001c\u0011\t\u0005}\u0013qN\u0005\u0005\u0003c\n\tG\u0001\fFqR\u0014\u0018m\u0019;j_:tU\u000f\u001c7TiJ\fG/Z4z\u0003i\u0019HO]5di\u001aKW\r\u001c3EKN,'/[1mSj\fG/[8o\u0003)!\u0017\r^3G_Jl\u0017\r^\u000b\u0003\u0003s\u00022aIA>\u0013\r\ti(\b\u0002\u000b\t\u0006$XMR8s[\u0006$\u0018!\u00043bi\u00164uN]7biR,'/F\u0001<\u0003!awn]:mKN\u001cX#\u0001\u0018\u0002\u0013]LG\u000f\u001b%j]R\u001cHc\u0001\u0018\u0002\f\"1\u0011QR\u000eA\u00025\u000bQ\u0001[5oiN\fa\u0001P5oSRtD#\u0001\u0012\u0002\u0007U#6)\u0006\u0002\u0002\u0018B!\u0011\u0011TAP\u001b\t\tYJC\u0002\u0002\u001e~\nA!\u001e;jY&!\u0011\u0011UAN\u0005!!\u0016.\\3[_:,\u0017\u0001B+U\u0007\u0002\nA\u0002\\8tg2,7o\u001d#bi\u0016\fQ\u0002\\8tg2,7o\u001d#bi\u0016\u0004\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAAW!\u0011\ty+!-\u000e\u0005\u0005u\u0012\u0002BAZ\u0003{\u0011aa\u00142kK\u000e$\b"
)
public interface DefaultFormats extends Formats {
   static TimeZone UTC() {
      return DefaultFormats$.MODULE$.UTC();
   }

   void org$json4s$DefaultFormats$_setter_$org$json4s$DefaultFormats$$df_$eq(final ThreadLocal x$1);

   void org$json4s$DefaultFormats$_setter_$parameterNameReader_$eq(final ParameterNameReader x$1);

   void org$json4s$DefaultFormats$_setter_$typeHints_$eq(final TypeHints x$1);

   void org$json4s$DefaultFormats$_setter_$customSerializers_$eq(final List x$1);

   void org$json4s$DefaultFormats$_setter_$customKeySerializers_$eq(final List x$1);

   void org$json4s$DefaultFormats$_setter_$fieldSerializers_$eq(final List x$1);

   void org$json4s$DefaultFormats$_setter_$wantsBigInt_$eq(final boolean x$1);

   void org$json4s$DefaultFormats$_setter_$wantsBigDecimal_$eq(final boolean x$1);

   void org$json4s$DefaultFormats$_setter_$primitives_$eq(final Set x$1);

   void org$json4s$DefaultFormats$_setter_$companions_$eq(final List x$1);

   void org$json4s$DefaultFormats$_setter_$strictOptionParsing_$eq(final boolean x$1);

   void org$json4s$DefaultFormats$_setter_$emptyValueStrategy_$eq(final EmptyValueStrategy x$1);

   void org$json4s$DefaultFormats$_setter_$extractionNullStrategy_$eq(final ExtractionNullStrategy x$1);

   void org$json4s$DefaultFormats$_setter_$dateFormat_$eq(final DateFormat x$1);

   ThreadLocal org$json4s$DefaultFormats$$df();

   ParameterNameReader parameterNameReader();

   TypeHints typeHints();

   List customSerializers();

   List customKeySerializers();

   List fieldSerializers();

   boolean wantsBigInt();

   boolean wantsBigDecimal();

   Set primitives();

   List companions();

   boolean strictOptionParsing();

   EmptyValueStrategy emptyValueStrategy();

   ExtractionNullStrategy extractionNullStrategy();

   default boolean strictFieldDeserialization() {
      return false;
   }

   DateFormat dateFormat();

   default SimpleDateFormat dateFormatter() {
      SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
      f.setTimeZone(DefaultFormats$.MODULE$.UTC());
      return f;
   }

   default Formats lossless() {
      return new DefaultFormats() {
         private ThreadLocal org$json4s$DefaultFormats$$df;
         private ParameterNameReader parameterNameReader;
         private TypeHints typeHints;
         private List customSerializers;
         private List customKeySerializers;
         private List fieldSerializers;
         private boolean wantsBigInt;
         private boolean wantsBigDecimal;
         private Set primitives;
         private List companions;
         private boolean strictOptionParsing;
         private EmptyValueStrategy emptyValueStrategy;
         private ExtractionNullStrategy extractionNullStrategy;
         private DateFormat dateFormat;

         public boolean strictFieldDeserialization() {
            return DefaultFormats.super.strictFieldDeserialization();
         }

         public Formats lossless() {
            return DefaultFormats.super.lossless();
         }

         public Formats withHints(final TypeHints hints) {
            return DefaultFormats.super.withHints(hints);
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
            return this.org$json4s$DefaultFormats$$df;
         }

         public ParameterNameReader parameterNameReader() {
            return this.parameterNameReader;
         }

         public TypeHints typeHints() {
            return this.typeHints;
         }

         public List customSerializers() {
            return this.customSerializers;
         }

         public List customKeySerializers() {
            return this.customKeySerializers;
         }

         public List fieldSerializers() {
            return this.fieldSerializers;
         }

         public boolean wantsBigInt() {
            return this.wantsBigInt;
         }

         public boolean wantsBigDecimal() {
            return this.wantsBigDecimal;
         }

         public Set primitives() {
            return this.primitives;
         }

         public List companions() {
            return this.companions;
         }

         public boolean strictOptionParsing() {
            return this.strictOptionParsing;
         }

         public EmptyValueStrategy emptyValueStrategy() {
            return this.emptyValueStrategy;
         }

         public ExtractionNullStrategy extractionNullStrategy() {
            return this.extractionNullStrategy;
         }

         public DateFormat dateFormat() {
            return this.dateFormat;
         }

         public final void org$json4s$DefaultFormats$_setter_$org$json4s$DefaultFormats$$df_$eq(final ThreadLocal x$1) {
            this.org$json4s$DefaultFormats$$df = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$parameterNameReader_$eq(final ParameterNameReader x$1) {
            this.parameterNameReader = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$typeHints_$eq(final TypeHints x$1) {
            this.typeHints = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$customSerializers_$eq(final List x$1) {
            this.customSerializers = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$customKeySerializers_$eq(final List x$1) {
            this.customKeySerializers = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$fieldSerializers_$eq(final List x$1) {
            this.fieldSerializers = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$wantsBigInt_$eq(final boolean x$1) {
            this.wantsBigInt = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$wantsBigDecimal_$eq(final boolean x$1) {
            this.wantsBigDecimal = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$primitives_$eq(final Set x$1) {
            this.primitives = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$companions_$eq(final List x$1) {
            this.companions = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$strictOptionParsing_$eq(final boolean x$1) {
            this.strictOptionParsing = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$emptyValueStrategy_$eq(final EmptyValueStrategy x$1) {
            this.emptyValueStrategy = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$extractionNullStrategy_$eq(final ExtractionNullStrategy x$1) {
            this.extractionNullStrategy = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$dateFormat_$eq(final DateFormat x$1) {
            this.dateFormat = x$1;
         }

         public SimpleDateFormat dateFormatter() {
            return (SimpleDateFormat)DefaultFormats$.MODULE$.org$json4s$DefaultFormats$$losslessDate().apply();
         }

         public {
            Formats.$init$(this);
            DefaultFormats.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   default Formats withHints(final TypeHints hints) {
      return new DefaultFormats(hints) {
         private final TypeHints typeHints;
         private ThreadLocal org$json4s$DefaultFormats$$df;
         private ParameterNameReader parameterNameReader;
         private List customSerializers;
         private List customKeySerializers;
         private List fieldSerializers;
         private boolean wantsBigInt;
         private boolean wantsBigDecimal;
         private Set primitives;
         private List companions;
         private boolean strictOptionParsing;
         private EmptyValueStrategy emptyValueStrategy;
         private ExtractionNullStrategy extractionNullStrategy;
         private DateFormat dateFormat;

         public boolean strictFieldDeserialization() {
            return DefaultFormats.super.strictFieldDeserialization();
         }

         public SimpleDateFormat dateFormatter() {
            return DefaultFormats.super.dateFormatter();
         }

         public Formats lossless() {
            return DefaultFormats.super.lossless();
         }

         public Formats withHints(final TypeHints hints) {
            return DefaultFormats.super.withHints(hints);
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
            return this.org$json4s$DefaultFormats$$df;
         }

         public ParameterNameReader parameterNameReader() {
            return this.parameterNameReader;
         }

         public List customSerializers() {
            return this.customSerializers;
         }

         public List customKeySerializers() {
            return this.customKeySerializers;
         }

         public List fieldSerializers() {
            return this.fieldSerializers;
         }

         public boolean wantsBigInt() {
            return this.wantsBigInt;
         }

         public boolean wantsBigDecimal() {
            return this.wantsBigDecimal;
         }

         public Set primitives() {
            return this.primitives;
         }

         public List companions() {
            return this.companions;
         }

         public boolean strictOptionParsing() {
            return this.strictOptionParsing;
         }

         public EmptyValueStrategy emptyValueStrategy() {
            return this.emptyValueStrategy;
         }

         public ExtractionNullStrategy extractionNullStrategy() {
            return this.extractionNullStrategy;
         }

         public DateFormat dateFormat() {
            return this.dateFormat;
         }

         public final void org$json4s$DefaultFormats$_setter_$org$json4s$DefaultFormats$$df_$eq(final ThreadLocal x$1) {
            this.org$json4s$DefaultFormats$$df = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$parameterNameReader_$eq(final ParameterNameReader x$1) {
            this.parameterNameReader = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$typeHints_$eq(final TypeHints x$1) {
         }

         public void org$json4s$DefaultFormats$_setter_$customSerializers_$eq(final List x$1) {
            this.customSerializers = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$customKeySerializers_$eq(final List x$1) {
            this.customKeySerializers = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$fieldSerializers_$eq(final List x$1) {
            this.fieldSerializers = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$wantsBigInt_$eq(final boolean x$1) {
            this.wantsBigInt = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$wantsBigDecimal_$eq(final boolean x$1) {
            this.wantsBigDecimal = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$primitives_$eq(final Set x$1) {
            this.primitives = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$companions_$eq(final List x$1) {
            this.companions = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$strictOptionParsing_$eq(final boolean x$1) {
            this.strictOptionParsing = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$emptyValueStrategy_$eq(final EmptyValueStrategy x$1) {
            this.emptyValueStrategy = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$extractionNullStrategy_$eq(final ExtractionNullStrategy x$1) {
            this.extractionNullStrategy = x$1;
         }

         public void org$json4s$DefaultFormats$_setter_$dateFormat_$eq(final DateFormat x$1) {
            this.dateFormat = x$1;
         }

         public TypeHints typeHints() {
            return this.typeHints;
         }

         public {
            Formats.$init$(this);
            DefaultFormats.$init$(this);
            this.typeHints = hints$1;
            Statics.releaseFence();
         }
      };
   }

   static void $init$(final DefaultFormats $this) {
      $this.org$json4s$DefaultFormats$_setter_$org$json4s$DefaultFormats$$df_$eq(new ThreadLocal(() -> $this.dateFormatter()));
      $this.org$json4s$DefaultFormats$_setter_$parameterNameReader_$eq(ParanamerReader$.MODULE$);
      $this.org$json4s$DefaultFormats$_setter_$typeHints_$eq(NoTypeHints$.MODULE$);
      $this.org$json4s$DefaultFormats$_setter_$customSerializers_$eq(.MODULE$.Nil());
      $this.org$json4s$DefaultFormats$_setter_$customKeySerializers_$eq(.MODULE$.Nil());
      $this.org$json4s$DefaultFormats$_setter_$fieldSerializers_$eq(.MODULE$.Nil());
      $this.org$json4s$DefaultFormats$_setter_$wantsBigInt_$eq(true);
      $this.org$json4s$DefaultFormats$_setter_$wantsBigDecimal_$eq(false);
      $this.org$json4s$DefaultFormats$_setter_$primitives_$eq((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Type[]{JValue.class, JObject.class, JArray.class}))));
      $this.org$json4s$DefaultFormats$_setter_$companions_$eq(.MODULE$.Nil());
      $this.org$json4s$DefaultFormats$_setter_$strictOptionParsing_$eq(false);
      $this.org$json4s$DefaultFormats$_setter_$emptyValueStrategy_$eq(org.json4s.prefs.EmptyValueStrategy..MODULE$.default());
      $this.org$json4s$DefaultFormats$_setter_$extractionNullStrategy_$eq(ExtractionNullStrategy.Keep$.MODULE$);
      $this.org$json4s$DefaultFormats$_setter_$dateFormat_$eq(new DateFormat() {
         // $FF: synthetic field
         private final DefaultFormats $outer;

         public Option parse(final String s) {
            Object var10000;
            try {
               var10000 = new Some(this.formatter().parse(s));
            } catch (ParseException var2) {
               var10000 = scala.None..MODULE$;
            }

            return (Option)var10000;
         }

         public String format(final Date d) {
            return this.formatter().format(d);
         }

         public TimeZone timezone() {
            return this.formatter().getTimeZone();
         }

         private SimpleDateFormat formatter() {
            return (SimpleDateFormat)this.$outer.org$json4s$DefaultFormats$$df().apply();
         }

         public {
            if (DefaultFormats.this == null) {
               throw null;
            } else {
               this.$outer = DefaultFormats.this;
            }
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
