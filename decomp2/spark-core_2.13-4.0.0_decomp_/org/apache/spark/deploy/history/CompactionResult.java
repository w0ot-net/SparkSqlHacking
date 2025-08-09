package org.apache.spark.deploy.history;

import java.io.Serializable;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001B\r\u001b\u0001\u0016B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\r\u0002\u0011\t\u0012)A\u0005{!Aq\t\u0001BK\u0002\u0013\u0005\u0001\n\u0003\u0005P\u0001\tE\t\u0015!\u0003J\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u001d)\u0006!!A\u0005\u0002YCq!\u0017\u0001\u0012\u0002\u0013\u0005!\fC\u0004f\u0001E\u0005I\u0011\u00014\t\u000f!\u0004\u0011\u0011!C!S\"9!\u000fAA\u0001\n\u0003\u0019\bbB<\u0001\u0003\u0003%\t\u0001\u001f\u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\ti\u0001AA\u0001\n\u0003\ty\u0001C\u0005\u0002\u001a\u0001\t\t\u0011\"\u0011\u0002\u001c!I\u0011q\u0004\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0005\u0005\n\u0003G\u0001\u0011\u0011!C!\u0003KA\u0011\"a\n\u0001\u0003\u0003%\t%!\u000b\b\u0013\u00055\"$!A\t\u0002\u0005=b\u0001C\r\u001b\u0003\u0003E\t!!\r\t\rA\u001bB\u0011AA%\u0011%\t\u0019cEA\u0001\n\u000b\n)\u0003C\u0005\u0002LM\t\t\u0011\"!\u0002N!I\u00111K\n\u0002\u0002\u0013\u0005\u0015Q\u000b\u0005\n\u0003G\u001a\u0012\u0011!C\u0005\u0003K\u0012\u0001cQ8na\u0006\u001cG/[8o%\u0016\u001cX\u000f\u001c;\u000b\u0005ma\u0012a\u00025jgR|'/\u001f\u0006\u0003;y\ta\u0001Z3qY>L(BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001M!\u0001A\n\u00170!\t9#&D\u0001)\u0015\u0005I\u0013!B:dC2\f\u0017BA\u0016)\u0005\u0019\te.\u001f*fMB\u0011q%L\u0005\u0003]!\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00021q9\u0011\u0011G\u000e\b\u0003eUj\u0011a\r\u0006\u0003i\u0011\na\u0001\u0010:p_Rt\u0014\"A\u0015\n\u0005]B\u0013a\u00029bG.\fw-Z\u0005\u0003si\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!a\u000e\u0015\u0002\t\r|G-Z\u000b\u0002{A\u0011aH\u0011\b\u0003\u007f\u0001k\u0011AG\u0005\u0003\u0003j\tAcQ8na\u0006\u001cG/[8o%\u0016\u001cX\u000f\u001c;D_\u0012,\u0017BA\"E\u0005\u00151\u0016\r\\;f\u0013\t)\u0005FA\u0006F]VlWM]1uS>t\u0017!B2pI\u0016\u0004\u0013\u0001D2p[B\f7\r^%oI\u0016DX#A%\u0011\u0007\u001dRE*\u0003\u0002LQ\t1q\n\u001d;j_:\u0004\"aJ'\n\u00059C#\u0001\u0002'p]\u001e\fQbY8na\u0006\u001cG/\u00138eKb\u0004\u0013A\u0002\u001fj]&$h\bF\u0002S'R\u0003\"a\u0010\u0001\t\u000bm*\u0001\u0019A\u001f\t\u000b\u001d+\u0001\u0019A%\u0002\t\r|\u0007/\u001f\u000b\u0004%^C\u0006bB\u001e\u0007!\u0003\u0005\r!\u0010\u0005\b\u000f\u001a\u0001\n\u00111\u0001J\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0017\u0016\u0003{q[\u0013!\u0018\t\u0003=\u000el\u0011a\u0018\u0006\u0003A\u0006\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\tD\u0013AC1o]>$\u0018\r^5p]&\u0011Am\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002O*\u0012\u0011\nX\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003)\u0004\"a\u001b9\u000e\u00031T!!\u001c8\u0002\t1\fgn\u001a\u0006\u0002_\u0006!!.\u0019<b\u0013\t\tHN\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002iB\u0011q%^\u0005\u0003m\"\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001f?\u0011\u0005\u001dR\u0018BA>)\u0005\r\te.\u001f\u0005\b{.\t\t\u00111\u0001u\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0001\t\u0006\u0003\u0007\tI!_\u0007\u0003\u0003\u000bQ1!a\u0002)\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0017\t)A\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\t\u0003/\u00012aJA\n\u0013\r\t)\u0002\u000b\u0002\b\u0005>|G.Z1o\u0011\u001diX\"!AA\u0002e\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019!.!\b\t\u000fut\u0011\u0011!a\u0001i\u0006A\u0001.Y:i\u0007>$W\rF\u0001u\u0003!!xn\u0015;sS:<G#\u00016\u0002\r\u0015\fX/\u00197t)\u0011\t\t\"a\u000b\t\u000fu\f\u0012\u0011!a\u0001s\u0006\u00012i\\7qC\u000e$\u0018n\u001c8SKN,H\u000e\u001e\t\u0003\u007fM\u0019RaEA\u001a\u0003\u007f\u0001r!!\u000e\u0002<uJ%+\u0004\u0002\u00028)\u0019\u0011\u0011\b\u0015\u0002\u000fI,h\u000e^5nK&!\u0011QHA\u001c\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003\u0003\n9%\u0004\u0002\u0002D)\u0019\u0011Q\t8\u0002\u0005%|\u0017bA\u001d\u0002DQ\u0011\u0011qF\u0001\u0006CB\u0004H.\u001f\u000b\u0006%\u0006=\u0013\u0011\u000b\u0005\u0006wY\u0001\r!\u0010\u0005\u0006\u000fZ\u0001\r!S\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9&a\u0018\u0011\t\u001dR\u0015\u0011\f\t\u0006O\u0005mS(S\u0005\u0004\u0003;B#A\u0002+va2,'\u0007\u0003\u0005\u0002b]\t\t\u00111\u0001S\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003O\u00022a[A5\u0013\r\tY\u0007\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class CompactionResult implements Product, Serializable {
   private final Enumeration.Value code;
   private final Option compactIndex;

   public static Option unapply(final CompactionResult x$0) {
      return CompactionResult$.MODULE$.unapply(x$0);
   }

   public static CompactionResult apply(final Enumeration.Value code, final Option compactIndex) {
      return CompactionResult$.MODULE$.apply(code, compactIndex);
   }

   public static Function1 tupled() {
      return CompactionResult$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return CompactionResult$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Enumeration.Value code() {
      return this.code;
   }

   public Option compactIndex() {
      return this.compactIndex;
   }

   public CompactionResult copy(final Enumeration.Value code, final Option compactIndex) {
      return new CompactionResult(code, compactIndex);
   }

   public Enumeration.Value copy$default$1() {
      return this.code();
   }

   public Option copy$default$2() {
      return this.compactIndex();
   }

   public String productPrefix() {
      return "CompactionResult";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.code();
         }
         case 1 -> {
            return this.compactIndex();
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
      return x$1 instanceof CompactionResult;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "code";
         }
         case 1 -> {
            return "compactIndex";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof CompactionResult) {
               label48: {
                  CompactionResult var4 = (CompactionResult)x$1;
                  Enumeration.Value var10000 = this.code();
                  Enumeration.Value var5 = var4.code();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Option var7 = this.compactIndex();
                  Option var6 = var4.compactIndex();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public CompactionResult(final Enumeration.Value code, final Option compactIndex) {
      this.code = code;
      this.compactIndex = compactIndex;
      Product.$init$(this);
   }
}
