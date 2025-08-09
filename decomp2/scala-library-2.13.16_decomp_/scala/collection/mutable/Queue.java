package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.collection.View;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=g\u0001\u0002\u000e\u001c\u0001\tB\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006I!\u0014\u0005\t'\u0002\u0011\t\u0011)A\u0005)\"Aq\u000b\u0001B\u0001B\u0003%A\u000bC\u0003Y\u0001\u0011E\u0011\fC\u0003Y\u0001\u0011\u0005Q\fC\u0003a\u0001\u0011\u0005\u0013\r\u0003\u0004f\u0001\u0001&\tF\u001a\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006_\u0002!\t\u0001\u001e\u0005\u0006}\u0002!\ta \u0005\b\u0003\u0013\u0001A\u0011AA\u0006\u0011\u001d\ti\u0001\u0001C\u0001\u0003\u001fAq!a\n\u0001\t\u0003\tI\u0003C\u0004\u0002:\u0001!\t!a\u000f\t\u000f\u0005\u0015\u0003\u0001\"\u0002\u0002H!9\u0011\u0011\u000b\u0001\u0005R\u0005M\u0003bBA+\u0001\u0011E\u0013qK\u0004\b\u0003;Z\u0002\u0012AA0\r\u0019Q2\u0004#\u0001\u0002b!1\u0001l\u0005C\u0001\u0003SBq!a\u001b\u0014\t\u0003\ti\u0007C\u0004\u0002~M!\t!a \t\u000f\u0005%5\u0003\"\u0001\u0002\f\"I\u00111T\n\u0012\u0002\u0013\u0005\u0011Q\u0014\u0005\n\u0003o\u001b\u0012\u0011!C\u0005\u0003s\u0013Q!U;fk\u0016T!\u0001H\u000f\u0002\u000f5,H/\u00192mK*\u0011adH\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U\u00111EK\n\t\u0001\u0011\"\u0014(\u0010!D\rB\u0019QE\n\u0015\u000e\u0003mI!aJ\u000e\u0003\u0015\u0005\u0013(/Y=EKF,X\r\u0005\u0002*U1\u0001A!B\u0016\u0001\u0005\u0004a#!A!\u0012\u00055\n\u0004C\u0001\u00180\u001b\u0005y\u0012B\u0001\u0019 \u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\f\u001a\n\u0005Mz\"aA!osB)Q%\u000e\u00158q%\u0011ag\u0007\u0002\u000e\u0013:$W\r_3e'\u0016\fx\n]:\u0011\u0005\u0015\u0002\u0001cA\u0013\u0001QA)!h\u000f\u00158q5\tQ$\u0003\u0002=;\t)2\u000b\u001e:jGR|\u0005\u000f^5nSj,GmU3r\u001fB\u001c\b\u0003\u0002\u001e?Q]J!aP\u000f\u0003/%#XM]1cY\u00164\u0015m\u0019;pef$UMZ1vYR\u001c\b#B\u0013BQ]B\u0014B\u0001\"\u001c\u00055\t%O]1z\t\u0016\fX/Z(qgB\u0019Q\u0005\u0012\u001d\n\u0005\u0015[\"!C\"m_:,\u0017M\u00197f!\t9%*D\u0001I\u0015\tIU$A\u0004hK:,'/[2\n\u0005-C%a\u0005#fM\u0006,H\u000e^*fe&\fG.\u001b>bE2,\u0017!B1se\u0006L\bc\u0001\u0018O!&\u0011qj\b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003]EK!AU\u0010\u0003\r\u0005s\u0017PU3g\u0003\u0015\u0019H/\u0019:u!\tqS+\u0003\u0002W?\t\u0019\u0011J\u001c;\u0002\u0007\u0015tG-\u0001\u0004=S:LGO\u0010\u000b\u0005qi[F\fC\u0003M\t\u0001\u0007Q\nC\u0003T\t\u0001\u0007A\u000bC\u0003X\t\u0001\u0007A\u000b\u0006\u00029=\"9q,\u0002I\u0001\u0002\u0004!\u0016aC5oSRL\u0017\r\\*ju\u0016\fq\"\u001b;fe\u0006\u0014G.\u001a$bGR|'/_\u000b\u0002EB\u0019!hY\u001c\n\u0005\u0011l\"AC*fc\u001a\u000b7\r^8ss\u0006a1\u000f\u001e:j]\u001e\u0004&/\u001a4jqV\tq\r\u0005\u0002i[6\t\u0011N\u0003\u0002kW\u0006!A.\u00198h\u0015\u0005a\u0017\u0001\u00026bm\u0006L!A\\5\u0003\rM#(/\u001b8h\u0003\u001d)g.];fk\u0016$\"!\u001d:\u000e\u0003\u0001AQa\u001d\u0005A\u0002!\nA!\u001a7f[R!\u0011/^<z\u0011\u00151\u0018\u00021\u0001)\u0003\u0015)G.Z72\u0011\u0015A\u0018\u00021\u0001)\u0003\u0015)G.Z73\u0011\u0015Q\u0018\u00021\u0001|\u0003\u0015)G.Z7t!\rqC\u0010K\u0005\u0003{~\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?\u0003))g.];fk\u0016\fE\u000e\u001c\u000b\u0004c\u0006\u0005\u0001B\u0002>\u000b\u0001\u0004\t\u0019\u0001\u0005\u0003;\u0003\u000bA\u0013bAA\u0004;\ta\u0011\n^3sC\ndWm\u00148dK\u00069A-Z9vKV,G#\u0001\u0015\u0002\u0019\u0011,\u0017/^3vK\u001aK'o\u001d;\u0015\t\u0005E\u0011q\u0003\t\u0005]\u0005M\u0001&C\u0002\u0002\u0016}\u0011aa\u00149uS>t\u0007bBA\r\u0019\u0001\u0007\u00111D\u0001\u0002aB1a&!\b)\u0003CI1!a\b \u0005%1UO\\2uS>t\u0017\u0007E\u0002/\u0003GI1!!\n \u0005\u001d\u0011un\u001c7fC:\f!\u0002Z3rk\u0016,X-\u00117m)\u0011\tY#a\u000e\u0011\u000b\u00055\u00121\u0007\u0015\u000e\u0005\u0005=\"bAA\u0019;\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0003k\tyCA\u0002TKFDq!!\u0007\u000e\u0001\u0004\tY\"\u0001\u0007eKF,X-^3XQ&dW\r\u0006\u0003\u0002>\u0005\u0005\u0003\u0003\u0002\u001e\u0002@!J1!!\u000e\u001e\u0011\u001d\t\u0019E\u0004a\u0001\u00037\t\u0011AZ\u0001\u0006MJ|g\u000e^\u000b\u0002Q!\u001aq\"a\u0013\u0011\u00079\ni%C\u0002\u0002P}\u0011a!\u001b8mS:,\u0017!B6m_:,G#\u0001\u001d\u0002\u000f=4\u0017I\u001d:bsR)\u0001(!\u0017\u0002\\!)A*\u0005a\u0001\u001b\")q+\u0005a\u0001)\u0006)\u0011+^3vKB\u0011QeE\n\u0005'A\u000b\u0019\u0007\u0005\u0003;\u0003K:\u0014bAA4;\tI2\u000b\u001e:jGR|\u0005\u000f^5nSj,GmU3r\r\u0006\u001cGo\u001c:z)\t\ty&\u0001\u0003ge>lW\u0003BA8\u0003k\"B!!\u001d\u0002xA!Q\u0005AA:!\rI\u0013Q\u000f\u0003\u0006WU\u0011\r\u0001\f\u0005\b\u0003s*\u0002\u0019AA>\u0003\u0019\u0019x.\u001e:dKB)!(!\u0002\u0002t\u0005)Q-\u001c9usV!\u0011\u0011QAD+\t\t\u0019\t\u0005\u0003&\u0001\u0005\u0015\u0005cA\u0015\u0002\b\u0012)1F\u0006b\u0001Y\u0005Qa.Z<Ck&dG-\u001a:\u0016\t\u00055\u0015qS\u000b\u0003\u0003\u001f\u0003r!JAI\u0003+\u000bI*C\u0002\u0002\u0014n\u0011qAQ;jY\u0012,'\u000fE\u0002*\u0003/#QaK\fC\u00021\u0002B!\n\u0001\u0002\u0016\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE*B!a(\u00026V\u0011\u0011\u0011\u0015\u0016\u0004)\u0006\r6FAAS!\u0011\t9+!-\u000e\u0005\u0005%&\u0002BAV\u0003[\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005=v$\u0001\u0006b]:|G/\u0019;j_:LA!a-\u0002*\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b-B\"\u0019\u0001\u0017\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005m\u0006c\u00015\u0002>&\u0019\u0011qX5\u0003\r=\u0013'.Z2uQ\u001d\u0019\u00121YAe\u0003\u0017\u00042ALAc\u0013\r\t9m\b\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012a\u0001\u0015\b%\u0005\r\u0017\u0011ZAf\u0001"
)
public class Queue extends ArrayDeque {
   public static int $lessinit$greater$default$1() {
      Queue$ var10000 = Queue$.MODULE$;
      return 16;
   }

   public static Builder newBuilder() {
      return Queue$.MODULE$.newBuilder();
   }

   public static Queue from(final IterableOnce source) {
      return Queue$.MODULE$.from(source);
   }

   public static scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      Builder tabulate_b = Queue$.MODULE$.newBuilder();
      tabulate_b.sizeHint(n);

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         Object tabulate_$plus$eq_elem = f.apply(tabulate_i);
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static scala.collection.SeqOps fill(final int n, final Function0 elem) {
      Builder fill_b = Queue$.MODULE$.newBuilder();
      fill_b.sizeHint(n);

      for(int fill_i = 0; fill_i < n; ++fill_i) {
         Object fill_$plus$eq_elem = elem.apply();
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      Queue$ var10000 = Queue$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      Queue$ tabulate_this = Queue$.MODULE$;
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
      Queue$ tabulate_this = Queue$.MODULE$;
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
      Queue$ tabulate_this = Queue$.MODULE$;
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
      Queue$ tabulate_this = Queue$.MODULE$;
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
      Queue$ fill_this = Queue$.MODULE$;
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
      Queue$ fill_this = Queue$.MODULE$;
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
      Queue$ fill_this = Queue$.MODULE$;
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
      Queue$ fill_this = Queue$.MODULE$;
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
      return IterableFactory.range$(Queue$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Queue$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      Queue$ unfold_this = Queue$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      Queue$ iterate_this = Queue$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   public SeqFactory iterableFactory() {
      return Queue$.MODULE$;
   }

   public String stringPrefix() {
      return "Queue";
   }

   public Queue enqueue(final Object elem) {
      return (Queue)this.addOne(elem);
   }

   public Queue enqueue(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return this.enqueue(elem1).enqueue(elem2).enqueueAll(elems);
   }

   public Queue enqueueAll(final IterableOnce elems) {
      return (Queue)this.addAll(elems);
   }

   public Object dequeue() {
      return this.removeHead(this.removeHead$default$1());
   }

   public Option dequeueFirst(final Function1 p) {
      return this.removeFirst(p, this.removeFirst$default$2());
   }

   public scala.collection.immutable.Seq dequeueAll(final Function1 p) {
      return this.removeAll(p);
   }

   public scala.collection.Seq dequeueWhile(final Function1 f) {
      return this.removeHeadWhile(f);
   }

   public final Object front() {
      return this.head();
   }

   public Queue klone() {
      Builder bf = this.newSpecificBuilder();
      if (bf == null) {
         throw null;
      } else {
         bf.addAll(this);
         return (Queue)bf.result();
      }
   }

   public Queue ofArray(final Object[] array, final int end) {
      return new Queue(array, 0, end);
   }

   public Queue(final Object[] array, final int start, final int end) {
      super(array, start, end);
   }

   public Queue(final int initialSize) {
      this(ArrayDeque$.MODULE$.alloc(initialSize), 0, 0);
   }
}
