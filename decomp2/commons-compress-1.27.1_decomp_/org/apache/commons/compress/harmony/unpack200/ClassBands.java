package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantValueAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.DeprecatedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.EnclosingMethodAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LineNumberTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTypeTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SignatureAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;

public class ClassBands extends BandSet {
   private int[] classFieldCount;
   private long[] classFlags;
   private long[] classAccessFlags;
   private int[][] classInterfacesInts;
   private int[] classMethodCount;
   private int[] classSuperInts;
   private String[] classThis;
   private int[] classThisInts;
   private ArrayList[] classAttributes;
   private int[] classVersionMajor;
   private int[] classVersionMinor;
   private IcTuple[][] icLocal;
   private List[] codeAttributes;
   private int[] codeHandlerCount;
   private int[] codeMaxNALocals;
   private int[] codeMaxStack;
   private ArrayList[][] fieldAttributes;
   private String[][] fieldDescr;
   private int[][] fieldDescrInts;
   private long[][] fieldFlags;
   private long[][] fieldAccessFlags;
   private ArrayList[][] methodAttributes;
   private String[][] methodDescr;
   private int[][] methodDescrInts;
   private long[][] methodFlags;
   private long[][] methodAccessFlags;
   private final AttributeLayoutMap attrMap;
   private final CpBands cpBands;
   private final SegmentOptions options;
   private final int classCount;
   private int[] methodAttrCalls;
   private int[][] codeHandlerStartP;
   private int[][] codeHandlerEndPO;
   private int[][] codeHandlerCatchPO;
   private int[][] codeHandlerClassRCN;
   private boolean[] codeHasAttributes;

   public ClassBands(Segment segment) {
      super(segment);
      this.attrMap = segment.getAttrDefinitionBands().getAttributeDefinitionMap();
      this.cpBands = segment.getCpBands();
      this.classCount = this.header.getClassCount();
      this.options = this.header.getOptions();
   }

   private int getCallCount(int[][] methodAttrIndexes, long[][] flags, int context) {
      int callCount = 0;

      for(int[] element : methodAttrIndexes) {
         for(int index : element) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(index, context);
            callCount += layout.numBackwardsCallables();
         }
      }

      int layoutsUsed = 0;

      for(long[] flag : flags) {
         for(long element : flag) {
            layoutsUsed = (int)((long)layoutsUsed | element);
         }
      }

      for(int i = 0; i < 26; ++i) {
         if ((layoutsUsed & 1 << i) != 0) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(i, context);
            callCount += layout.numBackwardsCallables();
         }
      }

      return callCount;
   }

   public ArrayList[] getClassAttributes() {
      return this.classAttributes;
   }

   public int[] getClassFieldCount() {
      return this.classFieldCount;
   }

   public long[] getClassFlags() {
      if (this.classAccessFlags == null) {
         long mask = 32767L;

         for(int i = 0; i < 16; ++i) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(i, 0);
            if (layout != null && !layout.isDefaultLayout()) {
               mask &= (long)(~(1 << i));
            }
         }

         this.classAccessFlags = new long[this.classFlags.length];

         for(int i = 0; i < this.classFlags.length; ++i) {
            this.classAccessFlags[i] = this.classFlags[i] & mask;
         }
      }

      return this.classAccessFlags;
   }

   public int[][] getClassInterfacesInts() {
      return this.classInterfacesInts;
   }

   public int[] getClassMethodCount() {
      return this.classMethodCount;
   }

   public int[] getClassSuperInts() {
      return this.classSuperInts;
   }

   public int[] getClassThisInts() {
      return this.classThisInts;
   }

   public int[] getClassVersionMajor() {
      return this.classVersionMajor;
   }

   public int[] getClassVersionMinor() {
      return this.classVersionMinor;
   }

   public int[][] getCodeHandlerCatchPO() {
      return this.codeHandlerCatchPO;
   }

   public int[][] getCodeHandlerClassRCN() {
      return this.codeHandlerClassRCN;
   }

   public int[] getCodeHandlerCount() {
      return this.codeHandlerCount;
   }

   public int[][] getCodeHandlerEndPO() {
      return this.codeHandlerEndPO;
   }

   public int[][] getCodeHandlerStartP() {
      return this.codeHandlerStartP;
   }

   public boolean[] getCodeHasAttributes() {
      return this.codeHasAttributes;
   }

   public int[] getCodeMaxNALocals() {
      return this.codeMaxNALocals;
   }

   public int[] getCodeMaxStack() {
      return this.codeMaxStack;
   }

   public ArrayList[][] getFieldAttributes() {
      return this.fieldAttributes;
   }

   public int[][] getFieldDescrInts() {
      return this.fieldDescrInts;
   }

   public long[][] getFieldFlags() {
      if (this.fieldAccessFlags == null) {
         long mask = 32767L;

         for(int i = 0; i < 16; ++i) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(i, 1);
            if (layout != null && !layout.isDefaultLayout()) {
               mask &= (long)(~(1 << i));
            }
         }

         this.fieldAccessFlags = new long[this.fieldFlags.length][];

         for(int i = 0; i < this.fieldFlags.length; ++i) {
            this.fieldAccessFlags[i] = new long[this.fieldFlags[i].length];

            for(int j = 0; j < this.fieldFlags[i].length; ++j) {
               this.fieldAccessFlags[i][j] = this.fieldFlags[i][j] & mask;
            }
         }
      }

      return this.fieldAccessFlags;
   }

   public IcTuple[][] getIcLocal() {
      return this.icLocal;
   }

   public ArrayList[][] getMethodAttributes() {
      return this.methodAttributes;
   }

   public String[][] getMethodDescr() {
      return this.methodDescr;
   }

   public int[][] getMethodDescrInts() {
      return this.methodDescrInts;
   }

   public long[][] getMethodFlags() {
      if (this.methodAccessFlags == null) {
         long mask = 32767L;

         for(int i = 0; i < 16; ++i) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(i, 2);
            if (layout != null && !layout.isDefaultLayout()) {
               mask &= (long)(~(1 << i));
            }
         }

         this.methodAccessFlags = new long[this.methodFlags.length][];

         for(int i = 0; i < this.methodFlags.length; ++i) {
            this.methodAccessFlags[i] = new long[this.methodFlags[i].length];

            for(int j = 0; j < this.methodFlags[i].length; ++j) {
               this.methodAccessFlags[i][j] = this.methodFlags[i][j] & mask;
            }
         }
      }

      return this.methodAccessFlags;
   }

   public ArrayList getOrderedCodeAttributes() {
      return (ArrayList)Stream.of(this.codeAttributes).map(ArrayList::new).collect(Collectors.toCollection(ArrayList::new));
   }

   public long[] getRawClassFlags() {
      return this.classFlags;
   }

   private void parseClassAttrBands(InputStream in) throws IOException, Pack200Exception {
      String[] cpUTF8 = this.cpBands.getCpUTF8();
      String[] cpClass = this.cpBands.getCpClass();
      this.classAttributes = new ArrayList[this.classCount];
      Arrays.setAll(this.classAttributes, (ix) -> new ArrayList());
      this.classFlags = this.parseFlags("class_flags", in, this.classCount, Codec.UNSIGNED5, this.options.hasClassFlagsHi());
      int classAttrCount = SegmentUtils.countBit16(this.classFlags);
      int[] classAttrCounts = this.decodeBandInt("class_attr_count", in, Codec.UNSIGNED5, classAttrCount);
      int[][] classAttrIndexes = this.decodeBandInt("class_attr_indexes", in, Codec.UNSIGNED5, classAttrCounts);
      int callCount = this.getCallCount(classAttrIndexes, new long[][]{this.classFlags}, 0);
      int[] classAttrCalls = this.decodeBandInt("class_attr_calls", in, Codec.UNSIGNED5, callCount);
      AttributeLayout deprecatedLayout = this.attrMap.getAttributeLayout("Deprecated", 0);
      AttributeLayout sourceFileLayout = this.attrMap.getAttributeLayout("SourceFile", 0);
      int sourceFileCount = SegmentUtils.countMatches((long[])this.classFlags, sourceFileLayout);
      int[] classSourceFile = this.decodeBandInt("class_SourceFile_RUN", in, Codec.UNSIGNED5, sourceFileCount);
      AttributeLayout enclosingMethodLayout = this.attrMap.getAttributeLayout("EnclosingMethod", 0);
      int enclosingMethodCount = SegmentUtils.countMatches((long[])this.classFlags, enclosingMethodLayout);
      int[] enclosingMethodRC = this.decodeBandInt("class_EnclosingMethod_RC", in, Codec.UNSIGNED5, enclosingMethodCount);
      int[] enclosingMethodRDN = this.decodeBandInt("class_EnclosingMethod_RDN", in, Codec.UNSIGNED5, enclosingMethodCount);
      AttributeLayout signatureLayout = this.attrMap.getAttributeLayout("Signature", 0);
      int signatureCount = SegmentUtils.countMatches((long[])this.classFlags, signatureLayout);
      int[] classSignature = this.decodeBandInt("class_Signature_RS", in, Codec.UNSIGNED5, signatureCount);
      int backwardsCallsUsed = this.parseClassMetadataBands(in, classAttrCalls);
      AttributeLayout innerClassLayout = this.attrMap.getAttributeLayout("InnerClasses", 0);
      int innerClassCount = SegmentUtils.countMatches((long[])this.classFlags, innerClassLayout);
      int[] classInnerClassesN = this.decodeBandInt("class_InnerClasses_N", in, Codec.UNSIGNED5, innerClassCount);
      int[][] classInnerClassesRC = this.decodeBandInt("class_InnerClasses_RC", in, Codec.UNSIGNED5, classInnerClassesN);
      int[][] classInnerClassesF = this.decodeBandInt("class_InnerClasses_F", in, Codec.UNSIGNED5, classInnerClassesN);
      int flagsCount = 0;

      for(int[] element : classInnerClassesF) {
         for(int element2 : element) {
            if (element2 != 0) {
               ++flagsCount;
            }
         }
      }

      int[] classInnerClassesOuterRCN = this.decodeBandInt("class_InnerClasses_outer_RCN", in, Codec.UNSIGNED5, flagsCount);
      int[] classInnerClassesNameRUN = this.decodeBandInt("class_InnerClasses_name_RUN", in, Codec.UNSIGNED5, flagsCount);
      AttributeLayout versionLayout = this.attrMap.getAttributeLayout("class-file version", 0);
      int versionCount = SegmentUtils.countMatches((long[])this.classFlags, versionLayout);
      int[] classFileVersionMinorH = this.decodeBandInt("class_file_version_minor_H", in, Codec.UNSIGNED5, versionCount);
      int[] classFileVersionMajorH = this.decodeBandInt("class_file_version_major_H", in, Codec.UNSIGNED5, versionCount);
      if (versionCount > 0) {
         this.classVersionMajor = new int[this.classCount];
         this.classVersionMinor = new int[this.classCount];
      }

      int defaultVersionMajor = this.header.getDefaultClassMajorVersion();
      int defaultVersionMinor = this.header.getDefaultClassMinorVersion();
      int backwardsCallIndex = backwardsCallsUsed;
      int limit = this.options.hasClassFlagsHi() ? 62 : 31;
      AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
      int[] counts = new int[limit + 1];
      List<Attribute>[] otherAttributes = new List[limit + 1];

      for(int i = 0; i < limit; ++i) {
         AttributeLayout layout = this.attrMap.getAttributeLayout(i, 0);
         if (layout != null && !layout.isDefaultLayout()) {
            otherLayouts[i] = layout;
            counts[i] = SegmentUtils.countMatches((long[])this.classFlags, layout);
         }
      }

      for(int i = 0; i < counts.length; ++i) {
         if (counts[i] > 0) {
            NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i]);
            otherAttributes[i] = bands.parseAttributes(in, counts[i]);
            int numBackwardsCallables = otherLayouts[i].numBackwardsCallables();
            if (numBackwardsCallables > 0) {
               int[] backwardsCalls = new int[numBackwardsCallables];
               System.arraycopy(classAttrCalls, backwardsCallIndex, backwardsCalls, 0, numBackwardsCallables);
               bands.setBackwardsCalls(backwardsCalls);
               backwardsCallIndex += numBackwardsCallables;
            }
         }
      }

      int sourceFileIndex = 0;
      int enclosingMethodIndex = 0;
      int signatureIndex = 0;
      int innerClassIndex = 0;
      int innerClassC2NIndex = 0;
      int versionIndex = 0;
      this.icLocal = new IcTuple[this.classCount][];

      for(int i = 0; i < this.classCount; ++i) {
         long flag = this.classFlags[i];
         if (deprecatedLayout.matches(this.classFlags[i])) {
            this.classAttributes[i].add(new DeprecatedAttribute());
         }

         if (sourceFileLayout.matches(flag)) {
            long result = (long)classSourceFile[sourceFileIndex];
            ClassFileEntry value = sourceFileLayout.getValue(result, this.cpBands.getConstantPool());
            if (value == null) {
               String className = this.classThis[i].substring(this.classThis[i].lastIndexOf(47) + 1);
               className = className.substring(className.lastIndexOf(46) + 1);
               char[] chars = className.toCharArray();
               int index = -1;

               for(int j = 0; j < chars.length; ++j) {
                  if (chars[j] <= '-') {
                     index = j;
                     break;
                  }
               }

               if (index > -1) {
                  className = className.substring(0, index);
               }

               value = this.cpBands.cpUTF8Value(className + ".java", true);
            }

            this.classAttributes[i].add(new SourceFileAttribute((CPUTF8)value));
            ++sourceFileIndex;
         }

         if (enclosingMethodLayout.matches(flag)) {
            CPClass theClass = this.cpBands.cpClassValue(enclosingMethodRC[enclosingMethodIndex]);
            CPNameAndType theMethod = null;
            if (enclosingMethodRDN[enclosingMethodIndex] != 0) {
               theMethod = this.cpBands.cpNameAndTypeValue(enclosingMethodRDN[enclosingMethodIndex] - 1);
            }

            this.classAttributes[i].add(new EnclosingMethodAttribute(theClass, theMethod));
            ++enclosingMethodIndex;
         }

         if (signatureLayout.matches(flag)) {
            long result = (long)classSignature[signatureIndex];
            CPUTF8 value = (CPUTF8)signatureLayout.getValue(result, this.cpBands.getConstantPool());
            this.classAttributes[i].add(new SignatureAttribute(value));
            ++signatureIndex;
         }

         if (innerClassLayout.matches(flag)) {
            this.icLocal[i] = new IcTuple[classInnerClassesN[innerClassIndex]];

            for(int j = 0; j < this.icLocal[i].length; ++j) {
               int icTupleCIndex = classInnerClassesRC[innerClassIndex][j];
               int icTupleC2Index = -1;
               int icTupleNIndex = -1;
               String icTupleC = cpClass[icTupleCIndex];
               int icTupleF = classInnerClassesF[innerClassIndex][j];
               String icTupleC2 = null;
               String icTupleN = null;
               if (icTupleF != 0) {
                  icTupleC2Index = classInnerClassesOuterRCN[innerClassC2NIndex];
                  icTupleNIndex = classInnerClassesNameRUN[innerClassC2NIndex];
                  icTupleC2 = cpClass[icTupleC2Index];
                  icTupleN = cpUTF8[icTupleNIndex];
                  ++innerClassC2NIndex;
               } else {
                  IcBands icBands = this.segment.getIcBands();
                  IcTuple[] icAll = icBands.getIcTuples();

                  for(IcTuple element : icAll) {
                     if (element.getC().equals(icTupleC)) {
                        icTupleF = element.getF();
                        icTupleC2 = element.getC2();
                        icTupleN = element.getN();
                        break;
                     }
                  }
               }

               IcTuple icTuple = new IcTuple(icTupleC, icTupleF, icTupleC2, icTupleN, icTupleCIndex, icTupleC2Index, icTupleNIndex, j);
               this.icLocal[i][j] = icTuple;
            }

            ++innerClassIndex;
         }

         if (versionLayout.matches(flag)) {
            this.classVersionMajor[i] = classFileVersionMajorH[versionIndex];
            this.classVersionMinor[i] = classFileVersionMinorH[versionIndex];
            ++versionIndex;
         } else if (this.classVersionMajor != null) {
            this.classVersionMajor[i] = defaultVersionMajor;
            this.classVersionMinor[i] = defaultVersionMinor;
         }

         for(int j = 0; j < otherLayouts.length; ++j) {
            if (otherLayouts[j] != null && otherLayouts[j].matches(flag)) {
               this.classAttributes[i].add((Attribute)otherAttributes[j].get(0));
               otherAttributes[j].remove(0);
            }
         }
      }

   }

   private int parseClassMetadataBands(InputStream in, int[] classAttrCalls) throws Pack200Exception, IOException {
      int numBackwardsCalls = 0;
      String[] RxA = new String[]{"RVA", "RIA"};
      AttributeLayout rvaLayout = this.attrMap.getAttributeLayout("RuntimeVisibleAnnotations", 0);
      AttributeLayout riaLayout = this.attrMap.getAttributeLayout("RuntimeInvisibleAnnotations", 0);
      int rvaCount = SegmentUtils.countMatches((long[])this.classFlags, rvaLayout);
      int riaCount = SegmentUtils.countMatches((long[])this.classFlags, riaLayout);
      int[] RxACount = new int[]{rvaCount, riaCount};
      int[] backwardsCalls = new int[]{0, 0};
      if (rvaCount > 0) {
         ++numBackwardsCalls;
         backwardsCalls[0] = classAttrCalls[0];
         if (riaCount > 0) {
            ++numBackwardsCalls;
            backwardsCalls[1] = classAttrCalls[1];
         }
      } else if (riaCount > 0) {
         ++numBackwardsCalls;
         backwardsCalls[1] = classAttrCalls[0];
      }

      MetadataBandGroup[] mbgs = this.parseMetadata(in, RxA, RxACount, backwardsCalls, "class");
      List<Attribute> rvaAttributes = mbgs[0].getAttributes();
      List<Attribute> riaAttributes = mbgs[1].getAttributes();
      int rvaAttributesIndex = 0;
      int riaAttributesIndex = 0;

      for(int i = 0; i < this.classFlags.length; ++i) {
         if (rvaLayout.matches(this.classFlags[i])) {
            this.classAttributes[i].add((Attribute)rvaAttributes.get(rvaAttributesIndex++));
         }

         if (riaLayout.matches(this.classFlags[i])) {
            this.classAttributes[i].add((Attribute)riaAttributes.get(riaAttributesIndex++));
         }
      }

      return numBackwardsCalls;
   }

   private void parseCodeAttrBands(InputStream in, int codeFlagsCount) throws IOException, Pack200Exception {
      long[] codeFlags = this.parseFlags("code_flags", in, codeFlagsCount, Codec.UNSIGNED5, this.segment.getSegmentHeader().getOptions().hasCodeFlagsHi());
      int codeAttrCount = SegmentUtils.countBit16(codeFlags);
      int[] codeAttrCounts = this.decodeBandInt("code_attr_count", in, Codec.UNSIGNED5, codeAttrCount);
      int[][] codeAttrIndexes = this.decodeBandInt("code_attr_indexes", in, Codec.UNSIGNED5, codeAttrCounts);
      int callCount = 0;

      for(int[] element : codeAttrIndexes) {
         for(int index : element) {
            AttributeLayout layout = this.attrMap.getAttributeLayout(index, 3);
            callCount += layout.numBackwardsCallables();
         }
      }

      int[] codeAttrCalls = this.decodeBandInt("code_attr_calls", in, Codec.UNSIGNED5, callCount);
      AttributeLayout lineNumberTableLayout = this.attrMap.getAttributeLayout("LineNumberTable", 3);
      int lineNumberTableCount = SegmentUtils.countMatches((long[])codeFlags, lineNumberTableLayout);
      int[] lineNumberTableN = this.decodeBandInt("code_LineNumberTable_N", in, Codec.UNSIGNED5, lineNumberTableCount);
      int[][] lineNumberTableBciP = this.decodeBandInt("code_LineNumberTable_bci_P", in, Codec.BCI5, lineNumberTableN);
      int[][] lineNumberTableLine = this.decodeBandInt("code_LineNumberTable_line", in, Codec.UNSIGNED5, lineNumberTableN);
      AttributeLayout localVariableTableLayout = this.attrMap.getAttributeLayout("LocalVariableTable", 3);
      AttributeLayout localVariableTypeTableLayout = this.attrMap.getAttributeLayout("LocalVariableTypeTable", 3);
      int lengthLocalVariableNBand = SegmentUtils.countMatches((long[])codeFlags, localVariableTableLayout);
      int[] localVariableTableN = this.decodeBandInt("code_LocalVariableTable_N", in, Codec.UNSIGNED5, lengthLocalVariableNBand);
      int[][] localVariableTableBciP = this.decodeBandInt("code_LocalVariableTable_bci_P", in, Codec.BCI5, localVariableTableN);
      int[][] localVariableTableSpanO = this.decodeBandInt("code_LocalVariableTable_span_O", in, Codec.BRANCH5, localVariableTableN);
      CPUTF8[][] localVariableTableNameRU = this.parseCPUTF8References("code_LocalVariableTable_name_RU", in, Codec.UNSIGNED5, localVariableTableN);
      CPUTF8[][] localVariableTableTypeRS = this.parseCPSignatureReferences("code_LocalVariableTable_type_RS", in, Codec.UNSIGNED5, localVariableTableN);
      int[][] localVariableTableSlot = this.decodeBandInt("code_LocalVariableTable_slot", in, Codec.UNSIGNED5, localVariableTableN);
      int lengthLocalVariableTypeTableNBand = SegmentUtils.countMatches((long[])codeFlags, localVariableTypeTableLayout);
      int[] localVariableTypeTableN = this.decodeBandInt("code_LocalVariableTypeTable_N", in, Codec.UNSIGNED5, lengthLocalVariableTypeTableNBand);
      int[][] localVariableTypeTableBciP = this.decodeBandInt("code_LocalVariableTypeTable_bci_P", in, Codec.BCI5, localVariableTypeTableN);
      int[][] localVariableTypeTableSpanO = this.decodeBandInt("code_LocalVariableTypeTable_span_O", in, Codec.BRANCH5, localVariableTypeTableN);
      CPUTF8[][] localVariableTypeTableNameRU = this.parseCPUTF8References("code_LocalVariableTypeTable_name_RU", in, Codec.UNSIGNED5, localVariableTypeTableN);
      CPUTF8[][] localVariableTypeTableTypeRS = this.parseCPSignatureReferences("code_LocalVariableTypeTable_type_RS", in, Codec.UNSIGNED5, localVariableTypeTableN);
      int[][] localVariableTypeTableSlot = this.decodeBandInt("code_LocalVariableTypeTable_slot", in, Codec.UNSIGNED5, localVariableTypeTableN);
      int backwardsCallIndex = 0;
      int limit = this.options.hasCodeFlagsHi() ? 62 : 31;
      AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
      int[] counts = new int[limit + 1];
      List<Attribute>[] otherAttributes = new List[limit + 1];

      for(int i = 0; i < limit; ++i) {
         AttributeLayout layout = this.attrMap.getAttributeLayout(i, 3);
         if (layout != null && !layout.isDefaultLayout()) {
            otherLayouts[i] = layout;
            counts[i] = SegmentUtils.countMatches((long[])codeFlags, layout);
         }
      }

      for(int i = 0; i < counts.length; ++i) {
         if (counts[i] > 0) {
            NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i]);
            otherAttributes[i] = bands.parseAttributes(in, counts[i]);
            int numBackwardsCallables = otherLayouts[i].numBackwardsCallables();
            if (numBackwardsCallables > 0) {
               int[] backwardsCalls = new int[numBackwardsCallables];
               System.arraycopy(codeAttrCalls, backwardsCallIndex, backwardsCalls, 0, numBackwardsCallables);
               bands.setBackwardsCalls(backwardsCalls);
               backwardsCallIndex += numBackwardsCallables;
            }
         }
      }

      int lineNumberIndex = 0;
      int lvtIndex = 0;
      int lvttIndex = 0;

      for(int i = 0; i < codeFlagsCount; ++i) {
         if (lineNumberTableLayout.matches(codeFlags[i])) {
            LineNumberTableAttribute lnta = new LineNumberTableAttribute(lineNumberTableN[lineNumberIndex], lineNumberTableBciP[lineNumberIndex], lineNumberTableLine[lineNumberIndex]);
            ++lineNumberIndex;
            this.codeAttributes[i].add(lnta);
         }

         if (localVariableTableLayout.matches(codeFlags[i])) {
            LocalVariableTableAttribute lvta = new LocalVariableTableAttribute(localVariableTableN[lvtIndex], localVariableTableBciP[lvtIndex], localVariableTableSpanO[lvtIndex], localVariableTableNameRU[lvtIndex], localVariableTableTypeRS[lvtIndex], localVariableTableSlot[lvtIndex]);
            ++lvtIndex;
            this.codeAttributes[i].add(lvta);
         }

         if (localVariableTypeTableLayout.matches(codeFlags[i])) {
            LocalVariableTypeTableAttribute lvtta = new LocalVariableTypeTableAttribute(localVariableTypeTableN[lvttIndex], localVariableTypeTableBciP[lvttIndex], localVariableTypeTableSpanO[lvttIndex], localVariableTypeTableNameRU[lvttIndex], localVariableTypeTableTypeRS[lvttIndex], localVariableTypeTableSlot[lvttIndex]);
            ++lvttIndex;
            this.codeAttributes[i].add(lvtta);
         }

         for(int j = 0; j < otherLayouts.length; ++j) {
            if (otherLayouts[j] != null && otherLayouts[j].matches(codeFlags[i])) {
               this.codeAttributes[i].add((Attribute)otherAttributes[j].get(0));
               otherAttributes[j].remove(0);
            }
         }
      }

   }

   private void parseCodeBands(InputStream in) throws Pack200Exception, IOException {
      AttributeLayout layout = this.attrMap.getAttributeLayout("Code", 2);
      int codeCount = SegmentUtils.countMatches((long[][])this.methodFlags, layout);
      int[] codeHeaders = this.decodeBandInt("code_headers", in, Codec.BYTE1, codeCount);
      boolean allCodeHasFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
      if (!allCodeHasFlags) {
         this.codeHasAttributes = new boolean[codeCount];
      }

      int codeSpecialHeader = 0;

      for(int i = 0; i < codeCount; ++i) {
         if (codeHeaders[i] == 0) {
            ++codeSpecialHeader;
            if (!allCodeHasFlags) {
               this.codeHasAttributes[i] = true;
            }
         }
      }

      int[] codeMaxStackSpecials = this.decodeBandInt("code_max_stack", in, Codec.UNSIGNED5, codeSpecialHeader);
      int[] codeMaxNALocalsSpecials = this.decodeBandInt("code_max_na_locals", in, Codec.UNSIGNED5, codeSpecialHeader);
      int[] codeHandlerCountSpecials = this.decodeBandInt("code_handler_count", in, Codec.UNSIGNED5, codeSpecialHeader);
      this.codeMaxStack = new int[codeCount];
      this.codeMaxNALocals = new int[codeCount];
      this.codeHandlerCount = new int[codeCount];
      int special = 0;

      for(int i = 0; i < codeCount; ++i) {
         int header = 255 & codeHeaders[i];
         if (header < 0) {
            throw new IllegalStateException("Shouldn't get here");
         }

         if (header == 0) {
            this.codeMaxStack[i] = codeMaxStackSpecials[special];
            this.codeMaxNALocals[i] = codeMaxNALocalsSpecials[special];
            this.codeHandlerCount[i] = codeHandlerCountSpecials[special];
            ++special;
         } else if (header <= 144) {
            this.codeMaxStack[i] = (header - 1) % 12;
            this.codeMaxNALocals[i] = (header - 1) / 12;
            this.codeHandlerCount[i] = 0;
         } else if (header <= 208) {
            this.codeMaxStack[i] = (header - 145) % 8;
            this.codeMaxNALocals[i] = (header - 145) / 8;
            this.codeHandlerCount[i] = 1;
         } else {
            if (header > 255) {
               throw new IllegalStateException("Shouldn't get here either");
            }

            this.codeMaxStack[i] = (header - 209) % 7;
            this.codeMaxNALocals[i] = (header - 209) / 7;
            this.codeHandlerCount[i] = 2;
         }
      }

      this.codeHandlerStartP = this.decodeBandInt("code_handler_start_P", in, Codec.BCI5, this.codeHandlerCount);
      this.codeHandlerEndPO = this.decodeBandInt("code_handler_end_PO", in, Codec.BRANCH5, this.codeHandlerCount);
      this.codeHandlerCatchPO = this.decodeBandInt("code_handler_catch_PO", in, Codec.BRANCH5, this.codeHandlerCount);
      this.codeHandlerClassRCN = this.decodeBandInt("code_handler_class_RCN", in, Codec.UNSIGNED5, this.codeHandlerCount);
      int codeFlagsCount = allCodeHasFlags ? codeCount : codeSpecialHeader;
      this.codeAttributes = new List[codeFlagsCount];
      Arrays.setAll(this.codeAttributes, (ix) -> new ArrayList());
      this.parseCodeAttrBands(in, codeFlagsCount);
   }

   private void parseFieldAttrBands(InputStream in) throws IOException, Pack200Exception {
      this.fieldFlags = this.parseFlags("field_flags", in, this.classFieldCount, Codec.UNSIGNED5, this.options.hasFieldFlagsHi());
      int fieldAttrCount = SegmentUtils.countBit16(this.fieldFlags);
      int[] fieldAttrCounts = this.decodeBandInt("field_attr_count", in, Codec.UNSIGNED5, fieldAttrCount);
      int[][] fieldAttrIndexes = this.decodeBandInt("field_attr_indexes", in, Codec.UNSIGNED5, fieldAttrCounts);
      int callCount = this.getCallCount(fieldAttrIndexes, this.fieldFlags, 1);
      int[] fieldAttrCalls = this.decodeBandInt("field_attr_calls", in, Codec.UNSIGNED5, callCount);
      this.fieldAttributes = new ArrayList[this.classCount][];

      for(int i = 0; i < this.classCount; ++i) {
         this.fieldAttributes[i] = new ArrayList[this.fieldFlags[i].length];

         for(int j = 0; j < this.fieldFlags[i].length; ++j) {
            this.fieldAttributes[i][j] = new ArrayList();
         }
      }

      AttributeLayout constantValueLayout = this.attrMap.getAttributeLayout("ConstantValue", 1);
      int constantCount = SegmentUtils.countMatches((long[][])this.fieldFlags, constantValueLayout);
      int[] field_constantValue_KQ = this.decodeBandInt("field_ConstantValue_KQ", in, Codec.UNSIGNED5, constantCount);
      int constantValueIndex = 0;
      AttributeLayout signatureLayout = this.attrMap.getAttributeLayout("Signature", 1);
      int signatureCount = SegmentUtils.countMatches((long[][])this.fieldFlags, signatureLayout);
      int[] fieldSignatureRS = this.decodeBandInt("field_Signature_RS", in, Codec.UNSIGNED5, signatureCount);
      int signatureIndex = 0;
      AttributeLayout deprecatedLayout = this.attrMap.getAttributeLayout("Deprecated", 1);

      for(int i = 0; i < this.classCount; ++i) {
         for(int j = 0; j < this.fieldFlags[i].length; ++j) {
            long flag = this.fieldFlags[i][j];
            if (deprecatedLayout.matches(flag)) {
               this.fieldAttributes[i][j].add(new DeprecatedAttribute());
            }

            if (constantValueLayout.matches(flag)) {
               long result = (long)field_constantValue_KQ[constantValueIndex];
               String desc = this.fieldDescr[i][j];
               int colon = desc.indexOf(58);
               String type = desc.substring(colon + 1);
               if (type.equals("B") || type.equals("S") || type.equals("C") || type.equals("Z")) {
                  type = "I";
               }

               ClassFileEntry value = constantValueLayout.getValue(result, type, this.cpBands.getConstantPool());
               this.fieldAttributes[i][j].add(new ConstantValueAttribute(value));
               ++constantValueIndex;
            }

            if (signatureLayout.matches(flag)) {
               long result = (long)fieldSignatureRS[signatureIndex];
               String desc = this.fieldDescr[i][j];
               int colon = desc.indexOf(58);
               String type = desc.substring(colon + 1);
               CPUTF8 value = (CPUTF8)signatureLayout.getValue(result, type, this.cpBands.getConstantPool());
               this.fieldAttributes[i][j].add(new SignatureAttribute(value));
               ++signatureIndex;
            }
         }
      }

      int backwardsCallIndex = this.parseFieldMetadataBands(in, fieldAttrCalls);
      int limit = this.options.hasFieldFlagsHi() ? 62 : 31;
      AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
      int[] counts = new int[limit + 1];
      List<Attribute>[] otherAttributes = new List[limit + 1];

      for(int i = 0; i < limit; ++i) {
         AttributeLayout layout = this.attrMap.getAttributeLayout(i, 1);
         if (layout != null && !layout.isDefaultLayout()) {
            otherLayouts[i] = layout;
            counts[i] = SegmentUtils.countMatches((long[][])this.fieldFlags, layout);
         }
      }

      for(int i = 0; i < counts.length; ++i) {
         if (counts[i] > 0) {
            NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i]);
            otherAttributes[i] = bands.parseAttributes(in, counts[i]);
            int numBackwardsCallables = otherLayouts[i].numBackwardsCallables();
            if (numBackwardsCallables > 0) {
               int[] backwardsCalls = new int[numBackwardsCallables];
               System.arraycopy(fieldAttrCalls, backwardsCallIndex, backwardsCalls, 0, numBackwardsCallables);
               bands.setBackwardsCalls(backwardsCalls);
               backwardsCallIndex += numBackwardsCallables;
            }
         }
      }

      for(int i = 0; i < this.classCount; ++i) {
         for(int j = 0; j < this.fieldFlags[i].length; ++j) {
            long flag = this.fieldFlags[i][j];
            int othersAddedAtStart = 0;

            for(int k = 0; k < otherLayouts.length; ++k) {
               if (otherLayouts[k] != null && otherLayouts[k].matches(flag)) {
                  if (otherLayouts[k].getIndex() < 15) {
                     this.fieldAttributes[i][j].add(othersAddedAtStart++, (Attribute)otherAttributes[k].get(0));
                  } else {
                     this.fieldAttributes[i][j].add((Attribute)otherAttributes[k].get(0));
                  }

                  otherAttributes[k].remove(0);
               }
            }
         }
      }

   }

   private void parseFieldBands(InputStream in) throws IOException, Pack200Exception {
      this.fieldDescrInts = this.decodeBandInt("field_descr", in, Codec.DELTA5, this.classFieldCount);
      this.fieldDescr = this.getReferences(this.fieldDescrInts, this.cpBands.getCpDescriptor());
      this.parseFieldAttrBands(in);
   }

   private int parseFieldMetadataBands(InputStream in, int[] fieldAttrCalls) throws Pack200Exception, IOException {
      int backwardsCallsUsed = 0;
      String[] RxA = new String[]{"RVA", "RIA"};
      AttributeLayout rvaLayout = this.attrMap.getAttributeLayout("RuntimeVisibleAnnotations", 1);
      AttributeLayout riaLayout = this.attrMap.getAttributeLayout("RuntimeInvisibleAnnotations", 1);
      int rvaCount = SegmentUtils.countMatches((long[][])this.fieldFlags, rvaLayout);
      int riaCount = SegmentUtils.countMatches((long[][])this.fieldFlags, riaLayout);
      int[] RxACount = new int[]{rvaCount, riaCount};
      int[] backwardsCalls = new int[]{0, 0};
      if (rvaCount > 0) {
         backwardsCalls[0] = fieldAttrCalls[0];
         ++backwardsCallsUsed;
         if (riaCount > 0) {
            backwardsCalls[1] = fieldAttrCalls[1];
            ++backwardsCallsUsed;
         }
      } else if (riaCount > 0) {
         backwardsCalls[1] = fieldAttrCalls[0];
         ++backwardsCallsUsed;
      }

      MetadataBandGroup[] mb = this.parseMetadata(in, RxA, RxACount, backwardsCalls, "field");
      List<Attribute> rvaAttributes = mb[0].getAttributes();
      List<Attribute> riaAttributes = mb[1].getAttributes();
      int rvaAttributesIndex = 0;
      int riaAttributesIndex = 0;

      for(int i = 0; i < this.fieldFlags.length; ++i) {
         for(int j = 0; j < this.fieldFlags[i].length; ++j) {
            if (rvaLayout.matches(this.fieldFlags[i][j])) {
               this.fieldAttributes[i][j].add((Attribute)rvaAttributes.get(rvaAttributesIndex++));
            }

            if (riaLayout.matches(this.fieldFlags[i][j])) {
               this.fieldAttributes[i][j].add((Attribute)riaAttributes.get(riaAttributesIndex++));
            }
         }
      }

      return backwardsCallsUsed;
   }

   private MetadataBandGroup[] parseMetadata(InputStream in, String[] RxA, int[] RxACount, int[] backwardsCallCounts, String contextName) throws IOException, Pack200Exception {
      MetadataBandGroup[] mbg = new MetadataBandGroup[RxA.length];

      for(int i = 0; i < RxA.length; ++i) {
         mbg[i] = new MetadataBandGroup(RxA[i], this.cpBands);
         String rxa = RxA[i];
         if (rxa.indexOf(80) >= 0) {
            mbg[i].param_NB = this.decodeBandInt(contextName + "_" + rxa + "_param_NB", in, Codec.BYTE1, RxACount[i]);
         }

         int pairCount = 0;
         if (rxa.equals("AD")) {
            pairCount = RxACount[i];
         } else {
            mbg[i].anno_N = this.decodeBandInt(contextName + "_" + rxa + "_anno_N", in, Codec.UNSIGNED5, RxACount[i]);
            mbg[i].type_RS = this.parseCPSignatureReferences(contextName + "_" + rxa + "_type_RS", in, Codec.UNSIGNED5, mbg[i].anno_N);
            mbg[i].pair_N = this.decodeBandInt(contextName + "_" + rxa + "_pair_N", in, Codec.UNSIGNED5, mbg[i].anno_N);

            for(int[] element : mbg[i].pair_N) {
               for(int element2 : element) {
                  pairCount += element2;
               }
            }

            mbg[i].name_RU = this.parseCPUTF8References(contextName + "_" + rxa + "_name_RU", in, Codec.UNSIGNED5, pairCount);
         }

         mbg[i].T = this.decodeBandInt(contextName + "_" + rxa + "_T", in, Codec.BYTE1, pairCount + backwardsCallCounts[i]);
         int ICount = 0;
         int DCount = 0;
         int FCount = 0;
         int JCount = 0;
         int cCount = 0;
         int eCount = 0;
         int sCount = 0;
         int arrayCount = 0;
         int atCount = 0;

         for(int element : mbg[i].T) {
            char c = (char)element;
            switch (c) {
               case '@':
                  ++atCount;
               case 'A':
               case 'E':
               case 'G':
               case 'H':
               case 'K':
               case 'L':
               case 'M':
               case 'N':
               case 'O':
               case 'P':
               case 'Q':
               case 'R':
               case 'T':
               case 'U':
               case 'V':
               case 'W':
               case 'X':
               case 'Y':
               case '\\':
               case ']':
               case '^':
               case '_':
               case '`':
               case 'a':
               case 'b':
               case 'd':
               case 'f':
               case 'g':
               case 'h':
               case 'i':
               case 'j':
               case 'k':
               case 'l':
               case 'm':
               case 'n':
               case 'o':
               case 'p':
               case 'q':
               case 'r':
               default:
                  break;
               case 'B':
               case 'C':
               case 'I':
               case 'S':
               case 'Z':
                  ++ICount;
                  break;
               case 'D':
                  ++DCount;
                  break;
               case 'F':
                  ++FCount;
                  break;
               case 'J':
                  ++JCount;
                  break;
               case '[':
                  ++arrayCount;
                  break;
               case 'c':
                  ++cCount;
                  break;
               case 'e':
                  ++eCount;
                  break;
               case 's':
                  ++sCount;
            }
         }

         mbg[i].caseI_KI = this.parseCPIntReferences(contextName + "_" + rxa + "_caseI_KI", in, Codec.UNSIGNED5, ICount);
         mbg[i].caseD_KD = this.parseCPDoubleReferences(contextName + "_" + rxa + "_caseD_KD", in, Codec.UNSIGNED5, DCount);
         mbg[i].caseF_KF = this.parseCPFloatReferences(contextName + "_" + rxa + "_caseF_KF", in, Codec.UNSIGNED5, FCount);
         mbg[i].caseJ_KJ = this.parseCPLongReferences(contextName + "_" + rxa + "_caseJ_KJ", in, Codec.UNSIGNED5, JCount);
         mbg[i].casec_RS = this.parseCPSignatureReferences(contextName + "_" + rxa + "_casec_RS", in, Codec.UNSIGNED5, cCount);
         mbg[i].caseet_RS = this.parseReferences(contextName + "_" + rxa + "_caseet_RS", in, Codec.UNSIGNED5, eCount, this.cpBands.getCpSignature());
         mbg[i].caseec_RU = this.parseReferences(contextName + "_" + rxa + "_caseec_RU", in, Codec.UNSIGNED5, eCount, this.cpBands.getCpUTF8());
         mbg[i].cases_RU = this.parseCPUTF8References(contextName + "_" + rxa + "_cases_RU", in, Codec.UNSIGNED5, sCount);
         mbg[i].casearray_N = this.decodeBandInt(contextName + "_" + rxa + "_casearray_N", in, Codec.UNSIGNED5, arrayCount);
         mbg[i].nesttype_RS = this.parseCPUTF8References(contextName + "_" + rxa + "_nesttype_RS", in, Codec.UNSIGNED5, atCount);
         mbg[i].nestpair_N = this.decodeBandInt(contextName + "_" + rxa + "_nestpair_N", in, Codec.UNSIGNED5, atCount);
         int nestPairCount = 0;

         for(int element : mbg[i].nestpair_N) {
            nestPairCount += element;
         }

         mbg[i].nestname_RU = this.parseCPUTF8References(contextName + "_" + rxa + "_nestname_RU", in, Codec.UNSIGNED5, nestPairCount);
      }

      return mbg;
   }

   private void parseMethodAttrBands(InputStream in) throws IOException, Pack200Exception {
      this.methodFlags = this.parseFlags("method_flags", in, this.classMethodCount, Codec.UNSIGNED5, this.options.hasMethodFlagsHi());
      int methodAttrCount = SegmentUtils.countBit16(this.methodFlags);
      int[] methodAttrCounts = this.decodeBandInt("method_attr_count", in, Codec.UNSIGNED5, methodAttrCount);
      int[][] methodAttrIndexes = this.decodeBandInt("method_attr_indexes", in, Codec.UNSIGNED5, methodAttrCounts);
      int callCount = this.getCallCount(methodAttrIndexes, this.methodFlags, 2);
      this.methodAttrCalls = this.decodeBandInt("method_attr_calls", in, Codec.UNSIGNED5, callCount);
      this.methodAttributes = new ArrayList[this.classCount][];

      for(int i = 0; i < this.classCount; ++i) {
         this.methodAttributes[i] = new ArrayList[this.methodFlags[i].length];

         for(int j = 0; j < this.methodFlags[i].length; ++j) {
            this.methodAttributes[i][j] = new ArrayList();
         }
      }

      AttributeLayout methodExceptionsLayout = this.attrMap.getAttributeLayout("Exceptions", 2);
      int count = SegmentUtils.countMatches((long[][])this.methodFlags, methodExceptionsLayout);
      int[] numExceptions = this.decodeBandInt("method_Exceptions_n", in, Codec.UNSIGNED5, count);
      int[][] methodExceptionsRS = this.decodeBandInt("method_Exceptions_RC", in, Codec.UNSIGNED5, numExceptions);
      AttributeLayout methodSignatureLayout = this.attrMap.getAttributeLayout("Signature", 2);
      int count1 = SegmentUtils.countMatches((long[][])this.methodFlags, methodSignatureLayout);
      int[] methodSignatureRS = this.decodeBandInt("method_signature_RS", in, Codec.UNSIGNED5, count1);
      AttributeLayout deprecatedLayout = this.attrMap.getAttributeLayout("Deprecated", 2);
      int methodExceptionsIndex = 0;
      int methodSignatureIndex = 0;

      for(int i = 0; i < this.methodAttributes.length; ++i) {
         for(int j = 0; j < this.methodAttributes[i].length; ++j) {
            long flag = this.methodFlags[i][j];
            if (methodExceptionsLayout.matches(flag)) {
               int n = numExceptions[methodExceptionsIndex];
               int[] exceptions = methodExceptionsRS[methodExceptionsIndex];
               CPClass[] exceptionClasses = new CPClass[n];

               for(int k = 0; k < n; ++k) {
                  exceptionClasses[k] = this.cpBands.cpClassValue(exceptions[k]);
               }

               this.methodAttributes[i][j].add(new ExceptionsAttribute(exceptionClasses));
               ++methodExceptionsIndex;
            }

            if (methodSignatureLayout.matches(flag)) {
               long result = (long)methodSignatureRS[methodSignatureIndex];
               String desc = this.methodDescr[i][j];
               int colon = desc.indexOf(58);
               String type = desc.substring(colon + 1);
               if (type.equals("B") || type.equals("H")) {
                  type = "I";
               }

               CPUTF8 value = (CPUTF8)methodSignatureLayout.getValue(result, type, this.cpBands.getConstantPool());
               this.methodAttributes[i][j].add(new SignatureAttribute(value));
               ++methodSignatureIndex;
            }

            if (deprecatedLayout.matches(flag)) {
               this.methodAttributes[i][j].add(new DeprecatedAttribute());
            }
         }
      }

      int backwardsCallIndex = this.parseMethodMetadataBands(in, this.methodAttrCalls);
      int limit = this.options.hasMethodFlagsHi() ? 62 : 31;
      AttributeLayout[] otherLayouts = new AttributeLayout[limit + 1];
      int[] counts = new int[limit + 1];

      for(int i = 0; i < limit; ++i) {
         AttributeLayout layout = this.attrMap.getAttributeLayout(i, 2);
         if (layout != null && !layout.isDefaultLayout()) {
            otherLayouts[i] = layout;
            counts[i] = SegmentUtils.countMatches((long[][])this.methodFlags, layout);
         }
      }

      List<Attribute>[] otherAttributes = new List[limit + 1];

      for(int i = 0; i < counts.length; ++i) {
         if (counts[i] > 0) {
            NewAttributeBands bands = this.attrMap.getAttributeBands(otherLayouts[i]);
            otherAttributes[i] = bands.parseAttributes(in, counts[i]);
            int numBackwardsCallables = otherLayouts[i].numBackwardsCallables();
            if (numBackwardsCallables > 0) {
               int[] backwardsCalls = new int[numBackwardsCallables];
               System.arraycopy(this.methodAttrCalls, backwardsCallIndex, backwardsCalls, 0, numBackwardsCallables);
               bands.setBackwardsCalls(backwardsCalls);
               backwardsCallIndex += numBackwardsCallables;
            }
         }
      }

      for(int i = 0; i < this.methodAttributes.length; ++i) {
         for(int j = 0; j < this.methodAttributes[i].length; ++j) {
            long flag = this.methodFlags[i][j];
            int othersAddedAtStart = 0;

            for(int k = 0; k < otherLayouts.length; ++k) {
               if (otherLayouts[k] != null && otherLayouts[k].matches(flag)) {
                  if (otherLayouts[k].getIndex() < 15) {
                     this.methodAttributes[i][j].add(othersAddedAtStart++, (Attribute)otherAttributes[k].get(0));
                  } else {
                     this.methodAttributes[i][j].add((Attribute)otherAttributes[k].get(0));
                  }

                  otherAttributes[k].remove(0);
               }
            }
         }
      }

   }

   private void parseMethodBands(InputStream in) throws IOException, Pack200Exception {
      this.methodDescrInts = this.decodeBandInt("method_descr", in, Codec.MDELTA5, this.classMethodCount);
      this.methodDescr = this.getReferences(this.methodDescrInts, this.cpBands.getCpDescriptor());
      this.parseMethodAttrBands(in);
   }

   private int parseMethodMetadataBands(InputStream in, int[] methodAttrCalls) throws Pack200Exception, IOException {
      int backwardsCallsUsed = 0;
      String[] RxA = new String[]{"RVA", "RIA", "RVPA", "RIPA", "AD"};
      int[] rxaCounts = new int[]{0, 0, 0, 0, 0};
      AttributeLayout rvaLayout = this.attrMap.getAttributeLayout("RuntimeVisibleAnnotations", 2);
      AttributeLayout riaLayout = this.attrMap.getAttributeLayout("RuntimeInvisibleAnnotations", 2);
      AttributeLayout rvpaLayout = this.attrMap.getAttributeLayout("RuntimeVisibleParameterAnnotations", 2);
      AttributeLayout ripaLayout = this.attrMap.getAttributeLayout("RuntimeInvisibleParameterAnnotations", 2);
      AttributeLayout adLayout = this.attrMap.getAttributeLayout("AnnotationDefault", 2);
      AttributeLayout[] rxaLayouts = new AttributeLayout[]{rvaLayout, riaLayout, rvpaLayout, ripaLayout, adLayout};
      Arrays.setAll(rxaCounts, (ix) -> SegmentUtils.countMatches((long[][])this.methodFlags, rxaLayouts[ix]));
      int[] backwardsCalls = new int[5];
      int methodAttrIndex = 0;

      for(int i = 0; i < backwardsCalls.length; ++i) {
         if (rxaCounts[i] > 0) {
            ++backwardsCallsUsed;
            backwardsCalls[i] = methodAttrCalls[methodAttrIndex];
            ++methodAttrIndex;
         } else {
            backwardsCalls[i] = 0;
         }
      }

      MetadataBandGroup[] mbgs = this.parseMetadata(in, RxA, rxaCounts, backwardsCalls, "method");
      List<Attribute>[] attributeLists = new List[RxA.length];
      int[] attributeListIndexes = new int[RxA.length];

      for(int i = 0; i < mbgs.length; ++i) {
         attributeLists[i] = mbgs[i].getAttributes();
         attributeListIndexes[i] = 0;
      }

      for(int i = 0; i < this.methodFlags.length; ++i) {
         for(int j = 0; j < this.methodFlags[i].length; ++j) {
            for(int k = 0; k < rxaLayouts.length; ++k) {
               if (rxaLayouts[k].matches(this.methodFlags[i][j])) {
                  this.methodAttributes[i][j].add((Attribute)attributeLists[k].get(attributeListIndexes[k]++));
               }
            }
         }
      }

      return backwardsCallsUsed;
   }

   public void read(InputStream in) throws IOException, Pack200Exception {
      int classCount = this.header.getClassCount();
      this.classThisInts = this.decodeBandInt("class_this", in, Codec.DELTA5, classCount);
      this.classThis = this.getReferences(this.classThisInts, this.cpBands.getCpClass());
      this.classSuperInts = this.decodeBandInt("class_super", in, Codec.DELTA5, classCount);
      int[] classInterfaceLengths = this.decodeBandInt("class_interface_count", in, Codec.DELTA5, classCount);
      this.classInterfacesInts = this.decodeBandInt("class_interface", in, Codec.DELTA5, classInterfaceLengths);
      this.classFieldCount = this.decodeBandInt("class_field_count", in, Codec.DELTA5, classCount);
      this.classMethodCount = this.decodeBandInt("class_method_count", in, Codec.DELTA5, classCount);
      this.parseFieldBands(in);
      this.parseMethodBands(in);
      this.parseClassAttrBands(in);
      this.parseCodeBands(in);
   }

   public void unpack() {
   }
}
