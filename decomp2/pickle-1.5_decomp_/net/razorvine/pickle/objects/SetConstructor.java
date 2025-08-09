package net.razorvine.pickle.objects;

import java.util.ArrayList;
import java.util.HashSet;
import net.razorvine.pickle.IObjectConstructor;

public class SetConstructor implements IObjectConstructor {
   public Object construct(Object[] args) {
      ArrayList<Object> data = (ArrayList)args[0];
      return new HashSet(data);
   }
}
