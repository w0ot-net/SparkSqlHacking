package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateResponse;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.api.records.ContainerStatus;
import org.apache.hadoop.yarn.api.records.NodeReport;
import org.apache.hadoop.yarn.api.records.NodeState;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.client.api.AMRMClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.ExecutorFailureTracker;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.resource.ExecutorResourceRequest;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile.;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.scheduler.ExecutorExited;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.StringOps;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\u001ddA\u0002?~\u0001u\fy\u0001\u0003\u0006\u0002*\u0001\u0011\t\u0011)A\u0005\u0003[A!\"a\u0011\u0001\u0005\u0003\u0005\u000b\u0011BA#\u0011)\t\t\u0006\u0001B\u0001B\u0003%\u00111\u000b\u0005\u000b\u0003G\u0002!\u0011!Q\u0001\n\u0005\u0015\u0004BCA7\u0001\t\u0005\t\u0015!\u0003\u0002p!Q\u0011Q\u0015\u0001\u0003\u0002\u0003\u0006I!a*\t\u0015\u0005U\u0006A!A!\u0002\u0013\t9\f\u0003\u0006\u0002>\u0002\u0011\t\u0011)A\u0005\u0003\u007fC!\"a3\u0001\u0005\u0003\u0005\u000b\u0011BAg\u0011)\t)\u000e\u0001B\u0001B\u0003%\u0011q\u001b\u0005\b\u0003G\u0004A\u0011AAs\u0011%\ti\u0010\u0001b\u0001\n\u0003\ty\u0010\u0003\u0005\u0003&\u0001\u0001\u000b\u0011\u0002B\u0001\u0011%\u0011\u0019\u0005\u0001b\u0001\n\u0003\u0011)\u0005\u0003\u0005\u0003J\u0001\u0001\u000b\u0011\u0002B$\u0011%\u0011i\u0005\u0001b\u0001\n\u0013\u0011y\u0005\u0003\u0005\u0003X\u0001\u0001\u000b\u0011\u0002B)\u0011%\u0011Y\u0006\u0001b\u0001\n\u0013\u0011y\u0005\u0003\u0005\u0003^\u0001\u0001\u000b\u0011\u0002B)\u0011%\u0011\t\u0007\u0001b\u0001\n\u0013\u0011\u0019\u0007\u0003\u0005\u0003j\u0001\u0001\u000b\u0011\u0002B3\u0011%\u0011i\u0007\u0001b\u0001\n\u0013\u0011y\u0007\u0003\u0005\u0003\b\u0002\u0001\u000b\u0011\u0002B9\u0011%\u0011Y\t\u0001b\u0001\n\u0013\u0011i\t\u0003\u0005\u0003\u0012\u0002\u0001\u000b\u0011\u0002BH\u0011%\u0011)\n\u0001b\u0001\n\u0013\u00119\n\u0003\u0005\u0003(\u0002\u0001\u000b\u0011\u0002BM\u0011%\u0011Y\u000b\u0001b\u0001\n\u0013\u0011i\u000b\u0003\u0005\u0003>\u0002\u0001\u000b\u0011\u0002BX\u0011)\u0011\t\r\u0001b\u0001\n\u0003i(1\u0019\u0005\t\u0005\u001b\u0004\u0001\u0015!\u0003\u0003F\"I!\u0011\u001b\u0001A\u0002\u0013%!1\u001b\u0005\n\u00057\u0004\u0001\u0019!C\u0005\u0005;D\u0001B!;\u0001A\u0003&!Q\u001b\u0005\n\u0005[\u0004!\u0019!C\u0005\u0005_D\u0001B!?\u0001A\u0003%!\u0011\u001f\u0005\u000b\u0005{\u0004!\u0019!C\u0001{\n}\b\u0002CB\b\u0001\u0001\u0006Ia!\u0001\t\u0015\r]\u0001A1A\u0005\u0002u\u001cI\u0002\u0003\u0005\u0004*\u0001\u0001\u000b\u0011BB\u000e\u0011%\u0019i\u0003\u0001a\u0001\n\u0013\u0019y\u0003C\u0005\u00046\u0001\u0001\r\u0011\"\u0003\u00048!A11\b\u0001!B\u0013\u0019\t\u0004\u0003\u0006\u0004@\u0001\u0001\r\u0011\"\u0001~\u0007\u0003B!b!\u0012\u0001\u0001\u0004%\t!`B$\u0011!\u0019Y\u0005\u0001Q!\n\r\r\u0003\"CB(\u0001\u0001\u0007I\u0011BB)\u0011%\u0019\u0019\u0006\u0001a\u0001\n\u0013\u0019)\u0006\u0003\u0005\u0004Z\u0001\u0001\u000b\u0015\u0002B\t\u0011-\u0019i\u0006\u0001b\u0001\n\u0003\t\u0019aa\u0018\t\u0011\r%\u0004\u0001)A\u0005\u0007CB\u0011ba\u001b\u0001\u0005\u0004%Ia!\u001c\t\u0011\rU\u0004\u0001)A\u0005\u0007_B\u0011ba\u001e\u0001\u0005\u0004%Ia!\u001f\t\u0011\r\u0005\u0005\u0001)A\u0005\u0007wB\u0011ba!\u0001\u0005\u0004%IAa5\t\u0011\r\u0015\u0005\u0001)A\u0005\u0005+D\u0011ba\"\u0001\u0005\u0004%Ia!#\t\u0011\rE\u0005\u0001)A\u0005\u0007\u0017C\u0011ba%\u0001\u0005\u0004%Ia!&\t\u0011\ru\u0005\u0001)A\u0005\u0007/C\u0011ba(\u0001\u0005\u0004%Ia!\u001f\t\u0011\r\u0005\u0006\u0001)A\u0005\u0007wB\u0011ba)\u0001\u0005\u0004%Ia!*\t\u0011\r5\u0006\u0001)A\u0005\u0007OC\u0011ba,\u0001\u0005\u0004%Ia!-\t\u0011\rU\u0006\u0001)A\u0005\u0007gC!ba.\u0001\u0005\u0004%\t!`B]\u0011!\u0019\t\r\u0001Q\u0001\n\rm\u0006\"CBb\u0001\t\u0007I\u0011BB=\u0011!\u0019)\r\u0001Q\u0001\n\rm\u0004\"CBd\u0001\t\u0007I\u0011BBe\u0011!\u0019\u0019\u000e\u0001Q\u0001\n\r-\u0007\"CBk\u0001\u0001\u0007I\u0011BB=\u0011%\u00199\u000e\u0001a\u0001\n\u0013\u0019I\u000e\u0003\u0005\u0004^\u0002\u0001\u000b\u0015BB>\u0011\u001d\u00199\u000f\u0001C\u0005\u0007SDqaa;\u0001\t\u0003\u0019i\u000fC\u0004\u0004r\u0002!\ta!\u0015\t\u000f\rM\b\u0001\"\u0001\u0004R!91Q\u001f\u0001\u0005\u0002\rE\u0003bBB|\u0001\u0011\u00051\u0011\u000b\u0005\b\u0007s\u0004A\u0011AB)\u0011\u001d\u0019Y\u0010\u0001C\u0001\u0007sBqa!@\u0001\t\u0003\u0019y\u0010C\u0004\u0005\u0016\u0001!\ta!\u0015\t\u000f\u0011]\u0001\u0001\"\u0003\u0005\u001a!9AQ\u0005\u0001\u0005\n\u0011\u001d\u0002b\u0002C\u0017\u0001\u0011%Aq\u0006\u0005\b\tg\u0001A\u0011\u0002C\u001b\u0011\u001d!I\u0004\u0001C\u0005\twAq\u0001b\u0010\u0001\t\u0013!\t\u0005C\u0004\u0005F\u0001!I\u0001b\u0012\t\u000f\u00115\u0003\u0001\"\u0003\u0005P!9AQ\u000b\u0001\u0005\u0002\u0011]\u0003b\u0002C6\u0001\u0011\u0005AQ\u000e\u0005\b\tg\u0002A\u0011ABu\u0011\u001d!)\b\u0001C\u0005\toBq\u0001\"#\u0001\t\u0013!Y\tC\u0004\u0005\u0018\u0002!\ta!;\t\u000f\u0011e\u0005\u0001\"\u0001\u0004j\"9A1\u0014\u0001\u0005\n\u0011u\u0005b\u0002CR\u0001\u0011%AQ\u0015\u0005\b\ts\u0003A\u0011\u0001C^\u0011\u001d!\u0019\r\u0001C\u0005\t\u000bDq\u0001b7\u0001\t\u0013!i\u000eC\u0004\u0005b\u0002!I\u0001b9\t\u0011\u00115\b\u0001\"\u0001~\t_D\u0001\u0002\"@\u0001\t\u0003iHq \u0005\b\u000b\u0013\u0001A\u0011BC\u0006\u0011!)y\u0001\u0001C\u0001{\nM\u0007\u0002CC\t\u0001\u0011\u0005Qp!\u0015\t\u000f\u0015M\u0001\u0001\"\u0003\u0006\u0016\u001d9QQE?\t\n\u0015\u001dbA\u0002?~\u0011\u0013)I\u0003C\u0004\u0002dN$\t!b\u000b\t\u0013\u001552O1A\u0005\u0002\u0015=\u0002\u0002CC\u001eg\u0002\u0006I!\"\r\t\u0013\u0015u2O1A\u0005\u0002\rE\u0003\u0002CC g\u0002\u0006IA!\u0005\t\u0013\u0015\u00053O1A\u0005\u0002\u0015\r\u0003\u0002CC(g\u0002\u0006I!\"\u0012\t\u0013\u0015E3/%A\u0005\u0002\u0015M#!D-be:\fE\u000e\\8dCR|'O\u0003\u0002\u007f\u007f\u0006!\u00110\u0019:o\u0015\u0011\t\t!a\u0001\u0002\r\u0011,\u0007\u000f\\8z\u0015\u0011\t)!a\u0002\u0002\u000bM\u0004\u0018M]6\u000b\t\u0005%\u00111B\u0001\u0007CB\f7\r[3\u000b\u0005\u00055\u0011aA8sON)\u0001!!\u0005\u0002\u001eA!\u00111CA\r\u001b\t\t)B\u0003\u0002\u0002\u0018\u0005)1oY1mC&!\u00111DA\u000b\u0005\u0019\te.\u001f*fMB!\u0011qDA\u0013\u001b\t\t\tC\u0003\u0003\u0002$\u0005\r\u0011\u0001C5oi\u0016\u0014h.\u00197\n\t\u0005\u001d\u0012\u0011\u0005\u0002\b\u0019><w-\u001b8h\u0003%!'/\u001b<feV\u0013Hn\u0001\u0001\u0011\t\u0005=\u0012Q\b\b\u0005\u0003c\tI\u0004\u0005\u0003\u00024\u0005UQBAA\u001b\u0015\u0011\t9$a\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0011\tY$!\u0006\u0002\rA\u0013X\rZ3g\u0013\u0011\ty$!\u0011\u0003\rM#(/\u001b8h\u0015\u0011\tY$!\u0006\u0002\u0013\u0011\u0014\u0018N^3s%\u00164\u0007\u0003BA$\u0003\u001bj!!!\u0013\u000b\t\u0005-\u00131A\u0001\u0004eB\u001c\u0017\u0002BA(\u0003\u0013\u0012aB\u00159d\u000b:$\u0007o\\5oiJ+g-\u0001\u0003d_:4\u0007\u0003BA+\u0003?j!!a\u0016\u000b\t\u0005E\u0013\u0011\f\u0006\u0004}\u0006m#\u0002BA/\u0003\u000f\ta\u0001[1e_>\u0004\u0018\u0002BA1\u0003/\u0012\u0011#W1s]\u000e{gNZ5hkJ\fG/[8o\u0003%\u0019\b/\u0019:l\u0007>tg\r\u0005\u0003\u0002h\u0005%TBAA\u0002\u0013\u0011\tY'a\u0001\u0003\u0013M\u0003\u0018M]6D_:4\u0017\u0001C1n\u00072LWM\u001c;\u0011\r\u0005E\u00141PA@\u001b\t\t\u0019H\u0003\u0003\u0002v\u0005]\u0014aA1qS*!\u0011\u0011PA-\u0003\u0019\u0019G.[3oi&!\u0011QPA:\u0005)\tUJU'DY&,g\u000e\u001e\t\u0005\u0003\u0003\u000byJ\u0004\u0003\u0002\u0004\u0006me\u0002BAC\u00033sA!a\"\u0002\u0018:!\u0011\u0011RAK\u001d\u0011\tY)a%\u000f\t\u00055\u0015\u0011\u0013\b\u0005\u0003g\ty)\u0003\u0002\u0002\u000e%!\u0011\u0011BA\u0006\u0013\u0011\ti&a\u0002\n\u0007y\fY&\u0003\u0003\u0002z\u0005e\u0013\u0002BA;\u0003oJA!!(\u0002t\u0005Q\u0011)\u0014*N\u00072LWM\u001c;\n\t\u0005\u0005\u00161\u0015\u0002\u0011\u0007>tG/Y5oKJ\u0014V-];fgRTA!!(\u0002t\u0005a\u0011\r\u001d9BiR,W\u000e\u001d;JIB!\u0011\u0011VAY\u001b\t\tYK\u0003\u0003\u0002.\u0006=\u0016a\u0002:fG>\u0014Hm\u001d\u0006\u0005\u0003k\nI&\u0003\u0003\u00024\u0006-&\u0001F!qa2L7-\u0019;j_:\fE\u000f^3naRLE-A\u0006tK\u000e,(/\u001b;z\u001b\u001e\u0014\b\u0003BA4\u0003sKA!a/\u0002\u0004\ty1+Z2ve&$\u00180T1oC\u001e,'/\u0001\bm_\u000e\fGNU3t_V\u00148-Z:\u0011\u0011\u0005=\u0012\u0011YA\u0017\u0003\u000bLA!a1\u0002B\t\u0019Q*\u00199\u0011\t\u0005%\u0016qY\u0005\u0005\u0003\u0013\fYKA\u0007M_\u000e\fGNU3t_V\u00148-Z\u0001\te\u0016\u001cx\u000e\u001c<feB!\u0011qZAi\u001b\u0005i\u0018bAAj{\n\t2\u000b]1sWJ\u000b7m\u001b*fg>dg/\u001a:\u0002\u000b\rdwnY6\u0011\t\u0005e\u0017q\\\u0007\u0003\u00037TA!!8\u0002\u0004\u0005!Q\u000f^5m\u0013\u0011\t\t/a7\u0003\u000b\rcwnY6\u0002\rqJg.\u001b;?)Y\t9/!;\u0002l\u00065\u0018q^Ay\u0003g\f)0a>\u0002z\u0006m\bcAAh\u0001!9\u0011\u0011F\u0006A\u0002\u00055\u0002bBA\"\u0017\u0001\u0007\u0011Q\t\u0005\b\u0003#Z\u0001\u0019AA*\u0011\u001d\t\u0019g\u0003a\u0001\u0003KBq!!\u001c\f\u0001\u0004\ty\u0007C\u0004\u0002&.\u0001\r!a*\t\u000f\u0005U6\u00021\u0001\u00028\"9\u0011QX\u0006A\u0002\u0005}\u0006bBAf\u0017\u0001\u0007\u0011Q\u001a\u0005\n\u0003+\\\u0001\u0013!a\u0001\u0003/\f1%\u00197m_\u000e\fG/\u001a3I_N$Hk\\\"p]R\f\u0017N\\3sg6\u000b\u0007\u000fU3s%BKE-\u0006\u0002\u0003\u0002AA!1\u0001B\u0007\u0005#\u00119\"\u0004\u0002\u0003\u0006)!!q\u0001B\u0005\u0003\u001diW\u000f^1cY\u0016TAAa\u0003\u0002\u0016\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\t=!Q\u0001\u0002\b\u0011\u0006\u001c\b.T1q!\u0011\t\u0019Ba\u0005\n\t\tU\u0011Q\u0003\u0002\u0004\u0013:$\b\u0003\u0003B\u0002\u0005\u001b\tiC!\u0007\u0011\r\t\r!1\u0004B\u0010\u0013\u0011\u0011iB!\u0002\u0003\u0007M+G\u000f\u0005\u0003\u0002*\n\u0005\u0012\u0002\u0002B\u0012\u0003W\u00131bQ8oi\u0006Lg.\u001a:JI\u0006!\u0013\r\u001c7pG\u0006$X\r\u001a%pgR$vnQ8oi\u0006Lg.\u001a:t\u001b\u0006\u0004\b+\u001a:S!&#\u0007\u0005K\u0004\u000e\u0005S\u0011iDa\u0010\u0011\t\t-\"\u0011H\u0007\u0003\u0005[QAAa\f\u00032\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\t\tM\"QG\u0001\u000bC:tw\u000e^1uS>t'B\u0001B\u001c\u0003\u0015Q\u0017M^1y\u0013\u0011\u0011YD!\f\u0003\u0013\u001d+\u0018M\u001d3fI\nK\u0018!\u0002<bYV,\u0017E\u0001B!\u0003\u0011!\b.[:\u00027\u0005dGn\\2bi\u0016$7i\u001c8uC&tWM\u001d+p\u0011>\u001cH/T1q+\t\u00119\u0005\u0005\u0005\u0003\u0004\t5!qDA\u0017\u0003q\tG\u000e\\8dCR,GmQ8oi\u0006Lg.\u001a:U_\"{7\u000f^'ba\u0002Bsa\u0004B\u0015\u0005{\u0011y$\u0001\nsK2,\u0017m]3e\u0007>tG/Y5oKJ\u001cXC\u0001B)!\u0019\u0011\u0019Aa\u0015\u0003 %!!Q\u000bB\u0003\u0005\u001dA\u0015m\u001d5TKR\f1C]3mK\u0006\u001cX\rZ\"p]R\f\u0017N\\3sg\u0002Bs!\u0005B\u0015\u0005{\u0011y$A\u000fmCVt7\r[5oO\u0016CXmY;u_J\u001cuN\u001c;bS:,'/\u00133t\u0003ya\u0017-\u001e8dQ&tw-\u0012=fGV$xN]\"p]R\f\u0017N\\3s\u0013\u0012\u001c\b\u0005K\u0004\u0014\u0005S\u0011iDa\u0010\u0002II,hN\\5oO\u0016CXmY;u_J\u001c\b+\u001a:SKN|WO]2f!J|g-\u001b7f\u0013\u0012,\"A!\u001a\u0011\u0011\t\r!Q\u0002B\t\u0005O\u0002bAa\u0001\u0003\u001c\u00055\u0012!\n:v]:LgnZ#yK\u000e,Ho\u001c:t!\u0016\u0014(+Z:pkJ\u001cW\r\u0015:pM&dW-\u00133!Q\u001d)\"\u0011\u0006B\u001f\u0005\u007f\t\u0001F\\;n\u000bb,7-\u001e;peN\u001cF/\u0019:uS:<\u0007+\u001a:SKN|WO]2f!J|g-\u001b7f\u0013\u0012,\"A!\u001d\u0011\u0011\t\r!Q\u0002B\t\u0005g\u0002BA!\u001e\u0003\u00046\u0011!q\u000f\u0006\u0005\u0005s\u0012Y(\u0001\u0004bi>l\u0017n\u0019\u0006\u0005\u0005_\u0011iH\u0003\u0003\u0002^\n}$B\u0001BA\u0003\u0011Q\u0017M^1\n\t\t\u0015%q\u000f\u0002\u000e\u0003R|W.[2J]R,w-\u001a:\u0002S9,X.\u0012=fGV$xN]:Ti\u0006\u0014H/\u001b8h!\u0016\u0014(+Z:pkJ\u001cW\r\u0015:pM&dW-\u00133!Q\u001d9\"\u0011\u0006B\u001f\u0005\u007f\ta\u0005^1sO\u0016$h*^7Fq\u0016\u001cW\u000f^8sgB+'OU3t_V\u00148-\u001a)s_\u001aLG.Z%e+\t\u0011y\t\u0005\u0005\u0003\u0004\t5!\u0011\u0003B\t\u0003\u001d\"\u0018M]4fi:+X.\u0012=fGV$xN]:QKJ\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0017\n\u001a\u0011)\u000fe\u0011IC!\u0010\u0003@\u0005I\u0002/\u001a8eS:<Gj\\:t%\u0016\f7o\u001c8SKF,Xm\u001d;t+\t\u0011I\n\u0005\u0005\u0003\u0004\t5\u0011Q\u0006BN!\u0019\u0011\u0019A!(\u0003\"&!!q\u0014B\u0003\u0005\u0019\u0011UO\u001a4feB!\u0011q\tBR\u0013\u0011\u0011)+!\u0013\u0003\u001dI\u00038mQ1mY\u000e{g\u000e^3yi\u0006Q\u0002/\u001a8eS:<Gj\\:t%\u0016\f7o\u001c8SKF,Xm\u001d;tA!:1D!\u000b\u0003>\t}\u0012a\u0007:fY\u0016\f7/\u001a3Fq\u0016\u001cW\u000f^8s\u0019>\u001c8OU3bg>t7/\u0006\u0002\u00030BA!1\u0001B\u0007\u0003[\u0011\t\f\u0005\u0003\u00034\neVB\u0001B[\u0015\u0011\u00119,a\u0001\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018\u0002\u0002B^\u0005k\u0013!#\u0012=fGV$xN\u001d'pgN\u0014V-Y:p]\u0006a\"/\u001a7fCN,G-\u0012=fGV$xN\u001d'pgN\u0014V-Y:p]N\u0004\u0003fB\u000f\u0003*\tu\"qH\u0001\u0016Kb,7-\u001e;pe&#Gk\\\"p]R\f\u0017N\\3s+\t\u0011)\r\u0005\u0005\u0003\u0004\t5\u0011Q\u0006Bd!\u0011\tIK!3\n\t\t-\u00171\u0016\u0002\n\u0007>tG/Y5oKJ\fa#\u001a=fGV$xN]%e)>\u001cuN\u001c;bS:,'\u000f\t\u0015\b?\t%\"Q\bB \u0003uqW/\\+oKb\u0004Xm\u0019;fI\u000e{g\u000e^1j]\u0016\u0014(+\u001a7fCN,WC\u0001Bk!\u0011\t\u0019Ba6\n\t\te\u0017Q\u0003\u0002\u0005\u0019>tw-A\u0011ok6,f.\u001a=qK\u000e$X\rZ\"p]R\f\u0017N\\3s%\u0016dW-Y:f?\u0012*\u0017\u000f\u0006\u0003\u0003`\n\u0015\b\u0003BA\n\u0005CLAAa9\u0002\u0016\t!QK\\5u\u0011%\u00119/IA\u0001\u0002\u0004\u0011).A\u0002yIE\naD\\;n+:,\u0007\u0010]3di\u0016$7i\u001c8uC&tWM\u001d*fY\u0016\f7/\u001a\u0011)\u000f\t\u0012IC!\u0010\u0003@\u0005Y3m\u001c8uC&tWM]%e)>,\u00050Z2vi>\u0014\u0018\nZ!oIJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE-\u0006\u0002\u0003rBA!1\u0001B\u0007\u0005?\u0011\u0019\u0010\u0005\u0005\u0002\u0014\tU\u0018Q\u0006B\t\u0013\u0011\u001190!\u0006\u0003\rQ+\b\u000f\\33\u00031\u001awN\u001c;bS:,'/\u00133U_\u0016CXmY;u_JLE-\u00118e%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&#\u0007\u0005K\u0004%\u0005S\u0011iDa\u0010\u0002%I\u0004\u0018\n\u001a+p3\u0006\u0014hNU3t_V\u00148-Z\u000b\u0003\u0007\u0003\u0001\u0002ba\u0001\u0004\u0006\tE1\u0011B\u0007\u0003\u0005wJAaa\u0002\u0003|\t\t2i\u001c8dkJ\u0014XM\u001c;ICNDW*\u00199\u0011\t\u0005%61B\u0005\u0005\u0007\u001b\tYK\u0001\u0005SKN|WO]2f\u0003M\u0011\b/\u00133U_f\u000b'O\u001c*fg>,(oY3!Q\u001d1#\u0011\u0006B\u001f\u0007'\t#a!\u0006\u0002#\r{gnY;se\u0016tG\u000fS1tQ6\u000b\u0007/A\u000bsa&#Gk\u001c*fg>,(oY3Qe>4\u0017\u000e\\3\u0016\u0005\rm\u0001\u0003\u0003B\u0002\u0005\u001b\u0011\tb!\b\u0011\t\r}1QE\u0007\u0003\u0007CQAaa\t\u0002\u0004\u0005A!/Z:pkJ\u001cW-\u0003\u0003\u0004(\r\u0005\"a\u0004*fg>,(oY3Qe>4\u0017\u000e\\3\u0002-I\u0004\u0018\n\u001a+p%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK\u0002Bs\u0001\u000bB\u0015\u0005{\u0011y$\u0001\u0015i_N$Hk\u001c'pG\u0006dG+Y:l\u0007>,h\u000e\u001e)feJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE-\u0006\u0002\u00042AA\u0011qFAa\u0005#\u0019\u0019\u0004\u0005\u0005\u00020\u0005\u0005\u0017Q\u0006B\t\u00031Bwn\u001d;U_2{7-\u00197UCN\\7i\\;oiB+'OU3t_V\u00148-\u001a)s_\u001aLG.Z%e?\u0012*\u0017\u000f\u0006\u0003\u0003`\u000ee\u0002\"\u0003BtU\u0005\u0005\t\u0019AB\u0019\u0003%Bwn\u001d;U_2{7-\u00197UCN\\7i\\;oiB+'OU3t_V\u00148-\u001a)s_\u001aLG.Z%eA!:1F!\u000b\u0003>\t}\u0012!\u000b8v[2{7-\u00197jif\fu/\u0019:f)\u0006\u001c8n\u001d)feJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LE-\u0006\u0002\u0004DAA\u0011qFAa\u0005#\u0011\t\"A\u0017ok6dunY1mSRL\u0018i^1sKR\u000b7o[:QKJ\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0017\nZ0%KF$BAa8\u0004J!I!q]\u0017\u0002\u0002\u0003\u000711I\u0001+]VlGj\\2bY&$\u00180Q<be\u0016$\u0016m]6t!\u0016\u0014(+Z:pkJ\u001cW\r\u0015:pM&dW-\u00133!Q\u001dq#\u0011\u0006B\u001f\u0005\u007f\t\u0011#\u001a=fGV$xN]%e\u0007>,h\u000e^3s+\t\u0011\t\"A\u000bfq\u0016\u001cW\u000f^8s\u0013\u0012\u001cu.\u001e8uKJ|F%Z9\u0015\t\t}7q\u000b\u0005\n\u0005O\u0004\u0014\u0011!a\u0001\u0005#\t!#\u001a=fGV$xN]%e\u0007>,h\u000e^3sA!:\u0011G!\u000b\u0003>\t}\u0012A\u00044bS2,(/\u001a+sC\u000e\\WM]\u000b\u0003\u0007C\u0002Baa\u0019\u0004f5\tq0C\u0002\u0004h}\u0014a#\u0012=fGV$xN\u001d$bS2,(/\u001a+sC\u000e\\WM]\u0001\u0010M\u0006LG.\u001e:f)J\f7m[3sA\u0005Q\u0012\r\u001c7pG\u0006$xN\u001d(pI\u0016DU-\u00197uQR\u0013\u0018mY6feV\u00111q\u000e\t\u0005\u0003\u001f\u001c\t(C\u0002\u0004tu\u0014a$W1s]\u0006cGn\\2bi>\u0014hj\u001c3f\u0011\u0016\fG\u000e\u001e5Ue\u0006\u001c7.\u001a:\u00027\u0005dGn\\2bi>\u0014hj\u001c3f\u0011\u0016\fG\u000e\u001e5Ue\u0006\u001c7.\u001a:!\u0003-I7\u000fU=uQ>t\u0017\t\u001d9\u0016\u0005\rm\u0004\u0003BA\n\u0007{JAaa \u0002\u0016\t9!i\\8mK\u0006t\u0017\u0001D5t!f$\bn\u001c8BaB\u0004\u0013!E7j]6+Wn\u001c:z\u001fZ,'\u000f[3bI\u0006\u0011R.\u001b8NK6|'/_(wKJDW-\u00193!\u0003QiW-\\8ss>3XM\u001d5fC\u00124\u0015m\u0019;peV\u001111\u0012\t\u0005\u0003'\u0019i)\u0003\u0003\u0004\u0010\u0006U!A\u0002#pk\ndW-A\u000bnK6|'/_(wKJDW-\u00193GC\u000e$xN\u001d\u0011\u0002\u00191\fWO\\2iKJ\u0004vn\u001c7\u0016\u0005\r]\u0005\u0003BB\u0002\u00073KAaa'\u0003|\t\u0011B\u000b\u001b:fC\u0012\u0004vn\u001c7Fq\u0016\u001cW\u000f^8s\u00035a\u0017-\u001e8dQ\u0016\u0014\bk\\8mA\u0005\u0001B.Y;oG\"\u001cuN\u001c;bS:,'o]\u0001\u0012Y\u0006,hn\u00195D_:$\u0018-\u001b8feN\u0004\u0013a\u00047bE\u0016dW\t\u001f9sKN\u001c\u0018n\u001c8\u0016\u0005\r\u001d\u0006CBA\n\u0007S\u000bi#\u0003\u0003\u0004,\u0006U!AB(qi&|g.\u0001\tmC\n,G.\u0012=qe\u0016\u001c8/[8oA\u0005\u0019\"/Z:pkJ\u001cWMT1nK6\u000b\u0007\u000f]5oOV\u001111\u0017\t\t\u0003_\t\t-!\f\u0002.\u0005!\"/Z:pkJ\u001cWMT1nK6\u000b\u0007\u000f]5oO\u0002\n!dY8oi\u0006Lg.\u001a:QY\u0006\u001cW-\\3oiN#(/\u0019;fOf,\"aa/\u0011\t\u0005=7QX\u0005\u0004\u0007\u007fk(a\u000b'pG\u0006d\u0017\u000e^=Qe\u00164WM\u001d:fI\u000e{g\u000e^1j]\u0016\u0014\b\u000b\\1dK6,g\u000e^*ue\u0006$XmZ=\u00027\r|g\u000e^1j]\u0016\u0014\b\u000b\\1dK6,g\u000e^*ue\u0006$XmZ=!\u0003\u0005J7/W1s]\u0016CXmY;u_J$UmY8n[&\u001c8/[8o\u000b:\f'\r\\3e\u0003\tJ7/W1s]\u0016CXmY;u_J$UmY8n[&\u001c8/[8o\u000b:\f'\r\\3eA\u0005IB-Z2p[6L7o]5p]&twMT8eKN\u001c\u0015m\u00195f+\t\u0019Y\r\u0005\u0005\u0004N\u000e=\u0017QFB>\u001b\t\u0011i(\u0003\u0003\u0004R\nu$!\u0004'j].,G\rS1tQ6\u000b\u0007/\u0001\u000eeK\u000e|W.\\5tg&|g.\u001b8h\u001d>$Wm]\"bG\",\u0007%\u0001\u0005tQV$Hm\\<o\u00031\u0019\b.\u001e;e_^tw\fJ3r)\u0011\u0011yna7\t\u0013\t\u001d8*!AA\u0002\rm\u0014!C:ikR$wn\u001e8!Q\ra5\u0011\u001d\t\u0005\u0003'\u0019\u0019/\u0003\u0003\u0004f\u0006U!\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002%%t\u0017\u000e\u001e#fM\u0006,H\u000e\u001e)s_\u001aLG.\u001a\u000b\u0003\u0005?\f1b]3u'\",H\u000fZ8x]R!!q\\Bx\u0011\u001d\u0019)N\u0014a\u0001\u0007w\nacZ3u\u001dVlW\t_3dkR|'o\u001d*v]:LgnZ\u0001\u0019O\u0016$h*^7M_\u000e\fG.\u001b;z\u0003^\f'/\u001a+bg.\u001c\u0018aF4fi:+X.\u0012=fGV$xN]:Ti\u0006\u0014H/\u001b8h\u0003a9W\r\u001e(v[J+G.Z1tK\u0012\u001cuN\u001c;bS:,'o]\u0001\u0016O\u0016$h*^7Fq\u0016\u001cW\u000f^8sg\u001a\u000b\u0017\u000e\\3e\u0003EI7/\u00117m\u001d>$W-\u0012=dYV$W\rZ\u0001\u0013O\u0016$\b+\u001a8eS:<\u0017\t\u001c7pG\u0006$X-\u0006\u0002\u0005\u0002AA\u0011qFAa\u0005#!\u0019\u0001\u0005\u0004\u0005\u0006\u0011=\u0011q\u0010\b\u0005\t\u000f!YA\u0004\u0003\u00024\u0011%\u0011BAA\f\u0013\u0011!i!!\u0006\u0002\u000fA\f7m[1hK&!A\u0011\u0003C\n\u0005\r\u0019V-\u001d\u0006\u0005\t\u001b\t)\"A\u0010hKRtU/\\\"p]R\f\u0017N\\3sgB+g\u000eZ5oO\u0006cGn\\2bi\u0016\fAcZ3u\u0007>tG/Y5oKJ\u0004&/[8sSRLH\u0003\u0002C\u000e\tC\u0001B!!+\u0005\u001e%!AqDAV\u0005!\u0001&/[8sSRL\bb\u0002C\u0012/\u0002\u0007!\u0011C\u0001\u0005eBLE-\u0001\u0011hKR\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0017\n\u001a$s_6\u0004&/[8sSRLH\u0003\u0002B\t\tSAq\u0001b\u000bY\u0001\u0004!Y\"\u0001\u0005qe&|'/\u001b;z\u00039:W\r^(s+B$\u0017\r^3BY2|7-\u0019;fI\"{7\u000f\u001e+p\u0007>tG/Y5oKJ\u001cX*\u00199G_J\u0014\u0006+\u00133\u0015\t\t]A\u0011\u0007\u0005\b\tGI\u0006\u0019\u0001B\t\u0003\u0005:W\r^(s+B$\u0017\r^3Sk:t\u0017N\\4Fq\u0016\u001cW\u000f^8s\r>\u0014(\u000bU%e)\u0011\u00119\u0007b\u000e\t\u000f\u0011\r\"\f1\u0001\u0003\u0012\u00051s-\u001a;PeV\u0003H-\u0019;f\u001dVlW\t_3dkR|'o]*uCJ$\u0018N\\4G_J\u0014\u0006+\u00133\u0015\t\tMDQ\b\u0005\b\tGY\u0006\u0019\u0001B\t\u0003\u0011:W\r^(s+B$\u0017\r^3UCJ<W\r\u001e(v[\u0016CXmY;u_J\u001chi\u001c:S!&#G\u0003\u0002B\t\t\u0007Bq\u0001b\t]\u0001\u0004\u0011\t\"\u0001\u000bhKR\u0004VM\u001c3j]\u001e\fE\u000fT8dCRLwN\u001c\u000b\u0005\t\u0003!I\u0005C\u0004\u0005Lu\u0003\r!!\f\u0002\u00111|7-\u0019;j_:\fAe\u0019:fCR,\u0017,\u0019:o%\u0016\u001cx.\u001e:dK\u001a{'OU3t_V\u00148-\u001a)s_\u001aLG.\u001a\u000b\u0005\u0005?$\t\u0006C\u0004\u0005Ty\u0003\ra!\b\u0002\u0005I\u0004\u0018\u0001\f:fcV,7\u000f\u001e+pi\u0006dW\t_3dkR|'o],ji\"\u0004&/\u001a4feJ,G\rT8dC2LG/[3t))\u0019Y\b\"\u0017\u0005`\u0011\u0005D1\r\u0005\b\t7z\u0006\u0019\u0001C/\u0003m\u0011Xm]8ve\u000e,\u0007K]8gS2,Gk\u001c+pi\u0006dW\t_3dgBA\u0011qFAa\u0007;\u0011\t\u0002C\u0004\u0004@}\u0003\raa\u0011\t\u000f\r5r\f1\u0001\u00042!9AQM0A\u0002\u0011\u001d\u0014!D3yG2,H-\u001a3O_\u0012,7\u000f\u0005\u0004\u00020\u0011%\u0014QF\u0005\u0005\u0005;\t\t%\u0001\u0007lS2dW\t_3dkR|'\u000f\u0006\u0003\u0003`\u0012=\u0004b\u0002C9A\u0002\u0007\u0011QF\u0001\u000bKb,7-\u001e;pe&#\u0017!E1mY>\u001c\u0017\r^3SKN|WO]2fg\u0006\t\u0003.\u00198eY\u0016tu\u000eZ3t\u0013:$UmY8n[&\u001c8/[8oS:<7\u000b^1uKR!!q\u001cC=\u0011\u001d!YH\u0019a\u0001\t{\n\u0001#\u00197m_\u000e\fG/\u001a*fgB|gn]3\u0011\t\u0011}DQQ\u0007\u0003\t\u0003SA\u0001b!\u00020\u0006y\u0001O]8u_\u000e|GN]3d_J$7/\u0003\u0003\u0005\b\u0012\u0005%\u0001E!mY>\u001c\u0017\r^3SKN\u0004xN\\:f\u000399W\r\u001e%pgR\fE\r\u001a:fgN$B!!\f\u0005\u000e\"9AqR2A\u0002\u0011E\u0015A\u00038pI\u0016\u0014V\r]8siB!\u0011\u0011\u0016CJ\u0013\u0011!)*a+\u0003\u00159{G-\u001a*fa>\u0014H/\u0001\fva\u0012\fG/\u001a*fg>,(oY3SKF,Xm\u001d;t\u0003\u0011\u0019Ho\u001c9\u0002\u000f!|7\u000f^*ueR!\u0011Q\u0006CP\u0011\u001d!\tK\u001aa\u0001\u0003\u007f\nqA]3rk\u0016\u001cH/\u0001\fde\u0016\fG/Z\"p]R\f\u0017N\\3s%\u0016\fX/Z:u))\ty\bb*\u0005*\u0012MFq\u0017\u0005\b\u0007G9\u0007\u0019AB\u0005\u0011\u001d!Yk\u001aa\u0001\t[\u000bQA\\8eKN\u0004b!a\u0005\u00050\u00065\u0012\u0002\u0002CY\u0003+\u0011Q!\u0011:sCfDq\u0001\".h\u0001\u0004!i+A\u0003sC\u000e\\7\u000fC\u0004\u0005$\u001d\u0004\rA!\u0005\u00023!\fg\u000e\u001a7f\u00032dwnY1uK\u0012\u001cuN\u001c;bS:,'o\u001d\u000b\u0005\u0005?$i\fC\u0004\u0005@\"\u0004\r\u0001\"1\u0002'\u0005dGn\\2bi\u0016$7i\u001c8uC&tWM]:\u0011\r\u0011\u0015Aq\u0002Bd\u0003]i\u0017\r^2i\u0007>tG/Y5oKJ$vNU3rk\u0016\u001cH\u000f\u0006\u0006\u0003`\u0012\u001dG1\u001aCg\t/Dq\u0001\"3j\u0001\u0004\u00119-\u0001\nbY2|7-\u0019;fI\u000e{g\u000e^1j]\u0016\u0014\bb\u0002C&S\u0002\u0007\u0011Q\u0006\u0005\b\t\u001fL\u0007\u0019\u0001Ci\u0003=\u0019wN\u001c;bS:,'o\u001d+p+N,\u0007C\u0002B\u0002\t'\u00149-\u0003\u0003\u0005V\n\u0015!aC!se\u0006L()\u001e4gKJDq\u0001\"7j\u0001\u0004!\t.A\u0005sK6\f\u0017N\\5oO\u00061\"/\u001e8BY2|7-\u0019;fI\u000e{g\u000e^1j]\u0016\u00148\u000f\u0006\u0003\u0003`\u0012}\u0007b\u0002ChU\u0002\u0007A\u0011[\u0001\u0014kB$\u0017\r^3J]R,'O\\1m'R\fG/\u001a\u000b\t\u0005?$)\u000fb:\u0005j\"9A1E6A\u0002\tE\u0001b\u0002C9W\u0002\u0007\u0011Q\u0006\u0005\b\tW\\\u0007\u0019\u0001Bd\u0003%\u0019wN\u001c;bS:,'/\u0001\u000eqe>\u001cWm]:D_6\u0004H.\u001a;fI\u000e{g\u000e^1j]\u0016\u00148\u000f\u0006\u0003\u0003`\u0012E\bb\u0002CzY\u0002\u0007AQ_\u0001\u0014G>l\u0007\u000f\\3uK\u0012\u001cuN\u001c;bS:,'o\u001d\t\u0007\t\u000b!y\u0001b>\u0011\t\u0005%F\u0011`\u0005\u0005\tw\fYKA\bD_:$\u0018-\u001b8feN#\u0018\r^;t\u0003m)g.];fk\u0016<U\r\u001e'pgN\u0014V-Y:p]J+\u0017/^3tiR1!q\\C\u0001\u000b\u000bAq!b\u0001n\u0001\u0004\ti#A\u0002fS\u0012Dq!b\u0002n\u0001\u0004\u0011\t+A\u0004d_:$X\r\u001f;\u00021%tG/\u001a:oC2\u0014V\r\\3bg\u0016\u001cuN\u001c;bS:,'\u000f\u0006\u0003\u0003`\u00165\u0001b\u0002Cv]\u0002\u0007!qY\u0001!O\u0016$h*^7V]\u0016D\b/Z2uK\u0012\u001cuN\u001c;bS:,'OU3mK\u0006\u001cX-A\u0010hKRtU/\u001c)f]\u0012Lgn\u001a'pgN\u0014V-Y:p]J+\u0017/^3tiN\f\u0011e\u001d9mSR\u0004VM\u001c3j]\u001e\fE\u000e\\8dCRLwN\\:Cs2{7-\u00197jif$b!b\u0006\u0006\u001e\u0015\u0005\u0002CCA\n\u000b3!\u0019\u0001b\u0001\u0005\u0004%!Q1DA\u000b\u0005\u0019!V\u000f\u001d7fg!9QqD9A\u0002\rM\u0012\u0001\u00065pgR$v\u000eT8dC2$\u0016m]6D_VtG\u000fC\u0004\u0006$E\u0004\r\u0001b\u0001\u0002%A,g\u000eZ5oO\u0006cGn\\2bi&|gn]\u0001\u000e3\u0006\u0014h.\u00117m_\u000e\fGo\u001c:\u0011\u0007\u0005=7oE\u0002t\u0003#!\"!b\n\u0002\u00135+Uj\u0018*F\u000f\u0016CVCAC\u0019!\u0011)\u0019$\"\u000f\u000e\u0005\u0015U\"\u0002BC\u001c\u0005\u007f\nA\u0001\\1oO&!\u0011qHC\u001b\u0003)iU)T0S\u000b\u001e+\u0005\fI\u0001!\t\u0016\u001bu*T'J'NKuJT%O\u000f~su\nR#T?\u000e\u000b5\tS#`'&SV)A\u0011E\u000b\u000e{U*T%T'&{e*\u0013(H?:{E)R*`\u0007\u0006\u001b\u0005*R0T\u0013j+\u0005%\u0001\u0013O\u001fR{\u0016\t\u0015)`\u0003:#ulU-T)\u0016kuLR!V\u0019R{V\tW%U?N#\u0016\tV+T+\t))\u0005\u0005\u0004\u0006H\u00155#\u0011C\u0007\u0003\u000b\u0013RA!b\u0013\u0003\n\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0005;)I%A\u0013O\u001fR{\u0016\t\u0015)`\u0003:#ulU-T)\u0016kuLR!V\u0019R{V\tW%U?N#\u0016\tV+TA\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\u0002TCAC+U\u0011\t9.b\u0016,\u0005\u0015e\u0003\u0003BC.\u000bGj!!\"\u0018\u000b\t\u0015}S\u0011M\u0001\nk:\u001c\u0007.Z2lK\u0012TAAa\r\u0002\u0016%!QQMC/\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public class YarnAllocator implements Logging {
   private final String driverUrl;
   private final RpcEndpointRef driverRef;
   private final YarnConfiguration conf;
   private final SparkConf sparkConf;
   private final AMRMClient amClient;
   private final ApplicationAttemptId appAttemptId;
   private final SecurityManager securityMgr;
   private final Map localResources;
   public final SparkRackResolver org$apache$spark$deploy$yarn$YarnAllocator$$resolver;
   @GuardedBy("this")
   private final HashMap allocatedHostToContainersMapPerRPId;
   @GuardedBy("this")
   private final HashMap allocatedContainerToHostMap;
   @GuardedBy("this")
   private final HashSet releasedContainers;
   @GuardedBy("this")
   private final HashSet launchingExecutorContainerIds;
   @GuardedBy("this")
   private final HashMap runningExecutorsPerResourceProfileId;
   @GuardedBy("this")
   private final HashMap numExecutorsStartingPerResourceProfileId;
   @GuardedBy("this")
   private final HashMap targetNumExecutorsPerResourceProfileId;
   @GuardedBy("this")
   private final HashMap pendingLossReasonRequests;
   @GuardedBy("this")
   private final HashMap releasedExecutorLossReasons;
   @GuardedBy("this")
   private final HashMap executorIdToContainer;
   @GuardedBy("this")
   private long numUnexpectedContainerRelease;
   @GuardedBy("this")
   private final HashMap containerIdToExecutorIdAndResourceProfileId;
   @GuardedBy("ConcurrentHashMap")
   private final ConcurrentHashMap rpIdToYarnResource;
   @GuardedBy("this")
   private final HashMap rpIdToResourceProfile;
   @GuardedBy("this")
   private Map hostToLocalTaskCountPerResourceProfileId;
   @GuardedBy("this")
   private Map numLocalityAwareTasksPerResourceProfileId;
   @GuardedBy("this")
   private int executorIdCounter;
   private final ExecutorFailureTracker failureTracker;
   private final YarnAllocatorNodeHealthTracker allocatorNodeHealthTracker;
   private final boolean isPythonApp;
   private final long minMemoryOverhead;
   private final double memoryOverheadFactor;
   private final ThreadPoolExecutor launcherPool;
   private final boolean launchContainers;
   private final Option labelExpression;
   private final Map resourceNameMapping;
   private final LocalityPreferredContainerPlacementStrategy containerPlacementStrategy;
   private final boolean isYarnExecutorDecommissionEnabled;
   private final LinkedHashMap decommissioningNodesCache;
   private volatile boolean shutdown;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Clock $lessinit$greater$default$10() {
      return YarnAllocator$.MODULE$.$lessinit$greater$default$10();
   }

   public static Set NOT_APP_AND_SYSTEM_FAULT_EXIT_STATUS() {
      return YarnAllocator$.MODULE$.NOT_APP_AND_SYSTEM_FAULT_EXIT_STATUS();
   }

   public static int DECOMMISSIONING_NODES_CACHE_SIZE() {
      return YarnAllocator$.MODULE$.DECOMMISSIONING_NODES_CACHE_SIZE();
   }

   public static String MEM_REGEX() {
      return YarnAllocator$.MODULE$.MEM_REGEX();
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public HashMap allocatedHostToContainersMapPerRPId() {
      return this.allocatedHostToContainersMapPerRPId;
   }

   public HashMap allocatedContainerToHostMap() {
      return this.allocatedContainerToHostMap;
   }

   private HashSet releasedContainers() {
      return this.releasedContainers;
   }

   private HashSet launchingExecutorContainerIds() {
      return this.launchingExecutorContainerIds;
   }

   private HashMap runningExecutorsPerResourceProfileId() {
      return this.runningExecutorsPerResourceProfileId;
   }

   private HashMap numExecutorsStartingPerResourceProfileId() {
      return this.numExecutorsStartingPerResourceProfileId;
   }

   private HashMap targetNumExecutorsPerResourceProfileId() {
      return this.targetNumExecutorsPerResourceProfileId;
   }

   private HashMap pendingLossReasonRequests() {
      return this.pendingLossReasonRequests;
   }

   private HashMap releasedExecutorLossReasons() {
      return this.releasedExecutorLossReasons;
   }

   public HashMap executorIdToContainer() {
      return this.executorIdToContainer;
   }

   private long numUnexpectedContainerRelease() {
      return this.numUnexpectedContainerRelease;
   }

   private void numUnexpectedContainerRelease_$eq(final long x$1) {
      this.numUnexpectedContainerRelease = x$1;
   }

   private HashMap containerIdToExecutorIdAndResourceProfileId() {
      return this.containerIdToExecutorIdAndResourceProfileId;
   }

   public ConcurrentHashMap rpIdToYarnResource() {
      return this.rpIdToYarnResource;
   }

   public HashMap rpIdToResourceProfile() {
      return this.rpIdToResourceProfile;
   }

   private Map hostToLocalTaskCountPerResourceProfileId() {
      return this.hostToLocalTaskCountPerResourceProfileId;
   }

   private void hostToLocalTaskCountPerResourceProfileId_$eq(final Map x$1) {
      this.hostToLocalTaskCountPerResourceProfileId = x$1;
   }

   public Map numLocalityAwareTasksPerResourceProfileId() {
      return this.numLocalityAwareTasksPerResourceProfileId;
   }

   public void numLocalityAwareTasksPerResourceProfileId_$eq(final Map x$1) {
      this.numLocalityAwareTasksPerResourceProfileId = x$1;
   }

   private int executorIdCounter() {
      return this.executorIdCounter;
   }

   private void executorIdCounter_$eq(final int x$1) {
      this.executorIdCounter = x$1;
   }

   public ExecutorFailureTracker failureTracker() {
      return this.failureTracker;
   }

   private YarnAllocatorNodeHealthTracker allocatorNodeHealthTracker() {
      return this.allocatorNodeHealthTracker;
   }

   private boolean isPythonApp() {
      return this.isPythonApp;
   }

   private long minMemoryOverhead() {
      return this.minMemoryOverhead;
   }

   private double memoryOverheadFactor() {
      return this.memoryOverheadFactor;
   }

   private ThreadPoolExecutor launcherPool() {
      return this.launcherPool;
   }

   private boolean launchContainers() {
      return this.launchContainers;
   }

   private Option labelExpression() {
      return this.labelExpression;
   }

   private Map resourceNameMapping() {
      return this.resourceNameMapping;
   }

   public LocalityPreferredContainerPlacementStrategy containerPlacementStrategy() {
      return this.containerPlacementStrategy;
   }

   private boolean isYarnExecutorDecommissionEnabled() {
      return this.isYarnExecutorDecommissionEnabled;
   }

   private LinkedHashMap decommissioningNodesCache() {
      return this.decommissioningNodesCache;
   }

   private boolean shutdown() {
      return this.shutdown;
   }

   private void shutdown_$eq(final boolean x$1) {
      this.shutdown = x$1;
   }

   private synchronized void initDefaultProfile() {
      this.allocatedHostToContainersMapPerRPId().update(BoxesRunTime.boxToInteger(.MODULE$.DEFAULT_RESOURCE_PROFILE_ID()), new HashMap());
      this.runningExecutorsPerResourceProfileId().put(BoxesRunTime.boxToInteger(.MODULE$.DEFAULT_RESOURCE_PROFILE_ID()), scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      this.numExecutorsStartingPerResourceProfileId().update(BoxesRunTime.boxToInteger(.MODULE$.DEFAULT_RESOURCE_PROFILE_ID()), new AtomicInteger(0));
      int initTargetExecNum = org.apache.spark.scheduler.cluster.SchedulerBackendUtils..MODULE$.getInitialTargetExecutorNumber(this.sparkConf, org.apache.spark.scheduler.cluster.SchedulerBackendUtils..MODULE$.getInitialTargetExecutorNumber$default$2());
      this.targetNumExecutorsPerResourceProfileId().update(BoxesRunTime.boxToInteger(.MODULE$.DEFAULT_RESOURCE_PROFILE_ID()), BoxesRunTime.boxToInteger(initTargetExecNum));
      ResourceProfile defaultProfile = .MODULE$.getOrCreateDefaultProfile(this.sparkConf);
      this.createYarnResourceForResourceProfile(defaultProfile);
   }

   public void setShutdown(final boolean shutdown) {
      this.shutdown_$eq(shutdown);
   }

   public synchronized int getNumExecutorsRunning() {
      return BoxesRunTime.unboxToInt(((IterableOnceOps)this.runningExecutorsPerResourceProfileId().values().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$getNumExecutorsRunning$1(x$1)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
   }

   public synchronized int getNumLocalityAwareTasks() {
      return BoxesRunTime.unboxToInt(this.numLocalityAwareTasksPerResourceProfileId().values().sum(scala.math.Numeric.IntIsIntegral..MODULE$));
   }

   public synchronized int getNumExecutorsStarting() {
      return BoxesRunTime.unboxToInt(((IterableOnceOps)this.numExecutorsStartingPerResourceProfileId().values().map((x$2) -> BoxesRunTime.boxToInteger($anonfun$getNumExecutorsStarting$1(x$2)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
   }

   public synchronized int getNumReleasedContainers() {
      return this.releasedContainers().size();
   }

   public int getNumExecutorsFailed() {
      return this.failureTracker().numFailedExecutors();
   }

   public boolean isAllNodeExcluded() {
      return this.allocatorNodeHealthTracker().isAllNodeExcluded();
   }

   public Map getPendingAllocate() {
      return this.getPendingAtLocation(YarnSparkHadoopUtil$.MODULE$.ANY_HOST());
   }

   public synchronized int getNumContainersPendingAllocate() {
      return ((IterableOnceOps)this.getPendingAllocate().values().flatten(scala.Predef..MODULE$.$conforms())).size();
   }

   private Priority getContainerPriority(final int rpId) {
      return Priority.newInstance(rpId);
   }

   private int getResourceProfileIdFromPriority(final Priority priority) {
      return priority.getPriority();
   }

   private synchronized HashMap getOrUpdateAllocatedHostToContainersMapForRPId(final int rpId) {
      return (HashMap)this.allocatedHostToContainersMapPerRPId().getOrElseUpdate(BoxesRunTime.boxToInteger(rpId), () -> new HashMap());
   }

   private synchronized scala.collection.mutable.Set getOrUpdateRunningExecutorForRPId(final int rpId) {
      return (scala.collection.mutable.Set)this.runningExecutorsPerResourceProfileId().getOrElseUpdate(BoxesRunTime.boxToInteger(rpId), () -> (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   private synchronized AtomicInteger getOrUpdateNumExecutorsStartingForRPId(final int rpId) {
      return (AtomicInteger)this.numExecutorsStartingPerResourceProfileId().getOrElseUpdate(BoxesRunTime.boxToInteger(rpId), () -> new AtomicInteger(0));
   }

   private synchronized int getOrUpdateTargetNumExecutorsForRPId(final int rpId) {
      return BoxesRunTime.unboxToInt(this.targetNumExecutorsPerResourceProfileId().getOrElseUpdate(BoxesRunTime.boxToInteger(rpId), (JFunction0.mcI.sp)() -> org.apache.spark.scheduler.cluster.SchedulerBackendUtils..MODULE$.getInitialTargetExecutorNumber(this.sparkConf, org.apache.spark.scheduler.cluster.SchedulerBackendUtils..MODULE$.getInitialTargetExecutorNumber$default$2())));
   }

   private synchronized Map getPendingAtLocation(final String location) {
      HashMap allContainerRequests = new HashMap();
      this.rpIdToResourceProfile().keys().foreach((JFunction1.mcVI.sp)(id) -> {
         Resource profResource = (Resource)this.rpIdToYarnResource().get(BoxesRunTime.boxToInteger(id));
         Buffer result = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(this.amClient.getMatchingRequests(this.getContainerPriority(id), location, profResource)).asScala().flatMap((x$3) -> scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(x$3).asScala());
         allContainerRequests.update(BoxesRunTime.boxToInteger(id), result.toSeq());
      });
      return allContainerRequests.toMap(scala..less.colon.less..MODULE$.refl());
   }

   private synchronized void createYarnResourceForResourceProfile(final ResourceProfile rp) {
      if (!this.rpIdToYarnResource().containsKey(BoxesRunTime.boxToInteger(rp.id()))) {
         this.getOrUpdateRunningExecutorForRPId(rp.id());
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Resource profile ", " doesn't exist, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(rp.id()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"adding it"})))).log(scala.collection.immutable.Nil..MODULE$))));
         ResourceProfile.ExecutorResourcesOrDefaults resourcesWithDefaults = .MODULE$.getResourcesForClusterManager(rp.id(), rp.executorResources(), this.minMemoryOverhead(), this.memoryOverheadFactor(), this.sparkConf, this.isPythonApp(), this.resourceNameMapping());
         Map customSparkResources = (Map)resourcesWithDefaults.customResources().map((x0$1) -> {
            if (x0$1 != null) {
               String name = (String)x0$1._1();
               ExecutorResourceRequest execReq = (ExecutorResourceRequest)x0$1._2();
               return new Tuple2(name, Long.toString(execReq.amount()));
            } else {
               throw new MatchError(x0$1);
            }
         });
         Map var10000;
         if (rp.id() == .MODULE$.DEFAULT_RESOURCE_PROFILE_ID()) {
            String gpuResource = (String)this.sparkConf.get(package$.MODULE$.YARN_GPU_DEVICE());
            String fpgaResource = (String)this.sparkConf.get(package$.MODULE$.YARN_FPGA_DEVICE());
            var10000 = (Map)ResourceRequestHelper$.MODULE$.getYarnResourcesAndAmounts(this.sparkConf, package$.MODULE$.YARN_EXECUTOR_RESOURCE_TYPES_PREFIX()).$plus$plus((IterableOnce)customSparkResources.filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$createYarnResourceForResourceProfile$3(gpuResource, fpgaResource, x0$2))));
         } else {
            var10000 = customSparkResources;
         }

         Map customResources = var10000;
         scala.Predef..MODULE$.assert(resourcesWithDefaults.cores().nonEmpty());
         Resource resource = Resource.newInstance((int)resourcesWithDefaults.totalMemMiB(), BoxesRunTime.unboxToInt(resourcesWithDefaults.cores().get()));
         ResourceRequestHelper$.MODULE$.setResourceRequests(customResources, resource);
         this.logDebug((Function0)(() -> "Created resource capability: " + resource));
         this.rpIdToYarnResource().putIfAbsent(BoxesRunTime.boxToInteger(rp.id()), resource);
         this.rpIdToResourceProfile().update(BoxesRunTime.boxToInteger(rp.id()), rp);
      }
   }

   public synchronized boolean requestTotalExecutorsWithPreferredLocalities(final Map resourceProfileToTotalExecs, final Map numLocalityAwareTasksPerResourceProfileId, final Map hostToLocalTaskCountPerResourceProfileId, final Set excludedNodes) {
      this.numLocalityAwareTasksPerResourceProfileId_$eq(numLocalityAwareTasksPerResourceProfileId);
      this.hostToLocalTaskCountPerResourceProfileId_$eq(hostToLocalTaskCountPerResourceProfileId);
      if (resourceProfileToTotalExecs.isEmpty()) {
         this.targetNumExecutorsPerResourceProfileId().keys().foreach((JFunction1.mcVI.sp)(rp) -> this.targetNumExecutorsPerResourceProfileId().update(BoxesRunTime.boxToInteger(rp), BoxesRunTime.boxToInteger(0)));
         this.allocatorNodeHealthTracker().setSchedulerExcludedNodes(excludedNodes);
         return true;
      } else {
         Iterable res = (Iterable)resourceProfileToTotalExecs.map((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$requestTotalExecutorsWithPreferredLocalities$2(this, excludedNodes, x0$1)));
         return res.exists((x$4) -> BoxesRunTime.boxToBoolean($anonfun$requestTotalExecutorsWithPreferredLocalities$4(BoxesRunTime.unboxToBoolean(x$4))));
      }
   }

   public void killExecutor(final String executorId) {
      synchronized(this){}

      try {
         Option var5 = this.executorIdToContainer().get(executorId);
         if (var5 instanceof Some var6) {
            Container container = (Container)var6.value();
            if (!this.releasedContainers().contains(container.getId())) {
               Tuple2 var9 = (Tuple2)this.containerIdToExecutorIdAndResourceProfileId().apply(container.getId());
               if (var9 == null) {
                  throw new MatchError(var9);
               }

               int rpId = var9._2$mcI$sp();
               this.internalReleaseContainer(container);
               BoxesRunTime.boxToBoolean(this.getOrUpdateRunningExecutorForRPId(rpId).remove(executorId));
               return;
            }
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempted to kill unknown executor "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "!"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } catch (Throwable var12) {
         throw var12;
      }

   }

   public synchronized void allocateResources() {
      this.updateResourceRequests();
      float progressIndicator = 0.1F;
      AllocateResponse allocateResponse = this.amClient.allocate(progressIndicator);
      List allocatedContainers = allocateResponse.getAllocatedContainers();
      this.allocatorNodeHealthTracker().setNumClusterNodes(allocateResponse.getNumClusterNodes());
      if (this.isYarnExecutorDecommissionEnabled()) {
         this.handleNodesInDecommissioningState(allocateResponse);
      }

      if (allocatedContainers.size() > 0) {
         this.logDebug((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Allocated containers: %d. Current executor count: %d. Launching executor count: %d. Cluster resources: %s."), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(allocatedContainers.size()), BoxesRunTime.boxToInteger(this.getNumExecutorsRunning()), BoxesRunTime.boxToInteger(this.getNumExecutorsStarting()), allocateResponse.getAvailableResources()}))));
         this.handleAllocatedContainers(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(allocatedContainers).asScala().toSeq());
      }

      List completedContainers = allocateResponse.getCompletedContainersStatuses();
      if (completedContainers.size() > 0) {
         this.logDebug((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Completed %d containers"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(completedContainers.size())}))));
         this.processCompletedContainers(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(completedContainers).asScala().toSeq());
         this.logDebug((Function0)(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Finished processing %d completed containers. Current running executor count: %d."), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(completedContainers.size()), BoxesRunTime.boxToInteger(this.getNumExecutorsRunning())}))));
      }
   }

   private void handleNodesInDecommissioningState(final AllocateResponse allocateResponse) {
      ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(allocateResponse.getUpdatedNodes()).asScala().filter((node) -> BoxesRunTime.boxToBoolean($anonfun$handleNodesInDecommissioningState$1(this, node)))).foreach((node) -> BoxesRunTime.boxToBoolean($anonfun$handleNodesInDecommissioningState$2(this, node)));
   }

   private String getHostAddress(final NodeReport nodeReport) {
      return nodeReport.getNodeId().getHost();
   }

   public synchronized void updateResourceRequests() {
      Map pendingAllocatePerResourceProfileId = this.getPendingAllocate();
      Map missingPerProfile = this.targetNumExecutorsPerResourceProfileId().map((x0$1) -> {
         if (x0$1 != null) {
            int rpId = x0$1._1$mcI$sp();
            int targetNum = x0$1._2$mcI$sp();
            int starting = this.getOrUpdateNumExecutorsStartingForRPId(rpId).get();
            int pending = ((SeqOps)pendingAllocatePerResourceProfileId.getOrElse(BoxesRunTime.boxToInteger(rpId), () -> (Seq)scala.package..MODULE$.Seq().empty())).size();
            int running = this.getOrUpdateRunningExecutorForRPId(rpId).size();
            this.logDebug((Function0)(() -> "Updating resource requests for ResourceProfile id: " + rpId + ", target: " + targetNum + ", pending: " + pending + ", running: " + running + ", executorsStarting: " + starting));
            return new Tuple2.mcII.sp(rpId, targetNum - pending - running - starting);
         } else {
            throw new MatchError(x0$1);
         }
      }).toMap(scala..less.colon.less..MODULE$.refl());
      missingPerProfile.foreach((x0$2) -> {
         $anonfun$updateResourceRequests$4(this, pendingAllocatePerResourceProfileId, x0$2);
         return BoxedUnit.UNIT;
      });
   }

   public void stop() {
      this.launcherPool().shutdownNow();
   }

   private String hostStr(final AMRMClient.ContainerRequest request) {
      Option var3 = scala.Option..MODULE$.apply(request.getNodes());
      if (var3 instanceof Some var4) {
         List nodes = (List)var4.value();
         return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(nodes).asScala().mkString(",");
      } else if (scala.None..MODULE$.equals(var3)) {
         return "Any";
      } else {
         throw new MatchError(var3);
      }
   }

   private AMRMClient.ContainerRequest createContainerRequest(final Resource resource, final String[] nodes, final String[] racks, final int rpId) {
      return new AMRMClient.ContainerRequest(resource, nodes, racks, this.getContainerPriority(rpId), true, (String)this.labelExpression().orNull(scala..less.colon.less..MODULE$.refl()));
   }

   public void handleAllocatedContainers(final Seq allocatedContainers) {
      ArrayBuffer containersToUse = new ArrayBuffer(allocatedContainers.size());
      ArrayBuffer remainingAfterHostMatches = new ArrayBuffer();
      allocatedContainers.foreach((allocatedContainer) -> {
         $anonfun$handleAllocatedContainers$1(this, containersToUse, remainingAfterHostMatches, allocatedContainer);
         return BoxedUnit.UNIT;
      });
      ArrayBuffer remainingAfterRackMatches = new ArrayBuffer();
      if (remainingAfterHostMatches.nonEmpty()) {
         ObjectRef exception = ObjectRef.create(scala.None..MODULE$);
         Thread thread = new Thread(remainingAfterHostMatches, containersToUse, remainingAfterRackMatches, exception) {
            // $FF: synthetic field
            private final YarnAllocator $outer;
            private final ArrayBuffer remainingAfterHostMatches$1;
            private final ArrayBuffer containersToUse$1;
            private final ArrayBuffer remainingAfterRackMatches$1;
            private final ObjectRef exception$1;

            public void run() {
               try {
                  this.remainingAfterHostMatches$1.foreach((allocatedContainer) -> {
                     $anonfun$run$1(this, allocatedContainer);
                     return BoxedUnit.UNIT;
                  });
               } catch (Throwable var2) {
                  this.exception$1.elem = new Some(var2);
               }

            }

            // $FF: synthetic method
            public static final void $anonfun$run$1(final Object $this, final Container allocatedContainer) {
               String rack = $this.$outer.org$apache$spark$deploy$yarn$YarnAllocator$$resolver.resolve(allocatedContainer.getNodeId().getHost());
               $this.$outer.org$apache$spark$deploy$yarn$YarnAllocator$$matchContainerToRequest(allocatedContainer, rack, $this.containersToUse$1, $this.remainingAfterRackMatches$1);
            }

            public {
               if (YarnAllocator.this == null) {
                  throw null;
               } else {
                  this.$outer = YarnAllocator.this;
                  this.remainingAfterHostMatches$1 = remainingAfterHostMatches$1;
                  this.containersToUse$1 = containersToUse$1;
                  this.remainingAfterRackMatches$1 = remainingAfterRackMatches$1;
                  this.exception$1 = exception$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
         thread.setDaemon(true);
         thread.start();

         try {
            thread.join();
         } catch (InterruptedException var9) {
            thread.interrupt();
            throw var9;
         }

         if (((Option)exception.elem).isDefined()) {
            throw (Throwable)((Option)exception.elem).get();
         }
      }

      ArrayBuffer remainingAfterOffRackMatches = new ArrayBuffer();
      remainingAfterRackMatches.foreach((allocatedContainer) -> {
         $anonfun$handleAllocatedContainers$2(this, containersToUse, remainingAfterOffRackMatches, allocatedContainer);
         return BoxedUnit.UNIT;
      });
      if (remainingAfterOffRackMatches.nonEmpty()) {
         this.logDebug((Function0)(() -> "Releasing " + remainingAfterOffRackMatches.size() + " unneeded containers that were allocated to us"));
         remainingAfterOffRackMatches.foreach((container) -> {
            $anonfun$handleAllocatedContainers$4(this, container);
            return BoxedUnit.UNIT;
         });
      }

      this.runAllocatedContainers(containersToUse);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received ", " containers from YARN, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(allocatedContainers.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"launching executors on ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTOR_LAUNCH..MODULE$, BoxesRunTime.boxToInteger(containersToUse.size()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"of them."})))).log(scala.collection.immutable.Nil..MODULE$))));
   }

   public void org$apache$spark$deploy$yarn$YarnAllocator$$matchContainerToRequest(final Container allocatedContainer, final String location, final ArrayBuffer containersToUse, final ArrayBuffer remaining) {
      int rpId = this.getResourceProfileIdFromPriority(allocatedContainer.getPriority());
      Resource resourceForRP = (Resource)this.rpIdToYarnResource().get(BoxesRunTime.boxToInteger(rpId));
      this.logDebug((Function0)(() -> "Calling amClient.getMatchingRequests with parameters: priority: " + allocatedContainer.getPriority() + ", location: " + location + ", resource: " + resourceForRP));
      List matchingRequests = this.amClient.getMatchingRequests(allocatedContainer.getPriority(), location, resourceForRP);
      if (!matchingRequests.isEmpty()) {
         AMRMClient.ContainerRequest containerRequest = (AMRMClient.ContainerRequest)((Collection)matchingRequests.get(0)).iterator().next();
         this.logDebug((Function0)(() -> "Removing container request via AM client: " + containerRequest));
         this.amClient.removeContainerRequest(containerRequest);
         containersToUse.$plus$eq(allocatedContainer);
      } else {
         remaining.$plus$eq(allocatedContainer);
      }
   }

   private synchronized void runAllocatedContainers(final ArrayBuffer containersToUse) {
      containersToUse.foreach((container) -> {
         $anonfun$runAllocatedContainers$1(this, container);
         return BoxedUnit.UNIT;
      });
   }

   private void updateInternalState(final int rpId, final String executorId, final Container container) {
      synchronized(this){}

      try {
         ContainerId containerId = container.getId();
         if (this.launchingExecutorContainerIds().contains(containerId)) {
            this.getOrUpdateRunningExecutorForRPId(rpId).add(executorId);
            this.executorIdToContainer().update(executorId, container);
            this.containerIdToExecutorIdAndResourceProfileId().update(containerId, new Tuple2(executorId, BoxesRunTime.boxToInteger(rpId)));
            HashMap localallocatedHostToContainersMap = this.getOrUpdateAllocatedHostToContainersMapForRPId(rpId);
            String executorHostname = container.getNodeId().getHost();
            scala.collection.mutable.Set containerSet = (scala.collection.mutable.Set)localallocatedHostToContainersMap.getOrElseUpdate(executorHostname, () -> new HashSet());
            containerSet.$plus$eq(containerId);
            this.allocatedContainerToHostMap().put(containerId, executorHostname);
            BoxesRunTime.boxToBoolean(this.launchingExecutorContainerIds().remove(containerId));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.getOrUpdateNumExecutorsStartingForRPId(rpId).decrementAndGet();
      } catch (Throwable var10) {
         throw var10;
      }

   }

   public synchronized void processCompletedContainers(final Seq completedContainers) {
      completedContainers.foreach((completedContainer) -> {
         $anonfun$processCompletedContainers$1(this, completedContainer);
         return BoxedUnit.UNIT;
      });
   }

   public void enqueueGetLossReasonRequest(final String eid, final RpcCallContext context) {
      synchronized(this){}

      try {
         if (this.executorIdToContainer().contains(eid)) {
            ((Growable)this.pendingLossReasonRequests().getOrElseUpdate(eid, () -> new ArrayBuffer())).$plus$eq(context);
         } else if (this.releasedExecutorLossReasons().contains(eid)) {
            context.reply(this.releasedExecutorLossReasons().remove(eid).get());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Tried to get the loss reason for non-existent executor "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, eid)}))))));
            context.sendFailure(new SparkException("Fail to find loss reason for non-existent executor " + eid));
            BoxedUnit var6 = BoxedUnit.UNIT;
         }
      } catch (Throwable var5) {
         throw var5;
      }

   }

   private synchronized void internalReleaseContainer(final Container container) {
      this.releasedContainers().add(container.getId());
      this.amClient.releaseAssignedContainer(container.getId());
   }

   public synchronized long getNumUnexpectedContainerRelease() {
      return this.numUnexpectedContainerRelease();
   }

   public synchronized int getNumPendingLossReasonRequests() {
      return this.pendingLossReasonRequests().size();
   }

   private Tuple3 splitPendingAllocationsByLocality(final Map hostToLocalTaskCount, final Seq pendingAllocations) {
      ArrayBuffer localityMatched = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      ArrayBuffer localityUnMatched = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      ArrayBuffer localityFree = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      Set preferredHosts = hostToLocalTaskCount.keySet();
      pendingAllocations.foreach((cr) -> {
         List nodes = cr.getNodes();
         if (nodes == null) {
            return (ArrayBuffer)localityFree.$plus$eq(cr);
         } else {
            return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(nodes).asScala().toSet().intersect(preferredHosts).nonEmpty() ? (ArrayBuffer)localityMatched.$plus$eq(cr) : (ArrayBuffer)localityUnMatched.$plus$eq(cr);
         }
      });
      return new Tuple3(localityMatched.toSeq(), localityUnMatched.toSeq(), localityFree.toSeq());
   }

   // $FF: synthetic method
   public static final int $anonfun$getNumExecutorsRunning$1(final scala.collection.mutable.Set x$1) {
      return x$1.size();
   }

   // $FF: synthetic method
   public static final int $anonfun$getNumExecutorsStarting$1(final AtomicInteger x$2) {
      return x$2.get();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createYarnResourceForResourceProfile$3(final String gpuResource$1, final String fpgaResource$1, final Tuple2 x0$2) {
      if (x0$2 == null) {
         throw new MatchError(x0$2);
      } else {
         boolean var10000;
         label39: {
            String r = (String)x0$2._1();
            if (r == null) {
               if (gpuResource$1 == null) {
                  break label39;
               }
            } else if (r.equals(gpuResource$1)) {
               break label39;
            }

            if (r == null) {
               if (fpgaResource$1 == null) {
                  break label39;
               }
            } else if (r.equals(fpgaResource$1)) {
               break label39;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$requestTotalExecutorsWithPreferredLocalities$2(final YarnAllocator $this, final Set excludedNodes$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         ResourceProfile rp = (ResourceProfile)x0$1._1();
         int numExecs = x0$1._2$mcI$sp();
         $this.createYarnResourceForResourceProfile(rp);
         if (numExecs != $this.getOrUpdateTargetNumExecutorsForRPId(rp.id())) {
            $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver requested a total number of ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(numExecs))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"executor(s) for resource profile id: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(rp.id()))}))))));
            $this.targetNumExecutorsPerResourceProfileId().update(BoxesRunTime.boxToInteger(rp.id()), BoxesRunTime.boxToInteger(numExecs));
            $this.allocatorNodeHealthTracker().setSchedulerExcludedNodes(excludedNodes$1);
            return true;
         } else {
            return false;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$requestTotalExecutorsWithPreferredLocalities$4(final boolean x$4) {
      return x$4;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleNodesInDecommissioningState$1(final YarnAllocator $this, final NodeReport node) {
      boolean var3;
      label18: {
         NodeState var10000 = node.getNodeState();
         NodeState var2 = NodeState.DECOMMISSIONING;
         if (var10000 == null) {
            if (var2 != null) {
               break label18;
            }
         } else if (!var10000.equals(var2)) {
            break label18;
         }

         if (!$this.decommissioningNodesCache().containsKey($this.getHostAddress(node))) {
            var3 = true;
            return var3;
         }
      }

      var3 = false;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$handleNodesInDecommissioningState$2(final YarnAllocator $this, final NodeReport node) {
      String host = $this.getHostAddress(node);
      $this.driverRef.send(new CoarseGrainedClusterMessages.DecommissionExecutorsOnHost(host));
      return BoxesRunTime.unboxToBoolean($this.decommissioningNodesCache().put(host, BoxesRunTime.boxToBoolean(true)));
   }

   // $FF: synthetic method
   public static final void $anonfun$updateResourceRequests$8(final YarnAllocator $this, final AMRMClient.ContainerRequest stale) {
      $this.amClient.removeContainerRequest(stale);
   }

   // $FF: synthetic method
   public static final ArrayBuffer $anonfun$updateResourceRequests$12(final YarnAllocator $this, final ArrayBuffer newLocalityRequests$1, final Resource resource$2, final int rpId$2, final int i) {
      return (ArrayBuffer)newLocalityRequests$1.$plus$eq($this.createContainerRequest(resource$2, (String[])null, (String[])null, rpId$2));
   }

   // $FF: synthetic method
   public static final void $anonfun$updateResourceRequests$13(final YarnAllocator $this, final AMRMClient.ContainerRequest nonLocal) {
      $this.amClient.removeContainerRequest(nonLocal);
   }

   // $FF: synthetic method
   public static final void $anonfun$updateResourceRequests$15(final YarnAllocator $this, final AMRMClient.ContainerRequest request) {
      $this.amClient.addContainerRequest(request);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateResourceRequests$16(final AMRMClient.ContainerRequest x$6) {
      return x$6.getNodes() != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$updateResourceRequests$18(final YarnAllocator $this, final AMRMClient.ContainerRequest request) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitted container request for host "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, $this.hostStr(request))}))))));
   }

   // $FF: synthetic method
   public static final void $anonfun$updateResourceRequests$21(final YarnAllocator $this, final AMRMClient.ContainerRequest x$1) {
      $this.amClient.removeContainerRequest(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$updateResourceRequests$4(final YarnAllocator $this, final Map pendingAllocatePerResourceProfileId$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         int rpId = x0$2._1$mcI$sp();
         int missing = x0$2._2$mcI$sp();
         Map hostToLocalTaskCount = (Map)$this.hostToLocalTaskCountPerResourceProfileId().getOrElse(BoxesRunTime.boxToInteger(rpId), () -> scala.Predef..MODULE$.Map().empty());
         Seq pendingAllocate = (Seq)pendingAllocatePerResourceProfileId$1.getOrElse(BoxesRunTime.boxToInteger(rpId), () -> (Seq)scala.package..MODULE$.Seq().empty());
         int numPendingAllocate = pendingAllocate.size();
         Tuple3 var13 = $this.splitPendingAllocationsByLocality(hostToLocalTaskCount, pendingAllocate);
         if (var13 != null) {
            Seq localRequests = (Seq)var13._1();
            Seq staleRequests = (Seq)var13._2();
            Seq anyHostRequests = (Seq)var13._3();
            Tuple3 var12 = new Tuple3(localRequests, staleRequests, anyHostRequests);
            Seq localRequests = (Seq)var12._1();
            Seq staleRequests = (Seq)var12._2();
            Seq anyHostRequests = (Seq)var12._3();
            if (missing > 0) {
               Resource resource = (Resource)$this.rpIdToYarnResource().get(BoxesRunTime.boxToInteger(rpId));
               if ($this.log().isInfoEnabled()) {
                  ObjectRef requestContainerMessage = ObjectRef.create($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Will request ", " executor "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(missing))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"container(s) for ResourceProfile Id: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(rpId))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"each with ", " core(s) and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VIRTUAL_CORES..MODULE$, BoxesRunTime.boxToInteger(resource.getVirtualCores()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " MB memory."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, BoxesRunTime.boxToLong(resource.getMemorySize()))})))));
                  if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])resource.getResources()))) {
                     requestContainerMessage.elem = ((MessageWithContext)requestContainerMessage.elem).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with custom resources: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.YARN_RESOURCE..MODULE$, resource)}))));
                  }

                  $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> (MessageWithContext)requestContainerMessage.elem));
               }

               staleRequests.foreach((stale) -> {
                  $anonfun$updateResourceRequests$8($this, stale);
                  return BoxedUnit.UNIT;
               });
               int cancelledContainers = staleRequests.size();
               if (cancelledContainers > 0) {
                  $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Canceled ", " container request(s) "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(cancelledContainers))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(locality no longer needed)"})))).log(scala.collection.immutable.Nil..MODULE$))));
               }

               int availableContainers = missing + cancelledContainers;
               int potentialContainers = availableContainers + anyHostRequests.size();
               HashMap allocatedHostToContainer = $this.getOrUpdateAllocatedHostToContainersMapForRPId(rpId);
               int numLocalityAwareTasks = BoxesRunTime.unboxToInt($this.numLocalityAwareTasksPerResourceProfileId().getOrElse(BoxesRunTime.boxToInteger(rpId), (JFunction0.mcI.sp)() -> 0));
               ContainerLocalityPreferences[] containerLocalityPreferences = $this.containerPlacementStrategy().localityOfRequestedContainers(potentialContainers, numLocalityAwareTasks, hostToLocalTaskCount, allocatedHostToContainer, localRequests, (ResourceProfile)$this.rpIdToResourceProfile().apply(BoxesRunTime.boxToInteger(rpId)));
               ArrayBuffer newLocalityRequests = new ArrayBuffer();
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(containerLocalityPreferences), (x0$3) -> {
                  if (x0$3 != null) {
                     String[] nodes = x0$3.nodes();
                     String[] racks = x0$3.racks();
                     if (nodes != null) {
                        return newLocalityRequests.$plus$eq($this.createContainerRequest(resource, nodes, racks, rpId));
                     }
                  }

                  return BoxedUnit.UNIT;
               });
               if (availableContainers >= newLocalityRequests.size()) {
                  scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), availableContainers - newLocalityRequests.size()).foreach((i) -> $anonfun$updateResourceRequests$12($this, newLocalityRequests, resource, rpId, BoxesRunTime.unboxToInt(i)));
               } else {
                  int numToCancel = newLocalityRequests.size() - availableContainers;
                  ((IterableOnceOps)anyHostRequests.slice(0, numToCancel)).foreach((nonLocal) -> {
                     $anonfun$updateResourceRequests$13($this, nonLocal);
                     return BoxedUnit.UNIT;
                  });
                  if (numToCancel > 0) {
                     $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Canceled ", " unlocalized container "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(numToCancel))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"requests to resubmit with locality"})))).log(scala.collection.immutable.Nil..MODULE$))));
                  }
               }

               newLocalityRequests.foreach((request) -> {
                  $anonfun$updateResourceRequests$15($this, request);
                  return BoxedUnit.UNIT;
               });
               if ($this.log().isInfoEnabled()) {
                  Tuple2 var31 = newLocalityRequests.partition((x$6) -> BoxesRunTime.boxToBoolean($anonfun$updateResourceRequests$16(x$6)));
                  if (var31 != null) {
                     ArrayBuffer localized = (ArrayBuffer)var31._1();
                     ArrayBuffer anyHost = (ArrayBuffer)var31._2();
                     Tuple2 var30 = new Tuple2(localized, anyHost);
                     ArrayBuffer localized = (ArrayBuffer)var30._1();
                     ArrayBuffer anyHost = (ArrayBuffer)var30._2();
                     if (anyHost.nonEmpty()) {
                        $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitted ", "} unlocalized container "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(anyHost.size()))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"requests."})))).log(scala.collection.immutable.Nil..MODULE$))));
                     }

                     localized.foreach((request) -> {
                        $anonfun$updateResourceRequests$18($this, request);
                        return BoxedUnit.UNIT;
                     });
                     BoxedUnit var40 = BoxedUnit.UNIT;
                  } else {
                     throw new MatchError(var31);
                  }
               } else {
                  BoxedUnit var39 = BoxedUnit.UNIT;
               }
            } else if (numPendingAllocate > 0 && missing < 0) {
               int numToCancel = scala.math.package..MODULE$.min(numPendingAllocate, -missing);
               $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Canceling requests for ", " executor "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(numToCancel))}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"container(s) to have a new desired total "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " executors."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTOR_DESIRED..MODULE$, BoxesRunTime.boxToInteger($this.getOrUpdateTargetNumExecutorsForRPId(rpId)))}))))));
               Seq cancelRequests = (Seq)((IterableOps)((IterableOps)staleRequests.$plus$plus(anyHostRequests)).$plus$plus(localRequests)).take(numToCancel);
               cancelRequests.foreach((x$1) -> {
                  $anonfun$updateResourceRequests$21($this, x$1);
                  return BoxedUnit.UNIT;
               });
               BoxedUnit var38 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(var13);
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$handleAllocatedContainers$1(final YarnAllocator $this, final ArrayBuffer containersToUse$1, final ArrayBuffer remainingAfterHostMatches$1, final Container allocatedContainer) {
      $this.org$apache$spark$deploy$yarn$YarnAllocator$$matchContainerToRequest(allocatedContainer, allocatedContainer.getNodeId().getHost(), containersToUse$1, remainingAfterHostMatches$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$handleAllocatedContainers$2(final YarnAllocator $this, final ArrayBuffer containersToUse$1, final ArrayBuffer remainingAfterOffRackMatches$1, final Container allocatedContainer) {
      $this.org$apache$spark$deploy$yarn$YarnAllocator$$matchContainerToRequest(allocatedContainer, YarnSparkHadoopUtil$.MODULE$.ANY_HOST(), containersToUse$1, remainingAfterOffRackMatches$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$handleAllocatedContainers$4(final YarnAllocator $this, final Container container) {
      $this.internalReleaseContainer(container);
   }

   // $FF: synthetic method
   public static final long $anonfun$runAllocatedContainers$3(final ExecutorResourceRequest x$8) {
      return x$8.amount();
   }

   // $FF: synthetic method
   public static final void $anonfun$runAllocatedContainers$1(final YarnAllocator $this, final Container container) {
      int rpId = $this.getResourceProfileIdFromPriority(container.getPriority());
      $this.executorIdCounter_$eq($this.executorIdCounter() + 1);
      String executorHostname = container.getNodeId().getHost();
      ContainerId containerId = container.getId();
      String executorId = Integer.toString($this.executorIdCounter());
      Resource yarnResourceForRpId = (Resource)$this.rpIdToYarnResource().get(BoxesRunTime.boxToInteger(rpId));
      scala.Predef..MODULE$.assert(container.getResource().getMemorySize() >= yarnResourceForRpId.getMemorySize());
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Launching container ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_ID..MODULE$, containerId)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"on host ", " for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, executorHostname)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"executor with ID ", " for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ResourceProfile Id ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_PROFILE_ID..MODULE$, BoxesRunTime.boxToInteger(rpId))}))))));
      ResourceProfile rp = (ResourceProfile)$this.rpIdToResourceProfile().apply(BoxesRunTime.boxToInteger(rpId));
      ResourceProfile.DefaultProfileExecutorResources defaultResources = .MODULE$.getDefaultProfileExecutorResources($this.sparkConf);
      int containerMem = (int)BoxesRunTime.unboxToLong(rp.executorResources().get(.MODULE$.MEMORY()).map((x$8) -> BoxesRunTime.boxToLong($anonfun$runAllocatedContainers$3(x$8))).getOrElse((JFunction0.mcJ.sp)() -> defaultResources.executorMemoryMiB()));
      scala.Predef..MODULE$.assert(defaultResources.cores().nonEmpty());
      int defaultCores = BoxesRunTime.unboxToInt(defaultResources.cores().get());
      int containerCores = BoxesRunTime.unboxToInt(rp.getExecutorCores().getOrElse((JFunction0.mcI.sp)() -> defaultCores));
      int rpRunningExecs = $this.getOrUpdateRunningExecutorForRPId(rpId).size();
      if (rpRunningExecs < $this.getOrUpdateTargetNumExecutorsForRPId(rpId)) {
         $this.getOrUpdateNumExecutorsStartingForRPId(rpId).incrementAndGet();
         $this.launchingExecutorContainerIds().add(containerId);
         if ($this.launchContainers()) {
            $this.launcherPool().execute(() -> {
               try {
                  (new ExecutorRunnable(new Some(container), $this.conf, $this.sparkConf, $this.driverUrl, executorId, executorHostname, containerMem, containerCores, $this.appAttemptId.getApplicationId().toString(), $this.securityMgr, $this.localResources, rp.id())).run();
                  $this.updateInternalState(rpId, executorId, container);
               } catch (Throwable var10) {
                  $this.getOrUpdateNumExecutorsStartingForRPId(rpId).decrementAndGet();
                  $this.launchingExecutorContainerIds().remove(containerId);
                  if (!scala.util.control.NonFatal..MODULE$.apply(var10)) {
                     throw var10;
                  }

                  $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to launch executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"on container ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_ID..MODULE$, containerId)}))))), var10);
                  $this.amClient.releaseAssignedContainer(containerId);
               }

            });
         } else {
            $this.updateInternalState(rpId, executorId, container);
         }
      } else {
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skip launching executorRunnable as running executors count: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " reached target executors count: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToInteger(rpRunningExecs))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTOR_TARGET..MODULE$, BoxesRunTime.boxToInteger($this.getOrUpdateTargetNumExecutorsForRPId(rpId)))}))))));
         $this.internalReleaseContainer(container);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$processCompletedContainers$13(final YarnAllocator $this, final int rpId$4, final ContainerId containerId$2, final String host) {
      $this.getOrUpdateAllocatedHostToContainersMapForRPId(rpId$4).get(host).foreach((containerSet) -> {
         containerSet.remove(containerId$2);
         if (containerSet.isEmpty()) {
            $this.getOrUpdateAllocatedHostToContainersMapForRPId(rpId$4).remove(host);
         } else {
            $this.getOrUpdateAllocatedHostToContainersMapForRPId(rpId$4).update(host, containerSet);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         return $this.allocatedContainerToHostMap().remove(containerId$2);
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$processCompletedContainers$16(final ExecutorExited exitReason$1, final RpcCallContext x$12) {
      x$12.reply(exitReason$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$processCompletedContainers$15(final YarnAllocator $this, final ExecutorExited exitReason$1, final boolean alreadyReleased$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String eid = (String)x0$1._1();
         $this.executorIdToContainer().remove(eid);
         Option var8 = $this.pendingLossReasonRequests().remove(eid);
         if (var8 instanceof Some) {
            Some var9 = (Some)var8;
            Buffer pendingRequests = (Buffer)var9.value();
            pendingRequests.foreach((x$12) -> {
               $anonfun$processCompletedContainers$16(exitReason$1, x$12);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (!scala.None..MODULE$.equals(var8)) {
               throw new MatchError(var8);
            }

            $this.releasedExecutorLossReasons().put(eid, exitReason$1);
         }

         if (!alreadyReleased$1) {
            $this.numUnexpectedContainerRelease_$eq($this.numUnexpectedContainerRelease() + 1L);
            $this.driverRef.send(new CoarseGrainedClusterMessages.RemoveExecutor(eid, exitReason$1));
            BoxedUnit var12 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var11 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$processCompletedContainers$1(final YarnAllocator $this, final ContainerStatus completedContainer) {
      ContainerId containerId = completedContainer.getContainerId();
      $this.launchingExecutorContainerIds().remove(containerId);
      Tuple2 var8 = (Tuple2)$this.containerIdToExecutorIdAndResourceProfileId().getOrElse(containerId, () -> new Tuple2("", BoxesRunTime.boxToInteger(.MODULE$.DEFAULT_RESOURCE_PROFILE_ID())));
      if (var8 == null) {
         throw new MatchError(var8);
      } else {
         int rpId = var8._2$mcI$sp();
         boolean alreadyReleased = $this.releasedContainers().remove(containerId);
         Option hostOpt = $this.allocatedContainerToHostMap().get(containerId);
         String onHostStr = (String)hostOpt.map((host) -> " on host: " + host).getOrElse(() -> "");
         ExecutorExited var37;
         if (!alreadyReleased) {
            label70: {
               Option var14 = $this.containerIdToExecutorIdAndResourceProfileId().get(containerId);
               if (var14 instanceof Some) {
                  Some var15 = (Some)var14;
                  Tuple2 var16 = (Tuple2)var15.value();
                  if (var16 != null) {
                     String executorId = (String)var16._1();
                     BoxesRunTime.boxToBoolean($this.getOrUpdateRunningExecutorForRPId(rpId).remove(executorId));
                     break label70;
                  }
               }

               if (!scala.None..MODULE$.equals(var14)) {
                  throw new MatchError(var14);
               }

               $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot find executorId for container: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_ID..MODULE$, containerId)}))))));
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Completed container ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_ID..MODULE$, containerId)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, onHostStr)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(state: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_STATE..MODULE$, completedContainer.getState())})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"exit status: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(completedContainer.getExitStatus()))}))))));
            int exitStatus = completedContainer.getExitStatus();
            Tuple2 var34;
            if ($this.shutdown()) {
               var34 = new Tuple2(BoxesRunTime.boxToBoolean(false), $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor for container ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_ID..MODULE$, containerId)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"exited after Application shutdown."})))).log(scala.collection.immutable.Nil..MODULE$)));
            } else if (0 == exitStatus) {
               var34 = new Tuple2(BoxesRunTime.boxToBoolean(false), $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor for container ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_ID..MODULE$, containerId)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"exited because of a YARN event (e.g., preemption) and not because of an "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"error in the running job."})))).log(scala.collection.immutable.Nil..MODULE$)));
            } else if (-102 == exitStatus) {
               var34 = new Tuple2(BoxesRunTime.boxToBoolean(false), $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Container ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_ID..MODULE$, containerId)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " was preempted."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, onHostStr)})))));
            } else if (-103 == exitStatus) {
               StringOps var35 = scala.collection.StringOps..MODULE$;
               Predef var10001 = scala.Predef..MODULE$;
               String var10002 = YarnAllocator$.MODULE$.MEM_REGEX();
               Regex vmemExceededPattern = var35.r$extension(var10001.augmentString(var10002 + " of " + YarnAllocator$.MODULE$.MEM_REGEX() + " virtual memory used"));
               String diag = (String)vmemExceededPattern.findFirstIn(completedContainer.getDiagnostics()).map((x$9) -> x$9.concat(".")).getOrElse(() -> "");
               MessageWithContext message = $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Container killed by YARN for exceeding virtual memory limits. "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " Consider boosting "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, diag)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " or boosting "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY_OVERHEAD().key())})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " or disabling "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, "yarn.nodemanager.vmem-pmem-ratio")})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG3..MODULE$, "yarn.nodemanager.vmem-check-enabled")})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because of YARN-4714."})))).log(scala.collection.immutable.Nil..MODULE$));
               var34 = new Tuple2(BoxesRunTime.boxToBoolean(true), message);
            } else if (-104 == exitStatus) {
               StringOps var36 = scala.collection.StringOps..MODULE$;
               Predef var38 = scala.Predef..MODULE$;
               String var39 = YarnAllocator$.MODULE$.MEM_REGEX();
               Regex pmemExceededPattern = var36.r$extension(var38.augmentString(var39 + " of " + YarnAllocator$.MODULE$.MEM_REGEX() + " physical memory used"));
               String diag = (String)pmemExceededPattern.findFirstIn(completedContainer.getDiagnostics()).map((x$10) -> x$10.concat(".")).getOrElse(() -> "");
               MessageWithContext message = $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Container killed by YARN for exceeding physical memory limits. "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " Consider boosting "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, diag)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY_OVERHEAD().key())}))));
               var34 = new Tuple2(BoxesRunTime.boxToBoolean(true), message);
            } else {
               int exitStatus = completedContainer.getExitStatus();
               String sparkExitCodeReason = org.apache.spark.executor.ExecutorExitCode..MODULE$.explainExitCode(exitStatus);
               if (YarnAllocator$.MODULE$.NOT_APP_AND_SYSTEM_FAULT_EXIT_STATUS().contains(BoxesRunTime.boxToInteger(exitStatus))) {
                  var34 = new Tuple2(BoxesRunTime.boxToBoolean(false), $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Container marked as failed: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_ID..MODULE$, containerId)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, onHostStr)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exit status: ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(exitStatus))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Possible causes: ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, sparkExitCodeReason)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Diagnostics: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, completedContainer.getDiagnostics())})))));
               } else {
                  $this.allocatorNodeHealthTracker().handleResourceAllocationFailure(hostOpt);
                  var34 = new Tuple2(BoxesRunTime.boxToBoolean(true), $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Container from a bad node: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONTAINER_ID..MODULE$, containerId)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, onHostStr)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exit status: ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(exitStatus))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Possible causes: ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, sparkExitCodeReason)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Diagnostics: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, completedContainer.getDiagnostics())})))));
               }
            }

            Tuple2 var20 = var34;
            if (var20 == null) {
               throw new MatchError(var20);
            }

            boolean exitCausedByApp = var20._1$mcZ$sp();
            MessageWithContext containerExitReason = (MessageWithContext)var20._2();
            Tuple2 var19 = new Tuple2(BoxesRunTime.boxToBoolean(exitCausedByApp), containerExitReason);
            boolean exitCausedByApp = var19._1$mcZ$sp();
            MessageWithContext containerExitReason = (MessageWithContext)var19._2();
            if (exitCausedByApp) {
               $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> containerExitReason));
            } else {
               $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> containerExitReason));
            }

            var37 = new ExecutorExited(exitStatus, exitCausedByApp, containerExitReason.message());
         } else {
            var37 = new ExecutorExited(completedContainer.getExitStatus(), false, "Container " + containerId + " exited from explicit termination request.");
         }

         ExecutorExited exitReason = var37;
         hostOpt.foreach((host) -> {
            $anonfun$processCompletedContainers$13($this, rpId, containerId, host);
            return BoxedUnit.UNIT;
         });
         $this.containerIdToExecutorIdAndResourceProfileId().remove(containerId).foreach((x0$1) -> {
            $anonfun$processCompletedContainers$15($this, exitReason, alreadyReleased, x0$1);
            return BoxedUnit.UNIT;
         });
      }
   }

   public YarnAllocator(final String driverUrl, final RpcEndpointRef driverRef, final YarnConfiguration conf, final SparkConf sparkConf, final AMRMClient amClient, final ApplicationAttemptId appAttemptId, final SecurityManager securityMgr, final Map localResources, final SparkRackResolver resolver, final Clock clock) {
      boolean var10001;
      label39: {
         this.driverUrl = driverUrl;
         this.driverRef = driverRef;
         this.conf = conf;
         this.sparkConf = sparkConf;
         this.amClient = amClient;
         this.appAttemptId = appAttemptId;
         this.securityMgr = securityMgr;
         this.localResources = localResources;
         this.org$apache$spark$deploy$yarn$YarnAllocator$$resolver = resolver;
         super();
         Logging.$init$(this);
         this.allocatedHostToContainersMapPerRPId = new HashMap();
         this.allocatedContainerToHostMap = new HashMap();
         this.releasedContainers = (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         this.launchingExecutorContainerIds = (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         this.runningExecutorsPerResourceProfileId = new HashMap();
         this.numExecutorsStartingPerResourceProfileId = new HashMap();
         this.targetNumExecutorsPerResourceProfileId = new HashMap();
         this.pendingLossReasonRequests = new HashMap();
         this.releasedExecutorLossReasons = new HashMap();
         this.executorIdToContainer = new HashMap();
         this.numUnexpectedContainerRelease = 0L;
         this.containerIdToExecutorIdAndResourceProfileId = new HashMap();
         this.rpIdToYarnResource = new ConcurrentHashMap();
         this.rpIdToResourceProfile = new HashMap();
         this.hostToLocalTaskCountPerResourceProfileId = (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(.MODULE$.DEFAULT_RESOURCE_PROFILE_ID())), scala.Predef..MODULE$.Map().empty())})));
         this.numLocalityAwareTasksPerResourceProfileId = (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(.MODULE$.DEFAULT_RESOURCE_PROFILE_ID())), BoxesRunTime.boxToInteger(0))})));
         this.executorIdCounter = BoxesRunTime.unboxToInt(driverRef.askSync(org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages.RetrieveLastAllocatedExecutorId..MODULE$, scala.reflect.ClassTag..MODULE$.Int()));
         this.failureTracker = new ExecutorFailureTracker(sparkConf, clock);
         this.allocatorNodeHealthTracker = new YarnAllocatorNodeHealthTracker(sparkConf, amClient, this.failureTracker());
         this.isPythonApp = BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.IS_PYTHON_APP()));
         this.minMemoryOverhead = BoxesRunTime.unboxToLong(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MIN_MEMORY_OVERHEAD()));
         this.memoryOverheadFactor = BoxesRunTime.unboxToDouble(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY_OVERHEAD_FACTOR()));
         this.launcherPool = org.apache.spark.util.ThreadUtils..MODULE$.newDaemonCachedThreadPool("ContainerLauncher", BoxesRunTime.unboxToInt(sparkConf.get(package$.MODULE$.CONTAINER_LAUNCH_MAX_THREADS())), org.apache.spark.util.ThreadUtils..MODULE$.newDaemonCachedThreadPool$default$3());
         this.launchContainers = sparkConf.getBoolean("spark.yarn.launchContainers", true);
         this.labelExpression = (Option)sparkConf.get(package$.MODULE$.EXECUTOR_NODE_LABEL_EXPRESSION());
         this.resourceNameMapping = ResourceRequestHelper$.MODULE$.getResourceNameMapping(sparkConf);
         this.containerPlacementStrategy = new LocalityPreferredContainerPlacementStrategy(sparkConf, conf, resolver);
         Tuple2.mcZZ.sp var12 = new Tuple2.mcZZ.sp(BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.DECOMMISSION_ENABLED())), BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_SERVICE_ENABLED())));
         if (var12 != null) {
            boolean var13 = ((Tuple2)var12)._1$mcZ$sp();
            boolean var14 = ((Tuple2)var12)._2$mcZ$sp();
            if (var13 && !var14) {
               var10001 = true;
               break label39;
            }
         }

         if (var12 != null) {
            boolean var15 = ((Tuple2)var12)._1$mcZ$sp();
            boolean var16 = ((Tuple2)var12)._2$mcZ$sp();
            if (var15 && var16) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Yarn Executor Decommissioning is supported only "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"when ", " is set to false. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package..MODULE$.SHUFFLE_SERVICE_ENABLED().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"See: SPARK-39018."})))).log(scala.collection.immutable.Nil..MODULE$))));
               var10001 = false;
               break label39;
            }
         }

         if (var12 == null) {
            throw new MatchError(var12);
         }

         boolean var17 = ((Tuple2)var12)._1$mcZ$sp();
         if (var17) {
            throw new MatchError(var12);
         }

         var10001 = false;
      }

      this.isYarnExecutorDecommissionEnabled = var10001;
      this.decommissioningNodesCache = new LinkedHashMap() {
         public boolean removeEldestEntry(final java.util.Map.Entry entry) {
            return this.size() > YarnAllocator$.MODULE$.DECOMMISSIONING_NODES_CACHE_SIZE();
         }
      };
      this.shutdown = false;
      this.initDefaultProfile();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
