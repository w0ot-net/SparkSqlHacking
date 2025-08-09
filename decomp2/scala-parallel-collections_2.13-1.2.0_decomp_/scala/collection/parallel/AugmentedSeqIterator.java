package scala.collection.parallel;

import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}4\u0001BC\u0006\u0011\u0002\u0007\u0005Q\"\u0005\u0005\u0006O\u0001!\t\u0001\u000b\u0005\u0006Y\u00011\t!\f\u0005\u0006c\u0001!\tA\r\u0005\u0006w\u0001!\t\u0001\u0010\u0005\u0006}\u0001!\ta\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006-\u0002!\ta\u0016\u0005\u0006K\u0002!\tA\u001a\u0005\u0006e\u0002!\ta\u001d\u0002\u0015\u0003V<W.\u001a8uK\u0012\u001cV-]%uKJ\fGo\u001c:\u000b\u00051i\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u00059y\u0011AC2pY2,7\r^5p]*\t\u0001#A\u0003tG\u0006d\u0017-\u0006\u0002\u0013;M\u0019\u0001aE\f\u0011\u0005Q)R\"A\b\n\u0005Yy!AB!osJ+g\rE\u0002\u00193mi\u0011aC\u0005\u00035-\u0011\u0011$Q;h[\u0016tG/\u001a3Ji\u0016\u0014\u0018M\u00197f\u0013R,'/\u0019;peB\u0011A$\b\u0007\u0001\t\u0019q\u0002\u0001\"b\u0001A\t\tAk\u0001\u0001\u0012\u0005\u0005\"\u0003C\u0001\u000b#\u0013\t\u0019sBA\u0004O_RD\u0017N\\4\u0011\u0005Q)\u0013B\u0001\u0014\u0010\u0005\r\te._\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003%\u0002\"\u0001\u0006\u0016\n\u0005-z!\u0001B+oSR\f\u0011B]3nC&t\u0017N\\4\u0016\u00039\u0002\"\u0001F\u0018\n\u0005Az!aA%oi\u0006a\u0001O]3gSbdUM\\4uQR\u0011af\r\u0005\u0006i\r\u0001\r!N\u0001\u0005aJ,G\r\u0005\u0003\u0015mmA\u0014BA\u001c\u0010\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002\u0015s%\u0011!h\u0004\u0002\b\u0005>|G.Z1o\u0003)Ig\u000eZ3y/\",'/\u001a\u000b\u0003]uBQ\u0001\u000e\u0003A\u0002U\na\u0002\\1ti&sG-\u001a=XQ\u0016\u0014X\r\u0006\u0002/\u0001\")A'\u0002a\u0001k\u0005Y1m\u001c:sKN\u0004xN\u001c3t+\t\u0019u\n\u0006\u0002E#R\u0011\u0001(\u0012\u0005\u0006\r\u001a\u0001\raR\u0001\u0005i\"\fG\u000fE\u0002I\u0017:s!\u0001F%\n\u0005){\u0011a\u00029bG.\fw-Z\u0005\u0003\u00196\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003\u0015>\u0001\"\u0001H(\u0005\u000bA3!\u0019\u0001\u0011\u0003\u0003MCQA\u0015\u0004A\u0002M\u000bAaY8seB)A\u0003V\u000eOq%\u0011Qk\u0004\u0002\n\rVt7\r^5p]J\n\u0001C]3wKJ\u001cXMM2p[\nLg.\u001a:\u0016\u0007ak\u0016\r\u0006\u0002ZGB!\u0001D\u0017/a\u0013\tY6B\u0001\u0005D_6\u0014\u0017N\\3s!\taR\fB\u0003_\u000f\t\u0007qLA\u0001V#\tYB\u0005\u0005\u0002\u001dC\u0012)!m\u0002b\u0001A\t!A\u000b[5t\u0011\u0015!w\u00011\u0001Z\u0003\t\u0019'-A\nsKZ,'o]3NCB\u00144m\\7cS:,'/F\u0002hU2$2\u0001\u001b8r!\u0011A\",[6\u0011\u0005qQG!\u0002)\t\u0005\u0004\u0001\u0003C\u0001\u000fm\t\u0015i\u0007B1\u0001!\u0005\u0011!\u0006.\u0019;\t\u000b=D\u0001\u0019\u00019\u0002\u0003\u0019\u0004B\u0001\u0006\u001c\u001cS\")A\r\u0003a\u0001Q\u0006\u0001R\u000f\u001d3bi\u0016$'gY8nE&tWM]\u000b\u0004i^LH\u0003B;{yz\u0004B\u0001\u0007.wqB\u0011Ad\u001e\u0003\u0006=&\u0011\ra\u0018\t\u00039e$Q!\\\u0005C\u0002\u0001BQa_\u0005A\u00029\nQ!\u001b8eKbDQ!`\u0005A\u0002Y\fA!\u001a7f[\")A-\u0003a\u0001k\u0002"
)
public interface AugmentedSeqIterator extends AugmentedIterableIterator {
   int remaining();

   // $FF: synthetic method
   static int prefixLength$(final AugmentedSeqIterator $this, final Function1 pred) {
      return $this.prefixLength(pred);
   }

   default int prefixLength(final Function1 pred) {
      int total = 0;
      boolean loop = true;

      while(this.hasNext() && loop) {
         if (BoxesRunTime.unboxToBoolean(pred.apply(this.next()))) {
            ++total;
         } else {
            loop = false;
         }
      }

      return total;
   }

   // $FF: synthetic method
   static int indexWhere$(final AugmentedSeqIterator $this, final Function1 pred) {
      return $this.indexWhere(pred);
   }

   default int indexWhere(final Function1 pred) {
      int i = 0;
      boolean loop = true;

      while(this.hasNext() && loop) {
         if (BoxesRunTime.unboxToBoolean(pred.apply(this.next()))) {
            loop = false;
         } else {
            ++i;
         }
      }

      return loop ? -1 : i;
   }

   // $FF: synthetic method
   static int lastIndexWhere$(final AugmentedSeqIterator $this, final Function1 pred) {
      return $this.lastIndexWhere(pred);
   }

   default int lastIndexWhere(final Function1 pred) {
      int pos = -1;

      for(int i = 0; this.hasNext(); ++i) {
         if (BoxesRunTime.unboxToBoolean(pred.apply(this.next()))) {
            pos = i;
         }
      }

      return pos;
   }

   // $FF: synthetic method
   static boolean corresponds$(final AugmentedSeqIterator $this, final Function2 corr, final Iterator that) {
      return $this.corresponds(corr, that);
   }

   default boolean corresponds(final Function2 corr, final Iterator that) {
      while(true) {
         if (this.hasNext() && that.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(corr.apply(this.next(), that.next()))) {
               continue;
            }

            return false;
         }

         return this.hasNext() == that.hasNext();
      }
   }

   // $FF: synthetic method
   static Combiner reverse2combiner$(final AugmentedSeqIterator $this, final Combiner cb) {
      return $this.reverse2combiner(cb);
   }

   default Combiner reverse2combiner(final Combiner cb) {
      if (this.isRemainingCheap()) {
         cb.sizeHint(this.remaining());
      }

      List lst;
      for(lst = .MODULE$; this.hasNext(); lst = lst.$colon$colon(this.next())) {
      }

      while(true) {
         Nil var3 = .MODULE$;
         if (lst == null) {
            if (var3 == null) {
               break;
            }
         } else if (lst.equals(var3)) {
            break;
         }

         cb.$plus$eq(lst.head());
         lst = (List)lst.tail();
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner reverseMap2combiner$(final AugmentedSeqIterator $this, final Function1 f, final Combiner cb) {
      return $this.reverseMap2combiner(f, cb);
   }

   default Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
      if (this.isRemainingCheap()) {
         cb.sizeHint(this.remaining());
      }

      List lst;
      for(lst = .MODULE$; this.hasNext(); lst = lst.$colon$colon(f.apply(this.next()))) {
      }

      while(true) {
         Nil var4 = .MODULE$;
         if (lst == null) {
            if (var4 == null) {
               break;
            }
         } else if (lst.equals(var4)) {
            break;
         }

         cb.$plus$eq(lst.head());
         lst = (List)lst.tail();
      }

      return cb;
   }

   // $FF: synthetic method
   static Combiner updated2combiner$(final AugmentedSeqIterator $this, final int index, final Object elem, final Combiner cb) {
      return $this.updated2combiner(index, elem, cb);
   }

   default Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
      if (this.isRemainingCheap()) {
         cb.sizeHint(this.remaining());
      }

      for(int j = 0; this.hasNext(); ++j) {
         if (j == index) {
            cb.$plus$eq(elem);
            this.next();
         } else {
            cb.$plus$eq(this.next());
         }
      }

      return cb;
   }

   static void $init$(final AugmentedSeqIterator $this) {
   }
}
