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
   bytes = "\u0006\u0005\u0005Mb!\u0002\f\u0018\u0001^i\u0002\u0002\u0003\u001d\u0001\u0005+\u0007I\u0011A\u001d\t\u0011u\u0002!\u0011#Q\u0001\niBQA\u0010\u0001\u0005\u0002}BqA\u0011\u0001\u0002\u0002\u0013\u00051\tC\u0004F\u0001E\u0005I\u0011\u0001$\t\u000fE\u0003\u0011\u0011!C!%\"91\fAA\u0001\n\u0003I\u0004b\u0002/\u0001\u0003\u0003%\t!\u0018\u0005\bG\u0002\t\t\u0011\"\u0011e\u0011\u001dY\u0007!!A\u0005\u00021Dq!\u001d\u0001\u0002\u0002\u0013\u0005#\u000fC\u0004u\u0001\u0005\u0005I\u0011I;\t\u000fY\u0004\u0011\u0011!C!o\"9\u0001\u0010AA\u0001\n\u0003Jx\u0001C>\u0018\u0003\u0003E\ta\u0006?\u0007\u0011Y9\u0012\u0011!E\u0001/uDaA\u0010\t\u0005\u0002\u0005M\u0001b\u0002<\u0011\u0003\u0003%)e\u001e\u0005\n\u0003+\u0001\u0012\u0011!CA\u0003/A\u0011\"a\u0007\u0011\u0003\u0003%\t)!\b\t\u0013\u0005%\u0002#!A\u0005\n\u0005-\"\u0001F$fi6\u000b\u0007oT;uaV$8\u000b^1ukN,7O\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h'\u0015\u0001a\u0004\n\u0015,!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0011QEJ\u0007\u0002/%\u0011qe\u0006\u0002\u0018\u001b\u0006\u0004x*\u001e;qkR$&/Y2lKJlUm]:bO\u0016\u0004\"aH\u0015\n\u0005)\u0002#a\u0002)s_\u0012,8\r\u001e\t\u0003YUr!!L\u001a\u000f\u00059\u0012T\"A\u0018\u000b\u0005A\n\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0005J!\u0001\u000e\u0011\u0002\u000fA\f7m[1hK&\u0011ag\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003i\u0001\n\u0011b\u001d5vM\u001adW-\u00133\u0016\u0003i\u0002\"aH\u001e\n\u0005q\u0002#aA%oi\u0006Q1\u000f[;gM2,\u0017\n\u001a\u0011\u0002\rqJg.\u001b;?)\t\u0001\u0015\t\u0005\u0002&\u0001!)\u0001h\u0001a\u0001u\u0005!1m\u001c9z)\t\u0001E\tC\u00049\tA\u0005\t\u0019\u0001\u001e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tqI\u000b\u0002;\u0011.\n\u0011\n\u0005\u0002K\u001f6\t1J\u0003\u0002M\u001b\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u001d\u0002\n!\"\u00198o_R\fG/[8o\u0013\t\u00016JA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A*\u0011\u0005QKV\"A+\u000b\u0005Y;\u0016\u0001\u00027b]\u001eT\u0011\u0001W\u0001\u0005U\u00064\u0018-\u0003\u0002[+\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002_CB\u0011qdX\u0005\u0003A\u0002\u00121!\u00118z\u0011\u001d\u0011\u0007\"!AA\u0002i\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A3\u0011\u0007\u0019Lg,D\u0001h\u0015\tA\u0007%\u0001\u0006d_2dWm\u0019;j_:L!A[4\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003[B\u0004\"a\b8\n\u0005=\u0004#a\u0002\"p_2,\u0017M\u001c\u0005\bE*\t\t\u00111\u0001_\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005M\u001b\bb\u00022\f\u0003\u0003\u0005\rAO\u0001\tQ\u0006\u001c\bnQ8eKR\t!(\u0001\u0005u_N#(/\u001b8h)\u0005\u0019\u0016AB3rk\u0006d7\u000f\u0006\u0002nu\"9!MDA\u0001\u0002\u0004q\u0016\u0001F$fi6\u000b\u0007oT;uaV$8\u000b^1ukN,7\u000f\u0005\u0002&!M!\u0001C`A\u0005!\u0015y\u0018Q\u0001\u001eA\u001b\t\t\tAC\u0002\u0002\u0004\u0001\nqA];oi&lW-\u0003\u0003\u0002\b\u0005\u0005!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u00111BA\t\u001b\t\tiAC\u0002\u0002\u0010]\u000b!![8\n\u0007Y\ni\u0001F\u0001}\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0001\u0015\u0011\u0004\u0005\u0006qM\u0001\rAO\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ty\"!\n\u0011\t}\t\tCO\u0005\u0004\u0003G\u0001#AB(qi&|g\u000e\u0003\u0005\u0002(Q\t\t\u00111\u0001A\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003[\u00012\u0001VA\u0018\u0013\r\t\t$\u0016\u0002\u0007\u001f\nTWm\u0019;"
)
public class GetMapOutputStatuses implements MapOutputTrackerMessage, Product, Serializable {
   private final int shuffleId;

   public static Option unapply(final GetMapOutputStatuses x$0) {
      return GetMapOutputStatuses$.MODULE$.unapply(x$0);
   }

   public static GetMapOutputStatuses apply(final int shuffleId) {
      return GetMapOutputStatuses$.MODULE$.apply(shuffleId);
   }

   public static Function1 andThen(final Function1 g) {
      return GetMapOutputStatuses$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return GetMapOutputStatuses$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public GetMapOutputStatuses copy(final int shuffleId) {
      return new GetMapOutputStatuses(shuffleId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public String productPrefix() {
      return "GetMapOutputStatuses";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
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
      return x$1 instanceof GetMapOutputStatuses;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.shuffleId());
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof GetMapOutputStatuses) {
               GetMapOutputStatuses var4 = (GetMapOutputStatuses)x$1;
               if (this.shuffleId() == var4.shuffleId() && var4.canEqual(this)) {
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

   public GetMapOutputStatuses(final int shuffleId) {
      this.shuffleId = shuffleId;
      Product.$init$(this);
   }
}
