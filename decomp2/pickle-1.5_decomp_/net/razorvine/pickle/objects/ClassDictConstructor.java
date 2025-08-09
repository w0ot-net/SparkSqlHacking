package net.razorvine.pickle.objects;

import net.razorvine.pickle.IObjectConstructor;
import net.razorvine.pickle.PickleException;

public class ClassDictConstructor implements IObjectConstructor {
   final String module;
   final String name;

   public ClassDictConstructor(String module, String name) {
      this.module = module;
      this.name = name;
   }

   public Object construct(Object[] args) {
      if (args.length > 0) {
         throw new PickleException("expected zero arguments for construction of ClassDict (for " + this.module + "." + this.name + "). This happens when an unsupported/unregistered class is being unpickled that requires construction arguments. Fix it by registering a custom IObjectConstructor for this class.");
      } else {
         return new ClassDict(this.module, this.name);
      }
   }
}
