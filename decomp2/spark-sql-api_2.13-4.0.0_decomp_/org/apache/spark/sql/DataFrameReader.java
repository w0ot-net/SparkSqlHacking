package org.apache.spark.sql;

import java.util.Properties;
import org.apache.spark.annotation.Stable;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap$;
import org.apache.spark.sql.catalyst.util.SparkCharVarcharUtils$;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.StructType$;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\tue!\u0002\u001a4\u0003\u0003a\u0004\"B\"\u0001\t\u0003!\u0005\"B$\u0001\t\u0003A\u0005\"B,\u0001\t\u0003A\u0006\"B,\u0001\t\u0003\u0001\u0007\"B2\u0001\t\u0003!\u0007\"B2\u0001\t\u0003I\u0007\"B2\u0001\t\u0003y\u0007\"B2\u0001\t\u0003)\b\"B>\u0001\t\u0003a\bBB>\u0001\t\u0003\tI\u0001C\u0004\u0002\u001e\u00011\t!a\b\t\u000f\u0005u\u0001A\"\u0001\u00020!9\u0011Q\u0004\u0001\u0007\u0002\u0005U\u0002bBA(\u0001\u0011\u0005\u0011\u0011\u000b\u0005\b\u0003\u001f\u0002A\u0011AA3\u0011\u001d\ty\u0005\u0001D\u0001\u0003\u000bCq!a&\u0001\t\u0003\tI\nC\u0004\u0002\u0018\u0002!\t!!(\t\u000f\u0005]\u0005A\"\u0001\u0002$\"9\u0011q\u0013\u0001\u0007\u0002\u0005=\u0006bBAL\u0001\u0019\u0005\u0011q\u001b\u0005\b\u0003S\u0004A\u0011AAv\u0011\u001d\tI\u000f\u0001D\u0001\u0003_Dq!!;\u0001\t\u0003\t)\u0010C\u0004\u0002|\u0002!\t!!@\t\u000f\u0005m\b\u0001\"\u0001\u0003\u0002!9\u00111 \u0001\u0007\u0002\t\u001d\u0001b\u0002B\u0007\u0001\u0011\u0005!q\u0002\u0005\b\u0005\u001b\u0001A\u0011\u0001B\n\u0011\u001d\u0011I\u0002\u0001C\u0001\u00057AqA!\u0007\u0001\t\u0003\u0011y\u0002C\u0004\u0002Z\u00011\tA!\n\t\u000f\t-\u0002\u0001\"\u0001\u0003.!9!1\u0006\u0001\u0005\u0002\tE\u0002b\u0002B\u001c\u0001\u0011\u0005!\u0011\b\u0005\b\u0005o\u0001A\u0011\u0001B\u001f\u0011\u001d\u0011\u0019\u0005\u0001C\t\u0005\u000bBqA!\u0015\u0001\t#\u0011\u0019\u0006C\u0004\u0003V\u0001!\tBa\u0015\t\u000f\t]\u0003\u0001\"\u0005\u0003T!Q1\n\u0001a\u0001\u0002\u0004%\tB!\u0017\t\u0017\tm\u0003\u00011AA\u0002\u0013E!Q\f\u0005\u000b\u0005G\u0002\u0001\u0019!A!B\u0013a\u0005\"\u0003B3\u0001\u0001\u0007I\u0011\u0003B4\u0011%\u0011y\u0007\u0001a\u0001\n#\u0011\t\b\u0003\u0005\u0003v\u0001\u0001\u000b\u0015\u0002B5\u0011%\u00119\b\u0001a\u0001\n#\u0011I\bC\u0005\u0003\n\u0002\u0001\r\u0011\"\u0005\u0003\f\"A!q\u0012\u0001!B\u0013\u0011YHA\bECR\fgI]1nKJ+\u0017\rZ3s\u0015\t!T'A\u0002tc2T!AN\u001c\u0002\u000bM\u0004\u0018M]6\u000b\u0005aJ\u0014AB1qC\u000eDWMC\u0001;\u0003\ry'oZ\u0002\u0001'\t\u0001Q\b\u0005\u0002?\u00036\tqHC\u0001A\u0003\u0015\u00198-\u00197b\u0013\t\u0011uH\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0015\u0003\"A\u0012\u0001\u000e\u0003M\naAZ8s[\u0006$HCA%K\u001b\u0005\u0001\u0001\"B&\u0003\u0001\u0004a\u0015AB:pkJ\u001cW\r\u0005\u0002N):\u0011aJ\u0015\t\u0003\u001f~j\u0011\u0001\u0015\u0006\u0003#n\na\u0001\u0010:p_Rt\u0014BA*@\u0003\u0019\u0001&/\u001a3fM&\u0011QK\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005M{\u0014AB:dQ\u0016l\u0017\r\u0006\u0002J3\")qk\u0001a\u00015B\u00111LX\u0007\u00029*\u0011QlM\u0001\u0006if\u0004Xm]\u0005\u0003?r\u0013!b\u0015;sk\u000e$H+\u001f9f)\tI\u0015\rC\u0003c\t\u0001\u0007A*\u0001\u0007tG\",W.Y*ue&tw-\u0001\u0004paRLwN\u001c\u000b\u0004\u0013\u0016<\u0007\"\u00024\u0006\u0001\u0004a\u0015aA6fs\")\u0001.\u0002a\u0001\u0019\u0006)a/\u00197vKR\u0019\u0011J[6\t\u000b\u00194\u0001\u0019\u0001'\t\u000b!4\u0001\u0019\u00017\u0011\u0005yj\u0017B\u00018@\u0005\u001d\u0011un\u001c7fC:$2!\u00139r\u0011\u00151w\u00011\u0001M\u0011\u0015Aw\u00011\u0001s!\tq4/\u0003\u0002u\u007f\t!Aj\u001c8h)\rIeo\u001e\u0005\u0006M\"\u0001\r\u0001\u0014\u0005\u0006Q\"\u0001\r\u0001\u001f\t\u0003}eL!A_ \u0003\r\u0011{WO\u00197f\u0003\u001dy\u0007\u000f^5p]N$\"!S?\t\u000bmL\u0001\u0019\u0001@\u0011\u000b}\f)\u0001\u0014'\u000e\u0005\u0005\u0005!bAA\u0002\u007f\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u001d\u0011\u0011\u0001\u0002\u0004\u001b\u0006\u0004HcA%\u0002\f!9\u0011Q\u0002\u0006A\u0002\u0005=\u0011\u0001B8qiN\u0004b!!\u0005\u0002\u001c1cUBAA\n\u0015\u0011\t)\"a\u0006\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u00033\tAA[1wC&!\u0011qAA\n\u0003\u0011aw.\u00193\u0015\u0005\u0005\u0005\u0002\u0003BA\u0012\u0003Sq1ARA\u0013\u0013\r\t9cM\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tY#!\f\u0003\u0013\u0011\u000bG/\u0019$sC6,'bAA\u0014gQ!\u0011\u0011EA\u0019\u0011\u0019\t\u0019\u0004\u0004a\u0001\u0019\u0006!\u0001/\u0019;i)\u0011\t\t#a\u000e\t\u000f\u0005eR\u00021\u0001\u0002<\u0005)\u0001/\u0019;igB!a(!\u0010M\u0013\r\tyd\u0010\u0002\u000byI,\u0007/Z1uK\u0012t\u0004fA\u0007\u0002DA!\u0011QIA&\u001b\t\t9EC\u0002\u0002J}\n!\"\u00198o_R\fG/[8o\u0013\u0011\ti%a\u0012\u0003\u000fY\f'/\u0019:hg\u0006!!\u000e\u001a2d)!\t\t#a\u0015\u0002X\u0005m\u0003BBA+\u001d\u0001\u0007A*A\u0002ve2Da!!\u0017\u000f\u0001\u0004a\u0015!\u0002;bE2,\u0007bBA/\u001d\u0001\u0007\u0011qL\u0001\u000baJ|\u0007/\u001a:uS\u0016\u001c\b\u0003BA\t\u0003CJA!a\u0019\u0002\u0014\tQ\u0001K]8qKJ$\u0018.Z:\u0015!\u0005\u0005\u0012qMA5\u0003W\ny'a\u001d\u0002x\u0005\u0005\u0005BBA+\u001f\u0001\u0007A\n\u0003\u0004\u0002Z=\u0001\r\u0001\u0014\u0005\u0007\u0003[z\u0001\u0019\u0001'\u0002\u0015\r|G.^7o\u001d\u0006lW\r\u0003\u0004\u0002r=\u0001\rA]\u0001\u000bY><XM\u001d\"pk:$\u0007BBA;\u001f\u0001\u0007!/\u0001\u0006vaB,'OQ8v]\u0012Dq!!\u001f\u0010\u0001\u0004\tY(A\u0007ok6\u0004\u0016M\u001d;ji&|gn\u001d\t\u0004}\u0005u\u0014bAA@\u007f\t\u0019\u0011J\u001c;\t\u000f\u0005\ru\u00021\u0001\u0002`\u0005!2m\u001c8oK\u000e$\u0018n\u001c8Qe>\u0004XM\u001d;jKN$\"\"!\t\u0002\b\u0006%\u00151RAK\u0011\u0019\t)\u0006\u0005a\u0001\u0019\"1\u0011\u0011\f\tA\u00021Cq!!$\u0011\u0001\u0004\ty)\u0001\u0006qe\u0016$\u0017nY1uKN\u0004BAPAI\u0019&\u0019\u00111S \u0003\u000b\u0005\u0013(/Y=\t\u000f\u0005\r\u0005\u00031\u0001\u0002`\u0005!!n]8o)\u0011\t\t#a'\t\r\u0005M\u0012\u00031\u0001M)\u0011\t\t#a(\t\u000f\u0005e\"\u00031\u0001\u0002<!\u001a!#a\u0011\u0015\t\u0005\u0005\u0012Q\u0015\u0005\b\u0003O\u001b\u0002\u0019AAU\u0003-Q7o\u001c8ECR\f7/\u001a;\u0011\t\u0019\u000bY\u000bT\u0005\u0004\u0003[\u001b$a\u0002#bi\u0006\u001cX\r\u001e\u000b\u0005\u0003C\t\t\fC\u0004\u00024R\u0001\r!!.\u0002\u000f)\u001cxN\u001c*E\tB)\u0011qWA`\u00196\u0011\u0011\u0011\u0018\u0006\u0005\u00033\tYLC\u0002\u0002>V\n1!\u00199j\u0013\u0011\t\t-!/\u0003\u000f)\u000bg/\u0019*E\t\"ZA#!2\u0002L\u00065\u0017\u0011[Aj!\rq\u0014qY\u0005\u0004\u0003\u0013|$A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017EAAh\u0003\t*6/\u001a\u0011kg>t\u0007\u0006R1uCN,GoW*ue&tw-X\u0015!S:\u001cH/Z1e]\u0005)1/\u001b8dK\u0006\u0012\u0011Q[\u0001\u0006e9\u0012d\u0006\r\u000b\u0005\u0003C\tI\u000eC\u0004\u00024V\u0001\r!a7\u0011\u000b\u0005u\u00171\u001d'\u000e\u0005\u0005}'bAAqk\u0005\u0019!\u000f\u001a3\n\t\u0005\u0015\u0018q\u001c\u0002\u0004%\u0012#\u0005fC\u000b\u0002F\u0006-\u0017QZAi\u0003'\f1aY:w)\u0011\t\t#!<\t\r\u0005Mb\u00031\u0001M)\u0011\t\t#!=\t\u000f\u0005Mx\u00031\u0001\u0002*\u0006Q1m\u001d<ECR\f7/\u001a;\u0015\t\u0005\u0005\u0012q\u001f\u0005\b\u0003sA\u0002\u0019AA\u001eQ\rA\u00121I\u0001\u0004q6dG\u0003BA\u0011\u0003\u007fDa!a\r\u001a\u0001\u0004aE\u0003BA\u0011\u0005\u0007Aq!!\u000f\u001b\u0001\u0004\tY\u0004K\u0002\u001b\u0003\u0007\"B!!\t\u0003\n!9!1B\u000eA\u0002\u0005%\u0016A\u0003=nY\u0012\u000bG/Y:fi\u00069\u0001/\u0019:rk\u0016$H\u0003BA\u0011\u0005#Aa!a\r\u001d\u0001\u0004aE\u0003BA\u0011\u0005+Aq!!\u000f\u001e\u0001\u0004\tY\u0004K\u0002\u001e\u0003\u0007\n1a\u001c:d)\u0011\t\tC!\b\t\r\u0005Mb\u00041\u0001M)\u0011\t\tC!\t\t\u000f\u0005er\u00041\u0001\u0002<!\u001aq$a\u0011\u0015\t\u0005\u0005\"q\u0005\u0005\u0007\u0005S\u0001\u0003\u0019\u0001'\u0002\u0013Q\f'\r\\3OC6,\u0017\u0001\u0002;fqR$B!!\t\u00030!1\u00111G\u0011A\u00021#B!!\t\u00034!9\u0011\u0011\b\u0012A\u0002\u0005m\u0002f\u0001\u0012\u0002D\u0005AA/\u001a=u\r&dW\r\u0006\u0003\u0002*\nm\u0002BBA\u001aG\u0001\u0007A\n\u0006\u0003\u0002*\n}\u0002bBA\u001dI\u0001\u0007\u00111\b\u0015\u0004I\u0005\r\u0013aF1tg\u0016\u0014HOT8Ta\u0016\u001c\u0017NZ5fIN\u001b\u0007.Z7b)\u0011\u00119E!\u0014\u0011\u0007y\u0012I%C\u0002\u0003L}\u0012A!\u00168ji\"1!qJ\u0013A\u00021\u000b\u0011b\u001c9fe\u0006$\u0018n\u001c8\u00027Y\fG.\u001b3bi\u0016\u001c\u0016N\\4mKZ\u000b'/[1oi\u000e{G.^7o)\t\u00119%\u0001\nwC2LG-\u0019;f\u0015N|gnU2iK6\f\u0017!\u0005<bY&$\u0017\r^3Y[2\u001c6\r[3nCV\tA*\u0001\u0006t_V\u00148-Z0%KF$BAa\u0012\u0003`!A!\u0011\r\u0016\u0002\u0002\u0003\u0007A*A\u0002yIE\nqa]8ve\u000e,\u0007%A\nvg\u0016\u00148\u000b]3dS\u001aLW\rZ*dQ\u0016l\u0017-\u0006\u0002\u0003jA!aHa\u001b[\u0013\r\u0011ig\u0010\u0002\u0007\u001fB$\u0018n\u001c8\u0002/U\u001cXM]*qK\u000eLg-[3e'\u000eDW-\\1`I\u0015\fH\u0003\u0002B$\u0005gB\u0011B!\u0019.\u0003\u0003\u0005\rA!\u001b\u0002)U\u001cXM]*qK\u000eLg-[3e'\u000eDW-\\1!\u00031)\u0007\u0010\u001e:b\u001fB$\u0018n\u001c8t+\t\u0011Y\bE\u0003\u0003~\t\u0015E*\u0004\u0002\u0003\u0000)!\u0011Q\u0003BA\u0015\r\u0011\u0019iM\u0001\tG\u0006$\u0018\r\\=ti&!!q\u0011B@\u0005I\u0019\u0015m]3J]N,gn]5uSZ,W*\u00199\u0002!\u0015DHO]1PaRLwN\\:`I\u0015\fH\u0003\u0002B$\u0005\u001bC\u0011B!\u00191\u0003\u0003\u0005\rAa\u001f\u0002\u001b\u0015DHO]1PaRLwN\\:!Q\r\u0001!1\u0013\t\u0005\u0005+\u0013I*\u0004\u0002\u0003\u0018*\u0019\u0011\u0011J\u001b\n\t\tm%q\u0013\u0002\u0007'R\f'\r\\3"
)
public abstract class DataFrameReader {
   private String source;
   private Option userSpecifiedSchema;
   private CaseInsensitiveMap extraOptions;

   public Dataset load(final String... paths) {
      return this.load((Seq).MODULE$.wrapRefArray((Object[])paths));
   }

   public Dataset json(final String... paths) {
      return this.json((Seq).MODULE$.wrapRefArray((Object[])paths));
   }

   public Dataset csv(final String... paths) {
      return this.csv((Seq).MODULE$.wrapRefArray((Object[])paths));
   }

   public Dataset xml(final String... paths) {
      return this.xml((Seq).MODULE$.wrapRefArray((Object[])paths));
   }

   public Dataset parquet(final String... paths) {
      return this.parquet((Seq).MODULE$.wrapRefArray((Object[])paths));
   }

   public Dataset orc(final String... paths) {
      return this.orc((Seq).MODULE$.wrapRefArray((Object[])paths));
   }

   public Dataset text(final String... paths) {
      return this.text((Seq).MODULE$.wrapRefArray((Object[])paths));
   }

   public Dataset textFile(final String... paths) {
      return this.textFile((Seq).MODULE$.wrapRefArray((Object[])paths));
   }

   public DataFrameReader format(final String source) {
      this.source_$eq(source);
      return this;
   }

   public DataFrameReader schema(final StructType schema) {
      if (schema != null) {
         StructType replaced = (StructType)SparkCharVarcharUtils$.MODULE$.failIfHasCharVarchar(schema);
         this.userSpecifiedSchema_$eq(scala.Option..MODULE$.apply(replaced));
         this.validateSingleVariantColumn();
      }

      return this;
   }

   public DataFrameReader schema(final String schemaString) {
      return this.schema(StructType$.MODULE$.fromDDL(schemaString));
   }

   public DataFrameReader option(final String key, final String value) {
      this.extraOptions_$eq(this.extraOptions().$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), value)));
      this.validateSingleVariantColumn();
      return this;
   }

   public DataFrameReader option(final String key, final boolean value) {
      return this.option(key, Boolean.toString(value));
   }

   public DataFrameReader option(final String key, final long value) {
      return this.option(key, Long.toString(value));
   }

   public DataFrameReader option(final String key, final double value) {
      return this.option(key, Double.toString(value));
   }

   public DataFrameReader options(final Map options) {
      this.extraOptions_$eq(this.extraOptions().$plus$plus(options));
      this.validateSingleVariantColumn();
      return this;
   }

   public DataFrameReader options(final java.util.Map opts) {
      return this.options((Map)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(opts).asScala());
   }

   public abstract Dataset load();

   public abstract Dataset load(final String path);

   public abstract Dataset load(final Seq paths);

   public Dataset jdbc(final String url, final String table, final Properties properties) {
      this.assertNoSpecifiedSchema("jdbc");
      this.extraOptions_$eq(this.extraOptions().$plus$plus(scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(properties).asScala()));
      this.extraOptions_$eq(this.extraOptions().$plus$plus(new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("url"), url), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("dbtable"), table), scala.collection.immutable.Nil..MODULE$))));
      return this.format("jdbc").load();
   }

   public Dataset jdbc(final String url, final String table, final String columnName, final long lowerBound, final long upperBound, final int numPartitions, final Properties connectionProperties) {
      this.extraOptions_$eq(this.extraOptions().$plus$plus((IterableOnce)scala.Predef..MODULE$.Map().apply(.MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("partitionColumn"), columnName), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("lowerBound"), Long.toString(lowerBound)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("upperBound"), Long.toString(upperBound)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numPartitions"), Integer.toString(numPartitions))})))));
      return this.jdbc(url, table, connectionProperties);
   }

   public abstract Dataset jdbc(final String url, final String table, final String[] predicates, final Properties connectionProperties);

   public Dataset json(final String path) {
      return this.json((Seq)(new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$)));
   }

   public Dataset json(final Seq paths) {
      this.validateJsonSchema();
      return this.format("json").load(paths);
   }

   public abstract Dataset json(final Dataset jsonDataset);

   /** @deprecated */
   public abstract Dataset json(final JavaRDD jsonRDD);

   /** @deprecated */
   public abstract Dataset json(final RDD jsonRDD);

   public Dataset csv(final String path) {
      return this.csv((Seq)(new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$)));
   }

   public abstract Dataset csv(final Dataset csvDataset);

   public Dataset csv(final Seq paths) {
      return this.format("csv").load(paths);
   }

   public Dataset xml(final String path) {
      return this.xml((Seq)(new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$)));
   }

   public Dataset xml(final Seq paths) {
      this.validateXmlSchema();
      return this.format("xml").load(paths);
   }

   public abstract Dataset xml(final Dataset xmlDataset);

   public Dataset parquet(final String path) {
      return this.parquet((Seq)(new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$)));
   }

   public Dataset parquet(final Seq paths) {
      return this.format("parquet").load(paths);
   }

   public Dataset orc(final String path) {
      return this.orc((Seq)(new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$)));
   }

   public Dataset orc(final Seq paths) {
      return this.format("orc").load(paths);
   }

   public abstract Dataset table(final String tableName);

   public Dataset text(final String path) {
      return this.text((Seq)(new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$)));
   }

   public Dataset text(final Seq paths) {
      return this.format("text").load(paths);
   }

   public Dataset textFile(final String path) {
      return this.textFile((Seq)(new scala.collection.immutable..colon.colon(path, scala.collection.immutable.Nil..MODULE$)));
   }

   public Dataset textFile(final Seq paths) {
      this.assertNoSpecifiedSchema("textFile");
      return this.text(paths).select((String)"value", (Seq)scala.collection.immutable.Nil..MODULE$).as((Encoder)AgnosticEncoders.StringEncoder$.MODULE$);
   }

   public void assertNoSpecifiedSchema(final String operation) {
      if (this.userSpecifiedSchema().nonEmpty()) {
         throw DataTypeErrors$.MODULE$.userSpecifiedSchemaUnsupportedError(operation);
      }
   }

   public void validateSingleVariantColumn() {
   }

   public void validateJsonSchema() {
   }

   public void validateXmlSchema() {
   }

   public String source() {
      return this.source;
   }

   public void source_$eq(final String x$1) {
      this.source = x$1;
   }

   public Option userSpecifiedSchema() {
      return this.userSpecifiedSchema;
   }

   public void userSpecifiedSchema_$eq(final Option x$1) {
      this.userSpecifiedSchema = x$1;
   }

   public CaseInsensitiveMap extraOptions() {
      return this.extraOptions;
   }

   public void extraOptions_$eq(final CaseInsensitiveMap x$1) {
      this.extraOptions = x$1;
   }

   public DataFrameReader() {
      this.userSpecifiedSchema = scala.None..MODULE$;
      this.extraOptions = CaseInsensitiveMap$.MODULE$.apply(scala.Predef..MODULE$.Map().empty());
   }
}
