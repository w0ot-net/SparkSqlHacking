package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;

public class CpBands extends BandSet {
   private static final String EMPTY_STRING = "";
   private final SegmentConstantPool pool = new SegmentConstantPool(this);
   private String[] cpClass;
   private int[] cpClassInts;
   private int[] cpDescriptorNameInts;
   private int[] cpDescriptorTypeInts;
   private String[] cpDescriptor;
   private double[] cpDouble;
   private String[] cpFieldClass;
   private String[] cpFieldDescriptor;
   private int[] cpFieldClassInts;
   private int[] cpFieldDescriptorInts;
   private float[] cpFloat;
   private String[] cpIMethodClass;
   private String[] cpIMethodDescriptor;
   private int[] cpIMethodClassInts;
   private int[] cpIMethodDescriptorInts;
   private int[] cpInt;
   private long[] cpLong;
   private String[] cpMethodClass;
   private String[] cpMethodDescriptor;
   private int[] cpMethodClassInts;
   private int[] cpMethodDescriptorInts;
   private String[] cpSignature;
   private int[] cpSignatureInts;
   private String[] cpString;
   private int[] cpStringInts;
   private String[] cpUTF8;
   private final Map stringsToCPUTF8 = new HashMap();
   private final Map stringsToCPStrings = new HashMap();
   private final Map longsToCPLongs = new HashMap();
   private final Map integersToCPIntegers = new HashMap();
   private final Map floatsToCPFloats = new HashMap();
   private final Map stringsToCPClass = new HashMap();
   private final Map doublesToCPDoubles = new HashMap();
   private final Map descriptorsToCPNameAndTypes = new HashMap();
   private Map mapClass;
   private Map mapDescriptor;
   private Map mapUTF8;
   private Map mapSignature;
   private int intOffset;
   private int floatOffset;
   private int longOffset;
   private int doubleOffset;
   private int stringOffset;
   private int classOffset;
   private int signatureOffset;
   private int descrOffset;
   private int fieldOffset;
   private int methodOffset;
   private int imethodOffset;

   public CpBands(Segment segment) {
      super(segment);
   }

   public CPClass cpClassValue(int index) {
      String string = this.cpClass[index];
      int utf8Index = this.cpClassInts[index];
      int globalIndex = this.classOffset + index;
      return (CPClass)this.stringsToCPClass.computeIfAbsent(string, (k) -> new CPClass(this.cpUTF8Value(utf8Index), globalIndex));
   }

   public CPClass cpClassValue(String string) {
      CPClass cpString = (CPClass)this.stringsToCPClass.get(string);
      if (cpString == null) {
         Integer index = (Integer)this.mapClass.get(string);
         if (index != null) {
            return this.cpClassValue(index);
         }

         cpString = new CPClass(this.cpUTF8Value(string, false), -1);
         this.stringsToCPClass.put(string, cpString);
      }

      return cpString;
   }

   public CPDouble cpDoubleValue(int index) {
      Double dbl = this.cpDouble[index];
      CPDouble cpDouble = (CPDouble)this.doublesToCPDoubles.get(dbl);
      if (cpDouble == null) {
         cpDouble = new CPDouble(dbl, index + this.doubleOffset);
         this.doublesToCPDoubles.put(dbl, cpDouble);
      }

      return cpDouble;
   }

   public CPFieldRef cpFieldValue(int index) {
      return new CPFieldRef(this.cpClassValue(this.cpFieldClassInts[index]), this.cpNameAndTypeValue(this.cpFieldDescriptorInts[index]), index + this.fieldOffset);
   }

   public CPFloat cpFloatValue(int index) {
      Float f = this.cpFloat[index];
      CPFloat cpFloat = (CPFloat)this.floatsToCPFloats.get(f);
      if (cpFloat == null) {
         cpFloat = new CPFloat(f, index + this.floatOffset);
         this.floatsToCPFloats.put(f, cpFloat);
      }

      return cpFloat;
   }

   public CPInterfaceMethodRef cpIMethodValue(int index) {
      return new CPInterfaceMethodRef(this.cpClassValue(this.cpIMethodClassInts[index]), this.cpNameAndTypeValue(this.cpIMethodDescriptorInts[index]), index + this.imethodOffset);
   }

   public CPInteger cpIntegerValue(int index) {
      Integer i = this.cpInt[index];
      CPInteger cpInteger = (CPInteger)this.integersToCPIntegers.get(i);
      if (cpInteger == null) {
         cpInteger = new CPInteger(i, index + this.intOffset);
         this.integersToCPIntegers.put(i, cpInteger);
      }

      return cpInteger;
   }

   public CPLong cpLongValue(int index) {
      Long l = this.cpLong[index];
      CPLong cpLong = (CPLong)this.longsToCPLongs.get(l);
      if (cpLong == null) {
         cpLong = new CPLong(l, index + this.longOffset);
         this.longsToCPLongs.put(l, cpLong);
      }

      return cpLong;
   }

   public CPMethodRef cpMethodValue(int index) {
      return new CPMethodRef(this.cpClassValue(this.cpMethodClassInts[index]), this.cpNameAndTypeValue(this.cpMethodDescriptorInts[index]), index + this.methodOffset);
   }

   public CPNameAndType cpNameAndTypeValue(int index) {
      String descriptor = this.cpDescriptor[index];
      CPNameAndType cpNameAndType = (CPNameAndType)this.descriptorsToCPNameAndTypes.get(descriptor);
      if (cpNameAndType == null) {
         int nameIndex = this.cpDescriptorNameInts[index];
         int descriptorIndex = this.cpDescriptorTypeInts[index];
         CPUTF8 name = this.cpUTF8Value(nameIndex);
         CPUTF8 descriptorU = this.cpSignatureValue(descriptorIndex);
         cpNameAndType = new CPNameAndType(name, descriptorU, index + this.descrOffset);
         this.descriptorsToCPNameAndTypes.put(descriptor, cpNameAndType);
      }

      return cpNameAndType;
   }

   public CPNameAndType cpNameAndTypeValue(String descriptor) {
      CPNameAndType cpNameAndType = (CPNameAndType)this.descriptorsToCPNameAndTypes.get(descriptor);
      if (cpNameAndType == null) {
         Integer index = (Integer)this.mapDescriptor.get(descriptor);
         if (index != null) {
            return this.cpNameAndTypeValue(index);
         }

         int colon = descriptor.indexOf(58);
         String nameString = descriptor.substring(0, colon);
         String descriptorString = descriptor.substring(colon + 1);
         CPUTF8 name = this.cpUTF8Value(nameString, true);
         CPUTF8 descriptorU = this.cpUTF8Value(descriptorString, true);
         cpNameAndType = new CPNameAndType(name, descriptorU, -1 + this.descrOffset);
         this.descriptorsToCPNameAndTypes.put(descriptor, cpNameAndType);
      }

      return cpNameAndType;
   }

   public CPUTF8 cpSignatureValue(int index) {
      int globalIndex;
      if (this.cpSignatureInts[index] != -1) {
         globalIndex = this.cpSignatureInts[index];
      } else {
         globalIndex = index + this.signatureOffset;
      }

      String string = this.cpSignature[index];
      CPUTF8 cpUTF8 = (CPUTF8)this.stringsToCPUTF8.get(string);
      if (cpUTF8 == null) {
         cpUTF8 = new CPUTF8(string, globalIndex);
         this.stringsToCPUTF8.put(string, cpUTF8);
      }

      return cpUTF8;
   }

   public CPString cpStringValue(int index) {
      String string = this.cpString[index];
      int utf8Index = this.cpStringInts[index];
      int globalIndex = this.stringOffset + index;
      CPString cpString = (CPString)this.stringsToCPStrings.get(string);
      if (cpString == null) {
         cpString = new CPString(this.cpUTF8Value(utf8Index), globalIndex);
         this.stringsToCPStrings.put(string, cpString);
      }

      return cpString;
   }

   public CPUTF8 cpUTF8Value(int index) {
      String string = this.cpUTF8[index];
      CPUTF8 cputf8 = (CPUTF8)this.stringsToCPUTF8.get(string);
      if (cputf8 == null) {
         cputf8 = new CPUTF8(string, index);
         this.stringsToCPUTF8.put(string, cputf8);
      } else if (cputf8.getGlobalIndex() > index) {
         cputf8.setGlobalIndex(index);
      }

      return cputf8;
   }

   public CPUTF8 cpUTF8Value(String string) {
      return this.cpUTF8Value(string, true);
   }

   public CPUTF8 cpUTF8Value(String string, boolean searchForIndex) {
      CPUTF8 cputf8 = (CPUTF8)this.stringsToCPUTF8.get(string);
      if (cputf8 == null) {
         Integer index = null;
         if (searchForIndex) {
            index = (Integer)this.mapUTF8.get(string);
         }

         if (index != null) {
            return this.cpUTF8Value(index);
         }

         if (searchForIndex) {
            index = (Integer)this.mapSignature.get(string);
         }

         if (index != null) {
            return this.cpSignatureValue(index);
         }

         cputf8 = new CPUTF8(string, -1);
         this.stringsToCPUTF8.put(string, cputf8);
      }

      return cputf8;
   }

   public SegmentConstantPool getConstantPool() {
      return this.pool;
   }

   public String[] getCpClass() {
      return this.cpClass;
   }

   public String[] getCpDescriptor() {
      return this.cpDescriptor;
   }

   public int[] getCpDescriptorNameInts() {
      return this.cpDescriptorNameInts;
   }

   public int[] getCpDescriptorTypeInts() {
      return this.cpDescriptorTypeInts;
   }

   public String[] getCpFieldClass() {
      return this.cpFieldClass;
   }

   public String[] getCpIMethodClass() {
      return this.cpIMethodClass;
   }

   public int[] getCpInt() {
      return this.cpInt;
   }

   public long[] getCpLong() {
      return this.cpLong;
   }

   public String[] getCpMethodClass() {
      return this.cpMethodClass;
   }

   public String[] getCpMethodDescriptor() {
      return this.cpMethodDescriptor;
   }

   public String[] getCpSignature() {
      return this.cpSignature;
   }

   public String[] getCpUTF8() {
      return this.cpUTF8;
   }

   private void parseCpClass(InputStream in) throws IOException, Pack200Exception {
      int cpClassCount = this.header.getCpClassCount();
      this.cpClassInts = this.decodeBandInt("cp_Class", in, Codec.UDELTA5, cpClassCount);
      this.cpClass = new String[cpClassCount];
      this.mapClass = new HashMap(cpClassCount);

      for(int i = 0; i < cpClassCount; ++i) {
         this.cpClass[i] = this.cpUTF8[this.cpClassInts[i]];
         this.mapClass.put(this.cpClass[i], i);
      }

   }

   private void parseCpDescriptor(InputStream in) throws IOException, Pack200Exception {
      int cpDescriptorCount = this.header.getCpDescriptorCount();
      this.cpDescriptorNameInts = this.decodeBandInt("cp_Descr_name", in, Codec.DELTA5, cpDescriptorCount);
      this.cpDescriptorTypeInts = this.decodeBandInt("cp_Descr_type", in, Codec.UDELTA5, cpDescriptorCount);
      String[] cpDescriptorNames = this.getReferences(this.cpDescriptorNameInts, this.cpUTF8);
      String[] cpDescriptorTypes = this.getReferences(this.cpDescriptorTypeInts, this.cpSignature);
      this.cpDescriptor = new String[cpDescriptorCount];
      this.mapDescriptor = new HashMap(cpDescriptorCount);

      for(int i = 0; i < cpDescriptorCount; ++i) {
         this.cpDescriptor[i] = cpDescriptorNames[i] + ":" + cpDescriptorTypes[i];
         this.mapDescriptor.put(this.cpDescriptor[i], i);
      }

   }

   private void parseCpDouble(InputStream in) throws IOException, Pack200Exception {
      int cpDoubleCount = this.header.getCpDoubleCount();
      long[] band = this.parseFlags("cp_Double", in, cpDoubleCount, Codec.UDELTA5, Codec.DELTA5);
      this.cpDouble = new double[band.length];
      Arrays.setAll(this.cpDouble, (i) -> Double.longBitsToDouble(band[i]));
   }

   private void parseCpField(InputStream in) throws IOException, Pack200Exception {
      int cpFieldCount = this.header.getCpFieldCount();
      this.cpFieldClassInts = this.decodeBandInt("cp_Field_class", in, Codec.DELTA5, cpFieldCount);
      this.cpFieldDescriptorInts = this.decodeBandInt("cp_Field_desc", in, Codec.UDELTA5, cpFieldCount);
      this.cpFieldClass = new String[cpFieldCount];
      this.cpFieldDescriptor = new String[cpFieldCount];

      for(int i = 0; i < cpFieldCount; ++i) {
         this.cpFieldClass[i] = this.cpClass[this.cpFieldClassInts[i]];
         this.cpFieldDescriptor[i] = this.cpDescriptor[this.cpFieldDescriptorInts[i]];
      }

   }

   private void parseCpFloat(InputStream in) throws IOException, Pack200Exception {
      int cpFloatCount = this.header.getCpFloatCount();
      int[] floatBits = this.decodeBandInt("cp_Float", in, Codec.UDELTA5, cpFloatCount);
      this.cpFloat = new float[cpFloatCount];

      for(int i = 0; i < cpFloatCount; ++i) {
         this.cpFloat[i] = Float.intBitsToFloat(floatBits[i]);
      }

   }

   private void parseCpIMethod(InputStream in) throws IOException, Pack200Exception {
      int cpIMethodCount = this.header.getCpIMethodCount();
      this.cpIMethodClassInts = this.decodeBandInt("cp_Imethod_class", in, Codec.DELTA5, cpIMethodCount);
      this.cpIMethodDescriptorInts = this.decodeBandInt("cp_Imethod_desc", in, Codec.UDELTA5, cpIMethodCount);
      this.cpIMethodClass = new String[cpIMethodCount];
      this.cpIMethodDescriptor = new String[cpIMethodCount];

      for(int i = 0; i < cpIMethodCount; ++i) {
         this.cpIMethodClass[i] = this.cpClass[this.cpIMethodClassInts[i]];
         this.cpIMethodDescriptor[i] = this.cpDescriptor[this.cpIMethodDescriptorInts[i]];
      }

   }

   private void parseCpInt(InputStream in) throws IOException, Pack200Exception {
      int cpIntCount = this.header.getCpIntCount();
      this.cpInt = this.decodeBandInt("cpInt", in, Codec.UDELTA5, cpIntCount);
   }

   private void parseCpLong(InputStream in) throws IOException, Pack200Exception {
      int cpLongCount = this.header.getCpLongCount();
      this.cpLong = this.parseFlags("cp_Long", in, cpLongCount, Codec.UDELTA5, Codec.DELTA5);
   }

   private void parseCpMethod(InputStream in) throws IOException, Pack200Exception {
      int cpMethodCount = this.header.getCpMethodCount();
      this.cpMethodClassInts = this.decodeBandInt("cp_Method_class", in, Codec.DELTA5, cpMethodCount);
      this.cpMethodDescriptorInts = this.decodeBandInt("cp_Method_desc", in, Codec.UDELTA5, cpMethodCount);
      this.cpMethodClass = new String[cpMethodCount];
      this.cpMethodDescriptor = new String[cpMethodCount];

      for(int i = 0; i < cpMethodCount; ++i) {
         this.cpMethodClass[i] = this.cpClass[this.cpMethodClassInts[i]];
         this.cpMethodDescriptor[i] = this.cpDescriptor[this.cpMethodDescriptorInts[i]];
      }

   }

   private void parseCpSignature(InputStream in) throws IOException, Pack200Exception {
      int cpSignatureCount = this.header.getCpSignatureCount();
      this.cpSignatureInts = this.decodeBandInt("cp_Signature_form", in, Codec.DELTA5, cpSignatureCount);
      String[] cpSignatureForm = this.getReferences(this.cpSignatureInts, this.cpUTF8);
      this.cpSignature = new String[cpSignatureCount];
      this.mapSignature = new HashMap();
      int lCount = 0;

      for(int i = 0; i < cpSignatureCount; ++i) {
         String form = cpSignatureForm[i];
         char[] chars = form.toCharArray();

         for(char element : chars) {
            if (element == 'L') {
               this.cpSignatureInts[i] = -1;
               ++lCount;
            }
         }
      }

      String[] cpSignatureClasses = this.parseReferences("cp_Signature_classes", in, Codec.UDELTA5, lCount, this.cpClass);
      int index = 0;

      for(int i = 0; i < cpSignatureCount; ++i) {
         String form = cpSignatureForm[i];
         int len = form.length();
         StringBuilder signature = new StringBuilder(64);

         for(int j = 0; j < len; ++j) {
            char c = form.charAt(j);
            signature.append(c);
            if (c == 'L') {
               String className = cpSignatureClasses[index];
               signature.append(className);
               ++index;
            }
         }

         this.cpSignature[i] = signature.toString();
         this.mapSignature.put(signature.toString(), i);
      }

   }

   private void parseCpString(InputStream in) throws IOException, Pack200Exception {
      int cpStringCount = this.header.getCpStringCount();
      this.cpStringInts = this.decodeBandInt("cp_String", in, Codec.UDELTA5, cpStringCount);
      this.cpString = new String[cpStringCount];
      Arrays.setAll(this.cpString, (i) -> this.cpUTF8[this.cpStringInts[i]]);
   }

   private void parseCpUtf8(InputStream in) throws IOException, Pack200Exception {
      int cpUTF8Count = this.header.getCpUTF8Count();
      if (cpUTF8Count <= 0) {
         throw new IOException("cpUTF8Count value must be greater than 0");
      } else {
         int[] prefix = this.decodeBandInt("cpUTF8Prefix", in, Codec.DELTA5, cpUTF8Count - 2);
         int charCount = 0;
         int bigSuffixCount = 0;
         int[] suffix = this.decodeBandInt("cpUTF8Suffix", in, Codec.UNSIGNED5, cpUTF8Count - 1);

         for(int element : suffix) {
            if (element == 0) {
               ++bigSuffixCount;
            } else {
               charCount += element;
            }
         }

         int[] dataBand = this.decodeBandInt("cp_Utf8_chars", in, Codec.CHAR3, charCount);
         char[] data = new char[charCount];

         for(int i = 0; i < data.length; ++i) {
            data[i] = (char)dataBand[i];
         }

         int[] bigSuffixCounts = this.decodeBandInt("cp_Utf8_big_suffix", in, Codec.DELTA5, bigSuffixCount);
         int[][] bigSuffixDataBand = new int[bigSuffixCount][];

         for(int i = 0; i < bigSuffixDataBand.length; ++i) {
            bigSuffixDataBand[i] = this.decodeBandInt("cp_Utf8_big_chars " + i, in, Codec.DELTA5, bigSuffixCounts[i]);
         }

         char[][] bigSuffixData = new char[bigSuffixCount][];

         for(int i = 0; i < bigSuffixDataBand.length; ++i) {
            bigSuffixData[i] = new char[bigSuffixDataBand[i].length];

            for(int j = 0; j < bigSuffixDataBand[i].length; ++j) {
               bigSuffixData[i][j] = (char)bigSuffixDataBand[i][j];
            }
         }

         this.mapUTF8 = new HashMap(cpUTF8Count + 1);
         this.cpUTF8 = new String[cpUTF8Count];
         this.cpUTF8[0] = "";
         this.mapUTF8.put("", 0);
         charCount = 0;
         bigSuffixCount = 0;

         for(int i = 1; i < cpUTF8Count; ++i) {
            String lastString = this.cpUTF8[i - 1];
            if (suffix[i - 1] == 0) {
               this.cpUTF8[i] = lastString.substring(0, i > 1 ? prefix[i - 2] : 0) + new String(bigSuffixData[bigSuffixCount++]);
               this.mapUTF8.put(this.cpUTF8[i], i);
            } else {
               this.cpUTF8[i] = lastString.substring(0, i > 1 ? prefix[i - 2] : 0) + new String(data, charCount, suffix[i - 1]);
               charCount += suffix[i - 1];
               this.mapUTF8.put(this.cpUTF8[i], i);
            }
         }

      }
   }

   public void read(InputStream in) throws IOException, Pack200Exception {
      this.parseCpUtf8(in);
      this.parseCpInt(in);
      this.parseCpFloat(in);
      this.parseCpLong(in);
      this.parseCpDouble(in);
      this.parseCpString(in);
      this.parseCpClass(in);
      this.parseCpSignature(in);
      this.parseCpDescriptor(in);
      this.parseCpField(in);
      this.parseCpMethod(in);
      this.parseCpIMethod(in);
      this.intOffset = this.cpUTF8.length;
      this.floatOffset = this.intOffset + this.cpInt.length;
      this.longOffset = this.floatOffset + this.cpFloat.length;
      this.doubleOffset = this.longOffset + this.cpLong.length;
      this.stringOffset = this.doubleOffset + this.cpDouble.length;
      this.classOffset = this.stringOffset + this.cpString.length;
      this.signatureOffset = this.classOffset + this.cpClass.length;
      this.descrOffset = this.signatureOffset + this.cpSignature.length;
      this.fieldOffset = this.descrOffset + this.cpDescriptor.length;
      this.methodOffset = this.fieldOffset + this.cpFieldClass.length;
      this.imethodOffset = this.methodOffset + this.cpMethodClass.length;
   }

   public void unpack() {
   }
}
