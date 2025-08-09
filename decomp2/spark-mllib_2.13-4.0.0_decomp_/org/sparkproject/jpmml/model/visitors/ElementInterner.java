package org.sparkproject.jpmml.model.visitors;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import java.lang.reflect.Field;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.ReflectionUtil;

public abstract class ElementInterner extends Interner {
   public ElementInterner(Class type) {
      super(type);
   }

   public VisitorAction visit(PMMLObject object) {
      for(Field field : ReflectionUtil.getFields(object.getClass())) {
         XmlElement element = (XmlElement)field.getAnnotation(XmlElement.class);
         XmlElements elements = (XmlElements)field.getAnnotation(XmlElements.class);
         if (element != null || elements != null) {
            this.apply(field, object);
         }
      }

      return super.visit(object);
   }
}
