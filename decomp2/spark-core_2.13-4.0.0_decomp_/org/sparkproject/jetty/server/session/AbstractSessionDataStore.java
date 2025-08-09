package org.sparkproject.jetty.server.session;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.FuturePromise;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

@ManagedObject
public abstract class AbstractSessionDataStore extends ContainerLifeCycle implements SessionDataStore {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractSessionDataStore.class);
   public static final int DEFAULT_GRACE_PERIOD_SEC = 3600;
   public static final int DEFAULT_SAVE_PERIOD_SEC = 0;
   protected SessionContext _context;
   protected int _gracePeriodSec = 3600;
   protected long _lastExpiryCheckTime = 0L;
   protected long _lastOrphanSweepTime = 0L;
   protected int _savePeriodSec = 0;

   public abstract boolean doExists(String var1) throws Exception;

   public abstract void doStore(String var1, SessionData var2, long var3) throws Exception;

   public abstract SessionData doLoad(String var1) throws Exception;

   public abstract Set doCheckExpired(Set var1, long var2);

   public abstract Set doGetExpired(long var1);

   public abstract void doCleanOrphans(long var1);

   public void initialize(SessionContext context) throws Exception {
      if (this.isStarted()) {
         throw new IllegalStateException("Context set after SessionDataStore started");
      } else {
         this._context = context;
      }
   }

   public void cleanOrphans(long timeLimit) {
      if (!this.isStarted()) {
         throw new IllegalStateException("Not started");
      } else {
         Runnable r = () -> this.doCleanOrphans(timeLimit);
         this._context.run(r);
      }
   }

   public SessionData load(String id) throws Exception {
      if (!this.isStarted()) {
         throw new IllegalStateException("Not started");
      } else {
         FuturePromise<SessionData> result = new FuturePromise();
         Runnable r = () -> {
            try {
               result.succeeded(this.doLoad(id));
            } catch (Exception e) {
               result.failed(e);
            }

         };
         this._context.run(r);
         return (SessionData)result.getOrThrow();
      }
   }

   public void store(String id, SessionData data) throws Exception {
      if (!this.isStarted()) {
         throw new IllegalStateException("Not started");
      } else if (data != null) {
         long lastSave = data.getLastSaved();
         long savePeriodMs = this._savePeriodSec <= 0 ? 0L : TimeUnit.SECONDS.toMillis((long)this._savePeriodSec);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Store: id={}, mdirty={}, dirty={}, lsave={}, period={}, elapsed={}", new Object[]{id, data.isMetaDataDirty(), data.isDirty(), data.getLastSaved(), savePeriodMs, System.currentTimeMillis() - lastSave});
         }

         if (data.isDirty() || lastSave <= 0L || data.isMetaDataDirty() && System.currentTimeMillis() - lastSave >= savePeriodMs) {
            data.setLastSaved(System.currentTimeMillis());
            FuturePromise<Void> result = new FuturePromise();
            Runnable r = () -> {
               try {
                  this.doStore(id, data, lastSave);
                  data.clean();
                  result.succeeded((Object)null);
               } catch (Exception e) {
                  data.setLastSaved(lastSave);
                  result.failed(e);
               }

            };
            this._context.run(r);
            result.getOrThrow();
         }

      }
   }

   public boolean exists(String id) throws Exception {
      FuturePromise<Boolean> result = new FuturePromise();
      Runnable r = () -> {
         try {
            result.succeeded(this.doExists(id));
         } catch (Exception e) {
            result.failed(e);
         }

      };
      this._context.run(r);
      return (Boolean)result.getOrThrow();
   }

   public Set getExpired(Set candidates) {
      if (!this.isStarted()) {
         throw new IllegalStateException("Not started");
      } else {
         long now = System.currentTimeMillis();
         Set<String> expired = new HashSet();
         Runnable r = () -> {
            Set<String> expiredCandidates = this.doCheckExpired(candidates, now);
            if (expiredCandidates != null) {
               expired.addAll(expiredCandidates);
            }

         };
         this._context.run(r);

         try {
            long t = 0L;
            if (this._lastExpiryCheckTime <= 0L) {
               t = now - TimeUnit.SECONDS.toMillis((long)(this._gracePeriodSec * 3));
            } else if (now > this._lastExpiryCheckTime + TimeUnit.SECONDS.toMillis((long)this._gracePeriodSec)) {
               t = now - TimeUnit.SECONDS.toMillis((long)this._gracePeriodSec);
            }

            if (t > 0L) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Searching for sessions expired before {} for context {}", t, this._context.getCanonicalContextPath());
               }

               r = () -> {
                  Set<String> tmp = this.doGetExpired(t);
                  if (tmp != null) {
                     expired.addAll(tmp);
                  }

               };
               this._context.run(r);
            }
         } finally {
            this._lastExpiryCheckTime = now;
         }

         try {
            if (now > this._lastOrphanSweepTime + TimeUnit.SECONDS.toMillis((long)(10 * this._gracePeriodSec))) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Cleaning orphans at {}, last sweep at {}", now, this._lastOrphanSweepTime);
               }

               this.cleanOrphans(now - TimeUnit.SECONDS.toMillis((long)(10 * this._gracePeriodSec)));
            }
         } finally {
            this._lastOrphanSweepTime = now;
         }

         return expired;
      }
   }

   public SessionData newSessionData(String id, long created, long accessed, long lastAccessed, long maxInactiveMs) {
      return new SessionData(id, this._context.getCanonicalContextPath(), this._context.getVhost(), created, accessed, lastAccessed, maxInactiveMs);
   }

   protected void checkStarted() throws IllegalStateException {
      if (this.isStarted()) {
         throw new IllegalStateException("Already started");
      }
   }

   protected void doStart() throws Exception {
      if (this._context == null) {
         throw new IllegalStateException("No SessionContext");
      } else {
         super.doStart();
      }
   }

   @ManagedAttribute(
      value = "interval in secs to prevent too eager session scavenging",
      readonly = true
   )
   public int getGracePeriodSec() {
      return this._gracePeriodSec;
   }

   public void setGracePeriodSec(int sec) {
      this._gracePeriodSec = sec;
   }

   @ManagedAttribute(
      value = "min secs between saves",
      readonly = true
   )
   public int getSavePeriodSec() {
      return this._savePeriodSec;
   }

   public void setSavePeriodSec(int savePeriodSec) {
      this._savePeriodSec = savePeriodSec;
   }

   public String toString() {
      return String.format("%s@%x[passivating=%b,graceSec=%d]", this.getClass().getName(), this.hashCode(), this.isPassivating(), this.getGracePeriodSec());
   }
}
