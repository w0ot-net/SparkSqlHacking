package org.apache.spark.mllib.stat;

import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.stat.test.ChiSqTestResult;
import org.apache.spark.mllib.stat.test.KolmogorovSmirnovTestResult;
import org.apache.spark.rdd.RDD;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ew!B\n\u0015\u0011\u0003yb!B\u0011\u0015\u0011\u0003\u0011\u0003\"B\u0015\u0002\t\u0003Q\u0003\"B\u0016\u0002\t\u0003a\u0003BB\u0016\u0002\t\u00031r\tC\u0003n\u0003\u0011\u0005a\u000eC\u0003n\u0003\u0011\u0005A\u000fC\u0003n\u0003\u0011\u0005\u0011\u0010\u0003\u0004n\u0003\u0011\u0005\u0011\u0011\u0001\u0005\u0007[\u0006!\t!!\u000b\t\r5\fA\u0011AA\u001a\u0011\u001d\ti$\u0001C\u0001\u0003\u007fAq!!\u0010\u0002\t\u0003\t9\u0006C\u0004\u0002>\u0005!\t!!\u0018\t\u000f\u0005u\u0012\u0001\"\u0001\u0002d!9\u0011QH\u0001\u0005\u0002\u0005}\u0004bBAF\u0003\u0011\u0005\u0011Q\u0012\u0005\b\u0003\u0017\u000bA\u0011AAR\u0011\u001d\tY)\u0001C\u0001\u0003\u0007\f!b\u0015;bi&\u001cH/[2t\u0015\t)b#\u0001\u0003ti\u0006$(BA\f\u0019\u0003\u0015iG\u000e\\5c\u0015\tI\"$A\u0003ta\u0006\u00148N\u0003\u0002\u001c9\u00051\u0011\r]1dQ\u0016T\u0011!H\u0001\u0004_J<7\u0001\u0001\t\u0003A\u0005i\u0011\u0001\u0006\u0002\u000b'R\fG/[:uS\u000e\u001c8CA\u0001$!\t!s%D\u0001&\u0015\u00051\u0013!B:dC2\f\u0017B\u0001\u0015&\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012aH\u0001\tG>d7\u000b^1ugR\u0011Q\u0006\r\t\u0003A9J!a\f\u000b\u0003=5+H\u000e^5wCJL\u0017\r^3Ti\u0006$\u0018n\u001d;jG\u0006d7+^7nCJL\b\"B\u0019\u0004\u0001\u0004\u0011\u0014!\u0001-\u0011\u0007M2\u0004(D\u00015\u0015\t)\u0004$A\u0002sI\u0012L!a\u000e\u001b\u0003\u0007I#E\t\u0005\u0002:y5\t!H\u0003\u0002<-\u00051A.\u001b8bY\u001eL!!\u0010\u001e\u0003\rY+7\r^8sQ\r\u0019q(\u0012\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005b\t!\"\u00198o_R\fG/[8o\u0013\t!\u0015IA\u0003TS:\u001cW-I\u0001G\u0003\u0015\td&\r\u00181)\rAuj\u0016\t\u0003\u00136k\u0011A\u0013\u0006\u0003+-S!\u0001\u0014\r\u0002\u00055d\u0017B\u0001(K\u0005A\u0019V/\\7be&TXM\u001d\"vM\u001a,'\u000fC\u00032\t\u0001\u0007\u0001\u000bE\u00024mE\u0003B\u0001\n*9)&\u00111+\n\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005\u0011*\u0016B\u0001,&\u0005\u0019!u.\u001e2mK\")\u0001\f\u0002a\u00013\u0006I!/Z9vKN$X\r\u001a\t\u00045\n,gBA.a\u001d\tav,D\u0001^\u0015\tqf$\u0001\u0004=e>|GOP\u0005\u0002M%\u0011\u0011-J\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019GMA\u0002TKFT!!Y\u0013\u0011\u0005\u0019TgBA4i!\taV%\u0003\u0002jK\u00051\u0001K]3eK\u001aL!a\u001b7\u0003\rM#(/\u001b8h\u0015\tIW%\u0001\u0003d_J\u0014HCA8s!\tI\u0004/\u0003\u0002ru\t1Q*\u0019;sSbDQ!M\u0003A\u0002IB3!B F)\ryWO\u001e\u0005\u0006c\u0019\u0001\rA\r\u0005\u0006o\u001a\u0001\r!Z\u0001\u0007[\u0016$\bn\u001c3)\u0007\u0019yT\tF\u0002UuvDQa_\u0004A\u0002q\f\u0011\u0001\u001f\t\u0004gY\"\u0006\"\u0002@\b\u0001\u0004a\u0018!A=)\u0007\u001dyT\tF\u0003U\u0003\u0007\t\t\u0003\u0003\u0004|\u0011\u0001\u0007\u0011Q\u0001\t\u0007\u0003\u000f\t\t\"!\u0006\u000e\u0005\u0005%!\u0002BA\u0006\u0003\u001b\tAA[1wC*\u0019\u0011q\u0002\r\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002\u0014\u0005%!a\u0002&bm\u0006\u0014F\t\u0012\t\u0005\u0003/\ty\"\u0004\u0002\u0002\u001a)!\u00111DA\u000f\u0003\u0011a\u0017M\\4\u000b\u0005\u0005-\u0011b\u0001,\u0002\u001a!1a\u0010\u0003a\u0001\u0003\u000bAC\u0001C \u0002&\u0005\u0012\u0011qE\u0001\u0006c9\"d&\r\u000b\b)\u0006-\u0012QFA\u0018\u0011\u0015Y\u0018\u00021\u0001}\u0011\u0015q\u0018\u00021\u0001}\u0011\u00159\u0018\u00021\u0001fQ\rIq(\u0012\u000b\b)\u0006U\u0012qGA\u001d\u0011\u0019Y(\u00021\u0001\u0002\u0006!1aP\u0003a\u0001\u0003\u000bAQa\u001e\u0006A\u0002\u0015DCAC \u0002&\u0005I1\r[5TcR+7\u000f\u001e\u000b\u0007\u0003\u0003\ni%!\u0015\u0011\t\u0005\r\u0013\u0011J\u0007\u0003\u0003\u000bR1!a\u0012\u0015\u0003\u0011!Xm\u001d;\n\t\u0005-\u0013Q\t\u0002\u0010\u0007\"L7+\u001d+fgR\u0014Vm];mi\"1\u0011qJ\u0006A\u0002a\n\u0001b\u001c2tKJ4X\r\u001a\u0005\u0007\u0003'Z\u0001\u0019\u0001\u001d\u0002\u0011\u0015D\b/Z2uK\u0012D3aC F)\u0011\t\t%!\u0017\t\r\u0005=C\u00021\u00019Q\raq(\u0012\u000b\u0005\u0003\u0003\ny\u0006\u0003\u0004\u0002P5\u0001\ra\u001c\u0015\u0004\u001b}*E\u0003BA3\u0003W\u0002R\u0001JA4\u0003\u0003J1!!\u001b&\u0005\u0015\t%O]1z\u0011\u001d\tiG\u0004a\u0001\u0003_\nA\u0001Z1uCB!1GNA9!\u0011\t\u0019(!\u001f\u000e\u0005\u0005U$bAA<-\u0005Q!/Z4sKN\u001c\u0018n\u001c8\n\t\u0005m\u0014Q\u000f\u0002\r\u0019\u0006\u0014W\r\\3e!>Lg\u000e\u001e\u0015\u0004\u001d}*E\u0003BA3\u0003\u0003Cq!!\u001c\u0010\u0001\u0004\t\u0019\t\u0005\u0004\u0002\b\u0005E\u0011\u0011\u000f\u0015\u0005\u001f}\n9)\t\u0002\u0002\n\u0006)\u0011GL\u001b/a\u0005)2n\u001c7n_\u001e|'o\u001c<T[&\u0014hn\u001c<UKN$HCBAH\u0003+\u000b9\n\u0005\u0003\u0002D\u0005E\u0015\u0002BAJ\u0003\u000b\u00121dS8m[><wN]8w'6L'O\\8w)\u0016\u001cHOU3tk2$\bBBA7!\u0001\u0007A\u0010C\u0004\u0002\u001aB\u0001\r!a'\u0002\u0007\r$g\rE\u0003%\u0003;#F+C\u0002\u0002 \u0016\u0012\u0011BR;oGRLwN\\\u0019)\tAy\u0014q\u0011\u000b\t\u0003\u001f\u000b)+a*\u0002,\"1\u0011QN\tA\u0002qDa!!+\u0012\u0001\u0004)\u0017\u0001\u00033jgRt\u0015-\\3\t\u000f\u00055\u0016\u00031\u0001\u00020\u00061\u0001/\u0019:b[N\u0004B\u0001JAY)&\u0019\u00111W\u0013\u0003\u0015q\u0012X\r]3bi\u0016$g\b\u000b\u0003\u0012\u007f\u0005\u001d\u0005fA\t\u0002:B!\u00111XA`\u001b\t\tiL\u0003\u0002CK%!\u0011\u0011YA_\u0005\u001d1\u0018M]1sON$\u0002\"a$\u0002F\u00065\u0017q\u001a\u0005\b\u0003[\u0012\u0002\u0019AAd!\u0011\t9!!3\n\t\u0005-\u0017\u0011\u0002\u0002\u000e\u0015\u00064\u0018\rR8vE2,'\u000b\u0012#\t\r\u0005%&\u00031\u0001f\u0011\u001d\tiK\u0005a\u0001\u0003_CCAE \u0002\b\"\u001a!#!/)\u0007\u0005yT\tK\u0002\u0001\u007f\u0015\u0003"
)
public final class Statistics {
   public static KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final JavaDoubleRDD data, final String distName, final double... params) {
      return Statistics$.MODULE$.kolmogorovSmirnovTest(data, distName, params);
   }

   public static KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final RDD data, final String distName, final double... params) {
      return Statistics$.MODULE$.kolmogorovSmirnovTest(data, distName, params);
   }

   public static KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final JavaDoubleRDD data, final String distName, final Seq params) {
      return Statistics$.MODULE$.kolmogorovSmirnovTest(data, distName, params);
   }

   public static KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final RDD data, final String distName, final Seq params) {
      return Statistics$.MODULE$.kolmogorovSmirnovTest(data, distName, params);
   }

   public static KolmogorovSmirnovTestResult kolmogorovSmirnovTest(final RDD data, final Function1 cdf) {
      return Statistics$.MODULE$.kolmogorovSmirnovTest(data, cdf);
   }

   public static ChiSqTestResult[] chiSqTest(final JavaRDD data) {
      return Statistics$.MODULE$.chiSqTest(data);
   }

   public static ChiSqTestResult[] chiSqTest(final RDD data) {
      return Statistics$.MODULE$.chiSqTest(data);
   }

   public static ChiSqTestResult chiSqTest(final Matrix observed) {
      return Statistics$.MODULE$.chiSqTest(observed);
   }

   public static ChiSqTestResult chiSqTest(final Vector observed) {
      return Statistics$.MODULE$.chiSqTest(observed);
   }

   public static ChiSqTestResult chiSqTest(final Vector observed, final Vector expected) {
      return Statistics$.MODULE$.chiSqTest(observed, expected);
   }

   public static double corr(final JavaRDD x, final JavaRDD y, final String method) {
      return Statistics$.MODULE$.corr(x, y, method);
   }

   public static double corr(final RDD x, final RDD y, final String method) {
      return Statistics$.MODULE$.corr(x, y, method);
   }

   public static double corr(final JavaRDD x, final JavaRDD y) {
      return Statistics$.MODULE$.corr(x, y);
   }

   public static double corr(final RDD x, final RDD y) {
      return Statistics$.MODULE$.corr(x, y);
   }

   public static Matrix corr(final RDD X, final String method) {
      return Statistics$.MODULE$.corr(X, method);
   }

   public static Matrix corr(final RDD X) {
      return Statistics$.MODULE$.corr(X);
   }

   public static MultivariateStatisticalSummary colStats(final RDD X) {
      return Statistics$.MODULE$.colStats(X);
   }
}
