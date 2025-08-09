package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.scheduler.JobWaiter;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.collection.immutable.Seq;
import scala.concurrent.CanAwait;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.util.Try;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=f\u0001B\t\u0013\u0001eA\u0001\u0002\r\u0001\u0003\u0002\u0003\u0006I!\r\u0005\tw\u0001\u0011\t\u0011*A\u0005y!1q\b\u0001C\u0001%\u0001Cq\u0001\u0013\u0001A\u0002\u0013%\u0011\nC\u0004N\u0001\u0001\u0007I\u0011\u0002(\t\rQ\u0003\u0001\u0015)\u0003K\u0011\u0015I\u0006\u0001\"\u0011[\u0011\u0015Y\u0007\u0001\"\u0011m\u0011\u0019y\b\u0001\"\u0011\u0002\u0002!9\u0011Q\u0005\u0001\u0005B\u0005\u001d\u0002BBA*\u0001\u0011\u0005\u0013\n\u0003\u0004\u0002V\u0001!\t%\u0013\u0005\b\u0003/\u0002A\u0011IA-\u0011\u001d\ti\u0006\u0001C\u0001\u0003?Bq!!\u001c\u0001\t\u0003\ny\u0007C\u0004\u0002\u000e\u0002!\t%a$\u0003%MKW\u000e\u001d7f\rV$XO]3BGRLwN\u001c\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sO\u000e\u0001QC\u0001\u000e('\r\u00011$\t\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\t\u001aS%D\u0001\u0013\u0013\t!#C\u0001\u0007GkR,(/Z!di&|g\u000e\u0005\u0002'O1\u0001A!\u0002\u0015\u0001\u0005\u0004I#!\u0001+\u0012\u0005)j\u0003C\u0001\u000f,\u0013\taSDA\u0004O_RD\u0017N\\4\u0011\u0005qq\u0013BA\u0018\u001e\u0005\r\te._\u0001\nU>\u0014w+Y5uKJ\u0004$AM\u001d\u0011\u0007M2\u0004(D\u00015\u0015\t)$#A\u0005tG\",G-\u001e7fe&\u0011q\u0007\u000e\u0002\n\u0015>\u0014w+Y5uKJ\u0004\"AJ\u001d\u0005\u0013i\n\u0011\u0011!A\u0001\u0006\u0003I#aA0%c\u0005Q!/Z:vYR4UO\\2\u0011\u0007qiT%\u0003\u0002?;\tAAHY=oC6,g(\u0001\u0004=S:LGO\u0010\u000b\u0004\u0003\n;\u0005c\u0001\u0012\u0001K!)\u0001g\u0001a\u0001\u0007B\u0012AI\u0012\t\u0004gY*\u0005C\u0001\u0014G\t%Q$)!A\u0001\u0002\u000b\u0005\u0011\u0006\u0003\u0004<\u0007\u0011\u0005\r\u0001P\u0001\u000b?\u000e\fgnY3mY\u0016$W#\u0001&\u0011\u0005qY\u0015B\u0001'\u001e\u0005\u001d\u0011un\u001c7fC:\fabX2b]\u000e,G\u000e\\3e?\u0012*\u0017\u000f\u0006\u0002P%B\u0011A\u0004U\u0005\u0003#v\u0011A!\u00168ji\"91+BA\u0001\u0002\u0004Q\u0015a\u0001=%c\u0005YqlY1oG\u0016dG.\u001a3!Q\t1a\u000b\u0005\u0002\u001d/&\u0011\u0001,\b\u0002\tm>d\u0017\r^5mK\u000611-\u00198dK2$\"aT.\t\u000bq;\u0001\u0019A/\u0002\rI,\u0017m]8o!\rab\fY\u0005\u0003?v\u0011aa\u00149uS>t\u0007CA1i\u001d\t\u0011g\r\u0005\u0002d;5\tAM\u0003\u0002f1\u00051AH]8pizJ!aZ\u000f\u0002\rA\u0013X\rZ3g\u0013\tI'N\u0001\u0004TiJLgn\u001a\u0006\u0003Ov\tQA]3bIf$\"!\\<\u0015\u00059|W\"\u0001\u0001\t\u000bAD\u00019A9\u0002\rA,'/\\5u!\t\u0011X/D\u0001t\u0015\t!X$\u0001\u0006d_:\u001cWO\u001d:f]RL!A^:\u0003\u0011\r\u000bg.Q<bSRDQ\u0001\u001f\u0005A\u0002e\fa!\u0019;N_N$\bC\u0001>~\u001b\u0005Y(B\u0001?t\u0003!!WO]1uS>t\u0017B\u0001@|\u0005!!UO]1uS>t\u0017A\u0002:fgVdG\u000f\u0006\u0003\u0002\u0004\u0005\u001dAcA\u0013\u0002\u0006!)\u0001/\u0003a\u0002c\")\u00010\u0003a\u0001s\"*\u0011\"a\u0003\u0002$A)A$!\u0004\u0002\u0012%\u0019\u0011qB\u000f\u0003\rQD'o\\<t!\u0011\t\u0019\"!\b\u000f\t\u0005U\u0011\u0011\u0004\b\u0004G\u0006]\u0011\"\u0001\u0010\n\u0007\u0005mQ$A\u0004qC\u000e\\\u0017mZ3\n\t\u0005}\u0011\u0011\u0005\u0002\n\u000bb\u001cW\r\u001d;j_:T1!a\u0007\u001eG\t\t\t\"\u0001\u0006p]\u000e{W\u000e\u001d7fi\u0016,B!!\u000b\u0002PQ!\u00111FA\u001c)\ry\u0015Q\u0006\u0005\b\u0003_Q\u00019AA\u0019\u0003!)\u00070Z2vi>\u0014\bc\u0001:\u00024%\u0019\u0011QG:\u0003!\u0015CXmY;uS>t7i\u001c8uKb$\bbBA\u001d\u0015\u0001\u0007\u00111H\u0001\u0005MVt7\rE\u0004\u001d\u0003{\t\t%!\u0014\n\u0007\u0005}RDA\u0005Gk:\u001cG/[8ocA)\u00111IA%K5\u0011\u0011Q\t\u0006\u0004\u0003\u000fj\u0012\u0001B;uS2LA!a\u0013\u0002F\t\u0019AK]=\u0011\u0007\u0019\ny\u0005\u0002\u0004\u0002R)\u0011\r!\u000b\u0002\u0002+\u0006Y\u0011n]\"p[BdW\r^3e\u0003-I7oQ1oG\u0016dG.\u001a3\u0002\u000bY\fG.^3\u0016\u0005\u0005m\u0003\u0003\u0002\u000f_\u0003\u0003\naA[8c\u0013\u0012\u001cXCAA1!\u0019\t\u0019\"a\u0019\u0002h%!\u0011QMA\u0011\u0005\r\u0019V-\u001d\t\u00049\u0005%\u0014bAA6;\t\u0019\u0011J\u001c;\u0002\u0013Q\u0014\u0018M\\:g_JlW\u0003BA9\u0003{\"B!a\u001d\u0002\u0006R!\u0011QOAA!\u0015\u0011\u0018qOA>\u0013\r\tIh\u001d\u0002\u0007\rV$XO]3\u0011\u0007\u0019\ni\b\u0002\u0004\u0002\u0000=\u0011\r!\u000b\u0002\u0002'\"9\u00111Q\bA\u0004\u0005E\u0012!A3\t\u000f\u0005\u001du\u00021\u0001\u0002\n\u0006\ta\rE\u0004\u001d\u0003{\t\t%a#\u0011\r\u0005\r\u0013\u0011JA>\u00035!(/\u00198tM>\u0014XnV5uQV!\u0011\u0011SAM)\u0011\t\u0019*!(\u0015\t\u0005U\u00151\u0014\t\u0006e\u0006]\u0014q\u0013\t\u0004M\u0005eEABA@!\t\u0007\u0011\u0006C\u0004\u0002\u0004B\u0001\u001d!!\r\t\u000f\u0005\u001d\u0005\u00031\u0001\u0002 B9A$!\u0010\u0002B\u0005U\u0005f\u0001\u0001\u0002$B!\u0011QUAV\u001b\t\t9KC\u0002\u0002*J\t!\"\u00198o_R\fG/[8o\u0013\u0011\ti+a*\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5"
)
public class SimpleFutureAction implements FutureAction {
   private final JobWaiter jobWaiter;
   private final Function0 resultFunc;
   private volatile boolean _cancelled;

   public void cancel() {
      FutureAction.cancel$(this);
   }

   public Object get() throws SparkException {
      return FutureAction.get$(this);
   }

   public Future failed() {
      return Future.failed$(this);
   }

   public void foreach(final Function1 f, final ExecutionContext executor) {
      Future.foreach$(this, f, executor);
   }

   public Future transform(final Function1 s, final Function1 f, final ExecutionContext executor) {
      return Future.transform$(this, s, f, executor);
   }

   public Future map(final Function1 f, final ExecutionContext executor) {
      return Future.map$(this, f, executor);
   }

   public Future flatMap(final Function1 f, final ExecutionContext executor) {
      return Future.flatMap$(this, f, executor);
   }

   public Future flatten(final .less.colon.less ev) {
      return Future.flatten$(this, ev);
   }

   public Future filter(final Function1 p, final ExecutionContext executor) {
      return Future.filter$(this, p, executor);
   }

   public final Future withFilter(final Function1 p, final ExecutionContext executor) {
      return Future.withFilter$(this, p, executor);
   }

   public Future collect(final PartialFunction pf, final ExecutionContext executor) {
      return Future.collect$(this, pf, executor);
   }

   public Future recover(final PartialFunction pf, final ExecutionContext executor) {
      return Future.recover$(this, pf, executor);
   }

   public Future recoverWith(final PartialFunction pf, final ExecutionContext executor) {
      return Future.recoverWith$(this, pf, executor);
   }

   public Future zip(final Future that) {
      return Future.zip$(this, that);
   }

   public Future zipWith(final Future that, final Function2 f, final ExecutionContext executor) {
      return Future.zipWith$(this, that, f, executor);
   }

   public Future fallbackTo(final Future that) {
      return Future.fallbackTo$(this, that);
   }

   public Future mapTo(final ClassTag tag) {
      return Future.mapTo$(this, tag);
   }

   public Future andThen(final PartialFunction pf, final ExecutionContext executor) {
      return Future.andThen$(this, pf, executor);
   }

   private boolean _cancelled() {
      return this._cancelled;
   }

   private void _cancelled_$eq(final boolean x$1) {
      this._cancelled = x$1;
   }

   public void cancel(final Option reason) {
      this._cancelled_$eq(true);
      this.jobWaiter.cancel(reason);
   }

   public SimpleFutureAction ready(final Duration atMost, final CanAwait permit) {
      this.jobWaiter.completionFuture().ready(atMost, permit);
      return this;
   }

   public Object result(final Duration atMost, final CanAwait permit) throws Exception {
      this.jobWaiter.completionFuture().ready(atMost, permit);
      scala.Predef..MODULE$.assert(this.value().isDefined(), () -> "Future has not completed properly");
      return ((Try)this.value().get()).get();
   }

   public void onComplete(final Function1 func, final ExecutionContext executor) {
      this.jobWaiter.completionFuture().onComplete((x$1) -> func.apply(this.value().get()), executor);
   }

   public boolean isCompleted() {
      return this.jobWaiter.jobFinished();
   }

   public boolean isCancelled() {
      return this._cancelled();
   }

   public Option value() {
      return this.jobWaiter.completionFuture().value().map((res) -> res.map((x$2) -> this.resultFunc.apply()));
   }

   public Seq jobIds() {
      return (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{this.jobWaiter.jobId()}));
   }

   public Future transform(final Function1 f, final ExecutionContext e) {
      return this.jobWaiter.completionFuture().transform((u) -> (Try)f.apply(u.map((x$3) -> this.resultFunc.apply())), e);
   }

   public Future transformWith(final Function1 f, final ExecutionContext e) {
      return this.jobWaiter.completionFuture().transformWith((u) -> (Future)f.apply(u.map((x$4) -> this.resultFunc.apply())), e);
   }

   public SimpleFutureAction(final JobWaiter jobWaiter, final Function0 resultFunc) {
      this.jobWaiter = jobWaiter;
      this.resultFunc = resultFunc;
      Future.$init$(this);
      FutureAction.$init$(this);
      this._cancelled = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
