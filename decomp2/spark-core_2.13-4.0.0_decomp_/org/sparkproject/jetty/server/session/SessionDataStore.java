package org.sparkproject.jetty.server.session;

import java.util.Set;

public interface SessionDataStore extends SessionDataMap {
   SessionData newSessionData(String var1, long var2, long var4, long var6, long var8);

   Set getExpired(Set var1);

   boolean isPassivating();

   boolean exists(String var1) throws Exception;
}
