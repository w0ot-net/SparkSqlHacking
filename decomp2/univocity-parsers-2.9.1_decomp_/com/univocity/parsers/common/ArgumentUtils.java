package com.univocity.parsers.common;

import com.univocity.parsers.common.fields.FieldSelector;
import com.univocity.parsers.common.input.BomInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArgumentUtils {
   public static final String[] EMPTY_STRING_ARRAY = new String[0];
   public static final NormalizedString[] EMPTY_NORMALIZED_STRING_ARRAY = new NormalizedString[0];

   public static void notEmpty(String argDescription, Object... args) {
      if (args == null) {
         throw new IllegalArgumentException(argDescription + " must not be null");
      } else if (args.length == 0) {
         throw new IllegalArgumentException(argDescription + " must not be empty");
      }
   }

   public static void noNulls(String argDescription, Object... args) {
      notEmpty(argDescription, args);

      for(Object arg : args) {
         if (arg == null) {
            if (args.length > 0) {
               throw new IllegalArgumentException(argDescription + " must not contain nulls");
            }

            throw new IllegalArgumentException(argDescription + " must not be null");
         }
      }

   }

   public static int indexOf(NormalizedString[] array, NormalizedString element, FieldSelector fieldSelector) {
      int index = indexOf(array, element);
      if (fieldSelector != null && index != -1) {
         int[] indexes = fieldSelector.getFieldIndexes(array);

         for(int i = 0; i < indexes.length; ++i) {
            if (indexes[i] == index) {
               return i;
            }
         }

         return -1;
      } else {
         return index;
      }
   }

   public static int[] indexesOf(Object[] array, Object element) {
      int[] tmp = new int[0];
      int i = 0;

      for(int o = 0; i < array.length; tmp[o++] = i++) {
         i = indexOf(array, element, i);
         if (i == -1) {
            break;
         }

         tmp = Arrays.copyOf(tmp, tmp.length + 1);
      }

      return tmp;
   }

   public static int indexOf(Object[] array, Object element) {
      return indexOf(array, element, 0);
   }

   public static int indexOf(char[] array, char element, int from) {
      for(int i = from; i < array.length; ++i) {
         if (array[i] == element) {
            return i;
         }
      }

      return -1;
   }

   private static int indexOf(Object[] array, Object element, int from) {
      if (array == null) {
         throw new NullPointerException("Null array");
      } else {
         if (element == null) {
            for(int i = from; i < array.length; ++i) {
               if (array[i] == null) {
                  return i;
               }
            }
         } else {
            if (element.getClass() != array.getClass().getComponentType()) {
               throw new IllegalStateException("a");
            }

            if (element instanceof String && array instanceof String[]) {
               for(int i = from; i < array.length; ++i) {
                  String e = String.valueOf(array[i]);
                  if (element.toString().equalsIgnoreCase(e)) {
                     return i;
                  }
               }
            } else {
               for(int i = from; i < array.length; ++i) {
                  if (element.equals(array[i])) {
                     return i;
                  }
               }
            }
         }

         return -1;
      }
   }

   public static Object[] findMissingElements(Object[] array, Collection elements) {
      return findMissingElements(array, elements.toArray());
   }

   public static Object[] findMissingElements(Object[] array, Object[] elements) {
      List<Object> out = new ArrayList();

      for(Object element : elements) {
         if (indexOf(array, element) == -1) {
            out.add(element);
         }
      }

      return out.toArray();
   }

   public static Writer newWriter(OutputStream output) {
      return newWriter(output, (Charset)null);
   }

   public static Writer newWriter(OutputStream output, String encoding) {
      return newWriter(output, Charset.forName(encoding));
   }

   public static Writer newWriter(OutputStream output, Charset encoding) {
      return encoding != null ? new OutputStreamWriter(output, encoding) : new OutputStreamWriter(output);
   }

   public static Writer newWriter(File file) {
      return newWriter(file, (Charset)null);
   }

   public static Writer newWriter(File file, String encoding) {
      return newWriter(file, Charset.forName(encoding));
   }

   public static Writer newWriter(File file, Charset encoding) {
      if (!file.exists()) {
         File parent = file.getParentFile();
         if (parent != null && !parent.exists()) {
            parent.mkdirs();
         }

         try {
            file.createNewFile();
         } catch (IOException e) {
            throw new IllegalArgumentException("Unable to create file '" + file.getAbsolutePath() + "', please ensure your application has permission to create files in that path", e);
         }
      }

      FileOutputStream os;
      try {
         os = new FileOutputStream(file);
      } catch (FileNotFoundException e) {
         throw new IllegalArgumentException(e);
      }

      return newWriter((OutputStream)os, (Charset)encoding);
   }

   public static Reader newReader(InputStream input) {
      return newReader(input, (Charset)null);
   }

   public static Reader newReader(InputStream input, String encoding) {
      return newReader(input, encoding == null ? (Charset)null : Charset.forName(encoding));
   }

   public static Reader newReader(InputStream input, Charset encoding) {
      if (encoding == null) {
         BomInput bomInput = new BomInput(input);
         if (bomInput.getEncoding() != null) {
            encoding = bomInput.getCharset();
         }

         if (bomInput.hasBytesStored()) {
            input = bomInput;
         }
      }

      return encoding != null ? new InputStreamReader(input, encoding) : new InputStreamReader(input);
   }

   public static Reader newReader(File file) {
      return newReader(file, (Charset)null);
   }

   public static Reader newReader(File file, String encoding) {
      return newReader(file, Charset.forName(encoding));
   }

   public static Reader newReader(File file, Charset encoding) {
      FileInputStream input;
      try {
         input = new FileInputStream(file);
      } catch (FileNotFoundException e) {
         throw new IllegalArgumentException(e);
      }

      return newReader((InputStream)input, (Charset)encoding);
   }

   public static String[] toArray(List enums) {
      String[] out = new String[enums.size()];

      for(int i = 0; i < out.length; ++i) {
         out[i] = ((Enum)enums.get(i)).toString();
      }

      return out;
   }

   public static int[] toIntArray(Collection ints) {
      int[] out = new int[ints.size()];
      int i = 0;

      for(Integer boxed : ints) {
         out[i++] = boxed;
      }

      return out;
   }

   public static char[] toCharArray(Collection characters) {
      char[] out = new char[characters.size()];
      int i = 0;

      for(Character boxed : characters) {
         out[i++] = boxed;
      }

      return out;
   }

   public static String restrictContent(int length, CharSequence content) {
      if (content == null) {
         return null;
      } else if (length == 0) {
         return "<omitted>";
      } else if (length == -1) {
         return content.toString();
      } else {
         int errorMessageStart = content.length() - length;
         return length > 0 && errorMessageStart > 0 ? "..." + content.subSequence(errorMessageStart, content.length()).toString() : content.toString();
      }
   }

   public static String restrictContent(int length, Object content) {
      if (content == null) {
         return null;
      } else {
         return content instanceof Object[] ? restrictContent(length, (CharSequence)Arrays.toString(content)) : restrictContent(length, (CharSequence)String.valueOf(content));
      }
   }

   public static void throwUnchecked(Throwable error) {
      throwsUnchecked(error);
   }

   private static void throwsUnchecked(Throwable toThrow) throws Exception {
      throw (Exception)toThrow;
   }

   public static byte[] toByteArray(int... ints) {
      byte[] out = new byte[ints.length];

      for(int i = 0; i < ints.length; ++i) {
         out[i] = (byte)ints[i];
      }

      return out;
   }

   public static Object[] findDuplicates(Object[] array) {
      if (array != null && array.length != 0) {
         Set<T> elements = new HashSet(array.length);
         ArrayList<T> duplicates = new ArrayList(1);

         for(Object element : array) {
            if (!elements.contains(element)) {
               elements.add(element);
            } else {
               duplicates.add(element);
            }
         }

         return duplicates.toArray(Array.newInstance(array.getClass().getComponentType(), duplicates.size()));
      } else {
         return array;
      }
   }

   public static String trim(String input, boolean left, boolean right) {
      if (input.length() != 0 && (left || right)) {
         int begin;
         for(begin = 0; left && begin < input.length() && input.charAt(begin) <= ' '; ++begin) {
         }

         if (begin == input.length()) {
            return "";
         } else {
            int end = begin + input.length() - 1;
            if (end >= input.length()) {
               end = input.length() - 1;
            }

            while(right && input.charAt(end) <= ' ') {
               --end;
            }

            if (begin == end) {
               return "";
            } else {
               return begin == 0 && end == input.length() - 1 ? input : input.substring(begin, end + 1);
            }
         }
      } else {
         return input;
      }
   }

   public static String displayLineSeparators(String str, boolean addNewLine) {
      StringBuilder out = new StringBuilder();

      for(int i = 0; i < str.length(); ++i) {
         char ch = str.charAt(i);
         if (ch != '\r' && ch != '\n') {
            out.append(ch);
         } else {
            out.append('[');
            out.append(ch == '\r' ? "cr" : "lf");
            char next = 0;
            if (i + 1 < str.length()) {
               next = str.charAt(i + 1);
               if (next != ch && (next == '\r' || next == '\n')) {
                  out.append(next == '\r' ? "cr" : "lf");
                  ++i;
               } else {
                  next = 0;
               }
            }

            out.append(']');
            if (addNewLine) {
               out.append(ch);
               if (next != 0) {
                  out.append(next);
               }
            }
         }
      }

      return out.toString();
   }

   public static int[] removeAll(int[] array, int e) {
      if (array != null && array.length != 0) {
         int removeCount = 0;

         for(int i = 0; i < array.length; ++i) {
            if (array[i] == e) {
               ++removeCount;
            }
         }

         if (removeCount == 0) {
            return array;
         } else {
            int[] tmp = new int[array.length - removeCount];
            int i = 0;

            for(int j = 0; i < array.length; ++i) {
               if (array[i] != e) {
                  tmp[j++] = array[i];
               }
            }

            return tmp;
         }
      } else {
         return array;
      }
   }
}
