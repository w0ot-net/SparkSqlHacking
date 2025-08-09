package org.apache.spark.scheduler;

import java.io.Serializable;
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
   bytes = "\u0006\u0005\u0005%e\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003I\u0011!\t\u0006A!f\u0001\n\u00039\u0005\u0002\u0003*\u0001\u0005#\u0005\u000b\u0011\u0002%\t\u000bM\u0003A\u0011\u0001+\t\u000fe\u0003\u0011\u0011!C\u00015\"9a\fAI\u0001\n\u0003y\u0006b\u00026\u0001#\u0003%\ta\u001b\u0005\b[\u0002\t\n\u0011\"\u0001l\u0011\u001dq\u0007!!A\u0005B=Dqa\u001e\u0001\u0002\u0002\u0013\u0005\u0001\u0010C\u0004}\u0001\u0005\u0005I\u0011A?\t\u0013\u0005\u001d\u0001!!A\u0005B\u0005%\u0001\"CA\f\u0001\u0005\u0005I\u0011AA\r\u0011%\t\u0019\u0003AA\u0001\n\u0003\n)\u0003C\u0005\u0002*\u0001\t\t\u0011\"\u0011\u0002,!I\u0011Q\u0006\u0001\u0002\u0002\u0013\u0005\u0013q\u0006\u0005\n\u0003c\u0001\u0011\u0011!C!\u0003g9\u0011\"a\u0011\u001e\u0003\u0003E\t!!\u0012\u0007\u0011qi\u0012\u0011!E\u0001\u0003\u000fBaa\u0015\f\u0005\u0002\u0005}\u0003\"CA\u0017-\u0005\u0005IQIA\u0018\u0011%\t\tGFA\u0001\n\u0003\u000b\u0019\u0007C\u0005\u0002lY\t\t\u0011\"!\u0002n!I\u0011q\u0010\f\u0002\u0002\u0013%\u0011\u0011\u0011\u0002\u001d'B\f'o\u001b'jgR,g.\u001a:Fq\u0016\u001cW\u000f^8s%\u0016lwN^3e\u0015\tqr$A\u0005tG\",G-\u001e7fe*\u0011\u0001%I\u0001\u0006gB\f'o\u001b\u0006\u0003E\r\na!\u00199bG\",'\"\u0001\u0013\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u00019S&\r\u001b\u0011\u0005!ZS\"A\u0015\u000b\u0003)\nQa]2bY\u0006L!\u0001L\u0015\u0003\r\u0005s\u0017PU3g!\tqs&D\u0001\u001e\u0013\t\u0001TD\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\bC\u0001\u00153\u0013\t\u0019\u0014FA\u0004Qe>$Wo\u0019;\u0011\u0005UjdB\u0001\u001c<\u001d\t9$(D\u00019\u0015\tIT%\u0001\u0004=e>|GOP\u0005\u0002U%\u0011A(K\u0001\ba\u0006\u001c7.Y4f\u0013\tqtH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002=S\u0005!A/[7f+\u0005\u0011\u0005C\u0001\u0015D\u0013\t!\u0015F\u0001\u0003M_:<\u0017!\u0002;j[\u0016\u0004\u0013AC3yK\u000e,Ho\u001c:JIV\t\u0001\n\u0005\u0002J\u001b:\u0011!j\u0013\t\u0003o%J!\u0001T\u0015\u0002\rA\u0013X\rZ3g\u0013\tquJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0019&\n1\"\u001a=fGV$xN]%eA\u00051!/Z1t_:\fqA]3bg>t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005+Z;\u0006\f\u0005\u0002/\u0001!)\u0001i\u0002a\u0001\u0005\")ai\u0002a\u0001\u0011\")\u0011k\u0002a\u0001\u0011\u0006!1m\u001c9z)\u0011)6\fX/\t\u000f\u0001C\u0001\u0013!a\u0001\u0005\"9a\t\u0003I\u0001\u0002\u0004A\u0005bB)\t!\u0003\u0005\r\u0001S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0001'F\u0001\"bW\u0005\u0011\u0007CA2i\u001b\u0005!'BA3g\u0003%)hn\u00195fG.,GM\u0003\u0002hS\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005%$'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00017+\u0005!\u000b\u0017AD2paf$C-\u001a4bk2$HeM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003A\u0004\"!\u001d<\u000e\u0003IT!a\u001d;\u0002\t1\fgn\u001a\u0006\u0002k\u0006!!.\u0019<b\u0013\tq%/\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001z!\tA#0\u0003\u0002|S\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019a0a\u0001\u0011\u0005!z\u0018bAA\u0001S\t\u0019\u0011I\\=\t\u0011\u0005\u0015a\"!AA\u0002e\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0006!\u0015\ti!a\u0005\u007f\u001b\t\tyAC\u0002\u0002\u0012%\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)\"a\u0004\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u00037\t\t\u0003E\u0002)\u0003;I1!a\b*\u0005\u001d\u0011un\u001c7fC:D\u0001\"!\u0002\u0011\u0003\u0003\u0005\rA`\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002q\u0003OA\u0001\"!\u0002\u0012\u0003\u0003\u0005\r!_\u0001\tQ\u0006\u001c\bnQ8eKR\t\u00110\u0001\u0005u_N#(/\u001b8h)\u0005\u0001\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\u001c\u0005U\u0002\u0002CA\u0003)\u0005\u0005\t\u0019\u0001@)\u0007\u0001\tI\u0004\u0005\u0003\u0002<\u0005}RBAA\u001f\u0015\t9w$\u0003\u0003\u0002B\u0005u\"\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017\u0001H*qCJ\\G*[:uK:,'/\u0012=fGV$xN\u001d*f[>4X\r\u001a\t\u0003]Y\u0019RAFA%\u0003+\u0002\u0002\"a\u0013\u0002R\tC\u0005*V\u0007\u0003\u0003\u001bR1!a\u0014*\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0015\u0002N\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005]\u0013QL\u0007\u0003\u00033R1!a\u0017u\u0003\tIw.C\u0002?\u00033\"\"!!\u0012\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fU\u000b)'a\u001a\u0002j!)\u0001)\u0007a\u0001\u0005\")a)\u0007a\u0001\u0011\")\u0011+\u0007a\u0001\u0011\u00069QO\\1qa2LH\u0003BA8\u0003w\u0002R\u0001KA9\u0003kJ1!a\u001d*\u0005\u0019y\u0005\u000f^5p]B1\u0001&a\u001eC\u0011\"K1!!\u001f*\u0005\u0019!V\u000f\u001d7fg!A\u0011Q\u0010\u000e\u0002\u0002\u0003\u0007Q+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a!\u0011\u0007E\f))C\u0002\u0002\bJ\u0014aa\u00142kK\u000e$\b"
)
public class SparkListenerExecutorRemoved implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String executorId;
   private final String reason;

   public static Option unapply(final SparkListenerExecutorRemoved x$0) {
      return SparkListenerExecutorRemoved$.MODULE$.unapply(x$0);
   }

   public static SparkListenerExecutorRemoved apply(final long time, final String executorId, final String reason) {
      return SparkListenerExecutorRemoved$.MODULE$.apply(time, executorId, reason);
   }

   public static Function1 tupled() {
      return SparkListenerExecutorRemoved$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerExecutorRemoved$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public long time() {
      return this.time;
   }

   public String executorId() {
      return this.executorId;
   }

   public String reason() {
      return this.reason;
   }

   public SparkListenerExecutorRemoved copy(final long time, final String executorId, final String reason) {
      return new SparkListenerExecutorRemoved(time, executorId, reason);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.executorId();
   }

   public String copy$default$3() {
      return this.reason();
   }

   public String productPrefix() {
      return "SparkListenerExecutorRemoved";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 1 -> {
            return this.executorId();
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
      return x$1 instanceof SparkListenerExecutorRemoved;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "executorId";
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
      var1 = Statics.mix(var1, Statics.longHash(this.time()));
      var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
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
            if (x$1 instanceof SparkListenerExecutorRemoved) {
               SparkListenerExecutorRemoved var4 = (SparkListenerExecutorRemoved)x$1;
               if (this.time() == var4.time()) {
                  label52: {
                     String var10000 = this.executorId();
                     String var5 = var4.executorId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     var10000 = this.reason();
                     String var6 = var4.reason();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var6)) {
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

   public SparkListenerExecutorRemoved(final long time, final String executorId, final String reason) {
      this.time = time;
      this.executorId = executorId;
      this.reason = reason;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
