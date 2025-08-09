package io.netty.resolver.dns;

import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoop;
import io.netty.channel.ReflectiveChannelFactory;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.SocketChannel;
import io.netty.resolver.HostsFileEntriesResolver;
import io.netty.resolver.ResolvedAddressTypes;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DnsNameResolverBuilder {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(DnsNameResolverBuilder.class);
   static final SocketAddress DEFAULT_LOCAL_ADDRESS = new InetSocketAddress(0);
   volatile EventLoop eventLoop;
   private ChannelFactory datagramChannelFactory;
   private ChannelFactory socketChannelFactory;
   private boolean retryOnTimeout;
   private DnsCache resolveCache;
   private DnsCnameCache cnameCache;
   private AuthoritativeDnsServerCache authoritativeDnsServerCache;
   private SocketAddress localAddress;
   private Integer minTtl;
   private Integer maxTtl;
   private Integer negativeTtl;
   private long queryTimeoutMillis;
   private ResolvedAddressTypes resolvedAddressTypes;
   private boolean completeOncePreferredResolved;
   private boolean recursionDesired;
   private int maxQueriesPerResolve;
   private boolean traceEnabled;
   private int maxPayloadSize;
   private boolean optResourceEnabled;
   private HostsFileEntriesResolver hostsFileEntriesResolver;
   private DnsServerAddressStreamProvider dnsServerAddressStreamProvider;
   private DnsServerAddressStream queryDnsServerAddressStream;
   private DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory;
   private String[] searchDomains;
   private int ndots;
   private boolean decodeIdn;
   private int maxNumConsolidation;
   private DnsNameResolverChannelStrategy datagramChannelStrategy;

   public DnsNameResolverBuilder() {
      this.localAddress = DEFAULT_LOCAL_ADDRESS;
      this.queryTimeoutMillis = -1L;
      this.resolvedAddressTypes = DnsNameResolver.DEFAULT_RESOLVE_ADDRESS_TYPES;
      this.recursionDesired = true;
      this.maxQueriesPerResolve = -1;
      this.maxPayloadSize = 4096;
      this.optResourceEnabled = true;
      this.hostsFileEntriesResolver = HostsFileEntriesResolver.DEFAULT;
      this.dnsServerAddressStreamProvider = DnsServerAddressStreamProviders.platformDefault();
      this.dnsQueryLifecycleObserverFactory = NoopDnsQueryLifecycleObserverFactory.INSTANCE;
      this.ndots = -1;
      this.decodeIdn = true;
      this.datagramChannelStrategy = DnsNameResolverChannelStrategy.ChannelPerResolver;
   }

   public DnsNameResolverBuilder(EventLoop eventLoop) {
      this.localAddress = DEFAULT_LOCAL_ADDRESS;
      this.queryTimeoutMillis = -1L;
      this.resolvedAddressTypes = DnsNameResolver.DEFAULT_RESOLVE_ADDRESS_TYPES;
      this.recursionDesired = true;
      this.maxQueriesPerResolve = -1;
      this.maxPayloadSize = 4096;
      this.optResourceEnabled = true;
      this.hostsFileEntriesResolver = HostsFileEntriesResolver.DEFAULT;
      this.dnsServerAddressStreamProvider = DnsServerAddressStreamProviders.platformDefault();
      this.dnsQueryLifecycleObserverFactory = NoopDnsQueryLifecycleObserverFactory.INSTANCE;
      this.ndots = -1;
      this.decodeIdn = true;
      this.datagramChannelStrategy = DnsNameResolverChannelStrategy.ChannelPerResolver;
      this.eventLoop(eventLoop);
   }

   public DnsNameResolverBuilder eventLoop(EventLoop eventLoop) {
      this.eventLoop = eventLoop;
      return this;
   }

   /** @deprecated */
   @Deprecated
   protected ChannelFactory channelFactory() {
      return this.datagramChannelFactory;
   }

   ChannelFactory datagramChannelFactory() {
      return this.datagramChannelFactory;
   }

   /** @deprecated */
   @Deprecated
   public DnsNameResolverBuilder channelFactory(ChannelFactory datagramChannelFactory) {
      this.datagramChannelFactory(datagramChannelFactory);
      return this;
   }

   public DnsNameResolverBuilder datagramChannelFactory(ChannelFactory datagramChannelFactory) {
      this.datagramChannelFactory = datagramChannelFactory;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public DnsNameResolverBuilder channelType(Class channelType) {
      return this.datagramChannelFactory(new ReflectiveChannelFactory(channelType));
   }

   public DnsNameResolverBuilder datagramChannelType(Class channelType) {
      return this.datagramChannelFactory(new ReflectiveChannelFactory(channelType));
   }

   public DnsNameResolverBuilder socketChannelFactory(ChannelFactory channelFactory) {
      return this.socketChannelFactory(channelFactory, false);
   }

   public DnsNameResolverBuilder socketChannelType(Class channelType) {
      return this.socketChannelType(channelType, false);
   }

   public DnsNameResolverBuilder socketChannelFactory(ChannelFactory channelFactory, boolean retryOnTimeout) {
      this.socketChannelFactory = channelFactory;
      this.retryOnTimeout = retryOnTimeout;
      return this;
   }

   public DnsNameResolverBuilder socketChannelType(Class channelType, boolean retryOnTimeout) {
      return channelType == null ? this.socketChannelFactory((ChannelFactory)null, retryOnTimeout) : this.socketChannelFactory(new ReflectiveChannelFactory(channelType), retryOnTimeout);
   }

   public DnsNameResolverBuilder resolveCache(DnsCache resolveCache) {
      this.resolveCache = resolveCache;
      return this;
   }

   public DnsNameResolverBuilder cnameCache(DnsCnameCache cnameCache) {
      this.cnameCache = cnameCache;
      return this;
   }

   public DnsNameResolverBuilder dnsQueryLifecycleObserverFactory(DnsQueryLifecycleObserverFactory lifecycleObserverFactory) {
      this.dnsQueryLifecycleObserverFactory = (DnsQueryLifecycleObserverFactory)ObjectUtil.checkNotNull(lifecycleObserverFactory, "lifecycleObserverFactory");
      return this;
   }

   /** @deprecated */
   @Deprecated
   public DnsNameResolverBuilder authoritativeDnsServerCache(DnsCache authoritativeDnsServerCache) {
      this.authoritativeDnsServerCache = new AuthoritativeDnsServerCacheAdapter(authoritativeDnsServerCache);
      return this;
   }

   public DnsNameResolverBuilder authoritativeDnsServerCache(AuthoritativeDnsServerCache authoritativeDnsServerCache) {
      this.authoritativeDnsServerCache = authoritativeDnsServerCache;
      return this;
   }

   public DnsNameResolverBuilder localAddress(SocketAddress localAddress) {
      this.localAddress = localAddress;
      return this;
   }

   public DnsNameResolverBuilder ttl(int minTtl, int maxTtl) {
      this.maxTtl = maxTtl;
      this.minTtl = minTtl;
      return this;
   }

   public DnsNameResolverBuilder negativeTtl(int negativeTtl) {
      this.negativeTtl = negativeTtl;
      return this;
   }

   public DnsNameResolverBuilder queryTimeoutMillis(long queryTimeoutMillis) {
      this.queryTimeoutMillis = queryTimeoutMillis;
      return this;
   }

   public static ResolvedAddressTypes computeResolvedAddressTypes(InternetProtocolFamily... internetProtocolFamilies) {
      if (internetProtocolFamilies != null && internetProtocolFamilies.length != 0) {
         if (internetProtocolFamilies.length > 2) {
            throw new IllegalArgumentException("No more than 2 InternetProtocolFamilies");
         } else {
            switch (internetProtocolFamilies[0]) {
               case IPv4:
                  return internetProtocolFamilies.length >= 2 && internetProtocolFamilies[1] == InternetProtocolFamily.IPv6 ? ResolvedAddressTypes.IPV4_PREFERRED : ResolvedAddressTypes.IPV4_ONLY;
               case IPv6:
                  return internetProtocolFamilies.length >= 2 && internetProtocolFamilies[1] == InternetProtocolFamily.IPv4 ? ResolvedAddressTypes.IPV6_PREFERRED : ResolvedAddressTypes.IPV6_ONLY;
               default:
                  throw new IllegalArgumentException("Couldn't resolve ResolvedAddressTypes from InternetProtocolFamily array");
            }
         }
      } else {
         return DnsNameResolver.DEFAULT_RESOLVE_ADDRESS_TYPES;
      }
   }

   public DnsNameResolverBuilder resolvedAddressTypes(ResolvedAddressTypes resolvedAddressTypes) {
      this.resolvedAddressTypes = resolvedAddressTypes;
      return this;
   }

   public DnsNameResolverBuilder completeOncePreferredResolved(boolean completeOncePreferredResolved) {
      this.completeOncePreferredResolved = completeOncePreferredResolved;
      return this;
   }

   public DnsNameResolverBuilder recursionDesired(boolean recursionDesired) {
      this.recursionDesired = recursionDesired;
      return this;
   }

   public DnsNameResolverBuilder maxQueriesPerResolve(int maxQueriesPerResolve) {
      this.maxQueriesPerResolve = maxQueriesPerResolve;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public DnsNameResolverBuilder traceEnabled(boolean traceEnabled) {
      this.traceEnabled = traceEnabled;
      return this;
   }

   public DnsNameResolverBuilder maxPayloadSize(int maxPayloadSize) {
      this.maxPayloadSize = maxPayloadSize;
      return this;
   }

   public DnsNameResolverBuilder optResourceEnabled(boolean optResourceEnabled) {
      this.optResourceEnabled = optResourceEnabled;
      return this;
   }

   public DnsNameResolverBuilder hostsFileEntriesResolver(HostsFileEntriesResolver hostsFileEntriesResolver) {
      this.hostsFileEntriesResolver = hostsFileEntriesResolver;
      return this;
   }

   protected DnsServerAddressStreamProvider nameServerProvider() {
      return this.dnsServerAddressStreamProvider;
   }

   public DnsNameResolverBuilder nameServerProvider(DnsServerAddressStreamProvider dnsServerAddressStreamProvider) {
      this.dnsServerAddressStreamProvider = (DnsServerAddressStreamProvider)ObjectUtil.checkNotNull(dnsServerAddressStreamProvider, "dnsServerAddressStreamProvider");
      return this;
   }

   protected DnsServerAddressStream queryServerAddressStream() {
      return this.queryDnsServerAddressStream;
   }

   public DnsNameResolverBuilder queryServerAddressStream(DnsServerAddressStream queryServerAddressStream) {
      this.queryDnsServerAddressStream = (DnsServerAddressStream)ObjectUtil.checkNotNull(queryServerAddressStream, "queryServerAddressStream");
      return this;
   }

   public DnsNameResolverBuilder searchDomains(Iterable searchDomains) {
      ObjectUtil.checkNotNull(searchDomains, "searchDomains");
      List<String> list = new ArrayList(4);

      for(String f : searchDomains) {
         if (f == null) {
            break;
         }

         if (!list.contains(f)) {
            list.add(f);
         }
      }

      this.searchDomains = (String[])list.toArray(EmptyArrays.EMPTY_STRINGS);
      return this;
   }

   public DnsNameResolverBuilder ndots(int ndots) {
      this.ndots = ndots;
      return this;
   }

   DnsCache getOrNewCache() {
      return (DnsCache)(this.resolveCache != null ? this.resolveCache : new DefaultDnsCache(ObjectUtil.intValue(this.minTtl, 0), ObjectUtil.intValue(this.maxTtl, Integer.MAX_VALUE), ObjectUtil.intValue(this.negativeTtl, 0)));
   }

   AuthoritativeDnsServerCache getOrNewAuthoritativeDnsServerCache() {
      return (AuthoritativeDnsServerCache)(this.authoritativeDnsServerCache != null ? this.authoritativeDnsServerCache : new DefaultAuthoritativeDnsServerCache(ObjectUtil.intValue(this.minTtl, 0), ObjectUtil.intValue(this.maxTtl, Integer.MAX_VALUE), new NameServerComparator(DnsNameResolver.preferredAddressType(this.resolvedAddressTypes).addressType())));
   }

   private DnsServerAddressStream newQueryServerAddressStream(DnsServerAddressStreamProvider dnsServerAddressStreamProvider) {
      return new ThreadLocalNameServerAddressStream(dnsServerAddressStreamProvider);
   }

   DnsCnameCache getOrNewCnameCache() {
      return (DnsCnameCache)(this.cnameCache != null ? this.cnameCache : new DefaultDnsCnameCache(ObjectUtil.intValue(this.minTtl, 0), ObjectUtil.intValue(this.maxTtl, Integer.MAX_VALUE)));
   }

   public DnsNameResolverBuilder decodeIdn(boolean decodeIdn) {
      this.decodeIdn = decodeIdn;
      return this;
   }

   public DnsNameResolverBuilder consolidateCacheSize(int maxNumConsolidation) {
      this.maxNumConsolidation = ObjectUtil.checkPositiveOrZero(maxNumConsolidation, "maxNumConsolidation");
      return this;
   }

   public DnsNameResolverBuilder datagramChannelStrategy(DnsNameResolverChannelStrategy datagramChannelStrategy) {
      this.datagramChannelStrategy = (DnsNameResolverChannelStrategy)ObjectUtil.checkNotNull(datagramChannelStrategy, "datagramChannelStrategy");
      return this;
   }

   public DnsNameResolver build() {
      if (this.eventLoop == null) {
         throw new IllegalStateException("eventLoop should be specified to build a DnsNameResolver.");
      } else {
         if (this.resolveCache != null && (this.minTtl != null || this.maxTtl != null || this.negativeTtl != null)) {
            logger.debug("resolveCache and TTLs are mutually exclusive. TTLs are ignored.");
         }

         if (this.cnameCache != null && (this.minTtl != null || this.maxTtl != null || this.negativeTtl != null)) {
            logger.debug("cnameCache and TTLs are mutually exclusive. TTLs are ignored.");
         }

         if (this.authoritativeDnsServerCache != null && (this.minTtl != null || this.maxTtl != null || this.negativeTtl != null)) {
            logger.debug("authoritativeDnsServerCache and TTLs are mutually exclusive. TTLs are ignored.");
         }

         DnsCache resolveCache = this.getOrNewCache();
         DnsCnameCache cnameCache = this.getOrNewCnameCache();
         AuthoritativeDnsServerCache authoritativeDnsServerCache = this.getOrNewAuthoritativeDnsServerCache();
         DnsServerAddressStream queryDnsServerAddressStream = this.queryDnsServerAddressStream != null ? this.queryDnsServerAddressStream : this.newQueryServerAddressStream(this.dnsServerAddressStreamProvider);
         return new DnsNameResolver(this.eventLoop, this.datagramChannelFactory, this.socketChannelFactory, this.retryOnTimeout, resolveCache, cnameCache, authoritativeDnsServerCache, this.localAddress, this.dnsQueryLifecycleObserverFactory, this.queryTimeoutMillis, this.resolvedAddressTypes, this.recursionDesired, this.maxQueriesPerResolve, this.traceEnabled, this.maxPayloadSize, this.optResourceEnabled, this.hostsFileEntriesResolver, this.dnsServerAddressStreamProvider, queryDnsServerAddressStream, this.searchDomains, this.ndots, this.decodeIdn, this.completeOncePreferredResolved, this.maxNumConsolidation, this.datagramChannelStrategy);
      }
   }

   public DnsNameResolverBuilder copy() {
      DnsNameResolverBuilder copiedBuilder = new DnsNameResolverBuilder();
      if (this.eventLoop != null) {
         copiedBuilder.eventLoop(this.eventLoop);
      }

      if (this.datagramChannelFactory != null) {
         copiedBuilder.datagramChannelFactory(this.datagramChannelFactory);
      }

      copiedBuilder.socketChannelFactory(this.socketChannelFactory, this.retryOnTimeout);
      if (this.resolveCache != null) {
         copiedBuilder.resolveCache(this.resolveCache);
      }

      if (this.cnameCache != null) {
         copiedBuilder.cnameCache(this.cnameCache);
      }

      if (this.maxTtl != null && this.minTtl != null) {
         copiedBuilder.ttl(this.minTtl, this.maxTtl);
      }

      if (this.negativeTtl != null) {
         copiedBuilder.negativeTtl(this.negativeTtl);
      }

      if (this.authoritativeDnsServerCache != null) {
         copiedBuilder.authoritativeDnsServerCache(this.authoritativeDnsServerCache);
      }

      if (this.dnsQueryLifecycleObserverFactory != null) {
         copiedBuilder.dnsQueryLifecycleObserverFactory(this.dnsQueryLifecycleObserverFactory);
      }

      copiedBuilder.queryTimeoutMillis(this.queryTimeoutMillis);
      copiedBuilder.resolvedAddressTypes(this.resolvedAddressTypes);
      copiedBuilder.recursionDesired(this.recursionDesired);
      copiedBuilder.maxQueriesPerResolve(this.maxQueriesPerResolve);
      copiedBuilder.traceEnabled(this.traceEnabled);
      copiedBuilder.maxPayloadSize(this.maxPayloadSize);
      copiedBuilder.optResourceEnabled(this.optResourceEnabled);
      copiedBuilder.hostsFileEntriesResolver(this.hostsFileEntriesResolver);
      if (this.dnsServerAddressStreamProvider != null) {
         copiedBuilder.nameServerProvider(this.dnsServerAddressStreamProvider);
      }

      if (this.queryDnsServerAddressStream != null) {
         copiedBuilder.queryServerAddressStream(this.queryDnsServerAddressStream);
      }

      if (this.searchDomains != null) {
         copiedBuilder.searchDomains(Arrays.asList(this.searchDomains));
      }

      copiedBuilder.ndots(this.ndots);
      copiedBuilder.decodeIdn(this.decodeIdn);
      copiedBuilder.completeOncePreferredResolved(this.completeOncePreferredResolved);
      copiedBuilder.localAddress(this.localAddress);
      copiedBuilder.consolidateCacheSize(this.maxNumConsolidation);
      copiedBuilder.datagramChannelStrategy(this.datagramChannelStrategy);
      return copiedBuilder;
   }
}
