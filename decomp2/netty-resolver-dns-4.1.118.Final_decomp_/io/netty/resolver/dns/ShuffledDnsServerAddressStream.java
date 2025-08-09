package io.netty.resolver.dns;

import io.netty.util.internal.PlatformDependent;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;

final class ShuffledDnsServerAddressStream implements DnsServerAddressStream {
   private final List addresses;
   private int i;

   ShuffledDnsServerAddressStream(List addresses) {
      this.addresses = addresses;
      this.shuffle();
   }

   private ShuffledDnsServerAddressStream(List addresses, int startIdx) {
      this.addresses = addresses;
      this.i = startIdx;
   }

   private void shuffle() {
      Collections.shuffle(this.addresses, PlatformDependent.threadLocalRandom());
   }

   public InetSocketAddress next() {
      int i = this.i;
      InetSocketAddress next = (InetSocketAddress)this.addresses.get(i);
      ++i;
      if (i < this.addresses.size()) {
         this.i = i;
      } else {
         this.i = 0;
         this.shuffle();
      }

      return next;
   }

   public int size() {
      return this.addresses.size();
   }

   public ShuffledDnsServerAddressStream duplicate() {
      return new ShuffledDnsServerAddressStream(this.addresses, this.i);
   }

   public String toString() {
      return SequentialDnsServerAddressStream.toString("shuffled", this.i, this.addresses);
   }
}
