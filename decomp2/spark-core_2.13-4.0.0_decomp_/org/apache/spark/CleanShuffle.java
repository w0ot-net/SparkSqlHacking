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
   bytes = "\u0006\u0005\u0005Mb\u0001\u0002\f\u0018\tzA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dY\u0006!!A\u0005\u0002eBq\u0001\u0018\u0001\u0002\u0002\u0013\u0005Q\fC\u0004d\u0001\u0005\u0005I\u0011\t3\t\u000f-\u0004\u0011\u0011!C\u0001Y\"9\u0011\u000fAA\u0001\n\u0003\u0012\bb\u0002;\u0001\u0003\u0003%\t%\u001e\u0005\bm\u0002\t\t\u0011\"\u0011x\u0011\u001dA\b!!A\u0005Be<qa_\f\u0002\u0002#%APB\u0004\u0017/\u0005\u0005\t\u0012B?\t\ry\u0002B\u0011AA\n\u0011\u001d1\b#!A\u0005F]D\u0011\"!\u0006\u0011\u0003\u0003%\t)a\u0006\t\u0013\u0005m\u0001#!A\u0005\u0002\u0006u\u0001\"CA\u0015!\u0005\u0005I\u0011BA\u0016\u00051\u0019E.Z1o'\",hM\u001a7f\u0015\tA\u0012$A\u0003ta\u0006\u00148N\u0003\u0002\u001b7\u00051\u0011\r]1dQ\u0016T\u0011\u0001H\u0001\u0004_J<7\u0001A\n\u0006\u0001})\u0013\u0006\f\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0019:S\"A\f\n\u0005!:\"aC\"mK\u0006tW\u000f\u001d+bg.\u0004\"\u0001\t\u0016\n\u0005-\n#a\u0002)s_\u0012,8\r\u001e\t\u0003[Ur!AL\u001a\u000f\u0005=\u0012T\"\u0001\u0019\u000b\u0005Ej\u0012A\u0002\u001fs_>$h(C\u0001#\u0013\t!\u0014%A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001b\"\u0003%\u0019\b.\u001e4gY\u0016LE-F\u0001;!\t\u00013(\u0003\u0002=C\t\u0019\u0011J\u001c;\u0002\u0015MDWO\u001a4mK&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0001\u0006\u0003\"A\n\u0001\t\u000ba\u001a\u0001\u0019\u0001\u001e\u0002\t\r|\u0007/\u001f\u000b\u0003\u0001\u0012Cq\u0001\u000f\u0003\u0011\u0002\u0003\u0007!(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u001dS#A\u000f%,\u0003%\u0003\"AS(\u000e\u0003-S!\u0001T'\u0002\u0013Ut7\r[3dW\u0016$'B\u0001(\"\u0003)\tgN\\8uCRLwN\\\u0005\u0003!.\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\u000b\u0005\u0002U36\tQK\u0003\u0002W/\u0006!A.\u00198h\u0015\u0005A\u0016\u0001\u00026bm\u0006L!AW+\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"AX1\u0011\u0005\u0001z\u0016B\u00011\"\u0005\r\te.\u001f\u0005\bE\"\t\t\u00111\u0001;\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tQ\rE\u0002gSzk\u0011a\u001a\u0006\u0003Q\u0006\n!bY8mY\u0016\u001cG/[8o\u0013\tQwM\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA7q!\t\u0001c.\u0003\u0002pC\t9!i\\8mK\u0006t\u0007b\u00022\u000b\u0003\u0003\u0005\rAX\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Tg\"9!mCA\u0001\u0002\u0004Q\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003i\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002'\u00061Q-];bYN$\"!\u001c>\t\u000f\tt\u0011\u0011!a\u0001=\u0006a1\t\\3b]NCWO\u001a4mKB\u0011a\u0005E\n\u0005!y\fI\u0001E\u0003\u0000\u0003\u000bQ\u0004)\u0004\u0002\u0002\u0002)\u0019\u00111A\u0011\u0002\u000fI,h\u000e^5nK&!\u0011qAA\u0001\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003\u0017\t\t\"\u0004\u0002\u0002\u000e)\u0019\u0011qB,\u0002\u0005%|\u0017b\u0001\u001c\u0002\u000eQ\tA0A\u0003baBd\u0017\u0010F\u0002A\u00033AQ\u0001O\nA\u0002i\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002 \u0005\u0015\u0002\u0003\u0002\u0011\u0002\"iJ1!a\t\"\u0005\u0019y\u0005\u000f^5p]\"A\u0011q\u0005\u000b\u0002\u0002\u0003\u0007\u0001)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\f\u0011\u0007Q\u000by#C\u0002\u00022U\u0013aa\u00142kK\u000e$\b"
)
public class CleanShuffle implements CleanupTask, Product, Serializable {
   private final int shuffleId;

   public static Option unapply(final CleanShuffle x$0) {
      return CleanShuffle$.MODULE$.unapply(x$0);
   }

   public static CleanShuffle apply(final int shuffleId) {
      return CleanShuffle$.MODULE$.apply(shuffleId);
   }

   public static Function1 andThen(final Function1 g) {
      return CleanShuffle$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return CleanShuffle$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public CleanShuffle copy(final int shuffleId) {
      return new CleanShuffle(shuffleId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public String productPrefix() {
      return "CleanShuffle";
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
      return x$1 instanceof CleanShuffle;
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
            if (x$1 instanceof CleanShuffle) {
               CleanShuffle var4 = (CleanShuffle)x$1;
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

   public CleanShuffle(final int shuffleId) {
      this.shuffleId = shuffleId;
      Product.$init$(this);
   }
}
