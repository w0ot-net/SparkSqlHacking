package org.apache.spark.ml.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3Q!\u0003\u0006\u0001\u001dQA\u0001\"\u0007\u0001\u0003\u0006\u0004%\te\u0007\u0005\tS\u0001\u0011\t\u0011)A\u00059!)!\u0006\u0001C\u0001W!9a\u0006\u0001a\u0001\n\u0013y\u0003b\u0002\u001b\u0001\u0001\u0004%I!\u000e\u0005\u0007w\u0001\u0001\u000b\u0015\u0002\u0019\t\u000bq\u0002A\u0011I\u001f\t\u000by\u0002A\u0011K \u0003\u001d1{7-\u00197Ti>\u0004x/\u0019;dQ*\u00111\u0002D\u0001\u0005kRLGN\u0003\u0002\u000e\u001d\u0005\u0011Q\u000e\u001c\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sON\u0011\u0001!\u0006\t\u0003-]i\u0011AC\u0005\u00031)\u0011\u0011b\u0015;pa^\fGo\u00195\u0002\t9\fW.Z\u0002\u0001+\u0005a\u0002CA\u000f'\u001d\tqB\u0005\u0005\u0002 E5\t\u0001E\u0003\u0002\"5\u00051AH]8pizR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\na\u0001\u0015:fI\u00164\u0017BA\u0014)\u0005\u0019\u0019FO]5oO*\u0011QEI\u0001\u0006]\u0006lW\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00051j\u0003C\u0001\f\u0001\u0011\u0015I2\u00011\u0001\u001d\u0003-)G.\u00199tK\u0012$\u0016.\\3\u0016\u0003A\u0002\"!\r\u001a\u000e\u0003\tJ!a\r\u0012\u0003\t1{gnZ\u0001\u0010K2\f\u0007o]3e)&lWm\u0018\u0013fcR\u0011a'\u000f\t\u0003c]J!\u0001\u000f\u0012\u0003\tUs\u0017\u000e\u001e\u0005\bu\u0015\t\t\u00111\u00011\u0003\rAH%M\u0001\rK2\f\u0007o]3e)&lW\rI\u0001\bK2\f\u0007o]3e)\u0005\u0001\u0014aA1eIR\u0011a\u0007\u0011\u0005\u0006\u0003\"\u0001\r\u0001M\u0001\tIV\u0014\u0018\r^5p]\u0002"
)
public class LocalStopwatch extends Stopwatch {
   private final String name;
   private long elapsedTime;

   public String name() {
      return this.name;
   }

   private long elapsedTime() {
      return this.elapsedTime;
   }

   private void elapsedTime_$eq(final long x$1) {
      this.elapsedTime = x$1;
   }

   public long elapsed() {
      return this.elapsedTime();
   }

   public void add(final long duration) {
      this.elapsedTime_$eq(this.elapsedTime() + duration);
   }

   public LocalStopwatch(final String name) {
      this.name = name;
      this.elapsedTime = 0L;
   }
}
