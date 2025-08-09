package com.fasterxml.jackson.datatype.jsr310;

import com.fasterxml.jackson.core.util.JacksonFeature;

public enum JavaTimeFeature implements JacksonFeature {
   NORMALIZE_DESERIALIZED_ZONE_ID(true),
   ALWAYS_ALLOW_STRINGIFIED_DATE_TIMESTAMPS(false),
   ONE_BASED_MONTHS(false);

   private final boolean _defaultState;
   private final int _mask;

   private JavaTimeFeature(boolean enabledByDefault) {
      this._defaultState = enabledByDefault;
      this._mask = 1 << this.ordinal();
   }

   public boolean enabledByDefault() {
      return this._defaultState;
   }

   public boolean enabledIn(int flags) {
      return (flags & this._mask) != 0;
   }

   public int getMask() {
      return this._mask;
   }
}
