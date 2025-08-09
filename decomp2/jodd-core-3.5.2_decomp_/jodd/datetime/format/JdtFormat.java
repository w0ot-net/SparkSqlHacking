package jodd.datetime.format;

import jodd.datetime.DateTimeStamp;
import jodd.datetime.JDateTime;

public class JdtFormat {
   protected final String format;
   protected final JdtFormatter formatter;

   public JdtFormat(JdtFormatter formatter, String format) {
      this.format = format;
      this.formatter = formatter;
   }

   public String getFormat() {
      return this.format;
   }

   public JdtFormatter getFormatter() {
      return this.formatter;
   }

   public String convert(JDateTime jdt) {
      return this.formatter.convert(jdt, this.format);
   }

   public DateTimeStamp parse(String value) {
      return this.formatter.parse(value, this.format);
   }
}
