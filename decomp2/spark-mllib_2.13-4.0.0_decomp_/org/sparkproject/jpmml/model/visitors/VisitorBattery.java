package org.sparkproject.jpmml.model.visitors;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;

public class VisitorBattery extends ArrayList {
   public void applyTo(Visitable visitable) {
      for(Class visitorClazz : this) {
         Visitor visitor;
         try {
            Constructor<? extends Visitor> constructor = visitorClazz.getDeclaredConstructor();
            visitor = (Visitor)constructor.newInstance();
         } catch (ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
         }

         visitor.applyTo(visitable);
      }

   }
}
