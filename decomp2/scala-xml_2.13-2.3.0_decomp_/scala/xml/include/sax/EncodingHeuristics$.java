package scala.xml.include.sax;

import java.io.InputStream;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.util.matching.Regex;

public final class EncodingHeuristics$ {
   public static final EncodingHeuristics$ MODULE$ = new EncodingHeuristics$();

   public String readEncodingFromStream(final InputStream in) {
      ObjectRef ret;
      int bytesToRead;
      Tuple4 bytes;
      String var10001;
      label229: {
         ret = ObjectRef.create((Object)null);
         bytesToRead = 1024;
         in.mark(bytesToRead);
         bytes = new Tuple4(BoxesRunTime.boxToInteger(in.read()), BoxesRunTime.boxToInteger(in.read()), BoxesRunTime.boxToInteger(in.read()), BoxesRunTime.boxToInteger(in.read()));
         if (bytes != null) {
            int var8 = BoxesRunTime.unboxToInt(bytes._1());
            int var9 = BoxesRunTime.unboxToInt(bytes._2());
            int var10 = BoxesRunTime.unboxToInt(bytes._3());
            int var11 = BoxesRunTime.unboxToInt(bytes._4());
            if (0 == var8 && 0 == var9 && 254 == var10 && 255 == var11) {
               var10001 = EncodingHeuristics.EncodingNames$.MODULE$.bigUCS4();
               break label229;
            }
         }

         if (bytes != null) {
            int var12 = BoxesRunTime.unboxToInt(bytes._1());
            int var13 = BoxesRunTime.unboxToInt(bytes._2());
            int var14 = BoxesRunTime.unboxToInt(bytes._3());
            int var15 = BoxesRunTime.unboxToInt(bytes._4());
            if (255 == var12 && 254 == var13 && 0 == var14 && 0 == var15) {
               var10001 = EncodingHeuristics.EncodingNames$.MODULE$.littleUCS4();
               break label229;
            }
         }

         if (bytes != null) {
            int var16 = BoxesRunTime.unboxToInt(bytes._1());
            int var17 = BoxesRunTime.unboxToInt(bytes._2());
            int var18 = BoxesRunTime.unboxToInt(bytes._3());
            int var19 = BoxesRunTime.unboxToInt(bytes._4());
            if (0 == var16 && 0 == var17 && 255 == var18 && 254 == var19) {
               var10001 = EncodingHeuristics.EncodingNames$.MODULE$.unusualUCS4();
               break label229;
            }
         }

         if (bytes != null) {
            int var20 = BoxesRunTime.unboxToInt(bytes._1());
            int var21 = BoxesRunTime.unboxToInt(bytes._2());
            int var22 = BoxesRunTime.unboxToInt(bytes._3());
            int var23 = BoxesRunTime.unboxToInt(bytes._4());
            if (254 == var20 && 255 == var21 && 0 == var22 && 0 == var23) {
               var10001 = EncodingHeuristics.EncodingNames$.MODULE$.unusualUCS4();
               break label229;
            }
         }

         if (bytes != null) {
            int var24 = BoxesRunTime.unboxToInt(bytes._1());
            int var25 = BoxesRunTime.unboxToInt(bytes._2());
            if (254 == var24 && 255 == var25) {
               var10001 = EncodingHeuristics.EncodingNames$.MODULE$.bigUTF16();
               break label229;
            }
         }

         if (bytes != null) {
            int var26 = BoxesRunTime.unboxToInt(bytes._1());
            int var27 = BoxesRunTime.unboxToInt(bytes._2());
            if (255 == var26 && 254 == var27) {
               var10001 = EncodingHeuristics.EncodingNames$.MODULE$.littleUTF16();
               break label229;
            }
         }

         if (bytes != null) {
            int var28 = BoxesRunTime.unboxToInt(bytes._1());
            int var29 = BoxesRunTime.unboxToInt(bytes._2());
            int var30 = BoxesRunTime.unboxToInt(bytes._3());
            if (239 == var28 && 187 == var29 && 191 == var30) {
               var10001 = EncodingHeuristics.EncodingNames$.MODULE$.utf8();
               break label229;
            }
         }

         var10001 = null;
      }

      ret.elem = var10001;
      if ((String)ret.elem != null) {
         return resetAndRet$1(in, ret);
      } else {
         label230: {
            if (bytes != null) {
               int var32 = BoxesRunTime.unboxToInt(bytes._1());
               int var33 = BoxesRunTime.unboxToInt(bytes._2());
               int var34 = BoxesRunTime.unboxToInt(bytes._3());
               int var35 = BoxesRunTime.unboxToInt(bytes._4());
               if (0 == var32 && 0 == var33 && 0 == var34 && 60 == var35) {
                  var10001 = EncodingHeuristics.EncodingNames$.MODULE$.bigUCS4();
                  break label230;
               }
            }

            if (bytes != null) {
               int var36 = BoxesRunTime.unboxToInt(bytes._1());
               int var37 = BoxesRunTime.unboxToInt(bytes._2());
               int var38 = BoxesRunTime.unboxToInt(bytes._3());
               int var39 = BoxesRunTime.unboxToInt(bytes._4());
               if (60 == var36 && 0 == var37 && 0 == var38 && 0 == var39) {
                  var10001 = EncodingHeuristics.EncodingNames$.MODULE$.littleUCS4();
                  break label230;
               }
            }

            if (bytes != null) {
               int var40 = BoxesRunTime.unboxToInt(bytes._1());
               int var41 = BoxesRunTime.unboxToInt(bytes._2());
               int var42 = BoxesRunTime.unboxToInt(bytes._3());
               int var43 = BoxesRunTime.unboxToInt(bytes._4());
               if (0 == var40 && 0 == var41 && 60 == var42 && 0 == var43) {
                  var10001 = EncodingHeuristics.EncodingNames$.MODULE$.unusualUCS4();
                  break label230;
               }
            }

            if (bytes != null) {
               int var44 = BoxesRunTime.unboxToInt(bytes._1());
               int var45 = BoxesRunTime.unboxToInt(bytes._2());
               int var46 = BoxesRunTime.unboxToInt(bytes._3());
               int var47 = BoxesRunTime.unboxToInt(bytes._4());
               if (0 == var44 && 60 == var45 && 0 == var46 && 0 == var47) {
                  var10001 = EncodingHeuristics.EncodingNames$.MODULE$.unusualUCS4();
                  break label230;
               }
            }

            if (bytes != null) {
               int var48 = BoxesRunTime.unboxToInt(bytes._1());
               int var49 = BoxesRunTime.unboxToInt(bytes._2());
               int var50 = BoxesRunTime.unboxToInt(bytes._3());
               int var51 = BoxesRunTime.unboxToInt(bytes._4());
               if (0 == var48 && 60 == var49 && 0 == var50 && 63 == var51) {
                  var10001 = EncodingHeuristics.EncodingNames$.MODULE$.bigUTF16();
                  break label230;
               }
            }

            if (bytes != null) {
               int var52 = BoxesRunTime.unboxToInt(bytes._1());
               int var53 = BoxesRunTime.unboxToInt(bytes._2());
               int var54 = BoxesRunTime.unboxToInt(bytes._3());
               int var55 = BoxesRunTime.unboxToInt(bytes._4());
               if (60 == var52 && 0 == var53 && 63 == var54 && 0 == var55) {
                  var10001 = EncodingHeuristics.EncodingNames$.MODULE$.littleUTF16();
                  break label230;
               }
            }

            if (bytes != null) {
               int var56 = BoxesRunTime.unboxToInt(bytes._1());
               int var57 = BoxesRunTime.unboxToInt(bytes._2());
               int var58 = BoxesRunTime.unboxToInt(bytes._3());
               int var59 = BoxesRunTime.unboxToInt(bytes._4());
               if (60 == var56 && 63 == var57 && 120 == var58 && 109 == var59) {
                  var10001 = readASCIIEncoding$1(bytesToRead, in);
                  break label230;
               }
            }

            if (bytes != null) {
               int var60 = BoxesRunTime.unboxToInt(bytes._1());
               int var61 = BoxesRunTime.unboxToInt(bytes._2());
               int var62 = BoxesRunTime.unboxToInt(bytes._3());
               int var63 = BoxesRunTime.unboxToInt(bytes._4());
               if (76 == var60 && 111 == var61 && 167 == var62 && 148 == var63) {
                  var10001 = EncodingHeuristics.EncodingNames$.MODULE$.utf8();
                  break label230;
               }
            }

            var10001 = EncodingHeuristics.EncodingNames$.MODULE$.utf8();
         }

         ret.elem = var10001;
         return resetAndRet$1(in, ret);
      }
   }

   private static final String resetAndRet$1(final InputStream in$1, final ObjectRef ret$1) {
      in$1.reset();
      return (String)ret$1.elem;
   }

   private static final String readASCIIEncoding$1(final int bytesToRead$1, final InputStream in$1) {
      byte[] data = new byte[bytesToRead$1 - 4];
      int length = in$1.read(data, 0, bytesToRead$1 - 4);
      String declaration = new String(data, 0, length, "ISO-8859-1");
      Regex regexp = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(?m).*?encoding\\s*=\\s*[\"'](.+?)['\"]"));
      Option var7 = regexp.findFirstMatchIn(declaration);
      if (scala.None..MODULE$.equals(var7)) {
         return EncodingHeuristics.EncodingNames$.MODULE$.default();
      } else if (var7 instanceof Some) {
         Some var8 = (Some)var7;
         Regex.Match md = (Regex.Match)var8.value();
         return (String)md.subgroups().apply(0);
      } else {
         throw new MatchError(var7);
      }
   }

   private EncodingHeuristics$() {
   }
}
