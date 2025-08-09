package org.apache.derby.impl.services.stream;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.derby.iapi.services.property.PropertyUtil;

public class RollingFileStreamProvider {
   public static OutputStream getOutputStream() throws IOException {
      Object var0 = null;
      String var1 = PropertyUtil.getSystemProperty("derby.stream.error.rollingFile.pattern", "%d/derby-%g.log");
      int var2 = Integer.parseInt(PropertyUtil.getSystemProperty("derby.stream.error.rollingFile.limit", "1024000"));
      int var3 = Integer.parseInt(PropertyUtil.getSystemProperty("derby.stream.error.rollingFile.count", "10"));
      boolean var4 = Boolean.parseBoolean(PropertyUtil.getSystemProperty("derby.infolog.append", "true"));
      RollingFileStream var5 = new RollingFileStream(var1, var2, var3, var4);
      return var5;
   }
}
