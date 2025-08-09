package org.apache.commons.text.similarity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

final class RegexTokenizer implements CharSequenceTokenizer {
   private static final Pattern PATTERN = Pattern.compile("(\\w)+");
   static final RegexTokenizer INSTANCE = new RegexTokenizer();

   public CharSequence[] apply(CharSequence text) {
      Validate.isTrue(StringUtils.isNotBlank(text), "Invalid text", new Object[0]);
      Matcher matcher = PATTERN.matcher(text);
      List<String> tokens = new ArrayList();

      while(matcher.find()) {
         tokens.add(matcher.group(0));
      }

      return (CharSequence[])tokens.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
   }
}
