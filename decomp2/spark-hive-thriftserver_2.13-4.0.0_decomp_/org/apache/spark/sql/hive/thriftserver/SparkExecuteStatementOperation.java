package org.apache.spark.sql.hive.thriftserver;

import java.lang.invoke.SerializedLambda;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.hive.shims.Utils;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hive.service.cli.FetchOrientation;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.cli.OperationState;
import org.apache.hive.service.cli.operation.ExecuteStatementOperation;
import org.apache.hive.service.cli.session.HiveSession;
import org.apache.hive.service.rpc.thrift.TRowSet;
import org.apache.hive.service.rpc.thrift.TTableSchema;
import org.apache.hive.service.rpc.thrift.TTypeId;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.internal.NonClosableMutableURLClassLoader;
import org.apache.spark.sql.internal.VariableSubstitution;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t-f!\u0002\u001a4\u0001Uz\u0004\u0002C+\u0001\u0005\u000b\u0007I\u0011A,\t\u0011q\u0003!\u0011!Q\u0001\naC\u0001\"\u0018\u0001\u0003\u0002\u0003\u0006IA\u0018\u0005\tG\u0002\u0011\t\u0011)A\u0005I\"A\u0011\u000f\u0001B\u0001B\u0003%!\u000f\u0003\u0005{\u0001\t\u0005\t\u0015!\u0003|\u0011%y\bA!A!\u0002\u0013\t\t\u0001C\u0004\u0002\b\u0001!\t!!\u0003\t\u0013\u0005e\u0001A1A\u0005\n\u0005m\u0001\u0002CA\u000f\u0001\u0001\u0006I!!\u0001\t\u0017\u0005}\u0001\u00011AA\u0002\u0013%\u0011\u0011\u0005\u0005\f\u0003_\u0001\u0001\u0019!a\u0001\n\u0013\t\t\u0004C\u0006\u0002>\u0001\u0001\r\u0011!Q!\n\u0005\r\u0002\"CA \u0001\t\u0007I\u0011BA!\u0011\u001d\t\u0019\u0005\u0001Q\u0001\nmD\u0011\"!\u0012\u0001\u0005\u0004%I!a\u0012\t\u000f\u0005%\u0003\u0001)A\u0005I\"Y\u00111\n\u0001A\u0002\u0003\u0007I\u0011BA'\u0011-\ti\u0007\u0001a\u0001\u0002\u0004%I!a\u001c\t\u0017\u0005M\u0004\u00011A\u0001B\u0003&\u0011q\n\u0005\f\u0003k\u0002\u0001\u0019!a\u0001\n\u0013\t9\bC\u0006\u0002\u0006\u0002\u0001\r\u00111A\u0005\n\u0005\u001d\u0005bCAF\u0001\u0001\u0007\t\u0011)Q\u0005\u0003sB1\"!$\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002\u0010\"Y\u00111\u0015\u0001A\u0002\u0003\u0007I\u0011BAS\u0011-\tI\u000b\u0001a\u0001\u0002\u0003\u0006K!!%\t\u0015\u0005-\u0006\u0001#b\u0001\n\u0013\ti\u000bC\u0004\u0002@\u0002!\t!!1\t\u000f\u0005e\u0007\u0001\"\u0003\u0002\\\"9\u0011\u0011\u001d\u0001\u0005\u0002\u0005\r\bbBAs\u0001\u0011\u0005\u0013q\u001d\u0005\b\u0003S\u0004A\u0011BAt\u0011\u001d\tY\u000f\u0001C\u0001\u0003ODq!!<\u0001\t\u0003\n9\u000fC\u0004\u0002p\u0002!\t&a:\t\u0019\u0005E\b\u0001%A\u0001\u0002\u0003%\t!a=\t\u0019\u0005u\b\u0001%A\u0001\u0002\u0003%\t!a@\t\u0019\t=\u0001\u0001%A\u0001\u0002\u0003%\tA!\u0005\t\u0019\tm\u0001\u0001%A\u0001\u0002\u0003%\tA!\b\t\u0019\t5\u0002\u0001%A\u0001\u0002\u0003%\tAa\f\b\u000f\tM2\u0007#\u0001\u00036\u00191!g\rE\u0001\u0005oAq!a\u0002+\t\u0003\u0011y\u0004C\u0004\u0003B)\"\tAa\u0011\t\u000f\t=#\u0006\"\u0003\u0003R!9!1\f\u0016\u0005\n\tu\u0003b\u0002B4U\u0011%!\u0011\u000e\u0005\b\u0005\u000bSC\u0011\u0001BD\u0011%\u0011\u0019JKI\u0001\n\u0003\u0011)J\u0001\u0010Ta\u0006\u00148.\u0012=fGV$Xm\u0015;bi\u0016lWM\u001c;Pa\u0016\u0014\u0018\r^5p]*\u0011A'N\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003m]\nA\u0001[5wK*\u0011\u0001(O\u0001\u0004gFd'B\u0001\u001e<\u0003\u0015\u0019\b/\u0019:l\u0015\taT(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002}\u0005\u0019qN]4\u0014\t\u0001\u00015j\u0014\t\u0003\u0003&k\u0011A\u0011\u0006\u0003\u0007\u0012\u000b\u0011b\u001c9fe\u0006$\u0018n\u001c8\u000b\u0005\u00153\u0015aA2mS*\u0011q\tS\u0001\bg\u0016\u0014h/[2f\u0015\t14(\u0003\u0002K\u0005\nIR\t_3dkR,7\u000b^1uK6,g\u000e^(qKJ\fG/[8o!\taU*D\u00014\u0013\tq5G\u0001\bTa\u0006\u00148n\u00149fe\u0006$\u0018n\u001c8\u0011\u0005A\u001bV\"A)\u000b\u0005IK\u0014\u0001C5oi\u0016\u0014h.\u00197\n\u0005Q\u000b&a\u0002'pO\u001eLgnZ\u0001\bg\u0016\u001c8/[8o\u0007\u0001)\u0012\u0001\u0017\t\u00033jk\u0011aN\u0005\u00037^\u0012Ab\u00159be.\u001cVm]:j_:\f\u0001b]3tg&|g\u000eI\u0001\u000ea\u0006\u0014XM\u001c;TKN\u001c\u0018n\u001c8\u0011\u0005}\u000bW\"\u00011\u000b\u0005U#\u0015B\u00012a\u0005-A\u0015N^3TKN\u001c\u0018n\u001c8\u0002\u0013M$\u0018\r^3nK:$\bCA3o\u001d\t1G\u000e\u0005\u0002hU6\t\u0001N\u0003\u0002j-\u00061AH]8pizR\u0011a[\u0001\u0006g\u000e\fG.Y\u0005\u0003[*\fa\u0001\u0015:fI\u00164\u0017BA8q\u0005\u0019\u0019FO]5oO*\u0011QN[\u0001\fG>tgm\u0014<fe2\f\u0017\u0010\u0005\u0003tq\u0012$W\"\u0001;\u000b\u0005U4\u0018\u0001B;uS2T\u0011a^\u0001\u0005U\u00064\u0018-\u0003\u0002zi\n\u0019Q*\u00199\u0002\u001fI,h.\u00138CC\u000e\\wM]8v]\u0012\u0004\"\u0001`?\u000e\u0003)L!A 6\u0003\u000f\t{w\u000e\\3b]\u0006a\u0011/^3ssRKW.Z8viB\u0019A0a\u0001\n\u0007\u0005\u0015!N\u0001\u0003M_:<\u0017A\u0002\u001fj]&$h\b\u0006\b\u0002\f\u00055\u0011qBA\t\u0003'\t)\"a\u0006\u0011\u00051\u0003\u0001\"B+\t\u0001\u0004A\u0006\"B/\t\u0001\u0004q\u0006\"B2\t\u0001\u0004!\u0007\"B9\t\u0001\u0004\u0011\bb\u0002>\t!\u0003\u0005\ra\u001f\u0005\u0007\u007f\"\u0001\r!!\u0001\u0002\u000fQLW.Z8viV\u0011\u0011\u0011A\u0001\ti&lWm\\;uA\u0005yA/[7f_V$X\t_3dkR|'/\u0006\u0002\u0002$A!\u0011QEA\u0016\u001b\t\t9CC\u0002\u0002*Q\f!bY8oGV\u0014(/\u001a8u\u0013\u0011\ti#a\n\u00031M\u001b\u0007.\u001a3vY\u0016$W\t_3dkR|'oU3sm&\u001cW-A\nuS6,w.\u001e;Fq\u0016\u001cW\u000f^8s?\u0012*\u0017\u000f\u0006\u0003\u00024\u0005e\u0002c\u0001?\u00026%\u0019\u0011q\u00076\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003wa\u0011\u0011!a\u0001\u0003G\t1\u0001\u001f\u00132\u0003A!\u0018.\\3pkR,\u00050Z2vi>\u0014\b%A\u0006g_J\u001cWmQ1oG\u0016dW#A>\u0002\u0019\u0019|'oY3DC:\u001cW\r\u001c\u0011\u0002#I,G-Y2uK\u0012\u001cF/\u0019;f[\u0016tG/F\u0001e\u0003I\u0011X\rZ1di\u0016$7\u000b^1uK6,g\u000e\u001e\u0011\u0002\rI,7/\u001e7u+\t\ty\u0005\u0005\u0003\u0002R\u0005\u001dd\u0002BA*\u0003GrA!!\u0016\u0002b9!\u0011qKA0\u001d\u0011\tI&!\u0018\u000f\u0007\u001d\fY&C\u0001?\u0013\taT(\u0003\u0002;w%\u0011\u0001(O\u0005\u0004\u0003K:\u0014a\u00029bG.\fw-Z\u0005\u0005\u0003S\nYGA\u0005ECR\fgI]1nK*\u0019\u0011QM\u001c\u0002\u0015I,7/\u001e7u?\u0012*\u0017\u000f\u0006\u0003\u00024\u0005E\u0004\"CA\u001e'\u0005\u0005\t\u0019AA(\u0003\u001d\u0011Xm];mi\u0002\nA!\u001b;feV\u0011\u0011\u0011\u0010\t\u0006\u0019\u0006m\u0014qP\u0005\u0004\u0003{\u001a$!\u0004$fi\u000eD\u0017\n^3sCR|'\u000fE\u0002Z\u0003\u0003K1!a!8\u0005\r\u0011vn^\u0001\tSR,'o\u0018\u0013fcR!\u00111GAE\u0011%\tYDFA\u0001\u0002\u0004\tI(A\u0003ji\u0016\u0014\b%A\u0005eCR\fG+\u001f9fgV\u0011\u0011\u0011\u0013\t\u0006y\u0006M\u0015qS\u0005\u0004\u0003+S'!B!se\u0006L\b\u0003BAM\u0003?k!!a'\u000b\u0007\u0005uu'A\u0003usB,7/\u0003\u0003\u0002\"\u0006m%\u0001\u0003#bi\u0006$\u0016\u0010]3\u0002\u001b\u0011\fG/\u0019+za\u0016\u001cx\fJ3r)\u0011\t\u0019$a*\t\u0013\u0005m\u0012$!AA\u0002\u0005E\u0015A\u00033bi\u0006$\u0016\u0010]3tA\u0005a!/Z:vYR\u001c6\r[3nCV\u0011\u0011q\u0016\t\u0005\u0003c\u000bY,\u0004\u0002\u00024*!\u0011QWA\\\u0003\u0019!\bN]5gi*\u0019\u0011\u0011\u0018$\u0002\u0007I\u00048-\u0003\u0003\u0002>\u0006M&\u0001\u0004+UC\ndWmU2iK6\f\u0017!D4fi:+\u0007\u0010\u001e*poN+G\u000f\u0006\u0004\u0002D\u0006%\u0017Q\u001b\t\u0005\u0003c\u000b)-\u0003\u0003\u0002H\u0006M&a\u0002+S_^\u001cV\r\u001e\u0005\b\u0003\u0017d\u0002\u0019AAg\u0003\u0015y'\u000fZ3s!\u0011\ty-!5\u000e\u0003\u0011K1!a5E\u0005A1U\r^2i\u001fJLWM\u001c;bi&|g\u000eC\u0004\u0002Xr\u0001\r!!\u0001\u0002\u00115\f\u0007PU8xg2\u000bQcZ3u\u001d\u0016DHOU8x'\u0016$\u0018J\u001c;fe:\fG\u000e\u0006\u0004\u0002D\u0006u\u0017q\u001c\u0005\b\u0003\u0017l\u0002\u0019AAg\u0011\u001d\t9.\ba\u0001\u0003\u0003\t!cZ3u%\u0016\u001cX\u000f\u001c;TKR\u001c6\r[3nCR\u0011\u0011qV\u0001\feVt\u0017J\u001c;fe:\fG\u000e\u0006\u0002\u00024\u00059Q\r_3dkR,\u0017!\u0004;j[\u0016|W\u000f^\"b]\u000e,G.\u0001\u0004dC:\u001cW\r\\\u0001\bG2,\u0017M\\;q\u0003%\u0002(o\u001c;fGR,G\r\n<bY&$\u0017\r^3EK\u001a\fW\u000f\u001c;GKR\u001c\u0007n\u0014:jK:$\u0018\r^5p]R!\u0011Q_A~)\u0011\t\u0019$a>\t\u000f\u0005eH\u00051\u0001\u0002N\u0006YqN]5f]R\fG/[8o\u0011%\tY\u0004JA\u0001\u0002\u0004\tY!A\u000bqe>$Xm\u0019;fI\u0012\n7o]3siN#\u0018\r^3\u0015\t\t\u0005!Q\u0002\u000b\u0005\u0003g\u0011\u0019\u0001C\u0004\u0003\u0006\u0015\u0002\rAa\u0002\u0002\u000bM$\u0018\r^3\u0011\t\u0005='\u0011B\u0005\u0004\u0005\u0017!%AD(qKJ\fG/[8o'R\fG/\u001a\u0005\n\u0003w)\u0013\u0011!a\u0001\u0003\u0017\t\u0011\u0004\u001d:pi\u0016\u001cG/\u001a3%g\u0016$\b*Y:SKN,H\u000e^*fiR!!1\u0003B\r)\u0011\t\u0019D!\u0006\t\r\t]a\u00051\u0001|\u00031A\u0017m\u001d*fgVdGoU3u\u0011%\tYDJA\u0001\u0002\u0004\tY!A\u0010qe>$Xm\u0019;fI\u0012\u001aX\r^(qKJ\fG/[8o\u000bb\u001cW\r\u001d;j_:$BAa\b\u0003,Q!\u00111\u0007B\u0011\u0011\u001d\u0011\u0019c\na\u0001\u0005K\t!c\u001c9fe\u0006$\u0018n\u001c8Fq\u000e,\u0007\u000f^5p]B!\u0011q\u001aB\u0014\u0013\r\u0011I\u0003\u0012\u0002\u0011\u0011&4XmU)M\u000bb\u001cW\r\u001d;j_:D\u0011\"a\u000f(\u0003\u0003\u0005\r!a\u0003\u0002KA\u0014x\u000e^3di\u0016$GE]3hSN$XM]\"veJ,g\u000e^(qKJ\fG/[8o\u0019><G\u0003BAt\u0005cA\u0011\"a\u000f)\u0003\u0003\u0005\r!a\u0003\u0002=M\u0003\u0018M]6Fq\u0016\u001cW\u000f^3Ti\u0006$X-\\3oi>\u0003XM]1uS>t\u0007C\u0001'+'\rQ#\u0011\b\t\u0004y\nm\u0012b\u0001B\u001fU\n1\u0011I\\=SK\u001a$\"A!\u000e\u0002\u0013Q|G\u000bV=qK&#G\u0003\u0002B#\u0005\u0017\u0002B!!-\u0003H%!!\u0011JAZ\u0005\u001d!F+\u001f9f\u0013\u0012DqA!\u0014-\u0001\u0004\t9*A\u0002usB\f\u0011\u0003^8U)f\u0004X-U;bY&4\u0017.\u001a:t)\u0011\u0011\u0019F!\u0017\u0011\t\u0005E&QK\u0005\u0005\u0005/\n\u0019LA\bU)f\u0004X-U;bY&4\u0017.\u001a:t\u0011\u001d\u0011i%\fa\u0001\u0003/\u000b1\u0002^8U)f\u0004X\rR3tGR!!q\fB3!\u0011\t\tL!\u0019\n\t\t\r\u00141\u0017\u0002\n)RK\b/\u001a#fg\u000eDqA!\u0014/\u0001\u0004\t9*A\u0007u_R\u001bu\u000e\\;n]\u0012+7o\u0019\u000b\u0007\u0005W\u0012\tHa\u001f\u0011\t\u0005E&QN\u0005\u0005\u0005_\n\u0019LA\u0006U\u0007>dW/\u001c8EKN\u001c\u0007b\u0002B:_\u0001\u0007!QO\u0001\u0006M&,G\u000e\u001a\t\u0005\u00033\u00139(\u0003\u0003\u0003z\u0005m%aC*ueV\u001cGOR5fY\u0012DqA! 0\u0001\u0004\u0011y(A\u0002q_N\u00042\u0001 BA\u0013\r\u0011\u0019I\u001b\u0002\u0004\u0013:$\u0018A\u0004;p)R\u000b'\r\\3TG\",W.\u0019\u000b\u0005\u0003_\u0013I\tC\u0004\u0003\fB\u0002\rA!$\u0002\rM\u001c\u0007.Z7b!\u0011\tIJa$\n\t\tE\u00151\u0014\u0002\u000b'R\u0014Xo\u0019;UsB,\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S'\u0006\u0002\u0003\u0018*\u001a1P!',\u0005\tm\u0005\u0003\u0002BO\u0005Ok!Aa(\u000b\t\t\u0005&1U\u0001\nk:\u001c\u0007.Z2lK\u0012T1A!*k\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005S\u0013yJA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u0004"
)
public class SparkExecuteStatementOperation extends ExecuteStatementOperation implements SparkOperation {
   private TTableSchema resultSchema;
   private final SparkSession session;
   private final HiveSession parentSession;
   private final String statement;
   private final boolean runInBackground;
   private final long org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeout;
   private ScheduledExecutorService org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeoutExecutor;
   private final boolean forceCancel;
   private final String redactedStatement;
   private Dataset org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result;
   private FetchIterator iter;
   private DataType[] dataTypes;
   private String statementId;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static boolean $lessinit$greater$default$5() {
      return SparkExecuteStatementOperation$.MODULE$.$lessinit$greater$default$5();
   }

   public static TTableSchema toTTableSchema(final StructType schema) {
      return SparkExecuteStatementOperation$.MODULE$.toTTableSchema(schema);
   }

   public static TTypeId toTTypeId(final DataType typ) {
      return SparkExecuteStatementOperation$.MODULE$.toTTypeId(typ);
   }

   // $FF: synthetic method
   public void org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$run() {
      super.run();
   }

   // $FF: synthetic method
   public void org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$close() {
      super.close();
   }

   // $FF: synthetic method
   public OperationState org$apache$spark$sql$hive$thriftserver$SparkOperation$$super$setState(final OperationState newState) {
      return super.setState(newState);
   }

   public void run() {
      SparkOperation.run$(this);
   }

   public void close() {
      SparkOperation.close$(this);
   }

   public Object withLocalProperties(final Function0 f) {
      return SparkOperation.withLocalProperties$(this, f);
   }

   public String tableTypeString(final CatalogTableType tableType) {
      return SparkOperation.tableTypeString$(this, tableType);
   }

   public PartialFunction onError() {
      return SparkOperation.onError$(this);
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

   public String statementId() {
      return this.statementId;
   }

   public void statementId_$eq(final String x$1) {
      this.statementId = x$1;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   // $FF: synthetic method
   public void protected$validateDefaultFetchOrientation(final SparkExecuteStatementOperation x$1, final FetchOrientation orientation) {
      x$1.validateDefaultFetchOrientation(orientation);
   }

   // $FF: synthetic method
   public void protected$assertState(final SparkExecuteStatementOperation x$1, final OperationState state) {
      x$1.assertState(state);
   }

   // $FF: synthetic method
   public void protected$setHasResultSet(final SparkExecuteStatementOperation x$1, final boolean hasResultSet) {
      x$1.setHasResultSet(hasResultSet);
   }

   // $FF: synthetic method
   public void protected$setOperationException(final SparkExecuteStatementOperation x$1, final HiveSQLException operationException) {
      x$1.setOperationException(operationException);
   }

   // $FF: synthetic method
   public void protected$registerCurrentOperationLog(final SparkExecuteStatementOperation x$1) {
      x$1.registerCurrentOperationLog();
   }

   public SparkSession session() {
      return this.session;
   }

   public long org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeout() {
      return this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeout;
   }

   public ScheduledExecutorService org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeoutExecutor() {
      return this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeoutExecutor;
   }

   private void timeoutExecutor_$eq(final ScheduledExecutorService x$1) {
      this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeoutExecutor = x$1;
   }

   private boolean forceCancel() {
      return this.forceCancel;
   }

   private String redactedStatement() {
      return this.redactedStatement;
   }

   public Dataset org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result() {
      return this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result;
   }

   private void result_$eq(final Dataset x$1) {
      this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result = x$1;
   }

   private FetchIterator iter() {
      return this.iter;
   }

   private void iter_$eq(final FetchIterator x$1) {
      this.iter = x$1;
   }

   private DataType[] dataTypes() {
      return this.dataTypes;
   }

   private void dataTypes_$eq(final DataType[] x$1) {
      this.dataTypes = x$1;
   }

   private TTableSchema resultSchema$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            TTableSchema var10001;
            if (this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result() != null && !this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result().schema().isEmpty()) {
               this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Result Schema: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCHEMA..MODULE$, this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result().schema().sql())})))));
               var10001 = SparkExecuteStatementOperation$.MODULE$.toTTableSchema(this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result().schema());
            } else {
               StructType sparkType = (new StructType()).add("Result", "string");
               var10001 = SparkExecuteStatementOperation$.MODULE$.toTTableSchema(sparkType);
            }

            this.resultSchema = var10001;
            this.bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.resultSchema;
   }

   private TTableSchema resultSchema() {
      return !this.bitmap$0 ? this.resultSchema$lzycompute() : this.resultSchema;
   }

   public TRowSet getNextRowSet(final FetchOrientation order, final long maxRowsL) {
      return (TRowSet)this.withLocalProperties(() -> {
         TRowSet var10000;
         try {
            this.session().sparkContext().setJobGroup(this.statementId(), this.redactedStatement(), this.forceCancel());
            var10000 = this.getNextRowSetInternal(order, maxRowsL);
         } finally {
            this.session().sparkContext().clearJobGroup();
         }

         return var10000;
      });
   }

   private TRowSet getNextRowSetInternal(final FetchOrientation order, final long maxRowsL) {
      return (TRowSet)this.withLocalProperties(() -> {
         this.log().debug("Received getNextRowSet request order=" + order + " and maxRowsL=" + maxRowsL + " with " + this.statementId());
         this.protected$validateDefaultFetchOrientation(this, order);
         this.protected$assertState(this, OperationState.FINISHED);
         this.protected$setHasResultSet(this, true);
         if (order.equals(FetchOrientation.FETCH_FIRST)) {
            this.iter().fetchAbsolute(0L);
         } else if (order.equals(FetchOrientation.FETCH_PRIOR)) {
            this.iter().fetchPrior(maxRowsL);
         } else {
            this.iter().fetchNext();
         }

         int maxRows = (int)maxRowsL;
         long offset = this.iter().getPosition();
         List rows = this.iter().take(maxRows).toList();
         Logger var10000 = this.log();
         int var10001 = rows.length();
         var10000.debug("Returning result set with " + var10001 + " rows from offsets [" + this.iter().getFetchStart() + ", " + this.iter().getPosition() + ") with " + this.statementId());
         return RowSetUtils$.MODULE$.toTRowSet(offset, rows, this.dataTypes(), this.getProtocolVersion());
      });
   }

   public TTableSchema getResultSetSchema() {
      return this.resultSchema();
   }

   public void runInternal() {
      this.setState(OperationState.PENDING);
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting query '", "' with "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REDACTED_STATEMENT..MODULE$, this.redactedStatement())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())}))))));
      HiveThriftServer2$.MODULE$.eventManager().onStatementStart(this.statementId(), this.parentSession.getSessionHandle().getSessionId().toString(), this.redactedStatement(), this.statementId(), this.parentSession.getUsername());
      this.setHasResultSet(true);
      if (this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeout() > 0L) {
         this.timeoutExecutor_$eq(Executors.newSingleThreadScheduledExecutor());
         this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeoutExecutor().schedule(new Runnable() {
            // $FF: synthetic field
            private final SparkExecuteStatementOperation $outer;

            public void run() {
               try {
                  this.$outer.timeoutCancel();
               } catch (Throwable var10) {
                  if (var10 == null || !scala.util.control.NonFatal..MODULE$.apply(var10)) {
                     throw var10;
                  }

                  this.$outer.protected$setOperationException(this.$outer, new HiveSQLException(var10));
                  long timeout_ms = this.$outer.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeout() * 1000L;
                  this.$outer.logError(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error cancelling the query after timeout: ", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, BoxesRunTime.boxToLong(timeout_ms))})))));
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } finally {
                  this.$outer.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeoutExecutor().shutdown();
               }

            }

            public {
               if (SparkExecuteStatementOperation.this == null) {
                  throw null;
               } else {
                  this.$outer = SparkExecuteStatementOperation.this;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         }, this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeout(), TimeUnit.SECONDS);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (!this.runInBackground) {
         this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$execute();
      } else {
         UserGroupInformation sparkServiceUGI = Utils.getUGI();
         Runnable backgroundOperation = new Runnable(sparkServiceUGI) {
            // $FF: synthetic field
            private final SparkExecuteStatementOperation $outer;
            private final UserGroupInformation sparkServiceUGI$1;

            public void run() {
               PrivilegedExceptionAction doAsAction = new PrivilegedExceptionAction() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void run() {
                     this.$outer.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$anon$$$outer().protected$registerCurrentOperationLog(this.$outer.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$anon$$$outer());

                     try {
                        this.$outer.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$anon$$$outer().withLocalProperties((JFunction0.mcV.sp)() -> this.$outer.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$anon$$$outer().org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$execute());
                     } catch (HiveSQLException var2) {
                        this.$outer.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$anon$$$outer().protected$setOperationException(this.$outer.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$anon$$$outer(), var2);
                     }

                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };

               try {
                  this.sparkServiceUGI$1.doAs(doAsAction);
               } catch (Exception var3) {
                  this.$outer.protected$setOperationException(this.$outer, new HiveSQLException(var3));
                  this.$outer.logError((LogEntry).MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error running hive query as user : "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.USER_NAME..MODULE$, this.sparkServiceUGI$1.getShortUserName())}))))), var3);
               }

            }

            // $FF: synthetic method
            public SparkExecuteStatementOperation org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (SparkExecuteStatementOperation.this == null) {
                  throw null;
               } else {
                  this.$outer = SparkExecuteStatementOperation.this;
                  this.sparkServiceUGI$1 = sparkServiceUGI$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };

         try {
            Future backgroundHandle = this.parentSession.getSessionManager().submitBackgroundOperation(backgroundOperation);
            this.setBackgroundHandle(backgroundHandle);
         } catch (Throwable var9) {
            if (var9 instanceof RejectedExecutionException) {
               RejectedExecutionException var7 = (RejectedExecutionException)var9;
               this.logError((Function0)(() -> "Error submitting query in background, query rejected"), var7);
               this.setState(OperationState.ERROR);
               HiveThriftServer2$.MODULE$.eventManager().onStatementError(this.statementId(), var7.getMessage(), org.apache.spark.util.Utils..MODULE$.exceptionString(var7));
               throw HiveThriftServerErrors$.MODULE$.taskExecutionRejectedError(var7);
            } else if (var9 != null && scala.util.control.NonFatal..MODULE$.apply(var9)) {
               this.logError((Function0)(() -> "Error executing query in background"), var9);
               this.setState(OperationState.ERROR);
               HiveThriftServer2$.MODULE$.eventManager().onStatementError(this.statementId(), var9.getMessage(), org.apache.spark.util.Utils..MODULE$.exceptionString(var9));
               throw new HiveSQLException(var9);
            } else {
               throw var9;
            }
         }
      }
   }

   public void org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$execute() {
      try {
         try {
            synchronized(this){}

            try {
               if (this.getStatus().getState().isTerminal()) {
                  this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Query with ", " in terminal state "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"before it started running"})))).log(scala.collection.immutable.Nil..MODULE$))));
                  return;
               }

               this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Running query with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())})))));
               this.setState(OperationState.RUNNING);
            } catch (Throwable var40) {
               throw var40;
            }

            NonClosableMutableURLClassLoader executionHiveClassLoader = this.session().sharedState().jarClassLoader();
            Thread.currentThread().setContextClassLoader(executionHiveClassLoader);
            if (!this.runInBackground) {
               this.parentSession.getSessionState().getConf().setClassLoader(executionHiveClassLoader);
            }

            this.session().sparkContext().setJobGroup(this.statementId(), this.redactedStatement(), this.forceCancel());
            this.result_$eq(this.session().sql(this.statement));
            this.logDebug((Function0)(() -> this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result().queryExecution().toString()));
            HiveThriftServer2$.MODULE$.eventManager().onStatementParsed(this.statementId(), this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result().queryExecution().toString());
            this.iter_$eq((FetchIterator)(scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(this.session().conf().get(org.apache.spark.sql.internal.SQLConf..MODULE$.THRIFTSERVER_INCREMENTAL_COLLECT().key()))) ? new IterableFetchIterator(new Iterable() {
               // $FF: synthetic field
               private final SparkExecuteStatementOperation $outer;

               /** @deprecated */
               public final Iterable toIterable() {
                  return Iterable.toIterable$(this);
               }

               public final Iterable coll() {
                  return Iterable.coll$(this);
               }

               public IterableFactory iterableFactory() {
                  return Iterable.iterableFactory$(this);
               }

               /** @deprecated */
               public Iterable seq() {
                  return Iterable.seq$(this);
               }

               public String className() {
                  return Iterable.className$(this);
               }

               public final String collectionClassName() {
                  return Iterable.collectionClassName$(this);
               }

               public String stringPrefix() {
                  return Iterable.stringPrefix$(this);
               }

               public String toString() {
                  return Iterable.toString$(this);
               }

               public LazyZip2 lazyZip(final Iterable that) {
                  return Iterable.lazyZip$(this, that);
               }

               public IterableOps fromSpecific(final IterableOnce coll) {
                  return IterableFactoryDefaults.fromSpecific$(this, coll);
               }

               public Builder newSpecificBuilder() {
                  return IterableFactoryDefaults.newSpecificBuilder$(this);
               }

               public IterableOps empty() {
                  return IterableFactoryDefaults.empty$(this);
               }

               /** @deprecated */
               public final Iterable toTraversable() {
                  return IterableOps.toTraversable$(this);
               }

               public boolean isTraversableAgain() {
                  return IterableOps.isTraversableAgain$(this);
               }

               /** @deprecated */
               public final Object repr() {
                  return IterableOps.repr$(this);
               }

               /** @deprecated */
               public IterableFactory companion() {
                  return IterableOps.companion$(this);
               }

               public Object head() {
                  return IterableOps.head$(this);
               }

               public Option headOption() {
                  return IterableOps.headOption$(this);
               }

               public Object last() {
                  return IterableOps.last$(this);
               }

               public Option lastOption() {
                  return IterableOps.lastOption$(this);
               }

               public View view() {
                  return IterableOps.view$(this);
               }

               public int sizeCompare(final int otherSize) {
                  return IterableOps.sizeCompare$(this, otherSize);
               }

               public final IterableOps sizeIs() {
                  return IterableOps.sizeIs$(this);
               }

               public int sizeCompare(final Iterable that) {
                  return IterableOps.sizeCompare$(this, that);
               }

               /** @deprecated */
               public View view(final int from, final int until) {
                  return IterableOps.view$(this, from, until);
               }

               public Object transpose(final Function1 asIterable) {
                  return IterableOps.transpose$(this, asIterable);
               }

               public Object filter(final Function1 pred) {
                  return IterableOps.filter$(this, pred);
               }

               public Object filterNot(final Function1 pred) {
                  return IterableOps.filterNot$(this, pred);
               }

               public WithFilter withFilter(final Function1 p) {
                  return IterableOps.withFilter$(this, p);
               }

               public Tuple2 partition(final Function1 p) {
                  return IterableOps.partition$(this, p);
               }

               public Tuple2 splitAt(final int n) {
                  return IterableOps.splitAt$(this, n);
               }

               public Object take(final int n) {
                  return IterableOps.take$(this, n);
               }

               public Object takeRight(final int n) {
                  return IterableOps.takeRight$(this, n);
               }

               public Object takeWhile(final Function1 p) {
                  return IterableOps.takeWhile$(this, p);
               }

               public Tuple2 span(final Function1 p) {
                  return IterableOps.span$(this, p);
               }

               public Object drop(final int n) {
                  return IterableOps.drop$(this, n);
               }

               public Object dropRight(final int n) {
                  return IterableOps.dropRight$(this, n);
               }

               public Object dropWhile(final Function1 p) {
                  return IterableOps.dropWhile$(this, p);
               }

               public Iterator grouped(final int size) {
                  return IterableOps.grouped$(this, size);
               }

               public Iterator sliding(final int size) {
                  return IterableOps.sliding$(this, size);
               }

               public Iterator sliding(final int size, final int step) {
                  return IterableOps.sliding$(this, size, step);
               }

               public Object tail() {
                  return IterableOps.tail$(this);
               }

               public Object init() {
                  return IterableOps.init$(this);
               }

               public Object slice(final int from, final int until) {
                  return IterableOps.slice$(this, from, until);
               }

               public scala.collection.immutable.Map groupBy(final Function1 f) {
                  return IterableOps.groupBy$(this, f);
               }

               public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
                  return IterableOps.groupMap$(this, key, f);
               }

               public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
                  return IterableOps.groupMapReduce$(this, key, f, reduce);
               }

               public Object scan(final Object z, final Function2 op) {
                  return IterableOps.scan$(this, z, op);
               }

               public Object scanLeft(final Object z, final Function2 op) {
                  return IterableOps.scanLeft$(this, z, op);
               }

               public Object scanRight(final Object z, final Function2 op) {
                  return IterableOps.scanRight$(this, z, op);
               }

               public Object map(final Function1 f) {
                  return IterableOps.map$(this, f);
               }

               public Object flatMap(final Function1 f) {
                  return IterableOps.flatMap$(this, f);
               }

               public Object flatten(final Function1 asIterable) {
                  return IterableOps.flatten$(this, asIterable);
               }

               public Object collect(final PartialFunction pf) {
                  return IterableOps.collect$(this, pf);
               }

               public Tuple2 partitionMap(final Function1 f) {
                  return IterableOps.partitionMap$(this, f);
               }

               public Object concat(final IterableOnce suffix) {
                  return IterableOps.concat$(this, suffix);
               }

               public final Object $plus$plus(final IterableOnce suffix) {
                  return IterableOps.$plus$plus$(this, suffix);
               }

               public Object zip(final IterableOnce that) {
                  return IterableOps.zip$(this, that);
               }

               public Object zipWithIndex() {
                  return IterableOps.zipWithIndex$(this);
               }

               public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
                  return IterableOps.zipAll$(this, that, thisElem, thatElem);
               }

               public Tuple2 unzip(final Function1 asPair) {
                  return IterableOps.unzip$(this, asPair);
               }

               public Tuple3 unzip3(final Function1 asTriple) {
                  return IterableOps.unzip3$(this, asTriple);
               }

               public Iterator tails() {
                  return IterableOps.tails$(this);
               }

               public Iterator inits() {
                  return IterableOps.inits$(this);
               }

               public Object tapEach(final Function1 f) {
                  return IterableOps.tapEach$(this, f);
               }

               /** @deprecated */
               public Object $plus$plus$colon(final IterableOnce that) {
                  return IterableOps.$plus$plus$colon$(this, that);
               }

               /** @deprecated */
               public boolean hasDefiniteSize() {
                  return IterableOnceOps.hasDefiniteSize$(this);
               }

               public void foreach(final Function1 f) {
                  IterableOnceOps.foreach$(this, f);
               }

               public boolean forall(final Function1 p) {
                  return IterableOnceOps.forall$(this, p);
               }

               public boolean exists(final Function1 p) {
                  return IterableOnceOps.exists$(this, p);
               }

               public int count(final Function1 p) {
                  return IterableOnceOps.count$(this, p);
               }

               public Option find(final Function1 p) {
                  return IterableOnceOps.find$(this, p);
               }

               public Object foldLeft(final Object z, final Function2 op) {
                  return IterableOnceOps.foldLeft$(this, z, op);
               }

               public Object foldRight(final Object z, final Function2 op) {
                  return IterableOnceOps.foldRight$(this, z, op);
               }

               /** @deprecated */
               public final Object $div$colon(final Object z, final Function2 op) {
                  return IterableOnceOps.$div$colon$(this, z, op);
               }

               /** @deprecated */
               public final Object $colon$bslash(final Object z, final Function2 op) {
                  return IterableOnceOps.$colon$bslash$(this, z, op);
               }

               public Object fold(final Object z, final Function2 op) {
                  return IterableOnceOps.fold$(this, z, op);
               }

               public Object reduce(final Function2 op) {
                  return IterableOnceOps.reduce$(this, op);
               }

               public Option reduceOption(final Function2 op) {
                  return IterableOnceOps.reduceOption$(this, op);
               }

               public Object reduceLeft(final Function2 op) {
                  return IterableOnceOps.reduceLeft$(this, op);
               }

               public Object reduceRight(final Function2 op) {
                  return IterableOnceOps.reduceRight$(this, op);
               }

               public Option reduceLeftOption(final Function2 op) {
                  return IterableOnceOps.reduceLeftOption$(this, op);
               }

               public Option reduceRightOption(final Function2 op) {
                  return IterableOnceOps.reduceRightOption$(this, op);
               }

               public boolean isEmpty() {
                  return IterableOnceOps.isEmpty$(this);
               }

               public boolean nonEmpty() {
                  return IterableOnceOps.nonEmpty$(this);
               }

               public int size() {
                  return IterableOnceOps.size$(this);
               }

               /** @deprecated */
               public final void copyToBuffer(final Buffer dest) {
                  IterableOnceOps.copyToBuffer$(this, dest);
               }

               public int copyToArray(final Object xs) {
                  return IterableOnceOps.copyToArray$(this, xs);
               }

               public int copyToArray(final Object xs, final int start) {
                  return IterableOnceOps.copyToArray$(this, xs, start);
               }

               public int copyToArray(final Object xs, final int start, final int len) {
                  return IterableOnceOps.copyToArray$(this, xs, start, len);
               }

               public Object sum(final Numeric num) {
                  return IterableOnceOps.sum$(this, num);
               }

               public Object product(final Numeric num) {
                  return IterableOnceOps.product$(this, num);
               }

               public Object min(final Ordering ord) {
                  return IterableOnceOps.min$(this, ord);
               }

               public Option minOption(final Ordering ord) {
                  return IterableOnceOps.minOption$(this, ord);
               }

               public Object max(final Ordering ord) {
                  return IterableOnceOps.max$(this, ord);
               }

               public Option maxOption(final Ordering ord) {
                  return IterableOnceOps.maxOption$(this, ord);
               }

               public Object maxBy(final Function1 f, final Ordering ord) {
                  return IterableOnceOps.maxBy$(this, f, ord);
               }

               public Option maxByOption(final Function1 f, final Ordering ord) {
                  return IterableOnceOps.maxByOption$(this, f, ord);
               }

               public Object minBy(final Function1 f, final Ordering ord) {
                  return IterableOnceOps.minBy$(this, f, ord);
               }

               public Option minByOption(final Function1 f, final Ordering ord) {
                  return IterableOnceOps.minByOption$(this, f, ord);
               }

               public Option collectFirst(final PartialFunction pf) {
                  return IterableOnceOps.collectFirst$(this, pf);
               }

               /** @deprecated */
               public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
                  return IterableOnceOps.aggregate$(this, z, seqop, combop);
               }

               public boolean corresponds(final IterableOnce that, final Function2 p) {
                  return IterableOnceOps.corresponds$(this, that, p);
               }

               public final String mkString(final String start, final String sep, final String end) {
                  return IterableOnceOps.mkString$(this, start, sep, end);
               }

               public final String mkString(final String sep) {
                  return IterableOnceOps.mkString$(this, sep);
               }

               public final String mkString() {
                  return IterableOnceOps.mkString$(this);
               }

               public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
                  return IterableOnceOps.addString$(this, b, start, sep, end);
               }

               public final StringBuilder addString(final StringBuilder b, final String sep) {
                  return IterableOnceOps.addString$(this, b, sep);
               }

               public final StringBuilder addString(final StringBuilder b) {
                  return IterableOnceOps.addString$(this, b);
               }

               public Object to(final Factory factory) {
                  return IterableOnceOps.to$(this, factory);
               }

               /** @deprecated */
               public final Iterator toIterator() {
                  return IterableOnceOps.toIterator$(this);
               }

               public List toList() {
                  return IterableOnceOps.toList$(this);
               }

               public Vector toVector() {
                  return IterableOnceOps.toVector$(this);
               }

               public scala.collection.immutable.Map toMap(final scala..less.colon.less ev) {
                  return IterableOnceOps.toMap$(this, ev);
               }

               public Set toSet() {
                  return IterableOnceOps.toSet$(this);
               }

               public Seq toSeq() {
                  return IterableOnceOps.toSeq$(this);
               }

               public IndexedSeq toIndexedSeq() {
                  return IterableOnceOps.toIndexedSeq$(this);
               }

               /** @deprecated */
               public final Stream toStream() {
                  return IterableOnceOps.toStream$(this);
               }

               public final Buffer toBuffer() {
                  return IterableOnceOps.toBuffer$(this);
               }

               public Object toArray(final ClassTag evidence$2) {
                  return IterableOnceOps.toArray$(this, evidence$2);
               }

               public Iterable reversed() {
                  return IterableOnceOps.reversed$(this);
               }

               public Stepper stepper(final StepperShape shape) {
                  return IterableOnce.stepper$(this, shape);
               }

               public int knownSize() {
                  return IterableOnce.knownSize$(this);
               }

               public Iterator iterator() {
                  return scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(this.$outer.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result().toLocalIterator()).asScala();
               }

               public {
                  if (SparkExecuteStatementOperation.this == null) {
                     throw null;
                  } else {
                     this.$outer = SparkExecuteStatementOperation.this;
                     IterableOnce.$init$(this);
                     IterableOnceOps.$init$(this);
                     IterableOps.$init$(this);
                     IterableFactoryDefaults.$init$(this);
                     Iterable.$init$(this);
                  }
               }
            }) : new ArrayFetchIterator(this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result().collect())));
            this.dataTypes_$eq((DataType[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$result().schema().fields()), (x$5) -> x$5.dataType(), scala.reflect.ClassTag..MODULE$.apply(DataType.class)));
         } catch (Throwable var41) {
            if (this.statementId() != null) {
               this.session().sparkContext().cancelJobGroup(this.statementId(), "The corresponding Thriftserver query has failed.");
            }

            OperationState currentState = this.getStatus().getState();
            if (!currentState.isTerminal()) {
               this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error executing query with ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"currentState ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HIVE_OPERATION_STATE..MODULE$, currentState)}))))), var41);
               this.setState(OperationState.ERROR);
               HiveThriftServer2$.MODULE$.eventManager().onStatementError(this.statementId(), var41.getMessage(), org.apache.spark.util.Utils..MODULE$.exceptionString(var41));
               if (var41 instanceof HiveSQLException) {
                  throw var41;
               }

               throw HiveThriftServerErrors$.MODULE$.runningQueryError(var41, this.session().sessionState().conf().errorMessageFormat());
            }

            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignore exception in terminal state with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())})))), var41);
         }

      } finally {
         synchronized(this){}

         try {
            if (!this.getStatus().getState().isTerminal()) {
               this.setState(OperationState.FINISHED);
               HiveThriftServer2$.MODULE$.eventManager().onStatementFinish(this.statementId());
            }
         } catch (Throwable var39) {
            throw var39;
         }

         this.session().sparkContext().clearJobGroup();
      }
   }

   public synchronized void timeoutCancel() {
      if (!this.getStatus().getState().isTerminal()) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Query with ", " timed out "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"after ", " seconds"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, BoxesRunTime.boxToLong(this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeout()))}))))));
         this.setState(OperationState.TIMEDOUT);
         this.cleanup();
         HiveThriftServer2$.MODULE$.eventManager().onStatementTimeout(this.statementId());
      }
   }

   public synchronized void cancel() {
      if (!this.getStatus().getState().isTerminal()) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cancel query with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATEMENT_ID..MODULE$, this.statementId())})))));
         this.setState(OperationState.CANCELED);
         this.cleanup();
         HiveThriftServer2$.MODULE$.eventManager().onStatementCanceled(this.statementId());
      }
   }

   public void cleanup() {
      if (this.runInBackground) {
         Future backgroundHandle = this.getBackgroundHandle();
         if (backgroundHandle != null) {
            BoxesRunTime.boxToBoolean(backgroundHandle.cancel(true));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         BoxedUnit var3 = BoxedUnit.UNIT;
      }

      if (this.statementId() != null) {
         this.session().sparkContext().cancelJobGroup(this.statementId());
      }

      if (this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeoutExecutor() != null) {
         OperationState var4 = this.getStatus().getState();
         OperationState var2 = OperationState.TIMEDOUT;
         if (var4 == null) {
            if (var2 == null) {
               return;
            }
         } else if (var4.equals(var2)) {
            return;
         }

         if (this.getStatus().getState().isTerminal()) {
            this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeoutExecutor().shutdownNow();
            return;
         }
      }

   }

   public SparkExecuteStatementOperation(final SparkSession session, final HiveSession parentSession, final String statement, final Map confOverlay, final boolean runInBackground, final long queryTimeout) {
      super(parentSession, statement, confOverlay, runInBackground);
      this.session = session;
      this.parentSession = parentSession;
      this.statement = statement;
      this.runInBackground = runInBackground;
      Logging.$init$(this);
      SparkOperation.$init$(this);
      long globalTimeout = BoxesRunTime.unboxToLong(session.sessionState().conf().getConf(org.apache.spark.sql.internal.SQLConf..MODULE$.THRIFTSERVER_QUERY_TIMEOUT()));
      this.org$apache$spark$sql$hive$thriftserver$SparkExecuteStatementOperation$$timeout = globalTimeout <= 0L || queryTimeout > 0L && globalTimeout >= queryTimeout ? queryTimeout : globalTimeout;
      this.forceCancel = BoxesRunTime.unboxToBoolean(session.sessionState().conf().getConf(org.apache.spark.sql.internal.SQLConf..MODULE$.THRIFTSERVER_FORCE_CANCEL()));
      String substitutorStatement = (String)org.apache.spark.sql.internal.SQLConf..MODULE$.withExistingConf(session.sessionState().conf(), () -> (new VariableSubstitution()).substitute(this.statement));
      this.redactedStatement = org.apache.spark.util.Utils..MODULE$.redact(session.sessionState().conf().stringRedactionPattern(), substitutorStatement);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
