package io.vertx.core.impl.resolver;

import io.netty.channel.EventLoop;
import io.netty.channel.socket.SocketChannel;
import io.netty.resolver.AddressResolver;
import io.netty.resolver.AddressResolverGroup;
import io.netty.resolver.HostsFileEntries;
import io.netty.resolver.HostsFileEntriesResolver;
import io.netty.resolver.HostsFileParser;
import io.netty.resolver.NameResolver;
import io.netty.resolver.ResolvedAddressTypes;
import io.netty.resolver.RoundRobinInetAddressResolver;
import io.netty.resolver.dns.DefaultDnsCache;
import io.netty.resolver.dns.DefaultDnsServerAddressStreamProvider;
import io.netty.resolver.dns.DnsAddressResolverGroup;
import io.netty.resolver.dns.DnsCache;
import io.netty.resolver.dns.DnsNameResolverBuilder;
import io.netty.resolver.dns.DnsServerAddressStream;
import io.netty.resolver.dns.DnsServerAddressStreamProvider;
import io.netty.resolver.dns.DnsServerAddresses;
import io.netty.util.NetUtil;
import io.netty.util.internal.ObjectUtil;
import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.spi.resolver.ResolverProvider;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DnsResolverProvider implements ResolverProvider, HostsFileEntriesResolver {
   private final VertxInternal vertx;
   private final List resolvers = Collections.synchronizedList(new ArrayList());
   private AddressResolverGroup resolverGroup;
   private final List serverList = new ArrayList();
   private final String hostsPath;
   private final Buffer hostsValue;
   private final AtomicLong refreshTimestamp = new AtomicLong();
   private final long hostsRefreshPeriodNanos;
   private volatile HostsFileEntries parsedHostsFile = new HostsFileEntries(Collections.emptyMap(), Collections.emptyMap());

   public static DnsResolverProvider create(VertxInternal vertx, AddressResolverOptions options) {
      DnsResolverProvider provider = new DnsResolverProvider(vertx, options);
      provider.refresh();
      return provider;
   }

   private DnsResolverProvider(VertxInternal vertx, final AddressResolverOptions options) {
      List<String> dnsServers = options.getServers();
      if (dnsServers != null && dnsServers.size() > 0) {
         for(String dnsServer : dnsServers) {
            int sep = dnsServer.indexOf(58);
            String ipAddress;
            int port;
            if (sep != -1) {
               ipAddress = dnsServer.substring(0, sep);
               port = Integer.parseInt(dnsServer.substring(sep + 1));
            } else {
               ipAddress = dnsServer;
               port = 53;
            }

            try {
               this.serverList.add(new InetSocketAddress(InetAddress.getByAddress(NetUtil.createByteArrayFromIpAddressString(ipAddress)), port));
            } catch (UnknownHostException e) {
               throw new VertxException(e);
            }
         }
      } else {
         DnsServerAddressStream stream = DefaultDnsServerAddressStreamProvider.defaultAddresses().stream();
         Set<InetSocketAddress> all = new HashSet();

         while(true) {
            InetSocketAddress address = stream.next();
            if (all.contains(address)) {
               break;
            }

            this.serverList.add(address);
            all.add(address);
         }
      }

      DnsServerAddresses nameServerAddresses = options.isRotateServers() ? DnsServerAddresses.rotational(this.serverList) : DnsServerAddresses.sequential(this.serverList);
      DnsServerAddressStreamProvider nameServerAddressProvider = (hostname) -> nameServerAddresses.stream();
      int minTtl = ObjectUtil.intValue(options.getCacheMinTimeToLive(), 0);
      int maxTtl = ObjectUtil.intValue(options.getCacheMaxTimeToLive(), Integer.MAX_VALUE);
      int negativeTtl = ObjectUtil.intValue(options.getCacheNegativeTimeToLive(), 0);
      DnsCache resolveCache = new DefaultDnsCache(minTtl, maxTtl, negativeTtl);
      DnsCache authoritativeDnsServerCache = new DefaultDnsCache(minTtl, maxTtl, negativeTtl);
      this.vertx = vertx;
      this.hostsPath = options.getHostsPath();
      this.hostsValue = options.getHostsValue();
      this.hostsRefreshPeriodNanos = (long)options.getHostsRefreshPeriod();
      DnsNameResolverBuilder builder = new DnsNameResolverBuilder();
      builder.hostsFileEntriesResolver(this);
      builder.channelFactory(() -> vertx.transport().datagramChannel());
      builder.socketChannelFactory(() -> (SocketChannel)vertx.transport().channelFactory(false).newChannel());
      builder.nameServerProvider(nameServerAddressProvider);
      builder.optResourceEnabled(options.isOptResourceEnabled());
      builder.resolveCache(resolveCache);
      builder.authoritativeDnsServerCache(authoritativeDnsServerCache);
      builder.queryTimeoutMillis(options.getQueryTimeout());
      builder.maxQueriesPerResolve(options.getMaxQueries());
      builder.recursionDesired(options.getRdFlag());
      if (options.getSearchDomains() != null) {
         builder.searchDomains(options.getSearchDomains());
         int ndots = options.getNdots();
         if (ndots == -1) {
            ndots = io.vertx.core.impl.AddressResolver.DEFAULT_NDOTS_RESOLV_OPTION;
         }

         builder.ndots(ndots);
      }

      this.resolverGroup = new DnsAddressResolverGroup(builder) {
         protected AddressResolver newAddressResolver(EventLoop eventLoop, NameResolver resolver) throws Exception {
            AddressResolver<InetSocketAddress> addressResolver;
            if (options.isRoundRobinInetAddress()) {
               addressResolver = (new RoundRobinInetAddressResolver(eventLoop, resolver)).asAddressResolver();
            } else {
               addressResolver = super.newAddressResolver(eventLoop, resolver);
            }

            DnsResolverProvider.this.resolvers.add(new ResolverRegistration(addressResolver, eventLoop));
            return addressResolver;
         }
      };
   }

   public InetAddress address(String inetHost, ResolvedAddressTypes resolvedAddressTypes) {
      if (inetHost.endsWith(".")) {
         inetHost = inetHost.substring(0, inetHost.length() - 1);
      }

      if (this.hostsRefreshPeriodNanos > 0L) {
         this.ensureHostsFileFresh(this.hostsRefreshPeriodNanos);
      }

      InetAddress address = this.lookup(inetHost, resolvedAddressTypes);
      if (address == null) {
         address = this.lookup(inetHost.toLowerCase(Locale.ENGLISH), resolvedAddressTypes);
      }

      return address;
   }

   InetAddress lookup(String inetHost, ResolvedAddressTypes resolvedAddressTypes) {
      switch (resolvedAddressTypes) {
         case IPV4_ONLY:
            return (InetAddress)this.parsedHostsFile.inet4Entries().get(inetHost);
         case IPV6_ONLY:
            return (InetAddress)this.parsedHostsFile.inet6Entries().get(inetHost);
         case IPV4_PREFERRED:
            Inet4Address inet4Address = (Inet4Address)this.parsedHostsFile.inet4Entries().get(inetHost);
            return (InetAddress)(inet4Address != null ? inet4Address : (InetAddress)this.parsedHostsFile.inet6Entries().get(inetHost));
         case IPV6_PREFERRED:
            Inet6Address inet6Address = (Inet6Address)this.parsedHostsFile.inet6Entries().get(inetHost);
            return (InetAddress)(inet6Address != null ? inet6Address : (InetAddress)this.parsedHostsFile.inet4Entries().get(inetHost));
         default:
            throw new IllegalArgumentException("Unknown ResolvedAddressTypes " + resolvedAddressTypes);
      }
   }

   public List nameServerAddresses() {
      return this.serverList;
   }

   public AddressResolverGroup resolver(AddressResolverOptions options) {
      return this.resolverGroup;
   }

   public void close(Handler doneHandler) {
      ContextInternal context = this.vertx.getOrCreateContext();
      ResolverRegistration[] registrations = (ResolverRegistration[])this.resolvers.toArray(new ResolverRegistration[0]);
      if (registrations.length == 0) {
         context.runOnContext(doneHandler);
      } else {
         AtomicInteger count = new AtomicInteger(registrations.length);

         for(ResolverRegistration registration : registrations) {
            Runnable task = () -> {
               registration.resolver.close();
               if (count.decrementAndGet() == 0) {
                  context.runOnContext(doneHandler);
               }

            };
            if (registration.executor.inEventLoop()) {
               task.run();
            } else {
               registration.executor.execute(task);
            }
         }

      }
   }

   public void refresh() {
      this.ensureHostsFileFresh(0L);
   }

   private void ensureHostsFileFresh(long refreshPeriodNanos) {
      long prev = this.refreshTimestamp.get();
      long now = System.nanoTime();
      if (now - prev >= refreshPeriodNanos && this.refreshTimestamp.compareAndSet(prev, now)) {
         this.refreshHostsFile();
      }

   }

   private void refreshHostsFile() {
      HostsFileEntries entries;
      if (this.hostsPath != null) {
         File file = this.vertx.resolveFile(this.hostsPath).getAbsoluteFile();

         try {
            if (!file.exists() || !file.isFile()) {
               throw new IOException();
            }

            entries = HostsFileParser.parse(file);
         } catch (IOException var5) {
            throw new VertxException("Cannot read hosts file " + file.getAbsolutePath());
         }
      } else if (this.hostsValue != null) {
         try {
            entries = HostsFileParser.parse(new StringReader(this.hostsValue.toString()));
         } catch (IOException e) {
            throw new VertxException("Cannot read hosts config ", e);
         }
      } else {
         entries = HostsFileParser.parseSilently();
      }

      this.parsedHostsFile = entries;
   }

   private static class ResolverRegistration {
      private final AddressResolver resolver;
      private final EventLoop executor;

      ResolverRegistration(AddressResolver resolver, EventLoop executor) {
         this.resolver = resolver;
         this.executor = executor;
      }
   }
}
