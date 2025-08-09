package javolution.text;

import java.io.IOException;
import javolution.context.LocalContext;
import javolution.lang.Realtime;
import javolution.lang.Reflection;

public abstract class TextFormat {
   protected TextFormat(Class forClass) {
      if (forClass != null) {
         Reflection.getInstance().setField(new LocalReference(this), forClass, LocalReference.class);
      }
   }

   public static TextFormat getDefault(Class forClass) {
      TextFormat.Predefined.init();
      LocalReference localReference = (LocalReference)Reflection.getInstance().getField(forClass, LocalReference.class, true);
      return localReference == null ? TextFormat.Predefined.OBJECT_FORMAT : (TextFormat)localReference.getDefault();
   }

   public static TextFormat getInstance(Class forClass) {
      TextFormat.Predefined.init();
      LocalReference localReference = (LocalReference)Reflection.getInstance().getField(forClass, LocalReference.class, true);
      return localReference == null ? TextFormat.Predefined.OBJECT_FORMAT : (TextFormat)localReference.get();
   }

   public static void setInstance(Class forClass, TextFormat format) {
      TextFormat.Predefined.init();
      LocalReference localReference = (LocalReference)Reflection.getInstance().getField(forClass, LocalReference.class, false);
      if (localReference == null) {
         throw new IllegalArgumentException("Cannot override default format for class " + forClass + " (no default format defined)");
      } else {
         localReference.set(format);
      }
   }

   public boolean isParsingSupported() {
      return true;
   }

   public abstract Appendable format(Object var1, Appendable var2) throws IOException;

   public abstract Object parse(CharSequence var1, Cursor var2) throws IllegalArgumentException;

   public final TextBuilder format(Object obj, TextBuilder dest) {
      try {
         this.format(obj, (Appendable)dest);
         return dest;
      } catch (IOException var4) {
         throw new Error();
      }
   }

   public final Text format(Object obj) {
      TextBuilder tb = TextBuilder.newInstance();

      Text var3;
      try {
         this.format(obj, (Appendable)tb);
         var3 = tb.toText();
      } catch (IOException var7) {
         throw new Error();
      } finally {
         TextBuilder.recycle(tb);
      }

      return var3;
   }

   public final String formatToString(Object obj) {
      TextBuilder tb = TextBuilder.newInstance();

      String var3;
      try {
         this.format(obj, (Appendable)tb);
         var3 = tb.toString();
      } catch (IOException var7) {
         throw new Error();
      } finally {
         TextBuilder.recycle(tb);
      }

      return var3;
   }

   public final Object parse(CharSequence csq) throws IllegalArgumentException {
      Cursor cursor = Cursor.newInstance();

      Object var4;
      try {
         T obj = (T)this.parse(csq, cursor);
         if (cursor.getIndex() < csq.length()) {
            throw new IllegalArgumentException("Extraneous characters in \"" + csq + "\"");
         }

         var4 = obj;
      } finally {
         Cursor.recycle(cursor);
      }

      return var4;
   }

   private static CharSequence j2meToCharSeq(Object str) {
      return (CharSequence)str;
   }

   private static Text dummy(Object str) {
      return str == null ? null : Text.valueOf(str);
   }

   private static class LocalReference extends LocalContext.Reference {
      public LocalReference(TextFormat defaultFormat) {
         super(defaultFormat);
      }
   }

   private static class Predefined {
      static final TextFormat OBJECT_FORMAT = new TextFormat(Object.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            dest.append(TextFormat.j2meToCharSeq(obj.getClass().getName()));
            dest.append('#');
            return TypeFormat.format(System.identityHashCode(obj), dest);
         }

         public boolean isParsingSupported() {
            return false;
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            throw new UnsupportedOperationException("Parsing not supported");
         }
      };
      static final TextFormat STRING_FORMAT = new TextFormat(String.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return dest.append(TextFormat.j2meToCharSeq(obj));
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            String str = csq.subSequence(cursor.getIndex(), csq.length()).toString();
            cursor.setIndex(csq.length());
            return str;
         }
      };
      static final TextFormat BOOLEAN_FORMAT = new TextFormat(Boolean.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return TypeFormat.format((Boolean)obj, dest);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            return TypeFormat.parseBoolean(csq, cursor) ? Boolean.TRUE : Boolean.FALSE;
         }
      };
      static final TextFormat CHARACTER_FORMAT = new TextFormat(Character.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return dest.append((Character)obj);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            return new Character(cursor.nextChar(csq));
         }
      };
      static final TextFormat BYTE_FORMAT = new TextFormat(Byte.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return TypeFormat.format((Byte)obj, dest);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            return new Byte(TypeFormat.parseByte(csq, 10, cursor));
         }
      };
      static final TextFormat SHORT_FORMAT = new TextFormat(Short.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return TypeFormat.format((Short)obj, dest);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            return new Short(TypeFormat.parseShort(csq, 10, cursor));
         }
      };
      static final TextFormat INTEGER_FORMAT = new TextFormat(Integer.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return TypeFormat.format((Integer)obj, dest);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            return new Integer(TypeFormat.parseInt(csq, 10, cursor));
         }
      };
      static final TextFormat LONG_FORMAT = new TextFormat(Long.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return TypeFormat.format((Long)obj, dest);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            return new Long(TypeFormat.parseLong(csq, 10, cursor));
         }
      };
      static final TextFormat FLOAT_FORMAT = new TextFormat(Float.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return TypeFormat.format((Float)obj, dest);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            return new Float(TypeFormat.parseFloat(csq, cursor));
         }
      };
      static final TextFormat DOUBLE_FORMAT = new TextFormat(Double.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return TypeFormat.format((Double)obj, dest);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            return new Double(TypeFormat.parseDouble(csq, cursor));
         }
      };
      static final TextFormat CLASS_FORMAT = new TextFormat(Class.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return dest.append(TextFormat.j2meToCharSeq(((Class)obj).getName()));
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            CharSequence className = cursor.nextToken(csq, CharSet.WHITESPACES);
            if (className == null) {
               throw new IllegalArgumentException("No class name found");
            } else {
               Class cls = Reflection.getInstance().getClass(className);
               if (cls != null) {
                  return cls;
               } else {
                  throw new IllegalArgumentException("Class \"" + className + "\" not found (see javolution.lang.Reflection)");
               }
            }
         }
      };
      static final TextFormat TEXT_FORMAT = new TextFormat(Text.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return dest.append((Text)obj);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            CharSequence subCsq = csq.subSequence(cursor.getIndex(), csq.length());
            return subCsq instanceof Realtime ? ((Realtime)subCsq).toText() : Text.valueOf((Object)subCsq.toString());
         }
      };

      private static void init() {
      }
   }
}
