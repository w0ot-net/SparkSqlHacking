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

/** @deprecated */
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005-e\u0001B\r\u001b\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005N\u0001\tE\t\u0015!\u0003F\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u001d\u0019\u0006!!A\u0005\u0002QCqa\u0016\u0001\u0012\u0002\u0013\u0005\u0001\fC\u0004d\u0001E\u0005I\u0011\u00013\t\u000f\u0019\u0004\u0011\u0011!C!O\"9q\u000eAA\u0001\n\u0003\u0001\bb\u0002;\u0001\u0003\u0003%\t!\u001e\u0005\bw\u0002\t\t\u0011\"\u0011}\u0011%\t9\u0001AA\u0001\n\u0003\tI\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131\u0004\u0005\n\u0003;\u0001\u0011\u0011!C!\u0003?A\u0011\"!\t\u0001\u0003\u0003%\t%a\t\b\u0013\u0005\u001d#$!A\t\u0002\u0005%c\u0001C\r\u001b\u0003\u0003E\t!a\u0013\t\r9\u001bB\u0011AA2\u0011%\tibEA\u0001\n\u000b\ny\u0002C\u0005\u0002fM\t\t\u0011\"!\u0002h!I\u0011QN\n\u0002\u0002\u0013\u0005\u0015q\u000e\u0005\n\u0003\u0003\u001b\u0012\u0011!C\u0005\u0003\u0007\u0013ad\u00159be.d\u0015n\u001d;f]\u0016\u0014hj\u001c3f+:\u0014G.Y2lY&\u001cH/\u001a3\u000b\u0005ma\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<7\u0001A\n\u0006\u0001\u0011Rc&\r\t\u0003K!j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-bS\"\u0001\u000e\n\u00055R\"AE*qCJ\\G*[:uK:,'/\u0012<f]R\u0004\"!J\u0018\n\u0005A2#a\u0002)s_\u0012,8\r\u001e\t\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005Y\u0012\u0013A\u0002\u001fs_>$h(C\u0001(\u0013\tId%A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d'\u0003\u0011!\u0018.\\3\u0016\u0003}\u0002\"!\n!\n\u0005\u00053#\u0001\u0002'p]\u001e\fQ\u0001^5nK\u0002\na\u0001[8ti&#W#A#\u0011\u0005\u0019SeBA$I!\t!d%\u0003\u0002JM\u00051\u0001K]3eK\u001aL!a\u0013'\u0003\rM#(/\u001b8h\u0015\tIe%A\u0004i_N$\u0018\n\u001a\u0011\u0002\rqJg.\u001b;?)\r\u0001\u0016K\u0015\t\u0003W\u0001AQ!P\u0003A\u0002}BQaQ\u0003A\u0002\u0015\u000bAaY8qsR\u0019\u0001+\u0016,\t\u000fu2\u0001\u0013!a\u0001\u007f!91I\u0002I\u0001\u0002\u0004)\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u00023*\u0012qHW\u0016\u00027B\u0011A,Y\u0007\u0002;*\u0011alX\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0019\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002c;\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tQM\u000b\u0002F5\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u001b\t\u0003S:l\u0011A\u001b\u0006\u0003W2\fA\u0001\\1oO*\tQ.\u0001\u0003kCZ\f\u0017BA&k\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\t\bCA\u0013s\u0013\t\u0019hEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002wsB\u0011Qe^\u0005\u0003q\u001a\u00121!\u00118z\u0011\u001dQ8\"!AA\u0002E\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A?\u0011\ty\f\u0019A^\u0007\u0002\u007f*\u0019\u0011\u0011\u0001\u0014\u0002\u0015\r|G\u000e\\3di&|g.C\u0002\u0002\u0006}\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111BA\t!\r)\u0013QB\u0005\u0004\u0003\u001f1#a\u0002\"p_2,\u0017M\u001c\u0005\bu6\t\t\u00111\u0001w\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007!\f9\u0002C\u0004{\u001d\u0005\u0005\t\u0019A9\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!]\u0001\ti>\u001cFO]5oOR\t\u0001.\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0017\t)\u0003C\u0004{#\u0005\u0005\t\u0019\u0001<)\u0017\u0001\tI#a\f\u00022\u0005U\u0012q\u0007\t\u0004K\u0005-\u0012bAA\u0017M\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u0012\u00111G\u0001(kN,\u0007e\u00159be.d\u0015n\u001d;f]\u0016\u0014hj\u001c3f+:,\u0007p\u00197vI\u0016$\u0007%\u001b8ti\u0016\fG-A\u0003tS:\u001cW-\t\u0002\u0002:\u0005)1GL\u0019/a!\u001a\u0001!!\u0010\u0011\t\u0005}\u00121I\u0007\u0003\u0003\u0003R!\u0001\u0019\u000f\n\t\u0005\u0015\u0013\u0011\t\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001\u001f'B\f'o\u001b'jgR,g.\u001a:O_\u0012,WK\u001c2mC\u000e\\G.[:uK\u0012\u0004\"aK\n\u0014\u000bM\ti%!\u0017\u0011\u000f\u0005=\u0013QK F!6\u0011\u0011\u0011\u000b\u0006\u0004\u0003'2\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003/\n\tFA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\u0017\u0002b5\u0011\u0011Q\f\u0006\u0004\u0003?b\u0017AA5p\u0013\rY\u0014Q\f\u000b\u0003\u0003\u0013\nQ!\u00199qYf$R\u0001UA5\u0003WBQ!\u0010\fA\u0002}BQa\u0011\fA\u0002\u0015\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002r\u0005u\u0004#B\u0013\u0002t\u0005]\u0014bAA;M\t1q\n\u001d;j_:\u0004R!JA=\u007f\u0015K1!a\u001f'\u0005\u0019!V\u000f\u001d7fe!A\u0011qP\f\u0002\u0002\u0003\u0007\u0001+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\"\u0011\u0007%\f9)C\u0002\u0002\n*\u0014aa\u00142kK\u000e$\b"
)
public class SparkListenerNodeUnblacklisted implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String hostId;

   public static Option unapply(final SparkListenerNodeUnblacklisted x$0) {
      return SparkListenerNodeUnblacklisted$.MODULE$.unapply(x$0);
   }

   public static SparkListenerNodeUnblacklisted apply(final long time, final String hostId) {
      return SparkListenerNodeUnblacklisted$.MODULE$.apply(time, hostId);
   }

   public static Function1 tupled() {
      return SparkListenerNodeUnblacklisted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerNodeUnblacklisted$.MODULE$.curried();
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

   public String hostId() {
      return this.hostId;
   }

   public SparkListenerNodeUnblacklisted copy(final long time, final String hostId) {
      return new SparkListenerNodeUnblacklisted(time, hostId);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.hostId();
   }

   public String productPrefix() {
      return "SparkListenerNodeUnblacklisted";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 1 -> {
            return this.hostId();
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
      return x$1 instanceof SparkListenerNodeUnblacklisted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "hostId";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.hostId()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SparkListenerNodeUnblacklisted) {
               SparkListenerNodeUnblacklisted var4 = (SparkListenerNodeUnblacklisted)x$1;
               if (this.time() == var4.time()) {
                  label44: {
                     String var10000 = this.hostId();
                     String var5 = var4.hostId();
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

   public SparkListenerNodeUnblacklisted(final long time, final String hostId) {
      this.time = time;
      this.hostId = hostId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
