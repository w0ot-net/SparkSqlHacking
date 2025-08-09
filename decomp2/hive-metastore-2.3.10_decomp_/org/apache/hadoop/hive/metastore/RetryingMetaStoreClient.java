package org.apache.hadoop.hive.metastore;

import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.hive.common.classification.RetrySemantics;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.annotation.NoReconnect;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Public
public class RetryingMetaStoreClient implements InvocationHandler {
   private static final Logger LOG = LoggerFactory.getLogger(RetryingMetaStoreClient.class.getName());
   private final IMetaStoreClient base;
   private final int retryLimit;
   private final long retryDelaySeconds;
   private final ConcurrentHashMap metaCallTimeMap;
   private final long connectionLifeTimeInMillis;
   private long lastConnectionTime;
   private boolean localMetaStore;

   protected RetryingMetaStoreClient(HiveConf hiveConf, Class[] constructorArgTypes, Object[] constructorArgs, ConcurrentHashMap metaCallTimeMap, Class msClientClass) throws MetaException {
      this.retryLimit = hiveConf.getIntVar(ConfVars.METASTORETHRIFTFAILURERETRIES);
      this.retryDelaySeconds = hiveConf.getTimeVar(ConfVars.METASTORE_CLIENT_CONNECT_RETRY_DELAY, TimeUnit.SECONDS);
      this.metaCallTimeMap = metaCallTimeMap;
      this.connectionLifeTimeInMillis = hiveConf.getTimeVar(ConfVars.METASTORE_CLIENT_SOCKET_LIFETIME, TimeUnit.MILLISECONDS);
      this.lastConnectionTime = System.currentTimeMillis();
      String msUri = hiveConf.getVar(ConfVars.METASTOREURIS);
      this.localMetaStore = msUri == null || msUri.trim().isEmpty();
      this.reloginExpiringKeytabUser();
      this.base = (IMetaStoreClient)MetaStoreUtils.newInstance(msClientClass, constructorArgTypes, constructorArgs);
   }

   public static IMetaStoreClient getProxy(HiveConf hiveConf, boolean allowEmbedded) throws MetaException {
      return getProxy(hiveConf, new Class[]{HiveConf.class, HiveMetaHookLoader.class, Boolean.class}, new Object[]{hiveConf, null, allowEmbedded}, (ConcurrentHashMap)null, HiveMetaStoreClient.class.getName());
   }

   @VisibleForTesting
   public static IMetaStoreClient getProxy(HiveConf hiveConf, HiveMetaHookLoader hookLoader, String mscClassName) throws MetaException {
      return getProxy(hiveConf, hookLoader, (ConcurrentHashMap)null, mscClassName, true);
   }

   public static IMetaStoreClient getProxy(HiveConf hiveConf, HiveMetaHookLoader hookLoader, ConcurrentHashMap metaCallTimeMap, String mscClassName, boolean allowEmbedded) throws MetaException {
      return getProxy(hiveConf, new Class[]{HiveConf.class, HiveMetaHookLoader.class, Boolean.class}, new Object[]{hiveConf, hookLoader, allowEmbedded}, metaCallTimeMap, mscClassName);
   }

   public static IMetaStoreClient getProxy(HiveConf hiveConf, Class[] constructorArgTypes, Object[] constructorArgs, String mscClassName) throws MetaException {
      return getProxy(hiveConf, constructorArgTypes, constructorArgs, (ConcurrentHashMap)null, mscClassName);
   }

   public static IMetaStoreClient getProxy(HiveConf hiveConf, Class[] constructorArgTypes, Object[] constructorArgs, ConcurrentHashMap metaCallTimeMap, String mscClassName) throws MetaException {
      Class<? extends IMetaStoreClient> baseClass = MetaStoreUtils.getClass(mscClassName);
      RetryingMetaStoreClient handler = new RetryingMetaStoreClient(hiveConf, constructorArgTypes, constructorArgs, metaCallTimeMap, baseClass);
      return (IMetaStoreClient)Proxy.newProxyInstance(RetryingMetaStoreClient.class.getClassLoader(), baseClass.getInterfaces(), handler);
   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Object ret = null;
      int retriesMade = 0;
      TException caughtException = null;
      boolean allowReconnect = !method.isAnnotationPresent(NoReconnect.class);
      boolean allowRetry = true;
      Annotation[] directives = method.getDeclaredAnnotations();
      if (directives != null) {
         for(Annotation a : directives) {
            if (a instanceof RetrySemantics.CannotRetry) {
               allowRetry = false;
            }
         }
      }

      while(true) {
         try {
            this.reloginExpiringKeytabUser();
            if (allowReconnect && (retriesMade > 0 || this.hasConnectionLifeTimeReached(method))) {
               this.base.reconnect();
               this.lastConnectionTime = System.currentTimeMillis();
            }

            if (this.metaCallTimeMap == null) {
               ret = method.invoke(this.base, args);
            } else {
               long startTime = System.currentTimeMillis();
               ret = method.invoke(this.base, args);
               long timeTaken = System.currentTimeMillis() - startTime;
               this.addMethodTime(method, timeTaken);
            }

            return ret;
         } catch (UndeclaredThrowableException e) {
            throw e.getCause();
         } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof TApplicationException) {
               TApplicationException tae = (TApplicationException)t;
               switch (tae.getType()) {
                  case 1:
                  case 3:
                  case 9:
                  case 10:
                     throw t;
                  case 2:
                  case 4:
                  case 5:
                  case 6:
                  case 7:
                  case 8:
                  default:
                     var18 = tae;
               }
            } else if (!(t instanceof TProtocolException) && !(t instanceof TTransportException)) {
               if (!(t instanceof MetaException) || !t.getMessage().matches("(?s).*(JDO[a-zA-Z]*|TProtocol|TTransport)Exception.*") || t.getMessage().contains("java.sql.SQLIntegrityConstraintViolationException")) {
                  throw t;
               }

               var18 = (MetaException)t;
            } else {
               var18 = (TException)t;
            }
         } catch (MetaException var16) {
            if (!var16.getMessage().matches("(?s).*(IO|TTransport)Exception.*") || var16.getMessage().contains("java.sql.SQLIntegrityConstraintViolationException")) {
               throw var16;
            }

            var18 = var16;
         }

         if (retriesMade >= this.retryLimit || this.base.isLocalMetaStore() || !allowRetry) {
            throw (Throwable)var18;
         }

         ++retriesMade;
         LOG.warn("MetaStoreClient lost connection. Attempting to reconnect (" + retriesMade + " of " + this.retryLimit + ") after " + this.retryDelaySeconds + "s. " + method.getName(), (Throwable)var18);
         Thread.sleep(this.retryDelaySeconds * 1000L);
      }
   }

   private void addMethodTime(Method method, long timeTaken) {
      String methodStr = this.getMethodString(method);

      Long curTime;
      Long newTime;
      do {
         curTime = (Long)this.metaCallTimeMap.get(methodStr);
         newTime = timeTaken;
      } while((curTime == null || !this.metaCallTimeMap.replace(methodStr, curTime, newTime + curTime)) && (curTime != null || null != this.metaCallTimeMap.putIfAbsent(methodStr, newTime)));

   }

   private String getMethodString(Method method) {
      StringBuilder methodSb = new StringBuilder(method.getName());
      methodSb.append("_(");

      for(Class paramClass : method.getParameterTypes()) {
         methodSb.append(paramClass.getSimpleName());
         methodSb.append(", ");
      }

      methodSb.append(")");
      return methodSb.toString();
   }

   private boolean hasConnectionLifeTimeReached(Method method) {
      if (this.connectionLifeTimeInMillis > 0L && !this.localMetaStore) {
         boolean shouldReconnect = System.currentTimeMillis() - this.lastConnectionTime >= this.connectionLifeTimeInMillis;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Reconnection status for Method: " + method.getName() + " is " + shouldReconnect);
         }

         return shouldReconnect;
      } else {
         return false;
      }
   }

   private void reloginExpiringKeytabUser() throws MetaException {
      if (UserGroupInformation.isSecurityEnabled()) {
         try {
            UserGroupInformation ugi = UserGroupInformation.getLoginUser();
            if (ugi.isFromKeytab()) {
               ugi.checkTGTAndReloginFromKeytab();
            }

         } catch (IOException e) {
            String msg = "Error doing relogin using keytab " + e.getMessage();
            LOG.error(msg, e);
            throw new MetaException(msg);
         }
      }
   }
}
