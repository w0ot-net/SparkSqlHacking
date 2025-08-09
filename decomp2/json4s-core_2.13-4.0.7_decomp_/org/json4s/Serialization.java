package org.json4s;

import java.io.Writer;
import org.json4s.prefs.EmptyValueStrategy;
import org.json4s.prefs.ExtractionNullStrategy;
import org.json4s.reflect.ParameterNameReader;
import scala.Option;
import scala.PartialFunction;
import scala.collection.Iterable;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%baB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006-\u0001!\ta\u0006\u0005\u00067\u00011\t\u0001\b\u0005\u00067\u00011\tA\u000f\u0005\u0006\u001f\u00021\t\u0001\u0015\u0005\u0006\u001f\u00021\ta\u0016\u0005\u0006C\u0002!\tA\u0019\u0005\u0006C\u00021\tA\u001e\u0005\u0007W\u0001!\t!!\u0002\u0003\u001bM+'/[1mSj\fG/[8o\u0015\tYA\"\u0001\u0004kg>tGg\u001d\u0006\u0002\u001b\u0005\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\u0005\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005A\u0002CA\t\u001a\u0013\tQ\"C\u0001\u0003V]&$\u0018!B<sSR,WCA\u000f5)\tq\u0002\u0007\u0006\u0002 UA\u0011\u0001e\n\b\u0003C\u0015\u0002\"A\t\n\u000e\u0003\rR!\u0001\n\b\u0002\rq\u0012xn\u001c;?\u0013\t1##\u0001\u0004Qe\u0016$WMZ\u0005\u0003Q%\u0012aa\u0015;sS:<'B\u0001\u0014\u0013\u0011\u0015Y#\u0001q\u0001-\u0003\u001d1wN]7biN\u0004\"!\f\u0018\u000e\u0003)I!a\f\u0006\u0003\u000f\u0019{'/\\1ug\")\u0011G\u0001a\u0001e\u0005\t\u0011\r\u0005\u00024i1\u0001A!B\u001b\u0003\u0005\u00041$!A!\u0012\u0005]\u0002\u0002CA\t9\u0013\tI$CA\u0004O_RD\u0017N\\4\u0016\u0007mbe\bF\u0002=\u00156#\"!P%\u0011\u0005MrD!B \u0004\u0005\u0004\u0001%!A,\u0012\u0005]\n\u0005C\u0001\"H\u001b\u0005\u0019%B\u0001#F\u0003\tIwNC\u0001G\u0003\u0011Q\u0017M^1\n\u0005!\u001b%AB,sSR,'\u000fC\u0003,\u0007\u0001\u000fA\u0006C\u00032\u0007\u0001\u00071\n\u0005\u00024\u0019\u0012)Qg\u0001b\u0001m!)aj\u0001a\u0001{\u0005\u0019q.\u001e;\u0002\u0017]\u0014\u0018\u000e^3Qe\u0016$H/_\u000b\u0003#Z#\"A\u0015+\u0015\u0005}\u0019\u0006\"B\u0016\u0005\u0001\ba\u0003\"B\u0019\u0005\u0001\u0004)\u0006CA\u001aW\t\u0015)DA1\u00017+\rAvl\u0017\u000b\u00043v\u0003GC\u0001.]!\t\u00194\fB\u0003@\u000b\t\u0007\u0001\tC\u0003,\u000b\u0001\u000fA\u0006C\u00032\u000b\u0001\u0007a\f\u0005\u00024?\u0012)Q'\u0002b\u0001m!)a*\u0002a\u00015\u0006!!/Z1e+\t\u0019g\r\u0006\u0002eiR\u0019Qm\u001b7\u0011\u0005M2G!B\u001b\u0007\u0005\u00049\u0017CA\u001ci!\t\t\u0012.\u0003\u0002k%\t\u0019\u0011I\\=\t\u000b-2\u00019\u0001\u0017\t\u000b54\u00019\u00018\u0002\u000554\u0007cA8sK6\t\u0001O\u0003\u0002r%\u00059!/\u001a4mK\u000e$\u0018BA:q\u0005!i\u0015M\\5gKN$\b\"B;\u0007\u0001\u0004y\u0012\u0001\u00026t_:,\"a\u001e>\u0015\u0005atHcA=|yB\u00111G\u001f\u0003\u0006k\u001d\u0011\ra\u001a\u0005\u0006W\u001d\u0001\u001d\u0001\f\u0005\u0006[\u001e\u0001\u001d! \t\u0004_JL\b\"B;\b\u0001\u0004y\bcA\u0017\u0002\u0002%\u0019\u00111\u0001\u0006\u0003\u0013)\u001bxN\\%oaV$H\u0003BA\u0004\u0003K\u0011B!!\u0003\u0011Y\u00191\u00111\u0002\u0005\u0001\u0003\u000f\u0011A\u0002\u0010:fM&tW-\\3oizB!\"a\u0004\u0002\n\t\u0007I\u0011AA\t\u0003)!\u0017\r^3G_Jl\u0017\r^\u000b\u0003\u0003'\u00012!LA\u000b\u0013\r\t9B\u0003\u0002\u000b\t\u0006$XMR8s[\u0006$\bBCA\u000e\u0003\u0013\u0011\r\u0011\"\u0011\u0002\u001e\u0005IA/\u001f9f\u0011&tGo]\u000b\u0003\u0003?\u00012!LA\u0011\u0013\r\t\u0019C\u0003\u0002\n)f\u0004X\rS5oiNDq!a\n\t\u0001\u0004\ty\"A\u0003iS:$8\u000f"
)
public interface Serialization {
   String write(final Object a, final Formats formats);

   Writer write(final Object a, final Writer out, final Formats formats);

   String writePretty(final Object a, final Formats formats);

   Writer writePretty(final Object a, final Writer out, final Formats formats);

   // $FF: synthetic method
   static Object read$(final Serialization $this, final String json, final Formats formats, final Manifest mf) {
      return $this.read(json, formats, mf);
   }

   default Object read(final String json, final Formats formats, final Manifest mf) {
      return this.read((JsonInput)(new StringInput(json)), formats, mf);
   }

   Object read(final JsonInput json, final Formats formats, final Manifest mf);

   // $FF: synthetic method
   static Formats formats$(final Serialization $this, final TypeHints hints) {
      return $this.formats(hints);
   }

   default Formats formats(final TypeHints hints) {
      return new Formats(hints) {
         private final DateFormat dateFormat;
         private final TypeHints typeHints;

         public List customSerializers() {
            return Formats.customSerializers$(this);
         }

         public List richSerializers() {
            return Formats.richSerializers$(this);
         }

         public List customKeySerializers() {
            return Formats.customKeySerializers$(this);
         }

         public List fieldSerializers() {
            return Formats.fieldSerializers$(this);
         }

         public boolean wantsBigInt() {
            return Formats.wantsBigInt$(this);
         }

         public boolean wantsBigDecimal() {
            return Formats.wantsBigDecimal$(this);
         }

         public Set primitives() {
            return Formats.primitives$(this);
         }

         public List companions() {
            return Formats.companions$(this);
         }

         public ExtractionNullStrategy extractionNullStrategy() {
            return Formats.extractionNullStrategy$(this);
         }

         public boolean strictOptionParsing() {
            return Formats.strictOptionParsing$(this);
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

         public boolean strictFieldDeserialization() {
            return Formats.strictFieldDeserialization$(this);
         }

         public boolean considerCompanionConstructors() {
            return Formats.considerCompanionConstructors$(this);
         }

         public ParameterNameReader parameterNameReader() {
            return Formats.parameterNameReader$(this);
         }

         public EmptyValueStrategy emptyValueStrategy() {
            return Formats.emptyValueStrategy$(this);
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

         public DateFormat dateFormat() {
            return this.dateFormat;
         }

         public TypeHints typeHints() {
            return this.typeHints;
         }

         public {
            Formats.$init$(this);
            this.dateFormat = DefaultFormats$.MODULE$.lossless().dateFormat();
            this.typeHints = hints$1;
         }
      };
   }

   static void $init$(final Serialization $this) {
   }
}
