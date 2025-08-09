package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javassist.CtClass;

public class SignatureAttribute extends AttributeInfo {
   public static final String tag = "Signature";

   SignatureAttribute(ConstPool cp, int n, DataInputStream in) throws IOException {
      super(cp, n, in);
   }

   public SignatureAttribute(ConstPool cp, String signature) {
      super(cp, "Signature");
      int index = cp.addUtf8Info(signature);
      byte[] bvalue = new byte[2];
      bvalue[0] = (byte)(index >>> 8);
      bvalue[1] = (byte)index;
      this.set(bvalue);
   }

   public String getSignature() {
      return this.getConstPool().getUtf8Info(ByteArray.readU16bit(this.get(), 0));
   }

   public void setSignature(String sig) {
      int index = this.getConstPool().addUtf8Info(sig);
      ByteArray.write16bit(index, this.info, 0);
   }

   public AttributeInfo copy(ConstPool newCp, Map classnames) {
      return new SignatureAttribute(newCp, this.getSignature());
   }

   void renameClass(String oldname, String newname) {
      String sig = renameClass(this.getSignature(), oldname, newname);
      this.setSignature(sig);
   }

   void renameClass(Map classnames) {
      String sig = renameClass(this.getSignature(), classnames);
      this.setSignature(sig);
   }

   static String renameClass(String desc, String oldname, String newname) {
      Map<String, String> map = new HashMap();
      map.put(oldname, newname);
      return renameClass(desc, map);
   }

   static String renameClass(String desc, Map map) {
      if (map != null && !map.isEmpty()) {
         StringBuilder newdesc = new StringBuilder();
         int head = 0;
         int i = 0;

         while(true) {
            int j = desc.indexOf(76, i);
            if (j < 0) {
               break;
            }

            ArrayList<StringBuilder> nameBufs = new ArrayList();
            ArrayList<StringBuilder> genericParamBufs = new ArrayList();
            i = parseClassName(nameBufs, genericParamBufs, desc, j) + 1;
            if (i < 0) {
               break;
            }

            String name = String.join("$", (CharSequence[])nameBufs.toArray(new StringBuilder[0]));
            String newname = (String)map.get(name);
            if (newname != null) {
               if (makeNewClassName(desc, map, name, newname, newdesc, head, j, nameBufs, genericParamBufs)) {
                  head = i;
               }
            } else if (replaceTypeArguments(desc, map, newdesc, head, j, nameBufs, genericParamBufs)) {
               head = i;
            }
         }

         if (head == 0) {
            return desc;
         } else {
            int len = desc.length();
            if (head < len) {
               newdesc.append(desc.substring(head, len));
            }

            return newdesc.toString();
         }
      } else {
         return desc;
      }
   }

   private static int parseClassName(ArrayList nameBufs, ArrayList genericParamBufs, String desc, int j) {
      StringBuilder nameBuf = new StringBuilder();
      StringBuilder genericParamBuf = new StringBuilder();
      int k = j;

      try {
         while(true) {
            ++k;
            char c;
            if ((c = desc.charAt(k)) == ';') {
               break;
            }

            if (c == '<') {
               genericParamBuf.append(c);
               int level = 1;

               while(level > 0) {
                  ++k;
                  c = desc.charAt(k);
                  genericParamBuf.append(c);
                  if (c == '<') {
                     ++level;
                  } else if (c == '>') {
                     --level;
                  }
               }
            } else if (c == '.') {
               nameBufs.add(nameBuf);
               genericParamBufs.add(genericParamBuf);
               nameBuf = new StringBuilder();
               genericParamBuf = new StringBuilder();
            } else {
               nameBuf.append(c);
            }
         }
      } catch (IndexOutOfBoundsException var9) {
         return -2;
      }

      nameBufs.add(nameBuf);
      genericParamBufs.add(genericParamBuf);
      return k;
   }

   private static boolean makeNewClassName(String desc, Map map, String name, String newname, StringBuilder newdesc, int head, int j, ArrayList nameBufs, ArrayList genericParamBufs) {
      String[] nameSplit = name.split("\\$");
      String[] newnameSplit = newname.split("\\$");
      if (nameSplit.length != newnameSplit.length) {
         return false;
      } else {
         String[] newnames = new String[nameBufs.size()];
         int start = 0;

         for(int z = 0; z < nameBufs.size(); ++z) {
            int toAggregate = (int)((StringBuilder)nameBufs.get(z)).chars().filter((ch) -> ch == 36).count() + 1;
            String s = String.join("$", (CharSequence[])Arrays.copyOfRange(newnameSplit, start, start + toAggregate));
            start += toAggregate;
            newnames[z] = s;
         }

         newdesc.append(desc.substring(head, j));
         newdesc.append('L');

         for(int z = 0; z < newnames.length; ++z) {
            if (z > 0) {
               newdesc.append('.');
            }

            newdesc.append(newnames[z]);
            StringBuilder genericParamBufCurrent = (StringBuilder)genericParamBufs.get(z);
            String newgenericParam;
            if (genericParamBufCurrent.length() > 0) {
               newgenericParam = "<" + renameClass(genericParamBufCurrent.substring(1, genericParamBufCurrent.length() - 1), map) + ">";
            } else {
               newgenericParam = genericParamBufCurrent.toString();
            }

            newdesc.append(newgenericParam);
         }

         newdesc.append(';');
         return true;
      }
   }

   private static boolean replaceTypeArguments(String desc, Map map, StringBuilder newdesc, int head, int j, ArrayList nameBufs, ArrayList genericParamBufs) {
      ArrayList<String> newGenericParamBufs = new ArrayList();
      boolean changed = false;

      for(int z = 0; z < genericParamBufs.size(); ++z) {
         StringBuilder genericParamBufCurrent = (StringBuilder)genericParamBufs.get(z);
         String newGenericParam;
         if (genericParamBufCurrent.length() > 0) {
            newGenericParam = "<" + renameClass(genericParamBufCurrent.substring(1, genericParamBufCurrent.length() - 1), map) + ">";
            changed = changed || !genericParamBufCurrent.toString().equals(newGenericParam);
         } else {
            newGenericParam = genericParamBufCurrent.toString();
         }

         newGenericParamBufs.add(newGenericParam);
      }

      if (changed) {
         newdesc.append(desc.substring(head, j));
         newdesc.append('L');

         for(int z = 0; z < genericParamBufs.size(); ++z) {
            if (z > 0) {
               newdesc.append('.');
            }

            newdesc.append((CharSequence)nameBufs.get(z));
            newdesc.append((String)newGenericParamBufs.get(z));
         }

         newdesc.append(';');
         return true;
      } else {
         return false;
      }
   }

   private static boolean isNamePart(int c) {
      return c != 59 && c != 60;
   }

   public static ClassSignature toClassSignature(String sig) throws BadBytecode {
      try {
         return parseSig(sig);
      } catch (IndexOutOfBoundsException var2) {
         throw error(sig);
      }
   }

   public static MethodSignature toMethodSignature(String sig) throws BadBytecode {
      try {
         return parseMethodSig(sig);
      } catch (IndexOutOfBoundsException var2) {
         throw error(sig);
      }
   }

   public static ObjectType toFieldSignature(String sig) throws BadBytecode {
      try {
         return parseObjectType(sig, new Cursor(), false);
      } catch (IndexOutOfBoundsException var2) {
         throw error(sig);
      }
   }

   public static Type toTypeSignature(String sig) throws BadBytecode {
      try {
         return parseType(sig, new Cursor());
      } catch (IndexOutOfBoundsException var2) {
         throw error(sig);
      }
   }

   private static ClassSignature parseSig(String sig) throws BadBytecode, IndexOutOfBoundsException {
      Cursor cur = new Cursor();
      TypeParameter[] tp = parseTypeParams(sig, cur);
      ClassType superClass = parseClassType(sig, cur);
      int sigLen = sig.length();
      List<ClassType> ifArray = new ArrayList();

      while(cur.position < sigLen && sig.charAt(cur.position) == 'L') {
         ifArray.add(parseClassType(sig, cur));
      }

      ClassType[] ifs = (ClassType[])ifArray.toArray(new ClassType[ifArray.size()]);
      return new ClassSignature(tp, superClass, ifs);
   }

   private static MethodSignature parseMethodSig(String sig) throws BadBytecode {
      Cursor cur = new Cursor();
      TypeParameter[] tp = parseTypeParams(sig, cur);
      if (sig.charAt(cur.position++) != '(') {
         throw error(sig);
      } else {
         List<Type> params = new ArrayList();

         while(sig.charAt(cur.position) != ')') {
            Type t = parseType(sig, cur);
            params.add(t);
         }

         ++cur.position;
         Type ret = parseType(sig, cur);
         int sigLen = sig.length();
         List<ObjectType> exceptions = new ArrayList();

         while(cur.position < sigLen && sig.charAt(cur.position) == '^') {
            ++cur.position;
            ObjectType t = parseObjectType(sig, cur, false);
            if (t instanceof ArrayType) {
               throw error(sig);
            }

            exceptions.add(t);
         }

         Type[] p = (Type[])params.toArray(new Type[params.size()]);
         ObjectType[] ex = (ObjectType[])exceptions.toArray(new ObjectType[exceptions.size()]);
         return new MethodSignature(tp, p, ret, ex);
      }
   }

   private static TypeParameter[] parseTypeParams(String sig, Cursor cur) throws BadBytecode {
      List<TypeParameter> typeParam = new ArrayList();
      if (sig.charAt(cur.position) == '<') {
         ++cur.position;

         while(sig.charAt(cur.position) != '>') {
            int nameBegin = cur.position;
            int nameEnd = cur.indexOf(sig, 58);
            ObjectType classBound = parseObjectType(sig, cur, true);
            List<ObjectType> ifBound = new ArrayList();

            while(sig.charAt(cur.position) == ':') {
               ++cur.position;
               ObjectType t = parseObjectType(sig, cur, false);
               ifBound.add(t);
            }

            TypeParameter p = new TypeParameter(sig, nameBegin, nameEnd, classBound, (ObjectType[])ifBound.toArray(new ObjectType[ifBound.size()]));
            typeParam.add(p);
         }

         ++cur.position;
      }

      return (TypeParameter[])typeParam.toArray(new TypeParameter[typeParam.size()]);
   }

   private static ObjectType parseObjectType(String sig, Cursor c, boolean dontThrow) throws BadBytecode {
      int begin = c.position;
      switch (sig.charAt(begin)) {
         case 'L':
            return parseClassType2(sig, c, (ClassType)null);
         case 'T':
            int i = c.indexOf(sig, 59);
            return new TypeVariable(sig, begin + 1, i);
         case '[':
            return parseArray(sig, c);
         default:
            if (dontThrow) {
               return null;
            } else {
               throw error(sig);
            }
      }
   }

   private static ClassType parseClassType(String sig, Cursor c) throws BadBytecode {
      if (sig.charAt(c.position) == 'L') {
         return parseClassType2(sig, c, (ClassType)null);
      } else {
         throw error(sig);
      }
   }

   private static ClassType parseClassType2(String sig, Cursor c, ClassType parent) throws BadBytecode {
      int start = ++c.position;

      char t;
      do {
         t = sig.charAt(c.position++);
      } while(t != '$' && t != '<' && t != ';');

      int end = c.position - 1;
      TypeArgument[] targs;
      if (t == '<') {
         targs = parseTypeArgs(sig, c);
         t = sig.charAt(c.position++);
      } else {
         targs = null;
      }

      ClassType thisClass = SignatureAttribute.ClassType.make(sig, start, end, targs, parent);
      if (t != '$' && t != '.') {
         return thisClass;
      } else {
         --c.position;
         return parseClassType2(sig, c, thisClass);
      }
   }

   private static TypeArgument[] parseTypeArgs(String sig, Cursor c) throws BadBytecode {
      List<TypeArgument> args;
      char t;
      TypeArgument ta;
      for(args = new ArrayList(); (t = sig.charAt(c.position++)) != '>'; args.add(ta)) {
         if (t == '*') {
            ta = new TypeArgument((ObjectType)null, '*');
         } else {
            if (t != '+' && t != '-') {
               t = ' ';
               --c.position;
            }

            ta = new TypeArgument(parseObjectType(sig, c, false), t);
         }
      }

      return (TypeArgument[])args.toArray(new TypeArgument[args.size()]);
   }

   private static ObjectType parseArray(String sig, Cursor c) throws BadBytecode {
      int dim;
      for(dim = 1; sig.charAt(++c.position) == '['; ++dim) {
      }

      return new ArrayType(dim, parseType(sig, c));
   }

   private static Type parseType(String sig, Cursor c) throws BadBytecode {
      Type t = parseObjectType(sig, c, true);
      if (t == null) {
         t = new BaseType(sig.charAt(c.position++));
      }

      return t;
   }

   private static BadBytecode error(String sig) {
      return new BadBytecode("bad signature: " + sig);
   }

   private static class Cursor {
      int position;

      private Cursor() {
         this.position = 0;
      }

      int indexOf(String s, int ch) throws BadBytecode {
         int i = s.indexOf(ch, this.position);
         if (i < 0) {
            throw SignatureAttribute.error(s);
         } else {
            this.position = i + 1;
            return i;
         }
      }
   }

   public static class ClassSignature {
      TypeParameter[] params;
      ClassType superClass;
      ClassType[] interfaces;

      public ClassSignature(TypeParameter[] params, ClassType superClass, ClassType[] interfaces) {
         this.params = params == null ? new TypeParameter[0] : params;
         this.superClass = superClass == null ? SignatureAttribute.ClassType.OBJECT : superClass;
         this.interfaces = interfaces == null ? new ClassType[0] : interfaces;
      }

      public ClassSignature(TypeParameter[] p) {
         this(p, (ClassType)null, (ClassType[])null);
      }

      public TypeParameter[] getParameters() {
         return this.params;
      }

      public ClassType getSuperClass() {
         return this.superClass;
      }

      public ClassType[] getInterfaces() {
         return this.interfaces;
      }

      public String toString() {
         StringBuilder sbuf = new StringBuilder();
         SignatureAttribute.TypeParameter.toString(sbuf, this.params);
         sbuf.append(" extends ").append(this.superClass);
         if (this.interfaces.length > 0) {
            sbuf.append(" implements ");
            SignatureAttribute.Type.toString(sbuf, this.interfaces);
         }

         return sbuf.toString();
      }

      public String encode() {
         StringBuilder sbuf = new StringBuilder();
         if (this.params.length > 0) {
            sbuf.append('<');

            for(int i = 0; i < this.params.length; ++i) {
               this.params[i].encode(sbuf);
            }

            sbuf.append('>');
         }

         this.superClass.encode(sbuf);

         for(int i = 0; i < this.interfaces.length; ++i) {
            this.interfaces[i].encode(sbuf);
         }

         return sbuf.toString();
      }
   }

   public static class MethodSignature {
      TypeParameter[] typeParams;
      Type[] params;
      Type retType;
      ObjectType[] exceptions;

      public MethodSignature(TypeParameter[] tp, Type[] params, Type ret, ObjectType[] ex) {
         this.typeParams = tp == null ? new TypeParameter[0] : tp;
         this.params = params == null ? new Type[0] : params;
         this.retType = (Type)(ret == null ? new BaseType("void") : ret);
         this.exceptions = ex == null ? new ObjectType[0] : ex;
      }

      public TypeParameter[] getTypeParameters() {
         return this.typeParams;
      }

      public Type[] getParameterTypes() {
         return this.params;
      }

      public Type getReturnType() {
         return this.retType;
      }

      public ObjectType[] getExceptionTypes() {
         return this.exceptions;
      }

      public String toString() {
         StringBuilder sbuf = new StringBuilder();
         SignatureAttribute.TypeParameter.toString(sbuf, this.typeParams);
         sbuf.append(" (");
         SignatureAttribute.Type.toString(sbuf, this.params);
         sbuf.append(") ");
         sbuf.append(this.retType);
         if (this.exceptions.length > 0) {
            sbuf.append(" throws ");
            SignatureAttribute.Type.toString(sbuf, this.exceptions);
         }

         return sbuf.toString();
      }

      public String encode() {
         StringBuilder sbuf = new StringBuilder();
         if (this.typeParams.length > 0) {
            sbuf.append('<');

            for(int i = 0; i < this.typeParams.length; ++i) {
               this.typeParams[i].encode(sbuf);
            }

            sbuf.append('>');
         }

         sbuf.append('(');

         for(int i = 0; i < this.params.length; ++i) {
            this.params[i].encode(sbuf);
         }

         sbuf.append(')');
         this.retType.encode(sbuf);
         if (this.exceptions.length > 0) {
            for(int i = 0; i < this.exceptions.length; ++i) {
               sbuf.append('^');
               this.exceptions[i].encode(sbuf);
            }
         }

         return sbuf.toString();
      }
   }

   public static class TypeParameter {
      String name;
      ObjectType superClass;
      ObjectType[] superInterfaces;

      TypeParameter(String sig, int nb, int ne, ObjectType sc, ObjectType[] si) {
         this.name = sig.substring(nb, ne);
         this.superClass = sc;
         this.superInterfaces = si;
      }

      public TypeParameter(String name, ObjectType superClass, ObjectType[] superInterfaces) {
         this.name = name;
         this.superClass = superClass;
         if (superInterfaces == null) {
            this.superInterfaces = new ObjectType[0];
         } else {
            this.superInterfaces = superInterfaces;
         }

      }

      public TypeParameter(String name) {
         this(name, (ObjectType)null, (ObjectType[])null);
      }

      public String getName() {
         return this.name;
      }

      public ObjectType getClassBound() {
         return this.superClass;
      }

      public ObjectType[] getInterfaceBound() {
         return this.superInterfaces;
      }

      public String toString() {
         StringBuilder sbuf = new StringBuilder(this.getName());
         if (this.superClass != null) {
            sbuf.append(" extends ").append(this.superClass.toString());
         }

         int len = this.superInterfaces.length;
         if (len > 0) {
            for(int i = 0; i < len; ++i) {
               if (i <= 0 && this.superClass == null) {
                  sbuf.append(" extends ");
               } else {
                  sbuf.append(" & ");
               }

               sbuf.append(this.superInterfaces[i].toString());
            }
         }

         return sbuf.toString();
      }

      static void toString(StringBuilder sbuf, TypeParameter[] tp) {
         sbuf.append('<');

         for(int i = 0; i < tp.length; ++i) {
            if (i > 0) {
               sbuf.append(", ");
            }

            sbuf.append(tp[i]);
         }

         sbuf.append('>');
      }

      void encode(StringBuilder sb) {
         sb.append(this.name);
         if (this.superClass == null) {
            sb.append(":Ljava/lang/Object;");
         } else {
            sb.append(':');
            this.superClass.encode(sb);
         }

         for(int i = 0; i < this.superInterfaces.length; ++i) {
            sb.append(':');
            this.superInterfaces[i].encode(sb);
         }

      }
   }

   public static class TypeArgument {
      ObjectType arg;
      char wildcard;

      TypeArgument(ObjectType a, char w) {
         this.arg = a;
         this.wildcard = w;
      }

      public TypeArgument(ObjectType t) {
         this(t, ' ');
      }

      public TypeArgument() {
         this((ObjectType)null, '*');
      }

      public static TypeArgument subclassOf(ObjectType t) {
         return new TypeArgument(t, '+');
      }

      public static TypeArgument superOf(ObjectType t) {
         return new TypeArgument(t, '-');
      }

      public char getKind() {
         return this.wildcard;
      }

      public boolean isWildcard() {
         return this.wildcard != ' ';
      }

      public ObjectType getType() {
         return this.arg;
      }

      public String toString() {
         if (this.wildcard == '*') {
            return "?";
         } else {
            String type = this.arg.toString();
            if (this.wildcard == ' ') {
               return type;
            } else {
               return this.wildcard == '+' ? "? extends " + type : "? super " + type;
            }
         }
      }

      static void encode(StringBuilder sb, TypeArgument[] args) {
         sb.append('<');

         for(int i = 0; i < args.length; ++i) {
            TypeArgument ta = args[i];
            if (ta.isWildcard()) {
               sb.append(ta.wildcard);
            }

            if (ta.getType() != null) {
               ta.getType().encode(sb);
            }
         }

         sb.append('>');
      }
   }

   public abstract static class Type {
      abstract void encode(StringBuilder var1);

      static void toString(StringBuilder sbuf, Type[] ts) {
         for(int i = 0; i < ts.length; ++i) {
            if (i > 0) {
               sbuf.append(", ");
            }

            sbuf.append(ts[i]);
         }

      }

      public String jvmTypeName() {
         return this.toString();
      }
   }

   public static class BaseType extends Type {
      char descriptor;

      BaseType(char c) {
         this.descriptor = c;
      }

      public BaseType(String typeName) {
         this(Descriptor.of(typeName).charAt(0));
      }

      public char getDescriptor() {
         return this.descriptor;
      }

      public CtClass getCtlass() {
         return Descriptor.toPrimitiveClass(this.descriptor);
      }

      public String toString() {
         return Descriptor.toClassName(Character.toString(this.descriptor));
      }

      void encode(StringBuilder sb) {
         sb.append(this.descriptor);
      }
   }

   public abstract static class ObjectType extends Type {
      public String encode() {
         StringBuilder sb = new StringBuilder();
         this.encode(sb);
         return sb.toString();
      }
   }

   public static class ClassType extends ObjectType {
      String name;
      TypeArgument[] arguments;
      public static ClassType OBJECT = new ClassType("java.lang.Object", (TypeArgument[])null);

      static ClassType make(String s, int b, int e, TypeArgument[] targs, ClassType parent) {
         return (ClassType)(parent == null ? new ClassType(s, b, e, targs) : new NestedClassType(s, b, e, targs, parent));
      }

      ClassType(String signature, int begin, int end, TypeArgument[] targs) {
         this.name = signature.substring(begin, end).replace('/', '.');
         this.arguments = targs;
      }

      public ClassType(String className, TypeArgument[] args) {
         this.name = className;
         this.arguments = args;
      }

      public ClassType(String className) {
         this(className, (TypeArgument[])null);
      }

      public String getName() {
         return this.name;
      }

      public TypeArgument[] getTypeArguments() {
         return this.arguments;
      }

      public ClassType getDeclaringClass() {
         return null;
      }

      public String toString() {
         StringBuilder sbuf = new StringBuilder();
         ClassType parent = this.getDeclaringClass();
         if (parent != null) {
            sbuf.append(parent.toString()).append('.');
         }

         return this.toString2(sbuf);
      }

      private String toString2(StringBuilder sbuf) {
         sbuf.append(this.name);
         if (this.arguments != null) {
            sbuf.append('<');
            int n = this.arguments.length;

            for(int i = 0; i < n; ++i) {
               if (i > 0) {
                  sbuf.append(", ");
               }

               sbuf.append(this.arguments[i].toString());
            }

            sbuf.append('>');
         }

         return sbuf.toString();
      }

      public String jvmTypeName() {
         StringBuilder sbuf = new StringBuilder();
         ClassType parent = this.getDeclaringClass();
         if (parent != null) {
            sbuf.append(parent.jvmTypeName()).append('$');
         }

         return this.toString2(sbuf);
      }

      void encode(StringBuilder sb) {
         sb.append('L');
         this.encode2(sb);
         sb.append(';');
      }

      void encode2(StringBuilder sb) {
         ClassType parent = this.getDeclaringClass();
         if (parent != null) {
            parent.encode2(sb);
            sb.append('$');
         }

         sb.append(this.name.replace('.', '/'));
         if (this.arguments != null) {
            SignatureAttribute.TypeArgument.encode(sb, this.arguments);
         }

      }
   }

   public static class NestedClassType extends ClassType {
      ClassType parent;

      NestedClassType(String s, int b, int e, TypeArgument[] targs, ClassType p) {
         super(s, b, e, targs);
         this.parent = p;
      }

      public NestedClassType(ClassType parent, String className, TypeArgument[] args) {
         super(className, args);
         this.parent = parent;
      }

      public ClassType getDeclaringClass() {
         return this.parent;
      }
   }

   public static class ArrayType extends ObjectType {
      int dim;
      Type componentType;

      public ArrayType(int d, Type comp) {
         this.dim = d;
         this.componentType = comp;
      }

      public int getDimension() {
         return this.dim;
      }

      public Type getComponentType() {
         return this.componentType;
      }

      public String toString() {
         StringBuilder sbuf = new StringBuilder(this.componentType.toString());

         for(int i = 0; i < this.dim; ++i) {
            sbuf.append("[]");
         }

         return sbuf.toString();
      }

      void encode(StringBuilder sb) {
         for(int i = 0; i < this.dim; ++i) {
            sb.append('[');
         }

         this.componentType.encode(sb);
      }
   }

   public static class TypeVariable extends ObjectType {
      String name;

      TypeVariable(String sig, int begin, int end) {
         this.name = sig.substring(begin, end);
      }

      public TypeVariable(String name) {
         this.name = name;
      }

      public String getName() {
         return this.name;
      }

      public String toString() {
         return this.name;
      }

      void encode(StringBuilder sb) {
         sb.append('T').append(this.name).append(';');
      }
   }
}
