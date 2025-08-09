package org.apache.spark.ml.clustering;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.feature.InstanceBlock$;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasDistanceMeasure;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.stat.Summarizer$;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DoubleType;
import org.apache.spark.sql.types.FloatType;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.DoubleAccumulator;
import org.apache.spark.util.LongAccumulator;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Map;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.math.BigDecimal;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tMf\u0001\u0002\u00192\u0001qB\u0001B\u0014\u0001\u0003\u0006\u0004%\te\u0014\u0005\tM\u0002\u0011\t\u0011)A\u0005!\")\u0001\u000e\u0001C\u0001S\")a\u000e\u0001C!_\")\u0001\u000e\u0001C\u0001s\")1\u0010\u0001C\u0001y\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\u0006\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!a\f\u0001\t\u0003\t\t\u0004C\u0004\u00028\u0001!\t!!\u000f\t\u000f\u0005}\u0002\u0001\"\u0001\u0002B!9\u0011Q\n\u0001\u0005\u0002\u0005=\u0003bBA.\u0001\u0011\u0005\u0011Q\f\u0005\b\u0003O\u0002A\u0011AA5\u0011\u001d\t\u0019\b\u0001C\u0001\u0003kBq!a\u001f\u0001\t\u0003\ni\bC\u0004\u0002.\u0002!I!a,\t\u000f\u0005\r\u0007\u0001\"\u0003\u0002F\"9\u0011\u0011\u001e\u0001\u0005\n\u0005-\bbBA~\u0001\u0011%\u0011Q \u0005\b\u0005#\u0001A\u0011\u0002B\n\u0011\u001d\u00119\u0003\u0001C!\u0005S9qAa\u00102\u0011\u0003\u0011\tE\u0002\u00041c!\u0005!1\t\u0005\u0007Qj!\tA!\u0019\t\u000f\t\r$\u0004\"\u0011\u0003f!Q!\u0011\u000f\u000eC\u0002\u0013\u0005\u0011Ga\u001d\t\u0011\t}$\u0004)A\u0005\u0005kB!B!!\u001b\u0005\u0004%\t!\rB:\u0011!\u0011\u0019I\u0007Q\u0001\n\tU\u0004B\u0003BC5\t\u0007I\u0011A\u0019\u0003\b\"A!1\u0012\u000e!\u0002\u0013\u0011I\t\u0003\u0006\u0003\u000ej\u0011\r\u0011\"\u00012\u0005gB\u0001Ba$\u001bA\u0003%!Q\u000f\u0005\u000b\u0005#S\"\u0019!C\u0001c\tM\u0004\u0002\u0003BJ5\u0001\u0006IA!\u001e\t\u0015\tU%D1A\u0005\u0002E\u0012\u0019\b\u0003\u0005\u0003\u0018j\u0001\u000b\u0011\u0002B;\u0011)\u0011IJ\u0007b\u0001\n\u0003\t$1\u000f\u0005\t\u00057S\u0002\u0015!\u0003\u0003v!Q!Q\u0014\u000eC\u0002\u0013\u0005\u0011Ga\u001d\t\u0011\t}%\u0004)A\u0005\u0005kB!B!)\u001b\u0005\u0004%\t!\rBD\u0011!\u0011\u0019K\u0007Q\u0001\n\t%\u0005\"\u0003BS5\u0005\u0005I\u0011\u0002BT\u0005\u0019YU*Z1og*\u0011!gM\u0001\u000bG2,8\u000f^3sS:<'B\u0001\u001b6\u0003\tiGN\u0003\u00027o\u0005)1\u000f]1sW*\u0011\u0001(O\u0001\u0007CB\f7\r[3\u000b\u0003i\n1a\u001c:h\u0007\u0001\u0019B\u0001A\u001fF\u0011B\u0019ahP!\u000e\u0003MJ!\u0001Q\u001a\u0003\u0013\u0015\u001bH/[7bi>\u0014\bC\u0001\"D\u001b\u0005\t\u0014B\u0001#2\u0005-YU*Z1og6{G-\u001a7\u0011\u0005\t3\u0015BA$2\u00051YU*Z1ogB\u000b'/Y7t!\tIE*D\u0001K\u0015\tY5'\u0001\u0003vi&d\u0017BA'K\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005\u0001\u0006CA)[\u001d\t\u0011\u0006\f\u0005\u0002T-6\tAK\u0003\u0002Vw\u00051AH]8pizR\u0011aV\u0001\u0006g\u000e\fG.Y\u0005\u00033Z\u000ba\u0001\u0015:fI\u00164\u0017BA.]\u0005\u0019\u0019FO]5oO*\u0011\u0011L\u0016\u0015\u0004\u0003y#\u0007CA0c\u001b\u0005\u0001'BA16\u0003)\tgN\\8uCRLwN\\\u0005\u0003G\u0002\u0014QaU5oG\u0016\f\u0013!Z\u0001\u0006c9*d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003=\u0012\fa\u0001P5oSRtDC\u00016l!\t\u0011\u0005\u0001C\u0003O\u0007\u0001\u0007\u0001\u000bK\u0002l=\u0012D3a\u00010e\u0003\u0011\u0019w\u000e]=\u0015\u0005)\u0004\b\"B9\u0005\u0001\u0004\u0011\u0018!B3yiJ\f\u0007CA:w\u001b\u0005!(BA;4\u0003\u0015\u0001\u0018M]1n\u0013\t9HO\u0001\u0005QCJ\fW.T1qQ\r!a\f\u001a\u000b\u0002U\"\u001aQA\u00183\u0002\u001dM,GOR3biV\u0014Xm]\"pYR\u0011QP`\u0007\u0002\u0001!)qP\u0002a\u0001!\u0006)a/\u00197vK\"\u001aaA\u00183\u0002!M,G\u000f\u0015:fI&\u001cG/[8o\u0007>dGcA?\u0002\b!)qp\u0002a\u0001!\"\u001aqA\u00183\u0002\tM,Go\u0013\u000b\u0004{\u0006=\u0001BB@\t\u0001\u0004\t\t\u0002\u0005\u0003\u0002\u0014\u0005UQ\"\u0001,\n\u0007\u0005]aKA\u0002J]RD3\u0001\u00030e\u0003-\u0019X\r^%oSRlu\u000eZ3\u0015\u0007u\fy\u0002C\u0003\u0000\u0013\u0001\u0007\u0001\u000bK\u0002\n=\u0012\f!c]3u\t&\u001cH/\u00198dK6+\u0017m];sKR\u0019Q0a\n\t\u000b}T\u0001\u0019\u0001))\t)q\u00161F\u0011\u0003\u0003[\tQA\r\u00185]A\nAb]3u\u0013:LGo\u0015;faN$2!`A\u001a\u0011\u0019y8\u00021\u0001\u0002\u0012!\u001a1B\u00183\u0002\u0015M,G/T1y\u0013R,'\u000fF\u0002~\u0003wAaa \u0007A\u0002\u0005E\u0001f\u0001\u0007_I\u000611/\u001a;U_2$2!`A\"\u0011\u0019yX\u00021\u0001\u0002FA!\u00111CA$\u0013\r\tIE\u0016\u0002\u0007\t>,(\r\\3)\u00075qF-A\u0004tKR\u001cV-\u001a3\u0015\u0007u\f\t\u0006\u0003\u0004\u0000\u001d\u0001\u0007\u00111\u000b\t\u0005\u0003'\t)&C\u0002\u0002XY\u0013A\u0001T8oO\"\u001aaB\u00183\u0002\u0019M,GoV3jO\"$8i\u001c7\u0015\u0007u\fy\u0006C\u0003\u0000\u001f\u0001\u0007\u0001\u000b\u000b\u0003\u0010=\u0006\r\u0014EAA3\u0003\u0015\u0019d\u0006\r\u00181\u0003%\u0019X\r^*pYZ,'\u000fF\u0002~\u0003WBQa \tA\u0002ACC\u0001\u00050\u0002p\u0005\u0012\u0011\u0011O\u0001\u0006g9\"d\u0006M\u0001\u0014g\u0016$X*\u0019=CY>\u001c7nU5{K&sWJ\u0011\u000b\u0004{\u0006]\u0004BB@\u0012\u0001\u0004\t)\u0005\u000b\u0003\u0012=\u0006=\u0014a\u00014jiR\u0019\u0011)a \t\u000f\u0005\u0005%\u00031\u0001\u0002\u0004\u00069A-\u0019;bg\u0016$\b\u0007BAC\u0003+\u0003b!a\"\u0002\u000e\u0006EUBAAE\u0015\r\tY)N\u0001\u0004gFd\u0017\u0002BAH\u0003\u0013\u0013q\u0001R1uCN,G\u000f\u0005\u0003\u0002\u0014\u0006UE\u0002\u0001\u0003\r\u0003/\u000by(!A\u0001\u0002\u000b\u0005\u0011\u0011\u0014\u0002\u0004?\u0012\u0012\u0014\u0003BAN\u0003C\u0003B!a\u0005\u0002\u001e&\u0019\u0011q\u0014,\u0003\u000f9{G\u000f[5oOB!\u00111CAR\u0013\r\t)K\u0016\u0002\u0004\u0003:L\b\u0006\u0002\n_\u0003S\u000b#!a+\u0002\u000bIr\u0003G\f\u0019\u0002#A\u0014XMZ3s\u00052|7m[*pYZ,'\u000f\u0006\u0003\u00022\u0006]\u0006\u0003BA\n\u0003gK1!!.W\u0005\u001d\u0011un\u001c7fC:Dq!!!\u0014\u0001\u0004\tI\f\r\u0003\u0002<\u0006}\u0006CBAD\u0003\u001b\u000bi\f\u0005\u0003\u0002\u0014\u0006}F\u0001DAa\u0003o\u000b\t\u0011!A\u0003\u0002\u0005e%aA0%g\u0005aAO]1j]^KG\u000f\u001b*poR1\u0011qYAj\u0003?\u0004B!!3\u0002R6\u0011\u00111\u001a\u0006\u0004e\u00055'bAAhk\u0005)Q\u000e\u001c7jE&\u0019A)a3\t\u000f\u0005\u0005E\u00031\u0001\u0002VB\"\u0011q[An!\u0019\t9)!$\u0002ZB!\u00111SAn\t1\ti.a5\u0002\u0002\u0003\u0005)\u0011AAM\u0005\ryF\u0005\u000e\u0005\b\u0003C$\u0002\u0019AAr\u0003\u0015Ign\u001d;s!\rI\u0015Q]\u0005\u0004\u0003OT%aD%ogR\u0014X/\\3oi\u0006$\u0018n\u001c8\u0002\u001dQ\u0014\u0018-\u001b8XSRD'\t\\8dWR1\u0011qYAw\u0003sDq!!!\u0016\u0001\u0004\ty\u000f\r\u0003\u0002r\u0006U\bCBAD\u0003\u001b\u000b\u0019\u0010\u0005\u0003\u0002\u0014\u0006UH\u0001DA|\u0003[\f\t\u0011!A\u0003\u0002\u0005e%aA0%k!9\u0011\u0011]\u000bA\u0002\u0005\r\u0018aE4fi\u0012K7\u000f^1oG\u00164UO\\2uS>tWCAA\u0000!)\t\u0019B!\u0001\u0003\u0006\t\u0015\u0011QI\u0005\u0004\u0005\u00071&!\u0003$v]\u000e$\u0018n\u001c83!\u0011\u00119A!\u0004\u000e\u0005\t%!b\u0001B\u0006g\u00051A.\u001b8bY\u001eLAAa\u0004\u0003\n\t1a+Z2u_J\f!\"\u001b8ji&\fG.\u001b>f)\u0011\u0011)Ba\u0007\u0011\r\u0005M!q\u0003B\u0003\u0013\r\u0011IB\u0016\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\u0003\u0003;\u0002\u0019\u0001B\u000fa\u0011\u0011yBa\t\u0011\r\u0005\u001d\u0015Q\u0012B\u0011!\u0011\t\u0019Ja\t\u0005\u0019\t\u0015\"1DA\u0001\u0002\u0003\u0015\t!!'\u0003\u0007}#c'A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\u0011YCa\u000e\u0011\t\t5\"1G\u0007\u0003\u0005_QAA!\r\u0002\n\u0006)A/\u001f9fg&!!Q\u0007B\u0018\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0005sA\u0002\u0019\u0001B\u0016\u0003\u0019\u00198\r[3nC\"\u001a\u0001D\u00183)\u0007\u0001qF-\u0001\u0004L\u001b\u0016\fgn\u001d\t\u0003\u0005j\u0019rA\u0007B#\u0005\u0017\u0012\t\u0006\u0005\u0003\u0002\u0014\t\u001d\u0013b\u0001B%-\n1\u0011I\\=SK\u001a\u0004B!\u0013B'U&\u0019!q\n&\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!!1\u000bB/\u001b\t\u0011)F\u0003\u0003\u0003X\te\u0013AA5p\u0015\t\u0011Y&\u0001\u0003kCZ\f\u0017\u0002\u0002B0\u0005+\u0012AbU3sS\u0006d\u0017N_1cY\u0016$\"A!\u0011\u0002\t1|\u0017\r\u001a\u000b\u0004U\n\u001d\u0004B\u0002B59\u0001\u0007\u0001+\u0001\u0003qCRD\u0007\u0006\u0002\u000f_\u0005[\n#Aa\u001c\u0002\u000bErcG\f\u0019\u0002\rI\u000be\nR(N+\t\u0011)\b\u0005\u0003\u0003x\tuTB\u0001B=\u0015\u0011\u0011YH!\u0017\u0002\t1\fgnZ\u0005\u00047\ne\u0014a\u0002*B\u001d\u0012{U\nI\u0001\u0011\u0017~kU)\u0011(T?B\u000b%+\u0011'M\u000b2\u000b\u0011cS0N\u000b\u0006s5k\u0018)B%\u0006cE*\u0012'!\u0003I\u0019X\u000f\u001d9peR,G-\u00138ji6{G-Z:\u0016\u0005\t%\u0005CBA\n\u0005/\u0011)(A\ntkB\u0004xN\u001d;fI&s\u0017\u000e^'pI\u0016\u001c\b%A\u0005F+\u000ec\u0015\nR#B\u001d\u0006QQ)V\"M\u0013\u0012+\u0015I\u0014\u0011\u0002\r\r{5+\u0013(F\u0003\u001d\u0019ujU%O\u000b\u0002\n1AU(X\u0003\u0011\u0011vj\u0016\u0011\u0002\u000b\tcujQ&\u0002\r\tcujQ&!\u0003\u0011\tU\u000bV(\u0002\u000b\u0005+Fk\u0014\u0011\u0002!M,\b\u000f]8si\u0016$7k\u001c7wKJ\u001c\u0018!E:vaB|'\u000f^3e'>dg/\u001a:tA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!\u0011\u0016\t\u0005\u0005o\u0012Y+\u0003\u0003\u0003.\ne$AB(cU\u0016\u001cG\u000f\u000b\u0003\u001b=\n5\u0004\u0006B\r_\u0005[\u0002"
)
public class KMeans extends Estimator implements KMeansParams, DefaultParamsWritable {
   private final String uid;
   private IntParam k;
   private Param initMode;
   private IntParam initSteps;
   private Param solver;
   private DoubleParam maxBlockSizeInMB;
   private Param weightCol;
   private Param distanceMeasure;
   private DoubleParam tol;
   private Param predictionCol;
   private LongParam seed;
   private Param featuresCol;
   private IntParam maxIter;

   public static KMeans load(final String path) {
      return KMeans$.MODULE$.load(path);
   }

   public static MLReader read() {
      return KMeans$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getK() {
      return KMeansParams.getK$(this);
   }

   public String getInitMode() {
      return KMeansParams.getInitMode$(this);
   }

   public int getInitSteps() {
      return KMeansParams.getInitSteps$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return KMeansParams.validateAndTransformSchema$(this, schema);
   }

   public final double getMaxBlockSizeInMB() {
      return HasMaxBlockSizeInMB.getMaxBlockSizeInMB$(this);
   }

   public final String getSolver() {
      return HasSolver.getSolver$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final String getDistanceMeasure() {
      return HasDistanceMeasure.getDistanceMeasure$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final IntParam k() {
      return this.k;
   }

   public final Param initMode() {
      return this.initMode;
   }

   public final IntParam initSteps() {
      return this.initSteps;
   }

   public final Param solver() {
      return this.solver;
   }

   public final void org$apache$spark$ml$clustering$KMeansParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
   }

   public final void org$apache$spark$ml$clustering$KMeansParams$_setter_$initMode_$eq(final Param x$1) {
      this.initMode = x$1;
   }

   public final void org$apache$spark$ml$clustering$KMeansParams$_setter_$initSteps_$eq(final IntParam x$1) {
      this.initSteps = x$1;
   }

   public final void org$apache$spark$ml$clustering$KMeansParams$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
   }

   public final DoubleParam maxBlockSizeInMB() {
      return this.maxBlockSizeInMB;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxBlockSizeInMB$_setter_$maxBlockSizeInMB_$eq(final DoubleParam x$1) {
      this.maxBlockSizeInMB = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1) {
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final Param distanceMeasure() {
      return this.distanceMeasure;
   }

   public final void org$apache$spark$ml$param$shared$HasDistanceMeasure$_setter_$distanceMeasure_$eq(final Param x$1) {
      this.distanceMeasure = x$1;
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public KMeans copy(final ParamMap extra) {
      return (KMeans)this.defaultCopy(extra);
   }

   public KMeans setFeaturesCol(final String value) {
      return (KMeans)this.set(this.featuresCol(), value);
   }

   public KMeans setPredictionCol(final String value) {
      return (KMeans)this.set(this.predictionCol(), value);
   }

   public KMeans setK(final int value) {
      return (KMeans)this.set(this.k(), BoxesRunTime.boxToInteger(value));
   }

   public KMeans setInitMode(final String value) {
      return (KMeans)this.set(this.initMode(), value);
   }

   public KMeans setDistanceMeasure(final String value) {
      return (KMeans)this.set(this.distanceMeasure(), value);
   }

   public KMeans setInitSteps(final int value) {
      return (KMeans)this.set(this.initSteps(), BoxesRunTime.boxToInteger(value));
   }

   public KMeans setMaxIter(final int value) {
      return (KMeans)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public KMeans setTol(final double value) {
      return (KMeans)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public KMeans setSeed(final long value) {
      return (KMeans)this.set(this.seed(), BoxesRunTime.boxToLong(value));
   }

   public KMeans setWeightCol(final String value) {
      return (KMeans)this.set(this.weightCol(), value);
   }

   public KMeans setSolver(final String value) {
      return (KMeans)this.set(this.solver(), value);
   }

   public KMeans setMaxBlockSizeInMB(final double value) {
      return (KMeans)this.set(this.maxBlockSizeInMB(), BoxesRunTime.boxToDouble(value));
   }

   public KMeansModel fit(final Dataset dataset) {
      return (KMeansModel)Instrumentation$.MODULE$.instrumented((instr) -> {
         this.transformSchema(dataset.schema(), true);
         instr.logPipelineStage(this);
         instr.logDataset(dataset);
         instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.featuresCol(), this.predictionCol(), this.k(), this.initMode(), this.initSteps(), this.distanceMeasure(), this.maxIter(), this.seed(), this.tol(), this.weightCol(), this.solver(), this.maxBlockSizeInMB()}));
         org.apache.spark.mllib.clustering.KMeansModel oldModel = this.preferBlockSolver(dataset) ? this.trainWithBlock(dataset, instr) : this.trainWithRow(dataset, instr);
         KMeansModel model = (KMeansModel)this.copyValues((new KMeansModel(this.uid(), oldModel)).setParent(this), this.copyValues$default$2());
         KMeansSummary summary = new KMeansSummary(model.transform(dataset), (String)this.$(this.predictionCol()), (String)this.$(this.featuresCol()), BoxesRunTime.unboxToInt(this.$(this.k())), oldModel.numIter(), oldModel.trainingCost());
         model.setSummary(new Some(summary));
         instr.logNamedValue("clusterSizes", summary.clusterSizes());
         return model;
      });
   }

   private boolean preferBlockSolver(final Dataset dataset) {
      String var6 = (String)this.$(this.solver());
      String var10000 = KMeans$.MODULE$.ROW();
      if (var10000 == null) {
         if (var6 == null) {
            return false;
         }
      } else if (var10000.equals(var6)) {
         return false;
      }

      var10000 = KMeans$.MODULE$.BLOCK();
      if (var10000 == null) {
         if (var6 == null) {
            return true;
         }
      } else if (var10000.equals(var6)) {
         return true;
      }

      var10000 = KMeans$.MODULE$.AUTO();
      if (var10000 == null) {
         if (var6 != null) {
            throw new MatchError(var6);
         }
      } else if (!var10000.equals(var6)) {
         throw new MatchError(var6);
      }

      DataType var10 = dataset.schema().apply((String)this.$(this.featuresCol())).dataType();
      if (var10 instanceof VectorUDT) {
         Row var12 = (Row)dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{Summarizer$.MODULE$.metrics((Seq).MODULE$.wrapRefArray((Object[])(new String[]{"count", "numNonZeros"}))).summary(DatasetUtils$.MODULE$.checkNonNanVectors(org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol())))).as("summary")}))).select("summary.count", .MODULE$.wrapRefArray((Object[])(new String[]{"summary.numNonZeros"}))).first();
         if (var12 != null) {
            Some var13 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var12);
            if (!var13.isEmpty() && var13.get() != null && ((SeqOps)var13.get()).lengthCompare(2) == 0) {
               Object count = ((SeqOps)var13.get()).apply(0);
               Object numNonzeros = ((SeqOps)var13.get()).apply(1);
               if (count instanceof Long) {
                  long var16 = BoxesRunTime.unboxToLong(count);
                  if (numNonzeros instanceof Vector) {
                     Vector var18 = (Vector)numNonzeros;
                     Tuple2 var11 = new Tuple2(BoxesRunTime.boxToLong(var16), var18);
                     long count = var11._1$mcJ$sp();
                     Vector numNonzeros = (Vector)var11._2();
                     int numFeatures = numNonzeros.size();
                     BigDecimal nnz = (BigDecimal)numNonzeros.activeIterator().map((x$5) -> BoxesRunTime.boxToInteger($anonfun$preferBlockSolver$1(x$5))).foldLeft(scala.package..MODULE$.BigDecimal().apply(0), (x$6, x$7) -> $anonfun$preferBlockSolver$2(x$6, BoxesRunTime.unboxToInt(x$7)));
                     return nnz.$greater$eq(scala.package..MODULE$.BigDecimal().apply(count).$times(scala.math.BigDecimal..MODULE$.int2bigDecimal(numFeatures)).$times(scala.math.BigDecimal..MODULE$.double2bigDecimal((double)0.5F)));
                  }
               }
            }
         }

         throw new MatchError(var12);
      } else if (var10 instanceof ArrayType) {
         ArrayType var24 = (ArrayType)var10;
         DataType var25 = var24.elementType();
         if (var25 instanceof FloatType) {
            return true;
         } else if (var25 instanceof DoubleType) {
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private org.apache.spark.mllib.clustering.KMeansModel trainWithRow(final Dataset dataset, final Instrumentation instr) {
      org.apache.spark.mllib.clustering.KMeans algo;
      RDD instances;
      boolean var7;
      label17: {
         label16: {
            algo = (new org.apache.spark.mllib.clustering.KMeans()).setK(BoxesRunTime.unboxToInt(this.$(this.k()))).setInitializationMode((String)this.$(this.initMode())).setInitializationSteps(BoxesRunTime.unboxToInt(this.$(this.initSteps()))).setMaxIterations(BoxesRunTime.unboxToInt(this.$(this.maxIter()))).setSeed(BoxesRunTime.unboxToLong(this.$(this.seed()))).setEpsilon(BoxesRunTime.unboxToDouble(this.$(this.tol()))).setDistanceMeasure((String)this.$(this.distanceMeasure()));
            instances = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkNonNanVectors(DatasetUtils$.MODULE$.columnToVector(dataset, (String)this.$(this.featuresCol()))), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()))}))).rdd().map((x0$1) -> {
               if (x0$1 != null) {
                  Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
                  if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                     Object f = ((SeqOps)var3.get()).apply(0);
                     Object w = ((SeqOps)var3.get()).apply(1);
                     if (f instanceof Vector) {
                        Vector var6 = (Vector)f;
                        if (w instanceof Double) {
                           double var7 = BoxesRunTime.unboxToDouble(w);
                           return new Tuple2(Vectors$.MODULE$.fromML(var6), BoxesRunTime.boxToDouble(var7));
                        }
                     }
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).setName("training instances");
            StorageLevel var10000 = dataset.storageLevel();
            StorageLevel var6 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var10000 == null) {
               if (var6 == null) {
                  break label16;
               }
            } else if (var10000.equals(var6)) {
               break label16;
            }

            var7 = false;
            break label17;
         }

         var7 = true;
      }

      boolean handlePersistence = var7;
      return algo.runWithWeight(instances, handlePersistence, new Some(instr));
   }

   private org.apache.spark.mllib.clustering.KMeansModel trainWithBlock(final Dataset dataset, final Instrumentation instr) {
      label82: {
         StorageLevel var10000 = dataset.storageLevel();
         StorageLevel var4 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
         if (var10000 == null) {
            if (var4 == null) {
               break label82;
            }
         } else if (var10000.equals(var4)) {
            break label82;
         }

         instr.logWarning((Function0)(() -> "Input vectors will be blockified to blocks, and then cached during training. Be careful of double caching!"));
      }

      Vector[] centers;
      int numFeatures;
      RDD var36;
      label75: {
         label86: {
            long initStartTime = System.currentTimeMillis();
            centers = this.initialize(dataset);
            long initTimeMs = System.currentTimeMillis() - initStartTime;
            instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Initialization with ", " took "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INIT_MODE..MODULE$, this.$(this.initMode()))}))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", " ms."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(initTimeMs))}))))));
            numFeatures = ((Vector)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])centers))).size();
            instr.logNumFeatures((long)numFeatures);
            String var12 = (String)this.$(this.distanceMeasure());
            String var34 = KMeans$.MODULE$.EUCLIDEAN();
            if (var34 == null) {
               if (var12 == null) {
                  break label86;
               }
            } else if (var34.equals(var12)) {
               break label86;
            }

            var34 = KMeans$.MODULE$.COSINE();
            if (var34 == null) {
               if (var12 != null) {
                  throw new MatchError(var12);
               }
            } else if (!var34.equals(var12)) {
               throw new MatchError(var12);
            }

            var36 = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkNonNanVectors(DatasetUtils$.MODULE$.columnToVector(dataset, (String)this.$(this.featuresCol()))), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()))}))).rdd().map((x0$2) -> {
               if (x0$2 != null) {
                  Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$2);
                  if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                     Object features = ((SeqOps)var3.get()).apply(0);
                     Object weight = ((SeqOps)var3.get()).apply(1);
                     if (features instanceof Vector) {
                        Vector var6 = (Vector)features;
                        if (weight instanceof Double) {
                           double var7 = BoxesRunTime.unboxToDouble(weight);
                           return new Instance((double)1.0F, var7, org.apache.spark.ml.linalg.Vectors..MODULE$.normalize(var6, (double)2.0F));
                        }
                     }
                  }
               }

               throw new MatchError(x0$2);
            }, scala.reflect.ClassTag..MODULE$.apply(Instance.class));
            break label75;
         }

         var36 = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkNonNanVectors(DatasetUtils$.MODULE$.columnToVector(dataset, (String)this.$(this.featuresCol()))), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol()))}))).rdd().map((x0$1) -> {
            if (x0$1 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                  Object features = ((SeqOps)var3.get()).apply(0);
                  Object weight = ((SeqOps)var3.get()).apply(1);
                  if (features instanceof Vector) {
                     Vector var6 = (Vector)features;
                     if (weight instanceof Double) {
                        double var7 = BoxesRunTime.unboxToDouble(weight);
                        return new Instance(org.apache.spark.ml.linalg.BLAS..MODULE$.dot(var6, var6), var7, var6);
                     }
                  }
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Instance.class));
      }

      RDD instances = var36;
      double actualBlockSizeInMB = BoxesRunTime.unboxToDouble(this.$(this.maxBlockSizeInMB()));
      if (actualBlockSizeInMB == (double)0) {
         actualBlockSizeInMB = InstanceBlock$.MODULE$.DefaultBlockSizeInMB();
         scala.Predef..MODULE$.require(actualBlockSizeInMB > (double)0, () -> "inferred actual BlockSizeInMB must > 0");
         instr.logNamedValue("actualBlockSizeInMB", Double.toString(actualBlockSizeInMB));
      }

      long maxMemUsage = (long)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(actualBlockSizeInMB * (double)1024L * (double)1024L));
      var36 = InstanceBlock$.MODULE$.blokifyWithMaxMemUsage(instances, maxMemUsage).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      String var10001 = this.uid();
      RDD blocks = var36.setName(var10001 + ": training blocks (blockSizeInMB=" + actualBlockSizeInMB + ")");
      Function2 distanceFunction = this.getDistanceFunction();
      SparkContext sc = dataset.sparkSession().sparkContext();
      long iterationStartTime = System.currentTimeMillis();
      BooleanRef converged = BooleanRef.create(false);
      DoubleRef cost = DoubleRef.create((double)0.0F);

      IntRef iteration;
      for(iteration = IntRef.create(0); iteration.elem < BoxesRunTime.unboxToInt(this.$(this.maxIter())) && !converged.elem; ++iteration.elem) {
         Broadcast bcCenters = sc.broadcast(org.apache.spark.ml.linalg.DenseMatrix..MODULE$.fromVectors(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(centers).toImmutableArraySeq()), scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
         LongAccumulator countSumAccum = iteration.elem == 0 ? sc.longAccumulator() : null;
         DoubleAccumulator weightSumAccum = iteration.elem == 0 ? sc.doubleAccumulator() : null;
         DoubleAccumulator costSumAccum = sc.doubleAccumulator();
         Map newCenters = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(blocks.mapPartitions((iter) -> {
            if (iter.nonEmpty()) {
               KMeansAggregator agg = new KMeansAggregator((DenseMatrix)bcCenters.value(), BoxesRunTime.unboxToInt(this.$(this.k())), numFeatures, (String)this.$(this.distanceMeasure()));
               iter.foreach((block) -> agg.add(block));
               if (iteration.elem == 0) {
                  countSumAccum.add(agg.count());
                  weightSumAccum.add(agg.weightSum());
               }

               costSumAccum.add(agg.costSum());
               return agg.weightSumVec().iterator().zip(agg.sumMat().rowIter()).flatMap((x0$3) -> {
                  if (x0$3 != null) {
                     Tuple2 var3 = (Tuple2)x0$3._1();
                     Vector vectorSum = (Vector)x0$3._2();
                     if (var3 != null) {
                        int i = var3._1$mcI$sp();
                        double weightSum = var3._2$mcD$sp();
                        if (weightSum > (double)0) {
                           return new Some(new Tuple2(BoxesRunTime.boxToInteger(i), new Tuple2(BoxesRunTime.boxToDouble(weightSum), vectorSum.toDense())));
                        }

                        return scala.None..MODULE$;
                     }
                  }

                  throw new MatchError(x0$3);
               });
            } else {
               return scala.package..MODULE$.Iterator().empty();
            }
         }, blocks.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).reduceByKey((sum1, sum2) -> {
            org.apache.spark.ml.linalg.BLAS..MODULE$.axpy((double)1.0F, (Vector)sum2._2(), (Vector)sum1._2());
            return new Tuple2(BoxesRunTime.boxToDouble(sum1._1$mcD$sp() + sum2._1$mcD$sp()), sum1._2());
         }), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).mapValues((x0$4) -> {
            if (x0$4 == null) {
               throw new MatchError(x0$4);
            } else {
               double weightSum = x0$4._1$mcD$sp();
               DenseVector vectorSum = (DenseVector)x0$4._2();
               org.apache.spark.ml.linalg.BLAS..MODULE$.scal((double)1.0F / weightSum, vectorSum);
               String var8 = (String)this.$(this.distanceMeasure());
               String var10000 = KMeans$.MODULE$.COSINE();
               if (var10000 == null) {
                  if (var8 == null) {
                     return org.apache.spark.ml.linalg.Vectors..MODULE$.normalize(vectorSum, (double)2.0F);
                  }
               } else if (var10000.equals(var8)) {
                  return org.apache.spark.ml.linalg.Vectors..MODULE$.normalize(vectorSum, (double)2.0F);
               }

               return vectorSum;
            }
         }), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Vector.class), scala.math.Ordering.Int..MODULE$).collectAsMap();
         bcCenters.destroy();
         if (iteration.elem == 0) {
            instr.logNumExamples(scala.Predef..MODULE$.Long2long(countSumAccum.value()));
            instr.logSumOfWeights(scala.Predef..MODULE$.Double2double(weightSumAccum.value()));
         }

         converged.elem = true;
         newCenters.foreach((x0$5) -> {
            $anonfun$trainWithBlock$11(this, converged, distanceFunction, centers, x0$5);
            return BoxedUnit.UNIT;
         });
         cost.elem = scala.Predef..MODULE$.Double2double(costSumAccum.value());
      }

      blocks.unpersist(blocks.unpersist$default$1());
      long iterationTimeMs = System.currentTimeMillis() - iterationStartTime;
      instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Iterations took ", " ms."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(iterationTimeMs))})))));
      if (iteration.elem == BoxesRunTime.unboxToInt(this.$(this.maxIter()))) {
         instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"KMeans reached the max number of iterations: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, this.$(this.maxIter()))}))))));
      } else {
         instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"KMeans converged in ", " iterations."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(iteration.elem))})))));
      }

      instr.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"The cost is ", "."})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COST..MODULE$, BoxesRunTime.boxToDouble(cost.elem))})))));
      return new org.apache.spark.mllib.clustering.KMeansModel((org.apache.spark.mllib.linalg.Vector[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])centers), (v) -> Vectors$.MODULE$.fromML(v), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.linalg.Vector.class)), (String)this.$(this.distanceMeasure()), cost.elem, iteration.elem);
   }

   private Function2 getDistanceFunction() {
      String var2 = (String)this.$(this.distanceMeasure());
      String var10000 = KMeans$.MODULE$.EUCLIDEAN();
      if (var10000 == null) {
         if (var2 == null) {
            return (v1, v2) -> BoxesRunTime.boxToDouble($anonfun$getDistanceFunction$1(v1, v2));
         }
      } else if (var10000.equals(var2)) {
         return (v1, v2) -> BoxesRunTime.boxToDouble($anonfun$getDistanceFunction$1(v1, v2));
      }

      var10000 = KMeans$.MODULE$.COSINE();
      if (var10000 == null) {
         if (var2 == null) {
            return (v1, v2) -> BoxesRunTime.boxToDouble($anonfun$getDistanceFunction$2(v1, v2));
         }
      } else if (var10000.equals(var2)) {
         return (v1, v2) -> BoxesRunTime.boxToDouble($anonfun$getDistanceFunction$2(v1, v2));
      }

      throw new MatchError(var2);
   }

   private Vector[] initialize(final Dataset dataset) {
      org.apache.spark.mllib.clustering.KMeans algo = (new org.apache.spark.mllib.clustering.KMeans()).setK(BoxesRunTime.unboxToInt(this.$(this.k()))).setInitializationMode((String)this.$(this.initMode())).setInitializationSteps(BoxesRunTime.unboxToInt(this.$(this.initSteps()))).setMaxIterations(BoxesRunTime.unboxToInt(this.$(this.maxIter()))).setSeed(BoxesRunTime.unboxToLong(this.$(this.seed()))).setEpsilon(BoxesRunTime.unboxToDouble(this.$(this.tol()))).setDistanceMeasure((String)this.$(this.distanceMeasure()));
      RDD vectors = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.columnToVector(dataset, this.getFeaturesCol())}))).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(1) == 0) {
               Object features = ((SeqOps)var3.get()).apply(0);
               if (features instanceof Vector) {
                  Vector var5 = (Vector)features;
                  return Vectors$.MODULE$.fromML(var5);
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.linalg.Vector.class));
      Vector[] centers = (Vector[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(algo.initialize(vectors)), (x$8) -> x$8.asML(), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
      String var6 = (String)this.$(this.distanceMeasure());
      String var10000 = KMeans$.MODULE$.EUCLIDEAN();
      if (var10000 == null) {
         if (var6 == null) {
            return centers;
         }
      } else if (var10000.equals(var6)) {
         return centers;
      }

      var10000 = KMeans$.MODULE$.COSINE();
      if (var10000 == null) {
         if (var6 == null) {
            return (Vector[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])centers), (x$9) -> org.apache.spark.ml.linalg.Vectors..MODULE$.normalize(x$9, (double)2.0F), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         }
      } else if (var10000.equals(var6)) {
         return (Vector[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])centers), (x$9) -> org.apache.spark.ml.linalg.Vectors..MODULE$.normalize(x$9, (double)2.0F), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
      }

      throw new MatchError(var6);
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema);
   }

   // $FF: synthetic method
   public static final int $anonfun$preferBlockSolver$1(final Tuple2 x$5) {
      return x$5._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final BigDecimal $anonfun$preferBlockSolver$2(final BigDecimal x$6, final int x$7) {
      return x$6.$plus(scala.math.BigDecimal..MODULE$.int2bigDecimal(x$7));
   }

   // $FF: synthetic method
   public static final void $anonfun$trainWithBlock$11(final KMeans $this, final BooleanRef converged$1, final Function2 distanceFunction$1, final Vector[] centers$1, final Tuple2 x0$5) {
      if (x0$5 != null) {
         int i = x0$5._1$mcI$sp();
         Vector newCenter = (Vector)x0$5._2();
         if (converged$1.elem && BoxesRunTime.unboxToDouble(distanceFunction$1.apply(centers$1[i], newCenter)) > BoxesRunTime.unboxToDouble($this.$($this.tol()))) {
            converged$1.elem = false;
         }

         centers$1[i] = newCenter;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$5);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$getDistanceFunction$1(final Vector v1, final Vector v2) {
      return scala.math.package..MODULE$.sqrt(org.apache.spark.ml.linalg.Vectors..MODULE$.sqdist(v1, v2));
   }

   // $FF: synthetic method
   public static final double $anonfun$getDistanceFunction$2(final Vector v1, final Vector v2) {
      double norm1 = org.apache.spark.ml.linalg.Vectors..MODULE$.norm(v1, (double)2.0F);
      double norm2 = org.apache.spark.ml.linalg.Vectors..MODULE$.norm(v2, (double)2.0F);
      scala.Predef..MODULE$.require(norm1 > (double)0 && norm2 > (double)0, () -> "Cosine distance is not defined for zero-length vectors.");
      return (double)1 - org.apache.spark.ml.linalg.BLAS..MODULE$.dot(v1, v2) / norm1 / norm2;
   }

   public KMeans(final String uid) {
      this.uid = uid;
      HasMaxIter.$init$(this);
      HasFeaturesCol.$init$(this);
      HasSeed.$init$(this);
      HasPredictionCol.$init$(this);
      HasTol.$init$(this);
      HasDistanceMeasure.$init$(this);
      HasWeightCol.$init$(this);
      HasSolver.$init$(this);
      HasMaxBlockSizeInMB.$init$(this);
      KMeansParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public KMeans() {
      this(Identifiable$.MODULE$.randomUID("kmeans"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
