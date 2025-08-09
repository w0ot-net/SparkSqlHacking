package org.apache.spark.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.spark.SparkException;
import scala.Function0;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.concurrent.Awaitable;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t%tAB\u000f\u001f\u0011\u0003\u0001cE\u0002\u0004)=!\u0005\u0001%\u000b\u0005\u0006a\u0005!\tA\r\u0005\bg\u0005\u0011\r\u0011\"\u00035\u0011\u0019Y\u0014\u0001)A\u0005k!)A(\u0001C\u0001{!)a)\u0001C\u0001\u000f\")1*\u0001C\u0001\u0019\")Q,\u0001C\u0001=\")Q,\u0001C\u0001G\"9A.AI\u0001\n\u0003i\u0007\"\u0002=\u0002\t\u0003I\b\"B?\u0002\t\u0003q\bbBA\u0002\u0003\u0011\u0005\u0011Q\u0001\u0005\b\u0003/\tA\u0011AA\r\u0011\u001d\t\u0019#\u0001C\u0001\u0003KAq!a\f\u0002\t\u0003\t\t\u0004C\u0004\u0002<\u0005!\t!!\u0010\t\u0013\u0005=\u0014!%A\u0005\u0002\u0005E\u0004bBA=\u0003\u0011\u0005\u00111\u0010\u0005\n\u0003G\u000b\u0011\u0013!C\u0001\u0003KC\u0011\"!,\u0002#\u0003%\t!a,\t\u000f\u0005M\u0016\u0001\"\u0001\u00026\"9\u0011\u0011Y\u0001\u0005\u0002\u0005\r\u0007bBAa\u0003\u0011\u0005\u0011q\u001f\u0005\b\u0005\u001b\tA\u0011\u0001B\b\u0011\u001d\u0011\t#\u0001C\u0001\u0005GA\u0011Ba\r\u0002#\u0003%\tA!\u000e\t\u000f\te\u0012\u0001\"\u0001\u0003<\u0005YA\u000b\u001b:fC\u0012,F/\u001b7t\u0015\ty\u0002%\u0001\u0003vi&d'BA\u0011#\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0019C%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002K\u0005\u0019qN]4\u0011\u0005\u001d\nQ\"\u0001\u0010\u0003\u0017QC'/Z1e+RLGn]\n\u0003\u0003)\u0002\"a\u000b\u0018\u000e\u00031R\u0011!L\u0001\u0006g\u000e\fG.Y\u0005\u0003_1\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\u0019\n!d]1nKRC'/Z1e\u000bb,7-\u001e;j_:\u001cuN\u001c;fqR,\u0012!\u000e\t\u0003mej\u0011a\u000e\u0006\u0003q1\n!bY8oGV\u0014(/\u001a8u\u0013\tQtGA\u0010Fq\u0016\u001cW\u000f^5p]\u000e{g\u000e^3yi\u0016CXmY;u_J\u001cVM\u001d<jG\u0016\f1d]1nKRC'/Z1e\u000bb,7-\u001e;j_:\u001cuN\u001c;fqR\u0004\u0013!G:b[\u0016$\u0006N]3bI\u0016CXmY;u_J\u001cVM\u001d<jG\u0016$\u0012A\u0010\t\u0003\u007f\u0011k\u0011\u0001\u0011\u0006\u0003q\u0005S!a\b\"\u000b\u0003\r\u000bAA[1wC&\u0011Q\t\u0011\u0002\u0010\u000bb,7-\u001e;peN+'O^5dK\u0006Q1/Y7f)\"\u0014X-\u00193\u0016\u0003!\u0003\"AN%\n\u0005);$\u0001G#yK\u000e,H/[8o\u0007>tG/\u001a=u\u000bb,7-\u001e;pe\u0006\u0011b.Y7fIRC'/Z1e\r\u0006\u001cGo\u001c:z)\ti\u0005\u000b\u0005\u0002@\u001d&\u0011q\n\u0011\u0002\u000e)\"\u0014X-\u00193GC\u000e$xN]=\t\u000bE;\u0001\u0019\u0001*\u0002\rA\u0014XMZ5y!\t\u0019&L\u0004\u0002U1B\u0011Q\u000bL\u0007\u0002-*\u0011q+M\u0001\u0007yI|w\u000e\u001e \n\u0005ec\u0013A\u0002)sK\u0012,g-\u0003\u0002\\9\n11\u000b\u001e:j]\u001eT!!\u0017\u0017\u000239,w\u000fR1f[>t7)Y2iK\u0012$\u0006N]3bIB{w\u000e\u001c\u000b\u0003?\n\u0004\"a\u00101\n\u0005\u0005\u0004%A\u0005+ie\u0016\fG\rU8pY\u0016CXmY;u_JDQ!\u0015\u0005A\u0002I#Ba\u00183fU\")\u0011+\u0003a\u0001%\")a-\u0003a\u0001O\u0006yQ.\u0019=UQJ,\u0017\r\u001a(v[\n,'\u000f\u0005\u0002,Q&\u0011\u0011\u000e\f\u0002\u0004\u0013:$\bbB6\n!\u0003\u0005\raZ\u0001\u0011W\u0016,\u0007/\u00117jm\u0016\u001cVmY8oIN\f1E\\3x\t\u0006,Wn\u001c8DC\u000eDW\r\u001a+ie\u0016\fG\rU8pY\u0012\"WMZ1vYR$3'F\u0001oU\t9wnK\u0001q!\t\th/D\u0001s\u0015\t\u0019H/A\u0005v]\u000eDWmY6fI*\u0011Q\u000fL\u0001\u000bC:tw\u000e^1uS>t\u0017BA<s\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0019]\u0016<H)Y3n_:4\u0015\u000e_3e)\"\u0014X-\u00193Q_>dGcA0{y\")1p\u0003a\u0001O\u0006Aa\u000e\u00165sK\u0006$7\u000fC\u0003R\u0017\u0001\u0007!+A\u000foK^$\u0015-Z7p]NKgn\u001a7f)\"\u0014X-\u00193Fq\u0016\u001cW\u000f^8s)\tyv\u0010\u0003\u0004\u0002\u00021\u0001\rAU\u0001\u000bi\"\u0014X-\u00193OC6,\u0017!\u000f8fo\u0012\u000bW-\\8o'&tw\r\\3UQJ,\u0017\rZ#yK\u000e,Ho\u001c:XSRD'+\u001a6fGR,G-\u0012=fGV$\u0018n\u001c8IC:$G.\u001a:\u0015\u000f}\u000b9!!\u0003\u0002\u000e!1\u0011\u0011A\u0007A\u0002ICa!a\u0003\u000e\u0001\u00049\u0017!\u0005;bg.\fV/Z;f\u0007\u0006\u0004\u0018mY5us\"9\u0011qB\u0007A\u0002\u0005E\u0011\u0001\u0007:fU\u0016\u001cG/\u001a3Fq\u0016\u001cW\u000f^5p]\"\u000bg\u000e\u001a7feB\u0019q(a\u0005\n\u0007\u0005U\u0001I\u0001\rSK*,7\r^3e\u000bb,7-\u001e;j_:D\u0015M\u001c3mKJ\faE\\3x\t\u0006,Wn\u001c8TS:<G.\u001a+ie\u0016\fGmU2iK\u0012,H.\u001a3Fq\u0016\u001cW\u000f^8s)\u0011\tY\"!\t\u0011\u0007}\ni\"C\u0002\u0002 \u0001\u0013\u0001dU2iK\u0012,H.\u001a3Fq\u0016\u001cW\u000f^8s'\u0016\u0014h/[2f\u0011\u0019\t\tA\u0004a\u0001%\u0006\u0001c.Z<TS:<G.\u001a+ie\u0016\fGmU2iK\u0012,H.\u001a3Fq\u0016\u001cW\u000f^8s)\u0011\t9#!\f\u0011\u0007}\nI#C\u0002\u0002,\u0001\u00131dU2iK\u0012,H.\u001a3UQJ,\u0017\r\u001a)p_2,\u00050Z2vi>\u0014\bBBA\u0001\u001f\u0001\u0007!+\u0001\u0013oK^$\u0015-Z7p]RC'/Z1e!>|GnU2iK\u0012,H.\u001a3Fq\u0016\u001cW\u000f^8s)\u0019\tY\"a\r\u00028!1\u0011Q\u0007\tA\u0002I\u000b\u0001\u0003\u001e5sK\u0006$g*Y7f!J,g-\u001b=\t\r\u0005e\u0002\u00031\u0001h\u0003)qW/\u001c+ie\u0016\fGm]\u0001\u000feVt\u0017J\u001c(foRC'/Z1e+\u0011\ty$a\u0012\u0015\r\u0005\u0005\u00131MA3)\u0011\t\u0019%!\u0017\u0011\t\u0005\u0015\u0013q\t\u0007\u0001\t\u001d\tI%\u0005b\u0001\u0003\u0017\u0012\u0011\u0001V\t\u0005\u0003\u001b\n\u0019\u0006E\u0002,\u0003\u001fJ1!!\u0015-\u0005\u001dqu\u000e\u001e5j]\u001e\u00042aKA+\u0013\r\t9\u0006\f\u0002\u0004\u0003:L\b\u0002CA.#\u0011\u0005\r!!\u0018\u0002\t\t|G-\u001f\t\u0006W\u0005}\u00131I\u0005\u0004\u0003Cb#\u0001\u0003\u001fcs:\fW.\u001a \t\r\u0005\u0005\u0011\u00031\u0001S\u0011%\t9'\u0005I\u0001\u0002\u0004\tI'\u0001\u0005jg\u0012\u000bW-\\8o!\rY\u00131N\u0005\u0004\u0003[b#a\u0002\"p_2,\u0017M\\\u0001\u0019eVt\u0017J\u001c(foRC'/Z1eI\u0011,g-Y;mi\u0012\u0012T\u0003BA:\u0003o*\"!!\u001e+\u0007\u0005%t\u000eB\u0004\u0002JI\u0011\r!a\u0013\u0002)]\u0014\u0018\r]\"bY2,'o\u0015;bG.$(/Y2f+\u0011\ti(!!\u0015\u0011\u0005}\u0014qSAN\u0003?\u0003B!!\u0012\u0002\u0002\u00129\u0011\u0011J\nC\u0002\u0005\r\u0015\u0003BA'\u0003\u000b\u0003B!a\"\u0002\u0012:!\u0011\u0011RAG\u001d\r)\u00161R\u0005\u0002[%\u0019\u0011q\u0012\u0017\u0002\u000fA\f7m[1hK&!\u00111SAK\u0005%!\u0006N]8xC\ndWMC\u0002\u0002\u00102Bq!!'\u0014\u0001\u0004\ty(A\u0007sK\u0006dW\t_2faRLwN\u001c\u0005\t\u0003;\u001b\u0002\u0013!a\u0001%\u0006q1m\\7cS:,W*Z:tC\u001e,\u0007\u0002CAQ'A\u0005\t\u0019A4\u0002\u0015\u0011\u0014x\u000e]*uC\u000e\\7/\u0001\u0010xe\u0006\u00048)\u00197mKJ\u001cF/Y2liJ\f7-\u001a\u0013eK\u001a\fW\u000f\u001c;%eU!\u0011qUAV+\t\tIK\u000b\u0002S_\u00129\u0011\u0011\n\u000bC\u0002\u0005\r\u0015AH<sCB\u001c\u0015\r\u001c7feN#\u0018mY6ue\u0006\u001cW\r\n3fM\u0006,H\u000e\u001e\u00134+\ri\u0017\u0011\u0017\u0003\b\u0003\u0013*\"\u0019AAB\u0003=qWm\u001e$pe.Tu.\u001b8Q_>dGCBA\\\u0003{\u000by\fE\u0002@\u0003sK1!a/A\u000511uN]6K_&t\u0007k\\8m\u0011\u0015\tf\u00031\u0001S\u0011\u00151g\u00031\u0001h\u0003-\tw/Y5u%\u0016\u001cX\u000f\u001c;\u0016\t\u0005\u0015\u0017\u0011\u001a\u000b\u0007\u0003\u000f\fY-!6\u0011\t\u0005\u0015\u0013\u0011\u001a\u0003\b\u0003\u0013:\"\u0019AA&\u0011\u001d\tim\u0006a\u0001\u0003\u001f\f\u0011\"Y<bSR\f'\r\\3\u0011\u000bY\n\t.a2\n\u0007\u0005MwGA\u0005Bo\u0006LG/\u00192mK\"9\u0011q[\fA\u0002\u0005e\u0017AB1u\u001b>\u001cH\u000f\u0005\u0003\u0002\\\u0006\u0005XBAAo\u0015\r\tynN\u0001\tIV\u0014\u0018\r^5p]&!\u00111]Ao\u0005!!UO]1uS>t\u0007&B\f\u0002h\u0006U\b#B\u0016\u0002j\u00065\u0018bAAvY\t1A\u000f\u001b:poN\u0004B!a<\u0002r6\t\u0001%C\u0002\u0002t\u0002\u0012ab\u00159be.,\u0005pY3qi&|gn\t\u0002\u0002nV!\u0011\u0011`A\u007f)\u0019\tY0a@\u0003\nA!\u0011QIA\u007f\t\u001d\tI\u0005\u0007b\u0001\u0003\u0017BqA!\u0001\u0019\u0001\u0004\u0011\u0019!\u0001\u0004gkR,(/\u001a\t\u0006\u007f\t\u0015\u00111`\u0005\u0004\u0005\u000f\u0001%A\u0002$viV\u0014X\rC\u0004\u0002Xb\u0001\r!!7)\u000ba\t9/!>\u0002\u0015\u0005<\u0018-\u001b;SK\u0006$\u00170\u0006\u0003\u0003\u0012\tmAC\u0002B\n\u0005+\u0011iB\u0004\u0003\u0002F\tU\u0001bBAg3\u0001\u0007!q\u0003\t\u0006m\u0005E'\u0011\u0004\t\u0005\u0003\u000b\u0012Y\u0002B\u0004\u0002Je\u0011\r!a\u0013\t\u000f\u0005]\u0017\u00041\u0001\u0002Z\"*\u0011$a:\u0002v\u0006A1\u000f[;uI><h\u000e\u0006\u0004\u0003&\t-\"q\u0006\t\u0004W\t\u001d\u0012b\u0001B\u0015Y\t!QK\\5u\u0011\u0019\u0011iC\u0007a\u0001}\u0005AQ\r_3dkR|'\u000fC\u0005\u00032i\u0001\n\u00111\u0001\u0002Z\u0006YqM]1dKB+'/[8e\u0003I\u0019\b.\u001e;e_^tG\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\t]\"fAAm_\u00061\u0001/\u0019:nCB,bA!\u0010\u0003Z\t%C\u0003\u0003B \u0005;\u0012\u0019G!\u001a\u0015\t\t\u0005#Q\n\t\u0007\u0003\u000f\u0013\u0019Ea\u0012\n\t\t\u0015\u0013Q\u0013\u0002\u0004'\u0016\f\b\u0003BA#\u0005\u0013\"qAa\u0013\u001d\u0005\u0004\tYEA\u0001P\u0011\u001d\u0011y\u0005\ba\u0001\u0005#\n\u0011A\u001a\t\bW\tM#q\u000bB$\u0013\r\u0011)\u0006\f\u0002\n\rVt7\r^5p]F\u0002B!!\u0012\u0003Z\u00119!1\f\u000fC\u0002\u0005-#!A%\t\u000f\t}C\u00041\u0001\u0003b\u0005\u0011\u0011N\u001c\t\u0007\u0003\u000f\u0013\u0019Ea\u0016\t\u000bEc\u0002\u0019\u0001*\t\r\t\u001dD\u00041\u0001h\u0003)i\u0017\r\u001f+ie\u0016\fGm\u001d"
)
public final class ThreadUtils {
   public static Seq parmap(final Seq in, final String prefix, final int maxThreads, final Function1 f) {
      return ThreadUtils$.MODULE$.parmap(in, prefix, maxThreads, f);
   }

   public static Duration shutdown$default$2() {
      return ThreadUtils$.MODULE$.shutdown$default$2();
   }

   public static void shutdown(final ExecutorService executor, final Duration gracePeriod) {
      ThreadUtils$.MODULE$.shutdown(executor, gracePeriod);
   }

   public static Awaitable awaitReady(final Awaitable awaitable, final Duration atMost) throws SparkException {
      return ThreadUtils$.MODULE$.awaitReady(awaitable, atMost);
   }

   public static Object awaitResult(final Future future, final Duration atMost) throws SparkException {
      return ThreadUtils$.MODULE$.awaitResult(future, atMost);
   }

   public static Object awaitResult(final Awaitable awaitable, final Duration atMost) throws SparkException {
      return ThreadUtils$.MODULE$.awaitResult(awaitable, atMost);
   }

   public static ForkJoinPool newForkJoinPool(final String prefix, final int maxThreadNumber) {
      return ThreadUtils$.MODULE$.newForkJoinPool(prefix, maxThreadNumber);
   }

   public static int wrapCallerStacktrace$default$3() {
      return ThreadUtils$.MODULE$.wrapCallerStacktrace$default$3();
   }

   public static String wrapCallerStacktrace$default$2() {
      return ThreadUtils$.MODULE$.wrapCallerStacktrace$default$2();
   }

   public static Throwable wrapCallerStacktrace(final Throwable realException, final String combineMessage, final int dropStacks) {
      return ThreadUtils$.MODULE$.wrapCallerStacktrace(realException, combineMessage, dropStacks);
   }

   public static boolean runInNewThread$default$2() {
      return ThreadUtils$.MODULE$.runInNewThread$default$2();
   }

   public static Object runInNewThread(final String threadName, final boolean isDaemon, final Function0 body) {
      return ThreadUtils$.MODULE$.runInNewThread(threadName, isDaemon, body);
   }

   public static ScheduledExecutorService newDaemonThreadPoolScheduledExecutor(final String threadNamePrefix, final int numThreads) {
      return ThreadUtils$.MODULE$.newDaemonThreadPoolScheduledExecutor(threadNamePrefix, numThreads);
   }

   public static ScheduledThreadPoolExecutor newSingleThreadScheduledExecutor(final String threadName) {
      return ThreadUtils$.MODULE$.newSingleThreadScheduledExecutor(threadName);
   }

   public static ScheduledExecutorService newDaemonSingleThreadScheduledExecutor(final String threadName) {
      return ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor(threadName);
   }

   public static ThreadPoolExecutor newDaemonSingleThreadExecutorWithRejectedExecutionHandler(final String threadName, final int taskQueueCapacity, final RejectedExecutionHandler rejectedExecutionHandler) {
      return ThreadUtils$.MODULE$.newDaemonSingleThreadExecutorWithRejectedExecutionHandler(threadName, taskQueueCapacity, rejectedExecutionHandler);
   }

   public static ThreadPoolExecutor newDaemonSingleThreadExecutor(final String threadName) {
      return ThreadUtils$.MODULE$.newDaemonSingleThreadExecutor(threadName);
   }

   public static ThreadPoolExecutor newDaemonFixedThreadPool(final int nThreads, final String prefix) {
      return ThreadUtils$.MODULE$.newDaemonFixedThreadPool(nThreads, prefix);
   }

   public static int newDaemonCachedThreadPool$default$3() {
      return ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3();
   }

   public static ThreadPoolExecutor newDaemonCachedThreadPool(final String prefix, final int maxThreadNumber, final int keepAliveSeconds) {
      return ThreadUtils$.MODULE$.newDaemonCachedThreadPool(prefix, maxThreadNumber, keepAliveSeconds);
   }

   public static ThreadPoolExecutor newDaemonCachedThreadPool(final String prefix) {
      return ThreadUtils$.MODULE$.newDaemonCachedThreadPool(prefix);
   }

   public static ThreadFactory namedThreadFactory(final String prefix) {
      return ThreadUtils$.MODULE$.namedThreadFactory(prefix);
   }

   public static ExecutionContextExecutor sameThread() {
      return ThreadUtils$.MODULE$.sameThread();
   }

   public static ExecutorService sameThreadExecutorService() {
      return ThreadUtils$.MODULE$.sameThreadExecutorService();
   }
}
