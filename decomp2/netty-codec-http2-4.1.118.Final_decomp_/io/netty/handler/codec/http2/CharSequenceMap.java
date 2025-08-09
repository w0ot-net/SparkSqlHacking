package io.netty.handler.codec.http2;

import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.UnsupportedValueConverter;
import io.netty.handler.codec.ValueConverter;
import io.netty.handler.codec.DefaultHeaders.NameValidator;
import io.netty.util.AsciiString;

public final class CharSequenceMap extends DefaultHeaders {
   public CharSequenceMap() {
      this(true);
   }

   public CharSequenceMap(boolean caseSensitive) {
      this(caseSensitive, UnsupportedValueConverter.instance());
   }

   public CharSequenceMap(boolean caseSensitive, ValueConverter valueConverter) {
      super(caseSensitive ? AsciiString.CASE_SENSITIVE_HASHER : AsciiString.CASE_INSENSITIVE_HASHER, valueConverter);
   }

   public CharSequenceMap(boolean caseSensitive, ValueConverter valueConverter, int arraySizeHint) {
      super(caseSensitive ? AsciiString.CASE_SENSITIVE_HASHER : AsciiString.CASE_INSENSITIVE_HASHER, valueConverter, NameValidator.NOT_NULL, arraySizeHint);
   }
}
