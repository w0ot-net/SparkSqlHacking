package org.json4s;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b\u0001\u0002\r\u001a\u0001zA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f\u0015!!\t\u0001\u0001;\u0011\u0015\u0019\u0005\u0001\"\u0001:\u0011\u001d!\u0005!!A\u0005\u0002\u0015Cqa\u0012\u0001\u0012\u0002\u0013\u0005\u0001\nC\u0004T\u0001\u0005\u0005I\u0011\t+\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAA\u0001\n\u0003\u0019\u0007bB5\u0001\u0003\u0003%\tE\u001b\u0005\bc\u0002\t\t\u0011\"\u0001s\u0011\u001d9\b!!A\u0005BaDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\u001eI\u00111A\r\u0002\u0002#\u0005\u0011Q\u0001\u0004\t1e\t\t\u0011#\u0001\u0002\b!1aH\u0005C\u0001\u0003?Aq\u0001 \n\u0002\u0002\u0013\u0015S\u0010C\u0005\u0002\"I\t\t\u0011\"!\u0002$!I\u0011q\u0005\n\u0002\u0002\u0013\u0005\u0015\u0011\u0006\u0005\n\u0003k\u0011\u0012\u0011!C\u0005\u0003o\u0011QA\u0013'p]\u001eT!AG\u000e\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005a\u0012aA8sO\u000e\u00011#\u0002\u0001 G\u0019b\u0003C\u0001\u0011\"\u001b\u0005I\u0012B\u0001\u0012\u001a\u0005\u0019Qe+\u00197vKB\u0011\u0001\u0005J\u0005\u0003Ke\u0011qA\u0013(v[\n,'\u000f\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003FA\u0004Qe>$Wo\u0019;\u0011\u00055*dB\u0001\u00184\u001d\ty#'D\u00011\u0015\t\tT$\u0001\u0004=e>|GOP\u0005\u0002S%\u0011A\u0007K\u0001\ba\u0006\u001c7.Y4f\u0013\t1tG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00025Q\u0005\u0019a.^7\u0016\u0003i\u0002\"aJ\u001e\n\u0005qB#\u0001\u0002'p]\u001e\fAA\\;nA\u00051A(\u001b8jiz\"\"\u0001Q!\u0011\u0005\u0001\u0002\u0001\"\u0002\u001d\u0004\u0001\u0004Q$A\u0002,bYV,7/\u0001\u0004wC2,Xm]\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002A\r\"9\u0001H\u0002I\u0001\u0002\u0004Q\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0013*\u0012!HS\u0016\u0002\u0017B\u0011A*U\u0007\u0002\u001b*\u0011ajT\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0015\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002S\u001b\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005)\u0006C\u0001,\\\u001b\u00059&B\u0001-Z\u0003\u0011a\u0017M\\4\u000b\u0003i\u000bAA[1wC&\u0011Al\u0016\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003}\u0003\"a\n1\n\u0005\u0005D#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00013h!\t9S-\u0003\u0002gQ\t\u0019\u0011I\\=\t\u000f!T\u0011\u0011!a\u0001?\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012a\u001b\t\u0004Y>$W\"A7\u000b\u00059D\u0013AC2pY2,7\r^5p]&\u0011\u0001/\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002tmB\u0011q\u0005^\u0005\u0003k\"\u0012qAQ8pY\u0016\fg\u000eC\u0004i\u0019\u0005\u0005\t\u0019\u00013\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003+fDq\u0001[\u0007\u0002\u0002\u0003\u0007q,\u0001\u0005iCND7i\u001c3f)\u0005y\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003U\u000ba!Z9vC2\u001cHcA:\u0002\u0002!9\u0001\u000eEA\u0001\u0002\u0004!\u0017!\u0002&M_:<\u0007C\u0001\u0011\u0013'\u0015\u0011\u0012\u0011BA\u000b!\u0019\tY!!\u0005;\u00016\u0011\u0011Q\u0002\u0006\u0004\u0003\u001fA\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003'\tiAA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!a\u0006\u0002\u001e5\u0011\u0011\u0011\u0004\u0006\u0004\u00037I\u0016AA5p\u0013\r1\u0014\u0011\u0004\u000b\u0003\u0003\u000b\tQ!\u00199qYf$2\u0001QA\u0013\u0011\u0015AT\u00031\u0001;\u0003\u001d)h.\u00199qYf$B!a\u000b\u00022A!q%!\f;\u0013\r\ty\u0003\u000b\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005Mb#!AA\u0002\u0001\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0004E\u0002W\u0003wI1!!\u0010X\u0005\u0019y%M[3di\u0002"
)
public class JLong extends JValue implements JNumber {
   private final long num;

   public static Option unapply(final JLong x$0) {
      return JLong$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return JLong$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JLong$.MODULE$.compose(g);
   }

   public long num() {
      return this.num;
   }

   public long values() {
      return this.num();
   }

   public JLong copy(final long num) {
      return new JLong(num);
   }

   public long copy$default$1() {
      return this.num();
   }

   public String productPrefix() {
      return "JLong";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToLong(this.num());
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
      return x$1 instanceof JLong;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "num";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.num()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof JLong) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               JLong var4 = (JLong)x$1;
               if (this.num() == var4.num() && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public JLong(final long num) {
      this.num = num;
   }
}
