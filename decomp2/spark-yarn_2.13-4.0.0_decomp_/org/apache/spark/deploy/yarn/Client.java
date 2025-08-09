package org.apache.spark.deploy.yarn;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileContext;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.GlobPattern;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.PathOperationException;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.LocalResourceType;
import org.apache.hadoop.yarn.api.records.LogAggregationContext;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.api.records.ResourceRequest;
import org.apache.hadoop.yarn.api.records.Token;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.ApplicationNotFoundException;
import org.apache.hadoop.yarn.util.Records;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.security.HadoopDelegationTokenManager;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.launcher.JavaModuleOptions;
import org.apache.spark.launcher.LauncherBackend;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.YarnCommandBuilderUtils$;
import org.apache.spark.launcher.SparkAppHandle.State;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.util.CallerContext;
import org.apache.spark.util.YarnContainerInfoHelper$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015}f!CA\u0015\u0003W\u0001\u00111GA \u0011)\tI\u0006\u0001BC\u0002\u0013\u0005\u0011Q\f\u0005\u000b\u0003O\u0002!\u0011!Q\u0001\n\u0005}\u0003BCA5\u0001\t\u0015\r\u0011\"\u0001\u0002l!Q\u0011Q\u000f\u0001\u0003\u0002\u0003\u0006I!!\u001c\t\u0015\u0005]\u0004A!b\u0001\n\u0003\tI\b\u0003\u0006\u0002\b\u0002\u0011\t\u0011)A\u0005\u0003wBq!!#\u0001\t\u0003\tY\tC\u0005\u0002\u0016\u0002\u0011\r\u0011\"\u0003\u0002\u0018\"A\u0011q\u0016\u0001!\u0002\u0013\tI\nC\u0005\u00022\u0002\u0011\r\u0011\"\u0003\u00024\"A\u0011\u0011\u0019\u0001!\u0002\u0013\t)\fC\u0005\u0002D\u0002\u0011\r\u0011\"\u0003\u0002F\"A\u0011Q\u001a\u0001!\u0002\u0013\t9\rC\u0005\u0002P\u0002\u0011\r\u0011\"\u0003\u0002F\"A\u0011\u0011\u001b\u0001!\u0002\u0013\t9\rC\u0005\u0002T\u0002\u0011\r\u0011\"\u0003\u0002F\"A\u0011Q\u001b\u0001!\u0002\u0013\t9\rC\u0005\u0002X\u0002\u0011\r\u0011\"\u0003\u0002Z\"A\u0011\u0011\u001d\u0001!\u0002\u0013\tY\u000eC\u0006\u0002d\u0002\u0001\r\u00111A\u0005\n\u0005\u0015\bbCAw\u0001\u0001\u0007\t\u0019!C\u0005\u0003_D1\"a?\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0002h\"Y\u0011Q \u0001A\u0002\u0003\u0007I\u0011BA\u0000\u0011-\u0011i\u0001\u0001a\u0001\u0002\u0004%IAa\u0004\t\u0017\tM\u0001\u00011A\u0001B\u0003&!\u0011\u0001\u0005\n\u0005+\u0001!\u0019!C\u0005\u0005/A\u0001Ba\b\u0001A\u0003%!\u0011\u0004\u0005\n\u0005C\u0001!\u0019!C\u0005\u00033D\u0001Ba\t\u0001A\u0003%\u00111\u001c\u0005\n\u0005K\u0001!\u0019!C\u0005\u0005OA\u0001Ba\f\u0001A\u0003%!\u0011\u0006\u0005\n\u0005c\u0001!\u0019!C\u0005\u00033D\u0001Ba\r\u0001A\u0003%\u00111\u001c\u0005\n\u0005k\u0001!\u0019!C\u0005\u00033D\u0001Ba\u000e\u0001A\u0003%\u00111\u001c\u0005\n\u0005s\u0001!\u0019!C\u0005\u0005OA\u0001Ba\u000f\u0001A\u0003%!\u0011\u0006\u0005\n\u0005{\u0001!\u0019!C\t\u00033D\u0001Ba\u0010\u0001A\u0003%\u00111\u001c\u0005\n\u0005\u0003\u0002!\u0019!C\u0005\u0005/A\u0001Ba\u0011\u0001A\u0003%!\u0011\u0004\u0005\n\u0005\u000b\u0002!\u0019!C\u0005\u0005OA\u0001Ba\u0012\u0001A\u0003%!\u0011\u0006\u0005\n\u0005\u0013\u0002!\u0019!C\u0005\u00033D\u0001Ba\u0013\u0001A\u0003%\u00111\u001c\u0005\n\u0005\u001b\u0002!\u0019!C\u0005\u0003\u000bD\u0001Ba\u0014\u0001A\u0003%\u0011q\u0019\u0005\n\u0005#\u0002!\u0019!C\u0005\u00033D\u0001Ba\u0015\u0001A\u0003%\u00111\u001c\u0005\n\u0005+\u0002!\u0019!C\u0005\u0005/B\u0001Ba\u0018\u0001A\u0003%!\u0011\f\u0005\n\u0005C\u0002!\u0019!C\u0005\u0003WB\u0001Ba\u0019\u0001A\u0003%\u0011Q\u000e\u0005\n\u0005K\u0002!\u0019!C\u0005\u0005OB\u0001Ba \u0001A\u0003%!\u0011\u000e\u0005\n\u0005\u0003\u0003!\u0019!C\u0005\u0005\u0007C\u0001Ba#\u0001A\u0003%!Q\u0011\u0005\n\u0005\u001b\u0003!\u0019!C\u0005\u0005\u001fC\u0001B!(\u0001A\u0003%!\u0011\u0013\u0005\n\u0005?\u0003!\u0019!C\u0005\u0003\u000bD\u0001B!)\u0001A\u0003%\u0011q\u0019\u0005\n\u0005G\u0003\u0001\u0019!C\u0005\u0005KC\u0011B!.\u0001\u0001\u0004%IAa.\t\u0011\tm\u0006\u0001)Q\u0005\u0005OCqA!0\u0001\t\u0003\u0011y\fC\u0004\u0003B\u0002!\tAa1\t\u000f\t]\u0007\u0001\"\u0001\u0003Z\"9!1\u001c\u0001\u0005\u0002\te\u0007b\u0002Bo\u0001\u0011%!\u0011\u001c\u0005\b\u0005?\u0004A\u0011\u0001Bq\u0011\u001d\u0011i\u0010\u0001C\u0005\u0005\u007fDqa!\u0002\u0001\t\u0013\u00199\u0001C\u0004\u0004\f\u0001!\ta!\u0004\t\u000f\rU\u0001\u0001\"\u0003\u0004\u0018!91Q\u0004\u0001\u0005\n\r}\u0001\"CB\u0019\u0001\u0011\u0005\u00111FB\u001a\u0011-\u0019)\bAI\u0001\n\u0003\tYca\u001e\t\u0017\r5\u0005!%A\u0005\u0002\u0005-2q\u0012\u0005\n\u0007'\u0003A\u0011AA\u0016\u0007+C\u0011b!/\u0001\t\u0003\tYca/\t\u0017\r]\u0007!%A\u0005\u0002\u0005-2\u0011\u001c\u0005\b\u0007;\u0004A\u0011ABp\u0011\u001d\u0019y\u000f\u0001C\u0005\u0007cD\u0011\u0002\"\u0002\u0001\t\u0003\tY\u0003b\u0002\t\u000f\u0011=\u0001\u0001\"\u0003\u0005\u0012!9A1\u0003\u0001\u0005\u0002\u0011U\u0001\"\u0003C\u0015\u0001E\u0005I\u0011AB<\u0011%!Y\u0003AI\u0001\n\u0003\u00199\bC\u0005\u0005.\u0001\t\n\u0011\"\u0001\u00050!9A1\u0007\u0001\u0005\n\u0011U\u0002b\u0002C\u001d\u0001\u0011%A1\b\u0005\b\t\u001b\u0002A\u0011\u0002C(\u0011\u001d!)\u0006\u0001C\u0001\u00053Dq\u0001b\u0016\u0001\t\u0013!If\u0002\u0006\u0005\\\u0005-\u0002\u0012AA\u001a\t;2!\"!\u000b\u0002,!\u0005\u00111\u0007C0\u0011\u001d\tI\t\u0019C\u0001\tCB\u0011\u0002b\u0019a\u0005\u0004%\tAa\u001a\t\u0011\u0011\u0015\u0004\r)A\u0005\u0005SB\u0011\u0002b\u001aa\u0005\u0004%\tAa\u001a\t\u0011\u0011%\u0004\r)A\u0005\u0005SB\u0011\u0002b\u001ba\u0005\u0004%\t\u0001\"\u001c\t\u0011\u0011m\u0004\r)A\u0005\t_B\u0011\u0002\" a\u0005\u0004%\t\u0001\"\u001c\t\u0011\u0011}\u0004\r)A\u0005\t_B\u0011\u0002\"!a\u0005\u0004%\t\u0001b!\t\u0011\u0011=\u0005\r)A\u0005\t\u000bC\u0011\u0002\"%a\u0005\u0004%\t\u0001b!\t\u0011\u0011M\u0005\r)A\u0005\t\u000bC\u0011\u0002\"&a\u0005\u0004%\t\u0001b!\t\u0011\u0011]\u0005\r)A\u0005\t\u000bC\u0011\u0002\"'a\u0005\u0004%\t\u0001b!\t\u0011\u0011m\u0005\r)A\u0005\t\u000bC\u0011\u0002\"(a\u0005\u0004%\t\u0001b!\t\u0011\u0011}\u0005\r)A\u0005\t\u000bC\u0011\u0002\")a\u0005\u0004%\t\u0001b!\t\u0011\u0011\r\u0006\r)A\u0005\t\u000bC\u0011\u0002\"*a\u0005\u0004%\t\u0001b!\t\u0011\u0011\u001d\u0006\r)A\u0005\t\u000bC\u0011\u0002\"+a\u0005\u0004%\t\u0001b!\t\u0011\u0011-\u0006\r)A\u0005\t\u000bC\u0011\u0002\",a\u0005\u0004%\t\u0001b!\t\u0011\u0011=\u0006\r)A\u0005\t\u000bCq\u0001\"-a\t\u0013!\u0019\fC\u0005\u00058\u0002$\t!a\u000b\u0005:\"9A1\u001a1\u0005\n\u00115\u0007b\u0002CiA\u0012%A1\u001b\u0005\n\t/\u0004G\u0011AA\u0016\t3D\u0011\u0002b7a\t\u0003\tY\u0003\"7\t\u0013\u0011u\u0007\r\"\u0001\u0002,\u0011}\u0007b\u0003CwAF\u0005I\u0011AA\u0016\u0007\u001fCq\u0001b<a\t\u0003!\t\u0010C\u0004\u0005|\u0002$\t\u0001\"@\t\u000f\u00155\u0001\r\"\u0003\u0006\u0010!9Qq\u00031\u0005\n\u0015e\u0001bBC\u0012A\u0012%QQ\u0005\u0005\b\u000bk\u0001G\u0011BC\u001c\u0011\u001d)y\u0004\u0019C\u0001\u000b\u0003B\u0011\"b\u0012a\t\u0003\t\u0019$\"\u0013\t\u000f\u0015M\u0003\r\"\u0005\u0006V!9Qq\f1\u0005\n\u0015\u0005\u0004bBC5A\u0012\u0005Q1\u000e\u0005\b\u000bg\u0002G\u0011AC;\u0011\u001d)\t\t\u0019C\u0001\u000b\u0007Cq!b\"a\t\u0003)I\tC\u0004\u0006\u0012\u0002$\t!b%\t\u000f\u0015\r\u0006\r\"\u0001\u0006&\n11\t\\5f]RTA!!\f\u00020\u0005!\u00110\u0019:o\u0015\u0011\t\t$a\r\u0002\r\u0011,\u0007\u000f\\8z\u0015\u0011\t)$a\u000e\u0002\u000bM\u0004\u0018M]6\u000b\t\u0005e\u00121H\u0001\u0007CB\f7\r[3\u000b\u0005\u0005u\u0012aA8sON)\u0001!!\u0011\u0002NA!\u00111IA%\u001b\t\t)E\u0003\u0002\u0002H\u0005)1oY1mC&!\u00111JA#\u0005\u0019\te.\u001f*fMB!\u0011qJA+\u001b\t\t\tF\u0003\u0003\u0002T\u0005M\u0012\u0001C5oi\u0016\u0014h.\u00197\n\t\u0005]\u0013\u0011\u000b\u0002\b\u0019><w-\u001b8h\u0003\u0011\t'oZ:\u0004\u0001U\u0011\u0011q\f\t\u0005\u0003C\n\u0019'\u0004\u0002\u0002,%!\u0011QMA\u0016\u0005=\u0019E.[3oi\u0006\u0013x-^7f]R\u001c\u0018!B1sON\u0004\u0013!C:qCJ\\7i\u001c8g+\t\ti\u0007\u0005\u0003\u0002p\u0005ETBAA\u001a\u0013\u0011\t\u0019(a\r\u0003\u0013M\u0003\u0018M]6D_:4\u0017AC:qCJ\\7i\u001c8gA\u00051!\u000f]2F]Z,\"!a\u001f\u0011\t\u0005u\u00141Q\u0007\u0003\u0003\u007fRA!!!\u00024\u0005\u0019!\u000f]2\n\t\u0005\u0015\u0015q\u0010\u0002\u0007%B\u001cWI\u001c<\u0002\u000fI\u00048-\u00128wA\u00051A(\u001b8jiz\"\u0002\"!$\u0002\u0010\u0006E\u00151\u0013\t\u0004\u0003C\u0002\u0001bBA-\u000f\u0001\u0007\u0011q\f\u0005\b\u0003S:\u0001\u0019AA7\u0011\u001d\t9h\u0002a\u0001\u0003w\n!\"_1s]\u000ec\u0017.\u001a8u+\t\tI\n\u0005\u0003\u0002\u001c\u0006-VBAAO\u0015\u0011\ty*!)\u0002\u0007\u0005\u0004\u0018N\u0003\u0003\u0002$\u0006\u0015\u0016AB2mS\u0016tGO\u0003\u0003\u0002.\u0005\u001d&\u0002BAU\u0003o\ta\u0001[1e_>\u0004\u0018\u0002BAW\u0003;\u0013!\"W1s]\u000ec\u0017.\u001a8u\u0003-I\u0018M\u001d8DY&,g\u000e\u001e\u0011\u0002\u0015!\fGm\\8q\u0007>tg-\u0006\u0002\u00026B!\u0011qWA_\u001b\t\tIL\u0003\u0003\u0002<\u0006\u0015\u0016\u0001B2p]\u001aLA!a0\u0002:\n\t\u0012,\u0019:o\u0007>tg-[4ve\u0006$\u0018n\u001c8\u0002\u0017!\fGm\\8q\u0007>tg\rI\u0001\u000eSN\u001cE.^:uKJlu\u000eZ3\u0016\u0005\u0005\u001d\u0007\u0003BA\"\u0003\u0013LA!a3\u0002F\t9!i\\8mK\u0006t\u0017AD5t\u00072,8\u000f^3s\u001b>$W\rI\u0001\u001bSN\u001cE.[3oiVsW.\u00198bO\u0016$\u0017)T#oC\ndW\rZ\u0001\u001cSN\u001cE.[3oiVsW.\u00198bO\u0016$\u0017)T#oC\ndW\r\u001a\u0011\u0002/M$\u0018\r^\"bG\",\u0007K]3m_\u0006$WI\\1cY\u0016$\u0017\u0001G:uCR\u001c\u0015m\u00195f!J,Gn\\1e\u000b:\f'\r\\3eA\u000593\u000f^1u\u0007\u0006\u001c\u0007.\u001a)sK2|\u0017\r\u001a#je\u0016\u001cGo\u001c:z\u0007>,h\u000e\u001e+ie\u0016\u001c\bn\u001c7e+\t\tY\u000e\u0005\u0003\u0002D\u0005u\u0017\u0002BAp\u0003\u000b\u00121!\u00138u\u0003!\u001aH/\u0019;DC\u000eDW\r\u0015:fY>\fG\rR5sK\u000e$xN]=D_VtG\u000f\u00165sKNDw\u000e\u001c3!\u0003%\t\u0007\u000f]'bgR,'/\u0006\u0002\u0002hB!\u0011\u0011MAu\u0013\u0011\tY/a\u000b\u0003#\u0005\u0003\b\u000f\\5dCRLwN\\'bgR,'/A\u0007baBl\u0015m\u001d;fe~#S-\u001d\u000b\u0005\u0003c\f9\u0010\u0005\u0003\u0002D\u0005M\u0018\u0002BA{\u0003\u000b\u0012A!\u00168ji\"I\u0011\u0011`\u000b\u0002\u0002\u0003\u0007\u0011q]\u0001\u0004q\u0012\n\u0014AC1qa6\u000b7\u000f^3sA\u0005q1\u000f^1hS:<G)\u001b:QCRDWC\u0001B\u0001!\u0011\u0011\u0019A!\u0003\u000e\u0005\t\u0015!\u0002\u0002B\u0004\u0003O\u000b!AZ:\n\t\t-!Q\u0001\u0002\u0005!\u0006$\b.\u0001\nti\u0006<\u0017N\\4ESJ\u0004\u0016\r\u001e5`I\u0015\fH\u0003BAy\u0005#A\u0011\"!?\u0019\u0003\u0003\u0005\rA!\u0001\u0002\u001fM$\u0018mZ5oO\u0012K'\u000fU1uQ\u0002\na#Y7NK6|'/_(wKJDW-\u00193GC\u000e$xN]\u000b\u0003\u00053\u0001B!a\u0011\u0003\u001c%!!QDA#\u0005\u0019!u.\u001e2mK\u00069\u0012-\\'f[>\u0014\u0018p\u0014<fe\",\u0017\r\u001a$bGR|'\u000fI\u0001\tC6lU-\\8ss\u0006I\u0011-\\'f[>\u0014\u0018\u0010I\u0001\u001cIJLg/\u001a:NS:LW.^7NK6|'/_(wKJDW-\u00193\u0016\u0005\t%\u0002\u0003BA\"\u0005WIAA!\f\u0002F\t!Aj\u001c8h\u0003q!'/\u001b<fe6Kg.[7v[6+Wn\u001c:z\u001fZ,'\u000f[3bI\u0002\n\u0001#Y7NK6|'/_(wKJDW-\u00193\u0002#\u0005lW*Z7pef|e/\u001a:iK\u0006$\u0007%A\u0004b[\u000e{'/Z:\u0002\u0011\u0005l7i\u001c:fg\u0002\na\"\u001a=fGV$xN]'f[>\u0014\u00180A\bfq\u0016\u001cW\u000f^8s\u001b\u0016lwN]=!\u0003U)\u00070Z2vi>\u0014xJ\u001a4IK\u0006\u0004X*Z7pef\fa#\u001a=fGV$xN](gM\"+\u0017\r]'f[>\u0014\u0018\u0010I\u0001\u001cKb,7-\u001e;pe6+Wn\u001c:z\u001fZ,'/Z1e\r\u0006\u001cGo\u001c:\u00029\u0015DXmY;u_JlU-\\8ss>3XM]3bI\u001a\u000b7\r^8sA\u0005\tR.\u001b8NK6|'/_(wKJDW-\u00193\u0002%5Lg.T3n_JLxJ^3sQ\u0016\fG\rI\u0001\u0017Kb,7-\u001e;pe6+Wn\u001c:z\u001fZ,'\u000f[3bI\u00069R\r_3dkR|'/T3n_JLxJ^3sQ\u0016\fG\rI\u0001\tSN\u0004\u0016\u0010\u001e5p]\u0006I\u0011n\u001d)zi\"|g\u000eI\u0001\u0014af\u001c\b/\u0019:l/>\u00148.\u001a:NK6|'/_\u0001\u0015af\u001c\b/\u0019:l/>\u00148.\u001a:NK6|'/\u001f\u0011\u0002\u0019\u0011L7\u000f^\"bG\",Wj\u001a:\u0016\u0005\te\u0003\u0003BA1\u00057JAA!\u0018\u0002,\ti2\t\\5f]R$\u0015n\u001d;sS\n,H/\u001a3DC\u000eDW-T1oC\u001e,'/A\u0007eSN$8)Y2iK6;'\u000fI\u0001\u0014G\u0006\u001c\u0007.\u001a3SKN|WO]2fg\u000e{gNZ\u0001\u0015G\u0006\u001c\u0007.\u001a3SKN|WO]2fg\u000e{gN\u001a\u0011\u0002\r-,\u0017\u0010^1c+\t\u0011I\u0007\u0005\u0003\u0003l\ted\u0002\u0002B7\u0005k\u0002BAa\u001c\u0002F5\u0011!\u0011\u000f\u0006\u0005\u0005g\nY&\u0001\u0004=e>|GOP\u0005\u0005\u0005o\n)%\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0005w\u0012iH\u0001\u0004TiJLgn\u001a\u0006\u0005\u0005o\n)%A\u0004lKf$\u0018M\u0019\u0011\u0002!\u0005l7*Z=uC\n4\u0015\u000e\\3OC6,WC\u0001BC!\u0019\t\u0019Ea\"\u0003j%!!\u0011RA#\u0005\u0019y\u0005\u000f^5p]\u0006\t\u0012-\\&fsR\f'MR5mK:\u000bW.\u001a\u0011\u0002\u001f1\fWO\\2iKJ\u0014\u0015mY6f]\u0012,\"A!%\u0011\t\tM%\u0011T\u0007\u0003\u0005+SAAa&\u00024\u0005AA.Y;oG\",'/\u0003\u0003\u0003\u001c\nU%a\u0004'bk:\u001c\u0007.\u001a:CC\u000e\\WM\u001c3\u0002!1\fWO\\2iKJ\u0014\u0015mY6f]\u0012\u0004\u0013!\u00044je\u0016\fe\u000e\u001a$pe\u001e,G/\u0001\bgSJ,\u0017I\u001c3G_J<W\r\u001e\u0011\u0002\u000b\u0005\u0004\b/\u00133\u0016\u0005\t\u001d\u0006\u0003\u0002BU\u0005ck!Aa+\u000b\t\t5&qV\u0001\be\u0016\u001cwN\u001d3t\u0015\u0011\ty*!*\n\t\tM&1\u0016\u0002\u000e\u0003B\u0004H.[2bi&|g.\u00133\u0002\u0013\u0005\u0004\b/\u00133`I\u0015\fH\u0003BAy\u0005sC\u0011\"!?@\u0003\u0003\u0005\rAa*\u0002\r\u0005\u0004\b/\u00133!\u0003A9W\r^!qa2L7-\u0019;j_:LE\r\u0006\u0002\u0003(\u0006\u0019\"/\u001a9peRd\u0015-\u001e8dQ\u0016\u00148\u000b^1uKR!\u0011\u0011\u001fBc\u0011\u001d\u00119M\u0011a\u0001\u0005\u0013\fQa\u001d;bi\u0016\u0004BAa3\u0003R:!!1\u0013Bg\u0013\u0011\u0011yM!&\u0002\u001dM\u0003\u0018M]6BaBD\u0015M\u001c3mK&!!1\u001bBk\u0005\u0015\u0019F/\u0019;f\u0015\u0011\u0011yM!&\u0002\tM$x\u000e\u001d\u000b\u0003\u0003c\f\u0011c];c[&$\u0018\t\u001d9mS\u000e\fG/[8o\u0003E\u0019G.Z1okB\u001cF/Y4j]\u001e$\u0015N]\u0001#GJ,\u0017\r^3BaBd\u0017nY1uS>t7+\u001e2nSN\u001c\u0018n\u001c8D_:$X\r\u001f;\u0015\r\t\r(\u0011\u001eBz!\u0011\u0011IK!:\n\t\t\u001d(1\u0016\u0002\u001d\u0003B\u0004H.[2bi&|gnU;c[&\u001c8/[8o\u0007>tG/\u001a=u\u0011\u001d\u0011YO\u0012a\u0001\u0005[\faA\\3x\u0003B\u0004\b\u0003BAN\u0005_LAA!=\u0002\u001e\n)\u0012,\u0019:o\u00072LWM\u001c;BaBd\u0017nY1uS>t\u0007b\u0002B{\r\u0002\u0007!q_\u0001\u0011G>tG/Y5oKJ\u001cuN\u001c;fqR\u0004BA!+\u0003z&!!1 BV\u0005Y\u0019uN\u001c;bS:,'\u000fT1v]\u000eD7i\u001c8uKb$\u0018AE:fiV\u00048+Z2ve&$\u0018\u0010V8lK:$B!!=\u0004\u0002!911A$A\u0002\t]\u0018aC1n\u0007>tG/Y5oKJ\fAb]3u)>\\WM\\\"p]\u001a$B!!=\u0004\n!911\u0001%A\u0002\t]\u0018\u0001F4fi\u0006\u0003\b\u000f\\5dCRLwN\u001c*fa>\u0014H\u000f\u0006\u0002\u0004\u0010A!!\u0011VB\t\u0013\u0011\u0019\u0019Ba+\u0003#\u0005\u0003\b\u000f\\5dCRLwN\u001c*fa>\u0014H/\u0001\bhKR\u001cE.[3oiR{7.\u001a8\u0015\t\t%4\u0011\u0004\u0005\b\u00077Q\u0005\u0019AB\b\u0003\u0019\u0011X\r]8si\u00061b/\u001a:jMf\u001cE.^:uKJ\u0014Vm]8ve\u000e,7\u000f\u0006\u0003\u0002r\u000e\u0005\u0002bBB\u0012\u0017\u0002\u00071QE\u0001\u000f]\u0016<\u0018\t\u001d9SKN\u0004xN\\:f!\u0011\u00199c!\f\u000e\u0005\r%\"\u0002BB\u0016\u0005_\u000bq\u0002\u001d:pi>\u001cw\u000e\u001c:fG>\u0014Hm]\u0005\u0005\u0007_\u0019ICA\rHKRtUm^!qa2L7-\u0019;j_:\u0014Vm\u001d9p]N,\u0017\u0001E2paf4\u0015\u000e\\3U_J+Wn\u001c;f)9\u0011\ta!\u000e\u0004:\ru2\u0011JB7\u0007cBqaa\u000eM\u0001\u0004\u0011\t!A\u0004eKN$H)\u001b:\t\u000f\rmB\n1\u0001\u0003\u0002\u000591O]2QCRD\u0007bBB \u0019\u0002\u00071\u0011I\u0001\fe\u0016\u0004H.[2bi&|g\u000e\u0005\u0004\u0002D\t\u001d51\t\t\u0005\u0003\u0007\u001a)%\u0003\u0003\u0004H\u0005\u0015#!B*i_J$\bbBB&\u0019\u0002\u00071QJ\u0001\rgflG.\u001b8l\u0007\u0006\u001c\u0007.\u001a\t\t\u0007\u001f\u001aIf!\u0018\u0003\u00025\u00111\u0011\u000b\u0006\u0005\u0007'\u001a)&A\u0004nkR\f'\r\\3\u000b\t\r]\u0013QI\u0001\u000bG>dG.Z2uS>t\u0017\u0002BB.\u0007#\u00121!T1q!\u0011\u0019yf!\u001b\u000e\u0005\r\u0005$\u0002BB2\u0007K\n1A\\3u\u0015\t\u00199'\u0001\u0003kCZ\f\u0017\u0002BB6\u0007C\u00121!\u0016*J\u0011%\u0019y\u0007\u0014I\u0001\u0002\u0004\t9-A\u0003g_J\u001cW\rC\u0005\u0004t1\u0003\n\u00111\u0001\u0003\u0006\u0006AA-Z:u\u001d\u0006lW-\u0001\u000ed_BLh)\u001b7f)>\u0014V-\\8uK\u0012\"WMZ1vYR$S'\u0006\u0002\u0004z)\"\u0011qYB>W\t\u0019i\b\u0005\u0003\u0004\u0000\r%UBABA\u0015\u0011\u0019\u0019i!\"\u0002\u0013Ut7\r[3dW\u0016$'\u0002BBD\u0003\u000b\n!\"\u00198o_R\fG/[8o\u0013\u0011\u0019Yi!!\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u000ed_BLh)\u001b7f)>\u0014V-\\8uK\u0012\"WMZ1vYR$c'\u0006\u0002\u0004\u0012*\"!QQB>\u0003a!\u0017N]3di>\u0014\u0018.Z:U_\n+\u0007K]3m_\u0006$W\r\u001a\u000b\u0005\u0007/\u001b\u0019\u000b\u0005\u0005\u0004P\re5QLBO\u0013\u0011\u0019Yj!\u0015\u0003\u000f!\u000b7\u000f['baB11qJBP\u0005SJAa!)\u0004R\t9\u0001*Y:i'\u0016$\bbBBS\u001f\u0002\u00071qU\u0001\u0006M&dWm\u001d\t\u0007\u0007S\u001b\u0019L!\u001b\u000f\t\r-6q\u0016\b\u0005\u0005_\u001ai+\u0003\u0002\u0002H%!1\u0011WA#\u0003\u001d\u0001\u0018mY6bO\u0016LAa!.\u00048\n\u00191+Z9\u000b\t\rE\u0016QI\u0001\u0016O\u0016$\bK]3m_\u0006$W\rZ*uCR\u001c\u0015m\u00195f)\u0019\u0019il!2\u0004HBA1qJBM\u0007;\u001ay\f\u0005\u0003\u0003\u0004\r\u0005\u0017\u0002BBb\u0005\u000b\u0011!BR5mKN#\u0018\r^;t\u0011\u001d\u0019)\u000b\u0015a\u0001\u0007OC\u0011b!3Q!\u0003\u0005\raa3\u0002\u0011\u0019\u001cHj\\8lkB\u0004\u0002\"a\u0011\u0004N\u000eu3\u0011[\u0005\u0005\u0007\u001f\f)EA\u0005Gk:\u001cG/[8ocA!!1ABj\u0013\u0011\u0019)N!\u0002\u0003\u0015\u0019KG.Z*zgR,W.A\u0010hKR\u0004&/\u001a7pC\u0012,Gm\u0015;bi\u000e\u000b7\r[3%I\u00164\u0017-\u001e7uII*\"aa7+\t\r-71P\u0001\u0016aJ,\u0007/\u0019:f\u0019>\u001c\u0017\r\u001c*fg>,(oY3t)\u0019\u0019\to!;\u0004lBA1qJBM\u0005S\u001a\u0019\u000f\u0005\u0003\u0003*\u000e\u0015\u0018\u0002BBt\u0005W\u0013Q\u0002T8dC2\u0014Vm]8ve\u000e,\u0007bBB\u001c%\u0002\u0007!\u0011\u0001\u0005\b\u0007[\u0014\u0006\u0019ABT\u0003=\u0001\u0018p\u00159be.\f%o\u00195jm\u0016\u001c\u0018!E2sK\u0006$XmQ8oM\u0006\u00138\r[5wKR!11_B\u0000!\u0011\u0019)pa?\u000e\u0005\r](\u0002BB}\u0007K\n!![8\n\t\ru8q\u001f\u0002\u0005\r&dW\rC\u0004\u0005\u0002M\u0003\r\u0001b\u0001\u0002\u001f\r|gNZ:U_>3XM\u001d:jI\u0016\u0004\u0002ba\u0014\u0004Z\t%$\u0011N\u0001\u000fg\u0016$X\u000f\u001d'bk:\u001c\u0007.\u00128w)\u0019!I\u0001b\u0003\u0005\u000eAA1qJBM\u0005S\u0012I\u0007C\u0004\u0002~R\u0003\rA!\u0001\t\u000f\r5H\u000b1\u0001\u0004(\u0006a2M]3bi\u0016\u001cuN\u001c;bS:,'\u000fT1v]\u000eD7i\u001c8uKb$HC\u0001B|\u0003IiwN\\5u_J\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8\u0015\u0011\u0011]AQ\u0004C\u0011\tK\u0001B!!\u0019\u0005\u001a%!A1DA\u0016\u00055I\u0016M\u001d8BaB\u0014V\r]8si\"IAq\u0004,\u0011\u0002\u0003\u0007\u0011qY\u0001\u0010e\u0016$XO\u001d8P]J+hN\\5oO\"IA1\u0005,\u0011\u0002\u0003\u0007\u0011qY\u0001\u0015Y><\u0017\t\u001d9mS\u000e\fG/[8o%\u0016\u0004xN\u001d;\t\u0013\u0011\u001db\u000b%AA\u0002\t%\u0012\u0001C5oi\u0016\u0014h/\u00197\u000295|g.\u001b;pe\u0006\u0003\b\u000f\\5dCRLwN\u001c\u0013eK\u001a\fW\u000f\u001c;%c\u0005aRn\u001c8ji>\u0014\u0018\t\u001d9mS\u000e\fG/[8oI\u0011,g-Y;mi\u0012\u0012\u0014\u0001H7p]&$xN]!qa2L7-\u0019;j_:$C-\u001a4bk2$HeM\u000b\u0003\tcQCA!\u000b\u0004|\u0005i2\u000f^1si\u0006\u0003\b\u000f\\5dCRLwN\\'bgR,'oU3sm&\u001cW\r\u0006\u0003\u0002h\u0012]\u0002bBB\u000e5\u0002\u00071qB\u0001\u0014M>\u0014X.\u0019;SKB|'\u000f\u001e#fi\u0006LGn\u001d\u000b\u0007\u0005S\"i\u0004b\u0010\t\u000f\rm1\f1\u0001\u0004\u0010!9A\u0011I.A\u0002\u0011\r\u0013a\u00043sSZ,'\u000fT8hg2Kgn[:\u0011\u0011\u0011\u0015C1\nB5\u0005Sj!\u0001b\u0012\u000b\t\u0011%3QK\u0001\nS6lW\u000f^1cY\u0016LAaa\u0017\u0005H\u0005\tr-\u001a;Ee&4XM\u001d'pONd\u0015N\\6\u0015\t\u0011\rC\u0011\u000b\u0005\b\t'b\u0006\u0019AB\b\u0003%\t\u0007\u000f\u001d*fa>\u0014H/A\u0002sk:\f1CZ5oIBK8\u000b]1sW\u0006\u00138\r[5wKN$\"aa*\u0002\r\rc\u0017.\u001a8u!\r\t\t\u0007Y\n\u0006A\u0006\u0005\u0013Q\n\u000b\u0003\t;\nA\"\u0011)Q?*\u000b%k\u0018(B\u001b\u0016\u000bQ\"\u0011)Q?*\u000b%k\u0018(B\u001b\u0016\u0003\u0013!D*Q\u0003J[ul\u0015+B\u000f&su)\u0001\bT!\u0006\u00136jX*U\u0003\u001eKej\u0012\u0011\u0002-M#\u0016iR%O\u000f~#\u0015JU0Q\u000bJk\u0015jU*J\u001f:+\"\u0001b\u001c\u0011\t\u0011EDqO\u0007\u0003\tgRA\u0001\"\u001e\u0003\u0006\u0005Q\u0001/\u001a:nSN\u001c\u0018n\u001c8\n\t\u0011eD1\u000f\u0002\r\rN\u0004VM]7jgNLwN\\\u0001\u0018'R\u000bu)\u0013(H?\u0012K%k\u0018)F%6K5kU%P\u001d\u0002\n1#\u0011)Q?\u001aKE*R0Q\u000bJk\u0015jU*J\u001f:\u000bA#\u0011)Q?\u001aKE*R0Q\u000bJk\u0015jU*J\u001f:\u0003\u0013AE#O-~#\u0015j\u0015+`\u00072\u000b5k\u0015)B)\"+\"\u0001\"\"\u0011\t\u0011\u001dEQR\u0007\u0003\t\u0013SA\u0001b#\u0004f\u0005!A.\u00198h\u0013\u0011\u0011Y\b\"#\u0002'\u0015sek\u0018#J'R{6\tT!T'B\u000bE\u000b\u0013\u0011\u0002%1{5)\u0011'J5\u0016#ulQ(O\r~#\u0015JU\u0001\u0014\u0019>\u001b\u0015\tT%[\u000b\u0012{6i\u0014(G?\u0012K%\u000bI\u0001\u001a\u0019>\u001b\u0015\tT%[\u000b\u0012{\u0006*\u0011#P\u001fB{6i\u0014(G?\u0012K%+\u0001\u000eM\u001f\u000e\u000bE*\u0013.F\t~C\u0015\tR(P!~\u001buJ\u0014$`\t&\u0013\u0006%\u0001\fM\u001f\u000e\u000bE*\u0013.F\t~\u001buJ\u0014$`\u0003J\u001b\u0005*\u0013,F\u0003]aujQ!M\u0013j+EiX\"P\u001d\u001a{\u0016IU\"I\u0013Z+\u0005%A\bT!\u0006\u00136jX\"P\u001d\u001a{f)\u0013'F\u0003A\u0019\u0006+\u0011*L?\u000e{eJR0G\u00132+\u0005%\u0001\u000bE\u0013N#vlQ!D\u0011\u0016{6i\u0014(G?\u001aKE*R\u0001\u0016\t&\u001bFkX\"B\u0007\"+ulQ(O\r~3\u0015\nT#!\u0003QaujQ!M\u0013j+Ei\u0018)Z)\"{ej\u0018#J%\u0006)BjT\"B\u0019&SV\tR0Q3RCuJT0E\u0013J\u0003\u0013!\u0005'P\u0007\u0006c\u0015JW#E?2K%i\u0018#J%\u0006\u0011BjT\"B\u0019&SV\tR0M\u0013\n{F)\u0013*!\u00035\u0019\u0006+\u0011*L?R+5\u000bV%O\u000f\u0006q1\u000bU!S\u0017~#Vi\u0015+J\u001d\u001e\u0003\u0013\u0001E4fi\u0006\u0003\bo\u0015;bO&tw\rR5s)\u0011\u0011I\u0007\".\t\u000f\t\rF\u00101\u0001\u0003(\u00069\u0002o\u001c9vY\u0006$X\rS1e_>\u00048\t\\1tgB\fG\u000f\u001b\u000b\u0007\u0003c$Y\fb2\t\u000f\u0005mV\u00101\u0001\u0005>B!Aq\u0018Cb\u001b\t!\tM\u0003\u0003\u0002<\u0006\u001d\u0016\u0002\u0002Cc\t\u0003\u0014QbQ8oM&<WO]1uS>t\u0007b\u0002Ce{\u0002\u0007A\u0011B\u0001\u0004K:4\u0018aE4fif\u000b'O\\!qa\u000ec\u0017m]:qCRDG\u0003BBT\t\u001fDq!a/\u007f\u0001\u0004!i,A\thKRl%+\u00119q\u00072\f7o\u001d9bi\"$Baa*\u0005V\"9\u00111X@A\u0002\u0011u\u0016AI4fi\u0012+g-Y;mif\u000b'O\\!qa2L7-\u0019;j_:\u001cE.Y:ta\u0006$\b.\u0006\u0002\u0004(\u0006\u0001s-\u001a;EK\u001a\fW\u000f\u001c;N%\u0006\u0003\b\u000f\\5dCRLwN\\\"mCN\u001c\b/\u0019;i\u0003E\u0001x\u000e];mCR,7\t\\1tgB\fG\u000f\u001b\u000b\r\u0003c$\t\u000fb9\u0005f\u0012\u001dH\u0011\u001e\u0005\t\u00033\n)\u00011\u0001\u0002`!A\u00111XA\u0003\u0001\u0004!i\f\u0003\u0005\u0002j\u0005\u0015\u0001\u0019AA7\u0011!!I-!\u0002A\u0002\u0011%\u0001B\u0003Cv\u0003\u000b\u0001\n\u00111\u0001\u0003\u0006\u0006qQ\r\u001f;sC\u000ec\u0017m]:QCRD\u0017a\u00079paVd\u0017\r^3DY\u0006\u001c8\u000f]1uQ\u0012\"WMZ1vYR$S'\u0001\thKR,6/\u001a:DY\u0006\u001c8\u000f]1uQR!A1\u001fC}!\u0019\t\u0019\u0005\">\u0004^%!Aq_A#\u0005\u0015\t%O]1z\u0011!\tY,!\u0003A\u0002\u00055\u0014\u0001F4fiV\u001bXM]\"mCN\u001c\b/\u0019;i+Jd7\u000f\u0006\u0004\u0005\u0000\u0016\u001dQ\u0011\u0002\t\u0007\u0003\u0007\")0\"\u0001\u0011\t\r}S1A\u0005\u0005\u000b\u000b\u0019\tGA\u0002V%2C\u0001\"a/\u0002\f\u0001\u0007\u0011Q\u000e\u0005\t\u000b\u0017\tY\u00011\u0001\u0002H\u0006qQo]3DYV\u001cH/\u001a:QCRD\u0017!D4fi6\u000b\u0017N\u001c&beV\u0013\u0018\u000e\u0006\u0003\u0006\u0012\u0015M\u0001CBA\"\u0005\u000f\u001bi\u0006\u0003\u0005\u0006\u0016\u00055\u0001\u0019\u0001BC\u0003\u001di\u0017-\u001b8KCJ\f1cZ3u'\u0016\u001cwN\u001c3befT\u0015M]+sSN$B!b\u0007\u0006\u001eA11\u0011VBZ\u0007;B\u0001\"b\b\u0002\u0010\u0001\u0007Q\u0011E\u0001\u000eg\u0016\u001cwN\u001c3befT\u0015M]:\u0011\r\u0005\r#qQBT\u0003I\tG\r\u001a$jY\u0016$vn\u00117bgN\u0004\u0018\r\u001e5\u0015\u0019\u0005EXqEC\u0015\u000bW)y#b\r\t\u0011\u0005m\u0016\u0011\u0003a\u0001\u0003[B\u0001\"!-\u0002\u0012\u0001\u0007AQ\u0018\u0005\t\u000b[\t\t\u00021\u0001\u0004^\u0005\u0019QO]5\t\u0011\u0015E\u0012\u0011\u0003a\u0001\u0005S\n\u0001BZ5mK:\u000bW.\u001a\u0005\t\t\u0013\f\t\u00021\u0001\u0005\n\u0005\t\u0012\r\u001a3DY\u0006\u001c8\u000f]1uQ\u0016sGO]=\u0015\r\u0005EX\u0011HC\u001f\u0011!)Y$a\u0005A\u0002\t%\u0014\u0001\u00029bi\"D\u0001\u0002\"3\u0002\u0014\u0001\u0007A\u0011B\u0001\u000fO\u0016$8\t\\;ti\u0016\u0014\b+\u0019;i)\u0019\u0011I'b\u0011\u0006F!A\u00111XA\u000b\u0001\u0004\ti\u0007\u0003\u0005\u0006<\u0005U\u0001\u0019\u0001B5\u0003)\u0019w.\u001c9be\u0016,&/\u001b\u000b\u0007\u0003\u000f,Y%b\u0014\t\u0011\u00155\u0013q\u0003a\u0001\u0007;\naa\u001d:d+JL\u0007\u0002CC)\u0003/\u0001\ra!\u0018\u0002\r\u0011\u001cH/\u0016:j\u0003%\u0019w.\u001c9be\u001645\u000f\u0006\u0004\u0002H\u0016]S1\f\u0005\t\u000b3\nI\u00021\u0001\u0004R\u0006)1O]2Gg\"AQQLA\r\u0001\u0004\u0019\t.\u0001\u0004eKN$hi]\u0001\u0016O\u0016$\u0018+^1mS\u001aLW\r\u001a'pG\u0006d\u0007+\u0019;i)\u0019\u0011\t!b\u0019\u0006h!AQQMA\u000e\u0001\u0004\u0019i&\u0001\u0005m_\u000e\fG.\u0016*J\u0011!\t\t,a\u0007A\u0002\u0011u\u0016\u0001F5t+N,'o\u00117bgN\u0004\u0016\r\u001e5GSJ\u001cH\u000f\u0006\u0004\u0002H\u00165Tq\u000e\u0005\t\u0003w\u000bi\u00021\u0001\u0002n!AQ\u0011OA\u000f\u0001\u0004\t9-\u0001\u0005jg\u0012\u0013\u0018N^3s\u0003%\u0011W/\u001b7e!\u0006$\b\u000e\u0006\u0003\u0003j\u0015]\u0004\u0002CC=\u0003?\u0001\r!b\u001f\u0002\u0015\r|W\u000e]8oK:$8\u000f\u0005\u0004\u0002D\u0015u$\u0011N\u0005\u0005\u000b\u007f\n)E\u0001\u0006=e\u0016\u0004X-\u0019;fIz\nqb\u0019:fCR,\u0017\t\u001d9SKB|'\u000f\u001e\u000b\u0005\t/))\t\u0003\u0005\u0004\u001c\u0005\u0005\u0002\u0019AB\b\u0003]\u0019'/Z1uK2K'M]1ssB\u000bG\u000f\u001b)sK\u001aL\u0007\u0010\u0006\u0004\u0003j\u0015-Uq\u0012\u0005\t\u000b\u001b\u000b\u0019\u00031\u0001\u0003j\u00059A.\u001b2qCRD\u0007\u0002CA^\u0003G\u0001\r!!\u001c\u0002!\r|gN\u001a+p!J|\u0007/\u001a:uS\u0016\u001cH\u0003BCK\u000bC\u0003B!b&\u0006\u001e6\u0011Q\u0011\u0014\u0006\u0005\u000b7\u001b)'\u0001\u0003vi&d\u0017\u0002BCP\u000b3\u0013!\u0002\u0015:pa\u0016\u0014H/[3t\u0011!\tY,!\nA\u0002\u00055\u0014\u0001G<sSR,\u0007K]8qKJ$\u0018.Z:U_\u0006\u00138\r[5wKRA\u0011\u0011_CT\u000bW+y\u000b\u0003\u0005\u0006*\u0006\u001d\u0002\u0019ACK\u0003\u0015\u0001(o\u001c9t\u0011!)i+a\nA\u0002\t%\u0014\u0001\u00028b[\u0016D\u0001\"\"-\u0002(\u0001\u0007Q1W\u0001\u0004_V$\b\u0003BC[\u000bwk!!b.\u000b\t\u0015eV\u0011T\u0001\u0004u&\u0004\u0018\u0002BC_\u000bo\u0013qBW5q\u001fV$\b/\u001e;TiJ,\u0017-\u001c"
)
public class Client implements Logging {
   private final ClientArguments args;
   private final SparkConf sparkConf;
   private final RpcEnv rpcEnv;
   private final YarnClient org$apache$spark$deploy$yarn$Client$$yarnClient;
   private final YarnConfiguration hadoopConf;
   private final boolean org$apache$spark$deploy$yarn$Client$$isClusterMode;
   private final boolean isClientUnmanagedAMEnabled;
   private final boolean statCachePreloadEnabled;
   private final int statCachePreloadDirectoryCountThreshold;
   private ApplicationMaster appMaster;
   private Path org$apache$spark$deploy$yarn$Client$$stagingDirPath;
   private final double amMemoryOverheadFactor;
   private final int amMemory;
   private final long driverMinimumMemoryOverhead;
   private final int amMemoryOverhead;
   private final int amCores;
   private final long executorMemory;
   private final int executorOffHeapMemory;
   private final double executorMemoryOvereadFactor;
   private final long minMemoryOverhead;
   private final int executorMemoryOverhead;
   private final boolean isPython;
   private final int pysparkWorkerMemory;
   private final ClientDistributedCacheManager distCacheMgr;
   private final SparkConf org$apache$spark$deploy$yarn$Client$$cachedResourcesConf;
   private final String keytab;
   private final Option amKeytabFileName;
   private final LauncherBackend launcherBackend;
   private final boolean fireAndForget;
   private ApplicationId org$apache$spark$deploy$yarn$Client$$appId;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static void writePropertiesToArchive(final Properties props, final String name, final ZipOutputStream out) {
      Client$.MODULE$.writePropertiesToArchive(props, name, out);
   }

   public static Properties confToProperties(final SparkConf conf) {
      return Client$.MODULE$.confToProperties(conf);
   }

   public static String createLibraryPathPrefix(final String libpath, final SparkConf conf) {
      return Client$.MODULE$.createLibraryPathPrefix(libpath, conf);
   }

   public static YarnAppReport createAppReport(final ApplicationReport report) {
      return Client$.MODULE$.createAppReport(report);
   }

   public static String buildPath(final Seq components) {
      return Client$.MODULE$.buildPath(components);
   }

   public static boolean isUserClassPathFirst(final SparkConf conf, final boolean isDriver) {
      return Client$.MODULE$.isUserClassPathFirst(conf, isDriver);
   }

   public static String getClusterPath(final SparkConf conf, final String path) {
      return Client$.MODULE$.getClusterPath(conf, path);
   }

   public static URL[] getUserClasspathUrls(final SparkConf conf, final boolean useClusterPath) {
      return Client$.MODULE$.getUserClasspathUrls(conf, useClusterPath);
   }

   public static URI[] getUserClasspath(final SparkConf conf) {
      return Client$.MODULE$.getUserClasspath(conf);
   }

   public static String SPARK_TESTING() {
      return Client$.MODULE$.SPARK_TESTING();
   }

   public static String LOCALIZED_LIB_DIR() {
      return Client$.MODULE$.LOCALIZED_LIB_DIR();
   }

   public static String LOCALIZED_PYTHON_DIR() {
      return Client$.MODULE$.LOCALIZED_PYTHON_DIR();
   }

   public static String DIST_CACHE_CONF_FILE() {
      return Client$.MODULE$.DIST_CACHE_CONF_FILE();
   }

   public static String SPARK_CONF_FILE() {
      return Client$.MODULE$.SPARK_CONF_FILE();
   }

   public static String LOCALIZED_CONF_ARCHIVE() {
      return Client$.MODULE$.LOCALIZED_CONF_ARCHIVE();
   }

   public static String LOCALIZED_HADOOP_CONF_DIR() {
      return Client$.MODULE$.LOCALIZED_HADOOP_CONF_DIR();
   }

   public static String LOCALIZED_CONF_DIR() {
      return Client$.MODULE$.LOCALIZED_CONF_DIR();
   }

   public static String ENV_DIST_CLASSPATH() {
      return Client$.MODULE$.ENV_DIST_CLASSPATH();
   }

   public static FsPermission APP_FILE_PERMISSION() {
      return Client$.MODULE$.APP_FILE_PERMISSION();
   }

   public static FsPermission STAGING_DIR_PERMISSION() {
      return Client$.MODULE$.STAGING_DIR_PERMISSION();
   }

   public static String SPARK_STAGING() {
      return Client$.MODULE$.SPARK_STAGING();
   }

   public static String APP_JAR_NAME() {
      return Client$.MODULE$.APP_JAR_NAME();
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public ClientArguments args() {
      return this.args;
   }

   public SparkConf sparkConf() {
      return this.sparkConf;
   }

   public RpcEnv rpcEnv() {
      return this.rpcEnv;
   }

   public YarnClient org$apache$spark$deploy$yarn$Client$$yarnClient() {
      return this.org$apache$spark$deploy$yarn$Client$$yarnClient;
   }

   private YarnConfiguration hadoopConf() {
      return this.hadoopConf;
   }

   public boolean org$apache$spark$deploy$yarn$Client$$isClusterMode() {
      return this.org$apache$spark$deploy$yarn$Client$$isClusterMode;
   }

   private boolean isClientUnmanagedAMEnabled() {
      return this.isClientUnmanagedAMEnabled;
   }

   private boolean statCachePreloadEnabled() {
      return this.statCachePreloadEnabled;
   }

   private int statCachePreloadDirectoryCountThreshold() {
      return this.statCachePreloadDirectoryCountThreshold;
   }

   private ApplicationMaster appMaster() {
      return this.appMaster;
   }

   private void appMaster_$eq(final ApplicationMaster x$1) {
      this.appMaster = x$1;
   }

   public Path org$apache$spark$deploy$yarn$Client$$stagingDirPath() {
      return this.org$apache$spark$deploy$yarn$Client$$stagingDirPath;
   }

   private void stagingDirPath_$eq(final Path x$1) {
      this.org$apache$spark$deploy$yarn$Client$$stagingDirPath = x$1;
   }

   private double amMemoryOverheadFactor() {
      return this.amMemoryOverheadFactor;
   }

   private int amMemory() {
      return this.amMemory;
   }

   private long driverMinimumMemoryOverhead() {
      return this.driverMinimumMemoryOverhead;
   }

   private int amMemoryOverhead() {
      return this.amMemoryOverhead;
   }

   private int amCores() {
      return this.amCores;
   }

   private long executorMemory() {
      return this.executorMemory;
   }

   public int executorOffHeapMemory() {
      return this.executorOffHeapMemory;
   }

   private double executorMemoryOvereadFactor() {
      return this.executorMemoryOvereadFactor;
   }

   private long minMemoryOverhead() {
      return this.minMemoryOverhead;
   }

   private int executorMemoryOverhead() {
      return this.executorMemoryOverhead;
   }

   private boolean isPython() {
      return this.isPython;
   }

   private int pysparkWorkerMemory() {
      return this.pysparkWorkerMemory;
   }

   private ClientDistributedCacheManager distCacheMgr() {
      return this.distCacheMgr;
   }

   public SparkConf org$apache$spark$deploy$yarn$Client$$cachedResourcesConf() {
      return this.org$apache$spark$deploy$yarn$Client$$cachedResourcesConf;
   }

   private String keytab() {
      return this.keytab;
   }

   private Option amKeytabFileName() {
      return this.amKeytabFileName;
   }

   private LauncherBackend launcherBackend() {
      return this.launcherBackend;
   }

   private boolean fireAndForget() {
      return this.fireAndForget;
   }

   public ApplicationId org$apache$spark$deploy$yarn$Client$$appId() {
      return this.org$apache$spark$deploy$yarn$Client$$appId;
   }

   private void appId_$eq(final ApplicationId x$1) {
      this.org$apache$spark$deploy$yarn$Client$$appId = x$1;
   }

   public ApplicationId getApplicationId() {
      return this.org$apache$spark$deploy$yarn$Client$$appId();
   }

   public void reportLauncherState(final SparkAppHandle.State state) {
      this.launcherBackend().setState(state);
   }

   public void stop() {
      if (this.appMaster() != null) {
         this.appMaster().stopUnmanaged(this.org$apache$spark$deploy$yarn$Client$$stagingDirPath());
      }

      this.launcherBackend().close();
      this.org$apache$spark$deploy$yarn$Client$$yarnClient().stop();
   }

   public void submitApplication() {
      ResourceRequestHelper$.MODULE$.validateResources(this.sparkConf());

      try {
         this.launcherBackend().connect();
         this.org$apache$spark$deploy$yarn$Client$$yarnClient().init(this.hadoopConf());
         this.org$apache$spark$deploy$yarn$Client$$yarnClient().start();
         if (this.log().isDebugEnabled()) {
            this.logDebug((Function0)(() -> .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Requesting a new application from cluster with %d NodeManagers"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.org$apache$spark$deploy$yarn$Client$$yarnClient().getYarnClusterMetrics().getNumNodeManagers())}))));
         }

         YarnClientApplication newApp = this.org$apache$spark$deploy$yarn$Client$$yarnClient().createApplication();
         GetNewApplicationResponse newAppResponse = newApp.getNewApplicationResponse();
         this.appId_$eq(newAppResponse.getApplicationId());
         Path appStagingBaseDir = (Path)((Option)this.sparkConf().get(org.apache.spark.internal.config.package..MODULE$.STAGING_DIR())).map((x$4) -> new Path(x$4, UserGroupInformation.getCurrentUser().getShortUserName())).getOrElse(() -> FileSystem.get(this.hadoopConf()).getHomeDirectory());
         this.stagingDirPath_$eq(new Path(appStagingBaseDir, Client$.MODULE$.org$apache$spark$deploy$yarn$Client$$getAppStagingDir(this.org$apache$spark$deploy$yarn$Client$$appId())));
         (new CallerContext("CLIENT", (Option)this.sparkConf().get(org.apache.spark.internal.config.package..MODULE$.APP_CALLER_CONTEXT()), scala.Option..MODULE$.apply(this.org$apache$spark$deploy$yarn$Client$$appId().toString()), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$4(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$5(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$6(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$7(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$8(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$9())).setCurrentContext();
         this.verifyClusterResources(newAppResponse);
         ContainerLaunchContext containerContext = this.createContainerLaunchContext();
         ApplicationSubmissionContext appContext = this.createApplicationSubmissionContext(newApp, containerContext);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting application ", " to ResourceManager"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.org$apache$spark$deploy$yarn$Client$$appId())})))));
         this.org$apache$spark$deploy$yarn$Client$$yarnClient().submitApplication(appContext);
         this.launcherBackend().setAppId(this.org$apache$spark$deploy$yarn$Client$$appId().toString());
         this.reportLauncherState(State.SUBMITTED);
      } catch (Throwable var7) {
         if (this.org$apache$spark$deploy$yarn$Client$$stagingDirPath() != null) {
            this.cleanupStagingDir();
         }

         throw var7;
      }
   }

   private void cleanupStagingDir() {
      if (!BoxesRunTime.unboxToBoolean(this.sparkConf().get(package$.MODULE$.PRESERVE_STAGING_FILES()))) {
         this.cleanupStagingDirInternal$1();
      }
   }

   public ApplicationSubmissionContext createApplicationSubmissionContext(final YarnClientApplication newApp, final ContainerLaunchContext containerContext) {
      String componentName = this.org$apache$spark$deploy$yarn$Client$$isClusterMode() ? package$.MODULE$.YARN_DRIVER_RESOURCE_TYPES_PREFIX() : package$.MODULE$.YARN_AM_RESOURCE_TYPES_PREFIX();
      scala.collection.immutable.Map yarnAMResources = ResourceRequestHelper$.MODULE$.getYarnResourcesAndAmounts(this.sparkConf(), componentName);
      scala.collection.immutable.Map amResources = (scala.collection.immutable.Map)yarnAMResources.$plus$plus(ResourceRequestHelper$.MODULE$.getYarnResourcesFromSparkResources(org.apache.spark.internal.config.package..MODULE$.SPARK_DRIVER_PREFIX(), this.sparkConf()));
      this.logDebug((Function0)(() -> "AM resources: " + amResources));
      ApplicationSubmissionContext appContext = newApp.getApplicationSubmissionContext();
      appContext.setApplicationName(this.sparkConf().get("spark.app.name", "Spark"));
      appContext.setQueue((String)this.sparkConf().get(package$.MODULE$.QUEUE_NAME()));
      appContext.setAMContainerSpec(containerContext);
      appContext.setApplicationType((String)this.sparkConf().get(package$.MODULE$.APPLICATION_TYPE()));
      ((Option)this.sparkConf().get(package$.MODULE$.APPLICATION_TAGS())).foreach((tags) -> {
         $anonfun$createApplicationSubmissionContext$2(appContext, tags);
         return BoxedUnit.UNIT;
      });
      Option var9 = (Option)this.sparkConf().get(package$.MODULE$.MAX_APP_ATTEMPTS());
      if (var9 instanceof Some var10) {
         int v = BoxesRunTime.unboxToInt(var10.value());
         appContext.setMaxAppAttempts(v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!scala.None..MODULE$.equals(var9)) {
            throw new MatchError(var9);
         }

         this.logDebug((Function0)(() -> package$.MODULE$.MAX_APP_ATTEMPTS().key() + " is not set. Cluster's default value will be used."));
         BoxedUnit var17 = BoxedUnit.UNIT;
      }

      ((Option)this.sparkConf().get(package$.MODULE$.AM_ATTEMPT_FAILURE_VALIDITY_INTERVAL_MS())).foreach((JFunction1.mcVJ.sp)(interval) -> appContext.setAttemptFailuresValidityInterval(interval));
      Resource capability = (Resource)Records.newRecord(Resource.class);
      capability.setMemorySize((long)(this.amMemory() + this.amMemoryOverhead()));
      capability.setVirtualCores(this.amCores());
      if (amResources.nonEmpty()) {
         ResourceRequestHelper$.MODULE$.setResourceRequests(amResources, capability);
      }

      this.logDebug((Function0)(() -> "Created resource capability for AM request: " + capability));
      Option var13 = (Option)this.sparkConf().get(package$.MODULE$.AM_NODE_LABEL_EXPRESSION());
      if (var13 instanceof Some var14) {
         String expr = (String)var14.value();
         ResourceRequest amRequest = (ResourceRequest)Records.newRecord(ResourceRequest.class);
         amRequest.setResourceName("*");
         amRequest.setPriority(Priority.newInstance(0));
         amRequest.setCapability(capability);
         amRequest.setNumContainers(1);
         amRequest.setNodeLabelExpression(expr);
         appContext.setAMContainerResourceRequests(Collections.singletonList(amRequest));
         BoxedUnit var18 = BoxedUnit.UNIT;
      } else {
         if (!scala.None..MODULE$.equals(var13)) {
            throw new MatchError(var13);
         }

         appContext.setResource(capability);
         BoxedUnit var19 = BoxedUnit.UNIT;
      }

      ((Option)this.sparkConf().get(package$.MODULE$.ROLLED_LOG_INCLUDE_PATTERN())).foreach((includePattern) -> {
         $anonfun$createApplicationSubmissionContext$6(this, appContext, includePattern);
         return BoxedUnit.UNIT;
      });
      appContext.setUnmanagedAM(this.isClientUnmanagedAMEnabled());
      ((Option)this.sparkConf().get(package$.MODULE$.APPLICATION_PRIORITY())).foreach((JFunction1.mcVI.sp)(appPriority) -> appContext.setPriority(Priority.newInstance(appPriority)));
      return appContext;
   }

   private void setupSecurityToken(final ContainerLaunchContext amContainer) {
      UserGroupInformation currentUser = UserGroupInformation.getCurrentUser();
      Credentials credentials = currentUser.getCredentials();
      if (this.org$apache$spark$deploy$yarn$Client$$isClusterMode()) {
         HadoopDelegationTokenManager credentialManager = new HadoopDelegationTokenManager(this.sparkConf(), this.hadoopConf(), (RpcEndpointRef)null);
         credentialManager.obtainDelegationTokens(credentials);
      }

      byte[] serializedCreds = org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().serialize(credentials);
      amContainer.setTokens(ByteBuffer.wrap(serializedCreds));
   }

   private void setTokenConf(final ContainerLaunchContext amContainer) {
      ((Option)this.sparkConf().get(package$.MODULE$.AM_TOKEN_CONF_REGEX())).foreach((regex) -> {
         $anonfun$setTokenConf$1(this, amContainer, regex);
         return BoxedUnit.UNIT;
      });
   }

   public ApplicationReport getApplicationReport() {
      return this.org$apache$spark$deploy$yarn$Client$$yarnClient().getApplicationReport(this.org$apache$spark$deploy$yarn$Client$$appId());
   }

   private String getClientToken(final ApplicationReport report) {
      return (String)scala.Option..MODULE$.apply(report.getClientToAMToken()).map((x$5) -> x$5.toString()).getOrElse(() -> "");
   }

   private void verifyClusterResources(final GetNewApplicationResponse newAppResponse) {
      long maxMem = newAppResponse.getMaximumResourceCapability().getMemorySize();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Verifying our application has not requested more than the maximum memory "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"capability of the cluster (", " MB per container)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_MEMORY_SIZE..MODULE$, BoxesRunTime.boxToLong(maxMem))}))))));
      long executorMem = this.executorMemory() + (long)this.executorOffHeapMemory() + (long)this.executorMemoryOverhead() + (long)this.pysparkWorkerMemory();
      if (executorMem > maxMem) {
         long var7 = this.executorMemory();
         throw new IllegalArgumentException("Required executor memory (" + var7 + " MB), offHeap memory (" + this.executorOffHeapMemory() + ") MB, overhead (" + this.executorMemoryOverhead() + " MB), and PySpark memory (" + this.pysparkWorkerMemory() + " MB) is above the max threshold (" + maxMem + " MB) of this cluster! Please check the values of 'yarn.scheduler.maximum-allocation-mb' and/or 'yarn.nodemanager.resource.memory-mb'.");
      } else {
         int amMem = this.amMemory() + this.amMemoryOverhead();
         if ((long)amMem > maxMem) {
            int var10002 = this.amMemory();
            throw new IllegalArgumentException("Required AM memory (" + var10002 + "+" + this.amMemoryOverhead() + " MB) is above the max threshold (" + maxMem + " MB) of this cluster! Please check the values of 'yarn.scheduler.maximum-allocation-mb' and/or 'yarn.nodemanager.resource.memory-mb'.");
         } else {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Will allocate AM container, with ", " MB memory "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, BoxesRunTime.boxToInteger(amMem))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"including ", " MB overhead"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.OVERHEAD_MEMORY_SIZE..MODULE$, BoxesRunTime.boxToInteger(this.amMemoryOverhead()))}))))));
         }
      }
   }

   public Path copyFileToRemote(final Path destDir, final Path srcPath, final Option replication, final scala.collection.mutable.Map symlinkCache, final boolean force, final Option destName) {
      FileSystem destFs = destDir.getFileSystem(this.hadoopConf());
      FileSystem srcFs = srcPath.getFileSystem(this.hadoopConf());
      ObjectRef destPath = ObjectRef.create(srcPath);
      if (!force && Client$.MODULE$.compareFs(srcFs, destFs) && !"file".equals(srcFs.getScheme())) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Source and destination file systems are the same. "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not copying ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SRC_PATH..MODULE$, srcPath)}))))));
      } else {
         destPath.elem = new Path(destDir, (String)destName.getOrElse(() -> srcPath.getName()));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Uploading resource ", " -> "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SRC_PATH..MODULE$, srcPath)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TARGET_PATH..MODULE$, (Path)destPath.elem)}))))));

         try {
            BoxesRunTime.boxToBoolean(FileUtil.copy(srcFs, srcPath, destFs, (Path)destPath.elem, false, this.hadoopConf()));
         } catch (Throwable var16) {
            if (!(var16 instanceof PathOperationException) || !srcFs.makeQualified(srcPath).equals(destFs.makeQualified((Path)destPath.elem))) {
               throw var16;
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         replication.foreach((repl) -> BoxesRunTime.boxToBoolean($anonfun$copyFileToRemote$3(destFs, destPath, BoxesRunTime.unboxToShort(repl))));
         destFs.setPermission((Path)destPath.elem, new FsPermission(Client$.MODULE$.APP_FILE_PERMISSION()));
      }

      Path qualifiedDestPath = destFs.makeQualified((Path)destPath.elem);
      Path qualifiedDestDir = qualifiedDestPath.getParent();
      Path resolvedDestDir = (Path)symlinkCache.getOrElseUpdate(qualifiedDestDir.toUri(), () -> {
         FileContext fc = FileContext.getFileContext(qualifiedDestDir.toUri(), this.hadoopConf());
         return fc.resolvePath(qualifiedDestDir);
      });
      return new Path(resolvedDestDir, qualifiedDestPath.getName());
   }

   public boolean copyFileToRemote$default$5() {
      return false;
   }

   public Option copyFileToRemote$default$6() {
      return scala.None..MODULE$;
   }

   public HashMap directoriesToBePreloaded(final Seq files) {
      HashMap directoryToFiles = new HashMap();
      files.foreach((file) -> {
         if (!org.apache.spark.util.Utils..MODULE$.isLocalUri(file) && !(new GlobPattern(file)).hasWildcard()) {
            Path currentPath = new Path(org.apache.spark.util.Utils..MODULE$.resolveURI(file));
            URI parentUri = currentPath.getParent().toUri();
            return ((Growable)directoryToFiles.getOrElseUpdate(parentUri, () -> new HashSet())).$plus$eq(currentPath.getName());
         } else {
            return BoxedUnit.UNIT;
         }
      });
      return (HashMap)directoryToFiles.filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$directoriesToBePreloaded$3(this, x$6)));
   }

   public HashMap getPreloadedStatCache(final Seq files, final Function1 fsLookup) {
      HashMap statCache = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.directoriesToBePreloaded(files).foreach((x0$1) -> {
         $anonfun$getPreloadedStatCache$1(fsLookup, statCache, x0$1);
         return BoxedUnit.UNIT;
      });
      return statCache;
   }

   public Function1 getPreloadedStatCache$default$2() {
      return (x$7) -> FileSystem.get(x$7, this.hadoopConf());
   }

   public HashMap prepareLocalResources(final Path destDir, final Seq pySparkArchives) {
      this.logInfo((Function0)(() -> "Preparing resources for our AM container"));
      FileSystem fs = destDir.getFileSystem(this.hadoopConf());
      HashSet distributedUris = new HashSet();
      HashSet distributedNames = new HashSet();
      Option replication = ((Option)this.sparkConf().get(package$.MODULE$.STAGING_FILE_REPLICATION())).map((x$9x) -> BoxesRunTime.boxToShort($anonfun$prepareLocalResources$2(BoxesRunTime.unboxToInt(x$9x))));
      HashMap localResources = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      FileSystem.mkdirs(fs, destDir, new FsPermission(Client$.MODULE$.STAGING_DIR_PERMISSION()));
      HashMap var10000;
      if (this.statCachePreloadEnabled()) {
         Seq files = (Seq)((IterableOps)((IterableOps)((IterableOps)((IterableOps)((IterableOps)((Option)this.sparkConf().get(package$.MODULE$.SPARK_JARS())).getOrElse(() -> scala.collection.immutable.Nil..MODULE$)).$plus$plus((IterableOnce)this.sparkConf().get(package$.MODULE$.JARS_TO_DISTRIBUTE()))).$plus$plus((IterableOnce)this.sparkConf().get(package$.MODULE$.FILES_TO_DISTRIBUTE()))).$plus$plus((IterableOnce)this.sparkConf().get(package$.MODULE$.ARCHIVES_TO_DISTRIBUTE()))).$plus$plus((IterableOnce)this.sparkConf().get(org.apache.spark.internal.config.package..MODULE$.PY_FILES()))).$plus$plus(pySparkArchives);
         var10000 = this.getPreloadedStatCache(files, this.getPreloadedStatCache$default$2());
      } else {
         var10000 = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      }

      HashMap statCache;
      scala.collection.mutable.Map symlinkCache;
      label171: {
         statCache = var10000;
         symlinkCache = (scala.collection.mutable.Map)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         this.amKeytabFileName().foreach((kt) -> {
            $anonfun$prepareLocalResources$10(this, destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames, kt);
            return BoxedUnit.UNIT;
         });
         Option ivySettings = this.sparkConf().getOption("spark.jars.ivySettings");
         if (ivySettings instanceof Some var18) {
            String ivySettingsPath = (String)var18.value();
            if (this.org$apache$spark$deploy$yarn$Client$$isClusterMode()) {
               URI uri = new URI(ivySettingsPath);
               String var21 = (String)scala.Option..MODULE$.apply(uri.getScheme()).getOrElse(() -> "file");
               switch (var21 == null ? 0 : var21.hashCode()) {
                  case 3143036:
                     if ("file".equals(var21)) {
                        File ivySettingsFile = new File(uri.getPath());
                        scala.Predef..MODULE$.require(ivySettingsFile.exists(), () -> "Ivy settings file " + ivySettingsFile + " not found");
                        scala.Predef..MODULE$.require(ivySettingsFile.isFile(), () -> "Ivy settings file " + ivySettingsFile + " is not anormal file");
                        String var10002 = ivySettingsFile.getName();
                        Some localizedFileName = new Some(var10002 + "-" + UUID.randomUUID().toString());
                        LocalResourceType x$8 = distribute$default$2$1();
                        Option x$9 = distribute$default$4$1();
                        boolean x$10 = distribute$default$5$1();
                        Tuple2 var25 = this.distribute$1(ivySettingsPath, x$8, localizedFileName, x$9, x$10, destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
                        if (var25 == null) {
                           throw new MatchError(var25);
                        }

                        String localizedPath = (String)var25._2();
                        scala.Predef..MODULE$.require(localizedPath != null, () -> "IvySettings file already distributed.");
                        var54 = new Some(localizedPath);
                        break label171;
                     }

                     throw new IllegalArgumentException("Scheme " + var21 + " not supported in spark.jars.ivySettings");
                  default:
                     throw new IllegalArgumentException("Scheme " + var21 + " not supported in spark.jars.ivySettings");
               }
            }
         }

         var54 = scala.None..MODULE$;
      }

      Option ivySettingsLocalizedPath = (Option)var54;
      Option sparkArchive = (Option)this.sparkConf().get(package$.MODULE$.SPARK_ARCHIVE());
      if (sparkArchive.isDefined()) {
         String archive = (String)sparkArchive.get();
         scala.Predef..MODULE$.require(!org.apache.spark.util.Utils..MODULE$.isLocalUri(archive), () -> package$.MODULE$.SPARK_ARCHIVE().key() + " cannot be a local URI.");
         this.distribute$1(org.apache.spark.util.Utils..MODULE$.resolveURI(archive).toString(), LocalResourceType.ARCHIVE, new Some(Client$.MODULE$.LOCALIZED_LIB_DIR()), distribute$default$4$1(), distribute$default$5$1(), destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
      } else {
         Option var34 = (Option)this.sparkConf().get(package$.MODULE$.SPARK_JARS());
         if (var34 instanceof Some) {
            Some var35 = (Some)var34;
            Seq jars = (Seq)var35.value();
            ArrayBuffer localJars = new ArrayBuffer();
            jars.foreach((jar) -> {
               if (!org.apache.spark.util.Utils..MODULE$.isLocalUri(jar)) {
                  Path path = Client$.MODULE$.org$apache$spark$deploy$yarn$Client$$getQualifiedLocalPath(org.apache.spark.util.Utils..MODULE$.resolveURI(jar), this.hadoopConf());
                  FileSystem pathFs = FileSystem.get(path.toUri(), this.hadoopConf());
                  if (statCache.contains(path.toUri())) {
                     String x$11 = path.toUri().toString();
                     Some x$12 = new Some(Client$.MODULE$.LOCALIZED_LIB_DIR());
                     LocalResourceType x$13 = distribute$default$2$1();
                     Option x$14 = distribute$default$3$1();
                     boolean x$15 = distribute$default$5$1();
                     return this.distribute$1(x$11, x$13, x$14, x$12, x$15, destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
                  } else {
                     FileStatus[] fss = pathFs.globStatus(path);
                     if (fss == null) {
                        throw new FileNotFoundException("Path " + path.toString() + " does not exist");
                     } else {
                        scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])fss), (x$11x) -> BoxesRunTime.boxToBoolean($anonfun$prepareLocalResources$19(x$11x)))), (entry) -> {
                           URI uri = entry.getPath().toUri();
                           statCache.update(uri, entry);
                           String x$16 = uri.toString();
                           Some x$17 = new Some(Client$.MODULE$.LOCALIZED_LIB_DIR());
                           LocalResourceType x$18 = distribute$default$2$1();
                           Option x$19 = distribute$default$3$1();
                           boolean x$20 = distribute$default$5$1();
                           return this.distribute$1(x$16, x$18, x$19, x$17, x$20, destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
                        });
                        return BoxedUnit.UNIT;
                     }
                  }
               } else {
                  return localJars.$plus$eq(jar);
               }
            });
            this.sparkConf().set(package$.MODULE$.SPARK_JARS(), localJars.toSeq());
         } else {
            if (!scala.None..MODULE$.equals(var34)) {
               throw new MatchError(var34);
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Neither ", " nor "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, package$.MODULE$.SPARK_JARS().key())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "} is set, falling back to uploading "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, package$.MODULE$.SPARK_ARCHIVE().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"libraries under SPARK_HOME."})))).log(scala.collection.immutable.Nil..MODULE$))));
            File jarsDir = new File(YarnCommandBuilderUtils$.MODULE$.findJarsDir(this.sparkConf().getenv("SPARK_HOME")));
            File jarsArchive = File.createTempFile(Client$.MODULE$.LOCALIZED_LIB_DIR(), ".zip", new File(org.apache.spark.util.Utils..MODULE$.getLocalDir(this.sparkConf())));
            ZipOutputStream jarsStream = new ZipOutputStream(new FileOutputStream(jarsArchive));

            try {
               jarsStream.setLevel(0);
               scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])jarsDir.listFiles()), (f) -> {
                  $anonfun$prepareLocalResources$22(jarsStream, f);
                  return BoxedUnit.UNIT;
               });
            } finally {
               jarsStream.close();
            }

            this.distribute$1(jarsArchive.toURI().getPath(), LocalResourceType.ARCHIVE, new Some(Client$.MODULE$.LOCALIZED_LIB_DIR()), distribute$default$4$1(), distribute$default$5$1(), destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
            BoxesRunTime.boxToBoolean(jarsArchive.delete());
         }
      }

      scala.Option..MODULE$.apply(this.args().userJar()).filter((x$12) -> BoxesRunTime.boxToBoolean($anonfun$prepareLocalResources$23(x$12))).foreach((jar) -> {
         Some x$22 = new Some(Client$.MODULE$.APP_JAR_NAME());
         LocalResourceType x$23 = distribute$default$2$1();
         Option x$24 = distribute$default$4$1();
         boolean x$25 = distribute$default$5$1();
         Tuple2 var11 = this.distribute$1(jar, x$23, x$22, x$24, x$25, destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
         if (var11 != null) {
            boolean isLocal = var11._1$mcZ$sp();
            String localizedPath = (String)var11._2();
            Tuple2 var10 = new Tuple2(BoxesRunTime.boxToBoolean(isLocal), localizedPath);
            boolean isLocalx = var10._1$mcZ$sp();
            String localizedPathx = (String)var10._2();
            if (isLocalx) {
               scala.Predef..MODULE$.require(localizedPathx != null, () -> "Path " + jar + " already distributed");
               return this.sparkConf().set(package$.MODULE$.APP_JAR(), localizedPathx);
            } else {
               return BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(var11);
         }
      });
      ListBuffer cachedSecondaryJarLinks = scala.collection.mutable.ListBuffer..MODULE$.empty();
      (new scala.collection.immutable..colon.colon(new Tuple3(this.sparkConf().get(package$.MODULE$.JARS_TO_DISTRIBUTE()), LocalResourceType.FILE, BoxesRunTime.boxToBoolean(true)), new scala.collection.immutable..colon.colon(new Tuple3(this.sparkConf().get(package$.MODULE$.FILES_TO_DISTRIBUTE()), LocalResourceType.FILE, BoxesRunTime.boxToBoolean(false)), new scala.collection.immutable..colon.colon(new Tuple3(this.sparkConf().get(package$.MODULE$.ARCHIVES_TO_DISTRIBUTE()), LocalResourceType.ARCHIVE, BoxesRunTime.boxToBoolean(false)), scala.collection.immutable.Nil..MODULE$)))).foreach((x0$1) -> {
         $anonfun$prepareLocalResources$26(this, cachedSecondaryJarLinks, destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames, x0$1);
         return BoxedUnit.UNIT;
      });
      if (cachedSecondaryJarLinks.nonEmpty()) {
         this.sparkConf().set(package$.MODULE$.SECONDARY_JARS(), cachedSecondaryJarLinks.toSeq());
      } else {
         BoxedUnit var55 = BoxedUnit.UNIT;
      }

      if (this.org$apache$spark$deploy$yarn$Client$$isClusterMode() && this.args().primaryPyFile() != null) {
         String x$26 = this.args().primaryPyFile();
         boolean x$27 = true;
         LocalResourceType x$28 = distribute$default$2$1();
         Option x$29 = distribute$default$3$1();
         Option x$30 = distribute$default$4$1();
         this.distribute$1(x$26, x$28, x$29, x$30, true, destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
      } else {
         BoxedUnit var56 = BoxedUnit.UNIT;
      }

      pySparkArchives.foreach((f) -> {
         URI uri = org.apache.spark.util.Utils..MODULE$.resolveURI(f);
         String var10000 = uri.getScheme();
         String var10 = org.apache.spark.util.Utils..MODULE$.LOCAL_SCHEME();
         if (var10000 == null) {
            if (var10 != null) {
               return this.distribute$1(f, distribute$default$2$1(), distribute$default$3$1(), distribute$default$4$1(), distribute$default$5$1(), destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
            }
         } else if (!var10000.equals(var10)) {
            return this.distribute$1(f, distribute$default$2$1(), distribute$default$3$1(), distribute$default$4$1(), distribute$default$5$1(), destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
         }

         return BoxedUnit.UNIT;
      });
      ((IterableOnceOps)this.sparkConf().get(org.apache.spark.internal.config.package..MODULE$.PY_FILES())).foreach((f) -> {
         Option targetDir = (Option)(f.endsWith(".py") ? new Some(Client$.MODULE$.LOCALIZED_PYTHON_DIR()) : scala.None..MODULE$);
         LocalResourceType x$33 = distribute$default$2$1();
         Option x$34 = distribute$default$3$1();
         boolean x$35 = distribute$default$5$1();
         return this.distribute$1(f, x$33, x$34, targetDir, x$35, destDir, replication, symlinkCache, localResources, statCache, distributedUris, distributedNames);
      });
      this.distCacheMgr().updateConfiguration(this.org$apache$spark$deploy$yarn$Client$$cachedResourcesConf());
      Path remoteConfArchivePath = new Path(destDir, Client$.MODULE$.LOCALIZED_CONF_ARCHIVE());
      FileSystem remoteFs = FileSystem.get(remoteConfArchivePath.toUri(), this.hadoopConf());
      this.org$apache$spark$deploy$yarn$Client$$cachedResourcesConf().set(package$.MODULE$.CACHED_CONF_ARCHIVE(), remoteConfArchivePath.toString());
      scala.collection.mutable.Map confsToOverride = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      this.amKeytabFileName().foreach((kt) -> confsToOverride.put(org.apache.spark.internal.config.package..MODULE$.KEYTAB().key(), kt));
      ivySettingsLocalizedPath.foreach((path) -> confsToOverride.put("spark.jars.ivySettings", path));
      Path localConfArchive = new Path(this.createConfArchive(confsToOverride).toURI());
      this.copyFileToRemote(destDir, localConfArchive, replication, symlinkCache, true, new Some(Client$.MODULE$.LOCALIZED_CONF_ARCHIVE()));
      this.distCacheMgr().addResource(remoteFs, this.hadoopConf(), remoteConfArchivePath, localResources, LocalResourceType.ARCHIVE, Client$.MODULE$.LOCALIZED_CONF_DIR(), statCache, false);
      return localResources;
   }

   private File createConfArchive(final scala.collection.mutable.Map confsToOverride) {
      HashMap hadoopConfFiles = new HashMap();
      scala.sys.package..MODULE$.env().get("SPARK_CONF_DIR").foreach((localConfDir) -> {
         $anonfun$createConfArchive$1(hadoopConfFiles, localConfDir);
         return BoxedUnit.UNIT;
      });
      Seq confDirs = (Seq)(new scala.collection.immutable..colon.colon("HADOOP_CONF_DIR", new scala.collection.immutable..colon.colon("YARN_CONF_DIR", scala.collection.immutable.Nil..MODULE$))).$plus$plus((IterableOnce)(org.apache.spark.util.Utils..MODULE$.isTesting() ? new scala.collection.immutable..colon.colon("SPARK_TEST_HADOOP_CONF_DIR", scala.collection.immutable.Nil..MODULE$) : scala.collection.immutable.Nil..MODULE$));
      confDirs.foreach((envKey) -> {
         $anonfun$createConfArchive$3(this, hadoopConfFiles, envKey);
         return BoxedUnit.UNIT;
      });
      File confArchive = File.createTempFile(Client$.MODULE$.LOCALIZED_CONF_DIR(), ".zip", new File(org.apache.spark.util.Utils..MODULE$.getLocalDir(this.sparkConf())));
      ZipOutputStream confStream = new ZipOutputStream(new FileOutputStream(confArchive));
      this.logDebug((Function0)(() -> "Creating an archive with the config files for distribution at " + confArchive + "."));

      try {
         confStream.setLevel(0);
         Seq log4j2ConfigFiles = new scala.collection.immutable..colon.colon("log4j2.yaml", new scala.collection.immutable..colon.colon("log4j2.yml", new scala.collection.immutable..colon.colon("log4j2.json", new scala.collection.immutable..colon.colon("log4j2.jsn", new scala.collection.immutable..colon.colon("log4j2.xml", new scala.collection.immutable..colon.colon("log4j2.properties", scala.collection.immutable.Nil..MODULE$))))));
         ((IterableOnceOps)log4j2ConfigFiles.$plus$plus(new scala.collection.immutable..colon.colon("metrics.properties", scala.collection.immutable.Nil..MODULE$))).foreach((prop) -> {
            $anonfun$createConfArchive$8(confStream, prop);
            return BoxedUnit.UNIT;
         });
         confStream.putNextEntry(new ZipEntry(Client$.MODULE$.LOCALIZED_HADOOP_CONF_DIR() + "/"));
         confStream.closeEntry();
         hadoopConfFiles.foreach((x0$1) -> {
            $anonfun$createConfArchive$11(confStream, x0$1);
            return BoxedUnit.UNIT;
         });
         confStream.putNextEntry(new ZipEntry(org.apache.spark.deploy.SparkHadoopUtil..MODULE$.SPARK_HADOOP_CONF_FILE()));
         this.hadoopConf().writeXml(confStream);
         confStream.closeEntry();
         Properties props = Client$.MODULE$.confToProperties(this.sparkConf());
         confsToOverride.foreach((x0$2) -> {
            if (x0$2 != null) {
               String k = (String)x0$2._1();
               String v = (String)x0$2._2();
               return props.setProperty(k, v);
            } else {
               throw new MatchError(x0$2);
            }
         });
         Client$.MODULE$.writePropertiesToArchive(props, Client$.MODULE$.SPARK_CONF_FILE(), confStream);
         Client$.MODULE$.writePropertiesToArchive(Client$.MODULE$.confToProperties(this.org$apache$spark$deploy$yarn$Client$$cachedResourcesConf()), Client$.MODULE$.DIST_CACHE_CONF_FILE(), confStream);
      } finally {
         confStream.close();
      }

      return confArchive;
   }

   public HashMap setupLaunchEnv(final Path stagingDirPath, final Seq pySparkArchives) {
      this.logInfo((Function0)(() -> "Setting up the launch environment for our AM container"));
      HashMap env = new HashMap();
      Client$.MODULE$.populateClasspath(this.args(), this.hadoopConf(), this.sparkConf(), env, (Option)this.sparkConf().get(org.apache.spark.internal.config.package..MODULE$.DRIVER_CLASS_PATH()));
      env.update("SPARK_YARN_STAGING_DIR", stagingDirPath.toString());
      env.update("SPARK_PREFER_IPV6", Boolean.toString(org.apache.spark.util.Utils..MODULE$.preferIPv6()));
      String amEnvPrefix = "spark.yarn.appMasterEnv.";
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.sparkConf().getAll()), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$setupLaunchEnv$2(amEnvPrefix, x0$1)))), (x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            String v = (String)x0$2._2();
            return new Tuple2(k.substring(amEnvPrefix.length()), v);
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), (x0$3) -> {
         $anonfun$setupLaunchEnv$4(env, x0$3);
         return BoxedUnit.UNIT;
      });
      if (!env.contains("SPARK_USER")) {
         env.update("SPARK_USER", UserGroupInformation.getCurrentUser().getShortUserName());
      }

      ListBuffer pythonPath = new ListBuffer();
      Tuple2 var8 = ((IterableOps)this.sparkConf().get(org.apache.spark.internal.config.package..MODULE$.PY_FILES())).partition((x$14) -> BoxesRunTime.boxToBoolean($anonfun$setupLaunchEnv$5(x$14)));
      if (var8 != null) {
         Seq pyFiles = (Seq)var8._1();
         Seq pyArchives = (Seq)var8._2();
         Tuple2 var7 = new Tuple2(pyFiles, pyArchives);
         Seq pyFiles = (Seq)var7._1();
         Seq pyArchives = (Seq)var7._2();
         if (pyFiles.nonEmpty()) {
            pythonPath.$plus$eq(Client$.MODULE$.buildPath(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{Environment.PWD.$$(), Client$.MODULE$.LOCALIZED_PYTHON_DIR()}))));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         ((IterableOnceOps)pySparkArchives.$plus$plus(pyArchives)).foreach((path) -> {
            URI uri = org.apache.spark.util.Utils..MODULE$.resolveURI(path);
            String var10000 = uri.getScheme();
            String var3 = org.apache.spark.util.Utils..MODULE$.LOCAL_SCHEME();
            if (var10000 == null) {
               if (var3 != null) {
                  return (ListBuffer)pythonPath.$plus$eq(Client$.MODULE$.buildPath(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{Environment.PWD.$$(), (new Path(uri)).getName()}))));
               }
            } else if (!var10000.equals(var3)) {
               return (ListBuffer)pythonPath.$plus$eq(Client$.MODULE$.buildPath(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{Environment.PWD.$$(), (new Path(uri)).getName()}))));
            }

            return (ListBuffer)pythonPath.$plus$eq(uri.getPath());
         });
         if (pythonPath.nonEmpty()) {
            Iterable pythonPathList = (Iterable)scala.Option..MODULE$.option2Iterable(scala.sys.package..MODULE$.env().get("PYTHONPATH")).$plus$plus(pythonPath);
            env.update("PYTHONPATH", ((IterableOnceOps)scala.Option..MODULE$.option2Iterable(env.get("PYTHONPATH")).$plus$plus(pythonPathList)).mkString("<CPS>"));
            String pythonPathExecutorEnv = ((IterableOnceOps)scala.Option..MODULE$.option2Iterable(this.sparkConf().getExecutorEnv().toMap(scala..less.colon.less..MODULE$.refl()).get("PYTHONPATH")).$plus$plus(pythonPathList)).mkString("<CPS>");
            this.sparkConf().setExecutorEnv("PYTHONPATH", pythonPathExecutorEnv);
         } else {
            BoxedUnit var15 = BoxedUnit.UNIT;
         }

         if (this.org$apache$spark$deploy$yarn$Client$$isClusterMode()) {
            (new scala.collection.immutable..colon.colon("PYSPARK_DRIVER_PYTHON", new scala.collection.immutable..colon.colon("PYSPARK_PYTHON", scala.collection.immutable.Nil..MODULE$))).foreach((envname) -> {
               $anonfun$setupLaunchEnv$7(env, envname);
               return BoxedUnit.UNIT;
            });
            scala.sys.package..MODULE$.env().get("PYTHONHASHSEED").foreach((x$17) -> env.put("PYTHONHASHSEED", x$17));
         }

         (new scala.collection.immutable..colon.colon(Client$.MODULE$.ENV_DIST_CLASSPATH(), new scala.collection.immutable..colon.colon(Client$.MODULE$.SPARK_TESTING(), scala.collection.immutable.Nil..MODULE$))).foreach((envVar) -> {
            $anonfun$setupLaunchEnv$10(env, envVar);
            return BoxedUnit.UNIT;
         });
         return env;
      } else {
         throw new MatchError(var8);
      }
   }

   private ContainerLaunchContext createContainerLaunchContext() {
      this.logInfo((Function0)(() -> "Setting up container launch context for our AM"));
      Seq pySparkArchives = (Seq)(BoxesRunTime.unboxToBoolean(this.sparkConf().get(org.apache.spark.internal.config.package..MODULE$.IS_PYTHON_APP())) ? this.findPySparkArchives() : scala.collection.immutable.Nil..MODULE$);
      HashMap launchEnv = this.setupLaunchEnv(this.org$apache$spark$deploy$yarn$Client$$stagingDirPath(), pySparkArchives);
      HashMap localResources = this.prepareLocalResources(this.org$apache$spark$deploy$yarn$Client$$stagingDirPath(), pySparkArchives);
      ContainerLaunchContext amContainer = (ContainerLaunchContext)Records.newRecord(ContainerLaunchContext.class);
      amContainer.setLocalResources(scala.jdk.CollectionConverters..MODULE$.MutableMapHasAsJava(localResources).asJava());
      amContainer.setEnvironment(scala.jdk.CollectionConverters..MODULE$.MutableMapHasAsJava(launchEnv).asJava());
      ListBuffer javaOpts = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      javaOpts.$plus$eq("-Djava.net.preferIPv6Addresses=" + org.apache.spark.util.Utils..MODULE$.preferIPv6());
      javaOpts.$plus$eq(JavaModuleOptions.defaultModuleOptions());
      ObjectRef prefixEnv = ObjectRef.create(scala.None..MODULE$);
      javaOpts.$plus$eq("-Xmx" + this.amMemory() + "m");
      Path tmpDir = new Path(Environment.PWD.$$(), "./tmp");
      javaOpts.$plus$eq("-Djava.io.tmpdir=" + tmpDir);
      if (this.org$apache$spark$deploy$yarn$Client$$isClusterMode()) {
         ((Option)this.sparkConf().get(org.apache.spark.internal.config.package..MODULE$.DRIVER_JAVA_OPTIONS())).foreach((opts) -> (ListBuffer)javaOpts.$plus$plus$eq((IterableOnce)((IterableOps)org.apache.spark.util.Utils..MODULE$.splitCommandString(opts).map((x$18) -> org.apache.spark.util.Utils..MODULE$.substituteAppId(x$18, this.org$apache$spark$deploy$yarn$Client$$appId().toString()))).map((arg) -> YarnSparkHadoopUtil$.MODULE$.escapeForShell(arg))));
         Seq libraryPaths = (Seq)(new scala.collection.immutable..colon.colon((Option)this.sparkConf().get(org.apache.spark.internal.config.package..MODULE$.DRIVER_LIBRARY_PATH()), new scala.collection.immutable..colon.colon(scala.sys.package..MODULE$.props().get("spark.driver.libraryPath"), scala.collection.immutable.Nil..MODULE$))).flatten(scala.Predef..MODULE$.$conforms());
         if (libraryPaths.nonEmpty()) {
            prefixEnv.elem = new Some(Client$.MODULE$.createLibraryPathPrefix(libraryPaths.mkString(File.pathSeparator), this.sparkConf()));
         }

         if (((Option)this.sparkConf().get(package$.MODULE$.AM_JAVA_OPTIONS())).isDefined()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " will not take effect "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, package$.MODULE$.AM_JAVA_OPTIONS().key())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"in cluster mode"})))).log(scala.collection.immutable.Nil..MODULE$))));
         }
      } else {
         ((Option)this.sparkConf().get(package$.MODULE$.AM_JAVA_OPTIONS())).foreach((opts) -> {
            if (opts.contains("-Dspark")) {
               String var5 = package$.MODULE$.AM_JAVA_OPTIONS().key();
               String msg = var5 + " is not allowed to set Spark options (was '" + opts + "').";
               throw new SparkException(msg);
            } else if (opts.contains("-Xmx")) {
               String var10000 = package$.MODULE$.AM_JAVA_OPTIONS().key();
               String msg = var10000 + " is not allowed to specify max heap memory settings (was '" + opts + "'). Use spark.yarn.am.memory instead.";
               throw new SparkException(msg);
            } else {
               return (ListBuffer)javaOpts.$plus$plus$eq((IterableOnce)((IterableOps)org.apache.spark.util.Utils..MODULE$.splitCommandString(opts).map((x$19) -> org.apache.spark.util.Utils..MODULE$.substituteAppId(x$19, this.org$apache$spark$deploy$yarn$Client$$appId().toString()))).map((arg) -> YarnSparkHadoopUtil$.MODULE$.escapeForShell(arg)));
            }
         });
         ((Option)this.sparkConf().get(package$.MODULE$.AM_LIBRARY_PATH())).foreach((paths) -> {
            $anonfun$createContainerLaunchContext$9(this, prefixEnv, paths);
            return BoxedUnit.UNIT;
         });
      }

      javaOpts.$plus$eq("-Dspark.yarn.app.container.log.dir=<LOG_DIR>");
      Seq userClass = (Seq)(this.org$apache$spark$deploy$yarn$Client$$isClusterMode() ? new scala.collection.immutable..colon.colon("--class", new scala.collection.immutable..colon.colon(YarnSparkHadoopUtil$.MODULE$.escapeForShell(this.args().userClass()), scala.collection.immutable.Nil..MODULE$)) : scala.collection.immutable.Nil..MODULE$);
      Seq userJar = (Seq)(this.args().userJar() != null ? new scala.collection.immutable..colon.colon("--jar", new scala.collection.immutable..colon.colon(this.args().userJar(), scala.collection.immutable.Nil..MODULE$)) : scala.collection.immutable.Nil..MODULE$);
      Seq primaryPyFile = (Seq)(this.org$apache$spark$deploy$yarn$Client$$isClusterMode() && this.args().primaryPyFile() != null ? new scala.collection.immutable..colon.colon("--primary-py-file", new scala.collection.immutable..colon.colon((new Path(this.args().primaryPyFile())).getName(), scala.collection.immutable.Nil..MODULE$)) : scala.collection.immutable.Nil..MODULE$);
      Seq primaryRFile = (Seq)(this.args().primaryRFile() != null ? new scala.collection.immutable..colon.colon("--primary-r-file", new scala.collection.immutable..colon.colon(this.args().primaryRFile(), scala.collection.immutable.Nil..MODULE$)) : scala.collection.immutable.Nil..MODULE$);
      String amClass = this.org$apache$spark$deploy$yarn$Client$$isClusterMode() ? org.apache.spark.util.Utils..MODULE$.classForName("org.apache.spark.deploy.yarn.ApplicationMaster", org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3()).getName() : org.apache.spark.util.Utils..MODULE$.classForName("org.apache.spark.deploy.yarn.ExecutorLauncher", org.apache.spark.util.Utils..MODULE$.classForName$default$2(), org.apache.spark.util.Utils..MODULE$.classForName$default$3()).getName();
      if (this.args().primaryRFile() != null && (this.args().primaryRFile().endsWith(".R") || this.args().primaryRFile().endsWith(".r"))) {
         this.args().userArgs_$eq((ArrayBuffer)((IterableOps)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{this.args().primaryRFile()})))).$plus$plus(this.args().userArgs()));
      }

      ArrayBuffer userArgs = (ArrayBuffer)this.args().userArgs().flatMap((arg) -> new scala.collection.immutable..colon.colon("--arg", new scala.collection.immutable..colon.colon(YarnSparkHadoopUtil$.MODULE$.escapeForShell(arg), scala.collection.immutable.Nil..MODULE$)));
      Seq amArgs = (Seq)((IterableOps)((IterableOps)((IterableOps)((IterableOps)((IterableOps)((IterableOps)(new scala.collection.immutable..colon.colon(amClass, scala.collection.immutable.Nil..MODULE$)).$plus$plus(userClass)).$plus$plus(userJar)).$plus$plus(primaryPyFile)).$plus$plus(primaryRFile)).$plus$plus(userArgs)).$plus$plus(new scala.collection.immutable..colon.colon("--properties-file", new scala.collection.immutable..colon.colon(Client$.MODULE$.buildPath(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{Environment.PWD.$$(), Client$.MODULE$.LOCALIZED_CONF_DIR(), Client$.MODULE$.SPARK_CONF_FILE()}))), scala.collection.immutable.Nil..MODULE$)))).$plus$plus(new scala.collection.immutable..colon.colon("--dist-cache-conf", new scala.collection.immutable..colon.colon(Client$.MODULE$.buildPath(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{Environment.PWD.$$(), Client$.MODULE$.LOCALIZED_CONF_DIR(), Client$.MODULE$.DIST_CACHE_CONF_FILE()}))), scala.collection.immutable.Nil..MODULE$)));
      Iterable commands = (Iterable)((IterableOps)((IterableOps)((IterableOps)scala.Option..MODULE$.option2Iterable((Option)prefixEnv.elem).$plus$plus(new scala.collection.immutable..colon.colon(Environment.JAVA_HOME.$$() + "/bin/java", new scala.collection.immutable..colon.colon("-server", scala.collection.immutable.Nil..MODULE$)))).$plus$plus(javaOpts)).$plus$plus(amArgs)).$plus$plus(new scala.collection.immutable..colon.colon("1>", new scala.collection.immutable..colon.colon("<LOG_DIR>/stdout", new scala.collection.immutable..colon.colon("2>", new scala.collection.immutable..colon.colon("<LOG_DIR>/stderr", scala.collection.immutable.Nil..MODULE$)))));
      List printableCommands = ((IterableOnceOps)commands.map((s) -> s == null ? "null" : s)).toList();
      amContainer.setCommands(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(printableCommands).asJava());
      this.logDebug((Function0)(() -> "==============================================================================="));
      this.logDebug((Function0)(() -> "YARN AM launch context:"));
      this.logDebug((Function0)(() -> {
         Option var10000 = scala.Option..MODULE$.apply(this.args().userClass());
         return "    user class: " + var10000.getOrElse(() -> "N/A");
      }));
      this.logDebug((Function0)(() -> "    env:"));
      if (this.log().isDebugEnabled()) {
         org.apache.spark.util.Utils..MODULE$.redact(this.sparkConf(), launchEnv.toSeq()).foreach((x0$1) -> {
            $anonfun$createContainerLaunchContext$17(this, x0$1);
            return BoxedUnit.UNIT;
         });
      }

      this.logDebug((Function0)(() -> "    resources:"));
      localResources.foreach((x0$2) -> {
         $anonfun$createContainerLaunchContext$20(this, x0$2);
         return BoxedUnit.UNIT;
      });
      this.logDebug((Function0)(() -> "    command:"));
      this.logDebug((Function0)(() -> "        " + printableCommands.mkString(" ")));
      this.logDebug((Function0)(() -> "==============================================================================="));
      SecurityManager securityManager = new SecurityManager(this.sparkConf(), org.apache.spark.SecurityManager..MODULE$.$lessinit$greater$default$2(), org.apache.spark.SecurityManager..MODULE$.$lessinit$greater$default$3());
      amContainer.setApplicationACLs(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(YarnSparkHadoopUtil$.MODULE$.getApplicationAclsForYarn(securityManager)).asJava());
      this.setupSecurityToken(amContainer);
      this.setTokenConf(amContainer);
      return amContainer;
   }

   public YarnAppReport monitorApplication(final boolean returnOnRunning, final boolean logApplicationReport, final long interval) {
      YarnApplicationState lastState = null;
      int reportsTillNextLog = BoxesRunTime.unboxToInt(this.sparkConf().get(package$.MODULE$.REPORT_LOG_FREQUENCY()));
      int reportsSinceLastLog = 0;

      while(true) {
         Thread.sleep(interval);

         ApplicationReport var10000;
         try {
            var10000 = this.getApplicationReport();
         } catch (Throwable var28) {
            if (var28 instanceof ApplicationNotFoundException) {
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application ", " not found."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.org$apache$spark$deploy$yarn$Client$$appId())})))));
               this.cleanupStagingDir();
               return new YarnAppReport(YarnApplicationState.KILLED, FinalApplicationStatus.KILLED, scala.None..MODULE$);
            }

            if (var28 != null) {
               Option var14 = scala.util.control.NonFatal..MODULE$.unapply(var28);
               if (!var14.isEmpty()) {
                  Throwable e = (Throwable)var14.get();
                  if (!(e instanceof InterruptedIOException)) {
                     MessageWithContext msg = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to contact YARN for application ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.org$apache$spark$deploy$yarn$Client$$appId())})));
                     this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msg), e);
                     return new YarnAppReport(YarnApplicationState.FAILED, FinalApplicationStatus.FAILED, new Some(msg.message()));
                  }
               }
            }

            throw var28;
         }

         ApplicationReport report = var10000;
         YarnApplicationState state = report.getYarnApplicationState();
         ++reportsSinceLastLog;
         if (logApplicationReport) {
            label123: {
               label122: {
                  if (lastState == null) {
                     if (state != null) {
                        break label122;
                     }
                  } else if (!lastState.equals(state)) {
                     break label122;
                  }

                  if (reportsSinceLastLog < reportsTillNextLog) {
                     break label123;
                  }
               }

               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application report for ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.org$apache$spark$deploy$yarn$Client$$appId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(state: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_STATE..MODULE$, state)}))))));
               reportsSinceLastLog = 0;
            }

            if (this.log().isDebugEnabled()) {
               this.logDebug((Function0)(() -> this.formatReportDetails(report, this.getDriverLogsLink(report))));
            } else {
               label171: {
                  if (lastState == null) {
                     if (state == null) {
                        break label171;
                     }
                  } else if (lastState.equals(state)) {
                     break label171;
                  }

                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REPORT_DETAILS..MODULE$, this.formatReportDetails(report, this.getDriverLogsLink(report)))})))));
               }
            }
         }

         label130: {
            if (lastState == null) {
               if (state == null) {
                  break label130;
               }
            } else if (lastState.equals(state)) {
               break label130;
            }

            if (YarnApplicationState.RUNNING.equals(state)) {
               this.reportLauncherState(State.RUNNING);
               BoxedUnit var29 = BoxedUnit.UNIT;
            } else if (YarnApplicationState.FINISHED.equals(state)) {
               FinalApplicationStatus var22 = report.getFinalApplicationStatus();
               if (FinalApplicationStatus.FAILED.equals(var22)) {
                  this.reportLauncherState(State.FAILED);
                  BoxedUnit var30 = BoxedUnit.UNIT;
               } else if (FinalApplicationStatus.KILLED.equals(var22)) {
                  this.reportLauncherState(State.KILLED);
                  BoxedUnit var31 = BoxedUnit.UNIT;
               } else {
                  this.reportLauncherState(State.FINISHED);
                  BoxedUnit var32 = BoxedUnit.UNIT;
               }

               BoxedUnit var33 = BoxedUnit.UNIT;
            } else if (YarnApplicationState.FAILED.equals(state)) {
               this.reportLauncherState(State.FAILED);
               BoxedUnit var34 = BoxedUnit.UNIT;
            } else if (YarnApplicationState.KILLED.equals(state)) {
               this.reportLauncherState(State.KILLED);
               BoxedUnit var35 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var36 = BoxedUnit.UNIT;
            }
         }

         label177: {
            YarnApplicationState var23 = YarnApplicationState.FINISHED;
            if (state == null) {
               if (var23 == null) {
                  break label177;
               }
            } else if (state.equals(var23)) {
               break label177;
            }

            YarnApplicationState var24 = YarnApplicationState.FAILED;
            if (state == null) {
               if (var24 == null) {
                  break label177;
               }
            } else if (state.equals(var24)) {
               break label177;
            }

            YarnApplicationState var25 = YarnApplicationState.KILLED;
            if (state == null) {
               if (var25 == null) {
                  break label177;
               }
            } else if (state.equals(var25)) {
               break label177;
            }

            if (returnOnRunning) {
               YarnApplicationState var26 = YarnApplicationState.RUNNING;
               if (state == null) {
                  if (var26 == null) {
                     return Client$.MODULE$.createAppReport(report);
                  }
               } else if (state.equals(var26)) {
                  return Client$.MODULE$.createAppReport(report);
               }
            }

            label137: {
               YarnApplicationState var27 = YarnApplicationState.ACCEPTED;
               if (state == null) {
                  if (var27 != null) {
                     break label137;
                  }
               } else if (!state.equals(var27)) {
                  break label137;
               }

               if (this.isClientUnmanagedAMEnabled() && this.appMaster() == null && report.getAMRMToken() != null) {
                  this.appMaster_$eq(this.startApplicationMasterService(report));
               }
            }

            lastState = state;
            continue;
         }

         this.cleanupStagingDir();
         return Client$.MODULE$.createAppReport(report);
      }
   }

   public boolean monitorApplication$default$1() {
      return false;
   }

   public boolean monitorApplication$default$2() {
      return true;
   }

   public long monitorApplication$default$3() {
      return BoxesRunTime.unboxToLong(this.sparkConf().get(package$.MODULE$.REPORT_INTERVAL()));
   }

   private ApplicationMaster startApplicationMasterService(final ApplicationReport report) {
      Token token = report.getAMRMToken();
      org.apache.hadoop.security.token.Token amRMToken = new org.apache.hadoop.security.token.Token(token.getIdentifier().array(), token.getPassword().array(), new Text(token.getKind()), new Text(token.getService()));
      UserGroupInformation currentUGI = UserGroupInformation.getCurrentUser();
      currentUGI.addToken(amRMToken);
      ApplicationMaster appMaster = new ApplicationMaster(new ApplicationMasterArguments((String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class))), this.sparkConf(), this.hadoopConf());
      Thread amService = new Thread(appMaster, report) {
         // $FF: synthetic field
         private final Client $outer;
         private final ApplicationMaster appMaster$1;
         private final ApplicationReport report$2;

         public void run() {
            this.appMaster$1.runUnmanaged(this.$outer.rpcEnv(), this.report$2.getCurrentApplicationAttemptId(), this.$outer.org$apache$spark$deploy$yarn$Client$$stagingDirPath(), this.$outer.org$apache$spark$deploy$yarn$Client$$cachedResourcesConf());
         }

         public {
            if (Client.this == null) {
               throw null;
            } else {
               this.$outer = Client.this;
               this.appMaster$1 = appMaster$1;
               this.report$2 = report$2;
            }
         }
      };
      amService.setDaemon(true);
      amService.start();
      return appMaster;
   }

   private String formatReportDetails(final ApplicationReport report, final scala.collection.immutable.Map driverLogsLinks) {
      Seq details = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2("client token", this.getClientToken(report)), new Tuple2("diagnostics", report.getDiagnostics()), new Tuple2("ApplicationMaster host", report.getHost()), new Tuple2("ApplicationMaster RPC port", Integer.toString(report.getRpcPort())), new Tuple2("queue", report.getQueue()), new Tuple2("start time", Long.toString(report.getStartTime())), new Tuple2("final status", report.getFinalApplicationStatus().toString()), new Tuple2("tracking URL", report.getTrackingUrl()), new Tuple2("user", report.getUser())}))).$plus$plus(driverLogsLinks.map((x0$1) -> {
         if (x0$1 != null) {
            String fname = (String)x0$1._1();
            String link = (String)x0$1._2();
            return new Tuple2("Driver Logs (" + fname + ")", link);
         } else {
            throw new MatchError(x0$1);
         }
      }));
      return ((IterableOnceOps)details.map((x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            String v = (String)x0$2._2();
            String newValue = (String)scala.Option..MODULE$.apply(v).filter((x$20) -> BoxesRunTime.boxToBoolean($anonfun$formatReportDetails$3(x$20))).getOrElse(() -> "N/A");
            return "\n\t " + k + ": " + newValue;
         } else {
            throw new MatchError(x0$2);
         }
      })).mkString("");
   }

   private scala.collection.immutable.Map getDriverLogsLink(final ApplicationReport appReport) {
      if (BoxesRunTime.unboxToBoolean(this.sparkConf().get(package$.MODULE$.CLIENT_INCLUDE_DRIVER_LOGS_LINK()))) {
         YarnApplicationState var10000 = appReport.getYarnApplicationState();
         YarnApplicationState var2 = YarnApplicationState.RUNNING;
         if (var10000 == null) {
            if (var2 != null) {
               return scala.collection.immutable.Map..MODULE$.empty();
            }
         } else if (!var10000.equals(var2)) {
            return scala.collection.immutable.Map..MODULE$.empty();
         }

         try {
            var5 = (scala.collection.immutable.Map)scala.Option..MODULE$.apply(appReport.getCurrentApplicationAttemptId()).flatMap((attemptId) -> scala.Option..MODULE$.apply(this.org$apache$spark$deploy$yarn$Client$$yarnClient().getApplicationAttemptReport(attemptId))).flatMap((attemptReport) -> scala.Option..MODULE$.apply(attemptReport.getAMContainerId())).flatMap((amContainerId) -> scala.Option..MODULE$.apply(this.org$apache$spark$deploy$yarn$Client$$yarnClient().getContainerReport(amContainerId))).flatMap((containerReport) -> scala.Option..MODULE$.apply(containerReport.getLogUrl())).map((baseUrl) -> YarnContainerInfoHelper$.MODULE$.getLogUrlsFromBaseUrl(baseUrl)).getOrElse(() -> scala.collection.immutable.Map..MODULE$.empty());
         } catch (Exception var4) {
            this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to get driver log links for ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.org$apache$spark$deploy$yarn$Client$$appId())})))), var4);
            this.logDebug((Function0)(() -> "Unable to get driver log links for " + this.org$apache$spark$deploy$yarn$Client$$appId()), var4);
            var5 = scala.collection.immutable.Map..MODULE$.empty();
         }

         return var5;
      } else {
         return scala.collection.immutable.Map..MODULE$.empty();
      }
   }

   public void run() {
      this.submitApplication();
      if (!this.launcherBackend().isConnected() && this.fireAndForget()) {
         YarnApplicationState state;
         label90: {
            ApplicationReport report = this.getApplicationReport();
            state = report.getYarnApplicationState();
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application report for ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.org$apache$spark$deploy$yarn$Client$$appId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(state: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_STATE..MODULE$, state)}))))));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REPORT_DETAILS..MODULE$, this.formatReportDetails(report, this.getDriverLogsLink(report)))})))));
            YarnApplicationState var4 = YarnApplicationState.FAILED;
            if (state == null) {
               if (var4 == null) {
                  break label90;
               }
            } else if (state.equals(var4)) {
               break label90;
            }

            YarnApplicationState var5 = YarnApplicationState.KILLED;
            if (state == null) {
               if (var5 == null) {
                  break label90;
               }
            } else if (state.equals(var5)) {
               break label90;
            }

            return;
         }

         ApplicationId var10002 = this.org$apache$spark$deploy$yarn$Client$$appId();
         throw new SparkException("Application " + var10002 + " finished with status: " + state);
      } else {
         YarnAppReport var7 = this.monitorApplication(this.monitorApplication$default$1(), this.monitorApplication$default$2(), this.monitorApplication$default$3());
         if (var7 == null) {
            throw new MatchError(var7);
         } else {
            Option diags;
            label106: {
               YarnApplicationState appState = var7.appState();
               FinalApplicationStatus finalState = var7.finalState();
               Option diags = var7.diagnostics();
               Tuple3 var6 = new Tuple3(appState, finalState, diags);
               YarnApplicationState appState = (YarnApplicationState)var6._1();
               FinalApplicationStatus finalState = (FinalApplicationStatus)var6._2();
               diags = (Option)var6._3();
               YarnApplicationState var14 = YarnApplicationState.FAILED;
               if (appState == null) {
                  if (var14 == null) {
                     break label106;
                  }
               } else if (appState.equals(var14)) {
                  break label106;
               }

               FinalApplicationStatus var15 = FinalApplicationStatus.FAILED;
               if (finalState == null) {
                  if (var15 == null) {
                     break label106;
                  }
               } else if (finalState.equals(var15)) {
                  break label106;
               }

               YarnApplicationState var16 = YarnApplicationState.KILLED;
               if (appState == null) {
                  if (var16 == null) {
                     throw new SparkException("Application " + this.org$apache$spark$deploy$yarn$Client$$appId() + " is killed");
                  }
               } else if (appState.equals(var16)) {
                  throw new SparkException("Application " + this.org$apache$spark$deploy$yarn$Client$$appId() + " is killed");
               }

               FinalApplicationStatus var17 = FinalApplicationStatus.KILLED;
               if (finalState == null) {
                  if (var17 == null) {
                     throw new SparkException("Application " + this.org$apache$spark$deploy$yarn$Client$$appId() + " is killed");
                  }
               } else if (finalState.equals(var17)) {
                  throw new SparkException("Application " + this.org$apache$spark$deploy$yarn$Client$$appId() + " is killed");
               }

               FinalApplicationStatus var18 = FinalApplicationStatus.UNDEFINED;
               if (finalState == null) {
                  if (var18 == null) {
                     throw new SparkException("The final status of application " + this.org$apache$spark$deploy$yarn$Client$$appId() + " is undefined");
                  }
               } else if (finalState.equals(var18)) {
                  throw new SparkException("The final status of application " + this.org$apache$spark$deploy$yarn$Client$$appId() + " is undefined");
               }

               return;
            }

            diags.foreach((err) -> {
               $anonfun$run$3(this, err);
               return BoxedUnit.UNIT;
            });
            throw new SparkException("Application " + this.org$apache$spark$deploy$yarn$Client$$appId() + " finished with failed status");
         }
      }
   }

   private Seq findPySparkArchives() {
      return (Seq)scala.sys.package..MODULE$.env().get("PYSPARK_ARCHIVES_PATH").map((x$22) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$22.split(",")).toImmutableArraySeq()).getOrElse(() -> {
         String pyLibPath = (new scala.collection.immutable..colon.colon((String)scala.sys.package..MODULE$.env().apply("SPARK_HOME"), new scala.collection.immutable..colon.colon("python", new scala.collection.immutable..colon.colon("lib", scala.collection.immutable.Nil..MODULE$)))).mkString(File.separator);
         File pyArchivesFile = new File(pyLibPath, "pyspark.zip");
         scala.Predef..MODULE$.require(pyArchivesFile.exists(), () -> pyArchivesFile + " not found; cannot run pyspark application in YARN mode.");
         File py4jFile = new File(pyLibPath, org.apache.spark.api.python.PythonUtils..MODULE$.PY4J_ZIP_NAME());
         scala.Predef..MODULE$.require(py4jFile.exists(), () -> py4jFile + " not found; cannot run pyspark application in YARN mode.");
         return new scala.collection.immutable..colon.colon(pyArchivesFile.getAbsolutePath(), new scala.collection.immutable..colon.colon(py4jFile.getAbsolutePath(), scala.collection.immutable.Nil..MODULE$));
      });
   }

   private final void cleanupStagingDirInternal$1() {
      try {
         FileSystem fs = this.org$apache$spark$deploy$yarn$Client$$stagingDirPath().getFileSystem(this.hadoopConf());
         if (fs.delete(this.org$apache$spark$deploy$yarn$Client$$stagingDirPath(), true)) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleted staging directory ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.org$apache$spark$deploy$yarn$Client$$stagingDirPath())})))));
         }
      } catch (IOException var3) {
         this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to cleanup staging dir ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.org$apache$spark$deploy$yarn$Client$$stagingDirPath())})))), var3);
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$createApplicationSubmissionContext$2(final ApplicationSubmissionContext appContext$1, final Seq tags) {
      appContext$1.setApplicationTags(new java.util.HashSet(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(tags).asJava()));
   }

   // $FF: synthetic method
   public static final void $anonfun$createApplicationSubmissionContext$7(final LogAggregationContext logAggregationContext$1, final String excludePattern) {
      logAggregationContext$1.setRolledLogsExcludePattern(excludePattern);
   }

   // $FF: synthetic method
   public static final void $anonfun$createApplicationSubmissionContext$6(final Client $this, final ApplicationSubmissionContext appContext$1, final String includePattern) {
      try {
         LogAggregationContext logAggregationContext = (LogAggregationContext)Records.newRecord(LogAggregationContext.class);
         logAggregationContext.setRolledLogsIncludePattern(includePattern);
         ((Option)$this.sparkConf().get(package$.MODULE$.ROLLED_LOG_EXCLUDE_PATTERN())).foreach((excludePattern) -> {
            $anonfun$createApplicationSubmissionContext$7(logAggregationContext, excludePattern);
            return BoxedUnit.UNIT;
         });
         appContext$1.setLogAggregationContext(logAggregationContext);
      } catch (Throwable var8) {
         if (var8 == null || !scala.util.control.NonFatal..MODULE$.apply(var8)) {
            throw var8;
         }

         $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring ", "} "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, package$.MODULE$.ROLLED_LOG_INCLUDE_PATTERN().key())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"because the version of YARN does not support it"})))).log(scala.collection.immutable.Nil..MODULE$))), var8);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$setTokenConf$3(final Client $this, final String regex$1, final Configuration copy$1, final Map.Entry entry) {
      if (((String)entry.getKey()).matches(regex$1)) {
         copy$1.set((String)entry.getKey(), (String)entry.getValue());
         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Captured key: ", " -> "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.KEY..MODULE$, entry.getKey())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"value: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VALUE..MODULE$, entry.getValue())}))))));
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$setTokenConf$1(final Client $this, final ContainerLaunchContext amContainer$1, final String regex) {
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Processing token conf (spark.yarn.am.tokenConfRegex) with "})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"regex ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOKEN_REGEX..MODULE$, regex)}))))));
      DataOutputBuffer dob = new DataOutputBuffer();
      Configuration copy = new Configuration(false);
      copy.clear();
      scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala($this.hadoopConf()).asScala().foreach((entry) -> {
         $anonfun$setTokenConf$3($this, regex, copy, entry);
         return BoxedUnit.UNIT;
      });
      copy.write(dob);
      amContainer$1.setTokensConf(ByteBuffer.wrap(dob.getData()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$copyFileToRemote$3(final FileSystem destFs$1, final ObjectRef destPath$1, final short repl) {
      return destFs$1.setReplication((Path)destPath$1.elem, repl);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$directoriesToBePreloaded$3(final Client $this, final Tuple2 x$6) {
      return ((HashSet)x$6._2()).size() >= $this.statCachePreloadDirectoryCountThreshold();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPreloadedStatCache$2(final FileStatus x$8) {
      return x$8.isFile();
   }

   // $FF: synthetic method
   public static final void $anonfun$getPreloadedStatCache$1(final Function1 fsLookup$1, final HashMap statCache$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         URI dir = (URI)x0$1._1();
         HashSet filesInDir = (HashSet)x0$1._2();
         if (dir != null && filesInDir != null) {
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])((FileSystem)fsLookup$1.apply(dir)).listStatus(new Path(dir), new PathFilter(filesInDir) {
               private final HashSet x3$1;

               public boolean accept(final Path path) {
                  return this.x3$1.contains(path.getName());
               }

               public {
                  this.x3$1 = x3$1;
               }
            })), (x$8) -> BoxesRunTime.boxToBoolean($anonfun$getPreloadedStatCache$2(x$8)))), (fileStatus) -> {
               URI uri = fileStatus.getPath().toUri();
               return statCache$1.put(uri, fileStatus);
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final short $anonfun$prepareLocalResources$2(final int x$9) {
      return (short)x$9;
   }

   private final boolean addDistributedUri$1(final URI uri, final HashSet distributedUris$1, final HashSet distributedNames$1) {
      String uriStr = uri.toString();
      String fileName = (new File(uri.getPath())).getName();
      if (distributedUris$1.contains(uriStr)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Same path resource ", " added multiple times "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URI..MODULE$, uri)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to distributed cache."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return false;
      } else if (distributedNames$1.contains(fileName)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Same name resource ", " added multiple times "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URI..MODULE$, uri)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to distributed cache"})))).log(scala.collection.immutable.Nil..MODULE$))));
         return false;
      } else {
         distributedUris$1.$plus$eq(uriStr);
         distributedNames$1.$plus$eq(fileName);
         return true;
      }
   }

   private final Tuple2 distribute$1(final String path, final LocalResourceType resType, final Option destName, final Option targetDir, final boolean appMasterOnly, final Path destDir$1, final Option replication$1, final scala.collection.mutable.Map symlinkCache$1, final HashMap localResources$1, final HashMap statCache$2, final HashSet distributedUris$1, final HashSet distributedNames$1) {
      String trimmedPath = path.trim();
      URI localURI = org.apache.spark.util.Utils..MODULE$.resolveURI(trimmedPath);
      String var10000 = localURI.getScheme();
      String var15 = org.apache.spark.util.Utils..MODULE$.LOCAL_SCHEME();
      if (var10000 == null) {
         if (var15 == null) {
            return new Tuple2(BoxesRunTime.boxToBoolean(true), trimmedPath);
         }
      } else if (var10000.equals(var15)) {
         return new Tuple2(BoxesRunTime.boxToBoolean(true), trimmedPath);
      }

      if (this.addDistributedUri$1(localURI, distributedUris$1, distributedNames$1)) {
         Path localPath = Client$.MODULE$.org$apache$spark$deploy$yarn$Client$$getQualifiedLocalPath(localURI, this.hadoopConf());
         String linkname = (String)targetDir.map((x$10) -> x$10 + "/").getOrElse(() -> "") + destName.orElse(() -> scala.Option..MODULE$.apply(localURI.getFragment())).getOrElse(() -> localPath.getName());
         Path destPath = this.copyFileToRemote(destDir$1, localPath, replication$1, symlinkCache$1, this.copyFileToRemote$default$5(), this.copyFileToRemote$default$6());
         FileSystem destFs = FileSystem.get(destPath.toUri(), this.hadoopConf());
         this.distCacheMgr().addResource(destFs, this.hadoopConf(), destPath, localResources$1, resType, linkname, statCache$2, appMasterOnly);
         return new Tuple2(BoxesRunTime.boxToBoolean(false), linkname);
      } else {
         return new Tuple2(BoxesRunTime.boxToBoolean(false), (Object)null);
      }
   }

   private static final LocalResourceType distribute$default$2$1() {
      return LocalResourceType.FILE;
   }

   private static final Option distribute$default$3$1() {
      return scala.None..MODULE$;
   }

   private static final Option distribute$default$4$1() {
      return scala.None..MODULE$;
   }

   private static final boolean distribute$default$5$1() {
      return false;
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareLocalResources$10(final Client $this, final Path destDir$1, final Option replication$1, final scala.collection.mutable.Map symlinkCache$1, final HashMap localResources$1, final HashMap statCache$2, final HashSet distributedUris$1, final HashSet distributedNames$1, final String kt) {
      $this.logInfo((Function0)(() -> "To enable the AM to login from keytab, credentials are being copied over to the AM via the YARN Secure Distributed Cache."));
      String x$1 = $this.keytab();
      Some x$2 = new Some(kt);
      boolean x$3 = true;
      LocalResourceType x$4 = distribute$default$2$1();
      Option x$5 = distribute$default$4$1();
      Tuple2 var11 = $this.distribute$1(x$1, x$4, x$2, x$5, true, destDir$1, replication$1, symlinkCache$1, localResources$1, statCache$2, distributedUris$1, distributedNames$1);
      if (var11 != null) {
         String localizedPath = (String)var11._2();
         scala.Predef..MODULE$.require(localizedPath != null, () -> "Keytab file already distributed.");
      } else {
         throw new MatchError(var11);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareLocalResources$19(final FileStatus x$11) {
      return x$11.isFile();
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareLocalResources$22(final ZipOutputStream jarsStream$1, final File f) {
      if (f.isFile() && f.getName().toLowerCase(Locale.ROOT).endsWith(".jar") && f.canRead()) {
         jarsStream$1.putNextEntry(new ZipEntry(f.getName()));
         Files.copy(f.toPath(), jarsStream$1);
         jarsStream$1.closeEntry();
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareLocalResources$23(final String x$12) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$12.trim()));
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareLocalResources$26(final Client $this, final ListBuffer cachedSecondaryJarLinks$1, final Path destDir$1, final Option replication$1, final scala.collection.mutable.Map symlinkCache$1, final HashMap localResources$1, final HashMap statCache$2, final HashSet distributedUris$1, final HashSet distributedNames$1, final Tuple3 x0$1) {
      if (x0$1 != null) {
         Seq flist = (Seq)x0$1._1();
         LocalResourceType resType = (LocalResourceType)x0$1._2();
         boolean addToClasspath = BoxesRunTime.unboxToBoolean(x0$1._3());
         flist.foreach((file) -> {
            Tuple2 var14 = $this.distribute$1(file, resType, distribute$default$3$1(), distribute$default$4$1(), distribute$default$5$1(), destDir$1, replication$1, symlinkCache$1, localResources$1, statCache$2, distributedUris$1, distributedNames$1);
            if (var14 != null) {
               String localizedPath = (String)var14._2();
               if (addToClasspath) {
                  return localizedPath != null ? cachedSecondaryJarLinks$1.$plus$eq(localizedPath) : BoxedUnit.UNIT;
               } else if (localizedPath == null) {
                  throw new IllegalArgumentException("Attempt to add (" + file + ") multiple times to the distributed cache.");
               } else {
                  return BoxedUnit.UNIT;
               }
            } else {
               throw new MatchError(var14);
            }
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createConfArchive$2(final HashMap hadoopConfFiles$1, final File f) {
      hadoopConfFiles$1.update(f.getName(), f);
   }

   // $FF: synthetic method
   public static final void $anonfun$createConfArchive$1(final HashMap hadoopConfFiles$1, final String localConfDir) {
      File dir = new File(localConfDir);
      if (dir.isDirectory()) {
         File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(final File pathname) {
               return pathname.isFile() && pathname.getName().endsWith(".xml");
            }
         });
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])files), (f) -> {
            $anonfun$createConfArchive$2(hadoopConfFiles$1, f);
            return BoxedUnit.UNIT;
         });
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createConfArchive$6(final HashMap hadoopConfFiles$1, final File file) {
      if (file.isFile() && !hadoopConfFiles$1.contains(file.getName())) {
         hadoopConfFiles$1.update(file.getName(), file);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createConfArchive$4(final Client $this, final HashMap hadoopConfFiles$1, final String path) {
      File dir = new File(path);
      if (dir.isDirectory()) {
         File[] files = dir.listFiles();
         if (files == null) {
            $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to list files under directory ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, dir)})))));
         } else {
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])files), (file) -> {
               $anonfun$createConfArchive$6(hadoopConfFiles$1, file);
               return BoxedUnit.UNIT;
            });
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createConfArchive$3(final Client $this, final HashMap hadoopConfFiles$1, final String envKey) {
      scala.sys.package..MODULE$.env().get(envKey).foreach((path) -> {
         $anonfun$createConfArchive$4($this, hadoopConfFiles$1, path);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createConfArchive$9(final URL url) {
      boolean var2;
      label23: {
         String var10000 = url.getProtocol();
         String var1 = "file";
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final void $anonfun$createConfArchive$10(final ZipOutputStream confStream$1, final URL url) {
      File file = new File(url.getPath());
      confStream$1.putNextEntry(new ZipEntry(file.getName()));
      Files.copy(file.toPath(), confStream$1);
      confStream$1.closeEntry();
   }

   // $FF: synthetic method
   public static final void $anonfun$createConfArchive$8(final ZipOutputStream confStream$1, final String prop) {
      scala.Option..MODULE$.apply(org.apache.spark.util.Utils..MODULE$.getContextOrSparkClassLoader().getResource(prop)).withFilter((url) -> BoxesRunTime.boxToBoolean($anonfun$createConfArchive$9(url))).foreach((url) -> {
         $anonfun$createConfArchive$10(confStream$1, url);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$createConfArchive$11(final ZipOutputStream confStream$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         File file = (File)x0$1._2();
         if (file.canRead()) {
            String var10003 = Client$.MODULE$.LOCALIZED_HADOOP_CONF_DIR();
            confStream$1.putNextEntry(new ZipEntry(var10003 + "/" + name));
            Files.copy(file.toPath(), confStream$1);
            confStream$1.closeEntry();
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$setupLaunchEnv$2(final String amEnvPrefix$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith(amEnvPrefix$1);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$setupLaunchEnv$4(final HashMap env$1, final Tuple2 x0$3) {
      if (x0$3 != null) {
         String k = (String)x0$3._1();
         String v = (String)x0$3._2();
         YarnSparkHadoopUtil$.MODULE$.addPathToEnvironment(env$1, k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$3);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$setupLaunchEnv$5(final String x$14) {
      return x$14.endsWith(".py");
   }

   // $FF: synthetic method
   public static final void $anonfun$setupLaunchEnv$8(final HashMap env$1, final String envname$1, final String x$16) {
      env$1.update(envname$1, x$16);
   }

   // $FF: synthetic method
   public static final void $anonfun$setupLaunchEnv$7(final HashMap env$1, final String envname) {
      if (!env$1.contains(envname)) {
         scala.sys.package..MODULE$.env().get(envname).foreach((x$16) -> {
            $anonfun$setupLaunchEnv$8(env$1, envname, x$16);
            return BoxedUnit.UNIT;
         });
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$setupLaunchEnv$11(final HashMap env$1, final String envVar$1, final String value) {
      env$1.update(envVar$1, value);
   }

   // $FF: synthetic method
   public static final void $anonfun$setupLaunchEnv$10(final HashMap env$1, final String envVar) {
      scala.sys.package..MODULE$.env().get(envVar).foreach((value) -> {
         $anonfun$setupLaunchEnv$11(env$1, envVar, value);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$createContainerLaunchContext$9(final Client $this, final ObjectRef prefixEnv$1, final String paths) {
      prefixEnv$1.elem = new Some(Client$.MODULE$.createLibraryPathPrefix(paths, $this.sparkConf()));
   }

   // $FF: synthetic method
   public static final void $anonfun$createContainerLaunchContext$17(final Client $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         $this.logDebug((Function0)(() -> "        " + k + " -> " + v));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$createContainerLaunchContext$20(final Client $this, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String k = (String)x0$2._1();
         LocalResource v = (LocalResource)x0$2._2();
         $this.logDebug((Function0)(() -> "        " + k + " -> " + v));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$formatReportDetails$3(final String x$20) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$20));
   }

   // $FF: synthetic method
   public static final void $anonfun$run$3(final Client $this, final String err) {
      $this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application diagnostics message: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, err)})))));
   }

   public Client(final ClientArguments args, final SparkConf sparkConf, final RpcEnv rpcEnv) {
      boolean var7;
      label98: {
         label97: {
            this.args = args;
            this.sparkConf = sparkConf;
            this.rpcEnv = rpcEnv;
            super();
            Logging.$init$(this);
            this.org$apache$spark$deploy$yarn$Client$$yarnClient = YarnClient.createYarnClient();
            this.hadoopConf = new YarnConfiguration(org.apache.spark.deploy.SparkHadoopUtil..MODULE$.newConfiguration(sparkConf));
            Object var10001 = sparkConf.get(org.apache.spark.internal.config.package..MODULE$.SUBMIT_DEPLOY_MODE());
            String var4 = "cluster";
            if (var10001 == null) {
               if (var4 == null) {
                  break label97;
               }
            } else if (var10001.equals(var4)) {
               break label97;
            }

            var7 = false;
            break label98;
         }

         var7 = true;
      }

      this.org$apache$spark$deploy$yarn$Client$$isClusterMode = var7;
      this.isClientUnmanagedAMEnabled = BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.YARN_UNMANAGED_AM())) && !this.org$apache$spark$deploy$yarn$Client$$isClusterMode();
      this.statCachePreloadEnabled = BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.YARN_CLIENT_STAT_CACHE_PRELOAD_ENABLED()));
      this.statCachePreloadDirectoryCountThreshold = BoxesRunTime.unboxToInt(sparkConf.get(package$.MODULE$.YARN_CLIENT_STAT_CACHE_PRELOAD_PER_DIRECTORY_THRESHOLD()));
      this.amMemoryOverheadFactor = this.org$apache$spark$deploy$yarn$Client$$isClusterMode() ? BoxesRunTime.unboxToDouble(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY_OVERHEAD_FACTOR())) : YarnSparkHadoopUtil$.MODULE$.AM_MEMORY_OVERHEAD_FACTOR();
      this.amMemory = this.org$apache$spark$deploy$yarn$Client$$isClusterMode() ? (int)BoxesRunTime.unboxToLong(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY())) : (int)BoxesRunTime.unboxToLong(sparkConf.get(package$.MODULE$.AM_MEMORY()));
      this.driverMinimumMemoryOverhead = this.org$apache$spark$deploy$yarn$Client$$isClusterMode() ? BoxesRunTime.unboxToLong(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_MIN_MEMORY_OVERHEAD())) : 384L;
      OptionalConfigEntry amMemoryOverheadEntry = this.org$apache$spark$deploy$yarn$Client$$isClusterMode() ? org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY_OVERHEAD() : package$.MODULE$.AM_MEMORY_OVERHEAD();
      this.amMemoryOverhead = (int)BoxesRunTime.unboxToLong(((Option)sparkConf.get(amMemoryOverheadEntry)).getOrElse((JFunction0.mcJ.sp)() -> scala.math.package..MODULE$.max((long)(this.amMemoryOverheadFactor() * (double)this.amMemory()), this.driverMinimumMemoryOverhead())));
      this.amCores = this.org$apache$spark$deploy$yarn$Client$$isClusterMode() ? BoxesRunTime.unboxToInt(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_CORES())) : BoxesRunTime.unboxToInt(sparkConf.get(package$.MODULE$.AM_CORES()));
      this.executorMemory = BoxesRunTime.unboxToLong(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY()));
      this.executorOffHeapMemory = org.apache.spark.util.Utils..MODULE$.executorOffHeapMemorySizeAsMb(sparkConf);
      this.executorMemoryOvereadFactor = BoxesRunTime.unboxToDouble(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY_OVERHEAD_FACTOR()));
      this.minMemoryOverhead = BoxesRunTime.unboxToLong(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MIN_MEMORY_OVERHEAD()));
      this.executorMemoryOverhead = (int)BoxesRunTime.unboxToLong(((Option)sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY_OVERHEAD())).getOrElse((JFunction0.mcJ.sp)() -> scala.math.package..MODULE$.max((long)(this.executorMemoryOvereadFactor() * (double)this.executorMemory()), this.minMemoryOverhead())));
      this.isPython = BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.IS_PYTHON_APP()));
      this.pysparkWorkerMemory = this.isPython() ? BoxesRunTime.unboxToInt(((Option)sparkConf.get(org.apache.spark.internal.config.Python..MODULE$.PYSPARK_EXECUTOR_MEMORY())).map((JFunction1.mcIJ.sp)(x$3) -> (int)x$3).getOrElse((JFunction0.mcI.sp)() -> 0)) : 0;
      this.distCacheMgr = new ClientDistributedCacheManager();
      this.org$apache$spark$deploy$yarn$Client$$cachedResourcesConf = new SparkConf(false);
      this.keytab = (String)((Option)sparkConf.get(org.apache.spark.internal.config.package..MODULE$.KEYTAB())).orNull(scala..less.colon.less..MODULE$.refl());
      Object var8;
      if (this.keytab() != null && this.org$apache$spark$deploy$yarn$Client$$isClusterMode()) {
         String principal = (String)((Option)sparkConf.get(org.apache.spark.internal.config.package..MODULE$.PRINCIPAL())).orNull(scala..less.colon.less..MODULE$.refl());
         scala.Predef..MODULE$.require(principal == null == (this.keytab() == null), () -> "Both principal and keytab must be defined, or neither.");
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Kerberos credentials: principal = ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PRINCIPAL..MODULE$, principal)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"keytab = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.KEYTAB..MODULE$, this.keytab())}))))));
         String var10003 = (new File(this.keytab())).getName();
         var8 = new Some(var10003 + "-" + UUID.randomUUID().toString());
      } else {
         var8 = scala.None..MODULE$;
      }

      this.amKeytabFileName = (Option)var8;
      scala.Predef..MODULE$.require(this.keytab() == null || !org.apache.spark.util.Utils..MODULE$.isLocalUri(this.keytab()), () -> "Keytab should reference a local file.");
      this.launcherBackend = new LauncherBackend() {
         // $FF: synthetic field
         private final Client $outer;

         public SparkConf conf() {
            return this.$outer.sparkConf();
         }

         public void onStopRequest() {
            if (this.$outer.org$apache$spark$deploy$yarn$Client$$isClusterMode() && this.$outer.org$apache$spark$deploy$yarn$Client$$appId() != null) {
               this.$outer.org$apache$spark$deploy$yarn$Client$$yarnClient().killApplication(this.$outer.org$apache$spark$deploy$yarn$Client$$appId());
            } else {
               this.setState(State.KILLED);
               this.$outer.stop();
            }
         }

         public {
            if (Client.this == null) {
               throw null;
            } else {
               this.$outer = Client.this;
            }
         }
      };
      this.fireAndForget = this.org$apache$spark$deploy$yarn$Client$$isClusterMode() && !BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.WAIT_FOR_APP_COMPLETION()));
      this.org$apache$spark$deploy$yarn$Client$$appId = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
