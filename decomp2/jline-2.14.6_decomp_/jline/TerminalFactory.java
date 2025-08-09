package jline;

import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import jline.internal.Configuration;
import jline.internal.Log;
import jline.internal.Preconditions;

public class TerminalFactory {
   public static final String JLINE_TERMINAL = "jline.terminal";
   public static final String AUTO = "auto";
   public static final String UNIX = "unix";
   public static final String OSV = "osv";
   public static final String WIN = "win";
   public static final String WINDOWS = "windows";
   public static final String FREEBSD = "freebsd";
   public static final String NONE = "none";
   public static final String OFF = "off";
   public static final String FALSE = "false";
   private static Terminal term = null;
   private static final Map FLAVORS = new HashMap();

   public static synchronized Terminal create() {
      return create((String)null);
   }

   public static synchronized Terminal create(String ttyDevice) {
      if (Log.TRACE) {
         Log.trace(new Throwable("CREATE MARKER"));
      }

      String defaultType = "dumb".equals(System.getenv("TERM")) ? "none" : "auto";
      String type = Configuration.getString("jline.terminal", defaultType);
      Log.debug("Creating terminal; type=", type);

      Terminal t;
      try {
         String tmp = type.toLowerCase();
         if (tmp.equals("unix")) {
            t = getFlavor(TerminalFactory.Flavor.UNIX);
         } else if (tmp.equals("osv")) {
            t = getFlavor(TerminalFactory.Flavor.OSV);
         } else if (!tmp.equals("win") && !tmp.equals("windows")) {
            if (!tmp.equals("none") && !tmp.equals("off") && !tmp.equals("false")) {
               if (tmp.equals("auto")) {
                  String os = Configuration.getOsName();
                  Flavor flavor = TerminalFactory.Flavor.UNIX;
                  if (os.contains("windows")) {
                     flavor = TerminalFactory.Flavor.WINDOWS;
                  } else if (System.getenv("OSV_CPUS") != null) {
                     flavor = TerminalFactory.Flavor.OSV;
                  }

                  t = getFlavor(flavor, ttyDevice);
               } else {
                  try {
                     t = (Terminal)Thread.currentThread().getContextClassLoader().loadClass(type).newInstance();
                  } catch (Exception e) {
                     throw new IllegalArgumentException(MessageFormat.format("Invalid terminal type: {0}", type), e);
                  }
               }
            } else if (System.getenv("INSIDE_EMACS") != null) {
               t = new UnsupportedTerminal(true, false);
            } else {
               t = new UnsupportedTerminal(false, true);
            }
         } else {
            t = getFlavor(TerminalFactory.Flavor.WINDOWS);
         }
      } catch (Exception e) {
         Log.error("Failed to construct terminal; falling back to unsupported", e);
         t = new UnsupportedTerminal();
      }

      Log.debug("Created Terminal: ", t);

      try {
         t.init();
         return t;
      } catch (Throwable e) {
         Log.error("Terminal initialization failed; falling back to unsupported", e);
         return new UnsupportedTerminal();
      }
   }

   public static synchronized void reset() {
      term = null;
   }

   public static synchronized void resetIf(Terminal t) {
      if (t == term) {
         reset();
      }

   }

   public static synchronized void configure(String type) {
      Preconditions.checkNotNull(type);
      System.setProperty("jline.terminal", type);
   }

   public static synchronized void configure(Type type) {
      Preconditions.checkNotNull(type);
      configure(type.name().toLowerCase());
   }

   public static synchronized Terminal get(String ttyDevice) {
      if (term == null) {
         term = create(ttyDevice);
      }

      return term;
   }

   public static synchronized Terminal get() {
      return get((String)null);
   }

   public static Terminal getFlavor(Flavor flavor) throws Exception {
      return getFlavor(flavor, (String)null);
   }

   public static Terminal getFlavor(Flavor flavor, String ttyDevice) throws Exception {
      Class<? extends Terminal> type = (Class)FLAVORS.get(flavor);
      Terminal result = null;
      if (type != null) {
         if (ttyDevice != null) {
            Constructor<?> ttyDeviceConstructor = type.getConstructor(String.class);
            if (ttyDeviceConstructor != null) {
               result = (Terminal)ttyDeviceConstructor.newInstance(ttyDevice);
            } else {
               result = (Terminal)type.newInstance();
            }
         } else {
            result = (Terminal)type.newInstance();
         }

         return result;
      } else {
         throw new InternalError();
      }
   }

   public static void registerFlavor(Flavor flavor, Class type) {
      FLAVORS.put(flavor, type);
   }

   static {
      registerFlavor(TerminalFactory.Flavor.WINDOWS, AnsiWindowsTerminal.class);
      registerFlavor(TerminalFactory.Flavor.UNIX, UnixTerminal.class);
      registerFlavor(TerminalFactory.Flavor.OSV, OSvTerminal.class);
   }

   public static enum Type {
      AUTO,
      WINDOWS,
      UNIX,
      OSV,
      NONE;
   }

   public static enum Flavor {
      WINDOWS,
      UNIX,
      OSV;
   }
}
