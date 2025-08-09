package org.apache.spark.status.api.v1;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2AAC\u0006\u00011!Aq\u0004\u0001BC\u0002\u0013\u0005\u0001\u0005\u0003\u0005%\u0001\t\u0005\t\u0015!\u0003\"\u0011!)\u0003A!b\u0001\n\u0003\u0001\u0003\u0002\u0003\u0014\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\t\u0011\u001d\u0002!Q1A\u0005\u0002\u0001B\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tS\u0001\u0011)\u0019!C\u0001A!A!\u0006\u0001B\u0001B\u0003%\u0011\u0005\u0003\u0004,\u0001\u0011\u0005\u0011\u0003\f\u0002\u000e\u001b\u0016lwN]=NKR\u0014\u0018nY:\u000b\u00051i\u0011A\u0001<2\u0015\tqq\"A\u0002ba&T!\u0001E\t\u0002\rM$\u0018\r^;t\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7\u0001A\n\u0003\u0001e\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0017aF;tK\u0012|e\u000eS3baN#xN]1hK6+Wn\u001c:z+\u0005\t\u0003C\u0001\u000e#\u0013\t\u00193D\u0001\u0003M_:<\u0017\u0001G;tK\u0012|e\u000eS3baN#xN]1hK6+Wn\u001c:zA\u0005ARo]3e\u001f\u001a4\u0007*Z1q'R|'/Y4f\u001b\u0016lwN]=\u00023U\u001cX\rZ(gM\"+\u0017\r]*u_J\fw-Z'f[>\u0014\u0018\u0010I\u0001\u0019i>$\u0018\r\\(o\u0011\u0016\f\u0007o\u0015;pe\u0006<W-T3n_JL\u0018!\u0007;pi\u0006dwJ\u001c%fCB\u001cFo\u001c:bO\u0016lU-\\8ss\u0002\n\u0011\u0004^8uC2|eM\u001a%fCB\u001cFo\u001c:bO\u0016lU-\\8ss\u0006QBo\u001c;bY>3g\rS3baN#xN]1hK6+Wn\u001c:zA\u00051A(\u001b8jiz\"R!L\u00181cI\u0002\"A\f\u0001\u000e\u0003-AQaH\u0005A\u0002\u0005BQ!J\u0005A\u0002\u0005BQaJ\u0005A\u0002\u0005BQ!K\u0005A\u0002\u0005\u0002"
)
public class MemoryMetrics {
   private final long usedOnHeapStorageMemory;
   private final long usedOffHeapStorageMemory;
   private final long totalOnHeapStorageMemory;
   private final long totalOffHeapStorageMemory;

   public long usedOnHeapStorageMemory() {
      return this.usedOnHeapStorageMemory;
   }

   public long usedOffHeapStorageMemory() {
      return this.usedOffHeapStorageMemory;
   }

   public long totalOnHeapStorageMemory() {
      return this.totalOnHeapStorageMemory;
   }

   public long totalOffHeapStorageMemory() {
      return this.totalOffHeapStorageMemory;
   }

   public MemoryMetrics(final long usedOnHeapStorageMemory, final long usedOffHeapStorageMemory, final long totalOnHeapStorageMemory, final long totalOffHeapStorageMemory) {
      this.usedOnHeapStorageMemory = usedOnHeapStorageMemory;
      this.usedOffHeapStorageMemory = usedOffHeapStorageMemory;
      this.totalOnHeapStorageMemory = totalOnHeapStorageMemory;
      this.totalOffHeapStorageMemory = totalOffHeapStorageMemory;
   }
}
