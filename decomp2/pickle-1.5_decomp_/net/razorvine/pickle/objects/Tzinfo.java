package net.razorvine.pickle.objects;

import java.util.HashMap;
import java.util.TimeZone;
import net.razorvine.pickle.PickleException;

public class Tzinfo {
   private final boolean forceTimeZone;
   private TimeZone timeZone;

   public Tzinfo(TimeZone timeZone) {
      this.forceTimeZone = true;
      this.timeZone = timeZone;
   }

   public Tzinfo() {
      this.forceTimeZone = false;
   }

   public TimeZone getTimeZone() {
      return this.timeZone;
   }

   public void __setstate__(HashMap args) {
      if (!this.forceTimeZone) {
         throw new PickleException("unexpected pickle data for tzinfo objects: can't __setstate__ with anything other than an empty dict, anything else is unimplemented");
      }
   }
}
