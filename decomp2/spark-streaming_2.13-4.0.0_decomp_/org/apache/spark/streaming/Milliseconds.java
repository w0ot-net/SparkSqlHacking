package org.apache.spark.streaming;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011:Q\u0001B\u0003\t\u000291Q\u0001E\u0003\t\u0002EAQ\u0001G\u0001\u0005\u0002eAQAG\u0001\u0005\u0002m\tA\"T5mY&\u001cXmY8oINT!AB\u0004\u0002\u0013M$(/Z1nS:<'B\u0001\u0005\n\u0003\u0015\u0019\b/\u0019:l\u0015\tQ1\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0019\u0005\u0019qN]4\u0004\u0001A\u0011q\"A\u0007\u0002\u000b\taQ*\u001b7mSN,7m\u001c8egN\u0011\u0011A\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005q\u0011!B1qa2LHC\u0001\u000f !\tyQ$\u0003\u0002\u001f\u000b\tAA)\u001e:bi&|g\u000eC\u0003!\u0007\u0001\u0007\u0011%\u0001\u0007nS2d\u0017n]3d_:$7\u000f\u0005\u0002\u0014E%\u00111\u0005\u0006\u0002\u0005\u0019>tw\r"
)
public final class Milliseconds {
   public static Duration apply(final long milliseconds) {
      return Milliseconds$.MODULE$.apply(milliseconds);
   }
}
