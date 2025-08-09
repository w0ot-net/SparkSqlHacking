package org.apache.spark.status;

import org.apache.spark.status.api.v1.RDDDataDistribution;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%4A!\u0006\f\u0005?!Aa\u0005\u0001B\u0001B\u0003%q\u0005C\u0003,\u0001\u0011\u0005A\u0006C\u00040\u0001\t\u0007I\u0011\u0001\u0019\t\rq\u0002\u0001\u0015!\u00032\u0011\u001di\u0004\u00011A\u0005\u0002yBqA\u0011\u0001A\u0002\u0013\u00051\t\u0003\u0004J\u0001\u0001\u0006Ka\u0010\u0005\b\u0015\u0002\u0001\r\u0011\"\u0001?\u0011\u001dY\u0005\u00011A\u0005\u00021CaA\u0014\u0001!B\u0013y\u0004bB(\u0001\u0001\u0004%\tA\u0010\u0005\b!\u0002\u0001\r\u0011\"\u0001R\u0011\u0019\u0019\u0006\u0001)Q\u0005\u007f!9A\u000b\u0001a\u0001\n\u0003q\u0004bB+\u0001\u0001\u0004%\tA\u0016\u0005\u00071\u0002\u0001\u000b\u0015B \t\u000fe\u0003\u0001\u0019!C\u00015\"91\r\u0001a\u0001\n\u0003!\u0007B\u00024\u0001A\u0003&1\fC\u0003h\u0001\u0011\u0005\u0001NA\nMSZ,'\u000b\u0012#ESN$(/\u001b2vi&|gN\u0003\u0002\u00181\u000511\u000f^1ukNT!!\u0007\u000e\u0002\u000bM\u0004\u0018M]6\u000b\u0005ma\u0012AB1qC\u000eDWMC\u0001\u001e\u0003\ry'oZ\u0002\u0001'\t\u0001\u0001\u0005\u0005\u0002\"I5\t!EC\u0001$\u0003\u0015\u00198-\u00197b\u0013\t)#E\u0001\u0004B]f\u0014VMZ\u0001\u0005Kb,7\r\u0005\u0002)S5\ta#\u0003\u0002+-\taA*\u001b<f\u000bb,7-\u001e;pe\u00061A(\u001b8jiz\"\"!\f\u0018\u0011\u0005!\u0002\u0001\"\u0002\u0014\u0003\u0001\u00049\u0013AC3yK\u000e,Ho\u001c:JIV\t\u0011\u0007\u0005\u00023s9\u00111g\u000e\t\u0003i\tj\u0011!\u000e\u0006\u0003my\ta\u0001\u0010:p_Rt\u0014B\u0001\u001d#\u0003\u0019\u0001&/\u001a3fM&\u0011!h\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005a\u0012\u0013aC3yK\u000e,Ho\u001c:JI\u0002\n!\"\\3n_JLXk]3e+\u0005y\u0004CA\u0011A\u0013\t\t%E\u0001\u0003M_:<\u0017AD7f[>\u0014\u00180V:fI~#S-\u001d\u000b\u0003\t\u001e\u0003\"!I#\n\u0005\u0019\u0013#\u0001B+oSRDq\u0001\u0013\u0004\u0002\u0002\u0003\u0007q(A\u0002yIE\n1\"\\3n_JLXk]3eA\u0005AA-[:l+N,G-\u0001\u0007eSN\\Wk]3e?\u0012*\u0017\u000f\u0006\u0002E\u001b\"9\u0001*CA\u0001\u0002\u0004y\u0014!\u00033jg.,6/\u001a3!\u0003)yg\u000eS3baV\u001bX\rZ\u0001\u000f_:DU-\u00199Vg\u0016$w\fJ3r)\t!%\u000bC\u0004I\u0019\u0005\u0005\t\u0019A \u0002\u0017=t\u0007*Z1q+N,G\rI\u0001\f_\u001a4\u0007*Z1q+N,G-A\bpM\u001aDU-\u00199Vg\u0016$w\fJ3r)\t!u\u000bC\u0004I\u001f\u0005\u0005\t\u0019A \u0002\u0019=4g\rS3baV\u001bX\r\u001a\u0011\u0002\u00151\f7\u000f^+qI\u0006$X-F\u0001\\!\ta\u0016-D\u0001^\u0015\tqv,\u0001\u0002wc)\u0011\u0001MF\u0001\u0004CBL\u0017B\u00012^\u0005M\u0011F\t\u0012#bi\u0006$\u0015n\u001d;sS\n,H/[8o\u00039a\u0017m\u001d;Va\u0012\fG/Z0%KF$\"\u0001R3\t\u000f!\u0013\u0012\u0011!a\u00017\u0006YA.Y:u+B$\u0017\r^3!\u0003\u0015!x.\u00119j)\u0005Y\u0006"
)
public class LiveRDDDistribution {
   private final LiveExecutor exec;
   private final String executorId;
   private long memoryUsed;
   private long diskUsed;
   private long onHeapUsed;
   private long offHeapUsed;
   private RDDDataDistribution lastUpdate;

   public String executorId() {
      return this.executorId;
   }

   public long memoryUsed() {
      return this.memoryUsed;
   }

   public void memoryUsed_$eq(final long x$1) {
      this.memoryUsed = x$1;
   }

   public long diskUsed() {
      return this.diskUsed;
   }

   public void diskUsed_$eq(final long x$1) {
      this.diskUsed = x$1;
   }

   public long onHeapUsed() {
      return this.onHeapUsed;
   }

   public void onHeapUsed_$eq(final long x$1) {
      this.onHeapUsed = x$1;
   }

   public long offHeapUsed() {
      return this.offHeapUsed;
   }

   public void offHeapUsed_$eq(final long x$1) {
      this.offHeapUsed = x$1;
   }

   public RDDDataDistribution lastUpdate() {
      return this.lastUpdate;
   }

   public void lastUpdate_$eq(final RDDDataDistribution x$1) {
      this.lastUpdate = x$1;
   }

   public RDDDataDistribution toApi() {
      if (this.lastUpdate() == null) {
         this.lastUpdate_$eq(new RDDDataDistribution(Utils$.MODULE$.weakIntern(this.exec.hostPort() != null ? this.exec.hostPort() : this.exec.host()), this.memoryUsed(), this.exec.maxMemory() - this.exec.memoryUsed(), this.diskUsed(), (Option)(this.exec.hasMemoryInfo() ? new Some(BoxesRunTime.boxToLong(this.onHeapUsed())) : .MODULE$), (Option)(this.exec.hasMemoryInfo() ? new Some(BoxesRunTime.boxToLong(this.offHeapUsed())) : .MODULE$), (Option)(this.exec.hasMemoryInfo() ? new Some(BoxesRunTime.boxToLong(this.exec.totalOnHeap() - this.exec.usedOnHeap())) : .MODULE$), (Option)(this.exec.hasMemoryInfo() ? new Some(BoxesRunTime.boxToLong(this.exec.totalOffHeap() - this.exec.usedOffHeap())) : .MODULE$)));
      }

      return this.lastUpdate();
   }

   public LiveRDDDistribution(final LiveExecutor exec) {
      this.exec = exec;
      this.executorId = exec.executorId();
      this.memoryUsed = 0L;
      this.diskUsed = 0L;
      this.onHeapUsed = 0L;
      this.offHeapUsed = 0L;
      this.lastUpdate = null;
   }
}
