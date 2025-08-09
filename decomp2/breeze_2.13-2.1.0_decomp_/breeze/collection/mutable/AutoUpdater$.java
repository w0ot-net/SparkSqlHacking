package breeze.collection.mutable;

import scala.;
import scala.Function0;

public final class AutoUpdater$ {
   public static final AutoUpdater$ MODULE$ = new AutoUpdater$();

   public AutoUpdater apply(final Object map, final Function0 default, final .less.colon.less ev) {
      return new AutoUpdater(map, default, ev);
   }

   public AutoUpdater apply(final Function0 default) {
      return this.apply(scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$), default, scala..less.colon.less..MODULE$.refl());
   }

   public Object ofKeys() {
      return new Object() {
         public AutoUpdater andValues(final Function0 v) {
            return AutoUpdater$.MODULE$.apply(v);
         }
      };
   }

   private AutoUpdater$() {
   }
}
