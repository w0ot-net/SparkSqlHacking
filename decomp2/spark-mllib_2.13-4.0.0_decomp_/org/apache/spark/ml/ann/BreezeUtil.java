package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I;aAB\u0004\t\u0002\u001d\tbAB\n\b\u0011\u00039A\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0003\u001f\u0003\u0011%q\u0004C\u00039\u0003\u0011\u0005\u0011\bC\u0003G\u0003\u0011\u0005q)\u0001\u0006Ce\u0016,'0Z+uS2T!\u0001C\u0005\u0002\u0007\u0005tgN\u0003\u0002\u000b\u0017\u0005\u0011Q\u000e\u001c\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sOB\u0011!#A\u0007\u0002\u000f\tQ!I]3fu\u0016,F/\u001b7\u0014\u0005\u0005)\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\t\u0012a\u0004;sC:\u001c\bo\\:f'R\u0014\u0018N\\4\u0015\u0005\u0001Z\u0003CA\u0011)\u001d\t\u0011c\u0005\u0005\u0002$/5\tAE\u0003\u0002&9\u00051AH]8pizJ!aJ\f\u0002\rA\u0013X\rZ3g\u0013\tI#F\u0001\u0004TiJLgn\u001a\u0006\u0003O]AQ\u0001L\u0002A\u00025\n\u0011!\u0011\t\u0004]M*T\"A\u0018\u000b\u0005A\n\u0014A\u00027j]\u0006dwMC\u00013\u0003\u0019\u0011'/Z3{K&\u0011Ag\f\u0002\f\t\u0016t7/Z'biJL\u0007\u0010\u0005\u0002\u0017m%\u0011qg\u0006\u0002\u0007\t>,(\r\\3\u0002\u000b\u0011<W-\\7\u0015\rijt\b\u0011\"E!\t12(\u0003\u0002=/\t!QK\\5u\u0011\u0015qD\u00011\u00016\u0003\u0015\tG\u000e\u001d5b\u0011\u0015aC\u00011\u0001.\u0011\u0015\tE\u00011\u0001.\u0003\u0005\u0011\u0005\"B\"\u0005\u0001\u0004)\u0014\u0001\u00022fi\u0006DQ!\u0012\u0003A\u00025\n\u0011aQ\u0001\u0006I\u001e,WN\u001e\u000b\u0007u!K%j\u0014)\t\u000by*\u0001\u0019A\u001b\t\u000b1*\u0001\u0019A\u0017\t\u000b-+\u0001\u0019\u0001'\u0002\u0003a\u00042AL'6\u0013\tquFA\u0006EK:\u001cXMV3di>\u0014\b\"B\"\u0006\u0001\u0004)\u0004\"B)\u0006\u0001\u0004a\u0015!A="
)
public final class BreezeUtil {
   public static void dgemv(final double alpha, final DenseMatrix A, final DenseVector x, final double beta, final DenseVector y) {
      BreezeUtil$.MODULE$.dgemv(alpha, A, x, beta, y);
   }

   public static void dgemm(final double alpha, final DenseMatrix A, final DenseMatrix B, final double beta, final DenseMatrix C) {
      BreezeUtil$.MODULE$.dgemm(alpha, A, B, beta, C);
   }
}
