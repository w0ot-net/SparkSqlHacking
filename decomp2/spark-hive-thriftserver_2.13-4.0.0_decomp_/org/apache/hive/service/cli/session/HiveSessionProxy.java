package org.apache.hive.service.cli.session;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.PrivilegedActionException;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hive.service.cli.HiveSQLException;

public class HiveSessionProxy implements InvocationHandler {
   private final HiveSession base;
   private final UserGroupInformation ugi;

   public HiveSessionProxy(HiveSession hiveSession, UserGroupInformation ugi) {
      this.base = hiveSession;
      this.ugi = ugi;
   }

   public static HiveSession getProxy(HiveSession hiveSession, UserGroupInformation ugi) throws IllegalArgumentException, HiveSQLException {
      return (HiveSession)Proxy.newProxyInstance(HiveSession.class.getClassLoader(), new Class[]{HiveSession.class}, new HiveSessionProxy(hiveSession, ugi));
   }

   public Object invoke(Object arg0, Method method, Object[] args) throws Throwable {
      try {
         return method.getDeclaringClass() == HiveSessionBase.class ? this.invoke(method, args) : this.ugi.doAs(() -> this.invoke(method, args));
      } catch (UndeclaredThrowableException e) {
         Throwable innerException = e.getCause();
         if (innerException instanceof PrivilegedActionException) {
            throw innerException.getCause();
         } else {
            throw e.getCause();
         }
      }
   }

   private Object invoke(Method method, Object[] args) throws HiveSQLException {
      try {
         return method.invoke(this.base, args);
      } catch (InvocationTargetException e) {
         if (e.getCause() instanceof HiveSQLException) {
            throw (HiveSQLException)e.getCause();
         } else {
            throw new RuntimeException(e.getCause());
         }
      } catch (IllegalArgumentException e) {
         throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      }
   }
}
