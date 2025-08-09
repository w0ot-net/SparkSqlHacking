package org.apache.hadoop.hive.ql.io.sarg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpressionTree {
   private final Operator operator;
   private final List children;
   private final PredicateLeaf leaf;
   private final SearchArgument.TruthValue constant;

   ExpressionTree() {
      this.operator = null;
      this.children = null;
      this.leaf = null;
      this.constant = null;
   }

   ExpressionTree(Operator op, ExpressionTree... kids) {
      this.operator = op;
      this.children = new ArrayList();
      this.leaf = null;
      this.constant = null;
      Collections.addAll(this.children, kids);
   }

   ExpressionTree(PredicateLeaf leaf) {
      this.operator = ExpressionTree.Operator.LEAF;
      this.children = null;
      this.leaf = leaf;
      this.constant = null;
   }

   ExpressionTree(SearchArgument.TruthValue constant) {
      this.operator = ExpressionTree.Operator.CONSTANT;
      this.children = null;
      this.leaf = null;
      this.constant = constant;
   }

   ExpressionTree(ExpressionTree other) {
      this.operator = other.operator;
      if (other.children == null) {
         this.children = null;
      } else {
         this.children = new ArrayList();

         for(ExpressionTree child : other.children) {
            this.children.add(new ExpressionTree(child));
         }
      }

      this.leaf = other.leaf;
      this.constant = other.constant;
   }

   public SearchArgument.TruthValue evaluate(SearchArgument.TruthValue[] leaves) {
      SearchArgument.TruthValue result = null;
      switch (this.operator) {
         case OR:
            for(ExpressionTree child : this.children) {
               result = child.evaluate(leaves).or(result);
            }

            return result;
         case AND:
            for(ExpressionTree child : this.children) {
               result = child.evaluate(leaves).and(result);
            }

            return result;
         case NOT:
            return ((ExpressionTree)this.children.get(0)).evaluate(leaves).not();
         case LEAF:
            return leaves[this.leaf.getId()];
         case CONSTANT:
            return this.constant;
         default:
            throw new IllegalStateException("Unknown operator: " + this.operator);
      }
   }

   private void buildString(boolean useLeafIds, StringBuilder output) {
      switch (this.operator) {
         case OR:
            output.append("(or");

            for(ExpressionTree child : this.children) {
               output.append(' ');
               child.buildString(useLeafIds, output);
            }

            output.append(')');
            break;
         case AND:
            output.append("(and");

            for(ExpressionTree child : this.children) {
               output.append(' ');
               child.buildString(useLeafIds, output);
            }

            output.append(')');
            break;
         case NOT:
            output.append("(not ");
            ((ExpressionTree)this.children.get(0)).buildString(useLeafIds, output);
            output.append(')');
            break;
         case LEAF:
            output.append("leaf-");
            if (useLeafIds) {
               output.append(this.leaf.getId());
            } else {
               output.append(this.leaf);
            }
            break;
         case CONSTANT:
            output.append(this.constant);
      }

   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      this.buildString(false, buffer);
      return buffer.toString();
   }

   public String toOldString() {
      StringBuilder buffer = new StringBuilder();
      this.buildString(true, buffer);
      return buffer.toString();
   }

   public Operator getOperator() {
      return this.operator;
   }

   public List getChildren() {
      return this.children;
   }

   public SearchArgument.TruthValue getConstant() {
      return this.constant;
   }

   public int getLeaf() {
      return this.leaf.getId();
   }

   public PredicateLeaf getPredicateLeaf() {
      return this.leaf;
   }

   public static enum Operator {
      OR,
      AND,
      NOT,
      LEAF,
      CONSTANT;
   }
}
