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
   bytes = "\u0006\u0005\u0005mb\u0001\u0002\f\u0018\tzA\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dY\u0006!!A\u0005\u0002qCq\u0001\u0019\u0001\u0002\u0002\u0013\u0005\u0011\rC\u0004h\u0001\u0005\u0005I\u0011\t5\t\u000f=\u0004\u0011\u0011!C\u0001a\"9Q\u000fAA\u0001\n\u00032\bb\u0002=\u0001\u0003\u0003%\t%\u001f\u0005\bu\u0002\t\t\u0011\"\u0011|\u0011\u001da\b!!A\u0005Bu<\u0001b`\f\u0002\u0002#%\u0011\u0011\u0001\u0004\t-]\t\t\u0011#\u0003\u0002\u0004!1a\b\u0005C\u0001\u00037AqA\u001f\t\u0002\u0002\u0013\u00153\u0010C\u0005\u0002\u001eA\t\t\u0011\"!\u0002 !I\u00111\u0005\t\u0002\u0002\u0013\u0005\u0015Q\u0005\u0005\n\u0003c\u0001\u0012\u0011!C\u0005\u0003g\u0011!b\u00117fC:\f5mY;n\u0015\tA\u0012$A\u0003ta\u0006\u00148N\u0003\u0002\u001b7\u00051\u0011\r]1dQ\u0016T\u0011\u0001H\u0001\u0004_J<7\u0001A\n\u0006\u0001})\u0013\u0006\f\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0019:S\"A\f\n\u0005!:\"aC\"mK\u0006tW\u000f\u001d+bg.\u0004\"\u0001\t\u0016\n\u0005-\n#a\u0002)s_\u0012,8\r\u001e\t\u0003[Ur!AL\u001a\u000f\u0005=\u0012T\"\u0001\u0019\u000b\u0005Ej\u0012A\u0002\u001fs_>$h(C\u0001#\u0013\t!\u0014%A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001b\"\u0003\u0015\t7mY%e+\u0005Q\u0004C\u0001\u0011<\u0013\ta\u0014E\u0001\u0003M_:<\u0017AB1dG&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0001\u0006\u0003\"A\n\u0001\t\u000ba\u001a\u0001\u0019\u0001\u001e\u0002\t\r|\u0007/\u001f\u000b\u0003\u0001\u0012Cq\u0001\u000f\u0003\u0011\u0002\u0003\u0007!(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u001dS#A\u000f%,\u0003%\u0003\"AS(\u000e\u0003-S!\u0001T'\u0002\u0013Ut7\r[3dW\u0016$'B\u0001(\"\u0003)\tgN\\8uCRLwN\\\u0005\u0003!.\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\u000b\u0005\u0002U36\tQK\u0003\u0002W/\u0006!A.\u00198h\u0015\u0005A\u0016\u0001\u00026bm\u0006L!AW+\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005i\u0006C\u0001\u0011_\u0013\ty\u0016EA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002cKB\u0011\u0001eY\u0005\u0003I\u0006\u00121!\u00118z\u0011\u001d1\u0007\"!AA\u0002u\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A5\u0011\u0007)l'-D\u0001l\u0015\ta\u0017%\u0001\u0006d_2dWm\u0019;j_:L!A\\6\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003cR\u0004\"\u0001\t:\n\u0005M\f#a\u0002\"p_2,\u0017M\u001c\u0005\bM*\t\t\u00111\u0001c\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005M;\bb\u00024\f\u0003\u0003\u0005\r!X\u0001\tQ\u0006\u001c\bnQ8eKR\tQ,\u0001\u0005u_N#(/\u001b8h)\u0005\u0019\u0016AB3rk\u0006d7\u000f\u0006\u0002r}\"9aMDA\u0001\u0002\u0004\u0011\u0017AC\"mK\u0006t\u0017iY2v[B\u0011a\u0005E\n\u0006!\u0005\u0015\u0011\u0011\u0003\t\u0007\u0003\u000f\tiA\u000f!\u000e\u0005\u0005%!bAA\u0006C\u00059!/\u001e8uS6,\u0017\u0002BA\b\u0003\u0013\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\t\u0019\"!\u0007\u000e\u0005\u0005U!bAA\f/\u0006\u0011\u0011n\\\u0005\u0004m\u0005UACAA\u0001\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0001\u0015\u0011\u0005\u0005\u0006qM\u0001\rAO\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9#!\f\u0011\t\u0001\nICO\u0005\u0004\u0003W\t#AB(qi&|g\u000e\u0003\u0005\u00020Q\t\t\u00111\u0001A\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003k\u00012\u0001VA\u001c\u0013\r\tI$\u0016\u0002\u0007\u001f\nTWm\u0019;"
)
public class CleanAccum implements CleanupTask, Product, Serializable {
   private final long accId;

   public static Option unapply(final CleanAccum x$0) {
      return CleanAccum$.MODULE$.unapply(x$0);
   }

   public static CleanAccum apply(final long accId) {
      return CleanAccum$.MODULE$.apply(accId);
   }

   public static Function1 andThen(final Function1 g) {
      return CleanAccum$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return CleanAccum$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long accId() {
      return this.accId;
   }

   public CleanAccum copy(final long accId) {
      return new CleanAccum(accId);
   }

   public long copy$default$1() {
      return this.accId();
   }

   public String productPrefix() {
      return "CleanAccum";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.accId());
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
      return x$1 instanceof CleanAccum;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "accId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.accId()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof CleanAccum) {
               CleanAccum var4 = (CleanAccum)x$1;
               if (this.accId() == var4.accId() && var4.canEqual(this)) {
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

   public CleanAccum(final long accId) {
      this.accId = accId;
      Product.$init$(this);
   }
}
