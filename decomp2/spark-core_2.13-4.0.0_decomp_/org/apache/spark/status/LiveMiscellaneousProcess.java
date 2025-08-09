package org.apache.spark.status;

import java.util.Date;
import org.apache.spark.status.api.v1.ProcessSummary;
import scala.None.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y4Q\u0001F\u000b\u0001/uA\u0001B\t\u0001\u0003\u0006\u0004%\t\u0001\n\u0005\te\u0001\u0011\t\u0011)A\u0005K!A1\u0007\u0001B\u0001B\u0003%A\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0004>\u0001\u0001\u0007I\u0011\u0001\u0013\t\u000fy\u0002\u0001\u0019!C\u0001\u007f!1Q\t\u0001Q!\n\u0015BqA\u0012\u0001A\u0002\u0013\u0005q\tC\u0004L\u0001\u0001\u0007I\u0011\u0001'\t\r9\u0003\u0001\u0015)\u0003I\u0011\u001dy\u0005\u00011A\u0005\u0002ACq\u0001\u0016\u0001A\u0002\u0013\u0005Q\u000b\u0003\u0004X\u0001\u0001\u0006K!\u0015\u0005\b1\u0002\u0011\r\u0011\"\u0001Z\u0011\u0019\u0011\u0007\u0001)A\u00055\"91\r\u0001a\u0001\n\u0003!\u0007bB7\u0001\u0001\u0004%\tA\u001c\u0005\u0007a\u0002\u0001\u000b\u0015B3\t\u000bE\u0004A\u0011\u000b:\u000311Kg/Z'jg\u000e,G\u000e\\1oK>,8\u000f\u0015:pG\u0016\u001c8O\u0003\u0002\u0017/\u000511\u000f^1ukNT!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'oZ\n\u0003\u0001y\u0001\"a\b\u0011\u000e\u0003UI!!I\u000b\u0003\u00151Kg/Z#oi&$\u00180A\u0005qe>\u001cWm]:JI\u000e\u0001Q#A\u0013\u0011\u0005\u0019zcBA\u0014.!\tA3&D\u0001*\u0015\tQ3%\u0001\u0004=e>|GO\u0010\u0006\u0002Y\u0005)1oY1mC&\u0011afK\u0001\u0007!J,G-\u001a4\n\u0005A\n$AB*ue&twM\u0003\u0002/W\u0005Q\u0001O]8dKN\u001c\u0018\n\u001a\u0011\u0002\u0019\r\u0014X-\u0019;j_:$\u0016.\\3\u0011\u0005U2T\"A\u0016\n\u0005]Z#\u0001\u0002'p]\u001e\fa\u0001P5oSRtDc\u0001\u001e<yA\u0011q\u0004\u0001\u0005\u0006E\u0011\u0001\r!\n\u0005\u0006g\u0011\u0001\r\u0001N\u0001\tQ>\u001cH\u000fU8si\u0006a\u0001n\\:u!>\u0014Ho\u0018\u0013fcR\u0011\u0001i\u0011\t\u0003k\u0005K!AQ\u0016\u0003\tUs\u0017\u000e\u001e\u0005\b\t\u001a\t\t\u00111\u0001&\u0003\rAH%M\u0001\nQ>\u001cH\u000fU8si\u0002\n\u0001\"[:BGRLg/Z\u000b\u0002\u0011B\u0011Q'S\u0005\u0003\u0015.\u0012qAQ8pY\u0016\fg.\u0001\u0007jg\u0006\u001bG/\u001b<f?\u0012*\u0017\u000f\u0006\u0002A\u001b\"9A)CA\u0001\u0002\u0004A\u0015!C5t\u0003\u000e$\u0018N^3!\u0003)!x\u000e^1m\u0007>\u0014Xm]\u000b\u0002#B\u0011QGU\u0005\u0003'.\u00121!\u00138u\u00039!x\u000e^1m\u0007>\u0014Xm]0%KF$\"\u0001\u0011,\t\u000f\u0011c\u0011\u0011!a\u0001#\u0006YAo\u001c;bY\u000e{'/Z:!\u0003\u001d\tG\r\u001a+j[\u0016,\u0012A\u0017\t\u00037\u0002l\u0011\u0001\u0018\u0006\u0003;z\u000bA!\u001e;jY*\tq,\u0001\u0003kCZ\f\u0017BA1]\u0005\u0011!\u0015\r^3\u0002\u0011\u0005$G\rV5nK\u0002\n1\u0002\u001d:pG\u0016\u001c8\u000fT8hgV\tQ\r\u0005\u0003gW\u0016*S\"A4\u000b\u0005!L\u0017!C5n[V$\u0018M\u00197f\u0015\tQ7&\u0001\u0006d_2dWm\u0019;j_:L!\u0001\\4\u0003\u00075\u000b\u0007/A\bqe>\u001cWm]:M_\u001e\u001cx\fJ3r)\t\u0001u\u000eC\u0004E#\u0005\u0005\t\u0019A3\u0002\u0019A\u0014xnY3tg2{wm\u001d\u0011\u0002\u0011\u0011|W\u000b\u001d3bi\u0016$\u0012a\u001d\t\u0003kQL!!^\u0016\u0003\u0007\u0005s\u0017\u0010"
)
public class LiveMiscellaneousProcess extends LiveEntity {
   private final String processId;
   private String hostPort;
   private boolean isActive;
   private int totalCores;
   private final Date addTime;
   private Map processLogs;

   public String processId() {
      return this.processId;
   }

   public String hostPort() {
      return this.hostPort;
   }

   public void hostPort_$eq(final String x$1) {
      this.hostPort = x$1;
   }

   public boolean isActive() {
      return this.isActive;
   }

   public void isActive_$eq(final boolean x$1) {
      this.isActive = x$1;
   }

   public int totalCores() {
      return this.totalCores;
   }

   public void totalCores_$eq(final int x$1) {
      this.totalCores = x$1;
   }

   public Date addTime() {
      return this.addTime;
   }

   public Map processLogs() {
      return this.processLogs;
   }

   public void processLogs_$eq(final Map x$1) {
      this.processLogs = x$1;
   }

   public Object doUpdate() {
      ProcessSummary info = new ProcessSummary(this.processId(), this.hostPort(), this.isActive(), this.totalCores(), this.addTime(), .MODULE$, this.processLogs());
      return new ProcessSummaryWrapper(info);
   }

   public LiveMiscellaneousProcess(final String processId, final long creationTime) {
      this.processId = processId;
      this.hostPort = null;
      this.isActive = true;
      this.totalCores = 0;
      this.addTime = new Date(creationTime);
      this.processLogs = (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }
}
