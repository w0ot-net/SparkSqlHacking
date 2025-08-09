package org.apache.logging.log4j.core.pattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.util.PerformanceSensitive;

public abstract class AbstractStyleNameConverter extends LogEventPatternConverter {
   private final List formatters;
   private final String style;

   protected AbstractStyleNameConverter(final String name, final List formatters, final String styling) {
      super(name, "style");
      this.formatters = formatters;
      this.style = styling;
   }

   protected static AbstractStyleNameConverter newInstance(final Class asnConverterClass, final String name, final Configuration config, final String[] options) {
      List<PatternFormatter> formatters = toPatternFormatterList(config, options);
      if (formatters == null) {
         return null;
      } else {
         try {
            Constructor<T> constructor = asnConverterClass.getConstructor(List.class, String.class);
            return (AbstractStyleNameConverter)constructor.newInstance(formatters, AnsiEscape.createSequence(name));
         } catch (NoSuchMethodException | IllegalArgumentException | InstantiationException | IllegalAccessException | InvocationTargetException | SecurityException e) {
            LOGGER.error(((Exception)e).toString(), e);
            return null;
         }
      }
   }

   private static List toPatternFormatterList(final Configuration config, final String[] options) {
      if (options.length != 0 && options[0] != null) {
         PatternParser parser = PatternLayout.createPatternParser(config);
         if (parser == null) {
            LOGGER.error("No PatternParser created for config=" + config + ", options=" + Arrays.toString(options));
            return null;
         } else {
            return parser.parse(options[0]);
         }
      } else {
         LOGGER.error("No pattern supplied on style for config=" + config);
         return null;
      }
   }

   @PerformanceSensitive({"allocation"})
   public void format(final LogEvent event, final StringBuilder toAppendTo) {
      int start = toAppendTo.length();

      for(int i = 0; i < this.formatters.size(); ++i) {
         PatternFormatter formatter = (PatternFormatter)this.formatters.get(i);
         formatter.format(event, toAppendTo);
      }

      if (toAppendTo.length() > start) {
         toAppendTo.insert(start, this.style);
         toAppendTo.append(AnsiEscape.getDefaultStyle());
      }

   }

   @Plugin(
      name = "black",
      category = "Converter"
   )
   @ConverterKeys({"black"})
   public static final class Black extends AbstractStyleNameConverter {
      protected static final String NAME = "black";

      public Black(final List formatters, final String styling) {
         super("black", formatters, styling);
      }

      public static Black newInstance(final Configuration config, final String[] options) {
         return (Black)newInstance(Black.class, "black", config, options);
      }
   }

   @Plugin(
      name = "blue",
      category = "Converter"
   )
   @ConverterKeys({"blue"})
   public static final class Blue extends AbstractStyleNameConverter {
      protected static final String NAME = "blue";

      public Blue(final List formatters, final String styling) {
         super("blue", formatters, styling);
      }

      public static Blue newInstance(final Configuration config, final String[] options) {
         return (Blue)newInstance(Blue.class, "blue", config, options);
      }
   }

   @Plugin(
      name = "cyan",
      category = "Converter"
   )
   @ConverterKeys({"cyan"})
   public static final class Cyan extends AbstractStyleNameConverter {
      protected static final String NAME = "cyan";

      public Cyan(final List formatters, final String styling) {
         super("cyan", formatters, styling);
      }

      public static Cyan newInstance(final Configuration config, final String[] options) {
         return (Cyan)newInstance(Cyan.class, "cyan", config, options);
      }
   }

   @Plugin(
      name = "green",
      category = "Converter"
   )
   @ConverterKeys({"green"})
   public static final class Green extends AbstractStyleNameConverter {
      protected static final String NAME = "green";

      public Green(final List formatters, final String styling) {
         super("green", formatters, styling);
      }

      public static Green newInstance(final Configuration config, final String[] options) {
         return (Green)newInstance(Green.class, "green", config, options);
      }
   }

   @Plugin(
      name = "magenta",
      category = "Converter"
   )
   @ConverterKeys({"magenta"})
   public static final class Magenta extends AbstractStyleNameConverter {
      protected static final String NAME = "magenta";

      public Magenta(final List formatters, final String styling) {
         super("magenta", formatters, styling);
      }

      public static Magenta newInstance(final Configuration config, final String[] options) {
         return (Magenta)newInstance(Magenta.class, "magenta", config, options);
      }
   }

   @Plugin(
      name = "red",
      category = "Converter"
   )
   @ConverterKeys({"red"})
   public static final class Red extends AbstractStyleNameConverter {
      protected static final String NAME = "red";

      public Red(final List formatters, final String styling) {
         super("red", formatters, styling);
      }

      public static Red newInstance(final Configuration config, final String[] options) {
         return (Red)newInstance(Red.class, "red", config, options);
      }
   }

   @Plugin(
      name = "white",
      category = "Converter"
   )
   @ConverterKeys({"white"})
   public static final class White extends AbstractStyleNameConverter {
      protected static final String NAME = "white";

      public White(final List formatters, final String styling) {
         super("white", formatters, styling);
      }

      public static White newInstance(final Configuration config, final String[] options) {
         return (White)newInstance(White.class, "white", config, options);
      }
   }

   @Plugin(
      name = "yellow",
      category = "Converter"
   )
   @ConverterKeys({"yellow"})
   public static final class Yellow extends AbstractStyleNameConverter {
      protected static final String NAME = "yellow";

      public Yellow(final List formatters, final String styling) {
         super("yellow", formatters, styling);
      }

      public static Yellow newInstance(final Configuration config, final String[] options) {
         return (Yellow)newInstance(Yellow.class, "yellow", config, options);
      }
   }
}
