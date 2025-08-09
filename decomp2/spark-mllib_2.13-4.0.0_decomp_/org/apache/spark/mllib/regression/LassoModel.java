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
   bytes = "\u0006\u0005\u0005-c\u0001B\u0007\u000f\u0001eA\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0005\u0010\u0005\n\u0019\u0002\u0011\t\u0011)A\u0005{5C\u0001b\u0014\u0001\u0003\u0006\u0004%\t\u0005\u0015\u0005\n1\u0002\u0011\t\u0011)A\u0005#fCQa\u0017\u0001\u0005\u0002qCQ!\u001a\u0001\u0005R\u0019DQ\u0001\u001c\u0001\u0005B5<q!a\u0003\u000f\u0011\u0003\tiA\u0002\u0004\u000e\u001d!\u0005\u0011q\u0002\u0005\u00077&!\t!a\u000b\t\u000f\u00055\u0012\u0002\"\u0011\u00020!I\u0011qG\u0005\u0002\u0002\u0013%\u0011\u0011\b\u0002\u000b\u0019\u0006\u001c8o\\'pI\u0016d'BA\b\u0011\u0003)\u0011Xm\u001a:fgNLwN\u001c\u0006\u0003#I\tQ!\u001c7mS\nT!a\u0005\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005U1\u0012AB1qC\u000eDWMC\u0001\u0018\u0003\ry'oZ\u0002\u0001'\u0019\u0001!DH\u00110kA\u00111\u0004H\u0007\u0002\u001d%\u0011QD\u0004\u0002\u0017\u000f\u0016tWM]1mSj,G\rT5oK\u0006\u0014Xj\u001c3fYB\u00111dH\u0005\u0003A9\u0011qBU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003E1r!aI\u0015\u000f\u0005\u0011:S\"A\u0013\u000b\u0005\u0019B\u0012A\u0002\u001fs_>$h(C\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQ3&A\u0004qC\u000e\\\u0017mZ3\u000b\u0003!J!!\f\u0018\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005)Z\u0003C\u0001\u00194\u001b\u0005\t$B\u0001\u001a\u0011\u0003\u0011)H/\u001b7\n\u0005Q\n$\u0001C*bm\u0016\f'\r\\3\u0011\u0005YJT\"A\u001c\u000b\u0005a\u0002\u0012\u0001\u00029n[2L!AO\u001c\u0003\u001dAkU\nT#ya>\u0014H/\u00192mK\u00069q/Z5hQR\u001cX#A\u001f\u0011\u0005y\nU\"A \u000b\u0005\u0001\u0003\u0012A\u00027j]\u0006dw-\u0003\u0002C\u007f\t1a+Z2u_JD3!\u0001#K!\t)\u0005*D\u0001G\u0015\t9%#\u0001\u0006b]:|G/\u0019;j_:L!!\u0013$\u0003\u000bMKgnY3\"\u0003-\u000bQ!\r\u00181]A\n\u0001b^3jO\"$8\u000fI\u0005\u0003wqA3A\u0001#K\u0003%Ig\u000e^3sG\u0016\u0004H/F\u0001R!\t\u00116+D\u0001,\u0013\t!6F\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0007\u00113\u0016%A,\u0002\u000bAr\u0003H\f\u0019\u0002\u0015%tG/\u001a:dKB$\b%\u0003\u0002P9!\u001aA\u0001\u0012,\u0002\rqJg.\u001b;?)\rif\f\u0019\t\u00037\u0001AQaO\u0003A\u0002uB3A\u0018#K\u0011\u0015yU\u00011\u0001RQ\r\u0001GI\u0016\u0015\u0004\u000b\u0011\u001b\u0017%\u00013\u0002\u000bEr\u0013G\f\u0019\u0002\u0019A\u0014X\rZ5diB{\u0017N\u001c;\u0015\tE;\u0017n\u001b\u0005\u0006Q\u001a\u0001\r!P\u0001\u000bI\u0006$\u0018-T1ue&D\b\"\u00026\u0007\u0001\u0004i\u0014\u0001D<fS\u001eDG/T1ue&D\b\"B(\u0007\u0001\u0004\t\u0016\u0001B:bm\u0016$2A\\9x!\t\u0011v.\u0003\u0002qW\t!QK\\5u\u0011\u0015\u0011x\u00011\u0001t\u0003\t\u00198\r\u0005\u0002uk6\t!#\u0003\u0002w%\ta1\u000b]1sW\u000e{g\u000e^3yi\")\u0001p\u0002a\u0001s\u0006!\u0001/\u0019;i!\tQhP\u0004\u0002|yB\u0011AeK\u0005\u0003{.\na\u0001\u0015:fI\u00164\u0017bA@\u0002\u0002\t11\u000b\u001e:j]\u001eT!!`\u0016)\t\u001d!\u0015QA\u0011\u0003\u0003\u000f\tQ!\r\u00184]AB3\u0001\u0001#W\u0003)a\u0015m]:p\u001b>$W\r\u001c\t\u00037%\u0019r!CA\t\u0003/\ti\u0002E\u0002S\u0003'I1!!\u0006,\u0005\u0019\te.\u001f*fMB!\u0001'!\u0007^\u0013\r\tY\"\r\u0002\u0007\u0019>\fG-\u001a:\u0011\t\u0005}\u0011\u0011F\u0007\u0003\u0003CQA!a\t\u0002&\u0005\u0011\u0011n\u001c\u0006\u0003\u0003O\tAA[1wC&\u0019Q&!\t\u0015\u0005\u00055\u0011\u0001\u00027pC\u0012$R!XA\u0019\u0003gAQA]\u0006A\u0002MDQ\u0001_\u0006A\u0002eDCa\u0003#\u0002\u0006\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\b\t\u0005\u0003{\t\u0019%\u0004\u0002\u0002@)!\u0011\u0011IA\u0013\u0003\u0011a\u0017M\\4\n\t\u0005\u0015\u0013q\b\u0002\u0007\u001f\nTWm\u0019;)\t%!\u0015Q\u0001\u0015\u0005\u0011\u0011\u000b)\u0001"
)
public class LassoModel extends GeneralizedLinearModel implements RegressionModel, Saveable, PMMLExportable {
   public static LassoModel load(final SparkContext sc, final String path) {
      return LassoModel$.MODULE$.load(sc, path);
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

   public LassoModel(final Vector weights, final double intercept) {
      super(weights, intercept);
      RegressionModel.$init$(this);
      PMMLExportable.$init$(this);
   }
}
