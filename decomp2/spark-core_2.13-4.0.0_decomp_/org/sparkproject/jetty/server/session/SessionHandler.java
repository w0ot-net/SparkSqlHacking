package org.sparkproject.jetty.server.session;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionContext;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;
import jakarta.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.HttpCookie;
import org.sparkproject.jetty.http.Syntax;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.SessionIdManager;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.ScopedHandler;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.statistic.CounterStatistic;
import org.sparkproject.jetty.util.statistic.SampleStatistic;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import org.sparkproject.jetty.util.thread.Scheduler;

@ManagedObject
public class SessionHandler extends ScopedHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SessionHandler.class);
   public static final EnumSet DEFAULT_TRACKING;
   public static final String __SessionCookieProperty = "org.sparkproject.jetty.servlet.SessionCookie";
   public static final String __DefaultSessionCookie = "JSESSIONID";
   public static final String __SessionIdPathParameterNameProperty = "org.sparkproject.jetty.servlet.SessionIdPathParameterName";
   public static final String __DefaultSessionIdPathParameterName = "jsessionid";
   public static final String __CheckRemoteSessionEncoding = "org.sparkproject.jetty.servlet.CheckingRemoteSessionIdEncoding";
   public static final String __SessionDomainProperty = "org.sparkproject.jetty.servlet.SessionDomain";
   public static final String __DefaultSessionDomain;
   public static final String __SessionPathProperty = "org.sparkproject.jetty.servlet.SessionPath";
   public static final String __MaxAgeProperty = "org.sparkproject.jetty.servlet.MaxAge";
   public static final Set DEFAULT_SESSION_TRACKING_MODES;
   public static final Class[] SESSION_LISTENER_TYPES;
   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   static final HttpSessionContext __nullSessionContext;
   protected int _dftMaxIdleSecs = -1;
   protected boolean _httpOnly = false;
   protected SessionIdManager _sessionIdManager;
   protected boolean _secureCookies = false;
   protected boolean _secureRequestOnly = true;
   protected final List _sessionAttributeListeners = new CopyOnWriteArrayList();
   protected final List _sessionListeners = new CopyOnWriteArrayList();
   protected final List _sessionIdListeners = new CopyOnWriteArrayList();
   protected ClassLoader _loader;
   protected ContextHandler.Context _context;
   protected SessionContext _sessionContext;
   protected String _sessionCookie = "JSESSIONID";
   protected String _sessionIdPathParameterName = "jsessionid";
   protected String _sessionIdPathParameterNamePrefix;
   protected String _sessionDomain;
   protected String _sessionPath;
   protected int _maxCookieAge;
   protected int _refreshCookieAge;
   protected boolean _checkingRemoteSessionIdEncoding;
   protected String _sessionComment;
   protected SessionCache _sessionCache;
   protected final SampleStatistic _sessionTimeStats;
   protected final CounterStatistic _sessionsCreatedStats;
   public Set _sessionTrackingModes;
   protected boolean _usingURLs;
   protected boolean _usingCookies;
   protected Set _candidateSessionIdsForExpiry;
   protected Scheduler _scheduler;
   protected boolean _ownScheduler;
   private SessionCookieConfig _cookieConfig;

   public SessionHandler() {
      this._sessionIdPathParameterNamePrefix = ";" + this._sessionIdPathParameterName + "=";
      this._maxCookieAge = -1;
      this._sessionTimeStats = new SampleStatistic();
      this._sessionsCreatedStats = new CounterStatistic();
      this._usingCookies = true;
      this._candidateSessionIdsForExpiry = ConcurrentHashMap.newKeySet();
      this._ownScheduler = false;
      this._cookieConfig = new CookieConfig();
      this.setSessionTrackingModes(DEFAULT_SESSION_TRACKING_MODES);
   }

   @ManagedAttribute("path of the session cookie, or null for default")
   public String getSessionPath() {
      return this._sessionPath;
   }

   @ManagedAttribute("if greater the zero, the time in seconds a session cookie will last for")
   public int getMaxCookieAge() {
      return this._maxCookieAge;
   }

   public HttpCookie access(HttpSession session, boolean secure) {
      long now = System.currentTimeMillis();
      Session s = ((SessionIf)session).getSession();
      if (s.access(now) && this.isUsingCookies() && (s.isIdChanged() || this.getSessionCookieConfig().getMaxAge() > 0 && this.getRefreshCookieAge() > 0 && (now - s.getCookieSetTime()) / 1000L > (long)this.getRefreshCookieAge())) {
         HttpCookie cookie = this.getSessionCookie(session, this._context == null ? "/" : this._context.getContextPath(), secure);
         s.cookieSet();
         s.setIdChanged(false);
         return cookie;
      } else {
         return null;
      }
   }

   public boolean addEventListener(EventListener listener) {
      if (super.addEventListener(listener)) {
         if (listener instanceof HttpSessionAttributeListener) {
            this._sessionAttributeListeners.add((HttpSessionAttributeListener)listener);
         }

         if (listener instanceof HttpSessionListener) {
            this._sessionListeners.add((HttpSessionListener)listener);
         }

         if (listener instanceof HttpSessionIdListener) {
            this._sessionIdListeners.add((HttpSessionIdListener)listener);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void callSessionDestroyedListeners(final Session session) {
      if (session != null) {
         if (this._sessionListeners != null) {
            Runnable r = new Runnable() {
               public void run() {
                  HttpSessionEvent event = new HttpSessionEvent(session);

                  for(int i = SessionHandler.this._sessionListeners.size() - 1; i >= 0; --i) {
                     ((HttpSessionListener)SessionHandler.this._sessionListeners.get(i)).sessionDestroyed(event);
                  }

               }
            };
            this._sessionContext.run(r);
         }

      }
   }

   protected void callSessionCreatedListeners(Session session) {
      if (session != null) {
         if (this._sessionListeners != null) {
            HttpSessionEvent event = new HttpSessionEvent(session);

            for(HttpSessionListener l : this._sessionListeners) {
               l.sessionCreated(event);
            }
         }

      }
   }

   protected void callSessionIdListeners(Session session, String oldId) {
      if (!this._sessionIdListeners.isEmpty()) {
         HttpSessionEvent event = new HttpSessionEvent(session);

         for(HttpSessionIdListener l : this._sessionIdListeners) {
            l.sessionIdChanged(event, oldId);
         }
      }

   }

   public void complete(HttpSession session) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Complete called with session {}", session);
      }

      if (session != null) {
         Session s = ((SessionIf)session).getSession();

         try {
            this._sessionCache.release(s.getId(), s);
         } catch (Exception e) {
            LOG.warn("Unable to release Session {}", s, e);
         }

      }
   }

   public void commit(HttpSession session) {
      if (session != null) {
         Session s = ((SessionIf)session).getSession();

         try {
            this._sessionCache.commit(s);
         } catch (Exception e) {
            LOG.warn("Unable to commit Session {}", s, e);
         }

      }
   }

   protected void doStart() throws Exception {
      Server server = this.getServer();
      this._context = ContextHandler.getCurrentContext();
      this._loader = Thread.currentThread().getContextClassLoader();
      synchronized(server) {
         if (this._sessionCache == null) {
            SessionCacheFactory ssFactory = (SessionCacheFactory)server.getBean(SessionCacheFactory.class);
            this.setSessionCache((SessionCache)(ssFactory != null ? ssFactory.getSessionCache(this) : new DefaultSessionCache(this)));
            SessionDataStore sds = null;
            SessionDataStoreFactory sdsFactory = (SessionDataStoreFactory)server.getBean(SessionDataStoreFactory.class);
            if (sdsFactory != null) {
               sds = sdsFactory.getSessionDataStore(this);
            } else {
               sds = new NullSessionDataStore();
            }

            this._sessionCache.setSessionDataStore(sds);
         }

         if (this._sessionIdManager == null) {
            this._sessionIdManager = server.getSessionIdManager();
            if (this._sessionIdManager == null) {
               ClassLoader serverLoader = server.getClass().getClassLoader();

               try {
                  Thread.currentThread().setContextClassLoader(serverLoader);
                  this._sessionIdManager = new DefaultSessionIdManager(server);
                  server.manage(this._sessionIdManager);
                  this._sessionIdManager.start();
               } finally {
                  Thread.currentThread().setContextClassLoader(this._loader);
               }
            }

            this.addBean(this._sessionIdManager, false);
         }

         this._scheduler = (Scheduler)server.getBean(Scheduler.class);
         if (this._scheduler == null) {
            this._scheduler = new ScheduledExecutorScheduler(String.format("Session-Scheduler-%x", this.hashCode()), false);
            this._ownScheduler = true;
            this._scheduler.start();
         }
      }

      if (this._context != null) {
         String tmp = this._context.getInitParameter("org.sparkproject.jetty.servlet.SessionCookie");
         if (tmp != null) {
            this._sessionCookie = tmp;
         }

         tmp = this._context.getInitParameter("org.sparkproject.jetty.servlet.SessionIdPathParameterName");
         if (tmp != null) {
            this.setSessionIdPathParameterName(tmp);
         }

         if (this._maxCookieAge == -1) {
            tmp = this._context.getInitParameter("org.sparkproject.jetty.servlet.MaxAge");
            if (tmp != null) {
               this._maxCookieAge = Integer.parseInt(tmp.trim());
            }
         }

         if (this._sessionDomain == null) {
            this._sessionDomain = this._context.getInitParameter("org.sparkproject.jetty.servlet.SessionDomain");
         }

         if (this._sessionPath == null) {
            this._sessionPath = this._context.getInitParameter("org.sparkproject.jetty.servlet.SessionPath");
         }

         tmp = this._context.getInitParameter("org.sparkproject.jetty.servlet.CheckingRemoteSessionIdEncoding");
         if (tmp != null) {
            this._checkingRemoteSessionIdEncoding = Boolean.parseBoolean(tmp);
         }
      }

      this._sessionContext = new SessionContext(this._sessionIdManager.getWorkerName(), this._context);
      this._sessionCache.initialize(this._sessionContext);
      super.doStart();
   }

   protected void doStop() throws Exception {
      this.shutdownSessions();
      this._sessionCache.stop();
      if (this._ownScheduler && this._scheduler != null) {
         this._scheduler.stop();
      }

      this._scheduler = null;
      super.doStop();
      this._loader = null;
   }

   @ManagedAttribute("true if cookies use the http only flag")
   public boolean getHttpOnly() {
      return this._httpOnly;
   }

   @ManagedAttribute("SameSite setting for session cookies")
   public HttpCookie.SameSite getSameSite() {
      return HttpCookie.getSameSiteFromComment(this._sessionComment);
   }

   protected HttpSession getHttpSession(String extendedId) {
      String id = this.getSessionIdManager().getId(extendedId);
      Session session = this.getSession(id);
      if (session != null && !session.getExtendedId().equals(extendedId)) {
         session.setIdChanged(true);
      }

      return session;
   }

   @ManagedAttribute("Session ID Manager")
   public SessionIdManager getSessionIdManager() {
      return this._sessionIdManager;
   }

   @ManagedAttribute("default maximum time a session may be idle for (in s)")
   public int getMaxInactiveInterval() {
      return this._dftMaxIdleSecs;
   }

   @ManagedAttribute("time before a session cookie is re-set (in s)")
   public int getRefreshCookieAge() {
      return this._refreshCookieAge;
   }

   @ManagedAttribute("if true, secure cookie flag is set on session cookies")
   public boolean getSecureCookies() {
      return this._secureCookies;
   }

   public boolean isSecureRequestOnly() {
      return this._secureRequestOnly;
   }

   public void setSecureRequestOnly(boolean secureRequestOnly) {
      this._secureRequestOnly = secureRequestOnly;
   }

   @ManagedAttribute("the set session cookie")
   public String getSessionCookie() {
      return this._sessionCookie;
   }

   public HttpCookie getSessionCookie(HttpSession session, String contextPath, boolean requestIsSecure) {
      if (!this.isUsingCookies()) {
         return null;
      } else {
         SessionCookieConfig cookieConfig = this.getSessionCookieConfig();
         String sessionPath = cookieConfig.getPath() == null ? contextPath : cookieConfig.getPath();
         sessionPath = StringUtil.isEmpty(sessionPath) ? "/" : sessionPath;
         String id = this.getExtendedId(session);
         String comment = cookieConfig.getComment();
         return new HttpCookie(getSessionCookieName(this._cookieConfig), id, cookieConfig.getDomain(), sessionPath, (long)cookieConfig.getMaxAge(), cookieConfig.isHttpOnly(), cookieConfig.isSecure() || this.isSecureRequestOnly() && requestIsSecure, HttpCookie.getCommentWithoutAttributes(comment), 0, HttpCookie.getSameSiteFromComment(comment), HttpCookie.isPartitionedInComment(comment));
      }
   }

   @ManagedAttribute("domain of the session cookie, or null for the default")
   public String getSessionDomain() {
      return this._sessionDomain;
   }

   @ManagedAttribute("number of sessions created by this node")
   public int getSessionsCreated() {
      return (int)this._sessionsCreatedStats.getCurrent();
   }

   @ManagedAttribute("name of use for URL session tracking")
   public String getSessionIdPathParameterName() {
      return this._sessionIdPathParameterName;
   }

   public String getSessionIdPathParameterNamePrefix() {
      return this._sessionIdPathParameterNamePrefix;
   }

   public boolean isUsingCookies() {
      return this._usingCookies;
   }

   public boolean isValid(HttpSession session) {
      Session s = ((SessionIf)session).getSession();
      return s.isValid();
   }

   public String getId(HttpSession session) {
      Session s = ((SessionIf)session).getSession();
      return s.getId();
   }

   public String getExtendedId(HttpSession session) {
      Session s = ((SessionIf)session).getSession();
      return s.getExtendedId();
   }

   public HttpSession newHttpSession(HttpServletRequest request) {
      long created = System.currentTimeMillis();
      String id = this._sessionIdManager.newSessionId(request, created);
      Session session = this._sessionCache.newSession(request, id, created, this._dftMaxIdleSecs > 0 ? (long)this._dftMaxIdleSecs * 1000L : -1L);
      session.setExtendedId(this._sessionIdManager.getExtendedId(id, request));
      session.getSessionData().setLastNode(this._sessionIdManager.getWorkerName());

      try {
         this._sessionCache.add(id, session);
         Request baseRequest = Request.getBaseRequest(request);
         baseRequest.setSession(session);
         baseRequest.enterSession(session);
         this._sessionsCreatedStats.increment();
         if (request != null && request.isSecure()) {
            session.setAttribute("org.sparkproject.jetty.security.sessionCreatedSecure", Boolean.TRUE);
         }

         this.callSessionCreatedListeners(session);
         return session;
      } catch (Exception e) {
         LOG.warn("Unable to add Session {}", id, e);
         return null;
      }
   }

   public boolean removeEventListener(EventListener listener) {
      if (super.removeEventListener(listener)) {
         if (listener instanceof HttpSessionAttributeListener) {
            this._sessionAttributeListeners.remove(listener);
         }

         if (listener instanceof HttpSessionListener) {
            this._sessionListeners.remove(listener);
         }

         if (listener instanceof HttpSessionIdListener) {
            this._sessionIdListeners.remove(listener);
         }

         return true;
      } else {
         return false;
      }
   }

   @ManagedOperation(
      value = "reset statistics",
      impact = "ACTION"
   )
   public void statsReset() {
      this._sessionsCreatedStats.reset();
      this._sessionTimeStats.reset();
   }

   public void setHttpOnly(boolean httpOnly) {
      this._httpOnly = httpOnly;
   }

   public void setPartitioned(boolean partitioned) {
      this._sessionComment = HttpCookie.getCommentWithAttributes(this._sessionComment, false, (HttpCookie.SameSite)null, partitioned);
   }

   public void setSameSite(HttpCookie.SameSite sameSite) {
      this._sessionComment = HttpCookie.getCommentWithAttributes(this._sessionComment, false, sameSite);
   }

   public void setSessionIdManager(SessionIdManager metaManager) {
      this.updateBean(this._sessionIdManager, metaManager);
      this._sessionIdManager = metaManager;
   }

   public void setMaxInactiveInterval(int seconds) {
      this._dftMaxIdleSecs = seconds;
      if (LOG.isDebugEnabled()) {
         if (this._dftMaxIdleSecs <= 0) {
            LOG.debug("Sessions created by this manager are immortal (default maxInactiveInterval={})", this._dftMaxIdleSecs);
         } else {
            LOG.debug("SessionManager default maxInactiveInterval={}", this._dftMaxIdleSecs);
         }
      }

   }

   public void setRefreshCookieAge(int ageInSeconds) {
      this._refreshCookieAge = ageInSeconds;
   }

   public void setSessionCookie(String cookieName) {
      this._sessionCookie = cookieName;
   }

   public void setSessionIdPathParameterName(String param) {
      this._sessionIdPathParameterName = param != null && !"none".equals(param) ? param : null;
      this._sessionIdPathParameterNamePrefix = param != null && !"none".equals(param) ? ";" + this._sessionIdPathParameterName + "=" : null;
   }

   public void setUsingCookies(boolean usingCookies) {
      this._usingCookies = usingCookies;
   }

   public Session getSession(String id) {
      try {
         Session session = this._sessionCache.get(id);
         if (session != null) {
            if (session.isExpiredAt(System.currentTimeMillis())) {
               try {
                  session.invalidate();
               } catch (Exception e) {
                  LOG.warn("Invalidating session {} found to be expired when requested", id, e);
               }

               return null;
            }

            session.setExtendedId(this._sessionIdManager.getExtendedId(id, (HttpServletRequest)null));
         }

         return session;
      } catch (UnreadableSessionDataException e) {
         LOG.warn("Error loading session {}", id, e);

         try {
            this.getSessionIdManager().invalidateAll(id);
         } catch (Exception x) {
            LOG.warn("Error cross-context invalidating unreadable session {}", id, x);
         }

         return null;
      } catch (Exception other) {
         LOG.warn("Unable to get Session", other);
         return null;
      }
   }

   protected void shutdownSessions() throws Exception {
      this._sessionCache.shutdown();
   }

   public SessionCache getSessionCache() {
      return this._sessionCache;
   }

   public void setSessionCache(SessionCache cache) {
      this.updateBean(this._sessionCache, cache);
      this._sessionCache = cache;
   }

   public Session removeSession(String id, boolean invalidate) {
      try {
         Session session = this._sessionCache.delete(id);
         if (session != null && invalidate) {
            session.beginInvalidate();
            if (this._sessionListeners != null) {
               HttpSessionEvent event = new HttpSessionEvent(session);

               for(int i = this._sessionListeners.size() - 1; i >= 0; --i) {
                  ((HttpSessionListener)this._sessionListeners.get(i)).sessionDestroyed(event);
               }
            }
         }

         return session;
      } catch (Exception e) {
         LOG.warn("Unable to remove Session", e);
         return null;
      }
   }

   @ManagedAttribute("maximum amount of time sessions have remained active (in s)")
   public long getSessionTimeMax() {
      return this._sessionTimeStats.getMax();
   }

   public Set getDefaultSessionTrackingModes() {
      return DEFAULT_SESSION_TRACKING_MODES;
   }

   public Set getEffectiveSessionTrackingModes() {
      return Collections.unmodifiableSet(this._sessionTrackingModes);
   }

   public void setSessionTrackingModes(Set sessionTrackingModes) {
      if (sessionTrackingModes != null && sessionTrackingModes.size() > 1 && sessionTrackingModes.contains(SessionTrackingMode.SSL)) {
         throw new IllegalArgumentException("sessionTrackingModes specifies a combination of SessionTrackingMode.SSL with a session tracking mode other than SessionTrackingMode.SSL");
      } else {
         this._sessionTrackingModes = new HashSet(sessionTrackingModes);
         this._usingCookies = this._sessionTrackingModes.contains(SessionTrackingMode.COOKIE);
         this._usingURLs = this._sessionTrackingModes.contains(SessionTrackingMode.URL);
      }
   }

   public boolean isUsingURLs() {
      return this._usingURLs;
   }

   public SessionCookieConfig getSessionCookieConfig() {
      return this._cookieConfig;
   }

   @ManagedAttribute("total time sessions have remained valid")
   public long getSessionTimeTotal() {
      return this._sessionTimeStats.getTotal();
   }

   @ManagedAttribute("mean time sessions remain valid (in s)")
   public double getSessionTimeMean() {
      return this._sessionTimeStats.getMean();
   }

   @ManagedAttribute("standard deviation a session remained valid (in s)")
   public double getSessionTimeStdDev() {
      return this._sessionTimeStats.getStdDev();
   }

   @ManagedAttribute("check remote session id encoding")
   public boolean isCheckingRemoteSessionIdEncoding() {
      return this._checkingRemoteSessionIdEncoding;
   }

   public void setCheckingRemoteSessionIdEncoding(boolean remote) {
      this._checkingRemoteSessionIdEncoding = remote;
   }

   public void renewSessionId(String oldId, String oldExtendedId, String newId, String newExtendedId) {
      Session session = null;

      try {
         session = this._sessionCache.renewSessionId(oldId, newId, oldExtendedId, newExtendedId);
         if (session != null) {
            this.callSessionIdListeners(session, oldId);
            return;
         }
      } catch (Exception e) {
         LOG.warn("Unable to renew Session Id {}:{} -> {}:{}", new Object[]{oldId, oldExtendedId, newId, newExtendedId, e});
         return;
      } finally {
         if (session != null) {
            try {
               this._sessionCache.release(newId, session);
            } catch (Exception e) {
               LOG.warn("Unable to release {}", newId, e);
            }
         }

      }

   }

   protected void recordSessionTime(Session session) {
      this._sessionTimeStats.record(Math.round((double)(System.currentTimeMillis() - session.getSessionData().getCreated()) / (double)1000.0F));
   }

   public void invalidate(String id) {
      if (!StringUtil.isBlank(id)) {
         try {
            Session session = this._sessionCache.delete(id);
            if (session != null) {
               try {
                  if (session.beginInvalidate()) {
                     try {
                        this.callSessionDestroyedListeners(session);
                     } catch (Exception e) {
                        LOG.warn("Error during Session destroy listener", e);
                     }

                     session.finishInvalidate();
                  }
               } catch (IllegalStateException e) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Session {} already invalid", session, e);
                  }
               }
            }
         } catch (Exception e) {
            LOG.warn("Unable to delete Session {}", id, e);
         }

      }
   }

   public void scavenge() {
      if (!this.isStopping() && !this.isStopped()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} scavenging sessions", this);
         }

         String[] ss = (String[])this._candidateSessionIdsForExpiry.toArray(new String[0]);
         Set<String> candidates = new HashSet(Arrays.asList(ss));
         this._candidateSessionIdsForExpiry.removeAll(candidates);
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} scavenging session ids {}", this, candidates);
         }

         try {
            for(String id : this._sessionCache.checkExpiration(candidates)) {
               try {
                  this.getSessionIdManager().expireAll(id);
               } catch (Exception e) {
                  LOG.warn("Unable to expire Session {}", id, e);
               }
            }
         } catch (Exception e) {
            LOG.warn("Failed to check expiration on {}", candidates.stream().map(Objects::toString).collect(Collectors.joining(", ", "[", "]")), e);
         }

      }
   }

   public void sessionInactivityTimerExpired(Session session, long now) {
      if (session != null) {
         try (AutoLock lock = session.lock()) {
            if (session.getRequests() > 0L) {
               return;
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Inspecting session {}, valid={}", session.getId(), session.isValid());
            }

            if (!session.isValid()) {
               return;
            }

            if (session.isExpiredAt(now)) {
               if (this._sessionIdManager.getSessionHouseKeeper() != null && this._sessionIdManager.getSessionHouseKeeper().getIntervalSec() > 0L) {
                  this._candidateSessionIdsForExpiry.add(session.getId());
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Session {} is candidate for expiry", session.getId());
                  }
               }
            } else {
               this._sessionCache.checkInactiveSession(session);
            }
         }

      }
   }

   public boolean isIdInUse(String id) throws Exception {
      return this._sessionCache.exists(id);
   }

   public Scheduler getScheduler() {
      return this._scheduler;
   }

   public static String getSessionCookieName(SessionCookieConfig config) {
      return config != null && config.getName() != null ? config.getName() : "JSESSIONID";
   }

   public void doSessionAttributeListeners(Session session, String name, Object old, Object value) {
      if (!this._sessionAttributeListeners.isEmpty()) {
         HttpSessionBindingEvent event = new HttpSessionBindingEvent(session, name, old == null ? value : old);

         for(HttpSessionAttributeListener l : this._sessionAttributeListeners) {
            if (old == null) {
               l.attributeAdded(event);
            } else if (value == null) {
               l.attributeRemoved(event);
            } else {
               l.attributeReplaced(event);
            }
         }
      }

   }

   public void doScope(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      SessionHandler oldSessionHandler = null;
      HttpSession oldSession = null;
      HttpSession existingSession = null;

      try {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Entering scope {}, dispatch={} asyncstarted={}", new Object[]{this, baseRequest.getDispatcherType(), baseRequest.isAsyncStarted()});
         }

         switch (baseRequest.getDispatcherType()) {
            case REQUEST:
               baseRequest.setSession((HttpSession)null);
               this.checkRequestedSessionId(baseRequest, request);
               existingSession = baseRequest.getSession(false);
               baseRequest.setSessionHandler(this);
               baseRequest.setSession(existingSession);
               break;
            case ASYNC:
            case ERROR:
            case FORWARD:
            case INCLUDE:
               oldSessionHandler = baseRequest.getSessionHandler();
               oldSession = baseRequest.getSession(false);
               if (oldSessionHandler != this) {
                  existingSession = baseRequest.getSession(this);
                  if (existingSession == null) {
                     baseRequest.setSession((HttpSession)null);
                     this.checkRequestedSessionId(baseRequest, request);
                     existingSession = baseRequest.getSession(false);
                  }

                  baseRequest.setSession(existingSession);
                  baseRequest.setSessionHandler(this);
               }
         }

         if (existingSession != null && oldSessionHandler != this) {
            HttpCookie cookie = this.access(existingSession, request.isSecure());
            if (cookie != null && (request.getDispatcherType() == DispatcherType.ASYNC || request.getDispatcherType() == DispatcherType.REQUEST)) {
               baseRequest.getResponse().replaceCookie(cookie);
            }
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("sessionHandler={} session={}", this, existingSession);
         }

         if (this._nextScope != null) {
            this._nextScope.doScope(target, baseRequest, request, response);
         } else if (this._outerScope != null) {
            this._outerScope.doHandle(target, baseRequest, request, response);
         } else {
            this.doHandle(target, baseRequest, request, response);
         }
      } finally {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Leaving scope {} dispatch={}, async={}, session={}, oldsession={}, oldsessionhandler={}", new Object[]{this, baseRequest.getDispatcherType(), baseRequest.isAsyncStarted(), baseRequest.getSession(false), oldSession, oldSessionHandler});
         }

         if (oldSessionHandler != null && oldSessionHandler != this) {
            baseRequest.setSessionHandler(oldSessionHandler);
            baseRequest.setSession(oldSession);
         }

      }

   }

   public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      this.nextHandle(target, baseRequest, request, response);
   }

   protected void checkRequestedSessionId(Request baseRequest, HttpServletRequest request) {
      String requestedSessionId = request.getRequestedSessionId();
      if (requestedSessionId != null) {
         HttpSession session = this.getHttpSession(requestedSessionId);
         if (session != null && this.isValid(session)) {
            baseRequest.enterSession(session);
            baseRequest.setSession(session);
         }

      } else if (DispatcherType.REQUEST.equals(baseRequest.getDispatcherType())) {
         boolean requestedSessionIdFromCookie = false;
         HttpSession session = null;
         if (this.isUsingCookies()) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
               String sessionCookie = getSessionCookieName(this.getSessionCookieConfig());

               for(Cookie cookie : cookies) {
                  if (sessionCookie.equalsIgnoreCase(cookie.getName())) {
                     String id = cookie.getValue();
                     requestedSessionIdFromCookie = true;
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Got Session ID {} from cookie {}", id, sessionCookie);
                     }

                     if (session == null) {
                        HttpSession s = this.getHttpSession(id);
                        if (s != null && this.isValid(s)) {
                           requestedSessionId = id;
                           session = s;
                           baseRequest.enterSession(s);
                           baseRequest.setSession(s);
                           if (LOG.isDebugEnabled()) {
                              LOG.debug("Selected session {}", s);
                           }
                        } else {
                           if (LOG.isDebugEnabled()) {
                              LOG.debug("No session found for session cookie id {}", id);
                           }

                           if (requestedSessionId == null) {
                              requestedSessionId = id;
                           }
                        }
                     } else if (!session.getId().equals(this.getSessionIdManager().getId(id))) {
                        HttpSession s = this.getHttpSession(id);
                        if (s != null && this.isValid(s)) {
                           baseRequest.enterSession(s);
                           if (LOG.isDebugEnabled()) {
                              LOG.debug("Multiple different valid session ids: {}, {}", requestedSessionId, id);
                           }

                           throw new BadMessageException("Duplicate valid session cookies: " + requestedSessionId + " ," + id);
                        }
                     } else if (LOG.isDebugEnabled()) {
                        LOG.debug("Duplicate valid session cookie id: {}", id);
                     }
                  }
               }
            }
         }

         if (this.isUsingURLs() && requestedSessionId == null) {
            String uri = request.getRequestURI();
            if (uri != null) {
               String prefix = this.getSessionIdPathParameterNamePrefix();
               if (prefix != null) {
                  int s = uri.indexOf(prefix);
                  if (s >= 0) {
                     s += prefix.length();

                     int i;
                     for(i = s; i < uri.length(); ++i) {
                        char c = uri.charAt(i);
                        if (c == ';' || c == '#' || c == '?' || c == '/') {
                           break;
                        }
                     }

                     requestedSessionId = uri.substring(s, i);
                     requestedSessionIdFromCookie = false;
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Got Session ID {} from URL", requestedSessionId);
                     }

                     session = this.getHttpSession(requestedSessionId);
                     if (session != null && this.isValid(session)) {
                        baseRequest.enterSession(session);
                        baseRequest.setSession(session);
                     }
                  }
               }
            }
         }

         baseRequest.setRequestedSessionId(requestedSessionId);
         baseRequest.setRequestedSessionIdFromCookie(requestedSessionId != null && requestedSessionIdFromCookie);
      }
   }

   public String toString() {
      return String.format("%s%d==dftMaxIdleSec=%d", this.getClass().getName(), this.hashCode(), this._dftMaxIdleSecs);
   }

   static {
      DEFAULT_TRACKING = EnumSet.of(SessionTrackingMode.COOKIE, SessionTrackingMode.URL);
      __DefaultSessionDomain = null;
      DEFAULT_SESSION_TRACKING_MODES = Collections.unmodifiableSet(new HashSet(Arrays.asList(SessionTrackingMode.COOKIE, SessionTrackingMode.URL)));
      SESSION_LISTENER_TYPES = new Class[]{HttpSessionAttributeListener.class, HttpSessionIdListener.class, HttpSessionListener.class};
      __nullSessionContext = new HttpSessionContext() {
         /** @deprecated */
         @Deprecated(
            since = "Servlet API 2.1"
         )
         public HttpSession getSession(String sessionId) {
            return null;
         }

         /** @deprecated */
         @Deprecated(
            since = "Servlet API 2.1"
         )
         public Enumeration getIds() {
            return Collections.enumeration(Collections.EMPTY_LIST);
         }
      };
   }

   public final class CookieConfig implements SessionCookieConfig {
      public String getComment() {
         return SessionHandler.this._sessionComment;
      }

      public String getDomain() {
         return SessionHandler.this._sessionDomain;
      }

      public int getMaxAge() {
         return SessionHandler.this._maxCookieAge;
      }

      public String getName() {
         return SessionHandler.this._sessionCookie;
      }

      public String getPath() {
         return SessionHandler.this._sessionPath;
      }

      public boolean isHttpOnly() {
         return SessionHandler.this._httpOnly;
      }

      public boolean isSecure() {
         return SessionHandler.this._secureCookies;
      }

      public void setComment(String comment) {
         if (SessionHandler.this._context != null && SessionHandler.this._context.getContextHandler().isAvailable()) {
            throw new IllegalStateException("CookieConfig cannot be set after ServletContext is started");
         } else {
            SessionHandler.this._sessionComment = comment;
         }
      }

      public void setDomain(String domain) {
         if (SessionHandler.this._context != null && SessionHandler.this._context.getContextHandler().isAvailable()) {
            throw new IllegalStateException("CookieConfig cannot be set after ServletContext is started");
         } else {
            SessionHandler.this._sessionDomain = domain;
         }
      }

      public void setHttpOnly(boolean httpOnly) {
         if (SessionHandler.this._context != null && SessionHandler.this._context.getContextHandler().isAvailable()) {
            throw new IllegalStateException("CookieConfig cannot be set after ServletContext is started");
         } else {
            SessionHandler.this._httpOnly = httpOnly;
         }
      }

      public void setMaxAge(int maxAge) {
         if (SessionHandler.this._context != null && SessionHandler.this._context.getContextHandler().isAvailable()) {
            throw new IllegalStateException("CookieConfig cannot be set after ServletContext is started");
         } else {
            SessionHandler.this._maxCookieAge = maxAge;
         }
      }

      public void setName(String name) {
         if (SessionHandler.this._context != null && SessionHandler.this._context.getContextHandler().isAvailable()) {
            throw new IllegalStateException("CookieConfig cannot be set after ServletContext is started");
         } else if ("".equals(name)) {
            throw new IllegalArgumentException("Blank cookie name");
         } else {
            if (name != null) {
               Syntax.requireValidRFC2616Token(name, "Bad Session cookie name");
            }

            SessionHandler.this._sessionCookie = name;
         }
      }

      public void setPath(String path) {
         if (SessionHandler.this._context != null && SessionHandler.this._context.getContextHandler().isAvailable()) {
            throw new IllegalStateException("CookieConfig cannot be set after ServletContext is started");
         } else {
            SessionHandler.this._sessionPath = path;
         }
      }

      public void setSecure(boolean secure) {
         if (SessionHandler.this._context != null && SessionHandler.this._context.getContextHandler().isAvailable()) {
            throw new IllegalStateException("CookieConfig cannot be set after ServletContext is started");
         } else {
            SessionHandler.this._secureCookies = secure;
         }
      }
   }

   public interface SessionIf extends HttpSession {
      Session getSession();
   }
}
