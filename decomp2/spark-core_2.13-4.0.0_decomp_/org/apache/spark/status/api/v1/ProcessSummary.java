package org.apache.spark.status.api.v1;

import java.util.Date;
import scala.Option;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4A\u0001E\t\u0001=!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u00053\u0001\t\u0005\t\u0015!\u0003(\u0011!\u0019\u0004A!b\u0001\n\u00031\u0003\u0002\u0003\u001b\u0001\u0005\u0003\u0005\u000b\u0011B\u0014\t\u0011U\u0002!Q1A\u0005\u0002YB\u0001B\u000f\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\tw\u0001\u0011)\u0019!C\u0001y!A\u0001\t\u0001B\u0001B\u0003%Q\b\u0003\u0005B\u0001\t\u0015\r\u0011\"\u0001C\u0011!Y\u0005A!A!\u0002\u0013\u0019\u0005\u0002\u0003'\u0001\u0005\u000b\u0007I\u0011A'\t\u0011E\u0003!\u0011!Q\u0001\n9C\u0001B\u0015\u0001\u0003\u0006\u0004%\ta\u0015\u0005\t/\u0002\u0011\t\u0011)A\u0005)\"1\u0001\f\u0001C\u0001/e\u0013a\u0002\u0015:pG\u0016\u001c8oU;n[\u0006\u0014\u0018P\u0003\u0002\u0013'\u0005\u0011a/\r\u0006\u0003)U\t1!\u00199j\u0015\t1r#\u0001\u0004ti\u0006$Xo\u001d\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sO\u000e\u00011C\u0001\u0001 !\t\u00013%D\u0001\"\u0015\u0005\u0011\u0013!B:dC2\f\u0017B\u0001\u0013\"\u0005\u0019\te.\u001f*fM\u0006\u0011\u0011\u000eZ\u000b\u0002OA\u0011\u0001f\f\b\u0003S5\u0002\"AK\u0011\u000e\u0003-R!\u0001L\u000f\u0002\rq\u0012xn\u001c;?\u0013\tq\u0013%\u0001\u0004Qe\u0016$WMZ\u0005\u0003aE\u0012aa\u0015;sS:<'B\u0001\u0018\"\u0003\rIG\rI\u0001\tQ>\u001cH\u000fU8si\u0006I\u0001n\\:u!>\u0014H\u000fI\u0001\tSN\f5\r^5wKV\tq\u0007\u0005\u0002!q%\u0011\u0011(\t\u0002\b\u0005>|G.Z1o\u0003%I7/Q2uSZ,\u0007%\u0001\u0006u_R\fGnQ8sKN,\u0012!\u0010\t\u0003AyJ!aP\u0011\u0003\u0007%sG/A\u0006u_R\fGnQ8sKN\u0004\u0013aB1eIRKW.Z\u000b\u0002\u0007B\u0011A)S\u0007\u0002\u000b*\u0011aiR\u0001\u0005kRLGNC\u0001I\u0003\u0011Q\u0017M^1\n\u0005)+%\u0001\u0002#bi\u0016\f\u0001\"\u00193e)&lW\rI\u0001\u000be\u0016lwN^3US6,W#\u0001(\u0011\u0007\u0001z5)\u0003\u0002QC\t1q\n\u001d;j_:\f1B]3n_Z,G+[7fA\u0005Y\u0001O]8dKN\u001cHj\\4t+\u0005!\u0006\u0003\u0002\u0015VO\u001dJ!AV\u0019\u0003\u00075\u000b\u0007/\u0001\u0007qe>\u001cWm]:M_\u001e\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\t5rkfl\u00181bEB\u00111\fA\u0007\u0002#!)Qe\u0004a\u0001O!)1g\u0004a\u0001O!)Qg\u0004a\u0001o!)1h\u0004a\u0001{!)\u0011i\u0004a\u0001\u0007\")Aj\u0004a\u0001\u001d\")!k\u0004a\u0001)\u0002"
)
public class ProcessSummary {
   private final String id;
   private final String hostPort;
   private final boolean isActive;
   private final int totalCores;
   private final Date addTime;
   private final Option removeTime;
   private final Map processLogs;

   public String id() {
      return this.id;
   }

   public String hostPort() {
      return this.hostPort;
   }

   public boolean isActive() {
      return this.isActive;
   }

   public int totalCores() {
      return this.totalCores;
   }

   public Date addTime() {
      return this.addTime;
   }

   public Option removeTime() {
      return this.removeTime;
   }

   public Map processLogs() {
      return this.processLogs;
   }

   public ProcessSummary(final String id, final String hostPort, final boolean isActive, final int totalCores, final Date addTime, final Option removeTime, final Map processLogs) {
      this.id = id;
      this.hostPort = hostPort;
      this.isActive = isActive;
      this.totalCores = totalCores;
      this.addTime = addTime;
      this.removeTime = removeTime;
      this.processLogs = processLogs;
   }
}
