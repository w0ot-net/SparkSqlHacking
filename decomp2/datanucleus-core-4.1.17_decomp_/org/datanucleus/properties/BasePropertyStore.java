package org.datanucleus.properties;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class BasePropertyStore extends PropertyStore {
   public void setProperty(String name, Object value) {
      this.setPropertyInternal(name, value);
   }

   public void dump(NucleusLogger logger) {
      logger.debug(">> BasePropertyStore : " + StringUtils.mapToString(this.properties));
   }

   public Set getPropertyNames() {
      return Collections.unmodifiableSet(this.properties.keySet());
   }

   public Map getProperties() {
      return Collections.unmodifiableMap(this.properties);
   }
}
