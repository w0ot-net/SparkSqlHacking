package org.apache.spark.status;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.ExceptionFailure;
import org.apache.spark.JobExecutionStatus;
import org.apache.spark.Resubmitted$;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext$;
import org.apache.spark.Success$;
import org.apache.spark.TaskCommitDenied;
import org.apache.spark.TaskEndReason;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.TaskKilled;
import org.apache.spark.TaskState$;
import org.apache.spark.executor.ExecutorMetrics;
import org.apache.spark.executor.ShuffleReadMetrics;
import org.apache.spark.executor.TaskMetrics$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.Status$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.resource.TaskResourceRequest;
import org.apache.spark.scheduler.JobFailed;
import org.apache.spark.scheduler.JobResult;
import org.apache.spark.scheduler.JobSucceeded$;
import org.apache.spark.scheduler.MiscellaneousProcessDetails;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerApplicationEnd;
import org.apache.spark.scheduler.SparkListenerApplicationStart;
import org.apache.spark.scheduler.SparkListenerBlockManagerAdded;
import org.apache.spark.scheduler.SparkListenerBlockManagerRemoved;
import org.apache.spark.scheduler.SparkListenerBlockUpdated;
import org.apache.spark.scheduler.SparkListenerEnvironmentUpdate;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.scheduler.SparkListenerExecutorAdded;
import org.apache.spark.scheduler.SparkListenerExecutorBlacklisted;
import org.apache.spark.scheduler.SparkListenerExecutorBlacklistedForStage;
import org.apache.spark.scheduler.SparkListenerExecutorExcluded;
import org.apache.spark.scheduler.SparkListenerExecutorExcludedForStage;
import org.apache.spark.scheduler.SparkListenerExecutorMetricsUpdate;
import org.apache.spark.scheduler.SparkListenerExecutorRemoved;
import org.apache.spark.scheduler.SparkListenerExecutorUnblacklisted;
import org.apache.spark.scheduler.SparkListenerExecutorUnexcluded;
import org.apache.spark.scheduler.SparkListenerJobEnd;
import org.apache.spark.scheduler.SparkListenerJobStart;
import org.apache.spark.scheduler.SparkListenerLogStart;
import org.apache.spark.scheduler.SparkListenerMiscellaneousProcessAdded;
import org.apache.spark.scheduler.SparkListenerNodeBlacklisted;
import org.apache.spark.scheduler.SparkListenerNodeBlacklistedForStage;
import org.apache.spark.scheduler.SparkListenerNodeExcluded;
import org.apache.spark.scheduler.SparkListenerNodeExcludedForStage;
import org.apache.spark.scheduler.SparkListenerNodeUnblacklisted;
import org.apache.spark.scheduler.SparkListenerNodeUnexcluded;
import org.apache.spark.scheduler.SparkListenerResourceProfileAdded;
import org.apache.spark.scheduler.SparkListenerStageCompleted;
import org.apache.spark.scheduler.SparkListenerStageExecutorMetrics;
import org.apache.spark.scheduler.SparkListenerStageSubmitted;
import org.apache.spark.scheduler.SparkListenerTaskEnd;
import org.apache.spark.scheduler.SparkListenerTaskGettingResult;
import org.apache.spark.scheduler.SparkListenerTaskStart;
import org.apache.spark.scheduler.SparkListenerUnpersistRDD;
import org.apache.spark.scheduler.StageInfo;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo$;
import org.apache.spark.status.api.v1.ApplicationEnvironmentInfo;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.status.api.v1.ApplicationInfo$;
import org.apache.spark.status.api.v1.RDDDataDistribution;
import org.apache.spark.status.api.v1.ResourceProfileInfo;
import org.apache.spark.status.api.v1.RuntimeInfo;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.StageStatus;
import org.apache.spark.status.api.v1.TaskMetrics;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BroadcastBlockId;
import org.apache.spark.storage.RDDBlockId;
import org.apache.spark.storage.RDDInfo;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.ui.SparkUI$;
import org.apache.spark.ui.scope.RDDOperationCluster;
import org.apache.spark.ui.scope.RDDOperationGraph;
import org.apache.spark.ui.scope.RDDOperationGraph$;
import org.apache.spark.util.kvstore.KVStoreIterator;
import org.apache.spark.util.kvstore.KVStoreView;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.Predef.;
import scala.collection.IndexedSeqOps;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Seq;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d\u0015b!CA\u0019\u0003g\u0001\u0011qGA\"\u0011)\ti\u0006\u0001B\u0001B\u0003%\u0011\u0011\r\u0005\u000b\u0003S\u0002!\u0011!Q\u0001\n\u0005-\u0004BCA:\u0001\t\u0005\t\u0015!\u0003\u0002v!Q\u0011\u0011\u0011\u0001\u0003\u0002\u0003\u0006I!a!\t\u0015\u0005=\u0005A!A!\u0002\u0013\t\t\nC\u0004\u0002\u001a\u0002!\t!a'\t\u0013\u0005%\u0006\u00011A\u0005\n\u0005-\u0006\"CAb\u0001\u0001\u0007I\u0011BAc\u0011!\t\t\u000e\u0001Q!\n\u00055\u0006\"CAj\u0001\u0001\u0007I\u0011BAk\u0011%\t9\u000f\u0001a\u0001\n\u0013\tI\u000f\u0003\u0005\u0002n\u0002\u0001\u000b\u0015BAl\u0011%\ty\u000f\u0001a\u0001\n\u0013\t\t\u0010C\u0005\u0002z\u0002\u0001\r\u0011\"\u0003\u0002|\"A\u0011q \u0001!B\u0013\t\u0019\u0010C\u0005\u0003\u0002\u0001\u0001\r\u0011\"\u0003\u0003\u0004!I!1\u0002\u0001A\u0002\u0013%!Q\u0002\u0005\t\u0005#\u0001\u0001\u0015)\u0003\u0003\u0006!I!1\u0003\u0001C\u0002\u0013%!Q\u0003\u0005\t\u0005/\u0001\u0001\u0015!\u0003\u0002\u0014\"I!\u0011\u0004\u0001C\u0002\u0013%!Q\u0003\u0005\t\u00057\u0001\u0001\u0015!\u0003\u0002\u0014\"I!Q\u0004\u0001C\u0002\u0013%!1\u0001\u0005\t\u0005?\u0001\u0001\u0015!\u0003\u0003\u0006!I!\u0011\u0005\u0001C\u0002\u0013%!1\u0001\u0005\t\u0005G\u0001\u0001\u0015!\u0003\u0003\u0006!I!Q\u0005\u0001C\u0002\u0013%!q\u0005\u0005\t\u0005\u0013\u0002\u0001\u0015!\u0003\u0003*!I!1\n\u0001C\u0002\u0013%!Q\n\u0005\t\u0005K\u0002\u0001\u0015!\u0003\u0003P!Y!q\r\u0001C\u0002\u0013\u0005\u0011q\u0007B5\u0011!\u0011\u0019\b\u0001Q\u0001\n\t-\u0004b\u0003B;\u0001\t\u0007I\u0011AA\u001c\u0005SB\u0001Ba\u001e\u0001A\u0003%!1\u000e\u0005\n\u0005s\u0002!\u0019!C\u0005\u0005wB\u0001B!\"\u0001A\u0003%!Q\u0010\u0005\n\u0005\u000f\u0003!\u0019!C\u0005\u0005\u0013C\u0001Ba%\u0001A\u0003%!1\u0012\u0005\n\u0005+\u0003!\u0019!C\u0005\u0005/C\u0001B!)\u0001A\u0003%!\u0011\u0014\u0005\n\u0005G\u0003!\u0019!C\u0005\u0005KC\u0001Ba,\u0001A\u0003%!q\u0015\u0005\f\u0005c\u0003!\u0019!C\u0001\u0003o\u0011\u0019\f\u0003\u0005\u0003>\u0002\u0001\u000b\u0011\u0002B[\u0011%\u0011y\f\u0001b\u0001\n\u0013\u0011\t\r\u0003\u0005\u0003N\u0002\u0001\u000b\u0011\u0002Bb\u0011%\u0011y\r\u0001a\u0001\n\u0013\u0011\u0019\u0001C\u0005\u0003R\u0002\u0001\r\u0011\"\u0003\u0003T\"A!q\u001b\u0001!B\u0013\u0011)\u0001C\u0005\u0003b\u0002\u0001\r\u0011\"\u0003\u0003\u0016!I!1\u001d\u0001A\u0002\u0013%!Q\u001d\u0005\t\u0005S\u0004\u0001\u0015)\u0003\u0002\u0014\"9!1\u001e\u0001\u0005B\t5\bb\u0002B}\u0001\u0011\u0005#1 \u0005\b\u0007\u000b\u0001A\u0011IB\u0004\u0011\u001d\u0019\t\u0002\u0001C!\u0007'Aqa!\b\u0001\t\u0003\u001ay\u0002C\u0004\u0004*\u0001!\tea\u000b\t\u000f\rU\u0002\u0001\"\u0011\u00048!91\u0011\t\u0001\u0005\n\r\r\u0003bBB%\u0001\u0011\u000531\n\u0005\b\u0007+\u0002A\u0011IB,\u0011\u001d\u0019\t\u0007\u0001C!\u0007GBqa!\u001c\u0001\t\u0003\u001ay\u0007C\u0004\u0004z\u0001!\tea\u001f\t\u000f\r\u0015\u0005\u0001\"\u0011\u0004\b\"91\u0011\u0013\u0001\u0005\n\rM\u0005bBBP\u0001\u0011%1\u0011\u0015\u0005\b\u0007g\u0003A\u0011IB[\u0011\u001d\u0019y\f\u0001C!\u0007\u0003Dqaa3\u0001\t\u0003\u001ai\rC\u0004\u0004X\u0002!\te!7\t\u000f\r\r\b\u0001\"\u0011\u0004f\"91q\u001e\u0001\u0005B\rE\bbBB~\u0001\u0011%1Q \u0005\b\t\u0013\u0001A\u0011\u0002C\u0006\u0011\u001d!)\u0002\u0001C\u0005\t/Aq\u0001\"\u0006\u0001\t\u0013!y\u0002C\u0004\u0005(\u0001!I\u0001\"\u000b\t\u000f\u0011E\u0002\u0001\"\u0011\u00054!9AQ\b\u0001\u0005\n\u0011}\u0002b\u0002C.\u0001\u0011\u0005CQ\f\u0005\b\tO\u0002A\u0011\tC5\u0011\u001d!\u0019\b\u0001C!\tkBq\u0001b \u0001\t\u0003\"\t\tC\u0004\u0005\f\u0002!\t\u0005\"$\t\u000f\u0011]\u0005\u0001\"\u0011\u0005\u001a\"9A1\u0015\u0001\u0005\n\u0011\u0015\u0006b\u0002CW\u0001\u0011\u0005Cq\u0016\u0005\b\ts\u0003A\u0011\tC^\u0011\u001d!)\r\u0001C!\t\u000fDq\u0001\"5\u0001\t\u0003\"\u0019\u000eC\u0004\u0005^\u0002!\t\u0005b8\t\u000f\u0011%\b\u0001\"\u0003\u0005l\"9Qq\u0001\u0001\u0005B\u0015%\u0001bBC\n\u0001\u0011%QQ\u0003\u0005\b\u000bO\u0001A\u0011AC\u0015\u0011\u001d)\u0019\u0005\u0001C\u0005\u000b\u000bBq!b\u0014\u0001\t\u0013)\t\u0006C\u0004\u0006f\u0001!I!b\u001a\t\u000f\u0015=\u0004\u0001\"\u0003\u0006r!9Q\u0011\u0010\u0001\u0005\n\u0015m\u0004bBCE\u0001\u0011%Q1\u0012\u0005\n\u000b3\u0003A\u0011AA\u001c\u000b7Cq!\"-\u0001\t\u0013)\u0019\fC\u0004\u0006@\u0002!I!\"1\t\u000f\u0015]\u0007\u0001\"\u0003\u0006Z\"IQQ\u001d\u0001\u0012\u0002\u0013%Qq\u001d\u0005\b\u000b{\u0004A\u0011BC\u0000\u0011\u001d1)\u0001\u0001C\u0005\r\u000fAqA\"\u0004\u0001\t\u00131y\u0001C\u0004\u0007\u0016\u0001!IAb\u0006\u0007\r\u0019m\u0001\u0001\u0012D\u000f\u0011)\u0019I*\u001dBK\u0002\u0013\u0005!1\u0001\u0005\u000b\rc\t(\u0011#Q\u0001\n\t\u0015\u0001B\u0003D\u001ac\nU\r\u0011\"\u0001\u0003\u0004!QaQG9\u0003\u0012\u0003\u0006IA!\u0002\t\u0015\u0019]\u0012O!f\u0001\n\u0003\u0011)\u0002\u0003\u0006\u0007:E\u0014\t\u0012)A\u0005\u0003'Cq!!'r\t\u00031Y\u0004C\u0005\u0007HE\f\t\u0011\"\u0001\u0007J!Ia\u0011K9\u0012\u0002\u0013\u0005a1\u000b\u0005\n\r/\n\u0018\u0013!C\u0001\r'B\u0011B\"\u0017r#\u0003%\tAb\u0017\t\u0013\u0019}\u0013/!A\u0005B\t\u0005\u0007\"\u0003D1c\u0006\u0005I\u0011\u0001B\u0002\u0011%1\u0019']A\u0001\n\u00031)\u0007C\u0005\u0007pE\f\t\u0011\"\u0011\u0007r!Ia1P9\u0002\u0002\u0013\u0005aQ\u0010\u0005\n\r\u0003\u000b\u0018\u0011!C!\r\u0007C\u0011Bb\"r\u0003\u0003%\tE\"#\t\u0013\u0019-\u0015/!A\u0005B\u00195\u0005\"\u0003DHc\u0006\u0005I\u0011\tDI\u000f%1)\nAA\u0001\u0012\u001319JB\u0005\u0007\u001c\u0001\t\t\u0011#\u0003\u0007\u001a\"A\u0011\u0011TA\b\t\u00031\t\f\u0003\u0006\u0007\f\u0006=\u0011\u0011!C#\r\u001bC!Bb-\u0002\u0010\u0005\u0005I\u0011\u0011D[\u0011)1i,a\u0004\u0002\u0002\u0013\u0005eq\u0018\u0005\b\r\u001b\u0004A\u0011\u0002Dh\u0011\u001d1i\u000e\u0001C\u0005\r?DqAb9\u0001\t\u00131)\u000fC\u0004\u0007j\u0002!IAb;\t\u000f\u0019=\b\u0001\"\u0003\u0007r\"9aq\u001f\u0001\u0005\n\u0019e\bbBD\u0002\u0001\u0011%qQA\u0004\r\u000f#\t\u0019$!A\t\u0002\u0005]r1\u0003\u0004\r\u0003c\t\u0019$!A\t\u0002\u0005]rQ\u0003\u0005\t\u00033\u000bI\u0003\"\u0001\b\u0018!Qq\u0011DA\u0015#\u0003%\tab\u0007\t\u0015\u001d}\u0011\u0011FI\u0001\n\u00039\tCA\tBaB\u001cF/\u0019;vg2K7\u000f^3oKJTA!!\u000e\u00028\u000511\u000f^1ukNTA!!\u000f\u0002<\u0005)1\u000f]1sW*!\u0011QHA \u0003\u0019\t\u0007/Y2iK*\u0011\u0011\u0011I\u0001\u0004_J<7#\u0002\u0001\u0002F\u0005E\u0003\u0003BA$\u0003\u001bj!!!\u0013\u000b\t\u0005-\u0013qG\u0001\ng\u000eDW\rZ;mKJLA!a\u0014\u0002J\ti1\u000b]1sW2K7\u000f^3oKJ\u0004B!a\u0015\u0002Z5\u0011\u0011Q\u000b\u0006\u0005\u0003/\n9$\u0001\u0005j]R,'O\\1m\u0013\u0011\tY&!\u0016\u0003\u000f1{wmZ5oO\u000691N^:u_J,7\u0001\u0001\t\u0005\u0003G\n)'\u0004\u0002\u00024%!\u0011qMA\u001a\u0005Q)E.Z7f]R$&/Y2lS:<7\u000b^8sK\u0006!1m\u001c8g!\u0011\ti'a\u001c\u000e\u0005\u0005]\u0012\u0002BA9\u0003o\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\t1Lg/\u001a\t\u0005\u0003o\ni(\u0004\u0002\u0002z)\u0011\u00111P\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003\u007f\nIHA\u0004C_>dW-\u00198\u0002\u001f\u0005\u0004\bo\u0015;biV\u001c8k\\;sG\u0016\u0004b!a\u001e\u0002\u0006\u0006%\u0015\u0002BAD\u0003s\u0012aa\u00149uS>t\u0007\u0003BA2\u0003\u0017KA!!$\u00024\ty\u0011\t\u001d9Ti\u0006$Xo]*pkJ\u001cW-\u0001\bmCN$X\u000b\u001d3bi\u0016$\u0016.\\3\u0011\r\u0005]\u0014QQAJ!\u0011\t9(!&\n\t\u0005]\u0015\u0011\u0010\u0002\u0005\u0019>tw-\u0001\u0004=S:LGO\u0010\u000b\r\u0003;\u000by*!)\u0002$\u0006\u0015\u0016q\u0015\t\u0004\u0003G\u0002\u0001bBA/\r\u0001\u0007\u0011\u0011\r\u0005\b\u0003S2\u0001\u0019AA6\u0011\u001d\t\u0019H\u0002a\u0001\u0003kB\u0011\"!!\u0007!\u0003\u0005\r!a!\t\u0013\u0005=e\u0001%AA\u0002\u0005E\u0015\u0001D:qCJ\\g+\u001a:tS>tWCAAW!\u0011\ty+!0\u000f\t\u0005E\u0016\u0011\u0018\t\u0005\u0003g\u000bI(\u0004\u0002\u00026*!\u0011qWA0\u0003\u0019a$o\\8u}%!\u00111XA=\u0003\u0019\u0001&/\u001a3fM&!\u0011qXAa\u0005\u0019\u0019FO]5oO*!\u00111XA=\u0003A\u0019\b/\u0019:l-\u0016\u00148/[8o?\u0012*\u0017\u000f\u0006\u0003\u0002H\u00065\u0007\u0003BA<\u0003\u0013LA!a3\u0002z\t!QK\\5u\u0011%\ty\rCA\u0001\u0002\u0004\ti+A\u0002yIE\nQb\u001d9be.4VM]:j_:\u0004\u0013aB1qa&sgm\\\u000b\u0003\u0003/\u0004B!!7\u0002d6\u0011\u00111\u001c\u0006\u0005\u0003;\fy.\u0001\u0002wc)!\u0011\u0011]A\u001a\u0003\r\t\u0007/[\u0005\u0005\u0003K\fYNA\bBaBd\u0017nY1uS>t\u0017J\u001c4p\u0003-\t\u0007\u000f]%oM>|F%Z9\u0015\t\u0005\u001d\u00171\u001e\u0005\n\u0003\u001f\\\u0011\u0011!a\u0001\u0003/\f\u0001\"\u00199q\u0013:4w\u000eI\u0001\u000bCB\u00048+^7nCJLXCAAz!\u0011\t\u0019'!>\n\t\u0005]\u00181\u0007\u0002\u000b\u0003B\u00048+^7nCJL\u0018AD1qaN+X.\\1ss~#S-\u001d\u000b\u0005\u0003\u000f\fi\u0010C\u0005\u0002P:\t\t\u00111\u0001\u0002t\u0006Y\u0011\r\u001d9Tk6l\u0017M]=!\u0003I!WMZ1vYR\u001c\u0005/^:QKJ$\u0016m]6\u0016\u0005\t\u0015\u0001\u0003BA<\u0005\u000fIAA!\u0003\u0002z\t\u0019\u0011J\u001c;\u0002-\u0011,g-Y;mi\u000e\u0003Xo\u001d)feR\u000b7o[0%KF$B!a2\u0003\u0010!I\u0011qZ\t\u0002\u0002\u0003\u0007!QA\u0001\u0014I\u00164\u0017-\u001e7u\u0007B,8\u000fU3s)\u0006\u001c8\u000eI\u0001\u0013Y&4X-\u00169eCR,\u0007+\u001a:j_\u0012t5/\u0006\u0002\u0002\u0014\u0006\u0019B.\u001b<f+B$\u0017\r^3QKJLw\u000e\u001a(tA\u0005AB.\u001b<f+B$\u0017\r^3NS:4E.^:i!\u0016\u0014\u0018n\u001c3\u000231Lg/Z+qI\u0006$X-T5o\r2,8\u000f\u001b)fe&|G\rI\u0001\u0011[\u0006DH+Y:lgB+'o\u0015;bO\u0016\f\u0011#\\1y)\u0006\u001c8n\u001d)feN#\u0018mZ3!\u0003Ei\u0017\r_$sCBD'k\\8u\u001d>$Wm]\u0001\u0013[\u0006DxI]1qQJ{w\u000e\u001e(pI\u0016\u001c\b%\u0001\u0006mSZ,7\u000b^1hKN,\"A!\u000b\u0011\u0011\t-\"\u0011\bB\u001f\u0005\u0007j!A!\f\u000b\t\t=\"\u0011G\u0001\u000bG>t7-\u001e:sK:$(\u0002\u0002B\u001a\u0005k\tA!\u001e;jY*\u0011!qG\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003<\t5\"!E\"p]\u000e,(O]3oi\"\u000b7\u000f['baBA\u0011q\u000fB \u0005\u000b\u0011)!\u0003\u0003\u0003B\u0005e$A\u0002+va2,'\u0007\u0005\u0003\u0002d\t\u0015\u0013\u0002\u0002B$\u0003g\u0011\u0011\u0002T5wKN#\u0018mZ3\u0002\u00171Lg/Z*uC\u001e,7\u000fI\u0001\tY&4XMS8cgV\u0011!q\n\t\t\u0005#\u0012YF!\u0002\u0003`5\u0011!1\u000b\u0006\u0005\u0005+\u00129&A\u0004nkR\f'\r\\3\u000b\t\te\u0013\u0011P\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B/\u0005'\u0012q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0003\u0002d\t\u0005\u0014\u0002\u0002B2\u0003g\u0011q\u0001T5wK*{'-A\u0005mSZ,'j\u001c2tA\u0005iA.\u001b<f\u000bb,7-\u001e;peN,\"Aa\u001b\u0011\u0011\tE#1LAW\u0005[\u0002B!a\u0019\u0003p%!!\u0011OA\u001a\u00051a\u0015N^3Fq\u0016\u001cW\u000f^8s\u00039a\u0017N^3Fq\u0016\u001cW\u000f^8sg\u0002\nQ\u0002Z3bI\u0016CXmY;u_J\u001c\u0018A\u00043fC\u0012,\u00050Z2vi>\u00148\u000fI\u0001\nY&4X\rV1tWN,\"A! \u0011\u0011\tE#1LAJ\u0005\u007f\u0002B!a\u0019\u0003\u0002&!!1QA\u001a\u0005!a\u0015N^3UCN\\\u0017A\u00037jm\u0016$\u0016m]6tA\u0005AA.\u001b<f%\u0012#5/\u0006\u0002\u0003\fBA!\u0011\u000bB.\u0005\u000b\u0011i\t\u0005\u0003\u0002d\t=\u0015\u0002\u0002BI\u0003g\u0011q\u0001T5wKJ#E)A\u0005mSZ,'\u000b\u0012#tA\u0005)\u0001o\\8mgV\u0011!\u0011\u0014\t\t\u0005#\u0012Y&!,\u0003\u001cB!\u00111\rBO\u0013\u0011\u0011y*a\r\u0003\u001bM\u001b\u0007.\u001a3vY\u0016\u0014\bk\\8m\u0003\u0019\u0001xn\u001c7tA\u0005!B.\u001b<f%\u0016\u001cx.\u001e:dKB\u0013xNZ5mKN,\"Aa*\u0011\u0011\tE#1\fB\u0003\u0005S\u0003B!a\u0019\u0003,&!!QVA\u001a\u0005Ma\u0015N^3SKN|WO]2f!J|g-\u001b7f\u0003Ua\u0017N^3SKN|WO]2f!J|g-\u001b7fg\u0002\n\u0001\u0004\\5wK6K7oY3mY\u0006tWm\\;t!J|7-Z:t+\t\u0011)\f\u0005\u0005\u0003R\tm\u0013Q\u0016B\\!\u0011\t\u0019G!/\n\t\tm\u00161\u0007\u0002\u0019\u0019&4X-T5tG\u0016dG.\u00198f_V\u001c\bK]8dKN\u001c\u0018!\u00077jm\u0016l\u0015n]2fY2\fg.Z8vgB\u0013xnY3tg\u0002\nAcU)M?\u0016CViQ+U\u0013>su,\u0013#`\u0017\u0016KVC\u0001Bb!\u0011\u0011)Ma3\u000e\u0005\t\u001d'\u0002\u0002Be\u0005k\tA\u0001\\1oO&!\u0011q\u0018Bd\u0003U\u0019\u0016\u000bT0F1\u0016\u001bU\u000bV%P\u001d~KEiX&F3\u0002\n1#Y2uSZ,W\t_3dkR|'oQ8v]R\fq#Y2uSZ,W\t_3dkR|'oQ8v]R|F%Z9\u0015\t\u0005\u001d'Q\u001b\u0005\n\u0003\u001f\u0004\u0014\u0011!a\u0001\u0005\u000b\tA#Y2uSZ,W\t_3dkR|'oQ8v]R\u0004\u0003fA\u0019\u0003\\B!\u0011q\u000fBo\u0013\u0011\u0011y.!\u001f\u0003\u0011Y|G.\u0019;jY\u0016\fq\u0002\\1ti\u001acWo\u001d5US6,gj]\u0001\u0014Y\u0006\u001cHO\u00127vg\"$\u0016.\\3Og~#S-\u001d\u000b\u0005\u0003\u000f\u00149\u000fC\u0005\u0002PN\n\t\u00111\u0001\u0002\u0014\u0006\u0001B.Y:u\r2,8\u000f\u001b+j[\u0016t5\u000fI\u0001\r_:|E\u000f[3s\u000bZ,g\u000e\u001e\u000b\u0005\u0003\u000f\u0014y\u000fC\u0004\u0003rV\u0002\rAa=\u0002\u000b\u00154XM\u001c;\u0011\t\u0005\u001d#Q_\u0005\u0005\u0005o\fIE\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\u0018AE8o\u0003B\u0004H.[2bi&|gn\u0015;beR$B!a2\u0003~\"9!\u0011\u001f\u001cA\u0002\t}\b\u0003BA$\u0007\u0003IAaa\u0001\u0002J\ti2\u000b]1sW2K7\u000f^3oKJ\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8Ti\u0006\u0014H/\u0001\fp]J+7o\\;sG\u0016\u0004&o\u001c4jY\u0016\fE\rZ3e)\u0011\t9m!\u0003\t\u000f\tEx\u00071\u0001\u0004\fA!\u0011qIB\u0007\u0013\u0011\u0019y!!\u0013\u0003CM\u0003\u0018M]6MSN$XM\\3s%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK\u0006#G-\u001a3\u0002'=tWI\u001c<je>tW.\u001a8u+B$\u0017\r^3\u0015\t\u0005\u001d7Q\u0003\u0005\b\u0005cD\u0004\u0019AB\f!\u0011\t9e!\u0007\n\t\rm\u0011\u0011\n\u0002\u001f'B\f'o\u001b'jgR,g.\u001a:F]ZL'o\u001c8nK:$X\u000b\u001d3bi\u0016\f\u0001c\u001c8BaBd\u0017nY1uS>tWI\u001c3\u0015\t\u0005\u001d7\u0011\u0005\u0005\b\u0005cL\u0004\u0019AB\u0012!\u0011\t9e!\n\n\t\r\u001d\u0012\u0011\n\u0002\u001c'B\f'o\u001b'jgR,g.\u001a:BaBd\u0017nY1uS>tWI\u001c3\u0002\u001f=tW\t_3dkR|'/\u00113eK\u0012$B!a2\u0004.!9!\u0011\u001f\u001eA\u0002\r=\u0002\u0003BA$\u0007cIAaa\r\u0002J\tQ2\u000b]1sW2K7\u000f^3oKJ,\u00050Z2vi>\u0014\u0018\t\u001a3fI\u0006\trN\\#yK\u000e,Ho\u001c:SK6|g/\u001a3\u0015\t\u0005\u001d7\u0011\b\u0005\b\u0005c\\\u0004\u0019AB\u001e!\u0011\t9e!\u0010\n\t\r}\u0012\u0011\n\u0002\u001d'B\f'o\u001b'jgR,g.\u001a:Fq\u0016\u001cW\u000f^8s%\u0016lwN^3e\u0003uI7/\u0012=fGV$xN]!di&4XMR8s\u0019&4Xm\u0015;bO\u0016\u001cH\u0003BA;\u0007\u000bBqaa\u0012=\u0001\u0004\u0011i'\u0001\u0003fq\u0016\u001c\u0017!F8o\u000bb,7-\u001e;pe\nc\u0017mY6mSN$X\r\u001a\u000b\u0005\u0003\u000f\u001ci\u0005C\u0004\u0003rv\u0002\raa\u0014\u0011\t\u0005\u001d3\u0011K\u0005\u0005\u0007'\nIE\u0001\u0011Ta\u0006\u00148\u000eT5ti\u0016tWM]#yK\u000e,Ho\u001c:CY\u0006\u001c7\u000e\\5ti\u0016$\u0017AE8o\u000bb,7-\u001e;pe\u0016C8\r\\;eK\u0012$B!a2\u0004Z!9!\u0011\u001f A\u0002\rm\u0003\u0003BA$\u0007;JAaa\u0018\u0002J\ti2\u000b]1sW2K7\u000f^3oKJ,\u00050Z2vi>\u0014X\t_2mk\u0012,G-A\u000fp]\u0016CXmY;u_J\u0014E.Y2lY&\u001cH/\u001a3G_J\u001cF/Y4f)\u0011\t9m!\u001a\t\u000f\tEx\b1\u0001\u0004hA!\u0011qIB5\u0013\u0011\u0019Y'!\u0013\u0003QM\u0003\u0018M]6MSN$XM\\3s\u000bb,7-\u001e;pe\nc\u0017mY6mSN$X\r\u001a$peN#\u0018mZ3\u00025=tW\t_3dkR|'/\u0012=dYV$W\r\u001a$peN#\u0018mZ3\u0015\t\u0005\u001d7\u0011\u000f\u0005\b\u0005c\u0004\u0005\u0019AB:!\u0011\t9e!\u001e\n\t\r]\u0014\u0011\n\u0002&'B\f'o\u001b'jgR,g.\u001a:Fq\u0016\u001cW\u000f^8s\u000bb\u001cG.\u001e3fI\u001a{'o\u0015;bO\u0016\f\u0011d\u001c8O_\u0012,'\t\\1dW2L7\u000f^3e\r>\u00148\u000b^1hKR!\u0011qYB?\u0011\u001d\u0011\t0\u0011a\u0001\u0007\u007f\u0002B!a\u0012\u0004\u0002&!11QA%\u0005\u0011\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8fe:{G-\u001a\"mC\u000e\\G.[:uK\u00124uN]*uC\u001e,\u0017AF8o\u001d>$W-\u0012=dYV$W\r\u001a$peN#\u0018mZ3\u0015\t\u0005\u001d7\u0011\u0012\u0005\b\u0005c\u0014\u0005\u0019ABF!\u0011\t9e!$\n\t\r=\u0015\u0011\n\u0002\"'B\f'o\u001b'jgR,g.\u001a:O_\u0012,W\t_2mk\u0012,GMR8s'R\fw-Z\u0001\u0013C\u0012$W\t_2mk\u0012,Gm\u0015;bO\u0016$v\u000e\u0006\u0005\u0002H\u000eU5qSBN\u0011\u001d\u00199e\u0011a\u0001\u0005[Bqa!'D\u0001\u0004\u0011)!A\u0004ti\u0006<W-\u00133\t\u000f\ru5\t1\u0001\u0002\u0014\u0006\u0019an\\<\u0002-M,Go\u0015;bO\u0016,\u0005p\u00197vI\u0016$7\u000b^1ukN$\u0002\"a2\u0004$\u000e\u001d6\u0011\u0016\u0005\b\u0007K#\u0005\u0019\u0001B\"\u0003\u0015\u0019H/Y4f\u0011\u001d\u0019i\n\u0012a\u0001\u0003'Cqaa+E\u0001\u0004\u0019i+A\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012\u001c\bCBA<\u0007_\u000bi+\u0003\u0003\u00042\u0006e$A\u0003\u001fsKB,\u0017\r^3e}\u00059rN\\#yK\u000e,Ho\u001c:V]\nd\u0017mY6mSN$X\r\u001a\u000b\u0005\u0003\u000f\u001c9\fC\u0004\u0003r\u0016\u0003\ra!/\u0011\t\u0005\u001d31X\u0005\u0005\u0007{\u000bIE\u0001\u0012Ta\u0006\u00148\u000eT5ti\u0016tWM]#yK\u000e,Ho\u001c:V]\nd\u0017mY6mSN$X\rZ\u0001\u0015_:,\u00050Z2vi>\u0014XK\\3yG2,H-\u001a3\u0015\t\u0005\u001d71\u0019\u0005\b\u0005c4\u0005\u0019ABc!\u0011\t9ea2\n\t\r%\u0017\u0011\n\u0002 'B\f'o\u001b'jgR,g.\u001a:Fq\u0016\u001cW\u000f^8s+:,\u0007p\u00197vI\u0016$\u0017!E8o\u001d>$WM\u00117bG.d\u0017n\u001d;fIR!\u0011qYBh\u0011\u001d\u0011\tp\u0012a\u0001\u0007#\u0004B!a\u0012\u0004T&!1Q[A%\u0005q\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe:{G-\u001a\"mC\u000e\\G.[:uK\u0012\fab\u001c8O_\u0012,W\t_2mk\u0012,G\r\u0006\u0003\u0002H\u000em\u0007b\u0002By\u0011\u0002\u00071Q\u001c\t\u0005\u0003\u000f\u001ay.\u0003\u0003\u0004b\u0006%#!G*qCJ\\G*[:uK:,'OT8eK\u0016C8\r\\;eK\u0012\f1c\u001c8O_\u0012,WK\u001c2mC\u000e\\G.[:uK\u0012$B!a2\u0004h\"9!\u0011_%A\u0002\r%\b\u0003BA$\u0007WLAa!<\u0002J\tq2\u000b]1sW2K7\u000f^3oKJtu\u000eZ3V]\nd\u0017mY6mSN$X\rZ\u0001\u0011_:tu\u000eZ3V]\u0016D8\r\\;eK\u0012$B!a2\u0004t\"9!\u0011\u001f&A\u0002\rU\b\u0003BA$\u0007oLAa!?\u0002J\tY2\u000b]1sW2K7\u000f^3oKJtu\u000eZ3V]\u0016D8\r\\;eK\u0012\f\u0011%\u001e9eCR,gj\u001c3f\u000bb\u001cG.^:j_:\u001cF/\u0019;vg\u001a{'o\u0015;bO\u0016$\u0002\"a2\u0004\u0000\u0012\u0005AQ\u0001\u0005\b\u00073[\u0005\u0019\u0001B\u0003\u0011\u001d!\u0019a\u0013a\u0001\u0005\u000b\tab\u001d;bO\u0016\fE\u000f^3naRLE\rC\u0004\u0005\b-\u0003\r!!,\u0002\r!|7\u000f^%e\u0003u)\b\u000fZ1uK\u0016C8\r\\;tS>t7\u000b^1ukN4uN]*uC\u001e,G\u0003CAd\t\u001b!y\u0001\"\u0005\t\u000f\reE\n1\u0001\u0003\u0006!9A1\u0001'A\u0002\t\u0015\u0001b\u0002C\n\u0019\u0002\u0007\u0011QV\u0001\u0007Kb,7-\u00133\u00023U\u0004H-\u0019;f\u000bb,7-\u0012=dYV\u001c\u0018n\u001c8Ti\u0006$Xo\u001d\u000b\u0007\u0003\u000f$I\u0002b\u0007\t\u000f\u0011MQ\n1\u0001\u0002.\"9AQD'A\u0002\u0005U\u0014\u0001C3yG2,H-\u001a3\u0015\u0011\u0005\u001dG\u0011\u0005C\u0012\tKAqaa\u0012O\u0001\u0004\u0011i\u0007C\u0004\u0005\u001e9\u0003\r!!\u001e\t\u000f\rue\n1\u0001\u0002\u0014\u0006\u0011R\u000f\u001d3bi\u0016tu\u000eZ3Fq\u000edW\u000fZ3e)\u0019\t9\rb\u000b\u00050!9AQF(A\u0002\u00055\u0016\u0001\u00025pgRDq\u0001\"\bP\u0001\u0004\t)(\u0001\u0006p]*{'m\u0015;beR$B!a2\u00056!9!\u0011\u001f)A\u0002\u0011]\u0002\u0003BA$\tsIA\u0001b\u000f\u0002J\t)2\u000b]1sW2K7\u000f^3oKJTuNY*uCJ$\u0018A\u00068foJ#Ei\u00149fe\u0006$\u0018n\u001c8DYV\u001cH/\u001a:\u0015\t\u0011\u0005Cq\t\t\u0005\u0003G\"\u0019%\u0003\u0003\u0005F\u0005M\"A\u0007*E\t>\u0003XM]1uS>t7\t\\;ti\u0016\u0014xK]1qa\u0016\u0014\bb\u0002C%#\u0002\u0007A1J\u0001\bG2,8\u000f^3s!\u0011!i\u0005b\u0016\u000e\u0005\u0011=#\u0002\u0002C)\t'\nQa]2pa\u0016TA\u0001\"\u0016\u00028\u0005\u0011Q/[\u0005\u0005\t3\"yEA\nS\t\u0012{\u0005/\u001a:bi&|gn\u00117vgR,'/\u0001\u0005p]*{'-\u00128e)\u0011\t9\rb\u0018\t\u000f\tE(\u000b1\u0001\u0005bA!\u0011q\tC2\u0013\u0011!)'!\u0013\u0003'M\u0003\u0018M]6MSN$XM\\3s\u0015>\u0014WI\u001c3\u0002!=t7\u000b^1hKN+(-\\5ui\u0016$G\u0003BAd\tWBqA!=T\u0001\u0004!i\u0007\u0005\u0003\u0002H\u0011=\u0014\u0002\u0002C9\u0003\u0013\u00121d\u00159be.d\u0015n\u001d;f]\u0016\u00148\u000b^1hKN+(-\\5ui\u0016$\u0017aC8o)\u0006\u001c8n\u0015;beR$B!a2\u0005x!9!\u0011\u001f+A\u0002\u0011e\u0004\u0003BA$\twJA\u0001\" \u0002J\t12\u000b]1sW2K7\u000f^3oKJ$\u0016m]6Ti\u0006\u0014H/A\np]R\u000b7o[$fiRLgn\u001a*fgVdG\u000f\u0006\u0003\u0002H\u0012\r\u0005b\u0002By+\u0002\u0007AQ\u0011\t\u0005\u0003\u000f\"9)\u0003\u0003\u0005\n\u0006%#AH*qCJ\\G*[:uK:,'\u000fV1tW\u001e+G\u000f^5oOJ+7/\u001e7u\u0003%yg\u000eV1tW\u0016sG\r\u0006\u0003\u0002H\u0012=\u0005b\u0002By-\u0002\u0007A\u0011\u0013\t\u0005\u0003\u000f\"\u0019*\u0003\u0003\u0005\u0016\u0006%#\u0001F*qCJ\\G*[:uK:,'\u000fV1tW\u0016sG-\u0001\tp]N#\u0018mZ3D_6\u0004H.\u001a;fIR!\u0011q\u0019CN\u0011\u001d\u0011\tp\u0016a\u0001\t;\u0003B!a\u0012\u0005 &!A\u0011UA%\u0005m\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8feN#\u0018mZ3D_6\u0004H.\u001a;fI\u00069\"/Z7pm\u0016,\u0005p\u00197vI\u0016$7\u000b^1hK\u001a\u0013x.\u001c\u000b\t\u0003\u000f$9\u000b\"+\u0005,\"91q\t-A\u0002\t5\u0004bBBM1\u0002\u0007!Q\u0001\u0005\b\u0007;C\u0006\u0019AAJ\u0003MygN\u00117pG.l\u0015M\\1hKJ\fE\rZ3e)\u0011\t9\r\"-\t\u000f\tE\u0018\f1\u0001\u00054B!\u0011q\tC[\u0013\u0011!9,!\u0013\u0003=M\u0003\u0018M]6MSN$XM\\3s\u00052|7m['b]\u0006<WM]!eI\u0016$\u0017!F8o\u00052|7m['b]\u0006<WM\u001d*f[>4X\r\u001a\u000b\u0005\u0003\u000f$i\fC\u0004\u0003rj\u0003\r\u0001b0\u0011\t\u0005\u001dC\u0011Y\u0005\u0005\t\u0007\fIE\u0001\u0011Ta\u0006\u00148\u000eT5ti\u0016tWM\u001d\"m_\u000e\\W*\u00198bO\u0016\u0014(+Z7pm\u0016$\u0017AD8o+:\u0004XM]:jgR\u0014F\t\u0012\u000b\u0005\u0003\u000f$I\rC\u0004\u0003rn\u0003\r\u0001b3\u0011\t\u0005\u001dCQZ\u0005\u0005\t\u001f\fIEA\rTa\u0006\u00148\u000eT5ti\u0016tWM]+oa\u0016\u00148/[:u%\u0012#\u0015aF8o\u000bb,7-\u001e;pe6+GO]5dgV\u0003H-\u0019;f)\u0011\t9\r\"6\t\u000f\tEH\f1\u0001\u0005XB!\u0011q\tCm\u0013\u0011!Y.!\u0013\u0003EM\u0003\u0018M]6MSN$XM\\3s\u000bb,7-\u001e;pe6+GO]5dgV\u0003H-\u0019;f\u0003Yygn\u0015;bO\u0016,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001cH\u0003BAd\tCDqA!=^\u0001\u0004!\u0019\u000f\u0005\u0003\u0002H\u0011\u0015\u0018\u0002\u0002Ct\u0003\u0013\u0012\u0011e\u00159be.d\u0015n\u001d;f]\u0016\u00148\u000b^1hK\u0016CXmY;u_JlU\r\u001e:jGN\f1%\u001e9eCR,7\u000b^1hK2+g/\u001a7QK\u0006\\W\t_3dkR|'/T3ue&\u001c7\u000f\u0006\u0007\u0002H\u00125Hq\u001eCy\tk,)\u0001C\u0004\u0004\u001az\u0003\rA!\u0002\t\u000f\u0011\ra\f1\u0001\u0003\u0006!9A1\u001f0A\u0002\u00055\u0016AC3yK\u000e,Ho\u001c:JI\"9Aq\u001f0A\u0002\u0011e\u0018aD3yK\u000e,Ho\u001c:NKR\u0014\u0018nY:\u0011\t\u0011mX\u0011A\u0007\u0003\t{TA\u0001b@\u00028\u0005AQ\r_3dkR|'/\u0003\u0003\u0006\u0004\u0011u(aD#yK\u000e,Ho\u001c:NKR\u0014\u0018nY:\t\u000f\rue\f1\u0001\u0002\u0014\u0006qqN\u001c\"m_\u000e\\W\u000b\u001d3bi\u0016$G\u0003BAd\u000b\u0017AqA!=`\u0001\u0004)i\u0001\u0005\u0003\u0002H\u0015=\u0011\u0002BC\t\u0003\u0013\u0012\u0011d\u00159be.d\u0015n\u001d;f]\u0016\u0014(\t\\8dWV\u0003H-\u0019;fI\u0006)a\r\\;tQR!\u0011qYC\f\u0011\u001d)I\u0002\u0019a\u0001\u000b7\tq\"\u001a8uSRLh\t\\;tQ\u001a+hn\u0019\t\t\u0003o*i\"\"\t\u0002H&!QqDA=\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0003\u0002d\u0015\r\u0012\u0002BC\u0013\u0003g\u0011!\u0002T5wK\u0016sG/\u001b;z\u00031\t7\r^5wKN#\u0018mZ3t)\t)Y\u0003\u0005\u0004\u0006.\u0015]RQ\b\b\u0005\u000b_)\u0019D\u0004\u0003\u00024\u0016E\u0012BAA>\u0013\u0011))$!\u001f\u0002\u000fA\f7m[1hK&!Q\u0011HC\u001e\u0005\r\u0019V-\u001d\u0006\u0005\u000bk\tI\b\u0005\u0003\u0002Z\u0016}\u0012\u0002BC!\u00037\u0014\u0011b\u0015;bO\u0016$\u0015\r^1\u0002\u001f\u0005$G\rR3mi\u0006$vNV1mk\u0016$b!a%\u0006H\u0015-\u0003bBC%E\u0002\u0007\u00111S\u0001\u0004_2$\u0007bBC'E\u0002\u0007\u00111S\u0001\u0006I\u0016dG/Y\u0001\u000fkB$\u0017\r^3S\t\u0012\u0013En\\2l)\u0019\t9-b\u0015\u0006V!9!\u0011_2A\u0002\u00155\u0001bBC,G\u0002\u0007Q\u0011L\u0001\u0006E2|7m\u001b\t\u0005\u000b7*\t'\u0004\u0002\u0006^)!QqLA\u001c\u0003\u001d\u0019Ho\u001c:bO\u0016LA!b\u0019\u0006^\tQ!\u000b\u0012#CY>\u001c7.\u00133\u0002'\u001d,Go\u0014:De\u0016\fG/Z#yK\u000e,Ho\u001c:\u0015\r\t5T\u0011NC6\u0011\u001d!\u0019\u0010\u001aa\u0001\u0003[Cq!\"\u001ce\u0001\u0004\t\u0019*A\u0004bI\u0012$\u0016.\\3\u0002/\u001d,Go\u0014:De\u0016\fG/Z(uQ\u0016\u0014\bK]8dKN\u001cHC\u0002B\\\u000bg*9\bC\u0004\u0006v\u0015\u0004\r!!,\u0002\u0013A\u0014xnY3tg&#\u0007bBC7K\u0002\u0007\u00111S\u0001\u0012kB$\u0017\r^3TiJ,\u0017-\u001c\"m_\u000e\\GCBAd\u000b{*y\bC\u0004\u0003r\u001a\u0004\r!\"\u0004\t\u000f\u0015\u0005e\r1\u0001\u0006\u0004\u000611\u000f\u001e:fC6\u0004B!b\u0017\u0006\u0006&!QqQC/\u00055\u0019FO]3b[\ncwnY6JI\u0006!R\u000f\u001d3bi\u0016\u0014%o\\1eG\u0006\u001cHO\u00117pG.$b!a2\u0006\u000e\u0016=\u0005b\u0002ByO\u0002\u0007QQ\u0002\u0005\b\u000b#;\u0007\u0019ACJ\u0003%\u0011'o\\1eG\u0006\u001cH\u000f\u0005\u0003\u0006\\\u0015U\u0015\u0002BCL\u000b;\u0012\u0001C\u0011:pC\u0012\u001c\u0017m\u001d;CY>\u001c7.\u00133\u00029U\u0004H-\u0019;f\u000bb,7-\u001e;pe6+Wn\u001c:z\t&\u001c8.\u00138g_RQ\u0011qYCO\u000b?+I+\",\t\u000f\r\u001d\u0003\u000e1\u0001\u0003n!9Q\u0011\u00155A\u0002\u0015\r\u0016\u0001D:u_J\fw-\u001a'fm\u0016d\u0007\u0003BC.\u000bKKA!b*\u0006^\ta1\u000b^8sC\u001e,G*\u001a<fY\"9Q1\u00165A\u0002\u0005M\u0015aC7f[>\u0014\u0018\u0010R3mi\u0006Dq!b,i\u0001\u0004\t\u0019*A\u0005eSN\\G)\u001a7uC\u0006\u0001r-\u001a;Pe\u000e\u0013X-\u0019;f'R\fw-\u001a\u000b\u0005\u0005\u0007*)\fC\u0004\u00068&\u0004\r!\"/\u0002\t%tgm\u001c\t\u0005\u0003\u000f*Y,\u0003\u0003\u0006>\u0006%#!C*uC\u001e,\u0017J\u001c4p\u0003IY\u0017\u000e\u001c7fIR\u000b7o[:Tk6l\u0017M]=\u0015\r\u0015\rW\u0011ZCj!!\ty+\"2\u0002.\n\u0015\u0011\u0002BCd\u0003\u0003\u00141!T1q\u0011\u001d)YM\u001ba\u0001\u000b\u001b\faA]3bg>t\u0007\u0003BA7\u000b\u001fLA!\"5\u00028\tiA+Y:l\u000b:$'+Z1t_:Dq!\"6k\u0001\u0004)\u0019-\u0001\u0006pY\u0012\u001cV/\\7bef\fa!\u001e9eCR,G\u0003CAd\u000b7,y.\"9\t\u000f\u0015u7\u000e1\u0001\u0006\"\u00051QM\u001c;jifDqa!(l\u0001\u0004\t\u0019\nC\u0005\u0006d.\u0004\n\u00111\u0001\u0002v\u0005!A.Y:u\u0003A)\b\u000fZ1uK\u0012\"WMZ1vYR$3'\u0006\u0002\u0006j*\"\u0011QOCvW\t)i\u000f\u0005\u0003\u0006p\u0016eXBACy\u0015\u0011)\u00190\">\u0002\u0013Ut7\r[3dW\u0016$'\u0002BC|\u0003s\n!\"\u00198o_R\fG/[8o\u0013\u0011)Y0\"=\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0006nCf\u0014W-\u00169eCR,GCBAd\r\u00031\u0019\u0001C\u0004\u0006^6\u0004\r!\"\t\t\u000f\ruU\u000e1\u0001\u0002\u0014\u0006QA.\u001b<f+B$\u0017\r^3\u0015\r\u0005\u001dg\u0011\u0002D\u0006\u0011\u001d)iN\u001ca\u0001\u000bCAqa!(o\u0001\u0004\t\u0019*\u0001\tdY\u0016\fg.\u001e9Fq\u0016\u001cW\u000f^8sgR!\u0011q\u0019D\t\u0011\u001d1\u0019b\u001ca\u0001\u0003'\u000bQaY8v]R\f1b\u00197fC:,\bOS8cgR!\u0011q\u0019D\r\u0011\u001d1\u0019\u0002\u001da\u0001\u0003'\u00131c\u0015;bO\u0016\u001cu.\u001c9mKRLwN\u001c+j[\u0016\u001cr!\u001dD\u0010\rK1Y\u0003\u0005\u0003\u0002x\u0019\u0005\u0012\u0002\u0002D\u0012\u0003s\u0012a!\u00118z%\u00164\u0007\u0003BA<\rOIAA\"\u000b\u0002z\t9\u0001K]8ek\u000e$\b\u0003BC\u0017\r[IAAb\f\u0006<\ta1+\u001a:jC2L'0\u00192mK\u0006A1\u000f^1hK&#\u0007%A\u0005biR,W\u000e\u001d;JI\u0006Q\u0011\r\u001e;f[B$\u0018\n\u001a\u0011\u0002\u001d\r|W\u000e\u001d7fi&|g\u000eV5nK\u0006y1m\\7qY\u0016$\u0018n\u001c8US6,\u0007\u0005\u0006\u0005\u0007>\u0019\u0005c1\tD#!\r1y$]\u0007\u0002\u0001!91\u0011\u0014=A\u0002\t\u0015\u0001b\u0002D\u001aq\u0002\u0007!Q\u0001\u0005\b\roA\b\u0019AAJ\u0003\u0011\u0019w\u000e]=\u0015\u0011\u0019ub1\nD'\r\u001fB\u0011b!'z!\u0003\u0005\rA!\u0002\t\u0013\u0019M\u0012\u0010%AA\u0002\t\u0015\u0001\"\u0003D\u001csB\u0005\t\u0019AAJ\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"A\"\u0016+\t\t\u0015Q1^\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"A\"\u0018+\t\u0005MU1^\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!aq\rD7!\u0011\t9H\"\u001b\n\t\u0019-\u0014\u0011\u0010\u0002\u0004\u0003:L\b\"CAh\u007f\u0006\u0005\t\u0019\u0001B\u0003\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001D:!\u00191)Hb\u001e\u0007h5\u0011!qK\u0005\u0005\rs\u00129F\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA;\r\u007fB!\"a4\u0002\u0004\u0005\u0005\t\u0019\u0001D4\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\t\rgQ\u0011\u0005\u000b\u0003\u001f\f)!!AA\u0002\t\u0015\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\t\u0015\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\t\r\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002v\u0019M\u0005BCAh\u0003\u0017\t\t\u00111\u0001\u0007h\u0005\u00192\u000b^1hK\u000e{W\u000e\u001d7fi&|g\u000eV5nKB!aqHA\b'\u0019\tyAb'\u0007(BaaQ\u0014DR\u0005\u000b\u0011)!a%\u0007>5\u0011aq\u0014\u0006\u0005\rC\u000bI(A\u0004sk:$\u0018.\\3\n\t\u0019\u0015fq\u0014\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003\u0002DU\r_k!Ab+\u000b\t\u00195&QG\u0001\u0003S>LAAb\f\u0007,R\u0011aqS\u0001\u0006CB\u0004H.\u001f\u000b\t\r{19L\"/\u0007<\"A1\u0011TA\u000b\u0001\u0004\u0011)\u0001\u0003\u0005\u00074\u0005U\u0001\u0019\u0001B\u0003\u0011!19$!\u0006A\u0002\u0005M\u0015aB;oCB\u0004H.\u001f\u000b\u0005\r\u00034I\r\u0005\u0004\u0002x\u0005\u0015e1\u0019\t\u000b\u0003o2)M!\u0002\u0003\u0006\u0005M\u0015\u0002\u0002Dd\u0003s\u0012a\u0001V;qY\u0016\u001c\u0004B\u0003Df\u0003/\t\t\u00111\u0001\u0007>\u0005\u0019\u0001\u0010\n\u0019\u0002=\rdW-\u00198vaN#\u0018mZ3t/&$\b.\u00138NK6|'/_*u_J,G\u0003\u0002Di\r3\u0004b!\"\f\u00068\u0019M\u0007CBA<\r+\u0014)!\u0003\u0003\u0007X\u0006e$!B!se\u0006L\b\u0002\u0003Dn\u00033\u0001\r!a%\u0002\u001b\r|WO\u001c;U_\u0012+G.\u001a;f\u0003Y\u0019G.Z1okB\u001cF/Y4fg&s7JV*u_J,G\u0003\u0002Di\rCD\u0001Bb7\u0002\u001c\u0001\u0007\u00111S\u0001\u000eG2,\u0017M\\;q'R\fw-Z:\u0015\t\u0005\u001dgq\u001d\u0005\t\r'\ti\u00021\u0001\u0002\u0014\u0006a1\r\\3b]V\u0004H+Y:lgR!\u0011q\u0019Dw\u0011!\u0019)+a\bA\u0002\t\r\u0013AF2mK\u0006tW\u000f]\"bG\",G-U;b]RLG.Z:\u0015\t\u0005\u001dg1\u001f\u0005\t\rk\f\t\u00031\u0001\u0007T\u0006A1\u000f^1hK.+\u00170A\fdC2\u001cW\u000f\\1uK:+XNY3s)>\u0014V-\\8wKR1\u00111\u0013D~\r\u007fD\u0001B\"@\u0002$\u0001\u0007\u00111S\u0001\tI\u0006$\u0018mU5{K\"Aq\u0011AA\u0012\u0001\u0004\t\u0019*\u0001\u0007sKR\f\u0017N\\3e'&TX-A\u000ep]6K7oY3mY\u0006tWm\\;t!J|7-Z:t\u0003\u0012$W\r\u001a\u000b\u0005\u0003\u000f<9\u0001\u0003\u0005\b\n\u0005\u0015\u0002\u0019AD\u0006\u0003A\u0001(o\\2fgNLeNZ8Fm\u0016tG\u000f\u0005\u0003\u0002H\u001d5\u0011\u0002BD\b\u0003\u0013\u0012ae\u00159be.d\u0015n\u001d;f]\u0016\u0014X*[:dK2d\u0017M\\3pkN\u0004&o\\2fgN\fE\rZ3e\u0003E\t\u0005\u000f]*uCR,8\u000fT5ti\u0016tWM\u001d\t\u0005\u0003G\nIc\u0005\u0003\u0002*\u0019}ACAD\n\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU\u0011qQ\u0004\u0016\u0005\u0003\u0007+Y/A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u000b\u0003\u000fGQC!!%\u0006l\u0002"
)
public class AppStatusListener extends SparkListener implements Logging {
   private volatile StageCompletionTime$ StageCompletionTime$module;
   private final ElementTrackingStore kvstore;
   private final SparkConf conf;
   private final boolean live;
   private final Option appStatusSource;
   private final Option lastUpdateTime;
   private String sparkVersion;
   private ApplicationInfo appInfo;
   private AppSummary appSummary;
   private int defaultCpusPerTask;
   private final long liveUpdatePeriodNs;
   private final long liveUpdateMinFlushPeriod;
   private final int maxTasksPerStage;
   private final int maxGraphRootNodes;
   private final ConcurrentHashMap liveStages;
   private final HashMap liveJobs;
   private final HashMap liveExecutors;
   private final HashMap deadExecutors;
   private final HashMap liveTasks;
   private final HashMap liveRDDs;
   private final HashMap pools;
   private final HashMap liveResourceProfiles;
   private final HashMap liveMiscellaneousProcess;
   private final String SQL_EXECUTION_ID_KEY;
   private volatile int activeExecutorCount;
   private long lastFlushTimeNs;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Option $lessinit$greater$default$5() {
      return AppStatusListener$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return AppStatusListener$.MODULE$.$lessinit$greater$default$4();
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   private StageCompletionTime$ StageCompletionTime() {
      if (this.StageCompletionTime$module == null) {
         this.StageCompletionTime$lzycompute$1();
      }

      return this.StageCompletionTime$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private String sparkVersion() {
      return this.sparkVersion;
   }

   private void sparkVersion_$eq(final String x$1) {
      this.sparkVersion = x$1;
   }

   private ApplicationInfo appInfo() {
      return this.appInfo;
   }

   private void appInfo_$eq(final ApplicationInfo x$1) {
      this.appInfo = x$1;
   }

   private AppSummary appSummary() {
      return this.appSummary;
   }

   private void appSummary_$eq(final AppSummary x$1) {
      this.appSummary = x$1;
   }

   private int defaultCpusPerTask() {
      return this.defaultCpusPerTask;
   }

   private void defaultCpusPerTask_$eq(final int x$1) {
      this.defaultCpusPerTask = x$1;
   }

   private long liveUpdatePeriodNs() {
      return this.liveUpdatePeriodNs;
   }

   private long liveUpdateMinFlushPeriod() {
      return this.liveUpdateMinFlushPeriod;
   }

   private int maxTasksPerStage() {
      return this.maxTasksPerStage;
   }

   private int maxGraphRootNodes() {
      return this.maxGraphRootNodes;
   }

   private ConcurrentHashMap liveStages() {
      return this.liveStages;
   }

   private HashMap liveJobs() {
      return this.liveJobs;
   }

   public HashMap liveExecutors() {
      return this.liveExecutors;
   }

   public HashMap deadExecutors() {
      return this.deadExecutors;
   }

   private HashMap liveTasks() {
      return this.liveTasks;
   }

   private HashMap liveRDDs() {
      return this.liveRDDs;
   }

   private HashMap pools() {
      return this.pools;
   }

   private HashMap liveResourceProfiles() {
      return this.liveResourceProfiles;
   }

   public HashMap liveMiscellaneousProcess() {
      return this.liveMiscellaneousProcess;
   }

   private String SQL_EXECUTION_ID_KEY() {
      return this.SQL_EXECUTION_ID_KEY;
   }

   private int activeExecutorCount() {
      return this.activeExecutorCount;
   }

   private void activeExecutorCount_$eq(final int x$1) {
      this.activeExecutorCount = x$1;
   }

   private long lastFlushTimeNs() {
      return this.lastFlushTimeNs;
   }

   private void lastFlushTimeNs_$eq(final long x$1) {
      this.lastFlushTimeNs = x$1;
   }

   public void onOtherEvent(final SparkListenerEvent event) {
      if (event instanceof SparkListenerLogStart var4) {
         String version = var4.sparkVersion();
         this.sparkVersion_$eq(version);
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (event instanceof SparkListenerMiscellaneousProcessAdded var6) {
         this.onMiscellaneousProcessAdded(var6);
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void onApplicationStart(final SparkListenerApplicationStart event) {
      .MODULE$.assert(event.appId().isDefined(), () -> "Application without IDs are not supported.");
      ApplicationAttemptInfo attempt = ApplicationAttemptInfo$.MODULE$.apply(event.appAttemptId(), new Date(event.time()), new Date(-1L), new Date(event.time()), -1L, event.sparkUser(), false, this.sparkVersion());
      this.appInfo_$eq(ApplicationInfo$.MODULE$.apply((String)((String)event.appId().get()), (String)event.appName(), (Option)scala.None..MODULE$, (Option)scala.None..MODULE$, (Option)scala.None..MODULE$, (Option)scala.None..MODULE$, (Seq)(new scala.collection.immutable..colon.colon(attempt, scala.collection.immutable.Nil..MODULE$))));
      this.kvstore.write(new ApplicationInfoWrapper(this.appInfo()));
      this.kvstore.write(this.appSummary());
      event.driverLogs().foreach((logs) -> {
         $anonfun$onApplicationStart$2(this, event, logs);
         return BoxedUnit.UNIT;
      });
   }

   public void onResourceProfileAdded(final SparkListenerResourceProfileAdded event) {
      Option maxTasks = (Option)(event.resourceProfile().isCoresLimitKnown() ? new Some(BoxesRunTime.boxToInteger(event.resourceProfile().maxTasksPerExecutor(this.conf))) : scala.None..MODULE$);
      LiveResourceProfile liveRP = new LiveResourceProfile(event.resourceProfile().id(), event.resourceProfile().executorResources(), event.resourceProfile().taskResources(), maxTasks);
      this.liveResourceProfiles().update(BoxesRunTime.boxToInteger(event.resourceProfile().id()), liveRP);
      ResourceProfileInfo rpInfo = new ResourceProfileInfo(liveRP.resourceProfileId(), liveRP.executorResources(), liveRP.taskResources());
      this.kvstore.write(new ResourceProfileWrapper(rpInfo));
   }

   public void onEnvironmentUpdate(final SparkListenerEnvironmentUpdate event) {
      scala.collection.Map details = event.environmentDetails();
      scala.collection.immutable.Map jvmInfo = ((IterableOnceOps)details.apply("JVM Information")).toMap(scala..less.colon.less..MODULE$.refl());
      RuntimeInfo runtime = new RuntimeInfo((String)jvmInfo.get("Java Version").orNull(scala..less.colon.less..MODULE$.refl()), (String)jvmInfo.get("Java Home").orNull(scala..less.colon.less..MODULE$.refl()), (String)jvmInfo.get("Scala Version").orNull(scala..less.colon.less..MODULE$.refl()));
      ApplicationEnvironmentInfo envInfo = new ApplicationEnvironmentInfo(runtime, (Seq)details.getOrElse("Spark Properties", () -> scala.collection.immutable.Nil..MODULE$), (Seq)details.getOrElse("Hadoop Properties", () -> scala.collection.immutable.Nil..MODULE$), (Seq)details.getOrElse("System Properties", () -> scala.collection.immutable.Nil..MODULE$), (Seq)details.getOrElse("Metrics Properties", () -> scala.collection.immutable.Nil..MODULE$), (Seq)details.getOrElse("Classpath Entries", () -> scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$);
      this.defaultCpusPerTask_$eq(BoxesRunTime.unboxToInt(envInfo.sparkProperties().toMap(scala..less.colon.less..MODULE$.refl()).get(package$.MODULE$.CPUS_PER_TASK().key()).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$onEnvironmentUpdate$6(x$2))).getOrElse((JFunction0.mcI.sp)() -> this.defaultCpusPerTask())));
      this.kvstore.write(new ApplicationEnvironmentInfoWrapper(envInfo));
   }

   public void onApplicationEnd(final SparkListenerApplicationEnd event) {
      ApplicationAttemptInfo old = (ApplicationAttemptInfo)this.appInfo().attempts().head();
      ApplicationAttemptInfo attempt = ApplicationAttemptInfo$.MODULE$.apply(old.attemptId(), old.startTime(), new Date(event.time()), new Date(event.time()), event.time() - old.startTime().getTime(), old.sparkUser(), true, old.appSparkVersion());
      this.appInfo_$eq(ApplicationInfo$.MODULE$.apply((String)this.appInfo().id(), (String)this.appInfo().name(), (Option)scala.None..MODULE$, (Option)scala.None..MODULE$, (Option)scala.None..MODULE$, (Option)scala.None..MODULE$, (Seq)(new scala.collection.immutable..colon.colon(attempt, scala.collection.immutable.Nil..MODULE$))));
      this.kvstore.write(new ApplicationInfoWrapper(this.appInfo()));
   }

   public void onExecutorAdded(final SparkListenerExecutorAdded event) {
      LiveExecutor exec = this.getOrCreateExecutor(event.executorId(), event.time());
      exec.host_$eq(event.executorInfo().executorHost());
      exec.isActive_$eq(true);
      exec.totalCores_$eq(event.executorInfo().totalCores());
      int rpId = event.executorInfo().resourceProfileId();
      Option liveRP = this.liveResourceProfiles().get(BoxesRunTime.boxToInteger(rpId));
      int cpusPerTask = BoxesRunTime.unboxToInt(liveRP.flatMap((x$3) -> x$3.taskResources().get(ResourceProfile$.MODULE$.CPUS())).map((x$4) -> BoxesRunTime.boxToInteger($anonfun$onExecutorAdded$2(x$4))).getOrElse((JFunction0.mcI.sp)() -> this.defaultCpusPerTask()));
      Option maxTasksPerExec = liveRP.flatMap((x$5) -> x$5.maxTasksPerExecutor());
      exec.maxTasks_$eq(BoxesRunTime.unboxToInt(maxTasksPerExec.getOrElse((JFunction0.mcI.sp)() -> event.executorInfo().totalCores() / cpusPerTask)));
      exec.executorLogs_$eq(event.executorInfo().logUrlMap());
      exec.resources_$eq(event.executorInfo().resourcesInfo());
      exec.attributes_$eq(event.executorInfo().attributes());
      exec.resourceProfileId_$eq(rpId);
      this.liveUpdate(exec, System.nanoTime());
   }

   public void onExecutorRemoved(final SparkListenerExecutorRemoved event) {
      this.liveExecutors().remove(event.executorId()).foreach((exec) -> {
         long now = System.nanoTime();
         this.activeExecutorCount_$eq(scala.math.package..MODULE$.max(0, this.activeExecutorCount() - 1));
         exec.isActive_$eq(false);
         exec.removeTime_$eq(new Date(event.time()));
         exec.removeReason_$eq(event.reason());
         this.update(exec, now, true);
         this.liveRDDs().values().foreach((rdd) -> {
            $anonfun$onExecutorRemoved$2(this, exec, now, rdd);
            return BoxedUnit.UNIT;
         });
         this.liveRDDs().values().foreach((rdd) -> {
            $anonfun$onExecutorRemoved$3(this, event, now, rdd);
            return BoxedUnit.UNIT;
         });
         return this.isExecutorActiveForLiveStages(exec) ? this.deadExecutors().put(event.executorId(), exec) : BoxedUnit.UNIT;
      });
   }

   private boolean isExecutorActiveForLiveStages(final LiveExecutor exec) {
      return scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.liveStages().values()).asScala().exists((stage) -> BoxesRunTime.boxToBoolean($anonfun$isExecutorActiveForLiveStages$1(exec, stage)));
   }

   public void onExecutorBlacklisted(final SparkListenerExecutorBlacklisted event) {
      this.updateExecExclusionStatus(event.executorId(), true);
   }

   public void onExecutorExcluded(final SparkListenerExecutorExcluded event) {
      this.updateExecExclusionStatus(event.executorId(), true);
   }

   public void onExecutorBlacklistedForStage(final SparkListenerExecutorBlacklistedForStage event) {
      this.updateExclusionStatusForStage(event.stageId(), event.stageAttemptId(), event.executorId());
   }

   public void onExecutorExcludedForStage(final SparkListenerExecutorExcludedForStage event) {
      this.updateExclusionStatusForStage(event.stageId(), event.stageAttemptId(), event.executorId());
   }

   public void onNodeBlacklistedForStage(final SparkListenerNodeBlacklistedForStage event) {
      this.updateNodeExclusionStatusForStage(event.stageId(), event.stageAttemptId(), event.hostId());
   }

   public void onNodeExcludedForStage(final SparkListenerNodeExcludedForStage event) {
      this.updateNodeExclusionStatusForStage(event.stageId(), event.stageAttemptId(), event.hostId());
   }

   private void addExcludedStageTo(final LiveExecutor exec, final int stageId, final long now) {
      exec.excludedInStages_$eq((Set)exec.excludedInStages().$plus(BoxesRunTime.boxToInteger(stageId)));
      this.liveUpdate(exec, now);
   }

   private void setStageExcludedStatus(final LiveStage stage, final long now, final scala.collection.immutable.Seq executorIds) {
      executorIds.foreach((executorId) -> {
         $anonfun$setStageExcludedStatus$1(this, stage, now, executorId);
         return BoxedUnit.UNIT;
      });
      stage.excludedExecutors_$eq((HashSet)stage.excludedExecutors().$plus$plus(executorIds));
      this.maybeUpdate(stage, now);
   }

   public void onExecutorUnblacklisted(final SparkListenerExecutorUnblacklisted event) {
      this.updateExecExclusionStatus(event.executorId(), false);
   }

   public void onExecutorUnexcluded(final SparkListenerExecutorUnexcluded event) {
      this.updateExecExclusionStatus(event.executorId(), false);
   }

   public void onNodeBlacklisted(final SparkListenerNodeBlacklisted event) {
      this.updateNodeExcluded(event.hostId(), true);
   }

   public void onNodeExcluded(final SparkListenerNodeExcluded event) {
      this.updateNodeExcluded(event.hostId(), true);
   }

   public void onNodeUnblacklisted(final SparkListenerNodeUnblacklisted event) {
      this.updateNodeExcluded(event.hostId(), false);
   }

   public void onNodeUnexcluded(final SparkListenerNodeUnexcluded event) {
      this.updateNodeExcluded(event.hostId(), false);
   }

   private void updateNodeExclusionStatusForStage(final int stageId, final int stageAttemptId, final String hostId) {
      long now = System.nanoTime();
      scala.Option..MODULE$.apply(this.liveStages().get(new Tuple2.mcII.sp(stageId, stageAttemptId))).foreach((stage) -> {
         $anonfun$updateNodeExclusionStatusForStage$1(this, hostId, now, stage);
         return BoxedUnit.UNIT;
      });
      ((IterableOnceOps)this.liveExecutors().values().filter((exec) -> BoxesRunTime.boxToBoolean($anonfun$updateNodeExclusionStatusForStage$4(hostId, exec)))).foreach((exec) -> {
         $anonfun$updateNodeExclusionStatusForStage$5(this, stageId, now, exec);
         return BoxedUnit.UNIT;
      });
   }

   private void updateExclusionStatusForStage(final int stageId, final int stageAttemptId, final String execId) {
      long now = System.nanoTime();
      scala.Option..MODULE$.apply(this.liveStages().get(new Tuple2.mcII.sp(stageId, stageAttemptId))).foreach((stage) -> {
         $anonfun$updateExclusionStatusForStage$1(this, now, execId, stage);
         return BoxedUnit.UNIT;
      });
      this.liveExecutors().get(execId).foreach((exec) -> {
         $anonfun$updateExclusionStatusForStage$2(this, stageId, now, exec);
         return BoxedUnit.UNIT;
      });
   }

   private void updateExecExclusionStatus(final String execId, final boolean excluded) {
      this.liveExecutors().get(execId).foreach((exec) -> {
         $anonfun$updateExecExclusionStatus$1(this, excluded, exec);
         return BoxedUnit.UNIT;
      });
   }

   private void updateExecExclusionStatus(final LiveExecutor exec, final boolean excluded, final long now) {
      if (exec.isExcluded() != excluded) {
         if (excluded) {
            this.appStatusSource.foreach((x$9) -> {
               $anonfun$updateExecExclusionStatus$2(x$9);
               return BoxedUnit.UNIT;
            });
            this.appStatusSource.foreach((x$10) -> {
               $anonfun$updateExecExclusionStatus$3(x$10);
               return BoxedUnit.UNIT;
            });
         } else {
            this.appStatusSource.foreach((x$11) -> {
               $anonfun$updateExecExclusionStatus$4(x$11);
               return BoxedUnit.UNIT;
            });
            this.appStatusSource.foreach((x$12) -> {
               $anonfun$updateExecExclusionStatus$5(x$12);
               return BoxedUnit.UNIT;
            });
         }

         exec.isExcluded_$eq(excluded);
         this.liveUpdate(exec, now);
      }
   }

   private void updateNodeExcluded(final String host, final boolean excluded) {
      long now = System.nanoTime();
      this.liveExecutors().values().foreach((exec) -> {
         $anonfun$updateNodeExcluded$1(this, host, excluded, now, exec);
         return BoxedUnit.UNIT;
      });
   }

   public void onJobStart(final SparkListenerJobStart event) {
      long now = System.nanoTime();
      scala.collection.immutable.Seq missingStages = (scala.collection.immutable.Seq)event.stageInfos().filter((x$13) -> BoxesRunTime.boxToBoolean($anonfun$onJobStart$1(x$13)));
      int numTasks = BoxesRunTime.unboxToInt(((IterableOnceOps)missingStages.map((x$14) -> BoxesRunTime.boxToInteger($anonfun$onJobStart$2(x$14)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      Option lastStageInfo = ((IterableOps)event.stageInfos().sortBy((x$15) -> BoxesRunTime.boxToInteger($anonfun$onJobStart$3(x$15)), scala.math.Ordering.Int..MODULE$)).lastOption();
      String jobName = (String)lastStageInfo.map((x$16) -> x$16.name()).getOrElse(() -> "");
      Option description = scala.Option..MODULE$.apply(event.properties()).flatMap((p) -> scala.Option..MODULE$.apply(p.getProperty(SparkContext$.MODULE$.SPARK_JOB_DESCRIPTION())));
      Option jobGroup = scala.Option..MODULE$.apply(event.properties()).flatMap((p) -> scala.Option..MODULE$.apply(p.getProperty(SparkContext$.MODULE$.SPARK_JOB_GROUP_ID())));
      scala.collection.immutable.Seq jobTags = (scala.collection.immutable.Seq)((SeqOps)((IterableOnceOps)scala.Option..MODULE$.apply(event.properties()).flatMap((p) -> scala.Option..MODULE$.apply(p.getProperty(SparkContext$.MODULE$.SPARK_JOB_TAGS()))).map((x$17) -> .MODULE$.wrapRefArray((Object[])x$17.split(SparkContext$.MODULE$.SPARK_JOB_TAGS_SEP())).toSet()).getOrElse(() -> (Set).MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$))).toSeq().filter((x$18) -> BoxesRunTime.boxToBoolean($anonfun$onJobStart$11(x$18)))).sorted(scala.math.Ordering.String..MODULE$);
      Option sqlExecutionId = scala.Option..MODULE$.apply(event.properties()).flatMap((p) -> scala.Option..MODULE$.apply(p.getProperty(this.SQL_EXECUTION_ID_KEY())).map((x$19) -> BoxesRunTime.boxToLong($anonfun$onJobStart$13(x$19))));
      LiveJob job = new LiveJob(event.jobId(), jobName, description, (Option)(event.time() > 0L ? new Some(new Date(event.time())) : scala.None..MODULE$), event.stageIds(), jobGroup, jobTags, numTasks, sqlExecutionId);
      this.liveJobs().put(BoxesRunTime.boxToInteger(event.jobId()), job);
      this.liveUpdate(job, now);
      event.stageInfos().foreach((stageInfo) -> {
         $anonfun$onJobStart$14(this, job, event, now, stageInfo);
         return BoxedUnit.UNIT;
      });
      event.stageInfos().foreach((stage) -> {
         $anonfun$onJobStart$15(this, stage);
         return BoxedUnit.UNIT;
      });
   }

   private RDDOperationClusterWrapper newRDDOperationCluster(final RDDOperationCluster cluster) {
      return new RDDOperationClusterWrapper(cluster.id(), cluster.name(), cluster.childNodes(), (Seq)cluster.childClusters().map((clusterx) -> this.newRDDOperationCluster(clusterx)));
   }

   public void onJobEnd(final SparkListenerJobEnd event) {
      this.liveJobs().remove(BoxesRunTime.boxToInteger(event.jobId())).foreach((job) -> {
         $anonfun$onJobEnd$1(this, event, job);
         return BoxedUnit.UNIT;
      });
   }

   public void onStageSubmitted(final SparkListenerStageSubmitted event) {
      long now = System.nanoTime();
      LiveStage stage = this.getOrCreateStage(event.stageInfo());
      stage.status_$eq(StageStatus.ACTIVE);
      stage.schedulingPool_$eq((String)scala.Option..MODULE$.apply(event.properties()).flatMap((p) -> scala.Option..MODULE$.apply(p.getProperty(SparkContext$.MODULE$.SPARK_SCHEDULER_POOL()))).getOrElse(() -> SparkUI$.MODULE$.DEFAULT_POOL_NAME()));
      stage.jobs_$eq(((IterableOnceOps)this.liveJobs().values().filter((x$22) -> BoxesRunTime.boxToBoolean($anonfun$onStageSubmitted$3(event, x$22)))).toSeq());
      stage.jobIds_$eq(((IterableOnceOps)stage.jobs().map((x$23) -> BoxesRunTime.boxToInteger($anonfun$onStageSubmitted$4(x$23)))).toSet());
      stage.description_$eq(scala.Option..MODULE$.apply(event.properties()).flatMap((p) -> scala.Option..MODULE$.apply(p.getProperty(SparkContext$.MODULE$.SPARK_JOB_DESCRIPTION()))));
      stage.jobs().foreach((job) -> {
         $anonfun$onStageSubmitted$6(this, event, now, job);
         return BoxedUnit.UNIT;
      });
      SchedulerPool pool = (SchedulerPool)this.pools().getOrElseUpdate(stage.schedulingPool(), () -> new SchedulerPool(stage.schedulingPool()));
      pool.stageIds_$eq((Set)pool.stageIds().$plus(BoxesRunTime.boxToInteger(event.stageInfo().stageId())));
      this.update(pool, now, this.update$default$3());
      event.stageInfo().rddInfos().foreach((info) -> {
         $anonfun$onStageSubmitted$8(this, now, info);
         return BoxedUnit.UNIT;
      });
      this.liveUpdate(stage, now);
   }

   public void onTaskStart(final SparkListenerTaskStart event) {
      long now = System.nanoTime();
      LiveTask task = new LiveTask(event.taskInfo(), event.stageId(), event.stageAttemptId(), this.lastUpdateTime);
      this.liveTasks().put(BoxesRunTime.boxToLong(event.taskInfo().taskId()), task);
      this.liveUpdate(task, now);
      scala.Option..MODULE$.apply(this.liveStages().get(new Tuple2.mcII.sp(event.stageId(), event.stageAttemptId()))).foreach((stage) -> {
         $anonfun$onTaskStart$1(this, event, now, stage);
         return BoxedUnit.UNIT;
      });
      this.liveExecutors().get(event.taskInfo().executorId()).foreach((exec) -> {
         $anonfun$onTaskStart$5(this, now, exec);
         return BoxedUnit.UNIT;
      });
   }

   public void onTaskGettingResult(final SparkListenerTaskGettingResult event) {
      this.liveTasks().get(BoxesRunTime.boxToLong(event.taskInfo().taskId())).foreach((task) -> {
         $anonfun$onTaskGettingResult$1(this, task);
         return BoxedUnit.UNIT;
      });
   }

   public void onTaskEnd(final SparkListenerTaskEnd event) {
      if (event.taskInfo() != null) {
         long now = System.nanoTime();
         TaskMetrics metricsDelta = (TaskMetrics)this.liveTasks().remove(BoxesRunTime.boxToLong(event.taskInfo().taskId())).map((task) -> {
            task.info_$eq(event.taskInfo());
            TaskEndReason var7 = event.reason();
            Object var10000;
            if (Success$.MODULE$.equals(var7)) {
               var10000 = scala.None..MODULE$;
            } else if (var7 instanceof TaskKilled) {
               TaskKilled var8 = (TaskKilled)var7;
               var10000 = new Some(var8.reason());
            } else if (var7 instanceof ExceptionFailure) {
               ExceptionFailure var9 = (ExceptionFailure)var7;
               var10000 = new Some(var9.toErrorString());
            } else if (var7 instanceof TaskFailedReason) {
               TaskFailedReason var10 = (TaskFailedReason)var7;
               var10000 = new Some(var10.toErrorString());
            } else {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unhandled task end reason: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, var7)})))));
               var10000 = scala.None..MODULE$;
            }

            Option errorMessage = (Option)var10000;
            task.errorMessage_$eq(errorMessage);
            TaskMetrics delta = task.updateMetrics(event.taskMetrics());
            this.update(task, now, true);
            return delta;
         }).orNull(scala..less.colon.less..MODULE$.refl());
         TaskEndReason var9 = event.reason();
         Tuple4 var8 = Success$.MODULE$.equals(var9) ? new Tuple4(BoxesRunTime.boxToInteger(1), BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(1)) : (var9 instanceof TaskKilled ? new Tuple4(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(1), BoxesRunTime.boxToInteger(1)) : (var9 instanceof TaskCommitDenied ? new Tuple4(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(1), BoxesRunTime.boxToInteger(1)) : (Resubmitted$.MODULE$.equals(var9) ? new Tuple4(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(1), BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(0)) : new Tuple4(BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(1), BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(1)))));
         if (var8 != null) {
            int completedDelta = BoxesRunTime.unboxToInt(var8._1());
            int failedDelta = BoxesRunTime.unboxToInt(var8._2());
            int killedDelta = BoxesRunTime.unboxToInt(var8._3());
            int activeDelta = BoxesRunTime.unboxToInt(var8._4());
            Tuple4 var7 = new Tuple4(BoxesRunTime.boxToInteger(completedDelta), BoxesRunTime.boxToInteger(failedDelta), BoxesRunTime.boxToInteger(killedDelta), BoxesRunTime.boxToInteger(activeDelta));
            int completedDelta = BoxesRunTime.unboxToInt(var7._1());
            int failedDelta = BoxesRunTime.unboxToInt(var7._2());
            int killedDelta = BoxesRunTime.unboxToInt(var7._3());
            int activeDelta = BoxesRunTime.unboxToInt(var7._4());
            scala.Option..MODULE$.apply(this.liveStages().get(new Tuple2.mcII.sp(event.stageId(), event.stageAttemptId()))).foreach((stage) -> {
               if (metricsDelta != null) {
                  stage.metrics_$eq(LiveEntityHelpers$.MODULE$.addMetrics(stage.metrics(), metricsDelta));
               }

               stage.activeTasks_$eq(stage.activeTasks() - activeDelta);
               stage.completedTasks_$eq(stage.completedTasks() + completedDelta);
               if (completedDelta > 0) {
                  stage.completedIndices().add$mcI$sp(event.taskInfo().index());
               }

               stage.failedTasks_$eq(stage.failedTasks() + failedDelta);
               stage.killedTasks_$eq(stage.killedTasks() + killedDelta);
               if (killedDelta > 0) {
                  stage.killedSummary_$eq(this.killedTasksSummary(event.reason(), stage.killedSummary()));
               }

               stage.activeTasksPerExecutor().update(event.taskInfo().executorId(), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(stage.activeTasksPerExecutor().apply(event.taskInfo().executorId())) - activeDelta));
               stage.peakExecutorMetrics().compareAndUpdatePeakValues(event.taskExecutorMetrics());
               stage.executorSummary(event.taskInfo().executorId()).peakExecutorMetrics().compareAndUpdatePeakValues(event.taskExecutorMetrics());
               boolean removeStage = stage.activeTasks() == 0 && (StageStatus.COMPLETE.equals(stage.status()) || StageStatus.FAILED.equals(stage.status()));
               if (removeStage) {
                  this.update(stage, now, true);
               } else {
                  this.maybeUpdate(stage, now);
               }

               long taskIndex = (long)event.stageId() << 32 | (long)event.taskInfo().index();
               stage.jobs().foreach((job) -> {
                  $anonfun$onTaskEnd$4(this, activeDelta, completedDelta, taskIndex, failedDelta, killedDelta, event, removeStage, now, job);
                  return BoxedUnit.UNIT;
               });
               LiveExecutorStageSummary esummary = stage.executorSummary(event.taskInfo().executorId());
               esummary.taskTime_$eq(esummary.taskTime() + event.taskInfo().duration());
               esummary.succeededTasks_$eq(esummary.succeededTasks() + completedDelta);
               esummary.failedTasks_$eq(esummary.failedTasks() + failedDelta);
               esummary.killedTasks_$eq(esummary.killedTasks() + killedDelta);
               if (metricsDelta != null) {
                  esummary.metrics_$eq(LiveEntityHelpers$.MODULE$.addMetrics(esummary.metrics(), metricsDelta));
               }

               boolean isLastTask = BoxesRunTime.unboxToInt(stage.activeTasksPerExecutor().apply(event.taskInfo().executorId())) == 0;
               if (isLastTask) {
                  this.update(esummary, now, this.update$default$3());
               } else {
                  this.maybeUpdate(esummary, now);
               }

               if (event.taskInfo().speculative()) {
                  stage.speculationStageSummary().numActiveTasks_$eq(stage.speculationStageSummary().numActiveTasks() - 1);
                  stage.speculationStageSummary().numCompletedTasks_$eq(stage.speculationStageSummary().numCompletedTasks() + completedDelta);
                  stage.speculationStageSummary().numFailedTasks_$eq(stage.speculationStageSummary().numFailedTasks() + failedDelta);
                  stage.speculationStageSummary().numKilledTasks_$eq(stage.speculationStageSummary().numKilledTasks() + killedDelta);
                  this.update(stage.speculationStageSummary(), now, this.update$default$3());
               }

               if (!stage.cleaning() && stage.savedTasks().get() > this.maxTasksPerStage()) {
                  stage.cleaning_$eq(true);
                  this.kvstore.doAsync((JFunction0.mcV.sp)() -> this.cleanupTasks(stage));
               }

               return removeStage ? this.liveStages().remove(new Tuple2.mcII.sp(event.stageId(), event.stageAttemptId())) : BoxedUnit.UNIT;
            });
            this.liveExecutors().get(event.taskInfo().executorId()).foreach((exec) -> {
               $anonfun$onTaskEnd$6(this, activeDelta, completedDelta, failedDelta, event, now, exec);
               return BoxedUnit.UNIT;
            });
         } else {
            throw new MatchError(var8);
         }
      }
   }

   public void onStageCompleted(final SparkListenerStageCompleted event) {
      Option maybeStage = scala.Option..MODULE$.apply(this.liveStages().get(new Tuple2.mcII.sp(event.stageInfo().stageId(), event.stageInfo().attemptNumber())));
      maybeStage.foreach((stage) -> {
         $anonfun$onStageCompleted$1(this, event, stage);
         return BoxedUnit.UNIT;
      });
      this.deadExecutors().filterInPlace((execId, exec) -> BoxesRunTime.boxToBoolean($anonfun$onStageCompleted$7(this, execId, exec)));
   }

   private void removeExcludedStageFrom(final LiveExecutor exec, final int stageId, final long now) {
      exec.excludedInStages_$eq((Set)exec.excludedInStages().$minus(BoxesRunTime.boxToInteger(stageId)));
      this.liveUpdate(exec, now);
   }

   public void onBlockManagerAdded(final SparkListenerBlockManagerAdded event) {
      LiveExecutor exec = this.getOrCreateExecutor(event.blockManagerId().executorId(), event.time());
      exec.hostPort_$eq(event.blockManagerId().hostPort());
      event.maxOnHeapMem().foreach((JFunction1.mcVJ.sp)(x$26) -> {
         exec.totalOnHeap_$eq(BoxesRunTime.unboxToLong(event.maxOnHeapMem().get()));
         exec.totalOffHeap_$eq(BoxesRunTime.unboxToLong(event.maxOffHeapMem().get()));
         exec.usedOnHeap_$eq(0L);
         exec.usedOffHeap_$eq(0L);
      });
      exec.isActive_$eq(true);
      exec.maxMemory_$eq(event.maxMem());
      this.liveUpdate(exec, System.nanoTime());
   }

   public void onBlockManagerRemoved(final SparkListenerBlockManagerRemoved event) {
   }

   public void onUnpersistRDD(final SparkListenerUnpersistRDD event) {
      this.liveRDDs().remove(BoxesRunTime.boxToInteger(event.rddId())).foreach((liveRDD) -> {
         $anonfun$onUnpersistRDD$1(this, liveRDD);
         return BoxedUnit.UNIT;
      });
      this.kvstore.delete(RDDStorageInfoWrapper.class, BoxesRunTime.boxToInteger(event.rddId()));
   }

   public void onExecutorMetricsUpdate(final SparkListenerExecutorMetricsUpdate event) {
      long now = System.nanoTime();
      event.accumUpdates().foreach((x0$1) -> {
         $anonfun$onExecutorMetricsUpdate$1(this, now, event, x0$1);
         return BoxedUnit.UNIT;
      });
      event.executorUpdates().foreach((x0$2) -> {
         $anonfun$onExecutorMetricsUpdate$4(this, event, now, x0$2);
         return BoxedUnit.UNIT;
      });
      if (now - this.lastFlushTimeNs() > this.liveUpdateMinFlushPeriod()) {
         this.flush((x$27) -> {
            $anonfun$onExecutorMetricsUpdate$6(this, now, x$27);
            return BoxedUnit.UNIT;
         });
         this.lastFlushTimeNs_$eq(System.nanoTime());
      }
   }

   public void onStageExecutorMetrics(final SparkListenerStageExecutorMetrics event) {
      long now = System.nanoTime();
      this.liveExecutors().get(event.execId()).orElse(() -> this.deadExecutors().get(event.execId())).foreach((exec) -> {
         $anonfun$onStageExecutorMetrics$2(this, event, now, exec);
         return BoxedUnit.UNIT;
      });
      this.updateStageLevelPeakExecutorMetrics(event.stageId(), event.stageAttemptId(), event.execId(), event.executorMetrics(), now);
   }

   private void updateStageLevelPeakExecutorMetrics(final int stageId, final int stageAttemptId, final String executorId, final ExecutorMetrics executorMetrics, final long now) {
      scala.Option..MODULE$.apply(this.liveStages().get(new Tuple2.mcII.sp(stageId, stageAttemptId))).foreach((stage) -> {
         $anonfun$updateStageLevelPeakExecutorMetrics$1(this, executorMetrics, now, executorId, stage);
         return BoxedUnit.UNIT;
      });
   }

   public void onBlockUpdated(final SparkListenerBlockUpdated event) {
      BlockId var3 = event.blockUpdatedInfo().blockId();
      if (var3 instanceof RDDBlockId var4) {
         this.updateRDDBlock(event, var4);
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else if (var3 instanceof StreamBlockId var5) {
         this.updateStreamBlock(event, var5);
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (var3 instanceof BroadcastBlockId var6) {
         this.updateBroadcastBlock(event, var6);
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   private void flush(final Function1 entityFlushFunc) {
      scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.liveStages().values()).asScala().foreach((stage) -> {
         $anonfun$flush$1(entityFlushFunc, stage);
         return BoxedUnit.UNIT;
      });
      this.liveJobs().values().foreach(entityFlushFunc);
      this.liveExecutors().values().foreach(entityFlushFunc);
      this.liveTasks().values().foreach(entityFlushFunc);
      this.liveRDDs().values().foreach(entityFlushFunc);
      this.pools().values().foreach(entityFlushFunc);
   }

   public scala.collection.immutable.Seq activeStages() {
      return (scala.collection.immutable.Seq)((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(this.liveStages().values()).asScala().filter((s) -> BoxesRunTime.boxToBoolean($anonfun$activeStages$1(s)))).map((x$29) -> x$29.toApi())).toList().sortBy((x$30) -> BoxesRunTime.boxToInteger($anonfun$activeStages$4(x$30)), scala.math.Ordering.Int..MODULE$);
   }

   private long addDeltaToValue(final long old, final long delta) {
      return scala.math.package..MODULE$.max(0L, old + delta);
   }

   private void updateRDDBlock(final SparkListenerBlockUpdated event, final RDDBlockId block) {
      long now = System.nanoTime();
      String executorId = event.blockUpdatedInfo().blockManagerId().executorId();
      StorageLevel storageLevel = event.blockUpdatedInfo().storageLevel();
      long diskDelta = event.blockUpdatedInfo().diskSize() * (long)(storageLevel.useDisk() ? 1 : -1);
      long memoryDelta = event.blockUpdatedInfo().memSize() * (long)(storageLevel.useMemory() ? 1 : -1);
      Option maybeExec = this.liveExecutors().get(executorId);
      IntRef rddBlocksDelta = IntRef.create(0);
      maybeExec.foreach((exec) -> {
         $anonfun$updateRDDBlock$1(this, storageLevel, memoryDelta, diskDelta, exec);
         return BoxedUnit.UNIT;
      });
      this.liveRDDs().get(BoxesRunTime.boxToInteger(block.rddId())).foreach((rdd) -> {
         $anonfun$updateRDDBlock$2(this, block, storageLevel, executorId, rddBlocksDelta, memoryDelta, diskDelta, maybeExec, now, rdd);
         return BoxedUnit.UNIT;
      });
      maybeExec.foreach((exec) -> {
         $anonfun$updateRDDBlock$7(this, rddBlocksDelta, now, exec);
         return BoxedUnit.UNIT;
      });
   }

   private LiveExecutor getOrCreateExecutor(final String executorId, final long addTime) {
      return (LiveExecutor)this.liveExecutors().getOrElseUpdate(executorId, () -> {
         this.activeExecutorCount_$eq(this.activeExecutorCount() + 1);
         return new LiveExecutor(executorId, addTime);
      });
   }

   private LiveMiscellaneousProcess getOrCreateOtherProcess(final String processId, final long addTime) {
      return (LiveMiscellaneousProcess)this.liveMiscellaneousProcess().getOrElseUpdate(processId, () -> new LiveMiscellaneousProcess(processId, addTime));
   }

   private void updateStreamBlock(final SparkListenerBlockUpdated event, final StreamBlockId stream) {
      StorageLevel storageLevel = event.blockUpdatedInfo().storageLevel();
      if (storageLevel.isValid()) {
         StreamBlockData data = new StreamBlockData(stream.name(), event.blockUpdatedInfo().blockManagerId().executorId(), event.blockUpdatedInfo().blockManagerId().hostPort(), storageLevel.description(), storageLevel.useMemory(), storageLevel.useDisk(), storageLevel.deserialized(), event.blockUpdatedInfo().memSize(), event.blockUpdatedInfo().diskSize());
         this.kvstore.write(data);
      } else {
         this.kvstore.delete(StreamBlockData.class, (Object[])(new String[]{stream.name(), event.blockUpdatedInfo().blockManagerId().executorId()}));
      }
   }

   private void updateBroadcastBlock(final SparkListenerBlockUpdated event, final BroadcastBlockId broadcast) {
      String executorId = event.blockUpdatedInfo().blockManagerId().executorId();
      this.liveExecutors().get(executorId).foreach((exec) -> {
         $anonfun$updateBroadcastBlock$1(this, event, exec);
         return BoxedUnit.UNIT;
      });
   }

   public void updateExecutorMemoryDiskInfo(final LiveExecutor exec, final StorageLevel storageLevel, final long memoryDelta, final long diskDelta) {
      if (exec.hasMemoryInfo()) {
         if (storageLevel.useOffHeap()) {
            exec.usedOffHeap_$eq(this.addDeltaToValue(exec.usedOffHeap(), memoryDelta));
         } else {
            exec.usedOnHeap_$eq(this.addDeltaToValue(exec.usedOnHeap(), memoryDelta));
         }
      }

      exec.memoryUsed_$eq(this.addDeltaToValue(exec.memoryUsed(), memoryDelta));
      exec.diskUsed_$eq(this.addDeltaToValue(exec.diskUsed(), diskDelta));
   }

   private LiveStage getOrCreateStage(final StageInfo info) {
      LiveStage stage = (LiveStage)this.liveStages().computeIfAbsent(new Tuple2.mcII.sp(info.stageId(), info.attemptNumber()), (x$32) -> new LiveStage(info));
      stage.info_$eq(info);
      return stage;
   }

   private scala.collection.immutable.Map killedTasksSummary(final TaskEndReason reason, final scala.collection.immutable.Map oldSummary) {
      if (reason instanceof TaskKilled var5) {
         return (scala.collection.immutable.Map)oldSummary.updated(var5.reason(), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(oldSummary.getOrElse(var5.reason(), (JFunction0.mcI.sp)() -> 0)) + 1));
      } else if (reason instanceof TaskCommitDenied var6) {
         String reason = var6.toErrorString();
         return (scala.collection.immutable.Map)oldSummary.updated(reason, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(oldSummary.getOrElse(reason, (JFunction0.mcI.sp)() -> 0)) + 1));
      } else {
         return oldSummary;
      }
   }

   private void update(final LiveEntity entity, final long now, final boolean last) {
      entity.write(this.kvstore, now, last);
   }

   private boolean update$default$3() {
      return false;
   }

   private void maybeUpdate(final LiveEntity entity, final long now) {
      if (this.live && this.liveUpdatePeriodNs() >= 0L && now - entity.lastWriteTime() > this.liveUpdatePeriodNs()) {
         this.update(entity, now, this.update$default$3());
      }
   }

   private void liveUpdate(final LiveEntity entity, final long now) {
      if (this.live) {
         this.update(entity, now, this.update$default$3());
      }
   }

   private void cleanupExecutors(final long count) {
      int threshold = BoxesRunTime.unboxToInt(this.conf.get(Status$.MODULE$.MAX_RETAINED_DEAD_EXECUTORS()));
      long dead = count - (long)this.activeExecutorCount();
      if (dead > (long)threshold) {
         long countToDelete = this.calculateNumberToRemove(dead, (long)threshold);
         scala.collection.immutable.Seq toDelete = KVUtils$.MODULE$.viewToSeq(this.kvstore.view(ExecutorSummaryWrapper.class).index("active").max(countToDelete).first(BoxesRunTime.boxToBoolean(false)).last(BoxesRunTime.boxToBoolean(false)));
         toDelete.foreach((e) -> {
            $anonfun$cleanupExecutors$1(this, e);
            return BoxedUnit.UNIT;
         });
      }
   }

   private void cleanupJobs(final long count) {
      long countToDelete = this.calculateNumberToRemove(count, (long)BoxesRunTime.unboxToInt(this.conf.get(Status$.MODULE$.MAX_RETAINED_JOBS())));
      if (countToDelete > 0L) {
         KVStoreView view = this.kvstore.view(JobDataWrapper.class).index("completionTime").first(BoxesRunTime.boxToLong(0L));
         scala.collection.immutable.Seq toDelete = KVUtils$.MODULE$.viewToSeq(view, (int)countToDelete, (j) -> BoxesRunTime.boxToBoolean($anonfun$cleanupJobs$1(j)));
         toDelete.foreach((j) -> {
            $anonfun$cleanupJobs$2(this, j);
            return BoxedUnit.UNIT;
         });
      }
   }

   private scala.collection.immutable.Seq cleanupStagesWithInMemoryStore(final long countToDelete) {
      ArrayBuffer stageArray = new ArrayBuffer();
      HashMap stageDataCount = new HashMap();
      KVUtils$.MODULE$.foreach(this.kvstore.view(StageDataWrapper.class), (s) -> {
         $anonfun$cleanupStagesWithInMemoryStore$1(this, stageDataCount, stageArray, s);
         return BoxedUnit.UNIT;
      });
      return ((IterableOnceOps)((StrictOptimizedIterableOps)((IndexedSeqOps)stageArray.sortBy((x$33) -> BoxesRunTime.boxToLong($anonfun$cleanupStagesWithInMemoryStore$2(x$33)), scala.math.Ordering.Long..MODULE$)).take((int)countToDelete)).map((s) -> {
         int[] key = new int[]{s.stageId(), s.attemptId()};
         this.kvstore.delete(StageDataWrapper.class, key);
         stageDataCount.update(BoxesRunTime.boxToInteger(s.stageId()), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(stageDataCount.apply(BoxesRunTime.boxToInteger(s.stageId()))) - 1));
         if (BoxesRunTime.unboxToInt(stageDataCount.apply(BoxesRunTime.boxToInteger(s.stageId()))) == 0) {
            this.kvstore.delete(RDDOperationGraphWrapper.class, BoxesRunTime.boxToInteger(s.stageId()));
         }

         this.cleanupCachedQuantiles(key);
         return key;
      })).toSeq();
   }

   private scala.collection.immutable.Seq cleanupStagesInKVStore(final long countToDelete) {
      KVStoreView view = this.kvstore.view(StageDataWrapper.class).index("completionTime");
      scala.collection.immutable.Seq stages = KVUtils$.MODULE$.viewToSeq(view, (int)countToDelete, (s) -> BoxesRunTime.boxToBoolean($anonfun$cleanupStagesInKVStore$1(s)));
      return (scala.collection.immutable.Seq)stages.map((s) -> {
         int[] key = new int[]{s.info().stageId(), s.info().attemptId()};
         this.kvstore.delete(s.getClass(), key);
         KVStoreIterator remainingAttempts = this.kvstore.view(StageDataWrapper.class).index("stageId").first(BoxesRunTime.boxToInteger(s.info().stageId())).last(BoxesRunTime.boxToInteger(s.info().stageId())).closeableIterator();

         boolean var10000;
         try {
            var10000 = scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(remainingAttempts).asScala().exists((other) -> BoxesRunTime.boxToBoolean($anonfun$cleanupStagesInKVStore$3(s, other)));
         } finally {
            remainingAttempts.close();
         }

         boolean hasMoreAttempts = var10000;
         if (!hasMoreAttempts) {
            this.kvstore.delete(RDDOperationGraphWrapper.class, BoxesRunTime.boxToInteger(s.info().stageId()));
         }

         this.cleanupCachedQuantiles(key);
         return key;
      });
   }

   private void cleanupStages(final long count) {
      long countToDelete = this.calculateNumberToRemove(count, (long)BoxesRunTime.unboxToInt(this.conf.get(Status$.MODULE$.MAX_RETAINED_STAGES())));
      if (countToDelete > 0L) {
         scala.collection.immutable.Seq stageIds = this.kvstore.usingInMemoryStore() ? this.cleanupStagesWithInMemoryStore(countToDelete) : this.cleanupStagesInKVStore(countToDelete);
         this.kvstore.removeAllByIndexValues(ExecutorStageSummaryWrapper.class, "stage", (Iterable)stageIds);
         this.kvstore.removeAllByIndexValues(TaskDataWrapper.class, "stage", (Iterable)stageIds);
      }
   }

   private void cleanupTasks(final LiveStage stage) {
      int countToDelete = (int)this.calculateNumberToRemove((long)stage.savedTasks().get(), (long)this.maxTasksPerStage());
      if (countToDelete > 0) {
         int[] stageKey = new int[]{stage.info().stageId(), stage.info().attemptNumber()};
         KVStoreView view = this.kvstore.view(TaskDataWrapper.class).index("ct").parent(stageKey);
         scala.collection.immutable.Seq toDelete = KVUtils$.MODULE$.viewToSeq(view, countToDelete, (t) -> BoxesRunTime.boxToBoolean($anonfun$cleanupTasks$1(this, t)));
         toDelete.foreach((t) -> {
            $anonfun$cleanupTasks$2(this, t);
            return BoxedUnit.UNIT;
         });
         stage.savedTasks().addAndGet(-toDelete.size());
         int remaining = countToDelete - toDelete.size();
         if (remaining > 0) {
            List runningTasksToDelete = scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(view.max((long)remaining).iterator()).asScala().toList();
            runningTasksToDelete.foreach((t) -> {
               $anonfun$cleanupTasks$3(this, t);
               return BoxedUnit.UNIT;
            });
            BoxesRunTime.boxToInteger(stage.savedTasks().addAndGet(-remaining));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (this.live) {
            this.cleanupCachedQuantiles(stageKey);
         }
      }

      stage.cleaning_$eq(false);
   }

   private void cleanupCachedQuantiles(final int[] stageKey) {
      scala.collection.immutable.Seq cachedQuantiles = KVUtils$.MODULE$.viewToSeq(this.kvstore.view(CachedQuantile.class).index("stage").first(stageKey).last(stageKey));
      cachedQuantiles.foreach((q) -> {
         $anonfun$cleanupCachedQuantiles$1(this, q);
         return BoxedUnit.UNIT;
      });
   }

   private long calculateNumberToRemove(final long dataSize, final long retainedSize) {
      return dataSize > retainedSize ? scala.math.package..MODULE$.max(retainedSize / 10L, dataSize - retainedSize) : 0L;
   }

   private void onMiscellaneousProcessAdded(final SparkListenerMiscellaneousProcessAdded processInfoEvent) {
      MiscellaneousProcessDetails processInfo = processInfoEvent.info();
      LiveMiscellaneousProcess miscellaneousProcess = this.getOrCreateOtherProcess(processInfoEvent.processId(), processInfoEvent.time());
      miscellaneousProcess.processLogs_$eq(processInfo.logUrlInfo());
      miscellaneousProcess.hostPort_$eq(processInfo.hostPort());
      miscellaneousProcess.isActive_$eq(true);
      miscellaneousProcess.totalCores_$eq(processInfo.cores());
      this.update(miscellaneousProcess, System.nanoTime(), this.update$default$3());
   }

   private final void StageCompletionTime$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.StageCompletionTime$module == null) {
            this.StageCompletionTime$module = new StageCompletionTime$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$new$5(final AppStatusListener $this, final long now$1, final LiveEntity x$1) {
      $this.update(x$1, now$1, $this.update$default$3());
   }

   // $FF: synthetic method
   public static final void $anonfun$onApplicationStart$3(final AppStatusListener $this, final scala.collection.Map logs$1, final SparkListenerApplicationStart event$1, final LiveExecutor d) {
      d.executorLogs_$eq(logs$1.toMap(scala..less.colon.less..MODULE$.refl()));
      d.attributes_$eq(((IterableOnceOps)event$1.driverAttributes().getOrElse(() -> .MODULE$.Map().empty())).toMap(scala..less.colon.less..MODULE$.refl()));
      $this.update(d, System.nanoTime(), $this.update$default$3());
   }

   // $FF: synthetic method
   public static final void $anonfun$onApplicationStart$2(final AppStatusListener $this, final SparkListenerApplicationStart event$1, final scala.collection.Map logs) {
      Option driver = $this.liveExecutors().get(SparkContext$.MODULE$.DRIVER_IDENTIFIER());
      driver.foreach((d) -> {
         $anonfun$onApplicationStart$3($this, logs, event$1, d);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final int $anonfun$onEnvironmentUpdate$6(final String x$2) {
      return scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(x$2));
   }

   // $FF: synthetic method
   public static final int $anonfun$onExecutorAdded$2(final TaskResourceRequest x$4) {
      return (int)x$4.amount();
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorRemoved$2(final AppStatusListener $this, final LiveExecutor exec$1, final long now$2, final LiveRDD rdd) {
      if (rdd.removeDistribution(exec$1)) {
         $this.update(rdd, now$2, $this.update$default$3());
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onExecutorRemoved$4(final SparkListenerExecutorRemoved event$3, final LiveRDDPartition x$6) {
      return x$6.executors().contains(event$3.executorId());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onExecutorRemoved$6(final SparkListenerExecutorRemoved event$3, final String x$7) {
      return !x$7.equals(event$3.executorId());
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorRemoved$5(final AppStatusListener $this, final LiveRDD rdd$1, final SparkListenerExecutorRemoved event$3, final LiveRDDPartition partition) {
      if (partition.executors().length() == 1) {
         rdd$1.removePartition(partition.blockName());
         rdd$1.memoryUsed_$eq($this.addDeltaToValue(rdd$1.memoryUsed(), partition.memoryUsed() * -1L));
         rdd$1.diskUsed_$eq($this.addDeltaToValue(rdd$1.diskUsed(), partition.diskUsed() * -1L));
      } else {
         rdd$1.memoryUsed_$eq($this.addDeltaToValue(rdd$1.memoryUsed(), partition.memoryUsed() / (long)partition.executors().length() * -1L));
         rdd$1.diskUsed_$eq($this.addDeltaToValue(rdd$1.diskUsed(), partition.diskUsed() / (long)partition.executors().length() * -1L));
         partition.update((Seq)partition.executors().filter((x$7) -> BoxesRunTime.boxToBoolean($anonfun$onExecutorRemoved$6(event$3, x$7))), $this.addDeltaToValue(partition.memoryUsed(), partition.memoryUsed() / (long)partition.executors().length() * -1L), $this.addDeltaToValue(partition.diskUsed(), partition.diskUsed() / (long)partition.executors().length() * -1L));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorRemoved$3(final AppStatusListener $this, final SparkListenerExecutorRemoved event$3, final long now$2, final LiveRDD rdd) {
      ((IterableOnceOps)rdd.getPartitions().values().filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$onExecutorRemoved$4(event$3, x$6)))).foreach((partition) -> {
         $anonfun$onExecutorRemoved$5($this, rdd, event$3, partition);
         return BoxedUnit.UNIT;
      });
      $this.update(rdd, now$2, $this.update$default$3());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isExecutorActiveForLiveStages$1(final LiveExecutor exec$2, final LiveStage stage) {
      return BoxesRunTime.unboxToLong(stage.info().submissionTime().getOrElse((JFunction0.mcJ.sp)() -> 0L)) < exec$2.removeTime().getTime();
   }

   // $FF: synthetic method
   public static final void $anonfun$setStageExcludedStatus$1(final AppStatusListener $this, final LiveStage stage$1, final long now$3, final String executorId) {
      LiveExecutorStageSummary executorStageSummary = stage$1.executorSummary(executorId);
      executorStageSummary.isExcluded_$eq(true);
      $this.maybeUpdate(executorStageSummary, now$3);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateNodeExclusionStatusForStage$2(final String hostId$1, final LiveExecutor exec) {
      boolean var5;
      label32: {
         label24: {
            String var10000 = exec.host();
            if (var10000 == null) {
               if (hostId$1 != null) {
                  break label24;
               }
            } else if (!var10000.equals(hostId$1)) {
               break label24;
            }

            var10000 = exec.executorId();
            String var3 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            if (var10000 == null) {
               if (var3 != null) {
                  break label32;
               }
            } else if (!var10000.equals(var3)) {
               break label32;
            }
         }

         var5 = false;
         return var5;
      }

      var5 = true;
      return var5;
   }

   // $FF: synthetic method
   public static final void $anonfun$updateNodeExclusionStatusForStage$1(final AppStatusListener $this, final String hostId$1, final long now$4, final LiveStage stage) {
      scala.collection.immutable.Seq executorIds = ((IterableOnceOps)((IterableOps)$this.liveExecutors().values().filter((exec) -> BoxesRunTime.boxToBoolean($anonfun$updateNodeExclusionStatusForStage$2(hostId$1, exec)))).map((x$8) -> x$8.executorId())).toSeq();
      $this.setStageExcludedStatus(stage, now$4, executorIds);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateNodeExclusionStatusForStage$4(final String hostId$1, final LiveExecutor exec) {
      boolean var5;
      label32: {
         label24: {
            String var10000 = exec.hostname();
            if (var10000 == null) {
               if (hostId$1 != null) {
                  break label24;
               }
            } else if (!var10000.equals(hostId$1)) {
               break label24;
            }

            var10000 = exec.executorId();
            String var3 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            if (var10000 == null) {
               if (var3 != null) {
                  break label32;
               }
            } else if (!var10000.equals(var3)) {
               break label32;
            }
         }

         var5 = false;
         return var5;
      }

      var5 = true;
      return var5;
   }

   // $FF: synthetic method
   public static final void $anonfun$updateNodeExclusionStatusForStage$5(final AppStatusListener $this, final int stageId$1, final long now$4, final LiveExecutor exec) {
      $this.addExcludedStageTo(exec, stageId$1, now$4);
   }

   // $FF: synthetic method
   public static final void $anonfun$updateExclusionStatusForStage$1(final AppStatusListener $this, final long now$5, final String execId$1, final LiveStage stage) {
      $this.setStageExcludedStatus(stage, now$5, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{execId$1})));
   }

   // $FF: synthetic method
   public static final void $anonfun$updateExclusionStatusForStage$2(final AppStatusListener $this, final int stageId$2, final long now$5, final LiveExecutor exec) {
      $this.addExcludedStageTo(exec, stageId$2, now$5);
   }

   // $FF: synthetic method
   public static final void $anonfun$updateExecExclusionStatus$1(final AppStatusListener $this, final boolean excluded$1, final LiveExecutor exec) {
      $this.updateExecExclusionStatus(exec, excluded$1, System.nanoTime());
   }

   // $FF: synthetic method
   public static final void $anonfun$updateExecExclusionStatus$2(final AppStatusSource x$9) {
      x$9.BLACKLISTED_EXECUTORS().inc();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateExecExclusionStatus$3(final AppStatusSource x$10) {
      x$10.EXCLUDED_EXECUTORS().inc();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateExecExclusionStatus$4(final AppStatusSource x$11) {
      x$11.UNBLACKLISTED_EXECUTORS().inc();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateExecExclusionStatus$5(final AppStatusSource x$12) {
      x$12.UNEXCLUDED_EXECUTORS().inc();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateNodeExcluded$1(final AppStatusListener $this, final String host$1, final boolean excluded$2, final long now$6, final LiveExecutor exec) {
      label29: {
         String var10000 = exec.hostname();
         if (var10000 == null) {
            if (host$1 != null) {
               return;
            }
         } else if (!var10000.equals(host$1)) {
            return;
         }

         var10000 = exec.executorId();
         String var7 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
         if (var10000 == null) {
            if (var7 != null) {
               break label29;
            }
         } else if (!var10000.equals(var7)) {
            break label29;
         }

         return;
      }

      $this.updateExecExclusionStatus(exec, excluded$2, now$6);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onJobStart$1(final StageInfo x$13) {
      return x$13.completionTime().isEmpty();
   }

   // $FF: synthetic method
   public static final int $anonfun$onJobStart$2(final StageInfo x$14) {
      return x$14.numTasks();
   }

   // $FF: synthetic method
   public static final int $anonfun$onJobStart$3(final StageInfo x$15) {
      return x$15.stageId();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onJobStart$11(final String x$18) {
      return !x$18.isEmpty();
   }

   // $FF: synthetic method
   public static final long $anonfun$onJobStart$13(final String x$19) {
      return scala.collection.StringOps..MODULE$.toLong$extension(.MODULE$.augmentString(x$19));
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobStart$14(final AppStatusListener $this, final LiveJob job$1, final SparkListenerJobStart event$4, final long now$7, final StageInfo stageInfo) {
      LiveStage stage = $this.getOrCreateStage(stageInfo);
      stage.jobs_$eq((scala.collection.immutable.Seq)stage.jobs().$colon$plus(job$1));
      stage.jobIds_$eq((Set)stage.jobIds().$plus(BoxesRunTime.boxToInteger(event$4.jobId())));
      $this.liveUpdate(stage, now$7);
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobStart$15(final AppStatusListener $this, final StageInfo stage) {
      RDDOperationGraph graph = RDDOperationGraph$.MODULE$.makeOperationGraph(stage, $this.maxGraphRootNodes());
      RDDOperationGraphWrapper uigraph = new RDDOperationGraphWrapper(stage.stageId(), graph.edges(), graph.outgoingEdges(), graph.incomingEdges(), $this.newRDDOperationCluster(graph.rootCluster()));
      $this.kvstore.write(uigraph);
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$2(final AppStatusListener $this, final LiveStage stage$2, final long now$8, final SchedulerPool pool) {
      pool.stageIds_$eq((Set)pool.stageIds().$minus(BoxesRunTime.boxToInteger(stage$2.info().stageId())));
      $this.update(pool, now$8, $this.update$default$3());
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$3(final AppStatusSource x$20) {
      x$20.SUCCEEDED_JOBS().inc();
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$4(final AppStatusSource x$21) {
      x$21.FAILED_JOBS().inc();
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$7(final AppStatusSource source$1, final Date submissionTime$1, final Date completionTime) {
      source$1.JOB_DURATION().value().set(completionTime.getTime() - submissionTime$1.getTime());
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$6(final LiveJob job$2, final AppStatusSource source$1, final Date submissionTime) {
      job$2.completionTime().foreach((completionTime) -> {
         $anonfun$onJobEnd$7(source$1, submissionTime, completionTime);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$5(final LiveJob job$2, final AppStatusSource source) {
      job$2.submissionTime().foreach((submissionTime) -> {
         $anonfun$onJobEnd$6(job$2, source, submissionTime);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$8(final LiveJob job$2, final AppStatusSource source) {
      source.COMPLETED_STAGES().inc((long)job$2.completedStages().size());
      source.FAILED_STAGES().inc((long)job$2.failedStages());
      source.COMPLETED_TASKS().inc((long)job$2.completedTasks());
      source.FAILED_TASKS().inc((long)job$2.failedTasks());
      source.KILLED_TASKS().inc((long)job$2.killedTasks());
      source.SKIPPED_TASKS().inc((long)job$2.skippedTasks());
      source.SKIPPED_STAGES().inc((long)job$2.skippedStages().size());
   }

   // $FF: synthetic method
   public static final void $anonfun$onJobEnd$1(final AppStatusListener $this, final SparkListenerJobEnd event$5, final LiveJob job) {
      long now = System.nanoTime();
      Iterator it = $this.liveStages().entrySet().iterator();

      while(it.hasNext()) {
         Map.Entry e = (Map.Entry)it.next();
         if (job.stageIds().contains(BoxesRunTime.boxToInteger(((Tuple2)e.getKey())._1$mcI$sp()))) {
            LiveStage stage = (LiveStage)e.getValue();
            if (StageStatus.PENDING.equals(stage.status())) {
               stage.status_$eq(StageStatus.SKIPPED);
               job.skippedStages_$eq((Set)job.skippedStages().$plus(BoxesRunTime.boxToInteger(stage.info().stageId())));
               job.skippedTasks_$eq(job.skippedTasks() + stage.info().numTasks());
               $this.pools().get(stage.schedulingPool()).foreach((pool) -> {
                  $anonfun$onJobEnd$2($this, stage, now, pool);
                  return BoxedUnit.UNIT;
               });
               it.remove();
               $this.update(stage, now, true);
            }
         }
      }

      JobResult var9 = event$5.jobResult();
      JobExecutionStatus var10001;
      if (JobSucceeded$.MODULE$.equals(var9)) {
         $this.appStatusSource.foreach((x$20) -> {
            $anonfun$onJobEnd$3(x$20);
            return BoxedUnit.UNIT;
         });
         var10001 = JobExecutionStatus.SUCCEEDED;
      } else {
         if (!(var9 instanceof JobFailed)) {
            throw new MatchError(var9);
         }

         $this.appStatusSource.foreach((x$21) -> {
            $anonfun$onJobEnd$4(x$21);
            return BoxedUnit.UNIT;
         });
         var10001 = JobExecutionStatus.FAILED;
      }

      label39: {
         job.status_$eq(var10001);
         job.completionTime_$eq((Option)(event$5.time() > 0L ? new Some(new Date(event$5.time())) : scala.None..MODULE$));
         $this.appStatusSource.foreach((source) -> {
            $anonfun$onJobEnd$5(job, source);
            return BoxedUnit.UNIT;
         });
         $this.appStatusSource.foreach((source) -> {
            $anonfun$onJobEnd$8(job, source);
            return BoxedUnit.UNIT;
         });
         $this.update(job, now, true);
         JobExecutionStatus var10000 = job.status();
         JobExecutionStatus var10 = JobExecutionStatus.SUCCEEDED;
         if (var10000 == null) {
            if (var10 == null) {
               break label39;
            }
         } else if (var10000.equals(var10)) {
            break label39;
         }

         return;
      }

      $this.appSummary_$eq(new AppSummary($this.appSummary().numCompletedJobs() + 1, $this.appSummary().numCompletedStages()));
      $this.kvstore.write($this.appSummary());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onStageSubmitted$3(final SparkListenerStageSubmitted event$6, final LiveJob x$22) {
      return x$22.stageIds().contains(BoxesRunTime.boxToInteger(event$6.stageInfo().stageId()));
   }

   // $FF: synthetic method
   public static final int $anonfun$onStageSubmitted$4(final LiveJob x$23) {
      return x$23.jobId();
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageSubmitted$6(final AppStatusListener $this, final SparkListenerStageSubmitted event$6, final long now$9, final LiveJob job) {
      job.completedStages_$eq((Set)job.completedStages().$minus(BoxesRunTime.boxToInteger(event$6.stageInfo().stageId())));
      job.activeStages_$eq(job.activeStages() + 1);
      $this.liveUpdate(job, now$9);
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageSubmitted$8(final AppStatusListener $this, final long now$9, final RDDInfo info) {
      if (info.storageLevel().isValid()) {
         $this.liveUpdate((LiveEntity)$this.liveRDDs().getOrElseUpdate(BoxesRunTime.boxToInteger(info.id()), () -> new LiveRDD(info, info.storageLevel())), now$9);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onTaskStart$3(final AppStatusListener $this, final long now$10, final LiveJob job) {
      job.activeTasks_$eq(job.activeTasks() + 1);
      $this.maybeUpdate(job, now$10);
   }

   // $FF: synthetic method
   public static final void $anonfun$onTaskStart$1(final AppStatusListener $this, final SparkListenerTaskStart event$7, final long now$10, final LiveStage stage) {
      if (event$7.taskInfo().speculative()) {
         stage.speculationStageSummary().numActiveTasks_$eq(stage.speculationStageSummary().numActiveTasks() + 1);
         stage.speculationStageSummary().numTasks_$eq(stage.speculationStageSummary().numTasks() + 1);
         $this.update(stage.speculationStageSummary(), now$10, $this.update$default$3());
      }

      stage.activeTasks_$eq(stage.activeTasks() + 1);
      stage.firstLaunchTime_$eq(scala.math.package..MODULE$.min(stage.firstLaunchTime(), event$7.taskInfo().launchTime()));
      String locality = event$7.taskInfo().taskLocality().toString();
      long count = BoxesRunTime.unboxToLong(stage.localitySummary().getOrElse(locality, (JFunction0.mcJ.sp)() -> 0L)) + 1L;
      stage.localitySummary_$eq((scala.collection.immutable.Map)stage.localitySummary().$plus$plus((IterableOnce).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(locality), BoxesRunTime.boxToLong(count))})))));
      stage.activeTasksPerExecutor().update(event$7.taskInfo().executorId(), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(stage.activeTasksPerExecutor().apply(event$7.taskInfo().executorId())) + 1));
      $this.maybeUpdate(stage, now$10);
      stage.jobs().foreach((job) -> {
         $anonfun$onTaskStart$3($this, now$10, job);
         return BoxedUnit.UNIT;
      });
      if (stage.savedTasks().incrementAndGet() > $this.maxTasksPerStage() && !stage.cleaning()) {
         stage.cleaning_$eq(true);
         $this.kvstore.doAsync((JFunction0.mcV.sp)() -> $this.cleanupTasks(stage));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onTaskStart$5(final AppStatusListener $this, final long now$10, final LiveExecutor exec) {
      exec.activeTasks_$eq(exec.activeTasks() + 1);
      exec.totalTasks_$eq(exec.totalTasks() + 1);
      $this.maybeUpdate(exec, now$10);
   }

   // $FF: synthetic method
   public static final void $anonfun$onTaskGettingResult$1(final AppStatusListener $this, final LiveTask task) {
      $this.maybeUpdate(task, System.nanoTime());
   }

   // $FF: synthetic method
   public static final void $anonfun$onTaskEnd$4(final AppStatusListener $this, final int activeDelta$1, final int completedDelta$1, final long taskIndex$1, final int failedDelta$1, final int killedDelta$1, final SparkListenerTaskEnd event$8, final boolean removeStage$1, final long now$11, final LiveJob job) {
      job.activeTasks_$eq(job.activeTasks() - activeDelta$1);
      job.completedTasks_$eq(job.completedTasks() + completedDelta$1);
      if (completedDelta$1 > 0) {
         job.completedIndices().add$mcJ$sp(taskIndex$1);
      }

      job.failedTasks_$eq(job.failedTasks() + failedDelta$1);
      job.killedTasks_$eq(job.killedTasks() + killedDelta$1);
      if (killedDelta$1 > 0) {
         job.killedSummary_$eq($this.killedTasksSummary(event$8.reason(), job.killedSummary()));
      }

      if (removeStage$1) {
         $this.update(job, now$11, $this.update$default$3());
      } else {
         $this.maybeUpdate(job, now$11);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onTaskEnd$6(final AppStatusListener $this, final int activeDelta$1, final int completedDelta$1, final int failedDelta$1, final SparkListenerTaskEnd event$8, final long now$11, final LiveExecutor exec) {
      label21: {
         exec.activeTasks_$eq(exec.activeTasks() - activeDelta$1);
         exec.completedTasks_$eq(exec.completedTasks() + completedDelta$1);
         exec.failedTasks_$eq(exec.failedTasks() + failedDelta$1);
         exec.totalDuration_$eq(exec.totalDuration() + event$8.taskInfo().duration());
         exec.peakExecutorMetrics().compareAndUpdatePeakValues(event$8.taskExecutorMetrics());
         TaskEndReason var10000 = event$8.reason();
         Resubmitted$ var8 = Resubmitted$.MODULE$;
         if (var10000 == null) {
            if (var8 == null) {
               break label21;
            }
         } else if (var10000.equals(var8)) {
            break label21;
         }

         if (event$8.taskMetrics() != null) {
            ShuffleReadMetrics readMetrics = event$8.taskMetrics().shuffleReadMetrics();
            exec.totalGcTime_$eq(exec.totalGcTime() + event$8.taskMetrics().jvmGCTime());
            exec.totalInputBytes_$eq(exec.totalInputBytes() + event$8.taskMetrics().inputMetrics().bytesRead());
            exec.totalShuffleRead_$eq(exec.totalShuffleRead() + readMetrics.localBytesRead() + readMetrics.remoteBytesRead());
            exec.totalShuffleWrite_$eq(exec.totalShuffleWrite() + event$8.taskMetrics().shuffleWriteMetrics().bytesWritten());
         }
      }

      if (exec.activeTasks() == 0) {
         $this.update(exec, now$11, $this.update$default$3());
      } else {
         $this.maybeUpdate(exec, now$11);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageCompleted$2(final AppStatusListener $this, final long now$12, final LiveExecutorStageSummary x$25) {
      $this.update(x$25, now$12, $this.update$default$3());
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageCompleted$3(final AppStatusListener $this, final LiveStage stage$6, final SparkListenerStageCompleted event$9, final long now$12, final LiveJob job) {
      StageStatus var7 = stage$6.status();
      if (StageStatus.COMPLETE.equals(var7)) {
         job.completedStages_$eq((Set)job.completedStages().$plus(BoxesRunTime.boxToInteger(event$9.stageInfo().stageId())));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (StageStatus.SKIPPED.equals(var7)) {
         job.skippedStages_$eq((Set)job.skippedStages().$plus(BoxesRunTime.boxToInteger(event$9.stageInfo().stageId())));
         job.skippedTasks_$eq(job.skippedTasks() + event$9.stageInfo().numTasks());
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else {
         job.failedStages_$eq(job.failedStages() + 1);
         BoxedUnit var9 = BoxedUnit.UNIT;
      }

      job.activeStages_$eq(job.activeStages() - 1);
      $this.liveUpdate(job, now$12);
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageCompleted$4(final AppStatusListener $this, final SparkListenerStageCompleted event$9, final long now$12, final SchedulerPool pool) {
      pool.stageIds_$eq((Set)pool.stageIds().$minus(BoxesRunTime.boxToInteger(event$9.stageInfo().stageId())));
      $this.update(pool, now$12, $this.update$default$3());
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageCompleted$6(final AppStatusListener $this, final SparkListenerStageCompleted event$9, final long now$12, final LiveExecutor exec) {
      $this.removeExcludedStageFrom(exec, event$9.stageInfo().stageId(), now$12);
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageCompleted$5(final AppStatusListener $this, final SparkListenerStageCompleted event$9, final long now$12, final String executorId) {
      $this.liveExecutors().get(executorId).foreach((exec) -> {
         $anonfun$onStageCompleted$6($this, event$9, now$12, exec);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageCompleted$1(final AppStatusListener $this, final SparkListenerStageCompleted event$9, final LiveStage stage) {
      long now = System.nanoTime();
      stage.info_$eq(event$9.stageInfo());
      stage.executorSummaries().values().foreach((x$25) -> {
         $anonfun$onStageCompleted$2($this, now, x$25);
         return BoxedUnit.UNIT;
      });
      Option var6 = event$9.stageInfo().failureReason();
      stage.status_$eq(var6 instanceof Some ? StageStatus.FAILED : (event$9.stageInfo().submissionTime().isDefined() ? StageStatus.COMPLETE : StageStatus.SKIPPED));
      stage.jobs().foreach((job) -> {
         $anonfun$onStageCompleted$3($this, stage, event$9, now, job);
         return BoxedUnit.UNIT;
      });
      $this.pools().get(stage.schedulingPool()).foreach((pool) -> {
         $anonfun$onStageCompleted$4($this, event$9, now, pool);
         return BoxedUnit.UNIT;
      });
      HashSet executorIdsForStage = stage.excludedExecutors();
      executorIdsForStage.foreach((executorId) -> {
         $anonfun$onStageCompleted$5($this, event$9, now, executorId);
         return BoxedUnit.UNIT;
      });
      boolean removeStage = stage.activeTasks() == 0;
      $this.update(stage, now, removeStage);
      if (removeStage) {
         $this.liveStages().remove(new Tuple2.mcII.sp(event$9.stageInfo().stageId(), event$9.stageInfo().attemptNumber()));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      label34: {
         StageStatus var10 = stage.status();
         StageStatus var9 = StageStatus.COMPLETE;
         if (var10 == null) {
            if (var9 == null) {
               break label34;
            }
         } else if (var10.equals(var9)) {
            break label34;
         }

         return;
      }

      $this.appSummary_$eq(new AppSummary($this.appSummary().numCompletedJobs(), $this.appSummary().numCompletedStages() + 1));
      $this.kvstore.write($this.appSummary());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onStageCompleted$7(final AppStatusListener $this, final String execId, final LiveExecutor exec) {
      return $this.isExecutorActiveForLiveStages(exec);
   }

   // $FF: synthetic method
   public static final void $anonfun$onUnpersistRDD$4(final LiveExecutor exec) {
      exec.rddBlocks_$eq(exec.rddBlocks() - 1);
   }

   // $FF: synthetic method
   public static final void $anonfun$onUnpersistRDD$3(final AppStatusListener $this, final String executorId) {
      $this.liveExecutors().get(executorId).foreach((exec) -> {
         $anonfun$onUnpersistRDD$4(exec);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$onUnpersistRDD$2(final AppStatusListener $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         LiveRDDPartition part = (LiveRDDPartition)x0$1._2();
         part.executors().foreach((executorId) -> {
            $anonfun$onUnpersistRDD$3($this, executorId);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onUnpersistRDD$6(final AppStatusListener $this, final StorageLevel storageLevel$1, final LiveRDDDistribution rddDist$1, final long now$13, final LiveExecutor exec) {
      if (exec.hasMemoryInfo()) {
         if (storageLevel$1.useOffHeap()) {
            exec.usedOffHeap_$eq($this.addDeltaToValue(exec.usedOffHeap(), -rddDist$1.offHeapUsed()));
         } else {
            exec.usedOnHeap_$eq($this.addDeltaToValue(exec.usedOnHeap(), -rddDist$1.onHeapUsed()));
         }
      }

      exec.memoryUsed_$eq($this.addDeltaToValue(exec.memoryUsed(), -rddDist$1.memoryUsed()));
      exec.diskUsed_$eq($this.addDeltaToValue(exec.diskUsed(), -rddDist$1.diskUsed()));
      $this.maybeUpdate(exec, now$13);
   }

   // $FF: synthetic method
   public static final void $anonfun$onUnpersistRDD$5(final AppStatusListener $this, final StorageLevel storageLevel$1, final long now$13, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String executorId = (String)x0$2._1();
         LiveRDDDistribution rddDist = (LiveRDDDistribution)x0$2._2();
         $this.liveExecutors().get(executorId).foreach((exec) -> {
            $anonfun$onUnpersistRDD$6($this, storageLevel$1, rddDist, now$13, exec);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onUnpersistRDD$1(final AppStatusListener $this, final LiveRDD liveRDD) {
      StorageLevel storageLevel = liveRDD.info().storageLevel();
      liveRDD.getPartitions().foreach((x0$1) -> {
         $anonfun$onUnpersistRDD$2($this, x0$1);
         return BoxedUnit.UNIT;
      });
      long now = System.nanoTime();
      liveRDD.getDistributions().foreach((x0$2) -> {
         $anonfun$onUnpersistRDD$5($this, storageLevel, now, x0$2);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorMetricsUpdate$3(final AppStatusListener $this, final TaskMetrics delta$1, final long now$14, final SparkListenerExecutorMetricsUpdate event$11, final LiveStage stage) {
      stage.metrics_$eq(LiveEntityHelpers$.MODULE$.addMetrics(stage.metrics(), delta$1));
      $this.maybeUpdate(stage, now$14);
      LiveExecutorStageSummary esummary = stage.executorSummary(event$11.execId());
      esummary.metrics_$eq(LiveEntityHelpers$.MODULE$.addMetrics(esummary.metrics(), delta$1));
      $this.maybeUpdate(esummary, now$14);
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorMetricsUpdate$2(final AppStatusListener $this, final scala.collection.immutable.Seq accumUpdates$1, final long now$14, final int sid$1, final int sAttempt$1, final SparkListenerExecutorMetricsUpdate event$11, final LiveTask task) {
      org.apache.spark.executor.TaskMetrics metrics = TaskMetrics$.MODULE$.fromAccumulatorInfos(accumUpdates$1);
      TaskMetrics delta = task.updateMetrics(metrics);
      $this.maybeUpdate(task, now$14);
      scala.Option..MODULE$.apply($this.liveStages().get(new Tuple2.mcII.sp(sid$1, sAttempt$1))).foreach((stage) -> {
         $anonfun$onExecutorMetricsUpdate$3($this, delta, now$14, event$11, stage);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorMetricsUpdate$1(final AppStatusListener $this, final long now$14, final SparkListenerExecutorMetricsUpdate event$11, final Tuple4 x0$1) {
      if (x0$1 != null) {
         long taskId = BoxesRunTime.unboxToLong(x0$1._1());
         int sid = BoxesRunTime.unboxToInt(x0$1._2());
         int sAttempt = BoxesRunTime.unboxToInt(x0$1._3());
         scala.collection.immutable.Seq accumUpdates = (scala.collection.immutable.Seq)x0$1._4();
         $this.liveTasks().get(BoxesRunTime.boxToLong(taskId)).foreach((task) -> {
            $anonfun$onExecutorMetricsUpdate$2($this, accumUpdates, now$14, sid, sAttempt, event$11, task);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorMetricsUpdate$5(final AppStatusListener $this, final ExecutorMetrics peakUpdates$1, final long now$14, final LiveExecutor exec) {
      if (exec.peakExecutorMetrics().compareAndUpdatePeakValues(peakUpdates$1)) {
         $this.update(exec, now$14, $this.update$default$3());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorMetricsUpdate$4(final AppStatusListener $this, final SparkListenerExecutorMetricsUpdate event$11, final long now$14, final Tuple2 x0$2) {
      if (x0$2 != null) {
         Tuple2 key = (Tuple2)x0$2._1();
         ExecutorMetrics peakUpdates = (ExecutorMetrics)x0$2._2();
         $this.liveExecutors().get(event$11.execId()).foreach((exec) -> {
            $anonfun$onExecutorMetricsUpdate$5($this, peakUpdates, now$14, exec);
            return BoxedUnit.UNIT;
         });
         $this.updateStageLevelPeakExecutorMetrics(key._1$mcI$sp(), key._2$mcI$sp(), event$11.execId(), peakUpdates, now$14);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onExecutorMetricsUpdate$6(final AppStatusListener $this, final long now$14, final LiveEntity x$27) {
      $this.maybeUpdate(x$27, now$14);
   }

   // $FF: synthetic method
   public static final void $anonfun$onStageExecutorMetrics$2(final AppStatusListener $this, final SparkListenerStageExecutorMetrics event$12, final long now$15, final LiveExecutor exec) {
      if (exec.peakExecutorMetrics().compareAndUpdatePeakValues(event$12.executorMetrics())) {
         $this.update(exec, now$15, $this.update$default$3());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$updateStageLevelPeakExecutorMetrics$1(final AppStatusListener $this, final ExecutorMetrics executorMetrics$1, final long now$16, final String executorId$1, final LiveStage stage) {
      if (stage.peakExecutorMetrics().compareAndUpdatePeakValues(executorMetrics$1)) {
         $this.update(stage, now$16, $this.update$default$3());
      }

      LiveExecutorStageSummary esummary = stage.executorSummary(executorId$1);
      if (esummary.peakExecutorMetrics().compareAndUpdatePeakValues(executorMetrics$1)) {
         $this.update(esummary, now$16, $this.update$default$3());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$flush$1(final Function1 entityFlushFunc$1, final LiveStage stage) {
      entityFlushFunc$1.apply(stage);
      stage.executorSummaries().values().foreach(entityFlushFunc$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$activeStages$2(final StageInfo x$28) {
      return x$28.submissionTime().isDefined();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$activeStages$1(final LiveStage s) {
      return scala.Option..MODULE$.apply(s.info()).exists((x$28) -> BoxesRunTime.boxToBoolean($anonfun$activeStages$2(x$28)));
   }

   // $FF: synthetic method
   public static final int $anonfun$activeStages$4(final StageData x$30) {
      return x$30.stageId();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateRDDBlock$1(final AppStatusListener $this, final StorageLevel storageLevel$2, final long memoryDelta$1, final long diskDelta$1, final LiveExecutor exec) {
      $this.updateExecutorMemoryDiskInfo(exec, storageLevel$2, memoryDelta$1, diskDelta$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateRDDBlock$3(final String executorId$2, final String x$31) {
      boolean var10000;
      label23: {
         if (x$31 == null) {
            if (executorId$2 != null) {
               break label23;
            }
         } else if (!x$31.equals(executorId$2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$updateRDDBlock$6(final AppStatusListener $this, final LiveRDD otherRdd$1, final long now$17, final LiveRDDDistribution dist) {
      dist.lastUpdate_$eq((RDDDataDistribution)null);
      $this.update(otherRdd$1, now$17, $this.update$default$3());
   }

   // $FF: synthetic method
   public static final void $anonfun$updateRDDBlock$5(final AppStatusListener $this, final RDDBlockId block$1, final LiveExecutor exec$4, final long now$17, final LiveRDD otherRdd) {
      if (otherRdd.info().id() != block$1.rddId()) {
         otherRdd.distributionOpt(exec$4).foreach((dist) -> {
            $anonfun$updateRDDBlock$6($this, otherRdd, now$17, dist);
            return BoxedUnit.UNIT;
         });
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$updateRDDBlock$4(final AppStatusListener $this, final IntRef rddBlocksDelta$1, final LiveRDD rdd$2, final long memoryDelta$1, final long diskDelta$1, final StorageLevel storageLevel$2, final RDDBlockId block$1, final long now$17, final LiveExecutor exec) {
      if (exec.rddBlocks() + rddBlocksDelta$1.elem > 0) {
         LiveRDDDistribution dist = rdd$2.distribution(exec);
         dist.memoryUsed_$eq($this.addDeltaToValue(dist.memoryUsed(), memoryDelta$1));
         dist.diskUsed_$eq($this.addDeltaToValue(dist.diskUsed(), diskDelta$1));
         if (exec.hasMemoryInfo()) {
            if (storageLevel$2.useOffHeap()) {
               dist.offHeapUsed_$eq($this.addDeltaToValue(dist.offHeapUsed(), memoryDelta$1));
            } else {
               dist.onHeapUsed_$eq($this.addDeltaToValue(dist.onHeapUsed(), memoryDelta$1));
            }
         }

         dist.lastUpdate_$eq((RDDDataDistribution)null);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxesRunTime.boxToBoolean(rdd$2.removeDistribution(exec));
      }

      $this.liveRDDs().values().foreach((otherRdd) -> {
         $anonfun$updateRDDBlock$5($this, block$1, exec, now$17, otherRdd);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$updateRDDBlock$2(final AppStatusListener $this, final RDDBlockId block$1, final StorageLevel storageLevel$2, final String executorId$2, final IntRef rddBlocksDelta$1, final long memoryDelta$1, final long diskDelta$1, final Option maybeExec$1, final long now$17, final LiveRDD rdd) {
      LiveRDDPartition partition = rdd.partition(block$1.name());
      Seq var10000;
      if (storageLevel$2.isValid()) {
         Seq current = partition.executors();
         if (current.contains(executorId$2)) {
            var10000 = current;
         } else {
            rddBlocksDelta$1.elem = 1;
            var10000 = (Seq)current.$colon$plus(executorId$2);
         }
      } else {
         rddBlocksDelta$1.elem = -1;
         var10000 = (Seq)partition.executors().filter((x$31) -> BoxesRunTime.boxToBoolean($anonfun$updateRDDBlock$3(executorId$2, x$31)));
      }

      Seq executors = var10000;
      if (executors.nonEmpty()) {
         partition.update(executors, $this.addDeltaToValue(partition.memoryUsed(), memoryDelta$1), $this.addDeltaToValue(partition.diskUsed(), diskDelta$1));
      } else {
         rdd.removePartition(block$1.name());
      }

      maybeExec$1.foreach((exec) -> {
         $anonfun$updateRDDBlock$4($this, rddBlocksDelta$1, rdd, memoryDelta$1, diskDelta$1, storageLevel$2, block$1, now$17, exec);
         return BoxedUnit.UNIT;
      });
      rdd.memoryUsed_$eq($this.addDeltaToValue(rdd.memoryUsed(), memoryDelta$1));
      rdd.diskUsed_$eq($this.addDeltaToValue(rdd.diskUsed(), diskDelta$1));
      $this.update(rdd, now$17, $this.update$default$3());
   }

   // $FF: synthetic method
   public static final void $anonfun$updateRDDBlock$7(final AppStatusListener $this, final IntRef rddBlocksDelta$1, final long now$17, final LiveExecutor exec) {
      exec.rddBlocks_$eq(exec.rddBlocks() + rddBlocksDelta$1.elem);
      $this.maybeUpdate(exec, now$17);
   }

   // $FF: synthetic method
   public static final void $anonfun$updateBroadcastBlock$1(final AppStatusListener $this, final SparkListenerBlockUpdated event$13, final LiveExecutor exec) {
      long now = System.nanoTime();
      StorageLevel storageLevel = event$13.blockUpdatedInfo().storageLevel();
      long diskDelta = event$13.blockUpdatedInfo().diskSize() * (long)(storageLevel.useDisk() ? 1 : -1);
      long memoryDelta = event$13.blockUpdatedInfo().memSize() * (long)(storageLevel.useMemory() ? 1 : -1);
      $this.updateExecutorMemoryDiskInfo(exec, storageLevel, memoryDelta, diskDelta);
      $this.maybeUpdate(exec, now);
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupExecutors$1(final AppStatusListener $this, final ExecutorSummaryWrapper e) {
      $this.kvstore.delete(e.getClass(), e.info().id());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupJobs$1(final JobDataWrapper j) {
      boolean var4;
      label32: {
         label24: {
            JobExecutionStatus var10000 = j.info().status();
            JobExecutionStatus var1 = JobExecutionStatus.RUNNING;
            if (var10000 == null) {
               if (var1 == null) {
                  break label24;
               }
            } else if (var10000.equals(var1)) {
               break label24;
            }

            var10000 = j.info().status();
            JobExecutionStatus var2 = JobExecutionStatus.UNKNOWN;
            if (var10000 == null) {
               if (var2 != null) {
                  break label32;
               }
            } else if (!var10000.equals(var2)) {
               break label32;
            }
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupJobs$2(final AppStatusListener $this, final JobDataWrapper j) {
      $this.kvstore.delete(j.getClass(), BoxesRunTime.boxToInteger(j.info().jobId()));
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupStagesWithInMemoryStore$1(final AppStatusListener $this, final HashMap stageDataCount$1, final ArrayBuffer stageArray$1, final StageDataWrapper s) {
      if (stageDataCount$1.contains(BoxesRunTime.boxToInteger(s.info().stageId()))) {
         stageDataCount$1.update(BoxesRunTime.boxToInteger(s.info().stageId()), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(stageDataCount$1.apply(BoxesRunTime.boxToInteger(s.info().stageId()))) + 1));
      } else {
         stageDataCount$1.update(BoxesRunTime.boxToInteger(s.info().stageId()), BoxesRunTime.boxToInteger(1));
      }

      label36: {
         StageStatus var10000 = s.info().status();
         StageStatus var4 = StageStatus.ACTIVE;
         if (var10000 == null) {
            if (var4 == null) {
               return;
            }
         } else if (var10000.equals(var4)) {
            return;
         }

         var10000 = s.info().status();
         StageStatus var5 = StageStatus.PENDING;
         if (var10000 == null) {
            if (var5 != null) {
               break label36;
            }
         } else if (!var10000.equals(var5)) {
            break label36;
         }

         return;
      }

      StageCompletionTime candidate = $this.new StageCompletionTime(s.info().stageId(), s.info().attemptId(), s.completionTime());
      stageArray$1.append(candidate);
   }

   // $FF: synthetic method
   public static final long $anonfun$cleanupStagesWithInMemoryStore$2(final StageCompletionTime x$33) {
      return x$33.completionTime();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupStagesInKVStore$1(final StageDataWrapper s) {
      boolean var4;
      label32: {
         label24: {
            StageStatus var10000 = s.info().status();
            StageStatus var1 = StageStatus.ACTIVE;
            if (var10000 == null) {
               if (var1 == null) {
                  break label24;
               }
            } else if (var10000.equals(var1)) {
               break label24;
            }

            var10000 = s.info().status();
            StageStatus var2 = StageStatus.PENDING;
            if (var10000 == null) {
               if (var2 != null) {
                  break label32;
               }
            } else if (!var10000.equals(var2)) {
               break label32;
            }
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupStagesInKVStore$3(final StageDataWrapper s$1, final StageDataWrapper other) {
      return other.info().attemptId() != s$1.info().attemptId();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanupTasks$1(final AppStatusListener $this, final TaskDataWrapper t) {
      boolean var3;
      if ($this.live) {
         label29: {
            String var10000 = t.status();
            String var2 = TaskState$.MODULE$.RUNNING().toString();
            if (var10000 == null) {
               if (var2 != null) {
                  break label29;
               }
            } else if (!var10000.equals(var2)) {
               break label29;
            }

            var3 = false;
            return var3;
         }
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupTasks$2(final AppStatusListener $this, final TaskDataWrapper t) {
      $this.kvstore.delete(t.getClass(), t.taskId());
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupTasks$3(final AppStatusListener $this, final TaskDataWrapper t) {
      $this.kvstore.delete(t.getClass(), t.taskId());
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupCachedQuantiles$1(final AppStatusListener $this, final CachedQuantile q) {
      $this.kvstore.delete(q.getClass(), q.id());
   }

   public AppStatusListener(final ElementTrackingStore kvstore, final SparkConf conf, final boolean live, final Option appStatusSource, final Option lastUpdateTime) {
      this.kvstore = kvstore;
      this.conf = conf;
      this.live = live;
      this.appStatusSource = appStatusSource;
      this.lastUpdateTime = lastUpdateTime;
      Logging.$init$(this);
      this.sparkVersion = org.apache.spark.package$.MODULE$.SPARK_VERSION();
      this.appInfo = null;
      this.appSummary = new AppSummary(0, 0);
      this.defaultCpusPerTask = 1;
      this.liveUpdatePeriodNs = live ? BoxesRunTime.unboxToLong(conf.get(Status$.MODULE$.LIVE_ENTITY_UPDATE_PERIOD())) : -1L;
      this.liveUpdateMinFlushPeriod = BoxesRunTime.unboxToLong(conf.get(Status$.MODULE$.LIVE_ENTITY_UPDATE_MIN_FLUSH_PERIOD()));
      this.maxTasksPerStage = BoxesRunTime.unboxToInt(conf.get(Status$.MODULE$.MAX_RETAINED_TASKS_PER_STAGE()));
      this.maxGraphRootNodes = BoxesRunTime.unboxToInt(conf.get(Status$.MODULE$.MAX_RETAINED_ROOT_NODES()));
      this.liveStages = new ConcurrentHashMap();
      this.liveJobs = new HashMap();
      this.liveExecutors = new HashMap();
      this.deadExecutors = new HashMap();
      this.liveTasks = new HashMap();
      this.liveRDDs = new HashMap();
      this.pools = new HashMap();
      this.liveResourceProfiles = new HashMap();
      this.liveMiscellaneousProcess = new HashMap();
      this.SQL_EXECUTION_ID_KEY = "spark.sql.execution.id";
      this.activeExecutorCount = 0;
      this.lastFlushTimeNs = System.nanoTime();
      kvstore.addTrigger(ExecutorSummaryWrapper.class, (long)BoxesRunTime.unboxToInt(conf.get(Status$.MODULE$.MAX_RETAINED_DEAD_EXECUTORS())), (JFunction1.mcVJ.sp)(count) -> this.cleanupExecutors(count));
      kvstore.addTrigger(JobDataWrapper.class, (long)BoxesRunTime.unboxToInt(conf.get(Status$.MODULE$.MAX_RETAINED_JOBS())), (JFunction1.mcVJ.sp)(count) -> this.cleanupJobs(count));
      kvstore.addTrigger(StageDataWrapper.class, (long)BoxesRunTime.unboxToInt(conf.get(Status$.MODULE$.MAX_RETAINED_STAGES())), (JFunction1.mcVJ.sp)(count) -> this.cleanupStages(count));
      kvstore.onFlush((JFunction0.mcV.sp)() -> {
         if (!this.live) {
            long now = System.nanoTime();
            this.flush((x$1) -> {
               $anonfun$new$5(this, now, x$1);
               return BoxedUnit.UNIT;
            });
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class StageCompletionTime implements Product, Serializable {
      private final int stageId;
      private final int attemptId;
      private final long completionTime;
      // $FF: synthetic field
      public final AppStatusListener $outer;

      public scala.collection.Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int stageId() {
         return this.stageId;
      }

      public int attemptId() {
         return this.attemptId;
      }

      public long completionTime() {
         return this.completionTime;
      }

      public StageCompletionTime copy(final int stageId, final int attemptId, final long completionTime) {
         return this.org$apache$spark$status$AppStatusListener$StageCompletionTime$$$outer().new StageCompletionTime(stageId, attemptId, completionTime);
      }

      public int copy$default$1() {
         return this.stageId();
      }

      public int copy$default$2() {
         return this.attemptId();
      }

      public long copy$default$3() {
         return this.completionTime();
      }

      public String productPrefix() {
         return "StageCompletionTime";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.stageId());
            }
            case 1 -> {
               return BoxesRunTime.boxToInteger(this.attemptId());
            }
            case 2 -> {
               return BoxesRunTime.boxToLong(this.completionTime());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public scala.collection.Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof StageCompletionTime;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "stageId";
            }
            case 1 -> {
               return "attemptId";
            }
            case 2 -> {
               return "completionTime";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.stageId());
         var1 = Statics.mix(var1, this.attemptId());
         var1 = Statics.mix(var1, Statics.longHash(this.completionTime()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label45: {
               if (x$1 instanceof StageCompletionTime && ((StageCompletionTime)x$1).org$apache$spark$status$AppStatusListener$StageCompletionTime$$$outer() == this.org$apache$spark$status$AppStatusListener$StageCompletionTime$$$outer()) {
                  StageCompletionTime var4 = (StageCompletionTime)x$1;
                  if (this.stageId() == var4.stageId() && this.attemptId() == var4.attemptId() && this.completionTime() == var4.completionTime() && var4.canEqual(this)) {
                     break label45;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public AppStatusListener org$apache$spark$status$AppStatusListener$StageCompletionTime$$$outer() {
         return this.$outer;
      }

      public StageCompletionTime(final int stageId, final int attemptId, final long completionTime) {
         this.stageId = stageId;
         this.attemptId = attemptId;
         this.completionTime = completionTime;
         if (AppStatusListener.this == null) {
            throw null;
         } else {
            this.$outer = AppStatusListener.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class StageCompletionTime$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final AppStatusListener $outer;

      public final String toString() {
         return "StageCompletionTime";
      }

      public StageCompletionTime apply(final int stageId, final int attemptId, final long completionTime) {
         return this.$outer.new StageCompletionTime(stageId, attemptId, completionTime);
      }

      public Option unapply(final StageCompletionTime x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.stageId()), BoxesRunTime.boxToInteger(x$0.attemptId()), BoxesRunTime.boxToLong(x$0.completionTime()))));
      }

      public StageCompletionTime$() {
         if (AppStatusListener.this == null) {
            throw null;
         } else {
            this.$outer = AppStatusListener.this;
            super();
         }
      }
   }
}
