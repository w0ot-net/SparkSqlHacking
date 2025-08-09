package org.sparkproject.jetty.server;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import org.sparkproject.jetty.server.session.HouseKeeper;
import org.sparkproject.jetty.util.component.LifeCycle;

public interface SessionIdManager extends LifeCycle {
   boolean isIdInUse(String var1);

   void expireAll(String var1);

   void invalidateAll(String var1);

   String newSessionId(HttpServletRequest var1, long var2);

   String getWorkerName();

   String getId(String var1);

   String getExtendedId(String var1, HttpServletRequest var2);

   String renewSessionId(String var1, String var2, HttpServletRequest var3);

   Set getSessionHandlers();

   void setSessionHouseKeeper(HouseKeeper var1);

   HouseKeeper getSessionHouseKeeper();
}
