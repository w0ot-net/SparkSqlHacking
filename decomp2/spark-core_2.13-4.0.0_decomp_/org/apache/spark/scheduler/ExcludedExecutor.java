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
   bytes = "\u0006\u0005\u0005\u0015d\u0001B\r\u001b\r\u000eB\u0001\"\u000f\u0001\u0003\u0016\u0004%\tA\u000f\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005w!AA\t\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005J\u0001\tE\t\u0015!\u0003G\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u001d\u0001\u0006!!A\u0005\u0002ECq\u0001\u0016\u0001\u0012\u0002\u0013\u0005Q\u000bC\u0004a\u0001E\u0005I\u0011A1\t\u000f\r\u0004\u0011\u0011!C!I\"9A\u000eAA\u0001\n\u0003i\u0007bB9\u0001\u0003\u0003%\tA\u001d\u0005\bq\u0002\t\t\u0011\"\u0011z\u0011%\t\t\u0001AA\u0001\n\u0003\t\u0019\u0001C\u0005\u0002\u000e\u0001\t\t\u0011\"\u0011\u0002\u0010!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0013Q\u0003\u0005\n\u0003/\u0001\u0011\u0011!C!\u00033A\u0011\"a\u0007\u0001\u0003\u0003%\t%!\b\b\u0013\u0005\u0005\"$!A\t\n\u0005\rb\u0001C\r\u001b\u0003\u0003EI!!\n\t\r)\u001bB\u0011AA\u001f\u0011%\t9bEA\u0001\n\u000b\nI\u0002C\u0005\u0002@M\t\t\u0011\"!\u0002B!I\u0011qI\n\u0002\u0002\u0013\u0005\u0015\u0011\n\u0005\n\u00037\u001a\u0012\u0011!C\u0005\u0003;\u0012\u0001#\u0012=dYV$W\rZ#yK\u000e,Ho\u001c:\u000b\u0005ma\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0011RS\u0006\u0005\u0002&Q5\taEC\u0001(\u0003\u0015\u00198-\u00197b\u0013\tIcE\u0001\u0004B]f\u0014VM\u001a\t\u0003K-J!\u0001\f\u0014\u0003\u000fA\u0013x\u000eZ;diB\u0011aF\u000e\b\u0003_Qr!\u0001M\u001a\u000e\u0003ER!A\r\u0012\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0013BA\u001b'\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000e\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005U2\u0013\u0001\u00028pI\u0016,\u0012a\u000f\t\u0003y\u0001s!!\u0010 \u0011\u0005A2\u0013BA '\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011I\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005}2\u0013!\u00028pI\u0016\u0004\u0013AC3ya&\u0014\u0018\u0010V5nKV\ta\t\u0005\u0002&\u000f&\u0011\u0001J\n\u0002\u0005\u0019>tw-A\u0006fqBL'/\u001f+j[\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u0002M\u001d>\u0003\"!\u0014\u0001\u000e\u0003iAQ!O\u0003A\u0002mBQ\u0001R\u0003A\u0002\u0019\u000bAaY8qsR\u0019AJU*\t\u000fe2\u0001\u0013!a\u0001w!9AI\u0002I\u0001\u0002\u00041\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002-*\u00121hV\u0016\u00021B\u0011\u0011LX\u0007\u00025*\u00111\fX\u0001\nk:\u001c\u0007.Z2lK\u0012T!!\u0018\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002`5\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\t!M\u000b\u0002G/\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u001a\t\u0003M.l\u0011a\u001a\u0006\u0003Q&\fA\u0001\\1oO*\t!.\u0001\u0003kCZ\f\u0017BA!h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005q\u0007CA\u0013p\u0013\t\u0001hEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002tmB\u0011Q\u0005^\u0005\u0003k\u001a\u00121!\u00118z\u0011\u001d98\"!AA\u00029\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001>\u0011\u0007mt8/D\u0001}\u0015\tih%\u0001\u0006d_2dWm\u0019;j_:L!a ?\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u000b\tY\u0001E\u0002&\u0003\u000fI1!!\u0003'\u0005\u001d\u0011un\u001c7fC:Dqa^\u0007\u0002\u0002\u0003\u00071/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA3\u0002\u0012!9qODA\u0001\u0002\u0004q\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u00039\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002K\u00061Q-];bYN$B!!\u0002\u0002 !9q/EA\u0001\u0002\u0004\u0019\u0018\u0001E#yG2,H-\u001a3Fq\u0016\u001cW\u000f^8s!\ti5cE\u0003\u0014\u0003O\t\u0019\u0004E\u0004\u0002*\u0005=2H\u0012'\u000e\u0005\u0005-\"bAA\u0017M\u00059!/\u001e8uS6,\u0017\u0002BA\u0019\u0003W\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\t)$a\u000f\u000e\u0005\u0005]\"bAA\u001dS\u0006\u0011\u0011n\\\u0005\u0004o\u0005]BCAA\u0012\u0003\u0015\t\u0007\u000f\u001d7z)\u0015a\u00151IA#\u0011\u0015Id\u00031\u0001<\u0011\u0015!e\u00031\u0001G\u0003\u001d)h.\u00199qYf$B!a\u0013\u0002XA)Q%!\u0014\u0002R%\u0019\u0011q\n\u0014\u0003\r=\u0003H/[8o!\u0015)\u00131K\u001eG\u0013\r\t)F\n\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005es#!AA\u00021\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\u0006E\u0002g\u0003CJ1!a\u0019h\u0005\u0019y%M[3di\u0002"
)
public final class ExcludedExecutor implements Product, Serializable {
   private final String node;
   private final long expiryTime;

   public static Option unapply(final ExcludedExecutor x$0) {
      return ExcludedExecutor$.MODULE$.unapply(x$0);
   }

   public static ExcludedExecutor apply(final String node, final long expiryTime) {
      return ExcludedExecutor$.MODULE$.apply(node, expiryTime);
   }

   public static Function1 tupled() {
      return ExcludedExecutor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ExcludedExecutor$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String node() {
      return this.node;
   }

   public long expiryTime() {
      return this.expiryTime;
   }

   public ExcludedExecutor copy(final String node, final long expiryTime) {
      return new ExcludedExecutor(node, expiryTime);
   }

   public String copy$default$1() {
      return this.node();
   }

   public long copy$default$2() {
      return this.expiryTime();
   }

   public String productPrefix() {
      return "ExcludedExecutor";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.node();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.expiryTime());
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
      return x$1 instanceof ExcludedExecutor;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "node";
         }
         case 1 -> {
            return "expiryTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.node()));
      var1 = Statics.mix(var1, Statics.longHash(this.expiryTime()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label41: {
            if (x$1 instanceof ExcludedExecutor) {
               ExcludedExecutor var4 = (ExcludedExecutor)x$1;
               if (this.expiryTime() == var4.expiryTime()) {
                  String var10000 = this.node();
                  String var5 = var4.node();
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label41;
                     }
                  } else if (var10000.equals(var5)) {
                     break label41;
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

   public ExcludedExecutor(final String node, final long expiryTime) {
      this.node = node;
      this.expiryTime = expiryTime;
      Product.$init$(this);
   }
}
