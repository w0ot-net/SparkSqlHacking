package org.apache.yetus.audience.tools;

import com.sun.javadoc.DocErrorReporter;
import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.standard.Standard;
import org.apache.yetus.audience.InterfaceAudience;
import org.apache.yetus.audience.InterfaceStability;

@InterfaceAudience.Public
@InterfaceStability.Evolving
public class ExcludePrivateAnnotationsStandardDoclet {
   public static LanguageVersion languageVersion() {
      return LanguageVersion.JAVA_1_5;
   }

   public static boolean start(RootDoc root) {
      return Standard.start(RootDocProcessor.process(root));
   }

   public static int optionLength(String option) {
      Integer length = StabilityOptions.optionLength(option);
      return length != null ? length : Standard.optionLength(option);
   }

   public static boolean validOptions(String[][] options, DocErrorReporter reporter) {
      StabilityOptions.validOptions(options, reporter);
      String[][] filteredOptions = StabilityOptions.filterOptions(options);
      return Standard.validOptions(filteredOptions, reporter);
   }
}
