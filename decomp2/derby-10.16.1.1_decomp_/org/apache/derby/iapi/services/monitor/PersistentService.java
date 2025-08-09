package org.apache.derby.iapi.services.monitor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.derby.io.StorageFactory;
import org.apache.derby.shared.common.error.StandardException;

public interface PersistentService {
   String DIRECTORY = "directory";
   String CLASSPATH = "classpath";
   String JAR = "jar";
   String HTTP = "http";
   String HTTPS = "https";
   String INMEMORY = "memory";
   String DB_README_FILE_NAME = "README_DO_NOT_TOUCH_FILES.txt";
   String PROPERTIES_NAME = "service.properties";
   String ROOT = "derby.__rt.serviceDirectory";
   String TYPE = "derby.__rt.serviceType";

   String getType();

   Enumeration getBootTimeServices();

   void createDataWarningFile(StorageFactory var1) throws StandardException;

   Properties getServiceProperties(String var1, Properties var2) throws StandardException;

   void saveServiceProperties(String var1, StorageFactory var2, Properties var3, boolean var4) throws StandardException;

   void saveServiceProperties(String var1, Properties var2) throws StandardException;

   String createServiceRoot(String var1, boolean var2) throws StandardException;

   boolean removeServiceRoot(String var1);

   String getCanonicalServiceName(String var1) throws StandardException;

   String getUserServiceName(String var1);

   boolean isSameService(String var1, String var2);

   boolean hasStorageFactory();

   StorageFactory getStorageFactoryInstance(boolean var1, String var2, String var3, String var4) throws StandardException, IOException;
}
