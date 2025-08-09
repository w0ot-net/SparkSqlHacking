package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2qa\u0003\u0007\u0011\u0002\u0007\u00051\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0004\"\u0001\u0001\u0007i\u0011\u0001\u0012\t\u000f\r\u0002\u0001\u0019!D\u0001I!)q\u0005\u0001C\u0001Q!)A\u0006\u0001C\u0001;!)Q\u0006\u0001C\u0001]!)!\u0007\u0001C\u0001g!)a\u0007\u0001C\u0001o!)\u0011\b\u0001C\u0001u!)A\b\u0001C\u0001]\t\u0019B)\u001a7fO\u0006$X\rZ*jO:\fG\u000e\\5oO*\u0011QBD\u0001\bO\u0016tWM]5d\u0015\ty\u0001#\u0001\u0006d_2dWm\u0019;j_:T\u0011!E\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001A\u0003\u0007\t\u0003+Yi\u0011\u0001E\u0005\u0003/A\u0011a!\u00118z%\u00164\u0007CA\r\u001b\u001b\u0005a\u0011BA\u000e\r\u0005)\u0019\u0016n\u001a8bY2LgnZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003y\u0001\"!F\u0010\n\u0005\u0001\u0002\"\u0001B+oSR\fab]5h]\u0006dG)\u001a7fO\u0006$X-F\u0001\u0019\u0003I\u0019\u0018n\u001a8bY\u0012+G.Z4bi\u0016|F%Z9\u0015\u0005y)\u0003b\u0002\u0014\u0004\u0003\u0003\u0005\r\u0001G\u0001\u0004q\u0012\n\u0014!C5t\u0003\n|'\u000f^3e+\u0005I\u0003CA\u000b+\u0013\tY\u0003CA\u0004C_>dW-\u00198\u0002\u000b\u0005\u0014wN\u001d;\u0002\u0013%tG-\u001a=GY\u0006<W#A\u0018\u0011\u0005U\u0001\u0014BA\u0019\u0011\u0005\rIe\u000e^\u0001\rg\u0016$\u0018J\u001c3fq\u001ac\u0017m\u001a\u000b\u0003=QBQ!N\u0004A\u0002=\n\u0011AZ\u0001\u0016g\u0016$\u0018J\u001c3fq\u001ac\u0017mZ%g\u000fJ,\u0017\r^3s)\tq\u0002\bC\u00036\u0011\u0001\u0007q&\u0001\u000btKRLe\u000eZ3y\r2\fw-\u00134MKN\u001cXM\u001d\u000b\u0003=mBQ!N\u0005A\u0002=\n1\u0001^1h\u0001"
)
public interface DelegatedSignalling extends Signalling {
   Signalling signalDelegate();

   void signalDelegate_$eq(final Signalling x$1);

   // $FF: synthetic method
   static boolean isAborted$(final DelegatedSignalling $this) {
      return $this.isAborted();
   }

   default boolean isAborted() {
      return this.signalDelegate().isAborted();
   }

   // $FF: synthetic method
   static void abort$(final DelegatedSignalling $this) {
      $this.abort();
   }

   default void abort() {
      this.signalDelegate().abort();
   }

   // $FF: synthetic method
   static int indexFlag$(final DelegatedSignalling $this) {
      return $this.indexFlag();
   }

   default int indexFlag() {
      return this.signalDelegate().indexFlag();
   }

   // $FF: synthetic method
   static void setIndexFlag$(final DelegatedSignalling $this, final int f) {
      $this.setIndexFlag(f);
   }

   default void setIndexFlag(final int f) {
      this.signalDelegate().setIndexFlag(f);
   }

   // $FF: synthetic method
   static void setIndexFlagIfGreater$(final DelegatedSignalling $this, final int f) {
      $this.setIndexFlagIfGreater(f);
   }

   default void setIndexFlagIfGreater(final int f) {
      this.signalDelegate().setIndexFlagIfGreater(f);
   }

   // $FF: synthetic method
   static void setIndexFlagIfLesser$(final DelegatedSignalling $this, final int f) {
      $this.setIndexFlagIfLesser(f);
   }

   default void setIndexFlagIfLesser(final int f) {
      this.signalDelegate().setIndexFlagIfLesser(f);
   }

   // $FF: synthetic method
   static int tag$(final DelegatedSignalling $this) {
      return $this.tag();
   }

   default int tag() {
      return this.signalDelegate().tag();
   }

   static void $init$(final DelegatedSignalling $this) {
   }
}
