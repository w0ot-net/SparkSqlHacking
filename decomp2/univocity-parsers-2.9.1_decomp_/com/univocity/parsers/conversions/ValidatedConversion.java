package com.univocity.parsers.conversions;

import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.DataValidationException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatedConversion implements Conversion {
   private final String regexToMatch;
   private final boolean nullable;
   private final boolean allowBlanks;
   private final Set oneOf;
   private final Set noneOf;
   private final Matcher matcher;
   private final Validator[] validators;

   public ValidatedConversion() {
      this(false, false, (String[])null, (String[])null, (String)null);
   }

   public ValidatedConversion(String regexToMatch) {
      this(false, false, (String[])null, (String[])null, regexToMatch);
   }

   public ValidatedConversion(boolean nullable, boolean allowBlanks) {
      this(nullable, allowBlanks, (String[])null, (String[])null, (String)null);
   }

   public ValidatedConversion(boolean nullable, boolean allowBlanks, String[] oneOf, String[] noneOf, String regexToMatch) {
      this(nullable, allowBlanks, oneOf, noneOf, regexToMatch, (Class[])null);
   }

   public ValidatedConversion(boolean nullable, boolean allowBlanks, String[] oneOf, String[] noneOf, String regexToMatch, Class[] validators) {
      this.regexToMatch = regexToMatch;
      this.matcher = regexToMatch != null && !regexToMatch.isEmpty() ? Pattern.compile(regexToMatch).matcher("") : null;
      this.nullable = nullable;
      this.allowBlanks = allowBlanks;
      this.oneOf = oneOf != null && oneOf.length != 0 ? new HashSet(Arrays.asList(oneOf)) : null;
      this.noneOf = noneOf != null && noneOf.length != 0 ? new HashSet(Arrays.asList(noneOf)) : null;
      this.validators = validators != null && validators.length != 0 ? this.instantiateValidators(validators) : new Validator[0];
   }

   private Validator[] instantiateValidators(Class[] validators) {
      Validator[] out = new Validator[validators.length];

      for(int i = 0; i < validators.length; ++i) {
         out[i] = (Validator)AnnotationHelper.newInstance(Validator.class, validators[i], ArgumentUtils.EMPTY_STRING_ARRAY);
      }

      return out;
   }

   public Object execute(Object input) {
      this.validate(input);
      return input;
   }

   public Object revert(Object input) {
      this.validate(input);
      return input;
   }

   protected void validate(Object value) {
      DataValidationException e = null;
      String str = null;
      if (value == null) {
         if (this.nullable) {
            if (this.noneOf == null || !this.noneOf.contains((Object)null)) {
               return;
            }

            e = new DataValidationException("Value '{value}' is not allowed.");
         } else {
            if (this.oneOf != null && this.oneOf.contains((Object)null)) {
               return;
            }

            e = new DataValidationException("Null values not allowed.");
         }
      } else {
         str = String.valueOf(value);
         if (str.trim().isEmpty()) {
            if (this.allowBlanks) {
               if (this.noneOf == null || !this.noneOf.contains(str)) {
                  return;
               }

               e = new DataValidationException("Value '{value}' is not allowed.");
            } else {
               if (this.oneOf != null && this.oneOf.contains(str)) {
                  return;
               }

               e = new DataValidationException("Blanks are not allowed. '{value}' is blank.");
            }
         }

         if (this.matcher != null && e == null) {
            boolean match;
            synchronized(this.matcher) {
               match = this.matcher.reset(str).matches();
            }

            if (!match) {
               e = new DataValidationException("Value '{value}' does not match expected pattern: '" + this.regexToMatch + "'");
            }
         }
      }

      if (this.oneOf != null && !this.oneOf.contains(str)) {
         e = new DataValidationException("Value '{value}' is not allowed. Expecting one of: " + this.oneOf);
      }

      if (e == null && this.noneOf != null && this.noneOf.contains(str)) {
         e = new DataValidationException("Value '{value}' is not allowed.");
      }

      for(int i = 0; e == null && i < this.validators.length; ++i) {
         String error = this.validators[i].validate(value);
         if (error != null && !error.trim().isEmpty()) {
            e = new DataValidationException("Value '{value}' didn't pass validation: " + error);
         }
      }

      if (e != null) {
         e.setValue(value);
         throw e;
      }
   }
}
