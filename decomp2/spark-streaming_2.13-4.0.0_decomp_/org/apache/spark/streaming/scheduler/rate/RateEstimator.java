package org.apache.spark.streaming.scheduler.rate;

import java.io.Serializable;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}3\u0001b\u0002\u0005\u0011\u0002G\u0005A\u0002\u0006\u0005\u0006Q\u00011\t!K\u0004\u0006w!A\t\u0001\u0010\u0004\u0006\u000f!A\tA\u0010\u0005\u0006\r\u000e!\ta\u0012\u0005\u0006\u0011\u000e!\t!\u0013\u0005\b/\u000e\t\t\u0011\"\u0003Y\u00055\u0011\u0016\r^3FgRLW.\u0019;pe*\u0011\u0011BC\u0001\u0005e\u0006$XM\u0003\u0002\f\u0019\u0005I1o\u00195fIVdWM\u001d\u0006\u0003\u001b9\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c2\u0001A\u000b\u001c!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fMB\u0011A$\n\b\u0003;\rr!A\b\u0012\u000e\u0003}Q!\u0001I\u0011\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001G\u0005\u0003I]\tq\u0001]1dW\u0006<W-\u0003\u0002'O\ta1+\u001a:jC2L'0\u00192mK*\u0011AeF\u0001\bG>l\u0007/\u001e;f)\u0015Q\u0003'N\u001c:!\r12&L\u0005\u0003Y]\u0011aa\u00149uS>t\u0007C\u0001\f/\u0013\tysC\u0001\u0004E_V\u0014G.\u001a\u0005\u0006c\u0005\u0001\rAM\u0001\u0005i&lW\r\u0005\u0002\u0017g%\u0011Ag\u0006\u0002\u0005\u0019>tw\rC\u00037\u0003\u0001\u0007!'\u0001\u0005fY\u0016lWM\u001c;t\u0011\u0015A\u0014\u00011\u00013\u0003=\u0001(o\\2fgNLgn\u001a#fY\u0006L\b\"\u0002\u001e\u0002\u0001\u0004\u0011\u0014aD:dQ\u0016$W\u000f\\5oO\u0012+G.Y=\u0002\u001bI\u000bG/Z#ti&l\u0017\r^8s!\ti4!D\u0001\t'\r\u0019Qc\u0010\t\u0003\u0001\u0016k\u0011!\u0011\u0006\u0003\u0005\u000e\u000b!![8\u000b\u0003\u0011\u000bAA[1wC&\u0011a%Q\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003q\naa\u0019:fCR,Gc\u0001&L#B\u0011Q\b\u0001\u0005\u0006\u0019\u0016\u0001\r!T\u0001\u0005G>tg\r\u0005\u0002O\u001f6\ta\"\u0003\u0002Q\u001d\tI1\u000b]1sW\u000e{gN\u001a\u0005\u0006%\u0016\u0001\raU\u0001\u000eE\u0006$8\r[%oi\u0016\u0014h/\u00197\u0011\u0005Q+V\"\u0001\u0007\n\u0005Yc!\u0001\u0003#ve\u0006$\u0018n\u001c8\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003e\u0003\"AW/\u000e\u0003mS!\u0001X\"\u0002\t1\fgnZ\u0005\u0003=n\u0013aa\u00142kK\u000e$\b"
)
public interface RateEstimator extends Serializable {
   static RateEstimator create(final SparkConf conf, final Duration batchInterval) {
      return RateEstimator$.MODULE$.create(conf, batchInterval);
   }

   Option compute(final long time, final long elements, final long processingDelay, final long schedulingDelay);
}
