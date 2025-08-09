package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.MatchError;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.tpe.TypeMaps;
import scala.reflect.internal.util.ReusableInstance;
import scala.reflect.internal.util.ReusableInstance$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tMc!C\u00193!\u0003\r\t!\u000fB'\u0011\u0015q\u0004\u0001\"\u0001@\r\u0011\u0019\u0005\u0001\u0001#\t\u000b-\u0013A\u0011\u0001'\t\u00199\u0013A\u0011!A\u0003\u0002\u0003\u0005\u000b\u0015B(\t\u000bI\u0013A\u0011B*\t\u000b\t\u0014A\u0011C2\t\u000bU\u0014A\u0011\u0001<\t\u000bi\u0014A\u0011A>\b\u000bu\u0014\u0001\u0012\u0002@\u0007\u000f\u0005\u0005!\u0001#\u0003\u0002\u0004!11J\u0003C\u0001\u0003#A\u0011\"\u001a\u0006A\u0002\u0003\u0005\u000b\u0015\u00024\t\u0015\u0005M!\u00021A\u0001B\u0003&a\rC\u0004\u0002\u0016)!\t!a\u0006\t\u000f\u0005m!\u0002\"\u0001\u0002\u001e!9\u00111\u0005\u0006\u0005\n\u0005\u0015\u0002bBA\u0015\u0015\u0011\u0005\u00131\u0006\u0005\b\u0003wQA\u0011BA\u001f\u0011\u001d\t\tE\u0003C\u0001\u0003\u0007Bq!a\u0012\u000b\t\u0013\tI\u0005C\u0004\u0002N)!\t!a\u0014\b\u000f\u0005M#\u0001#\u0003\u0002V\u00199\u0011q\u000b\u0002\t\n\u0005e\u0003BB&\u0018\t\u0003\t\t\u0007C\u0004\u0002d]!I!!\u001a\t\u000f\u0005Et\u0003\"\u0003\u0002t!9\u0011\u0011I\f\u0005\u0002\u0005u\u0004bBAA\u0005\u0011\u0005\u00111\u0011\u0005\b\u0003\u000f\u0013A\u0011IAE\u0011\u001d\t)\n\u0001C\u0003\u0003/Cq!a,\u0001\t\u000b\t\t\fC\u0005\u0002>\u0002\t\n\u0011\"\u0002\u0002@\"I\u0011Q\u001b\u0001CB\u0013%\u0011q\u001b\u0004\u0007\u0003O\u0004a!!;\t\r-\u0013C\u0011AAv\u0011)\t\tC\ta\u0001\u0002\u0003\u0006K\u0001\u0016\u0005\u000b\u0003s\u0012\u0003\u0019!A!B\u00131\u0007bBA^E\u0001\u0006Ka\u0014\u0005\b\u0003[\u0014C\u0011BAx\u0011\u001d\t9P\tC\u0005\u0003sDqA!\u0001#\t\u0013\u0011\u0019\u0001C\u0004\u0003\b\t\"IA!\u0003\t\u000f\u0005-'\u0005\"\u0003\u0003\u001c!Q!q\u0004\u0012\t\u0006\u0004&IA!\t\t\u0015\tE\"\u0005#b!\n\u0013\u0011\u0019\u0004\u0003\u0006\u0003<\tB)\u0019)C\u0005\u0005{A\u0001B!\u0011#A\u0003%!1\t\u0005\b\u0003\u0003\u0012C\u0011\u0001B#\u0005%1\u0016M]5b]\u000e,7O\u0003\u00024i\u0005A\u0011N\u001c;fe:\fGN\u0003\u00026m\u00059!/\u001a4mK\u000e$(\"A\u001c\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\u000f\t\u0003wqj\u0011AN\u0005\u0003{Y\u0012a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001A!\tY\u0014)\u0003\u0002Cm\t!QK\\5u\u0005E1\u0016M]5b]\u000e,g+\u00197jI\u0006$xN]\n\u0003\u0005\u0015\u0003\"AR$\u000e\u0003\u0001I!\u0001S%\u0003#%sG/\u001a:oC2$&/\u0019<feN,'/\u0003\u0002Ke\t)AK]3fg\u00061A(\u001b8jiz\"\u0012!\u0014\t\u0003\r\n\t\u0001i]2bY\u0006$#/\u001a4mK\u000e$H%\u001b8uKJt\u0017\r\u001c\u0013WCJL\u0017M\\2fg\u00122\u0016M]5b]\u000e,g+\u00197jI\u0006$xN\u001d\u0013%S:\u0014VMZ5oK6,g\u000e\u001e\t\u0003wAK!!\u0015\u001c\u0003\u000f\t{w\u000e\\3b]\u0006\u0001r/\u001b;iS:\u0014VMZ5oK6,g\u000e\u001e\u000b\u0003)f\u0003\"AR+\n\u0005Y;&\u0001\u0002+za\u0016L!\u0001\u0017\u001a\u0003\u000bQK\b/Z:\t\ri+A\u00111\u0001\\\u0003\u0011\u0011w\u000eZ=\u0011\u0007mbF+\u0003\u0002^m\tAAHY=oC6,g\b\u000b\u0002\u0006?B\u00111\bY\u0005\u0003CZ\u0012a!\u001b8mS:,\u0017AE5tgV,g+\u0019:jC:\u001cW-\u0012:s_J$R\u0001\u00113l[NDQ!\u001a\u0004A\u0002\u0019\fAAY1tKB\u0011aiZ\u0005\u0003Q&\u0014aaU=nE>d\u0017B\u000163\u0005\u001d\u0019\u00160\u001c2pYNDQ\u0001\u001c\u0004A\u0002\u0019\f1a]=n\u0011\u0015qg\u00011\u0001p\u0003!\u0011X-];je\u0016$\u0007C\u00019r\u001b\u0005\u0011\u0014B\u0001:3\u0005!1\u0016M]5b]\u000e,\u0007\"\u0002;\u0007\u0001\u0004!\u0016a\u0001;qK\u0006Q1\u000f[8vY\u00124E.\u001b9\u0015\u0007=;\b\u0010C\u0003m\u000f\u0001\u0007a\rC\u0003z\u000f\u0001\u0007a-\u0001\u0003um\u0006\u0014\u0018\u0001F5t\u000bb,W\u000e\u001d;Ge>lg+\u0019:jC:\u001cW\r\u0006\u0002Py\")A\u000e\u0003a\u0001M\u0006\u0019b+\u00197jI\u0006$XMV1sS\u0006t7-Z'baB\u0011qPC\u0007\u0002\u0005\t\u0019b+\u00197jI\u0006$XMV1sS\u0006t7-Z'baN\u0019!\"!\u0002\u0011\u0007\u0019\u000b9!\u0003\u0003\u0002\n\u0005-!\u0001\u0005,be&\fgnY3e)f\u0004X-T1q\u0013\u0011\ti!a\u0004\u0003\u0011QK\b/Z'baNT!\u0001\u001e\u001a\u0015\u0003y\fa\"\u001b8M_^,'OQ8v]\u0012|e-\u0001\tsK2\fG/\u001b<f-\u0006\u0014\u0018.\u00198dKR\u0019q.!\u0007\t\u000bet\u0001\u0019\u00014\u0002'%\u001cXK\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0015\u0007=\u000by\u0002\u0003\u0004\u0002\"=\u0001\r\u0001V\u0001\u0003iB\fQc\u00195fG.4\u0016M]5b]\u000e,wJZ*z[\n|G\u000eF\u0002A\u0003OAQ\u0001\u001c\tA\u0002\u0019\fq!\\1q\u001fZ,'\u000f\u0006\u0003\u0002.\u0005]\u0002c\u0001$\u00020%!\u0011\u0011GA\u001a\u0005\u0015\u00196m\u001c9f\u0013\r\t)D\r\u0002\u0007'\u000e|\u0007/Z:\t\u000f\u0005e\u0012\u00031\u0001\u0002.\u0005)A-Z2mg\u0006q!/Z:vYR$\u0016\u0010]3P]2LHcA(\u0002@!1\u0011\u0011\u0005\nA\u0002Q\u000bQ!\u00199qYf$2\u0001VA#\u0011\u0019\t\tc\u0005a\u0001)\u0006i1\u000f[8vY\u0012$U-\u00197jCN$2aTA&\u0011\u0015aG\u00031\u0001g\u0003I1\u0018\r\\5eCR,G)\u001a4j]&$\u0018n\u001c8\u0015\u0007\u0001\u000b\t\u0006C\u0003f+\u0001\u0007a-A\nQ_2LH+\u001f9f-\u0006\u0014\u0018.\u00198dK6\u000b\u0007\u000f\u0005\u0002\u0000/\t\u0019\u0002k\u001c7z)f\u0004XMV1sS\u0006t7-Z'baN\u0019q#a\u0017\u0011\u0007\u0019\u000bi&\u0003\u0003\u0002`\u0005-!a\u0002+za\u0016l\u0015\r\u001d\u000b\u0003\u0003+\nqa\\<oKJ|e\rF\u0002g\u0003OBq!!\u001b\u001a\u0001\u0004\tY'\u0001\u0002qiB\u0019a)!\u001c\n\u0007\u0005=tK\u0001\u0005Q_2LH+\u001f9f\u0003I\u0019\u0007.Z2l!>d\u0017\u0010V=qKB\u000b'/Y7\u0015\u000f\u0001\u000b)(a\u001e\u0002|!9\u0011\u0011\u000e\u000eA\u0002\u0005-\u0004BBA=5\u0001\u0007a-\u0001\u0004ua\u0006\u0014\u0018-\u001c\u0005\u0006ij\u0001\r\u0001\u0016\u000b\u0004)\u0006}\u0004BBA\u00117\u0001\u0007A+A\u000fwC2LG-\u0019;f-\u0006\u0014\u0018.\u00198dK>3\u0007k\u001c7z)f\u0004Xm]%o)\r\u0001\u0015Q\u0011\u0005\u0006ir\u0001\r\u0001V\u0001\tiJ\fg/\u001a:tKR\u0019\u0001)a#\t\u000f\u00055U\u00041\u0001\u0002\u0010\u0006!AO]3f!\r1\u0015\u0011S\u0005\u0004\u0003'K%\u0001\u0002+sK\u0016\fqB^1sS\u0006t7-Z%o)f\u0004Xm\u001d\u000b\u0005\u00033\u000bi\nF\u0002p\u00037Ca!!\u001f\u001f\u0001\u00041\u0007bBAP=\u0001\u0007\u0011\u0011U\u0001\u0004iB\u001c\b#BAR\u0003S#fbA\u001e\u0002&&\u0019\u0011q\u0015\u001c\u0002\u000fA\f7m[1hK&!\u00111VAW\u0005\u0011a\u0015n\u001d;\u000b\u0007\u0005\u001df'\u0001\bwCJL\u0017M\\2f\u0013:$\u0016\u0010]3\u0015\r\u0005M\u0016qWA])\ry\u0017Q\u0017\u0005\u0007\u0003sz\u0002\u0019\u00014\t\r\u0005\u0005r\u00041\u0001U\u0011!\tYl\bI\u0001\u0002\u0004y\u0015!E2p]NLG-\u001a:V]\u000eDWmY6fI\u0006Ab/\u0019:jC:\u001cW-\u00138UsB,G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005\u0005'fA(\u0002D.\u0012\u0011Q\u0019\t\u0005\u0003\u000f\f\t.\u0004\u0002\u0002J*!\u00111ZAg\u0003%)hn\u00195fG.,GMC\u0002\u0002PZ\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\u0019.!3\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\nwCJL\u0017M\\2f\u0013:$\u0016\u0010]3DC\u000eDW-\u0006\u0002\u0002ZB1\u00111\\Aq\u0003Kl!!!8\u000b\u0007\u0005}''\u0001\u0003vi&d\u0017\u0002BAr\u0003;\u0014\u0001CU3vg\u0006\u0014G.Z%ogR\fgnY3\u0011\u0005\u0019\u0013#A\u0004<be&\fgnY3J]RK\b/Z\n\u0003Ei\"\"!!:\u0002\r%t\u0017I]4t)\u0015y\u0017\u0011_Az\u0011\u0015aw\u00051\u0001g\u0011\u001d\t)p\na\u0001\u0003C\u000bA!\u0019:hg\u00061\u0011N\\*z[N$2a\\A~\u0011\u001d\ti\u0010\u000ba\u0001\u0003\u007f\fAa]=ngB)\u00111UAUM\u00069\u0011N\u001c+za\u0016\u001cHcA8\u0003\u0006!9\u0011qT\u0015A\u0002\u0005\u0005\u0016\u0001C5o\u0003:tw\u000e^:\u0015\u0007=\u0014Y\u0001C\u0004\u0003\u000e)\u0002\rAa\u0004\u0002\t\u0005tgn\u001d\t\u0007\u0003G\u000bIK!\u0005\u0011\u0007\u0019\u0013\u0019\"\u0003\u0003\u0003\u0016\t]!AD!o]>$\u0018\r^5p]&sgm\\\u0005\u0004\u00053\u0011$aD!o]>$\u0018\r^5p]&sgm\\:\u0015\u0007=\u0013i\u0002C\u0004\u0003\u000e-\u0002\rAa\u0004\u0002\u001f%t\u0017I\u001c8pi\u0006$\u0018n\u001c8BiB,\"Aa\t\u0011\r\t\u0015\"1\u0006B\t\u001d\r\u0001(qE\u0005\u0004\u0005S\u0011\u0014\u0001\u0003,be&\fgnY3\n\t\t5\"q\u0006\u0002\n\u000bb$(/Y2u_JT1A!\u000b3\u0003)Ig.\u0011:h!\u0006\u0014\u0018-\\\u000b\u0003\u0005k\u0001bA!\n\u00038Q3\u0017\u0002\u0002B\u001d\u0005_\u0011!\"\u0012=ue\u0006\u001cGo\u001c:3\u0003\u0015IgnU=n+\t\u0011y\u0004E\u0003\u0003&\t-b-\u0001\u0004j]RK\b/\u001a\t\u0006\u0005K\u0011Y\u0003\u0016\u000b\b_\n\u001d#\u0011\nB&\u0011\u0019\t\t\u0003\ra\u0001)\"1\u0011\u0011\u0010\u0019A\u0002\u0019Da!a/1\u0001\u0004y\u0005c\u00019\u0003P%\u0019!\u0011\u000b\u001a\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a"
)
public interface Variances {
   void scala$reflect$internal$Variances$_setter_$scala$reflect$internal$Variances$$varianceInTypeCache_$eq(final ReusableInstance x$1);

   // $FF: synthetic method
   static int varianceInTypes$(final Variances $this, final List tps, final Symbols.Symbol tparam) {
      return $this.varianceInTypes(tps, tparam);
   }

   default int varianceInTypes(final List tps, final Symbols.Symbol tparam) {
      Variance$ foldExtract_this = Variance$.MODULE$;
      int foldExtract_loop$1_acc = foldExtract_this.Bivariant();

      List var10000;
      for(List foldExtract_loop$1_xs = tps; !foldExtract_this.isInvariant$extension(foldExtract_loop$1_acc) && !foldExtract_loop$1_xs.isEmpty(); foldExtract_loop$1_xs = var10000) {
         var10000 = (List)foldExtract_loop$1_xs.tail();
         Types.Type var6 = (Types.Type)foldExtract_loop$1_xs.head();
         foldExtract_loop$1_acc = foldExtract_this.$amp$extension(foldExtract_loop$1_acc, $anonfun$varianceInTypes$1(this, tparam, var6));
      }

      return foldExtract_loop$1_acc;
   }

   default int varianceInType(final Types.Type tp, final boolean considerUnchecked, final Symbols.Symbol tparam) {
      ReusableInstance var10000 = this.scala$reflect$internal$Variances$$varianceInTypeCache();
      if (var10000 == null) {
         throw null;
      } else {
         ReusableInstance using_this = var10000;
         if (using_this.scala$reflect$internal$util$ReusableInstance$$cache == null) {
            varianceInType var7 = (varianceInType)using_this.scala$reflect$internal$util$ReusableInstance$$make.apply();
            var10000 = (ReusableInstance)$anonfun$varianceInType$1$adapted(tp, tparam, considerUnchecked, var7);
         } else {
            int var15 = using_this.scala$reflect$internal$util$ReusableInstance$$taken;
            ArrayBuffer var10001 = using_this.scala$reflect$internal$util$ReusableInstance$$cache;
            if (var10001 == null) {
               throw null;
            }

            if (var15 == SeqOps.size$(var10001)) {
               ArrayBuffer var16 = using_this.scala$reflect$internal$util$ReusableInstance$$cache;
               Object using_$plus$eq_elem = using_this.scala$reflect$internal$util$ReusableInstance$$make.apply();
               if (var16 == null) {
                  throw null;
               }

               var16.addOne(using_$plus$eq_elem);
               using_$plus$eq_elem = null;
            }

            ++using_this.scala$reflect$internal$util$ReusableInstance$$taken;

            try {
               varianceInType var13 = (varianceInType)using_this.scala$reflect$internal$util$ReusableInstance$$cache.apply(using_this.scala$reflect$internal$util$ReusableInstance$$taken - 1);
               var10000 = (ReusableInstance)$anonfun$varianceInType$1$adapted(tp, tparam, considerUnchecked, var13);
            } finally {
               --using_this.scala$reflect$internal$util$ReusableInstance$$taken;
            }
         }

         Object var10 = null;
         Object var12 = null;
         return ((Variance)var10000).flags();
      }
   }

   // $FF: synthetic method
   static boolean varianceInType$default$2$(final Variances $this) {
      return $this.varianceInType$default$2();
   }

   default boolean varianceInType$default$2() {
      return false;
   }

   ReusableInstance scala$reflect$internal$Variances$$varianceInTypeCache();

   // $FF: synthetic method
   static int $anonfun$varianceInTypes$1(final Variances $this, final Symbols.Symbol tparam$1, final Types.Type t) {
      return $this.varianceInType(t, false, tparam$1);
   }

   // $FF: synthetic method
   static int $anonfun$varianceInType$1(final Types.Type tp$2, final Symbols.Symbol tparam$2, final boolean considerUnchecked$1, final varianceInType x$5) {
      return x$5.apply(tp$2, tparam$2, considerUnchecked$1);
   }

   static void $init$(final Variances $this) {
      ReusableInstance$ var10001 = ReusableInstance$.MODULE$;
      Function0 var6 = () -> (SymbolTable)$this.new varianceInType();
      boolean apply_enabled = ((SymbolTable)$this).isCompilerUniverse();
      Function0 apply_make = var6;
      ReusableInstance var7;
      if (apply_enabled) {
         int apply_apply_apply_initialSize = 4;
         var7 = new ReusableInstance(apply_make, apply_apply_apply_initialSize);
      } else {
         int apply_apply_initialSize = -1;
         var7 = new ReusableInstance(apply_make, apply_apply_initialSize);
      }

      Object var5 = null;
      $this.scala$reflect$internal$Variances$_setter_$scala$reflect$internal$Variances$$varianceInTypeCache_$eq(var7);
   }

   // $FF: synthetic method
   static Object $anonfun$varianceInType$1$adapted(final Types.Type tp$2, final Symbols.Symbol tparam$2, final boolean considerUnchecked$1, final varianceInType x$5) {
      return new Variance(x$5.apply(tp$2, tparam$2, considerUnchecked$1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class VarianceValidator extends Trees.InternalTraverser {
      private volatile ValidateVarianceMap$ ValidateVarianceMap$module;
      private volatile PolyTypeVarianceMap$ PolyTypeVarianceMap$module;
      public boolean scala$reflect$internal$Variances$VarianceValidator$$inRefinement;
      // $FF: synthetic field
      public final SymbolTable $outer;

      private ValidateVarianceMap$ ValidateVarianceMap() {
         if (this.ValidateVarianceMap$module == null) {
            this.ValidateVarianceMap$lzycompute$1();
         }

         return this.ValidateVarianceMap$module;
      }

      private PolyTypeVarianceMap$ PolyTypeVarianceMap() {
         if (this.PolyTypeVarianceMap$module == null) {
            this.PolyTypeVarianceMap$lzycompute$1();
         }

         return this.PolyTypeVarianceMap$module;
      }

      public Types.Type scala$reflect$internal$Variances$VarianceValidator$$withinRefinement(final Function0 body) {
         boolean saved = this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement;
         this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement = true;

         Types.Type var10000;
         try {
            var10000 = (Types.Type)body.apply();
         } finally {
            this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement = saved;
         }

         return var10000;
      }

      public void issueVarianceError(final Symbols.Symbol base, final Symbols.Symbol sym, final int required, final Types.Type tpe) {
      }

      public boolean shouldFlip(final Symbols.Symbol sym, final Symbols.Symbol tvar) {
         if (sym.isParameter() && !sym.owner().isLocalToThis()) {
            if (!tvar.isTypeParameterOrSkolem() || !sym.isTypeParameterOrSkolem()) {
               return true;
            }

            Symbols.Symbol var10000 = tvar.owner();
            Symbols.Symbol var3 = sym.owner();
            if (var10000 == null) {
               if (var3 != null) {
                  return true;
               }
            } else if (!var10000.equals(var3)) {
               return true;
            }
         }

         return false;
      }

      public boolean isExemptFromVariance(final Symbols.Symbol sym) {
         return !sym.owner().isClass() || sym.isLocalToThis() || sym.isSuperAccessor();
      }

      public void validateVarianceOfPolyTypesIn(final Types.Type tpe) {
         this.PolyTypeVarianceMap().apply(tpe);
      }

      public void traverse(final Trees.Tree tree) {
         if (tree instanceof Trees.MemberDef && this.skip$1(tree)) {
            this.scala$reflect$internal$Variances$VarianceValidator$$$outer().debuglog(() -> (new StringBuilder(27)).append("Skipping variance check of ").append(tree.symbol().defString()).toString());
         } else if (tree instanceof Trees.ClassDef ? true : tree instanceof Trees.TypeDef) {
            this.ValidateVarianceMap().validateDefinition(tree.symbol());
            tree.traverse(this);
         } else if (tree instanceof Trees.ModuleDef) {
            this.ValidateVarianceMap().validateDefinition(tree.symbol().moduleClass());
            tree.traverse(this);
         } else if (tree instanceof Trees.ValDef) {
            this.ValidateVarianceMap().validateDefinition(tree.symbol());
         } else if (tree instanceof Trees.DefDef) {
            Trees.DefDef var2 = (Trees.DefDef)tree;
            List tparams = var2.tparams();
            List vparamss = var2.vparamss();
            this.ValidateVarianceMap().validateDefinition(tree.symbol());
            this.traverseTrees(tparams);
            this.traverseTreess(vparamss);
         } else if (tree instanceof Trees.Template) {
            tree.traverse(this);
         } else if (tree instanceof Trees.CompoundTypeTree) {
            tree.traverse(this);
         } else {
            if (tree instanceof Trees.TypeTree) {
               Trees.TypeTree var5 = (Trees.TypeTree)tree;
               if (var5.original() != null) {
                  var5.original().traverse(this);
                  return;
               }
            }

            if (tree instanceof Trees.TypTree) {
               ((Trees.TreeContextApiImpl)((Trees.TypTree)tree)).traverse(this);
            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Variances$VarianceValidator$$$outer() {
         return this.$outer;
      }

      private final void ValidateVarianceMap$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.ValidateVarianceMap$module == null) {
               this.ValidateVarianceMap$module = new ValidateVarianceMap$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      private final void PolyTypeVarianceMap$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.PolyTypeVarianceMap$module == null) {
               this.PolyTypeVarianceMap$module = new PolyTypeVarianceMap$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      private static final Symbols.Symbol sym$3(final Trees.Tree tree$1) {
         return tree$1.symbol();
      }

      private final boolean skip$1(final Trees.Tree tree$1) {
         Symbols.Symbol var10000 = tree$1.symbol();
         Symbols.NoSymbol var2 = this.scala$reflect$internal$Variances$VarianceValidator$$$outer().NoSymbol();
         if (var10000 == null) {
            if (var2 == null) {
               return true;
            }
         } else if (var10000.equals(var2)) {
            return true;
         }

         if (!tree$1.symbol().owner().isConstructor() && !tree$1.symbol().owner().isCaseApplyOrUnapply() && (!tree$1.symbol().isParamAccessor() || !tree$1.symbol().isLocalToThis())) {
            return false;
         } else {
            return true;
         }
      }

      public VarianceValidator() {
         if (Variances.this == null) {
            throw null;
         } else {
            this.$outer = Variances.this;
            super();
            this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement = false;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }

      private class ValidateVarianceMap$ extends TypeMaps.VariancedTypeMap {
         private Symbols.Symbol base;
         private Symbols.Symbol inLowerBoundOf;
         // $FF: synthetic field
         private final VarianceValidator $outer;

         public int relativeVariance(final Symbols.Symbol tvar) {
            return this.loop$1(this.base, Variance$.MODULE$.Covariant(), tvar);
         }

         public boolean isUncheckedVariance(final Types.Type tp) {
            if (tp instanceof Types.AnnotatedType) {
               List annots = ((Types.AnnotatedType)tp).annotations();
               if (annots == null) {
                  throw null;
               } else {
                  for(List exists_these = annots; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
                     AnnotationInfos.AnnotationInfo var4 = (AnnotationInfos.AnnotationInfo)exists_these.head();
                     if ($anonfun$isUncheckedVariance$1(this, var4)) {
                        return true;
                     }
                  }

                  return false;
               }
            } else {
               return false;
            }
         }

         private void checkVarianceOfSymbol(final Symbols.Symbol sym) {
            int relative = this.relativeVariance(sym);
            int required = Variance$.MODULE$.$times$extension(relative, this.variance());
            if (!Variance$.MODULE$.isBivariant$extension(relative)) {
               this.$outer.scala$reflect$internal$Variances$VarianceValidator$$$outer().log(() -> (new StringBuilder(18)).append("verifying ").append(sym_s$1(sym)).append(" is ").append(new Variance(required)).append(" at ").append(this.base_s$1()).toString());
               if (sym.variance() != required) {
                  this.$outer.issueVarianceError(this.base, sym, required, this.base.info());
               }
            }
         }

         public Scopes.Scope mapOver(final Scopes.Scope decls) {
            decls.foreach((sym) -> {
               int withVariance_v = sym.isAliasType() ? Variance$.MODULE$.Invariant() : this.variance();
               int withVariance_saved = ((TypeMaps.VariancedTypeMap)this).variance();
               ((TypeMaps.VariancedTypeMap)this).variance_$eq(withVariance_v);

               Types.Type var10000;
               try {
                  var10000 = $anonfun$mapOver$2(this, sym);
               } finally {
                  ((TypeMaps.VariancedTypeMap)this).variance_$eq(withVariance_saved);
               }

               return var10000;
            });
            return decls;
         }

         private boolean resultTypeOnly(final Types.Type tp) {
            if (tp instanceof Types.MethodType) {
               return !this.$outer.scala$reflect$internal$Variances$VarianceValidator$$inRefinement;
            } else {
               return tp instanceof Types.PolyType;
            }
         }

         public Types.Type apply(final Types.Type tp) {
            boolean var2 = false;
            Types.TypeRef var3 = null;
            if (!this.isUncheckedVariance(tp)) {
               if (this.resultTypeOnly(tp)) {
                  this.apply(tp.resultType());
               } else {
                  if (tp instanceof Types.TypeRef) {
                     var2 = true;
                     var3 = (Types.TypeRef)tp;
                     Symbols.Symbol sym = var3.sym();
                     if (this.shouldDealias(sym)) {
                        this.apply(tp.normalize());
                        return tp;
                     }
                  }

                  if (var2) {
                     Symbols.Symbol sym = var3.sym();
                     if (!Variance$.MODULE$.isInvariant$extension(sym.variance())) {
                        this.checkVarianceOfSymbol(sym);
                        tp.mapOver(this);
                        return tp;
                     }
                  }

                  if (tp instanceof Types.RefinedType) {
                     VarianceValidator var10000 = this.$outer;
                     if (var10000 == null) {
                        throw null;
                     }

                     VarianceValidator scala$reflect$internal$Variances$VarianceValidator$$withinRefinement_this = var10000;
                     boolean scala$reflect$internal$Variances$VarianceValidator$$withinRefinement_saved = scala$reflect$internal$Variances$VarianceValidator$$withinRefinement_this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement;
                     scala$reflect$internal$Variances$VarianceValidator$$withinRefinement_this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement = true;

                     try {
                        tp.mapOver(this);
                     } finally {
                        scala$reflect$internal$Variances$VarianceValidator$$withinRefinement_this.scala$reflect$internal$Variances$VarianceValidator$$inRefinement = scala$reflect$internal$Variances$VarianceValidator$$withinRefinement_saved;
                     }

                     Object var21 = null;
                  } else if (tp instanceof Types.ClassInfoType) {
                     List parents = ((Types.ClassInfoType)tp).parents();
                     if (parents == null) {
                        throw null;
                     }

                     for(List foreach_these = parents; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                        Types.Type var14 = (Types.Type)foreach_these.head();
                        this.apply(var14);
                     }

                     Object var22 = null;
                  } else if (tp instanceof Types.MethodType) {
                     Types.MethodType var7 = (Types.MethodType)tp;
                     Types.Type result = var7.resultType();
                     ((TypeMaps.VariancedTypeMap)this).variance_$eq(Variance$.MODULE$.flip$extension(((TypeMaps.VariancedTypeMap)this).variance()));

                     try {
                        $anonfun$apply$3(this, var7);
                     } finally {
                        ((TypeMaps.VariancedTypeMap)this).variance_$eq(Variance$.MODULE$.flip$extension(((TypeMaps.VariancedTypeMap)this).variance()));
                     }

                     this.apply(result);
                  } else {
                     tp.mapOver(this);
                  }
               }
            }

            return tp;
         }

         private boolean shouldDealias(final Symbols.Symbol sym) {
            return sym.isAliasType() && this.$outer.isExemptFromVariance(sym);
         }

         public void validateDefinition(final Symbols.Symbol base) {
            this.base = base;
            Types.Type var2 = base.info();
            if (var2 instanceof Types.PolyType) {
               Types.Type var3 = ((Types.PolyType)var2).resultType();
               if (var3 instanceof Types.TypeBounds) {
                  Types.TypeBounds var4 = (Types.TypeBounds)var3;
                  Types.Type lo = var4.lo();
                  Types.Type hi = var4.hi();
                  this.inLowerBoundOf = base;

                  try {
                     ((TypeMaps.VariancedTypeMap)this).variance_$eq(Variance$.MODULE$.flip$extension(((TypeMaps.VariancedTypeMap)this).variance()));

                     try {
                        this.apply(lo);
                     } finally {
                        ((TypeMaps.VariancedTypeMap)this).variance_$eq(Variance$.MODULE$.flip$extension(((TypeMaps.VariancedTypeMap)this).variance()));
                     }
                  } finally {
                     this.inLowerBoundOf = null;
                  }

                  this.apply(hi);
                  return;
               }
            }

            this.apply(var2);
         }

         private final int nextVariance$1(final Symbols.Symbol sym, final int v, final Symbols.Symbol tvar$1) {
            if (this.$outer.shouldFlip(sym, tvar$1)) {
               return Variance$.MODULE$.flip$extension(v);
            } else if (this.$outer.isExemptFromVariance(sym)) {
               return Variance$.MODULE$.Bivariant();
            } else {
               return sym.isAliasType() ? Variance$.MODULE$.Invariant() : v;
            }
         }

         private final int loop$1(final Symbols.Symbol sym, final int v, final Symbols.Symbol tvar$1) {
            while(!Variance$.MODULE$.isBivariant$extension(v)) {
               label41: {
                  Symbols.Symbol var4 = tvar$1.owner();
                  if (sym == null) {
                     if (var4 != null) {
                        break label41;
                     }
                  } else if (!sym.equals(var4)) {
                     break label41;
                  }

                  Symbols.Symbol var10000 = this.inLowerBoundOf;
                  if (var10000 == null) {
                     if (sym == null) {
                        return Variance$.MODULE$.flip$extension(v);
                     }
                  } else if (var10000.equals(sym)) {
                     return Variance$.MODULE$.flip$extension(v);
                  }

                  return v;
               }

               Symbols.Symbol var5 = sym.owner();
               v = this.nextVariance$1(sym, v, tvar$1);
               sym = var5;
            }

            return v;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$isUncheckedVariance$1(final ValidateVarianceMap$ $this, final AnnotationInfos.AnnotationInfo x$3) {
            return x$3.matches($this.$outer.scala$reflect$internal$Variances$VarianceValidator$$$outer().definitions().uncheckedVarianceClass());
         }

         private static final String sym_s$1(final Symbols.Symbol sym$1) {
            return (new StringBuilder(3)).append(sym$1).append(" (").append(new Variance(sym$1.variance())).append(sym$1.locationString()).append(")").toString();
         }

         private final String base_s$1() {
            return (new StringBuilder(4)).append(this.base).append(" in ").append(this.base.owner()).append(this.base.owner().isClass() ? "" : (new StringBuilder(4)).append(" in ").append(this.base.owner().enclClass()).toString()).toString();
         }

         // $FF: synthetic method
         public static final Types.Type $anonfun$mapOver$2(final ValidateVarianceMap$ $this, final Symbols.Symbol sym$2) {
            return $this.apply(sym$2.info());
         }

         // $FF: synthetic method
         public static final Types.Type $anonfun$apply$1(final ValidateVarianceMap$ $this, final Types.Type tp$1) {
            return tp$1.mapOver($this);
         }

         // $FF: synthetic method
         public static final Types.Type $anonfun$apply$2(final ValidateVarianceMap$ $this, final Types.Type tp) {
            return $this.apply(tp);
         }

         // $FF: synthetic method
         public static final Types.Type $anonfun$apply$4(final ValidateVarianceMap$ $this, final Types.Type tp) {
            return $this.apply(tp);
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$3(final ValidateVarianceMap$ $this, final Types.MethodType x14$1) {
            List var10000 = x14$1.paramTypes();
            if (var10000 == null) {
               throw null;
            } else {
               for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                  Types.Type var3 = (Types.Type)foreach_these.head();
                  $this.apply(var3);
               }

            }
         }

         // $FF: synthetic method
         public static final Types.Type $anonfun$validateDefinition$1(final ValidateVarianceMap$ $this, final Types.Type lo$1) {
            return $this.apply(lo$1);
         }

         public ValidateVarianceMap$() {
            if (VarianceValidator.this == null) {
               throw null;
            } else {
               this.$outer = VarianceValidator.this;
               super();
            }
         }

         // $FF: synthetic method
         public static final Object $anonfun$isUncheckedVariance$1$adapted(final ValidateVarianceMap$ $this, final AnnotationInfos.AnnotationInfo x$3) {
            return BoxesRunTime.boxToBoolean($anonfun$isUncheckedVariance$1($this, x$3));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      }

      private class PolyTypeVarianceMap$ extends TypeMaps.TypeMap {
         // $FF: synthetic field
         private final VarianceValidator $outer;

         private Symbols.Symbol ownerOf(final Types.PolyType pt) {
            return ((Symbols.Symbol)pt.typeParams().head()).owner();
         }

         private void checkPolyTypeParam(final Types.PolyType pt, final Symbols.Symbol tparam, final Types.Type tpe) {
            if (!tparam.isInvariant()) {
               SymbolTable var10000 = this.$outer.scala$reflect$internal$Variances$VarianceValidator$$$outer();
               boolean varianceInType_considerUnchecked = true;
               if (var10000 == null) {
                  throw null;
               } else {
                  SymbolTable varianceInType_this = var10000;
                  int var8 = varianceInType_this.varianceInType(tpe, varianceInType_considerUnchecked, tparam);
                  varianceInType_this = null;
                  int required = var8;
                  if (!Variance$.MODULE$.isBivariant$extension(required) && tparam.variance() != required) {
                     this.$outer.issueVarianceError(this.ownerOf(pt), tparam, required, pt);
                  }
               }
            }
         }

         public Types.Type apply(final Types.Type tp) {
            boolean var2 = false;
            Types.PolyType var3 = null;
            if (tp instanceof Types.PolyType) {
               var2 = true;
               var3 = (Types.PolyType)tp;
               List typeParams = var3.typeParams();
               Types.Type var5 = var3.resultType();
               if (var5 instanceof Types.TypeBounds) {
                  Types.TypeBounds var6 = (Types.TypeBounds)var5;
                  Types.Type lo = var6.lo();
                  Types.Type hi = var6.hi();
                  if (typeParams == null) {
                     throw null;
                  }

                  for(List foreach_these = typeParams; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                     Symbols.Symbol var13 = (Symbols.Symbol)foreach_these.head();
                     $anonfun$apply$5(this, var3, lo, hi, var13);
                  }

                  Object var15 = null;
                  var3.mapOver(this);
                  return tp;
               }
            }

            if (var2) {
               List typeParams = var3.typeParams();
               Types.Type resultType = var3.resultType();
               if (typeParams == null) {
                  throw null;
               }

               for(List foreach_these = typeParams; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                  Symbols.Symbol var14 = (Symbols.Symbol)foreach_these.head();
                  $anonfun$apply$6(this, var3, resultType, var14);
               }

               Object var16 = null;
               var3.mapOver(this);
            } else {
               tp.mapOver(this);
            }

            return tp;
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$5(final PolyTypeVarianceMap$ $this, final Types.PolyType x2$1, final Types.Type lo$2, final Types.Type hi$1, final Symbols.Symbol tparam) {
            $this.checkPolyTypeParam(x2$1, tparam, lo$2);
            $this.checkPolyTypeParam(x2$1, tparam, hi$1);
         }

         // $FF: synthetic method
         public static final void $anonfun$apply$6(final PolyTypeVarianceMap$ $this, final Types.PolyType x2$1, final Types.Type resultType$1, final Symbols.Symbol x$4) {
            $this.checkPolyTypeParam(x2$1, x$4, resultType$1);
         }

         public PolyTypeVarianceMap$() {
            if (VarianceValidator.this == null) {
               throw null;
            } else {
               this.$outer = VarianceValidator.this;
               super();
            }
         }

         // $FF: synthetic method
         public static final Object $anonfun$apply$5$adapted(final PolyTypeVarianceMap$ $this, final Types.PolyType x2$1, final Types.Type lo$2, final Types.Type hi$1, final Symbols.Symbol tparam) {
            $anonfun$apply$5($this, x2$1, lo$2, hi$1, tparam);
            return BoxedUnit.UNIT;
         }

         // $FF: synthetic method
         public static final Object $anonfun$apply$6$adapted(final PolyTypeVarianceMap$ $this, final Types.PolyType x2$1, final Types.Type resultType$1, final Symbols.Symbol x$4) {
            $anonfun$apply$6($this, x2$1, resultType$1, x$4);
            return BoxedUnit.UNIT;
         }
      }
   }

   private final class varianceInType {
      private Variance.Extractor inAnnotationAtp;
      private Variance.Extractor2 inArgParam;
      private Variance.Extractor inSym;
      private Types.Type tp;
      private Symbols.Symbol tparam;
      private boolean considerUnchecked;
      private final Variance.Extractor inType;
      private volatile byte bitmap$0;
      // $FF: synthetic field
      private final SymbolTable $outer;

      private int inArgs(final Symbols.Symbol sym, final List args) {
         return Variance$.MODULE$.foldExtract2(args, sym.typeParams(), this.inArgParam());
      }

      private int inSyms(final List syms) {
         return Variance$.MODULE$.foldExtract(syms, this.inSym());
      }

      private int inTypes(final List tps) {
         return Variance$.MODULE$.foldExtract(tps, this.inType);
      }

      private int inAnnots(final List anns) {
         return Variance$.MODULE$.foldExtract(anns, this.inAnnotationAtp());
      }

      private boolean unchecked(final List anns) {
         if (this.considerUnchecked) {
            if (anns == null) {
               throw null;
            }

            List exists_these = anns;

            boolean var10000;
            while(true) {
               if (exists_these.isEmpty()) {
                  var10000 = false;
                  break;
               }

               AnnotationInfos.AnnotationInfo var3 = (AnnotationInfos.AnnotationInfo)exists_these.head();
               if ($anonfun$unchecked$1(this, var3)) {
                  var10000 = true;
                  break;
               }

               exists_these = (List)exists_these.tail();
            }

            Object var4 = null;
            if (var10000) {
               return true;
            }
         }

         return false;
      }

      private Variance.Extractor inAnnotationAtp$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               this.inAnnotationAtp = (a) -> this.inType.apply(a.atp());
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.inAnnotationAtp;
      }

      private Variance.Extractor inAnnotationAtp() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.inAnnotationAtp$lzycompute() : this.inAnnotationAtp;
      }

      private Variance.Extractor2 inArgParam$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               this.inArgParam = (a, b) -> Variance$.MODULE$.$times$extension(this.inType.apply(a), b.variance());
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.inArgParam;
      }

      private Variance.Extractor2 inArgParam() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.inArgParam$lzycompute() : this.inArgParam;
      }

      private Variance.Extractor inSym$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 4) == 0) {
               this.inSym = (sym) -> sym.isAliasType() ? Variance$.MODULE$.cut$extension(this.inType.apply(sym.info())) : this.inType.apply(sym.info());
               this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.inSym;
      }

      private Variance.Extractor inSym() {
         return (byte)(this.bitmap$0 & 4) == 0 ? this.inSym$lzycompute() : this.inSym;
      }

      public int apply(final Types.Type tp, final Symbols.Symbol tparam, final boolean considerUnchecked) {
         this.tp = tp;
         this.tparam = tparam;
         this.considerUnchecked = considerUnchecked;

         int var10000;
         try {
            var10000 = this.inType.apply(tp);
         } finally {
            this.tp = null;
            this.tparam = null;
            this.considerUnchecked = false;
         }

         return var10000;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$unchecked$1(final varianceInType $this, final AnnotationInfos.AnnotationInfo x$8) {
         return x$8.matches($this.$outer.definitions().uncheckedVarianceClass());
      }

      public varianceInType() {
         if (Variances.this == null) {
            throw null;
         } else {
            this.$outer = Variances.this;
            super();
            this.considerUnchecked = false;
            this.inType = (x0$1) -> {
               boolean var2 = false;
               Types.TypeRef var3 = null;
               boolean var4 = false;
               Types.AnnotatedType var5 = null;
               if (x0$1 instanceof Types.ProtoType) {
                  Types.ProtoType var6 = (Types.ProtoType)x0$1;
                  return this.inType.apply(var6.toVariantType());
               } else if (this.$outer.ErrorType().equals(x0$1) ? true : (this.$outer.NoType().equals(x0$1) ? true : this.$outer.NoPrefix().equals(x0$1))) {
                  return Variance$.MODULE$.Bivariant();
               } else {
                  boolean var10000;
                  if (x0$1 instanceof Types.ThisType) {
                     var10000 = true;
                  } else {
                     label92: {
                        if (x0$1 instanceof Types.ConstantType) {
                           Types.ConstantType var7 = (Types.ConstantType)x0$1;
                           if (!this.$outer.ConstantType().unapply(var7).isEmpty()) {
                              var10000 = true;
                              break label92;
                           }
                        }

                        var10000 = false;
                     }
                  }

                  if (var10000) {
                     return Variance$.MODULE$.Bivariant();
                  } else {
                     if (x0$1 instanceof Types.TypeRef) {
                        var2 = true;
                        var3 = (Types.TypeRef)x0$1;
                        if (var3.sym() == this.tparam) {
                           return Variance$.MODULE$.Covariant();
                        }
                     }

                     if (x0$1 instanceof Types.NullaryMethodType) {
                        Types.Type restpe = ((Types.NullaryMethodType)x0$1).resultType();
                        return this.inType.apply(restpe);
                     } else if (x0$1 instanceof Types.SingleType) {
                        Types.Type pre = ((Types.SingleType)x0$1).pre();
                        return this.inType.apply(pre);
                     } else {
                        if (var2) {
                           Types.Type pre = var3.pre();
                           if (this.tp.isHigherKinded()) {
                              return this.inType.apply(pre);
                           }
                        }

                        if (var2) {
                           Types.Type pre = var3.pre();
                           Symbols.Symbol sym = var3.sym();
                           List args = var3.args();
                           return Variance$.MODULE$.$amp$extension(this.inType.apply(pre), this.inArgs(sym, args));
                        } else if (x0$1 instanceof Types.TypeBounds) {
                           Types.TypeBounds var14 = (Types.TypeBounds)x0$1;
                           Types.Type lo = var14.lo();
                           Types.Type hi = var14.hi();
                           return Variance$.MODULE$.$amp$extension(Variance$.MODULE$.flip$extension(this.inType.apply(lo)), this.inType.apply(hi));
                        } else if (x0$1 instanceof Types.RefinedType) {
                           Types.RefinedType var17 = (Types.RefinedType)x0$1;
                           List parents = var17.parents();
                           Scopes.Scope defs = var17.decls();
                           return Variance$.MODULE$.$amp$extension(this.inTypes(parents), this.inSyms(defs.toList()));
                        } else if (x0$1 instanceof Types.MethodType) {
                           Types.MethodType var20 = (Types.MethodType)x0$1;
                           List params = var20.params();
                           Types.Type restpe = var20.resultType();
                           return Variance$.MODULE$.$amp$extension(Variance$.MODULE$.flip$extension(this.inSyms(params)), this.inType.apply(restpe));
                        } else if (x0$1 instanceof Types.PolyType) {
                           Types.PolyType var23 = (Types.PolyType)x0$1;
                           List tparams = var23.typeParams();
                           Types.Type restpe = var23.resultType();
                           return Variance$.MODULE$.$amp$extension(Variance$.MODULE$.flip$extension(this.inSyms(tparams)), this.inType.apply(restpe));
                        } else if (x0$1 instanceof Types.ExistentialType) {
                           Types.ExistentialType var26 = (Types.ExistentialType)x0$1;
                           List tparams = var26.quantified();
                           Types.Type restpe = var26.underlying();
                           return Variance$.MODULE$.$amp$extension(this.inSyms(tparams), this.inType.apply(restpe));
                        } else {
                           if (x0$1 instanceof Types.AnnotatedType) {
                              var4 = true;
                              var5 = (Types.AnnotatedType)x0$1;
                              List annots = var5.annotations();
                              if (this.unchecked(annots)) {
                                 return Variance$.MODULE$.Bivariant();
                              }
                           }

                           if (var4) {
                              List annots = var5.annotations();
                              Types.Type tp = var5.underlying();
                              return Variance$.MODULE$.$amp$extension(this.inAnnots(annots), this.inType.apply(tp));
                           } else if (x0$1 instanceof Types.SuperType) {
                              Types.SuperType var32 = (Types.SuperType)x0$1;
                              Types.Type thistpe = var32.thistpe();
                              Types.Type supertpe = var32.supertpe();
                              return Variance$.MODULE$.$amp$extension(this.inType.apply(thistpe), this.inType.apply(supertpe));
                           } else {
                              throw new MatchError(x0$1);
                           }
                        }
                     }
                  }
               }
            };
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$unchecked$1$adapted(final varianceInType $this, final AnnotationInfos.AnnotationInfo x$8) {
         return BoxesRunTime.boxToBoolean($anonfun$unchecked$1($this, x$8));
      }
   }
}
