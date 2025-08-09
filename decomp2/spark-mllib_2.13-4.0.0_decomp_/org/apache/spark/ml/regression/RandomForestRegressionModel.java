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
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.EnsembleModelReadWrite$;
import org.apache.spark.ml.tree.HasVarianceImpurity;
import org.apache.spark.ml.tree.Node;
import org.apache.spark.ml.tree.RandomForestParams;
import org.apache.spark.ml.tree.RandomForestRegressorParams;
import org.apache.spark.ml.tree.TreeEnsembleModel;
import org.apache.spark.ml.tree.TreeEnsembleModel$;
import org.apache.spark.ml.tree.TreeEnsembleParams;
import org.apache.spark.ml.tree.TreeEnsembleRegressorParams;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.model.RandomForestModel;
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
import scala.Array.;
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
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t=e\u0001\u0002\u0015*\u0001QB\u0001\u0002\u0019\u0001\u0003\u0006\u0004%\t%\u0019\u0005\tU\u0002\u0011\t\u0011)A\u0005E\"A1\u000e\u0001BC\u0002\u0013%A\u000e\u0003\u0005r\u0001\t\u0005\t\u0015!\u0003n\u0011!\u0011\bA!b\u0001\n\u0003\u001a\b\u0002C<\u0001\u0005\u0003\u0005\u000b\u0011\u0002;\t\ra\u0004A\u0011A\u0016z\u0011\u0019A\b\u0001\"\u0001,{\"9\u0001\u0010\u0001C\u0001W\u0005\r\u0001\"B@\u0001\t\u0003b\u0007BCA\f\u0001!\u0015\r\u0011\"\u0003\u0002\u001a!9\u00111\u0005\u0001\u0005B\u0005e\u0001bBA\u0014\u0001\u0011\u0005\u0013\u0011\u0006\u0005\b\u0003\u0003\u0002A\u0011IA\"\u0011\u001d\t)\t\u0001C!\u0003\u000fCq!!$\u0001\t\u0003\ny\tC\u0004\u0002$\u0002!\t%!*\t\u0015\u0005%\u0006\u0001#b\u0001\n\u0003\tY\u000b\u0003\u0005\u00024\u0002!\taKA[\u0011\u001d\tI\r\u0001C!\u0003\u0017<q!a7*\u0011\u0003\tiN\u0002\u0004)S!\u0005\u0011q\u001c\u0005\u0007qZ!\t!a?\t\u000f\u0005uh\u0003\"\u0011\u0002\u0000\"9!\u0011\u0002\f\u0005B\t-aa\u0002B\n-\u00011\"Q\u0003\u0005\n\u0005/Q\"\u0011!Q\u0001\n}Ba\u0001\u001f\u000e\u0005\u0002\te\u0001b\u0002B\u00115\u0011E#1\u0005\u0004\u0007\u0005[1BAa\f\t\ratB\u0011\u0001B\u0019\u0011%\u0011)D\bb\u0001\n\u0013\u00119\u0004\u0003\u0005\u0003Dy\u0001\u000b\u0011\u0002B\u001d\u0011%\u0011)E\bb\u0001\n\u0013\u00119\u0004\u0003\u0005\u0003Hy\u0001\u000b\u0011\u0002B\u001d\u0011\u001d\u0011IA\bC!\u0005\u0013B\u0001B!\u0014\u0017\t\u0003Y#q\n\u0005\u000b\u0005W2\u0012\u0013!C\u0001W\t5\u0004\"\u0003BA-\u0005\u0005I\u0011\u0002BB\u0005m\u0011\u0016M\u001c3p[\u001a{'/Z:u%\u0016<'/Z:tS>tWj\u001c3fY*\u0011!fK\u0001\u000be\u0016<'/Z:tS>t'B\u0001\u0017.\u0003\tiGN\u0003\u0002/_\u0005)1\u000f]1sW*\u0011\u0001'M\u0001\u0007CB\f7\r[3\u000b\u0003I\n1a\u001c:h\u0007\u0001\u0019b\u0001A\u001bA\r2\u0013\u0006\u0003\u0002\u001c8s}j\u0011!K\u0005\u0003q%\u0012qBU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003uuj\u0011a\u000f\u0006\u0003y-\na\u0001\\5oC2<\u0017B\u0001 <\u0005\u00191Vm\u0019;peB\u0011a\u0007\u0001\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007.\nA\u0001\u001e:fK&\u0011QI\u0011\u0002\u001c%\u0006tGm\\7G_J,7\u000f\u001e*fOJ,7o]8s!\u0006\u0014\u0018-\\:\u0011\u0007\u0005;\u0015*\u0003\u0002I\u0005\n\tBK]3f\u000b:\u001cX-\u001c2mK6{G-\u001a7\u0011\u0005YR\u0015BA&*\u0005m!UmY5tS>tGK]3f%\u0016<'/Z:tS>tWj\u001c3fYB\u0011Q\nU\u0007\u0002\u001d*\u0011qjK\u0001\u0005kRLG.\u0003\u0002R\u001d\nQQ\nT,sSR\f'\r\\3\u0011\u0005MkfB\u0001+[\u001d\t)\u0006,D\u0001W\u0015\t96'\u0001\u0004=e>|GOP\u0005\u00023\u0006)1oY1mC&\u00111\fX\u0001\ba\u0006\u001c7.Y4f\u0015\u0005I\u0016B\u00010`\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tYF,A\u0002vS\u0012,\u0012A\u0019\t\u0003G\u001et!\u0001Z3\u0011\u0005Uc\u0016B\u00014]\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001.\u001b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019d\u0016\u0001B;jI\u0002\naa\u0018;sK\u0016\u001cX#A7\u0011\u00079|\u0017*D\u0001]\u0013\t\u0001HLA\u0003BeJ\f\u00170A\u0004`iJ,Wm\u001d\u0011\u0002\u00179,XNR3biV\u0014Xm]\u000b\u0002iB\u0011a.^\u0005\u0003mr\u00131!\u00138u\u00031qW/\u001c$fCR,(/Z:!\u0003\u0019a\u0014N\\5u}Q!qH_>}\u0011\u0015\u0001w\u00011\u0001c\u0011\u0015Yw\u00011\u0001n\u0011\u0015\u0011x\u00011\u0001u)\u0011yd0!\u0001\t\u000b}D\u0001\u0019A7\u0002\u000bQ\u0014X-Z:\t\u000bID\u0001\u0019\u0001;\u0015\u0003}BSACA\u0004\u0003'\u0001B!!\u0003\u0002\u00105\u0011\u00111\u0002\u0006\u0004\u0003\u001bi\u0013AC1o]>$\u0018\r^5p]&!\u0011\u0011CA\u0006\u0005\u0015\u0019\u0016N\\2fC\t\t)\"A\u00032]Qr\u0003'\u0001\u0007`iJ,WmV3jO\"$8/\u0006\u0002\u0002\u001cA!an\\A\u000f!\rq\u0017qD\u0005\u0004\u0003Ca&A\u0002#pk\ndW-A\u0006ue\u0016,w+Z5hQR\u001c\b&\u0002\u0007\u0002\b\u0005M\u0011a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005-\u00121\b\t\u0005\u0003[\t9$\u0004\u0002\u00020)!\u0011\u0011GA\u001a\u0003\u0015!\u0018\u0010]3t\u0015\r\t)$L\u0001\u0004gFd\u0017\u0002BA\u001d\u0003_\u0011!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\ti$\u0004a\u0001\u0003W\taa]2iK6\f\u0007&B\u0007\u0002\b\u0005M\u0011!\u0003;sC:\u001chm\u001c:n)\u0011\t)%!\u0019\u0011\t\u0005\u001d\u00131\f\b\u0005\u0003\u0013\nIF\u0004\u0003\u0002L\u0005]c\u0002BA'\u0003+rA!a\u0014\u0002T9\u0019Q+!\u0015\n\u0003IJ!\u0001M\u0019\n\u00059z\u0013bAA\u001b[%\u00191,a\r\n\t\u0005u\u0013q\f\u0002\n\t\u0006$\u0018M\u0012:b[\u0016T1aWA\u001a\u0011\u001d\t\u0019G\u0004a\u0001\u0003K\nq\u0001Z1uCN,G\u000f\r\u0003\u0002h\u0005M\u0004CBA5\u0003W\ny'\u0004\u0002\u00024%!\u0011QNA\u001a\u0005\u001d!\u0015\r^1tKR\u0004B!!\u001d\u0002t1\u0001A\u0001DA;\u0003C\n\t\u0011!A\u0003\u0002\u0005]$aA0%eE!\u0011\u0011PA@!\rq\u00171P\u0005\u0004\u0003{b&a\u0002(pi\"Lgn\u001a\t\u0004]\u0006\u0005\u0015bAAB9\n\u0019\u0011I\\=\u0002\u000fA\u0014X\rZ5diR!\u0011QDAE\u0011\u0019\tYi\u0004a\u0001s\u0005Aa-Z1ukJ,7/\u0001\u0003d_BLHcA \u0002\u0012\"9\u00111\u0013\tA\u0002\u0005U\u0015!B3yiJ\f\u0007\u0003BAL\u0003;k!!!'\u000b\u0007\u0005m5&A\u0003qCJ\fW.\u0003\u0003\u0002 \u0006e%\u0001\u0003)be\u0006lW*\u00199)\u000bA\t9!a\u0005\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A\u0019\u0015\u0006#\u0005\u001d\u00111C\u0001\u0013M\u0016\fG/\u001e:f\u00136\u0004xN\u001d;b]\u000e,7/F\u0001:Q\u0015\u0011\u0012qAAXC\t\t\t,A\u00032]Ur\u0003'A\u0003u_>cG-\u0006\u0002\u00028B!\u0011\u0011XAc\u001b\t\tYL\u0003\u0003\u0002>\u0006}\u0016!B7pI\u0016d'bA\"\u0002B*\u0019\u00111Y\u0017\u0002\u000b5dG.\u001b2\n\t\u0005\u001d\u00171\u0018\u0002\u0012%\u0006tGm\\7G_J,7\u000f^'pI\u0016d\u0017!B<sSR,WCAAg!\ri\u0015qZ\u0005\u0004\u0003#t%\u0001C'M/JLG/\u001a:)\u000bQ\t9!!6\"\u0005\u0005]\u0017!\u0002\u001a/a9\u0002\u0004&\u0002\u0001\u0002\b\u0005M\u0011a\u0007*b]\u0012|WNR8sKN$(+Z4sKN\u001c\u0018n\u001c8N_\u0012,G\u000e\u0005\u00027-M9a#!9\u0002h\u00065\bc\u00018\u0002d&\u0019\u0011Q\u001d/\u0003\r\u0005s\u0017PU3g!\u0011i\u0015\u0011^ \n\u0007\u0005-hJ\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004B!a<\u0002z6\u0011\u0011\u0011\u001f\u0006\u0005\u0003g\f)0\u0001\u0002j_*\u0011\u0011q_\u0001\u0005U\u00064\u0018-C\u0002_\u0003c$\"!!8\u0002\tI,\u0017\rZ\u000b\u0003\u0005\u0003\u0001B!\u0014B\u0002\u007f%\u0019!Q\u0001(\u0003\u00115c%+Z1eKJDS\u0001GA\u0004\u0003+\fA\u0001\\8bIR\u0019qH!\u0004\t\r\t=\u0011\u00041\u0001c\u0003\u0011\u0001\u0018\r\u001e5)\u000be\t9!!6\u0003CI\u000bg\u000eZ8n\r>\u0014Xm\u001d;SK\u001e\u0014Xm]:j_:lu\u000eZ3m/JLG/\u001a:\u0014\u0007i\ti-\u0001\u0005j]N$\u0018M\\2f)\u0011\u0011YBa\b\u0011\u0007\tu!$D\u0001\u0017\u0011\u0019\u00119\u0002\ba\u0001\u007f\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0003&\t-\u0002c\u00018\u0003(%\u0019!\u0011\u0006/\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0005\u001fi\u0002\u0019\u00012\u0003CI\u000bg\u000eZ8n\r>\u0014Xm\u001d;SK\u001e\u0014Xm]:j_:lu\u000eZ3m%\u0016\fG-\u001a:\u0014\u0007y\u0011\t\u0001\u0006\u0002\u00034A\u0019!Q\u0004\u0010\u0002\u0013\rd\u0017m]:OC6,WC\u0001B\u001d!\u0011\u0011YD!\u0011\u000e\u0005\tu\"\u0002\u0002B \u0003k\fA\u0001\\1oO&\u0019\u0001N!\u0010\u0002\u0015\rd\u0017m]:OC6,\u0007%A\u0007ue\u0016,7\t\\1tg:\u000bW.Z\u0001\u000fiJ,Wm\u00117bgNt\u0015-\\3!)\ry$1\n\u0005\u0007\u0005\u001f!\u0003\u0019\u00012\u0002\u000f\u0019\u0014x.\\(mIRIqH!\u0015\u0003V\t}#\u0011\u000e\u0005\b\u0005'*\u0003\u0019AA\\\u0003!yG\u000eZ'pI\u0016d\u0007b\u0002B,K\u0001\u0007!\u0011L\u0001\u0007a\u0006\u0014XM\u001c;\u0011\u0007Y\u0012Y&C\u0002\u0003^%\u0012QCU1oI>lgi\u001c:fgR\u0014Vm\u001a:fgN|'\u000fC\u0004\u0003b\u0015\u0002\rAa\u0019\u0002'\r\fG/Z4pe&\u001c\u0017\r\u001c$fCR,(/Z:\u0011\u000b\r\u0014)\u0007\u001e;\n\u0007\t\u001d\u0014NA\u0002NCBDqA]\u0013\u0011\u0002\u0003\u0007A/A\tge>lw\n\u001c3%I\u00164\u0017-\u001e7uIQ*\"Aa\u001c+\u0007Q\u0014\th\u000b\u0002\u0003tA!!Q\u000fB?\u001b\t\u00119H\u0003\u0003\u0003z\tm\u0014!C;oG\",7m[3e\u0015\r\ti\u0001X\u0005\u0005\u0005\u007f\u00129HA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A!\"\u0011\t\tm\"qQ\u0005\u0005\u0005\u0013\u0013iD\u0001\u0004PE*,7\r\u001e\u0015\u0006-\u0005\u001d\u0011Q\u001b\u0015\u0006+\u0005\u001d\u0011Q\u001b"
)
public class RandomForestRegressionModel extends RegressionModel implements RandomForestRegressorParams, TreeEnsembleModel, MLWritable {
   private double[] _treeWeights;
   private Vector featureImportances;
   private final String uid;
   private final DecisionTreeRegressionModel[] _trees;
   private final int numFeatures;
   private int totalNumNodes;
   private Param impurity;
   private IntParam numTrees;
   private BooleanParam bootstrap;
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

   public static RandomForestRegressionModel load(final String path) {
      return RandomForestRegressionModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RandomForestRegressionModel$.MODULE$.read();
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

   public final int getNumTrees() {
      return RandomForestParams.getNumTrees$(this);
   }

   public final boolean getBootstrap() {
      return RandomForestParams.getBootstrap$(this);
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
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.totalNumNodes = TreeEnsembleModel.totalNumNodes$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.totalNumNodes;
   }

   public int totalNumNodes() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.totalNumNodes$lzycompute() : this.totalNumNodes;
   }

   public final Param impurity() {
      return this.impurity;
   }

   public final void org$apache$spark$ml$tree$HasVarianceImpurity$_setter_$impurity_$eq(final Param x$1) {
      this.impurity = x$1;
   }

   public final IntParam numTrees() {
      return this.numTrees;
   }

   public final BooleanParam bootstrap() {
      return this.bootstrap;
   }

   public final void org$apache$spark$ml$tree$RandomForestParams$_setter_$numTrees_$eq(final IntParam x$1) {
      this.numTrees = x$1;
   }

   public final void org$apache$spark$ml$tree$RandomForestParams$_setter_$bootstrap_$eq(final BooleanParam x$1) {
      this.bootstrap = x$1;
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

   public int numFeatures() {
      return this.numFeatures;
   }

   public DecisionTreeRegressionModel[] trees() {
      return this._trees();
   }

   private double[] _treeWeights$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this._treeWeights = (double[]).MODULE$.fill(this._trees().length, (JFunction0.mcD.sp)() -> (double)1.0F, scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this._treeWeights;
   }

   private double[] _treeWeights() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this._treeWeights$lzycompute() : this._treeWeights;
   }

   public double[] treeWeights() {
      return this._treeWeights();
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = super.transformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateField(outputSchema, this.getLeafField((String)this.$(this.leafCol())), SchemaUtils$.MODULE$.updateField$default$3());
      }

      return outputSchema;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Seq predictionColNames = (Seq)scala.package..MODULE$.Seq().empty();
      Seq predictionColumns = (Seq)scala.package..MODULE$.Seq().empty();
      Broadcast bcastModel = dataset.sparkSession().sparkContext().broadcast(this, scala.reflect.ClassTag..MODULE$.apply(RandomForestRegressionModel.class));
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> BoxesRunTime.boxToDouble($anonfun$transform$1(bcastModel, features));
         TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RandomForestRegressionModel.class.getClassLoader());

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

      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         functions var14 = org.apache.spark.sql.functions..MODULE$;
         Function1 var15 = (features) -> ((TreeEnsembleModel)bcastModel.value()).predictLeaf(features);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RandomForestRegressionModel.class.getClassLoader());

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
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RandomForestRegressionModel.class.getClassLoader());

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
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": RandomForestRegressionModel.transform() "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"does nothing because no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return dataset.toDF();
      }
   }

   public double predict(final Vector features) {
      return BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this._trees()), (x$3) -> BoxesRunTime.boxToDouble($anonfun$predict$1(features, x$3)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) / (double)this.getNumTrees();
   }

   public RandomForestRegressionModel copy(final ParamMap extra) {
      return (RandomForestRegressionModel)((Model)this.copyValues(new RandomForestRegressionModel(this.uid(), this._trees(), this.numFeatures()), extra)).setParent(this.parent());
   }

   public String toString() {
      String var10000 = this.uid();
      return "RandomForestRegressionModel: uid=" + var10000 + ", numTrees=" + this.getNumTrees() + ", numFeatures=" + this.numFeatures();
   }

   private Vector featureImportances$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.featureImportances = TreeEnsembleModel$.MODULE$.featureImportances(this.trees(), this.numFeatures(), TreeEnsembleModel$.MODULE$.featureImportances$default$3());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.featureImportances;
   }

   public Vector featureImportances() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.featureImportances$lzycompute() : this.featureImportances;
   }

   public RandomForestModel toOld() {
      return new RandomForestModel(Algo$.MODULE$.Regression(), (org.apache.spark.mllib.tree.model.DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this._trees()), (x$4) -> x$4.toOld(), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.tree.model.DecisionTreeModel.class)));
   }

   public MLWriter write() {
      return new RandomForestRegressionModelWriter(this);
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$1(final Broadcast bcastModel$1, final Vector features) {
      return ((RandomForestRegressionModel)bcastModel$1.value()).predict(features);
   }

   // $FF: synthetic method
   public static final double $anonfun$predict$1(final Vector features$1, final DecisionTreeRegressionModel x$3) {
      return x$3.rootNode().predictImpl(features$1).prediction();
   }

   public RandomForestRegressionModel(final String uid, final DecisionTreeRegressionModel[] _trees, final int numFeatures) {
      this.uid = uid;
      this._trees = _trees;
      this.numFeatures = numFeatures;
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      TreeEnsembleParams.$init$(this);
      RandomForestParams.$init$(this);
      TreeEnsembleRegressorParams.$init$(this);
      HasVarianceImpurity.$init$(this);
      TreeEnsembleModel.$init$(this);
      MLWritable.$init$(this);
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps(_trees)), () -> "RandomForestRegressionModel requires at least 1 tree.");
      Statics.releaseFence();
   }

   public RandomForestRegressionModel(final DecisionTreeRegressionModel[] trees, final int numFeatures) {
      this(Identifiable$.MODULE$.randomUID("rfr"), trees, numFeatures);
   }

   public RandomForestRegressionModel() {
      this("", (DecisionTreeRegressionModel[])(new DecisionTreeRegressionModel[]{new DecisionTreeRegressionModel()}), -1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class RandomForestRegressionModelWriter extends MLWriter {
      private final RandomForestRegressionModel instance;

      public void saveImpl(final String path) {
         JObject extraMetadata = org.json4s.JsonDSL..MODULE$.map2jvalue((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numFeatures"), BoxesRunTime.boxToInteger(this.instance.numFeatures())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numTrees"), BoxesRunTime.boxToInteger(this.instance.getNumTrees()))}))), (x) -> $anonfun$saveImpl$1(BoxesRunTime.unboxToInt(x)));
         EnsembleModelReadWrite$.MODULE$.saveImpl(this.instance, path, this.sparkSession(), extraMetadata);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$1(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      public RandomForestRegressionModelWriter(final RandomForestRegressionModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class RandomForestRegressionModelReader extends MLReader {
      private final String className = RandomForestRegressionModel.class.getName();
      private final String treeClassName = DecisionTreeRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      private String treeClassName() {
         return this.treeClassName;
      }

      public RandomForestRegressionModel load(final String path) {
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
               double[] var14 = (double[])var4._3();
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
               scala.Predef..MODULE$.require(numTrees == trees.length, () -> "RandomForestRegressionModel.load expected " + numTrees + " trees based on metadata but found " + trees.length + " trees.");
               RandomForestRegressionModel model = new RandomForestRegressionModel(metadata.uid(), trees, numFeatures);
               metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
               return model;
            }
         }

         throw new MatchError(var5);
      }

      public RandomForestRegressionModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
