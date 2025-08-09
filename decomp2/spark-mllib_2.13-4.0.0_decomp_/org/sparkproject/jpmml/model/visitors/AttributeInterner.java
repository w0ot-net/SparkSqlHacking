package org.sparkproject.jpmml.model.visitors;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlList;
import java.lang.reflect.Field;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.ReflectionUtil;

public abstract class AttributeInterner extends Interner {
   public AttributeInterner(Class type) {
      super(type);
   }

   public VisitorAction visit(PMMLObject object) {
      for(Field field : ReflectionUtil.getFields(object.getClass())) {
         XmlAttribute attribute = (XmlAttribute)field.getAnnotation(XmlAttribute.class);
         XmlList list = (XmlList)field.getAnnotation(XmlList.class);
         if (attribute != null || list != null) {
            this.apply(field, object);
         }
      }

      return super.visit(object);
   }
}
