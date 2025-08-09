package org.apache.spark.status.api.v1;

import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-b\u0001\u0002\u0014(\u0001QB\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005{!AQ\n\u0001BC\u0002\u0013\u0005A\b\u0003\u0005O\u0001\t\u0005\t\u0015!\u0003>\u0011!y\u0005A!b\u0001\n\u0003a\u0004\u0002\u0003)\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u0011E\u0003!Q1A\u0005\u0002qB\u0001B\u0015\u0001\u0003\u0002\u0003\u0006I!\u0010\u0005\t'\u0002\u0011)\u0019!C\u0001y!AA\u000b\u0001B\u0001B\u0003%Q\b\u0003\u0005V\u0001\t\u0015\r\u0011\"\u0001=\u0011!1\u0006A!A!\u0002\u0013i\u0004\u0002C,\u0001\u0005\u000b\u0007I\u0011\u0001\u001f\t\u0011a\u0003!\u0011!Q\u0001\nuB\u0001\"\u0017\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t5\u0002\u0011\t\u0011)A\u0005{!A1\f\u0001BC\u0002\u0013\u0005A\b\u0003\u0005]\u0001\t\u0005\t\u0015!\u0003>\u0011!i\u0006A!b\u0001\n\u0003a\u0004\u0002\u00030\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u0011}\u0003!Q1A\u0005\u0002qB\u0001\u0002\u0019\u0001\u0003\u0002\u0003\u0006I!\u0010\u0005\tC\u0002\u0011)\u0019!C\u0001y!A!\r\u0001B\u0001B\u0003%Q\b\u0003\u0005d\u0001\t\u0015\r\u0011\"\u0001=\u0011!!\u0007A!A!\u0002\u0013i\u0004\u0002C3\u0001\u0005\u000b\u0007I\u0011\u0001\u001f\t\u0011\u0019\u0004!\u0011!Q\u0001\nuB\u0001b\u001a\u0001\u0003\u0006\u0004%\t\u0001\u001b\u0005\t[\u0002\u0011\t\u0011)A\u0005S\"Aa\u000e\u0001BC\u0002\u0013\u0005q\u000e\u0003\u0005t\u0001\t\u0005\t\u0015!\u0003q\u0011!!\bA!b\u0001\n\u0003)\b\u0002C=\u0001\u0005\u0003\u0005\u000b\u0011\u0002<\t\u0011i\u0004!Q1A\u0005\u0002mD\u0001b \u0001\u0003\u0002\u0003\u0006I\u0001 \u0005\t\u0003\u0003\u0001A\u0011A\u0017\u0002\u0004\t9B+Y:l\u001b\u0016$(/[2ESN$(/\u001b2vi&|gn\u001d\u0006\u0003Q%\n!A^\u0019\u000b\u0005)Z\u0013aA1qS*\u0011A&L\u0001\u0007gR\fG/^:\u000b\u00059z\u0013!B:qCJ\\'B\u0001\u00192\u0003\u0019\t\u0007/Y2iK*\t!'A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001kA\u0011a'O\u0007\u0002o)\t\u0001(A\u0003tG\u0006d\u0017-\u0003\u0002;o\t1\u0011I\\=SK\u001a\f\u0011\"];b]RLG.Z:\u0016\u0003u\u00022A\u0010$J\u001d\tyDI\u0004\u0002A\u00076\t\u0011I\u0003\u0002Cg\u00051AH]8pizJ\u0011\u0001O\u0005\u0003\u000b^\nq\u0001]1dW\u0006<W-\u0003\u0002H\u0011\nQ\u0011J\u001c3fq\u0016$7+Z9\u000b\u0005\u0015;\u0004C\u0001\u001cK\u0013\tYuG\u0001\u0004E_V\u0014G.Z\u0001\u000bcV\fg\u000e^5mKN\u0004\u0013\u0001\u00033ve\u0006$\u0018n\u001c8\u0002\u0013\u0011,(/\u0019;j_:\u0004\u0013aF3yK\u000e,Ho\u001c:EKN,'/[1mSj,G+[7f\u0003a)\u00070Z2vi>\u0014H)Z:fe&\fG.\u001b>f)&lW\rI\u0001\u001bKb,7-\u001e;pe\u0012+7/\u001a:jC2L'0Z\"qkRKW.Z\u0001\u001cKb,7-\u001e;pe\u0012+7/\u001a:jC2L'0Z\"qkRKW.\u001a\u0011\u0002\u001f\u0015DXmY;u_J\u0014VO\u001c+j[\u0016\f\u0001#\u001a=fGV$xN\u001d*v]RKW.\u001a\u0011\u0002\u001f\u0015DXmY;u_J\u001c\u0005/\u001e+j[\u0016\f\u0001#\u001a=fGV$xN]\"qkRKW.\u001a\u0011\u0002\u0015I,7/\u001e7u'&TX-A\u0006sKN,H\u000e^*ju\u0016\u0004\u0013!\u00036w[\u001e\u001bG+[7f\u0003)Qg/\\$d)&lW\rI\u0001\u0018e\u0016\u001cX\u000f\u001c;TKJL\u0017\r\\5{CRLwN\u001c+j[\u0016\f\u0001D]3tk2$8+\u001a:jC2L'0\u0019;j_:$\u0016.\\3!\u0003E9W\r\u001e;j]\u001e\u0014Vm];miRKW.Z\u0001\u0013O\u0016$H/\u001b8h%\u0016\u001cX\u000f\u001c;US6,\u0007%\u0001\btG\",G-\u001e7fe\u0012+G.Y=\u0002\u001fM\u001c\u0007.\u001a3vY\u0016\u0014H)\u001a7bs\u0002\n1\u0003]3bW\u0016CXmY;uS>tW*Z7pef\fA\u0003]3bW\u0016CXmY;uS>tW*Z7pef\u0004\u0013AE7f[>\u0014\u0018PQ=uKN\u001c\u0006/\u001b7mK\u0012\f1#\\3n_JL()\u001f;fgN\u0003\u0018\u000e\u001c7fI\u0002\n\u0001\u0003Z5tW\nKH/Z:Ta&dG.\u001a3\u0002#\u0011L7o\u001b\"zi\u0016\u001c8\u000b]5mY\u0016$\u0007%\u0001\u0007j]B,H/T3ue&\u001c7/F\u0001j!\tQ7.D\u0001(\u0013\tawE\u0001\rJ]B,H/T3ue&\u001cG)[:ue&\u0014W\u000f^5p]N\fQ\"\u001b8qkRlU\r\u001e:jGN\u0004\u0013!D8viB,H/T3ue&\u001c7/F\u0001q!\tQ\u0017/\u0003\u0002sO\tIr*\u001e;qkRlU\r\u001e:jG\u0012K7\u000f\u001e:jEV$\u0018n\u001c8t\u00039yW\u000f\u001e9vi6+GO]5dg\u0002\n!c\u001d5vM\u001adWMU3bI6+GO]5dgV\ta\u000f\u0005\u0002ko&\u0011\u0001p\n\u0002\u001f'\",hM\u001a7f%\u0016\fG-T3ue&\u001cG)[:ue&\u0014W\u000f^5p]N\f1c\u001d5vM\u001adWMU3bI6+GO]5dg\u0002\n1c\u001d5vM\u001adWm\u0016:ji\u0016lU\r\u001e:jGN,\u0012\u0001 \t\u0003UvL!A`\u0014\u0003?MCWO\u001a4mK^\u0013\u0018\u000e^3NKR\u0014\u0018n\u0019#jgR\u0014\u0018NY;uS>t7/\u0001\u000btQV4g\r\\3Xe&$X-T3ue&\u001c7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015M\u0005\u0015\u0011qAA\u0005\u0003\u0017\ti!a\u0004\u0002\u0012\u0005M\u0011QCA\f\u00033\tY\"!\b\u0002 \u0005\u0005\u00121EA\u0013\u0003O\tI\u0003\u0005\u0002k\u0001!)1(\na\u0001{!)Q*\na\u0001{!)q*\na\u0001{!)\u0011+\na\u0001{!)1+\na\u0001{!)Q+\na\u0001{!)q+\na\u0001{!)\u0011,\na\u0001{!)1,\na\u0001{!)Q,\na\u0001{!)q,\na\u0001{!)\u0011-\na\u0001{!)1-\na\u0001{!)Q-\na\u0001{!)q-\na\u0001S\")a.\na\u0001a\")A/\na\u0001m\")!0\na\u0001y\u0002"
)
public class TaskMetricDistributions {
   private final IndexedSeq quantiles;
   private final IndexedSeq duration;
   private final IndexedSeq executorDeserializeTime;
   private final IndexedSeq executorDeserializeCpuTime;
   private final IndexedSeq executorRunTime;
   private final IndexedSeq executorCpuTime;
   private final IndexedSeq resultSize;
   private final IndexedSeq jvmGcTime;
   private final IndexedSeq resultSerializationTime;
   private final IndexedSeq gettingResultTime;
   private final IndexedSeq schedulerDelay;
   private final IndexedSeq peakExecutionMemory;
   private final IndexedSeq memoryBytesSpilled;
   private final IndexedSeq diskBytesSpilled;
   private final InputMetricDistributions inputMetrics;
   private final OutputMetricDistributions outputMetrics;
   private final ShuffleReadMetricDistributions shuffleReadMetrics;
   private final ShuffleWriteMetricDistributions shuffleWriteMetrics;

   public IndexedSeq quantiles() {
      return this.quantiles;
   }

   public IndexedSeq duration() {
      return this.duration;
   }

   public IndexedSeq executorDeserializeTime() {
      return this.executorDeserializeTime;
   }

   public IndexedSeq executorDeserializeCpuTime() {
      return this.executorDeserializeCpuTime;
   }

   public IndexedSeq executorRunTime() {
      return this.executorRunTime;
   }

   public IndexedSeq executorCpuTime() {
      return this.executorCpuTime;
   }

   public IndexedSeq resultSize() {
      return this.resultSize;
   }

   public IndexedSeq jvmGcTime() {
      return this.jvmGcTime;
   }

   public IndexedSeq resultSerializationTime() {
      return this.resultSerializationTime;
   }

   public IndexedSeq gettingResultTime() {
      return this.gettingResultTime;
   }

   public IndexedSeq schedulerDelay() {
      return this.schedulerDelay;
   }

   public IndexedSeq peakExecutionMemory() {
      return this.peakExecutionMemory;
   }

   public IndexedSeq memoryBytesSpilled() {
      return this.memoryBytesSpilled;
   }

   public IndexedSeq diskBytesSpilled() {
      return this.diskBytesSpilled;
   }

   public InputMetricDistributions inputMetrics() {
      return this.inputMetrics;
   }

   public OutputMetricDistributions outputMetrics() {
      return this.outputMetrics;
   }

   public ShuffleReadMetricDistributions shuffleReadMetrics() {
      return this.shuffleReadMetrics;
   }

   public ShuffleWriteMetricDistributions shuffleWriteMetrics() {
      return this.shuffleWriteMetrics;
   }

   public TaskMetricDistributions(final IndexedSeq quantiles, final IndexedSeq duration, final IndexedSeq executorDeserializeTime, final IndexedSeq executorDeserializeCpuTime, final IndexedSeq executorRunTime, final IndexedSeq executorCpuTime, final IndexedSeq resultSize, final IndexedSeq jvmGcTime, final IndexedSeq resultSerializationTime, final IndexedSeq gettingResultTime, final IndexedSeq schedulerDelay, final IndexedSeq peakExecutionMemory, final IndexedSeq memoryBytesSpilled, final IndexedSeq diskBytesSpilled, final InputMetricDistributions inputMetrics, final OutputMetricDistributions outputMetrics, final ShuffleReadMetricDistributions shuffleReadMetrics, final ShuffleWriteMetricDistributions shuffleWriteMetrics) {
      this.quantiles = quantiles;
      this.duration = duration;
      this.executorDeserializeTime = executorDeserializeTime;
      this.executorDeserializeCpuTime = executorDeserializeCpuTime;
      this.executorRunTime = executorRunTime;
      this.executorCpuTime = executorCpuTime;
      this.resultSize = resultSize;
      this.jvmGcTime = jvmGcTime;
      this.resultSerializationTime = resultSerializationTime;
      this.gettingResultTime = gettingResultTime;
      this.schedulerDelay = schedulerDelay;
      this.peakExecutionMemory = peakExecutionMemory;
      this.memoryBytesSpilled = memoryBytesSpilled;
      this.diskBytesSpilled = diskBytesSpilled;
      this.inputMetrics = inputMetrics;
      this.outputMetrics = outputMetrics;
      this.shuffleReadMetrics = shuffleReadMetrics;
      this.shuffleWriteMetrics = shuffleWriteMetrics;
   }
}
