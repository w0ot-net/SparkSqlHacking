package org.apache.spark.sql.hive.thriftserver;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Properties;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.operation.Operation;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b\u0001\u0003\b\u0010!\u0003\r\t!E\u000e\t\u000b5\u0002A\u0011A\u0018\t\u000bY\u0002a\u0011C\u001c\t\u000fq\u0002\u0001\u0019!C\t{!9a\t\u0001a\u0001\n#9\u0005\"\u0002&\u0001\t#y\u0003BB&\u0001!\u0013\u0005q\u0006\u0003\u0004M\u0001A%\ta\f\u0005\u0006\u001b\u0002!\tA\u0014\u0005\u0006A\u0002!\t!\u0019\u0005\u0006m\u0002!\tb\u001e\u0005\u000e\u0003\u0013\u0001\u0001\u0013aA\u0001\u0002\u0013%q&a\u0003\t\u001b\u00055\u0001\u0001%A\u0002\u0002\u0003%IaLA\b\u00119\t\t\u0002\u0001I\u0001\u0004\u0003\u0005I\u0011BA\n\u0003C\u0011ab\u00159be.|\u0005/\u001a:bi&|gN\u0003\u0002\u0011#\u0005aA\u000f\u001b:jMR\u001cXM\u001d<fe*\u0011!cE\u0001\u0005Q&4XM\u0003\u0002\u0015+\u0005\u00191/\u001d7\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e\u001c2\u0001\u0001\u000f(!\tiR%D\u0001\u001f\u0015\ty\u0002%A\u0005pa\u0016\u0014\u0018\r^5p]*\u0011\u0011EI\u0001\u0004G2L'BA\u0012%\u0003\u001d\u0019XM\u001d<jG\u0016T!AE\f\n\u0005\u0019r\"!C(qKJ\fG/[8o!\tA3&D\u0001*\u0015\tQS#\u0001\u0005j]R,'O\\1m\u0013\ta\u0013FA\u0004M_\u001e<\u0017N\\4\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012\u0001\r\t\u0003cQj\u0011A\r\u0006\u0002g\u0005)1oY1mC&\u0011QG\r\u0002\u0005+:LG/A\u0004tKN\u001c\u0018n\u001c8\u0016\u0003a\u0002\"!\u000f\u001e\u000e\u0003MI!aO\n\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\u0002\u0017M$\u0018\r^3nK:$\u0018\nZ\u000b\u0002}A\u0011q\bR\u0007\u0002\u0001*\u0011\u0011IQ\u0001\u0005Y\u0006twMC\u0001D\u0003\u0011Q\u0017M^1\n\u0005\u0015\u0003%AB*ue&tw-A\bti\u0006$X-\\3oi&#w\fJ3r)\t\u0001\u0004\nC\u0004J\t\u0005\u0005\t\u0019\u0001 \u0002\u0007a$\u0013'A\u0004dY\u0016\fg.\u001e9\u0002\u0007I,h.A\u0003dY>\u001cX-A\nxSRDGj\\2bYB\u0013x\u000e]3si&,7/\u0006\u0002P%R\u0011\u0001k\u0017\t\u0003#Jc\u0001\u0001B\u0003T\u0011\t\u0007AKA\u0001U#\t)\u0006\f\u0005\u00022-&\u0011qK\r\u0002\b\u001d>$\b.\u001b8h!\t\t\u0014,\u0003\u0002[e\t\u0019\u0011I\\=\t\rqCA\u00111\u0001^\u0003\u00051\u0007cA\u0019_!&\u0011qL\r\u0002\ty\tLh.Y7f}\u0005yA/\u00192mKRK\b/Z*ue&tw\r\u0006\u0002cYB\u00111M\u001b\b\u0003I\"\u0004\"!\u001a\u001a\u000e\u0003\u0019T!a\u001a\u0018\u0002\rq\u0012xn\u001c;?\u0013\tI''\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000b.T!!\u001b\u001a\t\u000b5L\u0001\u0019\u00018\u0002\u0013Q\f'\r\\3UsB,\u0007CA8u\u001b\u0005\u0001(BA9s\u0003\u001d\u0019\u0017\r^1m_\u001eT!a]\n\u0002\u0011\r\fG/\u00197zgRL!!\u001e9\u0003!\r\u000bG/\u00197pOR\u000b'\r\\3UsB,\u0017aB8o\u000bJ\u0014xN\u001d\u000b\u0002qB!\u0011'_>1\u0013\tQ(GA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o!\ra\u00181\u0001\b\u0003{~t!!\u001a@\n\u0003MJ1!!\u00013\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0002\u0002\b\tIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u0004\u0003\u0003\u0011\u0014!C:va\u0016\u0014HE];o\u0013\tYU%A\u0006tkB,'\u000fJ2m_N,\u0017B\u0001'&\u00039\u0019X\u000f]3sIM,Go\u0015;bi\u0016$B!!\u0006\u0002\u001eA!\u0011qCA\r\u001b\u0005\u0001\u0013bAA\u000eA\tqq\n]3sCRLwN\\*uCR,\u0007bBA\u0010\u001b\u0001\u0007\u0011QC\u0001\t]\u0016<8\u000b^1uK&\u0019\u00111E\u0013\u0002\u0011M,Go\u0015;bi\u0016\u0004"
)
public interface SparkOperation extends Logging {
   // $FF: synthetic method
   void org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$run();

   // $FF: synthetic method
   void org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$close();

   // $FF: synthetic method
   OperationState org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$setState(final OperationState newState);

   SparkSession session();

   String statementId();

   void statementId_$eq(final String x$1);

   // $FF: synthetic method
   static void cleanup$(final SparkOperation $this) {
      $this.cleanup();
   }

   default void cleanup() {
   }

   // $FF: synthetic method
   static void run$(final SparkOperation $this) {
      $this.run();
   }

   default void run() {
      this.withLocalProperties((JFunction0.mcV.sp)() -> this.org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$run());
   }

   // $FF: synthetic method
   static void close$(final SparkOperation $this) {
      $this.close();
   }

   default void close() {
      this.org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$close();
      this.cleanup();
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Close statement with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())})))));
      HiveThriftServer2$.MODULE$.eventManager().onOperationClosed(this.statementId());
   }

   // $FF: synthetic method
   static Object withLocalProperties$(final SparkOperation $this, final Function0 f) {
      return $this.withLocalProperties(f);
   }

   default Object withLocalProperties(final Function0 f) {
      Properties originalProps = org.apache.spark.util.Utils..MODULE$.cloneProperties(this.session().sparkContext().getLocalProperties());
      Option originalSession = org.apache.spark.sql.SparkSession..MODULE$.getActiveSession();

      Object var16;
      try {
         org.apache.spark.sql.SparkSession..MODULE$.setActiveSession(this.session());
         Option var6 = this.session().conf().getOption(org.apache.spark.sql.internal.SQLConf..MODULE$.THRIFTSERVER_POOL().key());
         if (var6 instanceof Some var7) {
            String pool = (String)var7.value();
            this.session().sparkContext().setLocalProperty(org.apache.spark.SparkContext..MODULE$.SPARK_SCHEDULER_POOL(), pool);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!scala.None..MODULE$.equals(var6)) {
               throw new MatchError(var6);
            }

            BoxedUnit var15 = BoxedUnit.UNIT;
         }

         org.apache.spark.sql.catalyst.CurrentUserContext..MODULE$.CURRENT_USER().set(((Operation)this).getParentSession().getUserName());
         var16 = f.apply();
      } finally {
         org.apache.spark.sql.catalyst.CurrentUserContext..MODULE$.CURRENT_USER().remove();
         this.session().sparkContext().setLocalProperties(originalProps);
         if (originalSession instanceof Some var11) {
            SparkSession session = (SparkSession)var11.value();
            org.apache.spark.sql.SparkSession..MODULE$.setActiveSession(session);
            BoxedUnit var17 = BoxedUnit.UNIT;
         } else {
            if (!scala.None..MODULE$.equals(originalSession)) {
               throw new MatchError(originalSession);
            }

            org.apache.spark.sql.SparkSession..MODULE$.clearActiveSession();
            BoxedUnit var10001 = BoxedUnit.UNIT;
         }

      }

      return var16;
   }

   // $FF: synthetic method
   static String tableTypeString$(final SparkOperation $this, final CatalogTableType tableType) {
      return $this.tableTypeString(tableType);
   }

   default String tableTypeString(final CatalogTableType tableType) {
      boolean var9;
      label49: {
         label52: {
            CatalogTableType var10000 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.EXTERNAL();
            if (var10000 == null) {
               if (tableType == null) {
                  break label52;
               }
            } else if (var10000.equals(tableType)) {
               break label52;
            }

            label41: {
               var10000 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.MANAGED();
               if (var10000 == null) {
                  if (tableType == null) {
                     break label41;
                  }
               } else if (var10000.equals(tableType)) {
                  break label41;
               }

               var9 = false;
               break label49;
            }

            var9 = true;
            break label49;
         }

         var9 = true;
      }

      if (var9) {
         return "TABLE";
      } else {
         CatalogTableType var10 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW();
         if (var10 == null) {
            if (tableType == null) {
               return "VIEW";
            }
         } else if (var10.equals(tableType)) {
            return "VIEW";
         }

         throw new IllegalArgumentException("Unknown table type is found: " + tableType);
      }
   }

   // $FF: synthetic method
   static PartialFunction onError$(final SparkOperation $this) {
      return $this.onError();
   }

   default PartialFunction onError() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final SparkOperation $outer;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (x1 != null) {
               this.$outer.logError(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error operating ", " with "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HIVE_OPERATION_TYPE..MODULE$, ((Operation)this.$outer).getType())}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.$outer.statementId())}))))), x1);
               this.$outer.org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$setState(OperationState.ERROR);
               HiveThriftServer2$.MODULE$.eventManager().onStatementError(this.$outer.statementId(), x1.getMessage(), org.apache.spark.util.Utils..MODULE$.exceptionString(x1));
               if (x1 instanceof HiveSQLException) {
                  throw x1;
               } else {
                  throw HiveThriftServerErrors$.MODULE$.hiveOperatingError(((Operation)this.$outer).getType(), x1);
               }
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return x1 != null;
         }

         public {
            if (SparkOperation.this == null) {
               throw null;
            } else {
               this.$outer = SparkOperation.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final SparkOperation $this) {
      $this.statementId_$eq(((Operation)$this).getHandle().getHandleIdentifier().getPublicId().toString());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
