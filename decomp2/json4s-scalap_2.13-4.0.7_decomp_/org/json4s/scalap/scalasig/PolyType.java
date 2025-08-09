package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005I!AQ\b\u0001BK\u0002\u0013\u0005a\b\u0003\u0005F\u0001\tE\t\u0015!\u0003@\u0011\u00151\u0005\u0001\"\u0001H\u0011\u001dY\u0005!!A\u0005\u00021Cqa\u0014\u0001\u0012\u0002\u0013\u0005\u0001\u000bC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000fy\u0003\u0011\u0011!C!?\"9\u0001\u000eAA\u0001\n\u0003I\u0007bB7\u0001\u0003\u0003%\tA\u001c\u0005\bi\u0002\t\t\u0011\"\u0011v\u0011\u001da\b!!A\u0005\u0002uD\u0011\"!\u0002\u0001\u0003\u0003%\t%a\u0002\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\b\u0001\u0005\u0005I\u0011IA\t\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)bB\u0005\u0002\u001ai\t\t\u0011#\u0001\u0002\u001c\u0019A\u0011DGA\u0001\u0012\u0003\ti\u0002\u0003\u0004G'\u0011\u0005\u0011Q\u0007\u0005\n\u0003\u001f\u0019\u0012\u0011!C#\u0003#A\u0011\"a\u000e\u0014\u0003\u0003%\t)!\u000f\t\u0013\u0005}2#!A\u0005\u0002\u0006\u0005\u0003\"CA*'\u0005\u0005I\u0011BA+\u0005!\u0001v\u000e\\=UsB,'BA\u000e\u001d\u0003!\u00198-\u00197bg&<'BA\u000f\u001f\u0003\u0019\u00198-\u00197ba*\u0011q\u0004I\u0001\u0007UN|g\u000eN:\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0013)]A\u0011QEJ\u0007\u00025%\u0011qE\u0007\u0002\u0005)f\u0004X\r\u0005\u0002*Y5\t!FC\u0001,\u0003\u0015\u00198-\u00197b\u0013\ti#FA\u0004Qe>$Wo\u0019;\u0011\u0005=:dB\u0001\u00196\u001d\t\tD'D\u00013\u0015\t\u0019$%\u0001\u0004=e>|GOP\u0005\u0002W%\u0011aGK\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0014H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00027U\u00059A/\u001f9f%\u00164W#\u0001\u0013\u0002\u0011QL\b/\u001a*fM\u0002\nqa]=nE>d7/F\u0001@!\ry\u0003IQ\u0005\u0003\u0003f\u00121aU3r!\t)3)\u0003\u0002E5\tQA+\u001f9f'fl'm\u001c7\u0002\u0011MLXNY8mg\u0002\na\u0001P5oSRtDc\u0001%J\u0015B\u0011Q\u0005\u0001\u0005\u0006u\u0015\u0001\r\u0001\n\u0005\u0006{\u0015\u0001\raP\u0001\u0005G>\u0004\u0018\u0010F\u0002I\u001b:CqA\u000f\u0004\u0011\u0002\u0003\u0007A\u0005C\u0004>\rA\u0005\t\u0019A \u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0011K\u000b\u0002%%.\n1\u000b\u0005\u0002U36\tQK\u0003\u0002W/\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u00031*\n!\"\u00198o_R\fG/[8o\u0013\tQVKA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001^U\ty$+A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002AB\u0011\u0011MZ\u0007\u0002E*\u00111\rZ\u0001\u0005Y\u0006twMC\u0001f\u0003\u0011Q\u0017M^1\n\u0005\u001d\u0014'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001k!\tI3.\u0003\u0002mU\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011qN\u001d\t\u0003SAL!!\u001d\u0016\u0003\u0007\u0005s\u0017\u0010C\u0004t\u0017\u0005\u0005\t\u0019\u00016\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u00051\bcA<{_6\t\u0001P\u0003\u0002zU\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005mD(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2A`A\u0002!\tIs0C\u0002\u0002\u0002)\u0012qAQ8pY\u0016\fg\u000eC\u0004t\u001b\u0005\u0005\t\u0019A8\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004A\u0006%\u0001bB:\u000f\u0003\u0003\u0005\rA[\u0001\tQ\u0006\u001c\bnQ8eKR\t!.\u0001\u0005u_N#(/\u001b8h)\u0005\u0001\u0017AB3rk\u0006d7\u000fF\u0002\u007f\u0003/Aqa]\t\u0002\u0002\u0003\u0007q.\u0001\u0005Q_2LH+\u001f9f!\t)3cE\u0003\u0014\u0003?\tY\u0003E\u0004\u0002\"\u0005\u001dBe\u0010%\u000e\u0005\u0005\r\"bAA\u0013U\u00059!/\u001e8uS6,\u0017\u0002BA\u0015\u0003G\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\ti#a\r\u000e\u0005\u0005=\"bAA\u0019I\u0006\u0011\u0011n\\\u0005\u0004q\u0005=BCAA\u000e\u0003\u0015\t\u0007\u000f\u001d7z)\u0015A\u00151HA\u001f\u0011\u0015Qd\u00031\u0001%\u0011\u0015id\u00031\u0001@\u0003\u001d)h.\u00199qYf$B!a\u0011\u0002PA)\u0011&!\u0012\u0002J%\u0019\u0011q\t\u0016\u0003\r=\u0003H/[8o!\u0015I\u00131\n\u0013@\u0013\r\tiE\u000b\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005Es#!AA\u0002!\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u0006E\u0002b\u00033J1!a\u0017c\u0005\u0019y%M[3di\u0002"
)
public class PolyType extends Type implements Product, Serializable {
   private final Type typeRef;
   private final Seq symbols;

   public static Option unapply(final PolyType x$0) {
      return PolyType$.MODULE$.unapply(x$0);
   }

   public static PolyType apply(final Type typeRef, final Seq symbols) {
      return PolyType$.MODULE$.apply(typeRef, symbols);
   }

   public static Function1 tupled() {
      return PolyType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return PolyType$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Type typeRef() {
      return this.typeRef;
   }

   public Seq symbols() {
      return this.symbols;
   }

   public PolyType copy(final Type typeRef, final Seq symbols) {
      return new PolyType(typeRef, symbols);
   }

   public Type copy$default$1() {
      return this.typeRef();
   }

   public Seq copy$default$2() {
      return this.symbols();
   }

   public String productPrefix() {
      return "PolyType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.typeRef();
            break;
         case 1:
            var10000 = this.symbols();
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
      return x$1 instanceof PolyType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "typeRef";
            break;
         case 1:
            var10000 = "symbols";
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
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof PolyType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     PolyType var4 = (PolyType)x$1;
                     Type var10000 = this.typeRef();
                     Type var5 = var4.typeRef();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Seq var7 = this.symbols();
                     Seq var6 = var4.symbols();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public PolyType(final Type typeRef, final Seq symbols) {
      this.typeRef = typeRef;
      this.symbols = symbols;
      Product.$init$(this);
   }
}
