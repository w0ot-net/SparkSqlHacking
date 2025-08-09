package io.fabric8.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonPointer {
   private final RefToken[] tokens;
   public static final JsonPointer ROOT = new JsonPointer(new RefToken[0]);
   static final int LAST_INDEX = Integer.MIN_VALUE;

   private JsonPointer(RefToken[] tokens) {
      this.tokens = tokens;
   }

   public JsonPointer(List tokens) {
      this.tokens = (RefToken[])tokens.toArray(new RefToken[0]);
   }

   public static JsonPointer parse(String path) throws IllegalArgumentException {
      StringBuilder reftoken = null;
      List<RefToken> result = new ArrayList();

      for(int i = 0; i < path.length(); ++i) {
         char c = path.charAt(i);
         if (i == 0) {
            if (c != '/') {
               throw new IllegalArgumentException("Missing leading slash");
            }

            reftoken = new StringBuilder();
         } else {
            switch (c) {
               case '/':
                  result.add(new RefToken(reftoken.toString()));
                  reftoken.setLength(0);
                  break;
               case '~':
                  ++i;
                  switch (path.charAt(i)) {
                     case '0':
                        reftoken.append('~');
                        continue;
                     case '1':
                        reftoken.append('/');
                        continue;
                     default:
                        char var10002 = path.charAt(i);
                        throw new IllegalArgumentException("Invalid escape sequence ~" + var10002 + " at index " + i);
                  }
               default:
                  reftoken.append(c);
            }
         }
      }

      if (reftoken == null) {
         return ROOT;
      } else {
         result.add(JsonPointer.RefToken.parse(reftoken.toString()));
         return new JsonPointer(result);
      }
   }

   public boolean isRoot() {
      return this.tokens.length == 0;
   }

   JsonPointer append(String field) {
      RefToken[] newTokens = (RefToken[])Arrays.copyOf(this.tokens, this.tokens.length + 1);
      newTokens[this.tokens.length] = new RefToken(field);
      return new JsonPointer(newTokens);
   }

   JsonPointer append(int index) {
      return this.append(Integer.toString(index));
   }

   int size() {
      return this.tokens.length;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(RefToken token : this.tokens) {
         sb.append('/');
         sb.append(token);
      }

      return sb.toString();
   }

   public List decompose() {
      return Arrays.asList((RefToken[])this.tokens.clone());
   }

   public RefToken get(int index) throws IndexOutOfBoundsException {
      if (index >= 0 && index < this.tokens.length) {
         return this.tokens[index];
      } else {
         throw new IndexOutOfBoundsException("Illegal index: " + index);
      }
   }

   public RefToken last() {
      if (this.isRoot()) {
         throw new IllegalStateException("Root pointers contain no reference tokens");
      } else {
         return this.tokens[this.tokens.length - 1];
      }
   }

   public JsonPointer getParent() {
      return this.isRoot() ? this : new JsonPointer((RefToken[])Arrays.copyOf(this.tokens, this.tokens.length - 1));
   }

   private void error(int atToken, String message, JsonNode document) throws JsonPointerEvaluationException {
      throw new JsonPointerEvaluationException(message, new JsonPointer((RefToken[])Arrays.copyOf(this.tokens, atToken)), document);
   }

   public JsonNode evaluate(JsonNode document) throws JsonPointerEvaluationException {
      JsonNode current = document;

      for(int idx = 0; idx < this.tokens.length; ++idx) {
         RefToken token = this.tokens[idx];
         if (current.isArray()) {
            if (!token.isArrayIndex()) {
               this.error(idx, "Can't reference field \"" + token.getField() + "\" on array", document);
            }

            if (token.getIndex() == Integer.MIN_VALUE || token.getIndex() >= current.size()) {
               this.error(idx, "Array index " + token.toString() + " is out of bounds", document);
            }

            current = current.get(token.getIndex());
         } else if (current.isObject()) {
            if (!current.has(token.getField())) {
               this.error(idx, "Missing field \"" + token.getField() + "\"", document);
            }

            current = current.get(token.getField());
         } else {
            this.error(idx, "Can't reference past scalar value", document);
         }
      }

      return current;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         JsonPointer that = (JsonPointer)o;
         return Arrays.equals(this.tokens, that.tokens);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.tokens);
   }

   static class RefToken {
      private String decodedToken;
      private transient Integer index = null;
      private static final Pattern DECODED_TILDA_PATTERN = Pattern.compile("~0");
      private static final Pattern DECODED_SLASH_PATTERN = Pattern.compile("~1");
      private static final Pattern ENCODED_TILDA_PATTERN = Pattern.compile("~");
      private static final Pattern ENCODED_SLASH_PATTERN = Pattern.compile("/");
      private static final Pattern VALID_ARRAY_IND = Pattern.compile("-|0|(?:[1-9][0-9]*)");

      public RefToken(String decodedToken) {
         if (decodedToken == null) {
            throw new IllegalArgumentException("Token can't be null");
         } else {
            this.decodedToken = decodedToken;
         }
      }

      private static String decodePath(Object object) {
         String path = object.toString();
         path = DECODED_SLASH_PATTERN.matcher(path).replaceAll("/");
         return DECODED_TILDA_PATTERN.matcher(path).replaceAll("~");
      }

      private static String encodePath(Object object) {
         String path = object.toString();
         path = ENCODED_TILDA_PATTERN.matcher(path).replaceAll("~0");
         return ENCODED_SLASH_PATTERN.matcher(path).replaceAll("~1");
      }

      public static RefToken parse(String rawToken) {
         if (rawToken == null) {
            throw new IllegalArgumentException("Token can't be null");
         } else {
            return new RefToken(decodePath(rawToken));
         }
      }

      public boolean isArrayIndex() {
         if (this.index != null) {
            return true;
         } else {
            Matcher matcher = VALID_ARRAY_IND.matcher(this.decodedToken);
            if (matcher.matches()) {
               this.index = matcher.group().equals("-") ? Integer.MIN_VALUE : Integer.parseInt(matcher.group());
               return true;
            } else {
               return false;
            }
         }
      }

      public int getIndex() {
         if (!this.isArrayIndex()) {
            throw new IllegalStateException("Object operation on array target");
         } else {
            return this.index;
         }
      }

      public String getField() {
         return this.decodedToken;
      }

      public String toString() {
         return encodePath(this.decodedToken);
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            RefToken refToken = (RefToken)o;
            return this.decodedToken.equals(refToken.decodedToken);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.decodedToken.hashCode();
      }
   }
}
