package io.netty.handler.codec.spdy;

import io.netty.handler.codec.CharSequenceValueConverter;
import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.HeadersUtils;
import io.netty.handler.codec.ValueConverter;
import io.netty.handler.codec.DefaultHeaders.NameValidator;
import io.netty.util.AsciiString;
import java.util.Iterator;
import java.util.List;

public class DefaultSpdyHeaders extends DefaultHeaders implements SpdyHeaders {
   private static final DefaultHeaders.NameValidator SpdyNameValidator = new DefaultHeaders.NameValidator() {
      public void validateName(CharSequence name) {
         SpdyCodecUtil.validateHeaderName(name);
      }
   };

   public DefaultSpdyHeaders() {
      this(true);
   }

   public DefaultSpdyHeaders(boolean validate) {
      super(AsciiString.CASE_INSENSITIVE_HASHER, (ValueConverter)(validate ? DefaultSpdyHeaders.HeaderValueConverterAndValidator.INSTANCE : CharSequenceValueConverter.INSTANCE), validate ? SpdyNameValidator : NameValidator.NOT_NULL);
   }

   public String getAsString(CharSequence name) {
      return HeadersUtils.getAsString(this, name);
   }

   public List getAllAsString(CharSequence name) {
      return HeadersUtils.getAllAsString(this, name);
   }

   public Iterator iteratorAsString() {
      return HeadersUtils.iteratorAsString(this);
   }

   public boolean contains(CharSequence name, CharSequence value) {
      return this.contains(name, value, false);
   }

   public boolean contains(CharSequence name, CharSequence value, boolean ignoreCase) {
      return this.contains(name, value, ignoreCase ? AsciiString.CASE_INSENSITIVE_HASHER : AsciiString.CASE_SENSITIVE_HASHER);
   }

   private static final class HeaderValueConverterAndValidator extends CharSequenceValueConverter {
      public static final HeaderValueConverterAndValidator INSTANCE = new HeaderValueConverterAndValidator();

      public CharSequence convertObject(Object value) {
         CharSequence seq = super.convertObject(value);
         SpdyCodecUtil.validateHeaderValue(seq);
         return seq;
      }
   }
}
