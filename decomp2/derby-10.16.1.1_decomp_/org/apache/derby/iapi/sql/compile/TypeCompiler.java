package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public interface TypeCompiler {
   int LONGINT_MAXWIDTH_AS_CHAR = 20;
   int INT_MAXWIDTH_AS_CHAR = 11;
   int SMALLINT_MAXWIDTH_AS_CHAR = 6;
   int TINYINT_MAXWIDTH_AS_CHAR = 4;
   int DOUBLE_MAXWIDTH_AS_CHAR = 54;
   int REAL_MAXWIDTH_AS_CHAR = 25;
   int DEFAULT_DECIMAL_PRECISION = 5;
   int DEFAULT_DECIMAL_SCALE = 0;
   int MAX_DECIMAL_PRECISION_SCALE = 31;
   int BOOLEAN_MAXWIDTH_AS_CHAR = 5;
   String PLUS_OP = "+";
   String DIVIDE_OP = "/";
   String MINUS_OP = "-";
   String TIMES_OP = "*";
   String SUM_OP = "sum";
   String AVG_OP = "avg";
   String MOD_OP = "mod";

   DataTypeDescriptor resolveArithmeticOperation(DataTypeDescriptor var1, DataTypeDescriptor var2, String var3) throws StandardException;

   boolean convertible(TypeId var1, boolean var2);

   boolean compatible(TypeId var1);

   boolean storable(TypeId var1, ClassFactory var2);

   String interfaceName();

   String getCorrespondingPrimitiveTypeName();

   String getPrimitiveMethodName();

   void generateNull(MethodBuilder var1, int var2);

   void generateDataValue(MethodBuilder var1, int var2, LocalField var3);

   int getCastToCharWidth(DataTypeDescriptor var1);
}
