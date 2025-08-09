package org.apache.logging.log4j.layout.template.json.util;

public interface Recycler {
   Object acquire();

   void release(Object value);
}
