package org.apache.spark.ml.r;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineModel$;
import org.apache.spark.ml.classification.MultilayerPerceptronClassificationModel;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.json4s.DefaultFormats;
import org.json4s.DefaultFormats.;
import scala.Tuple1;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f!B\u000e\u001d\u0001q1\u0003\u0002C\u001a\u0001\u0005\u000b\u0007I\u0011A\u001b\t\u0011i\u0002!\u0011!Q\u0001\nYBQa\u000f\u0001\u0005\nqBq\u0001\u0011\u0001C\u0002\u0013%\u0011\t\u0003\u0004I\u0001\u0001\u0006IA\u0011\u0005\t\u0013\u0002A)\u0019!C\u0001\u0015\"A\u0011\u000b\u0001EC\u0002\u0013\u0005!\u000bC\u0003X\u0001\u0011\u0005\u0001\f\u0003\u0004\u0000\u0001\u0011\u0005\u0013\u0011A\u0004\t\u0003\u0013a\u0002\u0012\u0001\u000f\u0002\f\u001991\u0004\bE\u00019\u00055\u0001BB\u001e\f\t\u0003\t)\u0002C\u0005\u0002\u0018-\u0011\r\u0011\"\u0001\u0002\u001a!A\u00111F\u0006!\u0002\u0013\tY\u0002C\u0005\u0002.-\u0011\r\u0011\"\u0001\u0002\u001a!A\u0011qF\u0006!\u0002\u0013\tY\u0002C\u0004\u00022-!\t!a\r\t\u000f\u000554\u0002\"\u0011\u0002p!9\u0011qO\u0006\u0005B\u0005edABA@\u0017\u0001\t\t\t\u0003\u0004<)\u0011\u0005\u00111\u0011\u0005\b\u0003o\"B\u0011IAE\r\u0019\tii\u0003\u0001\u0002\u0010\"I\u0011\u0011S\f\u0003\u0002\u0003\u0006I!\u0010\u0005\u0007w]!\t!a%\t\u000f\u0005eu\u0003\"\u0015\u0002\u001c\n)S*\u001e7uS2\f\u00170\u001a:QKJ\u001cW\r\u001d;s_:\u001cE.Y:tS\u001aLWM],sCB\u0004XM\u001d\u0006\u0003;y\t\u0011A\u001d\u0006\u0003?\u0001\n!!\u001c7\u000b\u0005\u0005\u0012\u0013!B:qCJ\\'BA\u0012%\u0003\u0019\t\u0007/Y2iK*\tQ%A\u0002pe\u001e\u001c2\u0001A\u0014.!\tA3&D\u0001*\u0015\u0005Q\u0013!B:dC2\f\u0017B\u0001\u0017*\u0005\u0019\te.\u001f*fMB\u0011a&M\u0007\u0002_)\u0011\u0001GH\u0001\u0005kRLG.\u0003\u00023_\tQQ\nT,sSR\f'\r\\3\u0002\u0011AL\u0007/\u001a7j]\u0016\u001c\u0001!F\u00017!\t9\u0004(D\u0001\u001f\u0013\tIdDA\u0007QSB,G.\u001b8f\u001b>$W\r\\\u0001\na&\u0004X\r\\5oK\u0002\na\u0001P5oSRtDCA\u001f@!\tq\u0004!D\u0001\u001d\u0011\u0015\u00194\u00011\u00017\u0003!iG\u000e]'pI\u0016dW#\u0001\"\u0011\u0005\r3U\"\u0001#\u000b\u0005\u0015s\u0012AD2mCN\u001c\u0018NZ5dCRLwN\\\u0005\u0003\u000f\u0012\u0013q%T;mi&d\u0017-_3s!\u0016\u00148-\u001a9ue>t7\t\\1tg&4\u0017nY1uS>tWj\u001c3fY\u0006IQ\u000e\u001c9N_\u0012,G\u000eI\u0001\bo\u0016Lw\r\u001b;t+\u0005Y\u0005c\u0001\u0015M\u001d&\u0011Q*\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003Q=K!\u0001U\u0015\u0003\r\u0011{WO\u00197f\u0003\u0019a\u0017-_3sgV\t1\u000bE\u0002)\u0019R\u0003\"\u0001K+\n\u0005YK#aA%oi\u0006IAO]1og\u001a|'/\u001c\u000b\u000336\u0004\"A\u00176\u000f\u0005m;gB\u0001/f\u001d\tiFM\u0004\u0002_G:\u0011qLY\u0007\u0002A*\u0011\u0011\rN\u0001\u0007yI|w\u000e\u001e \n\u0003\u0015J!a\t\u0013\n\u0005\u0005\u0012\u0013B\u00014!\u0003\r\u0019\u0018\u000f\\\u0005\u0003Q&\fq\u0001]1dW\u0006<WM\u0003\u0002gA%\u00111\u000e\u001c\u0002\n\t\u0006$\u0018M\u0012:b[\u0016T!\u0001[5\t\u000b9D\u0001\u0019A8\u0002\u000f\u0011\fG/Y:fiB\u0012\u0001O\u001e\t\u0004cJ$X\"A5\n\u0005ML'a\u0002#bi\u0006\u001cX\r\u001e\t\u0003kZd\u0001\u0001B\u0005x[\u0006\u0005\t\u0011!B\u0001q\n\u0019q\fJ\u0019\u0012\u0005ed\bC\u0001\u0015{\u0013\tY\u0018FA\u0004O_RD\u0017N\\4\u0011\u0005!j\u0018B\u0001@*\u0005\r\te._\u0001\u0006oJLG/Z\u000b\u0003\u0003\u0007\u00012ALA\u0003\u0013\r\t9a\f\u0002\t\u001b2;&/\u001b;fe\u0006)S*\u001e7uS2\f\u00170\u001a:QKJ\u001cW\r\u001d;s_:\u001cE.Y:tS\u001aLWM],sCB\u0004XM\u001d\t\u0003}-\u0019BaC\u0014\u0002\u0010A!a&!\u0005>\u0013\r\t\u0019b\f\u0002\u000b\u001b2\u0013V-\u00193bE2,GCAA\u0006\u0003e\u0001&+\u0012#J\u0007R+Ei\u0018'B\u0005\u0016cu,\u0013(E\u000bb{6i\u0014'\u0016\u0005\u0005m\u0001\u0003BA\u000f\u0003Oi!!a\b\u000b\t\u0005\u0005\u00121E\u0001\u0005Y\u0006twM\u0003\u0002\u0002&\u0005!!.\u0019<b\u0013\u0011\tI#a\b\u0003\rM#(/\u001b8h\u0003i\u0001&+\u0012#J\u0007R+Ei\u0018'B\u0005\u0016cu,\u0013(E\u000bb{6i\u0014'!\u0003M\u0001&+\u0012#J\u0007R+Ei\u0018'B\u0005\u0016culQ(M\u0003Q\u0001&+\u0012#J\u0007R+Ei\u0018'B\u0005\u0016culQ(MA\u0005\u0019a-\u001b;\u0015/u\n)$!\u000f\u0002L\u0005=\u0013\u0011KA+\u00033\ni&!\u0019\u0002f\u0005%\u0004BBA\u001c#\u0001\u0007\u0011,\u0001\u0003eCR\f\u0007bBA\u001e#\u0001\u0007\u0011QH\u0001\bM>\u0014X.\u001e7b!\u0011\ty$a\u0012\u000f\t\u0005\u0005\u00131\t\t\u0003?&J1!!\u0012*\u0003\u0019\u0001&/\u001a3fM&!\u0011\u0011FA%\u0015\r\t)%\u000b\u0005\u0007\u0003\u001b\n\u0002\u0019\u0001+\u0002\u0013\tdwnY6TSj,\u0007\"B)\u0012\u0001\u0004\u0019\u0006bBA*#\u0001\u0007\u0011QH\u0001\u0007g>dg/\u001a:\t\r\u0005]\u0013\u00031\u0001U\u0003\u001di\u0017\r_%uKJDa!a\u0017\u0012\u0001\u0004q\u0015a\u0001;pY\"1\u0011qL\tA\u00029\u000b\u0001b\u001d;faNK'0\u001a\u0005\b\u0003G\n\u0002\u0019AA\u001f\u0003\u0011\u0019X-\u001a3\t\r\u0005\u001d\u0014\u00031\u0001L\u00039Ig.\u001b;jC2<V-[4iiNDq!a\u001b\u0012\u0001\u0004\ti$A\u0007iC:$G.Z%om\u0006d\u0017\u000eZ\u0001\u0005e\u0016\fG-\u0006\u0002\u0002rA!a&a\u001d>\u0013\r\t)h\f\u0002\t\u001b2\u0013V-\u00193fe\u0006!An\\1e)\ri\u00141\u0010\u0005\b\u0003{\u001a\u0002\u0019AA\u001f\u0003\u0011\u0001\u0018\r\u001e5\u0003W5+H\u000e^5mCf,'\u000fU3sG\u0016\u0004HO]8o\u00072\f7o]5gS\u0016\u0014xK]1qa\u0016\u0014(+Z1eKJ\u001c2\u0001FA9)\t\t)\tE\u0002\u0002\bRi\u0011a\u0003\u000b\u0004{\u0005-\u0005bBA?-\u0001\u0007\u0011Q\b\u0002,\u001bVdG/\u001b7bs\u0016\u0014\b+\u001a:dKB$(o\u001c8DY\u0006\u001c8/\u001b4jKJ<&/\u00199qKJ<&/\u001b;feN\u0019q#a\u0001\u0002\u0011%t7\u000f^1oG\u0016$B!!&\u0002\u0018B\u0019\u0011qQ\f\t\r\u0005E\u0015\u00041\u0001>\u0003!\u0019\u0018M^3J[BdG\u0003BAO\u0003G\u00032\u0001KAP\u0013\r\t\t+\u000b\u0002\u0005+:LG\u000fC\u0004\u0002~i\u0001\r!!\u0010"
)
public class MultilayerPerceptronClassifierWrapper implements MLWritable {
   private double[] weights;
   private int[] layers;
   private final PipelineModel pipeline;
   private final MultilayerPerceptronClassificationModel mlpModel;
   private volatile byte bitmap$0;

   public static MultilayerPerceptronClassifierWrapper load(final String path) {
      return MultilayerPerceptronClassifierWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MultilayerPerceptronClassifierWrapper$.MODULE$.read();
   }

   public static MultilayerPerceptronClassifierWrapper fit(final Dataset data, final String formula, final int blockSize, final int[] layers, final String solver, final int maxIter, final double tol, final double stepSize, final String seed, final double[] initialWeights, final String handleInvalid) {
      return MultilayerPerceptronClassifierWrapper$.MODULE$.fit(data, formula, blockSize, layers, solver, maxIter, tol, stepSize, seed, initialWeights, handleInvalid);
   }

   public static String PREDICTED_LABEL_COL() {
      return MultilayerPerceptronClassifierWrapper$.MODULE$.PREDICTED_LABEL_COL();
   }

   public static String PREDICTED_LABEL_INDEX_COL() {
      return MultilayerPerceptronClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public PipelineModel pipeline() {
      return this.pipeline;
   }

   private MultilayerPerceptronClassificationModel mlpModel() {
      return this.mlpModel;
   }

   private double[] weights$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.weights = this.mlpModel().weights().toArray();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.weights;
   }

   public double[] weights() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.weights$lzycompute() : this.weights;
   }

   private int[] layers$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.layers = this.mlpModel().getLayers();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.layers;
   }

   public int[] layers() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.layers$lzycompute() : this.layers;
   }

   public Dataset transform(final Dataset dataset) {
      return this.pipeline().transform(dataset).drop(this.mlpModel().getFeaturesCol()).drop(this.mlpModel().getLabelCol()).drop(MultilayerPerceptronClassifierWrapper$.MODULE$.PREDICTED_LABEL_INDEX_COL());
   }

   public MLWriter write() {
      return new MultilayerPerceptronClassifierWrapperWriter(this);
   }

   public MultilayerPerceptronClassifierWrapper(final PipelineModel pipeline) {
      this.pipeline = pipeline;
      MLWritable.$init$(this);
      this.mlpModel = (MultilayerPerceptronClassificationModel)pipeline.stages()[1];
   }

   public static class MultilayerPerceptronClassifierWrapperReader extends MLReader {
      public MultilayerPerceptronClassifierWrapper load(final String path) {
         DefaultFormats format = .MODULE$;
         String pipelinePath = (new Path(path, "pipeline")).toString();
         PipelineModel pipeline = PipelineModel$.MODULE$.load(pipelinePath);
         return new MultilayerPerceptronClassifierWrapper(pipeline);
      }
   }

   public static class MultilayerPerceptronClassifierWrapperWriter extends MLWriter {
      private final MultilayerPerceptronClassifierWrapper instance;

      public void saveImpl(final String path) {
         String rMetadataPath = (new Path(path, "rMetadata")).toString();
         String pipelinePath = (new Path(path, "pipeline")).toString();
         Tuple2 rMetadata = scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.instance.getClass().getName());
         String rMetadataJson = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonDSL..MODULE$.pair2jvalue(rMetadata, (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(rMetadataJson), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MultilayerPerceptronClassifierWrapperWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(rMetadataPath);
         this.instance.pipeline().save(pipelinePath);
      }

      public MultilayerPerceptronClassifierWrapperWriter(final MultilayerPerceptronClassifierWrapper instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
