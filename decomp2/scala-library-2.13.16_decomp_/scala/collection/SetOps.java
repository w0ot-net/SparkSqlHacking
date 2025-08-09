package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Array$;
import scala.Function1;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mha\u0002\u000f\u001e!\u0003\r\tA\t\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001b\u00021\tA\u0014\u0005\u0006#\u0002!)A\u0015\u0005\u00061\u0002!\t!\u0017\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006?\u0002!\t!\u001b\u0004\u0005U\u0002!1\u000e\u0003\u0005p\u000f\t\u0005\t\u0015!\u0003q\u0011!)wA!A!\u0002\u00131\u0007\"B:\b\t\u0003!\bBB=\bA\u0003%!\u0010\u0003\u0004~\u000f\u0001\u0006K!\u0012\u0005\u0006}\u001e!\ta \u0005\b\u0003\u00039A\u0011AA\u0002\u0011\u001d\tI\u0006\u0001C\u0001\u00037Bq!a\u0018\u0001\t\u000b\t\t\u0007C\u0004\u0002h\u00011\t!!\u001b\t\u000f\u00055\u0004\u0001\"\u0002\u0002p!9\u0011Q\u000f\u0001\u0005\u0002\u0005]\u0004bBAK\u0001\u0011\u0005\u0011q\u0013\u0005\b\u0003+\u0003A\u0011AAQ\u0011\u001d\tY\f\u0001C\u0001\u0003{Cq!!1\u0001\t\u0003\t\u0019\rC\u0004\u0002B\u0002!\t!!4\t\u000f\u0005m\u0007\u0001\"\u0002\u0002^\"9\u00111\u001d\u0001\u0005\u0006\u0005\u0015\bbBAv\u0001\u0011\u0015\u0011Q\u001e\u0002\u0007'\u0016$x\n]:\u000b\u0005yy\u0012AC2pY2,7\r^5p]*\t\u0001%A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\t\rr\u0003HP\n\u0005\u0001\u0011B#\t\u0005\u0002&M5\tq$\u0003\u0002(?\t1\u0011I\\=SK\u001a\u0004R!\u000b\u0016-ouj\u0011!H\u0005\u0003Wu\u00111\"\u0013;fe\u0006\u0014G.Z(qgB\u0011QF\f\u0007\u0001\t\u0015y\u0003A1\u00011\u0005\u0005\t\u0015CA\u00195!\t)#'\u0003\u00024?\t9aj\u001c;iS:<\u0007CA\u00136\u0013\t1tDA\u0002B]f\u0004\"!\f\u001d\u0005\re\u0002AQ1\u0001;\u0005\t\u00195)\u0006\u00021w\u0011)A\b\u000fb\u0001a\t!q\f\n\u00132!\tic\b\u0002\u0004@\u0001\u0011\u0015\r\u0001\u0011\u0002\u0002\u0007F\u0011\u0011'\u0011\t\u0006S\u0001as'\u0010\t\u0005K\rcS)\u0003\u0002E?\tIa)\u001e8di&|g.\r\t\u0003K\u0019K!aR\u0010\u0003\u000f\t{w\u000e\\3b]\u00061A%\u001b8ji\u0012\"\u0012A\u0013\t\u0003K-K!\u0001T\u0010\u0003\tUs\u0017\u000e^\u0001\tG>tG/Y5ogR\u0011Qi\u0014\u0005\u0006!\n\u0001\r\u0001L\u0001\u0005K2,W.A\u0003baBd\u0017\u0010\u0006\u0002F'\")\u0001k\u0001a\u0001Y!\u00121!\u0016\t\u0003KYK!aV\u0010\u0003\r%tG.\u001b8f\u0003!\u0019XOY:fi>3GCA#[\u0011\u0015YF\u00011\u0001]\u0003\u0011!\b.\u0019;\u0011\u0007%jF&\u0003\u0002_;\t\u00191+\u001a;\u0002\u000fM,(m]3ugR\u0011\u0011\r\u001a\t\u0004S\tl\u0014BA2\u001e\u0005!IE/\u001a:bi>\u0014\b\"B3\u0006\u0001\u00041\u0017a\u00017f]B\u0011QeZ\u0005\u0003Q~\u00111!\u00138u)\u0005\t'AC*vEN,Go]%ueN\u0011q\u0001\u001c\t\u0004S5l\u0014B\u00018\u001e\u0005A\t%m\u001d;sC\u000e$\u0018\n^3sCR|'/\u0001\u0003fY6\u001c\bcA\u0015rY%\u0011!/\b\u0002\u000b\u0013:$W\r_3e'\u0016\f\u0018A\u0002\u001fj]&$h\bF\u0002vob\u0004\"A^\u0004\u000e\u0003\u0001AQa\u001c\u0006A\u0002ADQ!\u001a\u0006A\u0002\u0019\fA!\u001b3ygB\u0019Qe\u001f4\n\u0005q|\"!B!se\u0006L\u0018\u0001C0iCNtU\r\u001f;\u0002\u000f!\f7OT3yiV\tQ)\u0001\u0003oKb$H#A\u001f)\u000b9\t9!a\u0007\u0011\u000b\u0015\nI!!\u0004\n\u0007\u0005-qD\u0001\u0004uQJ|wo\u001d\t\u0005\u0003\u001f\t)BD\u0002&\u0003#I1!a\u0005 \u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u0006\u0002\u001a\t1bj\\*vG\",E.Z7f]R,\u0005pY3qi&|gNC\u0002\u0002\u0014}\ttAHA\u000f\u0003g\t9\u0006\u0005\u0003\u0002 \u00055b\u0002BA\u0011\u0003S\u00012!a\t \u001b\t\t)CC\u0002\u0002(\u0005\na\u0001\u0010:p_Rt\u0014bAA\u0016?\u00051\u0001K]3eK\u001aLA!a\f\u00022\t11\u000b\u001e:j]\u001eT1!a\u000b c%\u0019\u0013QGA\u001f\u0003\u001b\ny$\u0006\u0003\u00028\u0005eRCAA\u000f\t\u001d\tY$\tb\u0001\u0003\u000b\u0012\u0011\u0001V\u0005\u0005\u0003\u007f\t\t%A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%\r\u0006\u0004\u0003\u0007z\u0012A\u0002;ie><8/E\u00022\u0003\u000f\u0002B!a\u0004\u0002J%!\u00111JA\r\u0005%!\u0006N]8xC\ndW-M\u0005$\u0003\u001f\n\t&a\u0015\u0002D9\u0019Q%!\u0015\n\u0007\u0005\rs$M\u0003#K}\t)FA\u0003tG\u0006d\u0017-M\u0002'\u0003\u001b\t\u0011\"\u001b8uKJ\u001cXm\u0019;\u0015\u0007u\ni\u0006C\u0003\\\u001f\u0001\u0007A,\u0001\u0003%C6\u0004HcA\u001f\u0002d!)1\f\u0005a\u00019\"\u0012\u0001#V\u0001\u0005I&4g\rF\u0002>\u0003WBQaW\tA\u0002q\u000b!\u0002J1na\u0012\"\u0018\u000e\u001c3f)\ri\u0014\u0011\u000f\u0005\u00067J\u0001\r\u0001\u0018\u0015\u0003%U\u000bA\u0002J7j]V\u001cH%\\5okN$2!PA=\u0011\u0019Y6\u00031\u0001\u0002|A!\u0011&! -\u0013\r\ty(\b\u0002\r\u0013R,'/\u00192mK>s7-\u001a\u0015\f'\u0005\r\u0015\u0011RAF\u0003\u001f\u000b\t\nE\u0002&\u0003\u000bK1!a\" \u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\ti)A\u0012D_:\u001c\u0018\u000eZ3sAI,\u0017/^5sS:<\u0007%\u00198!S6lW\u000f^1cY\u0016\u00043+\u001a;\u0002\u000bMLgnY3\"\u0005\u0005M\u0015A\u0002\u001a/cMr\u0003'\u0001\u0004%[&tWo\u001d\u000b\u0004{\u0005e\u0005\"\u0002)\u0015\u0001\u0004a\u0003f\u0003\u000b\u0002\u0004\u0006%\u0015QTAH\u0003#\u000b#!a(\u0002y\r{gn]5eKJ\u0004#/Z9vSJLgn\u001a\u0011b]\u0002JW.\\;uC\ndW\rI*fi\u0002z'\u000f\t4bY2\u0004#-Y2lAQ|\u0007eU3u]\u0011LgM\u001a\u000b\b{\u0005\r\u0016qUAV\u0011\u0019\t)+\u0006a\u0001Y\u0005)Q\r\\3nc!1\u0011\u0011V\u000bA\u00021\nQ!\u001a7f[JBq!!,\u0016\u0001\u0004\ty+A\u0003fY\u0016l7\u000f\u0005\u0003&\u0003cc\u0013bAAZ?\tQAH]3qK\u0006$X\r\u001a )\u0017U\t\u0019)!#\u00028\u0006=\u0015\u0011S\u0011\u0003\u0003s\u000bQ)V:fA\u0019j\u0003e^5uQ\u0002\ng\u000eI3ya2L7-\u001b;!G>dG.Z2uS>t\u0007%\u0019:hk6,g\u000e\u001e\u0011j]N$X-\u00193!_\u001a\u0004S\u0006I<ji\"\u0004c/\u0019:be\u001e\u001c\u0018AB2p]\u000e\fG\u000fF\u0002>\u0003\u007fCaa\u0017\fA\u0002\u0005m\u0014!\u0002\u0013qYV\u001cHcA\u001f\u0002F\")\u0001k\u0006a\u0001Y!Zq#a!\u0002\n\u0006%\u0017qRAIC\t\tY-A\u001fD_:\u001c\u0018\u000eZ3sAI,\u0017/^5sS:<\u0007%\u00198!S6lW\u000f^1cY\u0016\u00043+\u001a;!_J\u0004c-\u00197mA\t\f7m\u001b\u0011u_\u0002\u001aV\r\u001e\u0018v]&|g\u000eF\u0004>\u0003\u001f\f\t.a5\t\r\u0005\u0015\u0006\u00041\u0001-\u0011\u0019\tI\u000b\u0007a\u0001Y!9\u0011Q\u0016\rA\u0002\u0005=\u0006f\u0003\r\u0002\u0004\u0006%\u0015q[AH\u0003#\u000b#!!7\u0002\u000bV\u001bX\rI\u0016,A]LG\u000f\u001b\u0011b]\u0002*\u0007\u0010\u001d7jG&$\beY8mY\u0016\u001cG/[8oA\u0005\u0014x-^7f]R\u0004\u0013N\\:uK\u0006$\u0007e\u001c4!W\u0001:\u0018\u000e\u001e5!m\u0006\u0014\u0018M]4t\u0003)!\u0003\u000f\\;tIAdWo\u001d\u000b\u0004{\u0005}\u0007BB.\u001a\u0001\u0004\tY\b\u000b\u0002\u001a+\u0006)QO\\5p]R\u0019Q(a:\t\u000bmS\u0002\u0019\u0001/)\u0005i)\u0016\u0001\u0002\u0013cCJ$2!PAx\u0011\u0015Y6\u00041\u0001]Q\tYR\u000b"
)
public interface SetOps extends IterableOps, Function1 {
   boolean contains(final Object elem);

   // $FF: synthetic method
   static boolean apply$(final SetOps $this, final Object elem) {
      return $this.apply(elem);
   }

   default boolean apply(final Object elem) {
      return this.contains(elem);
   }

   // $FF: synthetic method
   static boolean subsetOf$(final SetOps $this, final Set that) {
      return $this.subsetOf(that);
   }

   default boolean subsetOf(final Set that) {
      return this.forall(that);
   }

   // $FF: synthetic method
   static Iterator subsets$(final SetOps $this, final int len) {
      return $this.subsets(len);
   }

   default Iterator subsets(final int len) {
      if (len >= 0 && len <= this.size()) {
         IterableFactory$ var10004 = IterableFactory$.MODULE$;
         IterableFactory toFactory_factory = IndexedSeq$.MODULE$;
         IterableFactory.ToFactory var4 = new IterableFactory.ToFactory(toFactory_factory);
         toFactory_factory = null;
         return new SubsetsItr((IndexedSeq)this.to(var4), len);
      } else {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      }
   }

   // $FF: synthetic method
   static Iterator subsets$(final SetOps $this) {
      return $this.subsets();
   }

   default Iterator subsets() {
      return new AbstractIterator() {
         private final IndexedSeq elms;
         private int len;
         private Iterator itr;
         // $FF: synthetic field
         private final SetOps $outer;

         public boolean hasNext() {
            int var10000 = this.len;
            IndexedSeq var10001 = this.elms;
            if (var10001 == null) {
               throw null;
            } else {
               return var10000 <= var10001.length() || this.itr.hasNext();
            }
         }

         public SetOps next() {
            if (!this.itr.hasNext()) {
               int var10000 = this.len;
               IndexedSeq var10001 = this.elms;
               if (var10001 == null) {
                  throw null;
               }

               if (var10000 > var10001.length()) {
                  Iterator$ var1 = Iterator$.MODULE$;
                  Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.itr = this.$outer.new SubsetsItr(this.elms, this.len);
                  ++this.len;
               }
            }

            return (SetOps)this.itr.next();
         }

         public {
            if (SetOps.this == null) {
               throw null;
            } else {
               this.$outer = SetOps.this;
               IterableFactory$ var10002 = IterableFactory$.MODULE$;
               IterableFactory toFactory_factory = IndexedSeq$.MODULE$;
               IterableFactory.ToFactory var4 = new IterableFactory.ToFactory(toFactory_factory);
               toFactory_factory = null;
               this.elms = (IndexedSeq)SetOps.this.to(var4);
               this.len = 0;
               Iterator$ var10001 = Iterator$.MODULE$;
               this.itr = Iterator$.scala$collection$Iterator$$_empty;
            }
         }
      };
   }

   // $FF: synthetic method
   static SetOps intersect$(final SetOps $this, final Set that) {
      return $this.intersect(that);
   }

   default SetOps intersect(final Set that) {
      return (SetOps)this.filter(that);
   }

   // $FF: synthetic method
   static SetOps $amp$(final SetOps $this, final Set that) {
      return $this.$amp(that);
   }

   default SetOps $amp(final Set that) {
      return this.intersect(that);
   }

   SetOps diff(final Set that);

   // $FF: synthetic method
   static SetOps $amp$tilde$(final SetOps $this, final Set that) {
      return $this.$amp$tilde(that);
   }

   default SetOps $amp$tilde(final Set that) {
      return this.diff(that);
   }

   // $FF: synthetic method
   static SetOps $minus$minus$(final SetOps $this, final IterableOnce that) {
      return $this.$minus$minus(that);
   }

   /** @deprecated */
   default SetOps $minus$minus(final IterableOnce that) {
      Iterator var10000 = that.iterator();
      IterableFactory$ var10001 = IterableFactory$.MODULE$;
      IterableFactory toFactory_factory = scala.collection.immutable.Set$.MODULE$;
      IterableFactory.ToFactory var5 = new IterableFactory.ToFactory(toFactory_factory);
      toFactory_factory = null;
      scala.collection.immutable.Set toRemove = (scala.collection.immutable.Set)var10000.to(var5);
      return (SetOps)this.fromSpecific((IterableOnce)this.view().filterNot(toRemove));
   }

   // $FF: synthetic method
   static SetOps $minus$(final SetOps $this, final Object elem) {
      return $this.$minus(elem);
   }

   /** @deprecated */
   default SetOps $minus(final Object elem) {
      return this.diff((Set)Set$.MODULE$.apply(ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{elem})));
   }

   // $FF: synthetic method
   static SetOps $minus$(final SetOps $this, final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return $this.$minus(elem1, elem2, elems);
   }

   /** @deprecated */
   default SetOps $minus(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      scala.collection.immutable.SetOps var10001 = elems.toSet();
      if (var10001 == null) {
         throw null;
      } else {
         var10001 = var10001.incl(elem1);
         if (var10001 == null) {
            throw null;
         } else {
            return this.diff((Set)var10001.incl(elem2));
         }
      }
   }

   // $FF: synthetic method
   static SetOps concat$(final SetOps $this, final IterableOnce that) {
      return $this.concat(that);
   }

   default SetOps concat(final IterableOnce that) {
      if (this instanceof scala.collection.immutable.Set.Set1 ? true : (this instanceof scala.collection.immutable.Set.Set2 ? true : (this instanceof scala.collection.immutable.Set.Set3 ? true : this instanceof scala.collection.immutable.Set.Set4))) {
         scala.collection.immutable.SetOps result = (scala.collection.immutable.SetOps)this;

         scala.collection.immutable.SetOps var10000;
         for(Iterator it = that.iterator(); it.hasNext(); result = var10000) {
            Object $plus_elem = it.next();
            if (result == null) {
               throw null;
            }

            var10000 = result.incl($plus_elem);
            $plus_elem = null;
         }

         return result;
      } else {
         Object var10001;
         if (that instanceof Iterable) {
            Iterable var4 = (Iterable)that;
            var10001 = new View.Concat(this, var4);
         } else {
            var10001 = this.iterator().concat(() -> that.iterator());
         }

         return (SetOps)this.fromSpecific((IterableOnce)var10001);
      }
   }

   // $FF: synthetic method
   static SetOps $plus$(final SetOps $this, final Object elem) {
      return $this.$plus(elem);
   }

   /** @deprecated */
   default SetOps $plus(final Object elem) {
      return (SetOps)this.fromSpecific(new View.Appended(this, elem));
   }

   // $FF: synthetic method
   static SetOps $plus$(final SetOps $this, final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return $this.$plus(elem1, elem2, elems);
   }

   /** @deprecated */
   default SetOps $plus(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return (SetOps)this.fromSpecific(new View.Concat(new View.Appended(new View.Appended(this, elem1), elem2), elems));
   }

   // $FF: synthetic method
   static SetOps $plus$plus$(final SetOps $this, final IterableOnce that) {
      return $this.$plus$plus(that);
   }

   default SetOps $plus$plus(final IterableOnce that) {
      return this.concat(that);
   }

   // $FF: synthetic method
   static SetOps union$(final SetOps $this, final Set that) {
      return $this.union(that);
   }

   default SetOps union(final Set that) {
      return this.concat(that);
   }

   // $FF: synthetic method
   static SetOps $bar$(final SetOps $this, final Set that) {
      return $this.$bar(that);
   }

   default SetOps $bar(final Set that) {
      return this.concat(that);
   }

   static void $init$(final SetOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private class SubsetsItr extends AbstractIterator {
      private final IndexedSeq elms;
      private final int len;
      private final int[] idxs;
      private boolean _hasNext;
      // $FF: synthetic field
      public final SetOps $outer;

      public boolean hasNext() {
         return this._hasNext;
      }

      public SetOps next() throws NoSuchElementException {
         if (!this.hasNext()) {
            Iterator$ var10000 = Iterator$.MODULE$;
            Iterator$.scala$collection$Iterator$$_empty.next();
         }

         Builder buf = this.scala$collection$SetOps$SubsetsItr$$$outer().newSpecificBuilder();

         for(Object var11 : (int[])ArrayOps$.MODULE$.slice$extension(this.idxs, 0, this.len)) {
            $anonfun$next$1(this, buf, (int)var11);
         }

         Object var12 = null;
         SetOps result = (SetOps)buf.result();

         int i;
         for(i = this.len - 1; i >= 0 && this.idxs[i] == this.idxs[i + 1] - 1; --i) {
         }

         if (i < 0) {
            this._hasNext = false;
         } else {
            int var10002 = this.idxs[i]++;
            RichInt$ var13 = RichInt$.MODULE$;
            int var4 = i + 1;
            int until$extension_end = this.len;
            Range$ var14 = Range$.MODULE$;
            Range foreach$mVc$sp_this = new Range.Exclusive(var4, until$extension_end, 1);
            if (!foreach$mVc$sp_this.isEmpty()) {
               int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

               while(true) {
                  $anonfun$next$2(this, foreach$mVc$sp_i);
                  if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
                     break;
                  }

                  foreach$mVc$sp_i += foreach$mVc$sp_this.step();
               }
            }
         }

         return result;
      }

      // $FF: synthetic method
      public SetOps scala$collection$SetOps$SubsetsItr$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Builder $anonfun$next$1(final SubsetsItr $this, final Builder buf$1, final int idx) {
         Object $plus$eq_elem = $this.elms.apply(idx);
         if (buf$1 == null) {
            throw null;
         } else {
            return (Builder)buf$1.addOne($plus$eq_elem);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$next$2(final SubsetsItr $this, final int j) {
         $this.idxs[j] = $this.idxs[j - 1] + 1;
      }

      public SubsetsItr(final IndexedSeq elms, final int len) {
         this.elms = elms;
         this.len = len;
         if (SetOps.this == null) {
            throw null;
         } else {
            this.$outer = SetOps.this;
            super();
            this.idxs = Array$.MODULE$.range(0, len + 1, 1);
            this._hasNext = true;
            int[] var10000 = this.idxs;
            if (elms == null) {
               throw null;
            } else {
               var10000[len] = elms.length();
            }
         }
      }

      // $FF: synthetic method
      public static final Builder $anonfun$next$1$adapted(final SubsetsItr $this, final Builder buf$1, final Object idx) {
         return $anonfun$next$1($this, buf$1, BoxesRunTime.unboxToInt(idx));
      }
   }
}
