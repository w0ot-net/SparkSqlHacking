package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;

public class FormattedStringFunction implements Function {
   private final String msg;

   public FormattedStringFunction(String msg) {
      this.msg = (String)Assert.hasText(msg, "msg argument cannot be null or empty.");
   }

   public String apply(Object arg) {
      return String.format(this.msg, arg);
   }
}
