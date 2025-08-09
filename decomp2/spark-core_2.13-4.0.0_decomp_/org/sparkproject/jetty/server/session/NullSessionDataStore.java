package org.sparkproject.jetty.server.session;

import java.util.Collections;
import java.util.Set;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject
public class NullSessionDataStore extends AbstractSessionDataStore {
   public SessionData doLoad(String id) throws Exception {
      return null;
   }

   public SessionData newSessionData(String id, long created, long accessed, long lastAccessed, long maxInactiveMs) {
      return new SessionData(id, this._context.getCanonicalContextPath(), this._context.getVhost(), created, accessed, lastAccessed, maxInactiveMs);
   }

   public boolean delete(String id) throws Exception {
      return true;
   }

   public void doStore(String id, SessionData data, long lastSaveTime) throws Exception {
   }

   public Set doCheckExpired(Set candidates, long time) {
      return candidates;
   }

   public Set doGetExpired(long timeLimit) {
      return Collections.emptySet();
   }

   @ManagedAttribute(
      value = "does this store serialize sessions",
      readonly = true
   )
   public boolean isPassivating() {
      return false;
   }

   public boolean doExists(String id) {
      return false;
   }

   public void doCleanOrphans(long timeLimit) {
   }
}
