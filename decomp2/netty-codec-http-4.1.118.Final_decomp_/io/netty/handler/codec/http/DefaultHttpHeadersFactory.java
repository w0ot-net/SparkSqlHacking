package io.netty.handler.codec.http;

import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.DefaultHeaders.NameValidator;
import io.netty.handler.codec.DefaultHeaders.ValueValidator;
import io.netty.util.internal.ObjectUtil;

public final class DefaultHttpHeadersFactory implements HttpHeadersFactory {
   private static final DefaultHeaders.NameValidator DEFAULT_NAME_VALIDATOR = new DefaultHeaders.NameValidator() {
      public void validateName(CharSequence name) {
         if (name != null && name.length() != 0) {
            int index = HttpHeaderValidationUtil.validateToken(name);
            if (index != -1) {
               throw new IllegalArgumentException("a header name can only contain \"token\" characters, but found invalid character 0x" + Integer.toHexString(name.charAt(index)) + " at index " + index + " of header '" + name + "'.");
            }
         } else {
            throw new IllegalArgumentException("empty headers are not allowed [" + name + ']');
         }
      }
   };
   private static final DefaultHeaders.ValueValidator DEFAULT_VALUE_VALIDATOR = new DefaultHeaders.ValueValidator() {
      public void validate(CharSequence value) {
         int index = HttpHeaderValidationUtil.validateValidHeaderValue(value);
         if (index != -1) {
            throw new IllegalArgumentException("a header value contains prohibited character 0x" + Integer.toHexString(value.charAt(index)) + " at index " + index + '.');
         }
      }
   };
   private static final DefaultHeaders.NameValidator DEFAULT_TRAILER_NAME_VALIDATOR = new DefaultHeaders.NameValidator() {
      public void validateName(CharSequence name) {
         DefaultHttpHeadersFactory.DEFAULT_NAME_VALIDATOR.validateName(name);
         if (HttpHeaderNames.CONTENT_LENGTH.contentEqualsIgnoreCase(name) || HttpHeaderNames.TRANSFER_ENCODING.contentEqualsIgnoreCase(name) || HttpHeaderNames.TRAILER.contentEqualsIgnoreCase(name)) {
            throw new IllegalArgumentException("prohibited trailing header: " + name);
         }
      }
   };
   private static final DefaultHeaders.NameValidator NO_NAME_VALIDATOR;
   private static final DefaultHeaders.ValueValidator NO_VALUE_VALIDATOR;
   private static final DefaultHttpHeadersFactory DEFAULT;
   private static final DefaultHttpHeadersFactory DEFAULT_TRAILER;
   private static final DefaultHttpHeadersFactory DEFAULT_COMBINING;
   private static final DefaultHttpHeadersFactory DEFAULT_NO_VALIDATION;
   private final DefaultHeaders.NameValidator nameValidator;
   private final DefaultHeaders.ValueValidator valueValidator;
   private final boolean combiningHeaders;

   private DefaultHttpHeadersFactory(DefaultHeaders.NameValidator nameValidator, DefaultHeaders.ValueValidator valueValidator, boolean combiningHeaders) {
      this.nameValidator = (DefaultHeaders.NameValidator)ObjectUtil.checkNotNull(nameValidator, "nameValidator");
      this.valueValidator = (DefaultHeaders.ValueValidator)ObjectUtil.checkNotNull(valueValidator, "valueValidator");
      this.combiningHeaders = combiningHeaders;
   }

   public static DefaultHttpHeadersFactory headersFactory() {
      return DEFAULT;
   }

   public static DefaultHttpHeadersFactory trailersFactory() {
      return DEFAULT_TRAILER;
   }

   public HttpHeaders newHeaders() {
      return (HttpHeaders)(this.isCombiningHeaders() ? new CombinedHttpHeaders(this.getNameValidator(), this.getValueValidator()) : new DefaultHttpHeaders(this.getNameValidator(), this.getValueValidator()));
   }

   public HttpHeaders newEmptyHeaders() {
      return (HttpHeaders)(this.isCombiningHeaders() ? new CombinedHttpHeaders(this.getNameValidator(), this.getValueValidator(), 2) : new DefaultHttpHeaders(this.getNameValidator(), this.getValueValidator(), 2));
   }

   public DefaultHttpHeadersFactory withNameValidation(boolean validation) {
      return this.withNameValidator(validation ? DEFAULT_NAME_VALIDATOR : NO_NAME_VALIDATOR);
   }

   public DefaultHttpHeadersFactory withNameValidator(DefaultHeaders.NameValidator validator) {
      if (this.nameValidator == ObjectUtil.checkNotNull(validator, "validator")) {
         return this;
      } else if (validator == DEFAULT_NAME_VALIDATOR && this.valueValidator == DEFAULT_VALUE_VALIDATOR) {
         return this.combiningHeaders ? DEFAULT_COMBINING : DEFAULT;
      } else {
         return new DefaultHttpHeadersFactory(validator, this.valueValidator, this.combiningHeaders);
      }
   }

   public DefaultHttpHeadersFactory withValueValidation(boolean validation) {
      return this.withValueValidator(validation ? DEFAULT_VALUE_VALIDATOR : NO_VALUE_VALIDATOR);
   }

   public DefaultHttpHeadersFactory withValueValidator(DefaultHeaders.ValueValidator validator) {
      if (this.valueValidator == ObjectUtil.checkNotNull(validator, "validator")) {
         return this;
      } else if (this.nameValidator == DEFAULT_NAME_VALIDATOR && validator == DEFAULT_VALUE_VALIDATOR) {
         return this.combiningHeaders ? DEFAULT_COMBINING : DEFAULT;
      } else {
         return new DefaultHttpHeadersFactory(this.nameValidator, validator, this.combiningHeaders);
      }
   }

   public DefaultHttpHeadersFactory withValidation(boolean validation) {
      if (this == DEFAULT && !validation) {
         return DEFAULT_NO_VALIDATION;
      } else {
         return this == DEFAULT_NO_VALIDATION && validation ? DEFAULT : this.withNameValidation(validation).withValueValidation(validation);
      }
   }

   public DefaultHttpHeadersFactory withCombiningHeaders(boolean combiningHeaders) {
      return this.combiningHeaders == combiningHeaders ? this : new DefaultHttpHeadersFactory(this.nameValidator, this.valueValidator, combiningHeaders);
   }

   public DefaultHeaders.NameValidator getNameValidator() {
      return this.nameValidator;
   }

   public DefaultHeaders.ValueValidator getValueValidator() {
      return this.valueValidator;
   }

   public boolean isCombiningHeaders() {
      return this.combiningHeaders;
   }

   public boolean isValidatingHeaderNames() {
      return this.nameValidator != NO_NAME_VALIDATOR;
   }

   public boolean isValidatingHeaderValues() {
      return this.valueValidator != NO_VALUE_VALIDATOR;
   }

   static {
      NO_NAME_VALIDATOR = NameValidator.NOT_NULL;
      NO_VALUE_VALIDATOR = ValueValidator.NO_VALIDATION;
      DEFAULT = new DefaultHttpHeadersFactory(DEFAULT_NAME_VALIDATOR, DEFAULT_VALUE_VALIDATOR, false);
      DEFAULT_TRAILER = new DefaultHttpHeadersFactory(DEFAULT_TRAILER_NAME_VALIDATOR, DEFAULT_VALUE_VALIDATOR, false);
      DEFAULT_COMBINING = new DefaultHttpHeadersFactory(DEFAULT.nameValidator, DEFAULT.valueValidator, true);
      DEFAULT_NO_VALIDATION = new DefaultHttpHeadersFactory(NO_NAME_VALIDATOR, NO_VALUE_VALIDATOR, false);
   }
}
