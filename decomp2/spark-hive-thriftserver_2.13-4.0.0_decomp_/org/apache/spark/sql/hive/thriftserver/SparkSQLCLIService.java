package org.apache.spark.sql.hive.thriftserver;

import java.io.IOException;
import javax.security.auth.login.LoginException;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.security.SecurityUtil;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.cli.CLIService;
import org.apache.hive.service.cli.GetInfoType;
import org.apache.hive.service.cli.GetInfoValue;
import org.apache.hive.service.cli.SessionHandle;
import org.apache.hive.service.server.HiveServer2;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.errors.QueryExecutionErrors.;
import scala.Function1;
import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005a3Qa\u0002\u0005\u0001\u0015QA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tU\u0001\u0011\t\u0011)A\u0005W!)q\u0006\u0001C\u0001a!)A\u0007\u0001C!k!)q\t\u0001C!\u0011\")\u0011\n\u0001C!\u0015\n\u00112\u000b]1sWN\u000bFj\u0011'J'\u0016\u0014h/[2f\u0015\tI!\"\u0001\u0007uQJLg\r^:feZ,'O\u0003\u0002\f\u0019\u0005!\u0001.\u001b<f\u0015\tia\"A\u0002tc2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\n\u0004\u0001Uq\u0002C\u0001\f\u001d\u001b\u00059\"B\u0001\r\u001a\u0003\r\u0019G.\u001b\u0006\u00035m\tqa]3sm&\u001cWM\u0003\u0002\f!%\u0011Qd\u0006\u0002\u000b\u00072K5+\u001a:wS\u000e,\u0007CA\u0010!\u001b\u0005A\u0011BA\u0011\t\u0005e\u0011VM\u001a7fGR,GmQ8na>\u001c\u0018\u000e^3TKJ4\u0018nY3\u0002\u0015!Lg/Z*feZ,'o\u0001\u0001\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0005\u001dJ\u0012AB:feZ,'/\u0003\u0002*M\tY\u0001*\u001b<f'\u0016\u0014h/\u001a:3\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o!\taS&D\u0001\r\u0013\tqCB\u0001\u0007Ta\u0006\u00148nU3tg&|g.\u0001\u0004=S:LGO\u0010\u000b\u0004cI\u001a\u0004CA\u0010\u0001\u0011\u0015\u00113\u00011\u0001%\u0011\u0015Q3\u00011\u0001,\u0003\u0011Ig.\u001b;\u0015\u0005Yb\u0004CA\u001c;\u001b\u0005A$\"A\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005mB$\u0001B+oSRDQ!\u0010\u0003A\u0002y\n\u0001\u0002[5wK\u000e{gN\u001a\t\u0003\u007f\u0015k\u0011\u0001\u0011\u0006\u0003\u0003\n\u000bAaY8oM*\u00111b\u0011\u0006\u0003\tB\ta\u0001[1e_>\u0004\u0018B\u0001$A\u0005!A\u0015N^3D_:4\u0017!B:uCJ$H#\u0001\u001c\u0002\u000f\u001d,G/\u00138g_R\u00191JT*\u0011\u0005Ya\u0015BA'\u0018\u000519U\r^%oM>4\u0016\r\\;f\u0011\u0015ye\u00011\u0001Q\u00035\u0019Xm]:j_:D\u0015M\u001c3mKB\u0011a#U\u0005\u0003%^\u0011QbU3tg&|g\u000eS1oI2,\u0007\"\u0002+\u0007\u0001\u0004)\u0016aC4fi&sgm\u001c+za\u0016\u0004\"A\u0006,\n\u0005];\"aC$fi&sgm\u001c+za\u0016\u0004"
)
public class SparkSQLCLIService extends CLIService implements ReflectedCompositeService {
   private final HiveServer2 hiveServer;
   private final SparkSession sparkSession;
   private Function1 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo;
   private Function2 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError;

   public void initCompositeService(final HiveConf hiveConf) {
      ReflectedCompositeService.initCompositeService$(this, hiveConf);
   }

   public void startCompositeService() {
      ReflectedCompositeService.startCompositeService$(this);
   }

   public Function1 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo() {
      return this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo;
   }

   public Function2 org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError() {
      return this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError;
   }

   public final void org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo_$eq(final Function1 x$1) {
      this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logInfo = x$1;
   }

   public final void org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$_setter_$org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError_$eq(final Function2 x$1) {
      this.org$apache$spark$sql$hive$thriftserver$ReflectedCompositeService$$logError = x$1;
   }

   public void init(final HiveConf hiveConf) {
      ReflectionUtils$.MODULE$.setSuperField(this, "hiveConf", hiveConf);
      SparkSQLSessionManager sparkSqlSessionManager = new SparkSQLSessionManager(this.hiveServer, this.sparkSession);
      ReflectionUtils$.MODULE$.setSuperField(this, "sessionManager", sparkSqlSessionManager);
      this.addService(sparkSqlSessionManager);
      UserGroupInformation sparkServiceUGI = null;
      UserGroupInformation httpUGI = null;
      if (UserGroupInformation.isSecurityEnabled()) {
         try {
            String principal = hiveConf.getVar(ConfVars.HIVE_SERVER2_KERBEROS_PRINCIPAL);
            String keyTabFile = hiveConf.getVar(ConfVars.HIVE_SERVER2_KERBEROS_KEYTAB);
            if (principal.isEmpty() || keyTabFile.isEmpty()) {
               throw .MODULE$.invalidKerberosConfigForHiveServer2Error();
            }

            UserGroupInformation originalUgi = UserGroupInformation.getCurrentUser();
            UserGroupInformation var10000;
            if (HiveAuthFactory.needUgiLogin(originalUgi, SecurityUtil.getServerPrincipal(principal, "0.0.0.0"), keyTabFile)) {
               HiveAuthFactory.loginFromKeytab(hiveConf);
               var10000 = Utils.getUGI();
            } else {
               var10000 = originalUgi;
            }

            sparkServiceUGI = var10000;
            ReflectionUtils$.MODULE$.setSuperField(this, "serviceUGI", sparkServiceUGI);
         } catch (Throwable var16) {
            if (var16 instanceof IOException ? true : var16 instanceof LoginException) {
               throw HiveThriftServerErrors$.MODULE$.cannotLoginToKerberosError(var16);
            }

            throw var16;
         }

         String principal = hiveConf.getVar(ConfVars.HIVE_SERVER2_SPNEGO_PRINCIPAL).trim();
         String keyTabFile = hiveConf.getVar(ConfVars.HIVE_SERVER2_SPNEGO_KEYTAB).trim();
         if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(principal)) && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(keyTabFile))) {
            try {
               httpUGI = HiveAuthFactory.loginFromSpnegoKeytabAndReturnUGI(hiveConf);
               ReflectionUtils$.MODULE$.setSuperField(this, "httpUGI", httpUGI);
            } catch (IOException var15) {
               throw HiveThriftServerErrors$.MODULE$.cannotLoginToSpnegoError(principal, keyTabFile, var15);
            }
         }
      }

      this.initCompositeService(hiveConf);
   }

   public void start() {
      this.startCompositeService();
   }

   public GetInfoValue getInfo(final SessionHandle sessionHandle, final GetInfoType getInfoType) {
      if (GetInfoType.CLI_SERVER_NAME.equals(getInfoType)) {
         return new GetInfoValue("Spark SQL");
      } else if (GetInfoType.CLI_DBMS_NAME.equals(getInfoType)) {
         return new GetInfoValue("Spark SQL");
      } else if (GetInfoType.CLI_DBMS_VER.equals(getInfoType)) {
         return new GetInfoValue(this.sparkSession.version());
      } else {
         return GetInfoType.CLI_ODBC_KEYWORDS.equals(getInfoType) ? new GetInfoValue(org.apache.spark.sql.catalyst.util.SQLKeywordUtils..MODULE$.keywords().mkString(",")) : super.getInfo(sessionHandle, getInfoType);
      }
   }

   public SparkSQLCLIService(final HiveServer2 hiveServer, final SparkSession sparkSession) {
      super(hiveServer);
      this.hiveServer = hiveServer;
      this.sparkSession = sparkSession;
      ReflectedCompositeService.$init$(this);
      Statics.releaseFence();
   }
}
