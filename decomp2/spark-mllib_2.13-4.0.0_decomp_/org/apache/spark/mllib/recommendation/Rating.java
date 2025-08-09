package org.apache.spark.mllib.recommendation;

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
   bytes = "\u0006\u0005\u0005Ue\u0001\u0002\u000f\u001e\u0001\"B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\u0001\"Aa\n\u0001BK\u0002\u0013\u0005q\b\u0003\u0005Q\u0001\tE\t\u0015!\u0003A\u0011!\u0011\u0006A!f\u0001\n\u0003\u0019\u0006\u0002\u0003-\u0001\u0005#\u0005\u000b\u0011\u0002+\t\u000bi\u0003A\u0011A.\t\u000f\u0015\u0004\u0011\u0011!C\u0001M\"9!\u000eAI\u0001\n\u0003Y\u0007bB;\u0001#\u0003%\ta\u001b\u0005\bm\u0002\t\n\u0011\"\u0001x\u0011\u001dI\b!!A\u0005BiD\u0001\"a\u0002\u0001\u0003\u0003%\ta\u0010\u0005\n\u0003\u0013\u0001\u0011\u0011!C\u0001\u0003\u0017A\u0011\"a\u0006\u0001\u0003\u0003%\t%!\u0007\t\u0013\u0005\u001d\u0002!!A\u0005\u0002\u0005%\u0002\"CA\u001a\u0001\u0005\u0005I\u0011IA\u001b\u0011%\tI\u0004AA\u0001\n\u0003\nY\u0004C\u0005\u0002>\u0001\t\t\u0011\"\u0011\u0002@!I\u0011\u0011\t\u0001\u0002\u0002\u0013\u0005\u00131I\u0004\n\u0003\u0013j\u0012\u0011!E\u0001\u0003\u00172\u0001\u0002H\u000f\u0002\u0002#\u0005\u0011Q\n\u0005\u00075Z!\t!!\u001a\t\u0013\u0005ub#!A\u0005F\u0005}\u0002\"CA4-\u0005\u0005I\u0011QA5\u0011%\t9HFA\u0001\n\u0003\u000bI\bC\u0005\u0002\fZ\t\t\u0011\"\u0003\u0002\u000e\n1!+\u0019;j]\u001eT!AH\u0010\u0002\u001dI,7m\\7nK:$\u0017\r^5p]*\u0011\u0001%I\u0001\u0006[2d\u0017N\u0019\u0006\u0003E\r\nQa\u001d9be.T!\u0001J\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0013aA8sO\u000e\u00011\u0003\u0002\u0001*_I\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012a!\u00118z%\u00164\u0007C\u0001\u00161\u0013\t\t4FA\u0004Qe>$Wo\u0019;\u0011\u0005MZdB\u0001\u001b:\u001d\t)\u0004(D\u00017\u0015\t9t%\u0001\u0004=e>|GOP\u0005\u0002Y%\u0011!hK\u0001\ba\u0006\u001c7.Y4f\u0013\taTH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002;W\u0005!Qo]3s+\u0005\u0001\u0005C\u0001\u0016B\u0013\t\u00115FA\u0002J]RD3!\u0001#K!\t)\u0005*D\u0001G\u0015\t9\u0015%\u0001\u0006b]:|G/\u0019;j_:L!!\u0013$\u0003\u000bMKgnY3\"\u0003-\u000bQ\u0001\r\u00189]A\nQ!^:fe\u0002B3A\u0001#K\u0003\u001d\u0001(o\u001c3vGRD3a\u0001#K\u0003!\u0001(o\u001c3vGR\u0004\u0003f\u0001\u0003E\u0015\u00061!/\u0019;j]\u001e,\u0012\u0001\u0016\t\u0003UUK!AV\u0016\u0003\r\u0011{WO\u00197fQ\r)AIS\u0001\be\u0006$\u0018N\\4!Q\r1AIS\u0001\u0007y%t\u0017\u000e\u001e \u0015\tqs\u0006M\u0019\t\u0003;\u0002i\u0011!\b\u0005\u0006}\u001d\u0001\r\u0001\u0011\u0015\u0004=\u0012S\u0005\"\u0002(\b\u0001\u0004\u0001\u0005f\u00011E\u0015\")!k\u0002a\u0001)\"\u001a!\r\u0012&)\u0007\u001d!%*\u0001\u0003d_BLH\u0003\u0002/hQ&DqA\u0010\u0005\u0011\u0002\u0003\u0007\u0001\tC\u0004O\u0011A\u0005\t\u0019\u0001!\t\u000fIC\u0001\u0013!a\u0001)\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00017+\u0005\u0001k7&\u00018\u0011\u0005=\u001cX\"\u00019\u000b\u0005E\u0014\u0018!C;oG\",7m[3e\u0015\t95&\u0003\u0002ua\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT#\u0001=+\u0005Qk\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001|!\ra\u00181A\u0007\u0002{*\u0011ap`\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u0002\u0005!!.\u0019<b\u0013\r\t)! \u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011QBA\n!\rQ\u0013qB\u0005\u0004\u0003#Y#aA!os\"A\u0011Q\u0003\b\u0002\u0002\u0003\u0007\u0001)A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u00037\u0001b!!\b\u0002$\u00055QBAA\u0010\u0015\r\t\tcK\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0013\u0003?\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111FA\u0019!\rQ\u0013QF\u0005\u0004\u0003_Y#a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003+\u0001\u0012\u0011!a\u0001\u0003\u001b\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u001910a\u000e\t\u0011\u0005U\u0011#!AA\u0002\u0001\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u0001\u0006AAo\\*ue&tw\rF\u0001|\u0003\u0019)\u0017/^1mgR!\u00111FA#\u0011%\t)\u0002FA\u0001\u0002\u0004\ti\u0001K\u0002\u0001\t*\u000baAU1uS:<\u0007CA/\u0017'\u00151\u0012qJA.!!\t\t&a\u0016A\u0001RcVBAA*\u0015\r\t)fK\u0001\beVtG/[7f\u0013\u0011\tI&a\u0015\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\u0005\u0003\u0002^\u0005\rTBAA0\u0015\r\t\tg`\u0001\u0003S>L1\u0001PA0)\t\tY%A\u0003baBd\u0017\u0010F\u0004]\u0003W\ny'a\u001d\t\u000byJ\u0002\u0019\u0001!)\t\u0005-DI\u0013\u0005\u0006\u001df\u0001\r\u0001\u0011\u0015\u0005\u0003_\"%\nC\u0003S3\u0001\u0007A\u000b\u000b\u0003\u0002t\u0011S\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003w\n9\tE\u0003+\u0003{\n\t)C\u0002\u0002\u0000-\u0012aa\u00149uS>t\u0007C\u0002\u0016\u0002\u0004\u0002\u0003E+C\u0002\u0002\u0006.\u0012a\u0001V;qY\u0016\u001c\u0004\u0002CAE5\u0005\u0005\t\u0019\u0001/\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0010B\u0019A0!%\n\u0007\u0005MUP\u0001\u0004PE*,7\r\u001e"
)
public class Rating implements Product, Serializable {
   private final int user;
   private final int product;
   private final double rating;

   public static Option unapply(final Rating x$0) {
      return Rating$.MODULE$.unapply(x$0);
   }

   public static Rating apply(final int user, final int product, final double rating) {
      return Rating$.MODULE$.apply(user, product, rating);
   }

   public static Function1 tupled() {
      return Rating$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Rating$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int user() {
      return this.user;
   }

   public int product() {
      return this.product;
   }

   public double rating() {
      return this.rating;
   }

   public Rating copy(final int user, final int product, final double rating) {
      return new Rating(user, product, rating);
   }

   public int copy$default$1() {
      return this.user();
   }

   public int copy$default$2() {
      return this.product();
   }

   public double copy$default$3() {
      return this.rating();
   }

   public String productPrefix() {
      return "Rating";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.user());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.product());
         }
         case 2 -> {
            return BoxesRunTime.boxToDouble(this.rating());
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
      return x$1 instanceof Rating;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "user";
         }
         case 1 -> {
            return "product";
         }
         case 2 -> {
            return "rating";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.user());
      var1 = Statics.mix(var1, this.product());
      var1 = Statics.mix(var1, Statics.doubleHash(this.rating()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof Rating) {
               Rating var4 = (Rating)x$1;
               if (this.user() == var4.user() && this.product() == var4.product() && this.rating() == var4.rating() && var4.canEqual(this)) {
                  break label40;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Rating(final int user, final int product, final double rating) {
      this.user = user;
      this.product = product;
      this.rating = rating;
      Product.$init$(this);
   }
}
