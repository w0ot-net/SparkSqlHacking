package org.apache.spark.sql.streaming;

import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.StructType$;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=d!\u0002\r\u001a\u0003\u0003!\u0003\"B\u0016\u0001\t\u0003a\u0003\"B\u0018\u0001\r\u0003\u0001\u0004\"B \u0001\r\u0003\u0001\u0005\"B \u0001\t\u0003A\u0005\"B&\u0001\r\u0003a\u0005\"B&\u0001\t\u0003\t\u0006\"B&\u0001\t\u00039\u0006\"B&\u0001\t\u0003i\u0006\"B2\u0001\r\u0003!\u0007\"B2\u0001\t\u0003a\u0007\"B;\u0001\r\u00031\bBB;\u0001\r\u0003\ti\u0001C\u0004\u0002\u0014\u0001!\t!!\u0006\t\u000f\u0005e\u0001\u0001\"\u0001\u0002\u001c!9\u0011q\u0004\u0001\u0005\u0002\u0005\u0005\u0002bBA\u0013\u0001\u0011\u0005\u0011q\u0005\u0005\b\u0003W\u0001A\u0011AA\u0017\u0011\u001d\t\t\u0004\u0001D\u0001\u0003gAq!!\u000f\u0001\t\u0003\tY\u0004C\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u00055\u0003A\"\u0005\u0002P!9\u00111\f\u0001\u0005\u0012\u0005u\u0003bBA0\u0001\u0011E\u0011Q\f\u0002\u0011\t\u0006$\u0018m\u0015;sK\u0006l'+Z1eKJT!AG\u000e\u0002\u0013M$(/Z1nS:<'B\u0001\u000f\u001e\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003=}\tQa\u001d9be.T!\u0001I\u0011\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0013aA8sO\u000e\u00011C\u0001\u0001&!\t1\u0013&D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!\f\t\u0003]\u0001i\u0011!G\u0001\u0007M>\u0014X.\u0019;\u0015\u0005E\u0012T\"\u0001\u0001\t\u000bM\u0012\u0001\u0019\u0001\u001b\u0002\rM|WO]2f!\t)DH\u0004\u00027uA\u0011qgJ\u0007\u0002q)\u0011\u0011hI\u0001\u0007yI|w\u000e\u001e \n\u0005m:\u0013A\u0002)sK\u0012,g-\u0003\u0002>}\t11\u000b\u001e:j]\u001eT!aO\u0014\u0002\rM\u001c\u0007.Z7b)\t\t\u0014\tC\u0003@\u0007\u0001\u0007!\t\u0005\u0002D\r6\tAI\u0003\u0002F7\u0005)A/\u001f9fg&\u0011q\t\u0012\u0002\u000b'R\u0014Xo\u0019;UsB,GCA\u0019J\u0011\u0015QE\u00011\u00015\u00031\u00198\r[3nCN#(/\u001b8h\u0003\u0019y\u0007\u000f^5p]R\u0019\u0011'T(\t\u000b9+\u0001\u0019\u0001\u001b\u0002\u0007-,\u0017\u0010C\u0003Q\u000b\u0001\u0007A'A\u0003wC2,X\rF\u00022%NCQA\u0014\u0004A\u0002QBQ\u0001\u0015\u0004A\u0002Q\u0003\"AJ+\n\u0005Y;#a\u0002\"p_2,\u0017M\u001c\u000b\u0004caK\u0006\"\u0002(\b\u0001\u0004!\u0004\"\u0002)\b\u0001\u0004Q\u0006C\u0001\u0014\\\u0013\tavE\u0001\u0003M_:<GcA\u0019_?\")a\n\u0003a\u0001i!)\u0001\u000b\u0003a\u0001AB\u0011a%Y\u0005\u0003E\u001e\u0012a\u0001R8vE2,\u0017aB8qi&|gn\u001d\u000b\u0003c\u0015DQaY\u0005A\u0002\u0019\u0004Ba\u001a65i5\t\u0001N\u0003\u0002jO\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005-D'aA'baR\u0011\u0011'\u001c\u0005\u0006G*\u0001\rA\u001c\t\u0005_R$D'D\u0001q\u0015\t\t(/\u0001\u0003vi&d'\"A:\u0002\t)\fg/Y\u0005\u0003WB\fA\u0001\\8bIR\tq\u000fE\u0002y\u0003\u000fq1!_A\u0002\u001d\rQ\u0018\u0011\u0001\b\u0003w~t!\u0001 @\u000f\u0005]j\u0018\"\u0001\u0012\n\u0005\u0001\n\u0013B\u0001\u0010 \u0013\taR$C\u0002\u0002\u0006m\tq\u0001]1dW\u0006<W-\u0003\u0003\u0002\n\u0005-!!\u0003#bi\u00064%/Y7f\u0015\r\t)a\u0007\u000b\u0004o\u0006=\u0001BBA\t\u0019\u0001\u0007A'\u0001\u0003qCRD\u0017\u0001\u00026t_:$2a^A\f\u0011\u0019\t\t\"\u0004a\u0001i\u0005\u00191m\u001d<\u0015\u0007]\fi\u0002\u0003\u0004\u0002\u00129\u0001\r\u0001N\u0001\u0004q6dGcA<\u0002$!1\u0011\u0011C\bA\u0002Q\n1a\u001c:d)\r9\u0018\u0011\u0006\u0005\u0007\u0003#\u0001\u0002\u0019\u0001\u001b\u0002\u000fA\f'/];fiR\u0019q/a\f\t\r\u0005E\u0011\u00031\u00015\u0003\u0015!\u0018M\u00197f)\r9\u0018Q\u0007\u0005\u0007\u0003o\u0011\u0002\u0019\u0001\u001b\u0002\u0013Q\f'\r\\3OC6,\u0017\u0001\u0002;fqR$2a^A\u001f\u0011\u0019\t\tb\u0005a\u0001i\u0005AA/\u001a=u\r&dW\r\u0006\u0003\u0002D\u0005-\u0003#BA#\u0003\u000f\"T\"A\u000e\n\u0007\u0005%3DA\u0004ECR\f7/\u001a;\t\r\u0005EA\u00031\u00015\u0003]\t7o]3si:{7\u000b]3dS\u001aLW\rZ*dQ\u0016l\u0017\r\u0006\u0003\u0002R\u0005]\u0003c\u0001\u0014\u0002T%\u0019\u0011QK\u0014\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u00033*\u0002\u0019\u0001\u001b\u0002\u0013=\u0004XM]1uS>t\u0017A\u0005<bY&$\u0017\r^3Kg>t7k\u00195f[\u0006$\"!!\u0015\u0002#Y\fG.\u001b3bi\u0016DV\u000e\\*dQ\u0016l\u0017\rK\u0002\u0001\u0003G\u0002B!!\u001a\u0002l5\u0011\u0011q\r\u0006\u0004\u0003Sj\u0012AC1o]>$\u0018\r^5p]&!\u0011QNA4\u0005!)eo\u001c7wS:<\u0007"
)
public abstract class DataStreamReader {
   public abstract DataStreamReader format(final String source);

   public abstract DataStreamReader schema(final StructType schema);

   public DataStreamReader schema(final String schemaString) {
      return this.schema(StructType$.MODULE$.fromDDL(schemaString));
   }

   public abstract DataStreamReader option(final String key, final String value);

   public DataStreamReader option(final String key, final boolean value) {
      return this.option(key, Boolean.toString(value));
   }

   public DataStreamReader option(final String key, final long value) {
      return this.option(key, Long.toString(value));
   }

   public DataStreamReader option(final String key, final double value) {
      return this.option(key, Double.toString(value));
   }

   public abstract DataStreamReader options(final Map options);

   public DataStreamReader options(final java.util.Map options) {
      this.options((Map).MODULE$.MapHasAsScala(options).asScala());
      return this;
   }

   public abstract Dataset load();

   public abstract Dataset load(final String path);

   public Dataset json(final String path) {
      this.validateJsonSchema();
      return this.format("json").load(path);
   }

   public Dataset csv(final String path) {
      return this.format("csv").load(path);
   }

   public Dataset xml(final String path) {
      this.validateXmlSchema();
      return this.format("xml").load(path);
   }

   public Dataset orc(final String path) {
      return this.format("orc").load(path);
   }

   public Dataset parquet(final String path) {
      return this.format("parquet").load(path);
   }

   public abstract Dataset table(final String tableName);

   public Dataset text(final String path) {
      return this.format("text").load(path);
   }

   public Dataset textFile(final String path) {
      this.assertNoSpecifiedSchema("textFile");
      return this.text(path).select((String)"value", (Seq)scala.collection.immutable.Nil..MODULE$).as(Encoders$.MODULE$.STRING());
   }

   public abstract void assertNoSpecifiedSchema(final String operation);

   public void validateJsonSchema() {
   }

   public void validateXmlSchema() {
   }
}
