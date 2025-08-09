package org.apache.spark.deploy.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.apache.spark.SparkException;
import org.apache.spark.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.concurrent.Future;
import scala.concurrent.duration.package;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t=b!B\u00181\u0001QR\u0004\u0002C$\u0001\u0005\u0003\u0005\u000b\u0011B%\t\u000bQ\u0003A\u0011A+\t\u000fe\u0003!\u0019!C\u00055\"1a\f\u0001Q\u0001\nmCqa\u0018\u0001C\u0002\u0013%\u0001\r\u0003\u0004j\u0001\u0001\u0006I!\u0019\u0005\u0006U\u0002!\ta\u001b\u0005\u0006i\u0002!\t!\u001e\u0005\u0006q\u0002!\t!\u001f\u0005\u0006u\u0002!\t!\u001f\u0005\u0006w\u0002!\t!\u001f\u0005\u0006y\u0002!\t! \u0005\n\u0003\u0013\u0001\u0011\u0013!C\u0001\u0003\u0017Aq!!\t\u0001\t\u0003\t\u0019\u0003C\u0004\u0002@\u0001!I!!\u0011\t\u000f\u0005]\u0003\u0001\"\u0003\u0002Z!9\u0011Q\f\u0001\u0005\n\u0005}\u0003\u0002CA4\u0001\u0011\u0005\u0001'!\u001b\t\u000f\u0005U\u0004\u0001\"\u0003\u0002x!9\u00111\u0010\u0001\u0005\n\u0005u\u0004bBAB\u0001\u0011%\u0011Q\u0011\u0005\b\u0003\u0013\u0003A\u0011BAF\u0011\u001d\ty\t\u0001C\u0005\u0003#Cq!!&\u0001\t\u0013\t9\nC\u0004\u0002\u001e\u0002!I!a(\t\u000f\u0005\r\u0006\u0001\"\u0003\u0002&\"9\u0011q\u0016\u0001\u0005\n\u0005E\u0006bBA_\u0001\u0011%\u0011q\u0018\u0005\b\u0003\u0007\u0004A\u0011BAc\u0011\u001d\tY\r\u0001C\u0005\u0003\u001bDq!a5\u0001\t\u0013\t)n\u0002\u0005\u0002\\BB\t\u0001NAo\r\u001dy\u0003\u0007#\u00015\u0003?Da\u0001V\u0011\u0005\u0002\u0005\u0005\b\"CArC\t\u0007I\u0011AAs\u0011!\ti0\tQ\u0001\n\u0005\u001d\b\"CA\u0000C\t\u0007I\u0011\u0002B\u0001\u0011!\u0011I!\tQ\u0001\n\t\r\u0001\"\u0003B\u0006C\t\u0007I\u0011\u0002B\u0007\u0011!\u0011)\"\tQ\u0001\n\t=\u0001\"\u0003B\fC\t\u0007I\u0011\u0002B\u0007\u0011!\u0011I\"\tQ\u0001\n\t=\u0001\"\u0003B\u000eC\t\u0007I\u0011\u0001B\u000f\u0011!\u0011y\"\tQ\u0001\n\u0005M\b\u0002\u0003B\u0011C\u0011\u0005\u0001Ga\t\t\u0011\t%\u0012\u0005\"\u00015\u0005W\u0011ACU3tiN+(-\\5tg&|gn\u00117jK:$(BA\u00193\u0003\u0011\u0011Xm\u001d;\u000b\u0005M\"\u0014A\u00023fa2|\u0017P\u0003\u00026m\u0005)1\u000f]1sW*\u0011q\u0007O\u0001\u0007CB\f7\r[3\u000b\u0003e\n1a\u001c:h'\r\u00011(\u0011\t\u0003y}j\u0011!\u0010\u0006\u0002}\u0005)1oY1mC&\u0011\u0001)\u0010\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\t+U\"A\"\u000b\u0005\u0011#\u0014\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0019\u001b%a\u0002'pO\u001eLgnZ\u0001\u0007[\u0006\u001cH/\u001a:\u0004\u0001A\u0011!*\u0015\b\u0003\u0017>\u0003\"\u0001T\u001f\u000e\u00035S!A\u0014%\u0002\rq\u0012xn\u001c;?\u0013\t\u0001V(\u0001\u0004Qe\u0016$WMZ\u0005\u0003%N\u0013aa\u0015;sS:<'B\u0001)>\u0003\u0019a\u0014N\\5u}Q\u0011a\u000b\u0017\t\u0003/\u0002i\u0011\u0001\r\u0005\u0006\u000f\n\u0001\r!S\u0001\b[\u0006\u001cH/\u001a:t+\u0005Y\u0006c\u0001\u001f]\u0013&\u0011Q,\u0010\u0002\u0006\u0003J\u0014\u0018-_\u0001\t[\u0006\u001cH/\u001a:tA\u0005YAn\\:u\u001b\u0006\u001cH/\u001a:t+\u0005\t\u0007c\u00012h\u00136\t1M\u0003\u0002eK\u00069Q.\u001e;bE2,'B\u00014>\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003Q\u000e\u0014q\u0001S1tQN+G/\u0001\u0007m_N$X*Y:uKJ\u001c\b%\u0001\tde\u0016\fG/Z*vE6L7o]5p]R\u0011An\u001c\t\u0003/6L!A\u001c\u0019\u00035M+(-\\5u%\u0016\u001cH\u000f\u0015:pi>\u001cw\u000e\u001c*fgB|gn]3\t\u000bA<\u0001\u0019A9\u0002\u000fI,\u0017/^3tiB\u0011qK]\u0005\u0003gB\u0012qc\u0011:fCR,7+\u001e2nSN\u001c\u0018n\u001c8SKF,Xm\u001d;\u0002\u001d-LG\u000e\\*vE6L7o]5p]R\u0011AN\u001e\u0005\u0006o\"\u0001\r!S\u0001\rgV\u0014W.[:tS>t\u0017\nZ\u0001\u0013W&dG.\u00117m'V\u0014W.[:tS>t7\u000fF\u0001m\u0003\u0015\u0019G.Z1s\u0003\u0019\u0011X-\u00193zu\u00069\"/Z9vKN$8+\u001e2nSN\u001c\u0018n\u001c8Ti\u0006$Xo\u001d\u000b\u0004Yz|\b\"B<\r\u0001\u0004I\u0005\"CA\u0001\u0019A\u0005\t\u0019AA\u0002\u0003\u0015\tX/[3u!\ra\u0014QA\u0005\u0004\u0003\u000fi$a\u0002\"p_2,\u0017M\\\u0001\"e\u0016\fX/Z:u'V\u0014W.[:tS>t7\u000b^1ukN$C-\u001a4bk2$HEM\u000b\u0003\u0003\u001bQC!a\u0001\u0002\u0010-\u0012\u0011\u0011\u0003\t\u0005\u0003'\ti\"\u0004\u0002\u0002\u0016)!\u0011qCA\r\u0003%)hn\u00195fG.,GMC\u0002\u0002\u001cu\n!\"\u00198o_R\fG/[8o\u0013\u0011\ty\"!\u0006\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\fd_:\u001cHO];diN+(-\\5u%\u0016\fX/Z:u)-\t\u0018QEA\u0015\u0003[\t\t$a\u000f\t\r\u0005\u001db\u00021\u0001J\u0003-\t\u0007\u000f\u001d*fg>,(oY3\t\r\u0005-b\u00021\u0001J\u0003%i\u0017-\u001b8DY\u0006\u001c8\u000f\u0003\u0004\u000209\u0001\raW\u0001\bCB\u0004\u0018I]4t\u0011\u001d\t\u0019D\u0004a\u0001\u0003k\tqb\u001d9be.\u0004&o\u001c9feRLWm\u001d\t\u0006\u0015\u0006]\u0012*S\u0005\u0004\u0003s\u0019&aA'ba\"9\u0011Q\b\bA\u0002\u0005U\u0012\u0001F3om&\u0014xN\\7f]R4\u0016M]5bE2,7/A\u0002hKR$2\u0001\\A\"\u0011\u001d\t)e\u0004a\u0001\u0003\u000f\n1!\u001e:m!\u0011\tI%a\u0015\u000e\u0005\u0005-#\u0002BA'\u0003\u001f\n1A\\3u\u0015\t\t\t&\u0001\u0003kCZ\f\u0017\u0002BA+\u0003\u0017\u00121!\u0016*M\u0003\u0011\u0001xn\u001d;\u0015\u00071\fY\u0006C\u0004\u0002FA\u0001\r!a\u0012\u0002\u0011A|7\u000f\u001e&t_:$R\u0001\\A1\u0003GBq!!\u0012\u0012\u0001\u0004\t9\u0005\u0003\u0004\u0002fE\u0001\r!S\u0001\u0005UN|g.\u0001\u0007sK\u0006$'+Z:q_:\u001cX\rF\u0002m\u0003WBq!!\u001c\u0013\u0001\u0004\ty'\u0001\u0006d_:tWm\u0019;j_:\u0004B!!\u0013\u0002r%!\u00111OA&\u0005EAE\u000f\u001e9V%2\u001buN\u001c8fGRLwN\\\u0001\rO\u0016$8+\u001e2nSR,&\u000f\u001c\u000b\u0005\u0003\u000f\nI\bC\u0003H'\u0001\u0007\u0011*\u0001\u0006hKR\\\u0015\u000e\u001c7Ve2$b!a\u0012\u0002\u0000\u0005\u0005\u0005\"B$\u0015\u0001\u0004I\u0005\"B<\u0015\u0001\u0004I\u0015!D4fi.KG\u000e\\!mYV\u0013H\u000e\u0006\u0003\u0002H\u0005\u001d\u0005\"B$\u0016\u0001\u0004I\u0015aC4fi\u000ecW-\u0019:Ve2$B!a\u0012\u0002\u000e\")qI\u0006a\u0001\u0013\u0006aq-\u001a;SK\u0006$\u0017P_+sYR!\u0011qIAJ\u0011\u00159u\u00031\u0001J\u000319W\r^*uCR,8/\u0016:m)\u0019\t9%!'\u0002\u001c\")q\t\u0007a\u0001\u0013\")q\u000f\u0007a\u0001\u0013\u0006Qq-\u001a;CCN,WK\u001d7\u0015\u0007%\u000b\t\u000bC\u0003H3\u0001\u0007\u0011*\u0001\bwC2LG-\u0019;f\u001b\u0006\u001cH/\u001a:\u0015\t\u0005\u001d\u0016Q\u0016\t\u0004y\u0005%\u0016bAAV{\t!QK\\5u\u0011\u00159%\u00041\u0001J\u0003Y\u0011X\r]8siN+(-\\5tg&|gn\u0015;biV\u001cH\u0003BAT\u0003gCq!!.\u001c\u0001\u0004\t9,\u0001\btk\nl\u0017\u000e\u001e*fgB|gn]3\u0011\u0007]\u000bI,C\u0002\u0002<B\u0012\u0001d\u0011:fCR,7+\u001e2nSN\u001c\u0018n\u001c8SKN\u0004xN\\:f\u0003Q\u0001x\u000e\u001c7Tk\nl\u0017n]:j_:\u001cF/\u0019;vgR!\u0011qUAa\u0011\u00159H\u00041\u0001J\u0003IA\u0017M\u001c3mKJ+7\u000f\u001e*fgB|gn]3\u0015\t\u0005\u001d\u0016q\u0019\u0005\u0007\u0003\u0013l\u0002\u0019\u00017\u0002\u0011I,7\u000f]8og\u0016\fA\u0004[1oI2,WK\\3ya\u0016\u001cG/\u001a3SKN$(+Z:q_:\u001cX\r\u0006\u0003\u0002(\u0006=\u0007BBAi=\u0001\u0007A.\u0001\u0006v]\u0016D\b/Z2uK\u0012\f\u0011\u0004[1oI2,7i\u001c8oK\u000e$\u0018n\u001c8Fq\u000e,\u0007\u000f^5p]R!\u00111AAl\u0011\u0019\tIn\ba\u0001\u0013\u0006IQ.Y:uKJ,&\u000f\\\u0001\u0015%\u0016\u001cHoU;c[&\u001c8/[8o\u00072LWM\u001c;\u0011\u0005]\u000b3CA\u0011<)\t\ti.A\ftkB\u0004xN\u001d;fI6\u000b7\u000f^3s!J,g-\u001b=fgV\u0011\u0011q\u001d\t\u0007\u0003S\fy/a=\u000e\u0005\u0005-(bAAwK\u0006I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0003c\fYOA\u0002TKF\u0004B!!>\u0002|6\u0011\u0011q\u001f\u0006\u0005\u0003s\fy%\u0001\u0003mC:<\u0017b\u0001*\u0002x\u0006A2/\u001e9q_J$X\rZ'bgR,'\u000f\u0015:fM&DXm\u001d\u0011\u0002/\u0015C6\tT+E\u000b\u0012{6\u000bU!S\u0017~+eJV0W\u0003J\u001bVC\u0001B\u0002!\u0019\tIO!\u0002\u0002t&!!qAAv\u0005\r\u0019V\r^\u0001\u0019\u000bb\u001bE*\u0016#F\t~\u001b\u0006+\u0011*L?\u0016sek\u0018,B%N\u0003\u0013!\b*F!>\u0013Fk\u0018#S\u0013Z+%kX*U\u0003R+6kX%O)\u0016\u0013f+\u0011'\u0016\u0005\t=\u0001c\u0001\u001f\u0003\u0012%\u0019!1C\u001f\u0003\u0007%sG/\u0001\u0010S\u000bB{%\u000bV0E%&3VIU0T)\u0006#VkU0J\u001dR+%KV!MA\u0005q\"+\u0012)P%R{FIU%W\u000bJ{6\u000bV!U+N{V*\u0011-`)JKUiU\u0001 %\u0016\u0003vJ\u0015+`\tJKe+\u0012*`'R\u000bE+V*`\u001b\u0006Cv\f\u0016*J\u000bN\u0003\u0013\u0001\u0005)S\u001fR{5i\u0014'`-\u0016\u00136+S(O+\t\t\u00190A\tQ%>#vjQ(M?Z+%kU%P\u001d\u0002\nqCZ5mi\u0016\u00148+_:uK6,eN^5s_:lWM\u001c;\u0015\t\u0005U\"Q\u0005\u0005\b\u0005Oi\u0003\u0019AA\u001b\u0003\r)gN^\u0001\u0013gV\u0004\bo\u001c:ugJ+7\u000f^\"mS\u0016tG\u000f\u0006\u0003\u0002\u0004\t5\u0002\"B$/\u0001\u0004I\u0005"
)
public class RestSubmissionClient implements Logging {
   private final String master;
   private final String[] masters;
   private final HashSet lostMasters;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String PROTOCOL_VERSION() {
      return RestSubmissionClient$.MODULE$.PROTOCOL_VERSION();
   }

   public static Seq supportedMasterPrefixes() {
      return RestSubmissionClient$.MODULE$.supportedMasterPrefixes();
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

   private String[] masters() {
      return this.masters;
   }

   private HashSet lostMasters() {
      return this.lostMasters;
   }

   public SubmitRestProtocolResponse createSubmission(final CreateSubmissionRequest request) {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting a request to launch an application in ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.master)})))));
      BooleanRef handled = BooleanRef.create(false);
      ObjectRef response = ObjectRef.create((Object)null);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.masters()), (m) -> BoxesRunTime.boxToBoolean($anonfun$createSubmission$2(handled, m))).foreach((m) -> {
         $anonfun$createSubmission$3(this, response, request, handled, m);
         return BoxedUnit.UNIT;
      });
      return (SubmitRestProtocolResponse)response.elem;
   }

   public SubmitRestProtocolResponse killSubmission(final String submissionId) {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting a request to kill submission "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SUBMISSION_ID..MODULE$, submissionId)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.master)}))))));
      BooleanRef handled = BooleanRef.create(false);
      ObjectRef response = ObjectRef.create((Object)null);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.masters()), (m) -> BoxesRunTime.boxToBoolean($anonfun$killSubmission$2(handled, m))).foreach((m) -> {
         $anonfun$killSubmission$3(this, submissionId, response, handled, m);
         return BoxedUnit.UNIT;
      });
      return (SubmitRestProtocolResponse)response.elem;
   }

   public SubmitRestProtocolResponse killAllSubmissions() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting a request to kill all submissions in ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.master)})))));
      BooleanRef handled = BooleanRef.create(false);
      ObjectRef response = ObjectRef.create((Object)null);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.masters()), (m) -> BoxesRunTime.boxToBoolean($anonfun$killAllSubmissions$2(handled, m))).foreach((m) -> {
         $anonfun$killAllSubmissions$3(this, response, handled, m);
         return BoxedUnit.UNIT;
      });
      return (SubmitRestProtocolResponse)response.elem;
   }

   public SubmitRestProtocolResponse clear() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting a request to clear ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.master)})))));
      BooleanRef handled = BooleanRef.create(false);
      ObjectRef response = ObjectRef.create((Object)null);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.masters()), (m) -> BoxesRunTime.boxToBoolean($anonfun$clear$2(handled, m))).foreach((m) -> {
         $anonfun$clear$3(this, response, handled, m);
         return BoxedUnit.UNIT;
      });
      return (SubmitRestProtocolResponse)response.elem;
   }

   public SubmitRestProtocolResponse readyz() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting a request to check the status of ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.master)})))));
      BooleanRef handled = BooleanRef.create(false);
      ObjectRef response = ObjectRef.create(new ErrorResponse());
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.masters()), (m) -> BoxesRunTime.boxToBoolean($anonfun$readyz$2(handled, m))).foreach((m) -> {
         $anonfun$readyz$3(this, response, handled, m);
         return BoxedUnit.UNIT;
      });
      return (SubmitRestProtocolResponse)response.elem;
   }

   public SubmitRestProtocolResponse requestSubmissionStatus(final String submissionId, final boolean quiet) {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitting a request for the status of submission "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SUBMISSION_ID..MODULE$, submissionId)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, this.master)}))))));
      BooleanRef handled = BooleanRef.create(false);
      ObjectRef response = ObjectRef.create((Object)null);
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.masters()), (m) -> BoxesRunTime.boxToBoolean($anonfun$requestSubmissionStatus$2(handled, m))).foreach((m) -> {
         $anonfun$requestSubmissionStatus$3(this, submissionId, response, quiet, handled, m);
         return BoxedUnit.UNIT;
      });
      return (SubmitRestProtocolResponse)response.elem;
   }

   public boolean requestSubmissionStatus$default$2() {
      return false;
   }

   public CreateSubmissionRequest constructSubmitRequest(final String appResource, final String mainClass, final String[] appArgs, final scala.collection.immutable.Map sparkProperties, final scala.collection.immutable.Map environmentVariables) {
      CreateSubmissionRequest message = new CreateSubmissionRequest();
      message.clientSparkVersion_$eq(package$.MODULE$.SPARK_VERSION());
      message.appResource_$eq(appResource);
      message.mainClass_$eq(mainClass);
      message.appArgs_$eq(appArgs);
      message.sparkProperties_$eq(sparkProperties);
      message.environmentVariables_$eq(environmentVariables);
      message.validate();
      return message;
   }

   private SubmitRestProtocolResponse get(final URL url) {
      this.logDebug((Function0)(() -> "Sending GET request to server at " + url + "."));
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("GET");
      return this.readResponse(conn);
   }

   private SubmitRestProtocolResponse post(final URL url) {
      this.logDebug((Function0)(() -> "Sending POST request to server at " + url + "."));
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("POST");
      return this.readResponse(conn);
   }

   private SubmitRestProtocolResponse postJson(final URL url, final String json) {
      this.logDebug((Function0)(() -> "Sending POST request to server at " + url + ":\n" + json));
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setRequestProperty("charset", "utf-8");
      conn.setDoOutput(true);

      try {
         DataOutputStream out = new DataOutputStream(conn.getOutputStream());
         Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> out.write(json.getBytes(StandardCharsets.UTF_8)), (JFunction0.mcV.sp)() -> out.close());
      } catch (ConnectException var6) {
         throw new SubmitRestConnectionException("Connect Exception when connect to server", var6);
      }

      return this.readResponse(conn);
   }

   public SubmitRestProtocolResponse readResponse(final HttpURLConnection connection) {
      Future responseFuture = scala.concurrent.Future..MODULE$.apply(() -> {
         int responseCode = connection.getResponseCode();
         if (responseCode != 200) {
            Some errString = new Some(scala.io.Source..MODULE$.fromInputStream(connection.getErrorStream(), scala.io.Codec..MODULE$.fallbackSystemCodec()).getLines().mkString("\n"));
            if (responseCode == 500 && !connection.getContentType().contains("application/json")) {
               throw new SubmitRestProtocolException("Server responded with exception:\n" + errString, SubmitRestProtocolException$.MODULE$.$lessinit$greater$default$2());
            } else {
               this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Server responded with error:\\n", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, errString)})))));
               ErrorResponse error = new ErrorResponse();
               if (responseCode == RestSubmissionServer$.MODULE$.SC_UNKNOWN_PROTOCOL_VERSION()) {
                  error.highestProtocolVersion_$eq(RestSubmissionServer$.MODULE$.PROTOCOL_VERSION());
               }

               error.message_$eq((String)errString.get());
               return error;
            }
         } else {
            InputStream dataStream = connection.getInputStream();
            if (dataStream == null) {
               throw new SubmitRestProtocolException("Server returned empty body", SubmitRestProtocolException$.MODULE$.$lessinit$greater$default$2());
            } else {
               String responseJson = scala.io.Source..MODULE$.fromInputStream(dataStream, scala.io.Codec..MODULE$.fallbackSystemCodec()).mkString();
               this.logDebug((Function0)(() -> "Response from the server:\n" + responseJson));
               SubmitRestProtocolMessage response = SubmitRestProtocolMessage$.MODULE$.fromJson(responseJson);
               response.validate();
               if (response instanceof ErrorResponse) {
                  ErrorResponse var10 = (ErrorResponse)response;
                  this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Server responded with error:\\n", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var10.message())})))));
                  return var10;
               } else if (response instanceof SubmitRestProtocolResponse) {
                  SubmitRestProtocolResponse var11 = (SubmitRestProtocolResponse)response;
                  return var11;
               } else {
                  throw new SubmitRestProtocolException("Message received from server was not a response:\n" + response.toJson(), SubmitRestProtocolException$.MODULE$.$lessinit$greater$default$2());
               }
            }
         }
      }, scala.concurrent.ExecutionContext.Implicits..MODULE$.global());

      try {
         return (SubmitRestProtocolResponse)scala.concurrent.Await..MODULE$.result(responseFuture, (new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(10))).seconds());
      } catch (Throwable var10) {
         if (var10 instanceof FileNotFoundException ? true : var10 instanceof SocketException) {
            throw new SubmitRestConnectionException("Unable to connect to server", var10);
         } else if (var10 instanceof JsonProcessingException ? true : var10 instanceof SubmitRestProtocolException) {
            throw new SubmitRestProtocolException("Malformed response received from server", var10);
         } else if (var10 instanceof TimeoutException) {
            TimeoutException var8 = (TimeoutException)var10;
            throw new SubmitRestConnectionException("No response from server", var8);
         } else if (var10 != null && scala.util.control.NonFatal..MODULE$.apply(var10)) {
            throw new SparkException("Exception while waiting for response", var10);
         } else {
            throw var10;
         }
      }
   }

   private URL getSubmitUrl(final String master) {
      String baseUrl = this.getBaseUrl(master);
      return (new URI(baseUrl + "/create")).toURL();
   }

   private URL getKillUrl(final String master, final String submissionId) {
      String baseUrl = this.getBaseUrl(master);
      return (new URI(baseUrl + "/kill/" + submissionId)).toURL();
   }

   private URL getKillAllUrl(final String master) {
      String baseUrl = this.getBaseUrl(master);
      return (new URI(baseUrl + "/killall")).toURL();
   }

   private URL getClearUrl(final String master) {
      String baseUrl = this.getBaseUrl(master);
      return (new URI(baseUrl + "/clear")).toURL();
   }

   private URL getReadyzUrl(final String master) {
      String baseUrl = this.getBaseUrl(master);
      return (new URI(baseUrl + "/readyz")).toURL();
   }

   private URL getStatusUrl(final String master, final String submissionId) {
      String baseUrl = this.getBaseUrl(master);
      return (new URI(baseUrl + "/status/" + submissionId)).toURL();
   }

   private String getBaseUrl(final String master) {
      ObjectRef masterUrl = ObjectRef.create(master);
      RestSubmissionClient$.MODULE$.supportedMasterPrefixes().foreach((prefix) -> {
         $anonfun$getBaseUrl$1(master, masterUrl, prefix);
         return BoxedUnit.UNIT;
      });
      masterUrl.elem = scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString((String)masterUrl.elem), "/");
      String var10000 = (String)masterUrl.elem;
      return "http://" + var10000 + "/" + RestSubmissionClient$.MODULE$.PROTOCOL_VERSION() + "/submissions";
   }

   private void validateMaster(final String master) {
      boolean valid = RestSubmissionClient$.MODULE$.supportedMasterPrefixes().exists((prefix) -> BoxesRunTime.boxToBoolean($anonfun$validateMaster$1(master, prefix)));
      if (!valid) {
         Seq var10002 = RestSubmissionClient$.MODULE$.supportedMasterPrefixes();
         throw new IllegalArgumentException("This REST client only supports master URLs that start with one of the following: " + var10002.mkString(","));
      }
   }

   private void reportSubmissionStatus(final CreateSubmissionResponse submitResponse) {
      if (scala.Predef..MODULE$.Boolean2boolean(submitResponse.success())) {
         String submissionId = submitResponse.submissionId();
         if (submissionId != null) {
            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submission successfully created as ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SUBMISSION_ID..MODULE$, submissionId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Polling submission state..."})))).log(scala.collection.immutable.Nil..MODULE$))));
            this.pollSubmissionStatus(submissionId);
         } else {
            this.logError((Function0)(() -> "Application successfully submitted, but submission ID was not provided!"));
         }
      } else {
         String failMessage = (String)scala.Option..MODULE$.apply(submitResponse.message()).map((x$1) -> ": " + x$1).getOrElse(() -> "");
         this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application submission failed", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, failMessage)})))));
      }
   }

   private void pollSubmissionStatus(final String submissionId) {
      Object var2 = new Object();

      try {
         scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), RestSubmissionClient$.MODULE$.org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_MAX_TRIES()).foreach$mVc$sp((JFunction1.mcVI.sp)(x$2) -> {
            SubmitRestProtocolResponse response = this.requestSubmissionStatus(submissionId, true);
            if (!(response instanceof SubmissionStatusResponse var10)) {
               throw new NonLocalReturnControl.mcV.sp(var2, BoxedUnit.UNIT);
            } else if (scala.Predef..MODULE$.Boolean2boolean(var10.success())) {
               Option driverState = scala.Option..MODULE$.apply(var10.driverState());
               Option workerId = scala.Option..MODULE$.apply(var10.workerId());
               Option workerHostPort = scala.Option..MODULE$.apply(var10.workerHostPort());
               Option exception = scala.Option..MODULE$.apply(var10.message());
               if (driverState instanceof Some) {
                  Some var16 = (Some)driverState;
                  String state = (String)var16.value();
                  this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"State of driver ", " is now "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SUBMISSION_ID..MODULE$, submissionId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_STATE..MODULE$, state)}))))));
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"State of driver ", " was not found!"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SUBMISSION_ID..MODULE$, submissionId)})))));
                  BoxedUnit var25 = BoxedUnit.UNIT;
               }

               label29: {
                  Tuple2 var18 = new Tuple2(workerId, workerHostPort);
                  if (var18 != null) {
                     Option var19 = (Option)var18._1();
                     Option var20 = (Option)var18._2();
                     if (var19 instanceof Some) {
                        Some var21 = (Some)var19;
                        String id = (String)var21.value();
                        if (var20 instanceof Some) {
                           Some var23 = (Some)var20;
                           String hp = (String)var23.value();
                           this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver is running on worker ", " at ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WORKER_ID..MODULE$, id), new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, hp)})))));
                           BoxedUnit var27 = BoxedUnit.UNIT;
                           break label29;
                        }
                     }
                  }

                  BoxedUnit var26 = BoxedUnit.UNIT;
               }

               exception.foreach((e) -> {
                  $anonfun$pollSubmissionStatus$5(this, e);
                  return BoxedUnit.UNIT;
               });
               throw new NonLocalReturnControl.mcV.sp(var2, BoxedUnit.UNIT);
            } else {
               Thread.sleep((long)RestSubmissionClient$.MODULE$.org$apache$spark$deploy$rest$RestSubmissionClient$$REPORT_DRIVER_STATUS_INTERVAL());
            }
         });
         this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error: Master did not recognize driver ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SUBMISSION_ID..MODULE$, submissionId)})))));
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var4.value$mcV$sp();
      }

   }

   private void handleRestResponse(final SubmitRestProtocolResponse response) {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Server responded with ", ":\\n"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, response.messageType())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESULT..MODULE$, response.toJson())}))))));
   }

   private void handleUnexpectedRestResponse(final SubmitRestProtocolResponse unexpected) {
      this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error: Server responded with message of unexpected type ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, unexpected.messageType())})))));
   }

   private boolean handleConnectionException(final String masterUrl) {
      if (!this.lostMasters().contains(masterUrl)) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to connect to server ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MASTER_URL..MODULE$, masterUrl)})))));
         this.lostMasters().$plus$eq(masterUrl);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this.lostMasters().size() >= this.masters().length;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createSubmission$2(final BooleanRef handled$1, final String m) {
      return !handled$1.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$createSubmission$3(final RestSubmissionClient $this, final ObjectRef response$1, final CreateSubmissionRequest request$1, final BooleanRef handled$1, final String m) {
      $this.validateMaster(m);
      URL url = $this.getSubmitUrl(m);

      try {
         response$1.elem = $this.postJson(url, request$1.toJson());
         SubmitRestProtocolResponse var7 = (SubmitRestProtocolResponse)response$1.elem;
         if (var7 instanceof CreateSubmissionResponse var8) {
            if (scala.Predef..MODULE$.Boolean2boolean(var8.success())) {
               $this.reportSubmissionStatus(var8);
               $this.handleRestResponse(var8);
               handled$1.elem = true;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var11 = BoxedUnit.UNIT;
            }
         } else {
            $this.handleUnexpectedRestResponse(var7);
            BoxedUnit var12 = BoxedUnit.UNIT;
         }
      } catch (SubmitRestConnectionException var10) {
         if ($this.handleConnectionException(m)) {
            throw new SubmitRestConnectionException("Unable to connect to server", var10);
         }
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$killSubmission$2(final BooleanRef handled$2, final String m) {
      return !handled$2.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$killSubmission$3(final RestSubmissionClient $this, final String submissionId$1, final ObjectRef response$2, final BooleanRef handled$2, final String m) {
      $this.validateMaster(m);
      URL url = $this.getKillUrl(m, submissionId$1);

      try {
         response$2.elem = $this.post(url);
         SubmitRestProtocolResponse var7 = (SubmitRestProtocolResponse)response$2.elem;
         if (var7 instanceof KillSubmissionResponse var8) {
            if (!Utils$.MODULE$.responseFromBackup(var8.message())) {
               $this.handleRestResponse(var8);
               handled$2.elem = true;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var11 = BoxedUnit.UNIT;
            }
         } else {
            $this.handleUnexpectedRestResponse(var7);
            BoxedUnit var12 = BoxedUnit.UNIT;
         }
      } catch (SubmitRestConnectionException var10) {
         if ($this.handleConnectionException(m)) {
            throw new SubmitRestConnectionException("Unable to connect to server", var10);
         }
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$killAllSubmissions$2(final BooleanRef handled$3, final String m) {
      return !handled$3.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$killAllSubmissions$3(final RestSubmissionClient $this, final ObjectRef response$3, final BooleanRef handled$3, final String m) {
      $this.validateMaster(m);
      URL url = $this.getKillAllUrl(m);

      try {
         response$3.elem = $this.post(url);
         SubmitRestProtocolResponse var6 = (SubmitRestProtocolResponse)response$3.elem;
         if (var6 instanceof KillAllSubmissionResponse var7) {
            if (!Utils$.MODULE$.responseFromBackup(var7.message())) {
               $this.handleRestResponse(var7);
               handled$3.elem = true;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10 = BoxedUnit.UNIT;
            }
         } else {
            $this.handleUnexpectedRestResponse(var6);
            BoxedUnit var11 = BoxedUnit.UNIT;
         }
      } catch (SubmitRestConnectionException var9) {
         if ($this.handleConnectionException(m)) {
            throw new SubmitRestConnectionException("Unable to connect to server", var9);
         }
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$clear$2(final BooleanRef handled$4, final String m) {
      return !handled$4.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$clear$3(final RestSubmissionClient $this, final ObjectRef response$4, final BooleanRef handled$4, final String m) {
      $this.validateMaster(m);
      URL url = $this.getClearUrl(m);

      try {
         response$4.elem = $this.post(url);
         SubmitRestProtocolResponse var6 = (SubmitRestProtocolResponse)response$4.elem;
         if (var6 instanceof ClearResponse var7) {
            if (!Utils$.MODULE$.responseFromBackup(var7.message())) {
               $this.handleRestResponse(var7);
               handled$4.elem = true;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10 = BoxedUnit.UNIT;
            }
         } else {
            $this.handleUnexpectedRestResponse(var6);
            BoxedUnit var11 = BoxedUnit.UNIT;
         }
      } catch (SubmitRestConnectionException var9) {
         if ($this.handleConnectionException(m)) {
            throw new SubmitRestConnectionException("Unable to connect to server", var9);
         }
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$readyz$2(final BooleanRef handled$5, final String m) {
      return !handled$5.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$readyz$3(final RestSubmissionClient $this, final ObjectRef response$5, final BooleanRef handled$5, final String m) {
      $this.validateMaster(m);
      URL url = $this.getReadyzUrl(m);

      try {
         response$5.elem = $this.get(url);
         SubmitRestProtocolResponse var6 = (SubmitRestProtocolResponse)response$5.elem;
         if (var6 instanceof ReadyzResponse var7) {
            if (!Utils$.MODULE$.responseFromBackup(var7.message())) {
               $this.handleRestResponse(var7);
               handled$5.elem = true;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10 = BoxedUnit.UNIT;
            }
         } else {
            $this.handleUnexpectedRestResponse(var6);
            BoxedUnit var11 = BoxedUnit.UNIT;
         }
      } catch (SubmitRestConnectionException var9) {
         if ($this.handleConnectionException(m)) {
            throw new SubmitRestConnectionException("Unable to connect to server", var9);
         }
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$requestSubmissionStatus$2(final BooleanRef handled$6, final String m) {
      return !handled$6.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$requestSubmissionStatus$3(final RestSubmissionClient $this, final String submissionId$2, final ObjectRef response$6, final boolean quiet$1, final BooleanRef handled$6, final String m) {
      $this.validateMaster(m);
      URL url = $this.getStatusUrl(m, submissionId$2);

      try {
         response$6.elem = $this.get(url);
         SubmitRestProtocolResponse var8 = (SubmitRestProtocolResponse)response$6.elem;
         if (var8 instanceof SubmissionStatusResponse var9) {
            if (scala.Predef..MODULE$.Boolean2boolean(var9.success())) {
               if (!quiet$1) {
                  $this.handleRestResponse(var9);
               }

               handled$6.elem = true;
               BoxedUnit var12 = BoxedUnit.UNIT;
               return;
            }
         }

         $this.handleUnexpectedRestResponse(var8);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } catch (SubmitRestConnectionException var11) {
         if ($this.handleConnectionException(m)) {
            throw new SubmitRestConnectionException("Unable to connect to server", var11);
         }
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$getBaseUrl$1(final String master$1, final ObjectRef masterUrl$1, final String prefix) {
      if (master$1.startsWith(prefix)) {
         masterUrl$1.elem = scala.collection.StringOps..MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(master$1), prefix);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$validateMaster$1(final String master$2, final String prefix) {
      return master$2.startsWith(prefix);
   }

   // $FF: synthetic method
   public static final void $anonfun$pollSubmissionStatus$5(final RestSubmissionClient $this, final String e) {
      $this.logError(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, e)})))));
   }

   public RestSubmissionClient(final String master) {
      this.master = master;
      Logging.$init$(this);
      this.masters = master.startsWith("spark://") ? Utils$.MODULE$.parseStandaloneMasterUrls(master) : (String[])((Object[])(new String[]{master}));
      this.lostMasters = new HashSet();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
