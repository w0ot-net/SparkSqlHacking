package io.fabric8.kubernetes.client.lib;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilenameUtils {
   private static final String[] EMPTY_STRING_ARRAY = new String[0];
   private static final String EMPTY_STRING = "";
   private static final int NOT_FOUND = -1;
   private static final char UNIX_SEPARATOR = '/';
   private static final char WINDOWS_SEPARATOR = '\\';
   private static final char SYSTEM_SEPARATOR;
   private static final char OTHER_SEPARATOR;
   private static final Pattern IPV4_PATTERN;
   private static final int IPV4_MAX_OCTET_VALUE = 255;
   private static final int IPV6_MAX_HEX_GROUPS = 8;
   private static final int IPV6_MAX_HEX_DIGITS_PER_GROUP = 4;
   private static final int MAX_UNSIGNED_SHORT = 65535;
   private static final int BASE_16 = 16;
   private static final Pattern REG_NAME_PART_PATTERN;

   private FilenameUtils() {
   }

   static boolean isSystemWindows() {
      return SYSTEM_SEPARATOR == '\\';
   }

   private static boolean isSeparator(char ch) {
      return ch == '/' || ch == '\\';
   }

   public static String normalize(String fileName) {
      return doNormalize(fileName, SYSTEM_SEPARATOR, true);
   }

   public static String normalizeNoEndSeparator(String fileName, boolean unixSeparator) {
      char separator = (char)(unixSeparator ? 47 : 92);
      return doNormalize(fileName, separator, false);
   }

   private static String doNormalize(String fileName, char separator, boolean keepSeparator) {
      if (fileName == null) {
         return null;
      } else {
         requireNonNullChars(fileName);
         int size = fileName.length();
         if (size == 0) {
            return fileName;
         } else {
            int prefix = getPrefixLength(fileName);
            if (prefix < 0) {
               return null;
            } else {
               char[] array = new char[size + 2];
               fileName.getChars(0, fileName.length(), array, 0);
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
         return isSystemWindows() ? separatorsToWindows(path) : separatorsToUnix(path);
      }
   }

   public static int getPrefixLength(String fileName) {
      if (fileName == null) {
         return -1;
      } else {
         int len = fileName.length();
         if (len == 0) {
            return 0;
         } else {
            char ch0 = fileName.charAt(0);
            if (ch0 == ':') {
               return -1;
            } else if (len == 1) {
               if (ch0 == '~') {
                  return 2;
               } else {
                  return isSeparator(ch0) ? 1 : 0;
               }
            } else if (ch0 == '~') {
               int posUnix = fileName.indexOf(47, 1);
               int posWin = fileName.indexOf(92, 1);
               if (posUnix == -1 && posWin == -1) {
                  return len + 1;
               } else {
                  posUnix = posUnix == -1 ? posWin : posUnix;
                  posWin = posWin == -1 ? posUnix : posWin;
                  return Math.min(posUnix, posWin) + 1;
               }
            } else {
               char ch1 = fileName.charAt(1);
               if (ch1 == ':') {
                  ch0 = Character.toUpperCase(ch0);
                  if (ch0 >= 'A' && ch0 <= 'Z') {
                     if (len == 2 && !FileSystem.getCurrent().supportsDriveLetter()) {
                        return 0;
                     } else {
                        return len != 2 && isSeparator(fileName.charAt(2)) ? 3 : 2;
                     }
                  } else {
                     return ch0 == '/' ? 1 : -1;
                  }
               } else if (isSeparator(ch0) && isSeparator(ch1)) {
                  int posUnix = fileName.indexOf(47, 2);
                  int posWin = fileName.indexOf(92, 2);
                  if ((posUnix != -1 || posWin != -1) && posUnix != 2 && posWin != 2) {
                     posUnix = posUnix == -1 ? posWin : posUnix;
                     posWin = posWin == -1 ? posUnix : posWin;
                     int pos = Math.min(posUnix, posWin) + 1;
                     String hostnamePart = fileName.substring(2, pos - 1);
                     return isValidHostName(hostnamePart) ? pos : -1;
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

   private static void requireNonNullChars(String path) {
      if (path.indexOf(0) >= 0) {
         throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
      }
   }

   private static boolean isValidHostName(String name) {
      return isIPv6Address(name) || isRFC3986HostName(name);
   }

   private static boolean isIPv4Address(String name) {
      Matcher m = IPV4_PATTERN.matcher(name);
      if (m.matches() && m.groupCount() == 4) {
         for(int i = 1; i <= 4; ++i) {
            String ipSegment = m.group(i);
            int iIpSegment = Integer.parseInt(ipSegment);
            if (iIpSegment > 255) {
               return false;
            }

            if (ipSegment.length() > 1 && ipSegment.startsWith("0")) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean isIPv6Address(String inet6Address) {
      boolean containsCompressedZeroes = inet6Address.contains("::");
      if (containsCompressedZeroes && inet6Address.indexOf("::") != inet6Address.lastIndexOf("::")) {
         return false;
      } else if ((!inet6Address.startsWith(":") || inet6Address.startsWith("::")) && (!inet6Address.endsWith(":") || inet6Address.endsWith("::"))) {
         String[] octets = inet6Address.split(":");
         if (containsCompressedZeroes) {
            List<String> octetList = new ArrayList(Arrays.asList(octets));
            if (inet6Address.endsWith("::")) {
               octetList.add("");
            } else if (inet6Address.startsWith("::") && !octetList.isEmpty()) {
               octetList.remove(0);
            }

            octets = (String[])octetList.toArray(EMPTY_STRING_ARRAY);
         }

         if (octets.length > 8) {
            return false;
         } else {
            int validOctets = 0;
            int emptyOctets = 0;

            for(int index = 0; index < octets.length; ++index) {
               String octet = octets[index];
               if (octet.isEmpty()) {
                  ++emptyOctets;
                  if (emptyOctets > 1) {
                     return false;
                  }
               } else {
                  emptyOctets = 0;
                  if (index == octets.length - 1 && octet.contains(".")) {
                     if (!isIPv4Address(octet)) {
                        return false;
                     }

                     validOctets += 2;
                     continue;
                  }

                  if (octet.length() > 4) {
                     return false;
                  }

                  int octetInt = 0;

                  try {
                     octetInt = Integer.parseInt(octet, 16);
                  } catch (NumberFormatException var9) {
                     return false;
                  }

                  if (octetInt < 0 || octetInt > 65535) {
                     return false;
                  }
               }

               ++validOctets;
            }

            return validOctets <= 8 && (validOctets >= 8 || containsCompressedZeroes);
         }
      } else {
         return false;
      }
   }

   private static boolean isRFC3986HostName(String name) {
      String[] parts = name.split("\\.", -1);

      for(int i = 0; i < parts.length; ++i) {
         if (parts[i].isEmpty()) {
            return i == parts.length - 1;
         }

         if (!REG_NAME_PART_PATTERN.matcher(parts[i]).matches()) {
            return false;
         }
      }

      return true;
   }

   static {
      SYSTEM_SEPARATOR = File.separatorChar;
      if (isSystemWindows()) {
         OTHER_SEPARATOR = '/';
      } else {
         OTHER_SEPARATOR = '\\';
      }

      IPV4_PATTERN = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
      REG_NAME_PART_PATTERN = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9-]*$");
   }
}
