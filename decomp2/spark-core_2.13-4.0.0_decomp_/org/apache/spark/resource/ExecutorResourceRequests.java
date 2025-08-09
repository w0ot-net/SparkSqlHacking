package org.apache.spark.resource;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.network.util.JavaUtils;
import scala.collection.immutable.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea\u0001B\b\u0011\u0001eAQ\u0001\f\u0001\u0005\u00025Bq\u0001\r\u0001C\u0002\u0013%\u0011\u0007\u0003\u0004H\u0001\u0001\u0006IA\r\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001b\u0002!\tA\u0014\u0005\u0006%\u0002!\ta\u0015\u0005\u0006/\u0002!\t\u0001\u0017\u0005\u00065\u0002!\ta\u0017\u0005\u0006;\u0002!\tA\u0018\u0005\u0006A\u0002!\t!\u0019\u0005\u0006#\u0001!\tA\u001a\u0005\bc\u0002\t\n\u0011\"\u0001s\u0011\u001di\b!%A\u0005\u0002IDQA \u0001\u0005B}\u0014\u0001$\u0012=fGV$xN\u001d*fg>,(oY3SKF,Xm\u001d;t\u0015\t\t\"#\u0001\u0005sKN|WO]2f\u0015\t\u0019B#A\u0003ta\u0006\u00148N\u0003\u0002\u0016-\u00051\u0011\r]1dQ\u0016T\u0011aF\u0001\u0004_J<7\u0001A\n\u0004\u0001i\u0001\u0003CA\u000e\u001f\u001b\u0005a\"\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}a\"AB!osJ+g\r\u0005\u0002\"S9\u0011!e\n\b\u0003G\u0019j\u0011\u0001\n\u0006\u0003Ka\ta\u0001\u0010:p_Rt\u0014\"A\u000f\n\u0005!b\u0012a\u00029bG.\fw-Z\u0005\u0003U-\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000b\u000f\u0002\rqJg.\u001b;?)\u0005q\u0003CA\u0018\u0001\u001b\u0005\u0001\u0012AE0fq\u0016\u001cW\u000f^8s%\u0016\u001cx.\u001e:dKN,\u0012A\r\t\u0005gibD)D\u00015\u0015\t)d'\u0001\u0006d_:\u001cWO\u001d:f]RT!a\u000e\u001d\u0002\tU$\u0018\u000e\u001c\u0006\u0002s\u0005!!.\u0019<b\u0013\tYDGA\tD_:\u001cWO\u001d:f]RD\u0015m\u001d5NCB\u0004\"!P!\u000f\u0005yz\u0004CA\u0012\u001d\u0013\t\u0001E$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0005\u000e\u0013aa\u0015;sS:<'B\u0001!\u001d!\tyS)\u0003\u0002G!\t9R\t_3dkR|'OU3t_V\u00148-\u001a*fcV,7\u000f^\u0001\u0014?\u0016DXmY;u_J\u0014Vm]8ve\u000e,7\u000fI\u0001\te\u0016\fX/Z:ugV\t!\n\u0005\u0003>\u0017r\"\u0015B\u0001'D\u0005\ri\u0015\r]\u0001\re\u0016\fX/Z:ug*k\u0015\r]\u000b\u0002\u001fB!\u0001+\u0015\u001fE\u001b\u00051\u0014B\u0001'7\u0003\u0019iW-\\8ssR\u0011A+V\u0007\u0002\u0001!)aK\u0002a\u0001y\u00051\u0011-\\8v]R\fQb\u001c4g\u0011\u0016\f\u0007/T3n_JLHC\u0001+Z\u0011\u00151v\u00011\u0001=\u00039iW-\\8ss>3XM\u001d5fC\u0012$\"\u0001\u0016/\t\u000bYC\u0001\u0019\u0001\u001f\u0002\u001bAL8\u000f]1sW6+Wn\u001c:z)\t!v\fC\u0003W\u0013\u0001\u0007A(A\u0003d_J,7\u000f\u0006\u0002UE\")aK\u0003a\u0001GB\u00111\u0004Z\u0005\u0003Kr\u00111!\u00138u)\u0015!v-[7p\u0011\u0015A7\u00021\u0001=\u00031\u0011Xm]8ve\u000e,g*Y7f\u0011\u001516\u00021\u0001k!\tY2.\u0003\u0002m9\t!Aj\u001c8h\u0011\u001dq7\u0002%AA\u0002q\nq\u0002Z5tG>4XM]=TGJL\u0007\u000f\u001e\u0005\ba.\u0001\n\u00111\u0001=\u0003\u00191XM\u001c3pe\u0006\u0011\"/Z:pkJ\u001cW\r\n3fM\u0006,H\u000e\u001e\u00134+\u0005\u0019(F\u0001\u001fuW\u0005)\bC\u0001<|\u001b\u00059(B\u0001=z\u0003%)hn\u00195fG.,GM\u0003\u0002{9\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005q<(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u0011\"/Z:pkJ\u001cW\r\n3fM\u0006,H\u000e\u001e\u00135\u0003!!xn\u0015;sS:<G#\u0001\u001f)\u0007\u0001\t\u0019\u0001\u0005\u0003\u0002\u0006\u0005%QBAA\u0004\u0015\tQ(#\u0003\u0003\u0002\f\u0005\u001d!\u0001C#w_24\u0018N\\4)\u000b\u0001\ty!!\u0006\u0011\t\u0005\u0015\u0011\u0011C\u0005\u0005\u0003'\t9AA\u0003TS:\u001cW-\t\u0002\u0002\u0018\u0005)1GL\u0019/a\u0001"
)
public class ExecutorResourceRequests implements Serializable {
   private final ConcurrentHashMap _executorResources = new ConcurrentHashMap();

   private ConcurrentHashMap _executorResources() {
      return this._executorResources;
   }

   public Map requests() {
      return .MODULE$.ConcurrentMapHasAsScala(this._executorResources()).asScala().toMap(scala..less.colon.less..MODULE$.refl());
   }

   public java.util.Map requestsJMap() {
      return .MODULE$.MapHasAsJava(this.requests()).asJava();
   }

   public ExecutorResourceRequests memory(final String amount) {
      long amountMiB = JavaUtils.byteStringAsMb(amount);
      ExecutorResourceRequest req = new ExecutorResourceRequest(ResourceProfile$.MODULE$.MEMORY(), amountMiB, ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$3(), ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$4());
      this._executorResources().put(ResourceProfile$.MODULE$.MEMORY(), req);
      return this;
   }

   public ExecutorResourceRequests offHeapMemory(final String amount) {
      long amountMiB = JavaUtils.byteStringAsMb(amount);
      ExecutorResourceRequest req = new ExecutorResourceRequest(ResourceProfile$.MODULE$.OFFHEAP_MEM(), amountMiB, ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$3(), ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$4());
      this._executorResources().put(ResourceProfile$.MODULE$.OFFHEAP_MEM(), req);
      return this;
   }

   public ExecutorResourceRequests memoryOverhead(final String amount) {
      long amountMiB = JavaUtils.byteStringAsMb(amount);
      ExecutorResourceRequest req = new ExecutorResourceRequest(ResourceProfile$.MODULE$.OVERHEAD_MEM(), amountMiB, ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$3(), ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$4());
      this._executorResources().put(ResourceProfile$.MODULE$.OVERHEAD_MEM(), req);
      return this;
   }

   public ExecutorResourceRequests pysparkMemory(final String amount) {
      long amountMiB = JavaUtils.byteStringAsMb(amount);
      ExecutorResourceRequest req = new ExecutorResourceRequest(ResourceProfile$.MODULE$.PYSPARK_MEM(), amountMiB, ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$3(), ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$4());
      this._executorResources().put(ResourceProfile$.MODULE$.PYSPARK_MEM(), req);
      return this;
   }

   public ExecutorResourceRequests cores(final int amount) {
      ExecutorResourceRequest req = new ExecutorResourceRequest(ResourceProfile$.MODULE$.CORES(), (long)amount, ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$3(), ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$4());
      this._executorResources().put(ResourceProfile$.MODULE$.CORES(), req);
      return this;
   }

   public ExecutorResourceRequests resource(final String resourceName, final long amount, final String discoveryScript, final String vendor) {
      ExecutorResourceRequest req = new ExecutorResourceRequest(resourceName, amount, discoveryScript, vendor);
      this._executorResources().put(resourceName, req);
      return this;
   }

   public String resource$default$3() {
      return "";
   }

   public String resource$default$4() {
      return "";
   }

   public String toString() {
      return "Executor resource requests: " + this._executorResources();
   }
}
