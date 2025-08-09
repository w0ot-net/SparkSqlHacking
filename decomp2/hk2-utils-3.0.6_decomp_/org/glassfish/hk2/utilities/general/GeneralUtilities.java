package org.glassfish.hk2.utilities.general;

import java.lang.reflect.Array;
import org.glassfish.hk2.utilities.general.internal.WeakHashClockImpl;
import org.glassfish.hk2.utilities.general.internal.WeakHashLRUImpl;

public class GeneralUtilities {
   public static boolean safeEquals(Object a, Object b) {
      if (a == b) {
         return true;
      } else if (a == null) {
         return false;
      } else {
         return b == null ? false : a.equals(b);
      }
   }

   private static Class loadArrayClass(ClassLoader cl, String aName) {
      Class<?> componentType = null;
      int[] dimensions = null;
      int dot = 0;

      while(componentType == null) {
         char dotChar = aName.charAt(dot);
         if (dotChar == '[') {
            ++dot;
         } else {
            dimensions = new int[dot];

            for(int lcv = 0; lcv < dot; ++lcv) {
               dimensions[lcv] = 0;
            }

            if (dotChar == 'B') {
               componentType = Byte.TYPE;
            } else if (dotChar == 'I') {
               componentType = Integer.TYPE;
            } else if (dotChar == 'J') {
               componentType = Long.TYPE;
            } else if (dotChar == 'Z') {
               componentType = Boolean.TYPE;
            } else if (dotChar == 'S') {
               componentType = Short.TYPE;
            } else if (dotChar == 'C') {
               componentType = Character.TYPE;
            } else if (dotChar == 'D') {
               componentType = Double.TYPE;
            } else if (dotChar == 'F') {
               componentType = Float.TYPE;
            } else {
               if (dotChar != 'L') {
                  throw new IllegalArgumentException("Unknown array type " + aName);
               }

               if (aName.charAt(aName.length() - 1) != ';') {
                  throw new IllegalArgumentException("Badly formed L array expresion: " + aName);
               }

               String cName = aName.substring(dot + 1, aName.length() - 1);
               componentType = loadClass(cl, cName);
               if (componentType == null) {
                  return null;
               }
            }
         }
      }

      Object retArray = Array.newInstance(componentType, dimensions);
      return retArray.getClass();
   }

   public static Class loadClass(ClassLoader cl, String cName) {
      if (cName.startsWith("[")) {
         return loadArrayClass(cl, cName);
      } else {
         try {
            return cl.loadClass(cName);
         } catch (Throwable var3) {
            return null;
         }
      }
   }

   public static WeakHashClock getWeakHashClock(boolean isWeak) {
      return new WeakHashClockImpl(isWeak);
   }

   public static WeakHashLRU getWeakHashLRU(boolean isWeak) {
      return new WeakHashLRUImpl(isWeak);
   }

   public static String prettyPrintBytes(byte[] bytes) {
      StringBuffer sb = new StringBuffer("Total buffer length: " + bytes.length + "\n");
      int numEntered = 0;

      for(byte b : bytes) {
         if (numEntered % 16 == 0) {
            if (numEntered != 0) {
               sb.append("\n");
            }

            String desc = String.format("%08X ", numEntered);
            sb.append(desc);
         }

         String singleByte = String.format("%02X ", b);
         sb.append(singleByte);
         ++numEntered;
         if (numEntered % 8 == 0 && numEntered % 16 != 0) {
            sb.append(" ");
         }
      }

      return sb.toString();
   }
}
