package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153QAC\u0006\u0003\u0017=A\u0001B\t\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\u0006U\u0001!\ta\u000b\u0004\u0007]\u0001\u0001\u000bQB\u0018\t\u0011M\u001a!\u0011!S\u0001\nQBQAK\u0002\u0005\u0002]B\u0001bO\u0002\t\u0006\u0004%\t\u0001\u0010\u0005\u0007{\u0001\u0001\u000b\u0015\u0002\u001d\t\u000by\u0002A\u0011A \t\u000b\r\u0003A\u0011\u0001#\u0003#1Kg.Z1s'\u0016\f\u0018\n^3sCR|'O\u0003\u0002\r\u001b\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u00039\tQa]2bY\u0006,\"\u0001E\f\u0014\u0005\u0001\t\u0002c\u0001\n\u0014+5\t1\"\u0003\u0002\u0015\u0017\t\u0001\u0012IY:ue\u0006\u001cG/\u0013;fe\u0006$xN\u001d\t\u0003-]a\u0001\u0001B\u0003\u0019\u0001\t\u0007!DA\u0001B\u0007\u0001\t\"aG\u0010\u0011\u0005qiR\"A\u0007\n\u0005yi!a\u0002(pi\"Lgn\u001a\t\u00039\u0001J!!I\u0007\u0003\u0007\u0005s\u00170\u0001\u0003d_2d\u0007#\u0002\n%+\u0019J\u0013BA\u0013\f\u00051a\u0015N\\3beN+\u0017o\u00149t!\t\u0011r%\u0003\u0002)\u0017\tIA*\u001b8fCJ\u001cV-\u001d\t\u0004%\u001d*\u0012A\u0002\u001fj]&$h\b\u0006\u0002-[A\u0019!\u0003A\u000b\t\u000b\t\u0012\u0001\u0019A\u0012\u0003\u00111\u000b'0_\"fY2\u001c\"a\u0001\u0019\u0011\u0005q\t\u0014B\u0001\u001a\u000e\u0005\u0019\te.\u001f*fM\u0006\u00111\u000f\u001e\t\u00049U\u001a\u0013B\u0001\u001c\u000e\u0005!a$-\u001f8b[\u0016tDC\u0001\u001d;!\tI4!D\u0001\u0001\u0011\u0019\u0019T\u0001\"a\u0001i\u0005\ta/F\u0001$\u0003\u0015!\b.Z:f\u0003\u001dA\u0017m\u001d(fqR,\u0012\u0001\u0011\t\u00039\u0005K!AQ\u0007\u0003\u000f\t{w\u000e\\3b]\u0006!a.\u001a=u)\u0005)\u0002"
)
public final class LinearSeqIterator extends AbstractIterator {
   private LazyCell these;

   public boolean hasNext() {
      return this.these.v().nonEmpty();
   }

   public Object next() {
      if (Iterator.isEmpty$(this)) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty.next();
      } else {
         LinearSeqOps cur = this.these.v();
         Object result = cur.head();
         this.these = new LazyCell(() -> (LinearSeq)cur.tail());
         return result;
      }
   }

   public LinearSeqIterator(final LinearSeqOps coll) {
      this.these = new LazyCell(() -> coll);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private final class LazyCell {
      private LinearSeqOps v;
      private Function0 st;
      private volatile boolean bitmap$0;

      private LinearSeqOps v$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.v = (LinearSeqOps)this.st.apply();
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         this.st = null;
         return this.v;
      }

      public LinearSeqOps v() {
         return !this.bitmap$0 ? this.v$lzycompute() : this.v;
      }

      public LazyCell(final Function0 st) {
         this.st = st;
         super();
      }
   }
}
