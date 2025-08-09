package org.apache.hadoop.hive.ql.io.sarg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.NoDynamicValuesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SearchArgumentImpl implements SearchArgument {
   private static final Logger LOG = LoggerFactory.getLogger(SearchArgumentImpl.class);
   private final List leaves;
   private final ExpressionTree normalizedExpression;
   private final ExpressionTree compactExpression;

   SearchArgumentImpl(ExpressionTree normalizedExpression, ExpressionTree compactExpression, List leaves) {
      this.normalizedExpression = normalizedExpression;
      this.compactExpression = compactExpression;
      this.leaves = leaves;
   }

   SearchArgumentImpl() {
      this.leaves = null;
      this.normalizedExpression = null;
      this.compactExpression = null;
   }

   public List getLeaves() {
      return this.leaves;
   }

   public SearchArgument.TruthValue evaluate(SearchArgument.TruthValue[] leaves) {
      return this.normalizedExpression == null ? SearchArgument.TruthValue.YES : this.normalizedExpression.evaluate(leaves);
   }

   public ExpressionTree getExpression() {
      return this.normalizedExpression;
   }

   public ExpressionTree getCompactExpression() {
      return this.compactExpression;
   }

   public String toString() {
      return this.normalizedExpression.toString();
   }

   public String toOldString() {
      StringBuilder buffer = new StringBuilder();

      for(int i = 0; this.leaves != null && i < this.leaves.size(); ++i) {
         buffer.append("leaf-");
         buffer.append(i);
         buffer.append(" = ");
         buffer.append(((PredicateLeaf)this.leaves.get(i)).toString());
         buffer.append(", ");
      }

      buffer.append("expr = ");
      buffer.append(this.normalizedExpression.toOldString());
      return buffer.toString();
   }

   public static final class PredicateLeafImpl implements PredicateLeaf {
      private final PredicateLeaf.Operator operator;
      private final PredicateLeaf.Type type;
      private String columnName;
      private final Object literal;
      private final List literalList;
      private int id;

      PredicateLeafImpl() {
         this.operator = null;
         this.type = null;
         this.columnName = null;
         this.literal = null;
         this.literalList = null;
         this.id = -1;
      }

      public PredicateLeafImpl(PredicateLeaf.Operator operator, PredicateLeaf.Type type, String columnName, Object literal, List literalList) {
         this(operator, type, columnName, literal, literalList, (Configuration)null);
      }

      public PredicateLeafImpl(PredicateLeaf.Operator operator, PredicateLeaf.Type type, String columnName, Object literal, List literalList, Configuration conf) {
         this.operator = operator;
         this.type = type;
         this.columnName = columnName;
         this.literal = literal;
         this.id = -1;
         this.checkLiteralType(literal, type, conf);
         this.literalList = literalList;
         if (literalList != null) {
            for(Object lit : literalList) {
               this.checkLiteralType(lit, type, conf);
            }
         }

      }

      public PredicateLeaf.Operator getOperator() {
         return this.operator;
      }

      public PredicateLeaf.Type getType() {
         return this.type;
      }

      public String getColumnName() {
         return this.columnName;
      }

      public Object getLiteral() {
         return this.literal instanceof LiteralDelegate ? ((LiteralDelegate)this.literal).getLiteral() : this.literal;
      }

      public List getLiteralList() {
         if (this.literalList != null && this.literalList.size() > 0 && this.literalList.get(0) instanceof LiteralDelegate) {
            List<Object> newLiteraList = new ArrayList();

            try {
               for(Object litertalObj : this.literalList) {
                  Object literal = ((LiteralDelegate)litertalObj).getLiteral();
                  if (literal != null) {
                     newLiteraList.add(literal);
                  }
               }

               return newLiteraList;
            } catch (NoDynamicValuesException err) {
               SearchArgumentImpl.LOG.debug("Error while retrieving literalList, returning null", err);
               return Collections.emptyList();
            }
         } else {
            return this.literalList;
         }
      }

      public int getId() {
         return this.id;
      }

      public void setId(int newId) {
         this.id = newId;
      }

      public String toString() {
         StringBuilder buffer = new StringBuilder();
         buffer.append('(');
         buffer.append(this.operator);
         buffer.append(' ');
         buffer.append(this.columnName);
         if (this.literal != null) {
            buffer.append(' ');
            buffer.append(this.literal);
         } else if (this.literalList != null) {
            for(Object lit : this.literalList) {
               buffer.append(' ');
               buffer.append(lit == null ? "null" : lit.toString());
            }
         }

         buffer.append(')');
         return buffer.toString();
      }

      private static boolean isEqual(Object left, Object right) {
         return left == right || left != null && right != null && left.equals(right);
      }

      public boolean equals(Object other) {
         if (other != null && other.getClass() == this.getClass()) {
            if (other == this) {
               return true;
            } else {
               PredicateLeafImpl o = (PredicateLeafImpl)other;
               return this.operator == o.operator && this.type == o.type && this.columnName.equals(o.columnName) && isEqual(this.literal, o.literal) && isEqual(this.literalList, o.literalList);
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.operator.hashCode() + this.type.hashCode() * 17 + this.columnName.hashCode() * 3 * 17 + (this.literal == null ? 0 : this.literal.hashCode()) * 101 * 3 * 17 + (this.literalList == null ? 0 : this.literalList.hashCode()) * 103 * 101 * 3 * 17;
      }

      public static void setColumnName(PredicateLeaf leaf, String newName) {
         assert leaf instanceof PredicateLeafImpl;

         ((PredicateLeafImpl)leaf).columnName = newName;
      }

      protected void checkLiteralType(Object literal, PredicateLeaf.Type type, Configuration conf) {
         if (literal != null) {
            if (literal instanceof LiteralDelegate) {
               ((LiteralDelegate)literal).setConf(conf);
            } else if (literal.getClass() != type.getValueClass()) {
               throw new IllegalArgumentException("Wrong value class " + literal.getClass().getName() + " for " + type + "." + this.operator + " leaf");
            }

         }
      }
   }

   static class BuilderImpl implements SearchArgument.Builder {
      Configuration conf;
      private static final int CNF_COMBINATIONS_THRESHOLD = 256;
      private final Deque currentTree = new ArrayDeque();
      private final Map leaves = new HashMap();
      private final ExpressionTree root;

      public BuilderImpl(Configuration conf) {
         this.root = new ExpressionTree(ExpressionTree.Operator.AND, new ExpressionTree[0]);
         this.currentTree.add(this.root);
         this.conf = conf;
      }

      public SearchArgument.Builder startOr() {
         ExpressionTree node = new ExpressionTree(ExpressionTree.Operator.OR, new ExpressionTree[0]);
         ((ExpressionTree)this.currentTree.getFirst()).getChildren().add(node);
         this.currentTree.addFirst(node);
         return this;
      }

      public SearchArgument.Builder startAnd() {
         ExpressionTree node = new ExpressionTree(ExpressionTree.Operator.AND, new ExpressionTree[0]);
         ((ExpressionTree)this.currentTree.getFirst()).getChildren().add(node);
         this.currentTree.addFirst(node);
         return this;
      }

      public SearchArgument.Builder startNot() {
         ExpressionTree node = new ExpressionTree(ExpressionTree.Operator.NOT, new ExpressionTree[0]);
         ((ExpressionTree)this.currentTree.getFirst()).getChildren().add(node);
         this.currentTree.addFirst(node);
         return this;
      }

      public SearchArgument.Builder end() {
         ExpressionTree current = (ExpressionTree)this.currentTree.removeFirst();
         if (current.getChildren().size() == 0) {
            throw new IllegalArgumentException("Can't create expression " + this.root + " with no children.");
         } else if (current.getOperator() == ExpressionTree.Operator.NOT && current.getChildren().size() != 1) {
            throw new IllegalArgumentException("Can't create not expression " + current + " with more than 1 child.");
         } else {
            return this;
         }
      }

      private PredicateLeaf addLeaf(PredicateLeaf leaf) {
         PredicateLeaf result = (PredicateLeaf)this.leaves.get(leaf);
         if (result == null) {
            this.leaves.put(leaf, leaf);
            return leaf;
         } else {
            return result;
         }
      }

      public SearchArgument.Builder lessThan(String column, PredicateLeaf.Type type, Object literal) {
         ExpressionTree parent = (ExpressionTree)this.currentTree.getFirst();
         if (column != null && literal != null) {
            PredicateLeafImpl leaf = new PredicateLeafImpl(PredicateLeaf.Operator.LESS_THAN, type, column, literal, (List)null, this.conf);
            parent.getChildren().add(new ExpressionTree(this.addLeaf(leaf)));
         } else {
            parent.getChildren().add(new ExpressionTree(SearchArgument.TruthValue.YES_NO_NULL));
         }

         return this;
      }

      public SearchArgument.Builder lessThanEquals(String column, PredicateLeaf.Type type, Object literal) {
         ExpressionTree parent = (ExpressionTree)this.currentTree.getFirst();
         if (column != null && literal != null) {
            PredicateLeafImpl leaf = new PredicateLeafImpl(PredicateLeaf.Operator.LESS_THAN_EQUALS, type, column, literal, (List)null, this.conf);
            parent.getChildren().add(new ExpressionTree(this.addLeaf(leaf)));
         } else {
            parent.getChildren().add(new ExpressionTree(SearchArgument.TruthValue.YES_NO_NULL));
         }

         return this;
      }

      public SearchArgument.Builder equals(String column, PredicateLeaf.Type type, Object literal) {
         ExpressionTree parent = (ExpressionTree)this.currentTree.getFirst();
         if (column != null && literal != null) {
            PredicateLeafImpl leaf = new PredicateLeafImpl(PredicateLeaf.Operator.EQUALS, type, column, literal, (List)null, this.conf);
            parent.getChildren().add(new ExpressionTree(this.addLeaf(leaf)));
         } else {
            parent.getChildren().add(new ExpressionTree(SearchArgument.TruthValue.YES_NO_NULL));
         }

         return this;
      }

      public SearchArgument.Builder nullSafeEquals(String column, PredicateLeaf.Type type, Object literal) {
         ExpressionTree parent = (ExpressionTree)this.currentTree.getFirst();
         if (column != null && literal != null) {
            PredicateLeafImpl leaf = new PredicateLeafImpl(PredicateLeaf.Operator.NULL_SAFE_EQUALS, type, column, literal, (List)null, this.conf);
            parent.getChildren().add(new ExpressionTree(this.addLeaf(leaf)));
         } else {
            parent.getChildren().add(new ExpressionTree(SearchArgument.TruthValue.YES_NO_NULL));
         }

         return this;
      }

      public SearchArgument.Builder in(String column, PredicateLeaf.Type type, Object... literal) {
         ExpressionTree parent = (ExpressionTree)this.currentTree.getFirst();
         if (column != null && literal != null) {
            if (literal.length == 0) {
               throw new IllegalArgumentException("Can't create in expression with no arguments");
            }

            List<Object> argList = new ArrayList(Arrays.asList(literal));
            PredicateLeafImpl leaf = new PredicateLeafImpl(PredicateLeaf.Operator.IN, type, column, (Object)null, argList, this.conf);
            parent.getChildren().add(new ExpressionTree(this.addLeaf(leaf)));
         } else {
            parent.getChildren().add(new ExpressionTree(SearchArgument.TruthValue.YES_NO_NULL));
         }

         return this;
      }

      public SearchArgument.Builder isNull(String column, PredicateLeaf.Type type) {
         ExpressionTree parent = (ExpressionTree)this.currentTree.getFirst();
         if (column == null) {
            parent.getChildren().add(new ExpressionTree(SearchArgument.TruthValue.YES_NO_NULL));
         } else {
            PredicateLeafImpl leaf = new PredicateLeafImpl(PredicateLeaf.Operator.IS_NULL, type, column, (Object)null, (List)null, this.conf);
            parent.getChildren().add(new ExpressionTree(this.addLeaf(leaf)));
         }

         return this;
      }

      public SearchArgument.Builder between(String column, PredicateLeaf.Type type, Object lower, Object upper) {
         ExpressionTree parent = (ExpressionTree)this.currentTree.getFirst();
         if (column != null && lower != null && upper != null) {
            List<Object> argList = new ArrayList();
            argList.add(lower);
            argList.add(upper);
            PredicateLeafImpl leaf = new PredicateLeafImpl(PredicateLeaf.Operator.BETWEEN, type, column, (Object)null, argList, this.conf);
            parent.getChildren().add(new ExpressionTree(this.addLeaf(leaf)));
         } else {
            parent.getChildren().add(new ExpressionTree(SearchArgument.TruthValue.YES_NO_NULL));
         }

         return this;
      }

      public SearchArgument.Builder literal(SearchArgument.TruthValue truth) {
         ExpressionTree parent = (ExpressionTree)this.currentTree.getFirst();
         parent.getChildren().add(new ExpressionTree(truth));
         return this;
      }

      static void compactLeaves(ExpressionTree tree, List leaves) {
         if (tree.getOperator() == ExpressionTree.Operator.LEAF) {
            PredicateLeafImpl newLeaf = (PredicateLeafImpl)tree.getPredicateLeaf();
            if (newLeaf.getId() == -1) {
               newLeaf.setId(leaves.size());
               leaves.add(newLeaf);
            }
         } else if (tree.getChildren() != null) {
            for(ExpressionTree child : tree.getChildren()) {
               compactLeaves(child, leaves);
            }
         }

      }

      public SearchArgument build() {
         if (this.currentTree.size() != 1) {
            throw new IllegalArgumentException("Failed to end " + this.currentTree.size() + " operations.");
         } else {
            ExpressionTree optimized = optimize(this.root);
            ExpressionTree expanded = convertToCNF(new ExpressionTree(optimized));
            expanded = flatten(expanded);
            List<PredicateLeaf> finalLeaves = new ArrayList(this.leaves.size());
            compactLeaves(expanded, finalLeaves);
            return new SearchArgumentImpl(expanded, optimized, finalLeaves);
         }
      }

      static ExpressionTree optimize(ExpressionTree root) {
         ExpressionTree optimized = pushDownNot(root);
         optimized = foldMaybe(optimized);
         return flatten(optimized);
      }

      static ExpressionTree pushDownNot(ExpressionTree root) {
         if (root.getOperator() == ExpressionTree.Operator.NOT) {
            ExpressionTree child = (ExpressionTree)root.getChildren().get(0);
            switch (child.getOperator()) {
               case NOT:
                  return pushDownNot((ExpressionTree)child.getChildren().get(0));
               case CONSTANT:
                  return new ExpressionTree(child.getConstant().not());
               case AND:
                  root = new ExpressionTree(ExpressionTree.Operator.OR, new ExpressionTree[0]);

                  for(ExpressionTree kid : child.getChildren()) {
                     root.getChildren().add(pushDownNot(new ExpressionTree(ExpressionTree.Operator.NOT, new ExpressionTree[]{kid})));
                  }
                  break;
               case OR:
                  root = new ExpressionTree(ExpressionTree.Operator.AND, new ExpressionTree[0]);

                  for(ExpressionTree kid : child.getChildren()) {
                     root.getChildren().add(pushDownNot(new ExpressionTree(ExpressionTree.Operator.NOT, new ExpressionTree[]{kid})));
                  }
            }
         } else if (root.getChildren() != null) {
            for(int i = 0; i < root.getChildren().size(); ++i) {
               root.getChildren().set(i, pushDownNot((ExpressionTree)root.getChildren().get(i)));
            }
         }

         return root;
      }

      static ExpressionTree foldMaybe(ExpressionTree expr) {
         if (expr.getChildren() != null) {
            for(int i = 0; i < expr.getChildren().size(); ++i) {
               ExpressionTree child = foldMaybe((ExpressionTree)expr.getChildren().get(i));
               if (child.getConstant() == SearchArgument.TruthValue.YES_NO_NULL) {
                  switch (expr.getOperator()) {
                     case AND:
                        expr.getChildren().remove(i);
                        --i;
                        break;
                     case OR:
                        return child;
                     default:
                        throw new IllegalStateException("Got a maybe as child of " + expr);
                  }
               } else {
                  expr.getChildren().set(i, child);
               }
            }

            if (expr.getChildren().isEmpty()) {
               return new ExpressionTree(SearchArgument.TruthValue.YES_NO_NULL);
            }
         }

         return expr;
      }

      static ExpressionTree flatten(ExpressionTree root) {
         if (root.getChildren() != null) {
            for(int i = 0; i < root.getChildren().size(); ++i) {
               ExpressionTree child = flatten((ExpressionTree)root.getChildren().get(i));
               if (child.getOperator() == root.getOperator() && child.getOperator() != ExpressionTree.Operator.NOT) {
                  boolean first = true;

                  for(ExpressionTree grandkid : child.getChildren()) {
                     if (first) {
                        first = false;
                        root.getChildren().set(i, grandkid);
                     } else {
                        List var10000 = root.getChildren();
                        ++i;
                        var10000.add(i, grandkid);
                     }
                  }
               } else {
                  root.getChildren().set(i, child);
               }
            }

            if ((root.getOperator() == ExpressionTree.Operator.OR || root.getOperator() == ExpressionTree.Operator.AND) && root.getChildren().size() == 1) {
               return (ExpressionTree)root.getChildren().get(0);
            }
         }

         return root;
      }

      private static void generateAllCombinations(List result, List andList, List nonAndList) {
         List<ExpressionTree> kids = ((ExpressionTree)andList.get(0)).getChildren();
         if (result.isEmpty()) {
            for(ExpressionTree kid : kids) {
               ExpressionTree or = new ExpressionTree(ExpressionTree.Operator.OR, new ExpressionTree[0]);
               result.add(or);

               for(ExpressionTree node : nonAndList) {
                  or.getChildren().add(new ExpressionTree(node));
               }

               or.getChildren().add(kid);
            }
         } else {
            List<ExpressionTree> work = new ArrayList(result);
            result.clear();

            for(ExpressionTree kid : kids) {
               for(ExpressionTree or : work) {
                  ExpressionTree copy = new ExpressionTree(or);
                  copy.getChildren().add(kid);
                  result.add(copy);
               }
            }
         }

         if (andList.size() > 1) {
            generateAllCombinations(result, andList.subList(1, andList.size()), nonAndList);
         }

      }

      static ExpressionTree convertToCNF(ExpressionTree root) {
         if (root.getChildren() != null) {
            int size = root.getChildren().size();

            for(int i = 0; i < size; ++i) {
               root.getChildren().set(i, convertToCNF((ExpressionTree)root.getChildren().get(i)));
            }

            if (root.getOperator() == ExpressionTree.Operator.OR) {
               List<ExpressionTree> nonAndList = new ArrayList();
               List<ExpressionTree> andList = new ArrayList();

               for(ExpressionTree child : root.getChildren()) {
                  if (child.getOperator() == ExpressionTree.Operator.AND) {
                     andList.add(child);
                  } else if (child.getOperator() == ExpressionTree.Operator.OR) {
                     nonAndList.addAll(child.getChildren());
                  } else {
                     nonAndList.add(child);
                  }
               }

               if (!andList.isEmpty()) {
                  if (checkCombinationsThreshold(andList)) {
                     root = new ExpressionTree(ExpressionTree.Operator.AND, new ExpressionTree[0]);
                     generateAllCombinations(root.getChildren(), andList, nonAndList);
                  } else {
                     root = new ExpressionTree(SearchArgument.TruthValue.YES_NO_NULL);
                  }
               }
            }
         }

         return root;
      }

      private static boolean checkCombinationsThreshold(List andList) {
         int numComb = 1;

         for(ExpressionTree tree : andList) {
            numComb *= tree.getChildren().size();
            if (numComb > 256) {
               return false;
            }
         }

         return true;
      }
   }
}
