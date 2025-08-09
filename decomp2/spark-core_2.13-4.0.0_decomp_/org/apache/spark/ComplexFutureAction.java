package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeoutException;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.rdd.RDD;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.concurrent.CanAwait;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import scala.concurrent.duration.Duration;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005uh\u0001\u0002\f\u0018\u0001yA\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\u0006\u0005\u0002!\ta\u0011\u0005\b\r\u0002\u0001\r\u0011\"\u0003H\u0011\u001dY\u0005\u00011A\u0005\n1CaA\u0015\u0001!B\u0013A\u0005bB,\u0001\u0001\u0004%I\u0001\u0017\u0005\bY\u0002\u0001\r\u0011\"\u0003n\u0011\u0019Q\u0007\u0001)Q\u00053\"9A\u000f\u0001b\u0001\n\u0013)\bBB=\u0001A\u0003%a\u000fC\u0003{\u0001\u0011\u00053\u0010C\u0004\u0002\u0014\u0001!I!!\u0006\t\r\u0005u\u0001\u0001\"\u0011H\u0011\u001d\ty\u0002\u0001C!\u0003CAq!a\u001a\u0001\t\u0003\nI\u0007C\u0004\u0002~\u0001!\t%a \t\r\u0005\u001d\u0006\u0001\"\u0011H\u0011\u001d\tI\u000b\u0001C!\u0003WCq!a,\u0001\t\u0003\t\t\fC\u0004\u0002@\u0002!\t%!1\t\u000f\u0005m\u0007\u0001\"\u0011\u0002^\n\u00192i\\7qY\u0016Dh)\u001e;ve\u0016\f5\r^5p]*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0005}a3c\u0001\u0001!MA\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\u00042a\n\u0015+\u001b\u00059\u0012BA\u0015\u0018\u000511U\u000f^;sK\u0006\u001bG/[8o!\tYC\u0006\u0004\u0001\u0005\u000b5\u0002!\u0019\u0001\u0018\u0003\u0003Q\u000b\"a\f\u001a\u0011\u0005\u0005\u0002\u0014BA\u0019#\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!I\u001a\n\u0005Q\u0012#aA!os\u0006\u0019!/\u001e8\u0011\t\u0005:\u0014\bP\u0005\u0003q\t\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0005\u001dR\u0014BA\u001e\u0018\u00051QuNY*vE6LG\u000f^3s!\ri\u0004IK\u0007\u0002})\u0011qHI\u0001\u000bG>t7-\u001e:sK:$\u0018BA!?\u0005\u00191U\u000f^;sK\u00061A(\u001b8jiz\"\"\u0001R#\u0011\u0007\u001d\u0002!\u0006C\u00036\u0005\u0001\u0007a'\u0001\u0006`G\u0006t7-\u001a7mK\u0012,\u0012\u0001\u0013\t\u0003C%K!A\u0013\u0012\u0003\u000f\t{w\u000e\\3b]\u0006qqlY1oG\u0016dG.\u001a3`I\u0015\fHCA'Q!\t\tc*\u0003\u0002PE\t!QK\\5u\u0011\u001d\tF!!AA\u0002!\u000b1\u0001\u001f\u00132\u0003-y6-\u00198dK2dW\r\u001a\u0011)\u0005\u0015!\u0006CA\u0011V\u0013\t1&E\u0001\u0005w_2\fG/\u001b7f\u0003)\u0019XOY!di&|gn]\u000b\u00023B\u0019!LY3\u000f\u0005m\u0003gB\u0001/`\u001b\u0005i&B\u00010\u001e\u0003\u0019a$o\\8u}%\t1%\u0003\u0002bE\u00059\u0001/Y2lC\u001e,\u0017BA2e\u0005\u0011a\u0015n\u001d;\u000b\u0005\u0005\u0014\u0003G\u00014i!\r9\u0003f\u001a\t\u0003W!$\u0011\"\u001b\u0005\u0002\u0002\u0003\u0005)\u0011\u0001\u0018\u0003\u0007}##'A\u0006tk\n\f5\r^5p]N\u0004\u0003F\u0001\u0005U\u00039\u0019XOY!di&|gn]0%KF$\"!\u00148\t\u000fE;\u0011\u0011!a\u0001_B\u0019!L\u001991\u0005E\u001c\bcA\u0014)eB\u00111f\u001d\u0003\nS:\f\t\u0011!A\u0003\u00029\n\u0011\u0001]\u000b\u0002mB\u0019Qh\u001e\u0016\n\u0005at$a\u0002)s_6L7/Z\u0001\u0003a\u0002\naaY1oG\u0016dGCA'}\u0011\u0015i8\u00021\u0001\u007f\u0003\u0019\u0011X-Y:p]B!\u0011e`A\u0002\u0013\r\t\tA\t\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\u0005\u0015\u0011Q\u0002\b\u0005\u0003\u000f\tI\u0001\u0005\u0002]E%\u0019\u00111\u0002\u0012\u0002\rA\u0013X\rZ3g\u0013\u0011\ty!!\u0005\u0003\rM#(/\u001b8h\u0015\r\tYAI\u0001\rU>\u00147+\u001e2nSR$XM]\u000b\u0003\u0003/\u0011B!!\u0007!s\u00191\u00111\u0004\u0007\u0001\u0003/\u0011A\u0002\u0010:fM&tW-\\3oiz\n1\"[:DC:\u001cW\r\u001c7fI\u0006)!/Z1esR!\u00111EA\u0019)\u0011\t)#a\n\u000e\u0003\u0001Aq!!\u000b\u000f\u0001\b\tY#\u0001\u0004qKJl\u0017\u000e\u001e\t\u0004{\u00055\u0012bAA\u0018}\tA1)\u00198Bo\u0006LG\u000fC\u0004\u000249\u0001\r!!\u000e\u0002\r\u0005$Xj\\:u!\u0011\t9$!\u0010\u000e\u0005\u0005e\"bAA\u001e}\u0005AA-\u001e:bi&|g.\u0003\u0003\u0002@\u0005e\"\u0001\u0003#ve\u0006$\u0018n\u001c8)\u000b9\t\u0019%a\u0014\u0011\u000b\u0005\n)%!\u0013\n\u0007\u0005\u001d#E\u0001\u0004uQJ|wo\u001d\t\u00045\u0006-\u0013bAA'I\n!\u0012J\u001c;feJ,\b\u000f^3e\u000bb\u001cW\r\u001d;j_:\u001c#!!\u0013)\u000b9\t\u0019&!\u001a\u0011\u000b\u0005\n)%!\u0016\u0011\t\u0005]\u0013q\f\b\u0005\u00033\niFD\u0002\\\u00037J!a\u0010\u0012\n\u0005\u0005t\u0014\u0002BA1\u0003G\u0012\u0001\u0003V5nK>,H/\u0012=dKB$\u0018n\u001c8\u000b\u0005\u0005t4EAA+\u0003\u0019\u0011Xm];miR!\u00111NA8)\rQ\u0013Q\u000e\u0005\b\u0003Sy\u00019AA\u0016\u0011\u001d\t\u0019d\u0004a\u0001\u0003kASaDA:\u0003w\u0002R!IA#\u0003k\u00022AWA<\u0013\r\tI\b\u001a\u0002\n\u000bb\u001cW\r\u001d;j_:\u001c#!!\u001e\u0002\u0015=t7i\\7qY\u0016$X-\u0006\u0003\u0002\u0002\u0006\rF\u0003BAB\u0003\u001f#2!TAC\u0011\u001d\t9\t\u0005a\u0002\u0003\u0013\u000b\u0001\"\u001a=fGV$xN\u001d\t\u0004{\u0005-\u0015bAAG}\t\u0001R\t_3dkRLwN\\\"p]R,\u0007\u0010\u001e\u0005\b\u0003#\u0003\u0002\u0019AAJ\u0003\u00111WO\\2\u0011\r\u0005:\u0014QSAQ!\u0015\t9*!(+\u001b\t\tIJC\u0002\u0002\u001c\n\nA!\u001e;jY&!\u0011qTAM\u0005\r!&/\u001f\t\u0004W\u0005\rFABAS!\t\u0007aFA\u0001V\u0003-I7oQ8na2,G/\u001a3\u0002\u000bY\fG.^3\u0016\u0005\u00055\u0006\u0003B\u0011\u0000\u0003+\u000baA[8c\u0013\u0012\u001cXCAAZ!\u0015Q\u0016QWA]\u0013\r\t9\f\u001a\u0002\u0004'\u0016\f\bcA\u0011\u0002<&\u0019\u0011Q\u0018\u0012\u0003\u0007%sG/A\u0005ue\u0006t7OZ8s[V!\u00111YAf)\u0011\t)-a5\u0015\t\u0005\u001d\u0017q\u001a\t\u0005{\u0001\u000bI\rE\u0002,\u0003\u0017$a!!4\u0015\u0005\u0004q#!A*\t\u000f\u0005EG\u0003q\u0001\u0002\n\u0006\tQ\rC\u0004\u0002VR\u0001\r!a6\u0002\u0003\u0019\u0004b!I\u001c\u0002\u0016\u0006e\u0007CBAL\u0003;\u000bI-A\u0007ue\u0006t7OZ8s[^KG\u000f[\u000b\u0005\u0003?\f9\u000f\u0006\u0003\u0002b\u0006-H\u0003BAr\u0003S\u0004B!\u0010!\u0002fB\u00191&a:\u0005\r\u00055WC1\u0001/\u0011\u001d\t\t.\u0006a\u0002\u0003\u0013Cq!!6\u0016\u0001\u0004\ti\u000f\u0005\u0004\"o\u0005U\u00151\u001d\u0015\u0004\u0001\u0005E\b\u0003BAz\u0003sl!!!>\u000b\u0007\u0005]x#\u0001\u0006b]:|G/\u0019;j_:LA!a?\u0002v\naA)\u001a<fY>\u0004XM]!qS\u0002"
)
public class ComplexFutureAction implements FutureAction {
   private volatile boolean _cancelled;
   private volatile List org$apache$spark$ComplexFutureAction$$subActions;
   private final Promise p;

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

   public List org$apache$spark$ComplexFutureAction$$subActions() {
      return this.org$apache$spark$ComplexFutureAction$$subActions;
   }

   public void org$apache$spark$ComplexFutureAction$$subActions_$eq(final List x$1) {
      this.org$apache$spark$ComplexFutureAction$$subActions = x$1;
   }

   private Promise p() {
      return this.p;
   }

   public synchronized void cancel(final Option reason) {
      this._cancelled_$eq(true);
      this.p().tryFailure(new SparkException("Action has been cancelled"));
      this.org$apache$spark$ComplexFutureAction$$subActions().foreach((x$5) -> {
         $anonfun$cancel$1(reason, x$5);
         return BoxedUnit.UNIT;
      });
   }

   private JobSubmitter jobSubmitter() {
      return new JobSubmitter() {
         // $FF: synthetic field
         private final ComplexFutureAction $outer;

         public FutureAction submitJob(final RDD rdd, final Function1 processPartition, final Seq partitions, final Function2 resultHandler, final Function0 resultFunc) {
            synchronized(this.$outer){}

            SimpleFutureAction var7;
            try {
               if (this.$outer.isCancelled()) {
                  throw new SparkException("Action has been cancelled");
               }

               SimpleFutureAction job = rdd.context().submitJob(rdd, processPartition, partitions, resultHandler, resultFunc);
               this.$outer.org$apache$spark$ComplexFutureAction$$subActions_$eq(this.$outer.org$apache$spark$ComplexFutureAction$$subActions().$colon$colon(job));
               var7 = job;
            } catch (Throwable var11) {
               throw var11;
            }

            return var7;
         }

         public {
            if (ComplexFutureAction.this == null) {
               throw null;
            } else {
               this.$outer = ComplexFutureAction.this;
            }
         }
      };
   }

   public boolean isCancelled() {
      return this._cancelled();
   }

   public ComplexFutureAction ready(final Duration atMost, final CanAwait permit) throws InterruptedException, TimeoutException {
      this.p().future().ready(atMost, permit);
      return this;
   }

   public Object result(final Duration atMost, final CanAwait permit) throws Exception {
      return this.p().future().result(atMost, permit);
   }

   public void onComplete(final Function1 func, final ExecutionContext executor) {
      this.p().future().onComplete(func, executor);
   }

   public boolean isCompleted() {
      return this.p().isCompleted();
   }

   public Option value() {
      return this.p().future().value();
   }

   public Seq jobIds() {
      return this.org$apache$spark$ComplexFutureAction$$subActions().flatMap((x$6) -> x$6.jobIds());
   }

   public Future transform(final Function1 f, final ExecutionContext e) {
      return this.p().future().transform(f, e);
   }

   public Future transformWith(final Function1 f, final ExecutionContext e) {
      return this.p().future().transformWith(f, e);
   }

   // $FF: synthetic method
   public static final void $anonfun$cancel$1(final Option reason$1, final FutureAction x$5) {
      x$5.cancel(reason$1);
   }

   public ComplexFutureAction(final Function1 run) {
      Future.$init$(this);
      FutureAction.$init$(this);
      this._cancelled = false;
      this.org$apache$spark$ComplexFutureAction$$subActions = scala.collection.immutable.Nil..MODULE$;
      this.p = scala.concurrent.Promise..MODULE$.apply().completeWith((Future)run.apply(this.jobSubmitter()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
