package org.apache.spark.launcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class CommandBuilderUtils {
   static final String DEFAULT_MEM = "1g";
   static final String DEFAULT_PROPERTIES_FILE = "spark-defaults.conf";
   static final String ENV_SPARK_HOME = "SPARK_HOME";
   static final String SECRET_REDACTION_PATTERN = "(?i)secret|password|token|access[.]?key";
   static final Pattern redactPattern = Pattern.compile("(?i)secret|password|token|access[.]?key");
   static final Pattern keyValuePattern = Pattern.compile("-D(.+?)=(.+)");

   static boolean isEmpty(String s) {
      return s == null || s.isEmpty();
   }

   static String join(String sep, String... elements) {
      StringBuilder sb = new StringBuilder();

      for(String e : elements) {
         if (e != null) {
            if (sb.length() > 0) {
               sb.append(sep);
            }

            sb.append(e);
         }
      }

      return sb.toString();
   }

   static String join(String sep, Iterable elements) {
      StringBuilder sb = new StringBuilder();

      for(String e : elements) {
         if (e != null) {
            if (sb.length() > 0) {
               sb.append(sep);
            }

            sb.append(e);
         }
      }

      return sb.toString();
   }

   static String firstNonEmptyValue(String key, Map... maps) {
      for(Map map : maps) {
         String value = (String)map.get(key);
         if (!isEmpty(value)) {
            return value;
         }
      }

      return null;
   }

   static String firstNonEmpty(String... candidates) {
      for(String s : candidates) {
         if (!isEmpty(s)) {
            return s;
         }
      }

      return null;
   }

   static String getLibPathEnvName() {
      if (isWindows()) {
         return "PATH";
      } else {
         String os = System.getProperty("os.name");
         return os.startsWith("Mac OS X") ? "DYLD_LIBRARY_PATH" : "LD_LIBRARY_PATH";
      }
   }

   static boolean isWindows() {
      String os = System.getProperty("os.name");
      return os.startsWith("Windows");
   }

   static void mergeEnvPathList(Map userEnv, String envKey, String pathList) {
      if (!isEmpty(pathList)) {
         String current = firstNonEmpty((String)userEnv.get(envKey), System.getenv(envKey));
         userEnv.put(envKey, join(File.pathSeparator, current, pathList));
      }

   }

   static List parseOptionString(String s) {
      List<String> opts = new ArrayList();
      StringBuilder opt = new StringBuilder();
      boolean inOpt = false;
      boolean inSingleQuote = false;
      boolean inDoubleQuote = false;
      boolean escapeNext = false;
      boolean hasData = false;

      for(int i = 0; i < s.length(); ++i) {
         int c = s.codePointAt(i);
         if (escapeNext) {
            opt.appendCodePoint(c);
            escapeNext = false;
         } else if (inOpt) {
            switch (c) {
               case 34:
                  if (inSingleQuote) {
                     opt.appendCodePoint(c);
                  } else {
                     inDoubleQuote = !inDoubleQuote;
                  }
                  break;
               case 39:
                  if (inDoubleQuote) {
                     opt.appendCodePoint(c);
                  } else {
                     inSingleQuote = !inSingleQuote;
                  }
                  break;
               case 92:
                  if (inSingleQuote) {
                     opt.appendCodePoint(c);
                  } else {
                     escapeNext = true;
                  }
                  break;
               default:
                  if (Character.isWhitespace(c) && !inSingleQuote && !inDoubleQuote) {
                     opts.add(opt.toString());
                     opt.setLength(0);
                     inOpt = false;
                     hasData = false;
                  } else {
                     opt.appendCodePoint(c);
                  }
            }
         } else {
            switch (c) {
               case 34:
                  inDoubleQuote = true;
                  inOpt = true;
                  hasData = true;
                  break;
               case 39:
                  inSingleQuote = true;
                  inOpt = true;
                  hasData = true;
                  break;
               case 92:
                  escapeNext = true;
                  inOpt = true;
                  hasData = true;
                  break;
               default:
                  if (!Character.isWhitespace(c)) {
                     inOpt = true;
                     hasData = true;
                     opt.appendCodePoint(c);
                  }
            }
         }
      }

      checkArgument(!inSingleQuote && !inDoubleQuote && !escapeNext, "Invalid option string: %s", s);
      if (hasData) {
         opts.add(opt.toString());
      }

      return opts;
   }

   static void checkNotNull(Object o, String arg) {
      if (o == null) {
         throw new IllegalArgumentException(String.format("'%s' must not be null.", arg));
      }
   }

   static void checkArgument(boolean check, String msg, Object... args) {
      if (!check) {
         throw new IllegalArgumentException(String.format(msg, args));
      }
   }

   static void checkState(boolean check, String msg, Object... args) {
      if (!check) {
         throw new IllegalStateException(String.format(msg, args));
      }
   }

   static String quoteForBatchScript(String arg) {
      boolean needsQuotes = false;

      for(int i = 0; i < arg.length(); ++i) {
         int c = arg.codePointAt(i);
         if (Character.isWhitespace(c) || c == 34 || c == 61 || c == 44 || c == 59) {
            needsQuotes = true;
            break;
         }
      }

      if (!needsQuotes) {
         return arg;
      } else {
         StringBuilder quoted = new StringBuilder();
         quoted.append("\"");

         for(int i = 0; i < arg.length(); ++i) {
            int cp = arg.codePointAt(i);
            switch (cp) {
               case 34:
                  quoted.append('"');
               default:
                  quoted.appendCodePoint(cp);
            }
         }

         if (arg.codePointAt(arg.length() - 1) == 92) {
            quoted.append("\\");
         }

         quoted.append("\"");
         return quoted.toString();
      }
   }

   static String quoteForCommandString(String s) {
      StringBuilder quoted = (new StringBuilder()).append('"');

      for(int i = 0; i < s.length(); ++i) {
         int cp = s.codePointAt(i);
         if (cp == 34 || cp == 92) {
            quoted.appendCodePoint(92);
         }

         quoted.appendCodePoint(cp);
      }

      return quoted.append('"').toString();
   }

   static int javaMajorVersion(String javaVersion) {
      String[] version = javaVersion.split("[+.\\-]+");
      int major = Integer.parseInt(version[0]);
      return major > 1 ? major : Integer.parseInt(version[1]);
   }

   static String findJarsDir(String sparkHome, String scalaVersion, boolean failIfNotFound) {
      File libdir = new File(sparkHome, "jars");
      if (!libdir.isDirectory()) {
         libdir = new File(sparkHome, String.format("assembly/target/scala-%s/jars", scalaVersion));
         if (!libdir.isDirectory()) {
            checkState(!failIfNotFound, "Library directory '%s' does not exist; make sure Spark is built.", libdir.getAbsolutePath());
            return null;
         }
      }

      return libdir.getAbsolutePath();
   }

   static List redactCommandLineArgs(List args) {
      return (List)args.stream().map(CommandBuilderUtils::redact).collect(Collectors.toList());
   }

   static String redact(String arg) {
      Matcher m = keyValuePattern.matcher(arg);
      return m.find() && redactPattern.matcher(m.group(1)).find() ? String.format("-D%s=%s", m.group(1), "*********(redacted)") : arg;
   }
}
