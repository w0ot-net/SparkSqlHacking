package com.thoughtworks.paranamer;

import java.lang.reflect.AccessibleObject;

public class AdaptiveParanamer implements Paranamer {
   public static final String __PARANAMER_DATA = "v1.0 \ncom.thoughtworks.paranamer.AdaptiveParanamer AdaptiveParanamer com.thoughtworks.paranamer.Paranamer,com.thoughtworks.paranamer.Paranamer delegate,fallback\ncom.thoughtworks.paranamer.AdaptiveParanamer AdaptiveParanamer com.thoughtworks.paranamer.Paranamer,com.thoughtworks.paranamer.Paranamer,com.thoughtworks.paranamer.Paranamer delegate,fallback,reserve\ncom.thoughtworks.paranamer.AdaptiveParanamer AdaptiveParanamer com.thoughtworks.paranamer.Paranamer[] paranamers\ncom.thoughtworks.paranamer.AdaptiveParanamer lookupParameterNames java.lang.AccessibleObject methodOrConstructor \ncom.thoughtworks.paranamer.AdaptiveParanamer lookupParameterNames java.lang.AccessibleObject,boolean methodOrCtor,throwExceptionIfMissing \n";
   private final Paranamer[] paranamers;

   public AdaptiveParanamer() {
      this(new DefaultParanamer(), new BytecodeReadingParanamer());
   }

   public AdaptiveParanamer(Paranamer... paranamers) {
      this.paranamers = paranamers;
   }

   public String[] lookupParameterNames(AccessibleObject methodOrConstructor) {
      return this.lookupParameterNames(methodOrConstructor, true);
   }

   public String[] lookupParameterNames(AccessibleObject methodOrCtor, boolean throwExceptionIfMissing) {
      for(int i = 0; i < this.paranamers.length; ++i) {
         Paranamer paranamer = this.paranamers[i];
         String[] names = paranamer.lookupParameterNames(methodOrCtor, i + 1 < this.paranamers.length ? false : throwExceptionIfMissing);
         if (names != Paranamer.EMPTY_NAMES) {
            return names;
         }
      }

      return Paranamer.EMPTY_NAMES;
   }
}
