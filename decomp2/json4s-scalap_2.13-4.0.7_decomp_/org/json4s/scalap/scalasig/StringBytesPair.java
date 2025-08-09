package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d\u0001B\r\u001b\u0001\u000eB\u0001\"\u000f\u0001\u0003\u0016\u0004%\tA\u000f\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005w!AA\t\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005M\u0001\tE\t\u0015!\u0003G\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u001d\u0019\u0006!!A\u0005\u0002QCqa\u0016\u0001\u0012\u0002\u0013\u0005\u0001\fC\u0004d\u0001E\u0005I\u0011\u00013\t\u000f\u0019\u0004\u0011\u0011!C!O\"9q\u000eAA\u0001\n\u0003\u0001\bb\u0002;\u0001\u0003\u0003%\t!\u001e\u0005\bw\u0002\t\t\u0011\"\u0011}\u0011%\t9\u0001AA\u0001\n\u0003\tI\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131\u0004\u0005\n\u0003;\u0001\u0011\u0011!C!\u0003?A\u0011\"!\t\u0001\u0003\u0003%\t%a\t\b\u0013\u0005\u001d\"$!A\t\u0002\u0005%b\u0001C\r\u001b\u0003\u0003E\t!a\u000b\t\r5\u001bB\u0011AA\"\u0011%\tibEA\u0001\n\u000b\ny\u0002C\u0005\u0002FM\t\t\u0011\"!\u0002H!I\u0011QJ\n\u0002\u0002\u0013\u0005\u0015q\n\u0005\n\u0003C\u001a\u0012\u0011!C\u0005\u0003G\u0012qb\u0015;sS:<')\u001f;fgB\u000b\u0017N\u001d\u0006\u00037q\t\u0001b]2bY\u0006\u001c\u0018n\u001a\u0006\u0003;y\taa]2bY\u0006\u0004(BA\u0010!\u0003\u0019Q7o\u001c85g*\t\u0011%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001I)j\u0003CA\u0013)\u001b\u00051#\"A\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005%2#AB!osJ+g\r\u0005\u0002&W%\u0011AF\n\u0002\b!J|G-^2u!\tqcG\u0004\u00020i9\u0011\u0001gM\u0007\u0002c)\u0011!GI\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dJ!!\u000e\u0014\u0002\u000fA\f7m[1hK&\u0011q\u0007\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003k\u0019\naa\u001d;sS:<W#A\u001e\u0011\u0005q\u0002eBA\u001f?!\t\u0001d%\u0003\u0002@M\u00051\u0001K]3eK\u001aL!!\u0011\"\u0003\rM#(/\u001b8h\u0015\tyd%A\u0004tiJLgn\u001a\u0011\u0002\u000b\tLH/Z:\u0016\u0003\u0019\u00032!J$J\u0013\tAeEA\u0003BeJ\f\u0017\u0010\u0005\u0002&\u0015&\u00111J\n\u0002\u0005\u0005f$X-\u0001\u0004csR,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007=\u000b&\u000b\u0005\u0002Q\u00015\t!\u0004C\u0003:\u000b\u0001\u00071\bC\u0003E\u000b\u0001\u0007a)\u0001\u0003d_BLHcA(V-\"9\u0011H\u0002I\u0001\u0002\u0004Y\u0004b\u0002#\u0007!\u0003\u0005\rAR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005I&FA\u001e[W\u0005Y\u0006C\u0001/b\u001b\u0005i&B\u00010`\u0003%)hn\u00195fG.,GM\u0003\u0002aM\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\tl&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A3+\u0005\u0019S\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001i!\tIg.D\u0001k\u0015\tYG.\u0001\u0003mC:<'\"A7\u0002\t)\fg/Y\u0005\u0003\u0003*\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u001d\t\u0003KIL!a\u001d\u0014\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005YL\bCA\u0013x\u0013\tAhEA\u0002B]fDqA_\u0006\u0002\u0002\u0003\u0007\u0011/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002{B!a0a\u0001w\u001b\u0005y(bAA\u0001M\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0007\u0005\u0015qP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0006\u0003#\u00012!JA\u0007\u0013\r\tyA\n\u0002\b\u0005>|G.Z1o\u0011\u001dQX\"!AA\u0002Y\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u0001.a\u0006\t\u000fit\u0011\u0011!a\u0001c\u0006A\u0001.Y:i\u0007>$W\rF\u0001r\u0003!!xn\u0015;sS:<G#\u00015\u0002\r\u0015\fX/\u00197t)\u0011\tY!!\n\t\u000fi\f\u0012\u0011!a\u0001m\u0006y1\u000b\u001e:j]\u001e\u0014\u0015\u0010^3t!\u0006L'\u000f\u0005\u0002Q'M)1#!\f\u0002:A9\u0011qFA\u001bw\u0019{UBAA\u0019\u0015\r\t\u0019DJ\u0001\beVtG/[7f\u0013\u0011\t9$!\r\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0005\u0003\u0002<\u0005\u0005SBAA\u001f\u0015\r\ty\u0004\\\u0001\u0003S>L1aNA\u001f)\t\tI#A\u0003baBd\u0017\u0010F\u0003P\u0003\u0013\nY\u0005C\u0003:-\u0001\u00071\bC\u0003E-\u0001\u0007a)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005E\u0013Q\f\t\u0006K\u0005M\u0013qK\u0005\u0004\u0003+2#AB(qi&|g\u000eE\u0003&\u00033Zd)C\u0002\u0002\\\u0019\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA0/\u0005\u0005\t\u0019A(\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002fA\u0019\u0011.a\u001a\n\u0007\u0005%$N\u0001\u0004PE*,7\r\u001e"
)
public class StringBytesPair implements Product, Serializable {
   private final String string;
   private final byte[] bytes;

   public static Option unapply(final StringBytesPair x$0) {
      return StringBytesPair$.MODULE$.unapply(x$0);
   }

   public static StringBytesPair apply(final String string, final byte[] bytes) {
      return StringBytesPair$.MODULE$.apply(string, bytes);
   }

   public static Function1 tupled() {
      return StringBytesPair$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return StringBytesPair$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String string() {
      return this.string;
   }

   public byte[] bytes() {
      return this.bytes;
   }

   public StringBytesPair copy(final String string, final byte[] bytes) {
      return new StringBytesPair(string, bytes);
   }

   public String copy$default$1() {
      return this.string();
   }

   public byte[] copy$default$2() {
      return this.bytes();
   }

   public String productPrefix() {
      return "StringBytesPair";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.string();
            break;
         case 1:
            var10000 = this.bytes();
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
      return x$1 instanceof StringBytesPair;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "string";
            break;
         case 1:
            var10000 = "bytes";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label55: {
            boolean var2;
            if (x$1 instanceof StringBytesPair) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label38: {
                  label37: {
                     StringBytesPair var4 = (StringBytesPair)x$1;
                     String var10000 = this.string();
                     String var5 = var4.string();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label37;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label37;
                     }

                     if (this.bytes() == var4.bytes() && var4.canEqual(this)) {
                        var7 = true;
                        break label38;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label55;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public StringBytesPair(final String string, final byte[] bytes) {
      this.string = string;
      this.bytes = bytes;
      Product.$init$(this);
   }
}
