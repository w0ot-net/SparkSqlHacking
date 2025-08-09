package org.apache.spark.status.api.v1;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function1;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114\u0001\u0002C\u0005\u0011\u0002\u0007\u0005\u0011\"\u0006\u0005\u0006A\u0001!\tA\t\u0005\nM\u0001\u0001\r\u00111Q\u0005\u0012\u001dB\u0011\u0002\u0011\u0001A\u0002\u0003\u0007K\u0011C!\t\u0013\u0011\u0003\u0001\u0019!a!\n#9\u0003\"C$\u0001\u0001\u0004\u0005\r\u0015\"\u0005I\u0011\u0015Q\u0005\u0001\"\u0005L\u0011\u0015\u0019\u0007\u0001\"\u0005#\u0005=\u0011\u0015m]3BaB\u0014Vm]8ve\u000e,'B\u0001\u0006\f\u0003\t1\u0018G\u0003\u0002\r\u001b\u0005\u0019\u0011\r]5\u000b\u00059y\u0011AB:uCR,8O\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h'\r\u0001a\u0003\b\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005uqR\"A\u0005\n\u0005}I!!E!qSJ+\u0017/^3ti\u000e{g\u000e^3yi\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001$!\t9B%\u0003\u0002&1\t!QK\\5u\u0003\u0015\t\u0007\u000f]%e+\u0005A\u0003CA\u00151\u001d\tQc\u0006\u0005\u0002,15\tAF\u0003\u0002.C\u00051AH]8pizJ!a\f\r\u0002\rA\u0013X\rZ3g\u0013\t\t$G\u0001\u0004TiJLgn\u001a\u0006\u0003_aACA\u0001\u001b?\u007fA\u0011Q\u0007P\u0007\u0002m)\u0011q\u0007O\u0001\u0003eNT!!\u000f\u001e\u0002\u0005]\u001c(\"A\u001e\u0002\u000f)\f7.\u0019:uC&\u0011QH\u000e\u0002\n!\u0006$\b\u000eU1sC6\fQA^1mk\u0016\f\u0013AJ\u0001\nCB\u0004\u0018\nZ0%KF$\"a\t\"\t\u000f\r\u001b\u0011\u0011!a\u0001Q\u0005\u0019\u0001\u0010J\u0019\u0002\u0013\u0005$H/Z7qi&#\u0007\u0006\u0002\u00035}\u0019\u000b\u0013\u0001R\u0001\u000eCR$X-\u001c9u\u0013\u0012|F%Z9\u0015\u0005\rJ\u0005bB\"\u0006\u0003\u0003\u0005\r\u0001K\u0001\u0007o&$\b.V%\u0016\u00051{ECA'Y!\tqu\n\u0004\u0001\u0005\u000bA3!\u0019A)\u0003\u0003Q\u000b\"AU+\u0011\u0005]\u0019\u0016B\u0001+\u0019\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0006,\n\u0005]C\"aA!os\")\u0011L\u0002a\u00015\u0006\u0011aM\u001c\t\u0005/mkV*\u0003\u0002]1\tIa)\u001e8di&|g.\r\t\u0003=\u0006l\u0011a\u0018\u0006\u0003A>\t!!^5\n\u0005\t|&aB*qCJ\\W+S\u0001\u0017G\",7m[+J-&,w\u000fU3s[&\u001c8/[8og\u0002"
)
public interface BaseAppResource extends ApiRequestContext {
   String appId();

   void appId_$eq(final String x$1);

   String attemptId();

   void attemptId_$eq(final String x$1);

   // $FF: synthetic method
   static Object withUI$(final BaseAppResource $this, final Function1 fn) {
      return $this.withUI(fn);
   }

   default Object withUI(final Function1 fn) {
      try {
         return this.uiRoot().withSparkUI(this.appId(), .MODULE$.apply(this.attemptId()), (ui) -> {
            String user = this.httpRequest().getRemoteUser();
            if (!ui.securityManager().checkUIViewPermissions(user)) {
               throw new ForbiddenException("user \"" + user + "\" is not authorized");
            } else {
               return fn.apply(ui);
            }
         });
      } catch (NoSuchElementException var3) {
         String appKey = (String).MODULE$.apply(this.attemptId()).map((x$5) -> {
            String var10000 = this.appId();
            return var10000 + "/" + x$5;
         }).getOrElse(() -> this.appId());
         throw new NotFoundException("no such app: " + appKey);
      }
   }

   // $FF: synthetic method
   static void checkUIViewPermissions$(final BaseAppResource $this) {
      $this.checkUIViewPermissions();
   }

   default void checkUIViewPermissions() {
      try {
         String user = this.httpRequest().getRemoteUser();
         if (!this.uiRoot().checkUIViewPermissions(this.appId(), .MODULE$.apply(this.attemptId()), user)) {
            throw new ForbiddenException("user \"" + user + "\" is not authorized");
         }
      } catch (NoSuchElementException var3) {
         String appKey = (String).MODULE$.apply(this.attemptId()).map((x$6) -> {
            String var10000 = this.appId();
            return var10000 + "/" + x$6;
         }).getOrElse(() -> this.appId());
         throw new NotFoundException("no such app: " + appKey);
      }
   }

   static void $init$(final BaseAppResource $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
