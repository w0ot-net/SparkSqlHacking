package scala.collection.concurrent;

import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.StringOps$;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.math.Equiv;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005q4Q!\u0004\b\u0003!QA\u0001B\u000b\u0001\u0003\u0006\u0004%\ta\u000b\u0005\tk\u0001\u0011\t\u0011)A\u0005Y!Aa\u0007\u0001B\u0001B\u0003%q\u0007C\u0003?\u0001\u0011\u0005q\bC\u0003?\u0001\u0011\u00051\tC\u0003?\u0001\u0011\u0005\u0011\nC\u0003T\u0001\u0011\u0005A\u000bC\u0003X\u0001\u0011\u0005\u0001\fC\u0003`\u0001\u0011\u0005\u0001\rC\u0003f\u0001\u0011\u0005a\rC\u0003o\u0001\u0011\u0005q\u000eC\u0003q\u0001\u0011\u0005\u0011OA\u0003M\u001d>$WM\u0003\u0002\u0010!\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005E\u0011\u0012AC2pY2,7\r^5p]*\t1#A\u0003tG\u0006d\u0017-F\u0002\u00169!\u001a\"\u0001\u0001\f\u0011\t]A\"dJ\u0007\u0002\u001d%\u0011\u0011D\u0004\u0002\t\u001b\u0006LgNT8eKB\u00111\u0004\b\u0007\u0001\t\u0015i\u0002A1\u0001 \u0005\u0005Y5\u0001A\t\u0003A\u0011\u0002\"!\t\u0012\u000e\u0003II!a\t\n\u0003\u000f9{G\u000f[5oOB\u0011\u0011%J\u0005\u0003MI\u00111!\u00118z!\tY\u0002\u0006B\u0003*\u0001\t\u0007qDA\u0001W\u0003\u001d)g\u000e\u001e:jKN,\u0012\u0001\f\t\u0004[A\u0012T\"\u0001\u0018\u000b\u0005=\u0002\u0012!C5n[V$\u0018M\u00197f\u0013\t\tdF\u0001\u0003MSN$\b\u0003B\u001145\u001dJ!\u0001\u000e\n\u0003\rQ+\b\u000f\\33\u0003!)g\u000e\u001e:jKN\u0004\u0013!B3rk&4\bc\u0001\u001d<59\u0011\u0011%O\u0005\u0003uI\tq\u0001]1dW\u0006<W-\u0003\u0002={\t)Q)];jm*\u0011!HE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u0001\u000b%\t\u0005\u0003\u0018\u0001i9\u0003\"\u0002\u0016\u0005\u0001\u0004a\u0003\"\u0002\u001c\u0005\u0001\u00049D\u0003\u0002!E\r\"CQ!R\u0003A\u0002i\t\u0011a\u001b\u0005\u0006\u000f\u0016\u0001\raJ\u0001\u0002m\")a'\u0002a\u0001oQ1\u0001I\u0013'O!JCQa\u0013\u0004A\u0002i\t!a[\u0019\t\u000b53\u0001\u0019A\u0014\u0002\u0005Y\f\u0004\"B(\u0007\u0001\u0004Q\u0012AA63\u0011\u0015\tf\u00011\u0001(\u0003\t1(\u0007C\u00037\r\u0001\u0007q'\u0001\u0005j]N,'\u000f^3e)\r\u0001UK\u0016\u0005\u0006\u000b\u001e\u0001\rA\u0007\u0005\u0006\u000f\u001e\u0001\raJ\u0001\be\u0016lwN^3e)\r1\u0012L\u0017\u0005\u0006\u000b\"\u0001\rA\u0007\u0005\u00067\"\u0001\r\u0001X\u0001\u0003GR\u0004BaF/\u001bO%\u0011aL\u0004\u0002\b)JLW-T1q\u0003\r9W\r\u001e\u000b\u0003C\u0012\u00042!\t2(\u0013\t\u0019'C\u0001\u0004PaRLwN\u001c\u0005\u0006\u000b&\u0001\rAG\u0001\u000bG\u0006\u001c\u0007.\u001a3TSj,GCA4k!\t\t\u0003.\u0003\u0002j%\t\u0019\u0011J\u001c;\t\u000bmS\u0001\u0019A6\u0011\u0005\u0005b\u0017BA7\u0013\u0005\u0019\te.\u001f*fM\u0006I1N\\8x]NK'0\u001a\u000b\u0002O\u000611\u000f\u001e:j]\u001e$\"A\u001d>\u0011\u0005MDX\"\u0001;\u000b\u0005U4\u0018\u0001\u00027b]\u001eT\u0011a^\u0001\u0005U\u00064\u0018-\u0003\u0002zi\n11\u000b\u001e:j]\u001eDQa\u001f\u0007A\u0002\u001d\f1\u0001\\3w\u0001"
)
public final class LNode extends MainNode {
   private final List entries;
   private final Equiv equiv;

   public List entries() {
      return this.entries;
   }

   public LNode inserted(final Object k, final Object v) {
      Object var8 = k;
      List var10000 = this.entries();
      Object remove$1_acc = Nil$.MODULE$;
      List remove$1_elems = var10000;

      while(true) {
         if (remove$1_elems.isEmpty()) {
            var10000 = (List)remove$1_acc;
            break;
         }

         if (this.equiv.equiv(((Tuple2)remove$1_elems.head())._1(), k)) {
            var8 = ((Tuple2)remove$1_elems.head())._1();
            var10000 = ((List)remove$1_elems.tail()).$colon$colon$colon((List)remove$1_acc);
            break;
         }

         var10000 = (List)remove$1_elems.tail();
         Tuple2 var7 = (Tuple2)remove$1_elems.head();
         remove$1_acc = new $colon$colon(var7, (List)remove$1_acc);
         remove$1_elems = var10000;
      }

      Object var9 = null;
      remove$1_acc = null;
      Object var11 = null;
      List e = var10000;
      LNode var14 = new LNode;
      Predef.ArrowAssoc$ var10002 = Predef.ArrowAssoc$.MODULE$;
      Tuple2 var4 = new Tuple2(var8, v);
      if (e == null) {
         throw null;
      } else {
         var14.<init>(new $colon$colon(var4, e), this.equiv);
         return var14;
      }
   }

   public MainNode removed(final Object k, final TrieMap ct) {
      List var10000 = this.entries();
      if (var10000 == null) {
         throw null;
      } else {
         List filterNot_this = var10000;
         boolean filterNot_filterCommon_isFlipped = true;
         List filterNot_filterCommon_noneIn$1_l = filterNot_this;

         while(true) {
            if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
               var10000 = Nil$.MODULE$;
               break;
            }

            Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
            List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
            Tuple2 var24 = (Tuple2)filterNot_filterCommon_noneIn$1_h;
            if ($anonfun$removed$1(this, k, var24) != filterNot_filterCommon_isFlipped) {
               List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

               while(true) {
                  if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                     var10000 = filterNot_filterCommon_noneIn$1_l;
                     break;
                  }

                  Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                  var24 = (Tuple2)filterNot_filterCommon_noneIn$1_allIn$1_x;
                  if ($anonfun$removed$1(this, k, var24) == filterNot_filterCommon_isFlipped) {
                     $colon$colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new $colon$colon(filterNot_filterCommon_noneIn$1_l.head(), Nil$.MODULE$);
                     List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                     $colon$colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                     for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                        $colon$colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new $colon$colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), Nil$.MODULE$);
                        filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                        filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                     }

                     List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                     while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                        Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                        var24 = (Tuple2)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                        if ($anonfun$removed$1(this, k, var24) != filterNot_filterCommon_isFlipped) {
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                        } else {
                           while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                              $colon$colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new $colon$colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), Nil$.MODULE$);
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

                     var10000 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                     filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = null;
                     Object var37 = null;
                     Object var40 = null;
                     Object var43 = null;
                     Object var46 = null;
                     Object var49 = null;
                     Object var52 = null;
                     Object var55 = null;
                     break;
                  }

                  filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
               }

               Object var30 = null;
               Object var32 = null;
               Object var35 = null;
               Object var38 = null;
               Object var41 = null;
               Object var44 = null;
               Object var47 = null;
               Object var50 = null;
               Object var53 = null;
               Object var56 = null;
               break;
            }

            filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
         }

         Object var27 = null;
         Object var28 = null;
         Object var29 = null;
         Object var31 = null;
         Object var33 = null;
         Object var36 = null;
         Object var39 = null;
         Object var42 = null;
         Object var45 = null;
         Object var48 = null;
         Object var51 = null;
         Object var54 = null;
         Object var57 = null;
         List filterNot_filterCommon_result = var10000;
         Statics.releaseFence();
         var10000 = filterNot_filterCommon_result;
         filterNot_filterCommon_result = null;
         filterNot_this = null;
         List updmap = var10000;
         IterableOps.SizeCompareOps$ var62 = IterableOps.SizeCompareOps$.MODULE$;
         int $greater$extension_size = 1;
         if (updmap.sizeCompare($greater$extension_size) > 0) {
            return new LNode(updmap, this.equiv);
         } else {
            Tuple2 var4 = (Tuple2)updmap.iterator().next();
            if (var4 != null) {
               Object k = var4._1();
               Object v = var4._2();
               return new TNode(k, v, ct.computeHash(k));
            } else {
               throw new MatchError((Object)null);
            }
         }
      }
   }

   public Option get(final Object k) {
      List var10000 = this.entries();
      if (var10000 == null) {
         throw null;
      } else {
         List find_these = var10000;

         while(true) {
            if (find_these.isEmpty()) {
               var10000 = None$.MODULE$;
               break;
            }

            Tuple2 var4 = (Tuple2)find_these.head();
            if ($anonfun$get$1(this, k, var4)) {
               var10000 = new Some(find_these.head());
               break;
            }

            find_these = (List)find_these.tail();
         }

         Object var5 = null;
         Option map_this = var10000;
         return (Option)(map_this.isEmpty() ? None$.MODULE$ : new Some(((Tuple2)map_this.get())._2()));
      }
   }

   public int cachedSize(final Object ct) {
      List var10000 = this.entries();
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.length();
      }
   }

   public int knownSize() {
      return -1;
   }

   public String string(final int lev) {
      StringBuilder var10000 = (new StringBuilder(0)).append(StringOps$.MODULE$.$times$extension(" ", lev));
      StringOps$ var10001 = StringOps$.MODULE$;
      ScalaRunTime$ var10003 = ScalaRunTime$.MODULE$;
      Object[] var10004 = new Object[1];
      List var10007 = this.entries();
      String mkString_sep = ", ";
      if (var10007 == null) {
         throw null;
      } else {
         AbstractIterable mkString_this = var10007;
         String mkString_end = "";
         String mkString_start = "";
         String var10 = IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
         Object var8 = null;
         Object var9 = null;
         mkString_this = null;
         Object var7 = null;
         var10004[0] = var10;
         return var10000.append(var10001.format$extension("LNode(%s)", var10003.genericWrapArray(var10004))).toString();
      }
   }

   private final List remove$1(final List elems, final List acc, final Object k$3, final ObjectRef k0$1) {
      while(!elems.isEmpty()) {
         if (this.equiv.equiv(((Tuple2)elems.head())._1(), k$3)) {
            k0$1.elem = ((Tuple2)elems.head())._1();
            return ((List)elems.tail()).$colon$colon$colon(acc);
         }

         List var10000 = (List)elems.tail();
         Tuple2 var5 = (Tuple2)elems.head();
         if (acc == null) {
            throw null;
         }

         acc = new $colon$colon(var5, acc);
         elems = var10000;
      }

      return acc;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removed$1(final LNode $this, final Object k$4, final Tuple2 entry) {
      return $this.equiv.equiv(entry._1(), k$4);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$get$1(final LNode $this, final Object k$5, final Tuple2 entry) {
      return $this.equiv.equiv(entry._1(), k$5);
   }

   // $FF: synthetic method
   public static final Object $anonfun$get$2(final Tuple2 x$2) {
      return x$2._2();
   }

   public LNode(final List entries, final Equiv equiv) {
      this.entries = entries;
      this.equiv = equiv;
   }

   public LNode(final Object k, final Object v, final Equiv equiv) {
      Predef.ArrowAssoc$ var10001 = Predef.ArrowAssoc$.MODULE$;
      Tuple2 var4 = new Tuple2(k, v);
      List $colon$colon_this = Nil$.MODULE$;
      $colon$colon var7 = new $colon$colon(var4, $colon$colon_this);
      $colon$colon_this = null;
      this(var7, equiv);
   }

   public LNode(final Object k1, final Object v1, final Object k2, final Object v2, final Equiv equiv) {
      $colon$colon var15;
      if (equiv.equiv(k1, k2)) {
         Predef.ArrowAssoc$ var10001 = Predef.ArrowAssoc$.MODULE$;
         Tuple2 var6 = new Tuple2(k2, v2);
         List $colon$colon_this = Nil$.MODULE$;
         var15 = new $colon$colon(var6, $colon$colon_this);
         $colon$colon_this = null;
      } else {
         Predef.ArrowAssoc$ var16 = Predef.ArrowAssoc$.MODULE$;
         Tuple2 var7 = new Tuple2(k1, v1);
         var16 = Predef.ArrowAssoc$.MODULE$;
         Tuple2 var8 = new Tuple2(k2, v2);
         List $colon$colon_this = Nil$.MODULE$;
         $colon$colon var18 = new $colon$colon(var8, $colon$colon_this);
         $colon$colon_this = null;
         List $colon$colon_this = var18;
         var15 = new $colon$colon(var7, $colon$colon_this);
         $colon$colon_this = null;
      }

      this(var15, equiv);
   }

   // $FF: synthetic method
   public static final Object $anonfun$removed$1$adapted(final LNode $this, final Object k$4, final Tuple2 entry) {
      return BoxesRunTime.boxToBoolean($anonfun$removed$1($this, k$4, entry));
   }

   // $FF: synthetic method
   public static final Object $anonfun$get$1$adapted(final LNode $this, final Object k$5, final Tuple2 entry) {
      return BoxesRunTime.boxToBoolean($anonfun$get$1($this, k$5, entry));
   }
}
