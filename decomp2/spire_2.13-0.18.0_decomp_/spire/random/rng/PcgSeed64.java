package spire.random.rng;

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
   bytes = "\u0006\u0005\u00055c\u0001B\r\u001b\u0001\u0006B\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005s!AQ\b\u0001BK\u0002\u0013\u0005\u0001\b\u0003\u0005?\u0001\tE\t\u0015!\u0003:\u0011\u0015y\u0004\u0001\"\u0001A\u0011\u001d)\u0005!!A\u0005\u0002\u0019Cq!\u0013\u0001\u0012\u0002\u0013\u0005!\nC\u0004V\u0001E\u0005I\u0011\u0001&\t\u000fY\u0003\u0011\u0011!C!/\"9\u0001\rAA\u0001\n\u0003\t\u0007bB3\u0001\u0003\u0003%\tA\u001a\u0005\bY\u0002\t\t\u0011\"\u0011n\u0011\u001d!\b!!A\u0005\u0002UDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004~\u0001\u0005\u0005I\u0011\t@\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\b\u0013\u0005%!$!A\t\u0002\u0005-a\u0001C\r\u001b\u0003\u0003E\t!!\u0004\t\r}\u001aB\u0011AA\u0013\u0011!y8#!A\u0005F\u0005\u0005\u0001\"CA\u0014'\u0005\u0005I\u0011QA\u0015\u0011%\tycEA\u0001\n\u0003\u000b\t\u0004C\u0005\u0002DM\t\t\u0011\"\u0003\u0002F\tI\u0001kY4TK\u0016$g\u0007\u000e\u0006\u00037q\t1A\u001d8h\u0015\tib$\u0001\u0004sC:$w.\u001c\u0006\u0002?\u0005)1\u000f]5sK\u000e\u00011\u0003\u0002\u0001#Q-\u0002\"a\t\u0014\u000e\u0003\u0011R\u0011!J\u0001\u0006g\u000e\fG.Y\u0005\u0003O\u0011\u0012a!\u00118z%\u00164\u0007CA\u0012*\u0013\tQCEA\u0004Qe>$Wo\u0019;\u0011\u00051\"dBA\u00173\u001d\tq\u0013'D\u00010\u0015\t\u0001\u0004%\u0001\u0004=e>|GOP\u0005\u0002K%\u00111\u0007J\u0001\ba\u0006\u001c7.Y4f\u0013\t)dG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00024I\u0005I\u0011N\\5u'R\fG/Z\u000b\u0002sA\u00111EO\u0005\u0003w\u0011\u0012A\u0001T8oO\u0006Q\u0011N\\5u'R\fG/\u001a\u0011\u0002\u000f%t\u0017\u000e^*fc\u0006A\u0011N\\5u'\u0016\f\b%\u0001\u0004=S:LGO\u0010\u000b\u0004\u0003\u000e#\u0005C\u0001\"\u0001\u001b\u0005Q\u0002\"B\u001c\u0006\u0001\u0004I\u0004\"B\u001f\u0006\u0001\u0004I\u0014\u0001B2paf$2!Q$I\u0011\u001d9d\u0001%AA\u0002eBq!\u0010\u0004\u0011\u0002\u0003\u0007\u0011(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003-S#!\u000f',\u00035\u0003\"AT*\u000e\u0003=S!\u0001U)\u0002\u0013Ut7\r[3dW\u0016$'B\u0001*%\u0003)\tgN\\8uCRLwN\\\u0005\u0003)>\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001-\u0011\u0005esV\"\u0001.\u000b\u0005mc\u0016\u0001\u00027b]\u001eT\u0011!X\u0001\u0005U\u00064\u0018-\u0003\u0002`5\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A\u0019\t\u0003G\rL!\u0001\u001a\u0013\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\u001dT\u0007CA\u0012i\u0013\tIGEA\u0002B]fDqa[\u0006\u0002\u0002\u0003\u0007!-A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002]B\u0019qN]4\u000e\u0003AT!!\u001d\u0013\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002ta\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t1\u0018\u0010\u0005\u0002$o&\u0011\u0001\u0010\n\u0002\b\u0005>|G.Z1o\u0011\u001dYW\"!AA\u0002\u001d\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011\u0001\f \u0005\bW:\t\t\u00111\u0001c\u0003!A\u0017m\u001d5D_\u0012,G#\u00012\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001W\u0001\u0007KF,\u0018\r\\:\u0015\u0007Y\f9\u0001C\u0004l#\u0005\u0005\t\u0019A4\u0002\u0013A\u001bwmU3fIZ\"\u0004C\u0001\"\u0014'\u0015\u0019\u0012qBA\u000e!\u001d\t\t\"a\u0006:s\u0005k!!a\u0005\u000b\u0007\u0005UA%A\u0004sk:$\u0018.\\3\n\t\u0005e\u00111\u0003\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u000f\u0003Gi!!a\b\u000b\u0007\u0005\u0005B,\u0001\u0002j_&\u0019Q'a\b\u0015\u0005\u0005-\u0011!B1qa2LH#B!\u0002,\u00055\u0002\"B\u001c\u0017\u0001\u0004I\u0004\"B\u001f\u0017\u0001\u0004I\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003g\ty\u0004E\u0003$\u0003k\tI$C\u0002\u00028\u0011\u0012aa\u00149uS>t\u0007#B\u0012\u0002<eJ\u0014bAA\u001fI\t1A+\u001e9mKJB\u0001\"!\u0011\u0018\u0003\u0003\u0005\r!Q\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA$!\rI\u0016\u0011J\u0005\u0004\u0003\u0017R&AB(cU\u0016\u001cG\u000f"
)
public class PcgSeed64 implements Product, Serializable {
   private final long initState;
   private final long initSeq;

   public static Option unapply(final PcgSeed64 x$0) {
      return PcgSeed64$.MODULE$.unapply(x$0);
   }

   public static PcgSeed64 apply(final long initState, final long initSeq) {
      return PcgSeed64$.MODULE$.apply(initState, initSeq);
   }

   public static Function1 tupled() {
      return PcgSeed64$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return PcgSeed64$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long initState() {
      return this.initState;
   }

   public long initSeq() {
      return this.initSeq;
   }

   public PcgSeed64 copy(final long initState, final long initSeq) {
      return new PcgSeed64(initState, initSeq);
   }

   public long copy$default$1() {
      return this.initState();
   }

   public long copy$default$2() {
      return this.initSeq();
   }

   public String productPrefix() {
      return "PcgSeed64";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToLong(this.initState());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToLong(this.initSeq());
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
      return x$1 instanceof PcgSeed64;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "initState";
            break;
         case 1:
            var10000 = "initSeq";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.initState()));
      var1 = Statics.mix(var1, Statics.longHash(this.initSeq()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof PcgSeed64) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               PcgSeed64 var4 = (PcgSeed64)x$1;
               if (this.initState() == var4.initState() && this.initSeq() == var4.initSeq() && var4.canEqual(this)) {
                  break label51;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public PcgSeed64(final long initState, final long initSeq) {
      this.initState = initState;
      this.initSeq = initSeq;
      Product.$init$(this);
   }
}
