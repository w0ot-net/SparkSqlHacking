package org.datanucleus.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassConstants;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.query.Query;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class JPQLSingleStringParser {
   private Query query;
   private String queryString;

   public JPQLSingleStringParser(Query query, String queryString) {
      if (NucleusLogger.QUERY.isDebugEnabled()) {
         NucleusLogger.QUERY.debug(Localiser.msg("043000", queryString));
      }

      this.query = query;
      this.queryString = queryString;
   }

   public void parse() {
      (new Compiler(new Parser(this.queryString))).compile();
   }

   private class Compiler {
      Parser parser;
      int subqueryNum = 1;

      Compiler(Parser tokenizer) {
         this.parser = tokenizer;
      }

      private void compile() {
         this.compileQuery();
         String keyword = this.parser.parseKeyword();
         if (keyword != null && JPQLQueryHelper.isKeyword(keyword)) {
            throw new NucleusUserException(Localiser.msg("043001", keyword));
         }
      }

      private void compileQuery() {
         boolean update = false;
         boolean delete = false;
         if (!this.parser.parseKeywordIgnoreCase("SELECT")) {
            if (this.parser.parseKeywordIgnoreCase("UPDATE")) {
               update = true;
               JPQLSingleStringParser.this.query.setType((short)1);
            } else {
               if (!this.parser.parseKeywordIgnoreCase("DELETE")) {
                  throw new NucleusUserException(Localiser.msg("043002"));
               }

               delete = true;
               JPQLSingleStringParser.this.query.setType((short)2);
            }
         }

         if (update) {
            this.compileUpdate();
            if (this.parser.parseKeywordIgnoreCase("WHERE")) {
               this.compileWhere();
            }
         } else if (delete) {
            if (this.parser.parseKeywordIgnoreCase("FROM")) {
               this.compileFrom();
            }

            if (this.parser.parseKeywordIgnoreCase("WHERE")) {
               this.compileWhere();
            }
         } else {
            this.compileResult();
            if (this.parser.parseKeywordIgnoreCase("FROM")) {
               this.compileFrom();
            }

            if (this.parser.parseKeywordIgnoreCase("WHERE")) {
               this.compileWhere();
            }

            if (this.parser.parseKeywordIgnoreCase("GROUP BY")) {
               this.compileGroup();
            }

            if (this.parser.parseKeywordIgnoreCase("HAVING")) {
               this.compileHaving();
            }

            if (this.parser.parseKeywordIgnoreCase("ORDER BY")) {
               this.compileOrder();
            }
         }

      }

      private void compileResult() {
         String content = this.parser.parseContent((String)null, true);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("043004", "SELECT", "<result>"));
         } else {
            if (content.toUpperCase().indexOf("SELECT ") > 0) {
               String substitutedContent = this.processContentWithSubqueries(content);
               JPQLSingleStringParser.this.query.setResult(substitutedContent);
            } else {
               JPQLSingleStringParser.this.query.setResult(content);
            }

         }
      }

      private void compileUpdate() {
         String content = this.parser.parseContent((String)null, true);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("043010"));
         } else {
            if (content.toUpperCase().indexOf("SELECT ") > 0) {
               String substitutedContent = this.processContentWithSubqueries(content);
               String contentUpper = substitutedContent.toUpperCase();
               int setIndex = contentUpper.indexOf("SET");
               if (setIndex < 0) {
                  throw new NucleusUserException(Localiser.msg("043011"));
               }

               JPQLSingleStringParser.this.query.setFrom(substitutedContent.substring(0, setIndex).trim());
               JPQLSingleStringParser.this.query.setUpdate(substitutedContent.substring(setIndex + 3).trim());
            } else {
               String contentUpper = content.toUpperCase();
               int setIndex = contentUpper.indexOf("SET");
               if (setIndex < 0) {
                  throw new NucleusUserException(Localiser.msg("043011"));
               }

               JPQLSingleStringParser.this.query.setFrom(content.substring(0, setIndex).trim());
               JPQLSingleStringParser.this.query.setUpdate(content.substring(setIndex + 3).trim());
            }

         }
      }

      private void compileFrom() {
         String content = this.parser.parseContent((String)null, true);
         if (content.length() > 0) {
            if (content.toUpperCase().indexOf("SELECT ") > 0) {
               String substitutedContent = this.processContentWithSubqueries(content);
               JPQLSingleStringParser.this.query.setFrom(substitutedContent);
            } else {
               JPQLSingleStringParser.this.query.setFrom(content);
            }
         }

      }

      private void compileWhere() {
         String content = this.parser.parseContent("FROM", true);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("043004", "WHERE", "<filter>"));
         } else {
            if (content.toUpperCase().indexOf("SELECT ") > 0) {
               String substitutedContent = this.processContentWithSubqueries(content);
               JPQLSingleStringParser.this.query.setFilter(substitutedContent);
            } else {
               JPQLSingleStringParser.this.query.setFilter(content);
            }

         }
      }

      private void compileGroup() {
         String content = this.parser.parseContent("FROM", true);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("043004", "GROUP BY", "<grouping>"));
         } else {
            if (content.toUpperCase().indexOf("SELECT ") > 0) {
               String substitutedContent = this.processContentWithSubqueries(content);
               JPQLSingleStringParser.this.query.setGrouping(substitutedContent);
            } else {
               JPQLSingleStringParser.this.query.setGrouping(content);
            }

         }
      }

      private void compileHaving() {
         String content = this.parser.parseContent("FROM", true);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("043004", "HAVING", "<having>"));
         } else {
            if (content.toUpperCase().indexOf("SELECT ") > 0) {
               String substitutedContent = this.processContentWithSubqueries(content);
               JPQLSingleStringParser.this.query.setHaving(substitutedContent);
            } else {
               JPQLSingleStringParser.this.query.setHaving(content);
            }

         }
      }

      private void compileOrder() {
         String content = this.parser.parseContent("FROM", true);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("043004", "ORDER", "<ordering>"));
         } else {
            if (content.toUpperCase().indexOf("SELECT ") > 0) {
               String substitutedContent = this.processContentWithSubqueries(content);
               JPQLSingleStringParser.this.query.setOrdering(substitutedContent);
            } else {
               JPQLSingleStringParser.this.query.setOrdering(content);
            }

         }
      }

      private String processContentWithSubqueries(String content) {
         StringBuilder stringContent = new StringBuilder();
         boolean withinLiteralDouble = false;
         boolean withinLiteralSingle = false;

         for(int i = 0; i < content.length(); ++i) {
            boolean subqueryProcessed = false;
            char chr = content.charAt(i);
            if (chr == '"') {
               withinLiteralDouble = !withinLiteralDouble;
            } else if (chr == '\'') {
               withinLiteralSingle = !withinLiteralSingle;
            }

            if (!withinLiteralDouble && !withinLiteralSingle && chr == '(') {
               String remains = content.substring(i + 1).trim();
               if (remains.toUpperCase().startsWith("SELECT")) {
                  remains = content.substring(i);
                  int endPosition = -1;
                  int braceLevel = 0;

                  for(int j = 1; j < remains.length(); ++j) {
                     if (remains.charAt(j) == '(') {
                        ++braceLevel;
                     } else if (remains.charAt(j) == ')') {
                        --braceLevel;
                        if (braceLevel < 0) {
                           endPosition = i + j;
                           break;
                        }
                     }
                  }

                  if (endPosition < 0) {
                     throw new NucleusUserException(Localiser.msg("042017"));
                  }

                  String subqueryStr = content.substring(i + 1, endPosition).trim();
                  String subqueryVarName = "DN_SUBQUERY_" + this.subqueryNum;
                  Query subquery = (Query)ClassUtils.newInstance(JPQLSingleStringParser.this.query.getClass(), new Class[]{ClassConstants.STORE_MANAGER, ClassConstants.EXECUTION_CONTEXT, String.class}, new Object[]{JPQLSingleStringParser.this.query.getStoreManager(), JPQLSingleStringParser.this.query.getExecutionContext(), subqueryStr});
                  JPQLSingleStringParser.this.query.addSubquery(subquery, "double " + subqueryVarName, (String)null, (Map)null);
                  if (stringContent.length() > 0 && stringContent.charAt(stringContent.length() - 1) != ' ') {
                     stringContent.append(' ');
                  }

                  stringContent.append(subqueryVarName);
                  i = endPosition;
                  ++this.subqueryNum;
                  subqueryProcessed = true;
               }
            }

            if (!subqueryProcessed) {
               stringContent.append(chr);
            }
         }

         if (!withinLiteralDouble && !withinLiteralSingle) {
            return stringContent.toString();
         } else {
            throw new NucleusUserException(Localiser.msg("042017"));
         }
      }
   }

   private static class Parser {
      final String queryString;
      int queryStringPos = 0;
      final String[] tokens;
      final String[] keywords;
      int tokenIndex = -1;

      public Parser(String str) {
         this.queryString = str.replace('\n', ' ');
         List<String> tokenList = new ArrayList();
         boolean withinSingleQuote = false;
         boolean withinDoubleQuote = false;
         StringBuilder currentToken = new StringBuilder();

         for(int i = 0; i < this.queryString.length(); ++i) {
            char chr = this.queryString.charAt(i);
            if (chr == '"') {
               withinDoubleQuote = !withinDoubleQuote;
               currentToken.append(chr);
            } else if (chr == '\'') {
               withinSingleQuote = !withinSingleQuote;
               currentToken.append(chr);
            } else if (chr == ' ') {
               if (!withinDoubleQuote && !withinSingleQuote) {
                  tokenList.add(currentToken.toString().trim());
                  currentToken = new StringBuilder();
               } else {
                  currentToken.append(chr);
               }
            } else {
               currentToken.append(chr);
            }
         }

         if (currentToken.length() > 0) {
            tokenList.add(currentToken.toString());
         }

         this.tokens = new String[tokenList.size()];
         int i = 0;

         for(String token : tokenList) {
            this.tokens[i++] = token;
         }

         this.keywords = new String[tokenList.size()];

         for(int var10 = 0; var10 < this.tokens.length; ++var10) {
            if (JPQLQueryHelper.isKeyword(this.tokens[var10])) {
               this.keywords[var10] = this.tokens[var10];
            } else if (var10 < this.tokens.length - 1 && JPQLQueryHelper.isKeyword(this.tokens[var10] + ' ' + this.tokens[var10 + 1])) {
               this.keywords[var10] = this.tokens[var10];
               ++var10;
               this.keywords[var10] = this.tokens[var10];
            }
         }

      }

      public String parseContent(String keywordToIgnore, boolean allowSubentries) {
         String content = "";
         int level = 0;

         while(this.tokenIndex < this.tokens.length - 1) {
            ++this.tokenIndex;
            if (allowSubentries) {
               for(int i = 0; i < this.tokens[this.tokenIndex].length(); ++i) {
                  char c = this.tokens[this.tokenIndex].charAt(i);
                  if (c == '(') {
                     ++level;
                  } else if (c == ')') {
                     --level;
                  }
               }
            }

            if (level == 0 && JPQLQueryHelper.isKeyword(this.tokens[this.tokenIndex]) && !this.tokens[this.tokenIndex].equals(keywordToIgnore)) {
               --this.tokenIndex;
               break;
            }

            if (level == 0 && this.tokenIndex < this.tokens.length - 1 && JPQLQueryHelper.isKeyword(this.tokens[this.tokenIndex] + ' ' + this.tokens[this.tokenIndex + 1])) {
               --this.tokenIndex;
               break;
            }

            int endPos = this.queryString.indexOf(this.tokens[this.tokenIndex], this.queryStringPos) + this.tokens[this.tokenIndex].length();
            String contentValue = this.queryString.substring(this.queryStringPos, endPos);
            this.queryStringPos = endPos;
            if (content.length() == 0) {
               content = contentValue;
            } else {
               content = content + contentValue;
            }
         }

         return content;
      }

      public boolean parseKeywordIgnoreCase(String keyword) {
         if (this.tokenIndex < this.tokens.length - 1) {
            ++this.tokenIndex;
            if (this.keywords[this.tokenIndex] != null) {
               if (this.keywords[this.tokenIndex].equalsIgnoreCase(keyword)) {
                  this.queryStringPos = this.queryString.indexOf(this.keywords[this.tokenIndex], this.queryStringPos) + this.keywords[this.tokenIndex].length() + 1;
                  return true;
               }

               if (keyword.indexOf(32) > -1 && (this.keywords[this.tokenIndex] + ' ' + this.keywords[this.tokenIndex + 1]).equalsIgnoreCase(keyword)) {
                  this.queryStringPos = this.queryString.indexOf(this.keywords[this.tokenIndex], this.queryStringPos) + this.keywords[this.tokenIndex].length() + 1;
                  this.queryStringPos = this.queryString.indexOf(this.keywords[this.tokenIndex + 1], this.queryStringPos) + this.keywords[this.tokenIndex + 1].length() + 1;
                  ++this.tokenIndex;
                  return true;
               }
            }

            --this.tokenIndex;
         }

         return false;
      }

      public String parseKeyword() {
         if (this.tokenIndex < this.tokens.length - 1) {
            ++this.tokenIndex;
            if (this.keywords[this.tokenIndex] != null) {
               return this.keywords[this.tokenIndex];
            }

            --this.tokenIndex;
         }

         return null;
      }
   }
}
