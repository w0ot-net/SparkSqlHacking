package org.apache.spark.sql.hive.thriftserver;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.RejectedExecutionException;
import org.apache.hive.service.ServiceException;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationType;
import org.apache.spark.SparkThrowable;
import org.apache.spark.ErrorMessageFormat.;
import scala.Enumeration;

public final class HiveThriftServerErrors$ {
   public static final HiveThriftServerErrors$ MODULE$ = new HiveThriftServerErrors$();

   public Throwable taskExecutionRejectedError(final RejectedExecutionException rejected) {
      return new HiveSQLException("The background threadpool cannot accept new task for execution, please retry the operation", rejected);
   }

   public Throwable runningQueryError(final Throwable e, final Enumeration.Value format) {
      label25: {
         if (e instanceof SparkThrowable) {
            Enumeration.Value var6 = .MODULE$.PRETTY();
            if (format == null) {
               if (var6 == null) {
                  break label25;
               }
            } else if (format.equals(var6)) {
               break label25;
            }
         }

         if (e instanceof SparkThrowable) {
            return new HiveSQLException(org.apache.spark.SparkThrowableHelper..MODULE$.getMessage(e, format), ((SparkThrowable)e).getSqlState(), e);
         }

         return new HiveSQLException("Error running query: " + e.toString(), e);
      }

      String errorClassPrefix = (String)scala.Option..MODULE$.apply(((SparkThrowable)e).getCondition()).map((ex) -> "[" + ex + "] ").getOrElse(() -> "");
      return new HiveSQLException("Error running query: " + errorClassPrefix + e.toString(), ((SparkThrowable)e).getSqlState(), e);
   }

   public Throwable hiveOperatingError(final OperationType operationType, final Throwable e) {
      return new HiveSQLException("Error operating " + operationType + " " + e.getMessage(), e);
   }

   public Throwable failedToOpenNewSessionError(final Throwable e) {
      return new HiveSQLException("Failed to open new session: " + e, e);
   }

   public Throwable cannotLoginToKerberosError(final Throwable e) {
      return new ServiceException("Unable to login to kerberos with given principal/keytab", e);
   }

   public Throwable cannotLoginToSpnegoError(final String principal, final String keyTabFile, final IOException e) {
      return new ServiceException("Unable to login to spnego with given principal " + principal + " and keytab " + keyTabFile + ": " + e, e);
   }

   public Throwable failedToStartServiceError(final String serviceName, final Throwable e) {
      return new ServiceException("Failed to Start " + serviceName, e);
   }

   private HiveThriftServerErrors$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
