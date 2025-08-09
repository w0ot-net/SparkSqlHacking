package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CodecEncoding {
   private static final int[] EMPTY_INT_ARRAY = new int[0];
   private static final BHSDCodec[] canonicalCodec = new BHSDCodec[]{null, new BHSDCodec(1, 256), new BHSDCodec(1, 256, 1), new BHSDCodec(1, 256, 0, 1), new BHSDCodec(1, 256, 1, 1), new BHSDCodec(2, 256), new BHSDCodec(2, 256, 1), new BHSDCodec(2, 256, 0, 1), new BHSDCodec(2, 256, 1, 1), new BHSDCodec(3, 256), new BHSDCodec(3, 256, 1), new BHSDCodec(3, 256, 0, 1), new BHSDCodec(3, 256, 1, 1), new BHSDCodec(4, 256), new BHSDCodec(4, 256, 1), new BHSDCodec(4, 256, 0, 1), new BHSDCodec(4, 256, 1, 1), new BHSDCodec(5, 4), new BHSDCodec(5, 4, 1), new BHSDCodec(5, 4, 2), new BHSDCodec(5, 16), new BHSDCodec(5, 16, 1), new BHSDCodec(5, 16, 2), new BHSDCodec(5, 32), new BHSDCodec(5, 32, 1), new BHSDCodec(5, 32, 2), new BHSDCodec(5, 64), new BHSDCodec(5, 64, 1), new BHSDCodec(5, 64, 2), new BHSDCodec(5, 128), new BHSDCodec(5, 128, 1), new BHSDCodec(5, 128, 2), new BHSDCodec(5, 4, 0, 1), new BHSDCodec(5, 4, 1, 1), new BHSDCodec(5, 4, 2, 1), new BHSDCodec(5, 16, 0, 1), new BHSDCodec(5, 16, 1, 1), new BHSDCodec(5, 16, 2, 1), new BHSDCodec(5, 32, 0, 1), new BHSDCodec(5, 32, 1, 1), new BHSDCodec(5, 32, 2, 1), new BHSDCodec(5, 64, 0, 1), new BHSDCodec(5, 64, 1, 1), new BHSDCodec(5, 64, 2, 1), new BHSDCodec(5, 128, 0, 1), new BHSDCodec(5, 128, 1, 1), new BHSDCodec(5, 128, 2, 1), new BHSDCodec(2, 192), new BHSDCodec(2, 224), new BHSDCodec(2, 240), new BHSDCodec(2, 248), new BHSDCodec(2, 252), new BHSDCodec(2, 8, 0, 1), new BHSDCodec(2, 8, 1, 1), new BHSDCodec(2, 16, 0, 1), new BHSDCodec(2, 16, 1, 1), new BHSDCodec(2, 32, 0, 1), new BHSDCodec(2, 32, 1, 1), new BHSDCodec(2, 64, 0, 1), new BHSDCodec(2, 64, 1, 1), new BHSDCodec(2, 128, 0, 1), new BHSDCodec(2, 128, 1, 1), new BHSDCodec(2, 192, 0, 1), new BHSDCodec(2, 192, 1, 1), new BHSDCodec(2, 224, 0, 1), new BHSDCodec(2, 224, 1, 1), new BHSDCodec(2, 240, 0, 1), new BHSDCodec(2, 240, 1, 1), new BHSDCodec(2, 248, 0, 1), new BHSDCodec(2, 248, 1, 1), new BHSDCodec(3, 192), new BHSDCodec(3, 224), new BHSDCodec(3, 240), new BHSDCodec(3, 248), new BHSDCodec(3, 252), new BHSDCodec(3, 8, 0, 1), new BHSDCodec(3, 8, 1, 1), new BHSDCodec(3, 16, 0, 1), new BHSDCodec(3, 16, 1, 1), new BHSDCodec(3, 32, 0, 1), new BHSDCodec(3, 32, 1, 1), new BHSDCodec(3, 64, 0, 1), new BHSDCodec(3, 64, 1, 1), new BHSDCodec(3, 128, 0, 1), new BHSDCodec(3, 128, 1, 1), new BHSDCodec(3, 192, 0, 1), new BHSDCodec(3, 192, 1, 1), new BHSDCodec(3, 224, 0, 1), new BHSDCodec(3, 224, 1, 1), new BHSDCodec(3, 240, 0, 1), new BHSDCodec(3, 240, 1, 1), new BHSDCodec(3, 248, 0, 1), new BHSDCodec(3, 248, 1, 1), new BHSDCodec(4, 192), new BHSDCodec(4, 224), new BHSDCodec(4, 240), new BHSDCodec(4, 248), new BHSDCodec(4, 252), new BHSDCodec(4, 8, 0, 1), new BHSDCodec(4, 8, 1, 1), new BHSDCodec(4, 16, 0, 1), new BHSDCodec(4, 16, 1, 1), new BHSDCodec(4, 32, 0, 1), new BHSDCodec(4, 32, 1, 1), new BHSDCodec(4, 64, 0, 1), new BHSDCodec(4, 64, 1, 1), new BHSDCodec(4, 128, 0, 1), new BHSDCodec(4, 128, 1, 1), new BHSDCodec(4, 192, 0, 1), new BHSDCodec(4, 192, 1, 1), new BHSDCodec(4, 224, 0, 1), new BHSDCodec(4, 224, 1, 1), new BHSDCodec(4, 240, 0, 1), new BHSDCodec(4, 240, 1, 1), new BHSDCodec(4, 248, 0, 1), new BHSDCodec(4, 248, 1, 1)};
   private static Map canonicalCodecsToSpecifiers;

   public static BHSDCodec getCanonicalCodec(int i) {
      return canonicalCodec[i];
   }

   public static Codec getCodec(int value, InputStream in, Codec defaultCodec) throws IOException, Pack200Exception {
      if (canonicalCodec.length != 116) {
         throw new Error("Canonical encodings have been incorrectly modified");
      } else if (value < 0) {
         throw new IllegalArgumentException("Encoding cannot be less than zero");
      } else if (value == 0) {
         return defaultCodec;
      } else if (value <= 115) {
         return canonicalCodec[value];
      } else if (value == 116) {
         int code = in.read();
         if (code == -1) {
            throw new EOFException("End of buffer read whilst trying to decode codec");
         } else {
            int d = code & 1;
            int s = code >> 1 & 3;
            int b = (code >> 3 & 7) + 1;
            code = in.read();
            if (code == -1) {
               throw new EOFException("End of buffer read whilst trying to decode codec");
            } else {
               int h = code + 1;
               return new BHSDCodec(b, h, s, d);
            }
         }
      } else if (value >= 117 && value <= 140) {
         int offset = value - 117;
         int kx = offset & 3;
         boolean kbflag = (offset >> 2 & 1) == 1;
         boolean adef = (offset >> 3 & 1) == 1;
         boolean bdef = (offset >> 4 & 1) == 1;
         if (adef && bdef) {
            throw new Pack200Exception("ADef and BDef should never both be true");
         } else {
            int kb = kbflag ? in.read() : 3;
            int k = (kb + 1) * (int)Math.pow((double)16.0F, (double)kx);
            Codec aCodec;
            if (adef) {
               aCodec = defaultCodec;
            } else {
               aCodec = getCodec(in.read(), in, defaultCodec);
            }

            Codec bCodec;
            if (bdef) {
               bCodec = defaultCodec;
            } else {
               bCodec = getCodec(in.read(), in, defaultCodec);
            }

            return new RunCodec(k, aCodec, bCodec);
         }
      } else if (value >= 141 && value <= 188) {
         int offset = value - 141;
         boolean fdef = (offset & 1) == 1;
         boolean udef = (offset >> 1 & 1) == 1;
         int tdefl = offset >> 2;
         boolean tdef = tdefl != 0;
         int[] tdefToL = new int[]{0, 4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252};
         int l = tdefToL[tdefl];
         if (tdef) {
            Codec fCodec = fdef ? defaultCodec : getCodec(in.read(), in, defaultCodec);
            Codec uCodec = udef ? defaultCodec : getCodec(in.read(), in, defaultCodec);
            return new PopulationCodec(fCodec, l, uCodec);
         } else {
            Codec fCodec = fdef ? defaultCodec : getCodec(in.read(), in, defaultCodec);
            Codec tCodec = getCodec(in.read(), in, defaultCodec);
            Codec uCodec = udef ? defaultCodec : getCodec(in.read(), in, defaultCodec);
            return new PopulationCodec(fCodec, tCodec, uCodec);
         }
      } else {
         throw new Pack200Exception("Invalid codec encoding byte (" + value + ") found");
      }
   }

   public static int[] getSpecifier(Codec codec, Codec defaultForBand) {
      if (canonicalCodecsToSpecifiers.containsKey(codec)) {
         return new int[]{(Integer)canonicalCodecsToSpecifiers.get(codec)};
      } else if (codec instanceof BHSDCodec) {
         BHSDCodec bhsdCodec = (BHSDCodec)codec;
         int[] specifiers = new int[3];
         specifiers[0] = 116;
         specifiers[1] = (bhsdCodec.isDelta() ? 1 : 0) + 2 * bhsdCodec.getS() + 8 * (bhsdCodec.getB() - 1);
         specifiers[2] = bhsdCodec.getH() - 1;
         return specifiers;
      } else if (codec instanceof RunCodec) {
         RunCodec runCodec = (RunCodec)codec;
         int k = runCodec.getK();
         int kb;
         int kx;
         if (k <= 256) {
            kb = 0;
            kx = k - 1;
         } else if (k <= 4096) {
            kb = 1;
            kx = k / 16 - 1;
         } else if (k <= 65536) {
            kb = 2;
            kx = k / 256 - 1;
         } else {
            kb = 3;
            kx = k / 4096 - 1;
         }

         Codec aCodec = runCodec.getACodec();
         Codec bCodec = runCodec.getBCodec();
         int abDef = 0;
         if (aCodec.equals(defaultForBand)) {
            abDef = 1;
         } else if (bCodec.equals(defaultForBand)) {
            abDef = 2;
         }

         int first = 117 + kb + (kx == 3 ? 0 : 4) + 8 * abDef;
         int[] aSpecifier = abDef == 1 ? EMPTY_INT_ARRAY : getSpecifier(aCodec, defaultForBand);
         int[] bSpecifier = abDef == 2 ? EMPTY_INT_ARRAY : getSpecifier(bCodec, defaultForBand);
         int[] specifier = new int[1 + (kx == 3 ? 0 : 1) + aSpecifier.length + bSpecifier.length];
         specifier[0] = first;
         int index = 1;
         if (kx != 3) {
            specifier[1] = kx;
            ++index;
         }

         for(int element : aSpecifier) {
            specifier[index] = element;
            ++index;
         }

         for(int element : bSpecifier) {
            specifier[index] = element;
            ++index;
         }

         return specifier;
      } else if (!(codec instanceof PopulationCodec)) {
         return null;
      } else {
         PopulationCodec populationCodec = (PopulationCodec)codec;
         Codec tokenCodec = populationCodec.getTokenCodec();
         Codec favouredCodec = populationCodec.getFavouredCodec();
         Codec unfavouredCodec = populationCodec.getUnfavouredCodec();
         int fDef = favouredCodec.equals(defaultForBand) ? 1 : 0;
         int uDef = unfavouredCodec.equals(defaultForBand) ? 1 : 0;
         int tDefL = 0;
         int[] favoured = populationCodec.getFavoured();
         if (favoured != null) {
            if (tokenCodec == Codec.BYTE1) {
               tDefL = 1;
            } else if (tokenCodec instanceof BHSDCodec) {
               BHSDCodec tokenBHSD = (BHSDCodec)tokenCodec;
               if (tokenBHSD.getS() == 0) {
                  int[] possibleLValues = new int[]{4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252};
                  int l = 256 - tokenBHSD.getH();
                  int index = Arrays.binarySearch(possibleLValues, l);
                  if (index != -1) {
                     tDefL = index++;
                  }
               }
            }
         }

         int first = 141 + fDef + 2 * uDef + 4 * tDefL;
         int[] favouredSpecifier = fDef == 1 ? EMPTY_INT_ARRAY : getSpecifier(favouredCodec, defaultForBand);
         int[] tokenSpecifier = tDefL != 0 ? EMPTY_INT_ARRAY : getSpecifier(tokenCodec, defaultForBand);
         int[] unfavouredSpecifier = uDef == 1 ? EMPTY_INT_ARRAY : getSpecifier(unfavouredCodec, defaultForBand);
         int[] specifier = new int[1 + favouredSpecifier.length + unfavouredSpecifier.length + tokenSpecifier.length];
         specifier[0] = first;
         int index = 1;

         for(int element : favouredSpecifier) {
            specifier[index] = element;
            ++index;
         }

         for(int element : tokenSpecifier) {
            specifier[index] = element;
            ++index;
         }

         for(int element : unfavouredSpecifier) {
            specifier[index] = element;
            ++index;
         }

         return specifier;
      }
   }

   public static int getSpecifierForDefaultCodec(BHSDCodec defaultCodec) {
      return getSpecifier(defaultCodec, (Codec)null)[0];
   }

   static {
      HashMap<BHSDCodec, Integer> reverseMap = new HashMap(canonicalCodec.length);

      for(int i = 0; i < canonicalCodec.length; ++i) {
         reverseMap.put(canonicalCodec[i], i);
      }

      canonicalCodecsToSpecifiers = reverseMap;
   }
}
