package org.datanucleus.store.valuegenerator;

import java.util.Calendar;
import java.util.Properties;

public class TimestampValueGenerator extends AbstractGenerator {
   public TimestampValueGenerator(String name, Properties props) {
      super(name, props);
   }

   protected ValueGenerationBlock reserveBlock(long size) {
      Calendar cal = Calendar.getInstance();
      Long[] ids = new Long[1];
      ids[0] = cal.getTimeInMillis();
      ValueGenerationBlock block = new ValueGenerationBlock(ids);
      return block;
   }
}
