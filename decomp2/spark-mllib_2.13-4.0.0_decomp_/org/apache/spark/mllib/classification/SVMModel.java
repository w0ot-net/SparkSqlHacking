package org.apache.spark.mllib.classification;

import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.classification.impl.GLMClassificationModel;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.pmml.PMMLExportable;
import org.apache.spark.mllib.regression.GeneralizedLinearModel;
import org.apache.spark.mllib.util.Saveable;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-e\u0001\u0002\u000b\u0016\u0001\u0001B\u0001\"\u0012\u0001\u0003\u0006\u0004%\tE\u0012\u0005\n-\u0002\u0011\t\u0011)A\u0005\u000f^C\u0001\"\u0017\u0001\u0003\u0006\u0004%\tE\u0017\u0005\nE\u0002\u0011\t\u0011)A\u00057\u000eDQ!\u001a\u0001\u0005\u0002\u0019Dqa\u001c\u0001A\u0002\u0013%\u0001\u000fC\u0004u\u0001\u0001\u0007I\u0011B;\t\rm\u0004\u0001\u0015)\u0003r\u0011\u0015a\b\u0001\"\u0001~\u0011\u0019\t\u0019\u0001\u0001C\u0001a\"9\u00111\u0002\u0001\u0005\u0002\u00055\u0001bBA\t\u0001\u0011E\u00131\u0003\u0005\b\u0003?\u0001A\u0011IA\u0011\u0011\u001d\t)\u0005\u0001C!\u0003\u000f:q!a\u0013\u0016\u0011\u0003\tiE\u0002\u0004\u0015+!\u0005\u0011q\n\u0005\u0007KB!\t!a\u001b\t\u000f\u00055\u0004\u0003\"\u0011\u0002p!I\u0011q\u000f\t\u0002\u0002\u0013%\u0011\u0011\u0010\u0002\t'ZkUj\u001c3fY*\u0011acF\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\tA\u0012$A\u0003nY2L'M\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h\u0007\u0001\u0019b\u0001A\u0011(Wez\u0004C\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\u0018\u0003)\u0011Xm\u001a:fgNLwN\\\u0005\u0003M\r\u0012acR3oKJ\fG.\u001b>fI2Kg.Z1s\u001b>$W\r\u001c\t\u0003Q%j\u0011!F\u0005\u0003UU\u00111c\u00117bgNLg-[2bi&|g.T8eK2\u0004\"\u0001\f\u001c\u000f\u00055\u001adB\u0001\u00182\u001b\u0005y#B\u0001\u0019 \u0003\u0019a$o\\8u}%\t!'A\u0003tG\u0006d\u0017-\u0003\u00025k\u00059\u0001/Y2lC\u001e,'\"\u0001\u001a\n\u0005]B$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001b6!\tQT(D\u0001<\u0015\tat#\u0001\u0003vi&d\u0017B\u0001 <\u0005!\u0019\u0016M^3bE2,\u0007C\u0001!D\u001b\u0005\t%B\u0001\"\u0018\u0003\u0011\u0001X.\u001c7\n\u0005\u0011\u000b%A\u0004)N\u001b2+\u0005\u0010]8si\u0006\u0014G.Z\u0001\bo\u0016Lw\r\u001b;t+\u00059\u0005C\u0001%L\u001b\u0005I%B\u0001&\u0018\u0003\u0019a\u0017N\\1mO&\u0011A*\u0013\u0002\u0007-\u0016\u001cGo\u001c:)\u0007\u0005qE\u000b\u0005\u0002P%6\t\u0001K\u0003\u0002R3\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005M\u0003&!B*j]\u000e,\u0017%A+\u0002\u000bEr\u0003G\f\u0019\u0002\u0011],\u0017n\u001a5ug\u0002J!!R\u0013)\u0007\tqE+A\u0005j]R,'oY3qiV\t1\f\u0005\u0002];6\tQ'\u0003\u0002_k\t1Ai\\;cY\u0016D3a\u0001(aC\u0005\t\u0017!\u0002\u0019/q9\u0002\u0014AC5oi\u0016\u00148-\u001a9uA%\u0011\u0011,\n\u0015\u0004\t9\u0003\u0017A\u0002\u001fj]&$h\bF\u0002hQ*\u0004\"\u0001\u000b\u0001\t\u000b\u0015+\u0001\u0019A$)\u0007!tE\u000bC\u0003Z\u000b\u0001\u00071\fK\u0002k\u001d\u0002D3!\u0002(nC\u0005q\u0017!B\u0019/c9\u0002\u0014!\u0003;ie\u0016\u001c\bn\u001c7e+\u0005\t\bc\u0001/s7&\u00111/\u000e\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u001bQD'/Z:i_2$w\fJ3r)\t1\u0018\u0010\u0005\u0002]o&\u0011\u00010\u000e\u0002\u0005+:LG\u000fC\u0004{\u000f\u0005\u0005\t\u0019A9\u0002\u0007a$\u0013'\u0001\u0006uQJ,7\u000f[8mI\u0002\nAb]3u)\"\u0014Xm\u001d5pY\u0012$\"A`@\u000e\u0003\u0001AQa\\\u0005A\u0002mC3!\u0003(U\u000319W\r\u001e+ie\u0016\u001c\bn\u001c7eQ\u0011Qa*a\u0002\"\u0005\u0005%\u0011!B\u0019/g9\u0002\u0014AD2mK\u0006\u0014H\u000b\u001b:fg\"|G\u000e\u001a\u000b\u0002}\"\u001a1B\u0014+\u0002\u0019A\u0014X\rZ5diB{\u0017N\u001c;\u0015\u000fm\u000b)\"!\u0007\u0002\u001e!1\u0011q\u0003\u0007A\u0002\u001d\u000b!\u0002Z1uC6\u000bGO]5y\u0011\u0019\tY\u0002\u0004a\u0001\u000f\u0006aq/Z5hQRl\u0015\r\u001e:jq\")\u0011\f\u0004a\u00017\u0006!1/\u0019<f)\u00151\u00181EA\u0018\u0011\u001d\t)#\u0004a\u0001\u0003O\t!a]2\u0011\t\u0005%\u00121F\u0007\u00023%\u0019\u0011QF\r\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\u000f\u0005ER\u00021\u0001\u00024\u0005!\u0001/\u0019;i!\u0011\t)$!\u0010\u000f\t\u0005]\u0012\u0011\b\t\u0003]UJ1!a\u000f6\u0003\u0019\u0001&/\u001a3fM&!\u0011qHA!\u0005\u0019\u0019FO]5oO*\u0019\u00111H\u001b)\t5q\u0015qA\u0001\ti>\u001cFO]5oOR\u0011\u00111\u0007\u0015\u0004\u00019\u0003\u0017\u0001C*W\u001b6{G-\u001a7\u0011\u0005!\u00022c\u0002\t\u0002R\u0005]\u0013Q\f\t\u00049\u0006M\u0013bAA+k\t1\u0011I\\=SK\u001a\u0004BAOA-O&\u0019\u00111L\u001e\u0003\r1{\u0017\rZ3s!\u0011\ty&!\u001b\u000e\u0005\u0005\u0005$\u0002BA2\u0003K\n!![8\u000b\u0005\u0005\u001d\u0014\u0001\u00026bm\u0006L1aNA1)\t\ti%\u0001\u0003m_\u0006$G#B4\u0002r\u0005M\u0004bBA\u0013%\u0001\u0007\u0011q\u0005\u0005\b\u0003c\u0011\u0002\u0019AA\u001aQ\u0011\u0011b*a\u0002\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005m\u0004\u0003BA?\u0003\u0007k!!a \u000b\t\u0005\u0005\u0015QM\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u0006\u0006}$AB(cU\u0016\u001cG\u000f\u000b\u0003\u0011\u001d\u0006\u001d\u0001\u0006B\bO\u0003\u000f\u0001"
)
public class SVMModel extends GeneralizedLinearModel implements ClassificationModel, Saveable, PMMLExportable {
   private Option threshold;

   public static SVMModel load(final SparkContext sc, final String path) {
      return SVMModel$.MODULE$.load(sc, path);
   }

   public void toPMML(final String localPath) {
      PMMLExportable.toPMML$(this, (String)localPath);
   }

   public void toPMML(final SparkContext sc, final String path) {
      PMMLExportable.toPMML$(this, sc, path);
   }

   public void toPMML(final OutputStream outputStream) {
      PMMLExportable.toPMML$(this, (OutputStream)outputStream);
   }

   public String toPMML() {
      return PMMLExportable.toPMML$(this);
   }

   public JavaRDD predict(final JavaRDD testData) {
      return ClassificationModel.predict$(this, testData);
   }

   public Vector weights() {
      return super.weights();
   }

   public double intercept() {
      return super.intercept();
   }

   private Option threshold() {
      return this.threshold;
   }

   private void threshold_$eq(final Option x$1) {
      this.threshold = x$1;
   }

   public SVMModel setThreshold(final double threshold) {
      this.threshold_$eq(new Some(BoxesRunTime.boxToDouble(threshold)));
      return this;
   }

   public Option getThreshold() {
      return this.threshold();
   }

   public SVMModel clearThreshold() {
      this.threshold_$eq(.MODULE$);
      return this;
   }

   public double predictPoint(final Vector dataMatrix, final Vector weightMatrix, final double intercept) {
      double margin = BLAS$.MODULE$.dot(weightMatrix, dataMatrix) + intercept;
      Option var9 = this.threshold();
      if (var9 instanceof Some var10) {
         double t = BoxesRunTime.unboxToDouble(var10.value());
         return margin > t ? (double)1.0F : (double)0.0F;
      } else if (.MODULE$.equals(var9)) {
         return margin;
      } else {
         throw new MatchError(var9);
      }
   }

   public void save(final SparkContext sc, final String path) {
      GLMClassificationModel.SaveLoadV1_0$.MODULE$.save(sc, path, this.getClass().getName(), this.weights().size(), 2, this.weights(), this.intercept(), this.threshold());
   }

   public String toString() {
      String var10000 = super.toString();
      return var10000 + ", numClasses = 2, threshold = " + this.threshold().getOrElse(() -> "None");
   }

   public SVMModel(final Vector weights, final double intercept) {
      super(weights, intercept);
      ClassificationModel.$init$(this);
      PMMLExportable.$init$(this);
      this.threshold = new Some(BoxesRunTime.boxToDouble((double)0.0F));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
