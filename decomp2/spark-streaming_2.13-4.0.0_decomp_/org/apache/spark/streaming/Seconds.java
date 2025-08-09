package org.apache.spark.streaming;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011:Q\u0001B\u0003\t\u000291Q\u0001E\u0003\t\u0002EAQ\u0001G\u0001\u0005\u0002eAQAG\u0001\u0005\u0002m\tqaU3d_:$7O\u0003\u0002\u0007\u000f\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003\u0011%\tQa\u001d9be.T!AC\u0006\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0011aA8sO\u000e\u0001\u0001CA\b\u0002\u001b\u0005)!aB*fG>tGm]\n\u0003\u0003I\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u000f\u0003\u0015\t\u0007\u000f\u001d7z)\tar\u0004\u0005\u0002\u0010;%\u0011a$\u0002\u0002\t\tV\u0014\u0018\r^5p]\")\u0001e\u0001a\u0001C\u000591/Z2p]\u0012\u001c\bCA\n#\u0013\t\u0019CC\u0001\u0003M_:<\u0007"
)
public final class Seconds {
   public static Duration apply(final long seconds) {
      return Seconds$.MODULE$.apply(seconds);
   }
}
