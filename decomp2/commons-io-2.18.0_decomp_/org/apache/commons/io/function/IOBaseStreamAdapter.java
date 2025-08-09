package org.apache.commons.io.function;

import java.util.Objects;
import java.util.stream.BaseStream;

abstract class IOBaseStreamAdapter implements IOBaseStream {
   private final BaseStream delegate;

   IOBaseStreamAdapter(BaseStream delegate) {
      this.delegate = (BaseStream)Objects.requireNonNull(delegate, "delegate");
   }

   public BaseStream unwrap() {
      return this.delegate;
   }
}
