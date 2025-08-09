package org.apache.hadoop.hive.ql.exec;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

public class SparkDefaultUDAFEvaluatorResolver implements UDAFEvaluatorResolver {
   private final Class udafClass;

   public SparkDefaultUDAFEvaluatorResolver(DefaultUDAFEvaluatorResolver wrapped) {
      try {
         Field udfClassField = wrapped.getClass().getDeclaredField("udafClass");
         udfClassField.setAccessible(true);
         this.udafClass = (Class)udfClassField.get(wrapped);
      } catch (ReflectiveOperationException rethrow) {
         throw new RuntimeException(rethrow);
      }
   }

   public Class getEvaluatorClass(List argClasses) throws UDFArgumentException {
      ArrayList<Class<? extends UDAFEvaluator>> classList = new ArrayList();

      for(Class enclClass : this.udafClass.getClasses()) {
         if (UDAFEvaluator.class.isAssignableFrom(enclClass)) {
            classList.add(enclClass);
         }
      }

      ArrayList<Method> mList = new ArrayList();
      ArrayList<Class<? extends UDAFEvaluator>> cList = new ArrayList();

      for(Class evaluator : classList) {
         for(Method m : evaluator.getMethods()) {
            if (m.getName().equalsIgnoreCase("iterate")) {
               mList.add(m);
               cList.add(evaluator);
            }
         }
      }

      Method m = HiveFunctionRegistryUtils.getMethodInternal(this.udafClass, (List)mList, false, argClasses);
      int found = -1;

      for(int i = 0; i < mList.size(); ++i) {
         if (mList.get(i) == m) {
            if (found != -1) {
               throw new AmbiguousMethodException(this.udafClass, argClasses, mList);
            }

            found = i;
         }
      }

      assert found != -1;

      return (Class)cList.get(found);
   }
}
