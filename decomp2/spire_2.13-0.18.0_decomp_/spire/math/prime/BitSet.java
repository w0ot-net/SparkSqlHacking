package spire.math.prime;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mt!\u0002\u0010 \u0011\u00031c!\u0002\u0015 \u0011\u0003I\u0003\"\u0002\u001d\u0002\t\u0003I\u0004\"\u0002\u001e\u0002\t\u0003Y\u0004\u0002C7\u0002\u0003\u0003%\t)a\u0016\t\u0013\u0005u\u0013!!A\u0005\u0002\u0006}\u0003\"CA9\u0003\u0005\u0005I\u0011BA:\r\u0011As\u0004Q\u001f\t\u00111;!Q3A\u0005\u00025C\u0001\"U\u0004\u0003\u0012\u0003\u0006IA\u0014\u0005\t%\u001e\u0011)\u001a!C\u0001'\"Aqk\u0002B\tB\u0003%A\u000bC\u00039\u000f\u0011\u0005\u0001\fC\u0003\\\u000f\u0011\u0005A\fC\u0003c\u000f\u0011\u00051\rC\u0003f\u000f\u0011\u0005a\rC\u0003n\u000f\u0011\u0005a\u000eC\u0003q\u000f\u0011\u0005\u0011\u000fC\u0004s\u000f\u0005\u0005I\u0011A:\t\u000fY<\u0011\u0013!C\u0001o\"I\u0011QA\u0004\u0012\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003\u00179\u0011\u0011!C!\u0003\u001bA\u0001\"a\u0007\b\u0003\u0003%\t!\u0014\u0005\n\u0003;9\u0011\u0011!C\u0001\u0003?A\u0011\"a\u000b\b\u0003\u0003%\t%!\f\t\u0013\u0005mr!!A\u0005\u0002\u0005u\u0002\"CA!\u000f\u0005\u0005I\u0011IA\"\u0011%\t9eBA\u0001\n\u0003\nI\u0005C\u0005\u0002L\u001d\t\t\u0011\"\u0011\u0002N!I\u0011qJ\u0004\u0002\u0002\u0013\u0005\u0013\u0011K\u0001\u0007\u0005&$8+\u001a;\u000b\u0005\u0001\n\u0013!\u00029sS6,'B\u0001\u0012$\u0003\u0011i\u0017\r\u001e5\u000b\u0003\u0011\nQa\u001d9je\u0016\u001c\u0001\u0001\u0005\u0002(\u00035\tqD\u0001\u0004CSR\u001cV\r^\n\u0004\u0003)\u0002\u0004CA\u0016/\u001b\u0005a#\"A\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u0005=b#AB!osJ+g\r\u0005\u00022m5\t!G\u0003\u00024i\u0005\u0011\u0011n\u001c\u0006\u0002k\u0005!!.\u0019<b\u0013\t9$G\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002M\u0005)\u0011\r\u001c7pGR\u0019A(!\u0016\u0011\u0005\u001d:1\u0003B\u0004+}\u0005\u0003\"aK \n\u0005\u0001c#a\u0002)s_\u0012,8\r\u001e\t\u0003\u0005*s!a\u0011%\u000f\u0005\u0011;U\"A#\u000b\u0005\u0019+\u0013A\u0002\u001fs_>$h(C\u0001.\u0013\tIE&A\u0004qC\u000e\\\u0017mZ3\n\u0005]Z%BA%-\u0003\u0019aWM\\4uQV\ta\n\u0005\u0002,\u001f&\u0011\u0001\u000b\f\u0002\u0004\u0013:$\u0018a\u00027f]\u001e$\b\u000eI\u0001\u0006CJ\u0014\u0018-_\u000b\u0002)B\u00191&\u0016(\n\u0005Yc#!B!se\u0006L\u0018AB1se\u0006L\b\u0005F\u0002=3jCQ\u0001\u0014\u0007A\u00029CQA\u0015\u0007A\u0002Q\u000b\u0001\u0002\n9mkN$S-\u001d\u000b\u0003;\u0002\u0004\"a\u000b0\n\u0005}c#\u0001B+oSRDQ!Y\u0007A\u00029\u000b\u0011A\\\u0001\nI5Lg.^:%KF$\"!\u00183\t\u000b\u0005t\u0001\u0019\u0001(\u0002\rU\u0004H-\u0019;f)\riv\r\u001b\u0005\u0006C>\u0001\rA\u0014\u0005\u0006S>\u0001\rA[\u0001\u0002EB\u00111f[\u0005\u0003Y2\u0012qAQ8pY\u0016\fg.A\u0003baBd\u0017\u0010\u0006\u0002k_\")\u0011\r\u0005a\u0001\u001d\u0006)1\r\\3beR\tQ,\u0001\u0003d_BLHc\u0001\u001fuk\"9AJ\u0005I\u0001\u0002\u0004q\u0005b\u0002*\u0013!\u0003\u0005\r\u0001V\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005A(F\u0001(zW\u0005Q\bcA>\u0002\u00025\tAP\u0003\u0002~}\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u007f2\n!\"\u00198o_R\fG/[8o\u0013\r\t\u0019\u0001 \u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003\u0013Q#\u0001V=\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ty\u0001\u0005\u0003\u0002\u0012\u0005]QBAA\n\u0015\r\t)\u0002N\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u001a\u0005M!AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0005\u0012q\u0005\t\u0004W\u0005\r\u0012bAA\u0013Y\t\u0019\u0011I\\=\t\u0011\u0005%r#!AA\u00029\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0018!\u0019\t\t$a\u000e\u0002\"5\u0011\u00111\u0007\u0006\u0004\u0003ka\u0013AC2pY2,7\r^5p]&!\u0011\u0011HA\u001a\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007)\fy\u0004C\u0005\u0002*e\t\t\u00111\u0001\u0002\"\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\ty!!\u0012\t\u0011\u0005%\"$!AA\u00029\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u001d\u0006AAo\\*ue&tw\r\u0006\u0002\u0002\u0010\u00051Q-];bYN$2A[A*\u0011%\tI#HA\u0001\u0002\u0004\t\t\u0003C\u0003M\u0007\u0001\u0007a\nF\u0003=\u00033\nY\u0006C\u0003M\t\u0001\u0007a\nC\u0003S\t\u0001\u0007A+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0005\u0014Q\u000e\t\u0006W\u0005\r\u0014qM\u0005\u0004\u0003Kb#AB(qi&|g\u000eE\u0003,\u0003SrE+C\u0002\u0002l1\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA8\u000b\u0005\u0005\t\u0019\u0001\u001f\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002vA!\u0011\u0011CA<\u0013\u0011\tI(a\u0005\u0003\r=\u0013'.Z2u\u0001"
)
public class BitSet implements Product, Serializable {
   private final int length;
   private final int[] array;

   public static Option unapply(final BitSet x$0) {
      return BitSet$.MODULE$.unapply(x$0);
   }

   public static BitSet alloc(final int length) {
      return BitSet$.MODULE$.alloc(length);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int length() {
      return this.length;
   }

   public int[] array() {
      return this.array;
   }

   public void $plus$eq(final int n) {
      int q = n >>> 5;
      this.array()[q] |= 1 << (n & 31);
   }

   public void $minus$eq(final int n) {
      int q = n >>> 5;
      this.array()[q] &= ~(1 << (n & 31));
   }

   public void update(final int n, final boolean b) {
      if (b) {
         this.$plus$eq(n);
      } else {
         this.$minus$eq(n);
      }

   }

   public boolean apply(final int n) {
      return (this.array()[n >>> 5] >>> (n & 31) & 1) == 1;
   }

   public void clear() {
      for(int index$macro$1 = 0; index$macro$1 < this.array().length; ++index$macro$1) {
         this.array()[index$macro$1] = 0;
      }

   }

   public BitSet copy(final int length, final int[] array) {
      return new BitSet(length, array);
   }

   public int copy$default$1() {
      return this.length();
   }

   public int[] copy$default$2() {
      return this.array();
   }

   public String productPrefix() {
      return "BitSet";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.length());
            break;
         case 1:
            var10000 = this.array();
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
      return x$1 instanceof BitSet;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "length";
            break;
         case 1:
            var10000 = "array";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.length());
      var1 = Statics.mix(var1, Statics.anyHash(this.array()));
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
            if (x$1 instanceof BitSet) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               BitSet var4 = (BitSet)x$1;
               if (this.length() == var4.length() && this.array() == var4.array() && var4.canEqual(this)) {
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

   public BitSet(final int length, final int[] array) {
      this.length = length;
      this.array = array;
      Product.$init$(this);
   }
}
