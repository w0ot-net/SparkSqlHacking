package org.datanucleus.query.compiler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.query.JDOQLQueryHelper;
import org.datanucleus.store.query.QueryCompilerSyntaxException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.StringUtils;

public class JDOQLParser implements Parser {
   private static String[] jdoqlMethodNames = new String[]{"contains", "get", "containsKey", "containsValue", "isEmpty", "size", "toLowerCase", "toUpperCase", "indexOf", "matches", "substring", "startsWith", "endsWith", "getObjectId", "abs", "sqrt"};
   private ParameterType paramType;
   private boolean strictJDOQL;
   private Lexer p;
   private Deque stack;
   private static String paramPrefixes = ":";
   private boolean allowSingleEquals;
   private List parameterNameList;

   public JDOQLParser(Map options) {
      this.paramType = JDOQLParser.ParameterType.IMPLICIT;
      this.strictJDOQL = false;
      this.stack = new ArrayDeque();
      this.allowSingleEquals = false;
      this.parameterNameList = null;
      if (options != null && options.containsKey("jdoql.strict")) {
         this.strictJDOQL = Boolean.valueOf((String)options.get("jdoql.strict"));
      }

      if (options != null && options.containsKey("explicitParameters")) {
         this.paramType = JDOQLParser.ParameterType.EXPLICIT;
      }

   }

   public void allowSingleEquals(boolean flag) {
      this.allowSingleEquals = flag;
   }

   public Node parse(String expression) {
      this.p = new Lexer(expression, paramPrefixes, true);
      this.stack = new ArrayDeque();
      Node result = this.processExpression();
      if (this.p.ci.getIndex() != this.p.ci.getEndIndex()) {
         String unparsed = this.p.getInput().substring(this.p.ci.getIndex());
         throw new QueryCompilerSyntaxException("Portion of expression could not be parsed: " + unparsed);
      } else {
         return result;
      }
   }

   public Node parseVariable(String expression) {
      this.p = new Lexer(expression, paramPrefixes, true);
      this.stack = new ArrayDeque();
      if (!this.processIdentifier()) {
         throw new QueryCompilerSyntaxException("expected identifier", this.p.getIndex(), this.p.getInput());
      } else if (!this.processIdentifier()) {
         throw new QueryCompilerSyntaxException("expected identifier", this.p.getIndex(), this.p.getInput());
      } else {
         Node nodeVariable = (Node)this.stack.pop();
         Node nodeType = (Node)this.stack.pop();
         nodeType.appendChildNode(nodeVariable);
         return nodeType;
      }
   }

   public Node[] parseFrom(String expression) {
      this.p = new Lexer(expression, paramPrefixes, true);
      this.stack = new ArrayDeque();
      return this.processFromExpression();
   }

   private Node[] processFromExpression() {
      this.processExpression();
      Node id = (Node)this.stack.pop();
      StringBuilder className = new StringBuilder(id.getNodeValue().toString());

      while(id.getChildNodes().size() > 0) {
         id = id.getFirstChild();
         className.append(".").append(id.getNodeValue().toString());
      }

      String alias = this.p.parseIdentifier();
      if (alias != null && alias.equalsIgnoreCase("AS")) {
         alias = this.p.parseIdentifier();
      }

      if (alias == null) {
         alias = "this";
      }

      Node classNode = new Node(NodeType.CLASS, className.toString());
      Node aliasNode = new Node(NodeType.NAME, alias);
      classNode.insertChildNode(aliasNode);
      this.stack.push(classNode);
      return new Node[]{classNode};
   }

   public Node[] parseUpdate(String expression) {
      return null;
   }

   public Node[] parseOrder(String expression) {
      this.p = new Lexer(expression, paramPrefixes, true);
      this.stack = new ArrayDeque();
      return this.processOrderExpression();
   }

   public Node[] parseResult(String expression) {
      this.p = new Lexer(expression, paramPrefixes, true);
      this.stack = new ArrayDeque();
      List nodes = new ArrayList();

      do {
         this.processExpression();
         Node expr = (Node)this.stack.pop();
         String alias = this.p.parseIdentifier();
         if (alias != null && alias.equalsIgnoreCase("AS")) {
            alias = this.p.parseIdentifier();
         }

         if (alias != null) {
            Node aliasNode = new Node(NodeType.NAME, alias);
            expr.appendChildNode(aliasNode);
         }

         nodes.add(expr);
      } while(this.p.parseString(","));

      return (Node[])nodes.toArray(new Node[nodes.size()]);
   }

   public Node[] parseTuple(String expression) {
      this.p = new Lexer(expression, paramPrefixes, true);
      this.stack = new ArrayDeque();
      List nodes = new ArrayList();

      do {
         this.processExpression();
         Node expr = (Node)this.stack.pop();
         nodes.add(expr);
      } while(this.p.parseString(","));

      return (Node[])nodes.toArray(new Node[nodes.size()]);
   }

   public Node[][] parseVariables(String expression) {
      this.p = new Lexer(expression, paramPrefixes, true);
      List nodes = new ArrayList();

      while(true) {
         if (!StringUtils.isWhitespace(this.p.remaining())) {
            this.processPrimary();
            if (this.stack.isEmpty()) {
               throw new QueryCompilerSyntaxException("Parsing variable list and expected variable type", this.p.getIndex(), this.p.getInput());
            }

            if (!this.processIdentifier()) {
               throw new QueryCompilerSyntaxException("Parsing variable list and expected variable name", this.p.getIndex(), this.p.getInput());
            }

            Node nodeVariable = (Node)this.stack.pop();
            String varName = (String)nodeVariable.getNodeValue();
            if (!JDOQLQueryHelper.isValidJavaIdentifierForJDOQL(varName)) {
               throw new NucleusUserException(Localiser.msg("021105", varName));
            }

            Node nodeType = (Node)this.stack.pop();
            nodes.add(new Node[]{nodeType, nodeVariable});
            if (this.p.parseString(";")) {
               continue;
            }
         }

         return (Node[][])nodes.toArray(new Node[nodes.size()][2]);
      }
   }

   public Node[][] parseParameters(String expression) {
      List nodes = new ArrayList();
      StringTokenizer tokeniser = new StringTokenizer(expression, ",");

      while(tokeniser.hasMoreTokens()) {
         String token = tokeniser.nextToken();
         StringTokenizer subTokeniser = new StringTokenizer(token, " ");
         if (subTokeniser.countTokens() != 2) {
            throw new QueryCompilerSyntaxException(Localiser.msg("021101", expression));
         }

         String classDecl = subTokeniser.nextToken();
         String parameterName = subTokeniser.nextToken();
         Node declNode = new Node(NodeType.IDENTIFIER, classDecl);
         Node nameNode = new Node(NodeType.IDENTIFIER, parameterName);
         nodes.add(new Node[]{declNode, nameNode});
      }

      return (Node[][])nodes.toArray(new Node[nodes.size()][2]);
   }

   private Node[] processOrderExpression() {
      List nodes = new ArrayList();

      do {
         this.processExpression();
         Node directionNode = null;
         if (!this.p.parseString("ascending") && !this.p.parseString("asc") && !this.p.parseString("ASCENDING") && !this.p.parseString("ASC")) {
            if (!this.p.parseString("descending") && !this.p.parseString("desc") && !this.p.parseString("DESCENDING") && !this.p.parseString("DESC")) {
               directionNode = new Node(NodeType.OPERATOR, "ascending");
            } else {
               directionNode = new Node(NodeType.OPERATOR, "descending");
            }
         } else {
            directionNode = new Node(NodeType.OPERATOR, "ascending");
         }

         Node nullsNode = null;
         if (!this.p.parseString("NULLS FIRST") && !this.p.parseString("nulls first")) {
            if (this.p.parseString("NULLS LAST") || this.p.parseString("nulls last")) {
               nullsNode = new Node(NodeType.OPERATOR, "nulls last");
            }
         } else {
            nullsNode = new Node(NodeType.OPERATOR, "nulls first");
         }

         Node expr = new Node(NodeType.OPERATOR, "order");
         expr.insertChildNode(directionNode);
         if (nullsNode != null) {
            expr.appendChildNode(nullsNode);
         }

         if (!this.stack.isEmpty()) {
            expr.insertChildNode((Node)this.stack.pop());
         }

         nodes.add(expr);
      } while(this.p.parseChar(','));

      return (Node[])nodes.toArray(new Node[nodes.size()]);
   }

   private Node processExpression() {
      this.processConditionalOrExpression();
      return (Node)this.stack.peek();
   }

   private void processConditionalOrExpression() {
      this.processConditionalAndExpression();

      while(this.p.parseString("||")) {
         this.processConditionalAndExpression();
         Node expr = new Node(NodeType.OPERATOR, "||");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }

   }

   private void processConditionalAndExpression() {
      this.processInclusiveOrExpression();

      while(this.p.parseString("&&")) {
         this.processInclusiveOrExpression();
         Node expr = new Node(NodeType.OPERATOR, "&&");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }

   }

   private void processInclusiveOrExpression() {
      this.processExclusiveOrExpression();

      while(this.p.parseChar('|', '|')) {
         this.processExclusiveOrExpression();
         Node expr = new Node(NodeType.OPERATOR, "|");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }

   }

   private void processExclusiveOrExpression() {
      this.processAndExpression();

      while(this.p.parseChar('^')) {
         this.processAndExpression();
         Node expr = new Node(NodeType.OPERATOR, "^");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }

   }

   private void processAndExpression() {
      this.processRelationalExpression();

      while(this.p.parseChar('&', '&')) {
         this.processRelationalExpression();
         Node expr = new Node(NodeType.OPERATOR, "&");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }

   }

   private void processRelationalExpression() {
      this.processAdditiveExpression();

      while(true) {
         while(!this.p.parseString("==")) {
            if (this.p.parseString("!=")) {
               this.processAdditiveExpression();
               Node expr = new Node(NodeType.OPERATOR, "!=");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            } else if (this.p.parseString("=")) {
               if (!this.allowSingleEquals) {
                  throw new QueryCompilerSyntaxException("Invalid operator \"=\". Did you mean to use \"==\"?");
               }

               this.processAdditiveExpression();
               Node expr = new Node(NodeType.OPERATOR, "==");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            } else if (this.p.parseString("<=")) {
               this.processAdditiveExpression();
               Node expr = new Node(NodeType.OPERATOR, "<=");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            } else if (this.p.parseString(">=")) {
               this.processAdditiveExpression();
               Node expr = new Node(NodeType.OPERATOR, ">=");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            } else if (this.p.parseChar('<')) {
               this.processAdditiveExpression();
               Node expr = new Node(NodeType.OPERATOR, "<");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            } else if (this.p.parseChar('>')) {
               this.processAdditiveExpression();
               Node expr = new Node(NodeType.OPERATOR, ">");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            } else {
               if (!this.p.parseString("instanceof")) {
                  return;
               }

               this.processAdditiveExpression();
               Node expr = new Node(NodeType.OPERATOR, "instanceof");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            }
         }

         this.processAdditiveExpression();
         Node expr = new Node(NodeType.OPERATOR, "==");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }
   }

   protected void processAdditiveExpression() {
      this.processMultiplicativeExpression();

      while(true) {
         while(!this.p.parseChar('+')) {
            if (!this.p.parseChar('-')) {
               return;
            }

            this.processMultiplicativeExpression();
            Node expr = new Node(NodeType.OPERATOR, "-");
            expr.insertChildNode((Node)this.stack.pop());
            expr.insertChildNode((Node)this.stack.pop());
            this.stack.push(expr);
         }

         this.processMultiplicativeExpression();
         Node expr = new Node(NodeType.OPERATOR, "+");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }
   }

   protected void processMultiplicativeExpression() {
      this.processUnaryExpression();

      while(true) {
         while(!this.p.parseChar('*')) {
            if (this.p.parseChar('/')) {
               this.processUnaryExpression();
               Node expr = new Node(NodeType.OPERATOR, "/");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            } else {
               if (!this.p.parseChar('%')) {
                  return;
               }

               this.processUnaryExpression();
               Node expr = new Node(NodeType.OPERATOR, "%");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            }
         }

         this.processUnaryExpression();
         Node expr = new Node(NodeType.OPERATOR, "*");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }
   }

   protected void processUnaryExpression() {
      if (this.p.parseString("++")) {
         throw new QueryCompilerSyntaxException("Unsupported operator '++'");
      } else if (this.p.parseString("--")) {
         throw new QueryCompilerSyntaxException("Unsupported operator '--'");
      } else {
         if (this.p.parseChar('+')) {
            this.processUnaryExpression();
         } else if (this.p.parseChar('-')) {
            this.processUnaryExpression();
            Node expr = new Node(NodeType.OPERATOR, "-");
            expr.insertChildNode((Node)this.stack.pop());
            this.stack.push(expr);
         } else if (this.p.parseChar('~')) {
            this.processUnaryExpression();
            Node expr = new Node(NodeType.OPERATOR, "~");
            expr.insertChildNode((Node)this.stack.pop());
            this.stack.push(expr);
         } else if (this.p.parseChar('!')) {
            this.processUnaryExpression();
            Node expr = new Node(NodeType.OPERATOR, "!");
            expr.insertChildNode((Node)this.stack.pop());
            this.stack.push(expr);
         } else {
            this.processPrimary();
         }

      }
   }

   protected void processPrimary() {
      if (!this.p.parseString("DISTINCT ") && !this.p.parseString("distinct")) {
         Node castNode = null;
         if (this.processCast()) {
            castNode = (Node)this.stack.pop();
         }

         if (!this.p.peekString("IF(") && !this.p.peekString("if(") && !this.p.peekString("IF (") && !this.p.peekString("if (")) {
            if (this.processCreator()) {
               boolean endOfChain = false;

               while(!endOfChain) {
                  if (this.p.parseChar('.')) {
                     if (this.processMethod()) {
                        Node invokeNode = (Node)this.stack.pop();
                        Node invokedNode = (Node)this.stack.peek();
                        invokedNode.appendChildNode(invokeNode);
                     }
                  } else {
                     endOfChain = true;
                  }
               }

               if (castNode != null) {
                  throw new NucleusException("Dont currently support compile of cast of creator expression");
               }
            } else if (this.processLiteral()) {
               boolean endOfChain = false;

               while(!endOfChain) {
                  if (this.p.parseChar('.')) {
                     if (this.processMethod()) {
                        Node invokeNode = (Node)this.stack.pop();
                        Node invokedNode = (Node)this.stack.peek();
                        invokedNode.appendChildNode(invokeNode);
                     }
                  } else {
                     endOfChain = true;
                  }
               }

               if (castNode != null) {
                  throw new NucleusException("Dont currently support compile of cast of literal expression");
               }
            } else if (this.processMethod()) {
               if (castNode != null) {
                  throw new NucleusException("Dont currently support compile of cast of static method call");
               }
            } else if (this.processArray()) {
               boolean endOfChain = false;

               while(!endOfChain) {
                  if (this.p.parseChar('.')) {
                     if (this.processMethod()) {
                        Node invokeNode = (Node)this.stack.pop();
                        Node invokedNode = (Node)this.stack.peek();
                        invokedNode.appendChildNode(invokeNode);
                     }
                  } else {
                     endOfChain = true;
                  }
               }

               if (castNode != null) {
                  throw new NucleusException("Dont currently support compile of cast of array expression");
               }
            } else {
               int sizeBeforeBraceProcessing = this.stack.size();
               boolean braceProcessing = false;
               if (this.p.parseChar('(')) {
                  this.processExpression();
                  if (!this.p.parseChar(')')) {
                     throw new QueryCompilerSyntaxException("expected ')'", this.p.getIndex(), this.p.getInput());
                  }

                  if (!this.p.parseChar('.')) {
                     return;
                  }

                  braceProcessing = true;
               }

               if (!this.processMethod() && !this.processIdentifier()) {
                  throw new QueryCompilerSyntaxException("Method/Identifier expected", this.p.getIndex(), this.p.getInput());
               } else {
                  int size = this.stack.size();
                  if (braceProcessing) {
                     size = sizeBeforeBraceProcessing + 1;
                  }

                  while(this.p.parseChar('.')) {
                     if (!this.processMethod() && !this.processIdentifier()) {
                        throw new QueryCompilerSyntaxException("Identifier expected", this.p.getIndex(), this.p.getInput());
                     }
                  }

                  if (castNode != null) {
                     ((Node)this.stack.peek()).appendChildNode(castNode);
                  }

                  while(this.stack.size() > size) {
                     Node top = (Node)this.stack.pop();
                     Node peek = (Node)this.stack.peek();
                     Node lastDescendant = this.getLastDescendantNodeForNode(peek);
                     if (lastDescendant != null) {
                        lastDescendant.appendChildNode(top);
                     } else {
                        Node primNode = new Node(NodeType.PRIMARY);
                        primNode.appendChildNode(peek);
                        primNode.appendChildNode(top);
                        this.stack.pop();
                        this.stack.push(primNode);
                     }
                  }

               }
            }
         } else {
            this.processIfElseExpression();
         }
      } else {
         Node distinctNode = new Node(NodeType.OPERATOR, "DISTINCT");
         this.processExpression();
         Node identifierNode = (Node)this.stack.pop();
         distinctNode.appendChildNode(identifierNode);
         this.stack.push(distinctNode);
      }
   }

   private Node getLastDescendantNodeForNode(Node node) {
      if (node == null) {
         return null;
      } else if (node.getChildNodes() == null) {
         return node;
      } else if (node.getChildNodes().size() > 1) {
         return null;
      } else {
         return !node.hasNextChild() ? node : this.getLastDescendantNodeForNode(node.getChildNode(0));
      }
   }

   private void processIfElseExpression() {
      Node caseNode = new Node(NodeType.CASE);
      if (this.p.parseString("IF") && !this.p.parseString("if")) {
         if (!this.p.parseChar('(')) {
            throw new QueryCompilerSyntaxException("Expected '(' as part of IF (...)", this.p.getIndex(), this.p.getInput());
         } else {
            this.processExpression();
            Node whenNode = (Node)this.stack.pop();
            caseNode.appendChildNode(whenNode);
            if (!this.p.parseChar(')')) {
               throw new QueryCompilerSyntaxException("Expected ')' as part of IF (...)", this.p.getIndex(), this.p.getInput());
            } else {
               this.processExpression();
               Node actionNode = (Node)this.stack.pop();
               caseNode.appendChildNode(actionNode);
               boolean elseClause = false;

               while(this.p.parseString("ELSE") || this.p.parseString("else")) {
                  boolean hasIf = false;
                  if (this.p.parseString("IF") || this.p.parseString("if")) {
                     hasIf = true;
                     if (!this.p.parseChar('(')) {
                        throw new QueryCompilerSyntaxException("Expected '(' as part of IF (...)", this.p.getIndex(), this.p.getInput());
                     }

                     this.processExpression();
                     whenNode = (Node)this.stack.pop();
                     caseNode.appendChildNode(whenNode);
                     if (!this.p.parseChar(')')) {
                        throw new QueryCompilerSyntaxException("Expected ')' as part of IF (...)", this.p.getIndex(), this.p.getInput());
                     }
                  }

                  this.processExpression();
                  actionNode = (Node)this.stack.pop();
                  caseNode.appendChildNode(actionNode);
                  if (!hasIf) {
                     elseClause = true;
                  }
               }

               if (!elseClause) {
                  throw new QueryCompilerSyntaxException("Use of IF {expr} ELSE IF {expr} structure should always terminate with ELSE {expr} but doesn't", this.p.getIndex(), this.p.getInput());
               } else {
                  this.stack.push(caseNode);
               }
            }
         }
      } else {
         throw new QueryCompilerSyntaxException("Expected IF or if", this.p.getIndex(), this.p.getInput());
      }
   }

   private boolean processCast() {
      String typeName = this.p.parseCast();
      if (typeName == null) {
         return false;
      } else {
         Node castNode = new Node(NodeType.CAST, typeName);
         this.stack.push(castNode);
         return true;
      }
   }

   private boolean processCreator() {
      if (!this.p.parseString("new ")) {
         return false;
      } else {
         int size = this.stack.size();
         if (!this.processMethod()) {
            if (!this.processIdentifier()) {
               throw new QueryCompilerSyntaxException("Identifier expected", this.p.getIndex(), this.p.getInput());
            }

            while(this.p.parseChar('.')) {
               if (!this.processMethod() && !this.processIdentifier()) {
                  throw new QueryCompilerSyntaxException("Identifier expected", this.p.getIndex(), this.p.getInput());
               }
            }
         }

         while(this.stack.size() - 1 > size) {
            Node top = (Node)this.stack.pop();
            Node peek = (Node)this.stack.peek();
            peek.insertChildNode(top);
         }

         Node expr = (Node)this.stack.pop();
         Node newExpr = new Node(NodeType.CREATOR);
         newExpr.insertChildNode(expr);
         this.stack.push(newExpr);
         return true;
      }
   }

   private boolean processMethod() {
      String method = this.p.parseMethod();
      if (method != null) {
         this.p.skipWS();
         this.p.parseChar('(');
         if (this.strictJDOQL && Arrays.binarySearch(jdoqlMethodNames, method) < 0) {
            throw new QueryCompilerSyntaxException("Query uses method \"" + method + "\" but this is not a standard JDOQL method name");
         } else {
            Node expr = new Node(NodeType.INVOKE, method);
            if (!this.p.parseChar(')')) {
               do {
                  this.processExpression();
                  expr.addProperty((Node)this.stack.pop());
               } while(this.p.parseChar(','));

               if (!this.p.parseChar(')')) {
                  throw new QueryCompilerSyntaxException("')' expected", this.p.getIndex(), this.p.getInput());
               }
            }

            this.stack.push(expr);
            return true;
         }
      } else {
         return false;
      }
   }

   private boolean processArray() {
      if (!this.p.parseChar('{')) {
         return false;
      } else {
         List<Node> elements = new ArrayList();

         while(!this.p.parseChar('}')) {
            this.processPrimary();
            Node elementNode = (Node)this.stack.pop();
            elements.add(elementNode);
            if (this.p.parseChar('}')) {
               break;
            }

            if (!this.p.parseChar(',')) {
               throw new QueryCompilerSyntaxException("',' or '}' expected", this.p.getIndex(), this.p.getInput());
            }
         }

         Node arrayNode = new Node(NodeType.ARRAY, elements);
         this.stack.push(arrayNode);
         if (this.p.parseString(".length")) {
            Node lengthMethod = new Node(NodeType.INVOKE, "length");
            arrayNode.appendChildNode(lengthMethod);
         }

         return true;
      }
   }

   protected boolean processLiteral() {
      Object litValue = null;
      boolean single_quote_next = this.p.nextIsSingleQuote();
      String sLiteral;
      if ((sLiteral = this.p.parseStringLiteral()) != null) {
         if (sLiteral.length() == 1 && single_quote_next) {
            litValue = sLiteral.charAt(0);
         } else {
            litValue = sLiteral;
         }
      } else {
         BigDecimal fLiteral;
         if ((fLiteral = this.p.parseFloatingPointLiteral()) != null) {
            litValue = fLiteral;
         } else {
            BigInteger iLiteral;
            if ((iLiteral = this.p.parseIntegerLiteral()) != null) {
               String longStr = "" + iLiteral.longValue();
               if (longStr.length() < iLiteral.toString().length()) {
                  litValue = iLiteral;
               } else {
                  litValue = iLiteral.longValue();
               }
            } else {
               Boolean bLiteral;
               if ((bLiteral = this.p.parseBooleanLiteral()) != null) {
                  litValue = bLiteral;
               } else if (!this.p.parseNullLiteral()) {
                  return false;
               }
            }
         }
      }

      this.stack.push(new Node(NodeType.LITERAL, litValue));
      return true;
   }

   private boolean processIdentifier() {
      String id = this.p.parseIdentifier();
      if (id == null) {
         return false;
      } else {
         char first = id.charAt(0);
         if (first == ':') {
            if (this.paramType == JDOQLParser.ParameterType.EXPLICIT) {
               throw new QueryCompilerSyntaxException("Explicit parameters defined for query, yet implicit parameter syntax (\"" + id + "\") found");
            } else {
               String name = id.substring(1);
               Node expr = new ParameterNode(NodeType.PARAMETER, name, this.getPositionFromParameterName(name));
               this.stack.push(expr);
               return true;
            }
         } else {
            Node expr = new Node(NodeType.IDENTIFIER, id);
            this.stack.push(expr);
            return true;
         }
      }
   }

   private int getPositionFromParameterName(Object name) {
      if (this.parameterNameList == null) {
         this.parameterNameList = new ArrayList(1);
      }

      int pos = this.parameterNameList.indexOf(name);
      if (pos == -1) {
         pos = this.parameterNameList.size();
         this.parameterNameList.add(name);
      }

      return pos;
   }

   private static enum ParameterType {
      IMPLICIT,
      EXPLICIT;
   }
}
