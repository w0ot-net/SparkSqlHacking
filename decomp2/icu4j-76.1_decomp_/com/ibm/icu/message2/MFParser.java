package com.ibm.icu.message2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @deprecated */
@Deprecated
public class MFParser {
   private static final int EOF = -1;
   private final InputSource input;
   private static final Pattern RE_NUMBER_LITERAL = Pattern.compile("^-?(0|[1-9][0-9]*)(\\.[0-9]+)?([eE][+\\-]?[0-9]+)?");

   MFParser(String text) {
      this.input = new InputSource(text);
   }

   /** @deprecated */
   @Deprecated
   public static MFDataModel.Message parse(String input) throws MFParseException {
      return (new MFParser(input)).parseImpl();
   }

   private MFDataModel.Message parseImpl() throws MFParseException {
      int savedPosition = this.input.getPosition();
      this.skipOptionalWhitespaces();
      int cp = this.input.peekChar();
      MFDataModel.Message result;
      if (cp == 46) {
         result = this.getComplexMessage();
      } else if (cp == 123) {
         cp = this.input.readCodePoint();
         cp = this.input.peekChar();
         if (cp == 123) {
            this.input.backup(1);
            MFDataModel.Pattern pattern = this.getQuotedPattern();
            this.skipOptionalWhitespaces();
            result = new MFDataModel.PatternMessage(new ArrayList(), pattern);
         } else {
            this.input.gotoPosition(savedPosition);
            MFDataModel.Pattern pattern = this.getPattern();
            result = new MFDataModel.PatternMessage(new ArrayList(), pattern);
         }
      } else {
         this.input.gotoPosition(savedPosition);
         MFDataModel.Pattern pattern = this.getPattern();
         result = new MFDataModel.PatternMessage(new ArrayList(), pattern);
      }

      this.checkCondition(this.input.atEnd(), "Content detected after the end of the message.");
      (new MFDataModelValidator(result)).validate();
      return result;
   }

   private MFDataModel.Pattern getPattern() throws MFParseException {
      MFDataModel.Pattern pattern = new MFDataModel.Pattern();

      while(true) {
         MFDataModel.PatternPart part = this.getPatternPart();
         if (part == null) {
            return pattern;
         }

         pattern.parts.add(part);
      }
   }

   private MFDataModel.PatternPart getPatternPart() throws MFParseException {
      int cp = this.input.peekChar();
      switch (cp) {
         case -1:
            return null;
         case 123:
            MFDataModel.Expression ph = this.getPlaceholder();
            return ph;
         case 125:
            return null;
         default:
            String plainText = this.getText();
            MFDataModel.StringPart sp = new MFDataModel.StringPart(plainText);
            return sp;
      }
   }

   private String getText() {
      StringBuilder result = new StringBuilder();

      while(true) {
         int cp = this.input.readCodePoint();
         switch (cp) {
            case -1:
               return result.toString();
            case 46:
            case 64:
            case 124:
               result.appendCodePoint(cp);
               break;
            case 92:
               cp = this.input.readCodePoint();
               if (cp != 92 && cp != 123 && !(cp == 124 | cp == 125)) {
                  result.appendCodePoint(92);
                  result.appendCodePoint(cp);
                  break;
               }

               result.appendCodePoint(cp);
               break;
            default:
               if (!StringUtils.isContentChar(cp) && !StringUtils.isWhitespace(cp)) {
                  this.input.backup(1);
                  return result.toString();
               }

               result.appendCodePoint(cp);
         }
      }
   }

   private MFDataModel.Expression getPlaceholder() throws MFParseException {
      int cp = this.input.peekChar();
      if (cp != 123) {
         return null;
      } else {
         this.input.readCodePoint();
         this.skipOptionalWhitespaces();
         cp = this.input.peekChar();
         MFDataModel.Expression result;
         if (cp != 35 && cp != 47) {
            if (cp == 36) {
               result = this.getVariableExpression();
            } else if (StringUtils.isFunctionSigil(cp)) {
               result = this.getAnnotationExpression();
            } else {
               result = this.getLiteralExpression();
            }
         } else {
            result = this.getMarkup();
         }

         this.skipOptionalWhitespaces();
         cp = this.input.readCodePoint();
         this.checkCondition(cp == 125, "Unclosed placeholder");
         return result;
      }
   }

   private MFDataModel.Annotation getAnnotation(boolean whitespaceRequired) throws MFParseException {
      int position = this.input.getPosition();
      int cp = this.input.peekChar();
      if (cp == 125) {
         return null;
      } else {
         int whitespaceCount = 0;
         if (whitespaceRequired) {
            whitespaceCount = this.skipMandatoryWhitespaces();
         } else {
            whitespaceCount = this.skipOptionalWhitespaces();
         }

         cp = this.input.peekChar();
         switch (cp) {
            case 58:
               this.input.readCodePoint();
               String identifier = this.getIdentifier();
               this.checkCondition(identifier != null, "Annotation / function name missing");
               Map<String, MFDataModel.Option> options = this.getOptions();
               return new MFDataModel.FunctionAnnotation(identifier, options);
            case 125:
               this.input.backup(whitespaceCount);
               return null;
            default:
               this.input.gotoPosition(position);
               return null;
         }
      }
   }

   private MFDataModel.Annotation getMarkupAnnotation() throws MFParseException {
      this.skipOptionalWhitespaces();
      int cp = this.input.peekChar();
      switch (cp) {
         case 35:
         case 47:
            this.input.readCodePoint();
            String identifier = this.getIdentifier();
            this.checkCondition(identifier != null, "Annotation / function name missing");
            Map<String, MFDataModel.Option> options = this.getOptions();
            return new MFDataModel.FunctionAnnotation(identifier, options);
         case 125:
            return null;
         default:
            return null;
      }
   }

   private MFDataModel.Expression getLiteralExpression() throws MFParseException {
      MFDataModel.Literal literal = this.getLiteral();
      this.checkCondition(literal != null, "Literal expression expected.");
      MFDataModel.Annotation annotation = null;
      boolean hasWhitespace = StringUtils.isWhitespace(this.input.peekChar());
      if (hasWhitespace) {
         annotation = this.getAnnotation(true);
         if (annotation == null) {
         }
      }

      List<MFDataModel.Attribute> attributes = this.getAttributes();
      return new MFDataModel.LiteralExpression(literal, annotation, attributes);
   }

   private MFDataModel.VariableExpression getVariableExpression() throws MFParseException {
      MFDataModel.VariableRef variableRef = this.getVariableRef();
      MFDataModel.Annotation annotation = this.getAnnotation(true);
      List<MFDataModel.Attribute> attributes = this.getAttributes();
      return new MFDataModel.VariableExpression(variableRef, annotation, attributes);
   }

   private MFDataModel.Expression getAnnotationExpression() throws MFParseException {
      MFDataModel.Annotation annotation = this.getAnnotation(false);
      List<MFDataModel.Attribute> attributes = this.getAttributes();
      if (annotation instanceof MFDataModel.FunctionAnnotation) {
         return new MFDataModel.FunctionExpression((MFDataModel.FunctionAnnotation)annotation, attributes);
      } else {
         this.error("Unexpected annotation : " + annotation);
         return null;
      }
   }

   private MFDataModel.Markup getMarkup() throws MFParseException {
      int cp = this.input.peekChar();
      this.checkCondition(cp == 35 || cp == 47, "Should not happen. Expecting a markup.");
      MFDataModel.Markup.Kind kind = cp == 47 ? MFDataModel.Markup.Kind.CLOSE : MFDataModel.Markup.Kind.OPEN;
      MFDataModel.Annotation annotation = this.getMarkupAnnotation();
      List<MFDataModel.Attribute> attributes = this.getAttributes();
      this.skipOptionalWhitespaces();
      cp = this.input.peekChar();
      if (cp == 47) {
         kind = MFDataModel.Markup.Kind.STANDALONE;
         this.input.readCodePoint();
      }

      if (annotation instanceof MFDataModel.FunctionAnnotation) {
         MFDataModel.FunctionAnnotation fa = (MFDataModel.FunctionAnnotation)annotation;
         return new MFDataModel.Markup(kind, fa.name, fa.options, attributes);
      } else {
         return null;
      }
   }

   private List getAttributes() throws MFParseException {
      List<MFDataModel.Attribute> result = new ArrayList();

      while(true) {
         MFDataModel.Attribute attribute = this.getAttribute();
         if (attribute == null) {
            return result;
         }

         result.add(attribute);
      }
   }

   private MFDataModel.Attribute getAttribute() throws MFParseException {
      int position = this.input.getPosition();
      if (this.skipWhitespaces() == 0) {
         this.input.gotoPosition(position);
         return null;
      } else {
         int cp = this.input.peekChar();
         if (cp == 64) {
            this.input.readCodePoint();
            String id = this.getIdentifier();
            int wsCount = this.skipWhitespaces();
            cp = this.input.peekChar();
            MFDataModel.LiteralOrVariableRef literalOrVariable = null;
            if (cp == 61) {
               this.input.readCodePoint();
               this.skipOptionalWhitespaces();
               literalOrVariable = this.getLiteralOrVariableRef();
               this.checkCondition(literalOrVariable != null, "Attributes must have a value after `=`");
            } else {
               this.input.backup(wsCount);
            }

            return new MFDataModel.Attribute(id, literalOrVariable);
         } else {
            this.input.gotoPosition(position);
            return null;
         }
      }
   }

   private String getIdentifier() throws MFParseException {
      String namespace = this.getName();
      if (namespace == null) {
         return null;
      } else {
         int cp = this.input.readCodePoint();
         if (cp == 58) {
            String name = this.getName();
            this.checkCondition(name != null, "Expected name after namespace '" + namespace + "'");
            return namespace + ":" + name;
         } else {
            this.input.backup(1);
            return namespace;
         }
      }
   }

   private Map getOptions() throws MFParseException {
      Map<String, MFDataModel.Option> options = new LinkedHashMap();
      boolean first = true;
      int skipCount = 0;

      while(true) {
         MFDataModel.Option option = this.getOption();
         if (option == null) {
            this.input.backup(skipCount);
            return options;
         }

         this.checkCondition(first || skipCount != 0, "Expected whitespace before option " + option.name);
         first = false;
         if (options.containsKey(option.name)) {
            this.error("Duplicated option '" + option.name + "'");
         }

         options.put(option.name, option);
         skipCount = this.skipOptionalWhitespaces();
      }
   }

   private MFDataModel.Option getOption() throws MFParseException {
      int position = this.input.getPosition();
      this.skipOptionalWhitespaces();
      String identifier = this.getIdentifier();
      if (identifier == null) {
         this.input.gotoPosition(position);
         return null;
      } else {
         this.skipOptionalWhitespaces();
         int cp = this.input.readCodePoint();
         this.checkCondition(cp == 61, "Expected '='");
         this.skipOptionalWhitespaces();
         MFDataModel.LiteralOrVariableRef litOrVar = this.getLiteralOrVariableRef();
         if (litOrVar == null) {
            this.error("Options must have a value. An empty string should be quoted.");
         }

         return new MFDataModel.Option(identifier, litOrVar);
      }
   }

   private MFDataModel.LiteralOrVariableRef getLiteralOrVariableRef() throws MFParseException {
      int cp = this.input.peekChar();
      return (MFDataModel.LiteralOrVariableRef)(cp == 36 ? this.getVariableRef() : this.getLiteral());
   }

   private MFDataModel.Literal getLiteral() throws MFParseException {
      int cp = this.input.readCodePoint();
      switch (cp) {
         case 124:
            this.input.backup(1);
            MFDataModel.Literal ql = this.getQuotedLiteral();
            return ql;
         default:
            this.input.backup(1);
            MFDataModel.Literal unql = this.getUnQuotedLiteral();
            return unql;
      }
   }

   private MFDataModel.VariableRef getVariableRef() throws MFParseException {
      int cp = this.input.readCodePoint();
      if (cp != 36) {
         this.checkCondition(cp == 36, "We can't get here");
      }

      String name = this.getName();
      this.checkCondition(name != null, "Invalid variable reference following $");
      return new MFDataModel.VariableRef(name);
   }

   private MFDataModel.Literal getQuotedLiteral() throws MFParseException {
      StringBuilder result = new StringBuilder();
      int cp = this.input.readCodePoint();
      this.checkCondition(cp == 124, "expected starting '|'");

      while(true) {
         cp = this.input.readCodePoint();
         if (cp == -1) {
            break;
         }

         if (StringUtils.isQuotedChar(cp)) {
            result.appendCodePoint(cp);
         } else {
            if (cp != 92) {
               break;
            }

            cp = this.input.readCodePoint();
            boolean isValidEscape = cp == 124 || cp == 92 || cp == 123 || cp == 125;
            this.checkCondition(isValidEscape, "Invalid escape sequence inside quoted literal");
            result.appendCodePoint(cp);
         }
      }

      this.checkCondition(cp == 124, "expected ending '|'");
      return new MFDataModel.Literal(result.toString());
   }

   private MFDataModel.Literal getUnQuotedLiteral() throws MFParseException {
      String name = this.getName();
      return name != null ? new MFDataModel.Literal(name) : this.getNumberLiteral();
   }

   private MFDataModel.Literal getNumberLiteral() {
      String numberString = this.peekWithRegExp(RE_NUMBER_LITERAL);
      return numberString != null ? new MFDataModel.Literal(numberString) : null;
   }

   private int skipMandatoryWhitespaces() throws MFParseException {
      int count = this.skipWhitespaces();
      this.checkCondition(count > 0, "Space expected");
      return count;
   }

   private int skipOptionalWhitespaces() {
      return this.skipWhitespaces();
   }

   private int skipWhitespaces() {
      int skipCount = 0;

      while(true) {
         int cp = this.input.readCodePoint();
         if (cp == -1) {
            return skipCount;
         }

         if (!StringUtils.isWhitespace(cp)) {
            this.input.backup(1);
            return skipCount;
         }

         ++skipCount;
      }
   }

   private MFDataModel.Message getComplexMessage() throws MFParseException {
      List<MFDataModel.Declaration> declarations = new ArrayList();
      boolean foundMatch = false;

      while(true) {
         MFDataModel.Declaration declaration = this.getDeclaration();
         if (declaration == null) {
            break;
         }

         if (declaration instanceof MatchDeclaration) {
            foundMatch = true;
            break;
         }

         declarations.add(declaration);
      }

      if (foundMatch) {
         return this.getMatch(declarations);
      } else {
         this.skipOptionalWhitespaces();
         int cp = this.input.peekChar();
         this.checkCondition(cp != -1, "Expected a quoted pattern or .match; got end-of-input");
         MFDataModel.Pattern pattern = this.getQuotedPattern();
         this.skipOptionalWhitespaces();
         this.checkCondition(this.input.atEnd(), "Content detected after the end of the message.");
         return new MFDataModel.PatternMessage(declarations, pattern);
      }
   }

   private MFDataModel.SelectMessage getMatch(List declarations) throws MFParseException {
      List<MFDataModel.Expression> expressions = new ArrayList();

      while(true) {
         this.skipOptionalWhitespaces();
         MFDataModel.Expression expression = this.getPlaceholder();
         if (expression == null) {
            this.checkCondition(!expressions.isEmpty(), "There should be at least one selector expression.");
            List<MFDataModel.Variant> variants = new ArrayList();

            while(true) {
               MFDataModel.Variant variant = this.getVariant();
               if (variant == null) {
                  this.checkCondition(this.input.atEnd(), "Content detected after the end of the message.");
                  return new MFDataModel.SelectMessage(declarations, expressions, variants);
               }

               variants.add(variant);
            }
         }

         this.checkCondition(!(expression instanceof MFDataModel.Markup), "Cannot do selection on markup");
         expressions.add(expression);
      }
   }

   private MFDataModel.Variant getVariant() throws MFParseException {
      List<MFDataModel.LiteralOrCatchallKey> keys = new ArrayList();

      while(true) {
         MFDataModel.LiteralOrCatchallKey key = this.getKey(!keys.isEmpty());
         if (key == null) {
            this.skipOptionalWhitespaces();
            if (this.input.atEnd()) {
               this.checkCondition(keys.isEmpty(), "After selector keys it is mandatory to have a pattern.");
               return null;
            }

            MFDataModel.Pattern pattern = this.getQuotedPattern();
            return new MFDataModel.Variant(keys, pattern);
         }

         keys.add(key);
      }
   }

   private MFDataModel.LiteralOrCatchallKey getKey(boolean requireSpaces) throws MFParseException {
      int cp = this.input.peekChar();
      if (cp == 123) {
         return null;
      } else {
         int skipCount = 0;
         if (requireSpaces) {
            skipCount = this.skipMandatoryWhitespaces();
         } else {
            skipCount = this.skipOptionalWhitespaces();
         }

         cp = this.input.peekChar();
         if (cp == 42) {
            this.input.readCodePoint();
            return new MFDataModel.CatchallKey();
         } else if (cp == -1) {
            this.input.backup(skipCount);
            return null;
         } else {
            return this.getLiteral();
         }
      }
   }

   private MFDataModel.Declaration getDeclaration() throws MFParseException {
      int position = this.input.getPosition();
      this.skipOptionalWhitespaces();
      int cp = this.input.readCodePoint();
      if (cp != 46) {
         this.input.gotoPosition(position);
         return null;
      } else {
         String declName = this.getName();
         this.checkCondition(declName != null, "Expected a declaration after the '.'");
         switch (declName) {
            case "input":
               this.skipOptionalWhitespaces();
               MFDataModel.Expression expression = this.getPlaceholder();
               String inputVarName = null;
               this.checkCondition(expression instanceof MFDataModel.VariableExpression, "Variable expression required in .input declaration");
               inputVarName = ((MFDataModel.VariableExpression)expression).arg.name;
               return new MFDataModel.InputDeclaration(inputVarName, (MFDataModel.VariableExpression)expression);
            case "local":
               this.skipMandatoryWhitespaces();
               MFDataModel.LiteralOrVariableRef varName = this.getVariableRef();
               this.skipOptionalWhitespaces();
               cp = this.input.readCodePoint();
               this.checkCondition(cp == 61, declName);
               this.skipOptionalWhitespaces();
               MFDataModel.Expression expression = this.getPlaceholder();
               if (varName instanceof MFDataModel.VariableRef) {
                  return new MFDataModel.LocalDeclaration(((MFDataModel.VariableRef)varName).name, expression);
               }
            default:
               return null;
            case "match":
               return new MatchDeclaration();
         }
      }
   }

   private MFDataModel.Pattern getQuotedPattern() throws MFParseException {
      int cp = this.input.readCodePoint();
      this.checkCondition(cp == 123, "Expected { for a complex body");
      cp = this.input.readCodePoint();
      this.checkCondition(cp == 123, "Expected second { for a complex body");
      MFDataModel.Pattern pattern = this.getPattern();
      cp = this.input.readCodePoint();
      this.checkCondition(cp == 125, "Expected } to end a complex body");
      cp = this.input.readCodePoint();
      this.checkCondition(cp == 125, "Expected second } to end a complex body");
      return pattern;
   }

   private String getName() throws MFParseException {
      StringBuilder result = new StringBuilder();
      int cp = this.input.readCodePoint();
      this.checkCondition(cp != -1, "Expected name or namespace.");
      if (!StringUtils.isNameStart(cp)) {
         this.input.backup(1);
         return null;
      } else {
         result.appendCodePoint(cp);

         while(true) {
            cp = this.input.readCodePoint();
            if (!StringUtils.isNameChar(cp)) {
               if (cp != -1) {
                  this.input.backup(1);
               }

               return result.toString();
            }

            result.appendCodePoint(cp);
         }
      }
   }

   private void checkCondition(boolean condition, String message) throws MFParseException {
      if (!condition) {
         this.error(message);
      }

   }

   private void error(String message) throws MFParseException {
      StringBuilder finalMsg = new StringBuilder();
      if (this.input == null) {
         finalMsg.append("Parse error: ");
         finalMsg.append(message);
      } else {
         int position = this.input.getPosition();
         finalMsg.append("Parse error [" + this.input.getPosition() + "]: ");
         finalMsg.append(message);
         finalMsg.append("\n");
         if (position != -1) {
            finalMsg.append(this.input.buffer.substring(0, position));
            finalMsg.append("^^^");
            finalMsg.append(this.input.buffer.substring(position));
         } else {
            finalMsg.append(this.input.buffer);
            finalMsg.append("^^^");
         }
      }

      throw new MFParseException(finalMsg.toString(), this.input.getPosition());
   }

   private String peekWithRegExp(Pattern pattern) {
      StringView sv = new StringView(this.input.buffer, this.input.getPosition());
      Matcher m = pattern.matcher(sv);
      boolean found = m.find();
      if (found) {
         this.input.skip(m.group().length());
         return m.group();
      } else {
         return null;
      }
   }

   private static class MatchDeclaration implements MFDataModel.Declaration {
      private MatchDeclaration() {
      }
   }
}
