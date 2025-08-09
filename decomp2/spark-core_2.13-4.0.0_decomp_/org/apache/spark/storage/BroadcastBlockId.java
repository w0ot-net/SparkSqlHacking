package org.apache.spark.storage;

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
   bytes = "\u0006\u0005\u0005md\u0001B\u000e\u001d\u0001\u0016B\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!A!\t\u0001BK\u0002\u0013\u00051\t\u0003\u0005M\u0001\tE\t\u0015!\u0003E\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0015\u0011\u0006\u0001\"\u0011D\u0011\u001d\u0019\u0006!!A\u0005\u0002QCqa\u0016\u0001\u0012\u0002\u0013\u0005\u0001\fC\u0004d\u0001E\u0005I\u0011\u00013\t\u000f\u0019\u0004\u0011\u0011!C!O\"9q\u000eAA\u0001\n\u0003\u0001\bb\u0002;\u0001\u0003\u0003%\t!\u001e\u0005\bw\u0002\t\t\u0011\"\u0011}\u0011%\t9\u0001AA\u0001\n\u0003\tI\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131\u0004\u0005\n\u0003;\u0001\u0011\u0011!C!\u0003?9\u0011\"a\f\u001d\u0003\u0003E\t!!\r\u0007\u0011ma\u0012\u0011!E\u0001\u0003gAa!T\n\u0005\u0002\u0005-\u0003\"CA''\u0005\u0005IQIA(\u0011%\t\tfEA\u0001\n\u0003\u000b\u0019\u0006\u0003\u0005\u0002ZM\t\n\u0011\"\u0001e\u0011%\tYfEA\u0001\n\u0003\u000bi\u0006\u0003\u0005\u0002pM\t\n\u0011\"\u0001e\u0011%\t\thEA\u0001\n\u0013\t\u0019H\u0001\tCe>\fGmY1ti\ncwnY6JI*\u0011QDH\u0001\bgR|'/Y4f\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0019R\u0003\u0007\u0005\u0002(Q5\tA$\u0003\u0002*9\t9!\t\\8dW&#\u0007CA\u0016/\u001b\u0005a#\"A\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u0005=b#a\u0002)s_\u0012,8\r\u001e\t\u0003cer!AM\u001c\u000f\u0005M2T\"\u0001\u001b\u000b\u0005U\"\u0013A\u0002\u001fs_>$h(C\u0001.\u0013\tAD&A\u0004qC\u000e\\\u0017mZ3\n\u0005iZ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001d-\u0003-\u0011'o\\1eG\u0006\u001cH/\u00133\u0016\u0003y\u0002\"aK \n\u0005\u0001c#\u0001\u0002'p]\u001e\fAB\u0019:pC\u0012\u001c\u0017m\u001d;JI\u0002\nQAZ5fY\u0012,\u0012\u0001\u0012\t\u0003\u000b&s!AR$\u0011\u0005Mb\u0013B\u0001%-\u0003\u0019\u0001&/\u001a3fM&\u0011!j\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!c\u0013A\u00024jK2$\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004\u001fB\u000b\u0006CA\u0014\u0001\u0011\u0015aT\u00011\u0001?\u0011\u001d\u0011U\u0001%AA\u0002\u0011\u000bAA\\1nK\u0006!1m\u001c9z)\ryUK\u0016\u0005\by\u001d\u0001\n\u00111\u0001?\u0011\u001d\u0011u\u0001%AA\u0002\u0011\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001ZU\tq$lK\u0001\\!\ta\u0016-D\u0001^\u0015\tqv,A\u0005v]\u000eDWmY6fI*\u0011\u0001\rL\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00012^\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005)'F\u0001#[\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0001\u000e\u0005\u0002j]6\t!N\u0003\u0002lY\u0006!A.\u00198h\u0015\u0005i\u0017\u0001\u00026bm\u0006L!A\u00136\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003E\u0004\"a\u000b:\n\u0005Md#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001<z!\tYs/\u0003\u0002yY\t\u0019\u0011I\\=\t\u000fid\u0011\u0011!a\u0001c\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012! \t\u0005}\u0006\ra/D\u0001\u0000\u0015\r\t\t\u0001L\u0001\u000bG>dG.Z2uS>t\u0017bAA\u0003\u007f\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY!!\u0005\u0011\u0007-\ni!C\u0002\u0002\u00101\u0012qAQ8pY\u0016\fg\u000eC\u0004{\u001d\u0005\u0005\t\u0019\u0001<\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004Q\u0006]\u0001b\u0002>\u0010\u0003\u0003\u0005\r!]\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0017\t\t\u0003C\u0004{#\u0005\u0005\t\u0019\u0001<)\u0007\u0001\t)\u0003\u0005\u0003\u0002(\u0005-RBAA\u0015\u0015\t\u0001g$\u0003\u0003\u0002.\u0005%\"\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017\u0001\u0005\"s_\u0006$7-Y:u\u00052|7m[%e!\t93cE\u0003\u0014\u0003k\t\t\u0005E\u0004\u00028\u0005ub\bR(\u000e\u0005\u0005e\"bAA\u001eY\u00059!/\u001e8uS6,\u0017\u0002BA \u0003s\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\t\u0019%!\u0013\u000e\u0005\u0005\u0015#bAA$Y\u0006\u0011\u0011n\\\u0005\u0004u\u0005\u0015CCAA\u0019\u0003!!xn\u0015;sS:<G#\u00015\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b=\u000b)&a\u0016\t\u000bq2\u0002\u0019\u0001 \t\u000f\t3\u0002\u0013!a\u0001\t\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$#'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005}\u00131\u000e\t\u0006W\u0005\u0005\u0014QM\u0005\u0004\u0003Gb#AB(qi&|g\u000eE\u0003,\u0003OrD)C\u0002\u0002j1\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA71\u0005\u0005\t\u0019A(\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003k\u00022![A<\u0013\r\tIH\u001b\u0002\u0007\u001f\nTWm\u0019;"
)
public class BroadcastBlockId extends BlockId implements Product, Serializable {
   private final long broadcastId;
   private final String field;

   public static String $lessinit$greater$default$2() {
      return BroadcastBlockId$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final BroadcastBlockId x$0) {
      return BroadcastBlockId$.MODULE$.unapply(x$0);
   }

   public static String apply$default$2() {
      return BroadcastBlockId$.MODULE$.apply$default$2();
   }

   public static BroadcastBlockId apply(final long broadcastId, final String field) {
      return BroadcastBlockId$.MODULE$.apply(broadcastId, field);
   }

   public static Function1 tupled() {
      return BroadcastBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BroadcastBlockId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long broadcastId() {
      return this.broadcastId;
   }

   public String field() {
      return this.field;
   }

   public String name() {
      String var2;
      long var10000;
      label23: {
         var10000 = this.broadcastId();
         var2 = this.field();
         String var1 = "";
         if (var2 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var2.equals(var1)) {
            break label23;
         }

         var2 = "_" + this.field();
         return "broadcast_" + var10000 + var2;
      }

      var2 = "";
      return "broadcast_" + var10000 + var2;
   }

   public BroadcastBlockId copy(final long broadcastId, final String field) {
      return new BroadcastBlockId(broadcastId, field);
   }

   public long copy$default$1() {
      return this.broadcastId();
   }

   public String copy$default$2() {
      return this.field();
   }

   public String productPrefix() {
      return "BroadcastBlockId";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.broadcastId());
         }
         case 1 -> {
            return this.field();
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
      return x$1 instanceof BroadcastBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "broadcastId";
         }
         case 1 -> {
            return "field";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.broadcastId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.field()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof BroadcastBlockId) {
               BroadcastBlockId var4 = (BroadcastBlockId)x$1;
               if (this.broadcastId() == var4.broadcastId()) {
                  label44: {
                     String var10000 = this.field();
                     String var5 = var4.field();
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

   public BroadcastBlockId(final long broadcastId, final String field) {
      this.broadcastId = broadcastId;
      this.field = field;
      Product.$init$(this);
   }
}
