package org.apache.spark.metrics;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import javax.management.ObjectName;
import org.apache.spark.memory.MemoryManager;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153aAB\u0004\u0002\u0002%y\u0001\u0002\u0003\u000e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u000f\t\u000b\u001d\u0002A\u0011\u0001\u0015\t\u000f-\u0002!\u0019!C\u0005Y!1q\u0007\u0001Q\u0001\n5Ba\u0001\u000f\u0001\u0005B%I$aF'CK\u0006tW\t_3dkR|'/T3ue&\u001cG+\u001f9f\u0015\tA\u0011\"A\u0004nKR\u0014\u0018nY:\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c2\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0011q\u0003G\u0007\u0002\u000f%\u0011\u0011d\u0002\u0002\u001e'&tw\r\\3WC2,X-\u0012=fGV$xN]'fiJL7\rV=qK\u0006IQNQ3b]:\u000bW.Z\u0002\u0001!\tiBE\u0004\u0002\u001fEA\u0011qDE\u0007\u0002A)\u0011\u0011eG\u0001\u0007yI|w\u000e\u001e \n\u0005\r\u0012\u0012A\u0002)sK\u0012,g-\u0003\u0002&M\t11\u000b\u001e:j]\u001eT!a\t\n\u0002\rqJg.\u001b;?)\tI#\u0006\u0005\u0002\u0018\u0001!)!D\u0001a\u00019\u0005!!-Z1o+\u0005i\u0003C\u0001\u00186\u001b\u0005y#B\u0001\u00192\u0003)i\u0017M\\1hK6,g\u000e\u001e\u0006\u0003eM\nA\u0001\\1oO*\tA'\u0001\u0003kCZ\f\u0017B\u0001\u001c0\u0005A\u0011UO\u001a4feB{w\u000e\\'Y\u0005\u0016\fg.A\u0003cK\u0006t\u0007%\u0001\bhKRlU\r\u001e:jGZ\u000bG.^3\u0015\u0005ij\u0004CA\t<\u0013\ta$C\u0001\u0003M_:<\u0007\"\u0002 \u0006\u0001\u0004y\u0014!D7f[>\u0014\u00180T1oC\u001e,'\u000f\u0005\u0002A\u00076\t\u0011I\u0003\u0002C\u0013\u00051Q.Z7pefL!\u0001R!\u0003\u001b5+Wn\u001c:z\u001b\u0006t\u0017mZ3s\u0001"
)
public abstract class MBeanExecutorMetricType implements SingleValueExecutorMetricType {
   private final BufferPoolMXBean bean;

   public Seq names() {
      return SingleValueExecutorMetricType.names$(this);
   }

   public long[] getMetricValues(final MemoryManager memoryManager) {
      return SingleValueExecutorMetricType.getMetricValues$(this, memoryManager);
   }

   private BufferPoolMXBean bean() {
      return this.bean;
   }

   public long getMetricValue(final MemoryManager memoryManager) {
      return this.bean().getMemoryUsed();
   }

   public MBeanExecutorMetricType(final String mBeanName) {
      SingleValueExecutorMetricType.$init$(this);
      this.bean = (BufferPoolMXBean)ManagementFactory.newPlatformMXBeanProxy(ManagementFactory.getPlatformMBeanServer(), (new ObjectName(mBeanName)).toString(), BufferPoolMXBean.class);
   }
}
