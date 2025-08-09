package org.sparkproject.jetty.servlets;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.annotation.Name;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import org.sparkproject.jetty.util.thread.Scheduler;

@ManagedObject("limits exposure to abuse from request flooding, whether malicious, or as a result of a misconfigured client")
public class DoSFilter implements Filter {
   private static final Logger LOG = LoggerFactory.getLogger(DoSFilter.class);
   private static final String IPv4_GROUP = "(\\d{1,3})";
   private static final Pattern IPv4_PATTERN = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");
   private static final String IPv6_GROUP = "(\\p{XDigit}{1,4})";
   private static final Pattern IPv6_PATTERN = Pattern.compile("(\\p{XDigit}{1,4}):(\\p{XDigit}{1,4}):(\\p{XDigit}{1,4}):(\\p{XDigit}{1,4}):(\\p{XDigit}{1,4}):(\\p{XDigit}{1,4}):(\\p{XDigit}{1,4}):(\\p{XDigit}{1,4})");
   private static final Pattern CIDR_PATTERN = Pattern.compile("([^/]+)/(\\d+)");
   private static final String __TRACKER = "DoSFilter.Tracker";
   private static final String __THROTTLED = "DoSFilter.Throttled";
   private static final int __DEFAULT_MAX_REQUESTS_PER_SEC = 25;
   private static final int __DEFAULT_DELAY_MS = 100;
   private static final int __DEFAULT_THROTTLE = 5;
   private static final int __DEFAULT_MAX_WAIT_MS = 50;
   private static final long __DEFAULT_THROTTLE_MS = 30000L;
   private static final long __DEFAULT_MAX_REQUEST_MS_INIT_PARAM = 30000L;
   private static final long __DEFAULT_MAX_IDLE_TRACKER_MS_INIT_PARAM = 30000L;
   static final String MANAGED_ATTR_INIT_PARAM = "managedAttr";
   static final String MAX_REQUESTS_PER_S_INIT_PARAM = "maxRequestsPerSec";
   static final String DELAY_MS_INIT_PARAM = "delayMs";
   static final String THROTTLED_REQUESTS_INIT_PARAM = "throttledRequests";
   static final String MAX_WAIT_INIT_PARAM = "maxWaitMs";
   static final String THROTTLE_MS_INIT_PARAM = "throttleMs";
   static final String MAX_REQUEST_MS_INIT_PARAM = "maxRequestMs";
   static final String MAX_IDLE_TRACKER_MS_INIT_PARAM = "maxIdleTrackerMs";
   static final String INSERT_HEADERS_INIT_PARAM = "insertHeaders";
   /** @deprecated */
   @Deprecated
   static final String TRACK_SESSIONS_INIT_PARAM = "trackSessions";
   static final String REMOTE_PORT_INIT_PARAM = "remotePort";
   static final String IP_WHITELIST_INIT_PARAM = "ipWhitelist";
   static final String ENABLED_INIT_PARAM = "enabled";
   static final String TOO_MANY_CODE = "tooManyCode";
   private final String _suspended = "DoSFilter@" + Integer.toHexString(this.hashCode()) + ".SUSPENDED";
   private final String _resumed = "DoSFilter@" + Integer.toHexString(this.hashCode()) + ".RESUMED";
   private final ConcurrentHashMap _rateTrackers = new ConcurrentHashMap();
   private final List _whitelist = new CopyOnWriteArrayList();
   private int _tooManyCode;
   private volatile long _delayMs;
   private volatile long _throttleMs;
   private volatile long _maxWaitMs;
   private volatile long _maxRequestMs;
   private volatile long _maxIdleTrackerMs;
   private volatile boolean _insertHeaders;
   private volatile boolean _remotePort;
   private volatile boolean _enabled;
   private volatile String _name;
   private Listener _listener = new Listener();
   private Semaphore _passes;
   private volatile int _throttledRequests;
   private volatile int _maxRequestsPerSec;
   private final Queue _queue = new ConcurrentLinkedQueue();
   private final AsyncListener _asyncListener = new DoSAsyncListener();
   private Scheduler _scheduler;
   private ServletContext _context;

   public void init(FilterConfig filterConfig) throws ServletException {
      this._rateTrackers.clear();
      int maxRequests = 25;
      String parameter = filterConfig.getInitParameter("maxRequestsPerSec");
      if (parameter != null) {
         maxRequests = Integer.parseInt(parameter);
      }

      this.setMaxRequestsPerSec(maxRequests);
      long delay = 100L;
      parameter = filterConfig.getInitParameter("delayMs");
      if (parameter != null) {
         delay = Long.parseLong(parameter);
      }

      this.setDelayMs(delay);
      int throttledRequests = 5;
      parameter = filterConfig.getInitParameter("throttledRequests");
      if (parameter != null) {
         throttledRequests = Integer.parseInt(parameter);
      }

      this.setThrottledRequests(throttledRequests);
      long maxWait = 50L;
      parameter = filterConfig.getInitParameter("maxWaitMs");
      if (parameter != null) {
         maxWait = Long.parseLong(parameter);
      }

      this.setMaxWaitMs(maxWait);
      long throttle = 30000L;
      parameter = filterConfig.getInitParameter("throttleMs");
      if (parameter != null) {
         throttle = Long.parseLong(parameter);
      }

      this.setThrottleMs(throttle);
      long maxRequestMs = 30000L;
      parameter = filterConfig.getInitParameter("maxRequestMs");
      if (parameter != null) {
         maxRequestMs = Long.parseLong(parameter);
      }

      this.setMaxRequestMs(maxRequestMs);
      long maxIdleTrackerMs = 30000L;
      parameter = filterConfig.getInitParameter("maxIdleTrackerMs");
      if (parameter != null) {
         maxIdleTrackerMs = Long.parseLong(parameter);
      }

      this.setMaxIdleTrackerMs(maxIdleTrackerMs);
      String whiteList = "";
      parameter = filterConfig.getInitParameter("ipWhitelist");
      if (parameter != null) {
         whiteList = parameter;
      }

      this.setWhitelist(whiteList);
      parameter = filterConfig.getInitParameter("insertHeaders");
      this.setInsertHeaders(parameter == null || Boolean.parseBoolean(parameter));
      parameter = filterConfig.getInitParameter("trackSessions");
      this.setTrackSessions(Boolean.parseBoolean(parameter));
      parameter = filterConfig.getInitParameter("remotePort");
      this.setRemotePort(Boolean.parseBoolean(parameter));
      parameter = filterConfig.getInitParameter("enabled");
      this.setEnabled(parameter == null || Boolean.parseBoolean(parameter));
      parameter = filterConfig.getInitParameter("tooManyCode");
      this.setTooManyCode(parameter == null ? 429 : Integer.parseInt(parameter));
      this.setName(filterConfig.getFilterName());
      this._context = filterConfig.getServletContext();
      if (this._context != null) {
         this._context.setAttribute(filterConfig.getFilterName(), this);
      }

      this._scheduler = this.startScheduler();
   }

   protected Scheduler startScheduler() throws ServletException {
      try {
         Scheduler result = new ScheduledExecutorScheduler(String.format("DoS-Scheduler-%x", this.hashCode()), false);
         result.start();
         return result;
      } catch (Exception x) {
         throw new ServletException(x);
      }
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
      this.doFilter((HttpServletRequest)request, (HttpServletResponse)response, filterChain);
   }

   protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
      if (!this.isEnabled()) {
         filterChain.doFilter(request, response);
      } else {
         RateTracker tracker = (RateTracker)request.getAttribute("DoSFilter.Tracker");
         if (tracker != null) {
            this.throttleRequest(request, response, filterChain, tracker);
         } else {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Filtering {}", request);
            }

            tracker = this.getRateTracker(request);
            OverLimit overLimit = tracker.isRateExceeded(NanoTime.now());
            if (overLimit == null) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Allowing {}", request);
               }

               this.doFilterChain(filterChain, request, response);
            } else {
               Action action = this._listener.onRequestOverLimit(request, overLimit, this);
               long delayMs = this.getDelayMs();
               boolean insertHeaders = this.isInsertHeaders();
               switch (action.ordinal()) {
                  case 0:
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Allowing over-limit request {}", request);
                     }

                     this.doFilterChain(filterChain, request, response);
                     break;
                  case 1:
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Aborting over-limit request {}", request);
                     }

                     response.sendError(-1);
                     return;
                  case 2:
                     if (insertHeaders) {
                        response.addHeader("DoSFilter", "unavailable");
                     }

                     response.sendError(this.getTooManyCode());
                     return;
                  case 3:
                     if (insertHeaders) {
                        response.addHeader("DoSFilter", "delayed");
                     }

                     request.setAttribute("DoSFilter.Tracker", tracker);
                     AsyncContext asyncContext = request.startAsync();
                     if (delayMs > 0L) {
                        asyncContext.setTimeout(delayMs);
                     }

                     asyncContext.addListener(new DoSTimeoutAsyncListener());
                     break;
                  case 4:
                     this.throttleRequest(request, response, filterChain, tracker);
               }

            }
         }
      }
   }

   private void throttleRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, RateTracker tracker) throws IOException, ServletException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Throttling {}", request);
      }

      boolean accepted = false;

      try {
         long throttleMs;
         label752: {
            accepted = this._passes.tryAcquire(this.getMaxWaitMs(), TimeUnit.MILLISECONDS);
            if (!accepted) {
               Boolean throttled = (Boolean)request.getAttribute("DoSFilter.Throttled");
               throttleMs = this.getThrottleMs();
               if (!Boolean.TRUE.equals(throttled) && throttleMs > 0L) {
                  request.setAttribute("DoSFilter.Throttled", Boolean.TRUE);
                  if (this.isInsertHeaders()) {
                     response.addHeader("DoSFilter", "throttled");
                  }
                  break label752;
               }

               Boolean resumed = (Boolean)request.getAttribute(this._resumed);
               if (Boolean.TRUE.equals(resumed)) {
                  this._passes.acquire();
                  accepted = true;
               }
            }

            if (accepted) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Allowing {}", request);
               }

               this.doFilterChain(filterChain, request, response);
            } else {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Rejecting {}", request);
               }

               if (this.isInsertHeaders()) {
                  response.addHeader("DoSFilter", "unavailable");
               }

               response.sendError(this.getTooManyCode());
            }

            return;
         }

         AsyncContext asyncContext = request.startAsync();
         request.setAttribute(this._suspended, Boolean.TRUE);
         asyncContext.setTimeout(throttleMs);
         asyncContext.addListener(this._asyncListener);
         this._queue.add(asyncContext);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Throttled {}, {}ms", request, throttleMs);
         }
      } catch (InterruptedException e) {
         LOG.trace("IGNORED", e);
         response.sendError(this.getTooManyCode());
         return;
      } finally {
         if (accepted) {
            try {
               AsyncContext asyncContext = (AsyncContext)this._queue.poll();
               if (asyncContext != null) {
                  ServletRequest candidate = asyncContext.getRequest();
                  Boolean suspended = (Boolean)candidate.getAttribute(this._suspended);
                  if (Boolean.TRUE.equals(suspended)) {
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Resuming {}", request);
                     }

                     candidate.setAttribute(this._resumed, Boolean.TRUE);
                     asyncContext.dispatch();
                  }
               }
            } finally {
               this._passes.release();
            }
         }

      }

   }

   protected void doFilterChain(FilterChain chain, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Thread thread = Thread.currentThread();
      Runnable requestTimeout = () -> this.onRequestTimeout(request, response, thread);
      Scheduler.Task task = this._scheduler.schedule(requestTimeout, this.getMaxRequestMs(), TimeUnit.MILLISECONDS);

      try {
         chain.doFilter(request, response);
      } finally {
         task.cancel();
      }

   }

   protected void onRequestTimeout(HttpServletRequest request, HttpServletResponse response, Thread handlingThread) {
      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Timing out {}", request);
         }

         try {
            response.sendError(503);
         } catch (IllegalStateException ise) {
            LOG.trace("IGNORED", ise);
            response.sendError(-1);
         }
      } catch (Throwable x) {
         LOG.info("Failed to sendError", x);
      }

      handlingThread.interrupt();
   }

   /** @deprecated */
   @Deprecated
   protected RateType getMaxPriority() {
      return null;
   }

   public void setListener(Listener listener) {
      this._listener = (Listener)Objects.requireNonNull(listener, "Listener may not be null");
   }

   public Listener getListener() {
      return this._listener;
   }

   private void schedule(RateTracker tracker) {
      this._scheduler.schedule(tracker, this.getMaxIdleTrackerMs(), TimeUnit.MILLISECONDS);
   }

   RateTracker getRateTracker(ServletRequest request) {
      String loadId = this.isRemotePort() ? this.createRemotePortId(request) : request.getRemoteAddr();
      RateTracker tracker = (RateTracker)this._rateTrackers.get(loadId);
      if (tracker == null) {
         boolean allowed = this.checkWhitelist(request.getRemoteAddr());
         int maxRequestsPerSec = this.getMaxRequestsPerSec();
         tracker = (RateTracker)(allowed ? new FixedRateTracker(this._context, this._name, loadId, maxRequestsPerSec) : new RateTracker(this._context, this._name, loadId, maxRequestsPerSec));
         tracker.setContext(this._context);
         RateTracker existing = (RateTracker)this._rateTrackers.putIfAbsent(loadId, tracker);
         if (existing != null) {
            tracker = existing;
         }

         this._scheduler.schedule(tracker, this.getMaxIdleTrackerMs(), TimeUnit.MILLISECONDS);
      }

      return tracker;
   }

   private void addToRateTracker(RateTracker tracker) {
      this._rateTrackers.put(tracker.getId(), tracker);
   }

   public void removeFromRateTracker(String id) {
      this._rateTrackers.remove(id);
   }

   protected boolean checkWhitelist(String candidate) {
      for(String address : this._whitelist) {
         if (address.contains("/")) {
            if (this.subnetMatch(address, candidate)) {
               return true;
            }
         } else if (address.equals(candidate)) {
            return true;
         }
      }

      return false;
   }

   protected boolean subnetMatch(String subnetAddress, String address) {
      Matcher cidrMatcher = CIDR_PATTERN.matcher(subnetAddress);
      if (!cidrMatcher.matches()) {
         return false;
      } else {
         String subnet = cidrMatcher.group(1);

         int prefix;
         try {
            prefix = Integer.parseInt(cidrMatcher.group(2));
         } catch (NumberFormatException var11) {
            LOG.info("Ignoring malformed CIDR address {}", subnetAddress);
            return false;
         }

         byte[] subnetBytes = this.addressToBytes(subnet);
         if (subnetBytes == null) {
            LOG.info("Ignoring malformed CIDR address {}", subnetAddress);
            return false;
         } else {
            byte[] addressBytes = this.addressToBytes(address);
            if (addressBytes == null) {
               LOG.info("Ignoring malformed remote address {}", address);
               return false;
            } else {
               int length = subnetBytes.length;
               if (length != addressBytes.length) {
                  return false;
               } else {
                  byte[] mask = this.prefixToBytes(prefix, length);

                  for(int i = 0; i < length; ++i) {
                     if ((subnetBytes[i] & mask[i]) != (addressBytes[i] & mask[i])) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         }
      }
   }

   private byte[] addressToBytes(String address) {
      Matcher ipv4Matcher = IPv4_PATTERN.matcher(address);
      if (ipv4Matcher.matches()) {
         byte[] result = new byte[4];

         for(int i = 0; i < result.length; ++i) {
            result[i] = Integer.valueOf(ipv4Matcher.group(i + 1)).byteValue();
         }

         return result;
      } else {
         Matcher ipv6Matcher = IPv6_PATTERN.matcher(address);
         if (!ipv6Matcher.matches()) {
            return null;
         } else {
            byte[] result = new byte[16];

            for(int i = 0; i < result.length; i += 2) {
               int word = Integer.parseInt(ipv6Matcher.group(i / 2 + 1), 16);
               result[i] = (byte)((word & '\uff00') >>> 8);
               result[i + 1] = (byte)(word & 255);
            }

            return result;
         }
      }
   }

   private byte[] prefixToBytes(int prefix, int length) {
      byte[] result = new byte[length];

      int index;
      for(index = 0; prefix / 8 > 0; ++index) {
         result[index] = -1;
         prefix -= 8;
      }

      if (index == result.length) {
         return result;
      } else {
         result[index] = (byte)(-(1 << 8 - prefix));
         return result;
      }
   }

   public void destroy() {
      LOG.debug("Destroy {}", this);
      this.stopScheduler();
      this._rateTrackers.clear();
      this._whitelist.clear();
   }

   protected void stopScheduler() {
      try {
         this._scheduler.stop();
      } catch (Exception x) {
         LOG.trace("IGNORED", x);
      }

   }

   /** @deprecated */
   @Deprecated
   protected String extractUserId(ServletRequest request) {
      return null;
   }

   @ManagedAttribute("maximum number of requests allowed from a connection per second")
   public int getMaxRequestsPerSec() {
      return this._maxRequestsPerSec;
   }

   public void setMaxRequestsPerSec(int value) {
      this._maxRequestsPerSec = value;
   }

   @ManagedAttribute("delay applied to all requests over the rate limit (in ms)")
   public long getDelayMs() {
      return this._delayMs;
   }

   public void setDelayMs(long value) {
      this._delayMs = value;
   }

   @ManagedAttribute("maximum time the filter will block waiting throttled connections, (0 for no delay, -1 to reject requests)")
   public long getMaxWaitMs() {
      return this._maxWaitMs;
   }

   public void setMaxWaitMs(long value) {
      this._maxWaitMs = value;
   }

   @ManagedAttribute("number of requests over rate limit")
   public int getThrottledRequests() {
      return this._throttledRequests;
   }

   public void setThrottledRequests(int value) {
      int permits = this._passes == null ? 0 : this._passes.availablePermits();
      this._passes = new Semaphore(value - this._throttledRequests + permits, true);
      this._throttledRequests = value;
   }

   @ManagedAttribute("amount of time to async wait for semaphore")
   public long getThrottleMs() {
      return this._throttleMs;
   }

   public void setThrottleMs(long value) {
      this._throttleMs = value;
   }

   @ManagedAttribute("maximum time to allow requests to process (in ms)")
   public long getMaxRequestMs() {
      return this._maxRequestMs;
   }

   public void setMaxRequestMs(long value) {
      this._maxRequestMs = value;
   }

   @ManagedAttribute("maximum time to track of request rates for connection before discarding")
   public long getMaxIdleTrackerMs() {
      return this._maxIdleTrackerMs;
   }

   public void setMaxIdleTrackerMs(long value) {
      this._maxIdleTrackerMs = value;
   }

   public String getName() {
      return this._name;
   }

   public void setName(String name) {
      this._name = name;
   }

   @ManagedAttribute("inser DoSFilter headers in response")
   public boolean isInsertHeaders() {
      return this._insertHeaders;
   }

   public void setInsertHeaders(boolean value) {
      this._insertHeaders = value;
   }

   /** @deprecated */
   @Deprecated
   public boolean isTrackSessions() {
      return false;
   }

   /** @deprecated */
   @Deprecated
   public void setTrackSessions(boolean value) {
      if (value) {
         LOG.warn("Session Tracking is not supported");
      }

   }

   @ManagedAttribute("usage rate is tracked by IP+port is session tracking not used")
   public boolean isRemotePort() {
      return this._remotePort;
   }

   public void setRemotePort(boolean value) {
      this._remotePort = value;
   }

   @ManagedAttribute("whether this filter is enabled")
   public boolean isEnabled() {
      return this._enabled;
   }

   public void setEnabled(boolean enabled) {
      this._enabled = enabled;
   }

   public int getTooManyCode() {
      return this._tooManyCode;
   }

   public void setTooManyCode(int tooManyCode) {
      this._tooManyCode = tooManyCode;
   }

   @ManagedAttribute("list of IPs that will not be rate limited")
   public String getWhitelist() {
      StringBuilder result = new StringBuilder();
      Iterator<String> iterator = this._whitelist.iterator();

      while(iterator.hasNext()) {
         String address = (String)iterator.next();
         result.append(address);
         if (iterator.hasNext()) {
            result.append(",");
         }
      }

      return result.toString();
   }

   public void setWhitelist(String commaSeparatedList) {
      List<String> result = new ArrayList();

      for(String address : StringUtil.csvSplit(commaSeparatedList)) {
         this.addWhitelistAddress(result, address);
      }

      this.clearWhitelist();
      this._whitelist.addAll(result);
      LOG.debug("Whitelisted IP addresses: {}", result);
   }

   @ManagedOperation("clears the list of IP addresses that will not be rate limited")
   public void clearWhitelist() {
      this._whitelist.clear();
   }

   @ManagedOperation("adds an IP address that will not be rate limited")
   public boolean addWhitelistAddress(@Name("address") String address) {
      return this.addWhitelistAddress(this._whitelist, address);
   }

   private boolean addWhitelistAddress(List list, String address) {
      address = address.trim();
      if (address.length() <= 0) {
         return false;
      } else {
         list.add(address);
         return true;
      }
   }

   @ManagedOperation("removes an IP address that will not be rate limited")
   public boolean removeWhitelistAddress(@Name("address") String address) {
      return this._whitelist.remove(address);
   }

   private String createRemotePortId(ServletRequest request) {
      String addr = request.getRemoteAddr();
      int port = request.getRemotePort();
      return addr + ":" + port;
   }

   /** @deprecated */
   @Deprecated
   public static enum RateType {
      AUTH,
      SESSION,
      IP,
      UNKNOWN;

      // $FF: synthetic method
      private static RateType[] $values() {
         return new RateType[]{AUTH, SESSION, IP, UNKNOWN};
      }
   }

   static class RateTracker implements Runnable, Serializable {
      private static final long serialVersionUID = 3534663738034577872L;
      final AutoLock _lock = new AutoLock();
      protected final String _filterName;
      protected transient ServletContext _context;
      protected final String _id;
      protected final int _maxRequestsPerSecond;
      protected final long[] _timestamps;
      protected int _next;

      RateTracker(ServletContext context, String filterName, String id, int maxRequestsPerSecond) {
         this._context = context;
         this._filterName = filterName;
         this._id = id;
         this._maxRequestsPerSecond = maxRequestsPerSecond;
         this._timestamps = new long[maxRequestsPerSecond];
         this._next = 0;
      }

      public OverLimit isRateExceeded(long now) {
         long last;
         try (AutoLock l = this._lock.lock()) {
            last = this._timestamps[this._next];
            this._timestamps[this._next] = now;
            this._next = (this._next + 1) % this._timestamps.length;
         }

         if (last == 0L) {
            return null;
         } else {
            long rate = NanoTime.elapsed(last, now);
            return TimeUnit.NANOSECONDS.toSeconds(rate) < 1L ? new Overage(Duration.ofNanos(rate), (long)this._maxRequestsPerSecond) : null;
         }
      }

      public String getId() {
         return this._id;
      }

      public void setContext(ServletContext context) {
         this._context = context;
      }

      protected void removeFromRateTrackers(DoSFilter filter, String id) {
         if (filter != null) {
            filter.removeFromRateTracker(id);
            if (DoSFilter.LOG.isDebugEnabled()) {
               DoSFilter.LOG.debug("Tracker removed: {}", this.getId());
            }

         }
      }

      private void addToRateTrackers(DoSFilter filter, RateTracker tracker) {
         if (filter != null) {
            filter.addToRateTracker(tracker);
         }
      }

      public void run() {
         if (this._context == null) {
            DoSFilter.LOG.warn("Unknown context for rate tracker {}", this);
         } else {
            int latestIndex = this._next == 0 ? this._timestamps.length - 1 : this._next - 1;
            long last = this._timestamps[latestIndex];
            boolean hasRecentRequest = last != 0L && NanoTime.secondsSince(last) < 1L;
            DoSFilter filter = (DoSFilter)this._context.getAttribute(this._filterName);
            if (hasRecentRequest) {
               if (filter != null) {
                  filter.schedule(this);
               } else {
                  DoSFilter.LOG.warn("No filter {}", this._filterName);
               }
            } else {
               this.removeFromRateTrackers(filter, this._id);
            }

         }
      }

      public String toString() {
         return "RateTracker/" + this._id;
      }

      public class Overage implements OverLimit {
         private final Duration duration;
         private final long count;

         public Overage(Duration dur, long count) {
            this.duration = dur;
            this.count = count;
         }

         public String getRateId() {
            return RateTracker.this._id;
         }

         public Duration getDuration() {
            return this.duration;
         }

         public long getCount() {
            return this.count;
         }

         public String toString() {
            String var10000 = OverLimit.class.getSimpleName();
            return var10000 + "@" + Integer.toHexString(this.hashCode()) + "[id=" + this.getRateId() + ", duration=" + String.valueOf(this.duration) + ", count=" + this.count + "]";
         }
      }
   }

   private static class FixedRateTracker extends RateTracker {
      public FixedRateTracker(ServletContext context, String filterName, String id, int numRecentRequestsTracked) {
         super(context, filterName, id, numRecentRequestsTracked);
      }

      public OverLimit isRateExceeded(long now) {
         try (AutoLock l = this._lock.lock()) {
            this._timestamps[this._next] = now;
            this._next = (this._next + 1) % this._timestamps.length;
         }

         return null;
      }

      public String toString() {
         return "Fixed" + super.toString();
      }
   }

   private static class DoSTimeoutAsyncListener implements AsyncListener {
      public void onStartAsync(AsyncEvent event) {
      }

      public void onComplete(AsyncEvent event) {
      }

      public void onTimeout(AsyncEvent event) throws IOException {
         event.getAsyncContext().dispatch();
      }

      public void onError(AsyncEvent event) {
      }
   }

   private class DoSAsyncListener extends DoSTimeoutAsyncListener {
      public DoSAsyncListener() {
      }

      public void onTimeout(AsyncEvent event) throws IOException {
         DoSFilter.this._queue.remove(event.getAsyncContext());
         super.onTimeout(event);
      }
   }

   public static enum Action {
      NO_ACTION,
      ABORT,
      REJECT,
      DELAY,
      THROTTLE;

      public static Action fromDelay(long delayMs) {
         if (delayMs < 0L) {
            return REJECT;
         } else {
            return delayMs == 0L ? THROTTLE : DELAY;
         }
      }

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{NO_ACTION, ABORT, REJECT, DELAY, THROTTLE};
      }
   }

   public static class Listener {
      public Action onRequestOverLimit(HttpServletRequest request, OverLimit overlimit, DoSFilter dosFilter) {
         Action action = DoSFilter.Action.fromDelay(dosFilter.getDelayMs());
         switch (action.ordinal()) {
            case 2:
               DoSFilter.LOG.warn("DoS ALERT: Request rejected ip={}, overlimit={}, user={}", new Object[]{request.getRemoteAddr(), overlimit, request.getUserPrincipal()});
               break;
            case 3:
               DoSFilter.LOG.warn("DoS ALERT: Request delayed={}ms, ip={}, overlimit={}, user={}", new Object[]{dosFilter.getDelayMs(), request.getRemoteAddr(), overlimit, request.getUserPrincipal()});
               break;
            case 4:
               DoSFilter.LOG.warn("DoS ALERT: Request throttled ip={}, overlimit={}, user={}", new Object[]{request.getRemoteAddr(), overlimit, request.getUserPrincipal()});
         }

         return action;
      }
   }

   public interface OverLimit {
      String getRateId();

      Duration getDuration();

      long getCount();
   }
}
