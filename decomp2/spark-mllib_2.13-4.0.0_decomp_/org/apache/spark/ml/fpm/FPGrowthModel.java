package org.apache.spark.ml.fpm;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.expressions.SparkUserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Map;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dd\u0001\u0002\u0016,\u0001YB\u0001B\u0012\u0001\u0003\u0006\u0004%\te\u0012\u0005\t=\u0002\u0011\t\u0011)A\u0005\u0011\"A\u0001\r\u0001BC\u0002\u0013\u0005\u0011\r\u0003\u0005u\u0001\t\u0005\t\u0015!\u0003c\u0011!Y\bA!b\u0001\n\u0013a\b\"CA\n\u0001\t\u0005\t\u0015!\u0003~\u0011)\t)\u0002\u0001BC\u0002\u0013%\u0011q\u0003\u0005\u000b\u0003?\u0001!\u0011!Q\u0001\n\u0005e\u0001\u0002CA\u0011\u0001\u0011\u0005Q&a\t\t\u0011\u0005\u0005\u0002\u0001\"\u0001.\u0003cAq!a\r\u0001\t\u0003\t)\u0004C\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005\u001d\u0003\u0001\"\u0001\u0002J!I\u0011q\n\u0001A\u0002\u0013%\u0011\u0011\u000b\u0005\n\u0003'\u0002\u0001\u0019!C\u0005\u0003+B\u0001\"!\u0019\u0001A\u0003&\u0011Q\u0002\u0005\u000b\u0003K\u0002\u0001\u0019!a\u0001\n\u0013\t\u0007bCA4\u0001\u0001\u0007\t\u0019!C\u0005\u0003SB!\"!\u001c\u0001\u0001\u0004\u0005\t\u0015)\u0003c\u0011\u0019\t\t\b\u0001C\u0001C\"9\u0011q\u000f\u0001\u0005B\u0005e\u0004bBAN\u0001\u0011%\u0011Q\u0014\u0005\b\u0003W\u0003A\u0011IAW\u0011\u001d\t\t\r\u0001C!\u0003\u0007Dq!a6\u0001\t\u0003\nI\u000eC\u0004\u0002d\u0002!\t%!:\b\u000f\u0005=8\u0006#\u0001\u0002r\u001a1!f\u000bE\u0001\u0003gDq!!\t\u001d\t\u0003\u0011\t\u0002C\u0004\u0003\u0014q!\tE!\u0006\t\u000f\t}A\u0004\"\u0011\u0003\"\u00199!\u0011\u0006\u000f\u00019\t-\u0002\"\u0003B\u0017A\t\u0005\t\u0015!\u0003<\u0011\u001d\t\t\u0003\tC\u0001\u0005_AqAa\u000e!\t#\u0012ID\u0002\u0004\u0003>q!!q\b\u0005\b\u0003C!C\u0011\u0001B!\u0011%\u0011)\u0005\nb\u0001\n\u0013\u00119\u0005\u0003\u0005\u0003T\u0011\u0002\u000b\u0011\u0002B%\u0011\u001d\u0011y\u0002\nC!\u0005+B\u0011B!\u0017\u001d\u0003\u0003%IAa\u0017\u0003\u001b\u0019\u0003vI]8xi\"lu\u000eZ3m\u0015\taS&A\u0002ga6T!AL\u0018\u0002\u00055d'B\u0001\u00192\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00114'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002i\u0005\u0019qN]4\u0004\u0001M!\u0001aN\u001fA!\rA\u0014hO\u0007\u0002[%\u0011!(\f\u0002\u0006\u001b>$W\r\u001c\t\u0003y\u0001i\u0011a\u000b\t\u0003yyJ!aP\u0016\u0003\u001d\u0019\u0003vI]8xi\"\u0004\u0016M]1ngB\u0011\u0011\tR\u0007\u0002\u0005*\u00111)L\u0001\u0005kRLG.\u0003\u0002F\u0005\nQQ\nT,sSR\f'\r\\3\u0002\u0007ULG-F\u0001I!\tI%K\u0004\u0002K!B\u00111JT\u0007\u0002\u0019*\u0011Q*N\u0001\u0007yI|w\u000e\u001e \u000b\u0003=\u000bQa]2bY\u0006L!!\u0015(\u0002\rA\u0013X\rZ3g\u0013\t\u0019FK\u0001\u0004TiJLgn\u001a\u0006\u0003#:C3!\u0001,]!\t9&,D\u0001Y\u0015\tIv&\u0001\u0006b]:|G/\u0019;j_:L!a\u0017-\u0003\u000bMKgnY3\"\u0003u\u000bQA\r\u00183]A\nA!^5eA!\u001a!A\u0016/\u0002\u0019\u0019\u0014X-]%uK6\u001cX\r^:\u0016\u0003\t\u0004\"a\u00199\u000f\u0005\u0011lgBA3l\u001d\t1'N\u0004\u0002hS:\u00111\n[\u0005\u0002i%\u0011!gM\u0005\u0003aEJ!\u0001\\\u0018\u0002\u0007M\fH.\u0003\u0002o_\u00069\u0001/Y2lC\u001e,'B\u000170\u0013\t\t(OA\u0005ECR\fgI]1nK*\u0011an\u001c\u0015\u0004\u0007Yc\u0016!\u00044sKFLE/Z7tKR\u001c\b\u0005K\u0002\u0005-rC#\u0001B<\u0011\u0005aLX\"\u0001(\n\u0005it%!\u0003;sC:\u001c\u0018.\u001a8u\u0003-IG/Z7TkB\u0004xN\u001d;\u0016\u0003u\u0004rA`A\u0002\u0003\u000f\ti!D\u0001\u0000\u0015\r\t\tAT\u0001\u000bG>dG.Z2uS>t\u0017bAA\u0003\u007f\n\u0019Q*\u00199\u0011\u0007a\fI!C\u0002\u0002\f9\u00131!\u00118z!\rA\u0018qB\u0005\u0004\u0003#q%A\u0002#pk\ndW-\u0001\u0007ji\u0016l7+\u001e9q_J$\b%\u0001\nok6$&/Y5oS:<'+Z2pe\u0012\u001cXCAA\r!\rA\u00181D\u0005\u0004\u0003;q%\u0001\u0002'p]\u001e\f1C\\;n)J\f\u0017N\\5oOJ+7m\u001c:eg\u0002\na\u0001P5oSRtD#C\u001e\u0002&\u0005%\u0012QFA\u0018\u0011\u00151\u0015\u00021\u0001IQ\u0011\t)C\u0016/\t\u000b\u0001L\u0001\u0019\u00012)\t\u0005%b\u000b\u0018\u0005\u0006w&\u0001\r! \u0005\b\u0003+I\u0001\u0019AA\r)\u0005Y\u0014\u0001E:fi6KgnQ8oM&$WM\\2f)\u0011\t9$!\u000f\u000e\u0003\u0001Aq!a\u000f\f\u0001\u0004\ti!A\u0003wC2,X\rK\u0002\f-r\u000b1b]3u\u0013R,Wn]\"pYR!\u0011qGA\"\u0011\u0019\tY\u0004\u0004a\u0001\u0011\"\u001aAB\u0016/\u0002!M,G\u000f\u0015:fI&\u001cG/[8o\u0007>dG\u0003BA\u001c\u0003\u0017Ba!a\u000f\u000e\u0001\u0004A\u0005fA\u0007W9\u0006qqlY1dQ\u0016$W*\u001b8D_:4WCAA\u0007\u0003Iy6-Y2iK\u0012l\u0015N\\\"p]\u001a|F%Z9\u0015\t\u0005]\u0013Q\f\t\u0004q\u0006e\u0013bAA.\u001d\n!QK\\5u\u0011%\tyfDA\u0001\u0002\u0004\ti!A\u0002yIE\nqbX2bG\",G-T5o\u0007>tg\r\t\u0015\u0003!]\fAbX2bG\",GMU;mKN\f\u0001cX2bG\",GMU;mKN|F%Z9\u0015\t\u0005]\u00131\u000e\u0005\t\u0003?\u0012\u0012\u0011!a\u0001E\u0006iqlY1dQ\u0016$'+\u001e7fg\u0002B#aE<\u0002!\u0005\u001c8o\\2jCRLwN\u001c*vY\u0016\u001c\bf\u0001\u000bW9\"\u0012Ac^\u0001\niJ\fgn\u001d4pe6$2AYA>\u0011\u001d\ti(\u0006a\u0001\u0003\u007f\nq\u0001Z1uCN,G\u000f\r\u0003\u0002\u0002\u00065\u0005CBAB\u0003\u000b\u000bI)D\u0001p\u0013\r\t9i\u001c\u0002\b\t\u0006$\u0018m]3u!\u0011\tY)!$\r\u0001\u0011a\u0011qRA>\u0003\u0003\u0005\tQ!\u0001\u0002\u0012\n\u0019q\fJ\u001a\u0012\t\u0005M\u0015q\u0001\t\u0004q\u0006U\u0015bAAL\u001d\n9aj\u001c;iS:<\u0007fA\u000bW9\u0006\u0001r-\u001a8fe&\u001cGK]1og\u001a|'/\u001c\u000b\u0004E\u0006}\u0005bBA?-\u0001\u0007\u0011\u0011\u0015\u0019\u0005\u0003G\u000b9\u000b\u0005\u0004\u0002\u0004\u0006\u0015\u0015Q\u0015\t\u0005\u0003\u0017\u000b9\u000b\u0002\u0007\u0002*\u0006}\u0015\u0011!A\u0001\u0006\u0003\t\tJA\u0002`IQ\nq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0005\u0003_\u000bY\f\u0005\u0003\u00022\u0006]VBAAZ\u0015\r\t)l\\\u0001\u0006if\u0004Xm]\u0005\u0005\u0003s\u000b\u0019L\u0001\u0006TiJ,8\r\u001e+za\u0016Dq!!0\u0018\u0001\u0004\ty+\u0001\u0004tG\",W.\u0019\u0015\u0004/Yc\u0016\u0001B2paf$2aOAc\u0011\u001d\t9\r\u0007a\u0001\u0003\u0013\fQ!\u001a=ue\u0006\u0004B!a3\u0002R6\u0011\u0011Q\u001a\u0006\u0004\u0003\u001fl\u0013!\u00029be\u0006l\u0017\u0002BAj\u0003\u001b\u0014\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u00041Yc\u0016!B<sSR,WCAAn!\r\t\u0015Q\\\u0005\u0004\u0003?\u0014%\u0001C'M/JLG/\u001a:)\u0007e1F,\u0001\u0005u_N#(/\u001b8h)\u0005A\u0005\u0006\u0002\u000eW\u0003S\f#!a;\u0002\u000bMr\u0003G\f\u0019)\u0007\u00011F,A\u0007G!\u001e\u0013xn\u001e;i\u001b>$W\r\u001c\t\u0003yq\u0019r\u0001HA{\u0003w\u0014\t\u0001E\u0002y\u0003oL1!!?O\u0005\u0019\te.\u001f*fMB!\u0011)!@<\u0013\r\tyP\u0011\u0002\u000b\u001b2\u0013V-\u00193bE2,\u0007\u0003\u0002B\u0002\u0005\u001bi!A!\u0002\u000b\t\t\u001d!\u0011B\u0001\u0003S>T!Aa\u0003\u0002\t)\fg/Y\u0005\u0005\u0005\u001f\u0011)A\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002r\u0006!!/Z1e+\t\u00119\u0002\u0005\u0003B\u00053Y\u0014b\u0001B\u000e\u0005\nAQ\n\u0014*fC\u0012,'\u000fK\u0002\u001f-r\u000bA\u0001\\8bIR\u00191Ha\t\t\r\t\u0015r\u00041\u0001I\u0003\u0011\u0001\u0018\r\u001e5)\u0007}1FLA\nG!\u001e\u0013xn\u001e;i\u001b>$W\r\\,sSR,'oE\u0002!\u00037\f\u0001\"\u001b8ti\u0006t7-\u001a\u000b\u0005\u0005c\u0011)\u0004E\u0002\u00034\u0001j\u0011\u0001\b\u0005\u0007\u0005[\u0011\u0003\u0019A\u001e\u0002\u0011M\fg/Z%na2$B!a\u0016\u0003<!1!QE\u0012A\u0002!\u00131C\u0012)He><H\u000f['pI\u0016d'+Z1eKJ\u001c2\u0001\nB\f)\t\u0011\u0019\u0005E\u0002\u00034\u0011\n\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\t%\u0003\u0003\u0002B&\u0005#j!A!\u0014\u000b\t\t=#\u0011B\u0001\u0005Y\u0006tw-C\u0002T\u0005\u001b\n!b\u00197bgNt\u0015-\\3!)\rY$q\u000b\u0005\u0007\u0005KA\u0003\u0019\u0001%\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tu\u0003\u0003\u0002B&\u0005?JAA!\u0019\u0003N\t1qJ\u00196fGRD3\u0001\b,]Q\rYb\u000b\u0018"
)
public class FPGrowthModel extends Model implements FPGrowthParams, MLWritable {
   private final String uid;
   private final transient Dataset freqItemsets;
   private final Map itemSupport;
   private final long org$apache$spark$ml$fpm$FPGrowthModel$$numTrainingRecords;
   private transient double _cachedMinConf;
   private transient Dataset _cachedRules;
   private Param itemsCol;
   private DoubleParam minSupport;
   private IntParam numPartitions;
   private DoubleParam minConfidence;
   private Param predictionCol;

   public static FPGrowthModel load(final String path) {
      return FPGrowthModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return FPGrowthModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getItemsCol() {
      return FPGrowthParams.getItemsCol$(this);
   }

   public double getMinSupport() {
      return FPGrowthParams.getMinSupport$(this);
   }

   public int getNumPartitions() {
      return FPGrowthParams.getNumPartitions$(this);
   }

   public double getMinConfidence() {
      return FPGrowthParams.getMinConfidence$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return FPGrowthParams.validateAndTransformSchema$(this, schema);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public Param itemsCol() {
      return this.itemsCol;
   }

   public DoubleParam minSupport() {
      return this.minSupport;
   }

   public IntParam numPartitions() {
      return this.numPartitions;
   }

   public DoubleParam minConfidence() {
      return this.minConfidence;
   }

   public void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$itemsCol_$eq(final Param x$1) {
      this.itemsCol = x$1;
   }

   public void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$minSupport_$eq(final DoubleParam x$1) {
      this.minSupport = x$1;
   }

   public void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$numPartitions_$eq(final IntParam x$1) {
      this.numPartitions = x$1;
   }

   public void org$apache$spark$ml$fpm$FPGrowthParams$_setter_$minConfidence_$eq(final DoubleParam x$1) {
      this.minConfidence = x$1;
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

   public Dataset freqItemsets() {
      return this.freqItemsets;
   }

   private Map itemSupport() {
      return this.itemSupport;
   }

   public long org$apache$spark$ml$fpm$FPGrowthModel$$numTrainingRecords() {
      return this.org$apache$spark$ml$fpm$FPGrowthModel$$numTrainingRecords;
   }

   public FPGrowthModel setMinConfidence(final double value) {
      return (FPGrowthModel)this.set(this.minConfidence(), BoxesRunTime.boxToDouble(value));
   }

   public FPGrowthModel setItemsCol(final String value) {
      return (FPGrowthModel)this.set(this.itemsCol(), value);
   }

   public FPGrowthModel setPredictionCol(final String value) {
      return (FPGrowthModel)this.set(this.predictionCol(), value);
   }

   private double _cachedMinConf() {
      return this._cachedMinConf;
   }

   private void _cachedMinConf_$eq(final double x$1) {
      this._cachedMinConf = x$1;
   }

   private Dataset _cachedRules() {
      return this._cachedRules;
   }

   private void _cachedRules_$eq(final Dataset x$1) {
      this._cachedRules = x$1;
   }

   public Dataset associationRules() {
      if (BoxesRunTime.unboxToDouble(this.$(this.minConfidence())) == this._cachedMinConf()) {
         return this._cachedRules();
      } else {
         this._cachedRules_$eq(AssociationRules$.MODULE$.getAssociationRulesFromFP(this.freqItemsets(), "items", "freq", BoxesRunTime.unboxToDouble(this.$(this.minConfidence())), this.itemSupport(), this.org$apache$spark$ml$fpm$FPGrowthModel$$numTrainingRecords(), .MODULE$.Any()));
         this._cachedMinConf_$eq(BoxesRunTime.unboxToDouble(this.$(this.minConfidence())));
         return this._cachedRules();
      }
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      return this.genericTransform(dataset);
   }

   private Dataset genericTransform(final Dataset dataset) {
      Tuple2[] rules = (Tuple2[])this.associationRules().select("antecedent", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"consequent"}))).rdd().map((r) -> new Tuple2(r.getSeq(0), r.getSeq(1)), .MODULE$.apply(Tuple2.class)).collect();
      Broadcast brRules = dataset.sparkSession().sparkContext().broadcast(rules, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Tuple2.class)));
      DataType dt = dataset.schema().apply((String)this.$(this.itemsCol())).dataType();
      SparkUserDefinedFunction predictUDF = new SparkUserDefinedFunction((Function1)(items) -> {
         if (items != null) {
            Set itemset = items.toSet();
            return scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.genericArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(brRules.value()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$genericTransform$3(itemset, x$2)))), (x$3) -> (Seq)((IterableOps)x$3._2()).filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$genericTransform$6(itemset, x$4))), .MODULE$.Any())));
         } else {
            return scala.package..MODULE$.Seq().empty();
         }
      }, dt, scala.collection.immutable.Nil..MODULE$, org.apache.spark.sql.expressions.SparkUserDefinedFunction..MODULE$.apply$default$4(), org.apache.spark.sql.expressions.SparkUserDefinedFunction..MODULE$.apply$default$5(), org.apache.spark.sql.expressions.SparkUserDefinedFunction..MODULE$.apply$default$6(), org.apache.spark.sql.expressions.SparkUserDefinedFunction..MODULE$.apply$default$7());
      return dataset.withColumn((String)this.$(this.predictionCol()), predictUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.itemsCol()))}))));
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   public FPGrowthModel copy(final ParamMap extra) {
      FPGrowthModel copied = new FPGrowthModel(this.uid(), this.freqItemsets(), this.itemSupport(), this.org$apache$spark$ml$fpm$FPGrowthModel$$numTrainingRecords());
      return (FPGrowthModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new FPGrowthModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "FPGrowthModel: uid=" + var10000 + ", numTrainingRecords=" + this.org$apache$spark$ml$fpm$FPGrowthModel$$numTrainingRecords();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$genericTransform$4(final Set itemset$1, final Object elem) {
      return itemset$1.contains(elem);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$genericTransform$3(final Set itemset$1, final Tuple2 x$2) {
      return ((IterableOnceOps)x$2._1()).forall((elem) -> BoxesRunTime.boxToBoolean($anonfun$genericTransform$4(itemset$1, elem)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$genericTransform$6(final Set itemset$1, final Object x$4) {
      return !itemset$1.contains(x$4);
   }

   public FPGrowthModel(final String uid, final Dataset freqItemsets, final Map itemSupport, final long numTrainingRecords) {
      this.uid = uid;
      this.freqItemsets = freqItemsets;
      this.itemSupport = itemSupport;
      this.org$apache$spark$ml$fpm$FPGrowthModel$$numTrainingRecords = numTrainingRecords;
      HasPredictionCol.$init$(this);
      FPGrowthParams.$init$(this);
      MLWritable.$init$(this);
      this._cachedMinConf = Double.NaN;
      Statics.releaseFence();
   }

   public FPGrowthModel() {
      this("", (Dataset)null, scala.Predef..MODULE$.Map().empty(), -1L);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class FPGrowthModelWriter extends MLWriter {
      private final FPGrowthModel instance;

      public void saveImpl(final String path) {
         JObject extraMetadata = org.json4s.JsonDSL..MODULE$.map2jvalue((scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numTrainingRecords"), BoxesRunTime.boxToLong(this.instance.org$apache$spark$ml$fpm$FPGrowthModel$$numTrainingRecords()))}))), (x) -> $anonfun$saveImpl$1(BoxesRunTime.unboxToLong(x)));
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession(), new Some(extraMetadata));
         String dataPath = (new Path(path, "data")).toString();
         this.instance.freqItemsets().write().parquet(dataPath);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$1(final long x) {
         return org.json4s.JsonDSL..MODULE$.long2jvalue(x);
      }

      public FPGrowthModelWriter(final FPGrowthModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class FPGrowthModelReader extends MLReader {
      private final String className = FPGrowthModel.class.getName();

      private String className() {
         return this.className;
      }

      public FPGrowthModel load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         Tuple2 var6 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
         if (var6 == null) {
            throw new MatchError(var6);
         } else {
            int major = var6._1$mcI$sp();
            int minor = var6._2$mcI$sp();
            Tuple2.mcII.sp var5 = new Tuple2.mcII.sp(major, minor);
            int major = ((Tuple2)var5)._1$mcI$sp();
            int minor = ((Tuple2)var5)._2$mcI$sp();
            long numTrainingRecords = major >= 2 && (major != 2 || minor >= 4) ? BoxesRunTime.unboxToLong(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numTrainingRecords")), format, scala.reflect.ManifestFactory..MODULE$.Long())) : 0L;
            String dataPath = (new Path(path, "data")).toString();
            Dataset frequentItems = this.sparkSession().read().parquet(dataPath);
            Object var10000;
            if (numTrainingRecords == 0L) {
               var10000 = scala.Predef..MODULE$.Map().empty();
            } else {
               RDD x$1 = frequentItems.rdd().flatMap((x0$1) -> {
                  if (x0$1 != null) {
                     Some var5 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
                     if (!var5.isEmpty() && var5.get() != null && ((SeqOps)var5.get()).lengthCompare(2) == 0) {
                        Object items = ((SeqOps)var5.get()).apply(0);
                        Object count = ((SeqOps)var5.get()).apply(1);
                        if (items instanceof scala.collection.Seq) {
                           scala.collection.Seq var8 = (scala.collection.Seq)items;
                           if (count instanceof Long) {
                              long var9 = BoxesRunTime.unboxToLong(count);
                              if (var8.length() == 1) {
                                 return new Some(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(var8.head()), BoxesRunTime.boxToDouble((double)var9 / (double)numTrainingRecords)));
                              }
                           }
                        }
                     }
                  }

                  return scala.None..MODULE$;
               }, .MODULE$.apply(Tuple2.class));
               ClassTag x$2 = .MODULE$.Any();
               ClassTag x$3 = .MODULE$.Double();
               Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(x$1);
               var10000 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(x$1, x$2, x$3, (Ordering)null).collectAsMap();
            }

            Map itemSupport = (Map)var10000;
            FPGrowthModel model = new FPGrowthModel(metadata.uid(), frequentItems, itemSupport, numTrainingRecords);
            metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
            return model;
         }
      }

      public FPGrowthModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
