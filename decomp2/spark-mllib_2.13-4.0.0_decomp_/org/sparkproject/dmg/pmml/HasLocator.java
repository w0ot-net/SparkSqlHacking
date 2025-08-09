package org.sparkproject.dmg.pmml;

import org.xml.sax.Locator;

public interface HasLocator {
   boolean hasLocator();

   Locator getLocator();

   void setLocator(Locator var1);
}
