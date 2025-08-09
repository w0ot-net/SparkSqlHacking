package org.apache.spark.sql.hive.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005Y3Q\u0001B\u0003\u0001\u000bEAQA\u0006\u0001\u0005\u0002aA\u0001B\u0007\u0001\t\u0006\u0004%Ia\u0007\u0005\u0006M\u0001!\te\n\u0002\n'\"LWn\u0018<3?NR!AB\u0004\u0002\r\rd\u0017.\u001a8u\u0015\tA\u0011\"\u0001\u0003iSZ,'B\u0001\u0006\f\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0011\u0001A\u0005\t\u0003'Qi\u0011!B\u0005\u0003+\u0015\u0011\u0011b\u00155j[~3(gX\u0019\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!\u0007\t\u0003'\u0001\tQcZ3u)\u0006\u0014G.Z:CsRK\b/Z'fi\"|G-F\u0001\u001d!\tiB%D\u0001\u001f\u0015\ty\u0002%A\u0004sK\u001adWm\u0019;\u000b\u0005\u0005\u0012\u0013\u0001\u00027b]\u001eT\u0011aI\u0001\u0005U\u00064\u0018-\u0003\u0002&=\t1Q*\u001a;i_\u0012\fqbZ3u)\u0006\u0014G.Z:CsRK\b/\u001a\u000b\u0006QyREJ\u0014\t\u0004SM2dB\u0001\u00161\u001d\tYc&D\u0001-\u0015\tis#\u0001\u0004=e>|GOP\u0005\u0002_\u0005)1oY1mC&\u0011\u0011GM\u0001\ba\u0006\u001c7.Y4f\u0015\u0005y\u0013B\u0001\u001b6\u0005\r\u0019V-\u001d\u0006\u0003cI\u0002\"aN\u001e\u000f\u0005aJ\u0004CA\u00163\u0013\tQ$'\u0001\u0004Qe\u0016$WMZ\u0005\u0003yu\u0012aa\u0015;sS:<'B\u0001\u001e3\u0011\u0015A1\u00011\u0001@!\t\u0001\u0005*D\u0001B\u0015\t\u00115)\u0001\u0005nKR\fG-\u0019;b\u0015\t!U)\u0001\u0002rY*\u0011\u0001B\u0012\u0006\u0003\u000f6\ta\u0001[1e_>\u0004\u0018BA%B\u0005\u0011A\u0015N^3\t\u000b-\u001b\u0001\u0019\u0001\u001c\u0002\r\u0011\u0014g*Y7f\u0011\u0015i5\u00011\u00017\u0003\u001d\u0001\u0018\r\u001e;fe:DQaT\u0002A\u0002A\u000b\u0011\u0002^1cY\u0016$\u0016\u0010]3\u0011\u0005E#V\"\u0001*\u000b\u0005M+\u0015!C7fi\u0006\u001cHo\u001c:f\u0013\t)&KA\u0005UC\ndW\rV=qK\u0002"
)
public class Shim_v2_3 extends Shim_v2_1 {
   private Method getTablesByTypeMethod;
   private volatile boolean bitmap$0;

   private Method getTablesByTypeMethod$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.getTablesByTypeMethod = this.findMethod(Hive.class, "getTablesByType", .MODULE$.wrapRefArray((Object[])(new Class[]{String.class, String.class, TableType.class})));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.getTablesByTypeMethod;
   }

   private Method getTablesByTypeMethod() {
      return !this.bitmap$0 ? this.getTablesByTypeMethod$lzycompute() : this.getTablesByTypeMethod;
   }

   public Seq getTablesByType(final Hive hive, final String dbName, final String pattern, final TableType tableType) {
      this.recordHiveCall();

      try {
         return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala((List)this.getTablesByTypeMethod().invoke(hive, dbName, pattern, tableType)).asScala().toSeq();
      } catch (Throwable var10) {
         if (var10 instanceof InvocationTargetException var8) {
            if (var8.getCause() instanceof HiveException) {
               Throwable cause = var8.getCause().getCause();
               if (cause != null && cause instanceof MetaException && cause.getMessage() != null && cause.getMessage().contains("Invalid method name: 'get_tables_by_type'")) {
                  throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.getTablesByTypeUnsupportedByHiveVersionError();
               }

               throw var8;
            }
         }

         throw var10;
      }
   }
}
