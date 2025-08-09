package org.apache.yetus.audience.tools;

import com.sun.javadoc.DocErrorReporter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class StabilityOptions {
   public static final String STABLE_OPTION = "-stable";
   public static final String EVOLVING_OPTION = "-evolving";
   public static final String UNSTABLE_OPTION = "-unstable";

   public static Integer optionLength(String option) {
      String opt = option.toLowerCase(Locale.ENGLISH);
      if (opt.equals("-unstable")) {
         return 1;
      } else if (opt.equals("-evolving")) {
         return 1;
      } else {
         return opt.equals("-stable") ? 1 : null;
      }
   }

   public static void validOptions(String[][] options, DocErrorReporter reporter) {
      for(String[] option : options) {
         switch (option[0].toLowerCase(Locale.ENGLISH)) {
            case "-unstable":
               RootDocProcessor.stability = "-unstable";
               break;
            case "-evolving":
               RootDocProcessor.stability = "-evolving";
               break;
            case "-stable":
               RootDocProcessor.stability = "-stable";
         }
      }

   }

   public static String[][] filterOptions(String[][] options) {
      List<String[]> optionsList = new ArrayList(options.length);

      for(String[] option1 : options) {
         if (!option1[0].equalsIgnoreCase("-unstable") && !option1[0].equalsIgnoreCase("-evolving") && !option1[0].equalsIgnoreCase("-stable")) {
            optionsList.add(option1);
         }
      }

      String[][] filteredOptions = new String[optionsList.size()][];
      int i = 0;

      for(String[] option : optionsList) {
         filteredOptions[i++] = option;
      }

      return filteredOptions;
   }
}
