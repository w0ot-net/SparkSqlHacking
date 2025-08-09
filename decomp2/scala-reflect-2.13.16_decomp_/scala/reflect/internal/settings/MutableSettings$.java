package scala.reflect.internal.settings;

import scala.runtime.BoxesRunTime;

public final class MutableSettings$ {
   public static final MutableSettings$ MODULE$ = new MutableSettings$();

   /** @deprecated */
   public boolean reflectSettingToBoolean(final MutableSettings.SettingValue s) {
      return BoxesRunTime.unboxToBoolean(s.value());
   }

   public MutableSettings SettingsOps(final MutableSettings settings) {
      return settings;
   }

   private MutableSettings$() {
   }
}
