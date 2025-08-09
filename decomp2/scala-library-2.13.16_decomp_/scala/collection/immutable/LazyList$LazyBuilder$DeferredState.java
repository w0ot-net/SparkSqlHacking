package scala.collection.immutable;

import scala.Function0;

public final class LazyList$LazyBuilder$DeferredState {
   private Function0 _state;

   public LazyList.State eval() {
      Function0 state = this._state;
      if (state == null) {
         throw new IllegalStateException("uninitialized");
      } else {
         return (LazyList.State)state.apply();
      }
   }

   public void init(final Function0 state) {
      if (this._state != null) {
         throw new IllegalStateException("already initialized");
      } else {
         this._state = state;
      }
   }
}
