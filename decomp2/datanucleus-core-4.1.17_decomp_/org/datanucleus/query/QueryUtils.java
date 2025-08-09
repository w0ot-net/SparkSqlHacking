package org.datanucleus.query;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.query.compiler.JPQLCompiler;
import org.datanucleus.query.compiler.JavaQueryCompiler;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.OrderExpression;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.inmemory.InMemoryExpressionEvaluator;
import org.datanucleus.query.inmemory.InMemoryFailure;
import org.datanucleus.store.query.Query;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Imports;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;
import org.datanucleus.util.TypeConversionHelper;

public class QueryUtils {
   static final Class[] classArrayObjectObject = new Class[]{Object.class, Object.class};

   public static boolean resultClassIsUserType(String className) {
      return !resultClassIsSimple(className) && !className.equals(Map.class.getName()) && !className.equals(ClassNameConstants.Object);
   }

   public static boolean resultClassIsSimple(String className) {
      return className.equals(ClassNameConstants.JAVA_LANG_BOOLEAN) || className.equals(ClassNameConstants.JAVA_LANG_BYTE) || className.equals(ClassNameConstants.JAVA_LANG_CHARACTER) || className.equals(ClassNameConstants.JAVA_LANG_DOUBLE) || className.equals(ClassNameConstants.JAVA_LANG_FLOAT) || className.equals(ClassNameConstants.JAVA_LANG_INTEGER) || className.equals(ClassNameConstants.JAVA_LANG_LONG) || className.equals(ClassNameConstants.JAVA_LANG_SHORT) || className.equals(ClassNameConstants.JAVA_LANG_STRING) || className.equals(ClassNameConstants.JAVA_MATH_BIGDECIMAL) || className.equals(ClassNameConstants.JAVA_MATH_BIGINTEGER) || className.equals(ClassNameConstants.JAVA_UTIL_DATE) || className.equals(ClassNameConstants.JAVA_SQL_DATE) || className.equals(ClassNameConstants.JAVA_SQL_TIME) || className.equals(ClassNameConstants.JAVA_SQL_TIMESTAMP) || className.equals(ClassNameConstants.Object) || className.equals("java.net.URL") || className.equals("java.net.URI") || className.equals("java.util.Calendar") || className.equals("java.util.Currency") || className.equals("java.util.UUID");
   }

   public static boolean resultHasOnlyAggregates(String result) {
      if (result == null) {
         return false;
      } else {
         String resultDefn = result;
         if (result.toLowerCase().startsWith("distinct")) {
            resultDefn = result.substring(8);
         }

         StringTokenizer tokenizer = new StringTokenizer(resultDefn, ",");

         while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim().toLowerCase();
            if (!token.startsWith("max") && !token.startsWith("min") && !token.startsWith("avg") && !token.startsWith("sum")) {
               if (!token.startsWith("count")) {
                  return false;
               }

               token = token.substring(5).trim();
               if (!token.startsWith("(")) {
                  return false;
               }
            } else {
               token = token.substring(3).trim();
               if (!token.startsWith("(")) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static boolean queryReturnsSingleRow(Query query) {
      if (query.isUnique()) {
         return true;
      } else if (query.getGrouping() != null) {
         return false;
      } else {
         return resultHasOnlyAggregates(query.getResult());
      }
   }

   public static Object createResultObjectUsingArgumentedConstructor(Class resultClass, Object[] fieldValues, Class[] fieldTypes) {
      Object obj = null;
      Class[] ctrTypes = new Class[fieldValues.length];

      for(int i = 0; i < ctrTypes.length; ++i) {
         if (fieldTypes != null && fieldTypes[i] != null) {
            ctrTypes[i] = fieldTypes[i];
         } else if (fieldValues[i] != null) {
            ctrTypes[i] = fieldValues[i].getClass();
         } else {
            ctrTypes[i] = null;
         }
      }

      Constructor ctr = ClassUtils.getConstructorWithArguments(resultClass, ctrTypes);
      if (ctr != null) {
         try {
            obj = ctr.newInstance(fieldValues);
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               String msg = "ResultObject of type " + resultClass.getName() + " created with following constructor arguments: " + StringUtils.objectArrayToString(fieldValues);
               NucleusLogger.QUERY.debug(msg);
            }
         } catch (Exception var7) {
         }
      }

      return obj;
   }

   public static Object createResultObjectUsingDefaultConstructorAndSetters(Class resultClass, String[] resultFieldNames, Map resultClassFieldNames, Object[] fieldValues) {
      Object obj = null;

      try {
         obj = resultClass.newInstance();
      } catch (Exception var9) {
         String msg = Localiser.msg("021205", resultClass.getName());
         NucleusLogger.QUERY.error(msg);
         throw new NucleusUserException(msg);
      }

      for(int i = 0; i < fieldValues.length; ++i) {
         Field field = (Field)resultClassFieldNames.get(resultFieldNames[i].toUpperCase());
         if (!setFieldForResultObject(obj, resultFieldNames[i], field, fieldValues[i])) {
            String fieldType = "null";
            if (fieldValues[i] != null) {
               fieldType = fieldValues[i].getClass().getName();
            }

            String msg = Localiser.msg("021204", resultClass.getName(), resultFieldNames[i], fieldType);
            NucleusLogger.QUERY.error(msg);
            throw new NucleusUserException(msg);
         }
      }

      return obj;
   }

   public static Object createResultObjectUsingDefaultConstructorAndSetters(Class resultClass, String[] resultFieldNames, Field[] resultFields, Object[] fieldValues) {
      Object obj = null;

      try {
         obj = resultClass.newInstance();
      } catch (Exception var8) {
         String msg = Localiser.msg("021205", resultClass.getName());
         NucleusLogger.QUERY.error(msg);
         throw new NucleusUserException(msg);
      }

      for(int i = 0; i < fieldValues.length; ++i) {
         if (!setFieldForResultObject(obj, resultFieldNames[i], resultFields[i], fieldValues[i])) {
            String fieldType = "null";
            if (fieldValues[i] != null) {
               fieldType = fieldValues[i].getClass().getName();
            }

            String msg = Localiser.msg("021204", resultClass.getName(), resultFieldNames[i], fieldType);
            NucleusLogger.QUERY.error(msg);
            throw new NucleusUserException(msg);
         }
      }

      return obj;
   }

   private static boolean setFieldForResultObject(final Object obj, String fieldName, Field field, Object value) {
      boolean fieldSet = false;
      if (!fieldSet) {
         String declaredFieldName = fieldName;
         if (field != null) {
            declaredFieldName = field.getName();
         }

         Field f = ClassUtils.getFieldForClass(obj.getClass(), declaredFieldName);
         if (f != null && Modifier.isPublic(f.getModifiers())) {
            try {
               f.set(obj, value);
               fieldSet = true;
            } catch (Exception var17) {
               Object convertedValue = TypeConversionHelper.convertTo(value, f.getType());
               if (convertedValue != value) {
                  try {
                     f.set(obj, convertedValue);
                     fieldSet = true;
                     if (NucleusLogger.QUERY.isDebugEnabled()) {
                        String msg = "ResultObject set field=" + fieldName + " using reflection";
                        NucleusLogger.QUERY.debug(msg);
                     }
                  } catch (Exception var15) {
                  }
               }
            }
         }

         if (!fieldSet && NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021209", obj.getClass().getName(), declaredFieldName));
         }
      }

      if (!fieldSet) {
         String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
         if (field != null) {
            setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + field.getName().substring(1);
         }

         Class argType = null;
         if (value != null) {
            argType = value.getClass();
         } else if (field != null) {
            argType = field.getType();
         }

         Method m = ClassUtils.getMethodWithArgument(obj.getClass(), setMethodName, argType);
         if (m != null && Modifier.isPublic(m.getModifiers())) {
            try {
               m.invoke(obj, value);
               fieldSet = true;
               if (NucleusLogger.QUERY.isDebugEnabled()) {
                  String msg = "ResultObject set field=" + fieldName + " using public " + setMethodName + "() method";
                  NucleusLogger.QUERY.debug(msg);
               }
            } catch (Exception var14) {
            }
         } else if (m == null) {
            Method[] methods = (Method[])AccessController.doPrivileged(new PrivilegedAction() {
               public Object run() {
                  return obj.getClass().getDeclaredMethods();
               }
            });

            for(int i = 0; i < methods.length; ++i) {
               Class[] args = methods[i].getParameterTypes();
               if (methods[i].getName().equals(setMethodName) && Modifier.isPublic(methods[i].getModifiers()) && args != null && args.length == 1) {
                  try {
                     Object convValue = ClassUtils.convertValue(value, args[0]);
                     methods[i].invoke(obj, convValue);
                     fieldSet = true;
                     if (NucleusLogger.QUERY.isDebugEnabled()) {
                        String msg = "ResultObject set field=" + fieldName + " using " + setMethodName + "() method after converting value";
                        NucleusLogger.QUERY.debug(msg);
                     }
                     break;
                  } catch (Exception var16) {
                  }
               }
            }
         }

         if (!fieldSet && NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021207", obj.getClass().getName(), setMethodName, argType != null ? argType.getName() : null));
         }
      }

      if (!fieldSet) {
         Method m = getPublicPutMethodForResultClass(obj.getClass());
         if (m != null) {
            try {
               m.invoke(obj, fieldName, value);
               fieldSet = true;
               if (NucleusLogger.QUERY.isDebugEnabled()) {
                  String msg = "ResultObject set field=" + fieldName + " using put() method";
                  NucleusLogger.QUERY.debug(msg);
               }
            } catch (Exception var13) {
            }
         }

         if (!fieldSet && NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021208", obj.getClass().getName(), "put"));
         }
      }

      return fieldSet;
   }

   public static Method getPublicSetMethodForFieldOfResultClass(Class resultClass, String fieldName, Class fieldType) {
      String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
      Method m = ClassUtils.getMethodWithArgument(resultClass, setMethodName, fieldType);
      return m != null && Modifier.isPublic(m.getModifiers()) ? m : null;
   }

   public static Method getPublicPutMethodForResultClass(final Class resultClass) {
      return (Method)AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            try {
               return resultClass.getMethod("put", QueryUtils.classArrayObjectObject);
            } catch (NoSuchMethodException var2) {
               return null;
            }
         }
      });
   }

   public static String[] getExpressionsFromString(String str) {
      CharacterIterator ci = new StringCharacterIterator(str);
      int braces = 0;
      String text = "";

      List<String> exprList;
      for(exprList = new ArrayList(); ci.getIndex() != ci.getEndIndex(); ci.next()) {
         char c = ci.current();
         if (c == ',' && braces == 0) {
            exprList.add(text);
            text = "";
         } else if (c == '(') {
            ++braces;
            text = text + c;
         } else if (c == ')') {
            --braces;
            text = text + c;
         } else {
            text = text + c;
         }
      }

      exprList.add(text);
      return (String[])exprList.toArray(new String[exprList.size()]);
   }

   public static Object getValueForParameterExpression(Map parameterValues, ParameterExpression paramExpr) {
      if (parameterValues == null) {
         return null;
      } else {
         for(Map.Entry entry : parameterValues.entrySet()) {
            Object key = entry.getKey();
            if (key instanceof Integer) {
               if ((Integer)key == paramExpr.getPosition()) {
                  return entry.getValue();
               }
            } else if (key instanceof String && ((String)key).equals(paramExpr.getId())) {
               return entry.getValue();
            }
         }

         return null;
      }
   }

   public static String getStringValue(Object obj) {
      String value = null;
      if (obj instanceof String) {
         value = (String)obj;
      } else if (obj instanceof Character) {
         value = ((Character)obj).toString();
      } else if (obj instanceof Number) {
         value = ((Number)obj).toString();
      } else if (obj != null) {
         throw new NucleusException("getStringValue(obj) where obj is instanceof " + obj.getClass().getName() + " not supported");
      }

      return value;
   }

   public static String getStringValueForExpression(Expression expr, Map parameters) {
      String paramValue = null;
      if (expr instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)expr;
         Object obj = getValueForParameterExpression(parameters, paramExpr);
         paramValue = getStringValue(obj);
      } else {
         if (!(expr instanceof Literal)) {
            throw new NucleusException("getStringValueForExpression(expr) where expr is instanceof " + expr.getClass().getName() + " not supported");
         }

         Literal literal = (Literal)expr;
         paramValue = getStringValue(literal.getLiteral());
      }

      return paramValue;
   }

   public static boolean compareExpressionValues(Object left, Object right, Expression.Operator op) {
      if (left != null && right != null) {
         if (!(left instanceof Float) && !(left instanceof Double) && !(left instanceof BigDecimal) && !(right instanceof Float) && !(right instanceof Double) && !(right instanceof BigDecimal)) {
            if (!(left instanceof Short) && !(left instanceof Integer) && !(left instanceof Long) && !(left instanceof BigInteger) && !(left instanceof Character) && !(right instanceof Short) && !(right instanceof Integer) && !(right instanceof Long) && !(right instanceof BigInteger) && !(right instanceof Character)) {
               if (left instanceof Enum || right instanceof Enum || left instanceof String || right instanceof String) {
                  String leftStr = left.toString();
                  String rightStr = right.toString();
                  if (op == Expression.OP_EQ) {
                     return leftStr != null ? leftStr.equals(rightStr) : rightStr == null;
                  }

                  if (op == Expression.OP_NOTEQ) {
                     return leftStr != null ? !leftStr.equals(rightStr) : rightStr != null;
                  }

                  if (op == Expression.OP_GT) {
                     return leftStr != null ? leftStr.compareTo(rightStr) > 0 : false;
                  }

                  if (op == Expression.OP_GTEQ) {
                     return leftStr != null ? leftStr.compareTo(rightStr) >= 0 : false;
                  }

                  if (op == Expression.OP_LT) {
                     return leftStr != null ? leftStr.compareTo(rightStr) < 0 : false;
                  }

                  if (op == Expression.OP_LTEQ) {
                     return leftStr != null ? leftStr.compareTo(rightStr) <= 0 : false;
                  }

                  throw new NucleusException("Attempt to evaluate relational expression between\"" + left + "\" (type=" + left.getClass().getName() + ") and\"" + right + "\" (type=" + right.getClass().getName() + ") not possible due to types");
               }

               if (!(left instanceof Date) && !(right instanceof Date)) {
                  if (op == Expression.OP_EQ) {
                     return left.equals(right);
                  }

                  if (op == Expression.OP_NOTEQ) {
                     return !left.equals(right);
                  }

                  if (left instanceof Comparable && right instanceof Comparable) {
                     Comparable leftC = (Comparable)left;
                     Comparable rightC = (Comparable)right;
                     if (op == Expression.OP_GT) {
                        return leftC.compareTo(rightC) > 0;
                     }

                     if (op == Expression.OP_LT) {
                        return leftC.compareTo(rightC) < 0;
                     }

                     if (op == Expression.OP_LTEQ) {
                        return leftC.compareTo(rightC) < 0 || leftC.compareTo(rightC) == 0;
                     }

                     if (op == Expression.OP_GTEQ) {
                        return leftC.compareTo(rightC) > 0 || leftC.compareTo(rightC) == 0;
                     }
                  }

                  throw new NucleusException("Attempt to evaluate relational expression between\"" + left + "\" (type=" + left.getClass().getName() + ") and\"" + right + "\" (type=" + right.getClass().getName() + ") not possible due to types");
               }

               long leftVal = Long.MAX_VALUE;
               long rightVal = Long.MAX_VALUE;
               if (left instanceof Date) {
                  leftVal = ((Date)left).getTime();
               }

               if (right instanceof Date) {
                  rightVal = ((Date)right).getTime();
               }

               if (leftVal == Long.MAX_VALUE || rightVal == Long.MAX_VALUE) {
                  throw new NucleusException("Attempt to evaluate relational expression between\"" + left + "\" (type=" + left.getClass().getName() + ") and\"" + right + "\" (type=" + right.getClass().getName() + ") not possible due to types");
               }

               if (op == Expression.OP_GT) {
                  return leftVal > rightVal;
               }

               if (op == Expression.OP_LT) {
                  return leftVal < rightVal;
               }

               if (op == Expression.OP_GTEQ) {
                  return leftVal >= rightVal;
               }

               if (op == Expression.OP_LTEQ) {
                  return leftVal <= rightVal;
               }

               if (op == Expression.OP_EQ) {
                  return leftVal == rightVal;
               }

               if (op == Expression.OP_NOTEQ) {
                  return leftVal != rightVal;
               }
            } else {
               boolean leftUnset = false;
               boolean rightUnset = false;
               long leftVal = Long.MAX_VALUE;
               long rightVal = Long.MAX_VALUE;
               if (left instanceof BigInteger) {
                  leftVal = ((BigInteger)left).longValue();
               } else if (left instanceof Long) {
                  leftVal = (Long)left;
               } else if (left instanceof Integer) {
                  leftVal = ((Integer)left).longValue();
               } else if (left instanceof Short) {
                  leftVal = ((Short)left).longValue();
               } else if (left instanceof BigDecimal) {
                  leftVal = ((BigDecimal)left).longValue();
               } else if (left instanceof Double) {
                  leftVal = ((Double)left).longValue();
               } else if (left instanceof Float) {
                  leftVal = ((Float)left).longValue();
               } else if (left instanceof Enum) {
                  leftVal = (long)((Enum)left).ordinal();
               } else if (left instanceof Byte) {
                  leftVal = ((Byte)left).longValue();
               } else if (left instanceof Character) {
                  leftVal = (long)(Character)left;
               } else {
                  leftUnset = true;
               }

               if (right instanceof BigInteger) {
                  rightVal = ((BigInteger)right).longValue();
               } else if (right instanceof Long) {
                  rightVal = (Long)right;
               } else if (right instanceof Integer) {
                  rightVal = ((Integer)right).longValue();
               } else if (right instanceof Short) {
                  rightVal = ((Short)right).longValue();
               } else if (right instanceof BigDecimal) {
                  rightVal = ((BigDecimal)right).longValue();
               } else if (right instanceof Double) {
                  rightVal = ((Double)right).longValue();
               } else if (right instanceof Float) {
                  rightVal = ((Float)right).longValue();
               } else if (right instanceof Enum) {
                  rightVal = (long)((Enum)right).ordinal();
               } else if (right instanceof Byte) {
                  rightVal = ((Byte)right).longValue();
               } else if (right instanceof Character) {
                  rightVal = (long)(Character)right;
               } else {
                  rightUnset = true;
               }

               if (leftUnset || rightUnset) {
                  throw new NucleusException("Attempt to evaluate relational expression between\"" + left + "\" (type=" + left.getClass().getName() + ") and\"" + right + "\" (type=" + right.getClass().getName() + ") not possible due to types");
               }

               if (op == Expression.OP_GT) {
                  return leftVal > rightVal;
               }

               if (op == Expression.OP_LT) {
                  return leftVal < rightVal;
               }

               if (op == Expression.OP_GTEQ) {
                  return leftVal >= rightVal;
               }

               if (op == Expression.OP_LTEQ) {
                  return leftVal <= rightVal;
               }

               if (op == Expression.OP_EQ) {
                  return leftVal == rightVal;
               }

               if (op == Expression.OP_NOTEQ) {
                  return leftVal != rightVal;
               }
            }
         } else {
            Double leftVal = null;
            Double rightVal = null;
            if (left instanceof BigDecimal) {
               leftVal = new Double(((BigDecimal)left).doubleValue());
            } else if (left instanceof Double) {
               leftVal = (Double)left;
            } else if (left instanceof Float) {
               leftVal = new Double(((Float)left).doubleValue());
            } else if (left instanceof BigInteger) {
               leftVal = new Double(((BigInteger)left).doubleValue());
            } else if (left instanceof Long) {
               leftVal = new Double(((Long)left).doubleValue());
            } else if (left instanceof Integer) {
               leftVal = new Double(((Integer)left).doubleValue());
            } else if (left instanceof Short) {
               leftVal = new Double(((Short)left).doubleValue());
            } else if (left instanceof Enum) {
               leftVal = new Double((double)((Enum)left).ordinal());
            }

            if (right instanceof BigDecimal) {
               rightVal = new Double(((BigDecimal)right).doubleValue());
            } else if (right instanceof Double) {
               rightVal = (Double)right;
            } else if (right instanceof Float) {
               rightVal = new Double(((Float)right).doubleValue());
            } else if (right instanceof BigInteger) {
               rightVal = new Double(((BigInteger)right).doubleValue());
            } else if (right instanceof Long) {
               rightVal = new Double(((Long)right).doubleValue());
            } else if (right instanceof Integer) {
               rightVal = new Double(((Integer)right).doubleValue());
            } else if (right instanceof Short) {
               rightVal = new Double(((Short)right).doubleValue());
            } else if (right instanceof Enum) {
               rightVal = new Double((double)((Enum)right).ordinal());
            }

            if (leftVal == null || rightVal == null) {
               throw new NucleusException("Attempt to evaluate relational expression between\"" + left + "\" (type=" + left.getClass().getName() + ") and\"" + right + "\" (type=" + right.getClass().getName() + ") not possible due to types");
            }

            int comparison = leftVal.compareTo(rightVal);
            if (op == Expression.OP_GT) {
               return comparison > 0;
            }

            if (op == Expression.OP_LT) {
               return comparison < 0;
            }

            if (op == Expression.OP_GTEQ) {
               return comparison >= 0;
            }

            if (op == Expression.OP_LTEQ) {
               return comparison <= 0;
            }

            if (op == Expression.OP_EQ) {
               return comparison == 0;
            }

            if (op == Expression.OP_NOTEQ) {
               return comparison != 0;
            }
         }
      } else {
         if (op == Expression.OP_GT) {
            if (left == null && right == null) {
               return false;
            }

            if (left == null) {
               return false;
            }

            return true;
         }

         if (op == Expression.OP_LT) {
            if (left == null && right == null) {
               return false;
            }

            if (left == null) {
               return true;
            }

            return false;
         }

         if (op == Expression.OP_GTEQ) {
            if (left == null && right == null) {
               return true;
            }

            if (left == null) {
               return false;
            }

            return true;
         }

         if (op == Expression.OP_LTEQ) {
            if (left == null && right == null) {
               return true;
            }

            if (left == null) {
               return true;
            }

            return false;
         }

         if (op == Expression.OP_EQ) {
            return left == right;
         }

         if (op == Expression.OP_NOTEQ) {
            return left != right;
         }
      }

      throw new NucleusException("Attempt to evaluate relational expression between " + left + " and " + right + " with operation = " + op + " impossible to perform");
   }

   public static boolean expressionHasOrOperator(Expression expr) {
      if (expr instanceof DyadicExpression && expr.getOperator() == Expression.OP_OR) {
         return true;
      } else if (expr.getLeft() != null && expressionHasOrOperator(expr.getLeft())) {
         return true;
      } else {
         return expr.getRight() != null && expressionHasOrOperator(expr.getRight());
      }
   }

   public static boolean expressionHasNotOperator(Expression expr) {
      if (expr instanceof DyadicExpression && expr.getOperator() == Expression.OP_NOT) {
         return true;
      } else if (expr.getLeft() != null && expressionHasNotOperator(expr.getLeft())) {
         return true;
      } else {
         return expr.getRight() != null && expressionHasNotOperator(expr.getRight());
      }
   }

   public static ParameterExpression getParameterExpressionForPosition(Expression rootExpr, int pos) {
      if (rootExpr instanceof ParameterExpression && ((ParameterExpression)rootExpr).getPosition() == pos) {
         return (ParameterExpression)rootExpr;
      } else {
         if (rootExpr.getLeft() != null) {
            ParameterExpression paramExpr = getParameterExpressionForPosition(rootExpr.getLeft(), pos);
            if (paramExpr != null) {
               return paramExpr;
            }
         }

         if (rootExpr.getRight() != null) {
            ParameterExpression paramExpr = getParameterExpressionForPosition(rootExpr.getRight(), pos);
            if (paramExpr != null) {
               return paramExpr;
            }
         }

         if (rootExpr instanceof InvokeExpression) {
            InvokeExpression invokeExpr = (InvokeExpression)rootExpr;
            List<Expression> args = invokeExpr.getArguments();
            if (args != null) {
               Iterator<Expression> argIter = args.iterator();

               while(argIter.hasNext()) {
                  ParameterExpression paramExpr = getParameterExpressionForPosition((Expression)argIter.next(), pos);
                  if (paramExpr != null) {
                     return paramExpr;
                  }
               }
            }
         }

         return null;
      }
   }

   public static boolean queryParameterTypesAreCompatible(Class cls1, Class cls2) {
      Class first = cls1;
      Class second = cls2;
      if (cls1.isPrimitive()) {
         first = ClassUtils.getWrapperTypeForPrimitiveType(cls1);
      }

      if (cls2.isPrimitive()) {
         second = ClassUtils.getWrapperTypeForPrimitiveType(cls2);
      }

      if (first.isAssignableFrom(second)) {
         return true;
      } else {
         return Number.class.isAssignableFrom(first) && Number.class.isAssignableFrom(cls2);
      }
   }

   public static String getKeyForQueryResultsCache(Query query, Map params) {
      return params != null && params.size() > 0 ? query.getLanguage() + ":" + query.toString() + ":" + params.hashCode() : query.getLanguage() + ":" + query.toString() + ":";
   }

   public static List orderCandidates(List candidates, Class type, String ordering, ExecutionContext ec, ClassLoaderResolver clr) {
      return orderCandidates(candidates, type, ordering, ec, clr, "JDOQL");
   }

   public static List orderCandidates(List candidates, Class type, String ordering, ExecutionContext ec, ClassLoaderResolver clr, String queryLanguage) {
      if (candidates != null && !candidates.isEmpty() && ordering != null && !ordering.equals("#PK")) {
         JavaQueryCompiler compiler = new JPQLCompiler(ec.getMetaDataManager(), ec.getClassLoaderResolver(), (String)null, type, (Collection)null, (String)null, (Imports)null, ordering, (String)null, (String)null, (String)null, (String)null, (String)null);
         QueryCompilation compilation = compiler.compile((Map)null, (Map)null);
         return orderCandidates(candidates, compilation.getExprOrdering(), new HashMap(), "this", ec, clr, (Map)null, (Imports)null, queryLanguage);
      } else {
         return candidates;
      }
   }

   public static List orderCandidates(List candidates, final Expression[] ordering, final Map state, final String candidateAlias, final ExecutionContext ec, final ClassLoaderResolver clr, final Map parameterValues, final Imports imports, final String queryLanguage) {
      if (ordering == null) {
         return candidates;
      } else {
         Object[] o = candidates.toArray();
         Arrays.sort(o, new Comparator() {
            public int compare(Object obj1, Object obj2) {
               for(int i = 0; i < ordering.length; ++i) {
                  state.put(candidateAlias, obj1);
                  Object a = ordering[i].evaluate(new InMemoryExpressionEvaluator(ec, parameterValues, state, imports, clr, candidateAlias, queryLanguage));
                  state.put(candidateAlias, obj2);
                  Object b = ordering[i].evaluate(new InMemoryExpressionEvaluator(ec, parameterValues, state, imports, clr, candidateAlias, queryLanguage));
                  if (a instanceof InMemoryFailure || b instanceof InMemoryFailure) {
                     throw new NucleusException("Error encountered in in-memory evaluation of ordering. Consult the log for details");
                  }

                  OrderExpression orderExpr = (OrderExpression)ordering[i];
                  if (a == null && b == null) {
                     return 0;
                  }

                  if (a == null) {
                     if (orderExpr.getNullOrder() != null) {
                        return orderExpr.getNullOrder() == NullOrderingType.NULLS_FIRST ? 1 : -1;
                     }

                     return -1;
                  }

                  if (b == null) {
                     if (orderExpr.getNullOrder() != null) {
                        return orderExpr.getNullOrder() == NullOrderingType.NULLS_FIRST ? -1 : 1;
                     }

                     return 1;
                  }

                  int result = ((Comparable)a).compareTo(b);
                  if (result != 0) {
                     if (orderExpr.getSortOrder() != null && !orderExpr.getSortOrder().equals("ascending")) {
                        return -1 * result;
                     }

                     return result;
                  }
               }

               return 0;
            }
         });
         return Arrays.asList(o);
      }
   }
}
