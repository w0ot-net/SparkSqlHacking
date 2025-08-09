package org.apache.ivy.osgi.filter;

import java.text.ParseException;

public class OSGiFilterParser {
   public static OSGiFilter parse(String text) throws ParseException {
      return (new Parser(text)).parse();
   }

   static class Parser {
      private final String text;
      private int length;
      private int pos = 0;
      private char c;

      Parser(String text) {
         this.text = text;
         this.length = text.length();
      }

      OSGiFilter parse() throws ParseException {
         return this.parseFilter();
      }

      private char readNext() {
         if (this.pos == this.length) {
            this.c = 0;
         } else {
            this.c = this.text.charAt(this.pos++);
         }

         return this.c;
      }

      private void unread() {
         if (this.pos > 0) {
            --this.pos;
         }

      }

      private OSGiFilter parseFilter() throws ParseException {
         this.skipWhiteSpace();
         this.readNext();
         if (this.c != '(') {
            throw new ParseException("Expecting '(' as the start of the filter", this.pos);
         } else {
            OSGiFilter filter = this.parseFilterComp();
            this.readNext();
            if (this.c != ')') {
               throw new ParseException("Expecting ')' as the end of the filter", this.pos);
            } else {
               return filter;
            }
         }
      }

      private OSGiFilter parseFilterComp() throws ParseException {
         OSGiFilter filter;
         switch (this.readNext()) {
            case '!':
               filter = this.parseNot();
               break;
            case '&':
               filter = this.parseAnd();
               break;
            case '|':
               filter = this.parseOr();
               break;
            default:
               this.unread();
               filter = this.parseOperation();
         }

         return filter;
      }

      private OSGiFilter parseOperation() throws ParseException {
         String leftValue = this.parseCompareValue();
         CompareFilter.Operator operator = this.parseCompareOperator();
         String rightValue = this.parseCompareValue();
         return new CompareFilter(leftValue, operator, rightValue);
      }

      private String parseCompareValue() {
         StringBuilder builder = new StringBuilder();

         do {
            this.readNext();
            if (this.isOperator(this.c) || this.c == ')' || this.c == '(') {
               this.unread();
               break;
            }

            builder.append(this.c);
         } while(this.pos < this.length);

         return builder.toString();
      }

      private boolean isOperator(char ch) {
         return ch == '=' || ch == '<' || ch == '>' || ch == '~';
      }

      private CompareFilter.Operator parseCompareOperator() throws ParseException {
         switch (this.readNext()) {
            case '<':
               if (this.readNext() == '=') {
                  return CompareFilter.Operator.LOWER_OR_EQUAL;
               }

               this.unread();
               return CompareFilter.Operator.LOWER_THAN;
            case '=':
               if (this.readNext() == '*') {
                  return CompareFilter.Operator.PRESENT;
               }

               this.unread();
               return CompareFilter.Operator.EQUALS;
            case '>':
               if (this.readNext() == '=') {
                  return CompareFilter.Operator.GREATER_OR_EQUAL;
               }

               this.unread();
               return CompareFilter.Operator.GREATER_THAN;
            case '~':
               if (this.readNext() == '=') {
                  return CompareFilter.Operator.LOWER_OR_EQUAL;
               } else {
                  this.unread();
               }
            default:
               throw new ParseException("Expecting an operator: =, <, <=, >, >=, ~= or =*", this.pos);
         }
      }

      private OSGiFilter parseAnd() throws ParseException {
         AndFilter filter = new AndFilter();
         this.parseFilterList(filter);
         return filter;
      }

      private OSGiFilter parseOr() throws ParseException {
         OrFilter filter = new OrFilter();
         this.parseFilterList(filter);
         return filter;
      }

      private void parseFilterList(MultiOperatorFilter filter) throws ParseException {
         while(true) {
            this.skipWhiteSpace();
            this.readNext();
            if (this.c == '(') {
               this.unread();
               filter.add(this.parseFilter());
               if (this.pos < this.length) {
                  continue;
               }
            } else {
               this.unread();
            }

            if (filter.getSubFilters().size() == 0) {
               throw new ParseException("Expecting at least one sub filter", this.pos);
            }

            return;
         }
      }

      private OSGiFilter parseNot() throws ParseException {
         this.readNext();
         if (this.c != '(') {
            throw new ParseException("The ! operator is expecting a filter", this.pos);
         } else {
            this.unread();
            return new NotFilter(this.parseFilter());
         }
      }

      private void skipWhiteSpace() {
         while(true) {
            switch (this.readNext()) {
               case ' ':
                  if (this.pos < this.length) {
                     break;
                  }

                  return;
               default:
                  this.unread();
                  return;
            }
         }
      }
   }
}
