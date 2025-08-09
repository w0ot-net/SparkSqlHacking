package org.datanucleus.query.compiler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.query.QueryCompilerSyntaxException;

public class JPQLParser implements Parser {
   private Lexer lexer;
   private Deque stack = new ArrayDeque();
   private static String paramPrefixes = ":?";
   private boolean strictJPQL = false;
   int parameterPosition = 0;

   public JPQLParser(Map options) {
      if (options != null && options.containsKey("jpql.strict")) {
         this.strictJPQL = Boolean.valueOf((String)options.get("jpql.strict"));
      }

   }

   public Node parse(String expression) {
      this.lexer = new Lexer(expression, paramPrefixes, false);
      this.stack = new ArrayDeque();
      Node result = this.processExpression();
      if (this.lexer.ci.getIndex() != this.lexer.ci.getEndIndex()) {
         throw new QueryCompilerSyntaxException("Portion of expression could not be parsed: " + this.lexer.getInput().substring(this.lexer.ci.getIndex()));
      } else {
         return result;
      }
   }

   public Node parseVariable(String expression) {
      this.lexer = new Lexer(expression, paramPrefixes, false);
      this.stack = new ArrayDeque();
      if (!this.processIdentifier()) {
         throw new QueryCompilerSyntaxException("expected identifier", this.lexer.getIndex(), this.lexer.getInput());
      } else if (!this.processIdentifier()) {
         throw new QueryCompilerSyntaxException("expected identifier", this.lexer.getIndex(), this.lexer.getInput());
      } else {
         Node nodeVariable = (Node)this.stack.pop();
         Node nodeType = (Node)this.stack.pop();
         nodeType.appendChildNode(nodeVariable);
         return nodeType;
      }
   }

   public Node[] parseFrom(String expression) {
      this.lexer = new Lexer(expression, paramPrefixes, false);
      this.stack = new ArrayDeque();
      return this.processFromExpression();
   }

   public Node[] parseUpdate(String expression) {
      this.lexer = new Lexer(expression, paramPrefixes, false);
      this.stack = new ArrayDeque();
      return this.parseTuple(expression);
   }

   public Node[] parseOrder(String expression) {
      this.lexer = new Lexer(expression, paramPrefixes, false);
      this.stack = new ArrayDeque();
      return this.processOrderExpression();
   }

   public Node[] parseResult(String expression) {
      this.lexer = new Lexer(expression, paramPrefixes, false);
      this.stack = new ArrayDeque();
      List nodes = new ArrayList();

      do {
         this.processExpression();
         Node node = (Node)this.stack.pop();
         String alias = this.lexer.parseIdentifier();
         if (alias != null && alias.equalsIgnoreCase("AS")) {
            alias = this.lexer.parseIdentifier();
         }

         if (alias != null) {
            Node aliasNode = new Node(NodeType.NAME, alias.toLowerCase());
            node.appendChildNode(aliasNode);
         }

         nodes.add(node);
      } while(this.lexer.parseString(","));

      return (Node[])nodes.toArray(new Node[nodes.size()]);
   }

   public Node[] parseTuple(String expression) {
      this.lexer = new Lexer(expression, paramPrefixes, false);
      this.stack = new ArrayDeque();
      List nodes = new ArrayList();

      do {
         this.processExpression();
         Node node = (Node)this.stack.pop();
         nodes.add(node);
      } while(this.lexer.parseString(","));

      return (Node[])nodes.toArray(new Node[nodes.size()]);
   }

   public Node[][] parseVariables(String expression) {
      this.lexer = new Lexer(expression, paramPrefixes, false);
      List nodes = new ArrayList();

      do {
         this.processPrimary();
         if (this.stack.isEmpty()) {
            throw new QueryCompilerSyntaxException("expected identifier", this.lexer.getIndex(), this.lexer.getInput());
         }

         if (!this.processIdentifier()) {
            throw new QueryCompilerSyntaxException("expected identifier", this.lexer.getIndex(), this.lexer.getInput());
         }

         Node nodeVariable = (Node)this.stack.pop();
         Node nodeType = (Node)this.stack.pop();
         nodes.add(new Node[]{nodeType, nodeVariable});
      } while(this.lexer.parseString(";"));

      return (Node[][])nodes.toArray(new Node[nodes.size()][2]);
   }

   public Node[][] parseParameters(String expression) {
      this.lexer = new Lexer(expression, paramPrefixes, false);
      List nodes = new ArrayList();

      do {
         this.processPrimary();
         if (this.stack.isEmpty()) {
            throw new QueryCompilerSyntaxException("expected identifier", this.lexer.getIndex(), this.lexer.getInput());
         }

         if (!this.processIdentifier()) {
            throw new QueryCompilerSyntaxException("expected identifier", this.lexer.getIndex(), this.lexer.getInput());
         }

         Node nodeVariable = (Node)this.stack.pop();
         Node nodeType = (Node)this.stack.pop();
         nodes.add(new Node[]{nodeType, nodeVariable});
      } while(this.lexer.parseString(","));

      return (Node[][])nodes.toArray(new Node[nodes.size()][2]);
   }

   private Node[] processFromExpression() {
      String candidateClassName = null;
      String candidateAlias = null;
      List nodes = new ArrayList();

      do {
         if (!this.lexer.peekStringIgnoreCase("IN(") && !this.lexer.peekStringIgnoreCase("IN ")) {
            this.processExpression();
            Node id = (Node)this.stack.pop();
            StringBuilder className = new StringBuilder(id.getNodeValue().toString());

            while(id.getChildNodes().size() > 0) {
               id = id.getFirstChild();
               className.append(".").append(id.getNodeValue().toString());
            }

            String alias = this.lexer.parseIdentifier();
            if (alias != null && alias.equalsIgnoreCase("AS")) {
               alias = this.lexer.parseIdentifier();
            }

            if (candidateClassName == null) {
               candidateClassName = className.toString();
               candidateAlias = alias;
            }

            Node classNode = new Node(NodeType.CLASS, className.toString());
            Node aliasNode = new Node(NodeType.NAME, alias);
            classNode.insertChildNode(aliasNode);
            this.stack.push(classNode);
            this.processFromJoinExpression();
            nodes.add(classNode);
         } else {
            this.lexer.parseStringIgnoreCase("IN");
            if (!this.lexer.parseChar('(')) {
               throw new QueryCompilerSyntaxException("Expected: '(' but got " + this.lexer.remaining(), this.lexer.getIndex(), this.lexer.getInput());
            }

            String name = this.lexer.parseIdentifier();
            Node joinedNode = new Node(NodeType.IDENTIFIER, name);

            Node subNode;
            for(Node parentNode = joinedNode; this.lexer.nextIsDot(); parentNode = subNode) {
               this.lexer.parseChar('.');
               String subName = this.lexer.parseIdentifier();
               subNode = new Node(NodeType.IDENTIFIER, subName);
               parentNode.appendChildNode(subNode);
            }

            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("Expected: ')' but got " + this.lexer.remaining(), this.lexer.getIndex(), this.lexer.getInput());
            }

            this.lexer.parseStringIgnoreCase("AS");
            String alias = this.lexer.parseIdentifier();
            subNode = new Node(NodeType.CLASS, candidateClassName);
            Node classAliasNode = new Node(NodeType.NAME, candidateAlias);
            subNode.insertChildNode(classAliasNode);
            this.stack.push(subNode);
            Node joinNode = new Node(NodeType.OPERATOR, "JOIN_INNER");
            joinNode.appendChildNode(joinedNode);
            Node joinAliasNode = new Node(NodeType.NAME, alias);
            joinNode.appendChildNode(joinAliasNode);
            subNode.appendChildNode(joinNode);
            this.processFromJoinExpression();
            nodes.add(subNode);
         }
      } while(this.lexer.parseString(","));

      return (Node[])nodes.toArray(new Node[nodes.size()]);
   }

   private void processFromJoinExpression() {
      Node candidateNode = (Node)this.stack.pop();
      boolean moreJoins = true;

      while(moreJoins) {
         boolean leftJoin = false;
         boolean rightJoin = false;
         boolean innerJoin = false;
         if (this.lexer.parseStringIgnoreCase("INNER ")) {
            innerJoin = true;
         } else if (this.lexer.parseStringIgnoreCase("LEFT ")) {
            this.lexer.parseStringIgnoreCase("OUTER");
            leftJoin = true;
         } else if (this.lexer.parseStringIgnoreCase("RIGHT ")) {
            this.lexer.parseStringIgnoreCase("OUTER");
            rightJoin = true;
         }

         if (!this.lexer.parseStringIgnoreCase("JOIN ")) {
            if (innerJoin || leftJoin) {
               throw new NucleusUserException("Expected JOIN after INNER/LEFT keyword at" + this.lexer.remaining());
            }

            moreJoins = false;
         } else {
            if (!innerJoin && !leftJoin && !rightJoin) {
               innerJoin = true;
            }

            boolean fetch = false;
            if (this.lexer.parseStringIgnoreCase("FETCH")) {
               fetch = true;
            }

            String joinType = "JOIN_INNER";
            if (innerJoin) {
               joinType = fetch ? "JOIN_INNER_FETCH" : "JOIN_INNER";
            } else if (leftJoin) {
               joinType = fetch ? "JOIN_OUTER_FETCH" : "JOIN_OUTER";
            } else if (rightJoin) {
               joinType = fetch ? "JOIN_OUTER_FETCH_RIGHT" : "JOIN_OUTER_RIGHT";
            }

            Node joinNode = new Node(NodeType.OPERATOR, joinType);
            if (this.processTreat()) {
               Node treatNode = (Node)this.stack.pop();
               joinNode.appendChildNode(treatNode);
               this.lexer.parseStringIgnoreCase("AS ");
               String alias = this.lexer.parseName();
               Node joinedAliasNode = new Node(NodeType.NAME, alias);
               joinNode.appendChildNode(joinedAliasNode);
            } else {
               String id = this.lexer.parseIdentifier();
               Node joinedNode = new Node(NodeType.IDENTIFIER, id);

               Node subNode;
               for(Node parentNode = joinedNode; this.lexer.nextIsDot(); parentNode = subNode) {
                  this.lexer.parseChar('.');
                  subNode = new Node(NodeType.IDENTIFIER, this.lexer.parseName());
                  parentNode.appendChildNode(subNode);
               }

               joinNode.appendChildNode(joinedNode);
               this.lexer.parseStringIgnoreCase("AS ");
               String alias = this.lexer.parseName();
               Node joinedAliasNode = new Node(NodeType.NAME, alias);
               joinNode.appendChildNode(joinedAliasNode);
            }

            if (this.lexer.parseStringIgnoreCase("ON ")) {
               this.processExpression();
               Node onNode = (Node)this.stack.pop();
               joinNode.appendChildNode(onNode);
            }

            candidateNode.appendChildNode(joinNode);
         }
      }

      this.stack.push(candidateNode);
   }

   private Node[] processOrderExpression() {
      List nodes = new ArrayList();

      do {
         this.processExpression();
         Node directionNode = null;
         if (this.lexer.parseStringIgnoreCase("asc")) {
            directionNode = new Node(NodeType.OPERATOR, "ascending");
         } else if (this.lexer.parseStringIgnoreCase("desc")) {
            directionNode = new Node(NodeType.OPERATOR, "descending");
         } else {
            directionNode = new Node(NodeType.OPERATOR, "ascending");
         }

         Node nullsNode = null;
         if (!this.lexer.parseString("NULLS FIRST") && !this.lexer.parseString("nulls first")) {
            if (this.lexer.parseString("NULLS LAST") || this.lexer.parseString("nulls last")) {
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
      } while(this.lexer.parseString(","));

      return (Node[])nodes.toArray(new Node[nodes.size()]);
   }

   private Node processExpression() {
      this.processOrExpression();
      return (Node)this.stack.peek();
   }

   private void processOrExpression() {
      this.processAndExpression();

      while(this.lexer.parseStringIgnoreCase("OR ")) {
         this.processAndExpression();
         Node expr = new Node(NodeType.OPERATOR, "||");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }

   }

   private void processAndExpression() {
      this.processRelationalExpression();

      while(this.lexer.parseStringIgnoreCase("AND ")) {
         this.processRelationalExpression();
         Node expr = new Node(NodeType.OPERATOR, "&&");
         expr.insertChildNode((Node)this.stack.pop());
         expr.insertChildNode((Node)this.stack.pop());
         this.stack.push(expr);
      }

   }

   private void processRelationalExpression() {
      this.processAdditiveExpression();

      while(true) {
         while(!this.lexer.parseString("=")) {
            if (this.lexer.parseString("<>")) {
               this.processAdditiveExpression();
               Node right = (Node)this.stack.pop();
               Node left = (Node)this.stack.pop();
               if (right.getNodeType() == NodeType.TYPE) {
                  Node primNode = right.getFirstChild();
                  Node expr = new Node(NodeType.OPERATOR, "instanceof");
                  expr.appendChildNode(primNode);
                  expr.appendChildNode(left);
                  Node notNode = new Node(NodeType.OPERATOR, "!");
                  notNode.appendChildNode(expr);
                  this.stack.push(notNode);
               } else if (left.getNodeType() == NodeType.TYPE) {
                  Node primNode = left.getFirstChild();
                  Node expr = new Node(NodeType.OPERATOR, "instanceof");
                  expr.appendChildNode(primNode);
                  expr.appendChildNode(right);
                  Node notNode = new Node(NodeType.OPERATOR, "!");
                  notNode.appendChildNode(expr);
                  this.stack.push(notNode);
               } else {
                  Node expr = new Node(NodeType.OPERATOR, "!=");
                  expr.insertChildNode(right);
                  expr.insertChildNode(left);
                  this.stack.push(expr);
               }
            } else if (this.lexer.parseStringIgnoreCase("NOT ")) {
               if (this.lexer.parseStringIgnoreCase("BETWEEN ")) {
                  Node inputNode = (Node)this.stack.pop();
                  this.processAdditiveExpression();
                  Node lowerNode = (Node)this.stack.pop();
                  if (!this.lexer.parseStringIgnoreCase("AND ")) {
                     throw new NucleusUserException("Query has BETWEEN keyword with no AND clause");
                  }

                  this.processAdditiveExpression();
                  Node upperNode = (Node)this.stack.pop();
                  Node leftNode = new Node(NodeType.OPERATOR, "<");
                  leftNode.appendChildNode(inputNode);
                  leftNode.appendChildNode(lowerNode);
                  Node rightNode = new Node(NodeType.OPERATOR, ">");
                  rightNode.appendChildNode(inputNode);
                  rightNode.appendChildNode(upperNode);
                  Node betweenNode = new Node(NodeType.OPERATOR, "||");
                  betweenNode.appendChildNode(leftNode);
                  betweenNode.appendChildNode(rightNode);
                  this.stack.push(betweenNode);
               } else if (this.lexer.parseStringIgnoreCase("LIKE ")) {
                  this.processLikeExpression();
                  Node notNode = new Node(NodeType.OPERATOR, "!");
                  notNode.insertChildNode((Node)this.stack.pop());
                  this.stack.push(notNode);
               } else if (this.lexer.parseStringIgnoreCase("IN")) {
                  this.processInExpression(true);
               } else {
                  if (!this.lexer.parseStringIgnoreCase("MEMBER ")) {
                     throw new NucleusException("Unsupported query syntax NOT followed by unsupported keyword");
                  }

                  this.processMemberExpression(true);
               }
            } else if (this.lexer.parseStringIgnoreCase("BETWEEN ")) {
               Node inputNode = (Node)this.stack.pop();
               this.processAdditiveExpression();
               Node lowerNode = (Node)this.stack.pop();
               if (!this.lexer.parseStringIgnoreCase("AND ")) {
                  throw new NucleusUserException("Query has BETWEEN keyword with no AND clause");
               }

               this.processAdditiveExpression();
               Node upperNode = (Node)this.stack.pop();
               Node leftNode = new Node(NodeType.OPERATOR, ">=");
               leftNode.appendChildNode(inputNode);
               leftNode.appendChildNode(lowerNode);
               Node rightNode = new Node(NodeType.OPERATOR, "<=");
               rightNode.appendChildNode(inputNode);
               rightNode.appendChildNode(upperNode);
               Node betweenNode = new Node(NodeType.OPERATOR, "&&");
               betweenNode.appendChildNode(rightNode);
               betweenNode.appendChildNode(leftNode);
               this.stack.push(betweenNode);
            } else if (this.lexer.parseStringIgnoreCase("LIKE ")) {
               this.processLikeExpression();
            } else if (this.lexer.parseStringIgnoreCase("IN")) {
               this.processInExpression(false);
            } else if (this.lexer.parseStringIgnoreCase("MEMBER ")) {
               this.processMemberExpression(false);
            } else if (!this.lexer.parseStringIgnoreCase("IS ")) {
               if (this.lexer.parseString("<=")) {
                  this.processAdditiveExpression();
                  Node expr = new Node(NodeType.OPERATOR, "<=");
                  expr.insertChildNode((Node)this.stack.pop());
                  expr.insertChildNode((Node)this.stack.pop());
                  this.stack.push(expr);
               } else if (this.lexer.parseString(">=")) {
                  this.processAdditiveExpression();
                  Node expr = new Node(NodeType.OPERATOR, ">=");
                  expr.insertChildNode((Node)this.stack.pop());
                  expr.insertChildNode((Node)this.stack.pop());
                  this.stack.push(expr);
               } else if (this.lexer.parseChar('<')) {
                  this.processAdditiveExpression();
                  Node expr = new Node(NodeType.OPERATOR, "<");
                  expr.insertChildNode((Node)this.stack.pop());
                  expr.insertChildNode((Node)this.stack.pop());
                  this.stack.push(expr);
               } else {
                  if (!this.lexer.parseChar('>')) {
                     return;
                  }

                  this.processAdditiveExpression();
                  Node expr = new Node(NodeType.OPERATOR, ">");
                  expr.insertChildNode((Node)this.stack.pop());
                  expr.insertChildNode((Node)this.stack.pop());
                  this.stack.push(expr);
               }
            } else {
               Node inputNode = (Node)this.stack.pop();
               Node inputRootNode = inputNode;
               if (inputNode.getNodeType() == NodeType.IDENTIFIER) {
                  while(inputNode.getFirstChild() != null) {
                     inputNode = inputNode.getFirstChild();
                  }
               }

               boolean not = false;
               if (this.lexer.parseStringIgnoreCase("NOT ")) {
                  not = true;
               }

               if (this.lexer.parseStringIgnoreCase("NULL")) {
                  Node isNode = new Node(NodeType.OPERATOR, not ? "!=" : "==");
                  Node compareNode = new Node(NodeType.LITERAL, (Object)null);
                  isNode.insertChildNode(compareNode);
                  isNode.insertChildNode(inputRootNode);
                  this.stack.push(isNode);
               } else {
                  if (!this.lexer.parseStringIgnoreCase("EMPTY")) {
                     throw new NucleusException("Encountered IS " + (not ? "NOT " : " ") + " that should be followed by NULL | EMPTY but isnt");
                  }

                  Node sizeNode = new Node(NodeType.INVOKE, "size");
                  inputNode.insertChildNode(sizeNode);
                  Node isEmptyNode = new Node(NodeType.OPERATOR, not ? "!=" : "==");
                  isEmptyNode.appendChildNode(inputNode);
                  Node zeroNode = new Node(NodeType.LITERAL, 0);
                  isEmptyNode.appendChildNode(zeroNode);
                  this.stack.push(isEmptyNode);
               }
            }
         }

         this.processAdditiveExpression();
         Node right = (Node)this.stack.pop();
         Node left = (Node)this.stack.pop();
         if (right.getNodeType() == NodeType.TYPE) {
            Node primNode = right.getFirstChild();
            Node expr = new Node(NodeType.OPERATOR, "instanceof");
            expr.appendChildNode(primNode);
            expr.appendChildNode(left);
            this.stack.push(expr);
         } else if (left.getNodeType() == NodeType.TYPE) {
            Node primNode = left.getFirstChild();
            Node expr = new Node(NodeType.OPERATOR, "instanceof");
            expr.appendChildNode(primNode);
            expr.appendChildNode(right);
            this.stack.push(expr);
         } else {
            Node expr = new Node(NodeType.OPERATOR, "==");
            expr.insertChildNode(right);
            expr.insertChildNode(left);
            this.stack.push(expr);
         }
      }
   }

   private void processLikeExpression() {
      Node primaryNode = (Node)this.stack.pop();
      Node primaryRootNode = primaryNode;
      if (primaryNode.getNodeType() == NodeType.IDENTIFIER) {
         while(primaryNode.getFirstChild() != null) {
            primaryNode = primaryNode.getFirstChild();
         }
      }

      this.processAdditiveExpression();
      Node likeExprNode = (Node)this.stack.pop();
      if (this.lexer.parseStringIgnoreCase("ESCAPE")) {
         this.processAdditiveExpression();
         Node escapeNode = (Node)this.stack.pop();
         Node matchesNode = new Node(NodeType.INVOKE, "matches");
         matchesNode.addProperty(likeExprNode);
         matchesNode.addProperty(escapeNode);
         primaryNode.appendChildNode(matchesNode);
         this.stack.push(primaryRootNode);
      } else {
         Node matchesNode = new Node(NodeType.INVOKE, "matches");
         matchesNode.addProperty(likeExprNode);
         primaryNode.appendChildNode(matchesNode);
         this.stack.push(primaryRootNode);
      }

   }

   private void processInExpression(boolean not) {
      Node inputNode = (Node)this.stack.pop();
      if (!this.lexer.parseChar('(')) {
         Node inNode = new Node(NodeType.OPERATOR, not ? "NOT IN" : "IN");
         inNode.appendChildNode(inputNode);
         this.processPrimary();
         Node subqueryNode = (Node)this.stack.pop();
         inNode.appendChildNode(subqueryNode);
         this.stack.push(inNode);
      } else {
         List<Node> valueNodes = new ArrayList();

         do {
            this.processPrimary();
            if (this.stack.peek() == null) {
               throw new QueryCompilerSyntaxException("Expected literal|parameter but got " + this.lexer.remaining(), this.lexer.getIndex(), this.lexer.getInput());
            }

            Node valueNode = (Node)this.stack.pop();
            valueNodes.add(valueNode);
            this.lexer.skipWS();
         } while(this.lexer.parseChar(','));

         if (!this.lexer.parseChar(')')) {
            throw new QueryCompilerSyntaxException("Expected: ')' but got " + this.lexer.remaining(), this.lexer.getIndex(), this.lexer.getInput());
         } else if (valueNodes.isEmpty()) {
            throw new QueryCompilerSyntaxException("IN expression had zero arguments!");
         } else {
            Node inNode = null;
            Node firstValueNode = (Node)valueNodes.get(0);
            if (valueNodes.size() == 1 && firstValueNode.getNodeType() != NodeType.LITERAL) {
               inNode = new Node(NodeType.OPERATOR, not ? "NOT IN" : "IN");
               inNode.appendChildNode(inputNode);
               inNode.appendChildNode((Node)valueNodes.get(0));
            } else {
               for(Node valueNode : valueNodes) {
                  Node compareNode = new Node(NodeType.OPERATOR, not ? "!=" : "==");
                  compareNode.appendChildNode(inputNode);
                  compareNode.appendChildNode(valueNode);
                  if (inNode == null) {
                     inNode = compareNode;
                  } else {
                     Node newInNode = new Node(NodeType.OPERATOR, not ? "&&" : "||");
                     newInNode.appendChildNode(inNode);
                     newInNode.appendChildNode(compareNode);
                     inNode = newInNode;
                  }
               }
            }

            this.stack.push(inNode);
         }
      }
   }

   private void processMemberExpression(boolean not) {
      Node inputNode = (Node)this.stack.pop();
      this.lexer.parseStringIgnoreCase("OF");
      this.processPrimary();
      Node containerNode = (Node)this.stack.peek();

      Node lastNode;
      for(lastNode = containerNode; lastNode.getFirstChild() != null; lastNode = lastNode.getFirstChild()) {
      }

      if (not) {
         Node notNode = new Node(NodeType.OPERATOR, "!");
         this.stack.pop();
         notNode.insertChildNode(containerNode);
         this.stack.push(notNode);
      }

      Node containsNode = new Node(NodeType.INVOKE, "contains");
      containsNode.addProperty(inputNode);
      lastNode.appendChildNode(containsNode);
   }

   private void processCaseExpression() {
      Node caseNode = new Node(NodeType.CASE);
      boolean simple = true;
      if (this.lexer.peekStringIgnoreCase("WHEN ")) {
         simple = false;
      }

      if (simple) {
         Node exprNode = this.processExpression();
         this.stack.pop();

         while(this.lexer.parseStringIgnoreCase("WHEN ")) {
            this.processExpression();
            Node eqCondNode = (Node)this.stack.pop();
            Node whenNode = new Node(NodeType.OPERATOR, "==");
            whenNode.insertChildNode(exprNode.clone((Node)null));
            whenNode.insertChildNode(eqCondNode);
            caseNode.appendChildNode(whenNode);
            boolean hasThen = this.lexer.parseStringIgnoreCase("THEN ");
            if (!hasThen) {
               throw new QueryCompilerSyntaxException("expected 'THEN' as part of CASE", this.lexer.getIndex(), this.lexer.getInput());
            }

            this.processExpression();
            Node actionNode = (Node)this.stack.pop();
            caseNode.appendChildNode(actionNode);
         }

         if (this.lexer.parseStringIgnoreCase("ELSE ")) {
            this.processExpression();
            Node elseNode = (Node)this.stack.pop();
            caseNode.appendChildNode(elseNode);
         }

         if (!this.lexer.parseStringIgnoreCase("END")) {
            throw new QueryCompilerSyntaxException("expected 'END' as part of CASE", this.lexer.getIndex(), this.lexer.getInput());
         }
      } else {
         while(this.lexer.parseStringIgnoreCase("WHEN ")) {
            this.processExpression();
            Node whenNode = (Node)this.stack.pop();
            caseNode.appendChildNode(whenNode);
            boolean hasThen = this.lexer.parseStringIgnoreCase("THEN ");
            if (!hasThen) {
               throw new QueryCompilerSyntaxException("expected 'THEN' as part of CASE", this.lexer.getIndex(), this.lexer.getInput());
            }

            this.processExpression();
            Node actionNode = (Node)this.stack.pop();
            caseNode.appendChildNode(actionNode);
         }

         if (this.lexer.parseStringIgnoreCase("ELSE ")) {
            this.processExpression();
            Node elseNode = (Node)this.stack.pop();
            caseNode.appendChildNode(elseNode);
         }

         if (!this.lexer.parseStringIgnoreCase("END")) {
            throw new QueryCompilerSyntaxException("expected 'END' as part of CASE", this.lexer.getIndex(), this.lexer.getInput());
         }
      }

      this.stack.push(caseNode);
   }

   protected void processAdditiveExpression() {
      this.processMultiplicativeExpression();

      while(true) {
         while(!this.lexer.parseChar('+')) {
            if (!this.lexer.parseChar('-')) {
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
         while(!this.lexer.parseChar('*')) {
            if (this.lexer.parseChar('/')) {
               this.processUnaryExpression();
               Node expr = new Node(NodeType.OPERATOR, "/");
               expr.insertChildNode((Node)this.stack.pop());
               expr.insertChildNode((Node)this.stack.pop());
               this.stack.push(expr);
            } else {
               if (!this.lexer.parseChar('%')) {
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
      if (this.lexer.parseString("++")) {
         throw new NucleusUserException("Unsupported operator '++'");
      } else if (this.lexer.parseString("--")) {
         throw new NucleusUserException("Unsupported operator '--'");
      } else {
         if (this.lexer.parseChar('+')) {
            this.processUnaryExpression();
         } else if (this.lexer.parseChar('-')) {
            this.processUnaryExpression();
            Node expr = new Node(NodeType.OPERATOR, "-");
            expr.insertChildNode((Node)this.stack.pop());
            this.stack.push(expr);
         } else if (this.lexer.parseStringIgnoreCase("NOT ")) {
            this.processRelationalExpression();
            Node expr = new Node(NodeType.OPERATOR, "!");
            expr.insertChildNode((Node)this.stack.pop());
            this.stack.push(expr);
         } else {
            this.processPrimary();
         }

      }
   }

   protected void processPrimary() {
      String subqueryKeyword = null;
      Node subqueryNode = null;
      if (this.lexer.parseStringIgnoreCase("SOME ")) {
         subqueryKeyword = "SOME";
         this.processExpression();
         subqueryNode = (Node)this.stack.pop();
      } else if (this.lexer.parseStringIgnoreCase("ALL ")) {
         subqueryKeyword = "ALL";
         this.processExpression();
         subqueryNode = (Node)this.stack.pop();
      } else if (this.lexer.parseStringIgnoreCase("ANY ")) {
         subqueryKeyword = "ANY";
         this.processExpression();
         subqueryNode = (Node)this.stack.pop();
      } else if (this.lexer.parseStringIgnoreCase("EXISTS ")) {
         subqueryKeyword = "EXISTS";
         this.processExpression();
         subqueryNode = (Node)this.stack.pop();
      }

      if (subqueryKeyword != null && subqueryNode != null) {
         Node subNode = new Node(NodeType.SUBQUERY, subqueryKeyword);
         subNode.appendChildNode(subqueryNode);
         this.stack.push(subNode);
      } else {
         if (!this.strictJPQL) {
            if (this.lexer.parseStringIgnoreCase("COUNT(*)")) {
               Node node = new Node(NodeType.INVOKE, "COUNTSTAR");
               this.stack.push(node);
               return;
            }

            if (this.lexer.parseStringIgnoreCase("CURRENT_DATE()")) {
               Node node = new Node(NodeType.INVOKE, "CURRENT_DATE");
               this.stack.push(node);
               return;
            }

            if (this.lexer.parseStringIgnoreCase("CURRENT_TIMESTAMP()")) {
               Node node = new Node(NodeType.INVOKE, "CURRENT_TIMESTAMP");
               this.stack.push(node);
               return;
            }

            if (this.lexer.parseStringIgnoreCase("CURRENT_TIME()")) {
               Node node = new Node(NodeType.INVOKE, "CURRENT_TIME");
               this.stack.push(node);
               return;
            }
         }

         if (this.lexer.parseStringIgnoreCase("CURRENT_DATE")) {
            Node node = new Node(NodeType.INVOKE, "CURRENT_DATE");
            this.stack.push(node);
         } else if (this.lexer.parseStringIgnoreCase("CURRENT_TIMESTAMP")) {
            Node node = new Node(NodeType.INVOKE, "CURRENT_TIMESTAMP");
            this.stack.push(node);
         } else if (this.lexer.parseStringIgnoreCase("CURRENT_TIME")) {
            Node node = new Node(NodeType.INVOKE, "CURRENT_TIME");
            this.stack.push(node);
         } else if (this.lexer.parseStringIgnoreCase("CASE ")) {
            this.processCaseExpression();
         } else if (this.lexer.parseStringIgnoreCase("DISTINCT ")) {
            Node distinctNode = new Node(NodeType.OPERATOR, "DISTINCT");
            this.processExpression();
            Node identifierNode = (Node)this.stack.pop();
            distinctNode.appendChildNode(identifierNode);
            this.stack.push(distinctNode);
         } else {
            if (!this.lexer.peekStringIgnoreCase("TREAT(")) {
               if (this.processKey()) {
                  return;
               }

               if (this.processValue()) {
                  return;
               }

               if (this.processEntry()) {
                  return;
               }

               if (this.processCreator() || this.processLiteral() || this.processMethod()) {
                  return;
               }
            }

            int sizeBeforeBraceProcessing = this.stack.size();
            boolean braceProcessing = false;
            if (this.lexer.parseChar('(')) {
               this.processExpression();
               if (!this.lexer.parseChar(')')) {
                  throw new QueryCompilerSyntaxException("expected ')'", this.lexer.getIndex(), this.lexer.getInput());
               }

               if (!this.lexer.parseChar('.')) {
                  return;
               }

               braceProcessing = true;
            }

            if (!this.processTreat() && !this.processMethod() && !this.processIdentifier()) {
               throw new QueryCompilerSyntaxException("Method/Identifier expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               int size = this.stack.size();
               if (braceProcessing) {
                  size = sizeBeforeBraceProcessing + 1;
               }

               while(this.lexer.parseChar('.')) {
                  if (!this.processMethod() && !this.processIdentifier()) {
                     throw new QueryCompilerSyntaxException("Identifier expected", this.lexer.getIndex(), this.lexer.getInput());
                  }
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

   private boolean processCreator() {
      if (!this.lexer.parseStringIgnoreCase("NEW ")) {
         return false;
      } else {
         int size = this.stack.size();
         if (!this.processMethod()) {
            if (!this.processIdentifier()) {
               throw new QueryCompilerSyntaxException("Identifier expected", this.lexer.getIndex(), this.lexer.getInput());
            }

            while(this.lexer.parseChar('.')) {
               if (!this.processMethod() && !this.processIdentifier()) {
                  throw new QueryCompilerSyntaxException("Identifier expected", this.lexer.getIndex(), this.lexer.getInput());
               }
            }
         }

         while(this.stack.size() - 1 > size) {
            Node top = (Node)this.stack.pop();
            Node peek = (Node)this.stack.peek();
            peek.insertChildNode(top);
         }

         Node node = (Node)this.stack.pop();
         Node newNode = new Node(NodeType.CREATOR);
         newNode.insertChildNode(node);
         this.stack.push(newNode);
         return true;
      }
   }

   protected boolean processEntry() {
      if (!this.lexer.parseString("ENTRY")) {
         return false;
      } else {
         this.lexer.skipWS();
         this.lexer.parseChar('(');
         Node invokeNode = new Node(NodeType.INVOKE, "mapEntry");
         this.processExpression();
         if (!this.lexer.parseChar(')')) {
            throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
         } else {
            Node primaryNode = (Node)this.stack.pop();

            Node primaryRootNode;
            for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
            }

            primaryNode.appendChildNode(invokeNode);
            this.stack.push(primaryRootNode);
            return true;
         }
      }
   }

   protected boolean processKey() {
      if (!this.lexer.parseString("KEY")) {
         return false;
      } else {
         this.lexer.skipWS();
         this.lexer.parseChar('(');
         Node invokeNode = new Node(NodeType.INVOKE, "mapKey");
         this.processExpression();
         if (!this.lexer.parseChar(')')) {
            throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
         } else {
            Node primaryNode = (Node)this.stack.pop();

            Node primaryRootNode;
            for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
            }

            primaryNode.appendChildNode(invokeNode);
            this.stack.push(primaryRootNode);
            int size = this.stack.size();

            while(this.lexer.parseChar('.')) {
               if (!this.processIdentifier()) {
                  throw new QueryCompilerSyntaxException("Identifier expected", this.lexer.getIndex(), this.lexer.getInput());
               }
            }

            Node top;
            if (size != this.stack.size()) {
               for(Node lastNode = invokeNode; this.stack.size() > size; lastNode = top) {
                  top = (Node)this.stack.pop();
                  lastNode.insertChildNode(top);
               }
            }

            return true;
         }
      }
   }

   protected boolean processValue() {
      if (!this.lexer.parseString("VALUE")) {
         return false;
      } else {
         this.lexer.skipWS();
         this.lexer.parseChar('(');
         Node invokeNode = new Node(NodeType.INVOKE, "mapValue");
         this.processExpression();
         if (!this.lexer.parseChar(')')) {
            throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
         } else {
            Node primaryNode = (Node)this.stack.pop();

            Node primaryRootNode;
            for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
            }

            primaryNode.appendChildNode(invokeNode);
            this.stack.push(primaryRootNode);
            int size = this.stack.size();

            while(this.lexer.parseChar('.')) {
               if (!this.processIdentifier()) {
                  throw new QueryCompilerSyntaxException("Identifier expected", this.lexer.getIndex(), this.lexer.getInput());
               }
            }

            Node top;
            if (size != this.stack.size()) {
               for(Node lastNode = invokeNode; this.stack.size() > size; lastNode = top) {
                  top = (Node)this.stack.pop();
                  lastNode.insertChildNode(top);
               }
            }

            return true;
         }
      }
   }

   private boolean processMethod() {
      String method = this.lexer.parseMethod();
      if (method != null) {
         this.lexer.skipWS();
         this.lexer.parseChar('(');
         if (method.equalsIgnoreCase("COUNT")) {
            method = "COUNT";
         } else if (method.equalsIgnoreCase("AVG")) {
            method = "AVG";
         } else if (method.equalsIgnoreCase("MIN")) {
            method = "MIN";
         } else if (method.equalsIgnoreCase("MAX")) {
            method = "MAX";
         } else if (method.equalsIgnoreCase("SUM")) {
            method = "SUM";
         } else if (method.equalsIgnoreCase("ABS")) {
            method = "ABS";
         } else if (method.equalsIgnoreCase("INDEX")) {
            method = "INDEX";
         } else if (method.equalsIgnoreCase("FUNCTION")) {
            method = "FUNCTION";
         }

         if (method.equalsIgnoreCase("Object")) {
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               return true;
            }
         } else if (method.equalsIgnoreCase("MOD")) {
            Node modNode = new Node(NodeType.OPERATOR, "%");
            this.processExpression();
            Node firstNode = (Node)this.stack.pop();
            if (!this.lexer.parseChar(',')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               this.processExpression();
               Node secondNode = (Node)this.stack.pop();
               modNode.appendChildNode(firstNode);
               modNode.appendChildNode(secondNode);
               this.stack.push(modNode);
               if (!this.lexer.parseChar(')')) {
                  throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
               } else {
                  return true;
               }
            }
         } else if (method.equalsIgnoreCase("TYPE")) {
            Node typeNode = new Node(NodeType.TYPE);
            this.processExpression();
            Node typePrimaryNode = (Node)this.stack.pop();
            typeNode.appendChildNode(typePrimaryNode);
            this.stack.push(typeNode);
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               return true;
            }
         } else if (method.equalsIgnoreCase("SUBSTRING")) {
            Node invokeNode = new Node(NodeType.INVOKE, "substring");
            this.processExpression();
            Node primaryNode = (Node)this.stack.pop();
            if (!this.lexer.parseChar(',')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               this.processExpression();
               Node arg1 = (Node)this.stack.pop();
               Node oneNode = new Node(NodeType.LITERAL, 1);
               Node arg1Node = new Node(NodeType.OPERATOR, "-");
               arg1Node.insertChildNode(arg1);
               arg1Node.appendChildNode(oneNode);
               if (this.lexer.parseChar(',')) {
                  this.processExpression();
                  Node arg2 = (Node)this.stack.pop();
                  Node arg2Node = new Node(NodeType.OPERATOR, "+");
                  arg2Node.appendChildNode(arg2);
                  arg2Node.appendChildNode(arg1Node);
                  if (!this.lexer.parseChar(')')) {
                     throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
                  } else {
                     primaryNode.appendChildNode(invokeNode);
                     invokeNode.addProperty(arg1Node);
                     invokeNode.addProperty(arg2Node);
                     this.stack.push(primaryNode);
                     return true;
                  }
               } else if (this.lexer.parseChar(')')) {
                  primaryNode.appendChildNode(invokeNode);
                  invokeNode.addProperty(arg1Node);
                  this.stack.push(primaryNode);
                  return true;
               } else {
                  throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
               }
            }
         } else if (method.equalsIgnoreCase("UPPER")) {
            Node invokeNode = new Node(NodeType.INVOKE, "toUpperCase");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("LOWER")) {
            Node invokeNode = new Node(NodeType.INVOKE, "toLowerCase");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("LENGTH")) {
            Node invokeNode = new Node(NodeType.INVOKE, "length");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("CONCAT")) {
            this.processExpression();

            Node currentNode;
            for(Node prevNode = (Node)this.stack.pop(); this.lexer.parseChar(','); currentNode = null) {
               this.processExpression();
               Node thisNode = (Node)this.stack.pop();
               currentNode = new Node(NodeType.OPERATOR, "+");
               currentNode.appendChildNode(prevNode);
               currentNode.appendChildNode(thisNode);
               if (this.lexer.parseChar(')')) {
                  this.stack.push(currentNode);
                  return true;
               }

               prevNode = currentNode;
            }

            throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
         } else if (method.equalsIgnoreCase("LOCATE")) {
            this.processExpression();
            Node searchNode = (Node)this.stack.pop();
            Node invokeNode = new Node(NodeType.INVOKE, "indexOf");
            invokeNode.addProperty(searchNode);
            if (!this.lexer.parseChar(',')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               this.processExpression();
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               Node oneNode = new Node(NodeType.LITERAL, 1);
               if (this.lexer.parseChar(',')) {
                  this.processExpression();
                  Node fromPosNode = (Node)this.stack.pop();
                  Node positionNode = new Node(NodeType.OPERATOR, "-");
                  positionNode.appendChildNode(fromPosNode);
                  positionNode.appendChildNode(oneNode);
                  invokeNode.addProperty(positionNode);
               }

               if (!this.lexer.parseChar(')')) {
                  throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
               } else {
                  Node locateNode = new Node(NodeType.OPERATOR, "+");
                  locateNode.appendChildNode(primaryRootNode);
                  locateNode.appendChildNode(oneNode);
                  this.stack.push(locateNode);
                  return true;
               }
            }
         } else if (method.equalsIgnoreCase("TRIM")) {
            String methodName = "trim";
            if (this.lexer.parseStringIgnoreCase("LEADING")) {
               methodName = "trimLeft";
            } else if (this.lexer.parseStringIgnoreCase("TRAILING")) {
               methodName = "trimRight";
            } else if (this.lexer.parseStringIgnoreCase("BOTH")) {
            }

            Node invokeNode = new Node(NodeType.INVOKE, methodName);
            this.processExpression();
            Node next = (Node)this.stack.pop();
            if (this.lexer.parseChar(')')) {
               Node primaryNode;
               for(primaryNode = next; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(next);
               return true;
            } else {
               if (next.getNodeType() == NodeType.LITERAL) {
                  Node trimCharNode = next;
                  if (this.lexer.parseStringIgnoreCase("FROM ")) {
                  }

                  this.processExpression();
                  next = (Node)this.stack.pop();
                  if (trimCharNode != null) {
                     invokeNode.addProperty(trimCharNode);
                  }
               } else if (next.getNodeType() == NodeType.IDENTIFIER) {
                  Object litValue = next.getNodeValue();
                  if (!(litValue instanceof String) || !((String)litValue).equals("FROM")) {
                     throw new QueryCompilerSyntaxException("Unexpected expression", this.lexer.getIndex(), this.lexer.getInput());
                  }

                  this.processExpression();
                  next = (Node)this.stack.pop();
               }

               if (!this.lexer.parseChar(')')) {
                  throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
               } else {
                  Node primaryNode;
                  for(primaryNode = next; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
                  }

                  primaryNode.appendChildNode(invokeNode);
                  this.stack.push(next);
                  return true;
               }
            }
         } else if (method.equalsIgnoreCase("SIZE")) {
            Node invokeNode = new Node(NodeType.INVOKE, "size");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("YEAR")) {
            Node invokeNode = new Node(NodeType.INVOKE, "getYear");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("MONTH")) {
            Node invokeNode = new Node(NodeType.INVOKE, "getMonth");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("DAY")) {
            Node invokeNode = new Node(NodeType.INVOKE, "getDay");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("HOUR")) {
            Node invokeNode = new Node(NodeType.INVOKE, "getHour");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("MINUTE")) {
            Node invokeNode = new Node(NodeType.INVOKE, "getMinute");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("SECOND")) {
            Node invokeNode = new Node(NodeType.INVOKE, "getSecond");
            this.processExpression();
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("',' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               Node primaryNode = (Node)this.stack.pop();

               Node primaryRootNode;
               for(primaryRootNode = primaryNode; primaryNode.getFirstChild() != null; primaryNode = primaryNode.getFirstChild()) {
               }

               primaryNode.appendChildNode(invokeNode);
               this.stack.push(primaryRootNode);
               return true;
            }
         } else if (method.equalsIgnoreCase("FUNCTION")) {
            this.processExpression();
            Node sqlFunctionNode = (Node)this.stack.pop();
            Node invokeNode = new Node(NodeType.INVOKE, "SQL_function");
            invokeNode.addProperty(sqlFunctionNode);
            if (this.lexer.parseChar(',')) {
               do {
                  this.processExpression();
                  invokeNode.addProperty((Node)this.stack.pop());
               } while(this.lexer.parseChar(','));
            }

            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               this.stack.push(invokeNode);
               return true;
            }
         } else {
            Node node = new Node(NodeType.INVOKE, method);
            if (!this.lexer.parseChar(')')) {
               do {
                  this.processExpression();
                  node.addProperty((Node)this.stack.pop());
               } while(this.lexer.parseChar(','));

               if (!this.lexer.parseChar(')')) {
                  throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
               }
            }

            this.stack.push(node);
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean processTreat() {
      if (this.lexer.parseString("TREAT(")) {
         this.processExpression();
         Node identifierNode = (Node)this.stack.pop();
         String typeName = this.lexer.parseIdentifier();
         if (typeName != null && typeName.equalsIgnoreCase("AS")) {
            this.processExpression();
            Node typeNode = (Node)this.stack.pop();
            typeName = typeNode.getNodeChildId();
            typeNode = new Node(NodeType.CAST, typeName);
            Node endNode = this.getLastDescendantNodeForNode(identifierNode);
            typeNode.setParent(endNode);
            endNode.appendChildNode(typeNode);
            if (!this.lexer.parseChar(')')) {
               throw new QueryCompilerSyntaxException("')' expected", this.lexer.getIndex(), this.lexer.getInput());
            } else {
               this.stack.push(identifierNode);
               return true;
            }
         } else {
            throw new QueryCompilerSyntaxException("TREAT should always be structured as 'TREAT(id AS typeName)'");
         }
      } else {
         return false;
      }
   }

   protected boolean processLiteral() {
      if (this.lexer.parseChar('{')) {
         StringBuilder jdbcLiteralStr = new StringBuilder("{");
         if (this.lexer.parseChar('d')) {
            jdbcLiteralStr.append("d ");
         } else if (this.lexer.parseString("ts")) {
            jdbcLiteralStr.append("ts ");
         } else {
            if (!this.lexer.parseChar('t')) {
               throw new QueryCompilerSyntaxException("d, ts or t expected after { (JDBC escape syntax)", this.lexer.getIndex(), this.lexer.getInput());
            }

            jdbcLiteralStr.append("t ");
         }

         if (this.lexer.nextIsSingleQuote()) {
            String datetimeLit = this.lexer.parseStringLiteral();
            jdbcLiteralStr.append("'").append(datetimeLit).append("'");
            if (this.lexer.parseChar('}')) {
               jdbcLiteralStr.append('}');
               this.stack.push(new Node(NodeType.LITERAL, jdbcLiteralStr.toString()));
               return true;
            } else {
               throw new QueryCompilerSyntaxException("} expected in JDBC escape syntax", this.lexer.getIndex(), this.lexer.getInput());
            }
         } else {
            throw new QueryCompilerSyntaxException("'...' expected in JDBC escape syntax", this.lexer.getIndex(), this.lexer.getInput());
         }
      } else {
         Object litValue = null;
         boolean single_quote_next = this.lexer.nextIsSingleQuote();
         String sLiteral;
         if ((sLiteral = this.lexer.parseStringLiteral()) != null) {
            if (sLiteral.length() == 1 && single_quote_next) {
               litValue = sLiteral.charAt(0);
            } else {
               litValue = sLiteral;
            }
         } else {
            BigDecimal fLiteral;
            if ((fLiteral = this.lexer.parseFloatingPointLiteral()) != null) {
               litValue = fLiteral;
            } else {
               BigInteger iLiteral;
               if ((iLiteral = this.lexer.parseIntegerLiteral()) != null) {
                  String longStr = "" + iLiteral.longValue();
                  if (longStr.length() < iLiteral.toString().length()) {
                     litValue = iLiteral;
                  } else {
                     litValue = iLiteral.longValue();
                  }
               } else {
                  Boolean bLiteral;
                  if ((bLiteral = this.lexer.parseBooleanLiteralIgnoreCase()) != null) {
                     litValue = bLiteral;
                  } else if (!this.lexer.parseNullLiteralIgnoreCase()) {
                     return false;
                  }
               }
            }
         }

         this.stack.push(new Node(NodeType.LITERAL, litValue));
         return true;
      }
   }

   private boolean processIdentifier() {
      String id = this.lexer.parseIdentifier();
      if (id != null && id.length() != 0) {
         char first = id.charAt(0);
         if (first == '?') {
            String paramName = id.substring(1);
            Node node = new ParameterNode(NodeType.PARAMETER, paramName, this.parameterPosition);
            ++this.parameterPosition;
            this.stack.push(node);
            return true;
         } else if (first == ':') {
            Node node = new ParameterNode(NodeType.PARAMETER, id.substring(1), this.parameterPosition);
            ++this.parameterPosition;
            this.stack.push(node);
            return true;
         } else {
            Node node = new Node(NodeType.IDENTIFIER, id);
            this.stack.push(node);
            return true;
         }
      } else {
         return false;
      }
   }
}
