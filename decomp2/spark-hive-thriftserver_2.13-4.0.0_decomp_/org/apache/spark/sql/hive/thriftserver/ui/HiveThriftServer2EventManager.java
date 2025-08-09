package org.apache.spark.sql.hive.thriftserver.ui;

import org.apache.spark.SparkContext;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015a!B\b\u0011\u0001Iq\u0002\u0002C\u0013\u0001\u0005\u0003\u0005\u000b\u0011B\u0014\t\u000b-\u0002A\u0011\u0001\u0017\t\u000bA\u0002A\u0011A\u0019\t\u000bu\u0002A\u0011\u0001 \t\u000fA\u0003\u0011\u0013!C\u0001#\")A\f\u0001C\u0001;\")q\f\u0001C\u0001A\"9\u0011\u000eAI\u0001\n\u0003\t\u0006\"\u00026\u0001\t\u0003Y\u0007\"B8\u0001\t\u0003\u0001\b\"\u0002:\u0001\t\u0003\u0019\b\"B;\u0001\t\u00031\b\"\u0002?\u0001\t\u0003i\bBB@\u0001\t\u0003\t\tAA\u000fISZ,G\u000b\u001b:jMR\u001cVM\u001d<feJ*e/\u001a8u\u001b\u0006t\u0017mZ3s\u0015\t\t\"#\u0001\u0002vS*\u00111\u0003F\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003+Y\tA\u0001[5wK*\u0011q\u0003G\u0001\u0004gFd'BA\r\u001b\u0003\u0015\u0019\b/\u0019:l\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\u0014\u0005\u0001y\u0002C\u0001\u0011$\u001b\u0005\t#\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\n#AB!osJ+g-\u0001\u0002tG\u000e\u0001\u0001C\u0001\u0015*\u001b\u0005A\u0012B\u0001\u0016\u0019\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\u0019a\u0014N\\5u}Q\u0011Qf\f\t\u0003]\u0001i\u0011\u0001\u0005\u0005\u0006K\t\u0001\raJ\u0001\u0014a>\u001cH\u000fT5wK2K7\u000f^3oKJ\u0014Uo\u001d\u000b\u0003eU\u0002\"\u0001I\u001a\n\u0005Q\n#\u0001B+oSRDQAN\u0002A\u0002]\nQ!\u001a<f]R\u0004\"\u0001O\u001e\u000e\u0003eR!A\u000f\r\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018B\u0001\u001f:\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0002!=t7+Z:tS>t7I]3bi\u0016$G\u0003\u0002\u001a@\u0019:CQ\u0001\u0011\u0003A\u0002\u0005\u000b!!\u001b9\u0011\u0005\tKeBA\"H!\t!\u0015%D\u0001F\u0015\t1e%\u0001\u0004=e>|GOP\u0005\u0003\u0011\u0006\na\u0001\u0015:fI\u00164\u0017B\u0001&L\u0005\u0019\u0019FO]5oO*\u0011\u0001*\t\u0005\u0006\u001b\u0012\u0001\r!Q\u0001\ng\u0016\u001c8/[8o\u0013\u0012Dqa\u0014\u0003\u0011\u0002\u0003\u0007\u0011)\u0001\u0005vg\u0016\u0014h*Y7f\u0003iygnU3tg&|gn\u0011:fCR,G\r\n3fM\u0006,H\u000e\u001e\u00134+\u0005\u0011&FA!TW\u0005!\u0006CA+[\u001b\u00051&BA,Y\u0003%)hn\u00195fG.,GM\u0003\u0002ZC\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005m3&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006yqN\\*fgNLwN\\\"m_N,G\r\u0006\u00023=\")QJ\u0002a\u0001\u0003\u0006\u0001rN\\*uCR,W.\u001a8u'R\f'\u000f\u001e\u000b\u0007e\u0005\u001cGM\u001a5\t\u000b\t<\u0001\u0019A!\u0002\u0005%$\u0007\"B'\b\u0001\u0004\t\u0005\"B3\b\u0001\u0004\t\u0015!C:uCR,W.\u001a8u\u0011\u00159w\u00011\u0001B\u0003\u001d9'o\\;q\u0013\u0012DqaT\u0004\u0011\u0002\u0003\u0007\u0011)\u0001\u000ep]N#\u0018\r^3nK:$8\u000b^1si\u0012\"WMZ1vYR$S'A\tp]N#\u0018\r^3nK:$\b+\u0019:tK\u0012$2A\r7n\u0011\u0015\u0011\u0017\u00021\u0001B\u0011\u0015q\u0017\u00021\u0001B\u00035)\u00070Z2vi&|g\u000e\u00157b]\u0006\u0019rN\\*uCR,W.\u001a8u\u0007\u0006t7-\u001a7fIR\u0011!'\u001d\u0005\u0006E*\u0001\r!Q\u0001\u0013_:\u001cF/\u0019;f[\u0016tG\u000fV5nK>,H\u000f\u0006\u00023i\")!m\u0003a\u0001\u0003\u0006\u0001rN\\*uCR,W.\u001a8u\u000bJ\u0014xN\u001d\u000b\u0005e]D(\u0010C\u0003c\u0019\u0001\u0007\u0011\tC\u0003z\u0019\u0001\u0007\u0011)\u0001\u0005feJ|'/T:h\u0011\u0015YH\u00021\u0001B\u0003))'O]8s)J\f7-Z\u0001\u0012_:\u001cF/\u0019;f[\u0016tGOR5oSNDGC\u0001\u001a\u007f\u0011\u0015\u0011W\u00021\u0001B\u0003Eygn\u00149fe\u0006$\u0018n\u001c8DY>\u001cX\r\u001a\u000b\u0004e\u0005\r\u0001\"\u00022\u000f\u0001\u0004\t\u0005"
)
public class HiveThriftServer2EventManager {
   private final SparkContext sc;

   public void postLiveListenerBus(final SparkListenerEvent event) {
      this.sc.listenerBus().post(event);
   }

   public void onSessionCreated(final String ip, final String sessionId, final String userName) {
      this.postLiveListenerBus(new SparkListenerThriftServerSessionCreated(ip, sessionId, userName, System.currentTimeMillis()));
   }

   public String onSessionCreated$default$3() {
      return "UNKNOWN";
   }

   public void onSessionClosed(final String sessionId) {
      this.postLiveListenerBus(new SparkListenerThriftServerSessionClosed(sessionId, System.currentTimeMillis()));
   }

   public void onStatementStart(final String id, final String sessionId, final String statement, final String groupId, final String userName) {
      this.postLiveListenerBus(new SparkListenerThriftServerOperationStart(id, sessionId, statement, groupId, System.currentTimeMillis(), userName));
   }

   public String onStatementStart$default$5() {
      return "UNKNOWN";
   }

   public void onStatementParsed(final String id, final String executionPlan) {
      this.postLiveListenerBus(new SparkListenerThriftServerOperationParsed(id, executionPlan));
   }

   public void onStatementCanceled(final String id) {
      this.postLiveListenerBus(new SparkListenerThriftServerOperationCanceled(id, System.currentTimeMillis()));
   }

   public void onStatementTimeout(final String id) {
      this.postLiveListenerBus(new SparkListenerThriftServerOperationTimeout(id, System.currentTimeMillis()));
   }

   public void onStatementError(final String id, final String errorMsg, final String errorTrace) {
      this.postLiveListenerBus(new SparkListenerThriftServerOperationError(id, errorMsg, errorTrace, System.currentTimeMillis()));
   }

   public void onStatementFinish(final String id) {
      this.postLiveListenerBus(new SparkListenerThriftServerOperationFinish(id, System.currentTimeMillis()));
   }

   public void onOperationClosed(final String id) {
      this.postLiveListenerBus(new SparkListenerThriftServerOperationClosed(id, System.currentTimeMillis()));
   }

   public HiveThriftServer2EventManager(final SparkContext sc) {
      this.sc = sc;
   }
}
