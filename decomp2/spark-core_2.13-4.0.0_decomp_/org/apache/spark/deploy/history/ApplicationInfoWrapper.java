package org.apache.spark.deploy.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.util.kvstore.KVIndex;
import scala.Option;
import scala.collection.immutable.List;
import scala.math.Ordering.Long.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005i4QAC\u0006\u0001\u0017UA\u0001\u0002\b\u0001\u0003\u0006\u0004%\tA\b\u0005\tS\u0001\u0011\t\u0011)A\u0005?!A!\u0006\u0001BC\u0002\u0013\u00051\u0006\u0003\u0005=\u0001\t\u0005\t\u0015!\u0003-\u0011\u0015i\u0004\u0001\"\u0001?\u0011\u0015\u0011\u0005\u0001\"\u0001D\u0011\u0015Y\u0007\u0001\"\u0001m\u0011\u0015!\b\u0001\"\u0001m\u0011\u0015A\b\u0001\"\u0001z\u0005Y\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8J]\u001a|wK]1qa\u0016\u0014(B\u0001\u0007\u000e\u0003\u001dA\u0017n\u001d;pefT!AD\b\u0002\r\u0011,\u0007\u000f\\8z\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7C\u0001\u0001\u0017!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fM\u0006!\u0011N\u001c4p\u0007\u0001)\u0012a\b\t\u0003A\u001dj\u0011!\t\u0006\u0003E\r\n!A^\u0019\u000b\u0005\u0011*\u0013aA1qS*\u0011aeD\u0001\u0007gR\fG/^:\n\u0005!\n#aD!qa2L7-\u0019;j_:LeNZ8\u0002\u000b%tgm\u001c\u0011\u0002\u0011\u0005$H/Z7qiN,\u0012\u0001\f\t\u0004[UBdB\u0001\u00184\u001d\ty#'D\u00011\u0015\t\tT$\u0001\u0004=e>|GOP\u0005\u00023%\u0011A\u0007G\u0001\ba\u0006\u001c7.Y4f\u0013\t1tG\u0001\u0003MSN$(B\u0001\u001b\u0019!\tI$(D\u0001\f\u0013\tY4B\u0001\nBiR,W\u000e\u001d;J]\u001a|wK]1qa\u0016\u0014\u0018!C1ui\u0016l\u0007\u000f^:!\u0003\u0019a\u0014N\\5u}Q\u0019q\bQ!\u0011\u0005e\u0002\u0001\"\u0002\u000f\u0006\u0001\u0004y\u0002\"\u0002\u0016\u0006\u0001\u0004a\u0013AA5e+\u0005!\u0005CA#J\u001d\t1u\t\u0005\u000201%\u0011\u0001\nG\u0001\u0007!J,G-\u001a4\n\u0005)[%AB*ue&twM\u0003\u0002I1!\u0012a!\u0014\t\u0003\u001d^k\u0011a\u0014\u0006\u0003!F\u000b!\"\u00198o_R\fG/[8o\u0015\t\u00116+A\u0004kC\u000e\\7o\u001c8\u000b\u0005Q+\u0016!\u00034bgR,'\u000f_7m\u0015\u00051\u0016aA2p[&\u0011\u0001l\u0014\u0002\u000b\u0015N|g.S4o_J,\u0007F\u0001\u0004[U\tY6\r\u0005\u0002]C6\tQL\u0003\u0002_?\u000691N^:u_J,'B\u00011\u0010\u0003\u0011)H/\u001b7\n\u0005\tl&aB&W\u0013:$W\r_\u0016\u0002IB\u0011Q-[\u0007\u0002M*\u0011q\r[\u0001\u0005[\u0016$\u0018M\u0003\u0002Q1%\u0011!N\u001a\u0002\u0007O\u0016$H/\u001a:\u0002\u000f\u0015tG\rV5nKR\tQ\u000e\u0005\u0002\u0018]&\u0011q\u000e\u0007\u0002\u0005\u0019>tw\r\u000b\u0002\b\u001b\"\"qA\u0017:t\u0003\u00151\u0018\r\\;fC\u0005Y\u0017!D8mI\u0016\u001cH/\u0011;uK6\u0004H\u000f\u000b\u0002\t\u001b\"\"\u0001B\u0017:xC\u0005!\u0018!\u0005;p\u0003B\u0004H.[2bi&|g.\u00138g_R\tq\u0004"
)
public class ApplicationInfoWrapper {
   private final ApplicationInfo info;
   private final List attempts;

   public ApplicationInfo info() {
      return this.info;
   }

   public List attempts() {
      return this.attempts;
   }

   @JsonIgnore
   @KVIndex
   public String id() {
      return this.info().id();
   }

   @JsonIgnore
   @KVIndex("endTime")
   public long endTime() {
      return ((AttemptInfoWrapper)this.attempts().head()).info().endTime().getTime();
   }

   @JsonIgnore
   @KVIndex("oldestAttempt")
   public long oldestAttempt() {
      return BoxesRunTime.unboxToLong(this.attempts().map((x$25) -> BoxesRunTime.boxToLong($anonfun$oldestAttempt$1(x$25))).min(.MODULE$));
   }

   public ApplicationInfo toApplicationInfo() {
      List x$1 = this.attempts().map((x$26) -> x$26.info());
      String x$2 = this.info().copy$default$1();
      String x$3 = this.info().copy$default$2();
      Option x$4 = this.info().copy$default$3();
      Option x$5 = this.info().copy$default$4();
      Option x$6 = this.info().copy$default$5();
      Option x$7 = this.info().copy$default$6();
      return this.info().copy(x$2, x$3, x$4, x$5, x$6, x$7, x$1);
   }

   // $FF: synthetic method
   public static final long $anonfun$oldestAttempt$1(final AttemptInfoWrapper x$25) {
      return x$25.info().lastUpdated().getTime();
   }

   public ApplicationInfoWrapper(final ApplicationInfo info, final List attempts) {
      this.info = info;
      this.attempts = attempts;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
