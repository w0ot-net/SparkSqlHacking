package net.razorvine.pickle.objects;

import java.util.HashMap;

public class ClassDict extends HashMap {
   private static final long serialVersionUID = 6157715596627049511L;
   private final String classname;

   public ClassDict(String modulename, String classname) {
      if (modulename == null) {
         this.classname = classname;
      } else {
         this.classname = modulename + "." + classname;
      }

      this.put("__class__", this.classname);
   }

   public void __setstate__(HashMap values) {
      this.clear();
      this.put("__class__", this.classname);
      this.putAll(values);
   }

   public String getClassName() {
      return this.classname;
   }
}
