package org.apache.spark.util;

import scala.Function0;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2Aa\u0002\u0005\u0005#!A!\u0005\u0001BC\u0002\u0013%1\u0005\u0003\u0005(\u0001\t\u0005\t\u0015!\u0003%\u0011!A\u0003A!A!\u0002\u0013I\u0003\"B\u0018\u0001\t\u0003\u0001\u0004\"B\u001a\u0001\t\u0003\"\u0004\"B\u001c\u0001\t\u0003A$!E*qCJ\\7\u000b[;uI><h\u000eS8pW*\u0011\u0011BC\u0001\u0005kRLGN\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h\u0007\u0001\u00192\u0001\u0001\n\u0019!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0019\u0011D\b\u0011\u000e\u0003iQ!a\u0007\u000f\u0002\t1\fgn\u001a\u0006\u0002;\u0005!!.\u0019<b\u0013\ty\"D\u0001\u0006D_6\u0004\u0018M]1cY\u0016\u0004\"!\t\u0001\u000e\u0003!\t\u0001\u0002\u001d:j_JLG/_\u000b\u0002IA\u00111#J\u0005\u0003MQ\u00111!\u00138u\u0003%\u0001(/[8sSRL\b%\u0001\u0003i_>\\\u0007cA\n+Y%\u00111\u0006\u0006\u0002\n\rVt7\r^5p]B\u0002\"aE\u0017\n\u00059\"\"\u0001B+oSR\fa\u0001P5oSRtDc\u0001\u00112e!)!\u0005\u0002a\u0001I!)\u0001\u0006\u0002a\u0001S\u0005I1m\\7qCJ,Gk\u001c\u000b\u0003IUBQAN\u0003A\u0002\u0001\nQa\u001c;iKJ\f1A];o)\u0005a\u0003"
)
public class SparkShutdownHook implements Comparable {
   private final int priority;
   private final Function0 hook;

   private int priority() {
      return this.priority;
   }

   public int compareTo(final SparkShutdownHook other) {
      return .MODULE$.int2Integer(other.priority()).compareTo(.MODULE$.int2Integer(this.priority()));
   }

   public void run() {
      this.hook.apply$mcV$sp();
   }

   public SparkShutdownHook(final int priority, final Function0 hook) {
      this.priority = priority;
      this.hook = hook;
   }
}
