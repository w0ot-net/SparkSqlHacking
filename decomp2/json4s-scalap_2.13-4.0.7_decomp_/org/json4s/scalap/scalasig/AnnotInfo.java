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
   bytes = "\u0006\u0005\u0005eb\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t}\u0001\u0011\t\u0012)A\u0005q!)q\b\u0001C\u0001\u0001\"9A\tAA\u0001\n\u0003)\u0005bB$\u0001#\u0003%\t\u0001\u0013\u0005\b'\u0002\t\t\u0011\"\u0011U\u0011\u001di\u0006!!A\u0005\u0002yCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004g\u0001\u0005\u0005I\u0011I4\t\u000f9\u0004\u0011\u0011!C\u0001_\"9A\u000fAA\u0001\n\u0003*\bbB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011\u001dY\b!!A\u0005Bq<qA`\f\u0002\u0002#\u0005qP\u0002\u0005\u0017/\u0005\u0005\t\u0012AA\u0001\u0011\u0019y\u0004\u0003\"\u0001\u0002\u001a!9\u0011\u0010EA\u0001\n\u000bR\b\"CA\u000e!\u0005\u0005I\u0011QA\u000f\u0011%\t\t\u0003EA\u0001\n\u0003\u000b\u0019\u0003C\u0005\u00020A\t\t\u0011\"\u0003\u00022\tI\u0011I\u001c8pi&sgm\u001c\u0006\u00031e\t\u0001b]2bY\u0006\u001c\u0018n\u001a\u0006\u00035m\taa]2bY\u0006\u0004(B\u0001\u000f\u001e\u0003\u0019Q7o\u001c85g*\ta$A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001C\u001dR\u0003C\u0001\u0012&\u001b\u0005\u0019#\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a#AB!osJ+g\r\u0005\u0002#Q%\u0011\u0011f\t\u0002\b!J|G-^2u!\tY3G\u0004\u0002-c9\u0011Q\u0006M\u0007\u0002])\u0011qfH\u0001\u0007yI|w\u000e\u001e \n\u0003\u0011J!AM\u0012\u0002\u000fA\f7m[1hK&\u0011A'\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003e\r\nAA]3ggV\t\u0001\bE\u0002,smJ!AO\u001b\u0003\u0007M+\u0017\u000f\u0005\u0002#y%\u0011Qh\t\u0002\u0004\u0013:$\u0018!\u0002:fMN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002B\u0007B\u0011!\tA\u0007\u0002/!)ag\u0001a\u0001q\u0005!1m\u001c9z)\t\te\tC\u00047\tA\u0005\t\u0019\u0001\u001d\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0011J\u000b\u00029\u0015.\n1\n\u0005\u0002M#6\tQJ\u0003\u0002O\u001f\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003!\u000e\n!\"\u00198o_R\fG/[8o\u0013\t\u0011VJA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A+\u0011\u0005Y[V\"A,\u000b\u0005aK\u0016\u0001\u00027b]\u001eT\u0011AW\u0001\u0005U\u00064\u0018-\u0003\u0002]/\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012aO\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t\tG\r\u0005\u0002#E&\u00111m\t\u0002\u0004\u0003:L\bbB3\t\u0003\u0003\u0005\raO\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003!\u00042!\u001b7b\u001b\u0005Q'BA6$\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003[*\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0011\u0001o\u001d\t\u0003EEL!A]\u0012\u0003\u000f\t{w\u000e\\3b]\"9QMCA\u0001\u0002\u0004\t\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$\"!\u0016<\t\u000f\u0015\\\u0011\u0011!a\u0001w\u0005A\u0001.Y:i\u0007>$W\rF\u0001<\u0003!!xn\u0015;sS:<G#A+\u0002\r\u0015\fX/\u00197t)\t\u0001X\u0010C\u0004f\u001d\u0005\u0005\t\u0019A1\u0002\u0013\u0005sgn\u001c;J]\u001a|\u0007C\u0001\"\u0011'\u0015\u0001\u00121AA\b!\u0019\t)!a\u00039\u00036\u0011\u0011q\u0001\u0006\u0004\u0003\u0013\u0019\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u001b\t9AA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\u0005\u0002\u00185\u0011\u00111\u0003\u0006\u0004\u0003+I\u0016AA5p\u0013\r!\u00141\u0003\u000b\u0002\u007f\u0006)\u0011\r\u001d9msR\u0019\u0011)a\b\t\u000bY\u001a\u0002\u0019\u0001\u001d\u0002\u000fUt\u0017\r\u001d9msR!\u0011QEA\u0016!\u0011\u0011\u0013q\u0005\u001d\n\u0007\u0005%2E\u0001\u0004PaRLwN\u001c\u0005\t\u0003[!\u0012\u0011!a\u0001\u0003\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005M\u0002c\u0001,\u00026%\u0019\u0011qG,\u0003\r=\u0013'.Z2u\u0001"
)
public class AnnotInfo implements Product, Serializable {
   private final Seq refs;

   public static Option unapply(final AnnotInfo x$0) {
      return AnnotInfo$.MODULE$.unapply(x$0);
   }

   public static AnnotInfo apply(final Seq refs) {
      return AnnotInfo$.MODULE$.apply(refs);
   }

   public static Function1 andThen(final Function1 g) {
      return AnnotInfo$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return AnnotInfo$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq refs() {
      return this.refs;
   }

   public AnnotInfo copy(final Seq refs) {
      return new AnnotInfo(refs);
   }

   public Seq copy$default$1() {
      return this.refs();
   }

   public String productPrefix() {
      return "AnnotInfo";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.refs();
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
      return x$1 instanceof AnnotInfo;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "refs";
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
         label53: {
            boolean var2;
            if (x$1 instanceof AnnotInfo) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     AnnotInfo var4 = (AnnotInfo)x$1;
                     Seq var10000 = this.refs();
                     Seq var5 = var4.refs();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public AnnotInfo(final Seq refs) {
      this.refs = refs;
      Product.$init$(this);
   }
}
