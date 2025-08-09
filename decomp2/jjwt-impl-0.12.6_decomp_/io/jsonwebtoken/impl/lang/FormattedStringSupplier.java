package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Supplier;

public class FormattedStringSupplier implements Supplier {
   private final String msg;
   private final Object[] args;

   public FormattedStringSupplier(String msg, Object[] args) {
      this.msg = (String)Assert.hasText(msg, "Message cannot be null or empty.");
      this.args = Assert.notEmpty(args, "Arguments cannot be null or empty.");
   }

   public String get() {
      return String.format(this.msg, this.args);
   }
}
