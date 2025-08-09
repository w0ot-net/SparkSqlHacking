package org.jline.terminal.spi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Properties;
import org.jline.terminal.Attributes;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;

public interface TerminalProvider {
   String name();

   Terminal sysTerminal(String var1, String var2, boolean var3, Charset var4, boolean var5, Terminal.SignalHandler var6, boolean var7, SystemStream var8) throws IOException;

   Terminal newTerminal(String var1, String var2, InputStream var3, OutputStream var4, Charset var5, Terminal.SignalHandler var6, boolean var7, Attributes var8, Size var9) throws IOException;

   boolean isSystemStream(SystemStream var1);

   String systemStreamName(SystemStream var1);

   int systemStreamWidth(SystemStream var1);

   static TerminalProvider load(String name) throws IOException {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      if (cl == null) {
         cl = ClassLoader.getSystemClassLoader();
      }

      InputStream is = cl.getResourceAsStream("META-INF/services/org/jline/terminal/provider/" + name);
      if (is != null) {
         Properties props = new Properties();

         try {
            props.load(is);
            String className = props.getProperty("class");
            if (className == null) {
               throw new IOException("No class defined in terminal provider file " + name);
            } else {
               Class<?> clazz = cl.loadClass(className);
               return (TerminalProvider)clazz.getConstructor().newInstance();
            }
         } catch (Exception e) {
            throw new IOException("Unable to load terminal provider " + name + ": " + e.getMessage(), e);
         }
      } else {
         throw new IOException("Unable to find terminal provider " + name);
      }
   }
}
