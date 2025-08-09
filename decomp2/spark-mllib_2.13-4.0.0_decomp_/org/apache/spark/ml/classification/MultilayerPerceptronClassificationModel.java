package org.apache.spark.ml.classification;

import java.io.IOException;
import java.io.Serializable;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.ann.FeedForwardTopology$;
import org.apache.spark.ml.ann.TopologyModel;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntArrayParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasBlockSize;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.HasTrainingSummary;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tug\u0001\u0002\u001c8\u0001\tC\u0001b\u001b\u0001\u0003\u0006\u0004%\t\u0005\u001c\u0005\t}\u0002\u0011\t\u0011)A\u0005[\"Q\u0011\u0011\u0001\u0001\u0003\u0006\u0004%\t!a\u0001\t\u0013\u0005-\u0001A!A!\u0002\u00139\u0005\u0002CA\b\u0001\u0011\u0005\u0011(!\u0005\t\u0011\u0005=\u0001\u0001\"\u0001:\u00037A!\"!\b\u0001\u0011\u000b\u0007I\u0011IA\u0010\u0011-\ty\u0003\u0001EC\u0002\u0013\u0005\u0011(!\r\t\u000f\u0005\u001d\u0003\u0001\"\u0011\u0002J!9\u0011\u0011\u000b\u0001\u0005\u0002\u0005M\u0003bBAC\u0001\u0011\u0005\u0013q\u0011\u0005\b\u0003'\u0003A\u0011IAK\u0011\u001d\tI\u000b\u0001C!\u0003WCq!!.\u0001\t#\n9\fC\u0004\u0002>\u0002!\t%a0\t\u000f\u0005%\u0007\u0001\"\u0011\u0002 !9\u00111\u001a\u0001\u0005B\u00055waBAjo!\u0005\u0011Q\u001b\u0004\u0007m]B\t!a6\t\u000f\u0005=1\u0003\"\u0001\u0002t\"9\u0011Q_\n\u0005B\u0005]\bb\u0002B\u0001'\u0011\u0005#1\u0001\u0004\b\u0005\u0017\u0019\u0002a\u0005B\u0007\u0011%\u0011ya\u0006B\u0001B\u0003%Q\nC\u0004\u0002\u0010]!\tA!\u0005\u0007\r\teq\u0003\u0012B\u000e\u0011)\t\tA\u0007BK\u0002\u0013\u0005\u00111\u0001\u0005\n\u0003\u0017Q\"\u0011#Q\u0001\n\u001dCq!a\u0004\u001b\t\u0003\u0011\u0019\u0003C\u0005\u0002\u0014j\t\t\u0011\"\u0001\u0003,!I!q\u0006\u000e\u0012\u0002\u0013\u0005!\u0011\u0007\u0005\n\u0005\u000bR\u0012\u0011!C!\u0005\u000fB\u0011Ba\u0015\u001b\u0003\u0003%\t!a\b\t\u0013\tU#$!A\u0005\u0002\t]\u0003\"\u0003B/5\u0005\u0005I\u0011\tB0\u0011%\u0011iGGA\u0001\n\u0003\u0011y\u0007C\u0005\u0003zi\t\t\u0011\"\u0011\u0003|!I!q\u0010\u000e\u0002\u0002\u0013\u0005#\u0011\u0011\u0005\n\u0003\u0017T\u0012\u0011!C!\u0005\u0007C\u0011B!\"\u001b\u0003\u0003%\tEa\"\b\u0013\t-u#!A\t\n\t5e!\u0003B\r/\u0005\u0005\t\u0012\u0002BH\u0011\u001d\tyA\u000bC\u0001\u0005;C\u0011\"a3+\u0003\u0003%)Ea!\t\u0013\t}%&!A\u0005\u0002\n\u0005\u0006\"\u0003BSU\u0005\u0005I\u0011\u0011BT\u0011\u001d\u0011\u0019l\u0006C)\u0005k3aAa0\u0014\t\t\u0005\u0007bBA\ba\u0011\u0005!1\u0019\u0005\n\u0005\u000f\u0004$\u0019!C\u0005\u0005\u000fB\u0001B!31A\u0003%!\u0011\n\u0005\b\u0005\u0003\u0001D\u0011\tBf\u0011%\u0011ymEA\u0001\n\u0013\u0011\tNA\u0014Nk2$\u0018\u000e\\1zKJ\u0004VM]2faR\u0014xN\\\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d'B\u0001\u001d:\u00039\u0019G.Y:tS\u001aL7-\u0019;j_:T!AO\u001e\u0002\u00055d'B\u0001\u001f>\u0003\u0015\u0019\b/\u0019:l\u0015\tqt(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0001\u0006\u0019qN]4\u0004\u0001M1\u0001a\u0011(R?\u0016\u0004B\u0001R#H\u001b6\tq'\u0003\u0002Go\t\u0001\u0003K]8cC\nLG.[:uS\u000e\u001cE.Y:tS\u001aL7-\u0019;j_:lu\u000eZ3m!\tA5*D\u0001J\u0015\tQ\u0015(\u0001\u0004mS:\fGnZ\u0005\u0003\u0019&\u0013aAV3di>\u0014\bC\u0001#\u0001!\t!u*\u0003\u0002Qo\tQR*\u001e7uS2\f\u00170\u001a:QKJ\u001cW\r\u001d;s_:\u0004\u0016M]1ngB\u0011!\u000b\u0018\b\u0003'fs!\u0001V,\u000e\u0003US!AV!\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0016!B:dC2\f\u0017B\u0001.\\\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011\u0001W\u0005\u0003;z\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!AW.\u0011\u0005\u0001\u001cW\"A1\u000b\u0005\tL\u0014\u0001B;uS2L!\u0001Z1\u0003\u00155cuK]5uC\ndW\rE\u0002aM\"L!aZ1\u0003%!\u000b7\u000f\u0016:bS:LgnZ*v[6\f'/\u001f\t\u0003\t&L!A[\u001c\u0003c5+H\u000e^5mCf,'\u000fU3sG\u0016\u0004HO]8o\u00072\f7o]5gS\u000e\fG/[8o)J\f\u0017N\\5oON+X.\\1ss\u0006\u0019Q/\u001b3\u0016\u00035\u0004\"A\u001c:\u000f\u0005=\u0004\bC\u0001+\\\u0013\t\t8,\u0001\u0004Qe\u0016$WMZ\u0005\u0003gR\u0014aa\u0015;sS:<'BA9\\Q\r\ta\u000f \t\u0003ojl\u0011\u0001\u001f\u0006\u0003sn\n!\"\u00198o_R\fG/[8o\u0013\tY\bPA\u0003TS:\u001cW-I\u0001~\u0003\u0015\td&\u000e\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\t1H0A\u0004xK&<\u0007\u000e^:\u0016\u0003\u001dCCa\u0001<\u0002\b\u0005\u0012\u0011\u0011B\u0001\u0006e9\u0002d\u0006M\u0001\to\u0016Lw\r\u001b;tA!\"AA^A\u0004\u0003\u0019a\u0014N\\5u}Q)Q*a\u0005\u0002\u0018!)1.\u0002a\u0001[\"\"\u00111\u0003<}\u0011\u0019\t\t!\u0002a\u0001\u000f\"*\u0011q\u0003<\u0002\bQ\tQ*A\u0006ok64U-\u0019;ve\u0016\u001cXCAA\u0011!\u0011\t\u0019#!\n\u000e\u0003mK1!a\n\\\u0005\rIe\u000e\u001e\u0015\u0005\u000fY\fY#\t\u0002\u0002.\u0005)\u0011G\f\u001c/a\u0005AQ\u000e\u001c9N_\u0012,G.\u0006\u0002\u00024A!\u0011QGA\u001e\u001b\t\t9DC\u0002\u0002:e\n1!\u00198o\u0013\u0011\ti$a\u000e\u0003\u001bQ{\u0007o\u001c7pOflu\u000eZ3mQ\rA\u0011\u0011\t\t\u0005\u0003G\t\u0019%C\u0002\u0002Fm\u0013\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u000fM,X.\\1ssV\t\u0001\u000e\u000b\u0003\nm\u00065\u0013EAA(\u0003\u0015\u0019d&\r\u00181\u0003!)g/\u00197vCR,G\u0003BA+\u00037\u00022\u0001RA,\u0013\r\tIf\u000e\u0002*\u001bVdG/\u001b7bs\u0016\u0014\b+\u001a:dKB$(o\u001c8DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8Tk6l\u0017M]=\t\u000f\u0005u#\u00021\u0001\u0002`\u00059A-\u0019;bg\u0016$\b\u0007BA1\u0003c\u0002b!a\u0019\u0002j\u00055TBAA3\u0015\r\t9gO\u0001\u0004gFd\u0017\u0002BA6\u0003K\u0012q\u0001R1uCN,G\u000f\u0005\u0003\u0002p\u0005ED\u0002\u0001\u0003\r\u0003g\nY&!A\u0001\u0002\u000b\u0005\u0011Q\u000f\u0002\u0004?\u0012\u001a\u0014\u0003BA<\u0003{\u0002B!a\t\u0002z%\u0019\u00111P.\u0003\u000f9{G\u000f[5oOB!\u00111EA@\u0013\r\t\ti\u0017\u0002\u0004\u0003:L\b\u0006\u0002\u0006w\u0003\u001b\nq\u0001\u001d:fI&\u001cG\u000f\u0006\u0003\u0002\n\u0006=\u0005\u0003BA\u0012\u0003\u0017K1!!$\\\u0005\u0019!u.\u001e2mK\"1\u0011\u0011S\u0006A\u0002\u001d\u000b\u0001BZ3biV\u0014Xm]\u0001\u0005G>\u0004\u0018\u0010F\u0002N\u0003/Cq!!'\r\u0001\u0004\tY*A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002\u001e\u0006\rVBAAP\u0015\r\t\t+O\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0005\u0003K\u000byJ\u0001\u0005QCJ\fW.T1qQ\raa\u000f`\u0001\u0006oJLG/Z\u000b\u0003\u0003[\u00032\u0001YAX\u0013\r\t\t,\u0019\u0002\t\u001b2;&/\u001b;fe\"\"QB^A\u0004\u0003Y\u0011\u0018m\u001e\u001aqe>\u0014\u0017MY5mSRL\u0018J\u001c)mC\u000e,GcA$\u0002:\"1\u00111\u0018\bA\u0002\u001d\u000bQB]1x!J,G-[2uS>t\u0017A\u00039sK\u0012L7\r\u001e*boR\u0019q)!1\t\r\u0005Eu\u00021\u0001HQ\u0011ya/!2\"\u0005\u0005\u001d\u0017!B\u001a/a9\u0002\u0014A\u00038v[\u000ec\u0017m]:fg\u0006AAo\\*ue&tw\rF\u0001nQ\u0011\tb/!2)\u0007\u00011H0A\u0014Nk2$\u0018\u000e\\1zKJ\u0004VM]2faR\u0014xN\\\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d\u0007C\u0001#\u0014'\u001d\u0019\u0012\u0011\\Ap\u0003K\u0004B!a\t\u0002\\&\u0019\u0011Q\\.\u0003\r\u0005s\u0017PU3g!\u0011\u0001\u0017\u0011]'\n\u0007\u0005\r\u0018M\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004B!a:\u0002r6\u0011\u0011\u0011\u001e\u0006\u0005\u0003W\fi/\u0001\u0002j_*\u0011\u0011q^\u0001\u0005U\u00064\u0018-C\u0002^\u0003S$\"!!6\u0002\tI,\u0017\rZ\u000b\u0003\u0003s\u0004B\u0001YA~\u001b&\u0019\u0011Q`1\u0003\u00115c%+Z1eKJDC!\u0006<\u0002\b\u0005!An\\1e)\ri%Q\u0001\u0005\u0007\u0005\u000f1\u0002\u0019A7\u0002\tA\fG\u000f\u001b\u0015\u0005-Y\f9AA\u0017Nk2$\u0018\u000e\\1zKJ\u0004VM]2faR\u0014xN\\\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016dwK]5uKJ\u001c2aFAW\u0003!Ign\u001d;b]\u000e,G\u0003\u0002B\n\u0005/\u00012A!\u0006\u0018\u001b\u0005\u0019\u0002B\u0002B\b3\u0001\u0007QJ\u0001\u0003ECR\f7C\u0002\u000e\u0002Z\nu\u0011\u000b\u0005\u0003\u0002$\t}\u0011b\u0001B\u00117\n9\u0001K]8ek\u000e$H\u0003\u0002B\u0013\u0005S\u00012Aa\n\u001b\u001b\u00059\u0002BBA\u0001;\u0001\u0007q\t\u0006\u0003\u0003&\t5\u0002\u0002CA\u0001=A\u0005\t\u0019A$\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!1\u0007\u0016\u0004\u000f\nU2F\u0001B\u001c!\u0011\u0011ID!\u0011\u000e\u0005\tm\"\u0002\u0002B\u001f\u0005\u007f\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005e\\\u0016\u0002\u0002B\"\u0005w\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!\u0011\n\t\u0005\u0005\u0017\u0012\t&\u0004\u0002\u0003N)!!qJAw\u0003\u0011a\u0017M\\4\n\u0007M\u0014i%\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005u$\u0011\f\u0005\n\u00057\u0012\u0013\u0011!a\u0001\u0003C\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B1!\u0019\u0011\u0019G!\u001b\u0002~5\u0011!Q\r\u0006\u0004\u0005OZ\u0016AC2pY2,7\r^5p]&!!1\u000eB3\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\tE$q\u000f\t\u0005\u0003G\u0011\u0019(C\u0002\u0003vm\u0013qAQ8pY\u0016\fg\u000eC\u0005\u0003\\\u0011\n\t\u00111\u0001\u0002~\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0011IE! \t\u0013\tmS%!AA\u0002\u0005\u0005\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u0005BC\u0001B%\u0003\u0019)\u0017/^1mgR!!\u0011\u000fBE\u0011%\u0011Y\u0006KA\u0001\u0002\u0004\ti(\u0001\u0003ECR\f\u0007c\u0001B\u0014UM)!F!%\u0002fB9!1\u0013BM\u000f\n\u0015RB\u0001BK\u0015\r\u00119jW\u0001\beVtG/[7f\u0013\u0011\u0011YJ!&\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0006\u0002\u0003\u000e\u0006)\u0011\r\u001d9msR!!Q\u0005BR\u0011\u0019\t\t!\fa\u0001\u000f\u00069QO\\1qa2LH\u0003\u0002BU\u0005_\u0003R!a\t\u0003,\u001eK1A!,\\\u0005\u0019y\u0005\u000f^5p]\"I!\u0011\u0017\u0018\u0002\u0002\u0003\u0007!QE\u0001\u0004q\u0012\u0002\u0014\u0001C:bm\u0016LU\u000e\u001d7\u0015\t\t]&Q\u0018\t\u0005\u0003G\u0011I,C\u0002\u0003<n\u0013A!\u00168ji\"1!qA\u0018A\u00025\u0014Q&T;mi&d\u0017-_3s!\u0016\u00148-\u001a9ue>t7\t\\1tg&4\u0017nY1uS>tWj\u001c3fYJ+\u0017\rZ3s'\r\u0001\u0014\u0011 \u000b\u0003\u0005\u000b\u00042A!\u00061\u0003%\u0019G.Y:t\u001d\u0006lW-\u0001\u0006dY\u0006\u001c8OT1nK\u0002\"2!\u0014Bg\u0011\u0019\u00119\u0001\u000ea\u0001[\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u001b\t\u0005\u0005\u0017\u0012).\u0003\u0003\u0003X\n5#AB(cU\u0016\u001cG\u000f\u000b\u0003\u0014m\u0006\u001d\u0001\u0006\u0002\nw\u0003\u000f\u0001"
)
public class MultilayerPerceptronClassificationModel extends ProbabilisticClassificationModel implements MultilayerPerceptronParams, MLWritable, HasTrainingSummary {
   private int numFeatures;
   private transient TopologyModel mlpModel;
   private final String uid;
   private final Vector weights;
   private Option trainingSummary;
   private IntArrayParam layers;
   private Param solver;
   private Param initialWeights;
   private IntParam blockSize;
   private DoubleParam stepSize;
   private DoubleParam tol;
   private IntParam maxIter;
   private LongParam seed;
   private volatile boolean bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public static MultilayerPerceptronClassificationModel load(final String path) {
      return MultilayerPerceptronClassificationModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MultilayerPerceptronClassificationModel$.MODULE$.read();
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

   public final int[] getLayers() {
      return MultilayerPerceptronParams.getLayers$(this);
   }

   public final Vector getInitialWeights() {
      return MultilayerPerceptronParams.getInitialWeights$(this);
   }

   public final int getBlockSize() {
      return HasBlockSize.getBlockSize$(this);
   }

   public final String getSolver() {
      return HasSolver.getSolver$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final Option trainingSummary() {
      return this.trainingSummary;
   }

   public final void trainingSummary_$eq(final Option x$1) {
      this.trainingSummary = x$1;
   }

   public final IntArrayParam layers() {
      return this.layers;
   }

   public final Param solver() {
      return this.solver;
   }

   public final Param initialWeights() {
      return this.initialWeights;
   }

   public final void org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$layers_$eq(final IntArrayParam x$1) {
      this.layers = x$1;
   }

   public final void org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
   }

   public final void org$apache$spark$ml$classification$MultilayerPerceptronParams$_setter_$initialWeights_$eq(final Param x$1) {
      this.initialWeights = x$1;
   }

   public final IntParam blockSize() {
      return this.blockSize;
   }

   public final void org$apache$spark$ml$param$shared$HasBlockSize$_setter_$blockSize_$eq(final IntParam x$1) {
      this.blockSize = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1) {
   }

   public DoubleParam stepSize() {
      return this.stepSize;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public Vector weights() {
      return this.weights;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.numFeatures = BoxesRunTime.unboxToInt(.MODULE$.head$extension(scala.Predef..MODULE$.intArrayOps((int[])this.$(this.layers()))));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numFeatures;
   }

   public int numFeatures() {
      return !this.bitmap$0 ? this.numFeatures$lzycompute() : this.numFeatures;
   }

   private TopologyModel mlpModel$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.mlpModel = FeedForwardTopology$.MODULE$.multiLayerPerceptron((int[])this.$(this.layers()), true).model(this.weights());
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.mlpModel;
   }

   public TopologyModel mlpModel() {
      return !this.bitmap$trans$0 ? this.mlpModel$lzycompute() : this.mlpModel;
   }

   public MultilayerPerceptronClassificationTrainingSummary summary() {
      return (MultilayerPerceptronClassificationTrainingSummary)HasTrainingSummary.summary$(this);
   }

   public MultilayerPerceptronClassificationSummary evaluate(final Dataset dataset) {
      Tuple3 var4 = this.findSummaryModel();
      if (var4 != null) {
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var4._1();
         String predictionColName = (String)var4._3();
         Tuple2 var3 = new Tuple2(summaryModel, predictionColName);
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var3._1();
         String predictionColName = (String)var3._2();
         return new MultilayerPerceptronClassificationSummaryImpl(summaryModel.transform(dataset), predictionColName, (String)this.$(this.labelCol()), "");
      } else {
         throw new MatchError(var4);
      }
   }

   public double predict(final Vector features) {
      return (double)this.mlpModel().predict(features).argmax();
   }

   public MultilayerPerceptronClassificationModel copy(final ParamMap extra) {
      MultilayerPerceptronClassificationModel copied = (MultilayerPerceptronClassificationModel)(new MultilayerPerceptronClassificationModel(this.uid(), this.weights())).setParent(this.parent());
      return (MultilayerPerceptronClassificationModel)this.copyValues(copied, extra);
   }

   public MLWriter write() {
      return new MultilayerPerceptronClassificationModelWriter(this);
   }

   public Vector raw2probabilityInPlace(final Vector rawPrediction) {
      return this.mlpModel().raw2ProbabilityInPlace(rawPrediction);
   }

   public Vector predictRaw(final Vector features) {
      return this.mlpModel().predictRaw(features);
   }

   public int numClasses() {
      return BoxesRunTime.unboxToInt(.MODULE$.last$extension(scala.Predef..MODULE$.intArrayOps((int[])this.$(this.layers()))));
   }

   public String toString() {
      String var10000 = this.uid();
      return "MultilayerPerceptronClassificationModel: uid=" + var10000 + ", numLayers=" + ((int[])this.$(this.layers())).length + ", numClasses=" + this.numClasses() + ", numFeatures=" + this.numFeatures();
   }

   public MultilayerPerceptronClassificationModel(final String uid, final Vector weights) {
      this.uid = uid;
      this.weights = weights;
      HasSeed.$init$(this);
      HasMaxIter.$init$(this);
      HasTol.$init$(this);
      HasStepSize.$init$(this);
      HasSolver.$init$(this);
      HasBlockSize.$init$(this);
      MultilayerPerceptronParams.$init$(this);
      MLWritable.$init$(this);
      HasTrainingSummary.$init$(this);
      Statics.releaseFence();
   }

   public MultilayerPerceptronClassificationModel() {
      this("", org.apache.spark.ml.linalg.Vectors..MODULE$.empty());
   }

   public static class MultilayerPerceptronClassificationModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final MultilayerPerceptronClassificationModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.weights());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MultilayerPerceptronClassificationModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.classification.MultilayerPerceptronClassificationModel.MultilayerPerceptronClassificationModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.classification.MultilayerPerceptronClassificationModel.MultilayerPerceptronClassificationModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public MultilayerPerceptronClassificationModelWriter(final MultilayerPerceptronClassificationModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Vector weights;
         // $FF: synthetic field
         public final MultilayerPerceptronClassificationModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Vector weights() {
            return this.weights;
         }

         public Data copy(final Vector weights) {
            return this.org$apache$spark$ml$classification$MultilayerPerceptronClassificationModel$MultilayerPerceptronClassificationModelWriter$Data$$$outer().new Data(weights);
         }

         public Vector copy$default$1() {
            return this.weights();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.weights();
               }
               default -> {
                  return Statics.ioobe(x$1);
               }
            }
         }

         public Iterator productIterator() {
            return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
         }

         public boolean canEqual(final Object x$1) {
            return x$1 instanceof Data;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "weights";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label52: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$classification$MultilayerPerceptronClassificationModel$MultilayerPerceptronClassificationModelWriter$Data$$$outer() == this.org$apache$spark$ml$classification$MultilayerPerceptronClassificationModel$MultilayerPerceptronClassificationModelWriter$Data$$$outer()) {
                     label42: {
                        Data var4 = (Data)x$1;
                        Vector var10000 = this.weights();
                        Vector var5 = var4.weights();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label42;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label42;
                        }

                        if (var4.canEqual(this)) {
                           break label52;
                        }
                     }
                  }

                  var6 = false;
                  return var6;
               }
            }

            var6 = true;
            return var6;
         }

         // $FF: synthetic method
         public MultilayerPerceptronClassificationModelWriter org$apache$spark$ml$classification$MultilayerPerceptronClassificationModel$MultilayerPerceptronClassificationModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Vector weights) {
            this.weights = weights;
            if (MultilayerPerceptronClassificationModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = MultilayerPerceptronClassificationModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final MultilayerPerceptronClassificationModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Vector weights) {
            return this.$outer.new Data(weights);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.weights()));
         }

         public Data$() {
            if (MultilayerPerceptronClassificationModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = MultilayerPerceptronClassificationModelWriter.this;
               super();
            }
         }
      }
   }

   private static class MultilayerPerceptronClassificationModelReader extends MLReader {
      private final String className = MultilayerPerceptronClassificationModel.class.getName();

      private String className() {
         return this.className;
      }

      public MultilayerPerceptronClassificationModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         Tuple2 var5 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
         if (var5 != null) {
            int majorVersion = var5._1$mcI$sp();
            String dataPath = (new Path(path, "data")).toString();
            Dataset df = this.sparkSession().read().parquet(dataPath);
            MultilayerPerceptronClassificationModel var10000;
            if (majorVersion < 3) {
               Row data = (Row)df.select("layers", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"weights"}))).head();
               int[] layers = (int[])((IterableOnceOps)data.getAs(0)).toArray(scala.reflect.ClassTag..MODULE$.Int());
               Vector weights = (Vector)data.getAs(1);
               MultilayerPerceptronClassificationModel model = new MultilayerPerceptronClassificationModel(metadata.uid(), weights);
               var10000 = (MultilayerPerceptronClassificationModel)model.set("layers", layers);
            } else {
               Row data = (Row)df.select("weights", scala.collection.immutable.Nil..MODULE$).head();
               Vector weights = (Vector)data.getAs(0);
               var10000 = new MultilayerPerceptronClassificationModel(metadata.uid(), weights);
            }

            MultilayerPerceptronClassificationModel model = var10000;
            metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
            return model;
         } else {
            throw new MatchError(var5);
         }
      }

      public MultilayerPerceptronClassificationModelReader() {
      }
   }
}
