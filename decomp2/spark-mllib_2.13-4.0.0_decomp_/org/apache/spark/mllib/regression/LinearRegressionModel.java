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
   bytes = "\u0006\u0005\u0005-c\u0001B\u0007\u000f\u0001eA\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0005\u0010\u0005\n\u0019\u0002\u0011\t\u0011)A\u0005{5C\u0001b\u0014\u0001\u0003\u0006\u0004%\t\u0005\u0015\u0005\n1\u0002\u0011\t\u0011)A\u0005#fCQa\u0017\u0001\u0005\u0002qCQ!\u001a\u0001\u0005R\u0019DQ\u0001\u001c\u0001\u0005B5<q!a\u0003\u000f\u0011\u0003\tiA\u0002\u0004\u000e\u001d!\u0005\u0011q\u0002\u0005\u00077&!\t!a\u000b\t\u000f\u00055\u0012\u0002\"\u0011\u00020!I\u0011qG\u0005\u0002\u0002\u0013%\u0011\u0011\b\u0002\u0016\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:lu\u000eZ3m\u0015\ty\u0001#\u0001\u0006sK\u001e\u0014Xm]:j_:T!!\u0005\n\u0002\u000b5dG.\u001b2\u000b\u0005M!\u0012!B:qCJ\\'BA\u000b\u0017\u0003\u0019\t\u0007/Y2iK*\tq#A\u0002pe\u001e\u001c\u0001a\u0005\u0004\u00015y\ts&\u000e\t\u00037qi\u0011AD\u0005\u0003;9\u0011acR3oKJ\fG.\u001b>fI2Kg.Z1s\u001b>$W\r\u001c\t\u00037}I!\u0001\t\b\u0003\u001fI+wM]3tg&|g.T8eK2\u0004\"A\t\u0017\u000f\u0005\rJcB\u0001\u0013(\u001b\u0005)#B\u0001\u0014\u0019\u0003\u0019a$o\\8u}%\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+W\u00059\u0001/Y2lC\u001e,'\"\u0001\u0015\n\u00055r#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0016,!\t\u00014'D\u00012\u0015\t\u0011\u0004#\u0001\u0003vi&d\u0017B\u0001\u001b2\u0005!\u0019\u0016M^3bE2,\u0007C\u0001\u001c:\u001b\u00059$B\u0001\u001d\u0011\u0003\u0011\u0001X.\u001c7\n\u0005i:$A\u0004)N\u001b2+\u0005\u0010]8si\u0006\u0014G.Z\u0001\bo\u0016Lw\r\u001b;t+\u0005i\u0004C\u0001 B\u001b\u0005y$B\u0001!\u0011\u0003\u0019a\u0017N\\1mO&\u0011!i\u0010\u0002\u0007-\u0016\u001cGo\u001c:)\u0007\u0005!%\n\u0005\u0002F\u00116\taI\u0003\u0002H%\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005%3%!B*j]\u000e,\u0017%A&\u0002\u000bEr\u0003G\f\u0019\u0002\u0011],\u0017n\u001a5ug\u0002J!a\u000f\u000f)\u0007\t!%*A\u0005j]R,'oY3qiV\t\u0011\u000b\u0005\u0002S'6\t1&\u0003\u0002UW\t1Ai\\;cY\u0016D3a\u0001#WC\u00059\u0016!\u0002\u0019/q9\u0002\u0014AC5oi\u0016\u00148-\u001a9uA%\u0011q\n\b\u0015\u0004\t\u00113\u0016A\u0002\u001fj]&$h\bF\u0002^=\u0002\u0004\"a\u0007\u0001\t\u000bm*\u0001\u0019A\u001f)\u0007y#%\nC\u0003P\u000b\u0001\u0007\u0011\u000bK\u0002a\tZC3!\u0002#dC\u0005!\u0017!B\u0019/c9\u0002\u0014\u0001\u00049sK\u0012L7\r\u001e)pS:$H\u0003B)hS.DQ\u0001\u001b\u0004A\u0002u\n!\u0002Z1uC6\u000bGO]5y\u0011\u0015Qg\u00011\u0001>\u000319X-[4ii6\u000bGO]5y\u0011\u0015ye\u00011\u0001R\u0003\u0011\u0019\u0018M^3\u0015\u00079\fx\u000f\u0005\u0002S_&\u0011\u0001o\u000b\u0002\u0005+:LG\u000fC\u0003s\u000f\u0001\u00071/\u0001\u0002tGB\u0011A/^\u0007\u0002%%\u0011aO\u0005\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0005\u0006q\u001e\u0001\r!_\u0001\u0005a\u0006$\b\u000e\u0005\u0002{}:\u00111\u0010 \t\u0003I-J!!`\u0016\u0002\rA\u0013X\rZ3g\u0013\ry\u0018\u0011\u0001\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005u\\\u0003\u0006B\u0004E\u0003\u000b\t#!a\u0002\u0002\u000bEr3G\f\u0019)\u0007\u0001!e+A\u000bMS:,\u0017M\u001d*fOJ,7o]5p]6{G-\u001a7\u0011\u0005mI1cB\u0005\u0002\u0012\u0005]\u0011Q\u0004\t\u0004%\u0006M\u0011bAA\u000bW\t1\u0011I\\=SK\u001a\u0004B\u0001MA\r;&\u0019\u00111D\u0019\u0003\r1{\u0017\rZ3s!\u0011\ty\"!\u000b\u000e\u0005\u0005\u0005\"\u0002BA\u0012\u0003K\t!![8\u000b\u0005\u0005\u001d\u0012\u0001\u00026bm\u0006L1!LA\u0011)\t\ti!\u0001\u0003m_\u0006$G#B/\u00022\u0005M\u0002\"\u0002:\f\u0001\u0004\u0019\b\"\u0002=\f\u0001\u0004I\b\u0006B\u0006E\u0003\u000b\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u000f\u0011\t\u0005u\u00121I\u0007\u0003\u0003\u007fQA!!\u0011\u0002&\u0005!A.\u00198h\u0013\u0011\t)%a\u0010\u0003\r=\u0013'.Z2uQ\u0011IA)!\u0002)\t!!\u0015Q\u0001"
)
public class LinearRegressionModel extends GeneralizedLinearModel implements RegressionModel, Saveable, PMMLExportable {
   public static LinearRegressionModel load(final SparkContext sc, final String path) {
      return LinearRegressionModel$.MODULE$.load(sc, path);
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

   public LinearRegressionModel(final Vector weights, final double intercept) {
      super(weights, intercept);
      RegressionModel.$init$(this);
      PMMLExportable.$init$(this);
   }
}
