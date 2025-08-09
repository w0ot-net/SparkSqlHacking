package org.apache.spark.mllib.api.python;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD.;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS;
import org.apache.spark.mllib.classification.LogisticRegressionWithSGD;
import org.apache.spark.mllib.classification.NaiveBayes$;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.clustering.BisectingKMeans;
import org.apache.spark.mllib.clustering.BisectingKMeansModel;
import org.apache.spark.mllib.clustering.DistributedLDAModel;
import org.apache.spark.mllib.clustering.DistributedLDAModel$;
import org.apache.spark.mllib.clustering.GaussianMixture;
import org.apache.spark.mllib.clustering.GaussianMixtureModel;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.clustering.LDA;
import org.apache.spark.mllib.clustering.LDAModel;
import org.apache.spark.mllib.clustering.PowerIterationClustering;
import org.apache.spark.mllib.clustering.PowerIterationClusteringModel;
import org.apache.spark.mllib.clustering.StreamingKMeansModel;
import org.apache.spark.mllib.evaluation.RankingMetrics;
import org.apache.spark.mllib.feature.ChiSqSelector;
import org.apache.spark.mllib.feature.ChiSqSelectorModel;
import org.apache.spark.mllib.feature.ElementwiseProduct;
import org.apache.spark.mllib.feature.IDF;
import org.apache.spark.mllib.feature.IDFModel;
import org.apache.spark.mllib.feature.Normalizer;
import org.apache.spark.mllib.feature.PCA;
import org.apache.spark.mllib.feature.PCAModel;
import org.apache.spark.mllib.feature.StandardScaler;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.feature.Word2Vec;
import org.apache.spark.mllib.feature.Word2VecModel;
import org.apache.spark.mllib.fpm.FPGrowth;
import org.apache.spark.mllib.fpm.FPGrowthModel;
import org.apache.spark.mllib.fpm.PrefixSpan;
import org.apache.spark.mllib.fpm.PrefixSpanModel;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.VectorUDT;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.linalg.distributed.BlockMatrix;
import org.apache.spark.mllib.linalg.distributed.CoordinateMatrix;
import org.apache.spark.mllib.linalg.distributed.IndexedRow;
import org.apache.spark.mllib.linalg.distributed.IndexedRowMatrix;
import org.apache.spark.mllib.linalg.distributed.MatrixEntry;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.mllib.optimization.L1Updater;
import org.apache.spark.mllib.optimization.SimpleUpdater;
import org.apache.spark.mllib.optimization.SquaredL2Updater;
import org.apache.spark.mllib.optimization.Updater;
import org.apache.spark.mllib.random.RandomRDDs$;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.regression.GeneralizedLinearAlgorithm;
import org.apache.spark.mllib.regression.GeneralizedLinearModel;
import org.apache.spark.mllib.regression.IsotonicRegression;
import org.apache.spark.mllib.regression.IsotonicRegressionModel;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LassoWithSGD;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;
import org.apache.spark.mllib.regression.RidgeRegressionWithSGD;
import org.apache.spark.mllib.stat.KernelDensity;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics$;
import org.apache.spark.mllib.stat.correlation.CorrelationNames$;
import org.apache.spark.mllib.stat.distribution.MultivariateGaussian;
import org.apache.spark.mllib.stat.test.ChiSqTestResult;
import org.apache.spark.mllib.stat.test.KolmogorovSmirnovTestResult;
import org.apache.spark.mllib.tree.DecisionTree$;
import org.apache.spark.mllib.tree.GradientBoostedTrees$;
import org.apache.spark.mllib.tree.RandomForest$;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy$;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.configuration.Strategy$;
import org.apache.spark.mllib.tree.impurity.Impurities$;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.loss.Losses$;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel;
import org.apache.spark.mllib.tree.model.RandomForestModel;
import org.apache.spark.mllib.util.LinearDataGenerator$;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.LongType;
import org.apache.spark.sql.types.StructField;
import scala.Enumeration;
import scala.MatchError;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d}c!B'O\u00019S\u0006\"\u00028\u0001\t\u0003y\u0007\"\u0002:\u0001\t\u0003\u0019\bbBA\u0016\u0001\u0011\u0005\u0011Q\u0006\u0005\b\u0003\u0017\u0002A\u0011BA'\u0011\u001d\t\u0019\n\u0001C\u0001\u0003+Cq!a*\u0001\t\u0003\tI\u000bC\u0004\u0002X\u0002!\t!!7\t\u000f\t\r\u0001\u0001\"\u0001\u0003\u0006!9!\u0011\u0004\u0001\u0005\u0002\tm\u0001b\u0002B\u0018\u0001\u0011\u0005!\u0011\u0007\u0005\b\u0005\u000f\u0002A\u0011\u0001B%\u0011\u001d\u0011y\u0006\u0001C\u0001\u0005CBqA! \u0001\t\u0003\u0011y\bC\u0004\u0003\b\u0002!\tA!#\t\u000f\tE\u0005\u0001\"\u0001\u0003\u0014\"9!Q\u0018\u0001\u0005\u0002\t}\u0006b\u0002Bd\u0001\u0011\u0005!\u0011\u001a\u0005\b\u0005c\u0004A\u0011\u0001Bz\u0011\u001d\u0019I\u0001\u0001C\u0001\u0007\u0017Aqa!\b\u0001\t\u0003\u0019y\u0002C\u0004\u0004N\u0001!\taa\u0014\t\u000f\r\r\u0004\u0001\"\u0001\u0004f!91q\u0012\u0001\u0005\u0002\rE\u0005bBBL\u0001\u0011\u00051\u0011\u0014\u0005\b\u0007s\u0003A\u0011AB^\u0011\u001d\u0019)\u000e\u0001C\u0001\u0007/Dqa!6\u0001\t\u0003\u0019\t\u000fC\u0004\u0004h\u0002!\ta!;\t\u000f\u0011\u0005\u0001\u0001\"\u0001\u0005\u0004!9AQ\u0005\u0001\u0005\u0002\u0011\u001d\u0002b\u0002C\u001a\u0001\u0011\u0005AQ\u0007\u0005\b\t\u000b\u0002A\u0011\u0001C$\u0011\u001d!i\u0007\u0001C\u0001\t_Bq\u0001b*\u0001\t\u0003!I\u000bC\u0004\u0005J\u0002!\t\u0001b3\t\u000f\u0011\u0015\b\u0001\"\u0001\u0005h\"9AQ\u001d\u0001\u0005\u0002\u0011=\bb\u0002C{\u0001\u0011\u0005Aq\u001f\u0005\b\u000b\u000f\u0001A\u0011AC\u0005\u0011\u001d)9\u0001\u0001C\u0001\u000b'Aq!b\b\u0001\t\u0003)\t\u0003C\u0004\u0006 \u0001!\t!b\u000e\t\u000f\u0015}\u0001\u0001\"\u0001\u0006<!9Q\u0011\t\u0001\u0005\n\u0015\r\u0003bBC$\u0001\u0011%Q\u0011\n\u0005\b\u000b#\u0002A\u0011BC*\u0011\u001d)y\u0006\u0001C\u0001\u000bCBq!\"\u001c\u0001\t\u0003)y\u0007C\u0004\u0006z\u0001!\t!b\u001f\t\u000f\u00155\u0005\u0001\"\u0001\u0006\u0010\"9Q1\u0014\u0001\u0005\u0002\u0015u\u0005bBCU\u0001\u0011\u0005Q1\u0016\u0005\b\u000b{\u0003A\u0011AC`\u0011\u001d)y\r\u0001C\u0001\u000b#Dq!\"8\u0001\t\u0003)y\u000eC\u0004\u0006p\u0002!\t!\"=\t\u000f\u0015}\b\u0001\"\u0001\u0007\u0002!9aq\u0002\u0001\u0005\u0002\u0019E\u0001b\u0002D\u0011\u0001\u0011\u0005a1\u0005\u0005\b\r+\u0002A\u0011\u0001D,\u0011\u001d19\u0007\u0001C\u0001\rSBqA\"!\u0001\t\u00031\u0019\tC\u0004\u0007 \u0002!\tA\")\t\u000f\u0019]\u0006\u0001\"\u0001\u0007:\"9a1\u001a\u0001\u0005\u0002\u00195\u0007b\u0002Df\u0001\u0011\u0005a1\u001d\u0005\b\r[\u0004A\u0011\u0001Dx\u0011\u001d1i\u0010\u0001C\u0001\r\u007fDqa\"\u0004\u0001\t\u00039y\u0001C\u0004\b&\u0001!\tab\n\t\u000f\u001d5\u0002\u0001\"\u0001\b0!9qQ\u0007\u0001\u0005\u0002\u001d]\u0002bBD\u001f\u0001\u0011\u0005qq\b\u0005\b\u000f\u000f\u0002A\u0011AD%\u0011\u001d9y\u0005\u0001C\u0001\u000f#Bqab\u0016\u0001\t\u00039IF\u0001\bQsRDwN\\'M\u0019&\u0014\u0017\tU%\u000b\u0005=\u0003\u0016A\u00029zi\"|gN\u0003\u0002R%\u0006\u0019\u0011\r]5\u000b\u0005M#\u0016!B7mY&\u0014'BA+W\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0006,\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0006\u0019qN]4\u0014\u0007\u0001Y\u0016\r\u0005\u0002]?6\tQLC\u0001_\u0003\u0015\u00198-\u00197b\u0013\t\u0001WL\u0001\u0004B]f\u0014VM\u001a\t\u0003E.t!aY5\u000f\u0005\u0011DW\"A3\u000b\u0005\u0019<\u0017A\u0002\u001fs_>$hh\u0001\u0001\n\u0003yK!A[/\u0002\u000fA\f7m[1hK&\u0011A.\u001c\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Uv\u000ba\u0001P5oSRtD#\u00019\u0011\u0005E\u0004Q\"\u0001(\u0002#1|\u0017\r\u001a'bE\u0016dW\r\u001a)pS:$8\u000fF\u0004u\u0003\u0007\ti!!\t\u0011\u0007UL80D\u0001w\u0015\t9\b0\u0001\u0003kCZ\f'BA)U\u0013\tQhOA\u0004KCZ\f'\u000b\u0012#\u0011\u0005q|X\"A?\u000b\u0005y\u0014\u0016A\u0003:fOJ,7o]5p]&\u0019\u0011\u0011A?\u0003\u00191\u000b'-\u001a7fIB{\u0017N\u001c;\t\u000f\u0005\u0015!\u00011\u0001\u0002\b\u0005\u0019!n]2\u0011\u0007U\fI!C\u0002\u0002\fY\u0014\u0001CS1wCN\u0003\u0018M]6D_:$X\r\u001f;\t\u000f\u0005=!\u00011\u0001\u0002\u0012\u0005!\u0001/\u0019;i!\u0011\t\u0019\"a\u0007\u000f\t\u0005U\u0011q\u0003\t\u0003IvK1!!\u0007^\u0003\u0019\u0001&/\u001a3fM&!\u0011QDA\u0010\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011D/\t\u000f\u0005\r\"\u00011\u0001\u0002&\u0005iQ.\u001b8QCJ$\u0018\u000e^5p]N\u00042\u0001XA\u0014\u0013\r\tI#\u0018\u0002\u0004\u0013:$\u0018a\u00037pC\u00124Vm\u0019;peN$b!a\f\u0002H\u0005%\u0003CBA\u0019\u0003o\tY$\u0004\u0002\u00024)\u0019\u0011Q\u0007+\u0002\u0007I$G-\u0003\u0003\u0002:\u0005M\"a\u0001*E\tB!\u0011QHA\"\u001b\t\tyDC\u0002\u0002BI\u000ba\u0001\\5oC2<\u0017\u0002BA#\u0003\u007f\u0011aAV3di>\u0014\bbBA\u0003\u0007\u0001\u0007\u0011q\u0001\u0005\b\u0003\u001f\u0019\u0001\u0019AA\t\u0003Q!(/Y5o%\u0016<'/Z:tS>tWj\u001c3fYRA\u0011qJA5\u0003\u0017\u000by\t\u0005\u0004\u0002R\u0005e\u0013QL\u0007\u0003\u0003'RA!!\u0016\u0002X\u0005!Q\u000f^5m\u0015\u00059\u0018\u0002BA.\u0003'\u0012A\u0001T5tiB!\u0011qLA3\u001b\t\t\tG\u0003\u0003\u0002d\u0005]\u0013\u0001\u00027b]\u001eLA!a\u001a\u0002b\t1qJ\u00196fGRDq!a\u001b\u0005\u0001\u0004\ti'A\u0004mK\u0006\u0014h.\u001a:1\t\u0005=\u0014\u0011\u0010\t\u0006y\u0006E\u0014QO\u0005\u0004\u0003gj(AG$f]\u0016\u0014\u0018\r\\5{K\u0012d\u0015N\\3be\u0006cwm\u001c:ji\"l\u0007\u0003BA<\u0003sb\u0001\u0001\u0002\u0007\u0002|\u0005%\u0014\u0011!A\u0001\u0006\u0003\tiHA\u0002`IE\nB!a \u0002\u0006B\u0019A,!!\n\u0007\u0005\rULA\u0004O_RD\u0017N\\4\u0011\u0007q\f9)C\u0002\u0002\nv\u0014acR3oKJ\fG.\u001b>fI2Kg.Z1s\u001b>$W\r\u001c\u0005\u0007\u0003\u001b#\u0001\u0019\u0001;\u0002\t\u0011\fG/\u0019\u0005\b\u0003##\u0001\u0019AA\u001e\u00039Ig.\u001b;jC2<V-[4iiN\fAcZ3u+B$\u0017\r^3s\rJ|Wn\u0015;sS:<G\u0003BAL\u0003G\u0003B!!'\u0002 6\u0011\u00111\u0014\u0006\u0004\u0003;\u0013\u0016\u0001D8qi&l\u0017N_1uS>t\u0017\u0002BAQ\u00037\u0013q!\u00169eCR,'\u000fC\u0004\u0002&\u0016\u0001\r!!\u0005\u0002\u000fI,w\rV=qK\u0006!BO]1j]\nK7/Z2uS:<7*T3b]N$B\"a+\u00028\u0006m\u0016qXAb\u0003\u001b\u0004B!!,\u000246\u0011\u0011q\u0016\u0006\u0004\u0003c\u0013\u0016AC2mkN$XM]5oO&!\u0011QWAX\u0005Q\u0011\u0015n]3di&twmS'fC:\u001cXj\u001c3fY\"9\u0011Q\u0012\u0004A\u0002\u0005e\u0006\u0003B;z\u0003wAq!!0\u0007\u0001\u0004\t)#A\u0001l\u0011\u001d\t\tM\u0002a\u0001\u0003K\tQ\"\\1y\u0013R,'/\u0019;j_:\u001c\bbBAc\r\u0001\u0007\u0011qY\u0001\u0018[&tG)\u001b<jg&\u0014G.Z\"mkN$XM]*ju\u0016\u00042\u0001XAe\u0013\r\tY-\u0018\u0002\u0007\t>,(\r\\3\t\u000f\u0005=g\u00011\u0001\u0002R\u0006!1/Z3e!\u0011\ty&a5\n\t\u0005U\u0017\u0011\r\u0002\u0005\u0019>tw-A\u0011ue\u0006Lg\u000eT5oK\u0006\u0014(+Z4sKN\u001c\u0018n\u001c8N_\u0012,GnV5uQN;E\t\u0006\f\u0002P\u0005m\u0017Q\\Aq\u0003K\fI/a;\u0002p\u0006E\u00181`A\u0000\u0011\u0019\tii\u0002a\u0001i\"9\u0011q\\\u0004A\u0002\u0005\u0015\u0012!\u00048v[&#XM]1uS>t7\u000fC\u0004\u0002d\u001e\u0001\r!a2\u0002\u0011M$X\r]*ju\u0016Dq!a:\b\u0001\u0004\t9-A\tnS:L')\u0019;dQ\u001a\u0013\u0018m\u0019;j_:Dq!!%\b\u0001\u0004\tY\u0004C\u0004\u0002n\u001e\u0001\r!a2\u0002\u0011I,w\rU1sC6Dq!!*\b\u0001\u0004\t\t\u0002C\u0004\u0002t\u001e\u0001\r!!>\u0002\u0013%tG/\u001a:dKB$\bc\u0001/\u0002x&\u0019\u0011\u0011`/\u0003\u000f\t{w\u000e\\3b]\"9\u0011Q`\u0004A\u0002\u0005U\u0018\u0001\u0004<bY&$\u0017\r^3ECR\f\u0007b\u0002B\u0001\u000f\u0001\u0007\u0011qY\u0001\u000fG>tg/\u001a:hK:\u001cW\rV8m\u0003Y!(/Y5o\u0019\u0006\u001c8o\\'pI\u0016dw+\u001b;i'\u001e#E\u0003FA(\u0005\u000f\u0011IAa\u0003\u0003\u000e\t=!\u0011\u0003B\n\u0005+\u00119\u0002\u0003\u0004\u0002\u000e\"\u0001\r\u0001\u001e\u0005\b\u0003?D\u0001\u0019AA\u0013\u0011\u001d\t\u0019\u000f\u0003a\u0001\u0003\u000fDq!!<\t\u0001\u0004\t9\rC\u0004\u0002h\"\u0001\r!a2\t\u000f\u0005E\u0005\u00021\u0001\u0002<!9\u00111\u001f\u0005A\u0002\u0005U\bbBA\u007f\u0011\u0001\u0007\u0011Q\u001f\u0005\b\u0005\u0003A\u0001\u0019AAd\u0003Y!(/Y5o%&$w-Z'pI\u0016dw+\u001b;i'\u001e#E\u0003FA(\u0005;\u0011yB!\t\u0003$\t\u0015\"q\u0005B\u0015\u0005W\u0011i\u0003\u0003\u0004\u0002\u000e&\u0001\r\u0001\u001e\u0005\b\u0003?L\u0001\u0019AA\u0013\u0011\u001d\t\u0019/\u0003a\u0001\u0003\u000fDq!!<\n\u0001\u0004\t9\rC\u0004\u0002h&\u0001\r!a2\t\u000f\u0005E\u0015\u00021\u0001\u0002<!9\u00111_\u0005A\u0002\u0005U\bbBA\u007f\u0013\u0001\u0007\u0011Q\u001f\u0005\b\u0005\u0003I\u0001\u0019AAd\u0003Q!(/Y5o'ZkUj\u001c3fY^KG\u000f[*H\tR1\u0012q\nB\u001a\u0005k\u00119D!\u000f\u0003<\tu\"q\bB!\u0005\u0007\u0012)\u0005\u0003\u0004\u0002\u000e*\u0001\r\u0001\u001e\u0005\b\u0003?T\u0001\u0019AA\u0013\u0011\u001d\t\u0019O\u0003a\u0001\u0003\u000fDq!!<\u000b\u0001\u0004\t9\rC\u0004\u0002h*\u0001\r!a2\t\u000f\u0005E%\u00021\u0001\u0002<!9\u0011Q\u0015\u0006A\u0002\u0005E\u0001bBAz\u0015\u0001\u0007\u0011Q\u001f\u0005\b\u0003{T\u0001\u0019AA{\u0011\u001d\u0011\tA\u0003a\u0001\u0003\u000f\f1\u0005\u001e:bS:dunZ5ti&\u001c'+Z4sKN\u001c\u0018n\u001c8N_\u0012,GnV5uQN;E\t\u0006\f\u0002P\t-#Q\nB(\u0005#\u0012\u0019F!\u0016\u0003X\te#1\fB/\u0011\u0019\tii\u0003a\u0001i\"9\u0011q\\\u0006A\u0002\u0005\u0015\u0002bBAr\u0017\u0001\u0007\u0011q\u0019\u0005\b\u0003O\\\u0001\u0019AAd\u0011\u001d\t\tj\u0003a\u0001\u0003wAq!!<\f\u0001\u0004\t9\rC\u0004\u0002&.\u0001\r!!\u0005\t\u000f\u0005M8\u00021\u0001\u0002v\"9\u0011Q`\u0006A\u0002\u0005U\bb\u0002B\u0001\u0017\u0001\u0007\u0011qY\u0001&iJ\f\u0017N\u001c'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:lu\u000eZ3m/&$\b\u000e\u0014\"G\u000fN#b#a\u0014\u0003d\t\u0015$q\rB5\u0005W\u0012iGa\u001c\u0003t\t]$\u0011\u0010\u0005\u0007\u0003\u001bc\u0001\u0019\u0001;\t\u000f\u0005}G\u00021\u0001\u0002&!9\u0011\u0011\u0013\u0007A\u0002\u0005m\u0002bBAw\u0019\u0001\u0007\u0011q\u0019\u0005\b\u0003Kc\u0001\u0019AA\t\u0011\u001d\t\u0019\u0010\u0004a\u0001\u0003kDqA!\u001d\r\u0001\u0004\t)#A\u0006d_J\u0014Xm\u0019;j_:\u001c\bb\u0002B;\u0019\u0001\u0007\u0011qY\u0001\ni>dWM]1oG\u0016Dq!!@\r\u0001\u0004\t)\u0010C\u0004\u0003|1\u0001\r!!\n\u0002\u00159,Xn\u00117bgN,7/\u0001\u000bue\u0006LgNT1jm\u0016\u0014\u0015-_3t\u001b>$W\r\u001c\u000b\u0007\u0003\u001f\u0012\tIa!\t\r\u00055U\u00021\u0001u\u0011\u001d\u0011))\u0004a\u0001\u0003\u000f\fa\u0001\\1nE\u0012\f\u0017\u0001\b;sC&t\u0017j]8u_:L7MU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\u000b\u0007\u0003\u001f\u0012YI!$\t\u000f\u00055e\u00021\u0001\u0002:\"9!q\u0012\bA\u0002\u0005U\u0018\u0001C5t_R|g.[2\u0002!Q\u0014\u0018-\u001b8L\u001b\u0016\fgn]'pI\u0016dG\u0003\u0006BK\u00057\u0013iJa(\u0003\"\n\u0015&q\u0015BV\u0005_\u0013I\f\u0005\u0003\u0002.\n]\u0015\u0002\u0002BM\u0003_\u00131bS'fC:\u001cXj\u001c3fY\"9\u0011QR\bA\u0002\u0005e\u0006bBA_\u001f\u0001\u0007\u0011Q\u0005\u0005\b\u0003\u0003|\u0001\u0019AA\u0013\u0011\u001d\u0011\u0019k\u0004a\u0001\u0003#\t!#\u001b8ji&\fG.\u001b>bi&|g.T8eK\"9\u0011qZ\bA\u0002\u0005E\u0007b\u0002BU\u001f\u0001\u0007\u0011QE\u0001\u0014S:LG/[1mSj\fG/[8o'R,\u0007o\u001d\u0005\b\u0005[{\u0001\u0019AAd\u0003\u001d)\u0007o]5m_:DqA!-\u0010\u0001\u0004\u0011\u0019,\u0001\u0007j]&$\u0018.\u00197N_\u0012,G\u000e\u0005\u0004\u0002R\tU\u00161H\u0005\u0005\u0005o\u000b\u0019FA\u0005BeJ\f\u0017\u0010T5ti\"9!1X\bA\u0002\u0005E\u0011a\u00043jgR\fgnY3NK\u0006\u001cXO]3\u0002-\r|W\u000e];uK\u000e{7\u000f^&nK\u0006t7/T8eK2$b!a2\u0003B\n\r\u0007bBAG!\u0001\u0007\u0011\u0011\u0018\u0005\b\u0005\u000b\u0004\u0002\u0019\u0001BZ\u0003\u001d\u0019WM\u001c;feN\f\u0011\u0004\u001e:bS:<\u0015-^:tS\u0006tW*\u001b=ukJ,Wj\u001c3fYR\u0011\"1\u001aBi\u0005'\u0014)Na6\u0003Z\nm'\u0011\u001dBs!\r\t(QZ\u0005\u0004\u0005\u001ft%aG$bkN\u001c\u0018.\u00198NSb$XO]3N_\u0012,Gn\u0016:baB,'\u000fC\u0004\u0002\u000eF\u0001\r!!/\t\u000f\u0005u\u0016\u00031\u0001\u0002&!9!\u0011A\tA\u0002\u0005\u001d\u0007bBAa#\u0001\u0007\u0011Q\u0005\u0005\b\u0003\u001f\f\u0002\u0019AAi\u0011\u001d\u0011i.\u0005a\u0001\u0005?\f1#\u001b8ji&\fG.T8eK2<V-[4iiN\u0004b!!\u0015\u00036\u0006\u001d\u0007b\u0002Br#\u0001\u0007!1W\u0001\u000fS:LG/[1m\u001b>$W\r\\'v\u0011\u001d\u00119/\u0005a\u0001\u0005S\f\u0011#\u001b8ji&\fG.T8eK2\u001c\u0016nZ7b!\u0019\t\tF!.\u0003lB!\u0011Q\bBw\u0013\u0011\u0011y/a\u0010\u0003\r5\u000bGO]5y\u00039\u0001(/\u001a3jGR\u001cvN\u001a;H\u001b6#\"\"a\f\u0003v\n](1`B\u0003\u0011\u001d\tiI\u0005a\u0001\u0003sCqA!?\u0013\u0001\u0004\tY$\u0001\u0002xi\"9!Q \nA\u0002\t}\u0018AA7v!\u0015a6\u0011AA/\u0013\r\u0019\u0019!\u0018\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\u0007\u000f\u0011\u0002\u0019\u0001B\u0000\u0003\t\u0019\u0018.\u0001\u0012ue\u0006Lg\u000eU8xKJLE/\u001a:bi&|gn\u00117vgR,'/\u001b8h\u001b>$W\r\u001c\u000b\u000b\u0007\u001b\u0019\u0019b!\u0006\u0004\u0018\re\u0001\u0003BAW\u0007\u001fIAa!\u0005\u00020\ni\u0002k\\<fe&#XM]1uS>t7\t\\;ti\u0016\u0014\u0018N\\4N_\u0012,G\u000eC\u0004\u0002\u000eN\u0001\r!!/\t\u000f\u0005u6\u00031\u0001\u0002&!9\u0011\u0011Y\nA\u0002\u0005\u0015\u0002bBB\u000e'\u0001\u0007\u0011\u0011C\u0001\tS:LG/T8eK\u0006iAO]1j]\u0006c5+T8eK2$\u0002c!\t\u0004.\re2QHB!\u0007\u0007\u001a9ea\u0013\u0011\t\r\r2\u0011F\u0007\u0003\u0007KQ1aa\nS\u00039\u0011XmY8n[\u0016tG-\u0019;j_:LAaa\u000b\u0004&\tAR*\u0019;sSb4\u0015m\u0019;pe&T\u0018\r^5p]6{G-\u001a7\t\u000f\r=B\u00031\u0001\u00042\u0005Y!/\u0019;j]\u001e\u001c(J\u0015#E!\u0011)\u0018pa\r\u0011\t\r\r2QG\u0005\u0005\u0007o\u0019)C\u0001\u0004SCRLgn\u001a\u0005\b\u0007w!\u0002\u0019AA\u0013\u0003\u0011\u0011\u0018M\\6\t\u000f\r}B\u00031\u0001\u0002&\u0005Q\u0011\u000e^3sCRLwN\\:\t\u000f\t\u0015E\u00031\u0001\u0002H\"91Q\t\u000bA\u0002\u0005\u0015\u0012A\u00022m_\u000e\\7\u000fC\u0004\u0004JQ\u0001\r!!>\u0002\u00179|gN\\3hCRLg/\u001a\u0005\b\u0003\u001f$\u0002\u0019AAi\u0003U!(/Y5o\u00136\u0004H.[2ji\u0006c5+T8eK2$\"c!\t\u0004R\rM3QKB,\u00073\u001aYfa\u0018\u0004b!91qF\u000bA\u0002\rE\u0002bBB\u001e+\u0001\u0007\u0011Q\u0005\u0005\b\u0007\u007f)\u0002\u0019AA\u0013\u0011\u001d\u0011))\u0006a\u0001\u0003\u000fDqa!\u0012\u0016\u0001\u0004\t)\u0003C\u0004\u0004^U\u0001\r!a2\u0002\u000b\u0005d\u0007\u000f[1\t\u000f\r%S\u00031\u0001\u0002v\"9\u0011qZ\u000bA\u0002\u0005E\u0017!\u0004;sC&tG\nR!N_\u0012,G\u000e\u0006\n\u0004h\r54\u0011PB>\u0007{\u001a\ti!\"\u0004\b\u000e-\u0005cA9\u0004j%\u001911\u000e(\u0003\u001f1#\u0015)T8eK2<&/\u00199qKJDq!!$\u0017\u0001\u0004\u0019y\u0007\u0005\u0003vs\u000eE\u0004CBA)\u00033\u001a\u0019\bE\u0002]\u0007kJ1aa\u001e^\u0005\r\te.\u001f\u0005\b\u0003{3\u0002\u0019AA\u0013\u0011\u001d\t\tM\u0006a\u0001\u0003KAqaa \u0017\u0001\u0004\t9-\u0001\te_\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]\"911\u0011\fA\u0002\u0005\u001d\u0017A\u0005;pa&\u001c7i\u001c8dK:$(/\u0019;j_:Dq!a4\u0017\u0001\u0004\t\t\u000eC\u0004\u0004\nZ\u0001\r!!\n\u0002%\rDWmY6q_&tG/\u00138uKJ4\u0018\r\u001c\u0005\b\u0007\u001b3\u0002\u0019AA\t\u0003%y\u0007\u000f^5nSj,'/\u0001\u0007m_\u0006$G\nR!N_\u0012,G\u000e\u0006\u0004\u0004h\rM5Q\u0013\u0005\b\u0003\u000b9\u0002\u0019AA\u0004\u0011\u001d\tya\u0006a\u0001\u0003#\t!\u0003\u001e:bS:4\u0005k\u0012:poRDWj\u001c3fYRA11TBT\u0007c\u001b)\f\u0005\u0004\u0004\u001e\u000e\r61O\u0007\u0003\u0007?S1a!)S\u0003\r1\u0007/\\\u0005\u0005\u0007K\u001byJA\u0007G!\u001e\u0013xn\u001e;i\u001b>$W\r\u001c\u0005\b\u0003\u001bC\u0002\u0019ABU!\u0011)\u0018pa+\u0011\r\u0005}3QVB:\u0013\u0011\u0019y+!\u0019\u0003\u0011%#XM]1cY\u0016Dqaa-\u0019\u0001\u0004\t9-\u0001\u0006nS:\u001cV\u000f\u001d9peRDqaa.\u0019\u0001\u0004\t)#A\u0007ok6\u0004\u0016M\u001d;ji&|gn]\u0001\u0015iJ\f\u0017N\u001c)sK\u001aL\u0007p\u00159b]6{G-\u001a7\u0015\u0015\ru61YBf\u0007\u001b\u001c\t\u000eE\u0002r\u0007\u007fK1a!1O\u0005Y\u0001&/\u001a4jqN\u0003\u0018M\\'pI\u0016dwK]1qa\u0016\u0014\bbBAG3\u0001\u00071Q\u0019\t\u0005kf\u001c9\r\u0005\u0004\u0002R\tU6\u0011\u001a\t\u0007\u0003#\u0012)la\u001d\t\u000f\rM\u0016\u00041\u0001\u0002H\"91qZ\rA\u0002\u0005\u0015\u0012\u0001E7bqB\u000bG\u000f^3s]2+gn\u001a;i\u0011\u001d\u0019\u0019.\u0007a\u0001\u0003K\tq\u0002\\8dC2\u0004&o\u001c6E\u0005NK'0Z\u0001\u0010]>\u0014X.\u00197ju\u00164Vm\u0019;peR1\u00111HBm\u0007;Dqaa7\u001b\u0001\u0004\t9-A\u0001q\u0011\u001d\u0019yN\u0007a\u0001\u0003w\taA^3di>\u0014HCBA]\u0007G\u001c)\u000fC\u0004\u0004\\n\u0001\r!a2\t\u000f\u0005U2\u00041\u0001\u0002:\u0006\tb-\u001b;Ti\u0006tG-\u0019:e'\u000e\fG.\u001a:\u0015\u0011\r-8q_B~\u0007\u007f\u0004Ba!<\u0004t6\u00111q\u001e\u0006\u0004\u0007c\u0014\u0016a\u00024fCR,(/Z\u0005\u0005\u0007k\u001cyOA\nTi\u0006tG-\u0019:e'\u000e\fG.\u001a:N_\u0012,G\u000eC\u0004\u0004zr\u0001\r!!>\u0002\u0011]LG\u000f['fC:Dqa!@\u001d\u0001\u0004\t)0A\u0004xSRD7\u000b\u001e3\t\u000f\u00055E\u00041\u0001\u0002:\u0006\u0001b-\u001b;DQ&\u001c\u0016oU3mK\u000e$xN\u001d\u000b\u0011\t\u000b!Y\u0001b\u0004\u0005\u0014\u0011]A1\u0004C\u0010\tG\u0001Ba!<\u0005\b%!A\u0011BBx\u0005I\u0019\u0005.[*r'\u0016dWm\u0019;pe6{G-\u001a7\t\u000f\u00115Q\u00041\u0001\u0002\u0012\u0005a1/\u001a7fGR|'\u000fV=qK\"9A\u0011C\u000fA\u0002\u0005\u0015\u0012A\u00048v[R{\u0007OR3biV\u0014Xm\u001d\u0005\b\t+i\u0002\u0019AAd\u0003)\u0001XM]2f]RLG.\u001a\u0005\b\t3i\u0002\u0019AAd\u0003\r1\u0007O\u001d\u0005\b\t;i\u0002\u0019AAd\u0003\r1GM\u001d\u0005\b\tCi\u0002\u0019AAd\u0003\r1w/\u001a\u0005\u0007\u0003\u001bk\u0002\u0019\u0001;\u0002\r\u0019LG\u000fU\"B)\u0019!I\u0003b\f\u00052A!1Q\u001eC\u0016\u0013\u0011!ica<\u0003\u0011A\u001b\u0015)T8eK2Dq!!0\u001f\u0001\u0004\t)\u0003C\u0004\u0002\u000ez\u0001\r!!/\u0002\r\u0019LG/\u0013#G)\u0019!9\u0004\"\u0010\u0005BA!1Q\u001eC\u001d\u0013\u0011!Yda<\u0003\u0011%#e)T8eK2Dq\u0001b\u0010 \u0001\u0004\t)#\u0001\u0006nS:$un\u0019$sKFDq\u0001b\u0011 \u0001\u0004\tI,A\u0004eCR\f7/\u001a;\u0002%Q\u0014\u0018-\u001b8X_J$'GV3d\u001b>$W\r\u001c\u000b\u0013\t\u0013\"y\u0005b\u0016\u0005\\\u0011}C\u0011\rC2\tK\"I\u0007E\u0002r\t\u0017J1\u0001\"\u0014O\u0005Q9vN\u001d33-\u0016\u001cWj\u001c3fY^\u0013\u0018\r\u001d9fe\"9A\u0011\u000b\u0011A\u0002\u0011M\u0013\u0001\u00033bi\u0006T%\u000b\u0012#\u0011\tULHQ\u000b\t\u0007\u0003#\u0012),!\u0005\t\u000f\u0011e\u0003\u00051\u0001\u0002&\u0005Qa/Z2u_J\u001c\u0016N_3\t\u000f\u0011u\u0003\u00051\u0001\u0002H\u0006aA.Z1s]&twMU1uK\"91q\u0017\u0011A\u0002\u0005\u0015\u0002bBApA\u0001\u0007\u0011Q\u0005\u0005\b\u0003\u001f\u0004\u0003\u0019AAi\u0011\u001d!9\u0007\ta\u0001\u0003K\t\u0001\"\\5o\u0007>,h\u000e\u001e\u0005\b\tW\u0002\u0003\u0019AA\u0013\u0003)9\u0018N\u001c3poNK'0Z\u0001\u0017iJ\f\u0017N\u001c#fG&\u001c\u0018n\u001c8Ue\u0016,Wj\u001c3fYR!B\u0011\u000fCA\t\u0007#9\t\"#\u0005\u0014\u0012]E1\u0014CP\tG\u0003B\u0001b\u001d\u0005~5\u0011AQ\u000f\u0006\u0005\to\"I(A\u0003n_\u0012,GNC\u0002\u0005|I\u000bA\u0001\u001e:fK&!Aq\u0010C;\u0005E!UmY5tS>tGK]3f\u001b>$W\r\u001c\u0005\u0007\u0003\u001b\u000b\u0003\u0019\u0001;\t\u000f\u0011\u0015\u0015\u00051\u0001\u0002\u0012\u00059\u0011\r\\4p'R\u0014\bb\u0002B>C\u0001\u0007\u0011Q\u0005\u0005\b\t\u0017\u000b\u0003\u0019\u0001CG\u0003]\u0019\u0017\r^3h_JL7-\u00197GK\u0006$XO]3t\u0013:4w\u000e\u0005\u0005\u0002R\u0011=\u0015QEA\u0013\u0013\u0011!\t*a\u0015\u0003\u00075\u000b\u0007\u000fC\u0004\u0005\u0016\u0006\u0002\r!!\u0005\u0002\u0017%l\u0007/\u001e:jif\u001cFO\u001d\u0005\b\t3\u000b\u0003\u0019AA\u0013\u0003!i\u0017\r\u001f#faRD\u0007b\u0002COC\u0001\u0007\u0011QE\u0001\b[\u0006D()\u001b8t\u0011\u001d!\t+\ta\u0001\u0003K\t1#\\5o\u0013:\u001cH/\u00198dKN\u0004VM\u001d(pI\u0016Dq\u0001\"*\"\u0001\u0004\t9-A\u0006nS:LeNZ8HC&t\u0017A\u0006;sC&t'+\u00198e_64uN]3ti6{G-\u001a7\u0015-\u0011-F\u0011\u0017CZ\tk#9\f\"/\u0005>\u0012\u0005G1\u0019Cc\t\u000f\u0004B\u0001b\u001d\u0005.&!Aq\u0016C;\u0005E\u0011\u0016M\u001c3p[\u001a{'/Z:u\u001b>$W\r\u001c\u0005\u0007\u0003\u001b\u0013\u0003\u0019\u0001;\t\u000f\u0011\u0015%\u00051\u0001\u0002\u0012!9!1\u0010\u0012A\u0002\u0005\u0015\u0002b\u0002CFE\u0001\u0007AQ\u0012\u0005\b\tw\u0013\u0003\u0019AA\u0013\u0003!qW/\u001c+sK\u0016\u001c\bb\u0002C`E\u0001\u0007\u0011\u0011C\u0001\u0016M\u0016\fG/\u001e:f'V\u00147/\u001a;TiJ\fG/Z4z\u0011\u001d!)J\ta\u0001\u0003#Aq\u0001\"'#\u0001\u0004\t)\u0003C\u0004\u0005\u001e\n\u0002\r!!\n\t\u000f\u0005='\u00051\u0001\u0002R\u0006qBO]1j]\u001e\u0013\u0018\rZ5f]R\u0014un\\:uK\u0012$&/Z3t\u001b>$W\r\u001c\u000b\u0013\t\u001b$\u0019\u000e\"6\u0005X\u0012eGQ\u001cCp\tC$\u0019\u000f\u0005\u0003\u0005t\u0011=\u0017\u0002\u0002Ci\tk\u0012\u0011d\u0012:bI&,g\u000e\u001e\"p_N$X\r\u001a+sK\u0016\u001cXj\u001c3fY\"1\u0011QR\u0012A\u0002QDq\u0001\"\"$\u0001\u0004\t\t\u0002C\u0004\u0005\f\u000e\u0002\r\u0001\"$\t\u000f\u0011m7\u00051\u0001\u0002\u0012\u00059An\\:t'R\u0014\bbBApG\u0001\u0007\u0011Q\u0005\u0005\b\t;\u001a\u0003\u0019AAd\u0011\u001d!Ij\ta\u0001\u0003KAq\u0001\"($\u0001\u0004\t)#\u0001\rfY\u0016lWM\u001c;xSN,\u0007K]8ek\u000e$h+Z2u_J$b!a\u000f\u0005j\u00125\bb\u0002CvI\u0001\u0007\u00111H\u0001\u000eg\u000e\fG.\u001b8h-\u0016\u001cGo\u001c:\t\u000f\r}G\u00051\u0001\u0002<Q1\u0011\u0011\u0018Cy\tgDq\u0001b;&\u0001\u0004\tY\u0004C\u0004\u0004`\u0016\u0002\r!!/\u0002\u0011\r|Gn\u0015;biN$B\u0001\"?\u0006\u0006A!A1`C\u0001\u001b\t!iPC\u0002\u0005\u0000J\u000bAa\u001d;bi&!Q1\u0001C\u007f\u0005yiU\u000f\u001c;jm\u0006\u0014\u0018.\u0019;f'R\fG/[:uS\u000e\fGnU;n[\u0006\u0014\u0018\u0010C\u0004\u00026\u0019\u0002\r!!/\u0002\t\r|'O\u001d\u000b\u0007\u0005W,Y!b\u0004\t\u000f\u00155q\u00051\u0001\u0002:\u0006\t\u0001\u0010C\u0004\u0006\u0012\u001d\u0002\r!!\u0005\u0002\r5,G\u000f[8e)!\t9-\"\u0006\u0006\u001a\u0015u\u0001bBC\u0007Q\u0001\u0007Qq\u0003\t\u0005kf\f9\rC\u0004\u0006\u001c!\u0002\r!b\u0006\u0002\u0003eDq!\"\u0005)\u0001\u0004\t\t\"A\u0005dQ&\u001c\u0016\u000fV3tiR1Q1EC\u0018\u000bg\u0001B!\"\n\u0006,5\u0011Qq\u0005\u0006\u0005\u000bS!i0\u0001\u0003uKN$\u0018\u0002BC\u0017\u000bO\u0011qb\u00115j'F$Vm\u001d;SKN,H\u000e\u001e\u0005\b\u000bcI\u0003\u0019AA\u001e\u0003!y'm]3sm\u0016$\u0007bBC\u001bS\u0001\u0007\u00111H\u0001\tKb\u0004Xm\u0019;fIR!Q1EC\u001d\u0011\u001d)\tD\u000ba\u0001\u0005W$B!\"\u0010\u0006@A)Al!\u0001\u0006$!1\u0011QR\u0016A\u0002Q\fAcZ3u\u0007>\u0014(OT1nK>\u0013H)\u001a4bk2$H\u0003BA\t\u000b\u000bBq!\"\u0005-\u0001\u0004\t\t\"\u0001\thKR\u001cV-\u001a3Pe\u0012+g-Y;miR!Q1JC(!\raVQJ\u0005\u0004\u0003+l\u0006bBAh[\u0001\u0007\u0011\u0011[\u0001\u001aO\u0016$h*^7QCJ$\u0018\u000e^5p]N|%\u000fR3gCVdG\u000f\u0006\u0004\u0002&\u0015USQ\f\u0005\b\u0007os\u0003\u0019AC,!\u0011\ty&\"\u0017\n\t\u0015m\u0013\u0011\r\u0002\b\u0013:$XmZ3s\u0011\u001d\t)A\fa\u0001\u0003\u000f\t!\"\u001e8jM>\u0014XN\u0015#E)))9\"b\u0019\u0006f\u0015%T1\u000e\u0005\b\u0003\u000by\u0003\u0019AA\u0004\u0011\u001d)9g\fa\u0001\u000b\u0017\nAa]5{K\"91qW\u0018A\u0002\u0015]\u0003bBAh_\u0001\u0007\u0011\u0011[\u0001\n]>\u0014X.\u00197S\t\u0012#\"\"b\u0006\u0006r\u0015MTQOC<\u0011\u001d\t)\u0001\ra\u0001\u0003\u000fAq!b\u001a1\u0001\u0004)Y\u0005C\u0004\u00048B\u0002\r!b\u0016\t\u000f\u0005=\u0007\u00071\u0001\u0002R\u0006aAn\\4O_Jl\u0017\r\u001c*E\tRqQqCC?\u000b\u007f*\u0019)b\"\u0006\n\u0016-\u0005bBA\u0003c\u0001\u0007\u0011q\u0001\u0005\b\u000b\u0003\u000b\u0004\u0019AAd\u0003\u0011iW-\u00198\t\u000f\u0015\u0015\u0015\u00071\u0001\u0002H\u0006\u00191\u000f\u001e3\t\u000f\u0015\u001d\u0014\u00071\u0001\u0006L!91qW\u0019A\u0002\u0015]\u0003bBAhc\u0001\u0007\u0011\u0011[\u0001\u000ba>L7o]8o%\u0012#E\u0003DC\f\u000b#+\u0019*\"&\u0006\u0018\u0016e\u0005bBA\u0003e\u0001\u0007\u0011q\u0001\u0005\b\u000b\u0003\u0013\u0004\u0019AAd\u0011\u001d)9G\ra\u0001\u000b\u0017Bqaa.3\u0001\u0004)9\u0006C\u0004\u0002PJ\u0002\r!!5\u0002\u001d\u0015D\bo\u001c8f]RL\u0017\r\u001c*E\tRaQqCCP\u000bC+\u0019+\"*\u0006(\"9\u0011QA\u001aA\u0002\u0005\u001d\u0001bBCAg\u0001\u0007\u0011q\u0019\u0005\b\u000bO\u001a\u0004\u0019AC&\u0011\u001d\u00199l\ra\u0001\u000b/Bq!a44\u0001\u0004\t\t.\u0001\u0005hC6l\u0017M\u0015#E)9)9\"\",\u00060\u0016MVqWC]\u000bwCq!!\u00025\u0001\u0004\t9\u0001C\u0004\u00062R\u0002\r!a2\u0002\u000bMD\u0017\r]3\t\u000f\u0015UF\u00071\u0001\u0002H\u0006)1oY1mK\"9Qq\r\u001bA\u0002\u0015-\u0003bBB\\i\u0001\u0007Qq\u000b\u0005\b\u0003\u001f$\u0004\u0019AAi\u0003A)h.\u001b4pe64Vm\u0019;peJ#E\t\u0006\u0007\u0002:\u0016\u0005W1YCd\u000b\u0017,i\rC\u0004\u0002\u0006U\u0002\r!a\u0002\t\u000f\u0015\u0015W\u00071\u0001\u0006L\u00059a.^7S_^\u001c\bbBCek\u0001\u0007\u0011QE\u0001\b]Vl7i\u001c7t\u0011\u001d\u00199,\u000ea\u0001\u000b/Bq!a46\u0001\u0004\t\t.A\bo_Jl\u0017\r\u001c,fGR|'O\u0015#E)1\tI,b5\u0006V\u0016]W\u0011\\Cn\u0011\u001d\t)A\u000ea\u0001\u0003\u000fAq!\"27\u0001\u0004)Y\u0005C\u0004\u0006JZ\u0002\r!!\n\t\u000f\r]f\u00071\u0001\u0006X!9\u0011q\u001a\u001cA\u0002\u0005E\u0017A\u00057pO:{'/\\1m-\u0016\u001cGo\u001c:S\t\u0012#\u0002#!/\u0006b\u0016\rXQ]Ct\u000bS,Y/\"<\t\u000f\u0005\u0015q\u00071\u0001\u0002\b!9Q\u0011Q\u001cA\u0002\u0005\u001d\u0007bBCCo\u0001\u0007\u0011q\u0019\u0005\b\u000b\u000b<\u0004\u0019AC&\u0011\u001d)Im\u000ea\u0001\u0003KAqaa.8\u0001\u0004)9\u0006C\u0004\u0002P^\u0002\r!!5\u0002!A|\u0017n]:p]Z+7\r^8s%\u0012#ECDA]\u000bg,)0b>\u0006z\u0016mXQ \u0005\b\u0003\u000bA\u0004\u0019AA\u0004\u0011\u001d)\t\t\u000fa\u0001\u0003\u000fDq!\"29\u0001\u0004)Y\u0005C\u0004\u0006Jb\u0002\r!!\n\t\u000f\r]\u0006\b1\u0001\u0006X!9\u0011q\u001a\u001dA\u0002\u0005E\u0017\u0001F3ya>tWM\u001c;jC24Vm\u0019;peJ#E\t\u0006\b\u0002:\u001a\raQ\u0001D\u0004\r\u00131YA\"\u0004\t\u000f\u0005\u0015\u0011\b1\u0001\u0002\b!9Q\u0011Q\u001dA\u0002\u0005\u001d\u0007bBCcs\u0001\u0007Q1\n\u0005\b\u000b\u0013L\u0004\u0019AA\u0013\u0011\u001d\u00199,\u000fa\u0001\u000b/Bq!a4:\u0001\u0004\t\t.\u0001\bhC6l\u0017MV3di>\u0014(\u000b\u0012#\u0015!\u0005ef1\u0003D\u000b\r/1IBb\u0007\u0007\u001e\u0019}\u0001bBA\u0003u\u0001\u0007\u0011q\u0001\u0005\b\u000bcS\u0004\u0019AAd\u0011\u001d))L\u000fa\u0001\u0003\u000fDq!\"2;\u0001\u0004)Y\u0005C\u0004\u0006Jj\u0002\r!!\n\t\u000f\r]&\b1\u0001\u0006X!9\u0011q\u001a\u001eA\u0002\u0005E\u0017!\u00058foJ\u000bgn[5oO6+GO]5dgR!aQ\u0005D\u0019!\u001919C\"\f\u0004t5\u0011a\u0011\u0006\u0006\u0004\rW\u0011\u0016AC3wC2,\u0018\r^5p]&!aq\u0006D\u0015\u00059\u0011\u0016M\\6j]\u001elU\r\u001e:jGNDqAb\r<\u0001\u00041)$A\nqe\u0016$\u0017n\u0019;j_:\fe\u000e\u001a'bE\u0016d7\u000f\u0005\u0003\u00078\u0019=c\u0002\u0002D\u001d\r\u0017rAAb\u000f\u0007H9!aQ\bD#\u001d\u00111yDb\u0011\u000f\u0007\u00114\t%C\u0001Z\u0013\t9\u0006,\u0003\u0002V-&\u0019a\u0011\n+\u0002\u0007M\fH.C\u0002k\r\u001bR1A\"\u0013U\u0013\u00111\tFb\u0015\u0003\u0013\u0011\u000bG/\u0019$sC6,'b\u00016\u0007N\u0005)Rm\u001d;j[\u0006$XmS3s]\u0016dG)\u001a8tSRLH\u0003\u0003D-\r72yFb\u0019\u0011\u000bq\u001b\t!a2\t\u000f\u0019uC\b1\u0001\u0006\u0018\u000511/Y7qY\u0016DqA\"\u0019=\u0001\u0004\t9-A\u0005cC:$w/\u001b3uQ\"9aQ\r\u001fA\u0002\t}\u0017A\u00029pS:$8/\u0001\u000eva\u0012\fG/Z*ue\u0016\fW.\u001b8h\u00176+\u0017M\\:N_\u0012,G\u000e\u0006\u0007\u0002P\u0019-d\u0011\u000fD<\rs2i\bC\u0004\u0007nu\u0002\rAb\u001c\u0002\u001d\rdWo\u001d;fe\u000e+g\u000e^3sgB1\u0011\u0011KA-\u0003wAqAb\u001d>\u0001\u00041)(\u0001\bdYV\u001cH/\u001a:XK&<\u0007\u000e^:\u0011\r\u0005E\u0013\u0011LAd\u0011\u001d\ti)\u0010a\u0001\u0003sCqAb\u001f>\u0001\u0004\t9-A\u0006eK\u000e\f\u0017PR1di>\u0014\bb\u0002D@{\u0001\u0007\u0011\u0011C\u0001\ti&lW-\u00168ji\u0006Qr-\u001a8fe\u0006$X\rT5oK\u0006\u0014\u0018J\u001c9vi^\u0013\u0018\r\u001d9feR\u0001bQ\u0011DD\r\u00133iI\"%\u0007\u0016\u001aee1\u0014\t\u00059\u000e\u00051\u0010C\u0004\u0002tz\u0002\r!a2\t\u000f\u0019-e\b1\u0001\u0007v\u00059q/Z5hQR\u001c\bb\u0002DH}\u0001\u0007aQO\u0001\u0006q6+\u0017M\u001c\u0005\b\r's\u0004\u0019\u0001D;\u0003%Ah+\u0019:jC:\u001cW\rC\u0004\u0007\u0018z\u0002\r!!\n\u0002\u000f9\u0004v.\u001b8ug\"9\u0011q\u001a A\u0002\u0005\u0015\u0002b\u0002DO}\u0001\u0007\u0011qY\u0001\u0004KB\u001c\u0018\u0001G4f]\u0016\u0014\u0018\r^3MS:,\u0017M\u001d*E\t^\u0013\u0018\r\u001d9feRiAOb)\u0007(\u001a-fq\u0016DY\rkCqA\"*@\u0001\u0004\t9!\u0001\u0002tG\"9a\u0011V A\u0002\u0005\u0015\u0012!\u00038fq\u0006l\u0007\u000f\\3t\u0011\u001d1ik\u0010a\u0001\u0003K\t\u0011B\u001c4fCR,(/Z:\t\u000f\u0019uu\b1\u0001\u0002H\"9a1W A\u0002\u0005\u0015\u0012A\u00028qCJ$8\u000fC\u0004\u0002t~\u0002\r!a2\u0002+-|G.\\8h_J|goU7je:|g\u000fV3tiRAa1\u0018Da\r\u000749\r\u0005\u0003\u0006&\u0019u\u0016\u0002\u0002D`\u000bO\u00111dS8m[><wN]8w'6L'O\\8w)\u0016\u001cHOU3tk2$\bbBAG\u0001\u0002\u0007Qq\u0003\u0005\b\r\u000b\u0004\u0005\u0019AA\t\u0003!!\u0017n\u001d;OC6,\u0007b\u0002De\u0001\u0002\u0007aQO\u0001\u0007a\u0006\u0014\u0018-\\:\u0002\u001f\r\u0014X-\u0019;f%><X*\u0019;sSb$\u0002Bb4\u0007\\\u001a}g\u0011\u001d\t\u0005\r#49.\u0004\u0002\u0007T*!aQ[A \u0003-!\u0017n\u001d;sS\n,H/\u001a3\n\t\u0019eg1\u001b\u0002\n%><X*\u0019;sSbDqA\"8B\u0001\u0004\tI,\u0001\u0003s_^\u001c\bbBCc\u0003\u0002\u0007Q1\n\u0005\b\u000b\u0013\f\u0005\u0019AA\u0013)!1yM\":\u0007j\u001a-\bb\u0002Dt\u0005\u0002\u0007aQG\u0001\u0003I\u001aDq!\"2C\u0001\u0004)Y\u0005C\u0004\u0006J\n\u0003\r!!\n\u0002-\r\u0014X-\u0019;f\u0013:$W\r_3e%><X*\u0019;sSb$\u0002B\"=\u0007x\u001aeh1 \t\u0005\r#4\u00190\u0003\u0003\u0007v\u001aM'\u0001E%oI\u0016DX\r\u001a*po6\u000bGO]5y\u0011\u001d1in\u0011a\u0001\rkAq!\"2D\u0001\u0004)Y\u0005C\u0004\u0006J\u000e\u0003\r!!\n\u0002-\r\u0014X-\u0019;f\u0007>|'\u000fZ5oCR,W*\u0019;sSb$\u0002b\"\u0001\b\b\u001d%q1\u0002\t\u0005\r#<\u0019!\u0003\u0003\b\u0006\u0019M'\u0001E\"p_J$\u0017N\\1uK6\u000bGO]5y\u0011\u001d1i\u000e\u0012a\u0001\rkAq!\"2E\u0001\u0004)Y\u0005C\u0004\u0006J\u0012\u0003\r!b\u0013\u0002#\r\u0014X-\u0019;f\u00052|7m['biJL\u0007\u0010\u0006\u0007\b\u0012\u001d]q\u0011DD\u000f\u000fC9\u0019\u0003\u0005\u0003\u0007R\u001eM\u0011\u0002BD\u000b\r'\u00141B\u00117pG.l\u0015\r\u001e:jq\"91QI#A\u0002\u0019U\u0002bBD\u000e\u000b\u0002\u0007\u0011QE\u0001\re><8\u000fU3s\u00052|7m\u001b\u0005\b\u000f?)\u0005\u0019AA\u0013\u00031\u0019w\u000e\\:QKJ\u0014En\\2l\u0011\u001d))-\u0012a\u0001\u000b\u0017Bq!\"3F\u0001\u0004)Y%\u0001\bhKRLe\u000eZ3yK\u0012\u0014vn^:\u0015\t\u0019Ur\u0011\u0006\u0005\b\u000fW1\u0005\u0019\u0001Dy\u0003AIg\u000eZ3yK\u0012\u0014vn^'biJL\u00070\u0001\thKRl\u0015\r\u001e:jq\u0016sGO]5fgR!aQGD\u0019\u0011\u001d9\u0019d\u0012a\u0001\u000f\u0003\t\u0001cY8pe\u0012Lg.\u0019;f\u001b\u0006$(/\u001b=\u0002\u001f\u001d,G/T1ue&D(\t\\8dWN$BA\"\u000e\b:!9q1\b%A\u0002\u001dE\u0011a\u00032m_\u000e\\W*\u0019;sSb\f\u0001dY8om\u0016\u0014HOV3di>\u00148i\u001c7v[:\u001cHk\\'M)\u00191)d\"\u0011\bD!9A1I%A\u0002\u0019U\u0002bBD#\u0013\u0002\u0007AQK\u0001\u0005G>d7/\u0001\u000ed_:4XM\u001d;WK\u000e$xN]\"pYVlgn\u001d$s_6lE\n\u0006\u0004\u00076\u001d-sQ\n\u0005\b\t\u0007R\u0005\u0019\u0001D\u001b\u0011\u001d9)E\u0013a\u0001\t+\n\u0001dY8om\u0016\u0014H/T1ue&D8i\u001c7v[:\u001cHk\\'M)\u00191)db\u0015\bV!9A1I&A\u0002\u0019U\u0002bBD#\u0017\u0002\u0007AQK\u0001\u001bG>tg/\u001a:u\u001b\u0006$(/\u001b=D_2,XN\\:Ge>lW\n\u0014\u000b\u0007\rk9Yf\"\u0018\t\u000f\u0011\rC\n1\u0001\u00076!9qQ\t'A\u0002\u0011U\u0003"
)
public class PythonMLLibAPI implements Serializable {
   public JavaRDD loadLabeledPoints(final JavaSparkContext jsc, final String path, final int minPartitions) {
      return .MODULE$.fromRDD(MLUtils$.MODULE$.loadLabeledPoints(jsc.sc(), path, minPartitions), scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
   }

   public RDD loadVectors(final JavaSparkContext jsc, final String path) {
      return MLUtils$.MODULE$.loadVectors(jsc.sc(), path);
   }

   private List trainRegressionModel(final GeneralizedLinearAlgorithm learner, final JavaRDD data, final Vector initialWeights) {
      List var10000;
      try {
         GeneralizedLinearModel model = learner.run(data.rdd().persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK()), initialWeights);
         if (model instanceof LogisticRegressionModel var7) {
            var10000 = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(((scala.collection.immutable.List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var7.weights(), BoxesRunTime.boxToDouble(var7.intercept()), BoxesRunTime.boxToInteger(var7.numFeatures()), BoxesRunTime.boxToInteger(var7.numClasses())}))).map((x$1) -> x$1)).asJava();
         } else {
            var10000 = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(((scala.collection.immutable.List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{model.weights(), BoxesRunTime.boxToDouble(model.intercept())}))).map((x$2) -> x$2)).asJava();
         }
      } finally {
         data.rdd().unpersist(data.rdd().unpersist$default$1());
      }

      return var10000;
   }

   public Updater getUpdaterFromString(final String regType) {
      String var2 = "l2";
      if (regType == null) {
         if (var2 == null) {
            return new SquaredL2Updater();
         }
      } else if (regType.equals(var2)) {
         return new SquaredL2Updater();
      }

      String var3 = "l1";
      if (regType == null) {
         if (var3 == null) {
            return new L1Updater();
         }
      } else if (regType.equals(var3)) {
         return new L1Updater();
      }

      if (regType != null) {
         String var4 = "none";
         if (regType == null) {
            if (var4 == null) {
               return new SimpleUpdater();
            }
         } else if (regType.equals(var4)) {
            return new SimpleUpdater();
         }

         throw new IllegalArgumentException("Invalid value for 'regType' parameter. Can only be initialized using the following string values: ['l1', 'l2', None].");
      } else {
         return new SimpleUpdater();
      }
   }

   public BisectingKMeansModel trainBisectingKMeans(final JavaRDD data, final int k, final int maxIterations, final double minDivisibleClusterSize, final Long seed) {
      BisectingKMeans kmeans = (new BisectingKMeans()).setK(k).setMaxIterations(maxIterations).setMinDivisibleClusterSize(minDivisibleClusterSize);
      if (seed != null) {
         kmeans.setSeed(scala.Predef..MODULE$.Long2long(seed));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return kmeans.run(data);
   }

   public List trainLinearRegressionModelWithSGD(final JavaRDD data, final int numIterations, final double stepSize, final double miniBatchFraction, final Vector initialWeights, final double regParam, final String regType, final boolean intercept, final boolean validateData, final double convergenceTol) {
      LinearRegressionWithSGD lrAlg = new LinearRegressionWithSGD((double)1.0F, 100, (double)0.0F, (double)1.0F);
      lrAlg.setIntercept(intercept).setValidateData(validateData);
      lrAlg.optimizer().setNumIterations(numIterations).setRegParam(regParam).setStepSize(stepSize).setMiniBatchFraction(miniBatchFraction).setConvergenceTol(convergenceTol);
      lrAlg.optimizer().setUpdater(this.getUpdaterFromString(regType));
      return this.trainRegressionModel(lrAlg, data, initialWeights);
   }

   public List trainLassoModelWithSGD(final JavaRDD data, final int numIterations, final double stepSize, final double regParam, final double miniBatchFraction, final Vector initialWeights, final boolean intercept, final boolean validateData, final double convergenceTol) {
      LassoWithSGD lassoAlg = new LassoWithSGD((double)1.0F, 100, 0.01, (double)1.0F);
      lassoAlg.setIntercept(intercept).setValidateData(validateData);
      lassoAlg.optimizer().setNumIterations(numIterations).setRegParam(regParam).setStepSize(stepSize).setMiniBatchFraction(miniBatchFraction).setConvergenceTol(convergenceTol);
      return this.trainRegressionModel(lassoAlg, data, initialWeights);
   }

   public List trainRidgeModelWithSGD(final JavaRDD data, final int numIterations, final double stepSize, final double regParam, final double miniBatchFraction, final Vector initialWeights, final boolean intercept, final boolean validateData, final double convergenceTol) {
      RidgeRegressionWithSGD ridgeAlg = new RidgeRegressionWithSGD((double)1.0F, 100, 0.01, (double)1.0F);
      ridgeAlg.setIntercept(intercept).setValidateData(validateData);
      ridgeAlg.optimizer().setNumIterations(numIterations).setRegParam(regParam).setStepSize(stepSize).setMiniBatchFraction(miniBatchFraction).setConvergenceTol(convergenceTol);
      return this.trainRegressionModel(ridgeAlg, data, initialWeights);
   }

   public List trainSVMModelWithSGD(final JavaRDD data, final int numIterations, final double stepSize, final double regParam, final double miniBatchFraction, final Vector initialWeights, final String regType, final boolean intercept, final boolean validateData, final double convergenceTol) {
      SVMWithSGD SVMAlg = new SVMWithSGD();
      SVMAlg.setIntercept(intercept).setValidateData(validateData);
      SVMAlg.optimizer().setNumIterations(numIterations).setRegParam(regParam).setStepSize(stepSize).setMiniBatchFraction(miniBatchFraction).setConvergenceTol(convergenceTol);
      SVMAlg.optimizer().setUpdater(this.getUpdaterFromString(regType));
      return this.trainRegressionModel(SVMAlg, data, initialWeights);
   }

   public List trainLogisticRegressionModelWithSGD(final JavaRDD data, final int numIterations, final double stepSize, final double miniBatchFraction, final Vector initialWeights, final double regParam, final String regType, final boolean intercept, final boolean validateData, final double convergenceTol) {
      LogisticRegressionWithSGD LogRegAlg = new LogisticRegressionWithSGD((double)1.0F, 100, 0.01, (double)1.0F);
      LogRegAlg.setIntercept(intercept).setValidateData(validateData);
      LogRegAlg.optimizer().setNumIterations(numIterations).setRegParam(regParam).setStepSize(stepSize).setMiniBatchFraction(miniBatchFraction).setConvergenceTol(convergenceTol);
      LogRegAlg.optimizer().setUpdater(this.getUpdaterFromString(regType));
      return this.trainRegressionModel(LogRegAlg, data, initialWeights);
   }

   public List trainLogisticRegressionModelWithLBFGS(final JavaRDD data, final int numIterations, final Vector initialWeights, final double regParam, final String regType, final boolean intercept, final int corrections, final double tolerance, final boolean validateData, final int numClasses) {
      LogisticRegressionWithLBFGS LogRegAlg = new LogisticRegressionWithLBFGS();
      ((LogisticRegressionWithLBFGS)LogRegAlg.setIntercept(intercept).setValidateData(validateData)).setNumClasses(numClasses);
      LogRegAlg.optimizer().setNumIterations(numIterations).setRegParam(regParam).setNumCorrections(corrections).setConvergenceTol(tolerance);
      LogRegAlg.optimizer().setUpdater(this.getUpdaterFromString(regType));
      return this.trainRegressionModel(LogRegAlg, data, initialWeights);
   }

   public List trainNaiveBayesModel(final JavaRDD data, final double lambda) {
      NaiveBayesModel model = NaiveBayes$.MODULE$.train(data.rdd(), lambda);
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((new scala.collection.immutable..colon.colon(Vectors$.MODULE$.dense(model.labels()), new scala.collection.immutable..colon.colon(Vectors$.MODULE$.dense(model.pi()), new scala.collection.immutable..colon.colon(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])model.theta()), (values) -> Vectors$.MODULE$.dense(values), scala.reflect.ClassTag..MODULE$.apply(Vector.class)), scala.collection.immutable.Nil..MODULE$)))).map((x$3) -> x$3)).asJava();
   }

   public List trainIsotonicRegressionModel(final JavaRDD data, final boolean isotonic) {
      IsotonicRegression isotonicRegressionAlg = (new IsotonicRegression()).setIsotonic(isotonic);
      RDD input = data.rdd().map((x) -> new Tuple3(BoxesRunTime.boxToDouble(x.apply(0)), BoxesRunTime.boxToDouble(x.apply(1)), BoxesRunTime.boxToDouble(x.apply(2))), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());

      List var10000;
      try {
         IsotonicRegressionModel model = isotonicRegressionAlg.run(input);
         var10000 = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(new scala.collection.immutable..colon.colon(model.boundaryVector(), new scala.collection.immutable..colon.colon(model.predictionVector(), scala.collection.immutable.Nil..MODULE$))).asJava();
      } finally {
         data.rdd().unpersist(data.rdd().unpersist$default$1());
      }

      return var10000;
   }

   public KMeansModel trainKMeansModel(final JavaRDD data, final int k, final int maxIterations, final String initializationMode, final Long seed, final int initializationSteps, final double epsilon, final ArrayList initialModel, final String distanceMeasure) {
      KMeans kMeansAlg = (new KMeans()).setK(k).setMaxIterations(maxIterations).setInitializationMode(initializationMode).setInitializationSteps(initializationSteps).setEpsilon(epsilon).setDistanceMeasure(distanceMeasure);
      if (seed != null) {
         kMeansAlg.setSeed(scala.Predef..MODULE$.Long2long(seed));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (!initialModel.isEmpty()) {
         kMeansAlg.setInitialModel(new KMeansModel(initialModel));
      } else {
         BoxedUnit var15 = BoxedUnit.UNIT;
      }

      KMeansModel var16;
      try {
         var16 = kMeansAlg.run(data.rdd().persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK()));
      } finally {
         data.rdd().unpersist(data.rdd().unpersist$default$1());
      }

      return var16;
   }

   public double computeCostKmeansModel(final JavaRDD data, final ArrayList centers) {
      return (new KMeansModel(centers)).computeCost(.MODULE$.toRDD(data));
   }

   public GaussianMixtureModelWrapper trainGaussianMixtureModel(final JavaRDD data, final int k, final double convergenceTol, final int maxIterations, final Long seed, final ArrayList initialModelWeights, final ArrayList initialModelMu, final ArrayList initialModelSigma) {
      GaussianMixture gmmAlg = (new GaussianMixture()).setK(k).setConvergenceTol(convergenceTol).setMaxIterations(maxIterations);
      if (initialModelWeights != null && initialModelMu != null && initialModelSigma != null) {
         Seq gaussians = (Seq)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(initialModelMu).asScala().toSeq().zip(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(initialModelSigma).asScala().toSeq())).map((x0$1) -> {
            if (x0$1 != null) {
               Vector x = (Vector)x0$1._1();
               Matrix y = (Matrix)x0$1._2();
               return new MultivariateGaussian(x, y);
            } else {
               throw new MatchError(x0$1);
            }
         });
         GaussianMixtureModel initialModel = new GaussianMixtureModel((double[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(initialModelWeights).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()), (MultivariateGaussian[])gaussians.toArray(scala.reflect.ClassTag..MODULE$.apply(MultivariateGaussian.class)));
         gmmAlg.setInitialModel(initialModel);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (seed != null) {
         gmmAlg.setSeed(scala.Predef..MODULE$.Long2long(seed));
      } else {
         BoxedUnit var13 = BoxedUnit.UNIT;
      }

      return new GaussianMixtureModelWrapper(gmmAlg.run(data.rdd()));
   }

   public RDD predictSoftGMM(final JavaRDD data, final Vector wt, final Object[] mu, final Object[] si) {
      double[] weight = wt.toArray();
      DenseVector[] mean = (DenseVector[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(mu), (x$4) -> (DenseVector)x$4, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      DenseMatrix[] sigma = (DenseMatrix[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(si), (x$5) -> (DenseMatrix)x$5, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
      MultivariateGaussian[] gaussians = (MultivariateGaussian[])scala.Array..MODULE$.tabulate(weight.length, (i) -> $anonfun$predictSoftGMM$3(mean, sigma, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(MultivariateGaussian.class));
      GaussianMixtureModel model = new GaussianMixtureModel(weight, gaussians);
      return model.predictSoft(.MODULE$.toRDD(data)).map((values) -> Vectors$.MODULE$.dense(values), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public PowerIterationClusteringModel trainPowerIterationClusteringModel(final JavaRDD data, final int k, final int maxIterations, final String initMode) {
      PowerIterationClustering pic = (new PowerIterationClustering()).setK(k).setMaxIterations(maxIterations).setInitializationMode(initMode);
      PowerIterationClusteringModel model = pic.run(data.rdd().map((v) -> new Tuple3(BoxesRunTime.boxToLong((long)v.apply(0)), BoxesRunTime.boxToLong((long)v.apply(1)), BoxesRunTime.boxToDouble(v.apply(2))), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)));
      return new PowerIterationClusteringModelWrapper(model);
   }

   public MatrixFactorizationModel trainALSModel(final JavaRDD ratingsJRDD, final int rank, final int iterations, final double lambda, final int blocks, final boolean nonnegative, final Long seed) {
      ALS als = (new ALS()).setRank(rank).setIterations(iterations).setLambda(lambda).setBlocks(blocks).setNonnegative(nonnegative);
      if (seed != null) {
         als.setSeed(scala.Predef..MODULE$.Long2long(seed));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      MatrixFactorizationModel model = als.run(ratingsJRDD.rdd());
      return new MatrixFactorizationModelWrapper(model);
   }

   public MatrixFactorizationModel trainImplicitALSModel(final JavaRDD ratingsJRDD, final int rank, final int iterations, final double lambda, final int blocks, final double alpha, final boolean nonnegative, final Long seed) {
      ALS als = (new ALS()).setImplicitPrefs(true).setRank(rank).setIterations(iterations).setLambda(lambda).setBlocks(blocks).setAlpha(alpha).setNonnegative(nonnegative);
      if (seed != null) {
         als.setSeed(scala.Predef..MODULE$.Long2long(seed));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      MatrixFactorizationModel model = als.run(ratingsJRDD.rdd());
      return new MatrixFactorizationModelWrapper(model);
   }

   public LDAModelWrapper trainLDAModel(final JavaRDD data, final int k, final int maxIterations, final double docConcentration, final double topicConcentration, final Long seed, final int checkpointInterval, final String optimizer) {
      LDA algo = (new LDA()).setK(k).setMaxIterations(maxIterations).setDocConcentration(docConcentration).setTopicConcentration(topicConcentration).setCheckpointInterval(checkpointInterval).setOptimizer(optimizer);
      if (seed != null) {
         algo.setSeed(scala.Predef..MODULE$.Long2long(seed));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      RDD documents = data.rdd().map((x$6) -> scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(x$6).asScala().toArray(scala.reflect.ClassTag..MODULE$.Any()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class))).map((r) -> {
         Object var2 = r[0];
         if (var2 instanceof Integer var3) {
            return new Tuple2(BoxesRunTime.boxToLong((long)scala.Predef..MODULE$.Integer2int(var3)), (Vector)r[1]);
         } else if (var2 instanceof Long var4) {
            return new Tuple2(BoxesRunTime.boxToLong(scala.Predef..MODULE$.Long2long(var4)), (Vector)r[1]);
         } else {
            throw new IllegalArgumentException("input values contains invalid type value.");
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      LDAModel model = algo.run(documents);
      return new LDAModelWrapper(model);
   }

   public LDAModelWrapper loadLDAModel(final JavaSparkContext jsc, final String path) {
      DistributedLDAModel model = DistributedLDAModel$.MODULE$.load(jsc.sc(), path);
      return new LDAModelWrapper(model);
   }

   public FPGrowthModel trainFPGrowthModel(final JavaRDD data, final double minSupport, final int numPartitions) {
      FPGrowth fpg = new FPGrowth(minSupport, numPartitions);
      FPGrowthModel model = fpg.run(data.rdd().map((x$7) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(x$7).asScala().toArray(scala.reflect.ClassTag..MODULE$.Any()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class))), scala.reflect.ClassTag..MODULE$.Any());
      return new FPGrowthModelWrapper(model);
   }

   public PrefixSpanModelWrapper trainPrefixSpanModel(final JavaRDD data, final double minSupport, final int maxPatternLength, final int localProjDBSize) {
      PrefixSpan prefixSpan = (new PrefixSpan()).setMinSupport(minSupport).setMaxPatternLength(maxPatternLength).setMaxLocalProjDBSize((long)localProjDBSize);
      RDD trainData = data.rdd().map((x$8) -> scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(x$8).asScala().toArray(scala.reflect.ClassTag..MODULE$.apply(ArrayList.class))), (x$9) -> scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(x$9).asScala().toArray(scala.reflect.ClassTag..MODULE$.Any()), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class))), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Object.class))));
      PrefixSpanModel model = prefixSpan.run(trainData, scala.reflect.ClassTag..MODULE$.Any());
      return new PrefixSpanModelWrapper(model);
   }

   public Vector normalizeVector(final double p, final Vector vector) {
      return (new Normalizer(p)).transform(vector);
   }

   public JavaRDD normalizeVector(final double p, final JavaRDD rdd) {
      return (new Normalizer(p)).transform(rdd);
   }

   public StandardScalerModel fitStandardScaler(final boolean withMean, final boolean withStd, final JavaRDD data) {
      return (new StandardScaler(withMean, withStd)).fit(data.rdd());
   }

   public ChiSqSelectorModel fitChiSqSelector(final String selectorType, final int numTopFeatures, final double percentile, final double fpr, final double fdr, final double fwe, final JavaRDD data) {
      return (new ChiSqSelector()).setSelectorType(selectorType).setNumTopFeatures(numTopFeatures).setPercentile(percentile).setFpr(fpr).setFdr(fdr).setFwe(fwe).fit(data.rdd());
   }

   public PCAModel fitPCA(final int k, final JavaRDD data) {
      return (new PCA(k)).fit(data.rdd());
   }

   public IDFModel fitIDF(final int minDocFreq, final JavaRDD dataset) {
      return (new IDF(minDocFreq)).fit(dataset);
   }

   public Word2VecModelWrapper trainWord2VecModel(final JavaRDD dataJRDD, final int vectorSize, final double learningRate, final int numPartitions, final int numIterations, final Long seed, final int minCount, final int windowSize) {
      Word2Vec word2vec = (new Word2Vec()).setVectorSize(vectorSize).setLearningRate(learningRate).setNumPartitions(numPartitions).setNumIterations(numIterations).setMinCount(minCount).setWindowSize(windowSize);
      if (seed != null) {
         word2vec.setSeed(scala.Predef..MODULE$.Long2long(seed));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      Word2VecModelWrapper var15;
      try {
         Word2VecModel model = word2vec.fit(.MODULE$.fromRDD(dataJRDD.rdd().persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK_SER()), scala.reflect.ClassTag..MODULE$.apply(ArrayList.class)));
         var15 = new Word2VecModelWrapper(model);
      } finally {
         dataJRDD.rdd().unpersist(dataJRDD.rdd().unpersist$default$1());
      }

      return var15;
   }

   public DecisionTreeModel trainDecisionTreeModel(final JavaRDD data, final String algoStr, final int numClasses, final Map categoricalFeaturesInfo, final String impurityStr, final int maxDepth, final int maxBins, final int minInstancesPerNode, final double minInfoGain) {
      Enumeration.Value algo = Algo$.MODULE$.fromString(algoStr);
      Impurity impurity = Impurities$.MODULE$.fromString(impurityStr);
      scala.collection.immutable.Map x$6 = scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(categoricalFeaturesInfo).asScala().toMap(scala..less.colon.less..MODULE$.refl());
      Enumeration.Value x$9 = Strategy$.MODULE$.$lessinit$greater$default$6();
      int x$10 = Strategy$.MODULE$.$lessinit$greater$default$10();
      double x$11 = Strategy$.MODULE$.$lessinit$greater$default$11();
      boolean x$12 = Strategy$.MODULE$.$lessinit$greater$default$12();
      int x$13 = Strategy$.MODULE$.$lessinit$greater$default$13();
      double x$14 = Strategy$.MODULE$.$lessinit$greater$default$14();
      boolean x$15 = Strategy$.MODULE$.$lessinit$greater$default$15();
      Strategy strategy = new Strategy(algo, impurity, maxDepth, numClasses, maxBins, x$9, x$6, minInstancesPerNode, minInfoGain, x$10, x$11, x$12, x$13, x$14, x$15);

      DecisionTreeModel var10000;
      try {
         var10000 = DecisionTree$.MODULE$.train(data.rdd().persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK()), strategy);
      } finally {
         data.rdd().unpersist(data.rdd().unpersist$default$1());
      }

      return var10000;
   }

   public RandomForestModel trainRandomForestModel(final JavaRDD data, final String algoStr, final int numClasses, final Map categoricalFeaturesInfo, final int numTrees, final String featureSubsetStrategy, final String impurityStr, final int maxDepth, final int maxBins, final Long seed) {
      Enumeration.Value algo = Algo$.MODULE$.fromString(algoStr);
      Impurity impurity = Impurities$.MODULE$.fromString(impurityStr);
      scala.collection.immutable.Map x$6 = scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(categoricalFeaturesInfo).asScala().toMap(scala..less.colon.less..MODULE$.refl());
      Enumeration.Value x$7 = Strategy$.MODULE$.$lessinit$greater$default$6();
      int x$8 = Strategy$.MODULE$.$lessinit$greater$default$8();
      double x$9 = Strategy$.MODULE$.$lessinit$greater$default$9();
      int x$10 = Strategy$.MODULE$.$lessinit$greater$default$10();
      double x$11 = Strategy$.MODULE$.$lessinit$greater$default$11();
      boolean x$12 = Strategy$.MODULE$.$lessinit$greater$default$12();
      int x$13 = Strategy$.MODULE$.$lessinit$greater$default$13();
      double x$14 = Strategy$.MODULE$.$lessinit$greater$default$14();
      boolean x$15 = Strategy$.MODULE$.$lessinit$greater$default$15();
      Strategy strategy = new Strategy(algo, impurity, maxDepth, numClasses, maxBins, x$7, x$6, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15);
      RDD cached = data.rdd().persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      int intSeed = (int)this.getSeedOrDefault(seed);

      RandomForestModel var10000;
      try {
         label52: {
            Enumeration.Value var34 = Algo$.MODULE$.Classification();
            if (algo == null) {
               if (var34 == null) {
                  break label52;
               }
            } else if (algo.equals(var34)) {
               break label52;
            }

            var10000 = RandomForest$.MODULE$.trainRegressor(cached, strategy, numTrees, featureSubsetStrategy, intSeed);
            return var10000;
         }

         var10000 = RandomForest$.MODULE$.trainClassifier(cached, strategy, numTrees, featureSubsetStrategy, intSeed);
      } finally {
         cached.unpersist(cached.unpersist$default$1());
      }

      return var10000;
   }

   public GradientBoostedTreesModel trainGradientBoostedTreesModel(final JavaRDD data, final String algoStr, final Map categoricalFeaturesInfo, final String lossStr, final int numIterations, final double learningRate, final int maxDepth, final int maxBins) {
      BoostingStrategy boostingStrategy = BoostingStrategy$.MODULE$.defaultParams(algoStr);
      boostingStrategy.setLoss(Losses$.MODULE$.fromString(lossStr));
      boostingStrategy.setNumIterations(numIterations);
      boostingStrategy.setLearningRate(learningRate);
      boostingStrategy.treeStrategy().setMaxDepth(maxDepth);
      boostingStrategy.treeStrategy().setMaxBins(maxBins);
      boostingStrategy.treeStrategy().categoricalFeaturesInfo_$eq(scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(categoricalFeaturesInfo).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
      RDD cached = data.rdd().persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());

      GradientBoostedTreesModel var10000;
      try {
         var10000 = GradientBoostedTrees$.MODULE$.train(cached, boostingStrategy);
      } finally {
         cached.unpersist(cached.unpersist$default$1());
      }

      return var10000;
   }

   public Vector elementwiseProductVector(final Vector scalingVector, final Vector vector) {
      return (new ElementwiseProduct(scalingVector)).transform(vector);
   }

   public JavaRDD elementwiseProductVector(final Vector scalingVector, final JavaRDD vector) {
      return (new ElementwiseProduct(scalingVector)).transform(vector);
   }

   public MultivariateStatisticalSummary colStats(final JavaRDD rdd) {
      return Statistics$.MODULE$.colStats(rdd.rdd());
   }

   public Matrix corr(final JavaRDD x, final String method) {
      return Statistics$.MODULE$.corr(x.rdd(), this.getCorrNameOrDefault(method));
   }

   public double corr(final JavaRDD x, final JavaRDD y, final String method) {
      return Statistics$.MODULE$.corr(x.rdd(), y.rdd(), this.getCorrNameOrDefault(method));
   }

   public ChiSqTestResult chiSqTest(final Vector observed, final Vector expected) {
      return expected == null ? Statistics$.MODULE$.chiSqTest(observed) : Statistics$.MODULE$.chiSqTest(observed, expected);
   }

   public ChiSqTestResult chiSqTest(final Matrix observed) {
      return Statistics$.MODULE$.chiSqTest(observed);
   }

   public ChiSqTestResult[] chiSqTest(final JavaRDD data) {
      return Statistics$.MODULE$.chiSqTest(data.rdd());
   }

   private String getCorrNameOrDefault(final String method) {
      return method == null ? CorrelationNames$.MODULE$.defaultCorrName() : method;
   }

   private long getSeedOrDefault(final Long seed) {
      return seed == null ? org.apache.spark.util.Utils..MODULE$.random().nextLong() : scala.Predef..MODULE$.Long2long(seed);
   }

   private int getNumPartitionsOrDefault(final Integer numPartitions, final JavaSparkContext jsc) {
      return numPartitions == null ? jsc.sc().defaultParallelism() : scala.Predef..MODULE$.Integer2int(numPartitions);
   }

   public JavaRDD uniformRDD(final JavaSparkContext jsc, final long size, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.uniformRDD(jsc.sc(), size, parts, s), scala.reflect.ClassTag..MODULE$.Double());
   }

   public JavaRDD normalRDD(final JavaSparkContext jsc, final long size, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.normalRDD(jsc.sc(), size, parts, s), scala.reflect.ClassTag..MODULE$.Double());
   }

   public JavaRDD logNormalRDD(final JavaSparkContext jsc, final double mean, final double std, final long size, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.logNormalRDD(jsc.sc(), mean, std, size, parts, s), scala.reflect.ClassTag..MODULE$.Double());
   }

   public JavaRDD poissonRDD(final JavaSparkContext jsc, final double mean, final long size, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.poissonRDD(jsc.sc(), mean, size, parts, s), scala.reflect.ClassTag..MODULE$.Double());
   }

   public JavaRDD exponentialRDD(final JavaSparkContext jsc, final double mean, final long size, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.exponentialRDD(jsc.sc(), mean, size, parts, s), scala.reflect.ClassTag..MODULE$.Double());
   }

   public JavaRDD gammaRDD(final JavaSparkContext jsc, final double shape, final double scale, final long size, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.gammaRDD(jsc.sc(), shape, scale, size, parts, s), scala.reflect.ClassTag..MODULE$.Double());
   }

   public JavaRDD uniformVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.uniformVectorRDD(jsc.sc(), numRows, numCols, parts, s), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public JavaRDD normalVectorRDD(final JavaSparkContext jsc, final long numRows, final int numCols, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.normalVectorRDD(jsc.sc(), numRows, numCols, parts, s), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public JavaRDD logNormalVectorRDD(final JavaSparkContext jsc, final double mean, final double std, final long numRows, final int numCols, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.logNormalVectorRDD(jsc.sc(), mean, std, numRows, numCols, parts, s), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public JavaRDD poissonVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.poissonVectorRDD(jsc.sc(), mean, numRows, numCols, parts, s), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public JavaRDD exponentialVectorRDD(final JavaSparkContext jsc, final double mean, final long numRows, final int numCols, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.exponentialVectorRDD(jsc.sc(), mean, numRows, numCols, parts, s), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public JavaRDD gammaVectorRDD(final JavaSparkContext jsc, final double shape, final double scale, final long numRows, final int numCols, final Integer numPartitions, final Long seed) {
      int parts = this.getNumPartitionsOrDefault(numPartitions, jsc);
      long s = this.getSeedOrDefault(seed);
      return .MODULE$.fromRDD(RandomRDDs$.MODULE$.gammaVectorRDD(jsc.sc(), shape, scale, numRows, numCols, parts, s), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   public RankingMetrics newRankingMetrics(final Dataset predictionAndLabels) {
      return new RankingMetrics(predictionAndLabels.rdd().map((r) -> new Tuple2(r.getSeq(0).toArray(scala.reflect.ClassTag..MODULE$.Any()), r.getSeq(1).toArray(scala.reflect.ClassTag..MODULE$.Any())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Any());
   }

   public double[] estimateKernelDensity(final JavaRDD sample, final double bandwidth, final ArrayList points) {
      return (new KernelDensity()).setSample(.MODULE$.toRDD(sample)).setBandwidth(bandwidth).estimate((double[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(points).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()));
   }

   public List updateStreamingKMeansModel(final List clusterCenters, final List clusterWeights, final JavaRDD data, final double decayFactor, final String timeUnit) {
      StreamingKMeansModel model = (new StreamingKMeansModel((Vector[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(clusterCenters).asScala().toArray(scala.reflect.ClassTag..MODULE$.apply(Vector.class)), (double[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(clusterWeights).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()))).update(.MODULE$.toRDD(data), decayFactor, timeUnit);
      return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(new scala.collection.immutable..colon.colon(model.clusterCenters(), new scala.collection.immutable..colon.colon(Vectors$.MODULE$.dense(model.clusterWeights()), scala.collection.immutable.Nil..MODULE$))).asJava();
   }

   public LabeledPoint[] generateLinearInputWrapper(final double intercept, final List weights, final List xMean, final List xVariance, final int nPoints, final int seed, final double eps) {
      return (LabeledPoint[])LinearDataGenerator$.MODULE$.generateLinearInput(intercept, (double[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(weights).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()), (double[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(xMean).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()), (double[])scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(xVariance).asScala().toArray(scala.reflect.ClassTag..MODULE$.Double()), nPoints, seed, eps).toArray(scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
   }

   public JavaRDD generateLinearRDDWrapper(final JavaSparkContext sc, final int nexamples, final int nfeatures, final double eps, final int nparts, final double intercept) {
      return .MODULE$.fromRDD(LinearDataGenerator$.MODULE$.generateLinearRDD(org.apache.spark.api.java.JavaSparkContext..MODULE$.toSparkContext(sc), nexamples, nfeatures, eps, nparts, intercept), scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
   }

   public KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final JavaRDD data, final String distName, final List params) {
      Seq paramsSeq = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(params).asScala().toSeq();
      return Statistics$.MODULE$.kolmogorovSmirnovTest(.MODULE$.toRDD(data), distName, paramsSeq);
   }

   public RowMatrix createRowMatrix(final JavaRDD rows, final long numRows, final int numCols) {
      return new RowMatrix(rows.rdd(), numRows, numCols);
   }

   public RowMatrix createRowMatrix(final Dataset df, final long numRows, final int numCols) {
      boolean var6;
      Predef var10000;
      label19: {
         label18: {
            var10000 = scala.Predef..MODULE$;
            if (df.schema().length() == 1) {
               Class var10001 = ((StructField)df.schema().head()).dataType().getClass();
               Class var5 = VectorUDT.class;
               if (var10001 == null) {
                  if (var5 == null) {
                     break label18;
                  }
               } else if (var10001.equals(var5)) {
                  break label18;
               }
            }

            var6 = false;
            break label19;
         }

         var6 = true;
      }

      var10000.require(var6, () -> "DataFrame must have a single vector type column");
      return new RowMatrix(df.rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(1) == 0) {
               Object vector = ((SeqOps)var3.get()).apply(0);
               if (vector instanceof Vector) {
                  Vector var5 = (Vector)vector;
                  return var5;
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Vector.class)), numRows, numCols);
   }

   public IndexedRowMatrix createIndexedRowMatrix(final Dataset rows, final long numRows, final int numCols) {
      boolean var9;
      Predef var10000;
      label29: {
         label33: {
            var10000 = scala.Predef..MODULE$;
            if (rows.schema().length() == 2) {
               label32: {
                  DataType var10001 = ((StructField)rows.schema().head()).dataType();
                  LongType var5 = org.apache.spark.sql.types.LongType..MODULE$;
                  if (var10001 == null) {
                     if (var5 != null) {
                        break label32;
                     }
                  } else if (!var10001.equals(var5)) {
                     break label32;
                  }

                  Class var8 = rows.schema().apply(1).dataType().getClass();
                  Class var6 = VectorUDT.class;
                  if (var8 == null) {
                     if (var6 == null) {
                        break label33;
                     }
                  } else if (var8.equals(var6)) {
                     break label33;
                  }
               }
            }

            var9 = false;
            break label29;
         }

         var9 = true;
      }

      var10000.require(var9, () -> "DataFrame must consist of a long type index column and a vector type column");
      RDD indexedRows = rows.rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
               Object index = ((SeqOps)var3.get()).apply(0);
               Object vector = ((SeqOps)var3.get()).apply(1);
               if (index instanceof Long) {
                  long var6 = BoxesRunTime.unboxToLong(index);
                  if (vector instanceof Vector) {
                     Vector var8 = (Vector)vector;
                     return new IndexedRow(var6, var8);
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(IndexedRow.class));
      return new IndexedRowMatrix(indexedRows, numRows, numCols);
   }

   public CoordinateMatrix createCoordinateMatrix(final Dataset rows, final long numRows, final long numCols) {
      RDD entries = rows.rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
               Object i = ((SeqOps)var3.get()).apply(0);
               Object j = ((SeqOps)var3.get()).apply(1);
               Object value = ((SeqOps)var3.get()).apply(2);
               if (i instanceof Long) {
                  long var7 = BoxesRunTime.unboxToLong(i);
                  if (j instanceof Long) {
                     long var9 = BoxesRunTime.unboxToLong(j);
                     if (value instanceof Double) {
                        double var11 = BoxesRunTime.unboxToDouble(value);
                        return new MatrixEntry(var7, var9, var11);
                     }
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(MatrixEntry.class));
      return new CoordinateMatrix(entries, numRows, numCols);
   }

   public BlockMatrix createBlockMatrix(final Dataset blocks, final int rowsPerBlock, final int colsPerBlock, final long numRows, final long numCols) {
      RDD blockTuples = blocks.rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
               Object var4 = ((SeqOps)var3.get()).apply(0);
               Object subMatrix = ((SeqOps)var3.get()).apply(1);
               if (var4 instanceof Row) {
                  Row var6 = (Row)var4;
                  Some var7 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var6);
                  if (!var7.isEmpty() && var7.get() != null && ((SeqOps)var7.get()).lengthCompare(2) == 0) {
                     Object blockRowIndex = ((SeqOps)var7.get()).apply(0);
                     Object blockColIndex = ((SeqOps)var7.get()).apply(1);
                     if (blockRowIndex instanceof Long) {
                        long var10 = BoxesRunTime.unboxToLong(blockRowIndex);
                        if (blockColIndex instanceof Long) {
                           long var12 = BoxesRunTime.unboxToLong(blockColIndex);
                           if (subMatrix instanceof Matrix) {
                              Matrix var14 = (Matrix)subMatrix;
                              return new Tuple2(new Tuple2.mcII.sp((int)var10, (int)var12), var14);
                           }
                        }
                     }
                  }
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return new BlockMatrix(blockTuples, rowsPerBlock, colsPerBlock, numRows, numCols);
   }

   public Dataset getIndexedRows(final IndexedRowMatrix indexedRowMatrix) {
      SparkContext sc = indexedRowMatrix.rows().sparkContext();
      SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
      RDD var10001 = indexedRowMatrix.rows();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PythonMLLibAPI.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.mllib.linalg.distributed.IndexedRow").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      return spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
   }

   public Dataset getMatrixEntries(final CoordinateMatrix coordinateMatrix) {
      SparkContext sc = coordinateMatrix.entries().sparkContext();
      SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
      RDD var10001 = coordinateMatrix.entries();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PythonMLLibAPI.class.getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.mllib.linalg.distributed.MatrixEntry").asType().toTypeConstructor();
         }

         public $typecreator1$2() {
         }
      }

      return spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
   }

   public Dataset getMatrixBlocks(final BlockMatrix blockMatrix) {
      SparkContext sc = blockMatrix.blocks().sparkContext();
      SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
      RDD var10001 = blockMatrix.blocks();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(PythonMLLibAPI.class.getClassLoader());

      final class $typecreator1$3 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$))), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.mllib.linalg.Matrix").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$3() {
         }
      }

      return spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3()));
   }

   public Dataset convertVectorColumnsToML(final Dataset dataset, final ArrayList cols) {
      return MLUtils$.MODULE$.convertVectorColumnsToML(dataset, scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(cols).asScala().toSeq());
   }

   public Dataset convertVectorColumnsFromML(final Dataset dataset, final ArrayList cols) {
      return MLUtils$.MODULE$.convertVectorColumnsFromML(dataset, scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(cols).asScala().toSeq());
   }

   public Dataset convertMatrixColumnsToML(final Dataset dataset, final ArrayList cols) {
      return MLUtils$.MODULE$.convertMatrixColumnsToML(dataset, scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(cols).asScala().toSeq());
   }

   public Dataset convertMatrixColumnsFromML(final Dataset dataset, final ArrayList cols) {
      return MLUtils$.MODULE$.convertMatrixColumnsFromML(dataset, scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(cols).asScala().toSeq());
   }

   // $FF: synthetic method
   public static final MultivariateGaussian $anonfun$predictSoftGMM$3(final DenseVector[] mean$1, final DenseMatrix[] sigma$1, final int i) {
      return new MultivariateGaussian(mean$1[i], sigma$1[i]);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
