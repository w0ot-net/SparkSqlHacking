package org.apache.spark.mllib.regression;

import java.io.OutputStream;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.pmml.PMMLExportable;
import org.apache.spark.mllib.regression.impl.GLMRegressionModel;
import org.apache.spark.mllib.util.Saveable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-c\u0001B\u0007\u000f\u0001eA\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0005\u0010\u0005\n\u0019\u0002\u0011\t\u0011)A\u0005{5C\u0001b\u0014\u0001\u0003\u0006\u0004%\t\u0005\u0015\u0005\n1\u0002\u0011\t\u0011)A\u0005#fCQa\u0017\u0001\u0005\u0002qCQ!\u001a\u0001\u0005R\u0019DQ\u0001\u001c\u0001\u0005B5<q!a\u0003\u000f\u0011\u0003\tiA\u0002\u0004\u000e\u001d!\u0005\u0011q\u0002\u0005\u00077&!\t!a\u000b\t\u000f\u00055\u0012\u0002\"\u0011\u00020!I\u0011qG\u0005\u0002\u0002\u0013%\u0011\u0011\b\u0002\u0015%&$w-\u001a*fOJ,7o]5p]6{G-\u001a7\u000b\u0005=\u0001\u0012A\u0003:fOJ,7o]5p]*\u0011\u0011CE\u0001\u0006[2d\u0017N\u0019\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sO\u000e\u00011C\u0002\u0001\u001b=\u0005zS\u0007\u0005\u0002\u001c95\ta\"\u0003\u0002\u001e\u001d\t1r)\u001a8fe\u0006d\u0017N_3e\u0019&tW-\u0019:N_\u0012,G\u000e\u0005\u0002\u001c?%\u0011\u0001E\u0004\u0002\u0010%\u0016<'/Z:tS>tWj\u001c3fYB\u0011!\u0005\f\b\u0003G%r!\u0001J\u0014\u000e\u0003\u0015R!A\n\r\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016,\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011\u0001K\u0005\u0003[9\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!AK\u0016\u0011\u0005A\u001aT\"A\u0019\u000b\u0005I\u0002\u0012\u0001B;uS2L!\u0001N\u0019\u0003\u0011M\u000bg/Z1cY\u0016\u0004\"AN\u001d\u000e\u0003]R!\u0001\u000f\t\u0002\tAlW\u000e\\\u0005\u0003u]\u0012a\u0002U'N\u0019\u0016C\bo\u001c:uC\ndW-A\u0004xK&<\u0007\u000e^:\u0016\u0003u\u0002\"AP!\u000e\u0003}R!\u0001\u0011\t\u0002\r1Lg.\u00197h\u0013\t\u0011uH\u0001\u0004WK\u000e$xN\u001d\u0015\u0004\u0003\u0011S\u0005CA#I\u001b\u00051%BA$\u0013\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0013\u001a\u0013QaU5oG\u0016\f\u0013aS\u0001\u0006c9\u0002d\u0006M\u0001\to\u0016Lw\r\u001b;tA%\u00111\b\b\u0015\u0004\u0005\u0011S\u0015!C5oi\u0016\u00148-\u001a9u+\u0005\t\u0006C\u0001*T\u001b\u0005Y\u0013B\u0001+,\u0005\u0019!u.\u001e2mK\"\u001a1\u0001\u0012,\"\u0003]\u000bQ\u0001\r\u00189]A\n!\"\u001b8uKJ\u001cW\r\u001d;!\u0013\tyE\u0004K\u0002\u0005\tZ\u000ba\u0001P5oSRtDcA/_AB\u00111\u0004\u0001\u0005\u0006w\u0015\u0001\r!\u0010\u0015\u0004=\u0012S\u0005\"B(\u0006\u0001\u0004\t\u0006f\u00011E-\"\u001aQ\u0001R2\"\u0003\u0011\fQ!\r\u00182]A\nA\u0002\u001d:fI&\u001cG\u000fU8j]R$B!U4jW\")\u0001N\u0002a\u0001{\u0005QA-\u0019;b\u001b\u0006$(/\u001b=\t\u000b)4\u0001\u0019A\u001f\u0002\u0019],\u0017n\u001a5u\u001b\u0006$(/\u001b=\t\u000b=3\u0001\u0019A)\u0002\tM\fg/\u001a\u000b\u0004]F<\bC\u0001*p\u0013\t\u00018F\u0001\u0003V]&$\b\"\u0002:\b\u0001\u0004\u0019\u0018AA:d!\t!X/D\u0001\u0013\u0013\t1(C\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000fC\u0003y\u000f\u0001\u0007\u00110\u0001\u0003qCRD\u0007C\u0001>\u007f\u001d\tYH\u0010\u0005\u0002%W%\u0011QpK\u0001\u0007!J,G-\u001a4\n\u0007}\f\tA\u0001\u0004TiJLgn\u001a\u0006\u0003{.BCa\u0002#\u0002\u0006\u0005\u0012\u0011qA\u0001\u0006c9\u001ad\u0006\r\u0015\u0004\u0001\u00113\u0016\u0001\u0006*jI\u001e,'+Z4sKN\u001c\u0018n\u001c8N_\u0012,G\u000e\u0005\u0002\u001c\u0013M9\u0011\"!\u0005\u0002\u0018\u0005u\u0001c\u0001*\u0002\u0014%\u0019\u0011QC\u0016\u0003\r\u0005s\u0017PU3g!\u0011\u0001\u0014\u0011D/\n\u0007\u0005m\u0011G\u0001\u0004M_\u0006$WM\u001d\t\u0005\u0003?\tI#\u0004\u0002\u0002\")!\u00111EA\u0013\u0003\tIwN\u0003\u0002\u0002(\u0005!!.\u0019<b\u0013\ri\u0013\u0011\u0005\u000b\u0003\u0003\u001b\tA\u0001\\8bIR)Q,!\r\u00024!)!o\u0003a\u0001g\")\u0001p\u0003a\u0001s\"\"1\u0002RA\u0003\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tY\u0004\u0005\u0003\u0002>\u0005\rSBAA \u0015\u0011\t\t%!\n\u0002\t1\fgnZ\u0005\u0005\u0003\u000b\nyD\u0001\u0004PE*,7\r\u001e\u0015\u0005\u0013\u0011\u000b)\u0001\u000b\u0003\t\t\u0006\u0015\u0001"
)
public class RidgeRegressionModel extends GeneralizedLinearModel implements RegressionModel, Saveable, PMMLExportable {
   public static RidgeRegressionModel load(final SparkContext sc, final String path) {
      return RidgeRegressionModel$.MODULE$.load(sc, path);
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
      return RegressionModel.predict$(this, testData);
   }

   public Vector weights() {
      return super.weights();
   }

   public double intercept() {
      return super.intercept();
   }

   public double predictPoint(final Vector dataMatrix, final Vector weightMatrix, final double intercept) {
      return BLAS$.MODULE$.dot(weightMatrix, dataMatrix) + intercept;
   }

   public void save(final SparkContext sc, final String path) {
      GLMRegressionModel.SaveLoadV1_0$.MODULE$.save(sc, path, this.getClass().getName(), this.weights(), this.intercept());
   }

   public RidgeRegressionModel(final Vector weights, final double intercept) {
      super(weights, intercept);
      RegressionModel.$init$(this);
      PMMLExportable.$init$(this);
   }
}
