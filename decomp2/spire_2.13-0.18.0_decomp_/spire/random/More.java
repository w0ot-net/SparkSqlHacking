package spire.random;

import java.io.Serializable;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001\u0002\f\u0018\u0001rA\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u000f\u0002\u0011\t\u0012)A\u0005\t\")\u0001\n\u0001C\u0001\u0013\"9A\nAA\u0001\n\u0003i\u0005bB+\u0001#\u0003%\tA\u0016\u0005\bG\u0002\t\t\u0011\"\u0011e\u0011\u001di\u0007!!A\u0005\u00029DqA\u001d\u0001\u0002\u0002\u0013\u00051\u000fC\u0004w\u0001\u0005\u0005I\u0011I<\t\u000fy\u0004\u0011\u0011!C\u0001\u007f\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00131\u0002\u0005\n\u0003\u001f\u0001\u0011\u0011!C!\u0003#A\u0011\"a\u0005\u0001\u0003\u0003%\t%!\u0006\t\u0013\u0005]\u0001!!A\u0005B\u0005eq!CA\u000f/\u0005\u0005\t\u0012AA\u0010\r!1r#!A\t\u0002\u0005\u0005\u0002B\u0002%\u0011\t\u0003\ti\u0003C\u0005\u0002\u0014A\t\t\u0011\"\u0012\u0002\u0016!I\u0011q\u0006\t\u0002\u0002\u0013\u0005\u0015\u0011\u0007\u0005\n\u0003\u0003\u0002\u0012\u0011!CA\u0003\u0007B\u0011\"a\u0017\u0011\u0003\u0003%I!!\u0018\u0003\t5{'/\u001a\u0006\u00031e\taA]1oI>l'\"\u0001\u000e\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011QDK\n\u0006\u0001y!3G\u000e\t\u0003?\tj\u0011\u0001\t\u0006\u0002C\u0005)1oY1mC&\u00111\u0005\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\u00152\u0003&D\u0001\u0018\u0013\t9sC\u0001\u0002PaB\u0011\u0011F\u000b\u0007\u0001\t\u0019Y\u0003\u0001\"b\u0001Y\t\t\u0011)\u0005\u0002.aA\u0011qDL\u0005\u0003_\u0001\u0012qAT8uQ&tw\r\u0005\u0002 c%\u0011!\u0007\t\u0002\u0004\u0003:L\bCA\u00105\u0013\t)\u0004EA\u0004Qe>$Wo\u0019;\u0011\u0005]zdB\u0001\u001d>\u001d\tID(D\u0001;\u0015\tY4$\u0001\u0004=e>|GOP\u0005\u0002C%\u0011a\bI\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0015I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002?A\u0005\t1.F\u0001E!\ryR\tJ\u0005\u0003\r\u0002\u0012\u0011BR;oGRLwN\u001c\u0019\u0002\u0005-\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002K\u0017B\u0019Q\u0005\u0001\u0015\t\u000b\t\u001b\u0001\u0019\u0001#\u0002\t\r|\u0007/_\u000b\u0003\u001dF#\"a\u0014*\u0011\u0007\u0015\u0002\u0001\u000b\u0005\u0002*#\u0012)1\u0006\u0002b\u0001Y!9!\t\u0002I\u0001\u0002\u0004\u0019\u0006cA\u0010F)B\u0019QE\n)\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011qKY\u000b\u00021*\u0012A)W\u0016\u00025B\u00111\fY\u0007\u00029*\u0011QLX\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u0018\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002b9\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b-*!\u0019\u0001\u0017\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005)\u0007C\u00014l\u001b\u00059'B\u00015j\u0003\u0011a\u0017M\\4\u000b\u0003)\fAA[1wC&\u0011An\u001a\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003=\u0004\"a\b9\n\u0005E\u0004#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001\u0019u\u0011\u001d)\b\"!AA\u0002=\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001=\u0011\u0007ed\b'D\u0001{\u0015\tY\b%\u0001\u0006d_2dWm\u0019;j_:L!! >\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0003\t9\u0001E\u0002 \u0003\u0007I1!!\u0002!\u0005\u001d\u0011un\u001c7fC:Dq!\u001e\u0006\u0002\u0002\u0003\u0007\u0001'\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA3\u0002\u000e!9QoCA\u0001\u0002\u0004y\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003=\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002K\u00061Q-];bYN$B!!\u0001\u0002\u001c!9QODA\u0001\u0002\u0004\u0001\u0014\u0001B'pe\u0016\u0004\"!\n\t\u0014\tAq\u00121\u0005\t\u0005\u0003K\tY#\u0004\u0002\u0002()\u0019\u0011\u0011F5\u0002\u0005%|\u0017b\u0001!\u0002(Q\u0011\u0011qD\u0001\u0006CB\u0004H._\u000b\u0005\u0003g\tI\u0004\u0006\u0003\u00026\u0005m\u0002\u0003B\u0013\u0001\u0003o\u00012!KA\u001d\t\u0015Y3C1\u0001-\u0011\u0019\u00115\u00031\u0001\u0002>A!q$RA !\u0011)c%a\u000e\u0002\u000fUt\u0017\r\u001d9msV!\u0011QIA*)\u0011\t9%!\u0016\u0011\u000b}\tI%!\u0014\n\u0007\u0005-\u0003E\u0001\u0004PaRLwN\u001c\t\u0005?\u0015\u000by\u0005\u0005\u0003&M\u0005E\u0003cA\u0015\u0002T\u0011)1\u0006\u0006b\u0001Y!I\u0011q\u000b\u000b\u0002\u0002\u0003\u0007\u0011\u0011L\u0001\u0004q\u0012\u0002\u0004\u0003B\u0013\u0001\u0003#\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0018\u0011\u0007\u0019\f\t'C\u0002\u0002d\u001d\u0014aa\u00142kK\u000e$\b"
)
public class More implements Op, Product, Serializable {
   private final Function0 k;

   public static Option unapply(final More x$0) {
      return More$.MODULE$.unapply(x$0);
   }

   public static More apply(final Function0 k) {
      return More$.MODULE$.apply(k);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Op flatMap(final Function1 f) {
      return Op.flatMap$(this, f);
   }

   public Op map(final Function1 f) {
      return Op.map$(this, f);
   }

   public final Either resume(final Generator gen) {
      return Op.resume$(this, gen);
   }

   public Object run(final Generator gen) {
      return Op.run$(this, gen);
   }

   public Function0 k() {
      return this.k;
   }

   public More copy(final Function0 k) {
      return new More(k);
   }

   public Function0 copy$default$1() {
      return this.k();
   }

   public String productPrefix() {
      return "More";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.k();
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
      return x$1 instanceof More;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "k";
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
            if (x$1 instanceof More) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     More var4 = (More)x$1;
                     Function0 var10000 = this.k();
                     Function0 var5 = var4.k();
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

   public More(final Function0 k) {
      this.k = k;
      Op.$init$(this);
      Product.$init$(this);
   }
}
