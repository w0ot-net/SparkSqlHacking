package org.apache.jute;

import java.io.IOException;
import org.apache.yetus.audience.InterfaceAudience.Public;

@Public
public interface Record {
   void serialize(OutputArchive var1, String var2) throws IOException;

   void deserialize(InputArchive var1, String var2) throws IOException;
}
