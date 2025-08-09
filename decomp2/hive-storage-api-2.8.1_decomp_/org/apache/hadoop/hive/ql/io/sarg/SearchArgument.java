package org.apache.hadoop.hive.ql.io.sarg;

import java.util.List;

public interface SearchArgument {
   List getLeaves();

   ExpressionTree getExpression();

   ExpressionTree getCompactExpression();

   TruthValue evaluate(TruthValue[] var1);

   public static enum TruthValue {
      YES,
      NO,
      NULL,
      YES_NULL,
      NO_NULL,
      YES_NO,
      YES_NO_NULL;

      public TruthValue or(TruthValue right) {
         if (right != null && right != this) {
            if (right != YES && this != YES) {
               if (right != YES_NULL && this != YES_NULL) {
                  if (right == NO) {
                     return this;
                  } else if (this == NO) {
                     return right;
                  } else if (this == NULL) {
                     return right == NO_NULL ? NULL : YES_NULL;
                  } else if (right == NULL) {
                     return this == NO_NULL ? NULL : YES_NULL;
                  } else {
                     return YES_NO_NULL;
                  }
               } else {
                  return YES_NULL;
               }
            } else {
               return YES;
            }
         } else {
            return this;
         }
      }

      public TruthValue and(TruthValue right) {
         if (right != null && right != this) {
            if (right != NO && this != NO) {
               if (right != NO_NULL && this != NO_NULL) {
                  if (right == YES) {
                     return this;
                  } else if (this == YES) {
                     return right;
                  } else if (this == NULL) {
                     return right == YES_NULL ? NULL : NO_NULL;
                  } else if (right == NULL) {
                     return this == YES_NULL ? NULL : NO_NULL;
                  } else {
                     return YES_NO_NULL;
                  }
               } else {
                  return NO_NULL;
               }
            } else {
               return NO;
            }
         } else {
            return this;
         }
      }

      public TruthValue not() {
         switch (this) {
            case NO:
               return YES;
            case YES:
               return NO;
            case NULL:
            case YES_NO:
            case YES_NO_NULL:
               return this;
            case NO_NULL:
               return YES_NULL;
            case YES_NULL:
               return NO_NULL;
            default:
               throw new IllegalArgumentException("Unknown value: " + this);
         }
      }

      public boolean isNeeded() {
         switch (this) {
            case NO:
            case NULL:
            case NO_NULL:
               return false;
            default:
               return true;
         }
      }
   }

   public interface Builder {
      Builder startOr();

      Builder startAnd();

      Builder startNot();

      Builder end();

      Builder lessThan(String var1, PredicateLeaf.Type var2, Object var3);

      Builder lessThanEquals(String var1, PredicateLeaf.Type var2, Object var3);

      Builder equals(String var1, PredicateLeaf.Type var2, Object var3);

      Builder nullSafeEquals(String var1, PredicateLeaf.Type var2, Object var3);

      Builder in(String var1, PredicateLeaf.Type var2, Object... var3);

      Builder isNull(String var1, PredicateLeaf.Type var2);

      Builder between(String var1, PredicateLeaf.Type var2, Object var3, Object var4);

      Builder literal(TruthValue var1);

      SearchArgument build();
   }
}
