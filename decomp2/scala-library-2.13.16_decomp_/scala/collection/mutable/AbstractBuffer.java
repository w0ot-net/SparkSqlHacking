package scala.collection.mutable;

import scala.Function1;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2QAA\u0002\u0002\u0002)AQa\b\u0001\u0005\u0002\u0001\u0012a\"\u00112tiJ\f7\r\u001e\"vM\u001a,'O\u0003\u0002\u0005\u000b\u00059Q.\u001e;bE2,'B\u0001\u0004\b\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0011\u0005)1oY1mC\u000e\u0001QCA\u0006\u0013'\r\u0001A\u0002\b\t\u0004\u001b9\u0001R\"A\u0002\n\u0005=\u0019!aC!cgR\u0014\u0018m\u0019;TKF\u0004\"!\u0005\n\r\u0001\u0011)1\u0003\u0001b\u0001)\t\t\u0011)\u0005\u0002\u00163A\u0011acF\u0007\u0002\u000f%\u0011\u0001d\u0002\u0002\b\u001d>$\b.\u001b8h!\t1\"$\u0003\u0002\u001c\u000f\t\u0019\u0011I\\=\u0011\u00075i\u0002#\u0003\u0002\u001f\u0007\t1!)\u001e4gKJ\fa\u0001P5oSRtD#A\u0011\u0011\u00075\u0001\u0001\u0003"
)
public abstract class AbstractBuffer extends AbstractSeq implements Buffer {
   public SeqFactory iterableFactory() {
      return Buffer.iterableFactory$(this);
   }

   public int knownSize() {
      return Buffer.knownSize$(this);
   }

   public final Buffer append(final Object elem) {
      return Buffer.append$(this, (Object)elem);
   }

   /** @deprecated */
   public final Buffer append(final scala.collection.immutable.Seq elems) {
      return Buffer.append$(this, (scala.collection.immutable.Seq)elems);
   }

   public final Buffer appendAll(final IterableOnce elems) {
      return Buffer.appendAll$(this, elems);
   }

   public final Buffer $plus$eq$colon(final Object elem) {
      return Buffer.$plus$eq$colon$(this, elem);
   }

   public Buffer prependAll(final IterableOnce elems) {
      return Buffer.prependAll$(this, elems);
   }

   /** @deprecated */
   public final Buffer prepend(final scala.collection.immutable.Seq elems) {
      return Buffer.prepend$(this, elems);
   }

   public final Buffer $plus$plus$eq$colon(final IterableOnce elems) {
      return Buffer.$plus$plus$eq$colon$(this, elems);
   }

   public Buffer subtractOne(final Object x) {
      return Buffer.subtractOne$(this, x);
   }

   /** @deprecated */
   public void trimStart(final int n) {
      Buffer.trimStart$(this, n);
   }

   /** @deprecated */
   public void trimEnd(final int n) {
      Buffer.trimEnd$(this, n);
   }

   public Buffer dropInPlace(final int n) {
      return Buffer.dropInPlace$(this, n);
   }

   public Buffer dropRightInPlace(final int n) {
      return Buffer.dropRightInPlace$(this, n);
   }

   public Buffer takeInPlace(final int n) {
      return Buffer.takeInPlace$(this, n);
   }

   public Buffer takeRightInPlace(final int n) {
      return Buffer.takeRightInPlace$(this, n);
   }

   public Buffer sliceInPlace(final int start, final int end) {
      return Buffer.sliceInPlace$(this, start, end);
   }

   public Buffer dropWhileInPlace(final Function1 p) {
      return Buffer.dropWhileInPlace$(this, p);
   }

   public Buffer takeWhileInPlace(final Function1 p) {
      return Buffer.takeWhileInPlace$(this, p);
   }

   public Buffer padToInPlace(final int len, final Object elem) {
      return Buffer.padToInPlace$(this, len, elem);
   }

   public String stringPrefix() {
      return Buffer.stringPrefix$(this);
   }

   public final Shrinkable $minus$eq(final Object elem) {
      return Shrinkable.$minus$eq$(this, elem);
   }

   /** @deprecated */
   public Shrinkable $minus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return Shrinkable.$minus$eq$(this, elem1, elem2, elems);
   }

   public Shrinkable subtractAll(final IterableOnce xs) {
      return Shrinkable.subtractAll$(this, xs);
   }

   public final Shrinkable $minus$minus$eq(final IterableOnce xs) {
      return Shrinkable.$minus$minus$eq$(this, xs);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public Growable addAll(final IterableOnce elems) {
      return Growable.addAll$(this, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }
}
