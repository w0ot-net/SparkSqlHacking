package org.apache.ivy.osgi.core;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManifestHeaderValue {
   private List elements = new ArrayList();

   ManifestHeaderValue() {
   }

   public ManifestHeaderValue(String header) throws ParseException {
      if (header != null) {
         (new ManifestHeaderParser(header)).parse();
      }

   }

   public List getElements() {
      return this.elements;
   }

   public String getSingleValue() {
      if (this.elements.isEmpty()) {
         return null;
      } else {
         List<String> values = ((ManifestHeaderElement)this.getElements().iterator().next()).getValues();
         return values.isEmpty() ? null : (String)values.iterator().next();
      }
   }

   public List getValues() {
      if (this.elements.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<String> list = new ArrayList();

         for(ManifestHeaderElement element : this.getElements()) {
            list.addAll(element.getValues());
         }

         return list;
      }
   }

   void addElement(ManifestHeaderElement element) {
      this.elements.add(element);
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof ManifestHeaderValue)) {
         return false;
      } else {
         ManifestHeaderValue other = (ManifestHeaderValue)obj;
         if (other.elements.size() != this.elements.size()) {
            return false;
         } else {
            for(ManifestHeaderElement element : this.elements) {
               if (!other.elements.contains(element)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(ManifestHeaderElement element : this.elements) {
         if (sb.length() > 0) {
            sb.append(",");
         }

         sb.append(element.toString());
      }

      return sb.toString();
   }

   public static void writeParseException(PrintStream out, String source, ParseException e) {
      out.println(e.getMessage());
      out.print("   " + source + "\n   ");

      for(int i = 0; i < e.getErrorOffset(); ++i) {
         out.print(' ');
      }

      out.println('^');
   }

   class ManifestHeaderParser {
      private final String header;
      private int length;
      private StringBuilder buffer = new StringBuilder();
      private int pos = 0;
      private char c;
      private ManifestHeaderElement elem = new ManifestHeaderElement();
      private boolean valuesParsed;
      private String paramName;
      private boolean isDirective;

      ManifestHeaderParser(String header) {
         this.header = header;
         this.length = header.length();
      }

      void parse() throws ParseException {
         do {
            this.elem = new ManifestHeaderElement();
            int posElement = this.pos;
            this.parseElement();
            if (this.elem.getValues().isEmpty()) {
               this.error("No defined value", posElement);
            } else {
               ManifestHeaderValue.this.addElement(this.elem);
            }
         } while(this.pos < this.length);

      }

      private char readNext() {
         if (this.pos == this.length) {
            this.c = 0;
         } else {
            this.c = this.header.charAt(this.pos++);
         }

         return this.c;
      }

      private void error(String message) throws ParseException {
         this.error(message, this.pos - 1);
      }

      private void error(String message, int p) throws ParseException {
         throw new ParseException(message, p);
      }

      private void parseElement() throws ParseException {
         this.valuesParsed = false;

         do {
            this.parseValueOrParameter();
         } while(this.c == ';' && this.pos < this.length);

      }

      private void parseValueOrParameter() throws ParseException {
         boolean start = false;

         do {
            switch (this.readNext()) {
               case '\u0000':
                  break;
               case '\t':
               case '\n':
               case '\r':
               case ' ':
                  if (start) {
                     this.buffer.append(this.c);
                  }
                  break;
               case ',':
               case ';':
                  this.endValue();
                  return;
               case ':':
               case '=':
                  this.endParameterName();
                  this.parseSeparator();
                  this.parseParameterValue();
                  return;
               default:
                  start = true;
                  this.buffer.append(this.c);
            }
         } while(this.pos < this.length);

         this.endValue();
      }

      private void endValue() throws ParseException {
         if (this.valuesParsed) {
            this.error("Early end of a parameter");
            this.buffer.setLength(0);
         } else {
            if (this.buffer.length() == 0) {
               this.error("Empty value");
            }

            this.elem.addValue(this.buffer.toString());
            this.buffer.setLength(0);
         }
      }

      private void endParameterName() throws ParseException {
         if (this.buffer.length() == 0) {
            this.error("Empty parameter name");
            this.paramName = null;
         }

         this.paramName = this.buffer.toString();
         this.buffer.setLength(0);
      }

      private void parseSeparator() throws ParseException {
         if (this.c == '=') {
            this.isDirective = false;
         } else {
            if (this.readNext() != '=') {
               this.error("Expecting '='");
               --this.pos;
               this.paramName = null;
            }

            this.isDirective = true;
         }
      }

      private void parseParameterValue() throws ParseException {
         boolean start = false;
         boolean end = false;
         boolean doubleQuoted = false;

         do {
            switch (this.readNext()) {
               case '\u0000':
                  break;
               case '\t':
               case '\n':
               case '\r':
               case ' ':
                  if (start) {
                     end = true;
                  }
                  break;
               case '"':
                  doubleQuoted = true;
               case '\'':
                  if (end && this.paramName != null) {
                     this.error("Expecting the end of a parameter value");
                     this.paramName = null;
                  }

                  if (start) {
                     this.buffer.append(this.c);
                  } else {
                     start = true;
                     this.appendQuoted(doubleQuoted);
                     end = true;
                  }
                  break;
               case ',':
               case ';':
                  this.endParameterValue();
                  return;
               case ':':
               case '=':
                  this.error("Illegal character '" + this.c + "' in parameter value of " + this.paramName);
                  this.paramName = null;
                  break;
               case '\\':
                  if (end && this.paramName != null) {
                     this.error("Expecting the end of a parameter value");
                     this.paramName = null;
                  }

                  start = true;
                  this.appendEscaped();
                  break;
               default:
                  if (end && this.paramName != null) {
                     this.error("Expecting the end of a parameter value");
                     this.paramName = null;
                  }

                  start = true;
                  this.buffer.append(this.c);
            }
         } while(this.pos < this.length);

         this.endParameterValue();
      }

      private void endParameterValue() throws ParseException {
         if (this.paramName != null) {
            if (this.buffer.length() == 0) {
               this.error("Empty parameter value");
            } else {
               String value = this.buffer.toString();
               if (this.isDirective) {
                  this.elem.addDirective(this.paramName, value);
               } else {
                  this.elem.addAttribute(this.paramName, value);
               }

               this.valuesParsed = true;
               this.buffer.setLength(0);
            }
         }
      }

      private void appendQuoted(boolean doubleQuoted) {
         do {
            switch (this.readNext()) {
               case '\u0000':
               case '\\':
                  break;
               case '"':
                  if (doubleQuoted) {
                     return;
                  }

                  this.buffer.append(this.c);
                  break;
               case '\'':
                  if (!doubleQuoted) {
                     return;
                  }

                  this.buffer.append(this.c);
                  break;
               default:
                  this.buffer.append(this.c);
            }
         } while(this.pos < this.length);

      }

      private void appendEscaped() {
         if (this.pos < this.length) {
            this.buffer.append(this.readNext());
         } else {
            this.buffer.append(this.c);
         }

      }
   }
}
