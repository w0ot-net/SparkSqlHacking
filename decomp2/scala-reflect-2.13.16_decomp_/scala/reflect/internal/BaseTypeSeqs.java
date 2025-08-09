package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Predef;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Range;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.BitSet;
import scala.collection.mutable.ListBuffer;
import scala.math.LowPriorityOrderingImplicits;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.StatisticsStatics;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uh!\u0003\u00180!\u0003\r\tANAx\u0011\u0015Y\u0004\u0001\"\u0001=\u0011\u0015\u0001\u0005\u0001\"\u0005B\u0011\u001d\ti\t\u0001C\t\u0003\u001f3A\u0001\u0012\u0001\u0001\u000b\"Ia\t\u0002B\u0003\u0002\u0003\u0006Ia\u0012\u0005\n'\u0012\u0011)\u0011!Q\u0001\nQCaa\u0016\u0003\u0005\u0012EB\u0006BB/\u0005A\u0003%a\fC\u0003c\t\u0011\u00051\r\u0003\u0004e\t\u0001\u0006I!\u001a\u0005\u0006[\u0012!\tA\u001c\u0005\u0006c\u0012!\tA\u001d\u0005\u0006i\u0012!\t!\u001e\u0005\u0006y\u0012!)! \u0005\b\u0003\u0003!A\u0011AA\u0002\u0011\u001d\t)\u0001\u0002C\u0001\u0003\u000fAq!a\u0004\u0005\t\u0003\t\t\u0002C\u0004\u0002\u001c\u0011!\t!!\b\t\u000f\u0005\rB\u0001\"\u0001\u0002&!9\u0011\u0011\u0006\u0003\u0005\u0002\u0005-\u0002bBA\u001c\t\u0011\u0005\u0011\u0011\b\u0005\b\u0003{!A\u0011AA \u0011)\ti\u0005\u0002EC\u0002\u0013\u0005\u0011q\n\u0005\b\u00033\"A\u0011CA(\u0011\u001d\tY\u0006\u0002C!\u0003;Bq!!\u001e\u0005\t\u0013\t9\b\u0003\u0007\u0002\u0004\u0012\u0011)Q1A\u0005\u0002\u0001\t\u0019\u0001\u0003\u0007\u0002\u0006\u0012\u0011)Q1A\u0005\u0002\u0001\t9\t\u0003\u0006\u0002L\u0002A)\u0019!C\u0001\u0003\u001bDq!a4\u0001\t\u0003\t\t\u000eC\u0004\u0002V\u0002!\t!a6\u0007\r\u0005M\u0005\u0001AAK\u0011%\t9\n\tB\u0001B\u0003%!\t\u0003\u0006\u00020\u0001\u0012\t\u0011)A\u0005\u0003cAaa\u0016\u0011\u0005\u0002\u0005e\u0005BB7!\t\u0003\ny\n\u0003\u0004rA\u0011\u0005\u00131\u0015\u0005\b\u0003\u0003\u0001C\u0011IAT\u0011\u001d\ty\u0001\tC!\u0003gCq!!\u000b!\t\u0003\nI\fC\u0004\u00028\u0001\"\t%a0\t\u000f\u0005u\u0002\u0005\"\u0011\u0002D\"9\u0011\u0011\f\u0011\u0005R\u0005=\u0003bBA.A\u0011\u0005\u0013Q\f\u0005\n\u00037\u0004!\u0019!C\u0001\u0003;\u0014ABQ1tKRK\b/Z*fcNT!\u0001M\u0019\u0002\u0011%tG/\u001a:oC2T!AM\u001a\u0002\u000fI,g\r\\3di*\tA'A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u00019\u0004C\u0001\u001d:\u001b\u0005\u0019\u0014B\u0001\u001e4\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012!\u0010\t\u0003qyJ!aP\u001a\u0003\tUs\u0017\u000e^\u0001\u000f]\u0016<()Y:f)f\u0004XmU3r)\u0015\u0011\u0015\u0011RAF!\t\u0019E!D\u0001\u0001\u0005-\u0011\u0015m]3UsB,7+Z9\u0014\u0005\u00119\u0014!L:dC2\fGE]3gY\u0016\u001cG\u000fJ5oi\u0016\u0014h.\u00197%\u0005\u0006\u001cX\rV=qKN+\u0017o\u001d\u0013%a\u0006\u0014XM\u001c;tAA\u0019\u0001j\u0013(\u000f\u0005aJ\u0015B\u0001&4\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001T'\u0003\t1K7\u000f\u001e\u0006\u0003\u0015N\u0002\"aQ(\n\u0005A\u000b&\u0001\u0002+za\u0016L!AU\u0018\u0003\u000bQK\b/Z:\u0002WM\u001c\u0017\r\\1%e\u00164G.Z2uI%tG/\u001a:oC2$#)Y:f)f\u0004XmU3rg\u0012\"S\r\\3ng\u0002\u00022\u0001O+O\u0013\t16GA\u0003BeJ\f\u00170\u0001\u0004=S:LGO\u0010\u000b\u0004\u0005f[\u0006\"\u0002.\b\u0001\u00049\u0015a\u00029be\u0016tGo\u001d\u0005\u00069\u001e\u0001\r\u0001V\u0001\u0006K2,Wn]\u0001\fif\u0004XmU=nE>d7\u000fE\u00029+~\u0003\"\u0001\u000f1\n\u0005\u0005\u001c$aA%oi\u00061A.\u001a8hi\",\u0012aX\u0001\ba\u0016tG-\u001b8h!\t17.D\u0001h\u0015\tA\u0017.A\u0004nkR\f'\r\\3\u000b\u0005)\u001c\u0014AC2pY2,7\r^5p]&\u0011An\u001a\u0002\u0007\u0005&$8+\u001a;\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u00059{\u0007\"\u00029\f\u0001\u0004y\u0016!A5\u0002\u000fI\fw/\u00127f[R\u0011aj\u001d\u0005\u0006a2\u0001\raX\u0001\u000bif\u0004XmU=nE>dGC\u0001<|!\t\u0019u/\u0003\u0002ys\n11+_7c_2L!A_\u0018\u0003\u000fMKXNY8mg\")\u0001/\u0004a\u0001?\u0006i!-Y:f)f\u0004X-\u00138eKb$\"a\u0018@\t\u000b}t\u0001\u0019\u0001<\u0002\u0007MLX.\u0001\u0004u_2K7\u000f^\u000b\u0002\u000f\u0006QAo\\%uKJ\fGo\u001c:\u0016\u0005\u0005%\u0001\u0003\u0002%\u0002\f9K1!!\u0004N\u0005!IE/\u001a:bi>\u0014\u0018\u0001B2paf$RAQA\n\u0003/Aa!!\u0006\u0012\u0001\u0004q\u0015\u0001\u00025fC\u0012Da!!\u0007\u0012\u0001\u0004y\u0016AB8gMN,G/A\u0004qe\u0016\u0004XM\u001c3\u0015\u0007\t\u000by\u0002\u0003\u0004\u0002\"I\u0001\rAT\u0001\u0003iB\f!\"\u001e9eCR,\u0007*Z1e)\r\u0011\u0015q\u0005\u0005\u0007\u0003C\u0019\u0002\u0019\u0001(\u0002\u00075\f\u0007\u000fF\u0002C\u0003[Aq!a\f\u0015\u0001\u0004\t\t$A\u0001g!\u0015A\u00141\u0007(O\u0013\r\t)d\r\u0002\n\rVt7\r^5p]F\nq\u0001\\1uK6\u000b\u0007\u000fF\u0002C\u0003wAq!a\f\u0016\u0001\u0004\t\t$\u0001\u0004fq&\u001cHo\u001d\u000b\u0005\u0003\u0003\n9\u0005E\u00029\u0003\u0007J1!!\u00124\u0005\u001d\u0011un\u001c7fC:Dq!!\u0013\u0017\u0001\u0004\tY%A\u0001q!\u0019A\u00141\u0007(\u0002B\u0005AQ.\u0019=EKB$\b.\u0006\u0002\u0002RA!\u00111KA+\u001b\u0005y\u0013bAA,_\t)A)\u001a9uQ\u0006yQ.\u0019=EKB$\bn\u00144FY\u0016l7/\u0001\u0005u_N#(/\u001b8h)\t\ty\u0006\u0005\u0003\u0002b\u0005=d\u0002BA2\u0003W\u00022!!\u001a4\u001b\t\t9GC\u0002\u0002jU\na\u0001\u0010:p_Rt\u0014bAA7g\u00051\u0001K]3eK\u001aLA!!\u001d\u0002t\t11\u000b\u001e:j]\u001eT1!!\u001c4\u0003%!\u0018\u0010]3FeJ|'\u000f\u0006\u0003\u0002z\u0005}\u0004c\u0001\u001d\u0002|%\u0019\u0011QP\u001a\u0003\u000f9{G\u000f[5oO\"9\u0011\u0011\u0011\u000eA\u0002\u0005}\u0013aA7tO\u0006a3oY1mC\u0012\u0012XM\u001a7fGR$\u0013N\u001c;fe:\fG\u000e\n\"bg\u0016$\u0016\u0010]3TKF\u001cH\u0005\n9be\u0016tGo]\u0001+g\u000e\fG.\u0019\u0013sK\u001adWm\u0019;%S:$XM\u001d8bY\u0012\u0012\u0015m]3UsB,7+Z9tI\u0011*G.Z7t+\u0005!\u0006\"\u0002.\u0003\u0001\u00049\u0005\"\u0002/\u0003\u0001\u0004!\u0016\u0001\u00068fo6\u000b\u0007\u000f]3e\u0005\u0006\u001cX\rV=qKN+\u0017\u000f\u0006\u0004\u0002\u0012\u0006\u001d\u0017\u0011\u001a\t\u0003\u0007\u0002\u0012\u0011#T1qa\u0016$')Y:f)f\u0004XmU3r'\t\u0001#)\u0001\u0003pe&<GCBAI\u00037\u000bi\n\u0003\u0004\u0002\u0018\u000e\u0002\rA\u0011\u0005\b\u0003_\u0019\u0003\u0019AA\u0019)\rq\u0015\u0011\u0015\u0005\u0006a\u0012\u0002\ra\u0018\u000b\u0004\u001d\u0006\u0015\u0006\"\u00029&\u0001\u0004yVCAAU!\u0015\tY+!-O\u001b\t\tiKC\u0002\u00020&\f\u0011\"[7nkR\f'\r\\3\n\u00071\u000bi\u000bF\u0003C\u0003k\u000b9\f\u0003\u0004\u0002\u0016\u001d\u0002\rA\u0014\u0005\u0007\u000339\u0003\u0019A0\u0015\u0007\t\u000bY\fC\u0004\u0002>\"\u0002\r!!\r\u0002\u0003\u001d$2AQAa\u0011\u001d\ti,\u000ba\u0001\u0003c!B!!\u0011\u0002F\"9\u0011\u0011\n\u0016A\u0002\u0005-\u0003BBAL\u0007\u0001\u0007!\tC\u0004\u00020\r\u0001\r!!\r\u0002!UtG-\u001a;CCN,G+\u001f9f'\u0016\fX#\u0001\"\u0002)\t\f7/\u001a+za\u0016\u001c\u0016N\\4mKR|gnU3r)\r\u0011\u00151\u001b\u0005\u0007\u0003Cq\u0002\u0019\u0001(\u0002'\r|W\u000e]8v]\u0012\u0014\u0015m]3UsB,7+Z9\u0015\u0007\t\u000bI\u000e\u0003\u0004\u0002\"}\u0001\rAT\u0001\u0012\u0007f\u001cG.[2J]\",'/\u001b;b]\u000e,WCAAp!\u0011\t\t/a;\u000e\u0005\u0005\r(\u0002BAs\u0003O\fA\u0001\\1oO*\u0011\u0011\u0011^\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002n\u0006\r(!\u0003+ie><\u0018M\u00197f!\u0011\t\u0019&!=\n\u0007\u0005MxFA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007"
)
public interface BaseTypeSeqs {
   void scala$reflect$internal$BaseTypeSeqs$_setter_$CyclicInheritance_$eq(final Throwable x$1);

   // $FF: synthetic method
   static BaseTypeSeq newBaseTypeSeq$(final BaseTypeSeqs $this, final List parents, final Types.Type[] elems) {
      return $this.newBaseTypeSeq(parents, elems);
   }

   default BaseTypeSeq newBaseTypeSeq(final List parents, final Types.Type[] elems) {
      return (SymbolTable)this.new BaseTypeSeq(parents, elems);
   }

   // $FF: synthetic method
   static MappedBaseTypeSeq newMappedBaseTypeSeq$(final BaseTypeSeqs $this, final BaseTypeSeq orig, final Function1 f) {
      return $this.newMappedBaseTypeSeq(orig, f);
   }

   default MappedBaseTypeSeq newMappedBaseTypeSeq(final BaseTypeSeq orig, final Function1 f) {
      return (SymbolTable)this.new MappedBaseTypeSeq(orig, f);
   }

   // $FF: synthetic method
   static BaseTypeSeq undetBaseTypeSeq$(final BaseTypeSeqs $this) {
      return $this.undetBaseTypeSeq();
   }

   default BaseTypeSeq undetBaseTypeSeq() {
      return this.newBaseTypeSeq(.MODULE$, (Types.Type[])scala.Array..MODULE$.apply(.MODULE$, ((Types)this).TypeTagg()));
   }

   // $FF: synthetic method
   static BaseTypeSeq baseTypeSingletonSeq$(final BaseTypeSeqs $this, final Types.Type tp) {
      return $this.baseTypeSingletonSeq(tp);
   }

   default BaseTypeSeq baseTypeSingletonSeq(final Types.Type tp) {
      Nil var10001 = .MODULE$;
      ((Types)this).TypeTagg();
      Object var2 = ((Types)this).TypeTagg().newArray(1);
      scala.runtime.ScalaRunTime..MODULE$.array_update(var2, 0, tp);
      return this.newBaseTypeSeq(var10001, (Types.Type[])var2);
   }

   // $FF: synthetic method
   static BaseTypeSeq compoundBaseTypeSeq$(final BaseTypeSeqs $this, final Types.Type tp) {
      return $this.compoundBaseTypeSeq(tp);
   }

   default BaseTypeSeq compoundBaseTypeSeq(final Types.Type tp) {
      Symbols.Symbol tsym = tp.typeSymbol();
      List parents = tp.parents();
      ListBuffer buf = new ListBuffer();
      Object $plus$eq_elem = tsym.tpe_$times();
      buf.addOne($plus$eq_elem);
      $plus$eq_elem = null;
      int btsSize = 1;
      if (parents.nonEmpty()) {
         int nparents = parents.length();
         BaseTypeSeq[] pbtss = new BaseTypeSeq[nparents];
         int[] index = new int[nparents];
         int var23 = 0;

         for(List foreach_these = parents; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            BaseTypeSeq $anonfun$compoundBaseTypeSeq$1_parentBts = ((Types.Type)foreach_these.head()).dealias().baseTypeSeq();
            pbtss[var23] = $anonfun$compoundBaseTypeSeq$1_parentBts == this.undetBaseTypeSeq() ? ((Definitions)this).definitions().AnyClass().info().baseTypeSeq() : $anonfun$compoundBaseTypeSeq$1_parentBts;
            index[var23] = 0;
            ++var23;
            $anonfun$compoundBaseTypeSeq$1_parentBts = null;
         }

         Object var26 = null;
         Symbols.Symbol minSym = ((Symbols)this).NoSymbol();

         while(true) {
            Symbols.ClassSymbol var10 = ((Definitions)this).definitions().AnyClass();
            if (minSym == null) {
               if (var10 == null) {
                  break;
               }
            } else if (minSym.equals(var10)) {
               break;
            }

            minSym = this.nextTypeSymbol$1(0, index, pbtss);

            for(int var34 = 1; var34 < nparents; ++var34) {
               Symbols.Symbol nextSym = this.nextTypeSymbol$1(var34, index, pbtss);
               if (nextSym.isLess(minSym)) {
                  minSym = nextSym;
               }
            }

            Object var24 = .MODULE$;

            for(int var35 = 0; var35 < nparents; ++var35) {
               Symbols.Symbol var10000 = this.nextTypeSymbol$1(var35, index, pbtss);
               if (var10000 == null) {
                  if (minSym != null) {
                     continue;
                  }
               } else if (!var10000.equals(minSym)) {
                  continue;
               }

               Types.Type var12 = this.nextRawElem$1(var35, index, pbtss);
               if (var12 instanceof Types.RefinedType) {
                  List variants = ((Types.RefinedType)var12).parents();
                  if (variants == null) {
                     throw null;
                  }

                  for(List foreach_these = variants; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                     Types.Type var20 = (Types.Type)foreach_these.head();
                     if (!this.loop$1((List)var24, var20)) {
                        List updateInMinTypes$1_$colon$colon_this = (List)var24;
                        scala.collection.immutable..colon.colon var36 = new scala.collection.immutable..colon.colon(var20, updateInMinTypes$1_$colon$colon_this);
                        Object var32 = null;
                        var24 = var36;
                     }

                     Object var33 = null;
                  }

                  Object var27 = null;
               } else {
                  if (!this.loop$1((List)var24, var12)) {
                     List updateInMinTypes$1_$colon$colon_this = (List)var24;
                     scala.collection.immutable..colon.colon var37 = new scala.collection.immutable..colon.colon(var12, updateInMinTypes$1_$colon$colon_this);
                     Object var29 = null;
                     var24 = var37;
                  }

                  Object var30 = null;
               }

               int var10002 = index[var35]++;
            }

            Object $plus$eq_elem = ((Types)this).intersectionTypeForLazyBaseType((List)var24);
            buf.addOne($plus$eq_elem);
            $plus$eq_elem = null;
            ++btsSize;
         }
      }

      Types.Type[] elems = new Types.Type[btsSize];
      buf.copyToArray(elems, 0);
      return this.newBaseTypeSeq(parents, elems);
   }

   Throwable CyclicInheritance();

   // $FF: synthetic method
   static void $anonfun$compoundBaseTypeSeq$1(final BaseTypeSeqs $this, final BaseTypeSeq[] pbtss$1, final IntRef i$2, final int[] index$1, final Types.Type p) {
      BaseTypeSeq parentBts = p.dealias().baseTypeSeq();
      pbtss$1[i$2.elem] = parentBts == $this.undetBaseTypeSeq() ? ((Definitions)$this).definitions().AnyClass().info().baseTypeSeq() : parentBts;
      index$1[i$2.elem] = 0;
      ++i$2.elem;
   }

   private Symbols.Symbol nextTypeSymbol$1(final int i, final int[] index$1, final BaseTypeSeq[] pbtss$1) {
      int j = index$1[i];
      BaseTypeSeq pbts = pbtss$1[i];
      return (Symbols.Symbol)(j < pbts.length() ? pbts.typeSymbol(j) : ((Definitions)this).definitions().AnyClass());
   }

   private Types.Type nextRawElem$1(final int i, final int[] index$1, final BaseTypeSeq[] pbtss$1) {
      int j = index$1[i];
      BaseTypeSeq pbts = pbtss$1[i];
      return j < pbts.length() ? pbts.rawElem(j) : ((Definitions)this).definitions().AnyTpe();
   }

   private boolean loop$1(final List tps, final Types.Type tp$1) {
      while(true) {
         if (tps instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)tps;
            Types.Type x = (Types.Type)var3.head();
            List tps = var3.next$access$1();
            if (!tp$1.$eq$colon$eq(x)) {
               SymbolTable var10000 = (SymbolTable)this;
               tps = tps;
               this = var10000;
               continue;
            }

            return true;
         }

         return false;
      }
   }

   private void updateInMinTypes$1(final Types.Type tp, final ObjectRef minTypes$1) {
      if (!this.loop$1((List)minTypes$1.elem, tp)) {
         List var10001 = (List)minTypes$1.elem;
         if (var10001 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10001;
            minTypes$1.elem = new scala.collection.immutable..colon.colon(tp, $colon$colon_this);
         }
      }
   }

   // $FF: synthetic method
   static void $anonfun$compoundBaseTypeSeq$2(final BaseTypeSeqs $this, final ObjectRef minTypes$1, final Types.Type tp) {
      $this.updateInMinTypes$1(tp, minTypes$1);
   }

   static void $init$(final BaseTypeSeqs $this) {
      $this.scala$reflect$internal$BaseTypeSeqs$_setter_$CyclicInheritance_$eq(new Throwable());
   }

   // $FF: synthetic method
   static Object $anonfun$compoundBaseTypeSeq$1$adapted(final BaseTypeSeqs $this, final BaseTypeSeq[] pbtss$1, final IntRef i$2, final int[] index$1, final Types.Type p) {
      $anonfun$compoundBaseTypeSeq$1($this, pbtss$1, i$2, index$1, p);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$compoundBaseTypeSeq$2$adapted(final BaseTypeSeqs $this, final ObjectRef minTypes$1, final Types.Type tp) {
      $anonfun$compoundBaseTypeSeq$2($this, minTypes$1, tp);
      return BoxedUnit.UNIT;
   }

   public class BaseTypeSeq {
      private int maxDepth;
      private final List scala$reflect$internal$BaseTypeSeqs$$parents;
      private final Types.Type[] scala$reflect$internal$BaseTypeSeqs$$elems;
      private final int[] typeSymbols;
      private final BitSet pending;
      private volatile boolean bitmap$0;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public List scala$reflect$internal$BaseTypeSeqs$$parents() {
         return this.scala$reflect$internal$BaseTypeSeqs$$parents;
      }

      public Types.Type[] scala$reflect$internal$BaseTypeSeqs$$elems() {
         return this.scala$reflect$internal$BaseTypeSeqs$$elems;
      }

      public int length() {
         return this.scala$reflect$internal$BaseTypeSeqs$$elems().length;
      }

      public Types.Type apply(final int i) {
         if (this.pending.contains(i)) {
            this.pending.clear();
            throw this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().CyclicInheritance();
         } else {
            Types.Type var2 = this.scala$reflect$internal$BaseTypeSeqs$$elems()[i];
            if (var2 instanceof Types.RefinedType) {
               Types.RefinedType var3 = (Types.RefinedType)var2;
               return this.computeLazyType$1(var3, i);
            } else {
               if (var2 instanceof Types.ExistentialType) {
                  Types.ExistentialType var4 = (Types.ExistentialType)var2;
                  List quantified = var4.quantified();
                  Types.Type rtp = var4.underlying();
                  if (rtp instanceof Types.RefinedType) {
                     Types.RefinedType var7 = (Types.RefinedType)rtp;
                     return this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().existentialAbstraction(quantified, this.computeLazyType$1(var7, i), this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().existentialAbstraction$default$3());
                  }
               }

               return var2;
            }
         }
      }

      public Types.Type rawElem(final int i) {
         return this.scala$reflect$internal$BaseTypeSeqs$$elems()[i];
      }

      public Symbols.Symbol typeSymbol(final int i) {
         return this.scala$reflect$internal$BaseTypeSeqs$$elems()[i].typeSymbol();
      }

      public final int baseTypeIndex(final Symbols.Symbol sym) {
         int symId = sym.id();
         int i = 0;

         for(int len = this.length(); i < len; ++i) {
            if (this.typeSymbols[i] == symId) {
               return i;
            }
         }

         return -1;
      }

      public List toList() {
         ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(this.scala$reflect$internal$BaseTypeSeqs$$elems());
         if (var10000 == null) {
            throw null;
         } else {
            return IterableOnceOps.toList$(var10000);
         }
      }

      public Iterator toIterator() {
         return scala.collection.ArrayOps..MODULE$.iterator$extension(this.scala$reflect$internal$BaseTypeSeqs$$elems());
      }

      public BaseTypeSeq copy(final Types.Type head, final int offset) {
         Types.Type[] arr = new Types.Type[this.scala$reflect$internal$BaseTypeSeqs$$elems().length + offset];
         System.arraycopy(this.scala$reflect$internal$BaseTypeSeqs$$elems(), 0, arr, offset, this.scala$reflect$internal$BaseTypeSeqs$$elems().length);
         arr[0] = head;
         return this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().newBaseTypeSeq(this.scala$reflect$internal$BaseTypeSeqs$$parents(), arr);
      }

      public BaseTypeSeq prepend(final Types.Type tp) {
         return this.copy(tp, 1);
      }

      public BaseTypeSeq updateHead(final Types.Type tp) {
         return this.copy(tp, 0);
      }

      public BaseTypeSeq map(final Function1 f) {
         int len = this.length();
         Types.Type[] arr = new Types.Type[len];

         for(int i = 0; i < len; ++i) {
            arr[i] = (Types.Type)f.apply(this.scala$reflect$internal$BaseTypeSeqs$$elems()[i]);
         }

         return this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().newBaseTypeSeq(this.scala$reflect$internal$BaseTypeSeqs$$parents(), arr);
      }

      public BaseTypeSeq lateMap(final Function1 f) {
         return this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().newMappedBaseTypeSeq(this, f);
      }

      public boolean exists(final Function1 p) {
         Object[] refArrayOps_xs = this.scala$reflect$internal$BaseTypeSeqs$$elems();
         Object var5 = null;
         Object exists$extension_$this = refArrayOps_xs;
         int exists$extension_indexWhere$extension_i = 0;

         int var6;
         while(true) {
            if (exists$extension_indexWhere$extension_i >= ((Object[])exists$extension_$this).length) {
               var6 = -1;
               break;
            }

            if (BoxesRunTime.unboxToBoolean(p.apply(((Object[])exists$extension_$this)[exists$extension_indexWhere$extension_i]))) {
               var6 = exists$extension_indexWhere$extension_i;
               break;
            }

            ++exists$extension_indexWhere$extension_i;
         }

         return var6 >= 0;
      }

      private int maxDepth$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.maxDepth = this.maxDepthOfElems();
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.maxDepth;
      }

      public int maxDepth() {
         return !this.bitmap$0 ? this.maxDepth$lzycompute() : this.maxDepth;
      }

      public int maxDepthOfElems() {
         int var6 = Depth$.MODULE$.Zero();
         RichInt var10000 = scala.runtime.RichInt..MODULE$;
         byte var1 = 1;
         int until$extension_end = this.length();
         Range var8 = scala.collection.immutable.Range..MODULE$;
         Range foreach$mVc$sp_this = new Range.Exclusive(var1, until$extension_end, 1);
         if (!foreach$mVc$sp_this.isEmpty()) {
            int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

            while(true) {
               Depth$ var9 = Depth$.MODULE$;
               SymbolTable var10002 = this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer();
               Types.Type $anonfun$maxDepthOfElems$1_typeDepth_tp = this.scala$reflect$internal$BaseTypeSeqs$$elems()[foreach$mVc$sp_i];
               if (var10002 == null) {
                  throw null;
               }

               int var10 = Types.typeDepth$(var10002, $anonfun$maxDepthOfElems$1_typeDepth_tp);
               $anonfun$maxDepthOfElems$1_typeDepth_tp = null;
               var6 = var9.max$extension(var6, var10);
               if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
                  break;
               }

               foreach$mVc$sp_i += foreach$mVc$sp_this.step();
            }
         }

         return var6;
      }

      public String toString() {
         ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(this.scala$reflect$internal$BaseTypeSeqs$$elems());
         String mkString_end = ")";
         String mkString_sep = ",";
         String mkString_start = "BTS(";
         if (var10000 == null) {
            throw null;
         } else {
            return IterableOnceOps.mkString$(var10000, mkString_start, mkString_sep, mkString_end);
         }
      }

      private Nothing typeError(final String msg) {
         Types.TypeError var10000 = new Types.TypeError;
         SymbolTable var10002 = this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer();
         StringBuilder var10003 = (new StringBuilder(53)).append("the type intersection ");
         List var10004 = this.scala$reflect$internal$BaseTypeSeqs$$parents();
         String mkString_sep = " with ";
         if (var10004 == null) {
            throw null;
         } else {
            AbstractIterable mkString_this = var10004;
            String mkString_end = "";
            String mkString_start = "";
            String var10 = IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
            Object var8 = null;
            Object var9 = null;
            mkString_this = null;
            Object var7 = null;
            var10000.<init>(var10003.append(var10).append(" is malformed").append("\n --- because ---\n").append(msg).toString());
            throw var10000;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$apply$1(final BaseTypeSeq $this, final Types.RefinedType rtp$1) {
         return !$this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().isIntersectionTypeForLazyBaseType(rtp$1);
      }

      private final Types.Type computeLazyType$1(final Types.RefinedType rtp, final int i$1) {
         SymbolTable var10000 = this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer();
         Function0 devWarningIf_msg = () -> (new StringBuilder(114)).append("unexpected RefinedType in base type seq, lazy BTS elements should be created via intersectionTypeForLazyBaseType: ").append(rtp).toString();
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable devWarningIf_this;
            label69: {
               devWarningIf_this = var10000;
               MutableSettings.SettingsOps$ var35 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var36 = MutableSettings$.MODULE$;
               MutableSettings devWarningIf_isDeveloper_SettingsOps_settings = devWarningIf_this.settings();
               MutableSettings var37 = devWarningIf_isDeveloper_SettingsOps_settings;
               devWarningIf_isDeveloper_SettingsOps_settings = null;
               MutableSettings devWarningIf_isDeveloper_isDebug$extension_$this = var37;
               boolean var38 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(devWarningIf_isDeveloper_isDebug$extension_$this.debug().value());
               devWarningIf_isDeveloper_isDebug$extension_$this = null;
               if (!var38) {
                  MutableSettings.SettingsOps$ var39 = MutableSettings.SettingsOps$.MODULE$;
                  MutableSettings$ var40 = MutableSettings$.MODULE$;
                  MutableSettings devWarningIf_isDeveloper_SettingsOps_settings = devWarningIf_this.settings();
                  MutableSettings var41 = devWarningIf_isDeveloper_SettingsOps_settings;
                  devWarningIf_isDeveloper_SettingsOps_settings = null;
                  MutableSettings devWarningIf_isDeveloper_isDeveloper$extension_$this = var41;
                  boolean var42 = StatisticsStatics.DEVELOPER_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(devWarningIf_isDeveloper_isDeveloper$extension_$this.developer().value());
                  devWarningIf_isDeveloper_isDeveloper$extension_$this = null;
                  if (!var42) {
                     var43 = false;
                     break label69;
                  }
               }

               var43 = true;
            }

            Object var24 = null;
            Object var27 = null;
            if (var43 && $anonfun$apply$1(this, rtp)) {
               devWarningIf_this.devWarning(devWarningIf_msg);
            }

            Object var20 = null;
            Object var21 = null;
            List variants = rtp.parents();
            BitSet var44 = this.pending;
            Integer $plus$eq_elem = i$1;
            if (var44 == null) {
               throw null;
            } else {
               var44.addOne($plus$eq_elem);
               Object var28 = null;

               try {
                  Types.Type var4 = this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().mergePrefixAndArgs(variants, Variance$.MODULE$.Contravariant(), this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().lubDepth(variants));
                  if (this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().NoType().equals(var4)) {
                     StringBuilder var46 = (new StringBuilder(46)).append("no common type instance of base types ");
                     String mkString_sep = ", and ";
                     if (variants == null) {
                        throw null;
                     } else {
                        String mkString_end = "";
                        String mkString_start = "";
                        String var47 = IterableOnceOps.mkString$(variants, mkString_start, mkString_sep, mkString_end);
                        Object var31 = null;
                        Object var32 = null;
                        Object var29 = null;
                        throw this.typeError(var46.append(var47).append(" exists.").toString());
                     }
                  } else {
                     this.pending.update(i$1, false);
                     this.scala$reflect$internal$BaseTypeSeqs$$elems()[i$1] = var4;
                     return var4;
                  }
               } catch (Throwable var19) {
                  Throwable var45 = this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer().CyclicInheritance();
                  if (var45 == null) {
                     if (var19 != null) {
                        throw var19;
                     }
                  } else if (!var45.equals(var19)) {
                     throw var19;
                  }

                  StringBuilder var10001 = (new StringBuilder(67)).append("computing the common type instance of base types ");
                  String mkString_sepx = ", and ";
                  if (variants == null) {
                     throw null;
                  } else {
                     String mkString_endx = "";
                     String mkString_start = "";
                     String var10002 = IterableOnceOps.mkString$(variants, mkString_start, mkString_sepx, mkString_endx);
                     Object var33 = null;
                     Object var34 = null;
                     Object var30 = null;
                     throw this.typeError(var10001.append(var10002).append(" leads to a cycle.").toString());
                  }
               }
            }
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$maxDepthOfElems$1(final BaseTypeSeq $this, final IntRef d$1, final int i) {
         Depth$ var10001 = Depth$.MODULE$;
         int var10002 = d$1.elem;
         SymbolTable var10003 = $this.scala$reflect$internal$BaseTypeSeqs$BaseTypeSeq$$$outer();
         Types.Type typeDepth_tp = $this.scala$reflect$internal$BaseTypeSeqs$$elems()[i];
         if (var10003 == null) {
            throw null;
         } else {
            int var5 = Types.typeDepth$(var10003, typeDepth_tp);
            typeDepth_tp = null;
            d$1.elem = var10001.max$extension(var10002, var5);
         }
      }

      public BaseTypeSeq(final List parents, final Types.Type[] elems) {
         this.scala$reflect$internal$BaseTypeSeqs$$parents = parents;
         this.scala$reflect$internal$BaseTypeSeqs$$elems = elems;
         if (BaseTypeSeqs.this == null) {
            throw null;
         } else {
            this.$outer = BaseTypeSeqs.this;
            super();
            MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var31 = MutableSettings$.MODULE$;
            MutableSettings SettingsOps_settings = BaseTypeSeqs.this.settings();
            Object var29 = null;
            boolean var33 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(SettingsOps_settings.YstatisticsEnabled().value());
            Object var19 = null;
            if (var33) {
               Statistics var34 = BaseTypeSeqs.this.statistics();
               Statistics.Counter incCounter_c = ((BaseTypeSeqsStats)BaseTypeSeqs.this.statistics()).baseTypeSeqCount();
               if (var34 == null) {
                  throw null;
               }

               Statistics incCounter_this = var34;
               MutableSettings.SettingsOps$ var35 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var36 = MutableSettings$.MODULE$;
               MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
               Object var23 = null;
               boolean var38 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_SettingsOps_settings.YstatisticsEnabled().value());
               Object var22 = null;
               if (var38 && incCounter_c != null) {
                  incCounter_c.value_$eq(incCounter_c.value() + 1);
               }

               Object var20 = null;
               Object var21 = null;
            }

            MutableSettings.SettingsOps$ var39 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var40 = MutableSettings$.MODULE$;
            MutableSettings SettingsOps_settings = BaseTypeSeqs.this.settings();
            Object var30 = null;
            boolean var42 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(SettingsOps_settings.YstatisticsEnabled().value());
            Object var24 = null;
            if (var42) {
               Statistics var43 = BaseTypeSeqs.this.statistics();
               Statistics.Counter var10001 = ((BaseTypeSeqsStats)BaseTypeSeqs.this.statistics()).baseTypeSeqLenTotal();
               int incCounter_delta = elems.length;
               Statistics.Counter incCounter_cx = var10001;
               if (var43 == null) {
                  throw null;
               }

               Statistics incCounter_this = var43;
               MutableSettings.SettingsOps$ var44 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var45 = MutableSettings$.MODULE$;
               MutableSettings incCounter_enabled_SettingsOps_settings = incCounter_this.scala$reflect$internal$util$Statistics$$settings;
               Object var28 = null;
               boolean var47 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(incCounter_enabled_SettingsOps_settings.YstatisticsEnabled().value());
               Object var27 = null;
               if (var47 && incCounter_cx != null) {
                  incCounter_cx.value_$eq(incCounter_cx.value() + incCounter_delta);
               }

               Object var25 = null;
               Object var26 = null;
            }

            int[] tmp = new int[elems.length];

            for(int i = 0; i < elems.length; ++i) {
               tmp[i] = elems[i].typeSymbol().id();
            }

            this.typeSymbols = tmp;
            this.pending = new BitSet(this.length());
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class MappedBaseTypeSeq extends BaseTypeSeq {
      private final BaseTypeSeq orig;
      private final Function1 f;

      public Types.Type apply(final int i) {
         return (Types.Type)this.f.apply(this.orig.apply(i));
      }

      public Types.Type rawElem(final int i) {
         return (Types.Type)this.f.apply(this.orig.rawElem(i));
      }

      public List toList() {
         return this.orig.toList().map(this.f);
      }

      public BaseTypeSeq copy(final Types.Type head, final int offset) {
         return this.orig.map(this.f).copy(head, offset);
      }

      public BaseTypeSeq map(final Function1 g) {
         return this.lateMap(g);
      }

      public BaseTypeSeq lateMap(final Function1 g) {
         return this.orig.lateMap((x) -> (Types.Type)g.apply(this.f.apply(x)));
      }

      public boolean exists(final Function1 p) {
         Object[] refArrayOps_xs = this.scala$reflect$internal$BaseTypeSeqs$$elems();
         Object var6 = null;
         Object exists$extension_$this = refArrayOps_xs;
         int exists$extension_indexWhere$extension_i = 0;

         int var7;
         while(true) {
            if (exists$extension_indexWhere$extension_i >= ((Object[])exists$extension_$this).length) {
               var7 = -1;
               break;
            }

            Object var5 = ((Object[])exists$extension_$this)[exists$extension_indexWhere$extension_i];
            if (BoxesRunTime.unboxToBoolean(p.apply(this.f.apply(var5)))) {
               var7 = exists$extension_indexWhere$extension_i;
               break;
            }

            ++exists$extension_indexWhere$extension_i;
         }

         return var7 >= 0;
      }

      public int maxDepthOfElems() {
         Predef var10000 = scala.Predef..MODULE$;
         Object map$extension_$this = this.scala$reflect$internal$BaseTypeSeqs$$elems();
         int map$extension_len = ((Object[])map$extension_$this).length;
         Object map$extension_ys = new Depth[map$extension_len];
         if (map$extension_len > 0) {
            for(int map$extension_i = 0; map$extension_i < map$extension_len; ++map$extension_i) {
               Object var5 = ((Object[])map$extension_$this)[map$extension_i];
               Object array_update_value = $anonfun$maxDepthOfElems$2$adapted(this, (Types.Type)var5);
               ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
               array_update_value = null;
            }
         }

         map$extension_$this = null;
         Object var8 = null;
         return ((Depth)var10000.genericWrapArray(map$extension_ys).max(LowPriorityOrderingImplicits.ordered$(scala.math.Ordering..MODULE$, scala..less.colon.less..MODULE$.refl()))).depth();
      }

      public String toString() {
         ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(this.scala$reflect$internal$BaseTypeSeqs$$elems());
         String mkString_end = ")";
         String mkString_sep = ",";
         String mkString_start = "MBTS(";
         if (var10000 == null) {
            throw null;
         } else {
            return IterableOnceOps.mkString$(var10000, mkString_start, mkString_sep, mkString_end);
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$exists$1(final MappedBaseTypeSeq $this, final Function1 p$1, final Types.Type x) {
         return BoxesRunTime.unboxToBoolean(p$1.apply($this.f.apply(x)));
      }

      // $FF: synthetic method
      public static final int $anonfun$maxDepthOfElems$2(final MappedBaseTypeSeq $this, final Types.Type x) {
         SymbolTable var10000 = $this.scala$reflect$internal$BaseTypeSeqs$MappedBaseTypeSeq$$$outer();
         Types.Type typeDepth_tp = (Types.Type)$this.f.apply(x);
         if (var10000 == null) {
            throw null;
         } else {
            return Types.typeDepth$(var10000, typeDepth_tp);
         }
      }

      public MappedBaseTypeSeq(final BaseTypeSeq orig, final Function1 f) {
         this.orig = orig;
         this.f = f;
         List var10002 = orig.scala$reflect$internal$BaseTypeSeqs$$parents();
         if (var10002 == null) {
            throw null;
         } else {
            List map_this = var10002;
            Object var14;
            if (map_this == .MODULE$) {
               var14 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(f.apply(map_this.head()), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(f.apply(map_rest.head()), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var14 = map_h;
            }

            Object var9 = null;
            Object var10 = null;
            Object var11 = null;
            Object var12 = null;
            Object var13 = null;
            super((List)var14, orig.scala$reflect$internal$BaseTypeSeqs$$elems());
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$exists$1$adapted(final MappedBaseTypeSeq $this, final Function1 p$1, final Types.Type x) {
         return BoxesRunTime.boxToBoolean($anonfun$exists$1($this, p$1, x));
      }

      // $FF: synthetic method
      public static final Object $anonfun$maxDepthOfElems$2$adapted(final MappedBaseTypeSeq $this, final Types.Type x) {
         return new Depth($anonfun$maxDepthOfElems$2($this, x));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
