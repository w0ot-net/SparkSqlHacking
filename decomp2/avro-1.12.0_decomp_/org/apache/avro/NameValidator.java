package org.apache.avro;

public interface NameValidator {
   Result OK = new Result((String)null);
   NameValidator NO_VALIDATION = new NameValidator() {
   };
   NameValidator UTF_VALIDATOR = new NameValidator() {
      public Result validate(final String name) {
         if (name == null) {
            return new Result("Null name");
         } else {
            int length = name.length();
            if (length == 0) {
               return new Result("Empty name");
            } else {
               char first = name.charAt(0);
               if (!Character.isLetter(first) && first != '_') {
                  return new Result("Illegal initial character: " + name);
               } else {
                  for(int i = 1; i < length; ++i) {
                     char c = name.charAt(i);
                     if (!Character.isLetterOrDigit(c) && c != '_') {
                        return new Result("Illegal character in: " + name);
                     }
                  }

                  return OK;
               }
            }
         }
      }
   };
   NameValidator STRICT_VALIDATOR = new NameValidator() {
      public Result validate(final String name) {
         if (name == null) {
            return new Result("Null name");
         } else {
            int length = name.length();
            if (length == 0) {
               return new Result("Empty name");
            } else {
               char first = name.charAt(0);
               if (!this.isLetter(first) && first != '_') {
                  return new Result("Illegal initial character: " + name);
               } else {
                  for(int i = 1; i < length; ++i) {
                     char c = name.charAt(i);
                     if (!this.isLetter(c) && !this.isDigit(c) && c != '_') {
                        return new Result("Illegal character in: " + name);
                     }
                  }

                  return OK;
               }
            }
         }
      }

      private boolean isLetter(char c) {
         return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
      }

      private boolean isDigit(char c) {
         return c >= '0' && c <= '9';
      }
   };

   default Result validate(String name) {
      return OK;
   }

   public static class Result {
      private final String errors;

      public Result(final String errors) {
         this.errors = errors;
      }

      public boolean isOK() {
         return this == NameValidator.OK;
      }

      public String getErrors() {
         return this.errors;
      }
   }
}
