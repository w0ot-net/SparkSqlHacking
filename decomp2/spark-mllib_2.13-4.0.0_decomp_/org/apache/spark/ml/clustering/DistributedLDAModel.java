package org.apache.spark.ml.clustering;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.SparkSession;
import scala.Option;
import scala.Some;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}h\u0001\u0002\u0015*\u0001QB\u0011\"\u000f\u0001\u0003\u0002\u0003\u0006IAO$\t\u0013!\u0003!\u0011!Q\u0001\n%k\u0005\u0002\u0003(\u0001\u0005\u000b\u0007I\u0011B(\t\u0011Y\u0003!\u0011!Q\u0001\nAC\u0011b\u0016\u0001\u0003\u0002\u0003\u0006I\u0001\u00170\t\u0011}\u0003!\u00111A\u0005\n\u0001D\u0001b\u001a\u0001\u0003\u0002\u0004%I\u0001\u001b\u0005\t]\u0002\u0011\t\u0011)Q\u0005C\"1q\u000e\u0001C\u0001WADaa\u001c\u0001\u0005\u0002-:\bB\u0002=\u0001\t\u0003J\u0013\u0010\u0003\u0004{\u0001\u0011\u0005\u0013f\u001f\u0005\u0006}\u0002!\ta \u0005\b\u0003/\u0001A\u0011IA\r\u0011\u001d\ti\u0003\u0001C!\u0003_A!\"!\u000f\u0001\u0011\u000b\u0007I\u0011AA\u001e\u0011)\t)\u0005\u0001EC\u0002\u0013\u0005\u00111\b\u0005\n\u0003\u0013\u0002\u0001\u0019!C\u0005\u0003\u0017B\u0011\"a\u0015\u0001\u0001\u0004%I!!\u0016\t\u0011\u0005e\u0003\u0001)Q\u0005\u0003\u001bBq!a\u0017\u0001\t\u0003\tY\u0005C\u0004\u0002d\u0001!\t!!\u001a\t\u000f\u0005%\u0004\u0001\"\u0011\u0002l!9\u00111\u0010\u0001\u0005B\u0005utaBADS!\u0005\u0011\u0011\u0012\u0004\u0007Q%B\t!a#\t\r=TB\u0011AAU\r\u001d\tYK\u0007\u0001\u001b\u0003[C\u0011\"a,\u001d\u0005\u0003\u0005\u000b\u0011B9\t\r=dB\u0011AAY\u0011\u001d\tI\f\bC)\u0003w3a!!1\u001b\t\u0005\r\u0007BB8!\t\u0003\tY\rC\u0005\u0002P\u0002\u0012\r\u0011\"\u0003\u0002R\"A\u0011Q\u001c\u0011!\u0002\u0013\t\u0019\u000eC\u0004\u0002`\u0002\"\t%!9\t\u000f\u0005\u0015(\u0004\"\u0011\u0002h\"9\u0011q\u001c\u000e\u0005B\u0005-\b\"CAy5\u0005\u0005I\u0011BAz\u0005M!\u0015n\u001d;sS\n,H/\u001a3M\t\u0006ku\u000eZ3m\u0015\tQ3&\u0001\u0006dYV\u001cH/\u001a:j]\u001eT!\u0001L\u0017\u0002\u00055d'B\u0001\u00180\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0014'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002e\u0005\u0019qN]4\u0004\u0001M\u0011\u0001!\u000e\t\u0003m]j\u0011!K\u0005\u0003q%\u0012\u0001\u0002\u0014#B\u001b>$W\r\\\u0001\u0004k&$\u0007CA\u001eE\u001d\ta$\t\u0005\u0002>\u00016\taH\u0003\u0002@g\u00051AH]8pizR\u0011!Q\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0007\u0002\u000ba\u0001\u0015:fI\u00164\u0017BA#G\u0005\u0019\u0019FO]5oO*\u00111\tQ\u0005\u0003s]\n\u0011B^8dC\n\u001c\u0016N_3\u0011\u0005)[U\"\u0001!\n\u00051\u0003%aA%oi&\u0011\u0001jN\u0001\u0014_2$G)[:ue&\u0014W\u000f^3e\u001b>$W\r\\\u000b\u0002!B\u0011\u0011+V\u0007\u0002%*\u0011!f\u0015\u0006\u0003)6\nQ!\u001c7mS\nL!\u0001\u000b*\u0002)=dG\rR5tiJL'-\u001e;fI6{G-\u001a7!\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o!\tIF,D\u0001[\u0015\tYV&A\u0002tc2L!!\u0018.\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\n\u0005];\u0014aE8mI2{7-\u00197N_\u0012,Gn\u00149uS>tW#A1\u0011\u0007)\u0013G-\u0003\u0002d\u0001\n1q\n\u001d;j_:\u0004\"!U3\n\u0005\u0019\u0014&!\u0004'pG\u0006dG\nR!N_\u0012,G.A\fpY\u0012dunY1m\u001b>$W\r\\(qi&|gn\u0018\u0013fcR\u0011\u0011\u000e\u001c\t\u0003\u0015*L!a\u001b!\u0003\tUs\u0017\u000e\u001e\u0005\b[\u001e\t\t\u00111\u0001b\u0003\rAH%M\u0001\u0015_2$Gj\\2bY6{G-\u001a7PaRLwN\u001c\u0011\u0002\rqJg.\u001b;?)\u0019\t(o\u001d;vmB\u0011a\u0007\u0001\u0005\u0006s%\u0001\rA\u000f\u0005\u0006\u0011&\u0001\r!\u0013\u0005\u0006\u001d&\u0001\r\u0001\u0015\u0005\u0006/&\u0001\r\u0001\u0017\u0005\u0006?&\u0001\r!\u0019\u000b\u0002c\u0006iq\u000e\u001c3M_\u000e\fG.T8eK2,\u0012\u0001Z\u0001\tO\u0016$Xj\u001c3fYV\tA\u0010\u0005\u0002R{&\u0011\u0001HU\u0001\bi>dunY1m+\t\t\t\u0001E\u00027\u0003\u0007I!AZ\u0015)\u000b5\t9!a\u0005\u0011\t\u0005%\u0011qB\u0007\u0003\u0003\u0017Q1!!\u0004.\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003#\tYAA\u0003TS:\u001cW-\t\u0002\u0002\u0016\u0005)\u0011G\f\u001c/a\u0005!1m\u001c9z)\r\t\u00181\u0004\u0005\b\u0003;q\u0001\u0019AA\u0010\u0003\u0015)\u0007\u0010\u001e:b!\u0011\t\t#a\n\u000e\u0005\u0005\r\"bAA\u0013W\u0005)\u0001/\u0019:b[&!\u0011\u0011FA\u0012\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\b&\u0002\b\u0002\b\u0005M\u0011!D5t\t&\u001cHO]5ckR,G-\u0006\u0002\u00022A\u0019!*a\r\n\u0007\u0005U\u0002IA\u0004C_>dW-\u00198)\u000b=\t9!a\u0005\u0002+Q\u0014\u0018-\u001b8j]\u001edun\u001a'jW\u0016d\u0017\u000e[8pIV\u0011\u0011Q\b\t\u0004\u0015\u0006}\u0012bAA!\u0001\n1Ai\\;cY\u0016DS\u0001EA\u0004\u0003'\t\u0001\u0002\\8h!JLwN\u001d\u0015\u0006#\u0005\u001d\u00111C\u0001\u0011?\u000eDWmY6q_&tGOR5mKN,\"!!\u0014\u0011\t)\u000byEO\u0005\u0004\u0003#\u0002%!B!se\u0006L\u0018\u0001F0dQ\u0016\u001c7\u000e]8j]R4\u0015\u000e\\3t?\u0012*\u0017\u000fF\u0002j\u0003/B\u0001\"\\\n\u0002\u0002\u0003\u0007\u0011QJ\u0001\u0012?\u000eDWmY6q_&tGOR5mKN\u0004\u0013AE4fi\u000eCWmY6q_&tGOR5mKNDS!FA\u0004\u0003?\n#!!\u0019\u0002\u000bIr\u0003G\f\u0019\u0002+\u0011,G.\u001a;f\u0007\",7m\u001b9pS:$h)\u001b7fgR\t\u0011\u000eK\u0003\u0017\u0003\u000f\ty&A\u0003xe&$X-\u0006\u0002\u0002nA!\u0011qNA;\u001b\t\t\tHC\u0002\u0002t-\nA!\u001e;jY&!\u0011qOA9\u0005!iEj\u0016:ji\u0016\u0014\b&B\f\u0002\b\u0005M\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003iBS\u0001GA\u0004\u0003\u0003\u000b#!a!\u0002\u000bMr\u0003G\f\u0019)\u000b\u0001\t9!a\u0005\u0002'\u0011K7\u000f\u001e:jEV$X\r\u001a'E\u00036{G-\u001a7\u0011\u0005YR2c\u0002\u000e\u0002\u000e\u0006M\u0015\u0011\u0014\t\u0004\u0015\u0006=\u0015bAAI\u0001\n1\u0011I\\=SK\u001a\u0004R!a\u001c\u0002\u0016FLA!a&\u0002r\tQQ\n\u0014*fC\u0012\f'\r\\3\u0011\t\u0005m\u0015QU\u0007\u0003\u0003;SA!a(\u0002\"\u0006\u0011\u0011n\u001c\u0006\u0003\u0003G\u000bAA[1wC&!\u0011qUAO\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\tIIA\tESN$(/\u001b2vi\u0016$wK]5uKJ\u001c2\u0001HA7\u0003!Ign\u001d;b]\u000e,G\u0003BAZ\u0003o\u00032!!.\u001d\u001b\u0005Q\u0002BBAX=\u0001\u0007\u0011/\u0001\u0005tCZ,\u0017*\u001c9m)\rI\u0017Q\u0018\u0005\u0007\u0003\u007f{\u0002\u0019\u0001\u001e\u0002\tA\fG\u000f\u001b\u0002\u001a\t&\u001cHO]5ckR,G\r\u0014#B\u001b>$W\r\u001c*fC\u0012,'oE\u0002!\u0003\u000b\u0004R!a\u001c\u0002HFLA!!3\u0002r\tAQ\n\u0014*fC\u0012,'\u000f\u0006\u0002\u0002NB\u0019\u0011Q\u0017\u0011\u0002\u0013\rd\u0017m]:OC6,WCAAj!\u0011\t).a7\u000e\u0005\u0005]'\u0002BAm\u0003C\u000bA\u0001\\1oO&\u0019Q)a6\u0002\u0015\rd\u0017m]:OC6,\u0007%\u0001\u0003m_\u0006$GcA9\u0002d\"1\u0011q\u0018\u0013A\u0002i\nAA]3bIV\u0011\u0011Q\u0019\u0015\u0006K\u0005\u001d\u00111\u0003\u000b\u0004c\u00065\bBBA`M\u0001\u0007!\bK\u0003'\u0003\u000f\t\u0019\"\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002vB!\u0011Q[A|\u0013\u0011\tI0a6\u0003\r=\u0013'.Z2uQ\u0015Q\u0012qAA\nQ\u0015I\u0012qAA\n\u0001"
)
public class DistributedLDAModel extends LDAModel {
   private double trainingLogLikelihood;
   private double logPrior;
   private final org.apache.spark.mllib.clustering.DistributedLDAModel org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel;
   private Option oldLocalModelOption;
   private String[] _checkpointFiles;
   private volatile byte bitmap$0;

   public static DistributedLDAModel load(final String path) {
      return DistributedLDAModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return DistributedLDAModel$.MODULE$.read();
   }

   public org.apache.spark.mllib.clustering.DistributedLDAModel org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel() {
      return this.org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel;
   }

   private Option oldLocalModelOption() {
      return this.oldLocalModelOption;
   }

   private void oldLocalModelOption_$eq(final Option x$1) {
      this.oldLocalModelOption = x$1;
   }

   public org.apache.spark.mllib.clustering.LocalLDAModel oldLocalModel() {
      if (this.oldLocalModelOption().isEmpty()) {
         this.oldLocalModelOption_$eq(new Some(this.org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel().toLocal()));
      }

      return (org.apache.spark.mllib.clustering.LocalLDAModel)this.oldLocalModelOption().get();
   }

   public org.apache.spark.mllib.clustering.LDAModel getModel() {
      return this.org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel();
   }

   public LocalLDAModel toLocal() {
      return new LocalLDAModel(super.uid(), super.vocabSize(), this.oldLocalModel(), super.sparkSession());
   }

   public DistributedLDAModel copy(final ParamMap extra) {
      DistributedLDAModel copied = new DistributedLDAModel(super.uid(), super.vocabSize(), this.org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel(), super.sparkSession(), this.oldLocalModelOption());
      ((Model)this.copyValues(copied, extra)).setParent(this.parent());
      return copied;
   }

   public boolean isDistributed() {
      return true;
   }

   private double trainingLogLikelihood$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.trainingLogLikelihood = this.org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel().logLikelihood();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.trainingLogLikelihood;
   }

   public double trainingLogLikelihood() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.trainingLogLikelihood$lzycompute() : this.trainingLogLikelihood;
   }

   private double logPrior$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.logPrior = this.org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel().logPrior();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.logPrior;
   }

   public double logPrior() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.logPrior$lzycompute() : this.logPrior;
   }

   private String[] _checkpointFiles() {
      return this._checkpointFiles;
   }

   private void _checkpointFiles_$eq(final String[] x$1) {
      this._checkpointFiles = x$1;
   }

   public String[] getCheckpointFiles() {
      return this._checkpointFiles();
   }

   public void deleteCheckpointFiles() {
      Configuration hadoopConf = super.sparkSession().sparkContext().hadoopConfiguration();
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this._checkpointFiles()), (x$6) -> {
         $anonfun$deleteCheckpointFiles$1(hadoopConf, x$6);
         return BoxedUnit.UNIT;
      });
      this._checkpointFiles_$eq((String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   public MLWriter write() {
      return new DistributedWriter(this);
   }

   public String toString() {
      String var10000 = super.uid();
      return "DistributedLDAModel: uid=" + var10000 + ", k=" + this.$(this.k()) + ", numFeatures=" + super.vocabSize();
   }

   // $FF: synthetic method
   public static final void $anonfun$deleteCheckpointFiles$1(final Configuration hadoopConf$1, final String x$6) {
      org.apache.spark.util.PeriodicCheckpointer..MODULE$.removeCheckpointFile(x$6, hadoopConf$1);
   }

   public DistributedLDAModel(final String uid, final int vocabSize, final org.apache.spark.mllib.clustering.DistributedLDAModel oldDistributedModel, final SparkSession sparkSession, final Option oldLocalModelOption) {
      this.org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel = oldDistributedModel;
      this.oldLocalModelOption = oldLocalModelOption;
      super(uid, vocabSize, sparkSession);
      this._checkpointFiles = oldDistributedModel.checkpointFiles();
   }

   public DistributedLDAModel() {
      this("", -1, (org.apache.spark.mllib.clustering.DistributedLDAModel)null, (SparkSession)null, scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class DistributedWriter extends MLWriter {
      private final DistributedLDAModel instance;

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         String modelPath = (new Path(path, "oldModel")).toString();
         this.instance.org$apache$spark$ml$clustering$DistributedLDAModel$$oldDistributedModel().save(this.sc(), modelPath);
      }

      public DistributedWriter(final DistributedLDAModel instance) {
         this.instance = instance;
      }
   }

   private static class DistributedLDAModelReader extends MLReader {
      private final String className = DistributedLDAModel.class.getName();

      private String className() {
         return this.className;
      }

      public DistributedLDAModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String modelPath = (new Path(path, "oldModel")).toString();
         org.apache.spark.mllib.clustering.DistributedLDAModel oldModel = org.apache.spark.mllib.clustering.DistributedLDAModel$.MODULE$.load(this.sc(), modelPath);
         DistributedLDAModel model = new DistributedLDAModel(metadata.uid(), oldModel.vocabSize(), oldModel, this.sparkSession(), scala.None..MODULE$);
         LDAParams$.MODULE$.getAndSetParams(model, metadata);
         return model;
      }

      public DistributedLDAModelReader() {
      }
   }
}
