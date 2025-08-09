package org.apache.logging.log4j.core.config;

import java.util.Map;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.net.Advertiser;

@Plugin(
   name = "Default",
   category = "Core",
   elementType = "advertiser",
   printObject = false
)
public class DefaultAdvertiser implements Advertiser {
   public Object advertise(final Map properties) {
      return null;
   }

   public void unadvertise(final Object advertisedObject) {
   }
}
