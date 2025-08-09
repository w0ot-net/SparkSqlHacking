package scala.sys.process;

import scala.Function1;
import scala.collection.SeqOps;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.ArrayBuffer;
import scala.runtime.BooleanRef;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;

public final class Parser$ {
   public static final Parser$ MODULE$ = new Parser$();

   private final char DQ() {
      return '"';
   }

   private final char SQ() {
      return '\'';
   }

   private final int EOF() {
      return -1;
   }

   public List tokenize(final String line, final Function1 errorFn) {
      Object var14 = Nil$.MODULE$;
      int var15 = 0;
      ArrayBuffer qpos = new ArrayBuffer(16);

      while(true) {
         while(Character.isWhitespace(var15 >= line.length() ? -1 : line.charAt(var15))) {
            ++var15;
         }

         int var16 = var15;
         if (var15 >= line.length()) {
            return ((List)var14).reverse();
         }

         boolean var17 = false;

         boolean var10000;
         label146:
         while(true) {
            int var4 = var15 >= line.length() ? -1 : line.charAt(var15);
            if (var17) {
               var17 = false;
               ++var15;
            } else if (92 == var4) {
               var17 = true;
               ++var15;
            } else if (34 == var4 ? true : 39 == var4) {
               Object loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem = var15;
               qpos.addOne(loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem);
               loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem = null;
               ++var15;
               boolean var18 = false;

               while(true) {
                  int var6 = var15 >= line.length() ? -1 : line.charAt(var15);
                  if (var18) {
                     var18 = false;
                     var10000 = false;
                  } else if (92 == var6) {
                     var18 = true;
                     var10000 = false;
                  } else {
                     var10000 = var4 == var6 ? true : -1 == var6;
                  }

                  if (var10000) {
                     if (var15 >= line.length()) {
                        var10000 = false;
                        break label146;
                     }

                     Object loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem = var15;
                     qpos.addOne(loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem);
                     loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem = null;
                     ++var15;
                     break;
                  }

                  ++var15;
               }
            } else {
               if (-1 == var4) {
                  var10000 = true;
                  break;
               }

               if (Character.isWhitespace(var4)) {
                  var10000 = true;
                  break;
               }

               ++var15;
            }
         }

         Object var20 = null;
         Object var22 = null;
         if (!var10000) {
            errorFn.apply((new StringBuilder(20)).append("Unmatched quote [").append(qpos.last()).append("](").append(line.charAt(BoxesRunTime.unboxToInt(qpos.last()))).append(")").toString());
            return Nil$.MODULE$;
         }

         if (var15 > line.length()) {
            errorFn.apply("trailing backslash");
            return Nil$.MODULE$;
         }

         List var29 = (List)var14;
         String var10001;
         if (qpos.isEmpty()) {
            var10001 = line.substring(var16, var15);
         } else if (BoxesRunTime.unboxToInt(qpos.apply(0)) == var16 && BoxesRunTime.unboxToInt(qpos.apply(1)) == var15) {
            var10001 = line.substring(var16 + 1, var15 - 1);
         } else {
            StringBuilder loop$1_text$1_copyText$1_buf = new StringBuilder();
            int loop$1_text$1_copyText$1_p = var16;
            int loop$1_text$1_copyText$1_i = 0;

            while(loop$1_text$1_copyText$1_p < var15) {
               if (loop$1_text$1_copyText$1_i >= SeqOps.size$(qpos)) {
                  loop$1_text$1_copyText$1_buf.append(line, loop$1_text$1_copyText$1_p, var15);
                  loop$1_text$1_copyText$1_p = var15;
               } else if (loop$1_text$1_copyText$1_p == BoxesRunTime.unboxToInt(qpos.apply(loop$1_text$1_copyText$1_i))) {
                  loop$1_text$1_copyText$1_buf.append(line, BoxesRunTime.unboxToInt(qpos.apply(loop$1_text$1_copyText$1_i)) + 1, BoxesRunTime.unboxToInt(qpos.apply(loop$1_text$1_copyText$1_i + 1)));
                  loop$1_text$1_copyText$1_p = BoxesRunTime.unboxToInt(qpos.apply(loop$1_text$1_copyText$1_i + 1)) + 1;
                  loop$1_text$1_copyText$1_i += 2;
               } else {
                  loop$1_text$1_copyText$1_buf.append(line, loop$1_text$1_copyText$1_p, BoxesRunTime.unboxToInt(qpos.apply(loop$1_text$1_copyText$1_i)));
                  loop$1_text$1_copyText$1_p = BoxesRunTime.unboxToInt(qpos.apply(loop$1_text$1_copyText$1_i));
               }
            }

            var10001 = loop$1_text$1_copyText$1_buf.toString();
            Object var24 = null;
         }

         String loop$1_text$1_res = var10001;
         qpos.clear();
         Object var23 = null;
         Object var25 = null;
         List loop$1_$colon$colon_this = var29;
         $colon$colon var30 = new $colon$colon(loop$1_text$1_res, loop$1_$colon$colon_this);
         loop$1_$colon$colon_this = null;
         Object var27 = null;
         var14 = var30;
      }
   }

   public List tokenize(final String line) {
      Object var14 = Nil$.MODULE$;
      int var15 = 0;
      ArrayBuffer tokenize_qpos = new ArrayBuffer(16);

      while(true) {
         while(Character.isWhitespace(var15 >= line.length() ? -1 : line.charAt(var15))) {
            ++var15;
         }

         int var16 = var15;
         if (var15 >= line.length()) {
            return ((List)var14).reverse();
         }

         boolean var17 = false;

         boolean var10000;
         label146:
         while(true) {
            int var3 = var15 >= line.length() ? -1 : line.charAt(var15);
            if (var17) {
               var17 = false;
               ++var15;
            } else if (92 == var3) {
               var17 = true;
               ++var15;
            } else if (34 == var3 ? true : 39 == var3) {
               Object tokenize_loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem = var15;
               tokenize_qpos.addOne(tokenize_loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem);
               tokenize_loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem = null;
               ++var15;
               boolean var18 = false;

               while(true) {
                  int var5 = var15 >= line.length() ? -1 : line.charAt(var15);
                  if (var18) {
                     var18 = false;
                     var10000 = false;
                  } else if (92 == var5) {
                     var18 = true;
                     var10000 = false;
                  } else {
                     var10000 = var3 == var5 ? true : -1 == var5;
                  }

                  if (var10000) {
                     if (var15 >= line.length()) {
                        var10000 = false;
                        break label146;
                     }

                     Object tokenize_loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem = var15;
                     tokenize_qpos.addOne(tokenize_loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem);
                     tokenize_loop$1_skipToDelim$1_advance$1_quote$1_$plus$eq_elem = null;
                     ++var15;
                     break;
                  }

                  ++var15;
               }
            } else {
               if (-1 == var3) {
                  var10000 = true;
                  break;
               }

               if (Character.isWhitespace(var3)) {
                  var10000 = true;
                  break;
               }

               ++var15;
            }
         }

         Object var20 = null;
         Object var22 = null;
         if (!var10000) {
            String var28 = (new StringBuilder(20)).append("Unmatched quote [").append(tokenize_qpos.last()).append("](").append(line.charAt(BoxesRunTime.unboxToInt(tokenize_qpos.last()))).append(")").toString();
            throw new Parser.ParseException(var28);
         }

         if (var15 > line.length()) {
            String var13 = "trailing backslash";
            throw new Parser.ParseException(var13);
         }

         List var30 = (List)var14;
         String var10001;
         if (tokenize_qpos.isEmpty()) {
            var10001 = line.substring(var16, var15);
         } else if (BoxesRunTime.unboxToInt(tokenize_qpos.apply(0)) == var16 && BoxesRunTime.unboxToInt(tokenize_qpos.apply(1)) == var15) {
            var10001 = line.substring(var16 + 1, var15 - 1);
         } else {
            StringBuilder tokenize_loop$1_text$1_copyText$1_buf = new StringBuilder();
            int tokenize_loop$1_text$1_copyText$1_p = var16;
            int tokenize_loop$1_text$1_copyText$1_i = 0;

            while(tokenize_loop$1_text$1_copyText$1_p < var15) {
               if (tokenize_loop$1_text$1_copyText$1_i >= SeqOps.size$(tokenize_qpos)) {
                  tokenize_loop$1_text$1_copyText$1_buf.append(line, tokenize_loop$1_text$1_copyText$1_p, var15);
                  tokenize_loop$1_text$1_copyText$1_p = var15;
               } else if (tokenize_loop$1_text$1_copyText$1_p == BoxesRunTime.unboxToInt(tokenize_qpos.apply(tokenize_loop$1_text$1_copyText$1_i))) {
                  tokenize_loop$1_text$1_copyText$1_buf.append(line, BoxesRunTime.unboxToInt(tokenize_qpos.apply(tokenize_loop$1_text$1_copyText$1_i)) + 1, BoxesRunTime.unboxToInt(tokenize_qpos.apply(tokenize_loop$1_text$1_copyText$1_i + 1)));
                  tokenize_loop$1_text$1_copyText$1_p = BoxesRunTime.unboxToInt(tokenize_qpos.apply(tokenize_loop$1_text$1_copyText$1_i + 1)) + 1;
                  tokenize_loop$1_text$1_copyText$1_i += 2;
               } else {
                  tokenize_loop$1_text$1_copyText$1_buf.append(line, tokenize_loop$1_text$1_copyText$1_p, BoxesRunTime.unboxToInt(tokenize_qpos.apply(tokenize_loop$1_text$1_copyText$1_i)));
                  tokenize_loop$1_text$1_copyText$1_p = BoxesRunTime.unboxToInt(tokenize_qpos.apply(tokenize_loop$1_text$1_copyText$1_i));
               }
            }

            var10001 = tokenize_loop$1_text$1_copyText$1_buf.toString();
            Object var24 = null;
         }

         String tokenize_loop$1_text$1_res = var10001;
         tokenize_qpos.clear();
         Object var23 = null;
         Object var25 = null;
         List tokenize_loop$1_$colon$colon_this = var30;
         $colon$colon var31 = new $colon$colon(tokenize_loop$1_text$1_res, tokenize_loop$1_$colon$colon_this);
         tokenize_loop$1_$colon$colon_this = null;
         Object var27 = null;
         var14 = var31;
      }
   }

   private static final int cur$1(final String line$1, final IntRef pos$1) {
      return pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem);
   }

   private static final void bump$1(final IntRef pos$1) {
      ++pos$1.elem;
   }

   private static final boolean done$1(final IntRef pos$1, final String line$1) {
      return pos$1.elem >= line$1.length();
   }

   private static final boolean terminal$1(final BooleanRef escaped$1, final int q$1, final String line$1, final IntRef pos$1) {
      int var4 = pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem);
      if (escaped$1.elem) {
         escaped$1.elem = false;
         return false;
      } else if (92 == var4) {
         escaped$1.elem = true;
         return false;
      } else {
         return q$1 == var4 ? true : -1 == var4;
      }
   }

   private static final boolean skipToQuote$1(final int q, final String line$1, final IntRef pos$1) {
      boolean var4 = false;

      while(true) {
         int var3 = pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem);
         boolean var10000;
         if (var4) {
            var4 = false;
            var10000 = false;
         } else if (92 == var3) {
            var4 = true;
            var10000 = false;
         } else {
            var10000 = q == var3 ? true : -1 == var3;
         }

         if (var10000) {
            if (pos$1.elem < line$1.length()) {
               return true;
            }

            return false;
         }

         ++pos$1.elem;
      }
   }

   private static final void quote$1(final ArrayBuffer qpos$1, final IntRef pos$1) {
      Object $plus$eq_elem = pos$1.elem;
      qpos$1.addOne($plus$eq_elem);
      ++pos$1.elem;
   }

   private final boolean advance$1(final BooleanRef escaped$2, final String line$1, final IntRef pos$1, final ArrayBuffer qpos$1) {
      while(true) {
         int var5 = pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem);
         if (escaped$2.elem) {
            escaped$2.elem = false;
            ++pos$1.elem;
         } else if (92 == var5) {
            escaped$2.elem = true;
            ++pos$1.elem;
         } else if (34 == var5 ? true : 39 == var5) {
            Object quote$1_$plus$eq_elem = pos$1.elem;
            if (qpos$1 == null) {
               throw null;
            }

            qpos$1.addOne(quote$1_$plus$eq_elem);
            quote$1_$plus$eq_elem = null;
            ++pos$1.elem;
            boolean var9 = false;

            while(true) {
               int var7 = pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem);
               boolean var10000;
               if (var9) {
                  var9 = false;
                  var10000 = false;
               } else if (92 == var7) {
                  var9 = true;
                  var10000 = false;
               } else {
                  var10000 = var5 == var7 ? true : -1 == var7;
               }

               if (var10000) {
                  if (pos$1.elem < line$1.length()) {
                     Object quote$1_$plus$eq_elem = pos$1.elem;
                     qpos$1.addOne(quote$1_$plus$eq_elem);
                     quote$1_$plus$eq_elem = null;
                     ++pos$1.elem;
                     break;
                  }

                  return false;
               }

               ++pos$1.elem;
            }
         } else {
            if (-1 == var5) {
               return true;
            }

            if (Character.isWhitespace(var5)) {
               return true;
            }

            ++pos$1.elem;
         }
      }
   }

   private final boolean skipToDelim$1(final ArrayBuffer qpos$1, final IntRef pos$1, final String line$1) {
      boolean var8 = false;

      while(true) {
         int var4 = pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem);
         if (var8) {
            var8 = false;
            ++pos$1.elem;
         } else if (92 == var4) {
            var8 = true;
            ++pos$1.elem;
         } else if (34 == var4 ? true : 39 == var4) {
            Object advance$1_quote$1_$plus$eq_elem = pos$1.elem;
            if (qpos$1 == null) {
               throw null;
            }

            qpos$1.addOne(advance$1_quote$1_$plus$eq_elem);
            advance$1_quote$1_$plus$eq_elem = null;
            ++pos$1.elem;
            boolean var9 = false;

            while(true) {
               int var6 = pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem);
               boolean var10000;
               if (var9) {
                  var9 = false;
                  var10000 = false;
               } else if (92 == var6) {
                  var9 = true;
                  var10000 = false;
               } else {
                  var10000 = var4 == var6 ? true : -1 == var6;
               }

               if (var10000) {
                  if (pos$1.elem >= line$1.length()) {
                     return false;
                  }

                  Object advance$1_quote$1_$plus$eq_elem = pos$1.elem;
                  qpos$1.addOne(advance$1_quote$1_$plus$eq_elem);
                  advance$1_quote$1_$plus$eq_elem = null;
                  ++pos$1.elem;
                  break;
               }

               ++pos$1.elem;
            }
         } else {
            if (-1 == var4) {
               return true;
            }

            if (Character.isWhitespace(var4)) {
               return true;
            }

            ++pos$1.elem;
         }
      }
   }

   private static final void skipWhitespace$1(final String line$1, final IntRef pos$1) {
      while(Character.isWhitespace(pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem))) {
         ++pos$1.elem;
      }

   }

   private static final String copyText$1(final IntRef start$1, final IntRef pos$1, final ArrayBuffer qpos$1, final String line$1) {
      StringBuilder buf = new StringBuilder();
      int p = start$1.elem;
      int i = 0;

      while(p < pos$1.elem) {
         if (qpos$1 == null) {
            throw null;
         }

         if (i >= qpos$1.length()) {
            buf.append(line$1, p, pos$1.elem);
            p = pos$1.elem;
         } else if (p == BoxesRunTime.unboxToInt(qpos$1.apply(i))) {
            buf.append(line$1, BoxesRunTime.unboxToInt(qpos$1.apply(i)) + 1, BoxesRunTime.unboxToInt(qpos$1.apply(i + 1)));
            p = BoxesRunTime.unboxToInt(qpos$1.apply(i + 1)) + 1;
            i += 2;
         } else {
            buf.append(line$1, p, BoxesRunTime.unboxToInt(qpos$1.apply(i)));
            p = BoxesRunTime.unboxToInt(qpos$1.apply(i));
         }
      }

      return buf.toString();
   }

   private static final String text$1(final ArrayBuffer qpos$1, final String line$1, final IntRef start$1, final IntRef pos$1) {
      String var10000;
      if (qpos$1.isEmpty()) {
         var10000 = line$1.substring(start$1.elem, pos$1.elem);
      } else if (BoxesRunTime.unboxToInt(qpos$1.apply(0)) == start$1.elem && BoxesRunTime.unboxToInt(qpos$1.apply(1)) == pos$1.elem) {
         var10000 = line$1.substring(start$1.elem + 1, pos$1.elem - 1);
      } else {
         StringBuilder copyText$1_buf = new StringBuilder();
         int copyText$1_p = start$1.elem;
         int copyText$1_i = 0;

         while(copyText$1_p < pos$1.elem) {
            if (copyText$1_i >= qpos$1.length()) {
               copyText$1_buf.append(line$1, copyText$1_p, pos$1.elem);
               copyText$1_p = pos$1.elem;
            } else if (copyText$1_p == BoxesRunTime.unboxToInt(qpos$1.apply(copyText$1_i))) {
               copyText$1_buf.append(line$1, BoxesRunTime.unboxToInt(qpos$1.apply(copyText$1_i)) + 1, BoxesRunTime.unboxToInt(qpos$1.apply(copyText$1_i + 1)));
               copyText$1_p = BoxesRunTime.unboxToInt(qpos$1.apply(copyText$1_i + 1)) + 1;
               copyText$1_i += 2;
            } else {
               copyText$1_buf.append(line$1, copyText$1_p, BoxesRunTime.unboxToInt(qpos$1.apply(copyText$1_i)));
               copyText$1_p = BoxesRunTime.unboxToInt(qpos$1.apply(copyText$1_i));
            }
         }

         var10000 = copyText$1_buf.toString();
         Object var8 = null;
      }

      String res = var10000;
      qpos$1.clear();
      return res;
   }

   private static final void badquote$1(final Function1 errorFn$1, final ArrayBuffer qpos$1, final String line$1) {
      errorFn$1.apply((new StringBuilder(20)).append("Unmatched quote [").append(qpos$1.last()).append("](").append(line$1.charAt(BoxesRunTime.unboxToInt(qpos$1.last()))).append(")").toString());
   }

   private static final void badescape$1(final Function1 errorFn$1) {
      errorFn$1.apply("trailing backslash");
   }

   private final List loop$1(final IntRef start$1, final IntRef pos$1, final ObjectRef accum$1, final String line$1, final ArrayBuffer qpos$1, final Function1 errorFn$1) {
      while(true) {
         if (Character.isWhitespace(pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem))) {
            ++pos$1.elem;
         } else {
            start$1.elem = pos$1.elem;
            if (pos$1.elem >= line$1.length()) {
               return ((List)accum$1.elem).reverse();
            }

            boolean var17 = false;

            boolean var10000;
            label168:
            while(true) {
               int var7 = pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem);
               if (!var17) {
                  if (92 != var7) {
                     if (34 == var7 ? true : 39 == var7) {
                        Object skipToDelim$1_advance$1_quote$1_$plus$eq_elem = pos$1.elem;
                        if (qpos$1 == null) {
                           throw null;
                        }

                        qpos$1.addOne(skipToDelim$1_advance$1_quote$1_$plus$eq_elem);
                        skipToDelim$1_advance$1_quote$1_$plus$eq_elem = null;
                        ++pos$1.elem;
                        boolean var18 = false;

                        while(true) {
                           int var9 = pos$1.elem >= line$1.length() ? -1 : line$1.charAt(pos$1.elem);
                           if (var18) {
                              var18 = false;
                              var10000 = false;
                           } else if (92 == var9) {
                              var18 = true;
                              var10000 = false;
                           } else {
                              var10000 = var7 == var9 ? true : -1 == var9;
                           }

                           if (var10000) {
                              if (pos$1.elem < line$1.length()) {
                                 Object skipToDelim$1_advance$1_quote$1_$plus$eq_elem = pos$1.elem;
                                 qpos$1.addOne(skipToDelim$1_advance$1_quote$1_$plus$eq_elem);
                                 skipToDelim$1_advance$1_quote$1_$plus$eq_elem = null;
                                 ++pos$1.elem;
                                 break;
                              }

                              var10000 = false;
                              break label168;
                           }

                           ++pos$1.elem;
                        }
                     } else {
                        if (-1 == var7) {
                           var10000 = true;
                           break;
                        }

                        if (Character.isWhitespace(var7)) {
                           var10000 = true;
                           break;
                        }

                        ++pos$1.elem;
                     }
                  } else {
                     var17 = true;
                     ++pos$1.elem;
                  }
               } else {
                  var17 = false;
                  ++pos$1.elem;
               }
            }

            Object var20 = null;
            Object skipToDelim$1_advance$1_quote$1_$plus$eq_elem = null;
            if (!var10000) {
               errorFn$1.apply((new StringBuilder(20)).append("Unmatched quote [").append(qpos$1.last()).append("](").append(line$1.charAt(BoxesRunTime.unboxToInt(qpos$1.last()))).append(")").toString());
               return Nil$.MODULE$;
            }

            if (pos$1.elem > line$1.length()) {
               errorFn$1.apply("trailing backslash");
               return Nil$.MODULE$;
            }

            List var10001 = (List)accum$1.elem;
            String var10002;
            if (qpos$1.isEmpty()) {
               var10002 = line$1.substring(start$1.elem, pos$1.elem);
            } else if (BoxesRunTime.unboxToInt(qpos$1.apply(0)) == start$1.elem && BoxesRunTime.unboxToInt(qpos$1.apply(1)) == pos$1.elem) {
               var10002 = line$1.substring(start$1.elem + 1, pos$1.elem - 1);
            } else {
               StringBuilder text$1_copyText$1_buf = new StringBuilder();
               int text$1_copyText$1_p = start$1.elem;
               int text$1_copyText$1_i = 0;

               while(text$1_copyText$1_p < pos$1.elem) {
                  if (text$1_copyText$1_i >= SeqOps.size$(qpos$1)) {
                     text$1_copyText$1_buf.append(line$1, text$1_copyText$1_p, pos$1.elem);
                     text$1_copyText$1_p = pos$1.elem;
                  } else if (text$1_copyText$1_p == BoxesRunTime.unboxToInt(qpos$1.apply(text$1_copyText$1_i))) {
                     text$1_copyText$1_buf.append(line$1, BoxesRunTime.unboxToInt(qpos$1.apply(text$1_copyText$1_i)) + 1, BoxesRunTime.unboxToInt(qpos$1.apply(text$1_copyText$1_i + 1)));
                     text$1_copyText$1_p = BoxesRunTime.unboxToInt(qpos$1.apply(text$1_copyText$1_i + 1)) + 1;
                     text$1_copyText$1_i += 2;
                  } else {
                     text$1_copyText$1_buf.append(line$1, text$1_copyText$1_p, BoxesRunTime.unboxToInt(qpos$1.apply(text$1_copyText$1_i)));
                     text$1_copyText$1_p = BoxesRunTime.unboxToInt(qpos$1.apply(text$1_copyText$1_i));
                  }
               }

               var10002 = text$1_copyText$1_buf.toString();
               Object var24 = null;
            }

            String text$1_res = var10002;
            qpos$1.clear();
            Object var23 = null;
            Object var25 = null;
            if (var10001 == null) {
               throw null;
            }

            List $colon$colon_this = var10001;
            $colon$colon var29 = new $colon$colon(text$1_res, $colon$colon_this);
            $colon$colon_this = null;
            Object var27 = null;
            accum$1.elem = var29;
         }
      }
   }

   // $FF: synthetic method
   public static final Nothing$ $anonfun$tokenize$1(final String x) {
      throw new Parser.ParseException(x);
   }

   private Parser$() {
   }
}
