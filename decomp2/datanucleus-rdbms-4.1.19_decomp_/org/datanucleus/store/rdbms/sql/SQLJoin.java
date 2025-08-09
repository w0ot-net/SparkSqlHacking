package org.datanucleus.store.rdbms.sql;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.JoinExpression;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.util.NucleusLogger;

public class SQLJoin {
   private JoinType type;
   private SQLTable table;
   private SQLTable joinedTable;
   private BooleanExpression condition;

   public SQLJoin(JoinType type, SQLTable tbl, SQLTable joinedTbl, BooleanExpression condition) {
      if (type != SQLJoin.JoinType.NON_ANSI_JOIN && type != SQLJoin.JoinType.INNER_JOIN && type != SQLJoin.JoinType.LEFT_OUTER_JOIN && type != SQLJoin.JoinType.RIGHT_OUTER_JOIN && type != SQLJoin.JoinType.CROSS_JOIN) {
         throw new NucleusException("Unsupported join type specified : " + type);
      } else if (tbl == null) {
         throw new NucleusException("Specification of join must supply the table reference");
      } else {
         this.type = type;
         this.table = tbl;
         this.joinedTable = joinedTbl;
         this.condition = condition;
      }
   }

   public JoinType getType() {
      return this.type;
   }

   public void setType(JoinType type) {
      this.type = type;
   }

   public SQLTable getTable() {
      return this.table;
   }

   public SQLTable getJoinedTable() {
      return this.joinedTable;
   }

   public BooleanExpression getCondition() {
      return this.condition;
   }

   public void addAndCondition(BooleanExpression expr) {
      this.condition = this.condition.and(expr);
   }

   public String toString() {
      if (this.type == SQLJoin.JoinType.CROSS_JOIN) {
         return "JoinType: CROSSJOIN " + this.type + " tbl=" + this.table;
      } else {
         return this.type != SQLJoin.JoinType.INNER_JOIN && this.type != SQLJoin.JoinType.LEFT_OUTER_JOIN ? super.toString() : "JoinType: " + (this.type == SQLJoin.JoinType.INNER_JOIN ? "INNERJOIN" : "OUTERJOIN") + " tbl=" + this.table + " joinedTbl=" + this.joinedTable;
      }
   }

   public SQLText toSQLText(DatastoreAdapter dba, boolean lock) {
      SQLText st = new SQLText();
      if (this.type != SQLJoin.JoinType.NON_ANSI_JOIN) {
         if (this.type == SQLJoin.JoinType.INNER_JOIN) {
            st.append("INNER JOIN ");
         } else if (this.type == SQLJoin.JoinType.LEFT_OUTER_JOIN) {
            st.append("LEFT OUTER JOIN ");
         } else if (this.type == SQLJoin.JoinType.RIGHT_OUTER_JOIN) {
            st.append("RIGHT OUTER JOIN ");
         } else if (this.type == SQLJoin.JoinType.CROSS_JOIN) {
            st.append("CROSS JOIN ");
         }

         st.append(this.table.toString());
         if (this.type == SQLJoin.JoinType.INNER_JOIN || this.type == SQLJoin.JoinType.LEFT_OUTER_JOIN || this.type == SQLJoin.JoinType.RIGHT_OUTER_JOIN) {
            if (this.condition != null) {
               st.append(" ON ");
               st.append(this.condition.toSQLText());
            } else {
               st.append(" ON 1=0");
               NucleusLogger.DATASTORE_RETRIEVE.debug("Join condition has no 'on' condition defined! table=" + this.table + " type=" + this.type + " joinedTable=" + this.joinedTable + " : so using ON clause as 1=0");
            }
         }

         if (lock && dba.supportsOption("LockOptionWithinJoinClause")) {
            st.append(" WITH ").append(dba.getSelectWithLockOption());
         }
      } else {
         st.append("" + this.table);
      }

      return st;
   }

   public static JoinType getJoinTypeForJoinExpressionType(JoinExpression.JoinType ejt) {
      if (ejt != org.datanucleus.query.expression.JoinExpression.JoinType.JOIN_INNER && ejt != org.datanucleus.query.expression.JoinExpression.JoinType.JOIN_INNER_FETCH) {
         if (ejt != org.datanucleus.query.expression.JoinExpression.JoinType.JOIN_LEFT_OUTER && ejt != org.datanucleus.query.expression.JoinExpression.JoinType.JOIN_LEFT_OUTER_FETCH) {
            return ejt != org.datanucleus.query.expression.JoinExpression.JoinType.JOIN_RIGHT_OUTER && ejt != org.datanucleus.query.expression.JoinExpression.JoinType.JOIN_RIGHT_OUTER_FETCH ? null : SQLJoin.JoinType.RIGHT_OUTER_JOIN;
         } else {
            return SQLJoin.JoinType.LEFT_OUTER_JOIN;
         }
      } else {
         return SQLJoin.JoinType.INNER_JOIN;
      }
   }

   public static enum JoinType {
      NON_ANSI_JOIN,
      INNER_JOIN,
      LEFT_OUTER_JOIN,
      RIGHT_OUTER_JOIN,
      CROSS_JOIN;
   }
}
