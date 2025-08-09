package shaded.parquet.com.fasterxml.jackson.core;

import shaded.parquet.com.fasterxml.jackson.core.util.JacksonFeature;

public enum StreamReadCapability implements JacksonFeature {
   DUPLICATE_PROPERTIES(false),
   SCALARS_AS_OBJECTS(false),
   UNTYPED_SCALARS(false),
   EXACT_FLOATS(false);

   private final boolean _defaultState;
   private final int _mask;

   private StreamReadCapability(boolean defaultState) {
      this._defaultState = defaultState;
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
