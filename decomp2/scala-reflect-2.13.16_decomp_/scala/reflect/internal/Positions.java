package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Some;
import scala.collection.SeqOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuffer;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.Position$;
import scala.reflect.internal.util.ReusableInstance;
import scala.reflect.internal.util.ReusableInstance$;
import scala.reflect.internal.util.SourceFile;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tEe!\u0003 @!\u0003\r\tA\u0012BE\u0011\u0015\u0001\u0006\u0001\"\u0001R\u000b\u0011)\u0006\u0001\u0001,\t\u000fm\u0003!\u0019!C\u00019\"9!\u000e\u0001b\u0001\n\u0007Y\u0007\"\u0002:\u0001\t\u0003\u0019\b\"B<\u0001\t\u0003A\bBB<\u0001\t\u0013\t\u0019B\u0002\u0004\u0002\u001e\u00011\u0011q\u0004\u0005\b\u0003OAA\u0011AA\u0015\u0011-\ti\u0003\u0003a\u0001\u0002\u0003\u0006K!a\f\t\u0017\u0005U\u0002\u00021A\u0001B\u0003&\u0011q\u0006\u0005\u0007\u0003oAA\u0011A)\t\u000f\u0005e\u0002\u0002\"\u0001\u0002<!9\u0011\u0011\t\u0005\u0005B\u0005\r\u0003BB<\u0001\t\u0003\tI\u0005C\u0004\u0002N\u0001!\t!a\u0014\t\u000f\u00055\u0003\u0001\"\u0001\u0002Z!9\u0011\u0011\r\u0001\u0005\u0002\u0005\rdaBA>\u0001\u0005\u0005\u0011Q\u0010\u0005\b\u0003O\u0019B\u0011AAD\u0011\u001d\tYi\u0005C!\u0003\u001bCq!!'\u0014\t\u0003\nY\nC\u0004\u0002 N1\t!!)\t\u000f\u0005\u00053\u0003\"\u0001\u0002(\"I\u00111\u0016\u0001C\u0002\u0013%\u0011Q\u0016\u0005\b\u0003k\u0003A\u0011AA\\\u0011\u001d\tY\f\u0001C\u0005\u0003{C\u0011\"a2\u0001\u0005\u0004%I!!3\u0007\r\u0005M\u0007ABAk\u0011\u001d\t9#\bC\u0001\u0003/D\u0001\"!7\u001eA\u0003%\u00111\u0006\u0005\u000b\u0003\u0003l\u0002\u0019!A!B\u0013\u0001\bbBAn;\u0011\u0005\u0011Q\u001c\u0005\b\u0003\u0003jB\u0011AAr\r\u0019\t9\u000f\u0001\u0001\u0002j\"Q\u0011\u0011_\u0012\u0003\u0002\u0003\u0006I!a=\t\u000f\u0005\u001d2\u0005\"\u0001\u0003\u0004\u00191!\u0011\u0002\u0001\u0001\u0005\u0017A\u0011\"!1'\u0005\u0003\u0005\u000b\u0011\u00029\t\u000f\u0005\u001db\u0005\"\u0001\u0003\u000e!Y!1\u0003\u0014A\u0002\u0003\u0007I\u0011\u0001B\u000b\u0011-\u00119B\na\u0001\u0002\u0004%\tA!\u0007\t\u0017\t}a\u00051A\u0001B\u0003&\u0011\u0011\u0002\u0005\b\u0005C1C\u0011\u0001B\u0012\u0011\u001d\u0011IC\nC\t\u0005WAq!!''\t\u0003\u0012yC\u0002\u0004\u00034\u0001\u0001!Q\u0007\u0005\n\u0003\u0003|#\u0011!Q\u0001\nADq!a\n0\t\u0003\u00119\u0004C\u0004\u0003*=\"\tF!\u0010\u0007\u0013\t\u0005\u0003\u0001%A\u0012\u0002\t\r\u0003\"CAag\u0001\u0007i\u0011\u0001B&\u0011%\u0011ie\ra\u0001\u000e\u0003\u0011y\u0005\u0003\u0006\u0003T\u0001A)\u0019)C\t\u0005+2aA!\u0017\u0001\u0011\tm\u0003bBA\u0014o\u0011\u0005!Q\f\u0005\f\u0003\u0003<\u0004\u0019!a\u0001\n\u0003\u0011Y\u0005C\u0006\u0003N]\u0002\r\u00111A\u0005\u0002\t\u0005\u0004B\u0003B3o\u0001\u0007\t\u0011)Q\u0005a\"9\u0011\u0011T\u001c\u0005B\t\u001d\u0004b\u0002B6\u0001\u0011\u0005!Q\u000e\u0002\n!>\u001c\u0018\u000e^5p]NT!\u0001Q!\u0002\u0011%tG/\u001a:oC2T!AQ\"\u0002\u000fI,g\r\\3di*\tA)A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u000195\n\u0005\u0002I\u00136\t1)\u0003\u0002K\u0007\n1\u0011I\\=SK\u001a\u0004\"\u0001T(\u000e\u00035S!AT!\u0002\u0007\u0005\u0004\u0018.\u0003\u0002?\u001b\u00061A%\u001b8ji\u0012\"\u0012A\u0015\t\u0003\u0011NK!\u0001V\"\u0003\tUs\u0017\u000e\u001e\u0002\t!>\u001c\u0018\u000e^5p]B\u0011qKW\u0007\u00021*\u0011\u0011lP\u0001\u0005kRLG.\u0003\u0002V1\u0006Qaj\u001c)pg&$\u0018n\u001c8\u0016\u0003us!AX5\u000f\u0005}CgB\u00011h\u001d\t\tgM\u0004\u0002cK6\t1M\u0003\u0002e\u000b\u00061AH]8pizJ\u0011\u0001R\u0005\u0003\u0005\u000eK!\u0001Q!\n\u0005e{\u0014BA.Y\u0003-\u0001vn]5uS>tG+Y4\u0016\u00031\u00042!\u001c8q\u001b\u0005\t\u0015BA8B\u0005!\u0019E.Y:t)\u0006<\u0007CA9\u0003\u001b\u0005\u0001\u0011AE;tK>3gm]3u!>\u001c\u0018\u000e^5p]N,\u0012\u0001\u001e\t\u0003\u0011VL!A^\"\u0003\u000f\t{w\u000e\\3b]\u0006YqO]1qa&tw\rU8t)\r\u0001\u0018p\u001f\u0005\u0006u\u001a\u0001\r\u0001]\u0001\bI\u00164\u0017-\u001e7u\u0011\u0015ah\u00011\u0001~\u0003\u0015!(/Z3t!\u0015q\u00181AA\u0005\u001d\tAu0C\u0002\u0002\u0002\r\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0006\u0005\u001d!\u0001\u0002'jgRT1!!\u0001D!\r\t\u00181B\u0005\u0005\u0003\u001b\tyA\u0001\u0003Ue\u0016,\u0017bAA\t\u007f\t)AK]3fgR9\u0001/!\u0006\u0002\u0018\u0005e\u0001\"\u0002>\b\u0001\u0004\u0001\b\"\u0002?\b\u0001\u0004i\bBBA\u000e\u000f\u0001\u0007A/A\u0003g_\u000e,8O\u0001\fXe\u0006\u0004\b/\u001b8h!>\u001c\u0018iY2v[Vd\u0017\r^8s'\u0011Aq)!\t\u0011\r!\u000b\u0019#!\u0003u\u0013\r\t)c\u0011\u0002\n\rVt7\r^5p]F\na\u0001P5oSRtDCAA\u0016!\t\t\b\"A\u0002nS:\u00042\u0001SA\u0019\u0013\r\t\u0019d\u0011\u0002\u0004\u0013:$\u0018aA7bq\u0006)!/Z:fi\u00061!/Z:vYR$R\u0001]A\u001f\u0003\u007fAQA_\u0007A\u0002ADa!a\u0007\u000e\u0001\u0004!\u0018!B1qa2LHc\u0001;\u0002F!9\u0011q\t\bA\u0002\u0005%\u0011A\u0001<2)\r\u0001\u00181\n\u0005\u0006y>\u0001\r!`\u0001\u0015K:\u001cXO]3O_:|e/\u001a:mCB\u0004\u0018N\\4\u0015\u000bI\u000b\t&!\u0016\t\u000f\u0005M\u0003\u00031\u0001\u0002\n\u0005!AO]3f\u0011\u0019\t9\u0006\u0005a\u0001{\u00061q\u000e\u001e5feN$rAUA.\u0003;\ny\u0006C\u0004\u0002TE\u0001\r!!\u0003\t\r\u0005]\u0013\u00031\u0001~\u0011\u0019\tY\"\u0005a\u0001i\u0006A!/\u00198hKB{7\u000fF\u0005q\u0003K\ny'a\u001d\u0002x!9\u0011q\r\nA\u0002\u0005%\u0014AB:pkJ\u001cW\rE\u0002X\u0003WJ1!!\u001cY\u0005)\u0019v.\u001e:dK\u001aKG.\u001a\u0005\b\u0003c\u0012\u0002\u0019AA\u0018\u0003\u0015\u0019H/\u0019:u\u0011\u001d\t)H\u0005a\u0001\u0003_\tQ\u0001]8j]RDq!!\u001f\u0013\u0001\u0004\ty#A\u0002f]\u0012\u0014ad\u00115jY\u0012\u001cv\u000e\\5e\t\u0016\u001c8-\u001a8eC:$8oQ8mY\u0016\u001cGo\u001c:\u0014\u0007M\ty\bE\u0002r\u0003\u0003KA!a!\u0002\u0006\nIAK]1wKJ\u001cXM]\u0005\u0004\u0003#iECAAE!\t\t8#A\tue\u00064XM]:f\u001b>$\u0017NZ5feN$2AUAH\u0011\u001d\t\t*\u0006a\u0001\u0003'\u000bA!\\8egB\u0019\u0011/!&\n\t\u0005]\u0015q\u0002\u0002\n\u001b>$\u0017NZ5feN\f\u0001\u0002\u001e:bm\u0016\u00148/\u001a\u000b\u0004%\u0006u\u0005bBA*-\u0001\u0007\u0011\u0011B\u0001\u0013iJ\fg/\u001a:tKN{G.\u001b3DQ&dG\rF\u0002S\u0003GCq!!*\u0018\u0001\u0004\tI!A\u0001u)\r\u0011\u0016\u0011\u0016\u0005\b\u0003KC\u0002\u0019AA\u0005\u0003A\u0001xn]*uCJ$xJ\u001d3fe&tw-\u0006\u0002\u00020B)a0!-\u0002\n%!\u00111WA\u0004\u0005!y%\u000fZ3sS:<\u0017!\u0005<bY&$\u0017\r^3Q_NLG/[8ogR\u0019!+!/\t\u000f\u0005M#\u00041\u0001\u0002\n\u0005q1/\u001a;DQ&dGM]3o!>\u001cH#\u0002*\u0002@\u0006\r\u0007BBAa7\u0001\u0007\u0001/A\u0002q_NDq!!2\u001c\u0001\u0004\tI!\u0001\u0004qCJ,g\u000e^\u0001\u001ag\u0016$8\t[5mIJ,g\u000eU8t\u0003\u000e\u001cW/\\;mCR|'/\u0006\u0002\u0002LB)q+!4\u0002R&\u0019\u0011q\u001a-\u0003!I+Wo]1cY\u0016Len\u001d;b]\u000e,\u0007CA9\u001e\u0005e\u0019V\r^\"iS2$'/\u001a8Q_N\f5mY;nk2\fGo\u001c:\u0014\tu9\u0015\u0011\u0005\u000b\u0003\u0003#\fac\u001e:baBLgn\u001a)pg\u0006\u001b7-^7vY\u0006$xN]\u0001\u0004g\u0016$H#\u0002*\u0002`\u0006\u0005\bBBAaC\u0001\u0007\u0001\u000fC\u0004\u0002F\u0006\u0002\r!!\u0003\u0015\u0007Q\f)\u000fC\u0004\u0002T\t\u0002\r!!\u0003\u0003#Y\u000bG.\u001b3bi\u0016,\u0005pY3qi&|gnE\u0002$\u0003W\u00042A`Aw\u0013\u0011\ty/a\u0002\u0003\u0013\u0015C8-\u001a9uS>t\u0017aA7tOB!\u0011Q_A\u007f\u001d\u0011\t90!?\u0011\u0005\t\u001c\u0015bAA~\u0007\u00061\u0001K]3eK\u001aLA!a@\u0003\u0002\t11\u000b\u001e:j]\u001eT1!a?D)\u0011\u0011)Aa\u0002\u0011\u0005E\u001c\u0003bBAyK\u0001\u0007\u00111\u001f\u0002\b\u0019>\u001c\u0017\r^8s'\r1\u0013q\u0010\u000b\u0005\u0005\u001f\u0011\t\u0002\u0005\u0002rM!1\u0011\u0011\u0019\u0015A\u0002A\fA\u0001\\1tiV\u0011\u0011\u0011B\u0001\tY\u0006\u001cHo\u0018\u0013fcR\u0019!Ka\u0007\t\u0013\tu!&!AA\u0002\u0005%\u0011a\u0001=%c\u0005)A.Y:uA\u0005AAn\\2bi\u0016Le\u000e\u0006\u0003\u0002\n\t\u0015\u0002b\u0002B\u0014Y\u0001\u0007\u0011\u0011B\u0001\u0005e>|G/\u0001\u0006jg\u0016c\u0017nZ5cY\u0016$2\u0001\u001eB\u0017\u0011\u001d\t)+\fa\u0001\u0003\u0013!2A\u0015B\u0019\u0011\u001d\t)K\fa\u0001\u0003\u0013\u0011A\u0002V=qK\u0012dunY1u_J\u001c2a\fB\b)\u0011\u0011IDa\u000f\u0011\u0005E|\u0003BBAac\u0001\u0007\u0001\u000fF\u0002u\u0005\u007fAq!!*3\u0001\u0004\tIAA\u0006Q_N\f5o]5h]\u0016\u00148cA\u001a\u0003FA\u0019\u0011Oa\u0012\n\t\t%\u0013q\u0002\u0002\u0012\u0013:$XM\u001d8bYR\u0013\u0018M^3sg\u0016\u0014X#\u00019\u0002\u000fA|7o\u0018\u0013fcR\u0019!K!\u0015\t\u0011\tuQ'!AA\u0002A\f1\u0002]8t\u0003N\u001c\u0018n\u001a8feV\u0011!q\u000b\t\u0003cN\u0012!\u0003R3gCVdG\u000fU8t\u0003N\u001c\u0018n\u001a8feN)qG!\u0012\u0003XQ\u0011!q\f\t\u0003c^\"2A\u0015B2\u0011!\u0011iBOA\u0001\u0002\u0004\u0001\u0018\u0001\u00029pg\u0002\"2A\u0015B5\u0011\u001d\t)\u000b\u0010a\u0001\u0003\u0013\tQ!\u0019;Q_N,BAa\u001c\u0003|Q!!\u0011\u000fBD)\u0011\u0011\u0019Ha\u001e\u000f\t\tU$q\u000f\u0007\u0001\u0011\u001d\t\u0019&\u0010a\u0001\u0005s\u0002BA!\u001e\u0003|\u00119!QP\u001fC\u0002\t}$!\u0001+\u0012\t\t\u0005\u0015\u0011\u0002\t\u0004\u0011\n\r\u0015b\u0001BC\u0007\n9aj\u001c;iS:<\u0007BBAa{\u0001\u0007\u0001\u000f\u0005\u0003\u0003\f\n5U\"A \n\u0007\t=uHA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007"
)
public interface Positions extends scala.reflect.api.Positions {
   void scala$reflect$internal$Positions$_setter_$NoPosition_$eq(final NoPosition$ x$1);

   void scala$reflect$internal$Positions$_setter_$PositionTag_$eq(final ClassTag x$1);

   void scala$reflect$internal$Positions$_setter_$scala$reflect$internal$Positions$$posStartOrdering_$eq(final Ordering x$1);

   void scala$reflect$internal$Positions$_setter_$scala$reflect$internal$Positions$$setChildrenPosAccumulator_$eq(final ReusableInstance x$1);

   NoPosition$ NoPosition();

   ClassTag PositionTag();

   // $FF: synthetic method
   static boolean useOffsetPositions$(final Positions $this) {
      return $this.useOffsetPositions();
   }

   default boolean useOffsetPositions() {
      return true;
   }

   // $FF: synthetic method
   static Position wrappingPos$(final Positions $this, final Position default, final List trees) {
      return $this.wrappingPos(default, trees);
   }

   default Position wrappingPos(final Position default, final List trees) {
      return this.wrappingPos(default, trees, true);
   }

   private Position wrappingPos(final Position default, final List trees, final boolean focus) {
      if (this.useOffsetPositions()) {
         return default;
      } else {
         WrappingPosAccumulator accum = (SymbolTable)this.new WrappingPosAccumulator();
         return this.loop$1(trees, accum, default, focus);
      }
   }

   // $FF: synthetic method
   static Position wrappingPos$(final Positions $this, final List trees) {
      return $this.wrappingPos(trees);
   }

   default Position wrappingPos(final List trees) {
      Position headpos = ((Trees.Tree)trees.head()).pos();
      return !this.useOffsetPositions() && headpos.isDefined() ? this.wrappingPos(headpos, trees) : headpos;
   }

   // $FF: synthetic method
   static void ensureNonOverlapping$(final Positions $this, final Trees.Tree tree, final List others) {
      $this.ensureNonOverlapping(tree, others);
   }

   default void ensureNonOverlapping(final Trees.Tree tree, final List others) {
      this.ensureNonOverlapping(tree, others, true);
   }

   // $FF: synthetic method
   static void ensureNonOverlapping$(final Positions $this, final Trees.Tree tree, final List others, final boolean focus) {
      $this.ensureNonOverlapping(tree, others, focus);
   }

   default void ensureNonOverlapping(final Trees.Tree tree, final List others, final boolean focus) {
      if (!this.useOffsetPositions()) {
         Position treePos = tree.pos();
         if (isOverlapping$1(treePos, others)) {
            List children = tree.children();
            if (children == null) {
               throw null;
            } else {
               for(List foreach_these = children; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                  Trees.Tree var8 = (Trees.Tree)foreach_these.head();
                  this.ensureNonOverlapping(var8, others, focus);
               }

               Object var9 = null;
               if (treePos.isOpaqueRange()) {
                  Position wpos = this.wrappingPos(treePos, children, focus);
                  tree.setPos(isOverlapping$1(wpos, others) ? treePos.makeTransparent() : wpos);
               }
            }
         }
      }
   }

   // $FF: synthetic method
   static Position rangePos$(final Positions $this, final SourceFile source, final int start, final int point, final int end) {
      return $this.rangePos(source, start, point, end);
   }

   default Position rangePos(final SourceFile source, final int start, final int point, final int end) {
      return this.useOffsetPositions() ? Position$.MODULE$.offset(source, point) : Position$.MODULE$.range(source, start, point, end);
   }

   Ordering scala$reflect$internal$Positions$$posStartOrdering();

   // $FF: synthetic method
   static void validatePositions$(final Positions $this, final Trees.Tree tree) {
      $this.validatePositions(tree);
   }

   default void validatePositions(final Trees.Tree tree) {
      if (!this.useOffsetPositions()) {
         LazyRef worker$module = new LazyRef();
         this.worker$2(worker$module, tree).loop(tree, tree);
      }
   }

   private void setChildrenPos(final Position pos, final Trees.Tree parent) {
      try {
         ReusableInstance var10000 = this.scala$reflect$internal$Positions$$setChildrenPosAccumulator();
         if (var10000 == null) {
            throw null;
         } else {
            ReusableInstance using_this = var10000;
            if (using_this.scala$reflect$internal$util$ReusableInstance$$cache == null) {
               ((SetChildrenPosAccumulator)using_this.scala$reflect$internal$util$ReusableInstance$$make.apply()).set(pos, parent);
            } else {
               int var12 = using_this.scala$reflect$internal$util$ReusableInstance$$taken;
               ArrayBuffer var10001 = using_this.scala$reflect$internal$util$ReusableInstance$$cache;
               if (var10001 == null) {
                  throw null;
               }

               if (var12 == SeqOps.size$(var10001)) {
                  ArrayBuffer var13 = using_this.scala$reflect$internal$util$ReusableInstance$$cache;
                  Object using_$plus$eq_elem = using_this.scala$reflect$internal$util$ReusableInstance$$make.apply();
                  if (var13 == null) {
                     throw null;
                  }

                  var13.addOne(using_$plus$eq_elem);
                  using_$plus$eq_elem = null;
               }

               ++using_this.scala$reflect$internal$util$ReusableInstance$$taken;

               try {
                  ((SetChildrenPosAccumulator)using_this.scala$reflect$internal$util$ReusableInstance$$cache.apply(using_this.scala$reflect$internal$util$ReusableInstance$$taken - 1)).set(pos, parent);
               } finally {
                  --using_this.scala$reflect$internal$util$ReusableInstance$$taken;
               }
            }

         }
      } catch (Exception var10) {
         ((Reporting)this).inform((new StringBuilder(33)).append("error while set children pos ").append(pos).append(" of ").append(parent.children()).toString());
         throw var10;
      }
   }

   ReusableInstance scala$reflect$internal$Positions$$setChildrenPosAccumulator();

   // $FF: synthetic method
   static PosAssigner posAssigner$(final Positions $this) {
      return $this.posAssigner();
   }

   default PosAssigner posAssigner() {
      return (SymbolTable)this.new DefaultPosAssigner();
   }

   // $FF: synthetic method
   static Trees.Tree atPos$(final Positions $this, final Position pos, final Trees.Tree tree) {
      return $this.atPos(pos, tree);
   }

   default Trees.Tree atPos(final Position pos, final Trees.Tree tree) {
      if (!this.useOffsetPositions() && pos.isOpaqueRange()) {
         if (!tree.isEmpty() && tree.canHaveAttrs()) {
            Position var10000 = tree.pos();
            NoPosition$ var3 = this.NoPosition();
            if (var10000 == null) {
               if (var3 != null) {
                  return tree;
               }
            } else if (!var10000.equals(var3)) {
               return tree;
            }

            tree.setPos(pos);
            Trees.Tree var4 = tree.onlyChild();
            if (((Trees)this).EmptyTree().equals(var4)) {
               this.setChildrenPos(pos, tree);
            } else {
               this.atPos(pos, var4);
            }
         }
      } else {
         this.posAssigner().pos_$eq(pos);
         ((Trees.InternalTraverser)this.posAssigner()).traverse(tree);
      }

      return tree;
   }

   private Position loop$1(final List trees, final WrappingPosAccumulator accum$1, final Position default$1, final boolean focus$1) {
      while(trees instanceof .colon.colon) {
         .colon.colon var5 = (.colon.colon)trees;
         Trees.Tree head = (Trees.Tree)var5.head();
         List rest = var5.next$access$1();
         accum$1.apply(head);
         if (head instanceof Trees.MemberDef) {
            Trees.MemberDef var8 = (Trees.MemberDef)head;
            SymbolTable var10 = (SymbolTable)this;
            List var9 = var8.mods().annotations();
            trees = rest.$colon$colon$colon(var9);
            this = var10;
         } else {
            SymbolTable var10000 = (SymbolTable)this;
            trees = rest;
            this = var10000;
         }
      }

      return accum$1.result(default$1, focus$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$ensureNonOverlapping$1(final Position pos$1, final Trees.Tree x$3) {
      return pos$1.overlaps(x$3.pos());
   }

   private static boolean isOverlapping$1(final Position pos, final List others$1) {
      if (pos.isRange()) {
         if (others$1 == null) {
            throw null;
         }

         List exists_these = others$1;

         boolean var10000;
         while(true) {
            if (exists_these.isEmpty()) {
               var10000 = false;
               break;
            }

            Trees.Tree var3 = (Trees.Tree)exists_these.head();
            if ($anonfun$ensureNonOverlapping$1(pos, var3)) {
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

   // $FF: synthetic method
   static void $anonfun$ensureNonOverlapping$2(final Positions $this, final List others$1, final boolean focus$2, final Trees.Tree x$4) {
      $this.ensureNonOverlapping(x$4, others$1, focus$2);
   }

   // $FF: synthetic method
   static void scala$reflect$internal$Positions$$reportTree$1$(final Positions $this, final String prefix, final Trees.Tree tree) {
      $this.scala$reflect$internal$Positions$$reportTree$1(prefix, tree);
   }

   default void scala$reflect$internal$Positions$$reportTree$1(final String prefix, final Trees.Tree tree) {
      Object source = tree.pos().isDefined() ? tree.pos().source() : "";
      ((Reporting)this).inform((new StringBuilder(24)).append("== ").append(prefix).append(" tree [").append(tree.id()).append("] of type ").append(tree.productPrefix()).append(" at ").append(tree.pos().show()).append(source).toString());
      ((Reporting)this).inform("");
      ((Reporting)this).inform(((Trees)this).treeStatus(tree, ((Trees)this).treeStatus$default$2()));
      ((Reporting)this).inform("");
   }

   // $FF: synthetic method
   static void $anonfun$validatePositions$1(final Positions $this, final Trees.Tree topTree$1, final Trees.Tree t) {
      ((Reporting)$this).inform((new StringBuilder(2)).append("  ").append(((Trees)$this).treeStatus(t, topTree$1)).toString());
   }

   // $FF: synthetic method
   static void scala$reflect$internal$Positions$$positionError$1$(final Positions $this, final Trees.Tree topTree, final String msg, final Function0 body) {
      $this.scala$reflect$internal$Positions$$positionError$1(topTree, msg, body);
   }

   default void scala$reflect$internal$Positions$$positionError$1(final Trees.Tree topTree, final String msg, final Function0 body) {
      ((Reporting)this).inform((new StringBuilder(23)).append("======= Position error\n").append(msg).toString());
      body.apply$mcV$sp();
      ((Reporting)this).inform((new StringBuilder(19)).append("\nWhile validating #").append(topTree.id()).toString());
      ((Reporting)this).inform(((Trees)this).treeStatus(topTree, ((Trees)this).treeStatus$default$2()));
      ((Reporting)this).inform("\nChildren:");
      List var10000 = topTree.children();
      if (var10000 == null) {
         throw null;
      } else {
         for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            Trees.Tree var5 = (Trees.Tree)foreach_these.head();
            $anonfun$validatePositions$1(this, topTree, var5);
         }

         Object var6 = null;
         ((Reporting)this).inform("=======");
         throw (SymbolTable)this.new ValidateException(msg);
      }
   }

   // $FF: synthetic method
   private worker$1$ worker$lzycompute$1(final LazyRef worker$module$1, final Trees.Tree tree$1) {
      synchronized(worker$module$1){}

      worker$1$ var3;
      try {
         class worker$1$ {
            private volatile Positions$worker$1$solidChildrenCollector$ solidChildrenCollector$module;
            private final boolean trace;
            private final Trees.Tree topTree;
            // $FF: synthetic field
            private final SymbolTable $outer;

            public Positions$worker$1$solidChildrenCollector$ solidChildrenCollector() {
               if (this.solidChildrenCollector$module == null) {
                  this.solidChildrenCollector$lzycompute$1();
               }

               return this.solidChildrenCollector$module;
            }

            public boolean trace() {
               return this.trace;
            }

            public Trees.Tree topTree() {
               return this.topTree;
            }

            public void loop(final Trees.Tree tree, final Trees.Tree encltree) {
               while(true) {
                  if (!tree.isEmpty() && tree.canHaveAttrs()) {
                     Position treePos = tree.pos();
                     if (this.trace()) {
                        this.$outer.inform(scala.collection.StringOps..MODULE$.format$extension("[%10s] %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"validate", this.$outer.treeStatus(tree, encltree)})));
                     }

                     if (!treePos.isDefined()) {
                        SymbolTable var44 = this.$outer;
                        Trees.Tree var48 = this.topTree();
                        String scala$reflect$internal$Positions$$positionError$1_msg = (new StringBuilder(19)).append("Unpositioned tree #").append(tree.id()).toString();
                        Trees.Tree scala$reflect$internal$Positions$$positionError$1_topTree = var48;
                        if (var44 == null) {
                           throw null;
                        }

                        Positions scala$reflect$internal$Positions$$positionError$1_this = var44;
                        scala$reflect$internal$Positions$$positionError$1_this.inform((new StringBuilder(23)).append("======= Position error\n").append(scala$reflect$internal$Positions$$positionError$1_msg).toString());
                        $anonfun$loop$1(this, tree, encltree);
                        scala$reflect$internal$Positions$$positionError$1_this.inform((new StringBuilder(19)).append("\nWhile validating #").append(scala$reflect$internal$Positions$$positionError$1_topTree.id()).toString());
                        scala$reflect$internal$Positions$$positionError$1_this.inform(scala$reflect$internal$Positions$$positionError$1_this.treeStatus(scala$reflect$internal$Positions$$positionError$1_topTree, scala$reflect$internal$Positions$$positionError$1_this.treeStatus$default$2()));
                        scala$reflect$internal$Positions$$positionError$1_this.inform("\nChildren:");
                        List var45 = scala$reflect$internal$Positions$$positionError$1_topTree.children();
                        if (var45 == null) {
                           throw null;
                        }

                        for(List scala$reflect$internal$Positions$$positionError$1_foreach_these = var45; !scala$reflect$internal$Positions$$positionError$1_foreach_these.isEmpty(); scala$reflect$internal$Positions$$positionError$1_foreach_these = (List)scala$reflect$internal$Positions$$positionError$1_foreach_these.tail()) {
                           Trees.Tree var30 = (Trees.Tree)scala$reflect$internal$Positions$$positionError$1_foreach_these.head();
                           Positions.$anonfun$validatePositions$1(scala$reflect$internal$Positions$$positionError$1_this, scala$reflect$internal$Positions$$positionError$1_topTree, var30);
                        }

                        Object var35 = null;
                        scala$reflect$internal$Positions$$positionError$1_this.inform("=======");
                        throw scala$reflect$internal$Positions$$positionError$1_this.new ValidateException(scala$reflect$internal$Positions$$positionError$1_msg);
                     }

                     this.solidChildrenCollector().apply(tree);
                     int numChildren = this.solidChildrenCollector().collectedSize();
                     if (treePos.isRange()) {
                        Position enclPos = encltree.pos();
                        if (!enclPos.isRange()) {
                           SymbolTable var42 = this.$outer;
                           Trees.Tree var47 = this.topTree();
                           String scala$reflect$internal$Positions$$positionError$1_msgx = (new StringBuilder(47)).append("Synthetic tree [").append(encltree.id()).append("] contains nonsynthetic tree [").append(tree.id()).append("]").toString();
                           Trees.Tree scala$reflect$internal$Positions$$positionError$1_topTreex = var47;
                           if (var42 == null) {
                              throw null;
                           }

                           Positions scala$reflect$internal$Positions$$positionError$1_this = var42;
                           scala$reflect$internal$Positions$$positionError$1_this.inform((new StringBuilder(23)).append("======= Position error\n").append(scala$reflect$internal$Positions$$positionError$1_msgx).toString());
                           $anonfun$loop$3(this, encltree, tree);
                           scala$reflect$internal$Positions$$positionError$1_this.inform((new StringBuilder(19)).append("\nWhile validating #").append(scala$reflect$internal$Positions$$positionError$1_topTreex.id()).toString());
                           scala$reflect$internal$Positions$$positionError$1_this.inform(scala$reflect$internal$Positions$$positionError$1_this.treeStatus(scala$reflect$internal$Positions$$positionError$1_topTreex, scala$reflect$internal$Positions$$positionError$1_this.treeStatus$default$2()));
                           scala$reflect$internal$Positions$$positionError$1_this.inform("\nChildren:");
                           List var43 = scala$reflect$internal$Positions$$positionError$1_topTreex.children();
                           if (var43 == null) {
                              throw null;
                           }

                           for(List scala$reflect$internal$Positions$$positionError$1_foreach_these = var43; !scala$reflect$internal$Positions$$positionError$1_foreach_these.isEmpty(); scala$reflect$internal$Positions$$positionError$1_foreach_these = (List)scala$reflect$internal$Positions$$positionError$1_foreach_these.tail()) {
                              Trees.Tree var31 = (Trees.Tree)scala$reflect$internal$Positions$$positionError$1_foreach_these.head();
                              Positions.$anonfun$validatePositions$1(scala$reflect$internal$Positions$$positionError$1_this, scala$reflect$internal$Positions$$positionError$1_topTreex, var31);
                           }

                           Object var36 = null;
                           scala$reflect$internal$Positions$$positionError$1_this.inform("=======");
                           throw scala$reflect$internal$Positions$$positionError$1_this.new ValidateException(scala$reflect$internal$Positions$$positionError$1_msgx);
                        }

                        if (!enclPos.includes(treePos)) {
                           SymbolTable var40 = this.$outer;
                           Trees.Tree var46 = this.topTree();
                           String scala$reflect$internal$Positions$$positionError$1_msgxx = (new StringBuilder(42)).append("Enclosing tree [").append(encltree.id()).append("] does not include tree [").append(tree.id()).append("]").toString();
                           Trees.Tree scala$reflect$internal$Positions$$positionError$1_topTreexx = var46;
                           if (var40 == null) {
                              throw null;
                           }

                           Positions scala$reflect$internal$Positions$$positionError$1_this = var40;
                           scala$reflect$internal$Positions$$positionError$1_this.inform((new StringBuilder(23)).append("======= Position error\n").append(scala$reflect$internal$Positions$$positionError$1_msgxx).toString());
                           $anonfun$loop$4(this, encltree, tree);
                           scala$reflect$internal$Positions$$positionError$1_this.inform((new StringBuilder(19)).append("\nWhile validating #").append(scala$reflect$internal$Positions$$positionError$1_topTreexx.id()).toString());
                           scala$reflect$internal$Positions$$positionError$1_this.inform(scala$reflect$internal$Positions$$positionError$1_this.treeStatus(scala$reflect$internal$Positions$$positionError$1_topTreexx, scala$reflect$internal$Positions$$positionError$1_this.treeStatus$default$2()));
                           scala$reflect$internal$Positions$$positionError$1_this.inform("\nChildren:");
                           List var41 = scala$reflect$internal$Positions$$positionError$1_topTreexx.children();
                           if (var41 == null) {
                              throw null;
                           }

                           for(List scala$reflect$internal$Positions$$positionError$1_foreach_these = var41; !scala$reflect$internal$Positions$$positionError$1_foreach_these.isEmpty(); scala$reflect$internal$Positions$$positionError$1_foreach_these = (List)scala$reflect$internal$Positions$$positionError$1_foreach_these.tail()) {
                              Trees.Tree var32 = (Trees.Tree)scala$reflect$internal$Positions$$positionError$1_foreach_these.head();
                              Positions.$anonfun$validatePositions$1(scala$reflect$internal$Positions$$positionError$1_this, scala$reflect$internal$Positions$$positionError$1_topTreexx, var32);
                           }

                           Object var37 = null;
                           scala$reflect$internal$Positions$$positionError$1_this.inform("=======");
                           throw scala$reflect$internal$Positions$$positionError$1_this.new ValidateException(scala$reflect$internal$Positions$$positionError$1_msgxx);
                        }

                        if (numChildren > 1) {
                           Trees.Tree[] childSolidDescendants = this.solidChildrenCollector().sortedArray();
                           Trees.Tree var34 = childSolidDescendants[0];
                           Position t1Pos = var34.pos();

                           for(int i = 1; i < numChildren; ++i) {
                              Trees.Tree t2 = childSolidDescendants[i];
                              Position t2Pos = t2.pos();
                              if (t1Pos.overlaps(t2Pos)) {
                                 SymbolTable var10000 = this.$outer;
                                 Trees.Tree var10001 = this.topTree();
                                 String scala$reflect$internal$Positions$$positionError$1_msgxxx = "Overlapping trees";
                                 Trees.Tree scala$reflect$internal$Positions$$positionError$1_topTreexxx = var10001;
                                 if (var10000 == null) {
                                    throw null;
                                 }

                                 Positions scala$reflect$internal$Positions$$positionError$1_this = var10000;
                                 scala$reflect$internal$Positions$$positionError$1_this.inform((new StringBuilder(23)).append("======= Position error\n").append(scala$reflect$internal$Positions$$positionError$1_msgxxx).toString());
                                 this.$outer.scala$reflect$internal$Positions$$reportTree$1("Ancestor", tree);
                                 this.$outer.scala$reflect$internal$Positions$$reportTree$1("First overlapping", var34);
                                 this.$outer.scala$reflect$internal$Positions$$reportTree$1("Second overlapping", t2);
                                 scala$reflect$internal$Positions$$positionError$1_this.inform((new StringBuilder(19)).append("\nWhile validating #").append(scala$reflect$internal$Positions$$positionError$1_topTreexxx.id()).toString());
                                 scala$reflect$internal$Positions$$positionError$1_this.inform(scala$reflect$internal$Positions$$positionError$1_this.treeStatus(scala$reflect$internal$Positions$$positionError$1_topTreexxx, scala$reflect$internal$Positions$$positionError$1_this.treeStatus$default$2()));
                                 scala$reflect$internal$Positions$$positionError$1_this.inform("\nChildren:");
                                 List var39 = scala$reflect$internal$Positions$$positionError$1_topTreexxx.children();
                                 if (var39 == null) {
                                    throw null;
                                 }

                                 for(List scala$reflect$internal$Positions$$positionError$1_foreach_these = var39; !scala$reflect$internal$Positions$$positionError$1_foreach_these.isEmpty(); scala$reflect$internal$Positions$$positionError$1_foreach_these = (List)scala$reflect$internal$Positions$$positionError$1_foreach_these.tail()) {
                                    Trees.Tree var33 = (Trees.Tree)scala$reflect$internal$Positions$$positionError$1_foreach_these.head();
                                    Positions.$anonfun$validatePositions$1(scala$reflect$internal$Positions$$positionError$1_this, scala$reflect$internal$Positions$$positionError$1_topTreexxx, var33);
                                 }

                                 Object var38 = null;
                                 scala$reflect$internal$Positions$$positionError$1_this.inform("=======");
                                 throw scala$reflect$internal$Positions$$positionError$1_this.new ValidateException(scala$reflect$internal$Positions$$positionError$1_msgxxx);
                              }

                              if (t2Pos.isRange()) {
                                 var34 = t2;
                                 t1Pos = t2Pos;
                              }
                           }
                        }
                     }

                     if (numChildren > 0) {
                        if (numChildren == 1) {
                           Trees.Tree first = this.solidChildrenCollector().child(0);
                           this.solidChildrenCollector().clear();
                           encltree = tree;
                           tree = first;
                           continue;
                        }

                        Trees.Tree[] snap = this.solidChildrenCollector().borrowArray();

                        for(int i = 0; i < numChildren; ++i) {
                           this.loop(snap[i], tree);
                        }

                        this.solidChildrenCollector().spareArray(snap);
                        return;
                     }
                  }

                  return;
               }
            }

            // $FF: synthetic method
            public SymbolTable scala$reflect$internal$Positions$worker$$$outer() {
               return this.$outer;
            }

            private final void solidChildrenCollector$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.solidChildrenCollector$module == null) {
                     this.solidChildrenCollector$module = new Positions$worker$1$solidChildrenCollector$(this);
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            // $FF: synthetic method
            public static final void $anonfun$loop$2(final worker$1$ $this, final Trees.Tree encltree$1, final Trees.Tree t) {
               $this.$outer.inform(scala.collection.StringOps..MODULE$.format$extension("%15s %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"sibling", $this.$outer.treeStatus(t, encltree$1)})));
            }

            // $FF: synthetic method
            public static final void $anonfun$loop$1(final worker$1$ $this, final Trees.Tree tree$2, final Trees.Tree encltree$1) {
               $this.$outer.inform(scala.collection.StringOps..MODULE$.format$extension("%15s %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"unpositioned", $this.$outer.treeStatus(tree$2, encltree$1)})));
               $this.$outer.inform(scala.collection.StringOps..MODULE$.format$extension("%15s %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{"enclosing", $this.$outer.treeStatus(encltree$1, $this.$outer.treeStatus$default$2())})));
               List var10000 = encltree$1.children();
               if (var10000 == null) {
                  throw null;
               } else {
                  for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                     Trees.Tree var4 = (Trees.Tree)foreach_these.head();
                     $anonfun$loop$2($this, encltree$1, var4);
                  }

               }
            }

            // $FF: synthetic method
            public static final void $anonfun$loop$3(final worker$1$ $this, final Trees.Tree encltree$1, final Trees.Tree tree$2) {
               $this.$outer.scala$reflect$internal$Positions$$reportTree$1("Enclosing", encltree$1);
               $this.$outer.scala$reflect$internal$Positions$$reportTree$1("Enclosed", tree$2);
            }

            // $FF: synthetic method
            public static final void $anonfun$loop$4(final worker$1$ $this, final Trees.Tree encltree$1, final Trees.Tree tree$2) {
               $this.$outer.scala$reflect$internal$Positions$$reportTree$1("Enclosing", encltree$1);
               $this.$outer.scala$reflect$internal$Positions$$reportTree$1("Enclosed", tree$2);
            }

            // $FF: synthetic method
            public static final void $anonfun$loop$5(final worker$1$ $this, final Trees.Tree tree$2, final ObjectRef t1$1, final Trees.Tree t2$1) {
               $this.$outer.scala$reflect$internal$Positions$$reportTree$1("Ancestor", tree$2);
               $this.$outer.scala$reflect$internal$Positions$$reportTree$1("First overlapping", (Trees.Tree)t1$1.elem);
               $this.$outer.scala$reflect$internal$Positions$$reportTree$1("Second overlapping", t2$1);
            }

            public worker$1$(final Trees.Tree tree$1) {
               if (Positions.this == null) {
                  throw null;
               } else {
                  this.$outer = Positions.this;
                  super();
                  this.trace = BoxesRunTime.unboxToBoolean(Positions.this.settings().Yposdebug().value()) && BoxesRunTime.unboxToBoolean(Positions.this.settings().verbose().value());
                  this.topTree = tree$1;
               }
            }

            // $FF: synthetic method
            public static final Object $anonfun$loop$2$adapted(final worker$1$ $this, final Trees.Tree encltree$1, final Trees.Tree t) {
               $anonfun$loop$2($this, encltree$1, t);
               return BoxedUnit.UNIT;
            }
         }

         var3 = worker$module$1.initialized() ? (worker$1$)worker$module$1.value() : (worker$1$)worker$module$1.initialize(new worker$1$(tree$1));
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private worker$1$ worker$2(final LazyRef worker$module$1, final Trees.Tree tree$1) {
      return worker$module$1.initialized() ? (worker$1$)worker$module$1.value() : this.worker$lzycompute$1(worker$module$1, tree$1);
   }

   // $FF: synthetic method
   static void $anonfun$setChildrenPos$1(final Position pos$2, final Trees.Tree parent$1, final SetChildrenPosAccumulator x$5) {
      x$5.set(pos$2, parent$1);
   }

   static void $init$(final Positions $this) {
      $this.scala$reflect$internal$Positions$_setter_$NoPosition_$eq(NoPosition$.MODULE$);
      $this.scala$reflect$internal$Positions$_setter_$PositionTag_$eq(scala.reflect.ClassTag..MODULE$.apply(Position.class));
      $this.scala$reflect$internal$Positions$_setter_$scala$reflect$internal$Positions$$posStartOrdering_$eq(new Ordering() {
         // $FF: synthetic field
         private final SymbolTable $outer;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public boolean lteq(final Object x, final Object y) {
            return Ordering.lteq$(this, x, y);
         }

         public boolean gteq(final Object x, final Object y) {
            return Ordering.gteq$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Ordering.lt$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Ordering.gt$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return Ordering.equiv$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Ordering.max$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Ordering.min$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public int compare(final Trees.Tree x, final Trees.Tree y) {
            return Integer.compare(this.posOf$1(x), this.posOf$1(y));
         }

         private final int posOf$1(final Trees.Tree t) {
            Position pos = t.pos();
            return pos == this.$outer.NoPosition() ? Integer.MIN_VALUE : pos.start();
         }

         public {
            if (Positions.this == null) {
               throw null;
            } else {
               this.$outer = Positions.this;
            }
         }
      });
      ReusableInstance$ var10001 = ReusableInstance$.MODULE$;
      Function0 var6 = () -> (SymbolTable)$this.new SetChildrenPosAccumulator();
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
      $this.scala$reflect$internal$Positions$_setter_$scala$reflect$internal$Positions$$setChildrenPosAccumulator_$eq(var7);
   }

   // $FF: synthetic method
   static Object $anonfun$ensureNonOverlapping$2$adapted(final Positions $this, final List others$1, final boolean focus$2, final Trees.Tree x$4) {
      $anonfun$ensureNonOverlapping$2($this, others$1, focus$2, x$4);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$setChildrenPos$1$adapted(final Position pos$2, final Trees.Tree parent$1, final SetChildrenPosAccumulator x$5) {
      $anonfun$setChildrenPos$1(pos$2, parent$1, x$5);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$ensureNonOverlapping$1$adapted(final Position pos$1, final Trees.Tree x$3) {
      return BoxesRunTime.boxToBoolean($anonfun$ensureNonOverlapping$1(pos$1, x$3));
   }

   // $FF: synthetic method
   static Object $anonfun$validatePositions$1$adapted(final Positions $this, final Trees.Tree topTree$1, final Trees.Tree t) {
      $anonfun$validatePositions$1($this, topTree$1, t);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private final class WrappingPosAccumulator implements Function1 {
      private int min;
      private int max;

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public void reset() {
         this.min = Integer.MAX_VALUE;
         this.max = Integer.MIN_VALUE;
      }

      public Position result(final Position default, final boolean focus) {
         if (this.min > this.max) {
            return focus ? default.focus() : default;
         } else {
            int point = default.pointOrElse(this.min);
            if (point >= this.min && point <= this.max) {
               return Position$.MODULE$.range(default.source(), this.min, point, this.max);
            } else {
               int start = Math.min(this.min, point);
               int end = Math.max(this.max, point);
               return Position$.MODULE$.range(default.source(), start, point, end);
            }
         }
      }

      public boolean apply(final Trees.Tree v1) {
         Position pos = v1.pos();
         if (pos.isRange()) {
            this.min = Math.min(this.min, pos.start());
            this.max = Math.max(this.max, pos.end());
         }

         return true;
      }

      public WrappingPosAccumulator() {
         this.reset();
      }
   }

   public abstract class ChildSolidDescendantsCollector extends scala.reflect.api.Trees.Traverser {
      // $FF: synthetic field
      public final SymbolTable $outer;

      public void traverseModifiers(final Trees.Modifiers mods) {
      }

      public void traverse(final Trees.Tree tree) {
         if (tree != this.scala$reflect$internal$Positions$ChildSolidDescendantsCollector$$$outer().EmptyTree()) {
            if (tree.pos().isTransparent()) {
               super.traverse(tree);
            } else {
               this.traverseSolidChild(tree);
            }
         }
      }

      public abstract void traverseSolidChild(final Trees.Tree t);

      public void apply(final Trees.Tree t) {
         super.traverse(t);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Positions$ChildSolidDescendantsCollector$$$outer() {
         return this.$outer;
      }

      public ChildSolidDescendantsCollector() {
         if (Positions.this == null) {
            throw null;
         } else {
            this.$outer = Positions.this;
            super();
         }
      }
   }

   private final class SetChildrenPosAccumulator implements Function1 {
      private final WrappingPosAccumulator wrappingPosAccumulator;
      private Position pos;
      // $FF: synthetic field
      private final SymbolTable $outer;

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public void set(final Position pos, final Trees.Tree parent) {
         this.wrappingPosAccumulator.reset();
         this.pos = pos;

         try {
            parent.foreachChild((tree) -> BoxesRunTime.boxToBoolean($anonfun$set$1(this, tree)));
         } finally {
            this.pos = null;
         }

      }

      public boolean apply(final Trees.Tree tree) {
         this.wrappingPosAccumulator.reset();
         if (!tree.isEmpty() && tree.canHaveAttrs()) {
            Position var10000 = tree.pos();
            NoPosition$ var2 = this.$outer.NoPosition();
            if (var10000 == null) {
               if (var2 != null) {
                  return true;
               }
            } else if (!var10000.equals(var2)) {
               return true;
            }

            tree.foreachChild((treex) -> BoxesRunTime.boxToBoolean($anonfun$apply$1(this, treex)));
            tree.foreachChild(this.wrappingPosAccumulator);
            Position wrappingPos = this.wrappingPosAccumulator.result(this.pos, true);
            tree.setPos(wrappingPos);
         }

         return true;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$set$1(final SetChildrenPosAccumulator $this, final Trees.Tree tree) {
         return $this.apply(tree);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$apply$1(final SetChildrenPosAccumulator $this, final Trees.Tree tree) {
         return $this.apply(tree);
      }

      public SetChildrenPosAccumulator() {
         if (Positions.this == null) {
            throw null;
         } else {
            this.$outer = Positions.this;
            super();
            this.wrappingPosAccumulator = Positions.this.new WrappingPosAccumulator();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class ValidateException extends Exception {
      // $FF: synthetic field
      public final SymbolTable $outer;

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Positions$ValidateException$$$outer() {
         return this.$outer;
      }

      public ValidateException(final String msg) {
         if (Positions.this == null) {
            throw null;
         } else {
            this.$outer = Positions.this;
            super(msg);
         }
      }
   }

   public class Locator extends scala.reflect.api.Trees.Traverser {
      private final Position pos;
      private Trees.Tree last;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Trees.Tree last() {
         return this.last;
      }

      public void last_$eq(final Trees.Tree x$1) {
         this.last = x$1;
      }

      public Trees.Tree locateIn(final Trees.Tree root) {
         this.last_$eq(this.scala$reflect$internal$Positions$Locator$$$outer().EmptyTree());
         this.traverse(root);
         return this.last();
      }

      public boolean isEligible(final Trees.Tree t) {
         return !t.pos().isTransparent();
      }

      public void traverse(final Trees.Tree t) {
         if (t instanceof Trees.TypeTree) {
            Trees.TypeTree var2 = (Trees.TypeTree)t;
            if (var2.original() != null && var2.pos().includes(var2.original().pos())) {
               this.traverse(var2.original());
               return;
            }
         }

         if (t.pos().includes(this.pos)) {
            if (this.isEligible(t)) {
               this.last_$eq(t);
            }

            super.traverse(t);
         }

         if (t instanceof Trees.MemberDef) {
            Trees.MemberDef var3 = (Trees.MemberDef)t;
            List var5 = var3.mods().annotations();
            Object var10000;
            if (scala.collection.immutable.Nil..MODULE$.equals(var5) && var3.symbol() != null) {
               List var16 = var3.symbol().annotations();
               if (var16 == null) {
                  throw null;
               }

               List map_this = var16;
               if (map_this == scala.collection.immutable.Nil..MODULE$) {
                  var10000 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  .colon.colon map_h = new .colon.colon(((AnnotationInfos.AnnotationInfo)map_this.head()).original(), scala.collection.immutable.Nil..MODULE$);
                  .colon.colon map_t = map_h;

                  for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     .colon.colon map_nx = new .colon.colon(((AnnotationInfos.AnnotationInfo)map_rest.head()).original(), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var10000 = map_h;
               }

               Object var11 = null;
               Object var12 = null;
               Object var13 = null;
               Object var14 = null;
               Object var15 = null;
            } else {
               var10000 = var5;
            }

            List annTrees = (List)var10000;
            this.traverseTrees(annTrees);
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Positions$Locator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$traverse$1(final AnnotationInfos.AnnotationInfo x$8) {
         return x$8.original();
      }

      public Locator(final Position pos) {
         this.pos = pos;
         if (Positions.this == null) {
            throw null;
         } else {
            this.$outer = Positions.this;
            super();
         }
      }
   }

   public class TypedLocator extends Locator {
      public boolean isEligible(final Trees.Tree t) {
         return super.isEligible(t) && t.tpe() != null;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Positions$TypedLocator$$$outer() {
         return this.$outer;
      }

      public TypedLocator(final Position pos) {
         super(pos);
      }
   }

   public class DefaultPosAssigner extends Trees.InternalTraverser implements PosAssigner {
      private Position pos;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Position pos() {
         return this.pos;
      }

      public void pos_$eq(final Position x$1) {
         this.pos = x$1;
      }

      public void traverse(final Trees.Tree t) {
         if (t.canHaveAttrs()) {
            Position var10000 = t.pos();
            NoPosition$ var2 = this.scala$reflect$internal$Positions$DefaultPosAssigner$$$outer().NoPosition();
            if (var10000 == null) {
               if (var2 != null) {
                  return;
               }
            } else if (!var10000.equals(var2)) {
               return;
            }

            t.setPos(this.pos());
            t.traverse(this);
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Positions$DefaultPosAssigner$$$outer() {
         return this.$outer;
      }

      public DefaultPosAssigner() {
         if (Positions.this == null) {
            throw null;
         } else {
            this.$outer = Positions.this;
            super();
         }
      }
   }

   public interface PosAssigner {
      Position pos();

      void pos_$eq(final Position x$1);
   }
}
