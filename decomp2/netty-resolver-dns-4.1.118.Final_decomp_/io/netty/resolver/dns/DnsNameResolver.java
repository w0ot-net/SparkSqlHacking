package io.netty.resolver.dns;

import [Ljava.lang.String;;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.dns.DatagramDnsQueryEncoder;
import io.netty.handler.codec.dns.DatagramDnsResponse;
import io.netty.handler.codec.dns.DatagramDnsResponseDecoder;
import io.netty.handler.codec.dns.DefaultDnsRawRecord;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRawRecord;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.handler.codec.dns.DnsResponse;
import io.netty.resolver.DefaultHostsFileEntriesResolver;
import io.netty.resolver.HostsFileEntriesResolver;
import io.netty.resolver.InetNameResolver;
import io.netty.resolver.ResolvedAddressTypes;
import io.netty.util.AttributeKey;
import io.netty.util.NetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseNotifier;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Method;
import java.net.IDN;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DnsNameResolver extends InetNameResolver {
   public static final AttributeKey DNS_PIPELINE_ATTRIBUTE = AttributeKey.newInstance("io.netty.resolver.dns.pipeline");
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(DnsNameResolver.class);
   private static final String LOCALHOST = "localhost";
   private static final String WINDOWS_HOST_NAME;
   private static final InetAddress LOCALHOST_ADDRESS;
   private static final DnsRecord[] EMPTY_ADDITIONALS = new DnsRecord[0];
   private static final DnsRecordType[] IPV4_ONLY_RESOLVED_RECORD_TYPES;
   private static final InternetProtocolFamily[] IPV4_ONLY_RESOLVED_PROTOCOL_FAMILIES;
   private static final DnsRecordType[] IPV4_PREFERRED_RESOLVED_RECORD_TYPES;
   private static final InternetProtocolFamily[] IPV4_PREFERRED_RESOLVED_PROTOCOL_FAMILIES;
   private static final DnsRecordType[] IPV6_ONLY_RESOLVED_RECORD_TYPES;
   private static final InternetProtocolFamily[] IPV6_ONLY_RESOLVED_PROTOCOL_FAMILIES;
   private static final DnsRecordType[] IPV6_PREFERRED_RESOLVED_RECORD_TYPES;
   private static final InternetProtocolFamily[] IPV6_PREFERRED_RESOLVED_PROTOCOL_FAMILIES;
   private static final ChannelHandler NOOP_HANDLER;
   static final ResolvedAddressTypes DEFAULT_RESOLVE_ADDRESS_TYPES;
   static final String[] DEFAULT_SEARCH_DOMAINS;
   private static final UnixResolverOptions DEFAULT_OPTIONS;
   private static final DatagramDnsResponseDecoder DATAGRAM_DECODER;
   private static final DatagramDnsQueryEncoder DATAGRAM_ENCODER;
   private final Comparator nameServerComparator;
   private final DnsQueryContextManager queryContextManager;
   private final DnsCache resolveCache;
   private final AuthoritativeDnsServerCache authoritativeDnsServerCache;
   private final DnsCnameCache cnameCache;
   private final DnsServerAddressStream queryDnsServerAddressStream;
   private final long queryTimeoutMillis;
   private final int maxQueriesPerResolve;
   private final ResolvedAddressTypes resolvedAddressTypes;
   private final InternetProtocolFamily[] resolvedInternetProtocolFamilies;
   private final boolean recursionDesired;
   private final int maxPayloadSize;
   private final boolean optResourceEnabled;
   private final HostsFileEntriesResolver hostsFileEntriesResolver;
   private final DnsServerAddressStreamProvider dnsServerAddressStreamProvider;
   private final String[] searchDomains;
   private final int ndots;
   private final boolean supportsAAAARecords;
   private final boolean supportsARecords;
   private final InternetProtocolFamily preferredAddressType;
   private final DnsRecordType[] resolveRecordTypes;
   private final boolean decodeIdn;
   private final DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory;
   private final boolean completeOncePreferredResolved;
   private final DnsResolveChannelProvider resolveChannelProvider;
   private final Bootstrap socketBootstrap;
   private final boolean retryWithTcpOnTimeout;
   private final int maxNumConsolidation;
   private final Map inflightLookups;

   private static boolean anyInterfaceSupportsIpV6() {
      for(NetworkInterface iface : NetUtil.NETWORK_INTERFACES) {
         Enumeration<InetAddress> addresses = iface.getInetAddresses();

         while(addresses.hasMoreElements()) {
            InetAddress inetAddress = (InetAddress)addresses.nextElement();
            if (inetAddress instanceof Inet6Address && !inetAddress.isAnyLocalAddress() && !inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
               return true;
            }
         }
      }

      return false;
   }

   private static List getSearchDomainsHack() throws Exception {
      if (PlatformDependent.javaVersion() < 9) {
         Class<?> configClass = Class.forName("sun.net.dns.ResolverConfiguration");
         Method open = configClass.getMethod("open");
         Method nameservers = configClass.getMethod("searchlist");
         Object instance = open.invoke((Object)null);
         return (List)nameservers.invoke(instance);
      } else {
         return Collections.emptyList();
      }
   }

   /** @deprecated */
   @Deprecated
   public DnsNameResolver(EventLoop eventLoop, ChannelFactory channelFactory, DnsCache resolveCache, DnsCache authoritativeDnsServerCache, DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory, long queryTimeoutMillis, ResolvedAddressTypes resolvedAddressTypes, boolean recursionDesired, int maxQueriesPerResolve, boolean traceEnabled, int maxPayloadSize, boolean optResourceEnabled, HostsFileEntriesResolver hostsFileEntriesResolver, DnsServerAddressStreamProvider dnsServerAddressStreamProvider, String[] searchDomains, int ndots, boolean decodeIdn) {
      this(eventLoop, channelFactory, resolveCache, (AuthoritativeDnsServerCache)(new AuthoritativeDnsServerCacheAdapter(authoritativeDnsServerCache)), dnsQueryLifecycleObserverFactory, queryTimeoutMillis, resolvedAddressTypes, recursionDesired, maxQueriesPerResolve, traceEnabled, maxPayloadSize, optResourceEnabled, hostsFileEntriesResolver, dnsServerAddressStreamProvider, searchDomains, ndots, decodeIdn);
   }

   /** @deprecated */
   @Deprecated
   public DnsNameResolver(EventLoop eventLoop, ChannelFactory channelFactory, DnsCache resolveCache, AuthoritativeDnsServerCache authoritativeDnsServerCache, DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory, long queryTimeoutMillis, ResolvedAddressTypes resolvedAddressTypes, boolean recursionDesired, int maxQueriesPerResolve, boolean traceEnabled, int maxPayloadSize, boolean optResourceEnabled, HostsFileEntriesResolver hostsFileEntriesResolver, DnsServerAddressStreamProvider dnsServerAddressStreamProvider, String[] searchDomains, int ndots, boolean decodeIdn) {
      this(eventLoop, channelFactory, (ChannelFactory)null, false, resolveCache, NoopDnsCnameCache.INSTANCE, authoritativeDnsServerCache, (SocketAddress)null, dnsQueryLifecycleObserverFactory, queryTimeoutMillis, resolvedAddressTypes, recursionDesired, maxQueriesPerResolve, traceEnabled, maxPayloadSize, optResourceEnabled, hostsFileEntriesResolver, dnsServerAddressStreamProvider, new ThreadLocalNameServerAddressStream(dnsServerAddressStreamProvider), searchDomains, ndots, decodeIdn, false, 0, DnsNameResolverChannelStrategy.ChannelPerResolver);
   }

   DnsNameResolver(EventLoop eventLoop, ChannelFactory channelFactory, ChannelFactory socketChannelFactory, boolean retryWithTcpOnTimeout, DnsCache resolveCache, DnsCnameCache cnameCache, AuthoritativeDnsServerCache authoritativeDnsServerCache, SocketAddress localAddress, DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory, long queryTimeoutMillis, ResolvedAddressTypes resolvedAddressTypes, boolean recursionDesired, int maxQueriesPerResolve, boolean traceEnabled, final int maxPayloadSize, boolean optResourceEnabled, HostsFileEntriesResolver hostsFileEntriesResolver, DnsServerAddressStreamProvider dnsServerAddressStreamProvider, DnsServerAddressStream queryDnsServerAddressStream, String[] searchDomains, int ndots, boolean decodeIdn, boolean completeOncePreferredResolved, int maxNumConsolidation, DnsNameResolverChannelStrategy datagramChannelStrategy) {
      super(eventLoop);
      this.queryContextManager = new DnsQueryContextManager();
      this.queryTimeoutMillis = queryTimeoutMillis >= 0L ? queryTimeoutMillis : TimeUnit.SECONDS.toMillis((long)DEFAULT_OPTIONS.timeout());
      this.resolvedAddressTypes = resolvedAddressTypes != null ? resolvedAddressTypes : DEFAULT_RESOLVE_ADDRESS_TYPES;
      this.recursionDesired = recursionDesired;
      this.maxQueriesPerResolve = maxQueriesPerResolve > 0 ? maxQueriesPerResolve : DEFAULT_OPTIONS.attempts();
      this.maxPayloadSize = ObjectUtil.checkPositive(maxPayloadSize, "maxPayloadSize");
      this.optResourceEnabled = optResourceEnabled;
      this.hostsFileEntriesResolver = (HostsFileEntriesResolver)ObjectUtil.checkNotNull(hostsFileEntriesResolver, "hostsFileEntriesResolver");
      this.dnsServerAddressStreamProvider = (DnsServerAddressStreamProvider)ObjectUtil.checkNotNull(dnsServerAddressStreamProvider, "dnsServerAddressStreamProvider");
      this.queryDnsServerAddressStream = (DnsServerAddressStream)ObjectUtil.checkNotNull(queryDnsServerAddressStream, "queryDnsServerAddressStream");
      this.resolveCache = (DnsCache)ObjectUtil.checkNotNull(resolveCache, "resolveCache");
      this.cnameCache = (DnsCnameCache)ObjectUtil.checkNotNull(cnameCache, "cnameCache");
      this.dnsQueryLifecycleObserverFactory = (DnsQueryLifecycleObserverFactory)(traceEnabled ? (dnsQueryLifecycleObserverFactory instanceof NoopDnsQueryLifecycleObserverFactory ? new LoggingDnsQueryLifeCycleObserverFactory() : new BiDnsQueryLifecycleObserverFactory(new LoggingDnsQueryLifeCycleObserverFactory(), dnsQueryLifecycleObserverFactory)) : (DnsQueryLifecycleObserverFactory)ObjectUtil.checkNotNull(dnsQueryLifecycleObserverFactory, "dnsQueryLifecycleObserverFactory"));
      this.searchDomains = searchDomains != null ? (String[])((String;)searchDomains).clone() : DEFAULT_SEARCH_DOMAINS;
      this.ndots = ndots >= 0 ? ndots : DEFAULT_OPTIONS.ndots();
      this.decodeIdn = decodeIdn;
      this.completeOncePreferredResolved = completeOncePreferredResolved;
      this.retryWithTcpOnTimeout = retryWithTcpOnTimeout;
      if (socketChannelFactory == null) {
         this.socketBootstrap = null;
      } else {
         this.socketBootstrap = new Bootstrap();
         ((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)this.socketBootstrap.option(ChannelOption.SO_REUSEADDR, true)).group(this.executor())).channelFactory(socketChannelFactory)).attr(DNS_PIPELINE_ATTRIBUTE, Boolean.TRUE)).handler(NOOP_HANDLER);
         if (queryTimeoutMillis > 0L && queryTimeoutMillis <= 2147483647L) {
            this.socketBootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int)queryTimeoutMillis);
         }
      }

      switch (this.resolvedAddressTypes) {
         case IPV4_ONLY:
            this.supportsAAAARecords = false;
            this.supportsARecords = true;
            this.resolveRecordTypes = IPV4_ONLY_RESOLVED_RECORD_TYPES;
            this.resolvedInternetProtocolFamilies = IPV4_ONLY_RESOLVED_PROTOCOL_FAMILIES;
            break;
         case IPV4_PREFERRED:
            this.supportsAAAARecords = true;
            this.supportsARecords = true;
            this.resolveRecordTypes = IPV4_PREFERRED_RESOLVED_RECORD_TYPES;
            this.resolvedInternetProtocolFamilies = IPV4_PREFERRED_RESOLVED_PROTOCOL_FAMILIES;
            break;
         case IPV6_ONLY:
            this.supportsAAAARecords = true;
            this.supportsARecords = false;
            this.resolveRecordTypes = IPV6_ONLY_RESOLVED_RECORD_TYPES;
            this.resolvedInternetProtocolFamilies = IPV6_ONLY_RESOLVED_PROTOCOL_FAMILIES;
            break;
         case IPV6_PREFERRED:
            this.supportsAAAARecords = true;
            this.supportsARecords = true;
            this.resolveRecordTypes = IPV6_PREFERRED_RESOLVED_RECORD_TYPES;
            this.resolvedInternetProtocolFamilies = IPV6_PREFERRED_RESOLVED_PROTOCOL_FAMILIES;
            break;
         default:
            throw new IllegalArgumentException("Unknown ResolvedAddressTypes " + resolvedAddressTypes);
      }

      this.preferredAddressType = preferredAddressType(this.resolvedAddressTypes);
      this.authoritativeDnsServerCache = (AuthoritativeDnsServerCache)ObjectUtil.checkNotNull(authoritativeDnsServerCache, "authoritativeDnsServerCache");
      this.nameServerComparator = new NameServerComparator(this.preferredAddressType.addressType());
      this.maxNumConsolidation = maxNumConsolidation;
      if (maxNumConsolidation > 0) {
         this.inflightLookups = new HashMap();
      } else {
         this.inflightLookups = null;
      }

      final DnsResponseHandler responseHandler = new DnsResponseHandler(this.queryContextManager);
      Bootstrap bootstrap = (Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).channelFactory(channelFactory)).group(eventLoop)).attr(DNS_PIPELINE_ATTRIBUTE, Boolean.TRUE)).handler(new ChannelInitializer() {
         protected void initChannel(DatagramChannel ch) {
            ch.config().setRecvByteBufAllocator(new FixedRecvByteBufAllocator(maxPayloadSize));
            ch.pipeline().addLast(new ChannelHandler[]{DnsNameResolver.DATAGRAM_ENCODER, DnsNameResolver.DATAGRAM_DECODER, responseHandler});
         }
      });
      if (localAddress == null) {
         bootstrap.option(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION, true);
      }

      this.resolveChannelProvider = newProvider(datagramChannelStrategy, bootstrap, localAddress);
   }

   private static DnsResolveChannelProvider newProvider(DnsNameResolverChannelStrategy channelStrategy, Bootstrap bootstrap, SocketAddress localAddress) {
      switch (channelStrategy) {
         case ChannelPerResolver:
            return new DnsResolveChannelPerResolverProvider(bootstrap, localAddress);
         case ChannelPerResolution:
            return new DnsResolveChannelPerResolutionProvider(bootstrap, localAddress);
         default:
            throw new IllegalArgumentException("Unknown DnsNameResolverChannelStrategy: " + channelStrategy);
      }
   }

   static InternetProtocolFamily preferredAddressType(ResolvedAddressTypes resolvedAddressTypes) {
      switch (resolvedAddressTypes) {
         case IPV4_ONLY:
         case IPV4_PREFERRED:
            return InternetProtocolFamily.IPv4;
         case IPV6_ONLY:
         case IPV6_PREFERRED:
            return InternetProtocolFamily.IPv6;
         default:
            throw new IllegalArgumentException("Unknown ResolvedAddressTypes " + resolvedAddressTypes);
      }
   }

   InetSocketAddress newRedirectServerAddress(InetAddress server) {
      return new InetSocketAddress(server, 53);
   }

   final DnsQueryLifecycleObserverFactory dnsQueryLifecycleObserverFactory() {
      return this.dnsQueryLifecycleObserverFactory;
   }

   protected DnsServerAddressStream newRedirectDnsServerStream(String hostname, List nameservers) {
      DnsServerAddressStream cached = this.authoritativeDnsServerCache().get(hostname);
      if (cached != null && cached.size() != 0) {
         return cached;
      } else {
         Collections.sort(nameservers, this.nameServerComparator);
         return new SequentialDnsServerAddressStream(nameservers, 0);
      }
   }

   public DnsCache resolveCache() {
      return this.resolveCache;
   }

   public DnsCnameCache cnameCache() {
      return this.cnameCache;
   }

   public AuthoritativeDnsServerCache authoritativeDnsServerCache() {
      return this.authoritativeDnsServerCache;
   }

   public long queryTimeoutMillis() {
      return this.queryTimeoutMillis;
   }

   public DnsServerAddressStream queryDnsServerAddressStream() {
      return this.queryDnsServerAddressStream;
   }

   public ResolvedAddressTypes resolvedAddressTypes() {
      return this.resolvedAddressTypes;
   }

   InternetProtocolFamily[] resolvedInternetProtocolFamiliesUnsafe() {
      return this.resolvedInternetProtocolFamilies;
   }

   final String[] searchDomains() {
      return this.searchDomains;
   }

   final int ndots() {
      return this.ndots;
   }

   final boolean supportsAAAARecords() {
      return this.supportsAAAARecords;
   }

   final boolean supportsARecords() {
      return this.supportsARecords;
   }

   final InternetProtocolFamily preferredAddressType() {
      return this.preferredAddressType;
   }

   final DnsRecordType[] resolveRecordTypes() {
      return this.resolveRecordTypes;
   }

   final boolean isDecodeIdn() {
      return this.decodeIdn;
   }

   public boolean isRecursionDesired() {
      return this.recursionDesired;
   }

   public int maxQueriesPerResolve() {
      return this.maxQueriesPerResolve;
   }

   public int maxPayloadSize() {
      return this.maxPayloadSize;
   }

   public boolean isOptResourceEnabled() {
      return this.optResourceEnabled;
   }

   public HostsFileEntriesResolver hostsFileEntriesResolver() {
      return this.hostsFileEntriesResolver;
   }

   public void close() {
      this.resolveChannelProvider.close();
      this.resolveCache.clear();
      this.cnameCache.clear();
      this.authoritativeDnsServerCache.clear();
   }

   protected EventLoop executor() {
      return (EventLoop)super.executor();
   }

   private InetAddress resolveHostsFileEntry(String hostname) {
      if (this.hostsFileEntriesResolver == null) {
         return null;
      } else {
         InetAddress address = this.hostsFileEntriesResolver.address(hostname, this.resolvedAddressTypes);
         return address == null && isLocalWindowsHost(hostname) ? LOCALHOST_ADDRESS : address;
      }
   }

   private List resolveHostsFileEntries(String hostname) {
      if (this.hostsFileEntriesResolver == null) {
         return null;
      } else {
         List<InetAddress> addresses;
         if (this.hostsFileEntriesResolver instanceof DefaultHostsFileEntriesResolver) {
            addresses = ((DefaultHostsFileEntriesResolver)this.hostsFileEntriesResolver).addresses(hostname, this.resolvedAddressTypes);
         } else {
            InetAddress address = this.hostsFileEntriesResolver.address(hostname, this.resolvedAddressTypes);
            addresses = address != null ? Collections.singletonList(address) : null;
         }

         return addresses == null && isLocalWindowsHost(hostname) ? Collections.singletonList(LOCALHOST_ADDRESS) : addresses;
      }
   }

   private static boolean isLocalWindowsHost(String hostname) {
      return PlatformDependent.isWindows() && ("localhost".equalsIgnoreCase(hostname) || WINDOWS_HOST_NAME != null && WINDOWS_HOST_NAME.equalsIgnoreCase(hostname));
   }

   public final Future resolve(String inetHost, Iterable additionals) {
      return this.resolve(inetHost, additionals, this.executor().newPromise());
   }

   public final Future resolve(String inetHost, Iterable additionals, Promise promise) {
      ObjectUtil.checkNotNull(promise, "promise");
      DnsRecord[] additionalsArray = toArray(additionals, true);

      try {
         this.doResolve(inetHost, additionalsArray, promise, this.resolveCache);
         return promise;
      } catch (Exception e) {
         return promise.setFailure(e);
      }
   }

   public final Future resolveAll(String inetHost, Iterable additionals) {
      return this.resolveAll(inetHost, additionals, this.executor().newPromise());
   }

   public final Future resolveAll(String inetHost, Iterable additionals, Promise promise) {
      ObjectUtil.checkNotNull(promise, "promise");
      DnsRecord[] additionalsArray = toArray(additionals, true);

      try {
         this.doResolveAll(inetHost, additionalsArray, promise, this.resolveCache);
         return promise;
      } catch (Exception e) {
         return promise.setFailure(e);
      }
   }

   protected void doResolve(String inetHost, Promise promise) throws Exception {
      this.doResolve(inetHost, EMPTY_ADDITIONALS, promise, this.resolveCache);
   }

   public final Future resolveAll(DnsQuestion question) {
      return this.resolveAll(question, EMPTY_ADDITIONALS, this.executor().newPromise());
   }

   public final Future resolveAll(DnsQuestion question, Iterable additionals) {
      return this.resolveAll(question, additionals, this.executor().newPromise());
   }

   public final Future resolveAll(DnsQuestion question, Iterable additionals, Promise promise) {
      DnsRecord[] additionalsArray = toArray(additionals, true);
      return this.resolveAll(question, additionalsArray, promise);
   }

   private Future resolveAll(final DnsQuestion question, final DnsRecord[] additionals, final Promise promise) {
      ObjectUtil.checkNotNull(question, "question");
      ObjectUtil.checkNotNull(promise, "promise");
      DnsRecordType type = question.type();
      final String hostname = question.name();
      if (type == DnsRecordType.A || type == DnsRecordType.AAAA) {
         List<InetAddress> hostsFileEntries = this.resolveHostsFileEntries(hostname);
         if (hostsFileEntries != null) {
            List<DnsRecord> result = new ArrayList();

            for(InetAddress hostsFileEntry : hostsFileEntries) {
               ByteBuf content = null;
               if (hostsFileEntry instanceof Inet4Address) {
                  if (type == DnsRecordType.A) {
                     content = Unpooled.wrappedBuffer(hostsFileEntry.getAddress());
                  }
               } else if (hostsFileEntry instanceof Inet6Address && type == DnsRecordType.AAAA) {
                  content = Unpooled.wrappedBuffer(hostsFileEntry.getAddress());
               }

               if (content != null) {
                  result.add(new DefaultDnsRawRecord(hostname, type, 86400L, content));
               }
            }

            if (!result.isEmpty()) {
               if (!trySuccess(promise, result)) {
                  for(DnsRecord r : result) {
                     ReferenceCountUtil.safeRelease(r);
                  }
               }

               return promise;
            }
         }
      }

      ChannelFuture f = this.resolveChannelProvider.nextResolveChannel(promise);
      if (f.isDone()) {
         this.resolveAllNow(f, hostname, question, additionals, promise);
      } else {
         f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture f) {
               DnsNameResolver.this.resolveAllNow(f, hostname, question, additionals, promise);
            }
         });
      }

      return promise;
   }

   private void resolveAllNow(ChannelFuture f, String hostname, DnsQuestion question, DnsRecord[] additionals, Promise promise) {
      if (f.isSuccess()) {
         DnsServerAddressStream nameServerAddrs = this.dnsServerAddressStreamProvider.nameServerAddressStream(hostname);
         (new DnsRecordResolveContext(this, f.channel(), promise, question, additionals, nameServerAddrs, this.maxQueriesPerResolve)).resolve(promise);
      } else {
         UnknownHostException e = toException(f, hostname, question, additionals);
         promise.setFailure(e);
      }

   }

   private static UnknownHostException toException(ChannelFuture f, String hostname, DnsQuestion question, DnsRecord[] additionals) {
      UnknownHostException e = new UnknownHostException("Failed to resolve '" + hostname + "', couldn't setup transport: " + f.channel());
      e.initCause(f.cause());
      if (question != null) {
         ReferenceCountUtil.release(question);
      }

      for(DnsRecord record : additionals) {
         ReferenceCountUtil.release(record);
      }

      return e;
   }

   private static DnsRecord[] toArray(Iterable additionals, boolean validateType) {
      ObjectUtil.checkNotNull(additionals, "additionals");
      if (additionals instanceof Collection) {
         Collection<DnsRecord> records = (Collection)additionals;

         for(DnsRecord r : additionals) {
            validateAdditional(r, validateType);
         }

         return (DnsRecord[])records.toArray(new DnsRecord[records.size()]);
      } else {
         Iterator<DnsRecord> additionalsIt = additionals.iterator();
         if (!additionalsIt.hasNext()) {
            return EMPTY_ADDITIONALS;
         } else {
            List<DnsRecord> records = new ArrayList();

            do {
               DnsRecord r = (DnsRecord)additionalsIt.next();
               validateAdditional(r, validateType);
               records.add(r);
            } while(additionalsIt.hasNext());

            return (DnsRecord[])records.toArray(new DnsRecord[records.size()]);
         }
      }
   }

   private static void validateAdditional(DnsRecord record, boolean validateType) {
      ObjectUtil.checkNotNull(record, "record");
      if (validateType && record instanceof DnsRawRecord) {
         throw new IllegalArgumentException("DnsRawRecord implementations not allowed: " + record);
      }
   }

   private InetAddress loopbackAddress() {
      return this.preferredAddressType().localhost();
   }

   protected void doResolve(String inetHost, final DnsRecord[] additionals, final Promise promise, final DnsCache resolveCache) throws Exception {
      if (inetHost != null && !inetHost.isEmpty()) {
         InetAddress address = NetUtil.createInetAddressFromIpAddressString(inetHost);
         if (address != null) {
            promise.setSuccess(address);
         } else {
            final String hostname = hostname(inetHost);
            InetAddress hostsFileEntry = this.resolveHostsFileEntry(hostname);
            if (hostsFileEntry != null) {
               promise.setSuccess(hostsFileEntry);
            } else {
               if (!this.doResolveCached(hostname, additionals, promise, resolveCache)) {
                  ChannelFuture f = this.resolveChannelProvider.nextResolveChannel(promise);
                  if (f.isDone()) {
                     this.doResolveNow(f, hostname, additionals, promise, resolveCache);
                  } else {
                     f.addListener(new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture f) {
                           DnsNameResolver.this.doResolveNow(f, hostname, additionals, promise, resolveCache);
                        }
                     });
                  }
               }

            }
         }
      } else {
         promise.setSuccess(this.loopbackAddress());
      }
   }

   private void doResolveNow(ChannelFuture f, String hostname, DnsRecord[] additionals, Promise promise, DnsCache resolveCache) {
      if (f.isSuccess()) {
         this.doResolveUncached(f.channel(), hostname, additionals, promise, resolveCache, this.completeOncePreferredResolved);
      } else {
         UnknownHostException e = toException(f, hostname, (DnsQuestion)null, additionals);
         promise.setFailure(e);
      }

   }

   private boolean doResolveCached(String hostname, DnsRecord[] additionals, Promise promise, DnsCache resolveCache) {
      List<? extends DnsCacheEntry> cachedEntries = resolveCache.get(hostname, additionals);
      if (cachedEntries != null && !cachedEntries.isEmpty()) {
         Throwable cause = ((DnsCacheEntry)cachedEntries.get(0)).cause();
         if (cause != null) {
            tryFailure(promise, cause);
            return true;
         } else {
            int numEntries = cachedEntries.size();

            for(InternetProtocolFamily f : this.resolvedInternetProtocolFamilies) {
               for(int i = 0; i < numEntries; ++i) {
                  DnsCacheEntry e = (DnsCacheEntry)cachedEntries.get(i);
                  if (f.addressType().isInstance(e.address())) {
                     trySuccess(promise, e.address());
                     return true;
                  }
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   static boolean trySuccess(Promise promise, Object result) {
      boolean notifiedRecords = promise.trySuccess(result);
      if (!notifiedRecords) {
         logger.trace("Failed to notify success ({}) to a promise: {}", result, promise);
      }

      return notifiedRecords;
   }

   private static void tryFailure(Promise promise, Throwable cause) {
      if (!promise.tryFailure(cause)) {
         logger.trace("Failed to notify failure to a promise: {}", promise, cause);
      }

   }

   private void doResolveUncached(Channel channel, String hostname, DnsRecord[] additionals, final Promise promise, DnsCache resolveCache, boolean completeEarlyIfPossible) {
      Promise<List<InetAddress>> allPromise = this.executor().newPromise();
      this.doResolveAllUncached(channel, hostname, additionals, promise, allPromise, resolveCache, completeEarlyIfPossible);
      allPromise.addListener(new FutureListener() {
         public void operationComplete(Future future) {
            if (future.isSuccess()) {
               DnsNameResolver.trySuccess(promise, ((List)future.getNow()).get(0));
            } else {
               DnsNameResolver.tryFailure(promise, future.cause());
            }

         }
      });
   }

   protected void doResolveAll(String inetHost, Promise promise) throws Exception {
      this.doResolveAll(inetHost, EMPTY_ADDITIONALS, promise, this.resolveCache);
   }

   protected void doResolveAll(String inetHost, final DnsRecord[] additionals, final Promise promise, final DnsCache resolveCache) throws Exception {
      if (inetHost != null && !inetHost.isEmpty()) {
         InetAddress address = NetUtil.createInetAddressFromIpAddressString(inetHost);
         if (address != null) {
            promise.setSuccess(Collections.singletonList(address));
         } else {
            final String hostname = hostname(inetHost);
            List<InetAddress> hostsFileEntries = this.resolveHostsFileEntries(hostname);
            if (hostsFileEntries != null) {
               promise.setSuccess(hostsFileEntries);
            } else {
               if (!doResolveAllCached(hostname, additionals, promise, resolveCache, this.searchDomains(), this.ndots(), this.resolvedInternetProtocolFamilies)) {
                  ChannelFuture f = this.resolveChannelProvider.nextResolveChannel(promise);
                  if (f.isDone()) {
                     this.doResolveAllNow(f, hostname, additionals, promise, resolveCache);
                  } else {
                     f.addListener(new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture f) {
                           DnsNameResolver.this.doResolveAllNow(f, hostname, additionals, promise, resolveCache);
                        }
                     });
                  }
               }

            }
         }
      } else {
         promise.setSuccess(Collections.singletonList(this.loopbackAddress()));
      }
   }

   private void doResolveAllNow(ChannelFuture f, String hostname, DnsRecord[] additionals, Promise promise, DnsCache resolveCache) {
      if (f.isSuccess()) {
         this.doResolveAllUncached(f.channel(), hostname, additionals, promise, promise, resolveCache, this.completeOncePreferredResolved);
      } else {
         UnknownHostException e = toException(f, hostname, (DnsQuestion)null, additionals);
         promise.setFailure(e);
      }

   }

   private static boolean hasEntries(List cachedEntries) {
      return cachedEntries != null && !cachedEntries.isEmpty();
   }

   static boolean doResolveAllCached(String hostname, DnsRecord[] additionals, Promise promise, DnsCache resolveCache, String[] searchDomains, int ndots, InternetProtocolFamily[] resolvedInternetProtocolFamilies) {
      List<? extends DnsCacheEntry> cachedEntries = resolveCache.get(hostname, additionals);
      if (!hasEntries(cachedEntries) && searchDomains != null && ndots != 0 && !StringUtil.endsWith(hostname, '.')) {
         for(String searchDomain : searchDomains) {
            String initialHostname = hostname + '.' + searchDomain;
            cachedEntries = resolveCache.get(initialHostname, additionals);
            if (hasEntries(cachedEntries)) {
               break;
            }
         }
      }

      if (!hasEntries(cachedEntries)) {
         return false;
      } else {
         Throwable cause = ((DnsCacheEntry)cachedEntries.get(0)).cause();
         if (cause != null) {
            tryFailure(promise, cause);
            return true;
         } else {
            List<InetAddress> result = null;
            int numEntries = cachedEntries.size();

            for(InternetProtocolFamily f : resolvedInternetProtocolFamilies) {
               for(int i = 0; i < numEntries; ++i) {
                  DnsCacheEntry e = (DnsCacheEntry)cachedEntries.get(i);
                  if (f.addressType().isInstance(e.address())) {
                     if (result == null) {
                        result = new ArrayList(numEntries);
                     }

                     result.add(e.address());
                  }
               }
            }

            if (result != null) {
               trySuccess(promise, result);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   private void doResolveAllUncached(final Channel channel, final String hostname, final DnsRecord[] additionals, final Promise originalPromise, final Promise promise, final DnsCache resolveCache, final boolean completeEarlyIfPossible) {
      EventExecutor executor = this.executor();
      if (executor.inEventLoop()) {
         this.doResolveAllUncached0(channel, hostname, additionals, originalPromise, promise, resolveCache, completeEarlyIfPossible);
      } else {
         executor.execute(new Runnable() {
            public void run() {
               DnsNameResolver.this.doResolveAllUncached0(channel, hostname, additionals, originalPromise, promise, resolveCache, completeEarlyIfPossible);
            }
         });
      }

   }

   private void doResolveAllUncached0(final Channel channel, final String hostname, final DnsRecord[] additionals, final Promise originalPromise, final Promise promise, final DnsCache resolveCache, final boolean completeEarlyIfPossible) {
      assert this.executor().inEventLoop();

      if (this.inflightLookups != null && (additionals == null || additionals.length == 0)) {
         Future<List<InetAddress>> inflightFuture = (Future)this.inflightLookups.get(hostname);
         if (inflightFuture != null) {
            inflightFuture.addListener(new GenericFutureListener() {
               public void operationComplete(Future future) {
                  if (future.isSuccess()) {
                     promise.setSuccess((List)future.getNow());
                  } else {
                     Throwable cause = future.cause();
                     if (DnsNameResolver.isTimeoutError(cause)) {
                        DnsNameResolver.this.resolveNow(channel, hostname, additionals, originalPromise, promise, resolveCache, completeEarlyIfPossible);
                     } else {
                        promise.setFailure(cause);
                     }
                  }

               }
            });
            return;
         }

         if (this.inflightLookups.size() < this.maxNumConsolidation) {
            this.inflightLookups.put(hostname, promise);
            promise.addListener(new GenericFutureListener() {
               public void operationComplete(Future future) {
                  DnsNameResolver.this.inflightLookups.remove(hostname);
               }
            });
         }
      }

      this.resolveNow(channel, hostname, additionals, originalPromise, promise, resolveCache, completeEarlyIfPossible);
   }

   private void resolveNow(Channel channel, String hostname, DnsRecord[] additionals, Promise originalPromise, Promise promise, DnsCache resolveCache, boolean completeEarlyIfPossible) {
      DnsServerAddressStream nameServerAddrs = this.dnsServerAddressStreamProvider.nameServerAddressStream(hostname);
      DnsAddressResolveContext ctx = new DnsAddressResolveContext(this, channel, originalPromise, hostname, additionals, nameServerAddrs, this.maxQueriesPerResolve, resolveCache, this.authoritativeDnsServerCache, completeEarlyIfPossible);
      ctx.resolve(promise);
   }

   private static String hostname(String inetHost) {
      String hostname = IDN.toASCII(inetHost);
      if (StringUtil.endsWith(inetHost, '.') && !StringUtil.endsWith(hostname, '.')) {
         hostname = hostname + ".";
      }

      return hostname;
   }

   public Future query(DnsQuestion question) {
      return this.query(this.nextNameServerAddress(), question);
   }

   public Future query(DnsQuestion question, Iterable additionals) {
      return this.query(this.nextNameServerAddress(), question, additionals);
   }

   public Future query(DnsQuestion question, Promise promise) {
      return this.query(this.nextNameServerAddress(), question, Collections.emptyList(), promise);
   }

   private InetSocketAddress nextNameServerAddress() {
      return this.queryDnsServerAddressStream.next();
   }

   public Future query(InetSocketAddress nameServerAddr, DnsQuestion question) {
      return this.query(nameServerAddr, question, (Iterable)Collections.emptyList());
   }

   public Future query(InetSocketAddress nameServerAddr, DnsQuestion question, Iterable additionals) {
      return this.query(nameServerAddr, question, additionals, this.executor().newPromise());
   }

   public Future query(InetSocketAddress nameServerAddr, DnsQuestion question, Promise promise) {
      return this.query(nameServerAddr, question, Collections.emptyList(), promise);
   }

   public Future query(final InetSocketAddress nameServerAddr, final DnsQuestion question, Iterable additionals, final Promise promise) {
      ChannelFuture f = this.resolveChannelProvider.nextResolveChannel(promise);
      final DnsRecord[] additionalsArray = toArray(additionals, false);
      if (f.isDone()) {
         if (f.isSuccess()) {
            return this.doQuery(f.channel(), nameServerAddr, question, NoopDnsQueryLifecycleObserver.INSTANCE, additionalsArray, true, promise);
         } else {
            UnknownHostException e = toException(f, question.name(), question, additionalsArray);
            promise.setFailure(e);
            return this.executor().newFailedFuture(e);
         }
      } else {
         final Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> p = this.executor().newPromise();
         f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture f) {
               if (f.isSuccess()) {
                  Future<AddressedEnvelope<DnsResponse, InetSocketAddress>> qf = DnsNameResolver.this.doQuery(f.channel(), nameServerAddr, question, NoopDnsQueryLifecycleObserver.INSTANCE, additionalsArray, true, promise);
                  PromiseNotifier.cascade(qf, p);
               } else {
                  UnknownHostException e = DnsNameResolver.toException(f, question.name(), question, additionalsArray);
                  promise.setFailure(e);
                  p.setFailure(e);
               }

            }
         });
         return p;
      }
   }

   public static boolean isTransportOrTimeoutError(Throwable cause) {
      return cause != null && cause.getCause() instanceof DnsNameResolverException;
   }

   public static boolean isTimeoutError(Throwable cause) {
      return cause != null && cause.getCause() instanceof DnsNameResolverTimeoutException;
   }

   final Future doQuery(Channel channel, InetSocketAddress nameServerAddr, DnsQuestion question, DnsQueryLifecycleObserver queryLifecycleObserver, DnsRecord[] additionals, boolean flush, Promise promise) {
      Promise<AddressedEnvelope<DnsResponse, InetSocketAddress>> castPromise = cast((Promise)ObjectUtil.checkNotNull(promise, "promise"));
      int payloadSize = this.isOptResourceEnabled() ? this.maxPayloadSize() : 0;

      try {
         DnsQueryContext queryContext = new DatagramDnsQueryContext(channel, nameServerAddr, this.queryContextManager, payloadSize, this.isRecursionDesired(), this.queryTimeoutMillis(), question, additionals, castPromise, this.socketBootstrap, this.retryWithTcpOnTimeout);
         ChannelFuture future = queryContext.writeQuery(flush);
         queryLifecycleObserver.queryWritten(nameServerAddr, future);
         return castPromise;
      } catch (Exception e) {
         return castPromise.setFailure(e);
      }
   }

   private static Promise cast(Promise promise) {
      return promise;
   }

   final DnsServerAddressStream newNameServerAddressStream(String hostname) {
      return this.dnsServerAddressStreamProvider.nameServerAddressStream(hostname);
   }

   private static ChannelFuture registerOrBind(Bootstrap bootstrap, SocketAddress localAddress) {
      return localAddress == null ? bootstrap.register() : bootstrap.bind(localAddress);
   }

   static {
      IPV4_ONLY_RESOLVED_RECORD_TYPES = new DnsRecordType[]{DnsRecordType.A};
      IPV4_ONLY_RESOLVED_PROTOCOL_FAMILIES = new InternetProtocolFamily[]{InternetProtocolFamily.IPv4};
      IPV4_PREFERRED_RESOLVED_RECORD_TYPES = new DnsRecordType[]{DnsRecordType.A, DnsRecordType.AAAA};
      IPV4_PREFERRED_RESOLVED_PROTOCOL_FAMILIES = new InternetProtocolFamily[]{InternetProtocolFamily.IPv4, InternetProtocolFamily.IPv6};
      IPV6_ONLY_RESOLVED_RECORD_TYPES = new DnsRecordType[]{DnsRecordType.AAAA};
      IPV6_ONLY_RESOLVED_PROTOCOL_FAMILIES = new InternetProtocolFamily[]{InternetProtocolFamily.IPv6};
      IPV6_PREFERRED_RESOLVED_RECORD_TYPES = new DnsRecordType[]{DnsRecordType.AAAA, DnsRecordType.A};
      IPV6_PREFERRED_RESOLVED_PROTOCOL_FAMILIES = new InternetProtocolFamily[]{InternetProtocolFamily.IPv6, InternetProtocolFamily.IPv4};
      NOOP_HANDLER = new ChannelHandlerAdapter() {
         public boolean isSharable() {
            return true;
         }
      };
      if (!NetUtil.isIpV4StackPreferred() && anyInterfaceSupportsIpV6()) {
         if (NetUtil.isIpV6AddressesPreferred()) {
            DEFAULT_RESOLVE_ADDRESS_TYPES = ResolvedAddressTypes.IPV6_PREFERRED;
            LOCALHOST_ADDRESS = NetUtil.LOCALHOST6;
         } else {
            DEFAULT_RESOLVE_ADDRESS_TYPES = ResolvedAddressTypes.IPV4_PREFERRED;
            LOCALHOST_ADDRESS = NetUtil.LOCALHOST4;
         }
      } else {
         DEFAULT_RESOLVE_ADDRESS_TYPES = ResolvedAddressTypes.IPV4_ONLY;
         LOCALHOST_ADDRESS = NetUtil.LOCALHOST4;
      }

      logger.debug("Default ResolvedAddressTypes: {}", DEFAULT_RESOLVE_ADDRESS_TYPES);
      logger.debug("Localhost address: {}", LOCALHOST_ADDRESS);

      String hostName;
      try {
         hostName = PlatformDependent.isWindows() ? InetAddress.getLocalHost().getHostName() : null;
      } catch (Exception var6) {
         hostName = null;
      }

      WINDOWS_HOST_NAME = hostName;
      logger.debug("Windows hostname: {}", WINDOWS_HOST_NAME);

      String[] searchDomains;
      try {
         List<String> list = PlatformDependent.isWindows() ? getSearchDomainsHack() : UnixResolverDnsServerAddressStreamProvider.parseEtcResolverSearchDomains();
         searchDomains = (String[])list.toArray(EmptyArrays.EMPTY_STRINGS);
      } catch (Exception var5) {
         searchDomains = EmptyArrays.EMPTY_STRINGS;
      }

      DEFAULT_SEARCH_DOMAINS = searchDomains;
      logger.debug("Default search domains: {}", Arrays.toString(DEFAULT_SEARCH_DOMAINS));

      UnixResolverOptions options;
      try {
         options = UnixResolverDnsServerAddressStreamProvider.parseEtcResolverOptions();
      } catch (Exception var4) {
         options = UnixResolverOptions.newBuilder().build();
      }

      DEFAULT_OPTIONS = options;
      logger.debug("Default {}", DEFAULT_OPTIONS);
      DATAGRAM_DECODER = new DatagramDnsResponseDecoder() {
         protected DnsResponse decodeResponse(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
            DnsResponse response = super.decodeResponse(ctx, packet);
            if (((ByteBuf)packet.content()).isReadable()) {
               response.setTruncated(true);
               if (DnsNameResolver.logger.isDebugEnabled()) {
                  DnsNameResolver.logger.debug("{} RECEIVED: UDP [{}: {}] truncated packet received, consider adjusting maxPayloadSize for the {}.", new Object[]{ctx.channel(), response.id(), packet.sender(), StringUtil.simpleClassName(DnsNameResolver.class)});
               }
            }

            return response;
         }
      };
      DATAGRAM_ENCODER = new DatagramDnsQueryEncoder();
   }

   private static final class DnsResponseHandler extends ChannelInboundHandlerAdapter {
      private final DnsQueryContextManager queryContextManager;

      DnsResponseHandler(DnsQueryContextManager queryContextManager) {
         this.queryContextManager = queryContextManager;
      }

      public boolean isSharable() {
         return true;
      }

      public void channelRead(ChannelHandlerContext ctx, Object msg) {
         Channel qCh = ctx.channel();
         DatagramDnsResponse res = (DatagramDnsResponse)msg;
         int queryId = res.id();
         DnsNameResolver.logger.debug("{} RECEIVED: UDP [{}: {}], {}", new Object[]{qCh, queryId, res.sender(), res});
         DnsQueryContext qCtx = this.queryContextManager.get(res.sender(), queryId);
         if (qCtx == null) {
            DnsNameResolver.logger.debug("{} Received a DNS response with an unknown ID: UDP [{}: {}]", new Object[]{qCh, queryId, res.sender()});
            res.release();
         } else if (qCtx.isDone()) {
            DnsNameResolver.logger.debug("{} Received a DNS response for a query that was timed out or cancelled: UDP [{}: {}]", new Object[]{qCh, queryId, res.sender()});
            res.release();
         } else {
            qCtx.finishSuccess(res, res.isTruncated());
         }
      }

      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
         if (cause instanceof CorruptedFrameException) {
            DnsNameResolver.logger.debug("{} Unable to decode DNS response: UDP", ctx.channel(), cause);
         } else {
            DnsNameResolver.logger.warn("{} Unexpected exception: UDP", ctx.channel(), cause);
         }

      }
   }

   private static final class DnsResolveChannelPerResolverProvider implements DnsResolveChannelProvider {
      private final ChannelFuture resolveChannelFuture;

      DnsResolveChannelPerResolverProvider(Bootstrap bootstrap, SocketAddress localAddress) {
         this.resolveChannelFuture = DnsNameResolver.registerOrBind(bootstrap, localAddress);
      }

      public ChannelFuture nextResolveChannel(Future resolutionFuture) {
         return this.resolveChannelFuture;
      }

      public void close() {
         this.resolveChannelFuture.channel().close();
      }
   }

   private static final class DnsResolveChannelPerResolutionProvider implements DnsResolveChannelProvider {
      private final Bootstrap bootstrap;
      private final SocketAddress localAddress;

      DnsResolveChannelPerResolutionProvider(Bootstrap bootstrap, SocketAddress localAddress) {
         this.bootstrap = bootstrap;
         this.localAddress = localAddress;
      }

      public ChannelFuture nextResolveChannel(Future resolutionFuture) {
         final ChannelFuture f = DnsNameResolver.registerOrBind(this.bootstrap, this.localAddress);
         resolutionFuture.addListener(new FutureListener() {
            public void operationComplete(Future future) {
               f.channel().close();
            }
         });
         return f;
      }

      public void close() {
      }
   }

   private interface DnsResolveChannelProvider {
      ChannelFuture nextResolveChannel(Future var1);

      void close();
   }
}
