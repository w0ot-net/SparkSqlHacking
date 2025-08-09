package org.fusesource.hawtjni.runtime;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NativeStats {
   private final HashMap snapshot;

   public NativeStats(StatsInterface... classes) {
      this((Collection)Arrays.asList(classes));
   }

   public NativeStats(Collection classes) {
      this(snapshot(classes));
   }

   private NativeStats(HashMap snapshot) {
      this.snapshot = snapshot;
   }

   public void reset() {
      for(ArrayList functions : this.snapshot.values()) {
         for(NativeFunction function : functions) {
            function.reset();
         }
      }

   }

   public void update() {
      for(Map.Entry entry : this.snapshot.entrySet()) {
         StatsInterface si = (StatsInterface)entry.getKey();

         for(NativeFunction function : (ArrayList)entry.getValue()) {
            function.setCounter(si.functionCounter(function.getOrdinal()));
         }
      }

   }

   public NativeStats snapshot() {
      NativeStats copy = this.copy();
      copy.update();
      return copy;
   }

   public NativeStats copy() {
      HashMap<StatsInterface, ArrayList<NativeFunction>> rc = new HashMap(this.snapshot.size() * 2);

      for(Map.Entry entry : this.snapshot.entrySet()) {
         ArrayList<NativeFunction> list = new ArrayList(((ArrayList)entry.getValue()).size());

         for(NativeFunction function : (ArrayList)entry.getValue()) {
            list.add(function.copy());
         }

         rc.put(entry.getKey(), list);
      }

      return new NativeStats(rc);
   }

   public NativeStats diff() {
      HashMap<StatsInterface, ArrayList<NativeFunction>> rc = new HashMap(this.snapshot.size() * 2);

      for(Map.Entry entry : this.snapshot.entrySet()) {
         StatsInterface si = (StatsInterface)entry.getKey();
         ArrayList<NativeFunction> list = new ArrayList(((ArrayList)entry.getValue()).size());

         for(NativeFunction original : (ArrayList)entry.getValue()) {
            NativeFunction copy = original.copy();
            copy.setCounter(si.functionCounter(copy.getOrdinal()));
            copy.subtract(original);
            list.add(copy);
         }

         rc.put(si, list);
      }

      return new NativeStats(rc);
   }

   public void dump(PrintStream ps) {
      boolean firstSI = true;

      for(Map.Entry entry : this.snapshot.entrySet()) {
         StatsInterface si = (StatsInterface)entry.getKey();
         ArrayList<NativeFunction> funcs = (ArrayList)entry.getValue();
         int total = 0;

         for(NativeFunction func : funcs) {
            total += func.getCounter();
         }

         if (!firstSI) {
            ps.print(", ");
         }

         firstSI = false;
         ps.print("[");
         if (total > 0) {
            ps.println("{ ");
            ps.println("  \"class\": \"" + si.getNativeClass() + "\",");
            ps.println("  \"total\": " + total + ", ");
            ps.print("  \"functions\": {");
            boolean firstFunc = true;

            for(NativeFunction func : funcs) {
               if (func.getCounter() > 0) {
                  if (!firstFunc) {
                     ps.print(",");
                  }

                  firstFunc = false;
                  ps.println();
                  ps.print("    \"" + func.getName() + "\": " + func.getCounter());
               }
            }

            ps.println();
            ps.println("  }");
            ps.print("}");
         }

         ps.print("]");
      }

   }

   private static HashMap snapshot(Collection classes) {
      HashMap<StatsInterface, ArrayList<NativeFunction>> rc = new HashMap();

      for(StatsInterface sc : classes) {
         int count = sc.functionCount();
         ArrayList<NativeFunction> functions = new ArrayList(count);

         for(int i = 0; i < count; ++i) {
            String name = sc.functionName(i);
            functions.add(new NativeFunction(i, name, 0));
         }

         Collections.sort(functions);
         rc.put(sc, functions);
      }

      return rc;
   }

   public static class NativeFunction implements Comparable {
      private final int ordinal;
      private final String name;
      private int counter;

      public NativeFunction(int ordinal, String name, int callCount) {
         this.ordinal = ordinal;
         this.name = name;
         this.counter = callCount;
      }

      void subtract(NativeFunction func) {
         this.counter -= func.counter;
      }

      public int getCounter() {
         return this.counter;
      }

      public void setCounter(int counter) {
         this.counter = counter;
      }

      public String getName() {
         return this.name;
      }

      public int getOrdinal() {
         return this.ordinal;
      }

      public int compareTo(NativeFunction func) {
         return func.counter - this.counter;
      }

      public void reset() {
         this.counter = 0;
      }

      public NativeFunction copy() {
         return new NativeFunction(this.ordinal, this.name, this.counter);
      }
   }

   public interface StatsInterface {
      String getNativeClass();

      int functionCount();

      String functionName(int var1);

      int functionCounter(int var1);
   }
}
