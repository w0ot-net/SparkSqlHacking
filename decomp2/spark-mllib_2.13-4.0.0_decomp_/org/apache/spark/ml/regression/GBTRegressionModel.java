package org.apache.spark.ml.regression;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasCheckpointInterval;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasValidationIndicatorCol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.EnsembleModelReadWrite$;
import org.apache.spark.ml.tree.GBTParams;
import org.apache.spark.ml.tree.GBTRegressorParams;
import org.apache.spark.ml.tree.HasVarianceImpurity;
import org.apache.spark.ml.tree.Node;
import org.apache.spark.ml.tree.TreeEnsembleModel;
import org.apache.spark.ml.tree.TreeEnsembleModel$;
import org.apache.spark.ml.tree.TreeEnsembleParams;
import org.apache.spark.ml.tree.TreeEnsembleRegressorParams;
import org.apache.spark.ml.tree.impl.GradientBoostedTrees$;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.loss.Loss;
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
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
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
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
   bytes = "\u0006\u0005\tuf\u0001\u0002\u0017.\u0001aB\u0001\u0002\u001a\u0001\u0003\u0006\u0004%\t%\u001a\u0005\t]\u0002\u0011\t\u0011)A\u0005M\"Aq\u000e\u0001BC\u0002\u0013%\u0001\u000f\u0003\u0005v\u0001\t\u0005\t\u0015!\u0003r\u0011!1\bA!b\u0001\n\u00139\b\u0002\u0003?\u0001\u0005\u0003\u0005\u000b\u0011\u0002=\t\u0011u\u0004!Q1A\u0005ByD\u0011\"!\u0002\u0001\u0005\u0003\u0005\u000b\u0011B@\t\u0011\u0005\u001d\u0001\u0001\"\u00010\u0003\u0013Aq!a\u0002\u0001\t\u0003\t\u0019\u0002\u0003\u0005\u0002\b\u0001!\taLA\u0017\u0011\u0019\ty\u0003\u0001C!a\"A\u00111\u0007\u0001C\u0002\u0013\u0005a\u0010C\u0004\u0002<\u0001\u0001\u000b\u0011B@\t\r\u0005}\u0002\u0001\"\u0011x\u0011\u001d\t\u0019\u0005\u0001C!\u0003\u000bBq!!\u0018\u0001\t\u0003\ny\u0006C\u0004\u0002\"\u0002!\t%a)\t\u000f\u0005%\u0006\u0001\"\u0011\u0002,\"9\u0011q\u0018\u0001\u0005B\u0005\u0005\u0007BCAc\u0001!\u0015\r\u0011\"\u0001\u0002H\"A\u00111\u001a\u0001\u0005\u0002=\ni\rC\u0004\u0002b\u0002!\t!a9\t\u000f\u0005m\b\u0001\"\u0011\u0002~\u001e9!\u0011B\u0017\t\u0002\t-aA\u0002\u0017.\u0011\u0003\u0011i\u0001C\u0004\u0002\bi!\tA!\u000b\t\u000f\t-\"\u0004\"\u0011\u0003.!9!q\u0007\u000e\u0005B\teba\u0002B!5\u0001Q\"1\t\u0005\n\u0005\u000br\"\u0011!Q\u0001\n\rCq!a\u0002\u001f\t\u0003\u00119\u0005C\u0004\u0003Py!\tF!\u0015\u0007\r\tm#\u0004\u0002B/\u0011\u001d\t9A\tC\u0001\u0005?B\u0011Ba\u0019#\u0005\u0004%IA!\u001a\t\u0011\tE$\u0005)A\u0005\u0005OB\u0011Ba\u001d#\u0005\u0004%IA!\u001a\t\u0011\tU$\u0005)A\u0005\u0005OBqAa\u000e#\t\u0003\u00129\b\u0003\u0005\u0003|i!\ta\fB?\u0011)\u0011IJGI\u0001\n\u0003y#1\u0014\u0005\n\u0005_S\u0012\u0011!C\u0005\u0005c\u0013!c\u0012\"U%\u0016<'/Z:tS>tWj\u001c3fY*\u0011afL\u0001\u000be\u0016<'/Z:tS>t'B\u0001\u00192\u0003\tiGN\u0003\u00023g\u0005)1\u000f]1sW*\u0011A'N\u0001\u0007CB\f7\r[3\u000b\u0003Y\n1a\u001c:h\u0007\u0001\u0019b\u0001A\u001dE\u0015B3\u0006\u0003\u0002\u001e<{\rk\u0011!L\u0005\u0003y5\u0012qBU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003}\u0005k\u0011a\u0010\u0006\u0003\u0001>\na\u0001\\5oC2<\u0017B\u0001\"@\u0005\u00191Vm\u0019;peB\u0011!\b\u0001\t\u0003\u000b\"k\u0011A\u0012\u0006\u0003\u000f>\nA\u0001\u001e:fK&\u0011\u0011J\u0012\u0002\u0013\u000f\n#&+Z4sKN\u001cxN\u001d)be\u0006l7\u000fE\u0002F\u00176K!\u0001\u0014$\u0003#Q\u0013X-Z#og\u0016l'\r\\3N_\u0012,G\u000e\u0005\u0002;\u001d&\u0011q*\f\u0002\u001c\t\u0016\u001c\u0017n]5p]R\u0013X-\u001a*fOJ,7o]5p]6{G-\u001a7\u0011\u0005E#V\"\u0001*\u000b\u0005M{\u0013\u0001B;uS2L!!\u0016*\u0003\u00155cuK]5uC\ndW\r\u0005\u0002XC:\u0011\u0001L\u0018\b\u00033rk\u0011A\u0017\u0006\u00037^\na\u0001\u0010:p_Rt\u0014\"A/\u0002\u000bM\u001c\u0017\r\\1\n\u0005}\u0003\u0017a\u00029bG.\fw-\u001a\u0006\u0002;&\u0011!m\u0019\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003?\u0002\f1!^5e+\u00051\u0007CA4l\u001d\tA\u0017\u000e\u0005\u0002ZA&\u0011!\u000eY\u0001\u0007!J,G-\u001a4\n\u00051l'AB*ue&twM\u0003\u0002kA\u0006!Q/\u001b3!\u0003\u0019yFO]3fgV\t\u0011\u000fE\u0002sg6k\u0011\u0001Y\u0005\u0003i\u0002\u0014Q!\u0011:sCf\fqa\u0018;sK\u0016\u001c\b%\u0001\u0007`iJ,WmV3jO\"$8/F\u0001y!\r\u00118/\u001f\t\u0003ejL!a\u001f1\u0003\r\u0011{WO\u00197f\u00035yFO]3f/\u0016Lw\r\u001b;tA\u0005Ya.^7GK\u0006$XO]3t+\u0005y\bc\u0001:\u0002\u0002%\u0019\u00111\u00011\u0003\u0007%sG/\u0001\u0007ok64U-\u0019;ve\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\n\u0007\u0006-\u0011QBA\b\u0003#AQ\u0001Z\u0005A\u0002\u0019DQa\\\u0005A\u0002EDQA^\u0005A\u0002aDQ!`\u0005A\u0002}$raQA\u000b\u0003/\tI\u0002C\u0003e\u0015\u0001\u0007a\rC\u0003p\u0015\u0001\u0007\u0011\u000fC\u0003w\u0015\u0001\u0007\u0001\u0010K\u0003\u000b\u0003;\tI\u0003\u0005\u0003\u0002 \u0005\u0015RBAA\u0011\u0015\r\t\u0019#M\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0014\u0003C\u0011QaU5oG\u0016\f#!a\u000b\u0002\u000bErCG\f\u0019\u0015\u0003\r\u000bQ\u0001\u001e:fKNDS\u0001DA\u000f\u0003S\t1bZ3u\u001dVlGK]3fg\"*Q\"!\b\u00028\u0005\u0012\u0011\u0011H\u0001\u0006e9\u0002d\u0006M\u0001\rO\u0016$h*^7Ue\u0016,7\u000f\t\u0015\u0006\u001d\u0005u\u0011qG\u0001\fiJ,WmV3jO\"$8\u000fK\u0003\u0010\u0003;\tI#A\bue\u0006t7OZ8s[N\u001b\u0007.Z7b)\u0011\t9%a\u0016\u0011\t\u0005%\u00131K\u0007\u0003\u0003\u0017RA!!\u0014\u0002P\u0005)A/\u001f9fg*\u0019\u0011\u0011K\u0019\u0002\u0007M\fH.\u0003\u0003\u0002V\u0005-#AC*ueV\u001cG\u000fV=qK\"9\u0011\u0011\f\tA\u0002\u0005\u001d\u0013AB:dQ\u0016l\u0017\rK\u0003\u0011\u0003;\tI#A\u0005ue\u0006t7OZ8s[R!\u0011\u0011MA?!\u0011\t\u0019'a\u001e\u000f\t\u0005\u0015\u0014Q\u000f\b\u0005\u0003O\n\u0019H\u0004\u0003\u0002j\u0005Ed\u0002BA6\u0003_r1!WA7\u0013\u00051\u0014B\u0001\u001b6\u0013\t\u00114'C\u0002\u0002REJ1aXA(\u0013\u0011\tI(a\u001f\u0003\u0013\u0011\u000bG/\u0019$sC6,'bA0\u0002P!9\u0011qP\tA\u0002\u0005\u0005\u0015a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003\u0007\u000by\t\u0005\u0004\u0002\u0006\u0006\u001d\u00151R\u0007\u0003\u0003\u001fJA!!#\u0002P\t9A)\u0019;bg\u0016$\b\u0003BAG\u0003\u001fc\u0001\u0001\u0002\u0007\u0002\u0012\u0006u\u0014\u0011!A\u0001\u0006\u0003\t\u0019JA\u0002`II\nB!!&\u0002\u001cB\u0019!/a&\n\u0007\u0005e\u0005MA\u0004O_RD\u0017N\\4\u0011\u0007I\fi*C\u0002\u0002 \u0002\u00141!\u00118z\u0003\u001d\u0001(/\u001a3jGR$2!_AS\u0011\u0019\t9K\u0005a\u0001{\u0005Aa-Z1ukJ,7/\u0001\u0003d_BLHcA\"\u0002.\"9\u0011qV\nA\u0002\u0005E\u0016!B3yiJ\f\u0007\u0003BAZ\u0003sk!!!.\u000b\u0007\u0005]v&A\u0003qCJ\fW.\u0003\u0003\u0002<\u0006U&\u0001\u0003)be\u0006lW*\u00199)\u000bM\ti\"!\u000b\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A\u001a\u0015\u0006)\u0005u\u0011\u0011F\u0001\u0013M\u0016\fG/\u001e:f\u00136\u0004xN\u001d;b]\u000e,7/F\u0001>Q\u0015)\u0012QDA\u001c\u0003\u0015!xn\u00147e+\t\ty\r\u0005\u0003\u0002R\u0006uWBAAj\u0015\u0011\t).a6\u0002\u000b5|G-\u001a7\u000b\u0007\u001d\u000bINC\u0002\u0002\\F\nQ!\u001c7mS\nLA!a8\u0002T\nIrI]1eS\u0016tGOQ8pgR,G\r\u0016:fKNlu\u000eZ3m\u0003U)g/\u00197vCR,W)Y2i\u0013R,'/\u0019;j_:$R\u0001_As\u0003cDq!a \u0018\u0001\u0004\t9\u000f\r\u0003\u0002j\u00065\bCBAC\u0003\u000f\u000bY\u000f\u0005\u0003\u0002\u000e\u00065H\u0001DAx\u0003K\f\t\u0011!A\u0003\u0002\u0005M%aA0%g!1\u00111_\fA\u0002\u0019\fA\u0001\\8tg\"*q#!\b\u0002x\u0006\u0012\u0011\u0011`\u0001\u0006e9\"d\u0006M\u0001\u0006oJLG/Z\u000b\u0003\u0003\u007f\u00042!\u0015B\u0001\u0013\r\u0011\u0019A\u0015\u0002\t\u001b2;&/\u001b;fe\"*\u0001$!\b\u00028!*\u0001!!\b\u0002*\u0005\u0011rI\u0011+SK\u001e\u0014Xm]:j_:lu\u000eZ3m!\tQ$dE\u0004\u001b\u0005\u001f\u0011)Ba\u0007\u0011\u0007I\u0014\t\"C\u0002\u0003\u0014\u0001\u0014a!\u00118z%\u00164\u0007\u0003B)\u0003\u0018\rK1A!\u0007S\u0005)iEJU3bI\u0006\u0014G.\u001a\t\u0005\u0005;\u00119#\u0004\u0002\u0003 )!!\u0011\u0005B\u0012\u0003\tIwN\u0003\u0002\u0003&\u0005!!.\u0019<b\u0013\r\u0011'q\u0004\u000b\u0003\u0005\u0017\tAA]3bIV\u0011!q\u0006\t\u0005#\nE2)C\u0002\u00034I\u0013\u0001\"\u0014'SK\u0006$WM\u001d\u0015\u00069\u0005u\u0011qG\u0001\u0005Y>\fG\rF\u0002D\u0005wAaA!\u0010\u001e\u0001\u00041\u0017\u0001\u00029bi\"DS!HA\u000f\u0003o\u0011\u0001d\u0012\"U%\u0016<'/Z:tS>tWj\u001c3fY^\u0013\u0018\u000e^3s'\rq\u0012q`\u0001\tS:\u001cH/\u00198dKR!!\u0011\nB'!\r\u0011YEH\u0007\u00025!1!Q\t\u0011A\u0002\r\u000b\u0001b]1wK&k\u0007\u000f\u001c\u000b\u0005\u0005'\u0012I\u0006E\u0002s\u0005+J1Aa\u0016a\u0005\u0011)f.\u001b;\t\r\tu\u0012\u00051\u0001g\u0005a9%\t\u0016*fOJ,7o]5p]6{G-\u001a7SK\u0006$WM]\n\u0004E\t=BC\u0001B1!\r\u0011YEI\u0001\nG2\f7o\u001d(b[\u0016,\"Aa\u001a\u0011\t\t%$qN\u0007\u0003\u0005WRAA!\u001c\u0003$\u0005!A.\u00198h\u0013\ra'1N\u0001\u000bG2\f7o\u001d(b[\u0016\u0004\u0013!\u0004;sK\u0016\u001cE.Y:t\u001d\u0006lW-\u0001\bue\u0016,7\t\\1tg:\u000bW.\u001a\u0011\u0015\u0007\r\u0013I\b\u0003\u0004\u0003>!\u0002\rAZ\u0001\bMJ|Wn\u00147e)%\u0019%q\u0010BB\u0005\u001b\u00139\nC\u0004\u0003\u0002&\u0002\r!a4\u0002\u0011=dG-T8eK2DqA!\"*\u0001\u0004\u00119)\u0001\u0004qCJ,g\u000e\u001e\t\u0004u\t%\u0015b\u0001BF[\taqI\u0011+SK\u001e\u0014Xm]:pe\"9!qR\u0015A\u0002\tE\u0015aE2bi\u0016<wN]5dC24U-\u0019;ve\u0016\u001c\b#B4\u0003\u0014~|\u0018b\u0001BK[\n\u0019Q*\u00199\t\u000fuL\u0003\u0013!a\u0001\u007f\u0006\tbM]8n\u001f2$G\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\tu%fA@\u0003 .\u0012!\u0011\u0015\t\u0005\u0005G\u0013Y+\u0004\u0002\u0003&*!!q\u0015BU\u0003%)hn\u00195fG.,GMC\u0002\u0002$\u0001LAA!,\u0003&\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tM\u0006\u0003\u0002B5\u0005kKAAa.\u0003l\t1qJ\u00196fGRDSAGA\u000f\u0003oAS!GA\u000f\u0003o\u0001"
)
public class GBTRegressionModel extends RegressionModel implements GBTRegressorParams, TreeEnsembleModel, MLWritable {
   private Vector featureImportances;
   private final String uid;
   private final DecisionTreeRegressionModel[] _trees;
   private final double[] _treeWeights;
   private final int numFeatures;
   private final int getNumTrees;
   private int totalNumNodes;
   private Param lossType;
   private Param impurity;
   private DoubleParam validationTol;
   private DoubleParam stepSize;
   private Param validationIndicatorCol;
   private IntParam maxIter;
   private DoubleParam subsamplingRate;
   private Param featureSubsetStrategy;
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
   private volatile byte bitmap$0;

   public static GBTRegressionModel load(final String path) {
      return GBTRegressionModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GBTRegressionModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public DecisionTreeModel getTree(final int i) {
      return TreeEnsembleModel.getTree$(this, i);
   }

   public Vector javaTreeWeights() {
      return TreeEnsembleModel.javaTreeWeights$(this);
   }

   public String toDebugString() {
      return TreeEnsembleModel.toDebugString$(this);
   }

   public Vector predictLeaf(final Vector features) {
      return TreeEnsembleModel.predictLeaf$(this, features);
   }

   public StructField getLeafField(final String leafCol) {
      return TreeEnsembleModel.getLeafField$(this, leafCol);
   }

   public String getLossType() {
      return GBTRegressorParams.getLossType$(this);
   }

   public Loss getOldLossType() {
      return GBTRegressorParams.getOldLossType$(this);
   }

   public Loss convertToOldLossType(final String loss) {
      return GBTRegressorParams.convertToOldLossType$(this, loss);
   }

   public final String getImpurity() {
      return HasVarianceImpurity.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return HasVarianceImpurity.getOldImpurity$(this);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$TreeEnsembleRegressorParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return TreeEnsembleRegressorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   // $FF: synthetic method
   public Strategy org$apache$spark$ml$tree$GBTParams$$super$getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity) {
      return TreeEnsembleParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity);
   }

   public final double getValidationTol() {
      return GBTParams.getValidationTol$(this);
   }

   public BoostingStrategy getOldBoostingStrategy(final Map categoricalFeatures, final Enumeration.Value oldAlgo) {
      return GBTParams.getOldBoostingStrategy$(this, categoricalFeatures, oldAlgo);
   }

   public final String getValidationIndicatorCol() {
      return HasValidationIndicatorCol.getValidationIndicatorCol$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   // $FF: synthetic method
   public Strategy org$apache$spark$ml$tree$TreeEnsembleParams$$super$getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity, final double subsamplingRate) {
      return DecisionTreeParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity, subsamplingRate);
   }

   public final double getSubsamplingRate() {
      return TreeEnsembleParams.getSubsamplingRate$(this);
   }

   public Strategy getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity) {
      return TreeEnsembleParams.getOldStrategy$(this, categoricalFeatures, numClasses, oldAlgo, oldImpurity);
   }

   public final String getFeatureSubsetStrategy() {
      return TreeEnsembleParams.getFeatureSubsetStrategy$(this);
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

   private int totalNumNodes$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.totalNumNodes = TreeEnsembleModel.totalNumNodes$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.totalNumNodes;
   }

   public int totalNumNodes() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.totalNumNodes$lzycompute() : this.totalNumNodes;
   }

   public Param lossType() {
      return this.lossType;
   }

   public void org$apache$spark$ml$tree$GBTRegressorParams$_setter_$lossType_$eq(final Param x$1) {
      this.lossType = x$1;
   }

   public final Param impurity() {
      return this.impurity;
   }

   public final void org$apache$spark$ml$tree$HasVarianceImpurity$_setter_$impurity_$eq(final Param x$1) {
      this.impurity = x$1;
   }

   public final DoubleParam validationTol() {
      return this.validationTol;
   }

   public final DoubleParam stepSize() {
      return this.stepSize;
   }

   public final void org$apache$spark$ml$tree$GBTParams$_setter_$validationTol_$eq(final DoubleParam x$1) {
      this.validationTol = x$1;
   }

   public final void org$apache$spark$ml$tree$GBTParams$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
   }

   public final Param validationIndicatorCol() {
      return this.validationIndicatorCol;
   }

   public final void org$apache$spark$ml$param$shared$HasValidationIndicatorCol$_setter_$validationIndicatorCol_$eq(final Param x$1) {
      this.validationIndicatorCol = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public final DoubleParam subsamplingRate() {
      return this.subsamplingRate;
   }

   public final Param featureSubsetStrategy() {
      return this.featureSubsetStrategy;
   }

   public final void org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$subsamplingRate_$eq(final DoubleParam x$1) {
      this.subsamplingRate = x$1;
   }

   public final void org$apache$spark$ml$tree$TreeEnsembleParams$_setter_$featureSubsetStrategy_$eq(final Param x$1) {
      this.featureSubsetStrategy = x$1;
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

   public String uid() {
      return this.uid;
   }

   private DecisionTreeRegressionModel[] _trees() {
      return this._trees;
   }

   private double[] _treeWeights() {
      return this._treeWeights;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public DecisionTreeRegressionModel[] trees() {
      return this._trees();
   }

   public int getNumTrees() {
      return this.getNumTrees;
   }

   public double[] treeWeights() {
      return this._treeWeights();
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
      Seq predictionColNames = (Seq)scala.package..MODULE$.Seq().empty();
      Seq predictionColumns = (Seq)scala.package..MODULE$.Seq().empty();
      Broadcast bcastModel = dataset.sparkSession().sparkContext().broadcast(this, scala.reflect.ClassTag..MODULE$.apply(GBTRegressionModel.class));
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> BoxesRunTime.boxToDouble($anonfun$transform$1(bcastModel, features));
         TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GBTRegressionModel.class.getClassLoader());

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
         predictionColumns = (Seq)predictionColumns.$colon$plus(predictUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))).as((String)this.$(this.featuresCol()), outputSchema.apply((String)this.$(this.featuresCol())).metadata()));
      }

      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         functions var14 = org.apache.spark.sql.functions..MODULE$;
         Function1 var15 = (features) -> ((TreeEnsembleModel)bcastModel.value()).predictLeaf(features);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GBTRegressionModel.class.getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         TypeTags.TypeTag var16 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GBTRegressionModel.class.getClassLoader());

         final class $typecreator3$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator3$1() {
            }
         }

         UserDefinedFunction leafUDF = var14.udf(var15, var16, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1()));
         predictionColNames = (Seq)predictionColNames.$colon$plus(this.$(this.leafCol()));
         predictionColumns = (Seq)predictionColumns.$colon$plus(leafUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))).as((String)this.$(this.leafCol()), outputSchema.apply((String)this.$(this.leafCol())).metadata()));
      }

      if (predictionColNames.nonEmpty()) {
         return dataset.withColumns(predictionColNames, predictionColumns);
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": GBTRegressionModel.transform() "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"does nothing because no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return dataset.toDF();
      }
   }

   public double predict(final Vector features) {
      double[] treePredictions = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this._trees()), (x$4) -> BoxesRunTime.boxToDouble($anonfun$predict$1(features, x$4)), scala.reflect.ClassTag..MODULE$.Double());
      return org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(this.getNumTrees(), treePredictions, 1, this._treeWeights(), 1);
   }

   public GBTRegressionModel copy(final ParamMap extra) {
      return (GBTRegressionModel)((Model)this.copyValues(new GBTRegressionModel(this.uid(), this._trees(), this._treeWeights(), this.numFeatures()), extra)).setParent(this.parent());
   }

   public String toString() {
      String var10000 = this.uid();
      return "GBTRegressionModel: uid=" + var10000 + ", numTrees=" + this.getNumTrees() + ", numFeatures=" + this.numFeatures();
   }

   private Vector featureImportances$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.featureImportances = TreeEnsembleModel$.MODULE$.featureImportances(this.trees(), this.numFeatures(), false);
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

   public GradientBoostedTreesModel toOld() {
      return new GradientBoostedTreesModel(Algo$.MODULE$.Regression(), (org.apache.spark.mllib.tree.model.DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this._trees()), (x$5) -> x$5.toOld(), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.tree.model.DecisionTreeModel.class)), this._treeWeights());
   }

   public double[] evaluateEachIteration(final Dataset dataset, final String loss) {
      RDD data = DatasetUtils$.MODULE$.extractInstances(this, dataset, DatasetUtils$.MODULE$.extractInstances$default$3());
      return GradientBoostedTrees$.MODULE$.evaluateEachIteration(data, this.trees(), this.treeWeights(), this.convertToOldLossType(loss), Algo$.MODULE$.Regression());
   }

   public MLWriter write() {
      return new GBTRegressionModelWriter(this);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$1(final Broadcast bcastModel$1, final Vector features) {
      return ((GBTRegressionModel)bcastModel$1.value()).predict(features);
   }

   // $FF: synthetic method
   public static final double $anonfun$predict$1(final Vector features$1, final DecisionTreeRegressionModel x$4) {
      return x$4.rootNode().predictImpl(features$1).prediction();
   }

   public GBTRegressionModel(final String uid, final DecisionTreeRegressionModel[] _trees, final double[] _treeWeights, final int numFeatures) {
      this.uid = uid;
      this._trees = _trees;
      this._treeWeights = _treeWeights;
      this.numFeatures = numFeatures;
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      TreeEnsembleParams.$init$(this);
      HasMaxIter.$init$(this);
      HasStepSize.$init$(this);
      HasValidationIndicatorCol.$init$(this);
      GBTParams.$init$(this);
      TreeEnsembleRegressorParams.$init$(this);
      HasVarianceImpurity.$init$(this);
      GBTRegressorParams.$init$(this);
      TreeEnsembleModel.$init$(this);
      MLWritable.$init$(this);
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps(_trees)), () -> "GBTRegressionModel requires at least 1 tree.");
      scala.Predef..MODULE$.require(_trees.length == _treeWeights.length, () -> {
         int var10000 = this._trees().length;
         return "GBTRegressionModel given trees, treeWeights of non-matching lengths (" + var10000 + ", " + this._treeWeights().length + ", respectively).";
      });
      this.getNumTrees = this.trees().length;
      Statics.releaseFence();
   }

   public GBTRegressionModel(final String uid, final DecisionTreeRegressionModel[] _trees, final double[] _treeWeights) {
      this(uid, _trees, _treeWeights, -1);
   }

   public GBTRegressionModel() {
      this("", (DecisionTreeRegressionModel[])(new DecisionTreeRegressionModel[]{new DecisionTreeRegressionModel()}), new double[]{Double.NaN}, -1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class GBTRegressionModelWriter extends MLWriter {
      private final GBTRegressionModel instance;

      public void saveImpl(final String path) {
         JObject extraMetadata = org.json4s.JsonDSL..MODULE$.map2jvalue((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numFeatures"), BoxesRunTime.boxToInteger(this.instance.numFeatures())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numTrees"), BoxesRunTime.boxToInteger(this.instance.getNumTrees()))}))), (x) -> $anonfun$saveImpl$1(BoxesRunTime.unboxToInt(x)));
         EnsembleModelReadWrite$.MODULE$.saveImpl(this.instance, path, this.sparkSession(), extraMetadata);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$1(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      public GBTRegressionModelWriter(final GBTRegressionModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class GBTRegressionModelReader extends MLReader {
      private final String className = GBTRegressionModel.class.getName();
      private final String treeClassName = DecisionTreeRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      private String treeClassName() {
         return this.treeClassName;
      }

      public GBTRegressionModel load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         Tuple3 var5 = EnsembleModelReadWrite$.MODULE$.loadImpl(path, this.sparkSession(), this.className(), this.treeClassName());
         if (var5 != null) {
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var5._1();
            Tuple2[] treesData = (Tuple2[])var5._2();
            double[] treeWeights = (double[])var5._3();
            if (metadata != null && treesData != null && treeWeights != null) {
               Tuple3 var4 = new Tuple3(metadata, treesData, treeWeights);
               DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var4._1();
               Tuple2[] treesData = (Tuple2[])var4._2();
               double[] treeWeights = (double[])var4._3();
               int numFeatures = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numFeatures")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
               int numTrees = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numTrees")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
               DecisionTreeRegressionModel[] trees = (DecisionTreeRegressionModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])treesData), (x0$1) -> {
                  if (x0$1 != null) {
                     DefaultParamsReader.Metadata treeMetadata = (DefaultParamsReader.Metadata)x0$1._1();
                     Node root = (Node)x0$1._2();
                     DecisionTreeRegressionModel tree = new DecisionTreeRegressionModel(treeMetadata.uid(), root, numFeatures);
                     treeMetadata.getAndSetParams(tree, treeMetadata.getAndSetParams$default$2());
                     return tree;
                  } else {
                     throw new MatchError(x0$1);
                  }
               }, scala.reflect.ClassTag..MODULE$.apply(DecisionTreeRegressionModel.class));
               scala.Predef..MODULE$.require(numTrees == trees.length, () -> "GBTRegressionModel.load expected " + numTrees + " trees based on metadata but found " + trees.length + " trees.");
               GBTRegressionModel model = new GBTRegressionModel(metadata.uid(), trees, treeWeights, numFeatures);
               metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
               return model;
            }
         }

         throw new MatchError(var5);
      }

      public GBTRegressionModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
