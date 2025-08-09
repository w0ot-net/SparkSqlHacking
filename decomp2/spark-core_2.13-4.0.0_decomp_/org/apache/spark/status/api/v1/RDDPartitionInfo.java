package org.apache.spark.status.api.v1;

import scala.collection.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3A\u0001D\u0007\u00015!A\u0011\u0005\u0001BC\u0002\u0013\u0005!\u0005\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003$\u0011!y\u0003A!b\u0001\n\u0003\u0011\u0003\u0002\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u0012\t\u0011E\u0002!Q1A\u0005\u0002IB\u0001B\u000e\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\to\u0001\u0011)\u0019!C\u0001e!A\u0001\b\u0001B\u0001B\u0003%1\u0007\u0003\u0005:\u0001\t\u0015\r\u0011\"\u0001;\u0011!\t\u0005A!A!\u0002\u0013Y\u0004B\u0002\"\u0001\t\u0003\u00192I\u0001\tS\t\u0012\u0003\u0016M\u001d;ji&|g.\u00138g_*\u0011abD\u0001\u0003mFR!\u0001E\t\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0013'\u000511\u000f^1ukNT!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'oZ\u0002\u0001'\t\u00011\u0004\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VMZ\u0001\nE2|7m\u001b(b[\u0016,\u0012a\t\t\u0003I-r!!J\u0015\u0011\u0005\u0019jR\"A\u0014\u000b\u0005!J\u0012A\u0002\u001fs_>$h(\u0003\u0002+;\u00051\u0001K]3eK\u001aL!\u0001L\u0017\u0003\rM#(/\u001b8h\u0015\tQS$\u0001\u0006cY>\u001c7NT1nK\u0002\nAb\u001d;pe\u0006<W\rT3wK2\fQb\u001d;pe\u0006<W\rT3wK2\u0004\u0013AC7f[>\u0014\u00180V:fIV\t1\u0007\u0005\u0002\u001di%\u0011Q'\b\u0002\u0005\u0019>tw-A\u0006nK6|'/_+tK\u0012\u0004\u0013\u0001\u00033jg.,6/\u001a3\u0002\u0013\u0011L7o[+tK\u0012\u0004\u0013!C3yK\u000e,Ho\u001c:t+\u0005Y\u0004c\u0001\u001f@G5\tQH\u0003\u0002?;\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0001k$aA*fc\u0006QQ\r_3dkR|'o\u001d\u0011\u0002\rqJg.\u001b;?)\u0019!ei\u0012%J\u0015B\u0011Q\tA\u0007\u0002\u001b!)\u0011e\u0003a\u0001G!)qf\u0003a\u0001G!)\u0011g\u0003a\u0001g!)qg\u0003a\u0001g!)\u0011h\u0003a\u0001w\u0001"
)
public class RDDPartitionInfo {
   private final String blockName;
   private final String storageLevel;
   private final long memoryUsed;
   private final long diskUsed;
   private final Seq executors;

   public String blockName() {
      return this.blockName;
   }

   public String storageLevel() {
      return this.storageLevel;
   }

   public long memoryUsed() {
      return this.memoryUsed;
   }

   public long diskUsed() {
      return this.diskUsed;
   }

   public Seq executors() {
      return this.executors;
   }

   public RDDPartitionInfo(final String blockName, final String storageLevel, final long memoryUsed, final long diskUsed, final Seq executors) {
      this.blockName = blockName;
      this.storageLevel = storageLevel;
      this.memoryUsed = memoryUsed;
      this.diskUsed = diskUsed;
      this.executors = executors;
   }
}
