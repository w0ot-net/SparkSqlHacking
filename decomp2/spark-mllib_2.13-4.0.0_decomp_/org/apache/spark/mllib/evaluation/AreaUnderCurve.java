package org.apache.spark.mllib.evaluation;

import org.apache.spark.rdd.RDD;
import scala.collection.Iterable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r;aAB\u0004\t\u0002\u001d\tbAB\n\b\u0011\u00039A\u0003C\u0003\u001c\u0003\u0011\u0005Q\u0004C\u0003\u001f\u0003\u0011%q\u0004C\u00035\u0003\u0011\u0005Q\u0007C\u00035\u0003\u0011\u0005a(\u0001\bBe\u0016\fWK\u001c3fe\u000e+(O^3\u000b\u0005!I\u0011AC3wC2,\u0018\r^5p]*\u0011!bC\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sOB\u0011!#A\u0007\u0002\u000f\tq\u0011I]3b+:$WM]\"veZ,7CA\u0001\u0016!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0012\u0003%!(/\u00199fu>LG\r\u0006\u0002!GA\u0011a#I\u0005\u0003E]\u0011a\u0001R8vE2,\u0007\"\u0002\u0013\u0004\u0001\u0004)\u0013A\u00029pS:$8\u000fE\u0002']Er!a\n\u0017\u000f\u0005!ZS\"A\u0015\u000b\u0005)b\u0012A\u0002\u001fs_>$h(C\u0001\u0019\u0013\tis#A\u0004qC\u000e\\\u0017mZ3\n\u0005=\u0002$aA*fc*\u0011Qf\u0006\t\u0005-I\u0002\u0003%\u0003\u00024/\t1A+\u001e9mKJ\n!a\u001c4\u0015\u0005\u00012\u0004\"B\u001c\u0005\u0001\u0004A\u0014!B2veZ,\u0007cA\u001d=c5\t!H\u0003\u0002<\u0017\u0005\u0019!\u000f\u001a3\n\u0005uR$a\u0001*E\tR\u0011\u0001e\u0010\u0005\u0006o\u0015\u0001\r\u0001\u0011\t\u0004M\u0005\u000b\u0014B\u0001\"1\u0005!IE/\u001a:bE2,\u0007"
)
public final class AreaUnderCurve {
   public static double of(final Iterable curve) {
      return AreaUnderCurve$.MODULE$.of(curve);
   }

   public static double of(final RDD curve) {
      return AreaUnderCurve$.MODULE$.of(curve);
   }
}
