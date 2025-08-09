package org.datanucleus.query.expression;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.query.compiler.Node;
import org.datanucleus.query.compiler.NodeType;
import org.datanucleus.query.compiler.ParameterNode;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.store.query.QueryCompilerSyntaxException;
import org.datanucleus.util.NucleusLogger;

public class ExpressionCompiler {
   SymbolTable symtbl;
   Map aliasByPrefix = null;

   public void setMethodAliases(Map aliasByPrefix) {
      this.aliasByPrefix = aliasByPrefix;
   }

   public void setSymbolTable(SymbolTable symtbl) {
      this.symtbl = symtbl;
   }

   public Expression compileOrderExpression(Node node) {
      if (this.isOperator(node, "order")) {
         Node nameNode = node.getFirstChild();
         if (node.getChildNodes().size() > 1) {
            String node1Value = (String)node.getNextChild().getNodeValue();
            String node2Value = node.hasNextChild() ? (String)node.getNextChild().getNodeValue() : null;
            String ordering = null;
            String nullOrdering = null;
            if (!node1Value.equalsIgnoreCase("ascending") && !node1Value.equalsIgnoreCase("descending")) {
               nullOrdering = node1Value;
            } else {
               ordering = node1Value;
               if (node2Value != null) {
                  nullOrdering = node2Value;
               }
            }

            return new OrderExpression(this.compileExpression(nameNode), ordering, nullOrdering);
         }

         if (node.getChildNodes().size() == 1) {
            return new OrderExpression(this.compileExpression(nameNode));
         }
      }

      return this.compileExpression(node.getFirstChild());
   }

   public Expression compileFromExpression(Node node, boolean classIsExpression) {
      if (node.getNodeType() == NodeType.CLASS) {
         Node aliasNode = node.getFirstChild();
         ClassExpression clsExpr = new ClassExpression((String)aliasNode.getNodeValue());
         if (classIsExpression) {
            clsExpr.setCandidateExpression((String)node.getNodeValue());
         }

         JoinExpression currentJoinExpr = null;

         for(Node childNode : node.getChildNodes()) {
            if (childNode.getNodeType() == NodeType.OPERATOR) {
               String joinType = (String)childNode.getNodeValue();
               JoinExpression.JoinType joinTypeId = JoinExpression.JoinType.JOIN_INNER;
               if (joinType.equals("JOIN_INNER_FETCH")) {
                  joinTypeId = JoinExpression.JoinType.JOIN_INNER_FETCH;
               } else if (joinType.equals("JOIN_OUTER_FETCH")) {
                  joinTypeId = JoinExpression.JoinType.JOIN_LEFT_OUTER_FETCH;
               } else if (joinType.equals("JOIN_OUTER")) {
                  joinTypeId = JoinExpression.JoinType.JOIN_LEFT_OUTER;
               } else if (joinType.equals("JOIN_OUTER_FETCH_RIGHT")) {
                  joinTypeId = JoinExpression.JoinType.JOIN_RIGHT_OUTER_FETCH;
               } else if (joinType.equals("JOIN_OUTER_RIGHT")) {
                  joinTypeId = JoinExpression.JoinType.JOIN_RIGHT_OUTER;
               }

               Node joinedNode = childNode.getFirstChild();
               Node joinedAliasNode = childNode.getNextChild();
               Expression joinedExpr = this.compilePrimaryExpression(joinedNode);
               Expression onExpr = null;
               if (childNode.hasNextChild()) {
                  Node onNode = childNode.getNextChild();
                  onExpr = this.compileExpression(onNode);
               }

               JoinExpression joinExpr = new JoinExpression(joinedExpr, (String)joinedAliasNode.getNodeValue(), joinTypeId);
               if (currentJoinExpr != null) {
                  currentJoinExpr.setJoinExpression(joinExpr);
               } else {
                  clsExpr.setJoinExpression(joinExpr);
               }

               if (onExpr != null) {
                  joinExpr.setOnExpression(onExpr);
               }

               currentJoinExpr = joinExpr;
            }
         }

         return clsExpr;
      } else {
         return null;
      }
   }

   public Expression compileExpression(Node node) {
      return this.compileOrAndExpression(node);
   }

   private Expression compileOrAndExpression(Node node) {
      if (this.isOperator(node, "||")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_OR, right);
      } else if (this.isOperator(node, "&&")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_AND, right);
      } else if (this.isOperator(node, "|")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_BIT_OR, right);
      } else if (this.isOperator(node, "^")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_BIT_XOR, right);
      } else if (this.isOperator(node, "&")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_BIT_AND, right);
      } else {
         return this.compileRelationalExpression(node);
      }
   }

   private Expression compileRelationalExpression(Node node) {
      if (this.isOperator(node, "==")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_EQ, right);
      } else if (this.isOperator(node, "!=")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_NOTEQ, right);
      } else if (this.isOperator(node, "LIKE")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_LIKE, right);
      } else if (this.isOperator(node, "<=")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_LTEQ, right);
      } else if (this.isOperator(node, ">=")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_GTEQ, right);
      } else if (this.isOperator(node, "<")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_LT, right);
      } else if (this.isOperator(node, ">")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_GT, right);
      } else if (this.isOperator(node, "instanceof")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_IS, right);
      } else if (this.isOperator(node, "IN")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_IN, right);
      } else if (this.isOperator(node, "NOT IN")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_NOTIN, right);
      } else {
         return this.compileAdditiveMultiplicativeExpression(node);
      }
   }

   private Expression compileAdditiveMultiplicativeExpression(Node node) {
      if (this.isOperator(node, "+")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_ADD, right);
      } else if (this.isOperator(node, "-") && node.getChildNodes().size() > 1) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_SUB, right);
      } else if (this.isOperator(node, "*")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_MUL, right);
      } else if (this.isOperator(node, "/")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_DIV, right);
      } else if (this.isOperator(node, "%")) {
         Expression left = this.compileExpression(node.getFirstChild());
         Expression right = this.compileExpression(node.getNextChild());
         return new DyadicExpression(left, Expression.OP_MOD, right);
      } else {
         return this.compileUnaryExpression(node);
      }
   }

   private Expression compileUnaryExpression(Node node) {
      if (this.isOperator(node, "-") && node.getChildNodes().size() == 1) {
         Expression left = this.compileExpression(node.getFirstChild());
         if (left instanceof Literal) {
            ((Literal)left).negate();
            return left;
         } else {
            return new DyadicExpression(Expression.OP_NEG, left);
         }
      } else if (this.isOperator(node, "~")) {
         Expression left = this.compileExpression(node.getFirstChild());
         return new DyadicExpression(Expression.OP_COM, left);
      } else if (this.isOperator(node, "!")) {
         Expression left = this.compileExpression(node.getFirstChild());
         if (left instanceof DyadicExpression && left.getOperator() == Expression.OP_IS) {
            DyadicExpression leftExpr = (DyadicExpression)left;
            return new DyadicExpression(leftExpr.getLeft(), Expression.OP_ISNOT, leftExpr.getRight());
         } else {
            return new DyadicExpression(Expression.OP_NOT, left);
         }
      } else if (this.isOperator(node, "DISTINCT")) {
         Expression left = this.compileExpression(node.getFirstChild());
         return new DyadicExpression(Expression.OP_DISTINCT, left);
      } else {
         return this.compilePrimaryExpression(node);
      }
   }

   private Expression compilePrimaryExpression(Node node) {
      if (node.getNodeType() == NodeType.PRIMARY) {
         Node currentNode = node.getFirstChild();
         Node invokeNode = node.getNextChild();
         if (invokeNode.getNodeType() != NodeType.INVOKE) {
            throw new QueryCompilerSyntaxException("Dont support compilation of " + node);
         } else {
            Expression currentExpr = this.compileExpression(currentNode);
            String methodName = (String)invokeNode.getNodeValue();
            List parameterExprs = this.getExpressionsForPropertiesOfNode(invokeNode);
            Expression invokeExpr = new InvokeExpression(currentExpr, methodName, parameterExprs);
            return invokeExpr;
         }
      } else if (node.getNodeType() == NodeType.IDENTIFIER) {
         Node currentNode = node;
         List tupple = new ArrayList();
         Expression currentExpr = null;

         while(currentNode != null) {
            tupple.add(currentNode.getNodeValue());
            if (currentNode.getNodeType() == NodeType.INVOKE) {
               if (currentExpr == null && tupple.size() > 1) {
                  String first = (String)tupple.get(0);
                  Symbol firstSym = this.symtbl.getSymbol(first);
                  if (firstSym != null) {
                     if (firstSym.getType() == 1) {
                        currentExpr = new ParameterExpression(first, -1);
                        if (tupple.size() > 2) {
                           currentExpr = new PrimaryExpression(currentExpr, tupple.subList(1, tupple.size() - 1));
                        }
                     } else if (firstSym.getType() == 2) {
                        currentExpr = new VariableExpression(first);
                        if (tupple.size() > 2) {
                           currentExpr = new PrimaryExpression(currentExpr, tupple.subList(1, tupple.size() - 1));
                        }
                     }
                  }

                  if (currentExpr == null) {
                     currentExpr = new PrimaryExpression(tupple.subList(0, tupple.size() - 1));
                  }
               } else if (currentExpr != null && tupple.size() > 1) {
                  currentExpr = new PrimaryExpression(currentExpr, tupple.subList(0, tupple.size() - 1));
               }

               String methodName = (String)tupple.get(tupple.size() - 1);
               if (currentExpr instanceof PrimaryExpression) {
                  String id = ((PrimaryExpression)currentExpr).getId();
                  if (this.aliasByPrefix != null && this.aliasByPrefix.containsKey(id)) {
                     String alias = (String)this.aliasByPrefix.get(id);
                     methodName = alias + "." + methodName;
                     currentExpr = null;
                  }
               }

               List parameterExprs = this.getExpressionsForPropertiesOfNode(currentNode);
               currentExpr = new InvokeExpression(currentExpr, methodName, parameterExprs);
               currentNode = currentNode.getFirstChild();
               tupple = new ArrayList();
            } else if (currentNode.getNodeType() == NodeType.CAST) {
               if (currentExpr == null && tupple.size() > 1) {
                  currentExpr = new PrimaryExpression(tupple.subList(0, tupple.size() - 1));
                  PrimaryExpression primExpr = (PrimaryExpression)currentExpr;
                  if (primExpr.tuples.size() == 1) {
                     Symbol sym = this.symtbl.getSymbol(primExpr.getId());
                     if (sym != null) {
                        if (sym.getType() == 1) {
                           currentExpr = new ParameterExpression(primExpr.getId(), -1);
                        } else if (sym.getType() == 2) {
                           currentExpr = new VariableExpression(primExpr.getId());
                        }
                     }
                  }
               }

               String className = (String)tupple.get(tupple.size() - 1);
               currentExpr = new DyadicExpression(currentExpr, Expression.OP_CAST, new Literal(className));
               currentNode = currentNode.getFirstChild();
               tupple = new ArrayList();
            } else {
               currentNode = currentNode.getFirstChild();
            }
         }

         if (currentExpr != null && tupple.size() > 0) {
            currentExpr = new PrimaryExpression(currentExpr, tupple);
         }

         if (currentExpr == null) {
            String first = (String)tupple.get(0);
            Symbol firstSym = this.symtbl.getSymbol(first);
            if (firstSym != null) {
               if (firstSym.getType() == 1) {
                  ParameterExpression paramExpr = new ParameterExpression(first, -1);
                  if (tupple.size() > 1) {
                     currentExpr = new PrimaryExpression(paramExpr, tupple.subList(1, tupple.size()));
                  } else {
                     currentExpr = paramExpr;
                  }
               } else if (firstSym.getType() == 2) {
                  VariableExpression varExpr = new VariableExpression(first);
                  if (tupple.size() > 1) {
                     currentExpr = new PrimaryExpression(varExpr, tupple.subList(1, tupple.size()));
                  } else {
                     currentExpr = varExpr;
                  }
               } else {
                  currentExpr = new PrimaryExpression(tupple);
               }
            } else {
               currentExpr = new PrimaryExpression(tupple);
            }
         }

         return currentExpr;
      } else if (node.getNodeType() == NodeType.PARAMETER) {
         Object val = node.getNodeValue();
         Expression currentExpr = null;
         if (val instanceof Integer) {
            currentExpr = new ParameterExpression("" + node.getNodeValue(), ((ParameterNode)node).getPosition());
         } else {
            currentExpr = new ParameterExpression((String)node.getNodeValue(), ((ParameterNode)node).getPosition());
         }

         for(Node childNode = node.getFirstChild(); childNode != null; childNode = childNode.getFirstChild()) {
            if (childNode.getNodeType() == NodeType.INVOKE) {
               String methodName = (String)childNode.getNodeValue();
               List parameterExprs = this.getExpressionsForPropertiesOfNode(childNode);
               currentExpr = new InvokeExpression(currentExpr, methodName, parameterExprs);
            } else {
               if (childNode.getNodeType() != NodeType.IDENTIFIER) {
                  throw new QueryCompilerSyntaxException("Dont support compilation of " + node);
               }

               String identifier = childNode.getNodeId();
               List tuples = new ArrayList();
               tuples.add(identifier);
               boolean moreIdentifierNodes = true;

               while(moreIdentifierNodes) {
                  Node currentNode = childNode;
                  childNode = childNode.getFirstChild();
                  if (childNode != null && childNode.getNodeType() == NodeType.IDENTIFIER) {
                     tuples.add(childNode.getNodeId());
                  } else {
                     moreIdentifierNodes = false;
                     childNode = currentNode;
                  }
               }

               currentExpr = new PrimaryExpression(currentExpr, tuples);
            }
         }

         return currentExpr;
      } else if (node.getNodeType() == NodeType.INVOKE) {
         Node currentNode = node;
         List tupple = new ArrayList();
         Expression currentExpr = null;

         while(currentNode != null) {
            tupple.add(currentNode.getNodeValue());
            if (currentNode.getNodeType() == NodeType.INVOKE) {
               String methodName = (String)tupple.get(tupple.size() - 1);
               List parameterExprs = this.getExpressionsForPropertiesOfNode(currentNode);
               currentExpr = new InvokeExpression(currentExpr, methodName, parameterExprs);
               currentNode = currentNode.getFirstChild();
               if (currentNode != null) {
                  tupple = new ArrayList();
                  tupple.add(currentExpr);
               }
            } else {
               currentNode = currentNode.getFirstChild();
            }
         }

         return currentExpr;
      } else if (node.getNodeType() == NodeType.CREATOR) {
         Node currentNode = node.getFirstChild();
         List tupple = new ArrayList();

         boolean method;
         for(method = false; currentNode != null; currentNode = currentNode.getFirstChild()) {
            tupple.add(currentNode.getNodeValue());
            if (currentNode.getNodeType() == NodeType.INVOKE) {
               method = true;
               break;
            }
         }

         List parameterExprs = null;
         Object identifier;
         if (method) {
            identifier = this.getExpressionsForPropertiesOfNode(currentNode);
         } else {
            identifier = new ArrayList();
         }

         return new CreatorExpression(tupple, (List)identifier);
      } else if (node.getNodeType() == NodeType.LITERAL) {
         Node currentNode = node;
         List tupple = new ArrayList();
         Expression currentExpr = null;

         while(currentNode != null) {
            tupple.add(currentNode.getNodeValue());
            if (currentNode.getNodeType() == NodeType.INVOKE) {
               if (currentExpr == null && tupple.size() > 1) {
                  currentExpr = new Literal(node.getNodeValue());
               }

               String methodName = (String)tupple.get(tupple.size() - 1);
               List parameterExprs = this.getExpressionsForPropertiesOfNode(currentNode);
               currentExpr = new InvokeExpression(currentExpr, methodName, parameterExprs);
               currentNode = currentNode.getFirstChild();
               tupple = new ArrayList();
            } else {
               currentNode = currentNode.getFirstChild();
            }
         }

         if (currentExpr == null) {
            currentExpr = new Literal(node.getNodeValue());
         }

         return currentExpr;
      } else if (node.getNodeType() != NodeType.ARRAY) {
         if (node.getNodeType() == NodeType.SUBQUERY) {
            List children = node.getChildNodes();
            if (children.size() != 1) {
               throw new QueryCompilerSyntaxException("Invalid number of children for SUBQUERY node : " + node);
            } else {
               Node varNode = (Node)children.get(0);
               VariableExpression subqueryExpr = new VariableExpression(varNode.getNodeId());
               Expression currentExpr = new SubqueryExpression((String)node.getNodeValue(), subqueryExpr);
               return currentExpr;
            }
         } else if (node.getNodeType() == NodeType.CASE) {
            List<Node> children = node.getChildNodes();
            if (children.size() % 2 == 0) {
               throw new QueryCompilerSyntaxException("Invalid number of children for CASE node (should be odd) : " + node);
            } else {
               Node elseNode = (Node)children.get(children.size() - 1);
               Expression elseExpr = this.compileExpression(elseNode);
               CaseExpression caseExpr = new CaseExpression();
               Iterator<Node> childIter = children.iterator();

               while(childIter.hasNext()) {
                  Node whenNode = (Node)childIter.next();
                  if (childIter.hasNext()) {
                     Node actionNode = (Node)childIter.next();
                     Expression whenExpr = this.compileExpression(whenNode);
                     Expression actionExpr = this.compileExpression(actionNode);
                     caseExpr.addCondition(whenExpr, actionExpr);
                  }
               }

               caseExpr.setElseExpression(elseExpr);
               return caseExpr;
            }
         } else {
            NucleusLogger.QUERY.warn("ExpressionCompiler.compilePrimary " + node + " ignored by ExpressionCompiler");
            return null;
         }
      } else {
         List<Node> arrayElements = (List)node.getNodeValue();
         boolean literal = true;
         Class type = null;

         for(Node element : arrayElements) {
            if (type == null) {
               type = element.getNodeValue().getClass();
            }

            if (element.getNodeType() == NodeType.IDENTIFIER) {
               literal = false;
               break;
            }
         }

         Expression currentExpr = null;
         if (literal) {
            Object array = Array.newInstance(type, arrayElements.size());
            Iterator firstSym = arrayElements.iterator();
            int index = 0;

            while(firstSym.hasNext()) {
               Node element = (Node)firstSym.next();
               Array.set(array, index++, element.getNodeValue());
            }

            currentExpr = new Literal(array);
         } else {
            Expression[] arrayElementExprs = new Expression[arrayElements.size()];

            for(int i = 0; i < arrayElementExprs.length; ++i) {
               arrayElementExprs[i] = this.compilePrimaryExpression((Node)arrayElements.get(i));
            }

            currentExpr = new ArrayExpression(arrayElementExprs);
         }

         Node currentNode = node.getFirstChild();
         List tupple = new ArrayList();

         while(currentNode != null) {
            tupple.add(currentNode.getNodeValue());
            if (currentNode.getNodeType() == NodeType.INVOKE) {
               if (tupple.size() > 1) {
                  currentExpr = new Literal(node.getNodeValue());
               }

               String methodName = (String)tupple.get(tupple.size() - 1);
               List parameterExprs = this.getExpressionsForPropertiesOfNode(currentNode);
               currentExpr = new InvokeExpression(currentExpr, methodName, parameterExprs);
               currentNode = currentNode.getFirstChild();
               tupple = new ArrayList();
            } else {
               currentNode = currentNode.getFirstChild();
            }
         }

         return currentExpr;
      }
   }

   private List getExpressionsForPropertiesOfNode(Node node) {
      if (!node.hasProperties()) {
         return Collections.EMPTY_LIST;
      } else {
         List<Expression> parameterExprs = new ArrayList();
         List propNodes = node.getProperties();

         for(int i = 0; i < propNodes.size(); ++i) {
            parameterExprs.add(this.compileExpression((Node)propNodes.get(i)));
         }

         return parameterExprs;
      }
   }

   private boolean isOperator(Node node, String operator) {
      return node.getNodeType() == NodeType.OPERATOR && node.getNodeValue().equals(operator);
   }
}
