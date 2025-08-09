package org.apache.hadoop.hive.serde2.thrift.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class Complex implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Complex");
   private static final TField AINT_FIELD_DESC = new TField("aint", (byte)8, (short)1);
   private static final TField A_STRING_FIELD_DESC = new TField("aString", (byte)11, (short)2);
   private static final TField LINT_FIELD_DESC = new TField("lint", (byte)15, (short)3);
   private static final TField L_STRING_FIELD_DESC = new TField("lString", (byte)15, (short)4);
   private static final TField LINT_STRING_FIELD_DESC = new TField("lintString", (byte)15, (short)5);
   private static final TField M_STRING_STRING_FIELD_DESC = new TField("mStringString", (byte)13, (short)6);
   private static final TField ATTRIBUTES_FIELD_DESC = new TField("attributes", (byte)13, (short)7);
   private static final TField UNION_FIELD1_FIELD_DESC = new TField("unionField1", (byte)12, (short)8);
   private static final TField UNION_FIELD2_FIELD_DESC = new TField("unionField2", (byte)12, (short)9);
   private static final TField UNION_FIELD3_FIELD_DESC = new TField("unionField3", (byte)12, (short)10);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ComplexStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ComplexTupleSchemeFactory();
   private int aint;
   @Nullable
   private String aString;
   @Nullable
   private List lint;
   @Nullable
   private List lString;
   @Nullable
   private List lintString;
   @Nullable
   private Map mStringString;
   @Nullable
   private Map attributes;
   @Nullable
   private PropValueUnion unionField1;
   @Nullable
   private PropValueUnion unionField2;
   @Nullable
   private PropValueUnion unionField3;
   private static final int __AINT_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public Complex() {
      this.__isset_bitfield = 0;
   }

   public Complex(int aint, String aString, List lint, List lString, List lintString, Map mStringString, Map attributes, PropValueUnion unionField1, PropValueUnion unionField2, PropValueUnion unionField3) {
      this();
      this.aint = aint;
      this.setAintIsSet(true);
      this.aString = aString;
      this.lint = lint;
      this.lString = lString;
      this.lintString = lintString;
      this.mStringString = mStringString;
      this.attributes = attributes;
      this.unionField1 = unionField1;
      this.unionField2 = unionField2;
      this.unionField3 = unionField3;
   }

   public Complex(Complex other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.aint = other.aint;
      if (other.isSetAString()) {
         this.aString = other.aString;
      }

      if (other.isSetLint()) {
         List<Integer> __this__lint = new ArrayList(other.lint);
         this.lint = __this__lint;
      }

      if (other.isSetLString()) {
         List<String> __this__lString = new ArrayList(other.lString);
         this.lString = __this__lString;
      }

      if (other.isSetLintString()) {
         List<IntString> __this__lintString = new ArrayList(other.lintString.size());

         for(IntString other_element : other.lintString) {
            __this__lintString.add(new IntString(other_element));
         }

         this.lintString = __this__lintString;
      }

      if (other.isSetMStringString()) {
         Map<String, String> __this__mStringString = new HashMap(other.mStringString);
         this.mStringString = __this__mStringString;
      }

      if (other.isSetAttributes()) {
         Map<String, Map<String, Map<String, PropValueUnion>>> __this__attributes = new HashMap(other.attributes.size());

         for(Map.Entry other_element : other.attributes.entrySet()) {
            String other_element_key = (String)other_element.getKey();
            Map<String, Map<String, PropValueUnion>> other_element_value = (Map)other_element.getValue();
            Map<String, Map<String, PropValueUnion>> __this__attributes_copy_value = new HashMap(other_element_value.size());

            for(Map.Entry other_element_value_element : other_element_value.entrySet()) {
               String other_element_value_element_key = (String)other_element_value_element.getKey();
               Map<String, PropValueUnion> other_element_value_element_value = (Map)other_element_value_element.getValue();
               Map<String, PropValueUnion> __this__attributes_copy_value_copy_value = new HashMap(other_element_value_element_value.size());

               for(Map.Entry other_element_value_element_value_element : other_element_value_element_value.entrySet()) {
                  String other_element_value_element_value_element_key = (String)other_element_value_element_value_element.getKey();
                  PropValueUnion other_element_value_element_value_element_value = (PropValueUnion)other_element_value_element_value_element.getValue();
                  PropValueUnion __this__attributes_copy_value_copy_value_copy_value = new PropValueUnion(other_element_value_element_value_element_value);
                  __this__attributes_copy_value_copy_value.put(other_element_value_element_value_element_key, __this__attributes_copy_value_copy_value_copy_value);
               }

               __this__attributes_copy_value.put(other_element_value_element_key, __this__attributes_copy_value_copy_value);
            }

            __this__attributes.put(other_element_key, __this__attributes_copy_value);
         }

         this.attributes = __this__attributes;
      }

      if (other.isSetUnionField1()) {
         this.unionField1 = new PropValueUnion(other.unionField1);
      }

      if (other.isSetUnionField2()) {
         this.unionField2 = new PropValueUnion(other.unionField2);
      }

      if (other.isSetUnionField3()) {
         this.unionField3 = new PropValueUnion(other.unionField3);
      }

   }

   public Complex deepCopy() {
      return new Complex(this);
   }

   public void clear() {
      this.setAintIsSet(false);
      this.aint = 0;
      this.aString = null;
      this.lint = null;
      this.lString = null;
      this.lintString = null;
      this.mStringString = null;
      this.attributes = null;
      this.unionField1 = null;
      this.unionField2 = null;
      this.unionField3 = null;
   }

   public int getAint() {
      return this.aint;
   }

   public void setAint(int aint) {
      this.aint = aint;
      this.setAintIsSet(true);
   }

   public void unsetAint() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetAint() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setAintIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getAString() {
      return this.aString;
   }

   public void setAString(@Nullable String aString) {
      this.aString = aString;
   }

   public void unsetAString() {
      this.aString = null;
   }

   public boolean isSetAString() {
      return this.aString != null;
   }

   public void setAStringIsSet(boolean value) {
      if (!value) {
         this.aString = null;
      }

   }

   public int getLintSize() {
      return this.lint == null ? 0 : this.lint.size();
   }

   @Nullable
   public Iterator getLintIterator() {
      return this.lint == null ? null : this.lint.iterator();
   }

   public void addToLint(int elem) {
      if (this.lint == null) {
         this.lint = new ArrayList();
      }

      this.lint.add(elem);
   }

   @Nullable
   public List getLint() {
      return this.lint;
   }

   public void setLint(@Nullable List lint) {
      this.lint = lint;
   }

   public void unsetLint() {
      this.lint = null;
   }

   public boolean isSetLint() {
      return this.lint != null;
   }

   public void setLintIsSet(boolean value) {
      if (!value) {
         this.lint = null;
      }

   }

   public int getLStringSize() {
      return this.lString == null ? 0 : this.lString.size();
   }

   @Nullable
   public Iterator getLStringIterator() {
      return this.lString == null ? null : this.lString.iterator();
   }

   public void addToLString(String elem) {
      if (this.lString == null) {
         this.lString = new ArrayList();
      }

      this.lString.add(elem);
   }

   @Nullable
   public List getLString() {
      return this.lString;
   }

   public void setLString(@Nullable List lString) {
      this.lString = lString;
   }

   public void unsetLString() {
      this.lString = null;
   }

   public boolean isSetLString() {
      return this.lString != null;
   }

   public void setLStringIsSet(boolean value) {
      if (!value) {
         this.lString = null;
      }

   }

   public int getLintStringSize() {
      return this.lintString == null ? 0 : this.lintString.size();
   }

   @Nullable
   public Iterator getLintStringIterator() {
      return this.lintString == null ? null : this.lintString.iterator();
   }

   public void addToLintString(IntString elem) {
      if (this.lintString == null) {
         this.lintString = new ArrayList();
      }

      this.lintString.add(elem);
   }

   @Nullable
   public List getLintString() {
      return this.lintString;
   }

   public void setLintString(@Nullable List lintString) {
      this.lintString = lintString;
   }

   public void unsetLintString() {
      this.lintString = null;
   }

   public boolean isSetLintString() {
      return this.lintString != null;
   }

   public void setLintStringIsSet(boolean value) {
      if (!value) {
         this.lintString = null;
      }

   }

   public int getMStringStringSize() {
      return this.mStringString == null ? 0 : this.mStringString.size();
   }

   public void putToMStringString(String key, String val) {
      if (this.mStringString == null) {
         this.mStringString = new HashMap();
      }

      this.mStringString.put(key, val);
   }

   @Nullable
   public Map getMStringString() {
      return this.mStringString;
   }

   public void setMStringString(@Nullable Map mStringString) {
      this.mStringString = mStringString;
   }

   public void unsetMStringString() {
      this.mStringString = null;
   }

   public boolean isSetMStringString() {
      return this.mStringString != null;
   }

   public void setMStringStringIsSet(boolean value) {
      if (!value) {
         this.mStringString = null;
      }

   }

   public int getAttributesSize() {
      return this.attributes == null ? 0 : this.attributes.size();
   }

   public void putToAttributes(String key, Map val) {
      if (this.attributes == null) {
         this.attributes = new HashMap();
      }

      this.attributes.put(key, val);
   }

   @Nullable
   public Map getAttributes() {
      return this.attributes;
   }

   public void setAttributes(@Nullable Map attributes) {
      this.attributes = attributes;
   }

   public void unsetAttributes() {
      this.attributes = null;
   }

   public boolean isSetAttributes() {
      return this.attributes != null;
   }

   public void setAttributesIsSet(boolean value) {
      if (!value) {
         this.attributes = null;
      }

   }

   @Nullable
   public PropValueUnion getUnionField1() {
      return this.unionField1;
   }

   public void setUnionField1(@Nullable PropValueUnion unionField1) {
      this.unionField1 = unionField1;
   }

   public void unsetUnionField1() {
      this.unionField1 = null;
   }

   public boolean isSetUnionField1() {
      return this.unionField1 != null;
   }

   public void setUnionField1IsSet(boolean value) {
      if (!value) {
         this.unionField1 = null;
      }

   }

   @Nullable
   public PropValueUnion getUnionField2() {
      return this.unionField2;
   }

   public void setUnionField2(@Nullable PropValueUnion unionField2) {
      this.unionField2 = unionField2;
   }

   public void unsetUnionField2() {
      this.unionField2 = null;
   }

   public boolean isSetUnionField2() {
      return this.unionField2 != null;
   }

   public void setUnionField2IsSet(boolean value) {
      if (!value) {
         this.unionField2 = null;
      }

   }

   @Nullable
   public PropValueUnion getUnionField3() {
      return this.unionField3;
   }

   public void setUnionField3(@Nullable PropValueUnion unionField3) {
      this.unionField3 = unionField3;
   }

   public void unsetUnionField3() {
      this.unionField3 = null;
   }

   public boolean isSetUnionField3() {
      return this.unionField3 != null;
   }

   public void setUnionField3IsSet(boolean value) {
      if (!value) {
         this.unionField3 = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case AINT:
            if (value == null) {
               this.unsetAint();
            } else {
               this.setAint((Integer)value);
            }
            break;
         case A_STRING:
            if (value == null) {
               this.unsetAString();
            } else {
               this.setAString((String)value);
            }
            break;
         case LINT:
            if (value == null) {
               this.unsetLint();
            } else {
               this.setLint((List)value);
            }
            break;
         case L_STRING:
            if (value == null) {
               this.unsetLString();
            } else {
               this.setLString((List)value);
            }
            break;
         case LINT_STRING:
            if (value == null) {
               this.unsetLintString();
            } else {
               this.setLintString((List)value);
            }
            break;
         case M_STRING_STRING:
            if (value == null) {
               this.unsetMStringString();
            } else {
               this.setMStringString((Map)value);
            }
            break;
         case ATTRIBUTES:
            if (value == null) {
               this.unsetAttributes();
            } else {
               this.setAttributes((Map)value);
            }
            break;
         case UNION_FIELD1:
            if (value == null) {
               this.unsetUnionField1();
            } else {
               this.setUnionField1((PropValueUnion)value);
            }
            break;
         case UNION_FIELD2:
            if (value == null) {
               this.unsetUnionField2();
            } else {
               this.setUnionField2((PropValueUnion)value);
            }
            break;
         case UNION_FIELD3:
            if (value == null) {
               this.unsetUnionField3();
            } else {
               this.setUnionField3((PropValueUnion)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case AINT:
            return this.getAint();
         case A_STRING:
            return this.getAString();
         case LINT:
            return this.getLint();
         case L_STRING:
            return this.getLString();
         case LINT_STRING:
            return this.getLintString();
         case M_STRING_STRING:
            return this.getMStringString();
         case ATTRIBUTES:
            return this.getAttributes();
         case UNION_FIELD1:
            return this.getUnionField1();
         case UNION_FIELD2:
            return this.getUnionField2();
         case UNION_FIELD3:
            return this.getUnionField3();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case AINT:
               return this.isSetAint();
            case A_STRING:
               return this.isSetAString();
            case LINT:
               return this.isSetLint();
            case L_STRING:
               return this.isSetLString();
            case LINT_STRING:
               return this.isSetLintString();
            case M_STRING_STRING:
               return this.isSetMStringString();
            case ATTRIBUTES:
               return this.isSetAttributes();
            case UNION_FIELD1:
               return this.isSetUnionField1();
            case UNION_FIELD2:
               return this.isSetUnionField2();
            case UNION_FIELD3:
               return this.isSetUnionField3();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Complex ? this.equals((Complex)that) : false;
   }

   public boolean equals(Complex that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_aint = true;
         boolean that_present_aint = true;
         if (this_present_aint || that_present_aint) {
            if (!this_present_aint || !that_present_aint) {
               return false;
            }

            if (this.aint != that.aint) {
               return false;
            }
         }

         boolean this_present_aString = this.isSetAString();
         boolean that_present_aString = that.isSetAString();
         if (this_present_aString || that_present_aString) {
            if (!this_present_aString || !that_present_aString) {
               return false;
            }

            if (!this.aString.equals(that.aString)) {
               return false;
            }
         }

         boolean this_present_lint = this.isSetLint();
         boolean that_present_lint = that.isSetLint();
         if (this_present_lint || that_present_lint) {
            if (!this_present_lint || !that_present_lint) {
               return false;
            }

            if (!this.lint.equals(that.lint)) {
               return false;
            }
         }

         boolean this_present_lString = this.isSetLString();
         boolean that_present_lString = that.isSetLString();
         if (this_present_lString || that_present_lString) {
            if (!this_present_lString || !that_present_lString) {
               return false;
            }

            if (!this.lString.equals(that.lString)) {
               return false;
            }
         }

         boolean this_present_lintString = this.isSetLintString();
         boolean that_present_lintString = that.isSetLintString();
         if (this_present_lintString || that_present_lintString) {
            if (!this_present_lintString || !that_present_lintString) {
               return false;
            }

            if (!this.lintString.equals(that.lintString)) {
               return false;
            }
         }

         boolean this_present_mStringString = this.isSetMStringString();
         boolean that_present_mStringString = that.isSetMStringString();
         if (this_present_mStringString || that_present_mStringString) {
            if (!this_present_mStringString || !that_present_mStringString) {
               return false;
            }

            if (!this.mStringString.equals(that.mStringString)) {
               return false;
            }
         }

         boolean this_present_attributes = this.isSetAttributes();
         boolean that_present_attributes = that.isSetAttributes();
         if (this_present_attributes || that_present_attributes) {
            if (!this_present_attributes || !that_present_attributes) {
               return false;
            }

            if (!this.attributes.equals(that.attributes)) {
               return false;
            }
         }

         boolean this_present_unionField1 = this.isSetUnionField1();
         boolean that_present_unionField1 = that.isSetUnionField1();
         if (this_present_unionField1 || that_present_unionField1) {
            if (!this_present_unionField1 || !that_present_unionField1) {
               return false;
            }

            if (!this.unionField1.equals(that.unionField1)) {
               return false;
            }
         }

         boolean this_present_unionField2 = this.isSetUnionField2();
         boolean that_present_unionField2 = that.isSetUnionField2();
         if (this_present_unionField2 || that_present_unionField2) {
            if (!this_present_unionField2 || !that_present_unionField2) {
               return false;
            }

            if (!this.unionField2.equals(that.unionField2)) {
               return false;
            }
         }

         boolean this_present_unionField3 = this.isSetUnionField3();
         boolean that_present_unionField3 = that.isSetUnionField3();
         if (this_present_unionField3 || that_present_unionField3) {
            if (!this_present_unionField3 || !that_present_unionField3) {
               return false;
            }

            if (!this.unionField3.equals(that.unionField3)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.aint;
      hashCode = hashCode * 8191 + (this.isSetAString() ? 131071 : 524287);
      if (this.isSetAString()) {
         hashCode = hashCode * 8191 + this.aString.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetLint() ? 131071 : 524287);
      if (this.isSetLint()) {
         hashCode = hashCode * 8191 + this.lint.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetLString() ? 131071 : 524287);
      if (this.isSetLString()) {
         hashCode = hashCode * 8191 + this.lString.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetLintString() ? 131071 : 524287);
      if (this.isSetLintString()) {
         hashCode = hashCode * 8191 + this.lintString.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMStringString() ? 131071 : 524287);
      if (this.isSetMStringString()) {
         hashCode = hashCode * 8191 + this.mStringString.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetAttributes() ? 131071 : 524287);
      if (this.isSetAttributes()) {
         hashCode = hashCode * 8191 + this.attributes.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetUnionField1() ? 131071 : 524287);
      if (this.isSetUnionField1()) {
         hashCode = hashCode * 8191 + this.unionField1.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetUnionField2() ? 131071 : 524287);
      if (this.isSetUnionField2()) {
         hashCode = hashCode * 8191 + this.unionField2.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetUnionField3() ? 131071 : 524287);
      if (this.isSetUnionField3()) {
         hashCode = hashCode * 8191 + this.unionField3.hashCode();
      }

      return hashCode;
   }

   public int compareTo(Complex other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetAint(), other.isSetAint());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetAint()) {
               lastComparison = TBaseHelper.compareTo(this.aint, other.aint);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetAString(), other.isSetAString());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetAString()) {
                  lastComparison = TBaseHelper.compareTo(this.aString, other.aString);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetLint(), other.isSetLint());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetLint()) {
                     lastComparison = TBaseHelper.compareTo(this.lint, other.lint);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetLString(), other.isSetLString());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetLString()) {
                        lastComparison = TBaseHelper.compareTo(this.lString, other.lString);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetLintString(), other.isSetLintString());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetLintString()) {
                           lastComparison = TBaseHelper.compareTo(this.lintString, other.lintString);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetMStringString(), other.isSetMStringString());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetMStringString()) {
                              lastComparison = TBaseHelper.compareTo(this.mStringString, other.mStringString);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetAttributes(), other.isSetAttributes());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetAttributes()) {
                                 lastComparison = TBaseHelper.compareTo(this.attributes, other.attributes);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetUnionField1(), other.isSetUnionField1());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetUnionField1()) {
                                    lastComparison = TBaseHelper.compareTo(this.unionField1, other.unionField1);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetUnionField2(), other.isSetUnionField2());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetUnionField2()) {
                                       lastComparison = TBaseHelper.compareTo(this.unionField2, other.unionField2);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetUnionField3(), other.isSetUnionField3());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetUnionField3()) {
                                          lastComparison = TBaseHelper.compareTo(this.unionField3, other.unionField3);
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          }
                                       }

                                       return 0;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return Complex._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Complex(");
      boolean first = true;
      sb.append("aint:");
      sb.append(this.aint);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("aString:");
      if (this.aString == null) {
         sb.append("null");
      } else {
         sb.append(this.aString);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("lint:");
      if (this.lint == null) {
         sb.append("null");
      } else {
         sb.append(this.lint);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("lString:");
      if (this.lString == null) {
         sb.append("null");
      } else {
         sb.append(this.lString);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("lintString:");
      if (this.lintString == null) {
         sb.append("null");
      } else {
         sb.append(this.lintString);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("mStringString:");
      if (this.mStringString == null) {
         sb.append("null");
      } else {
         sb.append(this.mStringString);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("attributes:");
      if (this.attributes == null) {
         sb.append("null");
      } else {
         sb.append(this.attributes);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("unionField1:");
      if (this.unionField1 == null) {
         sb.append("null");
      } else {
         sb.append(this.unionField1);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("unionField2:");
      if (this.unionField2 == null) {
         sb.append("null");
      } else {
         sb.append(this.unionField2);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("unionField3:");
      if (this.unionField3 == null) {
         sb.append("null");
      } else {
         sb.append(this.unionField3);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      try {
         this.write(new TCompactProtocol(new TIOStreamTransport(out)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      try {
         this.__isset_bitfield = 0;
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(Complex._Fields.AINT, new FieldMetaData("aint", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Complex._Fields.A_STRING, new FieldMetaData("aString", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Complex._Fields.LINT, new FieldMetaData("lint", (byte)3, new ListMetaData((byte)15, new FieldValueMetaData((byte)8))));
      tmpMap.put(Complex._Fields.L_STRING, new FieldMetaData("lString", (byte)3, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(Complex._Fields.LINT_STRING, new FieldMetaData("lintString", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, IntString.class))));
      tmpMap.put(Complex._Fields.M_STRING_STRING, new FieldMetaData("mStringString", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      tmpMap.put(Complex._Fields.ATTRIBUTES, new FieldMetaData("attributes", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new StructMetaData((byte)12, PropValueUnion.class))))));
      tmpMap.put(Complex._Fields.UNION_FIELD1, new FieldMetaData("unionField1", (byte)3, new StructMetaData((byte)12, PropValueUnion.class)));
      tmpMap.put(Complex._Fields.UNION_FIELD2, new FieldMetaData("unionField2", (byte)3, new StructMetaData((byte)12, PropValueUnion.class)));
      tmpMap.put(Complex._Fields.UNION_FIELD3, new FieldMetaData("unionField3", (byte)3, new StructMetaData((byte)12, PropValueUnion.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Complex.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      AINT((short)1, "aint"),
      A_STRING((short)2, "aString"),
      LINT((short)3, "lint"),
      L_STRING((short)4, "lString"),
      LINT_STRING((short)5, "lintString"),
      M_STRING_STRING((short)6, "mStringString"),
      ATTRIBUTES((short)7, "attributes"),
      UNION_FIELD1((short)8, "unionField1"),
      UNION_FIELD2((short)9, "unionField2"),
      UNION_FIELD3((short)10, "unionField3");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return AINT;
            case 2:
               return A_STRING;
            case 3:
               return LINT;
            case 4:
               return L_STRING;
            case 5:
               return LINT_STRING;
            case 6:
               return M_STRING_STRING;
            case 7:
               return ATTRIBUTES;
            case 8:
               return UNION_FIELD1;
            case 9:
               return UNION_FIELD2;
            case 10:
               return UNION_FIELD3;
            default:
               return null;
         }
      }

      public static _Fields findByThriftIdOrThrow(int fieldId) {
         _Fields fields = findByThriftId(fieldId);
         if (fields == null) {
            throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
         } else {
            return fields;
         }
      }

      @Nullable
      public static _Fields findByName(String name) {
         return (_Fields)byName.get(name);
      }

      private _Fields(short thriftId, String fieldName) {
         this._thriftId = thriftId;
         this._fieldName = fieldName;
      }

      public short getThriftFieldId() {
         return this._thriftId;
      }

      public String getFieldName() {
         return this._fieldName;
      }

      static {
         for(_Fields field : EnumSet.allOf(_Fields.class)) {
            byName.put(field.getFieldName(), field);
         }

      }
   }

   private static class ComplexStandardSchemeFactory implements SchemeFactory {
      private ComplexStandardSchemeFactory() {
      }

      public ComplexStandardScheme getScheme() {
         return new ComplexStandardScheme();
      }
   }

   private static class ComplexStandardScheme extends StandardScheme {
      private ComplexStandardScheme() {
      }

      public void read(TProtocol iprot, Complex struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.aint = iprot.readI32();
                     struct.setAintIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.aString = iprot.readString();
                     struct.setAStringIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list18 = iprot.readListBegin();
                  struct.lint = new ArrayList(_list18.size);

                  for(int _i20 = 0; _i20 < _list18.size; ++_i20) {
                     int _elem19 = iprot.readI32();
                     struct.lint.add(_elem19);
                  }

                  iprot.readListEnd();
                  struct.setLintIsSet(true);
                  break;
               case 4:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list21 = iprot.readListBegin();
                  struct.lString = new ArrayList(_list21.size);

                  for(int _i23 = 0; _i23 < _list21.size; ++_i23) {
                     String _elem22 = iprot.readString();
                     struct.lString.add(_elem22);
                  }

                  iprot.readListEnd();
                  struct.setLStringIsSet(true);
                  break;
               case 5:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list24 = iprot.readListBegin();
                  struct.lintString = new ArrayList(_list24.size);

                  for(int _i26 = 0; _i26 < _list24.size; ++_i26) {
                     IntString _elem25 = new IntString();
                     _elem25.read(iprot);
                     struct.lintString.add(_elem25);
                  }

                  iprot.readListEnd();
                  struct.setLintStringIsSet(true);
                  break;
               case 6:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map27 = iprot.readMapBegin();
                  struct.mStringString = new HashMap(2 * _map27.size);

                  for(int _i30 = 0; _i30 < _map27.size; ++_i30) {
                     String _key28 = iprot.readString();
                     String _val29 = iprot.readString();
                     struct.mStringString.put(_key28, _val29);
                  }

                  iprot.readMapEnd();
                  struct.setMStringStringIsSet(true);
                  break;
               case 7:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map31 = iprot.readMapBegin();
                  struct.attributes = new HashMap(2 * _map31.size);

                  for(int _i34 = 0; _i34 < _map31.size; ++_i34) {
                     String _key32 = iprot.readString();
                     TMap _map35 = iprot.readMapBegin();
                     Map<String, Map<String, PropValueUnion>> _val33 = new HashMap(2 * _map35.size);

                     for(int _i38 = 0; _i38 < _map35.size; ++_i38) {
                        String _key36 = iprot.readString();
                        TMap _map39 = iprot.readMapBegin();
                        Map<String, PropValueUnion> _val37 = new HashMap(2 * _map39.size);

                        for(int _i42 = 0; _i42 < _map39.size; ++_i42) {
                           String _key40 = iprot.readString();
                           PropValueUnion _val41 = new PropValueUnion();
                           _val41.read(iprot);
                           _val37.put(_key40, _val41);
                        }

                        iprot.readMapEnd();
                        _val33.put(_key36, _val37);
                     }

                     iprot.readMapEnd();
                     struct.attributes.put(_key32, _val33);
                  }

                  iprot.readMapEnd();
                  struct.setAttributesIsSet(true);
                  break;
               case 8:
                  if (schemeField.type == 12) {
                     struct.unionField1 = new PropValueUnion();
                     struct.unionField1.read(iprot);
                     struct.setUnionField1IsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 12) {
                     struct.unionField2 = new PropValueUnion();
                     struct.unionField2.read(iprot);
                     struct.setUnionField2IsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 10:
                  if (schemeField.type == 12) {
                     struct.unionField3 = new PropValueUnion();
                     struct.unionField3.read(iprot);
                     struct.setUnionField3IsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, Complex struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Complex.STRUCT_DESC);
         oprot.writeFieldBegin(Complex.AINT_FIELD_DESC);
         oprot.writeI32(struct.aint);
         oprot.writeFieldEnd();
         if (struct.aString != null) {
            oprot.writeFieldBegin(Complex.A_STRING_FIELD_DESC);
            oprot.writeString(struct.aString);
            oprot.writeFieldEnd();
         }

         if (struct.lint != null) {
            oprot.writeFieldBegin(Complex.LINT_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)8, struct.lint.size()));

            for(int _iter43 : struct.lint) {
               oprot.writeI32(_iter43);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.lString != null) {
            oprot.writeFieldBegin(Complex.L_STRING_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.lString.size()));

            for(String _iter44 : struct.lString) {
               oprot.writeString(_iter44);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.lintString != null) {
            oprot.writeFieldBegin(Complex.LINT_STRING_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.lintString.size()));

            for(IntString _iter45 : struct.lintString) {
               _iter45.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.mStringString != null) {
            oprot.writeFieldBegin(Complex.M_STRING_STRING_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.mStringString.size()));

            for(Map.Entry _iter46 : struct.mStringString.entrySet()) {
               oprot.writeString((String)_iter46.getKey());
               oprot.writeString((String)_iter46.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.attributes != null) {
            oprot.writeFieldBegin(Complex.ATTRIBUTES_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)13, struct.attributes.size()));

            for(Map.Entry _iter47 : struct.attributes.entrySet()) {
               oprot.writeString((String)_iter47.getKey());
               oprot.writeMapBegin(new TMap((byte)11, (byte)13, ((Map)_iter47.getValue()).size()));

               for(Map.Entry _iter48 : ((Map)_iter47.getValue()).entrySet()) {
                  oprot.writeString((String)_iter48.getKey());
                  oprot.writeMapBegin(new TMap((byte)11, (byte)12, ((Map)_iter48.getValue()).size()));

                  for(Map.Entry _iter49 : ((Map)_iter48.getValue()).entrySet()) {
                     oprot.writeString((String)_iter49.getKey());
                     ((PropValueUnion)_iter49.getValue()).write(oprot);
                  }

                  oprot.writeMapEnd();
               }

               oprot.writeMapEnd();
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.unionField1 != null) {
            oprot.writeFieldBegin(Complex.UNION_FIELD1_FIELD_DESC);
            struct.unionField1.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.unionField2 != null) {
            oprot.writeFieldBegin(Complex.UNION_FIELD2_FIELD_DESC);
            struct.unionField2.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.unionField3 != null) {
            oprot.writeFieldBegin(Complex.UNION_FIELD3_FIELD_DESC);
            struct.unionField3.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ComplexTupleSchemeFactory implements SchemeFactory {
      private ComplexTupleSchemeFactory() {
      }

      public ComplexTupleScheme getScheme() {
         return new ComplexTupleScheme();
      }
   }

   private static class ComplexTupleScheme extends TupleScheme {
      private ComplexTupleScheme() {
      }

      public void write(TProtocol prot, Complex struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetAint()) {
            optionals.set(0);
         }

         if (struct.isSetAString()) {
            optionals.set(1);
         }

         if (struct.isSetLint()) {
            optionals.set(2);
         }

         if (struct.isSetLString()) {
            optionals.set(3);
         }

         if (struct.isSetLintString()) {
            optionals.set(4);
         }

         if (struct.isSetMStringString()) {
            optionals.set(5);
         }

         if (struct.isSetAttributes()) {
            optionals.set(6);
         }

         if (struct.isSetUnionField1()) {
            optionals.set(7);
         }

         if (struct.isSetUnionField2()) {
            optionals.set(8);
         }

         if (struct.isSetUnionField3()) {
            optionals.set(9);
         }

         oprot.writeBitSet(optionals, 10);
         if (struct.isSetAint()) {
            oprot.writeI32(struct.aint);
         }

         if (struct.isSetAString()) {
            oprot.writeString(struct.aString);
         }

         if (struct.isSetLint()) {
            oprot.writeI32(struct.lint.size());

            for(int _iter50 : struct.lint) {
               oprot.writeI32(_iter50);
            }
         }

         if (struct.isSetLString()) {
            oprot.writeI32(struct.lString.size());

            for(String _iter51 : struct.lString) {
               oprot.writeString(_iter51);
            }
         }

         if (struct.isSetLintString()) {
            oprot.writeI32(struct.lintString.size());

            for(IntString _iter52 : struct.lintString) {
               _iter52.write(oprot);
            }
         }

         if (struct.isSetMStringString()) {
            oprot.writeI32(struct.mStringString.size());

            for(Map.Entry _iter53 : struct.mStringString.entrySet()) {
               oprot.writeString((String)_iter53.getKey());
               oprot.writeString((String)_iter53.getValue());
            }
         }

         if (struct.isSetAttributes()) {
            oprot.writeI32(struct.attributes.size());

            for(Map.Entry _iter54 : struct.attributes.entrySet()) {
               oprot.writeString((String)_iter54.getKey());
               oprot.writeI32(((Map)_iter54.getValue()).size());

               for(Map.Entry _iter55 : ((Map)_iter54.getValue()).entrySet()) {
                  oprot.writeString((String)_iter55.getKey());
                  oprot.writeI32(((Map)_iter55.getValue()).size());

                  for(Map.Entry _iter56 : ((Map)_iter55.getValue()).entrySet()) {
                     oprot.writeString((String)_iter56.getKey());
                     ((PropValueUnion)_iter56.getValue()).write(oprot);
                  }
               }
            }
         }

         if (struct.isSetUnionField1()) {
            struct.unionField1.write(oprot);
         }

         if (struct.isSetUnionField2()) {
            struct.unionField2.write(oprot);
         }

         if (struct.isSetUnionField3()) {
            struct.unionField3.write(oprot);
         }

      }

      public void read(TProtocol prot, Complex struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(10);
         if (incoming.get(0)) {
            struct.aint = iprot.readI32();
            struct.setAintIsSet(true);
         }

         if (incoming.get(1)) {
            struct.aString = iprot.readString();
            struct.setAStringIsSet(true);
         }

         if (incoming.get(2)) {
            TList _list57 = iprot.readListBegin((byte)8);
            struct.lint = new ArrayList(_list57.size);

            for(int _i59 = 0; _i59 < _list57.size; ++_i59) {
               int _elem58 = iprot.readI32();
               struct.lint.add(_elem58);
            }

            struct.setLintIsSet(true);
         }

         if (incoming.get(3)) {
            TList _list60 = iprot.readListBegin((byte)11);
            struct.lString = new ArrayList(_list60.size);

            for(int _i62 = 0; _i62 < _list60.size; ++_i62) {
               String _elem61 = iprot.readString();
               struct.lString.add(_elem61);
            }

            struct.setLStringIsSet(true);
         }

         if (incoming.get(4)) {
            TList _list63 = iprot.readListBegin((byte)12);
            struct.lintString = new ArrayList(_list63.size);

            for(int _i65 = 0; _i65 < _list63.size; ++_i65) {
               IntString _elem64 = new IntString();
               _elem64.read(iprot);
               struct.lintString.add(_elem64);
            }

            struct.setLintStringIsSet(true);
         }

         if (incoming.get(5)) {
            TMap _map66 = iprot.readMapBegin((byte)11, (byte)11);
            struct.mStringString = new HashMap(2 * _map66.size);

            for(int _i69 = 0; _i69 < _map66.size; ++_i69) {
               String _key67 = iprot.readString();
               String _val68 = iprot.readString();
               struct.mStringString.put(_key67, _val68);
            }

            struct.setMStringStringIsSet(true);
         }

         if (incoming.get(6)) {
            TMap _map70 = iprot.readMapBegin((byte)11, (byte)13);
            struct.attributes = new HashMap(2 * _map70.size);

            for(int _i73 = 0; _i73 < _map70.size; ++_i73) {
               String _key71 = iprot.readString();
               TMap _map74 = iprot.readMapBegin((byte)11, (byte)13);
               Map<String, Map<String, PropValueUnion>> _val72 = new HashMap(2 * _map74.size);

               for(int _i77 = 0; _i77 < _map74.size; ++_i77) {
                  String _key75 = iprot.readString();
                  TMap _map78 = iprot.readMapBegin((byte)11, (byte)12);
                  Map<String, PropValueUnion> _val76 = new HashMap(2 * _map78.size);

                  for(int _i81 = 0; _i81 < _map78.size; ++_i81) {
                     String _key79 = iprot.readString();
                     PropValueUnion _val80 = new PropValueUnion();
                     _val80.read(iprot);
                     _val76.put(_key79, _val80);
                  }

                  _val72.put(_key75, _val76);
               }

               struct.attributes.put(_key71, _val72);
            }

            struct.setAttributesIsSet(true);
         }

         if (incoming.get(7)) {
            struct.unionField1 = new PropValueUnion();
            struct.unionField1.read(iprot);
            struct.setUnionField1IsSet(true);
         }

         if (incoming.get(8)) {
            struct.unionField2 = new PropValueUnion();
            struct.unionField2.read(iprot);
            struct.setUnionField2IsSet(true);
         }

         if (incoming.get(9)) {
            struct.unionField3 = new PropValueUnion();
            struct.unionField3.read(iprot);
            struct.setUnionField3IsSet(true);
         }

      }
   }
}
