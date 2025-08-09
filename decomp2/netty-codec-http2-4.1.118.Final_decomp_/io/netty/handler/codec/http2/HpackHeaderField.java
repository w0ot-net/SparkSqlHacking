package io.netty.handler.codec.http2;

import io.netty.util.internal.ObjectUtil;

class HpackHeaderField {
   static final int HEADER_ENTRY_OVERHEAD = 32;
   final CharSequence name;
   final CharSequence value;

   static long sizeOf(CharSequence name, CharSequence value) {
      return (long)(name.length() + value.length() + 32);
   }

   HpackHeaderField(CharSequence name, CharSequence value) {
      this.name = (CharSequence)ObjectUtil.checkNotNull(name, "name");
      this.value = (CharSequence)ObjectUtil.checkNotNull(value, "value");
   }

   final int size() {
      return this.name.length() + this.value.length() + 32;
   }

   public final boolean equalsForTest(HpackHeaderField other) {
      return HpackUtil.equalsVariableTime(this.name, other.name) && HpackUtil.equalsVariableTime(this.value, other.value);
   }

   public String toString() {
      return this.name + ": " + this.value;
   }
}
