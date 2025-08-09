package org.codehaus.janino.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.commons.nullanalysis.Nullable;
import org.codehaus.janino.Descriptor;
import org.codehaus.janino.MethodDescriptor;
import org.codehaus.janino.Mod;

public class ClassFile implements Annotatable {
   private static final int CLASS_FILE_MAGIC = -889275714;
   public static final short MAJOR_VERSION_JDK_1_1 = 45;
   public static final short MINOR_VERSION_JDK_1_1 = 3;
   public static final short MAJOR_VERSION_JDK_1_2 = 46;
   public static final short MINOR_VERSION_JDK_1_2 = 0;
   public static final short MAJOR_VERSION_JDK_1_3 = 47;
   public static final short MINOR_VERSION_JDK_1_3 = 0;
   public static final short MAJOR_VERSION_JDK_1_4 = 48;
   public static final short MINOR_VERSION_JDK_1_4 = 0;
   public static final short MAJOR_VERSION_JDK_1_5 = 49;
   public static final short MINOR_VERSION_JDK_1_5 = 0;
   public static final short MAJOR_VERSION_JDK_1_6 = 50;
   public static final short MINOR_VERSION_JDK_1_6 = 0;
   public static final short MAJOR_VERSION_JDK_1_7 = 51;
   public static final short MINOR_VERSION_JDK_1_7 = 0;
   public static final short MAJOR_VERSION_JDK_1_8 = 52;
   public static final short MINOR_VERSION_JDK_1_8 = 0;
   public static final short MAJOR_VERSION_JDK_1_9 = 53;
   public static final short MINOR_VERSION_JDK_1_9 = 0;
   public static final short MAJOR_VERSION_JDK_1_10 = 54;
   public static final short MINOR_VERSION_JDK_1_10 = 0;
   public static final short MAJOR_VERSION_JDK_1_11 = 55;
   public static final short MINOR_VERSION_JDK_1_11 = 0;
   public static final short MAJOR_VERSION_JDK_1_12 = 56;
   public static final short MINOR_VERSION_JDK_1_12 = 0;
   private short majorVersion;
   private short minorVersion;
   private final List constantPool;
   public final short accessFlags;
   public final short thisClass;
   public final short superclass;
   public final short[] interfaces;
   public final List fieldInfos;
   public final List methodInfos;
   private final List attributes;
   private final Map constantPoolMap;

   public ClassFile(short accessFlags, String thisClassFd, @Nullable String superclassFd, String[] interfaceFds) {
      String jcv = System.getProperty("java.class.version");
      Matcher m = Pattern.compile("(\\d+)\\.(\\d+)").matcher(jcv);
      if (!m.matches()) {
         throw new AssertionError("Unrecognized JVM class file version \"" + jcv + "\"");
      } else {
         this.majorVersion = Short.parseShort(m.group(1));
         this.minorVersion = Short.parseShort(m.group(2));
         this.constantPool = new ArrayList();
         this.constantPool.add((Object)null);
         this.constantPoolMap = new HashMap();

         assert (accessFlags & 512) == 0 || (accessFlags & 17456) == 1024 : Integer.toString(accessFlags & '\uffff', 16);

         assert (accessFlags & 512) != 0 || (accessFlags & 8192) == 0 && (accessFlags & 1040) != 1040 : Integer.toString(accessFlags & '\uffff', 16);

         this.accessFlags = accessFlags;
         this.thisClass = this.addConstantClassInfo(thisClassFd);
         this.superclass = superclassFd == null ? 0 : this.addConstantClassInfo(superclassFd);
         this.interfaces = new short[interfaceFds.length];

         for(int i = 0; i < interfaceFds.length; ++i) {
            this.interfaces[i] = this.addConstantClassInfo(interfaceFds[i]);
         }

         this.fieldInfos = new ArrayList();
         this.methodInfos = new ArrayList();
         this.attributes = new ArrayList();
      }
   }

   public void addSourceFileAttribute(String sourceFileName) {
      this.attributes.add(new SourceFileAttribute(this.addConstantUtf8Info("SourceFile"), this.addConstantUtf8Info(sourceFileName)));
   }

   public void addDeprecatedAttribute() {
      this.attributes.add(new DeprecatedAttribute(this.addConstantUtf8Info("Deprecated")));
   }

   @Nullable
   public InnerClassesAttribute getInnerClassesAttribute() {
      return (InnerClassesAttribute)this.findAttribute(this.attributes, "InnerClasses");
   }

   @Nullable
   public SignatureAttribute getSignatureAttribute() {
      return (SignatureAttribute)this.findAttribute(this.attributes, "Signature");
   }

   @Nullable
   private AttributeInfo findAttribute(List attributes, String attributeName) throws ClassFormatError {
      Short nameIndex = (Short)this.constantPoolMap.get(new ConstantUtf8Info(attributeName));
      if (nameIndex == null) {
         return null;
      } else {
         AttributeInfo result = null;

         for(AttributeInfo ai : attributes) {
            if (ai.nameIndex == nameIndex) {
               if (result != null) {
                  throw new ClassFileException("Duplicate \"" + attributeName + "\" attribute");
               }

               result = ai;
            }
         }

         return result;
      }
   }

   public void addInnerClassesAttributeEntry(InnerClassesAttribute.Entry entry) {
      InnerClassesAttribute ica = this.getInnerClassesAttribute();
      if (ica == null) {
         ica = new InnerClassesAttribute(this.addConstantUtf8Info("InnerClasses"));
         this.attributes.add(ica);
      }

      ica.getEntries().add(entry);
   }

   @Nullable
   private AnnotationsAttribute getAnnotationsAttribute(boolean runtimeVisible, List attributes) {
      String attributeName = runtimeVisible ? "RuntimeVisibleAnnotations" : "RuntimeInvisibleAnnotations";
      return (AnnotationsAttribute)this.findAttribute(attributes, attributeName);
   }

   public Annotation[] getAnnotations(boolean runtimeVisible) {
      AnnotationsAttribute aa = this.getAnnotationsAttribute(runtimeVisible, this.attributes);
      return aa == null ? new Annotation[0] : (Annotation[])aa.annotations.toArray(new Annotation[aa.annotations.size()]);
   }

   public void addAnnotationsAttributeEntry(boolean runtimeVisible, String fieldDescriptor, Map elementValuePairs) {
      this.addAnnotationsAttributeEntry(runtimeVisible, fieldDescriptor, elementValuePairs, this.attributes);
   }

   private void addAnnotationsAttributeEntry(boolean runtimeVisible, String fieldDescriptor, Map elementValuePairs, List target) {
      AnnotationsAttribute aa = this.getAnnotationsAttribute(runtimeVisible, target);
      if (aa == null) {
         String attributeName = runtimeVisible ? "RuntimeVisibleAnnotations" : "RuntimeInvisibleAnnotations";
         aa = new AnnotationsAttribute(this.addConstantUtf8Info(attributeName));
         target.add(aa);
      }

      aa.getAnnotations().add(new Annotation(this.addConstantUtf8Info(fieldDescriptor), elementValuePairs));
   }

   public ClassFile(InputStream inputStream) throws IOException {
      DataInputStream dis = inputStream instanceof DataInputStream ? (DataInputStream)inputStream : new DataInputStream(inputStream);
      int magic = dis.readInt();
      if (magic != -889275714) {
         throw new ClassFileException("Invalid magic number");
      } else {
         this.minorVersion = dis.readShort();
         this.majorVersion = dis.readShort();
         this.constantPool = new ArrayList();
         this.constantPoolMap = new HashMap();
         this.loadConstantPool(dis);
         this.accessFlags = dis.readShort();
         this.thisClass = dis.readShort();
         this.superclass = dis.readShort();
         this.interfaces = readShortArray(dis);
         this.fieldInfos = Collections.unmodifiableList(this.loadFields(dis));
         this.methodInfos = Collections.unmodifiableList(this.loadMethods(dis));
         this.attributes = Collections.unmodifiableList(this.loadAttributes(dis));
      }
   }

   public String getThisClassName() {
      return this.getConstantClassInfo(this.thisClass).getName(this).replace('/', '.');
   }

   public void setVersion(short majorVersion, short minorVersion) {
      this.majorVersion = majorVersion;
      this.minorVersion = minorVersion;
   }

   public short getMajorVersion() {
      return this.majorVersion;
   }

   public short getMinorVersion() {
      return this.minorVersion;
   }

   public short addConstantClassInfo(String typeFd) {
      String s;
      if (Descriptor.isClassOrInterfaceReference(typeFd)) {
         s = Descriptor.toInternalForm(typeFd);
      } else {
         if (!Descriptor.isArrayReference(typeFd)) {
            throw new ClassFileException("\"" + Descriptor.toString(typeFd) + "\" is neither a class nor an array");
         }

         s = typeFd;
      }

      return this.addToConstantPool(new ConstantClassInfo(this.addConstantUtf8Info(s)));
   }

   public short addConstantFieldrefInfo(String classFd, String fieldName, String fieldFd) {
      return this.addToConstantPool(new ConstantFieldrefInfo(this.addConstantClassInfo(classFd), this.addConstantNameAndTypeInfo(fieldName, fieldFd)));
   }

   public short addConstantMethodrefInfo(String classFd, String methodName, String methodMd) {
      return this.addToConstantPool(new ConstantMethodrefInfo(this.addConstantClassInfo(classFd), this.addConstantNameAndTypeInfo(methodName, methodMd)));
   }

   public short addConstantInterfaceMethodrefInfo(String classFd, String methodName, String methodMd) {
      return this.addToConstantPool(new ConstantInterfaceMethodrefInfo(this.addConstantClassInfo(classFd), this.addConstantNameAndTypeInfo(methodName, methodMd)));
   }

   public short addConstantStringInfo(String string) {
      return this.addToConstantPool(new ConstantStringInfo(this.addConstantUtf8Info(string)));
   }

   public short addConstantIntegerInfo(int value) {
      return this.addToConstantPool(new ConstantIntegerInfo(value));
   }

   public short addConstantFloatInfo(float value) {
      return this.addToConstantPool(new ConstantFloatInfo(value));
   }

   public short addConstantLongInfo(long value) {
      return this.addToConstantPool(new ConstantLongInfo(value));
   }

   public short addConstantDoubleInfo(double value) {
      return this.addToConstantPool(new ConstantDoubleInfo(value));
   }

   private short addConstantNameAndTypeInfo(String name, String descriptor) {
      return this.addToConstantPool(new ConstantNameAndTypeInfo(this.addConstantUtf8Info(name), this.addConstantUtf8Info(descriptor)));
   }

   public short addConstantUtf8Info(String s) {
      return this.addToConstantPool(new ConstantUtf8Info(s));
   }

   private short addConstantSifldInfo(Object cv) {
      if (cv instanceof String) {
         return this.addConstantStringInfo((String)cv);
      } else if (!(cv instanceof Byte) && !(cv instanceof Short) && !(cv instanceof Integer)) {
         if (cv instanceof Boolean) {
            return this.addConstantIntegerInfo((Boolean)cv ? 1 : 0);
         } else if (cv instanceof Character) {
            return this.addConstantIntegerInfo((Character)cv);
         } else if (cv instanceof Float) {
            return this.addConstantFloatInfo((Float)cv);
         } else if (cv instanceof Long) {
            return this.addConstantLongInfo((Long)cv);
         } else if (cv instanceof Double) {
            return this.addConstantDoubleInfo((Double)cv);
         } else {
            throw new ClassFileException("Unexpected constant value type \"" + cv.getClass().getName() + "\"");
         }
      } else {
         return this.addConstantIntegerInfo(((Number)cv).intValue());
      }
   }

   private short addToConstantPool(ConstantPoolInfo cpi) {
      Short index = (Short)this.constantPoolMap.get(cpi);
      if (index != null) {
         return index;
      } else {
         short res = (short)this.constantPool.size();
         this.constantPool.add(cpi);
         if (cpi.isWide()) {
            this.constantPool.add((Object)null);
         }

         if (this.constantPool.size() > 65535) {
            throw new ClassFileException("Constant pool for class " + this.getThisClassName() + " has grown past JVM limit of 0xFFFF");
         } else {
            this.constantPoolMap.put(cpi, res);
            return res;
         }
      }
   }

   public FieldInfo addFieldInfo(short accessFlags, String fieldName, String fieldTypeFd, @Nullable Object constantValue) {
      List<AttributeInfo> attributes = new ArrayList();
      if (constantValue != null) {
         attributes.add(new ConstantValueAttribute(this.addConstantUtf8Info("ConstantValue"), this.addConstantSifldInfo(constantValue)));
      }

      FieldInfo fi = new FieldInfo(accessFlags, this.addConstantUtf8Info(fieldName), this.addConstantUtf8Info(fieldTypeFd), attributes);
      this.fieldInfos.add(fi);
      return fi;
   }

   public MethodInfo addMethodInfo(short accessFlags, String methodName, MethodDescriptor methodMd) {
      int parameterCount = Mod.isStatic(accessFlags) ? 0 : 1;

      for(String fd : methodMd.parameterFds) {
         parameterCount += Descriptor.size(fd);
      }

      if (parameterCount > 255) {
         throw new ClassFileException("Method \"" + methodName + "\" has too many parameters (" + parameterCount + ")");
      } else {
         MethodInfo mi = new MethodInfo(accessFlags, this.addConstantUtf8Info(methodName), this.addConstantUtf8Info(methodMd.toString()), new ArrayList());
         this.methodInfos.add(mi);
         return mi;
      }
   }

   public ConstantPoolInfo getConstantPoolInfo(short index) {
      ConstantPoolInfo result = (ConstantPoolInfo)this.constantPool.get('\uffff' & index);
      if (result == null) {
         throw new ClassFileException("Invalid constant pool index " + index);
      } else {
         return result;
      }
   }

   public ConstantClassInfo getConstantClassInfo(short index) {
      return (ConstantClassInfo)this.getConstantPoolInfo(index);
   }

   public ConstantFieldrefInfo getConstantFieldrefInfo(short index) {
      return (ConstantFieldrefInfo)this.getConstantPoolInfo(index);
   }

   public ConstantInterfaceMethodrefInfo getConstantInterfaceMethodrefInfo(short index) {
      return (ConstantInterfaceMethodrefInfo)this.getConstantPoolInfo(index);
   }

   public ConstantInvokeDynamicInfo getConstantInvokeDynamicInfo(short index) {
      return (ConstantInvokeDynamicInfo)this.getConstantPoolInfo(index);
   }

   public ConstantMethodHandleInfo getConstantMethodHandleInfo(short index) {
      return (ConstantMethodHandleInfo)this.getConstantPoolInfo(index);
   }

   public ConstantMethodrefInfo getConstantMethodrefInfo(short index) {
      return (ConstantMethodrefInfo)this.getConstantPoolInfo(index);
   }

   public ConstantMethodTypeInfo getConstantMethodTypeInfo(short index) {
      return (ConstantMethodTypeInfo)this.getConstantPoolInfo(index);
   }

   public ConstantNameAndTypeInfo getConstantNameAndTypeInfo(short index) {
      return (ConstantNameAndTypeInfo)this.getConstantPoolInfo(index);
   }

   public ConstantUtf8Info getConstantUtf8Info(short index) {
      return (ConstantUtf8Info)this.getConstantPoolInfo(index);
   }

   public ConstantValuePoolInfo getConstantValuePoolInfo(short index) {
      return (ConstantValuePoolInfo)this.getConstantPoolInfo(index);
   }

   public int getConstantPoolSize() {
      return this.constantPool.size();
   }

   public String getConstantUtf8(short index) {
      return this.getConstantUtf8Info(index).s;
   }

   private static byte[] readLengthAndBytes(DataInputStream dis) throws IOException {
      byte[] ba = new byte[dis.readInt()];
      dis.readFully(ba);
      return ba;
   }

   private static short[] readShortArray(DataInputStream dis) throws IOException {
      short[] result = new short[dis.readUnsignedShort()];

      for(int i = 0; i < result.length; ++i) {
         result[i] = dis.readShort();
      }

      return result;
   }

   private void loadConstantPool(DataInputStream dis) throws IOException {
      this.constantPool.clear();
      this.constantPoolMap.clear();
      int constantPoolCount = dis.readUnsignedShort();
      this.constantPool.add((Object)null);

      for(int i = 1; i < constantPoolCount; ++i) {
         ConstantPoolInfo cpi = ClassFile.ConstantPoolInfo.loadConstantPoolInfo(dis);
         this.constantPool.add(cpi);
         this.constantPoolMap.put(cpi, (short)i);
         if (cpi.isWide()) {
            this.constantPool.add((Object)null);
            ++i;
         }
      }

   }

   private List loadFields(DataInputStream dis) throws IOException {
      List<FieldInfo> result = new ArrayList();

      for(int i = dis.readUnsignedShort(); i > 0; --i) {
         result.add(new FieldInfo(dis.readShort(), dis.readShort(), dis.readShort(), this.loadAttributes(dis)));
      }

      return result;
   }

   private List loadMethods(DataInputStream dis) throws IOException {
      int methodsCount = dis.readUnsignedShort();
      List<MethodInfo> methods = new ArrayList(methodsCount);

      for(int i = 0; i < methodsCount; ++i) {
         methods.add(this.loadMethodInfo(dis));
      }

      return methods;
   }

   private List loadAttributes(DataInputStream dis) throws IOException {
      int attributesCount = dis.readUnsignedShort();
      List<AttributeInfo> attributes = new ArrayList(attributesCount);

      for(int i = 0; i < attributesCount; ++i) {
         attributes.add(this.loadAttribute(dis));
      }

      return attributes;
   }

   public void store(OutputStream os) throws IOException {
      DataOutputStream dos = os instanceof DataOutputStream ? (DataOutputStream)os : new DataOutputStream(os);
      dos.writeInt(-889275714);
      dos.writeShort(this.minorVersion);
      dos.writeShort(this.majorVersion);
      storeConstantPool(dos, this.constantPool);
      dos.writeShort(this.accessFlags);
      dos.writeShort(this.thisClass);
      dos.writeShort(this.superclass);
      storeShortArray(dos, this.interfaces);
      storeFields(dos, this.fieldInfos);
      storeMethods(dos, this.methodInfos);
      storeAttributes(dos, this.attributes);
   }

   private static void storeConstantPool(DataOutputStream dos, List constantPool) throws IOException {
      dos.writeShort(constantPool.size());

      for(int i = 1; i < constantPool.size(); ++i) {
         ConstantPoolInfo cpi = (ConstantPoolInfo)constantPool.get(i);
         if (cpi != null) {
            cpi.store(dos);
         }
      }

   }

   private static void storeShortArray(DataOutputStream dos, short[] sa) throws IOException {
      dos.writeShort(sa.length);

      for(short s : sa) {
         dos.writeShort(s);
      }

   }

   private static void storeFields(DataOutputStream dos, List fieldInfos) throws IOException {
      dos.writeShort(fieldInfos.size());

      for(FieldInfo fieldInfo : fieldInfos) {
         fieldInfo.store(dos);
      }

   }

   private static void storeMethods(DataOutputStream dos, List methodInfos) throws IOException {
      dos.writeShort(methodInfos.size());

      for(MethodInfo methodInfo : methodInfos) {
         methodInfo.store(dos);
      }

   }

   private static void storeAttributes(DataOutputStream dos, List attributeInfos) throws IOException {
      dos.writeShort(attributeInfos.size());

      for(AttributeInfo attributeInfo : attributeInfos) {
         attributeInfo.store(dos);
      }

   }

   public static String getSourceResourceName(String className) {
      int idx = className.lastIndexOf(46) + 1;
      idx = className.indexOf(36, idx);
      if (idx != -1) {
         className = className.substring(0, idx);
      }

      return className.replace('.', '/') + ".java";
   }

   public static String getClassFileResourceName(String className) {
      return className.replace('.', '/') + ".class";
   }

   public byte[] toByteArray() {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      try {
         this.store(baos);
      } catch (IOException ex) {
         throw new ClassFileException(ex.toString(), ex);
      }

      return baos.toByteArray();
   }

   private MethodInfo loadMethodInfo(DataInputStream dis) throws IOException {
      return new MethodInfo(dis.readShort(), dis.readShort(), dis.readShort(), this.loadAttributes(dis));
   }

   private AttributeInfo loadAttribute(DataInputStream dis) throws IOException {
      short attributeNameIndex = dis.readShort();
      int attributeLength = dis.readInt();
      final byte[] ba = new byte[attributeLength];
      dis.readFully(ba);
      ByteArrayInputStream bais = new ByteArrayInputStream(ba);
      DataInputStream bdis = new DataInputStream(bais);
      String attributeName = this.getConstantUtf8(attributeNameIndex);
      AttributeInfo result;
      if ("ConstantValue".equals(attributeName)) {
         result = ClassFile.ConstantValueAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("Code".equals(attributeName)) {
         result = ClassFile.CodeAttribute.loadBody(attributeNameIndex, this, bdis);
      } else if ("Exceptions".equals(attributeName)) {
         result = ClassFile.ExceptionsAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("InnerClasses".equals(attributeName)) {
         result = ClassFile.InnerClassesAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("Synthetic".equals(attributeName)) {
         result = ClassFile.SyntheticAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("Signature".equals(attributeName)) {
         result = ClassFile.SignatureAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("SourceFile".equals(attributeName)) {
         result = ClassFile.SourceFileAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("StackMapTable".equals(attributeName)) {
         result = ClassFile.StackMapTableAttribute.loadBody(attributeNameIndex, bdis, this);
      } else if ("LineNumberTable".equals(attributeName)) {
         result = ClassFile.LineNumberTableAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("LocalVariableTable".equals(attributeName)) {
         result = ClassFile.LocalVariableTableAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("Deprecated".equals(attributeName)) {
         result = ClassFile.DeprecatedAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("AnnotationDefault".equals(attributeName)) {
         result = ClassFile.AnnotationDefaultAttribute.loadBody(attributeNameIndex, bdis);
      } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
         result = ClassFile.AnnotationsAttribute.loadBody(attributeNameIndex, bdis);
      } else {
         if (!"RuntimeInvisibleAnnotations".equals(attributeName)) {
            return new AttributeInfo(attributeNameIndex) {
               protected void storeBody(DataOutputStream dos) throws IOException {
                  dos.write(ba);
               }
            };
         }

         result = ClassFile.AnnotationsAttribute.loadBody(attributeNameIndex, bdis);
      }

      if (bais.available() > 0) {
         throw new ClassFileException(ba.length - bais.available() + " bytes of trailing garbage in body of attribute \"" + attributeName + "\"");
      } else {
         return result;
      }
   }

   private static ElementValue loadElementValue(DataInputStream dis) throws IOException {
      byte tag = dis.readByte();
      switch (tag) {
         case 64:
            return ClassFile.AnnotationsAttribute.loadAnnotation(dis);
         case 65:
         case 69:
         case 71:
         case 72:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 100:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         default:
            throw new ClassFileException("Invalid element-value-pair tag '" + (char)tag + "'");
         case 66:
            return new ByteElementValue(dis.readShort());
         case 67:
            return new CharElementValue(dis.readShort());
         case 68:
            return new DoubleElementValue(dis.readShort());
         case 70:
            return new FloatElementValue(dis.readShort());
         case 73:
            return new IntElementValue(dis.readShort());
         case 74:
            return new LongElementValue(dis.readShort());
         case 83:
            return new ShortElementValue(dis.readShort());
         case 90:
            return new BooleanElementValue(dis.readShort());
         case 91:
            ElementValue[] values = new ElementValue[dis.readUnsignedShort()];

            for(int i = 0; i < values.length; ++i) {
               values[i] = loadElementValue(dis);
            }

            return new ArrayElementValue(values);
         case 99:
            return new ClassElementValue(dis.readShort());
         case 101:
            return new EnumConstValue(dis.readShort(), dis.readShort());
         case 115:
            return new StringElementValue(dis.readShort());
      }
   }

   public StackMapTableAttribute.ObjectVariableInfo newObjectVariableInfo(String fieldDescriptor) {
      return new StackMapTableAttribute.ObjectVariableInfo(this.addConstantClassInfo(fieldDescriptor), fieldDescriptor);
   }

   public StackMapTableAttribute.UninitializedVariableInfo newUninitializedVariableInfo(short offset) {
      return new StackMapTableAttribute.UninitializedVariableInfo(offset);
   }

   public String toString() {
      try {
         return this.getConstantUtf8(this.getConstantClassInfo(this.thisClass).nameIndex);
      } catch (Exception var2) {
         return super.toString();
      }
   }

   public static class ClassFileException extends RuntimeException {
      public ClassFileException(String message) {
         super(message);
      }

      public ClassFileException(String message, Throwable cause) {
         super(message, cause);
      }
   }

   public abstract static class ConstantPoolInfo {
      protected abstract void store(DataOutputStream var1) throws IOException;

      public abstract boolean isWide();

      private static ConstantPoolInfo loadConstantPoolInfo(DataInputStream dis) throws IOException {
         byte tag = dis.readByte();
         switch (tag) {
            case 1:
               return new ConstantUtf8Info(dis.readUTF());
            case 2:
            case 13:
            case 14:
            case 17:
            default:
               throw new ClassFileException("Invalid constant pool tag " + tag);
            case 3:
               return new ConstantIntegerInfo(dis.readInt());
            case 4:
               return new ConstantFloatInfo(dis.readFloat());
            case 5:
               return new ConstantLongInfo(dis.readLong());
            case 6:
               return new ConstantDoubleInfo(dis.readDouble());
            case 7:
               return new ConstantClassInfo(dis.readShort());
            case 8:
               return new ConstantStringInfo(dis.readShort());
            case 9:
               return new ConstantFieldrefInfo(dis.readShort(), dis.readShort());
            case 10:
               return new ConstantMethodrefInfo(dis.readShort(), dis.readShort());
            case 11:
               return new ConstantInterfaceMethodrefInfo(dis.readShort(), dis.readShort());
            case 12:
               return new ConstantNameAndTypeInfo(dis.readShort(), dis.readShort());
            case 15:
               return new ConstantMethodHandleInfo(dis.readByte(), dis.readShort());
            case 16:
               return new ConstantMethodTypeInfo(dis.readShort());
            case 18:
               return new ConstantInvokeDynamicInfo(dis.readShort(), dis.readShort());
         }
      }
   }

   public abstract static class ConstantValuePoolInfo extends ConstantPoolInfo {
      public abstract Object getValue(ClassFile var1);
   }

   public static class ConstantClassInfo extends ConstantPoolInfo {
      private final short nameIndex;

      public ConstantClassInfo(short nameIndex) {
         this.nameIndex = nameIndex;
      }

      public String getName(ClassFile classFile) {
         return classFile.getConstantUtf8(this.nameIndex);
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(7);
         dos.writeShort(this.nameIndex);
      }

      public String toString() {
         return "CONSTANT_Class_info(" + this.nameIndex + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantClassInfo && ((ConstantClassInfo)o).nameIndex == this.nameIndex;
      }

      public int hashCode() {
         return this.nameIndex;
      }
   }

   public static class ConstantFieldrefInfo extends ConstantPoolInfo {
      private final short classIndex;
      private final short nameAndTypeIndex;

      public ConstantFieldrefInfo(short classIndex, short nameAndTypeIndex) {
         this.classIndex = classIndex;
         this.nameAndTypeIndex = nameAndTypeIndex;
      }

      public ConstantClassInfo getClassInfo(ClassFile classFile) {
         return classFile.getConstantClassInfo(this.classIndex);
      }

      public ConstantNameAndTypeInfo getNameAndType(ClassFile classFile) {
         return classFile.getConstantNameAndTypeInfo(this.nameAndTypeIndex);
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(9);
         dos.writeShort(this.classIndex);
         dos.writeShort(this.nameAndTypeIndex);
      }

      public String toString() {
         return "CONSTANT_Fieldref_info(" + this.classIndex + ", " + this.nameAndTypeIndex + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantFieldrefInfo && ((ConstantFieldrefInfo)o).classIndex == this.classIndex && ((ConstantFieldrefInfo)o).nameAndTypeIndex == this.nameAndTypeIndex;
      }

      public int hashCode() {
         return this.classIndex + (this.nameAndTypeIndex << 16);
      }
   }

   public static class ConstantMethodrefInfo extends ConstantPoolInfo {
      private final short classIndex;
      private final short nameAndTypeIndex;

      public ConstantMethodrefInfo(short classIndex, short nameAndTypeIndex) {
         this.classIndex = classIndex;
         this.nameAndTypeIndex = nameAndTypeIndex;
      }

      public ConstantClassInfo getClassInfo(ClassFile classFile) {
         return classFile.getConstantClassInfo(this.classIndex);
      }

      public ConstantNameAndTypeInfo getNameAndType(ClassFile classFile) {
         return classFile.getConstantNameAndTypeInfo(this.nameAndTypeIndex);
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(10);
         dos.writeShort(this.classIndex);
         dos.writeShort(this.nameAndTypeIndex);
      }

      public String toString() {
         return "CONSTANT_Methodref_info(" + this.classIndex + ", " + this.nameAndTypeIndex + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantMethodrefInfo && ((ConstantMethodrefInfo)o).classIndex == this.classIndex && ((ConstantMethodrefInfo)o).nameAndTypeIndex == this.nameAndTypeIndex;
      }

      public int hashCode() {
         return this.classIndex + (this.nameAndTypeIndex << 16);
      }
   }

   public static class ConstantInterfaceMethodrefInfo extends ConstantPoolInfo {
      private final short classIndex;
      private final short nameAndTypeIndex;

      public ConstantInterfaceMethodrefInfo(short classIndex, short nameAndTypeIndex) {
         this.classIndex = classIndex;
         this.nameAndTypeIndex = nameAndTypeIndex;
      }

      public ConstantClassInfo getClassInfo(ClassFile classFile) {
         return classFile.getConstantClassInfo(this.classIndex);
      }

      public ConstantNameAndTypeInfo getNameAndType(ClassFile classFile) {
         return classFile.getConstantNameAndTypeInfo(this.nameAndTypeIndex);
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(11);
         dos.writeShort(this.classIndex);
         dos.writeShort(this.nameAndTypeIndex);
      }

      public String toString() {
         return "CONSTANT_InterfaceMethodref_info(" + this.classIndex + ", " + this.nameAndTypeIndex + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantInterfaceMethodrefInfo && ((ConstantInterfaceMethodrefInfo)o).classIndex == this.classIndex && ((ConstantInterfaceMethodrefInfo)o).nameAndTypeIndex == this.nameAndTypeIndex;
      }

      public int hashCode() {
         return this.classIndex + (this.nameAndTypeIndex << 16);
      }
   }

   static class ConstantStringInfo extends ConstantValuePoolInfo {
      private final short stringIndex;

      ConstantStringInfo(short stringIndex) {
         this.stringIndex = stringIndex;
      }

      public Object getValue(ClassFile classFile) {
         return classFile.getConstantUtf8(this.stringIndex);
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(8);
         dos.writeShort(this.stringIndex);
      }

      public String toString() {
         return "CONSTANT_String_info(" + this.stringIndex + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantStringInfo && ((ConstantStringInfo)o).stringIndex == this.stringIndex;
      }

      public int hashCode() {
         return this.stringIndex;
      }
   }

   private static class ConstantIntegerInfo extends ConstantValuePoolInfo {
      private final int value;

      ConstantIntegerInfo(int value) {
         this.value = value;
      }

      public Object getValue(ClassFile classFile) {
         return this.value;
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(3);
         dos.writeInt(this.value);
      }

      public String toString() {
         return "CONSTANT_Integer_info(" + this.value + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantIntegerInfo && ((ConstantIntegerInfo)o).value == this.value;
      }

      public int hashCode() {
         return this.value;
      }
   }

   private static class ConstantFloatInfo extends ConstantValuePoolInfo {
      private final float value;

      ConstantFloatInfo(float value) {
         this.value = value;
      }

      public Object getValue(ClassFile classFile) {
         return this.value;
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(4);
         dos.writeFloat(this.value);
      }

      public String toString() {
         return "CONSTANT_Float_info(" + this.value + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantFloatInfo && ((ConstantFloatInfo)o).value == this.value;
      }

      public int hashCode() {
         return Float.floatToIntBits(this.value);
      }
   }

   private static class ConstantLongInfo extends ConstantValuePoolInfo {
      private final long value;

      ConstantLongInfo(long value) {
         this.value = value;
      }

      public Object getValue(ClassFile classFile) {
         return this.value;
      }

      public boolean isWide() {
         return true;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(5);
         dos.writeLong(this.value);
      }

      public String toString() {
         return "CONSTANT_Long_info(" + this.value + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantLongInfo && ((ConstantLongInfo)o).value == this.value;
      }

      public int hashCode() {
         return (int)this.value ^ (int)(this.value >> 32);
      }
   }

   private static class ConstantDoubleInfo extends ConstantValuePoolInfo {
      private final double value;

      ConstantDoubleInfo(double value) {
         this.value = value;
      }

      public Object getValue(ClassFile classFile) {
         return this.value;
      }

      public boolean isWide() {
         return true;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(6);
         dos.writeDouble(this.value);
      }

      public String toString() {
         return "CONSTANT_Double_info(" + this.value + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantDoubleInfo && ((ConstantDoubleInfo)o).value == this.value;
      }

      public int hashCode() {
         long bits = Double.doubleToLongBits(this.value);
         return (int)bits ^ (int)(bits >> 32);
      }
   }

   public static class ConstantNameAndTypeInfo extends ConstantPoolInfo {
      private final short nameIndex;
      private final short descriptorIndex;

      public ConstantNameAndTypeInfo(short nameIndex, short descriptorIndex) {
         this.nameIndex = nameIndex;
         this.descriptorIndex = descriptorIndex;
      }

      public String getName(ClassFile classFile) {
         return classFile.getConstantUtf8(this.nameIndex);
      }

      public String getDescriptor(ClassFile classFile) {
         return classFile.getConstantUtf8(this.descriptorIndex);
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(12);
         dos.writeShort(this.nameIndex);
         dos.writeShort(this.descriptorIndex);
      }

      public String toString() {
         return "CONSTANT_NameAndType_info(" + this.nameIndex + ", " + this.descriptorIndex + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantNameAndTypeInfo && ((ConstantNameAndTypeInfo)o).nameIndex == this.nameIndex && ((ConstantNameAndTypeInfo)o).descriptorIndex == this.descriptorIndex;
      }

      public int hashCode() {
         return this.nameIndex + (this.descriptorIndex << 16);
      }
   }

   public static class ConstantUtf8Info extends ConstantValuePoolInfo {
      private final String s;

      public ConstantUtf8Info(String s) {
         assert s != null;

         this.s = s;
      }

      public Object getValue(ClassFile classFile) {
         return this.s;
      }

      public String getString() {
         return this.s;
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(1);

         try {
            dos.writeUTF(this.s);
         } catch (UTFDataFormatException var3) {
            throw new ClassFileException("String constant too long to store in class file");
         }
      }

      public String toString() {
         return "CONSTANT_Utf8_info(\"" + this.s + "\")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantUtf8Info && ((ConstantUtf8Info)o).s.equals(this.s);
      }

      public int hashCode() {
         return this.s.hashCode();
      }
   }

   public static class ConstantMethodHandleInfo extends ConstantPoolInfo {
      private final byte referenceKind;
      private final short referenceIndex;

      public ConstantMethodHandleInfo(byte referenceKind, short referenceIndex) {
         this.referenceKind = referenceKind;
         this.referenceIndex = referenceIndex;
      }

      public byte getReferenceKind() {
         return this.referenceKind;
      }

      public short getReferenceIndex() {
         return this.referenceIndex;
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(15);
         dos.writeByte(this.referenceKind);
         dos.writeShort(this.referenceIndex);
      }

      public String toString() {
         return "CONSTANT_MethodHandle_info(" + this.referenceKind + ", " + this.referenceIndex + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantMethodHandleInfo && ((ConstantMethodHandleInfo)o).referenceKind == this.referenceKind && ((ConstantMethodHandleInfo)o).referenceIndex == this.referenceIndex;
      }

      public int hashCode() {
         return this.referenceKind + (this.referenceIndex << 16);
      }
   }

   public static class ConstantMethodTypeInfo extends ConstantPoolInfo {
      private final short descriptorIndex;

      public ConstantMethodTypeInfo(short descriptorIndex) {
         this.descriptorIndex = descriptorIndex;
      }

      public short getDescriptorIndex() {
         return this.descriptorIndex;
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(16);
         dos.writeShort(this.descriptorIndex);
      }

      public String toString() {
         return "CONSTANT_MethodType_info(" + this.descriptorIndex + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantMethodTypeInfo && ((ConstantMethodTypeInfo)o).descriptorIndex == this.descriptorIndex;
      }

      public int hashCode() {
         return this.descriptorIndex;
      }
   }

   public static class ConstantInvokeDynamicInfo extends ConstantPoolInfo {
      private final short bootstrapMethodAttrIndex;
      private final short nameAndTypeIndex;

      public ConstantInvokeDynamicInfo(short bootstrapMethodAttrIndex, short nameAndTypeIndex) {
         this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
         this.nameAndTypeIndex = nameAndTypeIndex;
      }

      public short getBootstrapMethodAttrIndex() {
         return this.bootstrapMethodAttrIndex;
      }

      public short getNameAndTypeIndex() {
         return this.nameAndTypeIndex;
      }

      public boolean isWide() {
         return false;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeByte(18);
         dos.writeShort(this.bootstrapMethodAttrIndex);
         dos.writeShort(this.nameAndTypeIndex);
      }

      public String toString() {
         return "CONSTANT_InvokeDynamic_info(" + this.bootstrapMethodAttrIndex + ", " + this.nameAndTypeIndex + ")";
      }

      public boolean equals(@Nullable Object o) {
         return o instanceof ConstantInvokeDynamicInfo && ((ConstantInvokeDynamicInfo)o).bootstrapMethodAttrIndex == this.bootstrapMethodAttrIndex && ((ConstantInvokeDynamicInfo)o).nameAndTypeIndex == this.nameAndTypeIndex;
      }

      public int hashCode() {
         return this.bootstrapMethodAttrIndex + (this.nameAndTypeIndex << 16);
      }
   }

   public class MethodInfo implements Annotatable {
      private final short accessFlags;
      private final short nameIndex;
      private final short descriptorIndex;
      private final List attributes;

      public MethodInfo(short accessFlags, short nameIndex, short descriptorIndex, List attributes) {
         this.accessFlags = accessFlags;
         this.nameIndex = nameIndex;
         this.descriptorIndex = descriptorIndex;
         this.attributes = attributes;
      }

      public ClassFile getClassFile() {
         return ClassFile.this;
      }

      public short getAccessFlags() {
         return this.accessFlags;
      }

      public Annotation[] getAnnotations(boolean runtimeVisible) {
         AnnotationsAttribute aa = ClassFile.this.getAnnotationsAttribute(runtimeVisible, this.attributes);
         return aa == null ? new Annotation[0] : (Annotation[])aa.annotations.toArray(new Annotation[aa.annotations.size()]);
      }

      public String getName() {
         return ClassFile.this.getConstantUtf8(this.nameIndex);
      }

      public String getDescriptor() {
         return ClassFile.this.getConstantUtf8(this.descriptorIndex);
      }

      public AttributeInfo[] getAttributes() {
         return (AttributeInfo[])this.attributes.toArray(new AttributeInfo[this.attributes.size()]);
      }

      public void addAttribute(AttributeInfo attribute) {
         this.attributes.add(attribute);
      }

      public void addAnnotationsAttributeEntry(boolean runtimeVisible, String fieldDescriptor, Map elementValuePairs) {
         ClassFile.this.addAnnotationsAttributeEntry(runtimeVisible, fieldDescriptor, elementValuePairs, this.attributes);
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeShort(this.accessFlags);
         dos.writeShort(this.nameIndex);
         dos.writeShort(this.descriptorIndex);
         ClassFile.storeAttributes(dos, this.attributes);
      }

      public String toString() {
         try {
            return ClassFile.this + "." + ClassFile.this.getConstantUtf8(this.nameIndex) + "(...)";
         } catch (Exception var2) {
            return super.toString();
         }
      }
   }

   public class FieldInfo implements Annotatable {
      private final short accessFlags;
      private final short nameIndex;
      private final short descriptorIndex;
      private final List attributes;

      public FieldInfo(short accessFlags, short nameIndex, short descriptorIndex, List attributes) {
         this.accessFlags = accessFlags;
         this.nameIndex = nameIndex;
         this.descriptorIndex = descriptorIndex;
         this.attributes = attributes;
      }

      public short getAccessFlags() {
         return this.accessFlags;
      }

      public Annotation[] getAnnotations(boolean runtimeVisible) {
         AnnotationsAttribute aa = ClassFile.this.getAnnotationsAttribute(runtimeVisible, this.attributes);
         return aa == null ? new Annotation[0] : (Annotation[])aa.annotations.toArray(new Annotation[aa.annotations.size()]);
      }

      public String getName(ClassFile classFile) {
         return classFile.getConstantUtf8(this.nameIndex);
      }

      public String getDescriptor(ClassFile classFile) {
         return classFile.getConstantUtf8(this.descriptorIndex);
      }

      public AttributeInfo[] getAttributes() {
         return (AttributeInfo[])this.attributes.toArray(new AttributeInfo[this.attributes.size()]);
      }

      public void addAttribute(AttributeInfo attribute) {
         this.attributes.add(attribute);
      }

      public void addAnnotationsAttributeEntry(boolean runtimeVisible, String fieldDescriptor, Map elementValuePairs) {
         ClassFile.this.addAnnotationsAttributeEntry(runtimeVisible, fieldDescriptor, elementValuePairs, this.attributes);
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeShort(this.accessFlags);
         dos.writeShort(this.nameIndex);
         dos.writeShort(this.descriptorIndex);
         ClassFile.storeAttributes(dos, this.attributes);
      }
   }

   public abstract static class AttributeInfo {
      private final short nameIndex;

      public AttributeInfo(short nameIndex) {
         this.nameIndex = nameIndex;
      }

      public void store(DataOutputStream dos) throws IOException {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         this.storeBody(new DataOutputStream(baos));
         dos.writeShort(this.nameIndex);
         dos.writeInt(baos.size());
         baos.writeTo(dos);
      }

      protected abstract void storeBody(DataOutputStream var1) throws IOException;
   }

   public static class ConstantValueAttribute extends AttributeInfo {
      private final short constantValueIndex;

      ConstantValueAttribute(short attributeNameIndex, short constantValueIndex) {
         super(attributeNameIndex);
         this.constantValueIndex = constantValueIndex;
      }

      public ConstantValuePoolInfo getConstantValue(ClassFile classFile) {
         return classFile.getConstantValuePoolInfo(this.constantValueIndex);
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) throws IOException {
         return new ConstantValueAttribute(attributeNameIndex, dis.readShort());
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.constantValueIndex);
      }
   }

   public static class ExceptionsAttribute extends AttributeInfo {
      private final short[] exceptionIndexes;

      public ExceptionsAttribute(short attributeNameIndex, short[] exceptionIndexes) {
         super(attributeNameIndex);
         this.exceptionIndexes = exceptionIndexes;
      }

      public ConstantClassInfo[] getExceptions(ClassFile classFile) {
         ConstantClassInfo[] es = new ConstantClassInfo[this.exceptionIndexes.length];

         for(int i = 0; i < es.length; ++i) {
            es[i] = classFile.getConstantClassInfo(this.exceptionIndexes[i]);
         }

         return es;
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) throws IOException {
         return new ExceptionsAttribute(attributeNameIndex, ClassFile.readShortArray(dis));
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         ClassFile.storeShortArray(dos, this.exceptionIndexes);
      }
   }

   public static class InnerClassesAttribute extends AttributeInfo {
      private final List entries;

      InnerClassesAttribute(short attributeNameIndex) {
         super(attributeNameIndex);
         this.entries = new ArrayList();
      }

      InnerClassesAttribute(short attributeNameIndex, Entry[] entries) {
         super(attributeNameIndex);
         this.entries = new ArrayList(Arrays.asList(entries));
      }

      public List getEntries() {
         return this.entries;
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) throws IOException {
         Entry[] ics = new Entry[dis.readUnsignedShort()];

         for(short i = 0; i < ics.length; ++i) {
            ics[i] = new Entry(dis.readShort(), dis.readShort(), dis.readShort(), dis.readShort());
         }

         return new InnerClassesAttribute(attributeNameIndex, ics);
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.entries.size());

         for(Entry e : this.entries) {
            dos.writeShort(e.innerClassInfoIndex);
            dos.writeShort(e.outerClassInfoIndex);
            dos.writeShort(e.innerNameIndex);
            dos.writeShort(e.innerClassAccessFlags);
         }

      }

      public static class Entry {
         public final short innerClassInfoIndex;
         public final short outerClassInfoIndex;
         public final short innerNameIndex;
         public final short innerClassAccessFlags;

         public Entry(short innerClassInfoIndex, short outerClassInfoIndex, short innerNameIndex, short innerClassAccessFlags) {
            this.innerClassInfoIndex = innerClassInfoIndex;
            this.outerClassInfoIndex = outerClassInfoIndex;
            this.innerNameIndex = innerNameIndex;
            this.innerClassAccessFlags = innerClassAccessFlags;
         }
      }
   }

   public static class AnnotationsAttribute extends AttributeInfo {
      private final List annotations;

      AnnotationsAttribute(short attributeNameIndex) {
         super(attributeNameIndex);
         this.annotations = new ArrayList();
      }

      AnnotationsAttribute(short attributeNameIndex, Annotation[] annotations) {
         super(attributeNameIndex);
         this.annotations = new ArrayList(Arrays.asList(annotations));
      }

      public List getAnnotations() {
         return this.annotations;
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) throws IOException {
         Annotation[] as = new Annotation[dis.readUnsignedShort()];

         for(short i = 0; i < as.length; ++i) {
            as[i] = loadAnnotation(dis);
         }

         return new AnnotationsAttribute(attributeNameIndex, as);
      }

      private static Annotation loadAnnotation(DataInputStream dis) throws IOException {
         return new Annotation(dis.readShort(), loadElementValuePairs(dis));
      }

      private static Map loadElementValuePairs(DataInputStream dis) throws IOException {
         int numElementaluePairs = dis.readUnsignedShort();
         if (numElementaluePairs == 0) {
            return Collections.emptyMap();
         } else {
            Map<Short, ElementValue> result = new HashMap();

            for(int i = 0; i < numElementaluePairs; ++i) {
               result.put(dis.readShort(), ClassFile.loadElementValue(dis));
            }

            return result;
         }
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.annotations.size());

         for(Annotation a : this.annotations) {
            a.store(dos);
         }

      }
   }

   public static class SyntheticAttribute extends AttributeInfo {
      SyntheticAttribute(short attributeNameIndex) {
         super(attributeNameIndex);
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) {
         return new SyntheticAttribute(attributeNameIndex);
      }

      protected void storeBody(DataOutputStream dos) {
      }
   }

   public static class SignatureAttribute extends AttributeInfo {
      private final short signatureIndex;

      public SignatureAttribute(short attributeNameIndex, short signatureIndex) {
         super(attributeNameIndex);
         this.signatureIndex = signatureIndex;
      }

      public String getSignature(ClassFile classFile) {
         return classFile.getConstantUtf8(this.signatureIndex);
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) throws IOException {
         return new SignatureAttribute(attributeNameIndex, dis.readShort());
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.signatureIndex);
      }
   }

   public static class SourceFileAttribute extends AttributeInfo {
      private final short sourceFileIndex;

      public SourceFileAttribute(short attributeNameIndex, short sourceFileIndex) {
         super(attributeNameIndex);
         this.sourceFileIndex = sourceFileIndex;
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) throws IOException {
         return new SourceFileAttribute(attributeNameIndex, dis.readShort());
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.sourceFileIndex);
      }
   }

   public static class LineNumberTableAttribute extends AttributeInfo {
      private final Entry[] entries;

      public LineNumberTableAttribute(short attributeNameIndex, Entry[] entries) {
         super(attributeNameIndex);
         this.entries = entries;
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) throws IOException {
         Entry[] lntes = new Entry[dis.readUnsignedShort()];

         for(short i = 0; i < lntes.length; ++i) {
            lntes[i] = new Entry(dis.readShort(), dis.readShort());
         }

         return new LineNumberTableAttribute(attributeNameIndex, lntes);
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.entries.length);

         for(Entry entry : this.entries) {
            dos.writeShort(entry.startPc);
            dos.writeShort(entry.lineNumber);
         }

      }

      public static class Entry {
         public final short startPc;
         public final short lineNumber;

         public Entry(short startPc, short lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
         }
      }
   }

   public static class LocalVariableTableAttribute extends AttributeInfo {
      private final Entry[] entries;

      public LocalVariableTableAttribute(short attributeNameIndex, Entry[] entries) {
         super(attributeNameIndex);
         this.entries = entries;
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) throws IOException {
         Entry[] lvtes = new Entry[dis.readUnsignedShort()];

         for(short i = 0; i < lvtes.length; ++i) {
            lvtes[i] = new Entry(dis.readShort(), dis.readShort(), dis.readShort(), dis.readShort(), dis.readShort());
         }

         return new LocalVariableTableAttribute(attributeNameIndex, lvtes);
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.entries.length);

         for(Entry lnte : this.entries) {
            dos.writeShort(lnte.startPc);
            dos.writeShort(lnte.length);
            dos.writeShort(lnte.nameIndex);
            dos.writeShort(lnte.descriptorIndex);
            dos.writeShort(lnte.index);
         }

      }

      public static class Entry {
         public final short startPc;
         public final short length;
         public final short nameIndex;
         public final short descriptorIndex;
         public final short index;

         public Entry(short startPc, short length, short nameIndex, short descriptorIndex, short index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.descriptorIndex = descriptorIndex;
            this.index = index;
         }
      }
   }

   public static class DeprecatedAttribute extends AttributeInfo {
      public DeprecatedAttribute(short attributeNameIndex) {
         super(attributeNameIndex);
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) {
         return new DeprecatedAttribute(attributeNameIndex);
      }

      protected void storeBody(DataOutputStream dos) {
      }
   }

   public static class AnnotationDefaultAttribute extends AttributeInfo {
      private final ElementValue elementValue;

      public AnnotationDefaultAttribute(short attributeNameIndex, ElementValue elementValue) {
         super(attributeNameIndex);
         this.elementValue = elementValue;
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis) throws IOException {
         return new AnnotationDefaultAttribute(attributeNameIndex, ClassFile.loadElementValue(dis));
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeByte(this.elementValue.getTag());
         this.elementValue.store(dos);
      }
   }

   public static class CodeAttribute extends AttributeInfo {
      private final short maxStack;
      private final short maxLocals;
      public final byte[] code;
      private final ExceptionTableEntry[] exceptionTableEntries;
      private final AttributeInfo[] attributes;

      public CodeAttribute(short attributeNameIndex, short maxStack, short maxLocals, byte[] code, ExceptionTableEntry[] exceptionTableEntries, AttributeInfo[] attributes) {
         super(attributeNameIndex);
         this.maxStack = maxStack;
         this.maxLocals = maxLocals;
         this.code = code;
         this.exceptionTableEntries = exceptionTableEntries;
         this.attributes = attributes;
      }

      private static AttributeInfo loadBody(short attributeNameIndex, ClassFile classFile, DataInputStream dis) throws IOException {
         short maxStack = dis.readShort();
         short maxLocals = dis.readShort();
         byte[] code = ClassFile.readLengthAndBytes(dis);
         ExceptionTableEntry[] etes = new ExceptionTableEntry[dis.readUnsignedShort()];

         for(int i = 0; i < etes.length; ++i) {
            etes[i] = new ExceptionTableEntry(dis.readShort(), dis.readShort(), dis.readShort(), dis.readShort());
         }

         AttributeInfo[] attributes = new AttributeInfo[dis.readUnsignedShort()];

         for(int i = 0; i < attributes.length; ++i) {
            attributes[i] = classFile.loadAttribute(dis);
         }

         return new CodeAttribute(attributeNameIndex, maxStack, maxLocals, code, etes, attributes);
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.maxStack);
         dos.writeShort(this.maxLocals);
         dos.writeInt(this.code.length);
         dos.write(this.code);
         dos.writeShort(this.exceptionTableEntries.length);

         for(ExceptionTableEntry ete : this.exceptionTableEntries) {
            dos.writeShort(ete.startPc);
            dos.writeShort(ete.endPc);
            dos.writeShort(ete.handlerPc);
            dos.writeShort(ete.catchType);
         }

         dos.writeShort(this.attributes.length);

         for(AttributeInfo ai : this.attributes) {
            ai.store(dos);
         }

      }

      public static class ExceptionTableEntry {
         final short startPc;
         final short endPc;
         final short handlerPc;
         final short catchType;

         public ExceptionTableEntry(short startPc, short endPc, short handlerPc, short catchType) {
            this.startPc = startPc;
            this.endPc = endPc;
            this.handlerPc = handlerPc;
            this.catchType = catchType;
         }
      }
   }

   public static class StackMapTableAttribute extends AttributeInfo {
      private final StackMapFrame[] entries;
      public static final VerificationTypeInfo TOP_VARIABLE_INFO = new VerificationTypeInfo() {
         public int category() {
            return 1;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(0);
         }

         public String toString() {
            return "top";
         }

         public int hashCode() {
            return 0;
         }

         public boolean equals(@Nullable Object obj) {
            return obj == this;
         }
      };
      public static final VerificationTypeInfo INTEGER_VARIABLE_INFO = new VerificationTypeInfo() {
         public int category() {
            return 1;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(1);
         }

         public String toString() {
            return "int";
         }

         public int hashCode() {
            return 1;
         }

         public boolean equals(@Nullable Object obj) {
            return obj == this;
         }
      };
      public static final VerificationTypeInfo FLOAT_VARIABLE_INFO = new VerificationTypeInfo() {
         public int category() {
            return 1;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(2);
         }

         public String toString() {
            return "float";
         }

         public int hashCode() {
            return 2;
         }

         public boolean equals(@Nullable Object obj) {
            return obj == this;
         }
      };
      public static final VerificationTypeInfo DOUBLE_VARIABLE_INFO = new VerificationTypeInfo() {
         public int category() {
            return 2;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(3);
         }

         public String toString() {
            return "double";
         }

         public int hashCode() {
            return 3;
         }

         public boolean equals(@Nullable Object obj) {
            return obj == this;
         }
      };
      public static final VerificationTypeInfo LONG_VARIABLE_INFO = new VerificationTypeInfo() {
         public int category() {
            return 2;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(4);
         }

         public String toString() {
            return "long";
         }

         public int hashCode() {
            return 4;
         }

         public boolean equals(@Nullable Object obj) {
            return obj == this;
         }
      };
      public static final VerificationTypeInfo NULL_VARIABLE_INFO = new VerificationTypeInfo() {
         public int category() {
            return 1;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(5);
         }

         public String toString() {
            return "null";
         }

         public int hashCode() {
            return 5;
         }

         public boolean equals(@Nullable Object obj) {
            return obj == this;
         }
      };
      public static final VerificationTypeInfo UNINITIALIZED_THIS_VARIABLE_INFO = new VerificationTypeInfo() {
         public int category() {
            return 1;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(6);
         }

         public String toString() {
            return "uninitializedThis";
         }

         public int hashCode() {
            return 6;
         }

         public boolean equals(@Nullable Object obj) {
            return obj == this;
         }
      };

      public StackMapTableAttribute(short attributeNameIndex, StackMapFrame[] entries) {
         super(attributeNameIndex);
         this.entries = entries;
      }

      private static AttributeInfo loadBody(short attributeNameIndex, DataInputStream dis, ClassFile classFile) throws IOException {
         StackMapFrame[] entries = new StackMapFrame[dis.readUnsignedShort()];

         for(int i = 0; i < entries.length; ++i) {
            int frameType = dis.readUnsignedByte();
            StackMapFrame e = (StackMapFrame)(frameType <= 63 ? new SameFrame(frameType) : (frameType <= 127 ? new SameLocals1StackItemFrame(frameType - 64, loadVerificationTypeInfo(dis, classFile)) : (frameType <= 246 ? null : (frameType == 247 ? new SameLocals1StackItemFrameExtended(dis.readUnsignedShort(), loadVerificationTypeInfo(dis, classFile)) : (frameType <= 250 ? new ChopFrame(dis.readUnsignedShort(), 251 - frameType) : (frameType == 251 ? new SameFrameExtended(dis.readUnsignedShort()) : (frameType <= 254 ? new AppendFrame(dis.readUnsignedShort(), loadVerificationTypeInfos(dis, frameType - 251, classFile)) : (frameType == 255 ? new FullFrame(dis.readUnsignedShort(), loadVerificationTypeInfos(dis, dis.readUnsignedShort(), classFile), loadVerificationTypeInfos(dis, dis.readUnsignedShort(), classFile)) : null))))))));
            if (e == null) {
               throw new ClassFileException("Invalid stack_map_frame frame_type " + frameType);
            }

            entries[i] = e;
         }

         return new StackMapTableAttribute(attributeNameIndex, entries);
      }

      private static void storeVerificationTypeInfos(VerificationTypeInfo[] vtis, DataOutputStream dos) throws IOException {
         for(VerificationTypeInfo vti : vtis) {
            vti.store(dos);
         }

      }

      private static VerificationTypeInfo[] loadVerificationTypeInfos(DataInputStream dis, int number, ClassFile classFile) throws IOException {
         VerificationTypeInfo[] result = new VerificationTypeInfo[number];

         for(int i = 0; i < number; ++i) {
            result[i] = loadVerificationTypeInfo(dis, classFile);
         }

         return result;
      }

      private static VerificationTypeInfo loadVerificationTypeInfo(DataInputStream dis, ClassFile classFile) throws IOException {
         int tag = 255 & dis.readByte();
         switch (tag) {
            case 0:
               return TOP_VARIABLE_INFO;
            case 1:
               return INTEGER_VARIABLE_INFO;
            case 2:
               return FLOAT_VARIABLE_INFO;
            case 3:
               return DOUBLE_VARIABLE_INFO;
            case 4:
               return LONG_VARIABLE_INFO;
            case 5:
               return NULL_VARIABLE_INFO;
            case 6:
               return UNINITIALIZED_THIS_VARIABLE_INFO;
            case 7:
               short constantClassInfoIndex = dis.readShort();
               return new ObjectVariableInfo(constantClassInfoIndex, classFile.getConstantClassInfo(constantClassInfoIndex).getName(classFile));
            case 8:
               return new UninitializedVariableInfo(dis.readShort());
            default:
               throw new ClassFileException("Invalid verification_type_info tag " + tag);
         }
      }

      protected void storeBody(DataOutputStream dos) throws IOException {
         dos.writeShort(this.entries.length);

         for(StackMapFrame smf : this.entries) {
            smf.store(dos);
         }

      }

      public abstract static class StackMapFrame {
         final int offsetDelta;

         public StackMapFrame(int offsetDelta) {
            assert offsetDelta >= 0 && offsetDelta <= 65535;

            this.offsetDelta = offsetDelta;
         }

         public abstract Object accept(StackMapFrameVisitor var1);

         public abstract void store(DataOutputStream var1) throws IOException;
      }

      public static class SameFrame extends StackMapFrame {
         public SameFrame(int offsetDelta) {
            super(offsetDelta);
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(this.offsetDelta);
         }

         public Object accept(StackMapFrameVisitor smfv) {
            return smfv.visitSameFrame(this);
         }

         public String toString() {
            return "same_frame (offsetDelta=" + this.offsetDelta + ")";
         }
      }

      public static class SameLocals1StackItemFrame extends StackMapFrame {
         private final VerificationTypeInfo stack;

         public SameLocals1StackItemFrame(int offsetDelta, VerificationTypeInfo stack) {
            super(offsetDelta);
            this.stack = stack;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(this.offsetDelta + 64);
            this.stack.store(dos);
         }

         public Object accept(StackMapFrameVisitor smfv) {
            return smfv.visitSameLocals1StackItemFrame(this);
         }

         public String toString() {
            return "same_locals_1_stack_item_frame(offsetDelta=" + this.offsetDelta + ", stack=[" + this.stack + "])";
         }
      }

      public static class SameLocals1StackItemFrameExtended extends StackMapFrame {
         private final VerificationTypeInfo stack;

         public SameLocals1StackItemFrameExtended(int offsetDelta, VerificationTypeInfo stack) {
            super(offsetDelta);
            this.stack = stack;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(247);
            dos.writeShort(this.offsetDelta);
            this.stack.store(dos);
         }

         public Object accept(StackMapFrameVisitor smfv) {
            return smfv.visitSameLocals1StackItemFrameExtended(this);
         }

         public String toString() {
            return "same_locals_1_stack_item_frame_extended(offsetDelta=" + this.offsetDelta + ", stack=[" + this.stack + "])";
         }
      }

      public static class ChopFrame extends StackMapFrame {
         private final int k;

         public ChopFrame(int offsetDelta, int k) {
            super(offsetDelta);

            assert k >= 1 && k <= 3 : k;

            this.k = k;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(251 - this.k);
            dos.writeShort(this.offsetDelta);
         }

         public Object accept(StackMapFrameVisitor smfv) {
            return smfv.visitChopFrame(this);
         }

         public String toString() {
            return "chop_frame(offsetDelta=" + this.offsetDelta + ", locals-=" + this.k + ", stack=[])";
         }
      }

      public static class SameFrameExtended extends StackMapFrame {
         public SameFrameExtended(int offsetDelta) {
            super(offsetDelta);
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(251);
            dos.writeShort(this.offsetDelta);
         }

         public Object accept(StackMapFrameVisitor smfv) {
            return smfv.visitSameFrameExtended(this);
         }

         public String toString() {
            return "same_frame_extended(offsetDelta=" + this.offsetDelta + ", stack=[])";
         }
      }

      public static class AppendFrame extends StackMapFrame {
         private final VerificationTypeInfo[] locals;

         public AppendFrame(int offsetDelta, VerificationTypeInfo[] locals) {
            super(offsetDelta);

            assert locals.length >= 1 && locals.length <= 3;

            this.locals = locals;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(this.locals.length + 251);
            dos.writeShort(this.offsetDelta);
            ClassFile.StackMapTableAttribute.storeVerificationTypeInfos(this.locals, dos);
         }

         public Object accept(StackMapFrameVisitor smfv) {
            return smfv.visitAppendFrame(this);
         }

         public String toString() {
            return "append_frame(offsetDelta=" + this.offsetDelta + ", locals+=" + Arrays.toString(this.locals) + ", stack=[])";
         }
      }

      public static class FullFrame extends StackMapFrame {
         private final VerificationTypeInfo[] locals;
         private final VerificationTypeInfo[] stack;

         public FullFrame(int offsetDelta, VerificationTypeInfo[] locals, VerificationTypeInfo[] stack) {
            super(offsetDelta);
            this.locals = locals;
            this.stack = stack;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(255);
            dos.writeShort(this.offsetDelta);
            dos.writeShort(this.locals.length);
            ClassFile.StackMapTableAttribute.storeVerificationTypeInfos(this.locals, dos);
            dos.writeShort(this.stack.length);
            ClassFile.StackMapTableAttribute.storeVerificationTypeInfos(this.stack, dos);
         }

         public Object accept(StackMapFrameVisitor smfv) {
            return smfv.visitFullFrame(this);
         }

         public String toString() {
            return "full_frame(offsetDelta=" + this.offsetDelta + ", locals=" + Arrays.toString(this.locals) + ", stack=" + Arrays.toString(this.stack) + ")";
         }
      }

      public static class ObjectVariableInfo implements VerificationTypeInfo {
         private final short constantClassInfoIndex;
         private final String fieldDescriptor;

         public ObjectVariableInfo(short constantClassInfoIndex, String fieldDescriptor) {
            this.constantClassInfoIndex = constantClassInfoIndex;
            this.fieldDescriptor = fieldDescriptor;
         }

         public short getConstantClassInfoIndex() {
            return this.constantClassInfoIndex;
         }

         public int category() {
            return 1;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(7);
            dos.writeShort(this.constantClassInfoIndex);
         }

         public String toString() {
            return "object(" + Descriptor.toString(this.fieldDescriptor) + ")";
         }

         public int hashCode() {
            return 7 ^ this.constantClassInfoIndex;
         }

         public boolean equals(@Nullable Object obj) {
            return obj instanceof ObjectVariableInfo && ((ObjectVariableInfo)obj).constantClassInfoIndex == this.constantClassInfoIndex;
         }
      }

      public static class UninitializedVariableInfo implements VerificationTypeInfo {
         public short offset;

         public UninitializedVariableInfo(short offset) {
            this.offset = offset;
         }

         public int category() {
            return 1;
         }

         public void store(DataOutputStream dos) throws IOException {
            dos.writeByte(8);
            dos.writeShort(this.offset);
         }

         public String toString() {
            return "uninitialized(offset=" + this.offset + ")";
         }

         public int hashCode() {
            return 8 ^ this.offset;
         }

         public boolean equals(@Nullable Object obj) {
            return obj instanceof UninitializedVariableInfo && ((UninitializedVariableInfo)obj).offset == this.offset;
         }
      }

      public interface StackMapFrameVisitor {
         Object visitSameFrame(SameFrame var1);

         Object visitSameLocals1StackItemFrame(SameLocals1StackItemFrame var1);

         Object visitSameLocals1StackItemFrameExtended(SameLocals1StackItemFrameExtended var1);

         Object visitChopFrame(ChopFrame var1);

         Object visitSameFrameExtended(SameFrameExtended var1);

         Object visitAppendFrame(AppendFrame var1);

         Object visitFullFrame(FullFrame var1);
      }

      public interface VerificationTypeInfo {
         int category();

         void store(DataOutputStream var1) throws IOException;
      }
   }

   public abstract static class ConstantElementValue implements ElementValue {
      private final byte tag;
      public final short constantValueIndex;

      public ConstantElementValue(byte tag, short constantValueIndex) {
         this.tag = tag;
         this.constantValueIndex = constantValueIndex;
      }

      public byte getTag() {
         return this.tag;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeShort(this.constantValueIndex);
      }

      @Nullable
      public Object accept(ElementValue.Visitor visitor) throws Throwable {
         return this.accept((Visitor)visitor);
      }

      @Nullable
      protected abstract Object accept(Visitor var1) throws Throwable;

      public interface Visitor {
         Object visitBooleanElementValue(BooleanElementValue var1) throws Throwable;

         Object visitByteElementValue(ByteElementValue var1) throws Throwable;

         Object visitCharElementValue(CharElementValue var1) throws Throwable;

         Object visitClassElementValue(ClassElementValue var1) throws Throwable;

         Object visitDoubleElementValue(DoubleElementValue var1) throws Throwable;

         Object visitFloatElementValue(FloatElementValue var1) throws Throwable;

         Object visitIntElementValue(IntElementValue var1) throws Throwable;

         Object visitLongElementValue(LongElementValue var1) throws Throwable;

         Object visitShortElementValue(ShortElementValue var1) throws Throwable;

         Object visitStringElementValue(StringElementValue var1) throws Throwable;
      }
   }

   public static final class ByteElementValue extends ConstantElementValue {
      public ByteElementValue(short constantValueIndex) {
         super((byte)66, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitByteElementValue(this);
      }
   }

   public static final class CharElementValue extends ConstantElementValue {
      public CharElementValue(short constantValueIndex) {
         super((byte)67, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitCharElementValue(this);
      }
   }

   public static final class DoubleElementValue extends ConstantElementValue {
      public DoubleElementValue(short constantValueIndex) {
         super((byte)68, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitDoubleElementValue(this);
      }
   }

   public static final class FloatElementValue extends ConstantElementValue {
      public FloatElementValue(short constantValueIndex) {
         super((byte)70, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitFloatElementValue(this);
      }
   }

   public static final class IntElementValue extends ConstantElementValue {
      public IntElementValue(short constantValueIndex) {
         super((byte)73, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitIntElementValue(this);
      }
   }

   public static final class LongElementValue extends ConstantElementValue {
      public LongElementValue(short constantValueIndex) {
         super((byte)74, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitLongElementValue(this);
      }
   }

   public static final class ShortElementValue extends ConstantElementValue {
      public ShortElementValue(short constantValueIndex) {
         super((byte)83, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitShortElementValue(this);
      }
   }

   public static final class BooleanElementValue extends ConstantElementValue {
      public BooleanElementValue(short constantValueIndex) {
         super((byte)90, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitBooleanElementValue(this);
      }
   }

   public static final class StringElementValue extends ConstantElementValue {
      public StringElementValue(short constantValueIndex) {
         super((byte)115, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitStringElementValue(this);
      }
   }

   public static final class ClassElementValue extends ConstantElementValue {
      public ClassElementValue(short constantValueIndex) {
         super((byte)99, constantValueIndex);
      }

      protected Object accept(ConstantElementValue.Visitor visitor) throws Throwable {
         return visitor.visitClassElementValue(this);
      }
   }

   public static final class EnumConstValue implements ElementValue {
      public final short typeNameIndex;
      public final short constNameIndex;

      public EnumConstValue(short typeNameIndex, short constNameIndex) {
         this.typeNameIndex = typeNameIndex;
         this.constNameIndex = constNameIndex;
      }

      public byte getTag() {
         return 101;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeShort(this.typeNameIndex);
         dos.writeShort(this.constNameIndex);
      }

      @Nullable
      public Object accept(ElementValue.Visitor visitor) throws Throwable {
         return visitor.visitEnumConstValue(this);
      }
   }

   public static final class ArrayElementValue implements ElementValue {
      public final ElementValue[] values;

      public ArrayElementValue(ElementValue[] values) {
         this.values = values;
      }

      public byte getTag() {
         return 91;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeShort(this.values.length);

         for(ElementValue ev : this.values) {
            dos.writeByte(ev.getTag());
            ev.store(dos);
         }

      }

      @Nullable
      public Object accept(ElementValue.Visitor visitor) throws Throwable {
         return visitor.visitArrayElementValue(this);
      }
   }

   public static class Annotation implements ElementValue {
      public final short typeIndex;
      public final Map elementValuePairs;

      public Annotation(short typeIndex, Map elementValuePairs) {
         this.typeIndex = typeIndex;
         this.elementValuePairs = elementValuePairs;
      }

      public byte getTag() {
         return 64;
      }

      public void store(DataOutputStream dos) throws IOException {
         dos.writeShort(this.typeIndex);
         dos.writeShort(this.elementValuePairs.size());

         for(Map.Entry evps : this.elementValuePairs.entrySet()) {
            Short elementNameIndex = (Short)evps.getKey();
            ElementValue elementValue = (ElementValue)evps.getValue();
            dos.writeShort(elementNameIndex);
            dos.writeByte(elementValue.getTag());
            elementValue.store(dos);
         }

      }

      @Nullable
      public Object accept(ElementValue.Visitor visitor) throws Throwable {
         return visitor.visitAnnotation(this);
      }
   }

   public interface ElementValue {
      byte getTag();

      void store(DataOutputStream var1) throws IOException;

      @Nullable
      Object accept(Visitor var1) throws Throwable;

      public interface Visitor extends ConstantElementValue.Visitor {
         Object visitAnnotation(Annotation var1) throws Throwable;

         Object visitArrayElementValue(ArrayElementValue var1) throws Throwable;

         Object visitEnumConstValue(EnumConstValue var1) throws Throwable;
      }
   }
}
