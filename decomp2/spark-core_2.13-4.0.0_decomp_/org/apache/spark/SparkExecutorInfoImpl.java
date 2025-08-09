package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3AAE\n\u00055!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u00053\u0001\t\u0005\t\u0015!\u0003(\u0011!\u0019\u0004A!b\u0001\n\u0003!\u0004\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011B\u001b\t\u0011e\u0002!Q1A\u0005\u0002iB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\t\u007f\u0001\u0011)\u0019!C\u0001i!A\u0001\t\u0001B\u0001B\u0003%Q\u0007\u0003\u0005B\u0001\t\u0015\r\u0011\"\u0001;\u0011!\u0011\u0005A!A!\u0002\u0013Y\u0004\u0002C\"\u0001\u0005\u000b\u0007I\u0011\u0001\u001e\t\u0011\u0011\u0003!\u0011!Q\u0001\nmB\u0001\"\u0012\u0001\u0003\u0006\u0004%\tA\u000f\u0005\t\r\u0002\u0011\t\u0011)A\u0005w!Aq\t\u0001BC\u0002\u0013\u0005!\b\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003<\u0011\u0015I\u0005\u0001\"\u0001K\u0005U\u0019\u0006/\u0019:l\u000bb,7-\u001e;pe&sgm\\%na2T!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'oZ\u0002\u0001'\r\u00011$\t\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\t\u001aS\"A\n\n\u0005\u0011\u001a\"!E*qCJ\\W\t_3dkR|'/\u00138g_\u0006!\u0001n\\:u+\u00059\u0003C\u0001\u00150\u001d\tIS\u0006\u0005\u0002+;5\t1F\u0003\u0002-3\u00051AH]8pizJ!AL\u000f\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0014G\u0001\u0004TiJLgn\u001a\u0006\u0003]u\tQ\u0001[8ti\u0002\nA\u0001]8siV\tQ\u0007\u0005\u0002\u001dm%\u0011q'\b\u0002\u0004\u0013:$\u0018!\u00029peR\u0004\u0013!C2bG\",7+\u001b>f+\u0005Y\u0004C\u0001\u000f=\u0013\tiTD\u0001\u0003M_:<\u0017AC2bG\",7+\u001b>fA\u0005ya.^7Sk:t\u0017N\\4UCN\\7/\u0001\tok6\u0014VO\u001c8j]\u001e$\u0016m]6tA\u00059Ro]3e\u001f:DU-\u00199Ti>\u0014\u0018mZ3NK6|'/_\u0001\u0019kN,Gm\u00148IK\u0006\u00048\u000b^8sC\u001e,W*Z7pef\u0004\u0013\u0001G;tK\u0012|eM\u001a%fCB\u001cFo\u001c:bO\u0016lU-\\8ss\u0006IRo]3e\u001f\u001a4\u0007*Z1q'R|'/Y4f\u001b\u0016lwN]=!\u0003a!x\u000e^1m\u001f:DU-\u00199Ti>\u0014\u0018mZ3NK6|'/_\u0001\u001ai>$\u0018\r\\(o\u0011\u0016\f\u0007o\u0015;pe\u0006<W-T3n_JL\b%A\ru_R\fGn\u00144g\u0011\u0016\f\u0007o\u0015;pe\u0006<W-T3n_JL\u0018A\u0007;pi\u0006dwJ\u001a4IK\u0006\u00048\u000b^8sC\u001e,W*Z7pef\u0004\u0013A\u0002\u001fj]&$h\bF\u0005L\u00196su\nU)S'B\u0011!\u0005\u0001\u0005\u0006KE\u0001\ra\n\u0005\u0006gE\u0001\r!\u000e\u0005\u0006sE\u0001\ra\u000f\u0005\u0006\u007fE\u0001\r!\u000e\u0005\u0006\u0003F\u0001\ra\u000f\u0005\u0006\u0007F\u0001\ra\u000f\u0005\u0006\u000bF\u0001\ra\u000f\u0005\u0006\u000fF\u0001\ra\u000f"
)
public class SparkExecutorInfoImpl implements SparkExecutorInfo {
   private final String host;
   private final int port;
   private final long cacheSize;
   private final int numRunningTasks;
   private final long usedOnHeapStorageMemory;
   private final long usedOffHeapStorageMemory;
   private final long totalOnHeapStorageMemory;
   private final long totalOffHeapStorageMemory;

   public String host() {
      return this.host;
   }

   public int port() {
      return this.port;
   }

   public long cacheSize() {
      return this.cacheSize;
   }

   public int numRunningTasks() {
      return this.numRunningTasks;
   }

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

   public SparkExecutorInfoImpl(final String host, final int port, final long cacheSize, final int numRunningTasks, final long usedOnHeapStorageMemory, final long usedOffHeapStorageMemory, final long totalOnHeapStorageMemory, final long totalOffHeapStorageMemory) {
      this.host = host;
      this.port = port;
      this.cacheSize = cacheSize;
      this.numRunningTasks = numRunningTasks;
      this.usedOnHeapStorageMemory = usedOnHeapStorageMemory;
      this.usedOffHeapStorageMemory = usedOffHeapStorageMemory;
      this.totalOnHeapStorageMemory = totalOnHeapStorageMemory;
      this.totalOffHeapStorageMemory = totalOffHeapStorageMemory;
   }
}
