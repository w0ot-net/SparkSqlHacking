package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005]e\u0001\u0002\u0011\"\u0001\"B\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\t\"AQ\n\u0001BK\u0002\u0013\u0005a\n\u0003\u0005S\u0001\tE\t\u0015!\u0003P\u0011!\u0019\u0006A!f\u0001\n\u0003!\u0006\u0002\u0003-\u0001\u0005#\u0005\u000b\u0011B+\t\u000be\u0003A\u0011\u0001.\t\u000b}\u0003A\u0011I\"\t\u000b\u0001\u0004A\u0011\t(\t\u000f\u0005\u0004\u0011\u0011!C\u0001E\"9a\rAI\u0001\n\u00039\u0007b\u0002:\u0001#\u0003%\ta\u001d\u0005\bk\u0002\t\n\u0011\"\u0001w\u0011\u001dA\b!!A\u0005BeD\u0011\"a\u0001\u0001\u0003\u0003%\t!!\u0002\t\u0013\u00055\u0001!!A\u0005\u0002\u0005=\u0001\"CA\u000e\u0001\u0005\u0005I\u0011IA\u000f\u0011%\tY\u0003AA\u0001\n\u0003\ti\u0003C\u0005\u00022\u0001\t\t\u0011\"\u0011\u00024!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0013\u0011\b\u0005\n\u0003w\u0001\u0011\u0011!C!\u0003{A\u0011\"a\u0010\u0001\u0003\u0003%\t%!\u0011\b\u0013\u0005E\u0013%!A\t\u0002\u0005Mc\u0001\u0003\u0011\"\u0003\u0003E\t!!\u0016\t\reCB\u0011AA7\u0011%\tY\u0004GA\u0001\n\u000b\ni\u0004C\u0005\u0002pa\t\t\u0011\"!\u0002r!A\u0011\u0011\u0010\r\u0012\u0002\u0013\u00051\u000fC\u0005\u0002|a\t\t\u0011\"!\u0002~!A\u00111\u0012\r\u0012\u0002\u0013\u00051\u000fC\u0005\u0002\u000eb\t\t\u0011\"\u0003\u0002\u0010\n\u0019R\t_3dkR|'\u000fT8ti\u001a\u000b\u0017\u000e\\;sK*\u0011!eI\u0001\u0006gB\f'o\u001b\u0006\u0003I\u0015\na!\u00199bG\",'\"\u0001\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001Isf\r\u001c\u0011\u0005)jS\"A\u0016\u000b\u00031\nQa]2bY\u0006L!AL\u0016\u0003\r\u0005s\u0017PU3g!\t\u0001\u0014'D\u0001\"\u0013\t\u0011\u0014E\u0001\tUCN\\g)Y5mK\u0012\u0014V-Y:p]B\u0011!\u0006N\u0005\u0003k-\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00028\u007f9\u0011\u0001(\u0010\b\u0003sqj\u0011A\u000f\u0006\u0003w\u001d\na\u0001\u0010:p_Rt\u0014\"\u0001\u0017\n\u0005yZ\u0013a\u00029bG.\fw-Z\u0005\u0003\u0001\u0006\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!AP\u0016\u0002\r\u0015DXmY%e+\u0005!\u0005CA#J\u001d\t1u\t\u0005\u0002:W%\u0011\u0001jK\u0001\u0007!J,G-\u001a4\n\u0005)[%AB*ue&twM\u0003\u0002IW\u00059Q\r_3d\u0013\u0012\u0004\u0013aD3ySR\u001c\u0015-^:fI\nK\u0018\t\u001d9\u0016\u0003=\u0003\"A\u000b)\n\u0005E[#a\u0002\"p_2,\u0017M\\\u0001\u0011KbLGoQ1vg\u0016$')_!qa\u0002\naA]3bg>tW#A+\u0011\u0007)2F)\u0003\u0002XW\t1q\n\u001d;j_:\fqA]3bg>t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u00057rkf\f\u0005\u00021\u0001!)!i\u0002a\u0001\t\"9Qj\u0002I\u0001\u0002\u0004y\u0005\"B*\b\u0001\u0004)\u0016!\u0004;p\u000bJ\u0014xN]*ue&tw-\u0001\rd_VtG\u000fV8xCJ$7\u000fV1tW\u001a\u000b\u0017\u000e\\;sKN\fAaY8qsR!1l\u00193f\u0011\u001d\u0011%\u0002%AA\u0002\u0011Cq!\u0014\u0006\u0011\u0002\u0003\u0007q\nC\u0004T\u0015A\u0005\t\u0019A+\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0001N\u000b\u0002ES.\n!\u000e\u0005\u0002la6\tAN\u0003\u0002n]\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003_.\n!\"\u00198o_R\fG/[8o\u0013\t\tHNA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001uU\ty\u0015.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003]T#!V5\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Q\bcA>\u0002\u00025\tAP\u0003\u0002~}\u0006!A.\u00198h\u0015\u0005y\u0018\u0001\u00026bm\u0006L!A\u0013?\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u001d\u0001c\u0001\u0016\u0002\n%\u0019\u00111B\u0016\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005E\u0011q\u0003\t\u0004U\u0005M\u0011bAA\u000bW\t\u0019\u0011I\\=\t\u0013\u0005e\u0001#!AA\u0002\u0005\u001d\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002 A1\u0011\u0011EA\u0014\u0003#i!!a\t\u000b\u0007\u0005\u00152&\u0001\u0006d_2dWm\u0019;j_:LA!!\u000b\u0002$\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\ry\u0015q\u0006\u0005\n\u00033\u0011\u0012\u0011!a\u0001\u0003#\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019!0!\u000e\t\u0013\u0005e1#!AA\u0002\u0005\u001d\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u001d\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003i\fa!Z9vC2\u001cHcA(\u0002D!I\u0011\u0011\u0004\f\u0002\u0002\u0003\u0007\u0011\u0011\u0003\u0015\u0004\u0001\u0005\u001d\u0003\u0003BA%\u0003\u001bj!!a\u0013\u000b\u0005=\f\u0013\u0002BA(\u0003\u0017\u0012A\u0002R3wK2|\u0007/\u001a:Ba&\f1#\u0012=fGV$xN\u001d'pgR4\u0015-\u001b7ve\u0016\u0004\"\u0001\r\r\u0014\u000ba\t9&a\u0019\u0011\u0011\u0005e\u0013q\f#P+nk!!a\u0017\u000b\u0007\u0005u3&A\u0004sk:$\u0018.\\3\n\t\u0005\u0005\u00141\f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BA3\u0003Wj!!a\u001a\u000b\u0007\u0005%d0\u0001\u0002j_&\u0019\u0001)a\u001a\u0015\u0005\u0005M\u0013!B1qa2LHcB.\u0002t\u0005U\u0014q\u000f\u0005\u0006\u0005n\u0001\r\u0001\u0012\u0005\b\u001bn\u0001\n\u00111\u0001P\u0011\u0015\u00196\u00041\u0001V\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u0012\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u007f\n9\t\u0005\u0003+-\u0006\u0005\u0005C\u0002\u0016\u0002\u0004\u0012{U+C\u0002\u0002\u0006.\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CAE;\u0005\u0005\t\u0019A.\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003#\u00032a_AJ\u0013\r\t)\n \u0002\u0007\u001f\nTWm\u0019;"
)
public class ExecutorLostFailure implements TaskFailedReason, Product, Serializable {
   private final String execId;
   private final boolean exitCausedByApp;
   private final Option reason;

   public static boolean $lessinit$greater$default$2() {
      return ExecutorLostFailure$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final ExecutorLostFailure x$0) {
      return ExecutorLostFailure$.MODULE$.unapply(x$0);
   }

   public static boolean apply$default$2() {
      return ExecutorLostFailure$.MODULE$.apply$default$2();
   }

   public static ExecutorLostFailure apply(final String execId, final boolean exitCausedByApp, final Option reason) {
      return ExecutorLostFailure$.MODULE$.apply(execId, exitCausedByApp, reason);
   }

   public static Function1 tupled() {
      return ExecutorLostFailure$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExecutorLostFailure$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String execId() {
      return this.execId;
   }

   public boolean exitCausedByApp() {
      return this.exitCausedByApp;
   }

   public Option reason() {
      return this.reason;
   }

   public String toErrorString() {
      String exitBehavior = this.exitCausedByApp() ? "caused by one of the running tasks" : "unrelated to the running tasks";
      String var10000 = this.execId();
      return "ExecutorLostFailure (executor " + var10000 + " exited " + exitBehavior + ")" + this.reason().map((r) -> " Reason: " + r).getOrElse(() -> "");
   }

   public boolean countTowardsTaskFailures() {
      return this.exitCausedByApp();
   }

   public ExecutorLostFailure copy(final String execId, final boolean exitCausedByApp, final Option reason) {
      return new ExecutorLostFailure(execId, exitCausedByApp, reason);
   }

   public String copy$default$1() {
      return this.execId();
   }

   public boolean copy$default$2() {
      return this.exitCausedByApp();
   }

   public Option copy$default$3() {
      return this.reason();
   }

   public String productPrefix() {
      return "ExecutorLostFailure";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.execId();
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.exitCausedByApp());
         }
         case 2 -> {
            return this.reason();
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
      return x$1 instanceof ExecutorLostFailure;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "execId";
         }
         case 1 -> {
            return "exitCausedByApp";
         }
         case 2 -> {
            return "reason";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.execId()));
      var1 = Statics.mix(var1, this.exitCausedByApp() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.reason()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof ExecutorLostFailure) {
               ExecutorLostFailure var4 = (ExecutorLostFailure)x$1;
               if (this.exitCausedByApp() == var4.exitCausedByApp()) {
                  label52: {
                     String var10000 = this.execId();
                     String var5 = var4.execId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Option var7 = this.reason();
                     Option var6 = var4.reason();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public ExecutorLostFailure(final String execId, final boolean exitCausedByApp, final Option reason) {
      this.execId = execId;
      this.exitCausedByApp = exitCausedByApp;
      this.reason = reason;
      TaskFailedReason.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
