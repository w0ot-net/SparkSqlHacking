package io.netty.resolver.dns;

import java.net.InetSocketAddress;

public interface DnsServerResponseFeedbackAddressStream extends DnsServerAddressStream {
   void feedbackSuccess(InetSocketAddress var1, long var2);

   void feedbackFailure(InetSocketAddress var1, Throwable var2, long var3);
}
