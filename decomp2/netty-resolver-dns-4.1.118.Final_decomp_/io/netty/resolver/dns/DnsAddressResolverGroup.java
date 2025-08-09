package io.netty.resolver.dns;

import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.DatagramChannel;
import io.netty.resolver.AddressResolver;
import io.netty.resolver.AddressResolverGroup;
import io.netty.resolver.InetSocketAddressResolver;
import io.netty.resolver.NameResolver;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.net.InetAddress;
import java.util.concurrent.ConcurrentMap;

public class DnsAddressResolverGroup extends AddressResolverGroup {
   private final DnsNameResolverBuilder dnsResolverBuilder;
   private final ConcurrentMap resolvesInProgress = PlatformDependent.newConcurrentHashMap();
   private final ConcurrentMap resolveAllsInProgress = PlatformDependent.newConcurrentHashMap();

   public DnsAddressResolverGroup(DnsNameResolverBuilder dnsResolverBuilder) {
      this.dnsResolverBuilder = withSharedCaches(dnsResolverBuilder.copy());
   }

   public DnsAddressResolverGroup(Class channelType, DnsServerAddressStreamProvider nameServerProvider) {
      this.dnsResolverBuilder = withSharedCaches(new DnsNameResolverBuilder());
      this.dnsResolverBuilder.datagramChannelType(channelType).nameServerProvider(nameServerProvider);
   }

   public DnsAddressResolverGroup(ChannelFactory channelFactory, DnsServerAddressStreamProvider nameServerProvider) {
      this.dnsResolverBuilder = withSharedCaches(new DnsNameResolverBuilder());
      this.dnsResolverBuilder.datagramChannelFactory(channelFactory).nameServerProvider(nameServerProvider);
   }

   private static DnsNameResolverBuilder withSharedCaches(DnsNameResolverBuilder dnsResolverBuilder) {
      return dnsResolverBuilder.resolveCache(dnsResolverBuilder.getOrNewCache()).cnameCache(dnsResolverBuilder.getOrNewCnameCache()).authoritativeDnsServerCache(dnsResolverBuilder.getOrNewAuthoritativeDnsServerCache());
   }

   protected final AddressResolver newResolver(EventExecutor executor) throws Exception {
      if (!(executor instanceof EventLoop)) {
         throw new IllegalStateException("unsupported executor type: " + StringUtil.simpleClassName(executor) + " (expected: " + StringUtil.simpleClassName(EventLoop.class));
      } else {
         EventLoop loop = this.dnsResolverBuilder.eventLoop;
         return this.newResolver(loop == null ? (EventLoop)executor : loop, this.dnsResolverBuilder.datagramChannelFactory(), this.dnsResolverBuilder.nameServerProvider());
      }
   }

   /** @deprecated */
   @Deprecated
   protected AddressResolver newResolver(EventLoop eventLoop, ChannelFactory channelFactory, DnsServerAddressStreamProvider nameServerProvider) throws Exception {
      NameResolver<InetAddress> resolver = new InflightNameResolver(eventLoop, this.newNameResolver(eventLoop, channelFactory, nameServerProvider), this.resolvesInProgress, this.resolveAllsInProgress);
      return this.newAddressResolver(eventLoop, resolver);
   }

   protected NameResolver newNameResolver(EventLoop eventLoop, ChannelFactory channelFactory, DnsServerAddressStreamProvider nameServerProvider) throws Exception {
      DnsNameResolverBuilder builder = this.dnsResolverBuilder.copy();
      return builder.eventLoop(eventLoop).datagramChannelFactory(channelFactory).nameServerProvider(nameServerProvider).build();
   }

   protected AddressResolver newAddressResolver(EventLoop eventLoop, NameResolver resolver) throws Exception {
      return new InetSocketAddressResolver(eventLoop, resolver);
   }
}
