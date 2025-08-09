package org.apache.hive.beeline;

import java.io.IOException;
import java.util.Properties;

public class DriverInfo {
   public String sampleURL;

   public DriverInfo(String name) throws IOException {
      Properties props = new Properties();
      props.load(DriverInfo.class.getResourceAsStream(name));
      this.fromProperties(props);
   }

   public DriverInfo(Properties props) {
      this.fromProperties(props);
   }

   public void fromProperties(Properties props) {
   }
}
