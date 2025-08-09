package org.apache.commons.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class HelpFormatter {
   private static final String HEADER_OPTIONS = "Options";
   private static final String HEADER_SINCE = "Since";
   private static final String HEADER_DESCRIPTION = "Description";
   public static final int DEFAULT_WIDTH = 74;
   public static final int DEFAULT_LEFT_PAD = 1;
   public static final int DEFAULT_DESC_PAD = 3;
   public static final String DEFAULT_SYNTAX_PREFIX = "usage: ";
   public static final String DEFAULT_OPT_PREFIX = "-";
   public static final String DEFAULT_LONG_OPT_PREFIX = "--";
   public static final String DEFAULT_LONG_OPT_SEPARATOR = " ";
   public static final String DEFAULT_ARG_NAME = "arg";
   /** @deprecated */
   @Deprecated
   public int defaultWidth;
   /** @deprecated */
   @Deprecated
   public int defaultLeftPad;
   /** @deprecated */
   @Deprecated
   public int defaultDescPad;
   /** @deprecated */
   @Deprecated
   public String defaultSyntaxPrefix;
   /** @deprecated */
   @Deprecated
   public String defaultNewLine;
   /** @deprecated */
   @Deprecated
   public String defaultOptPrefix;
   /** @deprecated */
   @Deprecated
   public String defaultLongOptPrefix;
   /** @deprecated */
   @Deprecated
   public String defaultArgName;
   protected Comparator optionComparator;
   private final Function deprecatedFormatFunction;
   private final PrintWriter printWriter;
   private final boolean showSince;
   private String longOptSeparator;

   public static Builder builder() {
      return new Builder();
   }

   private static PrintWriter createDefaultPrintWriter() {
      return new PrintWriter(System.out);
   }

   public static String getDescription(Option option) {
      String desc = option.getDescription();
      return desc == null ? "" : desc;
   }

   public HelpFormatter() {
      this((Function)null, createDefaultPrintWriter(), false);
   }

   private HelpFormatter(Function deprecatedFormatFunction, PrintWriter printWriter, boolean showSince) {
      this.defaultWidth = 74;
      this.defaultLeftPad = 1;
      this.defaultDescPad = 3;
      this.defaultSyntaxPrefix = "usage: ";
      this.defaultNewLine = System.lineSeparator();
      this.defaultOptPrefix = "-";
      this.defaultLongOptPrefix = "--";
      this.defaultArgName = "arg";
      this.optionComparator = new OptionComparator();
      this.longOptSeparator = " ";
      this.deprecatedFormatFunction = deprecatedFormatFunction;
      this.printWriter = printWriter;
      this.showSince = showSince;
   }

   private void appendOption(StringBuilder buff, Option option, boolean required) {
      if (!required) {
         buff.append("[");
      }

      if (option.getOpt() != null) {
         buff.append("-").append(option.getOpt());
      } else {
         buff.append("--").append(option.getLongOpt());
      }

      if (option.hasArg() && (option.getArgName() == null || !option.getArgName().isEmpty())) {
         buff.append(option.getOpt() == null ? this.longOptSeparator : " ");
         buff.append("<").append(option.getArgName() != null ? option.getArgName() : this.getArgName()).append(">");
      }

      if (!required) {
         buff.append("]");
      }

   }

   private void appendOptionGroup(StringBuilder buff, OptionGroup group) {
      if (!group.isRequired()) {
         buff.append("[");
      }

      List<Option> optList = new ArrayList(group.getOptions());
      if (this.getOptionComparator() != null) {
         Collections.sort(optList, this.getOptionComparator());
      }

      Iterator<Option> it = optList.iterator();

      while(it.hasNext()) {
         this.appendOption(buff, (Option)it.next(), true);
         if (it.hasNext()) {
            buff.append(" | ");
         }
      }

      if (!group.isRequired()) {
         buff.append("]");
      }

   }

   Appendable appendOptions(Appendable sb, int width, Options options, int leftPad, int descPad) throws IOException {
      String lpad = this.createPadding(leftPad);
      String dpad = this.createPadding(descPad);
      int max = 0;
      int maxSince = this.showSince ? this.determineMaxSinceLength(options) + leftPad : 0;
      List<StringBuilder> prefixList = new ArrayList();
      List<Option> optList = options.helpOptions();
      if (this.getOptionComparator() != null) {
         Collections.sort(optList, this.getOptionComparator());
      }

      for(Option option : optList) {
         StringBuilder optBuf = new StringBuilder();
         if (option.getOpt() == null) {
            optBuf.append(lpad).append("   ").append(this.getLongOptPrefix()).append(option.getLongOpt());
         } else {
            optBuf.append(lpad).append(this.getOptPrefix()).append(option.getOpt());
            if (option.hasLongOpt()) {
               optBuf.append(',').append(this.getLongOptPrefix()).append(option.getLongOpt());
            }
         }

         if (option.hasArg()) {
            String argName = option.getArgName();
            if (argName != null && argName.isEmpty()) {
               optBuf.append(' ');
            } else {
               optBuf.append(option.hasLongOpt() ? this.longOptSeparator : " ");
               optBuf.append("<").append(argName != null ? option.getArgName() : this.getArgName()).append(">");
            }
         }

         prefixList.add(optBuf);
         max = Math.max(optBuf.length() + maxSince, max);
      }

      int nextLineTabStop = max + descPad;
      if (this.showSince) {
         StringBuilder optHeader = (new StringBuilder("Options")).append(this.createPadding(max - maxSince - "Options".length() + leftPad)).append("Since");
         optHeader.append(this.createPadding(max - optHeader.length())).append(lpad).append("Description");
         this.appendWrappedText(sb, width, nextLineTabStop, optHeader.toString());
         sb.append(this.getNewLine());
      }

      int x = 0;
      Iterator<Option> it = optList.iterator();

      while(it.hasNext()) {
         Option option = (Option)it.next();
         StringBuilder optBuf = new StringBuilder(((StringBuilder)prefixList.get(x++)).toString());
         if (optBuf.length() < max) {
            optBuf.append(this.createPadding(max - maxSince - optBuf.length()));
            if (this.showSince) {
               optBuf.append(lpad).append(option.getSince() == null ? "-" : option.getSince());
            }

            optBuf.append(this.createPadding(max - optBuf.length()));
         }

         optBuf.append(dpad);
         if (this.deprecatedFormatFunction != null && option.isDeprecated()) {
            optBuf.append(((String)this.deprecatedFormatFunction.apply(option)).trim());
         } else if (option.getDescription() != null) {
            optBuf.append(option.getDescription());
         }

         this.appendWrappedText(sb, width, nextLineTabStop, optBuf.toString());
         if (it.hasNext()) {
            sb.append(this.getNewLine());
         }
      }

      return sb;
   }

   Appendable appendWrappedText(Appendable appendable, int width, int nextLineTabStop, String text) throws IOException {
      String render = text;
      int nextLineTabStopPos = nextLineTabStop;
      int pos = this.findWrapPos(text, width, 0);
      if (pos == -1) {
         appendable.append(this.rtrim(text));
         return appendable;
      } else {
         appendable.append(this.rtrim(text.substring(0, pos))).append(this.getNewLine());
         if (nextLineTabStop >= width) {
            nextLineTabStopPos = 1;
         }

         String padding = this.createPadding(nextLineTabStopPos);

         while(true) {
            render = padding + render.substring(pos).trim();
            pos = this.findWrapPos(render, width, 0);
            if (pos == -1) {
               appendable.append(render);
               return appendable;
            }

            if (render.length() > width && pos == nextLineTabStopPos - 1) {
               pos = width;
            }

            appendable.append(this.rtrim(render.substring(0, pos))).append(this.getNewLine());
         }
      }
   }

   protected String createPadding(int len) {
      char[] padding = new char[len];
      Arrays.fill(padding, ' ');
      return new String(padding);
   }

   private int determineMaxSinceLength(Options options) {
      int minLen = "Since".length();
      int len = (Integer)options.getOptions().stream().map((o) -> o.getSince() == null ? minLen : o.getSince().length()).max(Integer::compareTo).orElse(minLen);
      return len < minLen ? minLen : len;
   }

   protected int findWrapPos(String text, int width, int startPos) {
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

   public String getArgName() {
      return this.defaultArgName;
   }

   public int getDescPadding() {
      return this.defaultDescPad;
   }

   public int getLeftPadding() {
      return this.defaultLeftPad;
   }

   public String getLongOptPrefix() {
      return this.defaultLongOptPrefix;
   }

   public String getLongOptSeparator() {
      return this.longOptSeparator;
   }

   public String getNewLine() {
      return this.defaultNewLine;
   }

   public Comparator getOptionComparator() {
      return this.optionComparator;
   }

   public String getOptPrefix() {
      return this.defaultOptPrefix;
   }

   public String getSyntaxPrefix() {
      return this.defaultSyntaxPrefix;
   }

   public int getWidth() {
      return this.defaultWidth;
   }

   public void printHelp(int width, String cmdLineSyntax, String header, Options options, String footer) {
      this.printHelp(width, cmdLineSyntax, header, options, footer, false);
   }

   public void printHelp(int width, String cmdLineSyntax, String header, Options options, String footer, boolean autoUsage) {
      PrintWriter pw = new PrintWriter(this.printWriter);
      this.printHelp(pw, width, cmdLineSyntax, header, options, this.getLeftPadding(), this.getDescPadding(), footer, autoUsage);
      pw.flush();
   }

   public void printHelp(PrintWriter pw, int width, String cmdLineSyntax, String header, Options options, int leftPad, int descPad, String footer) {
      this.printHelp(pw, width, cmdLineSyntax, header, options, leftPad, descPad, footer, false);
   }

   public void printHelp(PrintWriter pw, int width, String cmdLineSyntax, String header, Options options, int leftPad, int descPad, String footer, boolean autoUsage) {
      if (Util.isEmpty(cmdLineSyntax)) {
         throw new IllegalArgumentException("cmdLineSyntax not provided");
      } else {
         if (autoUsage) {
            this.printUsage(pw, width, cmdLineSyntax, options);
         } else {
            this.printUsage(pw, width, cmdLineSyntax);
         }

         if (header != null && !header.isEmpty()) {
            this.printWrapped(pw, width, header);
         }

         this.printOptions(pw, width, options, leftPad, descPad);
         if (footer != null && !footer.isEmpty()) {
            this.printWrapped(pw, width, footer);
         }

      }
   }

   public void printHelp(String cmdLineSyntax, Options options) {
      this.printHelp(this.getWidth(), cmdLineSyntax, (String)null, options, (String)null, false);
   }

   public void printHelp(String cmdLineSyntax, Options options, boolean autoUsage) {
      this.printHelp(this.getWidth(), cmdLineSyntax, (String)null, options, (String)null, autoUsage);
   }

   public void printHelp(String cmdLineSyntax, String header, Options options, String footer) {
      this.printHelp(cmdLineSyntax, header, options, footer, false);
   }

   public void printHelp(String cmdLineSyntax, String header, Options options, String footer, boolean autoUsage) {
      this.printHelp(this.getWidth(), cmdLineSyntax, header, options, footer, autoUsage);
   }

   public void printOptions(PrintWriter pw, int width, Options options, int leftPad, int descPad) {
      try {
         pw.println(this.appendOptions(new StringBuilder(), width, options, leftPad, descPad));
      } catch (IOException e) {
         throw new UncheckedIOException(e);
      }
   }

   public void printUsage(PrintWriter pw, int width, String cmdLineSyntax) {
      int argPos = cmdLineSyntax.indexOf(32) + 1;
      this.printWrapped(pw, width, this.getSyntaxPrefix().length() + argPos, this.getSyntaxPrefix() + cmdLineSyntax);
   }

   public void printUsage(PrintWriter pw, int width, String app, Options options) {
      StringBuilder buff = (new StringBuilder(this.getSyntaxPrefix())).append(app).append(' ');
      Collection<OptionGroup> processedGroups = new ArrayList();
      List<Option> optList = new ArrayList(options.getOptions());
      if (this.getOptionComparator() != null) {
         Collections.sort(optList, this.getOptionComparator());
      }

      Iterator<Option> it = optList.iterator();

      while(it.hasNext()) {
         Option option = (Option)it.next();
         OptionGroup group = options.getOptionGroup(option);
         if (group != null) {
            if (!processedGroups.contains(group)) {
               processedGroups.add(group);
               this.appendOptionGroup(buff, group);
            }
         } else {
            this.appendOption(buff, option, option.isRequired());
         }

         if (it.hasNext()) {
            buff.append(' ');
         }
      }

      this.printWrapped(pw, width, buff.toString().indexOf(32) + 1, buff.toString());
   }

   public void printWrapped(PrintWriter pw, int width, int nextLineTabStop, String text) {
      pw.println(this.renderWrappedTextBlock(new StringBuilder(text.length()), width, nextLineTabStop, text));
   }

   public void printWrapped(PrintWriter pw, int width, String text) {
      this.printWrapped(pw, width, 0, text);
   }

   protected StringBuffer renderOptions(StringBuffer sb, int width, Options options, int leftPad, int descPad) {
      try {
         return (StringBuffer)this.appendOptions(sb, width, options, leftPad, descPad);
      } catch (IOException e) {
         throw new UncheckedIOException(e);
      }
   }

   protected StringBuffer renderWrappedText(StringBuffer sb, int width, int nextLineTabStop, String text) {
      try {
         return (StringBuffer)this.appendWrappedText(sb, width, nextLineTabStop, text);
      } catch (IOException e) {
         throw new UncheckedIOException(e);
      }
   }

   private Appendable renderWrappedTextBlock(Appendable appendable, int width, int nextLineTabStop, String text) {
      try {
         BufferedReader in = new BufferedReader(new StringReader(text));

         String line;
         for(boolean firstLine = true; (line = in.readLine()) != null; this.appendWrappedText(appendable, width, nextLineTabStop, line)) {
            if (!firstLine) {
               appendable.append(this.getNewLine());
            } else {
               firstLine = false;
            }
         }
      } catch (IOException var8) {
      }

      return appendable;
   }

   protected String rtrim(String s) {
      if (Util.isEmpty(s)) {
         return s;
      } else {
         int pos;
         for(pos = s.length(); pos > 0 && Character.isWhitespace(s.charAt(pos - 1)); --pos) {
         }

         return s.substring(0, pos);
      }
   }

   public void setArgName(String name) {
      this.defaultArgName = name;
   }

   public void setDescPadding(int padding) {
      this.defaultDescPad = padding;
   }

   public void setLeftPadding(int padding) {
      this.defaultLeftPad = padding;
   }

   public void setLongOptPrefix(String prefix) {
      this.defaultLongOptPrefix = prefix;
   }

   public void setLongOptSeparator(String longOptSeparator) {
      this.longOptSeparator = longOptSeparator;
   }

   public void setNewLine(String newline) {
      this.defaultNewLine = newline;
   }

   public void setOptionComparator(Comparator comparator) {
      this.optionComparator = comparator;
   }

   public void setOptPrefix(String prefix) {
      this.defaultOptPrefix = prefix;
   }

   public void setSyntaxPrefix(String prefix) {
      this.defaultSyntaxPrefix = prefix;
   }

   public void setWidth(int width) {
      this.defaultWidth = width;
   }

   public static class Builder implements Supplier {
      private static final Function DEFAULT_DEPRECATED_FORMAT = (o) -> "[Deprecated] " + HelpFormatter.getDescription(o);
      private Function deprecatedFormatFunction;
      private PrintWriter printStream;
      private boolean showSince;

      public Builder() {
         this.deprecatedFormatFunction = DEFAULT_DEPRECATED_FORMAT;
         this.printStream = HelpFormatter.createDefaultPrintWriter();
      }

      public HelpFormatter get() {
         return new HelpFormatter(this.deprecatedFormatFunction, this.printStream, this.showSince);
      }

      public Builder setPrintWriter(PrintWriter printWriter) {
         this.printStream = (PrintWriter)Objects.requireNonNull(printWriter, "printWriter");
         return this;
      }

      public Builder setShowDeprecated(boolean useDefaultFormat) {
         return this.setShowDeprecated(useDefaultFormat ? DEFAULT_DEPRECATED_FORMAT : null);
      }

      public Builder setShowDeprecated(Function deprecatedFormatFunction) {
         this.deprecatedFormatFunction = deprecatedFormatFunction;
         return this;
      }

      public Builder setShowSince(boolean showSince) {
         this.showSince = showSince;
         return this;
      }
   }

   private static final class OptionComparator implements Comparator, Serializable {
      private static final long serialVersionUID = 5305467873966684014L;

      private OptionComparator() {
      }

      public int compare(Option opt1, Option opt2) {
         return opt1.getKey().compareToIgnoreCase(opt2.getKey());
      }
   }
}
