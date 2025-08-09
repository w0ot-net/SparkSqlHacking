package org.sparkproject.jetty.server.session;

import jakarta.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.SessionIdManager;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.thread.AutoLock;

@ManagedObject
public class DefaultSessionIdManager extends ContainerLifeCycle implements SessionIdManager {
   private static final Logger LOG = LoggerFactory.getLogger(DefaultSessionIdManager.class);
   public static final String __NEW_SESSION_ID = "org.sparkproject.jetty.server.newSessionId";
   protected static final AtomicLong COUNTER = new AtomicLong();
   private final AutoLock _lock;
   protected Random _random;
   protected boolean _weakRandom;
   protected String _workerName;
   protected String _workerAttr;
   protected long _reseed;
   protected Server _server;
   protected HouseKeeper _houseKeeper;
   protected boolean _ownHouseKeeper;

   public DefaultSessionIdManager(Server server) {
      this._lock = new AutoLock();
      this._reseed = 100000L;
      this._server = (Server)Objects.requireNonNull(server);
      this._server.setSessionIdManager(this);
   }

   public DefaultSessionIdManager(Server server, Random random) {
      this(server);
      this._random = random;
   }

   public void setServer(Server server) {
      this._server = (Server)Objects.requireNonNull(server);
      this._server.setSessionIdManager(this);
   }

   public Server getServer() {
      return this._server;
   }

   public void setSessionHouseKeeper(HouseKeeper houseKeeper) {
      this.updateBean(this._houseKeeper, houseKeeper);
      this._houseKeeper = houseKeeper;
      this._houseKeeper.setSessionIdManager(this);
   }

   public HouseKeeper getSessionHouseKeeper() {
      return this._houseKeeper;
   }

   @ManagedAttribute(
      value = "unique name for this node",
      readonly = true
   )
   public String getWorkerName() {
      return this._workerName;
   }

   public void setWorkerName(String workerName) {
      if (this.isRunning()) {
         throw new IllegalStateException(this.getState());
      } else {
         if (workerName == null) {
            this._workerName = "";
         } else {
            if (workerName.contains(".")) {
               throw new IllegalArgumentException("Name cannot contain '.'");
            }

            this._workerName = workerName;
         }

      }
   }

   public Random getRandom() {
      return this._random;
   }

   public void setRandom(Random random) {
      this._random = random;
      this._weakRandom = false;
   }

   public long getReseed() {
      return this._reseed;
   }

   public void setReseed(long reseed) {
      this._reseed = reseed;
   }

   public String newSessionId(HttpServletRequest request, long created) {
      if (request == null) {
         return this.newSessionId(created);
      } else {
         String requestedId = request.getRequestedSessionId();
         if (requestedId != null) {
            String clusterId = this.getId(requestedId);
            if (this.isIdInUse(clusterId)) {
               return clusterId;
            }
         }

         String newId = (String)request.getAttribute("org.sparkproject.jetty.server.newSessionId");
         if (newId != null && this.isIdInUse(newId)) {
            return newId;
         } else {
            String id = this.newSessionId((long)request.hashCode());
            request.setAttribute("org.sparkproject.jetty.server.newSessionId", id);
            return id;
         }
      }
   }

   public String newSessionId(long seedTerm) {
      String id = null;

      try (AutoLock l = this._lock.lock()) {
         for(; id == null || id.length() == 0; id = id + Long.toString(COUNTER.getAndIncrement())) {
            long r0 = this._weakRandom ? (long)this.hashCode() ^ Runtime.getRuntime().freeMemory() ^ (long)this._random.nextInt() ^ seedTerm << 32 : this._random.nextLong();
            if (r0 < 0L) {
               r0 = -r0;
            }

            if (this._reseed > 0L && r0 % this._reseed == 1L) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Reseeding {}", this);
               }

               if (this._random instanceof SecureRandom) {
                  SecureRandom secure = (SecureRandom)this._random;
                  secure.setSeed(secure.generateSeed(8));
               } else {
                  this._random.setSeed(this._random.nextLong() ^ System.currentTimeMillis() ^ seedTerm ^ Runtime.getRuntime().freeMemory());
               }
            }

            long r1 = this._weakRandom ? (long)this.hashCode() ^ Runtime.getRuntime().freeMemory() ^ (long)this._random.nextInt() ^ seedTerm << 32 : this._random.nextLong();
            if (r1 < 0L) {
               r1 = -r1;
            }

            String var10000 = Long.toString(r0, 36);
            id = var10000 + Long.toString(r1, 36);
            if (!StringUtil.isBlank(this._workerName)) {
               id = this._workerName + id;
            }
         }
      }

      return id;
   }

   public boolean isIdInUse(String id) {
      if (id == null) {
         return false;
      } else {
         boolean inUse = false;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Checking {} is in use by at least one context", id);
         }

         try {
            for(SessionHandler manager : this.getSessionHandlers()) {
               if (manager.isIdInUse(id)) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Context {} reports id in use", manager);
                  }

                  inUse = true;
                  break;
               }
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Checked {}, in use: {}", id, inUse);
            }

            return inUse;
         } catch (Exception e) {
            LOG.warn("Problem checking if id {} is in use", id, e);
            return false;
         }
      }
   }

   protected void doStart() throws Exception {
      this.initRandom();
      if (this._workerName == null) {
         String inst = System.getenv("JETTY_WORKER_INSTANCE");
         this._workerName = "node" + (inst == null ? "0" : inst);
      }

      this._workerAttr = this._workerName != null && this._workerName.startsWith("$") ? this._workerName.substring(1) : null;
      if (this._houseKeeper == null) {
         this._ownHouseKeeper = true;
         this._houseKeeper = new HouseKeeper();
         this._houseKeeper.setSessionIdManager(this);
         this.addBean(this._houseKeeper, true);
      }

      LOG.info("Session workerName={}", this._workerName);
      this._houseKeeper.start();
   }

   protected void doStop() throws Exception {
      this._houseKeeper.stop();
      if (this._ownHouseKeeper) {
         this.removeBean(this._houseKeeper);
         this._houseKeeper = null;
      }

      this._random = null;
   }

   public void initRandom() {
      if (this._random == null) {
         try {
            this._random = new SecureRandom();
         } catch (Exception e) {
            LOG.warn("Could not generate SecureRandom for session-id randomness", e);
            this._random = new Random();
            this._weakRandom = true;
         }
      } else {
         this._random.setSeed(this._random.nextLong() ^ System.currentTimeMillis() ^ (long)this.hashCode() ^ Runtime.getRuntime().freeMemory());
      }

   }

   public String getExtendedId(String clusterId, HttpServletRequest request) {
      if (!StringUtil.isBlank(this._workerName)) {
         if (this._workerAttr == null) {
            return clusterId + "." + this._workerName;
         }

         String worker = (String)request.getAttribute(this._workerAttr);
         if (worker != null) {
            return clusterId + "." + worker;
         }
      }

      return clusterId;
   }

   public String getId(String extendedId) {
      int dot = extendedId.lastIndexOf(46);
      return dot > 0 ? extendedId.substring(0, dot) : extendedId;
   }

   public void expireAll(String id) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Expiring {}", id);
      }

      for(SessionHandler manager : this.getSessionHandlers()) {
         manager.invalidate(id);
      }

   }

   public void invalidateAll(String id) {
      for(SessionHandler manager : this.getSessionHandlers()) {
         manager.invalidate(id);
      }

   }

   public String renewSessionId(String oldClusterId, String oldNodeId, HttpServletRequest request) {
      String newClusterId = this.newSessionId((long)request.hashCode());

      for(SessionHandler manager : this.getSessionHandlers()) {
         manager.renewSessionId(oldClusterId, oldNodeId, newClusterId, this.getExtendedId(newClusterId, request));
      }

      return newClusterId;
   }

   public Set getSessionHandlers() {
      Set<SessionHandler> handlers = new HashSet();
      Handler[] tmp = this._server.getChildHandlersByClass(SessionHandler.class);
      if (tmp != null) {
         for(Handler h : tmp) {
            if (!h.isStopped() && !h.isFailed()) {
               handlers.add((SessionHandler)h);
            }
         }
      }

      return handlers;
   }

   public String toString() {
      return String.format("%s[worker=%s]", super.toString(), this._workerName);
   }
}
