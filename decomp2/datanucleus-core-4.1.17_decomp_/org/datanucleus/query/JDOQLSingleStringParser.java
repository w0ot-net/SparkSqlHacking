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

public class JDOQLSingleStringParser {
   private Query query;
   private String queryString;
   boolean allowDelete = false;
   boolean allowUpdate = false;

   public JDOQLSingleStringParser(Query query, String queryString) {
      NucleusLogger.QUERY.debug(Localiser.msg("042010", queryString));
      this.query = query;
      this.queryString = queryString;
   }

   public void setAllowDelete(boolean allow) {
      this.allowDelete = allow;
   }

   public void setAllowUpdate(boolean allow) {
      this.allowUpdate = allow;
   }

   public void parse() {
      (new Compiler(new Parser(this.queryString, this.allowDelete || this.allowUpdate))).compile();
   }

   private class Compiler {
      Parser parser;

      Compiler(Parser tokenizer) {
         this.parser = tokenizer;
      }

      private void compile() {
         this.compileSelect();
         String keyword = this.parser.parseKeyword();
         if (keyword != null && JDOQLQueryHelper.isKeyword(keyword)) {
            throw new NucleusUserException(Localiser.msg("042011", keyword));
         }
      }

      private void compileSelect() {
         boolean update = false;
         boolean delete = false;
         if (!JDOQLSingleStringParser.this.allowUpdate || !this.parser.parseKeyword("UPDATE") && !this.parser.parseKeyword("update")) {
            if (!JDOQLSingleStringParser.this.allowDelete || !this.parser.parseKeyword("DELETE") && !this.parser.parseKeyword("delete")) {
               if (!this.parser.parseKeyword("SELECT") && !this.parser.parseKeyword("select")) {
                  throw new NucleusUserException(Localiser.msg("042012"));
               }
            } else {
               delete = true;
               JDOQLSingleStringParser.this.query.setType((short)2);
            }
         } else {
            update = true;
            JDOQLSingleStringParser.this.query.setType((short)1);
         }

         if (update) {
            this.compileUpdate();
         } else if (delete) {
            if (this.parser.parseKeyword("FROM") || this.parser.parseKeyword("from")) {
               this.compileFrom();
            }
         } else {
            if (this.parser.parseKeyword("UNIQUE") || this.parser.parseKeyword("unique")) {
               this.compileUnique();
            }

            this.compileResult();
            if (this.parser.parseKeyword("INTO") || this.parser.parseKeyword("into")) {
               this.compileInto();
            }

            if (this.parser.parseKeyword("FROM") || this.parser.parseKeyword("from")) {
               this.compileFrom();
            }
         }

         if (this.parser.parseKeyword("WHERE") || this.parser.parseKeyword("where")) {
            this.compileWhere();
         }

         if (this.parser.parseKeyword("VARIABLES") || this.parser.parseKeyword("variables")) {
            this.compileVariables();
         }

         if (this.parser.parseKeyword("PARAMETERS") || this.parser.parseKeyword("parameters")) {
            this.compileParameters();
         }

         if (this.parser.parseKeyword("IMPORT") || this.parser.parseKeyword("import")) {
            this.compileImport();
         }

         if (this.parser.parseKeyword("GROUP") || this.parser.parseKeyword("group")) {
            this.compileGroup();
         }

         if (this.parser.parseKeyword("ORDER") || this.parser.parseKeyword("order")) {
            this.compileOrder();
         }

         if (this.parser.parseKeyword("RANGE") || this.parser.parseKeyword("range")) {
            this.compileRange();
         }

      }

      private void compileUnique() {
         JDOQLSingleStringParser.this.query.setUnique(true);
      }

      private void compileResult() {
         String content = this.parser.parseContent(true);
         if (content.length() > 0) {
            if (content.indexOf("SELECT ") <= 0 && content.indexOf("select ") <= 0) {
               JDOQLSingleStringParser.this.query.setResult(content);
            } else {
               String substitutedContent = this.processContentWithSubqueries(content);
               JDOQLSingleStringParser.this.query.setResult(substitutedContent);
            }
         }

      }

      private void compileUpdate() {
         String content = this.parser.parseContent(false);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("043010"));
         } else {
            JDOQLSingleStringParser.this.query.setFrom(content);
            JDOQLSingleStringParser.this.query.setCandidateClassName(content);
            if (!this.parser.parseKeyword("set") && !this.parser.parseKeyword("SET")) {
               throw new NucleusUserException(Localiser.msg("043011"));
            } else {
               content = this.parser.parseContent(false);
               JDOQLSingleStringParser.this.query.setUpdate(content.trim());
            }
         }
      }

      private void compileInto() {
         String content = this.parser.parseContent(false);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("042014", "INTO", "<result class>"));
         } else {
            String resultClassName = content.trim();
            JDOQLSingleStringParser.this.query.setResultClassName(resultClassName);
         }
      }

      private void compileFrom() {
         String content = this.parser.parseContent(false);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("042014", "FROM", "<candidate class>"));
         } else {
            if (content.indexOf(32) > 0) {
               JDOQLSingleStringParser.this.query.setFrom(content.trim());
            } else {
               JDOQLSingleStringParser.this.query.setCandidateClassName(content);
            }

            if (this.parser.parseKeyword("EXCLUDE") || this.parser.parseKeyword("exclude")) {
               if (!this.parser.parseKeyword("SUBCLASSES") && !this.parser.parseKeyword("subclasses")) {
                  throw new NucleusUserException(Localiser.msg("042015", "SUBCLASSES", "EXCLUDE"));
               }

               content = this.parser.parseContent(false);
               if (content.length() > 0) {
                  throw new NucleusUserException(Localiser.msg("042013", "EXCLUDE SUBCLASSES", content));
               }

               JDOQLSingleStringParser.this.query.setSubclasses(false);
            }

         }
      }

      private void compileWhere() {
         String content = this.parser.parseContent(true);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("042014", "WHERE", "<filter>"));
         } else {
            if (content.indexOf("SELECT ") <= 0 && content.indexOf("select ") <= 0) {
               JDOQLSingleStringParser.this.query.setFilter(content);
            } else {
               String substitutedContent = this.processContentWithSubqueries(content);
               JDOQLSingleStringParser.this.query.setFilter(substitutedContent);
            }

         }
      }

      private String processContentWithSubqueries(String content) {
         StringBuilder stringContent = new StringBuilder();
         boolean withinLiteralDouble = false;
         boolean withinLiteralSingle = false;
         int subqueryNum = 1;

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
               if (remains.startsWith("select") || remains.startsWith("SELECT")) {
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
                  String subqueryVarName = "DN_SUBQUERY_" + subqueryNum;
                  Query subquery = (Query)ClassUtils.newInstance(JDOQLSingleStringParser.this.query.getClass(), new Class[]{ClassConstants.STORE_MANAGER, ClassConstants.EXECUTION_CONTEXT, String.class}, new Object[]{JDOQLSingleStringParser.this.query.getStoreManager(), JDOQLSingleStringParser.this.query.getExecutionContext(), subqueryStr});
                  JDOQLSingleStringParser.this.query.addSubquery(subquery, "double " + subqueryVarName, (String)null, (Map)null);
                  if (stringContent.length() > 0 && stringContent.charAt(stringContent.length() - 1) != ' ') {
                     stringContent.append(' ');
                  }

                  stringContent.append(subqueryVarName);
                  i = endPosition;
                  subqueryProcessed = true;
                  ++subqueryNum;
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

      private void compileVariables() {
         String content = this.parser.parseContent(false);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("042014", "VARIABLES", "<variable declarations>"));
         } else {
            JDOQLSingleStringParser.this.query.declareExplicitVariables(content);
         }
      }

      private void compileParameters() {
         String content = this.parser.parseContent(false);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("042014", "PARAMETERS", "<parameter declarations>"));
         } else {
            JDOQLSingleStringParser.this.query.declareExplicitParameters(content);
         }
      }

      private void compileImport() {
         StringBuilder content = new StringBuilder("import " + this.parser.parseContent(false));

         while(this.parser.parseKeyword("import")) {
            content.append("import ").append(this.parser.parseContent(false));
         }

         JDOQLSingleStringParser.this.query.declareImports(content.toString());
      }

      private void compileGroup() {
         String content = this.parser.parseContent(false);
         if (!this.parser.parseKeyword("BY") && !this.parser.parseKeyword("by")) {
            throw new NucleusUserException(Localiser.msg("042015", "BY", "GROUP"));
         } else {
            content = this.parser.parseContent(false);
            if (content.length() == 0) {
               throw new NucleusUserException(Localiser.msg("042014", "GROUP BY", "<grouping>"));
            } else {
               JDOQLSingleStringParser.this.query.setGrouping(content);
            }
         }
      }

      private void compileOrder() {
         String content = this.parser.parseContent(false);
         if (!this.parser.parseKeyword("BY") && !this.parser.parseKeyword("by")) {
            throw new NucleusUserException(Localiser.msg("042015", "BY", "ORDER"));
         } else {
            content = this.parser.parseContent(false);
            if (content.length() == 0) {
               throw new NucleusUserException(Localiser.msg("042014", "ORDER BY", "<ordering>"));
            } else {
               JDOQLSingleStringParser.this.query.setOrdering(content);
            }
         }
      }

      private void compileRange() {
         String content = this.parser.parseContent(false);
         if (content.length() == 0) {
            throw new NucleusUserException(Localiser.msg("042014", "RANGE", "<range>"));
         } else {
            JDOQLSingleStringParser.this.query.setRange(content);
         }
      }
   }

   private static class Parser {
      final boolean extended;
      final String queryString;
      int queryStringPos = 0;
      final String[] tokens;
      final String[] keywords;
      int tokenIndex = -1;

      public Parser(String str, boolean extended) {
         this.queryString = str.replace('\n', ' ');
         this.extended = extended;
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
         this.keywords = new String[tokenList.size()];
         int i = 0;

         for(String token : tokenList) {
            this.tokens[i] = token;
            if (extended && JDOQLQueryHelper.isKeywordExtended(token) || !extended && JDOQLQueryHelper.isKeyword(token)) {
               this.keywords[i] = token;
            }

            ++i;
         }

      }

      public String parseContent(boolean allowSubentries) {
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

            if (level == 0 && (this.extended && JDOQLQueryHelper.isKeywordExtended(this.tokens[this.tokenIndex]) || !this.extended && JDOQLQueryHelper.isKeyword(this.tokens[this.tokenIndex]))) {
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

      public boolean parseKeyword(String keyword) {
         if (this.tokenIndex < this.tokens.length - 1) {
            ++this.tokenIndex;
            if (this.keywords[this.tokenIndex] != null && this.keywords[this.tokenIndex].equals(keyword)) {
               this.queryStringPos = this.queryString.indexOf(this.keywords[this.tokenIndex], this.queryStringPos) + this.keywords[this.tokenIndex].length() + 1;
               return true;
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
