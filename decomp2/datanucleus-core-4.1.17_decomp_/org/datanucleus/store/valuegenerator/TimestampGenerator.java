package org.datanucleus.store.valuegenerator;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

public class TimestampGenerator extends AbstractGenerator {
   public TimestampGenerator(String name, Properties props) {
      super(name, props);
   }

   protected ValueGenerationBlock reserveBlock(long size) {
      Calendar cal = Calendar.getInstance();
      Timestamp[] ts = new Timestamp[1];
      ts[0] = new Timestamp(cal.getTimeInMillis());
      ValueGenerationBlock block = new ValueGenerationBlock(ts);
      return block;
   }
}
