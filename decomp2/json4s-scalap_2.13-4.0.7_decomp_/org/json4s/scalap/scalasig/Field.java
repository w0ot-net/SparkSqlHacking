package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d\u0001B\u0010!\u0001&B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u0003\"AQ\t\u0001BK\u0002\u0013\u0005\u0001\t\u0003\u0005G\u0001\tE\t\u0015!\u0003B\u0011!9\u0005A!f\u0001\n\u0003\u0001\u0005\u0002\u0003%\u0001\u0005#\u0005\u000b\u0011B!\t\u0011%\u0003!Q3A\u0005\u0002)C\u0001B\u0015\u0001\u0003\u0012\u0003\u0006Ia\u0013\u0005\u0006'\u0002!\t\u0001\u0016\u0005\b5\u0002\t\t\u0011\"\u0001\\\u0011\u001d\u0001\u0007!%A\u0005\u0002\u0005Dq\u0001\u001c\u0001\u0012\u0002\u0013\u0005\u0011\rC\u0004n\u0001E\u0005I\u0011A1\t\u000f9\u0004\u0011\u0013!C\u0001_\"9\u0011\u000fAA\u0001\n\u0003\u0012\bbB>\u0001\u0003\u0003%\t\u0001\u0011\u0005\by\u0002\t\t\u0011\"\u0001~\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0001\u0002\u001a!I\u00111\u0005\u0001\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003S\u0001\u0011\u0011!C!\u0003WA\u0011\"!\f\u0001\u0003\u0003%\t%a\f\t\u0013\u0005E\u0002!!A\u0005B\u0005Mr!CA\u001cA\u0005\u0005\t\u0012AA\u001d\r!y\u0002%!A\t\u0002\u0005m\u0002BB*\u001a\t\u0003\t\u0019\u0006C\u0005\u0002.e\t\t\u0011\"\u0012\u00020!I\u0011QK\r\u0002\u0002\u0013\u0005\u0015q\u000b\u0005\n\u0003CJ\u0012\u0011!CA\u0003GB\u0011\"!\u001e\u001a\u0003\u0003%I!a\u001e\u0003\u000b\u0019KW\r\u001c3\u000b\u0005\u0005\u0012\u0013\u0001C:dC2\f7/[4\u000b\u0005\r\"\u0013AB:dC2\f\u0007O\u0003\u0002&M\u00051!n]8oiMT\u0011aJ\u0001\u0004_J<7\u0001A\n\u0005\u0001)\u00024\u0007\u0005\u0002,]5\tAFC\u0001.\u0003\u0015\u00198-\u00197b\u0013\tyCF\u0001\u0004B]f\u0014VM\u001a\t\u0003WEJ!A\r\u0017\u0003\u000fA\u0013x\u000eZ;diB\u0011A\u0007\u0010\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001\u000f\u0015\u0002\rq\u0012xn\u001c;?\u0013\u0005i\u0013BA\u001e-\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mb\u0013!\u00024mC\u001e\u001cX#A!\u0011\u0005-\u0012\u0015BA\"-\u0005\rIe\u000e^\u0001\u0007M2\fwm\u001d\u0011\u0002\u00139\fW.Z%oI\u0016D\u0018A\u00038b[\u0016Le\u000eZ3yA\u0005yA-Z:de&\u0004Ho\u001c:J]\u0012,\u00070\u0001\teKN\u001c'/\u001b9u_JLe\u000eZ3yA\u0005Q\u0011\r\u001e;sS\n,H/Z:\u0016\u0003-\u00032\u0001\u000e'O\u0013\tieHA\u0002TKF\u0004\"a\u0014)\u000e\u0003\u0001J!!\u0015\u0011\u0003\u0013\u0005#HO]5ckR,\u0017aC1uiJL'-\u001e;fg\u0002\na\u0001P5oSRtD#B+W/bK\u0006CA(\u0001\u0011\u0015y\u0014\u00021\u0001B\u0011\u0015)\u0015\u00021\u0001B\u0011\u00159\u0015\u00021\u0001B\u0011\u0015I\u0015\u00021\u0001L\u0003\u0011\u0019w\u000e]=\u0015\u000bUcVLX0\t\u000f}R\u0001\u0013!a\u0001\u0003\"9QI\u0003I\u0001\u0002\u0004\t\u0005bB$\u000b!\u0003\u0005\r!\u0011\u0005\b\u0013*\u0001\n\u00111\u0001L\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u0019\u0016\u0003\u0003\u000e\\\u0013\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005%d\u0013AC1o]>$\u0018\r^5p]&\u00111N\u001a\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\u0012\u0001\u001d\u0016\u0003\u0017\u000e\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A:\u0011\u0005QLX\"A;\u000b\u0005Y<\u0018\u0001\u00027b]\u001eT\u0011\u0001_\u0001\u0005U\u00064\u0018-\u0003\u0002{k\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002\u007f\u0003\u0007\u0001\"aK@\n\u0007\u0005\u0005AFA\u0002B]fD\u0001\"!\u0002\u0012\u0003\u0003\u0005\r!Q\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005-\u0001#BA\u0007\u0003'qXBAA\b\u0015\r\t\t\u0002L\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u000b\u0003\u001f\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111DA\u0011!\rY\u0013QD\u0005\u0004\u0003?a#a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003\u000b\u0019\u0012\u0011!a\u0001}\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r\u0019\u0018q\u0005\u0005\t\u0003\u000b!\u0012\u0011!a\u0001\u0003\u0006A\u0001.Y:i\u0007>$W\rF\u0001B\u0003!!xn\u0015;sS:<G#A:\u0002\r\u0015\fX/\u00197t)\u0011\tY\"!\u000e\t\u0011\u0005\u0015q#!AA\u0002y\fQAR5fY\u0012\u0004\"aT\r\u0014\u000be\ti$!\u0013\u0011\u0013\u0005}\u0012QI!B\u0003.+VBAA!\u0015\r\t\u0019\u0005L\u0001\beVtG/[7f\u0013\u0011\t9%!\u0011\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tG\u0007\u0005\u0003\u0002L\u0005ESBAA'\u0015\r\tye^\u0001\u0003S>L1!PA')\t\tI$A\u0003baBd\u0017\u0010F\u0005V\u00033\nY&!\u0018\u0002`!)q\b\ba\u0001\u0003\")Q\t\ba\u0001\u0003\")q\t\ba\u0001\u0003\")\u0011\n\ba\u0001\u0017\u00069QO\\1qa2LH\u0003BA3\u0003c\u0002RaKA4\u0003WJ1!!\u001b-\u0005\u0019y\u0005\u000f^5p]B91&!\u001cB\u0003\u0006[\u0015bAA8Y\t1A+\u001e9mKRB\u0001\"a\u001d\u001e\u0003\u0003\u0005\r!V\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA=!\r!\u00181P\u0005\u0004\u0003{*(AB(cU\u0016\u001cG\u000f"
)
public class Field implements Product, Serializable {
   private final int flags;
   private final int nameIndex;
   private final int descriptorIndex;
   private final Seq attributes;

   public static Option unapply(final Field x$0) {
      return Field$.MODULE$.unapply(x$0);
   }

   public static Field apply(final int flags, final int nameIndex, final int descriptorIndex, final Seq attributes) {
      return Field$.MODULE$.apply(flags, nameIndex, descriptorIndex, attributes);
   }

   public static Function1 tupled() {
      return Field$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Field$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int flags() {
      return this.flags;
   }

   public int nameIndex() {
      return this.nameIndex;
   }

   public int descriptorIndex() {
      return this.descriptorIndex;
   }

   public Seq attributes() {
      return this.attributes;
   }

   public Field copy(final int flags, final int nameIndex, final int descriptorIndex, final Seq attributes) {
      return new Field(flags, nameIndex, descriptorIndex, attributes);
   }

   public int copy$default$1() {
      return this.flags();
   }

   public int copy$default$2() {
      return this.nameIndex();
   }

   public int copy$default$3() {
      return this.descriptorIndex();
   }

   public Seq copy$default$4() {
      return this.attributes();
   }

   public String productPrefix() {
      return "Field";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.flags());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.nameIndex());
            break;
         case 2:
            var10000 = BoxesRunTime.boxToInteger(this.descriptorIndex());
            break;
         case 3:
            var10000 = this.attributes();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Field;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "flags";
            break;
         case 1:
            var10000 = "nameIndex";
            break;
         case 2:
            var10000 = "descriptorIndex";
            break;
         case 3:
            var10000 = "attributes";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.flags());
      var1 = Statics.mix(var1, this.nameIndex());
      var1 = Statics.mix(var1, this.descriptorIndex());
      var1 = Statics.mix(var1, Statics.anyHash(this.attributes()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label59: {
            boolean var2;
            if (x$1 instanceof Field) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label42: {
                  Field var4 = (Field)x$1;
                  if (this.flags() == var4.flags() && this.nameIndex() == var4.nameIndex() && this.descriptorIndex() == var4.descriptorIndex()) {
                     label38: {
                        Seq var10000 = this.attributes();
                        Seq var5 = var4.attributes();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label38;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label38;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label42;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label59;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public Field(final int flags, final int nameIndex, final int descriptorIndex, final Seq attributes) {
      this.flags = flags;
      this.nameIndex = nameIndex;
      this.descriptorIndex = descriptorIndex;
      this.attributes = attributes;
      Product.$init$(this);
   }
}
