package com.fasterxml.jackson.core.util;

import java.io.Serializable;

public final class JacksonFeatureSet implements Serializable {
   private static final long serialVersionUID = 1L;
   protected int _enabled;

   protected JacksonFeatureSet(int bitmask) {
      this._enabled = bitmask;
   }

   public static JacksonFeatureSet fromDefaults(JacksonFeature[] allFeatures) {
      if (allFeatures.length > 31) {
         String desc = allFeatures[0].getClass().getName();
         throw new IllegalArgumentException(String.format("Can not use type `%s` with JacksonFeatureSet: too many entries (%d > 31)", desc, allFeatures.length));
      } else {
         int flags = 0;

         for(JacksonFeature f : allFeatures) {
            if (f.enabledByDefault()) {
               flags |= f.getMask();
            }
         }

         return new JacksonFeatureSet(flags);
      }
   }

   public static JacksonFeatureSet fromBitmask(int bitmask) {
      return new JacksonFeatureSet(bitmask);
   }

   public JacksonFeatureSet with(JacksonFeature feature) {
      int newMask = this._enabled | feature.getMask();
      return newMask == this._enabled ? this : new JacksonFeatureSet(newMask);
   }

   public JacksonFeatureSet without(JacksonFeature feature) {
      int newMask = this._enabled & ~feature.getMask();
      return newMask == this._enabled ? this : new JacksonFeatureSet(newMask);
   }

   public boolean isEnabled(JacksonFeature feature) {
      return (feature.getMask() & this._enabled) != 0;
   }

   public int asBitmask() {
      return this._enabled;
   }
}
