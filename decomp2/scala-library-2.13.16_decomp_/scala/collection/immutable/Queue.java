package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedLinearSeqOps;
import scala.collection.View;
import scala.collection.generic.DefaultSerializable;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015d\u0001\u0002\u0014(!9B\u0001\u0002\u0017\u0001\u0003\u0006\u0004%\t\"\u0017\u0005\t;\u0002\u0011\t\u0011)A\u00055\"Aa\f\u0001BC\u0002\u0013E\u0011\f\u0003\u0005`\u0001\t\u0005\t\u0015!\u0003[\u0011\u0015\u0001\u0007\u0001\"\u0005b\u0011\u0015!\u0007\u0001\"\u0011f\u0011\u0015I\u0007\u0001\"\u0011k\u0011\u0015\u0001\b\u0001\"\u0011r\u0011\u0015)\b\u0001\"\u0011w\u0011\u0015Q\b\u0001\"\u0011|\u0011\u0015a\b\u0001\"\u0011~\u0011\u0015q\b\u0001\"\u0011|\u0011\u0019y\b\u0001\"\u0011\u0002\u0002!9\u0011Q\u0002\u0001\u0005B\u0005=\u0001\u0002CA\n\u0001\u0001&\t&!\u0006\t\u000f\u0005\u001d\u0002\u0001\"\u0011\u0002*!9\u00111\u0006\u0001\u0005B\u00055\u0002bBA \u0001\u0011\u0005\u0013\u0011\t\u0005\b\u0003\u001b\u0002A\u0011IA(\u0011\u001d\t\u0019\u0007\u0001C\u0001\u0003KBq!a\u0019\u0001\t\u000b\t\t\bC\u0004\u0002\"\u0002!\t!a)\t\u000f\u0005E\u0006\u0001\"\u0001\u00024\"9\u00111\u0018\u0001\u0005\u0002\u0005u\u0006BBAc\u0001\u0011\u00051\u0010C\u0004\u0002H\u0002!\t%!3\b\u000f\t\u0005t\u0005#\u0001\u0002t\u001a1ae\nE\u0001\u0003GDa\u0001\u0019\u000f\u0005\u0002\u0005E\bbBA{9\u0011\u0005\u0011q\u001f\u0005\b\u0005\u001baB\u0011\u0001B\b\u0011\u001d\u0011y\u0002\bC\u0001\u0005CAa!\u001b\u000f\u0005B\t-ra\u0002B 9!%!\u0011\t\u0004\b\u0003Cd\u0002\u0012\u0002B.\u0011\u0019\u00017\u0005\"\u0001\u0003`!I!Q\t\u000f\u0002\u0002\u0013%!q\t\u0002\u0006#V,W/\u001a\u0006\u0003Q%\n\u0011\"[7nkR\f'\r\\3\u000b\u0005)Z\u0013AC2pY2,7\r^5p]*\tA&A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005=24\u0003\u0003\u00011\u0001\u000eCEj\u0014*\u0011\u0007E\u0012D'D\u0001(\u0013\t\u0019tEA\u0006BEN$(/Y2u'\u0016\f\bCA\u001b7\u0019\u0001!aa\u000e\u0001\u0005\u0006\u0004A$!A!\u0012\u0005ej\u0004C\u0001\u001e<\u001b\u0005Y\u0013B\u0001\u001f,\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u000f \n\u0005}Z#aA!osB\u0019\u0011'\u0011\u001b\n\u0005\t;#!\u0003'j]\u0016\f'oU3r!\u0015\tD\t\u000e$H\u0013\t)uE\u0001\u0007MS:,\u0017M]*fc>\u00038\u000f\u0005\u00022\u0001A\u0019\u0011\u0007\u0001\u001b\u0011\u000b%SEGR$\u000e\u0003%J!aS\u0015\u00037M#(/[2u\u001fB$\u0018.\\5{K\u0012d\u0015N\\3beN+\u0017o\u00149t!\u0015\tT\n\u000e$H\u0013\tquEA\u000bTiJL7\r^(qi&l\u0017N_3e'\u0016\fx\n]:\u0011\t%\u0003FGR\u0005\u0003#&\u0012q#\u0013;fe\u0006\u0014G.\u001a$bGR|'/\u001f#fM\u0006,H\u000e^:\u0011\u0005M3V\"\u0001+\u000b\u0005UK\u0013aB4f]\u0016\u0014\u0018nY\u0005\u0003/R\u00131\u0003R3gCVdGoU3sS\u0006d\u0017N_1cY\u0016\f!!\u001b8\u0016\u0003i\u00032!M.5\u0013\tavE\u0001\u0003MSN$\u0018aA5oA\u0005\u0019q.\u001e;\u0002\t=,H\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u001d\u00137\rC\u0003Y\u000b\u0001\u0007!\fC\u0003_\u000b\u0001\u0007!,A\bji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z+\u00051\u0007cA%h\r&\u0011\u0001.\u000b\u0002\u000b'\u0016\fh)Y2u_JL\u0018!B1qa2LHC\u0001\u001bl\u0011\u0015aw\u00011\u0001n\u0003\u0005q\u0007C\u0001\u001eo\u0013\ty7FA\u0002J]R\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0002eB\u0019\u0011j\u001d\u001b\n\u0005QL#\u0001C%uKJ\fGo\u001c:\u0002\u000f%\u001cX)\u001c9usV\tq\u000f\u0005\u0002;q&\u0011\u0011p\u000b\u0002\b\u0005>|G.Z1o\u0003\u0011AW-\u00193\u0016\u0003Q\nA\u0001^1jYV\tq)\u0001\u0003mCN$\u0018A\u00024pe\u0006dG\u000eF\u0002x\u0003\u0007Aq!!\u0002\u000e\u0001\u0004\t9!A\u0001q!\u0015Q\u0014\u0011\u0002\u001bx\u0013\r\tYa\u000b\u0002\n\rVt7\r^5p]F\na!\u001a=jgR\u001cHcA<\u0002\u0012!9\u0011Q\u0001\bA\u0002\u0005\u001d\u0011!C2mCN\u001ch*Y7f+\t\t9\u0002\u0005\u0003\u0002\u001a\u0005\rRBAA\u000e\u0015\u0011\ti\"a\b\u0002\t1\fgn\u001a\u0006\u0003\u0003C\tAA[1wC&!\u0011QEA\u000e\u0005\u0019\u0019FO]5oO\u00061A.\u001a8hi\",\u0012!\\\u0001\naJ,\u0007/\u001a8eK\u0012,B!a\f\u00026Q!\u0011\u0011GA\u001e!\u0011\t\u0004!a\r\u0011\u0007U\n)\u0004B\u0004\u00028E\u0011\r!!\u000f\u0003\u0003\t\u000b\"\u0001N\u001f\t\u000f\u0005u\u0012\u00031\u0001\u00024\u0005!Q\r\\3n\u0003!\t\u0007\u000f]3oI\u0016$W\u0003BA\"\u0003\u0013\"B!!\u0012\u0002LA!\u0011\u0007AA$!\r)\u0014\u0011\n\u0003\b\u0003o\u0011\"\u0019AA\u001d\u0011\u001d\tiD\u0005a\u0001\u0003\u000f\n1\"\u00199qK:$W\rZ!mYV!\u0011\u0011KA,)\u0011\t\u0019&!\u0017\u0011\tE\u0002\u0011Q\u000b\t\u0004k\u0005]CaBA\u001c'\t\u0007\u0011\u0011\b\u0005\b\u00037\u001a\u0002\u0019AA/\u0003\u0011!\b.\u0019;\u0011\u000b%\u000by&!\u0016\n\u0007\u0005\u0005\u0014F\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-A\u0004f]F,X-^3\u0016\t\u0005\u001d\u0014Q\u000e\u000b\u0005\u0003S\ny\u0007\u0005\u00032\u0001\u0005-\u0004cA\u001b\u0002n\u00119\u0011q\u0007\u000bC\u0002\u0005e\u0002bBA\u001f)\u0001\u0007\u00111N\u000b\u0005\u0003g\nI\b\u0006\u0003\u0002v\u0005m\u0004\u0003B\u0019\u0001\u0003o\u00022!NA=\t\u001d\t9$\u0006b\u0001\u0003sAq!! \u0016\u0001\u0004\ty(\u0001\u0003ji\u0016\u0014\b#B%\u0002\u0002\u0006]\u0014bAABS\tA\u0011\n^3sC\ndW\rK\u0006\u0016\u0003\u000f\u000bi)a$\u0002\u0014\u0006U\u0005c\u0001\u001e\u0002\n&\u0019\u00111R\u0016\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005E\u0015!S+tK\u0002\u0002WM\\9vKV,\u0017\t\u001c7aA%t7\u000f^3bI\u0002zg\r\t1f]F,X-^3aAQ|\u0007%\u001a8rk\u0016,X\rI1!G>dG.Z2uS>t\u0007e\u001c4!K2,W.\u001a8ug\u0006)1/\u001b8dK\u0006\u0012\u0011qS\u0001\u0007e9\n4G\f\u0019)\u0007U\tY\nE\u0002;\u0003;K1!a(,\u0005\u0019Ig\u000e\\5oK\u0006QQM\\9vKV,\u0017\t\u001c7\u0016\t\u0005\u0015\u00161\u0016\u000b\u0005\u0003O\u000bi\u000b\u0005\u00032\u0001\u0005%\u0006cA\u001b\u0002,\u00129\u0011q\u0007\fC\u0002\u0005e\u0002bBA?-\u0001\u0007\u0011q\u0016\t\u0006\u0013\u0006\u0005\u0015\u0011V\u0001\bI\u0016\fX/Z;f+\t\t)\fE\u0003;\u0003o#t)C\u0002\u0002:.\u0012a\u0001V;qY\u0016\u0014\u0014!\u00043fcV,W/Z(qi&|g.\u0006\u0002\u0002@B)!(!1\u00026&\u0019\u00111Y\u0016\u0003\r=\u0003H/[8o\u0003\u00151'o\u001c8u\u0003!!xn\u0015;sS:<GCAAf!\u0011\ti-a7\u000f\t\u0005=\u0017q\u001b\t\u0004\u0003#\\SBAAj\u0015\r\t).L\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005e7&\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003K\tiNC\u0002\u0002Z.J#\u0001A\u0012\u0003\u0015\u0015k\u0007\u000f^=Rk\u0016,XmE\u0003\u001d\u0003K\fY\u000fE\u0002;\u0003OL1!!;,\u0005\u0019\te.\u001f*fMB!\u0011*!<G\u0013\r\ty/\u000b\u0002\u001a'R\u0014\u0018n\u0019;PaRLW.\u001b>fIN+\u0017OR1di>\u0014\u0018\u0010\u0006\u0002\u0002tB\u0011\u0011\u0007H\u0001\u000b]\u0016<()^5mI\u0016\u0014X\u0003BA}\u0005\u0013)\"!a?\u0011\u0011\u0005u(1\u0001B\u0004\u0005\u0017i!!a@\u000b\u0007\t\u0005\u0011&A\u0004nkR\f'\r\\3\n\t\t\u0015\u0011q \u0002\b\u0005VLG\u000eZ3s!\r)$\u0011\u0002\u0003\u0006oy\u0011\r\u0001\u000f\t\u0005c\u0001\u00119!\u0001\u0003ge>lW\u0003\u0002B\t\u0005/!BAa\u0005\u0003\u001aA!\u0011\u0007\u0001B\u000b!\r)$q\u0003\u0003\u0006o}\u0011\r\u0001\u000f\u0005\b\u00057y\u0002\u0019\u0001B\u000f\u0003\u0019\u0019x.\u001e:dKB)\u0011*a\u0018\u0003\u0016\u0005)Q-\u001c9usV!!1\u0005B\u0015+\t\u0011)\u0003\u0005\u00032\u0001\t\u001d\u0002cA\u001b\u0003*\u0011)q\u0007\tb\u0001qU!!Q\u0006B\u001a)\u0011\u0011yC!\u000e\u0011\tE\u0002!\u0011\u0007\t\u0004k\tMB!B\u001c\"\u0005\u0004A\u0004b\u0002B\u001cC\u0001\u0007!\u0011H\u0001\u0003qN\u0004RA\u000fB\u001e\u0005cI1A!\u0010,\u0005)a$/\u001a9fCR,GMP\u0001\u000b\u000b6\u0004H/_)vKV,\u0007c\u0001B\"G5\tA$\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003JA!\u0011\u0011\u0004B&\u0013\u0011\u0011i%a\u0007\u0003\r=\u0013'.Z2uQ\u001da\"\u0011\u000bB,\u00053\u00022A\u000fB*\u0013\r\u0011)f\u000b\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012aA\n\u0004G\tu\u0003cA\u0019\u0001sQ\u0011!\u0011I\u0001\u0006#V,W/\u001a\u0015\b7\tE#q\u000bB-\u0001"
)
public class Queue extends AbstractSeq implements LinearSeq, StrictOptimizedLinearSeqOps, StrictOptimizedSeqOps, DefaultSerializable {
   private final List in;
   private final List out;

   public static Queue from(final IterableOnce source) {
      return Queue$.MODULE$.from(source);
   }

   public static Builder newBuilder() {
      return Queue$.MODULE$.newBuilder();
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

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$immutable$StrictOptimizedSeqOps$$super$sorted(final Ordering ord) {
      return scala.collection.SeqOps.sorted$(this, ord);
   }

   public Object distinctBy(final Function1 f) {
      return StrictOptimizedSeqOps.distinctBy$(this, f);
   }

   public Object updated(final int index, final Object elem) {
      return StrictOptimizedSeqOps.updated$(this, index, elem);
   }

   public Object patch(final int from, final IterableOnce other, final int replaced) {
      return StrictOptimizedSeqOps.patch$(this, from, other, replaced);
   }

   public Object sorted(final Ordering ord) {
      return StrictOptimizedSeqOps.sorted$(this, ord);
   }

   public scala.collection.LinearSeq drop(final int n) {
      return StrictOptimizedLinearSeqOps.drop$(this, n);
   }

   public scala.collection.LinearSeq dropWhile(final Function1 p) {
      return StrictOptimizedLinearSeqOps.dropWhile$(this, p);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return scala.collection.StrictOptimizedSeqOps.prependedAll$(this, prefix);
   }

   public Object padTo(final int len, final Object elem) {
      return scala.collection.StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return scala.collection.StrictOptimizedSeqOps.intersect$(this, that);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public String stringPrefix() {
      return scala.collection.LinearSeq.stringPrefix$(this);
   }

   // $FF: synthetic method
   public boolean scala$collection$LinearSeqOps$$super$sameElements(final IterableOnce that) {
      return scala.collection.SeqOps.sameElements$(this, that);
   }

   public Option headOption() {
      return scala.collection.LinearSeqOps.headOption$(this);
   }

   public int lengthCompare(final int len) {
      return scala.collection.LinearSeqOps.lengthCompare$(this, len);
   }

   public int lengthCompare(final scala.collection.Iterable that) {
      return scala.collection.LinearSeqOps.lengthCompare$(this, that);
   }

   public boolean isDefinedAt(final int x) {
      return scala.collection.LinearSeqOps.isDefinedAt$(this, x);
   }

   public void foreach(final Function1 f) {
      scala.collection.LinearSeqOps.foreach$(this, f);
   }

   public boolean contains(final Object elem) {
      return scala.collection.LinearSeqOps.contains$(this, elem);
   }

   public Option find(final Function1 p) {
      return scala.collection.LinearSeqOps.find$(this, p);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return scala.collection.LinearSeqOps.foldLeft$(this, z, op);
   }

   public boolean sameElements(final IterableOnce that) {
      return scala.collection.LinearSeqOps.sameElements$(this, that);
   }

   public int segmentLength(final Function1 p, final int from) {
      return scala.collection.LinearSeqOps.segmentLength$(this, p, from);
   }

   public int indexWhere(final Function1 p, final int from) {
      return scala.collection.LinearSeqOps.indexWhere$(this, p, from);
   }

   public int lastIndexWhere(final Function1 p, final int end) {
      return scala.collection.LinearSeqOps.lastIndexWhere$(this, p, end);
   }

   public Option findLast(final Function1 p) {
      return scala.collection.LinearSeqOps.findLast$(this, p);
   }

   public Iterator tails() {
      return scala.collection.LinearSeqOps.tails$(this);
   }

   public List in() {
      return this.in;
   }

   public List out() {
      return this.out;
   }

   public SeqFactory iterableFactory() {
      return Queue$.MODULE$;
   }

   public Object apply(final int n) {
      int index = 0;

      List curr;
      for(curr = this.out(); index < n && curr.nonEmpty(); curr = (List)curr.tail()) {
         ++index;
      }

      if (index == n) {
         if (curr.nonEmpty()) {
            return curr.head();
         } else if (this.in().nonEmpty()) {
            return this.in().last();
         } else {
            throw indexOutOfRange$1(n);
         }
      } else {
         int indexFromBack = n - index;
         int inLength = this.in().length();
         if (indexFromBack >= inLength) {
            throw indexOutOfRange$1(n);
         } else {
            List var10000 = this.in();
            int apply_n = inLength - indexFromBack - 1;
            if (var10000 == null) {
               throw null;
            } else {
               return scala.collection.LinearSeqOps.apply$(var10000, apply_n);
            }
         }
      }
   }

   public Iterator iterator() {
      return this.out().iterator().concat(() -> this.in().reverse());
   }

   public boolean isEmpty() {
      return this.in().isEmpty() && this.out().isEmpty();
   }

   public Object head() {
      if (this.out().nonEmpty()) {
         return this.out().head();
      } else if (this.in().nonEmpty()) {
         return this.in().last();
      } else {
         throw new NoSuchElementException("head on empty queue");
      }
   }

   public Queue tail() {
      if (this.out().nonEmpty()) {
         return new Queue(this.in(), (List)this.out().tail());
      } else if (this.in().nonEmpty()) {
         return new Queue(Nil$.MODULE$, (List)this.in().reverse().tail());
      } else {
         throw new NoSuchElementException("tail on empty queue");
      }
   }

   public Object last() {
      if (this.in().nonEmpty()) {
         return this.in().head();
      } else if (this.out().nonEmpty()) {
         return this.out().last();
      } else {
         throw new NoSuchElementException("last on empty queue");
      }
   }

   public boolean forall(final Function1 p) {
      List var10000 = this.in();
      if (var10000 == null) {
         throw null;
      } else {
         List forall_these = var10000;

         while(true) {
            if (forall_these.isEmpty()) {
               var6 = true;
               break;
            }

            if (!BoxesRunTime.unboxToBoolean(p.apply(forall_these.head()))) {
               var6 = false;
               break;
            }

            forall_these = (List)forall_these.tail();
         }

         Object var4 = null;
         if (var6) {
            var10000 = this.out();
            if (var10000 == null) {
               throw null;
            }

            List forall_these = var10000;

            while(true) {
               if (forall_these.isEmpty()) {
                  var8 = true;
                  break;
               }

               if (!BoxesRunTime.unboxToBoolean(p.apply(forall_these.head()))) {
                  var8 = false;
                  break;
               }

               forall_these = (List)forall_these.tail();
            }

            Object var5 = null;
            if (var8) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean exists(final Function1 p) {
      List var10000 = this.in();
      if (var10000 == null) {
         throw null;
      } else {
         List exists_these = var10000;

         while(true) {
            if (exists_these.isEmpty()) {
               var6 = false;
               break;
            }

            if (BoxesRunTime.unboxToBoolean(p.apply(exists_these.head()))) {
               var6 = true;
               break;
            }

            exists_these = (List)exists_these.tail();
         }

         Object var4 = null;
         if (!var6) {
            var10000 = this.out();
            if (var10000 == null) {
               throw null;
            }

            List exists_these = var10000;

            while(true) {
               if (exists_these.isEmpty()) {
                  var8 = false;
                  break;
               }

               if (BoxesRunTime.unboxToBoolean(p.apply(exists_these.head()))) {
                  var8 = true;
                  break;
               }

               exists_these = (List)exists_these.tail();
            }

            Object var5 = null;
            if (!var8) {
               return false;
            }
         }

         return true;
      }
   }

   public String className() {
      return "Queue";
   }

   public int length() {
      return this.in().length() + this.out().length();
   }

   public Queue prepended(final Object elem) {
      Queue var10000 = new Queue;
      List var10002 = this.in();
      List var10003 = this.out();
      if (var10003 == null) {
         throw null;
      } else {
         List $colon$colon_this = var10003;
         $colon$colon var4 = new $colon$colon(elem, $colon$colon_this);
         $colon$colon_this = null;
         var10000.<init>(var10002, var4);
         return var10000;
      }
   }

   public Queue appended(final Object elem) {
      return this.enqueue(elem);
   }

   public Queue appendedAll(final IterableOnce that) {
      Object var11;
      if (that instanceof Queue) {
         Queue var3 = (Queue)that;
         var11 = var3.in();
         List var4 = var3.out();
         List $plus$plus_suffix = this.in().reverse_$colon$colon$colon(var4);
         if (var11 == null) {
            throw null;
         }

         var11 = var11.appendedAll($plus$plus_suffix);
         $plus$plus_suffix = null;
      } else if (that instanceof List) {
         List var5 = (List)that;
         var11 = this.in().reverse_$colon$colon$colon(var5);
      } else {
         List result = this.in();

         Object var8;
         for(Iterator iter = that.iterator(); iter.hasNext(); result = new $colon$colon(var8, result)) {
            var8 = iter.next();
            if (result == null) {
               throw null;
            }
         }

         var11 = result;
      }

      List newIn = var11;
      return newIn == this.in() ? this : new Queue(newIn, this.out());
   }

   public Queue enqueue(final Object elem) {
      Queue var10000 = new Queue;
      List var10002 = this.in();
      if (var10002 == null) {
         throw null;
      } else {
         List $colon$colon_this = var10002;
         $colon$colon var4 = new $colon$colon(elem, $colon$colon_this);
         $colon$colon_this = null;
         var10000.<init>(var4, this.out());
         return var10000;
      }
   }

   /** @deprecated */
   public final Queue enqueue(final scala.collection.Iterable iter) {
      return this.appendedAll(iter);
   }

   public Queue enqueueAll(final scala.collection.Iterable iter) {
      return this.appendedAll(iter);
   }

   public Tuple2 dequeue() {
      List var1 = this.out();
      if (Nil$.MODULE$.equals(var1) && !this.in().isEmpty()) {
         List rev = this.in().reverse();
         return new Tuple2(rev.head(), new Queue(Nil$.MODULE$, (List)rev.tail()));
      } else if (var1 instanceof $colon$colon) {
         $colon$colon var3 = ($colon$colon)var1;
         Object x = var3.head();
         List xs = var3.next$access$1();
         return new Tuple2(x, new Queue(this.in(), xs));
      } else {
         throw new NoSuchElementException("dequeue on empty queue");
      }
   }

   public Option dequeueOption() {
      return (Option)(this.isEmpty() ? None$.MODULE$ : new Some(this.dequeue()));
   }

   public Object front() {
      return this.head();
   }

   public String toString() {
      String mkString_end = ")";
      String mkString_sep = ", ";
      String mkString_start = "Queue(";
      return IterableOnceOps.mkString$(this, mkString_start, mkString_sep, mkString_end);
   }

   private static final Nothing$ indexOutOfRange$1(final int n$1) {
      throw new IndexOutOfBoundsException(Integer.toString(n$1));
   }

   public Queue(final List in, final List out) {
      this.in = in;
      this.out = out;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class EmptyQueue$ extends Queue {
      public static final EmptyQueue$ MODULE$ = new EmptyQueue$();

      public EmptyQueue$() {
         super(Nil$.MODULE$, Nil$.MODULE$);
      }
   }
}
