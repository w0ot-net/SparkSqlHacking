package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.lang.Assert;

public class LocatorFunction implements Function {
   private final Locator locator;

   public LocatorFunction(Locator locator) {
      this.locator = (Locator)Assert.notNull(locator, "Locator instance cannot be null.");
   }

   public Object apply(Header h) {
      return this.locator.locate(h);
   }
}
