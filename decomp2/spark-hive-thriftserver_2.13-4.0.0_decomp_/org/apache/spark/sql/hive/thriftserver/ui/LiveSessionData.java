package org.apache.spark.sql.hive.thriftserver.ui;

import org.apache.spark.status.LiveEntity;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4Q!\u0005\n\u0001)\u0001B\u0001b\n\u0001\u0003\u0006\u0004%\t!\u000b\u0005\to\u0001\u0011\t\u0011)A\u0005U!A\u0001\b\u0001BC\u0002\u0013\u0005\u0011\b\u0003\u0005?\u0001\t\u0005\t\u0015!\u0003;\u0011!y\u0004A!b\u0001\n\u0003I\u0003\u0002\u0003!\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u0011\u0005\u0003!Q1A\u0005\u0002%B\u0001B\u0011\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\b\u0017\u0002\u0001\r\u0011\"\u0001:\u0011\u001da\u0005\u00011A\u0005\u00025Caa\u0015\u0001!B\u0013Q\u0004b\u0002+\u0001\u0001\u0004%\t!\u0016\u0005\b3\u0002\u0001\r\u0011\"\u0001[\u0011\u0019a\u0006\u0001)Q\u0005-\")Q\f\u0001C)=\nyA*\u001b<f'\u0016\u001c8/[8o\t\u0006$\u0018M\u0003\u0002\u0014)\u0005\u0011Q/\u001b\u0006\u0003+Y\tA\u0002\u001e5sS\u001a$8/\u001a:wKJT!a\u0006\r\u0002\t!Lg/\u001a\u0006\u00033i\t1a]9m\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7C\u0001\u0001\"!\t\u0011S%D\u0001$\u0015\t!#$\u0001\u0004ti\u0006$Xo]\u0005\u0003M\r\u0012!\u0002T5wK\u0016sG/\u001b;z\u0003%\u0019Xm]:j_:LEm\u0001\u0001\u0016\u0003)\u0002\"a\u000b\u001b\u000f\u00051\u0012\u0004CA\u00171\u001b\u0005q#BA\u0018)\u0003\u0019a$o\\8u})\t\u0011'A\u0003tG\u0006d\u0017-\u0003\u00024a\u00051\u0001K]3eK\u001aL!!\u000e\u001c\u0003\rM#(/\u001b8h\u0015\t\u0019\u0004'\u0001\u0006tKN\u001c\u0018n\u001c8JI\u0002\nab\u001d;beR$\u0016.\\3Ti\u0006l\u0007/F\u0001;!\tYD(D\u00011\u0013\ti\u0004G\u0001\u0003M_:<\u0017aD:uCJ$H+[7f'R\fW\u000e\u001d\u0011\u0002\u0005%\u0004\u0018aA5qA\u0005AQo]3s]\u0006lW-A\u0005vg\u0016\u0014h.Y7fA\u00051A(\u001b8jiz\"R!R$I\u0013*\u0003\"A\u0012\u0001\u000e\u0003IAQaJ\u0005A\u0002)BQ\u0001O\u0005A\u0002iBQaP\u0005A\u0002)BQ!Q\u0005A\u0002)\nqBZ5oSNDG+[7fgR\fW\u000e]\u0001\u0014M&t\u0017n\u001d5US6,7\u000f^1na~#S-\u001d\u000b\u0003\u001dF\u0003\"aO(\n\u0005A\u0003$\u0001B+oSRDqAU\u0006\u0002\u0002\u0003\u0007!(A\u0002yIE\n\u0001CZ5oSNDG+[7fgR\fW\u000e\u001d\u0011\u0002\u001dQ|G/\u00197Fq\u0016\u001cW\u000f^5p]V\ta\u000b\u0005\u0002</&\u0011\u0001\f\r\u0002\u0004\u0013:$\u0018A\u0005;pi\u0006dW\t_3dkRLwN\\0%KF$\"AT.\t\u000fIs\u0011\u0011!a\u0001-\u0006yAo\u001c;bY\u0016CXmY;uS>t\u0007%\u0001\u0005e_V\u0003H-\u0019;f)\u0005y\u0006CA\u001ea\u0013\t\t\u0007GA\u0002B]f\u0004"
)
public class LiveSessionData extends LiveEntity {
   private final String sessionId;
   private final long startTimeStamp;
   private final String ip;
   private final String username;
   private long finishTimestamp;
   private int totalExecution;

   public String sessionId() {
      return this.sessionId;
   }

   public long startTimeStamp() {
      return this.startTimeStamp;
   }

   public String ip() {
      return this.ip;
   }

   public String username() {
      return this.username;
   }

   public long finishTimestamp() {
      return this.finishTimestamp;
   }

   public void finishTimestamp_$eq(final long x$1) {
      this.finishTimestamp = x$1;
   }

   public int totalExecution() {
      return this.totalExecution;
   }

   public void totalExecution_$eq(final int x$1) {
      this.totalExecution = x$1;
   }

   public Object doUpdate() {
      return new SessionInfo(this.sessionId(), this.startTimeStamp(), this.ip(), this.username(), this.finishTimestamp(), (long)this.totalExecution());
   }

   public LiveSessionData(final String sessionId, final long startTimeStamp, final String ip, final String username) {
      this.sessionId = sessionId;
      this.startTimeStamp = startTimeStamp;
      this.ip = ip;
      this.username = username;
      this.finishTimestamp = 0L;
      this.totalExecution = 0;
   }
}
