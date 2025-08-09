package org.apache.spark.ui.scope;

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
   bytes = "\u0006\u0005\u00055c!B\r\u001b\u0001z!\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011\u0001\u0003!\u0011#Q\u0001\nuB\u0001\"\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005{!)1\t\u0001C\u0001\t\"9\u0011\nAA\u0001\n\u0003Q\u0005bB'\u0001#\u0003%\tA\u0014\u0005\b3\u0002\t\n\u0011\"\u0001O\u0011\u001dQ\u0006!!A\u0005BmCq\u0001\u001a\u0001\u0002\u0002\u0013\u0005A\bC\u0004f\u0001\u0005\u0005I\u0011\u00014\t\u000f1\u0004\u0011\u0011!C![\"9A\u000fAA\u0001\n\u0003)\bb\u0002>\u0001\u0003\u0003%\te\u001f\u0005\b{\u0002\t\t\u0011\"\u0011\u007f\u0011!y\b!!A\u0005B\u0005\u0005\u0001\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u000f)\tIAGA\u0001\u0012\u0003q\u00121\u0002\u0004\n3i\t\t\u0011#\u0001\u001f\u0003\u001bAaaQ\n\u0005\u0002\u0005\u0015\u0002\u0002C@\u0014\u0003\u0003%)%!\u0001\t\u0013\u0005\u001d2#!A\u0005\u0002\u0006%\u0002\"CA\u0018'\u0005\u0005I\u0011QA\u0019\u0011%\t\u0019eEA\u0001\n\u0013\t)E\u0001\tS\t\u0012{\u0005/\u001a:bi&|g.\u00123hK*\u00111\u0004H\u0001\u0006g\u000e|\u0007/\u001a\u0006\u0003;y\t!!^5\u000b\u0005}\u0001\u0013!B:qCJ\\'BA\u0011#\u0003\u0019\t\u0007/Y2iK*\t1%A\u0002pe\u001e\u001cB\u0001A\u0013,]A\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t1\u0011I\\=SK\u001a\u0004\"A\n\u0017\n\u00055:#a\u0002)s_\u0012,8\r\u001e\t\u0003_ar!\u0001\r\u001c\u000f\u0005E*T\"\u0001\u001a\u000b\u0005M\"\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003!J!aN\u0014\u0002\u000fA\f7m[1hK&\u0011\u0011H\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003o\u001d\naA\u001a:p[&#W#A\u001f\u0011\u0005\u0019r\u0014BA (\u0005\rIe\u000e^\u0001\bMJ|W.\u00133!\u0003\u0011!x.\u00133\u0002\u000bQ|\u0017\n\u001a\u0011\u0002\rqJg.\u001b;?)\r)u\t\u0013\t\u0003\r\u0002i\u0011A\u0007\u0005\u0006w\u0015\u0001\r!\u0010\u0005\u0006\u0003\u0016\u0001\r!P\u0001\u0005G>\u0004\u0018\u0010F\u0002F\u00172Cqa\u000f\u0004\u0011\u0002\u0003\u0007Q\bC\u0004B\rA\u0005\t\u0019A\u001f\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tqJ\u000b\u0002>!.\n\u0011\u000b\u0005\u0002S/6\t1K\u0003\u0002U+\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003-\u001e\n!\"\u00198o_R\fG/[8o\u0013\tA6KA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u00029B\u0011QLY\u0007\u0002=*\u0011q\fY\u0001\u0005Y\u0006twMC\u0001b\u0003\u0011Q\u0017M^1\n\u0005\rt&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\u001dT\u0007C\u0001\u0014i\u0013\tIwEA\u0002B]fDqa[\u0006\u0002\u0002\u0003\u0007Q(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002]B\u0019qN]4\u000e\u0003AT!!]\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002ta\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t1\u0018\u0010\u0005\u0002'o&\u0011\u0001p\n\u0002\b\u0005>|G.Z1o\u0011\u001dYW\"!AA\u0002\u001d\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011A\f \u0005\bW:\t\t\u00111\u0001>\u0003!A\u0017m\u001d5D_\u0012,G#A\u001f\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001X\u0001\u0007KF,\u0018\r\\:\u0015\u0007Y\f9\u0001C\u0004l#\u0005\u0005\t\u0019A4\u0002!I#Ei\u00149fe\u0006$\u0018n\u001c8FI\u001e,\u0007C\u0001$\u0014'\u0015\u0019\u0012qBA\u000e!\u001d\t\t\"a\u0006>{\u0015k!!a\u0005\u000b\u0007\u0005Uq%A\u0004sk:$\u0018.\\3\n\t\u0005e\u00111\u0003\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u000f\u0003Gi!!a\b\u000b\u0007\u0005\u0005\u0002-\u0001\u0002j_&\u0019\u0011(a\b\u0015\u0005\u0005-\u0011!B1qa2LH#B#\u0002,\u00055\u0002\"B\u001e\u0017\u0001\u0004i\u0004\"B!\u0017\u0001\u0004i\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003g\ty\u0004E\u0003'\u0003k\tI$C\u0002\u00028\u001d\u0012aa\u00149uS>t\u0007#\u0002\u0014\u0002<uj\u0014bAA\u001fO\t1A+\u001e9mKJB\u0001\"!\u0011\u0018\u0003\u0003\u0005\r!R\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA$!\ri\u0016\u0011J\u0005\u0004\u0003\u0017r&AB(cU\u0016\u001cG\u000f"
)
public class RDDOperationEdge implements Product, Serializable {
   private final int fromId;
   private final int toId;

   public static Option unapply(final RDDOperationEdge x$0) {
      return RDDOperationEdge$.MODULE$.unapply(x$0);
   }

   public static RDDOperationEdge apply(final int fromId, final int toId) {
      return RDDOperationEdge$.MODULE$.apply(fromId, toId);
   }

   public static Function1 tupled() {
      return RDDOperationEdge$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RDDOperationEdge$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int fromId() {
      return this.fromId;
   }

   public int toId() {
      return this.toId;
   }

   public RDDOperationEdge copy(final int fromId, final int toId) {
      return new RDDOperationEdge(fromId, toId);
   }

   public int copy$default$1() {
      return this.fromId();
   }

   public int copy$default$2() {
      return this.toId();
   }

   public String productPrefix() {
      return "RDDOperationEdge";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.fromId());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.toId());
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
      return x$1 instanceof RDDOperationEdge;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "fromId";
         }
         case 1 -> {
            return "toId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.fromId());
      var1 = Statics.mix(var1, this.toId());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof RDDOperationEdge) {
               RDDOperationEdge var4 = (RDDOperationEdge)x$1;
               if (this.fromId() == var4.fromId() && this.toId() == var4.toId() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public RDDOperationEdge(final int fromId, final int toId) {
      this.fromId = fromId;
      this.toId = toId;
      Product.$init$(this);
   }
}
