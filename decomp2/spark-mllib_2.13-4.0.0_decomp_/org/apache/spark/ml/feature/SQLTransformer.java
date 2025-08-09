package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.;
import org.apache.spark.sql.types.StructType;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055e\u0001\u0002\u000b\u0016\u0001\u0001B\u0001b\u000b\u0001\u0003\u0006\u0004%\t\u0005\f\u0005\t\u0007\u0002\u0011\t\u0011)A\u0005[!)Q\t\u0001C\u0001\r\")Q\t\u0001C\u0001\u0019\"9a\n\u0001b\u0001\n\u000by\u0005BB,\u0001A\u00035\u0001\u000bC\u0003Z\u0001\u0011\u0005!\fC\u0003`\u0001\u0011\u0005A\u0006C\u0004b\u0001\t\u0007I\u0011\u0002\u0017\t\r\t\u0004\u0001\u0015!\u0003.\u0011\u0015\u0019\u0007\u0001\"\u0011e\u0011\u001d\tI\u0002\u0001C!\u00037Aq!a\f\u0001\t\u0003\n\t\u0004C\u0004\u0002@\u0001!\t%!\u0011\b\u000f\u0005-S\u0003#\u0001\u0002N\u00191A#\u0006E\u0001\u0003\u001fBa!\u0012\t\u0005\u0002\u00055\u0004bBA8!\u0011\u0005\u0013\u0011\u000f\u0005\n\u0003s\u0002\u0012\u0011!C\u0005\u0003w\u0012abU)M)J\fgn\u001d4pe6,'O\u0003\u0002\u0017/\u00059a-Z1ukJ,'B\u0001\r\u001a\u0003\tiGN\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h\u0007\u0001\u00192\u0001A\u0011&!\t\u00113%D\u0001\u0018\u0013\t!sCA\u0006Ue\u0006t7OZ8s[\u0016\u0014\bC\u0001\u0014*\u001b\u00059#B\u0001\u0015\u0018\u0003\u0011)H/\u001b7\n\u0005):#!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7o\u0016:ji\u0006\u0014G.Z\u0001\u0004k&$W#A\u0017\u0011\u00059:dBA\u00186!\t\u00014'D\u00012\u0015\t\u0011t$\u0001\u0004=e>|GO\u0010\u0006\u0002i\u0005)1oY1mC&\u0011agM\u0001\u0007!J,G-\u001a4\n\u0005aJ$AB*ue&twM\u0003\u00027g!\u001a\u0011aO!\u0011\u0005qzT\"A\u001f\u000b\u0005yJ\u0012AC1o]>$\u0018\r^5p]&\u0011\u0001)\u0010\u0002\u0006'&t7-Z\u0011\u0002\u0005\u0006)\u0011G\f\u001c/a\u0005!Q/\u001b3!Q\r\u00111(Q\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u001dK\u0005C\u0001%\u0001\u001b\u0005)\u0002\"B\u0016\u0004\u0001\u0004i\u0003fA%<\u0003\"\u001a1aO!\u0015\u0003\u001dC3\u0001B\u001eB\u0003%\u0019H/\u0019;f[\u0016tG/F\u0001Q!\r\tF+L\u0007\u0002%*\u00111kF\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003+J\u0013Q\u0001U1sC6D3!B\u001eB\u0003)\u0019H/\u0019;f[\u0016tG\u000f\t\u0015\u0004\rm\n\u0015\u0001D:fiN#\u0018\r^3nK:$HCA.]\u001b\u0005\u0001\u0001\"B/\b\u0001\u0004i\u0013!\u0002<bYV,\u0007fA\u0004<\u0003\u0006aq-\u001a;Ti\u0006$X-\\3oi\"\u001a\u0001bO!\u0002\u001fQ\f'\r\\3JI\u0016tG/\u001b4jKJ\f\u0001\u0003^1cY\u0016LE-\u001a8uS\u001aLWM\u001d\u0011\u0002\u0013Q\u0014\u0018M\\:g_JlGCA3w!\t17O\u0004\u0002ha:\u0011\u0001N\u001c\b\u0003S6t!A\u001b7\u000f\u0005AZ\u0017\"\u0001\u0010\n\u0005qi\u0012B\u0001\u000e\u001c\u0013\ty\u0017$A\u0002tc2L!!\u001d:\u0002\u000fA\f7m[1hK*\u0011q.G\u0005\u0003iV\u0014\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0005E\u0014\b\"B<\f\u0001\u0004A\u0018a\u00023bi\u0006\u001cX\r\u001e\u0019\u0003s~\u00042A_>~\u001b\u0005\u0011\u0018B\u0001?s\u0005\u001d!\u0015\r^1tKR\u0004\"A`@\r\u0001\u0011Y\u0011\u0011\u0001<\u0002\u0002\u0003\u0005)\u0011AA\u0002\u0005\ryF%M\t\u0005\u0003\u000b\ti\u0001\u0005\u0003\u0002\b\u0005%Q\"A\u001a\n\u0007\u0005-1GA\u0004O_RD\u0017N\\4\u0011\t\u0005\u001d\u0011qB\u0005\u0004\u0003#\u0019$aA!os\"\"1bOA\u000bC\t\t9\"A\u00033]Ar\u0003'A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\ti\"!\u000b\u0011\t\u0005}\u0011QE\u0007\u0003\u0003CQ1!a\ts\u0003\u0015!\u0018\u0010]3t\u0013\u0011\t9#!\t\u0003\u0015M#(/^2u)f\u0004X\rC\u0004\u0002,1\u0001\r!!\b\u0002\rM\u001c\u0007.Z7bQ\ra1(Q\u0001\u0005G>\u0004\u0018\u0010F\u0002H\u0003gAq!!\u000e\u000e\u0001\u0004\t9$A\u0003fqR\u0014\u0018\rE\u0002R\u0003sI1!a\u000fS\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\bfA\u0007<\u0003\u0006AAo\\*ue&tw\rF\u0001.Q\u0011q1(!\u0012\"\u0005\u0005\u001d\u0013!B\u001a/a9\u0002\u0004f\u0001\u0001<\u0003\u0006q1+\u0015'Ue\u0006t7OZ8s[\u0016\u0014\bC\u0001%\u0011'\u001d\u0001\u0012\u0011KA,\u0003;\u0002B!a\u0002\u0002T%\u0019\u0011QK\u001a\u0003\r\u0005s\u0017PU3g!\u00111\u0013\u0011L$\n\u0007\u0005msEA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn\u001d*fC\u0012\f'\r\\3\u0011\t\u0005}\u0013\u0011N\u0007\u0003\u0003CRA!a\u0019\u0002f\u0005\u0011\u0011n\u001c\u0006\u0003\u0003O\nAA[1wC&!\u00111NA1\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\ti%\u0001\u0003m_\u0006$GcA$\u0002t!1\u0011Q\u000f\nA\u00025\nA\u0001]1uQ\"\u001a!cO!\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0004\u0003BA@\u0003\u000bk!!!!\u000b\t\u0005\r\u0015QM\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\b\u0006\u0005%AB(cU\u0016\u001cG\u000fK\u0002\u0011w\u0005C3aD\u001eB\u0001"
)
public class SQLTransformer extends Transformer implements DefaultParamsWritable {
   private final String uid;
   private final Param statement;
   private final String tableIdentifier;

   public static SQLTransformer load(final String path) {
      return SQLTransformer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return SQLTransformer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String uid() {
      return this.uid;
   }

   public final Param statement() {
      return this.statement;
   }

   public SQLTransformer setStatement(final String value) {
      return (SQLTransformer)this.set(this.statement(), value);
   }

   public String getStatement() {
      return (String)this.$(this.statement());
   }

   private String tableIdentifier() {
      return this.tableIdentifier;
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      String tableName = Identifiable$.MODULE$.randomUID(this.uid());
      dataset.createOrReplaceTempView(tableName);
      String realStatement = ((String)this.$(this.statement())).replace(this.tableIdentifier(), tableName);
      Dataset result = dataset.sparkSession().sql(realStatement);
      dataset.sparkSession().sessionState().catalog().dropTempView(tableName);
      return result;
   }

   public StructType transformSchema(final StructType schema) {
      SparkSession spark = .MODULE$.builder().getOrCreate();
      SparkContext qual$1 = spark.sparkContext();
      Seq x$1 = new scala.collection.immutable..colon.colon(org.apache.spark.sql.Row..MODULE$.empty(), scala.collection.immutable.Nil..MODULE$);
      int x$2 = qual$1.parallelize$default$2();
      RDD dummyRDD = qual$1.parallelize(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Row.class));
      Dataset dummyDF = spark.createDataFrame(dummyRDD, schema);
      String tableName = Identifiable$.MODULE$.randomUID(this.uid());
      String realStatement = ((String)this.$(this.statement())).replace(this.tableIdentifier(), tableName);
      dummyDF.createOrReplaceTempView(tableName);
      StructType outputSchema = spark.sql(realStatement).schema();
      spark.catalog().dropTempView(tableName);
      return outputSchema;
   }

   public SQLTransformer copy(final ParamMap extra) {
      return (SQLTransformer)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "SQLTransformer: uid=" + var10000 + this.get(this.statement()).map((i) -> ", statement=" + i).getOrElse(() -> "");
   }

   public SQLTransformer(final String uid) {
      this.uid = uid;
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.statement = new Param(this, "statement", "SQL statement", scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.tableIdentifier = "__THIS__";
   }

   public SQLTransformer() {
      this(Identifiable$.MODULE$.randomUID("sql"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
