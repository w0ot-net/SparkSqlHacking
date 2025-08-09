package org.apache.spark.status.api.v1.streaming;

import java.util.Date;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005a\u0001\u0002\u000f\u001e\u00011B\u0001b\r\u0001\u0003\u0006\u0004%\t\u0001\u000e\u0005\tq\u0001\u0011\t\u0011)A\u0005k!A\u0011\b\u0001BC\u0002\u0013\u0005!\b\u0003\u0005D\u0001\t\u0005\t\u0015!\u0003<\u0011!!\u0003A!b\u0001\n\u0003!\u0005\u0002\u0003)\u0001\u0005\u0003\u0005\u000b\u0011B#\t\u0011E\u0003!Q1A\u0005\u0002QB\u0001B\u0015\u0001\u0003\u0002\u0003\u0006I!\u000e\u0005\t'\u0002\u0011)\u0019!C\u0001i!AA\u000b\u0001B\u0001B\u0003%Q\u0007\u0003\u0005V\u0001\t\u0015\r\u0011\"\u0001W\u0011!Q\u0006A!A!\u0002\u00139\u0006\u0002C.\u0001\u0005\u000b\u0007I\u0011\u0001,\t\u0011q\u0003!\u0011!Q\u0001\n]C\u0001\"\u0018\u0001\u0003\u0006\u0004%\tA\u0016\u0005\t=\u0002\u0011\t\u0011)A\u0005/\"Aq\f\u0001BC\u0002\u0013\u0005\u0001\r\u0003\u0005e\u0001\t\u0005\t\u0015!\u0003b\u0011!)\u0007A!b\u0001\n\u0003\u0001\u0007\u0002\u00034\u0001\u0005\u0003\u0005\u000b\u0011B1\t\u0011\u001d\u0004!Q1A\u0005\u0002\u0001D\u0001\u0002\u001b\u0001\u0003\u0002\u0003\u0006I!\u0019\u0005\tS\u0002\u0011)\u0019!C\u0001A\"A!\u000e\u0001B\u0001B\u0003%\u0011\r\u0003\u0005l\u0001\t\u0015\r\u0011\"\u0001m\u0011!q\u0007A!A!\u0002\u0013i\u0007BB8\u0001\t\u0003)\u0003OA\u0005CCR\u001c\u0007.\u00138g_*\u0011adH\u0001\ngR\u0014X-Y7j]\u001eT!\u0001I\u0011\u0002\u0005Y\f$B\u0001\u0012$\u0003\r\t\u0007/\u001b\u0006\u0003I\u0015\naa\u001d;biV\u001c(B\u0001\u0014(\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0013&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002U\u0005\u0019qN]4\u0004\u0001M\u0011\u0001!\f\t\u0003]Ej\u0011a\f\u0006\u0002a\u0005)1oY1mC&\u0011!g\f\u0002\u0007\u0003:L(+\u001a4\u0002\u000f\t\fGo\u00195JIV\tQ\u0007\u0005\u0002/m%\u0011qg\f\u0002\u0005\u0019>tw-\u0001\u0005cCR\u001c\u0007.\u00133!\u0003%\u0011\u0017\r^2i)&lW-F\u0001<!\ta\u0014)D\u0001>\u0015\tqt(\u0001\u0003vi&d'\"\u0001!\u0002\t)\fg/Y\u0005\u0003\u0005v\u0012A\u0001R1uK\u0006Q!-\u0019;dQRKW.\u001a\u0011\u0016\u0003\u0015\u0003\"AR'\u000f\u0005\u001d[\u0005C\u0001%0\u001b\u0005I%B\u0001&,\u0003\u0019a$o\\8u}%\u0011AjL\u0001\u0007!J,G-\u001a4\n\u00059{%AB*ue&twM\u0003\u0002M_\u000591\u000f^1ukN\u0004\u0013!\u00042bi\u000eDG)\u001e:bi&|g.\u0001\bcCR\u001c\u0007\u000eR;sCRLwN\u001c\u0011\u0002\u0013%t\u0007/\u001e;TSj,\u0017AC5oaV$8+\u001b>fA\u0005y1o\u00195fIVd\u0017N\\4EK2\f\u00170F\u0001X!\rq\u0003,N\u0005\u00033>\u0012aa\u00149uS>t\u0017\u0001E:dQ\u0016$W\u000f\\5oO\u0012+G.Y=!\u00039\u0001(o\\2fgNLgn\u001a+j[\u0016\fq\u0002\u001d:pG\u0016\u001c8/\u001b8h)&lW\rI\u0001\u000bi>$\u0018\r\u001c#fY\u0006L\u0018a\u0003;pi\u0006dG)\u001a7bs\u0002\n!C\\;n\u0003\u000e$\u0018N^3PkR\u0004X\u000f^(qgV\t\u0011\r\u0005\u0002/E&\u00111m\f\u0002\u0004\u0013:$\u0018a\u00058v[\u0006\u001bG/\u001b<f\u001fV$\b/\u001e;PaN\u0004\u0013!\u00068v[\u000e{W\u000e\u001d7fi\u0016$w*\u001e;qkR|\u0005o]\u0001\u0017]Vl7i\\7qY\u0016$X\rZ(viB,Ho\u00149tA\u0005\u0011b.^7GC&dW\rZ(viB,Ho\u00149t\u0003MqW/\u001c$bS2,GmT;uaV$x\n]:!\u0003EqW/\u001c+pi\u0006dw*\u001e;qkR|\u0005o]\u0001\u0013]VlGk\u001c;bY>+H\u000f];u\u001fB\u001c\b%\u0001\ngSJ\u001cHOR1jYV\u0014XMU3bg>tW#A7\u0011\u00079BV)A\ngSJ\u001cHOR1jYV\u0014XMU3bg>t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u000fcN$XO^<ysj\\H0 @\u0000!\t\u0011\b!D\u0001\u001e\u0011\u0015\u00194\u00041\u00016\u0011\u0015I4\u00041\u0001<\u0011\u0015!3\u00041\u0001F\u0011\u0015\t6\u00041\u00016\u0011\u0015\u00196\u00041\u00016\u0011\u0015)6\u00041\u0001X\u0011\u0015Y6\u00041\u0001X\u0011\u0015i6\u00041\u0001X\u0011\u0015y6\u00041\u0001b\u0011\u0015)7\u00041\u0001b\u0011\u001597\u00041\u0001b\u0011\u0015I7\u00041\u0001b\u0011\u0015Y7\u00041\u0001n\u0001"
)
public class BatchInfo {
   private final long batchId;
   private final Date batchTime;
   private final String status;
   private final long batchDuration;
   private final long inputSize;
   private final Option schedulingDelay;
   private final Option processingTime;
   private final Option totalDelay;
   private final int numActiveOutputOps;
   private final int numCompletedOutputOps;
   private final int numFailedOutputOps;
   private final int numTotalOutputOps;
   private final Option firstFailureReason;

   public long batchId() {
      return this.batchId;
   }

   public Date batchTime() {
      return this.batchTime;
   }

   public String status() {
      return this.status;
   }

   public long batchDuration() {
      return this.batchDuration;
   }

   public long inputSize() {
      return this.inputSize;
   }

   public Option schedulingDelay() {
      return this.schedulingDelay;
   }

   public Option processingTime() {
      return this.processingTime;
   }

   public Option totalDelay() {
      return this.totalDelay;
   }

   public int numActiveOutputOps() {
      return this.numActiveOutputOps;
   }

   public int numCompletedOutputOps() {
      return this.numCompletedOutputOps;
   }

   public int numFailedOutputOps() {
      return this.numFailedOutputOps;
   }

   public int numTotalOutputOps() {
      return this.numTotalOutputOps;
   }

   public Option firstFailureReason() {
      return this.firstFailureReason;
   }

   public BatchInfo(final long batchId, final Date batchTime, final String status, final long batchDuration, final long inputSize, final Option schedulingDelay, final Option processingTime, final Option totalDelay, final int numActiveOutputOps, final int numCompletedOutputOps, final int numFailedOutputOps, final int numTotalOutputOps, final Option firstFailureReason) {
      this.batchId = batchId;
      this.batchTime = batchTime;
      this.status = status;
      this.batchDuration = batchDuration;
      this.inputSize = inputSize;
      this.schedulingDelay = schedulingDelay;
      this.processingTime = processingTime;
      this.totalDelay = totalDelay;
      this.numActiveOutputOps = numActiveOutputOps;
      this.numCompletedOutputOps = numCompletedOutputOps;
      this.numFailedOutputOps = numFailedOutputOps;
      this.numTotalOutputOps = numTotalOutputOps;
      this.firstFailureReason = firstFailureReason;
   }
}
