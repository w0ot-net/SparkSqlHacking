package org.apache.hadoop.hive.metastore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ClassUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Private;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Evolving;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.util.ReflectionUtils;

@Private
@Evolving
public class RawStoreProxy implements InvocationHandler {
   private final RawStore base;
   private final MetaStoreInit.MetaStoreInitData metaStoreInitData = new MetaStoreInit.MetaStoreInitData();
   private final HiveConf hiveConf;
   private final Configuration conf;
   private final long socketTimeout;

   protected RawStoreProxy(HiveConf hiveConf, Configuration conf, Class rawStoreClass, int id) throws MetaException {
      this.conf = conf;
      this.hiveConf = hiveConf;
      this.socketTimeout = HiveConf.getTimeVar(hiveConf, ConfVars.METASTORE_CLIENT_SOCKET_TIMEOUT, TimeUnit.MILLISECONDS);
      this.init();
      this.base = (RawStore)ReflectionUtils.newInstance(rawStoreClass, conf);
   }

   public static RawStore getProxy(HiveConf hiveConf, Configuration conf, String rawStoreClassName, int id) throws MetaException {
      Class<? extends RawStore> baseClass = MetaStoreUtils.getClass(rawStoreClassName);
      RawStoreProxy handler = new RawStoreProxy(hiveConf, conf, baseClass, id);
      return (RawStore)Proxy.newProxyInstance(RawStoreProxy.class.getClassLoader(), getAllInterfaces(baseClass), handler);
   }

   private static Class[] getAllInterfaces(Class baseClass) {
      List interfaces = ClassUtils.getAllInterfaces(baseClass);
      Class<?>[] result = new Class[interfaces.size()];
      int i = 0;

      for(Object o : interfaces) {
         result[i++] = (Class)o;
      }

      return result;
   }

   private void init() throws MetaException {
      MetaStoreInit.updateConnectionURL(this.hiveConf, this.getConf(), (String)null, this.metaStoreInitData);
   }

   private void initMS() {
      this.base.setConf(this.getConf());
   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      try {
         Deadline.registerIfNot(this.socketTimeout);
         boolean isTimerStarted = Deadline.startTimer(method.getName());

         Object var5;
         try {
            var5 = method.invoke(this.base, args);
         } finally {
            if (isTimerStarted) {
               Deadline.stopTimer();
            }

         }

         return var5;
      } catch (UndeclaredThrowableException e) {
         throw e.getCause();
      } catch (InvocationTargetException e) {
         throw e.getCause();
      }
   }

   public Configuration getConf() {
      return this.conf;
   }
}
