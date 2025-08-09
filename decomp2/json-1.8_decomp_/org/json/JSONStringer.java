package org.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONStringer {
   final StringBuilder out = new StringBuilder();
   private final List stack = new ArrayList();
   private final String indent;

   public JSONStringer() {
      this.indent = null;
   }

   JSONStringer(int indentSpaces) {
      char[] indentChars = new char[indentSpaces];
      Arrays.fill(indentChars, ' ');
      this.indent = new String(indentChars);
   }

   public JSONStringer array() throws JSONException {
      return this.open(JSONStringer.Scope.EMPTY_ARRAY, "[");
   }

   public JSONStringer endArray() throws JSONException {
      return this.close(JSONStringer.Scope.EMPTY_ARRAY, JSONStringer.Scope.NONEMPTY_ARRAY, "]");
   }

   public JSONStringer object() throws JSONException {
      return this.open(JSONStringer.Scope.EMPTY_OBJECT, "{");
   }

   public JSONStringer endObject() throws JSONException {
      return this.close(JSONStringer.Scope.EMPTY_OBJECT, JSONStringer.Scope.NONEMPTY_OBJECT, "}");
   }

   JSONStringer open(Scope empty, String openBracket) throws JSONException {
      if (this.stack.isEmpty() && this.out.length() > 0) {
         throw new JSONException("Nesting problem: multiple top-level roots");
      } else {
         this.beforeValue();
         this.stack.add(empty);
         this.out.append(openBracket);
         return this;
      }
   }

   JSONStringer close(Scope empty, Scope nonempty, String closeBracket) throws JSONException {
      Scope context = this.peek();
      if (context != nonempty && context != empty) {
         throw new JSONException("Nesting problem");
      } else {
         this.stack.remove(this.stack.size() - 1);
         if (context == nonempty) {
            this.newline();
         }

         this.out.append(closeBracket);
         return this;
      }
   }

   private Scope peek() throws JSONException {
      if (this.stack.isEmpty()) {
         throw new JSONException("Nesting problem");
      } else {
         return (Scope)this.stack.get(this.stack.size() - 1);
      }
   }

   private void replaceTop(Scope topOfStack) {
      this.stack.set(this.stack.size() - 1, topOfStack);
   }

   public JSONStringer value(Object value) throws JSONException {
      if (this.stack.isEmpty()) {
         throw new JSONException("Nesting problem");
      } else if (value instanceof JSONArray) {
         ((JSONArray)value).writeTo(this);
         return this;
      } else if (value instanceof JSONObject) {
         ((JSONObject)value).writeTo(this);
         return this;
      } else {
         this.beforeValue();
         if (value instanceof JSONString) {
            this.out.append(((JSONString)value).toJSONString());
            return this;
         } else {
            if (value != null && !(value instanceof Boolean) && value != JSONObject.NULL) {
               if (value instanceof Number) {
                  this.out.append(JSONObject.numberToString((Number)value));
               } else if (value.getClass().getSimpleName().contains("JSONFunction")) {
                  this.out.append(value);
               } else {
                  this.string(value.toString());
               }
            } else {
               this.out.append(value);
            }

            return this;
         }
      }
   }

   public JSONStringer value(boolean value) throws JSONException {
      if (this.stack.isEmpty()) {
         throw new JSONException("Nesting problem");
      } else {
         this.beforeValue();
         this.out.append(value);
         return this;
      }
   }

   public JSONStringer value(double value) throws JSONException {
      if (this.stack.isEmpty()) {
         throw new JSONException("Nesting problem");
      } else {
         this.beforeValue();
         this.out.append(JSONObject.numberToString(value));
         return this;
      }
   }

   public JSONStringer value(long value) throws JSONException {
      if (this.stack.isEmpty()) {
         throw new JSONException("Nesting problem");
      } else {
         this.beforeValue();
         this.out.append(value);
         return this;
      }
   }

   private void string(String value) {
      this.out.append("\"");
      char currentChar = 0;
      int i = 0;

      for(int length = value.length(); i < length; ++i) {
         char previousChar = currentChar;
         currentChar = value.charAt(i);
         switch (currentChar) {
            case '\b':
               this.out.append("\\b");
               break;
            case '\t':
               this.out.append("\\t");
               break;
            case '\n':
               this.out.append("\\n");
               break;
            case '\f':
               this.out.append("\\f");
               break;
            case '\r':
               this.out.append("\\r");
               break;
            case '"':
            case '\\':
               this.out.append('\\').append(currentChar);
               break;
            case '/':
               if (previousChar == '<') {
                  this.out.append('\\');
               }

               this.out.append(currentChar);
               break;
            default:
               if (currentChar <= 31) {
                  this.out.append(String.format("\\u%04x", Integer.valueOf(currentChar)));
               } else {
                  this.out.append(currentChar);
               }
         }
      }

      this.out.append("\"");
   }

   private void newline() {
      if (this.indent != null) {
         this.out.append("\n");

         for(int i = 0; i < this.stack.size(); ++i) {
            this.out.append(this.indent);
         }

      }
   }

   public JSONStringer key(String name) throws JSONException {
      if (name == null) {
         throw new JSONException("Names must be non-null");
      } else {
         this.beforeKey();
         this.string(name);
         return this;
      }
   }

   private void beforeKey() throws JSONException {
      Scope context = this.peek();
      if (context == JSONStringer.Scope.NONEMPTY_OBJECT) {
         this.out.append(',');
      } else if (context != JSONStringer.Scope.EMPTY_OBJECT) {
         throw new JSONException("Nesting problem");
      }

      this.newline();
      this.replaceTop(JSONStringer.Scope.DANGLING_KEY);
   }

   private void beforeValue() throws JSONException {
      if (!this.stack.isEmpty()) {
         Scope context = this.peek();
         if (context == JSONStringer.Scope.EMPTY_ARRAY) {
            this.replaceTop(JSONStringer.Scope.NONEMPTY_ARRAY);
            this.newline();
         } else if (context == JSONStringer.Scope.NONEMPTY_ARRAY) {
            this.out.append(',');
            this.newline();
         } else if (context == JSONStringer.Scope.DANGLING_KEY) {
            this.out.append(this.indent == null ? ":" : ": ");
            this.replaceTop(JSONStringer.Scope.NONEMPTY_OBJECT);
         } else if (context != JSONStringer.Scope.NULL) {
            throw new JSONException("Nesting problem");
         }

      }
   }

   public String toString() {
      return this.out.length() == 0 ? null : this.out.toString();
   }

   static enum Scope {
      EMPTY_ARRAY,
      NONEMPTY_ARRAY,
      EMPTY_OBJECT,
      DANGLING_KEY,
      NONEMPTY_OBJECT,
      NULL;
   }
}
