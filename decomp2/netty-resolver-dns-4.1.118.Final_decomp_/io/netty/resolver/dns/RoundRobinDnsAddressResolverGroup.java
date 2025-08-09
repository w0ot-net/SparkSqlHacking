package io.netty.resolver.dns;

import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.DatagramChannel;
import io.netty.resolver.AddressResolver;
import io.netty.resolver.NameResolver;
import io.netty.resolver.RoundRobinInetAddressResolver;
import java.net.InetAddress;

public class RoundRobinDnsAddressResolverGroup extends DnsAddressResolverGroup {
   public RoundRobinDnsAddressResolverGroup(DnsNameResolverBuilder dnsResolverBuilder) {
      super(dnsResolverBuilder);
   }

   public RoundRobinDnsAddressResolverGroup(Class channelType, DnsServerAddressStreamProvider nameServerProvider) {
      super(channelType, nameServerProvider);
   }

   public RoundRobinDnsAddressResolverGroup(ChannelFactory channelFactory, DnsServerAddressStreamProvider nameServerProvider) {
      super(channelFactory, nameServerProvider);
   }

   protected final AddressResolver newAddressResolver(EventLoop eventLoop, NameResolver resolver) throws Exception {
      return (new RoundRobinInetAddressResolver(eventLoop, resolver)).asAddressResolver();
   }
}
