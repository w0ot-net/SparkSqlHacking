package org.apache.spark.rdd;

import org.apache.spark.Partition;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I2Q\u0001C\u0005\u0001\u0017EA\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\tC\u0001\u0011)\u0019!C\u0001E!Aa\u0005\u0001B\u0001B\u0003%1\u0005\u0003\u0005(\u0001\t\u0015\r\u0011\"\u0001#\u0011!A\u0003A!A!\u0002\u0013\u0019\u0003\"B\u0015\u0001\t\u0003Q\u0003\"\u0002\u0019\u0001\t\u0003\n$!\u0004&eE\u000e\u0004\u0016M\u001d;ji&|gN\u0003\u0002\u000b\u0017\u0005\u0019!\u000f\u001a3\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c2\u0001\u0001\n\u0019!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0011\u0011DG\u0007\u0002\u0017%\u00111d\u0003\u0002\n!\u0006\u0014H/\u001b;j_:\f1!\u001b3y\u0007\u0001\u0001\"aE\u0010\n\u0005\u0001\"\"aA%oi\u0006)An\\<feV\t1\u0005\u0005\u0002\u0014I%\u0011Q\u0005\u0006\u0002\u0005\u0019>tw-\u0001\u0004m_^,'\u000fI\u0001\u0006kB\u0004XM]\u0001\u0007kB\u0004XM\u001d\u0011\u0002\rqJg.\u001b;?)\u0011YSFL\u0018\u0011\u00051\u0002Q\"A\u0005\t\u000bq1\u0001\u0019\u0001\u0010\t\u000b\u00052\u0001\u0019A\u0012\t\u000b\u001d2\u0001\u0019A\u0012\u0002\u000b%tG-\u001a=\u0016\u0003y\u0001"
)
public class JdbcPartition implements Partition {
   private final int idx;
   private final long lower;
   private final long upper;

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int hashCode() {
      return Partition.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   public long lower() {
      return this.lower;
   }

   public long upper() {
      return this.upper;
   }

   public int index() {
      return this.idx;
   }

   public JdbcPartition(final int idx, final long lower, final long upper) {
      this.idx = idx;
      this.lower = lower;
      this.upper = upper;
      Partition.$init$(this);
   }
}
