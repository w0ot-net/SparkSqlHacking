package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I4AAE\n\u0001A!Aq\u0005\u0001BC\u0002\u0013\u0005\u0001\u0006\u0003\u00055\u0001\t\u0005\t\u0015!\u0003*\u0011!)\u0004A!b\u0001\n\u00031\u0004\u0002\u0003\u001e\u0001\u0005\u0003\u0005\u000b\u0011B\u001c\t\u0011m\u0002!Q1A\u0005\u0002YB\u0001\u0002\u0010\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\t{\u0001\u0011)\u0019!C\u0001m!Aa\b\u0001B\u0001B\u0003%q\u0007\u0003\u0005@\u0001\t\u0015\r\u0011\"\u0001A\u0011!!\u0005A!A!\u0002\u0013\t\u0005\u0002C#\u0001\u0005\u000b\u0007I\u0011\u0001!\t\u0011\u0019\u0003!\u0011!Q\u0001\n\u0005C\u0001b\u0012\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005\u0003\"A\u0011\n\u0001BC\u0002\u0013\u0005\u0001\t\u0003\u0005K\u0001\t\u0005\t\u0015!\u0003B\u0011\u0019Y\u0005\u0001\"\u0001\u001a\u0019\n\u0019\"\u000b\u0012#ECR\fG)[:ue&\u0014W\u000f^5p]*\u0011A#F\u0001\u0003mFR!AF\f\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u00193\u000511\u000f^1ukNT!AG\u000e\u0002\u000bM\u0004\u0018M]6\u000b\u0005qi\u0012AB1qC\u000eDWMC\u0001\u001f\u0003\ry'oZ\u0002\u0001'\t\u0001\u0011\u0005\u0005\u0002#K5\t1EC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13E\u0001\u0004B]f\u0014VMZ\u0001\bC\u0012$'/Z:t+\u0005I\u0003C\u0001\u00162\u001d\tYs\u0006\u0005\u0002-G5\tQF\u0003\u0002/?\u00051AH]8pizJ!\u0001M\u0012\u0002\rA\u0013X\rZ3g\u0013\t\u00114G\u0001\u0004TiJLgn\u001a\u0006\u0003a\r\n\u0001\"\u00193ee\u0016\u001c8\u000fI\u0001\u000b[\u0016lwN]=Vg\u0016$W#A\u001c\u0011\u0005\tB\u0014BA\u001d$\u0005\u0011auN\\4\u0002\u00175,Wn\u001c:z+N,G\rI\u0001\u0010[\u0016lwN]=SK6\f\u0017N\\5oO\u0006\u0001R.Z7pef\u0014V-\\1j]&tw\rI\u0001\tI&\u001c8.V:fI\u0006IA-[:l+N,G\rI\u0001\u0011_:DU-\u00199NK6|'/_+tK\u0012,\u0012!\u0011\t\u0004E\t;\u0014BA\"$\u0005\u0019y\u0005\u000f^5p]\u0006\trN\u001c%fCBlU-\\8ssV\u001bX\r\u001a\u0011\u0002#=4g\rS3ba6+Wn\u001c:z+N,G-\u0001\npM\u001aDU-\u00199NK6|'/_+tK\u0012\u0004\u0013!F8o\u0011\u0016\f\u0007/T3n_JL(+Z7bS:LgnZ\u0001\u0017_:DU-\u00199NK6|'/\u001f*f[\u0006Lg.\u001b8hA\u00051rN\u001a4IK\u0006\u0004X*Z7pef\u0014V-\\1j]&tw-A\fpM\u001aDU-\u00199NK6|'/\u001f*f[\u0006Lg.\u001b8hA\u00051A(\u001b8jiz\"\u0012\"T(Q#J\u001bFN\u001c9\u0011\u00059\u0003Q\"A\n\t\u000b\u001d\n\u0002\u0019A\u0015\t\u000bU\n\u0002\u0019A\u001c\t\u000bm\n\u0002\u0019A\u001c\t\u000bu\n\u0002\u0019A\u001c\t\u000b}\n\u0002\u0019A!)\tM+6\r\u001a\t\u0003-\u0006l\u0011a\u0016\u0006\u00031f\u000b!\"\u00198o_R\fG/[8o\u0015\tQ6,\u0001\u0005eCR\f'-\u001b8e\u0015\taV,A\u0004kC\u000e\\7o\u001c8\u000b\u0005y{\u0016!\u00034bgR,'\u000f_7m\u0015\u0005\u0001\u0017aA2p[&\u0011!m\u0016\u0002\u0010\u0015N|g\u000eR3tKJL\u0017\r\\5{K\u0006I1m\u001c8uK:$\u0018i]\u0012\u0002KB\u0011am[\u0007\u0002O*\u0011\u0001.[\u0001\u0005Y\u0006twMC\u0001k\u0003\u0011Q\u0017M^1\n\u0005e:\u0007\"B#\u0012\u0001\u0004\t\u0005\u0006\u00027VG\u0012DQaR\tA\u0002\u0005CCA\\+dI\")\u0011*\u0005a\u0001\u0003\"\"\u0001/V2e\u0001"
)
public class RDDDataDistribution {
   private final String address;
   private final long memoryUsed;
   private final long memoryRemaining;
   private final long diskUsed;
   private final Option onHeapMemoryUsed;
   private final Option offHeapMemoryUsed;
   private final Option onHeapMemoryRemaining;
   private final Option offHeapMemoryRemaining;

   public String address() {
      return this.address;
   }

   public long memoryUsed() {
      return this.memoryUsed;
   }

   public long memoryRemaining() {
      return this.memoryRemaining;
   }

   public long diskUsed() {
      return this.diskUsed;
   }

   public Option onHeapMemoryUsed() {
      return this.onHeapMemoryUsed;
   }

   public Option offHeapMemoryUsed() {
      return this.offHeapMemoryUsed;
   }

   public Option onHeapMemoryRemaining() {
      return this.onHeapMemoryRemaining;
   }

   public Option offHeapMemoryRemaining() {
      return this.offHeapMemoryRemaining;
   }

   public RDDDataDistribution(final String address, final long memoryUsed, final long memoryRemaining, final long diskUsed, @JsonDeserialize(contentAs = Long.class) final Option onHeapMemoryUsed, @JsonDeserialize(contentAs = Long.class) final Option offHeapMemoryUsed, @JsonDeserialize(contentAs = Long.class) final Option onHeapMemoryRemaining, @JsonDeserialize(contentAs = Long.class) final Option offHeapMemoryRemaining) {
      this.address = address;
      this.memoryUsed = memoryUsed;
      this.memoryRemaining = memoryRemaining;
      this.diskUsed = diskUsed;
      this.onHeapMemoryUsed = onHeapMemoryUsed;
      this.offHeapMemoryUsed = offHeapMemoryUsed;
      this.onHeapMemoryRemaining = onHeapMemoryRemaining;
      this.offHeapMemoryRemaining = offHeapMemoryRemaining;
   }
}
