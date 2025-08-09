package org.apache.spark.status.api.v1;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4A\u0001F\u000b\u0001E!A\u0011\u0006\u0001BC\u0002\u0013\u0005!\u0006\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003,\u0011!y\u0003A!b\u0001\n\u0003\u0001\u0004\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011B\u0019\t\u0011u\u0002!Q1A\u0005\u0002)B\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\t\u007f\u0001\u0011)\u0019!C\u0001U!A\u0001\t\u0001B\u0001B\u0003%1\u0006\u0003\u0005B\u0001\t\u0015\r\u0011\"\u00011\u0011!\u0011\u0005A!A!\u0002\u0013\t\u0004\u0002C\"\u0001\u0005\u000b\u0007I\u0011\u0001#\t\u0011!\u0003!\u0011!Q\u0001\n\u0015C\u0001\"\u0013\u0001\u0003\u0006\u0004%\t\u0001\u0012\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005\u000b\"A1\n\u0001BC\u0002\u0013\u0005A\n\u0003\u0005[\u0001\t\u0005\t\u0015!\u0003N\u0011!Y\u0006A!b\u0001\n\u0003a\u0006\u0002\u00032\u0001\u0005\u0003\u0005\u000b\u0011B/\t\r\r\u0004A\u0011A\u000ee\u00059\u0011F\tR*u_J\fw-Z%oM>T!AF\f\u0002\u0005Y\f$B\u0001\r\u001a\u0003\r\t\u0007/\u001b\u0006\u00035m\taa\u001d;biV\u001c(B\u0001\u000f\u001e\u0003\u0015\u0019\b/\u0019:l\u0015\tqr$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002A\u0005\u0019qN]4\u0004\u0001M\u0011\u0001a\t\t\u0003I\u001dj\u0011!\n\u0006\u0002M\u0005)1oY1mC&\u0011\u0001&\n\u0002\u0007\u0003:L(+\u001a4\u0002\u0005%$W#A\u0016\u0011\u0005\u0011b\u0013BA\u0017&\u0005\rIe\u000e^\u0001\u0004S\u0012\u0004\u0013\u0001\u00028b[\u0016,\u0012!\r\t\u0003eer!aM\u001c\u0011\u0005Q*S\"A\u001b\u000b\u0005Y\n\u0013A\u0002\u001fs_>$h(\u0003\u00029K\u00051\u0001K]3eK\u001aL!AO\u001e\u0003\rM#(/\u001b8h\u0015\tAT%A\u0003oC6,\u0007%A\u0007ok6\u0004\u0016M\u001d;ji&|gn]\u0001\u000f]Vl\u0007+\u0019:uSRLwN\\:!\u0003MqW/\\\"bG\",G\rU1si&$\u0018n\u001c8t\u0003QqW/\\\"bG\",G\rU1si&$\u0018n\u001c8tA\u0005a1\u000f^8sC\u001e,G*\u001a<fY\u0006i1\u000f^8sC\u001e,G*\u001a<fY\u0002\n!\"\\3n_JLXk]3e+\u0005)\u0005C\u0001\u0013G\u0013\t9UE\u0001\u0003M_:<\u0017aC7f[>\u0014\u00180V:fI\u0002\n\u0001\u0002Z5tWV\u001bX\rZ\u0001\nI&\u001c8.V:fI\u0002\n\u0001\u0003Z1uC\u0012K7\u000f\u001e:jEV$\u0018n\u001c8\u0016\u00035\u00032\u0001\n(Q\u0013\tyUE\u0001\u0004PaRLwN\u001c\t\u0004#R3V\"\u0001*\u000b\u0005M+\u0013AC2pY2,7\r^5p]&\u0011QK\u0015\u0002\u0004'\u0016\f\bCA,Y\u001b\u0005)\u0012BA-\u0016\u0005M\u0011F\t\u0012#bi\u0006$\u0015n\u001d;sS\n,H/[8o\u0003E!\u0017\r^1ESN$(/\u001b2vi&|g\u000eI\u0001\u000ba\u0006\u0014H/\u001b;j_:\u001cX#A/\u0011\u0007\u0011re\fE\u0002R)~\u0003\"a\u00161\n\u0005\u0005,\"\u0001\u0005*E\tB\u000b'\u000f^5uS>t\u0017J\u001c4p\u0003-\u0001\u0018M\u001d;ji&|gn\u001d\u0011\u0002\rqJg.\u001b;?)))gm\u001a5jU.dWN\u001c\t\u0003/\u0002AQ!K\nA\u0002-BQaL\nA\u0002EBQ!P\nA\u0002-BQaP\nA\u0002-BQ!Q\nA\u0002EBQaQ\nA\u0002\u0015CQ!S\nA\u0002\u0015CQaS\nA\u00025CQaW\nA\u0002u\u0003"
)
public class RDDStorageInfo {
   private final int id;
   private final String name;
   private final int numPartitions;
   private final int numCachedPartitions;
   private final String storageLevel;
   private final long memoryUsed;
   private final long diskUsed;
   private final Option dataDistribution;
   private final Option partitions;

   public int id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }

   public int numPartitions() {
      return this.numPartitions;
   }

   public int numCachedPartitions() {
      return this.numCachedPartitions;
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

   public Option dataDistribution() {
      return this.dataDistribution;
   }

   public Option partitions() {
      return this.partitions;
   }

   public RDDStorageInfo(final int id, final String name, final int numPartitions, final int numCachedPartitions, final String storageLevel, final long memoryUsed, final long diskUsed, final Option dataDistribution, final Option partitions) {
      this.id = id;
      this.name = name;
      this.numPartitions = numPartitions;
      this.numCachedPartitions = numCachedPartitions;
      this.storageLevel = storageLevel;
      this.memoryUsed = memoryUsed;
      this.diskUsed = diskUsed;
      this.dataDistribution = dataDistribution;
      this.partitions = partitions;
   }
}
