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
   bytes = "\u0006\u0005\u0005-c\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005I!AQ\b\u0001BK\u0002\u0013\u00051\b\u0003\u0005?\u0001\tE\t\u0015!\u0003%\u0011\u0015y\u0004\u0001\"\u0001A\u0011\u001d!\u0005!!A\u0005\u0002\u0015Cq\u0001\u0013\u0001\u0012\u0002\u0013\u0005\u0011\nC\u0004U\u0001E\u0005I\u0011A%\t\u000fU\u0003\u0011\u0011!C!-\"9q\fAA\u0001\n\u0003\u0001\u0007b\u00023\u0001\u0003\u0003%\t!\u001a\u0005\bW\u0002\t\t\u0011\"\u0011m\u0011\u001d\u0019\b!!A\u0005\u0002QDq!\u001f\u0001\u0002\u0002\u0013\u0005#\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\"I\u0011\u0011\u0001\u0001\u0002\u0002\u0013\u0005\u00131A\u0004\n\u0003\u000fQ\u0012\u0011!E\u0001\u0003\u00131\u0001\"\u0007\u000e\u0002\u0002#\u0005\u00111\u0002\u0005\u0007\u007fM!\t!a\t\t\u000fy\u001c\u0012\u0011!C#\u007f\"I\u0011QE\n\u0002\u0002\u0013\u0005\u0015q\u0005\u0005\n\u0003[\u0019\u0012\u0011!CA\u0003_A\u0011\"!\u0011\u0014\u0003\u0003%I!a\u0011\u0003\u001dQK\b/\u001a\"pk:$7\u000fV=qK*\u00111\u0004H\u0001\tg\u000e\fG.Y:jO*\u0011QDH\u0001\u0007g\u000e\fG.\u00199\u000b\u0005}\u0001\u0013A\u00026t_:$4OC\u0001\"\u0003\ry'oZ\u0002\u0001'\u0011\u0001A\u0005\u000b\u0018\u0011\u0005\u00152S\"\u0001\u000e\n\u0005\u001dR\"\u0001\u0002+za\u0016\u0004\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00020o9\u0011\u0001'\u000e\b\u0003cQj\u0011A\r\u0006\u0003g\t\na\u0001\u0010:p_Rt\u0014\"A\u0016\n\u0005YR\u0013a\u00029bG.\fw-Z\u0005\u0003qe\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\u000e\u0016\u0002\u000b1|w/\u001a:\u0016\u0003\u0011\na\u0001\\8xKJ\u0004\u0013!B;qa\u0016\u0014\u0018AB;qa\u0016\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0004\u0003\n\u001b\u0005CA\u0013\u0001\u0011\u0015QT\u00011\u0001%\u0011\u0015iT\u00011\u0001%\u0003\u0011\u0019w\u000e]=\u0015\u0007\u00053u\tC\u0004;\rA\u0005\t\u0019\u0001\u0013\t\u000fu2\u0001\u0013!a\u0001I\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001&+\u0005\u0011Z5&\u0001'\u0011\u00055\u0013V\"\u0001(\u000b\u0005=\u0003\u0016!C;oG\",7m[3e\u0015\t\t&&\u0001\u0006b]:|G/\u0019;j_:L!a\u0015(\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011a\u0017M\\4\u000b\u0003q\u000bAA[1wC&\u0011a,\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u0005\u0004\"!\u000b2\n\u0005\rT#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00014j!\tIs-\u0003\u0002iU\t\u0019\u0011I\\=\t\u000f)\\\u0011\u0011!a\u0001C\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001c\t\u0004]F4W\"A8\u000b\u0005AT\u0013AC2pY2,7\r^5p]&\u0011!o\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002vqB\u0011\u0011F^\u0005\u0003o*\u0012qAQ8pY\u0016\fg\u000eC\u0004k\u001b\u0005\u0005\t\u0019\u00014\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003/nDqA\u001b\b\u0002\u0002\u0003\u0007\u0011-\u0001\u0005iCND7i\u001c3f)\u0005\t\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003]\u000ba!Z9vC2\u001cHcA;\u0002\u0006!9!.EA\u0001\u0002\u00041\u0017A\u0004+za\u0016\u0014u.\u001e8egRK\b/\u001a\t\u0003KM\u0019RaEA\u0007\u00033\u0001r!a\u0004\u0002\u0016\u0011\"\u0013)\u0004\u0002\u0002\u0012)\u0019\u00111\u0003\u0016\u0002\u000fI,h\u000e^5nK&!\u0011qCA\t\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u00037\t\t#\u0004\u0002\u0002\u001e)\u0019\u0011qD.\u0002\u0005%|\u0017b\u0001\u001d\u0002\u001eQ\u0011\u0011\u0011B\u0001\u0006CB\u0004H.\u001f\u000b\u0006\u0003\u0006%\u00121\u0006\u0005\u0006uY\u0001\r\u0001\n\u0005\u0006{Y\u0001\r\u0001J\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t$!\u0010\u0011\u000b%\n\u0019$a\u000e\n\u0007\u0005U\"F\u0001\u0004PaRLwN\u001c\t\u0006S\u0005eB\u0005J\u0005\u0004\u0003wQ#A\u0002+va2,'\u0007\u0003\u0005\u0002@]\t\t\u00111\u0001B\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u000b\u00022\u0001WA$\u0013\r\tI%\u0017\u0002\u0007\u001f\nTWm\u0019;"
)
public class TypeBoundsType extends Type implements Product, Serializable {
   private final Type lower;
   private final Type upper;

   public static Option unapply(final TypeBoundsType x$0) {
      return TypeBoundsType$.MODULE$.unapply(x$0);
   }

   public static TypeBoundsType apply(final Type lower, final Type upper) {
      return TypeBoundsType$.MODULE$.apply(lower, upper);
   }

   public static Function1 tupled() {
      return TypeBoundsType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return TypeBoundsType$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Type lower() {
      return this.lower;
   }

   public Type upper() {
      return this.upper;
   }

   public TypeBoundsType copy(final Type lower, final Type upper) {
      return new TypeBoundsType(lower, upper);
   }

   public Type copy$default$1() {
      return this.lower();
   }

   public Type copy$default$2() {
      return this.upper();
   }

   public String productPrefix() {
      return "TypeBoundsType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.lower();
            break;
         case 1:
            var10000 = this.upper();
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
      return x$1 instanceof TypeBoundsType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "lower";
            break;
         case 1:
            var10000 = "upper";
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
            if (x$1 instanceof TypeBoundsType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     TypeBoundsType var4 = (TypeBoundsType)x$1;
                     Type var10000 = this.lower();
                     Type var5 = var4.lower();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     var10000 = this.upper();
                     Type var6 = var4.upper();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var6)) {
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

   public TypeBoundsType(final Type lower, final Type upper) {
      this.lower = lower;
      this.upper = upper;
      Product.$init$(this);
   }
}
