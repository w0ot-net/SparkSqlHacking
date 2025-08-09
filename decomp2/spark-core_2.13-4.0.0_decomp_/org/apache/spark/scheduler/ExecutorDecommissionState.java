package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=d!B\u000e\u001d\u0001r!\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011\u0001\u0003!\u0011#Q\u0001\nuB\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0007\")q\n\u0001C\u0001!\"9Q\u000bAA\u0001\n\u00031\u0006bB-\u0001#\u0003%\tA\u0017\u0005\bK\u0002\t\n\u0011\"\u0001g\u0011\u001dA\u0007!!A\u0005B%Dq!\u001d\u0001\u0002\u0002\u0013\u0005!\u000fC\u0004w\u0001\u0005\u0005I\u0011A<\t\u000fu\u0004\u0011\u0011!C!}\"I\u00111\u0002\u0001\u0002\u0002\u0013\u0005\u0011Q\u0002\u0005\n\u0003/\u0001\u0011\u0011!C!\u00033A\u0011\"!\b\u0001\u0003\u0003%\t%a\b\t\u0013\u0005\u0005\u0002!!A\u0005B\u0005\r\u0002\"CA\u0013\u0001\u0005\u0005I\u0011IA\u0014\u000f)\tY\u0003HA\u0001\u0012\u0003a\u0012Q\u0006\u0004\n7q\t\t\u0011#\u0001\u001d\u0003_AaaT\n\u0005\u0002\u0005\u001d\u0003\"CA\u0011'\u0005\u0005IQIA\u0012\u0011%\tIeEA\u0001\n\u0003\u000bY\u0005\u0003\u0005\u0002RM\t\n\u0011\"\u0001g\u0011%\t\u0019fEA\u0001\n\u0003\u000b)\u0006\u0003\u0005\u0002dM\t\n\u0011\"\u0001g\u0011%\t)gEA\u0001\n\u0013\t9GA\rFq\u0016\u001cW\u000f^8s\t\u0016\u001cw.\\7jgNLwN\\*uCR,'BA\u000f\u001f\u0003%\u00198\r[3ek2,'O\u0003\u0002 A\u0005)1\u000f]1sW*\u0011\u0011EI\u0001\u0007CB\f7\r[3\u000b\u0003\r\n1a\u001c:h'\u0011\u0001Qe\u000b\u0018\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g!\t1C&\u0003\u0002.O\t9\u0001K]8ek\u000e$\bCA\u00189\u001d\t\u0001dG\u0004\u00022k5\t!G\u0003\u00024i\u00051AH]8piz\u001a\u0001!C\u0001)\u0013\t9t%A\u0004qC\u000e\\\u0017mZ3\n\u0005eR$\u0001D*fe&\fG.\u001b>bE2,'BA\u001c(\u0003%\u0019H/\u0019:u)&lW-F\u0001>!\t1c(\u0003\u0002@O\t!Aj\u001c8h\u0003)\u0019H/\u0019:u)&lW\rI\u0001\u000bo>\u00148.\u001a:I_N$X#A\"\u0011\u0007\u0019\"e)\u0003\u0002FO\t1q\n\u001d;j_:\u0004\"aR&\u000f\u0005!K\u0005CA\u0019(\u0013\tQu%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&(\u0003-9xN]6fe\"{7\u000f\u001e\u0011\u0002\rqJg.\u001b;?)\r\t6\u000b\u0016\t\u0003%\u0002i\u0011\u0001\b\u0005\u0006w\u0015\u0001\r!\u0010\u0005\b\u0003\u0016\u0001\n\u00111\u0001D\u0003\u0011\u0019w\u000e]=\u0015\u0007E;\u0006\fC\u0004<\rA\u0005\t\u0019A\u001f\t\u000f\u00053\u0001\u0013!a\u0001\u0007\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A.+\u0005ub6&A/\u0011\u0005y\u001bW\"A0\u000b\u0005\u0001\f\u0017!C;oG\",7m[3e\u0015\t\u0011w%\u0001\u0006b]:|G/\u0019;j_:L!\u0001Z0\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003\u001dT#a\u0011/\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Q\u0007CA6q\u001b\u0005a'BA7o\u0003\u0011a\u0017M\\4\u000b\u0003=\fAA[1wC&\u0011A\n\\\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002gB\u0011a\u0005^\u0005\u0003k\u001e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001_>\u0011\u0005\u0019J\u0018B\u0001>(\u0005\r\te.\u001f\u0005\by.\t\t\u00111\u0001t\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tq\u0010E\u0003\u0002\u0002\u0005\u001d\u00010\u0004\u0002\u0002\u0004)\u0019\u0011QA\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\n\u0005\r!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0004\u0002\u0016A\u0019a%!\u0005\n\u0007\u0005MqEA\u0004C_>dW-\u00198\t\u000fql\u0011\u0011!a\u0001q\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rQ\u00171\u0004\u0005\by:\t\t\u00111\u0001t\u0003!A\u0017m\u001d5D_\u0012,G#A:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A[\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005=\u0011\u0011\u0006\u0005\byF\t\t\u00111\u0001y\u0003e)\u00050Z2vi>\u0014H)Z2p[6L7o]5p]N#\u0018\r^3\u0011\u0005I\u001b2#B\n\u00022\u0005u\u0002cBA\u001a\u0003si4)U\u0007\u0003\u0003kQ1!a\u000e(\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u000f\u00026\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005}\u0012QI\u0007\u0003\u0003\u0003R1!a\u0011o\u0003\tIw.C\u0002:\u0003\u0003\"\"!!\f\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000bE\u000bi%a\u0014\t\u000bm2\u0002\u0019A\u001f\t\u000f\u00053\u0002\u0013!a\u0001\u0007\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005]\u0013q\f\t\u0005M\u0011\u000bI\u0006E\u0003'\u00037j4)C\u0002\u0002^\u001d\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA11\u0005\u0005\t\u0019A)\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003S\u00022a[A6\u0013\r\ti\u0007\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class ExecutorDecommissionState implements Product, Serializable {
   private final long startTime;
   private final Option workerHost;

   public static Option $lessinit$greater$default$2() {
      return ExecutorDecommissionState$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final ExecutorDecommissionState x$0) {
      return ExecutorDecommissionState$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$2() {
      return ExecutorDecommissionState$.MODULE$.apply$default$2();
   }

   public static ExecutorDecommissionState apply(final long startTime, final Option workerHost) {
      return ExecutorDecommissionState$.MODULE$.apply(startTime, workerHost);
   }

   public static Function1 tupled() {
      return ExecutorDecommissionState$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExecutorDecommissionState$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long startTime() {
      return this.startTime;
   }

   public Option workerHost() {
      return this.workerHost;
   }

   public ExecutorDecommissionState copy(final long startTime, final Option workerHost) {
      return new ExecutorDecommissionState(startTime, workerHost);
   }

   public long copy$default$1() {
      return this.startTime();
   }

   public Option copy$default$2() {
      return this.workerHost();
   }

   public String productPrefix() {
      return "ExecutorDecommissionState";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.startTime());
         }
         case 1 -> {
            return this.workerHost();
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
      return x$1 instanceof ExecutorDecommissionState;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "startTime";
         }
         case 1 -> {
            return "workerHost";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.startTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.workerHost()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof ExecutorDecommissionState) {
               ExecutorDecommissionState var4 = (ExecutorDecommissionState)x$1;
               if (this.startTime() == var4.startTime()) {
                  label44: {
                     Option var10000 = this.workerHost();
                     Option var5 = var4.workerHost();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
                     }
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

   public ExecutorDecommissionState(final long startTime, final Option workerHost) {
      this.startTime = startTime;
      this.workerHost = workerHost;
      Product.$init$(this);
   }
}
