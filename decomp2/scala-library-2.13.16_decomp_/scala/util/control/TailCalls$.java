package scala.util.control;

import scala.Function0;

public final class TailCalls$ {
   public static final TailCalls$ MODULE$ = new TailCalls$();

   public TailCalls.TailRec tailcall(final Function0 rest) {
      return new TailCalls.Call(rest);
   }

   public TailCalls.TailRec done(final Object result) {
      return new TailCalls.Done(result);
   }

   private TailCalls$() {
   }
}
