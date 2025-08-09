package org.apache.spark.ml.clustering;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Matrix;
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
import org.apache.spark.ml.util.BaseReadWrite;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.GeneralMLWritable;
import org.apache.spark.ml.util.GeneralMLWriter;
import org.apache.spark.ml.util.HasTrainingSummary;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
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
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\teh\u0001\u0002\u001b6\u0001\u0001C\u0001B\u0016\u0001\u0003\u0006\u0004%\te\u0016\u0005\t]\u0002\u0011\t\u0011)A\u00051\"I\u0001\u000f\u0001BC\u0002\u0013\u0005Q'\u001d\u0005\tq\u0002\u0011\t\u0011)A\u0005e\"1\u0011\u0010\u0001C\u0001oiDa!\u001f\u0001\u0005\u0002]r\b\"C@\u0001\u0011\u000b\u0007I\u0011AA\u0001\u0011\u001d\t\t\u0002\u0001C!\u0003'Aq!a\n\u0001\t\u0003\tI\u0003C\u0004\u00028\u0001!\t!!\u000f\t\u000f\u0005}\u0002\u0001\"\u0011\u0002B!9\u00111\u0012\u0001\u0005B\u00055\u0005bBAQ\u0001\u0011\u0005\u00111\u0015\u0005\b\u0003o\u0003A\u0011AA]\u0011!\t\u0019\r\u0001C\u0001o\u0005\u0015\u0007bBAg\u0001\u0011\u0005\u0013q\u001a\u0005\b\u0003;\u0004A\u0011IAp\u0011\u001d\t\u0019\u000f\u0001C!\u0003K<q!a;6\u0011\u0003\tiO\u0002\u00045k!\u0005\u0011q\u001e\u0005\u0007sR!\tA!\u0004\t\u000f\t=A\u0003\"\u0011\u0003\u0012!9!1\u0004\u000b\u0005B\tuaA\u0002B\u0013)\u0011\u00139\u0003\u0003\u0006\u00028b\u0011)\u001a!C\u0001\u0005{A!B!\u0013\u0019\u0005#\u0005\u000b\u0011\u0002B \u0011\u0019I\b\u0004\"\u0001\u0003L!I\u0011\u0011\u0003\r\u0002\u0002\u0013\u0005!1\u000b\u0005\n\u0005/B\u0012\u0013!C\u0001\u00053B\u0011B!\u001c\u0019\u0003\u0003%\tEa\u001c\t\u0013\tm\u0004$!A\u0005\u0002\u0005\u0005\u0001\"\u0003B?1\u0005\u0005I\u0011\u0001B@\u0011%\u0011)\tGA\u0001\n\u0003\u00129\tC\u0005\u0003\u0016b\t\t\u0011\"\u0001\u0003\u0018\"I!\u0011\u0015\r\u0002\u0002\u0013\u0005#1\u0015\u0005\n\u0005OC\u0012\u0011!C!\u0005SC\u0011\"!8\u0019\u0003\u0003%\tEa+\t\u0013\t5\u0006$!A\u0005B\t=v!\u0003BZ)\u0005\u0005\t\u0012\u0002B[\r%\u0011)\u0003FA\u0001\u0012\u0013\u00119\f\u0003\u0004zQ\u0011\u0005!Q\u0019\u0005\n\u0003;D\u0013\u0011!C#\u0005WC\u0011Ba2)\u0003\u0003%\tI!3\t\u0013\t5\u0007&!A\u0005\u0002\n=\u0007\"\u0003BnQ\u0005\u0005I\u0011\u0002Bo\r\u0019\u0011)\u000f\u0006\u0003\u0003h\"1\u0011P\fC\u0001\u0005SD\u0011B!</\u0005\u0004%IAa\u001c\t\u0011\t=h\u0006)A\u0005\u0005cBqAa\u0007/\t\u0003\u0012\t\u0010C\u0005\u0003\\R\t\t\u0011\"\u0003\u0003^\nY1*T3b]Nlu\u000eZ3m\u0015\t1t'\u0001\u0006dYV\u001cH/\u001a:j]\u001eT!\u0001O\u001d\u0002\u00055d'B\u0001\u001e<\u0003\u0015\u0019\b/\u0019:l\u0015\taT(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002}\u0005\u0019qN]4\u0004\u0001M)\u0001!Q$K!B\u0019!iQ#\u000e\u0003]J!\u0001R\u001c\u0003\u000b5{G-\u001a7\u0011\u0005\u0019\u0003Q\"A\u001b\u0011\u0005\u0019C\u0015BA%6\u00051YU*Z1ogB\u000b'/Y7t!\tYe*D\u0001M\u0015\tiu'\u0001\u0003vi&d\u0017BA(M\u0005E9UM\\3sC2lEj\u0016:ji\u0006\u0014G.\u001a\t\u0004\u0017F\u001b\u0016B\u0001*M\u0005IA\u0015m\u001d+sC&t\u0017N\\4Tk6l\u0017M]=\u0011\u0005\u0019#\u0016BA+6\u00055YU*Z1ogN+X.\\1ss\u0006\u0019Q/\u001b3\u0016\u0003a\u0003\"!\u00172\u000f\u0005i\u0003\u0007CA._\u001b\u0005a&BA/@\u0003\u0019a$o\\8u})\tq,A\u0003tG\u0006d\u0017-\u0003\u0002b=\u00061\u0001K]3eK\u001aL!a\u00193\u0003\rM#(/\u001b8h\u0015\t\tg\fK\u0002\u0002M2\u0004\"a\u001a6\u000e\u0003!T!![\u001d\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002lQ\n)1+\u001b8dK\u0006\nQ.A\u00032]Ur\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002gY\u0006Y\u0001/\u0019:f]Rlu\u000eZ3m+\u0005\u0011\bCA:x\u001b\u0005!(B\u0001\u001cv\u0015\t1\u0018(A\u0003nY2L'-\u0003\u00025i\u0006a\u0001/\u0019:f]Rlu\u000eZ3mA\u00051A(\u001b8jiz\"2!R>~\u0011\u00151V\u00011\u0001YQ\rYh\r\u001c\u0005\u0006a\u0016\u0001\rA\u001d\u000b\u0002\u000b\u0006Ya.^7GK\u0006$XO]3t+\t\t\u0019\u0001\u0005\u0003\u0002\u0006\u0005\u001dQ\"\u00010\n\u0007\u0005%aLA\u0002J]RDCa\u00024\u0002\u000e\u0005\u0012\u0011qB\u0001\u0006g9\u0002d\u0006M\u0001\u0005G>\u0004\u0018\u0010F\u0002F\u0003+Aq!a\u0006\t\u0001\u0004\tI\"A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002\u001c\u0005\u0005RBAA\u000f\u0015\r\tybN\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0005\u0003G\tiB\u0001\u0005QCJ\fW.T1qQ\rAa\r\\\u0001\u000fg\u0016$h)Z1ukJ,7oQ8m)\u0011\tY#!\f\u000e\u0003\u0001Aa!a\f\n\u0001\u0004A\u0016!\u0002<bYV,\u0007\u0006B\u0005g\u0003g\t#!!\u000e\u0002\u000bIr\u0003G\f\u0019\u0002!M,G\u000f\u0015:fI&\u001cG/[8o\u0007>dG\u0003BA\u0016\u0003wAa!a\f\u000b\u0001\u0004A\u0006\u0006\u0002\u0006g\u0003g\t\u0011\u0002\u001e:b]N4wN]7\u0015\t\u0005\r\u0013Q\r\t\u0005\u0003\u000b\nyF\u0004\u0003\u0002H\u0005ec\u0002BA%\u0003+rA!a\u0013\u0002T9!\u0011QJA)\u001d\rY\u0016qJ\u0005\u0002}%\u0011A(P\u0005\u0003umJ1!a\u0016:\u0003\r\u0019\u0018\u000f\\\u0005\u0005\u00037\ni&A\u0004qC\u000e\\\u0017mZ3\u000b\u0007\u0005]\u0013(\u0003\u0003\u0002b\u0005\r$!\u0003#bi\u00064%/Y7f\u0015\u0011\tY&!\u0018\t\u000f\u0005\u001d4\u00021\u0001\u0002j\u00059A-\u0019;bg\u0016$\b\u0007BA6\u0003o\u0002b!!\u001c\u0002p\u0005MTBAA/\u0013\u0011\t\t(!\u0018\u0003\u000f\u0011\u000bG/Y:fiB!\u0011QOA<\u0019\u0001!A\"!\u001f\u0002f\u0005\u0005\t\u0011!B\u0001\u0003w\u00121a\u0018\u00132#\u0011\ti(a!\u0011\t\u0005\u0015\u0011qP\u0005\u0004\u0003\u0003s&a\u0002(pi\"Lgn\u001a\t\u0005\u0003\u000b\t))C\u0002\u0002\bz\u00131!\u00118zQ\u0011Ya-a\r\u0002\u001fQ\u0014\u0018M\\:g_Jl7k\u00195f[\u0006$B!a$\u0002\u001cB!\u0011\u0011SAL\u001b\t\t\u0019J\u0003\u0003\u0002\u0016\u0006u\u0013!\u0002;za\u0016\u001c\u0018\u0002BAM\u0003'\u0013!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\ti\n\u0004a\u0001\u0003\u001f\u000baa]2iK6\f\u0007f\u0001\u0007gY\u00069\u0001O]3eS\u000e$H\u0003BA\u0002\u0003KCq!a*\u000e\u0001\u0004\tI+\u0001\u0005gK\u0006$XO]3t!\u0011\tY+!-\u000e\u0005\u00055&bAAXo\u00051A.\u001b8bY\u001eLA!a-\u0002.\n1a+Z2u_JDC!\u00044\u0002\u000e\u0005q1\r\\;ti\u0016\u00148)\u001a8uKJ\u001cXCAA^!\u0019\t)!!0\u0002*&\u0019\u0011q\u00180\u0003\u000b\u0005\u0013(/Y=)\t91\u00171G\u0001\u0014G2,8\u000f^3s\u0007\u0016tG/\u001a:NCR\u0014\u0018\u000e_\u000b\u0003\u0003\u000f\u0004B!a+\u0002J&!\u00111ZAW\u0005\u0019i\u0015\r\u001e:jq\u0006)qO]5uKV\u0011\u0011\u0011\u001b\t\u0004\u0017\u0006M\u0017bAAk\u0019\nyq)\u001a8fe\u0006dW\nT,sSR,'\u000f\u000b\u0003\u0011M\u0006e\u0017EAAn\u0003\u0015\tdF\u000e\u00181\u0003!!xn\u0015;sS:<G#\u0001-)\tE1\u0017QB\u0001\bgVlW.\u0019:z+\u0005\u0019\u0006\u0006\u0002\ng\u0003gA3\u0001\u00014m\u0003-YU*Z1og6{G-\u001a7\u0011\u0005\u0019#2c\u0002\u000b\u0002r\u0006]\u0018Q \t\u0005\u0003\u000b\t\u00190C\u0002\u0002vz\u0013a!\u00118z%\u00164\u0007\u0003B&\u0002z\u0016K1!a?M\u0005)iEJU3bI\u0006\u0014G.\u001a\t\u0005\u0003\u007f\u0014I!\u0004\u0002\u0003\u0002)!!1\u0001B\u0003\u0003\tIwN\u0003\u0002\u0003\b\u0005!!.\u0019<b\u0013\u0011\u0011YA!\u0001\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u00055\u0018\u0001\u0002:fC\u0012,\"Aa\u0005\u0011\t-\u0013)\"R\u0005\u0004\u0005/a%\u0001C'M%\u0016\fG-\u001a:)\tY1\u0017\u0011\\\u0001\u0005Y>\fG\rF\u0002F\u0005?AaA!\t\u0018\u0001\u0004A\u0016\u0001\u00029bi\"DCa\u00064\u0002Z\n9q\n\u001c3ECR\f7c\u0002\r\u0002r\n%\"q\u0006\t\u0005\u0003\u000b\u0011Y#C\u0002\u0003.y\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u00032\teb\u0002\u0002B\u001a\u0005oq1a\u0017B\u001b\u0013\u0005y\u0016bAA.=&!!1\u0002B\u001e\u0015\r\tYFX\u000b\u0003\u0005\u007f\u0001b!!\u0002\u0002>\n\u0005\u0003\u0003\u0002B\"\u0005\u000fj!A!\u0012\u000b\u0007\u0005=V/\u0003\u0003\u00024\n\u0015\u0013aD2mkN$XM]\"f]R,'o\u001d\u0011\u0015\t\t5#\u0011\u000b\t\u0004\u0005\u001fBR\"\u0001\u000b\t\u000f\u0005]6\u00041\u0001\u0003@Q!!Q\nB+\u0011%\t9\f\bI\u0001\u0002\u0004\u0011y$\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\tm#\u0006\u0002B \u0005;Z#Aa\u0018\u0011\t\t\u0005$\u0011N\u0007\u0003\u0005GRAA!\u001a\u0003h\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0003SzKAAa\u001b\u0003d\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u0011\t\b\u0005\u0003\u0003t\teTB\u0001B;\u0015\u0011\u00119H!\u0002\u0002\t1\fgnZ\u0005\u0004G\nU\u0014\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u0007\u0013\t\tC\u0005\u0003\u0004\u0002\n\t\u00111\u0001\u0002\u0004\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!#\u0011\r\t-%\u0011SAB\u001b\t\u0011iIC\u0002\u0003\u0010z\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011\u0019J!$\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u00053\u0013y\n\u0005\u0003\u0002\u0006\tm\u0015b\u0001BO=\n9!i\\8mK\u0006t\u0007\"\u0003BBE\u0005\u0005\t\u0019AAB\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\tE$Q\u0015\u0005\n\u0005\u0007\u001b\u0013\u0011!a\u0001\u0003\u0007\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0007!\"A!\u001d\u0002\r\u0015\fX/\u00197t)\u0011\u0011IJ!-\t\u0013\t\re%!AA\u0002\u0005\r\u0015aB(mI\u0012\u000bG/\u0019\t\u0004\u0005\u001fB3#\u0002\u0015\u0003:\u0006u\b\u0003\u0003B^\u0005\u0003\u0014yD!\u0014\u000e\u0005\tu&b\u0001B`=\u00069!/\u001e8uS6,\u0017\u0002\u0002Bb\u0005{\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82)\t\u0011),A\u0003baBd\u0017\u0010\u0006\u0003\u0003N\t-\u0007bBA\\W\u0001\u0007!qH\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011\tNa6\u0011\r\u0005\u0015!1\u001bB \u0013\r\u0011)N\u0018\u0002\u0007\u001fB$\u0018n\u001c8\t\u0013\teG&!AA\u0002\t5\u0013a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\u001c\t\u0005\u0005g\u0012\t/\u0003\u0003\u0003d\nU$AB(cU\u0016\u001cGOA\tL\u001b\u0016\fgn]'pI\u0016d'+Z1eKJ\u001c2A\fB\n)\t\u0011Y\u000fE\u0002\u0003P9\n\u0011b\u00197bgNt\u0015-\\3\u0002\u0015\rd\u0017m]:OC6,\u0007\u0005F\u0002F\u0005gDaA!\t3\u0001\u0004A\u0006\u0006\u0002\u000bg\u00033DCa\u00054\u0002Z\u0002"
)
public class KMeansModel extends Model implements KMeansParams, GeneralMLWritable, HasTrainingSummary {
   private int numFeatures;
   private final String uid;
   private final org.apache.spark.mllib.clustering.KMeansModel parentModel;
   private Option trainingSummary;
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
   private volatile boolean bitmap$0;

   public static KMeansModel load(final String path) {
      return KMeansModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return KMeansModel$.MODULE$.read();
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

   public final Option trainingSummary() {
      return this.trainingSummary;
   }

   public final void trainingSummary_$eq(final Option x$1) {
      this.trainingSummary = x$1;
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

   public org.apache.spark.mllib.clustering.KMeansModel parentModel() {
      return this.parentModel;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.numFeatures = ((Vector).MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps(this.parentModel().clusterCenters()))).size();
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

   public KMeansModel copy(final ParamMap extra) {
      KMeansModel copied = (KMeansModel)this.copyValues(new KMeansModel(this.uid(), this.parentModel()), extra);
      return (KMeansModel)((Model)copied.setSummary(this.trainingSummary())).setParent(this.parent());
   }

   public KMeansModel setFeaturesCol(final String value) {
      return (KMeansModel)this.set(this.featuresCol(), value);
   }

   public KMeansModel setPredictionCol(final String value) {
      return (KMeansModel)this.set(this.predictionCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (vector) -> BoxesRunTime.boxToInteger($anonfun$transform$1(this, vector));
      TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Int();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(KMeansModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      UserDefinedFunction predictUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()));
      return dataset.withColumn((String)this.$(this.predictionCol()), predictUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.columnToVector(dataset, this.getFeaturesCol())}))), outputSchema.apply((String)this.$(this.predictionCol())).metadata());
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateNumValues(outputSchema, (String)this.$(this.predictionCol()), this.parentModel().k());
      }

      return outputSchema;
   }

   public int predict(final org.apache.spark.ml.linalg.Vector features) {
      return this.parentModel().predict(Vectors$.MODULE$.fromML(features));
   }

   public org.apache.spark.ml.linalg.Vector[] clusterCenters() {
      return (org.apache.spark.ml.linalg.Vector[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.parentModel().clusterCenters()), (x$1) -> x$1.asML(), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.ml.linalg.Vector.class));
   }

   public Matrix clusterCenterMatrix() {
      return org.apache.spark.ml.linalg.Matrices..MODULE$.fromVectors(.MODULE$.toSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.clusterCenters())));
   }

   public GeneralMLWriter write() {
      return new GeneralMLWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "KMeansModel: uid=" + var10000 + ", k=" + this.parentModel().k() + ", distanceMeasure=" + this.$(this.distanceMeasure()) + ", numFeatures=" + this.numFeatures();
   }

   public KMeansSummary summary() {
      return (KMeansSummary)HasTrainingSummary.summary$(this);
   }

   // $FF: synthetic method
   public static final int $anonfun$transform$1(final KMeansModel $this, final org.apache.spark.ml.linalg.Vector vector) {
      return $this.predict(vector);
   }

   public KMeansModel(final String uid, final org.apache.spark.mllib.clustering.KMeansModel parentModel) {
      this.uid = uid;
      this.parentModel = parentModel;
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
      HasTrainingSummary.$init$(this);
      Statics.releaseFence();
   }

   public KMeansModel() {
      this("", (org.apache.spark.mllib.clustering.KMeansModel)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class OldData implements Product, Serializable {
      private final Vector[] clusterCenters;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Vector[] clusterCenters() {
         return this.clusterCenters;
      }

      public OldData copy(final Vector[] clusterCenters) {
         return new OldData(clusterCenters);
      }

      public Vector[] copy$default$1() {
         return this.clusterCenters();
      }

      public String productPrefix() {
         return "OldData";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.clusterCenters();
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
         return x$1 instanceof OldData;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "clusterCenters";
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
         boolean var10000;
         if (this != x$1) {
            label36: {
               if (x$1 instanceof OldData) {
                  OldData var4 = (OldData)x$1;
                  if (this.clusterCenters() == var4.clusterCenters() && var4.canEqual(this)) {
                     break label36;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public OldData(final Vector[] clusterCenters) {
         this.clusterCenters = clusterCenters;
         Product.$init$(this);
      }
   }

   private static class OldData$ extends AbstractFunction1 implements Serializable {
      public static final OldData$ MODULE$ = new OldData$();

      public final String toString() {
         return "OldData";
      }

      public OldData apply(final Vector[] clusterCenters) {
         return new OldData(clusterCenters);
      }

      public Option unapply(final OldData x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.clusterCenters()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(OldData$.class);
      }

      public OldData$() {
      }
   }

   private static class KMeansModelReader extends MLReader {
      private final String className = KMeansModel.class.getName();

      private String className() {
         return this.className;
      }

      public KMeansModel load(final String path) {
         SparkSession sparkSession = BaseReadWrite.sparkSession$(this);
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, sparkSession, this.className());
         String dataPath = (new Path(path, "data")).toString();
         Vector[] var12;
         if (org.apache.spark.util.VersionUtils..MODULE$.majorVersion(metadata.sparkVersion()) >= 2) {
            Dataset var10000 = sparkSession.read().parquet(dataPath);
            SQLImplicits var10001 = sparkSession.implicits();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(KMeansModelReader.class.getClassLoader());

            final class $typecreator5$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.clustering.ClusterData").asType().toTypeConstructor();
               }

               public $typecreator5$1() {
               }
            }

            Dataset data = var10000.as(var10001.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1())));
            var12 = (Vector[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(data.collect()), (x$2) -> BoxesRunTime.boxToInteger($anonfun$load$1(x$2)), scala.math.Ordering.Int..MODULE$)), (x$3) -> x$3.clusterCenter(), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.ml.linalg.Vector.class))), (v) -> Vectors$.MODULE$.fromML(v), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
         } else {
            Dataset var13 = sparkSession.read().parquet(dataPath);
            SQLImplicits var14 = sparkSession.implicits();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(KMeansModelReader.class.getClassLoader());

            final class $typecreator10$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.clustering.KMeansModel.OldData").asType().toTypeConstructor();
               }

               public $typecreator10$1() {
               }
            }

            var12 = ((OldData)var13.as(var14.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1()))).head()).clusterCenters();
         }

         Vector[] clusterCenters = var12;
         KMeansModel model = new KMeansModel(metadata.uid(), new org.apache.spark.mllib.clustering.KMeansModel(clusterCenters));
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      // $FF: synthetic method
      public static final int $anonfun$load$1(final ClusterData x$2) {
         return x$2.clusterIdx();
      }

      public KMeansModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
