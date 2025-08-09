package org.apache.ivy.util.cli;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.ivy.util.StringUtils;

public class CommandLineParser {
   private static final int MIN_DESC_WIDTH = 40;
   private static final int MAX_SPEC_WIDTH = 30;
   private Map options = new LinkedHashMap();
   private Map categories = new LinkedHashMap();

   public CommandLineParser addCategory(String category) {
      this.categories.put(category, new ArrayList());
      return this;
   }

   public CommandLineParser addOption(Option option) {
      this.options.put(option.getName(), option);
      if (!this.categories.isEmpty()) {
         ((List)(new LinkedList(this.categories.values())).getLast()).add(option);
      }

      return this;
   }

   public CommandLine parse(String[] args) throws ParseException {
      CommandLine line = new CommandLine();
      int index = args.length;
      ListIterator<String> iterator = Arrays.asList(args).listIterator();

      while(iterator.hasNext()) {
         String arg = (String)iterator.next();
         if ("--".equals(arg)) {
            index = iterator.nextIndex();
            break;
         }

         if (!arg.startsWith("-")) {
            index = iterator.previousIndex();
            break;
         }

         Option option = (Option)this.options.get(arg.substring(1));
         if (option == null) {
            throw new ParseException("Unrecognized option: " + arg);
         }

         line.addOptionValues(arg.substring(1), option.parse(iterator));
      }

      String[] leftOverArgs = new String[args.length - index];
      System.arraycopy(args, index, leftOverArgs, 0, leftOverArgs.length);
      line.setLeftOverArgs(leftOverArgs);
      return line;
   }

   public void printHelp(PrintWriter pw, int width, String command, boolean showDeprecated) {
      pw.println("usage: " + command);
      int specWidth = 0;

      for(Option option : this.options.values()) {
         if (!option.isDeprecated() || showDeprecated) {
            specWidth = Math.min(30, Math.max(specWidth, option.getSpec().length()));
         }
      }

      for(Map.Entry entry : this.categories.entrySet()) {
         pw.println("==== " + (String)entry.getKey());

         for(Option option : (List)entry.getValue()) {
            if (!option.isDeprecated() || showDeprecated) {
               String spec = option.getSpec();
               pw.print(" " + spec);
               int specLength = spec.length() + 1;
               pw.print(StringUtils.repeat(" ", specWidth - specLength));
               StringBuilder desc = new StringBuilder((option.isDeprecated() ? "DEPRECATED: " : "") + option.getDescription());
               int count = Math.min(desc.length(), width - Math.max(specLength, specWidth));
               if (count > 40 || desc.length() + specLength < width) {
                  pw.print(desc.substring(0, count));
                  desc.delete(0, count);
               }

               pw.println();

               while(desc.length() > 0) {
                  pw.print(StringUtils.repeat(" ", specWidth));
                  count = Math.min(desc.length(), width - specWidth);
                  pw.println(desc.substring(0, count));
                  desc.delete(0, count);
               }
            }
         }

         pw.println();
      }

   }
}
