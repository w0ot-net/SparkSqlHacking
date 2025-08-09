package org.apache.spark;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001\u0002\f\u0018\tzA\u0001\u0002\u000e\u0001\u0003\u0016\u0004%\t!\u000e\u0005\t}\u0001\u0011\t\u0012)A\u0005m!)q\b\u0001C\u0001\u0001\"9A\tAA\u0001\n\u0003)\u0005bB$\u0001#\u0003%\t\u0001\u0013\u0005\b'\u0002\t\t\u0011\"\u0011U\u0011\u001da\u0006!!A\u0005\u0002uCq!\u0019\u0001\u0002\u0002\u0013\u0005!\rC\u0004i\u0001\u0005\u0005I\u0011I5\t\u000fA\u0004\u0011\u0011!C\u0001c\"9a\u000fAA\u0001\n\u0003:\bbB=\u0001\u0003\u0003%\tE\u001f\u0005\bw\u0002\t\t\u0011\"\u0011}\u0011\u001di\b!!A\u0005By<\u0011\"!\u0001\u0018\u0003\u0003EI!a\u0001\u0007\u0011Y9\u0012\u0011!E\u0005\u0003\u000bAaa\u0010\t\u0005\u0002\u0005u\u0001bB>\u0011\u0003\u0003%)\u0005 \u0005\n\u0003?\u0001\u0012\u0011!CA\u0003CA\u0011\"!\n\u0011\u0003\u0003%\t)a\n\t\u0013\u0005M\u0002#!A\u0005\n\u0005U\"AE#yK\u000e,Ho\u001c:SK\u001eL7\u000f^3sK\u0012T!\u0001G\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005iY\u0012AB1qC\u000eDWMC\u0001\u001d\u0003\ry'oZ\u0002\u0001'\u0011\u0001q$\n\u0015\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g!\t\u0001c%\u0003\u0002(C\t9\u0001K]8ek\u000e$\bCA\u00152\u001d\tQsF\u0004\u0002,]5\tAF\u0003\u0002.;\u00051AH]8pizJ\u0011AI\u0005\u0003a\u0005\nq\u0001]1dW\u0006<W-\u0003\u00023g\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001'I\u0001\u000bKb,7-\u001e;pe&#W#\u0001\u001c\u0011\u0005]ZdB\u0001\u001d:!\tY\u0013%\u0003\u0002;C\u00051\u0001K]3eK\u001aL!\u0001P\u001f\u0003\rM#(/\u001b8h\u0015\tQ\u0014%A\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002B\u0007B\u0011!\tA\u0007\u0002/!)Ag\u0001a\u0001m\u0005!1m\u001c9z)\t\te\tC\u00045\tA\u0005\t\u0019\u0001\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0011J\u000b\u00027\u0015.\n1\n\u0005\u0002M#6\tQJ\u0003\u0002O\u001f\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003!\u0006\n!\"\u00198o_R\fG/[8o\u0013\t\u0011VJA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A+\u0011\u0005Y[V\"A,\u000b\u0005aK\u0016\u0001\u00027b]\u001eT\u0011AW\u0001\u0005U\u00064\u0018-\u0003\u0002=/\u0006a\u0001O]8ek\u000e$\u0018I]5usV\ta\f\u0005\u0002!?&\u0011\u0001-\t\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003G\u001a\u0004\"\u0001\t3\n\u0005\u0015\f#aA!os\"9q\rCA\u0001\u0002\u0004q\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001k!\rYgnY\u0007\u0002Y*\u0011Q.I\u0001\u000bG>dG.Z2uS>t\u0017BA8m\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005I,\bC\u0001\u0011t\u0013\t!\u0018EA\u0004C_>dW-\u00198\t\u000f\u001dT\u0011\u0011!a\u0001G\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\t)\u0006\u0010C\u0004h\u0017\u0005\u0005\t\u0019\u00010\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AX\u0001\ti>\u001cFO]5oOR\tQ+\u0001\u0004fcV\fGn\u001d\u000b\u0003e~Dqa\u001a\b\u0002\u0002\u0003\u00071-\u0001\nFq\u0016\u001cW\u000f^8s%\u0016<\u0017n\u001d;fe\u0016$\u0007C\u0001\"\u0011'\u0015\u0001\u0012qAA\n!\u0019\tI!a\u00047\u00036\u0011\u00111\u0002\u0006\u0004\u0003\u001b\t\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003#\tYAA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\u0006\u0002\u001c5\u0011\u0011q\u0003\u0006\u0004\u00033I\u0016AA5p\u0013\r\u0011\u0014q\u0003\u000b\u0003\u0003\u0007\tQ!\u00199qYf$2!QA\u0012\u0011\u0015!4\u00031\u00017\u0003\u001d)h.\u00199qYf$B!!\u000b\u00020A!\u0001%a\u000b7\u0013\r\ti#\t\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005EB#!AA\u0002\u0005\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u0004E\u0002W\u0003sI1!a\u000fX\u0005\u0019y%M[3di\u0002"
)
public class ExecutorRegistered implements Product, Serializable {
   private final String executorId;

   public static Option unapply(final ExecutorRegistered x$0) {
      return ExecutorRegistered$.MODULE$.unapply(x$0);
   }

   public static ExecutorRegistered apply(final String executorId) {
      return ExecutorRegistered$.MODULE$.apply(executorId);
   }

   public static Function1 andThen(final Function1 g) {
      return ExecutorRegistered$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ExecutorRegistered$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String executorId() {
      return this.executorId;
   }

   public ExecutorRegistered copy(final String executorId) {
      return new ExecutorRegistered(executorId);
   }

   public String copy$default$1() {
      return this.executorId();
   }

   public String productPrefix() {
      return "ExecutorRegistered";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.executorId();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ExecutorRegistered;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "executorId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof ExecutorRegistered) {
               label40: {
                  ExecutorRegistered var4 = (ExecutorRegistered)x$1;
                  String var10000 = this.executorId();
                  String var5 = var4.executorId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public ExecutorRegistered(final String executorId) {
      this.executorId = executorId;
      Product.$init$(this);
   }
}
