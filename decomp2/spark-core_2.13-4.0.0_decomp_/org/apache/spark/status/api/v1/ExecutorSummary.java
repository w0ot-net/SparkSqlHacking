package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import scala.Option;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t-c\u0001\u0002!B\u00019C\u0001\"\u0016\u0001\u0003\u0006\u0004%\tA\u0016\u0005\tE\u0002\u0011\t\u0011)A\u0005/\"A1\r\u0001BC\u0002\u0013\u0005a\u000b\u0003\u0005e\u0001\t\u0005\t\u0015!\u0003X\u0011!)\u0007A!b\u0001\n\u00031\u0007\u0002\u00036\u0001\u0005\u0003\u0005\u000b\u0011B4\t\u0011-\u0004!Q1A\u0005\u00021D\u0001\u0002\u001d\u0001\u0003\u0002\u0003\u0006I!\u001c\u0005\tc\u0002\u0011)\u0019!C\u0001e\"Aa\u000f\u0001B\u0001B\u0003%1\u000f\u0003\u0005x\u0001\t\u0015\r\u0011\"\u0001s\u0011!A\bA!A!\u0002\u0013\u0019\b\u0002C=\u0001\u0005\u000b\u0007I\u0011\u00017\t\u0011i\u0004!\u0011!Q\u0001\n5D\u0001b\u001f\u0001\u0003\u0006\u0004%\t\u0001\u001c\u0005\ty\u0002\u0011\t\u0011)A\u0005[\"AQ\u0010\u0001BC\u0002\u0013\u0005A\u000e\u0003\u0005\u007f\u0001\t\u0005\t\u0015!\u0003n\u0011!y\bA!b\u0001\n\u0003a\u0007\"CA\u0001\u0001\t\u0005\t\u0015!\u0003n\u0011%\t\u0019\u0001\u0001BC\u0002\u0013\u0005A\u000eC\u0005\u0002\u0006\u0001\u0011\t\u0011)A\u0005[\"I\u0011q\u0001\u0001\u0003\u0006\u0004%\t\u0001\u001c\u0005\n\u0003\u0013\u0001!\u0011!Q\u0001\n5D\u0011\"a\u0003\u0001\u0005\u000b\u0007I\u0011\u0001:\t\u0013\u00055\u0001A!A!\u0002\u0013\u0019\b\"CA\b\u0001\t\u0015\r\u0011\"\u0001s\u0011%\t\t\u0002\u0001B\u0001B\u0003%1\u000fC\u0005\u0002\u0014\u0001\u0011)\u0019!C\u0001e\"I\u0011Q\u0003\u0001\u0003\u0002\u0003\u0006Ia\u001d\u0005\n\u0003/\u0001!Q1A\u0005\u0002ID\u0011\"!\u0007\u0001\u0005\u0003\u0005\u000b\u0011B:\t\u0013\u0005m\u0001A!b\u0001\n\u0003\u0011\b\"CA\u000f\u0001\t\u0005\t\u0015!\u0003t\u0011%\ty\u0002\u0001BC\u0002\u0013\u0005a\rC\u0005\u00026\u0001\u0011\t\u0011)A\u0005O\"I\u0011\u0011\b\u0001\u0003\u0006\u0004%\tA\u001d\u0005\n\u0003w\u0001!\u0011!Q\u0001\nMD!\"!\u0010\u0001\u0005\u000b\u0007I\u0011AA \u0011)\t\t\u0006\u0001B\u0001B\u0003%\u0011\u0011\t\u0005\u000b\u0003'\u0002!Q1A\u0005\u0002\u0005U\u0003BCA/\u0001\t\u0005\t\u0015!\u0003\u0002X!Q\u0011q\f\u0001\u0003\u0006\u0004%\t!!\u0019\t\u0015\u0005\u0015\u0004A!A!\u0002\u0013\t\u0019\u0007\u0003\u0006\u0002h\u0001\u0011)\u0019!C\u0001\u0003SB!\"!\u001d\u0001\u0005\u0003\u0005\u000b\u0011BA6\u0011)\t\u0019\b\u0001BC\u0002\u0013\u0005\u0011Q\u000f\u0005\u000b\u0003\u0003\u0003!\u0011!Q\u0001\n\u0005]\u0004BCAB\u0001\t\u0015\r\u0011\"\u0001\u0002\u0006\"Q\u00111\u0013\u0001\u0003\u0002\u0003\u0006I!a\"\t\u0015\u0005]\u0005A!b\u0001\n\u0003\tI\n\u0003\u0006\u0002*\u0002\u0011\t\u0011)A\u0005\u00037C!\"a+\u0001\u0005\u000b\u0007I\u0011AA5\u0011)\ti\u000b\u0001B\u0001B\u0003%\u00111\u000e\u0005\u000b\u0003_\u0003!Q1A\u0005\u0002\u0005E\u0006BCAa\u0001\t\u0005\t\u0015!\u0003\u00024\"I\u00111\u0019\u0001\u0003\u0006\u0004%\t\u0001\u001c\u0005\n\u0003\u000b\u0004!\u0011!Q\u0001\n5D\u0011\"a2\u0001\u0005\u000b\u0007I\u0011\u00014\t\u0013\u0005%\u0007A!A!\u0002\u00139\u0007BCAf\u0001\t\u0015\r\u0011\"\u0001\u0002\u0006\"Q\u0011Q\u001a\u0001\u0003\u0002\u0003\u0006I!a\"\t\u0011\u0005=\u0007\u0001\"\u0001H\u0003#\u0014q\"\u0012=fGV$xN]*v[6\f'/\u001f\u0006\u0003\u0005\u000e\u000b!A^\u0019\u000b\u0005\u0011+\u0015aA1qS*\u0011aiR\u0001\u0007gR\fG/^:\u000b\u0005!K\u0015!B:qCJ\\'B\u0001&L\u0003\u0019\t\u0007/Y2iK*\tA*A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001\u001fB\u0011\u0001kU\u0007\u0002#*\t!+A\u0003tG\u0006d\u0017-\u0003\u0002U#\n1\u0011I\\=SK\u001a\f!!\u001b3\u0016\u0003]\u0003\"\u0001W0\u000f\u0005ek\u0006C\u0001.R\u001b\u0005Y&B\u0001/N\u0003\u0019a$o\\8u}%\u0011a,U\u0001\u0007!J,G-\u001a4\n\u0005\u0001\f'AB*ue&twM\u0003\u0002_#\u0006\u0019\u0011\u000e\u001a\u0011\u0002\u0011!|7\u000f\u001e)peR\f\u0011\u0002[8tiB{'\u000f\u001e\u0011\u0002\u0011%\u001c\u0018i\u0019;jm\u0016,\u0012a\u001a\t\u0003!\"L!![)\u0003\u000f\t{w\u000e\\3b]\u0006I\u0011n]!di&4X\rI\u0001\ne\u0012$'\t\\8dWN,\u0012!\u001c\t\u0003!:L!a\\)\u0003\u0007%sG/\u0001\u0006sI\u0012\u0014En\\2lg\u0002\n!\"\\3n_JLXk]3e+\u0005\u0019\bC\u0001)u\u0013\t)\u0018K\u0001\u0003M_:<\u0017aC7f[>\u0014\u00180V:fI\u0002\n\u0001\u0002Z5tWV\u001bX\rZ\u0001\nI&\u001c8.V:fI\u0002\n!\u0002^8uC2\u001cuN]3t\u0003-!x\u000e^1m\u0007>\u0014Xm\u001d\u0011\u0002\u00115\f\u0007\u0010V1tWN\f\u0011\"\\1y)\u0006\u001c8n\u001d\u0011\u0002\u0017\u0005\u001cG/\u001b<f)\u0006\u001c8n]\u0001\rC\u000e$\u0018N^3UCN\\7\u000fI\u0001\fM\u0006LG.\u001a3UCN\\7/\u0001\u0007gC&dW\r\u001a+bg.\u001c\b%\u0001\bd_6\u0004H.\u001a;fIR\u000b7o[:\u0002\u001f\r|W\u000e\u001d7fi\u0016$G+Y:lg\u0002\n!\u0002^8uC2$\u0016m]6t\u0003-!x\u000e^1m)\u0006\u001c8n\u001d\u0011\u0002\u001bQ|G/\u00197EkJ\fG/[8o\u00039!x\u000e^1m\tV\u0014\u0018\r^5p]\u0002\n1\u0002^8uC2<5\tV5nK\u0006aAo\u001c;bY\u001e\u001bE+[7fA\u0005yAo\u001c;bY&s\u0007/\u001e;CsR,7/\u0001\tu_R\fG.\u00138qkR\u0014\u0015\u0010^3tA\u0005\u0001Bo\u001c;bYNCWO\u001a4mKJ+\u0017\rZ\u0001\u0012i>$\u0018\r\\*ik\u001a4G.\u001a*fC\u0012\u0004\u0013!\u0005;pi\u0006d7\u000b[;gM2,wK]5uK\u0006\u0011Bo\u001c;bYNCWO\u001a4mK^\u0013\u0018\u000e^3!\u00035I7O\u00117bG.d\u0017n\u001d;fI\"Z1%a\t\u0002*\u0005-\u0012qFA\u0019!\r\u0001\u0016QE\u0005\u0004\u0003O\t&A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017EAA\u0017\u0003Y)8/\u001a\u0011jg\u0016C8\r\\;eK\u0012\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0017EAA\u001a\u0003\u0015\u0019d&\r\u00181\u00039I7O\u00117bG.d\u0017n\u001d;fI\u0002B3\u0002JA\u0012\u0003S\tY#a\f\u00022\u0005IQ.\u0019=NK6|'/_\u0001\u000b[\u0006DX*Z7pef\u0004\u0013aB1eIRKW.Z\u000b\u0003\u0003\u0003\u0002B!a\u0011\u0002N5\u0011\u0011Q\t\u0006\u0005\u0003\u000f\nI%\u0001\u0003vi&d'BAA&\u0003\u0011Q\u0017M^1\n\t\u0005=\u0013Q\t\u0002\u0005\t\u0006$X-\u0001\u0005bI\u0012$\u0016.\\3!\u0003)\u0011X-\\8wKRKW.Z\u000b\u0003\u0003/\u0002R\u0001UA-\u0003\u0003J1!a\u0017R\u0005\u0019y\u0005\u000f^5p]\u0006Y!/Z7pm\u0016$\u0016.\\3!\u00031\u0011X-\\8wKJ+\u0017m]8o+\t\t\u0019\u0007\u0005\u0003Q\u00033:\u0016!\u0004:f[>4XMU3bg>t\u0007%\u0001\u0007fq\u0016\u001cW\u000f^8s\u0019><7/\u0006\u0002\u0002lA)\u0001,!\u001cX/&\u0019\u0011qN1\u0003\u00075\u000b\u0007/A\u0007fq\u0016\u001cW\u000f^8s\u0019><7\u000fI\u0001\u000e[\u0016lwN]=NKR\u0014\u0018nY:\u0016\u0005\u0005]\u0004#\u0002)\u0002Z\u0005e\u0004\u0003BA>\u0003{j\u0011!Q\u0005\u0004\u0003\u007f\n%!D'f[>\u0014\u00180T3ue&\u001c7/\u0001\bnK6|'/_'fiJL7m\u001d\u0011\u0002'\td\u0017mY6mSN$X\rZ%o'R\fw-Z:\u0016\u0005\u0005\u001d\u0005\u0003\u0002-\u0002\n6L1!a#b\u0005\r\u0019V\r\u001e\u0015\fc\u0005\r\u0012\u0011FAH\u0003_\t\t$\t\u0002\u0002\u0012\u0006aRo]3!Kb\u001cG.\u001e3fI&s7\u000b^1hKN\u0004\u0013N\\:uK\u0006$\u0017\u0001\u00062mC\u000e\\G.[:uK\u0012Len\u0015;bO\u0016\u001c\b\u0005K\u00063\u0003G\tI#a$\u00020\u0005E\u0012!\u00059fC.lU-\\8ss6+GO]5dgV\u0011\u00111\u0014\t\u0006!\u0006e\u0013Q\u0014\t\u0005\u0003?\u000b)+\u0004\u0002\u0002\"*\u0019\u00111U$\u0002\u0011\u0015DXmY;u_JLA!a*\u0002\"\nyQ\t_3dkR|'/T3ue&\u001c7/\u0001\nqK\u0006\\W*Z7peflU\r\u001e:jGN\u0004\u0013AC1uiJL'-\u001e;fg\u0006Y\u0011\r\u001e;sS\n,H/Z:!\u0003%\u0011Xm]8ve\u000e,7/\u0006\u0002\u00024B1\u0001,!\u001cX\u0003k\u0003B!a.\u0002>6\u0011\u0011\u0011\u0018\u0006\u0004\u0003w;\u0015\u0001\u0003:fg>,(oY3\n\t\u0005}\u0016\u0011\u0018\u0002\u0014%\u0016\u001cx.\u001e:dK&sgm\u001c:nCRLwN\\\u0001\u000be\u0016\u001cx.\u001e:dKN\u0004\u0013!\u0005:fg>,(oY3Qe>4\u0017\u000e\\3JI\u0006\u0011\"/Z:pkJ\u001cW\r\u0015:pM&dW-\u00133!\u0003)I7/\u0012=dYV$W\rZ\u0001\fSN,\u0005p\u00197vI\u0016$\u0007%\u0001\tfq\u000edW\u000fZ3e\u0013:\u001cF/Y4fg\u0006\tR\r_2mk\u0012,G-\u00138Ti\u0006<Wm\u001d\u0011\u0002\rqJg.\u001b;?)\u0001\u000b\u0019.!6\u0002X\u0006e\u00171\\Ao\u0003?\f\t/a9\u0002f\u0006\u001d\u0018\u0011^Av\u0003[\fy/!=\u0002t\u0006U\u0018q_A}\u0003w\fi0a@\u0003\u0002\t\r!Q\u0001B\u0004\u0005\u0003\u0012\u0019E!\u0012\u0003H\t%\u0003cAA>\u0001!)Qk\u0010a\u0001/\")1m\u0010a\u0001/\")Qm\u0010a\u0001O\")1n\u0010a\u0001[\")\u0011o\u0010a\u0001g\")qo\u0010a\u0001g\")\u0011p\u0010a\u0001[\")1p\u0010a\u0001[\")Qp\u0010a\u0001[\")qp\u0010a\u0001[\"1\u00111A A\u00025Da!a\u0002@\u0001\u0004i\u0007BBA\u0006\u007f\u0001\u00071\u000f\u0003\u0004\u0002\u0010}\u0002\ra\u001d\u0005\u0007\u0003'y\u0004\u0019A:\t\r\u0005]q\b1\u0001t\u0011\u0019\tYb\u0010a\u0001g\"1\u0011qD A\u0002\u001dDa!!\u000f@\u0001\u0004\u0019\bbBA\u001f\u007f\u0001\u0007\u0011\u0011\t\u0005\b\u0003'z\u0004\u0019AA,\u0011\u001d\tyf\u0010a\u0001\u0003GBq!a\u001a@\u0001\u0004\tY\u0007C\u0004\u0002t}\u0002\r!a\u001e\t\u000f\u0005\ru\b1\u0001\u0002\b\"9\u0011qS A\u0002\u0005m\u0005\u0006\u0003B\u0004\u0005\u0017\u00119C!\u000b\u0011\t\t5!1E\u0007\u0003\u0005\u001fQAA!\u0005\u0003\u0014\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\t\tU!qC\u0001\tI\u0006$\u0018MY5oI*!!\u0011\u0004B\u000e\u0003\u001dQ\u0017mY6t_:TAA!\b\u0003 \u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0003\u0005C\t1aY8n\u0013\u0011\u0011)Ca\u0004\u0003\u001b)\u001bxN\\*fe&\fG.\u001b>f\u0003\u0015)8/\u001b8hG\t\u0011Y\u0003\u0005\u0003\u0002|\t5\u0012b\u0001B\u0018\u0003\niR\t_3dkR|'/T3ue&\u001c7OS:p]N+'/[1mSj,'\u000f\u000b\u0005\u0003\b\tM\"q\u0005B\u001d!\u0011\u0011iA!\u000e\n\t\t]\"q\u0002\u0002\u0010\u0015N|g\u000eR3tKJL\u0017\r\\5{K\u000e\u0012!1\b\t\u0005\u0003w\u0012i$C\u0002\u0003@\u0005\u0013q$\u0012=fGV$xN]'fiJL7m\u001d&t_:$Um]3sS\u0006d\u0017N_3s\u0011\u001d\tYk\u0010a\u0001\u0003WBq!a,@\u0001\u0004\t\u0019\f\u0003\u0004\u0002D~\u0002\r!\u001c\u0005\u0007\u0003\u000f|\u0004\u0019A4\t\u000f\u0005-w\b1\u0001\u0002\b\u0002"
)
public class ExecutorSummary {
   private final String id;
   private final String hostPort;
   private final boolean isActive;
   private final int rddBlocks;
   private final long memoryUsed;
   private final long diskUsed;
   private final int totalCores;
   private final int maxTasks;
   private final int activeTasks;
   private final int failedTasks;
   private final int completedTasks;
   private final int totalTasks;
   private final long totalDuration;
   private final long totalGCTime;
   private final long totalInputBytes;
   private final long totalShuffleRead;
   private final long totalShuffleWrite;
   /** @deprecated */
   private final boolean isBlacklisted;
   private final long maxMemory;
   private final Date addTime;
   private final Option removeTime;
   private final Option removeReason;
   private final Map executorLogs;
   private final Option memoryMetrics;
   /** @deprecated */
   private final Set blacklistedInStages;
   private final Option peakMemoryMetrics;
   private final Map attributes;
   private final Map resources;
   private final int resourceProfileId;
   private final boolean isExcluded;
   private final Set excludedInStages;

   public String id() {
      return this.id;
   }

   public String hostPort() {
      return this.hostPort;
   }

   public boolean isActive() {
      return this.isActive;
   }

   public int rddBlocks() {
      return this.rddBlocks;
   }

   public long memoryUsed() {
      return this.memoryUsed;
   }

   public long diskUsed() {
      return this.diskUsed;
   }

   public int totalCores() {
      return this.totalCores;
   }

   public int maxTasks() {
      return this.maxTasks;
   }

   public int activeTasks() {
      return this.activeTasks;
   }

   public int failedTasks() {
      return this.failedTasks;
   }

   public int completedTasks() {
      return this.completedTasks;
   }

   public int totalTasks() {
      return this.totalTasks;
   }

   public long totalDuration() {
      return this.totalDuration;
   }

   public long totalGCTime() {
      return this.totalGCTime;
   }

   public long totalInputBytes() {
      return this.totalInputBytes;
   }

   public long totalShuffleRead() {
      return this.totalShuffleRead;
   }

   public long totalShuffleWrite() {
      return this.totalShuffleWrite;
   }

   /** @deprecated */
   public boolean isBlacklisted() {
      return this.isBlacklisted;
   }

   public long maxMemory() {
      return this.maxMemory;
   }

   public Date addTime() {
      return this.addTime;
   }

   public Option removeTime() {
      return this.removeTime;
   }

   public Option removeReason() {
      return this.removeReason;
   }

   public Map executorLogs() {
      return this.executorLogs;
   }

   public Option memoryMetrics() {
      return this.memoryMetrics;
   }

   /** @deprecated */
   public Set blacklistedInStages() {
      return this.blacklistedInStages;
   }

   public Option peakMemoryMetrics() {
      return this.peakMemoryMetrics;
   }

   public Map attributes() {
      return this.attributes;
   }

   public Map resources() {
      return this.resources;
   }

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public boolean isExcluded() {
      return this.isExcluded;
   }

   public Set excludedInStages() {
      return this.excludedInStages;
   }

   public ExecutorSummary(final String id, final String hostPort, final boolean isActive, final int rddBlocks, final long memoryUsed, final long diskUsed, final int totalCores, final int maxTasks, final int activeTasks, final int failedTasks, final int completedTasks, final int totalTasks, final long totalDuration, final long totalGCTime, final long totalInputBytes, final long totalShuffleRead, final long totalShuffleWrite, final boolean isBlacklisted, final long maxMemory, final Date addTime, final Option removeTime, final Option removeReason, final Map executorLogs, final Option memoryMetrics, final Set blacklistedInStages, @JsonSerialize(using = ExecutorMetricsJsonSerializer.class) @JsonDeserialize(using = ExecutorMetricsJsonDeserializer.class) final Option peakMemoryMetrics, final Map attributes, final Map resources, final int resourceProfileId, final boolean isExcluded, final Set excludedInStages) {
      this.id = id;
      this.hostPort = hostPort;
      this.isActive = isActive;
      this.rddBlocks = rddBlocks;
      this.memoryUsed = memoryUsed;
      this.diskUsed = diskUsed;
      this.totalCores = totalCores;
      this.maxTasks = maxTasks;
      this.activeTasks = activeTasks;
      this.failedTasks = failedTasks;
      this.completedTasks = completedTasks;
      this.totalTasks = totalTasks;
      this.totalDuration = totalDuration;
      this.totalGCTime = totalGCTime;
      this.totalInputBytes = totalInputBytes;
      this.totalShuffleRead = totalShuffleRead;
      this.totalShuffleWrite = totalShuffleWrite;
      this.isBlacklisted = isBlacklisted;
      this.maxMemory = maxMemory;
      this.addTime = addTime;
      this.removeTime = removeTime;
      this.removeReason = removeReason;
      this.executorLogs = executorLogs;
      this.memoryMetrics = memoryMetrics;
      this.blacklistedInStages = blacklistedInStages;
      this.peakMemoryMetrics = peakMemoryMetrics;
      this.attributes = attributes;
      this.resources = resources;
      this.resourceProfileId = resourceProfileId;
      this.isExcluded = isExcluded;
      this.excludedInStages = excludedInStages;
   }
}
