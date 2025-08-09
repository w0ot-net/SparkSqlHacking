package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.objectweb.asm.Type;

public class CpBands extends BandSet {
   private final Set defaultAttributeNames = new HashSet();
   private final Set cp_Utf8 = new TreeSet();
   private final Set cp_Int = new TreeSet();
   private final Set cp_Float = new TreeSet();
   private final Set cp_Long = new TreeSet();
   private final Set cp_Double = new TreeSet();
   private final Set cp_String = new TreeSet();
   private final Set cp_Class = new TreeSet();
   private final Set cp_Signature = new TreeSet();
   private final Set cp_Descr = new TreeSet();
   private final Set cp_Field = new TreeSet();
   private final Set cp_Method = new TreeSet();
   private final Set cp_Imethod = new TreeSet();
   private final Map stringsToCpUtf8 = new HashMap();
   private final Map stringsToCpNameAndType = new HashMap();
   private final Map stringsToCpClass = new HashMap();
   private final Map stringsToCpSignature = new HashMap();
   private final Map stringsToCpMethod = new HashMap();
   private final Map stringsToCpField = new HashMap();
   private final Map stringsToCpIMethod = new HashMap();
   private final Map objectsToCPConstant = new HashMap();
   private final Segment segment;

   public CpBands(Segment segment, int effort) {
      super(effort, segment.getSegmentHeader());
      this.segment = segment;
      this.defaultAttributeNames.add("AnnotationDefault");
      this.defaultAttributeNames.add("RuntimeVisibleAnnotations");
      this.defaultAttributeNames.add("RuntimeInvisibleAnnotations");
      this.defaultAttributeNames.add("RuntimeVisibleParameterAnnotations");
      this.defaultAttributeNames.add("RuntimeInvisibleParameterAnnotations");
      this.defaultAttributeNames.add("Code");
      this.defaultAttributeNames.add("LineNumberTable");
      this.defaultAttributeNames.add("LocalVariableTable");
      this.defaultAttributeNames.add("LocalVariableTypeTable");
      this.defaultAttributeNames.add("ConstantValue");
      this.defaultAttributeNames.add("Deprecated");
      this.defaultAttributeNames.add("EnclosingMethod");
      this.defaultAttributeNames.add("Exceptions");
      this.defaultAttributeNames.add("InnerClasses");
      this.defaultAttributeNames.add("Signature");
      this.defaultAttributeNames.add("SourceFile");
   }

   private void addCharacters(List chars, char[] charArray) {
      for(char element : charArray) {
         chars.add(element);
      }

   }

   public void addCPClass(String className) {
      this.getCPClass(className);
   }

   void addCPUtf8(String utf8) {
      this.getCPUtf8(utf8);
   }

   private void addIndices() {
      for(Set set : Arrays.asList(this.cp_Utf8, this.cp_Int, this.cp_Float, this.cp_Long, this.cp_Double, this.cp_String, this.cp_Class, this.cp_Signature, this.cp_Descr, this.cp_Field, this.cp_Method, this.cp_Imethod)) {
         int j = 0;

         for(ConstantPoolEntry entry : set) {
            entry.setIndex(j);
            ++j;
         }
      }

      Map<CPClass, Integer> classNameToIndex = new HashMap();
      this.cp_Field.forEach((mOrF) -> {
         CPClass cpClassName = mOrF.getClassName();
         Integer index = (Integer)classNameToIndex.get(cpClassName);
         if (index == null) {
            classNameToIndex.put(cpClassName, 1);
            mOrF.setIndexInClass(0);
         } else {
            int theIndex = index;
            mOrF.setIndexInClass(theIndex);
            classNameToIndex.put(cpClassName, theIndex + 1);
         }

      });
      classNameToIndex.clear();
      Map<CPClass, Integer> classNameToConstructorIndex = new HashMap();
      this.cp_Method.forEach((mOrF) -> {
         CPClass cpClassName = mOrF.getClassName();
         Integer index = (Integer)classNameToIndex.get(cpClassName);
         if (index == null) {
            classNameToIndex.put(cpClassName, 1);
            mOrF.setIndexInClass(0);
         } else {
            int theIndex = index;
            mOrF.setIndexInClass(theIndex);
            classNameToIndex.put(cpClassName, theIndex + 1);
         }

         if (mOrF.getDesc().getName().equals("<init>")) {
            Integer constructorIndex = (Integer)classNameToConstructorIndex.get(cpClassName);
            if (constructorIndex == null) {
               classNameToConstructorIndex.put(cpClassName, 1);
               mOrF.setIndexInClassForConstructor(0);
            } else {
               int theIndex = constructorIndex;
               mOrF.setIndexInClassForConstructor(theIndex);
               classNameToConstructorIndex.put(cpClassName, theIndex + 1);
            }
         }

      });
   }

   public boolean existsCpClass(String className) {
      return this.stringsToCpClass.containsKey(className);
   }

   public void finaliseBands() {
      this.addCPUtf8("");
      this.removeSignaturesFromCpUTF8();
      this.addIndices();
      this.segmentHeader.setCp_Utf8_count(this.cp_Utf8.size());
      this.segmentHeader.setCp_Int_count(this.cp_Int.size());
      this.segmentHeader.setCp_Float_count(this.cp_Float.size());
      this.segmentHeader.setCp_Long_count(this.cp_Long.size());
      this.segmentHeader.setCp_Double_count(this.cp_Double.size());
      this.segmentHeader.setCp_String_count(this.cp_String.size());
      this.segmentHeader.setCp_Class_count(this.cp_Class.size());
      this.segmentHeader.setCp_Signature_count(this.cp_Signature.size());
      this.segmentHeader.setCp_Descr_count(this.cp_Descr.size());
      this.segmentHeader.setCp_Field_count(this.cp_Field.size());
      this.segmentHeader.setCp_Method_count(this.cp_Method.size());
      this.segmentHeader.setCp_Imethod_count(this.cp_Imethod.size());
   }

   public CPConstant getConstant(Object value) {
      CPConstant<?> constant = (CPConstant)this.objectsToCPConstant.get(value);
      if (constant == null) {
         if (value instanceof Integer) {
            constant = new CPInt((Integer)value);
            this.cp_Int.add(constant);
         } else if (value instanceof Long) {
            constant = new CPLong((Long)value);
            this.cp_Long.add(constant);
         } else if (value instanceof Float) {
            constant = new CPFloat((Float)value);
            this.cp_Float.add(constant);
         } else if (value instanceof Double) {
            constant = new CPDouble((Double)value);
            this.cp_Double.add(constant);
         } else if (value instanceof String) {
            constant = new CPString(this.getCPUtf8((String)value));
            this.cp_String.add(constant);
         } else if (value instanceof Type) {
            String className = ((Type)value).getClassName();
            if (className.endsWith("[]")) {
               for(className = "[L" + className.substring(0, className.length() - 2); className.endsWith("[]"); className = "[" + className.substring(0, className.length() - 2)) {
               }

               className = className + ";";
            }

            constant = this.getCPClass(className);
         }

         this.objectsToCPConstant.put(value, constant);
      }

      return constant;
   }

   public CPClass getCPClass(String className) {
      if (className == null) {
         return null;
      } else {
         className = className.replace('.', '/');
         CPClass cpClass = (CPClass)this.stringsToCpClass.get(className);
         if (cpClass == null) {
            CPUTF8 cpUtf8 = this.getCPUtf8(className);
            cpClass = new CPClass(cpUtf8);
            this.cp_Class.add(cpClass);
            this.stringsToCpClass.put(className, cpClass);
         }

         if (cpClass.isInnerClass()) {
            this.segment.getClassBands().currentClassReferencesInnerClass(cpClass);
         }

         return cpClass;
      }
   }

   public CPMethodOrField getCPField(CPClass cpClass, String name, String desc) {
      String key = cpClass.toString() + ":" + name + ":" + desc;
      CPMethodOrField cpF = (CPMethodOrField)this.stringsToCpField.get(key);
      if (cpF == null) {
         CPNameAndType nAndT = this.getCPNameAndType(name, desc);
         cpF = new CPMethodOrField(cpClass, nAndT);
         this.cp_Field.add(cpF);
         this.stringsToCpField.put(key, cpF);
      }

      return cpF;
   }

   public CPMethodOrField getCPField(String owner, String name, String desc) {
      return this.getCPField(this.getCPClass(owner), name, desc);
   }

   public CPMethodOrField getCPIMethod(CPClass cpClass, String name, String desc) {
      String key = cpClass.toString() + ":" + name + ":" + desc;
      CPMethodOrField cpIM = (CPMethodOrField)this.stringsToCpIMethod.get(key);
      if (cpIM == null) {
         CPNameAndType nAndT = this.getCPNameAndType(name, desc);
         cpIM = new CPMethodOrField(cpClass, nAndT);
         this.cp_Imethod.add(cpIM);
         this.stringsToCpIMethod.put(key, cpIM);
      }

      return cpIM;
   }

   public CPMethodOrField getCPIMethod(String owner, String name, String desc) {
      return this.getCPIMethod(this.getCPClass(owner), name, desc);
   }

   public CPMethodOrField getCPMethod(CPClass cpClass, String name, String desc) {
      String key = cpClass.toString() + ":" + name + ":" + desc;
      CPMethodOrField cpM = (CPMethodOrField)this.stringsToCpMethod.get(key);
      if (cpM == null) {
         CPNameAndType nAndT = this.getCPNameAndType(name, desc);
         cpM = new CPMethodOrField(cpClass, nAndT);
         this.cp_Method.add(cpM);
         this.stringsToCpMethod.put(key, cpM);
      }

      return cpM;
   }

   public CPMethodOrField getCPMethod(String owner, String name, String desc) {
      return this.getCPMethod(this.getCPClass(owner), name, desc);
   }

   public CPNameAndType getCPNameAndType(String name, String signature) {
      String descr = name + ":" + signature;
      CPNameAndType nameAndType = (CPNameAndType)this.stringsToCpNameAndType.get(descr);
      if (nameAndType == null) {
         nameAndType = new CPNameAndType(this.getCPUtf8(name), this.getCPSignature(signature));
         this.stringsToCpNameAndType.put(descr, nameAndType);
         this.cp_Descr.add(nameAndType);
      }

      return nameAndType;
   }

   public CPSignature getCPSignature(String signature) {
      if (signature == null) {
         return null;
      } else {
         CPSignature cpS = (CPSignature)this.stringsToCpSignature.get(signature);
         if (cpS == null) {
            List<CPClass> cpClasses = new ArrayList();
            CPUTF8 signatureUTF8;
            if (signature.length() > 1 && signature.indexOf(76) != -1) {
               List<String> classes = new ArrayList();
               char[] chars = signature.toCharArray();
               StringBuilder signatureString = new StringBuilder();

               for(int i = 0; i < chars.length; ++i) {
                  signatureString.append(chars[i]);
                  if (chars[i] == 'L') {
                     StringBuilder className = new StringBuilder();

                     for(int j = i + 1; j < chars.length; ++j) {
                        char c = chars[j];
                        if (!Character.isLetter(c) && !Character.isDigit(c) && c != '/' && c != '$' && c != '_') {
                           classes.add(className.toString());
                           i = j - 1;
                           break;
                        }

                        className.append(c);
                     }
                  }
               }

               this.removeCpUtf8(signature);

               for(String className : classes) {
                  CPClass cpClass = null;
                  if (className != null) {
                     className = className.replace('.', '/');
                     cpClass = (CPClass)this.stringsToCpClass.get(className);
                     if (cpClass == null) {
                        CPUTF8 cpUtf8 = this.getCPUtf8(className);
                        cpClass = new CPClass(cpUtf8);
                        this.cp_Class.add(cpClass);
                        this.stringsToCpClass.put(className, cpClass);
                     }
                  }

                  cpClasses.add(cpClass);
               }

               signatureUTF8 = this.getCPUtf8(signatureString.toString());
            } else {
               signatureUTF8 = this.getCPUtf8(signature);
            }

            cpS = new CPSignature(signature, signatureUTF8, cpClasses);
            this.cp_Signature.add(cpS);
            this.stringsToCpSignature.put(signature, cpS);
         }

         return cpS;
      }
   }

   public CPUTF8 getCPUtf8(String utf8) {
      if (utf8 == null) {
         return null;
      } else {
         CPUTF8 cpUtf8 = (CPUTF8)this.stringsToCpUtf8.get(utf8);
         if (cpUtf8 == null) {
            cpUtf8 = new CPUTF8(utf8);
            this.cp_Utf8.add(cpUtf8);
            this.stringsToCpUtf8.put(utf8, cpUtf8);
         }

         return cpUtf8;
      }
   }

   public void pack(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing constant pool bands...");
      this.writeCpUtf8(out);
      this.writeCpInt(out);
      this.writeCpFloat(out);
      this.writeCpLong(out);
      this.writeCpDouble(out);
      this.writeCpString(out);
      this.writeCpClass(out);
      this.writeCpSignature(out);
      this.writeCpDescr(out);
      this.writeCpMethodOrField(this.cp_Field, out, "cp_Field");
      this.writeCpMethodOrField(this.cp_Method, out, "cp_Method");
      this.writeCpMethodOrField(this.cp_Imethod, out, "cp_Imethod");
   }

   private void removeCpUtf8(String string) {
      CPUTF8 utf8 = (CPUTF8)this.stringsToCpUtf8.get(string);
      if (utf8 != null && this.stringsToCpClass.get(string) == null) {
         this.stringsToCpUtf8.remove(string);
         this.cp_Utf8.remove(utf8);
      }

   }

   private void removeSignaturesFromCpUTF8() {
      this.cp_Signature.forEach((signature) -> {
         String sigStr = signature.getUnderlyingString();
         CPUTF8 utf8 = signature.getSignatureForm();
         String form = utf8.getUnderlyingString();
         if (!sigStr.equals(form)) {
            this.removeCpUtf8(sigStr);
         }

      });
   }

   private void writeCpClass(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + this.cp_Class.size() + " Class entries...");
      int[] cpClass = new int[this.cp_Class.size()];
      int i = 0;

      for(CPClass cpCl : this.cp_Class) {
         cpClass[i] = cpCl.getIndexInCpUtf8();
         ++i;
      }

      byte[] encodedBand = this.encodeBandInt("cpClass", cpClass, Codec.UDELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cpClass[" + cpClass.length + "]");
   }

   private void writeCpDescr(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + this.cp_Descr.size() + " Descriptor entries...");
      int[] cpDescrName = new int[this.cp_Descr.size()];
      int[] cpDescrType = new int[this.cp_Descr.size()];
      int i = 0;

      for(CPNameAndType nameAndType : this.cp_Descr) {
         cpDescrName[i] = nameAndType.getNameIndex();
         cpDescrType[i] = nameAndType.getTypeIndex();
         ++i;
      }

      byte[] encodedBand = this.encodeBandInt("cp_Descr_Name", cpDescrName, Codec.DELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cp_Descr_Name[" + cpDescrName.length + "]");
      encodedBand = this.encodeBandInt("cp_Descr_Type", cpDescrType, Codec.UDELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cp_Descr_Type[" + cpDescrType.length + "]");
   }

   private void writeCpDouble(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + this.cp_Double.size() + " Double entries...");
      int[] highBits = new int[this.cp_Double.size()];
      int[] loBits = new int[this.cp_Double.size()];
      int i = 0;

      for(CPDouble dbl : this.cp_Double) {
         long l = Double.doubleToLongBits(dbl.getDouble());
         highBits[i] = (int)(l >> 32);
         loBits[i] = (int)l;
         ++i;
      }

      byte[] encodedBand = this.encodeBandInt("cp_Double_hi", highBits, Codec.UDELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cp_Double_hi[" + highBits.length + "]");
      encodedBand = this.encodeBandInt("cp_Double_lo", loBits, Codec.DELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cp_Double_lo[" + loBits.length + "]");
   }

   private void writeCpFloat(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + this.cp_Float.size() + " Float entries...");
      int[] cpFloat = new int[this.cp_Float.size()];
      int i = 0;

      for(CPFloat fl : this.cp_Float) {
         cpFloat[i] = Float.floatToIntBits(fl.getFloat());
         ++i;
      }

      byte[] encodedBand = this.encodeBandInt("cp_Float", cpFloat, Codec.UDELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cp_Float[" + cpFloat.length + "]");
   }

   private void writeCpInt(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + this.cp_Int.size() + " Integer entries...");
      int[] cpInt = new int[this.cp_Int.size()];
      int i = 0;

      for(CPInt integer : this.cp_Int) {
         cpInt[i] = integer.getInt();
         ++i;
      }

      byte[] encodedBand = this.encodeBandInt("cp_Int", cpInt, Codec.UDELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cp_Int[" + cpInt.length + "]");
   }

   private void writeCpLong(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + this.cp_Long.size() + " Long entries...");
      int[] highBits = new int[this.cp_Long.size()];
      int[] loBits = new int[this.cp_Long.size()];
      int i = 0;

      for(CPLong lng : this.cp_Long) {
         long l = lng.getLong();
         highBits[i] = (int)(l >> 32);
         loBits[i] = (int)l;
         ++i;
      }

      byte[] encodedBand = this.encodeBandInt("cp_Long_hi", highBits, Codec.UDELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cp_Long_hi[" + highBits.length + "]");
      encodedBand = this.encodeBandInt("cp_Long_lo", loBits, Codec.DELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cp_Long_lo[" + loBits.length + "]");
   }

   private void writeCpMethodOrField(Set cp, OutputStream out, String name) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + cp.size() + " Method and Field entries...");
      int[] cp_methodOrField_class = new int[cp.size()];
      int[] cp_methodOrField_desc = new int[cp.size()];
      int i = 0;

      for(CPMethodOrField mOrF : cp) {
         cp_methodOrField_class[i] = mOrF.getClassIndex();
         cp_methodOrField_desc[i] = mOrF.getDescIndex();
         ++i;
      }

      byte[] encodedBand = this.encodeBandInt(name + "_class", cp_methodOrField_class, Codec.DELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from " + name + "_class[" + cp_methodOrField_class.length + "]");
      encodedBand = this.encodeBandInt(name + "_desc", cp_methodOrField_desc, Codec.UDELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from " + name + "_desc[" + cp_methodOrField_desc.length + "]");
   }

   private void writeCpSignature(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + this.cp_Signature.size() + " Signature entries...");
      int[] cpSignatureForm = new int[this.cp_Signature.size()];
      List<CPClass> classes = new ArrayList();
      int i = 0;

      for(CPSignature cpS : this.cp_Signature) {
         classes.addAll(cpS.getClasses());
         cpSignatureForm[i] = cpS.getIndexInCpUtf8();
         ++i;
      }

      int[] cpSignatureClasses = new int[classes.size()];
      Arrays.setAll(cpSignatureClasses, (j) -> ((CPClass)classes.get(j)).getIndex());
      byte[] encodedBand = this.encodeBandInt("cpSignatureForm", cpSignatureForm, Codec.DELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cpSignatureForm[" + cpSignatureForm.length + "]");
      encodedBand = this.encodeBandInt("cpSignatureClasses", cpSignatureClasses, Codec.UDELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cpSignatureClasses[" + cpSignatureClasses.length + "]");
   }

   private void writeCpString(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + this.cp_String.size() + " String entries...");
      int[] cpString = new int[this.cp_String.size()];
      int i = 0;

      for(CPString cpStr : this.cp_String) {
         cpString[i] = cpStr.getIndexInCpUtf8();
         ++i;
      }

      byte[] encodedBand = this.encodeBandInt("cpString", cpString, Codec.UDELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cpString[" + cpString.length + "]");
   }

   private void writeCpUtf8(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing " + this.cp_Utf8.size() + " UTF8 entries...");
      int[] cpUtf8Prefix = new int[this.cp_Utf8.size() - 2];
      int[] cpUtf8Suffix = new int[this.cp_Utf8.size() - 1];
      List<Character> chars = new ArrayList();
      List<Integer> bigSuffix = new ArrayList();
      List<Character> bigChars = new ArrayList();
      Object[] cpUtf8Array = this.cp_Utf8.toArray();
      String first = ((CPUTF8)cpUtf8Array[1]).getUnderlyingString();
      cpUtf8Suffix[0] = first.length();
      this.addCharacters(chars, first.toCharArray());

      for(int i = 2; i < cpUtf8Array.length; ++i) {
         char[] previous = ((CPUTF8)cpUtf8Array[i - 1]).getUnderlyingString().toCharArray();
         String currentStr = ((CPUTF8)cpUtf8Array[i]).getUnderlyingString();
         char[] current = currentStr.toCharArray();
         int prefix = 0;

         for(int j = 0; j < previous.length && previous[j] == current[j]; ++j) {
            ++prefix;
         }

         cpUtf8Prefix[i - 2] = prefix;
         currentStr = currentStr.substring(prefix);
         char[] suffix = currentStr.toCharArray();
         if (suffix.length > 1000) {
            cpUtf8Suffix[i - 1] = 0;
            bigSuffix.add(suffix.length);
            this.addCharacters(bigChars, suffix);
         } else {
            cpUtf8Suffix[i - 1] = suffix.length;
            this.addCharacters(chars, suffix);
         }
      }

      int[] cpUtf8Chars = new int[chars.size()];
      int[] cpUtf8BigSuffix = new int[bigSuffix.size()];
      int[][] cpUtf8BigChars = new int[bigSuffix.size()][];
      Arrays.setAll(cpUtf8Chars, (ix) -> (Character)chars.get(ix));

      for(int i = 0; i < cpUtf8BigSuffix.length; ++i) {
         int numBigChars = (Integer)bigSuffix.get(i);
         cpUtf8BigSuffix[i] = numBigChars;
         cpUtf8BigChars[i] = new int[numBigChars];
         Arrays.setAll(cpUtf8BigChars[i], (jx) -> (Character)bigChars.remove(0));
      }

      byte[] encodedBand = this.encodeBandInt("cpUtf8Prefix", cpUtf8Prefix, Codec.DELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cpUtf8Prefix[" + cpUtf8Prefix.length + "]");
      encodedBand = this.encodeBandInt("cpUtf8Suffix", cpUtf8Suffix, Codec.UNSIGNED5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cpUtf8Suffix[" + cpUtf8Suffix.length + "]");
      encodedBand = this.encodeBandInt("cpUtf8Chars", cpUtf8Chars, Codec.CHAR3);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cpUtf8Chars[" + cpUtf8Chars.length + "]");
      encodedBand = this.encodeBandInt("cpUtf8BigSuffix", cpUtf8BigSuffix, Codec.DELTA5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from cpUtf8BigSuffix[" + cpUtf8BigSuffix.length + "]");

      for(int i = 0; i < cpUtf8BigChars.length; ++i) {
         encodedBand = this.encodeBandInt("cpUtf8BigChars " + i, cpUtf8BigChars[i], Codec.DELTA5);
         out.write(encodedBand);
         PackingUtils.log("Wrote " + encodedBand.length + " bytes from cpUtf8BigChars" + i + "[" + cpUtf8BigChars[i].length + "]");
      }

   }
}
