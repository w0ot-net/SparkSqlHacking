package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\reA\u0002\t\u0012\u0003\u0003\u00192\u0004C\u0003;\u0001\u0011\u00051\bC\u0003>\u0001\u0011\u0005a\bC\u0003W\u0001\u0011\u0005q\u000bC\u0003[\u0001\u0011\u00051\fC\u0003b\u0001\u0011\u0005!\rC\u0003i\u0001\u0011\u0005\u0011\u000eC\u0003m\u0001\u0011\u0005Q\u000eC\u0003q\u0001\u0011\u0005\u0011\u000fC\u0003u\u0001\u0011\u0005Q\u000fC\u0003y\u0001\u0011\u0005\u0011\u0010\u0003\u0004}\u0001\u00016\t\" \u0005\t\u0003G\u0001\u0001U\"\u0005\u0002&!9\u0011Q\u0007\u0001\u0005B\u0005]\u0002bBA,\u0001\u0011\u0005\u0013\u0011\f\u0005\b\u0003[\u0002A\u0011IA8\u0005!\u0019V\r\\3di>\u0014(B\u0001\n\u0014\u0003\u001d1W-\u0019;ve\u0016T!\u0001F\u000b\u0002\u00055d'B\u0001\f\u0018\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0012$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00025\u0005\u0019qN]4\u0016\u0005q\u00193\u0003\u0002\u0001\u001ecQ\u00022AH\u0010\"\u001b\u0005\u0019\u0012B\u0001\u0011\u0014\u0005%)5\u000f^5nCR|'\u000f\u0005\u0002#G1\u0001A!\u0002\u0013\u0001\u0005\u00041#!\u0001+\u0004\u0001E\u0011q%\f\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\b\u001d>$\b.\u001b8h!\rqs&I\u0007\u0002#%\u0011\u0001'\u0005\u0002\u000e'\u0016dWm\u0019;pe6{G-\u001a7\u0011\u00059\u0012\u0014BA\u001a\u0012\u00059\u0019V\r\\3di>\u0014\b+\u0019:b[N\u0004\"!\u000e\u001d\u000e\u0003YR!aN\n\u0002\tU$\u0018\u000e\\\u0005\u0003sY\u0012Q\u0003R3gCVdG\u000fU1sC6\u001cxK]5uC\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002yA\u0019a\u0006A\u0011\u0002\u001dM,GOR3biV\u0014Xm]\"pYR\u0011q\bQ\u0007\u0002\u0001!)\u0011I\u0001a\u0001\u0005\u0006)a/\u00197vKB\u00111I\u0013\b\u0003\t\"\u0003\"!R\u0015\u000e\u0003\u0019S!aR\u0013\u0002\rq\u0012xn\u001c;?\u0013\tI\u0015&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00172\u0013aa\u0015;sS:<'BA%*Q\r\u0011a\n\u0016\t\u0003\u001fJk\u0011\u0001\u0015\u0006\u0003#V\t!\"\u00198o_R\fG/[8o\u0013\t\u0019\u0006KA\u0003TS:\u001cW-I\u0001V\u0003\u0015\u0019d&\r\u00181\u00031\u0019X\r^(viB,HoQ8m)\ty\u0004\fC\u0003B\u0007\u0001\u0007!\tK\u0002\u0004\u001dR\u000b\u0011c]3u\u001dVlGk\u001c9GK\u0006$XO]3t)\tyD\fC\u0003B\t\u0001\u0007Q\f\u0005\u0002)=&\u0011q,\u000b\u0002\u0004\u0013:$\bf\u0001\u0003O)\u0006i1/\u001a;QKJ\u001cWM\u001c;jY\u0016$\"aP2\t\u000b\u0005+\u0001\u0019\u00013\u0011\u0005!*\u0017B\u00014*\u0005\u0019!u.\u001e2mK\"\u001aQA\u0014+\u0002\rM,GO\u00129s)\ty$\u000eC\u0003B\r\u0001\u0007A\rK\u0002\u0007\u001dR\u000baa]3u\r\u0012\u0014HCA o\u0011\u0015\tu\u00011\u0001eQ\r9a\nV\u0001\u0007g\u0016$hi^3\u0015\u0005}\u0012\b\"B!\t\u0001\u0004!\u0007f\u0001\u0005O)\u0006y1/\u001a;TK2,7\r^8s)f\u0004X\r\u0006\u0002@m\")\u0011)\u0003a\u0001\u0005\"\u001a\u0011B\u0014+\u0002\u0017M,G\u000fT1cK2\u001cu\u000e\u001c\u000b\u0003\u007fiDQ!\u0011\u0006A\u0002\tC3A\u0003(U\u0003Y9W\r^*fY\u0016\u001cG/[8o)\u0016\u001cHOU3tk2$Hc\u0001@\u0002 A\u0019q0!\u0007\u000f\t\u0005\u0005\u00111\u0003\b\u0005\u0003\u0007\tyA\u0004\u0003\u0002\u0006\u00055a\u0002BA\u0004\u0003\u0017q1!RA\u0005\u0013\u0005Q\u0012B\u0001\r\u001a\u0013\t1r#C\u0002\u0002\u0012U\t1a]9m\u0013\u0011\t)\"a\u0006\u0002\u000fA\f7m[1hK*\u0019\u0011\u0011C\u000b\n\t\u0005m\u0011Q\u0004\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!!\u0006\u0002\u0018!1\u0011\u0011E\u0006A\u0002y\f!\u0001\u001a4\u0002'\r\u0014X-\u0019;f'\u0016dWm\u0019;pe6{G-\u001a7\u0015\u000b\u0005\n9#a\u000b\t\r\u0005%B\u00021\u0001C\u0003\r)\u0018\u000e\u001a\u0005\b\u0003[a\u0001\u0019AA\u0018\u0003\u001dIg\u000eZ5dKN\u0004B\u0001KA\u0019;&\u0019\u00111G\u0015\u0003\u000b\u0005\u0013(/Y=\u0002\u0007\u0019LG\u000fF\u0002\"\u0003sAq!a\u000f\u000e\u0001\u0004\ti$A\u0004eCR\f7/\u001a;1\t\u0005}\u0012\u0011\n\t\u0007\u0003\u0003\n\u0019%a\u0012\u000e\u0005\u0005]\u0011\u0002BA#\u0003/\u0011q\u0001R1uCN,G\u000fE\u0002#\u0003\u0013\"A\"a\u0013\u0002:\u0005\u0005\t\u0011!B\u0001\u0003\u001b\u00121a\u0018\u00132#\r9\u0013q\n\t\u0004Q\u0005E\u0013bAA*S\t\u0019\u0011I\\=)\u00075qE+A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\tY&a\u001a\u0011\t\u0005u\u00131M\u0007\u0003\u0003?RA!!\u0019\u0002\u0018\u0005)A/\u001f9fg&!\u0011QMA0\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003Sr\u0001\u0019AA.\u0003\u0019\u00198\r[3nC\"\u001aaB\u0014+\u0002\t\r|\u0007/\u001f\u000b\u0004y\u0005E\u0004bBA:\u001f\u0001\u0007\u0011QO\u0001\u0006Kb$(/\u0019\t\u0005\u0003o\ni(\u0004\u0002\u0002z)\u0019\u00111P\n\u0002\u000bA\f'/Y7\n\t\u0005}\u0014\u0011\u0010\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001aqB\u0014+"
)
public abstract class Selector extends Estimator implements SelectorParams, DefaultParamsWritable {
   private IntParam numTopFeatures;
   private DoubleParam percentile;
   private DoubleParam fpr;
   private DoubleParam fdr;
   private DoubleParam fwe;
   private Param selectorType;
   private Param outputCol;
   private Param labelCol;
   private Param featuresCol;

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getNumTopFeatures() {
      return SelectorParams.getNumTopFeatures$(this);
   }

   public double getPercentile() {
      return SelectorParams.getPercentile$(this);
   }

   public double getFpr() {
      return SelectorParams.getFpr$(this);
   }

   public double getFdr() {
      return SelectorParams.getFdr$(this);
   }

   public double getFwe() {
      return SelectorParams.getFwe$(this);
   }

   public String getSelectorType() {
      return SelectorParams.getSelectorType$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final IntParam numTopFeatures() {
      return this.numTopFeatures;
   }

   public final DoubleParam percentile() {
      return this.percentile;
   }

   public final DoubleParam fpr() {
      return this.fpr;
   }

   public final DoubleParam fdr() {
      return this.fdr;
   }

   public final DoubleParam fwe() {
      return this.fwe;
   }

   public final Param selectorType() {
      return this.selectorType;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$numTopFeatures_$eq(final IntParam x$1) {
      this.numTopFeatures = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$percentile_$eq(final DoubleParam x$1) {
      this.percentile = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$fpr_$eq(final DoubleParam x$1) {
      this.fpr = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$fdr_$eq(final DoubleParam x$1) {
      this.fdr = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$fwe_$eq(final DoubleParam x$1) {
      this.fwe = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$selectorType_$eq(final Param x$1) {
      this.selectorType = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public Selector setFeaturesCol(final String value) {
      return (Selector)this.set(this.featuresCol(), value);
   }

   public Selector setOutputCol(final String value) {
      return (Selector)this.set(this.outputCol(), value);
   }

   public Selector setNumTopFeatures(final int value) {
      return (Selector)this.set(this.numTopFeatures(), BoxesRunTime.boxToInteger(value));
   }

   public Selector setPercentile(final double value) {
      return (Selector)this.set(this.percentile(), BoxesRunTime.boxToDouble(value));
   }

   public Selector setFpr(final double value) {
      return (Selector)this.set(this.fpr(), BoxesRunTime.boxToDouble(value));
   }

   public Selector setFdr(final double value) {
      return (Selector)this.set(this.fdr(), BoxesRunTime.boxToDouble(value));
   }

   public Selector setFwe(final double value) {
      return (Selector)this.set(this.fwe(), BoxesRunTime.boxToDouble(value));
   }

   public Selector setSelectorType(final String value) {
      return (Selector)this.set(this.selectorType(), value);
   }

   public Selector setLabelCol(final String value) {
      return (Selector)this.set(this.labelCol(), value);
   }

   public abstract Dataset getSelectionTestResult(final Dataset df);

   public abstract SelectorModel createSelectorModel(final String uid, final int[] indices);

   public SelectorModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      SparkSession spark = dataset.sparkSession();
      int numFeatures = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.featuresCol()));
      Dataset resultDF = this.getSelectionTestResult(dataset.toDF());
      String var7 = (String)this.$(this.selectorType());
      int[] var10000;
      switch (var7 == null ? 0 : var7.hashCode()) {
         case -921824963:
            if (!"percentile".equals(var7)) {
               throw new IllegalArgumentException("Unknown Selector Type: " + var7);
            }

            var10000 = getTopIndices$1((int)((double)numFeatures * this.getPercentile()), resultDF, spark);
            break;
         case -315846324:
            if (!"numTopFeatures".equals(var7)) {
               throw new IllegalArgumentException("Unknown Selector Type: " + var7);
            }

            var10000 = getTopIndices$1(BoxesRunTime.unboxToInt(this.$(this.numTopFeatures())), resultDF, spark);
            break;
         case 101236:
            if (!"fdr".equals(var7)) {
               throw new IllegalArgumentException("Unknown Selector Type: " + var7);
            }

            double f = BoxesRunTime.unboxToDouble(this.$(this.fdr())) / (double)numFeatures;
            int maxIndex = BoxesRunTime.unboxToInt(resultDF.sort("pValue", .MODULE$.wrapRefArray((Object[])(new String[]{"featureIndex"}))).select("pValue", scala.collection.immutable.Nil..MODULE$).as(spark.implicits().newDoubleEncoder()).rdd().zipWithIndex().flatMap((x0$1) -> {
               if (x0$1 != null) {
                  double pValue = x0$1._1$mcD$sp();
                  long index = x0$1._2$mcJ$sp();
                  return pValue <= f * (double)(index + 1L) ? scala.package..MODULE$.Iterator().single(BoxesRunTime.boxToInteger((int)index)) : scala.package..MODULE$.Iterator().empty();
               } else {
                  throw new MatchError(x0$1);
               }
            }, scala.reflect.ClassTag..MODULE$.Int()).fold(BoxesRunTime.boxToInteger(-1), (JFunction2.mcIII.sp)(x, y) -> scala.math.package..MODULE$.max(x, y)));
            var10000 = maxIndex >= 0 ? getTopIndices$1(maxIndex + 1, resultDF, spark) : scala.Array..MODULE$.emptyIntArray();
            break;
         case 101608:
            if (!"fpr".equals(var7)) {
               throw new IllegalArgumentException("Unknown Selector Type: " + var7);
            }

            var10000 = (int[])resultDF.select("featureIndex", scala.collection.immutable.Nil..MODULE$).where(org.apache.spark.sql.functions..MODULE$.col("pValue").$less(this.$(this.fpr()))).as(spark.implicits().newIntEncoder()).collect();
            break;
         case 101812:
            if ("fwe".equals(var7)) {
               var10000 = (int[])resultDF.select("featureIndex", scala.collection.immutable.Nil..MODULE$).where(org.apache.spark.sql.functions..MODULE$.col("pValue").$less(BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(this.$(this.fwe())) / (double)numFeatures))).as(spark.implicits().newIntEncoder()).collect();
               break;
            }

            throw new IllegalArgumentException("Unknown Selector Type: " + var7);
         default:
            throw new IllegalArgumentException("Unknown Selector Type: " + var7);
      }

      int[] indices = var10000;
      return (SelectorModel)this.copyValues(this.createSelectorModel(this.uid(), (int[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.intArrayOps(indices), scala.math.Ordering.Int..MODULE$)).setParent(this), this.copyValues$default$2());
   }

   public StructType transformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.featuresCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      SchemaUtils$.MODULE$.checkNumericType(schema, (String)this.$(this.labelCol()), SchemaUtils$.MODULE$.checkNumericType$default$3());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.outputCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   public Selector copy(final ParamMap extra) {
      return (Selector)this.defaultCopy(extra);
   }

   private static final int[] getTopIndices$1(final int k, final Dataset resultDF$1, final SparkSession spark$1) {
      return (int[])resultDF$1.sort("pValue", .MODULE$.wrapRefArray((Object[])(new String[]{"featureIndex"}))).select("featureIndex", scala.collection.immutable.Nil..MODULE$).limit(k).as(spark$1.implicits().newIntEncoder()).collect();
   }

   public Selector() {
      HasFeaturesCol.$init$(this);
      HasLabelCol.$init$(this);
      HasOutputCol.$init$(this);
      SelectorParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
