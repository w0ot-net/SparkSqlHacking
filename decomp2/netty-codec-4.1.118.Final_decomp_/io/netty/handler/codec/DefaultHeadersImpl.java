package io.netty.handler.codec;

import io.netty.util.HashingStrategy;

public final class DefaultHeadersImpl extends DefaultHeaders {
   public DefaultHeadersImpl(HashingStrategy nameHashingStrategy, ValueConverter valueConverter, DefaultHeaders.NameValidator nameValidator) {
      super(nameHashingStrategy, valueConverter, nameValidator);
   }

   public DefaultHeadersImpl(HashingStrategy nameHashingStrategy, ValueConverter valueConverter, DefaultHeaders.NameValidator nameValidator, int arraySizeHint, DefaultHeaders.ValueValidator valueValidator) {
      super(nameHashingStrategy, valueConverter, nameValidator, arraySizeHint, valueValidator);
   }
}
