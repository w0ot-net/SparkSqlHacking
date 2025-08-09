package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class IcBands extends BandSet {
   private final Set innerClasses = new TreeSet();
   private final CpBands cpBands;
   private int bit16Count;
   private final Map outerToInner = new HashMap();

   public IcBands(SegmentHeader segmentHeader, CpBands cpBands, int effort) {
      super(effort, segmentHeader);
      this.cpBands = cpBands;
   }

   public void addInnerClass(String name, String outerName, String innerName, int flags) {
      if (outerName == null && innerName == null) {
         IcTuple innerClass = new IcTuple(this.cpBands.getCPClass(name), flags, (CPClass)null, (CPUTF8)null);
         this.addToMap(this.getOuter(name), innerClass);
         this.innerClasses.add(innerClass);
      } else if (this.namesArePredictable(name, outerName, innerName)) {
         IcTuple innerClass = new IcTuple(this.cpBands.getCPClass(name), flags, (CPClass)null, (CPUTF8)null);
         this.addToMap(outerName, innerClass);
         this.innerClasses.add(innerClass);
      } else {
         flags |= 65536;
         IcTuple icTuple = new IcTuple(this.cpBands.getCPClass(name), flags, this.cpBands.getCPClass(outerName), this.cpBands.getCPUtf8(innerName));
         boolean added = this.innerClasses.add(icTuple);
         if (added) {
            ++this.bit16Count;
            this.addToMap(outerName, icTuple);
         }
      }

   }

   private void addToMap(String outerName, IcTuple icTuple) {
      List<IcTuple> tuples = (List)this.outerToInner.get(outerName);
      if (tuples == null) {
         tuples = new ArrayList();
         this.outerToInner.put(outerName, tuples);
      } else {
         for(IcTuple tuple : tuples) {
            if (icTuple.equals(tuple)) {
               return;
            }
         }
      }

      tuples.add(icTuple);
   }

   public void finaliseBands() {
      this.segmentHeader.setIc_count(this.innerClasses.size());
   }

   public IcTuple getIcTuple(CPClass inner) {
      for(IcTuple icTuple : this.innerClasses) {
         if (icTuple.C.equals(inner)) {
            return icTuple;
         }
      }

      return null;
   }

   public List getInnerClassesForOuter(String outerClassName) {
      return (List)this.outerToInner.get(outerClassName);
   }

   private String getOuter(String name) {
      return name.substring(0, name.lastIndexOf(36));
   }

   private boolean namesArePredictable(String name, String outerName, String innerName) {
      return name.equals(outerName + '$' + innerName) && innerName.indexOf(36) == -1;
   }

   public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
      PackingUtils.log("Writing internal class bands...");
      int[] ic_this_class = new int[this.innerClasses.size()];
      int[] ic_flags = new int[this.innerClasses.size()];
      int[] ic_outer_class = new int[this.bit16Count];
      int[] ic_name = new int[this.bit16Count];
      int index2 = 0;
      List<IcTuple> innerClassesList = new ArrayList(this.innerClasses);

      for(int i = 0; i < ic_this_class.length; ++i) {
         IcTuple icTuple = (IcTuple)innerClassesList.get(i);
         ic_this_class[i] = icTuple.C.getIndex();
         ic_flags[i] = icTuple.F;
         if ((icTuple.F & 65536) != 0) {
            ic_outer_class[index2] = icTuple.C2 == null ? 0 : icTuple.C2.getIndex() + 1;
            ic_name[index2] = icTuple.N == null ? 0 : icTuple.N.getIndex() + 1;
            ++index2;
         }
      }

      byte[] encodedBand = this.encodeBandInt("ic_this_class", ic_this_class, Codec.UDELTA5);
      outputStream.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from ic_this_class[" + ic_this_class.length + "]");
      encodedBand = this.encodeBandInt("ic_flags", ic_flags, Codec.UNSIGNED5);
      outputStream.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from ic_flags[" + ic_flags.length + "]");
      encodedBand = this.encodeBandInt("ic_outer_class", ic_outer_class, Codec.DELTA5);
      outputStream.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from ic_outer_class[" + ic_outer_class.length + "]");
      encodedBand = this.encodeBandInt("ic_name", ic_name, Codec.DELTA5);
      outputStream.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from ic_name[" + ic_name.length + "]");
   }

   static class IcTuple implements Comparable {
      protected CPClass C;
      protected int F;
      protected CPClass C2;
      protected CPUTF8 N;

      IcTuple(CPClass C, int F, CPClass C2, CPUTF8 N) {
         this.C = C;
         this.F = F;
         this.C2 = C2;
         this.N = N;
      }

      public int compareTo(IcTuple arg0) {
         return this.C.compareTo(arg0.C);
      }

      public boolean equals(Object o) {
         if (!(o instanceof IcTuple)) {
            return false;
         } else {
            IcTuple icT = (IcTuple)o;
            return this.C.equals(icT.C) && this.F == icT.F && Objects.equals(this.C2, icT.C2) && Objects.equals(this.N, icT.N);
         }
      }

      public boolean isAnonymous() {
         String className = this.C.toString();
         String innerName = className.substring(className.lastIndexOf(36) + 1);
         return Character.isDigit(innerName.charAt(0));
      }

      public String toString() {
         return this.C.toString();
      }
   }
}
