package org.apache.spark.ml.evaluation;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.evaluation.RankingMetrics;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Predef;
import scala.Tuple2;
import scala.collection.ArrayOps;
import scala.collection.immutable.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\u00055h\u0001B\u000e\u001d\u0001\u001dB\u0001\"\u0010\u0001\u0003\u0006\u0004%\tE\u0010\u0005\t+\u0002\u0011\t\u0011)A\u0005\u007f!)q\u000b\u0001C\u00011\")q\u000b\u0001C\u0001;\"9q\f\u0001b\u0001\n\u000b\u0001\u0007B\u00024\u0001A\u00035\u0011\rC\u0003i\u0001\u0011\u0005a\bC\u0003k\u0001\u0011\u00051\u000eC\u0004q\u0001\t\u0007IQA9\t\rY\u0004\u0001\u0015!\u0004s\u0011\u0015A\b\u0001\"\u0001z\u0011\u0019y\b\u0001\"\u0001\u0002\u0002!9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\b\u0001\u0011\u0005\u0011\u0011\u0003\u0005\b\u0003/\u0001A\u0011IA\r\u0011\u001d\tY\u0005\u0001C\u0001\u0003\u001bBq!a\u001c\u0001\t\u0003\n\t\bC\u0004\u0002|\u0001!\t%! \t\u000f\u0005-\u0005\u0001\"\u0011\u0002\u000e\u001e9\u00111\u0014\u000f\t\u0002\u0005ueAB\u000e\u001d\u0011\u0003\ty\n\u0003\u0004X+\u0011\u0005\u0011Q\u0018\u0005\n\u0003\u007f+\"\u0019!C\u0005\u0003\u0003D\u0001\"a5\u0016A\u0003%\u00111\u0019\u0005\b\u0003+,B\u0011IAl\u0011%\ty.FA\u0001\n\u0013\t\tO\u0001\tSC:\\\u0017N\\4Fm\u0006dW/\u0019;pe*\u0011QDH\u0001\u000bKZ\fG.^1uS>t'BA\u0010!\u0003\tiGN\u0003\u0002\"E\u0005)1\u000f]1sW*\u00111\u0005J\u0001\u0007CB\f7\r[3\u000b\u0003\u0015\n1a\u001c:h\u0007\u0001\u0019R\u0001\u0001\u0015-i]\u0002\"!\u000b\u0016\u000e\u0003qI!a\u000b\u000f\u0003\u0013\u00153\u0018\r\\;bi>\u0014\bCA\u00173\u001b\u0005q#BA\u00181\u0003\u0019\u0019\b.\u0019:fI*\u0011\u0011GH\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003g9\u0012\u0001\u0003S1t!J,G-[2uS>t7i\u001c7\u0011\u00055*\u0014B\u0001\u001c/\u0005-A\u0015m\u001d'bE\u0016d7i\u001c7\u0011\u0005aZT\"A\u001d\u000b\u0005ir\u0012\u0001B;uS2L!\u0001P\u001d\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003}\u0002\"\u0001Q%\u000f\u0005\u0005;\u0005C\u0001\"F\u001b\u0005\u0019%B\u0001#'\u0003\u0019a$o\\8u})\ta)A\u0003tG\u0006d\u0017-\u0003\u0002I\u000b\u00061\u0001K]3eK\u001aL!AS&\u0003\rM#(/\u001b8h\u0015\tAU\tK\u0002\u0002\u001bN\u0003\"AT)\u000e\u0003=S!\u0001\u0015\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002S\u001f\n)1+\u001b8dK\u0006\nA+A\u00034]Ar\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002N'\u00061A(\u001b8jiz\"\"!\u0017.\u0011\u0005%\u0002\u0001\"B\u001f\u0004\u0001\u0004y\u0004f\u0001.N'\"\u001a1!T*\u0015\u0003eC3\u0001B'T\u0003)iW\r\u001e:jG:\u000bW.Z\u000b\u0002CB\u0019!mY \u000e\u0003AJ!\u0001\u001a\u0019\u0003\u000bA\u000b'/Y7)\u0007\u0015i5+A\u0006nKR\u0014\u0018n\u0019(b[\u0016\u0004\u0003f\u0001\u0004N'\u0006iq-\u001a;NKR\u0014\u0018n\u0019(b[\u0016D3aB'T\u00035\u0019X\r^'fiJL7MT1nKR\u0011A.\\\u0007\u0002\u0001!)a\u000e\u0003a\u0001\u007f\u0005)a/\u00197vK\"\u001a\u0001\"T*\u0002\u0003-,\u0012A\u001d\t\u0003ENL!\u0001\u001e\u0019\u0003\u0011%sG\u000fU1sC6D3!C'T\u0003\tY\u0007\u0005K\u0002\u000b\u001bN\u000bAaZ3u\u0017V\t!\u0010\u0005\u0002|y6\tQ)\u0003\u0002~\u000b\n\u0019\u0011J\u001c;)\u0007-i5+\u0001\u0003tKR\\Ec\u00017\u0002\u0004!)a\u000e\u0004a\u0001u\"\u001aA\"T*\u0002!M,G\u000f\u0015:fI&\u001cG/[8o\u0007>dGc\u00017\u0002\f!)a.\u0004a\u0001\u007f!\u001aQ\"T*\u0002\u0017M,G\u000fT1cK2\u001cu\u000e\u001c\u000b\u0004Y\u0006M\u0001\"\u00028\u000f\u0001\u0004y\u0004f\u0001\bN'\u0006AQM^1mk\u0006$X\r\u0006\u0003\u0002\u001c\u0005\u0005\u0002cA>\u0002\u001e%\u0019\u0011qD#\u0003\r\u0011{WO\u00197f\u0011\u001d\t\u0019c\u0004a\u0001\u0003K\tq\u0001Z1uCN,G\u000f\r\u0003\u0002(\u0005]\u0002CBA\u0015\u0003_\t\u0019$\u0004\u0002\u0002,)\u0019\u0011Q\u0006\u0011\u0002\u0007M\fH.\u0003\u0003\u00022\u0005-\"a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003k\t9\u0004\u0004\u0001\u0005\u0019\u0005e\u0012\u0011EA\u0001\u0002\u0003\u0015\t!a\u000f\u0003\u0007}#\u0013'\u0005\u0003\u0002>\u0005\r\u0003cA>\u0002@%\u0019\u0011\u0011I#\u0003\u000f9{G\u000f[5oOB\u001910!\u0012\n\u0007\u0005\u001dSIA\u0002B]fD3aD'T\u0003)9W\r^'fiJL7m\u001d\u000b\u0005\u0003\u001f\ni\u0006\u0005\u0004\u0002R\u0005e\u00131D\u0007\u0003\u0003'R1!HA+\u0015\r\t9\u0006I\u0001\u0006[2d\u0017NY\u0005\u0005\u00037\n\u0019F\u0001\bSC:\\\u0017N\\4NKR\u0014\u0018nY:\t\u000f\u0005\r\u0002\u00031\u0001\u0002`A\"\u0011\u0011MA3!\u0019\tI#a\f\u0002dA!\u0011QGA3\t1\t9'!\u0018\u0002\u0002\u0003\u0005)\u0011AA\u001e\u0005\ryFE\r\u0015\u0005!5\u000bY'\t\u0002\u0002n\u0005)1GL\u0019/a\u0005q\u0011n\u001d'be\u001e,'OQ3ui\u0016\u0014XCAA:!\rY\u0018QO\u0005\u0004\u0003o*%a\u0002\"p_2,\u0017M\u001c\u0015\u0004#5\u001b\u0016\u0001B2paf$2!WA@\u0011\u001d\t\tI\u0005a\u0001\u0003\u0007\u000bQ!\u001a=ue\u0006\u00042AYAC\u0013\r\t9\t\r\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001a!#T*\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a\u0010\u0015\u0004'5\u001b\u0006f\u0001\u0001\u0002\u0014B\u0019a*!&\n\u0007\u0005]uJ\u0001\u0007FqB,'/[7f]R\fG\u000eK\u0002\u0001\u001bN\u000b\u0001CU1oW&tw-\u0012<bYV\fGo\u001c:\u0011\u0005%*2cB\u000b\u0002\"\u0006\u001d\u0016Q\u0016\t\u0004w\u0006\r\u0016bAAS\u000b\n1\u0011I\\=SK\u001a\u0004B\u0001OAU3&\u0019\u00111V\u001d\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u0011qVA]\u001b\t\t\tL\u0003\u0003\u00024\u0006U\u0016AA5p\u0015\t\t9,\u0001\u0003kCZ\f\u0017\u0002BA^\u0003c\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!!(\u0002)M,\b\u000f]8si\u0016$W*\u001a;sS\u000et\u0015-\\3t+\t\t\u0019\rE\u0003|\u0003\u000b\fI-C\u0002\u0002H\u0016\u0013Q!\u0011:sCf\u0004B!a3\u0002R6\u0011\u0011Q\u001a\u0006\u0005\u0003\u001f\f),\u0001\u0003mC:<\u0017b\u0001&\u0002N\u0006)2/\u001e9q_J$X\rZ'fiJL7MT1nKN\u0004\u0013\u0001\u00027pC\u0012$2!WAm\u0011\u0019\tY.\u0007a\u0001\u007f\u0005!\u0001/\u0019;iQ\rIRjU\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003G\u0004B!a3\u0002f&!\u0011q]Ag\u0005\u0019y%M[3di\"\u001aQ#T*)\u0007Qi5\u000b"
)
public class RankingEvaluator extends Evaluator implements HasPredictionCol, HasLabelCol, DefaultParamsWritable {
   private final String uid;
   private final Param metricName;
   private final IntParam k;
   private Param labelCol;
   private Param predictionCol;

   public static RankingEvaluator load(final String path) {
      return RankingEvaluator$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RankingEvaluator$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public final Param metricName() {
      return this.metricName;
   }

   public String getMetricName() {
      return (String)this.$(this.metricName());
   }

   public RankingEvaluator setMetricName(final String value) {
      return (RankingEvaluator)this.set(this.metricName(), value);
   }

   public final IntParam k() {
      return this.k;
   }

   public int getK() {
      return BoxesRunTime.unboxToInt(this.$(this.k()));
   }

   public RankingEvaluator setK(final int value) {
      return (RankingEvaluator)this.set(this.k(), BoxesRunTime.boxToInteger(value));
   }

   public RankingEvaluator setPredictionCol(final String value) {
      return (RankingEvaluator)this.set(this.predictionCol(), value);
   }

   public RankingEvaluator setLabelCol(final String value) {
      return (RankingEvaluator)this.set(this.labelCol(), value);
   }

   public double evaluate(final Dataset dataset) {
      RankingMetrics metrics = this.getMetrics(dataset);
      String var5 = (String)this.$(this.metricName());
      switch (var5 == null ? 0 : var5.hashCode()) {
         case -1678028846:
            if ("meanAveragePrecisionAtK".equals(var5)) {
               return metrics.meanAveragePrecisionAt(BoxesRunTime.unboxToInt(this.$(this.k())));
            }
            break;
         case 587408807:
            if ("recallAtK".equals(var5)) {
               return metrics.recallAt(BoxesRunTime.unboxToInt(this.$(this.k())));
            }
            break;
         case 1159935174:
            if ("meanAveragePrecision".equals(var5)) {
               return metrics.meanAveragePrecision();
            }
            break;
         case 1798635390:
            if ("ndcgAtK".equals(var5)) {
               return metrics.ndcgAt(BoxesRunTime.unboxToInt(this.$(this.k())));
            }
            break;
         case 2068092186:
            if ("precisionAtK".equals(var5)) {
               return metrics.precisionAt(BoxesRunTime.unboxToInt(this.$(this.k())));
            }
      }

      throw new MatchError(var5);
   }

   public RankingMetrics getMetrics(final Dataset dataset) {
      StructType schema = dataset.schema();
      SchemaUtils$.MODULE$.checkColumnTypes(schema, (String)this.$(this.predictionCol()), new .colon.colon(new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, false), new .colon.colon(new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, true), scala.collection.immutable.Nil..MODULE$)), SchemaUtils$.MODULE$.checkColumnTypes$default$4());
      SchemaUtils$.MODULE$.checkColumnTypes(schema, (String)this.$(this.labelCol()), new .colon.colon(new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, false), new .colon.colon(new ArrayType(org.apache.spark.sql.types.DoubleType..MODULE$, true), scala.collection.immutable.Nil..MODULE$)), SchemaUtils$.MODULE$.checkColumnTypes$default$4());
      RDD predictionAndLabels = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.predictionCol())), org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.labelCol()))}))).rdd().map((row) -> new Tuple2(row.getSeq(0).toArray(scala.reflect.ClassTag..MODULE$.Double()), row.getSeq(1).toArray(scala.reflect.ClassTag..MODULE$.Double())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return new RankingMetrics(predictionAndLabels, scala.reflect.ClassTag..MODULE$.Double());
   }

   public boolean isLargerBetter() {
      return true;
   }

   public RankingEvaluator copy(final ParamMap extra) {
      return (RankingEvaluator)this.defaultCopy(extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "RankingEvaluator: uid=" + var10000 + ", metricName=" + this.$(this.metricName()) + ", k=" + this.$(this.k());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$k$1(final String x$1) {
      return x$1.endsWith("AtK");
   }

   public RankingEvaluator(final String uid) {
      this.uid = uid;
      HasPredictionCol.$init$(this);
      HasLabelCol.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Function1 allowedParams = ParamValidators$.MODULE$.inArray((Object)RankingEvaluator$.MODULE$.org$apache$spark$ml$evaluation$RankingEvaluator$$supportedMetricNames());
      this.metricName = new Param(this, "metricName", "metric name in evaluation " + scala.Predef..MODULE$.wrapRefArray((Object[])RankingEvaluator$.MODULE$.org$apache$spark$ml$evaluation$RankingEvaluator$$supportedMetricNames()).mkString("(", "|", ")"), allowedParams, scala.reflect.ClassTag..MODULE$.apply(String.class));
      Predef var10005 = scala.Predef..MODULE$;
      ArrayOps var10006 = scala.collection.ArrayOps..MODULE$;
      this.k = new IntParam(this, "k", "The ranking position value used in " + var10005.wrapRefArray(var10006.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])RankingEvaluator$.MODULE$.org$apache$spark$ml$evaluation$RankingEvaluator$$supportedMetricNames()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$k$1(x$1)))).mkString("(", "|", ")") + "  Must be > 0. The default value is 10.", ParamValidators$.MODULE$.gt((double)0.0F));
      this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{this.k().$minus$greater(BoxesRunTime.boxToInteger(10)), this.metricName().$minus$greater("meanAveragePrecision")}));
      Statics.releaseFence();
   }

   public RankingEvaluator() {
      this(Identifiable$.MODULE$.randomUID("rankEval"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
