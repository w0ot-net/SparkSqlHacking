package scala.collection;

import scala.None$;
import scala.Option;
import scala.Some;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4qAD\b\u0011\u0002\u0007\u0005A\u0003C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0019\u0005\u0001\u0005C\u00039\u0001\u0019\u0005\u0011\bC\u0003;\u0001\u0019\u0005\u0011\bC\u0003<\u0001\u0011\u0005A\bC\u0003W\u0001\u0019\u0005q\u000bC\u0003c\u0001\u0011\u00051\rC\u0003]\u0001\u0011\u0015a\rC\u0003l\u0001\u0011\u0005A\u000eC\u0003b\u0001\u0011\u0015a\u000eC\u0003t\u0001\u0011\u0005A\u000fC\u0003w\u0001\u0011\u0015q\u000fC\u0003}\u0001\u0019\u0005QPA\u0005T_J$X\rZ(qg*\u0011\u0001#E\u0001\u000bG>dG.Z2uS>t'\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u0019QcL-\u0014\u0005\u00011\u0002CA\f\u0019\u001b\u0005\t\u0012BA\r\u0012\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012\u0001\b\t\u0003/uI!AH\t\u0003\tUs\u0017\u000e^\u0001\t_J$WM]5oOV\t\u0011\u0005E\u0002#U5r!a\t\u0015\u000f\u0005\u0011:S\"A\u0013\u000b\u0005\u0019\u001a\u0012A\u0002\u001fs_>$h(C\u0001\u0013\u0013\tI\u0013#A\u0004qC\u000e\\\u0017mZ3\n\u0005-b#\u0001C(sI\u0016\u0014\u0018N\\4\u000b\u0005%\n\u0002C\u0001\u00180\u0019\u0001!Q\u0001\r\u0001C\u0002E\u0012\u0011!Q\t\u0003eU\u0002\"aF\u001a\n\u0005Q\n\"a\u0002(pi\"Lgn\u001a\t\u0003/YJ!aN\t\u0003\u0007\u0005s\u00170\u0001\u0005gSJ\u001cHoS3z+\u0005i\u0013a\u00027bgR\\U-_\u0001\bG>l\u0007/\u0019:f)\ri\u0004I\u0011\t\u0003/yJ!aP\t\u0003\u0007%sG\u000fC\u0003B\u000b\u0001\u0007Q&\u0001\u0002la!)1)\u0002a\u0001[\u0005\u00111.\r\u0015\u0007\u000b\u0015C\u0015j\u0013'\u0011\u0005]1\u0015BA$\u0012\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005Q\u0015\u0001H+tK\u0002z'\u000fZ3sS:<gfY8na\u0006\u0014X\rI5ogR,\u0017\rZ\u0001\u0006g&t7-Z\u0011\u0002\u001b\u00061!GL\u00194]ABc!B(I\u0013.c\u0005CA\fQ\u0013\t\t\u0016C\u0001\u000beKB\u0014XmY1uK\u0012|e/\u001a:sS\u0012Lgn\u001a\u0015\u0003\u000bM\u0003\"a\u0006+\n\u0005U\u000b\"AB5oY&tW-A\u0005sC:<W-S7qYR\u0019\u0001l\u00171\u0011\u00059JFA\u0002.\u0001\t\u000b\u0007\u0011GA\u0001D\u0011\u0015af\u00011\u0001^\u0003\u00111'o\\7\u0011\u0007]qV&\u0003\u0002`#\t1q\n\u001d;j_:DQ!\u0019\u0004A\u0002u\u000bQ!\u001e8uS2\fQA]1oO\u0016$2\u0001\u00173f\u0011\u0015av\u00011\u0001.\u0011\u0015\tw\u00011\u0001.)\tAv\rC\u0003]\u0011\u0001\u0007Q\u0006\u000b\u0004\t\u000b\"K7\nT\u0011\u0002U\u0006iQk]3!e\u0006tw-\u001a$s_6\f\u0011B]1oO\u00164%o\\7\u0015\u0005ak\u0007\"\u0002/\n\u0001\u0004iCC\u0001-p\u0011\u0015\t'\u00021\u0001.Q\u0019QQ\tS9L\u0019\u0006\n!/\u0001\bVg\u0016\u0004#/\u00198hKVsG/\u001b7\u0002\u0015I\fgnZ3V]RLG\u000e\u0006\u0002Yk\")\u0011m\u0003a\u0001[\u0005\u0011Ao\u001c\u000b\u00031bDQA\u001e\u0007A\u00025Bc\u0001D#Iu.c\u0015%A>\u0002\u0017U\u001bX\r\t:b]\u001e,Gk\\\u0001\be\u0006tw-\u001a+p)\tAf\u0010C\u0003w\u001b\u0001\u0007Q\u0006"
)
public interface SortedOps {
   Ordering ordering();

   Object firstKey();

   Object lastKey();

   // $FF: synthetic method
   static int compare$(final SortedOps $this, final Object k0, final Object k1) {
      return $this.compare(k0, k1);
   }

   /** @deprecated */
   default int compare(final Object k0, final Object k1) {
      return this.ordering().compare(k0, k1);
   }

   Object rangeImpl(final Option from, final Option until);

   // $FF: synthetic method
   static Object range$(final SortedOps $this, final Object from, final Object until) {
      return $this.range(from, until);
   }

   default Object range(final Object from, final Object until) {
      return this.rangeImpl(new Some(from), new Some(until));
   }

   // $FF: synthetic method
   static Object from$(final SortedOps $this, final Object from) {
      return $this.from(from);
   }

   /** @deprecated */
   default Object from(final Object from) {
      return this.rangeFrom(from);
   }

   // $FF: synthetic method
   static Object rangeFrom$(final SortedOps $this, final Object from) {
      return $this.rangeFrom(from);
   }

   default Object rangeFrom(final Object from) {
      return this.rangeImpl(new Some(from), None$.MODULE$);
   }

   // $FF: synthetic method
   static Object until$(final SortedOps $this, final Object until) {
      return $this.until(until);
   }

   /** @deprecated */
   default Object until(final Object until) {
      return this.rangeUntil(until);
   }

   // $FF: synthetic method
   static Object rangeUntil$(final SortedOps $this, final Object until) {
      return $this.rangeUntil(until);
   }

   default Object rangeUntil(final Object until) {
      return this.rangeImpl(None$.MODULE$, new Some(until));
   }

   // $FF: synthetic method
   static Object to$(final SortedOps $this, final Object to) {
      return $this.to(to);
   }

   /** @deprecated */
   default Object to(final Object to) {
      return this.rangeTo(to);
   }

   Object rangeTo(final Object to);

   static void $init$(final SortedOps $this) {
   }
}
