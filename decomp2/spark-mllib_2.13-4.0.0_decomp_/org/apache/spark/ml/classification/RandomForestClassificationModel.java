package org.apache.spark.ml.classification;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Model;
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
import org.apache.spark.ml.tree.DecisionTreeModel;
import org.apache.spark.ml.tree.DecisionTreeParams;
import org.apache.spark.ml.tree.EnsembleModelReadWrite$;
import org.apache.spark.ml.tree.Node;
import org.apache.spark.ml.tree.RandomForestClassifierParams;
import org.apache.spark.ml.tree.RandomForestParams;
import org.apache.spark.ml.tree.TreeClassifierParams;
import org.apache.spark.ml.tree.TreeEnsembleClassifierParams;
import org.apache.spark.ml.tree.TreeEnsembleModel;
import org.apache.spark.ml.tree.TreeEnsembleModel$;
import org.apache.spark.ml.tree.TreeEnsembleParams;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.HasTrainingSummary;
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
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.Array.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\r\ra\u0001\u0002\u00180\u0001iB\u0001\u0002\u001c\u0001\u0003\u0006\u0004%\t%\u001c\u0005\t\u007f\u0002\u0011\t\u0011)A\u0005]\"Q\u00111\u0001\u0001\u0003\u0006\u0004%I!!\u0002\t\u0015\u0005=\u0001A!A!\u0002\u0013\t9\u0001\u0003\u0006\u0002\u0012\u0001\u0011)\u0019!C!\u0003'A!\"!\t\u0001\u0005\u0003\u0005\u000b\u0011BA\u000b\u0011)\t)\u0003\u0001BC\u0002\u0013\u0005\u00131\u0003\u0005\u000b\u0003S\u0001!\u0011!Q\u0001\n\u0005U\u0001\u0002CA\u0017\u0001\u0011\u0005\u0011'a\f\t\u0011\u00055\u0002\u0001\"\u00012\u0003\u007fA\u0001\"!\f\u0001\t\u0003\t\u0014\u0011\n\u0005\b\u0003\u0007\u0002A\u0011IA\u0003\u0011)\t\t\u0006\u0001EC\u0002\u0013%\u00111\u000b\u0005\b\u0003;\u0002A\u0011IA*\u0011\u001d\t\t\u0007\u0001C!\u0003GBq!a\u001b\u0001\t\u0003\ti\u0007C\u0004\u0002x\u0001!\t!!\u001f\t\u000f\u0005-\u0006\u0001\"\u0011\u0002.\"9\u0011\u0011\u0019\u0001\u0005B\u0005\r\u0007bBAw\u0001\u0011\u0005\u0013q\u001e\u0005\b\u0003w\u0004A\u0011KA\u007f\u0011\u001d\u0011\u0019\u0001\u0001C!\u0005\u000bAqA!\u0007\u0001\t\u0003\u0012Y\u0002\u0003\u0006\u0003 \u0001A)\u0019!C\u0001\u0005CA\u0001B!\n\u0001\t\u0003\t$q\u0005\u0005\b\u0005w\u0001A\u0011\tB\u001f\u000f\u001d\u0011ie\fE\u0001\u0005\u001f2aAL\u0018\t\u0002\tE\u0003bBA\u00179\u0011\u0005!Q\u000e\u0005\b\u0005_bB\u0011\tB9\u0011\u001d\u0011Y\b\bC!\u0005{2qA!\"\u001d\u0001q\u00119\tC\u0005\u0003\n\u0002\u0012\t\u0011)A\u0005\u000b\"9\u0011Q\u0006\u0011\u0005\u0002\t-\u0005b\u0002BJA\u0011E#Q\u0013\u0004\u0007\u0005?cBA!)\t\u000f\u00055B\u0005\"\u0001\u0003$\"I!q\u0015\u0013C\u0002\u0013%!\u0011\u0016\u0005\t\u0005k#\u0003\u0015!\u0003\u0003,\"I!q\u0017\u0013C\u0002\u0013%!\u0011\u0016\u0005\t\u0005s#\u0003\u0015!\u0003\u0003,\"9!1\u0010\u0013\u0005B\tm\u0006\u0002\u0003B`9\u0011\u0005\u0011G!1\t\u0015\t}G$%A\u0005\u0002E\u0012\t\u000fC\u0005\u0003vr\t\t\u0011\"\u0003\u0003x\ny\"+\u00198e_64uN]3ti\u000ec\u0017m]:jM&\u001c\u0017\r^5p]6{G-\u001a7\u000b\u0005A\n\u0014AD2mCN\u001c\u0018NZ5dCRLwN\u001c\u0006\u0003eM\n!!\u001c7\u000b\u0005Q*\u0014!B:qCJ\\'B\u0001\u001c8\u0003\u0019\t\u0007/Y2iK*\t\u0001(A\u0002pe\u001e\u001c\u0001aE\u0004\u0001w\u0019c%\u000b\u00174\u0011\tqjt(R\u0007\u0002_%\u0011ah\f\u0002!!J|'-\u00192jY&\u001cH/[2DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8N_\u0012,G\u000e\u0005\u0002A\u00076\t\u0011I\u0003\u0002Cc\u00051A.\u001b8bY\u001eL!\u0001R!\u0003\rY+7\r^8s!\ta\u0004\u0001\u0005\u0002H\u00156\t\u0001J\u0003\u0002Jc\u0005!AO]3f\u0013\tY\u0005J\u0001\u000fSC:$w.\u001c$pe\u0016\u001cHo\u00117bgNLg-[3s!\u0006\u0014\u0018-\\:\u0011\u0007\u001dku*\u0003\u0002O\u0011\n\tBK]3f\u000b:\u001cX-\u001c2mK6{G-\u001a7\u0011\u0005q\u0002\u0016BA)0\u0005}!UmY5tS>tGK]3f\u00072\f7o]5gS\u000e\fG/[8o\u001b>$W\r\u001c\t\u0003'Zk\u0011\u0001\u0016\u0006\u0003+F\nA!\u001e;jY&\u0011q\u000b\u0016\u0002\u000b\u001b2;&/\u001b;bE2,\u0007CA-d\u001d\tQ\u0006M\u0004\u0002\\=6\tAL\u0003\u0002^s\u00051AH]8pizJ\u0011aX\u0001\u0006g\u000e\fG.Y\u0005\u0003C\n\fq\u0001]1dW\u0006<WMC\u0001`\u0013\t!WM\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002bEB\u00191kZ5\n\u0005!$&A\u0005%bgR\u0013\u0018-\u001b8j]\u001e\u001cV/\\7bef\u0004\"\u0001\u00106\n\u0005-|#!\u000b*b]\u0012|WNR8sKN$8\t\\1tg&4\u0017nY1uS>tGK]1j]&twmU;n[\u0006\u0014\u00180A\u0002vS\u0012,\u0012A\u001c\t\u0003_Nt!\u0001]9\u0011\u0005m\u0013\u0017B\u0001:c\u0003\u0019\u0001&/\u001a3fM&\u0011A/\u001e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005I\u0014\u0007fA\u0001x{B\u0011\u0001p_\u0007\u0002s*\u0011!pM\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001?z\u0005\u0015\u0019\u0016N\\2fC\u0005q\u0018!B\u0019/k9\u0002\u0014\u0001B;jI\u0002B3AA<~\u0003\u0019yFO]3fgV\u0011\u0011q\u0001\t\u0006\u0003\u0013\tYaT\u0007\u0002E&\u0019\u0011Q\u00022\u0003\u000b\u0005\u0013(/Y=\u0002\u000f}#(/Z3tA\u0005Ya.^7GK\u0006$XO]3t+\t\t)\u0002\u0005\u0003\u0002\n\u0005]\u0011bAA\rE\n\u0019\u0011J\u001c;)\t\u00159\u0018QD\u0011\u0003\u0003?\tQ!\r\u00187]A\nAB\\;n\r\u0016\fG/\u001e:fg\u0002BCAB<\u0002\u001e\u0005Qa.^7DY\u0006\u001c8/Z:)\u0007\u001d9X0A\u0006ok6\u001cE.Y:tKN\u0004\u0003f\u0001\u0005x{\u00061A(\u001b8jiz\"\u0012\"RA\u0019\u0003k\t9$a\u000f\t\u000b1L\u0001\u0019\u00018)\t\u0005Er/ \u0005\b\u0003\u0007I\u0001\u0019AA\u0004\u0011\u001d\t\t\"\u0003a\u0001\u0003+AS!a\u000ex\u0003;Aq!!\n\n\u0001\u0004\t)\u0002\u000b\u0003\u0002<]lHcB#\u0002B\u0005\u0015\u0013q\t\u0005\b\u0003\u0007R\u0001\u0019AA\u0004\u0003\u0015!(/Z3t\u0011\u001d\t\tB\u0003a\u0001\u0003+Aq!!\n\u000b\u0001\u0004\t)\u0002F\u0001FQ\u0011aq/!\u0014\"\u0005\u0005=\u0013!B\u0019/i9\u0002\u0014\u0001D0ue\u0016,w+Z5hQR\u001cXCAA+!\u0019\tI!a\u0003\u0002XA!\u0011\u0011BA-\u0013\r\tYF\u0019\u0002\u0007\t>,(\r\\3\u0002\u0017Q\u0014X-Z,fS\u001eDGo\u001d\u0015\u0005\u001d]\fi%A\u0004tk6l\u0017M]=\u0016\u0003%DCaD<\u0002h\u0005\u0012\u0011\u0011N\u0001\u0006g9\nd\u0006M\u0001\u000eE&t\u0017M]=Tk6l\u0017M]=\u0016\u0005\u0005=\u0004c\u0001\u001f\u0002r%\u0019\u00111O\u0018\u0003_\tKg.\u0019:z%\u0006tGm\\7G_J,7\u000f^\"mCN\u001c\u0018NZ5dCRLwN\u001c+sC&t\u0017N\\4Tk6l\u0017M]=)\tA9\u0018qM\u0001\tKZ\fG.^1uKR!\u00111PAA!\ra\u0014QP\u0005\u0004\u0003\u007fz#!\t*b]\u0012|WNR8sKN$8\t\\1tg&4\u0017nY1uS>t7+^7nCJL\bbBAB#\u0001\u0007\u0011QQ\u0001\bI\u0006$\u0018m]3ua\u0011\t9)a&\u0011\r\u0005%\u0015qRAJ\u001b\t\tYIC\u0002\u0002\u000eN\n1a]9m\u0013\u0011\t\t*a#\u0003\u000f\u0011\u000bG/Y:fiB!\u0011QSAL\u0019\u0001!A\"!'\u0002\u0002\u0006\u0005\t\u0011!B\u0001\u00037\u00131a\u0018\u00134#\u0011\ti*a)\u0011\t\u0005%\u0011qT\u0005\u0004\u0003C\u0013'a\u0002(pi\"Lgn\u001a\t\u0005\u0003\u0013\t)+C\u0002\u0002(\n\u00141!\u00118zQ\u0011\tr/a\u001a\u0002\u001fQ\u0014\u0018M\\:g_Jl7k\u00195f[\u0006$B!a,\u0002<B!\u0011\u0011WA\\\u001b\t\t\u0019L\u0003\u0003\u00026\u0006-\u0015!\u0002;za\u0016\u001c\u0018\u0002BA]\u0003g\u0013!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\tiL\u0005a\u0001\u0003_\u000baa]2iK6\f\u0007\u0006\u0002\nx\u0003\u001b\n\u0011\u0002\u001e:b]N4wN]7\u0015\t\u0005\u0015\u0017\u0011\u001d\t\u0005\u0003\u000f\fYN\u0004\u0003\u0002J\u0006eg\u0002BAf\u0003/tA!!4\u0002V:!\u0011qZAj\u001d\rY\u0016\u0011[\u0005\u0002q%\u0011agN\u0005\u0003iUJ1!!$4\u0013\r\t\u00171R\u0005\u0005\u0003;\fyNA\u0005ECR\fgI]1nK*\u0019\u0011-a#\t\u000f\u0005\r5\u00031\u0001\u0002dB\"\u0011Q]Au!\u0019\tI)a$\u0002hB!\u0011QSAu\t1\tY/!9\u0002\u0002\u0003\u0005)\u0011AAN\u0005\ryF\u0005N\u0001\u000baJ,G-[2u%\u0006<HcA \u0002r\"1\u00111\u001f\u000bA\u0002}\n\u0001BZ3biV\u0014Xm\u001d\u0015\u0005)]\f90\t\u0002\u0002z\u0006)1G\f\u0019/a\u00051\"/Y<3aJ|'-\u00192jY&$\u00180\u00138QY\u0006\u001cW\rF\u0002@\u0003\u007fDaA!\u0001\u0016\u0001\u0004y\u0014!\u0004:boB\u0013X\rZ5di&|g.\u0001\u0003d_BLHcA#\u0003\b!9!\u0011\u0002\fA\u0002\t-\u0011!B3yiJ\f\u0007\u0003\u0002B\u0007\u0005'i!Aa\u0004\u000b\u0007\tE\u0011'A\u0003qCJ\fW.\u0003\u0003\u0003\u0016\t=!\u0001\u0003)be\u0006lW*\u00199)\tY9\u0018QJ\u0001\ti>\u001cFO]5oOR\ta\u000e\u000b\u0003\u0018o\u00065\u0013A\u00054fCR,(/Z%na>\u0014H/\u00198dKN,\u0012a\u0010\u0015\u00041]l\u0018!\u0002;p\u001f2$WC\u0001B\u0015!\u0011\u0011YCa\u000e\u000e\u0005\t5\"\u0002\u0002B\u0018\u0005c\tQ!\\8eK2T1!\u0013B\u001a\u0015\r\u0011)dM\u0001\u0006[2d\u0017NY\u0005\u0005\u0005s\u0011iCA\tSC:$w.\u001c$pe\u0016\u001cH/T8eK2\fQa\u001e:ji\u0016,\"Aa\u0010\u0011\u0007M\u0013\t%C\u0002\u0003DQ\u0013\u0001\"\u0014'Xe&$XM\u001d\u0015\u00055]\u00149%\t\u0002\u0003J\u0005)!G\f\u0019/a!\"\u0001a^A'\u0003}\u0011\u0016M\u001c3p[\u001a{'/Z:u\u00072\f7o]5gS\u000e\fG/[8o\u001b>$W\r\u001c\t\u0003yq\u0019r\u0001\bB*\u00053\u0012y\u0006\u0005\u0003\u0002\n\tU\u0013b\u0001B,E\n1\u0011I\\=SK\u001a\u0004Ba\u0015B.\u000b&\u0019!Q\f+\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0003b\t-TB\u0001B2\u0015\u0011\u0011)Ga\u001a\u0002\u0005%|'B\u0001B5\u0003\u0011Q\u0017M^1\n\u0007\u0011\u0014\u0019\u0007\u0006\u0002\u0003P\u0005!!/Z1e+\t\u0011\u0019\b\u0005\u0003T\u0005k*\u0015b\u0001B<)\nAQ\n\u0014*fC\u0012,'\u000f\u000b\u0003\u001fo\n\u001d\u0013\u0001\u00027pC\u0012$2!\u0012B@\u0011\u0019\u0011\ti\ba\u0001]\u0006!\u0001/\u0019;iQ\u0011yrOa\u0012\u0003KI\u000bg\u000eZ8n\r>\u0014Xm\u001d;DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8N_\u0012,Gn\u0016:ji\u0016\u00148c\u0001\u0011\u0003@\u0005A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0003\u000e\nE\u0005c\u0001BHA5\tA\u0004\u0003\u0004\u0003\n\n\u0002\r!R\u0001\tg\u00064X-S7qYR!!q\u0013BO!\u0011\tIA!'\n\u0007\tm%M\u0001\u0003V]&$\bB\u0002BAG\u0001\u0007aNA\u0013SC:$w.\u001c$pe\u0016\u001cHo\u00117bgNLg-[2bi&|g.T8eK2\u0014V-\u00193feN\u0019AEa\u001d\u0015\u0005\t\u0015\u0006c\u0001BHI\u0005I1\r\\1tg:\u000bW.Z\u000b\u0003\u0005W\u0003BA!,\u000346\u0011!q\u0016\u0006\u0005\u0005c\u00139'\u0001\u0003mC:<\u0017b\u0001;\u00030\u0006Q1\r\\1tg:\u000bW.\u001a\u0011\u0002\u001bQ\u0014X-Z\"mCN\u001ch*Y7f\u00039!(/Z3DY\u0006\u001c8OT1nK\u0002\"2!\u0012B_\u0011\u0019\u0011\tI\u000ba\u0001]\u00069aM]8n\u001f2$GcC#\u0003D\n\u001d'\u0011\u001bBn\u0005;DqA!2,\u0001\u0004\u0011I#\u0001\u0005pY\u0012lu\u000eZ3m\u0011\u001d\u0011Im\u000ba\u0001\u0005\u0017\fa\u0001]1sK:$\bc\u0001\u001f\u0003N&\u0019!qZ\u0018\u0003-I\u000bg\u000eZ8n\r>\u0014Xm\u001d;DY\u0006\u001c8/\u001b4jKJDqAa5,\u0001\u0004\u0011).A\ndCR,wm\u001c:jG\u0006dg)Z1ukJ,7\u000fE\u0004p\u0005/\f)\"!\u0006\n\u0007\teWOA\u0002NCBDq!!\n,\u0001\u0004\t)\u0002C\u0005\u0002\u0012-\u0002\n\u00111\u0001\u0002\u0016\u0005\tbM]8n\u001f2$G\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\t\r(\u0006BA\u000b\u0005K\\#Aa:\u0011\t\t%(\u0011_\u0007\u0003\u0005WTAA!<\u0003p\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003u\nLAAa=\u0003l\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\te\b\u0003\u0002BW\u0005wLAA!@\u00030\n1qJ\u00196fGRDC\u0001H<\u0003H!\"1d\u001eB$\u0001"
)
public class RandomForestClassificationModel extends ProbabilisticClassificationModel implements RandomForestClassifierParams, TreeEnsembleModel, MLWritable, HasTrainingSummary {
   private double[] _treeWeights;
   private Vector featureImportances;
   private final String uid;
   private final DecisionTreeClassificationModel[] _trees;
   private final int numFeatures;
   private final int numClasses;
   private Option trainingSummary;
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

   public static RandomForestClassificationModel load(final String path) {
      return RandomForestClassificationModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RandomForestClassificationModel$.MODULE$.read();
   }

   public boolean hasSummary() {
      return HasTrainingSummary.hasSummary$(this);
   }

   public HasTrainingSummary setSummary(final Option summary) {
      return HasTrainingSummary.setSummary$(this, summary);
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
      return TreeClassifierParams.getImpurity$(this);
   }

   public Impurity getOldImpurity() {
      return TreeClassifierParams.getOldImpurity$(this);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$tree$TreeEnsembleClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ProbabilisticClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return TreeEnsembleClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
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

   public final Option trainingSummary() {
      return this.trainingSummary;
   }

   public final void trainingSummary_$eq(final Option x$1) {
      this.trainingSummary = x$1;
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

   public final void org$apache$spark$ml$tree$TreeClassifierParams$_setter_$impurity_$eq(final Param x$1) {
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

   private DecisionTreeClassificationModel[] _trees() {
      return this._trees;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public int numClasses() {
      return this.numClasses;
   }

   public DecisionTreeClassificationModel[] trees() {
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

   public RandomForestClassificationTrainingSummary summary() {
      return (RandomForestClassificationTrainingSummary)HasTrainingSummary.summary$(this);
   }

   public BinaryRandomForestClassificationTrainingSummary binarySummary() {
      RandomForestClassificationTrainingSummary var2 = this.summary();
      if (var2 instanceof BinaryRandomForestClassificationTrainingSummary var3) {
         return var3;
      } else {
         throw new RuntimeException("Cannot create a binary summary for a non-binary model(numClasses=" + this.numClasses() + "), use summary instead.");
      }
   }

   public RandomForestClassificationSummary evaluate(final Dataset dataset) {
      String weightColName = !this.isDefined(this.weightCol()) ? "weightCol" : (String)this.$(this.weightCol());
      Tuple3 var5 = this.findSummaryModel();
      if (var5 != null) {
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var5._1();
         String probabilityColName = (String)var5._2();
         String predictionColName = (String)var5._3();
         Tuple3 var4 = new Tuple3(summaryModel, probabilityColName, predictionColName);
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var4._1();
         String probabilityColName = (String)var4._2();
         String predictionColName = (String)var4._3();
         return (RandomForestClassificationSummary)(this.numClasses() > 2 ? new RandomForestClassificationSummaryImpl(summaryModel.transform(dataset), predictionColName, (String)this.$(this.labelCol()), weightColName) : new BinaryRandomForestClassificationSummaryImpl(summaryModel.transform(dataset), probabilityColName, predictionColName, (String)this.$(this.labelCol()), weightColName));
      } else {
         throw new MatchError(var5);
      }
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
      Dataset outputData = super.transform(dataset);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> this.predictLeaf(features);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RandomForestClassificationModel.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RandomForestClassificationModel.class.getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         UserDefinedFunction leafUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
         return outputData.withColumn((String)this.$(this.leafCol()), leafUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))}))), outputSchema.apply((String)this.$(this.leafCol())).metadata());
      } else {
         return outputData;
      }
   }

   public Vector predictRaw(final Vector features) {
      double[] votes = (double[]).MODULE$.ofDim(this.numClasses(), scala.reflect.ClassTag..MODULE$.Double());
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this._trees()), (tree) -> {
         $anonfun$predictRaw$1(this, features, votes, tree);
         return BoxedUnit.UNIT;
      });
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(votes);
   }

   public Vector raw2probabilityInPlace(final Vector rawPrediction) {
      if (rawPrediction instanceof DenseVector var4) {
         ProbabilisticClassificationModel$.MODULE$.normalizeToProbabilitiesInPlace(var4);
         return var4;
      } else if (rawPrediction instanceof SparseVector) {
         throw new RuntimeException("Unexpected error in RandomForestClassificationModel: raw2probabilityInPlace encountered SparseVector");
      } else {
         throw new MatchError(rawPrediction);
      }
   }

   public RandomForestClassificationModel copy(final ParamMap extra) {
      return (RandomForestClassificationModel)((Model)this.copyValues(new RandomForestClassificationModel(this.uid(), this._trees(), this.numFeatures(), this.numClasses()), extra)).setParent(this.parent());
   }

   public String toString() {
      String var10000 = this.uid();
      return "RandomForestClassificationModel: uid=" + var10000 + ", numTrees=" + this.getNumTrees() + ", numClasses=" + this.numClasses() + ", numFeatures=" + this.numFeatures();
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
      return new RandomForestModel(Algo$.MODULE$.Classification(), (org.apache.spark.mllib.tree.model.DecisionTreeModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this._trees()), (x$5) -> x$5.toOld(), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.tree.model.DecisionTreeModel.class)));
   }

   public MLWriter write() {
      return new RandomForestClassificationModelWriter(this);
   }

   // $FF: synthetic method
   public static final void $anonfun$predictRaw$1(final RandomForestClassificationModel $this, final Vector features$1, final double[] votes$1, final DecisionTreeClassificationModel tree) {
      double[] classCounts = tree.rootNode().predictImpl(features$1).impurityStats().stats();
      double total = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(classCounts).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      if (total != (double)0) {
         for(int i = 0; i < $this.numClasses(); ++i) {
            votes$1[i] += classCounts[i] / total;
         }

      }
   }

   public RandomForestClassificationModel(final String uid, final DecisionTreeClassificationModel[] _trees, final int numFeatures, final int numClasses) {
      this.uid = uid;
      this._trees = _trees;
      this.numFeatures = numFeatures;
      this.numClasses = numClasses;
      HasCheckpointInterval.$init$(this);
      HasSeed.$init$(this);
      HasWeightCol.$init$(this);
      DecisionTreeParams.$init$(this);
      TreeEnsembleParams.$init$(this);
      RandomForestParams.$init$(this);
      TreeEnsembleClassifierParams.$init$(this);
      TreeClassifierParams.$init$(this);
      TreeEnsembleModel.$init$(this);
      MLWritable.$init$(this);
      HasTrainingSummary.$init$(this);
      scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps(_trees)), () -> "RandomForestClassificationModel requires at least 1 tree.");
      Statics.releaseFence();
   }

   public RandomForestClassificationModel(final DecisionTreeClassificationModel[] trees, final int numFeatures, final int numClasses) {
      this(Identifiable$.MODULE$.randomUID("rfc"), trees, numFeatures, numClasses);
   }

   public RandomForestClassificationModel() {
      this("", (DecisionTreeClassificationModel[])(new DecisionTreeClassificationModel[]{new DecisionTreeClassificationModel()}), -1, -1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class RandomForestClassificationModelWriter extends MLWriter {
      private final RandomForestClassificationModel instance;

      public void saveImpl(final String path) {
         JObject extraMetadata = org.json4s.JsonDSL..MODULE$.map2jvalue((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numFeatures"), BoxesRunTime.boxToInteger(this.instance.numFeatures())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numClasses"), BoxesRunTime.boxToInteger(this.instance.numClasses())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numTrees"), BoxesRunTime.boxToInteger(this.instance.getNumTrees()))}))), (x) -> $anonfun$saveImpl$1(BoxesRunTime.unboxToInt(x)));
         EnsembleModelReadWrite$.MODULE$.saveImpl(this.instance, path, this.sparkSession(), extraMetadata);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$saveImpl$1(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      public RandomForestClassificationModelWriter(final RandomForestClassificationModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class RandomForestClassificationModelReader extends MLReader {
      private final String className = RandomForestClassificationModel.class.getName();
      private final String treeClassName = DecisionTreeClassificationModel.class.getName();

      private String className() {
         return this.className;
      }

      private String treeClassName() {
         return this.treeClassName;
      }

      public RandomForestClassificationModel load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         Tuple3 var5 = EnsembleModelReadWrite$.MODULE$.loadImpl(path, this.sparkSession(), this.className(), this.treeClassName());
         if (var5 != null) {
            DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var5._1();
            Tuple2[] treesData = (Tuple2[])var5._2();
            if (metadata != null && treesData != null) {
               Tuple2 var4 = new Tuple2(metadata, treesData);
               DefaultParamsReader.Metadata metadata = (DefaultParamsReader.Metadata)var4._1();
               Tuple2[] treesData = (Tuple2[])var4._2();
               int numFeatures = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numFeatures")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
               int numClasses = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numClasses")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
               int numTrees = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata.metadata()), "numTrees")), format, scala.reflect.ManifestFactory..MODULE$.Int()));
               DecisionTreeClassificationModel[] trees = (DecisionTreeClassificationModel[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])treesData), (x0$1) -> {
                  if (x0$1 != null) {
                     DefaultParamsReader.Metadata treeMetadata = (DefaultParamsReader.Metadata)x0$1._1();
                     Node root = (Node)x0$1._2();
                     DecisionTreeClassificationModel tree = new DecisionTreeClassificationModel(treeMetadata.uid(), root, numFeatures, numClasses);
                     treeMetadata.getAndSetParams(tree, treeMetadata.getAndSetParams$default$2());
                     return tree;
                  } else {
                     throw new MatchError(x0$1);
                  }
               }, scala.reflect.ClassTag..MODULE$.apply(DecisionTreeClassificationModel.class));
               scala.Predef..MODULE$.require(numTrees == trees.length, () -> "RandomForestClassificationModel.load expected " + numTrees + " trees based on metadata but found " + trees.length + " trees.");
               RandomForestClassificationModel model = new RandomForestClassificationModel(metadata.uid(), trees, numFeatures, numClasses);
               metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
               return model;
            }
         }

         throw new MatchError(var5);
      }

      public RandomForestClassificationModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
