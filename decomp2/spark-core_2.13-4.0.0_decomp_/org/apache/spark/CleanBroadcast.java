package org.apache.spark;

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
   bytes = "\u0006\u0005\u0005mb\u0001\u0002\f\u0018\tzA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dY\u0006!!A\u0005\u0002qCq\u0001\u0019\u0001\u0002\u0002\u0013\u0005\u0011\rC\u0004h\u0001\u0005\u0005I\u0011\t5\t\u000f=\u0004\u0011\u0011!C\u0001a\"9Q\u000fAA\u0001\n\u00032\bb\u0002=\u0001\u0003\u0003%\t%\u001f\u0005\bu\u0002\t\t\u0011\"\u0011|\u0011\u001da\b!!A\u0005Bu<\u0001b`\f\u0002\u0002#%\u0011\u0011\u0001\u0004\t-]\t\t\u0011#\u0003\u0002\u0004!1a\b\u0005C\u0001\u00037AqA\u001f\t\u0002\u0002\u0013\u00153\u0010C\u0005\u0002\u001eA\t\t\u0011\"!\u0002 !I\u00111\u0005\t\u0002\u0002\u0013\u0005\u0015Q\u0005\u0005\n\u0003c\u0001\u0012\u0011!C\u0005\u0003g\u0011ab\u00117fC:\u0014%o\\1eG\u0006\u001cHO\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h\u0007\u0001\u0019R\u0001A\u0010&S1\u0002\"\u0001I\u0012\u000e\u0003\u0005R\u0011AI\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0005\u0012a!\u00118z%\u00164\u0007C\u0001\u0014(\u001b\u00059\u0012B\u0001\u0015\u0018\u0005-\u0019E.Z1okB$\u0016m]6\u0011\u0005\u0001R\u0013BA\u0016\"\u0005\u001d\u0001&o\u001c3vGR\u0004\"!L\u001b\u000f\u00059\u001adBA\u00183\u001b\u0005\u0001$BA\u0019\u001e\u0003\u0019a$o\\8u}%\t!%\u0003\u00025C\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001c8\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t!\u0014%A\u0006ce>\fGmY1ti&#W#\u0001\u001e\u0011\u0005\u0001Z\u0014B\u0001\u001f\"\u0005\u0011auN\\4\u0002\u0019\t\u0014x.\u00193dCN$\u0018\n\u001a\u0011\u0002\rqJg.\u001b;?)\t\u0001\u0015\t\u0005\u0002'\u0001!)\u0001h\u0001a\u0001u\u0005!1m\u001c9z)\t\u0001E\tC\u00049\tA\u0005\t\u0019\u0001\u001e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tqI\u000b\u0002;\u0011.\n\u0011\n\u0005\u0002K\u001f6\t1J\u0003\u0002M\u001b\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u001d\u0006\n!\"\u00198o_R\fG/[8o\u0013\t\u00016JA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A*\u0011\u0005QKV\"A+\u000b\u0005Y;\u0016\u0001\u00027b]\u001eT\u0011\u0001W\u0001\u0005U\u00064\u0018-\u0003\u0002[+\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u0018\t\u0003AyK!aX\u0011\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\t,\u0007C\u0001\u0011d\u0013\t!\u0017EA\u0002B]fDqA\u001a\u0005\u0002\u0002\u0003\u0007Q,A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002SB\u0019!.\u001c2\u000e\u0003-T!\u0001\\\u0011\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002oW\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t\tH\u000f\u0005\u0002!e&\u00111/\t\u0002\b\u0005>|G.Z1o\u0011\u001d1'\"!AA\u0002\t\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u00111k\u001e\u0005\bM.\t\t\u00111\u0001^\u0003!A\u0017m\u001d5D_\u0012,G#A/\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aU\u0001\u0007KF,\u0018\r\\:\u0015\u0005Et\bb\u00024\u000f\u0003\u0003\u0005\rAY\u0001\u000f\u00072,\u0017M\u001c\"s_\u0006$7-Y:u!\t1\u0003cE\u0003\u0011\u0003\u000b\t\t\u0002\u0005\u0004\u0002\b\u00055!\bQ\u0007\u0003\u0003\u0013Q1!a\u0003\"\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0004\u0002\n\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005M\u0011\u0011D\u0007\u0003\u0003+Q1!a\u0006X\u0003\tIw.C\u00027\u0003+!\"!!\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0001\u000b\t\u0003C\u00039'\u0001\u0007!(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u001d\u0012Q\u0006\t\u0005A\u0005%\"(C\u0002\u0002,\u0005\u0012aa\u00149uS>t\u0007\u0002CA\u0018)\u0005\u0005\t\u0019\u0001!\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00026A\u0019A+a\u000e\n\u0007\u0005eRK\u0001\u0004PE*,7\r\u001e"
)
public class CleanBroadcast implements CleanupTask, Product, Serializable {
   private final long broadcastId;

   public static Option unapply(final CleanBroadcast x$0) {
      return CleanBroadcast$.MODULE$.unapply(x$0);
   }

   public static CleanBroadcast apply(final long broadcastId) {
      return CleanBroadcast$.MODULE$.apply(broadcastId);
   }

   public static Function1 andThen(final Function1 g) {
      return CleanBroadcast$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return CleanBroadcast$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long broadcastId() {
      return this.broadcastId;
   }

   public CleanBroadcast copy(final long broadcastId) {
      return new CleanBroadcast(broadcastId);
   }

   public long copy$default$1() {
      return this.broadcastId();
   }

   public String productPrefix() {
      return "CleanBroadcast";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.broadcastId());
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
      return x$1 instanceof CleanBroadcast;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "broadcastId";
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
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof CleanBroadcast) {
               CleanBroadcast var4 = (CleanBroadcast)x$1;
               if (this.broadcastId() == var4.broadcastId() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public CleanBroadcast(final long broadcastId) {
      this.broadcastId = broadcastId;
      Product.$init$(this);
   }
}
