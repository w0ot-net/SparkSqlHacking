package org.apache.derby.impl.sql.compile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableProperties;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.JoinStrategy;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.OptimizerPlan;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataTypeUtilities;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.types.XML;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

class SQLParser implements SQLParserConstants {
   private static final String[] SAVEPOINT_CLAUSE_NAMES = new String[]{"UNIQUE", "ON ROLLBACK RETAIN LOCKS", "ON ROLLBACK RETAIN CURSORS"};
   private static final String[] ROUTINE_CLAUSE_NAMES = new String[]{null, "SPECIFIC", "RESULT SET", "LANGUAGE", "EXTERNAL NAME", "PARAMETER STYLE", "SQL", "DETERMINISTIC", "ON NULL INPUT", "RETURN TYPE", "EXTERNAL SECURITY", null};
   private static final int[] JAVA_ROUTINE_CLAUSES = new int[]{3, 4, 5};
   private static final String[] TEMPORARY_TABLE_CLAUSE_NAMES = new String[]{"NOT LOGGED", "ON COMMIT", "ON ROLLBACK"};
   private static final int DEFAULT_STRING_COLUMN_LENGTH = 1;
   private static final int ON_OR_USING_CLAUSE_SIZE = 2;
   private static final int ON_CLAUSE = 0;
   private static final int USING_CLAUSE = 1;
   private static final int OPTIONAL_TABLE_CLAUSES_SIZE = 3;
   private static final int OPTIONAL_TABLE_CLAUSES_TABLE_PROPERTIES = 0;
   private static final int OPTIONAL_TABLE_CLAUSES_DERIVED_RCL = 1;
   private static final int OPTIONAL_TABLE_CLAUSES_CORRELATION_NAME = 2;
   private static final int MAX_UTF8_LENGTH = 65535;
   private static final int NO_SET_OP = 0;
   private static final int UNION_OP = 1;
   private static final int UNION_ALL_OP = 2;
   private static final int EXCEPT_OP = 3;
   private static final int EXCEPT_ALL_OP = 4;
   private static final int INTERSECT_OP = 5;
   private static final int INTERSECT_ALL_OP = 6;
   private static final int IDX_DATA_TYPE = 0;
   private static final int IDX_START_WITH_OPTION = 1;
   private static final int IDX_INCREMENT_BY_OPTION = 2;
   private static final int IDX_MAX_VALUE_OPTION = 3;
   private static final int IDX_MIN_VALUE_OPTION = 4;
   private static final int IDX_CYCLE_OPTION = 5;
   private static final int IDX_OPTION_COUNT = 6;
   private static final int OFFSET_CLAUSE = 0;
   private static final int FETCH_FIRST_CLAUSE = 1;
   private static final int OFFSET_CLAUSE_COUNT = 2;
   private Object[] paramDefaults;
   private String statementSQLText;
   private OptimizerFactory optimizerFactory;
   private ContextManager cm;
   private CompilerContext compilerContext;
   private int parameterNumber;
   private ArrayList parameterList;
   private boolean lastTokenDelimitedIdentifier = false;
   private boolean nextToLastTokenDelimitedIdentifier = false;
   private Token lastIdentifierToken;
   private Token nextToLastIdentifierToken;
   private Token thirdToLastIdentifierToken;
   static final String SINGLEQUOTES = "''";
   static final String DOUBLEQUOTES = "\"\"";
   static final String DEFAULT_INDEX_TYPE = "BTREE";
   private static final String[] ESCAPED_SYSFUN_FUNCTIONS = new String[]{"ACOS", "ASIN", "ATAN", "ATAN2", "COS", "SIN", "TAN", "PI", "DEGREES", "RADIANS", "EXP", "LOG", "LOG10", "CEILING", "FLOOR", "SIGN", "RAND", "COT"};
   public SQLParserTokenManager token_source;
   public Token token;
   public Token jj_nt;
   private Token jj_scanpos;
   private Token jj_lastpos;
   private int jj_la;
   public boolean lookingAhead = false;
   private boolean jj_semLA;
   private int jj_gen;
   private final int[] jj_la1 = new int[381];
   private static int[] jj_la1_0;
   private static int[] jj_la1_1;
   private static int[] jj_la1_2;
   private static int[] jj_la1_3;
   private static int[] jj_la1_4;
   private static int[] jj_la1_5;
   private static int[] jj_la1_6;
   private static int[] jj_la1_7;
   private static int[] jj_la1_8;
   private static int[] jj_la1_9;
   private static int[] jj_la1_10;
   private static int[] jj_la1_11;
   private static int[] jj_la1_12;
   private static int[] jj_la1_13;
   private static int[] jj_la1_14;
   private static int[] jj_la1_15;
   private final JJCalls[] jj_2_rtns = new JJCalls[94];
   private boolean jj_rescan = false;
   private int jj_gc = 0;
   private final LookaheadSuccess jj_ls = new LookaheadSuccess();
   private Vector jj_expentries = new Vector();
   private int[] jj_expentry;
   private int jj_kind = -1;
   private int[] jj_lasttokens = new int[100];
   private int jj_endpos;

   final void setCompilerContext(CompilerContext var1) {
      this.compilerContext = var1;
      this.cm = var1.getContextManager();
   }

   private final OptimizerFactory getOptimizerFactory() throws StandardException {
      if (this.optimizerFactory == null) {
         this.optimizerFactory = this.getCompilerContext().getOptimizerFactory();
      }

      return this.optimizerFactory;
   }

   private final CompilerContext getCompilerContext() {
      return this.compilerContext;
   }

   private DataTypeDescriptor getDataTypeServices(int var1, int var2, int var3, int var4) throws StandardException {
      return new DataTypeDescriptor(TypeId.getBuiltInTypeId(var1), var2, var3, true, var4);
   }

   private DataTypeDescriptor getJavaClassDataTypeDescriptor(TableName var1) throws StandardException {
      return new DataTypeDescriptor(TypeId.getUserDefinedTypeId(var1.getSchemaName(), var1.getTableName(), (String)null), true);
   }

   private LanguageConnectionContext getLanguageConnectionContext() {
      return (LanguageConnectionContext)this.getContextManager().getContext("LanguageConnectionContext");
   }

   private boolean checkVersion(int var1, String var2) throws StandardException {
      return this.getLanguageConnectionContext().getDataDictionary().checkVersion(var1, var2);
   }

   private void checkSqlStandardAccess(String var1) throws StandardException {
      if (!this.getLanguageConnectionContext().usesSqlAuthorization()) {
         throw StandardException.newException("42Z60", new Object[]{var1, "derby.database.sqlAuthorization", "TRUE"});
      }
   }

   private void forbidNextValueFor() {
      CompilerContext var1 = this.getCompilerContext();
      var1.setReliability(var1.getReliability() | 16384);
   }

   private void checkInternalFeature(String var1) throws StandardException {
      CompilerContext var2 = this.getCompilerContext();
      if ((var2.getReliability() & 1024) != 0) {
         throw StandardException.newException("42X01", new Object[]{var1});
      }
   }

   private void checkTypeLimits(int var1, int var2) throws StandardException {
      boolean var3 = true;
      switch (var1) {
         case -3:
         case 12:
            if (var2 > 32672) {
               var3 = false;
            }
            break;
         case -2:
         case 1:
            if (var2 > 254) {
               var3 = false;
            }
      }

      if (!var3) {
         DataTypeDescriptor var4 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(var1, var2);
         throw StandardException.newException("42611", new Object[]{var4.getSQLstring()});
      }
   }

   private final ContextManager getContextManager() {
      return this.cm;
   }

   private static void verifyImageLength(String var0) throws StandardException {
      if (var0.length() > 65535) {
         throw StandardException.newException("42Z99", new Object[0]);
      }
   }

   private static String normalizeDelimitedID(String var0) {
      var0 = StringUtil.compressQuotes(var0, "\"\"");
      return var0;
   }

   private static boolean isDATETIME(int var0) {
      return var0 == 285 || var0 == 350 || var0 == 351;
   }

   ValueNode multOp(ValueNode var1, ValueNode var2, int var3) throws StandardException {
      if (var1 == null) {
         return var2;
      } else {
         switch (var3) {
            case 3 -> {
               return new BinaryArithmeticOperatorNode(3, var1, var2, this.getContextManager());
            }
            case 4 -> {
               return new BinaryArithmeticOperatorNode(0, var1, var2, this.getContextManager());
            }
            case 5 -> {
               return new ConcatenationOperatorNode(var1, var2, this.getContextManager());
            }
            default -> {
               return null;
            }
         }
      }
   }

   private void setUpAndLinkParameters() throws StandardException {
      CompilerContextImpl var1 = (CompilerContextImpl)this.getCompilerContext();
      var1.setParameterList(this.parameterList);
      DataTypeDescriptor[] var2 = var1.getParameterTypes();
      int var4 = -1;
      int var5 = this.parameterList.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         ++var4;
         ParameterNode var3 = (ParameterNode)this.parameterList.get(var6);
         var3.setDescriptors(var2);
      }

   }

   void initUnnamedParameterList() {
      this.parameterList = new ArrayList();
   }

   ParameterNode makeParameterNode() throws StandardException {
      DataValueDescriptor var2 = null;
      if (this.paramDefaults != null && this.parameterNumber < this.paramDefaults.length) {
         var2 = (DataValueDescriptor)this.paramDefaults[this.parameterNumber];
      }

      ParameterNode var1 = new ParameterNode(this.parameterNumber, var2, this.getContextManager());
      ++this.parameterNumber;
      this.parameterList.add(var1);
      return var1;
   }

   NumericConstantNode getNumericNode(String var1, boolean var2) throws StandardException {
      ContextManager var3 = this.getContextManager();

      try {
         return new NumericConstantNode(TypeId.getBuiltInTypeId(4), Integer.valueOf(var1), var3);
      } catch (NumberFormatException var6) {
         try {
            return new NumericConstantNode(TypeId.getBuiltInTypeId(-5), Long.valueOf(var1), var3);
         } catch (NumberFormatException var5) {
            if (var2) {
               throw var5;
            } else {
               return new NumericConstantNode(TypeId.getBuiltInTypeId(3), new BigDecimal(var1), var3);
            }
         }
      }
   }

   private boolean isBuiltInAlias() {
      boolean var1 = false;
      switch (this.token.kind) {
         case 271:
         case 272:
         case 311:
         case 376:
         case 403:
         case 404:
         case 438:
         case 444:
            var1 = true;
            break;
         default:
            var1 = false;
      }

      return var1;
   }

   boolean commonDatatypeName(boolean var1) {
      return this.commonDatatypeName(1, var1);
   }

   boolean dataTypeCheck(int var1) {
      if (this.commonDatatypeName(var1, false)) {
         return true;
      } else {
         boolean var2 = true;
         switch (this.getToken(var1).kind) {
            case 453:
            case 454:
            case 458:
               var2 = false;
            default:
               return var2;
         }
      }
   }

   boolean commonDatatypeName(int var1, boolean var2) {
      boolean var3 = false;
      switch (this.getToken(var1).kind) {
         case 80:
         case 81:
         case 88:
         case 89:
         case 116:
         case 117:
         case 128:
         case 143:
         case 167:
         case 168:
         case 187:
         case 193:
         case 214:
         case 228:
         case 261:
         case 275:
         case 278:
         case 285:
         case 318:
         case 350:
         case 351:
         case 360:
         case 368:
         case 377:
         case 411:
            var3 = true;
            break;
         case 185:
            if (var2) {
               switch (this.getToken(var1 + 1).kind) {
                  case 88:
                  case 89:
                     var3 = true;
               }
            } else {
               var3 = true;
            }
            break;
         case 369:
            if (var2) {
               switch (this.getToken(var1 + 1).kind) {
                  case 80:
                  case 81:
                  case 260:
                  case 261:
                  case 411:
                     var3 = true;
               }
            } else {
               var3 = true;
            }
      }

      return var3;
   }

   private StatementNode getDeleteNode(FromTable var1, TableName var2, ValueNode var3) throws StandardException {
      FromList var4 = new FromList(this.getContextManager());
      var4.addFromTable(var1);
      SelectNode var5 = new SelectNode((ResultColumnList)null, var4, var3, (GroupByList)null, (ValueNode)null, (WindowList)null, (OptimizerPlan)null, this.getContextManager());
      DeleteNode var6 = new DeleteNode(var2, var5, (MatchingClauseNode)null, this.getContextManager());
      this.setUpAndLinkParameters();
      return var6;
   }

   private StatementNode getUpdateNode(FromTable var1, TableName var2, ResultColumnList var3, ValueNode var4) throws StandardException {
      FromList var5 = new FromList(this.getContextManager());
      var5.addFromTable(var1);
      SelectNode var6 = new SelectNode(var3, var5, var4, (GroupByList)null, (ValueNode)null, (WindowList)null, (OptimizerPlan)null, this.getContextManager());
      UpdateNode var7 = new UpdateNode(var2, var6, (MatchingClauseNode)null, this.getContextManager());
      this.setUpAndLinkParameters();
      return var7;
   }

   private ValueNode getTrimOperatorNode(Integer var1, ValueNode var2, ValueNode var3, ContextManager var4) throws StandardException {
      if (var2 == null) {
         var2 = new CharConstantNode(" ", this.getContextManager());
      }

      return new TernaryOperatorNode(var3, (ValueNode)var2, (ValueNode)null, 0, var1, var4 == null ? this.getContextManager() : var4);
   }

   private boolean ansiTrimSpecFollows() {
      return this.getToken(2).kind == 176 || this.getToken(2).kind == 247 || this.getToken(2).kind == 82;
   }

   private boolean remainingPredicateFollows() {
      boolean var1 = false;
      switch (this.getToken(1).kind) {
         case 79:
         case 160:
         case 171:
         case 178:
         case 465:
         case 466:
         case 467:
         case 468:
         case 469:
         case 470:
         case 471:
            var1 = true;
            break;
         case 190:
            switch (this.getToken(2).kind) {
               case 79:
               case 160:
               case 178:
                  var1 = true;
            }
      }

      return var1;
   }

   private boolean dropFollows() {
      return this.getToken(1).kind == 129;
   }

   private boolean escapedValueFunctionFollows() {
      if (this.getToken(1).kind != 451) {
         return false;
      } else {
         return this.getToken(2).kind == 399;
      }
   }

   private String getEscapedSYSFUN(String var1) {
      var1 = StringUtil.SQLToUpperCase(var1);

      for(int var2 = 0; var2 < ESCAPED_SYSFUN_FUNCTIONS.length; ++var2) {
         if (ESCAPED_SYSFUN_FUNCTIONS[var2].equals(var1)) {
            return var1;
         }
      }

      return null;
   }

   private boolean columnMethodInvocationFollows() {
      int var1 = this.getToken(1).kind;
      if (var1 == 0) {
         return false;
      } else if (this.getToken(1).image.charAt(0) == '?') {
         return false;
      } else if (var1 != 109 && var1 != 110 && var1 != 111 && (var1 != 108 || !isDATETIME(this.getToken(2).kind))) {
         if (this.getToken(2).kind != 460) {
            return false;
         } else {
            var1 = this.getToken(4).kind;
            if (var1 == 453) {
               return true;
            } else if (var1 != 460) {
               return false;
            } else {
               var1 = this.getToken(6).kind;
               if (var1 == 453) {
                  return true;
               } else if (var1 != 460) {
                  return false;
               } else {
                  var1 = this.getToken(8).kind;
                  return var1 == 453;
               }
            }
         }
      } else {
         return false;
      }
   }

   private boolean aggregateFollows() {
      boolean var1 = false;
      switch (this.getToken(1).kind) {
         case 77:
         case 181:
         case 182:
         case 236:
            var1 = true;
            break;
         case 105:
            if (this.getToken(2).kind == 453) {
               var1 = true;
            }
         default:
            if (this.getToken(2).kind == 453 && this.getToken(3).kind == 127) {
               var1 = true;
            }
      }

      return var1;
   }

   private boolean builtinAggregateFollows() {
      switch (this.getToken(1).kind) {
         case 77:
         case 105:
         case 181:
         case 182:
         case 236:
            return true;
         default:
            return false;
      }
   }

   private boolean distinctUDAFollows() {
      if (this.builtinAggregateFollows()) {
         return false;
      } else if (this.getToken(2).kind == 453 && this.getToken(3).kind == 127) {
         return true;
      } else {
         return this.getToken(2).kind == 460 && this.getToken(4).kind == 453 && this.getToken(5).kind == 127;
      }
   }

   private boolean windowOrAggregateFunctionFollows() {
      if (this.distinctUDAFollows()) {
         return false;
      } else {
         boolean var1 = false;
         switch (this.getToken(1).kind) {
            case 374 -> var1 = true;
            default -> var1 = this.aggregateFollows();
         }

         return var1;
      }
   }

   private boolean miscBuiltinFollows() {
      boolean var1 = false;
      int var2 = this.getToken(1).kind;
      if (this.getToken(0).kind == 361) {
         var1 = true;
      }

      switch (var2) {
         case 87:
         case 453:
            var1 = false;
            break;
         case 108:
            if (isDATETIME(this.getToken(2).kind)) {
               var1 = true;
            }
            break;
         case 109:
         case 110:
         case 111:
         case 366:
            var1 = true;
            break;
         default:
            if (this.getToken(2).kind == 453) {
               var1 = true;
            }
      }

      return var1;
   }

   private boolean subqueryFollows() {
      boolean var2 = false;
      int var3 = 1;

      while(true) {
         int var1 = this.getToken(var3).kind;
         if (var1 != 453) {
            if (var1 == 225 || var1 == 259) {
               var2 = true;
            }

            return var2;
         }

         ++var3;
      }
   }

   private boolean seeingOffsetClause() {
      boolean var1 = false;
      int var2 = 2;
      int var3 = this.getToken(var2).kind;
      if (var3 != 457 && var3 != 459) {
         if (var3 == 487 || var3 == 472) {
            ++var2;
            var3 = this.getToken(var2).kind;
            return var3 == 332 || var3 == 221;
         }
      } else {
         ++var2;
         var3 = this.getToken(var2).kind;
         if (var3 == 487) {
            ++var2;
            var3 = this.getToken(var2).kind;
            return var3 == 332 || var3 == 221;
         }
      }

      return false;
   }

   private boolean rowValueConstructorListFollows() {
      boolean var2 = false;
      if (this.getToken(1).kind == 453) {
         int var1 = 1;
         int var3 = 2;

         while(true) {
            int var4 = this.getToken(var3).kind;
            if (var3 == 2 && (var4 == 191 || var4 == 119)) {
               var2 = true;
               break;
            }

            if (var1 == 1 && var4 == 458) {
               var2 = true;
               break;
            }

            if (var4 == 0) {
               break;
            }

            if (var4 == 453) {
               ++var1;
            } else if (var4 == 454) {
               --var1;
            }

            if (var1 == 0) {
               break;
            }

            ++var3;
         }
      }

      return var2;
   }

   private boolean derbyPropertiesListFollows() {
      return this.getToken(1).kind == 59;
   }

   private boolean newInvocationFollows(int var1) {
      boolean var2 = false;
      if (this.getToken(var1).kind == 409) {
         int var3 = 2 + var1;

         while(true) {
            int var4 = this.getToken(var3).kind;
            if (var4 == 453) {
               var2 = true;
               break;
            }

            if (var4 != 460) {
               break;
            }

            var3 += 2;
         }
      }

      return var2;
   }

   boolean javaClassFollows() {
      boolean var1 = false;
      int var2 = 2;

      while(true) {
         int var3 = this.getToken(var2).kind;
         if (var3 == 463) {
            var1 = true;
            break;
         }

         if (var3 != 460) {
            break;
         }

         var2 += 2;
      }

      return var1;
   }

   private boolean fromNewInvocationFollows() {
      boolean var1 = false;
      return this.getToken(1).kind == 147 && this.newInvocationFollows(2);
   }

   private boolean joinedTableExpressionFollows() {
      boolean var1 = false;
      int var2 = this.getToken(1).kind;
      int var3 = this.getToken(2).kind;
      if (var2 == 173) {
         var1 = true;
      } else if (var2 == 163 && var3 == 173) {
         var1 = true;
      } else if (var2 == 107 && var3 == 173) {
         var1 = true;
      } else if (var2 == 186) {
         var1 = true;
      } else if ((var2 == 177 || var2 == 219) && var3 == 201) {
         if (this.getToken(3).kind == 173) {
            var1 = true;
         }
      } else if ((var2 == 177 || var2 == 219) && var3 == 173) {
         var1 = true;
      }

      return var1;
   }

   private static String aggName(Token var0) {
      String var1 = null;
      switch (var0.kind) {
         case 77 -> var1 = "AVG";
         case 105 -> var1 = "COUNT";
         case 181 -> var1 = "MAX";
         case 182 -> var1 = "MIN";
         case 236 -> var1 = "SUM";
      }

      return var1;
   }

   private static Class aggClass(Token var0) {
      Class var1 = null;
      switch (var0.kind) {
         case 77:
         case 236:
            var1 = SumAvgAggregateDefinition.class;
            break;
         case 105:
            var1 = CountAggregateDefinition.class;
            break;
         case 181:
         case 182:
            var1 = MaxMinAggregateDefinition.class;
      }

      return var1;
   }

   private boolean anotherPropertyFollows() {
      boolean var1 = false;
      if (this.getToken(1).kind == 458) {
         int var2 = 3;

         int var3;
         do {
            var3 = this.getToken(var2).kind;
            if (var3 == 467) {
               var1 = true;
               break;
            }

            var2 += 2;
         } while(var3 == 460);
      }

      return var1;
   }

   StatementNode getCreateAliasNode(TableName var1, Object var2, Object var3, char var4) throws StandardException {
      String var5 = null;
      if (var4 != 'S' && var4 != 'A' && var4 != 'G') {
         String var7 = (String)var2;
         int var8 = var7.indexOf(40);
         int var6;
         if (var8 == -1) {
            var6 = var7.lastIndexOf(46);
         } else {
            var6 = var7.substring(0, var8).lastIndexOf(46);
         }

         if (var6 == -1 || var6 == var7.length() - 1) {
            throw StandardException.newException("42Y04", new Object[]{var7});
         }

         String var9 = var7.substring(0, var6);
         var5 = var7.substring(var6 + 1);
         var2 = var9;
      }

      return new CreateAliasNode(var1, var2, var5, var3, var4, this.cm);
   }

   StatementNode dropAliasNode(TableName var1, char var2) throws StandardException {
      return new DropAliasNode(var1, var2, this.getContextManager());
   }

   ValueNode getSubstringNode(ValueNode var1, ValueNode var2, ValueNode var3) throws StandardException {
      return new TernaryOperatorNode(var1, var2, var3, 2, -1, this.getContextManager());
   }

   private void initStatement(String var1, Object[] var2) throws StandardException {
      this.parameterNumber = 0;
      this.statementSQLText = var1;
      this.paramDefaults = var2;
      this.optimizerFactory = this.getOptimizerFactory();
      this.initUnnamedParameterList();
   }

   private ValueNode getJdbcIntervalNode(int var1) throws StandardException {
      return new NumericConstantNode(TypeId.getBuiltInTypeId(4), var1, this.getContextManager());
   }

   void checkRequiredRoutineClause(int[] var1, Object[] var2) throws StandardException {
      for(int var3 = 0; var3 < var1.length; ++var3) {
         int var4 = var1[var3];
         if (var2[var4] == null) {
            throw StandardException.newException("42X01", new Object[]{ROUTINE_CLAUSE_NAMES[var4]});
         }
      }

   }

   boolean isPrivilegeKeywordExceptTrigger(int var1) {
      return var1 == 225 || var1 == 122 || var1 == 166 || var1 == 254 || var1 == 215 || var1 == 137 || var1 == 357 || var1 == 66;
   }

   TableElementNode wrapAlterColumnDefaultValue(ValueNode var1, String var2, long[] var3) throws StandardException {
      if (var3[2] == 0L) {
         var3 = null;
      }

      return new ModifyColumnNode(1, var2, var1, (DataTypeDescriptor)null, var3, this.getContextManager());
   }

   private boolean isTableValueConstructor(ResultSetNode var1) throws StandardException {
      return var1 instanceof RowResultSetNode || var1 instanceof UnionNode && ((UnionNode)var1).tableConstructor();
   }

   private JoinNode newJoinNode(ResultSetNode var1, ResultSetNode var2, ValueNode var3, ResultColumnList var4, int var5) throws StandardException {
      switch (var5) {
         case 1:
            return new JoinNode(var1, var2, var3, var4, (ResultColumnList)null, (Properties)null, (Properties)null, this.getContextManager());
         case 2:
         default:
            return null;
         case 3:
            return new HalfOuterJoinNode(var1, var2, var3, var4, false, (Properties)null, this.getContextManager());
         case 4:
            return new HalfOuterJoinNode(var1, var2, var3, var4, true, (Properties)null, this.getContextManager());
      }
   }

   private static boolean hasQueryExpressionSuffix(OrderByList var0, ValueNode[] var1) {
      return var0 != null || var1[0] != null || var1[1] != null;
   }

   public final StatementNode Statement(String var1, Object[] var2) throws ParseException, StandardException {
      this.initStatement(var1, var2);
      StatementNode var3 = this.StatementPart((Token[])null);
      this.jj_consume_token(0);
      var3.setBeginOffset(0);
      var3.setEndOffset(var1.length() - 1);
      return var3;
   }

   public final ValueNode SearchCondition(String var1) throws ParseException, StandardException {
      this.initStatement(var1, (Object[])null);
      ValueNode var2 = this.valueExpression();
      this.jj_consume_token(0);
      var2.setBeginOffset(0);
      var2.setEndOffset(var1.length() - 1);
      return var2;
   }

   public final StatementNode proceduralStatement(Token[] var1) throws ParseException, StandardException {
      var1[0] = this.getToken(1);
      Object var2;
      switch (this.jj_nt.kind) {
         case 122:
            var2 = this.preparableDeleteStatement();
            break;
         case 166:
            var2 = this.insertStatement();
            break;
         case 225:
         case 259:
         case 453:
            var2 = this.preparableSelectStatement(true);
            break;
         case 254:
            var2 = this.preparableUpdateStatement();
            break;
         case 309:
            var2 = this.mergeStatement();
            break;
         case 361:
         case 451:
         case 472:
            var2 = this.callStatement();
            break;
         default:
            this.jj_la1[0] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      return (StatementNode)var2;
   }

   public final StatementNode StatementPart(Token[] var1) throws ParseException, StandardException {
      if (var1 != null) {
         var1[0] = this.getToken(1);
      }

      switch (this.jj_nt.kind) {
         case 405:
         case 424:
            StatementNode var2;
            switch (this.jj_nt.kind) {
               case 405:
                  var2 = this.lockStatement();
                  break;
               case 424:
                  var2 = this.spsRenameStatement();
                  break;
               default:
                  this.jj_la1[1] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }

            return var2;
         default:
            this.jj_la1[5] = this.jj_gen;
            if (this.jj_2_4(1)) {
               StatementNode var3;
               switch (this.jj_nt.kind) {
                  case 68:
                     var3 = this.spsAlterStatement();
                     break;
                  case 106:
                     var3 = this.createStatements();
                     break;
                  case 118:
                     var3 = this.globalTemporaryTableDeclaration();
                     break;
                  case 122:
                  case 166:
                  case 220:
                  case 225:
                  case 254:
                  case 259:
                  case 309:
                  case 327:
                  case 333:
                  case 361:
                  case 451:
                  case 453:
                  case 472:
                     var3 = this.preparableSQLDataStatement();
                     break;
                  case 129:
                     var3 = this.dropStatements();
                     break;
                  default:
                     this.jj_la1[2] = this.jj_gen;
                     if (this.jj_2_1(1)) {
                        var3 = this.spsSetStatement();
                     } else {
                        switch (this.jj_nt.kind) {
                           case 354:
                              var3 = this.truncateTableStatement();
                              break;
                           default:
                              this.jj_la1[3] = this.jj_gen;
                              if (this.jj_2_2(1)) {
                                 var3 = this.grantStatement();
                              } else if (this.jj_2_3(1)) {
                                 var3 = this.revokeStatement();
                              } else {
                                 switch (this.jj_nt.kind) {
                                    case 137:
                                       var3 = this.execStatement();
                                       break;
                                    default:
                                       this.jj_la1[4] = this.jj_gen;
                                       this.jj_consume_token(-1);
                                       throw new ParseException();
                                 }
                              }
                        }
                     }
               }

               return var3;
            } else {
               this.jj_consume_token(-1);
               throw new ParseException();
            }
      }
   }

   public final StatementNode createStatements() throws ParseException, StandardException {
      Token var2 = this.jj_consume_token(106);
      StatementNode var1;
      switch (this.jj_nt.kind) {
         case 149:
            var1 = this.functionDefinition();
            break;
         case 211:
            var1 = this.procedureDefinition();
            break;
         case 222:
         case 263:
         case 348:
         case 373:
         case 432:
         case 442:
            switch (this.jj_nt.kind) {
               case 222:
                  var1 = this.schemaDefinition();
                  return var1;
               case 263:
                  var1 = this.viewDefinition(var2);
                  return var1;
               case 348:
                  var1 = this.synonymDefinition();
                  return var1;
               case 373:
                  var1 = this.roleDefinition();
                  return var1;
               case 432:
                  var1 = this.sequenceDefinition();
                  return var1;
               case 442:
                  var1 = this.triggerDefinition();
                  return var1;
               default:
                  this.jj_la1[6] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         case 239:
            var1 = this.tableDefinition();
            break;
         case 252:
         case 400:
            var1 = this.indexDefinition();
            break;
         case 355:
            var1 = this.udtDefinition();
            break;
         case 391:
            var1 = this.aggregateDefinition();
            break;
         default:
            this.jj_la1[7] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      return var1;
   }

   public final StatementNode dropStatements() throws ParseException, StandardException {
      this.jj_consume_token(129);
      StatementNode var1;
      switch (this.jj_nt.kind) {
         case 149:
         case 211:
         case 348:
         case 355:
         case 391:
            var1 = this.dropAliasStatement();
            break;
         case 222:
            var1 = this.dropSchemaStatement();
            break;
         case 239:
            var1 = this.dropTableStatement();
            break;
         case 263:
            var1 = this.dropViewStatement();
            break;
         case 373:
            var1 = this.dropRoleStatement();
            break;
         case 400:
            var1 = this.dropIndexStatement();
            break;
         case 432:
            var1 = this.dropSequenceStatement();
            break;
         case 442:
            var1 = this.dropTriggerStatement();
            break;
         default:
            this.jj_la1[8] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      return var1;
   }

   public final StatementNode spsAlterStatement() throws ParseException, StandardException {
      this.jj_consume_token(68);
      StatementNode var1 = this.alterTableStatement();
      return var1;
   }

   public final StatementNode spsSetStatement() throws ParseException, StandardException {
      if (this.getToken(1).kind == 227 && this.getToken(2).kind != 108) {
         this.jj_consume_token(227);
         Object var2;
         if (this.jj_2_5(1)) {
            var2 = this.setIsolationStatement();
         } else if (this.jj_2_6(1)) {
            var2 = this.setSchemaStatement();
         } else {
            switch (this.jj_nt.kind) {
               case 101:
                  var2 = this.setConstraintsStatement();
                  break;
               case 373:
                  var2 = this.setRoleStatement();
                  break;
               case 406:
                  var2 = this.setMessageLocaleStatement();
                  break;
               default:
                  this.jj_la1[9] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         }

         return (StatementNode)var2;
      } else if (this.getToken(1).kind == 227 && this.getToken(2).kind == 108) {
         this.jj_consume_token(227);
         Object var1;
         if (this.jj_2_7(1)) {
            var1 = this.setSchemaStatement();
         } else {
            if (!this.jj_2_8(1)) {
               this.jj_consume_token(-1);
               throw new ParseException();
            }

            var1 = this.setIsolationStatement();
         }

         return (StatementNode)var1;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final void constraintsReference(List var1) throws ParseException, StandardException {
      TableName var2 = this.qualifiedName(128);
      var1.add(var2);
   }

   public final StatementNode setConstraintsStatement() throws ParseException, StandardException {
      ArrayList var1 = new ArrayList();
      this.jj_consume_token(101);
      if (this.jj_2_9(1)) {
         this.constraintsReference(var1);

         label26:
         while(true) {
            switch (this.jj_nt.kind) {
               case 458:
                  this.jj_consume_token(458);
                  this.constraintsReference(var1);
                  break;
               default:
                  this.jj_la1[10] = this.jj_gen;
                  break label26;
            }
         }
      } else {
         switch (this.jj_nt.kind) {
            case 66:
               this.jj_consume_token(66);
               var1 = null;
               break;
            default:
               this.jj_la1[11] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }

      boolean var2;
      switch (this.jj_nt.kind) {
         case 121:
            this.jj_consume_token(121);
            var2 = true;
            break;
         case 159:
            this.jj_consume_token(159);
            var2 = false;
            break;
         default:
            this.jj_la1[12] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      this.checkVersion(230, "DEFERRED CONSTRAINTS");
      return new SetConstraintsNode(var1, var2, this.cm);
   }

   public final StatementNode preparableSQLDataStatement() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 122:
            StatementNode var7 = this.preparableDeleteStatement();
            return var7;
         case 166:
            StatementNode var6 = this.insertStatement();
            return var6;
         case 220:
         case 327:
         case 333:
            StatementNode var5 = this.savepointStatement();
            return var5;
         case 225:
         case 259:
         case 453:
            CursorNode var4 = this.preparableSelectStatement(true);
            return var4;
         case 254:
            StatementNode var3 = this.preparableUpdateStatement();
            return var3;
         case 309:
            StatementNode var2 = this.mergeStatement();
            return var2;
         case 361:
         case 451:
         case 472:
            StatementNode var1 = this.callStatement();
            return var1;
         default:
            this.jj_la1[13] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode preparableDeleteStatement() throws ParseException, StandardException {
      this.jj_consume_token(122);
      StatementNode var1 = this.deleteBody();
      return var1;
   }

   public final StatementNode deleteBody() throws ParseException, StandardException {
      JavaToSQLValueNode var1 = null;
      String var2 = null;
      Object var3 = null;
      ValueNode var4 = null;
      Object var5 = null;
      Properties var7 = null;
      Object var8 = null;
      if (this.fromNewInvocationFollows()) {
         this.jj_consume_token(147);
         var1 = this.newInvocation();
         switch (this.jj_nt.kind) {
            case 265:
               Token var13 = this.jj_consume_token(265);
               var4 = this.whereClause(var13);
               break;
            default:
               this.jj_la1[14] = this.jj_gen;
         }

         FromVTI var11 = new FromVTI((MethodCallNode)var1.getJavaValueNode(), (String)null, (ResultColumnList)null, (Properties)null, this.getContextManager());
         return this.getDeleteNode(var11, (TableName)var3, var4);
      } else {
         switch (this.jj_nt.kind) {
            case 147:
               this.jj_consume_token(147);
               TableName var10 = this.qualifiedName(128);
               if (this.getToken(1).kind != 0 && this.getToken(1).kind != 265 && !this.derbyPropertiesListFollows()) {
                  switch (this.jj_nt.kind) {
                     case 72 -> this.jj_consume_token(72);
                     default -> this.jj_la1[15] = this.jj_gen;
                  }

                  var2 = this.identifier(128, true);
               }

               switch (this.jj_nt.kind) {
                  case 59:
                     var7 = this.propertyList(false);
                     this.jj_consume_token(64);
                     break;
                  default:
                     this.jj_la1[16] = this.jj_gen;
               }

               switch (this.jj_nt.kind) {
                  case 265:
                     Token var12 = this.jj_consume_token(265);
                     if (this.getToken(1).kind == 108 && this.getToken(2).kind == 194) {
                        var5 = this.currentOfClause(var2);
                     } else {
                        if (!this.jj_2_10(1)) {
                           this.jj_consume_token(-1);
                           throw new ParseException();
                        }

                        var4 = this.whereClause(var12);
                     }
                     break;
                  default:
                     this.jj_la1[17] = this.jj_gen;
               }

               if (var5 == null) {
                  var5 = new FromBaseTable(var10, var2, 2, (ResultColumnList)null, this.getContextManager());
               }

               if (var7 != null) {
                  ((FromBaseTable)var5).setTableProperties(var7);
               }

               return this.getDeleteNode((FromTable)var5, var10, var4);
            default:
               this.jj_la1[18] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final FromTable currentOfClause(String var1) throws ParseException, StandardException {
      Object var2 = null;
      this.jj_consume_token(108);
      this.jj_consume_token(194);
      String var3 = this.identifier(128, true);
      return new CurrentOfNode(var1, var3, (Properties)null, this.getContextManager());
   }

   public final CursorNode preparableSelectStatement(boolean var1) throws ParseException, StandardException {
      ArrayList var3 = new ArrayList();
      int var4 = 0;
      int var5 = 0;
      OrderByList var7 = null;
      ValueNode[] var8 = new ValueNode[2];
      boolean var9 = false;
      ResultSetNode var2 = this.queryExpression((ResultSetNode)null, 0);
      switch (this.jj_nt.kind) {
         case 200 -> var7 = this.orderByClause(var2);
         default -> this.jj_la1[19] = this.jj_gen;
      }

      var9 = this.offsetFetchFirstClause(var8);
      switch (this.jj_nt.kind) {
         case 144:
            this.jj_consume_token(144);
            var4 = this.forUpdateClause(var3);
            break;
         default:
            this.jj_la1[20] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 267 -> var5 = this.atIsolationLevel();
         default -> this.jj_la1[21] = this.jj_gen;
      }

      CursorNode var6 = new CursorNode("SELECT", var2, (String)null, var7, var8[0], var8[1], var9, var4, var4 == 1 ? null : (String[])var3.toArray(new String[var3.size()]), false, this.getContextManager());
      if (var1) {
         this.setUpAndLinkParameters();
      }

      if (var5 != 0) {
         this.getCompilerContext().setScanIsolationLevel(var5);
      }

      return var6;
   }

   public final StatementNode insertStatement() throws ParseException, StandardException {
      this.jj_consume_token(166);
      this.jj_consume_token(170);
      QueryTreeNode var2 = this.targetTable();
      StatementNode var1 = this.insertColumnsAndSource(var2);
      this.setUpAndLinkParameters();
      return var1;
   }

   public final QueryTreeNode targetTable() throws ParseException, StandardException {
      Object var1 = null;
      Object var2 = null;
      if (this.newInvocationFollows(1)) {
         JavaToSQLValueNode var4 = this.newInvocation();
         return new FromVTI((MethodCallNode)var4.getJavaValueNode(), (String)var2, (ResultColumnList)null, (Properties)null, this.getContextManager());
      } else if (this.jj_2_11(1)) {
         TableName var3 = this.qualifiedName(128);
         return var3;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final StatementNode preparableUpdateStatement() throws ParseException, StandardException {
      this.jj_consume_token(254);
      StatementNode var1 = this.updateBody();
      return var1;
   }

   public final boolean tableOrIndex() throws ParseException {
      switch (this.jj_nt.kind) {
         case 239:
            this.jj_consume_token(239);
            return true;
         case 400:
            this.jj_consume_token(400);
            return false;
         default:
            this.jj_la1[22] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode updateBody() throws ParseException, StandardException {
      String var2 = null;
      JavaToSQLValueNode var3 = null;
      Object var4 = null;
      ValueNode var5 = null;
      Object var6 = null;
      Properties var7 = null;
      Object var8 = null;
      if (this.newInvocationFollows(1)) {
         var3 = this.newInvocation();
         this.jj_consume_token(227);
         ResultColumnList var9 = this.setClauseList();
         switch (this.jj_nt.kind) {
            case 265:
               Token var14 = this.jj_consume_token(265);
               var5 = this.whereClause(var14);
               break;
            default:
               this.jj_la1[23] = this.jj_gen;
         }

         FromVTI var12 = new FromVTI((MethodCallNode)var3.getJavaValueNode(), (String)null, (ResultColumnList)null, (Properties)null, this.getContextManager());
         return this.getUpdateNode(var12, (TableName)var4, var9, var5);
      } else if (this.jj_2_13(1)) {
         TableName var11 = this.qualifiedName(128);
         if (this.getToken(1).kind != 227 && !this.derbyPropertiesListFollows()) {
            switch (this.jj_nt.kind) {
               case 72 -> this.jj_consume_token(72);
               default -> this.jj_la1[24] = this.jj_gen;
            }

            var2 = this.identifier(128, true);
         }

         switch (this.jj_nt.kind) {
            case 59:
               var7 = this.propertyList(false);
               this.jj_consume_token(64);
               break;
            default:
               this.jj_la1[25] = this.jj_gen;
         }

         ResultColumnList var1;
         this.jj_consume_token(227);
         var1 = this.setClauseList();
         label40:
         switch (this.jj_nt.kind) {
            case 265:
               Token var13 = this.jj_consume_token(265);
               if (this.jj_2_12(1)) {
                  var5 = this.whereClause(var13);
                  break;
               } else {
                  switch (this.jj_nt.kind) {
                     case 108:
                        var6 = this.currentOfClause(var2);
                        break label40;
                     default:
                        this.jj_la1[26] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                  }
               }
            default:
               this.jj_la1[27] = this.jj_gen;
         }

         if (var6 == null) {
            var6 = new FromBaseTable(var11, var2, 1, (ResultColumnList)null, this.getContextManager());
         }

         if (var7 != null) {
            ((FromBaseTable)var6).setTableProperties(var7);
         }

         return this.getUpdateNode((FromTable)var6, var11, var1, var5);
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final StatementNode callStatement() throws ParseException, StandardException {
      StatementNode var1;
      switch (this.jj_nt.kind) {
         case 361:
         case 472:
            var1 = this.bareCallStatement();
            break;
         case 451:
            this.jj_consume_token(451);
            var1 = this.bareCallStatement();
            this.jj_consume_token(452);
            break;
         default:
            this.jj_la1[28] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      return var1;
   }

   public final StatementNode bareCallStatement() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 361:
            this.jj_consume_token(361);
            ValueNode var7 = this.primaryExpression();
            if (var7 instanceof JavaToSQLValueNode && ((JavaToSQLValueNode)var7).getJavaValueNode() instanceof MethodCallNode) {
               CallStatementNode var4 = new CallStatementNode((JavaToSQLValueNode)var7, this.getContextManager());
               this.setUpAndLinkParameters();
               return var4;
            }

            throw StandardException.newException("42X74", new Object[0]);
         case 472:
            ParameterNode var1 = this.dynamicParameterSpecification();
            this.getCompilerContext().setReturnParameterFlag();
            this.jj_consume_token(467);
            this.jj_consume_token(361);
            ResultSetNode var3 = this.rowValueConstructor((ResultSetNode)null);
            ResultColumnList var5 = var3.getResultColumns();
            if (var5 != null && var5.size() <= 1) {
               ValueNode var2 = ((ResultColumn)var5.elementAt(0)).getExpression();
               if (var2 instanceof JavaToSQLValueNode && ((JavaToSQLValueNode)var2).getJavaValueNode() instanceof MethodCallNode) {
                  CursorNode var6 = new CursorNode("SELECT", var3, (String)null, (OrderByList)null, (ValueNode)null, (ValueNode)null, false, 1, (String[])null, false, this.getContextManager());
                  var1.setReturnOutputParam(var2);
                  this.setUpAndLinkParameters();
                  return var6;
               }

               throw StandardException.newException("42X74", new Object[0]);
            }

            throw StandardException.newException("42X74", new Object[0]);
         default:
            this.jj_la1[29] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode mergeStatement() throws ParseException, StandardException {
      this.jj_consume_token(309);
      this.jj_consume_token(170);
      FromTable var1 = this.tableFactor();
      this.jj_consume_token(257);
      FromTable var2 = this.tableFactor();
      ValueNode var3 = this.joinCondition();
      QueryTreeNodeVector var4 = this.matchingClauseList();
      this.checkVersion(230, "MERGE");
      this.setUpAndLinkParameters();
      return new MergeNode(var1, var2, var3, var4, this.getContextManager());
   }

   public final QueryTreeNodeVector matchingClauseList() throws ParseException, StandardException {
      QueryTreeNodeVector var1 = new QueryTreeNodeVector(MatchingClauseNode.class, this.getContextManager());
      this.matchingClause(var1);

      while(this.jj_2_14(1)) {
         this.matchingClause(var1);
      }

      return var1;
   }

   public final void matchingClause(QueryTreeNodeVector var1) throws ParseException, StandardException {
      ValueNode var2 = null;
      ResultColumnList var3 = null;
      ResultColumnList var4 = null;
      ResultColumnList var5 = new ResultColumnList(this.getContextManager());
      if (this.getToken(1).kind == 358 && this.getToken(2).kind != 190) {
         this.jj_consume_token(358);
         this.jj_consume_token(307);
         switch (this.jj_nt.kind) {
            case 69:
               this.jj_consume_token(69);
               var2 = this.valueExpression();
               break;
            default:
               this.jj_la1[30] = this.jj_gen;
         }

         this.jj_consume_token(349);
         switch (this.jj_nt.kind) {
            case 122:
               this.jj_consume_token(122);
               break;
            case 254:
               this.jj_consume_token(254);
               this.jj_consume_token(227);
               var3 = this.setClauseList();
               break;
            default:
               this.jj_la1[31] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }

         MatchingClauseNode var7;
         if (var3 != null) {
            var7 = MatchingClauseNode.makeUpdateClause(var2, var3, this.getContextManager());
         } else {
            var7 = MatchingClauseNode.makeDeleteClause(var2, this.getContextManager());
         }

         var1.addElement(var7);
      } else {
         if (this.getToken(1).kind != 358 || this.getToken(2).kind != 190) {
            this.jj_consume_token(-1);
            throw new ParseException();
         }

         this.jj_consume_token(358);
         this.jj_consume_token(190);
         this.jj_consume_token(307);
         switch (this.jj_nt.kind) {
            case 69:
               this.jj_consume_token(69);
               var2 = this.valueExpression();
               break;
            default:
               this.jj_la1[32] = this.jj_gen;
         }

         this.jj_consume_token(349);
         this.jj_consume_token(166);
         switch (this.jj_nt.kind) {
            case 453:
               this.jj_consume_token(453);
               var4 = this.insertColumnList();
               this.jj_consume_token(454);
               break;
            default:
               this.jj_la1[33] = this.jj_gen;
         }

         this.jj_consume_token(259);
         this.jj_consume_token(453);
         this.rowValueConstructorList(var5);
         this.jj_consume_token(454);
         MatchingClauseNode var6 = MatchingClauseNode.makeInsertClause(var2, var4, var5, this.getContextManager());
         var1.addElement(var6);
      }

   }

   public final ValueNode primaryExpression() throws ParseException, StandardException {
      ValueNode var1 = null;
      if (this.getToken(2).kind == 460 && this.getToken(4).kind == 453) {
         var1 = this.routineInvocation();
         return var1;
      } else if (this.jj_2_15(1)) {
         var1 = this.primaryExpressionXX();
         return var1;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final StatementNode savepointStatement() throws ParseException, StandardException {
      String var1 = null;
      Object[] var3 = new Object[3];
      byte var2;
      switch (this.jj_nt.kind) {
         case 220:
            this.jj_consume_token(220);
            switch (this.jj_nt.kind) {
               case 268 -> this.jj_consume_token(268);
               default -> this.jj_la1[35] = this.jj_gen;
            }

            this.jj_consume_token(243);
            this.jj_consume_token(333);
            if (this.jj_2_16(1)) {
               var1 = this.identifier(128, true);
            }

            var2 = 2;
            break;
         case 327:
            this.jj_consume_token(327);
            switch (this.jj_nt.kind) {
               case 243 -> this.jj_consume_token(243);
               default -> this.jj_la1[36] = this.jj_gen;
            }

            this.jj_consume_token(333);
            var1 = this.identifier(128, true);
            var2 = 3;
            break;
         case 333:
            this.jj_consume_token(333);
            var1 = this.identifier(128, true);

            while(true) {
               this.savepointStatementClause(var3);
               switch (this.jj_nt.kind) {
                  case 195:
                  case 252:
                     break;
                  default:
                     this.jj_la1[34] = this.jj_gen;
                     if (var3[2] == null) {
                        throw StandardException.newException("42X01", new Object[]{"MISSING ON ROLLBACK RETAIN CURSORS"});
                     }

                     var2 = 1;
                     return new SavepointNode(var1, var2, this.getContextManager());
               }
            }
         default:
            this.jj_la1[37] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      return new SavepointNode(var1, var2, this.getContextManager());
   }

   public final void savepointStatementClause(Object[] var1) throws ParseException, StandardException {
      int var2 = -1;
      switch (this.jj_nt.kind) {
         case 195:
            this.jj_consume_token(195);
            this.jj_consume_token(220);
            this.jj_consume_token(427);
            var2 = this.LocksOrCursors();
            break;
         case 252:
            this.jj_consume_token(252);
            var2 = 0;
            break;
         default:
            this.jj_la1[38] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      if (var2 != -1) {
         if (var1[var2] != null) {
            String var3 = SAVEPOINT_CLAUSE_NAMES[var2];
            throw StandardException.newException("42613", new Object[]{var3});
         }

         var1[var2] = Boolean.TRUE;
      }

   }

   public final int LocksOrCursors() throws ParseException {
      switch (this.jj_nt.kind) {
         case 304:
            this.jj_consume_token(304);
            return 1;
         case 389:
            this.jj_consume_token(389);
            return 2;
         default:
            this.jj_la1[39] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode globalTemporaryTableDeclaration() throws ParseException, StandardException {
      Object[] var3 = new Object[3];
      this.jj_consume_token(118);
      this.jj_consume_token(151);
      this.jj_consume_token(240);
      this.jj_consume_token(239);
      TableName var1 = this.qualifiedName(128);
      TableElementList var2 = this.tableElementList();

      do {
         this.declareTableClause(var3);
      } while(this.jj_2_17(1));

      if (var3[0] == null) {
         throw StandardException.newException("42X01", new Object[]{"MISSING NOT LOGGED"});
      } else {
         if (var3[1] == null) {
            var3[1] = Boolean.TRUE;
         }

         if (var3[2] == null) {
            var3[2] = Boolean.TRUE;
         }

         return new CreateTableNode(var1, var2, (Properties)null, (Boolean)var3[1], (Boolean)var3[2], this.getContextManager());
      }
   }

   public final void declareTableClause(Object[] var1) throws ParseException, StandardException {
      byte var2 = -1;
      Object var3 = null;
      Boolean var6;
      switch (this.jj_nt.kind) {
         case 190:
            this.jj_consume_token(190);
            this.jj_consume_token(306);
            var6 = Boolean.TRUE;
            var2 = 0;
            break;
         default:
            this.jj_la1[40] = this.jj_gen;
            if (this.getToken(1).kind == 195 && this.getToken(2).kind == 97) {
               this.jj_consume_token(195);
               this.jj_consume_token(97);
               var6 = this.onCommit();
               this.jj_consume_token(221);
               var2 = 1;
            } else {
               if (this.getToken(1).kind != 195 || this.getToken(2).kind != 220) {
                  this.jj_consume_token(-1);
                  throw new ParseException();
               }

               this.jj_consume_token(195);
               this.jj_consume_token(220);
               this.jj_consume_token(122);
               this.jj_consume_token(221);
               var6 = Boolean.TRUE;
               var2 = 2;
            }
      }

      if (var2 != -1) {
         if (var1[var2] != null) {
            String var4 = TEMPORARY_TABLE_CLAUSE_NAMES[var2];
            throw StandardException.newException("42613", new Object[]{var4});
         }

         var1[var2] = var6;
      }

   }

   public final Boolean onCommit() throws ParseException {
      switch (this.jj_nt.kind) {
         case 122:
            this.jj_consume_token(122);
            return Boolean.TRUE;
         case 207:
            this.jj_consume_token(207);
            return Boolean.FALSE;
         default:
            this.jj_la1[41] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final TableElementList tableElementList() throws ParseException, StandardException {
      TableElementList var1 = new TableElementList(this.getContextManager());
      this.jj_consume_token(453);
      this.tableElement(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.tableElement(var1);
               break;
            default:
               this.jj_la1[42] = this.jj_gen;
               this.jj_consume_token(454);
               return var1;
         }
      }
   }

   public final void tableElement(TableElementList var1) throws ParseException, StandardException {
      if (this.jj_2_18(1)) {
         TableElementNode var2 = this.columnDefinition(var1);
         var1.addTableElement(var2);
      } else {
         switch (this.jj_nt.kind) {
            case 91:
            case 100:
            case 145:
            case 208:
            case 252:
               TableElementNode var3 = this.tableConstraintDefinition();
               var1.addTableElement(var3);
               break;
            default:
               this.jj_la1[43] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }

   }

   public final TableElementNode columnDefinition(TableElementList var1) throws ParseException, StandardException {
      DataTypeDescriptor[] var2 = new DataTypeDescriptor[1];
      ValueNode var3 = null;
      long[] var5 = new long[5];
      String var4 = this.identifier(128, true);
      if (this.jj_2_19(1)) {
         var2[0] = this.dataTypeDDL();
      }

      switch (this.jj_nt.kind) {
         case 91:
         case 100:
         case 119:
         case 190:
         case 208:
         case 215:
         case 252:
         case 267:
         case 292:
            var3 = this.defaultAndConstraints(var2, var1, var4, var5);
            break;
         default:
            this.jj_la1[44] = this.jj_gen;
      }

      if (var5[2] == 0L) {
         var5 = null;
      }

      return new ColumnDefinitionNode(var4, var3, var2[0], var5, this.getContextManager());
   }

   public final ValueNode defaultAndConstraints(DataTypeDescriptor[] var1, TableElementList var2, String var3, long[] var4) throws ParseException, StandardException {
      ValueNode var5 = null;
      switch (this.jj_nt.kind) {
         case 91:
         case 100:
         case 190:
         case 208:
         case 215:
         case 252:
            this.columnConstraintDefinition(var1, var2, var3);

            while(true) {
               switch (this.jj_nt.kind) {
                  case 91:
                  case 100:
                  case 190:
                  case 208:
                  case 215:
                  case 252:
                     this.columnConstraintDefinition(var1, var2, var3);
                     break;
                  default:
                     this.jj_la1[45] = this.jj_gen;
                     switch (this.jj_nt.kind) {
                        case 119:
                        case 267:
                        case 292:
                           var5 = this.defaultClause(var4, var3);

                           while(true) {
                              switch (this.jj_nt.kind) {
                                 case 91:
                                 case 100:
                                 case 190:
                                 case 208:
                                 case 215:
                                 case 252:
                                    this.columnConstraintDefinition(var1, var2, var3);
                                    break;
                                 default:
                                    this.jj_la1[46] = this.jj_gen;
                                    return var5;
                              }
                           }
                        default:
                           this.jj_la1[47] = this.jj_gen;
                           return var5;
                     }
               }
            }
         case 119:
         case 267:
         case 292:
            var5 = this.defaultClause(var4, var3);

            while(true) {
               switch (this.jj_nt.kind) {
                  case 91:
                  case 100:
                  case 190:
                  case 208:
                  case 215:
                  case 252:
                     this.columnConstraintDefinition(var1, var2, var3);
                     break;
                  default:
                     this.jj_la1[48] = this.jj_gen;
                     return var5;
               }
            }
         default:
            this.jj_la1[49] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final DataTypeDescriptor dataTypeDDL() throws ParseException, StandardException {
      if (this.commonDatatypeName(false)) {
         DataTypeDescriptor var2 = this.dataTypeCommon();
         return var2;
      } else if (this.getToken(1).kind != 292) {
         DataTypeDescriptor var1 = this.javaType(new TableName[1]);
         return var1;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final TypeDescriptor catalogType() throws ParseException, StandardException {
      DataTypeDescriptor var1 = this.dataTypeDDL();
      return var1.getCatalogType();
   }

   public final DataTypeDescriptor dataTypeCast(TableName[] var1) throws ParseException, StandardException {
      if (this.commonDatatypeName(true)) {
         DataTypeDescriptor var3 = this.dataTypeCommon();
         return var3;
      } else if (this.jj_2_20(1)) {
         DataTypeDescriptor var2 = this.javaType(var1);
         return var2;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final DataTypeDescriptor dataTypeCommon() throws ParseException, StandardException {
      boolean var2 = false;
      DataTypeDescriptor var1;
      if (this.jj_2_21(1)) {
         if (this.getToken(2).kind == 300) {
            this.jj_consume_token(-1);
            throw new ParseException();
         }

         var1 = this.characterStringType();
      } else if (this.jj_2_22(1)) {
         if (this.getToken(3).kind == 300) {
            this.jj_consume_token(-1);
            throw new ParseException();
         }

         var1 = this.nationalCharacterStringType();
      } else if (this.jj_2_23(1)) {
         var1 = this.numericType();
      } else {
         switch (this.jj_nt.kind) {
            case 80:
            case 88:
            case 89:
            case 185:
            case 275:
            case 278:
            case 318:
               var1 = this.LOBType();
               break;
            case 285:
            case 350:
            case 351:
               var1 = this.datetimeType();
               break;
            case 360:
               this.jj_consume_token(360);
               if ((this.getCompilerContext().getReliability() & 1024) != 0) {
                  this.checkVersion(190, "BOOLEAN");
               }

               var1 = new DataTypeDescriptor(TypeId.BOOLEAN_ID, true);
               break;
            case 369:
               var1 = this.longType();
               break;
            case 377:
               var1 = this.XMLType();
               break;
            default:
               this.jj_la1[50] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }

      return var1;
   }

   public final DataTypeDescriptor characterStringType() throws ParseException, StandardException {
      int var1 = 1;
      Token var2 = null;
      int var3;
      switch (this.jj_nt.kind) {
         case 88:
         case 89:
            this.charOrCharacter();
            switch (this.jj_nt.kind) {
               case 262:
                  var2 = this.jj_consume_token(262);
                  var1 = this.charLength();
                  break;
               default:
                  this.jj_la1[52] = this.jj_gen;
                  switch (this.jj_nt.kind) {
                     case 453 -> var1 = this.charLength();
                     default -> this.jj_la1[51] = this.jj_gen;
                  }
            }

            var3 = var2 == null ? 1 : 12;
            break;
         case 261:
            this.jj_consume_token(261);
            var1 = this.charLength();
            var3 = 12;
            break;
         default:
            this.jj_la1[53] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      switch (this.jj_nt.kind) {
         case 144 -> var3 = this.forBitData(var3);
         default -> this.jj_la1[54] = this.jj_gen;
      }

      this.checkTypeLimits(var3, var1);
      DataTypeDescriptor var4 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(var3, var1);
      return var4;
   }

   public final void charOrCharacter() throws ParseException {
      switch (this.jj_nt.kind) {
         case 88:
            this.jj_consume_token(88);
            break;
         case 89:
            this.jj_consume_token(89);
            break;
         default:
            this.jj_la1[55] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

   }

   public final int charLength() throws ParseException, StandardException {
      this.jj_consume_token(453);
      int var1 = this.length();
      this.jj_consume_token(454);
      return var1;
   }

   public final int forBitData(int var1) throws ParseException {
      this.jj_consume_token(144);
      this.jj_consume_token(81);
      this.jj_consume_token(284);
      if (var1 == 1) {
         var1 = -2;
      } else if (var1 == 12) {
         var1 = -3;
      } else if (var1 == -1) {
         var1 = -4;
      }

      return var1;
   }

   public final DataTypeDescriptor nationalCharacterStringType() throws ParseException, StandardException {
      int var2 = 1;
      Object var3 = null;
      Token var4 = null;
      String var10;
      switch (this.jj_nt.kind) {
         case 185:
            this.jj_consume_token(185);
            this.charOrCharacter();
            switch (this.jj_nt.kind) {
               case 262:
                  var4 = this.jj_consume_token(262);
                  var2 = this.charLength();
                  break;
               default:
                  this.jj_la1[57] = this.jj_gen;
                  switch (this.jj_nt.kind) {
                     case 453 -> var2 = this.charLength();
                     default -> this.jj_la1[56] = this.jj_gen;
                  }
            }

            var10 = var4 == null ? "NATIONAL CHAR" : "NATIONAL CHAR VARYING";
            break;
         case 187:
            this.jj_consume_token(187);
            switch (this.jj_nt.kind) {
               case 262:
                  var4 = this.jj_consume_token(262);
                  var2 = this.charLength();
                  break;
               default:
                  this.jj_la1[59] = this.jj_gen;
                  switch (this.jj_nt.kind) {
                     case 453 -> var2 = this.charLength();
                     default -> this.jj_la1[58] = this.jj_gen;
                  }
            }

            var10 = var4 == null ? "NATIONAL CHAR" : "NATIONAL CHAR VARYING";
            break;
         case 411:
            this.jj_consume_token(411);
            var2 = this.charLength();
            var10 = "NATIONAL CHAR VARYING";
            break;
         default:
            this.jj_la1[60] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      throw StandardException.newException("0A000.S", new Object[]{var10});
   }

   public final DataTypeDescriptor LOBType() throws ParseException, StandardException {
      int var1 = Integer.MAX_VALUE;
      String var6;
      switch (this.jj_nt.kind) {
         case 80:
            this.jj_consume_token(80);
            this.jj_consume_token(300);
            this.jj_consume_token(322);
            switch (this.jj_nt.kind) {
               case 453 -> var1 = this.lengthAndModifier();
               default -> this.jj_la1[63] = this.jj_gen;
            }

            var6 = "BLOB";
            break;
         case 88:
         case 89:
            this.charOrCharacter();
            this.jj_consume_token(300);
            this.jj_consume_token(322);
            switch (this.jj_nt.kind) {
               case 453 -> var1 = this.lengthAndModifier();
               default -> this.jj_la1[64] = this.jj_gen;
            }

            var6 = "CLOB";
            break;
         case 185:
            this.jj_consume_token(185);
            this.jj_consume_token(89);
            this.jj_consume_token(300);
            this.jj_consume_token(322);
            var1 = this.lengthAndModifier();
            var6 = "NCLOB";
            throw StandardException.newException("0A000.S", new Object[]{var6});
         case 275:
            this.jj_consume_token(275);
            switch (this.jj_nt.kind) {
               case 453 -> var1 = this.lengthAndModifier();
               default -> this.jj_la1[61] = this.jj_gen;
            }

            var6 = "BLOB";
            break;
         case 278:
            this.jj_consume_token(278);
            switch (this.jj_nt.kind) {
               case 453 -> var1 = this.lengthAndModifier();
               default -> this.jj_la1[62] = this.jj_gen;
            }

            var6 = "CLOB";
            break;
         case 318:
            this.jj_consume_token(318);
            var1 = this.lengthAndModifier();
            var6 = "NCLOB";
            throw StandardException.newException("0A000.S", new Object[]{var6});
         default:
            this.jj_la1[65] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      DataTypeDescriptor var3 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(var6, var1);
      return var3;
   }

   public final DataTypeDescriptor numericType() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 116:
         case 117:
         case 167:
         case 168:
         case 193:
         case 228:
         case 368:
            DataTypeDescriptor var1 = this.exactNumericType();
            return var1;
         default:
            this.jj_la1[66] = this.jj_gen;
            if (this.jj_2_24(1)) {
               DataTypeDescriptor var2 = this.approximateNumericType();
               return var2;
            } else {
               this.jj_consume_token(-1);
               throw new ParseException();
            }
      }
   }

   public final DataTypeDescriptor exactNumericType() throws ParseException, StandardException {
      int var1 = 5;
      int var2 = 0;
      byte var3 = 3;
      String var4 = "DECIMAL";
      DataTypeDescriptor var6 = null;
      switch (this.jj_nt.kind) {
         case 116:
         case 117:
         case 193:
            switch (this.jj_nt.kind) {
               case 116:
                  this.jj_consume_token(116);
                  break;
               case 117:
                  this.jj_consume_token(117);
                  break;
               case 193:
                  this.jj_consume_token(193);
                  var3 = 2;
                  var4 = "NUMERIC";
                  break;
               default:
                  this.jj_la1[67] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }

            switch (this.jj_nt.kind) {
               case 453:
                  this.jj_consume_token(453);
                  var1 = this.precision();
                  switch (this.jj_nt.kind) {
                     case 458:
                        this.jj_consume_token(458);
                        var2 = this.scale();
                        break;
                     default:
                        this.jj_la1[68] = this.jj_gen;
                  }

                  this.jj_consume_token(454);
                  break;
               default:
                  this.jj_la1[69] = this.jj_gen;
            }

            if (var1 > 0 && var1 <= 31) {
               if (var2 >= 0 && var2 <= 31) {
                  if (var2 > var1) {
                     throw StandardException.newException("42Y43", new Object[]{String.valueOf(var2), String.valueOf(var1)});
                  }

                  int var5 = DataTypeUtilities.computeMaxWidth(var1, var2);
                  return this.getDataTypeServices(var3, var1, var2, var5);
               }

               throw StandardException.newException("42Y42", new Object[]{var4, String.valueOf(var2)});
            }

            throw StandardException.newException("42X48", new Object[]{var4, String.valueOf(var1)});
         case 167:
         case 168:
         case 228:
         case 368:
            var6 = this.exactIntegerType();
            return var6;
         default:
            this.jj_la1[70] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final DataTypeDescriptor exactIntegerType() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 167:
         case 168:
            switch (this.jj_nt.kind) {
               case 167:
                  this.jj_consume_token(167);
                  break;
               case 168:
                  this.jj_consume_token(168);
                  break;
               default:
                  this.jj_la1[71] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }

            return DataTypeDescriptor.getBuiltInDataTypeDescriptor(4);
         case 228:
            this.jj_consume_token(228);
            return DataTypeDescriptor.getBuiltInDataTypeDescriptor(5);
         case 368:
            this.jj_consume_token(368);
            return DataTypeDescriptor.getBuiltInDataTypeDescriptor(-5);
         default:
            this.jj_la1[72] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final DataTypeDescriptor approximateNumericType() throws ParseException, StandardException {
      byte var1 = 0;
      byte var2 = 0;
      byte var3 = 0;
      int var4 = -1;
      DataTypeDescriptor var5 = null;
      switch (this.jj_nt.kind) {
         case 143:
            this.jj_consume_token(143);
            switch (this.jj_nt.kind) {
               case 453:
                  this.jj_consume_token(453);
                  var4 = this.precision();
                  this.jj_consume_token(454);
                  break;
               default:
                  this.jj_la1[73] = this.jj_gen;
            }

            if (var4 == -1) {
               var4 = 52;
            }

            if (var4 > 0 && var4 <= 23) {
               var1 = 7;
               var4 = 23;
               var2 = 0;
               var3 = 4;
            } else {
               if (var4 <= 23 || var4 > 52) {
                  throw StandardException.newException("42X48", new Object[]{"FLOAT", String.valueOf(var4)});
               }

               var1 = 8;
               var4 = 52;
               var2 = 0;
               var3 = 8;
            }

            return this.getDataTypeServices(var1, var4, var2, var3);
         case 214:
            this.jj_consume_token(214);
            return DataTypeDescriptor.getBuiltInDataTypeDescriptor(7);
         default:
            this.jj_la1[74] = this.jj_gen;
            if (this.jj_2_25(1)) {
               var5 = this.doubleType();
               return var5;
            } else {
               this.jj_consume_token(-1);
               throw new ParseException();
            }
      }
   }

   public final DataTypeDescriptor doubleType() throws ParseException, StandardException {
      if (this.getToken(2).kind == 326) {
         this.jj_consume_token(128);
         this.jj_consume_token(326);
      } else {
         switch (this.jj_nt.kind) {
            case 128:
               this.jj_consume_token(128);
               break;
            default:
               this.jj_la1[75] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }

      return DataTypeDescriptor.getBuiltInDataTypeDescriptor(8);
   }

   public final DataTypeDescriptor longType() throws ParseException, StandardException {
      this.jj_consume_token(369);
      DataTypeDescriptor var1 = this.longSubType();
      return var1;
   }

   public final DataTypeDescriptor longSubType() throws ParseException, StandardException {
      int var1 = -1;
      switch (this.jj_nt.kind) {
         case 261:
            this.jj_consume_token(261);
            switch (this.jj_nt.kind) {
               case 144 -> var1 = this.forBitData(var1);
               default -> this.jj_la1[76] = this.jj_gen;
            }

            return DataTypeDescriptor.getBuiltInDataTypeDescriptor(var1);
         case 411:
            this.jj_consume_token(411);
            throw StandardException.newException("0A000.S", new Object[]{"LONG NVARCHAR"});
         default:
            this.jj_la1[77] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final DataTypeDescriptor XMLType() throws ParseException, StandardException {
      this.jj_consume_token(377);
      this.checkVersion(130, "XML");
      return DataTypeDescriptor.getBuiltInDataTypeDescriptor(2009);
   }

   public final void xmlDocOrContent() throws ParseException, StandardException {
      if (this.getToken(1).kind != 395 && this.getToken(1).kind != 387) {
         throw StandardException.newException("42Z72", new Object[]{"DOCUMENT", this.getToken(1).beginLine, this.getToken(1).beginColumn});
      } else if (this.getToken(1).kind == 387) {
         this.jj_consume_token(387);
         throw StandardException.newException("42Z74", new Object[]{"CONTENT"});
      } else if (this.getToken(1).kind == 395) {
         this.jj_consume_token(395);
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final DataTypeDescriptor javaType(TableName[] var1) throws ParseException, StandardException {
      TableName var2 = this.qualifiedName(128);
      var1[0] = var2;
      return this.getJavaClassDataTypeDescriptor(var2);
   }

   public final String javaDSL() throws ParseException {
      String var1 = this.caseSensitiveIdentifierPlusReservedWords();

      while(true) {
         switch (this.jj_nt.kind) {
            case 460:
               var1 = this.javaDSLNameExtender(var1);
               break;
            default:
               this.jj_la1[78] = this.jj_gen;
               return var1;
         }
      }
   }

   public final String javaClassName() throws ParseException {
      String var1 = this.javaDSL();
      return var1;
   }

   public final String javaDSLNameExtender(String var1) throws ParseException {
      this.jj_consume_token(460);
      String var2 = this.caseSensitiveIdentifierPlusReservedWords();
      return var1 + "." + var2;
   }

   public final int lengthAndModifier() throws ParseException, StandardException {
      Token var1;
      Token var2;
      var2 = null;
      this.jj_consume_token(453);
      label46:
      switch (this.jj_nt.kind) {
         case 487:
            var1 = this.jj_consume_token(487);
            switch (this.jj_nt.kind) {
               case 480 -> var2 = this.jj_consume_token(480);
break label46;
               default -> this.jj_la1[79] = this.jj_gen;
break label46;
            }
         case 489:
            var1 = this.jj_consume_token(489);
            break;
         default:
            this.jj_la1[80] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      this.jj_consume_token(454);
      String var10000 = var1.image;
      String var3 = var10000 + (var2 == null ? "" : var2.image);

      try {
         char var4 = var3.charAt(var3.length() - 1);
         String var5 = var3.substring(0, var3.length() - 1);
         long var6;
         switch (var4) {
            case 'G':
            case 'g':
               var6 = 1073741824L;
               break;
            case 'K':
            case 'k':
               var6 = 1024L;
               break;
            case 'M':
            case 'm':
               var6 = 1048576L;
               break;
            default:
               var6 = 1L;
               var5 = var3;
         }

         long var8 = Long.parseLong(var5) * var6;
         if (var8 > 0L && var8 <= 2147483647L) {
            return (int)var8;
         }

         if (var6 != 1L && var8 == 2147483648L) {
            return Integer.MAX_VALUE;
         }
      } catch (NumberFormatException var10) {
      }

      throw StandardException.newException("42X44", new Object[]{var3});
   }

   public final int length() throws ParseException, StandardException {
      Token var1 = this.jj_consume_token(487);

      try {
         int var2 = Integer.parseInt(var1.image);
         if (var2 > 0) {
            return var2;
         }
      } catch (NumberFormatException var4) {
      }

      throw StandardException.newException("42X44", new Object[]{var1.image});
   }

   public final long exactNumber() throws ParseException, StandardException {
      String var2 = "";
      switch (this.jj_nt.kind) {
         case 457:
         case 459:
            var2 = this.sign();
            break;
         default:
            this.jj_la1[81] = this.jj_gen;
      }

      Token var1 = this.jj_consume_token(487);

      try {
         return var2.equals("-") ? Long.parseLong("-" + var1.image) : Long.parseLong(var1.image);
      } catch (NumberFormatException var4) {
         throw StandardException.newException("42X49", new Object[]{var1.image});
      }
   }

   public final int precision() throws ParseException, StandardException {
      int var1 = this.uint_value();
      return var1;
   }

   public final int uint_value() throws ParseException, StandardException {
      Token var1 = this.jj_consume_token(487);

      try {
         return Integer.parseInt(var1.image);
      } catch (NumberFormatException var3) {
         throw StandardException.newException("42X49", new Object[]{var1.image});
      }
   }

   public final int scale() throws ParseException, StandardException {
      int var1 = this.uint_value();
      return var1;
   }

   public final DataTypeDescriptor datetimeType() throws ParseException, StandardException {
      Object var1 = null;
      boolean var2 = true;
      switch (this.jj_nt.kind) {
         case 285:
            this.jj_consume_token(285);
            return DataTypeDescriptor.getBuiltInDataTypeDescriptor(91);
         case 350:
            this.jj_consume_token(350);
            return DataTypeDescriptor.getBuiltInDataTypeDescriptor(92);
         case 351:
            this.jj_consume_token(351);
            return DataTypeDescriptor.getBuiltInDataTypeDescriptor(93);
         default:
            this.jj_la1[82] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final TableName qualifiedName(int var1) throws ParseException, StandardException {
      String var2 = null;
      Object var4 = null;
      String var5 = null;
      Object var6 = null;
      String var7 = this.identifier(128, false);
      if (this.getToken(1).kind == 460 && this.getToken(2).kind != 455) {
         this.jj_consume_token(460);
         var5 = this.identifier(128, false);
      }

      String var3;
      Token var8;
      if (var5 == null) {
         var3 = var7;
         var8 = this.lastIdentifierToken;
      } else {
         var2 = var7;
         var3 = var5;
         var8 = this.nextToLastIdentifierToken;
      }

      IdUtil.checkIdentifierLengthLimit(var3, var1);
      if (var2 != null) {
         IdUtil.checkIdentifierLengthLimit(var2, 128);
      }

      return new TableName(var2, var3, var8.beginOffset, this.lastIdentifierToken.endOffset, this.getContextManager());
   }

   public final ResultSetNode queryExpression(ResultSetNode var1, int var2) throws ParseException, StandardException {
      ResultSetNode var3 = this.nonJoinQueryTerm(var1, var2);
      switch (this.jj_nt.kind) {
         case 134:
         case 251:
            var3 = this.unionOrExcept(var3);
            break;
         default:
            this.jj_la1[83] = this.jj_gen;
      }

      return var3;
   }

   public final ResultSetNode unionOrExcept(ResultSetNode var1) throws ParseException, StandardException {
      Token var3 = null;
      switch (this.jj_nt.kind) {
         case 134:
            this.jj_consume_token(134);
            label46:
            switch (this.jj_nt.kind) {
               case 66:
               case 127:
                  switch (this.jj_nt.kind) {
                     case 66:
                        var3 = this.jj_consume_token(66);
                        break label46;
                     case 127:
                        this.jj_consume_token(127);
                        break label46;
                     default:
                        this.jj_la1[86] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                  }
               default:
                  this.jj_la1[87] = this.jj_gen;
            }

            ResultSetNode var4 = this.queryExpression(var1, var3 != null ? 4 : 3);
            if (var3 != null && var3.kind == 127) {
               this.forbidNextValueFor();
            }

            return var4;
         case 251:
            this.jj_consume_token(251);
            label38:
            switch (this.jj_nt.kind) {
               case 66:
               case 127:
                  switch (this.jj_nt.kind) {
                     case 66:
                        var3 = this.jj_consume_token(66);
                        break label38;
                     case 127:
                        this.jj_consume_token(127);
                        break label38;
                     default:
                        this.jj_la1[84] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                  }
               default:
                  this.jj_la1[85] = this.jj_gen;
            }

            ResultSetNode var2 = this.queryExpression(var1, var3 != null ? 2 : 1);
            if (var3 != null && var3.kind == 127) {
               this.forbidNextValueFor();
            }

            return var2;
         default:
            this.jj_la1[88] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ResultSetNode nonJoinQueryTerm(ResultSetNode var1, int var2) throws ParseException, StandardException {
      ResultSetNode var3 = this.nonJoinQueryPrimary();
      switch (this.jj_nt.kind) {
         case 169 -> var3 = this.intersect(var3);
         default -> this.jj_la1[89] = this.jj_gen;
      }

      switch (var2) {
         case 0 -> {
            return var3;
         }
         case 1 -> {
            return new UnionNode(var1, var3, false, false, (Properties)null, this.getContextManager());
         }
         case 2 -> {
            return new UnionNode(var1, var3, true, false, (Properties)null, this.getContextManager());
         }
         case 3 -> {
            return new IntersectOrExceptNode(2, var1, var3, false, (Properties)null, this.getContextManager());
         }
         case 4 -> {
            return new IntersectOrExceptNode(2, var1, var3, true, (Properties)null, this.getContextManager());
         }
         case 5 -> {
            return new IntersectOrExceptNode(1, var1, var3, false, (Properties)null, this.getContextManager());
         }
         case 6 -> {
            return new IntersectOrExceptNode(1, var1, var3, true, (Properties)null, this.getContextManager());
         }
         default -> {
            return null;
         }
      }
   }

   public final ResultSetNode intersect(ResultSetNode var1) throws ParseException, StandardException {
      Token var3;
      var3 = null;
      this.jj_consume_token(169);
      label23:
      switch (this.jj_nt.kind) {
         case 66:
         case 127:
            switch (this.jj_nt.kind) {
               case 66:
                  var3 = this.jj_consume_token(66);
                  break label23;
               case 127:
                  this.jj_consume_token(127);
                  break label23;
               default:
                  this.jj_la1[90] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[91] = this.jj_gen;
      }

      ResultSetNode var2 = this.nonJoinQueryTerm(var1, var3 != null ? 6 : 5);
      if (var3 != null && var3.kind == 127) {
         this.forbidNextValueFor();
      }

      return var2;
   }

   public final ResultSetNode nonJoinQueryPrimary() throws ParseException, StandardException {
      boolean var2 = false;
      OrderByList var3 = null;
      ValueNode[] var4 = new ValueNode[2];
      switch (this.jj_nt.kind) {
         case 225:
         case 259:
            ResultSetNode var6 = this.simpleTable();
            return var6;
         case 453:
            this.jj_consume_token(453);
            ResultSetNode var1 = this.queryExpression((ResultSetNode)null, 0);
            switch (this.jj_nt.kind) {
               case 200 -> var3 = this.orderByClause(var1);
               default -> this.jj_la1[92] = this.jj_gen;
            }

            var2 = this.offsetFetchFirstClause(var4);
            this.jj_consume_token(454);
            if (hasQueryExpressionSuffix(var3, var4)) {
               if (!(var1 instanceof SelectNode) && !(var1 instanceof UnionNode) && !(var1 instanceof RowResultSetNode)) {
                  String var5;
                  if (var3 != null) {
                     var5 = "ORDER BY";
                  } else if (var2) {
                     var5 = "LIMIT";
                  } else if (var4[0] != null) {
                     var5 = "OFFSET";
                  } else {
                     var5 = "FETCH";
                  }

                  throw StandardException.newException("42X01", new Object[]{var5});
               }

               var1.pushQueryExpressionSuffix();
               var1.pushOrderByList(var3);
               var1.pushOffsetFetchFirst(var4[0], var4[1], var2);
            }

            return var1;
         default:
            this.jj_la1[93] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ResultSetNode simpleTable() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 225:
            ResultSetNode var2 = this.querySpecification();
            return var2;
         case 259:
            ResultSetNode var1 = this.tableValueConstructor();
            return var1;
         default:
            this.jj_la1[94] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ResultSetNode querySpecification() throws ParseException, StandardException {
      boolean var3 = false;
      this.jj_consume_token(225);
      if (this.jj_2_26(1)) {
         var3 = this.setQuantifier();
      }

      ResultColumnList var1 = this.selectList();
      SelectNode var2 = this.tableExpression(var1);
      if (var3) {
         var2.makeDistinct();
      }

      return var2;
   }

   public final boolean setQuantifier() throws ParseException {
      if (this.getToken(1).kind == 127 && this.getToken(2).kind != 460 && this.getToken(2).kind != 463) {
         this.jj_consume_token(127);
         this.forbidNextValueFor();
         return true;
      } else if (this.getToken(1).kind == 66 && this.getToken(2).kind != 460 && this.getToken(2).kind != 463) {
         this.jj_consume_token(66);
         return false;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final ResultColumnList selectList() throws ParseException, StandardException {
      ResultColumnList var2 = new ResultColumnList(this.getContextManager());
      switch (this.jj_nt.kind) {
         case 455:
            this.jj_consume_token(455);
            AllResultColumn var1 = new AllResultColumn((TableName)null, this.getContextManager());
            var2.addResultColumn(var1);
            return var2;
         default:
            this.jj_la1[95] = this.jj_gen;
            if (this.jj_2_27(1)) {
               this.selectColumnList(var2);
               return var2;
            } else {
               this.jj_consume_token(-1);
               throw new ParseException();
            }
      }
   }

   public final void selectColumnList(ResultColumnList var1) throws ParseException, StandardException {
      this.selectSublist(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.selectSublist(var1);
               break;
            default:
               this.jj_la1[96] = this.jj_gen;
               return;
         }
      }
   }

   public final void selectSublist(ResultColumnList var1) throws ParseException, StandardException {
      if (this.getToken(2).kind != 460 || this.getToken(3).kind != 455 && (this.getToken(4).kind != 460 || this.getToken(5).kind != 455)) {
         if (!this.jj_2_28(1)) {
            this.jj_consume_token(-1);
            throw new ParseException();
         }

         ResultColumn var2 = this.derivedColumn(var1);
         var1.addResultColumn(var2);
      } else {
         TableName var4 = this.qualifiedName(128);
         this.jj_consume_token(460);
         this.jj_consume_token(455);
         AllResultColumn var3 = new AllResultColumn(var4, this.getContextManager());
         var1.addResultColumn(var3);
      }

   }

   public final ResultColumn derivedColumn(ResultColumnList var1) throws ParseException, StandardException {
      String var3 = null;
      ValueNode var2 = this.valueExpression();
      if (this.jj_2_29(1)) {
         var3 = this.asClause();
      }

      if (var3 == null && var2 instanceof ColumnReference) {
         var3 = ((ColumnReference)var2).getColumnName();
      }

      return new ResultColumn(var3, var2, this.getContextManager());
   }

   public final String asClause() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 72 -> this.jj_consume_token(72);
         default -> this.jj_la1[97] = this.jj_gen;
      }

      String var1 = this.identifier(128, true);
      return var1;
   }

   public final ValueNode valueExpression() throws ParseException, StandardException {
      ValueNode var1 = this.orExpression((ValueNode)null);

      while(true) {
         switch (this.jj_nt.kind) {
            case 199:
               this.jj_consume_token(199);
               var1 = this.orExpression(var1);
               break;
            default:
               this.jj_la1[98] = this.jj_gen;
               return var1;
         }
      }
   }

   public final ValueNode orExpression(ValueNode var1) throws ParseException, StandardException {
      ValueNode var2 = this.andExpression((ValueNode)null);

      while(true) {
         switch (this.jj_nt.kind) {
            case 69:
               this.jj_consume_token(69);
               var2 = this.andExpression(var2);
               break;
            default:
               this.jj_la1[99] = this.jj_gen;
               return (ValueNode)(var1 == null ? var2 : new OrNode(var1, var2, this.getContextManager()));
         }
      }
   }

   public final ValueNode andExpression(ValueNode var1) throws ParseException, StandardException {
      Token var2 = null;
      if (this.getToken(1).kind == 190 && this.getToken(2).kind != 460 && this.getToken(2).kind != 463) {
         var2 = this.jj_consume_token(190);
      }

      Object var3 = this.isSearchCondition();
      if (var2 != null) {
         var3 = new NotNode((ValueNode)var3, this.getContextManager());
      }

      if (var1 != null) {
         var3 = new AndNode(var1, (ValueNode)var3, this.getContextManager());
      }

      return (ValueNode)var3;
   }

   public final ValueNode isSearchCondition() throws ParseException, StandardException {
      ValueNode var1 = this.booleanPrimary();
      return var1;
   }

   public final ValueNode booleanPrimary() throws ParseException, StandardException {
      ValueNode var1 = this.predicate();
      return var1;
   }

   public final ValueNode predicate() throws ParseException, StandardException {
      Object var1;
      if (this.jj_2_30(1)) {
         var1 = this.additiveExpression((ValueNode)null, 0);
      } else {
         switch (this.jj_nt.kind) {
            case 138:
               var1 = this.existsExpression();
               break;
            default:
               this.jj_la1[100] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }

      while(this.remainingPredicateFollows()) {
         var1 = this.remainingPredicate((ValueNode)var1);
      }

      return (ValueNode)var1;
   }

   public final ValueNode remainingPredicate(ValueNode var1) throws ParseException, StandardException {
      Token var2 = null;
      switch (this.jj_nt.kind) {
         case 79:
         case 160:
         case 171:
         case 178:
         case 190:
            switch (this.jj_nt.kind) {
               case 190 -> var2 = this.jj_consume_token(190);
               default -> this.jj_la1[101] = this.jj_gen;
            }

            var1 = this.remainingNegatablePredicate(var1);
            if (var2 != null) {
               var1 = new NotNode(var1, this.getContextManager());
            }

            return var1;
         case 465:
         case 466:
         case 467:
         case 468:
         case 469:
         case 470:
         case 471:
            var1 = this.remainingNonNegatablePredicate(var1);
            return var1;
         default:
            this.jj_la1[102] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode remainingNonNegatablePredicate(ValueNode var1) throws ParseException, StandardException {
      Object var4 = null;
      Object var5 = null;
      int var2 = this.compOp();
      if ((this.getToken(1).kind == 66 || this.getToken(1).kind == 70 || this.getToken(1).kind == 229) && this.getToken(2).kind == 453) {
         var2 = this.quantifier(var2);
         this.jj_consume_token(453);
         var1 = this.tableSubquery(var2, var1);
         this.jj_consume_token(454);
      } else {
         if (!this.jj_2_31(1)) {
            this.jj_consume_token(-1);
            throw new ParseException();
         }

         var1 = this.additiveExpression(var1, var2);
      }

      return var1;
   }

   public final ValueNode remainingNegatablePredicate(ValueNode var1) throws ParseException, StandardException {
      ValueNode var2 = null;
      ValueNode var6 = null;
      Token var7 = null;
      switch (this.jj_nt.kind) {
         case 79:
            this.jj_consume_token(79);
            ValueNode var4 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(69);
            ValueNode var5 = this.additiveExpression((ValueNode)null, 0);
            ValueNodeList var8 = new ValueNodeList(this.getContextManager());
            var8.addElement(var4);
            var8.addElement(var5);
            var2 = new BetweenOperatorNode(var1, var8, this.getContextManager());
            return var2;
         case 160:
            this.jj_consume_token(160);
            var2 = this.inPredicateValue(var1);
            return var2;
         case 171:
            this.jj_consume_token(171);
            switch (this.jj_nt.kind) {
               case 190 -> var7 = this.jj_consume_token(190);
               default -> this.jj_la1[103] = this.jj_gen;
            }

            this.jj_consume_token(191);
            return new IsNullNode(var1, var7 != null, this.getContextManager());
         case 178:
            this.jj_consume_token(178);
            ValueNode var3 = this.additiveExpression((ValueNode)null, 0);
            if (this.jj_2_32(1)) {
               switch (this.jj_nt.kind) {
                  case 133:
                     this.jj_consume_token(133);
                     var6 = this.additiveExpression((ValueNode)null, 0);
                     break;
                  default:
                     this.jj_la1[104] = this.jj_gen;
                     if (this.getToken(1).kind != 451 || this.getToken(2).kind == 303) {
                        this.jj_consume_token(-1);
                        throw new ParseException();
                     }

                     this.jj_consume_token(451);
                     this.jj_consume_token(133);
                     var6 = this.additiveExpression((ValueNode)null, 0);
                     this.jj_consume_token(452);
               }
            }

            var2 = new LikeEscapeOperatorNode(var1, var3, var6, this.getContextManager());
            return var2;
         default:
            this.jj_la1[105] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final int compOp() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 465:
            this.jj_consume_token(465);
            return 10;
         case 466:
            this.jj_consume_token(466);
            return 11;
         case 467:
            this.jj_consume_token(467);
            return 6;
         case 468:
            this.jj_consume_token(468);
            return 7;
         case 469:
            this.jj_consume_token(469);
            return 7;
         case 470:
            this.jj_consume_token(470);
            return 8;
         case 471:
            this.jj_consume_token(471);
            return 9;
         default:
            this.jj_la1[106] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode additiveExpression(ValueNode var1, int var2) throws ParseException, StandardException {
      ValueNode var3 = this.multiplicativeExpression((ValueNode)null, 0);

      while(true) {
         switch (this.jj_nt.kind) {
            case 457:
            case 459:
               int var4 = this.additiveOperator();
               var3 = this.multiplicativeExpression(var3, var4);
               break;
            default:
               this.jj_la1[107] = this.jj_gen;
               if (var1 == null) {
                  return var3;
               } else {
                  byte var5;
                  switch (var2) {
                     case 6 -> var5 = 0;
                     case 7 -> var5 = 5;
                     case 8 -> var5 = 2;
                     case 9 -> var5 = 1;
                     case 10 -> var5 = 4;
                     case 11 -> var5 = 3;
                     default -> var5 = -1;
                  }

                  return new BinaryRelationalOperatorNode(var5, var1, var3, false, this.getContextManager());
               }
         }
      }
   }

   public final int additiveOperator() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 457:
            Token var2 = this.jj_consume_token(457);
            return 1;
         case 459:
            Token var1 = this.jj_consume_token(459);
            return 2;
         default:
            this.jj_la1[108] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode multiplicativeExpression(ValueNode var1, int var2) throws ParseException, StandardException {
      ValueNode var3 = this.unaryExpression((ValueNode)null, 0);

      while(true) {
         switch (this.jj_nt.kind) {
            case 455:
            case 461:
            case 477:
               int var4 = this.multiplicativeOperator();
               var3 = this.unaryExpression(var3, var4);
               break;
            default:
               this.jj_la1[109] = this.jj_gen;
               if (var1 == null) {
                  return var3;
               } else {
                  switch (var2) {
                     case 1 -> {
                        return new BinaryArithmeticOperatorNode(2, var1, var3, this.getContextManager());
                     }
                     case 2 -> {
                        return new BinaryArithmeticOperatorNode(1, var1, var3, this.getContextManager());
                     }
                     default -> {
                        return null;
                     }
                  }
               }
         }
      }
   }

   public final int multiplicativeOperator() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 455:
            this.jj_consume_token(455);
            return 3;
         case 461:
            this.jj_consume_token(461);
            return 4;
         case 477:
            this.jj_consume_token(477);
            return 5;
         default:
            this.jj_la1[110] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode unaryExpression(ValueNode var1, int var2) throws ParseException, StandardException {
      String var4 = null;
      int var5;
      int var6;
      if (((var5 = this.getToken(1).kind) == 457 || var5 == 459) && (var6 = this.getToken(2).kind) != 487 && var6 != 492) {
         var4 = this.sign();
      }

      Object var3 = this.primaryExpression();
      if ("-".equals(var4)) {
         var3 = new UnaryArithmeticOperatorNode((ValueNode)var3, 1, this.getContextManager());
      } else if ("+".equals(var4)) {
         var3 = new UnaryArithmeticOperatorNode((ValueNode)var3, 0, this.getContextManager());
      }

      return this.multOp(var1, (ValueNode)var3, var2);
   }

   public final String sign() throws ParseException {
      switch (this.jj_nt.kind) {
         case 457:
            Token var2 = this.jj_consume_token(457);
            return var2.image;
         case 459:
            Token var1 = this.jj_consume_token(459);
            return var1.image;
         default:
            this.jj_la1[111] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode primaryExpressionXX() throws ParseException, StandardException {
      ValueNode var1;
      for(var1 = this.primary(); this.jj_2_33(1); var1 = this.nonStaticMethodCallOrFieldAccess(var1)) {
      }

      return var1;
   }

   public final ValueNode nonStaticMethodCallOrFieldAccess(ValueNode var1) throws ParseException, StandardException {
      ValueNode var2 = this.nonStaticMethodInvocation(var1);
      return var2;
   }

   public final ValueNode nonStaticMethodInvocation(ValueNode var1) throws ParseException, StandardException {
      ArrayList var2 = new ArrayList();
      if (this.getToken(3).kind == 453) {
         switch (this.jj_nt.kind) {
            case 460:
               this.jj_consume_token(460);
               break;
            case 478:
               this.jj_consume_token(478);
               break;
            default:
               this.jj_la1[112] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }

         MethodCallNode var4 = this.methodName(var1);
         this.methodCallParameterList(var2);
         if (var1 instanceof ParameterNode) {
            throw StandardException.newException("42X54", new Object[]{var4.getMethodName()});
         } else {
            var4.addParms(var2);
            return new JavaToSQLValueNode(var4, this.getContextManager());
         }
      } else {
         switch (this.jj_nt.kind) {
            case 460:
               this.jj_consume_token(460);
               MethodCallNode var3 = this.methodName(var1);
               if (var1 instanceof ParameterNode) {
                  throw StandardException.newException("42X54", new Object[]{var3.getMethodName()});
               }

               var3.addParms(var2);
               return new JavaToSQLValueNode(var3, this.getContextManager());
            default:
               this.jj_la1[113] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final MethodCallNode methodName(ValueNode var1) throws ParseException, StandardException {
      String var2 = this.caseSensitiveIdentifierPlusReservedWords();
      return new NonStaticMethodCallNode(var2, var1, this.getContextManager());
   }

   public final MethodCallNode staticMethodName(String var1) throws ParseException, StandardException {
      String var2 = this.caseSensitiveIdentifierPlusReservedWords();
      return new StaticMethodCallNode(var2, var1, this.getContextManager());
   }

   public final void methodParameter(List var1) throws ParseException, StandardException {
      if (this.jj_2_34(1)) {
         ValueNode var2 = this.valueExpression();
         var1.add(var2);
      } else {
         switch (this.jj_nt.kind) {
            case 191:
               ValueNode var3 = this.nullSpecification();
               var1.add(var3);
               break;
            default:
               this.jj_la1[114] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }

   }

   public final ValueNode primary() throws ParseException, StandardException {
      if (this.javaClassFollows()) {
         ValueNode var3 = this.staticClassReference();
         return var3;
      } else if (this.jj_2_35(1)) {
         ValueNode var2 = this.valueExpressionPrimary();
         return var2;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final ValueNode staticClassReference() throws ParseException, StandardException {
      String var1 = this.javaClass();
      this.jj_consume_token(463);
      ValueNode var2 = this.staticClassReferenceType(var1);
      return var2;
   }

   public final ValueNode staticClassReferenceType(String var1) throws ParseException, StandardException {
      if (this.getToken(2).kind == 453) {
         ValueNode var3 = this.staticMethodInvocation(var1);
         return var3;
      } else if (this.jj_2_36(1)) {
         ValueNode var2 = this.staticClassFieldReference(var1);
         return var2;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final ValueNode staticClassFieldReference(String var1) throws ParseException, StandardException {
      Object var2 = null;
      String var3 = this.caseSensitiveIdentifierPlusReservedWords();
      return new JavaToSQLValueNode(new StaticClassFieldReferenceNode(var1, var3, this.nextToLastTokenDelimitedIdentifier, this.getContextManager()), this.getContextManager());
   }

   public final int nonSecondDatetimeField() throws ParseException {
      switch (this.jj_nt.kind) {
         case 157:
            this.jj_consume_token(157);
            return 3;
         case 183:
            this.jj_consume_token(183);
            return 4;
         case 270:
            this.jj_consume_token(270);
            return 0;
         case 286:
            this.jj_consume_token(286);
            return 2;
         case 314:
            this.jj_consume_token(314);
            return 1;
         default:
            this.jj_la1[115] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode escapedValueFunction() throws ParseException, StandardException {
      ValueNode var5 = null;
      if (this.jj_2_37(1)) {
         ValueNode var9 = this.miscBuiltinsCore(true);
         return var9;
      } else {
         switch (this.jj_nt.kind) {
            case 112:
            case 226:
            case 256:
               ValueNode var7 = this.userNode();
               this.jj_consume_token(453);
               this.jj_consume_token(454);
               return var7;
            case 235:
               this.jj_consume_token(235);
               this.jj_consume_token(453);
               ValueNode var6 = this.additiveExpression((ValueNode)null, 0);
               this.jj_consume_token(458);
               ValueNode var4 = this.additiveExpression((ValueNode)null, 0);
               switch (this.jj_nt.kind) {
                  case 458:
                     this.jj_consume_token(458);
                     var5 = this.additiveExpression((ValueNode)null, 0);
                     break;
                  default:
                     this.jj_la1[116] = this.jj_gen;
               }

               this.jj_consume_token(454);
               return this.getSubstringNode(var6, var4, var5);
            case 281:
               this.jj_consume_token(281);
               this.jj_consume_token(453);
               ValueNode var2 = this.additiveExpression((ValueNode)null, 0);
               this.jj_consume_token(458);
               ValueNode var3 = this.additiveExpression((ValueNode)null, 0);
               this.jj_consume_token(454);
               return new ConcatenationOperatorNode(var2, var3, this.getContextManager());
            case 352:
            case 353:
               ValueNode var1 = this.timestampArithmeticFuncion();
               return var1;
            case 362:
               this.jj_consume_token(362);
               this.jj_consume_token(453);
               this.jj_consume_token(454);
               return new CurrentDatetimeOperatorNode(0, this.getContextManager());
            case 364:
               this.jj_consume_token(364);
               this.jj_consume_token(453);
               this.jj_consume_token(454);
               return new CurrentDatetimeOperatorNode(1, this.getContextManager());
            default:
               this.jj_la1[117] = this.jj_gen;
               if (this.getEscapedSYSFUN(this.getToken(1).image) != null) {
                  ValueNode var8 = this.escapedSYSFUNFunction();
                  return var8;
               } else {
                  this.jj_consume_token(-1);
                  throw new ParseException();
               }
         }
      }
   }

   public final ValueNode escapedSYSFUNFunction() throws ParseException, StandardException {
      ArrayList var1 = new ArrayList();
      Token var2 = this.jj_consume_token(480);
      this.methodCallParameterList(var1);
      String var3 = this.getEscapedSYSFUN(var2.image);
      TableName var4 = new TableName("SYSFUN", var3, this.getContextManager());
      StaticMethodCallNode var5 = new StaticMethodCallNode(var4, (String)null, this.getContextManager());
      ((MethodCallNode)var5).addParms(var1);
      return new JavaToSQLValueNode(var5, this.getContextManager());
   }

   public final ValueNode timestampArithmeticFuncion() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 352:
            this.jj_consume_token(352);
            this.jj_consume_token(453);
            ValueNode var5 = this.jdbcIntervalType();
            this.jj_consume_token(458);
            ValueNode var4 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(458);
            ValueNode var6 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return new TernaryOperatorNode(var6, var5, var4, 4, -1, this.getContextManager());
         case 353:
            this.jj_consume_token(353);
            this.jj_consume_token(453);
            ValueNode var1 = this.jdbcIntervalType();
            this.jj_consume_token(458);
            ValueNode var2 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(458);
            ValueNode var3 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return new TernaryOperatorNode(var3, var1, var2, 5, -1, this.getContextManager());
         default:
            this.jj_la1[118] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode jdbcIntervalType() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 337:
            this.jj_consume_token(337);
            return this.getJdbcIntervalNode(0);
         case 338:
            this.jj_consume_token(338);
            return this.getJdbcIntervalNode(1);
         case 339:
            this.jj_consume_token(339);
            return this.getJdbcIntervalNode(2);
         case 340:
            this.jj_consume_token(340);
            return this.getJdbcIntervalNode(3);
         case 341:
            this.jj_consume_token(341);
            return this.getJdbcIntervalNode(4);
         case 342:
            this.jj_consume_token(342);
            return this.getJdbcIntervalNode(5);
         case 343:
            this.jj_consume_token(343);
            return this.getJdbcIntervalNode(6);
         case 344:
            this.jj_consume_token(344);
            return this.getJdbcIntervalNode(7);
         case 345:
            this.jj_consume_token(345);
            return this.getJdbcIntervalNode(8);
         default:
            this.jj_la1[119] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode numericValueFunction() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 271:
            this.jj_consume_token(271);
            ValueNode var5 = this.absFunction();
            return var5;
         case 272:
            this.jj_consume_token(272);
            ValueNode var4 = this.absFunction();
            return var4;
         case 293:
            this.jj_consume_token(293);
            this.jj_consume_token(453);
            this.jj_consume_token(454);
            return new SpecialFunctionNode(0, this.getContextManager());
         case 311:
            this.jj_consume_token(311);
            ValueNode var3 = this.modFunction();
            return var3;
         case 438:
            this.jj_consume_token(438);
            this.jj_consume_token(453);
            ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return new UnaryArithmeticOperatorNode(var1, 2, this.getContextManager());
         default:
            this.jj_la1[120] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode coalesceFunction(String var1) throws ParseException, StandardException {
      ValueNodeList var2 = new ValueNodeList(this.getContextManager());
      this.jj_consume_token(453);
      this.coalesceExpression(var2);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.coalesceExpression(var2);
               break;
            default:
               this.jj_la1[121] = this.jj_gen;
               this.jj_consume_token(454);
               return new CoalesceFunctionNode(var1, var2, this.getContextManager());
         }
      }
   }

   public final void coalesceExpression(ValueNodeList var1) throws ParseException, StandardException {
      ValueNode var2 = this.additiveExpression((ValueNode)null, 0);
      var1.addElement(var2);
   }

   public final ValueNode absFunction() throws ParseException, StandardException {
      this.jj_consume_token(453);
      ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
      this.jj_consume_token(454);
      return new UnaryArithmeticOperatorNode(var1, 3, this.getContextManager());
   }

   public final ValueNode modFunction() throws ParseException, StandardException {
      this.jj_consume_token(453);
      ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
      this.jj_consume_token(458);
      ValueNode var2 = this.additiveExpression((ValueNode)null, 0);
      this.jj_consume_token(454);
      return new BinaryArithmeticOperatorNode(4, var1, var2, this.getContextManager());
   }

   public final int datetimeField() throws ParseException {
      switch (this.jj_nt.kind) {
         case 157:
         case 183:
         case 270:
         case 286:
         case 314:
            int var1 = this.nonSecondDatetimeField();
            return var1;
         case 224:
            this.jj_consume_token(224);
            return 5;
         default:
            this.jj_la1[122] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode characterValueFunction() throws ParseException, StandardException {
      ValueNode var1 = null;
      Token var4 = null;
      Object var5 = null;
      ValueNode var7 = null;
      switch (this.jj_nt.kind) {
         case 179:
         case 255:
            switch (this.jj_nt.kind) {
               case 179:
                  Token var13 = this.jj_consume_token(179);
                  break;
               case 255:
                  var4 = this.jj_consume_token(255);
                  break;
               default:
                  this.jj_la1[124] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }

            this.jj_consume_token(453);
            var1 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return new SimpleStringOperatorNode(var1, var4 != null ? "upper" : "lower", this.getContextManager());
         case 248:
         case 370:
         case 375:
            var1 = this.trimFunction();
            return var1;
         case 376:
            this.jj_consume_token(376);
            this.jj_consume_token(453);
            var1 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(458);
            ValueNode var6 = this.additiveExpression((ValueNode)null, 0);
            switch (this.jj_nt.kind) {
               case 458:
                  this.jj_consume_token(458);
                  var7 = this.additiveExpression((ValueNode)null, 0);
                  break;
               default:
                  this.jj_la1[123] = this.jj_gen;
            }

            this.jj_consume_token(454);
            return this.getSubstringNode(var1, var6, var7);
         case 403:
         case 444:
            switch (this.jj_nt.kind) {
               case 403:
                  Token var12 = this.jj_consume_token(403);
                  break;
               case 444:
                  var4 = this.jj_consume_token(444);
                  break;
               default:
                  this.jj_la1[125] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }

            this.jj_consume_token(453);
            var1 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return new SimpleStringOperatorNode(var1, var4 != null ? "upper" : "lower", this.getContextManager());
         case 404:
            this.jj_consume_token(404);
            this.jj_consume_token(453);
            ValueNode var2 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(458);
            ValueNode var3 = this.additiveExpression((ValueNode)null, 0);
            switch (this.jj_nt.kind) {
               case 458:
                  this.jj_consume_token(458);
                  var1 = this.additiveExpression((ValueNode)null, 0);
                  break;
               default:
                  this.jj_la1[126] = this.jj_gen;
            }

            this.jj_consume_token(454);
            return new TernaryOperatorNode(var2, var3, (ValueNode)(var1 == null ? new NumericConstantNode(TypeId.getBuiltInTypeId(4), 1, this.getContextManager()) : var1), 1, -1, this.getContextManager());
         default:
            this.jj_la1[127] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode trimFunction() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 248:
            this.jj_consume_token(248);
            ValueNode var3 = this.ansiTrim();
            return var3;
         case 370:
         case 375:
            Integer var2 = this.trimType();
            this.jj_consume_token(453);
            ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return this.getTrimOperatorNode(var2, (ValueNode)null, var1, (ContextManager)null);
         default:
            this.jj_la1[128] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode ansiTrim() throws ParseException, StandardException {
      Integer var1 = 0;
      Object var2 = null;
      Object var3 = null;
      if (this.ansiTrimSpecFollows()) {
         this.jj_consume_token(453);
         var1 = this.ansiTrimSpec();
         if (this.jj_2_38(Integer.MAX_VALUE)) {
            this.jj_consume_token(147);
            ValueNode var9 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return this.getTrimOperatorNode(var1, (ValueNode)var2, var9, (ContextManager)null);
         } else if (this.jj_2_39(1)) {
            ValueNode var6 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(147);
            ValueNode var8 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return this.getTrimOperatorNode(var1, var6, var8, (ContextManager)null);
         } else {
            this.jj_consume_token(-1);
            throw new ParseException();
         }
      } else if (!this.ansiTrimSpecFollows()) {
         this.jj_consume_token(453);
         ValueNode var5 = this.additiveExpression((ValueNode)null, 0);
         switch (this.jj_nt.kind) {
            case 147:
               this.jj_consume_token(147);
               ValueNode var7 = this.additiveExpression((ValueNode)null, 0);
               this.jj_consume_token(454);
               return this.getTrimOperatorNode(var1, var5, var7, (ContextManager)null);
            case 454:
               this.jj_consume_token(454);
               return this.getTrimOperatorNode(var1, (ValueNode)null, var5, (ContextManager)null);
            default:
               this.jj_la1[129] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final Integer ansiTrimSpec() throws ParseException {
      switch (this.jj_nt.kind) {
         case 82:
            this.jj_consume_token(82);
            return 0;
         case 176:
            this.jj_consume_token(176);
            return 2;
         case 247:
            this.jj_consume_token(247);
            return 1;
         default:
            this.jj_la1[130] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final Integer trimType() throws ParseException {
      switch (this.jj_nt.kind) {
         case 370:
            this.jj_consume_token(370);
            return 2;
         case 375:
            this.jj_consume_token(375);
            return 1;
         default:
            this.jj_la1[131] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode valueExpressionPrimary() throws ParseException, StandardException {
      if (this.escapedValueFunctionFollows()) {
         this.jj_consume_token(451);
         this.jj_consume_token(399);
         ValueNode var10 = this.escapedValueFunction();
         this.jj_consume_token(452);
         return var10;
      } else if (this.getToken(2).kind != 222 && this.getToken(2).kind != 436) {
         if (this.getToken(2).kind == 172) {
            this.jj_consume_token(108);
            this.jj_consume_token(172);
            return new SpecialFunctionNode(1, this.getContextManager());
         } else if (this.jj_2_41(1)) {
            ValueNode var9 = this.valueSpecification();
            return var9;
         } else if (this.newInvocationFollows(1)) {
            JavaToSQLValueNode var8 = this.newInvocation();
            return var8;
         } else if (this.windowOrAggregateFunctionFollows()) {
            ValueNode var7 = this.windowOrAggregateFunctionNode();
            return var7;
         } else if (this.miscBuiltinFollows()) {
            ValueNode var6 = this.miscBuiltins();
            return var6;
         } else if (this.jj_2_42(1)) {
            ColumnReference var5 = this.columnReference();
            return var5;
         } else {
            switch (this.jj_nt.kind) {
               case 87:
                  ValueNode var4 = this.castSpecification();
                  return var4;
               case 188:
                  ValueNode var3 = this.nextValueExpression();
                  return var3;
               case 453:
                  this.jj_consume_token(453);
                  Object var1;
                  if (this.getToken(1).kind != 225 && this.getToken(1).kind != 259) {
                     if (!this.jj_2_40(1)) {
                        this.jj_consume_token(-1);
                        throw new ParseException();
                     }

                     var1 = this.valueExpression();
                  } else {
                     var1 = this.subquery(17, (ValueNode)null);
                  }

                  this.jj_consume_token(454);
                  return (ValueNode)var1;
               default:
                  this.jj_la1[133] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         }
      } else {
         this.jj_consume_token(108);
         switch (this.jj_nt.kind) {
            case 222:
               this.jj_consume_token(222);
               break;
            case 436:
               this.jj_consume_token(436);
               break;
            default:
               this.jj_la1[132] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }

         return new SpecialFunctionNode(2, this.getContextManager());
      }
   }

   public final ValueNode miscBuiltins() throws ParseException, StandardException {
      if ((this.getToken(1).kind == 366 || this.getToken(1).kind == 271 || this.getToken(1).kind == 272 || this.getToken(1).kind == 438 || this.getToken(1).kind == 311 || this.getToken(1).kind == 93 || this.getToken(1).kind == 258 || this.getToken(1).kind == 293 || this.getToken(1).kind == 235 || this.getToken(1).kind == 376 || this.getToken(1).kind == 255 || this.getToken(1).kind == 179 || this.getToken(1).kind == 444 || this.getToken(1).kind == 403 || this.getToken(1).kind == 370 || this.getToken(1).kind == 375 || this.getToken(1).kind == 248 || this.getToken(1).kind == 285 || this.getToken(1).kind == 350 || this.getToken(1).kind == 351 || this.getToken(1).kind == 128 || this.getToken(1).kind == 88 || this.getToken(1).kind == 261 || this.getToken(1).kind == 168 || this.getToken(1).kind == 167 || this.getToken(1).kind == 228 || this.getToken(1).kind == 368 || this.getToken(1).kind == 270 || this.getToken(1).kind == 314 || this.getToken(1).kind == 286 || this.getToken(1).kind == 157 || this.getToken(1).kind == 183 || this.getToken(1).kind == 224 || this.getToken(1).kind == 301 || this.getToken(1).kind == 404 || this.getToken(1).kind == 379 || this.getToken(1).kind == 381 || this.getToken(1).kind == 378 || this.getToken(1).kind == 380) && this.getToken(2).kind == 453) {
         ValueNode var3 = this.miscBuiltinsCore(false);
         return var3;
      } else if (this.jj_2_43(1)) {
         ValueNode var2 = this.datetimeValueFunction();
         return var2;
      } else if (this.jj_2_44(1)) {
         ValueNode var1 = this.routineInvocation();
         return var1;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final ValueNode miscBuiltinsCore(boolean var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 179:
         case 248:
         case 255:
         case 370:
         case 375:
         case 376:
         case 403:
         case 404:
         case 444:
            ValueNode var5 = this.characterValueFunction();
            return var5;
         case 271:
         case 272:
         case 293:
         case 311:
         case 438:
            ValueNode var2 = this.numericValueFunction();
            return var2;
         case 366:
            this.jj_consume_token(366);
            this.jj_consume_token(453);
            this.jj_consume_token(454);
            ContextManager var3 = this.getContextManager();
            this.checkInternalFeature("GETCURRENTCONNECTION()");
            return new JavaToSQLValueNode(new GetCurrentConnectionNode(var3), var3);
         default:
            this.jj_la1[134] = this.jj_gen;
            if (this.jj_2_45(1)) {
               ValueNode var10 = this.dataTypeScalarFunction();
               return var10;
            } else {
               switch (this.jj_nt.kind) {
                  case 93:
                     this.jj_consume_token(93);
                     ValueNode var9 = this.coalesceFunction("COALESCE");
                     return var9;
                  case 258:
                     this.jj_consume_token(258);
                     ValueNode var8 = this.coalesceFunction("VALUE");
                     return var8;
                  case 301:
                     this.jj_consume_token(301);
                     this.jj_consume_token(453);
                     ValueNode var7 = this.additiveExpression((ValueNode)null, 0);
                     this.jj_consume_token(454);
                     ContextManager var4 = this.getContextManager();
                     if (var1) {
                        return new LengthOperatorNode(this.getTrimOperatorNode(1, (ValueNode)null, var7, var4), var4);
                     }

                     return new DB2LengthOperatorNode(var7, var4);
                  case 378:
                  case 379:
                  case 380:
                  case 381:
                     ValueNode var6 = this.xmlFunction();
                     return var6;
                  default:
                     this.jj_la1[135] = this.jj_gen;
                     this.jj_consume_token(-1);
                     throw new ParseException();
               }
            }
      }
   }

   public final ValueNode dataTypeScalarFunction() throws ParseException, StandardException {
      int var5 = -1;
      switch (this.jj_nt.kind) {
         case 157:
         case 183:
         case 224:
         case 270:
         case 285:
         case 286:
         case 314:
         case 350:
         case 351:
            ValueNode var2 = this.dateTimeScalarFunction();
            return var2;
         default:
            this.jj_la1[137] = this.jj_gen;
            if (this.jj_2_46(1)) {
               DataTypeDescriptor var1 = this.numericFunctionType();
               this.jj_consume_token(453);
               ValueNode var8 = this.additiveExpression((ValueNode)null, 0);
               this.jj_consume_token(454);
               CastNode var7 = new CastNode(var8, var1, this.getContextManager());
               ((CastNode)var7).setForDataTypeFunction(true);
               ((CastNode)var7).setForExternallyGeneratedCASTnode();
               return var7;
            } else {
               switch (this.jj_nt.kind) {
                  case 88:
                  case 261:
                     int var4 = this.charOrVarchar();
                     this.jj_consume_token(453);
                     ValueNode var3 = this.additiveExpression((ValueNode)null, 0);
                     switch (this.jj_nt.kind) {
                        case 458:
                           this.jj_consume_token(458);
                           var5 = this.length();
                           break;
                        default:
                           this.jj_la1[136] = this.jj_gen;
                     }

                     this.jj_consume_token(454);
                     this.checkTypeLimits(var4, var5);
                     CastNode var6 = new CastNode(var3, var4, var5, this.getContextManager());
                     ((CastNode)var6).setForDataTypeFunction(true);
                     ((CastNode)var6).setForExternallyGeneratedCASTnode();
                     return var6;
                  default:
                     this.jj_la1[138] = this.jj_gen;
                     this.jj_consume_token(-1);
                     throw new ParseException();
               }
            }
      }
   }

   public final ValueNode xmlFunction() throws ParseException, StandardException {
      this.checkVersion(130, "XML");
      org.apache.derby.iapi.types.XML.checkXMLRequirements();
      switch (this.jj_nt.kind) {
         case 378:
            this.jj_consume_token(378);
            this.jj_consume_token(453);
            ValueNode var4 = this.xmlQueryValue(true);
            this.jj_consume_token(454);
            return var4;
         case 379:
            this.jj_consume_token(379);
            this.jj_consume_token(453);
            this.xmlDocOrContent();
            ValueNode var3 = this.xmlParseValue();
            this.jj_consume_token(454);
            return var3;
         case 380:
            this.jj_consume_token(380);
            this.jj_consume_token(453);
            ValueNode var2 = this.xmlQueryValue(false);
            this.jj_consume_token(454);
            return var2;
         case 381:
            this.jj_consume_token(381);
            this.jj_consume_token(453);
            ValueNode var1 = this.xmlSerializeValue();
            this.jj_consume_token(454);
            return var1;
         default:
            this.jj_la1[139] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode xmlParseValue() throws ParseException, StandardException {
      ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
      boolean var2 = this.xmlPreserveWhitespace();
      return new UnaryOperatorNode(var1, 0, (DataTypeDescriptor)null, var2, this.getContextManager());
   }

   public final boolean xmlPreserveWhitespace() throws ParseException, StandardException {
      if (this.getToken(1).kind != 440 && this.getToken(1).kind != 207) {
         throw StandardException.newException("42Z72", new Object[]{"PRESERVE WHITESPACE", this.getToken(1).beginLine, this.getToken(1).beginColumn});
      } else {
         switch (this.jj_nt.kind) {
            case 207:
               this.jj_consume_token(207);
               this.jj_consume_token(446);
               return true;
            case 440:
               this.jj_consume_token(440);
               this.jj_consume_token(446);
               throw StandardException.newException("42Z74", new Object[]{"STRIP WHITESPACE"});
            default:
               this.jj_la1[140] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final ValueNode xmlSerializeValue() throws ParseException, StandardException {
      ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
      DataTypeDescriptor var2 = this.xmlSerializeTargetType();
      return new UnaryOperatorNode(var1, 1, var2, false, this.getContextManager());
   }

   public final DataTypeDescriptor xmlSerializeTargetType() throws ParseException, StandardException {
      if (this.getToken(1).kind != 72) {
         throw StandardException.newException("42Z72", new Object[]{"AS", this.getToken(1).beginLine, this.getToken(1).beginColumn});
      } else {
         switch (this.jj_nt.kind) {
            case 72:
               this.jj_consume_token(72);
               DataTypeDescriptor var1 = this.dataTypeDDL();
               return var1;
            default:
               this.jj_la1[141] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final ValueNode xmlQueryValue(boolean var1) throws ParseException, StandardException {
      Object var2 = null;
      Object var3 = null;
      short var4 = -1;
      ValueNode var6 = this.additiveExpression((ValueNode)null, 0);
      this.jj_consume_token(418);
      var4 = this.xmlPassingMechanism();
      ValueNode var7 = this.xqVarList();
      if (!var1) {
         if (this.jj_2_48(1)) {
            this.xqReturningClause();
            if (this.jj_2_47(1)) {
               this.xmlPassingMechanism();
            }
         }

         this.xqEmptyHandlingClause();
      } else if (!var1) {
         this.jj_consume_token(-1);
         throw new ParseException();
      }

      BinaryOperatorNode var5 = new BinaryOperatorNode(var6, var7, var1 ? 0 : 1, this.getContextManager());
      return var5;
   }

   public final ValueNode xqVarList() throws ParseException, StandardException {
      ValueNode[] var1 = new ValueNode[]{(ValueNode)null};
      this.xqVariable(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.xqVariable(var1);
               break;
            default:
               this.jj_la1[142] = this.jj_gen;
               return var1[0];
         }
      }
   }

   public final void xqVariable(ValueNode[] var1) throws ParseException, StandardException {
      Object var3 = null;
      short var4 = -1;
      ValueNode var2 = this.additiveExpression((ValueNode)null, 0);
      if (this.getToken(1).kind == 72) {
         this.jj_consume_token(72);
         String var5 = this.identifier(128, true);
         throw StandardException.newException("42Z74", new Object[]{"PASSING ... AS"});
      } else {
         if (this.jj_2_49(1)) {
            var4 = this.xmlPassingMechanism();
         }

         if (var3 == null) {
            if (var1[0] != null) {
               throw StandardException.newException("42Z76", new Object[0]);
            }

            var1[0] = var2;
         }

      }
   }

   public final short xmlPassingMechanism() throws ParseException, StandardException {
      if (this.getToken(2).kind == 422) {
         this.jj_consume_token(83);
         this.jj_consume_token(422);
         return 1;
      } else {
         switch (this.jj_nt.kind) {
            case 83:
               this.jj_consume_token(83);
               this.jj_consume_token(258);
               throw StandardException.newException("42Z74", new Object[]{"BY VALUE"});
            default:
               this.jj_la1[143] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final short xqReturningClause() throws ParseException, StandardException {
      if (this.getToken(2).kind == 432) {
         this.jj_consume_token(428);
         this.jj_consume_token(432);
         return 3;
      } else {
         switch (this.jj_nt.kind) {
            case 428:
               this.jj_consume_token(428);
               this.jj_consume_token(387);
               throw StandardException.newException("42Z74", new Object[]{"RETURNING CONTENT"});
            default:
               this.jj_la1[144] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final short xqEmptyHandlingClause() throws ParseException, StandardException {
      if (this.getToken(1).kind == 397) {
         this.jj_consume_token(397);
         this.jj_consume_token(195);
         this.jj_consume_token(397);
         return 5;
      } else {
         switch (this.jj_nt.kind) {
            case 191:
               this.jj_consume_token(191);
               this.jj_consume_token(195);
               this.jj_consume_token(397);
               throw StandardException.newException("42Z74", new Object[]{"NULL ON EMPTY"});
            default:
               this.jj_la1[145] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final DataTypeDescriptor numericFunctionType() throws ParseException, StandardException {
      if (this.jj_2_50(1)) {
         DataTypeDescriptor var2 = this.doubleType();
         return var2;
      } else {
         switch (this.jj_nt.kind) {
            case 167:
            case 168:
            case 228:
            case 368:
               DataTypeDescriptor var1 = this.exactIntegerType();
               return var1;
            default:
               this.jj_la1[146] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final ValueNode dateTimeScalarFunction() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 157:
         case 183:
         case 224:
         case 270:
         case 286:
         case 314:
            int var3 = this.datetimeField();
            this.jj_consume_token(453);
            ValueNode var7 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return new ExtractOperatorNode(var3, var7, this.getContextManager());
         case 285:
            this.jj_consume_token(285);
            this.jj_consume_token(453);
            ValueNode var6 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return new UnaryDateTimestampOperatorNode(var6, 0, this.getContextManager());
         case 350:
            this.jj_consume_token(350);
            this.jj_consume_token(453);
            ValueNode var5 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            CastNode var4 = new CastNode(var5, DataTypeDescriptor.getBuiltInDataTypeDescriptor(92), this.getContextManager());
            ((CastNode)var4).setForExternallyGeneratedCASTnode();
            return var4;
         case 351:
            this.jj_consume_token(351);
            this.jj_consume_token(453);
            ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
            ValueNode var2 = this.timestampFunctionCompletion(var1);
            return var2;
         default:
            this.jj_la1[147] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode timestampFunctionCompletion(ValueNode var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 454:
            this.jj_consume_token(454);
            return new UnaryDateTimestampOperatorNode(var1, 1, this.getContextManager());
         case 458:
            this.jj_consume_token(458);
            ValueNode var2 = this.additiveExpression((ValueNode)null, 0);
            this.jj_consume_token(454);
            return new TimestampOperatorNode(var1, var2, this.getContextManager());
         default:
            this.jj_la1[148] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final Token booleanLiteral() throws ParseException {
      switch (this.jj_nt.kind) {
         case 140:
            Token var2 = this.jj_consume_token(140);
            return var2;
         case 249:
            Token var1 = this.jj_consume_token(249);
            return var1;
         default:
            this.jj_la1[149] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode generalValueSpecification() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 112:
         case 226:
         case 256:
            ValueNode var3 = this.userNode();
            return var3;
         case 363:
            ValueNode var2 = this.currentRoleNode();
            return var2;
         case 472:
            ParameterNode var1 = this.dynamicParameterSpecification();
            return var1;
         default:
            this.jj_la1[150] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode userNode() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 112:
            this.jj_consume_token(112);
            return new SpecialFunctionNode(4, this.getContextManager());
         case 226:
            this.jj_consume_token(226);
            return new SpecialFunctionNode(5, this.getContextManager());
         case 256:
            this.jj_consume_token(256);
            return new SpecialFunctionNode(3, this.getContextManager());
         default:
            this.jj_la1[151] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode currentRoleNode() throws ParseException, StandardException {
      this.jj_consume_token(363);
      this.checkVersion(170, "ROLES");
      this.checkSqlStandardAccess("CURRENT_ROLE");
      return new SpecialFunctionNode(7, this.getContextManager());
   }

   public final JavaToSQLValueNode newInvocation() throws ParseException, StandardException {
      ArrayList var2 = new ArrayList();
      this.jj_consume_token(409);
      String var3 = this.javaClassName();
      this.methodCallParameterList(var2);
      if (!var3.startsWith("org.apache.derby.diag.") && !var3.startsWith("org.apache.derby.catalog.") && !var3.startsWith("com.ibm.db2j.")) {
         this.checkInternalFeature(var3);
      }

      NewInvocationNode var1 = new NewInvocationNode(var3, var2, this.lastTokenDelimitedIdentifier, this.getContextManager());
      return new JavaToSQLValueNode(var1, this.getContextManager());
   }

   public final JavaToSQLValueNode vtiTableConstruct() throws ParseException, StandardException {
      Object var1 = null;
      Object var2 = null;
      ArrayList var3 = new ArrayList();
      Object var4 = null;
      this.jj_consume_token(239);
      this.jj_consume_token(453);
      TableName var9 = this.qualifiedName(128);
      this.methodCallParameterList(var3);
      this.jj_consume_token(454);
      NewInvocationNode var7 = new NewInvocationNode(var9, (TableDescriptor)null, var3, this.lastTokenDelimitedIdentifier, this.getContextManager());
      if (var7.isBuiltinVTI()) {
         var2 = var7;
      } else {
         StaticMethodCallNode var6 = new StaticMethodCallNode(var9, (String)null, this.getContextManager());
         ((MethodCallNode)var6).addParms(var3);
         var2 = var6;
      }

      return new JavaToSQLValueNode((JavaValueNode)var2, this.getContextManager());
   }

   public final ValueNode staticMethodInvocation(String var1) throws ParseException, StandardException {
      ArrayList var2 = new ArrayList();
      MethodCallNode var3 = this.staticMethodName(var1);
      this.methodCallParameterList(var2);
      var3.addParms(var2);
      return new JavaToSQLValueNode(var3, this.getContextManager());
   }

   public final void methodCallParameterList(List var1) throws ParseException, StandardException {
      this.jj_consume_token(453);
      if (this.jj_2_51(1)) {
         this.methodParameter(var1);

         label16:
         while(true) {
            switch (this.jj_nt.kind) {
               case 458:
                  this.jj_consume_token(458);
                  this.methodParameter(var1);
                  break;
               default:
                  this.jj_la1[152] = this.jj_gen;
                  break label16;
            }
         }
      }

      this.jj_consume_token(454);
   }

   public final ValueNode routineInvocation() throws ParseException, StandardException {
      if (!this.distinctUDAFollows()) {
         ValueNode var2 = this.routineExpression();
         return var2;
      } else if (this.distinctUDAFollows()) {
         ValueNode var1 = this.distinctUDA();
         return var1;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final ValueNode routineExpression() throws ParseException, StandardException {
      ArrayList var1 = new ArrayList();
      TableName var2 = this.qualifiedName(128);
      this.methodCallParameterList(var1);
      StaticMethodCallNode var3 = new StaticMethodCallNode(var2, (String)null, this.getContextManager());
      ((MethodCallNode)var3).addParms(var1);
      return new JavaToSQLValueNode(var3, this.getContextManager());
   }

   public final ValueNode distinctUDA() throws ParseException, StandardException {
      TableName var2 = this.qualifiedName(128);
      this.jj_consume_token(453);
      this.jj_consume_token(127);
      ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
      this.jj_consume_token(454);
      return new AggregateNode(var1, var2, true, "", this.getContextManager());
   }

   public final String javaClass() throws ParseException, StandardException {
      String var1 = this.javaClassName();
      return var1;
   }

   public final ValueNode columnMethodInvocation() throws ParseException, StandardException {
      ValueNode var1 = this.columnNameForInvocation();
      ValueNode var2 = this.nonStaticMethodInvocation(var1);
      return var2;
   }

   public final ValueNode columnNameForInvocation() throws ParseException, StandardException {
      String var2 = null;
      String var3 = null;
      Object var4 = null;
      String var5 = null;
      String var6 = null;
      TableName var7 = null;
      String var1 = this.identifier(128, true);
      if (this.getToken(1).kind == 460 && this.getToken(3).kind == 460) {
         this.jj_consume_token(460);
         var2 = this.identifier(128, true);
         if (this.getToken(1).kind == 460 && this.getToken(3).kind == 460) {
            this.jj_consume_token(460);
            var3 = this.identifier(128, true);
         }
      }

      String var9;
      if (var3 == null) {
         if (var2 == null) {
            var9 = var1;
         } else {
            var5 = var1;
            var9 = var2;
         }
      } else {
         var6 = var1;
         var5 = var2;
         var9 = var3;
      }

      if (var5 != null) {
         var7 = new TableName(var6, var5, (var3 == null ? this.nextToLastIdentifierToken : this.thirdToLastIdentifierToken).beginOffset, this.nextToLastIdentifierToken.endOffset, this.getContextManager());
      }

      ColumnReference var8 = new ColumnReference(var9, var7, Integer.valueOf(this.lastIdentifierToken.beginOffset), Integer.valueOf(this.lastIdentifierToken.endOffset), this.getContextManager());
      return var8;
   }

   public final ColumnReference columnReference() throws ParseException, StandardException {
      String var2 = null;
      String var3 = null;
      Object var4 = null;
      String var5 = null;
      String var6 = null;
      TableName var7 = null;
      String var1 = this.identifier(128, false);
      if (this.getToken(1).kind == 460 && this.getToken(3).kind != 453) {
         this.jj_consume_token(460);
         var2 = this.identifier(128, false);
         if (this.getToken(1).kind == 460 && this.getToken(3).kind != 453) {
            this.jj_consume_token(460);
            var3 = this.identifier(128, false);
         }
      }

      String var8;
      if (var3 == null) {
         if (var2 == null) {
            var8 = var1;
         } else {
            var5 = var1;
            var8 = var2;
         }
      } else {
         var6 = var1;
         var5 = var2;
         var8 = var3;
      }

      IdUtil.checkIdentifierLengthLimit(var8, 128);
      if (var6 != null) {
         IdUtil.checkIdentifierLengthLimit(var6, 128);
      }

      if (var5 != null) {
         IdUtil.checkIdentifierLengthLimit(var5, 128);
      }

      if (var5 != null) {
         var7 = new TableName(var6, var5, (var3 == null ? this.nextToLastIdentifierToken : this.thirdToLastIdentifierToken).beginOffset, this.nextToLastIdentifierToken.endOffset, this.getContextManager());
      }

      return new ColumnReference(var8, var7, Integer.valueOf(this.lastIdentifierToken.beginOffset), Integer.valueOf(this.lastIdentifierToken.endOffset), this.getContextManager());
   }

   public final OrderByList orderByClause(ResultSetNode var1) throws ParseException, StandardException {
      this.jj_consume_token(200);
      this.jj_consume_token(83);
      OrderByList var2 = this.sortSpecificationList(var1);
      this.forbidNextValueFor();
      return var2;
   }

   public final int atIsolationLevel() throws ParseException, StandardException {
      this.jj_consume_token(267);
      int var1 = this.isolationLevelDB2Abbrev();
      return var1;
   }

   public final OrderByList sortSpecificationList(ResultSetNode var1) throws ParseException, StandardException {
      OrderByList var2 = new OrderByList(var1, this.getContextManager());
      this.sortSpecification(var2);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.sortSpecification(var2);
               break;
            default:
               this.jj_la1[153] = this.jj_gen;
               return var2;
         }
      }
   }

   public final void sortSpecification(OrderByList var1) throws ParseException, StandardException {
      OrderByColumn var2 = this.sortKey();
      switch (this.jj_nt.kind) {
         case 73:
         case 123:
            this.orderingSpecification(var2);
            break;
         default:
            this.jj_la1[154] = this.jj_gen;
      }

      if (this.jj_2_52(1)) {
         this.nullOrdering(var2);
      }

      var1.addOrderByColumn(var2);
   }

   public final OrderByColumn sortKey() throws ParseException, StandardException {
      ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
      return new OrderByColumn(var1, this.getContextManager());
   }

   public final void orderingSpecification(OrderByColumn var1) throws ParseException {
      switch (this.jj_nt.kind) {
         case 73:
            this.jj_consume_token(73);
            break;
         case 123:
            this.jj_consume_token(123);
            var1.setDescending();
            break;
         default:
            this.jj_la1[155] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

   }

   public final void nullOrdering(OrderByColumn var1) throws ParseException {
      if (this.getToken(2).kind == 175) {
         this.jj_consume_token(320);
         this.jj_consume_token(175);
         if (!var1.isAscending()) {
            var1.setNullsOrderedLow();
         }
      } else {
         switch (this.jj_nt.kind) {
            case 320:
               this.jj_consume_token(320);
               this.jj_consume_token(142);
               if (var1.isAscending()) {
                  var1.setNullsOrderedLow();
               }
               break;
            default:
               this.jj_la1[156] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }

   }

   public final boolean offsetFetchFirstClause(ValueNode[] var1) throws ParseException, StandardException {
      boolean var2 = false;
      if (this.getToken(1).kind != 323 && this.getToken(1).kind != 141) {
         if (this.getToken(1).kind == 451) {
            this.jdbcLimitOffset(var1);
            var2 = true;
         }

         return var2;
      } else {
         this.sqlStandardOffsetFetchFirst(var1);
         return false;
      }
   }

   public final void sqlStandardOffsetFetchFirst(ValueNode[] var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 323 -> var1[0] = this.offsetClause();
         default -> this.jj_la1[157] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 141 -> var1[1] = this.fetchFirstClause();
         default -> this.jj_la1[158] = this.jj_gen;
      }

   }

   public final void jdbcLimitOffset(ValueNode[] var1) throws ParseException, StandardException {
      Object var2 = null;
      Object var3 = null;
      this.jj_consume_token(451);
      this.jj_consume_token(303);
      switch (this.jj_nt.kind) {
         case 457:
         case 459:
         case 487:
            var2 = this.intLiteral();
            break;
         case 472:
            var2 = this.dynamicParameterSpecification();
            break;
         default:
            this.jj_la1[159] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      label26:
      switch (this.jj_nt.kind) {
         case 323:
            this.jj_consume_token(323);
            switch (this.jj_nt.kind) {
               case 457:
               case 459:
               case 487:
                  var3 = this.intLiteral();
                  break label26;
               case 472:
                  var3 = this.dynamicParameterSpecification();
                  break label26;
               default:
                  this.jj_la1[160] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[161] = this.jj_gen;
      }

      this.jj_consume_token(452);
      if (var2 instanceof NumericConstantNode && ((NumericConstantNode)var2).getValue().getInt() == 0) {
         var2 = null;
      }

      if (var3 == null) {
         var3 = this.getNumericNode("0", true);
      }

      var1[1] = (ValueNode)var2;
      var1[0] = (ValueNode)var3;
   }

   public final ValueNode offsetClause() throws ParseException, StandardException {
      Object var1 = null;
      this.jj_consume_token(323);
      switch (this.jj_nt.kind) {
         case 457:
         case 459:
         case 487:
            var1 = this.intLiteral();
            break;
         case 472:
            var1 = this.dynamicParameterSpecification();
            break;
         default:
            this.jj_la1[162] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      switch (this.jj_nt.kind) {
         case 221:
            this.jj_consume_token(221);
            break;
         case 332:
            this.jj_consume_token(332);
            break;
         default:
            this.jj_la1[163] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      return (ValueNode)var1;
   }

   public final ValueNode fetchFirstClause() throws ParseException, StandardException {
      Object var1 = this.getNumericNode("1", true);
      this.jj_consume_token(141);
      switch (this.jj_nt.kind) {
         case 142:
            this.jj_consume_token(142);
            break;
         case 188:
            this.jj_consume_token(188);
            break;
         default:
            this.jj_la1[164] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      label22:
      switch (this.jj_nt.kind) {
         case 457:
         case 459:
         case 472:
         case 487:
            switch (this.jj_nt.kind) {
               case 457:
               case 459:
               case 487:
                  var1 = this.intLiteral();
                  break label22;
               case 472:
                  var1 = this.dynamicParameterSpecification();
                  break label22;
               default:
                  this.jj_la1[165] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[166] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 221:
            this.jj_consume_token(221);
            break;
         case 332:
            this.jj_consume_token(332);
            break;
         default:
            this.jj_la1[167] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      this.jj_consume_token(196);
      return (ValueNode)var1;
   }

   public final int forUpdateClause(List var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 141:
            this.jj_consume_token(141);
            this.jj_consume_token(196);
            return 1;
         case 213:
            this.jj_consume_token(213);
            this.jj_consume_token(196);
            return 1;
         case 254:
            this.jj_consume_token(254);
            switch (this.jj_nt.kind) {
               case 194:
                  this.jj_consume_token(194);
                  this.forUpdateColumnList(var1);
                  break;
               default:
                  this.jj_la1[168] = this.jj_gen;
            }

            return 2;
         default:
            this.jj_la1[169] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final void forUpdateColumnList(List var1) throws ParseException, StandardException {
      this.forUpdateColumn(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.forUpdateColumn(var1);
               break;
            default:
               this.jj_la1[170] = this.jj_gen;
               return;
         }
      }
   }

   public final void forUpdateColumn(List var1) throws ParseException, StandardException {
      String var2 = this.identifier(128, true);
      var1.add(var2);
   }

   public final ResultColumnList setClauseList() throws ParseException, StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());
      this.setClause(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.setClause(var1);
               break;
            default:
               this.jj_la1[171] = this.jj_gen;
               return var1;
         }
      }
   }

   public final void setClause(ResultColumnList var1) throws ParseException, StandardException {
      ColumnReference var3 = this.columnReference();
      this.jj_consume_token(467);
      ValueNode var4 = this.updateSource(var3.getColumnName());
      ResultColumn var2 = new ResultColumn(var3, var4, this.getContextManager());
      var1.addResultColumn(var2);
   }

   public final ValueNode updateSource(String var1) throws ParseException, StandardException {
      if (this.jj_2_53(1)) {
         ValueNode var3 = this.valueExpression();
         return var3;
      } else {
         switch (this.jj_nt.kind) {
            case 119:
               this.jj_consume_token(119);
               return new DefaultNode(var1, this.getContextManager());
            case 191:
               ValueNode var2 = this.nullSpecification();
               return var2;
            default:
               this.jj_la1[172] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final ValueNode nullSpecification() throws ParseException, StandardException {
      this.jj_consume_token(191);
      return new UntypedNullConstantNode(this.getContextManager());
   }

   public final StatementNode insertColumnsAndSource(QueryTreeNode var1) throws ParseException, StandardException {
      Properties var2 = null;
      ResultColumnList var4 = null;
      OrderByList var5 = null;
      ValueNode[] var6 = new ValueNode[2];
      boolean var7 = false;
      if (this.getToken(1).kind == 453 && !this.subqueryFollows()) {
         this.jj_consume_token(453);
         var4 = this.insertColumnList();
         this.jj_consume_token(454);
      }

      switch (this.jj_nt.kind) {
         case 59:
            var2 = this.propertyList(false);
            this.jj_consume_token(64);
            break;
         default:
            this.jj_la1[173] = this.jj_gen;
      }

      ResultSetNode var3 = this.queryExpression((ResultSetNode)null, 0);
      switch (this.jj_nt.kind) {
         case 200 -> var5 = this.orderByClause(var3);
         default -> this.jj_la1[174] = this.jj_gen;
      }

      var7 = this.offsetFetchFirstClause(var6);
      if (var5 != null && this.isTableValueConstructor(var3)) {
         throw StandardException.newException("42X01", new Object[]{"ORDER BY"});
      } else if ((var6[0] != null || var6[1] != null) && this.isTableValueConstructor(var3)) {
         String var8;
         if (var7) {
            var8 = "LIMIT";
         } else if (var6[0] != null) {
            var8 = "OFFSET";
         } else {
            var8 = "FETCH";
         }

         throw StandardException.newException("42X01", new Object[]{var8});
      } else {
         return new InsertNode(var1, var4, var3, (MatchingClauseNode)null, var2, var5, var6[0], var6[1], var7, this.getContextManager());
      }
   }

   public final ResultColumnList insertColumnList() throws ParseException, StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());
      this.columnQualifiedNameList(var1);
      return var1;
   }

   public final void columnQualifiedNameList(ResultColumnList var1) throws ParseException, StandardException {
      this.columnQualifiedNameItem(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.columnQualifiedNameItem(var1);
               break;
            default:
               this.jj_la1[175] = this.jj_gen;
               return;
         }
      }
   }

   public final void columnQualifiedNameItem(ResultColumnList var1) throws ParseException, StandardException {
      ColumnReference var2 = this.columnReference();
      ResultColumn var3 = new ResultColumn(var2, (ValueNode)null, this.getContextManager());
      var1.addResultColumn(var3);
   }

   public final ResultSetNode rowValueConstructor(ResultSetNode var1) throws ParseException, StandardException {
      ResultColumnList var2 = new ResultColumnList(this.getContextManager());
      if (this.rowValueConstructorListFollows()) {
         this.jj_consume_token(453);
         this.rowValueConstructorList(var2);
         this.jj_consume_token(454);
         Object var4 = new RowResultSetNode(var2, (Properties)null, this.getContextManager());
         if (var1 != null) {
            if (var1.getResultColumns().size() != ((ResultSetNode)var4).getResultColumns().size()) {
               throw StandardException.newException("42X59", new Object[0]);
            }

            var4 = new UnionNode(var1, (ResultSetNode)var4, true, true, (Properties)null, this.getContextManager());
         }

         return (ResultSetNode)var4;
      } else {
         this.rowValueConstructorElement(var2);
         Object var3 = new RowResultSetNode(var2, (Properties)null, this.getContextManager());
         if (var1 != null) {
            if (var1.getResultColumns().size() != ((ResultSetNode)var3).getResultColumns().size()) {
               throw StandardException.newException("42X59", new Object[0]);
            }

            var3 = new UnionNode(var1, (ResultSetNode)var3, true, true, (Properties)null, this.getContextManager());
         }

         return (ResultSetNode)var3;
      }
   }

   public final void rowValueConstructorElement(ResultColumnList var1) throws ParseException, StandardException {
      if (this.jj_2_54(1)) {
         ValueNode var2 = this.valueExpression();
         var1.addResultColumn(new ResultColumn((String)null, var2, this.getContextManager()));
      } else {
         switch (this.jj_nt.kind) {
            case 119:
               this.jj_consume_token(119);
               var1.addResultColumn(new ResultColumn((String)null, new DefaultNode((String)null, this.getContextManager()), this.getContextManager()));
               break;
            case 191:
               ValueNode var3 = this.nullSpecification();
               var1.addResultColumn(new ResultColumn((String)null, var3, this.getContextManager()));
               break;
            default:
               this.jj_la1[176] = this.jj_gen;
               throw StandardException.newException("42X80", new Object[0]);
         }
      }

   }

   public final void rowValueConstructorList(ResultColumnList var1) throws ParseException, StandardException {
      this.rowValueConstructorElement(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.rowValueConstructorElement(var1);
               break;
            default:
               this.jj_la1[177] = this.jj_gen;
               return;
         }
      }
   }

   public final SubqueryNode tableSubquery(int var1, ValueNode var2) throws ParseException, StandardException {
      SubqueryNode var3 = this.subquery(var1, var2);
      return var3;
   }

   public final SubqueryNode subquery(int var1, ValueNode var2) throws ParseException, StandardException {
      OrderByList var5 = null;
      ValueNode[] var6 = new ValueNode[2];
      boolean var7 = false;
      ResultSetNode var3 = this.queryExpression((ResultSetNode)null, 0);
      switch (this.jj_nt.kind) {
         case 200 -> var5 = this.orderByClause(var3);
         default -> this.jj_la1[178] = this.jj_gen;
      }

      var7 = this.offsetFetchFirstClause(var6);
      SubqueryNode var4 = new SubqueryNode(var3, var1, var2, var5, var6[0], var6[1], var7, this.getContextManager());
      return var4;
   }

   public final ValueNode inPredicateValue(ValueNode var1) throws ParseException, StandardException {
      this.jj_consume_token(453);
      Object var2;
      if (this.subqueryFollows()) {
         var2 = this.tableSubquery(1, var1);
      } else {
         if (!this.jj_2_55(1)) {
            this.jj_consume_token(-1);
            throw new ParseException();
         }

         var2 = this.inValueList(var1);
      }

      this.jj_consume_token(454);
      return (ValueNode)var2;
   }

   public final ValueNode inValueList(ValueNode var1) throws ParseException, StandardException {
      ValueNodeList var2 = new ValueNodeList(this.getContextManager());
      this.inElement(var2);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.inElement(var2);
               break;
            default:
               this.jj_la1[179] = this.jj_gen;
               return new InListOperatorNode(var1, var2, this.getContextManager());
         }
      }
   }

   public final void inElement(ValueNodeList var1) throws ParseException, StandardException {
      ValueNode var2 = this.additiveExpression((ValueNode)null, 0);
      var1.addElement(var2);
   }

   public final int quantifier(int var1) throws ParseException, StandardException {
      byte var2 = 0;
      switch (this.jj_nt.kind) {
         case 66:
            this.jj_consume_token(66);
            switch (var1) {
               case 6 -> var2 = 4;
               case 7 -> var2 = 6;
               case 8 -> var2 = 8;
               case 9 -> var2 = 10;
               case 10 -> var2 = 12;
               case 11 -> var2 = 14;
            }

            return var2;
         case 70:
         case 229:
            this.some();
            switch (var1) {
               case 6 -> var2 = 3;
               case 7 -> var2 = 5;
               case 8 -> var2 = 7;
               case 9 -> var2 = 9;
               case 10 -> var2 = 11;
               case 11 -> var2 = 13;
            }

            return var2;
         default:
            this.jj_la1[180] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final void some() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 70:
            this.jj_consume_token(70);
            break;
         case 229:
            this.jj_consume_token(229);
            break;
         default:
            this.jj_la1[181] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

   }

   public final SubqueryNode existsExpression() throws ParseException, StandardException {
      this.jj_consume_token(138);
      this.jj_consume_token(453);
      SubqueryNode var1 = this.tableSubquery(15, (ValueNode)null);
      this.jj_consume_token(454);
      return var1;
   }

   public final SelectNode tableExpression(ResultColumnList var1) throws ParseException, StandardException {
      ValueNode var4 = null;
      GroupByList var5 = null;
      ValueNode var6 = null;
      WindowList var8 = null;
      OptimizerPlan var9 = null;
      FromList var3 = this.fromClause();
      switch (this.jj_nt.kind) {
         case 265:
            Token var7 = this.jj_consume_token(265);
            var4 = this.whereClause(var7);
            break;
         default:
            this.jj_la1[182] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 155 -> var5 = this.groupByClause();
         default -> this.jj_la1[183] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 156 -> var6 = this.havingClause();
         default -> this.jj_la1[184] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 266 -> var8 = this.windowClause();
         default -> this.jj_la1[185] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 359 -> var9 = this.optimizerOverridePlan();
         default -> this.jj_la1[186] = this.jj_gen;
      }

      if (var6 != null && var5 == null) {
         AggregateNode var10 = new AggregateNode((ValueNode)null, CountAggregateDefinition.class, false, "COUNT(*)", this.getContextManager());
         var10.replaceAggregatesWithColumnReferences(var1, 0);
      }

      SelectNode var2 = new SelectNode(var1, var3, var4, var5, var6, var8, var9, this.getContextManager());
      return var2;
   }

   public final FromList fromClause() throws ParseException, StandardException {
      FromList var1 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      this.jj_consume_token(147);
      Token var3 = this.getToken(1);
      switch (this.jj_nt.kind) {
         case 59 -> this.fromListProperties(var1);
         default -> this.jj_la1[187] = this.jj_gen;
      }

      this.dummyTableReferenceRule(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.dummyTableReferenceRule(var1);
               break;
            default:
               this.jj_la1[188] = this.jj_gen;
               Token var4 = this.getToken(0);
               var1.setBeginOffset(var3.beginOffset);
               var1.setEndOffset(var4.endOffset);
               return var1;
         }
      }
   }

   public final void fromListProperties(FromList var1) throws ParseException, StandardException {
      Properties var2 = this.propertyList(true);
      this.jj_consume_token(64);
      var1.setProperties(var2);
   }

   public final void dummyTableReferenceRule(FromList var1) throws ParseException, StandardException {
      if (this.getToken(1).kind != 239 || this.getToken(2).kind != 453 || this.getToken(3).kind != 225 && this.getToken(3).kind != 259) {
         if (!this.jj_2_56(1)) {
            this.jj_consume_token(-1);
            throw new ParseException();
         }

         FromTable var3 = this.tableReferenceTypes(false);
         var1.addFromTable(var3);
      } else {
         this.jj_consume_token(239);
         FromTable var2 = this.tableReferenceTypes(false);
         var1.addFromTable(var2);
      }

   }

   public final FromTable tableReferenceTypes(boolean var1) throws ParseException, StandardException {
      if (this.jj_2_57(1)) {
         FromTable var3 = this.tableReference(var1);
         return var3;
      } else {
         switch (this.jj_nt.kind) {
            case 451:
               this.jj_consume_token(451);
               this.jj_consume_token(412);
               FromTable var2 = this.tableReference(var1);
               this.jj_consume_token(452);
               return var2;
            default:
               this.jj_la1[189] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final Object[] optionalTableClauses() throws ParseException, StandardException {
      Object var1 = null;
      Properties var2 = null;
      ResultColumnList var3 = null;
      String var4 = null;
      switch (this.jj_nt.kind) {
         case 59:
            var1 = this.optionalTableProperties();
            ((Object[])var1)[1] = var3;
            ((Object[])var1)[2] = var4;
            return (Object[])var1;
         default:
            this.jj_la1[193] = this.jj_gen;
            if (this.jj_2_58(1)) {
               switch (this.jj_nt.kind) {
                  case 72 -> this.jj_consume_token(72);
                  default -> this.jj_la1[190] = this.jj_gen;
               }

               var4 = this.identifier(128, true);
               switch (this.jj_nt.kind) {
                  case 453:
                     this.jj_consume_token(453);
                     var3 = this.derivedColumnList();
                     this.jj_consume_token(454);
                     break;
                  default:
                     this.jj_la1[191] = this.jj_gen;
               }

               switch (this.jj_nt.kind) {
                  case 59:
                     var2 = this.propertyList(true);
                     this.jj_consume_token(64);
                     break;
                  default:
                     this.jj_la1[192] = this.jj_gen;
               }
            }

            var1 = new Object[]{var2, var3, var4};
            return (Object[])var1;
      }
   }

   public final Object[] optionalTableProperties() throws ParseException, StandardException {
      Object var1 = null;
      Object var2 = null;
      Properties var4 = this.propertyList(true);
      this.jj_consume_token(64);
      var1 = new Object[]{var4, null, null};
      return (Object[])var1;
   }

   public final FromTable tableReference(boolean var1) throws ParseException, StandardException {
      TableOperatorNode var3 = null;

      FromTable var2;
      for(var2 = this.tableFactor(); this.joinedTableExpressionFollows(); var3 = this.joinedTableExpression((ResultSetNode)(var3 == null ? var2 : var3), var1)) {
      }

      return (FromTable)(var3 == null ? var2 : var3);
   }

   public final FromTable tableFactor() throws ParseException, StandardException {
      JavaToSQLValueNode var1 = null;
      Object var3 = null;
      ResultColumnList var4 = null;
      Object[] var7 = new Object[3];
      Object var8 = null;
      if (this.jj_2_59(1)) {
         if (this.newInvocationFollows(1)) {
            var1 = this.newInvocation();
         } else {
            switch (this.jj_nt.kind) {
               case 239:
                  var1 = this.vtiTableConstruct();
                  break;
               default:
                  this.jj_la1[194] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         }

         switch (this.jj_nt.kind) {
            case 72 -> this.jj_consume_token(72);
            default -> this.jj_la1[195] = this.jj_gen;
         }

         String var12 = this.identifier(128, true);
         switch (this.jj_nt.kind) {
            case 453:
               this.jj_consume_token(453);
               var4 = this.derivedColumnList();
               this.jj_consume_token(454);
               break;
            default:
               this.jj_la1[196] = this.jj_gen;
         }

         switch (this.jj_nt.kind) {
            case 59 -> var7 = this.optionalTableProperties();
            default -> this.jj_la1[197] = this.jj_gen;
         }

         FromVTI var14 = new FromVTI((MethodCallNode)var1.getJavaValueNode(), var12, var4, var7 != null ? (Properties)var7[0] : (Properties)null, this.getContextManager());
         return var14;
      } else if (this.jj_2_60(1)) {
         TableName var2 = this.qualifiedName(128);
         var7 = this.optionalTableClauses();
         FromBaseTable var13 = new FromBaseTable(var2, (String)var7[2], (ResultColumnList)var7[1], (Properties)var7[0], this.getContextManager());
         return var13;
      } else if (this.getToken(1).kind != 453 || this.getToken(2).kind != 225 && this.getToken(2).kind != 259) {
         switch (this.jj_nt.kind) {
            case 453:
               this.jj_consume_token(453);
               FromTable var6 = this.tableReferenceTypes(true);
               this.jj_consume_token(454);
               return var6;
            default:
               this.jj_la1[201] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      } else {
         SubqueryNode var9 = this.derivedTable();
         switch (this.jj_nt.kind) {
            case 72 -> this.jj_consume_token(72);
            default -> this.jj_la1[198] = this.jj_gen;
         }

         String var11 = this.identifier(128, true);
         switch (this.jj_nt.kind) {
            case 453:
               this.jj_consume_token(453);
               var4 = this.derivedColumnList();
               this.jj_consume_token(454);
               break;
            default:
               this.jj_la1[199] = this.jj_gen;
         }

         switch (this.jj_nt.kind) {
            case 59 -> var7 = this.optionalTableProperties();
            default -> this.jj_la1[200] = this.jj_gen;
         }

         FromSubquery var5 = new FromSubquery(var9.getResultSet(), var9.getOrderByList(), var9.getOffset(), var9.getFetchFirst(), var9.hasJDBClimitClause(), var11, var4, var7 != null ? (Properties)var7[0] : (Properties)null, this.getContextManager());
         return var5;
      }
   }

   public final ResultColumnList derivedColumnList() throws ParseException, StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());
      this.columnNameList(var1);
      return var1;
   }

   public final void columnNameList(ResultColumnList var1) throws ParseException, StandardException {
      this.columnNameItem(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.columnNameItem(var1);
               break;
            default:
               this.jj_la1[202] = this.jj_gen;
               return;
         }
      }
   }

   public final void columnNameItem(ResultColumnList var1) throws ParseException, StandardException {
      String var2 = this.identifier(128, true);
      ResultColumn var3 = new ResultColumn(var2, (ValueNode)null, this.getContextManager());
      var1.addResultColumn(var3);
   }

   public final void indexColumnList(List var1) throws ParseException, StandardException {
      this.indexColumnItem(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.indexColumnItem(var1);
               break;
            default:
               this.jj_la1[203] = this.jj_gen;
               return;
         }
      }
   }

   public final void indexColumnItem(List var1) throws ParseException, StandardException {
      String var2;
      var2 = this.identifier(128, true);
      label12:
      switch (this.jj_nt.kind) {
         case 73:
         case 123:
            switch (this.jj_nt.kind) {
               case 73:
                  this.jj_consume_token(73);
                  break label12;
               case 123:
                  this.jj_consume_token(123);
                  var2 = var2 + " ";
                  break label12;
               default:
                  this.jj_la1[204] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[205] = this.jj_gen;
      }

      var1.add(var2);
   }

   public final SubqueryNode derivedTable() throws ParseException, StandardException {
      this.jj_consume_token(453);
      SubqueryNode var1 = this.tableSubquery(0, (ValueNode)null);
      this.jj_consume_token(454);
      return var1;
   }

   public final TableOperatorNode joinedTableExpression(ResultSetNode var1, boolean var2) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 107:
            TableOperatorNode var5 = this.crossJoin(var1, var2);
            return var5;
         case 163:
         case 173:
         case 177:
         case 219:
            TableOperatorNode var4 = this.qualifiedJoin(var1, var2);
            return var4;
         case 186:
            TableOperatorNode var3 = this.naturalJoin(var1, var2);
            return var3;
         default:
            this.jj_la1[206] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final TableOperatorNode crossJoin(ResultSetNode var1, boolean var2) throws ParseException, StandardException {
      this.jj_consume_token(107);
      this.jj_consume_token(173);
      FromTable var3 = this.tableFactor();
      JoinNode var4 = this.newJoinNode(var1, var3, (ValueNode)null, (ResultColumnList)null, 1);
      ((TableOperatorNode)var4).setNestedInParens(var2);
      return var4;
   }

   public final TableOperatorNode qualifiedJoin(ResultSetNode var1, boolean var2) throws ParseException, StandardException {
      int var3 = 1;
      TableOperatorNode var5 = null;
      Object var6 = null;
      Object var7 = null;
      switch (this.jj_nt.kind) {
         case 163:
         case 177:
         case 219:
            var3 = this.joinType();
            break;
         default:
            this.jj_la1[207] = this.jj_gen;
      }

      this.jj_consume_token(173);
      FromTable var4 = this.tableReferenceTypes(var2);
      var6 = this.joinSpecification(var1, var4);
      ValueNode var8 = (ValueNode)((Object[])var6)[0];
      ResultColumnList var11 = (ResultColumnList)((Object[])var6)[1];
      if (var8 == null && var11 == null) {
         throw StandardException.newException("42Y11", new Object[]{JoinNode.joinTypeToString(var3)});
      } else {
         var5 = this.newJoinNode(var1, var4, var8, var11, var3);
         var5.setNestedInParens(var2);
         return var5;
      }
   }

   public final TableOperatorNode naturalJoin(ResultSetNode var1, boolean var2) throws ParseException, StandardException {
      int var3 = 1;
      this.jj_consume_token(186);
      switch (this.jj_nt.kind) {
         case 163:
         case 177:
         case 219:
            var3 = this.joinType();
            break;
         default:
            this.jj_la1[208] = this.jj_gen;
      }

      this.jj_consume_token(173);
      FromTable var4 = this.tableFactor();
      JoinNode var5 = this.newJoinNode(var1, var4, (ValueNode)null, (ResultColumnList)null, var3);
      var5.setNestedInParens(var2);
      var5.setNaturalJoin();
      return var5;
   }

   public final int joinType() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 163:
            this.jj_consume_token(163);
            return 1;
         case 177:
         case 219:
            int var1 = this.outerJoinType();
            switch (this.jj_nt.kind) {
               case 201 -> this.jj_consume_token(201);
               default -> this.jj_la1[209] = this.jj_gen;
            }

            return var1;
         default:
            this.jj_la1[210] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final int outerJoinType() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 177:
            this.jj_consume_token(177);
            return 3;
         case 219:
            this.jj_consume_token(219);
            return 4;
         default:
            this.jj_la1[211] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final Object[] joinSpecification(ResultSetNode var1, ResultSetNode var2) throws ParseException, StandardException {
      Object[] var3 = new Object[2];
      Object var4 = null;
      Object var5 = null;
      switch (this.jj_nt.kind) {
         case 195:
            ValueNode var7 = this.joinCondition();
            var3[0] = var7;
            var3[1] = var4;
            return var3;
         case 257:
            ResultColumnList var6 = this.namedColumnsJoin();
            var3[0] = var5;
            var3[1] = var6;
            return var3;
         default:
            this.jj_la1[212] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode joinCondition() throws ParseException, StandardException {
      this.jj_consume_token(195);
      ValueNode var1 = this.valueExpression();
      return var1;
   }

   public final ResultColumnList namedColumnsJoin() throws ParseException, StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());
      this.jj_consume_token(257);
      this.jj_consume_token(453);
      this.columnNameList(var1);
      this.jj_consume_token(454);
      return var1;
   }

   public final ResultSetNode tableValueConstructor() throws ParseException, StandardException {
      this.jj_consume_token(259);
      ResultSetNode var1 = this.tableValueConstructorList();
      return var1;
   }

   public final ResultSetNode tableValueConstructorList() throws ParseException, StandardException {
      ResultSetNode var1 = this.rowValueConstructor((ResultSetNode)null);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               var1 = this.rowValueConstructor(var1);
               break;
            default:
               this.jj_la1[213] = this.jj_gen;
               if (var1 instanceof UnionNode) {
                  ((UnionNode)var1).markTopTableConstructor();
               }

               return var1;
         }
      }
   }

   public final ValueNode datetimeValueFunction() throws ParseException, StandardException {
      boolean var1 = true;
      if (this.getToken(1).kind == 108 && this.getToken(2).kind == 285) {
         this.jj_consume_token(108);
         this.jj_consume_token(285);
         return new CurrentDatetimeOperatorNode(0, this.getContextManager());
      } else {
         switch (this.jj_nt.kind) {
            case 109:
               this.jj_consume_token(109);
               return new CurrentDatetimeOperatorNode(0, this.getContextManager());
            default:
               this.jj_la1[214] = this.jj_gen;
               if (this.getToken(1).kind == 108 && this.getToken(2).kind == 350) {
                  this.jj_consume_token(108);
                  this.jj_consume_token(350);
                  return new CurrentDatetimeOperatorNode(1, this.getContextManager());
               } else {
                  switch (this.jj_nt.kind) {
                     case 110:
                        this.jj_consume_token(110);
                        return new CurrentDatetimeOperatorNode(1, this.getContextManager());
                     default:
                        this.jj_la1[215] = this.jj_gen;
                        if (this.getToken(1).kind == 108 && this.getToken(2).kind == 351) {
                           this.jj_consume_token(108);
                           this.jj_consume_token(351);
                           return new CurrentDatetimeOperatorNode(2, this.getContextManager());
                        } else {
                           switch (this.jj_nt.kind) {
                              case 111:
                                 this.jj_consume_token(111);
                                 return new CurrentDatetimeOperatorNode(2, this.getContextManager());
                              default:
                                 this.jj_la1[216] = this.jj_gen;
                                 this.jj_consume_token(-1);
                                 throw new ParseException();
                           }
                        }
                  }
               }
         }
      }
   }

   public final ValueNode windowOrAggregateFunctionNode() throws ParseException, StandardException {
      WindowNode var1 = null;
      ValueNode var2 = null;
      switch (this.jj_nt.kind) {
         case 77:
         case 181:
         case 182:
         case 236:
            var2 = this.generalAggregate();
            if (this.jj_2_63(1)) {
               var1 = this.overClause();
            }

            if (var1 != null) {
               return new AggregateWindowFunctionNode(var1, var2, this.getContextManager());
            }

            return var2;
         case 105:
            this.jj_consume_token(105);
            this.jj_consume_token(453);
            switch (this.jj_nt.kind) {
               case 455:
                  this.jj_consume_token(455);
                  var2 = new AggregateNode((ValueNode)null, CountAggregateDefinition.class, false, "COUNT(*)", this.getContextManager());
                  break;
               default:
                  this.jj_la1[217] = this.jj_gen;
                  if (!this.jj_2_61(1)) {
                     this.jj_consume_token(-1);
                     throw new ParseException();
                  }

                  var2 = this.aggregateExpression("COUNT", CountAggregateDefinition.class);
            }

            this.jj_consume_token(454);
            if (this.jj_2_62(1)) {
               var1 = this.overClause();
            }

            if (var1 != null) {
               return new AggregateWindowFunctionNode(var1, var2, this.getContextManager());
            }

            return var2;
         case 374:
            this.jj_consume_token(374);
            this.jj_consume_token(453);
            this.jj_consume_token(454);
            var1 = this.overClause();
            return new RowNumberFunctionNode((ValueNode)null, var1, this.getContextManager());
         default:
            this.jj_la1[218] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final WindowNode overClause() throws ParseException, StandardException {
      OrderByList var2 = null;
      if (this.getToken(2).kind != 453 && this.getToken(2).kind != 480) {
         this.jj_consume_token(-1);
         throw new ParseException();
      } else {
         this.jj_consume_token(372);
         switch (this.jj_nt.kind) {
            case 453:
               this.jj_consume_token(453);
               switch (this.jj_nt.kind) {
                  case 200 -> var2 = this.orderByClause((ResultSetNode)null);
                  default -> this.jj_la1[219] = this.jj_gen;
               }

               this.jj_consume_token(454);
               return new WindowDefinitionNode((String)null, var2, this.getContextManager());
            default:
               this.jj_la1[220] = this.jj_gen;
               if (this.jj_2_64(1)) {
                  String var1 = this.identifier(128, true);
                  return new WindowReferenceNode(var1, this.getContextManager());
               } else {
                  this.jj_consume_token(-1);
                  throw new ParseException();
               }
         }
      }
   }

   public final AggregateNode aggregateExpression(String var1, Class var2) throws ParseException, StandardException {
      boolean var3 = false;
      if (this.jj_2_65(1)) {
         var3 = this.setQuantifier();
      }

      ValueNode var4 = this.additiveExpression((ValueNode)null, 0);
      return new AggregateNode(var4, var2, var3, var1, this.getContextManager());
   }

   public final AggregateNode generalAggregate() throws ParseException, StandardException {
      Token var1 = this.builtInAggregateType();
      this.jj_consume_token(453);
      AggregateNode var3 = this.aggregateExpression(aggName(var1), aggClass(var1));
      this.jj_consume_token(454);
      return var3;
   }

   public final Token builtInAggregateType() throws ParseException, StandardException {
      Token var1;
      switch (this.jj_nt.kind) {
         case 77:
            var1 = this.jj_consume_token(77);
            break;
         case 181:
            var1 = this.jj_consume_token(181);
            break;
         case 182:
            var1 = this.jj_consume_token(182);
            break;
         case 236:
            var1 = this.jj_consume_token(236);
            break;
         default:
            this.jj_la1[221] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      return var1;
   }

   public final ValueNode castSpecification() throws ParseException, StandardException {
      boolean var4 = true;
      TableName[] var5 = new TableName[1];
      this.jj_consume_token(87);
      this.jj_consume_token(453);
      ValueNode var2 = this.castOperand();
      this.jj_consume_token(72);
      DataTypeDescriptor var1 = this.dataTypeCast(var5);
      this.jj_consume_token(454);
      CastNode var6 = new CastNode(var2, var1, this.getContextManager());
      var6.setForExternallyGeneratedCASTnode();
      var6.setTargetUDTName(var5[0]);
      Object var7 = var6;
      if (var1.getTypeId().userType()) {
         var7 = new JavaToSQLValueNode(new SQLToJavaValueNode(var6, this.getContextManager()), this.getContextManager());
      }

      return (ValueNode)var7;
   }

   public final ValueNode nextValueExpression() throws ParseException, StandardException {
      this.jj_consume_token(188);
      this.jj_consume_token(258);
      this.jj_consume_token(144);
      TableName var1 = this.qualifiedName(128);
      this.checkVersion(180, "NEXT VALUE");
      return new NextSequenceNode(var1, this.getContextManager());
   }

   public final int charOrVarchar() throws ParseException {
      switch (this.jj_nt.kind) {
         case 88:
            this.jj_consume_token(88);
            return 1;
         case 261:
            this.jj_consume_token(261);
            return 12;
         default:
            this.jj_la1[222] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode castOperand() throws ParseException, StandardException {
      if (this.jj_2_66(1)) {
         ValueNode var1 = this.additiveExpression((ValueNode)null, 0);
         return var1;
      } else {
         switch (this.jj_nt.kind) {
            case 191:
               this.jj_consume_token(191);
               return new UntypedNullConstantNode(this.getContextManager());
            default:
               this.jj_la1[223] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final ParameterNode dynamicParameterSpecification() throws ParseException, StandardException {
      this.jj_consume_token(472);
      return this.makeParameterNode();
   }

   public final ValueNode whereClause(Token var1) throws ParseException, StandardException {
      ValueNode var2 = this.valueExpression();
      Token var3 = this.getToken(0);
      var2.setBeginOffset(var1.endOffset + 1);
      var2.setEndOffset(var3.endOffset);
      return var2;
   }

   public final GroupByList groupByClause() throws ParseException, StandardException {
      this.jj_consume_token(155);
      this.jj_consume_token(83);
      if (this.getToken(1).kind == 331 && this.getToken(2).kind == 453) {
         this.jj_consume_token(331);
         this.jj_consume_token(453);
         GroupByList var2 = this.groupingColumnReferenceList();
         this.jj_consume_token(454);
         var2.setRollup();
         return var2;
      } else if (this.jj_2_67(1)) {
         GroupByList var1 = this.groupingColumnReferenceList();
         return var1;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final GroupByList groupingColumnReferenceList() throws ParseException, StandardException {
      GroupByList var1 = new GroupByList(this.getContextManager());
      this.groupingColumnReference(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.groupingColumnReference(var1);
               break;
            default:
               this.jj_la1[224] = this.jj_gen;
               return var1;
         }
      }
   }

   public final void groupingColumnReference(GroupByList var1) throws ParseException, StandardException {
      ValueNode var2 = this.additiveExpression((ValueNode)null, 0);
      HasNodeVisitor var3 = new HasNodeVisitor(AggregateNode.class);
      var2.accept(var3);
      if (var3.hasNode()) {
         throw StandardException.newException("42Y26.S.0", new Object[0]);
      } else {
         CollectNodesVisitor var4 = new CollectNodesVisitor(StaticMethodCallNode.class);
         var2.accept(var4);

         for(StaticMethodCallNode var6 : var4.getList()) {
            var6.setAppearsInGroupBy();
         }

         if (var2.isParameterNode()) {
            throw StandardException.newException("42X01", new Object[]{"?"});
         } else {
            var1.addGroupByColumn(new GroupByColumn(var2, this.getContextManager()));
         }
      }
   }

   public final ValueNode havingClause() throws ParseException, StandardException {
      this.jj_consume_token(156);
      ValueNode var1 = this.valueExpression();
      return var1;
   }

   public final WindowList windowClause() throws ParseException, StandardException {
      WindowList var1 = new WindowList(this.getContextManager());
      this.jj_consume_token(266);
      var1 = this.windowDefinition(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               var1 = this.windowDefinition(var1);
               break;
            default:
               this.jj_la1[225] = this.jj_gen;
               return var1;
         }
      }
   }

   public final WindowList windowDefinition(WindowList var1) throws ParseException, StandardException {
      OrderByList var3 = null;
      String var2 = this.identifier(128, true);
      this.jj_consume_token(72);
      this.jj_consume_token(453);
      switch (this.jj_nt.kind) {
         case 200 -> var3 = this.orderByClause((ResultSetNode)null);
         default -> this.jj_la1[226] = this.jj_gen;
      }

      this.jj_consume_token(454);
      var1.addWindow(new WindowDefinitionNode(var2, var3, this.getContextManager()));
      return var1;
   }

   public final OptimizerPlan optimizerOverridePlan() throws ParseException, StandardException {
      this.jj_consume_token(359);
      OptimizerPlan var1 = this.optimizerPlan();
      return var1;
   }

   public final OptimizerPlan optimizerPlan() throws ParseException, StandardException {
      if (this.getToken(1).kind == 453) {
         OptimizerPlan var2 = this.optimizerJoin();
         return var2;
      } else if (this.jj_2_68(1)) {
         OptimizerPlan var1 = this.optimizerRowSource();
         return var1;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final OptimizerPlan optimizerJoin() throws ParseException, StandardException {
      this.jj_consume_token(453);
      OptimizerPlan var2 = this.optimizerPlan();
      JoinStrategy var1 = this.joinStrategy();
      OptimizerPlan var3 = this.optimizerPlan();
      this.jj_consume_token(454);
      return new OptimizerPlan.Join(var1, var2, var3);
   }

   public final JoinStrategy joinStrategy() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 455:
            this.jj_consume_token(455);
            return new NestedLoopJoinStrategy();
         case 456:
            this.jj_consume_token(456);
            return new HashJoinStrategy();
         default:
            this.jj_la1[227] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final OptimizerPlan optimizerRowSource() throws ParseException, StandardException {
      Object var1 = null;
      Token var2 = null;
      TableName var3 = this.qualifiedName(128);
      switch (this.jj_nt.kind) {
         case 453:
            var2 = this.jj_consume_token(453);
            this.jj_consume_token(454);
            break;
         default:
            this.jj_la1[228] = this.jj_gen;
      }

      return (OptimizerPlan)(var2 != null ? new OptimizerPlan.TableFunctionRS(var3.getSchemaName(), var3.getTableName()) : new OptimizerPlan.ConglomerateRS(var3.getSchemaName(), var3.getTableName()));
   }

   public final StatementNode schemaDefinition() throws ParseException, StandardException {
      Object var1 = null;
      String var2 = null;
      this.jj_consume_token(222);
      if (this.jj_2_69(1)) {
         String var3 = this.identifier(128, true);
         switch (this.jj_nt.kind) {
            case 76:
               this.jj_consume_token(76);
               var2 = this.identifier(128, true);
               break;
            default:
               this.jj_la1[229] = this.jj_gen;
         }

         if (var2 != null) {
            this.checkVersion(140, "AUTHORIZATION");
         }

         if (var3.startsWith("SYS")) {
            throw StandardException.newException("42939", new Object[]{var3});
         } else {
            return new CreateSchemaNode(var3, var2, this.getContextManager());
         }
      } else {
         switch (this.jj_nt.kind) {
            case 76:
               this.jj_consume_token(76);
               var2 = this.identifier(128, true);
               this.checkVersion(140, "AUTHORIZATION");
               if (var2.startsWith("SYS")) {
                  throw StandardException.newException("42939", new Object[]{var2});
               }

               return new CreateSchemaNode(var2, var2, this.getContextManager());
            default:
               this.jj_la1[230] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final StatementNode roleDefinition() throws ParseException, StandardException {
      Object var1 = null;
      this.jj_consume_token(373);
      String var2 = this.identifier(128, true);
      this.checkVersion(170, "ROLES");
      this.checkSqlStandardAccess("CREATE ROLE");
      if (var2.startsWith("SYS")) {
         throw StandardException.newException("4293A", new Object[]{var2});
      } else {
         return new CreateRoleNode(var2, this.getContextManager());
      }
   }

   public final StatementNode sequenceDefinition() throws ParseException, StandardException {
      Object var1 = null;
      DataTypeDescriptor var2 = null;
      Long var3 = null;
      Long var4 = null;
      Long var5 = null;
      Long var6 = null;
      Boolean var7 = Boolean.FALSE;
      Object[] var8 = new Object[6];
      this.jj_consume_token(432);
      TableName var9 = this.qualifiedName(128);

      while(this.jj_2_70(1)) {
         this.sequenceGeneratorOption(var8);
      }

      this.checkVersion(180, "SEQUENCES");
      if (var8[0] != null) {
         var2 = (DataTypeDescriptor)var8[0];
      }

      if (var8[1] != null) {
         var3 = (Long)var8[1];
      }

      if (var8[2] != null) {
         var4 = (Long)var8[2];
      }

      if (var8[3] != null && !(var8[3] instanceof Boolean)) {
         var5 = (Long)var8[3];
      }

      if (var8[4] != null && !(var8[4] instanceof Boolean)) {
         var6 = (Long)var8[4];
      }

      if (var8[5] != null) {
         var7 = (Boolean)var8[5];
      }

      return new CreateSequenceNode(var9, var2, var3, var4, var5, var6, var7, this.getContextManager());
   }

   public final void sequenceGeneratorOption(Object[] var1) throws ParseException, StandardException {
      Object var2 = null;
      byte var3 = -1;
      Boolean[] var4 = new Boolean[1];
      Object var5 = null;
      Object var6 = null;
      Token var9;
      switch (this.jj_nt.kind) {
         case 72:
            var9 = this.jj_consume_token(72);
            var2 = this.exactIntegerType();
            var3 = 0;
            break;
         case 294:
            var9 = this.jj_consume_token(294);
            this.jj_consume_token(83);
            var2 = this.exactIntegerObject();
            var3 = 2;
            break;
         case 346:
            var9 = this.jj_consume_token(346);
            this.jj_consume_token(267);
            var2 = this.exactIntegerObject();
            var3 = 1;
            break;
         default:
            this.jj_la1[233] = this.jj_gen;
            if (this.jj_2_71(1)) {
               switch (this.jj_nt.kind) {
                  case 308:
                     var9 = this.jj_consume_token(308);
                     var2 = this.exactIntegerObject();
                     break;
                  default:
                     this.jj_la1[231] = this.jj_gen;
                     if (this.getToken(2).kind != 308) {
                        this.jj_consume_token(-1);
                        throw new ParseException();
                     }

                     this.jj_consume_token(189);
                     var9 = this.jj_consume_token(308);
                     var2 = Boolean.FALSE;
               }

               var3 = 3;
            } else if (this.jj_2_72(1)) {
               switch (this.jj_nt.kind) {
                  case 310:
                     var9 = this.jj_consume_token(310);
                     var2 = this.exactIntegerObject();
                     break;
                  default:
                     this.jj_la1[232] = this.jj_gen;
                     if (this.getToken(2).kind != 310) {
                        this.jj_consume_token(-1);
                        throw new ParseException();
                     }

                     this.jj_consume_token(189);
                     var9 = this.jj_consume_token(310);
                     var2 = Boolean.FALSE;
               }

               var3 = 4;
            } else {
               switch (this.jj_nt.kind) {
                  case 189:
                  case 283:
                     var9 = this.cycleClause(var4);
                     var2 = var4[0];
                     var3 = 5;
                     break;
                  default:
                     this.jj_la1[234] = this.jj_gen;
                     this.jj_consume_token(-1);
                     throw new ParseException();
               }
            }
      }

      if (var3 != -1) {
         if (var1[var3] != null) {
            throw StandardException.newException("42XAJ", new Object[]{var9.image});
         }

         var1[var3] = var2;
      }

   }

   public final Token cycleClause(Boolean[] var1) throws ParseException, StandardException {
      Token var2 = null;
      switch (this.jj_nt.kind) {
         case 189:
            this.jj_consume_token(189);
            var2 = this.jj_consume_token(283);
            var1[0] = Boolean.FALSE;
            return var2;
         case 283:
            var2 = this.jj_consume_token(283);
            var1[0] = Boolean.TRUE;
            return var2;
         default:
            this.jj_la1[235] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final Long exactIntegerObject() throws ParseException, StandardException {
      long var1 = this.exactNumber();
      return var1;
   }

   public final Long stepValue() throws ParseException, StandardException {
      this.jj_consume_token(294);
      this.jj_consume_token(83);
      long var1 = this.exactNumber();
      return var1;
   }

   public final StatementNode dropSequenceStatement() throws ParseException, StandardException {
      this.jj_consume_token(432);
      TableName var1 = this.qualifiedName(128);
      this.jj_consume_token(217);
      this.checkVersion(180, "SEQUENCES");
      return new DropSequenceNode(var1, this.getContextManager());
   }

   public final StatementNode tableDefinition() throws ParseException, StandardException {
      char var1 = 'R';
      Properties var2 = null;
      ResultColumnList var5 = null;
      boolean var7 = true;
      this.jj_consume_token(239);
      TableName var3 = this.qualifiedName(128);
      if (this.getToken(1).kind == 453 && this.getToken(3).kind != 458 && this.getToken(3).kind != 454) {
         TableElementList var4 = this.tableElementList();
         switch (this.jj_nt.kind) {
            case 59:
               var2 = this.propertyList(false);
               this.jj_consume_token(64);
               break;
            default:
               this.jj_la1[236] = this.jj_gen;
         }

         return new CreateTableNode(var3, var4, var2, var1, this.getContextManager());
      } else {
         switch (this.jj_nt.kind) {
            case 72:
            case 453:
               switch (this.jj_nt.kind) {
                  case 453:
                     this.jj_consume_token(453);
                     var5 = this.tableColumnList();
                     this.jj_consume_token(454);
                     break;
                  default:
                     this.jj_la1[237] = this.jj_gen;
               }

               this.jj_consume_token(72);
               ResultSetNode var6 = this.queryExpression((ResultSetNode)null, 0);
               this.jj_consume_token(267);
               switch (this.jj_nt.kind) {
                  case 189:
                     this.jj_consume_token(189);
                     var7 = false;
                     break;
                  default:
                     this.jj_la1[238] = this.jj_gen;
               }

               this.jj_consume_token(284);
               if (var7) {
                  throw StandardException.newException("0A000.S", new Object[]{"WITH DATA"});
               } else {
                  HasNodeVisitor var8 = new HasNodeVisitor(ParameterNode.class);
                  var6.accept(var8);
                  if (var8.hasNode()) {
                     throw StandardException.newException("42X99", new Object[0]);
                  }

                  return new CreateTableNode(var3, var5, var6, this.getContextManager());
               }
            default:
               this.jj_la1[239] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final ResultColumnList tableColumnList() throws ParseException, StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());
      this.columnNameList(var1);
      return var1;
   }

   public final Properties propertyList(boolean var1) throws ParseException, StandardException {
      FormatableProperties var2 = new FormatableProperties();
      this.jj_consume_token(59);
      StringTokenizer var3 = new StringTokenizer(this.getToken(1).image, ",");

      while(var3.hasMoreTokens()) {
         String var4 = var3.nextToken();
         StringTokenizer var5 = new StringTokenizer(var4, "=", true);
         if (var5.countTokens() != 3) {
            throw StandardException.newException("XCY04.S", new Object[0]);
         }

         String var6 = var5.nextToken().trim();
         if (!var5.nextToken().equals("=")) {
            throw StandardException.newException("XCY04.S", new Object[0]);
         }

         String var7 = var5.nextToken().trim();
         verifyImageLength(var7);
         if (var7.startsWith("'") && var7.endsWith("'")) {
            var7 = StringUtil.compressQuotes(var7.substring(1, var7.length() - 1), "''");
         } else if (var7.startsWith("\"") && var7.endsWith("\"")) {
            var7 = StringUtil.compressQuotes(var7.substring(1, var7.length() - 1), "\"\"");
         } else {
            var7 = var7.toUpperCase();
         }

         if (((Properties)var2).put(var6, var7) != null) {
            throw StandardException.newException("42Y49", new Object[]{var6});
         }
      }

      if (!var1) {
         this.checkInternalFeature("DERBY-PROPERTIES");
      }

      return var2;
   }

   public final char DB2lockGranularityClause() throws ParseException, StandardException {
      this.jj_consume_token(305);
      char var1 = this.lockGranularity();
      return var1;
   }

   public final char lockGranularity() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 239:
            this.jj_consume_token(239);
            return 'T';
         case 332:
            this.jj_consume_token(332);
            return 'R';
         default:
            this.jj_la1[240] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode indexDefinition() throws ParseException, StandardException {
      Boolean var1 = Boolean.FALSE;
      Properties var2 = null;
      ArrayList var5 = new ArrayList();
      switch (this.jj_nt.kind) {
         case 252 -> var1 = this.unique();
         default -> this.jj_la1[241] = this.jj_gen;
      }

      this.jj_consume_token(400);
      TableName var3 = this.qualifiedName(128);
      this.jj_consume_token(195);
      TableName var4 = this.qualifiedName(128);
      this.jj_consume_token(453);
      this.indexColumnList(var5);
      this.jj_consume_token(454);
      switch (this.jj_nt.kind) {
         case 59:
            var2 = this.propertyList(false);
            this.jj_consume_token(64);
            break;
         default:
            this.jj_la1[242] = this.jj_gen;
      }

      if (var3.getSchemaName() == null) {
         var3.setSchemaName(var4.getSchemaName());
      } else if (var4.getSchemaName() == null) {
         var4.setSchemaName(var3.getSchemaName());
      } else if (!var3.getSchemaName().equals(var4.getSchemaName())) {
         throw StandardException.newException("X0Y26.S", new Object[]{var3, var4});
      }

      return new CreateIndexNode(var1, "BTREE", var3, var4, var5, var2, this.getContextManager());
   }

   public final Boolean unique() throws ParseException, StandardException {
      this.jj_consume_token(252);
      return Boolean.TRUE;
   }

   public final StatementNode procedureDefinition() throws ParseException, StandardException {
      Object[] var2 = new Object[12];
      this.jj_consume_token(211);
      TableName var1 = this.qualifiedName(128);
      var2[0] = this.procedureParameterList(var2);

      while(true) {
         this.routineElement(true, false, var2);
         switch (this.jj_nt.kind) {
            case 139:
            case 189:
            case 190:
            case 277:
            case 282:
            case 288:
            case 289:
            case 299:
            case 312:
            case 330:
            case 417:
            case 421:
            case 426:
            case 437:
               break;
            default:
               this.jj_la1[243] = this.jj_gen;
               this.checkRequiredRoutineClause(JAVA_ROUTINE_CLAUSES, var2);
               return this.getCreateAliasNode(var1, (String)var2[4], var2, 'P');
         }
      }
   }

   public final void routineElement(boolean var1, boolean var2, Object[] var3) throws ParseException, StandardException {
      // $FF: Couldn't be decompiled
   }

   public final Boolean calledOnNullInput(boolean var1) throws ParseException, StandardException {
      Boolean var2;
      switch (this.jj_nt.kind) {
         case 277:
            this.jj_consume_token(277);
            var2 = Boolean.TRUE;
            break;
         case 330:
            this.jj_consume_token(330);
            this.jj_consume_token(191);
            if (var1) {
               throw StandardException.newException("42X01", new Object[]{"RETURNS NULL ON NULL INPUT"});
            }

            var2 = Boolean.FALSE;
            break;
         default:
            this.jj_la1[247] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      this.jj_consume_token(195);
      this.jj_consume_token(191);
      this.jj_consume_token(164);
      return var2;
   }

   public final boolean routineSecurityClause() throws ParseException, StandardException {
      boolean var1 = false;
      switch (this.jj_nt.kind) {
         case 287:
            this.jj_consume_token(287);
            var1 = true;
            break;
         case 298:
            this.jj_consume_token(298);
            var1 = false;
            break;
         default:
            this.jj_la1[248] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      return var1;
   }

   public final Short parameterStyle(boolean var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 391:
            this.jj_consume_token(391);
            this.checkVersion(220, "DERBY");
            if (var1) {
               throw StandardException.newException("42ZB2", new Object[0]);
            }

            return Short.valueOf((short)2);
         case 392:
            this.jj_consume_token(392);
            if (!var1) {
               throw StandardException.newException("42ZB1", new Object[0]);
            }

            return Short.valueOf((short)1);
         case 402:
            this.jj_consume_token(402);
            if (var1) {
               throw StandardException.newException("42ZB2", new Object[0]);
            }

            return Short.valueOf((short)0);
         default:
            this.jj_la1[249] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final Object[] procedureParameterList(Object[] var1) throws ParseException, StandardException {
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      Object[] var5 = new Object[3];
      Boolean var6 = null;
      this.jj_consume_token(453);
      if (this.jj_2_73(1)) {
         this.procedureParameterDefinition(var2, var3, var4);

         label20:
         while(true) {
            switch (this.jj_nt.kind) {
               case 458:
                  this.jj_consume_token(458);
                  this.procedureParameterDefinition(var2, var3, var4);
                  break;
               default:
                  this.jj_la1[250] = this.jj_gen;
                  switch (this.jj_nt.kind) {
                     case 479 -> var6 = this.ellipsis();
break label20;
                     default -> this.jj_la1[251] = this.jj_gen;
break label20;
                  }
            }
         }
      }

      this.jj_consume_token(454);
      var1[11] = var6;
      var5[0] = var2;
      var5[1] = var3;
      var5[2] = var4;
      return var5;
   }

   public final void procedureParameterDefinition(List var1, List var2, List var3) throws ParseException, StandardException {
      String var5 = "";
      Integer var6 = this.inoutParameter();
      if (this.dataTypeCheck(2)) {
         var5 = this.identifier(128, true);
      }

      DataTypeDescriptor var4 = this.dataTypeDDL();
      var1.add(var5);
      var2.add(var4.getCatalogType());
      var3.add(var6);
   }

   public final Integer inoutParameter() throws ParseException {
      byte var1 = 1;
      switch (this.jj_nt.kind) {
         case 160:
         case 296:
         case 416:
            switch (this.jj_nt.kind) {
               case 160:
                  this.jj_consume_token(160);
                  return Integer.valueOf(var1);
               case 296:
                  this.jj_consume_token(296);
                  var1 = 2;
                  return Integer.valueOf(var1);
               case 416:
                  this.jj_consume_token(416);
                  var1 = 4;
                  return Integer.valueOf(var1);
               default:
                  this.jj_la1[252] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[253] = this.jj_gen;
            return Integer.valueOf(var1);
      }
   }

   public final StatementNode functionDefinition() throws ParseException, StandardException {
      Object[] var3 = new Object[12];
      this.jj_consume_token(149);
      TableName var1 = this.qualifiedName(128);
      var3[0] = this.functionParameterList(var3);
      this.jj_consume_token(330);
      TypeDescriptor var2 = this.functionReturnDataType();

      while(true) {
         this.routineElement(false, var2.isRowMultiSet(), var3);
         switch (this.jj_nt.kind) {
            case 139:
            case 189:
            case 190:
            case 277:
            case 282:
            case 288:
            case 289:
            case 299:
            case 312:
            case 330:
            case 417:
            case 421:
            case 426:
            case 437:
               break;
            default:
               this.jj_la1[254] = this.jj_gen;
               var3[9] = var2;
               this.checkRequiredRoutineClause(JAVA_ROUTINE_CLAUSES, var3);
               return this.getCreateAliasNode(var1, (String)var3[4], var3, 'F');
         }
      }
   }

   public final Object[] functionParameterList(Object[] var1) throws ParseException, StandardException {
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      Object[] var5 = new Object[3];
      Boolean var6 = null;
      this.jj_consume_token(453);
      if (this.jj_2_74(1)) {
         this.functionParameterDefinition(var2, var3, var4);

         label20:
         while(true) {
            switch (this.jj_nt.kind) {
               case 458:
                  this.jj_consume_token(458);
                  this.functionParameterDefinition(var2, var3, var4);
                  break;
               default:
                  this.jj_la1[255] = this.jj_gen;
                  switch (this.jj_nt.kind) {
                     case 479 -> var6 = this.ellipsis();
break label20;
                     default -> this.jj_la1[256] = this.jj_gen;
break label20;
                  }
            }
         }
      }

      this.jj_consume_token(454);
      var1[11] = var6;
      var5[0] = var2;
      var5[1] = var3;
      var5[2] = var4;
      return var5;
   }

   public final Boolean ellipsis() throws ParseException, StandardException {
      this.jj_consume_token(479);
      this.checkVersion(220, "...");
      return Boolean.TRUE;
   }

   public final void functionParameterDefinition(List var1, List var2, List var3) throws ParseException, StandardException {
      String var5 = "";
      if (this.dataTypeCheck(2)) {
         var5 = this.identifier(128, true);
      }

      DataTypeDescriptor var4 = this.dataTypeDDL();
      var1.add(var5);
      var2.add(var4.getCatalogType());
      var3.add(1);
   }

   public final TypeDescriptor functionReturnDataType() throws ParseException, StandardException {
      TypeDescriptor var1;
      if (this.jj_2_75(1)) {
         var1 = this.catalogType();
      } else {
         switch (this.jj_nt.kind) {
            case 239:
               var1 = this.functionTableType();
               break;
            default:
               this.jj_la1[257] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }

      return var1;
   }

   public final TypeDescriptor functionTableType() throws ParseException, StandardException {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      this.jj_consume_token(239);
      this.jj_consume_token(453);
      this.functionTableReturnColumn(var1, var2);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.functionTableReturnColumn(var1, var2);
               break;
            default:
               this.jj_la1[258] = this.jj_gen;
               this.jj_consume_token(454);
               this.checkVersion(160, "CREATE FUNCTION...RETURNS TABLE");
               int var5 = var1.size();
               String[] var3 = new String[var5];
               var1.toArray(var3);
               TypeDescriptor[] var4 = new TypeDescriptor[var5];
               var2.toArray(var4);

               for(int var6 = 0; var6 < var5; ++var6) {
                  if (var4[var6].getJDBCTypeId() == 2009) {
                     throw StandardException.newException("42ZB3", new Object[0]);
                  }
               }

               return DataTypeDescriptor.getRowMultiSet(var3, var4);
         }
      }
   }

   public final void functionTableReturnColumn(ArrayList var1, ArrayList var2) throws ParseException, StandardException {
      String var3 = this.identifier(128, true);
      DataTypeDescriptor var4 = this.dataTypeDDL();
      var1.add(var3);
      var2.add(var4.getCatalogType());
   }

   public final StatementNode udtDefinition() throws ParseException, StandardException {
      this.jj_consume_token(355);
      TableName var1 = this.qualifiedName(128);
      this.jj_consume_token(139);
      this.jj_consume_token(317);
      String var2 = this.string();
      this.jj_consume_token(299);
      this.jj_consume_token(402);
      this.checkVersion(180, "CREATE TYPE");
      return this.getCreateAliasNode(var1, var2, (Object)null, 'A');
   }

   public final StatementNode aggregateDefinition() throws ParseException, StandardException {
      DataTypeDescriptor var3 = null;
      Object[] var4 = new Object[2];
      this.jj_consume_token(391);
      this.jj_consume_token(383);
      TableName var1 = this.qualifiedName(128);
      this.jj_consume_token(144);
      DataTypeDescriptor var2 = this.dataTypeDDL();
      switch (this.jj_nt.kind) {
         case 330:
            this.jj_consume_token(330);
            var3 = this.dataTypeDDL();
            break;
         default:
            this.jj_la1[259] = this.jj_gen;
      }

      this.jj_consume_token(139);
      this.jj_consume_token(317);
      String var5 = this.string();
      this.checkVersion(220, "CREATE DERBY AGGREGATE");
      if (var3 == null) {
         var3 = var2;
      }

      var4[0] = var2.getCatalogType();
      var4[1] = var3.getCatalogType();
      return this.getCreateAliasNode(var1, var5, var4, 'G');
   }

   public final StatementNode viewDefinition(Token var1) throws ParseException, StandardException {
      ResultColumnList var3 = null;
      Object var6 = null;
      OrderByList var8 = null;
      ValueNode[] var9 = new ValueNode[2];
      boolean var10 = false;
      this.jj_consume_token(263);
      TableName var5 = this.qualifiedName(128);
      switch (this.jj_nt.kind) {
         case 453:
            this.jj_consume_token(453);
            var3 = this.viewColumnList();
            this.jj_consume_token(454);
            break;
         default:
            this.jj_la1[260] = this.jj_gen;
      }

      this.jj_consume_token(72);
      ResultSetNode var4 = this.queryExpression((ResultSetNode)null, 0);
      switch (this.jj_nt.kind) {
         case 200 -> var8 = this.orderByClause(var4);
         default -> this.jj_la1[261] = this.jj_gen;
      }

      var10 = this.offsetFetchFirstClause(var9);
      byte var2 = 0;
      Token var7 = this.getToken(0);
      HasNodeVisitor var11 = new HasNodeVisitor(ParameterNode.class);
      var4.accept(var11);
      if (var11.hasNode()) {
         throw StandardException.newException("42X98", new Object[0]);
      } else {
         return new CreateViewNode(var5, var3, var4, var2, StringUtil.slice(this.statementSQLText, var1.beginOffset, var7.endOffset, false), var8, var9[0], var9[1], var10, this.getContextManager());
      }
   }

   public final ResultColumnList viewColumnList() throws ParseException, StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());
      this.columnNameList(var1);
      return var1;
   }

   public final StatementNode triggerDefinition() throws ParseException, StandardException {
      Boolean var2 = Boolean.FALSE;
      Token[] var5 = new Token[1];
      Object var7 = null;
      ResultColumnList var13 = new ResultColumnList(this.getContextManager());
      List var14 = null;
      ValueNode var15 = null;
      Token var16 = null;
      Token var17 = null;
      this.jj_consume_token(442);
      TableName var4 = this.qualifiedName(128);
      Boolean var1 = this.beforeOrAfter();
      int var11 = this.triggerEvent(var13);
      this.jj_consume_token(195);
      TableName var3 = this.qualifiedName(128);
      switch (this.jj_nt.kind) {
         case 423 -> var14 = this.triggerReferencingClause();
         default -> this.jj_la1[262] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 144:
            this.jj_consume_token(144);
            this.jj_consume_token(396);
            var2 = this.rowOrStatement();
            break;
         default:
            this.jj_la1[263] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 408:
            this.jj_consume_token(408);
            this.jj_consume_token(390);
            break;
         default:
            this.jj_la1[264] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 358:
            this.jj_consume_token(358);
            var16 = this.jj_consume_token(453);
            var15 = this.valueExpression();
            var17 = this.jj_consume_token(454);
            break;
         default:
            this.jj_la1[265] = this.jj_gen;
      }

      StatementNode var12 = this.proceduralStatement(var5);
      int var10 = this.getToken(0).endOffset;
      int var9 = var5[0].beginOffset;
      var12.setBeginOffset(var9);
      var12.setEndOffset(var10);
      if (var1 && var12 instanceof DMLModStatementNode) {
         throw StandardException.newException("42Z9D", new Object[]{var12.statementToString(), "BEFORE"});
      } else {
         HasNodeVisitor var18 = new HasNodeVisitor(ParameterNode.class);
         var12.accept(var18);
         if (var15 != null) {
            var15.accept(var18);
         }

         if (var18.hasNode()) {
            throw StandardException.newException("42Y27", new Object[0]);
         } else {
            String var19 = StringUtil.slice(this.statementSQLText, var9, var10, false);
            String var20 = null;
            if (var15 != null) {
               this.checkVersion(230, "WHEN");
               int var21 = var16.endOffset + 1;
               int var22 = var17.beginOffset - 1;
               var15.setBeginOffset(var21);
               var15.setEndOffset(var22);
               var20 = StringUtil.slice(this.statementSQLText, var21, var22, false);
            }

            return new CreateTriggerNode(var4, var3, var11, var13, var1, var2, true, var14, var15, var20, var12, var19, this.getContextManager());
         }
      }
   }

   public final StatementNode synonymDefinition() throws ParseException, StandardException {
      this.jj_consume_token(348);
      TableName var1 = this.qualifiedName(128);
      this.jj_consume_token(144);
      TableName var2 = this.qualifiedName(128);
      this.checkVersion(130, "CREATE SYNONYM");
      return this.getCreateAliasNode(var1, var2, (Object)null, 'S');
   }

   public final Boolean beforeOrAfter() throws ParseException {
      switch (this.jj_nt.kind) {
         case 189:
            this.jj_consume_token(189);
            this.jj_consume_token(84);
            this.jj_consume_token(384);
            return Boolean.TRUE;
         case 382:
            this.jj_consume_token(382);
            return Boolean.FALSE;
         default:
            this.jj_la1[266] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final int triggerEvent(ResultColumnList var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 122:
            this.jj_consume_token(122);
            return 2;
         case 166:
            this.jj_consume_token(166);
            return 4;
         case 254:
            this.jj_consume_token(254);
            switch (this.jj_nt.kind) {
               case 194:
                  this.jj_consume_token(194);
                  this.columnNameList(var1);
                  break;
               default:
                  this.jj_la1[267] = this.jj_gen;
            }

            return 1;
         default:
            this.jj_la1[268] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final Boolean rowOrStatement() throws ParseException {
      switch (this.jj_nt.kind) {
         case 332:
            this.token = this.jj_consume_token(332);
            return Boolean.TRUE;
         case 347:
            this.token = this.jj_consume_token(347);
            return Boolean.FALSE;
         default:
            this.jj_la1[269] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final List triggerReferencingClause() throws ParseException, StandardException {
      ArrayList var1 = new ArrayList();
      this.jj_consume_token(423);
      this.triggerReferencingExpression(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 409:
            case 410:
            case 414:
            case 415:
               this.triggerReferencingExpression(var1);
               break;
            case 411:
            case 412:
            case 413:
            default:
               this.jj_la1[270] = this.jj_gen;
               return var1;
         }
      }
   }

   public final void triggerReferencingExpression(List var1) throws ParseException, StandardException {
      boolean var3;
      boolean var4;
      var3 = true;
      var4 = true;
      label25:
      switch (this.jj_nt.kind) {
         case 409:
            this.jj_consume_token(409);
            switch (this.jj_nt.kind) {
               case 239:
               case 332:
                  switch (this.jj_nt.kind) {
                     case 239:
                        this.jj_consume_token(239);
                        var4 = false;
                        break label25;
                     case 332:
                        this.jj_consume_token(332);
                        break label25;
                     default:
                        this.jj_la1[271] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                  }
               default:
                  this.jj_la1[272] = this.jj_gen;
                  break label25;
            }
         case 410:
            this.jj_consume_token(410);
            var4 = false;
            break;
         case 411:
         case 412:
         case 413:
         default:
            this.jj_la1[275] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
         case 414:
            this.jj_consume_token(414);
            var3 = false;
            switch (this.jj_nt.kind) {
               case 239:
               case 332:
                  switch (this.jj_nt.kind) {
                     case 239:
                        this.jj_consume_token(239);
                        var4 = false;
                        break label25;
                     case 332:
                        this.jj_consume_token(332);
                        break label25;
                     default:
                        this.jj_la1[273] = this.jj_gen;
                        this.jj_consume_token(-1);
                        throw new ParseException();
                  }
               default:
                  this.jj_la1[274] = this.jj_gen;
                  break label25;
            }
         case 415:
            this.jj_consume_token(415);
            var3 = false;
            var4 = false;
      }

      this.jj_consume_token(72);
      String var2 = this.identifier(128, true);
      var1.add(new TriggerReferencingStruct(var4, var3, var2));
   }

   public final ValueNode defaultClause(long[] var1, String var2) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 119:
         case 267:
            switch (this.jj_nt.kind) {
               case 267 -> this.jj_consume_token(267);
               default -> this.jj_la1[276] = this.jj_gen;
            }

            Token var4 = this.jj_consume_token(119);
            ValueNode var6 = this.defaultOption(var4, var1, var2);
            return var6;
         case 292:
            ValueNode var3 = this.generatedColumnOption(var1);
            return var3;
         default:
            this.jj_la1[277] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode defaultNullOnlyClause() throws ParseException, StandardException {
      this.jj_consume_token(119);
      this.jj_consume_token(191);
      return new UntypedNullConstantNode(this.getContextManager());
   }

   public final ValueNode generatedColumnOption(long[] var1) throws ParseException, StandardException {
      ValueNode var2 = null;
      var1[0] = 1L;
      var1[1] = 1L;
      var1[2] = 1L;
      var1[3] = 0L;
      this.jj_consume_token(292);
      switch (this.jj_nt.kind) {
         case 83:
            var2 = this.generatedByDefault(var1);
            return var2;
         case 274:
            var2 = this.generatedAlways(var1);
            return var2;
         default:
            this.jj_la1[278] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode generatedAlways(long[] var1) throws ParseException, StandardException {
      ValueNode var2 = null;
      this.jj_consume_token(274);
      if (this.getToken(1).kind == 72 && this.getToken(2).kind == 158) {
         this.asIdentity(var1);
         return var2;
      } else if (this.getToken(1).kind == 72 && this.getToken(2).kind == 453) {
         var2 = this.generationClause();
         return var2;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final ValueNode generatedByDefault(long[] var1) throws ParseException, StandardException {
      ValueNode var2 = null;
      this.jj_consume_token(83);
      this.jj_consume_token(119);
      this.asIdentity(var1);
      this.checkVersion(130, "GENERATED BY DEFAULT");
      var2 = new DefaultNode(this.getContextManager());
      return var2;
   }

   public final void asIdentity(long[] var1) throws ParseException, StandardException {
      Object[] var2 = new Object[3];
      this.jj_consume_token(72);
      this.jj_consume_token(158);
      switch (this.jj_nt.kind) {
         case 453:
            this.jj_consume_token(453);

            while(true) {
               this.identityColumnOption(var1, var2);
               switch (this.jj_nt.kind) {
                  case 189:
                  case 283:
                  case 294:
                  case 346:
                  case 458:
                     break;
                  default:
                     this.jj_la1[279] = this.jj_gen;
                     this.jj_consume_token(454);
                     return;
               }
            }
         default:
            this.jj_la1[280] = this.jj_gen;
      }
   }

   public final ValueNode generationClause() throws ParseException, StandardException {
      Object var1 = null;
      Object var2 = null;
      Object var3 = null;
      this.jj_consume_token(72);
      Token var5 = this.jj_consume_token(453);
      ValueNode var4 = this.valueExpression();
      Token var6 = this.jj_consume_token(454);
      this.checkVersion(170, "GENERATED COLUMN");
      return new GenerationClauseNode(var4, StringUtil.slice(this.statementSQLText, var5.endOffset + 1, var6.beginOffset - 1, true), this.getContextManager());
   }

   public final void identityColumnOption(long[] var1, Object[] var2) throws ParseException, StandardException {
      byte var3 = -1;
      long var4 = 1L;
      long var6 = 1L;
      long var8 = 0L;
      Token var10 = null;
      switch (this.jj_nt.kind) {
         case 189:
            var10 = this.jj_consume_token(189);
            this.jj_consume_token(283);
            var8 = 0L;
            var1[4] = var8;
            var3 = 2;
            break;
         case 283:
            var10 = this.jj_consume_token(283);
            var8 = 1L;
            var1[4] = var8;
            var3 = 2;
            break;
         case 294:
            var10 = this.jj_consume_token(294);
            this.jj_consume_token(83);
            var6 = this.exactNumber();
            var1[1] = var6;
            var1[3] = 0L;
            var3 = 1;
            break;
         case 346:
            var10 = this.jj_consume_token(346);
            this.jj_consume_token(267);
            var4 = this.exactNumber();
            var1[0] = var4;
            var1[3] = 0L;
            var3 = 0;
            break;
         case 458:
            this.jj_consume_token(458);
            if (this.getToken(1).kind != 458 && this.getToken(1).kind != 454) {
               return;
            }

            var3 = -2;
            break;
         default:
            this.jj_la1[281] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      if (var3 != -1) {
         if (var3 == -2) {
            throw StandardException.newException("42X01", new Object[]{"Encountered an \",\" "});
         }

         if (var2[var3] != null) {
            throw StandardException.newException("42XAJ", new Object[]{var10.image});
         }

         var2[var3] = var10;
      }

   }

   public final long whetherCycle() throws ParseException, StandardException {
      return 1L;
   }

   public final ValueNode defaultOption(Token var1, long[] var2, String var3) throws ParseException, StandardException {
      Object var5 = null;
      Object var6 = null;
      if (this.getToken(1).kind == 191 && this.getToken(2).kind != 460 && this.getToken(2).kind != 463) {
         this.jj_consume_token(191);
         return new UntypedNullConstantNode(this.getContextManager());
      } else if (this.jj_2_76(1)) {
         ValueNode var7 = this.DB2DefaultOption(var3);
         Token var4 = this.getToken(0);
         var7.setBeginOffset(var1.beginOffset);
         var7.setEndOffset(var4.endOffset);
         var7 = new DefaultNode(var7, StringUtil.slice(this.statementSQLText, var1.beginOffset + 7, var4.endOffset, true), this.getContextManager());
         return var7;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final ValueNode DB2DefaultOption(String var1) throws ParseException, StandardException {
      if (this.getToken(2).kind != 222 && this.getToken(2).kind != 436) {
         switch (this.jj_nt.kind) {
            case 112:
            case 226:
            case 256:
               ValueNode var3 = this.userNode();
               return var3;
            case 363:
               ValueNode var2 = this.currentRoleNode();
               return var2;
            default:
               this.jj_la1[283] = this.jj_gen;
               if (this.getToken(1).kind != 285 && this.getToken(1).kind != 350 && this.getToken(1).kind != 351) {
                  if (this.getToken(2).kind != 453 && (this.getToken(4).kind != 453 || this.getToken(2).kind == 458)) {
                     if (this.jj_2_77(1)) {
                        ValueNode var7 = this.datetimeValueFunction();
                        return var7;
                     } else {
                        switch (this.jj_nt.kind) {
                           case 140:
                           case 249:
                           case 451:
                           case 457:
                           case 459:
                           case 487:
                           case 490:
                           case 491:
                           case 492:
                              ValueNode var6 = this.literal();
                              return var6;
                           default:
                              this.jj_la1[284] = this.jj_gen;
                              this.jj_consume_token(-1);
                              throw new ParseException();
                        }
                     }
                  } else {
                     ValueNode var5 = this.miscBuiltins();
                     throw StandardException.newException("42894", new Object[]{var1});
                  }
               } else {
                  ValueNode var4 = this.miscBuiltins();
                  return var4;
               }
         }
      } else {
         this.jj_consume_token(108);
         switch (this.jj_nt.kind) {
            case 222:
               this.jj_consume_token(222);
               break;
            case 436:
               this.jj_consume_token(436);
               break;
            default:
               this.jj_la1[282] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }

         return new SpecialFunctionNode(2, this.getContextManager());
      }
   }

   public final ValueNode literal() throws ParseException, StandardException {
      String var1 = "";
      switch (this.jj_nt.kind) {
         case 140:
         case 249:
            Token var2 = this.booleanLiteral();
            return new BooleanConstantNode(StringUtil.SQLEqualsIgnoreCase(var2.image, "true"), this.getContextManager());
         case 451:
            ValueNode var8 = this.dateTimeLiteral();
            return var8;
         case 457:
         case 459:
         case 487:
         case 492:
            switch (this.jj_nt.kind) {
               case 457:
               case 459:
                  var1 = this.sign();
                  break;
               default:
                  this.jj_la1[285] = this.jj_gen;
            }

            ValueNode var7 = this.numericLiteral(var1);
            return var7;
         case 490:
            CharConstantNode var6 = this.stringLiteral();
            return var6;
         case 491:
            ValueNode var5 = this.hexLiteral();
            return var5;
         default:
            this.jj_la1[286] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final NumericConstantNode intLiteral() throws ParseException, StandardException {
      String var2 = null;
      switch (this.jj_nt.kind) {
         case 457:
         case 459:
            var2 = this.sign();
            break;
         default:
            this.jj_la1[287] = this.jj_gen;
      }

      Token var1 = this.jj_consume_token(487);
      String var4 = var1.image;
      if (var2 != null && var2.equals("-")) {
         var4 = var2.concat(var4);
      }

      try {
         NumericConstantNode var3 = this.getNumericNode(var4, true);
         return var3;
      } catch (NumberFormatException var6) {
         throw StandardException.newException("42X20", new Object[0]);
      }
   }

   public final ValueNode numericLiteral(String var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 487:
            Token var12 = this.jj_consume_token(487);
            String var3 = var12.image;
            if (var1.equals("-")) {
               var3 = var1.concat(var3);
            }

            return this.getNumericNode(var3, false);
         case 492:
            Token var2 = this.jj_consume_token(492);
            StringBuffer var4 = new StringBuffer(var1);
            var4.append(var2.image);
            String var5 = var4.toString();
            int var6 = var5.indexOf(69);
            if (var6 == -1) {
               var6 = var5.indexOf(101);
            }

            if (var5.length() > 30) {
               throw StandardException.newException("42820", new Object[]{var5, "DOUBLE"});
            } else {
               int var7 = var5.substring(0, var6).indexOf(46);
               if (var7 == -1) {
                  var4.insert(var6, '.');
                  var5 = var4.toString();
                  ++var6;
               }

               Double var8;
               try {
                  var8 = Double.valueOf(var5);
               } catch (NumberFormatException var11) {
                  throw StandardException.newException("22018", new Object[]{"DOUBLE"});
               }

               double var9 = var8;
               if (var9 == (double)0.0F && Double.parseDouble(var5.substring(0, var6 - 1)) != (double)0.0F) {
                  throw StandardException.newException("22003", new Object[]{"DOUBLE"});
               } else {
                  if (!Double.isNaN(var9) && !Double.isInfinite(var9)) {
                     return new NumericConstantNode(TypeId.getBuiltInTypeId(8), var8, this.getContextManager());
                  }

                  throw StandardException.newException("22003", new Object[]{"DOUBLE"});
               }
            }
         default:
            this.jj_la1[288] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode dateTimeLiteral() throws ParseException, StandardException {
      this.jj_consume_token(451);
      ValueNode var1 = this.escapedDateTimeLiteral();
      this.jj_consume_token(452);
      return var1;
   }

   public final ValueNode escapedDateTimeLiteral() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 114:
            this.jj_consume_token(114);
            ValueNode var3 = this.bareDateLiteral();
            return var3;
         case 238:
            this.jj_consume_token(238);
            ValueNode var2 = this.bareTimeLiteral();
            return var2;
         case 250:
            this.jj_consume_token(250);
            ValueNode var1 = this.bareTimestampLiteral();
            return var1;
         default:
            this.jj_la1[289] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode bareDateLiteral() throws ParseException, StandardException {
      String var1 = this.string();
      return new UserTypeConstantNode(this.getLanguageConnectionContext().getDataValueFactory().getDateValue(var1, true), this.getContextManager());
   }

   public final ValueNode bareTimeLiteral() throws ParseException, StandardException {
      String var1 = this.string();
      return new UserTypeConstantNode(this.getLanguageConnectionContext().getDataValueFactory().getTimeValue(var1, true), this.getContextManager());
   }

   public final ValueNode bareTimestampLiteral() throws ParseException, StandardException {
      String var1 = this.string();
      return new UserTypeConstantNode(this.getLanguageConnectionContext().getDataValueFactory().getTimestampValue(var1, true), this.getContextManager());
   }

   public final String string() throws ParseException, StandardException {
      Token var1 = this.jj_consume_token(490);
      verifyImageLength(var1.image);
      return StringUtil.compressQuotes(var1.image.substring(1, var1.image.length() - 1), "''");
   }

   public final CharConstantNode stringLiteral() throws ParseException, StandardException {
      Token var1 = this.jj_consume_token(490);
      if (var1.image.length() - 2 > 32672) {
         throw StandardException.newException("54002", new Object[]{StringUtil.formatForPrint(var1.image)});
      } else {
         String var2 = StringUtil.compressQuotes(var1.image.substring(1, var1.image.length() - 1), "''");
         return new CharConstantNode(var2, this.getContextManager());
      }
   }

   public final ValueNode hexLiteral() throws ParseException, StandardException {
      Token var1 = this.jj_consume_token(491);
      String var2 = var1.image;
      if (var2.length() - 3 > 16336) {
         throw StandardException.newException("54002", new Object[]{StringUtil.formatForPrint(var2)});
      } else if ((var2.length() - 3) % 2 == 1) {
         throw StandardException.newException("42606", new Object[]{StringUtil.formatForPrint(var2)});
      } else {
         int var3 = (var2.length() - 3) / 2;
         return new VarbitConstantNode(var2.substring(2, var2.length() - 1), var3, this.getContextManager());
      }
   }

   public final TableName constraintNameDefinition() throws ParseException, StandardException {
      this.jj_consume_token(100);
      TableName var1 = this.qualifiedName(128);
      return var1;
   }

   public final ConstraintDefinitionNode checkConstraintDefinition(TableName var1, String var2) throws ParseException, StandardException {
      ResultColumnList var6 = null;
      this.jj_consume_token(91);
      Token var3 = this.jj_consume_token(453);
      ValueNode var5 = this.valueExpression();
      Token var4 = this.jj_consume_token(454);
      if (var2 != null) {
         var6 = new ResultColumnList(this.getContextManager());
         var6.addElement(new ResultColumn(var2, (ValueNode)null, this.getContextManager()));
      }

      var5.setBeginOffset(var3.beginOffset);
      var5.setEndOffset(var4.endOffset);
      return new ConstraintDefinitionNode(var1, 4, var6, (Properties)null, var5, StringUtil.slice(this.statementSQLText, var3.beginOffset, var4.endOffset, true), 2, 5, this.getContextManager());
   }

   public final StatementNode spsRenameStatement() throws ParseException, StandardException {
      this.jj_consume_token(424);
      StatementNode var1;
      switch (this.jj_nt.kind) {
         case 96:
            var1 = this.renameColumnStatement();
            break;
         case 239:
            var1 = this.renameTableStatement();
            break;
         case 400:
            var1 = this.renameIndexStatement();
            break;
         default:
            this.jj_la1[290] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      return var1;
   }

   public final StatementNode renameTableStatement() throws ParseException, StandardException {
      this.jj_consume_token(239);
      TableName var2 = this.qualifiedName(128);
      this.jj_consume_token(243);
      String var3 = this.identifier(128, true);
      return new RenameNode(var2, (String)null, var3, false, 1, this.getContextManager());
   }

   public final StatementNode renameIndexStatement() throws ParseException, StandardException {
      this.jj_consume_token(400);
      String var1 = this.identifier(128, true);
      this.jj_consume_token(243);
      String var2 = this.identifier(128, true);
      return new RenameNode((Object)null, var1, var2, false, 3, this.getContextManager());
   }

   public final StatementNode renameColumnStatement() throws ParseException, StandardException {
      this.jj_consume_token(96);
      ColumnReference var2 = this.columnReference();
      this.jj_consume_token(243);
      String var1 = this.identifier(128, true);
      if (var2.getQualifiedTableName() == null) {
         throw StandardException.newException("42Y55", new Object[]{"RENAME COLUMN", var2.getColumnName()});
      } else {
         return new RenameNode(var2.getQualifiedTableName(), var2.getColumnName(), var1, false, 2, this.getContextManager());
      }
   }

   public final StatementNode lockStatement() throws ParseException, StandardException {
      this.jj_consume_token(405);
      this.jj_consume_token(239);
      TableName var2 = this.qualifiedName(128);
      this.jj_consume_token(160);
      boolean var1 = this.lockMode();
      this.jj_consume_token(408);
      return new LockTableNode(var2, var1, this.getContextManager());
   }

   public final boolean lockMode() throws ParseException {
      switch (this.jj_nt.kind) {
         case 398:
            this.jj_consume_token(398);
            return true;
         case 435:
            this.jj_consume_token(435);
            return false;
         default:
            this.jj_la1[291] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode execStatement() throws ParseException, StandardException {
      this.jj_consume_token(137);
      this.jj_consume_token(347);
      TableName var1 = this.qualifiedName(128);
      return new ExecSPSNode(var1, this.getContextManager());
   }

   public final TransactionStatementNode setIsolationStatement() throws ParseException, StandardException {
      this.setIsolationHeader();
      label12:
      switch (this.jj_nt.kind) {
         case 243:
         case 467:
            switch (this.jj_nt.kind) {
               case 243:
                  this.jj_consume_token(243);
                  break label12;
               case 467:
                  this.jj_consume_token(467);
                  break label12;
               default:
                  this.jj_la1[292] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[293] = this.jj_gen;
      }

      TransactionStatementNode var1 = this.transactionMode();
      return var1;
   }

   public final void setIsolationHeader() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 172:
            this.jj_consume_token(172);
            break;
         default:
            this.jj_la1[294] = this.jj_gen;
            if (this.getToken(1).kind != 108 || this.getToken(2).kind != 172) {
               this.jj_consume_token(-1);
               throw new ParseException();
            }

            this.jj_consume_token(108);
            this.jj_consume_token(172);
      }

   }

   public final TransactionStatementNode transactionMode() throws ParseException, StandardException {
      int var1 = this.isolationLevelDB2OrReset();
      return new SetTransactionIsolationNode(var1, this.getContextManager());
   }

   public final int isolationLevelDB2OrReset() throws ParseException {
      switch (this.jj_nt.kind) {
         case 425:
            this.jj_consume_token(425);
            return 0;
         default:
            this.jj_la1[295] = this.jj_gen;
            if (this.jj_2_78(1)) {
               int var1 = this.isolationLevelDB2();
               return var1;
            } else {
               this.jj_consume_token(-1);
               throw new ParseException();
            }
      }
   }

   public final int isolationLevelDB2() throws ParseException {
      switch (this.jj_nt.kind) {
         case 113:
            this.jj_consume_token(113);
            this.jj_consume_token(439);
            return 2;
         case 328:
         case 336:
            switch (this.jj_nt.kind) {
               case 328:
                  this.jj_consume_token(328);
                  this.jj_consume_token(213);
                  break;
               case 336:
                  this.jj_consume_token(336);
                  break;
               default:
                  this.jj_la1[296] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }

            return 4;
         case 388:
         case 429:
         case 430:
         case 445:
            int var1 = this.isolationLevelDB2Abbrev();
            return var1;
         case 394:
            this.jj_consume_token(394);
            this.jj_consume_token(213);
            return 1;
         default:
            this.jj_la1[297] = this.jj_gen;
            if (this.getToken(1).kind == 213 && this.getToken(2).kind == 280) {
               this.jj_consume_token(213);
               this.jj_consume_token(280);
               return 2;
            } else if (this.getToken(1).kind == 213 && this.getToken(2).kind == 356) {
               this.jj_consume_token(213);
               this.jj_consume_token(356);
               return 1;
            } else {
               this.jj_consume_token(-1);
               throw new ParseException();
            }
      }
   }

   public final int isolationLevelDB2Abbrev() throws ParseException {
      switch (this.jj_nt.kind) {
         case 388:
            this.jj_consume_token(388);
            return 2;
         case 429:
            this.jj_consume_token(429);
            return 4;
         case 430:
            this.jj_consume_token(430);
            return 3;
         case 445:
            this.jj_consume_token(445);
            return 1;
         default:
            this.jj_la1[298] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final int isolationLevel() throws ParseException {
      this.jj_consume_token(172);
      this.jj_consume_token(302);
      int var1 = this.levelOfIsolation();
      return var1;
   }

   public final int levelOfIsolation() throws ParseException {
      switch (this.jj_nt.kind) {
         case 213:
            this.jj_consume_token(213);
            return this.levelOfIsolationRead();
         case 328:
            this.jj_consume_token(328);
            this.jj_consume_token(213);
            return 3;
         case 336:
            this.jj_consume_token(336);
            return 4;
         default:
            this.jj_la1[299] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final int levelOfIsolationRead() throws ParseException {
      switch (this.jj_nt.kind) {
         case 280:
            this.jj_consume_token(280);
            return 2;
         case 356:
            this.jj_consume_token(356);
            return 1;
         default:
            this.jj_la1[300] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ValueNode simpleValueSpecification() throws ParseException, StandardException {
      ValueNode var1 = this.literal();
      return var1;
   }

   public final StatementNode setRoleStatement() throws ParseException, StandardException {
      this.jj_consume_token(373);
      StatementNode var1 = this.setRoleSpecification();
      if (this.parameterList != null && this.parameterList.size() > 0) {
         this.setUpAndLinkParameters();
         ParameterNode var2 = (ParameterNode)this.parameterList.get(0);
         var2.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(12), false, 128));
      }

      return var1;
   }

   public final StatementNode setRoleSpecification() throws ParseException, StandardException {
      Object var1 = null;
      this.checkVersion(170, "ROLES");
      this.checkSqlStandardAccess("SET ROLE");
      switch (this.jj_nt.kind) {
         case 371:
            this.jj_consume_token(371);
            return new SetRoleNode((String)var1, 0, this.getContextManager());
         default:
            this.jj_la1[301] = this.jj_gen;
            if (this.jj_2_79(1)) {
               String var4 = this.identifier(128, true);
               return new SetRoleNode(var4, 0, this.getContextManager());
            } else {
               switch (this.jj_nt.kind) {
                  case 472:
                     this.dynamicParameterSpecification();
                     return new SetRoleNode((String)null, 1, this.getContextManager());
                  case 490:
                     String var2 = this.string();
                     var2 = IdUtil.parseRoleId(var2);
                     return new SetRoleNode(var2, 0, this.getContextManager());
                  default:
                     this.jj_la1[302] = this.jj_gen;
                     this.jj_consume_token(-1);
                     throw new ParseException();
               }
            }
      }
   }

   public final StatementNode setSchemaStatement() throws ParseException, StandardException {
      this.setSchemaHeader();
      switch (this.jj_nt.kind) {
         case 467 -> this.jj_consume_token(467);
         default -> this.jj_la1[303] = this.jj_gen;
      }

      StatementNode var1 = this.setSchemaValues();
      if (this.parameterList != null && this.parameterList.size() > 0) {
         this.setUpAndLinkParameters();
         ParameterNode var2 = (ParameterNode)this.parameterList.get(0);
         var2.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(12), false, 128));
      }

      return var1;
   }

   public final void setSchemaHeader() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 222:
            this.jj_consume_token(222);
            break;
         default:
            this.jj_la1[305] = this.jj_gen;
            if (this.getToken(1).kind != 108 || this.getToken(2).kind != 222 && this.getToken(2).kind != 436) {
               this.jj_consume_token(-1);
               throw new ParseException();
            }

            this.jj_consume_token(108);
            switch (this.jj_nt.kind) {
               case 222:
                  this.jj_consume_token(222);
                  break;
               case 436:
                  this.jj_consume_token(436);
                  break;
               default:
                  this.jj_la1[304] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
      }

   }

   public final StatementNode setSchemaValues() throws ParseException, StandardException {
      if (this.jj_2_80(1)) {
         String var2 = this.identifier(128, true);
         return new SetSchemaNode(var2, 0, this.getContextManager());
      } else {
         switch (this.jj_nt.kind) {
            case 256:
               this.jj_consume_token(256);
               return new SetSchemaNode((String)null, 1, this.getContextManager());
            case 472:
               this.dynamicParameterSpecification();
               return new SetSchemaNode((String)null, 2, this.getContextManager());
            case 490:
               String var1 = this.string();
               IdUtil.checkIdentifierLengthLimit(var1, 128);
               return new SetSchemaNode(var1, 0, this.getContextManager());
            default:
               this.jj_la1[306] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final StatementNode setMessageLocaleStatement() throws ParseException, StandardException {
      this.jj_consume_token(406);
      String var1 = this.string();
      this.getContextManager().setMessageLocale(var1);
      return new NOPStatementNode(this.getContextManager());
   }

   public final ValueNode valueSpecification() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 112:
         case 226:
         case 256:
         case 363:
         case 472:
            ValueNode var8 = this.generalValueSpecification();
            return var8;
         case 140:
         case 249:
         case 451:
         case 457:
         case 459:
         case 487:
         case 490:
         case 491:
         case 492:
            ValueNode var1 = this.literal();
            return var1;
         case 192:
            this.jj_consume_token(192);
            this.jj_consume_token(453);
            ValueNode var2 = this.valueExpression();
            this.jj_consume_token(458);
            ValueNode var3 = this.valueExpression();
            this.jj_consume_token(454);
            ContextManager var5 = this.getContextManager();
            ValueNodeList var6 = new ValueNodeList(var5);
            ValueNodeList var7 = new ValueNodeList(var5);
            CachedValueNode var11 = new CachedValueNode(var2);
            var6.addElement(new BinaryRelationalOperatorNode(0, var11, var3, false, var5));
            var7.addElement(new UntypedNullConstantNode(var5));
            var7.addElement(var11);
            return new ConditionalNode((CachedValueNode)null, var6, var7, var5);
         default:
            this.jj_la1[307] = this.jj_gen;
            if (this.getToken(1).kind == 86 && this.getToken(2).kind == 358) {
               this.jj_consume_token(86);
               ConditionalNode var10 = this.searchedCaseExpression();
               return var10;
            } else {
               switch (this.jj_nt.kind) {
                  case 86:
                     this.jj_consume_token(86);
                     ValueNode var4 = this.valueExpression();
                     ConditionalNode var9 = this.simpleCaseExpression(new CachedValueNode(var4));
                     return var9;
                  default:
                     this.jj_la1[308] = this.jj_gen;
                     this.jj_consume_token(-1);
                     throw new ParseException();
               }
            }
      }
   }

   public final ConditionalNode searchedCaseExpression() throws ParseException, StandardException {
      ContextManager var1 = this.getContextManager();
      ValueNodeList var2 = new ValueNodeList(var1);
      ValueNodeList var3 = new ValueNodeList(var1);
      Object var4 = null;

      while(true) {
         this.whenThenExpression(var2, var3);
         switch (this.jj_nt.kind) {
            case 358:
               break;
            default:
               this.jj_la1[309] = this.jj_gen;
               switch (this.jj_nt.kind) {
                  case 130:
                     this.jj_consume_token(130);
                     var4 = this.thenElseExpression();
                     break;
                  default:
                     this.jj_la1[310] = this.jj_gen;
               }

               this.jj_consume_token(131);
               if (var4 == null) {
                  var4 = new UntypedNullConstantNode(var1);
               }

               var3.addElement((QueryTreeNode)var4);
               return new ConditionalNode((CachedValueNode)null, var2, var3, var1);
         }
      }
   }

   public final void whenThenExpression(ValueNodeList var1, ValueNodeList var2) throws ParseException, StandardException {
      this.jj_consume_token(358);
      ValueNode var3 = this.valueExpression();
      this.jj_consume_token(349);
      ValueNode var4 = this.thenElseExpression();
      var1.addElement(var3);
      var2.addElement(var4);
   }

   public final ValueNode thenElseExpression() throws ParseException, StandardException {
      if (this.getToken(1).kind == 191) {
         this.jj_consume_token(191);
         return new UntypedNullConstantNode(this.getContextManager());
      } else if (this.jj_2_81(1)) {
         ValueNode var1 = this.valueExpression();
         return var1;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final ConditionalNode simpleCaseExpression(CachedValueNode var1) throws ParseException, StandardException {
      ContextManager var2 = this.getContextManager();
      ValueNodeList var3 = new ValueNodeList(var2);
      ValueNodeList var4 = new ValueNodeList(var2);
      Object var5 = null;

      while(true) {
         this.simpleWhenClause(var1, var3, var4);
         switch (this.jj_nt.kind) {
            case 358:
               break;
            default:
               this.jj_la1[311] = this.jj_gen;
               switch (this.jj_nt.kind) {
                  case 130:
                     this.jj_consume_token(130);
                     var5 = this.thenElseExpression();
                     break;
                  default:
                     this.jj_la1[312] = this.jj_gen;
               }

               this.jj_consume_token(131);
               if (var5 == null) {
                  var5 = new UntypedNullConstantNode(var2);
               }

               var4.addElement((QueryTreeNode)var5);
               return new ConditionalNode(var1, var3, var4, var2);
         }
      }
   }

   public final void simpleWhenClause(ValueNode var1, ValueNodeList var2, ValueNodeList var3) throws ParseException, StandardException {
      this.jj_consume_token(358);
      ValueNode var4 = this.whenOperandList(var1);
      this.jj_consume_token(349);
      ValueNode var5 = this.thenElseExpression();
      var2.addElement(var4);
      var3.addElement(var5);
   }

   public final ValueNode whenOperandList(ValueNode var1) throws ParseException, StandardException {
      ValueNode var3 = null;
      ValueNode var2 = this.whenOperand(var1);
      switch (this.jj_nt.kind) {
         case 458:
            this.jj_consume_token(458);
            var3 = this.whenOperandList(var1);
            break;
         default:
            this.jj_la1[313] = this.jj_gen;
      }

      return (ValueNode)(var3 == null ? var2 : new OrNode(var2, var3, this.getContextManager()));
   }

   public final ValueNode whenOperand(ValueNode var1) throws ParseException, StandardException {
      ContextManager var2 = this.getContextManager();
      if (this.remainingPredicateFollows()) {
         ValueNode var4 = this.remainingPredicate(var1);
         return var4;
      } else if (this.jj_2_82(1)) {
         ValueNode var3 = this.valueExpression();
         return new BinaryRelationalOperatorNode(0, var1, var3, false, var2);
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final TableElementNode tableConstraintDefinition() throws ParseException, StandardException {
      Properties var1 = null;
      TableName var3 = null;
      switch (this.jj_nt.kind) {
         case 100 -> var3 = this.constraintNameDefinition();
         default -> this.jj_la1[314] = this.jj_gen;
      }

      ConstraintDefinitionNode var2 = this.tableConstraint(var3);
      switch (this.jj_nt.kind) {
         case 59:
            var1 = this.propertyList(false);
            this.jj_consume_token(64);
            break;
         default:
            this.jj_la1[315] = this.jj_gen;
      }

      if (var1 != null) {
         var2.setProperties(var1);
      }

      return var2;
   }

   public final ConstraintDefinitionNode tableConstraint(TableName var1) throws ParseException, StandardException {
      boolean[] var3 = null;
      ConstraintDefinitionNode var2;
      switch (this.jj_nt.kind) {
         case 91:
            var2 = this.checkConstraintDefinition(var1, (String)null);
            break;
         case 145:
            var2 = this.referentialConstraintDefinition(var1);
            break;
         case 208:
         case 252:
            var2 = this.uniqueConstraintDefinition(var1);
            break;
         default:
            this.jj_la1[316] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      if (this.jj_2_83(1)) {
         var3 = this.constraintCharacteristics();
      }

      if (var3 != null) {
         var2.setCharacteristics(var3);
      }

      return var2;
   }

   public final ConstraintDefinitionNode uniqueConstraintDefinition(TableName var1) throws ParseException, StandardException {
      int var2 = this.uniqueSpecification((String)null);
      this.jj_consume_token(453);
      ResultColumnList var3 = this.uniqueColumnList();
      this.jj_consume_token(454);
      return new ConstraintDefinitionNode(var1, var2, var3, (Properties)null, (ValueNode)null, (String)null, 2, 5, this.getContextManager());
   }

   public final int uniqueSpecification(String var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 208:
            this.jj_consume_token(208);
            this.jj_consume_token(174);
            return 2;
         case 252:
            this.jj_consume_token(252);
            return 3;
         default:
            this.jj_la1[317] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final ResultColumnList uniqueColumnList() throws ParseException, StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());
      this.columnNameList(var1);
      return var1;
   }

   public final ConstraintDefinitionNode referentialConstraintDefinition(TableName var1) throws ParseException, StandardException {
      ResultColumnList var2 = new ResultColumnList(this.getContextManager());
      ResultColumnList var3 = new ResultColumnList(this.getContextManager());
      int[] var5 = new int[]{2, 2};
      this.jj_consume_token(145);
      this.jj_consume_token(174);
      this.jj_consume_token(453);
      this.columnNameList(var2);
      this.jj_consume_token(454);
      TableName var4 = this.referencesSpecification(var3, var5);
      return new FKConstraintDefinitionNode(var1, var4, var2, var3, var5, this.getContextManager());
   }

   public final TableName referencesSpecification(ResultColumnList var1, int[] var2) throws ParseException, StandardException {
      TableName var3 = null;
      this.jj_consume_token(215);
      var3 = this.referencedTableAndColumns(var1);
      switch (this.jj_nt.kind) {
         case 195:
            this.jj_consume_token(195);
            this.referentialTriggeredAction(var2);
            break;
         default:
            this.jj_la1[318] = this.jj_gen;
      }

      return var3;
   }

   public final TableName referencedTableAndColumns(ResultColumnList var1) throws ParseException, StandardException {
      TableName var2 = null;
      var2 = this.qualifiedName(128);
      switch (this.jj_nt.kind) {
         case 453:
            this.jj_consume_token(453);
            this.columnNameList(var1);
            this.jj_consume_token(454);
            break;
         default:
            this.jj_la1[319] = this.jj_gen;
      }

      return var2;
   }

   public final void referentialTriggeredAction(int[] var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 122:
            var1[0] = this.deleteRule();
            switch (this.jj_nt.kind) {
               case 195:
                  this.jj_consume_token(195);
                  var1[1] = this.updateRule();
                  return;
               default:
                  this.jj_la1[321] = this.jj_gen;
                  return;
            }
         case 254:
            var1[1] = this.updateRule();
            switch (this.jj_nt.kind) {
               case 195:
                  this.jj_consume_token(195);
                  var1[0] = this.deleteRule();
                  return;
               default:
                  this.jj_la1[320] = this.jj_gen;
                  return;
            }
         default:
            this.jj_la1[322] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final int updateRule() throws ParseException {
      this.jj_consume_token(254);
      int var1 = this.updateReferentialAction();
      return var1;
   }

   public final int deleteRule() throws ParseException {
      this.jj_consume_token(122);
      int var1 = this.deleteReferentialAction();
      return var1;
   }

   public final int updateReferentialAction() throws ParseException {
      switch (this.jj_nt.kind) {
         case 189:
            this.jj_consume_token(189);
            this.jj_consume_token(273);
            return 2;
         case 217:
            this.jj_consume_token(217);
            return 1;
         default:
            this.jj_la1[323] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final int deleteReferentialAction() throws ParseException {
      switch (this.jj_nt.kind) {
         case 84:
            this.jj_consume_token(84);
            return 0;
         case 189:
            this.jj_consume_token(189);
            this.jj_consume_token(273);
            return 2;
         case 217:
            this.jj_consume_token(217);
            return 1;
         case 227:
            this.jj_consume_token(227);
            switch (this.jj_nt.kind) {
               case 119:
                  this.jj_consume_token(119);
                  return 4;
               case 191:
                  this.jj_consume_token(191);
                  return 3;
               default:
                  this.jj_la1[324] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[325] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final boolean[] constraintCharacteristics() throws ParseException, StandardException {
      boolean var1 = false;
      boolean var2 = false;
      boolean var3 = true;
      boolean[] var4 = new boolean[]{false, false};
      switch (this.jj_nt.kind) {
         case 162:
            var2 = this.initiallyDeferred();
            var4[1] = true;
            if (this.getToken(1).kind == 120 || this.getToken(2).kind == 120) {
               var1 = this.deferrable();
               var4[0] = true;
            }

            if (this.getToken(1).kind == 290 || this.getToken(2).kind == 290) {
               var3 = this.constraintEnforcement();
            }
            break;
         default:
            this.jj_la1[327] = this.jj_gen;
            if (this.getToken(1).kind != 120 && this.getToken(2).kind != 120) {
               if (this.getToken(1).kind != 290 && this.getToken(2).kind != 290) {
                  this.jj_consume_token(-1);
                  throw new ParseException();
               }

               var3 = this.constraintEnforcement();
            } else {
               var1 = this.deferrable();
               var4[0] = true;
               switch (this.jj_nt.kind) {
                  case 162:
                     var2 = this.initiallyDeferred();
                     var4[1] = true;
                     break;
                  default:
                     this.jj_la1[326] = this.jj_gen;
               }

               if (this.getToken(1).kind == 290 || this.getToken(2).kind == 290) {
                  var3 = this.constraintEnforcement();
               }
            }
      }

      if (!var4[0] && var4[1] && var2) {
         var1 = true;
      }

      if (var4[0] && !var1 && var4[1] && var2) {
         throw StandardException.newException("42X97", new Object[0]);
      } else {
         return new boolean[]{var1, var2, var3};
      }
   }

   public final boolean initiallyDeferred() throws ParseException {
      this.jj_consume_token(162);
      switch (this.jj_nt.kind) {
         case 121:
            this.jj_consume_token(121);
            return true;
         case 159:
            this.jj_consume_token(159);
            return false;
         default:
            this.jj_la1[328] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final boolean deferrable() throws ParseException {
      boolean var1 = true;
      switch (this.jj_nt.kind) {
         case 190:
            this.jj_consume_token(190);
            var1 = false;
            break;
         default:
            this.jj_la1[329] = this.jj_gen;
      }

      this.jj_consume_token(120);
      return var1;
   }

   public final boolean constraintEnforcement() throws ParseException {
      boolean var1 = true;
      switch (this.jj_nt.kind) {
         case 190:
            this.jj_consume_token(190);
            var1 = false;
            break;
         default:
            this.jj_la1[330] = this.jj_gen;
      }

      this.jj_consume_token(290);
      return var1;
   }

   public final void columnConstraintDefinition(DataTypeDescriptor[] var1, TableElementList var2, String var3) throws ParseException, StandardException {
      TableName var6 = null;
      boolean[] var7 = null;
      switch (this.jj_nt.kind) {
         case 100 -> var6 = this.constraintNameDefinition();
         default -> this.jj_la1[331] = this.jj_gen;
      }

      ConstraintDefinitionNode var5 = this.columnConstraint(var6, var1, var3);
      if (this.jj_2_84(1)) {
         var7 = this.constraintCharacteristics();
      }

      if (var7 != null) {
         if (var5 == null) {
            throw StandardException.newException("42XAN", new Object[0]);
         }

         var5.setCharacteristics(var7);
      }

      if (var5 != null) {
         var2.addTableElement(var5);
      }
   }

   public final ConstraintDefinitionNode columnConstraint(TableName var1, DataTypeDescriptor[] var2, String var3) throws ParseException, StandardException {
      Properties var5 = null;
      ResultColumnList var7 = new ResultColumnList(this.getContextManager());
      int[] var9 = new int[]{2, 2};
      switch (this.jj_nt.kind) {
         case 91:
            ConstraintDefinitionNode var12 = this.checkConstraintDefinition(var1, var3);
            return var12;
         case 190:
            this.jj_consume_token(190);
            this.jj_consume_token(191);
            if (var2[0] == null) {
               throw StandardException.newException("42XAB", new Object[0]);
            }

            var2[0] = var2[0].getNullabilityType(false);
            return null;
         case 208:
         case 252:
            int var4 = this.uniqueSpecification(var3);
            switch (this.jj_nt.kind) {
               case 59:
                  var5 = this.propertyList(false);
                  this.jj_consume_token(64);
                  break;
               default:
                  this.jj_la1[332] = this.jj_gen;
            }

            ResultColumnList var10 = new ResultColumnList(this.getContextManager());
            var10.addElement(new ResultColumn(var3, (ValueNode)null, this.getContextManager()));
            return new ConstraintDefinitionNode(var1, var4, var10, var5, (ValueNode)null, (String)null, 2, 5, this.getContextManager());
         case 215:
            TableName var8 = this.referencesSpecification(var7, var9);
            switch (this.jj_nt.kind) {
               case 59:
                  var5 = this.propertyList(false);
                  this.jj_consume_token(64);
                  break;
               default:
                  this.jj_la1[333] = this.jj_gen;
            }

            ResultColumnList var11 = new ResultColumnList(this.getContextManager());
            var11.addElement(new ResultColumn(var3, (ValueNode)null, this.getContextManager()));
            FKConstraintDefinitionNode var6 = new FKConstraintDefinitionNode(var1, var8, var11, var7, var9, this.getContextManager());
            if (var5 != null) {
               ((ConstraintDefinitionNode)var6).setProperties(var5);
            }

            return var6;
         default:
            this.jj_la1[334] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode dropRoleStatement() throws ParseException, StandardException {
      this.jj_consume_token(373);
      String var1 = this.identifier(128, true);
      this.checkVersion(170, "ROLES");
      this.checkSqlStandardAccess("DROP ROLE");
      return new DropRoleNode(var1, this.getContextManager());
   }

   public final StatementNode dropSchemaStatement() throws ParseException, StandardException {
      this.jj_consume_token(222);
      String var1 = this.identifier(128, true);
      this.jj_consume_token(217);
      DropSchemaNode var2 = new DropSchemaNode(var1, 1, this.getContextManager());
      return var2;
   }

   public final StatementNode alterTableStatement() throws ParseException, StandardException {
      this.jj_consume_token(239);
      TableName var2 = this.qualifiedName(128);
      StatementNode var1 = this.alterTableBody(var2);
      return var1;
   }

   public final StatementNode alterTableBody(TableName var1) throws ParseException, StandardException {
      char var3 = '\u0000';
      TableElementList var5 = new TableElementList(this.getContextManager());
      int[] var6 = new int[1];
      int[] var7 = new int[1];
      Object var8 = null;
      switch (this.jj_nt.kind) {
         case 66:
            this.jj_consume_token(66);
            StatementNode var10;
            switch (this.jj_nt.kind) {
               case 129:
                  var10 = this.dropStatistics(var1);
                  break;
               case 254:
                  var10 = this.updateStatistics(var1);
                  break;
               default:
                  this.jj_la1[336] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }

            return var10;
         case 129:
            this.jj_consume_token(129);
            StatementNode var9;
            if (this.jj_2_85(1)) {
               var9 = this.dropColumnDefinition(var1, var5, var6, var7);
            } else {
               if (!this.jj_2_86(1)) {
                  this.jj_consume_token(-1);
                  throw new ParseException();
               }

               var9 = this.dropTableConstraintDefinitionCore(var1, var5, var6, var7);
            }

            return var9;
         case 254:
            this.jj_consume_token(254);
            this.jj_consume_token(431);
            String var13 = this.identifier(128, true);
            this.checkVersion(170, "SYSCS_UPDATE_STATISTICS");
            this.checkInternalFeature("UPDATE STATISTICS");
            return new AlterTableNode(var1, 5, false, var13, this.getContextManager());
         case 386:
            this.jj_consume_token(386);
            StatementNode var2;
            switch (this.jj_nt.kind) {
               case 401:
                  var2 = this.inplaceCompress(var1);
                  break;
               default:
                  this.jj_la1[335] = this.jj_gen;
                  var2 = this.sequentialCompress(var1);
            }

            return var2;
         case 431:
            this.jj_consume_token(431);
            this.jj_consume_token(129);
            String var12 = this.identifier(128, true);
            this.checkVersion(210, "SYSCS_UPDATE_STATISTICS");
            this.checkInternalFeature("DROP STATISTICS");
            return new AlterTableNode(var1, 6, false, var12, this.getContextManager());
         default:
            this.jj_la1[337] = this.jj_gen;
            if (this.jj_2_87(1)) {
               var3 = this.alterTableAction(var5, var6, var7);
               return new AlterTableNode(var1, var6[0], var5, var3, var7[0], this.getContextManager());
            } else {
               this.jj_consume_token(-1);
               throw new ParseException();
            }
      }
   }

   public final StatementNode dropStatistics(TableName var1) throws ParseException, StandardException {
      this.jj_consume_token(129);
      this.jj_consume_token(431);
      this.checkVersion(210, "SYSCS_DROP_STATISTICS");
      this.checkInternalFeature("DROP STATISTICS");
      return new AlterTableNode(var1, 6, true, (String)null, this.getContextManager());
   }

   public final StatementNode updateStatistics(TableName var1) throws ParseException, StandardException {
      this.jj_consume_token(254);
      this.jj_consume_token(431);
      this.checkVersion(170, "SYSCS_UPDATE_STATISTICS");
      this.checkInternalFeature("UPDATE STATISTICS");
      int[] var2 = new int[1];
      return new AlterTableNode(var1, 5, true, (String)null, this.getContextManager());
   }

   public final StatementNode inplaceCompress(TableName var1) throws ParseException, StandardException {
      Token var2 = null;
      Token var3 = null;
      Token var4 = null;
      this.jj_consume_token(401);
      switch (this.jj_nt.kind) {
         case 420 -> var2 = this.jj_consume_token(420);
         default -> this.jj_la1[338] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 393 -> var3 = this.jj_consume_token(393);
         default -> this.jj_la1[339] = this.jj_gen;
      }

      switch (this.jj_nt.kind) {
         case 443 -> var4 = this.jj_consume_token(443);
         default -> this.jj_la1[340] = this.jj_gen;
      }

      this.checkInternalFeature("COMPRESS");
      return new AlterTableNode(var1, var2 != null, var3 != null, var4 != null, this.getContextManager());
   }

   public final StatementNode sequentialCompress(TableName var1) throws ParseException, StandardException {
      Token var2 = null;
      switch (this.jj_nt.kind) {
         case 433 -> var2 = this.jj_consume_token(433);
         default -> this.jj_la1[341] = this.jj_gen;
      }

      this.checkInternalFeature("COMPRESS");
      return new AlterTableNode(var1, var2 != null, this.getContextManager());
   }

   public final char alterTableAction(TableElementList var1, int[] var2, int[] var3) throws ParseException, StandardException {
      char var4 = '\u0000';
      Object var7 = null;
      long[] var9 = new long[5];
      switch (this.jj_nt.kind) {
         case 65:
            this.jj_consume_token(65);
            TableElementNode var5;
            if (this.jj_2_88(1)) {
               var5 = this.addColumnDefinition(var1);
            } else {
               switch (this.jj_nt.kind) {
                  case 91:
                  case 100:
                  case 145:
                  case 208:
                  case 252:
                     var5 = this.tableConstraintDefinition();
                     break;
                  default:
                     this.jj_la1[342] = this.jj_gen;
                     this.jj_consume_token(-1);
                     throw new ParseException();
               }
            }

            if (var5 instanceof ColumnDefinitionNode var12) {
               if (var12.isAutoincrementColumn()) {
                  this.checkVersion(230, "ADD IDENTITY COLUMN");
               }
            }

            var2[0] = 1;
            var1.addTableElement(var5);
            return var4;
         default:
            this.jj_la1[344] = this.jj_gen;
            if (this.getToken(1).kind == 68 && this.getToken(2).kind != 100) {
               this.jj_consume_token(68);
               switch (this.jj_nt.kind) {
                  case 96 -> this.jj_consume_token(96);
                  default -> this.jj_la1[343] = this.jj_gen;
               }

               String var8 = this.identifier(128, true);
               TableElementNode var15 = this.columnAlterClause(var8);
               var2[0] = 3;
               var1.addTableElement(var15);
               return var4;
            } else {
               switch (this.jj_nt.kind) {
                  case 68:
                     this.jj_consume_token(68);
                     TableName var11 = this.constraintNameDefinition();
                     boolean var10 = this.constraintEnforcement();
                     ConstraintDefinitionNode var16 = new ConstraintDefinitionNode(var11, 7, (ResultColumnList)null, (Properties)null, (ValueNode)null, (String)null, 2, 7, this.getContextManager());
                     boolean[] var13 = new boolean[]{false, false, var10};
                     var16.setCharacteristics(var13);
                     var2[0] = 3;
                     var1.addTableElement(var16);
                     return var4;
                  case 305:
                     var4 = this.DB2lockGranularityClause();
                     var2[0] = 4;
                     return var4;
                  default:
                     this.jj_la1[345] = this.jj_gen;
                     this.jj_consume_token(-1);
                     throw new ParseException();
               }
            }
      }
   }

   public final StatementNode dropColumnDefinition(TableName var1, TableElementList var2, int[] var3, int[] var4) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 96 -> this.jj_consume_token(96);
         default -> this.jj_la1[346] = this.jj_gen;
      }

      String var5 = this.identifier(128, true);
      this.dropColumnReferentialAction(var4);
      ModifyColumnNode var6 = new ModifyColumnNode(4, var5, (ValueNode)null, (DataTypeDescriptor)null, (long[])null, this.getContextManager());
      var2.addTableElement(var6);
      return new AlterTableNode(var1, 2, var2, '\u0000', var4[0], this.getContextManager());
   }

   public final void dropColumnReferentialAction(int[] var1) throws ParseException {
      byte var2;
      var2 = 0;
      label12:
      switch (this.jj_nt.kind) {
         case 84:
         case 217:
            switch (this.jj_nt.kind) {
               case 84:
                  this.jj_consume_token(84);
                  var2 = 0;
                  break label12;
               case 217:
                  this.jj_consume_token(217);
                  var2 = 1;
                  break label12;
               default:
                  this.jj_la1[347] = this.jj_gen;
                  this.jj_consume_token(-1);
                  throw new ParseException();
            }
         default:
            this.jj_la1[348] = this.jj_gen;
      }

      var1[0] = var2;
   }

   public final TableElementNode addColumnDefinition(TableElementList var1) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 96 -> this.jj_consume_token(96);
         default -> this.jj_la1[349] = this.jj_gen;
      }

      TableElementNode var2 = this.columnDefinition(var1);
      return var2;
   }

   public final TableElementNode columnAlterClause(String var1) throws ParseException, StandardException {
      long[] var3 = new long[5];
      long var4 = 1L;
      long var6 = 0L;
      long var8 = 1L;
      Object var10 = null;
      if (this.getToken(2).kind == 284) {
         this.jj_consume_token(227);
         this.jj_consume_token(284);
         this.jj_consume_token(355);
         DataTypeDescriptor var15 = this.dataTypeDDL();
         return new ModifyColumnNode(0, var1, (ValueNode)null, var15, (long[])null, this.getContextManager());
      } else {
         switch (this.jj_nt.kind) {
            case 329:
               this.jj_consume_token(329);
               this.jj_consume_token(267);
               var8 = this.exactNumber();
               var3[0] = var8;
               var3[3] = 1L;
               return new ModifyColumnNode(1, var1, (ValueNode)null, (DataTypeDescriptor)null, var3, this.getContextManager());
            default:
               this.jj_la1[353] = this.jj_gen;
               if (this.getToken(2).kind == 292) {
                  this.jj_consume_token(227);
                  this.jj_consume_token(292);
                  int var11 = this.alterGeneratedColumn();
                  this.checkVersion(230, "ALTER TABLE ALTER COLUMN SET GENERATED");
                  return new ModifyColumnNode(var11, var1, (ValueNode)null, (DataTypeDescriptor)null, (long[])null, this.getContextManager());
               } else if (this.getToken(1).kind == 227 && this.getToken(2).kind != 119 && this.getToken(2).kind != 190) {
                  this.jj_consume_token(227);
                  TableElementNode var12 = this.columnAlterIdentityClause(var1);
                  return var12;
               } else if (this.getToken(1).kind != 267 && this.getToken(1).kind != 119 && (this.getToken(1).kind != 227 || this.getToken(2).kind != 119)) {
                  if (this.getToken(1).kind == 129 && this.getToken(2).kind == 119) {
                     this.jj_consume_token(129);
                     this.jj_consume_token(119);
                     UntypedNullConstantNode var13 = new UntypedNullConstantNode(this.getContextManager());
                     return this.wrapAlterColumnDefaultValue(var13, var1, var3);
                  } else if (this.getToken(1).kind != 191 && (this.getToken(1).kind != 129 || this.getToken(2).kind != 190)) {
                     if (this.getToken(1).kind == 190 || this.getToken(1).kind == 227 && this.getToken(2).kind == 190) {
                        switch (this.jj_nt.kind) {
                           case 190:
                              this.jj_consume_token(190);
                              this.jj_consume_token(191);
                              break;
                           case 227:
                              this.jj_consume_token(227);
                              this.jj_consume_token(190);
                              this.jj_consume_token(191);
                              break;
                           default:
                              this.jj_la1[352] = this.jj_gen;
                              this.jj_consume_token(-1);
                              throw new ParseException();
                        }

                        return new ModifyColumnNode(3, var1, (ValueNode)null, (DataTypeDescriptor)null, (long[])null, this.getContextManager());
                     } else {
                        this.jj_consume_token(-1);
                        throw new ParseException();
                     }
                  } else {
                     switch (this.jj_nt.kind) {
                        case 129:
                           this.jj_consume_token(129);
                           this.jj_consume_token(190);
                           this.jj_consume_token(191);
                           break;
                        case 191:
                           this.jj_consume_token(191);
                           break;
                        default:
                           this.jj_la1[351] = this.jj_gen;
                           this.jj_consume_token(-1);
                           throw new ParseException();
                     }

                     return new ModifyColumnNode(2, var1, (ValueNode)null, (DataTypeDescriptor)null, (long[])null, this.getContextManager());
                  }
               } else {
                  switch (this.jj_nt.kind) {
                     case 227 -> this.jj_consume_token(227);
                     default -> this.jj_la1[350] = this.jj_gen;
                  }

                  ValueNode var2 = this.defaultClause(var3, var1);
                  return this.wrapAlterColumnDefaultValue(var2, var1, var3);
               }
         }
      }
   }

   public final TableElementNode columnAlterIdentityClause(String var1) throws ParseException, StandardException {
      long[] var3 = new long[5];
      long var4 = 1L;
      long var6 = 0L;
      switch (this.jj_nt.kind) {
         case 189:
            this.jj_consume_token(189);
            this.jj_consume_token(283);
            this.checkVersion(230, "ALTER TABLE ... SET NO CYCLE");
            var3[4] = 0L;
            var3[3] = 4L;
            return new ModifyColumnNode(1, var1, (ValueNode)null, (DataTypeDescriptor)null, var3, this.getContextManager());
         case 283:
            this.jj_consume_token(283);
            this.checkVersion(230, "ALTER TABLE ... SET CYCLE");
            var3[4] = 1L;
            var3[3] = 4L;
            return new ModifyColumnNode(1, var1, (ValueNode)null, (DataTypeDescriptor)null, var3, this.getContextManager());
         case 294:
            this.jj_consume_token(294);
            this.jj_consume_token(83);
            var4 = this.exactNumber();
            var3[1] = var4;
            var3[3] = 2L;
            return new ModifyColumnNode(1, var1, (ValueNode)null, (DataTypeDescriptor)null, var3, this.getContextManager());
         default:
            this.jj_la1[354] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final int alterGeneratedColumn() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 83:
            this.jj_consume_token(83);
            this.jj_consume_token(119);
            return 6;
         case 274:
            this.jj_consume_token(274);
            return 5;
         default:
            this.jj_la1[355] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode dropTableConstraintDefinitionCore(TableName var1, TableElementList var2, int[] var3, int[] var4) throws ParseException, StandardException {
      TableElementNode var5 = this.dropTableConstraintDefinition();
      var2.addTableElement(var5);
      return new AlterTableNode(var1, 2, var2, '\u0000', var4[0], this.getContextManager());
   }

   public final TableElementNode dropTableConstraintDefinition() throws ParseException, StandardException {
      if (this.getToken(1).kind == 100) {
         this.jj_consume_token(100);
         TableName var4 = this.qualifiedName(128);
         return new ConstraintDefinitionNode(var4, 5, (ResultColumnList)null, (Properties)null, (ValueNode)null, (String)null, 2, 5, this.getContextManager());
      } else if (this.getToken(1).kind == 208) {
         this.jj_consume_token(208);
         this.jj_consume_token(174);
         return new ConstraintDefinitionNode((TableName)null, 5, (ResultColumnList)null, (Properties)null, (ValueNode)null, (String)null, 2, 5, this.getContextManager());
      } else if (this.getToken(1).kind == 145) {
         this.jj_consume_token(145);
         this.jj_consume_token(174);
         TableName var3 = this.qualifiedName(128);
         return new ConstraintDefinitionNode(var3, 5, (ResultColumnList)null, (Properties)null, (ValueNode)null, (String)null, 2, 6, this.getContextManager());
      } else if (this.getToken(1).kind == 252) {
         this.jj_consume_token(252);
         TableName var2 = this.qualifiedName(128);
         return new ConstraintDefinitionNode(var2, 5, (ResultColumnList)null, (Properties)null, (ValueNode)null, (String)null, 2, 3, this.getContextManager());
      } else {
         switch (this.jj_nt.kind) {
            case 91:
               this.jj_consume_token(91);
               TableName var1 = this.qualifiedName(128);
               return new ConstraintDefinitionNode(var1, 5, (ResultColumnList)null, (Properties)null, (ValueNode)null, (String)null, 2, 4, this.getContextManager());
            default:
               this.jj_la1[356] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final StatementNode dropTableStatement() throws ParseException, StandardException {
      this.jj_consume_token(239);
      TableName var1 = this.qualifiedName(128);
      return new DropTableNode(var1, 2, this.getContextManager());
   }

   public final StatementNode dropIndexStatement() throws ParseException, StandardException {
      this.jj_consume_token(400);
      TableName var1 = this.qualifiedName(128);
      return new DropIndexNode(var1, this.getContextManager());
   }

   public final StatementNode dropAliasStatement() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 149:
            this.jj_consume_token(149);
            TableName var5 = this.qualifiedName(128);
            return this.dropAliasNode(var5, 'F');
         case 211:
            this.jj_consume_token(211);
            TableName var4 = this.qualifiedName(128);
            return this.dropAliasNode(var4, 'P');
         case 348:
            this.jj_consume_token(348);
            TableName var3 = this.qualifiedName(128);
            this.checkVersion(130, "DROP SYNONYM");
            return this.dropAliasNode(var3, 'S');
         case 355:
            this.jj_consume_token(355);
            TableName var2 = this.qualifiedName(128);
            this.jj_consume_token(217);
            this.checkVersion(180, "DROP TYPE");
            return this.dropAliasNode(var2, 'A');
         case 391:
            this.jj_consume_token(391);
            this.jj_consume_token(383);
            TableName var1 = this.qualifiedName(128);
            this.jj_consume_token(217);
            this.checkVersion(220, "DROP DERBY AGGREGATE");
            return this.dropAliasNode(var1, 'G');
         default:
            this.jj_la1[357] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode dropViewStatement() throws ParseException, StandardException {
      this.jj_consume_token(263);
      TableName var1 = this.qualifiedName(128);
      return new DropViewNode(var1, this.getContextManager());
   }

   public final StatementNode dropTriggerStatement() throws ParseException, StandardException {
      this.jj_consume_token(442);
      TableName var1 = this.qualifiedName(128);
      return new DropTriggerNode(var1, this.getContextManager());
   }

   public final StatementNode truncateTableStatement() throws ParseException, StandardException {
      this.jj_consume_token(354);
      this.jj_consume_token(239);
      TableName var1 = this.qualifiedName(128);
      return new AlterTableNode(var1, this.getContextManager());
   }

   public final StatementNode grantStatement() throws ParseException, StandardException {
      if (this.getToken(1).kind != 154 || (this.getToken(2).kind != 442 || (this.getToken(3).kind != 458 || !this.isPrivilegeKeywordExceptTrigger(this.getToken(4).kind)) && this.getToken(3).kind != 195) && !this.isPrivilegeKeywordExceptTrigger(this.getToken(2).kind)) {
         if (this.getToken(1).kind != 154 || (this.getToken(2).kind != 442 || (this.getToken(3).kind != 458 || this.isPrivilegeKeywordExceptTrigger(this.getToken(4).kind)) && this.getToken(3).kind != 243) && this.isPrivilegeKeywordExceptTrigger(this.getToken(2).kind)) {
            this.jj_consume_token(-1);
            throw new ParseException();
         } else {
            this.jj_consume_token(154);
            StatementNode var2 = this.roleGrantStatement();
            return var2;
         }
      } else {
         this.jj_consume_token(154);
         this.checkVersion(140, "GRANT");
         this.checkSqlStandardAccess("GRANT");
         StatementNode var1;
         switch (this.jj_nt.kind) {
            case 66:
            case 122:
            case 166:
            case 215:
            case 225:
            case 254:
            case 442:
               var1 = this.tableGrantStatement();
               break;
            case 137:
               var1 = this.routineGrantStatement();
               break;
            case 357:
               var1 = this.usageGrantStatement();
               break;
            default:
               this.jj_la1[358] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }

         return var1;
      }
   }

   public final StatementNode tableGrantStatement() throws ParseException, StandardException {
      PrivilegeNode var1 = this.tablePrivileges();
      this.jj_consume_token(243);
      List var2 = this.granteeList();
      return new GrantNode(var1, var2, this.getContextManager());
   }

   public final PrivilegeNode tablePrivileges() throws ParseException, StandardException {
      Object var1 = null;
      Object var2 = null;
      TablePrivilegesNode var3 = this.tableActions();
      this.jj_consume_token(195);
      switch (this.jj_nt.kind) {
         case 239 -> this.jj_consume_token(239);
         default -> this.jj_la1[359] = this.jj_gen;
      }

      TableName var4 = this.qualifiedName(128);
      return new PrivilegeNode(0, var4, var3, this.getContextManager());
   }

   public final TablePrivilegesNode tableActions() throws ParseException, StandardException {
      TablePrivilegesNode var1 = new TablePrivilegesNode(this.getContextManager());
      switch (this.jj_nt.kind) {
         case 66:
            this.jj_consume_token(66);
            this.jj_consume_token(210);
            var1.addAll();
            return var1;
         case 122:
         case 166:
         case 215:
         case 225:
         case 254:
         case 442:
            this.tableAction(var1);

            while(true) {
               switch (this.jj_nt.kind) {
                  case 458:
                     this.jj_consume_token(458);
                     this.tableAction(var1);
                     break;
                  default:
                     this.jj_la1[360] = this.jj_gen;
                     return var1;
               }
            }
         default:
            this.jj_la1[361] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final StatementNode routineGrantStatement() throws ParseException, StandardException {
      this.jj_consume_token(137);
      this.jj_consume_token(195);
      RoutineDesignator var2 = this.routineDesignator();
      this.jj_consume_token(243);
      List var1 = this.granteeList();
      PrivilegeNode var3 = new PrivilegeNode(1, var2, (TablePrivilegesNode)null, this.getContextManager());
      return new GrantNode(var3, var1, this.getContextManager());
   }

   public final StatementNode usageGrantStatement() throws ParseException, StandardException {
      this.jj_consume_token(357);
      this.jj_consume_token(195);
      Integer var3 = this.usableObjects();
      TableName var2 = this.qualifiedName(128);
      this.jj_consume_token(243);
      List var1 = this.granteeList();
      this.checkVersion(180, "GRANT USAGE");
      PrivilegeNode var4 = new PrivilegeNode(var3, var2, "USAGE", false, this.getContextManager());
      return new GrantNode(var4, var1, this.getContextManager());
   }

   public final Integer usableObjects() throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 355:
            this.jj_consume_token(355);
            return 3;
         case 391:
            this.jj_consume_token(391);
            this.jj_consume_token(383);
            return 4;
         case 432:
            this.jj_consume_token(432);
            return 2;
         default:
            this.jj_la1[362] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }
   }

   public final RoutineDesignator routineDesignator() throws ParseException, StandardException {
      List var3 = null;
      Token var1;
      switch (this.jj_nt.kind) {
         case 149:
            var1 = this.jj_consume_token(149);
            break;
         case 211:
            var1 = this.jj_consume_token(211);
            break;
         default:
            this.jj_la1[363] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

      TableName var2 = this.qualifiedName(128);
      switch (this.jj_nt.kind) {
         case 453:
            this.jj_consume_token(453);
            var3 = this.parameterTypeList();
            this.jj_consume_token(454);
            break;
         default:
            this.jj_la1[364] = this.jj_gen;
      }

      return new RoutineDesignator(var2, var1.kind == 149, var3);
   }

   public final List parameterTypeList() throws ParseException, StandardException {
      ArrayList var1 = new ArrayList();
      if (this.jj_2_89(1)) {
         TypeDescriptor var2 = this.catalogType();
         var1.add(var2);

         while(true) {
            switch (this.jj_nt.kind) {
               case 458:
                  this.jj_consume_token(458);
                  var2 = this.catalogType();
                  var1.add(var2);
                  break;
               default:
                  this.jj_la1[365] = this.jj_gen;
                  return var1;
            }
         }
      } else {
         return var1;
      }
   }

   public final void tableAction(TablePrivilegesNode var1) throws ParseException, StandardException {
      ResultColumnList var2 = null;
      switch (this.jj_nt.kind) {
         case 122:
            this.jj_consume_token(122);
            var1.addAction(1, (ResultColumnList)null);
            break;
         case 166:
            this.jj_consume_token(166);
            var1.addAction(2, (ResultColumnList)null);
            break;
         case 215:
            this.jj_consume_token(215);
            switch (this.jj_nt.kind) {
               case 453 -> var2 = this.privilegeColumnList();
               default -> this.jj_la1[368] = this.jj_gen;
            }

            var1.addAction(4, var2);
            break;
         case 225:
            this.jj_consume_token(225);
            switch (this.jj_nt.kind) {
               case 453 -> var2 = this.privilegeColumnList();
               default -> this.jj_la1[366] = this.jj_gen;
            }

            var1.addAction(0, var2);
            break;
         case 254:
            this.jj_consume_token(254);
            switch (this.jj_nt.kind) {
               case 453 -> var2 = this.privilegeColumnList();
               default -> this.jj_la1[367] = this.jj_gen;
            }

            var1.addAction(3, var2);
            break;
         case 442:
            this.jj_consume_token(442);
            var1.addAction(5, (ResultColumnList)null);
            break;
         default:
            this.jj_la1[369] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
      }

   }

   public final ResultColumnList privilegeColumnList() throws ParseException, StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());
      this.jj_consume_token(453);
      this.columnNameList(var1);
      this.jj_consume_token(454);
      return var1;
   }

   public final List granteeList() throws ParseException, StandardException {
      ArrayList var1 = new ArrayList();
      this.grantee(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.grantee(var1);
               break;
            default:
               this.jj_la1[370] = this.jj_gen;
               return var1;
         }
      }
   }

   public final void grantee(List var1) throws ParseException, StandardException {
      if (this.jj_2_90(1)) {
         String var2 = this.identifier(128, true);
         var1.add(var2);
      } else {
         switch (this.jj_nt.kind) {
            case 212:
               this.jj_consume_token(212);
               var1.add("PUBLIC");
               break;
            default:
               this.jj_la1[371] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }

   }

   public final StatementNode roleGrantStatement() throws ParseException, StandardException {
      List var1 = this.roleList();
      this.jj_consume_token(243);
      List var2 = this.granteeList();
      this.checkSqlStandardAccess("GRANT <role>");
      this.checkVersion(170, "ROLES");
      return new GrantRoleNode(var1, var2, this.getContextManager());
   }

   public final List roleList() throws ParseException, StandardException {
      ArrayList var1 = new ArrayList();
      this.roleElement(var1);

      while(true) {
         switch (this.jj_nt.kind) {
            case 458:
               this.jj_consume_token(458);
               this.roleElement(var1);
               break;
            default:
               this.jj_la1[372] = this.jj_gen;
               return var1;
         }
      }
   }

   public final void roleElement(List var1) throws ParseException, StandardException {
      String var2 = this.identifier(128, true);
      var1.add(var2);
   }

   public final StatementNode revokeStatement() throws ParseException, StandardException {
      if (this.getToken(1).kind != 218 || (this.getToken(2).kind != 442 || (this.getToken(3).kind != 458 || !this.isPrivilegeKeywordExceptTrigger(this.getToken(4).kind)) && this.getToken(3).kind != 195) && !this.isPrivilegeKeywordExceptTrigger(this.getToken(2).kind)) {
         if (this.getToken(1).kind != 218 || (this.getToken(2).kind != 442 || (this.getToken(3).kind != 458 || this.isPrivilegeKeywordExceptTrigger(this.getToken(4).kind)) && this.getToken(3).kind != 147) && this.isPrivilegeKeywordExceptTrigger(this.getToken(2).kind)) {
            this.jj_consume_token(-1);
            throw new ParseException();
         } else {
            this.jj_consume_token(218);
            this.checkVersion(170, "ROLES");
            this.checkSqlStandardAccess("REVOKE <role>");
            StatementNode var2 = this.roleRevokeStatement();
            return var2;
         }
      } else {
         this.jj_consume_token(218);
         this.checkVersion(140, "REVOKE");
         this.checkSqlStandardAccess("REVOKE");
         StatementNode var1;
         switch (this.jj_nt.kind) {
            case 66:
            case 122:
            case 166:
            case 215:
            case 225:
            case 254:
            case 442:
               var1 = this.tableRevokeStatement();
               break;
            case 137:
               var1 = this.routineRevokeStatement();
               break;
            case 357:
               var1 = this.usageRevokeStatement();
               break;
            default:
               this.jj_la1[373] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }

         return var1;
      }
   }

   public final StatementNode tableRevokeStatement() throws ParseException, StandardException {
      Object var1 = null;
      PrivilegeNode var3 = this.tablePrivileges();
      this.jj_consume_token(147);
      List var2 = this.granteeList();
      return new RevokeNode(var3, var2, this.getContextManager());
   }

   public final StatementNode routineRevokeStatement() throws ParseException, StandardException {
      Object var2 = null;
      this.jj_consume_token(137);
      this.jj_consume_token(195);
      RoutineDesignator var4 = this.routineDesignator();
      this.jj_consume_token(147);
      List var1 = this.granteeList();
      this.jj_consume_token(217);
      PrivilegeNode var3 = new PrivilegeNode(1, var4, (TablePrivilegesNode)null, this.getContextManager());
      return new RevokeNode(var3, var1, this.getContextManager());
   }

   public final StatementNode usageRevokeStatement() throws ParseException, StandardException {
      this.jj_consume_token(357);
      this.jj_consume_token(195);
      Integer var3 = this.usableObjects();
      TableName var2 = this.qualifiedName(128);
      this.jj_consume_token(147);
      List var1 = this.granteeList();
      this.jj_consume_token(217);
      this.checkVersion(180, "REVOKE USAGE");
      PrivilegeNode var4 = new PrivilegeNode(var3, var2, "USAGE", true, this.getContextManager());
      return new RevokeNode(var4, var1, this.getContextManager());
   }

   public final StatementNode roleRevokeStatement() throws ParseException, StandardException {
      List var1 = this.roleList();
      this.jj_consume_token(147);
      List var2 = this.granteeList();
      return new RevokeRoleNode(var1, var2, this.getContextManager());
   }

   public final String internalIdentifier(int var1, boolean var2) throws ParseException, StandardException {
      switch (this.jj_nt.kind) {
         case 480:
            Token var4 = this.jj_consume_token(480);
            String var5 = StringUtil.SQLToUpperCase(var4.image);
            if (var2) {
               IdUtil.checkIdentifierLengthLimit(var5, var1);
            }

            this.nextToLastTokenDelimitedIdentifier = this.lastTokenDelimitedIdentifier;
            this.lastTokenDelimitedIdentifier = false;
            this.thirdToLastIdentifierToken = this.nextToLastIdentifierToken;
            this.nextToLastIdentifierToken = this.lastIdentifierToken;
            this.lastIdentifierToken = var4;
            return var5;
         case 486:
            String var3 = this.delimitedIdentifier();
            if (var2) {
               IdUtil.checkIdentifierLengthLimit(var3, var1);
            }

            return var3;
         default:
            this.jj_la1[374] = this.jj_gen;
            if (this.jj_2_91(1)) {
               String var6 = this.nonReservedKeyword();
               return StringUtil.SQLToUpperCase(var6);
            } else {
               this.jj_consume_token(-1);
               throw new ParseException();
            }
      }
   }

   public final String identifier(int var1, boolean var2) throws ParseException, StandardException {
      String var3 = this.internalIdentifier(var1, var2);
      return var3;
   }

   public final String delimitedIdentifier() throws ParseException {
      Token var2 = this.jj_consume_token(486);
      String var1 = var2.image.substring(1, var2.image.length() - 1);
      var1 = normalizeDelimitedID(var1);
      this.nextToLastTokenDelimitedIdentifier = this.lastTokenDelimitedIdentifier;
      this.lastTokenDelimitedIdentifier = true;
      this.thirdToLastIdentifierToken = this.nextToLastIdentifierToken;
      this.nextToLastIdentifierToken = this.lastIdentifierToken;
      this.lastIdentifierToken = var2;
      return var1;
   }

   public final String reservedKeyword() throws ParseException {
      Token var1;
      switch (this.jj_nt.kind) {
         case 65:
            var1 = this.jj_consume_token(65);
            break;
         case 66:
            var1 = this.jj_consume_token(66);
            break;
         case 67:
            var1 = this.jj_consume_token(67);
            break;
         case 68:
            var1 = this.jj_consume_token(68);
            break;
         case 69:
            var1 = this.jj_consume_token(69);
            break;
         case 70:
            var1 = this.jj_consume_token(70);
            break;
         case 71:
            var1 = this.jj_consume_token(71);
            break;
         case 72:
            var1 = this.jj_consume_token(72);
            break;
         case 73:
            var1 = this.jj_consume_token(73);
            break;
         case 74:
            var1 = this.jj_consume_token(74);
            break;
         case 75:
            var1 = this.jj_consume_token(75);
            break;
         case 76:
            var1 = this.jj_consume_token(76);
            break;
         case 77:
            var1 = this.jj_consume_token(77);
            break;
         case 78:
            var1 = this.jj_consume_token(78);
            break;
         case 79:
            var1 = this.jj_consume_token(79);
            break;
         case 80:
         case 90:
         case 93:
         case 105:
         case 114:
         case 184:
         case 238:
         case 250:
         case 258:
         case 260:
         case 266:
         case 271:
         case 272:
         case 273:
         case 274:
         case 275:
         case 276:
         case 277:
         case 278:
         case 279:
         case 280:
         case 281:
         case 282:
         case 283:
         case 284:
         case 285:
         case 286:
         case 287:
         case 288:
         case 289:
         case 290:
         case 291:
         case 292:
         case 293:
         case 294:
         case 295:
         case 297:
         case 298:
         case 299:
         case 300:
         case 301:
         case 302:
         case 303:
         case 304:
         case 305:
         case 306:
         case 307:
         case 308:
         case 309:
         case 310:
         case 311:
         case 312:
         case 313:
         case 314:
         case 315:
         case 316:
         case 317:
         case 318:
         case 319:
         case 320:
         case 321:
         case 322:
         case 323:
         case 324:
         case 325:
         case 326:
         case 327:
         case 328:
         case 329:
         case 330:
         case 331:
         case 332:
         case 333:
         case 334:
         case 335:
         case 336:
         case 337:
         case 338:
         case 339:
         case 340:
         case 341:
         case 342:
         case 343:
         case 344:
         case 345:
         case 346:
         case 347:
         case 348:
         case 349:
         case 350:
         case 351:
         case 352:
         case 353:
         case 354:
         case 355:
         case 356:
         case 357:
         case 358:
         case 359:
         case 362:
         case 364:
         case 365:
         case 369:
         case 372:
         case 373:
         case 374:
         case 382:
         case 383:
         case 384:
         case 385:
         case 386:
         case 387:
         case 388:
         case 389:
         case 390:
         case 391:
         case 392:
         case 393:
         case 394:
         case 395:
         case 396:
         case 397:
         case 398:
         case 399:
         case 400:
         case 401:
         case 402:
         case 403:
         case 404:
         case 405:
         case 406:
         case 407:
         case 408:
         case 409:
         case 410:
         case 412:
         case 413:
         case 414:
         case 415:
         default:
            this.jj_la1[375] = this.jj_gen;
            this.jj_consume_token(-1);
            throw new ParseException();
         case 81:
            var1 = this.jj_consume_token(81);
            break;
         case 82:
            var1 = this.jj_consume_token(82);
            break;
         case 83:
            var1 = this.jj_consume_token(83);
            break;
         case 84:
            var1 = this.jj_consume_token(84);
            break;
         case 85:
            var1 = this.jj_consume_token(85);
            break;
         case 86:
            var1 = this.jj_consume_token(86);
            break;
         case 87:
            var1 = this.jj_consume_token(87);
            break;
         case 88:
            var1 = this.jj_consume_token(88);
            break;
         case 89:
            var1 = this.jj_consume_token(89);
            break;
         case 91:
            var1 = this.jj_consume_token(91);
            break;
         case 92:
            var1 = this.jj_consume_token(92);
            break;
         case 94:
            var1 = this.jj_consume_token(94);
            break;
         case 95:
            var1 = this.jj_consume_token(95);
            break;
         case 96:
            var1 = this.jj_consume_token(96);
            break;
         case 97:
            var1 = this.jj_consume_token(97);
            break;
         case 98:
            var1 = this.jj_consume_token(98);
            break;
         case 99:
            var1 = this.jj_consume_token(99);
            break;
         case 100:
            var1 = this.jj_consume_token(100);
            break;
         case 101:
            var1 = this.jj_consume_token(101);
            break;
         case 102:
            var1 = this.jj_consume_token(102);
            break;
         case 103:
            var1 = this.jj_consume_token(103);
            break;
         case 104:
            var1 = this.jj_consume_token(104);
            break;
         case 106:
            var1 = this.jj_consume_token(106);
            break;
         case 107:
            var1 = this.jj_consume_token(107);
            break;
         case 108:
            var1 = this.jj_consume_token(108);
            break;
         case 109:
            var1 = this.jj_consume_token(109);
            break;
         case 110:
            var1 = this.jj_consume_token(110);
            break;
         case 111:
            var1 = this.jj_consume_token(111);
            break;
         case 112:
            var1 = this.jj_consume_token(112);
            break;
         case 113:
            var1 = this.jj_consume_token(113);
            break;
         case 115:
            var1 = this.jj_consume_token(115);
            break;
         case 116:
            var1 = this.jj_consume_token(116);
            break;
         case 117:
            var1 = this.jj_consume_token(117);
            break;
         case 118:
            var1 = this.jj_consume_token(118);
            break;
         case 119:
            var1 = this.jj_consume_token(119);
            break;
         case 120:
            var1 = this.jj_consume_token(120);
            break;
         case 121:
            var1 = this.jj_consume_token(121);
            break;
         case 122:
            var1 = this.jj_consume_token(122);
            break;
         case 123:
            var1 = this.jj_consume_token(123);
            break;
         case 124:
            var1 = this.jj_consume_token(124);
            break;
         case 125:
            var1 = this.jj_consume_token(125);
            break;
         case 126:
            var1 = this.jj_consume_token(126);
            break;
         case 127:
            var1 = this.jj_consume_token(127);
            break;
         case 128:
            var1 = this.jj_consume_token(128);
            break;
         case 129:
            var1 = this.jj_consume_token(129);
            break;
         case 130:
            var1 = this.jj_consume_token(130);
            break;
         case 131:
            var1 = this.jj_consume_token(131);
            break;
         case 132:
            var1 = this.jj_consume_token(132);
            break;
         case 133:
            var1 = this.jj_consume_token(133);
            break;
         case 134:
            var1 = this.jj_consume_token(134);
            break;
         case 135:
            var1 = this.jj_consume_token(135);
            break;
         case 136:
            var1 = this.jj_consume_token(136);
            break;
         case 137:
            var1 = this.jj_consume_token(137);
            break;
         case 138:
            var1 = this.jj_consume_token(138);
            break;
         case 139:
            var1 = this.jj_consume_token(139);
            break;
         case 140:
            var1 = this.jj_consume_token(140);
            break;
         case 141:
            var1 = this.jj_consume_token(141);
            break;
         case 142:
            var1 = this.jj_consume_token(142);
            break;
         case 143:
            var1 = this.jj_consume_token(143);
            break;
         case 144:
            var1 = this.jj_consume_token(144);
            break;
         case 145:
            var1 = this.jj_consume_token(145);
            break;
         case 146:
            var1 = this.jj_consume_token(146);
            break;
         case 147:
            var1 = this.jj_consume_token(147);
            break;
         case 148:
            var1 = this.jj_consume_token(148);
            break;
         case 149:
            var1 = this.jj_consume_token(149);
            break;
         case 150:
            var1 = this.jj_consume_token(150);
            break;
         case 151:
            var1 = this.jj_consume_token(151);
            break;
         case 152:
            var1 = this.jj_consume_token(152);
            break;
         case 153:
            var1 = this.jj_consume_token(153);
            break;
         case 154:
            var1 = this.jj_consume_token(154);
            break;
         case 155:
            var1 = this.jj_consume_token(155);
            break;
         case 156:
            var1 = this.jj_consume_token(156);
            break;
         case 157:
            var1 = this.jj_consume_token(157);
            break;
         case 158:
            var1 = this.jj_consume_token(158);
            break;
         case 159:
            var1 = this.jj_consume_token(159);
            break;
         case 160:
            var1 = this.jj_consume_token(160);
            break;
         case 161:
            var1 = this.jj_consume_token(161);
            break;
         case 162:
            var1 = this.jj_consume_token(162);
            break;
         case 163:
            var1 = this.jj_consume_token(163);
            break;
         case 164:
            var1 = this.jj_consume_token(164);
            break;
         case 165:
            var1 = this.jj_consume_token(165);
            break;
         case 166:
            var1 = this.jj_consume_token(166);
            break;
         case 167:
            var1 = this.jj_consume_token(167);
            break;
         case 168:
            var1 = this.jj_consume_token(168);
            break;
         case 169:
            var1 = this.jj_consume_token(169);
            break;
         case 170:
            var1 = this.jj_consume_token(170);
            break;
         case 171:
            var1 = this.jj_consume_token(171);
            break;
         case 172:
            var1 = this.jj_consume_token(172);
            break;
         case 173:
            var1 = this.jj_consume_token(173);
            break;
         case 174:
            var1 = this.jj_consume_token(174);
            break;
         case 175:
            var1 = this.jj_consume_token(175);
            break;
         case 176:
            var1 = this.jj_consume_token(176);
            break;
         case 177:
            var1 = this.jj_consume_token(177);
            break;
         case 178:
            var1 = this.jj_consume_token(178);
            break;
         case 179:
            var1 = this.jj_consume_token(179);
            break;
         case 180:
            var1 = this.jj_consume_token(180);
            break;
         case 181:
            var1 = this.jj_consume_token(181);
            break;
         case 182:
            var1 = this.jj_consume_token(182);
            break;
         case 183:
            var1 = this.jj_consume_token(183);
            break;
         case 185:
            var1 = this.jj_consume_token(185);
            break;
         case 186:
            var1 = this.jj_consume_token(186);
            break;
         case 187:
            var1 = this.jj_consume_token(187);
            break;
         case 188:
            var1 = this.jj_consume_token(188);
            break;
         case 189:
            var1 = this.jj_consume_token(189);
            break;
         case 190:
            var1 = this.jj_consume_token(190);
            break;
         case 191:
            var1 = this.jj_consume_token(191);
            break;
         case 192:
            var1 = this.jj_consume_token(192);
            break;
         case 193:
            var1 = this.jj_consume_token(193);
            break;
         case 194:
            var1 = this.jj_consume_token(194);
            break;
         case 195:
            var1 = this.jj_consume_token(195);
            break;
         case 196:
            var1 = this.jj_consume_token(196);
            break;
         case 197:
            var1 = this.jj_consume_token(197);
            break;
         case 198:
            var1 = this.jj_consume_token(198);
            break;
         case 199:
            var1 = this.jj_consume_token(199);
            break;
         case 200:
            var1 = this.jj_consume_token(200);
            break;
         case 201:
            var1 = this.jj_consume_token(201);
            break;
         case 202:
            var1 = this.jj_consume_token(202);
            break;
         case 203:
            var1 = this.jj_consume_token(203);
            break;
         case 204:
            var1 = this.jj_consume_token(204);
            break;
         case 205:
            var1 = this.jj_consume_token(205);
            break;
         case 206:
            var1 = this.jj_consume_token(206);
            break;
         case 207:
            var1 = this.jj_consume_token(207);
            break;
         case 208:
            var1 = this.jj_consume_token(208);
            break;
         case 209:
            var1 = this.jj_consume_token(209);
            break;
         case 210:
            var1 = this.jj_consume_token(210);
            break;
         case 211:
            var1 = this.jj_consume_token(211);
            break;
         case 212:
            var1 = this.jj_consume_token(212);
            break;
         case 213:
            var1 = this.jj_consume_token(213);
            break;
         case 214:
            var1 = this.jj_consume_token(214);
            break;
         case 215:
            var1 = this.jj_consume_token(215);
            break;
         case 216:
            var1 = this.jj_consume_token(216);
            break;
         case 217:
            var1 = this.jj_consume_token(217);
            break;
         case 218:
            var1 = this.jj_consume_token(218);
            break;
         case 219:
            var1 = this.jj_consume_token(219);
            break;
         case 220:
            var1 = this.jj_consume_token(220);
            break;
         case 221:
            var1 = this.jj_consume_token(221);
            break;
         case 222:
            var1 = this.jj_consume_token(222);
            break;
         case 223:
            var1 = this.jj_consume_token(223);
            break;
         case 224:
            var1 = this.jj_consume_token(224);
            break;
         case 225:
            var1 = this.jj_consume_token(225);
            break;
         case 226:
            var1 = this.jj_consume_token(226);
            break;
         case 227:
            var1 = this.jj_consume_token(227);
            break;
         case 228:
            var1 = this.jj_consume_token(228);
            break;
         case 229:
            var1 = this.jj_consume_token(229);
            break;
         case 230:
            var1 = this.jj_consume_token(230);
            break;
         case 231:
            var1 = this.jj_consume_token(231);
            break;
         case 232:
            var1 = this.jj_consume_token(232);
            break;
         case 233:
            var1 = this.jj_consume_token(233);
            break;
         case 234:
            var1 = this.jj_consume_token(234);
            break;
         case 235:
            var1 = this.jj_consume_token(235);
            break;
         case 236:
            var1 = this.jj_consume_token(236);
            break;
         case 237:
            var1 = this.jj_consume_token(237);
            break;
         case 239:
            var1 = this.jj_consume_token(239);
            break;
         case 240:
            var1 = this.jj_consume_token(240);
            break;
         case 241:
            var1 = this.jj_consume_token(241);
            break;
         case 242:
            var1 = this.jj_consume_token(242);
            break;
         case 243:
            var1 = this.jj_consume_token(243);
            break;
         case 244:
            var1 = this.jj_consume_token(244);
            break;
         case 245:
            var1 = this.jj_consume_token(245);
            break;
         case 246:
            var1 = this.jj_consume_token(246);
            break;
         case 247:
            var1 = this.jj_consume_token(247);
            break;
         case 248:
            var1 = this.jj_consume_token(248);
            break;
         case 249:
            var1 = this.jj_consume_token(249);
            break;
         case 251:
            var1 = this.jj_consume_token(251);
            break;
         case 252:
            var1 = this.jj_consume_token(252);
            break;
         case 253:
            var1 = this.jj_consume_token(253);
            break;
         case 254:
            var1 = this.jj_consume_token(254);
            break;
         case 255:
            var1 = this.jj_consume_token(255);
            break;
         case 256:
            var1 = this.jj_consume_token(256);
            break;
         case 257:
            var1 = this.jj_consume_token(257);
            break;
         case 259:
            var1 = this.jj_consume_token(259);
            break;
         case 261:
            var1 = this.jj_consume_token(261);
            break;
         case 262:
            var1 = this.jj_consume_token(262);
            break;
         case 263:
            var1 = this.jj_consume_token(263);
            break;
         case 264:
            var1 = this.jj_consume_token(264);
            break;
         case 265:
            var1 = this.jj_consume_token(265);
            break;
         case 267:
            var1 = this.jj_consume_token(267);
            break;
         case 268:
            var1 = this.jj_consume_token(268);
            break;
         case 269:
            var1 = this.jj_consume_token(269);
            break;
         case 270:
            var1 = this.jj_consume_token(270);
            break;
         case 296:
            var1 = this.jj_consume_token(296);
            break;
         case 360:
            var1 = this.jj_consume_token(360);
            break;
         case 361:
            var1 = this.jj_consume_token(361);
            break;
         case 363:
            var1 = this.jj_consume_token(363);
            break;
         case 366:
            var1 = this.jj_consume_token(366);
            break;
         case 367:
            var1 = this.jj_consume_token(367);
            break;
         case 368:
            var1 = this.jj_consume_token(368);
            break;
         case 370:
            var1 = this.jj_consume_token(370);
            break;
         case 371:
            var1 = this.jj_consume_token(371);
            break;
         case 375:
            var1 = this.jj_consume_token(375);
            break;
         case 376:
            var1 = this.jj_consume_token(376);
            break;
         case 377:
            var1 = this.jj_consume_token(377);
            break;
         case 378:
            var1 = this.jj_consume_token(378);
            break;
         case 379:
            var1 = this.jj_consume_token(379);
            break;
         case 380:
            var1 = this.jj_consume_token(380);
            break;
         case 381:
            var1 = this.jj_consume_token(381);
            break;
         case 411:
            var1 = this.jj_consume_token(411);
            break;
         case 416:
            var1 = this.jj_consume_token(416);
      }

      this.nextToLastTokenDelimitedIdentifier = this.lastTokenDelimitedIdentifier;
      this.lastTokenDelimitedIdentifier = false;
      return var1.image;
   }

   public final String nonReservedKeyword() throws ParseException {
      Token var1;
      label177:
      switch (this.jj_nt.kind) {
         case 80:
            var1 = this.jj_consume_token(80);
            break;
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         case 131:
         case 132:
         case 133:
         case 134:
         case 135:
         case 136:
         case 137:
         case 138:
         case 139:
         case 140:
         case 141:
         case 142:
         case 143:
         case 144:
         case 145:
         case 146:
         case 147:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
         case 155:
         case 156:
         case 157:
         case 158:
         case 159:
         case 160:
         case 161:
         case 162:
         case 163:
         case 164:
         case 165:
         case 166:
         case 167:
         case 168:
         case 169:
         case 170:
         case 171:
         case 172:
         case 173:
         case 174:
         case 175:
         case 176:
         case 177:
         case 178:
         case 179:
         case 180:
         case 181:
         case 182:
         case 183:
         case 185:
         case 186:
         case 187:
         case 188:
         case 189:
         case 190:
         case 191:
         case 192:
         case 193:
         case 194:
         case 195:
         case 196:
         case 197:
         case 198:
         case 199:
         case 200:
         case 201:
         case 202:
         case 203:
         case 204:
         case 205:
         case 206:
         case 207:
         case 208:
         case 209:
         case 210:
         case 211:
         case 212:
         case 213:
         case 214:
         case 215:
         case 216:
         case 217:
         case 218:
         case 219:
         case 220:
         case 221:
         case 222:
         case 223:
         case 224:
         case 225:
         case 226:
         case 227:
         case 228:
         case 229:
         case 230:
         case 231:
         case 232:
         case 233:
         case 234:
         case 235:
         case 236:
         case 237:
         case 238:
         case 239:
         case 240:
         case 241:
         case 242:
         case 243:
         case 244:
         case 245:
         case 246:
         case 247:
         case 248:
         case 249:
         case 250:
         case 251:
         case 252:
         case 253:
         case 254:
         case 255:
         case 256:
         case 257:
         case 258:
         case 259:
         case 260:
         case 261:
         case 262:
         case 263:
         case 264:
         case 265:
         case 266:
         case 267:
         case 268:
         case 269:
         case 270:
         case 287:
         case 288:
         case 296:
         case 298:
         case 323:
         case 324:
         case 325:
         case 326:
         case 327:
         case 328:
         case 329:
         case 330:
         case 331:
         case 332:
         case 333:
         case 334:
         case 335:
         case 336:
         case 337:
         case 338:
         case 339:
         case 340:
         case 341:
         case 342:
         case 343:
         case 344:
         case 345:
         case 346:
         case 347:
         case 348:
         case 349:
         case 350:
         case 351:
         case 352:
         case 353:
         case 354:
         case 355:
         case 356:
         case 357:
         case 358:
         case 359:
         case 360:
         case 361:
         case 363:
         case 366:
         case 367:
         case 368:
         case 370:
         case 371:
         case 372:
         case 373:
         case 374:
         case 375:
         case 376:
         case 377:
         case 378:
         case 379:
         case 380:
         case 381:
         case 389:
         case 411:
         case 412:
         default:
            this.jj_la1[376] = this.jj_gen;
            if (this.getToken(1).kind == 323 && !this.seeingOffsetClause()) {
               var1 = this.jj_consume_token(323);
            } else {
               switch (this.jj_nt.kind) {
                  case 238:
                     var1 = this.jj_consume_token(238);
                     break label177;
                  case 239:
                  case 240:
                  case 241:
                  case 242:
                  case 243:
                  case 244:
                  case 245:
                  case 246:
                  case 247:
                  case 248:
                  case 249:
                  case 251:
                  case 252:
                  case 253:
                  case 254:
                  case 255:
                  case 256:
                  case 257:
                  case 259:
                  case 261:
                  case 262:
                  case 263:
                  case 264:
                  case 265:
                  case 266:
                  case 267:
                  case 268:
                  case 269:
                  case 270:
                  case 271:
                  case 272:
                  case 273:
                  case 274:
                  case 275:
                  case 276:
                  case 277:
                  case 278:
                  case 279:
                  case 280:
                  case 281:
                  case 282:
                  case 283:
                  case 284:
                  case 285:
                  case 286:
                  case 287:
                  case 288:
                  case 289:
                  case 290:
                  case 291:
                  case 292:
                  case 293:
                  case 294:
                  case 295:
                  case 296:
                  case 297:
                  case 298:
                  case 299:
                  case 300:
                  case 301:
                  case 302:
                  case 303:
                  case 304:
                  case 305:
                  case 306:
                  case 307:
                  case 308:
                  case 309:
                  case 310:
                  case 311:
                  case 312:
                  case 313:
                  case 314:
                  case 315:
                  case 316:
                  case 317:
                  case 318:
                  case 319:
                  case 320:
                  case 321:
                  case 322:
                  case 323:
                  case 359:
                  case 360:
                  case 361:
                  case 362:
                  case 363:
                  case 364:
                  case 365:
                  case 366:
                  case 367:
                  case 368:
                  case 369:
                  case 370:
                  case 371:
                  case 375:
                  case 376:
                  case 377:
                  case 378:
                  case 379:
                  case 380:
                  case 381:
                  case 382:
                  case 383:
                  case 384:
                  case 385:
                  case 386:
                  case 387:
                  case 388:
                  case 389:
                  case 390:
                  case 391:
                  case 392:
                  case 393:
                  case 394:
                  case 395:
                  case 396:
                  case 397:
                  case 398:
                  case 399:
                  case 400:
                  case 401:
                  case 402:
                  case 403:
                  case 404:
                  case 405:
                  case 406:
                  case 407:
                  case 408:
                  case 409:
                  case 410:
                  case 411:
                  case 413:
                  case 416:
                  default:
                     this.jj_la1[377] = this.jj_gen;
                     this.jj_consume_token(-1);
                     throw new ParseException();
                  case 250:
                     var1 = this.jj_consume_token(250);
                     break label177;
                  case 258:
                     var1 = this.jj_consume_token(258);
                     break label177;
                  case 260:
                     var1 = this.jj_consume_token(260);
                     break label177;
                  case 324:
                     var1 = this.jj_consume_token(324);
                     break label177;
                  case 325:
                     var1 = this.jj_consume_token(325);
                     break label177;
                  case 326:
                     var1 = this.jj_consume_token(326);
                     break label177;
                  case 327:
                     var1 = this.jj_consume_token(327);
                     break label177;
                  case 328:
                     var1 = this.jj_consume_token(328);
                     break label177;
                  case 329:
                     var1 = this.jj_consume_token(329);
                     break label177;
                  case 330:
                     var1 = this.jj_consume_token(330);
                     break label177;
                  case 331:
                     var1 = this.jj_consume_token(331);
                     break label177;
                  case 332:
                     var1 = this.jj_consume_token(332);
                     break label177;
                  case 333:
                     var1 = this.jj_consume_token(333);
                     break label177;
                  case 334:
                     var1 = this.jj_consume_token(334);
                     break label177;
                  case 335:
                     var1 = this.jj_consume_token(335);
                     break label177;
                  case 336:
                     var1 = this.jj_consume_token(336);
                     break label177;
                  case 337:
                     var1 = this.jj_consume_token(337);
                     break label177;
                  case 338:
                     var1 = this.jj_consume_token(338);
                     break label177;
                  case 339:
                     var1 = this.jj_consume_token(339);
                     break label177;
                  case 340:
                     var1 = this.jj_consume_token(340);
                     break label177;
                  case 341:
                     var1 = this.jj_consume_token(341);
                     break label177;
                  case 342:
                     var1 = this.jj_consume_token(342);
                     break label177;
                  case 343:
                     var1 = this.jj_consume_token(343);
                     break label177;
                  case 344:
                     var1 = this.jj_consume_token(344);
                     break label177;
                  case 345:
                     var1 = this.jj_consume_token(345);
                     break label177;
                  case 346:
                     var1 = this.jj_consume_token(346);
                     break label177;
                  case 347:
                     var1 = this.jj_consume_token(347);
                     break label177;
                  case 348:
                     var1 = this.jj_consume_token(348);
                     break label177;
                  case 349:
                     var1 = this.jj_consume_token(349);
                     break label177;
                  case 350:
                     var1 = this.jj_consume_token(350);
                     break label177;
                  case 351:
                     var1 = this.jj_consume_token(351);
                     break label177;
                  case 352:
                     var1 = this.jj_consume_token(352);
                     break label177;
                  case 353:
                     var1 = this.jj_consume_token(353);
                     break label177;
                  case 354:
                     var1 = this.jj_consume_token(354);
                     break label177;
                  case 355:
                     var1 = this.jj_consume_token(355);
                     break label177;
                  case 356:
                     var1 = this.jj_consume_token(356);
                     break label177;
                  case 357:
                     var1 = this.jj_consume_token(357);
                     break label177;
                  case 358:
                     var1 = this.jj_consume_token(358);
                     break label177;
                  case 372:
                     var1 = this.jj_consume_token(372);
                     break label177;
                  case 373:
                     var1 = this.jj_consume_token(373);
                     break label177;
                  case 374:
                     var1 = this.jj_consume_token(374);
                     break label177;
                  case 412:
                     var1 = this.jj_consume_token(412);
                     break label177;
                  case 414:
                     var1 = this.jj_consume_token(414);
                     break label177;
                  case 415:
                     var1 = this.jj_consume_token(415);
                     break label177;
                  case 417:
                     var1 = this.jj_consume_token(417);
                     break label177;
                  case 418:
                     var1 = this.jj_consume_token(418);
                     break label177;
                  case 419:
                     var1 = this.jj_consume_token(419);
                     break label177;
                  case 420:
                     var1 = this.jj_consume_token(420);
                     break label177;
                  case 421:
                     var1 = this.jj_consume_token(421);
                     break label177;
                  case 422:
                     var1 = this.jj_consume_token(422);
                     break label177;
                  case 423:
                     var1 = this.jj_consume_token(423);
                     break label177;
                  case 424:
                     var1 = this.jj_consume_token(424);
                     break label177;
                  case 425:
                     var1 = this.jj_consume_token(425);
                     break label177;
                  case 426:
                     var1 = this.jj_consume_token(426);
                     break label177;
                  case 427:
                     var1 = this.jj_consume_token(427);
                     break label177;
                  case 428:
                     var1 = this.jj_consume_token(428);
                     break label177;
                  case 429:
                     var1 = this.jj_consume_token(429);
                     break label177;
                  case 430:
                     var1 = this.jj_consume_token(430);
                     break label177;
                  case 431:
                     var1 = this.jj_consume_token(431);
                     break label177;
                  case 432:
                     var1 = this.jj_consume_token(432);
                     break label177;
                  case 433:
                     var1 = this.jj_consume_token(433);
                     break label177;
                  case 434:
                     var1 = this.jj_consume_token(434);
                     break label177;
                  case 435:
                     var1 = this.jj_consume_token(435);
                     break label177;
                  case 436:
                     var1 = this.jj_consume_token(436);
                     break label177;
                  case 437:
                     var1 = this.jj_consume_token(437);
                     break label177;
                  case 438:
                     var1 = this.jj_consume_token(438);
                     break label177;
                  case 439:
                     var1 = this.jj_consume_token(439);
                     break label177;
                  case 440:
                     var1 = this.jj_consume_token(440);
                     break label177;
                  case 441:
                     var1 = this.jj_consume_token(441);
                     break label177;
                  case 442:
                     var1 = this.jj_consume_token(442);
                     break label177;
                  case 443:
                     var1 = this.jj_consume_token(443);
                     break label177;
                  case 444:
                     var1 = this.jj_consume_token(444);
                     break label177;
                  case 445:
                     var1 = this.jj_consume_token(445);
                     break label177;
                  case 446:
                     var1 = this.jj_consume_token(446);
               }
            }
            break;
         case 93:
            var1 = this.jj_consume_token(93);
            break;
         case 105:
            var1 = this.jj_consume_token(105);
            break;
         case 114:
            var1 = this.jj_consume_token(114);
            break;
         case 184:
            var1 = this.jj_consume_token(184);
            break;
         case 271:
            var1 = this.jj_consume_token(271);
            break;
         case 272:
            var1 = this.jj_consume_token(272);
            break;
         case 273:
            var1 = this.jj_consume_token(273);
            break;
         case 274:
            var1 = this.jj_consume_token(274);
            break;
         case 275:
            var1 = this.jj_consume_token(275);
            break;
         case 276:
            var1 = this.jj_consume_token(276);
            break;
         case 277:
            var1 = this.jj_consume_token(277);
            break;
         case 278:
            var1 = this.jj_consume_token(278);
            break;
         case 279:
            var1 = this.jj_consume_token(279);
            break;
         case 280:
            var1 = this.jj_consume_token(280);
            break;
         case 281:
            var1 = this.jj_consume_token(281);
            break;
         case 282:
            var1 = this.jj_consume_token(282);
            break;
         case 283:
            var1 = this.jj_consume_token(283);
            break;
         case 284:
            var1 = this.jj_consume_token(284);
            break;
         case 285:
            var1 = this.jj_consume_token(285);
            break;
         case 286:
            var1 = this.jj_consume_token(286);
            break;
         case 289:
            var1 = this.jj_consume_token(289);
            break;
         case 290:
            var1 = this.jj_consume_token(290);
            break;
         case 291:
            var1 = this.jj_consume_token(291);
            break;
         case 292:
            var1 = this.jj_consume_token(292);
            break;
         case 293:
            var1 = this.jj_consume_token(293);
            break;
         case 294:
            var1 = this.jj_consume_token(294);
            break;
         case 295:
            var1 = this.jj_consume_token(295);
            break;
         case 297:
            var1 = this.jj_consume_token(297);
            break;
         case 299:
            var1 = this.jj_consume_token(299);
            break;
         case 300:
            var1 = this.jj_consume_token(300);
            break;
         case 301:
            var1 = this.jj_consume_token(301);
            break;
         case 302:
            var1 = this.jj_consume_token(302);
            break;
         case 303:
            var1 = this.jj_consume_token(303);
            break;
         case 304:
            var1 = this.jj_consume_token(304);
            break;
         case 305:
            var1 = this.jj_consume_token(305);
            break;
         case 306:
            var1 = this.jj_consume_token(306);
            break;
         case 307:
            var1 = this.jj_consume_token(307);
            break;
         case 308:
            var1 = this.jj_consume_token(308);
            break;
         case 309:
            var1 = this.jj_consume_token(309);
            break;
         case 310:
            var1 = this.jj_consume_token(310);
            break;
         case 311:
            var1 = this.jj_consume_token(311);
            break;
         case 312:
            var1 = this.jj_consume_token(312);
            break;
         case 313:
            var1 = this.jj_consume_token(313);
            break;
         case 314:
            var1 = this.jj_consume_token(314);
            break;
         case 315:
            var1 = this.jj_consume_token(315);
            break;
         case 316:
            var1 = this.jj_consume_token(316);
            break;
         case 317:
            var1 = this.jj_consume_token(317);
            break;
         case 318:
            var1 = this.jj_consume_token(318);
            break;
         case 319:
            var1 = this.jj_consume_token(319);
            break;
         case 320:
            var1 = this.jj_consume_token(320);
            break;
         case 321:
            var1 = this.jj_consume_token(321);
            break;
         case 322:
            var1 = this.jj_consume_token(322);
            break;
         case 362:
            var1 = this.jj_consume_token(362);
            break;
         case 364:
            var1 = this.jj_consume_token(364);
            break;
         case 365:
            var1 = this.jj_consume_token(365);
            break;
         case 369:
            var1 = this.jj_consume_token(369);
            break;
         case 382:
            var1 = this.jj_consume_token(382);
            break;
         case 383:
            var1 = this.jj_consume_token(383);
            break;
         case 384:
            var1 = this.jj_consume_token(384);
            break;
         case 385:
            var1 = this.jj_consume_token(385);
            break;
         case 386:
            var1 = this.jj_consume_token(386);
            break;
         case 387:
            var1 = this.jj_consume_token(387);
            break;
         case 388:
            var1 = this.jj_consume_token(388);
            break;
         case 390:
            var1 = this.jj_consume_token(390);
            break;
         case 391:
            var1 = this.jj_consume_token(391);
            break;
         case 392:
            var1 = this.jj_consume_token(392);
            break;
         case 393:
            var1 = this.jj_consume_token(393);
            break;
         case 394:
            var1 = this.jj_consume_token(394);
            break;
         case 395:
            var1 = this.jj_consume_token(395);
            break;
         case 396:
            var1 = this.jj_consume_token(396);
            break;
         case 397:
            var1 = this.jj_consume_token(397);
            break;
         case 398:
            var1 = this.jj_consume_token(398);
            break;
         case 399:
            var1 = this.jj_consume_token(399);
            break;
         case 400:
            var1 = this.jj_consume_token(400);
            break;
         case 401:
            var1 = this.jj_consume_token(401);
            break;
         case 402:
            var1 = this.jj_consume_token(402);
            break;
         case 403:
            var1 = this.jj_consume_token(403);
            break;
         case 404:
            var1 = this.jj_consume_token(404);
            break;
         case 405:
            var1 = this.jj_consume_token(405);
            break;
         case 406:
            var1 = this.jj_consume_token(406);
            break;
         case 407:
            var1 = this.jj_consume_token(407);
            break;
         case 408:
            var1 = this.jj_consume_token(408);
            break;
         case 409:
            var1 = this.jj_consume_token(409);
            break;
         case 410:
            var1 = this.jj_consume_token(410);
            break;
         case 413:
            var1 = this.jj_consume_token(413);
      }

      this.nextToLastTokenDelimitedIdentifier = this.lastTokenDelimitedIdentifier;
      this.lastTokenDelimitedIdentifier = false;
      this.thirdToLastIdentifierToken = this.nextToLastIdentifierToken;
      this.nextToLastIdentifierToken = this.lastIdentifierToken;
      this.lastIdentifierToken = var1;
      return var1.image;
   }

   public final String caseSensitiveIdentifierPlusReservedWords() throws ParseException {
      if (this.jj_2_92(1)) {
         String var2 = this.caseSensitiveIdentifier();
         return var2;
      } else {
         switch (this.jj_nt.kind) {
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 91:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
            case 233:
            case 234:
            case 235:
            case 236:
            case 237:
            case 239:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 246:
            case 247:
            case 248:
            case 249:
            case 251:
            case 252:
            case 253:
            case 254:
            case 255:
            case 256:
            case 257:
            case 259:
            case 261:
            case 262:
            case 263:
            case 264:
            case 265:
            case 267:
            case 268:
            case 269:
            case 270:
            case 296:
            case 360:
            case 361:
            case 363:
            case 366:
            case 367:
            case 368:
            case 370:
            case 371:
            case 375:
            case 376:
            case 377:
            case 378:
            case 379:
            case 380:
            case 381:
            case 411:
            case 416:
               String var1 = this.reservedKeyword();
               return var1;
            case 80:
            case 90:
            case 93:
            case 105:
            case 114:
            case 184:
            case 238:
            case 250:
            case 258:
            case 260:
            case 266:
            case 271:
            case 272:
            case 273:
            case 274:
            case 275:
            case 276:
            case 277:
            case 278:
            case 279:
            case 280:
            case 281:
            case 282:
            case 283:
            case 284:
            case 285:
            case 286:
            case 287:
            case 288:
            case 289:
            case 290:
            case 291:
            case 292:
            case 293:
            case 294:
            case 295:
            case 297:
            case 298:
            case 299:
            case 300:
            case 301:
            case 302:
            case 303:
            case 304:
            case 305:
            case 306:
            case 307:
            case 308:
            case 309:
            case 310:
            case 311:
            case 312:
            case 313:
            case 314:
            case 315:
            case 316:
            case 317:
            case 318:
            case 319:
            case 320:
            case 321:
            case 322:
            case 323:
            case 324:
            case 325:
            case 326:
            case 327:
            case 328:
            case 329:
            case 330:
            case 331:
            case 332:
            case 333:
            case 334:
            case 335:
            case 336:
            case 337:
            case 338:
            case 339:
            case 340:
            case 341:
            case 342:
            case 343:
            case 344:
            case 345:
            case 346:
            case 347:
            case 348:
            case 349:
            case 350:
            case 351:
            case 352:
            case 353:
            case 354:
            case 355:
            case 356:
            case 357:
            case 358:
            case 359:
            case 362:
            case 364:
            case 365:
            case 369:
            case 372:
            case 373:
            case 374:
            case 382:
            case 383:
            case 384:
            case 385:
            case 386:
            case 387:
            case 388:
            case 389:
            case 390:
            case 391:
            case 392:
            case 393:
            case 394:
            case 395:
            case 396:
            case 397:
            case 398:
            case 399:
            case 400:
            case 401:
            case 402:
            case 403:
            case 404:
            case 405:
            case 406:
            case 407:
            case 408:
            case 409:
            case 410:
            case 412:
            case 413:
            case 414:
            case 415:
            default:
               this.jj_la1[378] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final String caseInsensitiveIdentifierPlusReservedWords() throws ParseException, StandardException {
      if (this.jj_2_93(1)) {
         String var2 = this.identifier(128, true);
         return var2;
      } else {
         switch (this.jj_nt.kind) {
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 91:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
            case 233:
            case 234:
            case 235:
            case 236:
            case 237:
            case 239:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 246:
            case 247:
            case 248:
            case 249:
            case 251:
            case 252:
            case 253:
            case 254:
            case 255:
            case 256:
            case 257:
            case 259:
            case 261:
            case 262:
            case 263:
            case 264:
            case 265:
            case 267:
            case 268:
            case 269:
            case 270:
            case 296:
            case 360:
            case 361:
            case 363:
            case 366:
            case 367:
            case 368:
            case 370:
            case 371:
            case 375:
            case 376:
            case 377:
            case 378:
            case 379:
            case 380:
            case 381:
            case 411:
            case 416:
               String var1 = this.reservedKeyword();
               return StringUtil.SQLToUpperCase(var1);
            case 80:
            case 90:
            case 93:
            case 105:
            case 114:
            case 184:
            case 238:
            case 250:
            case 258:
            case 260:
            case 266:
            case 271:
            case 272:
            case 273:
            case 274:
            case 275:
            case 276:
            case 277:
            case 278:
            case 279:
            case 280:
            case 281:
            case 282:
            case 283:
            case 284:
            case 285:
            case 286:
            case 287:
            case 288:
            case 289:
            case 290:
            case 291:
            case 292:
            case 293:
            case 294:
            case 295:
            case 297:
            case 298:
            case 299:
            case 300:
            case 301:
            case 302:
            case 303:
            case 304:
            case 305:
            case 306:
            case 307:
            case 308:
            case 309:
            case 310:
            case 311:
            case 312:
            case 313:
            case 314:
            case 315:
            case 316:
            case 317:
            case 318:
            case 319:
            case 320:
            case 321:
            case 322:
            case 323:
            case 324:
            case 325:
            case 326:
            case 327:
            case 328:
            case 329:
            case 330:
            case 331:
            case 332:
            case 333:
            case 334:
            case 335:
            case 336:
            case 337:
            case 338:
            case 339:
            case 340:
            case 341:
            case 342:
            case 343:
            case 344:
            case 345:
            case 346:
            case 347:
            case 348:
            case 349:
            case 350:
            case 351:
            case 352:
            case 353:
            case 354:
            case 355:
            case 356:
            case 357:
            case 358:
            case 359:
            case 362:
            case 364:
            case 365:
            case 369:
            case 372:
            case 373:
            case 374:
            case 382:
            case 383:
            case 384:
            case 385:
            case 386:
            case 387:
            case 388:
            case 389:
            case 390:
            case 391:
            case 392:
            case 393:
            case 394:
            case 395:
            case 396:
            case 397:
            case 398:
            case 399:
            case 400:
            case 401:
            case 402:
            case 403:
            case 404:
            case 405:
            case 406:
            case 407:
            case 408:
            case 409:
            case 410:
            case 412:
            case 413:
            case 414:
            case 415:
            default:
               this.jj_la1[379] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      }
   }

   public final String caseSensitiveIdentifier() throws ParseException {
      switch (this.jj_nt.kind) {
         case 480:
            Token var2 = this.jj_consume_token(480);
            this.nextToLastTokenDelimitedIdentifier = this.lastTokenDelimitedIdentifier;
            this.lastTokenDelimitedIdentifier = false;
            return var2.image;
         case 486:
            String var1 = this.delimitedIdentifier();
            return var1;
         default:
            this.jj_la1[380] = this.jj_gen;
            if (this.jj_2_94(1)) {
               String var3 = this.nonReservedKeyword();
               return var3;
            } else {
               this.jj_consume_token(-1);
               throw new ParseException();
            }
      }
   }

   private final boolean jj_2_1(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_1();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(0, var1);
      }

      return var3;
   }

   private final boolean jj_2_2(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_2();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(1, var1);
      }

      return var3;
   }

   private final boolean jj_2_3(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_3();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(2, var1);
      }

      return var3;
   }

   private final boolean jj_2_4(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_4();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(3, var1);
      }

      return var3;
   }

   private final boolean jj_2_5(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_5();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(4, var1);
      }

      return var3;
   }

   private final boolean jj_2_6(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_6();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(5, var1);
      }

      return var3;
   }

   private final boolean jj_2_7(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_7();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(6, var1);
      }

      return var3;
   }

   private final boolean jj_2_8(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_8();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(7, var1);
      }

      return var3;
   }

   private final boolean jj_2_9(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_9();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(8, var1);
      }

      return var3;
   }

   private final boolean jj_2_10(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_10();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(9, var1);
      }

      return var3;
   }

   private final boolean jj_2_11(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_11();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(10, var1);
      }

      return var3;
   }

   private final boolean jj_2_12(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_12();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(11, var1);
      }

      return var3;
   }

   private final boolean jj_2_13(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_13();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(12, var1);
      }

      return var3;
   }

   private final boolean jj_2_14(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_14();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(13, var1);
      }

      return var3;
   }

   private final boolean jj_2_15(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_15();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(14, var1);
      }

      return var3;
   }

   private final boolean jj_2_16(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_16();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(15, var1);
      }

      return var3;
   }

   private final boolean jj_2_17(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_17();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(16, var1);
      }

      return var3;
   }

   private final boolean jj_2_18(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_18();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(17, var1);
      }

      return var3;
   }

   private final boolean jj_2_19(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_19();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(18, var1);
      }

      return var3;
   }

   private final boolean jj_2_20(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_20();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(19, var1);
      }

      return var3;
   }

   private final boolean jj_2_21(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_21();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(20, var1);
      }

      return var3;
   }

   private final boolean jj_2_22(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_22();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(21, var1);
      }

      return var3;
   }

   private final boolean jj_2_23(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_23();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(22, var1);
      }

      return var3;
   }

   private final boolean jj_2_24(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_24();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(23, var1);
      }

      return var3;
   }

   private final boolean jj_2_25(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_25();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(24, var1);
      }

      return var3;
   }

   private final boolean jj_2_26(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_26();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(25, var1);
      }

      return var3;
   }

   private final boolean jj_2_27(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_27();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(26, var1);
      }

      return var3;
   }

   private final boolean jj_2_28(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_28();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(27, var1);
      }

      return var3;
   }

   private final boolean jj_2_29(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_29();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(28, var1);
      }

      return var3;
   }

   private final boolean jj_2_30(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_30();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(29, var1);
      }

      return var3;
   }

   private final boolean jj_2_31(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_31();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(30, var1);
      }

      return var3;
   }

   private final boolean jj_2_32(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_32();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(31, var1);
      }

      return var3;
   }

   private final boolean jj_2_33(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_33();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(32, var1);
      }

      return var3;
   }

   private final boolean jj_2_34(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_34();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(33, var1);
      }

      return var3;
   }

   private final boolean jj_2_35(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_35();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(34, var1);
      }

      return var3;
   }

   private final boolean jj_2_36(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_36();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(35, var1);
      }

      return var3;
   }

   private final boolean jj_2_37(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_37();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(36, var1);
      }

      return var3;
   }

   private final boolean jj_2_38(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_38();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(37, var1);
      }

      return var3;
   }

   private final boolean jj_2_39(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_39();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(38, var1);
      }

      return var3;
   }

   private final boolean jj_2_40(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_40();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(39, var1);
      }

      return var3;
   }

   private final boolean jj_2_41(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_41();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(40, var1);
      }

      return var3;
   }

   private final boolean jj_2_42(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_42();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(41, var1);
      }

      return var3;
   }

   private final boolean jj_2_43(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_43();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(42, var1);
      }

      return var3;
   }

   private final boolean jj_2_44(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_44();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(43, var1);
      }

      return var3;
   }

   private final boolean jj_2_45(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_45();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(44, var1);
      }

      return var3;
   }

   private final boolean jj_2_46(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_46();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(45, var1);
      }

      return var3;
   }

   private final boolean jj_2_47(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_47();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(46, var1);
      }

      return var3;
   }

   private final boolean jj_2_48(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_48();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(47, var1);
      }

      return var3;
   }

   private final boolean jj_2_49(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_49();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(48, var1);
      }

      return var3;
   }

   private final boolean jj_2_50(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_50();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(49, var1);
      }

      return var3;
   }

   private final boolean jj_2_51(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_51();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(50, var1);
      }

      return var3;
   }

   private final boolean jj_2_52(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_52();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(51, var1);
      }

      return var3;
   }

   private final boolean jj_2_53(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_53();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(52, var1);
      }

      return var3;
   }

   private final boolean jj_2_54(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_54();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(53, var1);
      }

      return var3;
   }

   private final boolean jj_2_55(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_55();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(54, var1);
      }

      return var3;
   }

   private final boolean jj_2_56(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_56();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(55, var1);
      }

      return var3;
   }

   private final boolean jj_2_57(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_57();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(56, var1);
      }

      return var3;
   }

   private final boolean jj_2_58(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_58();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(57, var1);
      }

      return var3;
   }

   private final boolean jj_2_59(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_59();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(58, var1);
      }

      return var3;
   }

   private final boolean jj_2_60(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_60();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(59, var1);
      }

      return var3;
   }

   private final boolean jj_2_61(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_61();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(60, var1);
      }

      return var3;
   }

   private final boolean jj_2_62(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_62();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(61, var1);
      }

      return var3;
   }

   private final boolean jj_2_63(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_63();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(62, var1);
      }

      return var3;
   }

   private final boolean jj_2_64(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_64();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(63, var1);
      }

      return var3;
   }

   private final boolean jj_2_65(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_65();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(64, var1);
      }

      return var3;
   }

   private final boolean jj_2_66(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_66();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(65, var1);
      }

      return var3;
   }

   private final boolean jj_2_67(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_67();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(66, var1);
      }

      return var3;
   }

   private final boolean jj_2_68(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_68();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(67, var1);
      }

      return var3;
   }

   private final boolean jj_2_69(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_69();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(68, var1);
      }

      return var3;
   }

   private final boolean jj_2_70(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_70();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(69, var1);
      }

      return var3;
   }

   private final boolean jj_2_71(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_71();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(70, var1);
      }

      return var3;
   }

   private final boolean jj_2_72(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_72();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(71, var1);
      }

      return var3;
   }

   private final boolean jj_2_73(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_73();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(72, var1);
      }

      return var3;
   }

   private final boolean jj_2_74(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_74();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(73, var1);
      }

      return var3;
   }

   private final boolean jj_2_75(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_75();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(74, var1);
      }

      return var3;
   }

   private final boolean jj_2_76(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_76();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(75, var1);
      }

      return var3;
   }

   private final boolean jj_2_77(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_77();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(76, var1);
      }

      return var3;
   }

   private final boolean jj_2_78(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_78();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(77, var1);
      }

      return var3;
   }

   private final boolean jj_2_79(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_79();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(78, var1);
      }

      return var3;
   }

   private final boolean jj_2_80(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_80();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(79, var1);
      }

      return var3;
   }

   private final boolean jj_2_81(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_81();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(80, var1);
      }

      return var3;
   }

   private final boolean jj_2_82(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_82();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(81, var1);
      }

      return var3;
   }

   private final boolean jj_2_83(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_83();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(82, var1);
      }

      return var3;
   }

   private final boolean jj_2_84(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_84();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(83, var1);
      }

      return var3;
   }

   private final boolean jj_2_85(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_85();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(84, var1);
      }

      return var3;
   }

   private final boolean jj_2_86(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_86();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(85, var1);
      }

      return var3;
   }

   private final boolean jj_2_87(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_87();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(86, var1);
      }

      return var3;
   }

   private final boolean jj_2_88(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_88();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(87, var1);
      }

      return var3;
   }

   private final boolean jj_2_89(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_89();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(88, var1);
      }

      return var3;
   }

   private final boolean jj_2_90(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_90();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(89, var1);
      }

      return var3;
   }

   private final boolean jj_2_91(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_91();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(90, var1);
      }

      return var3;
   }

   private final boolean jj_2_92(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_92();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(91, var1);
      }

      return var3;
   }

   private final boolean jj_2_93(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_93();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(92, var1);
      }

      return var3;
   }

   private final boolean jj_2_94(int var1) {
      this.jj_la = var1;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_94();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(93, var1);
      }

      return var3;
   }

   private final boolean jj_3R_174() {
      return this.jj_3R_264();
   }

   private final boolean jj_3R_280() {
      return this.jj_scan_token(363);
   }

   private final boolean jj_3R_87() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_173()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_174()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_175()) {
               this.jj_scanpos = var1;
               if (this.jj_3_45()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_176()) {
                     this.jj_scanpos = var1;
                     if (this.jj_3R_177()) {
                        this.jj_scanpos = var1;
                        if (this.jj_3R_178()) {
                           this.jj_scanpos = var1;
                           if (this.jj_3R_179()) {
                              return true;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_173() {
      return this.jj_scan_token(366);
   }

   private final boolean jj_3_36() {
      return this.jj_3R_86();
   }

   private final boolean jj_3R_129() {
      return this.jj_scan_token(218);
   }

   private final boolean jj_3R_372() {
      return this.jj_scan_token(377);
   }

   private final boolean jj_3R_78() {
      return this.jj_3R_84();
   }

   private final boolean jj_3R_367() {
      return this.jj_scan_token(327);
   }

   private final boolean jj_3R_131() {
      return this.jj_scan_token(129);
   }

   private final boolean jj_3_60() {
      return this.jj_3R_61();
   }

   private final boolean jj_3R_366() {
      return this.jj_scan_token(220);
   }

   private final boolean jj_3R_355() {
      return this.jj_scan_token(226);
   }

   private final boolean jj_3R_365() {
      return this.jj_scan_token(333);
   }

   private final boolean jj_3R_354() {
      return this.jj_scan_token(112);
   }

   private final boolean jj_3R_299() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_365()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_366()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_367()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3_44() {
      return this.jj_3R_91();
   }

   private final boolean jj_3R_300() {
      return this.jj_3R_368();
   }

   private final boolean jj_3_28() {
      return this.jj_3R_78();
   }

   private final boolean jj_3R_49() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(1).kind == 218 && (this.getToken(2).kind == 442 && (this.getToken(3).kind == 458 && this.isPrivilegeKeywordExceptTrigger(this.getToken(4).kind) || this.getToken(3).kind == 195) || this.isPrivilegeKeywordExceptTrigger(this.getToken(2).kind));
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_128()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 218 && (this.getToken(2).kind == 442 && (this.getToken(3).kind == 458 && !this.isPrivilegeKeywordExceptTrigger(this.getToken(4).kind) || this.getToken(3).kind == 147) || !this.isPrivilegeKeywordExceptTrigger(this.getToken(2).kind));
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_129()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_128() {
      return this.jj_scan_token(218);
   }

   private final boolean jj_3R_353() {
      return this.jj_scan_token(256);
   }

   private final boolean jj_3R_279() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_353()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_354()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_355()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3_43() {
      return this.jj_3R_90();
   }

   private final boolean jj_3R_102() {
      return this.jj_3R_207();
   }

   private final boolean jj_3R_101() {
      return this.jj_3R_206();
   }

   private final boolean jj_3_59() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.newInvocationFollows(1);
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_101()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_102()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_205() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3_59()) {
         this.jj_scanpos = var1;
         if (this.jj_3_60()) {
            this.jj_scanpos = var1;
            this.lookingAhead = true;
            this.jj_semLA = this.getToken(1).kind == 453 && (this.getToken(2).kind == 225 || this.getToken(2).kind == 259);
            this.lookingAhead = false;
            if (!this.jj_semLA || this.jj_3R_275()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_276()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_337() {
      return this.jj_3R_280();
   }

   private final boolean jj_3_35() {
      return this.jj_3R_85();
   }

   private final boolean jj_3R_370() {
      return this.jj_scan_token(369);
   }

   private final boolean jj_3R_254() {
      return this.jj_3R_61();
   }

   private final boolean jj_3R_159() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind == 460 && (this.getToken(3).kind == 455 || this.getToken(4).kind == 460 && this.getToken(5).kind == 455);
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_254()) {
         this.jj_scanpos = var1;
         if (this.jj_3_28()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_336() {
      return this.jj_3R_279();
   }

   private final boolean jj_3R_209() {
      return this.jj_3R_80();
   }

   private final boolean jj_3_15() {
      return this.jj_3R_63();
   }

   private final boolean jj_3R_248() {
      return this.jj_3R_300();
   }

   private final boolean jj_3R_141() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.javaClassFollows();
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_248()) {
         this.jj_scanpos = var1;
         if (this.jj_3_35()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_335() {
      return this.jj_3R_385();
   }

   private final boolean jj_3R_268() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_335()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_336()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_337()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_130() {
      return this.jj_scan_token(106);
   }

   private final boolean jj_3R_375() {
      return this.jj_3R_91();
   }

   private final boolean jj_3R_309() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind == 460 && this.getToken(4).kind == 453;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_375()) {
         this.jj_scanpos = var1;
         if (this.jj_3_15()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_105() {
      return this.jj_3R_209();
   }

   private final boolean jj_3R_156() {
      return this.jj_scan_token(128);
   }

   private final boolean jj_3R_75() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind == 326;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_156()) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(128)) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_56() {
      return this.jj_3R_136();
   }

   private final boolean jj_3_3() {
      return this.jj_3R_49();
   }

   private final boolean jj_3_2() {
      return this.jj_3R_48();
   }

   private final boolean jj_3R_77() {
      return this.jj_3R_159();
   }

   private final boolean jj_3R_55() {
      return this.jj_3R_135();
   }

   private final boolean jj_3R_100() {
      return this.jj_3R_205();
   }

   private final boolean jj_3R_408() {
      return this.jj_scan_token(140);
   }

   private final boolean jj_3R_200() {
      return this.jj_3R_274();
   }

   private final boolean jj_3_25() {
      return this.jj_3R_75();
   }

   private final boolean jj_3R_407() {
      return this.jj_scan_token(249);
   }

   private final boolean jj_3R_384() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_407()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_408()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3_67() {
      return this.jj_3R_105();
   }

   private final boolean jj_3_34() {
      return this.jj_3R_84();
   }

   private final boolean jj_3R_96() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3_34()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_200()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3_1() {
      return this.jj_3R_47();
   }

   private final boolean jj_3R_54() {
      return this.jj_3R_134();
   }

   private final boolean jj_3_27() {
      return this.jj_3R_77();
   }

   private final boolean jj_3R_53() {
      return this.jj_3R_133();
   }

   private final boolean jj_3R_52() {
      return this.jj_3R_132();
   }

   private final boolean jj_3R_51() {
      return this.jj_3R_131();
   }

   private final boolean jj_3R_50() {
      return this.jj_3R_130();
   }

   private final boolean jj_3R_155() {
      return this.jj_scan_token(214);
   }

   private final boolean jj_3R_140() {
      return this.jj_scan_token(358);
   }

   private final boolean jj_3_4() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_50()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_51()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_52()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_53()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_54()) {
                     this.jj_scanpos = var1;
                     if (this.jj_3_1()) {
                        this.jj_scanpos = var1;
                        if (this.jj_3R_55()) {
                           this.jj_scanpos = var1;
                           if (this.jj_3_2()) {
                              this.jj_scanpos = var1;
                              if (this.jj_3_3()) {
                                 this.jj_scanpos = var1;
                                 if (this.jj_3R_56()) {
                                    return true;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3_94() {
      return this.jj_3R_122();
   }

   private final boolean jj_3R_314() {
      return this.jj_3R_87();
   }

   private final boolean jj_3R_260() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = (this.getToken(1).kind == 366 || this.getToken(1).kind == 271 || this.getToken(1).kind == 272 || this.getToken(1).kind == 438 || this.getToken(1).kind == 311 || this.getToken(1).kind == 93 || this.getToken(1).kind == 258 || this.getToken(1).kind == 293 || this.getToken(1).kind == 235 || this.getToken(1).kind == 376 || this.getToken(1).kind == 255 || this.getToken(1).kind == 179 || this.getToken(1).kind == 444 || this.getToken(1).kind == 403 || this.getToken(1).kind == 370 || this.getToken(1).kind == 375 || this.getToken(1).kind == 248 || this.getToken(1).kind == 285 || this.getToken(1).kind == 350 || this.getToken(1).kind == 351 || this.getToken(1).kind == 128 || this.getToken(1).kind == 88 || this.getToken(1).kind == 261 || this.getToken(1).kind == 168 || this.getToken(1).kind == 167 || this.getToken(1).kind == 228 || this.getToken(1).kind == 368 || this.getToken(1).kind == 270 || this.getToken(1).kind == 314 || this.getToken(1).kind == 286 || this.getToken(1).kind == 157 || this.getToken(1).kind == 183 || this.getToken(1).kind == 224 || this.getToken(1).kind == 301 || this.getToken(1).kind == 404 || this.getToken(1).kind == 379 || this.getToken(1).kind == 381 || this.getToken(1).kind == 378 || this.getToken(1).kind == 380) && this.getToken(2).kind == 453;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_314()) {
         this.jj_scanpos = var1;
         if (this.jj_3_43()) {
            this.jj_scanpos = var1;
            if (this.jj_3_44()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_238() {
      return this.jj_3R_292();
   }

   private final boolean jj_3_14() {
      return this.jj_3R_62();
   }

   private final boolean jj_3_58() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(72)) {
         this.jj_scanpos = var1;
      }

      return this.jj_3R_64();
   }

   private final boolean jj_3R_121() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(96)) {
         this.jj_scanpos = var1;
      }

      return this.jj_3R_66();
   }

   private final boolean jj_3R_123() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_237()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_238()) {
            this.jj_scanpos = var1;
            if (this.jj_3_94()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_237() {
      return this.jj_scan_token(480);
   }

   private final boolean jj_3R_60() {
      return this.jj_3R_84();
   }

   private final boolean jj_3R_62() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(1).kind == 358 && this.getToken(2).kind != 190;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_139()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 358 && this.getToken(2).kind == 190;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_140()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_139() {
      return this.jj_scan_token(358);
   }

   private final boolean jj_3R_158() {
      return this.jj_scan_token(66);
   }

   private final boolean jj_3_40() {
      return this.jj_3R_84();
   }

   private final boolean jj_3R_171() {
      return this.jj_3R_262();
   }

   private final boolean jj_3R_170() {
      return this.jj_3R_261();
   }

   private final boolean jj_3R_341() {
      return this.jj_3R_386();
   }

   private final boolean jj_3R_385() {
      return this.jj_scan_token(472);
   }

   private final boolean jj_3R_74() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_154()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_155()) {
            this.jj_scanpos = var1;
            if (this.jj_3_25()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_154() {
      return this.jj_scan_token(143);
   }

   private final boolean jj_3_93() {
      return this.jj_3R_64();
   }

   private final boolean jj_3R_340() {
      return this.jj_scan_token(351);
   }

   private final boolean jj_3R_204() {
      return this.jj_scan_token(451);
   }

   private final boolean jj_3_90() {
      return this.jj_3R_64();
   }

   private final boolean jj_3_57() {
      return this.jj_3R_100();
   }

   private final boolean jj_3R_99() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3_57()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_204()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_339() {
      return this.jj_scan_token(285);
   }

   private final boolean jj_3R_76() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(1).kind == 127 && this.getToken(2).kind != 460 && this.getToken(2).kind != 463;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_157()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 66 && this.getToken(2).kind != 460 && this.getToken(2).kind != 463;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_158()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_157() {
      return this.jj_scan_token(127);
   }

   private final boolean jj_3R_263() {
      return this.jj_3R_315();
   }

   private final boolean jj_3_66() {
      return this.jj_3R_80();
   }

   private final boolean jj_3R_169() {
      return this.jj_scan_token(453);
   }

   private final boolean jj_3_26() {
      return this.jj_3R_76();
   }

   private final boolean jj_3R_338() {
      return this.jj_scan_token(350);
   }

   private final boolean jj_3R_271() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_338()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_339()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_340()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_341()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3_56() {
      return this.jj_3R_99();
   }

   private final boolean jj_3R_118() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(96)) {
         this.jj_scanpos = var1;
      }

      return this.jj_3R_64();
   }

   private final boolean jj_3_92() {
      return this.jj_3R_123();
   }

   private final boolean jj_3R_172() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3_92()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_263()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_346() {
      return this.jj_scan_token(368);
   }

   private final boolean jj_3R_345() {
      return this.jj_scan_token(228);
   }

   private final boolean jj_3_42() {
      return this.jj_3R_89();
   }

   private final boolean jj_3R_344() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(168)) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(167)) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_273() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_344()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_345()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_346()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_343() {
      return this.jj_scan_token(261);
   }

   private final boolean jj_3R_168() {
      return this.jj_3R_260();
   }

   private final boolean jj_3R_431() {
      return this.jj_scan_token(225);
   }

   private final boolean jj_3R_342() {
      return this.jj_scan_token(88);
   }

   private final boolean jj_3R_272() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_342()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_343()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_307() {
      return this.jj_3R_273();
   }

   private final boolean jj_3R_257() {
      return this.jj_scan_token(460);
   }

   private final boolean jj_3R_296() {
      return this.jj_scan_token(309);
   }

   private final boolean jj_3R_167() {
      return this.jj_3R_259();
   }

   private final boolean jj_3R_236() {
      return this.jj_3R_291();
   }

   private final boolean jj_3R_195() {
      return this.jj_3R_273();
   }

   private final boolean jj_3R_93() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3_50()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_195()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3_50() {
      return this.jj_3R_75();
   }

   private final boolean jj_3R_430() {
      return this.jj_3R_432();
   }

   private final boolean jj_3R_166() {
      return this.jj_3R_206();
   }

   private final boolean jj_3R_262() {
      return this.jj_scan_token(188);
   }

   private final boolean jj_3R_429() {
      return this.jj_3R_431();
   }

   private final boolean jj_3R_428() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_429()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_430()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3_41() {
      return this.jj_3R_88();
   }

   private final boolean jj_3_52() {
      return this.jj_3R_97();
   }

   private final boolean jj_3_83() {
      return this.jj_3R_117();
   }

   private final boolean jj_3R_165() {
      return this.jj_scan_token(108);
   }

   private final boolean jj_3R_256() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(478)) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(460)) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_161() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(3).kind == 453;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_256()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_257()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_164() {
      return this.jj_scan_token(108);
   }

   private final boolean jj_3R_235() {
      return this.jj_scan_token(68);
   }

   private final boolean jj_3R_382() {
      return this.jj_scan_token(491);
   }

   private final boolean jj_3R_234() {
      return this.jj_scan_token(68);
   }

   private final boolean jj_3R_85() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.escapedValueFunctionFollows();
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_163()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(2).kind == 222 || this.getToken(2).kind == 436;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_164()) {
            this.jj_scanpos = var1;
            this.lookingAhead = true;
            this.jj_semLA = this.getToken(2).kind == 172;
            this.lookingAhead = false;
            if (!this.jj_semLA || this.jj_3R_165()) {
               this.jj_scanpos = var1;
               if (this.jj_3_41()) {
                  this.jj_scanpos = var1;
                  this.lookingAhead = true;
                  this.jj_semLA = this.newInvocationFollows(1);
                  this.lookingAhead = false;
                  if (!this.jj_semLA || this.jj_3R_166()) {
                     this.jj_scanpos = var1;
                     this.lookingAhead = true;
                     this.jj_semLA = this.windowOrAggregateFunctionFollows();
                     this.lookingAhead = false;
                     if (!this.jj_semLA || this.jj_3R_167()) {
                        this.jj_scanpos = var1;
                        this.lookingAhead = true;
                        this.jj_semLA = this.miscBuiltinFollows();
                        this.lookingAhead = false;
                        if (!this.jj_semLA || this.jj_3R_168()) {
                           this.jj_scanpos = var1;
                           if (this.jj_3_42()) {
                              this.jj_scanpos = var1;
                              if (this.jj_3R_169()) {
                                 this.jj_scanpos = var1;
                                 if (this.jj_3R_170()) {
                                    this.jj_scanpos = var1;
                                    if (this.jj_3R_171()) {
                                       return true;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_163() {
      return this.jj_scan_token(451);
   }

   private final boolean jj_3R_373() {
      return this.jj_scan_token(193);
   }

   private final boolean jj_3R_306() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_373()) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(117)) {
            this.jj_scanpos = var1;
            if (this.jj_scan_token(116)) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_291() {
      return this.jj_scan_token(305);
   }

   private final boolean jj_3R_253() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_306()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_307()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_83() {
      return this.jj_3R_161();
   }

   private final boolean jj_3R_202() {
      return this.jj_scan_token(320);
   }

   private final boolean jj_3_88() {
      return this.jj_3R_121();
   }

   private final boolean jj_3R_261() {
      return this.jj_scan_token(87);
   }

   private final boolean jj_3R_381() {
      return this.jj_scan_token(490);
   }

   private final boolean jj_3_33() {
      return this.jj_3R_83();
   }

   private final boolean jj_3R_97() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind == 175;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_201()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_202()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_201() {
      return this.jj_scan_token(320);
   }

   private final boolean jj_3R_199() {
      return this.jj_scan_token(428);
   }

   private final boolean jj_3R_413() {
      return this.jj_3R_385();
   }

   private final boolean jj_3_89() {
      return this.jj_3R_114();
   }

   private final boolean jj_3R_63() {
      return this.jj_3R_141();
   }

   private final boolean jj_3R_120() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_233()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 68 && this.getToken(2).kind != 100;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_234()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_235()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_236()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_233() {
      return this.jj_scan_token(65);
   }

   private final boolean jj_3R_422() {
      return this.jj_scan_token(370);
   }

   private final boolean jj_3_24() {
      return this.jj_3R_74();
   }

   private final boolean jj_3R_95() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind == 432;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_198()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_199()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_198() {
      return this.jj_scan_token(428);
   }

   private final boolean jj_3R_421() {
      return this.jj_scan_token(375);
   }

   private final boolean jj_3R_416() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_421()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_422()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_419() {
      return this.jj_scan_token(453);
   }

   private final boolean jj_3R_73() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_153()) {
         this.jj_scanpos = var1;
         if (this.jj_3_24()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_153() {
      return this.jj_3R_253();
   }

   private final boolean jj_3R_418() {
      return this.jj_3R_428();
   }

   private final boolean jj_3R_411() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_418()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_419()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_402() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(181)) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(77)) {
            this.jj_scanpos = var1;
            if (this.jj_scan_token(182)) {
               this.jj_scanpos = var1;
               if (this.jj_scan_token(236)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3_82() {
      return this.jj_3R_84();
   }

   private final boolean jj_3R_412() {
      return this.jj_scan_token(361);
   }

   private final boolean jj_3R_388() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_412()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_413()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_400() {
      return this.jj_scan_token(459);
   }

   private final boolean jj_3R_399() {
      return this.jj_scan_token(457);
   }

   private final boolean jj_3R_374() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_399()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_400()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_398() {
      return this.jj_scan_token(185);
   }

   private final boolean jj_3R_364() {
      return this.jj_scan_token(451);
   }

   private final boolean jj_3R_363() {
      return this.jj_3R_388();
   }

   private final boolean jj_3R_397() {
      return this.jj_3R_252();
   }

   private final boolean jj_3R_396() {
      return this.jj_scan_token(80);
   }

   private final boolean jj_3R_377() {
      return this.jj_3R_402();
   }

   private final boolean jj_3R_298() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_363()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_364()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_395() {
      return this.jj_scan_token(318);
   }

   private final boolean jj_3R_197() {
      return this.jj_scan_token(83);
   }

   private final boolean jj_3R_394() {
      return this.jj_scan_token(278);
   }

   private final boolean jj_3R_94() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind == 422;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_196()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_197()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_196() {
      return this.jj_scan_token(83);
   }

   private final boolean jj_3R_393() {
      return this.jj_scan_token(275);
   }

   private final boolean jj_3_38() {
      return this.jj_scan_token(147);
   }

   private final boolean jj_3_65() {
      return this.jj_3R_76();
   }

   private final boolean jj_3_39() {
      return this.jj_3R_80();
   }

   private final boolean jj_3R_103() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3_65()) {
         this.jj_scanpos = var1;
      }

      return this.jj_3R_80();
   }

   private final boolean jj_3R_420() {
      return this.jj_scan_token(138);
   }

   private final boolean jj_3R_371() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_393()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_394()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_395()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_396()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_397()) {
                     this.jj_scanpos = var1;
                     if (this.jj_3R_398()) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3_12() {
      return this.jj_3R_60();
   }

   private final boolean jj_3R_308() {
      return this.jj_3R_374();
   }

   private final boolean jj_3R_208() {
      return false;
   }

   private final boolean jj_3R_255() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_308()) {
         this.jj_scanpos = var1;
      }

      return this.jj_3R_309();
   }

   private final boolean jj_3_64() {
      return this.jj_3R_64();
   }

   private final boolean jj_3_63() {
      return this.jj_3R_104();
   }

   private final boolean jj_3R_404() {
      return this.jj_scan_token(248);
   }

   private final boolean jj_3R_104() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind == 453 || this.getToken(2).kind == 480;
      this.lookingAhead = false;
      if (this.jj_semLA && !this.jj_3R_208()) {
         return this.jj_scan_token(372);
      } else {
         return true;
      }
   }

   private final boolean jj_3R_152() {
      return this.jj_scan_token(411);
   }

   private final boolean jj_3R_403() {
      return this.jj_3R_416();
   }

   private final boolean jj_3R_378() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_403()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_404()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3_13() {
      return this.jj_3R_61();
   }

   private final boolean jj_3_62() {
      return this.jj_3R_104();
   }

   private final boolean jj_3_81() {
      return this.jj_3R_84();
   }

   private final boolean jj_3R_313() {
      return this.jj_scan_token(374);
   }

   private final boolean jj_3R_151() {
      return this.jj_scan_token(187);
   }

   private final boolean jj_3R_387() {
      return this.jj_3R_411();
   }

   private final boolean jj_3_49() {
      return this.jj_3R_94();
   }

   private final boolean jj_3R_312() {
      return this.jj_3R_377();
   }

   private final boolean jj_3R_383() {
      return this.jj_scan_token(451);
   }

   private final boolean jj_3R_150() {
      return this.jj_scan_token(185);
   }

   private final boolean jj_3_61() {
      return this.jj_3R_103();
   }

   private final boolean jj_3R_122() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(271)) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(272)) {
            this.jj_scanpos = var1;
            if (this.jj_scan_token(273)) {
               this.jj_scanpos = var1;
               if (this.jj_scan_token(382)) {
                  this.jj_scanpos = var1;
                  if (this.jj_scan_token(383)) {
                     this.jj_scanpos = var1;
                     if (this.jj_scan_token(274)) {
                        this.jj_scanpos = var1;
                        if (this.jj_scan_token(384)) {
                           this.jj_scanpos = var1;
                           if (this.jj_scan_token(80)) {
                              this.jj_scanpos = var1;
                              if (this.jj_scan_token(275)) {
                                 this.jj_scanpos = var1;
                                 if (this.jj_scan_token(276)) {
                                    this.jj_scanpos = var1;
                                    if (this.jj_scan_token(277)) {
                                       this.jj_scanpos = var1;
                                       if (this.jj_scan_token(385)) {
                                          this.jj_scanpos = var1;
                                          if (this.jj_scan_token(278)) {
                                             this.jj_scanpos = var1;
                                             if (this.jj_scan_token(93)) {
                                                this.jj_scanpos = var1;
                                                if (this.jj_scan_token(279)) {
                                                   this.jj_scanpos = var1;
                                                   if (this.jj_scan_token(280)) {
                                                      this.jj_scanpos = var1;
                                                      if (this.jj_scan_token(386)) {
                                                         this.jj_scanpos = var1;
                                                         if (this.jj_scan_token(281)) {
                                                            this.jj_scanpos = var1;
                                                            if (this.jj_scan_token(282)) {
                                                               this.jj_scanpos = var1;
                                                               if (this.jj_scan_token(387)) {
                                                                  this.jj_scanpos = var1;
                                                                  if (this.jj_scan_token(105)) {
                                                                     this.jj_scanpos = var1;
                                                                     if (this.jj_scan_token(388)) {
                                                                        this.jj_scanpos = var1;
                                                                        if (this.jj_scan_token(362)) {
                                                                           this.jj_scanpos = var1;
                                                                           if (this.jj_scan_token(364)) {
                                                                              this.jj_scanpos = var1;
                                                                              if (this.jj_scan_token(283)) {
                                                                                 this.jj_scanpos = var1;
                                                                                 if (this.jj_scan_token(114)) {
                                                                                    this.jj_scanpos = var1;
                                                                                    if (this.jj_scan_token(284)) {
                                                                                       this.jj_scanpos = var1;
                                                                                       if (this.jj_scan_token(285)) {
                                                                                          this.jj_scanpos = var1;
                                                                                          if (this.jj_scan_token(286)) {
                                                                                             this.jj_scanpos = var1;
                                                                                             if (this.jj_scan_token(393)) {
                                                                                                this.jj_scanpos = var1;
                                                                                                if (this.jj_scan_token(394)) {
                                                                                                   this.jj_scanpos = var1;
                                                                                                   if (this.jj_scan_token(289)) {
                                                                                                      this.jj_scanpos = var1;
                                                                                                      if (this.jj_scan_token(365)) {
                                                                                                         this.jj_scanpos = var1;
                                                                                                         if (this.jj_scan_token(390)) {
                                                                                                            this.jj_scanpos = var1;
                                                                                                            if (this.jj_scan_token(391)) {
                                                                                                               this.jj_scanpos = var1;
                                                                                                               if (this.jj_scan_token(392)) {
                                                                                                                  this.jj_scanpos = var1;
                                                                                                                  if (this.jj_scan_token(395)) {
                                                                                                                     this.jj_scanpos = var1;
                                                                                                                     if (this.jj_scan_token(290)) {
                                                                                                                        this.jj_scanpos = var1;
                                                                                                                        if (this.jj_scan_token(396)) {
                                                                                                                           this.jj_scanpos = var1;
                                                                                                                           if (this.jj_scan_token(397)) {
                                                                                                                              this.jj_scanpos = var1;
                                                                                                                              if (this.jj_scan_token(398)) {
                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                 if (this.jj_scan_token(399)) {
                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                    if (this.jj_scan_token(291)) {
                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                       if (this.jj_scan_token(292)) {
                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                          if (this.jj_scan_token(293)) {
                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                             if (this.jj_scan_token(294)) {
                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                if (this.jj_scan_token(400)) {
                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                   if (this.jj_scan_token(295)) {
                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                      if (this.jj_scan_token(401)) {
                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                         if (this.jj_scan_token(297)) {
                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                            if (this.jj_scan_token(402)) {
                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                               if (this.jj_scan_token(299)) {
                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                  if (this.jj_scan_token(300)) {
                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                     if (this.jj_scan_token(403)) {
                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                        if (this.jj_scan_token(301)) {
                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                           if (this.jj_scan_token(302)) {
                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                              if (this.jj_scan_token(303)) {
                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                 if (this.jj_scan_token(404)) {
                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                    if (this.jj_scan_token(405)) {
                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                       if (this.jj_scan_token(304)) {
                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                          if (this.jj_scan_token(305)) {
                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                             if (this.jj_scan_token(306)) {
                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                if (this.jj_scan_token(369)) {
                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                   if (this.jj_scan_token(307)) {
                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                      if (this.jj_scan_token(308)) {
                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                         if (this.jj_scan_token(309)) {
                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                            if (this.jj_scan_token(310)) {
                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                               if (this.jj_scan_token(406)) {
                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                  if (this.jj_scan_token(407)) {
                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                     if (this.jj_scan_token(311)) {
                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                        if (this.jj_scan_token(408)) {
                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                           if (this.jj_scan_token(312)) {
                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                              if (this.jj_scan_token(313)) {
                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                 if (this.jj_scan_token(184)) {
                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                    if (this.jj_scan_token(314)) {
                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                       if (this.jj_scan_token(315)) {
                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                          if (this.jj_scan_token(316)) {
                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                             if (this.jj_scan_token(317)) {
                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                if (this.jj_scan_token(318)) {
                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                   if (this.jj_scan_token(409)) {
                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                      if (this.jj_scan_token(410)) {
                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                         if (this.jj_scan_token(319)) {
                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                            if (this.jj_scan_token(320)) {
                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                               if (this.jj_scan_token(321)) {
                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                  if (this.jj_scan_token(322)) {
                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                     if (this.jj_scan_token(413)) {
                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                        this.lookingAhead = true;
                                                                                                                                                                                                                                                                        this.jj_semLA = this.getToken(1).kind == 323 && !this.seeingOffsetClause();
                                                                                                                                                                                                                                                                        this.lookingAhead = false;
                                                                                                                                                                                                                                                                        if (!this.jj_semLA || this.jj_scan_token(323)) {
                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                           if (this.jj_scan_token(414)) {
                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                              if (this.jj_scan_token(415)) {
                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(412)) {
                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(372)) {
                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(324)) {
                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(418)) {
                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(325)) {
                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(326)) {
                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(419)) {
                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(420)) {
                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(421)) {
                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(422)) {
                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(327)) {
                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(424)) {
                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(328)) {
                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(423)) {
                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(425)) {
                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(329)) {
                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(426)) {
                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(427)) {
                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(428)) {
                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(330)) {
                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(373)) {
                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(331)) {
                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(332)) {
                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(374)) {
                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(429)) {
                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(430)) {
                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(334)) {
                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(333)) {
                                                                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(335)) {
                                                                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(432)) {
                                                                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(433)) {
                                                                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(336)) {
                                                                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(434)) {
                                                                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(435)) {
                                                                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(437)) {
                                                                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(436)) {
                                                                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(337)) {
                                                                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(338)) {
                                                                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(339)) {
                                                                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(340)) {
                                                                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(341)) {
                                                                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(342)) {
                                                                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(343)) {
                                                                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(344)) {
                                                                                                                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(345)) {
                                                                                                                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(438)) {
                                                                                                                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(439)) {
                                                                                                                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(346)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(347)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(431)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(440)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(348)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(441)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(238)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(349)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(350)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(351)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(352)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(353)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(442)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(354)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(443)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(250)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(355)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(444)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(356)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(445)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(357)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(258)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(260)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(417)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(358)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(446)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            return true;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                              }
                                                                                                                                                                                                                           }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                     }
                                                                                                                                                                                                                  }
                                                                                                                                                                                                               }
                                                                                                                                                                                                            }
                                                                                                                                                                                                         }
                                                                                                                                                                                                      }
                                                                                                                                                                                                   }
                                                                                                                                                                                                }
                                                                                                                                                                                             }
                                                                                                                                                                                          }
                                                                                                                                                                                       }
                                                                                                                                                                                    }
                                                                                                                                                                                 }
                                                                                                                                                                              }
                                                                                                                                                                           }
                                                                                                                                                                        }
                                                                                                                                                                     }
                                                                                                                                                                  }
                                                                                                                                                               }
                                                                                                                                                            }
                                                                                                                                                         }
                                                                                                                                                      }
                                                                                                                                                   }
                                                                                                                                                }
                                                                                                                                             }
                                                                                                                                          }
                                                                                                                                       }
                                                                                                                                    }
                                                                                                                                 }
                                                                                                                              }
                                                                                                                           }
                                                                                                                        }
                                                                                                                     }
                                                                                                                  }
                                                                                                               }
                                                                                                            }
                                                                                                         }
                                                                                                      }
                                                                                                   }
                                                                                                }
                                                                                             }
                                                                                          }
                                                                                       }
                                                                                    }
                                                                                 }
                                                                              }
                                                                           }
                                                                        }
                                                                     }
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_325() {
      return this.jj_scan_token(404);
   }

   private final boolean jj_3R_72() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_150()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_151()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_152()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_311() {
      return this.jj_scan_token(105);
   }

   private final boolean jj_3R_259() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_311()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_312()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_313()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_324() {
      return this.jj_3R_378();
   }

   private final boolean jj_3R_323() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(444)) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(403)) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_322() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(255)) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(179)) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_160() {
      return this.jj_3R_255();
   }

   private final boolean jj_3R_190() {
      return this.jj_scan_token(111);
   }

   private final boolean jj_3R_321() {
      return this.jj_scan_token(376);
   }

   private final boolean jj_3R_265() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_321()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_322()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_323()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_324()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_325()) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_297() {
      return this.jj_scan_token(254);
   }

   private final boolean jj_3R_189() {
      return this.jj_scan_token(108);
   }

   private final boolean jj_3R_184() {
      return this.jj_scan_token(86);
   }

   private final boolean jj_3R_188() {
      return this.jj_scan_token(110);
   }

   private final boolean jj_3R_362() {
      return this.jj_3R_387();
   }

   private final boolean jj_3R_183() {
      return this.jj_scan_token(86);
   }

   private final boolean jj_3R_187() {
      return this.jj_scan_token(108);
   }

   private final boolean jj_3R_410() {
      return this.jj_scan_token(224);
   }

   private final boolean jj_3R_252() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(88)) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(89)) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3_11() {
      return this.jj_3R_61();
   }

   private final boolean jj_3R_203() {
      return this.jj_3R_80();
   }

   private final boolean jj_3R_186() {
      return this.jj_scan_token(109);
   }

   private final boolean jj_3R_409() {
      return this.jj_3R_417();
   }

   private final boolean jj_3R_386() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_409()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_410()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_127() {
      return this.jj_scan_token(154);
   }

   private final boolean jj_3_86() {
      return this.jj_3R_119();
   }

   private final boolean jj_3_87() {
      return this.jj_3R_120();
   }

   private final boolean jj_3_85() {
      return this.jj_3R_118();
   }

   private final boolean jj_3R_90() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(1).kind == 108 && this.getToken(2).kind == 285;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_185()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_186()) {
            this.jj_scanpos = var1;
            this.lookingAhead = true;
            this.jj_semLA = this.getToken(1).kind == 108 && this.getToken(2).kind == 350;
            this.lookingAhead = false;
            if (!this.jj_semLA || this.jj_3R_187()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_188()) {
                  this.jj_scanpos = var1;
                  this.lookingAhead = true;
                  this.jj_semLA = this.getToken(1).kind == 108 && this.getToken(2).kind == 351;
                  this.lookingAhead = false;
                  if (!this.jj_semLA || this.jj_3R_189()) {
                     this.jj_scanpos = var1;
                     if (this.jj_3R_190()) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_185() {
      return this.jj_scan_token(108);
   }

   private final boolean jj_3R_98() {
      return this.jj_3R_203();
   }

   private final boolean jj_3R_406() {
      return this.jj_scan_token(492);
   }

   private final boolean jj_3R_89() {
      return this.jj_3R_64();
   }

   private final boolean jj_3_47() {
      return this.jj_3R_94();
   }

   private final boolean jj_3_55() {
      return this.jj_3R_98();
   }

   private final boolean jj_3R_48() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(1).kind == 154 && (this.getToken(2).kind == 442 && (this.getToken(3).kind == 458 && this.isPrivilegeKeywordExceptTrigger(this.getToken(4).kind) || this.getToken(3).kind == 195) || this.isPrivilegeKeywordExceptTrigger(this.getToken(2).kind));
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_126()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 154 && (this.getToken(2).kind == 442 && (this.getToken(3).kind == 458 && !this.isPrivilegeKeywordExceptTrigger(this.getToken(4).kind) || this.getToken(3).kind == 243) || !this.isPrivilegeKeywordExceptTrigger(this.getToken(2).kind));
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_127()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_126() {
      return this.jj_scan_token(154);
   }

   private final boolean jj_3R_149() {
      return this.jj_3R_252();
   }

   private final boolean jj_3R_182() {
      return this.jj_scan_token(192);
   }

   private final boolean jj_3R_181() {
      return this.jj_3R_268();
   }

   private final boolean jj_3R_295() {
      return this.jj_scan_token(166);
   }

   private final boolean jj_3R_405() {
      return this.jj_scan_token(487);
   }

   private final boolean jj_3R_380() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_405()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_406()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_148() {
      return this.jj_scan_token(261);
   }

   private final boolean jj_3R_88() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_180()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_181()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_182()) {
               this.jj_scanpos = var1;
               this.lookingAhead = true;
               this.jj_semLA = this.getToken(1).kind == 86 && this.getToken(2).kind == 358;
               this.lookingAhead = false;
               if (!this.jj_semLA || this.jj_3R_183()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_184()) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_180() {
      return this.jj_3R_267();
   }

   private final boolean jj_3R_70() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_148()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_149()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3_48() {
      return this.jj_3R_95();
   }

   private final boolean jj_3R_61() {
      return this.jj_3R_64();
   }

   private final boolean jj_3R_432() {
      return this.jj_scan_token(259);
   }

   private final boolean jj_3R_305() {
      return this.jj_3R_372();
   }

   private final boolean jj_3R_80() {
      return this.jj_3R_160();
   }

   private final boolean jj_3R_304() {
      return this.jj_3R_371();
   }

   private final boolean jj_3R_303() {
      return this.jj_3R_370();
   }

   private final boolean jj_3R_135() {
      return this.jj_scan_token(354);
   }

   private final boolean jj_3R_349() {
      return this.jj_scan_token(189);
   }

   private final boolean jj_3R_71() {
      return false;
   }

   private final boolean jj_3R_348() {
      return this.jj_scan_token(283);
   }

   private final boolean jj_3R_277() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_348()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_349()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_69() {
      return false;
   }

   private final boolean jj_3R_301() {
      return this.jj_3R_369();
   }

   private final boolean jj_3_23() {
      return this.jj_3R_73();
   }

   private final boolean jj_3R_392() {
      return this.jj_scan_token(351);
   }

   private final boolean jj_3R_302() {
      return this.jj_scan_token(360);
   }

   private final boolean jj_3_22() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(3).kind != 300;
      this.lookingAhead = false;
      if (this.jj_semLA && !this.jj_3R_71()) {
         return this.jj_3R_72();
      } else {
         return true;
      }
   }

   private final boolean jj_3_21() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind != 300;
      this.lookingAhead = false;
      if (this.jj_semLA && !this.jj_3R_69()) {
         return this.jj_3R_70();
      } else {
         return true;
      }
   }

   private final boolean jj_3R_294() {
      return this.jj_3R_362();
   }

   private final boolean jj_3R_213() {
      return this.jj_3R_277();
   }

   private final boolean jj_3R_391() {
      return this.jj_scan_token(350);
   }

   private final boolean jj_3R_111() {
      return this.jj_scan_token(189);
   }

   private final boolean jj_3R_110() {
      return this.jj_scan_token(310);
   }

   private final boolean jj_3_80() {
      return this.jj_3R_64();
   }

   private final boolean jj_3R_251() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3_21()) {
         this.jj_scanpos = var1;
         if (this.jj_3_22()) {
            this.jj_scanpos = var1;
            if (this.jj_3_23()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_301()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_302()) {
                     this.jj_scanpos = var1;
                     if (this.jj_3R_303()) {
                        this.jj_scanpos = var1;
                        if (this.jj_3R_304()) {
                           this.jj_scanpos = var1;
                           if (this.jj_3R_305()) {
                              return true;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_390() {
      return this.jj_scan_token(285);
   }

   private final boolean jj_3R_369() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_390()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_391()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_392()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_320() {
      return this.jj_scan_token(293);
   }

   private final boolean jj_3R_334() {
      return this.jj_3R_384();
   }

   private final boolean jj_3_72() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_110()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(2).kind == 310;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_111()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_109() {
      return this.jj_scan_token(189);
   }

   private final boolean jj_3R_108() {
      return this.jj_scan_token(308);
   }

   private final boolean jj_3R_319() {
      return this.jj_scan_token(311);
   }

   private final boolean jj_3R_333() {
      return this.jj_3R_383();
   }

   private final boolean jj_3R_247() {
      return this.jj_scan_token(108);
   }

   private final boolean jj_3R_138() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(222)) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 108 && (this.getToken(2).kind == 222 || this.getToken(2).kind == 436);
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_247()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3_71() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_108()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(2).kind == 308;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_109()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3_20() {
      return this.jj_3R_68();
   }

   private final boolean jj_3R_332() {
      return this.jj_3R_382();
   }

   private final boolean jj_3R_212() {
      return this.jj_scan_token(294);
   }

   private final boolean jj_3R_211() {
      return this.jj_scan_token(346);
   }

   private final boolean jj_3R_331() {
      return this.jj_3R_381();
   }

   private final boolean jj_3R_210() {
      return this.jj_scan_token(72);
   }

   private final boolean jj_3R_379() {
      return this.jj_3R_374();
   }

   private final boolean jj_3R_318() {
      return this.jj_scan_token(438);
   }

   private final boolean jj_3R_330() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_379()) {
         this.jj_scanpos = var1;
      }

      return this.jj_3R_380();
   }

   private final boolean jj_3R_267() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_330()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_331()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_332()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_333()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_334()) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3_75() {
      return this.jj_3R_114();
   }

   private final boolean jj_3R_107() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_210()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_211()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_212()) {
               this.jj_scanpos = var1;
               if (this.jj_3_71()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3_72()) {
                     this.jj_scanpos = var1;
                     if (this.jj_3R_213()) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_317() {
      return this.jj_scan_token(272);
   }

   private final boolean jj_3R_58() {
      return this.jj_3R_138();
   }

   private final boolean jj_3R_316() {
      return this.jj_scan_token(271);
   }

   private final boolean jj_3R_264() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_316()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_317()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_318()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_319()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_320()) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_114() {
      return this.jj_3R_67();
   }

   private final boolean jj_3R_82() {
      return this.jj_scan_token(451);
   }

   private final boolean jj_3R_81() {
      return this.jj_scan_token(133);
   }

   private final boolean jj_3_32() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_81()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 451 && this.getToken(2).kind != 303;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_82()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_216() {
      return this.jj_3R_64();
   }

   private final boolean jj_3_54() {
      return this.jj_3R_84();
   }

   private final boolean jj_3R_222() {
      return this.jj_3R_267();
   }

   private final boolean jj_3_10() {
      return this.jj_3R_60();
   }

   private final boolean jj_3R_113() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_216()) {
         this.jj_scanpos = var1;
      }

      return this.jj_3R_67();
   }

   private final boolean jj_3_77() {
      return this.jj_3R_90();
   }

   private final boolean jj_3R_147() {
      return this.jj_3R_68();
   }

   private final boolean jj_3R_67() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.commonDatatypeName(false);
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_146()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind != 292;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_147()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_146() {
      return this.jj_3R_251();
   }

   private final boolean jj_3R_368() {
      return this.jj_3R_389();
   }

   private final boolean jj_3_79() {
      return this.jj_3R_64();
   }

   private final boolean jj_3R_221() {
      return this.jj_3R_260();
   }

   private final boolean jj_3_31() {
      return this.jj_3R_80();
   }

   private final boolean jj_3_70() {
      return this.jj_3R_107();
   }

   private final boolean jj_3_74() {
      return this.jj_3R_113();
   }

   private final boolean jj_3R_290() {
      return this.jj_scan_token(91);
   }

   private final boolean jj_3R_220() {
      return this.jj_3R_260();
   }

   private final boolean jj_3R_270() {
      return this.jj_3R_61();
   }

   private final boolean jj_3R_219() {
      return this.jj_3R_280();
   }

   private final boolean jj_3R_218() {
      return this.jj_3R_279();
   }

   private final boolean jj_3R_289() {
      return this.jj_scan_token(252);
   }

   private final boolean jj_3R_115() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(2).kind == 222 || this.getToken(2).kind == 436;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_217()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_218()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_219()) {
               this.jj_scanpos = var1;
               this.lookingAhead = true;
               this.jj_semLA = this.getToken(1).kind == 285 || this.getToken(1).kind == 350 || this.getToken(1).kind == 351;
               this.lookingAhead = false;
               if (!this.jj_semLA || this.jj_3R_220()) {
                  this.jj_scanpos = var1;
                  this.lookingAhead = true;
                  this.jj_semLA = this.getToken(2).kind == 453 || this.getToken(4).kind == 453 && this.getToken(2).kind != 458;
                  this.lookingAhead = false;
                  if (!this.jj_semLA || this.jj_3R_221()) {
                     this.jj_scanpos = var1;
                     if (this.jj_3_77()) {
                        this.jj_scanpos = var1;
                        if (this.jj_3R_222()) {
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_217() {
      return this.jj_scan_token(108);
   }

   private final boolean jj_3R_315() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(65)) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(66)) {
            this.jj_scanpos = var1;
            if (this.jj_scan_token(67)) {
               this.jj_scanpos = var1;
               if (this.jj_scan_token(68)) {
                  this.jj_scanpos = var1;
                  if (this.jj_scan_token(69)) {
                     this.jj_scanpos = var1;
                     if (this.jj_scan_token(70)) {
                        this.jj_scanpos = var1;
                        if (this.jj_scan_token(71)) {
                           this.jj_scanpos = var1;
                           if (this.jj_scan_token(72)) {
                              this.jj_scanpos = var1;
                              if (this.jj_scan_token(73)) {
                                 this.jj_scanpos = var1;
                                 if (this.jj_scan_token(74)) {
                                    this.jj_scanpos = var1;
                                    if (this.jj_scan_token(75)) {
                                       this.jj_scanpos = var1;
                                       if (this.jj_scan_token(76)) {
                                          this.jj_scanpos = var1;
                                          if (this.jj_scan_token(77)) {
                                             this.jj_scanpos = var1;
                                             if (this.jj_scan_token(78)) {
                                                this.jj_scanpos = var1;
                                                if (this.jj_scan_token(79)) {
                                                   this.jj_scanpos = var1;
                                                   if (this.jj_scan_token(81)) {
                                                      this.jj_scanpos = var1;
                                                      if (this.jj_scan_token(82)) {
                                                         this.jj_scanpos = var1;
                                                         if (this.jj_scan_token(83)) {
                                                            this.jj_scanpos = var1;
                                                            if (this.jj_scan_token(84)) {
                                                               this.jj_scanpos = var1;
                                                               if (this.jj_scan_token(85)) {
                                                                  this.jj_scanpos = var1;
                                                                  if (this.jj_scan_token(86)) {
                                                                     this.jj_scanpos = var1;
                                                                     if (this.jj_scan_token(87)) {
                                                                        this.jj_scanpos = var1;
                                                                        if (this.jj_scan_token(88)) {
                                                                           this.jj_scanpos = var1;
                                                                           if (this.jj_scan_token(89)) {
                                                                              this.jj_scanpos = var1;
                                                                              if (this.jj_scan_token(91)) {
                                                                                 this.jj_scanpos = var1;
                                                                                 if (this.jj_scan_token(92)) {
                                                                                    this.jj_scanpos = var1;
                                                                                    if (this.jj_scan_token(94)) {
                                                                                       this.jj_scanpos = var1;
                                                                                       if (this.jj_scan_token(95)) {
                                                                                          this.jj_scanpos = var1;
                                                                                          if (this.jj_scan_token(96)) {
                                                                                             this.jj_scanpos = var1;
                                                                                             if (this.jj_scan_token(97)) {
                                                                                                this.jj_scanpos = var1;
                                                                                                if (this.jj_scan_token(98)) {
                                                                                                   this.jj_scanpos = var1;
                                                                                                   if (this.jj_scan_token(99)) {
                                                                                                      this.jj_scanpos = var1;
                                                                                                      if (this.jj_scan_token(100)) {
                                                                                                         this.jj_scanpos = var1;
                                                                                                         if (this.jj_scan_token(101)) {
                                                                                                            this.jj_scanpos = var1;
                                                                                                            if (this.jj_scan_token(102)) {
                                                                                                               this.jj_scanpos = var1;
                                                                                                               if (this.jj_scan_token(103)) {
                                                                                                                  this.jj_scanpos = var1;
                                                                                                                  if (this.jj_scan_token(104)) {
                                                                                                                     this.jj_scanpos = var1;
                                                                                                                     if (this.jj_scan_token(106)) {
                                                                                                                        this.jj_scanpos = var1;
                                                                                                                        if (this.jj_scan_token(107)) {
                                                                                                                           this.jj_scanpos = var1;
                                                                                                                           if (this.jj_scan_token(108)) {
                                                                                                                              this.jj_scanpos = var1;
                                                                                                                              if (this.jj_scan_token(109)) {
                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                 if (this.jj_scan_token(110)) {
                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                    if (this.jj_scan_token(111)) {
                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                       if (this.jj_scan_token(112)) {
                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                          if (this.jj_scan_token(113)) {
                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                             if (this.jj_scan_token(115)) {
                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                if (this.jj_scan_token(116)) {
                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                   if (this.jj_scan_token(117)) {
                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                      if (this.jj_scan_token(118)) {
                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                         if (this.jj_scan_token(119)) {
                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                            if (this.jj_scan_token(120)) {
                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                               if (this.jj_scan_token(121)) {
                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                  if (this.jj_scan_token(122)) {
                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                     if (this.jj_scan_token(123)) {
                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                        if (this.jj_scan_token(124)) {
                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                           if (this.jj_scan_token(125)) {
                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                              if (this.jj_scan_token(126)) {
                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                 if (this.jj_scan_token(127)) {
                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                    if (this.jj_scan_token(128)) {
                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                       if (this.jj_scan_token(129)) {
                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                          if (this.jj_scan_token(130)) {
                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                             if (this.jj_scan_token(131)) {
                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                if (this.jj_scan_token(132)) {
                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                   if (this.jj_scan_token(133)) {
                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                      if (this.jj_scan_token(134)) {
                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                         if (this.jj_scan_token(135)) {
                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                            if (this.jj_scan_token(136)) {
                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                               if (this.jj_scan_token(137)) {
                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                  if (this.jj_scan_token(138)) {
                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                     if (this.jj_scan_token(139)) {
                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                        if (this.jj_scan_token(140)) {
                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                           if (this.jj_scan_token(141)) {
                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                              if (this.jj_scan_token(142)) {
                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                 if (this.jj_scan_token(143)) {
                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                    if (this.jj_scan_token(144)) {
                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                       if (this.jj_scan_token(145)) {
                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                          if (this.jj_scan_token(146)) {
                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                             if (this.jj_scan_token(147)) {
                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                if (this.jj_scan_token(148)) {
                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                   if (this.jj_scan_token(149)) {
                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                      if (this.jj_scan_token(150)) {
                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                         if (this.jj_scan_token(366)) {
                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                            if (this.jj_scan_token(151)) {
                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                               if (this.jj_scan_token(152)) {
                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                  if (this.jj_scan_token(153)) {
                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                     if (this.jj_scan_token(154)) {
                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                        if (this.jj_scan_token(155)) {
                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                           if (this.jj_scan_token(156)) {
                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                              if (this.jj_scan_token(157)) {
                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(158)) {
                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(159)) {
                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(160)) {
                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(161)) {
                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(162)) {
                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(163)) {
                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(296)) {
                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(164)) {
                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(165)) {
                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(166)) {
                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(167)) {
                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(168)) {
                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(169)) {
                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(170)) {
                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(171)) {
                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(172)) {
                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(173)) {
                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(174)) {
                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(175)) {
                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(176)) {
                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(177)) {
                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(178)) {
                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(179)) {
                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(180)) {
                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(181)) {
                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(182)) {
                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(183)) {
                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(185)) {
                                                                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(186)) {
                                                                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(187)) {
                                                                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(411)) {
                                                                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(188)) {
                                                                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(189)) {
                                                                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(371)) {
                                                                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(190)) {
                                                                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(191)) {
                                                                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(192)) {
                                                                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(193)) {
                                                                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(194)) {
                                                                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(195)) {
                                                                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(196)) {
                                                                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(197)) {
                                                                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(198)) {
                                                                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(199)) {
                                                                                                                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(200)) {
                                                                                                                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(416)) {
                                                                                                                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(201)) {
                                                                                                                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(202)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(203)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(204)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(205)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(206)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(207)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(208)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(209)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(210)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(211)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(212)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(213)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(214)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(215)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(216)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(217)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(218)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(219)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(220)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(221)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(222)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(223)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(224)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(225)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(226)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(227)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(228)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(229)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(230)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(231)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(232)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(233)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(234)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(235)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(236)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(237)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(239)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(240)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(241)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(242)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(243)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(247)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(244)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(245)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(246)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(249)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(251)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(252)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(253)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(254)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(255)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(256)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(257)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(259)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(261)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(262)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(263)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(264)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(265)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(267)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(268)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     if (this.jj_scan_token(269)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        if (this.jj_scan_token(270)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           if (this.jj_scan_token(360)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              if (this.jj_scan_token(361)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 if (this.jj_scan_token(363)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    if (this.jj_scan_token(367)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       if (this.jj_scan_token(368)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          if (this.jj_scan_token(370)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             if (this.jj_scan_token(375)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if (this.jj_scan_token(248)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   if (this.jj_scan_token(376)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      if (this.jj_scan_token(377)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         if (this.jj_scan_token(379)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            if (this.jj_scan_token(381)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               if (this.jj_scan_token(378)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  this.jj_scanpos = var1;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  if (this.jj_scan_token(380)) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     return true;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                     }
                                                                                                                                                                                                                                                                  }
                                                                                                                                                                                                                                                               }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                         }
                                                                                                                                                                                                                                                      }
                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                             }
                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                              }
                                                                                                                                                                                                                           }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                     }
                                                                                                                                                                                                                  }
                                                                                                                                                                                                               }
                                                                                                                                                                                                            }
                                                                                                                                                                                                         }
                                                                                                                                                                                                      }
                                                                                                                                                                                                   }
                                                                                                                                                                                                }
                                                                                                                                                                                             }
                                                                                                                                                                                          }
                                                                                                                                                                                       }
                                                                                                                                                                                    }
                                                                                                                                                                                 }
                                                                                                                                                                              }
                                                                                                                                                                           }
                                                                                                                                                                        }
                                                                                                                                                                     }
                                                                                                                                                                  }
                                                                                                                                                               }
                                                                                                                                                            }
                                                                                                                                                         }
                                                                                                                                                      }
                                                                                                                                                   }
                                                                                                                                                }
                                                                                                                                             }
                                                                                                                                          }
                                                                                                                                       }
                                                                                                                                    }
                                                                                                                                 }
                                                                                                                              }
                                                                                                                           }
                                                                                                                        }
                                                                                                                     }
                                                                                                                  }
                                                                                                               }
                                                                                                            }
                                                                                                         }
                                                                                                      }
                                                                                                   }
                                                                                                }
                                                                                             }
                                                                                          }
                                                                                       }
                                                                                    }
                                                                                 }
                                                                              }
                                                                           }
                                                                        }
                                                                     }
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3_19() {
      return this.jj_3R_67();
   }

   private final boolean jj_3R_66() {
      return this.jj_3R_64();
   }

   private final boolean jj_3R_269() {
      return this.jj_3R_61();
   }

   private final boolean jj_3R_288() {
      return this.jj_scan_token(145);
   }

   private final boolean jj_3R_293() {
      return this.jj_scan_token(122);
   }

   private final boolean jj_3R_287() {
      return this.jj_scan_token(208);
   }

   private final boolean jj_3R_192() {
      return this.jj_3R_270();
   }

   private final boolean jj_3_76() {
      return this.jj_3R_115();
   }

   private final boolean jj_3R_350() {
      return this.jj_scan_token(160);
   }

   private final boolean jj_3R_292() {
      return this.jj_scan_token(486);
   }

   private final boolean jj_3R_278() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_350()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_351()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_352()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_91() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = !this.distinctUDAFollows();
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_191()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.distinctUDAFollows();
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_192()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_191() {
      return this.jj_3R_269();
   }

   private final boolean jj_3R_245() {
      return this.jj_3R_299();
   }

   private final boolean jj_3_18() {
      return this.jj_3R_66();
   }

   private final boolean jj_3_51() {
      return this.jj_3R_96();
   }

   private final boolean jj_3R_352() {
      return this.jj_scan_token(296);
   }

   private final boolean jj_3R_244() {
      return this.jj_3R_298();
   }

   private final boolean jj_3R_351() {
      return this.jj_scan_token(416);
   }

   private final boolean jj_3_69() {
      return this.jj_3R_64();
   }

   private final boolean jj_3R_286() {
      return this.jj_scan_token(100);
   }

   private final boolean jj_3R_232() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(1).kind == 100;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_286()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 208;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_287()) {
            this.jj_scanpos = var1;
            this.lookingAhead = true;
            this.jj_semLA = this.getToken(1).kind == 145;
            this.lookingAhead = false;
            if (!this.jj_semLA || this.jj_3R_288()) {
               this.jj_scanpos = var1;
               this.lookingAhead = true;
               this.jj_semLA = this.getToken(1).kind == 252;
               this.lookingAhead = false;
               if (!this.jj_semLA || this.jj_3R_289()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_290()) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_243() {
      return this.jj_3R_297();
   }

   private final boolean jj_3R_214() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_278()) {
         this.jj_scanpos = var1;
      }

      return false;
   }

   private final boolean jj_3R_415() {
      return this.jj_3R_420();
   }

   private final boolean jj_3R_347() {
      return this.jj_scan_token(453);
   }

   private final boolean jj_3R_242() {
      return this.jj_3R_296();
   }

   private final boolean jj_3_30() {
      return this.jj_3R_80();
   }

   private final boolean jj_3R_64() {
      return this.jj_3R_142();
   }

   private final boolean jj_3R_241() {
      return this.jj_3R_295();
   }

   private final boolean jj_3R_119() {
      return this.jj_3R_232();
   }

   private final boolean jj_3_84() {
      return this.jj_3R_117();
   }

   private final boolean jj_3R_215() {
      return this.jj_3R_64();
   }

   private final boolean jj_3R_401() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3_30()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_415()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_240() {
      return this.jj_3R_294();
   }

   private final boolean jj_3_91() {
      return this.jj_3R_122();
   }

   private final boolean jj_3R_134() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_239()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_240()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_241()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_242()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_243()) {
                     this.jj_scanpos = var1;
                     if (this.jj_3R_244()) {
                        this.jj_scanpos = var1;
                        if (this.jj_3R_245()) {
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_239() {
      return this.jj_3R_293();
   }

   private final boolean jj_3R_112() {
      if (this.jj_3R_214()) {
         return true;
      } else {
         Token var1 = this.jj_scanpos;
         if (this.jj_3R_215()) {
            this.jj_scanpos = var1;
         }

         return this.jj_3R_67();
      }
   }

   private final boolean jj_3R_329() {
      return this.jj_scan_token(380);
   }

   private final boolean jj_3R_250() {
      return this.jj_3R_292();
   }

   private final boolean jj_3R_359() {
      return this.jj_scan_token(445);
   }

   private final boolean jj_3R_328() {
      return this.jj_scan_token(378);
   }

   private final boolean jj_3R_376() {
      return this.jj_3R_401();
   }

   private final boolean jj_3R_358() {
      return this.jj_scan_token(388);
   }

   private final boolean jj_3R_357() {
      return this.jj_scan_token(430);
   }

   private final boolean jj_3R_327() {
      return this.jj_scan_token(381);
   }

   private final boolean jj_3R_106() {
      return this.jj_3R_61();
   }

   private final boolean jj_3R_356() {
      return this.jj_scan_token(429);
   }

   private final boolean jj_3R_361() {
      return this.jj_scan_token(190);
   }

   private final boolean jj_3R_326() {
      return this.jj_scan_token(379);
   }

   private final boolean jj_3R_266() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_326()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_327()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_328()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_329()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_285() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_361()) {
         this.jj_scanpos = var1;
      }

      return this.jj_scan_token(290);
   }

   private final boolean jj_3R_228() {
      return this.jj_scan_token(213);
   }

   private final boolean jj_3_73() {
      return this.jj_3R_112();
   }

   private final boolean jj_3R_249() {
      return this.jj_scan_token(480);
   }

   private final boolean jj_3R_142() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_249()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_250()) {
            this.jj_scanpos = var1;
            if (this.jj_3_91()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_310() {
      return this.jj_3R_376();
   }

   private final boolean jj_3R_281() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_356()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_357()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_358()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_359()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_145() {
      return this.jj_scan_token(195);
   }

   private final boolean jj_3R_227() {
      return this.jj_scan_token(213);
   }

   private final boolean jj_3R_144() {
      return this.jj_scan_token(195);
   }

   private final boolean jj_3R_143() {
      return this.jj_scan_token(190);
   }

   private final boolean jj_3R_226() {
      return this.jj_scan_token(394);
   }

   private final boolean jj_3R_360() {
      return this.jj_scan_token(190);
   }

   private final boolean jj_3R_282() {
      return this.jj_scan_token(328);
   }

   private final boolean jj_3R_225() {
      return this.jj_scan_token(113);
   }

   private final boolean jj_3R_65() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_143()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 195 && this.getToken(2).kind == 97;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_144()) {
            this.jj_scanpos = var1;
            this.lookingAhead = true;
            this.jj_semLA = this.getToken(1).kind == 195 && this.getToken(2).kind == 220;
            this.lookingAhead = false;
            if (!this.jj_semLA || this.jj_3R_145()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_284() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_360()) {
         this.jj_scanpos = var1;
      }

      return this.jj_scan_token(120);
   }

   private final boolean jj_3R_224() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_282()) {
         this.jj_scanpos = var1;
         if (this.jj_scan_token(336)) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_223() {
      return this.jj_3R_281();
   }

   private final boolean jj_3_9() {
      return this.jj_3R_59();
   }

   private final boolean jj_3R_194() {
      return this.jj_3R_272();
   }

   private final boolean jj_3R_258() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(190)) {
         this.jj_scanpos = var1;
      }

      return this.jj_3R_310();
   }

   private final boolean jj_3R_389() {
      return this.jj_3R_414();
   }

   private final boolean jj_3_78() {
      return this.jj_3R_116();
   }

   private final boolean jj_3R_116() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_223()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_224()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_225()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_226()) {
                  this.jj_scanpos = var1;
                  this.lookingAhead = true;
                  this.jj_semLA = this.getToken(1).kind == 213 && this.getToken(2).kind == 280;
                  this.lookingAhead = false;
                  if (!this.jj_semLA || this.jj_3R_227()) {
                     this.jj_scanpos = var1;
                     this.lookingAhead = true;
                     this.jj_semLA = this.getToken(1).kind == 213 && this.getToken(2).kind == 356;
                     this.lookingAhead = false;
                     if (!this.jj_semLA || this.jj_3R_228()) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3_46() {
      return this.jj_3R_93();
   }

   private final boolean jj_3_17() {
      return this.jj_3R_65();
   }

   private final boolean jj_3R_207() {
      return this.jj_scan_token(239);
   }

   private final boolean jj_3_37() {
      return this.jj_3R_87();
   }

   private final boolean jj_3R_92() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_193()) {
         this.jj_scanpos = var1;
         if (this.jj_3_46()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_194()) {
               return true;
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_193() {
      return this.jj_3R_271();
   }

   private final boolean jj_3R_283() {
      return this.jj_scan_token(162);
   }

   private final boolean jj_3R_59() {
      return this.jj_3R_61();
   }

   private final boolean jj_3R_133() {
      return this.jj_scan_token(118);
   }

   private final boolean jj_3_68() {
      return this.jj_3R_106();
   }

   private final boolean jj_3R_414() {
      return this.jj_3R_172();
   }

   private final boolean jj_3R_162() {
      return this.jj_3R_258();
   }

   private final boolean jj_3R_427() {
      return this.jj_scan_token(183);
   }

   private final boolean jj_3R_274() {
      return this.jj_scan_token(191);
   }

   private final boolean jj_3_8() {
      return this.jj_3R_57();
   }

   private final boolean jj_3_7() {
      return this.jj_3R_58();
   }

   private final boolean jj_3R_276() {
      return this.jj_scan_token(453);
   }

   private final boolean jj_3R_426() {
      return this.jj_scan_token(157);
   }

   private final boolean jj_3R_179() {
      return this.jj_3R_266();
   }

   private final boolean jj_3R_68() {
      return this.jj_3R_61();
   }

   private final boolean jj_3R_231() {
      return this.jj_3R_285();
   }

   private final boolean jj_3R_425() {
      return this.jj_scan_token(286);
   }

   private final boolean jj_3R_246() {
      return this.jj_scan_token(108);
   }

   private final boolean jj_3R_125() {
      return this.jj_scan_token(227);
   }

   private final boolean jj_3R_137() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(172)) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 108 && this.getToken(2).kind == 172;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_246()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_424() {
      return this.jj_scan_token(314);
   }

   private final boolean jj_3_6() {
      return this.jj_3R_58();
   }

   private final boolean jj_3R_84() {
      return this.jj_3R_162();
   }

   private final boolean jj_3_5() {
      return this.jj_3R_57();
   }

   private final boolean jj_3R_178() {
      return this.jj_scan_token(301);
   }

   private final boolean jj_3R_423() {
      return this.jj_scan_token(270);
   }

   private final boolean jj_3R_417() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_423()) {
         this.jj_scanpos = var1;
         if (this.jj_3R_424()) {
            this.jj_scanpos = var1;
            if (this.jj_3R_425()) {
               this.jj_scanpos = var1;
               if (this.jj_3R_426()) {
                  this.jj_scanpos = var1;
                  if (this.jj_3R_427()) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private final boolean jj_3R_57() {
      return this.jj_3R_137();
   }

   private final boolean jj_3R_47() {
      Token var1 = this.jj_scanpos;
      this.lookingAhead = true;
      this.jj_semLA = this.getToken(1).kind == 227 && this.getToken(2).kind != 108;
      this.lookingAhead = false;
      if (!this.jj_semLA || this.jj_3R_124()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 227 && this.getToken(2).kind == 108;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_125()) {
            return true;
         }
      }

      return false;
   }

   private final boolean jj_3R_124() {
      return this.jj_scan_token(227);
   }

   private final boolean jj_3_53() {
      return this.jj_3R_84();
   }

   private final boolean jj_3R_230() {
      return this.jj_3R_284();
   }

   private final boolean jj_3_16() {
      return this.jj_3R_64();
   }

   private final boolean jj_3R_177() {
      return this.jj_scan_token(258);
   }

   private final boolean jj_3R_176() {
      return this.jj_scan_token(93);
   }

   private final boolean jj_3R_79() {
      Token var1 = this.jj_scanpos;
      if (this.jj_scan_token(72)) {
         this.jj_scanpos = var1;
      }

      return this.jj_3R_64();
   }

   private final boolean jj_3R_206() {
      return this.jj_scan_token(409);
   }

   private final boolean jj_3R_275() {
      return this.jj_3R_347();
   }

   private final boolean jj_3_45() {
      return this.jj_3R_92();
   }

   private final boolean jj_3R_136() {
      return this.jj_scan_token(137);
   }

   private final boolean jj_3R_132() {
      return this.jj_scan_token(68);
   }

   private final boolean jj_3R_229() {
      return this.jj_3R_283();
   }

   private final boolean jj_3R_86() {
      return this.jj_3R_172();
   }

   private final boolean jj_3R_175() {
      return this.jj_3R_265();
   }

   private final boolean jj_3_29() {
      return this.jj_3R_79();
   }

   private final boolean jj_3R_117() {
      Token var1 = this.jj_scanpos;
      if (this.jj_3R_229()) {
         this.jj_scanpos = var1;
         this.lookingAhead = true;
         this.jj_semLA = this.getToken(1).kind == 120 || this.getToken(2).kind == 120;
         this.lookingAhead = false;
         if (!this.jj_semLA || this.jj_3R_230()) {
            this.jj_scanpos = var1;
            this.lookingAhead = true;
            this.jj_semLA = this.getToken(1).kind == 290 || this.getToken(2).kind == 290;
            this.lookingAhead = false;
            if (!this.jj_semLA || this.jj_3R_231()) {
               return true;
            }
         }
      }

      return false;
   }

   private static void jj_la1_0() {
      jj_la1_0 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   }

   private static void jj_la1_1() {
      jj_la1_1 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 134217728, 134217728, 0, 0, 0, 134217728, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   }

   private static void jj_la1_2() {
      jj_la1_2 = new int[]{0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 256, 0, 0, 0, 0, 0, 0, 0, 0, 256, 0, 0, 0, 0, 0, 32, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 134217728, 134217728, 134217728, 0, 134217728, 134217728, 50397184, 0, 0, 50331648, 0, 50331648, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50397184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 4, 4, 0, 0, 0, 0, 0, 256, 0, 32, 0, 0, 32768, 0, 0, 32768, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 262144, 0, 0, 8388608, 0, 536870912, 0, 0, 16777216, 0, 0, 256, 0, 524288, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 512, 512, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 68, 64, 0, 0, 0, 0, 0, 0, 0, 0, 256, 0, 0, 0, 0, 256, 0, 0, 256, 0, 0, 0, 0, 0, 512, 512, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8192, 0, 0, 8192, 16777216, 0, 0, 0, 0, 0, 0, 4096, 4096, 0, 0, 256, 0, 0, 0, 0, 0, 256, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524288, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4194304, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 1048576, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 4, 0, 0, 0, 0, 134217728, 0, 2, 16, 0, 1048576, 1048576, 0, 0, 0, 0, 0, 0, 524288, 134217728, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, -604045314, 536936448, 0, -604045314, -604045314, 0};
   }

   private static void jj_la1_3() {
      jj_la1_3 = new int[]{67108864, 0, 71304192, 0, 0, 0, 0, 0, 0, 32, 0, 0, 33554432, 67108864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4096, 0, 0, 0, 0, 67108864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 16, 8388624, 16, 16, 8388608, 16, 8388624, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3145728, 3145728, 0, 0, 3145728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 0, 0, Integer.MIN_VALUE, Integer.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 65536, 0, 0, 134217728, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8388608, 0, 0, 0, 8388608, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 134217728, 2048, 0, 0, 0, 0, 0, 0, 0, 8192, 16384, 32768, 0, 512, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 0, 0, 0, 0, 0, 0, 8388608, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 0, 0, 262144, 1, 0, 0, 0, 0, 0, 0, 131072, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 8388608, 0, 0, 0, 33554432, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 67108864, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 0, 67108864, 0, -262657, 262656, 0, -262657, -262657, 0};
   }

   private static void jj_la1_4() {
      jj_la1_4 = new int[]{0, 0, 2, 0, 512, 0, 0, 2097152, 2097152, 0, 0, 0, Integer.MIN_VALUE, 0, 0, 0, 0, 0, 524288, 0, 65536, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131072, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32768, 1, 65536, 0, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1024, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870912, 0, 0, 0, 0, 0, 0, 536870912, 0, 0, 0, 0, 0, 0, 524288, 0, 0, 0, 0, 0, 0, 0, 536870912, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870912, 0, 4096, 0, 0, 0, 0, 0, 0, 0, 0, 8192, 0, 0, 0, 0, 0, 16384, 0, 0, 0, 0, 8192, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 268435456, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4096, 0, 4096, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4096, 0, 0, 4, 0, 4, 0, 0, 0, 131072, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 131072, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2097152, 512, 0, 0, 0, 0, 2097152, 0, 0, 0, 0, 0, 0, 0, 0, 0, 512, 0, -1, 0, 0, -1, -1, 0};
   }

   private static void jj_la1_5() {
      jj_la1_5 = new int[]{64, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1073741824, 0, 0, 0, 1073741824, 1073741824, 1073741824, 0, 1073741824, 1073741824, 33554432, 0, 0, 0, 0, 0, 0, 0, 0, 0, 167772160, 0, 0, 0, 0, 33554432, 384, 0, 0, 0, 384, 384, 384, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 512, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1073741824, 1074006017, 1073741824, 0, 264193, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MIN_VALUE, 8388608, 0, 0, 0, 0, 0, 0, 8388608, 0, 524288, 0, 0, 524288, 0, 0, 65536, 0, 0, 268435456, 524288, 0, 0, 8388608, 0, 0, 0, 0, 0, 0, 0, Integer.MIN_VALUE, 384, 8388608, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 268435456, 0, 0, 0, 0, 0, 0, 0, Integer.MIN_VALUE, 0, 0, 0, Integer.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67248136, 131080, 131080, 0, 131080, 131072, 0, 0, 0, 0, 0, 0, 6291456, 0, 0, 6291456, 0, Integer.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870912, 536870912, 0, 0, 536870912, 0, 0, 0, 0, 1610612736, 0, 0, 1610612736, 0, 0, 0, 0, 0, 1, 1, 1610612736, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870912, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870912, 0, 536870912, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4096, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870912, Integer.MIN_VALUE, 536870912, 4, 4, 0, 1073741824, 1073741824, 0, 0, 0, 1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Integer.MIN_VALUE, 1073741824, 0, 536870912, 0, 0, 0, 64, 0, 0, 64, 0, 0, 0, 0, 0, 0, 0, 64, 0, 0, 0, 64, 0, -16777217, 16777216, 0, -16777217, -16777217, 0};
   }

   private static void jj_la1_6() {
      jj_la1_6 = new int[]{0, 0, 268435456, 0, 0, 0, 1073741824, 1074266112, 1074266112, 0, 0, 0, 0, 268435456, 0, 0, 0, 0, 0, 256, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 268435456, 8, 0, 0, 32768, 0, 65536, 8454144, 8454144, 8454144, 0, 8454144, 8454144, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 0, 0, 0, 4194304, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 256, 0, 0, 0, 0, 0, 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1073741824, 0, 0, 0, 0, 0, 0, 0, 32768, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870912, 0, 0, 0, 536870912, 4, 2097152, 0, 0, 0, 0, 256, 0, 0, 0, 256, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 134217728, 134217728, 512, 134217728, 134217728, 8, 0, 0, 0, 0, 0, 0, 256, 0, 0, 0, 0, 0, 0, 256, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 256, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2097152, 0, 0, 0, 0, 1073741824, 1073741824, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 65536, 8, 0, 8, 8, 0, 33554432, 0, 33554432, 0, 0, 0, 0, 0, 0, 0, 0, 8454144, 0, 0, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 0, 33554432, 33554432, 0, 0, 0, 0, 0, 0, 0, 0, 524288, 8388608, 0, 0, 8388608, 0, 524288, 0, 0, 0, 0, 0, 8388608, 0, 1048576, 0, 8388608, 0, -1, 0, 0, -1, -1, 0};
   }

   private static void jj_la1_7() {
      jj_la1_7 = new int[]{1073741826, 0, 1073741826, 0, 0, 0, 0, 268468224, 32768, 0, 0, 0, 0, 1073741826, 0, 0, 0, 0, 0, 0, 0, 0, 32768, 0, 0, 0, 0, 0, 0, 0, 0, 1073741824, 0, 0, 268435456, 0, 524288, 0, 268435456, 0, 0, 0, 0, 268435456, 268435456, 268435456, 268435456, 0, 268435456, 268435456, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 16, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2052, 0, 0, 0, 0, 1, 0, Integer.MIN_VALUE, 0, 0, -2130706432, 16777216, 0, 8388608, 0, 0, 0, -2130706432, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 16, 1, 0, 33554432, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32768, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4096, 0, 0, 4096, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32768, 268435456, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32768, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1073741824, 0, 0, 32768, 32768, 32768, 32768, 0, 0, 0, 0, 0, 0, 0, 0, 4, 33554432, 0, 33554432, 0, 0, 67125248, 32768, 0, 524288, 524288, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33554436, 0, 0, 0, 0, 0, 0, 0, 0, 268435456, 268435456, 0, 0, 0, 0, 1073741824, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 268435456, 0, 1073741824, 1073741824, 0, 0, 0, 0, 268435456, 0, 0, 0, 0, 0, 0, 0, 8, 0, 8, 0, 0, 0, 0, 0, 1073741826, 32768, 0, 1073741826, 0, 0, 0, 0, 0, 0, 0, 1073741826, 0, 0, 0, 1073741826, 0, -67125249, 0, 67125248, -67125249, -67125249, 0};
   }

   private static void jj_la1_8() {
      jj_la1_8 = new int[]{8, 0, 8, 0, 0, 0, 128, 128, 128, 0, 0, 0, 0, 8, 512, 0, 0, 512, 0, 0, 0, 2048, 0, 512, 0, 0, 0, 512, 0, 0, 0, 0, 0, 0, 0, 4096, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 0, 0, 2048, 0, 2048, 541589504, 0, 64, 32, 0, 0, 0, 64, 0, 64, 0, 0, 0, 0, 0, 4718592, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 536870912, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1073758208, 0, 33554433, 0, 0, 98304, 0, 1073758208, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 98304, 4, 0, 1610629120, 32, 0, 0, 0, 0, 0, 0, 0, 0, 1610629120, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 512, 0, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 134217728, 0, 0, 0, 0, 0, 0, 0, 69206016, 0, 0, 69206016, 2097152, Integer.MIN_VALUE, 0, 0, 0, 0, 0, 69206016, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 2048, 262144, 134217728, 0, 134217728, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16777216, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 262144, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 31723, 2147450880, 20, 31723, 31723, 0};
   }

   private static void jj_la1_9() {
      jj_la1_9 = new int[]{2097152, 0, 2097152, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2097152, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 0, 16, 0, 0, 16, 0, 16, 1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 0, 0, 8388640, 0, 67108864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8388640, 8192, 0, 67108864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1048576, 4194304, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16779267, 2, 536870912, 16779267, 0, 1024, 0, 0, 0, 256, 256, 16779267, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 64, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131072, 0, 0, 0, 0, 0, 0, 0, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 256, -1282, 0, 256, 256, 0};
   }

   private static void jj_la1_10() {
      jj_la1_10 = new int[]{0, 0, 8320, 0, 0, 0, 268435456, 268435456, 268435456, 0, 0, 0, 0, 8320, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8320, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 66977792, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 1, 8, 0, 0, 0, 8, 0, 4096, 0, 0, 0, 4096, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 0, 0, 0, 0, 4096, 0, 0, 1024, 0, 32768, 1024, 1024, 0, 0, 0, 0, 0, 0, 1024, 0, 0, 0, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134221824, 0, 4096, 4096, 4096, 4096, 0, 0, 0, 0, 67108864, 0, 67108864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65792, 65792, 0, 65792, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 512, 0, 0, 0, 268435456, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, -16, 0, 0, 0};
   }

   private static void jj_la1_11() {
      jj_la1_11 = new int[]{512, 0, 512, 4, 0, 0, 2097152, 2097160, 2097160, 2097152, 0, 0, 0, 512, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 512, 512, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33685760, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 65536, 0, 65536, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5123, 3, 0, 0, 0, 0, 0, 0, 0, 0, 25427968, 8650752, 0, 0, 8650752, 0, 0, 25444352, 1006632960, 0, 0, 0, 1006632960, 0, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4194304, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 64, 1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 524288, 0, 0, 0, 0, 0, 2048, 0, 64, 0, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 32, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 1066257152, -1073597440, 7340159, 1066257152, 1066257152, 0};
   }

   private static void jj_la1_12() {
      jj_la1_12 = new int[]{0, 2097152, 0, 0, 0, 2097152, 0, 65664, 65664, 4194304, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524288, 0, 1572864, 0, 0, 0, 0, 0, 0, 1572864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 262528, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16777216, 0, 0, 0, 0, 0, -973078528, 0, 0, 0, 0, -973078528, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 16384, 0, 0, 0, 0, 0, 1040, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 131072, 0, 4, 0, 512, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 128, 0, 0, 0, 0, 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217728, 671088607, -805306368, 134217728, 134217728, 0};
   }

   private static void jj_la1_13() {
      jj_la1_13 = new int[]{0, 256, 0, 0, 0, 256, 67174400, 67174400, 67174400, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4194304, 0, 0, 0, 0, 268435456, 0, 268435456, 0, 0, 0, 0, 1048576, 0, 272629760, 0, 0, 0, 0, 0, 16777216, 0, 0, 0, 4096, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2098210, 0, 0, 2098210, 0, 0, 0, 0, 0, 1, 1, 2098210, 0, 0, 0, 0, 0, 0, 0, 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1048576, 0, 0, 0, 0, 0, 0, 0, 0, 524288, 0, 0, 0, 512, 0, 536895488, 536895488, 0, 0, 0, 0, 0, 1048576, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32768, 16, 0, 134217728, 131072, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 67108864, 65536, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 0, 67108864, 0, 1, 0, 2147483646, 1, 1, 0};
   }

   private static void jj_la1_14() {
      jj_la1_14 = new int[]{16777256, 0, 16777256, 0, 0, 0, 0, 0, 0, 0, 1024, 0, 0, 16777256, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16777224, 16777216, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 32, 0, 32, 0, 0, 32, 32, 32, 32, 0, 0, 0, 1024, 32, 0, 0, 0, 32, 0, 0, 0, 0, 4096, 0, 0, 2560, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 128, 1024, 0, 0, 0, 0, 0, 16646144, 0, 0, 0, 16646144, 2560, 2560, 536879232, 536879232, 2560, 1073745920, 4096, 0, 0, 1024, 0, 0, 0, 0, 1024, 0, 1024, 0, 0, 1024, 0, 0, 64, 0, 0, 0, 32, 0, 0, 1024, 0, 0, 0, 0, 0, 1024, 0, 0, 0, 0, 0, 1088, 0, 16777216, 0, 1024, 1024, 0, 0, 0, 0, 0, 16779776, 16779776, 0, 16779776, 0, 0, 16779776, 16779776, 0, 0, 0, 1024, 1024, 0, 0, 0, 1024, 0, 1024, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 1024, 8, 0, 32, 0, 0, 0, 0, 32, 0, 0, 32, 0, 32, 1024, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1024, 0, 0, 0, 128, 0, 0, 32, 0, 0, 0, 1024, 1024, 0, 384, 32, 0, 0, 0, 0, 0, 0, 0, 0, 32, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1024, Integer.MIN_VALUE, 0, 0, 0, 1024, Integer.MIN_VALUE, 0, 1024, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1024, 32, 1024, 0, 0, 2568, 2560, 2568, 2560, 0, 0, 0, 0, 524288, 524288, 0, 0, 0, 0, 0, 0, 0, 0, 16777216, 524288, 0, 0, 16777216, 16779784, 0, 0, 0, 0, 0, 1024, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1024, 0, 0, 0, 32, 1024, 32, 32, 32, 0, 1024, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 0};
   }

   private static void jj_la1_15() {
      jj_la1_15 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 640, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 128, 128, 0, 128, 0, 0, 128, 128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7296, 0, 7296, 0, 4224, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1024, 0, 0, 0, 1024, 7296, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65, 0, 0, 0, 0, 0, 65};
   }

   public SQLParser(CharStream var1) {
      this.token_source = new SQLParserTokenManager(var1);
      this.token = new Token();
      this.token.next = this.jj_nt = this.token_source.getNextToken();
      this.jj_gen = 0;

      for(int var2 = 0; var2 < 381; ++var2) {
         this.jj_la1[var2] = -1;
      }

      for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
         this.jj_2_rtns[var3] = new JJCalls();
      }

   }

   public void ReInit(CharStream var1) {
      this.token_source.ReInit(var1);
      this.token = new Token();
      this.token.next = this.jj_nt = this.token_source.getNextToken();
      this.jj_gen = 0;

      for(int var2 = 0; var2 < 381; ++var2) {
         this.jj_la1[var2] = -1;
      }

      for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
         this.jj_2_rtns[var3] = new JJCalls();
      }

   }

   public SQLParser(SQLParserTokenManager var1) {
      this.token_source = var1;
      this.token = new Token();
      this.token.next = this.jj_nt = this.token_source.getNextToken();
      this.jj_gen = 0;

      for(int var2 = 0; var2 < 381; ++var2) {
         this.jj_la1[var2] = -1;
      }

      for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
         this.jj_2_rtns[var3] = new JJCalls();
      }

   }

   public void ReInit(SQLParserTokenManager var1) {
      this.token_source = var1;
      this.token = new Token();
      this.token.next = this.jj_nt = this.token_source.getNextToken();
      this.jj_gen = 0;

      for(int var2 = 0; var2 < 381; ++var2) {
         this.jj_la1[var2] = -1;
      }

      for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
         this.jj_2_rtns[var3] = new JJCalls();
      }

   }

   private final Token jj_consume_token(int var1) throws ParseException {
      Token var2 = this.token;
      if ((this.token = this.jj_nt).next != null) {
         this.jj_nt = this.jj_nt.next;
      } else {
         this.jj_nt = this.jj_nt.next = this.token_source.getNextToken();
      }

      if (this.token.kind != var1) {
         this.jj_nt = this.token;
         this.token = var2;
         this.jj_kind = var1;
         throw this.generateParseException();
      } else {
         ++this.jj_gen;
         if (++this.jj_gc > 100) {
            this.jj_gc = 0;

            for(int var3 = 0; var3 < this.jj_2_rtns.length; ++var3) {
               for(JJCalls var4 = this.jj_2_rtns[var3]; var4 != null; var4 = var4.next) {
                  if (var4.gen < this.jj_gen) {
                     var4.first = null;
                  }
               }
            }
         }

         return this.token;
      }
   }

   private final boolean jj_scan_token(int var1) {
      if (this.jj_scanpos == this.jj_lastpos) {
         --this.jj_la;
         if (this.jj_scanpos.next == null) {
            this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next = this.token_source.getNextToken();
         } else {
            this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next;
         }
      } else {
         this.jj_scanpos = this.jj_scanpos.next;
      }

      if (this.jj_rescan) {
         int var2 = 0;

         Token var3;
         for(var3 = this.token; var3 != null && var3 != this.jj_scanpos; var3 = var3.next) {
            ++var2;
         }

         if (var3 != null) {
            this.jj_add_error_token(var1, var2);
         }
      }

      if (this.jj_scanpos.kind != var1) {
         return true;
      } else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) {
         throw this.jj_ls;
      } else {
         return false;
      }
   }

   public final Token getNextToken() {
      if ((this.token = this.jj_nt).next != null) {
         this.jj_nt = this.jj_nt.next;
      } else {
         this.jj_nt = this.jj_nt.next = this.token_source.getNextToken();
      }

      ++this.jj_gen;
      return this.token;
   }

   public final Token getToken(int var1) {
      Token var2 = this.lookingAhead ? this.jj_scanpos : this.token;

      for(int var3 = 0; var3 < var1; ++var3) {
         if (var2.next != null) {
            var2 = var2.next;
         } else {
            var2 = var2.next = this.token_source.getNextToken();
         }
      }

      return var2;
   }

   private void jj_add_error_token(int var1, int var2) {
      if (var2 < 100) {
         if (var2 == this.jj_endpos + 1) {
            this.jj_lasttokens[this.jj_endpos++] = var1;
         } else if (this.jj_endpos != 0) {
            this.jj_expentry = new int[this.jj_endpos];

            for(int var3 = 0; var3 < this.jj_endpos; ++var3) {
               this.jj_expentry[var3] = this.jj_lasttokens[var3];
            }

            boolean var7 = false;
            Enumeration var4 = this.jj_expentries.elements();

            while(var4.hasMoreElements()) {
               int[] var5 = (int[])var4.nextElement();
               if (var5.length == this.jj_expentry.length) {
                  var7 = true;

                  for(int var6 = 0; var6 < this.jj_expentry.length; ++var6) {
                     if (var5[var6] != this.jj_expentry[var6]) {
                        var7 = false;
                        break;
                     }
                  }

                  if (var7) {
                     break;
                  }
               }
            }

            if (!var7) {
               this.jj_expentries.addElement(this.jj_expentry);
            }

            if (var2 != 0) {
               this.jj_lasttokens[(this.jj_endpos = var2) - 1] = var1;
            }
         }

      }
   }

   public ParseException generateParseException() {
      this.jj_expentries.removeAllElements();
      boolean[] var1 = new boolean[505];

      for(int var2 = 0; var2 < 505; ++var2) {
         var1[var2] = false;
      }

      if (this.jj_kind >= 0) {
         var1[this.jj_kind] = true;
         this.jj_kind = -1;
      }

      for(int var4 = 0; var4 < 381; ++var4) {
         if (this.jj_la1[var4] == this.jj_gen) {
            for(int var3 = 0; var3 < 32; ++var3) {
               if ((jj_la1_0[var4] & 1 << var3) != 0) {
                  var1[var3] = true;
               }

               if ((jj_la1_1[var4] & 1 << var3) != 0) {
                  var1[32 + var3] = true;
               }

               if ((jj_la1_2[var4] & 1 << var3) != 0) {
                  var1[64 + var3] = true;
               }

               if ((jj_la1_3[var4] & 1 << var3) != 0) {
                  var1[96 + var3] = true;
               }

               if ((jj_la1_4[var4] & 1 << var3) != 0) {
                  var1[128 + var3] = true;
               }

               if ((jj_la1_5[var4] & 1 << var3) != 0) {
                  var1[160 + var3] = true;
               }

               if ((jj_la1_6[var4] & 1 << var3) != 0) {
                  var1[192 + var3] = true;
               }

               if ((jj_la1_7[var4] & 1 << var3) != 0) {
                  var1[224 + var3] = true;
               }

               if ((jj_la1_8[var4] & 1 << var3) != 0) {
                  var1[256 + var3] = true;
               }

               if ((jj_la1_9[var4] & 1 << var3) != 0) {
                  var1[288 + var3] = true;
               }

               if ((jj_la1_10[var4] & 1 << var3) != 0) {
                  var1[320 + var3] = true;
               }

               if ((jj_la1_11[var4] & 1 << var3) != 0) {
                  var1[352 + var3] = true;
               }

               if ((jj_la1_12[var4] & 1 << var3) != 0) {
                  var1[384 + var3] = true;
               }

               if ((jj_la1_13[var4] & 1 << var3) != 0) {
                  var1[416 + var3] = true;
               }

               if ((jj_la1_14[var4] & 1 << var3) != 0) {
                  var1[448 + var3] = true;
               }

               if ((jj_la1_15[var4] & 1 << var3) != 0) {
                  var1[480 + var3] = true;
               }
            }
         }
      }

      for(int var5 = 0; var5 < 505; ++var5) {
         if (var1[var5]) {
            this.jj_expentry = new int[1];
            this.jj_expentry[0] = var5;
            this.jj_expentries.addElement(this.jj_expentry);
         }
      }

      this.jj_endpos = 0;
      this.jj_rescan_token();
      this.jj_add_error_token(0, 0);
      int[][] var6 = new int[this.jj_expentries.size()][];

      for(int var7 = 0; var7 < this.jj_expentries.size(); ++var7) {
         var6[var7] = (int[])this.jj_expentries.elementAt(var7);
      }

      return new ParseException(this.token, var6, SQLParserConstants.tokenImage);
   }

   public final void enable_tracing() {
   }

   public final void disable_tracing() {
   }

   private final void jj_rescan_token() {
      this.jj_rescan = true;

      for(int var1 = 0; var1 < 94; ++var1) {
         try {
            JJCalls var2 = this.jj_2_rtns[var1];

            while(true) {
               if (var2.gen > this.jj_gen) {
                  this.jj_la = var2.arg;
                  this.jj_lastpos = this.jj_scanpos = var2.first;
                  switch (var1) {
                     case 0 -> this.jj_3_1();
                     case 1 -> this.jj_3_2();
                     case 2 -> this.jj_3_3();
                     case 3 -> this.jj_3_4();
                     case 4 -> this.jj_3_5();
                     case 5 -> this.jj_3_6();
                     case 6 -> this.jj_3_7();
                     case 7 -> this.jj_3_8();
                     case 8 -> this.jj_3_9();
                     case 9 -> this.jj_3_10();
                     case 10 -> this.jj_3_11();
                     case 11 -> this.jj_3_12();
                     case 12 -> this.jj_3_13();
                     case 13 -> this.jj_3_14();
                     case 14 -> this.jj_3_15();
                     case 15 -> this.jj_3_16();
                     case 16 -> this.jj_3_17();
                     case 17 -> this.jj_3_18();
                     case 18 -> this.jj_3_19();
                     case 19 -> this.jj_3_20();
                     case 20 -> this.jj_3_21();
                     case 21 -> this.jj_3_22();
                     case 22 -> this.jj_3_23();
                     case 23 -> this.jj_3_24();
                     case 24 -> this.jj_3_25();
                     case 25 -> this.jj_3_26();
                     case 26 -> this.jj_3_27();
                     case 27 -> this.jj_3_28();
                     case 28 -> this.jj_3_29();
                     case 29 -> this.jj_3_30();
                     case 30 -> this.jj_3_31();
                     case 31 -> this.jj_3_32();
                     case 32 -> this.jj_3_33();
                     case 33 -> this.jj_3_34();
                     case 34 -> this.jj_3_35();
                     case 35 -> this.jj_3_36();
                     case 36 -> this.jj_3_37();
                     case 37 -> this.jj_3_38();
                     case 38 -> this.jj_3_39();
                     case 39 -> this.jj_3_40();
                     case 40 -> this.jj_3_41();
                     case 41 -> this.jj_3_42();
                     case 42 -> this.jj_3_43();
                     case 43 -> this.jj_3_44();
                     case 44 -> this.jj_3_45();
                     case 45 -> this.jj_3_46();
                     case 46 -> this.jj_3_47();
                     case 47 -> this.jj_3_48();
                     case 48 -> this.jj_3_49();
                     case 49 -> this.jj_3_50();
                     case 50 -> this.jj_3_51();
                     case 51 -> this.jj_3_52();
                     case 52 -> this.jj_3_53();
                     case 53 -> this.jj_3_54();
                     case 54 -> this.jj_3_55();
                     case 55 -> this.jj_3_56();
                     case 56 -> this.jj_3_57();
                     case 57 -> this.jj_3_58();
                     case 58 -> this.jj_3_59();
                     case 59 -> this.jj_3_60();
                     case 60 -> this.jj_3_61();
                     case 61 -> this.jj_3_62();
                     case 62 -> this.jj_3_63();
                     case 63 -> this.jj_3_64();
                     case 64 -> this.jj_3_65();
                     case 65 -> this.jj_3_66();
                     case 66 -> this.jj_3_67();
                     case 67 -> this.jj_3_68();
                     case 68 -> this.jj_3_69();
                     case 69 -> this.jj_3_70();
                     case 70 -> this.jj_3_71();
                     case 71 -> this.jj_3_72();
                     case 72 -> this.jj_3_73();
                     case 73 -> this.jj_3_74();
                     case 74 -> this.jj_3_75();
                     case 75 -> this.jj_3_76();
                     case 76 -> this.jj_3_77();
                     case 77 -> this.jj_3_78();
                     case 78 -> this.jj_3_79();
                     case 79 -> this.jj_3_80();
                     case 80 -> this.jj_3_81();
                     case 81 -> this.jj_3_82();
                     case 82 -> this.jj_3_83();
                     case 83 -> this.jj_3_84();
                     case 84 -> this.jj_3_85();
                     case 85 -> this.jj_3_86();
                     case 86 -> this.jj_3_87();
                     case 87 -> this.jj_3_88();
                     case 88 -> this.jj_3_89();
                     case 89 -> this.jj_3_90();
                     case 90 -> this.jj_3_91();
                     case 91 -> this.jj_3_92();
                     case 92 -> this.jj_3_93();
                     case 93 -> this.jj_3_94();
                  }
               }

               var2 = var2.next;
               if (var2 == null) {
                  break;
               }
            }
         } catch (LookaheadSuccess var3) {
         }
      }

      this.jj_rescan = false;
   }

   private final void jj_save(int var1, int var2) {
      JJCalls var3;
      for(var3 = this.jj_2_rtns[var1]; var3.gen > this.jj_gen; var3 = var3.next) {
         if (var3.next == null) {
            var3 = var3.next = new JJCalls();
            break;
         }
      }

      var3.gen = this.jj_gen + var2 - this.jj_la;
      var3.first = this.token;
      var3.arg = var2;
   }

   static {
      jj_la1_0();
      jj_la1_1();
      jj_la1_2();
      jj_la1_3();
      jj_la1_4();
      jj_la1_5();
      jj_la1_6();
      jj_la1_7();
      jj_la1_8();
      jj_la1_9();
      jj_la1_10();
      jj_la1_11();
      jj_la1_12();
      jj_la1_13();
      jj_la1_14();
      jj_la1_15();
   }

   private static final class LookaheadSuccess extends Error {
   }

   static final class JJCalls {
      int gen;
      Token first;
      int arg;
      JJCalls next;
   }
}
