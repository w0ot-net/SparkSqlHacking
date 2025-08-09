package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.stat.distribution.MultivariateGaussian;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.JValue;
import scala.MatchError;
import scala.Some;
import scala.Tuple1;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\teh\u0001\u0002\u001d:\u0001\u0011C\u0001\"\u0018\u0001\u0003\u0006\u0004%\tA\u0018\u0005\t]\u0002\u0011\t\u0011)A\u0005?\"A\u0001\u000f\u0001BC\u0002\u0013\u0005\u0011\u000f\u0003\u0005}\u0001\t\u0005\t\u0015!\u0003s\u0011\u0015q\b\u0001\"\u0001\u0000\u0011\u001d\ty\u0001\u0001C!\u0003#Aq!a\u0010\u0001\t\u0003\t\t\u0005C\u0004\u0002L\u0001!\t!!\u0014\t\u000f\u0005-\u0003\u0001\"\u0001\u0002p!9\u00111\n\u0001\u0005\u0002\u0005m\u0004bBAQ\u0001\u0011\u0005\u00111\u0015\u0005\b\u0003C\u0003A\u0011AAV\u0011\u001d\t\t\f\u0001C\u0005\u0003g;q!!5:\u0011\u0003\t\u0019N\u0002\u00049s!\u0005\u0011Q\u001b\u0005\u0007}>!\t!a:\b\u000f\u0005%x\u0002#\u0003\u0002l\u001a9\u0011q^\b\t\n\u0005E\bB\u0002@\u0013\t\u0003\t\u0019P\u0002\u0004\u0002vJ\u0001\u0015q\u001f\u0005\u000b\u0003\u007f$\"Q3A\u0005\u0002\t\u0005\u0001\"\u0003B\u0002)\tE\t\u0015!\u0003c\u0011)\u0011)\u0001\u0006BK\u0002\u0013\u0005!q\u0001\u0005\u000b\u0005\u0013!\"\u0011#Q\u0001\n\u0005\u0005\u0004B\u0003B\u0006)\tU\r\u0011\"\u0001\u0003\u000e!Q!Q\u0003\u000b\u0003\u0012\u0003\u0006IAa\u0004\t\ry$B\u0011\u0001B\f\u0011%\u0011\u0019\u0003FA\u0001\n\u0003\u0011)\u0003C\u0005\u0003.Q\t\n\u0011\"\u0001\u00030!I!1\t\u000b\u0012\u0002\u0013\u0005!Q\t\u0005\n\u0005\u0013\"\u0012\u0013!C\u0001\u0005\u0017B\u0011Ba\u0014\u0015\u0003\u0003%\tE!\u0015\t\u0013\t]C#!A\u0005\u0002\u0005\u0005\u0003\"\u0003B-)\u0005\u0005I\u0011\u0001B.\u0011%\u00119\u0007FA\u0001\n\u0003\u0012I\u0007C\u0005\u0003xQ\t\t\u0011\"\u0001\u0003z!I!1\u0011\u000b\u0002\u0002\u0013\u0005#Q\u0011\u0005\n\u0005\u0013#\u0012\u0011!C!\u0005\u0017C\u0011B!$\u0015\u0003\u0003%\tEa$\t\u0013\tEE#!A\u0005B\tMu!\u0003BL%\u0005\u0005\t\u0012\u0001BM\r%\t)PEA\u0001\u0012\u0003\u0011Y\n\u0003\u0004\u007fU\u0011\u0005!\u0011\u0016\u0005\n\u0005\u001bS\u0013\u0011!C#\u0005\u001fC\u0011Ba++\u0003\u0003%\tI!,\t\u0013\tU&&!A\u0005\u0002\n]\u0006\"\u0003BeU\u0005\u0005I\u0011\u0002Bf\u0011%\u0011\u0019N\u0005b\u0001\n\u0003\u0011\t\u0006\u0003\u0005\u0003VJ\u0001\u000b\u0011\u0002B*\u0011%\u00119N\u0005b\u0001\n\u0003\u0011\t\u0006\u0003\u0005\u0003ZJ\u0001\u000b\u0011\u0002B*\u0011\u001d\tyA\u0005C\u0001\u00057DqA!:\u0013\t\u0003\u00119\u000fC\u0004\u0003f>!\tE!<\t\u0013\t%w\"!A\u0005\n\t-'\u0001F$bkN\u001c\u0018.\u00198NSb$XO]3N_\u0012,GN\u0003\u0002;w\u0005Q1\r\\;ti\u0016\u0014\u0018N\\4\u000b\u0005qj\u0014!B7mY&\u0014'B\u0001 @\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0015)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0005\u0006\u0019qN]4\u0004\u0001M!\u0001!R&X!\t1\u0015*D\u0001H\u0015\u0005A\u0015!B:dC2\f\u0017B\u0001&H\u0005\u0019\te.\u001f*fMB\u0011A\n\u0016\b\u0003\u001bJs!AT)\u000e\u0003=S!\u0001U\"\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0015BA*H\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0016,\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005M;\u0005C\u0001-\\\u001b\u0005I&B\u0001.<\u0003\u0011)H/\u001b7\n\u0005qK&\u0001C*bm\u0016\f'\r\\3\u0002\u000f],\u0017n\u001a5ugV\tq\fE\u0002GA\nL!!Y$\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0019\u001b\u0017B\u00013H\u0005\u0019!u.\u001e2mK\"\u001a\u0011A\u001a7\u0011\u0005\u001dTW\"\u00015\u000b\u0005%l\u0014AC1o]>$\u0018\r^5p]&\u00111\u000e\u001b\u0002\u0006'&t7-Z\u0011\u0002[\u0006)\u0011GL\u001a/a\u0005Aq/Z5hQR\u001c\b\u0005K\u0002\u0003M2\f\u0011bZ1vgNL\u0017M\\:\u0016\u0003I\u00042A\u00121t!\t!\u00180D\u0001v\u0015\t1x/\u0001\u0007eSN$(/\u001b2vi&|gN\u0003\u0002yw\u0005!1\u000f^1u\u0013\tQXO\u0001\u000bNk2$\u0018N^1sS\u0006$XmR1vgNL\u0017M\u001c\u0015\u0004\u0007\u0019d\u0017AC4bkN\u001c\u0018.\u00198tA!\u001aAA\u001a7\u0002\rqJg.\u001b;?)\u0019\t\t!!\u0002\u0002\nA\u0019\u00111\u0001\u0001\u000e\u0003eBQ!X\u0003A\u0002}CC!!\u0002gY\")\u0001/\u0002a\u0001e\"\"\u0011\u0011\u00024mQ\r)a\r\\\u0001\u0005g\u00064X\r\u0006\u0004\u0002\u0014\u0005e\u0011Q\u0005\t\u0004\r\u0006U\u0011bAA\f\u000f\n!QK\\5u\u0011\u001d\tYB\u0002a\u0001\u0003;\t!a]2\u0011\t\u0005}\u0011\u0011E\u0007\u0002{%\u0019\u00111E\u001f\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\u000f\u0005\u001db\u00011\u0001\u0002*\u0005!\u0001/\u0019;i!\u0011\tY#a\r\u000f\t\u00055\u0012q\u0006\t\u0003\u001d\u001eK1!!\rH\u0003\u0019\u0001&/\u001a3fM&!\u0011QGA\u001c\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011G$)\t\u00191\u00171H\u0011\u0003\u0003{\tQ!\r\u00185]A\n\u0011a[\u000b\u0003\u0003\u0007\u00022ARA#\u0013\r\t9e\u0012\u0002\u0004\u0013:$\bfA\u0004gY\u00069\u0001O]3eS\u000e$H\u0003BA(\u00037\u0002b!!\u0015\u0002X\u0005\rSBAA*\u0015\r\t)&P\u0001\u0004e\u0012$\u0017\u0002BA-\u0003'\u00121A\u0015#E\u0011\u001d\ti\u0006\u0003a\u0001\u0003?\na\u0001]8j]R\u001c\bCBA)\u0003/\n\t\u0007\u0005\u0003\u0002d\u0005%TBAA3\u0015\r\t9gO\u0001\u0007Y&t\u0017\r\\4\n\t\u0005-\u0014Q\r\u0002\u0007-\u0016\u001cGo\u001c:)\u0007!1G\u000e\u0006\u0003\u0002D\u0005E\u0004bBA:\u0013\u0001\u0007\u0011\u0011M\u0001\u0006a>Lg\u000e\u001e\u0015\u0005\u0013\u0019\f9(\t\u0002\u0002z\u0005)\u0011GL\u001b/aQ!\u0011QPAN!\u0019\ty(!#\u0002\u000e6\u0011\u0011\u0011\u0011\u0006\u0005\u0003\u0007\u000b))\u0001\u0003kCZ\f'bAAD{\u0005\u0019\u0011\r]5\n\t\u0005-\u0015\u0011\u0011\u0002\b\u0015\u00064\u0018M\u0015#E!\u0011\ty)a&\u000e\u0005\u0005E%\u0002BAJ\u0003+\u000bA\u0001\\1oO*\u0011\u00111Q\u0005\u0005\u00033\u000b\tJA\u0004J]R,w-\u001a:\t\u000f\u0005u#\u00021\u0001\u0002\u001eB1\u0011qPAE\u0003CBCA\u00034\u0002<\u0005Y\u0001O]3eS\u000e$8k\u001c4u)\u0011\t)+a*\u0011\u000b\u0005E\u0013qK0\t\u000f\u0005u3\u00021\u0001\u0002`!\u001a1B\u001a7\u0015\u0007}\u000bi\u000bC\u0004\u0002t1\u0001\r!!\u0019)\t11\u00171H\u0001\u0017G>l\u0007/\u001e;f'>4G/Q:tS\u001etW.\u001a8ugRIq,!.\u0002H\u0006-\u0017Q\u001a\u0005\b\u0003ok\u0001\u0019AA]\u0003\t\u0001H\u000fE\u0003\u0002<\u0006\r'-\u0004\u0002\u0002>*!\u0011qMA`\u0015\t\t\t-\u0001\u0004ce\u0016,'0Z\u0005\u0005\u0003\u000b\fiLA\u0006EK:\u001cXMV3di>\u0014\bBBAe\u001b\u0001\u0007!/A\u0003eSN$8\u000fC\u0003^\u001b\u0001\u0007q\fC\u0004\u0002@5\u0001\r!a\u0011)\u0007\u00011G.\u0001\u000bHCV\u001c8/[1o\u001b&DH/\u001e:f\u001b>$W\r\u001c\t\u0004\u0003\u0007y1CB\bF\u0003/\fi\u000eE\u0003Y\u00033\f\t!C\u0002\u0002\\f\u0013a\u0001T8bI\u0016\u0014\b\u0003BAp\u0003Kl!!!9\u000b\t\u0005\r\u0018QS\u0001\u0003S>L1!VAq)\t\t\u0019.\u0001\u0007TCZ,Gj\\1e-Fz\u0006\u0007E\u0002\u0002nJi\u0011a\u0004\u0002\r'\u00064X\rT8bIZ\u000bt\fM\n\u0003%\u0015#\"!a;\u0003\t\u0011\u000bG/Y\n\u0006)\u0015\u000bIp\u0013\t\u0004\r\u0006m\u0018bAA\u007f\u000f\n9\u0001K]8ek\u000e$\u0018AB<fS\u001eDG/F\u0001c\u0003\u001d9X-[4ii\u0002\n!!\\;\u0016\u0005\u0005\u0005\u0014aA7vA\u0005)1/[4nCV\u0011!q\u0002\t\u0005\u0003G\u0012\t\"\u0003\u0003\u0003\u0014\u0005\u0015$AB'biJL\u00070\u0001\u0004tS\u001el\u0017\r\t\u000b\t\u00053\u0011iBa\b\u0003\"A\u0019!1\u0004\u000b\u000e\u0003IAa!a@\u001c\u0001\u0004\u0011\u0007b\u0002B\u00037\u0001\u0007\u0011\u0011\r\u0005\b\u0005\u0017Y\u0002\u0019\u0001B\b\u0003\u0011\u0019w\u000e]=\u0015\u0011\te!q\u0005B\u0015\u0005WA\u0001\"a@\u001d!\u0003\u0005\rA\u0019\u0005\n\u0005\u000ba\u0002\u0013!a\u0001\u0003CB\u0011Ba\u0003\u001d!\u0003\u0005\rAa\u0004\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!\u0011\u0007\u0016\u0004E\nM2F\u0001B\u001b!\u0011\u00119Da\u0010\u000e\u0005\te\"\u0002\u0002B\u001e\u0005{\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005%<\u0015\u0002\u0002B!\u0005s\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"Aa\u0012+\t\u0005\u0005$1G\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\u0011iE\u000b\u0003\u0003\u0010\tM\u0012!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0003TA!\u0011q\u0012B+\u0013\u0011\t)$!%\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!!Q\fB2!\r1%qL\u0005\u0004\u0005C:%aA!os\"I!Q\r\u0012\u0002\u0002\u0003\u0007\u00111I\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t-\u0004C\u0002B7\u0005g\u0012i&\u0004\u0002\u0003p)\u0019!\u0011O$\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003v\t=$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BAa\u001f\u0003\u0002B\u0019aI! \n\u0007\t}tIA\u0004C_>dW-\u00198\t\u0013\t\u0015D%!AA\u0002\tu\u0013A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$BAa\u0015\u0003\b\"I!QM\u0013\u0002\u0002\u0003\u0007\u00111I\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u00111I\u0001\ti>\u001cFO]5oOR\u0011!1K\u0001\u0007KF,\u0018\r\\:\u0015\t\tm$Q\u0013\u0005\n\u0005KB\u0013\u0011!a\u0001\u0005;\nA\u0001R1uCB\u0019!1\u0004\u0016\u0014\u000b)\u0012i*!8\u0011\u0017\t}%Q\u00152\u0002b\t=!\u0011D\u0007\u0003\u0005CS1Aa)H\u0003\u001d\u0011XO\u001c;j[\u0016LAAa*\u0003\"\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0015\u0005\te\u0015!B1qa2LH\u0003\u0003B\r\u0005_\u0013\tLa-\t\r\u0005}X\u00061\u0001c\u0011\u001d\u0011)!\fa\u0001\u0003CBqAa\u0003.\u0001\u0004\u0011y!A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\te&Q\u0019\t\u0006\r\nm&qX\u0005\u0004\u0005{;%AB(qi&|g\u000e\u0005\u0005G\u0005\u0003\u0014\u0017\u0011\rB\b\u0013\r\u0011\u0019m\u0012\u0002\u0007)V\u0004H.Z\u001a\t\u0013\t\u001dg&!AA\u0002\te\u0011a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!Q\u001a\t\u0005\u0003\u001f\u0013y-\u0003\u0003\u0003R\u0006E%AB(cU\u0016\u001cG/A\tg_Jl\u0017\r\u001e,feNLwN\u001c,2?B\n!CZ8s[\u0006$h+\u001a:tS>tg+M01A\u0005i1\r\\1tg:\u000bW.\u001a,2?B\nab\u00197bgNt\u0015-\\3Wc}\u0003\u0004\u0005\u0006\u0006\u0002\u0014\tu'q\u001cBq\u0005GDq!a\u00075\u0001\u0004\ti\u0002C\u0004\u0002(Q\u0002\r!!\u000b\t\u000bu#\u0004\u0019A0\t\u000bA$\u0004\u0019\u0001:\u0002\t1|\u0017\r\u001a\u000b\u0007\u0003\u0003\u0011IOa;\t\u000f\u0005mQ\u00071\u0001\u0002\u001e!9\u0011qE\u001bA\u0002\u0005%BCBA\u0001\u0005_\u0014\t\u0010C\u0004\u0002\u001cY\u0002\r!!\b\t\u000f\u0005\u001db\u00071\u0001\u0002*!\"aGZA\u001eQ\u0011ya-a\u000f)\t91\u00171\b"
)
public class GaussianMixtureModel implements Serializable, Saveable {
   private final double[] weights;
   private final MultivariateGaussian[] gaussians;

   public static GaussianMixtureModel load(final SparkContext sc, final String path) {
      return GaussianMixtureModel$.MODULE$.load(sc, path);
   }

   public double[] weights() {
      return this.weights;
   }

   public MultivariateGaussian[] gaussians() {
      return this.gaussians;
   }

   public void save(final SparkContext sc, final String path) {
      GaussianMixtureModel.SaveLoadV1_0$.MODULE$.save(sc, path, this.weights(), this.gaussians());
   }

   public int k() {
      return this.weights().length;
   }

   public RDD predict(final RDD points) {
      RDD responsibilityMatrix = this.predictSoft(points);
      return responsibilityMatrix.map((r) -> BoxesRunTime.boxToInteger($anonfun$predict$1(r)), .MODULE$.Int());
   }

   public int predict(final Vector point) {
      double[] r = this.predictSoft(point);
      Object qual$1 = scala.Predef..MODULE$.doubleArrayOps(r);
      double x$1 = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(r).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
      int x$2 = scala.collection.ArrayOps..MODULE$.indexOf$default$2$extension(qual$1);
      return scala.collection.ArrayOps..MODULE$.indexOf$extension(qual$1, BoxesRunTime.boxToDouble(x$1), x$2);
   }

   public JavaRDD predict(final JavaRDD points) {
      return this.predict(points.rdd()).toJavaRDD();
   }

   public RDD predictSoft(final RDD points) {
      SparkContext sc = points.sparkContext();
      Broadcast bcDists = sc.broadcast(this.gaussians(), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(MultivariateGaussian.class)));
      Broadcast bcWeights = sc.broadcast(this.weights(), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      return points.map((x) -> this.computeSoftAssignments(x.asBreeze().toDenseVector$mcD$sp(.MODULE$.Double()), (MultivariateGaussian[])bcDists.value(), (double[])bcWeights.value(), this.k()), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
   }

   public double[] predictSoft(final Vector point) {
      return this.computeSoftAssignments(point.asBreeze().toDenseVector$mcD$sp(.MODULE$.Double()), this.gaussians(), this.weights(), this.k());
   }

   private double[] computeSoftAssignments(final DenseVector pt, final MultivariateGaussian[] dists, final double[] weights, final int k) {
      double[] p = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.doubleArrayOps(weights), scala.Predef..MODULE$.wrapRefArray(dists))), (x0$1) -> BoxesRunTime.boxToDouble($anonfun$computeSoftAssignments$1(pt, x0$1)), .MODULE$.Double());
      double pSum = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(p).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), k).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> p[i] /= pSum);
      return p;
   }

   // $FF: synthetic method
   public static final int $anonfun$predict$1(final double[] r) {
      Object qual$1 = scala.Predef..MODULE$.doubleArrayOps(r);
      double x$1 = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(r).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
      int x$2 = scala.collection.ArrayOps..MODULE$.indexOf$default$2$extension(qual$1);
      return scala.collection.ArrayOps..MODULE$.indexOf$extension(qual$1, BoxesRunTime.boxToDouble(x$1), x$2);
   }

   // $FF: synthetic method
   public static final double $anonfun$computeSoftAssignments$1(final DenseVector pt$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         double weight = x0$1._1$mcD$sp();
         MultivariateGaussian dist = (MultivariateGaussian)x0$1._2();
         return MLUtils$.MODULE$.EPSILON() + weight * dist.pdf((breeze.linalg.Vector)pt$1);
      } else {
         throw new MatchError(x0$1);
      }
   }

   public GaussianMixtureModel(final double[] weights, final MultivariateGaussian[] gaussians) {
      this.weights = weights;
      this.gaussians = gaussians;
      scala.Predef..MODULE$.require(weights.length == gaussians.length, () -> "Length of weight and Gaussian arrays must match");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String formatVersionV1_0 = "1.0";
      private static final String classNameV1_0 = "org.apache.spark.mllib.clustering.GaussianMixtureModel";

      public String formatVersionV1_0() {
         return formatVersionV1_0;
      }

      public String classNameV1_0() {
         return classNameV1_0;
      }

      public void save(final SparkContext sc, final String path, final double[] weights, final MultivariateGaussian[] gaussians) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.classNameV1_0()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.formatVersionV1_0()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("k"), BoxesRunTime.boxToInteger(weights.length)), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(Loader$.MODULE$.metadataPath(path));
         GaussianMixtureModel$SaveLoadV1_0$Data[] dataArray = (GaussianMixtureModel$SaveLoadV1_0$Data[])scala.Array..MODULE$.tabulate(weights.length, (i) -> $anonfun$save$5(weights, gaussians, BoxesRunTime.unboxToInt(i)), .MODULE$.apply(GaussianMixtureModel$SaveLoadV1_0$Data.class));
         RDD var12 = sc.makeRDD(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(dataArray).toImmutableArraySeq(), 1, .MODULE$.apply(GaussianMixtureModel$SaveLoadV1_0$Data.class));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.GaussianMixtureModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(var12, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public GaussianMixtureModel load(final SparkContext sc, final String path) {
         String dataPath = Loader$.MODULE$.dataPath(path);
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Dataset dataFrame = spark.read().parquet(dataPath);
         Loader$ var10000 = Loader$.MODULE$;
         StructType var10001 = dataFrame.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.clustering.GaussianMixtureModel.SaveLoadV1_0.Data").asType().toTypeConstructor();
            }

            public $typecreator1$2() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
         Row[] dataArray = (Row[])dataFrame.select("weight", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"mu", "sigma"}))).collect();
         Tuple2 var11 = scala.collection.ArrayOps..MODULE$.unzip$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])dataArray), (x0$1) -> {
            if (x0$1 != null) {
               Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
                  Object weight = ((SeqOps)var3.get()).apply(0);
                  Object mu = ((SeqOps)var3.get()).apply(1);
                  Object sigma = ((SeqOps)var3.get()).apply(2);
                  if (weight instanceof Double) {
                     double var7 = BoxesRunTime.unboxToDouble(weight);
                     if (mu instanceof Vector) {
                        Vector var9 = (Vector)mu;
                        if (sigma instanceof Matrix) {
                           Matrix var10 = (Matrix)sigma;
                           return new Tuple2(BoxesRunTime.boxToDouble(var7), new MultivariateGaussian(var9, var10));
                        }
                     }
                  }
               }
            }

            throw new MatchError(x0$1);
         }, .MODULE$.apply(Tuple2.class))), scala.Predef..MODULE$.$conforms(), .MODULE$.Double(), .MODULE$.apply(MultivariateGaussian.class));
         if (var11 != null) {
            double[] weights = (double[])var11._1();
            MultivariateGaussian[] gaussians = (MultivariateGaussian[])var11._2();
            Tuple2 var10 = new Tuple2(weights, gaussians);
            double[] weights = (double[])var10._1();
            MultivariateGaussian[] gaussians = (MultivariateGaussian[])var10._2();
            return new GaussianMixtureModel(weights, gaussians);
         } else {
            throw new MatchError(var11);
         }
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$4(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final GaussianMixtureModel$SaveLoadV1_0$Data $anonfun$save$5(final double[] weights$1, final MultivariateGaussian[] gaussians$1, final int i) {
         return new GaussianMixtureModel$SaveLoadV1_0$Data(weights$1[i], gaussians$1[i].mu(), gaussians$1[i].sigma());
      }

      public SaveLoadV1_0$() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
