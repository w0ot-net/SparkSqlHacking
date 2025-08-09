package jodd.datetime.format;

import jodd.datetime.DateTimeStamp;
import jodd.datetime.JDateTime;

public interface JdtFormatter {
   String convert(JDateTime var1, String var2);

   DateTimeStamp parse(String var1, String var2);
}
