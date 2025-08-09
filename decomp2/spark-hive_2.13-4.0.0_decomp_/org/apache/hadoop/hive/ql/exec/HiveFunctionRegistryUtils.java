package org.apache.hadoop.hive.ql.exec;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.typeinfo.ListTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.MapTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;

public class HiveFunctionRegistryUtils {
   public static final SparkLogger LOG = SparkLoggerFactory.getLogger(HiveFunctionRegistryUtils.class);

   public static Method getMethodInternal(Class udfClass, String methodName, boolean exact, List argumentClasses) throws UDFArgumentException {
      List<Method> mlist = new ArrayList();

      for(Method m : udfClass.getMethods()) {
         if (m.getName().equals(methodName)) {
            mlist.add(m);
         }
      }

      return getMethodInternal(udfClass, mlist, exact, argumentClasses);
   }

   public static Method getMethodInternal(Class udfClass, List mlist, boolean exact, List argumentsPassed) throws UDFArgumentException {
      List<Method> udfMethods = new ArrayList();
      int leastConversionCost = Integer.MAX_VALUE;

      for(Method m : mlist) {
         List<TypeInfo> argumentsAccepted = TypeInfoUtils.getParameterTypeInfos(m, argumentsPassed.size());
         if (argumentsAccepted != null) {
            boolean match = argumentsAccepted.size() == argumentsPassed.size();
            int conversionCost = 0;

            for(int i = 0; i < argumentsPassed.size() && match; ++i) {
               int cost = matchCost((TypeInfo)argumentsPassed.get(i), (TypeInfo)argumentsAccepted.get(i), exact);
               if (cost == -1) {
                  match = false;
               } else {
                  conversionCost += cost;
               }
            }

            LOG.debug("Method {} match: passed = {} accepted = {} method = {}", new Object[]{match ? "did" : "didn't", argumentsPassed, argumentsAccepted, m});
            if (match) {
               if (conversionCost < leastConversionCost) {
                  udfMethods.clear();
                  udfMethods.add(m);
                  leastConversionCost = conversionCost;
                  if (conversionCost == 0) {
                     break;
                  }
               } else if (conversionCost == leastConversionCost) {
                  udfMethods.add(m);
               }
            }
         }
      }

      if (udfMethods.size() == 0) {
         throw new NoMatchingMethodException(udfClass, argumentsPassed, mlist);
      } else {
         if (udfMethods.size() > 1) {
            filterMethodsByTypeAffinity(udfMethods, argumentsPassed);
         }

         if (udfMethods.size() <= 1) {
            return (Method)udfMethods.get(0);
         } else {
            int lowestNumericType = Integer.MAX_VALUE;
            boolean multiple = true;
            Method candidate = null;
            List<TypeInfo> referenceArguments = null;

            for(Method m : udfMethods) {
               int maxNumericType = 0;
               List<TypeInfo> argumentsAccepted = TypeInfoUtils.getParameterTypeInfos(m, argumentsPassed.size());
               if (referenceArguments == null) {
                  referenceArguments = argumentsAccepted;
               }

               Iterator<TypeInfo> referenceIterator = referenceArguments.iterator();

               for(TypeInfo accepted : argumentsAccepted) {
                  TypeInfo reference = (TypeInfo)referenceIterator.next();
                  boolean acceptedIsPrimitive = false;
                  PrimitiveObjectInspector.PrimitiveCategory acceptedPrimCat = PrimitiveCategory.UNKNOWN;
                  if (accepted.getCategory() == Category.PRIMITIVE) {
                     acceptedIsPrimitive = true;
                     acceptedPrimCat = ((PrimitiveTypeInfo)accepted).getPrimitiveCategory();
                  }

                  if (acceptedIsPrimitive && TypeInfoUtils.numericTypes.containsKey(acceptedPrimCat)) {
                     int typeValue = (Integer)TypeInfoUtils.numericTypes.get(acceptedPrimCat);
                     maxNumericType = typeValue > maxNumericType ? typeValue : maxNumericType;
                  } else if (!accepted.equals(reference)) {
                     throw new AmbiguousMethodException(udfClass, argumentsPassed, mlist);
                  }
               }

               if (lowestNumericType > maxNumericType) {
                  multiple = false;
                  lowestNumericType = maxNumericType;
                  candidate = m;
               } else if (maxNumericType == lowestNumericType) {
                  multiple = true;
               }
            }

            if (!multiple) {
               return candidate;
            } else {
               throw new AmbiguousMethodException(udfClass, argumentsPassed, mlist);
            }
         }
      }
   }

   public static Object invoke(Method m, Object thisObject, Object... arguments) throws HiveException {
      try {
         Object o = m.invoke(thisObject, arguments);
         return o;
      } catch (Exception var7) {
         StringBuilder argumentString = new StringBuilder();
         if (arguments == null) {
            argumentString.append("null");
         } else {
            argumentString.append("{");

            for(int i = 0; i < arguments.length; ++i) {
               if (i > 0) {
                  argumentString.append(",");
               }

               argumentString.append(arguments[i]);
            }

            argumentString.append("}");
         }

         String detailedMsg = var7 instanceof InvocationTargetException ? var7.getCause().getMessage() : var7.getMessage();
         throw new HiveException("Unable to execute method " + String.valueOf(m) + " with arguments " + String.valueOf(argumentString) + ":" + detailedMsg, var7);
      }
   }

   public static int matchCost(TypeInfo argumentPassed, TypeInfo argumentAccepted, boolean exact) {
      if (!argumentAccepted.equals(argumentPassed) && !TypeInfoUtils.doPrimitiveCategoriesMatch(argumentPassed, argumentAccepted)) {
         if (argumentPassed.equals(TypeInfoFactory.voidTypeInfo)) {
            return 0;
         } else if (argumentPassed.getCategory().equals(Category.LIST) && argumentAccepted.getCategory().equals(Category.LIST)) {
            TypeInfo argumentPassedElement = ((ListTypeInfo)argumentPassed).getListElementTypeInfo();
            TypeInfo argumentAcceptedElement = ((ListTypeInfo)argumentAccepted).getListElementTypeInfo();
            return matchCost(argumentPassedElement, argumentAcceptedElement, exact);
         } else if (argumentPassed.getCategory().equals(Category.MAP) && argumentAccepted.getCategory().equals(Category.MAP)) {
            TypeInfo argumentPassedKey = ((MapTypeInfo)argumentPassed).getMapKeyTypeInfo();
            TypeInfo argumentAcceptedKey = ((MapTypeInfo)argumentAccepted).getMapKeyTypeInfo();
            TypeInfo argumentPassedValue = ((MapTypeInfo)argumentPassed).getMapValueTypeInfo();
            TypeInfo argumentAcceptedValue = ((MapTypeInfo)argumentAccepted).getMapValueTypeInfo();
            int cost1 = matchCost(argumentPassedKey, argumentAcceptedKey, exact);
            int cost2 = matchCost(argumentPassedValue, argumentAcceptedValue, exact);
            return cost1 >= 0 && cost2 >= 0 ? Math.max(cost1, cost2) : -1;
         } else if (argumentAccepted.equals(TypeInfoFactory.unknownTypeInfo)) {
            return 1;
         } else {
            return !exact && TypeInfoUtils.implicitConvertible(argumentPassed, argumentAccepted) ? 1 : -1;
         }
      } else {
         return 0;
      }
   }

   static void filterMethodsByTypeAffinity(List udfMethods, List argumentsPassed) {
      if (udfMethods.size() > 1) {
         int currentScore = 0;
         int bestMatchScore = 0;
         Method bestMatch = null;

         for(Method m : udfMethods) {
            currentScore = 0;
            List<TypeInfo> argumentsAccepted = TypeInfoUtils.getParameterTypeInfos(m, argumentsPassed.size());
            Iterator<TypeInfo> argsPassedIter = argumentsPassed.iterator();

            for(TypeInfo acceptedType : argumentsAccepted) {
               TypeInfo passedType = (TypeInfo)argsPassedIter.next();
               if (acceptedType.getCategory() == Category.PRIMITIVE && passedType.getCategory() == Category.PRIMITIVE) {
                  PrimitiveObjectInspectorUtils.PrimitiveGrouping acceptedPg = PrimitiveObjectInspectorUtils.getPrimitiveGrouping(((PrimitiveTypeInfo)acceptedType).getPrimitiveCategory());
                  PrimitiveObjectInspectorUtils.PrimitiveGrouping passedPg = PrimitiveObjectInspectorUtils.getPrimitiveGrouping(((PrimitiveTypeInfo)passedType).getPrimitiveCategory());
                  if (acceptedPg == passedPg) {
                     ++currentScore;
                  }
               }
            }

            if (currentScore > bestMatchScore) {
               bestMatchScore = currentScore;
               bestMatch = m;
            } else if (currentScore == bestMatchScore) {
               bestMatch = null;
            }
         }

         if (bestMatch != null) {
            udfMethods.clear();
            udfMethods.add(bestMatch);
         }
      }

   }
}
