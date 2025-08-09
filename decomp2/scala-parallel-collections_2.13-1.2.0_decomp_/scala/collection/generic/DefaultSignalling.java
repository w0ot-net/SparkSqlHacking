package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2Aa\u0002\u0005\u0001\u001f!)1\u0004\u0001C\u00019!)a\u0004\u0001C\u0001?!)1\u0005\u0001C\u0001I!)!\u0006\u0001C\u0001W!)Q\u0006\u0001C\u0001]!)\u0001\u0007\u0001C\u0001?\t\tB)\u001a4bk2$8+[4oC2d\u0017N\\4\u000b\u0005%Q\u0011aB4f]\u0016\u0014\u0018n\u0019\u0006\u0003\u00171\t!bY8mY\u0016\u001cG/[8o\u0015\u0005i\u0011!B:dC2\f7\u0001A\n\u0005\u0001A!\u0002\u0004\u0005\u0002\u0012%5\tA\"\u0003\u0002\u0014\u0019\t1\u0011I\\=SK\u001a\u0004\"!\u0006\f\u000e\u0003!I!a\u0006\u0005\u0003\u0015MKwM\\1mY&tw\r\u0005\u0002\u00163%\u0011!\u0004\u0003\u0002\u000e->d\u0017\r^5mK\u0006\u0013wN\u001d;\u0002\rqJg.\u001b;?)\u0005i\u0002CA\u000b\u0001\u0003%Ig\u000eZ3y\r2\fw-F\u0001!!\t\t\u0012%\u0003\u0002#\u0019\t\u0019\u0011J\u001c;\u0002\u0019M,G/\u00138eKb4E.Y4\u0015\u0005\u0015B\u0003CA\t'\u0013\t9CB\u0001\u0003V]&$\b\"B\u0015\u0004\u0001\u0004\u0001\u0013!\u00014\u0002+M,G/\u00138eKb4E.Y4JM\u001e\u0013X-\u0019;feR\u0011Q\u0005\f\u0005\u0006S\u0011\u0001\r\u0001I\u0001\u0015g\u0016$\u0018J\u001c3fq\u001ac\u0017mZ%g\u0019\u0016\u001c8/\u001a:\u0015\u0005\u0015z\u0003\"B\u0015\u0006\u0001\u0004\u0001\u0013a\u0001;bO\u0002"
)
public class DefaultSignalling implements VolatileAbort {
   private volatile boolean scala$collection$generic$VolatileAbort$$abortflag;

   public boolean isAborted() {
      return VolatileAbort.isAborted$(this);
   }

   public void abort() {
      VolatileAbort.abort$(this);
   }

   public boolean scala$collection$generic$VolatileAbort$$abortflag() {
      return this.scala$collection$generic$VolatileAbort$$abortflag;
   }

   public void scala$collection$generic$VolatileAbort$$abortflag_$eq(final boolean x$1) {
      this.scala$collection$generic$VolatileAbort$$abortflag = x$1;
   }

   public int indexFlag() {
      return -1;
   }

   public void setIndexFlag(final int f) {
   }

   public void setIndexFlagIfGreater(final int f) {
   }

   public void setIndexFlagIfLesser(final int f) {
   }

   public int tag() {
      return -1;
   }

   public DefaultSignalling() {
      VolatileAbort.$init$(this);
   }
}
