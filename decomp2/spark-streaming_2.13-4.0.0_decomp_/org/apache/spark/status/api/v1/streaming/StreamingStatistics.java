package org.apache.spark.status.api.v1.streaming;

import java.util.Date;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u4AAH\u0010\u0001]!AQ\u0007\u0001BC\u0002\u0013\u0005a\u0007\u0003\u0005@\u0001\t\u0005\t\u0015!\u00038\u0011!\u0001\u0005A!b\u0001\n\u0003\t\u0005\u0002C#\u0001\u0005\u0003\u0005\u000b\u0011\u0002\"\t\u0011\u0019\u0003!Q1A\u0005\u0002\u001dC\u0001b\u0013\u0001\u0003\u0002\u0003\u0006I\u0001\u0013\u0005\t\u0019\u0002\u0011)\u0019!C\u0001\u000f\"AQ\n\u0001B\u0001B\u0003%\u0001\n\u0003\u0005O\u0001\t\u0015\r\u0011\"\u0001H\u0011!y\u0005A!A!\u0002\u0013A\u0005\u0002\u0003)\u0001\u0005\u000b\u0007I\u0011A!\t\u0011E\u0003!\u0011!Q\u0001\n\tC\u0001B\u0015\u0001\u0003\u0006\u0004%\t!\u0011\u0005\t'\u0002\u0011\t\u0011)A\u0005\u0005\"AA\u000b\u0001BC\u0002\u0013\u0005\u0011\t\u0003\u0005V\u0001\t\u0005\t\u0015!\u0003C\u0011!1\u0006A!b\u0001\n\u0003\t\u0005\u0002C,\u0001\u0005\u0003\u0005\u000b\u0011\u0002\"\t\u0011a\u0003!Q1A\u0005\u0002\u0005C\u0001\"\u0017\u0001\u0003\u0002\u0003\u0006IA\u0011\u0005\t5\u0002\u0011)\u0019!C\u00017\"A!\r\u0001B\u0001B\u0003%A\f\u0003\u0005d\u0001\t\u0015\r\u0011\"\u0001e\u0011!1\u0007A!A!\u0002\u0013)\u0007\u0002C4\u0001\u0005\u000b\u0007I\u0011\u00013\t\u0011!\u0004!\u0011!Q\u0001\n\u0015D\u0001\"\u001b\u0001\u0003\u0006\u0004%\t\u0001\u001a\u0005\tU\u0002\u0011\t\u0011)A\u0005K\"11\u000e\u0001C\u0001O1\u00141c\u0015;sK\u0006l\u0017N\\4Ti\u0006$\u0018n\u001d;jGNT!\u0001I\u0011\u0002\u0013M$(/Z1nS:<'B\u0001\u0012$\u0003\t1\u0018G\u0003\u0002%K\u0005\u0019\u0011\r]5\u000b\u0005\u0019:\u0013AB:uCR,8O\u0003\u0002)S\u0005)1\u000f]1sW*\u0011!fK\u0001\u0007CB\f7\r[3\u000b\u00031\n1a\u001c:h\u0007\u0001\u0019\"\u0001A\u0018\u0011\u0005A\u001aT\"A\u0019\u000b\u0003I\nQa]2bY\u0006L!\u0001N\u0019\u0003\r\u0005s\u0017PU3g\u0003%\u0019H/\u0019:u)&lW-F\u00018!\tAT(D\u0001:\u0015\tQ4(\u0001\u0003vi&d'\"\u0001\u001f\u0002\t)\fg/Y\u0005\u0003}e\u0012A\u0001R1uK\u0006Q1\u000f^1siRKW.\u001a\u0011\u0002\u001b\t\fGo\u00195EkJ\fG/[8o+\u0005\u0011\u0005C\u0001\u0019D\u0013\t!\u0015G\u0001\u0003M_:<\u0017A\u00042bi\u000eDG)\u001e:bi&|g\u000eI\u0001\r]Vl'+Z2fSZ,'o]\u000b\u0002\u0011B\u0011\u0001'S\u0005\u0003\u0015F\u00121!\u00138u\u00035qW/\u001c*fG\u0016Lg/\u001a:tA\u0005\u0011b.^7BGRLg/\u001a*fG\u0016Lg/\u001a:t\u0003MqW/\\!di&4XMU3dK&4XM]:!\u0003QqW/\\%oC\u000e$\u0018N^3SK\u000e,\u0017N^3sg\u0006)b.^7J]\u0006\u001cG/\u001b<f%\u0016\u001cW-\u001b<feN\u0004\u0013\u0001\u00078v[R{G/\u00197D_6\u0004H.\u001a;fI\n\u000bGo\u00195fg\u0006Ib.^7U_R\fGnQ8na2,G/\u001a3CCR\u001c\u0007.Z:!\u0003mqW/\u001c*fi\u0006Lg.\u001a3D_6\u0004H.\u001a;fI\n\u000bGo\u00195fg\u0006ab.^7SKR\f\u0017N\\3e\u0007>l\u0007\u000f\\3uK\u0012\u0014\u0015\r^2iKN\u0004\u0013\u0001\u00058v[\u0006\u001bG/\u001b<f\u0005\u0006$8\r[3t\u0003EqW/\\!di&4XMQ1uG\",7\u000fI\u0001\u0014]Vl\u0007K]8dKN\u001cX\r\u001a*fG>\u0014Hm]\u0001\u0015]Vl\u0007K]8dKN\u001cX\r\u001a*fG>\u0014Hm\u001d\u0011\u0002%9,XNU3dK&4X\r\u001a*fG>\u0014Hm]\u0001\u0014]Vl'+Z2fSZ,GMU3d_J$7\u000fI\u0001\rCZ<\u0017J\u001c9viJ\u000bG/Z\u000b\u00029B\u0019\u0001'X0\n\u0005y\u000b$AB(qi&|g\u000e\u0005\u00021A&\u0011\u0011-\r\u0002\u0007\t>,(\r\\3\u0002\u001b\u00054x-\u00138qkR\u0014\u0016\r^3!\u0003I\tgoZ*dQ\u0016$W\u000f\\5oO\u0012+G.Y=\u0016\u0003\u0015\u00042\u0001M/C\u0003M\tgoZ*dQ\u0016$W\u000f\\5oO\u0012+G.Y=!\u0003E\tgo\u001a)s_\u000e,7o]5oORKW.Z\u0001\u0013CZ<\u0007K]8dKN\u001c\u0018N\\4US6,\u0007%A\u0007bm\u001e$v\u000e^1m\t\u0016d\u0017-_\u0001\u000fCZ<Gk\u001c;bY\u0012+G.Y=!\u0003\u0019a\u0014N\\5u}QyQn\u001c9reN$XO^<ysj\\H\u0010\u0005\u0002o\u00015\tq\u0004C\u00036;\u0001\u0007q\u0007C\u0003A;\u0001\u0007!\tC\u0003G;\u0001\u0007\u0001\nC\u0003M;\u0001\u0007\u0001\nC\u0003O;\u0001\u0007\u0001\nC\u0003Q;\u0001\u0007!\tC\u0003S;\u0001\u0007!\tC\u0003U;\u0001\u0007!\tC\u0003W;\u0001\u0007!\tC\u0003Y;\u0001\u0007!\tC\u0003[;\u0001\u0007A\fC\u0003d;\u0001\u0007Q\rC\u0003h;\u0001\u0007Q\rC\u0003j;\u0001\u0007Q\r"
)
public class StreamingStatistics {
   private final Date startTime;
   private final long batchDuration;
   private final int numReceivers;
   private final int numActiveReceivers;
   private final int numInactiveReceivers;
   private final long numTotalCompletedBatches;
   private final long numRetainedCompletedBatches;
   private final long numActiveBatches;
   private final long numProcessedRecords;
   private final long numReceivedRecords;
   private final Option avgInputRate;
   private final Option avgSchedulingDelay;
   private final Option avgProcessingTime;
   private final Option avgTotalDelay;

   public Date startTime() {
      return this.startTime;
   }

   public long batchDuration() {
      return this.batchDuration;
   }

   public int numReceivers() {
      return this.numReceivers;
   }

   public int numActiveReceivers() {
      return this.numActiveReceivers;
   }

   public int numInactiveReceivers() {
      return this.numInactiveReceivers;
   }

   public long numTotalCompletedBatches() {
      return this.numTotalCompletedBatches;
   }

   public long numRetainedCompletedBatches() {
      return this.numRetainedCompletedBatches;
   }

   public long numActiveBatches() {
      return this.numActiveBatches;
   }

   public long numProcessedRecords() {
      return this.numProcessedRecords;
   }

   public long numReceivedRecords() {
      return this.numReceivedRecords;
   }

   public Option avgInputRate() {
      return this.avgInputRate;
   }

   public Option avgSchedulingDelay() {
      return this.avgSchedulingDelay;
   }

   public Option avgProcessingTime() {
      return this.avgProcessingTime;
   }

   public Option avgTotalDelay() {
      return this.avgTotalDelay;
   }

   public StreamingStatistics(final Date startTime, final long batchDuration, final int numReceivers, final int numActiveReceivers, final int numInactiveReceivers, final long numTotalCompletedBatches, final long numRetainedCompletedBatches, final long numActiveBatches, final long numProcessedRecords, final long numReceivedRecords, final Option avgInputRate, final Option avgSchedulingDelay, final Option avgProcessingTime, final Option avgTotalDelay) {
      this.startTime = startTime;
      this.batchDuration = batchDuration;
      this.numReceivers = numReceivers;
      this.numActiveReceivers = numActiveReceivers;
      this.numInactiveReceivers = numInactiveReceivers;
      this.numTotalCompletedBatches = numTotalCompletedBatches;
      this.numRetainedCompletedBatches = numRetainedCompletedBatches;
      this.numActiveBatches = numActiveBatches;
      this.numProcessedRecords = numProcessedRecords;
      this.numReceivedRecords = numReceivedRecords;
      this.avgInputRate = avgInputRate;
      this.avgSchedulingDelay = avgSchedulingDelay;
      this.avgProcessingTime = avgProcessingTime;
      this.avgTotalDelay = avgTotalDelay;
   }
}
