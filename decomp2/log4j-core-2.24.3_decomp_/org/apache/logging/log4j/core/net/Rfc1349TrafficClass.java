package org.apache.logging.log4j.core.net;

public enum Rfc1349TrafficClass {
   IPTOS_NORMAL(0),
   IPTOS_LOWCOST(2),
   IPTOS_LOWDELAY(16),
   IPTOS_RELIABILITY(4),
   IPTOS_THROUGHPUT(8);

   private final int trafficClass;

   private Rfc1349TrafficClass(final int trafficClass) {
      this.trafficClass = trafficClass;
   }

   public int value() {
      return this.trafficClass;
   }

   // $FF: synthetic method
   private static Rfc1349TrafficClass[] $values() {
      return new Rfc1349TrafficClass[]{IPTOS_NORMAL, IPTOS_LOWCOST, IPTOS_LOWDELAY, IPTOS_RELIABILITY, IPTOS_THROUGHPUT};
   }
}
