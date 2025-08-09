package org.apache.spark.rpc;

import java.io.Serializable;
import java.util.concurrent.TimeoutException;
import org.apache.spark.SparkConf;
import org.apache.spark.util.ThreadUtils$;
import scala.Function1;
import scala.PartialFunction;
import scala.collection.immutable.Seq;
import scala.concurrent.Awaitable;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub!\u0002\t\u0012\u0001MI\u0002\u0002C\u0017\u0001\u0005\u000b\u0007I\u0011\u0001\u0018\t\u0011Y\u0002!\u0011!Q\u0001\n=B\u0001b\u000e\u0001\u0003\u0006\u0004%\t\u0001\u000f\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005s!)!\t\u0001C\u0001\u0007\")\u0001\n\u0001C\u0005\u0013\")\u0001\f\u0001C\u00013\")A\u000e\u0001C\u0001[\u001e1q/\u0005E\u0001'a4a\u0001E\t\t\u0002MI\b\"\u0002\"\u000b\t\u0003y\bbBA\u0001\u0015\u0011\u0005\u00111\u0001\u0005\b\u0003\u0003QA\u0011AA\n\u0011\u001d\t\tA\u0003C\u0001\u0003;A\u0011\"!\f\u000b\u0003\u0003%I!a\f\u0003\u0015I\u00038\rV5nK>,HO\u0003\u0002\u0013'\u0005\u0019!\u000f]2\u000b\u0005Q)\u0012!B:qCJ\\'B\u0001\f\u0018\u0003\u0019\t\u0007/Y2iK*\t\u0001$A\u0002pe\u001e\u001c2\u0001\u0001\u000e!!\tYb$D\u0001\u001d\u0015\u0005i\u0012!B:dC2\f\u0017BA\u0010\u001d\u0005\u0019\te.\u001f*fMB\u0011\u0011E\u000b\b\u0003E!r!aI\u0014\u000e\u0003\u0011R!!\n\u0014\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!H\u0005\u0003Sq\tq\u0001]1dW\u0006<W-\u0003\u0002,Y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011\u0006H\u0001\tIV\u0014\u0018\r^5p]V\tq\u0006\u0005\u00021i5\t\u0011G\u0003\u0002.e)\u00111\u0007H\u0001\u000bG>t7-\u001e:sK:$\u0018BA\u001b2\u000591\u0015N\\5uK\u0012+(/\u0019;j_:\f\u0011\u0002Z;sCRLwN\u001c\u0011\u0002\u0017QLW.Z8viB\u0013x\u000e]\u000b\u0002sA\u0011!H\u0010\b\u0003wq\u0002\"a\t\u000f\n\u0005ub\u0012A\u0002)sK\u0012,g-\u0003\u0002@\u0001\n11\u000b\u001e:j]\u001eT!!\u0010\u000f\u0002\u0019QLW.Z8viB\u0013x\u000e\u001d\u0011\u0002\rqJg.\u001b;?)\r!ei\u0012\t\u0003\u000b\u0002i\u0011!\u0005\u0005\u0006[\u0015\u0001\ra\f\u0005\u0006o\u0015\u0001\r!O\u0001\u001aGJ,\u0017\r^3Sa\u000e$\u0016.\\3pkR,\u0005pY3qi&|g\u000e\u0006\u0002K\u001bB\u0011QiS\u0005\u0003\u0019F\u00111C\u00159d)&lWm\\;u\u000bb\u001cW\r\u001d;j_:DQA\u0014\u0004A\u0002=\u000b!\u0001^3\u0011\u0005A3V\"A)\u000b\u0005M\u0012&BA*U\u0003\u0011)H/\u001b7\u000b\u0003U\u000bAA[1wC&\u0011q+\u0015\u0002\u0011)&lWm\\;u\u000bb\u001cW\r\u001d;j_:\f1#\u00193e\u001b\u0016\u001c8/Y4f\u0013\u001a$\u0016.\\3pkR,\"AW2\u0016\u0003m\u0003Ba\u0007/_C&\u0011Q\f\b\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]B\u0011\u0011eX\u0005\u0003A2\u0012\u0011\u0002\u00165s_^\f'\r\\3\u0011\u0005\t\u001cG\u0002\u0001\u0003\u0006I\u001e\u0011\r!\u001a\u0002\u0002)F\u0011a-\u001b\t\u00037\u001dL!\u0001\u001b\u000f\u0003\u000f9{G\u000f[5oOB\u00111D[\u0005\u0003Wr\u00111!\u00118z\u0003-\tw/Y5u%\u0016\u001cX\u000f\u001c;\u0016\u00059\u0004HCA8r!\t\u0011\u0007\u000fB\u0003e\u0011\t\u0007Q\rC\u0003s\u0011\u0001\u00071/\u0001\u0004gkR,(/\u001a\t\u0004iV|W\"\u0001\u001a\n\u0005Y\u0014$A\u0002$viV\u0014X-\u0001\u0006Sa\u000e$\u0016.\\3pkR\u0004\"!\u0012\u0006\u0014\u0007)Q\"\u0010\u0005\u0002|}6\tAP\u0003\u0002~)\u0006\u0011\u0011n\\\u0005\u0003Wq$\u0012\u0001_\u0001\u0006CB\u0004H.\u001f\u000b\u0006\t\u0006\u0015\u0011\u0011\u0003\u0005\b\u0003\u000fa\u0001\u0019AA\u0005\u0003\u0011\u0019wN\u001c4\u0011\t\u0005-\u0011QB\u0007\u0002'%\u0019\u0011qB\n\u0003\u0013M\u0003\u0018M]6D_:4\u0007\"B\u001c\r\u0001\u0004IDc\u0002#\u0002\u0016\u0005]\u0011\u0011\u0004\u0005\b\u0003\u000fi\u0001\u0019AA\u0005\u0011\u00159T\u00021\u0001:\u0011\u0019\tY\"\u0004a\u0001s\u0005aA-\u001a4bk2$h+\u00197vKR9A)a\b\u0002\"\u0005-\u0002bBA\u0004\u001d\u0001\u0007\u0011\u0011\u0002\u0005\b\u0003Gq\u0001\u0019AA\u0013\u0003=!\u0018.\\3pkR\u0004&o\u001c9MSN$\b\u0003B\u0011\u0002(eJ1!!\u000b-\u0005\r\u0019V-\u001d\u0005\u0007\u00037q\u0001\u0019A\u001d\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005E\u0002\u0003BA\u001a\u0003si!!!\u000e\u000b\u0007\u0005]B+\u0001\u0003mC:<\u0017\u0002BA\u001e\u0003k\u0011aa\u00142kK\u000e$\b"
)
public class RpcTimeout implements Serializable {
   private final FiniteDuration duration;
   private final String timeoutProp;

   public static RpcTimeout apply(final SparkConf conf, final Seq timeoutPropList, final String defaultValue) {
      return RpcTimeout$.MODULE$.apply(conf, timeoutPropList, defaultValue);
   }

   public static RpcTimeout apply(final SparkConf conf, final String timeoutProp, final String defaultValue) {
      return RpcTimeout$.MODULE$.apply(conf, timeoutProp, defaultValue);
   }

   public static RpcTimeout apply(final SparkConf conf, final String timeoutProp) {
      return RpcTimeout$.MODULE$.apply(conf, timeoutProp);
   }

   public FiniteDuration duration() {
      return this.duration;
   }

   public String timeoutProp() {
      return this.timeoutProp;
   }

   public RpcTimeoutException org$apache$spark$rpc$RpcTimeout$$createRpcTimeoutException(final TimeoutException te) {
      return new RpcTimeoutException(te.getMessage() + ". This timeout is controlled by " + this.timeoutProp(), te);
   }

   public PartialFunction addMessageIfTimeout() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final RpcTimeout $outer;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (x1 instanceof RpcTimeoutException var5) {
               throw var5;
            } else if (x1 instanceof TimeoutException var6) {
               throw this.$outer.org$apache$spark$rpc$RpcTimeout$$createRpcTimeoutException(var6);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            if (x1 instanceof RpcTimeoutException) {
               return true;
            } else {
               return x1 instanceof TimeoutException;
            }
         }

         public {
            if (RpcTimeout.this == null) {
               throw null;
            } else {
               this.$outer = RpcTimeout.this;
            }
         }
      };
   }

   public Object awaitResult(final Future future) {
      Object var10000;
      try {
         var10000 = ThreadUtils$.MODULE$.awaitResult((Awaitable)future, this.duration());
      } catch (Throwable var4) {
         PartialFunction catchExpr$1 = this.addMessageIfTimeout();
         if (!catchExpr$1.isDefinedAt(var4)) {
            throw var4;
         }

         var10000 = catchExpr$1.apply(var4);
      }

      return var10000;
   }

   public RpcTimeout(final FiniteDuration duration, final String timeoutProp) {
      this.duration = duration;
      this.timeoutProp = timeoutProp;
   }
}
