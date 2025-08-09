package io.netty.resolver.dns;

import io.netty.util.concurrent.FastThreadLocal;
import java.net.InetSocketAddress;

final class ThreadLocalNameServerAddressStream implements DnsServerAddressStream {
   private final String hostname;
   private final DnsServerAddressStreamProvider dnsServerAddressStreamProvider;
   private final FastThreadLocal threadLocal;

   ThreadLocalNameServerAddressStream(DnsServerAddressStreamProvider dnsServerAddressStreamProvider) {
      this(dnsServerAddressStreamProvider, "");
   }

   ThreadLocalNameServerAddressStream(DnsServerAddressStreamProvider dnsServerAddressStreamProvider, String hostname) {
      this.threadLocal = new FastThreadLocal() {
         protected DnsServerAddressStream initialValue() {
            return ThreadLocalNameServerAddressStream.this.dnsServerAddressStreamProvider.nameServerAddressStream(ThreadLocalNameServerAddressStream.this.hostname);
         }
      };
      this.dnsServerAddressStreamProvider = dnsServerAddressStreamProvider;
      this.hostname = hostname;
   }

   public InetSocketAddress next() {
      return ((DnsServerAddressStream)this.threadLocal.get()).next();
   }

   public DnsServerAddressStream duplicate() {
      return new ThreadLocalNameServerAddressStream(this.dnsServerAddressStreamProvider, this.hostname);
   }

   public int size() {
      return ((DnsServerAddressStream)this.threadLocal.get()).size();
   }
}
