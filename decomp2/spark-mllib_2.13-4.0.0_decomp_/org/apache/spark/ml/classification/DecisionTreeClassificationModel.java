package org.apache.spark.ml.classification;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.tree.DecisionTreeClassifierParams;
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.DecisionTreeModelReadWrite;
import org.apache.spark.ml.tree.DecisionTreeModelReadWrite$;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.Node;
import org.apache.spark.ml.tree.Node$;
import org.apache.spark.ml.tree.TreeClassifierParams;
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
   bytes = "\u0006\u0005\t-f\u0001B\u0014)\u0001MB\u0001\u0002\u0018\u0001\u0003\u0006\u0004%\t%\u0018\u0005\t_\u0002\u0011\t\u0011)A\u0005=\"A\u0011\u000f\u0001BC\u0002\u0013\u0005#\u000f\u0003\u0005x\u0001\t\u0005\t\u0015!\u0003t\u0011!I\bA!b\u0001\n\u0003R\b\"CA\u0003\u0001\t\u0005\t\u0015!\u0003|\u0011%\tI\u0001\u0001BC\u0002\u0013\u0005#\u0010C\u0005\u0002\u0012\u0001\u0011\t\u0011)A\u0005w\"A\u0011Q\u0003\u0001\u0005\u0002)\n9\u0002\u0003\u0005\u0002\u0016\u0001!\tAKA\u0015\u0011!\t)\u0002\u0001C\u0001U\u0005E\u0002bBA\u001a\u0001\u0011\u0005\u0013Q\u0007\u0005\b\u0003\u0003\u0002A\u0011IA\"\u0011\u001d\ty\u0006\u0001C!\u0003CBq!a)\u0001\t\u0003\n)\u000bC\u0004\u0002,\u0002!\t&!,\t\u000f\u0005M\u0006\u0001\"\u0011\u00026\"9\u0011\u0011\u001a\u0001\u0005B\u0005-\u0007BCAh\u0001!\u0015\r\u0011\"\u0001\u0002R\"A\u0011\u0011\u001c\u0001\u0005B1\nY\u000eC\u0004\u0002n\u0002!\t%a<\b\u000f\u0005m\b\u0006#\u0001\u0002~\u001a1q\u0005\u000bE\u0001\u0003\u007fDq!!\u0006\u0018\t\u0003\u0011Y\u0002C\u0004\u0003\u001e]!\tEa\b\t\u000f\t%r\u0003\"\u0011\u0003,\u00199!1G\f\u0001/\tU\u0002\"\u0003B\u001c7\t\u0005\t\u0015!\u0003?\u0011\u001d\t)b\u0007C\u0001\u0005sAqA!\u0011\u001c\t#\u0012\u0019E\u0002\u0004\u0003N]!!q\n\u0005\b\u0003+yB\u0011\u0001B)\u0011%\u0011)f\bb\u0001\n\u0013\u00119\u0006\u0003\u0005\u0003d}\u0001\u000b\u0011\u0002B-\u0011\u001d\u0011Ic\bC!\u0005KB\u0001B!\u001b\u0018\t\u0003Q#1\u000e\u0005\u000b\u0005\u000f;\u0012\u0013!C\u0001U\t%\u0005\"\u0003BO/\u0005\u0005I\u0011\u0002BP\u0005}!UmY5tS>tGK]3f\u00072\f7o]5gS\u000e\fG/[8o\u001b>$W\r\u001c\u0006\u0003S)\nab\u00197bgNLg-[2bi&|gN\u0003\u0002,Y\u0005\u0011Q\u000e\u001c\u0006\u0003[9\nQa\u001d9be.T!a\f\u0019\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0014aA8sO\u000e\u00011C\u0002\u00015\u007f\u0015Ce\n\u0005\u00036marT\"\u0001\u0015\n\u0005]B#\u0001\t)s_\n\f'-\u001b7jgRL7m\u00117bgNLg-[2bi&|g.T8eK2\u0004\"!\u000f\u001f\u000e\u0003iR!a\u000f\u0016\u0002\r1Lg.\u00197h\u0013\ti$H\u0001\u0004WK\u000e$xN\u001d\t\u0003k\u0001\u0001\"\u0001Q\"\u000e\u0003\u0005S!A\u0011\u0016\u0002\tQ\u0014X-Z\u0005\u0003\t\u0006\u0013\u0011\u0003R3dSNLwN\u001c+sK\u0016lu\u000eZ3m!\t\u0001e)\u0003\u0002H\u0003\naB)Z2jg&|g\u000e\u0016:fK\u000ec\u0017m]:jM&,'\u000fU1sC6\u001c\bCA%M\u001b\u0005Q%BA&+\u0003\u0011)H/\u001b7\n\u00055S%AC'M/JLG/\u00192mKB\u0011q*\u0017\b\u0003!Zs!!\u0015+\u000e\u0003IS!a\u0015\u001a\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0016!B:dC2\f\u0017BA,Y\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011!V\u0005\u00035n\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!a\u0016-\u0002\u0007ULG-F\u0001_!\ty6M\u0004\u0002aCB\u0011\u0011\u000bW\u0005\u0003Eb\u000ba\u0001\u0015:fI\u00164\u0017B\u00013f\u0005\u0019\u0019FO]5oO*\u0011!\r\u0017\u0015\u0004\u0003\u001dl\u0007C\u00015l\u001b\u0005I'B\u00016-\u0003)\tgN\\8uCRLwN\\\u0005\u0003Y&\u0014QaU5oG\u0016\f\u0013A\\\u0001\u0006c9\"d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003O6\f\u0001B]8pi:{G-Z\u000b\u0002gB\u0011\u0001\t^\u0005\u0003k\u0006\u0013AAT8eK\"\u001a1aZ7\u0002\u0013I|w\u000e\u001e(pI\u0016\u0004\u0003f\u0001\u0003h[\u0006Ya.^7GK\u0006$XO]3t+\u0005Y\bC\u0001?~\u001b\u0005A\u0016B\u0001@Y\u0005\rIe\u000e\u001e\u0015\u0005\u000b\u001d\f\t!\t\u0002\u0002\u0004\u0005)\u0011G\f\u001c/a\u0005aa.^7GK\u0006$XO]3tA!\"aaZA\u0001\u0003)qW/\\\"mCN\u001cXm\u001d\u0015\u0005\u000f\u001d\fi!\t\u0002\u0002\u0010\u0005)\u0011GL\u001b/a\u0005Ya.^7DY\u0006\u001c8/Z:!Q\u0011Aq-!\u0004\u0002\rqJg.\u001b;?)%q\u0014\u0011DA\u000f\u0003C\t)\u0003C\u0003]\u0013\u0001\u0007a\f\u000b\u0003\u0002\u001a\u001dl\u0007\"B9\n\u0001\u0004\u0019\b\u0006BA\u000fO6DQ!_\u0005A\u0002mDS!!\th\u0003\u0003Aa!!\u0003\n\u0001\u0004Y\b&BA\u0013O\u00065Ac\u0002 \u0002,\u00055\u0012q\u0006\u0005\u0006c*\u0001\ra\u001d\u0005\u0006s*\u0001\ra\u001f\u0005\u0007\u0003\u0013Q\u0001\u0019A>\u0015\u0003y\nq\u0001\u001d:fI&\u001cG\u000f\u0006\u0003\u00028\u0005u\u0002c\u0001?\u0002:%\u0019\u00111\b-\u0003\r\u0011{WO\u00197f\u0011\u0019\ty\u0004\u0004a\u0001q\u0005Aa-Z1ukJ,7/A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\t)%!\u0016\u0011\t\u0005\u001d\u0013\u0011K\u0007\u0003\u0003\u0013RA!a\u0013\u0002N\u0005)A/\u001f9fg*\u0019\u0011q\n\u0017\u0002\u0007M\fH.\u0003\u0003\u0002T\u0005%#AC*ueV\u001cG\u000fV=qK\"9\u0011qK\u0007A\u0002\u0005\u0015\u0013AB:dQ\u0016l\u0017\r\u000b\u0003\u000eO\u0006m\u0013EAA/\u0003\u0015\u0019d\u0006\r\u00181\u0003%!(/\u00198tM>\u0014X\u000e\u0006\u0003\u0002d\u0005}\u0004\u0003BA3\u0003srA!a\u001a\u0002x9!\u0011\u0011NA;\u001d\u0011\tY'a\u001d\u000f\t\u00055\u0014\u0011\u000f\b\u0004#\u0006=\u0014\"A\u0019\n\u0005=\u0002\u0014BA\u0017/\u0013\r\ty\u0005L\u0005\u0004/\u00065\u0013\u0002BA>\u0003{\u0012\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0007]\u000bi\u0005C\u0004\u0002\u0002:\u0001\r!a!\u0002\u000f\u0011\fG/Y:fiB\"\u0011QQAI!\u0019\t9)!#\u0002\u000e6\u0011\u0011QJ\u0005\u0005\u0003\u0017\u000biEA\u0004ECR\f7/\u001a;\u0011\t\u0005=\u0015\u0011\u0013\u0007\u0001\t1\t\u0019*a \u0002\u0002\u0003\u0005)\u0011AAK\u0005\ryFEM\t\u0005\u0003/\u000bi\nE\u0002}\u00033K1!a'Y\u0005\u001dqu\u000e\u001e5j]\u001e\u00042\u0001`AP\u0013\r\t\t\u000b\u0017\u0002\u0004\u0003:L\u0018A\u00039sK\u0012L7\r\u001e*boR\u0019\u0001(a*\t\r\u0005}r\u00021\u00019Q\u0011yq-a\u0017\u0002-I\fwO\r9s_\n\f'-\u001b7jifLe\u000e\u00157bG\u0016$2\u0001OAX\u0011\u0019\t\t\f\u0005a\u0001q\u0005i!/Y<Qe\u0016$\u0017n\u0019;j_:\fAaY8qsR\u0019a(a.\t\u000f\u0005e\u0016\u00031\u0001\u0002<\u0006)Q\r\u001f;sCB!\u0011QXAb\u001b\t\tyLC\u0002\u0002B*\nQ\u0001]1sC6LA!!2\u0002@\nA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\u0012O6\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002=\"\u001a!cZ7\u0002%\u0019,\u0017\r^;sK&k\u0007o\u001c:uC:\u001cWm]\u000b\u0002q!\"1cZAkC\t\t9.A\u00033]Ar\u0003'A\u0003u_>cG-\u0006\u0002\u0002^B!\u0011q\\Av\u001b\t\t\tO\u0003\u0003\u0002d\u0006\u0015\u0018!B7pI\u0016d'b\u0001\"\u0002h*\u0019\u0011\u0011\u001e\u0017\u0002\u000b5dG.\u001b2\n\u0007\u0011\u000b\t/A\u0003xe&$X-\u0006\u0002\u0002rB\u0019\u0011*a=\n\u0007\u0005U(J\u0001\u0005N\u0019^\u0013\u0018\u000e^3sQ\u0011)r-!6)\u0007\u00019W.A\u0010EK\u000eL7/[8o)J,Wm\u00117bgNLg-[2bi&|g.T8eK2\u0004\"!N\f\u0014\u000f]\u0011\tAa\u0002\u0003\u000eA\u0019APa\u0001\n\u0007\t\u0015\u0001L\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0013\n%a(C\u0002\u0003\f)\u0013!\"\u0014'SK\u0006$\u0017M\u00197f!\u0011\u0011yA!\u0007\u000e\u0005\tE!\u0002\u0002B\n\u0005+\t!![8\u000b\u0005\t]\u0011\u0001\u00026bm\u0006L1A\u0017B\t)\t\ti0\u0001\u0003sK\u0006$WC\u0001B\u0011!\u0011I%1\u0005 \n\u0007\t\u0015\"J\u0001\u0005N\u0019J+\u0017\rZ3sQ\u0011Ir-!6\u0002\t1|\u0017\r\u001a\u000b\u0004}\t5\u0002B\u0002B\u00185\u0001\u0007a,\u0001\u0003qCRD\u0007\u0006\u0002\u000eh\u0003+\u0014Q\u0005R3dSNLwN\u001c+sK\u0016\u001cE.Y:tS\u001aL7-\u0019;j_:lu\u000eZ3m/JLG/\u001a:\u0014\u0007m\t\t0\u0001\u0005j]N$\u0018M\\2f)\u0011\u0011YDa\u0010\u0011\u0007\tu2$D\u0001\u0018\u0011\u0019\u00119$\ba\u0001}\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0003F\t-\u0003c\u0001?\u0003H%\u0019!\u0011\n-\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0005_q\u0002\u0019\u00010\u0003K\u0011+7-[:j_:$&/Z3DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8N_\u0012,GNU3bI\u0016\u00148cA\u0010\u0003\"Q\u0011!1\u000b\t\u0004\u0005{y\u0012!C2mCN\u001ch*Y7f+\t\u0011I\u0006\u0005\u0003\u0003\\\t\u0005TB\u0001B/\u0015\u0011\u0011yF!\u0006\u0002\t1\fgnZ\u0005\u0004I\nu\u0013AC2mCN\u001ch*Y7fAQ\u0019aHa\u001a\t\r\t=2\u00051\u0001_\u0003\u001d1'o\\7PY\u0012$\u0012B\u0010B7\u0005c\u0012YH!\"\t\u000f\t=D\u00051\u0001\u0002^\u0006Aq\u000e\u001c3N_\u0012,G\u000eC\u0004\u0003t\u0011\u0002\rA!\u001e\u0002\rA\f'/\u001a8u!\r)$qO\u0005\u0004\u0005sB#A\u0006#fG&\u001c\u0018n\u001c8Ue\u0016,7\t\\1tg&4\u0017.\u001a:\t\u000f\tuD\u00051\u0001\u0003\u0000\u0005\u00192-\u0019;fO>\u0014\u0018nY1m\r\u0016\fG/\u001e:fgB)qL!!|w&\u0019!1Q3\u0003\u00075\u000b\u0007\u000fC\u0004zIA\u0005\t\u0019A>\u0002#\u0019\u0014x.\\(mI\u0012\"WMZ1vYR$C'\u0006\u0002\u0003\f*\u001a1P!$,\u0005\t=\u0005\u0003\u0002BI\u00053k!Aa%\u000b\t\tU%qS\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u001b-\n\t\tm%1\u0013\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001BQ!\u0011\u0011YFa)\n\t\t\u0015&Q\f\u0002\u0007\u001f\nTWm\u0019;)\t]9\u0017Q\u001b\u0015\u0005-\u001d\f)\u000e"
)
public class DecisionTreeClassificationModel extends ProbabilisticClassificationModel implements DecisionTreeModel, DecisionTreeClassifierParams, MLWritable {
   private Vector featureImportances;
   private final String uid;
   private final Node rootNode;
   private final int numFeatures;
   private final int numClasses;
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

   public static DecisionTreeClassificationModel load(final String path) {
      return DecisionTreeClassificationModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return DecisionTreeClassificationModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$DecisionTreeClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ProbabilisticClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return DecisionTreeClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final String getImpurity() {
      return TreeClassifierParams.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return TreeClassifierParams.getOldImpurity$(this);
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

   public final Param impurity() {
      return this.impurity;
   }

   public final void org$apache$spark$ml$tree$TreeClassifierParams$_setter_$impurity_$eq(final Param x$1) {
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

   public int numClasses() {
      return this.numClasses;
   }

   public double predict(final Vector features) {
      return this.rootNode().predictImpl(features).prediction();
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = super.transformSchema(schema);
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateField(outputSchema, this.getLeafField((String)this.$(this.leafCol())), SchemaUtils$.MODULE$.updateField$default$3());
      }

      return outputSchema;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Dataset outputData = super.transform(dataset);
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> BoxesRunTime.boxToDouble($anonfun$transform$1(this, features));
         TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DecisionTreeClassificationModel.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         UserDefinedFunction leafUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
         return outputData.withColumn((String)this.$(this.leafCol()), leafUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))), outputSchema.apply((String)this.$(this.leafCol())).metadata());
      } else {
         return outputData;
      }
   }

   public Vector predictRaw(final Vector features) {
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])this.rootNode().predictImpl(features).impurityStats().stats().clone());
   }

   public Vector raw2probabilityInPlace(final Vector rawPrediction) {
      if (rawPrediction instanceof DenseVector var4) {
         ProbabilisticClassificationModel$.MODULE$.normalizeToProbabilitiesInPlace(var4);
         return var4;
      } else if (rawPrediction instanceof SparseVector) {
         throw new RuntimeException("Unexpected error in DecisionTreeClassificationModel: raw2probabilityInPlace encountered SparseVector");
      } else {
         throw new MatchError(rawPrediction);
      }
   }

   public DecisionTreeClassificationModel copy(final ParamMap extra) {
      return (DecisionTreeClassificationModel)((Model)this.copyValues(new DecisionTreeClassificationModel(this.uid(), this.rootNode(), this.numFeatures(), this.numClasses()), extra)).setParent(this.parent());
   }

   public String toString() {
      String var10000 = this.uid();
      return "DecisionTreeClassificationModel: uid=" + var10000 + ", depth=" + this.depth() + ", numNodes=" + this.numNodes() + ", numClasses=" + this.numClasses() + ", numFeatures=" + this.numFeatures();
   }

   private Vector featureImportances$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.featureImportances = TreeEnsembleModel$.MODULE$.featureImportances(this, this.numFeatures(), scala.reflect.ClassTag..MODULE$.apply(DecisionTreeClassificationModel.class));
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
      return new org.apache.spark.mllib.tree.model.DecisionTreeModel(this.rootNode().toOld(1), Algo$.MODULE$.Classification());
   }

   public MLWriter write() {
      return new DecisionTreeClassificationModelWriter(this);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$1(final DecisionTreeClassificationModel $this, final Vector features) {
      return $this.predictLeaf(features);
   }

   public DecisionTreeClassificationModel(final String uid, final Node rootNode, final int numFeatures, final int numClasses) {
      this.uid = uid;
      this.rootNode = rootNode;
      this.numFeatures = numFeatures;
      this.numClasses = numClasses;
      DecisionTreeModel.$init$(this);
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      TreeClassifierParams.$init$(this);
      DecisionTreeClassifierParams.$init$(this);
      MLWritable.$init$(this);
      scala.Predef..MODULE$.require(rootNode != null, () -> "DecisionTreeClassificationModel given null rootNode, but it requires a non-null rootNode.");
      Statics.releaseFence();
   }

   public DecisionTreeClassificationModel(final Node rootNode, final int numFeatures, final int numClasses) {
      this(Identifiable$.MODULE$.randomUID("dtc"), rootNode, numFeatures, numClasses);
   }

   public DecisionTreeClassificationModel() {
      this("", Node$.MODULE$.dummyNode(), -1, -1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class DecisionTreeClassificationModelWriter extends MLWriter {
      private final DecisionTreeClassificationModel instance;

      public void saveImpl(final String path) {
         JObject extraMetadata = org.json4s.JsonDSL..MODULE$.map2jvalue((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numFeatures"), BoxesRunTime.boxToInteger(this.instance.numFeatures())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numClasses"), BoxesRunTime.boxToInteger(this.instance.numClasses()))}))), (x) -> $anonfun$saveImpl$1(BoxesRunTime.unboxToInt(x)));
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession(), new Some(extraMetadata));
         Tuple2 var5 = DecisionTreeModelReadWrite.NodeData$.MODULE$.build(this.instance.rootNode(), 0);
         if (var5 != null) {
            Seq nodeData = (Seq)var5._1();
            String dataPath = (new Path(path, "data")).toString();
            int numDataParts = DecisionTreeModelReadWrite.NodeData$.MODULE$.inferNumPartitions((long)this.instance.numNodes());
            SparkSession var10000 = this.sparkSession();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(DecisionTreeClassificationModelWriter.class.getClassLoader());

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

      public DecisionTreeClassificationModelWriter(final DecisionTreeClassificationModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class DecisionTreeClassificationModelReader extends MLReader {
      private final String className = DecisionTreeClassificationModel.class.getName();

      private String className() {
         return this.className;
      }

      public DecisionTreeClassificationModel load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         int numFeatures = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numFeatures")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
         int numClasses = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numClasses")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
         Node root = DecisionTreeModelReadWrite$.MODULE$.loadTreeNodes(path, metadata, this.sparkSession());
         DecisionTreeClassificationModel model = new DecisionTreeClassificationModel(metadata.uid(), root, numFeatures, numClasses);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public DecisionTreeClassificationModelReader() {
      }
   }
}
