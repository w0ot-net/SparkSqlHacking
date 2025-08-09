package javassist.bytecode.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subroutine {
   private List callers = new ArrayList();
   private Set access = new HashSet();
   private int start;

   public Subroutine(int start, int caller) {
      this.start = start;
      this.callers.add(caller);
   }

   public void addCaller(int caller) {
      this.callers.add(caller);
   }

   public int start() {
      return this.start;
   }

   public void access(int index) {
      this.access.add(index);
   }

   public boolean isAccessed(int index) {
      return this.access.contains(index);
   }

   public Collection accessed() {
      return this.access;
   }

   public Collection callers() {
      return this.callers;
   }

   public String toString() {
      return "start = " + this.start + " callers = " + this.callers.toString();
   }
}
