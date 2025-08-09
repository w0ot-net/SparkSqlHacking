package org.apache.hadoop.hive.metastore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.TimeUnit;
import javax.jdo.JDOException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Private;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.ql.log.PerfLogger;
import org.datanucleus.exceptions.NucleusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Private
@Evolving
public class RetryingHMSHandler implements InvocationHandler {
   private static final Logger LOG = LoggerFactory.getLogger(RetryingHMSHandler.class);
   private static final String CLASS_NAME = RetryingHMSHandler.class.getName();
   private final IHMSHandler baseHandler;
   private final MetaStoreInit.MetaStoreInitData metaStoreInitData = new MetaStoreInit.MetaStoreInitData();
   private final HiveConf origConf;
   private final Configuration activeConf;

   private RetryingHMSHandler(HiveConf hiveConf, IHMSHandler baseHandler, boolean local) throws MetaException {
      this.origConf = hiveConf;
      this.baseHandler = baseHandler;
      if (local) {
         baseHandler.setConf(hiveConf);
      }

      this.activeConf = baseHandler.getConf();
      MetaStoreInit.updateConnectionURL(hiveConf, this.getActiveConf(), (String)null, this.metaStoreInitData);

      try {
         this.invoke(baseHandler, baseHandler.getClass().getDeclaredMethod("init", (Class[])null), (Object[])null);
      } catch (Throwable e) {
         LOG.error("HMSHandler Fatal error: " + ExceptionUtils.getStackTrace(e));
         MetaException me = new MetaException(e.getMessage());
         me.initCause(e);
         throw me;
      }
   }

   public static IHMSHandler getProxy(HiveConf hiveConf, IHMSHandler baseHandler, boolean local) throws MetaException {
      RetryingHMSHandler handler = new RetryingHMSHandler(hiveConf, baseHandler, local);
      return (IHMSHandler)Proxy.newProxyInstance(RetryingHMSHandler.class.getClassLoader(), new Class[]{IHMSHandler.class}, handler);
   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      int retryCount = -1;
      int threadId = HiveMetaStore.HMSHandler.get();
      boolean error = true;
      PerfLogger perfLogger = PerfLogger.getPerfLogger(this.origConf, false);
      perfLogger.PerfLogBegin(CLASS_NAME, method.getName());

      Object var9;
      try {
         Result result = this.invokeInternal(proxy, method, args);
         retryCount = result.numRetries;
         error = false;
         var9 = result.result;
      } finally {
         StringBuffer additionalInfo = new StringBuffer();
         additionalInfo.append("threadId=").append(threadId).append(" retryCount=").append(retryCount).append(" error=").append(error);
         perfLogger.PerfLogEnd(CLASS_NAME, method.getName(), additionalInfo.toString());
      }

      return var9;
   }

   public Result invokeInternal(Object proxy, Method method, Object[] args) throws Throwable {
      boolean gotNewConnectUrl = false;
      boolean reloadConf = HiveConf.getBoolVar(this.origConf, ConfVars.HMSHANDLERFORCERELOADCONF);
      long retryInterval = HiveConf.getTimeVar(this.origConf, ConfVars.HMSHANDLERINTERVAL, TimeUnit.MILLISECONDS);
      int retryLimit = HiveConf.getIntVar(this.origConf, ConfVars.HMSHANDLERATTEMPTS);
      long timeout = HiveConf.getTimeVar(this.origConf, ConfVars.METASTORE_CLIENT_SOCKET_TIMEOUT, TimeUnit.MILLISECONDS);
      Deadline.registerIfNot(timeout);
      if (reloadConf) {
         MetaStoreInit.updateConnectionURL(this.origConf, this.getActiveConf(), (String)null, this.metaStoreInitData);
      }

      int retryCount = 0;
      Throwable caughtException = null;

      while(true) {
         try {
            if (reloadConf || gotNewConnectUrl) {
               this.baseHandler.setConf(this.getActiveConf());
            }

            Object object = null;
            boolean isStarted = Deadline.startTimer(method.getName());

            try {
               object = method.invoke(this.baseHandler, args);
            } finally {
               if (isStarted) {
                  Deadline.stopTimer();
               }

            }

            return new Result(object, retryCount);
         } catch (JDOException e) {
            caughtException = e;
         } catch (UndeclaredThrowableException var22) {
            if (var22.getCause() == null) {
               LOG.error(ExceptionUtils.getStackTrace(var22));
               throw var22;
            }

            if (var22.getCause() instanceof JDOException) {
               caughtException = var22.getCause();
            } else {
               if (!(var22.getCause() instanceof MetaException) || var22.getCause().getCause() == null || !(var22.getCause().getCause() instanceof JDOException)) {
                  LOG.error(ExceptionUtils.getStackTrace(var22.getCause()));
                  throw var22.getCause();
               }

               caughtException = var22.getCause().getCause();
            }
         } catch (InvocationTargetException var23) {
            if (var23.getCause() instanceof JDOException) {
               caughtException = var23.getCause();
            } else {
               if (var23.getCause() instanceof NoSuchObjectException || var23.getTargetException().getCause() instanceof NoSuchObjectException) {
                  String methodName = method.getName();
                  if (!methodName.startsWith("get_database") && !methodName.startsWith("get_table") && !methodName.startsWith("get_partition") && !methodName.startsWith("get_function")) {
                     LOG.error(ExceptionUtils.getStackTrace(var23.getCause()));
                  }

                  throw var23.getCause();
               }

               if (!(var23.getCause() instanceof MetaException) || var23.getCause().getCause() == null) {
                  LOG.error(ExceptionUtils.getStackTrace(var23.getCause()));
                  throw var23.getCause();
               }

               if (!(var23.getCause().getCause() instanceof JDOException) && !(var23.getCause().getCause() instanceof NucleusException)) {
                  if (var23.getCause().getCause() instanceof DeadlineException) {
                     Deadline.clear();
                     LOG.error("Error happens in method " + method.getName() + ": " + ExceptionUtils.getStackTrace(var23.getCause()));
                     throw var23.getCause();
                  }

                  LOG.error(ExceptionUtils.getStackTrace(var23.getCause()));
                  throw var23.getCause();
               }

               caughtException = var23.getCause().getCause();
            }
         }

         if (retryCount >= retryLimit) {
            LOG.error("HMSHandler Fatal error: " + ExceptionUtils.getStackTrace(caughtException));
            MetaException me = new MetaException(caughtException.getMessage());
            me.initCause(caughtException);
            throw me;
         }

         assert retryInterval >= 0L;

         ++retryCount;
         LOG.error(String.format("Retrying HMSHandler after %d ms (attempt %d of %d)", retryInterval, retryCount, retryLimit) + " with error: " + ExceptionUtils.getStackTrace(caughtException));
         Thread.sleep(retryInterval);
         String lastUrl = MetaStoreInit.getConnectionURL(this.getActiveConf());
         gotNewConnectUrl = MetaStoreInit.updateConnectionURL(this.origConf, this.getActiveConf(), lastUrl, this.metaStoreInitData);
      }
   }

   public Configuration getActiveConf() {
      return this.activeConf;
   }

   private static class Result {
      private final Object result;
      private final int numRetries;

      public Result(Object result, int numRetries) {
         this.result = result;
         this.numRetries = numRetries;
      }
   }
}
