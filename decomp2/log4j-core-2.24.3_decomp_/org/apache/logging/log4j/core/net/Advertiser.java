package org.apache.logging.log4j.core.net;

import java.util.Map;

public interface Advertiser {
   Object advertise(Map properties);

   void unadvertise(Object advertisedObject);
}
