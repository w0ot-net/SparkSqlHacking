package io.vertx.core.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class UsageMessageFormatter {
   public static final int DEFAULT_WIDTH = 80;
   public static final int DEFAULT_LEFT_PAD = 1;
   public static final int DEFAULT_DESC_PAD = 3;
   public static final String DEFAULT_USAGE_PREFIX = "Usage: ";
   public static final String DEFAULT_OPT_PREFIX = "-";
   public static final String DEFAULT_LONG_OPT_PREFIX = "--";
   public static final String DEFAULT_LONG_OPT_SEPARATOR = " ";
   public static final String DEFAULT_ARG_NAME = "arg";
   private int width = 80;
   private int leftPad = 1;
   private int descPad = 3;
   private String usagePrefix = "Usage: ";
   private String newLine = System.lineSeparator();
   private String defaultOptionPrefix = "-";
   private String defaultLongOptPrefix = "--";
   private String defaultArgName = "arg";
   private String longOptSeparator = " ";
   protected Comparator optionComparator = (opt1, opt2) -> opt1.getName().compareToIgnoreCase(opt2.getName());

   public void setWidth(int width) {
      this.width = width;
   }

   public int getWidth() {
      return this.width;
   }

   public void setLeftPadding(int padding) {
      this.leftPad = padding;
   }

   public int getLeftPadding() {
      return this.leftPad;
   }

   public void setDescPadding(int padding) {
      this.descPad = padding;
   }

   public int getDescPadding() {
      return this.descPad;
   }

   public void setUsagePrefix(String prefix) {
      this.usagePrefix = prefix;
   }

   public String getUsagePrefix() {
      return this.usagePrefix;
   }

   public void setNewLine(String newline) {
      this.newLine = newline;
   }

   public String getNewLine() {
      return this.newLine;
   }

   public void setOptionPrefix(String prefix) {
      this.defaultOptionPrefix = prefix;
   }

   public String getOptionPrefix() {
      return this.defaultOptionPrefix;
   }

   public void setLongOptionPrefix(String prefix) {
      this.defaultLongOptPrefix = prefix;
   }

   public String getLongOptionPrefix() {
      return this.defaultLongOptPrefix;
   }

   public void setLongOptionSeparator(String longOptSeparator) {
      this.longOptSeparator = longOptSeparator;
   }

   public String getLongOptionSeparator() {
      return this.longOptSeparator;
   }

   public void setArgName(String name) {
      this.defaultArgName = name;
   }

   public String getArgName() {
      return this.defaultArgName;
   }

   public Comparator getOptionComparator() {
      return this.optionComparator;
   }

   public void setOptionComparator(Comparator comparator) {
      this.optionComparator = comparator;
   }

   protected void appendOption(StringBuilder buff, Option option) {
      if (!option.isHidden()) {
         if (!option.isRequired()) {
            buff.append("[");
         }

         if (!isNullOrEmpty(option.getShortName())) {
            buff.append("-").append(option.getShortName());
         } else {
            buff.append("--").append(option.getLongName());
         }

         if (!option.getChoices().isEmpty()) {
            buff.append(isNullOrEmpty(option.getShortName()) ? this.getLongOptionSeparator() : " ");
            buff.append((String)option.getChoices().stream().collect(Collectors.joining(", ", "{", "}")));
         } else if (option.acceptValue() && (option.getArgName() == null || option.getArgName().length() != 0)) {
            buff.append(isNullOrEmpty(option.getShortName()) ? this.getLongOptionSeparator() : " ");
            buff.append("<").append(option.getArgName() != null ? option.getArgName() : this.getArgName()).append(">");
         }

         if (!option.isRequired()) {
            buff.append("]");
         }

      }
   }

   protected void appendArgument(StringBuilder buff, Argument argument, boolean required) {
      if (!argument.isHidden()) {
         if (!required) {
            buff.append("[");
         }

         buff.append(argument.getArgName());
         if (argument.isMultiValued()) {
            buff.append("...");
         }

         if (!required) {
            buff.append("]");
         }

      }
   }

   public void usage(StringBuilder builder, CLI cli) {
      this.usage(builder, (String)null, cli);
   }

   public void usage(StringBuilder builder, String prefix, CLI cli) {
      this.computeUsageLine(builder, prefix, cli);
      if (cli.getSummary() != null && cli.getSummary().trim().length() > 0) {
         this.buildWrapped(builder, "\n" + cli.getSummary());
      }

      if (cli.getDescription() != null && cli.getDescription().trim().length() > 0) {
         this.buildWrapped(builder, "\n" + cli.getDescription());
      }

      builder.append("\n");
      if (!cli.getOptions().isEmpty() || !cli.getArguments().isEmpty()) {
         builder.append("Options and Arguments:\n");
         this.computeOptionsAndArguments(builder, cli.getOptions(), cli.getArguments());
      }
   }

   public void computeUsage(StringBuilder buffer, String cmdLineSyntax) {
      int argPos = cmdLineSyntax.indexOf(32) + 1;
      this.buildWrapped(buffer, this.getUsagePrefix().length() + argPos, this.getUsagePrefix() + cmdLineSyntax);
   }

   public void computeUsageLine(StringBuilder buffer, String prefix, CLI cli) {
      StringBuilder buff;
      if (prefix == null) {
         buff = new StringBuilder(this.getUsagePrefix());
      } else {
         buff = (new StringBuilder(this.getUsagePrefix())).append(prefix);
         if (!prefix.endsWith(" ")) {
            buff.append(" ");
         }
      }

      buff.append(cli.getName()).append(" ");
      if (this.getOptionComparator() != null) {
         Collections.sort(cli.getOptions(), this.getOptionComparator());
      }

      for(Option option : cli.getOptions()) {
         this.appendOption(buff, option);
         buff.append(" ");
      }

      for(Argument arg : cli.getArguments()) {
         this.appendArgument(buff, arg, arg.isRequired());
         buff.append(" ");
      }

      this.buildWrapped(buffer, buff.toString().indexOf(32) + 1, buff.toString());
   }

   public void computeOptionsAndArguments(StringBuilder buffer, List options, List arguments) {
      this.renderOptionsAndArguments(buffer, options, arguments);
      buffer.append(this.newLine);
   }

   public void buildWrapped(StringBuilder buffer, String text) {
      this.buildWrapped(buffer, 0, text);
   }

   public void buildWrapped(StringBuilder buffer, int nextLineTabStop, String text) {
      this.renderWrappedTextBlock(buffer, this.width, nextLineTabStop, text);
      buffer.append(this.newLine);
   }

   protected StringBuilder renderCommands(StringBuilder sb, Collection commands) {
      String lpad = createPadding(this.leftPad);
      String dpad = createPadding(this.descPad);
      int max = 0;
      List<StringBuilder> prefixList = new ArrayList();

      for(CLI command : commands) {
         if (!command.isHidden()) {
            StringBuilder buf = new StringBuilder();
            buf.append(lpad).append("   ").append(command.getName());
            prefixList.add(buf);
            max = Math.max(buf.length(), max);
         }
      }

      int x = 0;
      Iterator<CLI> it = commands.iterator();

      while(it.hasNext()) {
         CLI command = (CLI)it.next();
         if (!command.isHidden()) {
            StringBuilder buf = new StringBuilder(((StringBuilder)prefixList.get(x++)).toString());
            if (buf.length() < max) {
               buf.append(createPadding(max - buf.length()));
            }

            buf.append(dpad);
            int nextLineTabStop = max + this.descPad;
            buf.append(command.getSummary());
            this.renderWrappedText(sb, this.width, nextLineTabStop, buf.toString());
            if (it.hasNext()) {
               sb.append(this.getNewLine());
            }
         }
      }

      return sb;
   }

   public static boolean isNullOrEmpty(String s) {
      return s == null || s.trim().length() == 0;
   }

   protected StringBuilder renderOptionsAndArguments(StringBuilder sb, List options, List arguments) {
      String lpad = createPadding(this.leftPad);
      String dpad = createPadding(this.descPad);
      int max = 0;
      List<StringBuilder> prefixList = new ArrayList();
      if (this.getOptionComparator() != null) {
         Collections.sort(options, this.getOptionComparator());
      }

      for(Option option : options) {
         StringBuilder buf = new StringBuilder();
         if (!option.isHidden()) {
            if (isNullOrEmpty(option.getShortName())) {
               buf.append(lpad).append("   ").append(this.getLongOptionPrefix()).append(option.getLongName());
            } else {
               buf.append(lpad).append(this.getOptionPrefix()).append(option.getShortName());
               if (!isNullOrEmpty(option.getLongName())) {
                  buf.append(',').append(this.getLongOptionPrefix()).append(option.getLongName());
               }
            }

            if (!option.getChoices().isEmpty()) {
               buf.append(!isNullOrEmpty(option.getLongName()) ? this.longOptSeparator : " ");
               buf.append((String)option.getChoices().stream().collect(Collectors.joining(", ", "{", "}")));
            } else if (option.acceptValue()) {
               String argName = option.getArgName();
               if (argName != null && argName.length() == 0) {
                  buf.append(' ');
               } else {
                  buf.append(!isNullOrEmpty(option.getLongName()) ? this.longOptSeparator : " ");
                  buf.append("<").append(argName != null ? option.getArgName() : this.getArgName()).append(">");
               }
            }

            prefixList.add(buf);
            max = Math.max(buf.length(), max);
         }
      }

      for(Argument argument : arguments) {
         StringBuilder buf = new StringBuilder();
         if (!argument.isHidden()) {
            buf.append(lpad).append("<").append(argument.getArgName()).append(">");
            prefixList.add(buf);
            max = Math.max(buf.length(), max);
         }
      }

      int x = 0;
      Iterator<Option> it = options.iterator();

      while(it.hasNext()) {
         Option option = (Option)it.next();
         if (!option.isHidden()) {
            StringBuilder optBuf = new StringBuilder(((StringBuilder)prefixList.get(x++)).toString());
            if (optBuf.length() < max) {
               optBuf.append(createPadding(max - optBuf.length()));
            }

            optBuf.append(dpad);
            int nextLineTabStop = max + this.descPad;
            if (option.getDescription() != null) {
               optBuf.append(option.getDescription());
            }

            this.renderWrappedText(sb, this.width, nextLineTabStop, optBuf.toString());
            if (it.hasNext()) {
               sb.append(this.getNewLine());
            }
         }
      }

      if (!options.isEmpty() && !arguments.isEmpty()) {
         sb.append(this.getNewLine());
      }

      it = arguments.iterator();

      while(it.hasNext()) {
         Argument argument = (Argument)it.next();
         if (!argument.isHidden()) {
            StringBuilder argBuf = new StringBuilder(((StringBuilder)prefixList.get(x++)).toString());
            if (argBuf.length() < max) {
               argBuf.append(createPadding(max - argBuf.length()));
            }

            argBuf.append(dpad);
            int nextLineTabStop = max + this.descPad;
            if (argument.getDescription() != null) {
               argBuf.append(argument.getDescription());
            }

            this.renderWrappedText(sb, this.width, nextLineTabStop, argBuf.toString());
            if (it.hasNext()) {
               sb.append(this.getNewLine());
            }
         }
      }

      return sb;
   }

   protected StringBuilder renderWrappedText(StringBuilder sb, int width, int nextLineTabStop, String text) {
      int pos = findWrapPos(text, width, 0);
      if (pos == -1) {
         sb.append(rtrim(text));
         return sb;
      } else {
         sb.append(rtrim(text.substring(0, pos))).append(this.getNewLine());
         if (nextLineTabStop >= width) {
            nextLineTabStop = 1;
         }

         String padding = createPadding(nextLineTabStop);

         while(true) {
            text = padding + text.substring(pos).trim();
            pos = findWrapPos(text, width, 0);
            if (pos == -1) {
               sb.append(text);
               return sb;
            }

            if (text.length() > width && pos == nextLineTabStop - 1) {
               pos = width;
            }

            sb.append(rtrim(text.substring(0, pos))).append(this.getNewLine());
         }
      }
   }

   public Appendable renderWrappedTextBlock(StringBuilder sb, int width, int nextLineTabStop, String text) {
      try {
         BufferedReader in = new BufferedReader(new StringReader(text));
         Throwable var6 = null;

         try {
            String line;
            try {
               for(boolean firstLine = true; (line = in.readLine()) != null; this.renderWrappedText(sb, width, nextLineTabStop, line)) {
                  if (!firstLine) {
                     sb.append(this.getNewLine());
                  } else {
                     firstLine = false;
                  }
               }
            } catch (Throwable var17) {
               var6 = var17;
               throw var17;
            }
         } finally {
            if (in != null) {
               if (var6 != null) {
                  try {
                     in.close();
                  } catch (Throwable var16) {
                     var6.addSuppressed(var16);
                  }
               } else {
                  in.close();
               }
            }

         }
      } catch (IOException var19) {
      }

      return sb;
   }

   public static int findWrapPos(String text, int width, int startPos) {
      int pos = text.indexOf(10, startPos);
      if (pos != -1 && pos <= width) {
         return pos + 1;
      } else {
         pos = text.indexOf(9, startPos);
         if (pos != -1 && pos <= width) {
            return pos + 1;
         } else if (startPos + width >= text.length()) {
            return -1;
         } else {
            for(pos = startPos + width; pos >= startPos; --pos) {
               char c = text.charAt(pos);
               if (c == ' ' || c == '\n' || c == '\r') {
                  break;
               }
            }

            if (pos > startPos) {
               return pos;
            } else {
               pos = startPos + width;
               return pos == text.length() ? -1 : pos;
            }
         }
      }
   }

   public static String createPadding(int len) {
      char[] padding = new char[len];
      Arrays.fill(padding, ' ');
      return new String(padding);
   }

   public static String rtrim(String s) {
      if (s != null && s.length() != 0) {
         int pos;
         for(pos = s.length(); pos > 0 && Character.isWhitespace(s.charAt(pos - 1)); --pos) {
         }

         return s.substring(0, pos);
      } else {
         return s;
      }
   }
}
