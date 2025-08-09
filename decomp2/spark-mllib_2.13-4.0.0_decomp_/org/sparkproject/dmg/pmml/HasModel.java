package org.sparkproject.dmg.pmml;

import org.sparkproject.jpmml.model.UnsupportedElementException;

public interface HasModel {
   default Model requireModel(Class clazz) {
      Model model = this.requireModel();
      if (!clazz.isInstance(model)) {
         throw new UnsupportedElementException(model);
      } else {
         return (Model)clazz.cast(model);
      }
   }

   Model requireModel();

   Model getModel();

   PMMLObject setModel(Model var1);
}
