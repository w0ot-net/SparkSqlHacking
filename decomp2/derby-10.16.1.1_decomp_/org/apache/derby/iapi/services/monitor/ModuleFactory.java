package org.apache.derby.iapi.services.monitor;

import java.util.Locale;
import java.util.Properties;
import org.apache.derby.iapi.services.loader.InstanceGetter;
import org.apache.derby.iapi.services.timer.TimerFactory;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.info.ProductVersionHolder;
import org.apache.derby.shared.common.stream.InfoStreams;

public interface ModuleFactory {
   Object findModule(Object var1, String var2, String var3);

   String getServiceName(Object var1);

   Locale getLocale(Object var1);

   Locale getLocaleFromString(String var1) throws StandardException;

   Locale setLocale(Object var1, String var2) throws StandardException;

   Locale setLocale(Properties var1, String var2) throws StandardException;

   PersistentService getServiceType(Object var1);

   PersistentService getServiceProvider(String var1) throws StandardException;

   Properties getApplicationProperties();

   void shutdown();

   void shutdown(Object var1);

   InstanceGetter classFromIdentifier(int var1) throws StandardException;

   Object newInstanceFromIdentifier(int var1) throws StandardException;

   Object getEnvironment();

   String[] getServiceList(String var1);

   boolean startPersistentService(String var1, Properties var2) throws StandardException;

   Object createPersistentService(String var1, String var2, Properties var3) throws StandardException;

   void removePersistentService(String var1) throws StandardException;

   Object startNonPersistentService(String var1, String var2, Properties var3) throws StandardException;

   String getCanonicalServiceName(String var1) throws StandardException;

   Object findService(String var1, String var2);

   Object startModule(boolean var1, Object var2, String var3, String var4, Properties var5) throws StandardException;

   InfoStreams getSystemStreams();

   void startServices(Properties var1, boolean var2);

   String getJVMProperty(String var1);

   Thread getDaemonThread(Runnable var1, String var2, boolean var3);

   boolean isDaemonThread(Thread var1);

   ProductVersionHolder getEngineVersion();

   UUIDFactory getUUIDFactory();

   TimerFactory getTimerFactory();
}
