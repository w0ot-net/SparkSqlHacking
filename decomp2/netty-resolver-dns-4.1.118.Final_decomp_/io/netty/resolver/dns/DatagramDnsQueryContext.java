package io.netty.resolver.dns;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.handler.codec.dns.DatagramDnsQuery;
import io.netty.handler.codec.dns.DnsQuery;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.util.concurrent.Promise;
import java.net.InetSocketAddress;

final class DatagramDnsQueryContext extends DnsQueryContext {
   DatagramDnsQueryContext(Channel channel, InetSocketAddress nameServerAddr, DnsQueryContextManager queryContextManager, int maxPayLoadSize, boolean recursionDesired, long queryTimeoutMillis, DnsQuestion question, DnsRecord[] additionals, Promise promise, Bootstrap socketBootstrap, boolean retryWithTcpOnTimeout) {
      super(channel, nameServerAddr, queryContextManager, maxPayLoadSize, recursionDesired, queryTimeoutMillis, question, additionals, promise, socketBootstrap, retryWithTcpOnTimeout);
   }

   protected DnsQuery newQuery(int id, InetSocketAddress nameServerAddr) {
      return new DatagramDnsQuery((InetSocketAddress)null, nameServerAddr, id);
   }

   protected String protocol() {
      return "UDP";
   }
}
