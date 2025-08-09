package scala.reflect.macros.whitebox;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple4;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.runtime.AbstractFunction4;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}fa\u0002\u0013&!\u0003\r\tA\f\u0005\u0006q\u0001!\t!\u000f\u0005\u0006{\u00011\tA\u0010\u0005\u0006\u0011\u00021\tA\u0010\u0004\u0005\u0013\u0002\u0001%\n\u0003\u0005X\t\tU\r\u0011\"\u0001Y\u0011!yFA!E!\u0002\u0013I\u0006\u0002\u00031\u0005\u0005+\u0007I\u0011A1\t\u0011\u0015$!\u0011#Q\u0001\n\tD\u0001B\u001a\u0003\u0003\u0016\u0004%\t\u0001\u0017\u0005\tO\u0012\u0011\t\u0012)A\u00053\"A\u0001\u000e\u0002BK\u0002\u0013\u0005\u0011\u000e\u0003\u0005n\t\tE\t\u0015!\u0003k\u0011\u0015qG\u0001\"\u0001p\u0011\u001d)H!!A\u0005\u0002YDqa\u001f\u0003\u0012\u0002\u0013\u0005A\u0010C\u0005\u0002\u0010\u0011\t\n\u0011\"\u0001\u0002\u0012!A\u0011Q\u0003\u0003\u0012\u0002\u0013\u0005A\u0010C\u0005\u0002\u0018\u0011\t\n\u0011\"\u0001\u0002\u001a!I\u0011Q\u0004\u0003\u0002\u0002\u0013\u0005\u0013q\u0004\u0005\n\u0003c!\u0011\u0011!C\u0001\u0003gA\u0011\"a\u000f\u0005\u0003\u0003%\t!!\u0010\t\u0013\u0005%C!!A\u0005B\u0005-\u0003\"CA-\t\u0005\u0005I\u0011AA.\u0011%\t)\u0007BA\u0001\n\u0003\n9\u0007C\u0005\u0002l\u0011\t\t\u0011\"\u0011\u0002n!I\u0011q\u000e\u0003\u0002\u0002\u0013\u0005\u0013\u0011\u000f\u0005\n\u0003g\"\u0011\u0011!C!\u0003k:\u0011\"!\u001f\u0001\u0003\u0003E\t!a\u001f\u0007\u0011%\u0003\u0011\u0011!E\u0001\u0003{BaA\\\u000f\u0005\u0002\u0005U\u0005\"CA8;\u0005\u0005IQIA9\u0011%\t9*HA\u0001\n\u0003\u000bI\nC\u0005\u0002$v\t\t\u0011\"!\u0002&\"9\u0011q\u0017\u0001\u0007\u0002\u0005e\u0006bBA_\u0001\u0019\u0005\u0011\u0011\u0018\u0002\b\u0007>tG/\u001a=u\u0015\t1s%\u0001\u0005xQ&$XMY8y\u0015\tA\u0013&\u0001\u0004nC\u000e\u0014xn\u001d\u0006\u0003U-\nqA]3gY\u0016\u001cGOC\u0001-\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001A\u00184!\t\u0001\u0014'D\u0001,\u0013\t\u00114F\u0001\u0004B]f\u0014VM\u001a\t\u0003i]j\u0011!\u000e\u0006\u0003m\u001d\n\u0001B\u00197bG.\u0014w\u000e_\u0005\u0003IU\na\u0001J5oSR$C#\u0001\u001e\u0011\u0005AZ\u0014B\u0001\u001f,\u0005\u0011)f.\u001b;\u0002\u0015=\u0004XM\\'bGJ|7/F\u0001@!\r\u00015I\u0012\b\u0003a\u0005K!AQ\u0016\u0002\u000fA\f7m[1hK&\u0011A)\u0012\u0002\u0005\u0019&\u001cHO\u0003\u0002CWA\u0011q\tA\u0007\u0002K\u0005yQM\\2m_NLgnZ'bGJ|7OA\tJ[Bd\u0017nY5u\u0007\u0006tG-\u001b3bi\u0016\u001cB\u0001B\u0018L\u001dB\u0011\u0001\u0007T\u0005\u0003\u001b.\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002P+:\u0011\u0001+\u0011\b\u0003#Rk\u0011A\u0015\u0006\u0003'6\na\u0001\u0010:p_Rt\u0014\"\u0001\u0017\n\u0005Y+%\u0001D*fe&\fG.\u001b>bE2,\u0017a\u00019sKV\t\u0011\f\u0005\u0002[76\t\u0001!\u0003\u0002];\n!A+\u001f9f\u0013\tqvEA\u0004BY&\f7/Z:\u0002\tA\u0014X\rI\u0001\u0004gflW#\u00012\u0011\u0005i\u001b\u0017B\u00013^\u0005\u0019\u0019\u00160\u001c2pY\u0006!1/_7!\u0003\t\u0001H/A\u0002qi\u0002\nA\u0001\u001e:fKV\t!\u000e\u0005\u0002[W&\u0011A.\u0018\u0002\u0005)J,W-A\u0003ue\u0016,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0006aF\u00148\u000f\u001e\t\u00035\u0012AQaV\u0007A\u0002eCQ\u0001Y\u0007A\u0002\tDQAZ\u0007A\u0002eCQ\u0001[\u0007A\u0002)\fAaY8qsR)\u0001o\u001e=zu\"9qK\u0004I\u0001\u0002\u0004I\u0006b\u00021\u000f!\u0003\u0005\rA\u0019\u0005\bM:\u0001\n\u00111\u0001Z\u0011\u001dAg\u0002%AA\u0002)\fabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001~U\tIfpK\u0001\u0000!\u0011\t\t!a\u0003\u000e\u0005\u0005\r!\u0002BA\u0003\u0003\u000f\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005%1&\u0001\u0006b]:|G/\u0019;j_:LA!!\u0004\u0002\u0004\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0003\u0016\u0003Ez\fabY8qs\u0012\"WMZ1vYR$3'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005m!F\u00016\u007f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011\u0011\u0005\t\u0005\u0003G\ti#\u0004\u0002\u0002&)!\u0011qEA\u0015\u0003\u0011a\u0017M\\4\u000b\u0005\u0005-\u0012\u0001\u00026bm\u0006LA!a\f\u0002&\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u000e\u0011\u0007A\n9$C\u0002\u0002:-\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0010\u0002FA\u0019\u0001'!\u0011\n\u0007\u0005\r3FA\u0002B]fD\u0011\"a\u0012\u0016\u0003\u0003\u0005\r!!\u000e\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ti\u0005\u0005\u0004\u0002P\u0005U\u0013qH\u0007\u0003\u0003#R1!a\u0015,\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003/\n\tF\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA/\u0003G\u00022\u0001MA0\u0013\r\t\tg\u000b\u0002\b\u0005>|G.Z1o\u0011%\t9eFA\u0001\u0002\u0004\ty$\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0011\u0003SB\u0011\"a\u0012\u0019\u0003\u0003\u0005\r!!\u000e\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u000e\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\t\u0002\r\u0015\fX/\u00197t)\u0011\ti&a\u001e\t\u0013\u0005\u001d3$!AA\u0002\u0005}\u0012!E%na2L7-\u001b;DC:$\u0017\u000eZ1uKB\u0011!,H\n\u0006;\u0005}\u00141\u0012\t\n\u0003\u0003\u000b9)\u00172ZUBl!!a!\u000b\u0007\u0005\u00155&A\u0004sk:$\u0018.\\3\n\t\u0005%\u00151\u0011\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:$\u0004\u0003BAG\u0003'k!!a$\u000b\t\u0005E\u0015\u0011F\u0001\u0003S>L1AVAH)\t\tY(A\u0003baBd\u0017\u0010F\u0005q\u00037\u000bi*a(\u0002\"\")q\u000b\ta\u00013\")\u0001\r\ta\u0001E\")a\r\ta\u00013\")\u0001\u000e\ta\u0001U\u00069QO\\1qa2LH\u0003BAT\u0003g\u0003R\u0001MAU\u0003[K1!a+,\u0005\u0019y\u0005\u000f^5p]B9\u0001'a,ZEfS\u0017bAAYW\t1A+\u001e9mKRB\u0001\"!.\"\u0003\u0003\u0005\r\u0001]\u0001\u0004q\u0012\u0002\u0014!D8qK:LU\u000e\u001d7jG&$8/\u0006\u0002\u0002<B\u0019\u0001i\u00119\u0002%\u0015t7\r\\8tS:<\u0017*\u001c9mS\u000eLGo\u001d"
)
public interface Context extends scala.reflect.macros.blackbox.Context {
   ImplicitCandidate$ ImplicitCandidate();

   List openMacros();

   List enclosingMacros();

   List openImplicits();

   List enclosingImplicits();

   static void $init$(final Context $this) {
   }

   public class ImplicitCandidate implements Product, Serializable {
      private final Types.TypeApi pre;
      private final Symbols.SymbolApi sym;
      private final Types.TypeApi pt;
      private final Trees.TreeApi tree;
      // $FF: synthetic field
      public final Context $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Types.TypeApi pre() {
         return this.pre;
      }

      public Symbols.SymbolApi sym() {
         return this.sym;
      }

      public Types.TypeApi pt() {
         return this.pt;
      }

      public Trees.TreeApi tree() {
         return this.tree;
      }

      public ImplicitCandidate copy(final Types.TypeApi pre, final Symbols.SymbolApi sym, final Types.TypeApi pt, final Trees.TreeApi tree) {
         return this.scala$reflect$macros$whitebox$Context$ImplicitCandidate$$$outer().new ImplicitCandidate(pre, sym, pt, tree);
      }

      public Types.TypeApi copy$default$1() {
         return this.pre();
      }

      public Symbols.SymbolApi copy$default$2() {
         return this.sym();
      }

      public Types.TypeApi copy$default$3() {
         return this.pt();
      }

      public Trees.TreeApi copy$default$4() {
         return this.tree();
      }

      public String productPrefix() {
         return "ImplicitCandidate";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.pre();
            case 1:
               return this.sym();
            case 2:
               return this.pt();
            case 3:
               return this.tree();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ImplicitCandidate;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "pre";
            case 1:
               return "sym";
            case 2:
               return "pt";
            case 3:
               return "tree";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$.productHash(this, -889275714, false);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof ImplicitCandidate && ((ImplicitCandidate)x$1).scala$reflect$macros$whitebox$Context$ImplicitCandidate$$$outer() == this.scala$reflect$macros$whitebox$Context$ImplicitCandidate$$$outer()) {
               ImplicitCandidate var2 = (ImplicitCandidate)x$1;
               Types.TypeApi var10000 = this.pre();
               Types.TypeApi var3 = var2.pre();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               Symbols.SymbolApi var7 = this.sym();
               Symbols.SymbolApi var4 = var2.sym();
               if (var7 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var7.equals(var4)) {
                  return false;
               }

               Types.TypeApi var8 = this.pt();
               Types.TypeApi var5 = var2.pt();
               if (var8 == null) {
                  if (var5 != null) {
                     return false;
                  }
               } else if (!var8.equals(var5)) {
                  return false;
               }

               Trees.TreeApi var9 = this.tree();
               Trees.TreeApi var6 = var2.tree();
               if (var9 == null) {
                  if (var6 != null) {
                     return false;
                  }
               } else if (!var9.equals(var6)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public Context scala$reflect$macros$whitebox$Context$ImplicitCandidate$$$outer() {
         return this.$outer;
      }

      public ImplicitCandidate(final Types.TypeApi pre, final Symbols.SymbolApi sym, final Types.TypeApi pt, final Trees.TreeApi tree) {
         this.pre = pre;
         this.sym = sym;
         this.pt = pt;
         this.tree = tree;
         if (Context.this == null) {
            throw null;
         } else {
            this.$outer = Context.this;
            super();
         }
      }
   }

   public class ImplicitCandidate$ extends AbstractFunction4 implements Serializable {
      // $FF: synthetic field
      private final Context $outer;

      public final String toString() {
         return "ImplicitCandidate";
      }

      public ImplicitCandidate apply(final Types.TypeApi pre, final Symbols.SymbolApi sym, final Types.TypeApi pt, final Trees.TreeApi tree) {
         return this.$outer.new ImplicitCandidate(pre, sym, pt, tree);
      }

      public Option unapply(final ImplicitCandidate x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(x$0.pre(), x$0.sym(), x$0.pt(), x$0.tree())));
      }

      public ImplicitCandidate$() {
         if (Context.this == null) {
            throw null;
         } else {
            this.$outer = Context.this;
            super();
         }
      }
   }
}
