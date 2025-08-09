package io.netty.resolver.dns;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

final class RotationalDnsServerAddresses extends DefaultDnsServerAddresses {
   private static final AtomicIntegerFieldUpdater startIdxUpdater = AtomicIntegerFieldUpdater.newUpdater(RotationalDnsServerAddresses.class, "startIdx");
   private volatile int startIdx;

   RotationalDnsServerAddresses(List addresses) {
      super("rotational", addresses);
   }

   public DnsServerAddressStream stream() {
      int curStartIdx;
      int nextStartIdx;
      do {
         curStartIdx = this.startIdx;
         nextStartIdx = curStartIdx + 1;
         if (nextStartIdx >= this.addresses.size()) {
            nextStartIdx = 0;
         }
      } while(!startIdxUpdater.compareAndSet(this, curStartIdx, nextStartIdx));

      return new SequentialDnsServerAddressStream(this.addresses, curStartIdx);
   }
}
