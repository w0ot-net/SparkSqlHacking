package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.collection.View;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%g\u0001B\r\u001b\u0001\u0005B\u0001b\u0013\u0001\u0003\u0002\u0003\u0006I\u0001\u0014\u0005\t%\u0002\u0011\t\u0011)A\u0005'\"Aa\u000b\u0001B\u0001B\u0003%1\u000bC\u0003X\u0001\u0011E\u0001\fC\u0003X\u0001\u0011\u0005A\fC\u0003`\u0001\u0011\u0005\u0003\r\u0003\u0004e\u0001\u0001&\t&\u001a\u0005\u0006]\u0002!\ta\u001c\u0005\u0006]\u0002!\ta\u001d\u0005\u0006{\u0002!\tA \u0005\b\u0003\u000f\u0001A\u0011AA\u0005\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bAq!!\u0006\u0001\t\u0003\t9\u0002C\u0004\u0002*\u0001!)!a\u000b\t\u000f\u0005U\u0002\u0001\"\u0015\u00028!9\u0011\u0011\b\u0001\u0005R\u0005mraBA.5!\u0005\u0011Q\f\u0004\u00073iA\t!a\u0018\t\r]\u0013B\u0011AA4\u0011\u001d\tIG\u0005C\u0001\u0003WBq!a\u001f\u0013\t\u0003\ti\bC\u0004\u0002\bJ!\t!!#\t\u0013\u0005e%#%A\u0005\u0002\u0005m\u0005\"CAY%\u0005\u0005I\u0011BAZ\u0005\u0015\u0019F/Y2l\u0015\tYB$A\u0004nkR\f'\r\\3\u000b\u0005uq\u0012AC2pY2,7\r^5p]*\tq$A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005\tJ3\u0003\u0003\u0001$gabtHQ#\u0011\u0007\u0011*s%D\u0001\u001b\u0013\t1#D\u0001\u0006BeJ\f\u0017\u0010R3rk\u0016\u0004\"\u0001K\u0015\r\u0001\u0011)!\u0006\u0001b\u0001W\t\t\u0011)\u0005\u0002-aA\u0011QFL\u0007\u0002=%\u0011qF\b\u0002\b\u001d>$\b.\u001b8h!\ti\u0013'\u0003\u00023=\t\u0019\u0011I\\=\u0011\u000b\u0011\"tEN\u001c\n\u0005UR\"!D%oI\u0016DX\rZ*fc>\u00038\u000f\u0005\u0002%\u0001A\u0019A\u0005A\u0014\u0011\u000beRtEN\u001c\u000e\u0003qI!a\u000f\u000f\u0003+M#(/[2u\u001fB$\u0018.\\5{K\u0012\u001cV-](qgB!\u0011(P\u00147\u0013\tqDDA\fJi\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z\t\u00164\u0017-\u001e7ugB)A\u0005Q\u00147o%\u0011\u0011I\u0007\u0002\u000e\u0003J\u0014\u0018-\u001f#fcV,w\n]:\u0011\u0007\u0011\u001au'\u0003\u0002E5\tI1\t\\8oK\u0006\u0014G.\u001a\t\u0003\r&k\u0011a\u0012\u0006\u0003\u0011r\tqaZ3oKJL7-\u0003\u0002K\u000f\n\u0019B)\u001a4bk2$8+\u001a:jC2L'0\u00192mK\u0006)\u0011M\u001d:bsB\u0019Q&T(\n\u00059s\"!B!se\u0006L\bCA\u0017Q\u0013\t\tfD\u0001\u0004B]f\u0014VMZ\u0001\u0006gR\f'\u000f\u001e\t\u0003[QK!!\u0016\u0010\u0003\u0007%sG/A\u0002f]\u0012\fa\u0001P5oSRtD\u0003B\u001cZ5nCQa\u0013\u0003A\u00021CQA\u0015\u0003A\u0002MCQA\u0016\u0003A\u0002M#\"aN/\t\u000fy+\u0001\u0013!a\u0001'\u0006Y\u0011N\\5uS\u0006d7+\u001b>f\u0003=IG/\u001a:bE2,g)Y2u_JLX#A1\u0011\u0007e\u0012g'\u0003\u0002d9\tQ1+Z9GC\u000e$xN]=\u0002\u0019M$(/\u001b8h!J,g-\u001b=\u0016\u0003\u0019\u0004\"a\u001a7\u000e\u0003!T!!\u001b6\u0002\t1\fgn\u001a\u0006\u0002W\u0006!!.\u0019<b\u0013\ti\u0007N\u0001\u0004TiJLgnZ\u0001\u0005aV\u001c\b\u000e\u0006\u0002qc6\t\u0001\u0001C\u0003s\u0011\u0001\u0007q%\u0001\u0003fY\u0016lG\u0003\u00029umbDQ!^\u0005A\u0002\u001d\nQ!\u001a7f[FBQa^\u0005A\u0002\u001d\nQ!\u001a7f[JBQ!_\u0005A\u0002i\fQ!\u001a7f[N\u00042!L>(\u0013\tahD\u0001\u0006=e\u0016\u0004X-\u0019;fIz\nq\u0001];tQ\u0006cG\u000e\u0006\u0002q\u007f\"1\u0011P\u0003a\u0001\u0003\u0003\u0001B!OA\u0002O%\u0019\u0011Q\u0001\u000f\u0003\u0019%#XM]1cY\u0016|enY3\u0002\u0007A|\u0007\u000fF\u0001(\u0003\u0019\u0001x\u000e]!mYR\u0011\u0011q\u0002\t\u0005s\u0005Eq%C\u0002\u0002\u0014q\u00111aU3r\u0003!\u0001x\u000e],iS2,G\u0003BA\b\u00033Aq!a\u0007\u000e\u0001\u0004\ti\"A\u0001g!\u0019i\u0013qD\u0014\u0002$%\u0019\u0011\u0011\u0005\u0010\u0003\u0013\u0019+hn\u0019;j_:\f\u0004cA\u0017\u0002&%\u0019\u0011q\u0005\u0010\u0003\u000f\t{w\u000e\\3b]\u0006\u0019Ao\u001c9\u0016\u0003\u001dB3ADA\u0018!\ri\u0013\u0011G\u0005\u0004\u0003gq\"AB5oY&tW-A\u0003lY>tW\rF\u00018\u0003\u001dyg-\u0011:sCf$RaNA\u001f\u0003\u007fAQa\u0013\tA\u00021CQA\u0016\tA\u0002MC3\u0002AA\"\u0003\u001f\n\t&!\u0016\u0002XA!\u0011QIA&\u001b\t\t9EC\u0002\u0002Jy\t!\"\u00198o_R\fG/[8o\u0013\u0011\ti%a\u0012\u0003\u00135LwM]1uS>t\u0017aB7fgN\fw-Z\u0011\u0003\u0003'\nAh\u0015;bG.\u0004\u0013n\u001d\u0011o_^\u0004#-Y:fI\u0002zg\u000eI1oA\u0005\u0013(/Y=EKF,X\rI5ogR,\u0017\r\u001a\u0011pM\u0002\n\u0007\u0005\\5oW\u0016$\u0007\u0005\\5ti\u0006I1\r[1oO\u0016$\u0017J\\\u0011\u0003\u00033\naA\r\u00182g9\u0002\u0014!B*uC\u000e\\\u0007C\u0001\u0013\u0013'\u0011\u0011r*!\u0019\u0011\te\n\u0019GN\u0005\u0004\u0003Kb\"!G*ue&\u001cGo\u00149uS6L'0\u001a3TKF4\u0015m\u0019;pef$\"!!\u0018\u0002\t\u0019\u0014x.\\\u000b\u0005\u0003[\n\u0019\b\u0006\u0003\u0002p\u0005U\u0004\u0003\u0002\u0013\u0001\u0003c\u00022\u0001KA:\t\u0015QCC1\u0001,\u0011\u001d\t9\b\u0006a\u0001\u0003s\naa]8ve\u000e,\u0007#B\u001d\u0002\u0004\u0005E\u0014!B3naRLX\u0003BA@\u0003\u000b+\"!!!\u0011\t\u0011\u0002\u00111\u0011\t\u0004Q\u0005\u0015E!\u0002\u0016\u0016\u0005\u0004Y\u0013A\u00038fo\n+\u0018\u000e\u001c3feV!\u00111RAK+\t\ti\tE\u0004%\u0003\u001f\u000b\u0019*a&\n\u0007\u0005E%DA\u0004Ck&dG-\u001a:\u0011\u0007!\n)\nB\u0003+-\t\u00071\u0006\u0005\u0003%\u0001\u0005M\u0015a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'\u0006\u0003\u0002\u001e\u0006=VCAAPU\r\u0019\u0016\u0011U\u0016\u0003\u0003G\u0003B!!*\u0002,6\u0011\u0011q\u0015\u0006\u0005\u0003S\u000b9%A\u0005v]\u000eDWmY6fI&!\u0011QVAT\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006U]\u0011\raK\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003k\u00032aZA\\\u0013\r\tI\f\u001b\u0002\u0007\u001f\nTWm\u0019;)\u000fI\ti,a1\u0002FB\u0019Q&a0\n\u0007\u0005\u0005gD\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t1\u0001K\u0004\u0012\u0003{\u000b\u0019-!2"
)
public class Stack extends ArrayDeque {
   public static int $lessinit$greater$default$1() {
      Stack$ var10000 = Stack$.MODULE$;
      return 16;
   }

   public static Builder newBuilder() {
      return Stack$.MODULE$.newBuilder();
   }

   public static Stack from(final IterableOnce source) {
      return Stack$.MODULE$.from(source);
   }

   public static scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      Builder tabulate_b = Stack$.MODULE$.newBuilder();
      tabulate_b.sizeHint(n);

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         Object tabulate_$plus$eq_elem = f.apply(tabulate_i);
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static scala.collection.SeqOps fill(final int n, final Function0 elem) {
      Builder fill_b = Stack$.MODULE$.newBuilder();
      fill_b.sizeHint(n);

      for(int fill_i = 0; fill_i < n; ++fill_i) {
         Object fill_$plus$eq_elem = elem.apply();
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      Stack$ var10000 = Stack$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      Stack$ tabulate_this = Stack$.MODULE$;
      Builder tabulate_b = tabulate_this.newBuilder();
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = tabulate_this.newBuilder();
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = tabulate_this.newBuilder();
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = tabulate_this.newBuilder();
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Builder tabulate_b = tabulate_this.newBuilder();
                  tabulate_b.sizeHint(n5);

                  for(int tabulate_i = 0; tabulate_i < n5; ++tabulate_i) {
                     Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                     tabulate_b.addOne(tabulate_$plus$eq_elem);
                     tabulate_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var10000 = (scala.collection.SeqOps)tabulate_b.result();
                  Object var32 = null;
                  Object var34 = null;
                  Object tabulate_$plus$eq_elem = var10000;
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var35 = (scala.collection.SeqOps)tabulate_b.result();
               Object var29 = null;
               Object var31 = null;
               Object tabulate_$plus$eq_elem = var35;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var36 = (scala.collection.SeqOps)tabulate_b.result();
            Object var26 = null;
            Object var28 = null;
            Object tabulate_$plus$eq_elem = var36;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var37 = (scala.collection.SeqOps)tabulate_b.result();
         Object var23 = null;
         Object var25 = null;
         Object tabulate_$plus$eq_elem = var37;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      Stack$ tabulate_this = Stack$.MODULE$;
      Builder tabulate_b = tabulate_this.newBuilder();
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = tabulate_this.newBuilder();
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = tabulate_this.newBuilder();
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = tabulate_this.newBuilder();
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var10000 = (scala.collection.SeqOps)tabulate_b.result();
               Object var25 = null;
               Object var27 = null;
               Object tabulate_$plus$eq_elem = var10000;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var28 = (scala.collection.SeqOps)tabulate_b.result();
            Object var22 = null;
            Object var24 = null;
            Object tabulate_$plus$eq_elem = var28;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var29 = (scala.collection.SeqOps)tabulate_b.result();
         Object var19 = null;
         Object var21 = null;
         Object tabulate_$plus$eq_elem = var29;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      Stack$ tabulate_this = Stack$.MODULE$;
      Builder tabulate_b = tabulate_this.newBuilder();
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = tabulate_this.newBuilder();
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = tabulate_this.newBuilder();
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i);
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var10000 = (scala.collection.SeqOps)tabulate_b.result();
            Object var18 = null;
            Object var20 = null;
            Object tabulate_$plus$eq_elem = var10000;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var21 = (scala.collection.SeqOps)tabulate_b.result();
         Object var15 = null;
         Object var17 = null;
         Object tabulate_$plus$eq_elem = var21;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      Stack$ tabulate_this = Stack$.MODULE$;
      Builder tabulate_b = tabulate_this.newBuilder();
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = tabulate_this.newBuilder();
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i);
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var10000 = (scala.collection.SeqOps)tabulate_b.result();
         Object var11 = null;
         Object var13 = null;
         Object tabulate_$plus$eq_elem = var10000;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      Stack$ fill_this = Stack$.MODULE$;
      Builder fill_b = fill_this.newBuilder();
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = fill_this.newBuilder();
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = fill_this.newBuilder();
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = fill_this.newBuilder();
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Builder fill_b = fill_this.newBuilder();
                  fill_b.sizeHint(n5);

                  for(int fill_i = 0; fill_i < n5; ++fill_i) {
                     Object fill_$plus$eq_elem = elem.apply();
                     fill_b.addOne(fill_$plus$eq_elem);
                     fill_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var10000 = (scala.collection.SeqOps)fill_b.result();
                  Object var32 = null;
                  Object var34 = null;
                  Object fill_$plus$eq_elem = var10000;
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var35 = (scala.collection.SeqOps)fill_b.result();
               Object var29 = null;
               Object var31 = null;
               Object fill_$plus$eq_elem = var35;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var36 = (scala.collection.SeqOps)fill_b.result();
            Object var26 = null;
            Object var28 = null;
            Object fill_$plus$eq_elem = var36;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var37 = (scala.collection.SeqOps)fill_b.result();
         Object var23 = null;
         Object var25 = null;
         Object fill_$plus$eq_elem = var37;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      Stack$ fill_this = Stack$.MODULE$;
      Builder fill_b = fill_this.newBuilder();
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = fill_this.newBuilder();
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = fill_this.newBuilder();
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = fill_this.newBuilder();
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Object fill_$plus$eq_elem = elem.apply();
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var10000 = (scala.collection.SeqOps)fill_b.result();
               Object var25 = null;
               Object var27 = null;
               Object fill_$plus$eq_elem = var10000;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var28 = (scala.collection.SeqOps)fill_b.result();
            Object var22 = null;
            Object var24 = null;
            Object fill_$plus$eq_elem = var28;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var29 = (scala.collection.SeqOps)fill_b.result();
         Object var19 = null;
         Object var21 = null;
         Object fill_$plus$eq_elem = var29;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      Stack$ fill_this = Stack$.MODULE$;
      Builder fill_b = fill_this.newBuilder();
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = fill_this.newBuilder();
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = fill_this.newBuilder();
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Object fill_$plus$eq_elem = elem.apply();
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var10000 = (scala.collection.SeqOps)fill_b.result();
            Object var18 = null;
            Object var20 = null;
            Object fill_$plus$eq_elem = var10000;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var21 = (scala.collection.SeqOps)fill_b.result();
         Object var15 = null;
         Object var17 = null;
         Object fill_$plus$eq_elem = var21;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      Stack$ fill_this = Stack$.MODULE$;
      Builder fill_b = fill_this.newBuilder();
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = fill_this.newBuilder();
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_$plus$eq_elem = elem.apply();
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var10000 = (scala.collection.SeqOps)fill_b.result();
         Object var11 = null;
         Object var13 = null;
         Object fill_$plus$eq_elem = var10000;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(Stack$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Stack$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      Stack$ unfold_this = Stack$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      Stack$ iterate_this = Stack$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   public SeqFactory iterableFactory() {
      return Stack$.MODULE$;
   }

   public String stringPrefix() {
      return "Stack";
   }

   public Stack push(final Object elem) {
      return (Stack)this.prepend(elem);
   }

   public Stack push(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      int k = elems.knownSize();
      this.ensureSize(this.length() + (k >= 0 ? k + 2 : 3));
      return ((Stack)this.prepend(elem1).prepend(elem2)).pushAll(elems);
   }

   public Stack pushAll(final IterableOnce elems) {
      return (Stack)this.prependAll((IterableOnce)(elems instanceof scala.collection.Seq ? ((scala.collection.Seq)elems).view().reverse() : ((scala.collection.IndexedSeqOps)IndexedSeq$.MODULE$.from(elems)).view().reverse()));
   }

   public Object pop() {
      return this.removeHead(this.removeHead$default$1());
   }

   public scala.collection.Seq popAll() {
      return this.removeAll();
   }

   public scala.collection.Seq popWhile(final Function1 f) {
      return this.removeHeadWhile(f);
   }

   public final Object top() {
      return this.head();
   }

   public Stack klone() {
      Builder bf = this.newSpecificBuilder();
      if (bf == null) {
         throw null;
      } else {
         bf.addAll(this);
         return (Stack)bf.result();
      }
   }

   public Stack ofArray(final Object[] array, final int end) {
      return new Stack(array, 0, end);
   }

   public Stack(final Object[] array, final int start, final int end) {
      super(array, start, end);
   }

   public Stack(final int initialSize) {
      this(ArrayDeque$.MODULE$.alloc(initialSize), 0, 0);
   }
}
