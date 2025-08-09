package org.apache.spark.ml.regression;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasVarianceCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.DecisionTreeModelReadWrite;
import org.apache.spark.ml.tree.DecisionTreeModelReadWrite$;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.DecisionTreeRegressorParams;
import org.apache.spark.ml.tree.HasVarianceImpurity;
import org.apache.spark.ml.tree.Node;
import org.apache.spark.ml.tree.Node$;
import org.apache.spark.ml.tree.TreeEnsembleModel$;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.json4s.DefaultFormats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Enumeration;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tud\u0001B\u0013'\u0001EB\u0001B\u0017\u0001\u0003\u0006\u0004%\te\u0017\u0005\tI\u0002\u0011\t\u0011)A\u00059\"AQ\r\u0001BC\u0002\u0013\u0005c\r\u0003\u0005k\u0001\t\u0005\t\u0015!\u0003h\u0011!Y\u0007A!b\u0001\n\u0003b\u0007\u0002C9\u0001\u0005\u0003\u0005\u000b\u0011B7\t\rI\u0004A\u0011\u0001\u0015t\u0011\u00159\b\u0001\"\u0001y\u0011\u0019\u0011\b\u0001\"\u0001)y\"1!\u000f\u0001C\u0001Q}Dq!!\u0001\u0001\t\u0003\n\u0019\u0001C\u0004\u0002\u0010\u0001!\t\"!\u0005\t\u000f\u0005U\u0001\u0001\"\u0011\u0002\u0018!9\u0011q\b\u0001\u0005B\u0005\u0005\u0003bBAE\u0001\u0011\u0005\u00131\u0012\u0005\b\u0003?\u0003A\u0011IAQ\u0011)\t)\u000b\u0001EC\u0002\u0013\u0005\u0011q\u0015\u0005\t\u0003W\u0003A\u0011\t\u0016\u0002.\"9\u0011q\u0018\u0001\u0005B\u0005\u0005waBAgM!\u0005\u0011q\u001a\u0004\u0007K\u0019B\t!!5\t\rI,B\u0011AAw\u0011\u001d\ty/\u0006C!\u0003cDq!a?\u0016\t\u0003\niPB\u0004\u0003\u0006U\u0001QCa\u0002\t\u0013\t%\u0011D!A!\u0002\u0013a\u0004B\u0002:\u001a\t\u0003\u0011Y\u0001C\u0004\u0003\u0014e!\tF!\u0006\u0007\r\t}Q\u0003\u0002B\u0011\u0011\u0019\u0011X\u0004\"\u0001\u0003$!I!qE\u000fC\u0002\u0013%!\u0011\u0006\u0005\t\u0005ki\u0002\u0015!\u0003\u0003,!9\u00111`\u000f\u0005B\t]\u0002\u0002\u0003B\u001e+\u0011\u0005\u0001F!\u0010\t\u0015\teS#%A\u0005\u0002!\u0012Y\u0006C\u0005\u0003pU\t\t\u0011\"\u0003\u0003r\tYB)Z2jg&|g\u000e\u0016:fKJ+wM]3tg&|g.T8eK2T!a\n\u0015\u0002\u0015I,wM]3tg&|gN\u0003\u0002*U\u0005\u0011Q\u000e\u001c\u0006\u0003W1\nQa\u001d9be.T!!\f\u0018\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0013aA8sO\u000e\u00011C\u0002\u00013{\r3E\n\u0005\u00034iYbT\"\u0001\u0014\n\u0005U2#a\u0004*fOJ,7o]5p]6{G-\u001a7\u0011\u0005]RT\"\u0001\u001d\u000b\u0005eB\u0013A\u00027j]\u0006dw-\u0003\u0002<q\t1a+Z2u_J\u0004\"a\r\u0001\u0011\u0005y\nU\"A \u000b\u0005\u0001C\u0013\u0001\u0002;sK\u0016L!AQ \u0003#\u0011+7-[:j_:$&/Z3N_\u0012,G\u000e\u0005\u0002?\t&\u0011Qi\u0010\u0002\u001c\t\u0016\u001c\u0017n]5p]R\u0013X-\u001a*fOJ,7o]8s!\u0006\u0014\u0018-\\:\u0011\u0005\u001dSU\"\u0001%\u000b\u0005%C\u0013\u0001B;uS2L!a\u0013%\u0003\u00155cuK]5uC\ndW\r\u0005\u0002N/:\u0011a\n\u0016\b\u0003\u001fJk\u0011\u0001\u0015\u0006\u0003#B\na\u0001\u0010:p_Rt\u0014\"A*\u0002\u000bM\u001c\u0017\r\\1\n\u0005U3\u0016a\u00029bG.\fw-\u001a\u0006\u0002'&\u0011\u0001,\u0017\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003+Z\u000b1!^5e+\u0005a\u0006CA/b\u001d\tqv\f\u0005\u0002P-&\u0011\u0001MV\u0001\u0007!J,G-\u001a4\n\u0005\t\u001c'AB*ue&twM\u0003\u0002a-\u0006!Q/\u001b3!\u0003!\u0011xn\u001c;O_\u0012,W#A4\u0011\u0005yB\u0017BA5@\u0005\u0011qu\u000eZ3\u0002\u0013I|w\u000e\u001e(pI\u0016\u0004\u0013a\u00038v[\u001a+\u0017\r^;sKN,\u0012!\u001c\t\u0003]>l\u0011AV\u0005\u0003aZ\u00131!\u00138u\u00031qW/\u001c$fCR,(/Z:!\u0003\u0019a\u0014N\\5u}Q!A\b^;w\u0011\u0015Qv\u00011\u0001]\u0011\u0015)w\u00011\u0001h\u0011\u0015Yw\u00011\u0001n\u00039\u0019X\r\u001e,be&\fgnY3D_2$\"!\u001f>\u000e\u0003\u0001AQa\u001f\u0005A\u0002q\u000bQA^1mk\u0016$2\u0001P?\u007f\u0011\u0015)\u0017\u00021\u0001h\u0011\u0015Y\u0017\u00021\u0001n)\u0005a\u0014a\u00029sK\u0012L7\r\u001e\u000b\u0005\u0003\u000b\tY\u0001E\u0002o\u0003\u000fI1!!\u0003W\u0005\u0019!u.\u001e2mK\"1\u0011QB\u0006A\u0002Y\n\u0001BZ3biV\u0014Xm]\u0001\u0010aJ,G-[2u-\u0006\u0014\u0018.\u00198dKR!\u0011QAA\n\u0011\u0019\ti\u0001\u0004a\u0001m\u0005yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002\u001a\u0005%\u0002\u0003BA\u000e\u0003Ki!!!\b\u000b\t\u0005}\u0011\u0011E\u0001\u0006if\u0004Xm\u001d\u0006\u0004\u0003GQ\u0013aA:rY&!\u0011qEA\u000f\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003Wi\u0001\u0019AA\r\u0003\u0019\u00198\r[3nC\"*Q\"a\f\u0002<A!\u0011\u0011GA\u001c\u001b\t\t\u0019DC\u0002\u00026)\n!\"\u00198o_R\fG/[8o\u0013\u0011\tI$a\r\u0003\u000bMKgnY3\"\u0005\u0005u\u0012!B\u0019/i9\u0002\u0014!\u0003;sC:\u001chm\u001c:n)\u0011\t\u0019%a\u0018\u0011\t\u0005\u0015\u0013\u0011\f\b\u0005\u0003\u000f\n9F\u0004\u0003\u0002J\u0005Uc\u0002BA&\u0003'rA!!\u0014\u0002R9\u0019q*a\u0014\n\u0003=J!!\f\u0018\n\u0005-b\u0013bAA\u0012U%\u0019Q+!\t\n\t\u0005m\u0013Q\f\u0002\n\t\u0006$\u0018M\u0012:b[\u0016T1!VA\u0011\u0011\u001d\t\tG\u0004a\u0001\u0003G\nq\u0001Z1uCN,G\u000f\r\u0003\u0002f\u0005E\u0004CBA4\u0003S\ni'\u0004\u0002\u0002\"%!\u00111NA\u0011\u0005\u001d!\u0015\r^1tKR\u0004B!a\u001c\u0002r1\u0001A\u0001DA:\u0003?\n\t\u0011!A\u0003\u0002\u0005U$aA0%eE!\u0011qOA?!\rq\u0017\u0011P\u0005\u0004\u0003w2&a\u0002(pi\"Lgn\u001a\t\u0004]\u0006}\u0014bAAA-\n\u0019\u0011I\\=)\u000b9\ty#!\"\"\u0005\u0005\u001d\u0015!\u0002\u001a/a9\u0002\u0014\u0001B2paf$2\u0001PAG\u0011\u001d\tyi\u0004a\u0001\u0003#\u000bQ!\u001a=ue\u0006\u0004B!a%\u0002\u001a6\u0011\u0011Q\u0013\u0006\u0004\u0003/C\u0013!\u00029be\u0006l\u0017\u0002BAN\u0003+\u0013\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0006\u001f\u0005=\u00121H\u0001\ti>\u001cFO]5oOR\tA\fK\u0003\u0011\u0003_\tY$\u0001\ngK\u0006$XO]3J[B|'\u000f^1oG\u0016\u001cX#\u0001\u001c)\u000bE\ty#!\"\u0002\u000bQ|w\n\u001c3\u0016\u0005\u0005=\u0006\u0003BAY\u0003{k!!a-\u000b\t\u0005U\u0016qW\u0001\u0006[>$W\r\u001c\u0006\u0004\u0001\u0006e&bAA^U\u0005)Q\u000e\u001c7jE&\u0019!)a-\u0002\u000b]\u0014\u0018\u000e^3\u0016\u0005\u0005\r\u0007cA$\u0002F&\u0019\u0011q\u0019%\u0003\u00115cuK]5uKJDSaEA\u0018\u0003\u000bCS\u0001AA\u0018\u0003w\t1\u0004R3dSNLwN\u001c+sK\u0016\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0007CA\u001a\u0016'\u001d)\u00121[Am\u0003?\u00042A\\Ak\u0013\r\t9N\u0016\u0002\u0007\u0003:L(+\u001a4\u0011\t\u001d\u000bY\u000eP\u0005\u0004\u0003;D%AC'M%\u0016\fG-\u00192mKB!\u0011\u0011]Av\u001b\t\t\u0019O\u0003\u0003\u0002f\u0006\u001d\u0018AA5p\u0015\t\tI/\u0001\u0003kCZ\f\u0017b\u0001-\u0002dR\u0011\u0011qZ\u0001\u0005e\u0016\fG-\u0006\u0002\u0002tB!q)!>=\u0013\r\t9\u0010\u0013\u0002\t\u001b2\u0013V-\u00193fe\"*q#a\f\u0002\u0006\u0006!An\\1e)\ra\u0014q \u0005\u0007\u0005\u0003A\u0002\u0019\u0001/\u0002\tA\fG\u000f\u001b\u0015\u00061\u0005=\u0012Q\u0011\u0002\"\t\u0016\u001c\u0017n]5p]R\u0013X-\u001a*fOJ,7o]5p]6{G-\u001a7Xe&$XM]\n\u00043\u0005\r\u0017\u0001C5ogR\fgnY3\u0015\t\t5!\u0011\u0003\t\u0004\u0005\u001fIR\"A\u000b\t\r\t%1\u00041\u0001=\u0003!\u0019\u0018M^3J[BdG\u0003\u0002B\f\u0005;\u00012A\u001cB\r\u0013\r\u0011YB\u0016\u0002\u0005+:LG\u000f\u0003\u0004\u0003\u0002q\u0001\r\u0001\u0018\u0002\"\t\u0016\u001c\u0017n]5p]R\u0013X-\u001a*fOJ,7o]5p]6{G-\u001a7SK\u0006$WM]\n\u0004;\u0005MHC\u0001B\u0013!\r\u0011y!H\u0001\nG2\f7o\u001d(b[\u0016,\"Aa\u000b\u0011\t\t5\"1G\u0007\u0003\u0005_QAA!\r\u0002h\u0006!A.\u00198h\u0013\r\u0011'qF\u0001\u000bG2\f7o\u001d(b[\u0016\u0004Cc\u0001\u001f\u0003:!1!\u0011A\u0011A\u0002q\u000bqA\u001a:p[>cG\rF\u0005=\u0005\u007f\u0011\u0019E!\u0014\u0003X!9!\u0011\t\u0012A\u0002\u0005=\u0016\u0001C8mI6{G-\u001a7\t\u000f\t\u0015#\u00051\u0001\u0003H\u00051\u0001/\u0019:f]R\u00042a\rB%\u0013\r\u0011YE\n\u0002\u0016\t\u0016\u001c\u0017n]5p]R\u0013X-\u001a*fOJ,7o]8s\u0011\u001d\u0011yE\ta\u0001\u0005#\n1cY1uK\u001e|'/[2bY\u001a+\u0017\r^;sKN\u0004R!\u0018B*[6L1A!\u0016d\u0005\ri\u0015\r\u001d\u0005\bW\n\u0002\n\u00111\u0001n\u0003E1'o\\7PY\u0012$C-\u001a4bk2$H\u0005N\u000b\u0003\u0005;R3!\u001cB0W\t\u0011\t\u0007\u0005\u0003\u0003d\t-TB\u0001B3\u0015\u0011\u00119G!\u001b\u0002\u0013Ut7\r[3dW\u0016$'bAA\u001b-&!!Q\u000eB3\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005g\u0002BA!\f\u0003v%!!q\u000fB\u0018\u0005\u0019y%M[3di\"*Q#a\f\u0002\u0006\"*A#a\f\u0002\u0006\u0002"
)
public class DecisionTreeRegressionModel extends RegressionModel implements DecisionTreeModel, DecisionTreeRegressorParams, MLWritable {
   private Vector featureImportances;
   private final String uid;
   private final Node rootNode;
   private final int numFeatures;
   private Param varianceCol;
   private Param impurity;
   private Param leafCol;
   private IntParam maxDepth;
   private IntParam maxBins;
   private IntParam minInstancesPerNode;
   private DoubleParam minWeightFractionPerNode;
   private DoubleParam minInfoGain;
   private IntParam maxMemoryInMB;
   private BooleanParam cacheNodeIds;
   private Param weightCol;
   private LongParam seed;
   private IntParam checkpointInterval;
   private int depth;
   private int numLeave;
   private NominalAttribute leafAttr;
   private transient Map org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices;
   private volatile byte bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public static DecisionTreeRegressionModel load(final String path) {
      return DecisionTreeRegressionModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return DecisionTreeRegressionModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$DecisionTreeRegressorParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return DecisionTreeRegressorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final String getVarianceCol() {
      return HasVarianceCol.getVarianceCol$(this);
   }

   public final String getImpurity() {
      return HasVarianceImpurity.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return HasVarianceImpurity.getOldImpurity$(this);
   }

   public final DecisionTreeParams setLeafCol(final String value) {
      return DecisionTreeParams.setLeafCol$(this, value);
   }

   public final String getLeafCol() {
      return DecisionTreeParams.getLeafCol$(this);
   }

   public final int getMaxDepth() {
      return DecisionTreeParams.getMaxDepth$(this);
   }

   public final int getMaxBins() {
      return DecisionTreeParams.getMaxBins$(this);
   }

   public final int getMinInstancesPerNode() {
      return DecisionTreeParams.getMinInstancesPerNode$(this);
   }

   public final double getMinWeightFractionPerNode() {
      return DecisionTreeParams.getMinWeightFractionPerNode$(this);
   }

   public final double getMinInfoGain() {
      return DecisionTreeParams.getMinInfoGain$(this);
   }

   public final int getMaxMemoryInMB() {
      return DecisionTreeParams.getMaxMemoryInMB$(this);
   }

   public final boolean getCacheNodeIds() {
      return DecisionTreeParams.getCacheNodeIds$(this);
   }

   public Strategy getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity, final double subsamplingRate) {
      return DecisionTreeParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity, subsamplingRate);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final int getCheckpointInterval() {
      return HasCheckpointInterval.getCheckpointInterval$(this);
   }

   public int numNodes() {
      return DecisionTreeModel.numNodes$(this);
   }

   public String toDebugString() {
      return DecisionTreeModel.toDebugString$(this);
   }

   public int maxSplitFeatureIndex() {
      return DecisionTreeModel.maxSplitFeatureIndex$(this);
   }

   public StructField getLeafField(final String leafCol) {
      return DecisionTreeModel.getLeafField$(this, leafCol);
   }

   public double predictLeaf(final Vector features) {
      return DecisionTreeModel.predictLeaf$(this, features);
   }

   public final Param varianceCol() {
      return this.varianceCol;
   }

   public final void org$apache$spark$ml$param$shared$HasVarianceCol$_setter_$varianceCol_$eq(final Param x$1) {
      this.varianceCol = x$1;
   }

   public final Param impurity() {
      return this.impurity;
   }

   public final void org$apache$spark$ml$tree$HasVarianceImpurity$_setter_$impurity_$eq(final Param x$1) {
      this.impurity = x$1;
   }

   public final Param leafCol() {
      return this.leafCol;
   }

   public final IntParam maxDepth() {
      return this.maxDepth;
   }

   public final IntParam maxBins() {
      return this.maxBins;
   }

   public final IntParam minInstancesPerNode() {
      return this.minInstancesPerNode;
   }

   public final DoubleParam minWeightFractionPerNode() {
      return this.minWeightFractionPerNode;
   }

   public final DoubleParam minInfoGain() {
      return this.minInfoGain;
   }

   public final IntParam maxMemoryInMB() {
      return this.maxMemoryInMB;
   }

   public final BooleanParam cacheNodeIds() {
      return this.cacheNodeIds;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$leafCol_$eq(final Param x$1) {
      this.leafCol = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxDepth_$eq(final IntParam x$1) {
      this.maxDepth = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxBins_$eq(final IntParam x$1) {
      this.maxBins = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInstancesPerNode_$eq(final IntParam x$1) {
      this.minInstancesPerNode = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minWeightFractionPerNode_$eq(final DoubleParam x$1) {
      this.minWeightFractionPerNode = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$minInfoGain_$eq(final DoubleParam x$1) {
      this.minInfoGain = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$maxMemoryInMB_$eq(final IntParam x$1) {
      this.maxMemoryInMB = x$1;
   }

   public final void org$apache$spark$ml$tree$DecisionTreeParams$_setter_$cacheNodeIds_$eq(final BooleanParam x$1) {
      this.cacheNodeIds = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public final IntParam checkpointInterval() {
      return this.checkpointInterval;
   }

   public final void org$apache$spark$ml$param$shared$HasCheckpointInterval$_setter_$checkpointInterval_$eq(final IntParam x$1) {
      this.checkpointInterval = x$1;
   }

   private int depth$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.depth = DecisionTreeModel.depth$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.depth;
   }

   public int depth() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.depth$lzycompute() : this.depth;
   }

   private int numLeave$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.numLeave = DecisionTreeModel.numLeave$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numLeave;
   }

   public int numLeave() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.numLeave$lzycompute() : this.numLeave;
   }

   private NominalAttribute leafAttr$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.leafAttr = DecisionTreeModel.leafAttr$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.leafAttr;
   }

   public NominalAttribute leafAttr() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.leafAttr$lzycompute() : this.leafAttr;
   }

   private Map org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices = DecisionTreeModel.org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices$(this);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices;
   }

   public Map org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices() {
      return !this.bitmap$trans$0 ? this.org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices$lzycompute() : this.org$apache$spark$ml$tree$DecisionTreeModel$$leafIndices;
   }

   public String uid() {
      return this.uid;
   }

   public Node rootNode() {
      return this.rootNode;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public DecisionTreeRegressionModel setVarianceCol(final String value) {
      return (DecisionTreeRegressionModel)this.set(this.varianceCol(), value);
   }

   public double predict(final Vector features) {
      return this.rootNode().predictImpl(features).prediction();
   }

   public double predictVariance(final Vector features) {
      return this.rootNode().predictImpl(features).impurityStats().calculate();
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = super.transformSchema(schema);
      if (this.isDefined(this.varianceCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.varianceCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateNumeric(outputSchema, (String)this.$(this.varianceCol()));
      }

      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateField(outputSchema, this.getLeafField((String)this.$(this.leafCol())), SchemaUtils$.MODULE$.updateField$default$3());
      }

      return outputSchema;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Seq predictionColNames = (Seq)scala.package..MODULE$.Seq().empty();
      Seq predictionColumns = (Seq)scala.package..MODULE$.Seq().empty();
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> BoxesRunTime.boxToDouble($anonfun$transform$1(this, features));
         TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DecisionTreeRegressionModel.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         UserDefinedFunction predictUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
         predictionColNames = (Seq)predictionColNames.$colon$plus(this.$(this.predictionCol()));
         predictionColumns = (Seq)predictionColumns.$colon$plus(predictUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))).as((String)this.$(this.predictionCol()), outputSchema.apply((String)this.$(this.predictionCol())).metadata()));
      }

      if (this.isDefined(this.varianceCol()) && .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.varianceCol())))) {
         functions var14 = org.apache.spark.sql.functions..MODULE$;
         Function1 var16 = (features) -> BoxesRunTime.boxToDouble($anonfun$transform$2(this, features));
         TypeTags.TypeTag var18 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DecisionTreeRegressionModel.class.getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         UserDefinedFunction predictVarianceUDF = var14.udf(var16, var18, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
         predictionColNames = (Seq)predictionColNames.$colon$plus(this.$(this.varianceCol()));
         predictionColumns = (Seq)predictionColumns.$colon$plus(predictVarianceUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))).as((String)this.$(this.varianceCol()), outputSchema.apply((String)this.$(this.varianceCol())).metadata()));
      }

      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         functions var15 = org.apache.spark.sql.functions..MODULE$;
         Function1 var17 = (features) -> BoxesRunTime.boxToDouble($anonfun$transform$3(this, features));
         TypeTags.TypeTag var19 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DecisionTreeRegressionModel.class.getClassLoader());

         final class $typecreator3$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator3$1() {
            }
         }

         UserDefinedFunction leafUDF = var15.udf(var17, var19, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1()));
         predictionColNames = (Seq)predictionColNames.$colon$plus(this.$(this.leafCol()));
         predictionColumns = (Seq)predictionColumns.$colon$plus(leafUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))).as((String)this.$(this.leafCol()), outputSchema.apply((String)this.$(this.leafCol())).metadata()));
      }

      if (predictionColNames.nonEmpty()) {
         return dataset.withColumns(predictionColNames, predictionColumns);
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": DecisionTreeRegressionModel.transform() "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"does nothing because no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return dataset.toDF();
      }
   }

   public DecisionTreeRegressionModel copy(final ParamMap extra) {
      return (DecisionTreeRegressionModel)((Model)this.copyValues(new DecisionTreeRegressionModel(this.uid(), this.rootNode(), this.numFeatures()), extra)).setParent(this.parent());
   }

   public String toString() {
      String var10000 = this.uid();
      return "DecisionTreeRegressionModel: uid=" + var10000 + ", depth=" + this.depth() + ", numNodes=" + this.numNodes() + ", numFeatures=" + this.numFeatures();
   }

   private Vector featureImportances$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.featureImportances = TreeEnsembleModel$.MODULE$.featureImportances(this, this.numFeatures(), scala.reflect.ClassTag..MODULE$.apply(DecisionTreeRegressionModel.class));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.featureImportances;
   }

   public Vector featureImportances() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.featureImportances$lzycompute() : this.featureImportances;
   }

   public org.apache.spark.mllib.tree.model.DecisionTreeModel toOld() {
      return new org.apache.spark.mllib.tree.model.DecisionTreeModel(this.rootNode().toOld(1), Algo$.MODULE$.Regression());
   }

   public MLWriter write() {
      return new DecisionTreeRegressionModelWriter(this);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$1(final DecisionTreeRegressionModel $this, final Vector features) {
      return $this.predict(features);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$2(final DecisionTreeRegressionModel $this, final Vector features) {
      return $this.predictVariance(features);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$3(final DecisionTreeRegressionModel $this, final Vector features) {
      return $this.predictLeaf(features);
   }

   public DecisionTreeRegressionModel(final String uid, final Node rootNode, final int numFeatures) {
      this.uid = uid;
      this.rootNode = rootNode;
      this.numFeatures = numFeatures;
      DecisionTreeModel.$init$(this);
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      HasVarianceImpurity.$init$(this);
      HasVarianceCol.$init$(this);
      DecisionTreeRegressorParams.$init$(this);
      MLWritable.$init$(this);
      scala.Predef..MODULE$.require(rootNode != null, () -> "DecisionTreeRegressionModel given null rootNode, but it requires a non-null rootNode.");
      Statics.releaseFence();
   }

   public DecisionTreeRegressionModel(final Node rootNode, final int numFeatures) {
      this(Identifiable$.MODULE$.randomUID("dtr"), rootNode, numFeatures);
   }

   public DecisionTreeRegressionModel() {
      this("", Node$.MODULE$.dummyNode(), -1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class DecisionTreeRegressionModelWriter extends MLWriter {
      private final DecisionTreeRegressionModel instance;

      public void saveImpl(final String path) {
         JObject extraMetadata = org.json4s.JsonDSL..MODULE$.map2jvalue((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numFeatures"), BoxesRunTime.boxToInteger(this.instance.numFeatures()))}))), (x) -> $anonfun$saveImpl$1(BoxesRunTime.unboxToInt(x)));
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession(), new Some(extraMetadata));
         Tuple2 var5 = DecisionTreeModelReadWrite.NodeData$.MODULE$.build(this.instance.rootNode(), 0);
         if (var5 != null) {
            Seq nodeData = (Seq)var5._1();
            String dataPath = (new Path(path, "data")).toString();
            int numDataParts = DecisionTreeModelReadWrite.NodeData$.MODULE$.inferNumPartitions((long)this.instance.numNodes());
            SparkSession var10000 = this.sparkSession();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DecisionTreeRegressionModelWriter.class.getClassLoader());

            final class $typecreator1$2 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("org")), $m$untyped.staticPackage("org.apache")), $m$untyped.staticPackage("org.apache.spark")), $m$untyped.staticPackage("org.apache.spark.ml")), $m$untyped.staticPackage("org.apache.spark.ml.tree")), $m$untyped.staticModule("org.apache.spark.ml.tree.DecisionTreeModelReadWrite")), $m$untyped.staticClass("org.apache.spark.ml.tree.DecisionTreeModelReadWrite.NodeData"), scala.collection.immutable.Nil..MODULE$);
               }

               public $typecreator1$2() {
               }
            }

            var10000.createDataFrame(nodeData, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).repartition(numDataParts).write().parquet(dataPath);
         } else {
            throw new MatchError(var5);
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$1(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      public DecisionTreeRegressionModelWriter(final DecisionTreeRegressionModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class DecisionTreeRegressionModelReader extends MLReader {
      private final String className = DecisionTreeRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      public DecisionTreeRegressionModel load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         int numFeatures = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numFeatures")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
         Node root = DecisionTreeModelReadWrite$.MODULE$.loadTreeNodes(path, metadata, this.sparkSession());
         DecisionTreeRegressionModel model = new DecisionTreeRegressionModel(metadata.uid(), root, numFeatures);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public DecisionTreeRegressionModelReader() {
      }
   }
}
