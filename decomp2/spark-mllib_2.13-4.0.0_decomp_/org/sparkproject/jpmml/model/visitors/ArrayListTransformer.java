package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.ReflectionUtil;
import org.sparkproject.jpmml.model.collections.DoubletonList;
import org.sparkproject.jpmml.model.collections.SingletonList;
import org.sparkproject.jpmml.model.collections.TripletonList;

public class ArrayListTransformer extends AbstractVisitor {
   public VisitorAction visit(PMMLObject object) {
      for(Field field : ReflectionUtil.getFields(object.getClass())) {
         Object value = ReflectionUtil.getFieldValue(field, object);
         if (value != null && Objects.equals(ArrayList.class, value.getClass())) {
            ArrayList<?> list = (ArrayList)value;
            List<?> transformedList = this.transform(list);
            if (list != transformedList) {
               ReflectionUtil.setFieldValue(field, object, transformedList);
            }
         }
      }

      return super.visit(object);
   }

   public List transform(List list) {
      int size = list.size();
      switch (size) {
         case 0:
            return Collections.emptyList();
         case 1:
            return new SingletonList(list.get(0));
         case 2:
            return new DoubletonList(list.get(0), list.get(1));
         case 3:
            return new TripletonList(list.get(0), list.get(1), list.get(2));
         default:
            return list;
      }
   }
}
