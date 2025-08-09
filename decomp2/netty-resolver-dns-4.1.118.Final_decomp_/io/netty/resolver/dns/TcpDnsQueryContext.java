package io.netty.resolver.dns;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.handler.codec.dns.DefaultDnsQuery;
import io.netty.handler.codec.dns.DnsQuery;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.util.concurrent.Promise;
import java.net.InetSocketAddress;

final class TcpDnsQueryContext extends DnsQueryContext {
   TcpDnsQueryContext(Channel channel, InetSocketAddress nameServerAddr, DnsQueryContextManager queryContextManager, int maxPayLoadSize, boolean recursionDesired, long queryTimeoutMillis, DnsQuestion question, DnsRecord[] additionals, Promise promise) {
      super(channel, nameServerAddr, queryContextManager, maxPayLoadSize, recursionDesired, queryTimeoutMillis, question, additionals, promise, (Bootstrap)null, false);
   }

   protected DnsQuery newQuery(int id, InetSocketAddress nameServerAddr) {
      return new DefaultDnsQuery(id);
   }

   protected String protocol() {
      return "TCP";
   }
}
