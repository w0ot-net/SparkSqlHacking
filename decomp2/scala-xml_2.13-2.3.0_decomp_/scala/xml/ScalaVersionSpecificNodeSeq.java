package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.collection.IterableOnce;
import scala.collection.View;
import scala.collection.immutable.Seq;
import scala.collection.immutable.StrictOptimizedSeqOps;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M4\u0011\"\u0004\b\u0011\u0002\u0007\u0005aBE\u0014\t\u000b5\u0002A\u0011A\u0018\t\u000bM\u0002A\u0011\t\u001b\t\u000bi\u0002A\u0011I\u001e\t\u000b\t\u0003A\u0011I\"\t\u000b\u0011\u0003A\u0011A#\t\u000b!\u0003AQA%\t\u000bi\u0003A\u0011A.\t\u000by\u0003A\u0011A0\t\u000b\u0005\u0004A\u0011\u00012\t\u000b\u0011\u0004A\u0011A3\t\u000b!\u0004A\u0011A5\t\u000b=\u0004A\u0011\u00019\u00037M\u001b\u0017\r\\1WKJ\u001c\u0018n\u001c8Ta\u0016\u001c\u0017NZ5d\u001d>$WmU3r\u0015\ty\u0001#A\u0002y[2T\u0011!E\u0001\u0006g\u000e\fG.Y\n\u0005\u0001M9\"\u0006\u0005\u0002\u0015+5\t\u0001#\u0003\u0002\u0017!\t1\u0011I\\=SK\u001a\u0004R\u0001G\u000e\u001eC\u001dj\u0011!\u0007\u0006\u00035A\t!bY8mY\u0016\u001cG/[8o\u0013\ta\u0012D\u0001\u0004TKF|\u0005o\u001d\t\u0003=}i\u0011AD\u0005\u0003A9\u0011AAT8eKB\u0011!%J\u0007\u0002G)\u0011A%G\u0001\nS6lW\u000f^1cY\u0016L!AJ\u0012\u0003\u0007M+\u0017\u000f\u0005\u0002\u001fQ%\u0011\u0011F\u0004\u0002\b\u001d>$WmU3r!\u0015\u00113&H\u0011(\u0013\ta3EA\u000bTiJL7\r^(qi&l\u0017N_3e'\u0016\fx\n]:\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012\u0001\r\t\u0003)EJ!A\r\t\u0003\tUs\u0017\u000e^\u0001\rMJ|Wn\u00159fG&4\u0017n\u0019\u000b\u0003OUBQA\u000e\u0002A\u0002]\nAaY8mYB\u0019\u0001\u0004O\u000f\n\u0005eJ\"\u0001D%uKJ\f'\r\\3P]\u000e,\u0017A\u00058foN\u0003XmY5gS\u000e\u0014U/\u001b7eKJ,\u0012\u0001\u0010\t\u0005{\u0001kr%D\u0001?\u0015\ty\u0014$A\u0004nkR\f'\r\\3\n\u0005\u0005s$a\u0002\"vS2$WM]\u0001\u0006K6\u0004H/_\u000b\u0002O\u000511m\u001c8dCR$\"a\n$\t\u000b\u001d+\u0001\u0019A\u001c\u0002\rM,hMZ5y\u0003)!\u0003\u000f\\;tIAdWo\u001d\u000b\u0003O)CQa\u0012\u0004A\u0002-\u00032\u0001\u0014+\u001e\u001d\ti%K\u0004\u0002O#6\tqJ\u0003\u0002Q]\u00051AH]8pizJ\u0011!E\u0005\u0003'B\tq\u0001]1dW\u0006<W-\u0003\u0002'+*\u00111\u000b\u0005\u0015\u0003\r]\u0003\"\u0001\u0006-\n\u0005e\u0003\"AB5oY&tW-\u0001\u0005baB,g\u000eZ3e)\t9C\fC\u0003^\u000f\u0001\u0007Q$\u0001\u0003cCN,\u0017aC1qa\u0016tG-\u001a3BY2$\"a\n1\t\u000b\u001dC\u0001\u0019A\u001c\u0002\u0013A\u0014X\r]3oI\u0016$GCA\u0014d\u0011\u0015i\u0016\u00021\u0001\u001e\u00031\u0001(/\u001a9f]\u0012,G-\u00117m)\t9c\rC\u0003h\u0015\u0001\u0007q'\u0001\u0004qe\u00164\u0017\u000e_\u0001\u0004[\u0006\u0004HCA\u0014k\u0011\u0015Y7\u00021\u0001m\u0003\u00051\u0007\u0003\u0002\u000bn;uI!A\u001c\t\u0003\u0013\u0019+hn\u0019;j_:\f\u0014a\u00024mCRl\u0015\r\u001d\u000b\u0003OEDQa\u001b\u0007A\u0002I\u0004B\u0001F7\u001eo\u0001"
)
public interface ScalaVersionSpecificNodeSeq extends StrictOptimizedSeqOps {
   // $FF: synthetic method
   static NodeSeq fromSpecific$(final ScalaVersionSpecificNodeSeq $this, final IterableOnce coll) {
      return $this.fromSpecific(coll);
   }

   default NodeSeq fromSpecific(final IterableOnce coll) {
      return (NodeSeq)((Builder)NodeSeq$.MODULE$.newBuilder().$plus$plus$eq(coll)).result();
   }

   // $FF: synthetic method
   static Builder newSpecificBuilder$(final ScalaVersionSpecificNodeSeq $this) {
      return $this.newSpecificBuilder();
   }

   default Builder newSpecificBuilder() {
      return NodeSeq$.MODULE$.newBuilder();
   }

   // $FF: synthetic method
   static NodeSeq empty$(final ScalaVersionSpecificNodeSeq $this) {
      return $this.empty();
   }

   default NodeSeq empty() {
      return NodeSeq$.MODULE$.Empty();
   }

   // $FF: synthetic method
   static NodeSeq concat$(final ScalaVersionSpecificNodeSeq $this, final IterableOnce suffix) {
      return $this.concat(suffix);
   }

   default NodeSeq concat(final IterableOnce suffix) {
      return this.fromSpecific(((NodeSeq)this).iterator().$plus$plus(() -> suffix.iterator()));
   }

   // $FF: synthetic method
   static NodeSeq $plus$plus$(final ScalaVersionSpecificNodeSeq $this, final Seq suffix) {
      return $this.$plus$plus(suffix);
   }

   default NodeSeq $plus$plus(final Seq suffix) {
      return this.concat(suffix);
   }

   // $FF: synthetic method
   static NodeSeq appended$(final ScalaVersionSpecificNodeSeq $this, final Node base) {
      return $this.appended(base);
   }

   default NodeSeq appended(final Node base) {
      return this.fromSpecific(new View.Appended(this, base));
   }

   // $FF: synthetic method
   static NodeSeq appendedAll$(final ScalaVersionSpecificNodeSeq $this, final IterableOnce suffix) {
      return $this.appendedAll(suffix);
   }

   default NodeSeq appendedAll(final IterableOnce suffix) {
      return this.concat(suffix);
   }

   // $FF: synthetic method
   static NodeSeq prepended$(final ScalaVersionSpecificNodeSeq $this, final Node base) {
      return $this.prepended(base);
   }

   default NodeSeq prepended(final Node base) {
      return this.fromSpecific(new View.Prepended(base, this));
   }

   // $FF: synthetic method
   static NodeSeq prependedAll$(final ScalaVersionSpecificNodeSeq $this, final IterableOnce prefix) {
      return $this.prependedAll(prefix);
   }

   default NodeSeq prependedAll(final IterableOnce prefix) {
      return this.fromSpecific(prefix.iterator().$plus$plus(() -> ((NodeSeq)this).iterator()));
   }

   // $FF: synthetic method
   static NodeSeq map$(final ScalaVersionSpecificNodeSeq $this, final Function1 f) {
      return $this.map(f);
   }

   default NodeSeq map(final Function1 f) {
      return this.fromSpecific(new View.Map(this, f));
   }

   // $FF: synthetic method
   static NodeSeq flatMap$(final ScalaVersionSpecificNodeSeq $this, final Function1 f) {
      return $this.flatMap(f);
   }

   default NodeSeq flatMap(final Function1 f) {
      return this.fromSpecific(new View.FlatMap(this, f));
   }

   static void $init$(final ScalaVersionSpecificNodeSeq $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
