package scala.reflect.internal.util;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.SeqOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.StrictOptimizedSeqOps;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Names;
import scala.reflect.internal.NoPhase$;
import scala.reflect.internal.Phase;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mgaB\r\u001b!\u0003\r\ta\t\u0005\u0006Q\u0001!\t!\u000b\u0005\b[\u0001\u0011\rQ\"\u0001/\u0011\u001d\u0019\u0004\u00011Q\u0005\nQBq\u0001\u000f\u0001AB\u0013%\u0011\bC\u0004=\u0001\t\u0007I\u0011A\u001f\t\u000fA\u0003!\u0019!C\u0001#\"9!\f\u0001b\u0001\n\u0003Y\u0006b\u00023\u0001\u0005\u0004%\t!\u001a\u0005\u0006]\u0002!\ta\u001c\u0005\u0006e\u0002!\ta\u001d\u0005\u0006m\u0002!\ta\u001e\u0005\tw\u0002A)\u0019!C\u0005y\")Q\u0010\u0001C\u0005}\"9\u0011\u0011\u0004\u0001\u0005\n\u0005m\u0001bBA\u0014\u0001\u0011%\u0011\u0011\u0006\u0005\b\u0003s\u0001A\u0011BA\u001e\u0011\u001d\t\t\u0005\u0001C\u0005\u0003\u0007Bq!a\u0012\u0001\t\u0013\tI\u0005C\u0004\u0002N\u0001!I!a\u0014\t\u000f\u0005M\u0003\u0001\"\u0003\u0002V!9\u0011\u0011\f\u0001\u0005\n\u0005m\u0003bBAI\u0001\u0011%\u00111\u0013\u0005\b\u0003w\u0003A\u0011BA_\u0011\u0019\tI\u000e\u0001C\u0001S\t\u0019BK]1dKNKXNY8m\u0003\u000e$\u0018N^5us*\u00111\u0004H\u0001\u0005kRLGN\u0003\u0002\u001e=\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002 A\u00059!/\u001a4mK\u000e$(\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001\u0001\n\t\u0003K\u0019j\u0011\u0001I\u0005\u0003O\u0001\u0012a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001+!\t)3&\u0003\u0002-A\t!QK\\5u\u0003\u00199Gn\u001c2bYV\tq\u0006\u0005\u00021c5\tA$\u0003\u000239\tY1+_7c_2$\u0016M\u00197f\u0003\u001d)g.\u00192mK\u0012,\u0012!\u000e\t\u0003KYJ!a\u000e\u0011\u0003\u000f\t{w\u000e\\3b]\u0006YQM\\1cY\u0016$w\fJ3r)\tQ#\bC\u0004<\t\u0005\u0005\t\u0019A\u001b\u0002\u0007a$\u0013'\u0001\u0006bY2\u001c\u00160\u001c2pYN,\u0012A\u0010\t\u0005\u007f\u00113\u0015*D\u0001A\u0015\t\t%)A\u0004nkR\f'\r\\3\u000b\u0005\r\u0003\u0013AC2pY2,7\r^5p]&\u0011Q\t\u0011\u0002\u0004\u001b\u0006\u0004\bCA\u0013H\u0013\tA\u0005EA\u0002J]R\u0004\"A\u0013'\u000f\u0005-\u0013Q\"\u0001\u0001\n\u00055s%AB*z[\n|G.\u0003\u0002P9\t91+_7c_2\u001c\u0018aC1mY\u000eC\u0017\u000e\u001c3sK:,\u0012A\u0015\t\u0005\u007f\u001135\u000bE\u0002U/\u001as!!J+\n\u0005Y\u0003\u0013a\u00029bG.\fw-Z\u0005\u00031f\u0013A\u0001T5ti*\u0011a\u000bI\u0001\u000baJ,goT<oKJ\u001cX#\u0001/\u0011\t}\"e)\u0018\t\u0004)^s\u0006\u0003B\u0013`\r\u0006L!\u0001\u0019\u0011\u0003\rQ+\b\u000f\\33!\t\u0001$-\u0003\u0002d9\t)\u0001\u000b[1tK\u0006A\u0011\r\u001c7Ue\u0016,7/F\u0001g!\ryt-[\u0005\u0003Q\u0002\u00131aU3u!\tQ%.\u0003\u0002lY\n!AK]3f\u0013\tiGDA\u0003Ue\u0016,7/A\nsK\u000e|'\u000fZ*z[\n|Gn]%o)J,W\r\u0006\u0002+a\")\u0011/\u0003a\u0001S\u0006!AO]3f\u0003=\u0011XmY8sI:+woU=nE>dGC\u0001\u0016u\u0011\u0015)(\u00021\u0001J\u0003\r\u0019\u00180\\\u0001\u0015e\u0016\u001cwN\u001d3OK^\u001c\u00160\u001c2pY>;h.\u001a:\u0015\u0007)B\u0018\u0010C\u0003v\u0017\u0001\u0007\u0011\nC\u0003{\u0017\u0001\u0007\u0011*\u0001\u0005oK^|uO\\3s\u00031)'/Y:ve\u0016\u0004\u0006.Y:f+\u0005\t\u0017!C:jO:\fG/\u001e:f)\ry\u0018Q\u0003\t\u0005\u0003\u0003\tyA\u0004\u0003\u0002\u0004\u0005-\u0001cAA\u0003A5\u0011\u0011q\u0001\u0006\u0004\u0003\u0013\u0011\u0013A\u0002\u001fs_>$h(C\u0002\u0002\u000e\u0001\na\u0001\u0015:fI\u00164\u0017\u0002BA\t\u0003'\u0011aa\u0015;sS:<'bAA\u0007A!1\u0011qC\u0007A\u0002\u0019\u000b!!\u001b3\u0002\r\u0011\f7\u000f[3t)\ry\u0018Q\u0004\u0005\b\u0003?q\u0001\u0019AA\u0011\u0003\u0005\u0019\bcA\u0013\u0002$%\u0019\u0011Q\u0005\u0011\u0003\u0007\u0005s\u00170\u0001\u0003tQ><H#\u0002\u0016\u0002,\u0005=\u0002bBA\u0017\u001f\u0001\u0007\u0011\u0011E\u0001\u0003gFBq!!\r\u0010\u0001\u0004\t\u0019$\u0001\u0002tgB)Q%!\u000e\u0002\"%\u0019\u0011q\u0007\u0011\u0003\u0015q\u0012X\r]3bi\u0016$g(\u0001\u0006tQ><\b*Z1eKJ$RAKA\u001f\u0003\u007fAq!!\f\u0011\u0001\u0004\t\t\u0003C\u0004\u00022A\u0001\r!a\r\u0002\u000fMDwn^*z[R\u0019!&!\u0012\t\u000bU\f\u0002\u0019A%\u0002\u001fMDwn^%e\u0003:$'+Z7pm\u0016$2AKA&\u0011\u0019\t9B\u0005a\u0001\r\u0006I1/_7c_2\u001cFO\u001d\u000b\u0004\u007f\u0006E\u0003BBA\f'\u0001\u0007a)\u0001\u0005po:,'o\u0015;s)\ry\u0018q\u000b\u0005\u0007\u0003/!\u0002\u0019\u0001$\u0002\t\u0019\u0014X-]\u000b\u0007\u0003;\n\t)!\u001b\u0015\t\u0005}\u0013Q\u0011\u000b\u0005\u0003C\n)\b\u0005\u0003U/\u0006\r\u0004#B\u0013`\u0003K2\u0005\u0003BA4\u0003Sb\u0001\u0001B\u0004\u0002lU\u0011\r!!\u001c\u0003\u0003U\u000bB!a\u001c\u0002\"A\u0019Q%!\u001d\n\u0007\u0005M\u0004EA\u0004O_RD\u0017N\\4\t\u000f\u0005]T\u00031\u0001\u0002z\u0005\u0011aM\u001c\t\bK\u0005m\u0014qPA3\u0013\r\ti\b\t\u0002\n\rVt7\r^5p]F\u0002B!a\u001a\u0002\u0002\u00129\u00111Q\u000bC\u0002\u00055$!\u0001+\t\u000f\u0005\u001dU\u00031\u0001\u0002\n\u0006\u0011\u0001p\u001d\t\u0007\u0003\u0017\u000bi)a \u000e\u0003\tK1!a$C\u0005!IE/\u001a:bE2,\u0017aC:i_^l\u0015\r\u001d$sKF,B!!&\u0002\"R!\u0011qSAR)\rQ\u0013\u0011\u0014\u0005\b\u000373\u0002\u0019AAO\u0003\u0019\u0019\bn\\<G]B1Q%a\u001f\u0002 ~\u0004B!a\u001a\u0002\"\u00129\u00111\u0011\fC\u0002\u00055\u0004bBAD-\u0001\u0007\u0011Q\u0015\t\t\u0003\u0017\u000b9+a(\u0002*&\u0011QI\u0011\u0019\u0005\u0003W\u000b\t\fE\u0003U\u0003[\u000by+C\u0002\u0002\u0010f\u0003B!a\u001a\u00022\u0012a\u00111WA[\u0003\u0003\u0005\tQ!\u0001\u0002n\t\u0019q\fJ\u0019\t\u000f\u0005\u001de\u00031\u0001\u00028BA\u00111RAT\u0003s\u000bI\u000b\u0005\u0003\u0002h\u0005\u0005\u0016\u0001C:i_^4%/Z9\u0016\r\u0005}\u00161ZAh)\u0011\t\t-!6\u0015\u000b)\n\u0019-!5\t\u000f\u0005\u0015w\u00031\u0001\u0002H\u00069qM]8va\u001as\u0007cB\u0013\u0002|\u0005%\u0017Q\u001a\t\u0005\u0003O\nY\rB\u0004\u0002\u0004^\u0011\r!!\u001c\u0011\t\u0005\u001d\u0014q\u001a\u0003\b\u0003W:\"\u0019AA7\u0011\u001d\tYj\u0006a\u0001\u0003'\u0004b!JA>\u0003\u001b|\bbBAD/\u0001\u0007\u0011q\u001b\t\u0006)\u00065\u0016\u0011Z\u0001\u000fg\"|w/\u00117m'fl'm\u001c7t\u0001"
)
public interface TraceSymbolActivity {
   void scala$reflect$internal$util$TraceSymbolActivity$_setter_$allSymbols_$eq(final Map x$1);

   void scala$reflect$internal$util$TraceSymbolActivity$_setter_$allChildren_$eq(final Map x$1);

   void scala$reflect$internal$util$TraceSymbolActivity$_setter_$prevOwners_$eq(final Map x$1);

   void scala$reflect$internal$util$TraceSymbolActivity$_setter_$allTrees_$eq(final scala.collection.mutable.Set x$1);

   SymbolTable global();

   boolean scala$reflect$internal$util$TraceSymbolActivity$$enabled();

   void scala$reflect$internal$util$TraceSymbolActivity$$enabled_$eq(final boolean x$1);

   Map allSymbols();

   Map allChildren();

   Map prevOwners();

   scala.collection.mutable.Set allTrees();

   // $FF: synthetic method
   static void recordSymbolsInTree$(final TraceSymbolActivity $this, final Trees.Tree tree) {
      $this.recordSymbolsInTree(tree);
   }

   default void recordSymbolsInTree(final Trees.Tree tree) {
      if (this.scala$reflect$internal$util$TraceSymbolActivity$$enabled()) {
         scala.collection.mutable.Set var10000 = this.allTrees();
         if (var10000 == null) {
            throw null;
         } else {
            var10000.addOne(tree);
         }
      }
   }

   // $FF: synthetic method
   static void recordNewSymbol$(final TraceSymbolActivity $this, final Symbols.Symbol sym) {
      $this.recordNewSymbol(sym);
   }

   default void recordNewSymbol(final Symbols.Symbol sym) {
      if (this.scala$reflect$internal$util$TraceSymbolActivity$$enabled() && sym.id() > 1) {
         this.allSymbols().update(sym.id(), sym);
         int var2 = sym.owner().id();
         Map var10000 = this.allChildren();
         Integer var10001 = var2;
         List var10002 = (List)this.allChildren().apply(var2);
         Integer $colon$colon_elem = sym.id();
         if (var10002 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10002;
            .colon.colon var7 = new .colon.colon($colon$colon_elem, $colon$colon_this);
            Object var5 = null;
            Object var6 = null;
            var10000.update(var10001, var7);
         }
      }
   }

   // $FF: synthetic method
   static void recordNewSymbolOwner$(final TraceSymbolActivity $this, final Symbols.Symbol sym, final Symbols.Symbol newOwner) {
      $this.recordNewSymbolOwner(sym, newOwner);
   }

   default void recordNewSymbolOwner(final Symbols.Symbol sym, final Symbols.Symbol newOwner) {
      if (this.scala$reflect$internal$util$TraceSymbolActivity$$enabled()) {
         int sid = sym.id();
         int oid = sym.owner().id();
         int nid = newOwner.id();
         Map var10000 = this.prevOwners();
         Integer var10001 = sid;
         List var10002 = (List)this.prevOwners().apply(sid);
         Predef.ArrowAssoc var10003 = scala.Predef.ArrowAssoc..MODULE$;
         Integer var80 = oid;
         Phase $minus$greater$extension_y = this.global().phase();
         Object $minus$greater$extension_$this = var80;
         Tuple2 var81 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
         $minus$greater$extension_$this = null;
         $minus$greater$extension_y = null;
         Tuple2 $colon$colon_elem = var81;
         if (var10002 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10002;
            .colon.colon var74 = new .colon.colon($colon$colon_elem, $colon$colon_this);
            Object var64 = null;
            Object var65 = null;
            var10000.update(var10001, var74);
            var10000 = this.allChildren();
            var10001 = oid;
            List var75 = (List)this.allChildren().apply(oid);
            if (var75 == null) {
               throw null;
            } else {
               List filterNot_this = var75;
               boolean filterNot_filterCommon_isFlipped = true;
               List filterNot_filterCommon_noneIn$1_l = filterNot_this;

               while(true) {
                  if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var76 = scala.collection.immutable.Nil..MODULE$;
                     break;
                  }

                  Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
                  List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
                  int var28 = BoxesRunTime.unboxToInt(filterNot_filterCommon_noneIn$1_h);
                  if ($anonfun$recordNewSymbolOwner$1(sid, var28) != filterNot_filterCommon_isFlipped) {
                     List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var76 = filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var28 = BoxesRunTime.unboxToInt(filterNot_filterCommon_noneIn$1_allIn$1_x);
                        if ($anonfun$recordNewSymbolOwner$1(sid, var28) == filterNot_filterCommon_isFlipped) {
                           .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new .colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                           .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var28 = BoxesRunTime.unboxToInt(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head);
                              if ($anonfun$recordNewSymbolOwner$1(sid, var28) != filterNot_filterCommon_isFlipped) {
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
                                    filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                    filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                 }

                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              }
                           }

                           if (!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                           }

                           var76 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var40 = null;
                           Object var43 = null;
                           Object var46 = null;
                           Object var49 = null;
                           Object var52 = null;
                           Object var55 = null;
                           Object var58 = null;
                           Object var61 = null;
                           break;
                        }

                        filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var36 = null;
                     Object var38 = null;
                     Object var41 = null;
                     Object var44 = null;
                     Object var47 = null;
                     Object var50 = null;
                     Object var53 = null;
                     Object var56 = null;
                     Object var59 = null;
                     Object var62 = null;
                     break;
                  }

                  filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
               }

               Object var33 = null;
               Object var34 = null;
               Object var35 = null;
               Object var37 = null;
               Object var39 = null;
               Object var42 = null;
               Object var45 = null;
               Object var48 = null;
               Object var51 = null;
               Object var54 = null;
               Object var57 = null;
               Object var60 = null;
               Object var63 = null;
               List filterNot_filterCommon_result = (List)var76;
               Statics.releaseFence();
               Object var77 = filterNot_filterCommon_result;
               Object var31 = null;
               filterNot_filterCommon_result = null;
               var10000.update(var10001, var77);
               var10000 = this.allChildren();
               var10001 = nid;
               List var78 = (List)this.allChildren().apply(nid);
               Integer $colon$colon_elem = sid;
               if (var78 == null) {
                  throw null;
               } else {
                  List $colon$colon_this = var78;
                  .colon.colon var79 = new .colon.colon($colon$colon_elem, $colon$colon_this);
                  Object var66 = null;
                  Object var67 = null;
                  var10000.update(var10001, var79);
               }
            }
         }
      }
   }

   // $FF: synthetic method
   static Phase scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase$(final TraceSymbolActivity $this) {
      return $this.scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase();
   }

   default Phase scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase() {
      return this.global().findPhaseWithName("erasure");
   }

   private String signature(final int id) {
      SymbolTable var10000 = this.global();
      Phase enteringPhase_ph = this.scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase();
      if (var10000 == null) {
         throw null;
      } else {
         SymbolTable enteringPhase_this = var10000;
         if (enteringPhase_ph == enteringPhase_this.phase()) {
            return $anonfun$signature$1(this, id);
         } else {
            Phase enteringPhase_saved = enteringPhase_this.pushPhase(enteringPhase_ph);

            try {
               var8 = $anonfun$signature$1(this, id);
            } finally {
               enteringPhase_this.popPhase(enteringPhase_saved);
            }

            return var8;
         }
      }
   }

   private String dashes(final Object s) {
      String map$extension_$this = String.valueOf(s);
      int map$extension_len = map$extension_$this.length();
      char[] map$extension_dst = new char[map$extension_len];

      for(int map$extension_i = 0; map$extension_i < map$extension_len; ++map$extension_i) {
         map$extension_$this.charAt(map$extension_i);
         map$extension_dst[map$extension_i] = '-';
      }

      return new String(map$extension_dst);
   }

   private void show(final Object s1, final Seq ss) {
      String var3 = scala.collection.StringOps..MODULE$.format$extension("%-12s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{s1}));
      if (ss == null) {
         throw null;
      } else {
         IterableOnceOps var10000 = (IterableOnceOps)ss.prepended(var3);
         String mkString_sep = " ";
         if (var10000 == null) {
            throw null;
         } else {
            IterableOnceOps mkString_this = var10000;
            String var9 = mkString_this.mkString("", mkString_sep, "");
            Object var7 = null;
            Object var8 = null;
            Object println_x = var9;
            scala.Console..MODULE$.println(println_x);
         }
      }
   }

   private void showHeader(final Object s1, final Seq ss) {
      this.show(s1, ss);
      this.show(this.dashes(s1), (Seq)ss.map((s) -> this.dashes(s)));
   }

   private void showSym(final Symbols.Symbol sym) {
      try {
         Object println_x = scala.collection.StringOps..MODULE$.format$extension("%s#%s %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{prefix$1(sym), sym.accurateKindString(), sym.name().decode()}));
         scala.Console..MODULE$.println(println_x);
         println_x = null;
      } catch (Throwable var7) {
         Object println_x = (new StringBuilder(9)).append(prefix$1(sym)).append(" failed: ").append(var7).toString();
         scala.Console..MODULE$.println(println_x);
         println_x = null;
      }

      List var10000 = (List)((StrictOptimizedSeqOps)this.allChildren().apply(sym.id())).sorted(scala.math.Ordering.Int..MODULE$);
      if (var10000 == null) {
         throw null;
      } else {
         for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            int var6 = BoxesRunTime.unboxToInt(foreach_these.head());
            $anonfun$showSym$1(this, var6);
         }

      }
   }

   private void showIdAndRemove(final int id) {
      Option var10000 = this.allSymbols().remove(id);
      if (var10000 == null) {
         throw null;
      } else {
         Option foreach_this = var10000;
         if (!foreach_this.isEmpty()) {
            Symbols.Symbol var3 = (Symbols.Symbol)foreach_this.get();
            $anonfun$showIdAndRemove$1(this, var3);
         }
      }
   }

   private String symbolStr(final int id) {
      if (id == 1) {
         return "NoSymbol";
      } else {
         Symbols.Symbol sym = (Symbols.Symbol)this.allSymbols().apply(id);
         return (new StringBuilder(1)).append(sym.accurateKindString()).append(" ").append(sym.name().decode()).toString();
      }
   }

   private String ownerStr(final int id) {
      Symbols.Symbol sym = (Symbols.Symbol)this.allSymbols().apply(id);
      return (new StringBuilder(1)).append(sym.name().decode()).append("#").append(sym.id()).toString();
   }

   private List freq(final Iterable xs, final Function1 fn) {
      return (List)xs.groupMapReduce(fn, (x$3) -> BoxesRunTime.boxToInteger($anonfun$freq$1(x$3)), (JFunction2.mcIII.sp)(x$4, x$5) -> x$4 + x$5).toList().sortBy((x$6) -> BoxesRunTime.boxToInteger($anonfun$freq$3(x$6)), scala.math.Ordering.Int..MODULE$);
   }

   private void showMapFreq(final scala.collection.Map xs, final Function1 showFn) {
      List var10000 = ((List)xs.view().mapValues((x$7) -> BoxesRunTime.boxToInteger($anonfun$showMapFreq$1(x$7))).toList().sortBy((x$8) -> BoxesRunTime.boxToInteger($anonfun$showMapFreq$2(x$8)), scala.math.Ordering.Int..MODULE$)).take(100);
      if (var10000 == null) {
         throw null;
      } else {
         for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            Tuple2 var5 = (Tuple2)foreach_these.head();
            if (var5 == null) {
               throw new MatchError((Object)null);
            }

            Object $anonfun$showMapFreq$3_k = var5._1();
            int $anonfun$showMapFreq$3_size = var5._2$mcI$sp();
            this.show($anonfun$showMapFreq$3_size, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{showFn.apply($anonfun$showMapFreq$3_k)}));
            $anonfun$showMapFreq$3_k = null;
         }

         Object var8 = null;
         Object println_x = "\n";
         scala.Console..MODULE$.println(println_x);
      }
   }

   private void showFreq(final Iterable xs, final Function1 groupFn, final Function1 showFn) {
      List var10000 = ((List)xs.toList().groupBy(groupFn).view().mapValues((x$7) -> BoxesRunTime.boxToInteger($anonfun$showMapFreq$1(x$7))).toList().sortBy((x$8) -> BoxesRunTime.boxToInteger($anonfun$showMapFreq$2(x$8)), scala.math.Ordering.Int..MODULE$)).take(100);
      if (var10000 == null) {
         throw null;
      } else {
         for(List showMapFreq_foreach_these = var10000; !showMapFreq_foreach_these.isEmpty(); showMapFreq_foreach_these = (List)showMapFreq_foreach_these.tail()) {
            Tuple2 var6 = (Tuple2)showMapFreq_foreach_these.head();
            if (var6 == null) {
               throw new MatchError((Object)null);
            }

            Object $anonfun$showMapFreq$3_k = var6._1();
            int $anonfun$showMapFreq$3_size = var6._2$mcI$sp();
            this.show($anonfun$showMapFreq$3_size, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{showFn.apply($anonfun$showMapFreq$3_k)}));
            $anonfun$showMapFreq$3_k = null;
         }

         Object var9 = null;
         Object showMapFreq_println_x = "\n";
         scala.Console..MODULE$.println(showMapFreq_println_x);
      }
   }

   // $FF: synthetic method
   static void showAllSymbols$(final TraceSymbolActivity $this) {
      $this.showAllSymbols();
   }

   default void showAllSymbols() {
      // $FF: Couldn't be decompiled
   }

   // $FF: synthetic method
   static boolean $anonfun$recordNewSymbolOwner$1(final int sid$1, final int x$1) {
      return x$1 == sid$1;
   }

   // $FF: synthetic method
   static String $anonfun$signature$1(final TraceSymbolActivity $this, final int id$1) {
      return ((Symbols.Symbol)$this.allSymbols().apply(id$1)).defString();
   }

   // $FF: synthetic method
   static char $anonfun$dashes$1(final char x$2) {
      return '-';
   }

   private static String prefix$1(final Symbols.Symbol sym$1) {
      return (new StringBuilder(0)).append(scala.collection.StringOps..MODULE$.$times$extension("  ", sym$1.ownerChain().length() - 1)).append(sym$1.id()).toString();
   }

   // $FF: synthetic method
   static void $anonfun$showSym$1(final TraceSymbolActivity $this, final int id) {
      $this.showIdAndRemove(id);
   }

   // $FF: synthetic method
   static void $anonfun$showIdAndRemove$1(final TraceSymbolActivity $this, final Symbols.Symbol sym) {
      $this.showSym(sym);
   }

   // $FF: synthetic method
   static int $anonfun$freq$1(final Object x$3) {
      return 1;
   }

   // $FF: synthetic method
   static int $anonfun$freq$3(final Tuple2 x$6) {
      return -x$6._2$mcI$sp();
   }

   // $FF: synthetic method
   static int $anonfun$showMapFreq$1(final Iterable x$7) {
      return x$7.size();
   }

   // $FF: synthetic method
   static int $anonfun$showMapFreq$2(final Tuple2 x$8) {
      return -x$8._2$mcI$sp();
   }

   // $FF: synthetic method
   static void $anonfun$showMapFreq$3(final TraceSymbolActivity $this, final Function1 showFn$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k = x0$1._1();
         int size = x0$1._2$mcI$sp();
         $this.show(size, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{showFn$1.apply(k)}));
      } else {
         throw new MatchError((Object)null);
      }
   }

   // $FF: synthetic method
   static int $anonfun$showAllSymbols$2(final Symbols.Symbol x$10) {
      return x$10.id();
   }

   // $FF: synthetic method
   static String $anonfun$showAllSymbols$3(final TraceSymbolActivity $this, final int id) {
      return $this.symbolStr(id);
   }

   // $FF: synthetic method
   static String $anonfun$showAllSymbols$4(final TraceSymbolActivity $this, final int id) {
      return $this.symbolStr(id);
   }

   // $FF: synthetic method
   static String $anonfun$showAllSymbols$6(final TraceSymbolActivity $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int oid = x0$1._1$mcI$sp();
         Phase var3 = (Phase)x0$1._2();
         if (NoPhase$.MODULE$.equals(var3)) {
            return (new StringBuilder(12)).append("-> owned by ").append($this.ownerStr(oid)).toString();
         }
      }

      if (x0$1 != null) {
         int oid = x0$1._1$mcI$sp();
         Phase ph = (Phase)x0$1._2();
         return scala.collection.StringOps..MODULE$.format$extension("-> owned by %s (until %s)", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{$this.ownerStr(oid), ph}));
      } else {
         throw new MatchError((Object)null);
      }
   }

   // $FF: synthetic method
   static String $anonfun$showAllSymbols$5(final TraceSymbolActivity $this, final int k) {
      Tuple2 var3 = new Tuple2(((Symbols.Symbol)$this.allSymbols().apply(k)).owner().id(), NoPhase$.MODULE$);
      List var10000 = (List)$this.prevOwners().apply(k);
      if (var10000 == null) {
         throw null;
      } else {
         List $colon$colon_this = var10000;
         .colon.colon var23 = new .colon.colon(var3, $colon$colon_this);
         Object var21 = null;
         List map_this = var23;
         Object var24;
         if (map_this == scala.collection.immutable.Nil..MODULE$) {
            var24 = scala.collection.immutable.Nil..MODULE$;
         } else {
            Tuple2 var15 = (Tuple2)map_this.head();
            .colon.colon map_h = new .colon.colon($anonfun$showAllSymbols$6($this, var15), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = ((.colon.colon)map_this).next(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               var15 = (Tuple2)map_rest.head();
               .colon.colon map_nx = new .colon.colon($anonfun$showAllSymbols$6($this, var15), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var24 = map_h;
         }

         map_this = null;
         Object var17 = null;
         Object var18 = null;
         Object var19 = null;
         Object var20 = null;
         List owners = (List)var24;
         String var4 = $this.signature(k);
         .colon.colon var25 = new .colon.colon(var4, owners);
         String mkString_sep = "\n                ";
         AbstractIterable mkString_this = var25;
         String mkString_end = "";
         String mkString_start = "";
         return IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
      }
   }

   // $FF: synthetic method
   static String $anonfun$showAllSymbols$10(final Tuple2 x0$2) {
      if (x0$2 != null) {
         Symbols.Symbol k = (Symbols.Symbol)x0$2._1();
         int v = x0$2._2$mcI$sp();
         return (new StringBuilder(1)).append(v).append("/").append(k).toString();
      } else {
         throw new MatchError((Object)null);
      }
   }

   // $FF: synthetic method
   static String $anonfun$showAllSymbols$8(final TraceSymbolActivity $this, final scala.collection.immutable.Map nameFreq$1, final Names.Name name) {
      scala.collection.StringOps var10000 = scala.collection.StringOps..MODULE$;
      ScalaRunTime var10002 = scala.runtime.ScalaRunTime..MODULE$;
      Object[] var10003 = new Object[]{name.decode(), null};
      Iterable var10006 = (Iterable)nameFreq$1.apply(name);
      Function1 freq_fn = (x$12) -> x$12.owner();
      Iterable freq_xs = var10006;
      List var20 = (List)freq_xs.groupMapReduce(freq_fn, (x$3) -> BoxesRunTime.boxToInteger($anonfun$freq$1(x$3)), (JFunction2.mcIII.sp)(x$4, x$5) -> x$4 + x$5).toList().sortBy((x$6) -> BoxesRunTime.boxToInteger($anonfun$freq$3(x$6)), scala.math.Ordering.Int..MODULE$);
      Object var13 = null;
      Object var14 = null;
      List owners = var20;
      scala.collection.StringOps var21 = scala.collection.StringOps..MODULE$;
      ScalaRunTime var10008 = scala.runtime.ScalaRunTime..MODULE$;
      Object[] var10009 = new Object[2];
      if (owners == null) {
         throw null;
      } else {
         var10009[0] = SeqOps.size$(owners);
         StringBuilder var10012 = new StringBuilder(5);
         List var10013 = owners.take(3);
         if (var10013 == null) {
            throw null;
         } else {
            List map_this = var10013;
            Object var22;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var22 = scala.collection.immutable.Nil..MODULE$;
            } else {
               .colon.colon map_h = new .colon.colon($anonfun$showAllSymbols$10((Tuple2)map_this.head()), scala.collection.immutable.Nil..MODULE$);
               .colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  .colon.colon map_nx = new .colon.colon($anonfun$showAllSymbols$10((Tuple2)map_rest.head()), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var22 = map_h;
            }

            Object var15 = null;
            Object var16 = null;
            Object var17 = null;
            Object var18 = null;
            Object var19 = null;
            String mkString_sep = ", ";
            String var23 = ((IterableOnceOps)var22).mkString("", mkString_sep, "");
            Object var12 = null;
            var10009[1] = var10012.append(var23).append(", ...").toString();
            var10003[1] = var21.format$extension("%4s owners (%s)", var10008.genericWrapArray(var10009));
            return var10000.format$extension("%-15s %s", var10002.genericWrapArray(var10003));
         }
      }
   }

   // $FF: synthetic method
   static void $anonfun$showAllSymbols$11(final TraceSymbolActivity $this, final int id) {
      $this.showIdAndRemove(id);
   }

   static void $init$(final TraceSymbolActivity $this) {
      $this.scala$reflect$internal$util$TraceSymbolActivity$$enabled_$eq($this.global().traceSymbolActivity());
      if ($this.scala$reflect$internal$util$TraceSymbolActivity$$enabled() && $this.global().isCompilerUniverse()) {
         Runtime.getRuntime().addShutdownHook(new Thread(() -> $this.showAllSymbols()));
      }

      $this.scala$reflect$internal$util$TraceSymbolActivity$_setter_$allSymbols_$eq((Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      $this.scala$reflect$internal$util$TraceSymbolActivity$_setter_$allChildren_$eq(((Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$)).withDefaultValue(scala.collection.immutable.Nil..MODULE$));
      $this.scala$reflect$internal$util$TraceSymbolActivity$_setter_$prevOwners_$eq(((Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$)).withDefaultValue(scala.collection.immutable.Nil..MODULE$));
      $this.scala$reflect$internal$util$TraceSymbolActivity$_setter_$allTrees_$eq((scala.collection.mutable.Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   static Object $anonfun$dashes$1$adapted(final Object x$2) {
      return BoxesRunTime.boxToCharacter($anonfun$dashes$1(BoxesRunTime.unboxToChar(x$2)));
   }

   // $FF: synthetic method
   static Object $anonfun$showIdAndRemove$1$adapted(final TraceSymbolActivity $this, final Symbols.Symbol sym) {
      $anonfun$showIdAndRemove$1($this, sym);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$showMapFreq$3$adapted(final TraceSymbolActivity $this, final Function1 showFn$1, final Tuple2 x0$1) {
      $anonfun$showMapFreq$3($this, showFn$1, x0$1);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static String $anonfun$showAllSymbols$3$adapted(final TraceSymbolActivity $this, final Object id) {
      return $anonfun$showAllSymbols$3($this, BoxesRunTime.unboxToInt(id));
   }

   // $FF: synthetic method
   static String $anonfun$showAllSymbols$4$adapted(final TraceSymbolActivity $this, final Object id) {
      return $anonfun$showAllSymbols$4($this, BoxesRunTime.unboxToInt(id));
   }

   // $FF: synthetic method
   static String $anonfun$showAllSymbols$5$adapted(final TraceSymbolActivity $this, final Object k) {
      return $anonfun$showAllSymbols$5($this, BoxesRunTime.unboxToInt(k));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
