package shaded.parquet.com.fasterxml.jackson.core;

import shaded.parquet.com.fasterxml.jackson.core.util.JacksonFeature;

public enum StreamWriteCapability implements JacksonFeature {
   CAN_WRITE_BINARY_NATIVELY(false),
   CAN_WRITE_FORMATTED_NUMBERS(false);

   private final boolean _defaultState;
   private final int _mask;

   private StreamWriteCapability(boolean defaultState) {
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
