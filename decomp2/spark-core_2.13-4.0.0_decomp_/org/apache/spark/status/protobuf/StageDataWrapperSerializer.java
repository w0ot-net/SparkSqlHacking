package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.commons.collections4.MapUtils;
import org.apache.spark.status.StageDataWrapper;
import org.apache.spark.status.api.v1.ExecutorMetricsDistributions;
import org.apache.spark.status.api.v1.ExecutorPeakMetricsDistributions;
import org.apache.spark.status.api.v1.ExecutorStageSummary;
import org.apache.spark.status.api.v1.InputMetricDistributions;
import org.apache.spark.status.api.v1.InputMetrics;
import org.apache.spark.status.api.v1.OutputMetricDistributions;
import org.apache.spark.status.api.v1.OutputMetrics;
import org.apache.spark.status.api.v1.ShufflePushReadMetricDistributions;
import org.apache.spark.status.api.v1.ShufflePushReadMetrics;
import org.apache.spark.status.api.v1.ShuffleReadMetricDistributions;
import org.apache.spark.status.api.v1.ShuffleReadMetrics;
import org.apache.spark.status.api.v1.ShuffleWriteMetricDistributions;
import org.apache.spark.status.api.v1.ShuffleWriteMetrics;
import org.apache.spark.status.api.v1.SpeculationStageSummary;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.StageStatus;
import org.apache.spark.status.api.v1.TaskData;
import org.apache.spark.status.api.v1.TaskMetricDistributions;
import org.apache.spark.status.api.v1.TaskMetrics;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.collection.mutable.ArrayBuffer;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tEc!\u0002\u0014(\u0001\u001d\n\u0004\"\u0002!\u0001\t\u0003\u0011\u0005\"\u0002#\u0001\t\u0003*\u0005\"\u0002(\u0001\t\u0013y\u0005\"\u00021\u0001\t\u0013\t\u0007\"B5\u0001\t\u0013Q\u0007\"\u0002:\u0001\t\u0013\u0019\b\"B>\u0001\t\u0013a\bbBA\u0005\u0001\u0011%\u00111\u0002\u0005\b\u00037\u0001A\u0011BA\u000f\u0011\u001d\ti\u0003\u0001C\u0005\u0003_Aq!a\u0010\u0001\t\u0013\t\t\u0005C\u0004\u0002R\u0001!I!a\u0015\t\u000f\u0005\r\u0004\u0001\"\u0003\u0002f!9\u0011Q\u000f\u0001\u0005\n\u0005]\u0004bBAD\u0001\u0011%\u0011\u0011\u0012\u0005\b\u00033\u0003A\u0011BAN\u0011\u001d\tY\u000b\u0001C\u0005\u0003[Cq!!0\u0001\t\u0013\ty\fC\u0004\u0002P\u0002!I!!5\t\u000f\u0005\u0005\b\u0001\"\u0011\u0002d\"9\u0011\u0011\u001e\u0001\u0005\n\u0005-\bbBAy\u0001\u0011%\u00111\u001f\u0005\b\u0003o\u0004A\u0011BA}\u0011\u001d\ti\u0010\u0001C\u0005\u0003\u007fDqAa\u0001\u0001\t\u0013\u0011)\u0001C\u0004\u0003\n\u0001!IAa\u0003\t\u000f\t=\u0001\u0001\"\u0003\u0003\u0012!9!Q\u0003\u0001\u0005\n\t]\u0001b\u0002B\u000e\u0001\u0011%!Q\u0004\u0005\b\u0005C\u0001A\u0011\u0002B\u0012\u0011\u001d\u00119\u0003\u0001C\u0005\u0005SAqA!\f\u0001\t\u0013\u0011y\u0003C\u0004\u00034\u0001!IA!\u000e\t\u000f\te\u0002\u0001\"\u0003\u0003<!9!q\b\u0001\u0005\n\t\u0005\u0003b\u0002B#\u0001\u0011%!q\t\u0005\b\u0005\u0017\u0002A\u0011\u0002B'\u0005i\u0019F/Y4f\t\u0006$\u0018m\u0016:baB,'oU3sS\u0006d\u0017N_3s\u0015\tA\u0013&\u0001\u0005qe>$xNY;g\u0015\tQ3&\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003Y5\nQa\u001d9be.T!AL\u0018\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0014aA8sON\u0019\u0001A\r\u001d\u0011\u0005M2T\"\u0001\u001b\u000b\u0003U\nQa]2bY\u0006L!a\u000e\u001b\u0003\r\u0005s\u0017PU3g!\rI$\bP\u0007\u0002O%\u00111h\n\u0002\u000e!J|Go\u001c2vMN+'\u000fR3\u0011\u0005urT\"A\u0015\n\u0005}J#\u0001E*uC\u001e,G)\u0019;b/J\f\u0007\u000f]3s\u0003\u0019a\u0014N\\5u}\r\u0001A#A\"\u0011\u0005e\u0002\u0011!C:fe&\fG.\u001b>f)\t1E\nE\u00024\u000f&K!\u0001\u0013\u001b\u0003\u000b\u0005\u0013(/Y=\u0011\u0005MR\u0015BA&5\u0005\u0011\u0011\u0015\u0010^3\t\u000b5\u0013\u0001\u0019\u0001\u001f\u0002\u000b%t\u0007/\u001e;\u0002%M,'/[1mSj,7\u000b^1hK\u0012\u000bG/\u0019\u000b\u0003!^\u0003\"!\u0015+\u000f\u0005e\u0012\u0016BA*(\u0003)\u0019Fo\u001c:f)f\u0004Xm]\u0005\u0003+Z\u0013\u0011b\u0015;bO\u0016$\u0015\r^1\u000b\u0005M;\u0003\"\u0002-\u0004\u0001\u0004I\u0016!C:uC\u001e,G)\u0019;b!\tQv,D\u0001\\\u0015\taV,\u0001\u0002wc)\u0011a,K\u0001\u0004CBL\u0017BA+\\\u0003E\u0019XM]5bY&TX\rV1tW\u0012\u000bG/\u0019\u000b\u0003E\u0016\u0004\"!U2\n\u0005\u00114&\u0001\u0003+bg.$\u0015\r^1\t\u000b\u0019$\u0001\u0019A4\u0002\u0003Q\u0004\"A\u00175\n\u0005\u0011\\\u0016\u0001F:fe&\fG.\u001b>f)\u0006\u001c8.T3ue&\u001c7\u000f\u0006\u0002l]B\u0011\u0011\u000b\\\u0005\u0003[Z\u00131\u0002V1tW6+GO]5dg\")q.\u0002a\u0001a\u0006\u0011A/\u001c\t\u00035FL!!\\.\u0002+M,'/[1mSj,\u0017J\u001c9vi6+GO]5dgR\u0011Ao\u001e\t\u0003#VL!A\u001e,\u0003\u0019%s\u0007/\u001e;NKR\u0014\u0018nY:\t\u000ba4\u0001\u0019A=\u0002\u0005%l\u0007C\u0001.{\u0013\t18,\u0001\ftKJL\u0017\r\\5{K>+H\u000f];u\u001b\u0016$(/[2t)\ri\u0018\u0011\u0001\t\u0003#zL!a ,\u0003\u001b=+H\u000f];u\u001b\u0016$(/[2t\u0011\u001d\t\u0019a\u0002a\u0001\u0003\u000b\t!a\\7\u0011\u0007i\u000b9!\u0003\u0002\u00007\u0006Y2/\u001a:jC2L'0Z*ik\u001a4G.\u001a*fC\u0012lU\r\u001e:jGN$B!!\u0004\u0002\u0014A\u0019\u0011+a\u0004\n\u0007\u0005EaK\u0001\nTQV4g\r\\3SK\u0006$W*\u001a;sS\u000e\u001c\bbBA\u000b\u0011\u0001\u0007\u0011qC\u0001\u0004gJl\u0007c\u0001.\u0002\u001a%\u0019\u0011\u0011C.\u0002?M,'/[1mSj,7\u000b[;gM2,\u0007+^:i%\u0016\fG-T3ue&\u001c7\u000f\u0006\u0003\u0002 \u0005\u0015\u0002cA)\u0002\"%\u0019\u00111\u0005,\u0003-MCWO\u001a4mKB+8\u000f\u001b*fC\u0012lU\r\u001e:jGNDq!a\n\n\u0001\u0004\tI#\u0001\u0003taJl\u0007c\u0001.\u0002,%\u0019\u00111E.\u00029M,'/[1mSj,7\u000b[;gM2,wK]5uK6+GO]5dgR!\u0011\u0011GA\u001c!\r\t\u00161G\u0005\u0004\u0003k1&aE*ik\u001a4G.Z,sSR,W*\u001a;sS\u000e\u001c\bbBA\u001d\u0015\u0001\u0007\u00111H\u0001\u0004g^l\u0007c\u0001.\u0002>%\u0019\u0011QG.\u0002AM,'/[1mSj,7\u000b]3dk2\fG/[8o'R\fw-Z*v[6\f'/\u001f\u000b\u0005\u0003\u0007\nI\u0005E\u0002R\u0003\u000bJ1!a\u0012W\u0005]\u0019\u0006/Z2vY\u0006$\u0018n\u001c8Ti\u0006<WmU;n[\u0006\u0014\u0018\u0010C\u0004\u0002L-\u0001\r!!\u0014\u0002\u0007M\u001c8\u000fE\u0002[\u0003\u001fJ1!a\u0012\\\u0003\u0001\u001aXM]5bY&TX\rV1tW6+GO]5d\t&\u001cHO]5ckRLwN\\:\u0015\t\u0005U\u00131\f\t\u0004#\u0006]\u0013bAA--\n9B+Y:l\u001b\u0016$(/[2ESN$(/\u001b2vi&|gn\u001d\u0005\b\u0003;b\u0001\u0019AA0\u0003\r!X\u000e\u001a\t\u00045\u0006\u0005\u0014bAA-7\u0006\t3/\u001a:jC2L'0Z%oaV$X*\u001a;sS\u000e$\u0015n\u001d;sS\n,H/[8ogR!\u0011qMA7!\r\t\u0016\u0011N\u0005\u0004\u0003W2&\u0001G%oaV$X*\u001a;sS\u000e$\u0015n\u001d;sS\n,H/[8og\"9\u0011qN\u0007A\u0002\u0005E\u0014aA5nIB\u0019!,a\u001d\n\u0007\u0005-4,\u0001\u0012tKJL\u0017\r\\5{K>+H\u000f];u\u001b\u0016$(/[2ESN$(/\u001b2vi&|gn\u001d\u000b\u0005\u0003s\ny\bE\u0002R\u0003wJ1!! W\u0005eyU\u000f\u001e9vi6+GO]5d\t&\u001cHO]5ckRLwN\\:\t\u000f\u0005\u0005e\u00021\u0001\u0002\u0004\u0006\u0019q.\u001c3\u0011\u0007i\u000b))C\u0002\u0002~m\u000bqe]3sS\u0006d\u0017N_3TQV4g\r\\3SK\u0006$W*\u001a;sS\u000e$\u0015n\u001d;sS\n,H/[8ogR!\u00111RAI!\r\t\u0016QR\u0005\u0004\u0003\u001f3&AH*ik\u001a4G.\u001a*fC\u0012lU\r\u001e:jG\u0012K7\u000f\u001e:jEV$\u0018n\u001c8t\u0011\u001d\t\u0019j\u0004a\u0001\u0003+\u000bAa\u001d:nIB\u0019!,a&\n\u0007\u0005=5,A\u0016tKJL\u0017\r\\5{KNCWO\u001a4mKB+8\u000f\u001b*fC\u0012lU\r\u001e:jG\u0012K7\u000f\u001e:jEV$\u0018n\u001c8t)\u0011\ti*a)\u0011\u0007E\u000by*C\u0002\u0002\"Z\u0013!e\u00155vM\u001adW\rU;tQJ+\u0017\rZ'fiJL7\rR5tiJL'-\u001e;j_:\u001c\bbBAS!\u0001\u0007\u0011qU\u0001\u0006gB\u0014X\u000e\u001a\t\u00045\u0006%\u0016bAAQ7\u0006A3/\u001a:jC2L'0Z*ik\u001a4G.Z,sSR,W*\u001a;sS\u000e$\u0015n\u001d;sS\n,H/[8ogR!\u0011qVA[!\r\t\u0016\u0011W\u0005\u0004\u0003g3&aH*ik\u001a4G.Z,sSR,W*\u001a;sS\u000e$\u0015n\u001d;sS\n,H/[8og\"9\u0011qW\tA\u0002\u0005e\u0016\u0001B:x[\u0012\u00042AWA^\u0013\r\t\u0019lW\u0001&g\u0016\u0014\u0018.\u00197ju\u0016,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001cH)[:ue&\u0014W\u000f^5p]N$B!!1\u0002HB\u0019\u0011+a1\n\u0007\u0005\u0015gK\u0001\u000fFq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t\t&\u001cHO]5ckRLwN\\:\t\u000f\u0005%'\u00031\u0001\u0002L\u0006\u0019Q-\u001c3\u0011\u0007i\u000bi-C\u0002\u0002Fn\u000b\u0011f]3sS\u0006d\u0017N_3Fq\u0016\u001cW\u000f^8s!\u0016\f7.T3ue&\u001c7\u000fR5tiJL'-\u001e;j_:\u001cH\u0003BAj\u00033\u00042!UAk\u0013\r\t9N\u0016\u0002!\u000bb,7-\u001e;peB+\u0017m['fiJL7m\u001d#jgR\u0014\u0018NY;uS>t7\u000fC\u0004\u0002\\N\u0001\r!!8\u0002\t\u0015\u0004X\u000e\u001a\t\u00045\u0006}\u0017bAAl7\u0006YA-Z:fe&\fG.\u001b>f)\ra\u0014Q\u001d\u0005\u0007\u0003O$\u0002\u0019\u0001$\u0002\u000b\tLH/Z:\u0002)\u0011,7/\u001a:jC2L'0Z*uC\u001e,G)\u0019;b)\rI\u0016Q\u001e\u0005\u0007\u0003_,\u0002\u0019\u0001)\u0002\r\tLg.\u0019:z\u0003\t\"Wm]3sS\u0006d\u0017N_3Ta\u0016\u001cW\u000f\\1uS>t7\u000b^1hKN+X.\\1ssR!\u0011QJA{\u0011\u001d\tyO\u0006a\u0001\u0003\u0007\n!\u0005Z3tKJL\u0017\r\\5{KR\u000b7o['fiJL7\rR5tiJL'-\u001e;j_:\u001cH\u0003BA0\u0003wDq!a<\u0018\u0001\u0004\t)&A\u0012eKN,'/[1mSj,\u0017J\u001c9vi6+GO]5d\t&\u001cHO]5ckRLwN\\:\u0015\t\u0005E$\u0011\u0001\u0005\b\u0003_D\u0002\u0019AA4\u0003\u0011\"Wm]3sS\u0006d\u0017N_3PkR\u0004X\u000f^'fiJL7\rR5tiJL'-\u001e;j_:\u001cH\u0003BAB\u0005\u000fAq!a<\u001a\u0001\u0004\tI(A\u0015eKN,'/[1mSj,7\u000b[;gM2,'+Z1e\u001b\u0016$(/[2ESN$(/\u001b2vi&|gn\u001d\u000b\u0005\u0003+\u0013i\u0001C\u0004\u0002pj\u0001\r!a#\u0002]\u0011,7/\u001a:jC2L'0Z*ik\u001a4G.\u001a)vg\"\u0014V-\u00193NKR\u0014\u0018nY:ESN$(/\u001b2vi&|gn\u001d\u000b\u0005\u0003O\u0013\u0019\u0002C\u0004\u0002pn\u0001\r!!(\u0002U\u0011,7/\u001a:jC2L'0Z*ik\u001a4G.Z,sSR,W*\u001a;sS\u000e$\u0015n\u001d;sS\n,H/[8ogR!\u0011\u0011\u0018B\r\u0011\u001d\ty\u000f\ba\u0001\u0003_\u000bq\u0005Z3tKJL\u0017\r\\5{K\u0016CXmY;u_JlU\r\u001e:jGN$\u0015n\u001d;sS\n,H/[8ogR!\u00111\u001aB\u0010\u0011\u001d\ty/\ba\u0001\u0003\u0003\f1\u0006Z3tKJL\u0017\r\\5{K\u0016CXmY;u_J\u0004V-Y6NKR\u0014\u0018nY:ESN$(/\u001b2vi&|gn\u001d\u000b\u0005\u0003;\u0014)\u0003C\u0004\u0002pz\u0001\r!a5\u0002'\u0011,7/\u001a:jC2L'0\u001a+bg.$\u0015\r^1\u0015\u0007\u001d\u0014Y\u0003\u0003\u0004\u0002p~\u0001\rAY\u0001\u0017I\u0016\u001cXM]5bY&TX\rV1tW6+GO]5dgR\u0019\u0001O!\r\t\r\u0005=\b\u00051\u0001l\u0003]!Wm]3sS\u0006d\u0017N_3J]B,H/T3ue&\u001c7\u000fF\u0002z\u0005oAa!a<\"\u0001\u0004!\u0018\u0001\u00073fg\u0016\u0014\u0018.\u00197ju\u0016|U\u000f\u001e9vi6+GO]5dgR!\u0011Q\u0001B\u001f\u0011\u0019\tyO\ta\u0001{\u0006iB-Z:fe&\fG.\u001b>f'\",hM\u001a7f%\u0016\fG-T3ue&\u001c7\u000f\u0006\u0003\u0002\u0018\t\r\u0003bBAxG\u0001\u0007\u0011QB\u0001\"I\u0016\u001cXM]5bY&TXm\u00155vM\u001adW\rU;tQJ+\u0017\rZ'fiJL7m\u001d\u000b\u0005\u0003S\u0011I\u0005C\u0004\u0002p\u0012\u0002\r!a\b\u0002=\u0011,7/\u001a:jC2L'0Z*ik\u001a4G.Z,sSR,W*\u001a;sS\u000e\u001cH\u0003BA\u001e\u0005\u001fBq!a<&\u0001\u0004\t\t\u0004"
)
public class StageDataWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final StageDataWrapper input) {
      StoreTypes.StageDataWrapper.Builder builder = StoreTypes.StageDataWrapper.newBuilder();
      builder.setInfo(this.serializeStageData(input.info()));
      input.jobIds().foreach((id) -> $anonfun$serialize$1(builder, BoxesRunTime.unboxToInt(id)));
      input.locality().foreach((entry) -> builder.putLocality((String)entry._1(), entry._2$mcJ$sp()));
      return builder.build().toByteArray();
   }

   private StoreTypes.StageData serializeStageData(final StageData stageData) {
      StoreTypes.StageData.Builder stageDataBuilder = StoreTypes.StageData.newBuilder();
      stageDataBuilder.setStatus(StageStatusSerializer$.MODULE$.serialize(stageData.status())).setStageId((long)stageData.stageId()).setAttemptId(stageData.attemptId()).setNumTasks(stageData.numTasks()).setNumActiveTasks(stageData.numActiveTasks()).setNumCompleteTasks(stageData.numCompleteTasks()).setNumFailedTasks(stageData.numFailedTasks()).setNumKilledTasks(stageData.numKilledTasks()).setNumCompletedIndices(stageData.numCompletedIndices()).setExecutorDeserializeTime(stageData.executorDeserializeTime()).setExecutorDeserializeCpuTime(stageData.executorDeserializeCpuTime()).setExecutorRunTime(stageData.executorRunTime()).setExecutorCpuTime(stageData.executorCpuTime()).setResultSize(stageData.resultSize()).setJvmGcTime(stageData.jvmGcTime()).setResultSerializationTime(stageData.resultSerializationTime()).setMemoryBytesSpilled(stageData.memoryBytesSpilled()).setDiskBytesSpilled(stageData.diskBytesSpilled()).setPeakExecutionMemory(stageData.peakExecutionMemory()).setInputBytes(stageData.inputBytes()).setInputRecords(stageData.inputRecords()).setOutputBytes(stageData.outputBytes()).setOutputRecords(stageData.outputRecords()).setShuffleRemoteBlocksFetched(stageData.shuffleRemoteBlocksFetched()).setShuffleLocalBlocksFetched(stageData.shuffleLocalBlocksFetched()).setShuffleFetchWaitTime(stageData.shuffleFetchWaitTime()).setShuffleRemoteBytesRead(stageData.shuffleRemoteBytesRead()).setShuffleRemoteBytesReadToDisk(stageData.shuffleRemoteBytesReadToDisk()).setShuffleLocalBytesRead(stageData.shuffleLocalBytesRead()).setShuffleReadBytes(stageData.shuffleReadBytes()).setShuffleReadRecords(stageData.shuffleReadRecords()).setShuffleCorruptMergedBlockChunks(stageData.shuffleCorruptMergedBlockChunks()).setShuffleMergedFetchFallbackCount(stageData.shuffleMergedFetchFallbackCount()).setShuffleMergedRemoteBlocksFetched(stageData.shuffleMergedRemoteBlocksFetched()).setShuffleMergedLocalBlocksFetched(stageData.shuffleMergedLocalBlocksFetched()).setShuffleMergedRemoteChunksFetched(stageData.shuffleMergedRemoteChunksFetched()).setShuffleMergedLocalChunksFetched(stageData.shuffleMergedLocalChunksFetched()).setShuffleMergedRemoteBytesRead(stageData.shuffleMergedRemoteBytesRead()).setShuffleMergedLocalBytesRead(stageData.shuffleMergedLocalBytesRead()).setShuffleRemoteReqsDuration(stageData.shuffleRemoteReqsDuration()).setShuffleMergedRemoteReqsDuration(stageData.shuffleMergedRemoteReqsDuration()).setShuffleWriteBytes(stageData.shuffleWriteBytes()).setShuffleWriteTime(stageData.shuffleWriteTime()).setShuffleWriteRecords(stageData.shuffleWriteRecords()).setResourceProfileId(stageData.resourceProfileId()).setIsShufflePushEnabled(stageData.isShufflePushEnabled()).setShuffleMergersCount(stageData.shuffleMergersCount());
      Utils$.MODULE$.setStringField(stageData.name(), (value) -> stageDataBuilder.setName(value));
      Utils$.MODULE$.setStringField(stageData.details(), (value) -> stageDataBuilder.setDetails(value));
      Utils$.MODULE$.setStringField(stageData.schedulingPool(), (value) -> stageDataBuilder.setSchedulingPool(value));
      stageData.submissionTime().foreach((d) -> stageDataBuilder.setSubmissionTime(d.getTime()));
      stageData.firstTaskLaunchedTime().foreach((d) -> stageDataBuilder.setFirstTaskLaunchedTime(d.getTime()));
      stageData.completionTime().foreach((d) -> stageDataBuilder.setCompletionTime(d.getTime()));
      stageData.failureReason().foreach((fr) -> stageDataBuilder.setFailureReason(fr));
      stageData.description().foreach((d) -> stageDataBuilder.setDescription(d));
      stageData.rddIds().foreach((id) -> $anonfun$serializeStageData$9(stageDataBuilder, BoxesRunTime.unboxToInt(id)));
      stageData.accumulatorUpdates().foreach((update) -> stageDataBuilder.addAccumulatorUpdates(AccumulableInfoSerializer$.MODULE$.serialize(update)));
      stageData.tasks().foreach((t) -> {
         $anonfun$serializeStageData$11(this, stageDataBuilder, t);
         return BoxedUnit.UNIT;
      });
      stageData.executorSummary().foreach((es) -> {
         $anonfun$serializeStageData$13(stageDataBuilder, es);
         return BoxedUnit.UNIT;
      });
      stageData.speculationSummary().foreach((ss) -> stageDataBuilder.setSpeculationSummary(this.serializeSpeculationStageSummary(ss)));
      stageData.killedTasksSummary().foreach((entry) -> stageDataBuilder.putKilledTasksSummary((String)entry._1(), entry._2$mcI$sp()));
      stageData.peakExecutorMetrics().foreach((pem) -> stageDataBuilder.setPeakExecutorMetrics(ExecutorMetricsSerializer$.MODULE$.serialize(pem)));
      stageData.taskMetricsDistributions().foreach((tmd) -> stageDataBuilder.setTaskMetricsDistributions(this.serializeTaskMetricDistributions(tmd)));
      stageData.executorMetricsDistributions().foreach((emd) -> stageDataBuilder.setExecutorMetricsDistributions(this.serializeExecutorMetricsDistributions(emd)));
      return stageDataBuilder.build();
   }

   private StoreTypes.TaskData serializeTaskData(final TaskData t) {
      StoreTypes.TaskData.Builder taskDataBuilder = StoreTypes.TaskData.newBuilder();
      taskDataBuilder.setTaskId(t.taskId()).setIndex(t.index()).setAttempt(t.attempt()).setPartitionId(t.partitionId()).setLaunchTime(t.launchTime().getTime()).setSpeculative(t.speculative()).setSchedulerDelay(t.schedulerDelay()).setGettingResultTime(t.gettingResultTime());
      Utils$.MODULE$.setStringField(t.executorId(), (value) -> taskDataBuilder.setExecutorId(value));
      Utils$.MODULE$.setStringField(t.host(), (value) -> taskDataBuilder.setHost(value));
      Utils$.MODULE$.setStringField(t.status(), (value) -> taskDataBuilder.setStatus(value));
      Utils$.MODULE$.setStringField(t.taskLocality(), (value) -> taskDataBuilder.setTaskLocality(value));
      t.resultFetchStart().foreach((rfs) -> taskDataBuilder.setResultFetchStart(rfs.getTime()));
      t.duration().foreach((d) -> $anonfun$serializeTaskData$6(taskDataBuilder, BoxesRunTime.unboxToLong(d)));
      t.accumulatorUpdates().foreach((update) -> taskDataBuilder.addAccumulatorUpdates(AccumulableInfoSerializer$.MODULE$.serialize(update)));
      t.errorMessage().foreach((em) -> taskDataBuilder.setErrorMessage(em));
      t.taskMetrics().foreach((tm) -> taskDataBuilder.setTaskMetrics(this.serializeTaskMetrics(tm)));
      t.executorLogs().foreach((entry) -> taskDataBuilder.putExecutorLogs((String)entry._1(), (String)entry._2()));
      return taskDataBuilder.build();
   }

   private StoreTypes.TaskMetrics serializeTaskMetrics(final TaskMetrics tm) {
      StoreTypes.TaskMetrics.Builder taskMetricsBuilder = StoreTypes.TaskMetrics.newBuilder();
      taskMetricsBuilder.setExecutorDeserializeTime(tm.executorDeserializeTime()).setExecutorDeserializeCpuTime(tm.executorDeserializeCpuTime()).setExecutorRunTime(tm.executorRunTime()).setExecutorCpuTime(tm.executorCpuTime()).setResultSize(tm.resultSize()).setJvmGcTime(tm.jvmGcTime()).setResultSerializationTime(tm.resultSerializationTime()).setMemoryBytesSpilled(tm.memoryBytesSpilled()).setDiskBytesSpilled(tm.diskBytesSpilled()).setPeakExecutionMemory(tm.peakExecutionMemory()).setInputMetrics(this.serializeInputMetrics(tm.inputMetrics())).setOutputMetrics(this.serializeOutputMetrics(tm.outputMetrics())).setShuffleReadMetrics(this.serializeShuffleReadMetrics(tm.shuffleReadMetrics())).setShuffleWriteMetrics(this.serializeShuffleWriteMetrics(tm.shuffleWriteMetrics()));
      return taskMetricsBuilder.build();
   }

   private StoreTypes.InputMetrics serializeInputMetrics(final InputMetrics im) {
      return StoreTypes.InputMetrics.newBuilder().setBytesRead(im.bytesRead()).setRecordsRead(im.recordsRead()).build();
   }

   private StoreTypes.OutputMetrics serializeOutputMetrics(final OutputMetrics om) {
      return StoreTypes.OutputMetrics.newBuilder().setBytesWritten(om.bytesWritten()).setRecordsWritten(om.recordsWritten()).build();
   }

   private StoreTypes.ShuffleReadMetrics serializeShuffleReadMetrics(final ShuffleReadMetrics srm) {
      return StoreTypes.ShuffleReadMetrics.newBuilder().setRemoteBlocksFetched(srm.remoteBlocksFetched()).setLocalBlocksFetched(srm.localBlocksFetched()).setFetchWaitTime(srm.fetchWaitTime()).setRemoteBytesRead(srm.remoteBytesRead()).setRemoteBytesReadToDisk(srm.remoteBytesReadToDisk()).setLocalBytesRead(srm.localBytesRead()).setRecordsRead(srm.recordsRead()).setRemoteReqsDuration(srm.remoteReqsDuration()).setShufflePushReadMetrics(this.serializeShufflePushReadMetrics(srm.shufflePushReadMetrics())).build();
   }

   private StoreTypes.ShufflePushReadMetrics serializeShufflePushReadMetrics(final ShufflePushReadMetrics sprm) {
      return StoreTypes.ShufflePushReadMetrics.newBuilder().setCorruptMergedBlockChunks(sprm.corruptMergedBlockChunks()).setMergedFetchFallbackCount(sprm.mergedFetchFallbackCount()).setRemoteMergedBlocksFetched(sprm.remoteMergedBlocksFetched()).setLocalMergedBlocksFetched(sprm.localMergedBlocksFetched()).setRemoteMergedChunksFetched(sprm.remoteMergedChunksFetched()).setLocalMergedChunksFetched(sprm.localMergedChunksFetched()).setRemoteMergedBytesRead(sprm.remoteMergedBytesRead()).setLocalMergedBytesRead(sprm.localMergedBytesRead()).setRemoteMergedReqsDuration(sprm.remoteMergedReqsDuration()).build();
   }

   private StoreTypes.ShuffleWriteMetrics serializeShuffleWriteMetrics(final ShuffleWriteMetrics swm) {
      return StoreTypes.ShuffleWriteMetrics.newBuilder().setBytesWritten(swm.bytesWritten()).setWriteTime(swm.writeTime()).setRecordsWritten(swm.recordsWritten()).build();
   }

   private StoreTypes.SpeculationStageSummary serializeSpeculationStageSummary(final SpeculationStageSummary sss) {
      return StoreTypes.SpeculationStageSummary.newBuilder().setNumTasks(sss.numTasks()).setNumActiveTasks(sss.numActiveTasks()).setNumCompletedTasks(sss.numCompletedTasks()).setNumFailedTasks(sss.numFailedTasks()).setNumKilledTasks(sss.numKilledTasks()).build();
   }

   private StoreTypes.TaskMetricDistributions serializeTaskMetricDistributions(final TaskMetricDistributions tmd) {
      StoreTypes.TaskMetricDistributions.Builder builder = StoreTypes.TaskMetricDistributions.newBuilder();
      tmd.quantiles().foreach((q) -> $anonfun$serializeTaskMetricDistributions$1(builder, BoxesRunTime.unboxToDouble(q)));
      tmd.duration().foreach((d) -> $anonfun$serializeTaskMetricDistributions$2(builder, BoxesRunTime.unboxToDouble(d)));
      tmd.executorDeserializeTime().foreach((edt) -> $anonfun$serializeTaskMetricDistributions$3(builder, BoxesRunTime.unboxToDouble(edt)));
      tmd.executorDeserializeCpuTime().foreach((edct) -> $anonfun$serializeTaskMetricDistributions$4(builder, BoxesRunTime.unboxToDouble(edct)));
      tmd.executorRunTime().foreach((ert) -> $anonfun$serializeTaskMetricDistributions$5(builder, BoxesRunTime.unboxToDouble(ert)));
      tmd.executorCpuTime().foreach((ect) -> $anonfun$serializeTaskMetricDistributions$6(builder, BoxesRunTime.unboxToDouble(ect)));
      tmd.resultSize().foreach((rs) -> $anonfun$serializeTaskMetricDistributions$7(builder, BoxesRunTime.unboxToDouble(rs)));
      tmd.jvmGcTime().foreach((jgt) -> $anonfun$serializeTaskMetricDistributions$8(builder, BoxesRunTime.unboxToDouble(jgt)));
      tmd.resultSerializationTime().foreach((rst) -> $anonfun$serializeTaskMetricDistributions$9(builder, BoxesRunTime.unboxToDouble(rst)));
      tmd.gettingResultTime().foreach((grt) -> $anonfun$serializeTaskMetricDistributions$10(builder, BoxesRunTime.unboxToDouble(grt)));
      tmd.schedulerDelay().foreach((sd) -> $anonfun$serializeTaskMetricDistributions$11(builder, BoxesRunTime.unboxToDouble(sd)));
      tmd.peakExecutionMemory().foreach((pem) -> $anonfun$serializeTaskMetricDistributions$12(builder, BoxesRunTime.unboxToDouble(pem)));
      tmd.memoryBytesSpilled().foreach((mbs) -> $anonfun$serializeTaskMetricDistributions$13(builder, BoxesRunTime.unboxToDouble(mbs)));
      tmd.diskBytesSpilled().foreach((dbs) -> $anonfun$serializeTaskMetricDistributions$14(builder, BoxesRunTime.unboxToDouble(dbs)));
      return builder.setInputMetrics(this.serializeInputMetricDistributions(tmd.inputMetrics())).setOutputMetrics(this.serializeOutputMetricDistributions(tmd.outputMetrics())).setShuffleReadMetrics(this.serializeShuffleReadMetricDistributions(tmd.shuffleReadMetrics())).setShuffleWriteMetrics(this.serializeShuffleWriteMetricDistributions(tmd.shuffleWriteMetrics())).build();
   }

   private StoreTypes.InputMetricDistributions serializeInputMetricDistributions(final InputMetricDistributions imd) {
      StoreTypes.InputMetricDistributions.Builder builder = StoreTypes.InputMetricDistributions.newBuilder();
      imd.bytesRead().foreach((br) -> $anonfun$serializeInputMetricDistributions$1(builder, BoxesRunTime.unboxToDouble(br)));
      imd.recordsRead().foreach((rr) -> $anonfun$serializeInputMetricDistributions$2(builder, BoxesRunTime.unboxToDouble(rr)));
      return builder.build();
   }

   private StoreTypes.OutputMetricDistributions serializeOutputMetricDistributions(final OutputMetricDistributions omd) {
      StoreTypes.OutputMetricDistributions.Builder builder = StoreTypes.OutputMetricDistributions.newBuilder();
      omd.bytesWritten().foreach((bw) -> $anonfun$serializeOutputMetricDistributions$1(builder, BoxesRunTime.unboxToDouble(bw)));
      omd.recordsWritten().foreach((rw) -> $anonfun$serializeOutputMetricDistributions$2(builder, BoxesRunTime.unboxToDouble(rw)));
      return builder.build();
   }

   private StoreTypes.ShuffleReadMetricDistributions serializeShuffleReadMetricDistributions(final ShuffleReadMetricDistributions srmd) {
      StoreTypes.ShuffleReadMetricDistributions.Builder builder = StoreTypes.ShuffleReadMetricDistributions.newBuilder();
      srmd.readBytes().foreach((rb) -> $anonfun$serializeShuffleReadMetricDistributions$1(builder, BoxesRunTime.unboxToDouble(rb)));
      srmd.readRecords().foreach((rr) -> $anonfun$serializeShuffleReadMetricDistributions$2(builder, BoxesRunTime.unboxToDouble(rr)));
      srmd.remoteBlocksFetched().foreach((rbf) -> $anonfun$serializeShuffleReadMetricDistributions$3(builder, BoxesRunTime.unboxToDouble(rbf)));
      srmd.localBlocksFetched().foreach((lbf) -> $anonfun$serializeShuffleReadMetricDistributions$4(builder, BoxesRunTime.unboxToDouble(lbf)));
      srmd.fetchWaitTime().foreach((fwt) -> $anonfun$serializeShuffleReadMetricDistributions$5(builder, BoxesRunTime.unboxToDouble(fwt)));
      srmd.remoteBytesRead().foreach((rbr) -> $anonfun$serializeShuffleReadMetricDistributions$6(builder, BoxesRunTime.unboxToDouble(rbr)));
      srmd.remoteBytesReadToDisk().foreach((rbrtd) -> $anonfun$serializeShuffleReadMetricDistributions$7(builder, BoxesRunTime.unboxToDouble(rbrtd)));
      srmd.totalBlocksFetched().foreach((tbf) -> $anonfun$serializeShuffleReadMetricDistributions$8(builder, BoxesRunTime.unboxToDouble(tbf)));
      srmd.remoteReqsDuration().foreach((rrd) -> $anonfun$serializeShuffleReadMetricDistributions$9(builder, BoxesRunTime.unboxToDouble(rrd)));
      builder.setShufflePushReadMetricsDist(this.serializeShufflePushReadMetricDistributions(srmd.shufflePushReadMetricsDist()));
      return builder.build();
   }

   private StoreTypes.ShufflePushReadMetricDistributions serializeShufflePushReadMetricDistributions(final ShufflePushReadMetricDistributions sprmd) {
      StoreTypes.ShufflePushReadMetricDistributions.Builder builder = StoreTypes.ShufflePushReadMetricDistributions.newBuilder();
      sprmd.corruptMergedBlockChunks().foreach((cmbc) -> $anonfun$serializeShufflePushReadMetricDistributions$1(builder, BoxesRunTime.unboxToDouble(cmbc)));
      sprmd.mergedFetchFallbackCount().foreach((mffc) -> $anonfun$serializeShufflePushReadMetricDistributions$2(builder, BoxesRunTime.unboxToDouble(mffc)));
      sprmd.remoteMergedBlocksFetched().foreach((rmbf) -> $anonfun$serializeShufflePushReadMetricDistributions$3(builder, BoxesRunTime.unboxToDouble(rmbf)));
      sprmd.localMergedBlocksFetched().foreach((lmbf) -> $anonfun$serializeShufflePushReadMetricDistributions$4(builder, BoxesRunTime.unboxToDouble(lmbf)));
      sprmd.remoteMergedChunksFetched().foreach((rmcf) -> $anonfun$serializeShufflePushReadMetricDistributions$5(builder, BoxesRunTime.unboxToDouble(rmcf)));
      sprmd.localMergedChunksFetched().foreach((lmcf) -> $anonfun$serializeShufflePushReadMetricDistributions$6(builder, BoxesRunTime.unboxToDouble(lmcf)));
      sprmd.remoteMergedBytesRead().foreach((rmbr) -> $anonfun$serializeShufflePushReadMetricDistributions$7(builder, BoxesRunTime.unboxToDouble(rmbr)));
      sprmd.localMergedBytesRead().foreach((lmbr) -> $anonfun$serializeShufflePushReadMetricDistributions$8(builder, BoxesRunTime.unboxToDouble(lmbr)));
      sprmd.remoteMergedReqsDuration().foreach((rmrd) -> $anonfun$serializeShufflePushReadMetricDistributions$9(builder, BoxesRunTime.unboxToDouble(rmrd)));
      return builder.build();
   }

   private StoreTypes.ShuffleWriteMetricDistributions serializeShuffleWriteMetricDistributions(final ShuffleWriteMetricDistributions swmd) {
      StoreTypes.ShuffleWriteMetricDistributions.Builder builder = StoreTypes.ShuffleWriteMetricDistributions.newBuilder();
      swmd.writeBytes().foreach((wb) -> $anonfun$serializeShuffleWriteMetricDistributions$1(builder, BoxesRunTime.unboxToDouble(wb)));
      swmd.writeRecords().foreach((wr) -> $anonfun$serializeShuffleWriteMetricDistributions$2(builder, BoxesRunTime.unboxToDouble(wr)));
      swmd.writeTime().foreach((wt) -> $anonfun$serializeShuffleWriteMetricDistributions$3(builder, BoxesRunTime.unboxToDouble(wt)));
      return builder.build();
   }

   private StoreTypes.ExecutorMetricsDistributions serializeExecutorMetricsDistributions(final ExecutorMetricsDistributions emd) {
      StoreTypes.ExecutorMetricsDistributions.Builder builder = StoreTypes.ExecutorMetricsDistributions.newBuilder();
      emd.quantiles().foreach((q) -> $anonfun$serializeExecutorMetricsDistributions$1(builder, BoxesRunTime.unboxToDouble(q)));
      emd.taskTime().foreach((tt) -> $anonfun$serializeExecutorMetricsDistributions$2(builder, BoxesRunTime.unboxToDouble(tt)));
      emd.failedTasks().foreach((ft) -> $anonfun$serializeExecutorMetricsDistributions$3(builder, BoxesRunTime.unboxToDouble(ft)));
      emd.succeededTasks().foreach((st) -> $anonfun$serializeExecutorMetricsDistributions$4(builder, BoxesRunTime.unboxToDouble(st)));
      emd.killedTasks().foreach((kt) -> $anonfun$serializeExecutorMetricsDistributions$5(builder, BoxesRunTime.unboxToDouble(kt)));
      emd.inputBytes().foreach((ib) -> $anonfun$serializeExecutorMetricsDistributions$6(builder, BoxesRunTime.unboxToDouble(ib)));
      emd.inputRecords().foreach((ir) -> $anonfun$serializeExecutorMetricsDistributions$7(builder, BoxesRunTime.unboxToDouble(ir)));
      emd.outputBytes().foreach((ob) -> $anonfun$serializeExecutorMetricsDistributions$8(builder, BoxesRunTime.unboxToDouble(ob)));
      emd.outputRecords().foreach((or) -> $anonfun$serializeExecutorMetricsDistributions$9(builder, BoxesRunTime.unboxToDouble(or)));
      emd.shuffleRead().foreach((sr) -> $anonfun$serializeExecutorMetricsDistributions$10(builder, BoxesRunTime.unboxToDouble(sr)));
      emd.shuffleReadRecords().foreach((srr) -> $anonfun$serializeExecutorMetricsDistributions$11(builder, BoxesRunTime.unboxToDouble(srr)));
      emd.shuffleWrite().foreach((sw) -> $anonfun$serializeExecutorMetricsDistributions$12(builder, BoxesRunTime.unboxToDouble(sw)));
      emd.shuffleWriteRecords().foreach((swr) -> $anonfun$serializeExecutorMetricsDistributions$13(builder, BoxesRunTime.unboxToDouble(swr)));
      emd.memoryBytesSpilled().foreach((mbs) -> $anonfun$serializeExecutorMetricsDistributions$14(builder, BoxesRunTime.unboxToDouble(mbs)));
      emd.diskBytesSpilled().foreach((dbs) -> $anonfun$serializeExecutorMetricsDistributions$15(builder, BoxesRunTime.unboxToDouble(dbs)));
      builder.setPeakMemoryMetrics(this.serializeExecutorPeakMetricsDistributions(emd.peakMemoryMetrics()));
      return builder.build();
   }

   private StoreTypes.ExecutorPeakMetricsDistributions serializeExecutorPeakMetricsDistributions(final ExecutorPeakMetricsDistributions epmd) {
      StoreTypes.ExecutorPeakMetricsDistributions.Builder builder = StoreTypes.ExecutorPeakMetricsDistributions.newBuilder();
      epmd.quantiles().foreach((q) -> $anonfun$serializeExecutorPeakMetricsDistributions$1(builder, BoxesRunTime.unboxToDouble(q)));
      epmd.executorMetrics().foreach((em) -> builder.addExecutorMetrics(ExecutorMetricsSerializer$.MODULE$.serialize(em)));
      return builder.build();
   }

   public StageDataWrapper deserialize(final byte[] bytes) {
      StoreTypes.StageDataWrapper binary = StoreTypes.StageDataWrapper.parseFrom(bytes);
      StageData info = this.deserializeStageData(binary.getInfo());
      return new StageDataWrapper(info, ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getJobIdsList()).asScala().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$deserialize$1(x$1)))).toSet(), (Map).MODULE$.MapHasAsScala(binary.getLocalityMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()).transform((x$2, v) -> BoxesRunTime.boxToLong($anonfun$deserialize$2(x$2, v))));
   }

   private StageData deserializeStageData(final StoreTypes.StageData binary) {
      StageStatus status = StageStatusSerializer$.MODULE$.deserialize(binary.getStatus());
      Option submissionTime = Utils$.MODULE$.getOptional(binary.hasSubmissionTime(), () -> new Date(binary.getSubmissionTime()));
      Option firstTaskLaunchedTime = Utils$.MODULE$.getOptional(binary.hasFirstTaskLaunchedTime(), () -> new Date(binary.getFirstTaskLaunchedTime()));
      Option completionTime = Utils$.MODULE$.getOptional(binary.hasCompletionTime(), () -> new Date(binary.getCompletionTime()));
      Option failureReason = Utils$.MODULE$.getOptional(binary.hasFailureReason(), () -> binary.getFailureReason());
      Option description = Utils$.MODULE$.getOptional(binary.hasDescription(), () -> binary.getDescription());
      ArrayBuffer accumulatorUpdates = AccumulableInfoSerializer$.MODULE$.deserialize(binary.getAccumulatorUpdatesList());
      Option tasks = (Option)(MapUtils.isNotEmpty(binary.getTasksMap()) ? new Some(.MODULE$.MapHasAsScala(binary.getTasksMap()).asScala().map((entry) -> new Tuple2(BoxesRunTime.boxToLong(scala.Predef..MODULE$.Long2long((Long)entry._1())), this.deserializeTaskData((StoreTypes.TaskData)entry._2()))).toMap(scala..less.colon.less..MODULE$.refl())) : scala.None..MODULE$);
      Option executorSummary = (Option)(MapUtils.isNotEmpty(binary.getExecutorSummaryMap()) ? new Some(.MODULE$.MapHasAsScala(binary.getExecutorSummaryMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()).transform((x$3, v) -> ExecutorStageSummarySerializer$.MODULE$.deserialize(v))) : scala.None..MODULE$);
      Option speculationSummary = Utils$.MODULE$.getOptional(binary.hasSpeculationSummary(), () -> this.deserializeSpeculationStageSummary(binary.getSpeculationSummary()));
      Option peakExecutorMetrics = Utils$.MODULE$.getOptional(binary.hasPeakExecutorMetrics(), () -> ExecutorMetricsSerializer$.MODULE$.deserialize(binary.getPeakExecutorMetrics()));
      Option taskMetricsDistributions = Utils$.MODULE$.getOptional(binary.hasTaskMetricsDistributions(), () -> this.deserializeTaskMetricDistributions(binary.getTaskMetricsDistributions()));
      Option executorMetricsDistributions = Utils$.MODULE$.getOptional(binary.hasExecutorMetricsDistributions(), () -> this.deserializeExecutorMetricsDistributions(binary.getExecutorMetricsDistributions()));
      return new StageData(status, (int)binary.getStageId(), binary.getAttemptId(), binary.getNumTasks(), binary.getNumActiveTasks(), binary.getNumCompleteTasks(), binary.getNumFailedTasks(), binary.getNumKilledTasks(), binary.getNumCompletedIndices(), submissionTime, firstTaskLaunchedTime, completionTime, failureReason, binary.getExecutorDeserializeTime(), binary.getExecutorDeserializeCpuTime(), binary.getExecutorRunTime(), binary.getExecutorCpuTime(), binary.getResultSize(), binary.getJvmGcTime(), binary.getResultSerializationTime(), binary.getMemoryBytesSpilled(), binary.getDiskBytesSpilled(), binary.getPeakExecutionMemory(), binary.getInputBytes(), binary.getInputRecords(), binary.getOutputBytes(), binary.getOutputRecords(), binary.getShuffleRemoteBlocksFetched(), binary.getShuffleLocalBlocksFetched(), binary.getShuffleFetchWaitTime(), binary.getShuffleRemoteBytesRead(), binary.getShuffleRemoteBytesReadToDisk(), binary.getShuffleLocalBytesRead(), binary.getShuffleReadBytes(), binary.getShuffleReadRecords(), binary.getShuffleCorruptMergedBlockChunks(), binary.getShuffleMergedFetchFallbackCount(), binary.getShuffleMergedRemoteBlocksFetched(), binary.getShuffleMergedLocalBlocksFetched(), binary.getShuffleMergedRemoteChunksFetched(), binary.getShuffleMergedLocalChunksFetched(), binary.getShuffleMergedRemoteBytesRead(), binary.getShuffleMergedLocalBytesRead(), binary.getShuffleRemoteReqsDuration(), binary.getShuffleMergedRemoteReqsDuration(), binary.getShuffleWriteBytes(), binary.getShuffleWriteTime(), binary.getShuffleWriteRecords(), Utils$.MODULE$.getStringField(binary.hasName(), () -> binary.getName()), description, Utils$.MODULE$.getStringField(binary.hasDetails(), () -> binary.getDetails()), Utils$.MODULE$.getStringField(binary.hasSchedulingPool(), () -> binary.getSchedulingPool()), (Seq).MODULE$.ListHasAsScala(binary.getRddIdsList()).asScala().map((x$4) -> BoxesRunTime.boxToInteger($anonfun$deserializeStageData$15(x$4))), accumulatorUpdates, tasks, executorSummary, speculationSummary, (Map).MODULE$.MapHasAsScala(binary.getKilledTasksSummaryMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()).transform((x$5, v) -> BoxesRunTime.boxToInteger($anonfun$deserializeStageData$16(x$5, v))), binary.getResourceProfileId(), peakExecutorMetrics, taskMetricsDistributions, executorMetricsDistributions, binary.getIsShufflePushEnabled(), binary.getShuffleMergersCount());
   }

   private SpeculationStageSummary deserializeSpeculationStageSummary(final StoreTypes.SpeculationStageSummary binary) {
      return new SpeculationStageSummary(binary.getNumTasks(), binary.getNumActiveTasks(), binary.getNumCompletedTasks(), binary.getNumFailedTasks(), binary.getNumKilledTasks());
   }

   private TaskMetricDistributions deserializeTaskMetricDistributions(final StoreTypes.TaskMetricDistributions binary) {
      return new TaskMetricDistributions(((IterableOnceOps).MODULE$.ListHasAsScala(binary.getQuantilesList()).asScala().map((x$6) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$1(x$6)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getDurationList()).asScala().map((x$7) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$2(x$7)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getExecutorDeserializeTimeList()).asScala().map((x$8) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$3(x$8)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getExecutorDeserializeCpuTimeList()).asScala().map((x$9) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$4(x$9)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getExecutorRunTimeList()).asScala().map((x$10) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$5(x$10)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getExecutorCpuTimeList()).asScala().map((x$11) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$6(x$11)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getResultSizeList()).asScala().map((x$12) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$7(x$12)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getJvmGcTimeList()).asScala().map((x$13) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$8(x$13)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getResultSerializationTimeList()).asScala().map((x$14) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$9(x$14)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getGettingResultTimeList()).asScala().map((x$15) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$10(x$15)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getSchedulerDelayList()).asScala().map((x$16) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$11(x$16)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getPeakExecutionMemoryList()).asScala().map((x$17) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$12(x$17)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getMemoryBytesSpilledList()).asScala().map((x$18) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$13(x$18)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getDiskBytesSpilledList()).asScala().map((x$19) -> BoxesRunTime.boxToDouble($anonfun$deserializeTaskMetricDistributions$14(x$19)))).toIndexedSeq(), this.deserializeInputMetricDistributions(binary.getInputMetrics()), this.deserializeOutputMetricDistributions(binary.getOutputMetrics()), this.deserializeShuffleReadMetricDistributions(binary.getShuffleReadMetrics()), this.deserializeShuffleWriteMetricDistributions(binary.getShuffleWriteMetrics()));
   }

   private InputMetricDistributions deserializeInputMetricDistributions(final StoreTypes.InputMetricDistributions binary) {
      return new InputMetricDistributions(((IterableOnceOps).MODULE$.ListHasAsScala(binary.getBytesReadList()).asScala().map((x$20) -> BoxesRunTime.boxToDouble($anonfun$deserializeInputMetricDistributions$1(x$20)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRecordsReadList()).asScala().map((x$21) -> BoxesRunTime.boxToDouble($anonfun$deserializeInputMetricDistributions$2(x$21)))).toIndexedSeq());
   }

   private OutputMetricDistributions deserializeOutputMetricDistributions(final StoreTypes.OutputMetricDistributions binary) {
      return new OutputMetricDistributions(((IterableOnceOps).MODULE$.ListHasAsScala(binary.getBytesWrittenList()).asScala().map((x$22) -> BoxesRunTime.boxToDouble($anonfun$deserializeOutputMetricDistributions$1(x$22)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRecordsWrittenList()).asScala().map((x$23) -> BoxesRunTime.boxToDouble($anonfun$deserializeOutputMetricDistributions$2(x$23)))).toIndexedSeq());
   }

   private ShuffleReadMetricDistributions deserializeShuffleReadMetricDistributions(final StoreTypes.ShuffleReadMetricDistributions binary) {
      return new ShuffleReadMetricDistributions(((IterableOnceOps).MODULE$.ListHasAsScala(binary.getReadBytesList()).asScala().map((x$24) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleReadMetricDistributions$1(x$24)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getReadRecordsList()).asScala().map((x$25) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleReadMetricDistributions$2(x$25)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRemoteBlocksFetchedList()).asScala().map((x$26) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleReadMetricDistributions$3(x$26)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getLocalBlocksFetchedList()).asScala().map((x$27) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleReadMetricDistributions$4(x$27)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getFetchWaitTimeList()).asScala().map((x$28) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleReadMetricDistributions$5(x$28)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRemoteBytesReadList()).asScala().map((x$29) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleReadMetricDistributions$6(x$29)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRemoteBytesReadToDiskList()).asScala().map((x$30) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleReadMetricDistributions$7(x$30)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getTotalBlocksFetchedList()).asScala().map((x$31) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleReadMetricDistributions$8(x$31)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRemoteReqsDurationList()).asScala().map((x$32) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleReadMetricDistributions$9(x$32)))).toIndexedSeq(), this.deserializeShufflePushReadMetricsDistributions(binary.getShufflePushReadMetricsDist()));
   }

   private ShufflePushReadMetricDistributions deserializeShufflePushReadMetricsDistributions(final StoreTypes.ShufflePushReadMetricDistributions binary) {
      return new ShufflePushReadMetricDistributions(((IterableOnceOps).MODULE$.ListHasAsScala(binary.getCorruptMergedBlockChunksList()).asScala().map((x$33) -> BoxesRunTime.boxToDouble($anonfun$deserializeShufflePushReadMetricsDistributions$1(x$33)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getMergedFetchFallbackCountList()).asScala().map((x$34) -> BoxesRunTime.boxToDouble($anonfun$deserializeShufflePushReadMetricsDistributions$2(x$34)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRemoteMergedBlocksFetchedList()).asScala().map((x$35) -> BoxesRunTime.boxToDouble($anonfun$deserializeShufflePushReadMetricsDistributions$3(x$35)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getLocalMergedBlocksFetchedList()).asScala().map((x$36) -> BoxesRunTime.boxToDouble($anonfun$deserializeShufflePushReadMetricsDistributions$4(x$36)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRemoteMergedChunksFetchedList()).asScala().map((x$37) -> BoxesRunTime.boxToDouble($anonfun$deserializeShufflePushReadMetricsDistributions$5(x$37)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getLocalMergedChunksFetchedList()).asScala().map((x$38) -> BoxesRunTime.boxToDouble($anonfun$deserializeShufflePushReadMetricsDistributions$6(x$38)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRemoteMergedBytesReadList()).asScala().map((x$39) -> BoxesRunTime.boxToDouble($anonfun$deserializeShufflePushReadMetricsDistributions$7(x$39)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getLocalMergedBytesReadList()).asScala().map((x$40) -> BoxesRunTime.boxToDouble($anonfun$deserializeShufflePushReadMetricsDistributions$8(x$40)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getRemoteMergedReqsDurationList()).asScala().map((x$41) -> BoxesRunTime.boxToDouble($anonfun$deserializeShufflePushReadMetricsDistributions$9(x$41)))).toIndexedSeq());
   }

   private ShuffleWriteMetricDistributions deserializeShuffleWriteMetricDistributions(final StoreTypes.ShuffleWriteMetricDistributions binary) {
      return new ShuffleWriteMetricDistributions(((IterableOnceOps).MODULE$.ListHasAsScala(binary.getWriteBytesList()).asScala().map((x$42) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleWriteMetricDistributions$1(x$42)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getWriteRecordsList()).asScala().map((x$43) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleWriteMetricDistributions$2(x$43)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getWriteTimeList()).asScala().map((x$44) -> BoxesRunTime.boxToDouble($anonfun$deserializeShuffleWriteMetricDistributions$3(x$44)))).toIndexedSeq());
   }

   private ExecutorMetricsDistributions deserializeExecutorMetricsDistributions(final StoreTypes.ExecutorMetricsDistributions binary) {
      return new ExecutorMetricsDistributions(((IterableOnceOps).MODULE$.ListHasAsScala(binary.getQuantilesList()).asScala().map((x$45) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$1(x$45)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getTaskTimeList()).asScala().map((x$46) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$2(x$46)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getFailedTasksList()).asScala().map((x$47) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$3(x$47)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getSucceededTasksList()).asScala().map((x$48) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$4(x$48)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getKilledTasksList()).asScala().map((x$49) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$5(x$49)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getInputBytesList()).asScala().map((x$50) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$6(x$50)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getInputRecordsList()).asScala().map((x$51) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$7(x$51)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getOutputBytesList()).asScala().map((x$52) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$8(x$52)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getOutputRecordsList()).asScala().map((x$53) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$9(x$53)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getShuffleReadList()).asScala().map((x$54) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$10(x$54)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getShuffleReadRecordsList()).asScala().map((x$55) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$11(x$55)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getShuffleWriteList()).asScala().map((x$56) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$12(x$56)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getShuffleWriteRecordsList()).asScala().map((x$57) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$13(x$57)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getMemoryBytesSpilledList()).asScala().map((x$58) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$14(x$58)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getDiskBytesSpilledList()).asScala().map((x$59) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorMetricsDistributions$15(x$59)))).toIndexedSeq(), this.deserializeExecutorPeakMetricsDistributions(binary.getPeakMemoryMetrics()));
   }

   private ExecutorPeakMetricsDistributions deserializeExecutorPeakMetricsDistributions(final StoreTypes.ExecutorPeakMetricsDistributions binary) {
      return new ExecutorPeakMetricsDistributions(((IterableOnceOps).MODULE$.ListHasAsScala(binary.getQuantilesList()).asScala().map((x$60) -> BoxesRunTime.boxToDouble($anonfun$deserializeExecutorPeakMetricsDistributions$1(x$60)))).toIndexedSeq(), ((IterableOnceOps).MODULE$.ListHasAsScala(binary.getExecutorMetricsList()).asScala().map((binaryx) -> ExecutorMetricsSerializer$.MODULE$.deserialize(binaryx))).toIndexedSeq());
   }

   private TaskData deserializeTaskData(final StoreTypes.TaskData binary) {
      Option resultFetchStart = Utils$.MODULE$.getOptional(binary.hasResultFetchStart(), () -> new Date(binary.getResultFetchStart()));
      Option duration = Utils$.MODULE$.getOptional(binary.hasDuration(), (JFunction0.mcJ.sp)() -> binary.getDuration());
      ArrayBuffer accumulatorUpdates = AccumulableInfoSerializer$.MODULE$.deserialize(binary.getAccumulatorUpdatesList());
      Option taskMetrics = Utils$.MODULE$.getOptional(binary.hasTaskMetrics(), () -> this.deserializeTaskMetrics(binary.getTaskMetrics()));
      return new TaskData(binary.getTaskId(), binary.getIndex(), binary.getAttempt(), binary.getPartitionId(), new Date(binary.getLaunchTime()), resultFetchStart, duration, Utils$.MODULE$.getStringField(binary.hasExecutorId(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getExecutorId())), Utils$.MODULE$.getStringField(binary.hasHost(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getHost())), Utils$.MODULE$.getStringField(binary.hasStatus(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getStatus())), Utils$.MODULE$.getStringField(binary.hasTaskLocality(), () -> org.apache.spark.util.Utils$.MODULE$.weakIntern(binary.getTaskLocality())), binary.getSpeculative(), accumulatorUpdates, Utils$.MODULE$.getOptional(binary.hasErrorMessage(), () -> binary.getErrorMessage()), taskMetrics, .MODULE$.MapHasAsScala(binary.getExecutorLogsMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()), binary.getSchedulerDelay(), binary.getGettingResultTime());
   }

   private TaskMetrics deserializeTaskMetrics(final StoreTypes.TaskMetrics binary) {
      return new TaskMetrics(binary.getExecutorDeserializeTime(), binary.getExecutorDeserializeCpuTime(), binary.getExecutorRunTime(), binary.getExecutorCpuTime(), binary.getResultSize(), binary.getJvmGcTime(), binary.getResultSerializationTime(), binary.getMemoryBytesSpilled(), binary.getDiskBytesSpilled(), binary.getPeakExecutionMemory(), this.deserializeInputMetrics(binary.getInputMetrics()), this.deserializeOutputMetrics(binary.getOutputMetrics()), this.deserializeShuffleReadMetrics(binary.getShuffleReadMetrics()), this.deserializeShuffleWriteMetrics(binary.getShuffleWriteMetrics()));
   }

   private InputMetrics deserializeInputMetrics(final StoreTypes.InputMetrics binary) {
      return new InputMetrics(binary.getBytesRead(), binary.getRecordsRead());
   }

   private OutputMetrics deserializeOutputMetrics(final StoreTypes.OutputMetrics binary) {
      return new OutputMetrics(binary.getBytesWritten(), binary.getRecordsWritten());
   }

   private ShuffleReadMetrics deserializeShuffleReadMetrics(final StoreTypes.ShuffleReadMetrics binary) {
      return new ShuffleReadMetrics(binary.getRemoteBlocksFetched(), binary.getLocalBlocksFetched(), binary.getFetchWaitTime(), binary.getRemoteBytesRead(), binary.getRemoteBytesReadToDisk(), binary.getLocalBytesRead(), binary.getRecordsRead(), binary.getRemoteReqsDuration(), this.deserializeShufflePushReadMetrics(binary.getShufflePushReadMetrics()));
   }

   private ShufflePushReadMetrics deserializeShufflePushReadMetrics(final StoreTypes.ShufflePushReadMetrics binary) {
      return new ShufflePushReadMetrics(binary.getCorruptMergedBlockChunks(), binary.getMergedFetchFallbackCount(), binary.getRemoteMergedBlocksFetched(), binary.getLocalMergedBlocksFetched(), binary.getRemoteMergedChunksFetched(), binary.getLocalMergedChunksFetched(), binary.getRemoteMergedBytesRead(), binary.getLocalMergedBytesRead(), binary.getRemoteMergedReqsDuration());
   }

   private ShuffleWriteMetrics deserializeShuffleWriteMetrics(final StoreTypes.ShuffleWriteMetrics binary) {
      return new ShuffleWriteMetrics(binary.getBytesWritten(), binary.getWriteTime(), binary.getRecordsWritten());
   }

   // $FF: synthetic method
   public static final StoreTypes.StageDataWrapper.Builder $anonfun$serialize$1(final StoreTypes.StageDataWrapper.Builder builder$1, final int id) {
      return builder$1.addJobIds((long)id);
   }

   // $FF: synthetic method
   public static final StoreTypes.StageData.Builder $anonfun$serializeStageData$9(final StoreTypes.StageData.Builder stageDataBuilder$1, final int id) {
      return stageDataBuilder$1.addRddIds((long)id);
   }

   // $FF: synthetic method
   public static final void $anonfun$serializeStageData$11(final StageDataWrapperSerializer $this, final StoreTypes.StageData.Builder stageDataBuilder$1, final Map t) {
      t.foreach((entry) -> stageDataBuilder$1.putTasks(entry._1$mcJ$sp(), $this.serializeTaskData((TaskData)entry._2())));
   }

   // $FF: synthetic method
   public static final void $anonfun$serializeStageData$13(final StoreTypes.StageData.Builder stageDataBuilder$1, final Map es) {
      es.foreach((entry) -> stageDataBuilder$1.putExecutorSummary((String)entry._1(), ExecutorStageSummarySerializer$.MODULE$.serialize((ExecutorStageSummary)entry._2())));
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskData.Builder $anonfun$serializeTaskData$6(final StoreTypes.TaskData.Builder taskDataBuilder$1, final long d) {
      return taskDataBuilder$1.setDuration(d);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$1(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double q) {
      return builder$2.addQuantiles(q);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$2(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double d) {
      return builder$2.addDuration(d);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$3(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double edt) {
      return builder$2.addExecutorDeserializeTime(edt);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$4(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double edct) {
      return builder$2.addExecutorDeserializeCpuTime(edct);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$5(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double ert) {
      return builder$2.addExecutorRunTime(ert);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$6(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double ect) {
      return builder$2.addExecutorCpuTime(ect);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$7(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double rs) {
      return builder$2.addResultSize(rs);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$8(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double jgt) {
      return builder$2.addJvmGcTime(jgt);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$9(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double rst) {
      return builder$2.addResultSerializationTime(rst);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$10(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double grt) {
      return builder$2.addGettingResultTime(grt);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$11(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double sd) {
      return builder$2.addSchedulerDelay(sd);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$12(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double pem) {
      return builder$2.addPeakExecutionMemory(pem);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$13(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double mbs) {
      return builder$2.addMemoryBytesSpilled(mbs);
   }

   // $FF: synthetic method
   public static final StoreTypes.TaskMetricDistributions.Builder $anonfun$serializeTaskMetricDistributions$14(final StoreTypes.TaskMetricDistributions.Builder builder$2, final double dbs) {
      return builder$2.addDiskBytesSpilled(dbs);
   }

   // $FF: synthetic method
   public static final StoreTypes.InputMetricDistributions.Builder $anonfun$serializeInputMetricDistributions$1(final StoreTypes.InputMetricDistributions.Builder builder$3, final double br) {
      return builder$3.addBytesRead(br);
   }

   // $FF: synthetic method
   public static final StoreTypes.InputMetricDistributions.Builder $anonfun$serializeInputMetricDistributions$2(final StoreTypes.InputMetricDistributions.Builder builder$3, final double rr) {
      return builder$3.addRecordsRead(rr);
   }

   // $FF: synthetic method
   public static final StoreTypes.OutputMetricDistributions.Builder $anonfun$serializeOutputMetricDistributions$1(final StoreTypes.OutputMetricDistributions.Builder builder$4, final double bw) {
      return builder$4.addBytesWritten(bw);
   }

   // $FF: synthetic method
   public static final StoreTypes.OutputMetricDistributions.Builder $anonfun$serializeOutputMetricDistributions$2(final StoreTypes.OutputMetricDistributions.Builder builder$4, final double rw) {
      return builder$4.addRecordsWritten(rw);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleReadMetricDistributions.Builder $anonfun$serializeShuffleReadMetricDistributions$1(final StoreTypes.ShuffleReadMetricDistributions.Builder builder$5, final double rb) {
      return builder$5.addReadBytes(rb);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleReadMetricDistributions.Builder $anonfun$serializeShuffleReadMetricDistributions$2(final StoreTypes.ShuffleReadMetricDistributions.Builder builder$5, final double rr) {
      return builder$5.addReadRecords(rr);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleReadMetricDistributions.Builder $anonfun$serializeShuffleReadMetricDistributions$3(final StoreTypes.ShuffleReadMetricDistributions.Builder builder$5, final double rbf) {
      return builder$5.addRemoteBlocksFetched(rbf);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleReadMetricDistributions.Builder $anonfun$serializeShuffleReadMetricDistributions$4(final StoreTypes.ShuffleReadMetricDistributions.Builder builder$5, final double lbf) {
      return builder$5.addLocalBlocksFetched(lbf);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleReadMetricDistributions.Builder $anonfun$serializeShuffleReadMetricDistributions$5(final StoreTypes.ShuffleReadMetricDistributions.Builder builder$5, final double fwt) {
      return builder$5.addFetchWaitTime(fwt);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleReadMetricDistributions.Builder $anonfun$serializeShuffleReadMetricDistributions$6(final StoreTypes.ShuffleReadMetricDistributions.Builder builder$5, final double rbr) {
      return builder$5.addRemoteBytesRead(rbr);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleReadMetricDistributions.Builder $anonfun$serializeShuffleReadMetricDistributions$7(final StoreTypes.ShuffleReadMetricDistributions.Builder builder$5, final double rbrtd) {
      return builder$5.addRemoteBytesReadToDisk(rbrtd);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleReadMetricDistributions.Builder $anonfun$serializeShuffleReadMetricDistributions$8(final StoreTypes.ShuffleReadMetricDistributions.Builder builder$5, final double tbf) {
      return builder$5.addTotalBlocksFetched(tbf);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleReadMetricDistributions.Builder $anonfun$serializeShuffleReadMetricDistributions$9(final StoreTypes.ShuffleReadMetricDistributions.Builder builder$5, final double rrd) {
      return builder$5.addRemoteReqsDuration(rrd);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShufflePushReadMetricDistributions.Builder $anonfun$serializeShufflePushReadMetricDistributions$1(final StoreTypes.ShufflePushReadMetricDistributions.Builder builder$6, final double cmbc) {
      return builder$6.addCorruptMergedBlockChunks(cmbc);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShufflePushReadMetricDistributions.Builder $anonfun$serializeShufflePushReadMetricDistributions$2(final StoreTypes.ShufflePushReadMetricDistributions.Builder builder$6, final double mffc) {
      return builder$6.addMergedFetchFallbackCount(mffc);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShufflePushReadMetricDistributions.Builder $anonfun$serializeShufflePushReadMetricDistributions$3(final StoreTypes.ShufflePushReadMetricDistributions.Builder builder$6, final double rmbf) {
      return builder$6.addRemoteMergedBlocksFetched(rmbf);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShufflePushReadMetricDistributions.Builder $anonfun$serializeShufflePushReadMetricDistributions$4(final StoreTypes.ShufflePushReadMetricDistributions.Builder builder$6, final double lmbf) {
      return builder$6.addLocalMergedBlocksFetched(lmbf);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShufflePushReadMetricDistributions.Builder $anonfun$serializeShufflePushReadMetricDistributions$5(final StoreTypes.ShufflePushReadMetricDistributions.Builder builder$6, final double rmcf) {
      return builder$6.addRemoteMergedChunksFetched(rmcf);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShufflePushReadMetricDistributions.Builder $anonfun$serializeShufflePushReadMetricDistributions$6(final StoreTypes.ShufflePushReadMetricDistributions.Builder builder$6, final double lmcf) {
      return builder$6.addLocalMergedChunksFetched(lmcf);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShufflePushReadMetricDistributions.Builder $anonfun$serializeShufflePushReadMetricDistributions$7(final StoreTypes.ShufflePushReadMetricDistributions.Builder builder$6, final double rmbr) {
      return builder$6.addRemoteMergedBytesRead(rmbr);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShufflePushReadMetricDistributions.Builder $anonfun$serializeShufflePushReadMetricDistributions$8(final StoreTypes.ShufflePushReadMetricDistributions.Builder builder$6, final double lmbr) {
      return builder$6.addLocalMergedBytesRead(lmbr);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShufflePushReadMetricDistributions.Builder $anonfun$serializeShufflePushReadMetricDistributions$9(final StoreTypes.ShufflePushReadMetricDistributions.Builder builder$6, final double rmrd) {
      return builder$6.addRemoteMergedReqsDuration(rmrd);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleWriteMetricDistributions.Builder $anonfun$serializeShuffleWriteMetricDistributions$1(final StoreTypes.ShuffleWriteMetricDistributions.Builder builder$7, final double wb) {
      return builder$7.addWriteBytes(wb);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleWriteMetricDistributions.Builder $anonfun$serializeShuffleWriteMetricDistributions$2(final StoreTypes.ShuffleWriteMetricDistributions.Builder builder$7, final double wr) {
      return builder$7.addWriteRecords(wr);
   }

   // $FF: synthetic method
   public static final StoreTypes.ShuffleWriteMetricDistributions.Builder $anonfun$serializeShuffleWriteMetricDistributions$3(final StoreTypes.ShuffleWriteMetricDistributions.Builder builder$7, final double wt) {
      return builder$7.addWriteTime(wt);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$1(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double q) {
      return builder$8.addQuantiles(q);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$2(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double tt) {
      return builder$8.addTaskTime(tt);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$3(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double ft) {
      return builder$8.addFailedTasks(ft);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$4(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double st) {
      return builder$8.addSucceededTasks(st);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$5(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double kt) {
      return builder$8.addKilledTasks(kt);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$6(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double ib) {
      return builder$8.addInputBytes(ib);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$7(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double ir) {
      return builder$8.addInputRecords(ir);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$8(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double ob) {
      return builder$8.addOutputBytes(ob);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$9(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double or) {
      return builder$8.addOutputRecords(or);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$10(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double sr) {
      return builder$8.addShuffleRead(sr);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$11(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double srr) {
      return builder$8.addShuffleReadRecords(srr);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$12(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double sw) {
      return builder$8.addShuffleWrite(sw);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$13(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double swr) {
      return builder$8.addShuffleWriteRecords(swr);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$14(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double mbs) {
      return builder$8.addMemoryBytesSpilled(mbs);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorMetricsDistributions.Builder $anonfun$serializeExecutorMetricsDistributions$15(final StoreTypes.ExecutorMetricsDistributions.Builder builder$8, final double dbs) {
      return builder$8.addDiskBytesSpilled(dbs);
   }

   // $FF: synthetic method
   public static final StoreTypes.ExecutorPeakMetricsDistributions.Builder $anonfun$serializeExecutorPeakMetricsDistributions$1(final StoreTypes.ExecutorPeakMetricsDistributions.Builder builder$9, final double q) {
      return builder$9.addQuantiles(q);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserialize$1(final Long x$1) {
      return (int)scala.Predef..MODULE$.Long2long(x$1);
   }

   // $FF: synthetic method
   public static final long $anonfun$deserialize$2(final String x$2, final Long v) {
      return scala.Predef..MODULE$.Long2long(v);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserializeStageData$15(final Long x$4) {
      return (int)scala.Predef..MODULE$.Long2long(x$4);
   }

   // $FF: synthetic method
   public static final int $anonfun$deserializeStageData$16(final String x$5, final Integer v) {
      return scala.Predef..MODULE$.Integer2int(v);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$1(final Double x$6) {
      return scala.Predef..MODULE$.Double2double(x$6);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$2(final Double x$7) {
      return scala.Predef..MODULE$.Double2double(x$7);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$3(final Double x$8) {
      return scala.Predef..MODULE$.Double2double(x$8);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$4(final Double x$9) {
      return scala.Predef..MODULE$.Double2double(x$9);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$5(final Double x$10) {
      return scala.Predef..MODULE$.Double2double(x$10);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$6(final Double x$11) {
      return scala.Predef..MODULE$.Double2double(x$11);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$7(final Double x$12) {
      return scala.Predef..MODULE$.Double2double(x$12);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$8(final Double x$13) {
      return scala.Predef..MODULE$.Double2double(x$13);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$9(final Double x$14) {
      return scala.Predef..MODULE$.Double2double(x$14);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$10(final Double x$15) {
      return scala.Predef..MODULE$.Double2double(x$15);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$11(final Double x$16) {
      return scala.Predef..MODULE$.Double2double(x$16);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$12(final Double x$17) {
      return scala.Predef..MODULE$.Double2double(x$17);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$13(final Double x$18) {
      return scala.Predef..MODULE$.Double2double(x$18);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeTaskMetricDistributions$14(final Double x$19) {
      return scala.Predef..MODULE$.Double2double(x$19);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeInputMetricDistributions$1(final Double x$20) {
      return scala.Predef..MODULE$.Double2double(x$20);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeInputMetricDistributions$2(final Double x$21) {
      return scala.Predef..MODULE$.Double2double(x$21);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeOutputMetricDistributions$1(final Double x$22) {
      return scala.Predef..MODULE$.Double2double(x$22);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeOutputMetricDistributions$2(final Double x$23) {
      return scala.Predef..MODULE$.Double2double(x$23);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleReadMetricDistributions$1(final Double x$24) {
      return scala.Predef..MODULE$.Double2double(x$24);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleReadMetricDistributions$2(final Double x$25) {
      return scala.Predef..MODULE$.Double2double(x$25);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleReadMetricDistributions$3(final Double x$26) {
      return scala.Predef..MODULE$.Double2double(x$26);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleReadMetricDistributions$4(final Double x$27) {
      return scala.Predef..MODULE$.Double2double(x$27);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleReadMetricDistributions$5(final Double x$28) {
      return scala.Predef..MODULE$.Double2double(x$28);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleReadMetricDistributions$6(final Double x$29) {
      return scala.Predef..MODULE$.Double2double(x$29);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleReadMetricDistributions$7(final Double x$30) {
      return scala.Predef..MODULE$.Double2double(x$30);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleReadMetricDistributions$8(final Double x$31) {
      return scala.Predef..MODULE$.Double2double(x$31);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleReadMetricDistributions$9(final Double x$32) {
      return scala.Predef..MODULE$.Double2double(x$32);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShufflePushReadMetricsDistributions$1(final Double x$33) {
      return scala.Predef..MODULE$.Double2double(x$33);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShufflePushReadMetricsDistributions$2(final Double x$34) {
      return scala.Predef..MODULE$.Double2double(x$34);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShufflePushReadMetricsDistributions$3(final Double x$35) {
      return scala.Predef..MODULE$.Double2double(x$35);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShufflePushReadMetricsDistributions$4(final Double x$36) {
      return scala.Predef..MODULE$.Double2double(x$36);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShufflePushReadMetricsDistributions$5(final Double x$37) {
      return scala.Predef..MODULE$.Double2double(x$37);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShufflePushReadMetricsDistributions$6(final Double x$38) {
      return scala.Predef..MODULE$.Double2double(x$38);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShufflePushReadMetricsDistributions$7(final Double x$39) {
      return scala.Predef..MODULE$.Double2double(x$39);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShufflePushReadMetricsDistributions$8(final Double x$40) {
      return scala.Predef..MODULE$.Double2double(x$40);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShufflePushReadMetricsDistributions$9(final Double x$41) {
      return scala.Predef..MODULE$.Double2double(x$41);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleWriteMetricDistributions$1(final Double x$42) {
      return scala.Predef..MODULE$.Double2double(x$42);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleWriteMetricDistributions$2(final Double x$43) {
      return scala.Predef..MODULE$.Double2double(x$43);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeShuffleWriteMetricDistributions$3(final Double x$44) {
      return scala.Predef..MODULE$.Double2double(x$44);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$1(final Double x$45) {
      return scala.Predef..MODULE$.Double2double(x$45);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$2(final Double x$46) {
      return scala.Predef..MODULE$.Double2double(x$46);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$3(final Double x$47) {
      return scala.Predef..MODULE$.Double2double(x$47);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$4(final Double x$48) {
      return scala.Predef..MODULE$.Double2double(x$48);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$5(final Double x$49) {
      return scala.Predef..MODULE$.Double2double(x$49);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$6(final Double x$50) {
      return scala.Predef..MODULE$.Double2double(x$50);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$7(final Double x$51) {
      return scala.Predef..MODULE$.Double2double(x$51);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$8(final Double x$52) {
      return scala.Predef..MODULE$.Double2double(x$52);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$9(final Double x$53) {
      return scala.Predef..MODULE$.Double2double(x$53);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$10(final Double x$54) {
      return scala.Predef..MODULE$.Double2double(x$54);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$11(final Double x$55) {
      return scala.Predef..MODULE$.Double2double(x$55);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$12(final Double x$56) {
      return scala.Predef..MODULE$.Double2double(x$56);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$13(final Double x$57) {
      return scala.Predef..MODULE$.Double2double(x$57);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$14(final Double x$58) {
      return scala.Predef..MODULE$.Double2double(x$58);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorMetricsDistributions$15(final Double x$59) {
      return scala.Predef..MODULE$.Double2double(x$59);
   }

   // $FF: synthetic method
   public static final double $anonfun$deserializeExecutorPeakMetricsDistributions$1(final Double x$60) {
      return scala.Predef..MODULE$.Double2double(x$60);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
