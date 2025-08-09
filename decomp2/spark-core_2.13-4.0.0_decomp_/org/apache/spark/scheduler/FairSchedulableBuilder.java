package org.apache.spark.scheduler;

import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.xml.Elem;
import scala.xml.Node;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}e!B\u0012%\u0001\u0019b\u0003\u0002C\u001f\u0001\u0005\u000b\u0007I\u0011A \t\u0011\r\u0003!\u0011!Q\u0001\n\u0001C\u0001\u0002\u0012\u0001\u0003\u0002\u0003\u0006I!\u0012\u0005\u0006\u0013\u0002!\tA\u0013\u0005\b\u001d\u0002\u0011\r\u0011\"\u0001P\u0011\u0019q\u0006\u0001)A\u0005!\"9q\f\u0001b\u0001\n\u0003\u0001\u0007B\u00025\u0001A\u0003%\u0011\rC\u0004j\u0001\t\u0007I\u0011\u00011\t\r)\u0004\u0001\u0015!\u0003b\u0011\u001dY\u0007A1A\u0005\u0002\u0001Da\u0001\u001c\u0001!\u0002\u0013\t\u0007bB7\u0001\u0005\u0004%\t\u0001\u0019\u0005\u0007]\u0002\u0001\u000b\u0011B1\t\u000f=\u0004!\u0019!C\u0001A\"1\u0001\u000f\u0001Q\u0001\n\u0005Dq!\u001d\u0001C\u0002\u0013\u0005\u0001\r\u0003\u0004s\u0001\u0001\u0006I!\u0019\u0005\bg\u0002\u0011\r\u0011\"\u0001a\u0011\u0019!\b\u0001)A\u0005C\"9Q\u000f\u0001b\u0001\n\u0003\u0001\u0007B\u0002<\u0001A\u0003%\u0011\rC\u0004x\u0001\t\u0007I\u0011\u0001=\t\u000f\u0005\r\u0001\u0001)A\u0005s\"I\u0011Q\u0001\u0001C\u0002\u0013\u0005\u0011q\u0001\u0005\t\u0003\u001f\u0001\u0001\u0015!\u0003\u0002\n!I\u0011\u0011\u0003\u0001C\u0002\u0013\u0005\u0011q\u0001\u0005\t\u0003'\u0001\u0001\u0015!\u0003\u0002\n!9\u0011Q\u0003\u0001\u0005B\u0005]\u0001bBA\u0010\u0001\u0011%\u0011q\u0003\u0005\b\u0003C\u0001A\u0011BA\u0012\u0011\u001d\tI\u0004\u0001C\u0005\u0003wAq!!\u001d\u0001\t\u0013\t\u0019\bC\u0004\u0002\u0002\u0002!\t%a!\u0003-\u0019\u000b\u0017N]*dQ\u0016$W\u000f\\1cY\u0016\u0014U/\u001b7eKJT!!\n\u0014\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0014)\u0003\u0015\u0019\b/\u0019:l\u0015\tI#&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002W\u0005\u0019qN]4\u0014\t\u0001i3g\u000e\t\u0003]Ej\u0011a\f\u0006\u0002a\u0005)1oY1mC&\u0011!g\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Q*T\"\u0001\u0013\n\u0005Y\"#AE*dQ\u0016$W\u000f\\1cY\u0016\u0014U/\u001b7eKJ\u0004\"\u0001O\u001e\u000e\u0003eR!A\u000f\u0014\u0002\u0011%tG/\u001a:oC2L!\u0001P\u001d\u0003\u000f1{wmZ5oO\u0006A!o\\8u!>|Gn\u0001\u0001\u0016\u0003\u0001\u0003\"\u0001N!\n\u0005\t##\u0001\u0002)p_2\f\u0011B]8piB{w\u000e\u001c\u0011\u0002\u0005M\u001c\u0007C\u0001$H\u001b\u00051\u0013B\u0001%'\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\u0019a\u0014N\\5u}Q\u00191\nT'\u0011\u0005Q\u0002\u0001\"B\u001f\u0005\u0001\u0004\u0001\u0005\"\u0002#\u0005\u0001\u0004)\u0015AE:dQ\u0016$W\u000f\\3s\u00032dwn\u0019$jY\u0016,\u0012\u0001\u0015\t\u0004]E\u001b\u0016B\u0001*0\u0005\u0019y\u0005\u000f^5p]B\u0011Ak\u0017\b\u0003+f\u0003\"AV\u0018\u000e\u0003]S!\u0001\u0017 \u0002\rq\u0012xn\u001c;?\u0013\tQv&\u0001\u0004Qe\u0016$WMZ\u0005\u00039v\u0013aa\u0015;sS:<'B\u0001.0\u0003M\u00198\r[3ek2,'/\u00117m_\u000e4\u0015\u000e\\3!\u0003Y!UIR!V\u0019R{6k\u0011%F\tVcUIU0G\u00132+U#A1\u0011\u0005\t<W\"A2\u000b\u0005\u0011,\u0017\u0001\u00027b]\u001eT\u0011AZ\u0001\u0005U\u00064\u0018-\u0003\u0002]G\u00069B)\u0012$B+2#vlU\"I\u000b\u0012+F*\u0012*`\r&cU\tI\u0001\u001a\r\u0006K%kX*D\u0011\u0016#U\u000bT#S?B\u0013v\nU#S)&+5+\u0001\u000eG\u0003&\u0013vlU\"I\u000b\u0012+F*\u0012*`!J{\u0005+\u0012*U\u0013\u0016\u001b\u0006%A\tE\u000b\u001a\u000bU\u000b\u0014+`!>{Ej\u0018(B\u001b\u0016\u000b!\u0003R#G\u0003VcEk\u0018)P\u001f2{f*Q'FA\u00059R*\u0013(J\u001bVkul\u0015%B%\u0016\u001bv\f\u0015*P!\u0016\u0013F+W\u0001\u0019\u001b&s\u0015*T+N?NC\u0015IU#T?B\u0013v\nU#S)f\u0003\u0013\u0001G*D\u0011\u0016#U\u000bT%O\u000f~ku\nR#`!J{\u0005+\u0012*U3\u0006I2k\u0011%F\tVc\u0015JT$`\u001b>#Ui\u0018)S\u001fB+%\u000bV-!\u0003=9V)S$I)~\u0003&k\u0014)F%RK\u0016\u0001E,F\u0013\u001eCEk\u0018)S\u001fB+%\u000bV-!\u0003I\u0001vj\u0014'`\u001d\u0006kUi\u0018)S\u001fB+%\u000bV-\u0002'A{u\nT0O\u00036+u\f\u0015*P!\u0016\u0013F+\u0017\u0011\u0002\u001dA{u\nT*`!J{\u0005+\u0012*U3\u0006y\u0001kT(M'~\u0003&k\u0014)F%RK\u0006%A\fE\u000b\u001a\u000bU\u000b\u0014+`'\u000eCU\tR+M\u0013:;u,T(E\u000bV\t\u0011\u0010\u0005\u0002{{:\u0011Ag_\u0005\u0003y\u0012\nabU2iK\u0012,H.\u001b8h\u001b>$W-\u0003\u0002\u007f\u007f\n)a+\u00197vK&\u0019\u0011\u0011A\u0018\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u0019\t\u00163\u0015)\u0016'U?N\u001b\u0005*\u0012#V\u0019&suiX'P\t\u0016\u0003\u0013!\u0006#F\r\u0006+F\nV0N\u0013:KU*V'`'\"\u000b%+R\u000b\u0003\u0003\u0013\u00012ALA\u0006\u0013\r\tia\f\u0002\u0004\u0013:$\u0018A\u0006#F\r\u0006+F\nV0N\u0013:KU*V'`'\"\u000b%+\u0012\u0011\u0002\u001d\u0011+e)Q+M)~;V)S$I)\u0006yA)\u0012$B+2#vlV#J\u000f\"#\u0006%\u0001\u0006ck&dG\rU8pYN$\"!!\u0007\u0011\u00079\nY\"C\u0002\u0002\u001e=\u0012A!\u00168ji\u0006\u0001\"-^5mI\u0012+g-Y;miB{w\u000e\\\u0001\u0017EVLG\u000e\u001a$bSJ\u001c6\r[3ek2,'\u000fU8pYR1\u0011\u0011DA\u0013\u0003kAq!a\n \u0001\u0004\tI#\u0001\u0002jgB!\u00111FA\u0019\u001b\t\tiCC\u0002\u00020\u0015\f!![8\n\t\u0005M\u0012Q\u0006\u0002\f\u0013:\u0004X\u000f^*ue\u0016\fW\u000e\u0003\u0004\u00028}\u0001\raU\u0001\tM&dWMT1nK\u00061r-\u001a;TG\",G-\u001e7j]\u001elu\u000eZ3WC2,X\r\u0006\u0006\u0002>\u0005]\u0013qMA6\u0003_\u0002B!a\u0010\u0002R9\u0019\u0011\u0011I>\u000f\t\u0005\r\u0013q\n\b\u0005\u0003\u000b\niE\u0004\u0003\u0002H\u0005-cb\u0001,\u0002J%\t1&\u0003\u0002*U%\u0011q\u0005K\u0005\u0003K\u0019JA!a\u0015\u0002V\tq1k\u00195fIVd\u0017N\\4N_\u0012,'B\u0001?%\u0011\u001d\tI\u0006\ta\u0001\u00037\n\u0001\u0002]8pY:{G-\u001a\t\u0005\u0003;\n\u0019'\u0004\u0002\u0002`)\u0019\u0011\u0011M\u0018\u0002\u0007alG.\u0003\u0003\u0002f\u0005}#\u0001\u0002(pI\u0016Da!!\u001b!\u0001\u0004\u0019\u0016\u0001\u00039p_2t\u0015-\\3\t\u000f\u00055\u0004\u00051\u0001\u0002>\u0005aA-\u001a4bk2$h+\u00197vK\"1\u0011q\u0007\u0011A\u0002M\u000b1bZ3u\u0013:$h+\u00197vKRa\u0011\u0011BA;\u0003o\nI(! \u0002\u0000!9\u0011\u0011L\u0011A\u0002\u0005m\u0003BBA5C\u0001\u00071\u000b\u0003\u0004\u0002|\u0005\u0002\raU\u0001\raJ|\u0007/\u001a:us:\u000bW.\u001a\u0005\b\u0003[\n\u0003\u0019AA\u0005\u0011\u0019\t9$\ta\u0001'\u0006\t\u0012\r\u001a3UCN\\7+\u001a;NC:\fw-\u001a:\u0015\r\u0005e\u0011QQAH\u0011\u001d\t9I\ta\u0001\u0003\u0013\u000bq!\\1oC\u001e,'\u000fE\u00025\u0003\u0017K1!!$%\u0005-\u00196\r[3ek2\f'\r\\3\t\u000f\u0005E%\u00051\u0001\u0002\u0014\u0006Q\u0001O]8qKJ$\u0018.Z:\u0011\t\u0005U\u00151T\u0007\u0003\u0003/S1!!'f\u0003\u0011)H/\u001b7\n\t\u0005u\u0015q\u0013\u0002\u000b!J|\u0007/\u001a:uS\u0016\u001c\b"
)
public class FairSchedulableBuilder implements SchedulableBuilder, Logging {
   private final Pool rootPool;
   private final SparkContext sc;
   private final Option schedulerAllocFile;
   private final String DEFAULT_SCHEDULER_FILE;
   private final String FAIR_SCHEDULER_PROPERTIES;
   private final String DEFAULT_POOL_NAME;
   private final String MINIMUM_SHARES_PROPERTY;
   private final String SCHEDULING_MODE_PROPERTY;
   private final String WEIGHT_PROPERTY;
   private final String POOL_NAME_PROPERTY;
   private final String POOLS_PROPERTY;
   private final Enumeration.Value DEFAULT_SCHEDULING_MODE;
   private final int DEFAULT_MINIMUM_SHARE;
   private final int DEFAULT_WEIGHT;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   public Pool rootPool() {
      return this.rootPool;
   }

   public Option schedulerAllocFile() {
      return this.schedulerAllocFile;
   }

   public String DEFAULT_SCHEDULER_FILE() {
      return this.DEFAULT_SCHEDULER_FILE;
   }

   public String FAIR_SCHEDULER_PROPERTIES() {
      return this.FAIR_SCHEDULER_PROPERTIES;
   }

   public String DEFAULT_POOL_NAME() {
      return this.DEFAULT_POOL_NAME;
   }

   public String MINIMUM_SHARES_PROPERTY() {
      return this.MINIMUM_SHARES_PROPERTY;
   }

   public String SCHEDULING_MODE_PROPERTY() {
      return this.SCHEDULING_MODE_PROPERTY;
   }

   public String WEIGHT_PROPERTY() {
      return this.WEIGHT_PROPERTY;
   }

   public String POOL_NAME_PROPERTY() {
      return this.POOL_NAME_PROPERTY;
   }

   public String POOLS_PROPERTY() {
      return this.POOLS_PROPERTY;
   }

   public Enumeration.Value DEFAULT_SCHEDULING_MODE() {
      return this.DEFAULT_SCHEDULING_MODE;
   }

   public int DEFAULT_MINIMUM_SHARE() {
      return this.DEFAULT_MINIMUM_SHARE;
   }

   public int DEFAULT_WEIGHT() {
      return this.DEFAULT_WEIGHT;
   }

   public void buildPools() {
      Option fileData = .MODULE$;

      try {
         fileData = (Option)this.schedulerAllocFile().map((f) -> {
            Path filePath = new Path(f);
            FSDataInputStream fis = filePath.getFileSystem(this.sc.hadoopConfiguration()).open(filePath);
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Creating Fair Scheduler pools from ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, f)})))));
            return new Some(new Tuple2(fis, f));
         }).getOrElse(() -> {
            InputStream is = Utils$.MODULE$.getSparkClassLoader().getResourceAsStream(this.DEFAULT_SCHEDULER_FILE());
            if (is != null) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Creating Fair Scheduler pools from default file: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, this.DEFAULT_SCHEDULER_FILE())}))))));
               return new Some(new Tuple2(is, this.DEFAULT_SCHEDULER_FILE()));
            } else {
               Enumeration.Value schedulingMode = SchedulingMode$.MODULE$.withName((String)this.sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.SCHEDULER_MODE()));
               this.rootPool().addSchedulable(new Pool(this.DEFAULT_POOL_NAME(), schedulingMode, this.DEFAULT_MINIMUM_SHARE(), this.DEFAULT_WEIGHT()));
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fair scheduler configuration not found, created default pool: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DEFAULT_NAME..MODULE$, this.DEFAULT_POOL_NAME())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"schedulingMode: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCHEDULING_MODE..MODULE$, schedulingMode)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"minShare: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_SHARE..MODULE$, BoxesRunTime.boxToInteger(this.DEFAULT_MINIMUM_SHARE()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"weight: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WEIGHT..MODULE$, BoxesRunTime.boxToInteger(this.DEFAULT_WEIGHT()))}))))));
               return .MODULE$;
            }
         });
         fileData.foreach((x0$1) -> {
            $anonfun$buildPools$6(this, x0$1);
            return BoxedUnit.UNIT;
         });
      } catch (Throwable var10) {
         if (var10 != null && scala.util.control.NonFatal..MODULE$.apply(var10)) {
            if (fileData.isDefined()) {
               String fileName = (String)((Tuple2)fileData.get())._2();
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error while building the fair scheduler pools from ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, fileName)})))), var10);
            } else {
               this.logError((Function0)(() -> "Error while building the fair scheduler pools"), var10);
            }

            throw var10;
         }

         throw var10;
      } finally {
         fileData.foreach((x0$2) -> {
            $anonfun$buildPools$9(x0$2);
            return BoxedUnit.UNIT;
         });
      }

      this.buildDefaultPool();
   }

   private void buildDefaultPool() {
      if (this.rootPool().getSchedulableByName(this.DEFAULT_POOL_NAME()) == null) {
         Pool pool = new Pool(this.DEFAULT_POOL_NAME(), this.DEFAULT_SCHEDULING_MODE(), this.DEFAULT_MINIMUM_SHARE(), this.DEFAULT_WEIGHT());
         this.rootPool().addSchedulable(pool);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Created default pool: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POOL_NAME..MODULE$, this.DEFAULT_POOL_NAME())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"schedulingMode: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCHEDULING_MODE..MODULE$, this.DEFAULT_SCHEDULING_MODE())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"minShare: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_SHARE..MODULE$, BoxesRunTime.boxToInteger(this.DEFAULT_MINIMUM_SHARE()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"weight: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WEIGHT..MODULE$, BoxesRunTime.boxToInteger(this.DEFAULT_WEIGHT()))}))))));
      }
   }

   private void buildFairSchedulerPool(final InputStream is, final String fileName) {
      Elem xml = (Elem)scala.xml.XML..MODULE$.load(is);
      xml.$bslash$bslash(this.POOLS_PROPERTY()).foreach((poolNode) -> {
         $anonfun$buildFairSchedulerPool$1(this, fileName, poolNode);
         return BoxedUnit.UNIT;
      });
   }

   private Enumeration.Value getSchedulingModeValue(final Node poolNode, final String poolName, final Enumeration.Value defaultValue, final String fileName) {
      String xmlSchedulingMode = poolNode.$bslash(this.SCHEDULING_MODE_PROPERTY()).text().trim().toUpperCase(Locale.ROOT);
      MessageWithContext warningMessage = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unsupported schedulingMode: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " found in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.XML_SCHEDULING_MODE..MODULE$, xmlSchedulingMode)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fair Scheduler configuration file: ", ", using "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, fileName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the default schedulingMode: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " for pool: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCHEDULING_MODE..MODULE$, defaultValue)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POOL_NAME..MODULE$, poolName)}))));

      Enumeration.Value var10000;
      try {
         label30: {
            var10000 = SchedulingMode$.MODULE$.withName(xmlSchedulingMode);
            Enumeration.Value var7 = SchedulingMode$.MODULE$.NONE();
            if (var10000 == null) {
               if (var7 != null) {
                  break label30;
               }
            } else if (!var10000.equals(var7)) {
               break label30;
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> warningMessage));
            var10000 = defaultValue;
            return var10000;
         }

         var10000 = SchedulingMode$.MODULE$.withName(xmlSchedulingMode);
      } catch (NoSuchElementException var8) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> warningMessage));
         var10000 = defaultValue;
      }

      return var10000;
   }

   private int getIntValue(final Node poolNode, final String poolName, final String propertyName, final int defaultValue, final String fileName) {
      String data = poolNode.$bslash(propertyName).text().trim();

      int var10000;
      try {
         var10000 = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(data));
      } catch (NumberFormatException var7) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error while loading fair scheduler configuration from "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, fileName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is blank or invalid: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PROPERTY_NAME..MODULE$, propertyName), new MDC(org.apache.spark.internal.LogKeys.DATA..MODULE$, data)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"using the default ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DEFAULT_NAME..MODULE$, propertyName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " for pool: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DEFAULT_VALUE..MODULE$, BoxesRunTime.boxToInteger(defaultValue)), new MDC(org.apache.spark.internal.LogKeys.POOL_NAME..MODULE$, poolName)}))))));
         var10000 = defaultValue;
      }

      return var10000;
   }

   public void addTaskSetManager(final Schedulable manager, final Properties properties) {
      String poolName = properties != null ? properties.getProperty(this.FAIR_SCHEDULER_PROPERTIES(), this.DEFAULT_POOL_NAME()) : this.DEFAULT_POOL_NAME();
      Schedulable parentPool = this.rootPool().getSchedulableByName(poolName);
      if (parentPool == null) {
         parentPool = new Pool(poolName, this.DEFAULT_SCHEDULING_MODE(), this.DEFAULT_MINIMUM_SHARE(), this.DEFAULT_WEIGHT());
         this.rootPool().addSchedulable(parentPool);
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"A job was submitted with scheduler pool "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", which has not been "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCHEDULER_POOL_NAME..MODULE$, poolName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"configured. This can happen when the file that pools are read from isn't set, or "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"when that file doesn't contain ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POOL_NAME..MODULE$, poolName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Created ", " with default "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CREATED_POOL_NAME..MODULE$, poolName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"configuration (schedulingMode: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCHEDULING_MODE..MODULE$, this.DEFAULT_SCHEDULING_MODE())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"minShare: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_SHARE..MODULE$, BoxesRunTime.boxToInteger(this.DEFAULT_MINIMUM_SHARE()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"weight: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WEIGHT..MODULE$, BoxesRunTime.boxToInteger(this.DEFAULT_WEIGHT()))}))))));
      }

      parentPool.addSchedulable(manager);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added task set ", " tasks to pool "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_SET_MANAGER..MODULE$, manager.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POOL_NAME..MODULE$, poolName)}))))));
   }

   // $FF: synthetic method
   public static final void $anonfun$buildPools$6(final FairSchedulableBuilder $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         InputStream is = (InputStream)x0$1._1();
         String fileName = (String)x0$1._2();
         $this.buildFairSchedulerPool(is, fileName);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$buildPools$9(final Tuple2 x0$2) {
      if (x0$2 != null) {
         InputStream is = (InputStream)x0$2._1();
         is.close();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$buildFairSchedulerPool$1(final FairSchedulableBuilder $this, final String fileName$2, final Node poolNode) {
      String poolName = poolNode.$bslash($this.POOL_NAME_PROPERTY()).text();
      Enumeration.Value schedulingMode = $this.getSchedulingModeValue(poolNode, poolName, $this.DEFAULT_SCHEDULING_MODE(), fileName$2);
      int minShare = $this.getIntValue(poolNode, poolName, $this.MINIMUM_SHARES_PROPERTY(), $this.DEFAULT_MINIMUM_SHARE(), fileName$2);
      int weight = $this.getIntValue(poolNode, poolName, $this.WEIGHT_PROPERTY(), $this.DEFAULT_WEIGHT(), fileName$2);
      $this.rootPool().addSchedulable(new Pool(poolName, schedulingMode, minShare, weight));
      $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Created pool: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POOL_NAME..MODULE$, poolName)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"schedulingMode: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SCHEDULING_MODE..MODULE$, schedulingMode)})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"minShare: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_SHARE..MODULE$, BoxesRunTime.boxToInteger(minShare))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"weight: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WEIGHT..MODULE$, BoxesRunTime.boxToInteger(weight))}))))));
   }

   public FairSchedulableBuilder(final Pool rootPool, final SparkContext sc) {
      this.rootPool = rootPool;
      this.sc = sc;
      Logging.$init$(this);
      this.schedulerAllocFile = (Option)sc.conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.SCHEDULER_ALLOCATION_FILE());
      this.DEFAULT_SCHEDULER_FILE = "fairscheduler.xml";
      this.FAIR_SCHEDULER_PROPERTIES = SparkContext$.MODULE$.SPARK_SCHEDULER_POOL();
      this.DEFAULT_POOL_NAME = "default";
      this.MINIMUM_SHARES_PROPERTY = "minShare";
      this.SCHEDULING_MODE_PROPERTY = "schedulingMode";
      this.WEIGHT_PROPERTY = "weight";
      this.POOL_NAME_PROPERTY = "@name";
      this.POOLS_PROPERTY = "pool";
      this.DEFAULT_SCHEDULING_MODE = SchedulingMode$.MODULE$.FIFO();
      this.DEFAULT_MINIMUM_SHARE = 0;
      this.DEFAULT_WEIGHT = 1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
