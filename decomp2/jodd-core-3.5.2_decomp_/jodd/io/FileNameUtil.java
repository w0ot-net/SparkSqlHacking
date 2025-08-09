package jodd.io;

import java.io.File;

public class FileNameUtil {
   private static final char EXTENSION_SEPARATOR = '.';
   private static final char UNIX_SEPARATOR = '/';
   private static final char WINDOWS_SEPARATOR = '\\';
   private static final char SYSTEM_SEPARATOR;
   private static final char OTHER_SEPARATOR;

   private static boolean isSeparator(char ch) {
      return ch == '/' || ch == '\\';
   }

   public static String normalize(String filename) {
      return doNormalize(filename, SYSTEM_SEPARATOR, true);
   }

   public static String normalize(String filename, boolean unixSeparator) {
      char separator = (char)(unixSeparator ? 47 : 92);
      return doNormalize(filename, separator, true);
   }

   public static String normalizeNoEndSeparator(String filename) {
      return doNormalize(filename, SYSTEM_SEPARATOR, false);
   }

   public static String normalizeNoEndSeparator(String filename, boolean unixSeparator) {
      char separator = (char)(unixSeparator ? 47 : 92);
      return doNormalize(filename, separator, false);
   }

   private static String doNormalize(String filename, char separator, boolean keepSeparator) {
      if (filename == null) {
         return null;
      } else {
         int size = filename.length();
         if (size == 0) {
            return filename;
         } else {
            int prefix = getPrefixLength(filename);
            if (prefix < 0) {
               return null;
            } else {
               char[] array = new char[size + 2];
               filename.getChars(0, filename.length(), array, 0);
               char otherSeparator = separator == SYSTEM_SEPARATOR ? OTHER_SEPARATOR : SYSTEM_SEPARATOR;

               for(int i = 0; i < array.length; ++i) {
                  if (array[i] == otherSeparator) {
                     array[i] = separator;
                  }
               }

               boolean lastIsDirectory = true;
               if (array[size - 1] != separator) {
                  array[size++] = separator;
                  lastIsDirectory = false;
               }

               for(int i = prefix + 1; i < size; ++i) {
                  if (array[i] == separator && array[i - 1] == separator) {
                     System.arraycopy(array, i, array, i - 1, size - i);
                     --size;
                     --i;
                  }
               }

               for(int i = prefix + 1; i < size; ++i) {
                  if (array[i] == separator && array[i - 1] == '.' && (i == prefix + 1 || array[i - 2] == separator)) {
                     if (i == size - 1) {
                        lastIsDirectory = true;
                     }

                     System.arraycopy(array, i + 1, array, i - 1, size - i);
                     size -= 2;
                     --i;
                  }
               }

               label109:
               for(int i = prefix + 2; i < size; ++i) {
                  if (array[i] == separator && array[i - 1] == '.' && array[i - 2] == '.' && (i == prefix + 2 || array[i - 3] == separator)) {
                     if (i == prefix + 2) {
                        return null;
                     }

                     if (i == size - 1) {
                        lastIsDirectory = true;
                     }

                     for(int j = i - 4; j >= prefix; --j) {
                        if (array[j] == separator) {
                           System.arraycopy(array, i + 1, array, j + 1, size - i);
                           size -= i - j;
                           i = j + 1;
                           continue label109;
                        }
                     }

                     System.arraycopy(array, i + 1, array, prefix, size - i);
                     size -= i + 1 - prefix;
                     i = prefix + 1;
                  }
               }

               if (size <= 0) {
                  return "";
               } else if (size <= prefix) {
                  return new String(array, 0, size);
               } else if (lastIsDirectory && keepSeparator) {
                  return new String(array, 0, size);
               } else {
                  return new String(array, 0, size - 1);
               }
            }
         }
      }
   }

   public static String concat(String basePath, String fullFilenameToAdd) {
      return doConcat(basePath, fullFilenameToAdd, SYSTEM_SEPARATOR);
   }

   public static String concat(String basePath, String fullFilenameToAdd, boolean unixSeparator) {
      char separator = (char)(unixSeparator ? 47 : 92);
      return doConcat(basePath, fullFilenameToAdd, separator);
   }

   public static String doConcat(String basePath, String fullFilenameToAdd, char separator) {
      int prefix = getPrefixLength(fullFilenameToAdd);
      if (prefix < 0) {
         return null;
      } else if (prefix > 0) {
         return doNormalize(fullFilenameToAdd, separator, true);
      } else if (basePath == null) {
         return null;
      } else {
         int len = basePath.length();
         if (len == 0) {
            return doNormalize(fullFilenameToAdd, separator, true);
         } else {
            char ch = basePath.charAt(len - 1);
            return isSeparator(ch) ? doNormalize(basePath + fullFilenameToAdd, separator, true) : doNormalize(basePath + '/' + fullFilenameToAdd, separator, true);
         }
      }
   }

   public static String separatorsToUnix(String path) {
      return path != null && path.indexOf(92) != -1 ? path.replace('\\', '/') : path;
   }

   public static String separatorsToWindows(String path) {
      return path != null && path.indexOf(47) != -1 ? path.replace('/', '\\') : path;
   }

   public static String separatorsToSystem(String path) {
      if (path == null) {
         return null;
      } else {
         return SYSTEM_SEPARATOR == '\\' ? separatorsToWindows(path) : separatorsToUnix(path);
      }
   }

   public static int getPrefixLength(String filename) {
      if (filename == null) {
         return -1;
      } else {
         int len = filename.length();
         if (len == 0) {
            return 0;
         } else {
            char ch0 = filename.charAt(0);
            if (ch0 == ':') {
               return -1;
            } else if (len == 1) {
               if (ch0 == '~') {
                  return 2;
               } else {
                  return isSeparator(ch0) ? 1 : 0;
               }
            } else if (ch0 == '~') {
               int posUnix = filename.indexOf(47, 1);
               int posWin = filename.indexOf(92, 1);
               if (posUnix == -1 && posWin == -1) {
                  return len + 1;
               } else {
                  posUnix = posUnix == -1 ? posWin : posUnix;
                  posWin = posWin == -1 ? posUnix : posWin;
                  return Math.min(posUnix, posWin) + 1;
               }
            } else {
               char ch1 = filename.charAt(1);
               if (ch1 == ':') {
                  ch0 = Character.toUpperCase(ch0);
                  if (ch0 >= 'A' && ch0 <= 'Z') {
                     return len != 2 && isSeparator(filename.charAt(2)) ? 3 : 2;
                  } else {
                     return -1;
                  }
               } else if (isSeparator(ch0) && isSeparator(ch1)) {
                  int posUnix = filename.indexOf(47, 2);
                  int posWin = filename.indexOf(92, 2);
                  if ((posUnix != -1 || posWin != -1) && posUnix != 2 && posWin != 2) {
                     posUnix = posUnix == -1 ? posWin : posUnix;
                     posWin = posWin == -1 ? posUnix : posWin;
                     return Math.min(posUnix, posWin) + 1;
                  } else {
                     return -1;
                  }
               } else {
                  return isSeparator(ch0) ? 1 : 0;
               }
            }
         }
      }
   }

   public static int indexOfLastSeparator(String filename) {
      if (filename == null) {
         return -1;
      } else {
         int lastUnixPos = filename.lastIndexOf(47);
         int lastWindowsPos = filename.lastIndexOf(92);
         return Math.max(lastUnixPos, lastWindowsPos);
      }
   }

   public static int indexOfExtension(String filename) {
      if (filename == null) {
         return -1;
      } else {
         int extensionPos = filename.lastIndexOf(46);
         int lastSeparator = indexOfLastSeparator(filename);
         return lastSeparator > extensionPos ? -1 : extensionPos;
      }
   }

   public static String getPrefix(String filename) {
      if (filename == null) {
         return null;
      } else {
         int len = getPrefixLength(filename);
         if (len < 0) {
            return null;
         } else {
            return len > filename.length() ? filename + '/' : filename.substring(0, len);
         }
      }
   }

   public static String getPath(String filename) {
      return doGetPath(filename, 1);
   }

   public static String getPathNoEndSeparator(String filename) {
      return doGetPath(filename, 0);
   }

   private static String doGetPath(String filename, int separatorAdd) {
      if (filename == null) {
         return null;
      } else {
         int prefix = getPrefixLength(filename);
         if (prefix < 0) {
            return null;
         } else {
            int index = indexOfLastSeparator(filename);
            int endIndex = index + separatorAdd;
            return prefix < filename.length() && index >= 0 && prefix < endIndex ? filename.substring(prefix, endIndex) : "";
         }
      }
   }

   public static String getFullPath(String filename) {
      return doGetFullPath(filename, true);
   }

   public static String getFullPathNoEndSeparator(String filename) {
      return doGetFullPath(filename, false);
   }

   private static String doGetFullPath(String filename, boolean includeSeparator) {
      if (filename == null) {
         return null;
      } else {
         int prefix = getPrefixLength(filename);
         if (prefix < 0) {
            return null;
         } else if (prefix >= filename.length()) {
            return includeSeparator ? getPrefix(filename) : filename;
         } else {
            int index = indexOfLastSeparator(filename);
            if (index < 0) {
               return filename.substring(0, prefix);
            } else {
               int end = index + (includeSeparator ? 1 : 0);
               if (end == 0) {
                  ++end;
               }

               return filename.substring(0, end);
            }
         }
      }
   }

   public static String getName(String filename) {
      if (filename == null) {
         return null;
      } else {
         int index = indexOfLastSeparator(filename);
         return filename.substring(index + 1);
      }
   }

   public static String getBaseName(String filename) {
      return removeExtension(getName(filename));
   }

   public static String getExtension(String filename) {
      if (filename == null) {
         return null;
      } else {
         int index = indexOfExtension(filename);
         return index == -1 ? "" : filename.substring(index + 1);
      }
   }

   public static String removeExtension(String filename) {
      if (filename == null) {
         return null;
      } else {
         int index = indexOfExtension(filename);
         return index == -1 ? filename : filename.substring(0, index);
      }
   }

   public static boolean equals(String filename1, String filename2) {
      return equals(filename1, filename2, false);
   }

   public static boolean equalsOnSystem(String filename1, String filename2) {
      return equals(filename1, filename2, true);
   }

   private static boolean equals(String filename1, String filename2, boolean system) {
      if (filename1 == filename2) {
         return true;
      } else if (filename1 != null && filename2 != null) {
         return system && SYSTEM_SEPARATOR == '\\' ? filename1.equalsIgnoreCase(filename2) : filename1.equals(filename2);
      } else {
         return false;
      }
   }

   public static String[] split(String filename) {
      String prefix = getPrefix(filename);
      if (prefix == null) {
         prefix = "";
      }

      int lastSeparatorIndex = indexOfLastSeparator(filename);
      int lastExtensionIndex = indexOfExtension(filename);
      String path;
      String baseName;
      String extension;
      if (lastSeparatorIndex == -1) {
         path = "";
         if (lastExtensionIndex == -1) {
            baseName = filename.substring(prefix.length());
            extension = "";
         } else {
            baseName = filename.substring(prefix.length(), lastExtensionIndex);
            extension = filename.substring(lastExtensionIndex + 1);
         }
      } else {
         path = filename.substring(prefix.length(), lastSeparatorIndex + 1);
         if (lastExtensionIndex == -1) {
            baseName = filename.substring(prefix.length() + path.length());
            extension = "";
         } else {
            baseName = filename.substring(prefix.length() + path.length(), lastExtensionIndex);
            extension = filename.substring(lastExtensionIndex + 1);
         }
      }

      return new String[]{prefix, path, baseName, extension};
   }

   static {
      SYSTEM_SEPARATOR = File.separatorChar;
      if (SYSTEM_SEPARATOR == '\\') {
         OTHER_SEPARATOR = '/';
      } else {
         OTHER_SEPARATOR = '\\';
      }

   }
}
