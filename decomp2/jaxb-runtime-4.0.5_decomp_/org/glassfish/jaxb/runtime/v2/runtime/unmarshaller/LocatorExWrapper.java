package org.glassfish.jaxb.runtime.v2.runtime.unmarshaller;

import jakarta.xml.bind.ValidationEventLocator;
import jakarta.xml.bind.helpers.ValidationEventLocatorImpl;
import org.glassfish.jaxb.core.v2.runtime.unmarshaller.LocatorEx;
import org.xml.sax.Locator;

class LocatorExWrapper implements LocatorEx {
   private final Locator locator;

   public LocatorExWrapper(Locator locator) {
      this.locator = locator;
   }

   public ValidationEventLocator getLocation() {
      return new ValidationEventLocatorImpl(this.locator);
   }

   public String getPublicId() {
      return this.locator.getPublicId();
   }

   public String getSystemId() {
      return this.locator.getSystemId();
   }

   public int getLineNumber() {
      return this.locator.getLineNumber();
   }

   public int getColumnNumber() {
      return this.locator.getColumnNumber();
   }
}
