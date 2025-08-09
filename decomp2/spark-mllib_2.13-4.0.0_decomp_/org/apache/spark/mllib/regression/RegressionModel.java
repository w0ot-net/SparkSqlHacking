package org.apache.spark.mllib.regression;

import java.io.Serializable;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import org.json4s.JValue;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005aa\u0002\u0006\f!\u0003\r\tA\u0006\u0005\u0006S\u0001!\tA\u000b\u0005\u0006]\u00011\ta\f\u0005\u0006]\u00011\ta\u0013\u0005\u0006]\u0001!\tAT\u0004\u0007G.A\t!\u00043\u0007\r)Y\u0001\u0012A\u0007g\u0011\u0015ag\u0001\"\u0001n\u0011\u0015qg\u0001\"\u0001p\u0011\u001dYh!!A\u0005\nq\u0014qBU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\u0006\u0003\u00195\t!B]3he\u0016\u001c8/[8o\u0015\tqq\"A\u0003nY2L'M\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h\u0007\u0001\u00192\u0001A\f\u001e!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fMB\u0011aD\n\b\u0003?\u0011r!\u0001I\u0012\u000e\u0003\u0005R!AI\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0012BA\u0013\u001a\u0003\u001d\u0001\u0018mY6bO\u0016L!a\n\u0015\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u0015J\u0012A\u0002\u0013j]&$H\u0005F\u0001,!\tAB&\u0003\u0002.3\t!QK\\5u\u0003\u001d\u0001(/\u001a3jGR$\"\u0001M\u001d\u0011\u0007E\"d'D\u00013\u0015\t\u0019t\"A\u0002sI\u0012L!!\u000e\u001a\u0003\u0007I#E\t\u0005\u0002\u0019o%\u0011\u0001(\u0007\u0002\u0007\t>,(\r\\3\t\u000bi\u0012\u0001\u0019A\u001e\u0002\u0011Q,7\u000f\u001e#bi\u0006\u00042!\r\u001b=!\ti\u0004)D\u0001?\u0015\tyT\"\u0001\u0004mS:\fGnZ\u0005\u0003\u0003z\u0012aAV3di>\u0014\bf\u0001\u0002D\u0013B\u0011AiR\u0007\u0002\u000b*\u0011aiD\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001%F\u0005\u0015\u0019\u0016N\\2fC\u0005Q\u0015!B\u0019/a9\u0002DC\u0001\u001cM\u0011\u0015Q4\u00011\u0001=Q\r\u00191)\u0013\u000b\u0003\u001fv\u00032\u0001U+X\u001b\u0005\t&B\u0001*T\u0003\u0011Q\u0017M^1\u000b\u0005Q{\u0011aA1qS&\u0011a+\u0015\u0002\b\u0015\u00064\u0018M\u0015#E!\tAF,D\u0001Z\u0015\tQ6,\u0001\u0003mC:<'\"\u0001*\n\u0005aJ\u0006\"\u0002\u001e\u0005\u0001\u0004q\u0006c\u0001)Vy!\u001aAaQ%)\u0007\u0001\u0019\u0015-I\u0001c\u0003\u0015\u0001d\u0006\u000f\u00181\u0003=\u0011Vm\u001a:fgNLwN\\'pI\u0016d\u0007CA3\u0007\u001b\u0005Y1c\u0001\u0004\u0018OB\u0011\u0001n[\u0007\u0002S*\u0011!nW\u0001\u0003S>L!aJ5\u0002\rqJg.\u001b;?)\u0005!\u0017AD4fi:+XNR3biV\u0014Xm\u001d\u000b\u0003aN\u0004\"\u0001G9\n\u0005IL\"aA%oi\")A\u000f\u0003a\u0001k\u0006AQ.\u001a;bI\u0006$\u0018\r\u0005\u0002ws6\tqO\u0003\u0002y'\u00051!n]8oiML!A_<\u0003\r)3\u0016\r\\;f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005i\bC\u0001-\u007f\u0013\ty\u0018L\u0001\u0004PE*,7\r\u001e"
)
public interface RegressionModel extends Serializable {
   static int getNumFeatures(final JValue metadata) {
      return RegressionModel$.MODULE$.getNumFeatures(metadata);
   }

   RDD predict(final RDD testData);

   double predict(final Vector testData);

   // $FF: synthetic method
   static JavaRDD predict$(final RegressionModel $this, final JavaRDD testData) {
      return $this.predict(testData);
   }

   default JavaRDD predict(final JavaRDD testData) {
      return this.predict(testData.rdd()).toJavaRDD();
   }

   static void $init$(final RegressionModel $this) {
   }
}
